/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1660;

import static business.servlet.NWAL1660.constant.NWAL1660Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1660.NWAL1660CMsg;
import business.servlet.NWAL1660.common.NWAL1660CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1660_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         M.Yamada        Create          N/A
 * 2015/12/16   Fujitsu         Y.Kanefusa      Update          #1090
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 *</pre>
 */
public class NWAL1660_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1660BMsg scrnMsg = (NWAL1660BMsg) bMsg;
        NWAL1660CMsg bizMsg = new NWAL1660CMsg();

        Object[] params = (Object[]) getArgForSubScreen();
        //
        if (params != null) {
            NWAL1660CommonLogic.setInputParam(scrnMsg, (Object[]) params);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1660BMsg scrnMsg = (NWAL1660BMsg) bMsg;
        NWAL1660CMsg bizMsg = (NWAL1660CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1660CommonLogic.initCmnBtnProp(this);
        NWAL1660CommonLogic.initBizBtnProp(this, scrnMsg);
        NWAL1660CommonLogic.setScreenItemCondition(this, scrnMsg);
        setAppFracDigit(scrnMsg);

        // MOD START 2015/12/16 #1090
        //NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        //NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A1");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A2");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B1");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B2");
        NWAL1660CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");
        // MOD END 2015/12/15 #1090

        //Add Start NA QC#2177
        NWAL1660CommonLogic.setDispLineNum(scrnMsg);
        //Add End   NA QC#2177
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1660BMsg scrnMsg = (NWAL1660BMsg) bMsg;

        scrnMsg.dealSvcRevTrnsfAmt.setNameForMessage("Service Revenue Transfer[SRT]");
        scrnMsg.dealSvcCostTrnsfAmt.setNameForMessage("Service Cost Transfer[Service CT]");
        scrnMsg.dealManFlAdjAmt.setNameForMessage("Floor Adjustments");
        scrnMsg.dealManRepRevAdjAmt.setNameForMessage("Incentive Comp Rep Amount");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcCondManDelFlg_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).manPrcAmtRate_A.setNameForMessage("Adjusted %");
            scrnMsg.A.no(i).unitPrcAmt_A.setNameForMessage("Adjustment Value");
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcCondManDelFlg_B.setNameForMessage("Check Box");
            scrnMsg.B.no(i).manPrcAmtRate_B.setNameForMessage("Adjusted %");
            scrnMsg.B.no(i).unitPrcAmt_B.setNameForMessage("Adjustment Value");
        }
    }

    private void setAppFracDigit(NWAL1660BMsg scrnMsg) {

        scrnMsg.xxSubTotCalcPrcAmt.setAppFracDigit(2);
        scrnMsg.xxTotChrgPrcAmt.setAppFracDigit(2);
        scrnMsg.xxTotTaxAmt.setAppFracDigit(2);
        scrnMsg.xxLineTotPrcAmt.setAppFracDigit(2);

        scrnMsg.dealSvcRevTrnsfAmt.setAppFracDigit(2);
        scrnMsg.dealSvcCostTrnsfAmt.setAppFracDigit(2);
        scrnMsg.dealManFlAdjAmt.setAppFracDigit(2);
        scrnMsg.dealManRepRevAdjAmt.setAppFracDigit(2);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).manPrcAmtRate_A.setAppFracDigit(2);
            scrnMsg.A.no(i).unitPrcAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcUnitListPrcAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).funcNetSellPrcAmt_A.setAppFracDigit(2);

        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).manPrcAmtRate_B.setAppFracDigit(2);
            scrnMsg.B.no(i).unitPrcAmt_B.setAppFracDigit(2);
        }
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).autoPrcAmtRate_C.setAppFracDigit(5);
            scrnMsg.C.no(i).calcPrcAmtRate_C.setAppFracDigit(2);
        }
    }
}
