/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_ASSET_ACCT_GL;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_EXP_ACCT_GL;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.STR_COMMA;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060_NMAL2550
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/30   Fujitsu         C.Tanaka        Create          QC#13360
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2017/11/01   Hitachi         J.Kim           Update          QC#16345
 *</pre>
 */
public class NLEL0060_NMAL2550 extends S21CommonHandler {

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

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            String scrnEventNm = scrnMsg.P.no(10).xxPopPrm.getValue();

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

            int idx = getButtonSelectNumber();
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_BA.getValue())) {
                if (OPENWIN_ASSET_ACCT_GL.equals(scrnEventNm)) {
                    // START 2016/11/01 J.Kim [QC#16345,ADD]
                    //ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).xxScrItem50Txt_B1, sb.toString());
                    //scrnMsg.setFocusItem(scrnMsg.B.no(idx).xxScrItem50Txt_B1);
                    // END 2016/11/01 J.Kim [QC#16345,ADD]
                } else if (OPENWIN_EXP_ACCT_GL.equals(scrnEventNm)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).xxScrItem50Txt_B2, sb.toString());
                    scrnMsg.setFocusItem(scrnMsg.B.no(idx).xxScrItem50Txt_B2);
                }
            }
        }
    }
}
