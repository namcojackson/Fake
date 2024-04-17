/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWCL0100_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        //NWCL0100CMsg bizMsg = new NWCL0100CMsg();
        //bizMsg.setBusinessID("NWCL0100");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            if ("OpenWin_CustPrcGrp".equals(scrnMsg.xxScrEventNm.getValue())) {
                NWCL0100_ABMsg abMsg = scrnMsg.A.no(getButtonSelectNumber());
                ZYPEZDItemValueSetter.setValue(abMsg.prcGrpPk_A0, new BigDecimal(scrnMsg.Q.no(0).xxComnScrColValTxt.getValue()));
                ZYPEZDItemValueSetter.setValue(abMsg.prcGrpNm_A0, scrnMsg.Q.no(1).xxComnScrColValTxt); 
                scrnMsg.setFocusItem(abMsg.prcGrpPk_A0);
            }
        }
    }
}
