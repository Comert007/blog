package com.readdown.blog.service;

import com.readdown.blog.dao.CommentRepository;
import com.readdown.blog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author feng
 * @Date 2017/11/5
 * @Time 21:25
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort(Sort.Direction.ASC,"createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveCommment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentRepository.findOne(parentCommentId));
        }else {
            comment.setParentComment(null);
        }

        comment.setCreateTime(new Date());

        return commentRepository.save(comment);
    }

    /**
     *  循环每个顶点的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();

        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }


        combineChildren(commentsView);
        return commentsView;
    }


    /**
     *
     * @param comments
     */
    private void combineChildren(List<Comment> comments){
        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();

            for (Comment reply1 : replys1) {
//                循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
//          修改顶级节点的集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
//           清除临时存放区
            tempReplys= new ArrayList<>();
        }
    }


    private List<Comment> tempReplys = new ArrayList<>();


    /**
     * 递归迭代
     * @param comment 被迭代对象
     */
    private void recursively(Comment comment){
        tempReplys.add(comment); //顶节点添加到临时存放区
        if (comment.getReplyComments().size()>0){
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);

                if (reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }

}
