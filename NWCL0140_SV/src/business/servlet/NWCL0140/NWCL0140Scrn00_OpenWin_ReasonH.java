/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0140;

import static business.servlet.NWCL0140.constant.NWCL0140Constant.BIZ_APP_ID;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_OPEN_WIN_REASON_H;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.ORD_CATEGORY;
import static business.servlet.NWCL0140.constant.NWCL0140Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0140.NWCL0140CMsg;
import business.servlet.NWCL0140.common.NWCL0140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : NWCL0140Scrn00_OpenWin_ReasonH
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 */
public class NWCL0140Scrn00_OpenWin_ReasonH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0140BMsg scrnMsg = (NWCL0140BMsg) bMsg;
        scrnMsg.dsOrdCatgDescTxt.clearErrorInfo();

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            scrnMsg.dsOrdCatgDescTxt.setErrorInfo(1, ZZM9000E, new String[] {ORD_CATEGORY });
            scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.xxRowNum.clear();
        }
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

        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.putErrorScreen();

        scrnMsg.xxScrEventNm.setValue(EVENT_NM_NWCL0140_OPEN_WIN_REASON_H);
        Object[] params = NWCL0140CommonLogic.getParamNWAL1130ForOrdRsn(getUserProfileService().getGlobalCompanyCode(), scrnMsg, scrnMsg.dsOrdTpDescTxt, scrnMsg.dsOrdCatgCd);
        setArgForSubScreen(params);
    }
}
