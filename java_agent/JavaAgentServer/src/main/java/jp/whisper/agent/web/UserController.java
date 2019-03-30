package jp.whisper.agent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.whisper.agent.service.UserService;


@RestController
public class UserController {
	@Autowired
    private UserService userService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "wellcome,  " + userService.getRemark("whisper");
	}
}
