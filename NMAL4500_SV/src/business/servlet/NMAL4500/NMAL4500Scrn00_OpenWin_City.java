/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL4500;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL4500.common.NMAL4500CommonLogic;
import business.servlet.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   SRA             T.Chijimatsu    Create          N/A
 * 08/05/2013   Fujitsu         N.Sugiura       Update          QC1469
 * 07/28/2016   CITS            S.Endo          Update          QC#10840
 *</pre>
 */
public class NMAL4500Scrn00_OpenWin_City extends S21CommonHandler implements NMAL4500Constant {

@Override
protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    // no process.
}

 @Override
protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // no process.
     return null;
}

@Override
protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
    ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, LINK_OPENWIN_CTY_EVENT_NM);
    Object[] params = NMAL4500CommonLogic.getAddressPopupParam(scrnMsg, getGlobalCompanyCode());
    setArgForSubScreen(params);
}

}
