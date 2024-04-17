/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.BIZ_ID;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.FUNC_CD_20;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/26   Hitachi         T.Tsuchida      Create          QC#13157
 * 2016/10/12   Hitachi         T.Tsuchimoto    Update          QC#13960
 *</pre>
 */
public class NFBL2040Scrn00_OnChange_InvDt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        // START 2016/10/12 Y.Tsuchimoto [QC#13960,MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.invDt)) {
            scrnMsg.addCheckItem(scrnMsg.invDt);
            scrnMsg.putErrorScreen();
        }
        // END   2016/10/12 Y.Tsuchimoto [QC#13960,MOD]

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
