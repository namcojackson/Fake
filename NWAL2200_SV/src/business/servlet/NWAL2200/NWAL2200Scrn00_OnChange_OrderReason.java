/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogic;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OnChange_OrderReason
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2017/01/23   Fujitsu         T.Aoi           Update          QC#18798(Sol#173)
 * 2018/07/25   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 *</pre>
 */
public class NWAL2200Scrn00_OnChange_OrderReason extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setResult(ZYPConstant.FLG_OFF_N);

        // Add Start 2018/07/25 S21_NA#14307
        if (ZYPConstant.FLG_ON_1.equals(scrnMsg.xxRqstFlg.getValue())){
            setResult(ZYPConstant.FLG_ON_1);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            setArgForSubScreen(NWAL2200CommonLogic.getArgForSubScreen(scrnMsg, this.getGlobalCompanyCode()));
            return;
        }
        // Add End 2018/07/25 S21_NA#14307

        NWAL2200CommonLogicForScreenFields.setProtectByOrdCatgBizCtx(this, scrnMsg); //2017/01/23 QC#18798 Add
        scrnMsg.setFocusItem(scrnMsg.dsOrdRsnCd);
    }
}