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
 * 微信支付相关功能 公众号支付  扫码支付
 */					   
@RequestMapping(value="/wechatpay")
@Controller
public class WeChatPayController {
	Logger logger= Logger.getLogger(this.getClass());

	/**
	 * 跳转到微信支付
	 * 这里的orderId 可以看做是MoneySource的一个记录的UUID
	 */
	@RequestMapping(value="/toPay/{orderId}/code.do")
	public String toPay(@PathVariable String orderId, HttpServletRequest request, HttpServletResponse response){

		//这个应该是授权回调页面域名geilove.org/glove/ 下面的
		String orginUrl = "http://geilove.org/glove/authorize/userOpeniD/"+orderId+"/preOrder.do";
		String encodeUrl = URLEncoder.encode(orginUrl);
		String resultUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx13eeb70a6cad4d76&redirect_uri="+encodeUrl+"&response_type=code&scope=snsapi_base&state="+orderId+"#wechat_redirect";

		logger.info(resultUrl);
		System.out.println("toPay页面");

		return "redirect:"+resultUrl;
	}

	//只有这里显示支付成功了才是真的成功，才能入库
	@RequestMapping(value="/wxNotifyUrl")
	public ModelAndView wxNotify(@RequestBody String wxData, HttpServletRequest request, HttpServletResponse response){
		logger.info("微信支付完成回调,返回的参数：");
		try {
			String return_code = "FAIL";
			String return_msg = "";
			Map<String, String> map = new HashMap<String, String>();
			Document document = DocumentHelper.parseText(wxData);
			Element root = document.getRootElement();
			for(Iterator i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				map.put(element.getName(), element.getText());
			}
			if(WxUrlUtils.checkSign(map)){
				logger.info("签名验证通过!");
				logger.info(JSONObject.toJSON(map));
				String trade_no = map.get("transaction_id");//交易号
				String out_trade_no = map.get("out_trade_no");//订单号
				logger.info("解析返回状态");
				if(map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")){
					logger.info("=============支付成功-更新业务状态start=============");
					return_code = "SUCCESS";
					return_msg = "ok";
					logger.info("=============支付成功-更新业务状态end=============");
				}
			} else {
				logger.error("支付成功返回数据，签名不合法");
			}
			String xml = "<xml><return_code>"+return_code+"</return_code><return_msg>"+return_msg+"</return_msg></xml>";
			response.getWriter().write(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/createWeChatOrder/{money}", method= RequestMethod.GET)
	public void createWeChatOrder(Model model, @PathVariable Float money, HttpServletResponse reponse){
		try {
			logger.info("提交微信订单");
				//保存订单
			String generateWxQRCode = WxUrlUtils.generateWxQRCode("2016083000001");
			ByteArrayOutputStream stream = QRCode.from(generateWxQRCode).withSize(160, 160).stream();
			ServletOutputStream outputStream = reponse.getOutputStream();
			stream.writeTo(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="wxPayBack")
	public ModelAndView wxPayBack(@RequestBody String wxData, HttpServletResponse response){
		logger.info("微信支付过程回调,返回的参数：");
		try {
			String return_code = "FAIL";
			String return_msg = "无效";
			String err_code_des = "此商品无效";
			String result_code = "FAIL";
			String prePayId = null;
			Map<String, String> map = new HashMap<String, String>();
			Document document = DocumentHelper.parseText(wxData);
			Element root = document.getRootElement();
			for(Iterator i = root.elementIterator(); i.hasNext();) {
			    Element element = (Element) i.next();
			    map.put(element.getName(), element.getText());
			}
            logger.info("payBack params:" + map);
			if(WxUrlUtils.checkSign(map)){
				logger.info("签名验证通过! 调用【统一下单API】提交支付交易");
				map.put("body", "测试body");
				map.put("total_fee", "1");//测试 1分
				prePayId = WxHttpClientUtils.getPrePayId(map);
				if(StringUtils.isNotBlank(prePayId)){
					return_code = "SUCCESS";
					result_code = "SUCCESS";
					return_msg = "";
					err_code_des = "";
				}
			} else {
				return_msg = "签名失败";
				err_code_des = "签名验证没有通过";
			}
			String xml = WxHttpClientUtils.getReturnXml(prePayId, return_code, return_msg, err_code_des, result_code);
			response.getWriter().write(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}