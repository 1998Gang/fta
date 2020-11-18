package cn.ezs.fta.service.impl;

import cn.ezs.fta.mapper.EnterpriseMapper;
import cn.ezs.fta.pojo.Enterprise;
import cn.ezs.fta.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>fta</h3>
 * <p></p>
 *
 * @author : 1998Gang
 * @date : 2020-08-13 09:20
 **/
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired(required = false)
    private EnterpriseMapper enterpriseMapper;

    /**
     * 获取所有公司名单
     *
     * @return List
     */
    @Override
    public List<Enterprise> getAllEnterprise() {
        return enterpriseMapper.queryAllEnterprise();
    }

    /**
     * 根据公司id获取该公司的描述
     *
     * @param eid 公司id
     * @return Enterprise
     */
    @Override
    public Enterprise getEnterpriseByEid(int eid) {
        return enterpriseMapper.queryEnterpriseByEid(eid);
    }

    /**
     * 添加新的企业
     *
     * @param enterprise Enterprise
     */
    @Override
    public void addNewEnterprise(Enterprise enterprise) {
        enterpriseMapper.insertEnterprise(enterprise);
    }

    /**
     * 删除企业，通过一个企业id
     *
     * @param eid eid
     */
    @Override
    public void deleteEnterpriseByEid(int eid) {
        enterpriseMapper.deleteEnterpriseByEid(eid);
    }

    /**
     * 根据关键词查询公司
     *
     * @param keyWord 关键词
     * @return List
     */
    @Override
    public List<Enterprise> getEnterpriseByKeyWord(String keyWord) {
        return enterpriseMapper.queryEnterpriseByKeyWord(keyWord);
    }

    /**
     * 更新企业信息
     *
     * @param enterprise enterprise
     */
    @Override
    public void updateEnterperise(Enterprise enterprise) {
        enterpriseMapper.updateEnterprise(enterprise);
    }
}
