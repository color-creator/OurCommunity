package com.yy.yycomunity.controller;

import com.yy.yycomunity.dao.DiscussPostMapper;
import com.yy.yycomunity.entity.DiscussPost;
import com.yy.yycomunity.entity.Page;
import com.yy.yycomunity.entity.User;
import com.yy.yycomunity.service.DiscussPostService;
import com.yy.yycomunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path="/index",method= RequestMethod.GET)
    //返回视图名
    public String getIndexPage(Model model, Page page){
        //方法调用之前，SpringMVC会自动实例化Model和Page，并将Page注入给Model
        //所以在thymeleaf中可以直接访问Page对象中的数据


        //服务器设置
        //设置总行数
        page.setRows(discussPostService.findDiscussPostRows(0));
        //设置路径
        page.setPath("/index");
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(),page.getLimit());


        //需要用户名~
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if(list!=null){
            for(DiscussPost post: list){
                Map<String,Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        model.addAttribute("page",page);
        return "/index";

    }
}
