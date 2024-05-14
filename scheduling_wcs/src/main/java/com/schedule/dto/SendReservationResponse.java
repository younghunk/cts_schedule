package com.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendReservationResponse {
    @JsonProperty("BR_END_NM")
    private String BR_END_NM;

    @JsonProperty("BR_END_TEL")
    private String BR_END_TEL;

    @JsonProperty("CODE")
    private String CODE;

    @JsonProperty("PD_BARCODE")
    private String PD_BARCODE;

    @JsonProperty("PD_BOHUM_PAY")
    private String PD_BOHUM_PAY;

    @JsonProperty("PD_DOCK1")
    private String PD_DOCK1;

    @JsonProperty("PD_DOCK2")
    private String PD_DOCK2;

    @JsonProperty("PD_DOSUN_PAY")
    private int PD_DOSUN_PAY;

    @JsonProperty("PD_TEMP")
    private String PD_TEMP;

    @JsonProperty("PD_TERMINAL1")
    private String PD_TERMINAL1;

    @JsonProperty("PD_TERMINAL2")
    private String PD_TERMINAL2;
    
    private String BL;
}
