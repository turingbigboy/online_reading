package com.yyjj.reading.api.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 修改相关spring boot配置类信息
 * 
 * @author doyle
 *
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

	// 添加视图映射
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {	
		registry.addViewController("/admin/shop/shop_info.html").setViewName("admin/shop/shop_info");

		
	}
	
	
	/**
	 * mybatis-plus 分页插件
	 * @return
	 */
	 @Bean
	 public PaginationInterceptor paginationInterceptor() {
	    return new PaginationInterceptor();
	 }
	 
	 @Override
	protected void addInterceptors(InterceptorRegistry registry) {
		 InterceptorRegistration registration = registry.addInterceptor(new loginFilter());
	        registration.addPathPatterns("/**");
	        registration.excludePathPatterns(
	        		 "/**/login.html",
	        		 "/**/login",//登录
	        		 "/**//register",
                         //html静态资源
                     "/**/*.js",              //js静态资源
                     "/**/*.css" ,
                     "/*.ico" //css静态资                   
                     );
		super.addInterceptors(registry);
	}
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (int i = 0; i < converters.size(); i++) {
			// 将jackson的时间格式化为带有标准时区的格式

			if (converters.get(i) instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) converters.get(i);

				ObjectMapper objectMapper = converter.getObjectMapper();

				SimpleModule simpleModule = new SimpleModule("JsonMapSerializer", Version.unknownVersion());

				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				simpleModule.addSerializer(new LocalDateTimeSerializer(dateTimeFormatter));

				// 忽略字段为null的数据
				objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

				objectMapper.registerModule(simpleModule);
				break;
			}
		}
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 重写这个方法，映射静态资源文件
//	    registry.addResourceHandler("/image/**").addResourceLocations("file:C:\\Users\\Administrator\\Documents\\workspace-spring-tool-suite-4-4.0.1.RELEASE\\repast\\src\\main\\resources\\static\\img\\adv\\");
	    registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/resources/")
				.addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
	}
	
	
}
