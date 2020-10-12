package com.github.iceant.point.web.core;

import com.fizzed.rocker.runtime.RockerBootstrap;
import com.fizzed.rocker.runtime.StringBuilderOutput;

public class RockerUtil {
    public static RockerBootstrap getRockerBootstrap(RockerProperties properties) {
        return new GeneralRockerBootstrap(properties);
    }

    public static RockerBootstrap getRockerBootstrap() {
        RockerProperties properties = new RockerProperties();
        properties.setTemplateDirectory("src/main/resources/fragments");
        return new GeneralRockerBootstrap(properties);
    }

    public static String render(RockerBootstrap bootstrap, String templatePath) {
        return bootstrap.model(templatePath).render(StringBuilderOutput.FACTORY).toString();
    }
}
