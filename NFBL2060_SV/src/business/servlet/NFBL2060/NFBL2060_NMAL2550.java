/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import static business.servlet.NFBL2060.constant.NFBL2060Constant.OPENWIN_GL_CHRG_ACCT_FROM;
import static business.servlet.NFBL2060.constant.NFBL2060Constant.OPENWIN_GL_CHRG_ACCT_TO;
import static business.servlet.NFBL2060.constant.NFBL2060Constant.STR_COMMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/08   Fujitsu         C.Tanaka        Create          QC#12040
 *</pre>
 */
public class NFBL2060_NMAL2550 extends S21CommonHandler {

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

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            String scrnEventNm = scrnMsg.xxScrEventNm.getValue();

            StringBuffer sb = new StringBuffer();
            sb.append(scrnMsg.P.no(1).xxPopPrm.getValue());
            sb.append(STR_COMMA);
            sb.append(scrnMsg.P.no(3).xxPopPrm.getValue());
            sb.append(STR_COMMA);
            sb.append(scrnMsg.P.no(4).xxPopPrm.getValue());
            sb.append(STR_COMMA);
            sb.append(scrnMsg.P.no(5).xxPopPrm.getValue());
            sb.append(STR_COMMA);
            sb.append(scrnMsg.P.no(6).xxPopPrm.getValue());
            sb.append(STR_COMMA);
            sb.append(scrnMsg.P.no(7).xxPopPrm.getValue());
            sb.append(STR_COMMA);
            sb.append(scrnMsg.P.no(2).xxPopPrm.getValue());
            sb.append(STR_COMMA);
            sb.append(scrnMsg.P.no(8).xxPopPrm.getValue());
            sb.append(STR_COMMA);
            sb.append(scrnMsg.P.no(9).xxPopPrm.getValue());

            if (OPENWIN_GL_CHRG_ACCT_FROM.equals(scrnEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCmntTxt_FR, sb.toString());
                scrnMsg.setFocusItem(scrnMsg.xxCmntTxt_FR);
            } else if (OPENWIN_GL_CHRG_ACCT_TO.equals(scrnEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxCmntTxt_TO, sb.toString());
                scrnMsg.setFocusItem(scrnMsg.xxCmntTxt_TO);
            }
        }

    }
}
