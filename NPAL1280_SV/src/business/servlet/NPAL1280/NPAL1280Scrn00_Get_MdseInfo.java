/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BTN_ACCOUNT_ID_PREFIX;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_SCREEN_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : Get_MdseInfo
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/14/2016   CITS            R.Shimamoto     Create          N/A
 *</pre>
 */
public class NPAL1280Scrn00_Get_MdseInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        int idx = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineNum, String.valueOf(idx));

        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        boolean isError = NPAL1280CommonLogic.retCheckItems(scrnMsg);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPL_ID);
        String glblCmpyCd = getGlobalCompanyCode();
        if (!isError) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {
                    setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, i, true);
                } else {
                    setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, i, false);
                }
                if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue()) || (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).openStsFlg_D.getValue()))) {
                    NPAL1280CommonLogic.controlItemsChild(this, scrnMsg, glblCmpyCd, funcList, i);
                } else {
                    NPAL1280CommonLogic.controlItemsDetailLineDef(this, scrnMsg, i);
                }
            }
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {
                    setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, i, true);
                } else {
                    setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, i, false);
                }
                if (PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue()) || (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).openStsFlg_D.getValue()))) {
                    NPAL1280CommonLogic.controlItemsChild(this, scrnMsg, glblCmpyCd, funcList, i);
                } else {
                    NPAL1280CommonLogic.controlItemsDetailLineDef(this, scrnMsg, i);
                }
            }
            scrnMsg.putErrorScreen();
        }

        S21TableColorController tblColor = new S21TableColorController(BUSINESS_SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        int idx = this.getButtonSelectNumber();
        scrnMsg.setFocusItem(scrnMsg.A.no(idx).prchReqDispQty_A1);

    }
}
