/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2620;

import static business.servlet.NFCL2620.constant.NFCL2620Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2620.NFCL2620CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL2620Scrn00_Save_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/09   Fujitsu         C.Tanaka        Create          QC#5521
 *</pre>
 */
public class NFCL2620Scrn00_Save_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.srchOptPk);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;

        NFCL2620CMsg bizMsg = new NFCL2620CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2620BMsg scrnMsg = (NFCL2620BMsg) bMsg;
        NFCL2620CMsg bizMsg = (NFCL2620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.srchOptPk);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm);
        scrnMsg.putErrorScreen();
    }
}
