package cn.ezs.fta.mapper;

import cn.ezs.fta.pojo.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 申请表相关
 * @author 1998Gang
 */
public interface ApplicationMapper {
    /**
     * 添加申请
     * @param application 申请
     */
    void insertNewApplication(Application application);

    /**
     * 根据username查询申请
     * @param username 用户名
     * @return List
     */
    List<Application> queryApplicationByUserName(String username);

    /**
     * 查询所有申请
     * @return list
     */
    List<Application> queryAllApplication();

    /**
     * 查询所有通过了的申请
     * @return List
     */
    List<Application> queryPassApplication();

    /**
     * 查询所有未通过的申请
     * @return List
     */
    List<Application> queryNoPassApplication();

    /**
     * 更改审核状态
     * @param aid 申请id
     * @param passChar 申请状态，p：代表通过，n：代表不通过。
     */
    void changeApplicationStatus(@Param("aid") String aid, @Param("passChar") char passChar);



}
