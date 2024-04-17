/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1410;

import static business.blap.NSAL1410.constant.NSAL1410Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1410.common.NSAL1410CommonLogic;
import business.db.CONTR_ADMIN_PSN_UPD_INFOTMsg;
import business.db.DS_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 *<pre>
 * Contract Branch Rep Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 * 2022/07/25   Hitachi         H.Watanabe      Update          QC#60080
 *</pre>
 */
public class NSAL1410BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL1410CMsg cMsg = (NSAL1410CMsg) arg0;
        NSAL1410SMsg sMsg = (NSAL1410SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1410Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1410Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1410Scrn00_CMN_Submit(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg) {
        NSAL1410CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        String sysTs = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        if (sMsg.A.getValidCount() > cMsg.A.length()) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                NSAL1410_ASMsg asMsg = (NSAL1410_ASMsg) sMsg.A.no(i);
                insertContrAdminPsnUpdInfo(cMsg, asMsg, PROC_STS.IN_COMPLETED, sysTs);
                cMsg.setMessageInfo(NSAM0761I);
            }
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                NSAL1410_ASMsg asMsg = (NSAL1410_ASMsg) sMsg.A.no(i);
                if (updateContract(cMsg, asMsg)) {
                    insertContrAdminPsnUpdInfo(cMsg, asMsg, PROC_STS.COMPLEATED, sysTs);
                    cMsg.setMessageInfo(NZZM0002I);
                } else {
                    insertContrAdminPsnUpdInfo(cMsg, asMsg, PROC_STS.ERROR, sysTs);
                    cMsg.setMessageInfo(NSAM0394W);
                }
            }
        }

        NSAL1410CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private static boolean updateContract(NSAL1410CMsg cMsg, NSAL1410_ASMsg asMsg) {

        if (!hasValue(asMsg.dsContrPk_A)) {
            setValue(asMsg.vldMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0045E, new String[] {"Contr#" }));
            return false;
        }

        if (!hasValue(asMsg.psnCd_A2)) {
            setValue(asMsg.vldMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0045E, new String[] {"New Branch Rep Code" }));
            return false;
        }

        S21SsmEZDResult ssmResult = NSAL1410Query.getInstance().getPsnInfo(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), asMsg.psnCd_A2.getValue());
        if (!ssmResult.isCodeNormal()) {
            setValue(asMsg.vldMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0453E));
            return false;
        }

        DS_CONTRTMsg tMsg = NSAL1410Query.getInstance().getDsContr(cMsg.glblCmpyCd.getValue(), asMsg.dsContrPk_A.getValue());
        if (tMsg == null) {
            setValue(asMsg.vldMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0045E, new String[] {"Contr#" }));
            return false;
        }

        if (NSAL1410Query.getInstance().getAdminPsnAuthority(cMsg.glblCmpyCd.getValue(), asMsg.svcContrBrCd_A.getValue(), asMsg.psnCd_A2.getValue(), cMsg.slsDt.getValue()) == false) {
            setValue(asMsg.vldMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0762E));
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A.getValue(), asMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            setValue(asMsg.vldMsgTxt_A, S21MessageFunc.clspGetMessage(ZZZM9004E));
            return false;
        }

        setValue(tMsg.contrAdminPsnCd, asMsg.psnCd_A2);
        setValue(tMsg.dsContrLastUpdDt, cMsg.slsDt);
        setValue(tMsg.dsContrLastUpdPsnCd, cMsg.getUserID());
        S21FastTBLAccessor.update(tMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            setValue(asMsg.vldMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0031E, new String[] {"DS_CONTR" }));
            return false;
        }
        setValue(asMsg.vldMsgTxt_A, S21MessageFunc.clspGetMessage(NZZM0002I));
        return true;
    }

    private static void insertContrAdminPsnUpdInfo(NSAL1410CMsg cMsg, NSAL1410_ASMsg asMsg, String procStsCd, String sysTs) {
        CONTR_ADMIN_PSN_UPD_INFOTMsg tMsg = new CONTR_ADMIN_PSN_UPD_INFOTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.contrAdminPsnUpdInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_ADMIN_PSN_UPD_INFO_SQ));
        setValue(tMsg.dsContrPk, asMsg.dsContrPk_A);
        setValue(tMsg.oldContrAdminPsnCd, asMsg.psnCd_A1);
        setValue(tMsg.newContrAdminPsnCd, asMsg.psnCd_A2);
        setValue(tMsg.psnUpdInfoProcStsCd, procStsCd);
        setValue(tMsg.psnUpdInfoCratTs, sysTs);
        setValue(tMsg.psnUpdInfoCratPsnCd, cMsg.getUserID());
        setValue(tMsg.psnUpdInfoUpdTs, sysTs);
        setValue(tMsg.psnUpdInfoUpdPsnCd, cMsg.getUserID());
        setValue(tMsg.vldMsgTxt, asMsg.vldMsgTxt_A);
        S21FastTBLAccessor.insert(tMsg);
    }
}
