package jwd.wafepa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Record;

public interface RecordService {

	Record findOne(Long id);
	Page<Record> findAll(int page);
	Record save(Record record);
	Record delete(Long id);
	Page<Record> findByUserId(Long userId, int page);
	Page<Record> findByActivityId(Long activityId, int page);
	Page<Record> search(
			@Param("activityName") String activityName, 
			@Param("minDuration") Integer minDuration, 
			@Param("intensity") String intensity, 
			int page);
}
