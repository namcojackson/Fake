/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500Scrn00_OpenWin_SpecialInstruction

 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_SpecialInstruction extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setArgForSubScreen(getArgForSubScreen(scrnMsg));
    }

    private Serializable getArgForSubScreen(NWAL1500BMsg scrnMsg) {

        List<Object> parmeters = new ArrayList<Object>();

        // [0] : Global Company Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, getGlobalCompanyCode());
        parmeters.add(scrnMsg.xxPopPrm_P0);

        // [1] : Function ID
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, BIZ_ID);
        parmeters.add(scrnMsg.xxPopPrm_P1);

        // [2] : Function Category ID
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, "");
        parmeters.add(scrnMsg.xxPopPrm_P2);

        // [3] : Transaction Type
        parmeters.add(scrnMsg.xxPopPrm_P3);

        // [4] : Business Area
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, "");
        parmeters.add(scrnMsg.xxPopPrm_P4);

        // [5] : Customer Special Instruction Line Suffix
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, "QL"); // S21_NA#1963
        parmeters.add("QL");

        // [6] : Customer Special Instruction Line
        int index = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, scrnMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, scrnMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");

        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, scrnMsg.shipToCustCd);
        scrnMsg.Q.setValidCount(index);
        parmeters.add(scrnMsg.Q);

        return parmeters.toArray(new Object[0]);
    }
}
