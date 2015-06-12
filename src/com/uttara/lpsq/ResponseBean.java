package com.uttara.lpsq;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Entity(name="RESPONSE")
@Table(name="RESPONSES")
public class ResponseBean 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SL_NO")
	private int sl_no;
	
	@Column(name="RESPONSE")
	private String response;
	
	@Column(name="ResponderName")
	private String responderName;
	
	@Column(name="ResponderSlNo")	
	private String responderSlNo;
	
	@Column(name="ResponseLikes")
	private String responseLikes;
	
	@Column(name="ResponseDisLikes")
	private String responseDisLikes;
	
	@Column(name="feedback")
	private String feedback;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="RESPONSE_SL_NO")
	private List<FeedBackBean> feedBackBeans= new ArrayList<FeedBackBean>();
	
	
	public JSONObject getJsonObject() 
	{
		JSONArray array= new JSONArray();
		JSONObject object2= new JSONObject();
		object2.put("sl_no", this.sl_no+"");
		object2.put("response", this.response);
		object2.put("responderName", this.responderName);
		object2.put("responderSlNo", this.responderSlNo);
		object2.put("responseLikes", this.responseLikes);
		object2.put("responseDisLikes", this.responseDisLikes);
		
		for(FeedBackBean bean: feedBackBeans)
		{
			array.add(bean.getJson());
		}
		object2.put("feedbacks", array);
		return object2;
	}
	
	public List<FeedBackBean> getFeedBackBeans() {
		return feedBackBeans;
	}

	public void setFeedBackBeans(List<FeedBackBean> feedBackBeans) {
		this.feedBackBeans = feedBackBeans;
	}

	private String respEmail;
	
	private String querySlNo;
	
	
	
	public String getQuerySlNo() {
		return querySlNo;
	}

	public void setQuerySlNo(String querySlNo) {
		this.querySlNo = querySlNo;
	}

	public String getRespEmail() {
		return respEmail;
	}

	public void setRespEmail(String respEmail) {
		this.respEmail = respEmail;
	}

	

	@Override
	public String toString() {
		return "ResponseBean [sl_no=" + sl_no + ", response=" + response
				+ ", responderName=" + responderName + ", responderSlNo="
				+ responderSlNo + ", responseLikes=" + responseLikes
				+ ", responseDisLikes=" + responseDisLikes + ", feedback="
				+ feedback + ", respEmail=" + respEmail + ", querySlNo="
				+ querySlNo + "]";
	}

	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponderName() {
		return responderName;
	}

	public void setResponderName(String responderName) {
		this.responderName = responderName;
	}

	public String getResponderSlNo() {
		return responderSlNo;
	}

	public void setResponderSlNo(String responderSlNo) {
		this.responderSlNo = responderSlNo;
	}

	public String getResponseLikes() {
		return responseLikes;
	}

	public void setResponseLikes(String responseLikes) {
		this.responseLikes = responseLikes;
	}

	public String getResponseDisLikes() {
		return responseDisLikes;
	}

	public void setResponseDisLikes(String responseDisLikes) {
		this.responseDisLikes = responseDisLikes;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result
				+ ((responderName == null) ? 0 : responderName.hashCode());
		result = prime * result
				+ ((responderSlNo == null) ? 0 : responderSlNo.hashCode());
		result = prime * result
				+ ((response == null) ? 0 : response.hashCode());
		result = prime
				* result
				+ ((responseDisLikes == null) ? 0 : responseDisLikes.hashCode());
		result = prime * result
				+ ((responseLikes == null) ? 0 : responseLikes.hashCode());
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
		ResponseBean other = (ResponseBean) obj;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (responderName == null) {
			if (other.responderName != null)
				return false;
		} else if (!responderName.equals(other.responderName))
			return false;
		if (responderSlNo == null) {
			if (other.responderSlNo != null)
				return false;
		} else if (!responderSlNo.equals(other.responderSlNo))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		if (responseDisLikes == null) {
			if (other.responseDisLikes != null)
				return false;
		} else if (!responseDisLikes.equals(other.responseDisLikes))
			return false;
		if (responseLikes == null) {
			if (other.responseLikes != null)
				return false;
		} else if (!responseLikes.equals(other.responseLikes))
			return false;
		return true;
	}
	
	
	
	
}
