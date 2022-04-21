package com.zxl.servlet;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxl.annotation.RequestParam;

/**
 * 保存一个url和一个Method的关系
 *
 * @author zhangxuelei <zhangxuelei@kuaishou.com>
 * Created on 2022-04-19
 */
public class Handler {
    //必须把url放到HandlerMapping才好理解吧
    public Pattern pattern;  //正则
    public Method method;
    public Object controller;
    public Class<?>[] paramTypes;

    public Pattern getPattern() {
        return pattern;
    }

    public Method getMethod() {
        return method;
    }

    public Object getController() {
        return controller;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    //形参列表
    //参数的名字作为key,参数的顺序，位置作为值
    public Map<String, Integer> paramIndexMapping;

    public Handler(Pattern pattern, Object controller, Method method) {
        this.pattern = pattern;
        this.method = method;
        this.controller = controller;

        paramTypes = method.getParameterTypes();

        paramIndexMapping = new HashMap<>();
        putParamIndexMapping(method);
    }

    private void putParamIndexMapping(Method method) {
        //提取方法中加了注解的参数
        //把方法上的注解拿到，得到的是一个二维数组
        //因为一个参数可以有多个注解，而一个方法又有多个参数
        Annotation[][] pa = method.getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation a : pa[i]) {
                if (a instanceof RequestParam) {
                    String paramName = ((RequestParam) a).value();
                    if (!"".equals(paramName.trim())) {
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }

        //提取方法中的request和response参数
        Class<?>[] paramsTypes = method.getParameterTypes();
        for (int i = 0; i < paramsTypes.length; i++) {
            Class<?> type = paramsTypes[i];
            if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                paramIndexMapping.put(type.getName(), i);
            }
        }
    }
}