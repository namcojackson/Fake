/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MSG_CTRL_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NMAL2020 Manage Shipping Orders
 * Function Name : Open Message Entry PopUp(NLBL3030)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CSAI            D.Fukaya        Create          QC#2200
 * 07/14/2016   CSAI            Y.Imazu         Update          QC#11900
 *</pre>
 */
public class NLBL2020Scrn00_OpenWin_ShpgInstn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        int selectedIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_EV, BigDecimal.valueOf(selectedIndex));

        NLBL2020CMsg bizMsg = new NLBL2020CMsg();
        bizMsg.setBusinessID(NLBL2020Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectedIndex = getButtonSelectNumber();

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxComnScrColValTxt, NLBL2020Constant.XX_OPS_TP_INQUIRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxComnScrColValTxt, MSG_CTRL_TP.SHIPPING_INSTRUCTIONS_COMMENT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxComnScrColValTxt, scrnMsg.A.no(selectedIndex).xxDsMultMsgDplyTxt_A1);

        setArgForSubScreen(NLBL2020CommonLogic.toArrayPopup(scrnMsg.P, 3));
    }
}
