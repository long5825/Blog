package com.chengdu.longblog.entity;

import com.baomidou.mybatisplus.annotation.*;



@TableName(value = "t_type")
public class Type {

    @TableId(value = "type_id",type = IdType.UUID)
    public Long id;

    @TableField("name")
    private String name;

    @TableField("blogCount")
    private int blogCount;

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogCount=" + blogCount +
                '}';
    }
}
