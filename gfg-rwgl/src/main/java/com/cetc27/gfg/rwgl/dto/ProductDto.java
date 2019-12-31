package com.cetc27.gfg.rwgl.dto;

import com.cetc27.gfg.rwgl.entity.CPST;
import com.cetc27.gfg.rwgl.entity.CPYXX;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDto<T> implements Serializable {
    //1.cpyxx
    private CPYXX cpyxx;
    //2.gxcp/qbcp
    private T cpxx;
    //3.cpst
    private List<CPST> cpstList;
}
