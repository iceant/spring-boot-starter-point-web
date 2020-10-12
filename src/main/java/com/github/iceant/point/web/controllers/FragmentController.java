package com.github.iceant.point.web.controllers;

import com.fizzed.rocker.runtime.RockerBootstrap;
import com.github.iceant.point.web.PointWebSpringBootStarterProperties;
import com.github.iceant.point.web.core.MimeTypeUtil;
import com.github.iceant.point.web.core.RockerUtil;
import com.github.iceant.point.web.core.Util;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = {"/fragment"})
public class FragmentController {

    static final int FRAGMENT_FILE_LENGTH = "/fragment/file/".length();

    final PointWebSpringBootStarterProperties properties;

    public FragmentController(PointWebSpringBootStarterProperties properties) {
        this.properties = properties;
    }

    @RequestMapping(path = {"/file/**"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object fragment(HttpServletRequest request) {
        return Util.makeResponse(o -> {
            String requestUri = request.getRequestURI();
            requestUri = properties.getFragmentPrefix()+requestUri.substring(FRAGMENT_FILE_LENGTH);
            File file = null;
            try {
                file = ResourceUtils.getFile(requestUri);
            }catch (FileNotFoundException fnfe){}
            if(file==null || !file.exists()){
                if(requestUri.startsWith("/")) requestUri = requestUri.substring(1);
                file = ResourceUtils.getFile(properties.getFragmentPath()+requestUri);
            }
            return Util.readFileAsString(file, 4096, "UTF-8");
        });
    }

    static final String [] TEXT_FILE_EXTENSIONS = {"txt", "html", "htm", "js", "css"};
    private boolean isTextFile(File file){
        String ext = Util.getFileExtension(file.getName()).toLowerCase();
        for(String txt : TEXT_FILE_EXTENSIONS){
            if(txt.equals(ext)) return true;
        }
        return false;
    }

    static final String[] IMAGE_FILE_EXTENSIONS = {"jpg", "jpeg", "png", "gif", "bmp"};
    private boolean isImage(File file){
        String img = Util.getFileExtension(file.getName()).toLowerCase();
        if(img.length()<1) return false;
        for(String ext : IMAGE_FILE_EXTENSIONS){
            if(ext.equals(img)) return true;
        }
        return false;
    }

    @RequestMapping(path = {"/bundle"}, produces = {})
    public Object bundles(HttpServletRequest request){
        return Util.makeResponse(o->{
            Resource[] resources = Util.getResources(properties.getFragmentPath()+"/**/*", Thread.currentThread().getContextClassLoader());
            Map<String, String> result = new HashMap<>();
            RockerBootstrap rockerBootstrap=RockerUtil.getRockerBootstrap();
            for(Resource resource : resources){
                if(resource.isFile() && resource.getFile().isFile()) {
                    File file = resource.getFile();
                    String path = Util.getRelatedPath(file.getPath(), properties.getFragmentPath());
                    if(isTextFile(file)){
//                        result.put(path, Util.readFileAsString(file, 1024, "UTF-8"));
                        String content = RockerUtil.render(rockerBootstrap, path);
                        result.put(path, content);
                        content = null;
                    }else if(isImage(file)){
                        String mime = MimeTypeUtil.getInstance().getMimetype(file);
                        result.put(path, "data:"+mime+";base64, "+Util.readFileAsBase64(file));
                    }else{
                        result.put(path, Util.readFileAsBase64(file));
                    }
                }
            }
            return result;
        });
    }
}
