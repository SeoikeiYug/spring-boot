package com.genius.spring.boot.session.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.genius.spring.boot.session.common.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

/**
 * @类名 PageController
 * @描述 TODO
 * @作者 shuaiqi
 * @创建日期 2020/1/16 15:39
 * @版本号 1.0
 * @参考地址
 **/
@Controller
@RequestMapping("/page")
public class PageController {

	/**
	 * 跳转到 首页
	 *
	 * @param request 请求
	 */
	@GetMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		final Enumeration<String> attributeNames = request.getSession().getAttributeNames();

		String token = (String) request.getSession().getAttribute(Constants.SESSION_KEY);
		mv.setViewName("index");
		mv.addObject("token", token);
		return mv;
	}

	/**
	 * 跳转到 登录页
	 *
	 * @param redirect 是否是跳转回来的
	 */
	@GetMapping("/login")
	public ModelAndView login(Boolean redirect) {
		ModelAndView mv = new ModelAndView();

		if (ObjectUtil.isNotNull(redirect) && ObjectUtil.equal(true, redirect)) {
			mv.addObject("message", "请先登录！");
		}
		mv.setViewName("login");
		return mv;
	}

	@GetMapping("/doLogin")
	public String doLogin(HttpSession session) {
		session.setAttribute(Constants.SESSION_KEY, IdUtil.fastUUID());

		return "redirect:/page/index";
	}

}