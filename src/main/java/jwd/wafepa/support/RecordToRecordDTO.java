package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Record;
import jwd.wafepa.web.dto.RecordDTO;

@Component
public class RecordToRecordDTO 
	implements Converter<Record, RecordDTO>{

	@Override
	public RecordDTO convert(Record record) {
		
		RecordDTO recordDto = new RecordDTO();
		
		recordDto.setId(record.getId());
		recordDto.setTime(record.getTime());
		recordDto.setDuration(record.getDuration());
		recordDto.setIntensity(record.getIntensity());
		
		recordDto.setUserId(record.getUser().getId());
		recordDto.setActivityId(record.getActivity().getId());
		
		recordDto.setUserName(record.getUser().getFirstName());
		recordDto.setActivityName(record.getActivity().getName());
		
		return recordDto;
	}

	public List<RecordDTO> convert(List<Record> records) {
		
		List<RecordDTO> dtoRecords = new ArrayList<>();
		
		for(Record record : records) {
			dtoRecords.add(convert(record));
		}
		
		return dtoRecords;
	}
	
}
