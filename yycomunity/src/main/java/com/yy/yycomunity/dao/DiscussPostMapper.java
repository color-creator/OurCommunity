package com.yy.yycomunity.dao;

import com.yy.yycomunity.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);
    //个人主页，我发布的帖子，sql有时需要拼userid，有时不需要
    //考虑分页的可能,起始行行号，一页最多多少条数据，保存页码

    int selectDiscussPostRows(@Param("userId")int userId);
    //sql里面需要用到动态条件参数<if>，并且这个方法只有一个参数，必须要取别名
    //@Param注解用于给参数取别名



}

