/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250;

import static business.servlet.NMAL7250.constant.NMAL7250Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7250.NMAL7250CMsg;
import business.servlet.NMAL7250.common.NMAL7250CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Fujitsu         W.Honda         Create          N/A
 * 2016/06/01   Fujitsu         Y.Kanefusa      Update          S21_NA#9173
 *</pre>
 */
public class NMAL7250Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;

        NMAL7250CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcRuleNm)
                // QC#9173 2016/06/01 Mod Start
                // && !ZYPCommonFunc.hasValue(scrnMsg.prcRulePrcdGrpNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.defRulePrcdNum)
                // QC#9173 2016/06/01 Mod End
                && !ZYPCommonFunc.hasValue(scrnMsg.prcRuleCatgCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcRuleTrxCatgCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcRuleAttrbCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcRuleCondFromTxt)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk_CG)
                && !ZYPCommonFunc.hasValue(scrnMsg.coaBrCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk_MA)
                && !ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && !ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {
            scrnMsg.setMessageInfo(NMAM0288E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;

        NMAL7250CMsg bizMsg = new NMAL7250CMsg();
        bizMsg.setBusinessID("NMAL7250");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;
        NMAL7250CMsg bizMsg  = (NMAL7250CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7250CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        NMAL7250CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        scrnMsg.setFocusItem(scrnMsg.prcRuleNm);
    }
}
