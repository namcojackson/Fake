/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_0;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_1;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_10;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_2;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_3;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_4;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_5;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_6;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_7;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_8;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_9;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.PARM_NMAL6800_MODE_CODE_10;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open Return to Item Search Popup(NMAL6800)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/16/2016   CITS            Makoto Okigami  Create          N/A
 * 06/26/2020   CITS            K.Ogino         Update          QC#57052
 *</pre>
 */
public class NPBL0020Scrn00_OpenWin_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();
        // QC#57052
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventRowIndex));

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(IDX_9).xxPopPrm, PARM_NMAL6800_MODE_CODE_10);

        Object[] params = new Object[IDX_10];
        params[IDX_0] = scrnMsg.A.no(eventRowIndex).mdseCd_A1;
        params[IDX_1] = scrnMsg.P.no(IDX_1).xxPopPrm;
        params[IDX_2] = scrnMsg.P.no(IDX_2).xxPopPrm;
        params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        params[IDX_4] = scrnMsg.P.no(IDX_4).xxPopPrm;
        params[IDX_5] = scrnMsg.P.no(IDX_5).xxPopPrm;
        params[IDX_6] = scrnMsg.P.no(IDX_6).xxPopPrm;
        params[IDX_7] = scrnMsg.A.no(eventRowIndex).mdseDescShortTxt_A1;
        params[IDX_8] = scrnMsg.P.no(IDX_8).xxPopPrm;
        params[IDX_9] = scrnMsg.P.no(IDX_9).xxPopPrm;

        setArgForSubScreen(params);

    }
}
