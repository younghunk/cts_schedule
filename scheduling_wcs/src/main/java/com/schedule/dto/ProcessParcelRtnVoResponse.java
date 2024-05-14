package com.schedule.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JacksonXmlRootElement(localName = "cargCsclPrgsInfoQryRtnVo")
public class ProcessParcelRtnVoResponse {

    private String mbl;

    private String consigneeCom;

    private Integer uniqueKey;

    @JacksonXmlProperty(localName = "ntceInfo")
    private String ntceInfo;

    @JacksonXmlProperty(localName = "tCnt")
    private int tCnt;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "cargCsclPrgsInfoQryVo")
    private List<ProcessParcelVoResponse> cargCsclPrgsInfoQryVo;

    @Setter
    @Getter
    @JacksonXmlRootElement(localName = "cargCsclPrgsInfoQryVo")
    public static class ProcessParcelVoResponse {

        @JacksonXmlProperty(localName = "cargMtNo")
        private String cargMtNo;

        @JacksonXmlProperty(localName = "hblNo")
        private String hblNo;

        @JacksonXmlProperty(localName = "mblNo")
        private String mblNo;

        @JacksonXmlProperty(localName = "cntrNo")
        private String cntrNo;

        @JacksonXmlProperty(localName = "frwrEntsConm")
        private String frwrEntsConm;

        @JacksonXmlProperty(localName = "shipNm")
        private String shipNm;

        @JacksonXmlProperty(localName = "prnm")
        private String prnm;

        @JacksonXmlProperty(localName = "prgsStts")
        private String prgsStts;

        @JacksonXmlProperty(localName = "ttwg")
        private String ttwg;

        @JacksonXmlProperty(localName = "pckGcnt")
        private String pckGcnt;

        @JacksonXmlProperty(localName = "cargTp")
        private String cargTp;

        @JacksonXmlProperty(localName = "prcsDttm")
        private String prcsDttm;

        private String cargMtNo1;

        private String cargMtNo2;

        private String cargMtNo3;
    }

}
