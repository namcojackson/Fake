/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2560.common;

import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_APL;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_APR;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_DEL;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_DWL;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_RJT;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_RST;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_RTN;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_SAV;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.BTN_CMN_SUB;
import static business.servlet.NMAL2560.constant.NMAL2560Constant.SCRN_ID_00;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2560.NMAL2560BMsg;
import business.servlet.NMAL2560.NMAL2560_ABMsgArray;
import business.servlet.NMAL2560.constant.NMAL2560Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL2560CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         T.Ogura         Create          N/A
 * 2016/06/30   Hitachi         A.Kohinata      Update          QC#10647
 *</pre>
 */
public class NMAL2560CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Set Common Button properties.
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
     * @param scrnMsg NMAL2560BMsg
     * @param scrnAMsgAry NMAL2560_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2560BMsg scrnMsg, NMAL2560_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2560BMsg
     * @param scrnAMsgAry NMAL2560_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2560BMsg scrnMsg, NMAL2560_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL2560BMsg
     * @param scrnAMsgAry NMAL2560_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL2560BMsg scrnMsg, NMAL2560_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * controlScreenFields
     * @param usrPrfSvc S21UserProfileService
     * @param salesDate String
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2560BMsg
     */
    public static final void controlScreenFields(S21UserProfileService usrPrfSvc, String salesDate, EZDCommonHandler handler, NMAL2560BMsg scrnMsg) {
        controlScreenFields(usrPrfSvc, salesDate, handler, scrnMsg, null);
    }

    /**
     * controlScreenFields
     * @param usrPrfSvc S21UserProfileService
     * @param salesDate String
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2560BMsg
     * @param lastGuard String
     */
    public static final void controlScreenFields(S21UserProfileService usrPrfSvc, String salesDate, EZDCommonHandler handler, NMAL2560BMsg scrnMsg, String lastGuard) {

        if (NMAL2560Constant.MAX_ROW <= scrnMsg.A.getValidCount()) {
            handler.setButtonEnabled(NMAL2560Constant.BTN_ADD, false);
        } else {
            handler.setButtonEnabled(NMAL2560Constant.BTN_ADD, isUpdateUser(usrPrfSvc));
        }

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonEnabled(NMAL2560Constant.BTN_DELETE, false);
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            // 2016/06/30 QC#10647 Add Start
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            // 2016/06/30 QC#10647 Add End
        } else {
            handler.setButtonEnabled(NMAL2560Constant.BTN_DELETE, isUpdateUser(usrPrfSvc));

            if (isUpdateUser(usrPrfSvc)) {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
                // 2016/06/30 QC#10647 Add Start
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
                // 2016/06/30 QC#10647 Add End
            } else {
                handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
                // 2016/06/30 QC#10647 Add Start
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
                // 2016/06/30 QC#10647 Add End
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (NMAL2560Constant.INSERT_FLG.equals(scrnMsg.A.no(i).xxRowId_A.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).bizAreaOrgCd_A.setInputProtected(false);
                scrnMsg.A.no(i).orgHrchStruDfnNm_A.setInputProtected(false);
                scrnMsg.A.no(i).effFromDt_A.setInputProtected(false);
                scrnMsg.A.no(i).effThruDt_A.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_1.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_2.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_3.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_4.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_5.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_6.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_7.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_8.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_9.setInputProtected(false);
                scrnMsg.A.no(i).struDfnCd_10.setInputProtected(false);
            } else {

                if ("AddBusinessArea".equals(lastGuard)
                        || "DelBusinessArea".equals(lastGuard)) {
                    continue;
                }

                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);

                if (salesDate.compareTo(scrnMsg.A.no(i).effFromDt_A.getValue()) <= 0) {
                    scrnMsg.A.no(i).bizAreaOrgCd_A.setInputProtected(false);
                    scrnMsg.A.no(i).orgHrchStruDfnNm_A.setInputProtected(false);
                    scrnMsg.A.no(i).effFromDt_A.setInputProtected(false);
                    scrnMsg.A.no(i).effThruDt_A.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_1.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_2.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_3.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_4.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_5.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_6.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_7.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_8.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_9.setInputProtected(false);
                    scrnMsg.A.no(i).struDfnCd_10.setInputProtected(false);
                } else {
                    scrnMsg.A.no(i).bizAreaOrgCd_A.setInputProtected(true);
                    scrnMsg.A.no(i).orgHrchStruDfnNm_A.setInputProtected(true);
                    scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_1.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_2.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_3.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_4.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_5.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_6.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_7.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_8.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_9.setInputProtected(true);
                    scrnMsg.A.no(i).struDfnCd_10.setInputProtected(true);

                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A) && salesDate.compareTo(scrnMsg.A.no(i).effThruDt_A.getValue()) > 0) {
                        scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
                    } else {
                        scrnMsg.A.no(i).effThruDt_A.setInputProtected(false);
                    }
                }
            }
            scrnMsg.A.no(i).xxChkMaxDescTxt_IN.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_IN.setInputProtected(true);
            scrnMsg.A.no(i).xxChkMaxDescTxt_UP.setInputProtected(true);
            scrnMsg.A.no(i).xxDtTm_UP.setInputProtected(true);
        }
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NMAL2560Constant.BIZ_ID);
        return functionIds.contains(NMAL2560Constant.FUNC_ID_UPDATE);
    }

    /**
     * addCheckItemDetail
     * @param scrnMsg NMAL2560BMsg
     */
    public static void addCheckItemDetail(NMAL2560BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).bizAreaOrgCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).orgHrchStruDfnNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_4);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_5);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_6);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_7);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_8);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_9);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).struDfnCd_10);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkMaxDescTxt_IN);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDtTm_IN);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkMaxDescTxt_UP);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxDtTm_UP);
        }
    }
}
