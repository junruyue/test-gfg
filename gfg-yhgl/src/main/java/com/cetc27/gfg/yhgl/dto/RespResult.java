package com.cetc27.gfg.yhgl.dto;

import lombok.Data;

@Data
public class RespResult<T> {

    private int code;
    private String message;
    private T data;
}
