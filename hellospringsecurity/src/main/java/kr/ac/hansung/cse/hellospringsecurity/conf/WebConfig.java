package kr.ac.hansung.cse.hellospringsecurity.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //url과 view이름을 매칭 시켜놓는 곳
    //따로 Controller 만들 필요가 없어진다.
    @Override
    public void addViewControllers(ViewControllerRegistry registry)   {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login-bootstrap");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/admin/home").setViewName("adminhome");
        registry.addViewController("/accessDenied").setViewName("403");
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}
