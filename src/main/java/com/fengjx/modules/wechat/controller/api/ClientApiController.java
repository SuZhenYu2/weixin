package com.fengjx.modules.wechat.controller.api;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengjx.modules.wechat.bean.Result;
import com.fengjx.modules.wechat.service.WechatPublicAccountService;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
@Controller
public class ClientApiController {
	@Autowired
	private WechatPublicAccountService publicAccountService;
	/**
	 * 客户端 调用微信  接口认证
	 * @throws IOException 
	 */
	@RequestMapping(value = "${clientApi}/getQrCode")
	public void getQrCode(String userId,String param,HttpServletResponse response) throws IOException {
		String res =null;
		OutputStream os =response.getOutputStream();
		try {

			if(StringUtils.isEmpty(userId)){
				throw new Exception(" userId 不能为空 ");
			}
			if(publicAccountService.getAccountByUserId(userId) == null) {
				throw new Exception(" 未找到相关配置  ");
			}
			WxMpService wxMpService = publicAccountService.getWxMpService(userId);


			WxMpQrCodeTicket wxMpQrCodeTicket = wxMpService.qrCodeCreateTmpTicket(10000, 10000);

			wxMpQrCodeTicket.setUrl(param);
			File file =	wxMpService.qrCodePicture(wxMpQrCodeTicket);
			os.write(FileUtils.readFileToByteArray(file));
			os.flush();
			return;
		} catch (Exception e) {
			res =e.getMessage();
		}
		os.write(res.getBytes());
		os.flush();

	}
	@RequestMapping(value = "${clientApi}/getUserInfo")
	@ResponseBody
	public Result getUserInfo(String userId,String openId,HttpServletResponse response) throws IOException {


		try {
			if(StringUtils.isEmpty(userId)){
				throw new Exception(" userId 不能为空 ");
			}

			if(publicAccountService.getAccountByUserId(userId) == null) {
				throw new Exception(" 未找到相关配置  ");
			}
			WxMpService wxMpService = publicAccountService.getWxMpService(userId);
 			
			WxMpUser wxMpUser =wxMpService.userInfo(openId, "zh_CN");
			
			return Result.renderSuccess(wxMpUser);

		} catch (Exception e) {
			return Result.renderError(e.getMessage());
		}
	 


	}
	@RequestMapping(value = "${clientApi}/sendMsgToWx")
	@ResponseBody
	public Result wxMpTemplateMessage(String userId,String openId,String templateId,HttpServletResponse response) throws IOException {
		
		
		try {
			if(StringUtils.isEmpty(userId)){
				throw new Exception(" userId 不能为空 ");
			}
			if(publicAccountService.getAccountByUserId(userId) == null) {
				throw new Exception(" 未找到相关配置  ");
			}
			WxMpService wxMpService = publicAccountService.getWxMpService(userId);
			
			WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
			
			wxMpTemplateMessage.setToUser(openId);
			wxMpTemplateMessage.setTemplateId(templateId);
			 
			String res =wxMpService.templateSend(wxMpTemplateMessage );
			
 			
			return Result.renderSuccess(res);
			
		} catch (Exception e) {
			return Result.renderError(e.getMessage());
		}
		
		
		
	}
	@RequestMapping(value = "${clientApi}/getPayInfo")
	@ResponseBody
	public Result getPayInfo(
			String userId,
			String openId, 
			String outTradeNo, 
			Double amt, 
			String body,
			String tradeType,
			String ip, 
			String notifyUrl) throws IOException {
		
		
		try {
			if(StringUtils.isEmpty(userId)){
				throw new Exception(" userId 不能为空 ");
			}
			if(publicAccountService.getAccountByUserId(userId) == null) {
				throw new Exception(" 未找到相关配置  ");
			}
			WxMpService wxMpService = publicAccountService.getWxMpService(userId);
			
			
			/*  *//**
		     * 统一下单(详见http://pay.weixin.qq.com/wiki/doc/api/index.php?chapter=9_1)
		     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
		     * @param openId 支付人openId
		     * @param outTradeNo 商户端对应订单号
		     * @param amt 金额(单位元)
		     * @param body 商品描述
		     * @param tradeType 交易类型 JSAPI，NATIVE，APP，WAP
		     * @param ip 发起支付的客户端IP
		     * @param notifyUrl 通知地址
		     * @return
		     *//*
		    WxMpPrepayIdResult getPrepayId(String openId, String outTradeNo, double amt, String body, String tradeType, String ip, String notifyUrl);

		    *//**
		     * 该接口调用“统一下单”接口，并拼装JSSDK发起支付请求需要的参数
		     * 详见http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E5.8F.91.E8.B5.B7.E4.B8.80.E4.B8.AA.E5.BE.AE.E4.BF.A1.E6.94.AF.E4.BB.98.E8.AF.B7.E6.B1.82
		     * @param openId 支付人openId
		     * @param outTradeNo 商户端对应订单号
		     * @param amt 金额(单位元)
		     * @param body 商品描述
		     * @param tradeType 交易类型 JSAPI，NATIVE，APP，WAP
		     * @param ip 发起支付的客户端IP
		     * @param notifyUrl 通知地址
		     * @return
		     *//*
		    Map<String, String> getJSSDKPayInfo(String openId, String outTradeNo, double amt, String body, String tradeType, String ip, String notifyUrl);
*/
			//Map map =wxMpService.getJSSDKPayInfo(openId, outTradeNo, amt, body, tradeType, ip, notifyUrl);
			Map<String,String> payInfo=new HashMap<>(); 
			payInfo.put("userId", userId);
			payInfo.put("openId", openId);
			payInfo.put("out_trade_no", outTradeNo);
			payInfo.put("amt", amt+"");
			payInfo.put("tradeType", tradeType);
			payInfo.put("ip", ip);
			payInfo.put("notifyUrl", notifyUrl);
			payInfo.put("body", body);
		  
			Map<String, String> map =wxMpService.getJSSDKPayInfo( openId,  outTradeNo,  amt,  body,  tradeType,  ip,  notifyUrl);
//			Map<String, String> map   =	wxMpService.getJSSDKPayInfo(payInfo);
			
			return Result.renderSuccess(map);
			
		} catch (Exception e) {
			return Result.renderError(e.getMessage());
		}
		
		
		
	}
}
