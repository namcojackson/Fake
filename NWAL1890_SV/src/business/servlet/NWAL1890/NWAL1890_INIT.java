/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1890;

import static business.servlet.NWAL1890.constant.NWAL1890Constant.NWAM0270E;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1890.common.NWAL1890CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/08   Fujitsu         T.Aoi           Create          N/A
 *</pre>
 */
public class NWAL1890_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;

        scrnMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // Get Parameter
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[0]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[1]);
            } else {
                scrnMsg.setMessageInfo(NWAM0270E);
                return null;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;

        NWAL1890CommonLogic.initCmnBtnProp(this);
        NWAL1890CommonLogic.setControlFieldsForLink(scrnMsg);
        NWAL1890CommonLogic.controlDetailScreenFields(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1890BMsg scrnMsg = (NWAL1890BMsg) bMsg;
        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.dsOrdPosnNum.setNameForMessage("Config Number");
        scrnMsg.xxConfigIdSrchTxt.setNameForMessage("Config ID");
        scrnMsg.xxMdlSrchTxt.setNameForMessage("Model");
        scrnMsg.xxBillToAcctNmSrchTxt.setNameForMessage("Bill To Cust Name");
        scrnMsg.xxBillToAcctCdSrchTxt.setNameForMessage("Bill To Account Number");
        scrnMsg.xxBillToLocCdSrchTxt.setNameForMessage("Bill To Location#");
        scrnMsg.xxShipToAcctNmSrchTxt.setNameForMessage("Ship To Cust Name");
        scrnMsg.xxShipToAcctCdSrchTxt.setNameForMessage("Ship To Account Number");
        scrnMsg.xxShipToLocCdSrchTxt.setNameForMessage("Ship To Location#");
        scrnMsg.xxSoldToAcctNmSrchTxt.setNameForMessage("Sold To Cust Name");
        scrnMsg.xxSoldToAcctCdSrchTxt.setNameForMessage("Sold To Account Number");
        scrnMsg.xxSoldToLocCdSrchTxt.setNameForMessage("Sold To Location Number#");
        scrnMsg.xxShowInclLineFlg.setNameForMessage("Show Include Line");
        scrnMsg.xxLineNum.setNameForMessage("Line Number");
        scrnMsg.xxLineStsSrchTxt.setNameForMessage("Line Status");
        scrnMsg.xxOrdItemSrchTxt.setNameForMessage("Ordered Item");
        scrnMsg.xxRtlWhSrchTxt.setNameForMessage("Warehouse");
        scrnMsg.xxRtlSwhSrchTxt.setNameForMessage("Sub Warehouse");
        scrnMsg.xxCpoSrcTpSrchTxt.setNameForMessage("Source Type");
        scrnMsg.xxOrdSrcRefNumSrchTxt.setNameForMessage("Line Source Ref");
        scrnMsg.xxSbstItemSrchTxt.setNameForMessage("Substitute Item");
        scrnMsg.xxSerNumSrchTxt.setNameForMessage("Serial#");
        scrnMsg.xxShowInclCloLineFlg.setNameForMessage("Show Include Closed Lines");
        scrnMsg.xxShowInclCancLineFlg.setNameForMessage("Show Include Cancelled Lines");
        scrnMsg.xxLineNum_R.setNameForMessage("Line Number");
        scrnMsg.xxLineStsSrchTxt_R.setNameForMessage("Line Status");
        scrnMsg.xxOrdItemSrchTxt_R.setNameForMessage("Ordered Item");
        scrnMsg.xxRtrnRsnSrchTxt_R.setNameForMessage("Return Reason");
        scrnMsg.xxRtlWhSrchTxt_R.setNameForMessage("Warehouse");
        scrnMsg.xxRtlSwhSrchTxt_R.setNameForMessage("Sub Warehouse");
        scrnMsg.xxOrdSrcRefNumSrchTxt_R.setNameForMessage("Line Source Ref");
        scrnMsg.xxSerNumSrchTxt_R.setNameForMessage("Serial#");
        scrnMsg.xxShowInclCloLineFlg_R.setNameForMessage("Show Include Closed lines");
        scrnMsg.xxShowInclCancLineFlg_R.setNameForMessage("Show Include Cancelled Lines");
    }
}
