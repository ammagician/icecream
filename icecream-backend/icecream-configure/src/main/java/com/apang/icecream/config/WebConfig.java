package com.apang.icecream.config;

import static java.util.Arrays.asList;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setOrder(1);
        return resolver;
    }
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resource/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resource/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resource/images/");
        registry.addResourceHandler("/lib/**").addResourceLocations("/resource/lib/");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));

        converters.add(byteArrayHttpMessageConverter);
        converters.add(responseBodyConverter());
        converters.add(mappingJackson2HttpMessageConverter);
    }
    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

  /*  @Bean
    public FilterRegistrationBean loggingFilterRegistrationBean() {
        return this.generateFilter(new LoggingFilter(),1);
    }*/

    @Bean
    public FilterRegistrationBean securityFilterChain(@Qualifier(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME) Filter securityFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(securityFilter);
        registration.setOrder(4);
        registration.setName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
        List<String> urlPatterns = new ArrayList<String>();
   	 	urlPatterns.add("*.do");
        registration.setUrlPatterns(urlPatterns);
        return registration;
    }

    private FilterRegistrationBean generateFilter(Filter pFilter,int order){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(pFilter);
        registrationBean.setOrder(order);
        return registrationBean;
    }
}
