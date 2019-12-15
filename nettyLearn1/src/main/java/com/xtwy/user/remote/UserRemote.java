package com.xtwy.user.remote;

import java.util.List;

import com.xtwy.netty.util.Response;
import com.xtwy.user.bean.User;

public interface UserRemote {
	public Response saveUser(User user);
	
	public Response saveUsers(List<User> users);
}
