/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
// import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
// import business.blap.ZZPL0110.ZZPL0110CMsg;
// import business.servlet.ZZPL0110.constant.ZZPL0110Constant;

import business.blap.ZZPL0110.ZZPL0110CMsg;
import business.servlet.ZZPL0110.common.ZZPL0110CommonLogic;
import business.servlet.ZZPL0110.constant.ZZPL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/09/12   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.eipRptProcLogPk);
        scrnMsg.addCheckItem(scrnMsg.subSysCd);
        scrnMsg.addCheckItem(scrnMsg.rptJobId);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
        scrnMsg.putErrorScreen();

        if ((scrnMsg.xxFromDt != null && !scrnMsg.xxFromDt.equals("")) && (scrnMsg.xxToDt != null && !scrnMsg.xxToDt.equals(""))) {
            ZZPL0110CommonLogic.compareFromToDate(scrnMsg);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        ZZPL0110CMsg bizMsg = new ZZPL0110CMsg();
        bizMsg.setBusinessID(ZZPL0110Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZPL0110Constant.FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;
        ZZPL0110CMsg bizMsg = (ZZPL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.eipRptProcLogPk);
    }
}
