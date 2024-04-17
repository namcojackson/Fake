/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750;

import static business.servlet.NMAL6750.constant.NMAL6750Constant.ACCT_NUM;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.BIZ_ID;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.LOC_NUM;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM0207E;
import static business.servlet.NMAL6750.constant.NMAL6750Constant.NMAM8229E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6750.NMAL6750CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL6750Scrn00_AddAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.locNum_H1)) {
            scrnMsg.dsAcctNum_H1.setErrorInfo(1, NMAM0207E, new String[] {ACCT_NUM });
            scrnMsg.locNum_H1.setErrorInfo(1, NMAM0207E, new String[] {LOC_NUM });
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1) && ZYPCommonFunc.hasValue(scrnMsg.locNum_H1)) {
            scrnMsg.dsAcctNum_H1.setErrorInfo(1, NMAM8229E, new String[] {ACCT_NUM, LOC_NUM });
            scrnMsg.locNum_H1.setErrorInfo(1, NMAM8229E, new String[] {ACCT_NUM, LOC_NUM });
        }

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.locNum_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;

        NMAL6750CMsg bizMsg = new NMAL6750CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6750BMsg scrnMsg = (NMAL6750BMsg) bMsg;
        NMAL6750CMsg bizMsg = (NMAL6750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_H1);
        scrnMsg.addCheckItem(scrnMsg.locNum_H1);
        scrnMsg.putErrorScreen();
    }
}
