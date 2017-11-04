package com.readdown.blog.service;

import com.readdown.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 20:50
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id, Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);

}
