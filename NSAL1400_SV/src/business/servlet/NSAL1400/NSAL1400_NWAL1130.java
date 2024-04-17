/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1400;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400_NWAL1130 extends S21CommonHandler {

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
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;
        if ("CMN_Close".equals(getLastGuard())) {
            if (!hasValue(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).contrAdminPsnCd_A)) {
                scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).xxCustPsnNm_A.clear();
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).xxCustPsnNm_A);
            return;
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).contrAdminPsnCd_A, scrnMsg.Z.no(1).xxComnScrColValTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).xxCustPsnNm_A, scrnMsg.Z.no(2).xxComnScrColValTxt);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxRowNum_P.getValueInt()).xxChkBox_A2);
    }
}
