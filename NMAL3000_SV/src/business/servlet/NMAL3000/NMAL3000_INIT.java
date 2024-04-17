/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000;

import static business.servlet.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.NMAM8234I;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.BTN_DELLETE_ROW;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3000.NMAL3000CMsg;
import business.servlet.NMAL3000.common.NMAL3000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;


/**
 *<pre>
 * NMAL3000_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/09   SRAA            Y.Chen          Update          QC#5169
 *</pre>
 */
public class NMAL3000_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg = new NMAL3000CMsg();
        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg = (NMAL3000CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL3000CommonLogic.initCmnBtnProp(this);
        setButtonConfirmMsgEx(BTN_DELLETE_ROW, NMAM8234I, new String[] {}, 1);
        NMAL3000CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsAcctCustNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;

        // Header
        scrnMsg.srchOptNm_S .setNameForMessage("Search Option Name");
        scrnMsg.srchOptPk_S.setNameForMessage("Save Search Option");
        scrnMsg.dsAcctCustNum.setNameForMessage("Account Number");
        scrnMsg.mktMdlCd.setNameForMessage("Marketing Model");
        scrnMsg.dsAcctDlrCd.setNameForMessage("Dealer Code");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        scrnMsg.slsAuthFlg_SA.setNameForMessage("Sales");
        scrnMsg.slsAuthFlg_SE.setNameForMessage("Service");
        scrnMsg.dsAcctCustNum_CO.setNameForMessage("Account Number");
        scrnMsg.mktMdlCd_CO.setNameForMessage("Marketing Model");
        scrnMsg.effFromDt_CO.setNameForMessage("Effective Date From");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).dsAcctCustNum_A.setNameForMessage("Account Number");
            scrnMsg.A.no(i).dsAcctDlrCd_A.setNameForMessage("Dealer Code");
            scrnMsg.A.no(i).mktMdlCd_A.setNameForMessage("Marketing Model");
            scrnMsg.A.no(i).slsAuthFlg_A.setNameForMessage("Sales");
            scrnMsg.A.no(i).svcAuthFlg_A.setNameForMessage("Service");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Effective Date From");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("Effective Date To");
        }
    }
}
