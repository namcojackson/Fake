/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL0090;

import static business.servlet.NLBL0090.constant.NLBL0090Constant.BIZ_APP_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.RADIO_BUTTON;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.SRCH_FUNCTION_ID;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.STATUS_DATE_FROM;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.STATUS_DATE_TO;
import static business.servlet.NLBL0090.constant.NLBL0090Constant.WH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL0090.NLBL0090CMsg;
import business.servlet.NLBL0090.common.NLBL0090CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 *</pre>
 */
public class NLBL0090_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = new NLBL0090CMsg();

        // put globalCompanyCD
        scrnMsg.glblCmpyCd_Z1.setValue(getGlobalCompanyCode());

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(SRCH_FUNCTION_ID);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        NLBL0090CMsg bizMsg = (NLBL0090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL0090CommonLogic.initGuiAttrForBOLTrckngTab(scrnMsg, this);

        NLBL0090CommonLogic.showBOLTrckngTab(scrnMsg, this);

        NLBL0090CommonLogic.setTblBackColorAndUnitCellsA(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.bolNum_H1);

        // Radio button status
        scrnMsg.xxRadioBtn_A1.clear();
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL0090BMsg scrnMsg = (NLBL0090BMsg) bMsg;
        scrnMsg.whCd_H1.setNameForMessage(WH);
        scrnMsg.podStsDt_H1.setNameForMessage(STATUS_DATE_FROM);
        scrnMsg.podStsDt_H2.setNameForMessage(STATUS_DATE_TO);
        scrnMsg.xxRadioBtn_A1.setNameForMessage(RADIO_BUTTON);
    }

}
