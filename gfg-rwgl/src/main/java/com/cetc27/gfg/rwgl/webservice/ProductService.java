package com.cetc27.gfg.rwgl.webservice;

import com.cetc27.gfg.rwgl.dto.ProductDto;
import com.cetc27.gfg.rwgl.dto.RespResult;
import com.cetc27.gfg.rwgl.entity.GXCP;
import org.springframework.data.domain.AfterDomainEventPublication;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ProductService {

    /**
     * 1.读取xml信息
     * 2.产品入库
     * 2.返回结果
     */
    @WebMethod
    public RespResult<ProductDto<GXCP>> sendDaProMsgAndImportDB(@WebParam(name = "daProFileAchNotify") String daProFileAchNotify);


}
