/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1530;

import static business.servlet.NPAL1530.constant.NPAL1530Constant.BIZ_APP_ID;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPAL1530.constant.NPAL1530Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1530.NPAL1530CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/16/2018   CITS            T.Tokutomi      Create          QC#21879
 *</pre>
 */
public class NPAL1530_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mrpPlnNm, scrnMsg.P.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.P.no(1).xxComnScrColValTxt);

            NPAL1530CMsg bizMsg = new NPAL1530CMsg();
            bizMsg.setBusinessID(BIZ_APP_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (cMsg != null) {
            NPAL1530BMsg scrnMsg = (NPAL1530BMsg) bMsg;
            NPAL1530CMsg bizMsg = (NPAL1530CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
                return;
            }

            scrnMsg.putErrorScreen();
        }
    }
}
