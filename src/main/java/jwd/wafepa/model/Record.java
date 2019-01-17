package jwd.wafepa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Record {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, name="time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	@Column(nullable=false, name="duration")
	private int duration;
	
	@Column(nullable=false, name="intensity")
	@Enumerated(EnumType.STRING)
	private Intensity intensity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Activity activity;
	
	public Record() {
		super();
	}

	public Record(Long id, Date time, int duration, Intensity intensity) {
		super();
		this.id = id;
		this.time = time;
		this.duration = duration;
		this.intensity = intensity;
	}

	public Record(Date time, int duration, Intensity intensity, User user, Activity activity) {
		super();
		this.time = time;
		this.duration = duration;
		this.intensity = intensity;
		this.user = user;
		this.activity = activity;
	}

	public Record(Long id, Date time, int duration, Intensity intensity, User user, Activity activity) {
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Intensity getIntensity() {
		return intensity;
	}

	public void setIntensity(Intensity intensity) {
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
