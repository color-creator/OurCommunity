package com.yy.yycomunity.service;

import com.yy.yycomunity.dao.DiscussPostMapper;
import com.yy.yycomunity.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    //查询某一页数据
    //由于DiscussPost里面的属性是UserId，但是实际业务当中不会显示id号，而是会显示用户名。
    // 有两条路线去解决，一种是在sql里面直接关联用户user表，但是这种对数据库压力比较大。
    // 另一条路线是在service层中
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    public int  findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
