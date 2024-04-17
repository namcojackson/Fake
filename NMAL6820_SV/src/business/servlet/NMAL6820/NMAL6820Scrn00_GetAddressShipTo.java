/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
                                                  // business.blap.NMAL6820.NMAL6820CMsg;
// import business.servlet.NMAL6820.constant.NMAL6820Constant;

import business.blap.NMAL6820.NMAL6820CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/02   CITS            S.Endo          Create          QC#10838
 *</pre>
 */
public class NMAL6820Scrn00_GetAddressShipTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.postCd_S1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = new NMAL6820CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.postCd_S1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_S1);
        scrnMsg.addCheckItem(scrnMsg.stCd_S1);
        scrnMsg.addCheckItem(scrnMsg.cntyPk_S1);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.postCd_S1);
    }
}
