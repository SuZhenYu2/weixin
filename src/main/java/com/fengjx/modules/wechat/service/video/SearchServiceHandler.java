package com.fengjx.modules.wechat.service.video;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fengjx.modules.wechat.bean.video.BaseVideo;
import com.fengjx.modules.wechat.service.video.iqiyi.IqiyiSearchVideoServiceImpl;
import com.fengjx.modules.wechat.service.video.tencent.TenSearchVideoServiceImpl;
import com.fengjx.modules.wechat.service.video.youku.YouKuSearchVideoServiceImpl;

/**
 * Created by gaochen on 2018/5/15.
 */
public class SearchServiceHandler implements ISearchVideoService {

    private static SearchServiceHandler searchServiceHandler = new SearchServiceHandler();

    private static List<ISearchVideoService> searchVideoServiceList = new ArrayList<>();

    static {
        //todo
        searchVideoServiceList.add(new TenSearchVideoServiceImpl());
        searchVideoServiceList.add(new YouKuSearchVideoServiceImpl());
        searchVideoServiceList.add(new IqiyiSearchVideoServiceImpl());
    }

    public static SearchServiceHandler getInstance() {
        return searchServiceHandler;
    }

    @Override
    public List<BaseVideo> search(String key) {
        return searchVideoServiceList.stream()
                .map(service -> service.search(key))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
