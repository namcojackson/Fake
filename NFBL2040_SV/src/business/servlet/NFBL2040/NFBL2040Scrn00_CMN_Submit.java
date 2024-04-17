/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL2040.NFBL2040CMsg;
import business.parts.NFBCommonBusiness;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          QC#11331
 * 2016/07/19   Hitachi         T.Tsuchida      Update          QC#12015
 * 2016/07/20   Hitachi         T.Tsuchida      Update          QC#12053
 * 2016/07/27   Hitachi         T.Tsuchida      Update          QC#12221
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/08/01   Hitachi         T.Tsuchida      Update          QC#12712
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12115
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12714
 * 2016/08/05   Hitachi         Y.Tsuchimoto    Update          QC#12952
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12968
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12988
 * 2016/08/09   Hitachi         Y.Tsuchimoto    Update          QC#12195
 * 2016/08/09   Hitachi         Y.Tsuchimoto    Update          QC#12198
 * 2016/08/10   Hitachi         Y.Tsuchimoto    Update          QC#12948
 * 2016/08/10   Hitachi         Y.Tsuchimoto    Update          QC#12970
 * 2016/09/01   Hitachi         Y.Tsuchimoto    Update          QC#12010
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Update          QC#13333
 * 2016/09/15   Hitachi         Y.Tsuchimoto    Update          QC#13156
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13550
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13157
 * 2016/09/26   Fujitsu         W.Honda         Update          Unit Test#201
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#15036
 * 2016/10/26   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2016/11/15   Hitachi         K.Kasai         Update          QC#15904
 * 2017/01/12   Hitachi         E.Kameishi      Update          QC#16950
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16948
 * 2017/01/24   Hitachi         E.Kameishi      Update          QC#16948
 * 2017/10/20   CITS            T.Tokutomi      Update          QC#21653
 * 2017/12/19   CITS            T.Tokutomi      Update          QC#14858-Sol#060
 * 2017/12/26   Hitachi         Y.Takeno        Update          QC#23022
 * 2018/01/05   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/01/23   CITS            T.Gotoda        Update          QC#21694, 21696
 * 2018/02/28   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/06   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/07/27   CITS            T.Tokutomi      Update          QC#27029
 * 2018/09/04   Hitachi         Y.Takeno        Update          QC#28039
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 * 2020/07/02   CITS            R.Kurahashi     Update          QC#56696
 * 2020/07/16   CITS            R.Kurahashi     Update          QC#56696-1
 * 2020/02/08   Hitachi         R.Onozuka       Update          QC#59613
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040Scrn00_CMN_Submit extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // Check Search Condition.
        // String apInvCatgCd_S = NFBCommonBusiness.chkNull(scrnMsg.apInvCatgCd_S.getValue());
        String poNum = NFBCommonBusiness.chkNull(scrnMsg.poNum.getValue());
        String delyOrdNum_H = NFBCommonBusiness.chkNull(scrnMsg.delyOrdNum_H.getValue());
        // String apInvCatgCd_HD = NFBCommonBusiness.chkNull(scrnMsg.apInvCatgCd_HD.getValue());
        String poNum_HD = NFBCommonBusiness.chkNull(scrnMsg.poNum_HD.getValue());
        String delyOrdNum_HD = NFBCommonBusiness.chkNull(scrnMsg.delyOrdNum_HD.getValue());
        // START QC#25902,QC#25190,QC#25141
        String vndRtrnNum = NFBCommonBusiness.chkNull(scrnMsg.vndRtrnNum.getValue());
        String vndRtrnNum_HD = NFBCommonBusiness.chkNull(scrnMsg.vndRtrnNum_HD.getValue());
        // END QC#25902,QC#25190,QC#25141

        
        // START 2020/03/23 [QC#53413, MOD]
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_CR.getValue())) {
            boolean errFlg = true;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxLineTpCd_A1)) {
                    if (XX_LINE_TP_CD_VALID.equals(scrnMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                        errFlg = false;
                        break;
                    }
                }
            }
            if (errFlg) {
                scrnMsg.setMessageInfo(NFBM0044E, new String[] {"Lines Data"});
                throw new EZDFieldErrorException();
            }
        }
        // END   2020/03/23 [QC#53413, MOD]

