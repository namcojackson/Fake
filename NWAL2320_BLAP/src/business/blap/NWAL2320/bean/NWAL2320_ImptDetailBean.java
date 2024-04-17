package business.blap.NWAL2320.bean;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;

import parts.common.EZDSMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_PRC_CALC_BASETMsg;
import business.db.MDSETMsg;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320_ImptDetailBean implements INWAL2320_ImptBean {
    /** NWAL2320_ImptConfigBean */
    final public NWAL2320_ImptConfigBean configBean;
    /** DS_IMPT_ORD_DTLTMsg */
    final public DS_IMPT_ORD_DTLTMsg me;
    /** EZDSMsg */
    final public EZDSMsg rowData;
    /** List<DS_IMPT_PRC_CALC_BASETMsg> */
    final public List<DS_IMPT_PRC_CALC_BASETMsg> prcCalcBaseTMsgList;
    /** List<NWXC220001ErrorInfo> */
    final public List<NWXC220001ErrorInfo> rowErrList;
    /** MDSETMsg */
    public MDSETMsg mdseTMsg;

    /**
     * NWAL2320_ImptDetailBean
     * @param configBean NWAL2320_ImptConfigBean
     * @param rowData EZDSMsg
     */
    public NWAL2320_ImptDetailBean(NWAL2320_ImptConfigBean configBean, EZDSMsg rowData) {
        this.configBean = configBean;
        this.me = new DS_IMPT_ORD_DTLTMsg();
        this.configBean.detailBeanList.add(this);
        this.configBean.headerBean.detailBeanList.add(this);
        this.prcCalcBaseTMsgList = new ArrayList<DS_IMPT_PRC_CALC_BASETMsg>();
        this.rowData = rowData;
        this.rowErrList = new ArrayList<NWXC220001ErrorInfo>();
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[Error Info]\n");
        for (NWXC220001ErrorInfo errorInfo : this.rowErrList) {
            sb.append(String.format("\t%s\n", errorInfo.toString()));
        }
        sb.append("[Data Info]\n");
        sb.append(this.me.toString());
        sb.append(this.me.toString());

        return sb.toString();
    }

    /**
     * addErrorInfo
     * @param errorInfo NWXC220001ErrorInfo
     */
    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        this.rowErrList.add(errorInfo);
    }

    /**
     * addErrorInfo
     * @param errorInfoList NWXC220001ErrorInfo
     */
    @Override
    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        this.rowErrList.addAll(errorInfoList);
    }

    /**
     * hasError
     * @return boolean
     */
    @Override
    public boolean hasError() {
        return this.rowErrList.size() > 0;
    }


}
