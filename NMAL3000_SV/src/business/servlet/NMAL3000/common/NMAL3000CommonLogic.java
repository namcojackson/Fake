/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000.common;

import static business.servlet.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_APL;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_APR;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_CLR;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_DEL;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_DWL;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_RJT;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_RST;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_RTN;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_SAV;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_CMN_SUB;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_COPY;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_INSERT_ROW;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_OPENWIN_SEARCH_ACCOUNT;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_OPENWIN_SEARCH_MODEL;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_SEARCH_ACCOUNT;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_SELECT_ALL;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_UNSELECT_ALL;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.FUNC_ID_UPDATE;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.INSERT_FLG;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.MAX_ROW;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.NMAM0192E;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.RECORD_NOTFOUND;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.SCRN_ID_00;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.ZZM9000E;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL3000.NMAL3000BMsg;
import business.servlet.NMAL3000.NMAL3000_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL3000CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5169, QC#5194
 * 2018/11/27   Fujitsu         R.Nakamura      Update          QC#27336
 *</pre>
 */
public class NMAL3000CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * checkInputValue
     * @param scrnMsg NMAL3000BMsg
     */
    public static void checkInputValue(NMAL3000BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {


            if (scrnMsg.A.no(i).dsAcctCustNum_A.isInputProtected()) {
                scrnMsg.A.no(i).dsAcctCustNum_A.clearErrorInfo();
            }

            if (scrnMsg.A.no(i).dsAcctDlrCd_A.isInputProtected()) {
                scrnMsg.A.no(i).dsAcctDlrCd_A.clearErrorInfo();
            }

            if (scrnMsg.A.no(i).mktMdlCd_A.isInputProtected()) {
                scrnMsg.A.no(i).mktMdlCd_A.clearErrorInfo();
            }

            if (scrnMsg.A.no(i).effFromDt_A.isInputProtected()) {
                scrnMsg.A.no(i).effFromDt_A.clearErrorInfo();
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsAcctCustNum_A)) {
                scrnMsg.A.no(i).dsAcctCustNum_A.setErrorInfo(1, ZZM9000E, new String[] {"Account Number"});
            }

            // QC#5169
            // if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsAcctDlrCd_A)) {
            //     scrnMsg.A.no(i).dsAcctDlrCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Dealer Code"});
            // }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mktMdlCd_A)) {
                scrnMsg.A.no(i).mktMdlCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Marketing Model"});
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A)) {
                scrnMsg.A.no(i).effFromDt_A.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date To"});
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctCustNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsAcctDlrCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mktMdlCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).slsAuthFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcAuthFlg_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * checkMandatory
     * @param scrnMsg NMAL3000BMsg
     */
    public static void checkMandatory(NMAL3000BMsg scrnMsg) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctCustNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.mktMdlCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctDlrCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && !ZYPCommonFunc.hasValue(scrnMsg.effThruDt)
                && !ZYPCommonFunc.hasValue(scrnMsg.slsAuthFlg_SA)
                && !ZYPCommonFunc.hasValue(scrnMsg.slsAuthFlg_SE)
                ) {

            scrnMsg.dsAcctCustNum.setErrorInfo(1, NMAM0192E);
            scrnMsg.mktMdlCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.dsAcctDlrCd.setErrorInfo(1, NMAM0192E);
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0192E);
            scrnMsg.effThruDt.setErrorInfo(1, NMAM0192E);
            scrnMsg.slsAuthFlg_SA.setErrorInfo(1, NMAM0192E);
            scrnMsg.slsAuthFlg_SE.setErrorInfo(1, NMAM0192E);
        }
    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL3000BMsg
     * @param scrnAMsgAry NMAL3000_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL3000BMsg scrnMsg, NMAL3000_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL3000BMsg
     * @param scrnAMsgAry NMAL3000_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL3000BMsg scrnMsg, NMAL3000_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNC_ID_UPDATE);
    }

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL3000BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL3000BMsg scrnMsg) {

        controlScreenFields(userProfileService, handler, scrnMsg);
    }

    /**
     * controlScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL3000BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL3000BMsg scrnMsg) {
        boolean updFlg = isUpdateUser(userProfileService);

        if (scrnMsg.A.getValidCount() == RECORD_NOTFOUND) {
            scrnMsg.dsAcctCustNum_CO.setInputProtected(true);
            scrnMsg.mktMdlCd_CO.setInputProtected(true);
            scrnMsg.effFromDt_CO.setInputProtected(true);
            handler.setButtonEnabled(BTN_COPY, false);
            handler.setButtonEnabled(BTN_DELETE_ROW, false);
            handler.setButtonEnabled(BTN_SELECT_ALL, false);
            handler.setButtonEnabled(BTN_UNSELECT_ALL, false);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        } else {
            scrnMsg.dsAcctCustNum_CO.setInputProtected(false);
            scrnMsg.mktMdlCd_CO.setInputProtected(false);
            scrnMsg.effFromDt_CO.setInputProtected(false);
            handler.setButtonEnabled(BTN_COPY, updFlg);
            handler.setButtonEnabled(BTN_DELETE_ROW, updFlg);
            handler.setButtonEnabled(BTN_SELECT_ALL, updFlg);
            handler.setButtonEnabled(BTN_UNSELECT_ALL, updFlg);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);

            if (updFlg) {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            }
        }

        if (MAX_ROW == scrnMsg.xxPageShowOfNum.getValueInt()) {
            handler.setButtonEnabled(BTN_INSERT_ROW, false);
        } else {
            handler.setButtonEnabled(BTN_INSERT_ROW, updFlg);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxRowId_A) && !INSERT_FLG.equals(scrnMsg.A.no(i).xxRowId_A.getValue())) {
                scrnMsg.A.no(i).dsAcctCustNum_A.setInputProtected(true);
                // QC#5194
                // scrnMsg.A.no(i).dsAcctDlrCd_A.setInputProtected(true);
                scrnMsg.A.no(i).mktMdlCd_A.setInputProtected(true);
                // Mod Start 2018/11/27 QC#27336
//                scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(false);
                // Mod End 2018/11/27 QC#27336
                handler.setButtonEnabled(BTN_SEARCH_ACCOUNT, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_SEARCH_ACCOUNT, i, false);
                handler.setButtonEnabled(BTN_OPENWIN_SEARCH_MODEL, i, false);
            } else {
                scrnMsg.A.no(i).dsAcctCustNum_A.setInputProtected(false);
                // QC#5194
                // scrnMsg.A.no(i).dsAcctDlrCd_A.setInputProtected(false);
                scrnMsg.A.no(i).mktMdlCd_A.setInputProtected(false);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(false);
                handler.setButtonEnabled(BTN_SEARCH_ACCOUNT, i, true);
                handler.setButtonEnabled(BTN_OPENWIN_SEARCH_ACCOUNT, i, true);
                handler.setButtonEnabled(BTN_OPENWIN_SEARCH_MODEL, i, true);
            }
            scrnMsg.A.no(i).dsAcctNm_A.setInputProtected(true);
            scrnMsg.A.no(i).locNum_A.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).stCd_A.setInputProtected(true);
            scrnMsg.A.no(i).postCd_A.setInputProtected(true);
            scrnMsg.A.no(i).upldUserId_A.setInputProtected(true);
            scrnMsg.A.no(i).upldDt_A.setInputProtected(true);
        }
    }
}
