/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2840;

import static business.blap.NMAL2840.constant.NMAL2840Constant.CHECK_ERROR;
import static business.blap.NMAL2840.constant.NMAL2840Constant.CHECK_INFORMATION;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DATE_PATTAN;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_11;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_12;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_21;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_31;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_32;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_33;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_34;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_50;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_51;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_52;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_53;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_54;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_55;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_56;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_57;
import static business.blap.NMAL2840.constant.NMAL2840Constant.COMMA;
import static business.blap.NMAL2840.constant.NMAL2840Constant.NMAM0282E;
import static business.blap.NMAL2840.constant.NMAL2840Constant.NMAM8549E;
import static business.blap.NMAL2840.constant.NMAL2840Constant.NMAM8550I;
import static business.blap.NMAL2840.constant.NMAL2840Constant.NMAM8657W;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2840.common.NMAL2840CommonLogic;
import business.db.DUNS_TRX_CRITTMsg;
import business.db.DUNS_TRX_FILETMsg;
import business.db.DUNS_TRX_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_CRIT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2840BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Fujitsu         R.Nakamura      Create          N/A
 * 2016/06/16   Fujitsu         R.Nakamura      Update          QC#10224
 * 2016/07/04   Fujitsu         R.Nakamura      Update          QC#11316
 * 2016/09/23   Fujitsu         R.Nakamura      Update          QC#14724
 * 2016/10/06   Fujitsu         R.Nakamura      Update          QC#14861
 * 2016/11/08   Fujitsu         N.Sugiura       Update          QC#14832
 *</pre>
 */
public class NMAL2840BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2840CMsg bizMsg = (NMAL2840CMsg) cMsg;
            NMAL2840SMsg glblMsg = (NMAL2840SMsg) sMsg;

            if ("NMAL2840Scrn00_ExtractSend".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_ExtractSend(bizMsg, glblMsg);

            } else if ("NMAL2840Scrn00_ExpForReview".equals(screenAplID)) {
                doProcess_NMAL2840Scrn00_ExpForReview(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Extract & Send
     * @param bizMsg NMAL2840CMsg
     * @param glblMsg NMAL2840SMsg
     */
    private void doProcess_NMAL2840Scrn00_ExtractSend(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        int checkFlgNum = imputErrCheck(bizMsg);
        if (checkFlgNum == 1) {
            bizMsg.setMessageInfo(NMAM8550I);
        } else if (checkFlgNum == CHECK_ERROR) {
            bizMsg.setMessageInfo(NMAM8549E);
            return;
        }

        // Add Start 2016/11/08 QC#14832
        if (!chkDuplicateFilter(bizMsg)) {
            bizMsg.setMessageInfo(NMAM8657W);
            return;
        }
        // Add End 2016/11/08 QC#14832

        BigDecimal seqDTH = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DUNS_TRX_HDR_SQ);
        DUNS_TRX_HDRTMsg tMsgTDH = new DUNS_TRX_HDRTMsg();
        // Add Start 2016/10/06 QC#14861
//        setValueDTHTmsg(tMsgTDH, seqDTH, DUNS_PROC_TP_CD_10, DUNS_PROC_STS_CD_01);
        setValueDTHTmsg(tMsgTDH, seqDTH, DUNS_PROC_TP.EXTRACT_FOR_DNB, DUNS_PROC_STS.REGISTERED);
        // Add End 2016/10/06 QC#14861
        boolean errFlg = false;
        EZDTBLAccessor.insert(tMsgTDH);

        if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(tMsgTDH.getReturnCode())) {
            errFlg = true;
        }

        if (!errFlg && ZYPCommonFunc.hasValue(bizMsg.dunsCritCd_PS.getValue())) {
            String setDunsCritId = bizMsg.dunsCritCd_PS.getValue();
            String setDunsCritValue = NMAL2840CommonLogic.getDunsCRITDescTxt(getGlobalCompanyCode(), setDunsCritId);
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritCd_PS, true, setDunsCritId, setDunsCritValue);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_11, false, DUNS_CRIT_DEF_VAL_FLG_11);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_11, false, DUNS_CRIT_DEF_VAL_FLG_12);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_11, false, DUNS_CRIT_DEF_VAL_FLG_21);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_11, false, DUNS_CRIT_DEF_VAL_FLG_31);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_11, false, DUNS_CRIT_DEF_VAL_FLG_32);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_11, false, DUNS_CRIT_DEF_VAL_FLG_33);
        }

        if (!errFlg && ZYPCommonFunc.hasValue(bizMsg.effFromDt.getValue())) {
            String setDunsCritValue = bizMsg.effFromDt.getValue();
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritCd_PS, true, DUNS_CRIT_DEF_VAL_FLG_34, setDunsCritValue);
        }

        if (errFlg) {
            bizMsg.setMessageInfo(NMAM0282E, new String[] {"Extract DNB Data" });
        }
    }

    /**
     * Export For Review
     * @param bizMsg NMAL2840CMsg
     * @param glblMsg NMAL2840SMsg
     */
    private void doProcess_NMAL2840Scrn00_ExpForReview(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        BigDecimal seqDTH = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DUNS_TRX_HDR_SQ);
        DUNS_TRX_HDRTMsg tMsgTDH = new DUNS_TRX_HDRTMsg();
        // Mod Start 2016/09/23 QC#14724
