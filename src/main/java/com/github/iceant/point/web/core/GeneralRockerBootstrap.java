package com.github.iceant.point.web.core;

import com.fizzed.rocker.RenderingException;
import com.fizzed.rocker.RockerModel;
import com.fizzed.rocker.TemplateBindException;
import com.fizzed.rocker.TemplateNotFoundException;
import com.fizzed.rocker.compiler.RockerConfiguration;
import com.fizzed.rocker.compiler.RockerOptions;
import com.fizzed.rocker.compiler.TokenException;
import com.fizzed.rocker.reload.ReloadingRockerBootstrap;
import com.fizzed.rocker.runtime.DefaultRockerBootstrap;
import com.fizzed.rocker.runtime.DefaultRockerModel;
import com.fizzed.rocker.runtime.DefaultRockerTemplate;
import com.fizzed.rocker.runtime.RockerBootstrap;

import java.io.File;

public class GeneralRockerBootstrap implements RockerBootstrap {
    private RockerProperties properties;
    private RockerBootstrap rockerBootstrap;

    private RockerConfiguration makeConfiguration(RockerProperties properties) {
        RockerConfiguration configuration = new RockerConfiguration();
        if (properties.getTemplateDirectory() != null) {
            configuration.setTemplateDirectory(new File(properties.getTemplateDirectory()));
        }

        if (properties.getClassDirectory() != null) {
            configuration.setClassDirectory(new File(properties.getClassDirectory()));
        }

        if (properties.getOutputDirectory() != null) {
            configuration.setOutputDirectory(new File(properties.getOutputDirectory()));
        }

        RockerOptions options = configuration.getOptions();
        options.setDiscardLogicWhitespace(properties.isDiscardLogicWhitespace());
        if (properties.getExtendsClass() != null) {
            options.setExtendsClass(properties.getExtendsClass());
        }

        if (properties.getExtendsModelClass() != null) {
            options.setExtendsModelClass(properties.getExtendsModelClass());
        }

        options.setOptimize(properties.isOptimize());
        options.setTargetCharset(properties.getTargetCharset());

        try {
            options.setJavaVersion(properties.getJavaVersion());
        } catch (TokenException e) {
        }

        if (properties.getPostProcessing() != null && properties.getPostProcessing().length() > 0) {
            options.setPostProcessing(properties.getPostProcessing().split(","));
        }

        configuration.setOptions(options);
        return configuration;
    }

    public GeneralRockerBootstrap(RockerProperties properties) {
        this.properties = properties;
        if (properties.isReloading()) {
            this.rockerBootstrap = new ReloadingRockerBootstrap(this.makeConfiguration(properties));
        } else {
            this.rockerBootstrap = new DefaultRockerBootstrap();
        }
    }

    @Override
    public RockerModel model(String templatePath) throws TemplateNotFoundException, TemplateBindException {
        return rockerBootstrap.model(templatePath);
    }

    @Override
    public DefaultRockerTemplate template(Class aClass, DefaultRockerModel defaultRockerModel) throws RenderingException {
        return rockerBootstrap.template(aClass, defaultRockerModel);
    }
}
