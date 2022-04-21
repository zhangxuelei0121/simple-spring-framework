package com.zxl.service;

import com.zxl.annotation.Service;

/**
 * @author zhangxuelei <zhangxuelei@kuaishou.com>
 * Created on 2022-04-19
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String get(String name) {
        return "My name is " + name;
    }

}
