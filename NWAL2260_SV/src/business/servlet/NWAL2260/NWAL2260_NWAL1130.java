/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2260;

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
 * 2018/11/28   Fujitsu         K.Ishizuka      Create          S21_NA#28899
 * 2019/01/15   Fujitsu         K.Ishizuka      Update          S21_NA#29875
 *</pre>
 */
public class NWAL2260_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2260BMsg scrnMsg = (NWAL2260BMsg) bMsg;

        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.idocPtnrCtyNm, scrnMsg.L.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.idocPtnrStCd, scrnMsg.L.no(1).xxComnScrColValTxt);
            // 2019/01/15 S21_NA#29875 Mod Start
            // ZYPEZDItemValueSetter.setValue(scrnMsg.idocPtnrPostCd, scrnMsg.L.no(2).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.idocPtnrPostCd, convertPostCd(scrnMsg.L.no(2).xxComnScrColValTxt.getValue()));
            // 2019/01/15 S21_NA#29875 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.idocPtnrCntyNm, scrnMsg.L.no(3).xxComnScrColValTxt);
        }
    }
    
    // 2019/01/15 S21_NA#29875 Add Start
    private String convertPostCd(String postCd){
        if (!ZYPCommonFunc.hasValue(postCd)) {
            return null;
        }
        return postCd.replace("-", "");
    }
    // 2019/01/15 S21_NA#29875 Add End
}
