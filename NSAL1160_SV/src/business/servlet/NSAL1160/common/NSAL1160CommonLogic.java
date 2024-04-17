/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL1160.common;

import static business.servlet.NSAL1160.constant.NSAL1160Constant.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSAL1160.NSAL1160CMsg;
import business.servlet.NSAL1160.NSAL1160BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/21/2016   Hitachi         J.Kim           Create          N/A
 * 03/30/2016   Hitachi         A.Kohinata      Update          QC#6066
 *</pre>
 */
public class NSAL1160CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * SetArgForSubScreen
     * @param arg Serializable
     * @param scrnMsg NSAL1160BMsg
     */
    public static void setArgForSubScreen(Serializable arg, NSAL1160BMsg scrnMsg) {

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            if (params[0] != null) {
                EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
                scrnMsg.dsContrPk_P.setValue(param0.getValue());
            }

            if (params[1] != null) {
                EZDBStringItem param01 = (EZDBStringItem) params[1];
                scrnMsg.shipToCustAcctCd_P.setValue(param01.getValue());
            }

            if (params[2] != null) {
                EZDBStringItem param02 = (EZDBStringItem) params[2];
                scrnMsg.svcPgmMdseCd_P.setValue(param02.getValue());
            }
        }
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NSAL1160CMsg
     */
    public static NSAL1160CMsg setRequestData_SearchCommon() {

        NSAL1160CMsg bizMsg = new NSAL1160CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * getTableId
     * @param ctx EZDApplicationContext
     * @return String
     */
    public static String getTableId(EZDApplicationContext ctx) {

        String rtnVal = "";
        String tblNm = (String) (((HttpDispatchContext) ctx.getDispatchContext()).getParameters().get("xxPagenationTableNm"))[0];
        if (TABLE_A.equals(tblNm)) {
            rtnVal = TABLE_A;
        } else if (TABLE_B.equals(tblNm)) {
            rtnVal = TABLE_B;
        } else {
            rtnVal = TABLE_C;
        }
        return rtnVal;
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1160BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSAL1160BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        // Set Button Property
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN);
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }

        screenControlByFuncId(handler, scrnMsg);
        controlScreenDetailFields(handler, scrnMsg);
        setTableBGColor(scrnMsg);
    }

    /**
     * Control ScreenDetail Fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1160BMsg
     */
    public static void controlScreenDetailFields(EZDCommonHandler handler, NSAL1160BMsg scrnMsg) {

        for (int aIdx = 0; aIdx < scrnMsg.A.getValidCount(); aIdx++) {
            scrnMsg.A.no(aIdx).xxScrItem61Txt_A.setInputProtected(true);
            scrnMsg.A.no(aIdx).abuseActDescTxt.setInputProtected(true);
            scrnMsg.A.no(aIdx).abuseActCmntTxt.setInputProtected(true);
        }

        for (int bIdx = 0; bIdx < scrnMsg.B.getValidCount(); bIdx++) {
            scrnMsg.B.no(bIdx).svcMemoTpDescTxt.setInputProtected(true);
            scrnMsg.B.no(bIdx).svcCmntTxt.setInputProtected(true);
        }

        for (int cIdx = 0; cIdx < scrnMsg.C.getValidCount(); cIdx++) {
            scrnMsg.C.no(cIdx).abuseOvwrtRsnDescTxt.setInputProtected(true);
            scrnMsg.C.no(cIdx).xxScrItem61Txt_C.setInputProtected(true);
        }
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1160BMsg
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NSAL1160BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NSAL1160). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (funcIdList.contains(FUNC_ID_T010)) {
            enableButtons(handler, RESET[0], RETURN[0]);
        }
    }

    /**
     * Activate buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm String
     */
    public static void enableButtons(EZDCommonHandler handler, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, true);
        }
    }

    private static void setTableBGColor(NSAL1160BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.clearRowsBG("B", scrnMsg.B);
        tblColor.clearRowsBG("C", scrnMsg.C);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
        if (scrnMsg.B.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
        if (scrnMsg.C.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("C", scrnMsg.C);
        }
    }
}
