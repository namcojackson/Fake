/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0280;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0280.NLCL0280CMsg;
import business.servlet.NLCL0280.common.NLCL0280CommonLogic;
import business.servlet.NLCL0280.constant.NLCL0280Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Init Inventory Transaction Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/25   CITS            T.Tokutomi      Create          N/A
 *</pre>
 */
public class NLCL0280_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLCL0280Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        // Parameter Set
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            NLCL0280CommonLogic.setInitParam(scrnMsg, params);
        }

        NLCL0280CMsg bizMsg = new NLCL0280CMsg();
        bizMsg.setBusinessID(NLCL0280Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;
        NLCL0280CMsg bizMsg = (NLCL0280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!NLCL0280CommonLogic.chkInitParam(scrnMsg)) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.trxDt_H2, ZYPDateUtil.getSalesDate());
        }

        NLCL0280CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLCL0280CommonLogic.ctrlScrnItemProtection(scrnMsg, this);
        scrnMsg.setFocusItem(scrnMsg.trxDt_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLCL0280BMsg scrnMsg = (NLCL0280BMsg) bMsg;

        scrnMsg.xxPageShowCurNum.setNameForMessage("Current Page Number");
        scrnMsg.invtyTrxPk_H1.setNameForMessage("Transaction ID");
        scrnMsg.invtyTrxSlpNum_H1.setNameForMessage("Source Document Number");
        scrnMsg.rwsNum_H1.setNameForMessage("RWS Number");
        scrnMsg.soNum_H1.setNameForMessage("Shipping Order Number");
        scrnMsg.serNum_H1.setNameForMessage("Serial");
        scrnMsg.mdseCd_H1.setNameForMessage("Item Number");
        scrnMsg.mdseDescShortTxt_H1.setNameForMessage("Item Description");
        scrnMsg.coaProdCd_H1.setNameForMessage("Product");
        scrnMsg.splyItemNum_H1.setNameForMessage("Supplier Item");
        scrnMsg.mnfItemCd_H1.setNameForMessage("Manufacturer Item");
        scrnMsg.rtlWhCd_H1.setNameForMessage("Warehouse Code");
        scrnMsg.rtlSwhCd_H1.setNameForMessage("Sub Warehouse Code");
        scrnMsg.vndCd_H1.setNameForMessage("Supplier");
        scrnMsg.shipFromLocCustCd_H1.setNameForMessage("Ship From Location");
        scrnMsg.shipToLocCustCd_H1.setNameForMessage("Ship To Location");
    }
}
