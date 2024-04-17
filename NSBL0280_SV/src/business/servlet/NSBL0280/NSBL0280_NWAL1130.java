/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0280;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0280_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        int index = getButtonSelectNumber();
        if ("OpenWin_R".equals(scrEventNm) || "OpenWin_Resource".equals(scrEventNm)) {

            String psnCd = scrnMsg.C.no(0).xxComnScrColValTxt.getValue();
            String fullPsnNm = scrnMsg.C.no(1).xxComnScrColValTxt.getValue();

            if (index < 0) {
                setValue(scrnMsg.psnCd, psnCd);
                setValue(scrnMsg.fullPsnNm, fullPsnNm);
                scrnMsg.setFocusItem(scrnMsg.psnCd);
            } else {
                setValue(scrnMsg.B.no(index).techCd_B, psnCd);
                setValue(scrnMsg.B.no(index).fullPsnNm_B, fullPsnNm);
                scrnMsg.setFocusItem(scrnMsg.B.no(index).techCd_B);
            }
        } else if ("OpenWin_S".equals(scrEventNm) || "OpenWin_Skill".equals(scrEventNm)) {

            String svcSkillNum = scrnMsg.C.no(0).xxComnScrColValTxt.getValue();
            String svcSkillDescTxt = scrnMsg.C.no(1).xxComnScrColValTxt.getValue();
            String svcSkillTpDescTxt = scrnMsg.C.no(2).xxComnScrColValTxt.getValue();

            if (index < 0) {
                setValue(scrnMsg.svcSkillDescTxt, svcSkillDescTxt);
                scrnMsg.setFocusItem(scrnMsg.svcSkillDescTxt);
            } else {
                setValue(scrnMsg.A.no(index).svcSkillNum_A, svcSkillNum);
                setValue(scrnMsg.A.no(index).svcSkillDescTxt_A, svcSkillDescTxt);
                setValue(scrnMsg.A.no(index).svcSkillTpDescTxt_A, svcSkillTpDescTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(index).svcSkillDescTxt_A);
            }
        }
    }
}
