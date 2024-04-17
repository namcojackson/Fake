/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0600;

import static business.servlet.NLCL0600.constant.NLCL0600Constant.BIZ_APP_ID;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.DISPLAY_NM_PHYS_INVTY_DT;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.NPAM1230E;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.NLCM0175E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0600.NLCL0600CMsg;
import business.servlet.NLCL0600.common.NLCL0600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : Submit
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2016   CITS            Makoto Okigami  Create          N/A
 * 10/05/2016   CITS            Y.Fujii         Update          QC#14417
 *</pre>
 */
public class NLCL0600Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.physInvtyDt);
        scrnMsg.addCheckItem(scrnMsg.physInvtyCtrlNm);
        scrnMsg.addCheckItem(scrnMsg.physInvtyCtrlDescNm);

        if (ZYPCommonFunc.hasValue(scrnMsg.physInvtyDt)) {
            if (!ZYPDateUtil.isFutureDate(scrnMsg.physInvtyDt.getValue())) {
                scrnMsg.physInvtyDt.setErrorInfo(1, NPAM1230E, new String[] {DISPLAY_NM_PHYS_INVTY_DT});
            }
        }

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NLCM0175E);
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;

        NLCL0600CMsg bizMsg = new NLCL0600CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;
        NLCL0600CMsg bizMsg  = (NLCL0600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.physInvtyDt);
        scrnMsg.addCheckItem(scrnMsg.physInvtyCtrlNm);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }

        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NLCL0600CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

    }
}
