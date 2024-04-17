/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6550CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6550.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL6550.NMAL6550CMsg;
import business.servlet.NMAL6550.NMAL6550BMsg;
import business.servlet.NMAL6550.NMAL6550Bean;
import business.servlet.NMAL6550.NMAL6550_ABMsg;
import business.servlet.NMAL6550.NMAL6550_ABMsgArray;
import business.servlet.NMAL6550.constant.NMAL6550Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6550CommonLogic
 * <dd>The class explanation: Common Logic for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author H.Nagashima
 */
public class NMAL6550CommonLogic implements NMAL6550Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL6550BMsg scrnMsg, String userId) {
        
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
    public static final void setControlScreen(EZDCommonHandler handler, NMAL6550BMsg scrnMsg, String userId) {
        
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
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NMAL6550BMsg scrnMsg) {
        
        if (!hasUpdateAuthority(userId)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
            handler.setButtonEnabled(BTN_DELETE_ROW[0], false);
            handler.setButtonEnabled(BTN_INSERT_ROW[0], false);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                handler.setButtonEnabled(BTN_OPENWIN_CTRY[0], i, false);
                handler.setButtonEnabled(BTN_OPENWIN_CCY[0], i, false);
                handler.setButtonEnabled(BTN_OPENWIN_COA_AFFL[0], i, false);
                handler.setButtonEnabled(BTN_OPENWIN_ACCT[0], i, false);
                handler.setButtonEnabled(BTN_SEARCH_COA_AFFL[0], i, false);
                handler.setButtonEnabled(BTN_SEARCH_ACCT[0], i, false);
            }
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
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL6550BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }
    
    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NMAL6550BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
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
    public static final void checkDetail(NMAL6550BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6550Bean.glblCmpySortNum_A1
                                            , NMAL6550Bean.glblCmpyCd_A1
                                            , NMAL6550Bean.glblCmpyNm_A1
                                            , NMAL6550Bean.glblCmpyDescTxt_A1
                                            }
                                , 1);

    }

    /**
     * check detail item for doProcess
     * @param scrnMsg
     */
    public static final void addCheckItemForDoProcess(NMAL6550BMsg scrnMsg) {
    
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6550Bean.glblCmpySortNum_A1
                                            , NMAL6550Bean.glblCmpyCd_A1
                                            , NMAL6550Bean.ctryCd_A1
                                            , NMAL6550Bean.stdCcyCd_A1
                                            , NMAL6550Bean.coaAfflCd_A1
                                            , NMAL6550Bean.acctCd_A1
                                            }
                                , 1);
    }
    
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL6550BMsg scrnMsg, String userId) {
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL6550_ABMsg detail = scrnMsg.A.no(i);
            if (hasUpdateAuthority(userId)) {
                if (hasValue(detail.glblCmpyCd_A1) && hasValue(detail.ezUpTime_A1)) {
                    detail.glblCmpyCd_A1.setInputProtected(true);
                    detail.xxChkBox_A1.setInputProtected(true);
                } else {
                    detail.glblCmpyCd_A1.setInputProtected(false);
                    detail.xxChkBox_A1.setInputProtected(false);
                }
            } else {
                detail.xxChkBox_A1.setInputProtected(true);
                detail.glblCmpySortNum_A1.setInputProtected(true);
                detail.glblCmpyCd_A1.setInputProtected(true);
                detail.glblCmpyNm_A1.setInputProtected(true);
                detail.glblCmpyDescTxt_A1.setInputProtected(true);
                detail.ctryCd_A1.setInputProtected(true);
                detail.stdCcyCd_A1.setInputProtected(true);
                detail.coaAfflCd_A1.setInputProtected(true);
                detail.acctCd_A1.setInputProtected(true);
            }
            detail.coaAfflNm_A1.setInputProtected(true);
            detail.imptInvCnsgnNm_A1.setInputProtected(true);

        }
    }
    
    public static NMAL6550CMsg setBizFunction(String funcCode) {

        NMAL6550CMsg bizMsg = new NMAL6550CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode(funcCode);

        return bizMsg;
    }

    public static boolean isClosedEvent(String lastGuard) {
        return "CMN_Close".toLowerCase().equals(lastGuard.toLowerCase());
    }

    
    public static final void setFocusErrItem(EZDCommonHandler handler, NMAL6550BMsg scrnMsg) {

        NMAL6550_ABMsgArray msgArray = scrnMsg.A;
        for (int i = 0; i < msgArray.getValidCount(); i++) {
            EZDBStringItem ctryItem = msgArray.no(i).ctryCd_A1;
            EZDBStringItem ccyItem = msgArray.no(i).stdCcyCd_A1;
            EZDBStringItem coaAfflItem = msgArray.no(i).coaAfflCd_A1;
            EZDBStringItem acctItem = msgArray.no(i).acctCd_A1;
       
            if (ctryItem.isError()) {
                scrnMsg.setFocusItem(ctryItem);
                break;
            } else if(ccyItem.isError()){
                scrnMsg.setFocusItem(ccyItem);
                break;
            } else if(coaAfflItem.isError()){
                scrnMsg.setFocusItem(coaAfflItem);
                break;
            } else if(acctItem.isError()){
                scrnMsg.setFocusItem(acctItem);
                break;
            }
        }

    }

}
