#支付宝支付demo

## 技术栈
前端：vue+vue-router
后端：springboot
其他：支付宝支付能力

## 开发方法
1. 执行后端应用
2. npm run serve 执行前端应用，前端的api请求才能通过代理请求到后端9090端口上的应用。yarn serve 不行

## 前端组件
https://blog.csdn.net/weixin_48201324/article/details/124969239

https://nutui.jd.com/#/zh-CN/guide/intro
http://mint-ui.github.io/#!/zh-cn
https://youzan.github.io/vant/#/zh-CN
https://vant-contrib.gitee.io/vant/v2/#/en-US/


## 支付宝文档
https://open.alipay.com/develop/manage

https://forum.alipay.com/college

https://opendocs.alipay.com/open/203/105285


## 支付成功return_url:
http://192.168.10.8:8080/pay-result?charset=UTF-8&out_trade_no=1664951073209&method=alipay.trade.wap.pay.return&total_amount=50.00&sign=ajUARSh0T4ZDmzGIeeFjnKjx3HO0k%2Bwz5npFxRtRWV1lK5lFibP3bKMBF7BJvzV6fiZaO2REBPj4GYZ%2Fofbd7rOHMj49NMrFlX7L9V8YAoTNcX8yD4cFP%2BT1E20bf8aRA%2FklHuMDFEw1B%2BpFK3W8XxM3%2ButEnWQ7yBlHnujjYrEFffmiWZgRZihFFRnyPp1uUg6pX4KladDftCXh%2BH3fNW%2FS7PtR3ytIkRRLINyNu%2BuDugg6YsTtRRHQmDAXRFoCsvRIJqisROkaH158UoV8%2BeELy0vGI7bL3j6A2F9usDaYQzmeJHIhnySmyGvqL5%2BcSU5N6Ysks%2BbF%2B9xGN9H7Yw%3D%3D&trade_no=2022100522001403990501989418&auth_app_id=2021000119683733&version=1.0&app_id=2021000119683733&sign_type=RSA2&seller_id=2088621958589997&timestamp=2022-10-05%2014%3A28%3A38

http://192.168.10.8:8080/pay-result?
charset=UTF-8
&out_trade_no=1664951073209
&method=alipay.trade.wap.pay.return
&total_amount=50.00
&sign=ajUARSh0T4ZDmzGIeeFjnKjx3HO0k%2Bwz5npFxRtRWV1lK5lFibP3bKMBF7BJvzV6fiZaO2REBPj4GYZ%2Fofbd7rOHMj49NMrFlX7L9V8YAoTNcX8yD4cFP%2BT1E20bf8aRA%2FklHuMDFEw1B%2BpFK3W8XxM3%2ButEnWQ7yBlHnujjYrEFffmiWZgRZihFFRnyPp1uUg6pX4KladDftCXh%2BH3fNW%2FS7PtR3ytIkRRLINyNu%2BuDugg6YsTtRRHQmDAXRFoCsvRIJqisROkaH158UoV8%2BeELy0vGI7bL3j6A2F9usDaYQzmeJHIhnySmyGvqL5%2BcSU5N6Ysks%2BbF%2B9xGN9H7Yw%3D%3D
&trade_no=2022100522001403990501989418
&auth_app_id=2021000119683733
&version=1.0
&app_id=2021000119683733
&sign_type=RSA2
&seller_id=2088621958589997
&timestamp=2022-10-05%2014%3A28%3A38



## 支付成功回调
https://dashboard.ngrok.com/get-started/your-authtoken
ngrok config add-authtoken 20AlOclEY4SwG77NZ9EJeGzS9tL_89B8RLFiq5CbwtioVx7pA
https://e249-163-125-202-196.jp.ngrok.io

执行：
ngrock http 9090  --authtoken 20AlOclEY4SwG77NZ9EJeGzS9tL_89B8RLFiq5CbwtioVx7pA


2022-10-05 16:42:41.004  INFO 17936 --- [nio-9090-exec-3] c.demo.mvc.controller.PaymentController  : =====payNotify:
{"gmt_create":"2022-10-05 16:42:36","charset":"UTF-8","seller_email":"yrvhqw4059@sandbox.com","subject":"德运奶粉","sign":"FeJL9w5ASlErlOEBBxVEmp7q0kCJUwsMKqweVqYWf6b3Zj2fCPxZG1aIcIoN3v9Bul+B7tHo6hUeyfZsWvRJOEOQvnbsfSUYlfXeAEemvOwG8U9gQIw2PpAZ6Yss6eIdx/cboISxPnvYEL9svTaykrZR/uLo+JmVqnOs3RLeDMRCV/tjP4Ry+kfCD+cawtgMKkg0NystRk3xo3o48nS5vsrlvBBxV0dSoBrl0uvcXlv7rF2RRn+TtfllaMrOb+7jJ0N4sQZluKdwzk2mVkbrfaTIzKxe5X1p0KaFNDrdV/kwJ8mcPMuV5F+xq7Ef8vDLfI7EQquIYs7joZUvXJkEYw\u003d\u003d","body":"德运奶粉","buyer_id":"2088622958603995","invoice_amount":"50.00","notify_id":"2022100500222164238003990521766283","fund_bill_list":"[{\"amount\":\"50.00\",\"fundChannel\":\"ALIPAYACCOUNT\"}]","notify_type":"trade_status_sync","trade_status":"TRADE_SUCCESS","receipt_amount":"50.00","buyer_pay_amount":"50.00","app_id":"2021000119683733","sign_type":"RSA2","seller_id":"2088621958589997","gmt_payment":"2022-10-05 16:42:38","notify_time":"2022-10-05 16:42:39","version":"1.0","out_trade_no":"1664959326802","total_amount":"50.00","trade_no":"2022100522001403990501989606","auth_app_id":"2021000119683733","buyer_logon_id":"ejh***@sandbox.com","point_amount":"0.00"}

