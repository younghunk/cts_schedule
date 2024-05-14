package com.schedule;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
	
	@Autowired
    @Qualifier(value = "orgSqlSession")
    private SqlSession orgSqlSession;

    public List<HashMap<String, String>> selectSendReservationRequest() {
        return orgSqlSession.selectList("test.selectSendReservationRequest");
    }
}
