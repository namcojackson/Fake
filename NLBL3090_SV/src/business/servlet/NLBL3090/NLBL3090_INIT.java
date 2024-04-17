/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3090;

import static business.servlet.NLBL3090.constant.NLBL3090Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.TAB_ASSIGN;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3090.NLBL3090CMsg;
import business.servlet.NLBL3090.common.NLBL3090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * Function Name : Init
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NLBL3090_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        NLBL3090CMsg bizMsg = new NLBL3090CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        NLBL3090CMsg bizMsg = (NLBL3090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // init
        NLBL3090CommonLogic.initCommonButton(this);
        NLBL3090CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLBL3090CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.B.no(0).getBaseContents());

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            scrnMsg.xxDplyTab.setValue(TAB_ASSIGN);
        }
        NLBL3090CommonLogic.control(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;

        scrnMsg.rtlWhCd_H1.setNameForMessage("Warehouse");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).mgrPsnCd_AM.setNameForMessage("Manager code");
            scrnMsg.A.no(i).supvPsnCd_AS.setNameForMessage("Supervisor code");
            scrnMsg.A.no(i).schdCoordPsnCd_AC.setNameForMessage("Coordinator code");
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).schdCoordPsnCd_BC.setNameForMessage("Coordinator code");
            scrnMsg.B.no(i).stCd_B.setNameForMessage("State code");
            scrnMsg.B.no(i).effFromDt_B.setNameForMessage("Effective Date (From)");
            scrnMsg.B.no(i).effThruDt_B.setNameForMessage("Effective Date (To)");
            scrnMsg.B.no(i).carrCd_B.setNameForMessage("Carrier code");
            scrnMsg.B.no(i).carrCtacEmlAddr_B.setNameForMessage("Carrier Contact");
            scrnMsg.B.no(i).carrCtacTelNum_B.setNameForMessage("Contact Phone");
        }
    }
}
