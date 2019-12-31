package com.cetc27.gfg.rwgl.service;

import com.cetc27.gfg.rwgl.dto.ProductDto;
import com.cetc27.gfg.rwgl.entity.GXCP;

public interface ImportProductService  {

    void saveGXCPProduct(ProductDto<GXCP> gxcpProductDto);
}
