/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0110;

import static business.servlet.NSAL0110.constant.NSAL0110Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0110.NSAL0110CMsg;
import business.servlet.NSAL0110.common.NSAL0110CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Contract Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/12   Hitachi         Y.Igarashi         Create          N/A
 * 2015/11/02   Hitachi         K.Kasai            Update          N/A
 *</pre>
 */
public class NSAL0110Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.dsContrNum_SC);
        scrnMsg.addCheckItem(scrnMsg.svcContrBrCd_SC);
        scrnMsg.addCheckItem(scrnMsg.dsContrClsCd_SV);
        scrnMsg.addCheckItem(scrnMsg.dsContrNm_SC);
        scrnMsg.addCheckItem(scrnMsg.dsContrCatgCd_SV);
        scrnMsg.addCheckItem(scrnMsg.sellToCustCd_SC);
        scrnMsg.addCheckItem(scrnMsg.dsContrCtrlStsCd_HV);
        scrnMsg.addCheckItem(scrnMsg.dsContrDtlTpCd_SV);
        scrnMsg.addCheckItem(scrnMsg.dsContrCtrlStsCd_DV);
        scrnMsg.addCheckItem(scrnMsg.serNum_SC);
        scrnMsg.addCheckItem(scrnMsg.mdlNm_SC);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem10Txt_SV);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem10Txt_SX, scrnMsg.xxScrItem10Txt_SV);

        NSAL0110CMsg bizMsg = new NSAL0110CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0110BMsg scrnMsg = (NSAL0110BMsg) bMsg;
        NSAL0110CMsg bizMsg  = (NSAL0110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0110CommonLogic.setBgColor(scrnMsg);
        NSAL0110CommonLogic.setFocusGrp(scrnMsg);
        NSAL0110CommonLogic.setProtected(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.dsContrNum_SC);

    }
}

