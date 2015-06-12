package com.uttara.lpsq;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component("DAO")
public class DAOImpl {

	private SessionFactory sessionFactory;
	private Session session;

	private String LOG = "DAOImpl : ";

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public DAOImpl() {
		System.out.println("inside of no-arg construtor of DAOImpl");
	}

	public List<String> getAllTopics() {
		System.out.println(LOG + "getAllTopics");
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM TOPIC");
		List<Topic> topics = query.list();
		List<String> list = new ArrayList<String>();
		for (Topic topic : topics)
			list.add(topic.getTopicName());
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public String insertUser(RegisterBean register, String role) {
		System.out.println(getCurrentUserDetails(register.getEmail()));
		System.out.println(LOG + "insertUser");
		List<Role> roles = getALlRoles();
		System.out.println(roles);

		UserDetails details = new UserDetails();
		details.setBean(register);
		details.setEmail(register.getEmail());
		details.setActivationStatus(false);
		details.setPass(register.getPass());
		for (Role r2 : roles) {
			if (r2.getRole().equals(role))
				details.setRole(r2.getId());
		}

		if (getCurrentUserDetails(register.getEmail()) == null) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.persist(details);
			session.getTransaction().commit();
			session.close();
			return Constants.SUCCESS;
		} else
			return "User Already Exist";

	}

	public UserDetails getCurrentUserDetails(String email) {
		System.out.println(LOG + "getCurrentUserDetails : "+email);
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM USERDETAILS WHERE EMAIL=?");
		query.setString(0, email);
		List<UserDetails> beans = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Size is " + beans);
		if (beans.size() == 0)
			return null;
		else
			return beans.get(0);
		
	}

	public List<Role> getALlRoles() {
		System.out.println(LOG + "getALlRoles ");
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM ROLE");
		List<Role> beans = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Size is " + beans);
		return beans;
	}

	public String verifyUserInDB(UserDetails bean) {
		System.out.println(LOG + "verifyUserInDB "+bean);
		String message = null;
		UserDetails details=getCurrentUserDetails(bean.getEmail());
		System.out.println("Present user is "+details);
		if(details==null)
		{
			message= "Invalid Email Id";
		}else if(details.getPass().equals(bean.getPass()))
		{
			List<Role> roles=getALlRoles();
			one:for(Role role:roles)
			{
				if(role.getId()==details.getRole())
				{
					message= role.getRole();
					details.getBean();
					break one;
				}
			}
		}else
			message= "Invalid Password";
		return message;
	}

	public List<ResponderBean> getAllResponders(String email) 
	{
		System.out.println(LOG + "getAllResponderBeans "+email);
		UserDetails details=getCurrentUserDetails(email);
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM RESPONDER WHERE user_sl_no=?");
		query.setInteger(0, details.getId());
		List<ResponderBean> beans = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Size is " + beans);
		return beans;
	}

	public String insertResponder(ResponderBean bean,String email) {
		System.out.println(LOG + "insertResponder "+bean+", Email : "+email);
		UserDetails details=getCurrentUserDetails(email);
		UserDetails details2= new UserDetails();
		details2.setResp_bean(bean);
		details2.setEmail(bean.getEmail());
		details2.setPass(bean.getPass());
		List<Role> roles=getALlRoles();
		for(Role role:roles)
		{
			if(role.getRole().equals("responder"))
				details2.setRole(role.getId());
		}		
		bean.setUser_sl_no(details.getId());
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(bean);	
		session.save(details2);
			
		session.getTransaction().commit();
		session.close();			
		return Constants.SUCCESS;
	}

	public List<Topic> getAllTopics(String email) {
		System.out.println(LOG + "getAllTopics  Email : "+email);
		UserDetails details=getCurrentUserDetails(email);		
		session = sessionFactory.openSession();
		session.beginTransaction();		
		Query query = session.createQuery("FROM TOPIC WHERE user_sl_no =?");
		query.setString(0, details.getId()+"");
		List<Topic> beans = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Size is " + beans);
		return beans;
	}