```json
{
	"gmt_create": "2022-10-05 16:42:36",
	"charset": "UTF-8",
	"seller_email": "yrvhqw4059@sandbox.com",
	"subject": "德运奶粉",
	"sign": "FeJL9w5ASlErlOEBBxVEmp7q0kCJUwsMKqweVqYWf6b3Zj2fCPxZG1aIcIoN3v9Bul+B7tHo6hUeyfZsWvRJOEOQvnbsfSUYlfXeAEemvOwG8U9gQIw2PpAZ6Yss6eIdx/cboISxPnvYEL9svTaykrZR/uLo+JmVqnOs3RLeDMRCV/tjP4Ry+kfCD+cawtgMKkg0NystRk3xo3o48nS5vsrlvBBxV0dSoBrl0uvcXlv7rF2RRn+TtfllaMrOb+7jJ0N4sQZluKdwzk2mVkbrfaTIzKxe5X1p0KaFNDrdV/kwJ8mcPMuV5F+xq7Ef8vDLfI7EQquIYs7joZUvXJkEYw\u003d\u003d",
	"body": "德运奶粉",
	"buyer_id": "2088622958603995",
	"invoice_amount": "50.00",
	"notify_id": "2022100500222164238003990521766283",
	"fund_bill_list": "[{\"amount\":\"50.00\",\"fundChannel\":\"ALIPAYACCOUNT\"}]",
	"notify_type": "trade_status_sync",
	"trade_status": "TRADE_SUCCESS",
	"receipt_amount": "50.00",
	"buyer_pay_amount": "50.00",
	"app_id": "2021000119683733",
	"sign_type": "RSA2",
	"seller_id": "2088621958589997",
	"gmt_payment": "2022-10-05 16:42:38",
	"notify_time": "2022-10-05 16:42:39",
	"version": "1.0",
	"out_trade_no": "1664959326802",
	"total_amount": "50.00",
	"trade_no": "2022100522001403990501989606",
	"auth_app_id": "2021000119683733",
	"buyer_logon_id": "ejh***@sandbox.com",
	"point_amount": "0.00"
}

```


## 退款
退款请求返回
```json
{
	"buyerLogonId": "ejh***@sandbox.com",
	"buyerUserId": "2088622958603995",
	"fundChange": "Y",
	"gmtRefundPay": "Oct 5, 2022 9:08:55 PM",
	"outTradeNo": "1664951073209",
	"refundFee": "50.00",
	"sendBackFee": "0.00",
	"tradeNo": "2022100522001403990501989418",
	"code": "10000",
	"msg": "Success",
	"body": "{\"alipay_trade_refund_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"buyer_logon_id\":\"ejh***@sandbox.com\",\"buyer_user_id\":\"2088622958603995\",\"fund_change\":\"Y\",\"gmt_refund_pay\":\"2022-10-05 21:08:55\",\"out_trade_no\":\"1664951073209\",\"refund_fee\":\"50.00\",\"send_back_fee\":\"0.00\",\"trade_no\":\"2022100522001403990501989418\"},\"sign\":\"XtR4ssEXuPiBoPuh5HczDhABAlbog7kkx6SVZqH6czr1SlohifweETEpJMKmiBI5oUSx36UdIPVDRgeD/CLFupiYHO6BFuRXBso9H8cfTKFDH3TIJ3K8VD5ynlgE37JdGgKv6qHgll6sFuK1Qqq23tf1w17EDUvARUhA8r+as9bLlQ1lhuoyavxmYC9BPRRdnLhLxDtutDqNvomA0N/vOrFwHUN4/sj/a3XM1phAhD6GYtKIfZ6QI1FFHh6Qc1oL76TaALzZebfRggAeWxTbaVf0KsFvnt3hIuMFS2M1DPbZ61lD99ZpFInJuXFUuW/aGinKsIlwS7qVlpzKPywJwQ\u003d\u003d\"}",
	"params": {
		"biz_content": "{\"out_trade_no\":\"1664951073209\",\"refund_amount\":\"50.00\",\"out_request_no\":\"1664951073209\"}"
	}
}
```





2022-10-15 18:20:04.188  INFO 7128 --- [   scheduling-1] com.demo.mvc.service.PaymentService      : ====alipayTradeQuery:



{"alipay_trade_refund_response":{"code":"10000","msg":"Success","buyer_logon_id":"ejh***@sandbox.com","buyer_user_id":"2088622958603995","fund_change":"Y","gmt_refund_pay":"2022-10-15 18:20:04","out_trade_no":"T1665829107360","refund_fee":"50.00","send_back_fee":"0.00","trade_no":"2022101522001403990501995122"},"sign":"KB7VCSJBeEDtbfgryMR8KaSsBI9MztqrTPVIRicXaREPs2Cili7UBb8PT/M9S/KvJnN1xHb1Gmi9h9H5XGdAjhQAK/1yFBJ1MTO6DQPtCDfV7BUuwlPoZBKlHVQM2hd6g0X2kDuTSoLmJDWqxWtD63jOFKnOo2haqnpsd9vfB9m255V1iulYrdghwcyvcbJAepma2oVqJuIr7TfBmMiz6k0w5jYzCJpErOo6ebUWDcFS3ctFki/j057/oe7PAFx15Q8E2LQt1eVqrKqB8XVHNoimx1gP7A3iptF6f5Nf0X6YawNd3GTY7y6CSYjCfyNRUWbVq513CeniGcFulPQPTQ=="}


