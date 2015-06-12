package com.uttara.lpsq;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {
	public SessionFilter() {
		System.out.println("inside of no-arg constructor of SessionFilter");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Destroy method of Session Filter");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		System.out.println("inside of Session Filter " + uri);
		String[] securedurl = { "/ContactAppMVCF2/Logout.do",
				"/ContactAppMVCF2/openEditAccountView.do",
				"/ContactAppMVCF2/updateRegister.do",
				"/ContactAppMVCF2/openAddContactView.do",
				"/ContactAppMVCF2/AddContact.do",
				"/ContactAppMVCF2/openListContactView.do",
				"/ContactAppMVCF2/openEditContactView.do",
				"/ContactAppMVCF2/EditContact.do",
				"/ContactAppMVCF2/openDeleteContactView.do",
				"/ContactAppMVCF2/openSearchView.do",
				"/ContactAppMVCF2/SearchContact.do",
				"/ContactAppMVCF2/openBirthdayReminderView.do",
				"/ContactAppMVCF2/AddContactInt.jsp",
				"/ContactAppMVCF2/EditAccountInt.jsp",
				"/ContactAppMVCF2/EditContactInt.jsp" };
		ArrayList<String> al = new ArrayList<String>();
		for (String s : securedurl) {
			al.add(s);
		}

		if (al.contains(uri) == true) {
			System.out.println("inside of Session Filter " + uri
					+ " is secured");
			HttpSession s = req.getSession(false);
			if (s != null) {
				String email = (String) s.getAttribute("user");
				System.out.println("LB in Session Checking " + email);
				if (email == null) {
					System.out.println(uri + " Session is not avilable");
					resp.sendRedirect("SessionError.jsp");
				} else {
					System.out.println(uri + " Session is avilable");
					chain.doFilter(req, resp);
				}
			}
		} else
			chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println(" inside of init method of Session Filter");
	}

}
