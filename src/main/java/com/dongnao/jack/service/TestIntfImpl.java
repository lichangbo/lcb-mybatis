/**
 * 
 */
package com.dongnao.jack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;

import com.dongnao.jack.dao.CommonMapper;

/**
 * @author luoyang
 *
 */
@Service
public class TestIntfImpl extends AbstractBaseService implements TestIntf {

	@Autowired
	JedisCluster jedisCluster;
	
	@Autowired
	CommonMapper mapper;
	
	public void todo(String param) {
		
		CommonMapper commonmapper = (CommonMapper)getProxy(CommonMapper.class,mapper);
		
		commonmapper.deleteArea(null);
		
	}

}
