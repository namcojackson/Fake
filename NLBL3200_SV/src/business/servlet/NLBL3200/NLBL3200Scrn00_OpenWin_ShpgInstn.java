/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

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
public class NLBL3200Scrn00_OpenWin_ShpgInstn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        int selectedIndex = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_EV, BigDecimal.valueOf(selectedIndex));

        NLBL3200CMsg bizMsg = new NLBL3200CMsg();
        bizMsg.setBusinessID(NLBL3200Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectedIndex = getButtonSelectNumber();

        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxComnScrColValTxt, NLBL3200Constant.XX_OPS_TP_INQUIRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxComnScrColValTxt, MSG_CTRL_TP.SHIPPING_INSTRUCTIONS_COMMENT);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxComnScrColValTxt, scrnMsg.A.no(selectedIndex).xxDsMultMsgDplyTxt_A1);

        setArgForSubScreen(NLBL3200CommonLogic.toArrayPopup(scrnMsg.P, 3));
    }
}
