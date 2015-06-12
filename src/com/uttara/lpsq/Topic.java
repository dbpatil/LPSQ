package com.uttara.lpsq;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.json.simple.JSONObject;

@Entity(name = "TOPIC")
@Table(name = "TOPICS")
public class Topic implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SL_NO")
	private int id;

	@Column(name = "TOPIC", unique = true)
	@NotEmpty(message = "Enter Topic")
	private String topicName;

	@Column(name = "DESCRIPTION")
	@NotEmpty(message = "Enter Description")
	private String description;

	@Column(name = "user_sl_no")
	private String user_sl_no;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getUser_sl_no() {
		return user_sl_no;
	}

	public void setUser_sl_no(String user_sl_no) {
		this.user_sl_no = user_sl_no;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((topicName == null) ? 0 : topicName.hashCode());
		result = prime * result
				+ ((user_sl_no == null) ? 0 : user_sl_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (topicName == null) {
			if (other.topicName != null)
				return false;
		} else if (!topicName.equals(other.topicName))
			return false;
		if (user_sl_no == null) {
			if (other.user_sl_no != null)
				return false;
		} else if (!user_sl_no.equals(other.user_sl_no))
			return false;
		return true;
	}
	
	
	public JSONObject getJson() 
	{
		JSONObject object= new JSONObject();
		object.put("topicName", this.topicName);
		object.put("topicDesc", this.description);
		object.put("userSlNo", this.user_sl_no);
		return object;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", topicName=" + topicName
				+ ", description=" + description + ", user_sl_no=" + user_sl_no
				+ "]";
	}

}
