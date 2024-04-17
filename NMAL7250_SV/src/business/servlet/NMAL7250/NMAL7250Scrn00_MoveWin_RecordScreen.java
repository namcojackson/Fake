/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250;

import static business.servlet.NMAL7250.constant.NMAL7250Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7250.common.NMAL7250CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7250Scrn00_MoveWin_RecordScreen
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/28   Fujitsu         W.Honda         Create          QC#5932
 *</pre>
 */
public class NMAL7250Scrn00_MoveWin_RecordScreen extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;

        NMAL7250CommonLogic.commonAddCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;

        int index = getButtonSelectNumber();

        Object[] param = new Object[1];
        param[0] = scrnMsg.A.no(index).prcRuleHdrPk_A1;

        if (PRC_RULE_COND_TP.PRICE_ADJUSTMENT_TABLE.equals(scrnMsg.A.no(index).prcRuleCondTpCd_A1.getValue())) {
            setResult(NEXT_SCREEN_TO_ADJUSTMENT);
        } else {
            setResult(NEXT_SCREEN_TO_RULE);
        }

        setArgForSubScreen(param);
    }
}
