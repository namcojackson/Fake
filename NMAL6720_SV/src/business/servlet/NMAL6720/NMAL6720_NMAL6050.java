/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_AP;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_PRIM_TECH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_REQ_TECH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_TECH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         C.Tanaka        Create          CSA
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 *</pre>
 */
public class NMAL6720_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        if (EVENT_TECH.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            int index = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.G.no(index).nonPrfTechCd_G1, scrnMsg.P.no(9).xxPopPrm_P.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.G.no(index).techNm_G1, scrnMsg.P.no(10).xxPopPrm_P.getValue());
            scrnMsg.setFocusItem(scrnMsg.G.no(index).nonPrfTechCd_G1);
        } else if (EVENT_PRIM_TECH.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prfTechCd_SA, scrnMsg.P.no(9).xxPopPrm_P.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxAllPsnNm_S1, scrnMsg.P.no(10).xxPopPrm_P.getValue());
            scrnMsg.setFocusItem(scrnMsg.prfTechCd_SA);
        } else if (EVENT_REQ_TECH.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.reqTechCd_SA, scrnMsg.P.no(9).xxPopPrm_P.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxAllPsnNm_S2, scrnMsg.P.no(10).xxPopPrm_P.getValue());
            scrnMsg.setFocusItem(scrnMsg.reqTechCd_SA);
        } else if (EVENT_AP.equals(scrnMsg.xxScrEventNm_P.getValue())) {
            int index = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.F.no(index).svcAccsPmitNum_F1, scrnMsg.P.no(9).xxPopPrm_P.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.F.no(index).svcAccsPmitDescTxt_F1, scrnMsg.P.no(10).xxPopPrm_P.getValue());
            scrnMsg.setFocusItem(scrnMsg.F.no(index).svcAccsPmitNum_F1);
         // 2023/01/13 QC#60860 Mod Start
            // }
        } else if ("OpenWin_CarrInfo".equals(scrnMsg.xxScrEventNm_P.getValue())) {
            int selectedRow = getButtonSelectNumber();
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(10).xxPopPrm_P)) {
                scrnMsg.I.no(selectedRow).carrNm_I3.setValue(scrnMsg.P.no(10).xxPopPrm_P.getValue());
            }
        }
        // 2023/01/13 QC#60860 Mod End
    }
}
