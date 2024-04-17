/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21StringUtil;

import parts.common.EZDMsg;
import business.db.DS_IMPT_SVC_ADDL_BASETMsg;
import business.db.DS_IMPT_SVC_ADDL_CHRGTMsg;
import business.db.DS_IMPT_SVC_CONFIG_REFTMsg;
import business.db.DS_IMPT_SVC_DTLTMsg;
import business.db.DS_IMPT_SVC_PRCTMsg;
import business.db.DS_IMPT_SVC_USG_PRCTMsg;

/** <pre>
 * DsImptSvcDtlBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * 07/05/2016   Fujitsu         M.Hara          Create          QC#19068
 * </pre>
 */
public class DsImptSvcDtlBean extends DS_IMPT_SVC_DTLTMsg implements IImportBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // *************************************************************************
    // Extention Field
    // *************************************************************************
    public final ImptHdrBean imptHdrBean;
    public final List<DsImptOrdErrBean> dsImptOrdErrList;

    // Service Shell Data
    public final List<DS_IMPT_SVC_CONFIG_REFTMsg> svcConfigRefMsgList;
    public final List<DS_IMPT_SVC_PRCTMsg> svcPrcMsgList;
    public final List<DS_IMPT_SVC_USG_PRCTMsg> svcUsgPrcMsgList;
    public final List<DS_IMPT_SVC_ADDL_BASETMsg> svcAddlBaseMsgList;
    public final List<DS_IMPT_SVC_ADDL_CHRGTMsg> svcAddlChrgMsgList;

    public DsImptSvcDtlBean(ImptHdrBean hdrBean, DS_IMPT_SVC_DTLTMsg baseTMsg) {
        this(hdrBean, baseTMsg, false, -1);
    }

    public DsImptSvcDtlBean(ImptHdrBean hdrBean, DS_IMPT_SVC_DTLTMsg baseTMsg, boolean child, int eleNo) {
        super(child, eleNo);

        EZDMsg.copy(baseTMsg, null, this, null);

        // Initialization of item
        imptHdrBean = hdrBean;
        dsImptOrdErrList = new ArrayList<DsImptOrdErrBean>();
        svcConfigRefMsgList = new ArrayList<DS_IMPT_SVC_CONFIG_REFTMsg>();
        svcPrcMsgList = new ArrayList<DS_IMPT_SVC_PRCTMsg>();
        svcUsgPrcMsgList = new ArrayList<DS_IMPT_SVC_USG_PRCTMsg>();
        svcAddlBaseMsgList = new ArrayList<DS_IMPT_SVC_ADDL_BASETMsg>();
        svcAddlChrgMsgList = new ArrayList<DS_IMPT_SVC_ADDL_CHRGTMsg>();
    }

    public BigDecimal getImptOrdDtlPk() {
        for (DS_IMPT_SVC_CONFIG_REFTMsg svcConfigRefMsg : this.svcConfigRefMsgList) {
            if (this.dsImptSvcDtlPk.getValue().equals(svcConfigRefMsg.dsImptSvcDtlPk.getValue())) {
                return svcConfigRefMsg.dsImptOrdDtlPk.getValue();
            }
        }

        return null;
    }

    public BigDecimal getImptOrdDtlPkFromSvcConfigRefPk(BigDecimal dsImptSvcConfigRefPk) {

        if (dsImptSvcConfigRefPk == null) {

            return null;
        }
        for (DS_IMPT_SVC_CONFIG_REFTMsg svcConfigRefMsg : this.svcConfigRefMsgList) {

            if (dsImptSvcConfigRefPk.equals(svcConfigRefMsg.dsImptSvcConfigRefPk.getValue())) {

                return svcConfigRefMsg.dsImptOrdDtlPk.getValue();
            }
        }
        return null;
    }

    public DS_IMPT_SVC_PRCTMsg getDsImptSvcPrcMsgFromSvcPrcPk(BigDecimal dsImptSvcPrcPk) {
        for (DS_IMPT_SVC_PRCTMsg svcPrcMsg : this.svcPrcMsgList) {
            if (svcPrcMsg.dsImptSvcPrcPk.getValue().equals(dsImptSvcPrcPk)) {
                return svcPrcMsg;
            }
        }

        return null;
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
        return null;
    }

    @Override
    public boolean isSameErrorExists(DsImptOrdErrBean dsImptOrdErrBean) {

        // Add Start 2017/07/05 QC#19068
        if (ZYPCommonFunc.hasValue(dsImptOrdErrBean.dsImptSvcDtlPk)) {

            for (DsImptOrdErrBean dsImptOrdErr : this.dsImptOrdErrList) {

                if (dsImptOrdErrBean.dsImptSvcDtlPk.getValue().compareTo(dsImptOrdErr.dsImptSvcDtlPk.getValue()) != 0) {

                    continue;
                }

                if (S21StringUtil.isEquals(dsImptOrdErrBean.dsImptOrdErrTxt.getValue(), dsImptOrdErr.dsImptOrdErrTxt.getValue())) {

                    return true;
                }
            }
        }
        // Add End 2017/07/05 QC#19068
        return false;
    }

}
