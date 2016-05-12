package com.bishe.home.service;

import com.bishe.home.domain.Message;

public interface Observer {
	
	 public void publish(Message message);

}
