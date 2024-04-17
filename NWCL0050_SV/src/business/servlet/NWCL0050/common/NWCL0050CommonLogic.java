/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0050.common;

import static business.servlet.NWCL0050.constant.NWCL0050Constant.AUTH_CREDIT_CARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.AUTH_MAIL_ENTRY;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BIZ_ID;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_01_SAV_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_01_SAV_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_01_SAV_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_02_SUB_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_02_SUB_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_02_SUB_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_03_APL_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_03_APL_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_03_APL_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_04_APR_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_04_APR_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_04_APR_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_05_REJ_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_05_REJ_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_05_REJ_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_06_DWL_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_06_DWL_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_06_DWL_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_07_DEL_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_07_DEL_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_07_DEL_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_08_CLE_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_08_CLE_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_08_CLE_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_09_RST_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_09_RST_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_09_RST_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_10_RTR_GUARD;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_10_RTR_LABEL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_10_RTR_NAME;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_DISPLAY;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_EMAIL;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.BTN_PAY;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.RECORD_PER_PAGE_10;
import static business.servlet.NWCL0050.constant.NWCL0050Constant.SCRN_ID;

import java.util.List;

import business.servlet.NWCL0050.NWCL0050BMsg;
import business.servlet.NWCL0050.NWCL0050Bean;
import business.servlet.NWCL0050.NWCL0050_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/26   Fujitsu         T.Yoshida       Create          N/A
 * 2017/03/07   Fujitsu         K.Ishizuka      Update          QC#13856
 * 2018/07/11   Fujitsu         M.Ohno          Update          QC#19801
 * 2019/02/26   Fujitsu         Y.Kanefusa      Update          S21_NA#30519
 *</pre>
 */
public class NWCL0050CommonLogic {

    /**
     * initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null); // MOD S21_NA QC#13856
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 0, null); // MOD S21_NA QC#13856
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);
    }

    /**
     * set Table's Back Color
     * @param scrnMsg NWCL0050BMsg
     */
    public static final void setTblBackColor(NWCL0050BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.clearRowsBG(NWCL0050Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NWCL0050Bean.A, scrnMsg.A);
    }

    /**
     * set Digit.
     * @param scrnMsg NWCL0050BMsg
     */
    public static void setAppFracDigit(NWCL0050BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).conslBillTotAmt_A.setAppFracDigit(2);
        }
    }

    /**
     * set Protected.
     * @param scrnMsg NWCL0050BMsg
     */
    public static void setProtected(NWCL0050BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // QC#30519 2019/02/26 Mod Start
            // 2018/07/11 QC#19801 mod start
            // if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).eipRptRqstPk_A)) {
            // if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).invNum_A)) {
            // 2018/07/11 QC#19801 mod end
            //     scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            // } else {
            //     scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
            // }
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
            // QC#30519 2019/02/26 Mod End
        }
    }
    
    /**
     * clear Header
     * @param scrnMsg NWCL0050BMsg
     */
    public static void clearHdr(NWCL0050BMsg scrnMsg) {

        scrnMsg.invNum.clear();
        scrnMsg.cpoOrdNum.clear();
        scrnMsg.conslBillNum.clear();
        scrnMsg.billToDsAcctNum.clear();
        scrnMsg.billToDsAcctNm.clear();
        scrnMsg.billToLocNum.clear();
        scrnMsg.xxSerNumSrchTxt.clear();
        scrnMsg.dsContrNum.clear();
        scrnMsg.invAvgGrpNum.clear();
        scrnMsg.xxUrnNum.clear();
        scrnMsg.invPrtBatTpCd.clear();
        scrnMsg.invPrtBrCd.clear();
        scrnMsg.ordClsCd.clear();
        scrnMsg.invSmryLineTpCd.clear();
        scrnMsg.conslBillInvDt_FR.clear();
        scrnMsg.conslBillInvDt_TO.clear();
        scrnMsg.xxCratDt_FR.clear();
        scrnMsg.xxCratDt_TO.clear();

        // initial value
//        scrnMsg.invProcTpCd.setValue(INV_PROC_TP.SPECIAL_BILLING);
        scrnMsg.keyInfoCd.setValue(RECORD_PER_PAGE_10);
        scrnMsg.xxChkBox_SB.setValue(ZYPConstant.FLG_OFF_N);
        scrnMsg.xxDplyCtrlFlg_EM.clear();
    }

    /**
     * set Authority
     * @param handler Event Handler
     */
    public static void setAuthority(S21CommonHandler handler) {

        // 218/07/11 QC#19801 add start
        handler.setButtonEnabled(BTN_DISPLAY, true);
        // 218/07/11 QC#19801 add end
        handler.setButtonEnabled(BTN_PAY, false);
        handler.setButtonEnabled(BTN_EMAIL, false);

        List<String> funcList = getFunctionList();

        if (funcList.contains(AUTH_CREDIT_CARD)) {
            handler.setButtonEnabled(BTN_PAY, true);
        }

        if (funcList.contains(AUTH_MAIL_ENTRY)) {
            handler.setButtonEnabled(BTN_EMAIL, true);
        }
    }

    /**
     * control File Link
     * @param scrnMsg NWCL0050BMsg
     */
    public static void controlFileLink(NWCL0050BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWCL0050_ABMsg abMsg = scrnMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(abMsg.invNum_A)) {
                abMsg.invFileUrl_AB.clear();
            } else {
                abMsg.invFileUrl_AI.clear();
            }

            if (!ZYPCommonFunc.hasValue(abMsg.xxUrnNum_A)) {
                abMsg.invFileUrl_AU.clear();
            }
        }
    }

    /**
     * set Parameter for Mail Entry
     * @param scrnMsg NWCL0050BMsg
     * @return Parameter for NMAL6050
     */
    public static Object[] getParamMailEntry(NWCL0050BMsg scrnMsg) {
//CSA MOD Start
//        Object[] params = new Object[2];
        Object[] params = new Object[6];
//CSA MOD End
        params[0] = scrnMsg.B;
        params[1] = scrnMsg.xxSfxKeyTxt.getValue();
//CSA ADD Start
        scrnMsg.scrInvEmlTs.clear();
        scrnMsg.scrInvEmlAddr.clear();
        scrnMsg.scrInvEmlSubjTxt.clear();
        scrnMsg.scrInvEmlCmntTxt.clear();
        params[2] = scrnMsg.scrInvEmlTs;
        params[3] = scrnMsg.scrInvEmlAddr;
        params[4] = scrnMsg.scrInvEmlSubjTxt;
        params[5] = scrnMsg.scrInvEmlCmntTxt;
//CSA ADD End
        return params;
    }

    /**
     * set Parameter for Payment
     * @param scrnMsg NWCL0050BMsg
     * @return Parameter for NMAL6050
     */
    public static Object[] setParamPayment(NWCL0050BMsg scrnMsg) {
        Object[] params = new Object[2];
        params[0] = scrnMsg.dsAcctNum_PM;
        params[1] = scrnMsg.xxFldValTxt_PM;

        return params;
    }
    
    /**
     * get Function List
     * @return Function List
     */
    private static List<String> getFunctionList() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Invoice Search(" + BIZ_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        return funcList;
    }
}
