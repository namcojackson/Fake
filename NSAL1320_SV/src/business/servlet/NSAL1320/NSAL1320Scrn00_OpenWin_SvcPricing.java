/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.NSAM0639E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;
import business.servlet.NSAL1320.constant.NSAL1320Constant.XX_PAGE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_OpenWin_SvcPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1320Scrn00_OpenWin_SvcPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        if (!NSAL1320CommonLogic.isImport(scrnMsg)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(i);
                NSAL1320CommonLogic.checkMandatoryForSave(aScrnMsg, scrnMsg);
            }
            NSAL1320CommonLogic.addCheckItemBizLayerErr(scrnMsg);
            scrnMsg.putErrorScreen();
        }

        int ixSelRow = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(ixSelRow));
        if (NSAL1320CommonLogic.getMaintenanceShellCnt(scrnMsg, ixSelRow) == 0) {
            scrnMsg.setMessageInfo(NSAM0639E);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        NSAL1320CMsg bizMsg = new NSAL1320CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        if (XX_PAGE.PAGE_IMPT.getCode().equals(scrnMsg.xxPageCd.getValue())) {
            bizMsg.setFunctionCode("20");
        } else {
            bizMsg.setFunctionCode("40");
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1320CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType() && !NSAL1320CommonLogic.isImport(scrnMsg)) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        Object[] params = NSAL1320CommonLogic.getParamNSAL1330(scrnMsg);
        setArgForSubScreen(params);
    }
}