//        if (!apInvCatgCd_S.equals(apInvCatgCd_HD)) {
//            scrnMsg.apInvCatgCd_S.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.apInvCatgCd_S.getNameForMessage() });
//        }

        // START 2018/03/06 [QC#23505, MOD]
        // if (!ZYPCommonFunc.hasValue(scrnMsg.cmApInvTpCd_S)) {
        //     scrnMsg.cmApInvTpCd_S.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.cmApInvTpCd_S.getNameForMessage() });
        // }
        if (!NFBL2040CommonLogic.isCorrectableInvoice(scrnMsg) || 
                !NFBL2040CommonLogic.isCorrectedInvoice(scrnMsg) || !NFBL2040CommonLogic.isUnsubmittedCorrection(scrnMsg)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.cmApInvTpCd_S)) {
                scrnMsg.cmApInvTpCd_S.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.cmApInvTpCd_S.getNameForMessage() });
            }
        }
        // END   2018/03/06 [QC#23505, MOD]

        // START 2017/01/13 E.Kameishi [QC#16948,MOD]
        String acctInvSts = scrnMsg.acctInvStsCd.getValue();
        if (!acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
            if (!poNum.equals(poNum_HD)) {
                scrnMsg.poNum.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.poNum.getNameForMessage() });
            }
            if (!delyOrdNum_H.equals(delyOrdNum_HD)) {
                scrnMsg.delyOrdNum_H.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.delyOrdNum_H.getNameForMessage() });
            }
            // START QC#25902,QC#25190,QC#25141
            if (!vndRtrnNum.equals(vndRtrnNum_HD)) {
                scrnMsg.vndRtrnNum.setErrorInfo(1, NFBM0052E, new String[] {scrnMsg.vndRtrnNum.getNameForMessage() });
            }
            // END QC#25902,QC#25190,QC#25141
        }
        // END 2017/01/13 E.Kameishi [QC#16948,MOD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.apVndCd)) {
            scrnMsg.apVndCd.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.apVndCd.getNameForMessage() });
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum)) {
            scrnMsg.apVndInvNum.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.apVndInvNum.getNameForMessage() });
        }
        // START 2016/08/05 T.Tsuchida [QC#12115,MOD]
        //if (!ZYPCommonFunc.hasValue(scrnMsg.poOrdNum)) {
        //    scrnMsg.poOrdNum.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.poOrdNum.getNameForMessage() });
        //}
        // START 2020/03/23 [QC#53413, DEL]
//        if (0 != scrnMsg.A.getValidCount()) {
//            List<String> poNumList = new ArrayList<String>();
//            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                if (NFBL2040CommonLogic.chkMtPo(scrnMsg, i) ) {
//                    String poNumA = scrnMsg.A.no(i).poNum_A1.getValue();
//                    if (!poNumList.contains(poNumA)) {
//                        poNumList.add(poNumA);
//                    }
//                }
//            }
//            if (poNumList.size() > 1) {
//                // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
//                if (!ZYPCommonFunc.hasValue(scrnMsg.poNum_HT) && CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
//                    scrnMsg.poNum_HT.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.poNum_HT.getNameForMessage() });
//                }
//                // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
//            }
//        }
        // END   2020/03/23 [QC#53413, DEL]
        // END 2016/08/05 T.Tsuchida [QC#12115,MOD]
