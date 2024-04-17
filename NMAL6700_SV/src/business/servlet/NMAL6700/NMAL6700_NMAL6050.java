/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/11/11   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/02/17   Fujitsu         T.Murai         Update          CSA#2943
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 *</pre>
 */
public class NMAL6700_NMAL6050 extends S21CommonHandler {

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
        if ("OpenWin_CltCustTp".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.cltCustTpCd_U1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(10).xxPopPrm)) {
                scrnMsg.cltCustTpNm_U1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.cltCustTpCd_U1);
        } else if ("OpenWin_AccsPmit".equals(scrEventNm)) {
            int selectedRow = getButtonSelectNumber();
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                ((NMAL6700_NBMsg) scrnMsg.N.get(selectedRow)).svcAccsPmitNum_N1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(10).xxPopPrm)) {
                ((NMAL6700_NBMsg) scrnMsg.N.get(selectedRow)).svcAccsPmitDescTxt_N1.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(((NMAL6700_NBMsg) scrnMsg.N.get(selectedRow)).svcAccsPmitNum_N1);
        } else if ("OpenWin_AcctGrp".equals(scrEventNm)) {
            int selectedRow = getButtonSelectNumber();
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.E.no(selectedRow).dsAcctGrpCd_E3.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(10).xxPopPrm)) {
                scrnMsg.E.no(selectedRow).dsAcctGrpDescTxt_E3.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.E.no(selectedRow).dsAcctGrpCd_E3);
        // 2023/01/13 QC#60860 Mod Start
        // }
        } else if ("OpenWin_CarrInfo".equals(scrEventNm)) {
            int selectedRow = getButtonSelectNumber();
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(10).xxPopPrm)) {
                scrnMsg.M.no(selectedRow).carrNm_M3.setValue(scrnMsg.P.no(10).xxPopPrm.getValue());
            }
        // 2023/01/13 QC#60860 Mod End
        }

    }
}
