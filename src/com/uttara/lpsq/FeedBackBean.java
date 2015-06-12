package com.uttara.lpsq;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.simple.JSONObject;

@Entity(name="FEEDBACK")
@Table(name="FEEDBACKS")
public class FeedBackBean 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private int sl_no;
	
	@Column(name="FEEDBACK")
	private String feedback;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="RESPONSE_SL_NO")
	private ResponseBean bean;

	@Transient
	private String resp_sl_no;
	
	
	
	public String getResp_sl_no() {
		return resp_sl_no;
	}

	public void setResp_sl_no(String resp_sl_no) {
		this.resp_sl_no = resp_sl_no;
	}

	@Override
	public String toString() {
		return "FeedBackBean [sl_no=" + sl_no + ", feedback=" + feedback
				+ ", bean=" + bean + "]";
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public ResponseBean getBean() {
		return bean;
	}

	public void setBean(ResponseBean bean) {
		this.bean = bean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bean == null) ? 0 : bean.hashCode());
		result = prime * result
				+ ((feedback == null) ? 0 : feedback.hashCode());
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
		FeedBackBean other = (FeedBackBean) obj;
		if (bean == null) {
			if (other.bean != null)
				return false;
		} else if (!bean.equals(other.bean))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		return true;
	}
	
	public JSONObject getJson() 
	{
		JSONObject object= new JSONObject();
		object.put("sl_no", this.sl_no+"");
		object.put("feedback", this.feedback);
		object.put("respSlNo", this.bean.getSl_no()+"");
		return object;
	}
	
}
