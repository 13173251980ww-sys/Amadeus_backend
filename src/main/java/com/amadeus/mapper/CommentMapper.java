package com.amadeus.mapper;

import com.amadeus.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comments order by time desc")
    public List<Comment> getComments();

    @Insert("insert into comments(username,content,time,iconurl) values(#{username},#{content},#{time},#{iconurl})")
    public void addComment(Comment comment);
}
