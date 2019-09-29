jquery.easyui修改
1.css：
.datagrid-header-row
.textbox-disabled：opacity: 1.0

.tabs li a.tabs-inner {
    background: transparent;
    filter: none;
    color: #0088CC;
}


2.jquery.easyui.min.js：
[10,20,30,40,50]
 href=\"javascript:;\"
modal:true
showSeconds:true
y+"-"+(m<10?("0"+m):m)+"-"+(d<10?("0"+d):d)


3.easyui-lang-zh_CN.js：
显示{from}到{to}条记录，共{total}条
Please enter a value length between {0} and {1}.


============================================================================
编码规范
1.页面跳转
	a.没有参数传递直接使用：PageController.java
	b.有参数传递需要使用：PageModelController
	c.有参数传递并且需要在controller中做处理需要定义以“-pageModel”结尾的url

	
2.Model需要“创建人、修改人”直接继承BaseModel且数据库字段需要定义“create_user_id、mod_user_id”


============================================================================
pom.xml
1.打包时排除tomcat：
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>


2.lombok插件：
java -jar lombok-1.16.22.jar
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<scope>provided</scope>
</dependency>


3.druid加密：
ShiroUtils


aaaaa
bbbbb
ccccc
ddddd
eeeee

fffff
