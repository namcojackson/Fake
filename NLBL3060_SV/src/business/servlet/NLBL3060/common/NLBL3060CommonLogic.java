/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.servlet.NLBL3060.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3060.NLBL3060BMsg;
import business.servlet.NLBL3060.NLBL3060Bean;
import business.servlet.NLBL3060.NLBL3060_ABMsg;
import business.servlet.NLBL3060.constant.NLBL3060Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          N/A
 * 2023/04/13   Hitachi         T.Kuroda        Update          QC#61166
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060CommonLogic implements NLBL3060Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * 
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NLBL3060BMsg scrnMsg, String userId) {

        initCommonButton(handler);
        setTblBackColor(scrnMsg);
        controlScreenDetailFields(handler, scrnMsg, userId);
        controlButtonByAuthority(handler, userId, scrnMsg, ZYPConstant.FLG_OFF_N);

    }

    /**
     * <pre>
     * The screen item is set.
     * </pre>
     * 
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void setControlScreen(EZDCommonHandler handler, NLBL3060BMsg scrnMsg, String userId, String submitBtnFlg) {

        setTblBackColor(scrnMsg);
        controlScreenFields(handler, scrnMsg, userId);
        controlButtonByAuthority(handler, userId, scrnMsg, submitBtnFlg);

    }

    /**
     * Method name: initCommonButton
     * <dd>The method explanation: init Common Button Control.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        // START 2023/04/13 T.Kuroda [QC#61166, MOD]
//        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        // END   2023/04/13 T.Kuroda [QC#61166, MOD]
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Buttons's activity set by user authority
     * @param handler
     * @param scrnMsg
     */
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NLBL3060BMsg scrnMsg, String submitBtnFlg) {

        if (!hasUpdateAuthority(userId)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
            // START 2023/04/13 T.Kuroda [QC#61166, ADD]
            handler.setButtonEnabled(BTN_CHK_ALL[0], false);
            handler.setButtonEnabled(BTN_UNCHK_ALL[0], false);
            handler.setButtonEnabled(BTN_UP_CSV[0], false);
            scrnMsg.xxFileData_UP.setInputProtected(true);
            // END   2023/04/13 T.Kuroda [QC#61166, ADD]

        } else {
//            if (scrnMsg.xxPageShowOfNum.getValueInt() < MAX_RECORD_COUNT) {
//                handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
//            } else {
//                handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
//            }
            handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
            // START 2023/04/13 T.Kuroda [QC#61166, ADD]
            handler.setButtonEnabled(BTN_UP_CSV[0], true);
            scrnMsg.xxFileData_UP.setInputProtected(false);
            // END   2023/04/13 T.Kuroda [QC#61166, ADD]
            if (ZYPCommonFunc.hasValue(submitBtnFlg) && ZYPConstant.FLG_ON_Y.equals(submitBtnFlg)) {
                handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
                // START 2023/04/13 T.Kuroda [QC#61166, ADD]
                handler.setButtonEnabled(BTN_CHK_ALL[0], true);
                handler.setButtonEnabled(BTN_UNCHK_ALL[0], true);
                // END   2023/04/13 T.Kuroda [QC#61166, ADD]
            } else if (ZYPCommonFunc.hasValue(submitBtnFlg) && ZYPConstant.FLG_OFF_N.equals(submitBtnFlg)) {
                handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
                // START 2023/04/13 T.Kuroda [QC#61166, ADD]
                handler.setButtonEnabled(BTN_CHK_ALL[0], false);
                handler.setButtonEnabled(BTN_UNCHK_ALL[0], false);
                // END   2023/04/13 T.Kuroda [QC#61166, ADD]
            }

        }
    }

    /**
     * Control screen fields
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NLBL3060BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }

    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NLBL3060BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        tblColor.setAlternateRowsBG(TABLE_ID_A, scrnMsg.A);

    }

    /**
     * Return true if userId have update authority.
     * @param userId
     * @return
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_UPDATE })) {
            return true;
        }
        return false;
    }

    /**
     * check detail item.
     * @param scrnMsg
     */
    public static final void checkDetail(NLBL3060BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[] {NLBL3060Bean.rtlWhCd_A1, NLBL3060Bean.schdCoordPsnCd_A1,  NLBL3060Bean.effFromDt_A1, NLBL3060Bean.effThruDt_A1 }, 1);
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            String rateCd = scrnMsg.A.no(i).schdCoordPsnCd_A1.getValue();
//            if (ZYPCommonFunc.hasValue(rateCd)) {
//                for (int j = 0; j < rateCd.length(); j++) {
//                    String str = rateCd.substring(j, j + 1);
//                    if (!ZYPCommonFunc.isAlphabet(str) && !ZYPCommonFunc.isNumberCheck(str)) {
//                        scrnMsg.A.no(i).schdCoordPsnCd_A1.setErrorInfo(1, MESSAGE_ID.ZZM9008E.toString(), new String[] {scrnMsg.A.no(i).schdCoordPsnCd_A1.getNameForMessage() });
//                    }
//
//                }
//            }
//        }
    }

    /**
     * check detail item for doProcess
     * @param scrnMsg
     */
    public static final void addCheckItemForDoProcess(NLBL3060BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[] {NLBL3060Bean.rtlWhCd_A1, NLBL3060Bean.schdCoordPsnCd_A1, NLBL3060Bean.effFromDt_A1, NLBL3060Bean.effThruDt_A1 }, 1);
    }

    private static final void controlScreenDetailFields(EZDCommonHandler handler, NLBL3060BMsg scrnMsg, String userId) {

        String slsDt = ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue());
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.fullPsnNm.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NLBL3060_ABMsg detail = scrnMsg.A.no(i);
            if (hasUpdateAuthority(userId)) {
                if (hasValue(detail.schdCoordPsnCd_A1) && hasValue(detail.ezUpTime_A1)) {
                    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                    detail.rtlWhCatgCd_A1.setInputProtected(true);
                    detail.physWhCd_A1.setInputProtected(true);
                    // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                    detail.rtlWhCd_A1.setInputProtected(true);
                    detail.schdCoordPsnCd_A1.setInputProtected(true);
                    detail.effFromDt_A1.setInputProtected(true);
                    if (detail.effThruDt_A2.getValue().compareTo(slsDt) < 0) {
                        detail.effThruDt_A1.setInputProtected(true);
                    } else {
                        detail.effThruDt_A1.setInputProtected(false);
                    }
                    // START 2023/04/13 T.Kuroda [QC#61166, MOD]
//                    detail.xxChkBox_A1.setInputProtected(true);
                    detail.xxChkBox_A1.setInputProtected(false);
                    // END   2023/04/13 T.Kuroda [QC#61166, MOD]
                    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                    handler.setButtonEnabled(BTN_DTL_GRP_NM[0], i, false);
                    // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                    handler.setButtonEnabled(BTN_DTL_RWH_NM[0], i, false);
                    handler.setButtonEnabled(BTN_DTL_PSN_NM[0], i, false);
                    handler.setButtonEnabled(BTN_DTL_RWH[0], i, false);
                    handler.setButtonEnabled(BTN_DTL_PSN[0], i, false);

                } else { // new
                    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                    detail.rtlWhCatgCd_A1.setInputProtected(false);
                    detail.physWhCd_A1.setInputProtected(false);
                    // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                    detail.rtlWhCd_A1.setInputProtected(false);
                    detail.schdCoordPsnCd_A1.setInputProtected(false);
                    detail.effFromDt_A1.setInputProtected(false);
                    detail.effThruDt_A1.setInputProtected(false);
                    detail.xxChkBox_A1.setInputProtected(false);
                    // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                    handler.setButtonEnabled(BTN_DTL_GRP_NM[0], i, true);
                    // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                    handler.setButtonEnabled(BTN_DTL_RWH_NM[0], i, true);
                    handler.setButtonEnabled(BTN_DTL_PSN_NM[0], i, true);
                    handler.setButtonEnabled(BTN_DTL_RWH[0], i, true);
                    handler.setButtonEnabled(BTN_DTL_PSN[0], i, true);
                }
            } else {
                // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                detail.rtlWhCatgCd_A1.setInputProtected(true);
                detail.physWhCd_A1.setInputProtected(true);
                // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                detail.rtlWhCd_A1.setInputProtected(true);
                detail.schdCoordPsnCd_A1.setInputProtected(true);
                detail.effFromDt_A1.setInputProtected(true);
                detail.effThruDt_A1.setInputProtected(true);
                detail.xxChkBox_A1.setInputProtected(true);
                // START 2023/10/18 Y.Ogura [QC#61793, ADD]
                handler.setButtonEnabled(BTN_DTL_GRP_NM[0], i, false);
                // END 2023/10/18 Y.Ogura [QC#61793, ADD]
                handler.setButtonEnabled(BTN_DTL_RWH_NM[0], i, false);
                handler.setButtonEnabled(BTN_DTL_PSN_NM[0], i, false);
                handler.setButtonEnabled(BTN_DTL_RWH[0], i, false);
                handler.setButtonEnabled(BTN_DTL_PSN[0], i, false);
            }
            detail.rtlWhNm_A1.setInputProtected(true);
            detail.fullPsnNm_A1.setInputProtected(true);
        }
    }
}
