package com.com.backend.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {

    DO("D"),
    //FORWARDED("F"),
    SEND("S"),
    APPROVED("A"),
    REJECTED("R");

    String statusDesc;

    private static Map<String, Status> map = new HashMap<>();
    static {
        for(Status status: Status.values()){
            map.put(status.statusDesc, status);
        }
    }

    public static Status findStatus(String statusDesc){
        return map.get(statusDesc);
    }

    Status(String status) {
        this.statusDesc = status;
    }

    public String getStatus() {
        return statusDesc;
    }
}
