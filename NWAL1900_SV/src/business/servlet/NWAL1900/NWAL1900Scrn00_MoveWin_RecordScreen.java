/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1900;

import static business.servlet.NWAL1900.constant.NWAL1900Constant.NEXT_SCREEN_TO_ADJUSTMENT;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.NEXT_SCREEN_TO_RULE;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.QUALIFIED;
import static business.servlet.NWAL1900.constant.NWAL1900Constant.APPLIED;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1900Scrn00_MoveWin_RecordScreen extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1900BMsg scrnMsg = (NWAL1900BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1900BMsg scrnMsg = (NWAL1900BMsg) bMsg;

        int index = getButtonSelectNumber();

        Object[] param = null;

        if (PRC_RULE_COND_TP.PRICE_ADJUSTMENT_TABLE.equals(scrnMsg.A.no(index).prcRuleCondTpCd_A.getValue())) {
            if (scrnMsg.A.no(index).xxCmntTxt_A3.getValue().equals(QUALIFIED) || scrnMsg.A.no(index).xxCmntTxt_A3.getValue().equals(APPLIED)) {
                param = new Object[3];
                param[0] = scrnMsg.A.no(index).prcRuleHdrPk_A;
                param[1] = scrnMsg.A.no(index).prcAdjDtlPk_A;
                param[2] = scrnMsg.A.no(index).prcRuleDtlPk_A;
                setResult(NEXT_SCREEN_TO_ADJUSTMENT);
            } else {
                param = new Object[1];
                param[0] = scrnMsg.A.no(index).prcRuleHdrPk_A;
                setResult(NEXT_SCREEN_TO_ADJUSTMENT);
            }

        } else {
            param = new Object[1];
            param[0] = scrnMsg.A.no(index).prcRuleHdrPk_A;
            setResult(NEXT_SCREEN_TO_RULE);
        }

        setArgForSubScreen(param);
        openMultiModeScreen();

    }
}
