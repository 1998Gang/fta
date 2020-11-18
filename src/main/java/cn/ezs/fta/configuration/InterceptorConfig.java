package cn.ezs.fta.configuration;

import cn.ezs.fta.Interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h3>fta</h3>
 * <p>拦截器配置</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-10 11:42
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }
}
