/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1060;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1060.NLCL1060CMsg;
import business.servlet.NLCL1060.common.NLCL1060CommonLogic;
import business.servlet.NLCL1060.constant.NLCL1060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * 3rd Party Onhand Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public class NLCL1060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLCL1060Constant.MY_BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

         NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;
         NLCL1060CMsg bizMsg = new NLCL1060CMsg();

         Serializable arg = getArgForSubScreen();

         if (arg instanceof Object[]) {
             Object[] params = (Object[]) arg;
             setValue(scrnMsg.mdseCd_H, (EZDBStringItem) params[0]);
             setValue(scrnMsg.xxChkBox, ZYPConstant.CHKBOX_ON_Y);
         }
         ZYPTableUtil.clear(scrnMsg.A);
         scrnMsg.xxPageShowFromNum_A1.setValue(1);
         scrnMsg.xxPageShowToNum_A1.clear();
         scrnMsg.xxPageShowOfNum_A1.clear();
         bizMsg.setBusinessID(NLCL1060Constant.MY_BUSINESS_ID);
         bizMsg.setFunctionCode(NLCL1060Constant.FUNCTION_ID_SEARCH);
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
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLCL1060BMsg scrnMsg = (NLCL1060BMsg) bMsg;
        scrnMsg.mdseCd_H.setNameForMessage("Item Number");
        scrnMsg.vndCd.setNameForMessage("Supplier Site Code");
    }
}