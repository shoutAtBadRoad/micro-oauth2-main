package com.post.db.own.entity;

import com.post.db.entity.QueryInfo;

public class QueryId extends QueryInfo {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QueryId(int pagenum, int pagesize, int id) {
        super(pagenum, pagesize);
        this.id = id;
    }
}
