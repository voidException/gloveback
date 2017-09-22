package org.geilove.controller;

/**
 * 支付宝支付，主要是订单生成，和通知回调
 */
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.geilove.requestParam.AlipayOrderParam;
import org.geilove.response.AlipayOrderRsp;
import org.geilove.utilAlipay.config.AlipayConfig;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
    /**
     * 支付下订单，生成预订单
     */

    @RequestMapping(value="/getOrder.do",method=RequestMethod.POST)
    @ResponseBody
    public Object payOrder(  @RequestBody AlipayOrderParam httpequest){
        AlipayOrderRsp alipayOrderRsp=new AlipayOrderRsp();

        if (httpequest==null){
            alipayOrderRsp.setRetcode(2001);
            alipayOrderRsp.setMsg("请求参数为空");
            alipayOrderRsp.setOderStr(null);
            return  alipayOrderRsp;
        }

        String  body=httpequest.getBody();           //对一笔交易的具体描述信息。httpequest传输过来 userUUID
        String  subject="中青年互助计划";              //商品的标题/交易标题/.  httpequest传输过来(互助类别)categoryType
        String  out_trade_no="outtradeno20170931";                     // 商户订单号，本地生成 payMoneyUUID
        String  total_amount=httpequest.getAmount(); //App传来的总金额
        String  passback_params="accountUUIDuserNameaihaitao";        //公众回传参数，这里我放进去App传过来的accountUUID和userName，

        String  seller_id="2088911776278734";        //合作者账号，PID，非必须

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gateway_url,
                AlipayConfig.app_id, AlipayConfig.alipay_private_key,
                AlipayConfig.format, AlipayConfig.charset,
                AlipayConfig.alipay_public_key, AlipayConfig.sign_type
        );

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

        model.setBody(URLEncoder.encode(body));
        model.setSubject(subject); // 商品标题
        model.setOutTradeNo(out_trade_no); // 商家订单编号(13位时间戳+4位认证人员信息表id后四位，不足前面补0)
        model.setTimeoutExpress(AlipayConfig.timeout_express); // 超时关闭该订单时间
        model.setTotalAmount(total_amount); // 订单总金额
        model.setProductCode(AlipayConfig.product_code); // 销售产品码，商家和支付宝签约的产品码，
        model.setPassbackParams(passback_params); // 描述信息 添加附加数据
        model.setSellerId(seller_id);
        request.setBizModel(model); //业务参数.参见文档

        request.setNotifyUrl(AlipayConfig.notify_url); // 回调地址
        String orderStr = "";  //最终加密的字符串
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            orderStr = response.getBody();

            alipayOrderRsp.setOderStr(orderStr);
            alipayOrderRsp.setRetcode(2000);
            alipayOrderRsp.setMsg("下单成功");
            System.out.println(orderStr);//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
            alipayOrderRsp.setRetcode(2001);
            alipayOrderRsp.setMsg("下单抛出异常");

        }
        return alipayOrderRsp;  //返回结果
    }

    /*alipay 支付异步通知*/
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value="/notify.do",method=RequestMethod.POST)
    public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter out = response.getWriter();

       //  获取支付宝POST过来反馈信息
        //logger.debug("支付宝异步回调");
        Map<String, String> params = new HashMap<String, String>();

        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                //logger.debug(valueStr);
            }
            // 乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }


        boolean flag = AlipaySignature.rsaCheckV1(params,
                AlipayConfig.alipay_public_key,
                AlipayConfig.charset,
                AlipayConfig.sign_type);

        if (flag) {
            if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
                // 订单金额
                String amount = params.get("total_amount");
                // 商户订单号
                String out_trade_no = params.get("out_trade_no");
                // 支付宝交易号
                String trade_no = params.get("trade_no");
                // 买家支付宝用户号
                String buyer_id = params.get("buyer_id");
                // 交易创建时间 格式为yyyy-MM-dd HH:mm:ss
                String gmt_create = params.get("gmt_create");
                // ...
                // 获取自己填充得信息如subject等，


                out.print("success");
                //logger.debug("-----支付宝异步通知成功----");

            } else {
                //logger.debug("-----支付宝异步通知，订单未成功付款----");
                out.print("failure");
            }
        }
            //logger.debug("-----支付宝异步通知，订单验证错误----");
        out.print("failure");
    } //notify


    /*alipay 支付异步通知*/
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/notify2.do",method=RequestMethod.GET)
    public String aliPay_notify(HttpServletRequest request, HttpServletResponse response)throws AlipayApiException, IOException{

//        Map requestParams=request.getParameterMap();
//        //System.out.println("支付宝支付结果通知"+requestParams.toString());
//        //获取支付宝POST过来反馈信息
//        Map<String,String> params = new HashMap<String,String>();
//
//        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//            }
//            //乱码解决，这段代码在出现乱码时使用。
//            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//            params.put(name, valueStr);
//        }
//        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
//        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
//        try {
//            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
//                    AlipayConfig.charset,
//                    AlipayConfig.sign_type);
//
//            if(flag){
//                if("TRADE_SUCCESS".equals(params.get("trade_status"))){
//                    //付款金额
//                    String amount = params.get("buyer_pay_amount");
//                    //商户订单号
//                    String out_trade_no = params.get("out_trade_no");
//                    //支付宝交易号
//                    String trade_no = params.get("trade_no");
//                    //附加数据
//                    String passback_params = URLDecoder.decode(params.get("passback_params"));
//
//                }
//            }
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return "failure";
//        }

        return  "success";
    }

    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }
}




