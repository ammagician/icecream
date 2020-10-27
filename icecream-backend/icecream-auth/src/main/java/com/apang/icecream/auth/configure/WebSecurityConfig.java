
package com.apang.icecream.auth.configure;

import com.apang.icecream.auth.security.CustomExpiredSessionStrategy;
import com.apang.icecream.auth.security.User4AuthDetailService;
import com.apang.icecream.auth.security.UserRoleDecisionVoter;
import com.apang.icecream.auth.constants.AuthConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 认证配置管理.<br>
 *
 * @author andy pang <br>
 * @version 1.0.0 2019年8月30日<br>
 * @since JDK 1.8.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * LOG.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private User4AuthDetailService user4AuthDetailService;

    @Autowired
    private CustomExpiredSessionStrategy expiredSessionStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
             // 默认许可的访问地址.
                .antMatchers(AuthConstants.C_AUTH_URI).permitAll()
                .anyRequest().authenticated()
                .and()
             // 登出session处理.
             .logout().invalidateHttpSession(true)
                .and()
             // Session控制.
            .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredSessionStrategy(expiredSessionStrategy)
                .sessionRegistry(this.sessionRegistry()).and()
                .and()
             // 认证
            .authenticationProvider(this.authenticationProvider()).authorizeRequests().anyRequest().authenticated()
                .accessDecisionManager(this.accessDecisionManager());
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    /**
     * 会话注册.
     * @return Registry.
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    /**
     * 实例化投票器.
     * @return 实例化的投票器.
     */
    @Bean
    UserRoleDecisionVoter userRoleVoter(){
        return new UserRoleDecisionVoter();
    }

    /**
     * 实例化AccessDecisionManager.
     * @return 实例.
     */
    @Bean
    AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
        decisionVoters.add(this.userRoleVoter());
        return new AffirmativeBased(decisionVoters);
    }

    @Bean
    User4AuthDetailService userDetailServiceImpl() {
        final User4AuthDetailService detailService = new User4AuthDetailService();
        return detailService;
    }

    /**
     * 认证管理
     * @return 实例.
     * @throws Exception 异常.
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(this.userDetailServiceImpl());
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList((AuthenticationProvider) authenticationProvider));
        return authenticationManager;
    }

    /**
     * 认证提供处理者.
     * @return 实例.
     * @throws Exception 异常.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(this.userDetailServiceImpl());
        return authenticationProvider;
    }
}
