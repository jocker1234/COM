package com.com.backend.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Role {

    ROLE_ADMIN("Admin"),
    ROLE_ACTIVE_PARTICIPANT("Active Participant"),
    ROLE_PASSIVE_PARTICIPANT("Passive Participant");

    private String desc;

    Role(String desc) {
        this.desc = desc;
    }

    private static Map<String, Role> map = new HashMap<>();
    static {
        for(Role role: Role.values()){
            map.put(role.desc, role);
        }
    }

    public static Role findRole(String desc){
        return map.get(desc);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
