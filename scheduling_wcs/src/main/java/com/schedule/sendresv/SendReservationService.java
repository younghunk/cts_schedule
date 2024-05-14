package com.schedule.sendresv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schedule.dto.SendReservationResponse;
import com.schedule.enums.Method;
import com.schedule.enums.Response;
import com.schedule.genimg.GenerateImageService;
import com.schedule.util.APIRequest;

@Service
public class SendReservationService {
	private static final Logger log = LoggerFactory.getLogger(SendReservationService.class);
	
//	@Value("${kdapi.path}")
//	private String kdApiUrl;
	
	@Autowired
	SendReservationDao sendDao;
	@Autowired
	GenerateImageService geneDao;
	
	public void creatStepSendReservation() {
		List<HashMap<String, Object>> list=  sendDao.selectSendReservationRequest();
		log.debug(">>>>>>>>>>list:"+list);
		
		ArrayList<SendReservationResponse> list2= new ArrayList<>();
		for(HashMap<String,Object> requestBody: list ) {
			APIRequest request = new APIRequest("https://kdapi.kditlab.com:444/v1/SendReservation", Method.POST, Response.JSON);
	        request.setRequestBody(requestBody);
	        SendReservationResponse response = request.getRestAPIResponse(SendReservationResponse.class);
	        log.debug(">>>>>>>>>>response:"+response);
	        if (response != null) {
	            response.setBL(requestBody.get("BL").toString());
	            list2.add(response);
	        }
		}
		for (SendReservationResponse item : list2) {
            sendDao.saveSendReservationInfo(item);
            log.info("Process Parcel CJ Response BL Number" + item.getBL() + " and Code" + item.getCODE() + " Written Completed!!!");
        }
		
		geneDao.generateImageAndSave();
	}
}
