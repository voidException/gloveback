package org.geilove.utilAlipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    public  static  String  gateway_url="https://openapi.alipay.com/gateway.do";  //固定值

    // APPID
    public static String app_id = "2017081908269445";
    // 商户的私钥 AES密钥
    public static String alipay_private_key = "L9B6it/YLFdaA873wm242Q==";

    public  static  String  format="json";

    public static String charset = "utf-8";

    //应用的公钥
    public  static String  alipayApp_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsz4FnWv2m5R1LT8EcKU2KG1gi3250eTUZWTHxumFWdA9cyLen+IYKtkAN4jH4lGL69UiHcgFj9WzK4wRJZGOs/LJLNMD8q0rXBHJeSF2/V7ei6JxxkPKAWn4fTb9prYoBLXVsyP59V5+n1pJchSeIHJdZ5QRxdWZslRfkb/oYkf68MiWhOE6nx2XAy/D1eIH2BKNVtCYhocjzWC4JVYAF8oCX2Dv/bp9Dm9X4u/XPgWpkkEsChb11AbryT0pb5EiH4RHmNyVsa0JQZVPcLlPmQxbgVepD7NOFs9XdBtbJijyuLBVY5gm+4NwC9Fa1DW3Y15UslCFsgnysFN7+Ug67wIDAQAB";

    // 支付宝的公钥，无需修改该值
    public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhT0ikGs8LX5iGEZB4GejBgcjxcqslHvovBlZp3K5iXSYmhCC5FpOfmlNAiwlA6n2MXgX526b/FxUfmoUtWVl/SYKBHdDgIhATblXVcsH2S+jBoCLWdL3t4m4a1g1QxhgaPlnI7rJDwnGhsKj0cKmwgAdI2DGjtDn7C9ZP4wLolwb0sxGO4xWHrEdBj49GkbSoH5rzaIR2vfhdz70L7tQlz9TNAUhXWtuSvnlCYnKVBO+K5CZUWwim3ppGQUFRk5+KfpW+bxSg0cmgJ5BRFm1SFjZ1kg6DTr2lD+ndHTOMyX4w+9bZvM1fneD4MIG87iIOWDyxaYhCpj7g4W5XwghwwIDAQAB";

    // 签名方式 不需修改
    public static String sign_type = "RSA2";

    //
    public static String product_code="QUICK_MSECURITY_PAY"; //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY

    //订单超时关闭时间
    public  static  String  timeout_express="30m";

    //回调通知地址
    public  static String  notify_url="http://www.putaohuzhu.cn/glove/auth/authredirect.do";

    //日志输出地址
    public  static  String  log_path="/";


}
