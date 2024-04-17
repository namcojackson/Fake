/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7070;

import static business.servlet.NMAL7070.constant.NMAL7070Constant.EVNT_ACCT_NM;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.EVNT_ACCT_NUM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7070.common.NMAL7070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070Scrn00_OpenWin_AccountSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        NMAL7070CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        String acctNum = null;
        String acctNm = null;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, getSubmitedFieldNm(ctx));
        if ((EVNT_ACCT_NUM).equals(scrnMsg.xxScrEventNm.getValue())) {
            acctNum = scrnMsg.dsAcctNum.getValue();
        } else if ((EVNT_ACCT_NM).equals(scrnMsg.xxScrEventNm.getValue())) {
            acctNm = scrnMsg.dsAcctNm.getValue();
        }
        int i = 0;
        if (acctNum != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, acctNum);
        } else {
            scrnMsg.P.no(i++).xxPopPrm_P.clear();
        }

        if (acctNm != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm_P, acctNm);
        } else {
            scrnMsg.P.no(i++).xxPopPrm_P.clear();
        }

        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();
        scrnMsg.P.no(i++).xxPopPrm_P.clear();

        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[24];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm_P;
        }
        setArgForSubScreen(params);
    }
}
