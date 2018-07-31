fork from: https://github.com/sharding-sphere/sharding-sphere

### 项目背景
近期需要对一个已有老系统进行数据库迁移，其中有几个大表（单表100多G），对性能存在隐患，所以决定采用分库分表保存数据。为了满足自身业务需求，本项目扩展了sharding-sphere的功能。

### sharding-proxy调研
* 优点：
    1. 支持分库分表和单节点库两种模式并存
    2. 应用可以像普通mysql一样连接，对业务代码改动较小
    3. 支持分页、排序、分组、聚合、关联查询（不支持跨库关联），支持DDL语句
    4. id生成策略使用snowflake算法生成18位long id，不会与已有数据id重复，性能好，且无需第三方服务（redis、zk、db）依赖
    5. 不使用分库分表的表也可以使用ID生成策略，迁移后无需手动给每个表设置auto_increment（修改了小部分源码后实现的）
    6. 支持读写分离，不需要在应用代码里指定，sharding-proxy会自动识别sql选择数据源
    7. 用netty实现了mysql协议，使用epoll模型通信（需要服务器支持，如果不支持则是nio）性能高，对线程消耗低
    8. 配置简单，学习成本低
    9. 完全开源，代码规范设计良好，易于阅读，方便问题排查，和定制一些符合实际业务的功能

* 缺点
    1. 原生不支持高可用，需要自己搭建四层协议负载实现高可用（可以用lvs）
    2. 增加数据表（需要分库分表的表），需要修改配置重启sharding-proxy（新增单节点表则不需要）
    3. 自带的分片算法不支持节点动态扩容，扩容节点需要重新导入数据，对数据重新分片，除非修改源码增加新的分片算法
    4. 不支持冗余括号、CASE WHEN、DISTINCT、HAVING、UNION (ALL)，有限支持子查询（http://shardingsphere.io/document/current/cn/features/sharding/usage-standard/sql/）。


### 扩展的功能点
* 支持不分库分表的表使用id生成策略
    config.yaml参考配置
    ```yaml
    shardingRule:
        # 自定义扩展的配置
        noSharding:
            defaultKeyGeneratorColumnName: id
            keyGeneratorColumnNames:
              user: uid
              category: cid
            excludeTables:
              - table_name
    ```
    参数含义
    - noSharding: 不需要sharding的表配置
    - defaultKeyGeneratorColumnName: 默认主键字段名称，配置了之后，不需要sharding的表将会使用主键生成策略来生成主键
    - keyGeneratorColumnNames: 表和主键的映射关系，table: keyColumnName
    - excludeTables: 排除掉部分表，不使用主键生成策略。将由程序或者数据库来生成主键



