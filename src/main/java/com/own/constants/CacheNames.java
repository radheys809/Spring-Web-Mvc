package com.own.constants;

public enum CacheNames {
    /**
     * this cache name denotes all the user lis
     */
    ALLUSER("alluserscache"),
    USERNAME("user-cache"),
    GROUPS("group");

    private String value;

    CacheNames(String value) {
        this.value = value;
        Name = this.value;
    }

    public String Name;

}
