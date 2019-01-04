package cn.ziav.seckill.config;

import static com.alibaba.fastjson.JSON.DEFFAULT_DATE_FORMAT;

import cn.ziav.common.serialize.LongToStringCodec;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = fastJsonConverter.getFastJsonConfig();
    fastJsonConfig.setDateFormat(DEFFAULT_DATE_FORMAT);
    List<MediaType> supportedMediaTypes = new ArrayList<>();
    supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    fastJsonConverter.setSupportedMediaTypes(supportedMediaTypes);
    fastJsonConfig.getSerializeConfig().put(Long.class, LongToStringCodec.instance);
    converters.add(fastJsonConverter);

    StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
    converter.setWriteAcceptCharset(false);
    converters.add(converter);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("*").allowCredentials(true);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 将所有/** 访问都映射到classpath:/public/ 目录下
    registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");
  }
}
