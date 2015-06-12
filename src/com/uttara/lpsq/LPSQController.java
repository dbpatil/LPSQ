package com.uttara.lpsq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/LearningPortalSolutionQuery")
public class LPSQController {

	@Autowired
	@Qualifier("service")
	public LPSQService service;
	
	@Autowired
	private JavaMailSender mailSender;
	
	JSONParser parser= new JSONParser();
	HttpSession httpSession;
	HttpServletRequest httpServletRequest;
	final String LOG="LPSQController : ";
	
	List<String> rolesRef= new ArrayList<String>();
	
	String res;
	
	
	public LPSQService getService() {
		return service;
	}

	public void setService(LPSQService service) {
		this.service = service;
	}
	
	public LPSQController() {
		System.out.println("inside of no-arg construtor of Controller");
	}

	@RequestMapping(value={"/","/welcome"})
	public String getHomePage()
	{
		String []arr= {"admin","solution_admin","responder","student"};
		for(String a:arr)			
		{
			if(rolesRef.contains(a)==false)
				rolesRef.add(a);
		}		
		System.out.println("inside of getHomePage");
		return "HomePage";
	}
	
	@RequestMapping("/openRegisterView")
	public String getRegisterPage(ModelMap map) {
		System.out.println("inside of getRegister Page");
		RegisterBean bean= new RegisterBean();
		/*List<String> trailist= service.getTopics();*/
		List<String> trailist= new ArrayList<String>();
		trailist.add("Java");
		trailist.add("J2EE");
		trailist.add("Android");
		trailist.add("Spring");		
		map.addAttribute("trailist", trailist);
		map.addAttribute("bean", bean);
		
		return "Register";
		
	}	
	
	@RequestMapping("/register")
	public String register(@ModelAttribute ("bean") @Valid RegisterBean bean, BindingResult result , ModelMap map) 
	{
		
		System.out.println("inside of Aftre Entering the inputs ");
		System.out.println("Bean is "+bean.toString());
		if(result.hasErrors())
		{
			System.out.println("inside of has Errors");
			List<String> trailist= new ArrayList<String>();
			trailist.add("Java");
			trailist.add("J2EE");
			trailist.add("Android");
			trailist.add("Spring");
			map.addAttribute("trailist", trailist);
			map.addAttribute("bean", bean);
			return "Register";
		}else
		{
			System.out.println("inside of Input validation Success");
			res=service.registerUser(bean,"solution_admin");
			if(res.equals(Constants.SUCCESS))
			{
				map.addAttribute("msg", "Registration Successfully Completed.");
				return "Message";
			}else
			{
				map.addAttribute("emsg", "Registration failed Caused By "+res);
				return "Message";
			}						
		}				
	}
	
	@RequestMapping("/openLoginView")
	public String getLoginPage(ModelMap map) 
	{
		UserDetails details= new UserDetails();
		map.addAttribute("user", details);
		return "Login";
	}
	
	@RequestMapping("/autenticate")
	public String authenticate(@ModelAttribute ("user") @Valid UserDetails bean, BindingResult result , ModelMap map,HttpSession session,HttpServletRequest request) {
		System.out.println(LOG+"authenticate");
		
		if(result.hasErrors())
		{
			System.out.println(LOG+"authenticate : has Errors");
			map.addAttribute("user", bean);
		}else
		{
			res=service.verifyUser(bean);
			if(res!=null && res.equals(rolesRef.get(0)))
			{
				session=request.getSession(true);
				session.setAttribute("user", bean.getEmail());
				session.setAttribute("menu", "AdminMenu");
				return "AdminMenu";
			}else if(res!=null && res.equals(rolesRef.get(1)))
			{
				session=request.getSession(true);
				session.setAttribute("user", bean.getEmail());
				session.setAttribute("menu", "SolutionAdminMenu");
				return "SolutionAdminMenu";
			}else if(res!=null && res.equals(rolesRef.get(2)))
			{
				session=request.getSession(true);
				session.setAttribute("user", bean.getEmail());
				session.setAttribute("menu", "ResponderMenu");
				return "ResponderMenu";
			}else if(res!=null && res.equals(rolesRef.get(3)))
			{
				map.addAttribute("emsg", "Kindly Login Using Android App");
				return "Message";
			}else
			{
				map.addAttribute("user", bean);
				map.addAttribute("emsg", res);
				return "Login";
			}
		}
		return "Empty";
		
	}
	
