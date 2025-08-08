package com.opengms.wukai.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HaveDataSDG {
    String Name;
    int Scale;
    List<String> HaveDataX_Repeat=new ArrayList<>();
    List<String> HaveDataY_Repeat=new ArrayList<>();
    List<String> HaveDataX_NoRepeat=new ArrayList<>();
    List<String> HaveDataY_NoRepeat=new ArrayList<>();
    double HaveDataX_Percent;
    double HaveDataY_Percent;
    int SDGNumber=0;
    int lackNumber=17;
    double AllNumber=0;

    public void removeDuplicates(){
        if(this.HaveDataX_Repeat.size()>0){
            for (String item : this.HaveDataX_Repeat) {
                if (!this.getHaveDataX_NoRepeat().contains(item)){
                    this.getHaveDataX_NoRepeat().add(item);
                }
            }
            HaveDataX_Percent=100*(17-this.getHaveDataX_NoRepeat().size())/17;
        }else
        {
            HaveDataX_Percent=100;
        }
        if(this.HaveDataY_Repeat.size()>0){
            for (String item : this.HaveDataY_Repeat) {
                if (!this.getHaveDataY_NoRepeat().contains(item)){
                    this.getHaveDataY_NoRepeat().add(item);
                }
            }
            HaveDataY_Percent=100*(17-this.getHaveDataY_NoRepeat().size())/17;
        }else{
            HaveDataY_Percent=100;
        }
        if(this.HaveDataX_NoRepeat.size()>0&&this.HaveDataY_NoRepeat.size()>0)
        {
            AllNumber=this.getHaveDataY_NoRepeat().size()*(this.getHaveDataY_NoRepeat().size()-1);
        }
        this.SDGNumber=this.HaveDataX_NoRepeat.size();
        this.lackNumber=17-this.SDGNumber;
    }
}
