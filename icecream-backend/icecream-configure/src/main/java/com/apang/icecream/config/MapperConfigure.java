
package com.apang.icecream.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mapper配置.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年8月30日<br>
 * @since JDK 1.8.0
 */
@Configuration
public class MapperConfigure {
    /**
     * 构建mapper实例.
     * @return 实例.
     */
    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
