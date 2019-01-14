package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_record")
public class Record {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(nullable=false, name="time")
	private String time;
	
	@Column(nullable=false, name="duration")
	private int duration;
	
	@Column(name="intensity")
	private String intensity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Activity activity;
	
	public Record() {
		super();
	}

	public Record(Long id, String time, int duration, String intensity) {
		super();
		this.id = id;
		this.time = time;
		this.duration = duration;
		this.intensity = intensity;
	}

	public Record(String time, int duration, String intensity, User user, Activity activity) {
		super();
		this.time = time;
		this.duration = duration;
		this.intensity = intensity;
		this.user = user;
		this.activity = activity;
	}

	public Record(Long id, String time, int duration, String intensity, User user, Activity activity) {
		super();
		this.id = id;
		this.time = time;
		this.duration = duration;
		this.intensity = intensity;
		this.user = user;
		this.activity = activity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if(!user.getRecords().contains(this)) {
			user.getRecords().add(this);
		}
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
		if(!activity.getRecords().contains(this)) {
			activity.getRecords().add(this);
		}
	}
	
}
