/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLEL0070.common;

import static business.servlet.NLEL0070.constant.NLEL0070Constant.DEF_LIFE_MTH;
import static business.servlet.NLEL0070.constant.NLEL0070Constant.ZZM9004E;
import static business.servlet.NLEL0070.constant.NLEL0070Constant.NLEM0039E;

import java.math.BigDecimal;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLEL0070.NLEL0070BMsg;
import business.servlet.NLEL0070.constant.NLEL0070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 * 05/09/2016   Fujitsu         C.Tanaka        Update          QC#7065
 * 2016/06/08   Hitachi         T.Tsuchida      Update          QC#9637
 * 2016/08/04   Hitachi         J.Kim           Update          QC#12925
 *</pre>
 */
public class NLEL0070CommonLogic {

    /**
     * hasRegistFuncId
     * @return boolean false:reference true:upedate
     */
    public static boolean hasUpdateFuncId() {
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NLEL0070Constant.BUSINESS_APPLICATION_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Asset Book Control(" + NLEL0070Constant.BUSINESS_APPLICATION_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        for (String func : funcList) {
            if (NLEL0070Constant.FUNC_ID_UPDATE.equals(func)) {
                return true;
            }
        }
        return false;
    }

    /**
     * buttonControl
     * @param scrnMsg NLEL0070BMsg
     * @param handler S21CommonHandler
     */
    public static void buttonControl(EZDCommonHandler handler, NLEL0070BMsg scrnMsg) {

        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_BTN_1[0], NLEL0070Constant.BTN_CMN_BTN_1[1], NLEL0070Constant.BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_SUBMIT[0], NLEL0070Constant.BTN_CMN_SUBMIT[1], NLEL0070Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_BTN_3[0], NLEL0070Constant.BTN_CMN_BTN_3[1], NLEL0070Constant.BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_BTN_4[0], NLEL0070Constant.BTN_CMN_BTN_4[1], NLEL0070Constant.BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_BTN_5[0], NLEL0070Constant.BTN_CMN_BTN_5[1], NLEL0070Constant.BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_BTN_6[0], NLEL0070Constant.BTN_CMN_BTN_6[1], NLEL0070Constant.BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_BTN_7[0], NLEL0070Constant.BTN_CMN_BTN_7[1], NLEL0070Constant.BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_CLEAR[0], NLEL0070Constant.BTN_CMN_CLEAR[1], NLEL0070Constant.BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_RESET[0], NLEL0070Constant.BTN_CMN_RESET[1], NLEL0070Constant.BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(NLEL0070Constant.BTN_CMN_RETURN[0], NLEL0070Constant.BTN_CMN_RETURN[1], NLEL0070Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * guiAttributeClear
     * @param scrnMsg NLEL0070BMsg
     */
    public static void clearGUIAttribute(NLEL0070BMsg scrnMsg) {
        // clear GUI Attribute
        scrnMsg.clearAllGUIAttribute(NLEL0070Constant.BUSINESS_APPLICATION_ID + "Scrn00");
    }

    /**
     * protectedInitEv
     * @param scrnMsg NLEL0070BMsg
     * @param handler S21CommonHandler
     */
    public static void protectedInitEv(NLEL0070BMsg scrnMsg, S21CommonHandler handler) {

        // clearGUIAttribute(scrnMsg);
        scrnMsg.setInputProtected(false);

        // _for Main
        scrnMsg.xxYrMth_M1.setInputProtected(true);
        scrnMsg.acctYrMth_M1.setInputProtected(true);

        // _for Button
        handler.setButtonEnabled(NLEL0070Constant.BTN_SEARCH, true);

        // _for CMNbutton
        NLEL0070CommonLogic.buttonControl(handler, scrnMsg);

        if (NLEL0070CommonLogic.hasUpdateFuncId()) {
            // START 2016/06/08 T.Tsuchida [QC#9637,MOD]
            //handler.setButtonProperties(NLEL0070Constant.BTN_CMN_BTN_1[0], NLEL0070Constant.BTN_CMN_BTN_1[1], NLEL0070Constant.BTN_CMN_BTN_1[2], 1, null);
            handler.setButtonProperties(NLEL0070Constant.BTN_CMN_SUBMIT[0], NLEL0070Constant.BTN_CMN_SUBMIT[1], NLEL0070Constant.BTN_CMN_SUBMIT[2], 1, null);
            // END 2016/06/08 T.Tsuchida [QC#9637,MOD]
        } else {
            scrnMsg.assetTpCd_M1.setInputProtected(true);
            scrnMsg.effFromDt_M1.setInputProtected(true);
            scrnMsg.assetBookCtrlDescTxt_M1.setInputProtected(true);
            scrnMsg.defDepcMthNum_M1.setInputProtected(true);

            scrnMsg.assetCoaAcctCd_M1.setInputProtected(true);
            scrnMsg.depcCoaAcctCd_M1.setInputProtected(true);
            scrnMsg.accumDepcCoaAcctCd_M1.setInputProtected(true);
            scrnMsg.depcAdjCoaAcctCd_M1.setInputProtected(true);
            scrnMsg.slsPrcdCoaAcctCd_M1.setInputProtected(true);
            scrnMsg.rmvCostCoaAcctCd_M1.setInputProtected(true);
            scrnMsg.gainLossCoaAcctCd_M1.setInputProtected(true);
            scrnMsg.clingCoaAcctCd_M1.setInputProtected(true);
            scrnMsg.adjCoaAcctCd_M1.setInputProtected(true);

            scrnMsg.assetCoaAcctCd_LK.setInputProtected(true);
            scrnMsg.depcCoaAcctCd_LK.setInputProtected(true);
            scrnMsg.accumDepcCoaAcctCd_LK.setInputProtected(true);
            scrnMsg.depcAdjCoaAcctCd_LK.setInputProtected(true);
            scrnMsg.slsPrcdCoaAcctCd_LK.setInputProtected(true);
            scrnMsg.rmvCostCoaAcctCd_LK.setInputProtected(true);
            scrnMsg.gainLossCoaAcctCd_LK.setInputProtected(true);
            scrnMsg.clingCoaAcctCd_LK.setInputProtected(true);
            scrnMsg.adjCoaAcctCd_LK.setInputProtected(true);
        }
    }

    public static void addCheckItems(NLEL0070BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.assetTpCd_M1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_M1);
        scrnMsg.addCheckItem(scrnMsg.assetBookCtrlDescTxt_M1);
        scrnMsg.addCheckItem(scrnMsg.defDepcMthNum_M1);
        scrnMsg.addCheckItem(scrnMsg.assetCoaAcctCd_M1);
        scrnMsg.addCheckItem(scrnMsg.depcCoaAcctCd_M1);
        scrnMsg.addCheckItem(scrnMsg.accumDepcCoaAcctCd_M1);
        scrnMsg.addCheckItem(scrnMsg.depcAdjCoaAcctCd_M1);
        scrnMsg.addCheckItem(scrnMsg.slsPrcdCoaAcctCd_M1);
        scrnMsg.addCheckItem(scrnMsg.rmvCostCoaAcctCd_M1);
        scrnMsg.addCheckItem(scrnMsg.gainLossCoaAcctCd_M1);
        scrnMsg.addCheckItem(scrnMsg.clingCoaAcctCd_M1);
        scrnMsg.addCheckItem(scrnMsg.adjCoaAcctCd_M1);

        // START 2016/05/09 C.Tanaka [QC#7065, ADD]
        if (!ZYPCommonFunc.isNumberCheck(scrnMsg.defDepcMthNum_M1.getValue())) {
            scrnMsg.defDepcMthNum_M1.setErrorInfo(1, ZZM9004E, new String[] {DEF_LIFE_MTH });
        } else {
            BigDecimal depcMthMun = new BigDecimal(scrnMsg.defDepcMthNum_M1.getValue());
            if (depcMthMun.compareTo(BigDecimal.ZERO) < 0) {
                scrnMsg.defDepcMthNum_M1.setErrorInfo(1, ZZM9004E, new String[] {DEF_LIFE_MTH });
            // START 2016/08/04 J.Kim [QC#12925, MOD]
            } else if (depcMthMun.compareTo(BigDecimal.ZERO) == 0) {
                scrnMsg.defDepcMthNum_M1.setErrorInfo(1, NLEM0039E);
            }
            // END 2016/08/04 J.Kim [QC#12925, MOD]
        }
        // END 2016/05/09 C.Tanaka [QC#7065, ADD]

        scrnMsg.putErrorScreen();
    }

    public static Object[] getNMAL6050Param(NLEL0070BMsg scrnMsg, String value) {

        scrnMsg.P.clear();

        scrnMsg.P.no(0).xxPopPrm.setValue("COA_ACCT");
        scrnMsg.P.no(1).xxPopPrm.setValue("COA_ACCT_CD");
        scrnMsg.P.no(2).xxPopPrm.setValue("COA_ACCT_NM");
        scrnMsg.P.no(3).xxPopPrm.setValue("COA_ACCT_SORT_NUM");
        scrnMsg.P.no(4).xxPopPrm.setValue("Lookup COA Account Code");
        scrnMsg.P.no(5).xxPopPrm.setValue("COA Account Code");
        scrnMsg.P.no(6).xxPopPrm.setValue("COA Account Name");
        scrnMsg.P.no(7).xxPopPrm.setValue("COA Account Code");
        scrnMsg.P.no(8).xxPopPrm.setValue("COA Account Name");
        scrnMsg.P.no(9).xxPopPrm.setValue(value);
        scrnMsg.P.no(10).xxPopPrm.clear();

        Object[] param = new Object[11];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;
        param[10] = scrnMsg.P.no(10).xxPopPrm;

        return param;
    }
}
