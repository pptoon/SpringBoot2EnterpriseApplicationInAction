package org.fkit.oa.util;

import org.fkit.oa.identity.domain.User;

public class UserHolder {
	/** 定义一个ThreadLocal 存储当前某个请求线程对应的登陆用户   */
	private static ThreadLocal<User> users = new ThreadLocal<>();
	
	public static void addCurrentUser(User user){
		if(users.get()== null){
			users.set(user);
		}
	}
	
	public static User getCurrentUser(){
		return users.get();
	}

	public static void removeCurrentUser() {
		users.remove();
	}
}
