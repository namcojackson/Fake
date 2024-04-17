/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630.common;

import static business.servlet.NWAL1630.constant.NWAL1630Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.BTN_PRICE;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.ZZM9000E;
import static business.servlet.NWAL1630.constant.NWAL1630Constant.NSAL1240_MODE_02;
import parts.common.EZDBMsgArray;

import business.servlet.NWAL1630.NWAL1630BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1630CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Yokoi         Create          N/A
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#18859(Sol#154)
 * 2019/01/22   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL1630CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1630BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL1630BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
        scrnMsg.csmpPrcListCd.setInputProtected(true);
        scrnMsg.csmpContrNum.setInputProtected(true); // 2016/08/30 S21_NA#9806 Add
        scrnMsg.dlrRefNum.setInputProtected(true); // 2016/08/30 S21_NA#9806 Add
    }

    /**
     * Protect Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1630BMsg
     */
    public static void protectCmnBtnProp(S21CommonHandler handler, NWAL1630BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 0, null);
        handler.setButtonEnabled(BTN_PRICE, false);
    }

    /**
     * chkInputParam
     * @param scrnMsg NWAL1630BMsg
     */
    public static void chkInputParam(NWAL1630BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.sellToCustCd.getNameForMessage() });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.dsOrdCatgCd.getNameForMessage() });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.dsOrdTpCd.getNameForMessage() });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.lineBizTpCd.getNameForMessage() });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.ordDt)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.ordDt.getNameForMessage() });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.dsOrdPosnNum.getNameForMessage() });
            return;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsCpoLineNum)) {
            scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.dsCpoLineNum.getNameForMessage() });
            return;
        }
    }

    /**
     * addCheckInput
     * @param scrnMsg NWAL1630BMsg
     */
    public static void addCheckInput(NWAL1630BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd);
        scrnMsg.addCheckItem(scrnMsg.ordDt);
        scrnMsg.addCheckItem(scrnMsg.dsOrdPosnNum);
        scrnMsg.addCheckItem(scrnMsg.dsCpoLineNum);
        scrnMsg.addCheckItem(scrnMsg.dsCpoLineSubNum);
        scrnMsg.addCheckItem(scrnMsg.splyAbuseNodePrflCd);
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        // scrnMsg.addCheckItem(scrnMsg.dlrFleetNum); // S21_NA#29446 Del
        scrnMsg.addCheckItem(scrnMsg.splyCdTxt);
        scrnMsg.addCheckItem(scrnMsg.annTermCapNum_BW);
        scrnMsg.addCheckItem(scrnMsg.annTermCapNum_CL);
        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItem
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NWAL1630BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.csmpContrNum);
        scrnMsg.addCheckItem(scrnMsg.dlrRefNum);
        scrnMsg.putErrorScreen();
    }

    // 2017/09/22 QC#18859 Add Start
    /**
     * Get Param NSAL1240 For Configuration ID
     * @param scrnMsg NWAL1630BMsg
     * @return Param NSAL1240
     */
    public static Object[] setParamNSAL1240(NWAL1630BMsg scrnMsg) {
        scrnMsg.P.no(0).xxPopPrm_P.clear();

        Object[] params = new Object[31];

        // Input Parameter
        // [0]: CONFIG_EXST_MODE_CD
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, NSAL1240_MODE_02);
        params[0] = scrnMsg.P.no(0).xxPopPrm_P;
        // [2]: SER_NUM
        params[2] = scrnMsg.serNum;
        // [18]: CONTR_NUM
        params[18] = scrnMsg.dsContrNum;

        // Output Parameter
        // [28]: CONTR_NUM
        params[28] = scrnMsg.dsContrNum_PO;
        // [30]: Return Param
        params[30] = (EZDBMsgArray) scrnMsg.O;
        return params;
    }
    // 2017/09/22 QC#18859 Add End

    // 2017/09/22 QC#18859 Add Start
    /**
     * Set Param NSAL0110
     * @param scrnMsg NWAL1630BMsg
     * @return Param NSAL0110
     */
    public static Object[] setParamNSAL0110(NWAL1630BMsg scrnMsg) {
        scrnMsg.P.no(0).xxPopPrm_P.clear();

        Object[] params = new Object[12];

        // [0]: Contract #
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm_P, scrnMsg.dsContrNum);

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm_P;
        }

        params[10] = scrnMsg.dsContrPk;
        params[11] = scrnMsg.dsContrDtlPk;

        return params;
    }
    // 2017/09/22 QC#18859 Add End
    
    // 2019/01/24 S21_NA#29446 Add Start
    public static void addChkSerNumContrNum(NWAL1630BMsg scrnMsg){
        if (ZYPCommonFunc.hasValue(scrnMsg.serNum) || ZYPCommonFunc.hasValue(scrnMsg.dsContrNum)) {
            scrnMsg.addCheckItem(scrnMsg.serNum);
            scrnMsg.addCheckItem(scrnMsg.dsContrNum);
            scrnMsg.putErrorScreen();
        }
    }
    // 2019/01/24 S21_NA#29446 Add End
}
