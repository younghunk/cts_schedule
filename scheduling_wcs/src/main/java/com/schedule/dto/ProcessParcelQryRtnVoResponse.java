package com.schedule.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@JacksonXmlRootElement(localName = "cargCsclPrgsInfoQryRtnVo")
public class ProcessParcelQryRtnVoResponse {

    @JacksonXmlProperty(localName = "ntceInfo")
    private String ntceInfo;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "cargCsclPrgsInfoQryVo")
    private ProcessParcelRtnVoResponse.ProcessParcelVoResponse cargCsclPrgsInfoQryVo;

    @JacksonXmlProperty(localName = "tCnt")
    private int tCnt;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "cargCsclPrgsInfoDtlQryVo")
    private List<ProcessParcelDtlQryVoResponse> cargCsclPrgsInfoDtlQryVo;

    @Getter
    @Setter
    @JacksonXmlRootElement(localName = "cargCsclPrgsInfoDtlQryVo")
    public static class ProcessParcelDtlQryVoResponse{
        @JacksonXmlProperty(localName = "shedNm")
        private String shedNm;

        @JacksonXmlProperty(localName = "prcsDttm")
        private String prcsDttm;

        @JacksonXmlProperty(localName = "cargTrcnRelaBsopTpcd")
        private String cargTrcnRelaBsopTpcd;

        @JacksonXmlProperty(localName = "dclrNo")
        private String dclrNo;

        @JacksonXmlProperty(localName = "wght")
        private String wght;

        @JacksonXmlProperty(localName = "wghtUt")
        private String wghtUt;

        @JacksonXmlProperty(localName = "pckGcnt")
        private String pckGcnt;

        @JacksonXmlProperty(localName = "shedSgn")
        private String shedSgn;

        @JacksonXmlProperty(localName = "rlbrCn")
        private String rlbrCn;

        @JacksonXmlProperty(localName = "rlbrBssNo")
        private String rlbrBssNo;

        @JacksonXmlProperty(localName = "rlbrDttm")
        private String rlbrDttm;

        @JacksonXmlProperty(localName = "bfhnGdncCn")
        private String bfhnGdncCn;
    }
}
