/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2170;

import static business.servlet.NWAL2170.constant.NWAL2170Constant.BIZ_ID;
import static business.servlet.NWAL2170.constant.NWAL2170Constant.NWBM0183E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2170.NWAL2170CMsg;
import business.servlet.NWAL2170.common.NWAL2170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2170Scrn00_OpenWin_SvcPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL2170Scrn00_OpenWin_SvcPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;

        int ixSelRow = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(ixSelRow));
        if (NWAL2170CommonLogic.getMaintenanceShellCnt(scrnMsg, ixSelRow) == 0) {
            scrnMsg.setMessageInfo(NWBM0183E);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;

        NWAL2170CMsg bizMsg = new NWAL2170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2170BMsg scrnMsg = (NWAL2170BMsg) bMsg;
        NWAL2170CMsg bizMsg = (NWAL2170CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2170CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType() && !NWAL2170CommonLogic.isImport(scrnMsg)) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        Object[] params = NWAL2170CommonLogic.getParamNWAL2180(scrnMsg);
        setArgForSubScreen(params);
    }
}
