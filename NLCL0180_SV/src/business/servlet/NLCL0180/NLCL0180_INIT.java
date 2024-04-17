/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLCL0180;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0180.NLCL0180CMsg;
import business.servlet.NLCL0180.common.NLCL0180CommonLogic;
import business.servlet.NLCL0180.constant.NLCL0180Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2009   Fujitsu         FXS)BY.Bao          Create          N/A
 * 05/22/2013   Fujitsu         T.Tozuka            Update          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLCL0180_INIT extends S21INITCommonHandler implements NLCL0180Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NLCL0180");

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0180CMsg bizMsg = null;

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        bizMsg = NLCL0180CommonLogic.setRequestData_NLCL0180_INIT();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0180CMsg bizMsg = (NLCL0180CMsg) cMsg;

        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0180CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NLCL0180CommonLogic.initialize(this, scrnMsg);

        NLCL0180CommonLogic.commonBtnControl_NLCL0180_INIT(this);

        NLCL0180CommonLogic.scrnItemControl_NLCL0180_INIT(scrnMsg);

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {

                setButtonEnabled("Display_VendorName", false);
                setButtonEnabled("Display_MDSEName", false);
                setButtonEnabled("Add_Dtail_Line", false);
                setButtonEnabled("btn1", false);
                setButtonEnabled("btn2", false);
                setButtonEnabled("btn3", false);
                setButtonEnabled("btn4", false);
                setButtonEnabled("btn5", false);
                setButtonEnabled("btn6", false);
                setButtonEnabled("btn7", false);
                setButtonEnabled("btn8", false);
                setButtonEnabled("btn9", false);
                // 2013/05/22 R-OM025 Inventory Transaction Add Start
                scrnMsg.whCd_LK.setInputProtected(true);
                // 2013/05/22 R-OM025 Inventory Transaction Add End
                scrnMsg.vndCd_LK.setInputProtected(true);
                scrnMsg.mdseCd_LK.setInputProtected(true);
            }
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLCL0180BMsg scrnMsg = (NLCL0180BMsg) bMsg;

        scrnMsg.whCd_P1.setNameForMessage("Loc Cd");
        scrnMsg.apVndInvNum.setNameForMessage("Invoice#");
        scrnMsg.arvDt.setNameForMessage("Arrive Date");
        scrnMsg.vndCd.setNameForMessage("Vendor");
        scrnMsg.mdseCd.setNameForMessage("Mdse Cd");
        scrnMsg.xxRqstQty_H1.setNameForMessage("Good Qty");
        scrnMsg.xxRqstQty_H2.setNameForMessage("Defect Qty");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxRqstQty_A1.setNameForMessage("Good Qty");
            scrnMsg.A.no(i).xxRqstQty_A2.setNameForMessage("Defect Qty");
        }
    }
}
