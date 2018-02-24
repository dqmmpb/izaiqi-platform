# 工程名：izaiqi-platform

## izaiqi-platform server端主工程

### 规范介绍

能用注解注入的尽量用注解，少写点xml，约定胜于配置；

@Component 用于Controller层实现类注入，@Service 用于service层实现类注入，@Repository 用于持久化层实现类注入；

每个数据表，设计到状态的，一定要把状态机的迁转设计清楚，并且不用数字枚举，要用英文枚举，包括数据库存储也用英文，可读性强；

### 目录结构介绍

controller层，仅仅用来处理url相关内容，以及路由控制

service层是基础服务层，存放单个领域模型的业务逻辑的代码，用来存放简单的业务逻辑

facade层是门面facade，用来存放复杂的业务逻辑，对外提供的Service接口通过facade统一封装，放在facacde包下；

dao是使用通用Mapper自动生成的，需要根据本地配置修改 generatorConfig.xml 这个文件，并且不要提交这个文件；

异常 和 trace log 交由 AOP 进行处理；


### 工程介绍

- platform-base: 基类工程，包括用户权限、登录的核心操作，在扩展工程时，请在自己的工程中进行扩展，不要修改platform-base的代码
- platform-web: 业务核心工程，提供业务相关的服务，具体项目的业务层请参看本工程


### 数据库脚本

数据库脚本放置在doc/sql目录下，请自行查阅

- init_sql.sql: 数据库初始化脚本（仅表结构）
- izaiqi_local_test.sql: 测试数据的初始化脚本（结构和数据）

后续的更改，请按照规范`1.0.1`新建子文件夹


注：
- mybatis generator在生成的*Mapper.xml会采用merge的方式，因此多次执行mybatis generator会在xml中存在重复代码，需要手动去除。
- 一种比较好的策略是在使用通用Mapper的时候不使用自动生成的xml。
- 通用Mapper没有提供继承方式，因此所有的Mapper都要继承自`tk.mybatis.mapper.common.Mapper`。
- 当接口有1个参数时，Mapper会根据参数名映射到xml中，但当有多个参数时，需要使用@Param方式绑定参数，便于在xml中获取。
