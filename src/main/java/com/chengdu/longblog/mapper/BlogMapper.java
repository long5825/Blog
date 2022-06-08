package com.chengdu.longblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengdu.longblog.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    List<Blog> getBlogByTypeID(Page<Blog> page);

    List<Blog> getBlogs(Page<Blog> page);

    Page<Blog> searchBlog(Page<Blog> page,@Param(Constants.WRAPPER) QueryWrapper<Blog> queryWrapper);

    Blog getBlog(@Param(Constants.WRAPPER)QueryWrapper queryWrapper);

    // 获取blog表的所有年
    @Select("SELECT DATE_FORMAT(b.create_time,'%Y') AS `year` FROM t_blog b GROUP BY `year` ORDER BY `year` DESC")
    List<String> getYears();

    // 通过年份获取博客
    @Select("SELECT * FROM t_blog b WHERE DATE_FORMAT(b.create_time,'%Y')=#{year}")
    List<Blog> getYearsByBlog(String year);

    Page<Blog> getTagByBlog(Page<Blog> page,@Param(Constants.WRAPPER)QueryWrapper queryWrapper);

}
