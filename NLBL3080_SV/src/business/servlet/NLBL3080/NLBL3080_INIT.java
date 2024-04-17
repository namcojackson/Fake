/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import static business.servlet.NLBL3080.constant.NLBL3080Constant.BUSINESS_ID;
import static business.servlet.NLBL3080.constant.NLBL3080Constant.TAB_ID_ORD;
import static business.servlet.NLBL3080.constant.NLBL3080Constant.TAB_ID_ORD_LINE;
import static business.servlet.NLBL3080.constant.NLBL3080Constant.TAB_PARAM_ORD;
import static business.servlet.NLBL3080.constant.NLBL3080Constant.TAB_PARAM_ORD_LINE;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3080.NLBL3080CMsg;
import business.servlet.NLBL3080.common.NLBL3080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 * 2018/07/23   CITS            K.Ogino         Update          QC#27047
 *</pre>
 */
public class NLBL3080_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        NLBL3080CMsg bizMsg = new NLBL3080CMsg();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param00 = (EZDBStringItem) params[0];
            if (ZYPCommonFunc.hasValue(param00)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_BK, param00);
            }
            EZDBStringItem param01 = (EZDBStringItem) params[1];
            if (ZYPCommonFunc.hasValue(param01)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_BK, param01);
            }
            EZDBStringItem param02 = (EZDBStringItem) params[2];
            if (ZYPCommonFunc.hasValue(param02)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_BK, param02);
            }
            EZDBBigDecimalItem param03 = (EZDBBigDecimalItem) params[3];
            if (ZYPCommonFunc.hasValue(param03)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_BK, param03);
            }
            EZDBStringItem param04 = (EZDBStringItem) params[4];
            if (ZYPCommonFunc.hasValue(param04)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.t_MdlNm_BK, param04);
            }
            EZDBStringItem param05 = (EZDBStringItem) params[5];
            if (ZYPCommonFunc.hasValue(param05)) {
                if (TAB_PARAM_ORD.equals(param05.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, TAB_ID_ORD);
                } else if (TAB_PARAM_ORD_LINE.equals(param05.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, TAB_ID_ORD_LINE);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_BK, TAB_ID_ORD);
            }
        }
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        NLBL3080CMsg bizMsg  = (NLBL3080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3080CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;
        // Header
        //// Search Option
        scrnMsg.srchOptPk_S.setNameForMessage("Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");
        //// Search Condition
        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.dsOrdCatgCd.setNameForMessage("Order Category");
        scrnMsg.dsOrdTpCd.setNameForMessage("Order Reason");
        scrnMsg.t_MdlNm.setNameForMessage("Model");
        scrnMsg.svcConfigMstrPk.setNameForMessage("Config ID");
        scrnMsg.rtlWhCd.setNameForMessage("Warehouse");
        scrnMsg.shipToCustCd.setNameForMessage("Ship To");
        scrnMsg.tocCd.setNameForMessage("Sales Rep");
        scrnMsg.mdseCd.setNameForMessage("Item Number");
        scrnMsg.serNum.setNameForMessage("Serial");
        scrnMsg.rddDt_FR.setNameForMessage("Request Date (From)");
        scrnMsg.rddDt_TO.setNameForMessage("Request Date (Through)");
        scrnMsg.xxOrdDt_FR.setNameForMessage("Order Date (From)");
        scrnMsg.xxOrdDt_TO.setNameForMessage("Order Date (Through)");
        // QC#27047
        scrnMsg.xxChkBox_BO.setNameForMessage("Actual BO");
        scrnMsg.xxChkBox_NS.setNameForMessage("Waiting for SO");
        scrnMsg.xxChkBox_AL.setNameForMessage("To Be Allocate");
        scrnMsg.xxPageShowCurNum_A.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowCurNum_B.setNameForMessage("Current Page Number");
     }
}
