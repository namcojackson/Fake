/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0750.common;

import static business.servlet.NSAL0750.constant.NSAL0750Constant.*;

import java.util.List;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0750.NSAL0750BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         J.Kim           Create          N/A
 * 2016/11/15   Hitachi         T.Kanasaka      Update          QC#15942
 * 2016/11/17   Hitachi         T.Tomita        Update          QC#15942
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0750CommonLogic {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0750BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NSAL0750BMsg scrnMsg) {

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (functionList.contains("NSAL0750T020")) {
            initCommonButton(handler, scrnMsg);
        } else {
            initInactiveCommonButton(handler, scrnMsg);
        }
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0750BMsg
     */
    public static void initCommonButton(EZDCommonHandler handler, NSAL0750BMsg scrnMsg) {

        // common button
        handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_ACTIVE, null);
        handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
     // START 2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
        handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_ACTIVE, null);
     // END   2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);

    }

    /**
     * Initialize the items and buttons on the screen.(InActive)
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0750BMsg
     */
    public static void initInactiveCommonButton(EZDCommonHandler handler, NSAL0750BMsg scrnMsg) {

        // common button
        handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
     // START 2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
        handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_ACTIVE, null);
     // END   2017/02/14 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);
    }
// mod start 2016/12/08 CSA QC#4210
    public static void controlScreenFields(EZDCommonHandler handler, NSAL0750BMsg scrnMsg) {
// mod end 2016/12/08 CSA QC#4210
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    private static void setRowColors(NSAL0750BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField(TBL_ID).get(scrnMsg);
            tblColor.setAlternateRowsBG(TBL_ID, table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Control ScreenDetail Fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0750BMsg
     */
    public static void controlScreenDetailFields(EZDCommonHandler handler, NSAL0750BMsg scrnMsg) {

        scrnMsg.baseBllgTmgCd_H3.setInputProtected(false);
        scrnMsg.svcMemoRsnCd_H3.setInputProtected(false);
        scrnMsg.svcCmntTxt.setInputProtected(false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            String flgNm3 = scrnMsg.A.no(i).xxFlgNm_D3.getValue();
            String flgNm4 = scrnMsg.A.no(i).xxFlgNm_D4.getValue();

            if (ZYPConstant.FLG_ON_1.equals(flgNm3)) {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(false);
            }

            scrnMsg.A.no(i).xxScrItem34Txt.setInputProtected(true);
            if (ZYPConstant.FLG_ON_1.equals(flgNm4)) {
                scrnMsg.A.no(i).xxChkBox_D2.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_D2.setInputProtected(false);
            }

            // START 2016/11/15 T.Kanasaka [QC#15942, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.A.no(i).dsContrCatgCd.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxScrItem34Txt)) {
                scrnMsg.A.no(i).xxChkBox_D2.setInputProtected(true);
            }
            // END 2016/11/15 T.Kanasaka [QC#15942, ADD]

            // START 2016/11/17 T.Tomita [QC#15942, ADD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxFlgNm_D1.getValue())) {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
            }
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxFlgNm_D2.getValue())) {
                scrnMsg.A.no(i).xxChkBox_D2.setInputProtected(true);
            }
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).invFlg.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxScrItem34Txt)) {
                    if (DS_CONTR_CATG.FLEET.equals(scrnMsg.A.no(i).dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.A.no(i).dsContrCatgCd.getValue())) {
                        scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
                    }
                } else {
                    if (DS_CONTR_CATG.REGULAR.equals(scrnMsg.A.no(i).dsContrCatgCd.getValue())) {
                        scrnMsg.A.no(i).xxChkBox_D2.setInputProtected(true);
                    }
                }
            }
            // END 2016/11/17 T.Tomita [QC#15942, ADD]

            scrnMsg.A.no(i).serNum.setInputProtected(true);
            scrnMsg.A.no(i).dsContrStsDescTxt.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffFromDt.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffThruDt.setInputProtected(true);
            scrnMsg.A.no(i).bllgTmgTpNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).bllgTmgTpNm_D2.setInputProtected(true);
            scrnMsg.A.no(i).contrMsgTxt.setInputProtected(true);
        }
    }

    /**
     * setBgColor
     * @param scrnMsg NSAL0750BMsg
     */
    public static void setBgColor(NSAL0750BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TBL_ID, scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID, scrnMsg.A);
        }
    }

    /**
     * addCheckItemForAllHeader
     * @param scrnMsg NSAL0750BMsg
     */
    public static void addCheckItemForAll(NSAL0750BMsg scrnMsg) {

        scrnMsg.svcMemoRsnCd_H3.setNameForMessage("Invoicing Rule");
        scrnMsg.baseBllgTmgCd_H3.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt.setNameForMessage("Notes");
    }
}
