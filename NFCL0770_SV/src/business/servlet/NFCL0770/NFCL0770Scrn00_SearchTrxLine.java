/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0770.NFCL0770CMsg;
import business.servlet.NFCL0770.common.NFCL0770CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/02   Fujitsu         S.Takami        Create          QC#28289
 * 2022/09/02   Hitachi         A.Kohinata      Update          QC#60403
 *</pre>
 */
public class NFCL0770Scrn00_SearchTrxLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;

        // add start 2022/09/02 QC#60403
        scrnMsg.xxListNum_LY.setValue(0);
        // add end 2022/09/02 QC#60403

        NFCL0770CommonLogic.chkSubmit(scrnMsg, "NFCL0770Scrn00_SearchTrxLine");
        scrnMsg.addCheckItem(scrnMsg.payerCustCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
        NFCL0770CMsg bizMsg = null;
        int lineNum = getButtonSelectNumber();

        scrnMsg.xxCellIdx_H1.setValue(lineNum);

        bizMsg = NFCL0770CommonLogic.setRequestData_NFCL0770Scrn00_SearchTrxLine();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0770BMsg scrnMsg = (NFCL0770BMsg) bMsg;
        NFCL0770CMsg bizMsg = (NFCL0770CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.payerCustCd);
        scrnMsg.putErrorScreen();

        if (!scrnMsg.A.no(getButtonSelectNumber()).arCustRefNum.isError()) {
            NFCL0770CommonLogic.initialize(this, scrnMsg);

            NFCL0770CommonLogic.commonBtnControl_NFCL0770Scrn00_SearchTrxLine(this);

            NFCL0770CommonLogic.setRowBg(scrnMsg);

            NFCL0770CommonLogic.protectModeOne(scrnMsg, this);

            NFCL0770CommonLogic.transMsgCheck(scrnMsg);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }


        //Nothing the Cust Ref Transaction
        scrnMsg.payerCustCd_P3.setValue(scrnMsg.payerCustCd.getValue());
        scrnMsg.ccyCd_P3.setValue(scrnMsg.ccyCd.getValue());

        Object[] params = NFCL0770CommonLogic.setParams_NFCL0770Scrn00_OpenWin_Search(bizMsg, scrnMsg, getButtonSelectNumber());

        NFCL0770CommonLogic.protectModeOne(scrnMsg, this);
        setArgForSubScreen(params);
        scrnMsg.putErrorScreen();

    }
}
