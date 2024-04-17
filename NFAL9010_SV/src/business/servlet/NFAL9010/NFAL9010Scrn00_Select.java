/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9010;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL9010.NFAL9010CMsg;
import business.servlet.NFAL9010.constant.NFAL9010Constant;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL9010Scrn00_Select
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */

/**
 * <pre>
 * COA Account Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   CSAI            K.Uramori       Update          CSA Modification
 * </pre>
 */
public class NFAL9010Scrn00_Select extends S21CommonHandler implements NFAL9010Constant {

    /**
     * Method name: checkInput
     * <dd>The method explanation: Check Input Data.
     * <dd>Remarks:
     * @param ctx Screen Application Context Class
     * @param bMsg Object which input value from screen is stored
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9010BMsg scrnMsg = (NFAL9010BMsg)bMsg;

    }

    /**
     * Method name: setRequestData
     * <dd>The method explanation: Call business logic.
     * <dd>Remarks:
     * @param ctx Screen Application Context Class
     * @param bMsg Object which input value from screen is stored
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;
        NFAL9010CMsg bizMsg = new NFAL9010CMsg();

        // Set PL Category Code in selected row
        // in order to get PL Category Name
        //scrnMsg.plCatgCd.setValue(scrnMsg.A.no(getButtonSelectNumber()).plCatgCd_A.getValue());

        bizMsg.setBusinessID("NFAL9010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
        
        //return null;
    }

    /**
     * Method name: doProcess
     * <dd>The method explanation: Display and edit for next page
     * contents.
     * <dd>Remarks:
     * @param ctx Screen Application Context
     * @param bMsg Object which input value from screen is stored
     * @param cMsg Object which input value from business is stored
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;
        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {

            // Row Number Index
            int selectedIndex = getButtonSelectNumber();

            for (int i = 0; i < param.length; i++) {
                if (i == 0) {
                    ((EZDBStringItem) param[0]).setValue(scrnMsg.A.no(selectedIndex).coaAcctCd_A.getValue());
                } else if (i == 1) {
                    ((EZDBStringItem) param[1]).setValue(scrnMsg.A.no(selectedIndex).coaAcctNm_A.getValue());
                } else if (i == 2) {
                    ((EZDBStringItem) param[2]).setValue(scrnMsg.A.no(selectedIndex).trialBalTpCd_A.getValue());
                } else if (i == 3) {
                    ((EZDBStringItem) param[3]).setValue(scrnMsg.A.no(selectedIndex).trialBalTpNm_A.getValue());
                } else if (i == 4) {
                    ((EZDBStringItem) param[4]).setValue(null);
                } else if (i == 5) {
                    ((EZDBStringItem) param[5]).setValue(null);
                } else if (i == 6) {
                    ((EZDBStringItem) param[6]).setValue(null);
                } else if (i == 7) {
                    ((EZDBStringItem) param[7]).setValue(null);
                } else if (i == 8) {
                    ((EZDBStringItem) param[8]).setValue(null);
                } else if (i == 9) {
                    ((EZDBStringItem) param[9]).setValue(null);
                } else if (i == 10) {
                    ((EZDBStringItem) param[10]).setValue(null);
                } else if (i == 11) {
                    ((EZDBStringItem) param[11]).setValue(null);
                }
            } // for
        } // if
    }
}
