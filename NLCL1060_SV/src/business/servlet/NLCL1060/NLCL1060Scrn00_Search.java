/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1060;

import static business.servlet.NLCL1060.constant.NLCL1060Constant.FUNCTION_ID_SEARCH;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.MESSAGE_KIND_E;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.MY_BUSINESS_ID;
import static business.servlet.NLCL1060.constant.NLCL1060Constant.NLCM0135E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1060.NLCL1060CMsg;
import business.servlet.NLCL1060.common.NLCL1060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * 3rd Party Onhand Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H) && !ZYPCommonFunc.hasValue(scrnMsg.vndCd)) {
                scrnMsg.mdseCd_H.setErrorInfo(1, NLCM0135E, new String[]{"Item Number", "Supplier Site Code"});
                scrnMsg.vndCd.setErrorInfo(1, NLCM0135E, new String[]{"Item Number", "Supplier Site Code"});
                scrnMsg.setMessageInfo(NLCM0135E);
        }
        scrnMsg.addCheckItem(scrnMsg.xxChkBox);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

         NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;
         NLCL1060CMsg bizMsg = new NLCL1060CMsg();
         bizMsg.setBusinessID(MY_BUSINESS_ID);
         bizMsg.setFunctionCode(FUNCTION_ID_SEARCH);
         EZDMsg.copy(scrnMsg, null, bizMsg, null);

         return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;
         NLCL1060CMsg bizMsg = (NLCL1060CMsg) cMsg;

         EZDMsg.copy(bizMsg, null, scrnMsg, null);
         NLCL1060CommonLogic.initButton(this, getUserProfileService(), scrnMsg);
         NLCL1060CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());
         if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
             throw new EZDFieldErrorException();
         }
    }

}
