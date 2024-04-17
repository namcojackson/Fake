/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DISPLAY_NM_PRCH_REQ_DISP_QTY;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_200;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.NPAM0038I;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.ZZZM9025E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Open Return to Serial Number Search Popup(NLBL3000)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/18/2016   CITS            Makoto Okigami  Create          N/A
 * 04/06/2016   CITS            K.Ogino         Update          N/A
 * 06/24/2016   CSAI            D.Fukaya        Update          QC#9292
 *</pre>
 */
public class NPBL0020Scrn00_OpenWin_SerEnt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRowIndex).mdseCd_A1)) {
            scrnMsg.A.no(eventRowIndex).mdseCd_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_MDSE_CD });
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRowIndex).prchReqDispQty_A1)) {
            scrnMsg.A.no(eventRowIndex).prchReqDispQty_A1.setErrorInfo(1, ZZZM9025E, new String[] {DISPLAY_NM_PRCH_REQ_DISP_QTY });
        }
        scrnMsg.addCheckItem(scrnMsg.A.no(eventRowIndex).mdseCd_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(eventRowIndex).prchReqDispQty_A1);
        scrnMsg.putErrorScreen();

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(eventRowIndex).xxLogDtlTxt_A1)) {

            String[] serialNumber = scrnMsg.A.no(eventRowIndex).xxLogDtlTxt_A1.getValue().split(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);
            // Max serial number array
            if (serialNumber.length > IDX_200) {
                scrnMsg.A.no(eventRowIndex).xxLogDtlTxt_A1.setErrorInfo(1, NPAM0038I);
                scrnMsg.addCheckItem(scrnMsg.A.no(eventRowIndex).xxLogDtlTxt_A1);
                scrnMsg.putErrorScreen();
            }

        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventRowIndex));
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

        scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_SW);
        scrnMsg.addCheckItem(scrnMsg.srcRtlSwhCd);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqDispQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).srcRtlSwhCd_A1);
        }
        scrnMsg.putErrorScreen();

        int eventRowIndex = getButtonSelectNumber();

        // Initialization of subscreen delivery information
        NPBL0020CommonLogic.setInitParamForSerialNumEntryPopup(scrnMsg, eventRowIndex);

        // set popup parameter
        Object[] params = NPBL0020CommonLogic.setParamForSerialNumEntryPopup(scrnMsg, eventRowIndex);

        // send popup parameter
        setArgForSubScreen(params);

    }
}
