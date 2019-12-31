package com.cetc27.gfg.xml.util;

import com.cetc27.gfg.xml.listener.MarshallerListener;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashMap;


/**
 * 解析xml接口文件工具类
 */
public class JaxbUtil {

    /**
     * 将类转换为xml
     *
     * @param obj
     * @param file
     */
    public static void convertToXml(Object obj, File file) {
        try(StringWriter sw = new StringWriter();
            FileWriter fw = new FileWriter(file)) {

            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setListener(new MarshallerListener());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            sw.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            marshaller.marshal(obj, sw);
            Document document = DocumentHelper.parseText(sw.toString());
            String xml = document.asXML();
            System.out.println(xml);
            fw.write(xml);

        } catch (JAXBException | IOException | DocumentException e) {
            e.printStackTrace();
        }

    }


    /**
     * 将xml转换为类
     * @param file
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T convertToJavaBean(File file, Class<T> clz) {

        return JAXB.unmarshal(file, clz);
    }

    /**
     * 将xml转换为类
     * @param xmlStr
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T convertToJavaBean(String xmlStr, Class<T> clz) {

        StringReader reader = new StringReader(xmlStr);
        return JAXB.unmarshal(reader, clz);
    }

    /**
     * 将xml转为map集合(dom4j)
     * @param file
     * @return
     */
    public static LinkedHashMap<String, Object> convertToMap(File file) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(file);
            Element rootElement = document.getRootElement();
            map = readDOM4JXml(rootElement);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 读取跟节点信息(需要修改)
     * @param rootElement
     * @return
     */
    private static LinkedHashMap<String, Object> readDOM4JXml(Element rootElement) {

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
