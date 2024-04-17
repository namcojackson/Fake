/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

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
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/23   Hitachi         T.Kanasaka      Create          QC#9905
 *</pre>
 */
public class NSAL0300Scrn00_ChangeShipToCustCd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        if (ZYPCommonFunc.hasValue(scrnMsg.B.no(rowNum).shipToCustCd_B)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(rowNum).shipToCustCd_B);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CMsg bizMsg = new NSAL0300CMsg();

        int rowNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(rowNum));

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

        this.setResult("no");
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");
            NSAL0300CommonLogic.clearPopupParam(scrnMsg);

            setValue(scrnMsg.xxScrEventNm, "OpenWin_ShipTo");
            int idx = getButtonSelectNumber();
            setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(idx));
            setValue(scrnMsg.xxPopPrm_12, DISP_HRCH_ACCT_CD_SHIP);
            setValue(scrnMsg.xxPopPrm_16, scrnMsg.B.no(idx).shipToCustCd_B.getValue().concat(PERCENT));
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

        NSAL0300CommonLogic.setupScreenItems(this, scrnMsg);
        int rowNum = scrnMsg.xxRowNum.getValue().intValue();
        scrnMsg.addCheckItem(scrnMsg.B.no(rowNum).shipToCustCd_B);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        scrnMsg.setFocusItem(scrnMsg.B.no(rowNum).baseBillToCustCd_B);
    }
}
