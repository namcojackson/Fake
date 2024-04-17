/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.common.NSAL0490CommonLogic;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/11   Fujitsu         T.Yoshida       Create          N/A
 * 2016/03/15   Hitachi         M.Gotou         Update          QC#5081
 * 2016/05/19   Hitachi         K.Kasai         Update          QC#447
 * 2017/08/01   CITS            S.Endo          Update          Sol#072(QC#18386)
 *</pre>
 */
public class NSAL0490Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            NSAL0490CommonLogic.checkHeader(scrnMsg);
            NSAL0490CommonLogic.clearStartDateOfEmptyLine(scrnMsg);
            NSAL0490CommonLogic.checkItemConfig(scrnMsg);
        } else if (NSAL0490Constant.TAB_SVC_RULES.equals(dplyTab)) {
            NSAL0490CommonLogic.checkSvcRules(scrnMsg);
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            NSAL0490CommonLogic.checkSupplyMap(scrnMsg);
        // add start 2016/05/19 CSA Defect#447
        } else if (NSAL0490Constant.TAB_END_OF_LIFE.equals(dplyTab)) {
            NSAL0490CommonLogic.checkEndOfLife(scrnMsg);
        // add end 2016/05/19 CSA Defect#447
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        NSAL0490CMsg bizMsg = new NSAL0490CMsg();
        bizMsg.setBusinessID(NSAL0490Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0490Constant.FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            // add start 2016/03/15 CSA Defect#5081
            NSAL0490CommonLogic.checkHeader(scrnMsg);
            // add end 2016/03/15 CSA Defect#5081
            NSAL0490CommonLogic.checkItemConfig(scrnMsg);
            NSAL0490CommonLogic.controlSearchField(this, scrnMsg, false);
            NSAL0490CommonLogic.controlItemConfigField(this, scrnMsg);
            NSAL0490CommonLogic.controlInitTabBtn(scrnMsg);
            NSAL0490CommonLogic.setTblBackColorItemConfig(scrnMsg);
            scrnMsg.xxRadioBtn_A.setValue(0);
            scrnMsg.setFocusItem(scrnMsg.mdlNm);
        } else if (NSAL0490Constant.TAB_SVC_RULES.equals(dplyTab)) {
            scrnMsg.setFocusItem(scrnMsg.rcllIntvlDaysAot);
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            NSAL0490CommonLogic.checkSupplyMap(scrnMsg);
            NSAL0490CommonLogic.controlSupplyMapField(this, scrnMsg);
            NSAL0490CommonLogic.setTblBackColorSupplyMap(scrnMsg);
            scrnMsg.xxRadioBtn_B.setValue(0);
            scrnMsg.setFocusItem(scrnMsg.B.no(0).mdseCd_B);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        } else if (NSAL0490Constant.TAB_CRITICALITY.equals(dplyTab)) {
            NSAL0490CommonLogic.checkCriticality(scrnMsg);
            NSAL0490CommonLogic.controlCriticalityField(this, scrnMsg,true);
            NSAL0490CommonLogic.setTblBackColorCriticality(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.D.no(0).mdseCd_D);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        }
    }
}
