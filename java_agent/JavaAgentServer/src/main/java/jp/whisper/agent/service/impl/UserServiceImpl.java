package jp.whisper.agent.service.impl;

import org.springframework.stereotype.Component;

import jp.whisper.agent.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	public String getRemark(String name) {
		return  name +" (jp) ";
	}

}
