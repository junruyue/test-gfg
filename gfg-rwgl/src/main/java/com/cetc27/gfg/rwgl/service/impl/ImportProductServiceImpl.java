package com.cetc27.gfg.rwgl.service.impl;

import com.cetc27.gfg.rwgl.dto.ProductDto;
import com.cetc27.gfg.rwgl.entity.CPST;
import com.cetc27.gfg.rwgl.entity.CPYXX;
import com.cetc27.gfg.rwgl.entity.GXCP;
import com.cetc27.gfg.rwgl.repository.CPSTRepository;
import com.cetc27.gfg.rwgl.repository.CPYXXRepository;
import com.cetc27.gfg.rwgl.repository.GXCPRepository;
import com.cetc27.gfg.rwgl.service.ImportProductService;
import com.cetc27.gfg.xml.entity.DaProFileAchNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Service
public class ImportProductServiceImpl implements ImportProductService {

    @Autowired
    private CPYXXRepository cpyxxRepository;

    @Autowired
    private GXCPRepository gxcpRepository;

    @Autowired
    private CPSTRepository cpstRepository;

    @Override
    public void saveGXCPProduct(ProductDto<GXCP> gxcpProductDto) {

        if (gxcpProductDto != null) {
            //1.存储产品源信息
            CPYXX cpyxx = gxcpProductDto.getCpyxx();

            //2.存储产品信息
            CPYXX save = cpyxxRepository.save(cpyxx);
            GXCP gxcp = gxcpProductDto.getCpxx();
            gxcp.setProductId(save.getProductId());
            gxcpRepository.save(gxcp);

            //3.存储产品实体
            List<CPST> cpstList = gxcpProductDto.getCpstList();
            cpstRepository.saveAll(cpstList);


        }
    }

    /**
     * 根据通知解析xml文件进行dto实体映射(dom4j)
     * @param daProFileAchNotify 产品归档完成通知
     * @return
     */
    public ProductDto parseProductMetaXml(DaProFileAchNotify daProFileAchNotify){



        return null;
    }
}
