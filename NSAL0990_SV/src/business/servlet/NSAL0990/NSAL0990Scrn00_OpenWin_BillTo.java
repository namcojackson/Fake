/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0990.common.NSAL0990CommonLogic;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/03/08   Hitachi         A.Kohinata      Update          QC5143
 * 2016/03/29   Hitachi         A.Kohinata      Update          QC5790
 * 2018/06/11   Fujitsu         T.Ogura         Update          QC23146
 * 2018/07/30   Hitachi         T.Tomita        Update          QC#14307
 *</pre>
 */
public class NSAL0990Scrn00_OpenWin_BillTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CommonLogic.clearPopUpParam(scrnMsg);

        setValue(scrnMsg.xxScrEventNm, "OpenWin_BillTo");
        Object[] params = new Object[NSAL0990Constant.PRM_NMAL6760];
        int i = 0;
        setValue(scrnMsg.xxPopPrm_P0, scrnMsg.billToAcctNum);
        params[i++] = scrnMsg.xxPopPrm_P0;
        params[i++] = scrnMsg.xxPopPrm_P1;
        params[i++] = scrnMsg.xxPopPrm_P2;
        params[i++] = scrnMsg.xxPopPrm_P3;
        params[i++] = scrnMsg.xxPopPrm_P4;
        params[i++] = scrnMsg.xxPopPrm_P5;
        params[i++] = scrnMsg.xxPopPrm_P6;
        params[i++] = scrnMsg.xxPopPrm_P7;
        params[i++] = scrnMsg.xxPopPrm_P8;
        params[i++] = scrnMsg.xxPopPrm_P9;
        params[i++] = scrnMsg.xxPopPrm_PA;
        params[i++] = scrnMsg.xxPopPrm_PB;
        // Bill To's Only
        setValue(scrnMsg.xxPopPrm_PC, NSAL0990Constant.DISP_HRCH_ACCT_CD_BILL);
        params[i++] = scrnMsg.xxPopPrm_PC;
        params[i++] = scrnMsg.xxPopPrm_PD;
        params[i++] = scrnMsg.xxPopPrm_PE;
        // Bill To Location
        // START 2018/06/11 T.Ogura [QC#23146,DEL]
//        setValue(scrnMsg.xxPopPrm_PF, scrnMsg.billToLocNum);
        // END   2018/06/11 T.Ogura [QC#23146,DEL]
        params[i++] = scrnMsg.xxPopPrm_PF;
        params[i++] = scrnMsg.xxPopPrm_PG;
        params[i++] = scrnMsg.xxPopPrm_PH;
        params[i++] = scrnMsg.xxPopPrm_PI;
        params[i++] = scrnMsg.xxPopPrm_PJ;
        params[i++] = scrnMsg.xxPopPrm_PK;
        params[i++] = scrnMsg.xxPopPrm_PL;
        params[i++] = scrnMsg.xxPopPrm_PM;
        params[i++] = scrnMsg.xxPopPrm_PN;
        // Display Hierarkey Active Flag
        setValue(scrnMsg.xxPopPrm_PO, ZYPConstant.FLG_OFF_N);
        params[i++] = scrnMsg.xxPopPrm_PO;
        // Account Number Active Flag
        if (ZYPCommonFunc.hasValue(scrnMsg.billToAcctNum)) {
            setValue(scrnMsg.xxPopPrm_PP, ZYPConstant.FLG_OFF_N);
        } else {
            setValue(scrnMsg.xxPopPrm_PP, ZYPConstant.FLG_ON_Y);
        }
        params[i++] = scrnMsg.xxPopPrm_PP;
        params[i++] = scrnMsg.xxPopPrm_PQ;
        // Add Start 2018/07/30 QC#14307
        params[i++] = scrnMsg.xxPopPrm_PR;
        params[i++] = scrnMsg.xxPopPrm_PS;
        params[i++] = scrnMsg.xxPopPrm_PT;
        params[i++] = scrnMsg.xxPopPrm_PU;
        params[i++] = scrnMsg.xxPopPrm_PV;
        params[i++] = scrnMsg.xxPopPrm_PW;
        params[i++] = scrnMsg.xxPopPrm_PX;
        params[i++] = scrnMsg.xxPopPrm_PY;
        params[i++] = scrnMsg.xxPopPrm_PZ;
        setValue(scrnMsg.xxPopPrm_10, ZYPConstant.FLG_ON_Y);
        params[i++] = scrnMsg.xxPopPrm_10;
        setValue(scrnMsg.xxPopPrm_11, scrnMsg.dsTrxRuleTpCd);
        params[i++] = scrnMsg.xxPopPrm_11;
        params[i++] = scrnMsg.xxPopPrm_12;
        setValue(scrnMsg.xxPopPrm_13, NSAL0990Constant.BIZ_ID);
        params[i++] = scrnMsg.xxPopPrm_13;
        setValue(scrnMsg.xxPopPrm_14, scrnMsg.funcMstrIdDescTxt);
        params[i++] = scrnMsg.xxPopPrm_14;
        setValue(scrnMsg.xxPopPrm_15, scrnMsg.svcByLineBizTpCd);
        params[i++] = scrnMsg.xxPopPrm_15;
        // Add End 2018/07/30 QC#14307
        setArgForSubScreen(params);
    }
}
