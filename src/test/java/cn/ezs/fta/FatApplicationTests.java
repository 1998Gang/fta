package cn.ezs.fta;

import cn.ezs.fta.mapper.TestMapper;
import cn.ezs.fta.pojo.Application;
import cn.ezs.fta.service.impl.TestAopServiceImpl;
import cn.ezs.fta.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FatApplicationTests {

    @Autowired
    TestAopServiceImpl testAopService;

    @Autowired
    TestMapper testMapper;

    @Test
    void contextLoads() {
        String s = JwtUtil.creatToken("pyg", "admin", "管理员");
        System.out.println("生成的token-->>"+s);

        boolean verify = JwtUtil.verify(s);
    }

    @Test
    void MapperTest(){
        Application application = new Application();
//        application.setAid(13);
        application.setApplicationReason("333");
        List<Application> applications = testMapper.queryApplication(application);
        System.out.println(applications);
    }
}
