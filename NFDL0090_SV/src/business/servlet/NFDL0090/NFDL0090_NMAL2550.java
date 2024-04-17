/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NFDL0090.constant.NFDL0090Constant.*;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/11/24   Hitachi         S.Naya          Create          QC#57252
 *</pre>
 */
public class NFDL0090_NMAL2550 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        if(CMN_CLOSE.equals(getLastGuard())){

            if (scrnMsg.xxCmntTxt_FS.isInputProtected()) {
                return;
            }

            StringBuffer sb = new StringBuffer();

            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I1) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I2) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I3) &&
                ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I4) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I5) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I6) &&
                ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I7) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I8) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_I9)) {

                sb.append(scrnMsg.xxPopPrm_I1.getValue());
                sb.append(STR_COMMA);
                sb.append(scrnMsg.xxPopPrm_I2.getValue());
                sb.append(STR_COMMA);
                sb.append(scrnMsg.xxPopPrm_I3.getValue());
                sb.append(STR_COMMA);
                sb.append(scrnMsg.xxPopPrm_I4.getValue());
                sb.append(STR_COMMA);
                sb.append(scrnMsg.xxPopPrm_I5.getValue());
                sb.append(STR_COMMA);
                sb.append(scrnMsg.xxPopPrm_I6.getValue());
                sb.append(STR_COMMA);
                sb.append(scrnMsg.xxPopPrm_I7.getValue());
                sb.append(STR_COMMA);
                sb.append(scrnMsg.xxPopPrm_I8.getValue());
                sb.append(STR_COMMA);
                sb.append(scrnMsg.xxPopPrm_I9.getValue());
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCmntTxt_FS, sb.toString());
            scrnMsg.setFocusItem(scrnMsg.xxCmntTxt_FS);
        }
    }
}
