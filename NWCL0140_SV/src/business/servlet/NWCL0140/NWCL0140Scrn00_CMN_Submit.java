/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.BIZ_APP_ID;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_CMN_SUBMIT;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.INV_THRES_HOLD;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0140.NWCL0140CMsg;
import business.servlet.NWCL0140.common.NWCL0140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : NWCL0140Scrn00_CMN_Submit
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0140Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.attrbValNum);
        if (!scrnMsg.attrbValNum.isError()) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.attrbValNum)) {
                scrnMsg.attrbValNum.setErrorInfo(1, ZZM9000E, new String[] {INV_THRES_HOLD });
                throw new EZDFieldErrorException();
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;

        NWCL0140CMsg bizMsg = new NWCL0140CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        bizMsg.xxScrEventNm.setValue(EVENT_NM_NWCL0140_CMN_SUBMIT);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        NWCL0140CMsg bizMsg = (NWCL0140CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWCL0140CommonLogic.setTableColumnAttr(scrnMsg);
        NWCL0140CommonLogic.chkErrorInfo(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).billToCustAcctCd_EL);
        }
    }
}
