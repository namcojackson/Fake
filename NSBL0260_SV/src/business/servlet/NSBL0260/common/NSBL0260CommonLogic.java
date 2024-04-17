/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0260.common;

import static business.servlet.NSBL0260.constant.NSBL0260Constant.AUTH_UPDATE;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_APPLY;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_APPROVE;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_CLEAR;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_DELETE;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_DOWNROAD;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_REJECT;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_RESET;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_RETURN;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_SAVE;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BTN_CMN_SUBMIT;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.BUSINESS_ID;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.NSBM0153E;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.NSBM0155E;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PARAM_LENGTH_NSAL1240;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_00;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_01;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_02;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_03;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_04;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_05;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_06;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_07;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_08;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_09;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_10;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_11;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_12;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_26;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.PRMS_30;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.SCREEN_ID;
import static business.servlet.NSBL0260.constant.NSBL0260Constant.ZZM9000E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSBL0260.NSBL0260BMsg;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * EOL Exception Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2018/06/07   Fujitsu         T.Ogura         Update          QC#22395
 *</pre>
 */
public class NSBL0260CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0260BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSBL0260BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);

        S21TableColorController control = new S21TableColorController(SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSBL0260BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSBL0260BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonEnabled("InsertRow", true);
            handler.setButtonEnabled("DeleteRow", true);
            handler.setButtonEnabled("SelectAll", true);
            handler.setButtonEnabled("UnselectAll", true);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonEnabled("InsertRow", false);
            handler.setButtonEnabled("DeleteRow", false);
            handler.setButtonEnabled("SelectAll", false);
            handler.setButtonEnabled("UnselectAll", false);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0260BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSBL0260BMsg scrnMsg) {
        controlScreenTableFields(scrnMsg);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Interface Maintenance (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * controlScreenTableFields
     * @param scrnMsg NSBL0260BMsg
     */
    public static final void controlScreenTableFields(NSBL0260BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).mdlNm_A.setInputProtected(false);
            scrnMsg.A.no(i).serNum_A.setInputProtected(false);
            scrnMsg.A.no(i).dsMdlEolDt_AN.setInputProtected(false);
            scrnMsg.A.no(i).dsMdlEolDt_AS.setInputProtected(false);
            scrnMsg.A.no(i).actvFlg_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNum_A.setInputProtected(true);
            scrnMsg.A.no(i).svcConfigMstrPk_A.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr_A.setInputProtected(true);
            scrnMsg.A.no(i).stCd_A.setInputProtected(true);
        }
    }

    /**
     * checkCriteria
     * @param scrnMsg NSBL0260BMsg
     */
    public static void checkCriteria(NSBL0260BMsg scrnMsg) {
        int inputCnt = 0;
        boolean combFlag = false;
        boolean mdlFlag = false;
        boolean dsAccFlag = false;
        if (hasValue(scrnMsg.mdlNm_C) || hasValue(scrnMsg.serNum_C)) {
            combFlag = true;
            inputCnt++;
        }
        if (hasValue(scrnMsg.mdlNm_H)) {
            mdlFlag = true;
            inputCnt++;
        }
        if (hasValue(scrnMsg.dsAcctNum_H)) {
            dsAccFlag = true;
            inputCnt++;
        }
        if (inputCnt > 1) {
            if (combFlag) {
                if (hasValue(scrnMsg.mdlNm_C)) {
                    scrnMsg.mdlNm_C.setErrorInfo(1, NSBM0153E);
                    return;
                }
                if (hasValue(scrnMsg.serNum_C)) {
                    scrnMsg.serNum_C.setErrorInfo(1, NSBM0153E);
                    return;
                }
            }
            if (mdlFlag) {
                scrnMsg.mdlNm_H.setErrorInfo(1, NSBM0153E);
                return;
            }
            if (dsAccFlag) {
                scrnMsg.dsAcctNum_H.setErrorInfo(1, NSBM0153E);
                return;
            }
        }
    }

    /**
     * set Parameter for Model Name(NMAL6050)
     * @param scrnMsg NSBL0260BMsg
     * @param mdlNm String
     */
    public static void setParamMdlNm(NSBL0260BMsg scrnMsg, String mdlNm) {

        scrnMsg.xxTblNm.setValue("MDL_NM_V");
        scrnMsg.xxTblCdColNm.setValue("T_MDL_ID");
        scrnMsg.xxTblNmColNm.setValue("T_MDL_NM");
        scrnMsg.xxTblSortColNm.setValue("T_MDL_ID");
        scrnMsg.xxScrNm.setValue("Model Name Popup");
        scrnMsg.xxHdrCdLbNm.setValue("Model ID");
        scrnMsg.xxHdrNmLbNm.setValue("Model Name");
        scrnMsg.xxDtlCdLbNm.setValue("Model ID");
        scrnMsg.xxDtlNmLbNm.setValue("Model Name");
        if (hasValue(mdlNm)) {
            scrnMsg.xxCondNm.setValue(mdlNm);
        } else {
            scrnMsg.xxCondNm.clear();
        }
        scrnMsg.xxCondCd.clear();
    }

    /**
     * get Parameter for NMAL6050
     * @param scrnMsg NSBL0260BMsg
     * @return Parameter for NMAL6050
     */
    public static Object[] getParamNMAL6050(NSBL0260BMsg scrnMsg) {
        Object[] params = new Object[PRMS_11];
        params[PRMS_00] = scrnMsg.xxTblNm;
        params[PRMS_01] = scrnMsg.xxTblCdColNm;
        params[PRMS_02] = scrnMsg.xxTblNmColNm;
        params[PRMS_03] = scrnMsg.xxTblSortColNm;
        params[PRMS_04] = scrnMsg.xxScrNm;
        params[PRMS_05] = scrnMsg.xxHdrCdLbNm;
        params[PRMS_06] = scrnMsg.xxHdrNmLbNm;
        params[PRMS_07] = scrnMsg.xxDtlCdLbNm;
        params[PRMS_08] = scrnMsg.xxDtlNmLbNm;
        params[PRMS_09] = scrnMsg.xxCondCd;
        params[PRMS_10] = scrnMsg.xxCondNm;
        return params;
    }

    /**
     * get Parameter for NSAL1240
     * @param scrnMsg NSBL0260BMsg
     * @param serNum EZDBStringItem
     * @return Parameter for NSAL1240
     */
    public static Object[] setConfigSearchPopUpInputParam(NSBL0260BMsg scrnMsg, EZDBStringItem serNum) {

        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.O.setValidCount(0);
        Object[] params = new Object[PARAM_LENGTH_NSAL1240];
        setValue(scrnMsg.xxPopPrm_00, "01");
        setValue(scrnMsg.xxPopPrm_02, serNum);
        setValue(scrnMsg.xxPopPrm_10, ZYPConstant.FLG_ON_Y);
        setValue(scrnMsg.xxPopPrm_11, "01");
        setValue(scrnMsg.xxPopPrm_12, ZYPConstant.FLG_OFF_N);

        params[PRMS_00] = scrnMsg.xxPopPrm_00;
        params[PRMS_02] = scrnMsg.xxPopPrm_02;
        params[PRMS_10] = scrnMsg.xxPopPrm_10;
        params[PRMS_11] = scrnMsg.xxPopPrm_11;
        params[PRMS_12] = scrnMsg.xxPopPrm_12;
        // START 2018/06/07 T.Ogura [QC#22395,ADD]
        params[PRMS_26] = scrnMsg.xxPopPrm_26;
        // END   2018/06/07 T.Ogura [QC#22395,ADD]
        params[PRMS_30] = scrnMsg.O;
        return params;
    }


    /**
     * clear PopupData For ScrnMsg
     * @param scrnMsg NSBL0260BMsg
     */
    public static final void clearPopupDataForScrnMsg(NSBL0260BMsg scrnMsg) {
        scrnMsg.xxPopPrm_SE.clear();
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
    }

    /**
     * addCheckItems
     * @param scrnMsg NSBL0260BMsg
     */
    public static void addCheckItems(NSBL0260BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsMdlEolDt_AN);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsMdlEolDt_AS);
            if (!isMandatoryCheckCheckDsMdlEolDt(scrnMsg.A.no(i).dsMdlEolDt_AN.getValue(), scrnMsg.A.no(i).dsMdlEolDt_AS.getValue())) {
                scrnMsg.A.no(i).dsMdlEolDt_AN.setErrorInfo(1, ZZM9000E, new String[] {"No New Contracts After Date or Stop Service Date" });
                scrnMsg.A.no(i).dsMdlEolDt_AS.setErrorInfo(1, ZZM9000E, new String[] {"No New Contracts After Date or Stop Service Date" });
            }
            if (!isCheckDsMdlEolDt(scrnMsg.A.no(i).dsMdlEolDt_AN.getValue(), scrnMsg.A.no(i).dsMdlEolDt_AS.getValue())) {
                scrnMsg.A.no(i).dsMdlEolDt_AN.setErrorInfo(1, NSBM0155E);
                scrnMsg.A.no(i).dsMdlEolDt_AS.setErrorInfo(1, NSBM0155E);

            }
        }
    }

    private static boolean isMandatoryCheckCheckDsMdlEolDt(String dsMdlEolDtAn, String dsMdlEolDtAs) {
        if (!hasValue(dsMdlEolDtAn) && !hasValue(dsMdlEolDtAs)) {
            return false;
        }
        return true;
    }

    private static boolean isCheckDsMdlEolDt(String dsMdlEolDtAn, String dsMdlEolDtAs) {
        if (hasValue(dsMdlEolDtAn) && hasValue(dsMdlEolDtAs)) {
            if (dsMdlEolDtAs.compareTo(dsMdlEolDtAn) < 0) {
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSBL0260BMsg
     */
    public static void commonAddCheckItem(NSBL0260BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsMdlEolDt_AN);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsMdlEolDt_AS);
        }
    }
}
