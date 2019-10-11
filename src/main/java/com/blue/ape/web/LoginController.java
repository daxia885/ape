package com.blue.ape.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.blue.ape.entity.Member;
import com.blue.ape.service.MemberService;
import com.blue.ape.util.ConstantsUtils;

@Controller
public class LoginController extends ConstantsUtils {
	@Autowired
	private MemberService memberService;

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public String loginPage() {
		return "login";
	}

	/**
	 * 用户登录
	 * 
	 * @param member
	 * @return
	 */
	@PostMapping("loginMember")
	public String loginMember(HttpServletRequest request, HttpServletResponse response, Member member) {
		if (StringUtils.isEmpty(member.getUsername())) {
			return USERNAME_IS_NULL;
		}
		if (StringUtils.isEmpty(member.getPassword())) {
			return PASSWORD_IS_NULL;
		}
		boolean isLogin = memberService.loginMember(member);
		if (isLogin) {
			request.getSession().setAttribute(member.getUsername(), member);
			if (member.isRemember()) {
				Cookie cookie = new Cookie("username", member.getUsername());
				cookie.setMaxAge(24 * 60 * 60);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			return "redirect:index";
		} else {
			request.setAttribute("info", "用户名或密码不对");
			return "login";
		}
	}

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@RequestMapping("register")
	public String registerPage() {
		return "register";
	}

	/**
	 * 注册新用户
	 * 
	 * @param member
	 * @return
	 */
	@PostMapping("registerMember")
	@ResponseBody
	public Object registerMember(@RequestBody Member member) {
		if (StringUtils.isEmpty(member.getUsername())) {
			return USERNAME_IS_NULL;
		}
		if (StringUtils.isEmpty(member.getPassword())) {
			return PASSWORD_IS_NULL;
		}
		memberService.addMember(member);
		return SUCCESS_CODE;
	}
	
	/**
	 * 校验用户是否已经存在，若存在则返回false
	 * @param member
	 * @return
	 */
	@PostMapping("checkExist")
	@ResponseBody
	public Object checkMemberExist(@RequestBody Member member) {
		if (StringUtils.isEmpty(member.getUsername())) {
			return USERNAME_IS_NULL;
		}
		return memberService.isExistMember(member);
	}

	private boolean isLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				Member mem = (Member) request.getSession().getAttribute(cookie.getValue());
				if (null != mem && mem.getUsername().equals(cookie.getValue())) {
					return true;
				}
			}
		}
		return false;
	}
}
