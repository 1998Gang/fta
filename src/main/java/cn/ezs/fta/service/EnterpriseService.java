package cn.ezs.fta.service;

import cn.ezs.fta.pojo.Enterprise;

import java.util.List;

/**
 * @author 1998Gang
 */
public interface EnterpriseService {
    /**
     * 获取所有公司名单
     * @return List
     */
    List<Enterprise> getAllEnterprise();

    /**
     * 根据公司id获取该公司的描述
     * @param eid 公司id
     * @return Enterprise
     */
    Enterprise getEnterpriseByEid(int eid);

    /**
     * 添加新的企业
     * @param enterprise Enterprise
     */
    void addNewEnterprise(Enterprise enterprise);

    /**
     * 删除企业，通过一个企业id
     * @param eid eid
     */
    void deleteEnterpriseByEid(int eid);

    /**
     * 根据关键词查询公司
     * @param keyWord 关键词
     * @return List
     */
    List<Enterprise> getEnterpriseByKeyWord(String keyWord);

    /**
     * 更新企业信息
     * @param enterprise enterprise
     */
    void updateEnterperise(Enterprise enterprise);
}
