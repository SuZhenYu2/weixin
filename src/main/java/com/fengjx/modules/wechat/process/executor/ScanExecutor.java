package com.fengjx.modules.wechat.process.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.utils.LogUtil;
import com.fengjx.modules.wechat.process.utils.ExecutorNameUtil;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

public class ScanExecutor extends BaseServiceExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(LocationExecutor.class);

    @Override
    public WxMpXmlOutMessage execute(WxMpXmlMessage inMessage, Record accountRecord,
            WxMpConfigStorage wxMpConfig, WxSession session) {
        LogUtil.info(LOG, "进入菜单点击消息处理器fromUserName=" + inMessage.getFromUserName());
        return doAction(WxConsts.XML_MSG_EVENT, WxConsts.EVT_SCAN, inMessage.getEventKey(),
                accountRecord.getStr("sys_user_id"));
    }

    @Override
    public String getExecutorName() {
        return ExecutorNameUtil.buildName(WxConsts.XML_MSG_EVENT, WxConsts.EVT_SCAN);
    }

}