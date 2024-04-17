/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6700.NMAL6700CMsg;
//import business.servlet.NMAL6700.constant.NMAL6700Constant;

import business.blap.NMAL6700.NMAL6700CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL6700_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_InterCompany".equals(scrEventNm)) {
            scrnMsg.coaAfflCd_H1.setValue(scrnMsg.P.no(2).xxPopPrm.getValue());
            scrnMsg.coaChCd_H1.setValue(scrnMsg.P.no(7).xxPopPrm.getValue());
        } else if ("OpenWin_Coa".equals(scrEventNm)) {
            scrnMsg.coaCcCd_F1.setValue(scrnMsg.P.no(4).xxPopPrm.getValue());
        } else if ("OpenWin_Acct".equals(scrEventNm)) {
            scrnMsg.coaAfflCd_H1.setValue(scrnMsg.P.no(2).xxPopPrm.getValue());
            scrnMsg.coaChCd_H1.setValue(scrnMsg.P.no(7).xxPopPrm.getValue());
        }

        NMAL6700CMsg bizMsg = new NMAL6700CMsg();
        bizMsg.setBusinessID("NMAL6700");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.putErrorScreen();

    }
}
