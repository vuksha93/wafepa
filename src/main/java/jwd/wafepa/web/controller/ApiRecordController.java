package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Record;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.support.RecordToRecordDTO;
import jwd.wafepa.web.dto.RecordDTO;

@RestController
@RequestMapping("api/records")
public class ApiRecordController {
	
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private RecordToRecordDTO toRecordDto;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<RecordDTO>> getRecords(
			@RequestParam(value="page", defaultValue="0") int page) {
		
		Page<Record> recordsPages = recordService.findAll(page);
		
		if(recordsPages == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toRecordDto.convert(recordsPages.getContent()),
				HttpStatus.OK);
	}
	
	//TODO findOne, addRecord, editRecord, deleteRecord
	
}
