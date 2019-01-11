package jwd.wafepa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Record;
import jwd.wafepa.repository.RecordRepository;
import jwd.wafepa.service.RecordService;

@Service
@Transactional
public class JpaRecordService 
	implements RecordService {
	
	@Autowired
	private RecordRepository recordRepository;

	@Override
	public Record findOne(Long id) {
		return recordRepository.findOne(id);
	}

	@Override
	public List<Record> findAll() {
		return recordRepository.findAll();
	}

	@Override
	public Record save(Record record) {
		return recordRepository.save(record);
	}

	@Override
	public Record delete(Long id) {
		Record toDelete = recordRepository.findOne(id);
		
		if(toDelete != null) {
			recordRepository.delete(toDelete);
		}
		
		return toDelete;
	}

}
