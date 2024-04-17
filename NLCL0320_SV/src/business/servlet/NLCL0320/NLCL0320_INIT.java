/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0320.NLCL0320CMsg;
import business.servlet.NLCL0320.common.NLCL0320CommonLogic;
import business.servlet.NLCL0320.constant.NLCL0320Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0320_INIT  extends S21INITCommonHandler implements NLCL0320Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;

        NLCL0320CMsg bizMsg = new NLCL0320CMsg();
        bizMsg.setBusinessID("NLCL0320");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;
        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H);
        NLCL0320CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

    }

    protected void setNameForMessage(EZDBMsg bMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;
        scrnMsg.rtlWhCd_H.setNameForMessage("Retail Warehouse");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).adjAcctAliasNm_A.setNameForMessage("Adjust Account Alias");
            scrnMsg.A.no(i).adjAcctAliasDescTxt_A.setNameForMessage("Adjust Account Description");
            scrnMsg.A.no(i).xxScrItem130Txt_A.setNameForMessage("Adjust Account Number");
            scrnMsg.A.no(i).rtlWhCd_A.setNameForMessage("Retail Warehouse");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Effective From Date");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("Effective Thru Date");
        }
    }
}
