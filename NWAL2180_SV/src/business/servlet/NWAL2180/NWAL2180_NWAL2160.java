/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.BIZ_ID;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.POP_UP_TIER_PRICING;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.POP_UP_TIER_PRICING_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2180.NWAL2180CMsg;
import business.servlet.NWAL2180.common.NWAL2180CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2180_NWAL2160
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   Fujitsu         M.Yamada        Create          N/A
 * 2016/07/22   Fujitsu         M.Yamada        Update          QC#11339
 *</pre>
 */
public class NWAL2180_NWAL2160 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        if (POP_UP_TIER_PRICING.equals(scrnMsg.xxPopPrm_P2.getValue())) {
            NWAL2180CommonLogic.setNWAL2160ReturnInfo(scrnMsg);

        } else if (POP_UP_TIER_PRICING_CONFIG.equals(scrnMsg.xxPopPrm_P2.getValue())) {
            NWAL2180CommonLogic.setNWAL2160ReturnInfoConfig(scrnMsg);
        }

        NWAL2180CMsg bizMsg = new NWAL2180CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
        NWAL2180CMsg bizMsg = (NWAL2180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int ix = scrnMsg.xxNum_Z.getValueInt();
        if (POP_UP_TIER_PRICING.equals(scrnMsg.xxPopPrm_P2.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.Z.no(ix).prcListBandDescTxt_Z);

        } else if (POP_UP_TIER_PRICING_CONFIG.equals(scrnMsg.xxPopPrm_P2.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.U.no(ix).prcListBandDescTxt_U);
        }
    }
}
