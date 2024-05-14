package com.schedule.genimg;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.schedule.dto.GenerateImageResponse;

@Repository
public class GenerateImageDao {
	
	@Autowired
    @Qualifier(value = "orgSqlSession")
    private SqlSession orgSqlSession;

    public List<HashMap<String, Object>> getListForGenerateImage() {
        return orgSqlSession.selectList("generateImage.getListForGenerateImage");
    }
    
    public int insertGenerateImageBLOB(GenerateImageResponse data) {
        return orgSqlSession.insert("generateImage.insertGenerateImageBLOB", data);
    }
    public int deleteExistenceImage(GenerateImageResponse data) {
    	return orgSqlSession.delete("generateImage.deleteExistenceImage", data);
    }
    public int updateListForGenerateImage(GenerateImageResponse data) {
    	return orgSqlSession.update("generateImage.updateListForGenerateImage", data);
    }
}
