package com.yy.yycomunity.controller;

import com.yy.yycomunity.dao.AlphaDao;
import com.yy.yycomunity.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring BOot.";
    }

    //如何获取请求对象，响应对象
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //无需设置返回值。可以直接通过Response对象直接向浏览器输出任何数据
        //获取请求对象、响应对象，直接在方法上进行声明，前端控制器会把这两个对象传给我们
        System.out.println(request.getMethod());//获取请求方式
        System.out.println(request.getServletPath());//获取请求路径
        Enumeration<String> enumeration=request.getHeaderNames();//得到所有请求行的key,迭代器一般是iteration但是这个请求航key的api比较捞
        //得到迭代器对象
        //里面是请求的消息头
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+" : "+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");//返回网页类型的数据，且网页字符集是utf-8类型
        try {
            PrintWriter writer = response.getWriter();//得到输出流对象
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*************************************************************************************/
    /******* ********** ********* ******获取参数的方法********** ************* ********* ****/
    /*************************************************************************************/
    //获取参数的方法一
    // /students?current=1&limit=20.当前是第几页，最多显示多少条数据
    @RequestMapping(path="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(int current,int limit){//servlet检测到名字相同的参数会直接赋值
        return "some students";
    }
    //获取参数的方法2
    @RequestMapping(path="/students2",method = RequestMethod.GET)
    @ResponseBody
    //通过RequestParam的注解对参数传递做更详细的说明.required=false指的是可以不传此参数，defaultValue默认值是1
    public String getStudents2(@RequestParam(name = "current",required = false,defaultValue = "1")int current,@RequestParam(name = "limit",required = false,defaultValue = "1")int limit){
        System.out.println("current: "+current);
        System.out.println("limit: "+limit);
        return "some students2";
    }
    //获取参数的方法3
    //将参数编排成路径的一部分
    // /students/123
    @RequestMapping(path = "/students/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        System.out.println(id);
        return "a students";
    }

    //post请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    /*使用模板引擎动态数据的方式*/
    //响应一份html数据，不能加@ResponseBody注解
    @RequestMapping(path="/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        //返回类型：返回给DispacherServlet的数据和视图
        ModelAndView mav = new ModelAndView();//模板里面需要多少数据就add多少
        mav.addObject("age",20);
        mav.addObject("name","张三");
        mav.setViewName("/demo/view");//设置模板的路径，放在templates之下，只写文件名，无需加后缀
        return mav;
    }
    //响应一份html数据，不能加@ResponseBody注解
    @RequestMapping(path="/school",method = RequestMethod.GET)
    public String getSchool(Model model){//DispacherServlet检测到方法参数中有Model类型的数据会自动把model装配进去
        model.addAttribute("name","北京大学");
        model.addAttribute("age",80);

        return "/demo/view";//直接返回路径
    }

    //响应---JSON数据---异步请求
    //java对象 -->  json字符串  --> 浏览器解析对象使用js
    //由属性和属性值构成的**数据类型**都能转换成json字符串
    @RequestMapping(path="/emp",method= RequestMethod.GET)
    @ResponseBody//返回json数据需要的注解
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",20);
        emp.put("salary",200);
        return emp;
    }
    @RequestMapping(path="/emps",method= RequestMethod.GET)
    @ResponseBody//返回json数据需要的注解
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",20);
        emp.put("salary",200);
    list.add(emp);
        Map<String,Object> emp2 = new HashMap<>();
        emp2.put("name","张三");
        emp2.put("age",25);
        emp2.put("salary",2500);
        list.add(emp2);
        return list;
    }

}
