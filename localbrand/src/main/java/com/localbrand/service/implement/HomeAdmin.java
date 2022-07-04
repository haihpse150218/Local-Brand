package com.localbrand.service.implement;

import com.localbrand.service.IHomeAdmin;

public class HomeAdmin implements IHomeAdmin {
	private HomeAdmin() {}
	private static HomeAdmin instance = new HomeAdmin();
	
	public static HomeAdmin getInstance () {
		if(instance == null) {
			instance = new HomeAdmin();
		}
		return instance;
	}
	
	//public 
}
