package com.bishe.home.domain;

import net.sf.json.JSONObject;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.bishe.home.service.Observer;

public class Device implements Observer {
	private Message message;
	private Long channelId;
	private String userId;

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Device() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Device(Long channelId, String userId) {
		super();
		this.channelId = channelId;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Device [channelId=" + channelId + ", userId=" + userId + "]";
	}

	@Override
	public void publish(Message newMessage) {
		this.message = newMessage;
		PublishMsg psh = new PublishMsg(channelId, userId);
		JSONObject notification = JSONObject.fromObject(message);
		try {
			psh.push(notification);
		} catch (PushClientException e) {
			e.printStackTrace();
		} catch (PushServerException e) {
			e.printStackTrace();
		}

	}

}
