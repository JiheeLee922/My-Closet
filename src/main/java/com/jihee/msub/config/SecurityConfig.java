package com.jihee.msub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jihee.msub.member.service.MemberSerivce;

import lombok.AllArgsConstructor;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private MemberSerivce memberService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*	spring security 에서 모든 인증은 AuthenticationManager를 통해 이뤄짐.
		 *  AuthenticationManager 생성하려면 AuthenticationManagerBuilder 사용.
		 *  인증을 위해 UserDetailService를 통해 필요한 정보 갖고 오는데 여기선 memberService에서 처리*/
		
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		/*	WebSecurity는 FilterChainProxy를(security에서 사용하는 filter모음) 생성하는 필터 
		 * 아래 경로의 파일들을 Spring Security가 무시할 수 있도록 설정. 기준은 resources/static */
		
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*	HttpSecurity를 통해 HTTP요청에 대한 웹기반 보안을 구성한다. 
		 * authorizeRequests -> HttpServletRequests에 따라 접근 제한. antMatchers 메서드로 특정 경로 지정해 역할에 따른 접근 설정 잡아준다
		 * formLogin -> form 기반으로 인증하도록함. 로그인 정보는 기본적으로 HttpSession 이용*/
		
		http.authorizeRequests()
				//페이지 권한 설정
				.antMatchers("/admin/**").hasRole("ADMIN")		//해당 경로는 ADMIN 롤을 가진 사용자만 접근 가능
				.antMatchers("/user/myinfo").hasRole("MEMBER")
				.antMatchers("/board/**").hasRole("MEMBER")
				.antMatchers("/chat/**").hasRole("MEMBER")
				.antMatchers("/**").permitAll()		//	<-> .anyRequest().authenticated() 모든 사용자 접근가능
			.and()	//로그인 설정
				.formLogin()
				.loginPage("/user/login")		// 기본 제공되는 form 말고 커스텀 로그인 form 을 쓰고 싶을 떄 
				.defaultSuccessUrl("/user/login/result")	//로그인 성공됐을 떄 이동 페이지
				.successHandler(successHandler())
				//.usernameParameter("mbrId")  로그인 form에선 name이 username을 아이디로 인식하는데 다른걸로 하고 싶을 때
				.permitAll()
			.and()	//로그아웃 설정
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))	//로그아웃의 기본 url 말고 커스텀하고 싶을 때
				.logoutSuccessUrl("/user/logout/result")
				.invalidateHttpSession(true)	//HTTP 세션 초기화
				//.deleteCookies("키명")  로그아웃 시 특정 쿠키 제거하고 싶을 떄 
			.and()	//403 예외처리 핸들링
				.exceptionHandling().accessDeniedPage("/user/denied");
		
	}
	
	
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler("/user/login/result");
	}
	
}
