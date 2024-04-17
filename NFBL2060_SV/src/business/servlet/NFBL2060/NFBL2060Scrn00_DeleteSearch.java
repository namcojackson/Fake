/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import static business.servlet.NFBL2060.constant.NFBL2060Constant.BIZ_ID;
import static business.servlet.NFBL2060.constant.NFBL2060Constant.FUNC_CD_40;
import static business.servlet.NFBL2060.constant.NFBL2060Constant.NZZM0002I;
import static business.servlet.NFBL2060.constant.NFBL2060Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2060.NFBL2060CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFBL2060Scrn00_DeleteSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/30   Fujitsu         C.Tanaka        Create          QC#5521
 *</pre>
 */
public class NFBL2060Scrn00_DeleteSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srchOptPk_S)) {
            scrnMsg.srchOptPk_S.setErrorInfo(1, ZZM9000E, new String[] {"Saved Search Options" });
        }

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        NFBL2060CMsg bizMsg = new NFBL2060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.putErrorScreen();

        scrnMsg.setMessageInfo(NZZM0002I);
    }
}
