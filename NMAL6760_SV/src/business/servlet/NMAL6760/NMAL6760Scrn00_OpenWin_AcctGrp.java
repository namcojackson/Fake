/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         T.Murai         Create          CSA #2943
 *</pre>
 */
public class NMAL6760Scrn00_OpenWin_AcctGrp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm.setValue("DS_ACCT_GRP");
        scrnMsg.P.no(1).xxPopPrm.setValue("DS_ACCT_GRP_CD");
        scrnMsg.P.no(2).xxPopPrm.setValue("DS_ACCT_GRP_DESC_TXT");
        scrnMsg.P.no(3).xxPopPrm.setValue("DS_ACCT_GRP_SORT_NUM");
        scrnMsg.P.no(4).xxPopPrm.setValue("Account Group Popup");
        scrnMsg.P.no(5).xxPopPrm.setValue("Account Group Code");
        scrnMsg.P.no(6).xxPopPrm.setValue("Account Group Name");
        scrnMsg.P.no(7).xxPopPrm.setValue("Account Group Code");
        scrnMsg.P.no(8).xxPopPrm.setValue("Account Group Name");
        scrnMsg.P.no(9).xxPopPrm.setValue(scrnMsg.dsAcctGrpCd_DP.getValue());
        scrnMsg.P.no(10).xxPopPrm.setValue("");

        Object[] param = new Object[11];
        for (int i = 0; i < 11; i++) {
            param[i] = scrnMsg.P.no(i).xxPopPrm;
        }

        setArgForSubScreen(param);
    }
}