	@RequestMapping("/viewMenu")
	public String getMenu(HttpSession session) 
	{
		System.out.println(LOG+"getMenu");
		String menu=(String) session.getAttribute("menu");
		return "redirect:"+menu;
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpSession session,ModelMap map) 
	{
		System.out.println(LOG+"logout");
		session=request.getSession(false);
		session.removeAttribute("user");
		session.invalidate();
		map.addAttribute("msg","<h1>Success</h1><br><h1><i>Logged out Successfully</i></h1>");
		return "Logout";
	}
	
	@RequestMapping("/openManageRespondersView")
	public String openManageRespondersView(HttpSession session,ModelMap map) 
	{
		System.out.println(LOG+"openManageRespondersView");
		String email=(String) session.getAttribute("user");
		System.out.println("The Present User is "+email);
		List<ResponderBean> responders=service.getAllResponders(email);
		map.addAttribute("list", responders);
		return "ManageResponders";
	}
	
	@RequestMapping("/listOfSolutionAdmin")
	public String listOfSolutionAdmin(ModelMap map) 
	{
		System.out.println(LOG+"listOfSolutionAdmin");
		List<UserDetails> registerBeans= service.getAllRegisteredSolutionAdmins();
		map.addAttribute("beans", registerBeans);
		return "SolutionAdminList";
	}
	
	@RequestMapping("/openAddResponderView")
	public String openAddResponderView(ModelMap map) 
	{
		System.out.println(LOG+"openAddResponderView");
		ResponderBean bean= new ResponderBean();		
		List<String> topics=service.getTopics();
		map.addAttribute("topics", topics);
		map.addAttribute("bean", bean);
		return "AddResponder";
	}
	
	@RequestMapping("/addResponder")
	public String addResponder(@ModelAttribute ("bean") @Valid ResponderBean bean, BindingResult result , ModelMap map,HttpSession session,HttpServletRequest request) {
		
		System.out.println(LOG+"openAddResponderView");
		if(result.hasErrors())
		{
			System.out.println(LOG+"openAddResponderView : has Errors");
			List<String> topics=service.getTopics();
			map.addAttribute("topics", topics);
			map.addAttribute("bean", bean);
			return "AddResponder";
		}else
		{
			String email=(String) session.getAttribute("user");
			bean.setIsActive(request.getParameter("isActive"));
			String res=service.addResponder(bean,email);
			return "redirect:openManageRespondersView";
		}		
	}
	
	@RequestMapping("/openEditResponderView")
	public String openEditResponderView(@RequestParam ("resp_sl_no") String resp_sl_no,HttpSession session, ModelMap map) 
	{
		System.out.println(LOG+"openEditResponderView");
		System.out.println("RESP SL NO IS "+resp_sl_no); 
		String email=(String) session.getAttribute("user");
		ResponderBean bean=service.getResponderDetails(resp_sl_no,email);
		System.out.println("RESPONSE BEAN IS "+bean);
		List<String> topics=service.getTopics();
		map.addAttribute("topics", topics);
		map.addAttribute("bean", bean);		
		return "EditResponder";				
	}
	
	
	@RequestMapping("/updateResponder")
	public String updateResponder(@RequestParam ("resp_sl_no") String resp_sl_no,HttpServletRequest request,@ModelAttribute ("bean") @Valid ResponderBean bean, BindingResult result , ModelMap map,HttpSession session) 
	{
		System.out.println(LOG+"updateResponder");
		System.out.println("RESP SL NO IS "+resp_sl_no);
		bean.setId(Integer.parseInt(resp_sl_no));
		String email=(String) session.getAttribute("user");
		System.out.println("Updated Bean is "+bean);
		String res=service.updateResponderDetails(bean,email);
		return "redirect:openManageRespondersView";
	}
	
