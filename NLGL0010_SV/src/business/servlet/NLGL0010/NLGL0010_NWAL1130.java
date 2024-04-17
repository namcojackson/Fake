/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLGL0010.NLGL0010CMsg;
//import business.servlet.NLGL0010.constant.NLGL0010Constant;

import business.blap.NLGL0010.NLGL0010CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLGL0010_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_Warehouse".equals(scrEventNm)) {
                scrnMsg.rtlWhCd_01.setValue(scrnMsg.W.no(0).xxComnScrColValTxt.getValue());
                scrnMsg.rtlWhNm_01.setValue(scrnMsg.W.no(1).xxComnScrColValTxt.getValue());
                scrnMsg.whCd_02.setValue(scrnMsg.W.no(4).xxComnScrColValTxt.getValue());
                scrnMsg.invtyOwnrCd_01.setValue(scrnMsg.W.no(3).xxComnScrColValTxt.getValue());
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_01);
            }
        }

        NLGL0010CMsg bizMsg = new NLGL0010CMsg();
        bizMsg.setBusinessID("NLGL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg  = (NLGL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
