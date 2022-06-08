package com.chengdu.longblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengdu.longblog.entity.Type;
import com.chengdu.longblog.mapper.TypeMapper;
import com.chengdu.longblog.service.TypeService;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
}
