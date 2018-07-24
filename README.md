fork from: https://github.com/sharding-sphere/sharding-sphere

本项目扩展了sharding-sphere的功能，用于实现符合自身业务的逻辑

### 扩展的功能点
* 支持不分库分表的表使用id生成策略
    config.yaml参考配置
    ```yaml
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



