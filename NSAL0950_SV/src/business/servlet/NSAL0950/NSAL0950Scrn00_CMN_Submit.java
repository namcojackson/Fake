/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0950;

import static business.servlet.NSAL0950.constant.NSAL0950Constant.BUSINESS_ID;
import static business.servlet.NSAL0950.constant.NSAL0950Constant.FUNCTION_SUBMIT;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0950.NSAL0950CMsg;
import business.servlet.NSAL0950.common.NSAL0950CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/14   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/07/07   Hitachi         O.Okuma         Update          QC#10991
 *</pre>
 */
public class NSAL0950Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0950BMsg scrnMsg = (NSAL0950BMsg) bMsg;
        // START 2016/07/07 [QC#10991, ADD]
        scrnMsg.dsContrVldCatgCd_DS.clearErrorInfo();
        scrnMsg.dsContrVldNm_D.clearErrorInfo();
        // END 2016/07/07 [QC#10991, ADD]
        scrnMsg.addCheckItem(scrnMsg.dsContrVldCatgCd_DS);
        scrnMsg.addCheckItem(scrnMsg.dsContrVldNm_D);
        scrnMsg.addCheckItem(scrnMsg.dsContrVldDescTxt_D);
        scrnMsg.addCheckItem(scrnMsg.vldLvlContrFlg_D);
        scrnMsg.addCheckItem(scrnMsg.vldLvlMachFlg_D);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_D);
        scrnMsg.addCheckItem(scrnMsg.effToDt_D);
        scrnMsg.addCheckItem(scrnMsg.vldCmptTxt_D);
        scrnMsg.addCheckItem(scrnMsg.primVldFlg_D);
        scrnMsg.addCheckItem(scrnMsg.ovrdVldRsltAvalFlg_D);
        scrnMsg.addCheckItem(scrnMsg.vldRegFlg_D);
        scrnMsg.addCheckItem(scrnMsg.vldFleetFlg_D);
        scrnMsg.addCheckItem(scrnMsg.vldAggrFlg_D);
        scrnMsg.addCheckItem(scrnMsg.vldWtyFlg_D);
        NSAL0950CommonLogic.setFromToDateCheck(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0950BMsg scrnMsg = (NSAL0950BMsg) bMsg;

        NSAL0950CMsg bizMsg = new NSAL0950CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0950BMsg scrnMsg = (NSAL0950BMsg) bMsg;
        NSAL0950CMsg bizMsg  = (NSAL0950CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/07/07 [QC#10991, ADD]
        scrnMsg.addCheckItem(scrnMsg.dsContrVldCatgCd_DS);
        scrnMsg.addCheckItem(scrnMsg.dsContrVldNm_D);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_D);
        scrnMsg.addCheckItem(scrnMsg.effToDt_D);
        // END 2016/07/07 [QC#10991, ADD]
        scrnMsg.putErrorScreen();
        NSAL0950CommonLogic.initialControlScreen(this, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            setValue(scrnMsg.xxRadioBtn_A, BigDecimal.ZERO);
            BigDecimal selectedRow = scrnMsg.xxRadioBtn_A.getValue();
            if (selectedRow == null || scrnMsg.A.getValidCount() < selectedRow.intValue()) {
                scrnMsg.setFocusItem(scrnMsg.dsContrVldCatgCd_SS);
                return;
            }
            if (hasValue(scrnMsg.A.no(selectedRow.intValue()).dsContrVldPk_A)) {
                NSAL0950CommonLogic.controlScreenDetailFields(this, scrnMsg, false);
                scrnMsg.setFocusItem(scrnMsg.dsContrVldDescTxt_D);
            } else {
                NSAL0950CommonLogic.controlScreenDetailFields(this, scrnMsg, true);
                scrnMsg.setFocusItem(scrnMsg.dsContrVldCatgCd_DS);
            }
        }
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }
}
