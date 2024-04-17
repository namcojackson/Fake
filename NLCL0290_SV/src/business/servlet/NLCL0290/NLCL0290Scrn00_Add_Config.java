/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0290;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLCL0290.NLCL0290CMsg;
import business.servlet.NLCL0290.common.NLCL0290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/06   CITS            K.Masaki        Create          QC#18472/18490
 *</pre>
 */
public class NLCL0290Scrn00_Add_Config extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)) {
            scrnMsg.svcConfigMstrPk.setErrorInfo(1, "NLCM0219E", new String[]{"Config#"});
            scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        NLCL0290CMsg bizMsg = new NLCL0290CMsg();
        bizMsg.setBusinessID("NLCL0290");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        NLCL0290CMsg bizMsg  = (NLCL0290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL0290CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.putErrorScreen();

        if (ADJ_TRX_TP.ADJUSTMENT.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).adjCatgCd_A);
        } else if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).toRtlSwhCd_A);
        }
    }
}
