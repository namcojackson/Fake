/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import static business.servlet.NLBL3200.constant.NLBL3200Constant.BUSINESS_ID;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.FUNC_SRCH;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.TAB_SO_LIST;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLBL3200Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        scrnMsg.clear();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Source Order Number
            EZDBStringItem param00 = (EZDBStringItem) params[0];
            if (ZYPCommonFunc.hasValue(param00)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.trxHdrNum_H1, param00);
            }
            // Shipping Order Number
            EZDBStringItem param01 = (EZDBStringItem) params[1];
            if (ZYPCommonFunc.hasValue(param01)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.soNum_H1, param01);
            }
            // Retail Warehouse Code
            EZDBStringItem param02 = (EZDBStringItem) params[2];
            if (ZYPCommonFunc.hasValue(param02)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_RW, param02);
            }
            // Sub Warehouse Code
            EZDBStringItem param03 = (EZDBStringItem) params[3];
            if (ZYPCommonFunc.hasValue(param03)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_SW, param03);
            }
            // Ship to Customer Code
            EZDBStringItem param04 = (EZDBStringItem) params[4];
            if (ZYPCommonFunc.hasValue(param04)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H1, param04);
            }
            // Carrier Code
            EZDBStringItem param05 = (EZDBStringItem) params[5];
            if (ZYPCommonFunc.hasValue(param05)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_H1, param05);
            }
        }

        NLBL3200CMsg bizMsg = new NLBL3200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set DefaultTab
        scrnMsg.xxDplyTab.setValue(TAB_SO_LIST);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        NLBL3200CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);

        scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
    }
}
