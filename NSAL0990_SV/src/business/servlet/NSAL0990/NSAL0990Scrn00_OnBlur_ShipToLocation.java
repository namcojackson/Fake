/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2018/07/30   Hitachi         T.Tomita        Update          QC#14307
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 *</pre>
 */
public class NSAL0990Scrn00_OnBlur_ShipToLocation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2019/01/21 QC#27304
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        if (hasValue(scrnMsg.curLocNum)) {
            scrnMsg.addCheckItem(scrnMsg.curLocNum);
            scrnMsg.putErrorScreen();
        }
        // mod end 2019/01/21 QC#27304
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        // del start 2019/01/21 QC#27304
        //if (!hasValue(scrnMsg.curLocNum)) {
        //    return null;
        //}
        // del end 2019/01/21 QC#27304
        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.curLocNum);
        scrnMsg.putErrorScreen();
        // Add Start 2018/07/30 QC#14307
        NSAL0990CommonLogic.activeSpclInstructionBtn(this, scrnMsg);
        setResult(ZYPConstant.FLG_OFF_N);
        if (ZYPCommonFunc.hasValue(scrnMsg.curLocNum_SI)) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NSAL0990CommonLogic.getParamNMAL3300(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        // Add End 2018/07/30 QC#14307
    }
}
