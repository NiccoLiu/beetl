# 技术依赖
- [x] beetl-2.0
- [x] jfinal-2.1


---

# 简介

1. beetl是模版引擎
详细请参考[beetl官网](http://ibeetl.com/)
2. jfinal主要用到里面的DB插件，没有用到jfinal的所有东西
详细请参考[jfinal官网](http://www.jfinal.com/)
3. 代码模版配置，项目中template有三个目录
   - template/project/mot-pay 移动支付平台模版
   - template/project/etcm ETCM项目模版
   - template/project/operation 自动化项目模版
4. 修改配置conf/template.properties
   - 配置基础基础包名：base_package=
   - 配置模块名：model=
   - 微服务名：microservice_name=
   - 模版路径：template.selected
   - 数据库表选择：template.tables=
   - 数据库表前缀：template.table.remove.prefixes=
   - 生成代码路径：template.output.path=

5. 执行生成代码
执行类
``` java
com.kmob.generator.main.GeneratorStart
```