/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0400;

import static business.servlet.NSAL0400.constant.NSAL0400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/19   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NSAL0400Scrn00_ClickNote extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0400BMsg scrnMsg = (NSAL0400BMsg) bMsg;

        NSAL0400_ABMsg abMsg = scrnMsg.A.no(getButtonSelectNumber());

        Object[] args = new Object[PRM_CNT_NSBL0160];

        scrnMsg.svcMemoCatgCd_P0.setValue(SVC_MEMO_CATG.CONTRACT_MEMO);
        scrnMsg.svcMemoTpCd_P1.setValue(SVC_MEMO_TP.TERMINATION_NOTE);
        setValue(scrnMsg.xxComnScrColLbTxt_P2, SCREEN_LABEL.MDSE_CD.getscreenLabel());
        setValue(scrnMsg.xxComnScrColLbTxt_P3, abMsg.mdseCd_AD);
        setValue(scrnMsg.xxComnScrColLbTxt_P4, SCREEN_LABEL.SERIAL.getscreenLabel());
        setValue(scrnMsg.xxComnScrColLbTxt_P5, abMsg.serNum_AD);
        setValue(scrnMsg.xxComnScrColLbTxt_P6, SCREEN_LABEL.MODEL.getscreenLabel());
        setValue(scrnMsg.xxComnScrColLbTxt_P7, abMsg.t_MdlNm_AD);
        setValue(scrnMsg.xxComnScrColLbTxt_P8, SCREEN_LABEL.SHIP_TO.getscreenLabel());
        setValue(scrnMsg.xxComnScrColLbTxt_P9, abMsg.shipToCustCd_AD);
        scrnMsg.xxComnScrColLbTxt_PA.clear();
        scrnMsg.xxComnScrColLbTxt_PB.clear();

        setValue(scrnMsg.xxComnScrColLbTxt_PC, LABEL.DS_CONTR_PK.toString());
        setValue(scrnMsg.xxComnScrColLbTxt_PD, abMsg.dsContrPk_AH.getValue().toString());
        setValue(scrnMsg.xxComnScrColLbTxt_PE, LABEL.DS_CONTR_DTL_PK.toString());
        setValue(scrnMsg.xxComnScrColLbTxt_PF, abMsg.dsContrDtlPk_AD.getValue().toString());
        scrnMsg.xxComnScrColLbTxt_PG.clear();
        scrnMsg.xxComnScrColLbTxt_PH.clear();
        scrnMsg.xxComnScrColLbTxt_PI.clear();
        scrnMsg.xxComnScrColLbTxt_PJ.clear();
        scrnMsg.xxComnScrColLbTxt_PK.clear();
        scrnMsg.xxComnScrColLbTxt_PL.clear();

        int i = 0;
        args[i++] = scrnMsg.svcMemoCatgCd_P0;
        args[i++] = scrnMsg.svcMemoTpCd_P1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_P2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_P3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_P4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_P5;
        args[i++] = scrnMsg.xxComnScrColLbTxt_P6;
        args[i++] = scrnMsg.xxComnScrColLbTxt_P7;
        args[i++] = scrnMsg.xxComnScrColLbTxt_P8;
        args[i++] = scrnMsg.xxComnScrColLbTxt_P9;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PA;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PB;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PC;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PD;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PE;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PF;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PG;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PH;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PI;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PJ;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PK;
        args[i++] = scrnMsg.xxComnScrColLbTxt_PL;

        setArgForSubScreen(args);

    }
}
