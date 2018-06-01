package com.fengjx.modules.wechat.service.video;

import java.util.List;

import com.fengjx.modules.wechat.bean.video.BaseVideo;

/**
 * Created by gaochen on 2018/4/22.
 * 视频搜索接口
 */
public interface ISearchVideoService {
    /**
     * 搜索视频
     * @param key 关键字
     * @return
     */
    List<BaseVideo> search(String key);
}