package com.cetc27.gfg.xml.test;


import com.cetc27.gfg.xml.entity.DaProFileAchNotify;
import com.cetc27.gfg.xml.entity.FileHead;
import com.cetc27.gfg.xml.entity.FileInfo;
import com.cetc27.gfg.xml.entity.InterfaceFile;
import com.cetc27.gfg.xml.util.JaxbUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.*;

public class ParseXmlInfoTest {

    @Test
    public void testParseXmlInfo() {

        DaProFileAchNotify daProFileAchNotify = new DaProFileAchNotify();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName("filename1");
        fileInfo.setFileLength(6456812);
        ArrayList<FileInfo> fileInfos = new ArrayList<>();
        fileInfos.add(fileInfo);
        daProFileAchNotify.setFileList(fileInfos);
        daProFileAchNotify.setReqID("135_REQ_00001");
        //daProFileAchNotify.setDataType("");
        daProFileAchNotify.setFolderPath("/data/");
        daProFileAchNotify.setProPlanID("0000001_000_231903");
        daProFileAchNotify.setSatelliteID("WX-1");
        daProFileAchNotify.setTaskID("0001_OMS_0011_111");

        FileHead fileHead = new FileHead();
        fileHead.setMessageType("PRDARCHNOTIFY");
        fileHead.setMessageID("000111");
        fileHead.setCreationTime(new Date());
        fileHead.setOriginatorAddress("KSCL");
        fileHead.setRecipientAddress("XXJC");

        InterfaceFile<DaProFileAchNotify> interfaceFile = new InterfaceFile<>();
        interfaceFile.setFileHead(fileHead);
        interfaceFile.setFileBody(daProFileAchNotify);
        File file = new File("d:\\xmlexample\\test1.xml");

        StringWriter stringWriter = new StringWriter();

       /*
       JAXB.marshal(interfaceFile, stringWriter);
       System.out.println(stringWriter.toString().replaceFirst("standalone=\"yes\"", ""));*/

        /*try{
            JAXBContext jaxbContext = JAXBContext.newInstance(InterfaceFile.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            StringWriter sw = new StringWriter();
            sw.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            marshaller.marshal(interfaceFile, sw);
            String xml = sw.toString();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(xml);
            fileWriter.close();
            sw.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        JaxbUtil.convertToXml(interfaceFile, new File("d:\\xmlexample\\test5.xml"));
    }

    @Test
    public void testParseXmlInfo2() {
        /*InterfaceFile<DaProFileAchNotify> interfaceFile =
                (InterfaceFile<DaProFileAchNotify>)JAXB.unmarshal(new File("d:\\xmlexample\\test1.xml"), InterfaceFile.class);*/

        InterfaceFile<DaProFileAchNotify> interfaceFile = JaxbUtil.convertToJavaBean(new File("d:\\xmlexample\\test2.xml"), InterfaceFile.class);

        System.out.println(interfaceFile);
    }

    @Test
    public void testDOM4J() {
        SAXReader saxReader = new SAXReader();
        try {
            Document document =
                    saxReader.read(
                            new File("D:\\PRODUCT\\GX\\L1\\XX-1\\2019\\1128\\XXX-1_MSS_000174222_005_002_002_L1\\XXX-1_MSS_000174222_005_002_002_L1.meta.xml"));
            Element rootElement = document.getRootElement();
            LinkedHashMap<String, Object> map = readDOM4JXml(rootElement);
            System.out.println(map);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private LinkedHashMap<String, Object> readDOM4JXml(Element rootElement) {

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        Iterator<Element> iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            String key = next.getName();
            if (next.isTextOnly()) {

                String value = next.getText();
                map.put(key, value);
            } else {
                LinkedHashMap<String, Object> value = readDOM4JXml(next);
                map.put(key, value);
            }
        }

        return map;
    }


}
