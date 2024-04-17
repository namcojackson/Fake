/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6460CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/24/2010   Fujitsu         N.Sakamoto      Create          N/A     
 * 2013/08/06   Fujitsu         K.Kimura        Update          WDS#1458 Installment Invoice modification
 * 2013/08/13   Fujitsu         K.Kimura        Update          WDS#1554 Multi Exchange Rate modification                                                    
 *</pre>
 */
package business.servlet.NMAL6460.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6460.NMAL6460BMsg;
import business.servlet.NMAL6460.NMAL6460Bean;
import business.servlet.NMAL6460.NMAL6460_ABMsg;
import business.servlet.NMAL6460.constant.NMAL6460Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6460CommonLogic
 * <dd>The class explanation: Common Logic for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sakamoto
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/19   Hitachi         T.Aoyagi        Update          QC#8569
 */
public class NMAL6460CommonLogic implements NMAL6460Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL6460BMsg scrnMsg, String userId) {
        
        initCommonButton(handler);
        setTblBackColor(scrnMsg);
        controlScreenDetailFields(handler, scrnMsg, userId);
        controlButtonByAuthority(handler, userId, scrnMsg);
        
    }
    
    /**
     * <pre>
     * The screen item is set.
     * </pre>
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void setControlScreen(EZDCommonHandler handler, NMAL6460BMsg scrnMsg, String userId) {
        
        setTblBackColor(scrnMsg);
        controlScreenFields(handler, scrnMsg, userId);
        controlButtonByAuthority(handler, userId, scrnMsg);
        
    }
    
    /**
     * Method name: initCommonButton
     * <dd>The method explanation: init Common Button Control.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0]    , BTN_CMN_SAVE[1]    , BTN_CMN_SAVE[2]    , 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0]  , BTN_CMN_SUBMIT[1]  , BTN_CMN_SUBMIT[2]  , 1, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0]   , BTN_CMN_APPLY[1]   , BTN_CMN_APPLY[2]   , 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0] , BTN_CMN_APPROVE[1] , BTN_CMN_APPROVE[2] , 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0]  , BTN_CMN_REJECT[1]  , BTN_CMN_REJECT[2]  , 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0]  , BTN_CMN_DELETE[1]  , BTN_CMN_DELETE[2]  , 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0]   , BTN_CMN_CLEAR[1]   , BTN_CMN_CLEAR[2]   , 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0]   , BTN_CMN_RESET[1]   , BTN_CMN_RESET[2]   , 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0]  , BTN_CMN_RETURN[1]  , BTN_CMN_RETURN[2]  , 1, null);

    }

    /**
     * Buttons's activity set by user authority
     * @param handler
     * @param scrnMsg
     */
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NMAL6460BMsg scrnMsg) {
        
        if (!hasUpdateAuthority(userId)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_PAYMENT_TERM[0], false);
            handler.setButtonEnabled(BTN_SEARCH_PAYMENT_TERM_NAME[0], false);
            handler.setButtonEnabled(BTN_OPEN_WIN_CASH_DISCOUNT_TERM[0], false);
        } else {
            if (scrnMsg.xxPageShowOfNum.getValueInt() < MAX_RECORD_COUNT) {
                handler.setButtonEnabled(BTN_INSERT_ROW[0], true);
            } else {
                handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
            }
        }
    }
    
    /**
     * Control screen fields
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL6460BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }
    
    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NMAL6460BMsg scrnMsg) {

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
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_UPDATE})) {
            return true;
        }
        return false;
    }
    
    /**
     * check detail item.
     * @param scrnMsg
     */
    public static final void checkDetail(NMAL6460BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6460Bean.pmtTermCashDiscSortNum_A1
                                            , NMAL6460Bean.pmtTermCashDiscCd_A1
                                            , NMAL6460Bean.pmtTermCashDiscNm_A1
                                            , NMAL6460Bean.pmtTermCashDiscDescTxt_A1
                                            , NMAL6460Bean.pmtTermCd_A1
                                            , NMAL6460Bean.pmtTermSendFaxFlg_A1
                                            , NMAL6460Bean.istlPmtTermFlg_A1
                                            , NMAL6460Bean.actlExchRateTpNum_A1
                                            , NMAL6460Bean.cashDiscTermCd_A1
                                            , NMAL6460Bean.ediCashDiscDueAot_A1
                                            , NMAL6460Bean.ediCashDiscPct_A1
                                            // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
                                            , NMAL6460Bean.pmtTermConslDueDay_A1
                                            , NMAL6460Bean.pmtTermConslAddMthNum_A1
                                            // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
                                            }
                                , 1);
        //for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        //    String pmtTermCd_A1 = scrnMsg.A.no(i).pmtTermCd_A1.getValue();
        //    if (hasValue(pmtTermCd_A1)) {
        //        if (pmtTermCd_A1.length() < CODE_LENGTH) {
        //            scrnMsg.A.no(i).pmtTermCd_A1.setErrorInfo(1
        //                    , MESSAGE_ID.NMAM0181E.toString()
        //                    , new String[]{Integer.toString(CODE_LENGTH)});
        //        }
        //    }
        //}
    }

    /**
     * check detail item for doProcess
     * @param scrnMsg
     */
    public static final void addCheckItemForDoProcess(NMAL6460BMsg scrnMsg) {
    
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6460Bean.pmtTermCashDiscSortNum_A1
                                            , NMAL6460Bean.pmtTermCashDiscCd_A1
                                            , NMAL6460Bean.pmtTermCd_A1
                                            , NMAL6460Bean.cashDiscTermCd_A1
                                            // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
                                            , NMAL6460Bean.istlPmtTermFlg_A1
                                            , NMAL6460Bean.pmtTermConslDueDay_A1
                                            , NMAL6460Bean.pmtTermConslAddMthNum_A1
                                            // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
                                            }
                                , 1);
    }
    
    public static final void clearNMAL6050Params(NMAL6460BMsg scrnMsg) {
        
        scrnMsg.xxTblNm.clear();
        scrnMsg.xxTblCdColNm.clear();
        scrnMsg.xxTblNmColNm.clear();
        scrnMsg.xxTblSortColNm.clear();
        scrnMsg.xxScrNm.clear();
        scrnMsg.xxHdrCdLbNm.clear();
        scrnMsg.xxHdrNmLbNm.clear();
        scrnMsg.xxDtlCdLbNm.clear();
        scrnMsg.xxDtlNmLbNm.clear();
        
    }
    
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL6460BMsg scrnMsg, String userId) {
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL6460_ABMsg detail = scrnMsg.A.no(i);
            detail.pmtTermNm_A1.setInputProtected(true);
            
            if (hasUpdateAuthority(userId)) {
                if (hasValue(detail.pmtTermCashDiscCd_A1) && hasValue(detail.ezUpTime_A1)) {
                    detail.pmtTermCashDiscCd_A1.setInputProtected(true);
                    detail.xxChkBox_A1.setInputProtected(true);
                } else {
                    detail.pmtTermCashDiscCd_A1.setInputProtected(false);
                    detail.xxChkBox_A1.setInputProtected(false);
                }
                // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(detail.pmtTermConslFlg_A1.getValue())) {
                    detail.pmtTermConslLastDomFlg_A1.setInputProtected(false);
                    detail.pmtTermConslDueDay_A1.setInputProtected(false);
                    detail.pmtTermConslAddMthNum_A1.setInputProtected(false);
                } else {
                    detail.pmtTermConslLastDomFlg_A1.setInputProtected(true);
                    detail.pmtTermConslDueDay_A1.setInputProtected(true);
                    detail.pmtTermConslAddMthNum_A1.setInputProtected(true);
                }
                // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
            } else {
                detail.pmtTermCashDiscSortNum_A1.setInputProtected(true);
                detail.pmtTermCashDiscCd_A1.setInputProtected(true);
                detail.pmtTermCashDiscNm_A1.setInputProtected(true);
                detail.pmtTermCashDiscDescTxt_A1.setInputProtected(true);
                detail.pmtTermCd_A1.setInputProtected(true);
                detail.cashDiscTermCd_A1.setInputProtected(true);
                detail.ediCashDiscDueAot_A1.setInputProtected(true);
                detail.ediCashDiscPct_A1.setInputProtected(true);
                detail.pmtTermSendFaxFlg_A1.setInputProtected(true);
                detail.istlPmtTermFlg_A1.setInputProtected(true);
                detail.actlExchRateTpNum_A1.setInputProtected(true);
                detail.xxChkBox_A1.setInputProtected(true);
                // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
                detail.pmtTermConslFlg_A1.setInputProtected(true);
                detail.pmtTermConslLastDomFlg_A1.setInputProtected(true);
                detail.pmtTermConslDueDay_A1.setInputProtected(true);
                detail.pmtTermConslAddMthNum_A1.setInputProtected(true);
                detail.pmtCashFlg_A1.setInputProtected(true);
                detail.pmtCcFlg_A1.setInputProtected(true);
                // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
            }
        }
    }

}
