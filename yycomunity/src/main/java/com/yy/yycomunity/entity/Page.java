package com.yy.yycomunity.entity;


/*
* 封装分页相关的信息
*
* */
public class Page {
    //页面传入信息的接收
    //当前页码
    private int current = 1;
    //最多显示的数据条数
    private int limit =10;
    //服务端塞入客户端的数据
    //数据总数
    private int rows;
    //查询路径，返回给页面，复用分页链接
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current >= 1){
            this.current=current;
        }
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1&&limit<=100){
            //限制每页最多传输的数据
            this.limit=limit;
        }
        this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0)
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //计算出来的数据
    public int getOffset(){
        //通过当前页的页码，算出当前页的起始行
        return (current-1) * limit;
    }

    /*
    *  获取总页数
    *
    *
    * */
    public int getTotal(){
        //总行数除以当前页显示数据条数
        if(rows%limit==0)return rows/limit;
        return rows/limit+1;
    }

    //显示从哪里---到哪里
    /*
    当页数过多，只显示前两个
    * */
    public int getFrom(){
        return  (current-2)<1?1:(current-2);
    }
    public int getTo(){
        return (current+2)>getTotal()?getTotal():(current+2);
    }
}
