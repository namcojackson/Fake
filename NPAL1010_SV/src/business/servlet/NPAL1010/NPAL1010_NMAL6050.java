/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010;

import static business.servlet.NPAL1010.constant.NPAL1010Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.EVENT_NM_OPENWIN_MGR;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.EVENT_NM_SEARCH_STATECODE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/26/2012   Fujitsu         S.Noguchi       Create          N/A
 * 05/04/2016   CSAI            D.Fukaya        Update          QC#7629
 *</pre>
 */
public class NPAL1010_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (EVENT_NM_OPENWIN_MGR.equals(scrnMsg.xxPopPrm_EV.getValue())) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_H1, scrnMsg.fullPsnNm_G1);
                scrnMsg.setFocusItem(scrnMsg.whMgrPsnCd_H1);

            } else if (EVENT_NM_SEARCH_STATECODE.equals(scrnMsg.xxPopPrm_EV.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.stCd);
            }
        }
        
    }
}
