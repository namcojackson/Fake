/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480;

import static business.servlet.NSAL0480.constant.NSAL0480Constant.ITEM_NM_SVC_SKILL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0480.NSAL0480CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 * 2016/10/07   Hitachi         N.Arai          Update          QC#15001
 *</pre>
 */
public class NSAL0480_NMAL6050 extends S21CommonHandler {

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

// START 2016/10/07 N.Arai [QC#15001, MOD]
        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;
            NSAL0480CMsg bizMsg = (NSAL0480CMsg) cMsg;

            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }

            String scrEventNm = scrnMsg.xxScrEventNm.getValue();

            if ("OpenWin_MdlGrp".equals(scrEventNm)) {
                scrnMsg.setFocusItem(scrnMsg.mdlGrpNm_H);

            // 2015/10/05 CSA Y.Tsuchimoto Del Start
            //} else if ("OpenWin_Mdse".equals(scrEventNm)) {
            //    scrnMsg.setFocusItem(scrnMsg.t_ItemCd_H);

            //} else if ("OpenWin_SplyMdse".equals(scrEventNm)) {
            //    scrnMsg.setFocusItem(scrnMsg.mdseCd_H);
            // 2015/10/05 CSA Y.Tsuchimoto Del End
            } else if (ITEM_NM_SVC_SKILL.equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcSkillNum_H, scrnMsg.xxCondCd_P);
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcSkillDescTxt_H, scrnMsg.xxCondNm_P);
                scrnMsg.setFocusItem(scrnMsg.svcSkillNum_H);

            }
        }
// END 2016/10/07 N.Arai [QC#15001, MOD]
    }
}
