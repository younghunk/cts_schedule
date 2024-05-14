package com.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProcessParcelKDResponse {
    private String hbl;
    private String bl;
    private Info info;
    private List<items> items;

    final private String company = "KD";
    @Setter
    @Getter
    public static class items {
        @JsonProperty("reg_date")
        private String reg_date;
        @JsonProperty("stat")
        private String status;
        @JsonProperty("location")
        private String location;
        @JsonProperty("tel")
        private String tel;
        @JsonProperty("sc_nm")
        private String branNm;
        @JsonProperty("sc_stat")
        private String sc_stat;
        public String getTrackingInfo(){
            return this.status + "(담당자 : "+ this.location + " " + this.tel + ")";
        }
    }

    @Getter
    @Setter
    private static class Info {
        private String branch_end;
        private String pd_dt;
        private String prod;
        private String send_name;
        private String cnt;
        private String re_name;
        private String rec_dt;
        private String barcode;
        private String branch_start;
        private String relation;
    }
}
