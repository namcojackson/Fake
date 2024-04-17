/*
 * <pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1410;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Branch Rep Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410_NSAL0420 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NSAL1410BMsg scrnMsg = (NSAL1410BMsg) bMsg;

        setValue(scrnMsg.svcContrBrCd_H, scrnMsg.svcContrBrCd_P);
        setValue(scrnMsg.svcContrBrDescTxt_H, scrnMsg.svcContrBrDescTxt_P);
        setValue(scrnMsg.svcLineBizCd_H, scrnMsg.svcLineBizCd_P);
        if (hasValue(scrnMsg.svcLineBizCd_H) && hasValue(scrnMsg.svcContrBrDescTxt_H)) {
            setValue(scrnMsg.xxGenlFldAreaTxt_H, scrnMsg.svcLineBizCd_H.getValue() + "-" + scrnMsg.svcContrBrDescTxt_H.getValue());
        } else {
            setValue(scrnMsg.xxGenlFldAreaTxt_H, scrnMsg.svcContrBrDescTxt_H.getValue());

        }
    }
}
