package com.cetc27.gfg.yhgl.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private long total;

    private List<T> list;
}
