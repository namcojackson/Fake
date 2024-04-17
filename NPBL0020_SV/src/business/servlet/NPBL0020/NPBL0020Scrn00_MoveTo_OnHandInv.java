/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_0;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_1;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_2;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_3;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_4;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_5;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM0049E;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM1239E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open Onhand Inventory Popup(NLCL0250)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CITS            Makoto Okigami  Create          N/A
 * 04/06/2016   CITS            K.Ogino         Update          N/A
 * 04/21/2016   CITS            K.Ogino         Update          QC#6878
 * 05/31/2016   CITS            K.Ogino         Update          QC#8228
 *</pre>
 */
public class NPBL0020Scrn00_MoveTo_OnHandInv extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NPAM0049E);
            throw new EZDFieldErrorException();
        }
        int count = 0;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                count++;
            }
        }

        if (count == 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM0049E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            }
            scrnMsg.putErrorScreen();
        } else if (count > 1) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM1239E);
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
            }
            scrnMsg.putErrorScreen();
        }
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

        Object[] params = null;
        if (ZYPCommonFunc.hasValue(scrnMsg.P.no(IDX_4).xxPopPrm)) {
            params = new Object[IDX_5];
            params[IDX_0] = scrnMsg.P.no(IDX_0).xxPopPrm;
            params[IDX_1] = scrnMsg.P.no(IDX_1).xxPopPrm;
            params[IDX_2] = scrnMsg.P.no(IDX_2).xxPopPrm;
            params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
            params[IDX_4] = scrnMsg.P.no(IDX_4).svcConfigMstrPk_P1;
        } else {
            params = new Object[IDX_4];
            params[IDX_0] = scrnMsg.P.no(IDX_0).xxPopPrm;
            params[IDX_1] = scrnMsg.P.no(IDX_1).xxPopPrm;
            params[IDX_2] = scrnMsg.P.no(IDX_2).xxPopPrm;
            params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        }

        setArgForSubScreen(params);

    }
}
