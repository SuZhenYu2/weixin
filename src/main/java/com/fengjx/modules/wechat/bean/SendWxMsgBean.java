package com.fengjx.modules.wechat.bean;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

public class SendWxMsgBean {
	private String userId;
	private String openId;
	private String templateId;
	private List<WxMpTemplateData> datas;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public List<WxMpTemplateData> getDatas() {
		return datas;
	}
	public void setDatas(List<WxMpTemplateData> datas) {
		this.datas = datas;
	}
	public static void main(String[] args) {
		SendWxMsgBean sendWxMsgBean = new SendWxMsgBean();
		List<WxMpTemplateData> datas = new ArrayList<>();
		WxMpTemplateData wxMpTemplateData = new WxMpTemplateData();
		wxMpTemplateData.setName("123");
		wxMpTemplateData.setValue("12312");
		datas.add(wxMpTemplateData);
		sendWxMsgBean.setDatas(datas);
		sendWxMsgBean.setOpenId("123123");
		System.out.println(JSON.toJSON(sendWxMsgBean));
		
	}
	
	
}
