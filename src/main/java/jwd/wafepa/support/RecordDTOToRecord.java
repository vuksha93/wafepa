package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Record;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.RecordDTO;

@Component
public class RecordDTOToRecord 
	implements Converter<RecordDTO, Record>{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private RecordService recordService;

	@Override
	public Record convert(RecordDTO dtoRecord) {
		
		User user = userService.findOne(dtoRecord.getUserId());
		Activity activity = activityService.findOne(dtoRecord.getActivityId());
		
		if(user != null && activity != null) {
			Record record = null;
			
			if(dtoRecord.getId() != null) {
				record = recordService.findOne(dtoRecord.getId());
			}
			else {
				record = new Record();
			}
			
			record.setId(dtoRecord.getId());
			record.setTime(dtoRecord.getTime());
			record.setDuration(dtoRecord.getDuration());
			record.setIntensity(dtoRecord.getIntensity());
			record.setUser(user);
			record.setActivity(activity);
			
			return record;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities!");
		}
	
	}
	
	public List<Record> convert(List<RecordDTO> dtoRecords) {
		
		List<Record> records = new ArrayList<Record>();
		
		for(RecordDTO dtoRecord : dtoRecords) {
			records.add(convert(dtoRecord));
		}
		
		return records;
	}

}
