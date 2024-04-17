/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
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
 * 2016/03/29   Hitachi         T.Tomita        Create          QC#4393
 * 2018/05/10   Hitachi         K.Kojima        Update          QC#24817
 *</pre>
 */
public class NSAL0150Scrn00_ChangeDsMtrReadTpGrpCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2018/05/10 K.Kojima [QC#24817,MOD]
        // return null;
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CMsg bizMsg = new NSAL0150CMsg();
        bizMsg.setBusinessID(NSAL0150Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        // END 2018/05/10 K.Kojima [QC#24817,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        // START 2018/05/10 K.Kojima [QC#24817,ADD]
        NSAL0150CMsg bizMsg = (NSAL0150CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // END 2018/05/10 K.Kojima [QC#24817,ADD]
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSAL0150CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0150CommonLogic.alternateTableRowColor(scrnMsg);
        if (scrnMsg.B.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.B.no(0).dsMtrReadTpCd_B);
        }
    }
}
