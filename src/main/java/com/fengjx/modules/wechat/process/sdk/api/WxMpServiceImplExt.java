
package com.fengjx.modules.wechat.process.sdk.api;

import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;

public class WxMpServiceImplExt extends WxMpServiceImpl implements WxMpServiceExt {

    @Override
    public WxMpMassSendResult massPreviewMessage(WxMpMassOpenIdsMessage message)
            throws WxErrorException {
        if (message.getToUsers().size() > 1) {
            WxError wxError = WxError.builder().build();
            wxError.setErrorMsg("预览消息 to User只能有一个");
            throw new WxErrorException(wxError);
        }
        // https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
        String json = message.toJson();
        // 预览只支持一个to User,去掉toUser list的[]
        json = json.replace("[", "").replace("]", "");

        String responseContent = execute(SimplePostRequestExecutor.create(this.getRequestHttp()), url, json);
        return WxMpMassSendResult.fromJson(responseContent);
    }





}
