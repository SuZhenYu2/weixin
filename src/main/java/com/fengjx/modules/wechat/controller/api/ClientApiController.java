package com.fengjx.modules.wechat.controller.api;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

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
}
