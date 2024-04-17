/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0340.common;

import static business.servlet.NSBL0340.constant.NSBL0340Constant.*;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSBL0340.NSBL0340CMsg;
import business.servlet.NSBL0340.NSBL0340BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Service Task Summary
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2016/03/25   Hitachi         K.Yamada        Update          QC#6026
 * 2016/11/22   Hitachi         T.Mizuki        Update          QC#16116
 *</pre>
 */
public class NSBL0340CommonLogic implements ZYPConstant {

    /**
     * setArgForSubScreen
     * @param arg Serializable
     * @param scrnMsg NSBL0340BMsg
     */
    public static void setArgForSubScreen(Serializable arg, NSBL0340BMsg scrnMsg) {

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;

            if (params.length > 0 && params[0] instanceof EZDBStringItem) {
                EZDBStringItem param00 = (EZDBStringItem) params[0];
                scrnMsg.orgCd.setValue(param00.getValue());
            }

            if (params.length > 1 && params[1] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[1];
                scrnMsg.orgLayerNum.setValue(param01.getValue());
            }

            if (params.length > 2 && params[2] instanceof EZDBStringItem) {
                EZDBStringItem param02 = (EZDBStringItem) params[2];
                scrnMsg.svcMgrTpCd.setValue(param02.getValue());
            }

            if (params.length > 3 && params[3] instanceof EZDBStringItem) {
                EZDBStringItem param03 = (EZDBStringItem) params[3];
                scrnMsg.svcRqstDownTpCd.setValue(param03.getValue());
            }

            if (params.length > 4 && params[4] instanceof EZDBStringItem) {
                EZDBStringItem param04 = (EZDBStringItem) params[4];
                scrnMsg.svcMgrPsnCd.setValue(param04.getValue());
            }

            if (params.length > 5 && params[5] instanceof EZDBStringItem) {
                EZDBStringItem param05 = (EZDBStringItem) params[5];
                scrnMsg.svcRqstCritTpCd.setValue(param05.getValue());
            }

            if (params.length > 6 && params[6] instanceof EZDBStringItem) {
                EZDBStringItem param06 = (EZDBStringItem) params[6];
                scrnMsg.dsSvcCallTpCd.setValue(param06.getValue());
            }

            if (params.length > 7 && params[7] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param07 = (EZDBBigDecimalItem) params[7];
                scrnMsg.fsrSvcTaskStsRelnPk.setValue(param07.getValue());
            }

            if (params.length > 8 && params[8] instanceof EZDBStringItem) {
                EZDBStringItem param08 = (EZDBStringItem) params[8];
                scrnMsg.techCd.setValue(param08.getValue());
            }

            if (params.length > 9 && params[9] instanceof EZDBStringItem) {
                EZDBStringItem param09 = (EZDBStringItem) params[9];
                scrnMsg.svcCallSrcTpCd.setValue(param09.getValue());
            }
        }
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NSBL0340CMsg
     */
    public static NSBL0340CMsg setRequestData_SearchCommon() {

        NSBL0340CMsg bizMsg = new NSBL0340CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * setRequestData_UpdateCommon
     * @return bizMsg NSBL0340CMsg
     */
    public static NSBL0340CMsg setRequestData_UpdateCommon() {

        NSBL0340CMsg bizMsg = new NSBL0340CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");

        return bizMsg;
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0340BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSBL0340BMsg scrnMsg) {

        // mod start 2016/11/22 CSA QC#16116
        hasUpdateFuncId();
        // mod end 2016/11/22 CSA QC#16116
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);

        initCommonButton(handler);
        controlScreenFields(scrnMsg);
        setTableBGColor(scrnMsg);
    }

    private static final void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        // START 2016/03/25 K.Yamada [QC#6026, ADD]
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        // END 2016/03/25 K.Yamada [QC#6026, ADD]
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    private static final void controlScreenFields(NSBL0340BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).ittNum.setInputProtected(true);
            scrnMsg.A.no(i).svcTaskStsDescTxt_S.setInputProtected(true);
            scrnMsg.A.no(i).svcTaskStsDescTxt_V.setInputProtected(true);
            scrnMsg.A.no(i).serNum.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm.setInputProtected(true);
            scrnMsg.A.no(i).shipToFullAddr.setInputProtected(true);
            scrnMsg.A.no(i).custTelNum.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem30Txt.setInputProtected(true);
            scrnMsg.A.no(i).rqstDtTmTsTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).techNm.setInputProtected(true);
            scrnMsg.A.no(i).xxDsRtrnRsnNm_A.setInputProtected(true);
            scrnMsg.A.no(i).xxDsRtrnRsnNm_B.setInputProtected(true);
            scrnMsg.A.no(i).svcCmntTxt.setInputProtected(true);
            scrnMsg.A.no(i).rqstDtTmTsTxt_B.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem54Txt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem54Txt_B.setInputProtected(true);
            scrnMsg.A.no(i).xxDsRtrnRsnNm_C.setInputProtected(true);
            scrnMsg.A.no(i).xxDsRtrnRsnNm_D.setInputProtected(true);
            scrnMsg.A.no(i).fullPsnNm.setInputProtected(true);
        }
    }

    private static void setTableBGColor(NSBL0340BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            tblColor.clearRowsBG("A", scrnMsg.A);
        }
    }
    // mod start 2016/11/22 CSA QC#16116
    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    public static void hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Renewal Rules contract/machine level(" + BUSINESS_ID + "). UserID is -> "
                    + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
    }
    // mod end 2016/11/22 CSA QC#16116
}
