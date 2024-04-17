/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6450CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 2013/08/14   Fujitsu         K.Kimura        Create          WDS#1458 Installment Invoice modification                                                         
 *</pre>
 */
package business.servlet.NMAL6670.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6670.NMAL6670BMsg;
import business.servlet.NMAL6670.NMAL6670Bean;
import business.servlet.NMAL6670.NMAL6670_ABMsg;
import business.servlet.NMAL6670.constant.NMAL6670Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6450CommonLogic
 * <dd>The class explanation: Common Logic for screen component.
 * <dd>Remarks:
 * @version 1.0
 */
public class NMAL6670CommonLogic implements NMAL6670Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL6670BMsg scrnMsg, String userId) {
        
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
    public static final void setControlScreen(EZDCommonHandler handler, NMAL6670BMsg scrnMsg, String userId) {
        
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
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NMAL6670BMsg scrnMsg) {
        
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
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL6670BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }
    
    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NMAL6670BMsg scrnMsg) {

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
    public static final void checkDetail(NMAL6670BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6670Bean.istlPmtTermCd_A1
                                            , NMAL6670Bean.istlPmtTermDtlNum_A1
                                            , NMAL6670Bean.istlPmtTermAot_A1
                                            , NMAL6670Bean.istlPmtTermPct_A1
                                            }
                                , 1);
    }

    /**
     * check detail item for doProcess
     * @param scrnMsg
     */
    public static final void addCheckItemForDoProcess(NMAL6670BMsg scrnMsg) {
    
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6670Bean.istlPmtTermCd_A1
                                            , NMAL6670Bean.istlPmtTermDtlNum_A1
                                            , NMAL6670Bean.istlPmtTermAot_A1
                                            , NMAL6670Bean.istlPmtTermPct_A1
                                            }
                                , 1);
    }
    
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL6670BMsg scrnMsg, String userId) {
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL6670_ABMsg detail = scrnMsg.A.no(i);
            if (hasUpdateAuthority(userId)) {
                if (hasValue(detail.istlPmtTermCd_A1) && hasValue(detail.ezUpTime_A1)) {
                    detail.istlPmtTermCd_A1.setInputProtected(true);
                    detail.istlPmtTermDtlNum_A1.setInputProtected(true);
                    detail.xxChkBox_A1.setInputProtected(true);
                } else {
                    detail.istlPmtTermCd_A1.setInputProtected(false);
                    detail.istlPmtTermDtlNum_A1.setInputProtected(false);
                    detail.xxChkBox_A1.setInputProtected(false);
                }
            } else {
                detail.istlPmtTermCd_A1.setInputProtected(true);
                detail.istlPmtTermDtlNum_A1.setInputProtected(true);
                detail.istlPmtTermAot_A1.setInputProtected(true);
                detail.istlPmtTermPct_A1.setInputProtected(true);
                detail.xxChkBox_A1.setInputProtected(true);
            }
        }
    }

}