	@RequestMapping("/openDeleteResponderView")
	public String openDeleteResponderView(@RequestParam ("resp_sl_no") String resp_sl_no) 
	{
		System.out.println(LOG+"openDeleteResponderView");
		System.out.println("RESP SL NO IS "+resp_sl_no);
		String email=service.deleteResponder(resp_sl_no);
		return "redirect:openManageRespondersView"; 
	}
		
	
	@RequestMapping("/openAddTopicView")
	public String openAddTopicView(HttpSession session,ModelMap map) {
		Topic topic= new Topic();
		map.addAttribute("bean", topic);
		return "AddTopic";
	}
	
	
	@RequestMapping("/openManageTopicsView")
	public String openManageTopicsView(HttpSession session,ModelMap map)
	{
		System.out.println(LOG+"openManageTopicsView");
		String email=(String) session.getAttribute("user");
		System.out.println("The Present User is "+email);
		List<Topic> topics=service.getAllTopic(email);
		map.addAttribute("list", topics);
		return "ManageTopics";
	}
	
	
	@RequestMapping("/addTopic")
	public String addTopic(@ModelAttribute ("bean") @Valid Topic bean, BindingResult result , ModelMap map,HttpSession session,HttpServletRequest request) {
		
		System.out.println(LOG+"addTopic");
		if(result.hasErrors())
		{
			System.out.println(LOG+"addTopic : has Errors");
			map.addAttribute("bean", bean);
			return "AddTopic";
		}else
		{
			String email=(String) session.getAttribute("user");
			String res=service.addTopic(bean,email);
			return "redirect:openManageTopicsView";
		}
		
	}
	
	String myTopic_sl="";
	@RequestMapping("/openEditTopicView")	
	public String openEditTopicView(@RequestParam("topic_sl_no") String topic_sl_no, ModelMap map) 
	{
		System.out.println(LOG+"openEditTopicView");
		System.out.println("TOPIC SL_NO IS "+topic_sl_no);
		myTopic_sl=topic_sl_no;
		Topic topic =service.getTopic(topic_sl_no);
		map.addAttribute("bean", topic);
		return "EditTopic";
	}
	
	
	@RequestMapping("/updateTopic")
	public String updateTopic(@ModelAttribute ("bean") @Valid Topic bean, BindingResult result , ModelMap map, HttpSession  session) {
		
		System.out.println(LOG+"updateTopic");
		if(result.hasErrors())
		{
			System.out.println(LOG+"updateTopic : has Errors");
			map.addAttribute("bean", bean);
			return "EditTopic";
		}else
		{			
			bean.setId(Integer.parseInt(myTopic_sl));
			String email=(String) session.getAttribute("user");
			String res=service.updateTopic(bean,email);
			return "redirect:openManageTopicsView";
		}		
	}
	
	@RequestMapping("/openDeleteTopicView")
	public String openDeleteTopicView(@RequestParam("topic_sl_no") String topic_sl_no) 
	{
		System.out.println(LOG+"updateTopic");
		System.out.println("SL NO IS "+topic_sl_no);
		String res=service.deleteTopic(topic_sl_no);
		return "redirect:openManageTopicsView";
	}
	
	
	
	
	@RequestMapping("/studentRegister")
	public String studentRegistration(HttpServletRequest request,ModelMap map) throws ParseException 
	{
		String data=request.getParameter("data");
		StudentBean bean=getStudentBean(data);
		String res=service.registerStudent(bean);
		JSONObject object= new JSONObject();
		if(res.equals(Constants.SUCCESS)){
			object.put("status", Constants.SUCCESS);
			map.addAttribute("data", object);
		}else
		{
			object.put("status", res);
			map.addAttribute("data", object);
		}
		return "Data";
	}	
	
	public StudentBean getStudentBean(String data) throws ParseException 
	{
		JSONParser parser= new JSONParser();
		JSONObject object=(JSONObject) parser.parse(data);
		StudentBean bean= new StudentBean();
		bean.setName((String) object.get("name"));
		bean.setCity((String) object.get("city"));
		bean.setDob((String) object.get("dob"));
		bean.setEmail((String) object.get("email"));
		bean.setPass((String) object.get("pass"));
		bean.setIntrested((String) object.get("institute"));		
		return bean;
	}
	
