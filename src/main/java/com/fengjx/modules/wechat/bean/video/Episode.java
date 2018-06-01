package com.fengjx.modules.wechat.bean.video;

/**
 * Created by kitchen on 2018/4/22.
 * 剧集
 */
 
public class Episode {
    private String name;

    private String url;

	public Episode(String asText, String asText2) {
		
		name=asText;
		url=asText2;
 	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}