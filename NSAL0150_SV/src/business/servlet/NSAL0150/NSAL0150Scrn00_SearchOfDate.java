/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0150.NSAL0150CMsg;
import business.servlet.NSAL0150.common.NSAL0150CommonLogic;
import business.servlet.NSAL0150.constant.NSAL0150Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/08   Hitachi         S.Moriai         Update          QC#63540
 *</pre>
 */
public class NSAL0150Scrn00_SearchOfDate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

        NSAL0150CMsg bizMsg = new NSAL0150CMsg();
        bizMsg.setBusinessID(NSAL0150Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CMsg bizMsg  = (NSAL0150CMsg) cMsg;
        
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0150CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSAL0150CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0150CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0150CommonLogic.focusItem(scrnMsg);

    }
}
