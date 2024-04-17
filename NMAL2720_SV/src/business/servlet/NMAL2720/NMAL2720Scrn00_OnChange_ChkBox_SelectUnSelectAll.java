/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720;

//import static business.servlet.NMAL2720.constant.NMAL2720Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL2720.NMAL2720CMsg;
//import business.servlet.NMAL2720.constant.NMAL2720Constant;

import business.servlet.NMAL2720.common.NMAL2720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2720Scrn00_OnChange_ChkBox_SelectUnSelectAll
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720Scrn00_OnChange_ChkBox_SelectUnSelectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;
        NMAL2720CommonLogic.addCheckItemScreen(scrnMsg, false);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2720BMsg scrnMsg = (NMAL2720BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_AH)) {
            ZYPTableUtil.selectAll(scrnMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        } else {
            ZYPTableUtil.unSelectAll(scrnMsg.A, "xxChkBox_A1");
        }
    }
}
