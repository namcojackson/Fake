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
 * 2016/10/07   Hitachi         T.Tsuchida      Create          QC#13157
 * 2017/01/12   Hitachi         E.Kameishi      Update          QC#16950
 * 2018/03/06   Hitachi         E.Kameishi      Update          QC#24079
 *</pre>
 */
public class NFBL2040Scrn00_OnChange_PmtTerm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        // START 2018/03/06 [QC#24079, MOD]
        // START 2017/01/12 E.Kameishi [QC#16950,ADD]
        // if (ZYPCommonFunc.hasValue(scrnMsg.termNetDueDt)) {
        //     return null;
        // }
        // END 2017/01/12 E.Kameishi [QC#16950,ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.termNetDueDt)
                && !ZYPCommonFunc.hasValue(scrnMsg.vndPmtTermDescTxt)) {
            return null;
        }
        // END 2018/03/06 [QC#24079, MOD]
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
        // START 2017/01/12 E.Kameishi [QC#16950,MOD]
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        // START 2017/01/12 E.Kameishi [QC#16950,MOD]
        scrnMsg.setFocusItem(scrnMsg.xxChkBox_HO);

    }
}
