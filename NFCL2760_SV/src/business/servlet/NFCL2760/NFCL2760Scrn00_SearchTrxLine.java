/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2022/01/06   CITS            G.Delgado       Update          QC#59329
 *</pre>
 */
public class NFCL2760Scrn00_SearchTrxLine extends S21CommonHandler implements NFCL2760Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

        // START 2022/01/06 G.Delgado [QC#59329, ADD]
        // Details position Initialize
        scrnMsg.xxListNum_LY.setValue(0);
        // END 2022/01/06 G.Delgado [QC#59329, ADD]

        NFCL2760CommonLogic.chkSubmit(scrnMsg, "NFCL2760Scrn00_SearchTrxLine");
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;
        NFCL2760CMsg bizMsg = null;
        int lineNum = getButtonSelectNumber();

        scrnMsg.xxCellIdx_H1.setValue(lineNum);

        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760Scrn00_SearchTrxLine();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxPageShowFromNum_H1.setValue(scrnMsg.xxPageShowFromNum_H1.getValueInt());
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    	NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;
        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
        
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if (!scrnMsg.A.no(getButtonSelectNumber()).arCustRefNum.isError()) {
            NFCL2760CommonLogic.initialize(this, scrnMsg);

            NFCL2760CommonLogic.commonBtnControl_NFCL2760Scrn00_SearchTrxLine(this);

            NFCL2760CommonLogic.setRowBg(scrnMsg);

            //NFCL2760CommonLogic.protectAddDetailLine(scrnMsg, this);
            NFCL2760CommonLogic.protectModeOne(scrnMsg, this);

            NFCL2760CommonLogic.transMsgCheck(scrnMsg);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }


        //Nothing the Cust Ref Transaction
        scrnMsg.payerCustCd_P3.setValue(scrnMsg.payerCustCd.getValue());
        scrnMsg.ccyCd_P3.setValue(scrnMsg.ccyCd.getValue());

        Object[] params = NFCL2760CommonLogic.setParams_NFCL2760Scrn00_OpenWin_Search(bizMsg, scrnMsg, getButtonSelectNumber());

        NFCL2760CommonLogic.protectModeOne(scrnMsg, this);
        setArgForSubScreen(params);
        scrnMsg.putErrorScreen();

    }
}
