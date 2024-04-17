package com.canon.cusa.s21.batch.NWA.NWAB221001;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import parts.common.EZDMsg;
import parts.common.EZDTStringItem;

import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_IMPT_ORD_RTRN_DTLTMsg;
import business.db.MDSETMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

/** <pre>
 * DsImptRtnLineBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class DsImptRtnLineBean extends DS_IMPT_ORD_RTRN_DTLTMsg implements IImportBean {

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
    public MDSETMsg mdseInfo = null;
    public ExpendMdseBean mdseBean;
    public DS_CPO_RTRN_DTLTMsg origDsCpoRtrnDtlTMsg = null;
    public boolean isAutoAddRMA;

    public NWZC157002PMsg prcResultNWZC157002PMsg = null;

    public NWZC157004PMsg prcResultNWZC157004PMsg = null;

    public DsImptRtnLineBean(ImptHdrBean hedBean, DS_IMPT_ORD_RTRN_DTLTMsg baseTMsg) {
        this(hedBean, baseTMsg, false, -1, null);
    }

    public DsImptRtnLineBean(ImptHdrBean hedBean, DS_IMPT_ORD_RTRN_DTLTMsg baseTMsg, DS_CPO_RTRN_DTLTMsg origDsCpoRtrnDtlTMsg) {
        this(hedBean, baseTMsg, false, -1, origDsCpoRtrnDtlTMsg);
    }

    public DsImptRtnLineBean(ImptHdrBean hdrBean, DS_IMPT_ORD_RTRN_DTLTMsg baseTMsg, boolean child, int eleNo, DS_CPO_RTRN_DTLTMsg origDsCpoRtrnDtlTMsg) {
        super(child, eleNo);

        EZDMsg.copy(baseTMsg, null, this, null);

        // Initialization of item
        this.imptHdrBean = hdrBean;
        this.dsImptOrdErrList = new ArrayList<DsImptOrdErrBean>();
        this.origDsCpoRtrnDtlTMsg = origDsCpoRtrnDtlTMsg;
        this.isAutoAddRMA = false;
//        mdseAllList = new ArrayList<ExpendMdseBean>();

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

        return false;
    }

    public boolean isUpdateData() {
        return this.origDsCpoRtrnDtlTMsg != null;
    }

    // QC#10087
    public boolean isExistValidatedSts() {
        return this.isUpdateData() && imptHdrBean.isExistValidatedSts();
    }

    public boolean isSetMdseTp() {
        return (this.mdseInfo != null && MDSE_TP.SET.equals(this.mdseInfo.mdseTpCd.getValue()));
    }

    public boolean hasShipToCust() {

        List<EZDTStringItem> shipToCustItemList = Arrays.asList(
                super.shipToLocNm
                , super.shipToAddlLocNm
                , super.shipToFirstLineAddr
                , super.shipToScdLineAddr
                , super.shipToThirdLineAddr
                , super.shipToFrthLineAddr
                , super.shipToFirstRefCmntTxt
                , super.shipToScdRefCmntTxt
                , super.shipToCtyAddr
                , super.shipToStCd
                , super.shipToProvNm
                , super.shipToCntyNm
                , super.shipToPostCd
                , super.shipToCtryCd
                );

        for (EZDTStringItem item : shipToCustItemList) {
            if (ZYPCommonFunc.hasValue(item)) {
                return true;
            }
        }

        return false;
    }

    //  S21_NA#11837
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
