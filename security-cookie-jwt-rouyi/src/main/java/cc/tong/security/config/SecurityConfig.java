package cc.tong.security.config;

import cc.tong.security.filter.JwtAuthenticationTokenFilter;
import cc.tong.security.handler.AuthenticationEntryPointImpl;
import cc.tong.security.handler.LogoutHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author: tn
 * @Date: 2020/8/5 0005 14:02
 * @Description:
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 自定义用户认证逻辑
     */
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * 认证失败处理类
     */
    @Autowired
    private AuthenticationEntryPointImpl authenticationPoint;
    /**
     * 退出处理类
     */
    @Autowired
    private LogoutHandlerImpl logoutHandler;

    /**
     * jwt处理
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    /**
     * 跨域处理 器
     */
    @Autowired
    private CorsFilter corsFilter;

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // CRSF禁用，因为不使用session
                .csrf().disable()
                // 认证失败处理
                .exceptionHandling().authenticationEntryPoint(authenticationPoint)
                // 基于token，不需要session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 过滤请求
                .and().authorizeRequests().antMatchers("/login").anonymous()
                .antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                // 除上诉路径，都需要认证
                .anyRequest().authenticated()
                .and().headers().frameOptions().disable();

        httpSecurity.logout().logoutUrl("logout").logoutSuccessHandler(logoutHandler);
        // JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // CORS filter 跨域
        httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);

    }

    /**
     * 密码
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份认证处理
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
