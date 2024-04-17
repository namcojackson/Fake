/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2630;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 *</pre>
 */
public class NMAL2630Scrn00_Select_TerritoryName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        NMAL2630BMsg scrnMsg = (NMAL2630BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {
            int index = getButtonSelectNumber();

            // Territory Code
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(param0, scrnMsg.A.no(index).orgCd_A1);

            // Territory Name
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            ZYPEZDItemValueSetter.setValue(param1, scrnMsg.A.no(index).orgNm_A1);

        }
    }
}
