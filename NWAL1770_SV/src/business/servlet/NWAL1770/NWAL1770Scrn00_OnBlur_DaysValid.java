/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.MSG_PARAM_SLS_DT;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0727E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1770Scrn00_OnBlur_DaysValid extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        BigDecimal splyQuoteVldDaysAot = scrnMsg.splyQuoteVldDaysAot.getValue();
        String splyQuoteDt = scrnMsg.splyQuoteDt.getValue();
        String slsDt = scrnMsg.slsDt.getValue();

        if (ZYPDateUtil.compare(slsDt, splyQuoteDt) > 0) {
            scrnMsg.splyQuoteDt.setErrorInfo(1, NWAM0727E, new String[] {MSG_PARAM_SLS_DT });
            scrnMsg.addCheckItem(scrnMsg.splyQuoteDt);
            scrnMsg.putErrorScreen();
        }

        if (!ZYPCommonFunc.hasValue(splyQuoteVldDaysAot)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyQuoteVldDaysAot, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(scrnMsg.splyQuoteVldThruDt, splyQuoteDt);
            return;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.splyQuoteVldThruDt, ZYPDateUtil.addDays(splyQuoteDt, splyQuoteVldDaysAot.intValue()));
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
//        return null;
        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        scrnMsg.setFocusItem(scrnMsg.custIssPoNum);
    }
}
