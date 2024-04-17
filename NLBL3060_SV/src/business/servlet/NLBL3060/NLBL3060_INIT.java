/*
 * <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.servlet.NLBL3060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3060.NLBL3060CMsg;
import business.servlet.NLBL3060.common.NLBL3060CommonLogic;
import business.servlet.NLBL3060.constant.NLBL3060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060_INIT extends S21INITCommonHandler implements NLBL3060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), APPLICATION_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;

        NLBL3060CMsg bizMsg = new NLBL3060CMsg();
        bizMsg.setBusinessID("NLBL3060");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
        NLBL3060CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

    }

    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        scrnMsg.rtlWhCd.setNameForMessage("Retail Warehouse");
        scrnMsg.schdCoordPsnCd.setNameForMessage("Person Code");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // START 2023/10/18 Y.Ogura [QC#61793, ADD]
            scrnMsg.A.no(i).rtlWhCatgCd_A1.setNameForMessage("WH Type");
            scrnMsg.A.no(i).physWhCd_A1.setNameForMessage("Group Name");
            // END 2023/10/18 Y.Ogura [QC#61793, ADD]
            scrnMsg.A.no(i).rtlWhCd_A1.setNameForMessage("Retail Warehouse");
            scrnMsg.A.no(i).schdCoordPsnCd_A1.setNameForMessage("Person Code");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Effective From Date");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Effective Thru Date");
        }
    }
}
