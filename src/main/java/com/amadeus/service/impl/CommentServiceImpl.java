package com.amadeus.service.impl;

import com.amadeus.exception.ServiceException;
import com.amadeus.mapper.CommentMapper;
import com.amadeus.pojo.Comment;
import com.amadeus.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public PageInfo<Comment> getCommentsByPage(Integer pageNum, Integer pageSize){
//            //设置分页参数   *强制转换有bug
//            PageHelper.startPage(pageNum,pageSize);
//            //调用Mapper接口方法
//            List<Comment> commentList=commentMapper.getComments();
//            //解析并封装结果
//            Page<Comment> p=(Page<Comment>) commentList;
//
//            return new PageResult<Comment>(p.getTotal(),p.getResult());

            //现代企业写法
            PageHelper.startPage(pageNum, pageSize);
            List<Comment> list = commentMapper.getComments();

            // 直接用 PageInfo 封装，不需要转换
            return new PageInfo<>(list);
    }

    @Transactional
    public void addComment(Comment comment){
        if(comment.getUsername()==null||comment.getUsername().trim().isEmpty()){
            throw new ServiceException(400,"用户名不能为空");
        }
        if(comment.getContent()==null || comment.getContent().trim().isEmpty()){
            throw new ServiceException(400,"评论内容不能为空");
        }
        if(comment.getIconurl()==null || comment.getIconurl().trim().isEmpty()){
            throw new ServiceException(400,"头像url不能为空");
        }
        comment.setTime(LocalDateTime.now());
        commentMapper.addComment(comment);
        log.info("新增评论: {}", comment);
    }
}
