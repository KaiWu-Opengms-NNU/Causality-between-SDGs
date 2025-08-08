package com.opengms.wukai.pojo.Result.NetWork;


import lombok.Data;

import java.util.List;

@Data
public class CountryNandR {
    private int countryID;
    private String countryName;
    private List<NetRelationship> relationshipList;
    private List<NetNode> nodeList;
}
