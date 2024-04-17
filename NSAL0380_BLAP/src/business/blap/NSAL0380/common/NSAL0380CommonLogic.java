/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0380.common;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import parts.common.EZDMsg;
import business.blap.NSAL0380.NSAL0380CMsg;
import business.blap.NSAL0380.NSAL0380SMsg;
import business.blap.NSAL0380.NSAL0380_ACMsg;
import business.blap.NSAL0380.NSAL0380_ACMsgArray;
import business.blap.NSAL0380.NSAL0380_ASMsg;
import business.blap.NSAL0380.NSAL0380_ASMsgArray;
import business.blap.NSAL0380.NSAL0380_BSMsg;
import business.blap.NSAL0380.NSAL0380_BSMsgArray;
import business.blap.NSAL0380.constant.NSAL0380Constant;
import business.db.CONTR_AUTO_RNW_TPTMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/25   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/15   Hitachi         O.Okuma         Update          QC3029
 * 2016/03/07   Hitachi         A.Kohinata      Update          QC5082
 * 2016/07/15   Hitachi         A.Kohinata      Update          QC#8608
 * 2019/06/26   Hitachi         A.Kohinata      Update          QC#50931
 *</pre>
 */
public class NSAL0380CommonLogic {

    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        // NSAL0380_ACMsg -> NSAL0380_BSMsg
        NSAL0380_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0380_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxNum_A.getValueInt();

            NSAL0380_BSMsg bsMsg = sMsg.B.no(targetIdxNum);