	public String addTopics(Topic bean, String email) {
		System.out.println(LOG + "addTopics  Email : "+email+", Topic is "+bean);
		UserDetails details=getCurrentUserDetails(email);	
		bean.setUser_sl_no(details.getId()+"");
		session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(bean);
		session.getTransaction().commit();
		session.close();		
		return Constants.SUCCESS;
	}

	public ResponderBean getCurrentResponderData(String resp_sl_no, String email) {
		System.out.println(LOG + "getCurrentResponderData  Email : "+email+", RESP_SL_NO  is "+resp_sl_no);
		session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("FROM RESPONDER WHERE id=?");
		query.setInteger(0, Integer.parseInt(resp_sl_no));
		ResponderBean bean=(ResponderBean) query.list().get(0);
		session.getTransaction().commit();
		session.close();
		return bean;		
}

	public String updateResponderDetails(ResponderBean bean,String email) {
		System.out.println(LOG + "updateResponderDetails  "+bean);
		UserDetails details=getCurrentUserDetails(email);	
		bean.setUser_sl_no(details.getId());
		session =sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(bean);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}

	public String deleteResponder(String resp_sl_no) {
		System.out.println(LOG + "deleteResponder  "+resp_sl_no);
		session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("FROM RESPONDER WHERE id=?");
		query.setInteger(0, Integer.parseInt(resp_sl_no));
		ResponderBean bean=(ResponderBean) query.list().get(0);
		UserDetails details=(UserDetails) session.createQuery("FROM USERDETAILS WHERE EMAIL=?").setString(0, bean.getEmail()).list().get(0);
		session.delete(bean);
		session.delete(details);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}

	public Topic getTopicFromSlNO(String topic_sl_no) {
		System.out.println(LOG + "getTopicFromSlNO  "+topic_sl_no);
		session=sessionFactory.openSession();
		session.beginTransaction();
		Topic topic=(Topic) session.createQuery("FROM TOPIC WHERE SL_NO=?").setInteger(0, Integer.parseInt(topic_sl_no)).list().get(0);
		session.getTransaction().commit();
		session.close();
		topic.setId(Integer.parseInt(topic_sl_no));
		return topic;
	}

	public String updateTopic(Topic bean,String email) {
		System.out.println(LOG+"updateTopic  : BEAN  is "+bean+", Email is "+email);
		UserDetails details=getCurrentUserDetails(email);
		bean.setUser_sl_no(details.getId()+"");
		session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(bean);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}

	public String deleteTopic(String topic_sl_no) {
		System.out.println(LOG+"deleteTopic  : SL NO  is "+topic_sl_no);
		session=sessionFactory.openSession();
		session.beginTransaction();
		Topic topic=(Topic) session.createQuery("FROM TOPIC WHERE SL_NO=?").setInteger(0, Integer.parseInt(topic_sl_no)).list().get(0);
		session.delete(topic);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}

