# XFinal
这是基于SpringBoot的一款Web开发框架，它能够让你快速地开发Java服务端程序。
## 特性
1. 无XML配置文件，纯Java Config
2. 便捷的数据库实体注入，无需书写繁琐的SQL
3. 默认Redis作为缓存，并使用ProtoStuff序列化/反序列化对象
4. 统一的异常处理和优雅的接口输出
5. RESTful接口风格
6. 可持续集成其他所需的服务

## 代码结构
```
XFinal                      
├─common                    公共库
│ ├─data                    数据访问处理
│ ├─exception               异常处理
│ └─utils                   工具包
│
├─config                    项目配置
│
├─module                    业务模块
│ ├─hello                   hello demo
│ │ ├─HelloFacade.java      接口定义
│ │ └─HelloFacadeImpl.java  接口实现
│ │
│ ├─seckill                 秒杀模块
│ │ ├─facade                秒杀接口
│ │ ├─manager               数据访问层
│ │ ├─model                 对象模型
│ │ │  └─result             返回结果对象模型
│ │ └─service               业务逻辑层
│ │
│ └─Modules.java            模块号定义
└─MainBooster.java          启服类
```