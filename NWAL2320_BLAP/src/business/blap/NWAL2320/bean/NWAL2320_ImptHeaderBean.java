package business.blap.NWAL2320.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import business.db.CPOTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001Util;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_UPLD_TMPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 * 2016/11/29   Fujitsu         M.Yamada        Update          QC#16266
 *</pre>
 */
public class NWAL2320_ImptHeaderBean implements INWAL2320_ImptBean {
    /** ordUpldTmplTpCd */
    final public String ordUpldTmplTpCd;
    /** doInsertTable */
    final public boolean doInsertTable;
    /** DS_IMPT_ORDTMsg */
    final public DS_IMPT_ORDTMsg me;
    /** LinkedHashMap<String, NWAL2320_ImptConfigBean> */
    final public LinkedHashMap<String, NWAL2320_ImptConfigBean> configBeanMap;
    /** List<NWAL2320_ImptDetailBean> */
    final public List<NWAL2320_ImptDetailBean> detailBeanList;
    /** List<NWAL2320_ImptRtrnDetailBean> */
    final public List<NWAL2320_ImptRtrnDetailBean> rtrnDtlBeanList;
    /** DS_ORD_TP_PROC_DFNTMsg */
    public DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg;
    /** slsDt */
    public String slsDt;
    /** coaExtnCd */
    public String coaExtnCd;
    /** lastOrdSrcRefLineNum Inbound */
    public int lastOrdSrcRefLineNumIn;
    /** lastOrdSrcRefLineNum Outbound */
    public int lastOrdSrcRefLineNumOut;
    /** CPOTMsg */
    public CPOTMsg cpoTMsg;
    /** cpoOrdNum */
    public String cpoOrdNum;

    /**
     * NWAL2320_ImptHeaderBean
     * @param ordUpldTmplTpCd String
     * @param doInsertTable boolean
     */
    public NWAL2320_ImptHeaderBean(String ordUpldTmplTpCd, boolean doInsertTable) {
        this.ordUpldTmplTpCd = ordUpldTmplTpCd;
        this.doInsertTable = doInsertTable;
        this.me = new DS_IMPT_ORDTMsg();
        this.configBeanMap = new LinkedHashMap<String, NWAL2320_ImptConfigBean>();
        this.detailBeanList = new ArrayList<NWAL2320_ImptDetailBean>();
        this.rtrnDtlBeanList = new ArrayList<NWAL2320_ImptRtrnDetailBean>();
        this.lastOrdSrcRefLineNumOut = 0;
        this.lastOrdSrcRefLineNumIn = 0;
    }

    /**
     * isUpldTpNew
     * @return isUpldTpNew
     */
    public boolean isUpldTpNew() {
        return NWXC220001Util.isTargetContents(this.ordUpldTmplTpCd, ORD_UPLD_TMPL_TP.NEW_SALES_ORDER, ORD_UPLD_TMPL_TP.NEW_RMA_ORDER);
    }

    /**
     * isUpldTpSlsOrd
     * @return boolean
     */
    public boolean isUpldTpSlsOrd() {
        return NWXC220001Util.isTargetContents(this.ordUpldTmplTpCd, ORD_UPLD_TMPL_TP.NEW_SALES_ORDER, ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER);
    }

    /**
     * setFixedValue
     */
    public void setFixedValue() {
        ZYPEZDItemValueSetter.setValue(me.cpoSrcTpCd, CPO_SRC_TP.ORDER_UPLOAD);
        ZYPEZDItemValueSetter.setValue(me.imptStsCd, IMPT_STS.NOT_PROCESSED);
        ZYPEZDItemValueSetter.setValue(me.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(me.dropShipFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(me.sendInvFlg, ZYPConstant.FLG_ON_Y);
        if (isUpldTpNew()) {
            ZYPEZDItemValueSetter.setValue(me.ordHdrEdtblFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(me.ordHdrEdtblFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(me.maintOnlyFlg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * addErrorInfo
     * @param errorInfo NWXC220001ErrorInfo
     */
    @Override
    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        if (isUpldTpSlsOrd()) {
            for (NWAL2320_ImptDetailBean dtlBean : this.detailBeanList) {
                dtlBean.rowErrList.add(errorInfo);
            }
        } else {
            for (NWAL2320_ImptRtrnDetailBean rtrnDtlBean : this.rtrnDtlBeanList) {
                rtrnDtlBean.rowErrList.add(errorInfo);
            }
        }
    }

    /**
     * addErrorInfo
     * @param errorInfoList List<NWXC220001ErrorInfo>
     */
    @Override
    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        for (NWXC220001ErrorInfo errorInfo : errorInfoList) {
            this.addErrorInfo(errorInfo);
        }
    }

    /**
     * hasError
     * @return boolean
     */
    @Override
    public boolean hasError() {
        if (isUpldTpSlsOrd()) {
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

        sb.append(String.format("OrdUpldTmplTpCd : %s\n", this.ordUpldTmplTpCd));
        sb.append(String.format("Config Bean Num : %d\n", this.configBeanMap.size()));
        sb.append(String.format("Detail Bean Num : %d\n", this.detailBeanList.size()));
        sb.append(String.format("RMA Detail Bean Num : %d\n", this.rtrnDtlBeanList.size()));
        sb.append(String.format("CpoOrdNum : %s\n", this.cpoOrdNum));
        sb.append(String.format("HasError : %b\n", this.hasError()));

        sb.append(this.me.toString());

        return sb.toString();
    }

}
