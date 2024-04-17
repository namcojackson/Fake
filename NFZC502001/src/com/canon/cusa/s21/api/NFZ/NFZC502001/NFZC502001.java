/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NFZ.NFZC502001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;


import parts.dbcommon.EZDTBLAccessor;

import business.db.CM_MAINT_INVTMsg;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Invoice Workflow Status Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CUSA            Y.Aikawa        Create          N/A
 * </pre>
 */
public class NFZC502001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>,
                                      S21NwfRejectEvent<S21NwfContext, S21NwfToken>,
                                      S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    /** @ is not found. */
    public static final String NFAM0154E = "NFAM0154E";

    /** @  is invalid. */
    public static final String NMZM0143E = "NMZM0143E";

    /** The parameter is blank. [@] */
    public static final String ZZXM0021E = "ZZXM0021E";

    /** AP_WF_STS_CD 00 */
    public static final String AP_WF_STS_CD_00 = "00";

    /** AP_WF_STS_CD 10 (Approved) */
    public static final String AP_WF_STS_CD_10 = "10";

    /** AP_WF_STS_CD 11 (Rejected) */
    public static final String AP_WF_STS_CD_11 = "11";

    /**
     * Approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        commonProc(name, context, token, AP_WF_STS_CD_10);
        return;
    }

    /**
     * Reject
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void reject(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        commonProc(name, context, token, AP_WF_STS_CD_11);
        return;
    }

    /**
     * End
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void end(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        return;
    }
    
    /**
     * commonProc
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @param apDsWfStsCd
     * @throws S21NwfException S21NwfException
     */
    public void commonProc(String name, S21NwfContext context, S21NwfToken token, String apDsWfStsCd) throws S21NwfException {
        NFZC502001TokenObject tokenObj = (NFZC502001TokenObject) token.getTokenObj();
        List<NFZC502001TokenObjectLine> lines = tokenObj.getLines();
        for (NFZC502001TokenObjectLine line : lines) {
            if (S21StringUtil.isEmpty(line.getApVndCd())) {
                throw new S21NwfBizException(ZZXM0021E, new String[]{"AP Vendor Code"});
            }
            if (S21StringUtil.isEmpty(line.getApInvNum())) {
                throw new S21NwfBizException(ZZXM0021E, new String[]{"AP Invoice Number"});
            }

            CM_MAINT_INVTMsg cmMaintInvTMsg = findCmMaintInv(line);
            if (cmMaintInvTMsg == null) {
                throw new S21NwfBizException(NFAM0154E, new String[]{"CM_MAINT_INV record"});
            } else {
                if (!hasValue(cmMaintInvTMsg.apDsWfStsCd) ||
                    !cmMaintInvTMsg.apDsWfStsCd.getValue().equals(AP_WF_STS_CD_00)) {
                    throw new S21NwfBizException(NMZM0143E, new String[]{"AP_DS_WF_STS_CD in CM_MAINT_INV table"});
                }
                setValue(cmMaintInvTMsg.apDsWfStsCd, apDsWfStsCd);
                EZDTBLAccessor.update(cmMaintInvTMsg);
            }
        }
    }

    private CM_MAINT_INVTMsg findCmMaintInv(NFZC502001TokenObjectLine line) {
        CM_MAINT_INVTMsg cmMaintInv = new CM_MAINT_INVTMsg();
        setValue(cmMaintInv.glblCmpyCd, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(cmMaintInv.apInvNum, line.getApInvNum());
        setValue(cmMaintInv.apVndCd, line.getApVndCd());
        return (CM_MAINT_INVTMsg) EZDTBLAccessor.findByKey(cmMaintInv);
    }

}

