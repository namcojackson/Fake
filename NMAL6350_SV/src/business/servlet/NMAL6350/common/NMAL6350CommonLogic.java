/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6350CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 * 09/04/2018   Fujitsu         T.Noguchi       Update          QC#28019
 *</pre>
 */
package business.servlet.NMAL6350.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL6350.NMAL6350CMsg;
import business.servlet.NMAL6350.NMAL6350BMsg;
import business.servlet.NMAL6350.NMAL6350Bean;
import business.servlet.NMAL6350.NMAL6350_ABMsg;
import business.servlet.NMAL6350.NMAL6350_ABMsgArray;
import business.servlet.NMAL6350.constant.NMAL6350Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6350CommonLogic
 * <dd>The class explanation: Common Logic for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sakamoto
 */
public class NMAL6350CommonLogic implements NMAL6350Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL6350BMsg scrnMsg, String userId) {
        
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
    public static final void setControlScreen(EZDCommonHandler handler, NMAL6350BMsg scrnMsg, String userId) {
        
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
    public static final void controlButtonByAuthority(EZDCommonHandler handler, String userId, NMAL6350BMsg scrnMsg) {
        
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
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL6350BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }
    
    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NMAL6350BMsg scrnMsg) {

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
    public static final void checkDetail(NMAL6350BMsg scrnMsg) {
        
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6350Bean.pkgUomSortNum_A1
                                            , NMAL6350Bean.pkgUomCd_A1
                                            , NMAL6350Bean.pkgUomNm_A1
                                            , NMAL6350Bean.pkgUomDescTxt_A1
                                            // 2018/09/04 QC#28019 Add Start
                                            , NMAL6350Bean.pkgUomClsCd_A1
                                            // 2018/09/04 QC#28019 Add End
                                            , NMAL6350Bean.pkgUomPkgLvlNum_A1}
                                , 1);
        checkNumeric(scrnMsg);
    }

    /**
     * check detail item for doProcess
     * @param scrnMsg
     */
    public static final void addCheckItemForDoProcess(NMAL6350BMsg scrnMsg) {
    
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6350Bean.pkgUomSortNum_A1
                                            , NMAL6350Bean.pkgUomCd_A1
                                            , NMAL6350Bean.pkgUomPkgLvlNum_A1}
                                , 1);
    }
    
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL6350BMsg scrnMsg, String userId) {
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL6350_ABMsg detail = scrnMsg.A.no(i);
            if (hasUpdateAuthority(userId)) {
                if (hasValue(detail.pkgUomCd_A1) && hasValue(detail.ezUpTime_A1)) {
                    detail.pkgUomCd_A1.setInputProtected(true);
                    detail.xxChkBox_A1.setInputProtected(true);
                } else {
                    detail.pkgUomCd_A1.setInputProtected(false);
                    detail.xxChkBox_A1.setInputProtected(false);
                }
            } else {
                detail.xxChkBox_A1.setInputProtected(true);
                detail.pkgUomSortNum_A1.setInputProtected(true);
                detail.pkgUomCd_A1.setInputProtected(true);
                detail.pkgUomNm_A1.setInputProtected(true);
                detail.pkgUomDescTxt_A1.setInputProtected(true);
                detail.pkgUomStdFlg_A1.setInputProtected(true);
                detail.pkgUomMndFlg_A1.setInputProtected(true);
                detail.pkgUomPkgLvlNum_A1.setInputProtected(true);
                // 2018/09/04 QC#28019 Add Start
                detail.pkgUomClsCd_A1.setInputProtected(true);
                // 2018/09/04 QC#28019 Add End
            }
            
        }
    }
    
    public static NMAL6350CMsg setBizFunction(String funcCode) {

        NMAL6350CMsg bizMsg = new NMAL6350CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode(funcCode);

        return bizMsg;
    }

    public static void checkNumeric(NMAL6350BMsg bizMsg) {
        
        //numeric check
        NMAL6350_ABMsgArray asMsgArray = bizMsg.A;
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            String lvlNum = asMsgArray.no(i).pkgUomPkgLvlNum_A1.getValue();
            if (hasValue(lvlNum)) {
                if (!ZYPCommonFunc.isNumberCheck(lvlNum)) {
                    asMsgArray.no(i).pkgUomPkgLvlNum_A1.setErrorInfo(1,MESSAGE_ID.ZZM9004E.toString(), new String[]{DISPLAY_NAME.PackageLevelNumber.toString()});
                }
            }
        }
    }
    
}
