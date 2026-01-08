package com.amadeus.controller;

import com.amadeus.anno.LogOperation;
import com.amadeus.pojo.Comment;
import com.amadeus.pojo.Result;
import com.amadeus.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public Result getCommentsByPage(@RequestParam Integer pageNum, Integer pageSize){
        log.info("分页获取评论");

        return Result.success(commentService.getCommentsByPage(pageNum,pageSize));
    }

    @LogOperation
    @PostMapping
    public Result addComment(@RequestBody Comment comment){
        log.info(comment.toString());
        commentService.addComment(comment);
        return Result.success();
    }
}
