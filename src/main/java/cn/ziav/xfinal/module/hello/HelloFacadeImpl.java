package cn.ziav.xfinal.module.hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.ziav.xfinal.common.data.DataAccess;
import cn.ziav.xfinal.common.data.EntityService;
import cn.ziav.xfinal.common.exception.Result;
import cn.ziav.xfinal.module.seckill.manager.Seckill;

@RestController
public class HelloFacadeImpl implements HelloFacade {
	@DataAccess
	private EntityService<Long, Seckill> seckillService;

	@Override
	public Result<String> hello(@PathVariable String param) {
		return Result.SUCCESS("hello: " + param);
	}

}
