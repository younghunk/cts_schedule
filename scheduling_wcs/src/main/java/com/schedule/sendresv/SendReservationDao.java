package com.schedule.sendresv;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.schedule.dto.SendReservationResponse;

@Repository
public class SendReservationDao {
	
	@Autowired
    @Qualifier(value = "orgSqlSession")
    private SqlSession orgSqlSession;

    public List<HashMap<String, Object>> selectSendReservationRequest() {
        return orgSqlSession.selectList("sendReservation.selectSendReservationRequest");
    }
    
    public int saveSendReservationInfo(SendReservationResponse parcelInfo) {
        return orgSqlSession.update("sendReservation.saveSendReservationInfo", parcelInfo);
    }
}
