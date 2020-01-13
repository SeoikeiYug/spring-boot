package com.genius.spring.boot.log.aop.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类名 TestController
 * @描述 测试 Controller
 * @作者 shuaiqi
 * @创建日期 2020/1/13 10:20
 * @版本号 1.0
 * @参考地址
 **/
@RestController
public class TestController {

	@GetMapping("/test")
	public Dict test(@RequestParam final String who) {
		return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
	}

}