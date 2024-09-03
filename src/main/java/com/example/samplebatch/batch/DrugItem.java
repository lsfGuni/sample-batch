package com.example.samplebatch.batch;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class DrugItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("ITEM_SEQ")
    private String itemSeq;

    @JsonProperty("ITEM_NAME")
    private String itemName;

    @JsonProperty("ENTP_NAME")
    private String entpName;

    @JsonProperty("ITEM_PERMIT_DATE")
    private String itemPermitDate;

    @JsonProperty("CNSGN_MANUF")
    private String cnsngManuf;

    @JsonProperty("ETC_OTC_CODE")
    private String etcOtcCode;

    @JsonProperty("CHART")
    private String chart;

    @JsonProperty("BAR_CODE")
    private String barCode;

    @JsonProperty("MATERIAL_NAME")
    private String materialName;

    @JsonProperty("EE_DOC_ID")
    private String eeDocId;

    @JsonProperty("UD_DOC_ID")
    private String udDocId;

    @JsonProperty("NB_DOC_ID")
    private String nbDocId;

    @JsonProperty("INSERT_FILE")
    private String insertFile;

    @JsonProperty("STORAGE_METHOD")
    private String storageMethod;

    @JsonProperty("VALID_TERM")
    private String validTerm;

    @JsonProperty("REEXAM_TARGET")
    private String reexamTarget;

    @JsonProperty("REEXAM_DATE")
    private String reexamDate;

    @JsonProperty("PACK_UNIT")
    private String packUnit;

    @JsonProperty("EDI_CODE")
    private String ediCode;

    @JsonProperty("DOC_TEXT")
    private String docText;

    @JsonProperty("PERMIT_KIND_NAME")
    private String permitKindName;

    @JsonProperty("ENTP_NO")
    private String entpNo;

    @JsonProperty("MAKE_MATERIAL_FLAG")
    private String makeMaterialFlag;

    @JsonProperty("NEWDRUG_CLASS_NAME")
    private String newdrugClassName;

    @JsonProperty("INDUTY_TYPE")
    private String indutyType;

    @JsonProperty("CANCEL_DATE")
    private String cancelDate;

    @JsonProperty("CANCEL_NAME")
    private String cancelName;

    @JsonProperty("CHANGE_DATE")
    private String changeDate;

    @JsonProperty("NARCOTIC_KIND_CODE")
    private String narcoticKindCode;

    @JsonProperty("GBN_NAME")
    private String gbnName;

    @JsonProperty("TOTAL_CONTENT")
    private String totalContent;

    @JsonProperty("EE_DOC_DATA")
    private String eeDocData;

    @JsonProperty("UD_DOC_DATA")
    private String udDocData;

    @JsonProperty("NB_DOC_DATA")
    private String nbDocData;

    @JsonProperty("PN_DOC_DATA")
    private String pnDocData;

    @JsonProperty("MAIN_ITEM_INGR")
    private String mainItemIngr;

    @JsonProperty("INGR_NAME")
    private String ingrName;

    @JsonProperty("ATC_CODE")
    private String atcCode;

    @JsonProperty("ITEM_ENG_NAME")
    private String itemEngName;

    @JsonProperty("ENTP_ENG_NAME")
    private String entpEngName;

    @JsonProperty("MAIN_INGR_ENG")
    private String mainIngrEng;

    @JsonProperty("BIZRNO")
    private String bizrno;
}
