package com.amadeus.service;

import com.amadeus.pojo.Comment;
import com.amadeus.pojo.PageResult;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    public PageInfo<Comment> getCommentsByPage(Integer pageNum, Integer pageSize);

    public void addComment(Comment comment);
}
