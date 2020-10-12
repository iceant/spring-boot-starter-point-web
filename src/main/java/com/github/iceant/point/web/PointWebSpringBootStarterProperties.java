package com.github.iceant.point.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.point.web")
public class PointWebSpringBootStarterProperties {
    private String fragmentPrefix = "fragment/";
    private String fragmentPath = "classpath:fragments/";

    public String getFragmentPrefix() {
        return fragmentPrefix;
    }

    public void setFragmentPrefix(String fragmentPrefix) {
        this.fragmentPrefix = fragmentPrefix;
    }

    public String getFragmentPath() {
        return fragmentPath;
    }

    public void setFragmentPath(String fragmentPath) {
        this.fragmentPath = fragmentPath;
    }
}
