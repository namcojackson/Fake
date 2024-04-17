/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0620;

import static business.servlet.NLCL0620.constant.NLCL0620Constant.BIZ_APP_ID;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.DISPLAY_NM_PHYS_INVTY_DT;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.NLCM0064E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0620.NLCL0620CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * Function Name : Submit
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0620Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;

        // START 2018/12/12 S.Ohki [QC#28755,MOD]
//        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        //if (!ZYPCommonFunc.hasValue(scrnMsg.techTocCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {
        	//scrnMsg.addCheckItem(scrnMsg.techTocCd);
        	scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        }
        // END 2018/12/12 S.Ohki [QC#28755,MOD]
        scrnMsg.addCheckItem(scrnMsg.physInvtyDt);
        scrnMsg.addCheckItem(scrnMsg.physInvtyCtrlNm);

        if (ZYPCommonFunc.hasValue(scrnMsg.physInvtyDt)) {
            if (ZYPDateUtil.isPastDate(scrnMsg.physInvtyDt.getValue())) {
                scrnMsg.physInvtyDt.setErrorInfo(1, NLCM0064E, new String[] {DISPLAY_NM_PHYS_INVTY_DT});
            }
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;

        NLCL0620CMsg bizMsg = new NLCL0620CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;
        NLCL0620CMsg bizMsg  = (NLCL0620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            scrnMsg.addCheckItem(scrnMsg.techTocCd);
            // START 2018/12/12 S.Ohki [QC#28755,ADD]
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
            // END 2018/12/12 S.Ohki [QC#28755,ADD]
            scrnMsg.addCheckItem(scrnMsg.physInvtyCtrlNm);
            scrnMsg.putErrorScreen();
            return;
        }

    }
}
