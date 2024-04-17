/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0060;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0060.NLGL0060CMsg;
import business.servlet.NLGL0060.common.NLGL0060CommonLogic;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.BUSINESS_ID;
import static business.servlet.NLGL0060.constant.NLGL0060Constant.FUNC_SRCH;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/09   CITS            M.Furugoori         Create          N/A
 *</pre>
 */
public class NLGL0060_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        // checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0060BMsg scrnMsg = (NLGL0060BMsg) bMsg;

        NLGL0060CMsg bizMsg = new NLGL0060CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0060BMsg scrnMsg = (NLGL0060BMsg) bMsg;
        NLGL0060CMsg bizMsg  = (NLGL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        NLGL0060CommonLogic.ctrlScrnItem(this, scrnMsg, funcList);

        scrnMsg.setFocusItem(scrnMsg.whCd_H);
    }

    protected void setNameForMessage(EZDBMsg bMsg) {

        NLGL0060BMsg scrnMsg = (NLGL0060BMsg) bMsg;

        // Header.
        scrnMsg.whCd_H.setNameForMessage("WH Code");
        scrnMsg.wmsTaskCd_H.setNameForMessage("Task Code");
        scrnMsg.procStsCd_H.setNameForMessage("Process Status");
        scrnMsg.wmsInbdTrxPk_H.setNameForMessage("Transaction ID");
        scrnMsg.wmsMdseCd_H.setNameForMessage("Item Number");

        // Detail.
        for (int rowIndex = 0; rowIndex < scrnMsg.A.getValidCount(); rowIndex++) {
            scrnMsg.A.no(rowIndex).xxChkBox_A1.setNameForMessage("Delete Flag");
            scrnMsg.A.no(rowIndex).wmsInbdTrxPk_A1.setNameForMessage("Transaction ID");
            scrnMsg.A.no(rowIndex).ezInTime_A1.setNameForMessage("Register Date");
            scrnMsg.A.no(rowIndex).ezUpTime_A1.setNameForMessage("Updated Date");
            scrnMsg.A.no(rowIndex).whCd_A1.setNameForMessage("WH Code");
            scrnMsg.A.no(rowIndex).wmsTaskCd_A1.setNameForMessage("Task Code");
            scrnMsg.A.no(rowIndex).procStsCd_A1.setNameForMessage("Process Status");
            scrnMsg.A.no(rowIndex).errMsgCd_A1.setNameForMessage("Error Message CD");
            scrnMsg.A.no(rowIndex).wmsMdseCd_A1.setNameForMessage("Item Number");
            scrnMsg.A.no(rowIndex).serNum_A1.setNameForMessage("Serial #");
            scrnMsg.A.no(rowIndex).wmsOrdQty_A1.setNameForMessage("Qty");
            scrnMsg.A.no(rowIndex).otbdOrdNum_A1.setNameForMessage("Otbd Ord #");
            scrnMsg.A.no(rowIndex).inbdOrdNum_A1.setNameForMessage("Inbd Ord #");
        }
    }

}