//        setValueDTHTmsg(tMsgTDH, seqDTH, DUNS_PROC_TP_CD_10, DUNS_PROC_STS_CD_30);
        setValueDTHTmsg(tMsgTDH, seqDTH, DUNS_PROC_TP.DOWNLOAD_DNB_FILE_FOR_REVIEW, DUNS_PROC_STS.DONE);
        // Mod End 2016/09/23 QC#14724
        boolean errFlg = false;
        EZDTBLAccessor.insert(tMsgTDH);

        if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(tMsgTDH.getReturnCode())) {
            errFlg = true;
        }

        if (!errFlg && ZYPCommonFunc.hasValue(bizMsg.xxDtTm_PS.getValue())) {
            // Mod Start 2016/06/16 QC#10224
            // String setDunsCritId = bizMsg.xxDtTm_PS.getValue();
            // String setDunsCritValue =
            // NMAL2840CommonLogic.getDunsCRITDescTxt(getGlobalCompanyCode(),
            // setDunsCritId);
            String setDunsCritId = DUNS_CRIT_DEF_VAL_FLG_50;
            String setDunsCritValue = bizMsg.xxDtTm_PS.getValue();
            // Mod End 2016/06/16 QC#10224

            errFlg = setValueDTCTmsg(seqDTH, bizMsg.xxDtTm_PS, true, setDunsCritId, setDunsCritValue);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_51.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_51, false, DUNS_CRIT_DEF_VAL_FLG_51);
        } else if (!errFlg && !bizMsg.dunsCritDefValFlg_51.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            String setDunsCritValue = bizMsg.dunsCritDescTxt_51.getValue();
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_51, true, DUNS_CRIT_DEF_VAL_FLG_51, setDunsCritValue);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_52.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_52, false, DUNS_CRIT_DEF_VAL_FLG_52);
        } else if (!errFlg && !bizMsg.dunsCritDefValFlg_52.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            String setDunsCritValue = bizMsg.dunsCritDescTxt_52.getValue();
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_52, true, DUNS_CRIT_DEF_VAL_FLG_52, setDunsCritValue);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_53.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_53, false, DUNS_CRIT_DEF_VAL_FLG_53);
        } else if (!errFlg && !bizMsg.dunsCritDefValFlg_53.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            String setDunsCritValue = bizMsg.dunsCritDescTxt_53.getValue();
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_53, true, DUNS_CRIT_DEF_VAL_FLG_53, setDunsCritValue);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_54.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_54, false, DUNS_CRIT_DEF_VAL_FLG_54);
        } else if (!errFlg && !bizMsg.dunsCritDefValFlg_54.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            String setDunsCritValue = bizMsg.dunsCritDescTxt_54.getValue();
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_54, true, DUNS_CRIT_DEF_VAL_FLG_54, setDunsCritValue);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_55.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_55, false, DUNS_CRIT_DEF_VAL_FLG_55);
        } else if (!errFlg && !bizMsg.dunsCritDefValFlg_55.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            String setDunsCritValue = bizMsg.dunsCritDescTxt_55.getValue();
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_55, true, DUNS_CRIT_DEF_VAL_FLG_55, setDunsCritValue);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_56.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_56, false, DUNS_CRIT_DEF_VAL_FLG_56);
        } else if (!errFlg && !bizMsg.dunsCritDefValFlg_56.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            String setDunsCritValue = bizMsg.dunsCritDescTxt_56.getValue();
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_56, true, DUNS_CRIT_DEF_VAL_FLG_56, setDunsCritValue);
        }

        if (!errFlg && bizMsg.dunsCritDefValFlg_57.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_57, false, DUNS_CRIT_DEF_VAL_FLG_57);
        } else if (!errFlg && !bizMsg.dunsCritDefValFlg_57.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            String setDunsCritValue = bizMsg.dunsCritDescTxt_57.getValue();
            errFlg = setValueDTCTmsg(seqDTH, bizMsg.dunsCritDefValFlg_57, true, DUNS_CRIT_DEF_VAL_FLG_57, setDunsCritValue);
        }

        if (!errFlg) {
            Map<String, Object> params = NMAL2840CommonLogic.setMapValue(bizMsg, getGlobalCompanyCode());
            NMAL2840Query.getInstance().createCSV(bizMsg, params);
            // Mod Start 2016/07/04 QC#11316
            errFlg = setValueDTFTmsg(seqDTH, bizMsg.xxRowNum_DL.getValue());
            // Mod End 2016/07/04 QC#11316
        }

        if (errFlg) {
            bizMsg.setMessageInfo(NMAM0282E, new String[] {"Extract For Review" });
        }
    }

    /**
     * imputErrCheck
     * @param bizMsg NMAL2840CMsg
     * @param glblMsg NMAL2840SMsg
     */
    private int imputErrCheck(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;
        if (bizMsg.dunsCritCd_PS.getValue().equals(DUNS_CRIT.YEARLY)) {
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG0(bizMsg);
            }
        } else {
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG1(bizMsg);
            }
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG2(bizMsg);
            }
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG3(bizMsg);
            }
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG4(bizMsg);
            }
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG5(bizMsg);
            }
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG6(bizMsg);
            }
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG7(bizMsg);
            }
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG8(bizMsg);
            }
            if (checkFlgNum == 0) {
                checkFlgNum = checkPattanG9(bizMsg);
            }
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG0
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG0(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = 0;
        } else {
            checkFlgNum = CHECK_ERROR;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG1
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG1(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = 0;
        }

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = CHECK_ERROR;
        }

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = CHECK_INFORMATION;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG2
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG2(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = CHECK_ERROR;
        }

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = CHECK_ERROR;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG3
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG3(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = CHECK_ERROR;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG4
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG4(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = 0;
        }

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = 0;
        }

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = 0;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG5
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG5(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = 0;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG6
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG6(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = 0;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG7
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG7(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = 0;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG8
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG8(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && (bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || !bizMsg.effFromDt
                        .getValue().equals(""))) {
            checkFlgNum = 0;
        }

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && (bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || !bizMsg.effFromDt
                        .getValue().equals(""))) {
            checkFlgNum = 0;
        }

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && (bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || !bizMsg.effFromDt
                        .getValue().equals(""))) {
            checkFlgNum = 0;
        }

        return checkFlgNum;
    }

    /**
     * checkPattanG9
     * @param bizMsg NMAL2840CMsg
     * @return checkFlgNum int
     */
    private int checkPattanG9(NMAL2840CMsg bizMsg) {

        int checkFlgNum = 0;

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) && !bizMsg.effFromDt.getValue().equals("")) {
            checkFlgNum = CHECK_ERROR;
        }

        if (!bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && !bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)
                && (bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y) || !bizMsg.effFromDt
                        .getValue().equals(""))) {
            checkFlgNum = CHECK_ERROR;
        }

        return checkFlgNum;
    }

    private void setValueDTHTmsg(DUNS_TRX_HDRTMsg tMsg, BigDecimal seqTDH, String dunsProvTpCd, String dunsProcStsCd) {
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.dunsTrxHdrPk, seqTDH);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsProcTpCd, dunsProvTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsProcStsCd, dunsProcStsCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_PATTAN));
        ZYPEZDItemValueSetter.setValue(tMsg.dunsProcById, getContextUserInfo().getUserId());
        tMsg.dunsProcCmntTxt.clear();
    }

    private boolean setValueDTCTmsg(BigDecimal seqDTH, EZDCStringItem setValue, boolean nullFlg, String setCritId, String setCritValue) {

        BigDecimal seqDTC = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DUNS_TRX_CRIT_SQ);
        DUNS_TRX_CRITTMsg tMsg = new DUNS_TRX_CRITTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.dunsTrxCritPk, seqDTC);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsTrxHdrPk, seqDTH);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsCritCd, setCritId);
        if (nullFlg) {
            ZYPEZDItemValueSetter.setValue(tMsg.dunsCritValFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tMsg.dunsCritValTxt, setCritValue);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.dunsCritValFlg, ZYPConstant.FLG_ON_Y);
            if (ZYPCommonFunc.hasValue(setCritValue)) {
                tMsg.dunsCritValTxt.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.dunsCritValTxt, setCritValue);
            }
        }

        EZDTBLAccessor.insert(tMsg);
        boolean errFlg = false;

        if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(tMsg.getReturnCode())) {
            errFlg = true;
        }

        return errFlg;
    }

    private boolean setValueDTCTmsg(BigDecimal seqDTH, EZDCStringItem setValue, boolean nullFlg, String setCritId) {

        return setValueDTCTmsg(seqDTH, setValue, nullFlg, setCritId, "");
    }

    private boolean setValueDTFTmsg(BigDecimal seqDTH, BigDecimal getCsvNum) {

        BigDecimal seqDTC = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DUNS_TRX_FILE_SQ);
        DUNS_TRX_FILETMsg tMsg = new DUNS_TRX_FILETMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.dunsTrxFilePk, seqDTC);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsTrxHdrPk, seqDTH);
        // Mod Start 2016/10/06 QC#14861
