
package com.fengjx.modules.wechat.process.executor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.CommonUtils;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.wechat.process.utils.ExecutorNameUtil;
import com.fengjx.modules.wechat.service.WechatUserInfoService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 用户关注消息处理器
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class EventSubscribeExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(EventSubscribeExecutor.class);

    @Autowired
    private WechatUserInfoService userInfoService;

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
       
        LogUtil.info(LOG, "进入用户关注消息处理器fromUserName=" + JSON.toJSONString(inMessage));
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("id", CommonUtils.getPrimaryKey());
        attrs.put("openid", inMessage.getFromUser());
        attrs.put("subscribe_time", new Date());
        attrs.put("public_account_id", accountRecord.getStr("id"));
        attrs.put("headimgurl",  inMessage.getPicUrl());
        attrs.put("nickname",  inMessage.getToUser());
        attrs.put("province",  inMessage.getSendLocationInfo().getLabel());
//        attrs.put("sex", inMessage.getScale());
        userInfoService.insert(attrs);
        return doAction(WxConsts.XmlMsgType.EVENT, WxConsts.EventType.SUBSCRIBE, null,
                accountRecord.getStr("sys_user_id"));
    }

    @Override
    public String getExecutorName() {
        return ExecutorNameUtil.buildName(WxConsts.XmlMsgType.EVENT,WxConsts.EventType.SUBSCRIBE);
    }

}
