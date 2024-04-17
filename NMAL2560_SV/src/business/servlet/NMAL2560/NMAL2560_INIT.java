/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2560;

import static business.servlet.NMAL2560.constant.NMAL2560Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2560.NMAL2560CMsg;
import business.servlet.NMAL2560.common.NMAL2560CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL2560_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2560_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2560BMsg scrnMsg = (NMAL2560BMsg) bMsg;
        NMAL2560CMsg bizMsg = new NMAL2560CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2560BMsg scrnMsg = (NMAL2560BMsg) bMsg;
        NMAL2560CMsg bizMsg = (NMAL2560CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2560CommonLogic.initCmnBtnProp(this);
        NMAL2560CommonLogic.controlScreenFields(getUserProfileService(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()), this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2560BMsg scrnMsg = (NMAL2560BMsg) bMsg;

        // Header
        scrnMsg.bizAreaOrgCd.setNameForMessage("Business Area");
        scrnMsg.xxChkBox.setNameForMessage("Show Inactive");

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).bizAreaOrgCd_A.setNameForMessage("Business Area");
            scrnMsg.A.no(i).orgHrchStruDfnNm_A.setNameForMessage("Structure Name");
            scrnMsg.A.no(i).effFromDt_A.setNameForMessage("Effective From");
            scrnMsg.A.no(i).effThruDt_A.setNameForMessage("Effective To");
            scrnMsg.A.no(i).struDfnCd_1.setNameForMessage("Tier1");
            scrnMsg.A.no(i).struDfnCd_2.setNameForMessage("Tier2");
            scrnMsg.A.no(i).struDfnCd_3.setNameForMessage("Tier3");
            scrnMsg.A.no(i).struDfnCd_4.setNameForMessage("Tier4");
            scrnMsg.A.no(i).struDfnCd_5.setNameForMessage("Tier5");
            scrnMsg.A.no(i).struDfnCd_6.setNameForMessage("Tier6");
            scrnMsg.A.no(i).struDfnCd_7.setNameForMessage("Tier7");
            scrnMsg.A.no(i).struDfnCd_8.setNameForMessage("Tier8");
            scrnMsg.A.no(i).struDfnCd_9.setNameForMessage("Tier9");
            scrnMsg.A.no(i).struDfnCd_10.setNameForMessage("Tier10");
            scrnMsg.A.no(i).xxChkMaxDescTxt_IN.setNameForMessage("Created By");
            scrnMsg.A.no(i).xxDtTm_IN.setNameForMessage("Created On");
            scrnMsg.A.no(i).xxChkMaxDescTxt_UP.setNameForMessage("Last Updated By");
            scrnMsg.A.no(i).xxDtTm_UP.setNameForMessage("Last Updated On");
        }
    }
}
