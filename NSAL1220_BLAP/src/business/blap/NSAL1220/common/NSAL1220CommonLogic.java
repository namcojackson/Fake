/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1220.common;

import static business.blap.NSAL1220.constant.NSAL1220Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL1220.NSAL1220CMsg;
import business.blap.NSAL1220.NSAL1220Query;
import business.db.COA_BRTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;
import business.parts.NSZC080001PMsg;
import com.canon.cusa.s21.api.NSZ.NSZC080001.NSZC080001;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Contract Branch Revenue Distribution
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Hitachi         T.Tsuchida      Create          N/A
 * 2016/01/21   Hitachi         A.Kohinata      Update          CSA QC#3352
 * 2016/10/06   Hitachi         K.Yamada        Update          CSA QC#13815
 *</pre>
 */
public class NSAL1220CommonLogic {

    /**
     * <a>check Master
     * @param cMsg NSAL1220CMsg
     * @return boolean
     */
    public static boolean checkMaster(NSAL1220CMsg cMsg) {
        boolean rtnVal = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            COA_BRTMsg result = NSAL1220Query.getCoaBrCd(cMsg.glblCmpyCd.getValue(), cMsg.A.no(i).coaBrCd_A.getValue());
            if (result == null) {
                cMsg.A.no(i).coaBrCd_A.setErrorInfo(1, NSZM0628E, new String[] {COA_BR_CD });
                rtnVal = false;
            }
        }
        return rtnVal;

    }

    /**
     * findContrInfo
     * @param cMsg NSAL1220CMsg
     */
    public static void findContrInfo(NSAL1220CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL1220Query.getInstance().getContrInfo(cMsg);

        if (ssmResult.getQueryResultCount() == 0) {
            setValue(cMsg.A.no(0).coaBrCd_A, cMsg.coaBrCd);
            setValue(cMsg.A.no(0).prcAllocRate_A, BigDecimal.valueOf(TOTAL_RATE));
            cMsg.A.setValidCount(1);
            return;
        }

        if (ssmResult.getQueryResultCount() > cMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
            cMsg.A.setValidCount(cMsg.A.length());
        }
    }

    /**
     * calculation
     * @param cMsg NSAL1220CMsg
     */
    public static void calc(NSAL1220CMsg cMsg) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (hasValue(cMsg.A.no(i).prcAllocRate_A.getValue())) {
                total = total.add(cMsg.A.no(i).prcAllocRate_A.getValue());
            } else {
                total = total.add(BigDecimal.ZERO);
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prcAllocRate_T, total);
    }

    /**
     * Insert Branch Code
     * @param cMsg NSAL1220CMsg
     */
    public static void insertBranch(NSAL1220CMsg cMsg) {

        DS_CONTR_BR_ALLOCTMsg inMsg = new DS_CONTR_BR_ALLOCTMsg();
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        if (hasValue(cMsg.dsContrDtlPk) && hasValue(cMsg.svcInvChrgTpCd)) {
            inMsg.setSQLID("001");
            inMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
            inMsg.setConditionValue("svcInvChrgTpCd01", cMsg.svcInvChrgTpCd.getValue());
        } else if (hasValue(cMsg.dsContrDtlPk)) {
            inMsg.setSQLID("002");
            inMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
        } else {
            inMsg.setSQLID("003");
        }

        DS_CONTR_BR_ALLOCTMsgArray tMsgArray = (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        for (int i = 0; i < tMsgArray.length(); i++) {
            EZDTBLAccessor.remove(tMsgArray.no(i));
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgArray.no(i).getReturnCode())) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {COA_BR_CD });
                return;
            }
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            inMsg = new DS_CONTR_BR_ALLOCTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrBrAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BR_ALLOC_SQ));
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrPk, cMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrDtlPk, cMsg.dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(inMsg.svcInvChrgTpCd, cMsg.svcInvChrgTpCd);
            ZYPEZDItemValueSetter.setValue(inMsg.coaBrCd, cMsg.A.no(i).coaBrCd_A);
            ZYPEZDItemValueSetter.setValue(inMsg.prcAllocRate, cMsg.A.no(i).prcAllocRate_A);
            EZDTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0032E, new String[] {COA_BR_CD });
                return;
            }
        }

        // del start 2016/10/06 CSA Defect#13815
        //// mod start 2016/01/21 CSA QC#3352
        //if (isExistsDsContrSegAlloc(cMsg)) {
        //    NSZC080001PMsg contrRevDisApiMsg = callContrRevDisAPI(cMsg);
        //    if (contrRevDisApiMsg.xxMsgIdList.getValidCount() != 0) {
        //        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(contrRevDisApiMsg);
        //        cMsg.setMessageInfo(msgList.get(0).getXxMsgid(), msgList.get(0).getXxMsgPrmArray());
        //        return;
        //    }
        //}
        //// mod end 2016/01/21 CSA QC#3352
        // del end 2016/10/06 CSA Defect#13815

        cMsg.setMessageInfo(NSAM0200I);
    }

    // add start 2016/01/21 CSA QC#3352
    /**
     * exists DsContrSegAlloc
     * @param cMsg NSAL1220CMsg
     * @return boolean
     */
    private static boolean isExistsDsContrSegAlloc(NSAL1220CMsg cMsg) {
        DS_CONTR_SEG_ALLOCTMsg inMsg = new DS_CONTR_SEG_ALLOCTMsg();
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        if (hasValue(cMsg.dsContrDtlPk) && hasValue(cMsg.svcInvChrgTpCd)) {
            inMsg.setSQLID("001");
            inMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
            inMsg.setConditionValue("svcInvChrgTpCd01", cMsg.svcInvChrgTpCd.getValue());
        } else if (hasValue(cMsg.dsContrDtlPk)) {
            inMsg.setSQLID("002");
            inMsg.setConditionValue("dsContrDtlPk01", cMsg.dsContrDtlPk.getValue());
        } else {
            inMsg.setSQLID("003");
        }

        DS_CONTR_SEG_ALLOCTMsgArray tMsgArray = (DS_CONTR_SEG_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray.getValidCount() == 0) {
            return false;
        }
        return true;
    }
    // add end 2016/01/21 CSA QC#3352

    /**
     * call API (NSZC0800)
     * @param cMsg NSAL1220CMsg
     * @return NSZC080001PMsg
     */
    private static NSZC080001PMsg callContrRevDisAPI(NSAL1220CMsg cMsg) {
        NSZC080001PMsg apiPMsg = null;
        apiPMsg = new NSZC080001PMsg();
        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsContrPk, cMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsContrDtlPk, cMsg.dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(apiPMsg.svcInvChrgTpCd, cMsg.svcInvChrgTpCd);
        NSZC080001 api = new NSZC080001();
        api.execute(apiPMsg, ONBATCH_TYPE.ONLINE);
        return apiPMsg;
    }
}
