/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL2040.common;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDFieldErrorException;
import parts.dbcommon.EZDTBLAccessor;
import parts.servletcommon.EZDCommonHandler;
import business.db.COA_ACCTTMsg;
import business.servlet.NFBL2040.NFBL2040BMsg;
import business.servlet.NFBL2040.NFBL2040_ABMsg;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/05/23   Hitachi         T.Tsuchida      Update          QC#4492
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          QC#11331
 * 2016/07/22   Hitachi         Y.Tsuchimoto    Update          QC#12008
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#11999
 * 2016/07/27   Hitachi         T.Tsuchida      Update          QC#12002
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/08/01   Hitachi         T.Tsuchida      Update          QC#12009
 * 2016/08/02   Hitachi         Y.Tsuchimoto    Update          QC#12011
 * 2016/08/02   Hitachi         T.Tsuchida      Update          QC#12040
 * 2016/08/05   Hitachi         Y.Tsuchimoto    Update          QC#12952
 * 2016/08/05   Hitachi         Y.Tsuchimoto    Update          QC#12201
 * 2016/08/09   Hitachi         Y.Tsuchimoto    Update          QC#12198
 * 2016/08/10   Hitachi         Y.Tsuchimoto    Update          QC#12970
 * 2016/08/24   Hitachi         Y.Tsuchimoto    Update          QC#13693
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Update          QC#12043
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Update          QC#13333
 * 2016/09/15   Hitachi         Y.Tsuchimoto    Update          QC#13156
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13550
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#12040
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13157
 * 2016/09/26   Fujitsu         W.Honda         Update          Unit Test#201
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/09/28   Hitachi         Y.Tsuchimoto    Update          QC#14797
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#15036
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#11613
 * 2016/10/26   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2016/11/14   Hitachi         Y.Tsuchimoto    Update          QC#15773
 * 2016/11/15   Hitachi         K.Kasai         Update          QC#15904
 * 2016/12/12   Hitachi         E.Kameishi      Update          QC#15905
 * 2017/01/05   Fujitsu         H.Ikeda         Update          QC#12865
 * 2017/01/12   Hitachi         E.Kameishi      Update          QC#16950
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16948
 * 2017/02/23   Hitachi         E.Kameishi      Update          QC#12988
 * 2017/10/11   CITS            T.Tokutomi      Update          QC#21640
 * 2017/10/20   CITS            T.Tokutomi      Update          QC#21653
 * 2017/11/13   CITS            T.Wada          Update          QC#22348
 * 2017/11/13   CITS            T.Wada          Update          QC#22289
 * 2017/11/13   CITS            T.Wada          Update          QC#22349
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2017/12/27   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/02/02   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/02/26   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/06   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#22755
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#24275
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#24140
 * 2018/03/07   Hitachi         J.Kim           Update          QC#24636
 * 2018/03/16   Hitachi         Y.Takeno        Update          QC#24089
 * 2018/03/20   Hitachi         Y.Takeno        Update          QC#24089
 * 2018/03/23   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/03/27   Hitachi         Y.Takeno        Update          QC#24277
 * 2018/04/04   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/04/06   Hitachi         Y.Takeno        Update          QC#25086
 * 2018/04/10   CITS            K.Ogino         Update          QC#24985
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/06/26   CITS            S.Katsuma       Update          QC#24617,26566
 * 2018/07/12   CITS            T.Tokutomi      Update          QC#27025
 * 2018/07/26   CITS            T.Tokutomi      Update          QC#27029
 * 2018/07/30   Hitachi         Y.Takeno        Update          QC#27025-1
 * 2018/08/03   Hitachi         Y.Takeno        Update          QC#27563
 * 2018/08/14   CITS            T.hakodate      Update          QC#27494
 * 2018/08/23   Hitachi         Y.Takeno        Update          QC#27247-1
 * 2018/09/26   Hitachi         Y.Takeno        Update          QC#28099
 * 2018/10/03   Hitachi         Y.Takeno        Update          QC#28099-1
 * 2018/11/20   Hitachi         Y.Takeno        Update          QC#28660
 * 2018/11/29   Hitachi         Y.Takeno        Update          QC#28904
 * 2019/04/08   Hitachi         Y.Takeno        Update          QC#31071
 * 2019/04/17   Hitachi         Y.Takeno        Update          QC#31071
 * 2019/09/02   Hitachi         Y.Takeno        Update          QC#52876
 * 2019/11/02   Fujitsu         Y.Matsui        Update          QC#54439
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 * 2020/07/02   CITS            R.Kurahashi     Update          QC#56696
 * 2020/04/22   CITS            R.Azucena       Update          QC#56829
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * 2023/01/17   Hitachi         S.Nakatani      Update          QC#60812
 * </pre>
 */
public class NFBL2040CommonLogic implements NFBL2040Constant {

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    @SuppressWarnings("unchecked")
    public static void initControl(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {

        List funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        // From other screen
        boolean fromOtherScreen = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum_OT)) {
            fromOtherScreen = true;
        }

