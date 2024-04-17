/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2620;

import static business.servlet.NMAL2620.constant.NMAL2620Constant.*;
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
 * 2013/03/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NMAL2620Scrn00_LINK_TERR_NM extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;

        setArgForSubScreen(setParam(ctx, scrnMsg));

    }

    private Object[] setParam(EZDApplicationContext ctx, NMAL2620BMsg scrnMsg) {

        Object[] params = new Object[NUM_8];
        scrnMsg.xxPopPrm_0.clear();
        scrnMsg.xxPopPrm_1.clear();
        scrnMsg.xxPopPrm_2.clear();
        scrnMsg.xxPopPrm_3.clear();

        if (ZYPCommonFunc.hasValue(scrnMsg.bizAreaOrgCd_V)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0, scrnMsg.bizAreaOrgCd_V);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.orgNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, scrnMsg.orgNm);
        }

        // Business Area
        params[0] = scrnMsg.xxPopPrm_0;
        // Territory Name
        params[1] = scrnMsg.xxPopPrm_1;

        return params;
    }
}