            EZDMsg.copy(acMsg, "A", bsMsg, "B");
            EZDMsg.copy(acMsg, "A1", bsMsg, "B1");
            EZDMsg.copy(acMsg, "A2", bsMsg, "B2");
        }
    }

    /**
     * copy To ASMsg for BSMsg Info
     * @param sMsg NSAL0380SMsg
     */
    public static void copyASMsgToBSMsg(NSAL0380SMsg sMsg) {

        // NSAL0380_BSMsg -> NSAL0380_ASMsg
        NSAL0380_ASMsgArray asMsgArray = sMsg.A;
        NSAL0380_BSMsgArray bsMsgArray = sMsg.B;

        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NSAL0380_ASMsg asMsg = asMsgArray.no(i);
            // mod start 2019/06/26 QC#50931
            //NSAL0380_BSMsg bsMsg = bsMsgArray.no(i);
            int targetIdxNum = asMsg.xxNum_A.getValueInt();
            NSAL0380_BSMsg bsMsg = sMsg.B.no(targetIdxNum);
            // mod end 2019/06/26 QC#50931

            EZDMsg.copy(bsMsg, "B", asMsg, "A");
            EZDMsg.copy(bsMsg, "B1", asMsg, "A1");
            EZDMsg.copy(bsMsg, "B2", asMsg, "A2");
        }
    }

    /**
     * check Selected Row
     * @param selectedRowsSerial List<Integer>
     * @param selectedRowsBaseUseage List<Integer>
     * @return boolean
     */
    public static boolean checkSelect(List<Integer> selectedRowsSerial, List<Integer> selectedRowsBaseUseage) {

        if (selectedRowsSerial.isEmpty() && selectedRowsBaseUseage.isEmpty()) {
            return false;
        }
        return true;
    }

    // add end 2016/07/15 CSA Defect#8608
    /**
     * getContrAutoRnwTp
     * @param glblCmpyCd String
     * @param contrAutoRnwTpCd String
     * @return CONTR_AUTO_RNW_TPTMsg
     */
    public static CONTR_AUTO_RNW_TPTMsg getContrAutoRnwTp(String glblCmpyCd, String contrAutoRnwTpCd) {
        if (!ZYPCommonFunc.hasValue(contrAutoRnwTpCd)) {
            return null;
        }
        return (CONTR_AUTO_RNW_TPTMsg) ZYPCodeDataUtil.findByCode(CONTR_AUTO_RNW_TP.class, glblCmpyCd, contrAutoRnwTpCd);
    }
    // add end 2016/07/15 CSA Defect#8608

    // add start 2019/06/26 QC#50931 (move from NSAL0380BL02)
    /**
     * copy To ASMsg for Expansion And Contraction
     * @param sMsg NSAL0380SMsg
     */
    public static void copyToASMsgForExpansionAndContraction(NSAL0380SMsg sMsg) {

        // clear NSAL0380_ASMsgArray
        NSAL0380_ASMsgArray asMsgArray = sMsg.A;
        ZYPTableUtil.clear(asMsgArray);

        // NSAL0380_BSMsg -> NSAL0380_ASMsg
        NSAL0380_BSMsgArray bsMsgArray = sMsg.B;
        int validCnt = 0;
        int asMsgMaxlength = asMsgArray.length() - 1;
        boolean dispContr = true;
        boolean dispMach = true;
        BigDecimal dsContrPk = null;
        BigDecimal dsContrDtlPk = null;
        BigDecimal preDsContrPk = null;
        BigDecimal preDsContrDtlPk = null;

        for (int i = 0; i < bsMsgArray.getValidCount(); i++) {
            NSAL0380_BSMsg bsMsg = bsMsgArray.no(i);

            dsContrPk = bsMsg.dsContrPk_B.getValue();
            dsContrDtlPk = bsMsg.dsContrDtlPk_B.getValue();

            if (ZYPCommonFunc.hasValue(dsContrPk) && ZYPCommonFunc.hasValue(preDsContrPk) && dsContrPk.compareTo(preDsContrPk) == 0) {
                if (dispContr) {
                    if (!ZYPCommonFunc.hasValue(dsContrDtlPk) || !ZYPCommonFunc.hasValue(preDsContrDtlPk) || dsContrDtlPk.compareTo(preDsContrDtlPk) != 0) {
                        if (ZYPConstant.FLG_OFF_N.equals(bsMsg.xxSmryLineFlg_B.getValue())) {
                            dispMach = false;
                        } else {
                            dispMach = true;
                        }
                    }
                }
            } else {
                if (ZYPConstant.FLG_OFF_N.equals(bsMsg.xxSmryLineFlg_B.getValue())) {
                    dispContr = false;
                    dispMach = false;
                } else {
                    dispContr = true;
                    dispMach = true;
                }
            }

            if (ZYPCommonFunc.hasValue(bsMsg.dsContrNum_B.getValue())) {
                NSAL0380_ASMsg asMsg = asMsgArray.no(validCnt);
                setToSMsgForExpansionAndContraction(asMsg, bsMsg);
                validCnt++;
            } else if (!dispContr) {
                if (ZYPCommonFunc.hasValue(bsMsg.serNum_B) || (DS_CONTR_DTL_TP.FLEET.equals(bsMsg.dsContrDtlTpCd_B.getValue()) && NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()))) {
                    ZYPEZDItemValueSetter.setValue(bsMsg.xxSmryLineFlg_B, ZYPConstant.FLG_ON_Y);
                }
            } else if (ZYPCommonFunc.hasValue(bsMsg.serNum_B) || (DS_CONTR_DTL_TP.FLEET.equals(bsMsg.dsContrDtlTpCd_B.getValue()) && NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()))) {
                NSAL0380_ASMsg asMsg = asMsgArray.no(validCnt);
                setToSMsgForExpansionAndContraction(asMsg, bsMsg);
                validCnt++;
                // START 2017/06/08 Y.Osawa [QC#18470, ADD]
            } else if (!ZYPCommonFunc.hasValue(bsMsg.serNum_B) && (!(DS_CONTR_DTL_TP.FLEET.equals(bsMsg.dsContrDtlTpCd_B.getValue())) && NSAL0380Constant.MACH_LVL_NUM_2.equals(bsMsg.xxDplyByCtrlAncrLvlCd_B.getValue()))) {
                NSAL0380_ASMsg asMsg = asMsgArray.no(validCnt);
                setToSMsgForExpansionAndContraction(asMsg, bsMsg);
                validCnt++;
                // END   2017/06/08 Y.Osawa [QC#18470, ADD]
            } else if (dispMach) {
                NSAL0380_ASMsg asMsg = asMsgArray.no(validCnt);
                setToSMsgForExpansionAndContraction(asMsg, bsMsg);
                validCnt++;
            }

            if (validCnt == asMsgMaxlength) {
                break;
            }

            preDsContrPk = dsContrPk;
            preDsContrDtlPk = dsContrDtlPk;
        }

        asMsgArray.setValidCount(validCnt);
    }

    /**
     * set To ASMsg for Expansion And Contraction
     * @param asMsg NSAL0380_ASMsg
     * @param bsMsg NSAL0380_BSMsg
     */
    private static void setToSMsgForExpansionAndContraction(NSAL0380_ASMsg asMsg, NSAL0380_BSMsg bsMsg) {

        EZDMsg.copy(bsMsg, "B", asMsg, "A");
        EZDMsg.copy(bsMsg, "B1", asMsg, "A1");
        EZDMsg.copy(bsMsg, "B2", asMsg, "A2");

    }

    /**
     * copy From ASMsg To ACMsg
     * @param cMsg NSAL0380CMsg
     * @param sMsg NSAL0380SMsg
     */
    public static void copyToACMsgForDisplay(NSAL0380CMsg cMsg, NSAL0380SMsg sMsg) {

        // clear NSAL0380_ACMsgArray
        NSAL0380_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        // NSAL0380_ASMsg -> NSAL0380_ACMsg
        NSAL0380_ASMsgArray asMsgArray = sMsg.A;
        int sMsgLine = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int validCnt = 0;

        for (int cnt = 0; cnt < acMsgArray.length(); cnt++) {
            if (sMsgLine >= asMsgArray.getValidCount()) {
                break;
            }

            EZDMsg.copy(asMsgArray.no(sMsgLine), null, acMsgArray.no(cnt), null);
            sMsgLine++;
            validCnt++;
        }
        acMsgArray.setValidCount(validCnt);
    }
    // add end 2019/06/26 QC#50931 (move from NSAL0380BL02)
}
