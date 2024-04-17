/*
 * <pre>Copyright (c) 2024 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2010;

import static business.servlet.NWAL2010.constant.NWAL2010Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2010.NWAL2010CMsg;
import business.servlet.NWAL2010.common.NWAL2010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2010_NWAL2020
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/03/29   CITS            M.Kobayashi     Create          N/A
 * 2024/04/10   CITS            M.Okamura       Update          QC#63757
 *</pre>
 */
public class NWAL2010_NWAL2020 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Start 2024/04/10 M.Okamura [QC#63757,MOD]
        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;

        NWAL2010CMsg bizMsg = new NWAL2010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // return null;
        // End 2024/04/10 M.Okamura [QC#63757,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;
        // Start 2024/04/10 M.Okamura [QC#63757,ADD]
        NWAL2010CMsg bizMsg = (NWAL2010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2010CommonLogic.initCmnBtnProp(this);
        NWAL2010CommonLogic.setCmnBtnProp(scrnMsg, this);
        NWAL2010CommonLogic.setBgColor(scrnMsg);
        //scrnMsg.setFocusItem(scrnMsg.crCardCustRefNum);
        // End 2024/04/10 M.Okamura [QC#63757,ADD]
        scrnMsg.setFocusItem(scrnMsg.sellToCustCd);
    }
}
