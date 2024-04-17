/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSAL0620_NMAL5040 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
//
//        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
//
//        if (FUNC_CD.OPEN_WIN_SRCH_MDL.getFuncCd().equals(scrEventNm)
//                && !NSAL0620CommonLogic.isClosedEvent(getLastGuard())) {
//            scrnMsg.setFocusItem(scrnMsg.mdlDescTxt_H);
//            scrnMsg.mdlDescTxt_H.setValue(scrnMsg.xxCondNm_P.getValue());
//        }
    }
}