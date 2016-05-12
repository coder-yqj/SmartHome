package com.bishe.home.service;

import java.util.ArrayList;
import java.util.List;

import com.bishe.home.domain.Message;

public abstract class Subject {
	
	private List<Observer> list = new ArrayList<Observer>();
	
	public void attach(Observer observer){
        
        list.add(observer);
//        System.out.println("Attached an observer");
    }
	
	public void detach(Observer observer) {

		list.remove(observer);
	}
	
	 public void nodifyObservers(Message message){
	        
	        for(Observer observer : list){
	            observer.publish(message);
	        }
	    }
	
}
