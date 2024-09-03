package com.example.samplebatch.batch;

import com.example.samplebatch.entity.DrugEntity;
import com.example.samplebatch.repository.DrugRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DrugApiReader implements ItemReader<DrugEntity> {

    private static final Logger logger = LoggerFactory.getLogger(DrugApiReader.class);

    private final RestTemplate restTemplate;
    private final DrugRepository drugRepository;
    private final String serviceKey = "4/QsOE8OxyyVse/LVodjHXg5ScZuaCH6rgbU9Kx4dtIKpGY/xQe98tXhaiIGDIB9rSbtiC01IBVBI6LZSRmZ2A=="; // 하드코딩된 serviceKey
    private int currentPage = 1;
    private int totalPages = -1;
    private List<DrugEntity> currentBatch = new ArrayList<>();

    private static final String API_URL_TEMPLATE = "https://apis.data.go.kr/1471000/DrugPrdtPrmsnInfoService06/getDrugPrdtPrmsnDtlInq05?serviceKey=%s&pageNo=%d&numOfRows=%d&type=json";

    public DrugApiReader(RestTemplate restTemplate, DrugRepository drugRepository) {
        this.restTemplate = restTemplate;
        this.drugRepository = drugRepository;
    }
    @Override
    public DrugEntity read() throws Exception {
        if (currentBatch.isEmpty()) {
            if (totalPages == -1 || currentPage <= totalPages) {
                DrugApiResponse response = fetchApiData();
                if (response != null && response.getBody().getItems() != null) {
                    List<DrugItem> items = response.getBody().getItems();
                    logger.info("Fetched {} items from API.", items.size());

                    for (DrugItem item : items) {
                        DrugEntity entity = convertToEntity(item);
                        Optional<DrugEntity> existingEntity = drugRepository.findByItemSeq(entity.getItemSeq());
                        if (existingEntity.isPresent()) {
                            DrugEntity existing = existingEntity.get();
                            updateExistingEntity(existing, entity);
                            drugRepository.save(existing);
                        } else {
                            drugRepository.save(entity);
                        }
                        currentBatch.add(entity);
                    }

                    if (totalPages == -1) {
                        totalPages = (int) Math.ceil((double) response.getBody().getTotalCount() / 100);
                        logger.info("Total pages to fetch: {}", totalPages);
                    }
                    currentPage++;
                } else {
                    logger.warn("Empty response or no body found for page: {}", currentPage);
                    return null;
                }
            } else {
                return null; // 모든 페이지를 다 읽었으면 null을 반환
            }
        }

        if (!currentBatch.isEmpty()) {
            return currentBatch.remove(0); // Batch에서 하나씩 반환
        }

        return null;
    }




    private DrugApiResponse fetchApiData() throws Exception {
        try {
            String url = String.format(API_URL_TEMPLATE, serviceKey, currentPage, 100);
            logger.info("Fetching data from API: {}", url);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<DrugApiResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, DrugApiResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("API call successful, status code: {}", response.getStatusCode());
            } else {
                logger.error("API call failed, status code: {}", response.getStatusCode());
                return null;
            }

            if (response.getBody() == null || response.getBody().getBody() == null) {
                logger.error("API response body is null.");
                return null;
            }

            logger.info("Received response with {} items.", response.getBody().getBody().getItems().size());
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error occurred while fetching data from API", e);
            throw e; // 필요에 따라 예외를 던지거나 처리할 수 있습니다.
        }
    }

    public void updateExistingEntity(DrugEntity existing, DrugEntity update) {
        existing.setItemSeq(update.getItemSeq());
        existing.setItemName(update.getItemName());
        existing.setEntpName(update.getEntpName());
        existing.setItemPermitDate(update.getItemPermitDate());
        existing.setCnsngManuf(update.getCnsngManuf());
        existing.setEtcOtcCode(update.getEtcOtcCode());
        existing.setChart(update.getChart());
        existing.setBarCode(update.getBarCode());
        existing.setMaterialName(update.getMaterialName());
        existing.setEeDocId(update.getEeDocId());
        existing.setUdDocId(update.getUdDocId());
        existing.setNbDocId(update.getNbDocId());
        existing.setInsertFile(update.getInsertFile());
        existing.setStorageMethod(update.getStorageMethod());
        existing.setValidTerm(update.getValidTerm());
        existing.setReexamTarget(update.getReexamTarget());
        existing.setReexamDate(update.getReexamDate());
        existing.setPackUnit(update.getPackUnit());
        existing.setEdiCode(update.getEdiCode());
        existing.setDocText(update.getDocText());
        existing.setPermitKindName(update.getPermitKindName());
        existing.setEntpNo(update.getEntpNo());
        existing.setMakeMaterialFlag(update.getMakeMaterialFlag());
        existing.setNewdrugClassName(update.getNewdrugClassName());
        existing.setIndutyType(update.getIndutyType());
        existing.setCancelDate(update.getCancelDate());
        existing.setCancelName(update.getCancelName());
        existing.setChangeDate(update.getChangeDate());
        existing.setNarcoticKindCode(update.getNarcoticKindCode());
        existing.setGbnName(update.getGbnName());
        existing.setTotalContent(update.getTotalContent());
        existing.setEeDocData(update.getEeDocData());
        existing.setUdDocData(update.getUdDocData());
        existing.setNbDocData(update.getNbDocData());
        existing.setPnDocData(update.getPnDocData());
        existing.setMainItemIngr(update.getMainItemIngr());
        existing.setIngrName(update.getIngrName());
        existing.setAtcCode(update.getAtcCode());
        existing.setItemEngName(update.getItemEngName());
        existing.setEntpEngName(update.getEntpEngName());
        existing.setMainIngrEng(update.getMainIngrEng());
        existing.setBizrno(update.getBizrno());
        logger.info("Updated existing DrugEntity with new values: {}", existing);
    }


    public DrugEntity convertToEntity(DrugItem item) {
        DrugEntity entity = new DrugEntity();
        entity.setItemSeq(item.getItemSeq());
        entity.setItemName(item.getItemName());
        entity.setEntpName(item.getEntpName());
        entity.setItemPermitDate(item.getItemPermitDate());
        entity.setCnsngManuf(item.getCnsngManuf());
        entity.setEtcOtcCode(item.getEtcOtcCode());
        entity.setChart(item.getChart());
        entity.setBarCode(item.getBarCode());
        entity.setMaterialName(item.getMaterialName());
        entity.setEeDocId(item.getEeDocId());
        entity.setUdDocId(item.getUdDocId());
        entity.setNbDocId(item.getNbDocId());
        entity.setInsertFile(item.getInsertFile());
        entity.setStorageMethod(item.getStorageMethod());
        entity.setValidTerm(item.getValidTerm());
        entity.setReexamTarget(item.getReexamTarget());
        entity.setReexamDate(item.getReexamDate());
        entity.setPackUnit(item.getPackUnit());
        entity.setEdiCode(item.getEdiCode());
        entity.setDocText(item.getDocText());
        entity.setPermitKindName(item.getPermitKindName());
        entity.setEntpNo(item.getEntpNo());
        entity.setMakeMaterialFlag(item.getMakeMaterialFlag());
        entity.setNewdrugClassName(item.getNewdrugClassName());
        entity.setIndutyType(item.getIndutyType());
        entity.setCancelDate(item.getCancelDate());
        entity.setCancelName(item.getCancelName());
        entity.setChangeDate(item.getChangeDate());
        entity.setNarcoticKindCode(item.getNarcoticKindCode());
        entity.setGbnName(item.getGbnName());
        entity.setTotalContent(item.getTotalContent());
        entity.setEeDocData(truncateString(removeXmlTags(item.getEeDocData()), 3000));
        entity.setUdDocData(truncateString(removeXmlTags(item.getUdDocData()), 3000));
        entity.setNbDocData(truncateString(removeXmlTags(item.getNbDocData()), 3000));
        entity.setPnDocData(truncateString(removeXmlTags(item.getPnDocData()), 3000));
        entity.setMainItemIngr(item.getMainItemIngr());
        entity.setIngrName(item.getIngrName());
        entity.setAtcCode(item.getAtcCode());
        entity.setItemEngName(item.getItemEngName());
        entity.setEntpEngName(item.getEntpEngName());
        entity.setMainIngrEng(item.getMainIngrEng());
        entity.setBizrno(item.getBizrno());
        logger.info("Converted DrugItem to DrugEntity: {}", entity);
        return entity;
    }

    // XML 태그를 제거하는 메서드
    private String removeXmlTags(String xml) {
        if (xml == null || xml.isEmpty()) {
            return xml;
        }
        Document doc = Jsoup.parse(xml);
        Element body = doc.body();
        return body.text();
    }

    // 문자열을 주어진 바이트 한도 내에서 잘라내는 메서드
    private String truncateString(String str, int byteLimit) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        byte[] strBytes = str.getBytes();
        if (strBytes.length <= byteLimit) {
            return str;
        }

        int endIndex = 0;
        int byteCount = 0;

        for (int i = 0; i < str.length(); i++) {
            byteCount += String.valueOf(str.charAt(i)).getBytes().length;
            if (byteCount > byteLimit) {
                break;
            }
            endIndex = i + 1;
        }

        return str.substring(0, endIndex);
    }

}
