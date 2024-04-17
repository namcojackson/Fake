/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6450CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/24/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 * 2013/08/14   Fujitsu         K.Kimura        Update          WDS#1458 Installment Invoice modification
 *</pre>
 */
package business.servlet.NMAL6450.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6450.NMAL6450BMsg;
import business.servlet.NMAL6450.NMAL6450Bean;
import business.servlet.NMAL6450.NMAL6450_ABMsg;
import business.servlet.NMAL6450.constant.NMAL6450Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6450CommonLogic
 * <dd>The class explanation: Common Logic for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sakamoto
 */
public class NMAL6450CommonLogic implements NMAL6450Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL6450BMsg scrnMsg, String userId) {
        
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
    public static final void setControlScreen(EZDCommonHandler handler, NMAL6450BMsg scrnMsg, String userId) {
        
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
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NMAL6450BMsg scrnMsg) {
        
        if (!hasUpdateAuthority(userId)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
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
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL6450BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }
    
    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NMAL6450BMsg scrnMsg) {

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
    public static final void checkDetail(NMAL6450BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6450Bean.pmtTermSortNum_A1
                                            , NMAL6450Bean.pmtTermCd_A1
                                            , NMAL6450Bean.pmtTermNm_A1
                                            , NMAL6450Bean.pmtTermDescTxt_A1
                                            , NMAL6450Bean.pmtTermAot_A1
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
    public static final void addCheckItemForDoProcess(NMAL6450BMsg scrnMsg) {
    
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6450Bean.pmtTermSortNum_A1
                                            , NMAL6450Bean.pmtTermCd_A1
                                            }
                                , 1);
    }
    
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL6450BMsg scrnMsg, String userId) {
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL6450_ABMsg detail = scrnMsg.A.no(i);
            if (hasUpdateAuthority(userId)) {
                if (hasValue(detail.pmtTermCd_A1) && hasValue(detail.ezUpTime_A1)) {
                    detail.pmtTermCd_A1.setInputProtected(true);
                    detail.xxChkBox_A1.setInputProtected(true);
                } else {
                    detail.pmtTermCd_A1.setInputProtected(false);
                    detail.xxChkBox_A1.setInputProtected(false);
                }
            } else {
                detail.pmtTermSortNum_A1.setInputProtected(true);
                detail.pmtTermCd_A1.setInputProtected(true);
                detail.pmtTermNm_A1.setInputProtected(true);
                detail.pmtTermDescTxt_A1.setInputProtected(true);
                detail.pmtTermAot_A1.setInputProtected(true);
                detail.xxChkBox_A1.setInputProtected(true);
            }
        }
    }

}
