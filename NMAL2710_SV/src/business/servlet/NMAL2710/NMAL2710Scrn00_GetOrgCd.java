/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710;

import static business.servlet.NMAL2710.constant.NMAL2710Constant.BIZ_ID;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.NMAM8368E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2710.NMAL2710CMsg;
import business.servlet.NMAL2710.common.NMAL2710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2710Scrn00_GetOrgCd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Fujitsu         W.Honda         Create          N/A
 * 2016/06/29   Fujitsu         W.Honda         Update          QC#10642
 *</pre>
 */
public class NMAL2710Scrn00_GetOrgCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        NMAL2710CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL2710CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();

        scrnMsg.orgCd_DC.clear();
        if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_DC)) {
            // QC#10642 2016/06/29 Mod start
//            scrnMsg.setMessageInfo(NMAM8368E, new String[] {scrnMsg.orgNm_DC.getNameForMessage()});
//            throw new EZDFieldErrorException();
            scrnMsg.orgNm_DC.setErrorInfo(1, NMAM8368E, new String[] {scrnMsg.orgNm_DC.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.orgNm_DC);
            // QC#10642 2016/06/29 Mod end
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;
        NMAL2710CMsg bizMsg = new NMAL2710CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;
        NMAL2710CMsg bizMsg  = (NMAL2710CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            NMAL2710CommonLogic.addCheckItemForDetailControl(scrnMsg);
            scrnMsg.putErrorScreen();
        }

        scrnMsg.setFocusItem(scrnMsg.orgNm_DC);
    }
}
