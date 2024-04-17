/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.MESSAGE_KIND_ERROR;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2016/09/28   Hitachi         T.Mizuki        Update          QC#13263
 * 2016/09/29   Hitachi         T.Mizuki        Update          QC#13270
 * 2017/02/23   Fujitsu         R.Nakamura      Update          QC#16011
 * 2017/02/23   Fujitsu         M.Ohno          Update          QC#20977
 * 2018/11/17   Fujitsu         N.Sugiura       Update          QC#29147
 *</pre>
 */
public class NMAL7010Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        // Mod Start 2017/02/23 QC#16011
//        NMAL7010CommonLogic.checkAllItemInput(this, scrnMsg);
        NMAL7010CommonLogic.checkAllItemInput(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/23 QC#16011
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // for error handling
        // Mod Start 2017/02/23 QC#16011
//        NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg);
        NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/23 QC#16011

        allCheckItem(scrnMsg);

        // NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg);
    }

    private void allCheckItem(NMAL7010BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcContrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);

        // Trx Drv
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_CA);

        // QC#2175
        for (int i = 0; i < scrnMsg.W.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.W.no(i).xxChkBox_SW);
            scrnMsg.addCheckItem(scrnMsg.W.no(i).prcCatgNm_SW);
            scrnMsg.addCheckItem(scrnMsg.W.no(i).effFromDt_SW);
            scrnMsg.addCheckItem(scrnMsg.W.no(i).effThruDt_SW);
            scrnMsg.addCheckItem(scrnMsg.W.no(i).subPrcPrtyNum_SW);
        }
        
        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X1);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X2);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X3);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).prcCustAudcCatgCd_X2);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).prcCustAudcCatgCd_X3);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).effFromDt_CX);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).effThruDt_CX);
        }
        for (int i = 0; i < scrnMsg.Y.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxScrItem30Txt_Y1);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxScrItem30Txt_Y2);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxRecNmTxt_Y1);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).xxRecNmTxt_Y2);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).effFromDt_TY);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).effThruDt_TY);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcQlfyTpCd_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcQlfyValTxt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcFmlaPk_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcListEquipPrcAmt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).pkgUomCd_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).minPrcAmt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcTermAot_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcTermUomCd_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mlyPmtAmt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).minLeasePmtAmt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_PA);
            // mod start 2016/09/29 CSA QC#13270
            scrnMsg.addCheckItem(scrnMsg.A.no(i).maxPrcAmt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).maxLeasePmtAmt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).quotRevAmt_PA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).custBidQty_PA);
            // mod end 2016/09/29 CSA QC#13270
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdlNm_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcMtrPkgNm_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcSvcPlnTpCd_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcSvcContrTpCd_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).minCopyVolCnt_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).maxCopyVolCnt_PB);
            // 2018/11/17 QC#29147 Mod Start
            // scrnMsg.addCheckItem(scrnMsg.B.no(i).prcListBandCd_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcListBandDescTxt_PB);
            // 2018/11/17 QC#29147 Mod End
            scrnMsg.addCheckItem(scrnMsg.B.no(i).baseAmt_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).minBaseAmt_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).maxBaseAmt_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).termFromMthAot_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).termThruMthAot_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_PB);

            scrnMsg.addCheckItem(scrnMsg.B.no(i).mtrLbNm_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).cpcAmtRate_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseCd_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcListMdseCd_PB);
            // mod start 2016/09/29 CSA QC#13270
            scrnMsg.addCheckItem(scrnMsg.B.no(i).minCpcAmtRate_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).maxCpcAmtRate_PB);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).quotRevAmt_PB);
            // mod end 2016/09/29 CSA QC#13270
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).mdlNm_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).prcMtrPkgNm_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).mdseCd_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).minCopyVolCnt_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).maxCopyVolCnt_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).termFromMthAot_PC);

            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).mtrLbNm_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).prcListBandDescTxt_PC); // 2018/11/17 QC#29147 Add
            // mod start 2016/09/29 CSA QC#13270
            scrnMsg.addCheckItem(scrnMsg.C.no(i).avgCopyVolCnt_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).baseAmt_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).minBaseAmt_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).maxBaseAmt_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).cpcAmtRate_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).minCpcAmtRate_PC);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).maxCpcAmtRate_PC);
            // mod end 2016/09/29 CSA QC#13270
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_PD);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).mdlNm_PD);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).mdseCd_PD);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).addlChrgPrcAmt_PD);

            scrnMsg.addCheckItem(scrnMsg.D.no(i).effFromDt_PD);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).effThruDt_PD);
            // mod start 2016/09/29 CSA QC#13270
            scrnMsg.addCheckItem(scrnMsg.D.no(i).addlChrgPrcAmt_PD);
            // mod end 2016/09/29 CSA QC#13270
        }
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.E.no(i).xxChkBox_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).mdlNm_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).prcMtrPkgNm_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).mdseCd_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).mtrLbCd_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).prcListBandDescTxt_PE); // 2018/11/17 QC#29147 Add
            scrnMsg.addCheckItem(scrnMsg.E.no(i).minCopyVolCnt_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).maxCopyVolCnt_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).minCpcAmtRate_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).termFromMthAot_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).termThruMthAot_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).cpcAmtRate_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).prcSvcZoneCd_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).splyAgmtPlnPk_PE);

            scrnMsg.addCheckItem(scrnMsg.E.no(i).mtrLbNm_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).effFromDt_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).effThruDt_PE);
            // mod start 2016/09/28 CSA QC#13263
            scrnMsg.addCheckItem(scrnMsg.E.no(i).splyBaseAmt_PE);
            // mod end 2016/09/28 CSA QC#13263
            // mod start 2016/09/29 CSA QC#13270
            scrnMsg.addCheckItem(scrnMsg.E.no(i).totBaseAmt_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).minCpcAmtRate_PE);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).maxCpcAmtRate_PE);
            // mod end 2016/09/29 CSA QC#13270
        }
        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.F.no(i).xxChkBox_PF);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).mdlNm_PF);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).dsAcctNm_PF);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).minTotFinAmt_PF);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).termFromMthAot_PF);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).leaseRate_PF);

            scrnMsg.addCheckItem(scrnMsg.F.no(i).effFromDt_PF);
            scrnMsg.addCheckItem(scrnMsg.F.no(i).effThruDt_PF);
            // mod start 2016/09/29 CSA QC#13270
            scrnMsg.addCheckItem(scrnMsg.F.no(i).maxTotFinAmt_PF);
            // mod end 2016/09/29 CSA QC#13270
        }
        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.G.no(i).xxChkBox_PG);
            scrnMsg.addCheckItem(scrnMsg.G.no(i).rtrnFeeAmt_PG);
            scrnMsg.addCheckItem(scrnMsg.G.no(i).effFromDt_PG);
            scrnMsg.addCheckItem(scrnMsg.G.no(i).effThruDt_PG);
            // mod start 2016/09/29 CSA QC#13270
            scrnMsg.addCheckItem(scrnMsg.G.no(i).dstMileAmt_PG);
            // mod end 2016/09/29 CSA QC#13270
        }
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.H.no(i).xxChkBox_PH);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).tiAmt_PH);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).fromMtrCopyVolCnt_PH);

            scrnMsg.addCheckItem(scrnMsg.H.no(i).effFromDt_PH);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).effThruDt_PH);
            // mod start 2016/09/29 CSA QC#13270
            scrnMsg.addCheckItem(scrnMsg.H.no(i).thruMtrCopyVolCnt_PH);
            // mod end 2016/09/29 CSA QC#13270
            // add start 2017/09/27 CSA QC#20977
            scrnMsg.addCheckItem(scrnMsg.H.no(i).mdlNm_PH);
            // add end 2017/09/27 CSA QC#20977
        }
        for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.I.no(i).xxChkBox_PI);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).prcQlfyTpCd_PI);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).prcQlfyValTxt_PI);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).qtyDiscPrcAmt_PI);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).pkgUomCd_PI);

            scrnMsg.addCheckItem(scrnMsg.I.no(i).effFromDt_PI);
            scrnMsg.addCheckItem(scrnMsg.I.no(i).effThruDt_PI);
        }

        for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.J.no(i).xxChkBox_PJ);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).qtyDiscDtlQty_PJ);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).pkgUomCd_PJ);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).prcBreakAmt_PJ);
        }
        scrnMsg.putErrorScreen();

    }
}
