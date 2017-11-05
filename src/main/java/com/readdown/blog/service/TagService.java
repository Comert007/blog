package com.readdown.blog.service;

import com.readdown.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 22:44
 */
public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTag(Integer size);

    Tag updateTag(Long id, Tag Tag);

    void deleteTag(Long id);

    Tag getTagByName(String name);

    String checkAndAdd(String ids);

}
