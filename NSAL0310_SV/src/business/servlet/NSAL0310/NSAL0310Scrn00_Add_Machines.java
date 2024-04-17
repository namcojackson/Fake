/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0310.NSAL0310CMsg;
import business.servlet.NSAL0310.common.NSAL0310CommonLogic;
import business.servlet.NSAL0310.constant.NSAL0310Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2015/10/08   Hitachi         T.Tomita        Update          N/A
 * 2017/03/01   Hitachi         N.Arai          Update          QC#17620
 * 2022/01/21   CITS            R.Cabral        Update          QC#59502
 *</pre>
 */
public class NSAL0310Scrn00_Add_Machines extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CMsg bizMsg = new NSAL0310CMsg();
        bizMsg.setBusinessID(NSAL0310Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0310Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CMsg bizMsg = (NSAL0310CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0310Constant.BIZ_ID);
        NSAL0310CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2017/03/01 N.Arai [QC#17620, MOD]
//        NSAL0310CommonLogic.addCheckItem(scrnMsg);
        NSAL0310CommonLogic.addCheckItem_AddMachines(scrnMsg);
//        scrnMsg.putErrorScreen();
//        if ("E".equals(bizMsg.getMessageKind())) {
//            throw new EZDFieldErrorException();
//        }

        NSAL0310CommonLogic.setupScreenItems(scrnMsg, functionList);
        NSAL0310CommonLogic.alternateTableRowColor(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END 2017/03/01 N.Arai [QC#17620, MOD]
        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                // START 2015/10/08 T.Tomita [N/A, MOD]
                if (prm.length > 1 && prm[1] != null && prm[1] instanceof EZDBMsgArray) {
                    EZDMsg.copy(scrnMsg.P, "P", ((EZDBMsgArray) prm[1]), "P");
                    // START 2022/01/21 R.Cabral [QC#59502, ADD]
                    EZDMsg.copy(scrnMsg.P, "P1", ((EZDBMsgArray) prm[1]), "P1");
                    // END   2022/01/21 R.Cabral [QC#59502, ADD]
                }
                // END 2015/10/08 T.Tomita [N/A, MOD]
            }
        }
    }
}
