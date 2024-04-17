package com.canon.cusa.s21.batch.NWA.NWAB221001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.MDSETMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/** <pre>
 * DsImptLineBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class DsImptLineBean extends DS_IMPT_ORD_DTLTMsg implements IImportBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // *************************************************************************
    // Extention Field
    // *************************************************************************
    public final ImptHdrBean imptHdrBean;
    public DsImptOrdConfigBean dsImptOrdConfigBean;
    public final List<DsImptOrdErrBean> dsImptOrdErrList;

    public String rtlWhCfsDispInbnd;
    public MDSETMsg mdseInfo = null;
    public final List<ExpendMdseBean> mdseAllList;
    public boolean isAutoAddSupply;
    public boolean hasEdiCustUomError;

    public CPO_DTLTMsg origCpoDtlTMsg = null;

    public NWZC157002PMsg prcResultNWZC157002PMsg = null;

    public NWZC157004PMsg prcResultNWZC157004PMsg = null;


    public DS_IMPT_DTL_EXT_ATTRBTMsg dsImptDtlExtAttrbTMsg = null;

    public DsImptLineBean(ImptHdrBean hdrBean, DS_IMPT_ORD_DTLTMsg baseTMsg) {
        this(hdrBean, baseTMsg, false, -1, null);
    }

    public DsImptLineBean(ImptHdrBean hdrBean, DS_IMPT_ORD_DTLTMsg baseTMsg, CPO_DTLTMsg origCpoDtl) {
        this(hdrBean, baseTMsg, false, -1, origCpoDtl);
    }

    public DsImptLineBean(ImptHdrBean hdrBean, DS_IMPT_ORD_DTLTMsg baseTMsg, boolean child, int eleNo, CPO_DTLTMsg origCpoDtl) {
        super(child, eleNo);

        EZDMsg.copy(baseTMsg, null, this, null);

        // Initialization of item
        imptHdrBean = hdrBean;
        dsImptOrdErrList = new ArrayList<DsImptOrdErrBean>();
        mdseAllList = new ArrayList<ExpendMdseBean>();
        this.isAutoAddSupply = false;
        this.hasEdiCustUomError = false;
        this.origCpoDtlTMsg = origCpoDtl;

        // overwrite item
        ZYPEZDItemValueSetter.setValue(this.invtyLocCd, this.rtlWhCd.getValue() + this.rtlSwhCd.getValue());
    }

    @Override
    public List<DsImptOrdErrBean> getDsImptOrdErrList() {
        return dsImptOrdErrList;
    }

    @Override
    public boolean hasError(boolean doSearchChild) {
        return (this.dsImptOrdErrList.size() > 0);
    }

    @Override
    public DsImptOrdConfigBean getDsImptOrdConfigBean() {
        return this.dsImptOrdConfigBean;
    }

    @Override
    public boolean isSameErrorExists(DsImptOrdErrBean dsImptOrdErrBean) {
        // TODO 自動生成されたメソッドスタブ
        return false;
    }

    public boolean isUpdateData() {
        return this.origCpoDtlTMsg != null;
    }

    // QC#10087
    public boolean isExistValidatedSts() {
        return this.isUpdateData() && imptHdrBean.isExistValidatedSts();
    }

    public boolean isSetMdseTp() {
        return (this.mdseInfo != null && MDSE_TP.SET.equals(this.mdseInfo.mdseTpCd.getValue()));
    }

    public void calcMdseQty() {

        BigDecimal ordQty =  this.ordQty.getValue();
        BigDecimal parentQty;

        for (ExpendMdseBean bean : this.mdseAllList) {
            if (bean.getParentBean() == null) {
                parentQty = BigDecimal.ONE;
            } else {
                parentQty = bean.getParentBean().getChildMdseQty();
            }

            bean.setCalcMdseQty(ordQty.multiply(parentQty.multiply(bean.getChildMdseQty())));
        }
    }

    public ExpendMdseBean getMdseBean(String cpoDtlLineNum, String cpoDtlSubLineNum) {
        for (ExpendMdseBean mdseBean : this.mdseAllList) {
            if (cpoDtlLineNum.equals(mdseBean.getCpoDtlLineNum())
                    && cpoDtlSubLineNum.equals(mdseBean.getCpoDtlLineSubNum())) {
                return mdseBean;
            }
        }

        return null;
    }

    public DS_IMPT_DTL_EXT_ATTRBTMsg setDsImptDtlExtAttrbTMsg() {
        for (DS_IMPT_DTL_EXT_ATTRBTMsg tMsg : this.imptHdrBean.getDsImptDtlExtAttrbTMsgList()) {
            if (tMsg.dsImptOrdDtlPk.getValue().equals(this.dsImptOrdDtlPk.getValue())) {
                this.dsImptDtlExtAttrbTMsg = tMsg;
                return tMsg;
            }
        }

        return null;
    }

    public NWZC157003PMsg getBasePrice() {

        if (this.prcResultNWZC157002PMsg != null) {
            for (int i = 0; i < this.prcResultNWZC157002PMsg.NWZC157003PMsg.getValidCount(); i++) {
                if (S21StringUtil.isEquals(PRC_COND_TP.BASE_PRICE, this.prcResultNWZC157002PMsg.NWZC157003PMsg.no(i).prcCondTpCd.getValue())) {
                    return this.prcResultNWZC157002PMsg.NWZC157003PMsg.no(i);
                }
            }
        }

        return null;
    }
}
