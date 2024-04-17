package business.servlet.NLBL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0060.NLBL0060CMsg;
import business.servlet.NLBL0060.common.NLBL0060CommonLogic;
import business.servlet.NLBL0060.constant.NLBL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * When Event[INIT] in BusinessID NLBL0060 is generated, this class processes it. 
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/02   Fujitsu         D.Fukaya        Create          N/A
 * </pre>
 */
public class NLBL0060_INIT extends S21INITCommonHandler implements NLBL0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
        NLBL0060CMsg bizMsg = new NLBL0060CMsg();

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0060BMsg scrnMsg = (NLBL0060BMsg) bMsg;
        NLBL0060CMsg bizMsg = (NLBL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL0060CommonLogic.setInitValue(scrnMsg);
        NLBL0060CommonLogic.cntrlDispScrnItem(this, scrnMsg);

        setButtonConfirmMsg(BTN_CMN_BTN_9[1], NLBM0070W, new String[] {BTN_CMN_BTN_9[2] }, 0);
        setButtonConfirmMsg(BTN_CMN_BTN_10[1], NZZM0004W, new String[] {BTN_CMN_BTN_10[2] }, 0);

        scrnMsg.setFocusItem(scrnMsg.whCd_H2);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NLBL0060BMsg scrnMsg = (NLBL0060BMsg) arg0;


        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        scrnMsg.whCd_H2.setNameForMessage(NAME_FOR_MESSAGE_WH);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // 2013/05/21 R-OM025 Inventory Transaction Modify Start
            scrnMsg.A.no(i).whCd_A1.setNameForMessage(NAME_FOR_MESSAGE_WH);
            // 2013/05/21 R-OM025 Inventory Transaction Modify End
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage(NAME_FOR_MESSAGE_EFF_FROM_DT);
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage(NAME_FOR_MESSAGE_EFF_THRU_DT);
            scrnMsg.A.no(i).shpgCloTmTs_A1.setNameForMessage(NAME_FOR_MESSAGE_SHPG_CLO_TM_TS);
            scrnMsg.A.no(i).xxDplyLeadTmDaysAot_A1.setNameForMessage(NAME_FOR_MESSAGE_PICK_PACK_AOT);
        }
    }
}
