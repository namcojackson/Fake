/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0150;

import static business.servlet.NWCL0150.constant.NWCL0150Constant.BIZ_APP_ID;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.CFS_LEASE_PKG_HLD_FLG;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.CPO_ORD_NUM;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.INVOICE_NUMBER;
import static business.servlet.NWCL0150.constant.NWCL0150Constant.LEASE_PKG_CRAT_FLG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0150.NWCL0150CMsg;
import business.servlet.NWCL0150.common.NWCL0150CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NWCL0150:Lease Package Hold Release Screen
 * Function Name : INIT
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0150_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0150BMsg scrnMsg = (NWCL0150BMsg) bMsg;

        NWCL0150CMsg bizMsg = new NWCL0150CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0150BMsg scrnMsg = (NWCL0150BMsg) bMsg;
        NWCL0150CMsg bizMsg = (NWCL0150CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0150CommonLogic.setCommonButtonInit(this);
        NWCL0150CommonLogic.setAttr(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg msg) {
        NWCL0150BMsg scrnMsg = (NWCL0150BMsg) msg;
        scrnMsg.cpoOrdNum.setNameForMessage(CPO_ORD_NUM);
        scrnMsg.invNum.setNameForMessage(INVOICE_NUMBER);
        scrnMsg.cfsLeasePkgHldFlg_SL.setNameForMessage(CFS_LEASE_PKG_HLD_FLG);
        scrnMsg.leasePkgCratFlg_SL.setNameForMessage(LEASE_PKG_CRAT_FLG);
    }
}
