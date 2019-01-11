package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Record;

public interface RecordService {

	Record findOne(Long id);
	List<Record> findAll();
	Record save(Record record);
	Record delete(Long id);
	
}
