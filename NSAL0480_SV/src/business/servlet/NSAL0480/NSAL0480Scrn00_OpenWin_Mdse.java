/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480;

import static business.servlet.NSAL0480.constant.NSAL0480Constant.NMAL6030_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg; //import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; //import business.blap.NSAL0480.NSAL0480CMsg;
//import business.servlet.NSAL0480.constant.NSAL0480Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 * 2016/03/01   Hitachi         K.Kasai         Update          QC#3586
 *</pre>
 */
public class NSAL0480Scrn00_OpenWin_Mdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0480BMsg scrnMsg = (NSAL0480BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        // 2015/10/05 CSA Y.Tsuchimoto Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P, TBL_NM.MDSE.toString());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P, COL_NM.MDSE_CD.toString());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P, COL_NM.MDSE_NM.toString());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P, COL_NM.MDSE_CD.toString());
//
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P, LBL.MERCHANDISE_POPUP.getLbl());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P, LBL.MERCHANDISE_CODE.getLbl());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P, LBL.MERCHANDISE_NAME.getLbl());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P, LBL.MERCHANDISE_CODE.getLbl());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P, LBL.MERCHANDISE_NAME.getLbl());
//
//        scrnMsg.xxCondCd_P.clear();
//        scrnMsg.xxCondNm_P.clear();
//
//        Object[] params = new Object[NMAL6050_PRM_LENGTH];
//        int i = 0;
//        params[i++] = scrnMsg.xxTblNm_P;
//        params[i++] = scrnMsg.xxTblCdColNm_P;
//        params[i++] = scrnMsg.xxTblNmColNm_P;
//        params[i++] = scrnMsg.xxTblSortColNm_P;
//        params[i++] = scrnMsg.xxScrNm_P;
//        params[i++] = scrnMsg.xxHdrCdLbNm_P;
//        params[i++] = scrnMsg.xxHdrNmLbNm_P;
//        params[i++] = scrnMsg.xxDtlCdLbNm_P;
//        params[i++] = scrnMsg.xxDtlNmLbNm_P;
//        params[i++] = scrnMsg.t_ItemCd_H;
//        params[i++] = scrnMsg.xxCondNm_P;
        scrnMsg.xxCondCd_P.clear();
        scrnMsg.xxCondNm_P.clear();

        Object[] params = new Object[NMAL6030_PRM_LENGTH];
        // add start 2016/03/01 CSA Defect#3586
        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        // add end 2016/03/01 CSA Defect#3586
        int i = 0;
        params[i++] = scrnMsg.t_ItemCd_H;
        // mod start 2016/03/01 CSA Defect#3586
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        // mod end 2016/03/01 CSA Defect#3586
        // 2015/10/05 CSA Y.Tsuchimoto Mod End
        setArgForSubScreen(params);

    }
}
