/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0100;

import static business.servlet.NFDL0100.constant.NFDL0100Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   Hitachi         T.Tsuchida      Create          QC#7639
 *</pre>
 */
public class NFDL0100Scrn00_OpenWin_Attach extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;

        Object[] params = new Object[PARAM_INDEX_9];
        StringBuilder sqNum = new StringBuilder();
        sqNum.append(getGlobalCompanyCode());
        sqNum.append(PARAMS_BILL_TO_CUST_ACCT_CD_KEY);
        sqNum.append(scrnMsg.billToCustAcctCd_H.getValue());
        sqNum.append(PARAMS_BILL_TO_CUST_CD_KEY);
        sqNum.append(scrnMsg.billToCustCd_H.getValue());

        int i = 0;
        params[i++] = PARAMS_DISPLAY_MODE;
        params[i++] = BUSINESS_APPLICATION_ID;
        params[i++] = sqNum.toString();
        params[i++] = PARAMS_FUNCTION_NAME;
        params[i++] = PARAMS_PRIMARY_KEY;
        params[i++] = null;
        params[i++] = PARAMS_UPPER_KEY;
        params[i++] = PARAMS_EXTENSION_KEY;
        params[i++] = PARAMS_SIZE_KEY;

        setArgForSubScreen(params);
    }
}
