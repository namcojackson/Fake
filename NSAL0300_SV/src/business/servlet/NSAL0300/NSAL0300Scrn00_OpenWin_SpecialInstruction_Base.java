/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/30   Hitachi         K.Kim           Create          QC#14307(Sol#020)
 * 2018/08/30   Hitachi         A.Kohinata      Update          QC#27961
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_SpecialInstruction_Base extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2018/08/30 QC#27961
//        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
//        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
//        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
//        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
        return null;
        // mod end 2018/08/30 QC#27961
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        // del start 2018/08/30 QC#27961
//        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;
        // del end 2018/08/30 QC#27961
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);

        // del start 2018/08/30 QC#27961
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
//        NSAL0300CommonLogic.addCheckItem(scrnMsg);
//
//        scrnMsg.putErrorScreen();
//        if ("E".equals(bizMsg.getMessageKind())) {
//            throw new EZDFieldErrorException();
//        }
        // del end 2018/08/30 QC#27961

        int rowNum = getButtonSelectNumber();
        Object[] params = NSAL0300CommonLogic.getParamNMAL3300(scrnMsg, getGlobalCompanyCode(), scrnMsg.B.no(rowNum).baseBillToCustCd_B.getValue());
        setArgForSubScreen(params);
    }
}
