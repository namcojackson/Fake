/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570;

import static business.servlet.NMAL2570.constant.NMAL2570Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2570.NMAL2570CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2570Scrn00_PagePrev
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL2570Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H1);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.jobTtlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H2);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H2);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;

        NMAL2570CMsg bizMsg = new NMAL2570CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2570BMsg scrnMsg = (NMAL2570BMsg) bMsg;
        NMAL2570CMsg bizMsg = (NMAL2570CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