	public String registerStudent(StudentBean bean) {
		System.out.println(LOG+"registerStudent  : bean is "+bean);
		UserDetails details=getCurrentUserDetails(bean.getEmail());
		UserDetails details2=new UserDetails();
		details2.setEmail(bean.getEmail());
		details2.setPass(bean.getPass());
		
		List<Role> roles=getALlRoles();
		for(Role role:roles)
		{
			if(role.getRole().equals("student"))
				details2.setRole(role.getId());
		}
		details2.setStudentBean(bean);
		if(details==null)
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			session.persist(details2);
			session.getTransaction().commit();
			session.close();
			return Constants.SUCCESS;
		}else
			return "Email ID is Already Registered";		
	}

	public String verifyUserInDBMobileUser(UserDetails bean) {
		System.out.println(LOG+"verifyUserInDBMobileUser  : bean is "+bean);
		UserDetails details=getCurrentUserDetails(bean.getEmail());
		if(details==null)
			return "Invalid Email ID ";
		else
		{
			if(details.getPass().equals(bean.getPass()))
			{
				int role_id=details.getRole();
				List<Role> roles=getALlRoles();
				for(Role role:roles)
				{
					if(role.getId()==role_id)
					{
						if(role.getRole().equals("student"))
						{							
							return role.getRole();
						}else if(role.getRole().equals("responder"))
						{
							/*if(details.getResp_bean().getIsActive().equals(""))*/
							return role.getRole();
						}								
					}
				}
			}else
				return "Invalid Password";			
		}
		return null;
	}
	
	
	

	public List<Queries> getAllQueriesOfStudent(String email) {
		System.out.println(LOG+"getAllQueriesOfStudent  : email is "+email);
		UserDetails details=getCurrentUserDetails(email);
		if(details==null)
		{
			return null;
		}else
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			List<Queries> list=session.createQuery("FROM Query WHERE queryEmail=?").setString(0, email).list();
			/*System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@ \n"+list);*/
			for(Queries queries:list)
			{
				List<ResponseBean> beans=session.createQuery("FROM RESPONSE WHERE querySlNo=?").setString(0, queries.getSl_no()+"").list();
				for(ResponseBean bean:beans)
				{
					List<FeedBackBean> feedBackBeans=session.createQuery("FROM FEEDBACK where RESPONSE_SL_NO=?").setInteger(0, bean.getSl_no()).list();
					bean.setFeedBackBeans(feedBackBeans);					
				}				
				queries.setResponses(beans);
			}
			session.getTransaction().commit();
			session.close();
			return list;
		}		
	}

	public String addQuery(Queries queries) {
		System.out.println(LOG+"addQuery : Query "+queries);
		UserDetails details=getCurrentUserDetails(queries.getQueryEmail());
		StudentBean bean=details.getStudentBean();
		System.out.println("Student bean is "+bean);
		queries.setQueryByName(bean.getName());
		queries.setQueryBySlNo(bean.getId()+"");
		queries.setStudentBean(bean);
		bean.getQueries().add(queries);		
		session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(details);
		session.saveOrUpdate(bean);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}

	public String addResponse(ResponseBean bean) {
		System.out.println(LOG+"addResponse : RESPONSE BEAN IS "+bean);
		UserDetails details=getCurrentUserDetails(bean.getRespEmail());
		ResponderBean responderBean=details.getResp_bean();
		bean.setResponderName(responderBean.getFname()+" "+responderBean.getLname());
		bean.setResponderSlNo(responderBean.getId()+"");
		session=sessionFactory.openSession();
		session.beginTransaction();
		Queries queries=(Queries) session.createQuery("FROM Query WHERE Q_SL_NO=?").setInteger(0, Integer.parseInt(bean.getQuerySlNo())).list().get(0);
		StudentBean studentBean=queries.getStudentBean();
		List<Queries> queries2=studentBean.getQueries();
		
		int qslNo=Integer.parseInt(bean.getQuerySlNo());
		one:for(Queries queries3:queries2)
		{
			if(queries3.getSl_no()==qslNo)
			{
				queries3.getResponses().add(bean);
				queries3.setStudentBean(studentBean);
				queries3.setResponder(responderBean);
				queries2.add(queries3);
				break one;
			}
		}		
		session.update(queries);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}

	public String addLikeForResponse(String resp_sl_no) {
		System.out.println(LOG+"addLikeForResponse");
		System.out.println("RESP SL NO "+resp_sl_no);
		session=sessionFactory.openSession();
		session.beginTransaction();
		ResponseBean bean=(ResponseBean) session.createQuery("FROM RESPONSE WHERE sl_no=?").setInteger(0, Integer.parseInt(resp_sl_no)).list().get(0);
		bean.setResponseLikes((Integer.parseInt(bean.getResponseLikes())+1)+"");
		session.update(bean);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}
	
	public String addDisLikeForResponse(String resp_sl_no) {
		System.out.println(LOG+"addDisLikeForResponse");
		System.out.println("RESP SL NO "+resp_sl_no);
		session=sessionFactory.openSession();
		session.beginTransaction();
		ResponseBean bean=(ResponseBean) session.createQuery("FROM RESPONSE WHERE sl_no=?").setInteger(0, Integer.parseInt(resp_sl_no)).list().get(0);
		bean.setResponseDisLikes((Integer.parseInt(bean.getResponseDisLikes())+1)+"");
		session.update(bean);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}

	public String addFeedback(FeedBackBean feBackBean) {
		System.out.println(LOG+"addFeedback");
		System.out.println("Feedback SL NO "+feBackBean);
		session=sessionFactory.openSession();
		session.beginTransaction();
		ResponseBean bean=(ResponseBean) session.createQuery("FROM RESPONSE WHERE sl_no=?").setInteger(0, Integer.parseInt(feBackBean.getResp_sl_no())).list().get(0);
		feBackBean.setBean(bean);
		bean.getFeedBackBeans().add(feBackBean);
		session.update(bean);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}

	public String getAllQueries(String email) {
		String data="";
		System.out.println(LOG+"getAllQueries");
		System.out.println("Email is "+email);
		session=sessionFactory.openSession();
		session.beginTransaction();
		StudentBean studentBean=(StudentBean) session.createQuery("FROM STUDENT WHERE EMAIL=?").setString(0, email).list().get(0);
		
		return null;
	}

	public List<RegisterBean> getAllInstitutes() {
		System.out.println(LOG+"getAllInstitutes");
		session=sessionFactory.openSession();
		session.beginTransaction();
		List<RegisterBean>beans=session.createQuery("FROM REGISTERS").list();
		session.getTransaction().commit();
		session.close();
		if(beans.size()>0)
			return beans;
		else
			return null;			
	}

	public List<Topic> getAllTopicOfInstitute(String email) {
		System.out.println(LOG+"getAllTopicOfInstitute");
		System.out.println("Email is "+email);
		session=sessionFactory.openSession();
		session.beginTransaction();
		StudentBean bean=(StudentBean) session.createQuery("FROM STUDENT WHERE email=?").setString(0, email).list().get(0);
		UserDetails details=(UserDetails) session.createQuery("FROM USERDETAILS WHERE REG_SL_NO=?").setInteger(0, Integer.parseInt(bean.getIntrested())).list().get(0);
		List<Topic>topics=session.createQuery("FROM TOPIC WHERE user_sl_no=?").setString(0, details.getBean().getId()+"").list();
		System.out.println("Fetched List is "+topics);
		session.getTransaction().commit();
		session.close();
		return topics;
	}

	List<Queries> queries=null;
	List<Integer> integers=null;
	public List<Queries> getAllQueriesForResponderOfResponsed(String email) {
		queries=new ArrayList<Queries>();
		integers=new ArrayList<Integer>();		
		System.out.println(LOG+"getAllQueriesForResponder");
		System.out.println("Email is "+email);
		session=sessionFactory.openSession();
		session.beginTransaction();
		List<ResponseBean> responseBeans=session.createQuery("FROM RESPONSE WHERE respEmail=?").setString(0, email).list();
		for(ResponseBean bean:responseBeans)
		{
			Queries query=	(Queries) session.createQuery("FROM Query WHERE sl_no=?").setInteger(0, Integer.parseInt(bean.getQuerySlNo())).list().get(0);
			if(integers.contains(query.getSl_no())==false)
			{
				List<ResponseBean> a=session.createQuery("FROM RESPONSE WHERE respEmail=? and querySlNo=?").setString(0, email).setString(1, query.getSl_no()+"").list();
				for(ResponseBean b:a)
				{
					List<FeedBackBean> feedBackBeans=session.createQuery("FROM FEEDBACK where RESPONSE_SL_NO=?").setInteger(0, b.getSl_no()).list();		
					b.setFeedBackBeans(feedBackBeans);
				}
				query.setResponses(a);				
				integers.add(query.getSl_no());
			}
			queries.add(query);
		}
		return queries;
	}

	List<Queries> topicBasedQueries=null;
	public List<Queries> getAllQueriesForResponderOfTopic(String email) {
		System.out.println(LOG+"getAllQueriesForResponderOfTopic");
		System.out.println("Email is "+email);
		topicBasedQueries=new ArrayList<Queries>();
		session=sessionFactory.openSession();
		session.beginTransaction();
		ResponderBean bean=(ResponderBean) session.createQuery("FROM RESPONDER WHERE email=?").setString(0, email).list().get(0);
		String topic=bean.getTopic();
		UserDetails details=(UserDetails) session.createQuery("FROM USERDETAILS WHERE id=?").setInteger(0, bean.getUser_sl_no()).list().get(0);
		
		String institute_sl_no=details.getBean().getId()+"";
		System.out.println("Institute Sl No : "+institute_sl_no);
		List<StudentBean> studentBeans=session.createQuery("FROM STUDENT WHERE intrested=?").setString(0, institute_sl_no).list();
		System.out.println("List of Students : \n"+studentBeans);
		for(StudentBean bean2:studentBeans)
		{
			List<Queries> list=session.createQuery("FROM Query WHERE queryEmail=? and topic=?").setString(0, bean2.getEmail()).setString(1, topic).list();
			
			for(Queries queries:list)
			{
				List<ResponseBean> beans=session.createQuery("FROM RESPONSE WHERE querySlNo=?").setString(0, queries.getSl_no()+"").list();
				for(ResponseBean a:beans)
				{
					List<FeedBackBean> feedBackBeans=session.createQuery("FROM FEEDBACK where RESPONSE_SL_NO=?").setInteger(0, a.getSl_no()).list();
					a.setFeedBackBeans(feedBackBeans);					
				}				
				queries.setResponses(beans);
				topicBasedQueries.add(queries);
			}
		}
		session.getTransaction().commit();
		session.close();
		return topicBasedQueries;
	}

	public List<String> getEmailAddresses(String recipientAddress,
			String emailId) {
		List<String> emailIdStrings=new ArrayList<String>();
		System.out.println(LOG+"getEmailAddresses");
		System.out.println("Email id is "+emailId);
		System.out.println("Send to is "+recipientAddress);
		UserDetails details=getCurrentUserDetails(emailId);
		if(recipientAddress.contains("Responders"))
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			List<ResponderBean> responderBeans=session.createQuery("FROM RESPONDER WHERE USER_SL_NO=?").setInteger(0, details.getId()).list();
			for(ResponderBean bean:responderBeans)
				emailIdStrings.add(bean.getEmail());
			session.getTransaction().commit();
			session.close();
			return emailIdStrings;
			
		}else if(recipientAddress.contains("Students"))
		{
			session=sessionFactory.openSession();
			session.beginTransaction();
			List<StudentBean> studentBeans=session.createQuery("FROM STUDENT WHERE intrested=?").setString(0, details.getBean().getId()+"").list();
			for(StudentBean bean:studentBeans)
				emailIdStrings.add(bean.getEmail());
			session.getTransaction().commit();
			session.close();
			return emailIdStrings;
		}
		return null;
	}

	public List<UserDetails> getAllRegisteredSolutionAdmins() {
		System.out.println(LOG+"getAllRegisteredSolutionAdmins");
		List<Role> roles=getALlRoles();
		int role_sl_no=99;
		
		for(Role role:roles)
		{
			if(role.getRole().equals("solution_admin"))
				role_sl_no=role.getId();
		}
		session=sessionFactory.openSession();
		session.beginTransaction();		
		List<UserDetails> userDetails=session.createQuery("FROM USERDETAILS WHERE role=?").setInteger(0, role_sl_no).list();
		session.getTransaction().commit();
		session.close();
		return userDetails;
	}

	public String makeStatusChange(String sl_no) {
		System.out.println(LOG+"makeStatusChange");
		System.out.println("SL No is "+sl_no);
		session=sessionFactory.openSession();
		session.beginTransaction();
		UserDetails details=(UserDetails) session.createQuery("FROM USERDETAILS WHERE id=?").setInteger(0, Integer.parseInt(sl_no)).list().get(0);
		if(details.isActivationStatus())
			details.setActivationStatus(false);
		else
			details.setActivationStatus(true);
		session.saveOrUpdate(details);
		session.getTransaction().commit();
		session.close();
		return Constants.SUCCESS;
	}	
	
	
	
}
