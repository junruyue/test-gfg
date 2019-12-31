package com.cetc27.gfg.rwgl;

import com.cetc27.gfg.rwgl.dto.ProductDto;
import com.cetc27.gfg.rwgl.entity.CPST;
import com.cetc27.gfg.rwgl.entity.CPYXX;
import com.cetc27.gfg.rwgl.entity.GXCP;
import com.cetc27.gfg.rwgl.service.impl.ImportProductServiceImpl;
import com.cetc27.gfg.rwgl.webservice.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RWGLApplicationTest {

    @Autowired
    private ImportProductServiceImpl ips;

    @Autowired
    private ProductService ps;

    /**
     * 测试插入产品
     */
    @Test
    public void saveCP() {
        ProductDto<GXCP> gxcpProductDto = new ProductDto<>();

        CPYXX cpyxx = new CPYXX();


        GXCP gxcp = new GXCP();
        gxcp.setImageLevel("L2");

        ArrayList<CPST> cpstList = new ArrayList<>();
        CPST cpst1 = new CPST();
        cpst1.setFileSize(5623);
        CPST cpst2 = new CPST();
        cpst2.setFileSize(4896);
        cpstList.add(cpst1);
        cpstList.add(cpst2);

        gxcpProductDto.setCpyxx(cpyxx);
        gxcpProductDto.setCpxx(gxcp);
        gxcpProductDto.setCpstList(cpstList);

        ips.saveGXCPProduct(gxcpProductDto);

        System.out.println(gxcpProductDto);
    }

    @Test
    public void test() {

        ps.sendDaProMsgAndImportDB("333");
    }

    @Test
    public void testFile() {

        File file = new File("D:\\PRODUCT\\GX\\L1\\XX-1\\2019\\1128\\XXX-1_MSS_000174222_005_002_002_L1");
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File file1 : list) {
                System.out.println("============================================================");
                System.out.println("文件名称: "+file1.getName());
                System.out.println("文件扩展名: "+ file1.getName().substring(file1.getName().lastIndexOf('.')));
                System.out.println("绝对路径: " + file1.getAbsolutePath());
                System.out.println("文件大小: " + file1.length());
            }
        }
    }

}
