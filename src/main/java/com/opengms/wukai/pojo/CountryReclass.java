package com.opengms.wukai.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CountryReclass {
    private String geoAreaName;
    private String geoAreaShort;
    private String geoAreaCodeID;

    private String geoIncome;
    private String geoTag;
    private String geoParentRegionI;
    private String geoParentRegionII;
    private String geoParentRegionIII;

    private Map<String,String> AttributeCountry=new HashMap<String,String>();



}
