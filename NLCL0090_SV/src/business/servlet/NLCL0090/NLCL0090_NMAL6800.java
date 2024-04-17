/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
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
 * 03/03/2016   CSAI            Y.Imazu         Create          CSA
 *</pre>
 */
public class NLCL0090_NMAL6800 extends S21CommonHandler implements NLCL0090Constant {

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

            if (EVENT_NM_OPENWIN_MDSE_FROM.equals(scrnMsg.xxScrEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_HF, scrnMsg.P.no(0).xxPopPrm.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_HF, scrnMsg.P.no(7).xxPopPrm.getValue());

            } else if (EVENT_NM_OPENWIN_MDSE_TO.equals(scrnMsg.xxScrEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_HT, scrnMsg.P.no(0).xxPopPrm.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt_HT, scrnMsg.P.no(7).xxPopPrm.getValue());
            }
        }

        // Set Focus
        if (EVENT_NM_OPENWIN_MDSE_FROM.equals(scrnMsg.xxScrEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.mdseCd_HF);

        } else if (EVENT_NM_OPENWIN_MDSE_TO.equals(scrnMsg.xxScrEventNm.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.mdseCd_HT);
        }
    }
}
