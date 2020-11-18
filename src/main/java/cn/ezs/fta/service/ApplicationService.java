package cn.ezs.fta.service;

import cn.ezs.fta.mapper.ApplicationMapper;
import cn.ezs.fta.pojo.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <h3>fta</h3>
 * <p>申请相关</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-12 11:13
 **/

public interface ApplicationService {


    /**
     * 获取所有的申请
     * @return List
     */
     List<Application> getAllApplication();

    /**
     * 添加申请
     * @param application 申请
     */
    void addNewApplication(Application application);

    /**
     * 获取某个用户的所有申请
     * @param username 用户名
     * @return List
     */
    List<Application> getApplicationByUserName(String username);

    /**
     * 获取所有通过了的申请
     * @return List
     */
    List<Application> getAllPassApplication();

    /**
     * 获取所有没有通过的申请
     * @return List
     */
    List<Application> getNoPassApplication();

    /**
     * 改变审核状态 通过还是未通过
     * @param aid 申请id
     * @param passChar 审核状态，p：代表通过，n：代表不通过。
     */
    void audit(String aid, char passChar);


}
