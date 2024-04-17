/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM1239E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NWAM0682E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Add New Line
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/03/2016   CITS            Makoto Okigami  Create          N/A
 * 11/28/2017   CITS            K.Ogino         Update          QC#22481
 * 02/26/2018   CITS            T.Tokutomi      Update          QC#22376
 *</pre>
 */
public class NPBL0020Scrn00_Add_NewLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int count = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                count++;
                // QC#22481
                if (!PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue()) && !CONFIG_TP.NEW.equals(scrnMsg.A.no(i).configTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NWAM0682E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
            }
        }

        if (count > 1) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1239E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
            }
        }

        // QC#22481
        if (PRCH_REQ_TP.SUBCONTRACT.equals(scrnMsg.prchReqTpCd_SL.getValue()) && count == 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1239E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!NPBL0020CommonLogic.postInputCheckForAddLineAndConfig(scrnMsg, bizMsg)) {
            scrnMsg.putErrorScreen();
            return;
        }

        NPBL0020CommonLogic.setCtrlScrnItemDispDetailTable(this, scrnMsg, true);

        // QC#22376 Set Focus
        int indexBehind = scrnMsg.xxNum_FC.getValueInt();
        scrnMsg.setFocusItem(scrnMsg.A.no(indexBehind).mdseCd_A1);
    }
}
