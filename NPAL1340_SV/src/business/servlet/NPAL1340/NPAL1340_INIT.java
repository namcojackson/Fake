/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1340;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1340.NPAL1340CMsg;

import business.servlet.NPAL1340.common.NPAL1340CommonLogic;
import business.servlet.NPAL1340.constant.NPAL1340Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NPAL1340_INIT extends S21INITCommonHandler implements NPAL1340Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];// PO_ORD_NUM
            EZDBStringItem param1 = (EZDBStringItem) params[1];// CPO_ORD_NUM
            ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdNum_H0, param0);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_H0, param1);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        NPAL1340CMsg bizMsg = new NPAL1340CMsg();
        bizMsg.setBusinessID("NPAL1340");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;
        NPAL1340CMsg bizMsg  = (NPAL1340CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL1340CommonLogic.commonBtnControl_NPAL1340(this);
        NPAL1340CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;
        scrnMsg.poOrdNum_H1.setNameForMessage("PO Number");
        scrnMsg.cpoOrdNum_H1.setNameForMessage("Order Number");
        scrnMsg.xxChkBox_H1.setNameForMessage("Only Open PO");
        scrnMsg.prntVndNm_H1.setNameForMessage("Supplier Name");
        scrnMsg.vndNm_H1.setNameForMessage("Supplier Site Name");
        scrnMsg.shipToAcctNm_H1.setNameForMessage("Customer Name");

        scrnMsg.vndInvNum_H2.setNameForMessage("Invoice Number");
        scrnMsg.invDt_H2.setNameForMessage("Invoice Date");

        scrnMsg.spTotFuncFrtAmt_H2.setNameForMessage("Freight Charge");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).poRcvQty_A1.setNameForMessage("Release Qty");
            scrnMsg.A.no(i).proNum_A1.setNameForMessage("Tracking Number");
            scrnMsg.A.no(i).vndNm_A1.setNameForMessage("Carrier Name");
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).poDispQty_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).poQty_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).poBalQty_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).poRcvQty_A1.setAppFracDigit(2);

        }
        scrnMsg.spTotFuncFrtAmt_H2.setAppFracDigit(2);
        scrnMsg.entPoDtlDealNetAmt_H2.setAppFracDigit(2);
    }
}
