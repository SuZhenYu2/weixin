
package com.fengjx.modules.wechat.process.ext.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.api.restful.WeatherServiceApi;
import com.fengjx.modules.wechat.process.ext.ExtService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

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
     * @param inMessage 微信消息
     * @param accountRecord 公众平台信息
     * @param wxMpConfig 公众平台配置
     * @param session 微信session
     * @return
     */
    @Override
    public String execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
        String resContent = "";
        if (WxConsts.XmlMsgType.TEXT.equals(inMessage.getMsgType())) {
            String content = inMessage.getContent();
            String keyWord = content.replaceAll("^天气", "").trim();
            LogUtil.info(LOG, "天气查询：" + keyWord);
            if ("".equals(keyWord)) {
                resContent = WeatherServiceApi.getWeatherUsage();
            } else {
                resContent = WeatherServiceApi.queryhWeather(keyWord);
            }
            return WxMpXmlOutMessage.TEXT().content(resContent).fromUser("").toUser("").build()
                    .toXml();
        }
        if (WxConsts.XmlMsgType.LOCATION.equals(inMessage.getMsgType())) {
            // 接收用户发送的文本消息内容
            Double Location_X = inMessage.getLocationX();
            Double Location_Y = inMessage.getLocationY();
            String label = inMessage.getLabel();
            String keyWord = Location_Y + "," + Location_X;
            LogUtil.info(LOG, "天气查询：" + keyWord + ", label=" + label);
            resContent = WeatherServiceApi.queryhWeather(keyWord);
            return WxMpXmlOutMessage.TEXT().content(resContent).fromUser("").toUser("").build()
                    .toXml();
        } else {
            throw new RuntimeException("请求的消息类型不支持该接口");
        }
    }
}
