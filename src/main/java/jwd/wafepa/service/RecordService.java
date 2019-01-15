package jwd.wafepa.service;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.Record;

public interface RecordService {

	Record findOne(Long id);
	Page<Record> findAll(int page);
	Record save(Record record);
	Record delete(Long id);
	Page<Record> findByUserId(Long userId, int page);
}
