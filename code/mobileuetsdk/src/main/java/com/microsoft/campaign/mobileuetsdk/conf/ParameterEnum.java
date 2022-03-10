package com.microsoft.campaign.mobileuetsdk.conf;

/**
 * Created by yaqi@microsoft.com
 * Description: This Emun is to restrict user write the URL parameter
 */
public enum ParameterEnum {
    LONGITUDE("mlo"),
    LATITUDE("mla"),
    EVENT("mev"),
    DETAIL("mdt");
    private String value;
    ParameterEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }




}
