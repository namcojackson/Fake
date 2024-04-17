/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;
import business.servlet.NSAL0300.constant.NSAL0300Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Hitachi         T.Kanasaka      Create          N/A
 * 2015/11/25   Hitachi         A.Kohinata      Update          QC981
 * 2016/01/02   Hitachi         T.Tomita        Update          CSA QC#2686
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_AddNotes extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID(NSAL0300Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0300Constant.EZD_FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        NSAL0300CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // START 2016/01/12 T.Tomita [QC2686, MOD]
//        Object[] prm = new Object[1];
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).dsContrPk_P1, scrnMsg.dsContrPk);
//        scrnMsg.P.setValidCount(1);
//        prm[0] = scrnMsg.P;
        Object[] prm = setParams(scrnMsg);
        // END 2016/01/12 T.Tomita [QC2686, MOD]

        setArgForSubScreen(prm);
    }

    // START 2016/01/12 T.Tomita [QC2686, ADD]
    private Object[] setParams(NSAL0300BMsg scrnMsg) {
        // Set Params
        setPopupParam(scrnMsg);

        Object[] prm = new Object[23];
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
        String flg = ZYPConstant.FLG_ON_Y;
        if (hasValue(scrnMsg.xxModeCd_FU) && ZYPConstant.FLG_ON_1.equals(scrnMsg.xxModeCd_FU.getValue())) {
            flg = ZYPConstant.FLG_OFF_N;
        }
        prm[22] = flg;
        return prm;
    }

    private void setPopupParam(NSAL0300BMsg scrnMsg) {
        setValue(scrnMsg.xxPopPrm_00, SVC_MEMO_CATG.CONTRACT_MEMO);
        scrnMsg.xxPopPrm_01.clear();
        setValue(scrnMsg.xxPopPrm_02, "Contract Number");
        setValue(scrnMsg.xxPopPrm_03, scrnMsg.dsContrNum);
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        setValue(scrnMsg.xxPopPrm_12, "DS_CONTR_PK");
        setValue(scrnMsg.xxPopPrm_13, scrnMsg.dsContrPk.getValue().toString());
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
    }
    // END 2016/01/12 T.Tomita [QC2686, ADD]
}
