/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         T.Murai         Create          N/A
 * 2018/07/26   Fujitsu         H.Kumagai       Update          QC#14307
 *</pre>
 */
public class NWAL1840Scrn00_OpenWin_ShipTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2018/07/26 QC#14307 Add Start
        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // 2018/07/26 QC#14307 Add End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        // 2018/07/26 QC#14307 Add Start
        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/07/26 QC#14307 Add End
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = NWAL1840CommonLogic.getParamNMAL6760ForShipTo(scrnMsg);
        setArgForSubScreen(params);
    }
}