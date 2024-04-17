/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2330;

import static business.servlet.NWAL2330.constant.NWAL2330Constant.BIZ_ID;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.NWAM0755E;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2330.NWAL2330CMsg;
import business.servlet.NWAL2330.common.NWAL2330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL2330_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2330_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2330BMsg scrnMsg = (NWAL2330BMsg) bMsg;
        NWAL2330CMsg bizMsg = new NWAL2330CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= 1) {
            if (params[0] instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_BK, (EZDBStringItem) params[0]);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_BK, (String) params[0]);
            }
        } else {
            // errMsg(There are not enough parameters.)
            scrnMsg.setMessageInfo(NWAM0755E, new String[] {scrnMsg.cpoOrdNum_H1.getNameForMessage()});
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2330BMsg scrnMsg = (NWAL2330BMsg) bMsg;
        NWAL2330CMsg bizMsg = (NWAL2330CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H1);
        NWAL2330CommonLogic.initCmnBtnProp(this);
        NWAL2330CommonLogic.appFracDigit(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2330BMsg scrnMsg = (NWAL2330BMsg) bMsg;

        scrnMsg.cpoOrdNum_H1.setNameForMessage("Order#");
        scrnMsg.soNum_H1.setNameForMessage("SO#");
        scrnMsg.invNum_H1.setNameForMessage("Invoice#");
        scrnMsg.dsAcctNm_H1.setNameForMessage("Sold To Name*");
        scrnMsg.dsAcctNm_H2.setNameForMessage("Bill To Name*");
        scrnMsg.dsAcctNm_H3.setNameForMessage("Ship To Name*");
        scrnMsg.sellToCustCd_H1.setNameForMessage("Number");
        scrnMsg.billToCustAcctCd_H1.setNameForMessage("Number");
        scrnMsg.shipToCustAcctCd_H1.setNameForMessage("Number");
        scrnMsg.ordDt_H1.setNameForMessage("Order Date");
        scrnMsg.ordDt_H2.setNameForMessage("Order Date");
        scrnMsg.invDt_H1.setNameForMessage("Invoice Date");
        scrnMsg.invDt_H1.setNameForMessage("Invoice Date");
    }
}
