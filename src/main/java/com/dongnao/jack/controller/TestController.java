package com.dongnao.jack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	JedisCluster jedisCluster;

	@RequestMapping("/invoke")
	public @ResponseBody String test() {
		try {
		for(int i = 0 ; i < 100 ; i++) {
			jedisCluster.set("key" + i, "value" + i);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "OK";
	}
	
}
