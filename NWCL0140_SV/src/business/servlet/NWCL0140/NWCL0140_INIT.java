/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.ACCT_NUM;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.BIZ_APP_ID;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.INCLUDE;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.INV_THRES_HOLD;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.LOC_NUM;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.ORD_CATEGORY;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.REASON;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0140.NWCL0140CMsg;
import business.servlet.NWCL0140.common.NWCL0140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : INIT
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0140_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;

        NWCL0140CMsg bizMsg = new NWCL0140CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        NWCL0140CMsg bizMsg = (NWCL0140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0140CommonLogic.setCommonButtonInit(this);
        NWCL0140CommonLogic.setTableColumnAttr(scrnMsg);
        NWCL0140CommonLogic.chkErrorInfo(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).billToCustAcctCd_EL);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg msg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) msg;
        scrnMsg.attrbValNum.setNameForMessage(INV_THRES_HOLD);
        scrnMsg.xxPageShowCurNum_EL.setNameForMessage("Current Page Number");
        scrnMsg.xxPageShowCurNum_EX.setNameForMessage("Current Page Number");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).billToCustAcctCd_EL.setNameForMessage(ACCT_NUM);
            scrnMsg.A.no(i).billToCustCd_EL.setNameForMessage(LOC_NUM);
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).dsOrdCatgDescTxt_EX.setNameForMessage(ORD_CATEGORY);
            scrnMsg.B.no(i).dsOrdTpDescTxt_EX.setNameForMessage(REASON);
            scrnMsg.B.no(i).ordCatgInclFlg_EX.setNameForMessage(INCLUDE);
        }
    }
}
