/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2700;

import static business.servlet.NMAL2700.constant.NMAL2700Constant.BIZ_ID;
import java.util.ArrayList;
import java.util.List;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.NZZM0002I;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.ZZM9000E;
import static business.servlet.NMAL2700.constant.NMAL2700Constant.NMAM0072E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2700.NMAL2700CMsg;
import business.servlet.NMAL2700.common.NMAL2700CommonLogic;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2700Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 * 2016/05/04   SRAA            Y.Chen          Update          QC#5575
 * 2016/06/01   Fujitsu         C.Yokoi         Update          CSA-QC#9249
 *</pre>
 */
public class NMAL2700Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.firstOrgCd);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpCd);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpNm);
        scrnMsg.addCheckItem(scrnMsg.orgFuncRoleTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.mgrFlg);
        scrnMsg.addCheckItem(scrnMsg.spclFlg);
        scrnMsg.addCheckItem(scrnMsg.equipFlg);
        scrnMsg.addCheckItem(scrnMsg.cmsnFlg);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
        // 2016/06/01 CSA-QC#9249 Delete Start
        // NMAL2700CommonLogic.checkMandator(scrnMsg);
        // 2016/06/01 CSA-QC#9249 Delete End

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).orgFuncRoleTpCd_A.isInputProtected()) {
                scrnMsg.A.no(i).orgFuncRoleTpCd_A.clearErrorInfo();
            }
            checkMandatory(scrnMsg, i);
            checkDuplicate(scrnMsg, i);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).firstOrgCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).orgFuncRoleTpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).orgFuncRoleTpNm_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).orgFuncRoleTpDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).gesTpCd_A);
            // QC#5575
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apvlLimitAmt_A);
        }
        scrnMsg.putErrorScreen();
    }

    private void checkDuplicate(NMAL2700BMsg scrnMsg, int i) {
        // 2016/06/08 CSA-QC#9324 Mod Start
        for (int cmprIdx = 0; cmprIdx < scrnMsg.A.getValidCount(); cmprIdx++) {
            if (i == cmprIdx) {
                continue;
            }

            if (scrnMsg.A.no(i).orgFuncRoleTpCd_A.getValue().equals(scrnMsg.A.no(cmprIdx).orgFuncRoleTpCd_A.getValue())) {
                scrnMsg.A.no(i).orgFuncRoleTpCd_A.setErrorInfo(1, NMAM0072E, new String[]{"Role Maintenance Detail"});
            }

            if (scrnMsg.A.no(i).orgFuncRoleTpNm_A.getValue().equals(scrnMsg.A.no(cmprIdx).orgFuncRoleTpNm_A.getValue())) {
                scrnMsg.A.no(i).orgFuncRoleTpNm_A.setErrorInfo(1, NMAM0072E, new String[]{"Role Maintenance Detail"});
            }
        }
        // 2016/06/08 CSA-QC#9324 Mod End
    }

    private void checkMandatory(NMAL2700BMsg scrnMsg, int i) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).firstOrgCd_A)) {
            scrnMsg.A.no(i).firstOrgCd_A.setErrorInfo(1, ZZM9000E , new String[]{scrnMsg.A.no(i).firstOrgCd_A.getNameForMessage()});
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).orgFuncRoleTpCd_A)) {
            scrnMsg.A.no(i).orgFuncRoleTpCd_A.setErrorInfo(1, ZZM9000E , new String[]{scrnMsg.A.no(i).orgFuncRoleTpCd_A.getNameForMessage()});
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).firstOrgCd_A)) {
            scrnMsg.A.no(i).firstOrgCd_A.setErrorInfo(1, ZZM9000E , new String[]{scrnMsg.A.no(i).firstOrgCd_A.getNameForMessage()});
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).orgFuncRoleTpNm_A)) {
            scrnMsg.A.no(i).orgFuncRoleTpNm_A.setErrorInfo(1, ZZM9000E , new String[]{scrnMsg.A.no(i).orgFuncRoleTpNm_A.getNameForMessage()});
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        NMAL2700CMsg bizMsg = new NMAL2700CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

       NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
       NMAL2700CMsg bizMsg = (NMAL2700CMsg) cMsg;
       EZDMsg.copy(bizMsg, null, scrnMsg, null);

       for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
           scrnMsg.addCheckItem(scrnMsg.A.no(i).orgFuncRoleTpCd_A);
           scrnMsg.addCheckItem(scrnMsg.A.no(i).orgFuncRoleTpNm_A);
       }
       NMAL2700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
       scrnMsg.putErrorScreen();
       if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
       }
    }
}
