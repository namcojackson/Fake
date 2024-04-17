/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_OpenWin_Customer
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/16   Hitachi         Y.Takeno        Update          QC#20378-2
 * 2017/08/18   Hitachi         Y.Takeno        Update          QC#20378-3
 *</pre>
 */
public class NSAL1320Scrn00_OpenWin_Customer extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2017/08/16 [QC#20378, ADD]
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        if (!ZYPConstant.FLG_ON_Y.equals(ZYPCommonFunc.hasValue(scrnMsg.xxWrnSkipFlg))) {
            NSAL1320CMsg bizMsg = new NSAL1320CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode("20");
            // START 2017/08/18 [QC#20378, ADD]
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
            // END   2017/08/18 [QC#20378, ADD]
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        // END   2017/08/16 [QC#20378, ADD]

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        // START 2017/08/18 [QC#20378, MOD]
        // START 2017/08/16 [QC#20378, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
            NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(getButtonSelectNumber());
            scrnMsg.addCheckItem(aScrnMsg.dsAcctNm);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
        scrnMsg.xxWrnSkipFlg.clear();
        // END   2017/08/16 [QC#20378, ADD]
        // END   2017/08/18 [QC#20378, MOD]
        Object[] params = NSAL1320CommonLogic.getParamNMAL6760ForBillTo(scrnMsg);
        setArgForSubScreen(params);
    }
}
