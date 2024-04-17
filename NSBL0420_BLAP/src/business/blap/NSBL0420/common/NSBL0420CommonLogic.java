/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0420.common;

import static business.blap.NSBL0420.constant.NSBL0420Constant.SVC_MOD_DTL_ITEM;
import static business.blap.NSBL0420.constant.NSBL0420Constant.SVC_MOD_DTL_PK_COL;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NSBM0079E;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NSBM0121E;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NSBM0005I;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NSBM0169W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

import parts.common.EZDMsg;
import business.blap.NSBL0420.NSBL0420CMsg;
import business.blap.NSBL0420.NSBL0420Query;
import business.blap.NSBL0420.NSBL0420SMsg;
import business.blap.NSBL0420.NSBL0420_ACMsg;
import business.blap.NSBL0420.NSBL0420_ACMsgArray;
import business.blap.NSBL0420.NSBL0420_ASMsg;
import business.db.SVC_MOD_DTL_ITEMTMsg;
import business.db.SVC_MOD_DTL_ITEMTMsgArray;
import business.db.SVC_MOD_STSTMsgArray;

/**
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11672
 *</pre>
 */
public class NSBL0420CommonLogic {

    /**
     * copy To ASMsg for Current Page Info
     * @param cMsg NSBL0420CMsg
     * @param sMsg NSBL0420SMsg
     */
    public static void copyCurrentPageToSMsg(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        // NSBL0420_ACMsg -> NSBL0420_ASMsg
        NSBL0420_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSBL0420_ACMsg acMsg = (NSBL0420_ACMsg) acMsgArray.no(i);
            int targetIdxNum = cMsg.xxPageShowFromNum.getValueInt() + acMsg.xxRowNum_A.getValue().intValue() - 2;
            NSBL0420_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.xxChkBox_A , acMsg.xxChkBox_A);
            setValue(asMsg.svcModQty_A , acMsg.svcModQty_A);
        }
    }

    /**
     * copyFromSMsgOntoCmsg
     * @param cMsg NSBL0420CMsg
     * @param sMsg NSBL0420SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.A.getValidCount()), sMsg.A.getValidCount());
    }

    /**
     * setPageNum
     * @param cMsg NSBL0420CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NSBL0420CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * <a>check Call
     * @param cMsg NSBL0420CMsg
     * @param sMsg NSBL0420SMsg
     * @return boolean
     */
    public static boolean checkCall(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        boolean rtnVal = true;
        SVC_MOD_STSTMsgArray result = NSBL0420Query.getSVC_MOD_STS(cMsg.glblCmpyCd.getValue(), cMsg.svcModDtlPk.getValue());
        if (result.getValidCount() != 0) {
            // mod start 2016/07/13 CSA Defect#11672
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxWrnSkipFlg.getValue())) {
                cMsg.setMessageInfo(NSBM0169W);
                setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
                rtnVal = false;
            }
            // mod end 2016/07/13 CSA Defect#11672
        }
        return rtnVal;
    }

    /**
     * Insert Branch Code
     * @param cMsg NSBL0420CMsg
     * @param sMsg NSBL0420SMsg
     */
    public static void updateDetailItem(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        boolean checkWarn = true;
        List<SVC_MOD_DTL_ITEMTMsg> deleteTMsgList = new ArrayList<SVC_MOD_DTL_ITEMTMsg>();
        SVC_MOD_DTL_ITEMTMsgArray result =  NSBL0420Query.getSVC_MOD_DTL_ITEM(cMsg.glblCmpyCd.getValue(), cMsg.svcModDtlPk.getValue());
        if (result.getValidCount() > 0) {
            for (int i = 0; i < result.getValidCount(); i++) {
                deleteTMsgList.add(result.no(i));
            }
            int delCnt = S21FastTBLAccessor.removePhysical(deleteTMsgList.toArray(new SVC_MOD_DTL_ITEMTMsg[deleteTMsgList.size()]));
            if (delCnt != deleteTMsgList.size()) {
                cMsg.setMessageInfo(NSBM0079E, new String[] {SVC_MOD_DTL_ITEM, SVC_MOD_DTL_PK_COL, cMsg.svcModDtlPk.getValue().toString()});
                checkWarn = false;
            }
            if (checkWarn == false) {
                return;
            }
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            SVC_MOD_DTL_ITEMTMsg instMsg = new SVC_MOD_DTL_ITEMTMsg();
            setValue(instMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(instMsg.svcModItemPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MOD_DTL_ITEM_SQ));
            setValue(instMsg.svcModDtlPk, cMsg.svcModDtlPk);
            setValue(instMsg.mdseCd,  sMsg.A.no(i).mdseCd_A);
            setValue(instMsg.svcModQty, sMsg.A.no(i).svcModQty_A);
            S21FastTBLAccessor.insert(instMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(instMsg.getReturnCode())) {
              cMsg.setMessageInfo(NSBM0121E, new String[]{SVC_MOD_DTL_ITEM, sMsg.A.no(i).mdseCd_A.getValue()});
              checkWarn = false;
            }
        }
        if (checkWarn == false) {
            return;
        }
        cMsg.setMessageInfo(NSBM0005I);
    }
}