	@RequestMapping("/jsonLogin")
	public String jsonLogin(HttpServletRequest request, ModelMap map) throws ParseException 
	{
		String data=request.getParameter("data");
		UserDetails details=getUserDatailsBean(data);
		String role=service.authenticateMobileUser(details);
		JSONObject object= new JSONObject();
		if(role.equals("student"))
		{
			List<Queries> queries=service.getAllQueriesForStudent(details.getEmail());
			object.put("status", role);
			object.put("queries", getAllQueriesAsJsonArray(queries));
			map.addAttribute("data", object);
			return "Data";
		}else if(role.equals("responder"))
		{
			
			List<Queries> responsed=service.getAllQueriesForResponderOfResponsed(details.getEmail());
			List<Queries> topicMatched=service.getAllQueriesForResponderOfTopic(details.getEmail());
			object.put("status", role);
			object.put("responded", getAllQueriesAsJsonArray(responsed));
			object.put("topicBased", getAllQueriesAsJsonArray(topicMatched));
			map.addAttribute("data", object);
			return "Data";
		}else
		{
			object.put("status", role);
			map.addAttribute("data", object);
		}			
		return "Data";
	}
	
	
	public JSONArray getAllQueriesAsJsonArray(List<Queries> queries) 
	{
		JSONArray array= new JSONArray();
		for (Queries queries2 : queries) {
			array.add(queries2.getJsonForQuery());
			/*List<ResponseBean> beans = queries2.getResponses();
			
			for (ResponseBean bean : beans) {

				List<FeedBackBean> feedBackBeans = bean.getFeedBackBeans();

				for (FeedBackBean bean2 : feedBackBeans) {
					
				}
			}
*/		}
		return array;
	}
	public UserDetails getUserDatailsBean(String date) throws ParseException 
	{
		
		JSONObject object=(JSONObject) parser.parse(date);
		UserDetails details= new UserDetails();
		details.setEmail((String) object.get("email"));
		details.setPass((String) object.get("pass"));
		return details;
	}
	
	@RequestMapping("/addQuery")
	public String addQuery(HttpServletRequest request, ModelMap map) throws ParseException 
	{
		System.out.println(LOG+"addQuery");
		String data=request.getParameter("data");
		Queries queries=getQuery(data);
		String res=service.addQuery(queries);
		if(res.equals(Constants.SUCCESS))
		{
			JSONObject object= new JSONObject();
			object.put("status", Constants.SUCCESS);
			map.addAttribute("data", object);
		}
		return "Data";
	}
	
	public Queries getQuery(String data) throws ParseException 
	{
		JSONObject object=(JSONObject) parser.parse(data);
		Queries queries= new Queries();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String presentDate = format.format(new Date()).toString();
		queries.setQuery((String) object.get("query"));
		queries.setQueryDate(presentDate);
		queries.setQueryLockedStatus(false);
		queries.setQueryEmail((String) object.get("email"));
		queries.setTopic((String) object.get("topic"));
		return queries;
	}
	
	@RequestMapping("/addResponse")
	public String addResponse(HttpServletRequest request,ModelMap map) throws ParseException 
	{
		System.out.println(LOG+"addResponse");
		String data=request.getParameter("data");
		ResponseBean bean=getResponseBean(data);
		String res=service.addResponse(bean);
		JSONObject object= new JSONObject();
		object.put("status", Constants.SUCCESS);
		map.addAttribute("data", object);
		return "Data";
	}

	public ResponseBean getResponseBean(String data) throws ParseException {
		JSONObject object=(JSONObject) parser.parse(data);
		ResponseBean  bean= new ResponseBean();
		bean.setRespEmail((String) object.get("email"));
		bean.setResponseLikes(0+"");
		bean.setResponseDisLikes(0+"");
		bean.setFeedback("");
		bean.setResponse((String) object.get("response"));
		bean.setQuerySlNo((String) object.get("query_sl_no"));
		return bean;
	}
	
	
	@RequestMapping("/addLikeForResponse")
	public String addLikeForResponse(HttpServletRequest request,ModelMap map) 
	{
		System.out.println(LOG+"addLikeForResponse");
		String resp_sl_no=request.getParameter("resp_sl_no");
		System.out.println("RESP SL No is "+resp_sl_no);		
		String res=service.addLikeForResponse(resp_sl_no);
		JSONObject object= new JSONObject();
		object.put("status", res);
		map.addAttribute("data", object);
		return "Data";
	}
	
	@RequestMapping("/addDisLikeForResponse")
	public String addDisLikeForResponse(HttpServletRequest request,ModelMap map) 
	{
		System.out.println(LOG+"addDisLikeForResponse");
		String resp_sl_no=request.getParameter("resp_sl_no");
		System.out.println("RESP SL No is "+resp_sl_no);		
		String res=service.addDisLikeForResponse(resp_sl_no);
		JSONObject object= new JSONObject();
		object.put("status", res);
		map.addAttribute("data", object);
		return "Data";
	}
	
