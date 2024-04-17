package business.blap.NWAL2320.bean;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDSMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */

/**
 * NWAL2320_ImptConfigBean
 */
public class NWAL2320_ImptConfigBean implements INWAL2320_ImptBean {
    /** NWAL2320_ImptHeaderBean */
    final public NWAL2320_ImptHeaderBean headerBean;
    /** DS_IMPT_ORD_CONFIGTMsg */
    final public DS_IMPT_ORD_CONFIGTMsg me;
    /** DS_IMPT_ORD_DELY_INFOTMsg */
    final public DS_IMPT_ORD_DELY_INFOTMsg delyInfoTMsg;
    /** DS_IMPT_ORD_SLS_CRTMsg */
    final public DS_IMPT_ORD_SLS_CRTMsg slsCrTMsg;
    /** NWAL2320_ImptDetailBean */
    final public List<NWAL2320_ImptDetailBean> detailBeanList;
    /** NWAL2320_ImptRtrnDetailBean */
    final public List<NWAL2320_ImptRtrnDetailBean> rtrnDtlBeanList;

    /**
     * NWAL2320_ImptConfigBean
     * @param headerBean NWAL2320_ImptHeaderBean
     */
    public NWAL2320_ImptConfigBean(NWAL2320_ImptHeaderBean headerBean) {
        this.headerBean = headerBean;
        this.me = new DS_IMPT_ORD_CONFIGTMsg();
        this.delyInfoTMsg = new DS_IMPT_ORD_DELY_INFOTMsg();
        this.slsCrTMsg = new DS_IMPT_ORD_SLS_CRTMsg();

        this.detailBeanList = new ArrayList<NWAL2320_ImptDetailBean>();
        this.rtrnDtlBeanList = new ArrayList<NWAL2320_ImptRtrnDetailBean>();
    }

    public boolean isOutbound() {
        return CONFIG_CATG.OUTBOUND.equals(this.me.configCatgCd.getValue());
    }

    /**
     * getDetailSMsg
     * @param <T> T
     * @param no int
     * @return T
     */
    public <T extends EZDSMsg> T getDetailSMsg(int no) {
        if (headerBean.isUpldTpSlsOrd()) {
            if (detailBeanList.size() > no) {
                return (T) detailBeanList.get(no).rowData;
            }
        } else {
            return (T) rtrnDtlBeanList.get(no).rowData;
        }

        return null;
    }

    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        if (this.headerBean.isUpldTpSlsOrd()) {
            for (NWAL2320_ImptDetailBean dtlBean : this.detailBeanList) {
                dtlBean.rowErrList.add(errorInfo);
            }
        } else {
            for (NWAL2320_ImptRtrnDetailBean rtrnDtlBean : this.rtrnDtlBeanList) {
                rtrnDtlBean.rowErrList.add(errorInfo);
            }
        }
    }

    @Override
    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        for (NWXC220001ErrorInfo errorInfo : errorInfoList) {
            this.addErrorInfo(errorInfo);
        }
    }

    @Override
    public boolean hasError() {
        if (this.headerBean.isUpldTpSlsOrd()) {
            for (NWAL2320_ImptDetailBean dtlBean : this.detailBeanList) {
                if (dtlBean.rowErrList.size() > 0) {
                    return true;
                }
            }
        } else {
            for (NWAL2320_ImptRtrnDetailBean rtrnDtlBean : this.rtrnDtlBeanList) {
                if (rtrnDtlBean.rowErrList.size() > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Detail Bean Num : %d\n", this.detailBeanList.size()));
        sb.append(String.format("RMA Detail Bean Num : %d\n", this.rtrnDtlBeanList.size()));

        sb.append(this.me.toString());

        return sb.toString();
    }

}
