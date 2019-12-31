package com.cetc27.gfg.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String,Date> {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");

    @Override
    public Date unmarshal(String v) throws Exception {
        if (!v.contains(".")){
            v+=".000";
        }
        return sdf.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {

        return sdf.format(v);
    }
}
