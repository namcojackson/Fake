/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0060;

import static business.blap.NSAL0060.constant.NSAL0060Constant.NSAM0031E;
import static business.blap.NSAL0060.constant.NSAL0060Constant.NSAM0032E;
import static business.blap.NSAL0060.constant.NSAL0060Constant.NSAM0033E;
import static business.blap.NSAL0060.constant.NSAL0060Constant.NSAM0079E;
import static business.blap.NSAL0060.constant.NSAL0060Constant.NZZM0003E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0060.common.NSAL0060CommonLogic;
import business.blap.NSAL0060.constant.NSAL0060Constant;
import business.db.DS_MDL_GRPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi      Create          N/A
 * 06/27/2016   Hitachi         M.Gotou         Update          CSA QC7886
 *</pre>
 */
public class NSAL0060BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0060Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0060Scrn00_CMN_Submit((NSAL0060CMsg) cMsg, (NSAL0060SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0060Scrn00_CMN_Submit(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        NSAL0060CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        // remove
        if (!remove(cMsg, sMsg)) {
            return;
        }
        // insert/update
        int ret = insertOrUpdate(cMsg, sMsg);
        if (ret > -1) {
            // same error
            int showPage = ((ret + 1) / cMsg.A.length());
            int showFromNum = showPage * cMsg.A.length();
            int errCnt = ret % cMsg.A.length();
            ZYPTableUtil.clear(cMsg.A);
            cMsg.xxPageShowFromNum.setValue(showFromNum);
            cMsg.xxPageShowToNum.clear();

            NSAL0060CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.A.no(errCnt).mdlGrpNm_SR.setErrorInfo(1, NSAM0079E, new String[]{"Model Group"});
        }
    }

    private boolean remove(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL0060_CSMsg csMsg = sMsg.C.no(i);
            if (NSAL0060CommonLogic.existRelatedInfo(csMsg.mdlGrpId_SR.getValue(), getGlobalCompanyCode())) {
                cMsg.setMessageInfo(NZZM0003E);
                return false;
            }
            DS_MDL_GRPTMsg tMsg = NSAL0060CommonLogic.locDsMdlGrp(csMsg.mdlGrpId_SR.getValue(), getGlobalCompanyCode());
            if (tMsg == null) {
                continue;
            }
            String preUpTime = csMsg.ezUpTime_SR.getValue();
            String preTimeZone = csMsg.ezUpTimeZone_SR.getValue();
            String currUpTime = tMsg.ezUpTime.getValue();
            String currTimeZone = tMsg.ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
                cMsg.setMessageInfo(NZZM0003E);
                return false;
            }
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"Model Group"});
                return false;
            }
            // add start 2016/06/27 CSA Defect#7886
            String isModel = NSAL0060Constant.DELETE;
            NSAL0060CommonLogic.invokeMasterDataMessaging(isModel, tMsg.ezUpTime.getValue(), csMsg.mdlGrpId_SR.toString(), csMsg.mdlGrpNm_SR.getValue());
            // add end 2016/06/27 CSA Defect#7886
        }
        return true;
    }

    private int insertOrUpdate(NSAL0060CMsg cMsg, NSAL0060SMsg sMsg) {
        BigDecimal maxId = getNextModelGroupId();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0060_ASMsg asMsg = sMsg.A.no(i);
            if (!hasValue(asMsg.mdlGrpId_SR)) {
                if (NSAL0060CommonLogic.isSameMdlGrpNm(asMsg.mdlGrpNm_SR.getValue(), getGlobalCompanyCode())) {
                    return i;
                }
                if (!insert(cMsg, asMsg, maxId)) {
                    return -1;
                }
                maxId = maxId.add(BigDecimal.ONE);
                // add start 2016/06/27 CSA Defect#7886
                String isModel = NSAL0060Constant.CREATE;
                NSAL0060CommonLogic.invokeMasterDataMessaging(isModel, asMsg.ezUpTime_SR.getValue(), asMsg.mdlGrpId_SR.toString(), asMsg.mdlGrpNm_SR.getValue());
                // add end 2016/06/27 CSA Defect#7886
            } else {
                NSAL0060_BSMsg bsMsg = sMsg.B.no(i);
                if (!update(cMsg, asMsg, bsMsg)) {
                    return -1;
                }
                // add start 2016/06/27 CSA Defect#7886
                String isModel = NSAL0060Constant.UPDATE;
                NSAL0060CommonLogic.invokeMasterDataMessaging(isModel, asMsg.ezUpTime_SR.getValue(), asMsg.mdlGrpId_SR.toString(), asMsg.mdlGrpNm_SR.getValue());
                // add end 2016/06/27 CSA Defect#7886
            }
        }
        return -1;
    }

    private BigDecimal getNextModelGroupId() {
        S21SsmEZDResult res = NSAL0060Query.getInstance().getNextModelGroupId(getGlobalCompanyCode());
        if (res.isCodeNormal()) {
            BigDecimal maxId = (BigDecimal) res.getResultObject();
            return maxId;
        }
        return BigDecimal.ONE;
    }

    private boolean insert(NSAL0060CMsg cMsg, NSAL0060_ASMsg asMsg, BigDecimal maxId) {
        DS_MDL_GRPTMsg inMsg = new DS_MDL_GRPTMsg();
        setValue(inMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(inMsg.mdlGrpId, maxId);
        setValue(inMsg.mdlGrpNm, asMsg.mdlGrpNm_SR);
        setValue(inMsg.mdlGrpDescTxt, asMsg.mdlGrpDescTxt_SR);
        EZDTBLAccessor.insert(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"Model Group"});
            return false;
        }
        return true;
    }

    private boolean update(NSAL0060CMsg cMsg, NSAL0060_ASMsg asMsg, NSAL0060_BSMsg bsMsg) {
        if (asMsg.mdlGrpNm_SR.getValue().equals(bsMsg.mdlGrpNm_SR.getValue())
                && asMsg.mdlGrpDescTxt_SR.getValue().equals(bsMsg.mdlGrpDescTxt_SR.getValue())) {
            // same
            return true;
        }

        DS_MDL_GRPTMsg upMsg = NSAL0060CommonLogic.locDsMdlGrp(asMsg.mdlGrpId_SR.getValue(), getGlobalCompanyCode());
        if (upMsg == null) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        String preUpTime = asMsg.ezUpTime_SR.getValue();
        String preTimeZone = asMsg.ezUpTimeZone_SR.getValue();
        String currUpTime = upMsg.ezUpTime.getValue();
        String currTimeZone = upMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        setValue(upMsg.mdlGrpDescTxt, asMsg.mdlGrpDescTxt_SR);
        EZDTBLAccessor.update(upMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(upMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"Model Group"});
            return false;
        }
        return true;
    }
}
