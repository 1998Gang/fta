package cn.ezs.fta.pojo;

/**
 * <h3>fta</h3>
 * <p>企业pojo</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-12 21:47
 **/
public class Enterprise {
    /**
     * 企业id
     */
    private int eid;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业描述
     */
    private String enterpriseDescription;

    @Override
    public String toString() {
        return "Enterprise{" +
                "eid=" + eid +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseDescription='" + enterpriseDescription + '\'' +
                '}';
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseDescription() {
        return enterpriseDescription;
    }

    public void setEnterpriseDescription(String enterpriseDescription) {
        this.enterpriseDescription = enterpriseDescription;
    }
}
