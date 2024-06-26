/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.schedule.sendresv.SendReservationService;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	TestDao dao;
	
	@Autowired
	SendReservationService sendService;
	
	@Scheduled(fixedRate = 3000)
	public void reportCurrentTime() {
//		log.info("The time is now {}", dateFormat.format(new Date()));
//		List<HashMap<String, String>> list = dao.selectSendReservationRequest();
//		log.info(">>>>>>>>>>>>>>list:"+list);
		sendService.creatStepSendReservation();
		
	}
	
	@Scheduled(cron = "1 * * * * ?")
	public void cronCurrentTime() {
		//log.info("cron {}", dateFormat.format(new Date()));
	}
}
