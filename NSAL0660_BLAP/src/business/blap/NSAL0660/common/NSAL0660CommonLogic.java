/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0660.common;

import static business.blap.NSAL0660.constant.NSAL0660Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0660.NSAL0660CMsg;
import business.blap.NSAL0660.NSAL0660SMsg;
import business.blap.NSAL0660.NSAL0660_ACMsg;
import business.blap.NSAL0660.NSAL0660_ACMsgArray;
import business.blap.NSAL0660.NSAL0660_ASMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * Add general Notes
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/02   Hitachi         K.Kasai         Create          N/A
 * 2015/12/04   Hitachi         T.Tsuchida      Update          QC#1541
 * 2016/12/06   Hitachi         T.Mizuki        Update          QC#4210
 *</pre>
 */
public class NSAL0660CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0660CMsg
     */
    public static void createPullDown(NSAL0660CMsg cMsg) {
        createSvcMemoRsnPullDown(cMsg);
    }

    private static void createSvcMemoRsnPullDown(NSAL0660CMsg cMsg) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(cMsg.glblCmpyCd.getValue(), SVC_MEMO_TP.CONTRACT_NOTE);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // mod start 2016/12/06 CSA QC#4210
    /**
     * Insert Service Memo
     * @param cMsg NSAL0660CMsg
     * @param sMsg NSAL0660SMsg
     */
    public static void insertSvcMemo(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {
        boolean errFlg = false;
        if (!checkSelect(sMsg)) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
                setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
                setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
                setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.CONTRACT_NOTE);
                setValue(tmsg.svcCmntTxt, cMsg.svcCmntTxt_H1);
                setValue(tmsg.dsContrPk, sMsg.A.no(i).dsContrPk_A1);
                setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
                setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
                setValue(tmsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
                S21FastTBLAccessor.insert(tmsg);

                if (S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxGenlFldAreaTxt_A1, RTRN_MSG_SUCCESS);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxGenlFldAreaTxt_A1, RTRN_MSG_FAILED);
                    errFlg = true;
                }
            }
        }
        if (errFlg) {
            cMsg.setMessageInfo(NSAM0394W, new String[] {});
        } else {
            cMsg.setMessageInfo(NSAM0200I, new String[] {});
        }
    }

    /**
     * check Select
     * @param sMsg NSAL0660SMsg
     * @return boolean
     */
    private static boolean checkSelect(NSAL0660SMsg sMsg) {
        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            rtnVal = false;
        }
        return rtnVal;
    }
    // mod end 2016/12/06 CSA QC#4210
    // add start 2016/12/06 CSA QC#4210
    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0660CMsg
     * @param sMsg NSAL0660SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0660CMsg cMsg, NSAL0660SMsg sMsg) {

        // NSAL0660_ACMsg -> NSAL0660_BSMsg
        NSAL0660_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0660_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A1.getValueInt();

            NSAL0660_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.xxChkBox_A1, acMsg.xxChkBox_A1);
            setValue(asMsg.xxGenlFldAreaTxt_A1, acMsg.xxGenlFldAreaTxt_A1);
        }
    }
    // add end 2016/12/06 CSA QC#4210
}
