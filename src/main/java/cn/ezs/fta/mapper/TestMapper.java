package cn.ezs.fta.mapper;

import cn.ezs.fta.pojo.Application;
import cn.ezs.fta.service.ApplicationService;

import java.util.List;

public interface TestMapper {
    List<Application> queryApplication(Application application);

}
