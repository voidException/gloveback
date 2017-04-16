package org.geilove.controller;
import com.alibaba.fastjson.JSONObject;
import org.geilove.utils.WeChatUtils;
import org.geilove.utils.WxHttpClientUtils;
import org.geilove.utils.WxUrlUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import net.glxn.qrgen.javase.QRCode;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 微信授权回调页面url
 * 这里用path，是技术上限制啊，回来好好研究tomcat8 配置
 * 在WeChatayController 里面，发起授权，微信会跳转到此页面
 */
@Controller
@RequestMapping(value="/path")
public class WxAuthorize {
    @RequestMapping(value="/authorizeCallBack/{orderId}")
    public String payByOrderId(@PathVariable Long orderId, Model model , HttpServletRequest request, HttpServletResponse response) throws Exception{
        String openId = "";
        if (request.getParameter("code")!=null) {
            String code = request.getParameter("code").toString();
            openId = WeChatUtils.getOpenId(code);

        }

        //orderId 为paymainId
        String attach = "测试订单attach";
        String body = "测试数据";
        System.out.println("payView");
        model.addAttribute("payMainId", "20160830110001");//FIXME 测试订单id
        model.addAttribute("openId", "asdfasdfasdfa8888");//FIXME 商户id 替换为自己的订单id

        //调用微信支付统一下单接口
        Map<String, String> orderParam = new HashMap<String, String>();
        orderParam.put("attach", attach);
        orderParam.put("body", body);
        orderParam.put("openid", openId);
        orderParam.put("out_trade_no", orderId.toString());
        orderParam.put("ip", request.getRemoteAddr());
        orderParam.put("total_fee", "1");//FIXME 测试数据 一分

        String prePayId = WxHttpClientUtils.getPrePayIdH5(orderParam);
        //封装h5页面调用参数
        Map<String, String> paySign = WxUrlUtils.generatePaySign(prePayId);

        model.addAttribute("paytimestamp", paySign.get("timeStamp"));
        model.addAttribute("paypackage", "prepay_id="+prePayId);
        model.addAttribute("prePayId", prePayId);
        model.addAttribute("paynonceStr", paySign.get("nonceStr"));
        model.addAttribute("paysignType", "MD5");
        model.addAttribute("paySign",paySign.get("sign") );

        return "front/payView";
    }

}
