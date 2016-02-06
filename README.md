# OnlineFriend
JSP入门实战项目（javabean+JSP+servlet+mysql）

这个简单网站使用了javabean+JSP+servlet+mysql（MVC）开发，包含了（1）用户管理：用户注册，登录，头像上传，用户信息显示，注册信息修改，密码修改，退出登录；

（2）文章管理：文章列表分页显示，新文章，修改和删除文章



特色：1 注册页面加了token，防重复提交
      
2 登录界面加了随机验证码
      
3 防SQL注入，防XSS跨站漏洞
      
4 将基础的数据库连接改成了JNDI连接池模式

开发环境：java1.8+tomcat8+mysql5.x+eclipse for javaee


注：详细的设计与实现可以参考我写的这篇博客：http://www.zifangsky.cn/2015/11/java-web入门项目之网络交友的设计与实现/ 