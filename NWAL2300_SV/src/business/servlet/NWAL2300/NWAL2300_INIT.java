/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300;

import static business.servlet.NWAL2300.constant.NWAL2300Constant.BIZ_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2300.NWAL2300CMsg;
import business.servlet.NWAL2300.common.NWAL2300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/10/02   Fujitsu         H.Sugawara      Update          QC#19922
 *</pre>
 */
public class NWAL2300_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

         checkBusinessAppGranted(getContextUserInfo().getUserId(),BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;
        NWAL2300CMsg bizMsg = new NWAL2300CMsg();

        // IN Parameter Get
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum_BK, param0.getValue());

        } else {
            // To process the menu transition.
            scrnMsg.cpoOrdNum_BK.clear();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H2, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H3, ZYPConstant.FLG_OFF_N);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;
        NWAL2300CMsg bizMsg = (NWAL2300CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2300CommonLogic.initCmnBtnProp(this);
        NWAL2300CommonLogic.initBtnProp(this, scrnMsg);
        NWAL2300CommonLogic.setProtect(this, scrnMsg);
        NWAL2300CommonLogic.setControlFieldsForDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;

        scrnMsg.cpoOrdNum_H1.setNameForMessage("Order Number");
        scrnMsg.cpoOrdNum_H2.setNameForMessage("Credit Order");
        scrnMsg.billToCustAcctCd_H1.setNameForMessage("Bill To Cust");
        scrnMsg.dsAcctNm_H1.setNameForMessage("Bill To Cust");
        // Mod Start 2017/10/02 QC#19922
        //scrnMsg.billToCustCd_H1.setNameForMessage("Location");
        scrnMsg.billToCustCd_H1.setNameForMessage("Bill To Code");
        // Mod End 2017/10/02 QC#19922
        scrnMsg.cpoOrdNum_H3.setNameForMessage("Rebill Order");
        scrnMsg.shipToCustAcctCd_H1.setNameForMessage("Ship To Cust");
        scrnMsg.dsAcctNm_H2.setNameForMessage("Ship To Cust");
        // Mod Start 2017/10/02 QC#19922
        //scrnMsg.shipToCustCd_H1.setNameForMessage("Location");
        scrnMsg.shipToCustCd_H1.setNameForMessage("Ship To Code");
        // Mod End 2017/10/02 QC#19922
        scrnMsg.xxOrdMemoTxt_H1.setNameForMessage("Comments");
        scrnMsg.invTotDealNetAmt_H1.setNameForMessage("Total Invoiced($)");
        scrnMsg.xxYesNoNm_H1.setNameForMessage("Open Lines");
        scrnMsg.xxYesNoNm_H2.setNameForMessage("Open RMAs");
        scrnMsg.ordPrftVrsnNum_H1.setNameForMessage("Version#");
        scrnMsg.grsPrftPct_H1.setNameForMessage("GP%");
        scrnMsg.funcGrsPrftAmt_H1.setNameForMessage("GP$");
        scrnMsg.funcNegoDealAmt_H1.setNameForMessage("Neg Deal($)");
        scrnMsg.totFuncFinalFlAmt_H1.setNameForMessage("Final Floor($)");
        scrnMsg.totFuncRepRevAmt_H1.setNameForMessage("Incentive Comp Rep Amount($)");
    }
}