	@RequestMapping("/addFeedbackForResponse")
	public String addFeedbackForResponse(HttpServletRequest request,ModelMap map) throws ParseException 
	{
		System.out.println(LOG+"addFeedbackForResponse");
		String data=request.getParameter("data");
		FeedBackBean feBackBean=getFeedBackBean(data);
		String res=service.addFeedback(feBackBean);
		JSONObject object= new JSONObject();
		object.put("status", res);
		map.addAttribute("data", object);
		return "Data";
	}
	
	public FeedBackBean getFeedBackBean(String data) throws ParseException 
	{
		
		JSONObject object= (JSONObject) parser.parse(data);
		FeedBackBean bean= new FeedBackBean();
		bean.setFeedback((String) object.get("feedback"));
		bean.setResp_sl_no((String) object.get("resp_sl_no"));		
		return bean;
	}
	
	
	@RequestMapping("/getMyQueriesForStudent")
	public String getMyQueriesForStudent(HttpServletRequest request,ModelMap map) 
	{
		System.out.println(LOG+"getMyQueriesForStudent");
		String email=request.getParameter("email");
		System.out.println("EMAIL is "+email);
		String data=service.getAllQueriesOfStudent(email);
		return "Data";
	}
	
	@RequestMapping("/getAllInstitutes")
	public String getAllInstitutes(HttpServletRequest  request,ModelMap map) 
	{
		System.out.println(LOG+"getAllInstitutes");
		List<RegisterBean> beans=service.getAllInstitutes();
		if(beans!=null)
		{
			JSONObject object= new JSONObject();
			object.put("loi", getJsonListOfInstitutes(beans));
			object.put("status", Constants.SUCCESS);
			map.addAttribute("data", object);
		}else
		{
			JSONObject object= new JSONObject();
			object.put("status", "No Institutes are Registered, Kindly try next time.");			
			map.addAttribute("data", object);			
		}
		return "Data";	
	}
	
	public JSONArray getJsonListOfInstitutes(List<RegisterBean> beans) 
	{
		JSONArray array= new JSONArray();
		JSONObject object;
		for(RegisterBean bean:beans)
		{
			object= new JSONObject();
			object.put("insName", bean.getCompany());
			object.put("insSlNo", bean.getId());
			array.add(object);
		}
		return array;
	}
	
	@RequestMapping("/getAllTopicOfInstitute")
	public String getAllTopicOfInstitute(HttpServletRequest request,ModelMap map) 
	{
		System.out.println(LOG+"getAllTopicOfInstitute");
		String email=request.getParameter("email");
		System.out.println("Email is "+email);
		List<Topic> topics=service.getAllTopicOfInstitute(email);
		JSONObject object= new JSONObject();
		object.put("status", Constants.SUCCESS);
		object.put("lot", getJsonArray(topics));
		map.addAttribute("data", object);
		return "Data";
	}
	
	public JSONArray getJsonArray(List<Topic> topics) 
	{
		System.out.println(LOG+"getJsonArray");
		JSONArray array= new JSONArray();
		if(topics!=null)
		{
			for(Topic topic:topics)
				array.add(topic.getJson());
		}
		return array;
	}
	
	@RequestMapping("/openBroadCastView")
	public String openBroadCastView()
	{
		System.out.println(LOG+"openBroadCastView");
		return "EmailForm";
	}
	
	@RequestMapping(value="sendEmail",method = RequestMethod.POST)
	public String doSendEmail(HttpServletRequest request,HttpSession session) {
		
		String recipientAddress = request.getParameter("sendTo");
		String emailId=(String) session.getAttribute("user");
		List<String> list=service.getEmailAddresses(recipientAddress,emailId);
		System.out.println("Email's List is "+list);
		
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		SimpleMailMessage email =null;
		for (String string : list) {
			
			try {
				
				System.out.println("To: " + recipientAddress);
				System.out.println("Subject: " + subject);
				System.out.println("Message: " + message);
				
				email = new SimpleMailMessage();
				email.setTo(string);
				email.setSubject(subject);
				email.setText(message);
				mailSender.send(email);
			}catch (Exception exception) {
				System.out.println("Exception inside of Sending Mails");
				exception.printStackTrace();
			}
		}
		return "redirect:viewMenu";
	}
	
	
	@RequestMapping("/makeStatusChange")
	public String makeStatusChange(@RequestParam("sl_no") String sl_no)
	{
		System.out.println(LOG+"makeStatusChange");
		System.out.println("SL_NO is "+sl_no);
		String res= service.makeStatusChange(sl_no);
		return "redirect:listOfSolutionAdmin";
	}
	
}
