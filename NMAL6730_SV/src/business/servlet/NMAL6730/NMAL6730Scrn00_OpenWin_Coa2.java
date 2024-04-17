/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/02/18   Fujitsu         H.Ikeda         UpDate          CSA
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 *</pre>
 */
public class NMAL6730Scrn00_OpenWin_Coa2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
// QC#9448
//
//        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;
//        
//        // QC#6382
//        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
//        
//        NMAL6730CommonLogic.clearParams(scrnMsg);
//
//        Object[] params = new Object[11];
//
//        scrnMsg.xxPopPrm_P0.setValue(FUNC_ID2);
//        params[0] = scrnMsg.xxPopPrm_P0;
//        params[1] = scrnMsg.xxPopPrm_P1;
//        scrnMsg.xxPopPrm_P2.setValue(scrnMsg.coaAfflCd_H1.getValue());
//        params[2] = scrnMsg.xxPopPrm_P2;
//        params[3] = scrnMsg.xxPopPrm_P3;
//        params[4] = scrnMsg.xxPopPrm_P4;
//        params[5] = scrnMsg.xxPopPrm_P5;
//        params[6] = scrnMsg.xxPopPrm_P6;
//        if (ZYPCommonFunc.hasValue(scrnMsg.coaChCd_H1)) {
//            scrnMsg.xxPopPrm_P7.setValue(scrnMsg.coaChCd_H1.getValue());
//            params[7] = scrnMsg.xxPopPrm_P7;
//        } else {
//            params[7] = scrnMsg.coaChCd_BK;
//        }
//        params[8] = scrnMsg.xxPopPrm_P8;
//        params[9] = scrnMsg.xxPopPrm_P9;
//        params[10] = scrnMsg.xxPopPrm_PA;
//
//        setArgForSubScreen(params);
    }
}
