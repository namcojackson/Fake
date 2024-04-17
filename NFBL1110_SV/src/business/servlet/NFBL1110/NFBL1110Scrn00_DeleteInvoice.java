/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL1110.NFBL1110CMsg;
import business.servlet.NFBL1110.common.NFBL1110CommonLogic;
import business.servlet.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   CUSA            Y.Aikawa        Create          N/A
 * </pre>
 */
public class NFBL1110Scrn00_DeleteInvoice extends S21CommonHandler implements NFBL1110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        if (!scrnMsg.invFlg_NE.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            // Mandatory check
            scrnMsg.addCheckItem(scrnMsg.vndSiteNm_IH);
            scrnMsg.addCheckItem(scrnMsg.apInvNum_IH);
            scrnMsg.putErrorScreen();

            for (int i = 0; i < scrnMsg.Y.getValidCount(); i++) {
                if (scrnMsg.apInvNum_IH.getValue().equals(scrnMsg.Y.no(i).apInvNum_Y1.getValue())
                &&  scrnMsg.apVndCd_HD.getValue().equals(scrnMsg.Y.no(i).apVndCd_Y1.getValue())
                ) {
                    if (i != scrnMsg.xxListNum_Y.getValueInt()) {
                        scrnMsg.apInvNum_IH.setErrorInfo(1, NFBM0041E, new String[] {"Invoice #"});
                        scrnMsg.vndSiteNm_IH.setErrorInfo(1, NFBM0041E, new String[] {"Supplier Site"});
                    }
                }
            }
            scrnMsg.addCheckItem(scrnMsg.apInvNum_IH);
            scrnMsg.addCheckItem(scrnMsg.vndSiteNm_IH);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        NFBL1110CMsg bizMsg = new NFBL1110CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        NFBL1110CMsg bizMsg  = (NFBL1110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Initialize input control */ 
        NFBL1110CommonLogic.initControl(this, scrnMsg);
        /** Set focus when opening screen */
        scrnMsg.setFocusItem(scrnMsg.apInvNum_IH);

    }
}
