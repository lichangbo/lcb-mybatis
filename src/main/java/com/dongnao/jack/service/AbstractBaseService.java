/**
 * 
 */
package com.dongnao.jack.service;

import java.lang.reflect.Proxy;

/**
 * @author luoyang
 *
 */
public abstract class AbstractBaseService {

	public Object getProxy(Class<?> intfClass,Object mapperInstance) {
		return Proxy.newProxyInstance(intfClass.getClassLoader(), new Class<?>[]{intfClass}, new DaoinvocationHandler(mapperInstance));
	}
	
}
