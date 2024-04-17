/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0010.NSAL0010CMsg;
//import business.servlet.NSAL0010.constant.NSAL0010Constant;

import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.constant.NSAL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NSAL0010Scrn00_SearchSellToCustName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H1);
//        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_H1.getValue())) {
//            scrnMsg.sellToCustCd_H1.setErrorInfo(1, NSAL0010Constant.NSAM0006E);
//        }
//        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//
//        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
//        bizMsg.setBusinessID(NSAL0010Constant.BUSINESS_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
//        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;
//
//        if (bizMsg != null) {
//            EZDMsg.copy(bizMsg, null, scrnMsg, null);
//        } else {
//            scrnMsg.sellToCustLocNm_H1.clear();
//        }
//        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H1);
//        scrnMsg.putErrorScreen();
//
//        if (!scrnMsg.sellToCustCd_H1.isError()) {
//            scrnMsg.setFocusItem(scrnMsg.sellToCustCd_H1);
//        }
    }
}
