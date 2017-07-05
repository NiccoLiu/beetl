#技术依赖
beetl2.0 + jfinal2.1

beetl是模版引擎，jfinal主要用到里面的DB插件，获取数据库信息

#模版配置
配置文件在src/main/resource/conf目录下template.properties
```
#生成模板配置

#选择模板路径
template.selected=
#all或者不填，为生成全部；多个表已逗号分隔
template.tables=
#表前缀
template.table.remove.prefixes=t_
#配置单表的搜索列，多个列逗号分割，大小写不敏感
template.table.OPERATION_TEAM.searchColumn=NAME

#生成路径
template.output.path=/output/
```