/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/01/23   Fujitsu         H.ikeda         Create          QC#54902
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 *</pre>
 */
public class NFCL2760Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        // START 2022/01/06 G.Delgado [QC#59329, ADD]
        // Details position Initialize
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/01/06 G.Delgado [QC#59329, ADD]

        if (!ZYPCommonFunc.hasValue(scrnMsg.arCustRefNum_H1) ) {
            scrnMsg.arCustRefNum_H1.setErrorInfo(1, "NFCM0911E");
            scrnMsg.setMessageInfo("ZZM9037E");
        }
        scrnMsg.addCheckItem(scrnMsg.arCustRefNum_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760CMsg bizMsg = null;
        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760Scrn00_Search(scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL2760CommonLogic.initialize(this, scrnMsg);
        NFCL2760CommonLogic.commonBtnControl_NFCL2760Scrn00_SearchTrxLine(this);
        NFCL2760CommonLogic.setRowBg(scrnMsg);
        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);
        NFCL2760CommonLogic.transMsgCheck(scrnMsg);
        if (scrnMsg.arCustRefNum_H1.isError()) {
            scrnMsg.setFocusItem(scrnMsg.arCustRefNum_H1);
            scrnMsg.putErrorScreen();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxCellIdx_H1) ) {
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx_H1.getValueInt()).arCustRefNum);
        } else {
            scrnMsg.setFocusItem(scrnMsg.arCustRefNum_H1);
            scrnMsg.putErrorScreen();
        }
    }
}
