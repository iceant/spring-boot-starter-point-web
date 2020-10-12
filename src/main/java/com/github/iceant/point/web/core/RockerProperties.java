package com.github.iceant.point.web.core;

public class RockerProperties {
    private String templateDirectory = "src/main/resources/templates";
    private String outputDirectory;
    private String classDirectory;
    private boolean failOnError = true;
    private boolean skip = false;
    private String touchFile;
    private boolean skipTouch = false;
    private boolean addAsSources = true;
    private boolean addAsTestSources = false;
    private String javaVersion = "1.8";
    private boolean optimize = false;
    private String extendsClass;
    private String extendsModelClass;
    private boolean discardLogicWhitespace = false;
    private String targetCharset = "UTF-8";
    private String suffixRegex;
    private String postProcessing;
    private boolean enabled = true;
    private boolean reloading = true;
    private int templateResolverOrder = 2147483637;
    private String prefix = "classpath:/templates/";
    private String suffix = ".rocker.html";
    private String contentType = "text/html; charset=utf-8";
    private boolean exposeRequestAttributes = false;
    private boolean allowRequestOverride = false;
    private boolean exposeSessionAttributes = false;
    private boolean allowSessionOverride = false;
    private boolean exposeSpringMacroHelpers = true;

    public RockerProperties() {
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTemplateDirectory() {
        return this.templateDirectory;
    }

    public void setTemplateDirectory(String templateDirectory) {
        this.templateDirectory = templateDirectory;
    }

    public String getOutputDirectory() {
        return this.outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getClassDirectory() {
        return this.classDirectory;
    }

    public void setClassDirectory(String classDirectory) {
        this.classDirectory = classDirectory;
    }

    public boolean isFailOnError() {
        return this.failOnError;
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public boolean isSkip() {
        return this.skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String getTouchFile() {
        return this.touchFile;
    }

    public void setTouchFile(String touchFile) {
        this.touchFile = touchFile;
    }

    public boolean isSkipTouch() {
        return this.skipTouch;
    }

    public void setSkipTouch(boolean skipTouch) {
        this.skipTouch = skipTouch;
    }

    public boolean isAddAsSources() {
        return this.addAsSources;
    }

    public void setAddAsSources(boolean addAsSources) {
        this.addAsSources = addAsSources;
    }

    public boolean isAddAsTestSources() {
        return this.addAsTestSources;
    }

    public void setAddAsTestSources(boolean addAsTestSources) {
        this.addAsTestSources = addAsTestSources;
    }

    public String getJavaVersion() {
        return this.javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public boolean isOptimize() {
        return this.optimize;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }

    public String getExtendsClass() {
        return this.extendsClass;
    }

    public void setExtendsClass(String extendsClass) {
        this.extendsClass = extendsClass;
    }

    public String getExtendsModelClass() {
        return this.extendsModelClass;
    }

    public void setExtendsModelClass(String extendsModelClass) {
        this.extendsModelClass = extendsModelClass;
    }

    public boolean isDiscardLogicWhitespace() {
        return this.discardLogicWhitespace;
    }

    public void setDiscardLogicWhitespace(boolean discardLogicWhitespace) {
        this.discardLogicWhitespace = discardLogicWhitespace;
    }

    public String getTargetCharset() {
        return this.targetCharset;
    }

    public void setTargetCharset(String targetCharset) {
        this.targetCharset = targetCharset;
    }

    public String getSuffixRegex() {
        return this.suffixRegex;
    }

    public void setSuffixRegex(String suffixRegex) {
        this.suffixRegex = suffixRegex;
    }

    public boolean isReloading() {
        return this.reloading;
    }

    public void setReloading(boolean reloading) {
        this.reloading = reloading;
    }

    public int getTemplateResolverOrder() {
        return this.templateResolverOrder;
    }

    public void setTemplateResolverOrder(int templateResolverOrder) {
        this.templateResolverOrder = templateResolverOrder;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isExposeRequestAttributes() {
        return this.exposeRequestAttributes;
    }

    public void setExposeRequestAttributes(boolean exposeRequestAttributes) {
        this.exposeRequestAttributes = exposeRequestAttributes;
    }

    public boolean isExposeSessionAttributes() {
        return this.exposeSessionAttributes;
    }

    public void setExposeSessionAttributes(boolean exposeSessionAttributes) {
        this.exposeSessionAttributes = exposeSessionAttributes;
    }

    public boolean isExposeSpringMacroHelpers() {
        return this.exposeSpringMacroHelpers;
    }

    public void setExposeSpringMacroHelpers(boolean exposeSpringMacroHelpers) {
        this.exposeSpringMacroHelpers = exposeSpringMacroHelpers;
    }

    public boolean isAllowRequestOverride() {
        return this.allowRequestOverride;
    }

    public void setAllowRequestOverride(boolean allowRequestOverride) {
        this.allowRequestOverride = allowRequestOverride;
    }

    public boolean isAllowSessionOverride() {
        return this.allowSessionOverride;
    }

    public void setAllowSessionOverride(boolean allowSessionOverride) {
        this.allowSessionOverride = allowSessionOverride;
    }

    public String getPostProcessing() {
        return this.postProcessing;
    }

    public void setPostProcessing(String postProcessing) {
        this.postProcessing = postProcessing;
    }

    public RockerProperties copy() {
        new RockerProperties();
        RockerProperties properties = new RockerProperties();
        properties.setPrefix(this.getPrefix());
        properties.setSuffix(this.getSuffix());
        properties.setAddAsSources(this.isAddAsSources());
        properties.setAddAsTestSources(this.isAddAsTestSources());
        properties.setAllowRequestOverride(this.isAllowRequestOverride());
        properties.setAllowSessionOverride(this.isAllowSessionOverride());
        properties.setClassDirectory(this.getClassDirectory());
        properties.setContentType(this.getContentType());
        properties.setDiscardLogicWhitespace(this.isDiscardLogicWhitespace());
        properties.setEnabled(this.isEnabled());
        properties.setExposeRequestAttributes(this.isExposeRequestAttributes());
        properties.setExposeSessionAttributes(this.isExposeSessionAttributes());
        properties.setExposeSpringMacroHelpers(this.isExposeSpringMacroHelpers());
        properties.setExtendsClass(this.getExtendsClass());
        properties.setExtendsModelClass(this.getExtendsModelClass());
        properties.setFailOnError(this.isFailOnError());
        properties.setJavaVersion(this.getJavaVersion());
        properties.setOptimize(this.isOptimize());
        properties.setOutputDirectory(this.getOutputDirectory());
        properties.setReloading(this.isReloading());
        properties.setSkip(this.isSkip());
        properties.setSkipTouch(this.isSkipTouch());
        properties.setTargetCharset(this.getTargetCharset());
        properties.setSuffixRegex(this.getSuffixRegex());
        properties.setTemplateDirectory(this.getTemplateDirectory());
        properties.setTemplateResolverOrder(this.getTemplateResolverOrder());
        properties.setTouchFile(this.getTouchFile());
        properties.setPostProcessing(this.getPostProcessing());
        return properties;
    }
}

