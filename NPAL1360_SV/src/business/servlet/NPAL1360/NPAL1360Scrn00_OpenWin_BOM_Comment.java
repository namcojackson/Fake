/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1360;

import static business.servlet.NPAL1360.constant.NPAL1360Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/14   CITS            K.Fukumura      Create          N/A
 *</pre>
 */
public class NPAL1360Scrn00_OpenWin_BOM_Comment extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.fnshGoodsMdseCd)) {
            scrnMsg.fnshGoodsMdseCd.setErrorInfo(1, ZZM9000E, new String[] {"BOM Item#" });
        }
        scrnMsg.addCheckItem(scrnMsg.fnshGoodsMdseCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1360BMsg scrnMsg = (NPAL1360BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.R);
        Object[] param = new Object[3];
        param[0] = scrnMsg.fnshGoodsMdseCd;
        param[1] = scrnMsg.R;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxReadOnlyFlg_P, ZYPConstant.FLG_ON_Y);
        param[2] = scrnMsg.xxReadOnlyFlg_P;
        setArgForSubScreen(param);

    }
}
