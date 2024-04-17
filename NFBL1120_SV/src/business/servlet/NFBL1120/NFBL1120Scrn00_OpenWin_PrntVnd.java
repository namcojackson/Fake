/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL1120.NFBL1120CMsg;
import business.servlet.NFBL1120.common.NFBL1120CommonLogic;
import business.servlet.NFBL1120.constant.NFBL1120Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 

 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   CUSA            Y.Aikawa        Create          N/A
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NFBL1120Scrn00_OpenWin_PrntVnd extends S21CommonHandler implements NFBL1120Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.locNm);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;

        NFBL1120CMsg bizMsg = new NFBL1120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1120BMsg scrnMsg = (NFBL1120BMsg) bMsg;
        NFBL1120CMsg bizMsg  = (NFBL1120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.locNm);
        scrnMsg.putErrorScreen();

        // START 2017/12/22 [QC#22831, MOD]
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = NFBL1120CommonLogic.getParamNWAL1130ForSupplier(scrnMsg, this.getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode()));
        // END   2017/12/22 [QC#22831, MOD]

        setArgForSubScreen(params);

    }
}
