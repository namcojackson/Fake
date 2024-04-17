/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0150.common.NSAL0150CommonLogic;
import business.servlet.NSAL0150.constant.NSAL0150Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/09/07   Hitachi         A.Kohinata      Update          QC#15134
 *</pre>
 */
public class NSAL0150Scrn00_OnChange_MeterType_Row extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // del start 2017/09/07 QC#15134
//        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
//        NSAL0150CommonLogic.addCheckItem(scrnMsg);
//        scrnMsg.putErrorScreen();
        // del end 2017/09/07 QC#15134
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // mod start 2017/09/07 QC#15134
//        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
//        NSAL0150CMsg bizMsg = new NSAL0150CMsg();
//        bizMsg.setBusinessID(NSAL0150Constant.BIZ_ID);
//        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_INQ);
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
        return null;
        // mod end 2017/09/07 QC#15134
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        // del start 2017/09/07 QC#15134
//        NSAL0150CMsg bizMsg = (NSAL0150CMsg) cMsg;
        // del end 2017/09/07 QC#15134

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);

        // del start 2017/09/07 QC#15134
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        NSAL0150CommonLogic.addCheckItem(scrnMsg);
//        scrnMsg.putErrorScreen();
//        if ("E".equals(bizMsg.getMessageKind())) {
//            throw new EZDFieldErrorException();
//        }
        // del end 2017/09/07 QC#15134

        NSAL0150CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0150CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0150CommonLogic.focusItem(scrnMsg);
    }
}
