package com.opengms.wukai.pojo.Result.Spatial;

import lombok.Data;

@Data
public class ArcmapForNetwork {

    private String AreaName;
    private String AreaAb;
    private int AreaID;
    private int Scale;
    private String Goal;
    private double GraphWeight;
    private String Type;

    //国家属性记录
    private int GeoRegionID;
    private String Economic;
    private String Population;

    //上层尺度
    private String Prioritize;//FP,NP,IP  PrioritizeTag
    private String RegionalPrioritize;
    private String GlobalPrioritize;
    private String GlobalToNationalPrioritize;//仅有Change和UnChange
    private String RegionToNationalPrioritize;

    //记录与上层属性的不一致
    int GN_FacilitatingConsistency=0;
    int GN_InhibitingConsistency=0;
    int RN_FacilitatingConsistency=0;
    int RN_InhibitingConsistency=0;


}
