# system-manager-learn
学习轮子，快速造车


## code-generator 代码生成
感谢[FEBS](https://github.com/febsteam) 系列框架，[鸟哥博客](https://mrbird.cc/Leanote%E5%8D%9A%E5%AE%A2%E4%B8%BB%E9%A2%98Summer.html)

流程：读取`resource`目录下的`.ftl`后缀模板文件，将模板文件中的自定义参数以对象形式传入，接收输出流，写入本地或导出到页面


## security-cookie-jwt-rouyi

感谢[Rouyi](https://gitee.com/y_project/RuoYi-Vue) 系列框架

之前有收罗一些教程实现过`Spring Security`，但都不是很系统，看了**ruoyi**的的实现后，比较全面

通过`SecurityConfig`进行展开，配置对应的处理器形成Filter链，及`URI`的无状态与放行
登录需要手动触发，将登入的用户名，密码及RemeberMe加入`Authentication`，之后会调用`UserDetailsService.loadUserByUsername`的用户实现，
主要将用户信息塞到`UserDetails`中，可附加进行自定义信息用户的校验。校验通过后拿到`Authentication`的`UserDetails`用户信息，生成jwt。


## shiro-jwt shiro jwt

## shiro-session-cookie