        boolean protect = true;
        boolean ckFlg = true;
        // Start 2017/01/05 H.Ikeda [QC#12865,MOD]
        if (funcList.contains(FUNC_ID_NFBL2040T010) && funcList.contains(FUNC_ID_NFBL2040T020)) {
            protect = false;
        } else if (funcList.contains(FUNC_ID_NFBL2040T010)) {
            //protect = false;
            protect = true;
            ckFlg = false;
        } else if (funcList.contains(FUNC_ID_NFBL2040T020)) {
            //protect = true;
            protect = false;
        }
        if (ckFlg) {
            if (ZYPCommonFunc.hasValue(scrnMsg.poStsCd_HD.getValue())
                    &&  PO_HDR_STS.CLOSED.equals(scrnMsg.poStsCd_HD.getValue())) {
                        //protect = true;
                        protect = false;
             }
            if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd.getValue())) {
                int intAcctInvStsCd = Integer.parseInt(scrnMsg.acctInvStsCd.getValue());
                int intAcctInvStsCdAuthorized = Integer.parseInt(ACCT_INV_STS.AUTHORIZED);
                if (intAcctInvStsCd >= intAcctInvStsCdAuthorized) {
                    //protect = true;
                    protect = false;
                }
            }
        }
        if (fromOtherScreen) {
            if (protect) {
                setInputProtectedFromOthScrForRef(scrnMsg);
                setButtonFromOthScrForRef(handler, scrnMsg);
            } else {
                // START 2018/02/27 [QC#23505, MOD]
                if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd.getValue())
                    && isCorrectableInvoice(scrnMsg) && isCorrectedInvoice(scrnMsg) && isUnsubmittedCorrection(scrnMsg)) {
                    setInputProtectedForCorrection(scrnMsg);
                    setButtonForCorrection(handler, scrnMsg);
                // START 2018/01/05 [QC#22143, MOD]
                // START 2018/07/30 [QC#27025-1, MOD]
                // } else if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd.getValue()) && !ACCT_INV_STS.ENTERED.equals(scrnMsg.acctInvStsCd.getValue())) {
                //     setInputProtectedFromOthScrForEdit(scrnMsg);
                //     setButtonFromOthScrForEdit(handler, scrnMsg);
                } else if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd.getValue()) 
                        && !ACCT_INV_STS.ENTERED.equals(scrnMsg.acctInvStsCd.getValue()) && !ACCT_INV_STS.AUTHORIZED.equals(scrnMsg.acctInvStsCd.getValue())) {
                    setInputProtectedFromOthScrForEdit(scrnMsg);
                    setButtonFromOthScrForEdit(handler, scrnMsg);
                // END   2018/07/30 [QC#27025-1, MOD]
                    
                    // Start 2023/1/17 S.Nakatani [QC#60812, ADD]
                    // S21UserProfileService Instance
                    S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
                    // Global Company Code
                    String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
                    if (isInvNumUpdatable(GLBL_CMPY_CD, scrnMsg.acctInvStsCd.getValue(), scrnMsg.apInvCatgCd.getValue())){
                        scrnMsg.apVndInvNum.setInputProtected(false);
                        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
                    }
                    // End 2023/1/17 S.Nakatani [QC#60812, ADD]
                } else {
                    setInputProtectedFromOthScrForEnteredEdit(scrnMsg);
                    setButtonFromOthScrForEnteredEdit(handler, scrnMsg);
                }
                // END   2018/01/05 [QC#22143, MOD]
                // END   2018/02/27 [QC#23505, MOD]
            }
        } else {
            if (protect) {
                setInputProtectedNotFromOthScrForRef(scrnMsg);
                setButtonNotFromOthScrForRef(handler, scrnMsg);
            } else {
                setInputProtectedNotFromOthScrForEdit(scrnMsg);
                setButtonNotFromOthScrForEdit(handler, scrnMsg);
            }
        }
         // End   2017/01/05 H.Ikeda [QC#12865,MOD]
        initAppFracDigit(scrnMsg);
    }

    /**
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedFromOthScrForRef(NFBL2040BMsg scrnMsg) {
        // Header
        scrnMsg.cmApInvTpCd_S.setInputProtected(true);
        scrnMsg.poNum.setInputProtected(true);
        // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        scrnMsg.xxLinkProt_V1.setInputProtected(true);
        // END   2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        scrnMsg.delyOrdNum_H.setInputProtected(true);
        // START 2016/07/27 T.Tsuchida [QC#12002,ADD]
        scrnMsg.delyOrdNum_HA.setInputProtected(true);
        // END 2016/07/27 T.Tsuchida [QC#12002,ADD]
        scrnMsg.rwsNum_H.setInputProtected(true);
        scrnMsg.rwsNum_HA.setInputProtected(true);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum.setInputProtected(true);
        scrnMsg.vndRtrnNum_HA.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        // HEADER tab
        scrnMsg.dplyVndNm.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.dplyVndNm_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.xxLinkProt.setInputProtected(true);
        scrnMsg.apVndCd.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.apVndCd_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.apVndInvNum.setInputProtected(true);
        scrnMsg.invDt.setInputProtected(true);
        scrnMsg.xxChkBox_PA.setInputProtected(true);
        scrnMsg.apPmtChkNum.setInputProtected(true);
        scrnMsg.prntVndCd.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.prntVndCd_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        // QC#21653 Delete cmInvMatchCd_S
        // scrnMsg.cmInvMatchCd_S.setInputProtected(true);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum_HT.setInputProtected(true);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        scrnMsg.xxLinkProt_V2.setInputProtected(true);
        // END   2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        scrnMsg.invAmt.setInputProtected(true);
        scrnMsg.vndPmtTermCd.setInputProtected(true);
        scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.xxChkBox_HO.setInputProtected(true);
        scrnMsg.vndPmtMethCd.setInputProtected(true);
        scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
        scrnMsg.poApvlDt.setInputProtected(true);
        scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
        scrnMsg.delyOrdNum_DH.setInputProtected(true);
        // START 2016/07/27 T.Tsuchida [QC#12002,ADD]
        scrnMsg.delyOrdNum_DA.setInputProtected(true);
        // END 2016/07/27 T.Tsuchida [QC#12002,ADD]
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum_H.setInputProtected(true);
        scrnMsg.xxLinkProt_V3.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        scrnMsg.rwsNum_DH.setInputProtected(true);
        scrnMsg.rwsNum_DA.setInputProtected(true);
        scrnMsg.invHdrDescTxt.setInputProtected(true);
        scrnMsg.xxChkBox_PO.setInputProtected(true);
        // START 2018/02/26 [QC#23505, ADD]
        scrnMsg.xxChkBox_CR.setInputProtected(true);
        // END   2018/02/26 [QC#23505, ADD]
        scrnMsg.acctInvStsDescTxt.setInputProtected(true);
        scrnMsg.pmtDt.setInputProtected(true);
        scrnMsg.hdrPmtAmt.setInputProtected(true);
        scrnMsg.termNetDueDt.setInputProtected(true);
        scrnMsg.acOcTotDiscAmt.setInputProtected(true);
        scrnMsg.apInvCatgDescTxt.setInputProtected(true);
        scrnMsg.acctDt.setInputProtected(true);
        // START 2020/03/23 [QC#53413, ADD]
        scrnMsg.xxLinkProt_V4.setInputProtected(true);
        scrnMsg.poNum_L.setInputProtected(true);
        scrnMsg.delyOrdNum_LA.setInputProtected(true);
        scrnMsg.delyOrdNum_L.setInputProtected(true);
        scrnMsg.rwsNum_LA.setInputProtected(true);
        scrnMsg.rwsNum_L.setInputProtected(true);
        scrnMsg.xxChkBox_MP.setInputProtected(true);
        // END   2020/03/23 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        scrnMsg.vndRtrnNum_LA.setInputProtected(true);
        scrnMsg.vndRtrnNum_L.setInputProtected(true);
        // add end 2022/02/15 QC#57090

        // LINES TAB
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            // END   2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
            scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
            // START 2018/08/03 [QC#27563, ADD]
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
            // END   2018/08/03 [QC#27563, ADD]
            // START 2018/02/26 [QC#23505, ADD]
            scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
            // END 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
            // START 2020/03/23 [QC#53413, ADD]
            scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
            scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
            scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
            // END   2020/03/23 [QC#53413, ADD]
            scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
            // END   2018/02/26 [QC#23505, ADD]
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]
        }
        // START 2016/07/07 [QC#11331,MOD]
        scrnMsg.xxTotPrcAmt_F.setInputProtected(true);
        // END   2016/07/07 [QC#11331,MOD]

        // HOLDS TAB
        scrnMsg.apVndInvNum_HH.setInputProtected(true);
        scrnMsg.invAmt_HH.setInputProtected(true);
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,MOD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(true);             // Delete Row Checkbox
            // START 2018/03/27 [QC#24277, ADD]
            scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);      // Line Number
            // END   2018/03/27 [QC#24277, ADD]
            scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(true);             // Hold Name
            scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);             // Hold Date
            scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(true);          // Hold Reason
            scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);          // Hold By
            scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);             // Release Checkbox
            scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);       // Released By
            scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);          // Released Date
            scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);       // Release Reason
            scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);     // Release Note
        }
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,MOD]

        // DISTRIBUTIONS TAB
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).xxDtlLineNum_D1.setInputProtected(true); // Line Number
            scrnMsg.D.no(i).xxDtlLineNum_D2.setInputProtected(true); // Distribution Line Number
            scrnMsg.D.no(i).invDt_D1.setInputProtected(true); // Date
            scrnMsg.D.no(i).jrnlFuncDrAmt_D1.setInputProtected(true); // Debit
            scrnMsg.D.no(i).jrnlFuncCrAmt_D1.setInputProtected(true); // Credit
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).apAcctDescTxt_D1.setInputProtected(true); // Account Description
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).xxCmntTxt_D1.setInputProtected(true); // Account Code
        }
        // START 2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
        scrnMsg.xxAllocTpCd_D.setInputProtected(true);    // Distribution(All Lines / Selected Lines)
        // END   2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonFromOthScrForRef(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {

        // HEADER
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, false);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]

        // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.apVndCd)
                && ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum)) {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, true);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, false);
        }
        // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

        // START 2016/09/26 W.Honda [Unit Test#201,ADD]
        handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, false);
        // START 2016/09/26 W.Honda [Unit Test#201,ADD]

        // HEADER tab
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        handler.setButtonEnabled(BTN_NORMAL_INVOICE_CANCEL, false);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        handler.setButtonEnabled(BTN_NORMAL_REFRESH, false);
        // START 2018/02/26 [QC#23505, ADD]
        handler.setButtonEnabled(BTN_NORMAL_CORRECTION, false);
        // START QC#25902,QC#25190,QC#25141
        handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, false);
        // END QC#25902,QC#25190,QC#25141
        // END   2018/02/26 [QC#23505, ADD]
        // START 2018/11/29 [QC#28904, ADD]
        handler.setButtonEnabled(BTN_NORMAL_REGENERATE_INVOICE, false);
        // END   2018/11/29 [QC#28904, ADD]

        // LINES TAB
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
        handler.setButtonEnabled(BTN_NORMAL_LINES_TAB_DOWNLOAD_BUTTON, true);
        // START 2016/09/26 T.Tsuchida [QC#12040,MOD]
        handler.setButtonEnabled(BTN_NORMAL_MDSE, false);
        handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, false);
        handler.setButtonEnabled(BTN_NORMAL_CHARGE_ACCOUNT, true);
        // END 2016/09/26 T.Tsuchida [QC#12040,MOD]
        // START 2020/03/23 [QC#53413, ADD]
        handler.setButtonEnabled(BTN_NORMAL_LINE_SEARCH, false);
        // END   2020/03/23 [QC#53413, ADD]

        // HOLDS TAB
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);
        handler.setButtonEnabled(BTN_NORMAL_HOLD, false);
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        handler.setButtonEnabled(BTN_NORMAL_RELEASE, false);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // DISTRIBUTIONS TAB
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        // START 2017/12/27 [QC#22413, ADD]
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        // END   2017/12/27 [QC#22413, ADD]
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedFromOthScrForEdit(NFBL2040BMsg scrnMsg) {

        // Header
        scrnMsg.cmApInvTpCd_S.setInputProtected(true);
        scrnMsg.poNum.setInputProtected(true);
        // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        scrnMsg.xxLinkProt_V1.setInputProtected(true);
        // END   2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        scrnMsg.delyOrdNum_H.setInputProtected(true);
        // START 2016/07/27 T.Tsuchida [QC#12002,ADD]
        scrnMsg.delyOrdNum_HA.setInputProtected(true);
        // END 2016/07/27 T.Tsuchida [QC#12002,ADD]
        scrnMsg.rwsNum_H.setInputProtected(true);
        scrnMsg.rwsNum_HA.setInputProtected(true);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum.setInputProtected(true);
        scrnMsg.vndRtrnNum_HA.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        // HEADER tab
        scrnMsg.dplyVndNm.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.dplyVndNm_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.xxLinkProt.setInputProtected(true);
        scrnMsg.apVndCd.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.apVndCd_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.apVndInvNum.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
        scrnMsg.invDt.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
        scrnMsg.xxChkBox_PA.setInputProtected(true);
        scrnMsg.apPmtChkNum.setInputProtected(true);
        scrnMsg.prntVndCd.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.prntVndCd_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        // QC#21653 Delete cmInvMatchCd_S
        // scrnMsg.cmInvMatchCd_S.setInputProtected(true);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum_HT.setInputProtected(true);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        scrnMsg.xxLinkProt_V2.setInputProtected(true);
        // END   2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
        // START 2016/08/10 Y.Tsuchimoto [QC#12970,MOD]
        scrnMsg.invAmt.setInputProtected(true);
        // END   2016/08/10 Y.Tsuchimoto [QC#12970,MOD]
        scrnMsg.vndPmtTermCd.setInputProtected(true);
        scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.xxChkBox_HO.setInputProtected(true);
        scrnMsg.vndPmtMethCd.setInputProtected(true);
        scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
        scrnMsg.poApvlDt.setInputProtected(true);
        scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
        scrnMsg.delyOrdNum_DH.setInputProtected(true);
        // START 2016/07/27 T.Tsuchida [QC#12002,ADD]
        scrnMsg.delyOrdNum_DA.setInputProtected(true);
        // END 2016/07/27 T.Tsuchida [QC#12002,ADD]
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum_H.setInputProtected(true);
        scrnMsg.xxLinkProt_V3.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        scrnMsg.rwsNum_DH.setInputProtected(true);
        scrnMsg.rwsNum_DA.setInputProtected(true);
        // START 2016/08/10 Y.Tsuchimoto [QC#12970,MOD]
        scrnMsg.invHdrDescTxt.setInputProtected(true);
        scrnMsg.xxChkBox_PO.setInputProtected(true);
        // END   2016/08/10 Y.Tsuchimoto [QC#12970,MOD]
        // START 2018/02/26 [QC#23505, ADD]
        scrnMsg.xxChkBox_CR.setInputProtected(true);
        // END   2018/02/26 [QC#23505, ADD]
        scrnMsg.acctInvStsDescTxt.setInputProtected(true);
        scrnMsg.pmtDt.setInputProtected(true);
        scrnMsg.hdrPmtAmt.setInputProtected(true);
        scrnMsg.termNetDueDt.setInputProtected(true);
        scrnMsg.acOcTotDiscAmt.setInputProtected(true);
        scrnMsg.apInvCatgDescTxt.setInputProtected(true);
        scrnMsg.acctDt.setInputProtected(true);
        // START 2020/03/23 [QC#53413, ADD]
        scrnMsg.xxLinkProt_V4.setInputProtected(true);
        scrnMsg.poNum_L.setInputProtected(true);
        scrnMsg.delyOrdNum_LA.setInputProtected(true);
        scrnMsg.delyOrdNum_L.setInputProtected(true);
        scrnMsg.rwsNum_LA.setInputProtected(true);
        scrnMsg.rwsNum_L.setInputProtected(true);
        scrnMsg.xxChkBox_MP.setInputProtected(true);
        // END   2020/03/23 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        scrnMsg.vndRtrnNum_LA.setInputProtected(true);
        scrnMsg.vndRtrnNum_L.setInputProtected(true);
        // add end 2022/02/15 QC#57090

        // LINES TAB
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            // END   2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
            scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            // START 2016/08/10 Y.Tsuchimoto [QC#12970,MOD]
            scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
            // END   2016/08/10 Y.Tsuchimoto [QC#12970,MOD]
            scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
            // QC#21666
            scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
            // END 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
            // START 2018/08/03 [QC#27563, ADD]
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
            // END   2018/08/03 [QC#27563, ADD]
            // START 2018/02/26 [QC#23505, ADD]
            scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
            // START 2020/03/23 [QC#53413, ADD]
            scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
            scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
            scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
            // END   2020/03/23 [QC#53413, ADD]
            scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
            // END   2018/02/26 [QC#23505, ADD]
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]
        }
        // START 2016/07/07 [QC#11331,MOD]
        scrnMsg.xxTotPrcAmt_F.setInputProtected(true);
        // END   2016/07/07 [QC#11331,MOD]

        // HOLDS TAB
        scrnMsg.apVndInvNum_HH.setInputProtected(true);
        scrnMsg.invAmt_HH.setInputProtected(true);
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,MOD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                // Manual Hold
                scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(false);            // Delete Row Checkbox
                // START 2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);      // Line Number
                // END   2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(false);            // Hold Name
                scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);             // Hold Date
                scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(false);         // Hold Reason
                scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);          // Hold By
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);        // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);         // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
                scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);       // Released By
                scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);          // Released Date
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);       // Release Reason
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);     // Release Note
            } else {
               // Auto Hold
               scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(true);              // Delete Row Checkbox
               // START 2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);       // Line Number
               // END   2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(true);              // Hold Name
               scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);              // Hold Date
               scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(true);           // Hold Reason
               scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);           // Hold By
               // START 2016/08/05 Y.Tsuchimoto [QC#12201,MOD]
               if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                   // START 2020/03/16 [QC#55993, MOD]
                   if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                       scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);     // Release Checkbox
                   } else {
                       scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);      // Release Checkbox
                   }
                   // END   2020/03/16 [QC#55993, MOD]
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);    // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);  // Release Note
               } else {
                   scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);         // Release Checkbox
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);   // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false); // Release Note
               }
               scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);        // Released By
               scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);           // Released Date
            }
        }
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,MOD]

        // DISTRIBUTIONS TAB
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).xxDtlLineNum_D1.setInputProtected(true); // Line Number
            scrnMsg.D.no(i).xxDtlLineNum_D2.setInputProtected(true); // Distribution Line Number
            scrnMsg.D.no(i).invDt_D1.setInputProtected(true); // Date
            scrnMsg.D.no(i).jrnlFuncDrAmt_D1.setInputProtected(true); // Debit
            scrnMsg.D.no(i).jrnlFuncCrAmt_D1.setInputProtected(true); // Credit
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).apAcctDescTxt_D1.setInputProtected(true); // Account Description
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).xxCmntTxt_D1.setInputProtected(true); // Account Code
        }
        // START 2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
        scrnMsg.xxAllocTpCd_D.setInputProtected(true);    // Distribution(All Lines / Selected Lines)
        // END   2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonFromOthScrForEdit(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {

        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, false);
        // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.apVndCd)
                && ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum)) {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, true);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, false);
        }
        // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

        // START 2016/09/26 W.Honda [Unit Test#201,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd)) {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, false);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, true);
        }
        // START 2016/09/26 W.Honda [Unit Test#201,ADD]

        // HEADER tab
        handler.setButtonEnabled(BTN_NORMAL_REFRESH, false);
        // START 2018/02/26 [QC#23505, ADD]
        if (isCorrectableInvoice(scrnMsg) && !isUnsubmittedCorrection(scrnMsg)) {
            handler.setButtonEnabled(BTN_NORMAL_CORRECTION, true);
            // START QC#25902,QC#25190,QC#25141
            handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, true);
            // END QC#25902,QC#25190,QC#25141

        } else {
            handler.setButtonEnabled(BTN_NORMAL_CORRECTION, false);
            // START QC#25902,QC#25190,QC#25141
            handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, false);
            // END QC#25902,QC#25190,QC#25141
        }
        // END   2018/02/26 [QC#23505, ADD]

        // LINES TAB
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
        handler.setButtonEnabled(BTN_NORMAL_LINES_TAB_DOWNLOAD_BUTTON, true);
        // START 2016/09/26 T.Tsuchida [QC#12040,MOD]
        handler.setButtonEnabled(BTN_NORMAL_MDSE, false);
        handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, false);
        handler.setButtonEnabled(BTN_NORMAL_CHARGE_ACCOUNT, true);
        // END 2016/09/26 T.Tsuchida [QC#12040,MOD]
        // START 2020/03/23 [QC#53413, ADD]
        handler.setButtonEnabled(BTN_NORMAL_LINE_SEARCH, false);
        // END   2020/03/23 [QC#53413, ADD]

        // HOLDS TAB
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        // START 2016/09/28 Y.Tsuchimoto [QC#14797,MOD]
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);
        // END   2016/09/28 Y.Tsuchimoto [QC#14797,MOD]
        handler.setButtonEnabled(BTN_NORMAL_RELEASE, true);
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // DISTRIBUTIONS TAB
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        // START 2016/08/05 Y.Tsuchimoto [QC#12952,MOD]
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        // END   2016/08/05 Y.Tsuchimoto [QC#12952,MOD]
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        // START 2017/12/27 [QC#22143, MOD]. 2018/07/12 Delete [QC#27025]
//        if (isApprovalUser() && ACCT_INV_STS.ENTERED.equals(scrnMsg.acctInvStsCd.getValue())) {
//            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 1, null);
//        } else {
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
//        }
        // END   2017/12/27 [QC#22143, MOD]
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        // QC#22289
        if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd)) {
            if (ACCT_INV_STS.LINKED_TO_COST_CALCULATION.equals(scrnMsg.acctInvStsCd.getValue())) { 
                handler.setButtonEnabled(BTN_NORMAL_INVOICE_CANCEL, false);
            }
        }

    }

    /**
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedNotFromOthScrForRef(NFBL2040BMsg scrnMsg) {

        // Header
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
        scrnMsg.cmApInvTpCd_S.setInputProtected(true);
        scrnMsg.poNum.setInputProtected(true);
        scrnMsg.xxLinkProt_V1.setInputProtected(true);
        scrnMsg.delyOrdNum_H.setInputProtected(true);
        scrnMsg.delyOrdNum_HA.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
        scrnMsg.rwsNum_H.setInputProtected(true);
        scrnMsg.rwsNum_HA.setInputProtected(true);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum.setInputProtected(true);
        scrnMsg.vndRtrnNum_HA.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        // HEADER tab
        scrnMsg.dplyVndNm.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.dplyVndNm_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.xxLinkProt.setInputProtected(true);
        scrnMsg.apVndCd.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.apVndCd_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.apVndInvNum.setInputProtected(true);
        scrnMsg.invDt.setInputProtected(true);
        scrnMsg.xxChkBox_PA.setInputProtected(true);
        scrnMsg.apPmtChkNum.setInputProtected(true);
        scrnMsg.prntVndCd.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.prntVndCd_L.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        // QC#21653 Delete cmInvMatchCd_S
        // scrnMsg.cmInvMatchCd_S.setInputProtected(true);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum_HT.setInputProtected(true);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.xxLinkProt_V2.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.invAmt.setInputProtected(true);
        scrnMsg.vndPmtTermCd.setInputProtected(true);
        scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.xxChkBox_HO.setInputProtected(true);
        scrnMsg.vndPmtMethCd.setInputProtected(true);
        scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
        scrnMsg.poApvlDt.setInputProtected(true);
        scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
        scrnMsg.delyOrdNum_DH.setInputProtected(true);
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        scrnMsg.delyOrdNum_DA.setInputProtected(true);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum_H.setInputProtected(true);
        scrnMsg.xxLinkProt_V3.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        scrnMsg.rwsNum_DH.setInputProtected(true);
        scrnMsg.rwsNum_DA.setInputProtected(true);
        scrnMsg.invHdrDescTxt.setInputProtected(true);
        scrnMsg.xxChkBox_PO.setInputProtected(true);
        // START 2018/02/26 [QC#23505, ADD]
        scrnMsg.xxChkBox_CR.setInputProtected(true);
        // END   2018/02/26 [QC#23505, ADD]
        scrnMsg.acctInvStsDescTxt.setInputProtected(true);
        scrnMsg.pmtDt.setInputProtected(true);
        scrnMsg.hdrPmtAmt.setInputProtected(true);
        scrnMsg.termNetDueDt.setInputProtected(true);
        scrnMsg.acOcTotDiscAmt.setInputProtected(true);
        scrnMsg.apInvCatgDescTxt.setInputProtected(true);
        scrnMsg.acctDt.setInputProtected(true);
        // START 2020/03/23 [QC#53413, ADD]
        scrnMsg.xxLinkProt_V4.setInputProtected(true);
        scrnMsg.poNum_L.setInputProtected(true);
        scrnMsg.delyOrdNum_LA.setInputProtected(true);
        scrnMsg.delyOrdNum_L.setInputProtected(true);
        scrnMsg.rwsNum_LA.setInputProtected(true);
        scrnMsg.rwsNum_L.setInputProtected(true);
        scrnMsg.xxChkBox_MP.setInputProtected(true);
        // END   2020/03/23 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        scrnMsg.vndRtrnNum_LA.setInputProtected(true);
        scrnMsg.vndRtrnNum_L.setInputProtected(true);
        // add end 2022/02/15 QC#57090

        // LINES TAB
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
            //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
            scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
            // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
            scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            // START 2018/01/25 K.Kameoka [QC#21696,MOD]
            scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
            // END   2018/01/25 K.Kameoka [QC#21696,MOD]
            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
            // END 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
            // START 2018/08/03 [QC#27563, ADD]
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
            // END   2018/08/03 [QC#27563, ADD]
            // START 2018/02/26 [QC#23505, ADD]
            scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
            // START 2020/03/23 [QC#53413, ADD]
            scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
            scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
            scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
            // END   2020/03/23 [QC#53413, ADD]
            scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
            // END   2018/02/26 [QC#23505, ADD]
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]
        }
        // START 2016/07/07 [QC#11331,MOD]
        scrnMsg.xxTotPrcAmt_F.setInputProtected(true);
        // END   2016/07/07 [QC#11331,MOD]

        // HOLDS TAB
        scrnMsg.apVndInvNum_HH.setInputProtected(true);
        scrnMsg.invAmt_HH.setInputProtected(true);
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,MOD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(true);             // Delete Row Checkbox
            // START 2018/03/27 [QC#24277, ADD]
            scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);      // Line Number
            // END   2018/03/27 [QC#24277, ADD]
            scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(true);             // Hold Name
            scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);             // Hold Date
            scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(true);          // Hold Reason
            scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);          // Hold By
            // START 2020/03/16 [QC#55993, MOD]
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);        // Release Checkbox
            } else {
                scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);         // Release Checkbox
            }
            // END   2020/03/16 [QC#55993, MOD]
            scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);       // Released By
            scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);          // Released Date
            scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);       // Release Reason
            scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);     // Release Note
        }
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,MOD]

        // DISTRIBUTIONS TAB
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).xxDtlLineNum_D1.setInputProtected(true); // Line Number
            scrnMsg.D.no(i).xxDtlLineNum_D2.setInputProtected(true); // Distribution Line Number
            scrnMsg.D.no(i).invDt_D1.setInputProtected(true); // Date
            scrnMsg.D.no(i).jrnlFuncDrAmt_D1.setInputProtected(true); // Debit
            scrnMsg.D.no(i).jrnlFuncCrAmt_D1.setInputProtected(true); // Credit
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).apAcctDescTxt_D1.setInputProtected(true); // Account Description
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).xxCmntTxt_D1.setInputProtected(true); // Account Code
        }
        // START 2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
        scrnMsg.xxAllocTpCd_D.setInputProtected(true);    // Distribution(All Lines / Selected Lines)
        // END   2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonNotFromOthScrForRef(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {

        // Header
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, false);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,MOD]
        // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.apVndCd)
                && ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum)) {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, true);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, false);
        }
        // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

        // START 2016/09/26 W.Honda [Unit Test#201,ADD]
        handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, false);
        // START 2016/09/26 W.Honda [Unit Test#201,ADD]

        // HEADER tab
        handler.setButtonEnabled(BTN_NORMAL_REFRESH, false);
        // START 2018/02/26 [QC#23505, ADD]
        handler.setButtonEnabled(BTN_NORMAL_CORRECTION, false);
        // START QC#25902,QC#25190,QC#25141
        handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, false);
        // END QC#25902,QC#25190,QC#25141
        // END   2018/02/26 [QC#23505, ADD]

        // LINES TAB
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
        handler.setButtonEnabled(BTN_NORMAL_LINES_TAB_DOWNLOAD_BUTTON, true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled(BTN_NORMAL_MDSE, false);
            handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, false);
        }
        // START 2020/03/23 [QC#53413, ADD]
        handler.setButtonEnabled(BTN_NORMAL_LINE_SEARCH, false);
        // END   2020/03/23 [QC#53413, ADD]
        
        // HOLDS TAB
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);
        handler.setButtonEnabled(BTN_NORMAL_HOLD, false);
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        handler.setButtonEnabled(BTN_NORMAL_RELEASE, false);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // DISTRIBUTIONS TAB
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        // START 2017/12/27 [QC#22413, ADD]
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        // END   2017/12/27 [QC#22413, ADD]
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    // START 2016/08/05 Y.Tsuchimoto [QC#12952,ADD]
    /**
     * setButtonNotFromOthScrForAfterApprove
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonNotFromOthScrForAfterApprove(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
        // START 2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        // HEADER tab
        handler.setButtonEnabled(BTN_NORMAL_REFRESH, false);
        // END   2016/08/24 Y.Tsuchimoto [QC#13693,ADD]
        // START 2018/02/26 [QC#23505, ADD]
        handler.setButtonEnabled(BTN_NORMAL_CORRECTION, false);
        // START QC#25902,QC#25190,QC#25141
        handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, false);
        // END QC#25902,QC#25190,QC#25141
        // END   2018/02/26 [QC#23505, ADD]

        // START 2016/09/26 W.Honda [Unit Test#201,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd)) {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, false);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, true);
        }
        // START 2016/09/26 W.Honda [Unit Test#201,ADD]

        // LINES TAB
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);

        handler.setButtonEnabled(BTN_NORMAL_MDSE, false);
        handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, false);
        // START 2016/09/26 T.Tsuchida [QC#12040,MOD]
        handler.setButtonEnabled(BTN_NORMAL_CHARGE_ACCOUNT, true);
        // END 2016/09/26 T.Tsuchida [QC#12040,MOD]
        // START 2020/03/23 [QC#53413, ADD]
        handler.setButtonEnabled(BTN_NORMAL_LINE_SEARCH, false);
        // END   2020/03/23 [QC#53413, ADD]

        // START 2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        // HOLDS TAB
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,ADD]

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        // START 2017/12/27 [QC#22413, ADD]
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        // END   2017/12/27 [QC#22413, ADD]
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }
    // END   2016/08/05 Y.Tsuchimoto [QC#12952,ADD]

    /**
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedNotFromOthScrForEdit(NFBL2040BMsg scrnMsg) {

        // Header
        scrnMsg.cmApInvTpCd_S.setInputProtected(false);
        scrnMsg.poNum.setInputProtected(false);
        scrnMsg.xxLinkProt_V1.setInputProtected(false);
        scrnMsg.delyOrdNum_H.setInputProtected(false);
        scrnMsg.delyOrdNum_HA.setInputProtected(false);
        scrnMsg.rwsNum_H.setInputProtected(false);
        scrnMsg.rwsNum_HA.setInputProtected(false);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum.setInputProtected(false);
        scrnMsg.vndRtrnNum_HA.setInputProtected(false);
        // END QC#25902,QC#25190,QC#25141
        // HEADER tab
        scrnMsg.dplyVndNm.setInputProtected(false);
        scrnMsg.xxLinkProt.setInputProtected(false);
        scrnMsg.apVndCd.setInputProtected(false);
        scrnMsg.apVndInvNum.setInputProtected(false);
        scrnMsg.invDt.setInputProtected(false);
        scrnMsg.xxChkBox_PA.setInputProtected(false);
        scrnMsg.apPmtChkNum.setInputProtected(true);
        scrnMsg.prntVndCd.setInputProtected(false);
        // QC#21653 Delete cmInvMatchCd_S
        // scrnMsg.cmInvMatchCd_S.setInputProtected(false);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum_HT.setInputProtected(false);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.invAmt.setInputProtected(false);
        scrnMsg.vndPmtTermCd.setInputProtected(false);
        scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
        scrnMsg.xxChkBox_HO.setInputProtected(true);
        scrnMsg.vndPmtMethCd.setInputProtected(true);
        scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
        scrnMsg.poApvlDt.setInputProtected(true);
        scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
        scrnMsg.delyOrdNum_DH.setInputProtected(false);
        scrnMsg.invHdrDescTxt.setInputProtected(false);
        scrnMsg.xxChkBox_PO.setInputProtected(false);
        // START 2018/02/26 [QC#23505, ADD]
        scrnMsg.xxChkBox_CR.setInputProtected(true);
        // END   2018/02/26 [QC#23505, ADD]
        scrnMsg.acctInvStsDescTxt.setInputProtected(true);
        scrnMsg.pmtDt.setInputProtected(true);
        scrnMsg.hdrPmtAmt.setInputProtected(true);
        scrnMsg.termNetDueDt.setInputProtected(true);
        scrnMsg.acOcTotDiscAmt.setInputProtected(true);
        scrnMsg.apInvCatgDescTxt.setInputProtected(true);
        scrnMsg.acctDt.setInputProtected(true);

        scrnMsg.dplyVndNm_L.setInputProtected(false);
        scrnMsg.apVndCd_L.setInputProtected(false);
        scrnMsg.prntVndCd_L.setInputProtected(false);
        scrnMsg.xxLinkProt_V2.setInputProtected(false);
        scrnMsg.vndPmtTermDescTxt_A.setInputProtected(false);
        scrnMsg.delyOrdNum_DA.setInputProtected(false);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum_H.setInputProtected(false);
        scrnMsg.xxLinkProt_V3.setInputProtected(false);
        // END QC#25902,QC#25190,QC#25141
        scrnMsg.rwsNum_DH.setInputProtected(false);
        scrnMsg.rwsNum_DA.setInputProtected(false);
        // START 2020/03/23 [QC#53413, ADD]
        scrnMsg.xxLinkProt_V4.setInputProtected(false);
        scrnMsg.poNum_L.setInputProtected(false);
        scrnMsg.delyOrdNum_LA.setInputProtected(false);
        scrnMsg.delyOrdNum_L.setInputProtected(false);
        scrnMsg.rwsNum_LA.setInputProtected(false);
        scrnMsg.rwsNum_L.setInputProtected(false);
        scrnMsg.xxChkBox_MP.setInputProtected(false);
        // END   2020/03/23 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        scrnMsg.vndRtrnNum_LA.setInputProtected(false);
        scrnMsg.vndRtrnNum_L.setInputProtected(false);
        // add end 2022/02/15 QC#57090

        // LINES TAB
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2018/03/16 [QC#24089, ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
            // START 2018/04/04 [QC#20316, MOD]
            //      && isPOLineClosed(scrnMsg, scrnMsg.A.no(i))) {
                    && !isEditableLine(scrnMsg, scrnMsg.A.no(i))) {
            // END   2018/04/04 [QC#20316, MOD]
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
                // START 2018/03/20 [QC#24089, ADD]
                scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
                // END   2018/03/20 [QC#24089, ADD]
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                // END   2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
                continue;
            }
            // END   2018/03/16 [QC#24089, ADD]

            // START 2016/09/14 Y.Tsuchimoto [QC#13333,MOD]
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                // Item
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                // START 2018/01/25 K.Kameoka [QC#21696,MOD]
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                // END   2018/01/25 K.Kameoka [QC#21696,MOD]
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                // END   2018/08/03 [QC#27563, ADD]
                // START 2018/02/26 [QC#23505, ADD]
                scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                // END   2018/02/26 [QC#23505, ADD]
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
            // START QC#25902,QC#25190,QC#25141
            } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.MISC.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                // Misc
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                // START 2019/11/02 [QC#54439, MOD]
                // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                // END 2019/11/02 [QC#54439, MOD]
                scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                // END   2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
            // END QC#25902,QC#25190,QC#25141
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(false);
                // START 2019/11/02 [QC#54439, MOD]
                // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                // END 2019/11/02 [QC#54439, MOD]
                // START 2016/11/14 Y.Tsuchimoto [QC#15773,MOD]
                scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                // END   2016/11/14 Y.Tsuchimoto [QC#15773,MOD]
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).poQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).apRejQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(false);
                scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(false);
                scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(false);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                // END   2018/08/03 [QC#27563, ADD]
                // START 2018/02/26 [QC#23505, ADD]
                scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(false);
                scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(false);
                scrnMsg.A.no(i).rwsNum_A1.setInputProtected(false);
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                // END   2018/02/26 [QC#23505, ADD]
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
            }
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            // END   2016/09/14 Y.Tsuchimoto [QC#13333,MOD]
        }
        // START 2016/07/07 [QC#11331,MOD]
        scrnMsg.xxTotPrcAmt_F.setInputProtected(true);
        // END   2016/07/07 [QC#11331,MOD]

        // HOLDS TAB
        scrnMsg.apVndInvNum_HH.setInputProtected(true);
        scrnMsg.invAmt_HH.setInputProtected(true);
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,MOD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                // Manual Hold
                scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(false);            // Delete Row Checkbox
                // START 2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);      // Line Number
                // END   2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(false);            // Hold Name
                scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);             // Hold Date
                scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(false);         // Hold Reason
                scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);          // Hold By
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);        // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);         // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
                scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);       // Released By
                scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);          // Released Date
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);       // Release Reason
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);     // Release Note
            } else {
               // Auto Hold
               scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(true);              // Delete Row Checkbox
               // START 2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);       // Line Number
               // END   2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(true);              // Hold Name
               scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);              // Hold Date
               scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(true);           // Hold Reason
               scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);           // Hold By
               // START 2016/08/05 Y.Tsuchimoto [QC#12201,MOD]
               if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                   // START 2020/03/16 [QC#55993, MOD]
                   if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                       scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);     // Release Checkbox
                   } else {
                       scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);      // Release Checkbox
                   }
                   // END   2020/03/16 [QC#55993, MOD]
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);    // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);  // Release Note
               } else {
                   scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);         // Release Checkbox
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);   // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false); // Release Note
               }
               scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);        // Released By
               scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);           // Released Date
            }
        }
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,MOD]

        // DISTRIBUTIONS TAB
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).xxDtlLineNum_D1.setInputProtected(true); // Line Number
            scrnMsg.D.no(i).xxDtlLineNum_D2.setInputProtected(true); // Distribution Line Number
            scrnMsg.D.no(i).invDt_D1.setInputProtected(true); // Date
            scrnMsg.D.no(i).jrnlFuncDrAmt_D1.setInputProtected(true); // Debit
            scrnMsg.D.no(i).jrnlFuncCrAmt_D1.setInputProtected(true); // Credit
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).apAcctDescTxt_D1.setInputProtected(true); // Account Description
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).xxCmntTxt_D1.setInputProtected(true); // Account Code
        }
        // START 2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
        scrnMsg.xxAllocTpCd_D.setInputProtected(true);    // Distribution(All Lines / Selected Lines)
        // END   2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonNotFromOthScrForEdit(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {

        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, true);
        // START 2016/05/23 T.Tsuchida [QC#4492,MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.apVndCd)
                && ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum)) {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, true);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, false);
        }
        // END 2016/05/23 T.Tsuchida [QC#4492,MOD]

        // START 2016/09/26 W.Honda [Unit Test#201,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd)) {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, false);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, true);
        }
        // START 2016/09/26 W.Honda [Unit Test#201,ADD]

        // HEADER tab
        handler.setButtonEnabled(BTN_NORMAL_REFRESH, true);
        // START 2018/02/26 [QC#23505, ADD]
        handler.setButtonEnabled(BTN_NORMAL_CORRECTION, false);
        // START QC#25902,QC#25190,QC#25141
        handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, false);
        // END QC#25902,QC#25190,QC#25141
        // END   2018/02/26 [QC#23505, ADD]

        // LINES TAB
        // S21UserProfileService Instance
        S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
        // Global Company Code
        String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
        // Get Varchar Const value(NFBL2040_PO_MATCH)
        String varCharConstValPoMatch = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_PO_MATCH, GLBL_CMPY_CD);
        if (ZYPCommonFunc.hasValue(varCharConstValPoMatch)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.cmApInvTpCd_S.getValue())
            &&  varCharConstValPoMatch.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
                handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
                handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
            } else {
                handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, true);
                handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, true);
            }
        } else {
            scrnMsg.setMessageInfo(NFBM0028E);
            throw new EZDFieldErrorException();
        }
        handler.setButtonEnabled(BTN_NORMAL_LINES_TAB_DOWNLOAD_BUTTON, true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled(BTN_NORMAL_MDSE, true);
            handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, true);
        }

        // HOLDS TAB
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, true);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, true);
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // DISTRIBUTIONS TAB
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        // START 2017/12/27 [QC#22143, MOD]. 2018/07/12 Delete [QC#27025]
//        if (isApprovalUser() && ACCT_INV_STS.ENTERED.equals(scrnMsg.acctInvStsCd.getValue())) {
//            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 1, null);
//        } else {
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
//        }
        // END   2017/12/27 [QC#22143, MOD]
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Method name: clearRowsBG_H
     * <dd>The method explanation: Clear alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void clearRowsBG_H(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        tblColor.clearRowsBG(TABLE_H, scrnMsg.H);
    }

    /**
     * Method name: clearRowsBG_A
     * <dd>The method explanation: Clear alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void clearRowsBG_A(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        // START 2018/07/23 [QC#27247-1, MOD]
        // tblColor.clearRowsBG(TABLE_A1, scrnMsg.A);
        // tblColor.clearRowsBG(TABLE_A2, scrnMsg.A);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        // END   2018/07/23 [QC#27247-1, MOD]
    }

    /**
     * Method name: clearRowsBG_D
     * <dd>The method explanation: Clear alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void clearRowsBG_D(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        tblColor.clearRowsBG(TABLE_D, scrnMsg.D);
    }

    /**
     * Method name: setAlternateRowsBG_H
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBG_H(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_H, scrnMsg.H);
    }

    /**
     * Method name: setAlternateRowsBG_A
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBG_A(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        // START 2018/07/23 [QC#27247-1, MOD]
        // tblColor.setAlternateRowsBG(TABLE_A1, scrnMsg.A); // Left table
        // tblColor.setAlternateRowsBG(TABLE_A2, scrnMsg.A); // Right table
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
        // END   2018/07/23 [QC#27247-1, MOD]
    
    }

    /**
     * Method name: setAlternateRowsBG_D
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBG_D(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        S21TableColorController tblColor = new S21TableColorController(SCRN_NM_00, scrnMsg);
        tblColor.setAlternateRowsBG(TABLE_D, scrnMsg.D);
    }

    /**
     * Method name: setAlternateRowsBGCommon
     * <dd>The method explanation: Set alternate rows background color.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void setAlternateRowsBGCommon(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        if (scrnMsg.xxPageTblNm.getValue().equals(TABLE_H)) {
            // HOLDS tab
            clearRowsBG_H(scrnMsg);
            setAlternateRowsBG_H(scrnMsg);
        } else if (scrnMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
            // LINES tab
            clearRowsBG_A(scrnMsg);
            setAlternateRowsBG_A(scrnMsg);
//            if (TAB_HEADER.equals(scrnMsg.xxDplyTab_01.getValue())) {
//                clearRowsBG_H(scrnMsg);
//            }
//            if (TAB_LINES.equals(scrnMsg.xxDplyTab_02.getValue())) {
                clearRowsBG_D(scrnMsg);
//            }
        } else if (scrnMsg.xxPageTblNm.getValue().equals(TABLE_D)) {
            // DISTRIBUTIONS tab
            clearRowsBG_D(scrnMsg);
            setAlternateRowsBG_D(scrnMsg);
            clearRowsBG_A(scrnMsg);
        } else {
            // HOLDS tab
            clearRowsBG_H(scrnMsg);
            setAlternateRowsBG_H(scrnMsg);
            // LINES tab
            clearRowsBG_A(scrnMsg);
            setAlternateRowsBG_A(scrnMsg);
            // DISTRIBUTIONS tab
            clearRowsBG_D(scrnMsg);
            setAlternateRowsBG_D(scrnMsg);
        }
    }

    /**
     * Method name: initTabPosition
     * <dd>The method explanation: Initialize tab position.
     * <dd>Remarks: 
     * @param bMsg EZDBMsg
     */
    public static void initTabPosition(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.xxDplyTab_01.setValue(TAB_HEADER);
        scrnMsg.xxDplyTab_02.setValue(TAB_LINES);
    }

    /**
     * Method name: setButtonConfirmMsg
     * <dd>The method explanation: Set Button Confirm Message.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static void setButtonConfirmMsg(EZDCommonHandler handler) {
        // Button Confirm Message Settings
        handler.setButtonConfirmMsg(BTN_CMN_SUBMIT[1], ZZM8101I, new String[] {BTN_CMN_SUBMIT[2] }, 0);
        // START 2017/12/27 [QC#22413, ADD]. 2018/07/12 Delete [QC#27025]
//        handler.setButtonConfirmMsg(BTN_CMN_APPROVE[1], ZZM8101I, new String[] {BTN_CMN_SUBMIT[2] }, 0);
        // END   2017/12/27 [QC#22413, ADD]
        handler.setButtonConfirmMsg(BTN_CMN_CLEAR[1], ZZM8101I, new String[] {BTN_CMN_CLEAR[2] }, 0);
        handler.setButtonConfirmMsg(BTN_CMN_RETURN[1], ZZM8101I, new String[] {BTN_CMN_RETURN[2] }, 0);
    }


    // START 2016/10/06 Y.Tsuchimoto [QC#11613,DEL]
//    /**
//     * Get Param NWAL1130 For Merchandise
//     * @param scrnMsg NFBL2040BMsg
//     * @return Param NWAL1130 For Merchandise
//     */
//    public static Object[] getParamNWAL1130ForMdseButton(NFBL2040BMsg scrnMsg, String glblCmpyCd) {
//
//        Object[] params = new Object[IDX_7];
//        params[IDX_0] = EMPTY_STRING;
//        params[IDX_1] = "Merchandise Search";
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT ");
//        sb.append("  M.EZCANCELFLAG        AS EZCANCELFLAG ");
//        sb.append(", M.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
//        sb.append(", M.MDSE_CD             AS MDSE_CD ");
//        sb.append(", M.MDSE_NM             AS MDSE_NM ");
//        sb.append("FROM ");
//        sb.append("  MDSE M ");
//        sb.append("WHERE ");
//        sb.append("    M.EZCANCELFLAG   = '0' ");
//        sb.append("AND M.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");
//
//        params[IDX_2] = sb.toString();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[IDX_4];
//        whereArray0[IDX_0] = "Merchandise Code";
//        whereArray0[IDX_1] = "UPPER(MDSE_CD)";
//        whereArray0[IDX_2] = scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.getValue();
//        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray0);
//
//        Object[] whereArray1 = new Object[IDX_4];
//        whereArray1[IDX_0] = "Merchandise Name";
//        whereArray1[IDX_1] = "UPPER(MDSE_NM)";
//        whereArray1[IDX_2] = scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseDescShortTxt_A1.getValue();
//        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
//
//        params[IDX_3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//        Object[] columnArray0 = new Object[IDX_4];
//        columnArray0[IDX_0] = "Merchandise Code";
//        columnArray0[IDX_1] = "MDSE_CD";
//        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_16);
//        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray0);
//
//        Object[] columnArray1 = new Object[IDX_4];
//        columnArray1[IDX_0] = "Merchandise Name";
//        columnArray1[IDX_1] = "MDSE_NM";
//        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
//        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray1);
//
//        params[IDX_4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray0 = new Object[IDX_2];
//        sortConditionArray0[IDX_0] = "MDSE_CD";
//        sortConditionArray0[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray0);
//
//        Object[] sortConditionArray1 = new Object[IDX_2];
//        sortConditionArray1[IDX_0] = "MDSE_NM";
//        sortConditionArray1[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray1);
//
//        params[IDX_5] = sortConditionList;
//
//        ZYPTableUtil.clear(scrnMsg.Z);
//        params[IDX_6] = scrnMsg.Z;
//
//        return params;
//    }
    // END   2016/10/06 Y.Tsuchimoto [QC#11613,DEL]

    // START 2016/07/22 Y.Tsuchimoto [QC#12008,ADD]
    /**
     * Get Param NWAL1130 For Supplier
     * @param scrnMsg NFBL2040BMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @return Param NWAL1130 For Supplier
     */
    public static Object[] getParamNWAL1130ForSupplier(NFBL2040BMsg scrnMsg, String glblCmpyCd, String salesDate) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Supplier/Supplier Site Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("    V.EZCANCELFLAG,");
        sb.append("    V.GLBL_CMPY_CD,");
        sb.append("    V.VND_CD,");
        sb.append("    V.LOC_NM,");
        sb.append("    PV.INAC_DT,");
        sb.append("    PV.PRNT_VND_CD,");
        sb.append("    PV.PRNT_VND_NM,");
        sb.append("    PV.PRNT_VND_TP_CD");
        sb.append(" FROM");
        sb.append("    PRNT_VND PV,");
        sb.append("    VND      V,");
        sb.append("    VND_TP_RELN VTR");
        sb.append(" WHERE 1 = 1");
        sb.append("  AND V.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("  AND V.EZCANCELFLAG = '0'");
        sb.append("  AND V.RGTN_STS_CD  = '");
        sb.append(RGTN_STS.READY_FOR_ORDER_TAKING);
        sb.append("'");
        sb.append("  AND V.EFF_THRU_DT  >= '").append(salesDate).append("'");
        // START 2018/09/26 [QC#28099, ADD]
        // START 2019/04/08 [QC#31071, ADD]
        // if (ZYPCommonFunc.hasValue(scrnMsg.poNum_HT)) {
        if (ZYPCommonFunc.hasValue(scrnMsg.poNum_HT) || ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum_H)) {
            sb.append("  AND V.SPLY_PMT_FLG  = '");
            sb.append(ZYPConstant.FLG_ON_Y);
            sb.append("'");
        }
        // END   2019/04/08 [QC#31071, ADD]
        // END   2018/09/26 [QC#28099, ADD]
        sb.append("  AND V.VND_CD        = VTR.VND_CD");
        sb.append("  AND V.GLBL_CMPY_CD  = VTR.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG  = VTR.EZCANCELFLAG");
        sb.append("  AND VTR.VND_TP_CD   = '");
        sb.append(VND_TP.SUPPLIER);
        sb.append("'");
        sb.append("  AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD");
        sb.append("  AND V.EZCANCELFLAG = PV.EZCANCELFLAG");
        sb.append("  AND V.PRNT_VND_PK  = PV.PRNT_VND_PK");
        sb.append("  AND (PV.INAC_DT IS NULL OR PV.INAC_DT > '").append(salesDate).append("')");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        String[] vndNmArray = new String[2];
        if (ZYPCommonFunc.hasValue(scrnMsg.dplyVndNm)) {
            vndNmArray = scrnMsg.dplyVndNm.getValue().split(DIV_DPLY_VND_NM);
        }
        // START 2017/12/22 [QC#22831, MOD]
        whereArray1[IDX_0] = "Supplier Number";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray1[IDX_1] = "PRNT_VND_CD";
        whereArray1[IDX_2] = scrnMsg.prntVndCd.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray2[IDX_0] = "Supplier Name";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray2[IDX_1] = "PRNT_VND_NM";
        whereArray2[IDX_2] = "";
        if (vndNmArray.length >= 1) {
            whereArray2[IDX_2] = vndNmArray[0];
        }
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray3 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray3[IDX_0] = "Supplier Site Code";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray3[IDX_1] = "VND_CD";
        whereArray3[IDX_2] = scrnMsg.apVndCd.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray4 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray4[IDX_0] = "Supplier Site Name";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray4[IDX_1] = "LOC_NM";
        whereArray4[IDX_2] = "";
        if (vndNmArray.length >= 2) {
            whereArray4[IDX_2] = vndNmArray[1];
        }
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        whereList.add(whereArray4);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray1[IDX_0] = "Supplier Number";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray1[IDX_1] = "PRNT_VND_CD";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray2 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray2[IDX_0] = "Supplier Name";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray2[IDX_1] = "PRNT_VND_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray3[IDX_0] = "Supplier Site Code";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray3[IDX_1] = "VND_CD";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray4 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray4[IDX_0] = "Supplier Site Name";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray4[IDX_1] = "LOC_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRNT_VND_CD";
        sortConditionArray1[IDX_1] = "ASC";
        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "VND_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        sortList.add(sortConditionArray2);
        params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;
        return params;
    }
    // END   2016/07/22 Y.Tsuchimoto [QC#12008,ADD]

    // START 2016/07/25 Y.Tsuchimoto [QC#11999,ADD]
    /**
     * Get Param NWAL1130 For Purchase Order
     * @param scrnMsg NFBL2040BMsg
     * @param glblCmpyCd String
     * @param purchaseOrder1Flag boolean
     * @return Param NWAL1130 For Purchase Order
     */
    // START 2020/03/23 [QC#53413, MOD]
    //public static Object[] getParamNWAL1130ForPurchaseOrder(NFBL2040BMsg scrnMsg, String glblCmpyCd, boolean purchaseOrder1Flag) {
    public static Object[] getParamNWAL1130ForPurchaseOrder(NFBL2040BMsg scrnMsg, String glblCmpyCd, String purchaseOrderType) {
    // START 2020/03/23 [QC#53413, MOD]
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "PO Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      P.PO_ORD_NUM             PO_ORD_NUM");

        // QC#22333
        sb.append("     ,P.PRNT_VND_CD            PRNT_VND_CD");
        sb.append("     ,P.PRNT_VND_NM            PRNT_VND_NM");

        sb.append("     ,DPT.DS_PO_TP_DESC_TXT    DS_PO_TP_DESC_TXT");
        sb.append("     ,PHS.PO_HDR_STS_DESC_TXT  PO_HDR_STS_DESC_TXT");
        sb.append("     ,PAS.PO_APVL_STS_DESC_TXT PO_APVL_STS_DESC_TXT");
        sb.append("     ,P.GLBL_CMPY_CD           GLBL_CMPY_CD");
        sb.append("     ,P.EZCANCELFLAG           EZCANCELFLAG");
        sb.append(" FROM");
        sb.append("      PO         P");
        sb.append("     ,DS_PO_TP   DPT");
        sb.append("     ,PO_HDR_STS PHS");
        sb.append("     ,PO_APVL_STS PAS");
        sb.append(" WHERE");
        sb.append("         P.GLBL_CMPY_CD      = '").append(glblCmpyCd).append("' ");
        sb.append("     AND P.EZCANCELFLAG      = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = DPT.GLBL_CMPY_CD");
        sb.append("     AND P.DS_PO_TP_CD       = DPT.DS_PO_TP_CD");
        sb.append("     AND DPT.EZCANCELFLAG    = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = PHS.GLBL_CMPY_CD");
        sb.append("     AND P.PO_HDR_STS_CD     = PHS.PO_HDR_STS_CD");
        // QC#22349
        //sb.append("     AND P.PO_HDR_STS_CD    <> '").append(PO_HDR_STS.CLOSED).append("' ");
        sb.append("     AND PHS.EZCANCELFLAG    = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = PAS.GLBL_CMPY_CD");
        sb.append("     AND P.PO_APVL_STS_CD    = PAS.PO_APVL_STS_CD");
        sb.append("     AND PAS.EZCANCELFLAG    = '0'");
        // START 2016/08/09 Y.Tsuchimoto [QC#12198,DEL]
        // QC#27029 Update. 2018/07/26
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            sb.append("     AND EXISTS (");
            sb.append("             SELECT");
            sb.append("                 1");
            sb.append("             FROM");
            sb.append("                 CM_AP_INV_HDR HDR");
            // START 2020/03/23 [QC#53413, MOD]
            sb.append("                ,CM_AP_INV_DTL DTL");
            sb.append("             WHERE");
            sb.append("                     DTL.GLBL_CMPY_CD      = P.GLBL_CMPY_CD");
            sb.append("                 AND DTL.PO_NUM            = P.PO_ORD_NUM");
            sb.append("                 AND DTL.GLBL_CMPY_CD      = HDR.GLBL_CMPY_CD");
            sb.append("                 AND DTL.AP_VND_CD         = HDR.AP_VND_CD");
            sb.append("                 AND DTL.AP_VND_INV_NUM    = HDR.AP_VND_INV_NUM");
            sb.append("                 AND DTL.AP_VND_INV_SQ_NUM = HDR.AP_VND_INV_SQ_NUM");
            sb.append("                 AND DTL.EZCANCELFLAG      = '0'");
            sb.append("                 AND HDR.EZCANCELFLAG      = '0'");
            // END   2020/03/23 [QC#53413, MOD]
            sb.append(" )");
        // START 2018/11/20 [QC#28660, MOD]
        //    sb.append("     AND EXISTS (");
        //    sb.append("             SELECT");
        //    sb.append("                 1");
        //    sb.append("             FROM");
        //    sb.append("                 PO_DTL PD");
        //    sb.append("             WHERE");
        //    sb.append("                     PD.GLBL_CMPY_CD = P.GLBL_CMPY_CD");
        //    sb.append("                 AND PD.PO_ORD_NUM   = P.PO_ORD_NUM");
        //    sb.append("                 AND PD.PO_INV_QTY   > 0");
        //    sb.append("                 AND PD.EZCANCELFLAG = '0'");
        //    sb.append(" )");
        // END   2018/11/20 [QC#28660, MOD]
        }
        // END   2016/08/09 Y.Tsuchimoto [QC#12198,DEL]
        // START 2018/03/06 [QC#23505, ADD]
        if (isCorrectableInvoice(scrnMsg) 
                && isUnsubmittedCorrection(scrnMsg) && isCorrectedInvoice(scrnMsg)) {
            // START 2018/10/03 [QC#28099-1, MOD]
            // sb.append("     AND P.PRNT_VND_CD       = '").append(scrnMsg.prntVndCd.getValue()).append("' ");
            // sb.append("     AND P.VND_CD            = '").append(scrnMsg.apVndCd.getValue()).append("' ");
            sb.append("     AND (P.PRNT_VND_CD, P.VND_CD) ");
            sb.append("           = (SELECT ");
            sb.append("                  PO.PRNT_VND_CD ");
            sb.append("                 ,PO.VND_CD ");
            sb.append("              FROM ");
            sb.append("                  CM_AP_INV_HDR CAIH ");
            // START 2020/03/23 [QC#53413, ADD]
            sb.append("                 ,CM_AP_INV_DTL CAID ");
            // END   2020/03/23 [QC#53413, ADD]
            sb.append("                 ,PO            PO ");
            sb.append("              WHERE ");
            sb.append("                  CAIH.GLBL_CMPY_CD      = '").append(glblCmpyCd).append("' ");
            sb.append("              AND CAIH.AP_VND_CD         = '").append(scrnMsg.apVndCd.getValue()).append("' ");
            sb.append("              AND CAIH.AP_VND_INV_NUM    = '").append(scrnMsg.apVndInvNum.getValue()).append("' ");
            sb.append("              AND CAIH.AP_VND_INV_SQ_NUM = '").append(scrnMsg.apVndInvSqNum.getValue()).append("' ");
            // START 2020/03/23 [QC#53413, ADD]
            //sb.append("              AND CAIH.PO_NUM            = PO.PO_ORD_NUM");
            sb.append("              AND CAID.AP_VND_CD         = CAIH.AP_VND_CD");
            sb.append("              AND CAID.AP_VND_INV_NUM    = CAIH.AP_VND_INV_NUM");
            sb.append("              AND CAID.AP_VND_INV_SQ_NUM = CAIH.AP_VND_INV_SQ_NUM");
            sb.append("              AND CAID.GLBL_CMPY_CD      = CAIH.GLBL_CMPY_CD");
            sb.append("              AND CAID.EZCANCELFLAG = '0'");
            sb.append("              AND CAID.PO_NUM            = PO.PO_ORD_NUM");
            // END   2020/03/23 [QC#53413, ADD]
            sb.append("          )");
            // END   2018/10/03 [QC#28099-1, MOD]
        }
        // END   2018/03/06 [QC#23505, ADD]
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "PO Number";
        whereArray1[IDX_1] = "PO_ORD_NUM";
        // START 2020/03/23 [QC#53413, MOD]
