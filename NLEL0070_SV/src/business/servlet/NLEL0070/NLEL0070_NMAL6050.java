/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLEL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLEL0070_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0070BMsg scrnMsg = (NLEL0070BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_AccumDepcCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.accumDepcCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.accumDepcCoaAcctCd_M1);

        } else if ("OpenWin_AdjCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.adjCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.adjCoaAcctCd_M1);

        } else if ("OpenWin_AssetCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.assetCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.assetCoaAcctCd_M1);

        } else if ("OpenWin_ClingCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.clingCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.clingCoaAcctCd_M1);

        } else if ("OpenWin_DepcAdjCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.depcAdjCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.depcAdjCoaAcctCd_M1);

        } else if ("OpenWin_DepcCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.depcCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.depcCoaAcctCd_M1);

        } else if ("OpenWin_GainLossCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.gainLossCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.gainLossCoaAcctCd_M1);

        } else if ("OpenWin_RmvCostCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.rmvCostCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.rmvCostCoaAcctCd_M1);

        } else if ("OpenWin_SlsPrcdCoaAcct".equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.P.no(9).xxPopPrm)) {
                scrnMsg.slsPrcdCoaAcctCd_M1.setValue(scrnMsg.P.no(9).xxPopPrm.getValue());
            }
            scrnMsg.setFocusItem(scrnMsg.slsPrcdCoaAcctCd_M1);

        }

    }
}
