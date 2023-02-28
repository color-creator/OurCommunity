package com.yy.yycomunity;

import com.yy.yycomunity.dao.DiscussPostMapper;
import com.yy.yycomunity.dao.UserMapper;
import com.yy.yycomunity.entity.DiscussPost;
import com.yy.yycomunity.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = YycomunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectIUser(){
        User user=userMapper.selectById(101);
        System.out.println(user);
        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
        user = userMapper.selectByName("liubei");
        System.out.println(user);
    }
    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test1");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());
        System.out.println(user);
        int rows = userMapper.insertUser(user);
        System.out.println(user);
        System.out.println("rows: "+rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser(){
        int rows = userMapper.updateHeader(150,"http://www.nowcoder.com/101.png");
        System.out.println(rows);
        rows = userMapper.updateStatus(150,1);
        System.out.println(rows);
        rows = userMapper.updatePassword(150,"234");
        System.out.println(rows);
    }


    @Autowired
    private DiscussPostMapper discussPostMapper;


    @Test
    public void selectPosts(){
       //
        List<DiscussPost> discussPostList = discussPostMapper.selectDiscussPosts(103,0,10);
        for(DiscussPost post :discussPostList){
            System.out.println(post);
        }
       int rows  = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);

    }



}
