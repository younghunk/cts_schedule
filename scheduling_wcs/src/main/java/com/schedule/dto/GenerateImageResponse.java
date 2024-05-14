package com.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenerateImageResponse {
    private String REG_DATE;
    private String BL;
    private String FILE_NAME;
    private int FILE_SIZE;
    private byte[]  FILE;
    private String URL;
}
