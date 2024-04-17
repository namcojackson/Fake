/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
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

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open Return to GL Common Popup(NMAL2550)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/18/2016   CITS            Makoto Okigami  Create          N/A
 * 06/15/2016   CSAI            D.Fukaya        Update          QC#9297
 * 06/27/2016   CSAI            D.Fukaya        Update          QC#9294
 * 02/27/2018   CITS            K.Ogino         Update          QC#22518
 *</pre>
 */
public class NPBL0020Scrn00_OpenWin_Account extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventRowIndex));

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

        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_DW);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhNm_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem50Txt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToLocNm_E1);
        }
        scrnMsg.putErrorScreen();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, ZYPCommonFunc.concatString(BIZ_APP_ID, "", scrnMsg.prchReqTpCd_SL.getValue()));

        Object[] params = new Object[IDX_10];
        params[IDX_0] = scrnMsg.P.no(IDX_0).xxPopPrm;
        params[IDX_1] = scrnMsg.P.no(IDX_1).xxPopPrm;
        params[IDX_2] = scrnMsg.P.no(IDX_2).xxPopPrm;
        params[IDX_3] = scrnMsg.P.no(IDX_3).xxPopPrm;
        params[IDX_4] = scrnMsg.P.no(IDX_4).xxPopPrm;
        params[IDX_5] = scrnMsg.P.no(IDX_5).xxPopPrm;
        params[IDX_6] = scrnMsg.P.no(IDX_6).xxPopPrm;
        params[IDX_7] = scrnMsg.P.no(IDX_7).xxPopPrm;
        params[IDX_8] = scrnMsg.P.no(IDX_8).xxPopPrm;
        params[IDX_9] = scrnMsg.P.no(IDX_9).xxPopPrm;

        setArgForSubScreen(params);

    }
}
