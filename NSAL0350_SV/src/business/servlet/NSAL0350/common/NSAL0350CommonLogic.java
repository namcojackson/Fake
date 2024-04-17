/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0350.common;

import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0350.NSAL0350BMsg;
import business.servlet.NSAL0350.constant.NSAL0350Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/10   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Hitachi         A.Kohinata      Update          N/A
 * 2016/04/25   Hitachi         T.Iwamoto       Update          QC#1759
 * 2017/08/07   Hitachi         K.Kitachi       Update          QC#20048
 * 2022/10/12   Hitachi         T.Doi           Update          QC#60007
 *</pre>
 */
public class NSAL0350CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0350BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0350BMsg scrnMsg, String userId) {
        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(userProfileService, handler, scrnMsg, userId);
        // ADD start 2016/04/25 CSA Defect#1759
        setRowColors(scrnMsg);
        // ADD end 2016/04/25 CSA Defect#1759
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0350BMsg
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0350BMsg scrnMsg) {
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_SAVE[0], NSAL0350Constant.BTN_CMN_SAVE[1], NSAL0350Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_SUBMIT[0], NSAL0350Constant.BTN_CMN_SUBMIT[1], NSAL0350Constant.BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_APPLY[0], NSAL0350Constant.BTN_CMN_APPLY[1], NSAL0350Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_APPROVE[0], NSAL0350Constant.BTN_CMN_APPROVE[1], NSAL0350Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_REJECT[0], NSAL0350Constant.BTN_CMN_REJECT[1], NSAL0350Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_BLANK6[0], NSAL0350Constant.BTN_CMN_BLANK6[1], NSAL0350Constant.BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_BLANK7[0], NSAL0350Constant.BTN_CMN_BLANK7[1], NSAL0350Constant.BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_CLEAR[0], NSAL0350Constant.BTN_CMN_CLEAR[1], NSAL0350Constant.BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_RESET[0], NSAL0350Constant.BTN_CMN_RESET[1], NSAL0350Constant.BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(NSAL0350Constant.BTN_CMN_RETURN[0], NSAL0350Constant.BTN_CMN_RETURN[1], NSAL0350Constant.BTN_CMN_RETURN[2], 1, null);

        if (!hasUpdate(userProfileService) || !ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxEdtModeFlg_H1.getValue())) {
            handler.setButtonProperties(NSAL0350Constant.BTN_CMN_SUBMIT[0], NSAL0350Constant.BTN_CMN_SUBMIT[1], NSAL0350Constant.BTN_CMN_SUBMIT[2], 0, null);
            // START 2017/08/07 K.Kitachi [QC#20048, DEL]
//            handler.setButtonProperties(NSAL0350Constant.BTN_CMN_RESET[0], NSAL0350Constant.BTN_CMN_RESET[1], NSAL0350Constant.BTN_CMN_RESET[2], 0, null);
            // END 2017/08/07 K.Kitachi [QC#20048, DEL]
        }
    }

    /**
     * Control screen fields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0350BMsg
     * @param userId String
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0350BMsg scrnMsg, String userId) {
        controlScreenHeaderFields(userProfileService, handler, scrnMsg, userId);
        controlScreenDetailFields(userProfileService, handler, scrnMsg, userId);
    }

    /**
     * controlScreenHeaderFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0350BMsg
     * @param userId String
     */
    private static final void controlScreenHeaderFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0350BMsg scrnMsg, String userId) {
        scrnMsg.dsContrNum_H1.setInputProtected(true);
        scrnMsg.serNum_H1.setInputProtected(true);
        // START 2022/10/12 T.Doi [QC#60007, ADD]
        scrnMsg.svcMachMstrPk_H1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
        // END 2022/10/12 T.Doi [QC#60007, ADD]
        scrnMsg.xxAbendMsgTxt_H1.setInputProtected(true);

        scrnMsg.mtrLbNm_A.setInputProtected(true);
        scrnMsg.mtrLbNm_B.setInputProtected(true);
        scrnMsg.mtrLbNm_C.setInputProtected(true);
        scrnMsg.mtrLbNm_D.setInputProtected(true);
        scrnMsg.mtrLbNm_E.setInputProtected(true);
        scrnMsg.mtrLbNm_F.setInputProtected(true);
        scrnMsg.mtrLbNm_G.setInputProtected(true);
        scrnMsg.mtrLbNm_H.setInputProtected(true);
        scrnMsg.mtrLbNm_I.setInputProtected(true);
        scrnMsg.mtrLbNm_J.setInputProtected(true);
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0350BMsg
     * @param userId String
     */
    private static final void controlScreenDetailFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NSAL0350BMsg scrnMsg, String userId) {

        String[] tblNms = getTblNms(scrnMsg);

        for (String tblNm : tblNms) {
            EZDMsgArray tblArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);

            for (int i = 0; i < tblArray.getValidCount(); i++) {
                EZDMsg ezdMsg = tblArray.get(i);

                String dsBllgSchdStsCd = NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "dsBllgSchdStsCd_A1").getValue();
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxEdtModeFlg_H1.getValue()) && DS_BLLG_SCHD_STS.OPEN.equals(dsBllgSchdStsCd) && hasUpdate(userProfileService)) {
                    NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "skipRecovTpCd_A3").setInputProtected(false);
                } else {
                    NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "skipRecovTpCd_A3").setInputProtected(true);
                }
                NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "basePrcDealAmt_A1").setAppFracDigit(2);
                NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "baseActlPrcDealAmt_A1").setAppFracDigit(2);
                // START 2015/10/15 [N/A, ADD]
                NSAL0350CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "dealTaxAmt_A1").setAppFracDigit(2);
                // END 2015/10/15 [N/A, ADD]
            }
        }

    }

    private static String[] getTblNms(NSAL0350BMsg scrnMsg) {
        String[] tblNms = null;
        String svcInvChrgTpCd = scrnMsg.svcInvChrgTpCd_H1.getValue();
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
            tblNms = new String[1];
            tblNms[0] = NSAL0350Constant.BASE_TBL_NM;

        } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
            tblNms = NSAL0350Constant.USAGE_TBL_NM_ARRAY;
        } else {
            tblNms = new String[0];
        }
        return tblNms;
    }

    // ADD start 2016/04/25 CSA Defect#1759
    /**
     * set Row Colors
     * @param NSAL0350BMsg scrnMsg
     */
    private static void setRowColors(NSAL0350BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(NSAL0350Constant.SCREEN_ID, scrnMsg);
        try {
            String[] tblNms = getTblNms(scrnMsg);
            String svcInvChrgTpCd = scrnMsg.svcInvChrgTpCd_H1.getValue();
            // set header back ground color (only Meter Charge)
            if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
                for (int i = 0; i < tblNms.length; i++) {
                    if (i%2 == 0) {
                        tblColor.setRowStyle(NSAL0350Constant.HEADER_LIST, i, NSAL0350Constant.STYLE_CLASS);
                    }
                }
            }
            // set detail back ground color
            for (String tblNm : tblNms) {
                EZDBMsgArray tblArray = (EZDBMsgArray) NSAL0350CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);
                tblColor.setAlternateRowsBG(tblNm, tblArray);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // ADD end 2016/04/25 CSA Defect#1759

    /**
     * <pre>
     * Check addCheckItem return UPDATE
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NSAL0350BMsg scrnMsg) {
        // START 2017/08/07 K.Kitachi [QC#20048, ADD]
        scrnMsg.addCheckItem(scrnMsg.bllgSchdFromDt_HD);
        scrnMsg.addCheckItem(scrnMsg.bllgSchdThruDt_HD);
        scrnMsg.addCheckItem(scrnMsg.dsBllgSchdStsCd_HD);
        scrnMsg.addCheckItem(scrnMsg.skipRecovTpCd_HD);
        // END 2017/08/07 K.Kitachi [QC#20048, ADD]
        addDetailCheckItem(scrnMsg);
    }

    /**
     * addDetailCheckItem
     * @param scrnMsg NSAL0350BMsg
     */
    public static void addDetailCheckItem(NSAL0350BMsg scrnMsg) {
        String[] tblNms = getTblNms(scrnMsg);
        for (String tblNm : tblNms) {
            EZDMsgArray tblArray = NSAL0350CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);
            for (int i = 0; i < tblArray.getValidCount(); i++) {
                EZDMsg ezdMsg = tblArray.get(i);
                scrnMsg.addCheckItem(NSAL0350CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "skipRecovTpCd_A3"));
            }
        }
    }

    /**
     * inputCheck
     * @param scrnMsg NSAL0350BMsg
     */
    public static final void inputCheck(NSAL0350BMsg scrnMsg) {
        addCheckItem(scrnMsg);
    }

    private static boolean hasUpdate(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NSAL0350Constant.BUSINESS_ID);
        if (functionIds.contains(NSAL0350Constant.FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }

    public static EZDBStringItem getStringValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDBStringItem result = null;
        try {
            result = (EZDBStringItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDBBigDecimalItem getBigDecimalValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDBBigDecimalItem result = null;
        try {
            result = (EZDBBigDecimalItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDBDateItem getDateValueFromEZDMsg(EZDMsg ezdMsg, String tblNm, String itemName) {
        EZDBDateItem result = null;
        try {
            result = (EZDBDateItem) ezdMsg.getClass().getField(replSfx(itemName, tblNm)).get(ezdMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static EZDMsgArray getTableArrayFromEZDMsg(NSAL0350BMsg scrnMsg, String tblNm) {
        EZDMsgArray result = null;
        try {
            result = (EZDMsgArray) scrnMsg.getClass().getField(tblNm).get(scrnMsg);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String replSfx(String str, String tblNm) {
        return str.replaceAll("_.", "_" + tblNm);
    }
}
