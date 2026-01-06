package com.amadeus.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Result{
    private Integer code;
    private Object data;
    private String msg;

    public static Result success(){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static Result success(Object data){
        Result result = success();
        result.setData(data);
        return result;
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
