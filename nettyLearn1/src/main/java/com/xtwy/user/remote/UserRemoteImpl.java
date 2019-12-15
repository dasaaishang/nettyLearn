package com.xtwy.user.remote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xtwy.netty.annotation.Remote;
import com.xtwy.netty.util.Response;
import com.xtwy.netty.util.ResponseUtil;
import com.xtwy.user.bean.User;
import com.xtwy.user.service.UserService;

@Remote
public class UserRemoteImpl implements UserRemote {

	@Autowired
	UserService uservice;

	public Response saveUser(User user) {

		 uservice.save(user);
		 return ResponseUtil.createSuccessResult(user);
	}
	
	public Response saveUsers(List<User> users) {
		
		uservice.saveList(users);
		return ResponseUtil.createSuccessResult(users);
	}
}
