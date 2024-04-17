/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0850;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Sales Credit for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL0850_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0850BMsg scrnMsg = (NSAL0850BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            int index = getButtonSelectNumber();
            for (int i = 0; i < scrnMsg.X.length(); i++) {
                if ("TOC_CD".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                    if (ZYPCommonFunc.hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                        setValue(scrnMsg.A.no(index).tocCd_A, scrnMsg.X.no(i).xxComnScrColValTxt.getValue());
                    } else {
                        scrnMsg.A.no(index).tocCd_A.clear();
                    }
                } else if ("TOC_NM".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                    if (ZYPCommonFunc.hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                        setValue(scrnMsg.A.no(index).tocNm_A, scrnMsg.X.no(i).xxComnScrColValTxt.getValue());
                    } else {
                        scrnMsg.A.no(index).tocNm_A.clear();
                    }
                }
            }
        }
    }
}
