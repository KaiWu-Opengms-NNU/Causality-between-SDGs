package com.opengms.wukai.pojo.Result.Spatial;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArcmapForNetworkByCountry {
    private String AreaName;
    private String AreaAb;
    private int AreaID;
    private int Scale;

    //国家属性记录
    private int GeoRegionID;//上层尺度属性属性
    private String Economic;

    //SDGs优先性 FP,NP,IP,FPIP
    //SDGs优先性的变化 Same,Change
    private String SDG1; //FP_Same
    private String SDG2; //FP_Change
    private String SDG3;
    private String SDG4;
    private String SDG5;
    private String SDG6;
    private String SDG7;
    private String SDG8;
    private String SDG9;
    private String SDG10;
    private String SDG11;
    private String SDG12;
    private String SDG13;
    private String SDG14;
    private String SDG15;
    private String SDG16;
    private String SDG17;

    private int NumberOfFPSame;
    private int NumberOfIPSame;

    public void InitClass(int AreaID,List<ArcmapForNetwork> NetworkList)
    {
        for (ArcmapForNetwork Country : NetworkList)
        {
            if(AreaID==Country.getAreaID()){
                this.AreaName=Country.getAreaName();
                this.AreaAb=Country.getAreaAb();
                this.AreaName=Country.getAreaName();
                this.AreaID=Country.getAreaID();
                this.Scale=Country.getScale();
                this.GeoRegionID=Country.getGeoRegionID();
                this.Economic=Country.getEconomic();
                break;
            }
        }
    }

    public String JudgeSDGTypeAndChange(String SDGNo,int GeoParentID,List<ArcmapForNetwork> SDGList)
    {
        String JudgeResult="Null";
        //获取这个国家的该目标的优先性
        List<ArcmapForNetwork> ThisCountry=new ArrayList<ArcmapForNetwork>();
        for(ArcmapForNetwork Country : SDGList)
        {
            if(Country.getAreaID()==this.AreaID&&Country.getGoal().equals(SDGNo)&&Country.getPrioritize()!=null)
            {
                ThisCountry.add(Country);
            }
        }
        int TagFacilitation=0;
        int TagInhibition=0;
        if(ThisCountry.size()>0)
        {
            for(ArcmapForNetwork Country : ThisCountry)
            {
                if(Country.getPrioritize().equals("FP"))
                {
                    TagFacilitation=1;
                }
                if(Country.getPrioritize().equals("IP"))
                {
                    TagInhibition=1;
                }
            }
            if(TagFacilitation==1&&TagInhibition==1)
            {
                JudgeResult="FPIP";
            }
            if(TagFacilitation==1&&TagInhibition==0)
            {
                JudgeResult="FP";
            }
            if(TagFacilitation==0&&TagInhibition==1)
            {
                JudgeResult="IP";
            }
            if(TagFacilitation==0&&TagInhibition==0)
            {
                JudgeResult="NP";
            }
        }
        //获取这个国家对应父级区域的该目标的优先性
        List<ArcmapForNetwork> ThisCountryParent=new ArrayList<ArcmapForNetwork>();
        for(ArcmapForNetwork Country : SDGList)
        {
            if(Country.getAreaID()==GeoParentID&&Country.getGoal().equals(SDGNo)&&Country.getPrioritize()!=null)
            {
                ThisCountryParent.add(Country);
            }
        }
        TagFacilitation=0;
        TagInhibition=0;
        String ParentSDGState="Null";
        if(ThisCountryParent.size()>0)
        {
            for(ArcmapForNetwork Country : ThisCountryParent)
            {
                if(Country.getPrioritize().equals("FP"))
                {
                    TagFacilitation=1;
                }
                if(Country.getPrioritize().equals("IP"))
                {
                    TagInhibition=1;
                }
            }
            if(TagFacilitation==1&&TagInhibition==1)
            {
                ParentSDGState="FPIP";
            }
            if(TagFacilitation==1&&TagInhibition==0)
            {
                ParentSDGState="FP";
            }
            if(TagFacilitation==0&&TagInhibition==1)
            {
                ParentSDGState="IP";
            }
            if(TagFacilitation==0&&TagInhibition==0)
            {
                ParentSDGState="NP";
            }

        }
        if(ThisCountry.size()>0&&ThisCountryParent.size()>0)
        {
            if(JudgeResult.equals(ParentSDGState))
            {
                JudgeResult=JudgeResult+"_Same";
            }else
            {
                JudgeResult=JudgeResult+"_Change";
            }
        }else if(ThisCountry.size()>0&&ThisCountryParent.size()==0)
        {
            JudgeResult=JudgeResult;
        }
        else
        {
            JudgeResult="Null";
        }
        return JudgeResult;
    }

    public void LoadSame()
    {
        int TempFP=0;
        int TempIP=0;
        if(this.SDG1.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG1.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG1.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}


        if(this.SDG2.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG2.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG2.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG3.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG3.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG3.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG4.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG4.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG4.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG5.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG5.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG5.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG6.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG6.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG6.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG7.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG7.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG7.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG8.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG8.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG8.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG9.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG9.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG9.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG10.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG10.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG10.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG11.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG11.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG11.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG12.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG12.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG12.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG13.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG13.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG13.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG14.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG14.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG14.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG15.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG15.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG15.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG16.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG16.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG16.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        if(this.SDG17.equals("FP_Same"))
        {
            TempFP++;
        }else if(this.SDG17.equals("IP_Same"))
        {
            TempIP++;
        }else if(this.SDG17.equals("FPIP_Same"))
        {
            TempFP++;
            TempIP++;
        }else{}

        this.NumberOfFPSame=TempFP;
        this.NumberOfIPSame=TempIP;
    }

    public void LoadDifferent()
    {
        int TempFP=0;
        int TempIP=0;
        if(this.SDG1.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG1.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG1.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG2.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG2.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG2.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG3.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG3.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG3.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG4.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG4.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG4.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG5.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG5.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG5.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG6.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG6.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG6.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG7.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG7.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG7.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG8.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG8.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG8.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG9.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG9.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG9.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG10.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG10.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG10.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG11.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG11.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG11.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG12.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG12.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG12.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG13.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG13.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG13.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG14.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG14.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG14.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG15.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG15.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG15.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG16.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG16.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG16.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        if(this.SDG17.equals("FP_Change"))
        {
            TempFP++;
        }else if(this.SDG17.equals("IP_Change"))
        {
            TempIP++;
        }else if(this.SDG17.equals("FPIP_Change"))
        {
            TempFP++;
            TempIP++;
        }else{}
        this.NumberOfFPSame=TempFP;
        this.NumberOfIPSame=TempIP;
    }
}
