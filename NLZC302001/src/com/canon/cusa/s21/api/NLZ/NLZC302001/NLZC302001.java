/**
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC302001;

import java.math.BigDecimal;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSE_SER_NUM_RNGTMsg;
import business.db.MDSE_SER_NUM_RNGTMsgArray;
import business.db.SER_TRXTMsg;
import business.db.SER_TRXTMsgArray;
import business.parts.NLZC302001PMsg;
import business.parts.NLZC302001_UpdateDetailListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2012   Hitachi         K.Yamada        Create          N/A
 * 07/17/2013   Fujitsu         A.Shinohara     Update          #21 Serial Repository
 * 01/13/2014   CSAI            K.Lee           Update          QC3410
 * 10/13/2015   CITS            Hisashi         Update          for CSA Requirements
 *</pre>
 */
public class NLZC302001 extends S21ApiCommonBase {

    /**
     * constructor
     */
    public NLZC302001() {
        super();
    }

    /**
     * execute API
     * @param param NLZC302001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC302001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        execUpdate(msgMap, onBatchType);
        setMessageList(msgMap);
        msgMap.flush();

    }

    /**
     * main process of API
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    private void execUpdate(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        NLZC302001PMsg param = (NLZC302001PMsg) msgMap.getPmsg();

        // Check Level1 Parameters.
        checkCommonParameter(msgMap, param);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // Process Each Detail.
        for (int i = 0; i < param.UpdateDetailList.getValidCount(); i++) {
            NLZC302001_UpdateDetailListPMsg detailMsg = param.UpdateDetailList.no(i);

            if (ZYPCommonFunc.hasValue(detailMsg.serTrxPk)) {
                updateSerTrx(param.glblCmpyCd.getValue(), detailMsg);
            } else {
                createSerTrx(param.glblCmpyCd.getValue(), detailMsg);
            }
        }

    }

    /**
     * Create New SER_TRX Record.
     * @param glblCmpyCd String
     * @param detailMsg NLZC302001_UpdateDetailListPMsg
     */
    private void createSerTrx(String glblCmpyCd, NLZC302001_UpdateDetailListPMsg detailMsg) {
        // Check Level2 Parameters.
        if (isErrorDetailParameter(detailMsg)) {
            return;
        }

        // Set Error Status Code by checking Serial Number Length and Range
        if (!ZYPCommonFunc.hasValue(detailMsg.serErrStsCd)) {
            checkSerNumLgRng(glblCmpyCd, detailMsg);
        }

        // Get Original Merchandise Code.
        String origMdseCd = getOrigMdseCd(glblCmpyCd, detailMsg);

        // Get Serial Transaction Latest Flag.
        String serTrxLtstFlg = getSerTrxLtstFlg(glblCmpyCd, detailMsg);
        if (!ZYPCommonFunc.hasValue(serTrxLtstFlg)) {
            return;
        }

        // Execute Insert.
        SER_TRXTMsg cratSerTrxTMsg = setSerTrxTMsgParamForCreate(glblCmpyCd, detailMsg, origMdseCd, serTrxLtstFlg);
        S21ApiTBLAccessor.insert(cratSerTrxTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cratSerTrxTMsg.getReturnCode())) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2173E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2173E);
            return;
        }

        // Return SER_TRX_PK.
        detailMsg.serTrxPk.setValue(cratSerTrxTMsg.serTrxPk.getValue());
    }

    /**
     * Update Exist SER_TRX Record.
     * @param glblCmpyCd String
     * @param detailMsg NLZC302001_UpdateDetailListPMsg
     */
    private void updateSerTrx(String glblCmpyCd, NLZC302001_UpdateDetailListPMsg detailMsg) {
        // Get SER_TRX record.
        SER_TRXTMsg rsltSerTrxTMsg = getSerTrxTMsgForUpdate(glblCmpyCd, detailMsg.serTrxPk.getValue());
        if (rsltSerTrxTMsg == null) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2172E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2172E);
            return;
        }

        // Set Parameters.
        ZYPEZDItemValueSetter.setValue(rsltSerTrxTMsg.serTrxCmntTxt, detailMsg.serTrxCmntTxt);
        ZYPEZDItemValueSetter.setValue(rsltSerTrxTMsg.serErrStsCd, detailMsg.serErrStsCd);
        if (NLZC302001Constant.MAN_DEL_ERR.compareTo(detailMsg.serErrStsCd.getValue()) == 0
                && ZYPConstant.FLG_ON_Y.compareTo(rsltSerTrxTMsg.serTrxLtstFlg.getValue()) == 0) {
            rsltSerTrxTMsg.serTrxLtstFlg.setValue(ZYPConstant.FLG_OFF_N);
        }

        // Execute Update.
        S21ApiTBLAccessor.update(rsltSerTrxTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rsltSerTrxTMsg.getReturnCode())) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2174E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2174E);
        }

        // update the latest flag for manual deletion
        updateSerTrxLtstFlgForUpdate(glblCmpyCd, rsltSerTrxTMsg, detailMsg);
    }

    /**
     * Check level1 parameter
     * @param msgMap S21ApiMessageMap
     * @param param NLZC302001PMsg
     */
    private void checkCommonParameter(S21ApiMessageMap msgMap, NLZC302001PMsg param) {
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NLZC302001Constant.NLZM2052E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2052E);
        }
    }

    /**
     * Check Level2 Parameters.
     * @param detailMsg NLZC302001_UpdateDetailListPMsg
     * @return Boolean
     */
    private boolean isErrorDetailParameter(NLZC302001_UpdateDetailListPMsg detailMsg) {
        if (!ZYPCommonFunc.hasValue(detailMsg.serNum)) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2091E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2091E);
            return true;
        }
        if (!ZYPCommonFunc.hasValue(detailMsg.mdseCd)) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM0005E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM0005E);
            return true;
        }
        if (!ZYPCommonFunc.hasValue(detailMsg.serTrxTs)) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2169E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2169E);
            return true;
        }
        if (!ZYPCommonFunc.hasValue(detailMsg.serTrxEventCd)) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2170E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2170E);
            return true;
        }
        if (!ZYPCommonFunc.hasValue(detailMsg.manCratFlg)) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2171E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2171E);
            return true;
        }
        if (!ZYPCommonFunc.hasValue(detailMsg.toStkStsCd)) {
            detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2270E);
            S21InfoLogOutput.println(NLZC302001Constant.NLZM2270E);
            return true;
        }

        return false;
    }

    /**
     * Get Original Merchandise Code For New Record.
     * @param glblCmpyCd String
     * @param detailMsg NLZC302001_UpdateDetailListPMsg
     * @return String
     */
    private String getOrigMdseCd(String glblCmpyCd, NLZC302001_UpdateDetailListPMsg detailMsg) {
        SER_TRXTMsgArray rsltSerTrxTMsgArray;
        if (ZYPCommonFunc.hasValue(detailMsg.oldMdseCd)) {
            rsltSerTrxTMsgArray = getSerTrxTMsgArray(glblCmpyCd, detailMsg.serNum.getValue(), detailMsg.oldMdseCd.getValue());
            if (rsltSerTrxTMsgArray == null || rsltSerTrxTMsgArray.length() == 0) {
                return detailMsg.oldMdseCd.getValue();
            }
        } else {
            rsltSerTrxTMsgArray = getSerTrxTMsgArray(glblCmpyCd, detailMsg.serNum.getValue(), detailMsg.mdseCd.getValue());
            if (rsltSerTrxTMsgArray == null || rsltSerTrxTMsgArray.length() == 0) {
                return detailMsg.mdseCd.getValue();
            }
        }
        return rsltSerTrxTMsgArray.no(0).origMdseCd.getValue();
    }

    /**
     * Get Serial Transaction Latest Flag For New Record.
     * @param glblCmpyCd String
     * @param detailMsg NLZC302001_UpdateDetailListPMsg
     * @return String
     */
    private String getSerTrxLtstFlg(String glblCmpyCd, NLZC302001_UpdateDetailListPMsg detailMsg) {

        String serTrxLtstFlg = ZYPConstant.FLG_ON_Y;

        // Get SER_TRX record.
        SER_TRXTMsgArray rsltSerTrxTMsgArray = getSerTrxTMsgArray(glblCmpyCd, detailMsg.serNum.getValue(), detailMsg.mdseCd.getValue());

        // set flag is Y because this serial# is first record.
        if (rsltSerTrxTMsgArray == null || rsltSerTrxTMsgArray.length() == 0) {
            return serTrxLtstFlg;
        }

        // Compare Serial Transaction Timestamp with the latest record in SER_TRX.
        int compDigit = rsltSerTrxTMsgArray.no(0).getAttr("serTrxTs").getDigit();
        BigDecimal ltstSerTrxTs = new BigDecimal(ZYPCommonFunc.rightPad(rsltSerTrxTMsgArray.no(0).serTrxTs.getValue(), compDigit, "0"));
        BigDecimal paramSerTrxTs = new BigDecimal(ZYPCommonFunc.rightPad(detailMsg.serTrxTs.getValue(), compDigit, "0"));

        if (ltstSerTrxTs.compareTo(paramSerTrxTs) > 0) {
            serTrxLtstFlg = ZYPConstant.FLG_OFF_N;
        }

        for (int i = 0; i < rsltSerTrxTMsgArray.getValidCount(); i++) {

            // the latest record does not neeed to update.
            if (ZYPConstant.FLG_OFF_N.compareTo(serTrxLtstFlg) == 0 && i == 0) {
                continue;
            }

            // Get SER_TRX record.
            SER_TRXTMsg rsltSerTrxTMsgForUpdate = getSerTrxTMsgForUpdate(glblCmpyCd, rsltSerTrxTMsgArray.no(i).serTrxPk.getValue());

            if (rsltSerTrxTMsgForUpdate == null) {
                detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2172E);
                S21InfoLogOutput.println(NLZC302001Constant.NLZM2172E);
                return null;
            }
    
            // Set Parameters.
            rsltSerTrxTMsgForUpdate.serTrxLtstFlg.setValue(ZYPConstant.FLG_OFF_N);
    
            // Execute Update.
            S21ApiTBLAccessor.update(rsltSerTrxTMsgForUpdate);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rsltSerTrxTMsgForUpdate.getReturnCode())) {
                detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2174E);
                S21InfoLogOutput.println(NLZC302001Constant.NLZM2174E);
                return null;
            }
        }

        return serTrxLtstFlg;
    }

    /**
     * Set Error Status Code by checking Serial Number Length and Range
     * @param glblCmpyCd String
     * @param detailMsg NLZC302001_UpdateDetailListPMsg
     * @return void
     */
    private void checkSerNumLgRng(String glblCmpyCd, NLZC302001_UpdateDetailListPMsg detailMsg) {

        MDSE_SER_NUM_RNGTMsgArray rsltMdseSerNumRngTMsgArray;

        rsltMdseSerNumRngTMsgArray = getMdseSerNumRngTMsgArray(glblCmpyCd, detailMsg.mdseCd.getValue());

        if (rsltMdseSerNumRngTMsgArray == null || rsltMdseSerNumRngTMsgArray.length() == 0) {
            // skip check process
        } else {
            for (int i = 0; i < rsltMdseSerNumRngTMsgArray.getValidCount(); i++) {
                // check for serial number length
                BigDecimal paramSerNum = new BigDecimal(detailMsg.serNum.getValue().length());
                BigDecimal rsltSerNum = rsltMdseSerNumRngTMsgArray.no(i).lgSerNum.getValue();

                if (paramSerNum.compareTo(rsltSerNum) == 0) {
                    detailMsg.serErrStsCd.clear();
                    break;
                } else {
                    detailMsg.serErrStsCd.setValue(NLZC302001Constant.LEN_ERR);
                }
            }

            for (int i = 0; i < rsltMdseSerNumRngTMsgArray.getValidCount(); i++) {
                // check for serial number range
                if ((rsltMdseSerNumRngTMsgArray.no(i).fromSerNum.getValue().compareTo(detailMsg.serNum.getValue()) > 0)
                        && (rsltMdseSerNumRngTMsgArray.no(i).thruSerNum.getValue().compareTo(detailMsg.serNum.getValue()) < 0)) {
                    detailMsg.serErrStsCd.clear();
                    break;
                } else {
                    detailMsg.serErrStsCd.setValue(NLZC302001Constant.RNG_ERR);
                }
            }
        }
    }

    /**
     * Find new latest record and update Serial Transaction Latest Flag because the latest record is deleted by manual
     * @param glblCmpyCd String
     * @param rsltSerTrxTMsg SER_TRXTMsg
     * @return void
     */
    private void updateSerTrxLtstFlgForUpdate(String glblCmpyCd, SER_TRXTMsg rsltSerTrxTMsg, NLZC302001_UpdateDetailListPMsg detailMsg) {

        if (NLZC302001Constant.MAN_DEL_ERR.compareTo(rsltSerTrxTMsg.serErrStsCd.getValue()) == 0
                && ZYPConstant.FLG_ON_Y.compareTo(rsltSerTrxTMsg.serTrxLtstFlg.getValue()) == 0) {

            SER_TRXTMsgArray rsltSerTrxTMsgArray;
    
            rsltSerTrxTMsgArray = getSerTrxTMsgArrayforltstFlg(glblCmpyCd, rsltSerTrxTMsg.serNum.getValue(), rsltSerTrxTMsg.mdseCd.getValue());
    
            if (rsltSerTrxTMsgArray == null || rsltSerTrxTMsgArray.length() == 0) {
                // skip check process
            } else {
                SER_TRXTMsg updateSerTrxTMsg = new SER_TRXTMsg();

                BigDecimal serTrxPk = rsltSerTrxTMsgArray.no(0).serTrxPk.getValue();

                // set parameter
                updateSerTrxTMsg.serTrxPk.setValue(serTrxPk);
                updateSerTrxTMsg.serTrxLtstFlg.setValue(ZYPConstant.FLG_ON_Y);

                // Execute Update.
                S21ApiTBLAccessor.update(updateSerTrxTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateSerTrxTMsg.getReturnCode())) {
                    detailMsg.xxMsgId.setValue(NLZC302001Constant.NLZM2174E);
                    S21InfoLogOutput.println(NLZC302001Constant.NLZM2174E);
                }
            }
        }
    }

    /**
     * Get MDSE_SER_NUM_RNG Records.
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSE_SER_NUM_RNGTMsgArray
     */
    private MDSE_SER_NUM_RNGTMsgArray getMdseSerNumRngTMsgArray(String glblCmpyCd, String mdseCd) {

        MDSE_SER_NUM_RNGTMsg srchSerTrxTMsg = new MDSE_SER_NUM_RNGTMsg();

        srchSerTrxTMsg.setSQLID("001");
        srchSerTrxTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        srchSerTrxTMsg.setConditionValue("mdseCd01", mdseCd);

        return (MDSE_SER_NUM_RNGTMsgArray) S21ApiTBLAccessor.findByCondition(srchSerTrxTMsg);
    }

    /**
     * Get SER_TRX Records.
     * @param glblCmpyCd String
     * @param serNum String
     * @param mdseCd String
     * @return SER_TRXTMsgArray
     */
    private SER_TRXTMsgArray getSerTrxTMsgArray(String glblCmpyCd, String serNum, String mdseCd) {
        SER_TRXTMsg srchSerTrxTMsg = new SER_TRXTMsg();
        srchSerTrxTMsg.setSQLID("002");
        srchSerTrxTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        srchSerTrxTMsg.setConditionValue("serNum01", serNum);
        srchSerTrxTMsg.setConditionValue("mdseCd01", mdseCd);
        srchSerTrxTMsg.setConditionValue("serTrxLtstFlg01", ZYPConstant.FLG_ON_Y);

        return (SER_TRXTMsgArray) S21ApiTBLAccessor.findByCondition(srchSerTrxTMsg);
    }

    /**
     * Get SER_TRX Record For Update.
     * @param glblCmpyCd String
     * @param serTrxPk BigDecimal
     * @return SER_TRXTMsg
     */
    private SER_TRXTMsg getSerTrxTMsgForUpdate(String glblCmpyCd, BigDecimal serTrxPk) {
        SER_TRXTMsg srchSerTrxTMsgForUpdate = new SER_TRXTMsg();
        srchSerTrxTMsgForUpdate.glblCmpyCd.setValue(glblCmpyCd);
        srchSerTrxTMsgForUpdate.serTrxPk.setValue(serTrxPk);

        return (SER_TRXTMsg) S21ApiTBLAccessor.findByKeyForUpdate(srchSerTrxTMsgForUpdate);
    }

    /**
     * Get SER_TRX Records to update latest record.
     * @param glblCmpyCd String
     * @param serNum String
     * @param mdseCd String
     * @return SER_TRXTMsgArray
     */
    private SER_TRXTMsgArray getSerTrxTMsgArrayforltstFlg(String glblCmpyCd, String serNum, String mdseCd) {

        SER_TRXTMsg srchSerTrxTMsg = new SER_TRXTMsg();

        srchSerTrxTMsg.setSQLID("003");
        srchSerTrxTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        srchSerTrxTMsg.setConditionValue("serNum01", serNum);
        srchSerTrxTMsg.setConditionValue("mdseCd01", mdseCd);
        srchSerTrxTMsg.setConditionValue("serErrStsCd01", NLZC302001Constant.MAN_DEL_ERR);

        return (SER_TRXTMsgArray) S21ApiTBLAccessor.findByCondition(srchSerTrxTMsg);
    }

    /**
     * Set Parameters For New SER_TRX Record.
     * @param glblCmpyCd String
     * @param detailMsg NLZC302001_UpdateDetailListPMsg
     * @param origMdseCd String
     * @param serTrxLtstFlg String
     * @return SER_TRXTMsg
     */
    private SER_TRXTMsg setSerTrxTMsgParamForCreate(String glblCmpyCd, NLZC302001_UpdateDetailListPMsg detailMsg, String origMdseCd, String serTrxLtstFlg) {

        SER_TRXTMsg cratSerTrxTMsg = new SER_TRXTMsg();

        cratSerTrxTMsg.glblCmpyCd.setValue(glblCmpyCd);
        cratSerTrxTMsg.serTrxPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SER_TRX_SQ));
        cratSerTrxTMsg.serNum.setValue(detailMsg.serNum.getValue());
        cratSerTrxTMsg.mdseCd.setValue(detailMsg.mdseCd.getValue());
        cratSerTrxTMsg.serTrxTs.setValue(detailMsg.serTrxTs.getValue());
        cratSerTrxTMsg.serTrxEventCd.setValue(detailMsg.serTrxEventCd.getValue());
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.serTrxSrcTpCd, detailMsg.serTrxSrcTpCd);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.serTrxSrcHdrNum, detailMsg.serTrxSrcHdrNum);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.serTrxSrcLineNum, detailMsg.serTrxSrcLineNum);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.serTrxSrcLineSubNum, detailMsg.serTrxSrcLineSubNum);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.serTrxRefNum, detailMsg.serTrxRefNum);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.fromLocCd, detailMsg.fromLocCd);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.toLocCd, detailMsg.toLocCd);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.origMdseCd, origMdseCd);
        cratSerTrxTMsg.manCratFlg.setValue(detailMsg.manCratFlg.getValue());
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.serTrxCmntTxt, detailMsg.serTrxCmntTxt);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.serTrxLtstFlg, serTrxLtstFlg);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.serErrStsCd, detailMsg.serErrStsCd);
        cratSerTrxTMsg.toStkStsCd.setValue(detailMsg.toStkStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.fromStkStsCd, detailMsg.fromStkStsCd);
        ZYPEZDItemValueSetter.setValue(cratSerTrxTMsg.locStsCd, detailMsg.locStsCd);

        return cratSerTrxTMsg;
    }

    /**
     * Message list -> msgMap
     * @param msgMap S21ApiMessageMap
     */
    private void setMessageList(S21ApiMessageMap msgMap) {

        NLZC302001PMsg param = (NLZC302001PMsg) msgMap.getPmsg();
        for (int i = 0; i < param.UpdateDetailList.getValidCount(); i++) {
            NLZC302001_UpdateDetailListPMsg detailMsg = param.UpdateDetailList.no(i);
            if (ZYPCommonFunc.hasValue(detailMsg.xxMsgId)) {
                msgMap.addXxMsgId(detailMsg.xxMsgId.getValue());
                String msgTxt = S21MessageFunc.clspGetMessage(detailMsg.xxMsgId.getValue()).substring(NLZC302001Constant.MSG_SUBSTRING_LENGTH);
                ZYPEZDItemValueSetter.setValue(detailMsg.xxDsMsgEntryTxt, msgTxt);
            }
        }
    }
}
