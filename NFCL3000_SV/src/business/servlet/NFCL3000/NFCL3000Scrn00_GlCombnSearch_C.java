/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         T.Tankaa        Create          N/A
 * 2016/07/04   Fujitsu         S.Fujita        Update          QC#10742
 * 2019/05/29   Fujitsu         S.Takami        Update          QC#50542
 *</pre>
 */
public class NFCL3000Scrn00_GlCombnSearch_C extends S21CommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int lineNum = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(lineNum);
        // START 2019/05/29 S.Takami [QC#50542,MOD]
//        scrnMsg.appFuncId.setValue("NFCL3000");
        String drCrTpCd = scrnMsg.C.no(lineNum).drCrTpCd_C1.getValue();
        if (S21StringUtil.isEquals(DR_CR_TP_DEBIT, drCrTpCd)) {
            scrnMsg.appFuncId.setValue(RESRC_OBJ_NM_DEBIT);
        } else {
            scrnMsg.appFuncId.setValue(RESRC_OBJ_NM_CREDIT);
        }
        // START 2019/05/29 S.Takami [QC#50542,MOD]
        // START 2016/07/04 S.Fujita [QC#10742,ADD]
        scrnMsg.addCheckItem(scrnMsg.C.no(lineNum).xxScrItem61Txt_C1);
        scrnMsg.putErrorScreen();
        // END   2016/07/04 S.Fujita [QC#10742,ADD]

        //NMAL2550
        Object[] param = new Object[10];
        param[0] = scrnMsg.appFuncId;
        param[1] = scrnMsg.C.no(lineNum).ajeCoaCmpyCd_C1;
        param[2] = scrnMsg.C.no(lineNum).ajeCoaAfflCd_C1;
        param[3] = scrnMsg.C.no(lineNum).ajeCoaBrCd_C1;
        param[4] = scrnMsg.C.no(lineNum).ajeCoaCcCd_C1;
        param[5] = scrnMsg.C.no(lineNum).ajeCoaAcctCd_C1;
        param[6] = scrnMsg.C.no(lineNum).ajeCoaProdCd_C1;
        param[7] = scrnMsg.C.no(lineNum).ajeCoaChCd_C1;
        param[8] = scrnMsg.C.no(lineNum).ajeCoaProjCd_C1;
        param[9] = scrnMsg.C.no(lineNum).ajeCoaExtnCd_C1;

        setArgForSubScreen(param);

    }
}
