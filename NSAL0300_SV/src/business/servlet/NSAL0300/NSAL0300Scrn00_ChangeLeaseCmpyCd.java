/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         T.Tomita        Create          QC3887
 * 2016/02/26   Hitachi         T.Kanasaka      Update          QC4092
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#3886
 * 2017/10/10   CITS            M.Naito         Update          QC#21072
 * 2018/05/15   Hitachi         K.Kitachi       Update          QC#24265
 *</pre>
 */
public class NSAL0300Scrn00_ChangeLeaseCmpyCd extends S21CommonHandler {

    // START 2016/02/26 T.Kanasaka [QC4092, MOD]
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.leaseCmpyCd)) {
            scrnMsg.addCheckItem(scrnMsg.leaseCmpyCd);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg  = (NSAL0300CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (!ZYPCommonFunc.hasValue(scrnMsg.leaseCmpyCd)) {
            scrnMsg.baseChrgToLeaseCmpyFlg.clear();
            scrnMsg.usgChrgToLeaseCmpyFlg.clear();
            // START 2018/05/15 K.Kitachi [QC#24265, ADD]
            scrnMsg.cfsLeaseNumCmntTxt.clear();
            // END 2018/05/15 K.Kitachi [QC#24265, ADD]
        }

        // START 2016/04/26 T.Tomita [QC#3886, ADD]
        this.setResult("no");
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");
            NSAL0300CommonLogic.clearPopupParam(scrnMsg);

            setValue(scrnMsg.xxScrEventNm, "OpenWin_LeaseCompany");
            setValue(scrnMsg.xxPopPrm_12, DISP_HRCH_ACCT_CD_BILL);
            // START 2017/10/10 M.Naito [QC#21072, MOD]
            String leaseCmpyCd = null;
            if (!("%").equals(scrnMsg.leaseCmpyCd.getValue())) {
                leaseCmpyCd = scrnMsg.leaseCmpyCd.getValue().concat(PERCENT);
            }
            setValue(scrnMsg.xxPopPrm_15, leaseCmpyCd);
            // END 2017/10/10 M.Naito [QC#21072, MOD]
            Object[] prm = new Object[24];
            prm[0] = scrnMsg.xxPopPrm_00;
            prm[1] = scrnMsg.xxPopPrm_01;
            prm[2] = scrnMsg.xxPopPrm_02;
            prm[3] = scrnMsg.xxPopPrm_03;
            prm[4] = scrnMsg.xxPopPrm_04;
            prm[5] = scrnMsg.xxPopPrm_05;
            prm[6] = scrnMsg.xxPopPrm_06;
            prm[7] = scrnMsg.xxPopPrm_07;
            prm[8] = scrnMsg.xxPopPrm_08;
            prm[9] = scrnMsg.xxPopPrm_09;
            prm[10] = scrnMsg.xxPopPrm_10;
            prm[11] = scrnMsg.xxPopPrm_11;
            prm[12] = scrnMsg.xxPopPrm_12;
            prm[13] = scrnMsg.xxPopPrm_13;
            prm[14] = scrnMsg.xxPopPrm_14;
            prm[15] = scrnMsg.xxPopPrm_15;
            prm[16] = scrnMsg.xxPopPrm_16;
            prm[17] = scrnMsg.xxPopPrm_17;
            prm[18] = scrnMsg.xxPopPrm_18;
            prm[19] = scrnMsg.xxPopPrm_19;
            prm[20] = scrnMsg.xxPopPrm_20;
            prm[21] = scrnMsg.xxPopPrm_21;
            prm[22] = scrnMsg.xxPopPrm_22;
            prm[23] = scrnMsg.xxPopPrm_23;

            setArgForSubScreen(prm);
            return;
        }
        // END 2016/04/26 T.Tomita [QC#3886, ADD]

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        scrnMsg.addCheckItem(scrnMsg.leaseCmpyCd);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.leaseCmpyCd)) {
            scrnMsg.setFocusItem(scrnMsg.baseChrgToLeaseCmpyFlg);
        } else {
            scrnMsg.setFocusItem(scrnMsg.custPoNum);
        }
    }
    // END 2016/02/26 T.Kanasaka [QC4092, MOD]
}
