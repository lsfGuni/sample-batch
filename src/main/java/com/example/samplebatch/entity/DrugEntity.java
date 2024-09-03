package com.example.samplebatch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "api_drug_list-batch")
@Data
public class DrugEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_seq", length = 255)
    private String itemSeq;

    @Column(name = "ITEM_NAME", columnDefinition = "TEXT")
    private String itemName;

    @Column(name = "entp_name", length = 255)
    private String entpName;

    @Column(name = "item_permit_date", length = 255)
    private String itemPermitDate;

    @Column(name = "cnsgn_manuf", length = 255)
    private String cnsngManuf;

    @Column(name = "etc_otc_code", length = 255)
    private String etcOtcCode;

    @Column(name = "CHART", columnDefinition = "TEXT")
    private String chart;

    @Column(name = "BAR_CODE", columnDefinition = "TEXT")
    private String barCode;

    @Column(name = "MATERIAL_NAME", columnDefinition = "TEXT")
    private String materialName;

    @Column(name = "EE_DOC_ID", columnDefinition = "TEXT")
    private String eeDocId;

    @Column(name = "UD_DOC_ID", columnDefinition = "TEXT")
    private String udDocId;

    @Column(name = "NB_DOC_ID", columnDefinition = "TEXT")
    private String nbDocId;

    @Column(name = "INSERT_FILE", columnDefinition = "TEXT")
    private String insertFile;

    @Column(name = "STORAGE_METHOD", columnDefinition = "TEXT")
    private String storageMethod;

    @Column(name = "VALID_TERM", columnDefinition = "TEXT")
    private String validTerm;

    @Column(name = "REEXAM_TARGET", columnDefinition = "TEXT")
    private String reexamTarget;

    @Column(name = "REEXAM_DATE", columnDefinition = "TEXT")
    private String reexamDate;

    @Column(name = "PACK_UNIT", columnDefinition = "TEXT")
    private String packUnit;

    @Column(name = "EDI_CODE", columnDefinition = "TEXT")
    private String ediCode;

    @Column(name = "DOC_TEXT", columnDefinition = "TEXT")
    private String docText;

    @Column(name = "PERMIT_KIND_NAME", columnDefinition = "TEXT")
    private String permitKindName;

    @Column(name = "ENTP_NO", columnDefinition = "TEXT")
    private String entpNo;

    @Column(name = "MAKE_MATERIAL_FLAG", columnDefinition = "TEXT")
    private String makeMaterialFlag;

    @Column(name = "NEWDRUG_CLASS_NAME", columnDefinition = "TEXT")
    private String newdrugClassName;

    @Column(name = "INDUTY_TYPE", columnDefinition = "TEXT")
    private String indutyType;

    @Column(name = "CANCEL_DATE", columnDefinition = "TEXT")
    private String cancelDate;

    @Column(name = "CANCEL_NAME", columnDefinition = "TEXT")
    private String cancelName;

    @Column(name = "CHANGE_DATE", columnDefinition = "TEXT")
    private String changeDate;

    @Column(name = "NARCOTIC_KIND_CODE", columnDefinition = "TEXT")
    private String narcoticKindCode;

    @Column(name = "GBN_NAME", columnDefinition = "TEXT")
    private String gbnName;

    @Column(name = "TOTAL_CONTENT", columnDefinition = "TEXT")
    private String totalContent;

    @Column(name = "EE_DOC_DATA", columnDefinition = "TEXT")
    private String eeDocData;

    @Column(name = "UD_DOC_DATA", columnDefinition = "TEXT")
    private String udDocData;

    @Column(name = "NB_DOC_DATA", columnDefinition = "TEXT")
    private String nbDocData;

    @Column(name = "PN_DOC_DATA", columnDefinition = "TEXT")
    private String pnDocData;

    @Column(name = "MAIN_ITEM_INGR", columnDefinition = "TEXT")
    private String mainItemIngr;

    @Column(name = "INGR_NAME", columnDefinition = "TEXT")
    private String ingrName;

    @Column(name = "ATC_CODE", columnDefinition = "TEXT")
    private String atcCode;

    @Column(name = "ITEM_ENG_NAME", columnDefinition = "TEXT")
    private String itemEngName;

    @Column(name = "ENTP_ENG_NAME", columnDefinition = "TEXT")
    private String entpEngName;

    @Column(name = "MAIN_INGR_ENG", columnDefinition = "TEXT")
    private String mainIngrEng;

    @Column(name = "bizrno", length = 255)
    private String bizrno;
}
