/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1400.common;

import static business.servlet.NSAL1400.constant.NSAL1400Constant.*;
import java.util.List;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import business.servlet.NSAL1400.NSAL1400BMsg;
import business.servlet.NSAL1400.NSAL1400Bean;
import business.servlet.NSAL1400.NSAL1400_ABMsg;
import parts.servletcommon.EZDCommonHandler;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAVE_BTN_NM, "", BTN_CMN_SAVE_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT_BTN_NM, BTN_CMN_SUBMIT_EVENT_NM, BTN_CMN_SUBMIT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY_BTN_NM, "", BTN_CMN_APPLY_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE_BTN_NM, "", BTN_CMN_APPROVE_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT_BTN_NM, "", BTN_CMN_REJECT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD_BTN_NM, "", BTN_CMN_DOWNLOAD_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE_BTN_NM, "", BTN_CMN_DELETE_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR_BTN_NM, BTN_CMN_CLEAR_EVENT_NM, BTN_CMN_CLEAR_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RESET_BTN_NM, "", BTN_CMN_RESET_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN_BTN_NM, BTN_CMN_RETURN_EVENT_NM, BTN_CMN_RETURN_LABEL, 0, null);
    }

    /**
     * initialize
     * @param handler
     * @param scrnMsg
     */
    public static void initialize(EZDCommonHandler handler, NSAL1400BMsg scrnMsg) {
        List<String> funcIdList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NSAL1400). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        initCmnBtnProp(handler);
        screenControlByFuncId(handler, scrnMsg, funcIdList);
        controlInitField(handler, scrnMsg, funcIdList);
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler
     * @param scrnMsg
     * @param funcIdList
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NSAL1400BMsg scrnMsg, List<String> funcIdList) {
        // button
        handler.setButtonEnabled(BTN_ADD, false);
        handler.setButtonEnabled(BTN_DELETE, false);

        if (funcIdList.contains(FUNC_ID_T010)) {
            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_ADD, true);
                handler.setButtonEnabled(BTN_DELETE, true);
            } else {
                handler.setButtonEnabled(BTN_ADD, true);
                handler.setButtonEnabled(BTN_DELETE, false);
            }
            handler.setButtonEnabled(BTN_CMN_SUBMIT_BTN_NM, true);
        }
        handler.setButtonEnabled(BTN_CMN_CLEAR_BTN_NM, true);
        handler.setButtonEnabled(BTN_CMN_RETURN_BTN_NM, true);
    }

    /**
     * control All Field
     * @param handler
     * @param scrnMsg
     * @param funcIdList
     */
    public static void controlInitField(EZDCommonHandler handler, NSAL1400BMsg scrnMsg, List<String> funcIdList) {
        // field
        scrnMsg.dsAcctNm_LK.setInputProtected(false);
        scrnMsg.dsAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);

        // lists
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1400_ABMsg abMsg = scrnMsg.A.no(i);
            abMsg.xxChkBox_A1.setInputProtected(false);
            abMsg.svcLineBizCd_A.setInputProtected(false);
            abMsg.dsAcctNum_A.setInputProtected(false);
            if (funcIdList.contains(FUNC_ID_T010)) {
                handler.setButtonEnabled(BTN_OPEN_CUST_LINE, i, true);
                handler.setButtonEnabled(BTN_OPEN_RESRC, i, true);
            } else {
                handler.setButtonEnabled(BTN_OPEN_CUST_LINE, i, false);
                handler.setButtonEnabled(BTN_OPEN_RESRC, i, false);
            }
            abMsg.dsAcctNm_A.setInputProtected(true);
            abMsg.contrAdminPsnCd_A.setInputProtected(false);
            
            abMsg.xxCustPsnNm_A.setInputProtected(true);
            abMsg.xxChkBox_A2.setInputProtected(false);
            abMsg.effFromDt_A.setInputProtected(false);
            abMsg.effThruDt_A.setInputProtected(false);
        }
    }

    /**
     * control All Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL1400BMsg
     */
    public static void controlField(EZDCommonHandler handler, NSAL1400BMsg scrnMsg) {
        // button
        if (MAX_ROW <= scrnMsg.xxPageShowOfNum_A.getValueInt()) {
            handler.setButtonEnabled(BTN_ADD, false);
        } else {
            handler.setButtonEnabled(BTN_ADD, true);
        }
        if (0 == scrnMsg.A.getValidCount()) {
            handler.setButtonEnabled(BTN_DELETE, false);
        } else {
            handler.setButtonEnabled(BTN_DELETE, true);
        }

        // Table Color
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID_00, scrnMsg);
        tblColor.clearRowsBG(NSAL1400Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NSAL1400Bean.A, scrnMsg.A, 1);
    }

    /**
     * Add CheckItem All Detail
     * @param scrnMsg NSAL1400BMsg
     */
    public static void addCheckDetailItem(NSAL1400BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1400_ABMsg aBMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(aBMsg.xxChkBox_A1);
            scrnMsg.addCheckItem(aBMsg.svcLineBizCd_A);
            scrnMsg.addCheckItem(aBMsg.dsAcctNum_A);
            scrnMsg.addCheckItem(aBMsg.contrAdminPsnCd_A);
            scrnMsg.addCheckItem(aBMsg.xxChkBox_A2);
            scrnMsg.addCheckItem(aBMsg.effFromDt_A);
            scrnMsg.addCheckItem(aBMsg.effThruDt_A);
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * clearPopupParams
     * @param scrnMsg
     */
    public static void clearPopupParams(NSAL1400BMsg scrnMsg) {
        scrnMsg.eventNm_P.clear();
        scrnMsg.xxRowNum_P.clear();
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
        scrnMsg.xxPopPrm_24.clear();
        ZYPTableUtil.clear(scrnMsg.Z);
    }
}
