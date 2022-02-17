package fis.police.fis_police_server;

import fis.police.fis_police_server.interceptor.LogInterceptor;
import fis.police.fis_police_server.interceptor.LoginCheckInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FisPoliceServerApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(FisPoliceServerApplication.class, args);
    }

	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry){
			registry.addMapping("/**")
					.allowedOriginPatterns("*", "http://54.175.8.114/*")
					.allowCredentials(true)
                    .allowedHeaders("*")
					.allowedMethods("*");
		}
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LogInterceptor())
                    .order(1)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/css/**", "/*.ico", "/error","/messenger/*", "/app/**");

            registry.addInterceptor(new LoginCheckInterceptor())
                    .order(2)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**", "/*.ico", "/error", "/messenger/*", "/app/**");
        }
    }
}
