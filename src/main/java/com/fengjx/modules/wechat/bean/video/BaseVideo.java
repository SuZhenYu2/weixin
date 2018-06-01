package com.fengjx.modules.wechat.bean.video;

import java.util.List;

/**
 * Created by gaochen on 2018/4/22.
 * 视频信息基类
 */
 
 public class BaseVideo {

    private String name;

    private String url;

    private String imageUrl;

    private String description;

    private String from;

    private List<Episode> episodes;

	public BaseVideo(String name2, String url2, String imageUrl2, String description2, String from,
			List<Episode> episodes2) {
		name=name2;
		url=url2;
		imageUrl=imageUrl2;
		description=description2;
		this.from=from;
		episodes=episodes2;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

 
}