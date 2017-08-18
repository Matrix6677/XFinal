import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ziav.xfinal.module.seckill.manager.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {
  @Autowired
  private StringRedisTemplate redisTpl;

  @Test
  public void test() throws Exception {
    BoundHashOperations<String, Long, Seckill> seckillHashOpt = redisTpl.boundHashOps("seckill");

    seckillHashOpt.put(1L, new Seckill());
  }
}
