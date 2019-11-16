package com.com.backend.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum AbstractType {

    CASE_REPORT("C", "Case Report"),
    RESEARCH("R", "Research");
    private static Map<String, AbstractType> map = new HashMap<>();

    private String type;
    private String desc;

    AbstractType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    static {
        for(AbstractType abstractType: AbstractType.values()){
            map.put(abstractType.type, abstractType);
        }
    }

    public static AbstractType findType(String abstractType){
        return map.get(abstractType);
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
