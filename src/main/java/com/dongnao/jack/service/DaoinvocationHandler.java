package com.dongnao.jack.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class DaoinvocationHandler implements InvocationHandler {

	private Object instance;

	private PluginsConfig config;

	public DaoinvocationHandler(Object instance) {
		this.instance = instance;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		// 缓存开关，如果不需要缓存把开关关掉
		if (config.getIsCache()) {
			// List<CacheHandler> cacheHandler = config.getCaches();
			// 获取CacheHandler接口的所有实现
			Map<String, CacheHandler> cacheHandlers = ApplicationContextUtil
					.getApplicationContext().getBeansOfType(CacheHandler.class);
			// for (CacheHandler cache : cacheHandler) {
			//
			// FilterMethodName name = cache.getClass().getAnnotation(
			// FilterMethodName.class);
			//
			// if (method.getName().equals(name)) {
			// cache.process(args[0].toString(), method, instance);
			// }
			// }

			for (Map.Entry<String, CacheHandler> entry : cacheHandlers
					.entrySet()) {
				FilterMethodName annotationFilter = entry.getValue().getClass()
						.getAnnotation(FilterMethodName.class);
				// 根据method对象进行缓存实现类的过滤，不同的方法缓存的处理类不同，类过滤，因为dao层有很多个xxxDao类，不同的类缓存处理类可能不同
				if (method.getDeclaringClass().getName()
						.equals(annotationFilter.type().getName())
						&& annotationFilter.methodName().contains(
								method.getName())) {
					return entry.getValue().process(args, method, instance);
				}
			}
			
			//不需要进行缓存处理的方法
			return method.invoke(instance, args);

		} else {
			return method.invoke(instance, args);
		}
	}

}
