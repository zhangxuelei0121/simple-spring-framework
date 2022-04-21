package com.zxl.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxl.annotation.Autowired;
import com.zxl.annotation.Controller;
import com.zxl.annotation.RequestMapping;
import com.zxl.annotation.RequestParam;
import com.zxl.service.UserService;

/**
 * @author zhangxuelei <zhangxuelei@kuaishou.com>
 * Created on 2022-04-19
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp, @RequestParam("name") String name) {
        String result = userService.get(name);
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/add")
    public void add(HttpServletResponse resp, @RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/sub")
    public void sub(HttpServletResponse resp, @RequestParam("a") Double a, @RequestParam("b") Double b) {
        try {
            resp.getWriter().write(a + "-" + b + "=" + (a - b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
