package cn.ezs.fta.service.impl;

import cn.ezs.fta.service.TestAopService;
import org.springframework.stereotype.Service;

/**
 * <h3>fat</h3>
 * <p></p>
 *
 * @author : 1998Gang
 * @date : 2020-08-04 18:16
 **/
@Service
public class TestAopServiceImpl implements TestAopService {
    @Override
    public void testAop() {
        System.out.println("hello aop");
    }

    @Override
    public void testTest(){
        System.out.println("这是自己的方法！");
    }
}
