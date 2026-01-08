package com.amadeus.mapper;

import com.amadeus.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_user_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operate_user_id}, #{operate_time}, #{class_name}, #{method_name}, #{method_params}, #{return_value}, #{cost_time});")
    public void insert(OperateLog log);

}