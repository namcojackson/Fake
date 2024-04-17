/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BIZ_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.EZD_FUNC_CD_UPD;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2460.NMAL2460CMsg;
import business.servlet.NMAL2460.common.NMAL2460CommonLogic;
import business.servlet.NMAL2460.constant.NMAL2460Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SRCH_LYOT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NMAL2460Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        if (!SRCH_LYOT_TP.LAYOUT_2.equals(scrnMsg.srchLyotTpCd.getValue())) {
            scrnMsg.setMessageInfo(NMAL2460Constant.NMAM8539E);
            throw new EZDFieldErrorException();
        }

        NMAL2460CommonLogic.addCheckSearchResult(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        NMAL2460CMsg bizMsg = new NMAL2460CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;
        NMAL2460CMsg bizMsg = (NMAL2460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        NMAL2460CommonLogic.setRowColors(scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            NMAL2460CommonLogic.addCheckSearchResult(scrnMsg);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        NMAL2460CommonLogic.protectSearchResult(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_H);
    }
}
