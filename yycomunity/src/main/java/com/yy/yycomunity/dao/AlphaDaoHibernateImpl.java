package com.yy.yycomunity.dao;

import org.springframework.stereotype.Repository;

@Repository("alphahibernate")
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select(){
        return "hiberna";

    }
}
