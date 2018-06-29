
package com.fengjx.modules.wechat.process.ext.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.modules.wechat.bean.video.BaseVideo;
import com.fengjx.modules.wechat.process.ext.ExtService;
import com.fengjx.modules.wechat.service.video.SearchServiceHandler;

import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

/**
 * 
 * @ClassName: MovieExtService
 * @Description: 電影插件
 * @author: suzy2
 * @date: 2018年6月25日 下午11:06:09
 */
@Service("movie")
public class MovieExtService implements ExtService {

	private static final Logger LOG = LoggerFactory.getLogger(MovieExtService.class);

	/**
	 * @param inMessage
	 *            微信消息
	 * @param accountRecord
	 *            公众平台信息
	 * @param wxMpConfig
	 *            公众平台配置
	 * @param session
	 *            微信session
	 * @return
	 */
	@Override
	public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord, WxMpConfigStorage wxMpConfig,
			WxSession session) {

		WxMpXmlOutNewsMessage news = WxMpXmlOutMessage.NEWS().build();
		// 优酷视频搜索接口

		List<BaseVideo> list = SearchServiceHandler.getInstance().search(inMessage.getContent());
		// 查询并解析结果

		WxMpXmlOutNewsMessage.Item item = null;
		for (BaseVideo baseVideo : list) {
			if(news.getArticleCount()>5){
				break;
			}
			item = new WxMpXmlOutNewsMessage.Item();
			item.setDescription(baseVideo.getDescription());
			item.setPicUrl(baseVideo.getImageUrl());
			item.setTitle(baseVideo.getName());
			item.setUrl(baseVideo.getUrl());
			news.addArticle(item);
		}

		return news;

	}

	public static void main(String[] args) {
		WxMpXmlOutNewsMessage news = WxMpXmlOutMessage.NEWS().build();
		// 优酷视频搜索接口
 		List<BaseVideo> list = SearchServiceHandler.getInstance().search("西部世界");
		// 查询并解析结果

		WxMpXmlOutNewsMessage.Item item = null;
		for (BaseVideo baseVideo : list) {
			item = new WxMpXmlOutNewsMessage.Item();
			item.setDescription(baseVideo.getDescription());
			item.setPicUrl(baseVideo.getImageUrl());
			item.setTitle(baseVideo.getName());
			item.setUrl(baseVideo.getUrl());
			news.addArticle(item);
		}

		System.out.println(news.toXml());
		;
	}
}
