/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7280.common;

import static business.servlet.NMAL7280.constant.NMAL7280Constant.BIZ_ID;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_ADD_COND_GRP;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_APL;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_APR;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_RST;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.BTN_DEL_COND_GRP;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.SCRN_ID_00;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.UPDATE_AUTHORITY;

import java.util.List;

import business.servlet.NMAL7280.NMAL7280BMsg;
import business.servlet.NMAL7280.NMAL7280_ABMsgArray;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7280CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 * 2017/02/27   Fujitsu         W.Honda         Update          QC#16011
 *</pre>
 */
public class NMAL7280CommonLogic {

    // Add Start 2017/02/27 W.Honda S21_NA#16011
    /**
     * updateAuthority
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean updateAuthority(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(UPDATE_AUTHORITY);
    }

    /**
     * setScrnCtrl
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7280BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setScrnCtrl(S21CommonHandler handler, NMAL7280BMsg scrnMsg, S21UserProfileService userProfileService) {
        boolean protectBtnFlg = updateAuthority(userProfileService);
        boolean protectFldFlg = !protectBtnFlg;
        initCmnBtnProp(handler, protectBtnFlg);

        handler.setButtonEnabled(BTN_ADD_COND_GRP, protectBtnFlg);
        handler.setButtonEnabled(BTN_DEL_COND_GRP, protectBtnFlg);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleCondGrpCd_A.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A1.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A1.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleCondNum_A2.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A2.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A3.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A3.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A4.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A4.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A5.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A5.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A6.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A6.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A7.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A7.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A8.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A8.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_A9.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_A9.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_AA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_AA.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_AB.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcRuleOpTpCd_AB.setInputProtected(true);
            scrnMsg.A.no(i).prcRuleCondNum_AC.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).effThruDt_A.setInputProtected(protectFldFlg);
        }
    }
    // Add End 2017/02/27 W.Honda S21_NA#16011

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler, boolean protectBtnFlg) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        // Mod Start 2017/02/27 W.Honda S21_NA#16011
        if (protectBtnFlg) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        // Mod End 2017/02/27 W.Honda S21_NA#16011
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

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
     * @param scrnMsg     NMAL7280BMsg
     * @param scrnAMsgAry NMAL7280_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear1(NMAL7280BMsg scrnMsg, NMAL7280_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7280BMsg
     * @param scrnAMsgAry NMAL7280_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7280BMsg scrnMsg, NMAL7280_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7280BMsg
     * @param scrnAMsgAry NMAL7280_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7280BMsg scrnMsg, NMAL7280_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }


}
