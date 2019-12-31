package com.cetc27.gfg.rwgl.webservice.impl;

import com.cetc27.gfg.rwgl.dto.ProductDto;
import com.cetc27.gfg.rwgl.dto.RespResult;
import com.cetc27.gfg.rwgl.entity.CPST;
import com.cetc27.gfg.rwgl.entity.CPYXX;
import com.cetc27.gfg.rwgl.entity.GXCP;
import com.cetc27.gfg.rwgl.service.ImportProductService;
import com.cetc27.gfg.rwgl.service.impl.ImportProductServiceImpl;
import com.cetc27.gfg.rwgl.webservice.ProductService;
import com.cetc27.gfg.xml.entity.DaProFileAchNotify;
import com.cetc27.gfg.xml.entity.FileInfo;
import com.cetc27.gfg.xml.entity.InterfaceFile;
import com.cetc27.gfg.xml.util.JaxbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@WebService(
        serviceName = "ProductService",
        targetNamespace = "http://webservice.rwgl.gfg.cetc27.com/",
        endpointInterface = "com.cetc27.gfg.rwgl.webservice.ProductService"
)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ImportProductService imps;

    @Override
    public RespResult<ProductDto<GXCP>> sendDaProMsgAndImportDB(String daProFileAchNotify) {

        RespResult<ProductDto<GXCP>> result = new RespResult<>();
        //1.根据归档通知获取源信息路径
        /*InterfaceFile<DaProFileAchNotify> interfaceFile =
                JaxbUtil.convertToJavaBean(new File("D:\\xmlexample\\test6.xml"), InterfaceFile.class);*/
        InterfaceFile<DaProFileAchNotify> interfaceFile =
                JaxbUtil.convertToJavaBean(daProFileAchNotify, InterfaceFile.class);
        //2.解析源信息文件获得对应的map集合
        //2.1获取源信息文件
        DaProFileAchNotify fileBody = interfaceFile.getFileBody();
        String filePath = "";
        String folderPath = fileBody.getFolderPath();
        List<FileInfo> fileList = fileBody.getFileList();
        for (FileInfo fileInfo : fileList) {
            if (fileInfo.getFileName().contains("meta")) {
                filePath = folderPath + "\\" + fileInfo.getFileName();
            }
        }
        if (filePath.length() > 0) {
            LinkedHashMap<String, Object> map = JaxbUtil.convertToMap(new File(filePath));
            //3.根据map集合,将对应字段存入cpyxx, 组合dto
            //之后写为枚举类型
            if ("IMGPRODUCT".equals(fileBody.getDataType())) {
                ProductDto productDto = new ProductDto();
                CPYXX cpyxx = new CPYXX();
                //提取map集合中cpyxx需要的信息
                cpyxx.setProductName((String) map.get("ProductID"));
                //cpyxx.setProductTime();
                //cpyxx.setProductType();
                cpyxx.setTaskId((String) map.get("TaskID"));
                cpyxx.setSatName((String) map.get("Satellite"));
                cpyxx.setSensorName((String) map.get("Sensor"));
                cpyxx.setSensorMode((String) map.get("SensorMode"));
                HashMap<String, Object> imageInfoMap = (HashMap<String, Object>) map.get("ImageInfo");
                cpyxx.setCountryRegion((String) imageInfoMap.get("Region"));
                HashMap<String, Object> corners = (HashMap<String, Object>) imageInfoMap.get("Corners");
                HashMap<String, Object> upperLeft = (HashMap<String, Object>) corners.get("UpperLeft");
                cpyxx.setTopLeftLat(Float.parseFloat((String) upperLeft.get("Latitude")));
                cpyxx.setTopLeftLon(Float.parseFloat((String) upperLeft.get("Longitude")));
                HashMap<String, Object> upperRight = (HashMap<String, Object>) corners.get("UpperRight");
                cpyxx.setTopRightLat(Float.parseFloat((String) upperRight.get("Latitude")));
                cpyxx.setTopRightLon(Float.parseFloat((String) upperRight.get("Longitude")));
                HashMap<String, Object> lowerRight = (HashMap<String, Object>) corners.get("LowerRight");
                cpyxx.setLowerRightLat(Float.parseFloat((String) lowerRight.get("Latitude")));
                cpyxx.setLowerRightLon(Float.parseFloat((String) lowerRight.get("Longitude")));
                HashMap<String, Object> lowerLeft = (HashMap<String, Object>) corners.get("LowerLeft");
                cpyxx.setLowerLeftLat(Float.parseFloat((String) lowerLeft.get("Latitude")));
                cpyxx.setLowerLeftLon(Float.parseFloat((String) lowerLeft.get("Longitude")));
                productDto.setCpyxx(cpyxx);

                //提取map集合中gxcp需要的信息
                GXCP gxcp = new GXCP();
                gxcp.setProductName(cpyxx.getProductName());
                HashMap<String, Object> centreLocation = (HashMap<String, Object>)imageInfoMap.get("CentreLocation");
                gxcp.setCentreLat(Float.parseFloat((String)centreLocation.get("Latitude")));
                gxcp.setCentreLon(Float.parseFloat((String)centreLocation.get("Longitude")));
                gxcp.setHeight(Integer.parseInt((String) imageInfoMap.get("Height")));
                gxcp.setUpperLeftLat(cpyxx.getTopLeftLat());
                gxcp.setUpperLeftLon(cpyxx.getTopLeftLon());
                gxcp.setLowerLeftLat(cpyxx.getLowerLeftLat());
                gxcp.setLowerLeftLon(cpyxx.getLowerLeftLon());
                gxcp.setUpperRightLat(cpyxx.getTopRightLat());
                gxcp.setUpperRightLon(cpyxx.getTopRightLon());
                gxcp.setLowerRightLat(cpyxx.getLowerRightLat());
                gxcp.setLowerRightLon(cpyxx.getLowerRightLon());
                productDto.setCpxx(gxcp);

                List<CPST> list = new ArrayList<>();
                //需要修改
                File cpstDir = new File(folderPath);
                File[] files = cpstDir.listFiles();
                for (File file : files) {
                    CPST cpst = new CPST();
                    cpst.setProductId(cpyxx.getProductName());
                    cpst.setRootPath(file.getAbsolutePath());
                    cpst.setRelativePath(file.getPath());
                    cpst.setFileExtension(file.getName().substring(file.getName().lastIndexOf('.')));
                    cpst.setFileSize((int)file.length());//文件大小不是int
                    list.add(cpst);

                }
                //提取map集合中cpst需要的信息
                productDto.setCpstList(list);
                imps.saveGXCPProduct(productDto);

                result.setCode(200);
                result.setMessage("导入数据成功");
                result.setData(productDto);
                return result;
            }


            //4.将dto组合完毕, 进行入库操作

            //5.返回结果

            return null;
        } else {

            return null;
        }

    }

}
