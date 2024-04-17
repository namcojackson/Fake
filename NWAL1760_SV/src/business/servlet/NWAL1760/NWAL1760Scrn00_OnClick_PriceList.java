/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1760;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NWAL1760.constant.NWAL1760Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/25   Fujitsu         A.Suda          Create          N/A
 * 2015/11/26   Fujitsu         T.Ishii         Update          S21_NA#939
 *</pre>
 */
public class NWAL1760Scrn00_OnClick_PriceList extends S21CommonHandler {

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

        NWAL1760BMsg scrnMsg = (NWAL1760BMsg) bMsg;

        int index = getButtonSelectNumber();
        String prcCatgCd = scrnMsg.A.no(index).prcCatgCd_A.getValue();
        String prcCatgNm = scrnMsg.A.no(index).prcCatgNm_A.getValue();
        String prcListTpCd = scrnMsg.A.no(index).prcListTpCd_A.getValue();
        String prcListTpNm = scrnMsg.A.no(index).prcListTpNm_A.getValue();

        Object[] arg = (Object[]) getArgForSubScreen();

        if (arg instanceof Object[]) {

            int ixP = PRM_OUT_IX09;
            Object[] params = (Object[]) arg;
            EZDBStringItem param09 = (EZDBStringItem) params[ixP++];
            EZDBStringItem param10 = (EZDBStringItem) params[ixP++];
            EZDBStringItem param11 = (EZDBStringItem) params[ixP++];
            EZDBStringItem param12 = (EZDBStringItem) params[ixP];

            param09.setValue(prcCatgCd);
            param10.setValue(prcCatgNm);
            param11.setValue(prcListTpCd);
            param12.setValue(prcListTpNm);

        }
    }
}
