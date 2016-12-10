package cn.ziav.xfinal.module.hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.ziav.xfinal.common.exception.Result;

@RestController
public class HelloFacadeImpl implements HelloFacade {

	@Override
	public Result<String> hello(@PathVariable String param) {
		return Result.SUCCESS("hello: " + param);
	}

}
