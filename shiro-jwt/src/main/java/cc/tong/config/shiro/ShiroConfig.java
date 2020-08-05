package cc.tong.config.shiro;

import cc.tong.config.shiro.jwt.JWTFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 16:02
 * @Description:
 */
@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager getManager(ShiroRealm shiroRealm) {

        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm);
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */

        DefaultSubjectDAO dao = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        dao.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(dao);
        return manager;
    }

    /**
     * 配置过滤器及URL拦截
     *
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        filterFactoryBean.setFilters(filterMap);
        filterFactoryBean.setSecurityManager(securityManager);
        // url拦截规则，规则匹配选择过滤器
        Map<String, String> url = new HashMap<>();
        url.put("/**", "jwt");
        url.put("/401", "anon");
        filterFactoryBean.setFilterChainDefinitionMap(url);
        return filterFactoryBean;
    }

//    /**
//     * 添加注解支持
//     */
//    @Bean("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
//        // https://zhuanlan.zhihu.com/p/29161098
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
