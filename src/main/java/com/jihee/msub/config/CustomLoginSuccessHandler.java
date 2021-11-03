package com.jihee.msub.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


/**
 * 로그인 성공 시 핸들러
 * @author JiheeLee
 * @since 2021.11.03
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      	  수정자       			   수정내용
 *  -------      --------    ---------------------------
 *   2021.11.03  JiheeLee       	  최초 생성
 * 
 * </pre>
 */
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	public CustomLoginSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session != null) 
		{
			String redirectUrl = (String) session.getAttribute("prevPage");
			if(redirectUrl != null) 
			{
				session.removeAttribute("prevPage");
				getRedirectStrategy().sendRedirect(request, response, redirectUrl); 
			} 
			else
			{
				super.onAuthenticationSuccess(request, response, authentication);
			}
		}
	}
	
	
}
