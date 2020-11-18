package cn.ezs.fta.service.impl;

import cn.ezs.fta.mapper.ApplicationMapper;
import cn.ezs.fta.pojo.Application;
import cn.ezs.fta.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>fta</h3>
 * <p></p>
 *
 * @author : 1998Gang
 * @date : 2020-08-12 11:21
 **/
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired(required = false)
    private ApplicationMapper applicationMapper;


    /**
     * 获取所有的申请
     * @return List
     */
    @Override
    public List<Application> getAllApplication() {
        return applicationMapper.queryAllApplication();
    }

    /**
     * 添加申请
     *
     * @param application 申请
     */
    @Override
    public void addNewApplication(Application application) {
        applicationMapper.insertNewApplication(application);
    }

    /**
     * 获取某个用户的所有申请
     *
     * @param username 用户名
     * @return List
     */
    @Override
    public List<Application> getApplicationByUserName(String username) {
        return applicationMapper.queryApplicationByUserName(username);
    }

    /**
     * 获取所有通过了的申请
     *
     * @return List
     */
    @Override
    public List<Application> getAllPassApplication() {
        return applicationMapper.queryPassApplication();
    }

    /**
     * 获取所有没有通过的申请
     *
     * @return List
     */
    @Override
    public List<Application> getNoPassApplication() {
        return applicationMapper.queryNoPassApplication();
    }

    /**
     * 改变审核状态 通过还是未通过
     *
     * @param aid      申请id
     * @param passChar 审核状态，p：代表通过，n：代表不通过。
     */
    @Override
    public void audit(String aid, char passChar) {
        applicationMapper.changeApplicationStatus(aid,passChar);
    }
}
