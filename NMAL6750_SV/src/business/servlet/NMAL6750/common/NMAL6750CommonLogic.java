/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750.common;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.BIZ_ID;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_01_SAV_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_01_SAV_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_01_SAV_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_02_SUB_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_02_SUB_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_02_SUB_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_03_APL_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_03_APL_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_03_APL_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_04_APR_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_04_APR_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_04_APR_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_05_REJ_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_05_REJ_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_05_REJ_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_06_DWL_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_06_DWL_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_06_DWL_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_07_DEL_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_07_DEL_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_07_DEL_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_09_RST_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_09_RST_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_09_RST_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_10_RTR_GUARD;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_10_RTR_LABEL;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_10_RTR_NAME;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_ADD_ACCT;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_ADD_CTAC;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_DEL_CTAC;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BTN_OPEN_WIN_CTAC;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.CTAC_PNT_FMT_US;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.FUNC_ID_UPD;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6750.NMAL6750BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Resource Search NMAL67500CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/04   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/05   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/19   SRAA            Y.Chen          Update          QC#4350
 * 2017/08/15   Fujitsu         H.Nagashima     Update          QC#8598
 * 2017/08/29   Fujitsu         N.Sugiura       Update          QC#20770
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 *</pre>
 */

public class NMAL6750CommonLogic {
    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6740BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6750BMsg scrnMsg) {

        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(userProfileService, handler, scrnMsg);
    }

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZCommandHandler
     * @param scrnMsg NMAL6740BMsg
     */
    public static void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6750BMsg scrnMsg) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 0, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        if (functionIds.contains(FUNC_ID_UPD)) {
            handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
        }
    }

    /**
     * <pre>
     * Screen protecting control [Initial]
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZCommandHandler
     * @param scrnMsg NMAL6750BMsg
     */
    public static void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6750BMsg scrnMsg) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        if (functionIds.contains(FUNC_ID_UPD)) {
            scrnMsg.ctacPsnLastNm_H1.setInputProtected(false);
            scrnMsg.ctacPsnFirstNm_H1.setInputProtected(false);
            scrnMsg.dsCtacPsnSaltCd_H2.setInputProtected(false);
            scrnMsg.dsCtacPsnDeptCd_H2.setInputProtected(false);
            scrnMsg.dsCtacPsnTtlCd_H2.setInputProtected(false);
            scrnMsg.ctacTpCd_H2.setInputProtected(false);
            scrnMsg.dsCtacPntTpCd_H2.setInputProtected(false);

            scrnMsg.dsAcctNum_H1.setInputProtected(false);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.locNum_H1.setInputProtected(false);
            scrnMsg.locNm_H1.setInputProtected(true);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
                // Mod Start 2018/01/22 QC#20291(Sol#348)
                //scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(false);
                // Mod End 2018/01/22 QC#20291(Sol#348)
            }

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                // Add Start 2018/01/22 QC#20291(Sol#348)
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
                scrnMsg.B.no(i).ctacPntFmtTxt_B1.setInputProtected(false);
                // Add End 2018/01/22 QC#20291(Sol#348)
                // QC#4350
                scrnMsg.B.no(i).dsCtacPntTpCd_B1.setInputProtected(false);
                scrnMsg.B.no(i).dsCtacPntValTxt_B1.setInputProtected(false);
                scrnMsg.B.no(i).dsCtacPsnExtnNum_B1.setInputProtected(false);
                scrnMsg.B.no(i).dsOpsOutFlg_B1.setInputProtected(false);
                scrnMsg.B.no(i).dsCtacPntActvFlg_B1.setInputProtected(false);
            }

            handler.setButtonEnabled(BTN_ADD_ACCT, true);
            handler.setButtonEnabled(BTN_ADD_CTAC, true);
            // Add Start 2018/01/22 QC#20291(Sol#348)
            handler.setButtonEnabled(BTN_DEL_CTAC, true);
            // Add End 2018/01/22 QC#20291(Sol#348)
            // QC#8598 add Start
            if (!ZYPCommonFunc.hasValue(scrnMsg.ctacPsnNum_P1)) {
                handler.setButtonEnabled(BTN_OPEN_WIN_CTAC, true);
            } else {
                handler.setButtonEnabled(BTN_OPEN_WIN_CTAC, false);
            }
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                scrnMsg.R.no(i).xxChkBox_R1.setInputProtected(false);
            }
            // QC#8598 add End

            // Add Start 2018/01/22 QC#20291(Sol#348)
            scrnMsg.ctacPsnCmntTxt_H1.setInputProtected(false);
            // Add End 2018/01/22 QC#20291(Sol#348)

        } else {
            scrnMsg.ctacPsnLastNm_H1.setInputProtected(true);
            scrnMsg.ctacPsnFirstNm_H1.setInputProtected(true);
            scrnMsg.dsCtacPsnSaltCd_H2.setInputProtected(true);
            scrnMsg.dsCtacPsnDeptCd_H2.setInputProtected(true);
            scrnMsg.dsCtacPsnTtlCd_H2.setInputProtected(true);
            scrnMsg.ctacTpCd_H2.setInputProtected(true);
            scrnMsg.dsCtacPntTpCd_H2.setInputProtected(true);

            scrnMsg.dsAcctNum_H1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.locNum_H1.setInputProtected(true);
            scrnMsg.locNm_H1.setInputProtected(true);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                // Mod Start 2018/01/22 QC#20291(Sol#348)
                //scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
                // Mod End 2018/01/22 QC#20291(Sol#348)
            }

            scrnMsg.dsCtacPntTpCd_H2.setInputProtected(true);

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                // Add Start 2018/01/22 QC#20291(Sol#348)
                scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(true);
                scrnMsg.B.no(i).ctacPntFmtTxt_B1.setInputProtected(true);
                // Add End 2018/01/22 QC#20291(Sol#348)
                // QC#4350
                scrnMsg.B.no(i).dsCtacPntTpCd_B1.setInputProtected(true);
                scrnMsg.B.no(i).dsCtacPntValTxt_B1.setInputProtected(true);
                scrnMsg.B.no(i).dsCtacPsnExtnNum_B1.setInputProtected(true);
                scrnMsg.B.no(i).dsOpsOutFlg_B1.setInputProtected(true);
                scrnMsg.B.no(i).dsCtacPntActvFlg_B1.setInputProtected(true);
            }
            handler.setButtonEnabled(BTN_ADD_ACCT, false);
            handler.setButtonEnabled(BTN_ADD_CTAC, false);
            // Add Start 2018/01/22 QC#20291(Sol#348)
            handler.setButtonEnabled(BTN_DEL_CTAC, false);
            // Add End 2018/01/22 QC#20291(Sol#348)
            //QC#8598 add Start
            handler.setButtonEnabled(BTN_OPEN_WIN_CTAC, false);
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                scrnMsg.R.no(i).xxChkBox_R1.setInputProtected(true);
            }
            //QC#8598 add End

            // Add Start 2018/01/22 QC#20291(Sol#348)
            scrnMsg.ctacPsnCmntTxt_H1.setInputProtected(true);
            // Add End 2018/01/22 QC#20291(Sol#348)
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NMAL6750BMsg
     */
    public static void addCheckItem(NMAL6750BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.ctacPsnPk_H1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnNum_H1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnFirstNm_H1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsCtacPsnSaltCd_H2);
        scrnMsg.addCheckItem(scrnMsg.dsCtacPsnDeptCd_H2);
        scrnMsg.addCheckItem(scrnMsg.dsCtacPsnTtlCd_H2);
        scrnMsg.addCheckItem(scrnMsg.ctacTpCd_H2);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnActvFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.dsCtacPntTpCd_H2);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.locNum_H1);

        scrnMsg.addCheckItem(scrnMsg.ctacPsnCmntTxt_H1);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).locNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).firstLineAddr_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).scdLineAddr_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).ctyAddr_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).stCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).postCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            // scrnMsg.addCheckItem(scrnMsg.A.no(i).dsPrimLocFlg_A1); // QC#20770 dell
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntTpCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntValTxt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPsnExtnNum_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsOpsOutFlg_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dsCtacPntActvFlg_B1);
        }
        // QC#8598 add Start
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.R.no(i).xxChkBox_R1);
        }
        // QC#8598 add End
    }

    /**
     * setNameForMassage
     * @param scrnMsg NMAL6750BMsg
     */
    public static void setNameForMessage(NMAL6750BMsg scrnMsg) {

        scrnMsg.ctacPsnNum_H1.setNameForMessage("Contact ID");
        scrnMsg.ctacPsnFirstNm_H1.setNameForMessage("First Name");
        scrnMsg.ctacPsnLastNm_H1.setNameForMessage("Last Name");
        scrnMsg.dsCtacPsnSaltCd_H2.setNameForMessage("Salutation");
        scrnMsg.dsCtacPsnDeptCd_H2.setNameForMessage("Department");
        scrnMsg.dsCtacPsnTtlCd_H2.setNameForMessage("Title");
        scrnMsg.ctacTpCd_H2.setNameForMessage("Relationship to CSA");
        scrnMsg.ctacPsnActvFlg_H1.setNameForMessage("Active");
        scrnMsg.dsCtacPntTpCd_H2.setNameForMessage("Primary Contact Type");

        scrnMsg.dsAcctNum_H1.setNameForMessage("Account#");
        scrnMsg.locNum_H1.setNameForMessage("Location#");

        scrnMsg.ctacPsnCmntTxt_H1.setNameForMessage("Contact Note");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("CheckBox");
            scrnMsg.A.no(i).dsAcctNm_A1.setNameForMessage("Account Name");
            scrnMsg.A.no(i).dsAcctNum_A1.setNameForMessage("Account#");
            scrnMsg.A.no(i).locNum_A1.setNameForMessage("Location Name");
            scrnMsg.A.no(i).firstLineAddr_A1.setNameForMessage("Address1");
            scrnMsg.A.no(i).scdLineAddr_A1.setNameForMessage("Address2");
            scrnMsg.A.no(i).ctyAddr_A1.setNameForMessage("City");
            scrnMsg.A.no(i).stCd_A1.setNameForMessage("State");
            scrnMsg.A.no(i).postCd_A1.setNameForMessage("Postal Code");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("End Date");
            // scrnMsg.A.no(i).dsPrimLocFlg_A1.setNameForMessage("Primary"); //  // QC#20770 dell
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).xxChkBox_B1.setNameForMessage("CheckBox");
            // QC#4350
            scrnMsg.B.no(i).dsCtacPntTpCd_B1.setNameForMessage("Contact Type");
            scrnMsg.B.no(i).dsCtacPntValTxt_B1.setNameForMessage("Value");
            scrnMsg.B.no(i).dsCtacPsnExtnNum_B1.setNameForMessage("Extension");
            scrnMsg.B.no(i).dsOpsOutFlg_B1.setNameForMessage("Opt Out");
            scrnMsg.B.no(i).dsCtacPntActvFlg_B1.setNameForMessage("Active");
        }

    }
    // QC#8598 add Start
    // Del Start 2017/12/18 QC#22286
    ///**
    // * set Default Contact Type
    // * @param scrnMsg NMAL6750BMsg
    // */
    //public static void setDefaultContactType(NMAL6750BMsg scrnMsg) {
    //    
    //    boolean defaultSetFlg = true;
    //    for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
    //        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.R.no(i).xxChkBox_R1.getValue())) {
    //            defaultSetFlg = false;
    //            break;
    //        }
    //    }
    //    if (defaultSetFlg) {
    //        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
    //            if (CTAC_TP.DELIVERY_INSTALL.equals(scrnMsg.R.no(i).ctacTpCd_R1.getValue())) {
    //                ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(i).xxChkBox_R1, ZYPConstant.FLG_ON_Y);
    //            }
    //        }
    //    }
    //}
    // Del End 2017/12/18 QC#22286

    /**
     * set Contact Point Value by Format
     * @param scrnMsg NMAL6750BMsg
     */
    public static String setContactPointValueByFormat(NMAL6750BMsg scrnMsg, int idx) {

        String fmtTxt = scrnMsg.B.no(idx).ctacPntFmtTxt_B1.getValue();
        String dsCtacPntValTxt = scrnMsg.B.no(idx).dsCtacPntValTxt_B1.getValue();
        String ctacPntTpCd = scrnMsg.B.no(idx).dsCtacPntTpCd_B1.getValue();

        boolean fmtFlg = false;
        for (int i = 0; i < scrnMsg.T.getValidCount(); i++) {
            if (ctacPntTpCd.equals(scrnMsg.T.no(i).dsCtacPntTpCd_T1.getValue())) {
                fmtFlg = true;
                break;
            }
        }
        if (!fmtFlg) {
            return dsCtacPntValTxt;
        }

        if (!CTAC_PNT_FMT_US.equals(fmtTxt) || dsCtacPntValTxt == null || dsCtacPntValTxt.length() == 0) {
            return dsCtacPntValTxt;
        }

        String val = dsCtacPntValTxt.replace("-", "");
        if (val.length() != 10 || !ZYPCommonFunc.isNumeric(val)) {
            return dsCtacPntValTxt;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(val.substring(0,3)).append("-").append(val.substring(3, 6)).append("-").append(val.substring(6));
//        scrnMsg.B.no(idx).dsCtacPntValTxt_B1.setValue(sb.toString());
        return sb.toString();
    }
    // QC#8598 add End
}
