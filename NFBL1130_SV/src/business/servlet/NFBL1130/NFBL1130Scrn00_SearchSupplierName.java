/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL1130.NFBL1130CMsg;
//import business.servlet.NFBL1130.constant.NFBL1130Constant;

import business.blap.NFBL1130.NFBL1130CMsg;
import business.servlet.NFBL1130.constant.NFBL1130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14179
 *</pre>
 */
public class NFBL1130Scrn00_SearchSupplierName extends S21CommonHandler implements NFBL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // if (scrnMsg.apVndCd.isClear()) {
        // scrnMsg.apVndCd.setErrorInfo(1, ZZM9000E, new String[]
        // {scrnMsg.apVndCd.getNameForMessage() });
        // scrnMsg.prntVndNm.clear();
        // }
        // scrnMsg.addCheckItem(scrnMsg.apVndCd);
        if (scrnMsg.prntVndCd.isClear()) {
            scrnMsg.prntVndCd.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prntVndCd.getNameForMessage() });
            scrnMsg.prntVndNm.clear();
        }
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;

        NFBL1130CMsg bizMsg = new NFBL1130CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        NFBL1130CMsg bizMsg  = (NFBL1130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
