/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/16   SRA             E.Inada         Create          N/A
 * 2017/02/23   Fujitsu         R.Nakamura      Update          QC#16011
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 *</pre>
 */
public class NMAL7010Scrn00_OnChange_RateType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        int idx = getButtonSelectNumber();

        if (PRC_RATE_TP.CPC.equals(scrnMsg.B.no(idx).prcRateTpCd_PB.getValue()) || !ZYPCommonFunc.hasValue(scrnMsg.B.no(idx).prcRateTpCd_PB.getValue())) {
            scrnMsg.B.no(idx).prcListMdseCd_PB.clear();
            scrnMsg.B.no(idx).mdseDescShortTxt_PB.clear();

        } else if (PRC_RATE_TP.ANNUAL.equals(scrnMsg.B.no(idx).prcRateTpCd_PB.getValue())) {
            scrnMsg.B.no(idx).prcListMdseCd_PB.clear();
            scrnMsg.B.no(idx).mdseDescShortTxt_PB.clear();
            scrnMsg.B.no(idx).mtrLbNm_PB.clear();
            scrnMsg.B.no(idx).minCopyVolCnt_PB.clear();
            scrnMsg.B.no(idx).maxCopyVolCnt_PB.clear();
            scrnMsg.B.no(idx).cpcAmtRate_PB.clear();
            scrnMsg.B.no(idx).minCpcAmtRate_PB.clear();
            scrnMsg.B.no(idx).maxCpcAmtRate_PB.clear();
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.B.no(idx).prcListBandCd_PB.clear();
            scrnMsg.B.no(idx).prcListBandDescTxt_PB.clear();
            // 2018/11/17 QC#29147 Mod End

        } else if (PRC_RATE_TP.BASE_ONLY.equals(scrnMsg.B.no(idx).prcRateTpCd_PB.getValue())) {
            scrnMsg.B.no(idx).mdlId_PB.clear();
            scrnMsg.B.no(idx).mdlNm_PB.clear();
            scrnMsg.B.no(idx).prcMtrPkgNm_PB.clear();
            scrnMsg.B.no(idx).mtrLbNm_PB.clear();
            scrnMsg.B.no(idx).minCopyVolCnt_PB.clear();
            scrnMsg.B.no(idx).maxCopyVolCnt_PB.clear();
            scrnMsg.B.no(idx).cpcAmtRate_PB.clear();
            scrnMsg.B.no(idx).minCpcAmtRate_PB.clear();
            scrnMsg.B.no(idx).maxCpcAmtRate_PB.clear();
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.B.no(idx).prcListBandCd_PB.clear();
            scrnMsg.B.no(idx).prcListBandDescTxt_PB.clear();
            // 2018/11/17 QC#29147 Mod End
        }

        //Mod Start 2017/02/23 QC#16011 
//        NMAL7010CommonLogic.prcListRateTypeCtrl(this, scrnMsg, idx);
        boolean updateAuthFlg = NMAL7010CommonLogic.updateAuthority(getUserProfileService());
        NMAL7010CommonLogic.prcListRateTypeCtrl(this, scrnMsg, idx, updateAuthFlg);
        // Mod End 2017/02/23 QC#16011
    }
}
