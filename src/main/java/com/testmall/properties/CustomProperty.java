package com.testmall.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom")
public class CustomProperty {
    public static String osType;

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public CustomProperty() {
    }
    public CustomProperty(String osType) {
        this.osType = osType;
    }
}
