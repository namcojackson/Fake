/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0390.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL0390.constant.NSAL0390Constant.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import business.blap.NSAL0390.NSAL0390CMsg;
import business.blap.NSAL0390.NSAL0390Query;
import business.blap.NSAL0390.NSAL0390SMsg;
import business.blap.NSAL0390.NSAL0390_ACMsg;
import business.blap.NSAL0390.NSAL0390_ACMsgArray;
import business.blap.NSAL0390.NSAL0390_ASMsg;
import business.blap.NSAL0390.NSAL0390_ASMsgArray;
import business.blap.NSAL0390.NSAL0390_BSMsg;
import business.blap.NSAL0390.NSAL0390_BSMsgArray;
import business.db.DS_CR_CARDTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/23   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC#4212
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#11243
 *</pre>
 */
public class NSAL0390CommonLogic {

    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    public static void copyCurrentPageToBSMsg(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        // NSAL0390_ACMsg -> NSAL0390_BSMsg
        NSAL0390_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0390_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxNum_A0.getValueInt();

            NSAL0390_BSMsg bsMsg = sMsg.B.no(targetIdxNum);
            EZDMsg.copy(acMsg, "A0", bsMsg, "B0");
            EZDMsg.copy(acMsg, "A1", bsMsg, "B1");
        }
    }

    /**
     * copy From ASMsg To ACMsg
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     * @param pageFrom int
     */
    public static void copyFromASMsgToACMsg(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg, int pageFrom) {

        // clear NSAL0390_ACMsgArray
        NSAL0390_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        // NSAL0390_ASMsg -> NSAL0390_ACMsg
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }

        acMsgArray.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * copy From BSMsg To ASMsg For Display
     * @param sMsg NSAL0390SMsg
     */
    public static void copyFromBSMsgToASMsgForDisplay(NSAL0390SMsg sMsg) {

        // clear NSAL0390_ASMsgArray
        NSAL0390_ASMsgArray asMsgArray = sMsg.A;
        ZYPTableUtil.clear(asMsgArray);

        // NSAL0390_BSMsg -> NSAL0390_ASMsg
        NSAL0390_BSMsgArray bsMsgArray = sMsg.B;
        int validCnt = 0;
        String smryLineFlgLvl1 = null;
        String smryLineFlgLvl2 = null;
        for (int i = 0; i < bsMsgArray.getValidCount(); i++) {
            NSAL0390_BSMsg bsMsg = bsMsgArray.no(i);

            if (MACH_LVL_NUM_1.equals(bsMsg.dsContrMachLvlNum_B0.getValue())) {
                NSAL0390_ASMsg asMsg = asMsgArray.no(validCnt);
                EZDMsg.copy(bsMsg, "B0", asMsg, "A0");
                EZDMsg.copy(bsMsg, "B1", asMsg, "A1");
                validCnt++;
                smryLineFlgLvl1 = bsMsg.xxSmryLineFlg_B0.getValue();
            } else if (MACH_LVL_NUM_2.equals(bsMsg.dsContrMachLvlNum_B0.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(smryLineFlgLvl1)) {
                    smryLineFlgLvl2 = ZYPConstant.FLG_ON_Y;
                    continue;
                } else {
                    NSAL0390_ASMsg asMsg = asMsgArray.no(validCnt);
                    EZDMsg.copy(bsMsg, "B0", asMsg, "A0");
                    EZDMsg.copy(bsMsg, "B1", asMsg, "A1");
                    validCnt++;
                    smryLineFlgLvl2 = bsMsg.xxSmryLineFlg_B0.getValue();
                }
            } else if (MACH_LVL_NUM_3.equals(bsMsg.dsContrMachLvlNum_B0.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(smryLineFlgLvl2)) {
                    continue;
                } else {
                    NSAL0390_ASMsg asMsg = asMsgArray.no(validCnt);
                    EZDMsg.copy(bsMsg, "B0", asMsg, "A0");
                    EZDMsg.copy(bsMsg, "B1", asMsg, "A1");
                    validCnt++;
                }
            }
        }
        asMsgArray.setValidCount(validCnt);
    }

    /**
     * copy From BSMsg To ASMsg For Paging
     * @param sMsg NSAL0390SMsg
     */
    public static void copyFromBSMsgToASMsgForPaging(NSAL0390SMsg sMsg) {

        // NSAL0390_BSMsg -> NSAL0390_ASMsg
        NSAL0390_ASMsgArray asMsgArray = sMsg.A;
        NSAL0390_BSMsgArray bsMsgArray = sMsg.B;

        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NSAL0390_ASMsg asMsg = asMsgArray.no(i);
            int idxNum = asMsg.xxNum_A0.getValueInt();

            for (int j = 0; j < bsMsgArray.getValidCount(); j++) {
                NSAL0390_BSMsg bsMsg = bsMsgArray.no(j);
                int targetIdxNum = bsMsg.xxNum_B0.getValueInt();

                if (targetIdxNum == idxNum) {
                    EZDMsg.copy(bsMsg, "B0", asMsg, "A0");
                    EZDMsg.copy(bsMsg, "B1", asMsg, "A1");
                    break;
                }
            }
        }
    }

    /**
     * Service Memo Reason Pull down
     * @param cMsg NSAL0390CMsg
     */
    public static void setServiceMemoReasonInfo(NSAL0390CMsg cMsg) {

        SVC_MEMO_RSNTMsgArray smrTMsgArray = NSAL0390Query.getInstance().getServiceMemoReasonInfo(cMsg);
        if (smrTMsgArray.getValidCount() > 0) {
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
            ZYPPulldownValueSetter.set(smrTMsgArray, tMsgKeys, cMsg.svcMemoRsnCd_L, cMsg.svcMemoRsnNm_L);
        }
    }

    /**
     * Clear Screen
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     */
    public static void clearScreen(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        cMsg.serNum_HF.clear();
        cMsg.serNum_HT.clear();
        cMsg.svcMachMstrPk_HF.clear();
        cMsg.svcMachMstrPk_HT.clear();
        cMsg.crCardCustRefNum_H.clear();
        cMsg.crCardExprYrMth_H.clear();
        cMsg.svcMemoRsnCd_H.clear();
        cMsg.svcCmntTxt_H.clear();
        cMsg.xxChkBox_H0.clear();
        cMsg.xxChkBox_H1.clear();

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
    }

    /**
     * select ItemValue
     * @param dsContrCrCardPoPk BigDecimal
     * @param lowLvlItem String
     * @param highLvlItem String
     * @return String
     */
    public static String selectItemValue(BigDecimal dsContrCrCardPoPk, String lowLvlItem, String highLvlItem) {

        // mod start 2016/08/29 CSA Defect#11243
        if (hasValue(dsContrCrCardPoPk)) {
            return lowLvlItem;
        }
        if (hasValue(lowLvlItem)) {
            return lowLvlItem;
        }
        return highLvlItem;
        // mod end 2016/08/29 CSA Defect#11243
    }

    /**
     * check ContrSts Active
     * @param dsContrCtrlStsCd String
     * @return String
     */
    public static String checkContrStsActive(String dsContrCtrlStsCd) {
        String[] notActiveSts = new String[] {DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.TERMINATED_HOLD, DS_CONTR_CTRL_STS.EXPIRED_HOLD };
        if (Arrays.asList(notActiveSts).contains(dsContrCtrlStsCd)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return ZYPConstant.FLG_ON_Y;
    }

    /**
     * check Select Line
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     * @return boolean
     */
    public static boolean checkSelectLine(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg) {

        boolean rtnVal = true;
        List<Integer> selectedRowsContract = ZYPTableUtil.getSelectedRows(sMsg.B, "xxChkBox_B0", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> selectedRowsSerial = ZYPTableUtil.getSelectedRows(sMsg.B, "xxChkBox_B1", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRowsContract.isEmpty() && selectedRowsSerial.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            rtnVal = false;
        }
        return rtnVal;
    }

    // add start 2016/08/29 CSA Defect#11243
    /**
     * get crCardTrxTp
     * @param dsContrMachLvlNum String
     * @param dtlNm String
     * @return String
     */
    public static String getCrCardTrxTp(String dsContrMachLvlNum, String dtlNm) {
        String crCardTrxTp = null;
        if (MACH_LVL_NUM_1.equals(dsContrMachLvlNum)) {
            crCardTrxTp = CR_CARD_TRX_TP.CONTRACT_HEADER;
        } else if (MACH_LVL_NUM_2.equals(dsContrMachLvlNum)) {
            crCardTrxTp = CR_CARD_TRX_TP.CONTRACT_DETAIL;
        } else if (MACH_LVL_NUM_3.equals(dsContrMachLvlNum) && TRD_TP_CONTR_DTL.equals(dtlNm)) {
            crCardTrxTp = CR_CARD_TRX_TP.CONTRACT_BASE;
        } else {
            crCardTrxTp = CR_CARD_TRX_TP.CPONTRACT_METER;
        }
        return crCardTrxTp;
    }

    /**
     * check newRefNum
     * @param cMsg NSAL0390CMsg
     * @param bsMsg NSAL0390_BSMsg
     * @return boolean
     */
    public static boolean checkNewRefNum(NSAL0390CMsg cMsg, NSAL0390_BSMsg bsMsg) {
        if (!hasValue(bsMsg.crCardCustRefNum_B1)) {
            return true;
        }
        if (!hasValue(bsMsg.sellToCustCd_B0)) {
            bsMsg.crCardCustRefNum_B1.setErrorInfo(1, NSAM0045E, new String[] {"Entered Reference#" });
            return false;
        }
        DS_CR_CARDTMsg tMsg = NSAL0390Query.getInstance().getDsCrCard(cMsg, bsMsg);
        if (tMsg == null) {
            bsMsg.crCardCustRefNum_B1.setErrorInfo(1, NSAM0045E, new String[] {"Entered Reference#" });
            return false;
        }
        setValue(bsMsg.crCardExprYrMth_B1, tMsg.crCardExprYrMth);
        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.crCardValidFlg.getValue())) {
            bsMsg.crCardCustRefNum_B1.setErrorInfo(1, NSAM0354E, new String[] {"Entered Reference#" });
            return false;
        }
        return true;
    }

    /**
     * copy From BSMsg To ASMsg For Error
     * @param cMsg NSAL0390CMsg
     * @param sMsg NSAL0390SMsg
     * @param errIndex int
     */
    public static void copyFromBSMsgToASMsgForError(NSAL0390CMsg cMsg, NSAL0390SMsg sMsg, int errIndex) {

        NSAL0390_BSMsg bsMsg = sMsg.B.no(errIndex);
        String dsContrMachLvlNum = bsMsg.dsContrMachLvlNum_B0.getValue();
        BigDecimal dsContrPk = bsMsg.dsContrPk_B0.getValue();
        BigDecimal dsContrDtlPk = bsMsg.dsContrDtlPk_B0.getValue();

        if (MACH_LVL_NUM_2.equals(dsContrMachLvlNum) || MACH_LVL_NUM_3.equals(dsContrMachLvlNum)) {
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                bsMsg = sMsg.B.no(i);
                if (MACH_LVL_NUM_1.equals(bsMsg.dsContrMachLvlNum_B0.getValue()) && dsContrPk.compareTo(bsMsg.dsContrPk_B0.getValue()) == 0) {
                    setValue(bsMsg.xxSmryLineFlg_B0, ZYPConstant.FLG_OFF_N);
                    break;
                }
            }
        }
        if (MACH_LVL_NUM_3.equals(dsContrMachLvlNum)) {
            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                bsMsg = sMsg.B.no(i);
                if (MACH_LVL_NUM_2.equals(bsMsg.dsContrMachLvlNum_B0.getValue()) && dsContrPk.compareTo(bsMsg.dsContrPk_B0.getValue()) == 0 && dsContrDtlPk.compareTo(bsMsg.dsContrDtlPk_B0.getValue()) == 0) {
                    setValue(bsMsg.xxSmryLineFlg_B0, ZYPConstant.FLG_OFF_N);
                    break;
                }
            }
        }

        copyFromBSMsgToASMsgForDisplay(sMsg);

        BigDecimal len = BigDecimal.valueOf(cMsg.A.length());
        BigDecimal fromNum = BigDecimal.ONE;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0390_ASMsg asMsg = sMsg.A.no(i);
            int idxNum = asMsg.xxNum_A0.getValueInt();
            if (idxNum == errIndex) {
                fromNum = BigDecimal.valueOf(i).divide(len, 0, BigDecimal.ROUND_DOWN).multiply(len).add(BigDecimal.ONE);
                break;
            }
        }
        cMsg.xxPageShowFromNum.setValue(fromNum);
    }
    // add end 2016/08/29 CSA Defect#11243
}
