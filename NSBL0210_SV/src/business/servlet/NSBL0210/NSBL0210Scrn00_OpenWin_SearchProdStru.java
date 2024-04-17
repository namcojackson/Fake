/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0210;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/28   SRA             E.Inada         Create          N/A
 * 2016/05/18   Hitachi         Y.Takeno        Update          NA#CSA
 *</pre>
 */
public class NSBL0210Scrn00_OpenWin_SearchProdStru extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

//        NSBL0210BMsg scrnMsg = (NSBL0210BMsg) bMsg;
//
//        int selectIdx = getButtonSelectNumber();
//
//        scrnMsg.xxPopPrm_P1.clear();
//        scrnMsg.xxPopPrm_P2.clear();
//        scrnMsg.xxPopPrm_P3.clear();
//        scrnMsg.xxPopPrm_P4.clear();
//        scrnMsg.xxPopPrm_P5.clear();
//        scrnMsg.xxPopPrm_P6.clear();
//        scrnMsg.xxPopPrm_P7.clear();
//
//        Object[] params = new Object[PARAM_27 + 1];
//
//        if (selectIdx < 0) {
//            // 1 : ZEROTH_PROD_CTRL_CD
//            params[PARAM_0] = scrnMsg.zerothProdCtrlCd;
//            // 3 : FIRST_PROD_CTRL_CD
//            params[PARAM_2] = scrnMsg.firstProdCtrlCd;
//            // 5 : SCD_PROD_CTRL_CD
//            params[PARAM_4] = scrnMsg.scdProdCtrlCd;
//            // 7 : THIRD_PROD_CTRL_CD
//            params[PARAM_6] = scrnMsg.thirdProdCtrlCd;
//            // 9 : FRTH_PROD_CTRL_CD
//            params[PARAM_8] = scrnMsg.frthProdCtrlCd;
//            // 11 : FIFTH_PROD_CTRL_CD
//            params[PARAM_10] = scrnMsg.fifthProdCtrlCd;
//            // 13 : MDSE_CD
//            params[PARAM_12] = scrnMsg.mdseCd;
//        } else {
//            // 1 : ZEROTH_PROD_CTRL_CD
//            params[PARAM_0] = scrnMsg.A.no(selectIdx).zerothProdCtrlCd_A;
//            // 3 : FIRST_PROD_CTRL_CD
//            params[PARAM_2] = scrnMsg.A.no(selectIdx).firstProdCtrlCd_A;
//            // 5 : SCD_PROD_CTRL_CD
//            params[PARAM_4] = scrnMsg.A.no(selectIdx).scdProdCtrlCd_A;
//            // 7 : THIRD_PROD_CTRL_CD
//            params[PARAM_6] = scrnMsg.A.no(selectIdx).thirdProdCtrlCd_A;
//            // 9 : FRTH_PROD_CTRL_CD
//            params[PARAM_8] = scrnMsg.A.no(selectIdx).frthProdCtrlCd_A;
//            // 11 : FIFTH_PROD_CTRL_CD
//            params[PARAM_10] = scrnMsg.A.no(selectIdx).fifthProdCtrlCd_A;
//            // 13 : MDSE_CD
//            params[PARAM_12] = scrnMsg.A.no(selectIdx).mdseCd_A;
//        }
//
//        // 2 : ZEROTH_PROD_CTRL_NM
//        params[PARAM_1] = scrnMsg.xxPopPrm_P1;
//        // 4 : FIRST_PROD_CTRL_NM
//        params[PARAM_3] = scrnMsg.xxPopPrm_P2;
//        // 6 : SCD_PROD_CTRL_NM
//        params[PARAM_5] = scrnMsg.xxPopPrm_P3;
//        // 8 : THIRD_PROD_CTRL_NM
//        params[PARAM_7] = scrnMsg.xxPopPrm_P4;
//        // 10 : FRTH_PROD_CTRL_NM
//        params[PARAM_9] = scrnMsg.xxPopPrm_P5;
//        // 12 : FIFTH_PROD_CTRL_NM
//        params[PARAM_11] = scrnMsg.xxPopPrm_P6;
//        // 14 : MDSE_NM
//        params[PARAM_13] = scrnMsg.xxPopPrm_P7;
//
//        params[PARAM_14] = params[PARAM_0];
//        params[PARAM_15] = params[PARAM_1];
//        params[PARAM_16] = params[PARAM_2];
//        params[PARAM_17] = params[PARAM_3];
//        params[PARAM_18] = params[PARAM_4];
//        params[PARAM_19] = params[PARAM_5];
//        params[PARAM_20] = params[PARAM_6];
//        params[PARAM_21] = params[PARAM_7];
//        params[PARAM_22] = params[PARAM_8];
//        params[PARAM_23] = params[PARAM_9];
//        params[PARAM_24] = params[PARAM_10];
//        params[PARAM_25] = params[PARAM_11];
//        params[PARAM_26] = params[PARAM_12];
//        params[PARAM_27] = params[PARAM_13];
//
//        setArgForSubScreen(params);
    }
}
