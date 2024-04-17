/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3100;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLBL3100.NLBL3100CMsg;
//import business.servlet.NLBL3100.constant.NLBL3100Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NLBL3100Scrn00_SelectManager extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3100BMsg scrnMsg = (NLBL3100BMsg) bMsg;

        int index = getButtonSelectNumber();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            ((EZDBStringItem) params[1]).setValue(scrnMsg.A.no(index).schdCoordPsnCd_A1.getValue());
            ((EZDBStringItem) params[2]).setValue(scrnMsg.A.no(index).mgrPsnCd_A1.getValue());
            ((EZDBStringItem) params[3]).setValue(scrnMsg.A.no(index).supvPsnCd_A1.getValue());
            ((EZDBStringItem) params[4]).setValue(scrnMsg.A.no(index).carrCd_A1.getValue());
            ((EZDBStringItem) params[5]).setValue(scrnMsg.A.no(index).stCd_A1.getValue());
            ((EZDBStringItem) params[6]).setValue(scrnMsg.A.no(index).xxPsnNm_A1.getValue());
            ((EZDBStringItem) params[7]).setValue(scrnMsg.A.no(index).xxPsnNm_A2.getValue());
            ((EZDBStringItem) params[8]).setValue(scrnMsg.A.no(index).xxPsnNm_A3.getValue());
            ((EZDBStringItem) params[9]).setValue(scrnMsg.A.no(index).locNm_A1.getValue());
        }
    }
}
