/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_TIER_PRICING;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_TIER_PRICING_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330_NSAL1370
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330_NSAL1370 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        if (POP_UP_TIER_PRICING.equals(scrnMsg.xxPopPrm_P2.getValue())) {
            NSAL1330CommonLogic.setNSAL1370ReturnInfo(scrnMsg);

        } else if (POP_UP_TIER_PRICING_CONFIG.equals(scrnMsg.xxPopPrm_P2.getValue())) {
            NSAL1330CommonLogic.setNSAL1370ReturnInfoConfig(scrnMsg);
        }

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int ix = scrnMsg.xxNum_Z.getValueInt();
        if (POP_UP_TIER_PRICING.equals(scrnMsg.xxPopPrm_P2.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.Z.no(ix).prcListBandDescTxt_Z);

        } else if (POP_UP_TIER_PRICING_CONFIG.equals(scrnMsg.xxPopPrm_P2.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.U.no(ix).prcListBandDescTxt_U);
        }
    }
}
