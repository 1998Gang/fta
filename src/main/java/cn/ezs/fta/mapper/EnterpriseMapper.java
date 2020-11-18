package cn.ezs.fta.mapper;

import cn.ezs.fta.pojo.Enterprise;

import java.util.List;

/**
 * 企业映射Mapper
 * @author 1998Gang
 */
public interface EnterpriseMapper {
    /**
     * 查询所有的企业
     * @return List
     */
    List<Enterprise> queryAllEnterprise();

    /**
     * 添加新的企业
     * @param enterprise Enterprise
     */
    void insertEnterprise(Enterprise enterprise);

    /**
     * 删除企业
     * @param eid 企业对应id
     */
    void deleteEnterpriseByEid(int eid);

    /**
     * 查询公司的描述信息
     * @param eid eid
     * @return Enterprise
     */
    Enterprise queryEnterpriseByEid(int eid);

    /**
     * 通过关键词检索公司
     * @param keyWord 关键词
     * @return List
     */
    List<Enterprise> queryEnterpriseByKeyWord(String keyWord);

    /**
     * 保存修改后的企业数据
     * @param enterprise enterprise
     */
    void updateEnterprise(Enterprise enterprise);
}
