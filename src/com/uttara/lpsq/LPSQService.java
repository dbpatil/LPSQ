package com.uttara.lpsq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;


@Component("service")
public class LPSQService {
	
	@Autowired
	@Qualifier("DAO")
	public DAOImpl daoImpl;
	
	private String LOG="LPSQService : ";
	
	

	public DAOImpl getDaoImpl() {
		return daoImpl;
	}

	public void setDaoImpl(DAOImpl daoImpl) {
		this.daoImpl = daoImpl;
	}
	
	public LPSQService() {
		System.out.println("inside of no-arg construtor of Service");
	}

	public List<String> getTopics() {
		System.out.println(LOG+"getTopics");
		return daoImpl.getAllTopics();
	}
	
	
	public String registerUser(RegisterBean register,String role) {
		System.out.println(LOG+"registerUser");
		return daoImpl.insertUser(register,role);		
	}

	public String verifyUser(UserDetails bean) {
		System.out.println(LOG+"verifyUser");
		String data= daoImpl.verifyUserInDB(bean);
		System.out.println(LOG+"verifyUser : Result = "+data);
		return data;
	}

	public List<ResponderBean> getAllResponders(String email) {
		System.out.println(LOG+"getAllResponders");
		List<ResponderBean> responders=daoImpl.getAllResponders(email);
		return responders;
	}

	public String addResponder(ResponderBean bean,String email) {
		System.out.println(LOG+"addResponder");
		String res=daoImpl.insertResponder(bean,email);
		return res;
	}

	public List<Topic> getAllTopic(String email) {
		System.out.println(LOG+"getAllTopic");
		List<Topic> topics=daoImpl.getAllTopics(email);
		return topics;
	}

	public String addTopic(Topic bean, String email) {
		System.out.println(LOG+"addTopic");		
		return daoImpl.addTopics(bean,email);
	}

	public ResponderBean getResponderDetails(String resp_sl_no,String email) {
		System.out.println(LOG+"getResponderDetails ");
		System.out.println("RESP SL NO IS "+resp_sl_no+", EMAIL IS "+email);
		return daoImpl.getCurrentResponderData(resp_sl_no,email);
}

	public String updateResponderDetails(ResponderBean bean,String email) {
		System.out.println(LOG+"updateResponderDetails ");
		System.out.println("Bean is "+bean+" Email : "+email);
		return daoImpl.updateResponderDetails(bean, email);
	}

	public String deleteResponder(String resp_sl_no) {
		System.out.println(LOG+"deleteResponder ");
		System.out.println("SL No is "+resp_sl_no);
		return daoImpl.deleteResponder(resp_sl_no);
	}

	public Topic getTopic(String topic_sl_no) {
		System.out.println(LOG+"deleteResponder ");
		System.out.println("SL No is "+topic_sl_no);
		return daoImpl.getTopicFromSlNO(topic_sl_no);
	}

	public String updateTopic(Topic bean,String email) {
		System.out.println(LOG+"updateTopic ");
		System.out.println("Bean is "+bean+", Email is "+email);
		return daoImpl.updateTopic(bean,email);
	}

	public String deleteTopic(String topic_sl_no) {
		System.out.println(LOG+"deleteTopic ");
		System.out.println("SL NO is "+topic_sl_no);
		return daoImpl.deleteTopic(topic_sl_no);
	}

	public String registerStudent(StudentBean bean) {
		System.out.println(LOG+"registerStudent ");
		System.out.println("Bean is "+bean);		
		return daoImpl.registerStudent(bean);
	}

	public String authenticateMobileUser(UserDetails details) {
		System.out.println(LOG+"authenticateMobileUser ");
		System.out.println("Bean is "+details);
		return daoImpl.verifyUserInDBMobileUser(details);
	}

	public List<Queries> getAllQueriesForStudent(String email) {
		System.out.println(LOG+"getAllQueriesForStudent ");
		System.out.println("EMAIL is "+email);
		return daoImpl.getAllQueriesOfStudent(email);
	}

	public String addQuery(Queries queries) {
		System.out.println(LOG+"addQuery ");
		System.out.println("Query is "+queries);
		return daoImpl.addQuery(queries);
	}

	public String addResponse(ResponseBean bean) {
		System.out.println(LOG+"addResponse ");
		System.out.println("RESPONSE is "+bean);
		return daoImpl.addResponse(bean);
	}

	public String addLikeForResponse(String resp_sl_no) {
		System.out.println(LOG+"addLikeForResponse");
		System.out.println("RESPONSE SL NO is "+resp_sl_no);
		return daoImpl.addLikeForResponse(resp_sl_no);
	}
	
	public String addDisLikeForResponse(String resp_sl_no) {
		System.out.println(LOG+"addDisLikeForResponse");
		System.out.println("RESPONSE SL NO is "+resp_sl_no);
		return daoImpl.addDisLikeForResponse(resp_sl_no);
	}

	public String addFeedback(FeedBackBean feBackBean) {
		System.out.println(LOG+"addFeedback");
		System.out.println("Feedback is "+feBackBean);
		return daoImpl.addFeedback(feBackBean);
	}

	public String getAllQueriesOfStudent(String email) {
		System.out.println(LOG+"getAllQueriesOfStudent");
		System.out.println("Email is "+email);		
		return daoImpl.getAllQueries(email);
	}

	public List<RegisterBean> getAllInstitutes() {
		System.out.println(LOG+"getAllInstitutes");
		return daoImpl.getAllInstitutes();
	}

	public List<Topic> getAllTopicOfInstitute(String email) {
		System.out.println(LOG+"getAllTopicOfInstitute");
		System.out.println("Email is "+email);
		return daoImpl.getAllTopicOfInstitute(email);
	}

	public List<Queries> getAllQueriesForResponderOfResponsed(String email) {
		System.out.println(LOG+"getAllQueriesForResponder");
		System.out.println("Email is "+email);
		return daoImpl.getAllQueriesForResponderOfResponsed(email);
	}

	public List<Queries> getAllQueriesForResponderOfTopic(String email) {
		System.out.println(LOG+"getAllQueriesForResponderOfTopic");
		System.out.println("Email is "+email);
		return daoImpl.getAllQueriesForResponderOfTopic(email);
	}

	public List<String> getEmailAddresses(String recipientAddress,String emailId) {
		System.out.println(LOG+"getEmailAddresses");
		System.out.println("Send to is "+recipientAddress+", Email id is "+emailId);
		return daoImpl.getEmailAddresses(recipientAddress,emailId);
	}

	public List<UserDetails> getAllRegisteredSolutionAdmins() {
		System.out.println(LOG+"getAllRegisteredSolutionAdmins");
		return daoImpl.getAllRegisteredSolutionAdmins();
	}

	public String makeStatusChange(String sl_no) {
		System.out.println(LOG+"makeStatusChange");
		System.out.println("SL No is "+sl_no);
		return daoImpl.makeStatusChange(sl_no);
	}

	public List<Queries> getTopQueries(String email) {
		System.out.println(LOG+"getTopQueries");
		System.out.println("Email is "+email);
		return daoImpl.getTopQueries(email);
	}

	public List<StudentBean> getStudentsCount(String email) {
		System.out.println(LOG+"getStudentsCount");
		System.out.println("Email is "+email);
		return daoImpl.getStudentsCount(email);
	}
	
	public int getNoOfQueriesCount() 
	{
		System.out.println(LOG+"getNoOfQueriesCount");
		return daoImpl.getNoOfQueriesCount();
	}

}