//        ZYPEZDItemValueSetter.setValue(tMsg.dunsFileTpCd, DUNS_PROC_TP_CD_20);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsFileTpCd, DUNS_PROC_TP.RECEIVE_AND_IMOORT_DNB_FILE);
        // Mod End 2016/10/06 QC#14861
        tMsg.dunsFileNm.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.dunsFileLineNum, getCsvNum);

        EZDTBLAccessor.insert(tMsg);
        boolean errFlg = false;

        if (EZDTBLAccessor.RTNCD_DUPLICATE.equals(tMsg.getReturnCode())) {
            errFlg = true;
        }

        return errFlg;
    }
    // Add Start 2016/11/08 QC#14832
    private boolean chkDuplicateFilter(NMAL2840CMsg bizMsg) {

        Integer dupCount = 0;

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunsCritDefValFlg_WN.getValue())
                && bizMsg.dunsCritCd_PS.getValue().equals(bizMsg.dunsCritCd_BK.getValue())
                && bizMsg.dunsCritDefValFlg_11.getValue().equals(bizMsg.dunsCritDefValFlg_B1.getValue())
                && bizMsg.dunsCritDefValFlg_12.getValue().equals(bizMsg.dunsCritDefValFlg_B2.getValue())
                && bizMsg.dunsCritDefValFlg_21.getValue().equals(bizMsg.dunsCritDefValFlg_B3.getValue())
                && bizMsg.dunsCritDefValFlg_31.getValue().equals(bizMsg.dunsCritDefValFlg_B4.getValue())
                && bizMsg.dunsCritDefValFlg_32.getValue().equals(bizMsg.dunsCritDefValFlg_B5.getValue())
                && bizMsg.dunsCritDefValFlg_33.getValue().equals(bizMsg.dunsCritDefValFlg_B6.getValue())
                && bizMsg.effFromDt.getValue().equals(bizMsg.effFromDt_BK.getValue())) {

            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_WN, ZYPConstant.FLG_OFF_N);
            return true;

        }

        List<String> dunsCritIdList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(bizMsg.dunsCritCd_PS)) {
            dunsCritIdList.add(bizMsg.dunsCritCd_PS.getValue());
        }

        if (bizMsg.dunsCritDefValFlg_11.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            dunsCritIdList.add(DUNS_CRIT_DEF_VAL_FLG_11);
        }

        if (bizMsg.dunsCritDefValFlg_12.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            dunsCritIdList.add(DUNS_CRIT_DEF_VAL_FLG_12);
        }

        if (bizMsg.dunsCritDefValFlg_21.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            dunsCritIdList.add(DUNS_CRIT_DEF_VAL_FLG_21);
        }

        if (bizMsg.dunsCritDefValFlg_31.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            dunsCritIdList.add(DUNS_CRIT_DEF_VAL_FLG_31);
        }

        if (bizMsg.dunsCritDefValFlg_32.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            dunsCritIdList.add(DUNS_CRIT_DEF_VAL_FLG_32);
        }

        if (bizMsg.dunsCritDefValFlg_33.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
            dunsCritIdList.add(DUNS_CRIT_DEF_VAL_FLG_33);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.effFromDt)) {
            dunsCritIdList.add(DUNS_CRIT_DEF_VAL_FLG_34);
        }

        String dunsCritId = "";
        for (int i = 0; i < dunsCritIdList.size(); ++i) {
            if (i == 0) {
                dunsCritId = S21StringUtil.concatStrings(dunsCritId, dunsCritIdList.get(i));
            } else {
                dunsCritId = S21StringUtil.concatStrings(dunsCritId, COMMA, dunsCritIdList.get(i));
            }
        }

        S21SsmEZDResult rs = NMAL2840Query.getInstance().countDuplicateFilter(bizMsg, dunsCritId);
        if (rs.isCodeNormal()) {
            dupCount = (Integer) rs.getResultObject();
        }
        if (dupCount > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_WN, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritCd_BK, bizMsg.dunsCritCd_PS);
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_B1, bizMsg.dunsCritDefValFlg_11);
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_B2, bizMsg.dunsCritDefValFlg_12);
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_B3, bizMsg.dunsCritDefValFlg_21);
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_B4, bizMsg.dunsCritDefValFlg_31);
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_B5, bizMsg.dunsCritDefValFlg_32);
            ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_B6, bizMsg.dunsCritDefValFlg_33);
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_BK, bizMsg.effFromDt);

            return false;
        }
        return true;
    }
    // Add End 2016/11/08 QC#14832

}
