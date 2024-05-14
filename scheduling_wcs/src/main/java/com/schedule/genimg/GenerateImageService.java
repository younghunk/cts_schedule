package com.schedule.genimg;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schedule.dto.GenerateImageResponse;

@Service
public class GenerateImageService {
	private static final Logger log = LoggerFactory.getLogger(GenerateImageService.class);
	
//	@Value("${kdapi.path}")
//	private String kdApiUrl;
	
	@Autowired
	GenerateImageDao generDao;
	
	public void generateImageAndSave() {
		List<HashMap<String, Object>> resultList = generDao.getListForGenerateImage();
		for(HashMap<String, Object> item : resultList) {
			try {
				GenerateImageResponse data= process(item);
				generDao.deleteExistenceImage(data);
				generDao.insertGenerateImageBLOB(data);
				generDao.updateListForGenerateImage(data);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public GenerateImageResponse process(HashMap<String, Object> item) throws ParseException, IOException {

        ImageGenerator imageGenerator = new ImageGenerator();

        String formattedDate = new SimpleDateFormat("yyyy.MM.dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(item.get("REG_DATE").toString()));
        imageGenerator.setREG_DATE(formattedDate);

        int PD_DELI_PAY = (int) item.get("PD_DELI_PAY");
        int temp1 = PD_DELI_PAY / 10;
        double temp2 = (double) temp1 / 100;
        int temp3 = temp1 % 100;

        imageGenerator.setHours("10");
        imageGenerator.setMin("12");
        imageGenerator.setTemp2(String.valueOf(temp2));
        imageGenerator.setTemp3(String.valueOf(temp3));
        if (item.containsKey("PD_NM")) imageGenerator.setCOM_NM(item.get("PD_NM").toString());
        if (item.containsKey("PD_BARCODE")) imageGenerator.setPD_BARCODE(item.get("PD_BARCODE").toString());
        if (item.containsKey("PD_BARCODE")&&item.containsKey("PD_CNT")) imageGenerator.setPD_BARCODE_L(item.get("PD_BARCODE").toString()+"."+item.get("PD_CNT"));
        if (item.containsKey("PD_NM")) imageGenerator.setPD_NM(item.get("PD_NM").toString());
        if (item.containsKey("PD_DELI_GUBUN")) imageGenerator.setPD_DELI_GUBUN(item.get("PD_DELI_GUBUN").toString());
        if (item.containsKey("CNT")) imageGenerator.setPD_CNT((int) item.get("CNT"));
        if (item.containsKey("PD_PACK")) imageGenerator.setPD_PACK(item.get("PD_PACK").toString());
        if (item.containsKey("PD_DOCK1")) imageGenerator.setPD_DOCK1(item.get("PD_DOCK1").toString());
        if (item.containsKey("BR_END_NM")) imageGenerator.setBR_END_NM(item.get("BR_END_NM").toString());
        if (item.containsKey("BR_END_TEL")) imageGenerator.setBR_END_TEL(item.get("BR_END_TEL").toString());
        if (item.containsKey("BL")) imageGenerator.setBL(item.get("BL").toString());
        if (item.containsKey("SEND_NM")) imageGenerator.setSEND_NM(item.get("SEND_NM").toString());
        if (item.containsKey("RECV_NM")) imageGenerator.setRECV_NM(item.get("RECV_NM").toString());
        if (item.containsKey("RECV_TEL1")) imageGenerator.setRECV_TEL1(item.get("RECV_TEL1").toString());
        if (item.containsKey("RECV_ZIPCODE")) imageGenerator.setRECV_ZIPCODE(item.get("RECV_ZIPCODE").toString());
        if (item.containsKey("RECV_ADDR1")) imageGenerator.setRECV_ADDR1(item.get("RECV_ADDR1").toString());
        if (item.containsKey("RECV_ADDR2")) imageGenerator.setRECV_ADDR2(item.get("RECV_ADDR2").toString());
        if (item.containsKey("PD_MEMO")) imageGenerator.setPD_MEMO(item.get("PD_MEMO").toString());
        if (item.containsKey("PD_DOCK2")) imageGenerator.setPD_DOCK2(item.get("PD_DOCK2").toString());
        if (item.containsKey("PD_TERMINAL1")) imageGenerator.setPD_TERMINAL1(item.get("PD_TERMINAL1").toString());

        byte[] blob = imageGenerator.generateImage();

        GenerateImageResponse response = new GenerateImageResponse();
        response.setREG_DATE(item.get("REG_DATE").toString());
        response.setBL(item.get("BL").toString());
        response.setFILE_NAME(item.get("BL").toString() + ".jpg");
        response.setFILE_SIZE(blob.length);
        response.setFILE(blob);
        response.setURL("https://mail.google.com/mail/u/0/");
        return response;
    }
}
