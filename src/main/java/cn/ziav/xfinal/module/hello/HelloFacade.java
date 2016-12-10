package cn.ziav.xfinal.module.hello;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.ziav.xfinal.common.exception.Result;
import cn.ziav.xfinal.module.Modules;

@RequestMapping(Modules.HELLO_MODULE)
public interface HelloFacade {
	@RequestMapping("/hello/{param}")
	Result<String> hello(String param);
}
