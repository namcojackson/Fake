/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLCL0090.common.NLCL0090CommonLogic;
import business.servlet.NLCL0090.constant.NLCL0090Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/23   CUSA            Fujitsu         Create          R-OM025 Inventory Transaction
 * 03/03/2016   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLCL0090_NPAL1010 extends S21CommonHandler implements NLCL0090Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0090BMsg scrnMsg = (NLCL0090BMsg) bMsg;
        NLCL0090CommonLogic.initialControlScreen(this, scrnMsg);

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(6).xxPopPrm.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.P.no(8).xxPopPrm.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_P1, NLCL0090CommonLogic.toUpperCase(scrnMsg.P.no(7).xxPopPrm.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_P2, NLCL0090CommonLogic.toUpperCase(scrnMsg.P.no(9).xxPopPrm.getValue()));
        }

        // Set Focus
        if (EVENT_NM_OPENWIN_LOCINFO_RTLWH.equals(scrnMsg.xxScrEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

        } else if (EVENT_NM_OPENWIN_LOCINFO_RTLSWH.equals(scrnMsg.xxScrEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.rtlSwhCd);
        }
    }
}
