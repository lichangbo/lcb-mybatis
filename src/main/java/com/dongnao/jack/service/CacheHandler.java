package com.dongnao.jack.service;

import java.lang.reflect.Method;

public interface CacheHandler {

	Object process(Object[] param,Method method,Object instance);
}
