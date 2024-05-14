package com.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProcessParcelCJResponse extends ParcelResponse {

    final static String company = "CJ";

    private String hbl;

    private String bl;

    @JsonProperty("data")
    private Data data;

    @JsonProperty("resultCode")
    private int resultCode;

    @JsonProperty("resultMessage")
    private String resultMessage;

    @Setter
    @Getter
    public static class Data {
        private List<SvcOutListItem> svcOutList;

        @Getter
        @Setter
        public static class SvcOutListItem {

            @JsonProperty("branNm")
            private String branNm;
            @JsonProperty("procBranTelNo")
            private String procBranTelNo;
            @JsonProperty("workDt")
            private String workDt;
            @JsonProperty("workHms")
            private String workHms;
            @JsonProperty("crgStDnm")
            private String status;
            @JsonProperty("crgStDcdVal")
            private String trackingInfo;
            @JsonProperty("patnBranNm")
            private String patnBranNm;

        }
    }
}
