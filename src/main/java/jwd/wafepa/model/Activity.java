package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Models a physical activity.
 * 
 *
 */
@Entity
@Table(name="tbl_activity")
public class Activity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, length=60)
	private String name;
	
	@Column(name="adm_comment")
	private String adminComment = "test";
	
	@OneToOne(fetch = FetchType.EAGER)
	private Record record;
	
	
	public Activity() {
		super();
	}

	public Activity(String name) {
		super();
		this.name = name;
	}

	public Activity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 
	 * @return Activity identifier.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets activity identifier.
	 * @param id new identifier
 	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return Name of the activity.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name New name of the activity.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}
	
}
