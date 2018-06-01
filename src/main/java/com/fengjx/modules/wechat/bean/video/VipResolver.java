package com.fengjx.modules.wechat.bean.video;

/**
 * Created by gaochen on 2018/5/16.
 */

public class VipResolver {
	private String name;

	private String url;

	private boolean checked;

	private VIPResolverTypeEnum type;

	public VipResolver(String vipName, String vipUrl, boolean b, VIPResolverTypeEnum typeOf) {
		name=vipName;
		url=vipUrl;
		checked=b;
		type=typeOf;
	}
	public VipResolver() {
		
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public VIPResolverTypeEnum getType() {
		return type;
	}

	public void setType(VIPResolverTypeEnum type) {
		this.type = type;
	}

}
