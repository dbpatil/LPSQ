package com.uttara.lpsq;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.engine.spi.CascadeStyle;
import org.hibernate.metamodel.binding.CascadeType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="USERDETAILS")
@Table(name = "USERS")
public class UserDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SL_NO")
	private int id;

	@Column(name = "EMAIL")
	@NotEmpty(message = "Enter the proper Email")
	@Email
	private String email;
	@Column(name = "PASS")
	@Length(min = 2, max = 20, message = "Password")
	private String pass;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name = "REG_SL_NO")
	private RegisterBean bean;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name = "RESP_SL_NO")
	private ResponderBean resp_bean;
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name = "STUDENT_SL_NO")
	private StudentBean studentBean;
	
	@Column(name="ActivatedStatus")
	@NotFound(action=NotFoundAction.IGNORE)
	private boolean activationStatus;	
	
	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}

	@Column(name = "ROLE_SL_NO")
	private int role;
	
	

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", email=" + email + ", pass=" + pass
				+ ", bean=" + bean + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
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
		UserDetails other = (UserDetails) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public RegisterBean getBean() {
		return bean;
	}

	public void setBean(RegisterBean bean) {
		this.bean = bean;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public ResponderBean getResp_bean() {
		return resp_bean;
	}

	public void setResp_bean(ResponderBean resp_bean) {
		this.resp_bean = resp_bean;
	}
}
