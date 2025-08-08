package com.opengms.wukai.pojo.Result.NetWork;

import lombok.Data;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Map;

@Data
public class CentralGraph {
    //每个国家属性
    private String AreaName;
    private String AreaID;
    private int Scale;
    //每个国家网络图
    Graph<String, DefaultWeightedEdge> FacilitationGraph;
    Graph<String, DefaultWeightedEdge> InhibitionGraph;

    //每个国家节点特征向量
    Map<String,Double> MapFacilitationCentrality;
    Map<String,Double> MapInhibitionCentrality;

}