//        if (!ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_DH)) {
//            scrnMsg.delyOrdNum_DH.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.delyOrdNum_DH.getNameForMessage() });
//        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_DH)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.delyOrdNum_DH, NONE);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.invDt)) {
            scrnMsg.invDt.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.invDt.getNameForMessage() });
        }
        // START 2016/08/05 T.Tsuchida [QC#12714,ADD]
        BigDecimal invAmt = NFBCommonBusiness.chkNull(scrnMsg.invAmt.getValue());
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())
                && BigDecimal.ZERO.compareTo(invAmt) <= 0) {
            scrnMsg.invAmt.setErrorInfo(1, NFBM0229E);
        }
        // END 2016/08/05 T.Tsuchida [QC#12714,ADD]
        // START 2016/08/05 T.Tsuchida [QC#12988,ADD]
        // START 2020/07/02 R.Kurahashi [QC#56696,DEL]
        //if (!CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())
        //        && BigDecimal.ZERO.compareTo(invAmt) >= 0) {
        //    scrnMsg.invAmt.setErrorInfo(1, NFBM0230E);
        //}
        // END 2020/07/02 R.Kurahashi [QC#56696,DEL]
        // END 2016/08/05 T.Tsuchida [QC#12988,ADD]
        // START 2017/01/13 E.Kameishi [QC#16948,ADD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldCd_H1.getValue())) {
                    scrnMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NLAM0047E, new String[] {"Hold Name" });
                    scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRsnCd_H1.getValue())) {
                    scrnMsg.H.no(i).pmtHldRsnCd_H1.setErrorInfo(1, NLAM0047E, new String[] {"Hold Reason" });
                    scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRsnCd_H1);
                }
            }
        }
        if (acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
            NFBL2040CommonLogic.checkHoldRelease(scrnMsg);
            scrnMsg.putErrorScreen();
        }
        // END 2017/01/13 E.Kameishi [QC#16948,ADD]
        scrnMsg.addCheckItem(scrnMsg.cmApInvTpCd_S);
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_H);
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.poNum_HT);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_DH);
        // Header Tab
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        scrnMsg.addCheckItem(scrnMsg.invDt);
        scrnMsg.addCheckItem(scrnMsg.apPmtChkNum);
        // QC#21653 Delete
        // scrnMsg.addCheckItem(scrnMsg.cmInvMatchCd_S);
        scrnMsg.addCheckItem(scrnMsg.invAmt);
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermCd);
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt);
        scrnMsg.addCheckItem(scrnMsg.vndPmtMethCd);
        scrnMsg.addCheckItem(scrnMsg.vndPmtMethDescTxt);
        scrnMsg.addCheckItem(scrnMsg.poApvlDt);
        scrnMsg.addCheckItem(scrnMsg.entPoDtlDealNetAmt_TO);
        scrnMsg.addCheckItem(scrnMsg.invHdrDescTxt);
        scrnMsg.addCheckItem(scrnMsg.acctInvStsDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.pmtDt);
        scrnMsg.addCheckItem(scrnMsg.hdrPmtAmt);
        scrnMsg.addCheckItem(scrnMsg.termNetDueDt);
        scrnMsg.addCheckItem(scrnMsg.acOcTotDiscAmt);
        scrnMsg.addCheckItem(scrnMsg.apInvCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.acctDt);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum_HH);
        scrnMsg.addCheckItem(scrnMsg.invAmt_HH);
        // HOLDS Tab
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {

            // START 2016/09/26 W.Honda [Unit Test#201,ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldCd_H1)
                    && PMT_HLD.THEREFORE.equals(scrnMsg.H.no(i).pmtHldCd_H1.getValue())) {
                scrnMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0253E);
                scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
            }
            // END   2016/09/26 W.Honda [Unit Test#201,ADD]
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelRsnCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelCmntTxt_H1);
        }
        // LINES Tab
        // START 2016/07/19 T.Tsuchida [QC#12015,MOD]
        boolean hasErrLinesTab = false;
        // END 2016/07/19 T.Tsuchida [QC#12015,MOD]
        // START 2017/01/26 E.Kameishi [QC#17090,ADD]
        int noneMdseCdNum = 1;
        // END 2017/01/26 E.Kameishi [QC#17090,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2016/07/19 T.Tsuchida [QC#12015,MOD]
            //if (scrnMsg.A.no(i).apLineTpCd_A1.isClear()) {
            //    scrnMsg.A.no(i).apLineTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Line Type" });
            //}
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1)) {
                scrnMsg.A.no(i).apLineTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Line Type" });
                hasErrLinesTab = true;
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxInvAmt_A1)) {
                scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Line Amount" });
                hasErrLinesTab = true;
            }

            // START 2016/09/14 Y.Tsuchimoto [QC#13333,MOD]
            // QC#14858-Sol#060 Update. Text item.
            if (AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxCntnrFlg_A1.getValue())
                    && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1)) {
                // Not Text Item required Item code.
                scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NFBM0255E, new String[] {"Line Type", "Item", "Item" });
                hasErrLinesTab = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && !AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                // START 2017/12/26 [QC#23022, MOD]
                if (!AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    // START 2017/01/26 E.Kameishi [QC#17090,MOD]
                    // START 2018/03/09 J.Kim [QC#24636,ADD]
                    // ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).mdseCd_A1, NONE + String.format(NONE_MDSE_CD_FORMAT, noneMdseCdNum));
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxMdseCd_A1, NONE + String.format(NONE_MDSE_CD_FORMAT, noneMdseCdNum));
                    // END 2018/03/091 J.Kim [QC#24636,ADD]
                    noneMdseCdNum++;
                    // END 2017/01/26 E.Kameishi [QC#17090,MOD]
                }
                // END 2017/12/26 [QC#23022, MOD]
            }
            // END   2016/09/14 Y.Tsuchimoto [QC#13333,MOD]
            // END 2016/07/19 T.Tsuchida [QC#12015,MOD]
            // START 2016/09/01 Y.Tsuchimoto [QC#12010,ADD]
            if (!scrnMsg.A.no(i).xxCmntTxt_A1.isInputProtected() && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxCmntTxt_A1)) {
                scrnMsg.A.no(i).xxCmntTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Charge Account" });
                hasErrLinesTab = true;
            }
            // END   2016/09/01 Y.Tsuchimoto [QC#12010,ADD]

            scrnMsg.addCheckItem(scrnMsg.A.no(i).apLineTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseDescShortTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxInvAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).vndUomCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxCmntTxt_A1);
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apAcctDescTxt_A1);
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invRcvQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apRejQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apBillQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsContrNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).custDlrCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).csmpInvNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxInstlFullAddr_A1);
        }

        // START 2018/01/23 [QC#21694, 21696 MOD]
        BigDecimal lineTotAmt = BigDecimal.ZERO;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // 2018/02/28 START [QC#23505, ADD]
            // START QC#25902,QC#25190,QC#25141
            if (XX_LINE_TP_CD_INVALID.equals(scrnMsg.A.no(i).xxLineTpCd_A1.getValue()) //
                    || XX_LINE_TP_CD_CREDIT.equals(scrnMsg.A.no(i).xxLineTpCd_A1.getValue()) //
                    || "".equals(scrnMsg.A.no(i).xxLineTpCd_A1.getValue()) //
            ) {
                continue;
            }
            // END QC#25902,QC#25190,QC#25141
            // 2018/02/28 END   [QC#23505, ADD]

            BigDecimal lineAmt = scrnMsg.A.no(i).xxInvAmt_A1.getValue();
            BigDecimal apBillQty = scrnMsg.A.no(i).apBillQty_A1.getValue();
            BigDecimal dealGrsUnitPrcAmt = scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue();
            
            // START 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                     && !ZYPCommonFunc.hasValue(apBillQty)) {
                apBillQty = BigDecimal.ZERO;
                scrnMsg.A.no(i).apBillQty_A1.setValue(apBillQty);
            }
            // END 2020/07/16 R.Kurahashi [QC#56696-1,ADD]

            if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {

                if (AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (apBillQty == null || BigDecimal.ZERO.compareTo(apBillQty) < 0) {
                        scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0231E);
                        hasErrLinesTab = true;
                    }

                    if (dealGrsUnitPrcAmt == null || BigDecimal.ZERO.compareTo(dealGrsUnitPrcAmt) > 0) {
                        scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setErrorInfo(1, NFBM0279E);
                        hasErrLinesTab = true;
                    }
                    // START 2020/07/02 R.Kurahashi [QC#56696,DEL]
                    //if (lineAmt == null
                    //        || dealGrsUnitPrcAmt == null
                    //        || apBillQty == null
                    //        || lineAmt.compareTo(dealGrsUnitPrcAmt.multiply(apBillQty)) != 0) {

                    //    scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0225E);
                    //    scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0225E);
                    //    hasErrLinesTab = true;
                    //}
                    // END 2020/07/02 R.Kurahashi [QC#56696,DEL]

                    // START 2018/09/04 [QC#28039, DEL]
                    // QC#27029 Add check
                    // if (ZYPCommonFunc.hasValue(scrnMsg.poNum)) {
                    //     BigDecimal invoicedQty = scrnMsg.A.no(i).xxInvQty_A1.getValue();
                    //     BigDecimal invoiceQty = scrnMsg.A.no(i).apBillQty_A1.getValue();
                    //     if (invoicedQty.compareTo(invoiceQty.abs()) < 0) {
                    //         // NFBM0284E : Please enter [@] between
                    //         // [@]
                    //         // and [@].
                    //         scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, "NFBM0284E", new String[] {"invoice Qty", "-1", invoicedQty.negate().toPlainString() });
                    //     }
                    // }
                    // END   2018/09/04 [QC#28039, DEL]

                } else if (AP_LINE_TP.TAX.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                        || AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (lineAmt == null || BigDecimal.ZERO.compareTo(lineAmt) <= 0) {
                        scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0278E);
                        hasErrLinesTab = true;
                    }
                }

            } else if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {

                if (AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (apBillQty == null
                            || BigDecimal.ZERO.compareTo(apBillQty) > 0) {

                        scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0273E);
                        hasErrLinesTab = true;
                    }

                    if (dealGrsUnitPrcAmt == null || BigDecimal.ZERO.compareTo(dealGrsUnitPrcAmt) > 0) {
                        scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setErrorInfo(1, NFBM0279E);
                        hasErrLinesTab = true;
                    }
                    // START 2020/07/02 R.Kurahashi [QC#56696,DEL]
                    //if (lineAmt == null
                    //        || dealGrsUnitPrcAmt == null
                    //        || apBillQty == null
                    //        || lineAmt.compareTo(dealGrsUnitPrcAmt.multiply(apBillQty)) != 0) {

                    //    scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0225E); 
                    //    scrnMsg.A.no(i).apBillQty_A1.setErrorInfo(1, NFBM0225E);
                    //    hasErrLinesTab = true;
                    //}
                    // END 2020/07/02 R.Kurahashi [QC#56696,DEL]
                } else if (AP_LINE_TP.TAX.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                        || AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    if (lineAmt == null || BigDecimal.ZERO.compareTo(lineAmt) >= 0) {
                        scrnMsg.A.no(i).xxInvAmt_A1.setErrorInfo(1, NFBM0274E);
                        hasErrLinesTab = true;
                    }
                }
            }

            // START 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
                    && !hasErrLinesTab ) {
                lineAmt = dealGrsUnitPrcAmt.multiply(apBillQty);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxInvAmt_A1, lineAmt);
            }
            // END 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
            
            if (lineAmt != null) {
                lineTotAmt = lineTotAmt.add(lineAmt);
            }
        }
        // END  2018/01/23 [QC#21694, 21696 MOD]

        // START 2016/07/07 [QC#11331,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotPrcAmt_F, lineTotAmt);
        scrnMsg.addCheckItem(scrnMsg.xxTotPrcAmt_F);
        // END   2016/07/07 [QC#11331,MOD]
        // START 2022/02/08 R.Onozuka [QC#59613, ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if(PO_LINE_STS.CANCELLED.equals(scrnMsg.A.no(i).poLineStsCd_A1.getValue())
                    && !BigDecimal.ZERO.equals(scrnMsg.A.no(i).apBillQty_A1.getValue())){
                scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NFBM0294E);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                hasErrLinesTab = true;
            }
        }
        // END 2022/02/08 R.Onozuka [QC#59613, ADD]
        // START 2016/07/19 T.Tsuchida [QC#12015,MOD]
        if (hasErrLinesTab) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_02, TAB_LINES);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
            NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
            scrnMsg.putErrorScreen();
        }
        // END 2016/07/19 T.Tsuchida [QC#12015,MOD]

        // DISTRIBUTIONS Tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).invDt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).jrnlFuncDrAmt_D1);
            scrnMsg.addCheckItem(scrnMsg.D.no(i).jrnlFuncCrAmt_D1);
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.addCheckItem(scrnMsg.D.no(i).apAcctDescTxt_D1);
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxCmntTxt_D1);
        }
        scrnMsg.putErrorScreen();

        // START 2016/08/05 T.Tsuchida [QC#12714,DEL]
        //BigDecimal invAmt = scrnMsg.invAmt.getValue();
        //if (!ZYPCommonFunc.hasValue(invAmt)) {
        //    invAmt = BigDecimal.ZERO;
        //}
        // END 2016/08/05 T.Tsuchida [QC#12714,DEL]
        // START 2016/08/05 T.Tsuchida [QC#12968,ADD]
        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NFBM0044E, new String[] {"Lines Data"});
            throw new EZDFieldErrorException();
        }
        // END 2016/08/05 T.Tsuchida [QC#12968,ADD]
        // START 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
        // START 2020/07/02 R.Kurahashi [QC#56696,DEL]
        if (invAmt.compareTo(lineTotAmt) != 0) {
            scrnMsg.setMessageInfo(NFBM0067E);
            throw new EZDFieldErrorException();
        }
        // END 2020/07/02 R.Kurahashi [QC#56696,DEL]
        // END 2020/07/16 R.Kurahashi [QC#56696-1,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NONE.equals(scrnMsg.delyOrdNum_DH.getValue())) {
            scrnMsg.delyOrdNum_DH.clear();
        }
        // START 2016/07/27 T.Tsuchida [QC#12221,ADD]
        scrnMsg.addCheckItem(scrnMsg.vndPmtTermDescTxt);
        // END 2016/07/27 T.Tsuchida [QC#12221,ADD]
        // START 2016/08/09 Y.Tsuchimoto [QC#12195,ADD]
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.apVndInvNum);
        // END   2016/08/09 Y.Tsuchimoto [QC#12195,ADD]
        // START 2016/08/10 Y.Tsuchimoto [QC#12948,ADD]
        scrnMsg.addCheckItem(scrnMsg.dplyVndNm);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.poNum_HT);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.addCheckItem(scrnMsg.apVndCd);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_DH);
        // END   2016/08/10 Y.Tsuchimoto [QC#12948,ADD]
        // START 2016/08/01 T.Tsuchida [QC#12712,ADD]
        scrnMsg.addCheckItem(scrnMsg.invDt);
        // END 2016/08/01 T.Tsuchida [QC#12712,ADD]
        // START 2016/07/20 T.Tsuchida [QC#12053,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxCmntTxt_A1);
            // START 2016/08/09 Y.Tsuchimoto [QC#12198,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apBillQty_A1);
            // END   2016/08/09 Y.Tsuchimoto [QC#12198,ADD]
            // START 2017/12/26 [QC#23022, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apLineTpCd_A1);
            // END   2017/12/26 [QC#23022, ADD]
            // add start 2022/02/15 QC#57090
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            // add end 2022/02/15 QC#57090
        }
        // START 2017/01/12 E.Kameishi [QC#16950,ADD]
        scrnMsg.addCheckItem(scrnMsg.termNetDueDt);
        // END 2017/01/12 E.Kameishi [QC#16950,ADD]
        // START 2017/01/13 E.Kameishi [QC#16948,ADD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRsnCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).xxChkBox_H1);
        }
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.offHoldReleaseCheck(scrnMsg);
        // END   2020/03/16 [QC#55993, ADD]
        // END 2017/01/13 E.Kameishi [QC#16948,ADD]
        scrnMsg.putErrorScreen();
        // END 2016/07/20 T.Tsuchida [QC#12053,ADD]

        // START 2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            // Common
            NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
            // START 2020/03/23 [QC#53413, MOD]
            //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
            NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, false);
            // END   2020/03/23 [QC#53413, MOD]
        } else {
            /** Initialize button control */ 
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }
        // END   2016/09/15 Y.Tsuchimoto [QC#13156,MOD]

        // START 2018/03/09 J.Kim [QC#24636,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFBL2040_ABMsg abMsg = scrnMsg.A.no(i);
            if (!AP_LINE_TP.ITEM.equals(abMsg.apLineTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(abMsg.xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
                scrnMsg.A.no(i).mdseCd_A1.clear();
            }
        }
        // END 2018/03/09 J.Kim [QC#24636,ADD]

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        if (TAB_HOLDS.equals(scrnMsg.xxDplyTab_01.getValue())) {
            NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);
        }
        if (TAB_DISTRIBUTIONS.equals(scrnMsg.xxDplyTab_02.getValue())) {
            NFBL2040CommonLogic.clearRowsBG_D(scrnMsg);
        }
        /** Initialize tab position */
        NFBL2040CommonLogic.initTabPosition(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
        // START 2018/01/05 [QC#22143, MOD]
        // START 2016/08/01 T.Tsuchida [QC#12712,ADD]
        // if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
        //     // START 2016/08/05 Y.Tsuchimoto [QC#12952,ADD]
        //     NFBL2040CommonLogic.setButtonNotFromOthScrForAfterSubmit(this, scrnMsg);
        //     // END   2016/08/05 Y.Tsuchimoto [QC#12952,ADD]
        //
        //     // START 2016/08/10 Y.Tsuchimoto [QC#12970,ADD]
        //     NFBL2040CommonLogic.setInputProtectedForAfterSubmit(scrnMsg);
        //     // END   2016/08/10 Y.Tsuchimoto [QC#12970,ADD]
        //
        //     scrnMsg.setMessageInfo(ZZM8100I);
        // }
        // END 2016/08/01 T.Tsuchida [QC#12712,ADD]
        if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            scrnMsg.setMessageInfo(ZZM8100I);
        }
        // END 2018/01/05 [QC#22143, MOD]
        // START 2017/01/13 E.Kameishi [QC#16948,ADD]
        String acctInvSts = scrnMsg.acctInvStsCd.getValue();
        if (acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
            this.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, true);
            this.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, true);
            // START 2018/01/05 [QC#22143, DEL]
            // boolean isProtected = true;
            // for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            //     if (!scrnMsg.H.no(i).xxChkBox_H2.isInputProtected()) {
            //         isProtected = false;
            //     }
            // }
            // if (isProtected) {
            //     this.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
            // }
            // END   2018/01/05 [QC#22143, DEL]
        }
        // END 2017/01/13 E.Kameishi [QC#16948,ADD]
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
        // START 2020/03/23 [QC#53413, ADD]
        // mod start 2022/02/15 QC#57090
        //NFBL2040CommonLogic.delMultiPoScrnData(scrnMsg);
        NFBL2040CommonLogic.clearMultiPoOrMultiVndRtrnData(scrnMsg);
        // mod end 2022/02/15 QC#57090
        // END   2020/03/23 [QC#53413, ADD]
    }
}
