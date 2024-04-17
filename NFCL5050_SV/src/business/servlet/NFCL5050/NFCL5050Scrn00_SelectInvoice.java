/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Fujitsu         FXS)Z.Wang      Create          N/A
 * 10/29/2009   Fujitsu         FAP)D.Kato      Update          DefID 0346
 * 05/14/2010   Fujitsu         FAP)N.Aoyama    Update          DefID:6321
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 11/01/2010   Fujitsu         I.Kondo         Update          DefID:M-19
 * 04/20/2016   Fujitsu         S.Fujita        Update          QC#7093
 * </pre>
 */
package business.servlet.NFCL5050;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.servlet.NFCL5050.common.NFCL5050CommonLogic;
import business.servlet.NFCL5050.constant.NFCL5050Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * NFCL5050Scrn00_SelectInvoice.
 */
public class NFCL5050Scrn00_SelectInvoice extends S21CommonHandler implements NFCL5050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5050CMsg bizMsg = null;
        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        bizMsg = NFCL5050CommonLogic.setRequestData_NFCL5050Scrn00_SelectInvoice(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        Object[] params = (Object[]) getArgForSubScreen();

        bizMsg.xxModeInd.setValue((String) params[PARAMS.NUM_8.getValue()]);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        S21TableColorController tblColor = new S21TableColorController("NFCL5050Scrn00", scrnMsg);
        ZYPTableUtil.clear(scrnMsg.A);
        // START 2016/04/20 S.Fujita [QC#7093,MOD]
        tblColor.clearRowsBG("A", scrnMsg.A);
        // tblColor.clearRowsBG("A1", scrnMsg.A);
        // tblColor.clearRowsBG("A2", scrnMsg.A);
        // END 2016/04/20 S.Fujita [QC#7093,MOD]

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            NFCL5050CommonLogic.setRowBg(scrnMsg);
            // START 2016/04/20 S.Fujita [QC#7093,MOD]
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
            // tblColor.setAlternateRowsBG("A1", scrnMsg.A);
            // tblColor.setAlternateRowsBG("A2", scrnMsg.A);
            // END 2016/04/20 S.Fujita [QC#7093,MOD] 

            NFCL5050CommonLogic.transMsgCheck(scrnMsg);
            scrnMsg.putErrorScreen();

            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
        }

        Object[] params = (Object[]) getArgForSubScreen();

        NFCL5050CommonLogic.initialize(this, scrnMsg);

        int count = 0;
        if (null != bizMsg && null != bizMsg.C) {
            count = bizMsg.C.getValidCount();
        }

        String[] arTrxNumList = new String[count];
        String[] arTrxTpCdList = new String[count];
        BigDecimal[] arTrxBalPkList = new BigDecimal[count];

        for (int i = 0; i < count; i++) {
            arTrxNumList[i] = bizMsg.C.no(i).arTrxNum_C1.getValue();
            arTrxTpCdList[i] = bizMsg.C.no(i).arTrxTpCd_C1.getValue();
            arTrxBalPkList[i] = bizMsg.C.no(i).arTrxBalPk_C1.getValue();
        }

        NFCL5050CommonLogic.otherBusConnectToReturn_NFCL5050Scrn00_SelectInvoice(params, arTrxNumList, arTrxTpCdList, arTrxBalPkList, scrnMsg);
    }
}
