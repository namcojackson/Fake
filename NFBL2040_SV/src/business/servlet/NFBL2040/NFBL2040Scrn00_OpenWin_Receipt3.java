/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/02/17   Fujitsu         H.Ikeda         Create          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040Scrn00_OpenWin_Receipt3 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.poNum_L);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_L);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        // mod start 2022/02/15 QC#57090
        //Object[] params = NFBL2040CommonLogic.getParamNWAL1130ForReceipt(scrnMsg, this.getGlobalCompanyCode(), scrnMsg.poNum_L.getValue(), scrnMsg.delyOrdNum_L.getValue(), scrnMsg.rwsNum_L.getValue());
        Object[] params = null;
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            params = NFBL2040CommonLogic.getParamNWAL1130ForVndRtrnReceipt(scrnMsg, this.getGlobalCompanyCode(), scrnMsg.poNum_L.getValue(), scrnMsg.delyOrdNum_L.getValue(), scrnMsg.rwsNum_L.getValue(), scrnMsg.vndRtrnNum_L.getValue());
        } else {
            params = NFBL2040CommonLogic.getParamNWAL1130ForReceipt(scrnMsg, this.getGlobalCompanyCode(), scrnMsg.poNum_L.getValue(), scrnMsg.delyOrdNum_L.getValue(), scrnMsg.rwsNum_L.getValue());
        }
        // mod end 2022/02/15 QC#57090
        setArgForSubScreen(params);
    }
}
