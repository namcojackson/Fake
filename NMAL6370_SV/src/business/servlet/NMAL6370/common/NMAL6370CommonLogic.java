/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6370CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/24/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 *</pre>
 */
package business.servlet.NMAL6370.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6370.NMAL6370BMsg;
import business.servlet.NMAL6370.NMAL6370Bean;
import business.servlet.NMAL6370.NMAL6370_ABMsg;
import business.servlet.NMAL6370.constant.NMAL6370Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6370CommonLogic
 * <dd>The class explanation: Common Logic for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sakamoto
 */
public class NMAL6370CommonLogic implements NMAL6370Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL6370BMsg scrnMsg, String userId) {
        
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
    public static final void setControlScreen(EZDCommonHandler handler, NMAL6370BMsg scrnMsg, String userId) {
        
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
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NMAL6370BMsg scrnMsg) {
        
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
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL6370BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }
    
    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NMAL6370BMsg scrnMsg) {

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
    public static final void checkDetail(NMAL6370BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6370Bean.ctacTpSortNum_A1
                                            , NMAL6370Bean.ctacTpCd_A1
                                            , NMAL6370Bean.ctacTpNm_A1
                                            , NMAL6370Bean.ctacTpDescTxt_A1 
                                            }
                                , 1);

    }

    /**
     * check detail item for doProcess
     * @param scrnMsg
     */
    public static final void addCheckItemForDoProcess(NMAL6370BMsg scrnMsg) {
    
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6370Bean.ctacTpSortNum_A1
                                            , NMAL6370Bean.ctacTpCd_A1
                                            }
                                , 1);
    }
    
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL6370BMsg scrnMsg, String userId) {
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL6370_ABMsg detail = scrnMsg.A.no(i);
            if (hasUpdateAuthority(userId)) {
                if (hasValue(detail.ctacTpCd_A1) && hasValue(detail.ezUpTime_A1)) {
                    detail.ctacTpCd_A1.setInputProtected(true);
                    detail.xxChkBox_A1.setInputProtected(true);
                } else {
                    detail.ctacTpCd_A1.setInputProtected(false);
                    detail.xxChkBox_A1.setInputProtected(false);
                }
            } else {
                detail.ctacTpSortNum_A1.setInputProtected(true);
                detail.ctacTpCd_A1.setInputProtected(true);
                detail.ctacTpNm_A1.setInputProtected(true);
                detail.ctacTpDescTxt_A1.setInputProtected(true);
                detail.techFlg_A1.setInputProtected(true);
                detail.actvFlg_A1.setInputProtected(true);
                detail.execFlg_A1.setInputProtected(true);
                detail.svcIndFlg_A1.setInputProtected(true);
                detail.xxChkBox_A1.setInputProtected(true);
            }
        }
    }

}
