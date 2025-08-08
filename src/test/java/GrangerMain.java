
import JSci.maths.statistics.TDistribution;
import cointegration.RawData;
import cointegration.SimpleLinearRegression;
import com.opengms.wukai.pojo.CountryCode;
import com.opengms.wukai.pojo.CountryRecordIndicator;
import com.opengms.wukai.pojo.CountryRecordResult;
import com.opengms.wukai.pojo.Granger.ADFPojo;
import com.opengms.wukai.pojo.Granger.GrangerPojo;
import com.opengms.wukai.pojo.Granger.GrangerResult;
import com.opengms.wukai.pojo.Granger.GrangerResultGoal;
import com.opengms.wukai.pojo.Indicators.Indicator;
import com.opengms.wukai.pojo.Result.Aggregation;
import com.opengms.wukai.pojo.SingleSeries.YearRecord;
import com.opengms.wukai.tool.OperateExcel;
import com.opengms.wukai.tool.SDGIndicatorTool;
import dro.stat.GrangerCausalIndicator;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**主要程序
 * @Description 5.0版本  将指标放到目标层面上分析
 * @Author  Kai
 * @Date 2022/8/23
 */

//https://unstats.un.org/SDGAPI/swagger/#!/Goal/V1SdgGoalDataGet

public class GrangerMain {
    public static int StartYear=2000;
    public static int EndYear=2022;
    public static int LeastYear=3;//最小年份
    public static int TempScale=1;
    public static void main(String[] args) throws IOException {
        //可持续发展目标分类

        OperateExcel operateExcel=new OperateExcel();
        List<List<String>> RegionAndCountry= operateExcel.readExcel(new File("InputData/NewRegionOfDifferentScale2.xlsx"));
        List<List<String>> indicatorResultByUsing=operateExcel.readExcel2(new File("InputData/IndicatorOnlyOne20250413.xls"));


        //17个指标国家数据
        List<List<String>> SDG1=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal1.xlsx"));
        List<List<String>> SDG2=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal2.xlsx"));
        List<List<String>> SDG3=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal3.xlsx"));
        List<List<String>> SDG4=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal4.xlsx"));
        List<List<String>> SDG5=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal5.xlsx"));
        List<List<String>> SDG6=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal6.xlsx"));
        List<List<String>> SDG7=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal7.xlsx"));
        List<List<String>> SDG8=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal8.xlsx"));
        SDG8=operateExcel.AddExcel(SDG8,new File("InputData/CountryForSDG/Goal8_2.xlsx"));
        List<List<String>> SDG9=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal9.xlsx"));
        List<List<String>> SDG10=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal10.xlsx"));
        List<List<String>> SDG11=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal11.xlsx"));
        List<List<String>> SDG12=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal12.xlsx"));
        SDG12=operateExcel.AddExcel(SDG12,new File("InputData/CountryForSDG/Goal12_2.xlsx"));
        List<List<String>> SDG13=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal13.xlsx"));
        List<List<String>> SDG14=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal14.xlsx"));
        List<List<String>> SDG15=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal15.xlsx"));
        List<List<String>> SDG16=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal16.xlsx"));
        List<List<String>> SDG17=operateExcel.readExcel(new File("InputData/CountryForSDG/Goal17.xlsx"));
        SDG17=operateExcel.AddExcel(SDG17,new File("InputData/CountryForSDG/Goal17_2.xlsx"));
        //17个指标国分区数据
        List<List<String>> SDG1R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal1.xlsx"));
        List<List<String>> SDG2R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal2.xlsx"));
        List<List<String>> SDG3R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal3.xlsx"));
        List<List<String>> SDG4R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal4.xlsx"));
        List<List<String>> SDG5R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal5.xlsx"));
        List<List<String>> SDG6R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal6.xlsx"));
        List<List<String>> SDG7R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal7.xlsx"));
        List<List<String>> SDG8R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal8.xlsx"));
        List<List<String>> SDG9R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal9.xlsx"));
        List<List<String>> SDG10R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal10.xlsx"));
        List<List<String>> SDG11R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal11.xlsx"));
        List<List<String>> SDG12R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal12.xlsx"));
        List<List<String>> SDG13R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal13.xlsx"));
        List<List<String>> SDG14R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal14.xlsx"));
        List<List<String>> SDG15R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal15.xlsx"));
        List<List<String>> SDG16R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal16.xlsx"));
        List<List<String>> SDG17R=operateExcel.readExcel(new File("InputData/RegionForSDG/Goal17.xlsx"));


        //首先载入不同的国家和地区
        List<CountryCode> WorldCountryList=new ArrayList<>();
        for(int i=1;i<RegionAndCountry.size();i++){
            CountryCode countryCode=new CountryCode();
            countryCode.setGeoAreaName(RegionAndCountry.get(i).get(6));
            countryCode.setGeoAreaShort(RegionAndCountry.get(i).get(1));
            countryCode.setGeoAreaCodeID(RegionAndCountry.get(i).get(2));
            countryCode.setGeoAreaScale(RegionAndCountry.get(i).get(3));
            countryCode.setGeoParentRegion(RegionAndCountry.get(i).get(4));
            countryCode.setGeoParentID(RegionAndCountry.get(i).get(5));
            WorldCountryList.add(countryCode);
        }
        List<Indicator> indicatorList=new ArrayList<>();
        for(int i=1;i<indicatorResultByUsing.size();i++){
            Indicator indicator=new Indicator();
            indicator.setGoal(indicatorResultByUsing.get(i).get(0));
            indicator.setTarget(indicatorResultByUsing.get(i).get(1));
            indicator.setCode(indicatorResultByUsing.get(i).get(2));
            indicator.setSeries(indicatorResultByUsing.get(i).get(3));
            indicator.setDescription(indicatorResultByUsing.get(i).get(4));
            indicator.setDirection(indicatorResultByUsing.get(i).get(5));
            indicatorList.add(indicator);
        }
        //载入数据
        List<CountryRecordIndicator> countryRecordList=new ArrayList<>();


        for(int i=0;i<WorldCountryList.size();i++){
            //先从国家层面载入数据
            System.out.println(i);
            if(WorldCountryList.get(i).getGeoAreaScale().equals("3")){
                CountryRecordIndicator OneDataOfCountry= new CountryRecordIndicator();
                //载入国家属性
                OneDataOfCountry.setGeoAreaID(WorldCountryList.get(i).getGeoAreaCodeID());
                OneDataOfCountry.setGeoAreaName(WorldCountryList.get(i).getGeoAreaName());
                OneDataOfCountry.setGeaAreaParentID(WorldCountryList.get(i).getGeoParentID());
                OneDataOfCountry.setGeaAreaScale(WorldCountryList.get(i).getGeoAreaScale());
                //载入指标属性
                for(int j=0;j<indicatorList.size();j++){
                    OneDataOfCountry.getGoals().add(indicatorList.get(j).getGoal());
                    OneDataOfCountry.getIndicator().add(indicatorList.get(j).getCode());
                    OneDataOfCountry.getSeriesCode().add(indicatorList.get(j).getSeries());
                    OneDataOfCountry.getDirection().add(indicatorList.get(j).getDirection());
                }
                //载入指标数据和单位
                OneDataOfCountry.LoadData(SDG1,SDG2,SDG3,SDG4,SDG5,SDG6,SDG7,SDG8,SDG9,SDG10,SDG11,SDG12,SDG13,SDG14,SDG15,SDG16,SDG17,StartYear,EndYear);
                countryRecordList.add(OneDataOfCountry);
            }else {
                CountryRecordIndicator OneDataOfCountry= new CountryRecordIndicator();
                //载入国家属性
                OneDataOfCountry.setGeoAreaID(WorldCountryList.get(i).getGeoAreaCodeID());
                OneDataOfCountry.setGeoAreaName(WorldCountryList.get(i).getGeoAreaName());
                OneDataOfCountry.setGeaAreaParentID(WorldCountryList.get(i).getGeoParentID());
                OneDataOfCountry.setGeaAreaScale(WorldCountryList.get(i).getGeoAreaScale());
                //载入指标属性
                for(int j=0;j<indicatorList.size();j++){
                    OneDataOfCountry.getGoals().add(indicatorList.get(j).getGoal());
                    OneDataOfCountry.getIndicator().add(indicatorList.get(j).getCode());
                    OneDataOfCountry.getSeriesCode().add(indicatorList.get(j).getSeries());
                    OneDataOfCountry.getDirection().add(indicatorList.get(j).getDirection());
                }
                //载入指标数据和单位
                OneDataOfCountry.LoadData(SDG1R,SDG2R,SDG3R,SDG4R,SDG5R,SDG6R,SDG7R,SDG8R,SDG9R,SDG10R,SDG11R,SDG12R,SDG13R,SDG14R,SDG15R,SDG16R,SDG17R,StartYear,EndYear);
                countryRecordList.add(OneDataOfCountry);
            }
        }
        System.out.println("success");

        //总的表格
        List<Aggregation> aggregationList=new ArrayList<>();
        //
        for(int i=0;i<countryRecordList.size();i++){
            //先初始化一个国家对象用于存储读取数据
            Aggregation aggregation=new Aggregation();

            aggregation.setCountryCode(countryRecordList.get(i).getGeoAreaID());
            aggregation.setCountryName(countryRecordList.get(i).getGeoAreaName());

            CountryRecordIndicator countryRecordI= countryRecordList.get(i);
            countryRecordI.ConvertOutput();
            operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_Data", CountryRecordResult.class,countryRecordI.getRecordResults());
            //单位根ADF检验,可以先新建一个通过ADF检验的列表
            List<ADFPojo> ADFPojoList=new ArrayList<>();
            List<RawData> rawDataList=new ArrayList<>();
            for(int SeriesCode=countryRecordI.getSeriesCode().size()-1;SeriesCode>=0;SeriesCode--){
                //取出一个元素进行分析
                List<YearRecord> SeriesX=countryRecordI.getData().get(SeriesCode);

                //初始化原始数据

                //这里应该用Try和catch 因为会出现<100的情况
                try {
                    //如果样本少于多少年就可以进行去除
                    if(SeriesX.size()<LeastYear){
                        countryRecordI.removeData(SeriesCode);
                        continue;
                    }
                    double [] TimeX= new double[SeriesX.size()];
                    double [] ValueX=new double[SeriesX.size()];
                    for(int tempX=0;tempX<SeriesX.size();tempX++){
                        String tempYear=SeriesX.get(tempX).getYear();
                        TimeX[tempX]=Double.parseDouble(tempYear);
                        ValueX[tempX]=Double.parseDouble(SeriesX.get(tempX).getValue());
                    }
                    //记录原始数据
                    RawData  rawData=new RawData(StartYear,EndYear,
                            countryRecordI.getGoals().get(SeriesCode),
                            countryRecordI.getIndicator().get(SeriesCode),
                            countryRecordI.getSeriesCode().get(SeriesCode)
                            ,TimeX,ValueX);
                    ADFPojo adfPojo=new ADFPojo(StartYear,EndYear,
                            countryRecordI.getGoals().get(SeriesCode),
                            countryRecordI.getIndicator().get(SeriesCode),
                            countryRecordI.getSeriesCode().get(SeriesCode)
                            ,TimeX,ValueX,countryRecordI.getDirection().get(SeriesCode));
                    adfPojo.SelectTimeScale(TempScale);
                    if(adfPojo.ADFJudge(adfPojo.getValue())==false){
                        countryRecordI.removeData(SeriesCode);
                        continue;
                    }else{
                        //说明通过了ADF检验
                        rawDataList.add(rawData);
                        ADFPojoList.add(adfPojo);
                    }
                }catch (Exception e){
                    //出现数据转化成double类型失败 照样将其移除
                    countryRecordI.removeData(SeriesCode);
                }
            }
            System.out.println("-------------");
            System.out.println("通过ADF检验的指标个数为:"+countryRecordI.getSeriesCode().size());
            System.out.println("通过ADF检验的指标个数为:"+ADFPojoList.size());
            //接着做Granger因果分析,先新建指标链表存贮数据
            List<GrangerResult> grangerResultList=new ArrayList<>();//六种作用关系求和
            List<GrangerResult> NeutralInteraction=new ArrayList<>();
            for(int SeriesCodeX=0;SeriesCodeX<ADFPojoList.size();SeriesCodeX++){
                System.out.println(ADFPojoList.get(SeriesCodeX));
                // 开始时间
                for (int SeriesCodeY=0;SeriesCodeY<ADFPojoList.size();SeriesCodeY++){
                    //如果是本身就跳过
                    //if(SeriesCodeX==SeriesCodeY||ADFPojoList.get(SeriesCodeX).getGoal().equals(ADFPojoList.get(SeriesCodeY).getGoal())){
                    if(SeriesCodeX==SeriesCodeY||ADFPojoList.get(SeriesCodeX).getGoal().equals(ADFPojoList.get(SeriesCodeY).getGoal())){
                        //不是同一个指标 但是是同一个目标
                        if(SeriesCodeX!=SeriesCodeY&&ADFPojoList.get(SeriesCodeX).getGoal().equals(ADFPojoList.get(SeriesCodeY).getGoal())){

                        }
                    }else{
                        double [] TimeX=ADFPojoList.get(SeriesCodeX).getTime();
                        double [] ValueX=ADFPojoList.get(SeriesCodeX).getValue();
                        double [] TimeY=ADFPojoList.get(SeriesCodeY).getTime();
                        double [] ValueY= ADFPojoList.get(SeriesCodeY).getValue();
                        GrangerPojo grangerPojo=new GrangerPojo(
                                ADFPojoList.get(SeriesCodeX).getGoal(),ADFPojoList.get(SeriesCodeY).getGoal(),
                                ADFPojoList.get(SeriesCodeX).getIndicator(),ADFPojoList.get(SeriesCodeY).getIndicator(),
                                ADFPojoList.get(SeriesCodeX).getSeries(),ADFPojoList.get(SeriesCodeY).getSeries(),
                                TimeX,TimeY,ValueX,ValueY,
                                ADFPojoList.get(SeriesCodeX).getADFFTest(),ADFPojoList.get(SeriesCodeX).getADFPValue(),ADFPojoList.get(SeriesCodeY).getADFFTest(),ADFPojoList.get(SeriesCodeY).getADFPValue(),
                                ADFPojoList.get(SeriesCodeX).getOrder(),ADFPojoList.get(SeriesCodeY).getOrder(),ADFPojoList.get(SeriesCodeX).getDirection(),ADFPojoList.get(SeriesCodeY).getDirection());
                        GrangerCausalIndicator result ;
                        try{
                            double TempPValue=1;
                            result=grangerPojo.DoGrangerCasual(grangerPojo.getValueY(),grangerPojo.getValueX(),1);
                            for(int Lag=1;Lag<=5;Lag++)
                            {
                                GrangerCausalIndicator TempResult=grangerPojo.DoGrangerCasual(grangerPojo.getValueY(),grangerPojo.getValueX(),Lag);
                                if(TempResult.getPValue()<TempPValue)
                                {
                                    TempPValue=TempResult.getPValue();
                                    result=TempResult;
                                }
                            }
                            GrangerResult grangerResult=new GrangerResult(result.getLagSize(),grangerPojo.getGoalX(),grangerPojo.getIndicatorX(),grangerPojo.getStringCodeX(),grangerPojo.getADFFTestX(),grangerPojo.getADFPValueX(),
                                    grangerPojo.getGoalY(),grangerPojo.getIndicatorY(),grangerPojo.getStringCodeY(),grangerPojo.getADFFTestY(),grangerPojo.getADFPValueY(),
                                    result.getPValue(),result.getH1Parameters(), grangerPojo.getOrderX(),grangerPojo.getOrderY(),grangerPojo.getDirectionX(),grangerPojo.getDirectionY());
                            //加入中性作用
                            if(result.getPValue()<=0.05&&result.getPValue()>=0){
                                grangerResultList.add(grangerResult);
                            }else{
                                NeutralInteraction.add(grangerResult);
                            }
                        }catch (Exception e)
                        {
                            System.out.println(grangerPojo.toString());
                        }
                    }
                }
            }


            //这时候就要分类了,判断指标见的方向关系
            SDGIndicatorTool Tools=new SDGIndicatorTool();
            //计算回归系数及R方
            for(int Li=0;Li<grangerResultList.size();Li++){
                //在使用斯皮尔曼系数，先获取延迟
                int LagSize=grangerResultList.get(Li).getLagSize();
                //在原始序列中找到原始数据
                String StringCodeX=grangerResultList.get(Li).getStringCodeX();
                double[] CodeDataX=Tools.GetRawData(rawDataList,StringCodeX);
                String StringCodeY=grangerResultList.get(Li).getStringCodeY();
                double[] CodeDataY=Tools.GetRawData(rawDataList,StringCodeY);
                //根据Lagsize重新构建数据
                double [] CodeDataXNewData=new double[CodeDataX.length-LagSize];
                double [] CodeDataYNewData=new double[CodeDataY.length-LagSize];
                for(int Ni=0;Ni<CodeDataX.length-LagSize;Ni++)
                {
                    CodeDataXNewData[Ni]=CodeDataX[Ni];
                    CodeDataYNewData[Ni]=CodeDataY[LagSize+Ni];
                }
                PearsonsCorrelation P=new PearsonsCorrelation();
                //SpearmansCorrelation P=new SpearmansCorrelation();
                double R=P.correlation(CodeDataXNewData,CodeDataYNewData);
                double Up=CodeDataXNewData.length-2;
                double Down=1-(R*R);
                double T=R*Math.sqrt(Up/Down);
                TDistribution td=new TDistribution(CodeDataXNewData.length-2);
                double cumulative=td.cumulative(T);
                double PValue=T>0?1-cumulative:cumulative;
                grangerResultList.get(Li).setStrengthXY(R);
                grangerResultList.get(Li).setLag(LagSize);
                grangerResultList.get(Li).setPvalue(PValue);
            }
            for(int Li=grangerResultList.size()-1;Li>=0;Li--){
                if(grangerResultList.get(Li).getPvalue()>0.05||grangerResultList.get(Li).getPvalue()<0){
                    NeutralInteraction.add(grangerResultList.get(Li));
                    grangerResultList.remove(grangerResultList.get(Li));
                }
            }
            if(grangerResultList.size()>=1){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_RawGranger",GrangerResult.class,grangerResultList);
            }



            //判断每种目标之间的主要交互作用
            List<GrangerResultGoal> grangerResultGoals=new ArrayList<>();

            for(int SDGa=1;SDGa<=17;SDGa++){
                for(int SDGb=1;SDGb<=17;SDGb++){
                    if(SDGa==SDGb){
                        continue;
                    }
                    //找到目标中的主要作用
                    List<GrangerResult> TempComparedList=new ArrayList<>();
                    for(int Li=0;Li<grangerResultList.size();Li++){
                        if((Integer.parseInt(grangerResultList.get(Li).getGoalX())==SDGa)&&(Integer.parseInt(grangerResultList.get(Li).getGoalY())==SDGb)){
                            TempComparedList.add(grangerResultList.get(Li));
                        }
                    }
                    //进行对比
                    String IndicatorInteraction="NoInteraction";
                    double Indicatorpercent=0;
                    double StrengthFacilitation=0;
                    double StrengthInhibition=0;
                    if(TempComparedList.size()>0){
                        //得到每种作用类型的强度
                        for(int Li=0;Li<TempComparedList.size();Li++){
                            if(TempComparedList.get(Li).getStrengthXY()<0){
                                StrengthInhibition=StrengthInhibition+TempComparedList.get(Li).getStrengthXY();
                            }else{
                                StrengthFacilitation=StrengthFacilitation+TempComparedList.get(Li).getStrengthXY();
                            }
                        }
                        //对比得到每种目标对的主要交互作用
                        if(StrengthFacilitation>0&&Math.abs(StrengthFacilitation)>Math.abs(StrengthInhibition)){
                            IndicatorInteraction="Facilitation";
                            Indicatorpercent=Math.abs(StrengthFacilitation)/(Math.abs(StrengthFacilitation)+Math.abs(StrengthInhibition));
                        }
                        if(StrengthInhibition<0&&Math.abs(StrengthFacilitation)<Math.abs(StrengthInhibition)){
                            IndicatorInteraction="Inhibition" ;
                            Indicatorpercent=Math.abs(StrengthInhibition)/(Math.abs(StrengthFacilitation)+Math.abs(StrengthInhibition));
                        }
                        //主要交互作用载入列表
                        if(IndicatorInteraction.equals("Facilitation")){
                            for (int Li=0;Li<TempComparedList.size();Li++){
                                if(TempComparedList.get(Li).getStrengthXY()>0){
                                    //把数据加入链表
                                    GrangerResultGoal grangerResultGoal=new GrangerResultGoal(TempComparedList.get(Li));
                                    grangerResultGoal.setIndicatorInteractionType(IndicatorInteraction);
                                    grangerResultGoal.setIndicatorInteractionPercent(Indicatorpercent);
                                    grangerResultGoals.add(grangerResultGoal);
                                }
                            }
                        }
                        if(IndicatorInteraction.equals("Inhibition")){
                            for (int Li=0;Li<TempComparedList.size();Li++){
                                if(TempComparedList.get(Li).getStrengthXY()<0){
                                    //把数据加入链表
                                    GrangerResultGoal grangerResultGoal=new GrangerResultGoal(TempComparedList.get(Li));
                                    grangerResultGoal.setIndicatorInteractionType(IndicatorInteraction);
                                    grangerResultGoal.setIndicatorInteractionPercent(Indicatorpercent);
                                    grangerResultGoals.add(grangerResultGoal);
                                }
                            }
                        }

                    }
                }
            }
            for(int Li=0;Li<grangerResultGoals.size();Li++){
                String GoalX=grangerResultGoals.get(Li).getGoalX();
                String GoalY=grangerResultGoals.get(Li).getGoalY();
                //找到相反的部分
                int ContraryLi=-1;
                for(int Lj=0;Lj<grangerResultGoals.size();Lj++){
                    String GoalX_C=grangerResultGoals.get(Lj).getGoalX();
                    String GoalY_C=grangerResultGoals.get(Lj).getGoalY();
                    //说明存在相反的目标对
                    if(GoalX.equals(GoalY_C)&&GoalY.equals(GoalX_C)){
                        ContraryLi=Lj;
                        break;
                    }
                }
                //说明是单向作用
                if(ContraryLi==-1){
                    if(grangerResultGoals.get(Li).getIndicatorInteractionType().equals("Facilitation")){
                        grangerResultGoals.get(Li).setGoalInteractionType("SingleFacilitation");
                        grangerResultGoals.get(Li).setGoalInteractionPercent(grangerResultGoals.get(Li).getIndicatorInteractionPercent());
                    }
                    if(grangerResultGoals.get(Li).getIndicatorInteractionType().equals("Inhibition")){
                        grangerResultGoals.get(Li).setGoalInteractionType("SingleInhibition");
                        grangerResultGoals.get(Li).setGoalInteractionPercent(grangerResultGoals.get(Li).getIndicatorInteractionPercent());
                    }
                }else {
                    if (grangerResultGoals.get(Li).getIndicatorInteractionType().equals(grangerResultGoals.get(ContraryLi).getIndicatorInteractionType())){
                        if (grangerResultGoals.get(Li).getIndicatorInteractionType().equals("Facilitation")){
                            grangerResultGoals.get(Li).setGoalInteractionType("MutualFacilitation");
                            grangerResultGoals.get(Li).setGoalInteractionPercent(grangerResultGoals.get(Li).getIndicatorInteractionPercent()*grangerResultGoals.get(ContraryLi).getIndicatorInteractionPercent());
                        }else{
                            grangerResultGoals.get(Li).setGoalInteractionType("MutualInhibition");
                            grangerResultGoals.get(Li).setGoalInteractionPercent(grangerResultGoals.get(Li).getIndicatorInteractionPercent()*grangerResultGoals.get(ContraryLi).getIndicatorInteractionPercent());
                        }
                    }else {
                        if(grangerResultGoals.get(Li).getIndicatorInteractionType().equals("Facilitation")){
                            grangerResultGoals.get(Li).setGoalInteractionType("FacilitationToInhibition");
                            grangerResultGoals.get(Li).setGoalInteractionPercent(grangerResultGoals.get(Li).getIndicatorInteractionPercent()*grangerResultGoals.get(ContraryLi).getIndicatorInteractionPercent());
                        }else{
                            grangerResultGoals.get(Li).setGoalInteractionType("InhibitionToFacilitation");
                            grangerResultGoals.get(Li).setGoalInteractionPercent(grangerResultGoals.get(Li).getIndicatorInteractionPercent()*grangerResultGoals.get(ContraryLi).getIndicatorInteractionPercent());
                        }

                    }
                }
            }
            if(grangerResultGoals.size()>=1){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_Granger",GrangerResultGoal.class,grangerResultGoals);
            }
            List<GrangerResultGoal> Indivisible=new ArrayList<>();//不可分割的协同关系
            List<GrangerResultGoal> Cancelling=new ArrayList<>();//相互阻碍的权衡关系
            List<GrangerResultGoal> ReinforcingOnly=new ArrayList<>();//单向的加强关系
            List<GrangerResultGoal> CounteractingOnly=new ArrayList<>();//单向的制约关系
            List<GrangerResultGoal> FacilitationToInhibition =new ArrayList<>();//先促进后抑制
            List<GrangerResultGoal> InhibitionToFacilitation =new ArrayList<>();//先抑制后促进


            for(int Li=0;Li<grangerResultGoals.size();Li++){
                String Interaction=grangerResultGoals.get(Li).getGoalInteractionType();
                switch (Interaction){
                    case "MutualFacilitation":
                        Indivisible.add(grangerResultGoals.get(Li));
                        break;
                    case "MutualInhibition":
                        Cancelling.add(grangerResultGoals.get(Li));
                        break;
                    case "SingleFacilitation" :
                        ReinforcingOnly.add(grangerResultGoals.get(Li));
                        break;
                    case "SingleInhibition":
                        CounteractingOnly.add(grangerResultGoals.get(Li));
                        break;
                    case "FacilitationToInhibition":
                        FacilitationToInhibition.add(grangerResultGoals.get(Li));
                        break;
                    case "InhibitionToFacilitation":
                        InhibitionToFacilitation.add(grangerResultGoals.get(Li));
                }

            }
            //remove
            for(int Ni=NeutralInteraction.size()-1;Ni>=0;Ni--){
                int JudgeNi=-1;
                String GoalX=NeutralInteraction.get(Ni).getGoalX();
                String GoalY=NeutralInteraction.get(Ni).getGoalY();
                for(int Gi=0;Gi<grangerResultList.size();Gi++){
                    String GoalX1=grangerResultList.get(Gi).getGoalX();
                    String GoalY1=grangerResultList.get(Gi).getGoalY();
                    if(GoalX.equals(GoalX1)&&GoalY.equals(GoalY1)){
                        JudgeNi=1;
                    }
                }
                if(JudgeNi==1){
                    NeutralInteraction.remove(NeutralInteraction.get(Ni));
                }
            }


            //输出七个列表，还需要判断数组长度是否为空
            if(Indivisible.size()>0){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_Indivisible",GrangerResultGoal.class,Indivisible);
                aggregation.setIndivisibleN(Indivisible.size());
            }else{
                aggregation.setIndivisibleN(0);
            }
            if(Cancelling.size()>0){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_Cancelling",GrangerResultGoal.class,Cancelling);
                aggregation.setCancellingN(Cancelling.size());
            }else{
                aggregation.setCancellingN(0);
            }
            if(ReinforcingOnly.size()>0){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_ReinforingOnly",GrangerResultGoal.class,ReinforcingOnly);
                aggregation.setReinforcingOnlyN(ReinforcingOnly.size());
            }else {
                aggregation.setReinforcingOnlyN(0);
            }
            if(CounteractingOnly.size()>0){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_CounteractingOnly",GrangerResultGoal.class,CounteractingOnly);
                aggregation.setCounteractingOnlyN(CounteractingOnly.size());
            }else {
                aggregation.setCounteractingOnlyN(0);
            }
            if(FacilitationToInhibition.size()>0){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_FacilitationToInhibition",GrangerResultGoal.class,FacilitationToInhibition);
                aggregation.setFacilitationToInhibitionN(FacilitationToInhibition.size());
            }else {
                aggregation.setFacilitationToInhibitionN(0);
            }
            if(InhibitionToFacilitation.size()>0){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_InhibitionToFacilitation",GrangerResultGoal.class,InhibitionToFacilitation);
                aggregation.setInhibitionToFacilitationN(InhibitionToFacilitation.size());
            }else {
                aggregation.setInhibitionToFacilitationN(0);
            }
            if (NeutralInteraction.size()>0){
                operateExcel.writeExcel(countryRecordI.getGeoAreaName()+"_NeutralInteraction",GrangerResult.class,NeutralInteraction);
                aggregation.setNeutralInteractionN(NeutralInteraction.size());
            }else{
                aggregation.setNeutralInteractionN(0);
            }

            //记录结果
            //不同国家之间的相互影响如何表征
            aggregationList.add(aggregation);
            countryRecordI.Clear();
            ADFPojoList.clear();
            grangerResultList.clear();
            operateExcel.writeExcel("WorldRecord",Aggregation.class,aggregationList);
            System.out.println("一条国家数据写入成功");
        }
    }


}