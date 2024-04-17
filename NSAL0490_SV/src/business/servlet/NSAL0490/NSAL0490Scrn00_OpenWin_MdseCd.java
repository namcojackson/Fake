/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0490.common.NSAL0490CommonLogic;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/13   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/03/01   Hitachi         K.Kasai         Update          QC#3586
 * 2017/08/01   CITS            S.Endo          Update          Sol#072(QC#18386)
 *</pre>
 */
public class NSAL0490Scrn00_OpenWin_MdseCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(NSAL0490Constant.ITEM_NM_MDSE_CD);

        // 2015/10/07 CSA Y.Tsuchimoto Mod Start
        // set param
        //NSAL0490CommonLogic.setParamMdse(scrnMsg);
        //setArgForSubScreen(NSAL0490CommonLogic.getParamNMAL6050(scrnMsg));
        int selectedRow = getButtonSelectNumber();
        NSAL0490CommonLogic.setParamMdse(scrnMsg);
        // add start 2016/03/01 CSA Defect#3586
        NSAL0490CommonLogic.clearParamNWAL6800(scrnMsg);
        // add end 2016/03/01 CSA Defect#3486
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            // mod start 2016/03/01 CSA Defect#3586
            setArgForSubScreen(NSAL0490CommonLogic.getParamNWAL6800ForItemConfig(scrnMsg, selectedRow));
            // mod end 2016/03/01 CSA Defect#3586
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        } else if (NSAL0490Constant.TAB_CRITICALITY.equals(scrnMsg.xxDplyTab.getValue())) {
            setArgForSubScreen(NSAL0490CommonLogic.getParamNWAL6800ForCriticality(scrnMsg, selectedRow));
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        } else {
            // mod start 2016/03/01 CSA Defect#3586
            setArgForSubScreen(NSAL0490CommonLogic.getParamNWAL6800ForSupplyMap(scrnMsg, selectedRow));
            // mod end 2016/03/01 CSA Defect#3586
        }
        // 2015/10/07 CSA Y.Tsuchimoto Mod End
    }
}
