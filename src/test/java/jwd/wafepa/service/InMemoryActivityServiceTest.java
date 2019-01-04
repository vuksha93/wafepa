package jwd.wafepa.service;

import java.util.ArrayList;
import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryActivityServiceTest {
	private ActivityService activityService;
	
	@Before
	public void setUp(){
		activityService = new InMemoryActivityService();
		List<Activity> activities = new ArrayList<>();
		
		Activity swimming = new Activity("Swimming");
		Activity running = new Activity("Running");
		Activity swimming2 = new Activity("Swimming");
		
		activities.add(swimming);
		activities.add(running);
		activities.add(swimming2);
		
		activityService.save(activities); 
	}
	
	@Test
	public void testFindOne(){
		Activity activity = activityService.findOne(1L);
		Assert.assertNotNull(activity);
		Assert.assertEquals(1L, activity.getId().longValue());
		Assert.assertEquals("Swimming", activity.getName());
	}
	
	@Test
	public void testFindNone() {
		Activity activity = activityService.findOne(100l);
		Assert.assertNull(activity);
	}
	
	@Test
	public void testFindAll(){
		List<Activity> activities = activityService.findAll();
		Assert.assertNotNull(activities);
		Assert.assertEquals(3, activities.size());
	}
	
	@Test
	public void testDeleteActivity() {
		Activity activity = activityService.delete(1L);
		Assert.assertEquals(1L, activity.getId().longValue());
		Assert.assertEquals("Swimming", activity.getName());
		List<Activity> activities = activityService.findAll();
		Assert.assertEquals(2, activities.size());
	}
	
	@Test
	public void testDeleteActivityException() {
		Activity activity = activityService.delete(100l);
		Assert.assertNull(activity);
	}
	
	@Test
	public void testFindByName() {
		List<Activity> activities = activityService.findByName("Swimming");
		Assert.assertEquals(2, activities.size());
		Assert.assertNotSame(activities.get(0), activities.get(1));
		Assert.assertNotEquals(activities.get(0).getId(), activities.get(1).getId());
	}
	
	@Test
	public void testDeleteList() {
		
	}
	
	@Test
	public void testSaveActivityAdd() {
		Activity dancing = new Activity("Dancing");
		activityService.save(dancing);
		List<Activity> activities = activityService.findAll();
		Assert.assertEquals("Dancing", activityService.findByName("Dancing").get(0).getName());
		Assert.assertEquals(4, activities.size());
	}
	
	@Test
	public void testSaveActivityUpdate() {
		List<Activity> oldActivities = activityService.findAll();
		Activity activity = activityService.findOne(2l);
		Assert.assertEquals("Running", activity.getName());
		activity.setName("Dancing");
		activityService.save(activity);
		List<Activity> newActivities = activityService.findAll();
		Assert.assertEquals(oldActivities.size(), newActivities.size());
		Activity newActivity = activityService.findOne(2l);
		Assert.assertEquals("Dancing", newActivity.getName());
	}
	
	@Test
	public void testSaveListAdd() {
		List<Activity> activities = new ArrayList<>();
		Activity oldActivity = activityService.findOne(1l);
		Assert.assertEquals("Swimming", oldActivity.getName());
		oldActivity.setName("Jumping");
		activities.add(oldActivity);
		Activity jumping = new Activity("Jumping");
		activities.add(jumping);
		activityService.save(activities);
		List<Activity> allActivities = activityService.findAll();
		Assert.assertEquals(4, allActivities.size());
	}
	
}
