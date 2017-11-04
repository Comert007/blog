package com.readdown.blog.service;

import com.readdown.blog.NotFoundException;
import com.readdown.blog.dao.BlogRepository;
import com.readdown.blog.po.Blog;
import com.readdown.blog.po.Type;
import org.attoparser.util.TextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.TextUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author feng
 * @Date 2017/11/4
 * @Time 23:42
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Blog blog) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (blog.getTitle()!=null && !"".equals(blog.getTitle()) ){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()));
                }

                if (blog.getType().getId()!=null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getType().getId()));
                }

                if (blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findOne(id);
        if (b == null) {
            throw new NotFoundException("the type of 'Blog' is not exist");
        }
        BeanUtils.copyProperties(blog, b);

        return blogRepository.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }
}
