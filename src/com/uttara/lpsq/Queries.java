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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Entity(name="Query")
@Table(name="Queries")
public class Queries 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Q_SL_NO")
	private int sl_no;
	
	@Column(name="QUERY")
	private String query;
	
	private String queryEmail;	
	
	public String getQueryEmail() {
		return queryEmail;
	}

	public void setQueryEmail(String queryEmail) {
		this.queryEmail = queryEmail;
	}

	@Column(name="Query_Date")
	private String queryDate;
	
	@Column(name="QueryByName")
	private String queryByName;
	
	@Column(name="QueryBySlNo")
	@Transient
	private String queryBySlNo;
	
	@Column(name="QueryLockedStatus")
	private boolean queryLockedStatus;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.DETACH)
	@JoinColumn(name="RESPONDER_QUERIES_SL_NO")
	private ResponderBean responder;	
	
	@Column(name="TOPIC")
	private String topic;
	
		
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="STUDENT_SL_NO")
	private StudentBean studentBean ;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name = "Query_SL_NO")
	private List<ResponseBean> responses= new ArrayList<ResponseBean>();
	
	
	
	public JSONObject getJson() 
	{
		JSONObject object= new JSONObject();
		object.put("queryslno", this.sl_no+"");
		object.put("query", this.query);
		return object;
	}
	
	public JSONObject getJsonForQuery() {
		JSONArray array= new JSONArray();
		JSONObject object= new JSONObject();
		object.put("sl_no", this.sl_no+"");
		object.put("query", this.query);
		object.put("queryByName", this.queryByName);
		object.put("queryBySlNo", this.studentBean.getId()+"");
		object.put("queryDate", this.queryDate);
		for(ResponseBean bean:responses)
		{
			array.add(bean.getJsonObject());
		}
		object.put("responses", array);
		return object;
	}
	
	public int getSl_no() {
		return sl_no;
	}

	public void setSl_no(int sl_no) {
		this.sl_no = sl_no;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getQueryByName() {
		return queryByName;
	}

	public void setQueryByName(String queryByName) {
		this.queryByName = queryByName;
	}

	public String getQueryBySlNo() {
		return queryBySlNo;
	}

	public void setQueryBySlNo(String queryBySlNo) {
		this.queryBySlNo = queryBySlNo;
	}

	public boolean isQueryLockedStatus() {
		return queryLockedStatus;
	}

	public void setQueryLockedStatus(boolean queryLockedStatus) {
		this.queryLockedStatus = queryLockedStatus;
	}

	public ResponderBean getResponder() {
		return responder;
	}

	public void setResponder(ResponderBean responder) {
		this.responder = responder;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}

	public List<ResponseBean> getResponses() {
		return responses;
	}

	public void setResponses(List<ResponseBean> responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "Queries [sl_no=" + sl_no + ", query=" + query + ", queryDate="
				+ queryDate + ", queryByName=" + queryByName + ", queryBySlNo="
				+ queryBySlNo + ", queryLockedStatus=" + queryLockedStatus
				+ ", responses=" + responses + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result
				+ ((queryByName == null) ? 0 : queryByName.hashCode());
		result = prime * result
				+ ((queryBySlNo == null) ? 0 : queryBySlNo.hashCode());
		result = prime * result
				+ ((queryDate == null) ? 0 : queryDate.hashCode());
		result = prime * result + (queryLockedStatus ? 1231 : 1237);
		result = prime * result
				+ ((responses == null) ? 0 : responses.hashCode());
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
		Queries other = (Queries) obj;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (queryByName == null) {
			if (other.queryByName != null)
				return false;
		} else if (!queryByName.equals(other.queryByName))
			return false;
		if (queryBySlNo == null) {
			if (other.queryBySlNo != null)
				return false;
		} else if (!queryBySlNo.equals(other.queryBySlNo))
			return false;
		if (queryDate == null) {
			if (other.queryDate != null)
				return false;
		} else if (!queryDate.equals(other.queryDate))
			return false;
		if (queryLockedStatus != other.queryLockedStatus)
			return false;
		if (responses == null) {
			if (other.responses != null)
				return false;
		} else if (!responses.equals(other.responses))
			return false;
		return true;
	}
}
