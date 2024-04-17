/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640;

import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_DTL;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_HEAD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
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
 * 2016/03/28   Hitachi         M.Gotou         Create          N/A
 * 2016/03/31   Hitachi         M.Gotou         Update          QC#4916
 *</pre>
 */
public class NSAL0640_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;

        // add start 2016/04/04 CSA Defect#4916
        if (!ZYPCommonFunc.hasValue(scrnMsg.svcContrBrCd_H)) {
            setValue(scrnMsg.svcContrBrCd_H, scrnMsg.svcContrBrCd_P);
        }
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (SELECT_BRANCH_DTL.equals(scrEventNm)) {
            int index = getButtonSelectNumber();
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).svcContrBrCd_A2)) {
                setValue(scrnMsg.A.no(index).svcContrBrCd_A2, scrnMsg.svcContrBrCd_P);
            }
        }
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        // add end 2016/04/04 CSA Defect#4916

        // mod start 2016/03/31 CSA Defect#4916
        String psnCd = scrnMsg.R.no(0).xxComnScrColValTxt.getValue();
        String psnNm = scrnMsg.R.no(1).xxComnScrColValTxt.getValue();
        if (SELECT_BRANCH_HEAD.equals(scrEventNm)) {
            if (ZYPCommonFunc.hasValue(psnCd)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd_H, psnCd);
            }
            if (ZYPCommonFunc.hasValue(psnNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H, psnNm);
            }
        } else if (SELECT_BRANCH_DTL.equals(scrEventNm)) {
            int index = getButtonSelectNumber();
            if (ZYPCommonFunc.hasValue(psnCd)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).psnCd_A2, psnCd);
            }
            if (ZYPCommonFunc.hasValue(psnNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).xxPsnNm_A2, psnNm);
            }
        }
        // mod end 2016/03/31 CSA Defect#4916
    }
}
