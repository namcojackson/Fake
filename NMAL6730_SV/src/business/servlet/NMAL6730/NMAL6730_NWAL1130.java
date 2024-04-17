/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6730.NMAL6730CMsg;
//import business.servlet.NMAL6730.constant.NMAL6730Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/05   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL6730_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        //NMAL6730CMsg bizMsg = new NMAL6730CMsg();
        //bizMsg.setBusinessID("NMAL6730");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6730BMsg scrnMsg = (NMAL6730BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_Template".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCustArTmplNm_F1, scrnMsg.L.no(1).xxComnScrColValTxt);
            }

        } else if ("OpenWin_CltPtfo".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(0).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cltPtfoCd_F1, scrnMsg.L.no(0).xxComnScrColValTxt);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cltPtfoNm_F1, scrnMsg.L.no(1).xxComnScrColValTxt);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(4).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cltPtfoPk_F1, new BigDecimal(scrnMsg.L.no(4).xxComnScrColValTxt.getValue()));
            }
        } else if ("OpenWin_RemTo".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(0).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.remId_F1, scrnMsg.L.no(0).xxComnScrColValTxt);
            }

        }
    }
}
