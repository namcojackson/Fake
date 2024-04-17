/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0150.common;

import static business.servlet.NWCL0150.constant.NWCL0150Constant.BIZ_APP_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_10_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_10_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_10_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_1_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_1_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_1_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_2_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_2_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_2_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_3_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_3_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_3_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_4_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_4_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_4_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_5_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_5_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_5_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_6_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_6_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_6_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_7_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_7_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_7_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_8_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_8_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_8_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_9_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_9_NAME;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.BTN_CMN_BTN_9_VAL;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.FUNCTION_UPDATE;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.SCRN_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.TABLE_A;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWCL0150.NWCL0150BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_MAN_HLD_ACT_TP;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NWCL0150:Lease Package Hold Release Screen
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 * 09/21/2017   Fujitsu         H.Ikeda         Update          QC#20381
 * 01/04/2018   Fujitsu         W.Honda         Update          QC#21706
 * 01/10/2018   Fujitsu         W.Honda         Update          QC#21706-2
 * 04/18/2022   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWCL0150CommonLogic {

    /**
     * Set Common Button for Initialized screen
     * @param handler EZDCommonHandler
     */
    public static void setCommonButtonInit(EZDCommonHandler handler) {
        // 0 : inactive
        // 1 : active
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 0, null);
        }
        handler.setButtonProperties(BTN_CMN_BTN_1_ID, BTN_CMN_BTN_1_NAME, BTN_CMN_BTN_1_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3_ID, BTN_CMN_BTN_3_NAME, BTN_CMN_BTN_3_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4_ID, BTN_CMN_BTN_4_NAME, BTN_CMN_BTN_4_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5_ID, BTN_CMN_BTN_5_NAME, BTN_CMN_BTN_5_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6_ID, BTN_CMN_BTN_6_NAME, BTN_CMN_BTN_6_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7_ID, BTN_CMN_BTN_7_NAME, BTN_CMN_BTN_7_VAL, 0, null);
        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        handler.setButtonProperties(BTN_CMN_BTN_8_ID, BTN_CMN_BTN_8_NAME, BTN_CMN_BTN_8_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8_ID, BTN_CMN_BTN_8_NAME, BTN_CMN_BTN_8_VAL, 1, null);
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
        handler.setButtonProperties(BTN_CMN_BTN_9_ID, BTN_CMN_BTN_9_NAME, BTN_CMN_BTN_9_VAL, 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10_ID, BTN_CMN_BTN_10_NAME, BTN_CMN_BTN_10_VAL, 1, null);

    }

    /**
     * set attribute
     * @param scrnMsg NWCL0150BMsg
     */
    public static void setAttr(NWCL0150BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        // Invoiced Threshold(%) digit 2
        scrnMsg.attrbValNum.setInputProtected(true);
        scrnMsg.attrbValNum.setAppFracDigit(2);

        if (isUpdatable()) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // QC#20381 add Start
                scrnMsg.A.no(i).cfsPoNum_A1.setInputProtected(true);
                // QC#20381 add End
                scrnMsg.A.no(i).cpoOrdNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).cfsPoAmt_A1.setInputProtected(true);
                // CPO Amount digit 2
                scrnMsg.A.no(i).cfsPoAmt_A1.setAppFracDigit(2);
                scrnMsg.A.no(i).invCpltAmtRate_A1.setInputProtected(true);
                // Percent Invoiced digit 2
                scrnMsg.A.no(i).invCpltAmtRate_A1.setAppFracDigit(2);
                scrnMsg.A.no(i).crRebilHldFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).cfsLeasePkgHldFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).leasePkgCratFlg_A1.setInputProtected(true);
                // QC#21706 mod Start
//                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).leasePkgCratFlg_A1.getValue())) {
                // QC#21706-2 mod Start
//                if ((ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).cfsLeasePkgHldFlg_A1.getValue())
                if ((ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).leasePkgCratFlg_A1.getValue())
                // QC#21706-2 mod End
                        && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).poInfoProcFlg_A1.getValue()))
                        || CFS_MAN_HLD_ACT_TP.IMMEDIATE_RELEASE.equals(scrnMsg.A.no(i).cfsManHldActTpCd_A1.getValue())) {
                // QC#21706 mod End
                    scrnMsg.A.no(i).cfsManHldActTpCd_A1.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).cfsManHldActTpCd_A1.setInputProtected(false);
                }
            }
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // QC#20381 add Start
                scrnMsg.A.no(i).cfsPoNum_A1.setInputProtected(true);
                // QC#20381 add End
                scrnMsg.A.no(i).cpoOrdNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).cfsPoAmt_A1.setInputProtected(true);
                // CPO Amount digit 2
                scrnMsg.A.no(i).cfsPoAmt_A1.setAppFracDigit(2);
                scrnMsg.A.no(i).invCpltAmtRate_A1.setInputProtected(true);
                // Percent Invoiced digit 2
                scrnMsg.A.no(i).invCpltAmtRate_A1.setAppFracDigit(2);
                scrnMsg.A.no(i).crRebilHldFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).cfsLeasePkgHldFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).leasePkgCratFlg_A1.setInputProtected(true);
                scrnMsg.A.no(i).cfsManHldActTpCd_A1.setInputProtected(true);
            }
        }
    }

    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_UPDATE);
    }

    /**
     * Function List
     * @return List<String> Function List
     */
    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_APP_ID);
        return functionList;
    }
}
