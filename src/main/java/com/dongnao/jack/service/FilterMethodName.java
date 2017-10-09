/**
 * 
 */
package com.dongnao.jack.service;

/**
 * @author luoyang
 *
 */
public @interface FilterMethodName {

	//过滤的方法
	String methodName();
	//过滤的dao类
	Class<?> type();
}
