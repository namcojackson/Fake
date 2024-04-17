/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_DELETE_ROW_BLLG;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NMAM8234I;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.ZZM9000E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7060.NMAL7060CMsg;
import business.servlet.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7060_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         W.Honda         Create          N/A
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#6738
 *</pre>
 */
public class NMAL7060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg = new NMAL7060CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcMtrPkgPk, (BigDecimal) params[0]);
            if (!ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgPk)) {
                scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.prcMtrPkgPk.getNameForMessage()});
                return null;
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg = (NMAL7060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);

        //setButtonConfirmMsgEx(BTN_CMN_CLR[1], "ZZM8102I", new String[] {BTN_CMN_CLR[2]}, 0);
        setButtonConfirmMsgEx(BTN_DELETE_ROW[1], NMAM8234I, new String[] {}, 1);
        setButtonConfirmMsgEx(BTN_DELETE_ROW_BLLG[1], NMAM8234I, new String[] {}, 1);

        scrnMsg.setFocusItem(scrnMsg.prcMtrPkgNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        scrnMsg.prcMtrPkgNm.setNameForMessage("Package Name");
        scrnMsg.prcMtrPkgDescTxt.setNameForMessage("Package Description");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        scrnMsg.corpAdvPrcFlg.setNameForMessage("Corporate Advantage Pricing");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mdlNm_A1.setNameForMessage("Service Model Name");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("End Date");
            scrnMsg.A.no(i).xxDt10Dt_AI.setNameForMessage("Create Date");
            scrnMsg.A.no(i).xxFullNm_AI.setNameForMessage("Create By");
            scrnMsg.A.no(i).xxDt10Dt_AU.setNameForMessage("Last Update Date");
            scrnMsg.A.no(i).xxFullNm_AU.setNameForMessage("Last Update By");
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).bllgMtrLvlNum_B1.setNameForMessage("Billing Meter Level");
            scrnMsg.B.no(i).mtrLbDescTxt_B1.setNameForMessage("Billing Counter Name");
            scrnMsg.B.no(i).mtrLbNm_B1.setNameForMessage("Meter Display");
            scrnMsg.B.no(i).mdseCd_B1.setNameForMessage("Usage Item Code");
        }
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).mtrLbDescTxt_C1.setNameForMessage("Hard Counter");
            // QC#6738 2016/04/26 Del start
//            scrnMsg.C.no(i).bllblFlg_C1.setNameForMessage("Billable");
//            scrnMsg.C.no(i).actvFlg_C1.setNameForMessage("Active");
            scrnMsg.C.no(i).mtrMultRate_C1.setNameForMessage("Multiplier");
//            scrnMsg.C.no(i).xxDt10Dt_CI.setNameForMessage("Create Date");
//            scrnMsg.C.no(i).xxFullNm_CI.setNameForMessage("Create By");
//            scrnMsg.C.no(i).xxDt10Dt_CU.setNameForMessage("Last Update Date");
//            scrnMsg.C.no(i).xxFullNm_CU.setNameForMessage("Last Updat By");
            // QC#6738 2016/04/26 Del end
        }
    }
}
