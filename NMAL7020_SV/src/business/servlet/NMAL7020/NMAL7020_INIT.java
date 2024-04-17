/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7020;

import static business.servlet.NMAL7020.constant.NMAL7020Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7020.NMAL7020CMsg;
import business.servlet.NMAL7020.common.NMAL7020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7020_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CMsg bizMsg = new NMAL7020CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd, (EZDBStringItem) params[0]);
        }
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CMsg bizMsg = (NMAL7020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7020CommonLogic.initCmnBtnProp(this, getUserProfileService());
        scrnMsg.varCharConstVal_A.setInputProtected(true);
        scrnMsg.varCharConstVal_B.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.prcCatgNm_O);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;

        scrnMsg.prcCatgNm_O.setNameForMessage("From Price List");
        scrnMsg.prcCatgNm_N.setNameForMessage("New Price List Name");
        scrnMsg.newPrcCatgDescTxt.setNameForMessage("Comments/Notes");
        scrnMsg.actvFlg.setNameForMessage("Active");

        scrnMsg.mainPrcListInfoFlg.setNameForMessage("Copy main Price List Information");
        scrnMsg.prcListAccsTpFlg.setNameForMessage("Copy Price List Access Types");
        scrnMsg.copyAttrbTrxFlg.setNameForMessage("Copy Contract Attributes/Transaction Drivers");
        scrnMsg.copyPrcFlg.setNameForMessage("Copy Pricing");

        scrnMsg.prcCalcTpCd.setNameForMessage("Price Calculation Type");
        scrnMsg.prcPctAmtTpCd.setNameForMessage("Percentage / Amount Type");
        scrnMsg.copyPrcAmtRate.setNameForMessage("Percentage / Amount");

        scrnMsg.effFromDt.setNameForMessage("Effective From");
        scrnMsg.effThruDt.setNameForMessage("Effective to");
        scrnMsg.effApplyLvlTpCd.setNameForMessage("Apply to");

    }

}
