/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_SWH_POPUP;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.INVENTORY_ACCOUNT_CODE_INVENTORY;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.OWNER_CODE_CSA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1410.NPAL1410CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 * 12/15/2017   CITS            K.Ogino         Update          QC#22836
 *</pre>
 */
public class NPAL1410Scrn00_OpenWin_SWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;

        NPAL1410CMsg bizMsg = new NPAL1410CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxPopPrm_H1.clear();
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
        NPAL1410CMsg bizMsg = (NPAL1410CMsg) cMsg;

        scrnMsg.eventNm.setValue(EVENT_NAME_SWH_POPUP);
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, bizMsg.xxPopPrm_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.rmnfRtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(idx).srcRtlSwhCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PB, INVENTORY_ACCOUNT_CODE_INVENTORY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, OWNER_CODE_CSA);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, OWNER_CODE_CSA);

        Object[] params = new Object[14];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;
        params[10] = scrnMsg.xxPopPrm_PA;
        params[11] = scrnMsg.xxPopPrm_PB;
        params[12] = scrnMsg.xxPopPrm_PC;
        params[13] = scrnMsg.xxPopPrm_PD;
        setArgForSubScreen(params);

        scrnMsg.setFocusItem(scrnMsg.A.no(idx).srcRtlSwhCd_A1);
    }
}
