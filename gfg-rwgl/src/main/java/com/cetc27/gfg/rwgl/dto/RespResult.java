package com.cetc27.gfg.rwgl.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RespResult<T> implements Serializable{

    private int code;
    private String message;
    T data;
}
