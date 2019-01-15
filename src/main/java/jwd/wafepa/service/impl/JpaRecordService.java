package jwd.wafepa.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Record;
import jwd.wafepa.repository.RecordRepository;
import jwd.wafepa.service.RecordService;

@Service
@Transactional
public class JpaRecordService 
	implements RecordService {
	
	private static final int PAGE_SIZE = 5;
	
	@Autowired
	private RecordRepository recordRepository;

	@Override
	public Record findOne(Long id) {
		return recordRepository.findOne(id);
	}

	@Override
	public Page<Record> findAll(int page) {
		return recordRepository.findAll(new PageRequest(page, PAGE_SIZE));
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

	@Override
	public Page<Record> findByUserId(Long userId, int page) {
		return recordRepository.findByUserId(userId, new PageRequest(page, PAGE_SIZE));
	}

}
