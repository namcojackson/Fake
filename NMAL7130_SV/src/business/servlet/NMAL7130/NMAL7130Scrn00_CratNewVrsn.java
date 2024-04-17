/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BIZ_ID;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.MSG_NEW_VRSN;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.NMAM8295W;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7130.NMAL7130CMsg;
import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7130Scrn00_CratNewVrsn
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/22   Fujitsu         W.Honda         Update          CSA-QC#3636
 *</pre>
 */
public class NMAL7130Scrn00_CratNewVrsn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        // START 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]
        NMAL7130CommonLogic.addCheckScreenItem(scrnMsg);
        scrnMsg.putErrorScreen();
        // END 2016/02/22 W.Honda [S21 CSA QC#3636 ADD]

        scrnMsg.addCheckItem(scrnMsg.ordTrxFlexPct_E1);
        scrnMsg.addCheckItem(scrnMsg.tonerAlwncPct_E1);
        scrnMsg.addCheckItem(scrnMsg.nonStdStartTm_E1);
        scrnMsg.addCheckItem(scrnMsg.maxDownTmDaysAot_E1);
        scrnMsg.addCheckItem(scrnMsg.lflReplTermNum_E1);
        scrnMsg.addCheckItem(scrnMsg.tmAndMatUplftTxt_E1);
        scrnMsg.addCheckItem(scrnMsg.docReqFrmAgmtNm_E1);
        scrnMsg.addCheckItem(scrnMsg.mstrAgmtDocNm_E1);
        scrnMsg.addCheckItem(scrnMsg.supplTermCmpyStdFrmTxt_E1);
        scrnMsg.addCheckItem(scrnMsg.aftHourBillRate_E1);
        scrnMsg.addCheckItem(scrnMsg.rspTmComitTxt_E1);
        scrnMsg.addCheckItem(scrnMsg.svcEtaCallReqHrsNum_E1);
        scrnMsg.addCheckItem(scrnMsg.tonerTpNm_E1);
        scrnMsg.addCheckItem(scrnMsg.tonerYieldCnt_E1);
        scrnMsg.addCheckItem(scrnMsg.maxRnwIncrAmtRate_E1);
        scrnMsg.addCheckItem(scrnMsg.maxStdAnnIncrPct_E1);
        scrnMsg.addCheckItem(scrnMsg.upTmGtdPct_E1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd.getValue())) {
            return null;
        }


        NMAL7130CMsg bizMsg = new NMAL7130CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd.getValue())) {
            scrnMsg.setMessageInfo(NMAM8295W, MSG_NEW_VRSN);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
            return;
        }
        NMAL7130CMsg bizMsg  = (NMAL7130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7130CommonLogic.setTermCondProtect(this, scrnMsg);

        scrnMsg.xxYesNoCd.clear();

    }
}
