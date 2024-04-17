/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6700.NMAL6700CMsg;
//import business.servlet.NMAL6700.constant.NMAL6700Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Update          N/A
 * 2018/07/13   Fujitsu         M.Ishii         Update          QC#26613
 *</pre>
 */
public class NMAL6700_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if ("OpenWin_Template".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCustArTmplNm_U1, scrnMsg.L.no(1).xxComnScrColValTxt);
            }

        } else if ("OpenWin_CltPtfo".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(0).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cltPtfoCd_U1, scrnMsg.L.no(0).xxComnScrColValTxt);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(1).xxComnScrColValTxt)) {
                // 2018/07/13 QC#26613 Mod Start
//                ZYPEZDItemValueSetter.setValue(scrnMsg.cltPtfoNm_U1, scrnMsg.L.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.cltPtfoDescTxt_U1, scrnMsg.L.no(1).xxComnScrColValTxt);
                // 2018/07/13 QC#26613 Mod End
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(4).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cltPtfoPk_U1, new BigDecimal(scrnMsg.L.no(4).xxComnScrColValTxt.getValue()));
            }
        } else if ("OpenWin_AcctGrp".equals(scrEventNm)) {
            int selectedRow = getButtonSelectNumber();
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(0).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(selectedRow).dsAcctGrpCd_E3, scrnMsg.L.no(0).xxComnScrColValTxt);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.L.no(1).xxComnScrColValTxt)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.E.no(selectedRow).dsAcctGrpDescTxt_E3, scrnMsg.L.no(1).xxComnScrColValTxt);
            }
            scrnMsg.setFocusItem(scrnMsg.E.no(selectedRow).dsAcctGrpCd_E3);
        }
    }
}
