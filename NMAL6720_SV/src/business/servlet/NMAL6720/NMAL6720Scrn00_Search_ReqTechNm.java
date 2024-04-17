/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6720.NMAL6720CMsg;
//import business.servlet.NMAL6720.constant.NMAL6720Constant;

import business.blap.NMAL6720.NMAL6720CMsg;
import business.servlet.NMAL6720.constant.NMAL6720Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6720Scrn00_Search_ReqTechNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.reqTechCd_SA)) {
            scrnMsg.reqTechCd_SA.setErrorInfo(1, NMAL6720Constant.ZZZM9024E, new String[] {"Requested Technician Code" });
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID("NMAL6720");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg  = (NMAL6720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.reqTechCd_SA);
        scrnMsg.putErrorScreen();
    }
}
