/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/17   Fujitsu         T.Nishikawa     Create          CSA
 *</pre>
 */
public class NPAL1090Scrn00_OpenWinLocTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        // NPAL1090CMsg bizMsg = new NPAL1090CMsg();
        // bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        // NPAL1090CMsg bizMsg = (NPAL1090CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // scrnMsg.P.clear();
        //
        // int idx = 0;
        //
        // scrnMsg.P.no(idx++).xxPopPrm_H1.setValue(scrnMsg.rqstRtlWhCd_H1.getValue());
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.setValue(NMXC100001EnableWH.getLocRoleTpForPopup(getGlobalCompanyCode(),
        // LOC_TP.TECHNICIAN));
        // scrnMsg.P.no(idx++).xxPopPrm_H1.setValue(ZYPConstant.FLG_OFF_N);
        // scrnMsg.P.no(idx++).xxPopPrm_H1.setValue(ZYPConstant.FLG_OFF_N);
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.setValidCount(idx);
        //
        // Object[] param = new Object[idx];
        // for (int i = 0; i < idx; i++) {
        // param[i] = scrnMsg.P.no(i).xxPopPrm_H1;
        // }
        //
        // setArgForSubScreen(param);

    }

}
