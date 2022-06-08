package com.chengdu.longblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengdu.longblog.entity.Blog;
import com.chengdu.longblog.entity.Tag;
import com.chengdu.longblog.mapper.BlogMapper;
import com.chengdu.longblog.mapper.TagMapper;
import com.chengdu.longblog.service.BlogService;
import com.chengdu.longblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    public List<Blog> getTagAll(QueryWrapper queryWrapper, Page<Blog> page){
        return    tagMapper.getTagAll(queryWrapper,page);
    }

}
