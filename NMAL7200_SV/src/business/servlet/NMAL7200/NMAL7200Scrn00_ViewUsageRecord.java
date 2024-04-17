/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7200;

import static business.servlet.NMAL7200.constant.NMAL7200Constant.NEXT_SCREEN_TO_ADJUSTMENT;
import static business.servlet.NMAL7200.constant.NMAL7200Constant.NEXT_SCREEN_TO_LIST;
import static business.servlet.NMAL7200.constant.NMAL7200Constant.NEXT_SCREEN_TO_RULE;
import static business.servlet.NMAL7200.constant.NMAL7200Constant.PARAM_SIZE;
import static business.servlet.NMAL7200.constant.NMAL7200Constant.USAGE_TYPE_RULE;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7200Scrn00_ViewUsageRecord
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7200Scrn00_ViewUsageRecord extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7200BMsg scrnMsg = (NMAL7200BMsg) bMsg;
        int selectedIndex = scrnMsg.xxRadioBtn.getValueInt();
        String usageType = scrnMsg.A.no(selectedIndex).xxScrItem10Txt.getValue();
        String priceRuleType = scrnMsg.A.no(selectedIndex).prcRuleCondTpCd.getValue();
        Object[] params = new Object[PARAM_SIZE];

        if (usageType.equals(USAGE_TYPE_RULE)) {
            if (PRC_RULE_COND_TP.PRICE_ADJUSTMENT_TABLE.equals(priceRuleType)) {
                setResult(NEXT_SCREEN_TO_ADJUSTMENT);
            } else {
                setResult(NEXT_SCREEN_TO_RULE);
            }
            String value = scrnMsg.A.no(selectedIndex).xxScrItem30Txt.getValue();
            if (isBigDecimal(value)) {
                params[0] = new BigDecimal(value);
            }
        } else {
            setResult(NEXT_SCREEN_TO_LIST);
            params[0] = scrnMsg.A.no(selectedIndex).xxScrItem30Txt;
        }

        setArgForSubScreen(params);
        openMultiModeScreen();

    }

    private boolean isBigDecimal(String checkValue) {
        try {
            new BigDecimal(checkValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
