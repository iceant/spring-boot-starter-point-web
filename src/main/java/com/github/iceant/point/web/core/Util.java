package com.github.iceant.point.web.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;

import javax.naming.CannotProceedException;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Base64;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

public class Util {

    private static ApplicationContext applicationContext;
    private static String OPERATION_SYSTEM = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return OPERATION_SYSTEM.contains("win");
    }

    public static boolean isMac() {
        return OPERATION_SYSTEM.contains("mac");
    }

    public static boolean isUnix() {
        return (OPERATION_SYSTEM.contains("nix") || OPERATION_SYSTEM.contains("nux") || OPERATION_SYSTEM.contains("aix") || OPERATION_SYSTEM.contains("bsd"));
    }

    public static boolean isSolaris() {
        return OPERATION_SYSTEM.contains("sunos");
    }

    ///////////////////////////////////////////////////////////////////////////
    ////
    public static File getFile(String resourceLocation) throws FileNotFoundException {
        return ResourceUtils.getFile(resourceLocation);
    }

    public static File getFile(URL url) throws FileNotFoundException {
        return ResourceUtils.getFile(url);
    }

    public static File getFile(URI uri) throws FileNotFoundException {
        return ResourceUtils.getFile(uri);
    }

    public static String errorToString(Throwable error, String charset) throws UnsupportedEncodingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(byteArrayOutputStream);
        error.printStackTrace(stream);
        stream.close();
        String result = byteArrayOutputStream.toString(charset);
        return result;
    }

    public static JsonResponse makeResponse(VarArgsFunction function, Object... args) {
        JsonResponse response = new JsonResponse();
        response.setTimestamp(System.currentTimeMillis());
        try {
            Object result = function.apply(args);
            response.setData(result);
            response.setStatusCode(JsonResponseStatus.SUCCESS);
            response.setStatus("SUCCESS");
        } catch (Exception err) {
            response.setStatusCode(JsonResponseStatus.FAILED);
            response.setStatus("FAILED");
            try {
                response.setMessage(errorToString(err, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
        }
        return response;
    }

    public static String readFileAsString(File file, int bufferSize, String charset)
            throws java.io.IOException {
        java.io.InputStream is = new java.io.FileInputStream(file);
        try {
            int available = is.available();
            byte[] data = new byte[available < bufferSize ? bufferSize : available];
            int used = 0;
            while (true) {
                if (data.length - used < bufferSize) {
                    byte[] newData = new byte[data.length << 1];
                    System.arraycopy(data, 0, newData, 0, used);
                    data = newData;
                }
                int got = is.read(data, used, data.length - used);
                if (got <= 0) break;
                used += got;
            }
            return charset != null ? new String(data, 0, used, charset)
                    : new String(data, 0, used);
        } finally {
            is.close();
        }
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();
        return bFile;
    }

    public static String readFileAsBase64(File file) throws IOException {
        byte[] bytes = readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static Resource[] getResources(String pattern, ClassLoader classLoader) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);
        return resolver.getResources(pattern);
    }

    public static String getRelatedPath(String fullpath, String prefix) {
        if (prefix.startsWith("classpath:")) prefix = prefix.substring("classpath:".length());
        if (isWindows()) {
            fullpath = fullpath.replaceAll("\\\\", "/");
        }
        int pos = fullpath.indexOf(prefix);
        if (pos == -1) return fullpath;
        return fullpath.substring(pos + prefix.length());
    }

    public static String getFileExtension(String filename) {
        if (filename == null || filename.length() < 1) return "";
        int pos = filename.lastIndexOf(".");
        if (pos == -1) return "";
        return filename.substring(pos + 1);
    }

    public static void setApplicationContext(ApplicationContext context){
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static String prop(String name, String defaultValue){
        return applicationContext.getEnvironment().getProperty(name, defaultValue);
    }

    public static String prop(String name){
        return applicationContext.getEnvironment().getProperty(name);
    }

    public static <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }

    public static <T> T getBean(Class<T> tClass, Object ... args){
        return applicationContext.getBean(tClass, args);
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static Object getBean(String name, Object ... args){
        return applicationContext.getBean(name, args);
    }

    public static String getMessage(String name, Object[] args, Locale locale){
        return applicationContext.getMessage(name, args, locale);
    }

    public static String getMessage(String name, Object ... args){
        return applicationContext.getMessage(name, args, LocaleContextHolder.getLocale());
    }

    public static Boolean hasRole(String role){
        GrantedAuthorityDefaults grantedAuthorityDefaults = getBean(GrantedAuthorityDefaults.class);
        String rolePrefix = grantedAuthorityDefaults != null ? grantedAuthorityDefaults.getRolePrefix() : "ROLE_";
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getAuthorities)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .map(GrantedAuthority::getAuthority)
                .map(authority -> rolePrefix + authority)
                .anyMatch(role::equals);
    }

    public static Boolean hasAnyRole(String ... roles){
        if(roles==null || roles.length<1) return false;
        GrantedAuthorityDefaults grantedAuthorityDefaults = getBean(GrantedAuthorityDefaults.class);
        String rolePrefix = (grantedAuthorityDefaults != null) ? grantedAuthorityDefaults.getRolePrefix() : "ROLE_";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null) return false;
        boolean hasRole = false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            String auth = rolePrefix + authority.getAuthority();
            for(String role : roles){
                if(role.equals(auth)){
                    hasRole = true;
                    break;
                }
            }
            if(hasRole){
                break;
            }
        }
        return hasRole;
    }
}
