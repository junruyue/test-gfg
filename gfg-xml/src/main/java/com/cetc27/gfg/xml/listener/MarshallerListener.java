package com.cetc27.gfg.xml.listener;

import javax.xml.bind.Marshaller;
import java.lang.reflect.Field;

public class MarshallerListener extends Marshaller.Listener {

    @Override
    public void beforeMarshal(Object source) {
        //获取所有属性
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getType() == String.class && field.get(source) == null) {
                    field.set(source,"");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
