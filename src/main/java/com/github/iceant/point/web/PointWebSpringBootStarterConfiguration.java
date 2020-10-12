package com.github.iceant.point.web;

import com.github.iceant.point.web.core.Util;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ConditionalOnClass({DispatcherServlet.class})
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class})
@EnableConfigurationProperties(PointWebSpringBootStarterProperties.class)
@ComponentScan
public class PointWebSpringBootStarterConfiguration {
    final PointWebSpringBootStarterProperties properties;
    final ApplicationContext applicationContext;

    public PointWebSpringBootStarterConfiguration(PointWebSpringBootStarterProperties properties, ApplicationContext applicationContext) {
        this.properties = properties;
        this.applicationContext = applicationContext;
        Util.setApplicationContext(applicationContext);
    }

}
