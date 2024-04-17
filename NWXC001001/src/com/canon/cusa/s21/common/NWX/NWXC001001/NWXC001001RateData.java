package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * It is a class that maintains rate data.
 * It uses it by processing concerning the conversion of the
 * {@link com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange NWXC001001Exchange} class.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/25/2009   Fujitsu         S.Sugino        Create          N/A
 * </pre>
 */
public class NWXC001001RateData {

    private String acctArthTpCd = null;

    private BigDecimal actlExchRate = null;

    private List<String> xxMsgIdList = null;

    public String getAcctArthTpCd() {
        return acctArthTpCd;
    }

    public void setAcctArthTpCd(String acctArthTpCd) {
        this.acctArthTpCd = acctArthTpCd;
    }

    public BigDecimal getActlExchRate() {
        return actlExchRate;
    }

    public void setActlExchRate(BigDecimal actlExchRate) {
        this.actlExchRate = actlExchRate;
    }

    public List<String> getXxMsgIdList() {
        if (xxMsgIdList == null) {
            return Collections.emptyList();
        }
        return xxMsgIdList;
    }

    public void setXxMsgIdList(List<String> xxMsgIdList) {
        this.xxMsgIdList = xxMsgIdList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("acctArthTpCd=").append(getAcctArthTpCd()).append(',');
        sb.append("actlExchRate=").append(getActlExchRate()).append(',');
        sb.append("xxMsgIdList=").append(getXxMsgIdList());
        return sb.toString();
    }

}
