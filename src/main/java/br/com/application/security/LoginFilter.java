package br.com.application.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter /*implements Filter*/ {

	/*
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		User user = null;
		   HttpSession sess = ((HttpServletRequest) request).getSession(false);

		   if (sess != null){
		         user = (User) sess.getAttribute("usuarioLogado");
		   }

		     if (user == null) {
		              String contextPath = ((HttpServletRequest) request)
		                                 .getContextPath();
		              ((HttpServletResponse) response).sendRedirect(contextPath + "/security/form_login.xhtml");
		     } else {
		              chain.doFilter(request, response);
		     }
	}
	*/

}
