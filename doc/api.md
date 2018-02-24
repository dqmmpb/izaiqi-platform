# Api

## 规约

### 格式
请求格式：统一以`application/json`格式提交
```
Content-Type: application/json
```
请求参数统一封装为如下格式
```json
{
	"sign": "非必填，用于校验参数的（暂时不适用，//TODO）",
	"params": {
		"phone": "13819493700",
		"password": "123456"
	}
}
```
`sign`为参数校验值，校验规则待定（基本原则）
`params`参数实体，业务参数统一使用`params`包装

返回格式：json
```
{
    "success": true,
    "errorCode": "",
    "errorMessage": "",
    "errorType": "",
    "result": {
        "login": true,
        "phone": "13819493700",
        "token": "35de065d-f2bd-4264-9c2c-bae60b064a1b"
    }
}

```

### 类型格式说明

字符型、数值型
- A18 固定长度为18 的字符。 
- VA100 可变长度，最大为100的字符。
- N3 最大长度为3 位数字。 
- N5.1 最大长度为5 位的十进制小数格式（包括小数点），小数点后保留1 位数字。
- B 布尔类型。

日期型 
- D8 采用YYYYMMDD 格式（8 位定长）表示年月日。如1998 年1 月8 日，应表示为19980108。
- D10 采用YYYY-MM-DD 格式（10 位定长）表示年月日。如1998 年1 月8 日，应表示为1998-01-08。 

日期时间型 
- DT15 采用YYYYMMDDThhmmss 格式（15 位定长）表示年月日时分秒。如1998年1月8日13点25分18秒，应表示为19980108T132518
- DT19 采用YYYY-MM-DDThh:mm:ss 格式（19 位定长）表示年月日时分秒。如1998年1月8日13点25分18秒，应表示为1998-01-08T13:25:18 


### 接口

#### 用户登录

`http://localhost:8080/xiaoma-web/sys/login`

请求
```json
{
	"params": {
		"phone": "13819493700",
		"password": "123456"
	}
}
```

| 参数      | 类型  | 说明          | 备注             |
|:---------|:-----|:-------------|:-----------------|
| phone    | VA11 | 必填          | 用户名/手机号，唯一 |
| password | VA20 | 必填（6~20位） | 6～20位数字、字母  |

返回
```json
{
    "success": true,
    "errorCode": "",
    "errorMessage": "",
    "errorType": "",
    "result": {
        "login": true,
        "phone": "13819493700",
        "token": "2d3432a6-a3da-4b89-bcae-3183f92941f9"
    }
}
```

| 参数          | 类型  | 说明        | 备注                        |
|:-------------|:-----|:-----------|:---------------------------|
| success      | B    | true/false | true-请求成功；false-请求失败 |
| errorCode    | VA   |            | 错误码                      |
| errorMessage | VA   |            | 错误消息                    |
| errorType    | VA   |            | 错误类型，用于将errorCode归类 |
| result       | Obj  |            | 返回结果                    |
|              |      |            |                            |
| login        | B    | true/false | 是否登录                    |
| phone        | VA11 |            | 用户名/手机号                |
| token        | VA36 |            | 登录Token                   |

#### 用户是否登录

`http://localhost:8080/xiaoma-web/sys/isLogin`

请求
```json
{
	"params": {
	}
}
```

| 参数 | 类型 | 说明 | 备注 |
|:----|:-----|:----|:----|
| 无   |      |     |     |

返回
```json
{
    "success": true,
    "errorCode": "",
    "errorMessage": "",
    "errorType": "",
    "result": {
        "login": true,
        "phone": "13819493700",
        "token": "2d3432a6-a3da-4b89-bcae-3183f92941f9"
    }
}
```

| 参数          | 类型  | 说明        | 备注                        |
|:-------------|:-----|:-----------|:---------------------------|
| success      | B    | true/false | true-请求成功；false-请求失败 |
| errorCode    | VA   |            | 错误码                      |
| errorMessage | VA   |            | 错误消息                    |
| errorType    | VA   |            | 错误类型，用于将errorCode归类 |
| result       | Obj  |            | 返回结果                    |
|              |      |            |                            |
| login        | B    | true/false | 是否登录                    |
| phone        | VA11 |            | 用户名/手机号                |
| token        | VA36 |            | 登录Token                   |


#### 用户退出

`http://localhost:8080/xiaoma-web/sys/logout`

请求
```json
{
	"params": {
	}
}
```

| 参数 | 类型 | 说明 | 备注 |
|:----|:-----|:----|:----|
| 无   |      |     |     |

返回
```json
{
    "success": true,
    "errorCode": "",
    "errorMessage": "",
    "errorType": "",
    "result": null
}
```

| 参数          | 类型 | 说明       | 备注                       |
|:-------------|:----|:-----------|:---------------------------|
| success      | B   | true/false | true-请求成功；false-请求失败 |
| errorCode    | VA  |            | 错误码                      |
| errorMessage | VA  |            | 错误消息                    |
| errorType    | VA  |            | 错误类型，用于将errorCode归类 |
| result       | Obj |            | 返回结果                    |

#### 用户个人信息

`http://localhost:8080/xiaoma-web/sys/profile/view`

请求
```json
{
	"params": {
	}
}
```

| 参数 | 类型 | 说明 | 备注 |
|:----|:-----|:----|:----|
| 无   |      |     |     |

返回
```json
{
    "success": true,
    "errorCode": "",
    "errorMessage": "",
    "errorType": "",
    "result": {
        "department": "",
        "email": "",
        "lastlogintime": null,
        "managerId": 1,
        "name": "",
        "phone": "13819493700",
        "state": "A"
    }
}
```

| 参数          | 类型   | 说明        | 备注                           |
|:-------------|:------|:-----------|:------------------------------|
| success      | B     | true/false | true-请求成功；false-请求失败    |
| errorCode    | VA    |            | 错误码                         |
| errorMessage | VA    |            | 错误消息                       |
| errorType    | VA    |            | 错误类型，用于将errorCode归类    |
| result       | Obj   |            | 返回结果/用户信息，null-无用户信息 |
|              |       |            |                               |
| managerId    | N16   |            | 用户ID                         |
| phone        | VA11  |            | 用户名/手机号                   |
| name         | VA20  |            | 用户姓名                       |
| email        | VA64  |            | 邮箱                           |
| department   | VA128 |            | 部门                           |
| state        | VA1   |            | 状态，A-可用；X-不可用           |