//        if (purchaseOrder1Flag) {
//            whereArray1[IDX_2] = scrnMsg.poNum.getValue();
//        } else {
//            // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
//            whereArray1[IDX_2] = scrnMsg.poNum_HT.getValue();
//            // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
//        }
        if (OPENWIN_PURCHASE_ORDER_1.equals(purchaseOrderType)) {
            whereArray1[IDX_2] = scrnMsg.poNum.getValue();
        } else if (OPENWIN_PURCHASE_ORDER_2.equals(purchaseOrderType)) {
            whereArray1[IDX_2] = scrnMsg.poNum_HT.getValue();
        } else {
            whereArray1[IDX_2] = scrnMsg.poNum_L.getValue();
        }
        // END   2020/03/23 [QC#53413, MOD]
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "PO Number";
        columnArray1[IDX_1] = "PO_ORD_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        // QC#22333
        Object[] columnArray2 = new Object[IDX_4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray2[IDX_0] = "Supplier Number";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray2[IDX_1] = "PRNT_VND_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Supplier Name";
        columnArray3[IDX_1] = "PRNT_VND_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "PO Type";
        columnArray4[IDX_1] = "DS_PO_TP_DESC_TXT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Header Status";
        columnArray5[IDX_1] = "PO_HDR_STS_DESC_TXT";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray6 = new Object[IDX_4];
        columnArray6[IDX_0] = "Approval Status";
        columnArray6[IDX_1] = "PO_APVL_STS_DESC_TXT";
        columnArray6[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray6[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        columnList.add(columnArray5);
        columnList.add(columnArray6);
        params[IDX_4] = columnList;

         List<Object[]> sortList = new ArrayList<Object[]>();
         Object[] sortConditionArray1 = new Object[IDX_2];
         sortConditionArray1[IDX_0] = "PO_ORD_NUM";
         sortConditionArray1[IDX_1] = "ASC";
         sortList.add(sortConditionArray1);
         params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;
        return params;
    }
    // END   2016/07/25 Y.Tsuchimoto [QC#11999,ADD]

    // START 2020/03/23 [QC#53413, MOD]
    /**
     * Get Param NWAL1130 For Purchase Order
     * @param scrnMsg NFBL2040BMsg
     * @param glblCmpyCd String
     * @param purchaseOrder1Flag boolean
     * @return Param NWAL1130 For Purchase Order
     */
    public static Object[] getParamNWAL1130ForPurchaseOrder3(NFBL2040BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "PO Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      P.PO_ORD_NUM             PO_ORD_NUM");

        sb.append("     ,P.PRNT_VND_CD            PRNT_VND_CD");
        sb.append("     ,P.PRNT_VND_NM            PRNT_VND_NM");

        sb.append("     ,DPT.DS_PO_TP_DESC_TXT    DS_PO_TP_DESC_TXT");
        sb.append("     ,PHS.PO_HDR_STS_DESC_TXT  PO_HDR_STS_DESC_TXT");
        sb.append("     ,PAS.PO_APVL_STS_DESC_TXT PO_APVL_STS_DESC_TXT");
        sb.append("     ,P.GLBL_CMPY_CD           GLBL_CMPY_CD");
        sb.append("     ,P.EZCANCELFLAG           EZCANCELFLAG");
        sb.append(" FROM");
        sb.append("      PO         P");
        sb.append("     ,DS_PO_TP   DPT");
        sb.append("     ,PO_HDR_STS PHS");
        sb.append("     ,PO_APVL_STS PAS");
        sb.append(" WHERE");
        sb.append("         P.GLBL_CMPY_CD      = '").append(glblCmpyCd).append("' ");
        sb.append("     AND P.EZCANCELFLAG      = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = DPT.GLBL_CMPY_CD");
        sb.append("     AND P.DS_PO_TP_CD       = DPT.DS_PO_TP_CD");
        sb.append("     AND DPT.EZCANCELFLAG    = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = PHS.GLBL_CMPY_CD");
        sb.append("     AND P.PO_HDR_STS_CD     = PHS.PO_HDR_STS_CD");
        sb.append("     AND PHS.EZCANCELFLAG    = '0'");
        sb.append("     AND P.GLBL_CMPY_CD      = PAS.GLBL_CMPY_CD");
        sb.append("     AND P.PO_APVL_STS_CD    = PAS.PO_APVL_STS_CD");
        sb.append("     AND PAS.EZCANCELFLAG    = '0'");
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            sb.append("     AND EXISTS (");
            sb.append("             SELECT");
            sb.append("                 1");
            sb.append("             FROM");
            sb.append("                 CM_AP_INV_HDR HDR");
            sb.append("                ,CM_AP_INV_DTL DTL");
            sb.append("             WHERE");
            sb.append("                     DTL.GLBL_CMPY_CD      = P.GLBL_CMPY_CD");
            sb.append("                 AND DTL.PO_NUM            = P.PO_ORD_NUM");
            sb.append("                 AND DTL.GLBL_CMPY_CD      = HDR.GLBL_CMPY_CD");
            sb.append("                 AND DTL.AP_VND_CD         = HDR.AP_VND_CD");
            sb.append("                 AND DTL.AP_VND_INV_NUM    = HDR.AP_VND_INV_NUM");
            sb.append("                 AND DTL.AP_VND_INV_SQ_NUM = HDR.AP_VND_INV_SQ_NUM");
            sb.append("                 AND DTL.EZCANCELFLAG      = '0'");
            sb.append("                 AND HDR.EZCANCELFLAG      = '0'");
            sb.append(" )");
        }
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "PO Number";
        whereArray1[IDX_1] = "PO_ORD_NUM";
        whereArray1[IDX_2] = scrnMsg.poNum_L.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "PO Number";
        columnArray1[IDX_1] = "PO_ORD_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Supplier Number";
        columnArray2[IDX_1] = "PRNT_VND_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Supplier Name";
        columnArray3[IDX_1] = "PRNT_VND_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "PO Type";
        columnArray4[IDX_1] = "DS_PO_TP_DESC_TXT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Header Status";
        columnArray5[IDX_1] = "PO_HDR_STS_DESC_TXT";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray6 = new Object[IDX_4];
        columnArray6[IDX_0] = "Approval Status";
        columnArray6[IDX_1] = "PO_APVL_STS_DESC_TXT";
        columnArray6[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray6[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        columnList.add(columnArray5);
        columnList.add(columnArray6);
        params[IDX_4] = columnList;

         List<Object[]> sortList = new ArrayList<Object[]>();
         Object[] sortConditionArray1 = new Object[IDX_2];
         sortConditionArray1[IDX_0] = "PO_ORD_NUM";
         sortConditionArray1[IDX_1] = "ASC";
         sortList.add(sortConditionArray1);
         params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;
        return params;
    }
    // END   2020/03/23 [QC#53413, MOD]

    // START 2016/09/28 T.Tsuchida [QC#13960,MOD]
    // START 2016/07/27 T.Tsuchida [QC#12002,ADD]
    /**
     * Get Param NWAL1130 For Receipt
     * @param scrnMsg NFBL2040BMsg
     * @param glblCmpyCd String
     * @param poNum String
     * @param delyOrdNum String
     * @param rwsNum String
     * @return Param NWAL1130 For Receipt
     */
    public static Object[] getParamNWAL1130ForReceipt(NFBL2040BMsg scrnMsg, String glblCmpyCd, String poNum, String delyOrdNum, String rwsNum) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Receipt Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      CSI.GLBL_CMPY_CD         GLBL_CMPY_CD");
        sb.append("     ,CSI.EZCANCELFLAG         EZCANCELFLAG");
        sb.append("     ,CSI.PO_ORD_NUM           PO_ORD_NUM");
        sb.append("     ,CSI.DELY_ORD_NUM         DELY_ORD_NUM");
        sb.append("     ,RWS.RWS_NUM              RWS_NUM");
        sb.append(" FROM");
        sb.append("      CM_STK_IN CSI");
        sb.append("     ,INVTY_TRX IT");
        sb.append("     ,RWS_HDR   RWS");
        sb.append(" WHERE");
        sb.append("         CSI.GLBL_CMPY_CD    = '").append(glblCmpyCd).append("' ");
        sb.append("     AND CSI.EZCANCELFLAG    = '0'");
        sb.append("     AND CSI.TRX_CD          = '").append(TRX.PURCHASE_STOCK_IN).append("' ");
        sb.append("     AND CSI.TRX_RSN_CD      = '").append(TRX_RSN.PURCHASE_STOCK_IN).append("' ");
        sb.append("     AND CSI.GLBL_CMPY_CD    = IT.GLBL_CMPY_CD  (+)");
        sb.append("     AND CSI.DELY_ORD_NUM    = IT.RWS_REF_NUM   (+)");
        sb.append("     AND IT.EZCANCELFLAG (+) = '0'");
        sb.append("     AND IT.GLBL_CMPY_CD     = RWS.GLBL_CMPY_CD (+)");
        sb.append("     AND IT.RWS_NUM          = RWS.RWS_NUM      (+)");
        sb.append("     AND RWS.EZCANCELFLAG(+) = '0'");
        sb.append(" GROUP BY CSI.GLBL_CMPY_CD, CSI.EZCANCELFLAG, CSI.PO_ORD_NUM, CSI.DELY_ORD_NUM, RWS.RWS_NUM");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "PO Number";
        whereArray1[IDX_1] = "PO_ORD_NUM";
        whereArray1[IDX_2] = poNum;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Receipt Number";
        whereArray2[IDX_1] = "DELY_ORD_NUM";
        whereArray2[IDX_2] = delyOrdNum;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "RWS Number";
        whereArray3[IDX_1] = "RWS_NUM";
        whereArray3[IDX_2] = rwsNum;
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "PO Number";
        columnArray1[IDX_1] = "PO_ORD_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Receipt Number";
        columnArray2[IDX_1] = "DELY_ORD_NUM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "RWS Number";
        columnArray3[IDX_1] = "RWS_NUM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);
        params[IDX_4] = columnList;

         List<Object[]> sortList = new ArrayList<Object[]>();
         Object[] sortConditionArray1 = new Object[IDX_2];
         sortConditionArray1[IDX_0] = "PO_ORD_NUM";
         sortConditionArray1[IDX_1] = "ASC";
         sortList.add(sortConditionArray1);

         Object[] sortConditionArray2 = new Object[IDX_2];
         sortConditionArray2[IDX_0] = "DELY_ORD_NUM";
         sortConditionArray2[IDX_1] = "ASC";
         sortList.add(sortConditionArray2);
         params[IDX_5] = sortList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;
        return params;
    }
    // END 2016/07/27 T.Tsuchida [QC#12002,ADD]
    // END 2016/09/28 T.Tsuchida [QC#13960,MOD]

    // START 2016/08/01 T.Tsuchida [QC#12009,ADD]
    /**
     * Get Param NWAL1130 For Terms
     * @param scrnMsg NFBL2040BMsg
     * @return Param NWAL1130 For Payment Term
     */
    public static Object[] getParamNWAL1130ForTermsLink(NFBL2040BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = EMPTY_STRING;
        params[IDX_1] = "Payment Term Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  VPT.EZCANCELFLAG          AS EZCANCELFLAG ");
        sb.append(", VPT.GLBL_CMPY_CD          AS GLBL_CMPY_CD ");
        sb.append(", VPT.VND_PMT_TERM_CD       AS VND_PMT_TERM_CD ");
        sb.append(", VPT.VND_PMT_TERM_DESC_TXT AS VND_PMT_TERM_DESC_TXT ");
        sb.append("FROM ");
        sb.append("  VND_PMT_TERM  VPT ");
        sb.append("WHERE ");
        sb.append("    VPT.EZCANCELFLAG   = '0' ");
        sb.append("AND VPT.GLBL_CMPY_CD   = '").append(glblCmpyCd).append("' ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Payment Term Code";
        whereArray0[IDX_1] = "UPPER(VND_PMT_TERM_CD)";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Payment Term Name";
        whereArray1[IDX_1] = "UPPER(VND_PMT_TERM_DESC_TXT)";
        whereArray1[IDX_2] = scrnMsg.vndPmtTermDescTxt.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Payment Term Code";
        columnArray0[IDX_1] = "VND_PMT_TERM_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Payment Term Name";
        columnArray1[IDX_1] = "VND_PMT_TERM_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "VND_PMT_TERM_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "VND_PMT_TERM_DESC_TXT";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }
    // END 2016/08/01 T.Tsuchida [QC#12009,MOD]

    // START 2016/08/02 T.Tsuchida [QC#12040,ADD]
    /**
     * Get Param NMAL2550 For Charge Account
     * @param scrnMsg NFBL2040BMsg
     * @return Param NMAL2550 For Payment Term
     */
    public static Object[] getParamForChargeAccount(NFBL2040BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        if (!splitCOA9Seg(scrnMsg)) {
            return null;
        }
        // START 2019/11/02 [QC#54439, MOD]
        // START 2016/12/12 E.Kameishi [QC#15905,MOD]
        String apLineTpCd = scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).apLineTpCd_A1.getValue();
        // START 2018/06/26 S.Katsuma [QC#26566,MOD]
        if (!NFBL2040CommonLogic.isVendorReturnItemLine(scrnMsg, scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()))) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I0, BIZ_ID + apLineTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I0, BIZ_ID + CM_DEF_ACCT.RETURN_COA);
        }
        // END 2018/06/26 S.Katsuma [QC#26566,MOD]
        // END 2016/12/12 E.Kameishi [QC#15905,MOD]
        // END 2019/11/02 [QC#54439, MOD]
        Object[] params = new Object[PARAM_INDEX_10];
        int index = 0;
        params[index++] = scrnMsg.xxPopPrm_I0;
        params[index++] = scrnMsg.xxPopPrm_I1;
        params[index++] = scrnMsg.xxPopPrm_I7;
        params[index++] = scrnMsg.xxPopPrm_I2;
        params[index++] = scrnMsg.xxPopPrm_I3;
        params[index++] = scrnMsg.xxPopPrm_I4;
        params[index++] = scrnMsg.xxPopPrm_I5;
        params[index++] = scrnMsg.xxPopPrm_I6;
        params[index++] = scrnMsg.xxPopPrm_I8;
        params[index++] = scrnMsg.xxPopPrm_I9;
        return params;
    }
    // END 2016/08/02 T.Tsuchida [QC#12040,ADD]

    /**
     * Get Param NMAL6800 For Merchandise
     * @param scrnMsg NFBL2040BMsg
     * @return Object[]
     */
    public static Object[] getParamNMAL6800ForMdseButton(NFBL2040BMsg scrnMsg, String glblCmpyCd) {

        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I0, scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I1, scrnMsg.A.no(scrnMsg.xxCellIdx.getValue().intValue()).mdseDescShortTxt_A1);

        Object[] params = new Object[PARAM_INDEX_10];
        params[0] = scrnMsg.xxPopPrm_I0;
        params[1] = scrnMsg.xxPopPrm_I1;
        params[2] = scrnMsg.xxPopPrm_I2;
        params[3] = scrnMsg.xxPopPrm_I3;
        params[4] = scrnMsg.xxPopPrm_I4;
        params[5] = scrnMsg.xxPopPrm_I5;
        params[6] = scrnMsg.xxPopPrm_I6;
        params[7] = scrnMsg.xxPopPrm_I7;
        params[8] = scrnMsg.xxPopPrm_I8;
        params[9] = scrnMsg.xxPopPrm_I9;

        return params;
    }

    // START 2016/08/02 T.Tsuchida [QC#12040,ADD]
    /**
     * Clear PopUpParam
     * @param scrnMsg NFBL2040BMsg
     */
    private static void clearPopUpParam(NFBL2040BMsg scrnMsg) {
        scrnMsg.xxPopPrm_I0.clear();
        scrnMsg.xxPopPrm_I1.clear();
        scrnMsg.xxPopPrm_I2.clear();
        scrnMsg.xxPopPrm_I3.clear();
        scrnMsg.xxPopPrm_I4.clear();
        scrnMsg.xxPopPrm_I5.clear();
        scrnMsg.xxPopPrm_I6.clear();
        scrnMsg.xxPopPrm_I7.clear();
        scrnMsg.xxPopPrm_I8.clear();
        scrnMsg.xxPopPrm_I9.clear();
    }

    /**
     * splitCOA9Seg
     * @param scrnMsg NFBL2040BMsg
     * @return boolean
     */
    private static boolean splitCOA9Seg(NFBL2040BMsg scrnMsg) {

        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).xxCmntTxt_A1)) {
            return true;
        }
        String coa[] = scrnMsg.A.no(idx).xxCmntTxt_A1.getValue().split("\\.", PARAM_INDEX_9);
        if(coa.length != PARAM_INDEX_9) {
            String errMsg = "9 segments format";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            scrnMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[]{errMsg});
            return false;
        }

        int index = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I1, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I2, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I3, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I4, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I5, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I6, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I7, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I8, coa[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_I9, coa[index++]);

        return true;
    }
    // END 2016/08/02 T.Tsuchida [QC#12040,ADD]

    /**
     * Method name: initAppFracDigit
     * <dd>The method explanation: Init App Frac Digit.
     * <dd>Remarks:
     * @param bMsg EZDBMsg
     */
    public static void initAppFracDigit(EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // HEADER tab
        scrnMsg.invAmt.setAppFracDigit(FRAC_DIGIT_2);
        scrnMsg.hdrPmtAmt.setAppFracDigit(FRAC_DIGIT_2);
        scrnMsg.entPoDtlDealNetAmt_TO.setAppFracDigit(FRAC_DIGIT_2);
        scrnMsg.acOcTotDiscAmt.setAppFracDigit(FRAC_DIGIT_2);
        // LINES tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxInvAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setAppFracDigit(FRAC_DIGIT_2);
            // START 2018/08/03 [QC#27563, ADD]
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setAppFracDigit(FRAC_DIGIT_2);
            // END   2018/08/03 [QC#27563, ADD]
        }
        // START 2016/07/07 [QC#11331,MOD]
        scrnMsg.xxTotPrcAmt_F.setAppFracDigit(FRAC_DIGIT_2);
        // END   2016/07/07 [QC#11331,MOD]
        // HOLD tab
        scrnMsg.invAmt_HH.setAppFracDigit(2);
        // DISTRIBUTIONS tab
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).jrnlFuncDrAmt_D1.setAppFracDigit(FRAC_DIGIT_2);
            scrnMsg.D.no(i).jrnlFuncCrAmt_D1.setAppFracDigit(FRAC_DIGIT_2);
        }
    }

    // START 2016/07/29 K.Kojima [QC#12665,ADD]
    /**
     * @param bMsg EZDBMsg
     */
    public static void clearRowsBG_NoSelectTab(EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        if (!scrnMsg.xxDplyTab_01.getValue().equals(TAB_HOLDS)) {
            clearRowsBG_H(bMsg);
        }
        if (!scrnMsg.xxDplyTab_02.getValue().equals(TAB_LINES)) {
            clearRowsBG_A(bMsg);
        }
        if (!scrnMsg.xxDplyTab_02.getValue().equals(TAB_DISTRIBUTIONS)) {
            clearRowsBG_D(bMsg);
        }
    }
    // END 2016/07/29 K.Kojima [QC#12665,ADD]

    // START 2016/08/10 Y.Tsuchimoto [QC#12970 ADD]
    /**
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedForAfterApprove(NFBL2040BMsg scrnMsg) {
        // HEADER TAB
        scrnMsg.dplyVndNm.setInputProtected(true);
        scrnMsg.apVndCd.setInputProtected(true);
        scrnMsg.apVndInvNum.setInputProtected(true);
        scrnMsg.invDt.setInputProtected(true);
        scrnMsg.xxChkBox_PA.setInputProtected(true);
        scrnMsg.prntVndCd.setInputProtected(true);
        // QC#21653 Delete cmInvMatchCd_S
        // scrnMsg.cmInvMatchCd_S.setInputProtected(true);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum_HT.setInputProtected(true);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.invAmt.setInputProtected(true);
        scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
        scrnMsg.delyOrdNum_DH.setInputProtected(true);
        scrnMsg.invHdrDescTxt.setInputProtected(true);
        scrnMsg.xxChkBox_PO.setInputProtected(true);
        // START 2018/02/26 [QC#23505, ADD]
        scrnMsg.xxChkBox_CR.setInputProtected(true);
        // END   2018/02/26 [QC#23505, ADD]
        
        scrnMsg.dplyVndNm_L.setInputProtected(true);
        scrnMsg.apVndCd_L.setInputProtected(true);
        scrnMsg.prntVndCd_L.setInputProtected(true);
        scrnMsg.xxLinkProt_V2.setInputProtected(true);
        scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
        scrnMsg.delyOrdNum_DA.setInputProtected(true);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum_H.setInputProtected(true);
        scrnMsg.xxLinkProt_V3.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        scrnMsg.rwsNum_DH.setInputProtected(true);
        scrnMsg.rwsNum_DA.setInputProtected(true);
        // START 2017/01/12 E.Kameishi [QC#16950,ADD]
        scrnMsg.termNetDueDt.setInputProtected(true);
        // END 2017/01/12 E.Kameishi [QC#16950,ADD]

        // LINES TAB
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            // END   2018/02/27 [QC#23505, ADD]
            scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
            // QC#21666
            scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
            // END 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
            // END   2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
        }
        
        // START 2016/09/23 Y.Tsuchimoto [QC#12043,ADD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);     // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);      // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);
            } else {
                scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false);
            }
            scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(true);              // Delete Row Checkbox
            // START 2018/03/27 [QC#24277, ADD]
            scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);       // Line Number
            // END   2018/03/27 [QC#24277, ADD]
            scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(true);              // Hold Name
            scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);              // Hold Date
            scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(true);           // Hold Reason
            scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);           // Hold By
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);     // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);      // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
               scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);    // Release Reason
               scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);  // Release Note
            } else {
               scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);         // Release Checkbox
               scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);   // Release Reason
               scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false); // Release Note
            }
            scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);        // Released By
            scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);           // Released Date
        }
        // END   2016/09/23 Y.Tsuchimoto [QC#12043,ADD]
    }
    // END   2016/08/10 Y.Tsuchimoto [QC#12970 ADD]

    // START 2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
    /**
     * setButtonAfterHold
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonAfterHold(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
        // HOLDS TAB
        // START 2016/09/23 Y.Tsuchimoto [QC#12043,DEL]
        //if (ACCT_INV_STS.ENTERED.equals(scrnMsg.acctInvStsCd.getValue())) {
        //    handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, true);
        //    handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, true);
        //    handler.setButtonEnabled(BTN_NORMAL_HOLD, true);
        //} else {
        //    handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        //    handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);
        //    handler.setButtonEnabled(BTN_NORMAL_HOLD, false);
        //}
        // END   2016/09/23 Y.Tsuchimoto [QC#12043,DEL]
    }

    /**
     * setButtonAfterHold
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonAfterRelease(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
        // HOLDS TAB
        // START 2016/09/23 Y.Tsuchimoto [QC#12043,DEL]
        //if (ACCT_INV_STS.ENTERED.equals(scrnMsg.acctInvStsCd.getValue())) {
        //    handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, true);
        //    handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, true);
        //    handler.setButtonEnabled(BTN_NORMAL_HOLD, true);
        //} else {
        //    handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        //    handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);
        //    handler.setButtonEnabled(BTN_NORMAL_HOLD, false);
        //}
        // END   2016/09/23 Y.Tsuchimoto [QC#12043,DEL]
    }

    /**
     * setInputProtectedForAfterRelease
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedForAfterRelease(NFBL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);     // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);      // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);
            } else {
                scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false);
            }
        }
    }

    /**
     * setInputProtectedForAfterInsertAndDelete
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedForAfterInsertAndDelete(NFBL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                // Manual Hold
                scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(false);            // Delete Row Checkbox
                // START 2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);      // Line Number
                // END   2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(false);            // Hold Name
                scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);             // Hold Date
                scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(false);         // Hold Reason
                scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);          // Hold By
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);        // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);         // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
                scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);       // Released By
                scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);          // Released Date
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);       // Release Reason
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);     // Release Note
           } else {
               // Auto Hold
               // START 2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);       // Line Number
               // END   2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(true);              // Hold Name
               scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);              // Hold Date
               scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(true);           // Hold Reason
               scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);           // Hold By
               if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                   // START 2017/01/13 E.Kameishi [QC#16948,MOD]
                   String acctInvSts = scrnMsg.acctInvStsCd.getValue();
                   if (!acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
                       // START 2020/03/16 [QC#55993, MOD]
                       if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                           scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);     // Release Checkbox
                       } else {
                           scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);      // Release Checkbox
                       }
                       // END   2020/03/16 [QC#55993, MOD]
                       scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);    // Release Reason
                       scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);  // Release Note
                   }
                   // END 2017/01/13 E.Kameishi [QC#16948,MOD]
               } else {
                   scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);         // Release Checkbox
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);   // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false); // Release Note
               }
               scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);        // Released By
               scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);           // Released Date
           }
        }
    }
    /**
     * setInputProtectedForAfterHold
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedForAfterHold(NFBL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);        // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);         // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);
            } else {
                scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false);
            }
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                // Manual Hold
                scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(false);            // Delete Row Checkbox
                // START 2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);      // Line Number
                // END   2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(false);            // Hold Name
                scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);             // Hold Date
                scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(false);         // Hold Reason
                scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);          // Hold By
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);        // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);         // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
                scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);       // Released By
                scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);          // Released Date
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);       // Release Reason
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);     // Release Note
           } else {
               // Auto Hold
               scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(true);              // Delete Row Checkbox
               // START 2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);       // Line Number
               // END   2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(true);              // Hold Name
               scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);              // Hold Date
               scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(true);           // Hold Reason
               scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);           // Hold By
               if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                   // START 2020/03/16 [QC#55993, MOD]
                   if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                       scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);     // Release Checkbox
                   } else {
                       scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);      // Release Checkbox
                   }
                   // END   2020/03/16 [QC#55993, MOD]
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);    // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);  // Release Note
               } else {
                   scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);         // Release Checkbox
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);   // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false); // Release Note
               }
               scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);        // Released By
               scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);           // Released Date
           }
        }
    }

    /**
     * setInputResetForAfterHold
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputResetForAfterHold(NFBL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(i).xxInsUpdDelFlg_H1, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * commonAddCheckHoldItem
     * @param scrnMsg NFBL2040BMsg
     */
    public static void commonAddCheckHoldItem(NFBL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldDt_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRsnCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldPsnCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelPsnCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelDt_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelRsnCd_H1);
            scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelCmntTxt_H1);
        }
    }
    // END   2016/08/26 Y.Tsuchimoto [QC#12043,ADD]

    // START 2016/09/14 Y.Tsuchimoto [QC#13333,ADD]
    /**
     * setInputProtectedNotFromOthScrForEditForLinesTab
     * @param scrnMsg NFBL2040BMsg
     * @param changeFlg boolean
     */
    public static void setInputProtectedNotFromOthScrForEditForLinesTab(NFBL2040BMsg scrnMsg, boolean changeFlg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2018/03/16 [QC#24089, ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
            // START 2018/04/04 [QC#20316, MOD]
            //        && isPOLineClosed(scrnMsg, scrnMsg.A.no(i))) {
                    && !isEditableLine(scrnMsg, scrnMsg.A.no(i))) {
            // END   2018/04/04 [QC#20316, MOD]
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
                // START 2018/03/20 [QC#24089, ADD]
                scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
                // END   2018/03/20 [QC#24089, ADD]
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                // END   2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
                continue;
            }
            // END   2018/03/16 [QC#24089, ADD]

            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
            if (!changeFlg && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                // START 2019/11/02 [QC#54439, MOD]
                // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                // END 2019/11/02 [QC#54439, MOD]
                scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                // START 2018/01/25 K.Kameoka [QC#21696,MOD]
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                // END   2018/01/25 K.Kameoka [QC#21696,MOD]
                scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                // END   2018/08/03 [QC#27563, ADD]
                // START 2018/02/26 [QC#23505, ADD]
                scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                // END   2018/02/26 [QC#23505, ADD]
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
                continue;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && !AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {

                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                // START QC#25902,QC#25190,QC#25141
                if (AP_LINE_TP.MISC.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                } else {
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                }
                // END QC#25902,QC#25190,QC#25141
                // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                // START 2016/11/15 [QC#15904, MOD]
                scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                // END 2016/11/15 [QC#15904, MOD]
                // START 2019/11/02 [QC#54439, MOD]
                // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                // END 2019/11/02 [QC#54439, MOD]
                // START 2016/11/14 Y.Tsuchimoto [QC#15773,MOD]
                scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                // END   2016/11/14 Y.Tsuchimoto [QC#15773,MOD]
                // START 2016/11/15 [QC#15904, MOD]
                // START 2018/01/25 K.Kameoka [QC#21696,MOD]
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                // END   2018/01/25 K.Kameoka [QC#21696,MOD]
                scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
                // QC#21666
                scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                // END   2018/08/03 [QC#27563, ADD]
                // START 2018/02/26 [QC#23505, ADD]
                scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                // END   2018/02/26 [QC#23505, ADD]
                // END 2016/11/15 [QC#15904, MOD]
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]

                // START 2017/12/26 [QC#23022, MOD]
                if (!AP_LINE_TP.FREIGHT.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).mdseCd_A1.clear();
                }
                // END 2017/12/26 [QC#23022, MOD]
                // START 2018/03/07 J.Kim [QC#24636,DEL]
                // scrnMsg.A.no(i).mdseDescShortTxt_A1.clear();
                scrnMsg.A.no(i).mdseCd_A1.clear();
                // END 2018/03/07 J.Kim [QC#24636,DEL]
                // START   2017/02/23 E.Kameishi [QC#12988,ADD]
                scrnMsg.A.no(i).vndUomCd_A1.clear();
                // START QC#25902,QC#25190,QC#25141
                if (!AP_LINE_TP.MISC.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.clear();
                }
                // END QC#25902,QC#25190,QC#25141
                scrnMsg.A.no(i).poQty_A1.clear();
                scrnMsg.A.no(i).invRcvQty_A1.clear();
                scrnMsg.A.no(i).apRejQty_A1.clear();
                scrnMsg.A.no(i).apBillQty_A1.clear();
                scrnMsg.A.no(i).dsContrNum_A1.clear();
                scrnMsg.A.no(i).custDlrCd_A1.clear();
                scrnMsg.A.no(i).serNum_A1.clear();
                scrnMsg.A.no(i).csmpInvNum_A1.clear();
                scrnMsg.A.no(i).xxInstlFullAddr_A1.clear(); 
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.clear();
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.clear();
                // END   2018/08/03 [QC#27563, ADD]
                // START 2018/02/26 [QC#23505, ADD]
                scrnMsg.A.no(i).poNum_A1.clear();
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.clear();
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.clear();
                scrnMsg.A.no(i).delyOrdNum_A2.clear();
                scrnMsg.A.no(i).rwsNum_A1.clear();
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.clear();
                // END   2018/02/26 [QC#23505, ADD]
                // END   2017/02/23 E.Kameishi [QC#12988,ADD]
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.clear();
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
            } else {
                if (scrnMsg.A.no(i).apLineTpCd_A1.isInputProtected() && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                    // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                    //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                    // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                    scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                    // START 2019/11/02 [QC#54439, MOD]
                    // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                    setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                    // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                    // END 2019/11/02 [QC#54439, MOD]
                    scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                    // START 2018/01/25 K.Kameoka [QC#21696,MOD]
                    scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                    // END   2018/01/25 K.Kameoka [QC#21696,MOD]
                    // START 2021/04/22 R.Azucena [QC#56829, ADD]
                    scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                    // END 2021/04/22 R.Azucena [QC#56829, ADD]
                    scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                    scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                    scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                    scrnMsg.A.no(i).apBillQty_A1.setInputProtected(false);
                    scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                    // START 2018/08/03 [QC#27563, ADD]
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                    // END   2018/08/03 [QC#27563, ADD]
                    // START 2018/02/26 [QC#23505, ADD]
                    scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                    // START 2020/03/23 [QC#53413, ADD]
                    scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                    scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                    scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                    // END   2020/03/23 [QC#53413, ADD]
                    scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                    // END   2018/02/26 [QC#23505, ADD]
                    // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                    scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                    // END 2018/06/26 S.Katsuma [QC#24617,ADD]
                    continue;
                // START QC#25902,QC#25190,QC#25141
                } else if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.MISC.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    // Misc
                    scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                    // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                    //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                    // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                    scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                    // START 2019/11/02 [QC#54439, MOD]
                    // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                    setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                    // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                    // END 2019/11/02 [QC#54439, MOD]
                    scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                    // START 2021/04/22 R.Azucena [QC#56829, ADD]
                    scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                    // END 2021/04/22 R.Azucena [QC#56829, ADD]
                    scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                    scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                    scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
                    scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                    scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                    // START 2018/08/03 [QC#27563, ADD]
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                    // END   2018/08/03 [QC#27563, ADD]
                    scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                    // START 2020/03/23 [QC#53413, ADD]
                    scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                    scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                    scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                    // END   2020/03/23 [QC#53413, ADD]
                    scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                    // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                    scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                    // END 2018/06/26 S.Katsuma [QC#24617,ADD]
                // END QC#25902,QC#25190,QC#25141
                } else {
                    scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).mdseCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                    // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                    //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                    // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                    scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(false);
                    // START 2019/11/02 [QC#54439, MOD]
                    // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                    setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                    // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                    // END 2019/11/02 [QC#54439, MOD]
                    // START 2016/11/14 Y.Tsuchimoto [QC#15773,MOD]
                    scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                    // END   2016/11/14 Y.Tsuchimoto [QC#15773,MOD]
                    scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).poQty_A1.setInputProtected(false);
                    scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(false);
                    scrnMsg.A.no(i).apRejQty_A1.setInputProtected(false);
                    scrnMsg.A.no(i).apBillQty_A1.setInputProtected(false);
                    // QC#21666
                    scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(false);
                    // START 2021/04/22 R.Azucena [QC#56829, ADD]
                    scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                    // END 2021/04/22 R.Azucena [QC#56829, ADD]
                    scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(false);
                    scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).serNum_A1.setInputProtected(false);
                    scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(false);
                    scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(false);
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                    // START 2018/08/03 [QC#27563, ADD]
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                    // END   2018/08/03 [QC#27563, ADD]
                    // START 2018/02/26 [QC#23505, ADD]
                    scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                    // START 2020/03/23 [QC#53413, ADD]
                    scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                    scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                    scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                    scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                    // END   2020/03/23 [QC#53413, ADD]
                    scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                    // END   2018/02/26 [QC#23505, ADD]
                    // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                    scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                    // END 2018/06/26 S.Katsuma [QC#24617,ADD]
                }
            }
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
        }
    }

    /**
     * setButtonAfterChangeApLineTpCd
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     * @param changeFlg boolean
     */
    public static void setButtonNotFromOthScrForEditForEditForLinesTab(EZDCommonHandler handler, NFBL2040BMsg scrnMsg, boolean changeFlg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled(BTN_NORMAL_CHARGE_ACCOUNT, i, true);
            if (!changeFlg && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                handler.setButtonEnabled(BTN_NORMAL_MDSE, i, true);
                handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, i, true);
                continue;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && !AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                handler.setButtonEnabled(BTN_NORMAL_MDSE, i, false);
                handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, i, false);
            } else {
                handler.setButtonEnabled(BTN_NORMAL_MDSE, i, true);
                handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, i, true);
            }
        }
    }
    // END   2016/09/14 Y.Tsuchimoto [QC#13333,ADD]

    // START 2016/09/15 Y.Tsuchimoto [QC#13156,ADD]
    /**
     * setInputProtectedNotFromOthScrForEditForSearchLinesTab
     * @param scrnMsg NFBL2040BMsg
     * @param multiFlg boolean
     */
    // START 2020/03/23 [QC#53413, MOD]
    //public static void setInputProtectedNotFromOthScrForEditForSearchLinesTab(NFBL2040BMsg scrnMsg) {
    public static void setInputProtectedNotFromOthScrForEditForSearchLinesTab(NFBL2040BMsg scrnMsg, boolean multiFlg) {

        scrnMsg.xxLinkProt_V4.setInputProtected(multiFlg);
        scrnMsg.poNum_L.setInputProtected(multiFlg);
        scrnMsg.delyOrdNum_LA.setInputProtected(multiFlg);
        scrnMsg.delyOrdNum_L.setInputProtected(multiFlg);
        scrnMsg.rwsNum_LA.setInputProtected(multiFlg);
        scrnMsg.rwsNum_L.setInputProtected(multiFlg);
        // END   2020/03/23 [QC#53413, MOD]
        // add start 2022/02/15 QC#57090
        scrnMsg.vndRtrnNum_LA.setInputProtected(multiFlg);
        scrnMsg.vndRtrnNum_L.setInputProtected(multiFlg);
        if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            scrnMsg.vndRtrnNum_LA.setInputProtected(true);
            scrnMsg.vndRtrnNum_L.setInputProtected(true);
        }
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            scrnMsg.rwsNum_LA.setInputProtected(true);
            scrnMsg.rwsNum_L.setInputProtected(true);
        }
        // add end 2022/02/15 QC#57090

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2018/03/16 [QC#24089, ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1) && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())
            // START 2018/04/04 [QC#20316, MOD]
            //        && isPOLineClosed(scrnMsg, scrnMsg.A.no(i))) {
                    && !isEditableLine(scrnMsg, scrnMsg.A.no(i))) {
            // END   2018/04/04 [QC#20316, MOD]
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxCmntTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(true);
                scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(true);
                // START 2018/03/20 [QC#24089, ADD]
                scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
                // END   2018/03/20 [QC#24089, ADD]
                // START 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
                // END 2021/04/22 R.Azucena [QC#56829, ADD]
                scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
                // START 2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
                // END   2018/08/03 [QC#27563, ADD]
                scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
                // START 2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
                scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
                scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
                // END   2020/03/23 [QC#53413, ADD]
                scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
                // START 2018/06/26 S.Katsuma [QC#24617,ADD]
                scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
                // END 2018/06/26 S.Katsuma [QC#24617,ADD]
                continue;
            }
            // END   2018/03/16 [QC#24089, ADD]

            scrnMsg.A.no(i).xxDtlLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
            //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);
            scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);
            // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
            scrnMsg.A.no(i).vndUomCd_A1.setInputProtected(true);
            // START 2019/11/02 [QC#54439, MOD]
            // START 2018/06/26 S.Katsuma [QC#26566,MOD]
            setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
            // END 2018/06/26 S.Katsuma [QC#26566,MOD]
            // END 2019/11/02 [QC#54439, MOD]
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.A.no(i).apAcctDescTxt_A1.setInputProtected(true);
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            // START 2018/01/25 K.Kameoka [QC#21696,MOD]
            scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);
            // END   2018/01/25 K.Kameoka [QC#21696,MOD]
            scrnMsg.A.no(i).poQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).invRcvQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apRejQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).apBillQty_A1.setInputProtected(false);
            // QC#21666
            scrnMsg.A.no(i).xxInvQty_A1.setInputProtected(true);
            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).slsHldQty_A1.setInputProtected(true);
            // END 2021/04/22 R.Azucena [QC#56829, ADD]
            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).custDlrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).csmpInvNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInstlFullAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A2.setInputProtected(true);
            // START 2018/08/03 [QC#27563, ADD]
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A1.setInputProtected(true);
            // END   2018/08/03 [QC#27563, ADD]
            // START 2018/02/26 [QC#23505, ADD]
            scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
            // START 2020/03/23 [QC#53413, ADD]
            scrnMsg.A.no(i).poApvlDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).entPoDtlDealNetAmt_A3.setInputProtected(true);
            scrnMsg.A.no(i).delyOrdNum_A2.setInputProtected(true);
            scrnMsg.A.no(i).rwsNum_A1.setInputProtected(true);
            // END   2020/03/23 [QC#53413, ADD]
            scrnMsg.A.no(i).invCrctDt_A1.setInputProtected(true);
            // END   2018/02/26 [QC#23505, ADD]
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]
        }
    }

    /**
     * setButtonNotFromOthScrForEditForSearchLinesTab
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonNotFromOthScrForEditForSearchLinesTab(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled(BTN_NORMAL_MDSE, i, true);
            handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, i, true);
            handler.setButtonEnabled(BTN_NORMAL_CHARGE_ACCOUNT, i, true);
        }
    }
    
    /**
     * getDisplayMode
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getDisplayMode(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
        List funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);

        // From other screen
        boolean fromOtherScreen = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum_OT)) {
            fromOtherScreen = true;
        }

        boolean protect = true;
        boolean ckFlg = true;
        // Start 2017/01/05 H.Ikeda [QC#12865,MOD]
        if (funcList.contains(FUNC_ID_NFBL2040T010) && funcList.contains(FUNC_ID_NFBL2040T020)) {
            protect = false;
        } else if (funcList.contains(FUNC_ID_NFBL2040T010)) {
            //protect = false;
            protect = true;
            ckFlg = false;
        } else if (funcList.contains(FUNC_ID_NFBL2040T020)) {
            //protect = true;
            protect = false;
        }
        if (ckFlg) {
            if (ZYPCommonFunc.hasValue(scrnMsg.poStsCd_HD.getValue()) && PO_HDR_STS.CLOSED.equals(scrnMsg.poStsCd_HD.getValue())) {
                //protect = true;
                protect = false;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd.getValue())) {
                int intAcctInvStsCd = Integer.parseInt(scrnMsg.acctInvStsCd.getValue());
                int intAcctInvStsCdAuthorized = Integer.parseInt(ACCT_INV_STS.AUTHORIZED);
                if (intAcctInvStsCd >= intAcctInvStsCdAuthorized) {
                    //protect = true;
                    protect = false;
                }
            }
        }

        String rtnMode = null;
        if (fromOtherScreen) {
            if (protect) {
                rtnMode = DISPLAY_MODE_OTH_SCR_REF;
                //rtnMode = DISPLAY_MODE_OTH_SCR_EDIT;
            } else {
                rtnMode = DISPLAY_MODE_OTH_SCR_EDIT;
                //rtnMode = DISPLAY_MODE_OTH_SCR_REF;
            }
        } else {
            if (protect) {
                rtnMode = DISPLAY_MODE_NOT_OTH_SCR_REF;
                //rtnMode = DISPLAY_MODE_NOT_OTH_SCR_EDIT;
            } else {
                rtnMode = DISPLAY_MODE_NOT_OTH_SCR_EDIT;
                //rtnMode = DISPLAY_MODE_NOT_OTH_SCR_REF;
            }
        }
        // End   2017/01/05 H.Ikeda [QC#12865,MOD]
        initAppFracDigit(scrnMsg);

        return rtnMode;
    }

    /**
     * setInputProtectedNotFromOthScrForEditForInit
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedNotFromOthScrForEditForInit(NFBL2040BMsg scrnMsg) {

        // Header
        scrnMsg.cmApInvTpCd_S.setInputProtected(false);
        scrnMsg.xxLinkProt_V1.setInputProtected(true);
        scrnMsg.poNum.setInputProtected(true);
        scrnMsg.delyOrdNum_HA.setInputProtected(true);
        scrnMsg.delyOrdNum_H.setInputProtected(true);
        scrnMsg.rwsNum_H.setInputProtected(true);
        scrnMsg.rwsNum_HA.setInputProtected(true);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum.setInputProtected(true);
        scrnMsg.vndRtrnNum_HA.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        // HEADER tab
        // Line 1
        scrnMsg.dplyVndNm_L.setInputProtected(true);
        scrnMsg.dplyVndNm.setInputProtected(true);
        scrnMsg.apVndCd_L.setInputProtected(true);
        scrnMsg.apVndCd.setInputProtected(true);
        scrnMsg.apVndInvNum.setInputProtected(true);
        scrnMsg.invDt.setInputProtected(true);
        scrnMsg.xxChkBox_PA.setInputProtected(true);
        scrnMsg.apPmtChkNum.setInputProtected(true);
        // Line 2
        scrnMsg.prntVndCd_L.setInputProtected(true);
        scrnMsg.prntVndCd.setInputProtected(true);
        // QC#21653 Delete cmInvMatchCd_S
        // scrnMsg.cmInvMatchCd_S.setInputProtected(true);
        scrnMsg.xxLinkProt_V2.setInputProtected(true);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum_HT.setInputProtected(true);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.invAmt.setInputProtected(true);
        // QC#21640: Clear GUIAttribute
        scrnMsg.clearGUIAttribute(SCRN_ID_00, ID_LINK_TERMS);
        scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
        scrnMsg.vndPmtTermCd.setInputProtected(true);
        scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
        scrnMsg.xxChkBox_HO.setInputProtected(true);
        scrnMsg.vndPmtMethCd.setInputProtected(true);
        scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
        // Line 3
        scrnMsg.poApvlDt.setInputProtected(true);
        scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
        scrnMsg.delyOrdNum_DA.setInputProtected(true);
        scrnMsg.delyOrdNum_DH.setInputProtected(true);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum_H.setInputProtected(true);
        scrnMsg.xxLinkProt_V3.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        scrnMsg.rwsNum_DH.setInputProtected(true);
        scrnMsg.rwsNum_DA.setInputProtected(true);
        scrnMsg.invHdrDescTxt.setInputProtected(true);
        scrnMsg.xxChkBox_PO.setInputProtected(true);
        // START 2018/02/26 [QC#23505, ADD]
        scrnMsg.xxChkBox_CR.setInputProtected(true);
        // END   2018/02/26 [QC#23505, ADD]
        scrnMsg.acctInvStsDescTxt.setInputProtected(true);
        // Line 4
        scrnMsg.pmtDt.setInputProtected(true);
        scrnMsg.hdrPmtAmt.setInputProtected(true);
        scrnMsg.termNetDueDt.setInputProtected(true);
        // Line 5
        scrnMsg.acOcTotDiscAmt.setInputProtected(true);
        scrnMsg.apInvCatgDescTxt.setInputProtected(true);
        scrnMsg.acctDt.setInputProtected(true);
        // START 2020/03/23 [QC#53413, ADD]
        scrnMsg.xxChkBox_MP.setInputProtected(true);
        // Line Tub
        scrnMsg.xxLinkProt_V4.setInputProtected(true);
        scrnMsg.poNum_L.setInputProtected(true);
        scrnMsg.delyOrdNum_LA.setInputProtected(true);
        scrnMsg.delyOrdNum_L.setInputProtected(true);
        scrnMsg.rwsNum_LA.setInputProtected(true);
        scrnMsg.rwsNum_L.setInputProtected(true);
        // END   2020/03/23 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        scrnMsg.vndRtrnNum_LA.setInputProtected(true);
        scrnMsg.vndRtrnNum_L.setInputProtected(true);
        // add end 2022/02/15 QC#57090
    }

    /**
     * setButtonNotFromOthScrForEditForInit
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonNotFromOthScrForEditForInit(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, false);
        handler.setButtonEnabled(BTN_NORMAL_REFRESH, false);
        // START 2018/02/26 [QC#23505, ADD]
        handler.setButtonEnabled(BTN_NORMAL_CORRECTION, false);
        // START QC#25902,QC#25190,QC#25141
        handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, false);
        // END QC#25902,QC#25190,QC#25141
        // END   2018/02/26 [QC#23505, ADD]

        // START 2016/09/26 W.Honda [Unit Test#201,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd)) {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, false);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, true);
        }
        // START 2016/09/26 W.Honda [Unit Test#201,ADD]

        // LINES TAB
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
        // START 2020/03/23 [QC#53413, ADD]
        handler.setButtonEnabled(BTN_NORMAL_LINE_SEARCH, false);
        // END   2020/03/23 [QC#53413, ADD]

        // HOLDS TAB
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);
    }

    /**
     * setInputProtectedNotFromOthScrForEditForChangeCmApInvTpCd
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedNotFromOthScrForEditForChangeCmApInvTpCd(NFBL2040BMsg scrnMsg) {

        if (CM_AP_INV_TP.STANDARD.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            // Header
            scrnMsg.cmApInvTpCd_S.setInputProtected(true);
            scrnMsg.xxLinkProt_V1.setInputProtected(true);
            scrnMsg.poNum.setInputProtected(true);
            scrnMsg.delyOrdNum_HA.setInputProtected(true);
            scrnMsg.delyOrdNum_H.setInputProtected(true);
            scrnMsg.rwsNum_H.setInputProtected(true);
            scrnMsg.rwsNum_HA.setInputProtected(true);
            // START QC#25902,QC#25190,QC#25141
            scrnMsg.vndRtrnNum.setInputProtected(true);
            scrnMsg.vndRtrnNum_HA.setInputProtected(true);
            // END QC#25902,QC#25190,QC#25141
            // HEADER tab
            // Line 1
            scrnMsg.dplyVndNm_L.setInputProtected(false);
            scrnMsg.dplyVndNm.setInputProtected(false);
            scrnMsg.apVndCd_L.setInputProtected(false);
            scrnMsg.apVndCd.setInputProtected(false);
            scrnMsg.apVndInvNum.setInputProtected(false);
            scrnMsg.invDt.setInputProtected(false);
            scrnMsg.xxChkBox_PA.setInputProtected(false);
            scrnMsg.apPmtChkNum.setInputProtected(true);
            // Line 2
            scrnMsg.prntVndCd_L.setInputProtected(false);
            scrnMsg.prntVndCd.setInputProtected(false);
            // QC#21653 Delete cmInvMatchCd_S
            // scrnMsg.cmInvMatchCd_S.setInputProtected(true);
            scrnMsg.xxLinkProt_V2.setInputProtected(true);
            // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.poNum_HT.setInputProtected(true);
            // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.invAmt.setInputProtected(false);
            scrnMsg.vndPmtTermDescTxt_A.setInputProtected(false);
            scrnMsg.vndPmtTermCd.setInputProtected(false);
            scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
            scrnMsg.xxChkBox_HO.setInputProtected(true);
            scrnMsg.vndPmtMethCd.setInputProtected(true);
            scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
            // Line 3
            scrnMsg.poApvlDt.setInputProtected(true);
            scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
            scrnMsg.delyOrdNum_DA.setInputProtected(true);
            scrnMsg.delyOrdNum_DH.setInputProtected(true);
            // START QC#25902,QC#25190,QC#25141
            scrnMsg.vndRtrnNum_H.setInputProtected(true);
            scrnMsg.xxLinkProt_V3.setInputProtected(true);
            // END QC#25902,QC#25190,QC#25141
            scrnMsg.rwsNum_DH.setInputProtected(true);
            scrnMsg.rwsNum_DA.setInputProtected(true);
            scrnMsg.invHdrDescTxt.setInputProtected(false);
            scrnMsg.xxChkBox_PO.setInputProtected(true);
            // START 2018/02/26 [QC#23505, ADD]
            scrnMsg.xxChkBox_CR.setInputProtected(true);
            // END   2018/02/26 [QC#23505, ADD]
            scrnMsg.acctInvStsDescTxt.setInputProtected(true);
            // Line 4
            scrnMsg.pmtDt.setInputProtected(true);
            scrnMsg.hdrPmtAmt.setInputProtected(true);
            // START 2017/01/12 E.Kameishi [QC#16950,MOD]
            scrnMsg.termNetDueDt.setInputProtected(false);
            // END 2017/01/12 E.Kameishi [QC#16950,MOD]
            // Line 5
            scrnMsg.acOcTotDiscAmt.setInputProtected(true);
            scrnMsg.apInvCatgDescTxt.setInputProtected(true);
            scrnMsg.acctDt.setInputProtected(true);
            // START 2020/03/23 [QC#53413, ADD]
            scrnMsg.xxChkBox_MP.setInputProtected(true);
            // END   2020/03/23 [QC#53413, ADD]
        } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            // START 2018/09/26 [QC#28099, MOD]
            // Header
            scrnMsg.cmApInvTpCd_S.setInputProtected(true);
            scrnMsg.xxLinkProt_V1.setInputProtected(false);
            scrnMsg.poNum.setInputProtected(false);
            scrnMsg.delyOrdNum_HA.setInputProtected(false);
            scrnMsg.delyOrdNum_H.setInputProtected(false);
            // START QC#25902,QC#25190,QC#25141
            scrnMsg.vndRtrnNum.setInputProtected(false);
            scrnMsg.vndRtrnNum_HA.setInputProtected(false);
            scrnMsg.vndRtrnNum_H.setInputProtected(true);
            scrnMsg.xxLinkProt_V3.setInputProtected(true);
            // END QC#25902,QC#25190,QC#25141
            // START 2018/02/02 [QC#21703, MOD]
            // scrnMsg.rwsNum_H.setInputProtected(false);
            // scrnMsg.rwsNum_HA.setInputProtected(false);
            scrnMsg.rwsNum_H.setInputProtected(true);
            scrnMsg.rwsNum_HA.setInputProtected(true);
            // END   2018/02/02 [QC#21703, MOD]

            // HEADER tab
            // Line 1
            scrnMsg.dplyVndNm_L.setInputProtected(true);
            scrnMsg.dplyVndNm.setInputProtected(true);
            scrnMsg.apVndCd_L.setInputProtected(true);
            scrnMsg.apVndCd.setInputProtected(true);
            scrnMsg.apVndInvNum.setInputProtected(true);
            scrnMsg.invDt.setInputProtected(true);
            scrnMsg.xxChkBox_PA.setInputProtected(true);
            scrnMsg.apPmtChkNum.setInputProtected(true);
            // Line 2
            scrnMsg.prntVndCd_L.setInputProtected(true);
            scrnMsg.prntVndCd.setInputProtected(true);
            // QC#21653 Delete cmInvMatchCd_S
            // scrnMsg.cmInvMatchCd_S.setInputProtected(true);
            scrnMsg.xxLinkProt_V2.setInputProtected(true);
            // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.poNum_HT.setInputProtected(true);
            // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.invAmt.setInputProtected(true);
            scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
            scrnMsg.vndPmtTermCd.setInputProtected(true);
            scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
            scrnMsg.xxChkBox_HO.setInputProtected(true);
            scrnMsg.vndPmtMethCd.setInputProtected(true);
            scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
            // Line 3
            scrnMsg.poApvlDt.setInputProtected(true);
            scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
            scrnMsg.delyOrdNum_DA.setInputProtected(true);
            scrnMsg.delyOrdNum_DH.setInputProtected(true);
            // START QC#25902,QC#25190,QC#25141
            scrnMsg.vndRtrnNum_H.setInputProtected(true);
            scrnMsg.xxLinkProt_V3.setInputProtected(true);
            // END QC#25902,QC#25190,QC#25141
            scrnMsg.rwsNum_DH.setInputProtected(true);
            scrnMsg.rwsNum_DA.setInputProtected(true);
            scrnMsg.invHdrDescTxt.setInputProtected(true);
            scrnMsg.xxChkBox_PO.setInputProtected(true);
            // START 2018/02/26 [QC#23505, ADD]
            scrnMsg.xxChkBox_CR.setInputProtected(true);
            // END   2018/02/26 [QC#23505, ADD]
            scrnMsg.acctInvStsDescTxt.setInputProtected(true);
            // Line 4
            scrnMsg.pmtDt.setInputProtected(true);
            scrnMsg.hdrPmtAmt.setInputProtected(true);
            // START 2017/01/12 E.Kameishi [QC#16950,MOD]
            scrnMsg.termNetDueDt.setInputProtected(true);
            // END 2017/01/12 E.Kameishi [QC#16950,MOD]
            // Line 5
            scrnMsg.acOcTotDiscAmt.setInputProtected(true);
            scrnMsg.apInvCatgDescTxt.setInputProtected(true);
            scrnMsg.acctDt.setInputProtected(true);
            // END   2018/09/26 [QC#28099, MOD]
            // START 2020/03/23 [QC#53413, ADD]
            scrnMsg.xxChkBox_MP.setInputProtected(true);
            // END   2020/03/23 [QC#53413, ADD]
        } else if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            // Header
            scrnMsg.cmApInvTpCd_S.setInputProtected(true);
            scrnMsg.xxLinkProt_V1.setInputProtected(false);
            scrnMsg.poNum.setInputProtected(false);
            scrnMsg.delyOrdNum_HA.setInputProtected(false);
            scrnMsg.delyOrdNum_H.setInputProtected(false);
            // START QC#25902,QC#25190,QC#25141
            scrnMsg.vndRtrnNum_H.setInputProtected(false);
            scrnMsg.xxLinkProt_V3.setInputProtected(false);
            // END QC#25902,QC#25190,QC#25141
            scrnMsg.rwsNum_H.setInputProtected(false);
            scrnMsg.rwsNum_HA.setInputProtected(false);

            // HEADER tab
            // Line 1
            scrnMsg.dplyVndNm_L.setInputProtected(true);
            scrnMsg.dplyVndNm.setInputProtected(true);
            scrnMsg.apVndCd_L.setInputProtected(true);
            scrnMsg.apVndCd.setInputProtected(true);
            scrnMsg.apVndInvNum.setInputProtected(true);
            scrnMsg.invDt.setInputProtected(true);
            scrnMsg.xxChkBox_PA.setInputProtected(true);
            scrnMsg.apPmtChkNum.setInputProtected(true);
            // Line 2
            scrnMsg.prntVndCd_L.setInputProtected(true);
            scrnMsg.prntVndCd.setInputProtected(true);
            // QC#21653 Delete cmInvMatchCd_S
            // scrnMsg.cmInvMatchCd_S.setInputProtected(true);
            scrnMsg.xxLinkProt_V2.setInputProtected(true);
            // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.poNum_HT.setInputProtected(true);
            // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.invAmt.setInputProtected(true);
            scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
            scrnMsg.vndPmtTermCd.setInputProtected(true);
            scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
            scrnMsg.xxChkBox_HO.setInputProtected(true);
            scrnMsg.vndPmtMethCd.setInputProtected(true);
            scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
            // Line 3
            scrnMsg.poApvlDt.setInputProtected(true);
            scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
            scrnMsg.delyOrdNum_DA.setInputProtected(true);
            scrnMsg.delyOrdNum_DH.setInputProtected(true);
            // START QC#25902,QC#25190,QC#25141
            scrnMsg.vndRtrnNum_H.setInputProtected(true);
            scrnMsg.xxLinkProt_V3.setInputProtected(true);
            // END QC#25902,QC#25190,QC#25141
            scrnMsg.rwsNum_DH.setInputProtected(true);
            scrnMsg.rwsNum_DA.setInputProtected(true);
            scrnMsg.invHdrDescTxt.setInputProtected(true);
            scrnMsg.xxChkBox_PO.setInputProtected(true);
            // START 2018/02/26 [QC#23505, ADD]
            scrnMsg.xxChkBox_CR.setInputProtected(true);
            // END   2018/02/26 [QC#23505, ADD]
            scrnMsg.acctInvStsDescTxt.setInputProtected(true);
            // Line 4
            scrnMsg.pmtDt.setInputProtected(true);
            scrnMsg.hdrPmtAmt.setInputProtected(true);
            scrnMsg.termNetDueDt.setInputProtected(true);
            // Line 5
            scrnMsg.acOcTotDiscAmt.setInputProtected(true);
            scrnMsg.apInvCatgDescTxt.setInputProtected(true);
            scrnMsg.acctDt.setInputProtected(true);
            // START 2020/03/23 [QC#53413, ADD]
            scrnMsg.xxChkBox_MP.setInputProtected(true);
            // END   2020/03/23 [QC#53413, ADD]
        }
    }

    /**
     * setButtonNotFromOthScrForEditForChangeCmApInvTpCd
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonNotFromOthScrForEditForChangeCmApInvTpCd(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {

        if (CM_AP_INV_TP.STANDARD.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            // Header
            handler.setButtonEnabled(BTN_NORMAL_SEARCH, false);
            // Lines Tab
            handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, true);
            handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, true);
        }else if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            // Header
            handler.setButtonEnabled(BTN_NORMAL_SEARCH, true);
            // Lines Tab
            // START 2018/09/26 [QC#28099, MOD]
            // handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, true);
            // handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, true);
            handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
            // END   2018/09/26 [QC#28099, MOD]
        }else if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            // Header
            handler.setButtonEnabled(BTN_NORMAL_SEARCH, true);
            // Lines Tab
            handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
        }
        // HOLDS TAB
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, true);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, true);
    }

    /**
     * setInputProtectedNotFromOthScrForEditForSearch
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedNotFromOthScrForEditForSearch(NFBL2040BMsg scrnMsg) {

        // Header
        scrnMsg.cmApInvTpCd_S.setInputProtected(true);
        scrnMsg.xxLinkProt_V1.setInputProtected(true);
        scrnMsg.poNum.setInputProtected(true);
        scrnMsg.delyOrdNum_HA.setInputProtected(true);
        scrnMsg.delyOrdNum_H.setInputProtected(true);
        scrnMsg.rwsNum_H.setInputProtected(true);
        scrnMsg.rwsNum_HA.setInputProtected(true);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum.setInputProtected(true);
        scrnMsg.vndRtrnNum_HA.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        // HEADER tab
        // Line 1
        scrnMsg.dplyVndNm_L.setInputProtected(false);
        scrnMsg.dplyVndNm.setInputProtected(false);
        scrnMsg.apVndCd_L.setInputProtected(false);
        scrnMsg.apVndCd.setInputProtected(false);
        scrnMsg.apVndInvNum.setInputProtected(false);
        scrnMsg.invDt.setInputProtected(false);
        scrnMsg.xxChkBox_PA.setInputProtected(false);
        scrnMsg.apPmtChkNum.setInputProtected(true);
        // Line 2
        scrnMsg.prntVndCd_L.setInputProtected(false);
        scrnMsg.prntVndCd.setInputProtected(false);
        // QC#21653 Delete cmInvMatchCd_S
        // scrnMsg.cmInvMatchCd_S.setInputProtected(false);
        scrnMsg.xxLinkProt_V2.setInputProtected(true);
        // START 2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.poNum_HT.setInputProtected(true);
        // END   2016/10/26 Y.Tsuchimoto [QC#15493,MOD]
        scrnMsg.invAmt.setInputProtected(false);
        // QC#21640 : Disable terms text and link.
        // QC#22348 Start
        scrnMsg.vndPmtTermDescTxt_A.setInputProtected(false);
        scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
        scrnMsg.clearGUIAttribute(SCRN_ID_00, ID_LINK_TERMS);
//        if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
//            scrnMsg.vndPmtTermDescTxt_A.setInputProtected(true);
//            scrnMsg.vndPmtTermDescTxt.setInputProtected(true);
//            EZDGUIAttribute termsLink = new EZDGUIAttribute(SCRN_ID_00, ID_LINK_TERMS);
//            termsLink.setAttribute("style", "text-decoration: none; color:#000000;");
//            scrnMsg.addGUIAttribute(termsLink);
//        } else {
//            scrnMsg.vndPmtTermDescTxt_A.setInputProtected(false);
//            scrnMsg.vndPmtTermDescTxt.setInputProtected(false);
//            scrnMsg.clearGUIAttribute(SCRN_ID_00, ID_LINK_TERMS);
//        }
        // QC#22348 End
        scrnMsg.vndPmtTermCd.setInputProtected(false);
        scrnMsg.xxChkBox_HO.setInputProtected(true);
        scrnMsg.vndPmtMethCd.setInputProtected(true);
        scrnMsg.vndPmtMethDescTxt.setInputProtected(true);
        // Line 3
        scrnMsg.poApvlDt.setInputProtected(true);
        scrnMsg.entPoDtlDealNetAmt_TO.setInputProtected(true);
        scrnMsg.delyOrdNum_DA.setInputProtected(true);
        scrnMsg.delyOrdNum_DH.setInputProtected(true);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.vndRtrnNum_H.setInputProtected(true);
        scrnMsg.xxLinkProt_V3.setInputProtected(true);
        // END QC#25902,QC#25190,QC#25141
        scrnMsg.rwsNum_DH.setInputProtected(true);
        scrnMsg.rwsNum_DA.setInputProtected(true);
        scrnMsg.invHdrDescTxt.setInputProtected(false);
        scrnMsg.xxChkBox_PO.setInputProtected(true);
        // START 2018/02/26 [QC#23505, ADD]
        scrnMsg.xxChkBox_CR.setInputProtected(true);
        // END   2018/02/26 [QC#23505, ADD]
        scrnMsg.acctInvStsDescTxt.setInputProtected(true);
        // Line 4
        scrnMsg.pmtDt.setInputProtected(true);
        scrnMsg.hdrPmtAmt.setInputProtected(true);
        // START 2017/01/12 E.Kameishi [QC#16950,MOD]
        scrnMsg.termNetDueDt.setInputProtected(false);
        // END 2017/01/12 E.Kameishi [QC#16950,MOD]
        // Line 5
        scrnMsg.acOcTotDiscAmt.setInputProtected(true);
        scrnMsg.apInvCatgDescTxt.setInputProtected(true);
        scrnMsg.acctDt.setInputProtected(true);
        // START 2020/03/23 [QC#53413, ADD]
        scrnMsg.xxChkBox_MP.setInputProtected(true);
        // END   2020/03/23 [QC#53413, ADD]
    }

    /**
     * setButtonNotFromOthScrForEditForSearch
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonNotFromOthScrForEditForSearch(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {

        // Header
        handler.setButtonEnabled(BTN_NORMAL_SEARCH, false);

        // LINES
        // START 2016/11/15 [QC#15904, MOD]
        // START 2019/09/02 [QC#52876, MOD]
        // if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
        if (CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())
                || CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
        // END 2019/09/02 [QC#52876, MOD]
            handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, true);
            handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, true);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
            handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
        }
        // END 2016/11/15 [QC#15904, MOD]
    }

    /**
     * setInputProtectedNotFromOthScrForEditCommon
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedNotFromOthScrForEditCommon(NFBL2040BMsg scrnMsg) {

        // LINES TAB
        // START 2016/07/07 [QC#11331,MOD]
        scrnMsg.xxTotPrcAmt_F.setInputProtected(true);
        // END   2016/07/07 [QC#11331,MOD]

        // HOLDS TAB
        scrnMsg.apVndInvNum_HH.setInputProtected(true);
        scrnMsg.invAmt_HH.setInputProtected(true);
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,MOD]
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                // Manual Hold
                scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(false);            // Delete Row Checkbox
                // START 2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);      // Line Number
                // END   2018/03/27 [QC#24277, ADD]
                scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(false);            // Hold Name
                scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);             // Hold Date
                scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(false);         // Hold Reason
                scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);          // Hold By
                // START 2020/03/16 [QC#55993, MOD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);        // Release Checkbox
                } else {
                    scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);         // Release Checkbox
                }
                // END   2020/03/16 [QC#55993, MOD]
                scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);       // Released By
                scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);          // Released Date
                scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);       // Release Reason
                scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);     // Release Note
            } else {
               // Auto Hold
               scrnMsg.H.no(i).xxChkBox_H2.setInputProtected(true);              // Delete Row Checkbox
               // START 2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).apVndInvLineNum_H1.setInputProtected(true);       // Line Number
               // END   2018/03/27 [QC#24277, ADD]
               scrnMsg.H.no(i).pmtHldCd_H1.setInputProtected(true);              // Hold Name
               scrnMsg.H.no(i).pmtHldDt_H1.setInputProtected(true);              // Hold Date
               scrnMsg.H.no(i).pmtHldRsnCd_H1.setInputProtected(true);           // Hold Reason
               scrnMsg.H.no(i).pmtHldPsnCd_H1.setInputProtected(true);           // Hold By
               // START 2016/08/05 Y.Tsuchimoto [QC#12201,MOD]
               if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                   // START 2020/03/16 [QC#55993, MOD]
                   if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
                       scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);     // Release Checkbox
                   } else {
                       scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);      // Release Checkbox
                   }
                   // END   2020/03/16 [QC#55993, MOD]
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(true);    // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(true);  // Release Note
               } else {
                   scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(false);         // Release Checkbox
                   scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);   // Release Reason
                   scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false); // Release Note
               }
               scrnMsg.H.no(i).pmtHldRelPsnCd_H1.setInputProtected(true);        // Released By
               scrnMsg.H.no(i).pmtHldRelDt_H1.setInputProtected(true);           // Released Date
            }
        }
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,MOD]

        // DISTRIBUTIONS TAB
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).xxDtlLineNum_D1.setInputProtected(true); // Line Number
            scrnMsg.D.no(i).xxDtlLineNum_D2.setInputProtected(true); // Distribution Line Number
            scrnMsg.D.no(i).invDt_D1.setInputProtected(true); // Date
            scrnMsg.D.no(i).jrnlFuncDrAmt_D1.setInputProtected(true); // Debit
            scrnMsg.D.no(i).jrnlFuncCrAmt_D1.setInputProtected(true); // Credit
            // START 2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).apAcctDescTxt_D1.setInputProtected(true); // Account Description
            // END   2016/10/06 Y.Tsuchimoto [QC#15036,MOD]
            scrnMsg.D.no(i).xxCmntTxt_D1.setInputProtected(true); // Account Code
        }
        // START 2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
        scrnMsg.xxAllocTpCd_D.setInputProtected(true);    // Distribution(All Lines / Selected Lines)
        // END   2016/08/02 Y.Tsuchimoto [QC#12011,ADD]
    }

    /**
     * setButtonNotFromOthScrForEditCommon
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    // START 2020/03/23 [QC#53413, MOD]
    //public static void setButtonNotFromOthScrForEditCommon(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
    public static void setButtonNotFromOthScrForEditCommon(EZDCommonHandler handler, NFBL2040BMsg scrnMsg, boolean multiFlg) {
    // END   2020/03/23 [QC#53413, MOD]
        // Header
        if (ZYPCommonFunc.hasValue(scrnMsg.apVndCd)
                && ZYPCommonFunc.hasValue(scrnMsg.apVndInvNum)) {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, true);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_ATTACH, false);
        }

        // HEADER tab
        handler.setButtonEnabled(BTN_NORMAL_REFRESH, true);
        // START 2018/02/26 [QC#23505, ADD]
        handler.setButtonEnabled(BTN_NORMAL_CORRECTION, false);
        // START QC#25902,QC#25190,QC#25141
        handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, false);
        // END QC#25902,QC#25190,QC#25141
        // END   2018/02/26 [QC#23505, ADD]

        // START 2016/09/26 W.Honda [Unit Test#201,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.acctInvStsCd)) {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, false);
        } else {
            handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, true);
        }
        // START 2016/09/26 W.Honda [Unit Test#201,ADD]

        // LINES TAB

        // LINES TAB
        // START 2016/11/15 [QC#15904, MOD]
//        /*// S21UserProfileService Instance
//        S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
//        // Global Company Code
//        String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
//        // Get Varchar Const value(NFBL2040_PO_MATCH)
//        String varCharConstValPoMatch = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_PO_MATCH, GLBL_CMPY_CD);
//        if (ZYPCommonFunc.hasValue(varCharConstValPoMatch)) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.cmApInvTpCd_S.getValue())
//            &&  varCharConstValPoMatch.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
//                handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, false);
//                handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, false);
//            } else {
//                handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, true);
//                handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, true);
//            }
//        } else {
//            scrnMsg.setMessageInfo(NFBM0028E);
//            throw new EZDFieldErrorException();
//        }*/
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, true);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, true);
        // END 2016/11/15 [QC#15904, MOD]
        handler.setButtonEnabled(BTN_NORMAL_LINES_TAB_DOWNLOAD_BUTTON, true);

        // START 2020/03/23 [QC#53413, ADD]
        handler.setButtonEnabled(BTN_NORMAL_LINE_SEARCH, multiFlg);
        // END   2020/03/23 [QC#53413, ADD]

        // HOLDS TAB
        // START 2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, true);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, true);
        // END   2016/08/26 Y.Tsuchimoto [QC#12043,ADD]
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        
        // DISTRIBUTIONS TAB
        handler.setButtonEnabled(BTN_NORMAL_HOLD_TAB_DOWNLOAD_BUTTON, true);

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        // START 2017/12/27 [QC#22413, MOD] QC#27025 Update.
//        if (isApprovalUser() && ACCT_INV_STS.ENTERED.equals(scrnMsg.acctInvStsCd.getValue())) {
//            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 1, null);
//        } else {
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
//        }
        // END   2017/12/27 [QC#22413, MOD]
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }
    // END   2016/09/15 Y.Tsuchimoto [QC#13156,ADD]

    // START 2016/11/14 Y.Tsuchimoto [QC#15773,ADD]
    /**
     * getCoaAcctNm
     * @param glblCmpyCd String
     * @param coaAcctCd String
     * @retrun coaAcctDescTxt String
     */
    public static String getCoaAcctDescTxt(String glblCmpyCd, String coaAcctCd) {

        String rtnCoaAcctDescTxt = null;
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(coaAcctCd)) {
            return null;
        }
        COA_ACCTTMsg coaAcct = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(coaAcct.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(coaAcct.coaAcctCd, coaAcctCd);

        coaAcct = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(coaAcct);
        if (coaAcct != null) {
            rtnCoaAcctDescTxt = coaAcct.coaAcctDescTxt.getValue();
        }
        return rtnCoaAcctDescTxt;
    }
    // END   2016/11/14 Y.Tsuchimoto [QC#15773,ADD]
    // START 2017/01/13 E.Kameishi [QC#16949,ADD]
    /**
     * setFocusRule
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setFocusRule(NFBL2040BMsg scrnMsg) {

        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule(SCRN_NM_00, "");
        ZYPGUIFocusRule focusRule = null;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            focusRule = new ZYPGUIFocusRule("xxDtlLineNum_A1#" + i);
            focusRule.setNextId("apLineTpCd_A1#" + i);
            tblFocusRule.addRule(focusRule);

            focusRule = new ZYPGUIFocusRule("apLineTpCd_A1#" + i);
            focusRule.setPrevId("xxDtlLineNum_A1#" + i);
            tblFocusRule.addRule(focusRule);

            if ((i + 1) != scrnMsg.A.getValidCount()) {
                focusRule = new ZYPGUIFocusRule("xxInstlFullAddr_A1#" + i);
                focusRule.setNextId("xxChkBox_A1#" + (i + 1));
                tblFocusRule.addRule(focusRule);
            }
            if (i > 0) {
                focusRule = new ZYPGUIFocusRule("xxChkBox_A1#" + i);
                focusRule.setPrevId("xxInstlFullAddr_A1#" + (i - 1));
                tblFocusRule.addRule(focusRule);
            }
        }
        scrnMsg.addGUIAttribute(tblFocusRule);
    }
    // END 2017/01/13 E.Kameishi [QC#16949,ADD]
    // START 2017/01/13 E.Kameishi [QC#16948,ADD]
    /**
    * checkHoldRelease
    * @param scrnMsg NFBL2040BMsg
    */
    public static void checkHoldRelease(NFBL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRelRsnCd_H1.getValue())) {
                    scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setErrorInfo(1, NLAM0047E, new String[] {"Release Reason" });
                    scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRelRsnCd_H1);
                }
                // QC# 27494 MOD START
                List funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);

                if (PMT_HLD.THEREFORE.equals(scrnMsg.H.no(i).pmtHldCd_H1.getValue())) {
                    // List funcList =
                    // S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);

                    // if (!funcList.contains(FUNC_ID_NFBL2040T030)) {
                    if (!funcList.contains(FUNC_ID_NFBL2040T050)) {
                        scrnMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0254E);
                        scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
                    }

                } else if (PMT_HLD.QUANTITY.equals(scrnMsg.H.no(i).pmtHldCd_H1.getValue()) || PMT_HLD.AMOUNT.equals(scrnMsg.H.no(i).pmtHldCd_H1.getValue())) {

                    if (!funcList.contains(FUNC_ID_NFBL2040T030)) {
                        scrnMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0285E);
                        scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
                    }

                }
                // QC# 27494 MOD START

                // START 2018/03/08 [QC#24275, ADD]
                scrnMsg.addCheckItem(scrnMsg.H.no(i).xxChkBox_H1);
                // END   2018/03/08 [QC#24275, ADD]
            }
        }

        // START 2018/03/08 [QC#24275, ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).apBillQty_A1);
        }
        // END   2018/03/08 [QC#24275, ADD]
    }
    // END 2017/01/13 E.Kameishi [QC#16948,ADD]

    // START 2017/12/27 [QC#22413, ADD]
    /**
     * isApprovalUser
     * @return boolean
     */
    public static boolean isApprovalUser() {
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList.contains(FUNC_ID_NFBL2040T040)) {
            return true;
        }
        return false;
    }

    /**
     * setInputProtectedFromOthScrForEnteredEdit
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedFromOthScrForEnteredEdit(NFBL2040BMsg scrnMsg) {
        setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
        setInputProtectedNotFromOthScrForEditForInit(scrnMsg);
        setInputProtectedNotFromOthScrForEditForChangeCmApInvTpCd(scrnMsg);
        setInputProtectedNotFromOthScrForEditForLinesTab(scrnMsg, false);

        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue()) 
                || CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            setInputProtectedNotFromOthScrForEditForSearch(scrnMsg);
            // START 2018/03/08 [QC#24140, DEL]
            // setInputProtectedNotFromOthScrForEditForSearchLinesTab(scrnMsg);
            // END   2018/03/08 [QC#24140, DEL]
        }
    }

    /**
     * setInputProtectedFromOthScrForEnteredEdit
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonFromOthScrForEnteredEdit(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
        // START 2020/03/23 [QC#53413, MOD]
        //setButtonNotFromOthScrForEditCommon(handler, scrnMsg);
        setButtonNotFromOthScrForEditCommon(handler, scrnMsg, false);
        // END   2020/03/23 [QC#53413, MOD]
        setButtonNotFromOthScrForEditForInit(handler, scrnMsg);
        setButtonNotFromOthScrForEditForChangeCmApInvTpCd(handler, scrnMsg);
        setButtonNotFromOthScrForEditForEditForLinesTab(handler, scrnMsg, false);

        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue()) 
                || CM_AP_INV_TP.PO_MATCH.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            setButtonNotFromOthScrForEditForSearch(handler, scrnMsg);
            setButtonNotFromOthScrForEditForSearchLinesTab(handler, scrnMsg);
        }
    }
    // END   2017/12/27 [QC#22413, ADD]

    // START 2018/02/02 [QC#21703, ADD]
    /**
     * MOD QC#25902,QC#25190,QC#25141
     * Get Param NWAL1130 For Receipt
     * @param scrnMsg NFBL2040BMsg
     * @param glblCmpyCd String
     * @param poNum String
     * @param delyOrdNum String
     * @param rwsNum String
     * @param vndRtrnNum String
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForVndRtrnReceipt(NFBL2040BMsg scrnMsg, String glblCmpyCd, String poNum, String delyOrdNum, String rwsNum, String vndRtrnNum) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Receipt Search";
        params[IDX_2] = getQueryNWAL1130ForVndRtrnReceipt(scrnMsg, glblCmpyCd, poNum, delyOrdNum, rwsNum);
        params[IDX_3] = getWhereListNWAL1130ForVndRtrnReceipt(scrnMsg, glblCmpyCd, poNum, delyOrdNum, rwsNum, vndRtrnNum);
        params[IDX_4] = getColumnListNWAL1130ForVndRtrnReceipt(scrnMsg, glblCmpyCd, poNum, delyOrdNum, rwsNum);
        params[IDX_5] = getSortListNWAL1130ForVndRtrnReceipt(scrnMsg, glblCmpyCd, poNum, delyOrdNum, rwsNum);

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    private static String getQueryNWAL1130ForVndRtrnReceipt(NFBL2040BMsg scrnMsg, String glblCmpyCd, String poNum, String delyOrdNum, String rwsNum) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      CSI.GLBL_CMPY_CD         GLBL_CMPY_CD");
        sb.append("     ,CSI.EZCANCELFLAG         EZCANCELFLAG");
        sb.append("     ,CSI.PO_ORD_NUM           PO_ORD_NUM");
        sb.append("     ,CSI.DELY_ORD_NUM         DELY_ORD_NUM");
        sb.append("     ,'Vendor Return'          ORD_TYPE_NM");
        sb.append(" FROM");
        sb.append("      CM_STK_IN CSI");
        sb.append("     ,INVTY_TRX IT");
        sb.append(" WHERE");
        sb.append("         CSI.GLBL_CMPY_CD    = '").append(glblCmpyCd).append("' ");
        sb.append("     AND CSI.EZCANCELFLAG    = '0'");
        sb.append("     AND CSI.TRX_CD          = '").append(TRX.PURCHASE_STOCK_IN ).append("' ");
        sb.append("     AND CSI.TRX_RSN_CD      = '").append(TRX_RSN.DOMESTIC_VENDOR_RETURN).append("' ");
        sb.append("     AND CSI.GLBL_CMPY_CD    = IT.GLBL_CMPY_CD  (+)");
        sb.append("     AND CSI.DELY_ORD_NUM    = IT.RWS_REF_NUM   (+)");
        sb.append("     AND IT.EZCANCELFLAG (+) = '0'");
        sb.append(" GROUP BY CSI.GLBL_CMPY_CD, CSI.EZCANCELFLAG, CSI.PO_ORD_NUM, CSI.DELY_ORD_NUM");
        sb.append(" UNION ALL SELECT");
        sb.append("      CSI.GLBL_CMPY_CD         GLBL_CMPY_CD");
        sb.append("     ,CSI.EZCANCELFLAG         EZCANCELFLAG");
        sb.append("     ,CSI.PO_ORD_NUM           PO_ORD_NUM");
        sb.append("     ,CSI.DELY_ORD_NUM         DELY_ORD_NUM");
        sb.append("     ,'PO'                     ORD_TYPE_NM");
        sb.append(" FROM");
        sb.append("      CM_STK_IN CSI");
        sb.append("     ,INVTY_TRX IT");
        sb.append("     ,RWS_HDR   RWS");
        sb.append(" WHERE");
        sb.append("         CSI.GLBL_CMPY_CD    = '").append(glblCmpyCd).append("' ");
        sb.append("     AND CSI.EZCANCELFLAG    = '0'");
        sb.append("     AND CSI.TRX_CD          = '").append(TRX.PURCHASE_STOCK_IN).append("' ");
        sb.append("     AND CSI.TRX_RSN_CD      = '").append(TRX_RSN.PURCHASE_STOCK_IN).append("' ");
        sb.append("     AND CSI.GLBL_CMPY_CD    = IT.GLBL_CMPY_CD  (+)");
        sb.append("     AND CSI.DELY_ORD_NUM    = IT.RWS_REF_NUM   (+)");
        sb.append("     AND IT.EZCANCELFLAG (+) = '0'");
        sb.append("     AND IT.GLBL_CMPY_CD     = RWS.GLBL_CMPY_CD (+)");
        sb.append("     AND IT.RWS_NUM          = RWS.RWS_NUM      (+)");
        sb.append("     AND RWS.EZCANCELFLAG(+) = '0'");
        sb.append(" GROUP BY CSI.GLBL_CMPY_CD, CSI.EZCANCELFLAG, CSI.PO_ORD_NUM, CSI.DELY_ORD_NUM, RWS.RWS_NUM");

        return sb.toString();
    }

    private static List<Object[]> getWhereListNWAL1130ForVndRtrnReceipt(NFBL2040BMsg scrnMsg, String glblCmpyCd, String poNum, String delyOrdNum, String rwsNum, String vndRtrnNum) {
        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Order Number";
        whereArray1[IDX_1] = "PO_ORD_NUM";
        if (ZYPCommonFunc.hasValue(poNum)) {
            whereArray1[IDX_2] = poNum;
        } else {
            whereArray1[IDX_2] = vndRtrnNum;
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Receipt Number";
        whereArray2[IDX_1] = "DELY_ORD_NUM";
        whereArray2[IDX_2] = delyOrdNum;
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        return whereList;
    }

    private static List<Object[]> getColumnListNWAL1130ForVndRtrnReceipt(NFBL2040BMsg scrnMsg, String glblCmpyCd, String poNum, String delyOrdNum, String rwsNum) {
        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Number";
        columnArray1[IDX_1] = "PO_ORD_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Receipt Number";
        columnArray2[IDX_1] = "DELY_ORD_NUM";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Order Type";
        columnArray3[IDX_1] = "ORD_TYPE_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        return columnList;
    }

    private static List<Object[]> getSortListNWAL1130ForVndRtrnReceipt(NFBL2040BMsg scrnMsg, String glblCmpyCd, String poNum, String delyOrdNum, String rwsNum) {
        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PO_ORD_NUM";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "DELY_ORD_NUM";
        sortConditionArray2[IDX_1] = "ASC";
        sortList.add(sortConditionArray2);

        return sortList;
    }

    /**
     * Get Param NWAL1130 For VndRtrn
     * @param scrnMsg NFBL2040BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] getParamNWAL1130ForVndRtrn(NFBL2040BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Vendor Return Search";
        params[IDX_2] = getQueryNWAL1130ForVndRtrn(scrnMsg, glblCmpyCd);
        params[IDX_3] = getWhereListNWAL1130ForVndRtrn(scrnMsg, glblCmpyCd);
        params[IDX_4] = getColumnListNWAL1130ForVndRtrn(scrnMsg, glblCmpyCd);
        params[IDX_5] = getSortListNWAL1130ForVndRtrn(scrnMsg, glblCmpyCd);

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    private static String getQueryNWAL1130ForVndRtrn(NFBL2040BMsg scrnMsg, String glblCmpyCd) {
        // START 2018/03/23 [QC#20316, MOD]
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      VR.VND_RTRN_NUM           VND_RTRN_NUM");
        sb.append("     ,PV.PRNT_VND_CD            PRNT_VND_CD");
        sb.append("     ,PV.PRNT_VND_NM            PRNT_VND_NM");
        sb.append("     ,VRT.VND_RTRN_TP_DESC_TXT  VND_RTRN_TP_DESC_TXT");
        sb.append("     ,(CASE WHEN EXISTS (");
        sb.append("         SELECT 1");
        sb.append("         FROM");
        sb.append("             CM_AP_INV_HDR    CAIH");
        sb.append("         ,   CM_INV_ACCT_DIST CIAD");
        sb.append("         WHERE");
        sb.append("             CAIH.GLBL_CMPY_CD       = '").append(glblCmpyCd).append("' ");
        sb.append("         AND CAIH.EZCANCELFLAG       = '0'");
        sb.append("         AND CAIH.GLBL_CMPY_CD       = CIAD.GLBL_CMPY_CD");
        sb.append("         AND CAIH.AP_VND_CD          = CIAD.AP_VND_CD");
        sb.append("         AND CAIH.AP_VND_INV_NUM     = CIAD.AP_VND_INV_NUM");
        sb.append("         AND CAIH.AP_VND_INV_SQ_NUM  = CIAD.AP_VND_INV_SQ_NUM");
        // START 2018/04/06 [QC#25086, ADD]
        sb.append("         AND CAIH.ACCT_INV_STS_CD   != '").append(ACCT_INV_STS.CANCEL).append("' ");
        // END   2018/04/06 [QC#25086, ADD]
        sb.append("         AND CIAD.EZCANCELFLAG       = '0'");
        sb.append("         AND CIAD.GLBL_CMPY_CD       = VR.GLBL_CMPY_CD");
        // START 2019/04/17 [QC#31071, MOD]
        // sb.append("         AND CIAD.PO_NUM             = VR.VND_RTRN_NUM");
        sb.append("         AND CIAD.VND_RTRN_NUM       = VR.VND_RTRN_NUM");
        // END   2019/04/17 [QC#31071, MOD]
        sb.append("      ) THEN '").append(VND_RTRN_STS_CREDITED).append("'");
        sb.append("        ELSE '").append(VND_RTRN_STS_OPEN).append("'");
        sb.append("      END)                      VND_RTRN_STS_DESC_TXT");
        sb.append("     ,VR.GLBL_CMPY_CD           GLBL_CMPY_CD");
        sb.append("     ,VR.EZCANCELFLAG           EZCANCELFLAG");
        sb.append(" FROM");
        sb.append("      VND_RTRN     VR");
        sb.append("     ,VND          V");
        sb.append("     ,PRNT_VND     PV");
        sb.append("     ,VND_RTRN_TP  VRT");
        // START 2019/04/17 [QC#31071, DEL]
        // START 2018/04/06 [QC#25086, ADD]
        // sb.append("     ,PRCH_REQ     PR");
        // END   2018/04/06 [QC#25086, ADD]
        // END   2019/04/17 [QC#31071, DEL]
        sb.append(" WHERE");
        sb.append("         VR.GLBL_CMPY_CD       = '").append(glblCmpyCd).append("' ");
        sb.append("     AND VR.EZCANCELFLAG       = '0'");
        sb.append("     AND VR.GLBL_CMPY_CD       = V.GLBL_CMPY_CD");
        sb.append("     AND VR.BILL_TO_VND_CD     = V.VND_CD");
        sb.append("     AND V.EZCANCELFLAG        = '0'");
        sb.append("     AND V.GLBL_CMPY_CD        = PV.GLBL_CMPY_CD");
        sb.append("     AND V.PRNT_VND_PK         = PV.PRNT_VND_PK");
        sb.append("     AND PV.EZCANCELFLAG       = '0'");
        sb.append("     AND VR.GLBL_CMPY_CD       = VRT.GLBL_CMPY_CD");
        sb.append("     AND VR.VND_RTRN_TP_CD     = VRT.VND_RTRN_TP_CD");
        sb.append("     AND VRT.EZCANCELFLAG      = '0'");
        // START 2019/04/17 [QC#31071, MOD]
        // START 2018/04/06 [QC#25086, ADD]
        // sb.append("     AND VR.GLBL_CMPY_CD       = PR.GLBL_CMPY_CD");
        // sb.append("     AND VR.VND_RTRN_NUM       = PR.PRCH_REQ_NUM");
        // sb.append("     AND PR.PRCH_REQ_STS_CD    = '").append(PRCH_REQ_STS.CLOSED).append("' ");
        // sb.append("     AND PR.EZCANCELFLAG       = '0'");
        // END   2018/04/06 [QC#25086, ADD]
        sb.append("     AND EXISTS ( SELECT 1");
        sb.append("                     FROM CM_STK_IN CSI");
        sb.append("                     WHERE ");
        sb.append("                         CSI.GLBL_CMPY_CD = VR.GLBL_CMPY_CD");
        sb.append("                     AND CSI.TRX_CD       = '").append(TRX.PURCHASE_STOCK_IN ).append("' ");
        sb.append("                     AND CSI.TRX_RSN_CD   = '").append(TRX_RSN.DOMESTIC_VENDOR_RETURN).append("' ");
        sb.append("                     AND CSI.VND_RTRN_NUM = VR.VND_RTRN_NUM");
        sb.append("                     AND CSI.EZCANCELFLAG = VR.EZCANCELFLAG");
        sb.append("     )");
        // END   2019/04/17 [QC#31071, MOD]
        // END   2018/03/23 [QC#20316, MOD]

        return sb.toString();
    }

    private static List<Object[]> getWhereListNWAL1130ForVndRtrn(NFBL2040BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Vendor Return Number";
        whereArray1[IDX_1] = "VND_RTRN_NUM";
        // mod start 2022/02/15 QC#57090
        //whereArray1[IDX_2] = scrnMsg.vndRtrnNum.getValue();
        if (OPENWIN_VND_RTRN_1.equals(scrnMsg.xxScrEventNm.getValue())) {
            whereArray1[IDX_2] = scrnMsg.vndRtrnNum.getValue();
        } else if (OPENWIN_VND_RTRN_2.equals(scrnMsg.xxScrEventNm.getValue())) {
            whereArray1[IDX_2] = scrnMsg.vndRtrnNum_H.getValue();
        } else if (OPENWIN_VND_RTRN_3.equals(scrnMsg.xxScrEventNm.getValue())) {
            whereArray1[IDX_2] = scrnMsg.vndRtrnNum_L.getValue();
        }
        // mod end 2022/02/15 QC#57090
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }

    private static List<Object[]> getColumnListNWAL1130ForVndRtrn(NFBL2040BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Vendor Return Number";
        columnArray1[IDX_1] = "VND_RTRN_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Supplier Number";
        columnArray2[IDX_1] = "PRNT_VND_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Supplier Name";
        columnArray3[IDX_1] = "PRNT_VND_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Vendor Return Type";
        columnArray4[IDX_1] = "VND_RTRN_TP_DESC_TXT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Status";
        columnArray5[IDX_1] = "VND_RTRN_STS_DESC_TXT";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;

        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        columnList.add(columnArray5);

        return columnList;
    }

    private static List<Object[]> getSortListNWAL1130ForVndRtrn(NFBL2040BMsg scrnMsg, String glblCmpyCd) {
        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "VND_RTRN_NUM";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);

        return sortList;
    }
    // END   2018/02/02 [QC#21703, ADD]

    // START 2018/02/26 [QC#23505, ADD]
    /**
     * isUnsubmittedCorrection
     * @param scrnMsg NFBL2040BMsg
     * @return boolean
     */
    public static boolean isUnsubmittedCorrection(NFBL2040BMsg scrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxBtnFlg_CR.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * isCorrectedInvoice
     * @param scrnMsg NFBL2040BMsg
     * @return boolean
     */
    public static boolean isCorrectedInvoice(NFBL2040BMsg scrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_CR.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * isCorrectableInvoice
     * @param scrnMsg NFBL2040BMsg
     * @return boolean
     */
    public static boolean isCorrectableInvoice(NFBL2040BMsg scrnMsg) {
        // START 2020/03/23 [QC#53413, MOD]
        //if (!ZYPCommonFunc.hasValue(scrnMsg.poNum_HT) || !ZYPCommonFunc.hasValue(scrnMsg.poStsCd_HD)
        if (!ZYPCommonFunc.hasValue(scrnMsg.poStsCd_HD)
        // START 2020/03/23 [QC#53413, MOD]
                || CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
            return false;
        }
        if (scrnMsg.acctInvStsCd.getValue().equals(ACCT_INV_STS.LINKED_TO_COST_CALCULATION)
                || scrnMsg.acctInvStsCd.getValue().equals(ACCT_INV_STS.PAID) || scrnMsg.acctInvStsCd.getValue().equals(ACCT_INV_STS.VOIDED)) {
            return true;
        }
        return false;
    }

    /**
     * isEditableLine
     * @param scrnMsg NFBL2040BMsg
     * @param scrnAMsg NFBL2040_ABMsg
     * @return boolean
     */
    public static boolean isEditableLine(NFBL2040BMsg scrnMsg, NFBL2040_ABMsg scrnAMsg) {
        if (XX_LINE_TP_CD_INVALID.equals(scrnAMsg.xxLineTpCd_A1.getValue())
                || XX_LINE_TP_CD_CREDIT.equals(scrnAMsg.xxLineTpCd_A1.getValue())
                || XX_LINE_TP_CD_NO_CHANGE.equals(scrnAMsg.xxLineTpCd_A1.getValue())) {
            return false;
        }
        // START 2018/04/04 [QC#20316, ADD]
        if (NFBL2040CommonLogic.isCorrectableInvoice(scrnMsg) && NFBL2040CommonLogic.isCorrectedInvoice(scrnMsg) 
                && NFBL2040CommonLogic.isUnsubmittedCorrection(scrnMsg)) {
            return true;
        } else if (isPOLineClosed(scrnMsg, scrnAMsg)) {
            return false;
        }
        // END   2018/04/04 [QC#20316, ADD]
        return true;
    }

    /**
     * setInputProtectedForAfterCorrection
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setInputProtectedForCorrection(NFBL2040BMsg scrnMsg) {
        setInputProtectedFromOthScrForRef(scrnMsg);

        // Header Tab
        scrnMsg.poNum_HT.setInputProtected(true);                                  // PO#
        scrnMsg.xxLinkProt_V2.setInputProtected(true);                             // PO#(Link)
        scrnMsg.invHdrDescTxt.setInputProtected(false);                             // Description

        // Holds Tab

        // Lines Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (!isEditableLine(scrnMsg, scrnMsg.A.no(i))) {
                continue;
            }

            // START 2018/03/16 [QC#24089, ADD]
            // START 2018/04/04 [QC#20316, DEL]
            // if (isPOLineClosed(scrnMsg, scrnMsg.A.no(i))) {
            //     continue;
            // }
            // END   2018/04/04 [QC#20316, DEL]

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).apLineTpCd_A1)
                    && AP_LINE_TP.ITEM.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                // Item
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);               // Checkbox
                // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);               // Line Amount
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);                // Line Amount
                // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                // START 2019/11/02 [QC#54439, MOD]
                // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                // END 2019/11/02 [QC#54439, MOD]
                scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false);      // Unit Price
                scrnMsg.A.no(i).apBillQty_A1.setInputProtected(false);              // Invoiced Qty
            } else {
                // Not Item
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);               // Checkbox
                scrnMsg.A.no(i).apLineTpCd_A1.setInputProtected(false);             // Line Type
                //scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(false);               // Line Amount
                scrnMsg.A.no(i).xxInvAmt_A1.setInputProtected(true);                // Line Amount
                // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                // START 2019/11/02 [QC#54439, MOD]
                // START 2018/06/26 S.Katsuma [QC#26566,MOD]
                setInputProtectedChargeAccount(scrnMsg, scrnMsg.A.no(i));
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]
                // END 2019/11/02 [QC#54439, MOD]
                // END 2018/06/26 S.Katsuma [QC#26566,MOD]                           // Charge Account
                if (AP_LINE_TP.MISC.equals(scrnMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false); 
                    scrnMsg.A.no(i).dealGrsUnitPrcAmt_A1.setInputProtected(false); 
                    
                }
            }
        }
    }

    /**
     * setButtonAfterCorrection
     * @param handler EZDCommonHandler
     * @param scrnMsg NFBL2040BMsg
     */
    public static void setButtonForCorrection(EZDCommonHandler handler, NFBL2040BMsg scrnMsg) {
        // HEADER Tab
        handler.setButtonEnabled(BTN_NORMAL_REFRESH, false);
        handler.setButtonEnabled(BTN_NORMAL_CORRECTION, false);
        handler.setButtonEnabled(BTN_NORMAL_PO_LINE_CORRECTION, false);
        handler.setButtonEnabled(BTN_NORMAL_WORKFOLDER, false);

        // LINES Tab
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW, true);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW, true);
        handler.setButtonEnabled(BTN_NORMAL_MDSE, false);
        handler.setButtonEnabled(BTN_NORMAL_ITEM_DESCRIPTION, false);
        // START 2020/03/23 [QC#53413, ADD]
        handler.setButtonEnabled(BTN_NORMAL_LINE_SEARCH, true);
        scrnMsg.xxLinkProt_V4.setInputProtected(false);
        scrnMsg.poNum_L.setInputProtected(false);
        // END   2020/03/23 [QC#53413, ADD]
        // QC#24985
        handler.setButtonEnabled(BTN_NORMAL_CHARGE_ACCOUNT, true);

        // HOLDS Tab
        handler.setButtonEnabled(BTN_NORMAL_INSERT_ROW_HOLD, false);
        handler.setButtonEnabled(BTN_NORMAL_DELETE_ROW_HOLD, false);

        // Common Button
        handler.setButtonProperties(BTN_CMN_BLANK1[0], BTN_CMN_BLANK1[1], BTN_CMN_BLANK1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK3[0], BTN_CMN_BLANK3[1], BTN_CMN_BLANK3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK5[0], BTN_CMN_BLANK5[1], BTN_CMN_BLANK5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BLANK9[0], BTN_CMN_BLANK9[1], BTN_CMN_BLANK9[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }
    // END   2018/02/26 [QC#23505, ADD]

    // START 2018/03/08 [QC#22755, ADD]
    /**
     * isValidPO
     * @param scrnMsg NFBL2040BMsg
     * @param aBMsg NFBL2040_ABMsg
     * @return boolean
     */
    public static boolean isValidPoNumAnchor(NFBL2040BMsg scrnMsg, NFBL2040_ABMsg aBMsg) {
        // mod start 2022/02/15 QC#57090
        //return ZYPCommonFunc.hasValue(scrnMsg.poStsCd_HD) && ZYPCommonFunc.hasValue(aBMsg.poNum_A1);
        return ZYPCommonFunc.hasValue(aBMsg.poNum_A1);
        // mod end 2022/02/15 QC#57090
    }
    // END   2018/03/08 [QC#22755, ADD]

    // START 2018/03/16 [QC#24089, ADD]
    /**
     * isPOClosedLine
     * @param scrnMsg NFBL2040CMsg
     * @param aScrnMsg NFBL2040_ACMsg
     * @return boolean
     */
    public static boolean isPOLineClosed(NFBL2040BMsg scrnMsg, NFBL2040_ABMsg aScrnMsg) {
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(scrnMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.poNum)) {
            return false;
        }

        // START 2018/08/01 [QC#27025-1, MOD]
        // if (PO_STS.CLOSED.equals(scrnMsg.poStsCd_HD.getValue()) || PO_STS.CANCELLED.equals(scrnMsg.poStsCd_HD.getValue())
        //         || PO_LINE_STS.CLOSED.equals(aScrnMsg.poLineStsCd_A1.getValue())) {
        //     return true;
        // }
        if (PO_STS.CANCELLED.equals(scrnMsg.poStsCd_HD.getValue())) {
            return true;
        }
        // START 2018/08/01 [QC#27025-1, MOD]
        return false;
    }
    // END   2018/03/16 [QC#24089, ADD]

    // START 2019/11/02 [QC#54439, ADD]
    /**
     * isVendorReturnItemLine
     * @param scrnMsg NFBL2040CMsg
     * @param aScrnMsg NFBL2040_ACMsg
     * @return boolean
     */
    public static boolean isVendorReturnItemLine(NFBL2040BMsg scrnMsg, NFBL2040_ABMsg aScrnMsg) {
        // mod start 2022/02/15 QC#57090
        //if (ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum)) {
        if (ZYPCommonFunc.hasValue(aScrnMsg.vndRtrnNum_A1)) {
        // mod end 2022/02/15 QC#57090
            if (AP_LINE_TP.ITEM.equals(aScrnMsg.apLineTpCd_A1.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * setInputProtectedChargeAccount
     * @param scrnMsg NFBL2040CMsg
     * @param aScrnMsg NFBL2040_ACMsg
     */
    public static void setInputProtectedChargeAccount(NFBL2040BMsg scrnMsg, NFBL2040_ABMsg aScrnMsg) {
        if (isVendorReturnItemLine(scrnMsg, aScrnMsg)) {
            aScrnMsg.xxCmntTxt_A1.setInputProtected(true);
        } else {
            aScrnMsg.xxCmntTxt_A1.setInputProtected(false);
        }
    }
    // END   2019/11/02 [QC#54439, ADD]

    // START 2020/03/16 [QC#55993, ADD]
    /**
     * clearHoldReleasePending
     * @param scrnMsg NFBL2040CMsg
     * @param boolean skipFlgOff
     */
    public static void clearHoldReleasePending(NFBL2040BMsg scrnMsg, boolean skipFlgOff) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
            for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRelPsnCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRelDt_H1)) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(i).xxChkBox_H1, ZYPConstant.FLG_OFF_N);
                        scrnMsg.H.no(i).pmtHldRelPsnCd_H1.clear();
                        scrnMsg.H.no(i).pmtHldRelDt_H1.clear();
                        scrnMsg.H.no(i).pmtHldRelRsnCd_H1.clear();                     // Release Reason
                        scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);
                        scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.clear();                   // Release Note
                        scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false);
                    }
                    if (ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRelPsnCd_H1) && ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRelDt_H1)) {
                        scrnMsg.H.no(i).xxChkBox_H1.setInputProtected(true);
                    }
                }
            }
            if(skipFlgOff){
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_OFF_N);
            }
        }
    }
    /**
     * offHoldReleaseCheck
     * @param scrnMsg NFBL2040CMsg
     */
    public static void offHoldReleaseCheck(NFBL2040BMsg scrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg_A.getValue())) {
            for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxChkBox_H1.getValue())) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRelPsnCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRelDt_H1)) {
                        scrnMsg.H.no(i).pmtHldRelRsnCd_H1.setInputProtected(false);
                        scrnMsg.H.no(i).pmtHldRelCmntTxt_H1.setInputProtected(false);
                    }
                }
            }
        }
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (scrnMsg.H.no(i).xxChkBox_H1.isError()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_OFF_N);
            }
        }
    }
    // END   2020/03/16 [QC#55993, ADD]

    // START 2020/03/23 [QC#53413, ADD]
    // mod start 2022/02/15 QC#57090
    //public static void delMultiPoScrnData(NFBL2040BMsg scrnMsg) {
    public static void clearMultiPoOrMultiVndRtrnData(NFBL2040BMsg scrnMsg) {
    // mod end 2022/02/15 QC#57090

        List<String> poNumList = new ArrayList<String>();
        // add start 2022/02/15 QC#57090
        List<String> vndRtrnNumList = new ArrayList<String>();
        // add end 2022/02/15 QC#57090
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poNum_A1)) {
                String poNum = scrnMsg.A.no(i).poNum_A1.getValue();
                if (!poNumList.contains(poNum)) {
                    poNumList.add(poNum);
                }
            }
            // add start 2022/02/15 QC#57090
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).vndRtrnNum_A1)) {
                String vndRtrnNum = scrnMsg.A.no(i).vndRtrnNum_A1.getValue();
                if (!vndRtrnNumList.contains(vndRtrnNum)) {
                    vndRtrnNumList.add(vndRtrnNum);
                }
            }
            // add end 2022/02/15 QC#57090
            // mod start 2022/02/15 QC#57090
            //if (poNumList.size() > 1) {
            if (poNumList.size() > 1 || vndRtrnNumList.size() > 1) {
            // mod end 2022/02/15 QC#57090
                scrnMsg.poNum_HT.clear();
                scrnMsg.poApvlDt.clear();
                scrnMsg.entPoDtlDealNetAmt_TO.clear();
                scrnMsg.delyOrdNum_DH.clear();
                scrnMsg.rwsNum_DH.clear();
                // add start 2022/02/15 QC#57090
                scrnMsg.vndRtrnNum_H.clear();
                // add end 2022/02/15 QC#57090
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_MP, ZYPConstant.FLG_ON_Y);
                break;
            }
        }
    }

    public static boolean chkMtPo (NFBL2040BMsg scrnMsg, int cnt) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(cnt).poNum_A1)){
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(cnt).xxLineTpCd_A1)) {
                if (XX_LINE_TP_CD_VALID.equals(scrnMsg.A.no(cnt).xxLineTpCd_A1.getValue())) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }
    // END   2020/03/23 [QC#53413, ADD]
    
    // Start 2023/1/17 S.Nakatani [QC#60812, ADD]
    public static boolean isInvNumUpdatable(String glblCmpyCd, String acctInvSts, String apInvCatg){
        String exclApInvCatg = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_EXCL_AP_INV_CATG, glblCmpyCd);
        if(ZYPCommonFunc.hasValue(apInvCatg) && ZYPCommonFunc.hasValue(exclApInvCatg)){
            String[] exclApInvCatgs = exclApInvCatg.split(",");
            for(int i = 0; i < exclApInvCatgs.length; i++){
                if(apInvCatg.equals(exclApInvCatgs[i]))
                    return false;
            }
        } else {
            return false;
        }
        if(ZYPCommonFunc.hasValue(apInvCatg)){
            if(!ACCT_INV_STS.LINKED_TO_COST_CALCULATION.equals(acctInvSts)){
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
    // End 2023/1/17 S.Nakatani [QC#60812, ADD]
}
