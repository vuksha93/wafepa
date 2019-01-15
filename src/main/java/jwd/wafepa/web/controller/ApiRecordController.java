package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Record;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.support.RecordDTOToRecord;
import jwd.wafepa.support.RecordToRecordDTO;
import jwd.wafepa.web.dto.RecordDTO;

@RestController
@RequestMapping("api/records")
public class ApiRecordController {
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private RecordToRecordDTO toRecordDto;
	
	@Autowired
	private RecordDTOToRecord toRecord;

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<RecordDTO>> getRecords(
			@RequestParam(value="page", defaultValue="0") int page) {
		
		Page<Record> recordsPages = recordService.findAll(page);
		
		if(recordsPages == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toRecordDto.convert(recordsPages.getContent()),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<RecordDTO> getRecord(@PathVariable Long id) {
		
		Record record = recordService.findOne(id);
		
		if(record == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toRecordDto.convert(record), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<RecordDTO> deleteRecord(@PathVariable Long id) {
		
		Record deleted = recordService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toRecordDto.convert(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<RecordDTO> addRecord(
			@Validated
			@RequestBody RecordDTO newRecordDto) {
		
		Record record = recordService.save(toRecord.convert(newRecordDto));
		
		Record newRecord = recordService.save(record);
		
		return new ResponseEntity<>(toRecordDto.convert(newRecord), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<RecordDTO> editRecord(
			@Validated
			@PathVariable Long id,
			@RequestBody RecordDTO recordDto) {
		
		if(!id.equals(recordDto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Record persisted = recordService.save(toRecord.convert(recordDto));
		
		return new ResponseEntity<>(toRecordDto.convert(persisted), HttpStatus.OK);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
