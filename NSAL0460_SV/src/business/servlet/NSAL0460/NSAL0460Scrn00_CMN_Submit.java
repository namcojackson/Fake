/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0460;

import static business.servlet.NSAL0460.common.NSAL0460CommonLogic.initialControlScreen;
import static business.servlet.NSAL0460.constant.NSAL0460Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0460.NSAL0460CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Start Read Capture
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Hitachi         T.Iwamoto       Create          N/A
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#2886
 * 2016/02/26   Hitachi         K.Kasai         Update          QC#2684
 *</pre>
 */
public class NSAL0460Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;
        scrnMsg.A.setCheckParam(new String[] {NSAL0460Bean.mtrReadDt, NSAL0460Bean.readMtrCnt }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;
        NSAL0460CMsg bizMsg = new NSAL0460CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0460BMsg scrnMsg = (NSAL0460BMsg) bMsg;
        NSAL0460CMsg bizMsg = (NSAL0460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);

        // mod start 2016/02/25 CSA Defect#2684
        // [QC#2886,MOD]START
        scrnMsg.A.setCheckParam(new String[] {NSAL0460Bean.dsContrNum, NSAL0460Bean.serNum, NSAL0460Bean.contrEffFromDt, NSAL0460Bean.mtrLbDescTxt, NSAL0460Bean.mtrReadDt, NSAL0460Bean.readMtrCnt }, 1);
        // [QC#2886,MOD]END
        // mod end 2016/02/25 CSA Defect#2684
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_PS);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
        scrnMsg.putErrorScreen();
        // [QC#2886,DEL]START
        //setValue(scrnMsg.xxHldFlg, ZYPConstant.FLG_ON_Y);
        //setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        // [QC#2886,DEL]END

    }
}
