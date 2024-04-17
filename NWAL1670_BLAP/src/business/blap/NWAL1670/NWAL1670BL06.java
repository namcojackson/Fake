/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1670;

import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0791E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0794E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NZZM0002I;
import static business.blap.NWAL1670.constant.NWAL1670Constant.ZZZM9004E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1670.common.NWAL1670CommonLogic;
import business.db.ORD_TEAM_MSTR_DTLTMsg;
import business.db.ORD_TEAM_MSTR_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_TEAM_ATTRB_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Order Team Set up
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma         Create          N/A
 * 2016/05/16   Hitachi         O.Okuma         Update          S21_NA#7104
 *</pre>
 */
public class NWAL1670BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NWAL1670CMsg cMsg = (NWAL1670CMsg) arg0;
        NWAL1670SMsg sMsg = (NWAL1670SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NWAL1670Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1670Scrn00_CMN_Submit(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1670Scrn00_CMN_Submit(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (cMsg.A.getValidCount() <= 0) {
            cMsg.setMessageInfo(NWAM0794E);
            return;
        }

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NWAL1670CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
        NWAL1670CommonLogic.copyToCSmsg(cMsg, sMsg);

        if (!deleteOrdTeamMstrDtl(cMsg, sMsg)) {
            return;
        }
        if (!NWAL1670CommonLogic.cheackTeamList(cMsg, sMsg)) {
            return;
        }
        if (!NWAL1670CommonLogic.cheackMemberList(cMsg)) {
            return;
        }
        if (!NWAL1670CommonLogic.cheackAttributeList(cMsg, sMsg)) {
            cMsg.ordTeamAttrbTpCd.clear();
            NWAL1670CommonLogic.doFilterForAttribute(cMsg, sMsg);
            return;
        }

        doSubmit(cMsg, sMsg);
    }

    private void doSubmit(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        List<Integer> numList = NWAL1670CommonLogic.getChangeDataNumList(sMsg);

        // 2016/05/16 S21_NA#7104 Mod Start
//        int index = cMsg.xxRadioBtn.getValueInt();
//        int rowNum = cMsg.A.no(index).xxRowNum_A.getValueInt();
//        BigDecimal ordTeamMstrHdrPkForSlct = cMsg.A.no(index).ordTeamMstrHdrPk_A.getValue();
//        for (int i = 0; i < numList.size(); i++) {
//            NWAL1670_ASMsg asMsg = sMsg.A.no(numList.get(i));
//
//            if (hasValue(asMsg.ordTeamMstrHdrPk_A)) {
//                if (!updateOrdTeamMstrHdr(cMsg, asMsg)) {
//                    return;
//                }
//            } else {
//                BigDecimal ordTeamMstrHdrPk = insertOrdTeamMstrHdr(cMsg, asMsg);
//                if (ordTeamMstrHdrPk == null) {
//                    return;
//                }
//                if (asMsg.xxRowNum_A.getValueInt() == rowNum) {
//                    ordTeamMstrHdrPkForSlct = ordTeamMstrHdrPk;
//                }
//            }
//        }
        BigDecimal ordTeamMstrHdrPkForSlct = null;
        int rowNum = -1;

        if (hasValue(cMsg.xxRadioBtn)) {
            int index = cMsg.xxRadioBtn.getValueInt();
            rowNum = cMsg.A.no(index).xxRowNum_A.getValueInt();
            ordTeamMstrHdrPkForSlct = cMsg.A.no(index).ordTeamMstrHdrPk_A.getValue();
        }

        for (int i: numList) {
            NWAL1670_ASMsg asMsg = sMsg.A.no(i);

            if (hasValue(asMsg.ordTeamMstrHdrPk_A)) {
                if (!updateOrdTeamMstrHdr(cMsg, asMsg)) {
                    return;
                }
            } else {
                BigDecimal ordTeamMstrHdrPk = insertOrdTeamMstrHdr(cMsg, asMsg);
                if (ordTeamMstrHdrPk == null) {
                    return;
                }
                if (asMsg.xxRowNum_A.getValueInt() == rowNum) {
                    ordTeamMstrHdrPkForSlct = ordTeamMstrHdrPk;
                }
            }
        }
        //2016/05/16 S21_NA#7104 Mod END

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NWAL1670_BCMsg bcMsg = cMsg.B.no(i);
            if (!hasValue(bcMsg.ordTeamMstrDtlPk_B)) {

                ORD_TEAM_MSTR_DTLTMsg tMsg = new ORD_TEAM_MSTR_DTLTMsg();
                setParamForOrdTeamMstrDtlForMember(cMsg, ordTeamMstrHdrPkForSlct, bcMsg.ordTeamAttrbValTxt_B, tMsg);

                if (!insertOrdTeamMstrDtl(cMsg, tMsg)) {
                    return;
                }
            }
        }

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NWAL1670_CSMsg csMsg = sMsg.C.no(i);
            if (!hasValue(csMsg.ordTeamMstrDtlPk_C)) {

                ORD_TEAM_MSTR_DTLTMsg tMsg = new ORD_TEAM_MSTR_DTLTMsg();
                setParamForOrdTeamMstrDtlForAttribute(cMsg, ordTeamMstrHdrPkForSlct, csMsg, tMsg);

                if (!insertOrdTeamMstrDtl(cMsg, tMsg)) {
                    return;
                }
            }
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    private BigDecimal insertOrdTeamMstrHdr(NWAL1670CMsg cMsg, NWAL1670_ASMsg asMsg) {

        ORD_TEAM_MSTR_HDRTMsg tMsg = new ORD_TEAM_MSTR_HDRTMsg();

        setParamForOrdTeamMstrHdr(cMsg, asMsg, tMsg, true);
        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0791E, new String[] {"TBL_NM_ORD_TEAM_MSTR_HDR" });
            return null;
        }
        return tMsg.ordTeamMstrHdrPk.getValue();
    }

    private boolean updateOrdTeamMstrHdr(NWAL1670CMsg cMsg, NWAL1670_ASMsg asMsg) {

        ORD_TEAM_MSTR_HDRTMsg tMsg = NWAL1670CommonLogic.getOrdTeamMstrHdr(cMsg.glblCmpyCd.getValue(), asMsg.ordTeamMstrHdrPk_A.getValue(), true);

        if (tMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(asMsg.ezUpTime_A.getValue(), asMsg.ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }
        setParamForOrdTeamMstrHdr(cMsg, asMsg, tMsg, false);
        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0791E, new String[] {"TBL_NM_ORD_TEAM_MSTR_HDR" });
            return false;
        }
        return true;
    }

    private boolean insertOrdTeamMstrDtl(NWAL1670CMsg cMsg, ORD_TEAM_MSTR_DTLTMsg tMsg) {

        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0791E, new String[] {"TBL_NM_ORD_TEAM_MSTR_DTL" });
            return false;
        }
        return true;
    }

    private boolean deleteOrdTeamMstrDtl(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            NWAL1670_ESMsg esMsg = sMsg.E.no(i);
            ORD_TEAM_MSTR_DTLTMsg tMsg = NWAL1670CommonLogic.getOrdTeamMstrDtl(cMsg.glblCmpyCd.getValue(), esMsg.ordTeamMstrDtlPk_E.getValue());

            if (tMsg == null) {
                cMsg.setMessageInfo(ZZZM9004E);
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(esMsg.ezUpTime_E.getValue(), esMsg.ezUpTimeZone_E.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return false;
            }
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NWAM0791E, new String[] {"TBL_NM_ORD_TEAM_MSTR_DTL" });
                return false;
            }
        }
        return true;
    }

    private void setParamForOrdTeamMstrHdr(NWAL1670CMsg cMsg, NWAL1670_ASMsg asMsg, ORD_TEAM_MSTR_HDRTMsg tMsg, boolean isIns) {

        if (isIns) {
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.ordTeamMstrHdrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_TEAM_MSTR_HDR_SQ));
        }
        setValue(tMsg.ordTeamMstrNm, asMsg.ordTeamMstrNm_A);
        setValue(tMsg.ordZnCd, asMsg.ordZnCd_A);
        setValue(tMsg.effFromDt, asMsg.effFromDt_A);
        setValue(tMsg.effThruDt, asMsg.effThruDt_A);
    }

    private void setParamForOrdTeamMstrDtlForMember(NWAL1670CMsg cMsg, BigDecimal ordTeamMstrHdrPk, EZDCStringItem ordTeamAttrbValTxt, ORD_TEAM_MSTR_DTLTMsg tMsg) {

        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.ordTeamMstrHdrPk, ordTeamMstrHdrPk);
        setValue(tMsg.ordTeamMstrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_TEAM_MSTR_DTL_SQ));
        setValue(tMsg.ordTeamAttrbTpCd, ORD_TEAM_ATTRB_TP.USER_ID);
        setValue(tMsg.ordTeamAttrbValTxt, ordTeamAttrbValTxt);
    }

    private void setParamForOrdTeamMstrDtlForAttribute(NWAL1670CMsg cMsg, BigDecimal ordTeamMstrHdrPk, NWAL1670_CSMsg csMsg, ORD_TEAM_MSTR_DTLTMsg tMsg) {

        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.ordTeamMstrHdrPk, ordTeamMstrHdrPk);
        setValue(tMsg.ordTeamMstrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_TEAM_MSTR_DTL_SQ));
        setValue(tMsg.ordTeamAttrbTpCd, csMsg.ordTeamAttrbTpCd_C);
        setValue(tMsg.ordTeamAttrbValTxt, csMsg.ordTeamAttrbValTxt_C);
    }
}
