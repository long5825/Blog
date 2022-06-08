package com.chengdu.longblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chengdu.longblog.entity.Blog;
import com.chengdu.longblog.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService extends IService<Tag> {


    List<Blog> getTagAll(QueryWrapper queryWrapper, Page<Blog> page);
}
