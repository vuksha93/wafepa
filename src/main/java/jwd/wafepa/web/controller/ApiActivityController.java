package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.support.ActivityDTOToActivity;
import jwd.wafepa.support.ActivityToActivityDTO;
import jwd.wafepa.web.dto.ActivityDTO;


@RestController
@RequestMapping("api/activities")
public class ApiActivityController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ActivityToActivityDTO toDto;
	
	@Autowired
	private ActivityDTOToActivity toActivity;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ActivityDTO>> getActivities(
			@RequestParam(required=false) String name) {
		
		List<Activity> activities;
		
		if(name == null) {
			activities = activityService.findAll();
		} else {
			activities = activityService.findByName(name);
		}
		
		if(activities == null || activities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>
			(toDto.convert(activities), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<List<ActivityDTO>> changeActivities
		(@RequestBody List<ActivityDTO> dtoActivities) {
		deleteActivities();
		
		List<Activity> retVal = toActivity.convert(dtoActivities);
		List<Activity> newActivities = activityService.save(retVal);
		
		return new ResponseEntity<>(toDto.convert(newActivities), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ActivityDTO> addActivity(@RequestBody ActivityDTO dto) {
		
		Activity converted = toActivity.convert(dto);
		Activity saved = activityService.save(converted);
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteActivities() {
		
		List<Activity> allActivities = activityService.findAll();
		
		for(Activity a : allActivities) {
			activityService.delete(a.getId());
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<ActivityDTO> getActivity(@PathVariable Long id) {
		Activity activity = activityService.findOne(id);
		
		if(activity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(activity), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<ActivityDTO> changeActivity
		(@PathVariable Long id, @RequestBody ActivityDTO dto) {
		
		if(!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Activity converted = toActivity.convert(dto);
		Activity edited = activityService.save(converted);
		
		return new ResponseEntity<>(toDto.convert(edited), HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<ActivityDTO> deleteActivity(@PathVariable Long id) {
		Activity deleted = activityService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
	}
	
}
