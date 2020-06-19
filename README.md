# Generate-Graph
## Feature
通过读取指定路径的json文件生成如下图所示的结构
```json
{
    "graph":[
        {
            "name":"event-header",
            "attributes":[
                {
                    "key":"timestamp",
                    "value":"0:4"
                },
                {
                    "key":"type_code",
                    "value":"4:1"
                },
                {
                    "key":"server_id",
                    "value":"5:4"
                }
            ]
        },
         {
            "name":"event-body",
            "attributes":[
                {
                    "key":"timestamp",
                    "value":"0:4"
                },
                {
                    "key":"type_code",
                    "value":"4:1"
                },
                {
                    "key":"server_id",
                    "value":"5:4"
                }
            ]
        }
    ]
}
```
图表如下图所示
```
+========================================+
| event- | timestamp             0:4    |
| header +------------------------------+
|        | type_code             4:1    |
|        +------------------------------+
|        | server_id             5:4    |
+========================================+
| event- | timestamp             0:4    |
| body   +------------------------------+
|        | type_code             4:1    |
|        +------------------------------+
|        | server_id             5:4    |
+========================================+
```
## How to use
通过path设置json的路径，可以设置header和body部分的长度

```
String path = "/Users/tommenx/Desktop/info.json";
Parser parser = new Parser(path);
List<Segment> list = parser.parse();
Painter painter = new Painter.Builder()
        .header(10)
        .body(30)
        .segments(list)
        .build();
```

### 测试一下git
ssdsdsds
dshdjshdj
sdjkl
