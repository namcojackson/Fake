/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2040.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.blap.NFBL2040.NFBL2040Query;
import business.blap.NFBL2040.NFBL2040_ACMsg;
import business.blap.NFBL2040.NFBL2040_HCMsg;
import business.blap.NFBL2040.constant.NFBL2040Constant;
import business.db.ACCT_INV_STSTMsg;
import business.db.AP_INV_CATGTMsg;
import business.db.CCYTMsg;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.CM_INV_PMT_HLDTMsg;
import business.db.CM_STK_INTMsg;
import business.db.COA_ACCTTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_DTLTMsgArray;
import business.file.NFBL2040F00FMsg;
import business.file.NFBL2040F01FMsg;
import business.file.NFBL2040F02FMsg;
import business.parts.NFBCommonBusiness;
import business.parts.NFZC102001PMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NFBCommonBusiness.DefCoaValues;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_INV_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MATCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
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
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          QC#11331
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/08/01   Hitachi         J.Kim           Update          QC#12554
 * 2016/08/02   Hitachi         Y.Tsuchimoto    Update          QC#12011
 * 2016/08/02   Hitachi         T.Tsuchida      Update          QC#12053
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12650
 * 2016/08/10   Hitachi         T.Tsuchida      Update          QC#12355
 * 2016/08/23   Hitachi         Y.Tsuchimoto    Update          QC#12010
 * 2016/08/23   Hitachi         Y.Tsuchimoto    Update          QC#12011
 * 2016/08/25   Hitachi         Y.Tsuchimoto    Update          QC#12979
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Update          QC#12043
 * 2016/09/20   Hitachi         T.Tsuchida      Update          QC#12355
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13550
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#12038
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13157
 * 2016/09/26   Fujitsu         W.Honda         Update          Unit Test#201
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/05   Hitachi         T.Tsuchida      Update          QC#14164
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#15036
 * 2016/10/07   Hitachi         T.Tsuchida      Update          QC#12053
 * 2016/10/07   Hitachi         Y.Tsuchimoto    Update          QC#15091
 * 2016/10/07   Hitachi         Y.Tsuchimoto    Update          QC#14799
 * 2016/10/18   Hitachi         Y.Tsuchimoto    Update          QC#14799
 * 2016/10/25   Hitachi         Y.Tsuchimoto    Update          QC#14501
 * 2016/10/26   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2016/11/11   Hitachi         K.Kasai         Update          QC#15455
 * 2016/11/14   Hitachi         Y.Tsuchimoto    Update          QC#15773
 * 2016/11/15   Hitachi         K.Kasai         Update          QC#15896
 * 2016/11/15   Hitachi         K.Kasai         Update          QC#15904
 * 2016/11/15   Hitachi         T.Tsuchida      Update          QC#16015
 * 2016/11/16   Hitachi         K.Kasai         Update          QC#15445
 * 2016/12/13   Hitachi         K.Kasai         Update          QC#16139, 16476
 * 2017/01/12   Hitachi         E.Kameishi      Update          QC#16950
 * 2017/01/25   Hitachi         E.Kameishi      Update          QC#17090
 * 2017/03/17   Hitachi         E.Kameishi      Update          QC#14205
 * 2017/06/15   CITS            K.Ogino         Update          QC#19219
 * 2017/06/26   CITS            K.Ogino         Update          QC#19391
 * 2017/07/05   CITS            M.Naito         Update          QC#19261
 * 07/18/2017   CITS            K.Ogino         Update          QC#19433
 * 2017/09/22   CITS            K.Ogino         Update          QC#21321
 * 2017/10/12   CITS            T.Tokutomi      Update          QC#21636
 * 2017/10/18   CITS            T.Tokutomi      Update          QC#21642
 * 2017/10/20   CITS            T.Tokutomi      Update          QC#21653
 * 2017/11/14   CITS            T.Wada          Update          QC#22144
 * 2017/11/14   CITS            T.Wada          Update          QC#21727
 * 2017/11/21   CITS            T.Wada          Update          QC#21727-1
 * 2017/12/20   CITS            T.Tokutomi      Update          QC#14858-Sol#060
 * 2017/12/25   Hitachi         J.Kim           Update          QC#23059
 * 2017/12/26   Hitachi         Y.Takeno        Update          QC#23022
 * 2017/01/09   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/01/24   CITS            T.Tokutomi      Update          QC#18602-Sol#102
 * 2018/01/25   CITS            S.Katsuma       Update          QC#19579
 * 2018/01/29   CITS            K.Kameoka       Update          QC#18602-Sol#102
 * 2018/01/30   CITS            T.Tokutomi      Update          QC#23837
 * 2018/02/23   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/02/26   Hitachi         Y.Takeno        Update          QC#24144
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#24140
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#24620
 * 2018/03/07   Hitachi         J.Kim           Update          QC#24636
 * 2018/03/13   Hitachi         Y.Takeno        Update          QC#24274
 * 2018/03/16   Hitachi         Y.Takeno        Update          QC#24089
 * 2018/03/22   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/03/27   Hitachi         Y.Takeno        Update          QC#24277
 * 2018/04/04   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/04/10   CITS            K.Ogino         Update          QC#24985
 * 2018/05/07   Hitachi         Y.Takeno        Update          QC#25828
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/06/19   Hitachi         Y.Takeno        Update          QC#26169
 * 2018/06/20   Hitachi         Y.Takeno        Update          QC#26169
 * 2018/06/26   CITS            S.Katsuma       Update          QC#24617
 * 2018/07/17   CITS            S.Katsuma       Update          QC#27078
 * 2018/07/19   CITS            T.Tokutomi      Update          QC#27026
 * 2018/07/27   CITS            T.Tokutomi      Update          QC#27029
 * 2018/08/02   Hitachi         Y.Takeno        Update          QC#27025-1
 * 2018/08/02   Hitachi         E.Kameishi      Update          QC#27557
 * 2018/08/09   CITS            T.Tokutomi      Update          QC#27029-1
 * 2018/08/14   Hitachi         Y.Takeno        Update          QC#27029-1
 * 2018/08/15   Hitachi         Y.Takeno        Update          QC#27029-1
 * 2018/08/21   Hitachi         Y.Takeno        Update          QC#27873
 * 2018/08/23   Hitachi         Y.Takeno        Update          QC#27247-1
 * 2018/08/24   Hitachi         Y.Takeno        Update          QC#27563-1
 * 2018/09/04   Hitachi         Y.Takeno        Update          QC#28039
 * 2018/09/25   Fujitsu         S.Ohki          Update          QC#28441
 * 2018/10/11   Fujitsu         S.Ohki          Update          QC#28038
 * 2018/10/25   Hitachi         Y.Takeno        Update          QC#28758
 * 2018/10/29   Hitachi         Y.Takeno        Update          QC#28988
 * 2018/11/20   Hitachi         Y.Takeno        Update          QC#28660
 * 2018/11/21   Hitachi         Y.Takeno        Update          QC#28660
 * 2019/01/29   Fujitsu         S.Ohki          Update          QC#30056
 * 2019/02/07   Hitachi         Y.Takeno        Update          QC#30136
 * 2019/02/19   Fujitsu         S.Ohki          Update          QC#30419
 * 2019/02/19   Fujitsu         S.Ohki          Update          QC#30418
 * 2019/02/21   Hitachi         Y.Takeno        Update          QC#30420
 * 2019/03/19   Hitachi         Y.Takeno        Update          QC#30829
 * 2019/07/18   Hitachi         Y.Takeno        Update          QC#51675
 * 2019/08/15   Hitachi         Y.Takeno        Update          QC#52280
 * 2019/11/02   Fujitsu         Y.Matsui        Update          QC#54439
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 * 2020/06/08   Fujitsu         H.Ikeda         Update          QC#57007
 * 2020/07/02   CITS            R.Kurahashi     Update          QC#56696
 * 2020/07/16   CITS            R.Kurahashi     Update          QC#56696-1
 * 2020/09/09   CITS            R.Kurahashi     Update          QC#57668
 * 2021/03/08   CITS            H.Dimay         Update          QC#58491
 * 2020/04/22   CITS            R.Azucena       Update          QC#56829
 * 2020/06/04   CITS            H.Dimay         Update          QC#56829
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * </pre>
 */
public class NFBL2040CommonLogic implements NFBL2040Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    // START 2020/02/17 [QC#53413, ADD]
    // del start 2022/02/15 QC#57090
    //public  String maxSqNum = null;
    // del end 2022/02/15 QC#57090
    // END    2020/02/17 [QC#53413, ADD]

    /**
     * Method name: createCmApInvTpPulldownList <dd>The method
     * explanation: Create pulldown list. Code Pull Down value from
     * CM_AP_INV_TP table. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void createCmApInvTpPulldownList(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getCmApInvTpPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.cmApInvTpCd_C.no(i).setValue((String) map.get(CM_AP_INV_TP_CD));
                bizMsg.cmApInvTpDescTxt_D.no(i).setValue((String) map.get(CM_AP_INV_TP_DESC_TXT));
            }
        }

    }

    /**
     * Method name: createCmInvMatchPulldownList <dd>The method
     * explanation: Create pulldown list. Code Pull Down value from
     * CM_INV_MATCH table. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void createCmInvMatchPulldownList(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getCmInvMatchPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.cmInvMatchCd_C.no(i).setValue((String) map.get(CM_INV_MATCH_CD));
                bizMsg.cmInvMatchDescTxt_D.no(i).setValue((String) map.get(CM_INV_MATCH_DESC_TXT));
            }
        }

    }

    /**
     * Method name: createPmtHldPulldownList <dd>The method
     * explanation: Create pulldown list. Code Pull Down value from
     * PMT_HLD table. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void createPmtHldPulldownList(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2018/01/25 S.Katsuma [QC#19579,MOD]
        List resultList = getPmtHldPulldownList();
        for (int i = 0; i < resultList.size(); i++) {
            Map map = (Map) resultList.get(i);
            bizMsg.pmtHldCd_HC.no(i).setValue((String) map.get(PMT_HLD_CD));
            bizMsg.pmtHldDescTxt_H1.no(i).setValue((String) map.get(PMT_HLD_DESC_TXT));
        }
        // END 2018/01/25 S.Katsuma [QC#19579,MOD]

    }

    /**
     * Method name: createPmtHldPulldownList <dd>The method
     * explanation: Create pulldown list. Code Pull Down value from
     * PMT_HLD_RSN table. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void createPmtHldRsnPulldownList(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2018/01/25 S.Katsuma [QC#19579,MOD]
        List resultList = getPmtHldRsnPulldownList();
        for (int i = 0; i < resultList.size(); i++) {
            Map map = (Map) resultList.get(i);
            bizMsg.pmtHldRsnCd_HC.no(i).setValue((String) map.get(PMT_HLD_RSN_CD));
            bizMsg.pmtHldRsnDescTxt_H1.no(i).setValue((String) map.get(PMT_HLD_RSN_DESC_TXT));
        }
        // END 2018/01/25 S.Katsuma [QC#19579,MOD]

    }

    /**
     * Method name: getInvRecordFromOtherScreen <dd>The method
     * explanation: Get invoice record in case invoice is referred to
     * from other screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static boolean getInvRecordFromOtherScreen(EZDCMsg cMsg) {
        EZDDebugOutput.println(5, "getInvRecordFromOtherScreen================================START", null);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        // LINES tab
        bizMsg.A.setValidCount(0);
        bizMsg.A.clear();
        bizMsg.E.setValidCount(0);
        ZYPTableUtil.clear(bizMsg.E);

        // HOLDS tab
        bizMsg.H.setValidCount(0);
        bizMsg.H.clear();

        // DISTRIBUTION tab
        bizMsg.D.setValidCount(0);
        bizMsg.D.clear();
        // Payment Hold
        String pmtHldFlg = ZYPConstant.FLG_OFF_N;

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getInvRecordFromOtherScreen(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            if (resultList.size() > bizMsg.A.length()) {
                bizMsg.A.setValidCount(bizMsg.A.length());
                bizMsg.setMessageInfo(NFBM0001W, new String[] {Long.toString(bizMsg.A.length()), Long.toString(resultList.size()) });
            } else {
                bizMsg.A.setValidCount(resultList.size());
            }

            BigDecimal lineTotAmt = BigDecimal.ZERO;

            // Distributions tab
            int validCountD = 0;
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllocTpCd_D, All_LINES); // Radio
            // button
            // default
            // value

            String invDt = EMPTY_STRING;

            // Get records for [Line Type] pulldown values.
            List apLineTpPulldownList = NFBL2040CommonLogic.getApLineTpPulldownList();

            boolean poVarianceFlg = false;
            // START 2019/02/07 [QC#30136, ADD]
            boolean isBlanketPO = ZYPCommonFunc.hasValue(bizMsg.poNum) && isBlanketPO(bizMsg.poNum.getValue());
            // END   2019/02/07 [QC#30136, ADD]

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                Map map = (Map) resultList.get(i);
                if (i == 0) {
                    // Hidden Value
                    setHiddenValueInfo(bizMsg, map);
                    // Search Condition
                    setSearchConditionInfo(bizMsg, map);
                    // HEADER Tab
                    setHeadersTabInfo(bizMsg, map, invDt);
                    // HOLDS tab
                    if (ZYPCommonFunc.hasValue((String) map.get(PMT_HLD_FLG)) && ZYPConstant.FLG_ON_Y.equals((String) map.get(PMT_HLD_FLG))) {
                        pmtHldFlg = ZYPConstant.FLG_ON_Y;
                        setHoldsTabHeaderInfo(bizMsg, map);
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_ON_Y);
                    } else {
                        pmtHldFlg = ZYPConstant.FLG_OFF_N;
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_OFF_N);
                    }
                }

                // QC#19391
                if (ZYPCommonFunc.hasValue((String) map.get(AP_LINE_TP_CD)) && AP_LINE_TP.VARIANCE.equals((String) map.get(AP_LINE_TP_CD))) {
                    poVarianceFlg = true;
                }

                // LINES Tab
                // START 2019/01/29 [QC#30056, MOD]
//                setLinesTabDetailInfo(bizMsg, map, i, apLineTpPulldownList);
                setLinesTabDetailInfo(bizMsg, map, i, apLineTpPulldownList, i);
                // END   2019/01/29 [QC#30056, MOD]
                // START 2018/02/27 [QC#20505, ADD]
                // lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                if (isValidLine(bizMsg, bizMsg.A.no(i))) {
                    lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                }
                // END   2018/02/27 [QC#20505, ADD]

                // DISTRIBUTIONS Tab
                // Debit Line
                if (All_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) || (SELECTED_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue()))) {
                    // For Credit line and variance record
                    if (validCountD < (bizMsg.A.length() - 2)) {
                        // START 2019/01/29 [QC#30056, MOD]
//                        setDistributionsTabDebitLineInfo(bizMsg, i, validCountD);
//                        validCountD++;
                        // START 2019/02/07 [QC#30136, MOD]
                        // if (setDistributionsTabDebitLineInfo(bizMsg, i, validCountD)) {
                        //     validCountD++;
                        // }
                        if (setDistributionsTabDebitLineInfo(bizMsg, i, validCountD, isBlanketPO)) {
                            validCountD++;
                        }
                        // END   2019/02/07 [QC#30136, MOD]
                        // END   2019/01/29 [QC#30056, MOD]
                    }
                }
            }

            if (poVarianceFlg) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PO, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PO, ZYPConstant.FLG_OFF_N);
            }

            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).apLineTpCd_A1)) {
                    ZYPTableUtil.deleteRows(bizMsg.A, Arrays.asList(i));
                    i = i - 1;
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, lineTotAmt); // Line
            // Total

            // DISTRIBUTIONS Tab
            // Credit Line
            setDistributionsTabCreditLineInfo(bizMsg, validCountD, bizMsg.A.getValidCount() + 1);
            validCountD++;
            bizMsg.D.setValidCount(validCountD);

            // HOLDS tab
            ssmResult = NFBL2040Query.getInstance().getHoldRecord(bizMsg);
            if (ssmResult.isCodeNormal()) {
                resultList = (List) ssmResult.getResultObject();
                if (resultList.size() > bizMsg.H.length()) {
                    bizMsg.H.setValidCount(bizMsg.H.length());
                } else {
                    bizMsg.H.setValidCount(resultList.size());
                }
                // Get records for [Release Reason] pulldown values.
                List pmtHldRelRsnPulldownList = NFBL2040CommonLogic.getPmtHldRelRsnPulldownList();
                for (int j = 0; j < bizMsg.H.getValidCount(); j++) {
                    Map map = (Map) resultList.get(j);
                    setHoldsTabDetailInfo(bizMsg, map, j, pmtHldRelRsnPulldownList);
                }
            }
        } else {
            // Not found
            bizMsg.setMessageInfo(NFBM0069E);
            return false;
        }

        // START 2018/03/08 [QC#24140, ADD]

        // set search condition items
        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_HD, bizMsg.cmApInvTpCd_S);    // Invoice
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HD, bizMsg.poNum_HT);               // PO#
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_HD, bizMsg.delyOrdNum_H);      // Receipt#
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_DH, bizMsg.rwsNum_H);              // RWS#
        // START QC#25902,QC#25190,QC#25141
        // mod start 2022/02/15 QC#57090
        //ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum_H);      // Vendor Return#
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_HD, bizMsg.vndRtrnNum);      // Vendor Return#
        // mod end 2022/02/15 QC#57090
        // END QC#25902,QC#25190,QC#25141

        // END   2018/03/08 [QC#24140, ADD]

        EZDDebugOutput.println(5, "getInvRecordFromOtherScreen================================END", null);
        return true;
    }

    // START 2020/02/17 [QC#53413, ADD]
    @SuppressWarnings("unchecked")
    // mod start 2022/02/15 QC#57090
    //public static boolean getRecordPoSearch(EZDCMsg cMsg) {
    public static boolean getRecordLineSearch(EZDCMsg cMsg) {
    // mod end 2022/02/15 QC#57090
        EZDDebugOutput.println(5, "getRecord================================START", null);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // LINES tab
        int aLineCnt = bizMsg.A.getValidCount();
        // DISTRIBUTION tab
        int dLineCnt = bizMsg.D.getValidCount();

        // mod start 2022/02/15 QC#57090
        //if (ZYPCommonFunc.hasValue(bizMsg.poNum_L) || ZYPCommonFunc.hasValue(bizMsg.delyOrdNum_L)) {
        if (ZYPCommonFunc.hasValue(bizMsg.poNum_L) || ZYPCommonFunc.hasValue(bizMsg.delyOrdNum_L) || ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_L)) {
        // mod end 2022/02/15 QC#57090
            S21SsmEZDResult ssmResult = null;

            // del start 2022/02/15 QC#57090
            //if (!checkPoSts(bizMsg, bizMsg.poNum_L.getValue())) {
            //    return false;
            //}
            // del end 2022/02/15 QC#57090

            boolean isBlanketPO = ZYPCommonFunc.hasValue(bizMsg.poNum_L) && isBlanketPO(bizMsg.poNum_L.getValue());
            // del start 2022/02/15 QC#57090
            //if (isBlanketPO (bizMsg, isBlanketPO)) {
            //    bizMsg.poNum_L.setErrorInfo(1, "NFBM0290E");
            //    return false;
            //}
            // del end 2022/02/15 QC#57090

            // Varchar Const value(NFBL2040_AP_INV_CATG_CD)
            String apInvCatgCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_AP_INV_CATG_CD, GLBL_CMPY_CD);
            DefCoaValues defCoa = NFBCommonBusiness.getDefCoaValues(GLBL_CMPY_CD);

            // mod start 2022/02/15 QC#57090
            //ssmResult = NFBL2040Query.getInstance().getMdseCatgRecord(bizMsg, apInvCatgCd, defCoa, false, false);
            if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) || (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.poNum_L))) {
                if (!checkPoSts(bizMsg, bizMsg.poNum_L.getValue())) {
                    return false;
                }
                if (isBlanketPO(bizMsg, isBlanketPO)) {
                    bizMsg.poNum_L.setErrorInfo(1, "NFBM0290E");
                    return false;
                }
                ssmResult = NFBL2040Query.getInstance().getMdseCatgRecord(bizMsg, apInvCatgCd, defCoa, false, false);
            } else {
                ssmResult = NFBL2040Query.getInstance().getMdseReturnCatgRecord(bizMsg, apInvCatgCd, defCoa, false);
            }
            // mod end 2022/02/15 QC#57090

            if (ssmResult != null && ssmResult.isCodeNormal()) {
                List resultList = (List) ssmResult.getResultObject();

                // add start 2022/02/15 QC#57090
                if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_L)) {
                    String vndRtrnNum = bizMsg.vndRtrnNum_L.getValue();
                    boolean alreayInvoice = true;
                    for (int i = 0; i < resultList.size(); i++) {
                        Map map = (Map) resultList.get(i);
                        String apLineTpCd = (String) map.get(AP_LINE_TP_CD);
                        String mdseCd = (String) map.get(MDSE_CD);
                        BigDecimal ordQty = (BigDecimal) map.get(PO_QTY);
                        if (AP_LINE_TP.ITEM.equals(apLineTpCd)) {
                            BigDecimal invQty = NFBL2040Query.getInstance().getInvoicedQtyOfVendorReturn(vndRtrnNum, mdseCd);
                            // Credit Qty is negative.
                            if (invQty.compareTo(ordQty) > 0) {
                                alreayInvoice = false;
                            }
                        }
                    }
                    if (alreayInvoice) {
                        bizMsg.vndRtrnNum_L.setErrorInfo(1, "NFBM0283E");
                        return false;
                    }
                }
                // add end 2022/02/15 QC#57090

                if ((resultList.size() + aLineCnt ) > bizMsg.A.length()) {
                    bizMsg.A.setValidCount(bizMsg.A.length());
                    bizMsg.setMessageInfo(NFBM0001W, new String[] {Long.toString(bizMsg.A.length()), Long.toString(resultList.size()) });
                } else {
                    bizMsg.A.setValidCount(resultList.size() + aLineCnt);
                }

                BigDecimal lineTotAmt = BigDecimal.ZERO;

                // Get records for [Line Type] pulldown values.
                List apLineTpPulldownList = NFBL2040CommonLogic.getApLineTpPulldownList();
                int defLineNum = 0;
                // START 2020/02/17 [QC#53413, ADD]
                int maxSqNumA = 0;
                for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(j).apVndInvSqNum_A1) && ZYPCommonFunc.hasValue(bizMsg.A.no(j).xxLineTpCd_A1) && !XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(j).xxLineTpCd_A1.getValue())) {
                        int newInvSqNum = Integer.valueOf(bizMsg.A.no(j).apVndInvSqNum_A1.getValue(), 10);
                        if (maxSqNumA < newInvSqNum) {
                            maxSqNumA = newInvSqNum;
                        }
                    }
                }
                if (maxSqNumA > 0) {
                    maxSqNumA = maxSqNumA + 1;
                    String strNewInvSqNum = String.format(AP_VND_INV_SQ_NUMFORMAT, maxSqNumA);
                    // mod start 2022/02/15 QC#57090
                    //maxSqNum = strNewInvSqNum;
                    ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvSqNum_MX, strNewInvSqNum);
                    // mod end 2022/02/15 QC#57090
                }
                // END   2020/02/17 [QC#53413, ADD]
                for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    if (i < aLineCnt) {
                        lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                        defLineNum++;
                        continue;
                    }

                    Map map = (Map) resultList.get(i - defLineNum);
                    // LINES Tab
                    setLinesTabDetailInfo(bizMsg, map, i, apLineTpPulldownList, i);
                    lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));

                    // DISTRIBUTIONS Tab
                    // Debit Line
                    if (All_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) || (SELECTED_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue()))) {
                        // For Credit line and variance record
                        if (dLineCnt < (bizMsg.A.length() - 2)) {
                            if (setDistributionsTabDebitLineInfo(bizMsg, i, dLineCnt, isBlanketPO)) {
                                dLineCnt++;
                            }
                        }
                    }
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, lineTotAmt); // Line

                // Credit Line
                setDistributionsTabCreditLineInfo(bizMsg, dLineCnt, bizMsg.A.getValidCount() + 1);
                dLineCnt++;
                bizMsg.D.setValidCount(dLineCnt);
            } else {
                // Not found
                bizMsg.setMessageInfo(NFBM0069E);
                return false;
            }
        }

        EZDDebugOutput.println(5, "getRecord================================END", null);
        return true;
    }

    /**
     * Multi PO Type Check
     * 
     * @param bizMsg     NFBL2040CMsg
     * @param linePoType boolean
     * @return boolean
     */
    private static boolean isBlanketPO (NFBL2040CMsg bizMsg, boolean linePoType) {
        if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            if (isBlanketPO(bizMsg.poNum.getValue())) {
                if (!linePoType) {
                    return true;
                }
            } else {
                if (linePoType) {
                    return true;
                }
            }
        }
        return false;
    }
    // END   2020/02/17 [QC#53413, ADD]

    /**
     * Method name: getRecord <dd>The method explanation: Get record
     * for LINES tab. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static boolean getRecord(EZDCMsg cMsg) {
        EZDDebugOutput.println(5, "getRecord================================START", null);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // LINES tab
        bizMsg.A.setValidCount(0);
        bizMsg.A.clear();
        bizMsg.E.setValidCount(0);
        ZYPTableUtil.clear(bizMsg.E);

        // HOLDS tab
        bizMsg.H.setValidCount(0);
        bizMsg.H.clear();

        // DISTRIBUTION tab
        bizMsg.D.setValidCount(0);
        bizMsg.D.clear();

        // START QC#25902,QC#25190,QC#25141
        if (ZYPCommonFunc.hasValue(bizMsg.poNum) || ZYPCommonFunc.hasValue(bizMsg.delyOrdNum_H) || ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)) {
        // END QC#25902,QC#25190,QC#25141
            S21SsmEZDResult ssmResult = null;

            // Varchar Const value(NFBL2040_AP_INV_CATG_CD)
            String apInvCatgCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_AP_INV_CATG_CD, GLBL_CMPY_CD);
            DefCoaValues defCoa = NFBCommonBusiness.getDefCoaValues(GLBL_CMPY_CD);
            // START QC#25902,QC#25190,QC#25141
            if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
                    if (!checkPoSts(bizMsg)) {
                        return false;
                    }
                }
                // START 2020/02/17 [QC#53413, MOD]
                //ssmResult = NFBL2040Query.getInstance().getMdseCatgRecord(bizMsg, apInvCatgCd, defCoa, false);
                ssmResult = NFBL2040Query.getInstance().getMdseCatgRecord(bizMsg, apInvCatgCd, defCoa, false, true);
                // END   2020/02/17 [QC#53413, MOD]
            } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.poNum)) {
                if (!checkPoSts(bizMsg)) {
                    return false;
                }
                // START 2020/02/17 [QC#53413, MOD]
                //ssmResult = NFBL2040Query.getInstance().getMdseCatgRecord(bizMsg, apInvCatgCd, defCoa, false);
                ssmResult = NFBL2040Query.getInstance().getMdseCatgRecord(bizMsg, apInvCatgCd, defCoa, false, true);
                // END   2020/02/17 [QC#53413, MOD]
            } else {
                // mod start 2022/02/15 QC#57090
                //ssmResult = NFBL2040Query.getInstance().getMdseReturnCatgRecord(bizMsg, apInvCatgCd, defCoa);
                ssmResult = NFBL2040Query.getInstance().getMdseReturnCatgRecord(bizMsg, apInvCatgCd, defCoa, true);
                // mod end 2022/02/15 QC#57090
            }
            // END QC#25902,QC#25190,QC#25141

            if (ssmResult != null && ssmResult.isCodeNormal()) {
                List resultList = (List) ssmResult.getResultObject();
                if (resultList.size() > bizMsg.A.length()) {
                    bizMsg.A.setValidCount(bizMsg.A.length());
                    bizMsg.setMessageInfo(NFBM0001W, new String[] {Long.toString(bizMsg.A.length()), Long.toString(resultList.size()) });
                } else {
                    bizMsg.A.setValidCount(resultList.size());
                }

                BigDecimal lineTotAmt = BigDecimal.ZERO;
                BigDecimal lineTotAmtForDistribution = BigDecimal.ZERO;

                // Distributions tab
                int validCountD = 0;
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllocTpCd_D, All_LINES); // Radio
                // button
                // default
                // value

                String invDt = EMPTY_STRING;

                // START 2019/02/07 [QC#30136, ADD]
                boolean isBlanketPO = ZYPCommonFunc.hasValue(bizMsg.poNum) && isBlanketPO(bizMsg.poNum.getValue());
                // END   2019/02/07 [QC#30136, ADD]

                // Get records for [Line Type] pulldown values.
                List apLineTpPulldownList = NFBL2040CommonLogic.getApLineTpPulldownList();
                int intMaxLineNumber = 0;
                for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    Map map = (Map) resultList.get(i);
                    if (i == 0) {
                        // Hidden Value
                        setHiddenValueInfo(bizMsg, map);
                        // HEADER Tab
                        setHeadersTabInfo(bizMsg, map, invDt);
                    }
                    // LINES Tab
                    // START 2019/01/29 [QC#30056, MOD]
//                    setLinesTabDetailInfo(bizMsg, map, i, apLineTpPulldownList);
                    setLinesTabDetailInfo(bizMsg, map, i, apLineTpPulldownList, i);
                    // END   2019/01/29 [QC#30056, MOD]
                    lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));

                    // DISTRIBUTIONS Tab
                    // Debit Line
                    if (All_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) || (SELECTED_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue()))) {
                        lineTotAmtForDistribution = lineTotAmtForDistribution.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).acInvJrnlCostAmt_A1.getValue()));
                        // For Credit line and variance record
                        if (validCountD < (bizMsg.A.length() - 2)) {
                            // START 2019/01/29 [QC#30056, MOD]
//                            setDistributionsTabDebitLineInfo(bizMsg, i, validCountD);
//                            validCountD++;
                            // START 2019/02/07 [QC#30136, ADD]
                            // if (setDistributionsTabDebitLineInfo(bizMsg, i, validCountD)) {
                            //     validCountD++;
                            // }
                            if (setDistributionsTabDebitLineInfo(bizMsg, i, validCountD, isBlanketPO)) {
                                validCountD++;
                            }
                            // END   2019/02/07 [QC#30136, ADD]
                            // END   2019/01/29 [QC#30056, MOD]
                            int intCurrentLineNumber = Integer.parseInt(bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
                            if (intMaxLineNumber < intCurrentLineNumber) {
                                intMaxLineNumber = intCurrentLineNumber;
                            }
                        }
                    }
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, lineTotAmt); // Line
                // Total

                // Variance Line
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_PO.getValue()) && NFBCommonBusiness.chkNull(bizMsg.invAmt.getValue()).compareTo(lineTotAmtForDistribution) != 0) {
                    BigDecimal varAmt = bizMsg.invAmt.getValue().subtract(lineTotAmtForDistribution);
                    setDistributionsTabVarDebitLineInfo(bizMsg, varAmt, validCountD, Integer.toString(intMaxLineNumber + 1));
                    validCountD++;
                }

                // Credit Line
                setDistributionsTabCreditLineInfo(bizMsg, validCountD, bizMsg.A.getValidCount() + 1);
                validCountD++;
                bizMsg.D.setValidCount(validCountD);
                
                // QC#27029 Add Check.
                if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) //
                        && ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)) {
                    boolean alreayInvoice = true;
                    for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                        if (AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                            BigDecimal invQty = NFBL2040Query.getInstance().getInvoicedQtyOfVendorReturn(bizMsg.vndRtrnNum.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue());
                            BigDecimal ordQty = bizMsg.A.no(i).poQty_A1.getValue();
                            // Credit Qty is negative.
                            if (invQty.compareTo(ordQty) > 0) {
                                alreayInvoice = false;
                            }
                        }
                    }

                    if (alreayInvoice) {
                        // Error.
                        // NFBM0283E : You have already created Invoice for the number of returns.
                        bizMsg.vndRtrnNum.setErrorInfo(1, "NFBM0283E");

                        // Clear.
                        // Clear information on HEADER tab
                        clearHeader(bizMsg, ZYPConstant.FLG_OFF_N);
                        // Clear information on HOLDS tab
                        clearHolds(bizMsg);
                        // Clear information on LINES tab
                        clearLines(bizMsg);
                        // Clear information on DISTRIBUTIONS tab
                        clearDistributions(bizMsg);
                    }
                }
            } else {
                // Not found
                bizMsg.setMessageInfo(NFBM0069E);
                return false;
            }
        } else {
            bizMsg.A.setValidCount(0);
        }

        EZDDebugOutput.println(5, "getRecord================================END", null);
        return true;
    }

    /**
     * Method name: setSearchConditionInfo <dd>The method explanation:
     * Set DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param map Map
     * @param invDt String
     */
    @SuppressWarnings("unchecked")
    public static void setSearchConditionInfo(EZDCMsg cMsg, Map map) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.cmApInvTpCd_S, (String) map.get(CM_AP_INV_TP_CD)); // Invoice
        // Type
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum, (String) map.get(PO_NUM)); // PO
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_H, (String) map.get(DELY_ORD_NUM)); // Receipt
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_H, (String) map.get(RWS_NUM)); // RWS
        // Number
        // START QC#25902,QC#25190,QC#25141
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum, (String) map.get(VND_RTRN_NUM)); // Vendor Return Number
        // END QC#25902,QC#25190,QC#25141
    }

    /**
     * Method name: setHiddenValueInfo <dd>The method explanation: Set
     * DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param map Map
     * @param invDt String
     */
    @SuppressWarnings("unchecked")
    public static void setHiddenValueInfo(EZDCMsg cMsg, Map map) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.poStsCd_HD, (String) map.get(PO_STS_CD)); // PO
        // Status
        // Code
    }

    /**
     * Method name: setHeadersTabInfo <dd>The method explanation: Set
     * DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param map Map
     * @param invDt String
     */
    @SuppressWarnings("unchecked")
    public static void setHeadersTabInfo(EZDCMsg cMsg, Map map, String invDt) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.dplyVndNm, (String) map.get(DPLY_VND_NM)); // Vendor
        // Name
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndCd, (String) map.get(AP_VND_CD)); // Location
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum, (String) map.get(AP_VND_INV_NUM)); // Invoice
        // Number
        invDt = (String) map.get(INV_DT);
        // QC#22144
        if (ZYPCommonFunc.hasValue(invDt)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invDt, invDt); // Invoice
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.invDt, ZYPDateUtil.getSalesDate()); // Invoice
        }


        // Date
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PA, (String) map.get(PAY_ALONE_FLG));
        ZYPEZDItemValueSetter.setValue(bizMsg.apPmtChkNum, (String) map.get(AP_PMT_CHK_NUM)); // Payment
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtMethCd, (String) map.get(VND_PMT_METH_CD)); // Payment
        // Method
        // Code
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtMethDescTxt, (String) map.get(VND_PMT_METH_DESC_TXT)); // Payment
        // Method
        // Name
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsCd, (String) map.get(ACCT_INV_STS_CD)); // Accounting
        // Invoice
        // Status
        // Code
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsDescTxt, (String) map.get(ACCT_INV_STS_DESC_TXT)); // Status
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, (String) map.get(PRNT_VND_CD)); // Vendor
        // Number
        // QC#21653 Delete cmInvMatchCd_S
        // ZYPEZDItemValueSetter.setValue(bizMsg.cmInvMatchCd_S, (String) map.get(CM_INV_MATCH_CD)); // Invoice
        // Matching
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HT, (String) map.get(PO_NUM)); // PO Number 
        // START QC#25902,QC#25190,QC#25141
        ZYPEZDItemValueSetter.setValue(bizMsg.vndRtrnNum_H, (String) map.get(VND_RTRN_NUM)); // Vendor Return#
        // END QC#25902,QC#25190,QC#25141
        ZYPEZDItemValueSetter.setValue(bizMsg.invAmt, (BigDecimal) map.get(AC_INV_TOT_COST_AMT)); // Invoice
        // Amount
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermCd, (String) map.get(VND_PMT_TERM_CD)); // Payment
        // Term
        // Code
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermDescTxt, (String) map.get(VND_PMT_TERM_DESC_TXT)); // Terms
        ZYPEZDItemValueSetter.setValue(bizMsg.hdrPmtAmt, (BigDecimal) map.get(HDR_PMT_AMT)); // Amount
        // Paid
        ZYPEZDItemValueSetter.setValue(bizMsg.termNetDueDt, (String) map.get(TERM_NET_DUE_DT)); // Terms
        // Date
        ZYPEZDItemValueSetter.setValue(bizMsg.poApvlDt, (String) map.get(PO_APVL_DT)); // PO
        // Date
        ZYPEZDItemValueSetter.setValue(bizMsg.entPoDtlDealNetAmt_TO, (BigDecimal) map.get(ENT_PO_DTL_DEAL_NET_AMT)); // PO
        // Amount
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_DH, (String) map.get(DELY_ORD_NUM)); // Receipt
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_DH, (String) map.get(RWS_NUM)); // RWS
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.invHdrDescTxt, (String) map.get(INV_HDR_DESC_TXT)); // Description
        ZYPEZDItemValueSetter.setValue(bizMsg.acOcTotDiscAmt, (BigDecimal) map.get(AC_OC_TOT_DISC_AMT)); // Discount
        ZYPEZDItemValueSetter.setValue(bizMsg.apInvCatgDescTxt, (String) map.get(AP_INV_CATG_DESC_TXT)); // Source
        ZYPEZDItemValueSetter.setValue(bizMsg.pmtDt, (String) map.get(PMT_DT)); // Payment
        // Date
        ZYPEZDItemValueSetter.setValue(bizMsg.acctDt, (String) map.get(INV_AUTH_DT)); // Accounting
        // Date

        //START 2017/03/17 E.Kameishi [QC#14205, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratTs, (String) map.get(XX_REC_HIST_CRAT_TS));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM)));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdTs, (String) map.get(XX_REC_HIST_UPD_TS));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM)));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRecHistTblNm, (String) map.get(XX_REC_HIST_TBL_NM));
        //END 2017/03/17 E.Kameishi [QC#14205, ADD]

        // START 2017/12/26 [QC#23022, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.apInvCatgCd, (String) map.get(AP_INV_CATG_CD));
        // END   2017/12/26 [QC#23022, ADD]

        // START 2018/02/27 [QC#23505, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvSqNum, (String) map.get(AP_VND_INV_SQ_NUM));
        if (!ZYPCommonFunc.hasValue(bizMsg.apVndInvSqNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        }
        bizMsg.xxChkBox_CR.clear();
        if (!AP_VND_INV_SQ_NUM_00.equals(bizMsg.apVndInvSqNum.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_CR, ZYPConstant.FLG_ON_Y);
        }
        // END   2018/02/27 [QC#23505, ADD]

        // START 2018/08/02 [QC#27025-1, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime, (String) map.get(EZUPTIME));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone, (String) map.get(EZUPTIMEZONE));
        // END   2018/08/02 [QC#27025-1, ADD]
        // START 2019/07/18 [QC#51675, ADD]
        bizMsg.xxWrnSkipFlg.clear();
        // END   2019/07/18 [QC#51675, ADD]
    }

    /**
     * Method name: setHoldsTabHeaderInfo <dd>The method explanation:
     * Set DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param map Map
     */
    @SuppressWarnings("unchecked")
    public static void setHoldsTabHeaderInfo(EZDCMsg cMsg, Map map) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum_HH, (String) map.get(AP_VND_INV_NUM)); // Invoice
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.invAmt_HH, (BigDecimal) map.get(AC_INV_TOT_COST_AMT)); // Invoice
        // Amount

    }

    /**
     * Method name: setLinesTabDetailInfo <dd>The method explanation:
     * Set DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param map Map
     * @param i int
     * @param apLineTpPulldownList List
     */
    @SuppressWarnings("unchecked")
    // START 2019/01/29 [QC#30056, MOD]
//    public static void setLinesTabDetailInfo(EZDCMsg cMsg, Map map, int i, List apLineTpPulldownList) {
    public static void setLinesTabDetailInfo(EZDCMsg cMsg, Map map, int i, List apLineTpPulldownList, int lineNum) {
    // END   2019/01/29 [QC#30056, MOD]

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        String cmInvAccrtDistLineNum = (String) map.get(AP_VND_INV_LINE_NUM);
        // START 2018/03/22 [QC#20316, MOD]
        // if (!ZYPCommonFunc.hasValue(cmInvAccrtDistLineNum)) {
        //     cmInvAccrtDistLineNum = String.format(PO_ORD_DTL_LINE_NUM_FORMAT, i + 1); // Line
        //     // Number
        // }
        if (!ZYPCommonFunc.hasValue(cmInvAccrtDistLineNum)) {
            // START 2019/01/29 [QC#30056, MOD]
//            cmInvAccrtDistLineNum = String.format(AP_VND_INV_LINE_NUM_FORMAT, i + 1); // Line Number
            cmInvAccrtDistLineNum = String.format(AP_VND_INV_LINE_NUM_FORMAT, lineNum + 1);
            // START 2019/01/29 [QC#30056, MOD]
        }
        // START 2018/03/22 [QC#20316, MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxDtlLineNum_A1, cmInvAccrtDistLineNum);

        // Create [Line Type] pulldown for each rows.
        createApLineTpPulldownList(apLineTpPulldownList, i, bizMsg);
        if (!AP_LINE_TP.VARIANCE.equals((String) map.get(AP_LINE_TP_CD))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apLineTpCd_A1, (String) map.get(AP_LINE_TP_CD)); // Line
            // Type
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseCd_A1, (String) map.get(MDSE_CD)); // Item
            // QC#14858-Sol#060 Add xxMdseCd
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxMdseCd_A1, bizMsg.A.no(i).mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxCntnrFlg_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseDescShortTxt_A1, (String) map.get(MDSE_DESC_SHORT_TXT)); // Line_Description

            // Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).acInvJrnlCostAmt_A1, (BigDecimal) map.get(AC_INV_JRNL_COST_AMT)); // AC_INV_JRNL_COST_AMT
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).vndUomCd_A1, (String) map.get(UOM_CD)); // Unit
            // Of
            // Measure
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxCmntTxt_A1, (String) map.get(XX_CMNT_TXT)); // Charge
            // Account
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apAcctDescTxt_A1, (String) map.get(AP_ACCT_DESC_TXT)); // Account
            // Description
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1, (BigDecimal) map.get(ENT_DEAL_NET_UNIT_PRC_AMT)); // Unit
            // Price
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invQty_A1, (BigDecimal) map.get(INV_QTY)); // Invoice
            // Quantity
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).poQty_A1, (BigDecimal) map.get(PO_QTY)); // Ordered
            // Quantity
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invRcvQty_A1, (BigDecimal) map.get(INV_RCV_QTY)); // Received
            // Qunatity
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apRejQty_A1, (BigDecimal) map.get(AP_REJ_QTY)); // Rejected

            // START 2021/04/22 R.Azucena [QC#56829, ADD]
            String poOrdNum = (String) map.get(PO_ORD_NUM);
            String poNum = (String) map.get(PO_NUM);
            String vndRtrnNum = (String) map.get(VND_RTRN_NUM);
            BigDecimal invQtyOnHoldCnt = BigDecimal.ZERO;

            // START 2021/06/04 H.Dimay [QC#56829, DEL]
            //if (ZYPCommonFunc.hasValue((String) map.get(AP_VND_INV_NUM))) {
            // END 2021/06/04 H.Dimay [QC#56829, DEL]
            if (ZYPCommonFunc.hasValue(poOrdNum)) {
                invQtyOnHoldCnt = getInvQtyOnHoldCnt(bizMsg, poOrdNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM), (String) map.get(AP_VND_INV_NUM));
            } else if (ZYPCommonFunc.hasValue(poNum)) {
                invQtyOnHoldCnt = getInvQtyOnHoldCnt(bizMsg, poNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM), (String) map.get(AP_VND_INV_NUM));
            } else if (ZYPCommonFunc.hasValue(vndRtrnNum)) {
                invQtyOnHoldCnt = getInvQtyOnHoldCntVndRtrn(bizMsg, vndRtrnNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM), (String) map.get(AP_VND_INV_NUM));
            }
            // START 2021/06/04 H.Dimay [QC#56829, DEL]
            //}
            // END 2021/06/04 H.Dimay [QC#56829, DEL]

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).slsHldQty_A1, invQtyOnHoldCnt); // Invoice Qty on HOLD
            // END 2021/04/22 R.Azucena [QC#56829, ADD]

            // Quantity
            // QC#21666
            if (AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {    // Line Type = 'ITEM' 
                BigDecimal invoicedQty = BigDecimal.ZERO;
                BigDecimal alreadyInvoicedQty = BigDecimal.ZERO;
                // START 2021/04/22 R.Azucena [QC#56829, DEL]
                // BigDecimal poQty = (BigDecimal) map.get(PO_QTY);
                // String poOrdNum = (String) map.get(PO_ORD_NUM);
                // String poNum = (String) map.get(PO_NUM);
                // START 2018/08/15 [QC#27029-1, ADD]
                // String vndRtrnNum = (String) map.get(VND_RTRN_NUM);
                // END   2018/08/15 [QC#27029-1, ADD]
                // END 2021/04/22 R.Azucena [QC#56829, DEL]
                if (ZYPCommonFunc.hasValue(poOrdNum)) {
                    // Invoice_Type = PO_MATCH_Search_Action
                    // START 2018/03/22 [QC#20316, MOD]
                    // alreadyInvoicedQty = getSumBilledQty(poOrdNum, (String) map.get(MDSE_CD));
                    // START 2018/11/20 [QC#28660, MOD]
                    // if (ZYPCommonFunc.hasValue((String) map.get(PO_ORD_DTL_LINE_NUM))) {
                    //     alreadyInvoicedQty = getSumBilledQty(poOrdNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM));
                    // } else {
                    //     alreadyInvoicedQty = getSumBilledQty(poOrdNum, (String) map.get(MDSE_CD));
                    // }
                    if (ZYPCommonFunc.hasValue((String) map.get(PO_ORD_DTL_LINE_NUM))) {
                        alreadyInvoicedQty = getSumBilledQty(bizMsg, poOrdNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM));
                    } else {
                        alreadyInvoicedQty = getSumBilledQty(bizMsg, poOrdNum, (String) map.get(MDSE_CD));
                    }
                    // END   2018/11/20 [QC#28660, MOD]
                    // END   2018/03/22 [QC#20316, MOD]

                    // START 2018/03/13 [QC#24274, DEL]
                    // invoicedQty = poQty.subtract(alreadyInvoicedQty);
                    // END   2018/03/13 [QC#24274, DEL]

                    // QC#14858-Sol#060 Check Text item.
                    if (isTextItem(bizMsg.A.no(i).mdseCd_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseDescShortTxt_A1, getMdseDescriptionFromPo(poOrdNum, bizMsg.A.no(i).mdseCd_A1.getValue()));
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
                        bizMsg.A.no(i).mdseCd_A1.clear();
                    }

                    // START 2018/03/13 [QC#24274, DEL]
                    // QC#23873 Update. Blanket po is not calcurate.
                    // if(isBlanketPO(poOrdNum)){
                    //     invoicedQty = BigDecimal.ONE;
                    // }
                    // END   2018/03/13 [QC#24274, DEL]

                } else if (ZYPCommonFunc.hasValue(poNum)) { // ReSearch
                                                            // Action
                    // START 2018/03/13 [QC#24274, DEL]
                    invoicedQty = (BigDecimal) map.get(AP_BILL_QTY);
                    // END   2018/03/13 [QC#24274, DEL]
                    // START 2018/03/22 [QC#20316, MOD]
                    // alreadyInvoicedQty = getSumBilledQty(poNum, (String) map.get(MDSE_CD));
                    // START 2018/11/20 [QC#28660, MOD]
                    // if (ZYPCommonFunc.hasValue((String) map.get(PO_ORD_DTL_LINE_NUM))) {
                    //     alreadyInvoicedQty = getSumBilledQty(poNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM));
                    // } else {
                    //     alreadyInvoicedQty = getSumBilledQty(poNum, (String) map.get(MDSE_CD));
                    // }
                    if (ZYPCommonFunc.hasValue((String) map.get(PO_ORD_DTL_LINE_NUM))) {
                        alreadyInvoicedQty = getSumBilledQty(bizMsg, poNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM));
                    } else {
                        alreadyInvoicedQty = getSumBilledQty(bizMsg, poNum, (String) map.get(MDSE_CD));
                    }
                    // END   2018/11/20 [QC#28660, MOD]
                    // END   2018/03/22 [QC#20316, MOD]
                    // START 2018/03/13 [QC#24274, DEL]
                    // invoicedQty = (BigDecimal) map.get(AP_BILL_QTY);
                    // END   2018/03/13 [QC#24274, DEL]

                    // QC#14858-Sol#060 Check Text item.
                    if (isTextItem(bizMsg.A.no(i).mdseCd_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseDescShortTxt_A1, getMdseDescriptionFromPo(poNum, bizMsg.A.no(i).mdseCd_A1.getValue()));
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
                        bizMsg.A.no(i).mdseCd_A1.clear();
                    }

                // START 2018/08/15 [QC#27029-1, ADD]
                } else if (ZYPCommonFunc.hasValue(vndRtrnNum)) {
                    invoicedQty = (BigDecimal) map.get(AP_BILL_QTY);
                    // START 2018/11/20 [QC#28660, MOD]
                    // alreadyInvoicedQty = getSumBilledQtyVndRtrn(vndRtrnNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM));
                    alreadyInvoicedQty = getSumBilledQtyVndRtrn(bizMsg, vndRtrnNum, (String) map.get(MDSE_CD), (String) map.get(PO_ORD_DTL_LINE_NUM));
                    // END   2018/11/20 [QC#28660, MOD]

                    if (isTextItem(bizMsg.A.no(i).mdseCd_A1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).mdseDescShortTxt_A1, getMdseDescriptionFromPo(poNum, bizMsg.A.no(i).mdseCd_A1.getValue()));
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
                        bizMsg.A.no(i).mdseCd_A1.clear();
                    }

                // END   2018/08/15 [QC#27029-1, ADD]
                } else { // Invoice Type = STANDARD or CREDIT MEMO
                    alreadyInvoicedQty = (BigDecimal) map.get(AP_BILL_QTY);
                    // START QC#25902,QC#25190,QC#25141
                    invoicedQty = (BigDecimal) map.get(AP_BILL_QTY);
                    // END QC#25902,QC#25190,QC#25141
                    // START 2018/03/13 [QC#24274, DEL]
                    // invoicedQty = (BigDecimal) map.get(AP_BILL_QTY);
                    // END   2018/03/13 [QC#24274, DEL]
                }
                // START 2020/07/02 R.Kurahashi [QC#56696,MOD]
                //ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apBillQty_A1, invoicedQty); // Invoiced_Qty
                // START 2020/09/09 R.Kurahashi [QC#57668,MOD]
                //if (invoicedQty.compareTo(BigDecimal.ZERO) > 0) {
                // START 2021/03/08 H.Dimay [QC#58491, MOD]
                //if (ZYPCommonFunc.hasValue(invoicedQty) && invoicedQty.compareTo(BigDecimal.ZERO) > 0) {
                if (ZYPCommonFunc.hasValue(invoicedQty) && invoicedQty.compareTo(BigDecimal.ZERO) != 0) {
                // END 2021/03/08 H.Dimay [QC#58491, MOD]
                // END 2020/09/09 R.Kurahashi [QC#57668,MOD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apBillQty_A1, invoicedQty); // Invoiced_Qty
                }
                // END 2020/07/02 R.Kurahashi [QC#56696,MOD]
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxInvQty_A1, alreadyInvoicedQty); // Already_Invoiced_Qty

                // START 2018/03/13 [QC#24274, DEL]
                // QC#22144
                // BigDecimal lineAmt = BigDecimal.ZERO;
                // BigDecimal unitPrice = (BigDecimal) map.get(ENT_DEAL_NET_UNIT_PRC_AMT);
                // if (ZYPCommonFunc.hasValue(poOrdNum)) {
                //     // Invoice_Type = PO_MATCH_Search_Action
                //     if (ZYPCommonFunc.hasValue(unitPrice)) {
                //
                //         // QC#23873 Update. Blanket po is not calcurate.
                //         if(isBlanketPO(poOrdNum)){
                //             lineAmt = unitPrice.multiply(invoicedQty);
                //         } else {
                //             // Unit_Price * ( PO Qty - Already Invoiced Qty)
                //             lineAmt = unitPrice.multiply(poQty.subtract(alreadyInvoicedQty)); 
                //         }
                // 
                //     } else {
                //         lineAmt = (BigDecimal) map.get(LINE_AMT);
                //     }
                //     // START 2018/02/20 [QC#21703, DEL]
                //     // if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) {
                //     //     // Invoice Type = CREDIT MEMO
                //     //     lineAmt = lineAmt.negate();
                //     // }
                //     // END   2018/02/20 [QC#21703, DEL]
                // } else {
                //     lineAmt = (BigDecimal) map.get(LINE_AMT);
                // }
                // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxInvAmt_A1, lineAmt); // Line_Amount
                // END   2018/03/13 [QC#24274, DEL]
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxInvAmt_A1, (BigDecimal) map.get(LINE_AMT)); // Line_Amount
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxInvAmt_A1, (BigDecimal) map.get(LINE_AMT));
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dsContrNum_A1, (String) map.get(CONTR_NUM)); // Contract
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).custDlrCd_A1, (String) map.get(CUST_DLR_CD)); // Dealer
            // Code
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).serNum_A1, (String) map.get(SER_NUM)); // Serial
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).csmpInvNum_A1, (String) map.get(CSMP_INV_NUM)); // CSMP
            // Invoice
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxInstlFullAddr_A1, (String) map.get(ISTL_LOC)); // Install
            // Location
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).cmStkInPk_A1, (BigDecimal) map.get(CM_STK_IN_PK));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invAsgFlg_A1, (String) map.get(INV_ASG_FLG));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).origVndInvNum_A1, (String) map.get(ORIG_VND_INV_NUM)); // Original
            // Vendor
            // Invoice
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).origVndInvSqNum_A1, (String) map.get(ORIG_VND_INV_SQ_NUM)); // Original
            // Vendor
            // Invoice
            // Sequence
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).origDelyOrdNum_A1, (String) map.get(ORIG_DELY_ORD_NUM)); // Original
            // Delivery
            // Order
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).poOrdDtlLineNum_A1, (String) map.get(PO_ORD_DTL_LINE_NUM)); // PO
            // Order
            // Detail
            // Line
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).poMatchTpCd_A1, (String) map.get(PO_MATCH_TP_CD)); // PO
            // Match
            // Type
            // Code
            // ADD QC#19219 START
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A1, (BigDecimal) map.get(ENT_PO_DTL_DEAL_NET_AMT_LINE)); // PO
            // Amount
            // (Line
            // unit)
            // ADD QC#19219 END

            // QC#14858-Sol#060
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A2, (BigDecimal) map.get(CSI_ENT_PO_DTL_DEAL_NET_AMT));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entPoDtlFuncNetAmt_A2, (BigDecimal) map.get(CSI_ENT_PO_DTL_FUNC_NET_AMT));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2, (BigDecimal) map.get(CSI_ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entFuncNetUnitPrcAmt_A2, (BigDecimal) map.get(CSI_ENT_FUNC_NET_UNIT_PRC_AMT)); 


            //START 2017/03/17 E.Kameishi [QC#14205, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratTs_A1, (String) map.get(XX_REC_HIST_CRAT_TS_A1));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM_A1)));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdTs_A1, (String) map.get(XX_REC_HIST_UPD_TS_A1));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM_A1)));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxRecHistTblNm_A1, (String) map.get(XX_REC_HIST_TBL_NM_A1));
            //END 2017/03/17 E.Kameishi [QC#14205, ADD]
            
            // START 2017/12/26 [QC#23022, ADD]
            if (isBlankMdseCdByApInvCatgCd(bizMsg)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxCntnrFlg_A1, ZYPConstant.FLG_ON_Y);
                bizMsg.A.no(i).mdseCd_A1.clear();
            }
            // END   2017/12/26 [QC#23022, ADD]

            // START 2018/02/27 [QC#23505, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).poNum_A1 , (String) map.get(LINE_PO_NUM));
            // START QC#25902,QC#25190,QC#25141
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apVndInvSqNum_A1, (String) map.get(LINE_AP_VND_INV_SQ_NUM));
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).apVndInvSqNum_A1)) {
                // mod start 2022/02/15 QC#57090
                //if (ZYPCommonFunc.hasValue(maxSqNum)) {
                //    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apVndInvSqNum_A1, maxSqNum);
                if (ZYPCommonFunc.hasValue(bizMsg.apVndInvSqNum_MX)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apVndInvSqNum_A1, bizMsg.apVndInvSqNum_MX);
                // mod end 2022/02/15 QC#57090
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apVndInvSqNum_A1, AP_VND_INV_SQ_NUM_00);
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).vndRtrnNum_A1 , (String) map.get(VND_RTRN_NUM));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).poLineStsCd_A1, (String) map.get(PO_LINE_STS_CD));
            String lineTpCd = getLineTpCd(bizMsg, bizMsg.A.no(i).xxInvAmt_A1.getValue(), bizMsg.A.no(i).apVndInvSqNum_A1.getValue());
            if (!CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) //
                    && PO_LINE_STS.CLOSED.equals(bizMsg.A.no(i).poLineStsCd_A1.getValue())) {
                lineTpCd = XX_LINE_TP_CD_VALID;
            }
            
            else if (bizMsg.xxChkBox_CR.getValue().equals("Y")) {
                lineTpCd = XX_LINE_TP_CD_VALID;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxLineTpCd_A1, lineTpCd);
            // START 2018/10/11 [QC#28038, ADD]
            if (XX_LINE_TP_CD_CREDIT.equals(lineTpCd)) {
            	BigDecimal alrdyInvQty = bizMsg.A.no(i).xxInvQty_A1.getValue();
                if (ZYPCommonFunc.hasValue(alrdyInvQty) && alrdyInvQty.compareTo(BigDecimal.ZERO) > 0) {
                	ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxInvQty_A1, alrdyInvQty.negate());
                }
            }
            // END 2018/10/11 [QC#28038, ADD]
            // END QC#25902,QC#25190,QC#25141
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).invCrctDt_A1 , (String) map.get(INV_CRCT_DT));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).delyOrdNum_A1 , (String) map.get(DELY_ORD_NUM));
            // START 2018/05/07 [QC#25828, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3, (BigDecimal) map.get(PD_ENT_DEAL_NET_UNIT_PRC_AMT));
            // END   2018/05/07 [QC#25828, ADD]
            // START 2018/07/31 [QC#27029, ADD]
            if(CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) //
                    && ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)){
                // START 2018/08/15 [QC#27029-1, MOD]
                // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3, (BigDecimal) map.get(ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3, (BigDecimal) map.get(VRD_ENT_DEAL_NET_UNIT_PRC_AMT));
                // END   2018/08/15 [QC#27029-1, MOD]
            }
            // END 2018/07/31 [QC#27029, ADD]
            // START 2018/06/26 S.Katsuma [QC#24617,ADD]
            // START 2021/04/22 R.Azucena [QC#56829, DEL]
            // String poOrdNum = null;
            // END 2021/04/22 R.Azucena [QC#56829, DEL]
            if (ZYPCommonFunc.hasValue((String) map.get(PO_ORD_NUM))) {
                poOrdNum = (String) map.get(PO_ORD_NUM);
            } else if (ZYPCommonFunc.hasValue((String) map.get(LINE_PO_NUM))) {
                poOrdNum = (String) map.get(LINE_PO_NUM);
            }
            if (ZYPCommonFunc.hasValue(poOrdNum) && ZYPCommonFunc.hasValue((String) map.get(PO_ORD_DTL_LINE_NUM))) {
                String rtlWhNm = NFBL2040Query.getInstance().getRtlWhNmByPo(poOrdNum, (String) map.get(PO_ORD_DTL_LINE_NUM));
                if (ZYPCommonFunc.hasValue(rtlWhNm)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).locNm_A1, rtlWhNm);
                }
            }
            // END 2018/06/26 S.Katsuma [QC#24617,ADD]
            // START 2018/07/17 S.Katsuma [QC#27078,ADD]
            if (ZYPCommonFunc.hasValue(poOrdNum)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dispPoDtlLineNum_A1, (String) map.get(DISP_PO_DTL_LINE_NUM));
            }
            // END 2018/07/17 S.Katsuma [QC#27078,ADD]
            // START 2020/02/17 H.Ikeda [QC#53413,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).poApvlDt_A1, (String) map.get(PO_APVL_DT));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A3, (BigDecimal) map.get(ENT_PO_DTL_DEAL_NET_AMT));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).delyOrdNum_A2, (String) map.get(DELY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).rwsNum_A1, (String) map.get(RWS_NUM));
            // END   2020/02/17 H.Ikeda [QC#53413,ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).apLineTpCd_E1, (String) map.get(AP_LINE_TP_CD)); // Line
            // Type
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).mdseCd_E1, (String) map.get(MDSE_CD)); // Item
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).mdseDescShortTxt_E1, (String) map.get(MDSE_DESC_SHORT_TXT)); // Line
            // Description
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).xxInvAmt_E1, (BigDecimal) map.get(LINE_AMT)); // Line
            // Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).vndUomCd_E1, (String) map.get(UOM_CD)); // Unit
            // Of
            // Measure
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).xxCmntTxt_E1, (String) map.get(XX_CMNT_TXT)); // Charge
            // Account
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).apAcctDescTxt_E1, (String) map.get(AP_ACCT_DESC_TXT)); // Account
            // Description
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).dealGrsUnitPrcAmt_E1, (BigDecimal) map.get(ENT_DEAL_NET_UNIT_PRC_AMT)); // Unit
            // Price
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).invQty_E1, (BigDecimal) map.get(INV_QTY)); // Invoice
            // Quantity
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).poQty_E1, (BigDecimal) map.get(PO_QTY)); // Ordered
            // Quantity
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).invRcvQty_E1, (BigDecimal) map.get(INV_RCV_QTY)); // Received
            // Qunatity
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).apRejQty_E1, (BigDecimal) map.get(AP_REJ_QTY)); // Rejected
            // Quantity
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).apBillQty_E1, (BigDecimal) map.get(AP_BILL_QTY)); // Billed
            // Quantity
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).dsContrNum_E1, (String) map.get(CONTR_NUM)); // Contract
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).custDlrCd_E1, (String) map.get(CUST_DLR_CD)); // Dealer
            // Code
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).serNum_E1, (String) map.get(SER_NUM)); // Serial
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).csmpInvNum_E1, (String) map.get(CSMP_INV_NUM)); // CSMP
            // Invoice
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).xxInstlFullAddr_E1, (String) map.get(ISTL_LOC)); // Install
            // Location
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).cmStkInPk_E1, (BigDecimal) map.get(CM_STK_IN_PK));
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).invAsgFlg_E1, (String) map.get(INV_ASG_FLG));
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).origVndInvNum_E1, (String) map.get(ORIG_VND_INV_NUM)); // Original
            // Vendor
            // Invoice
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).origVndInvSqNum_E1, (String) map.get(ORIG_VND_INV_SQ_NUM)); // Original
            // Vendor
            // Invoice
            // Sequence
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).origDelyOrdNum_E1, (String) map.get(ORIG_DELY_ORD_NUM)); // Original
            // Delivery
            // Order
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).poOrdDtlLineNum_E1, (String) map.get(PO_ORD_DTL_LINE_NUM)); // PO
            // Order
            // Detail
            // Line
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).poMatchTpCd_E1, (String) map.get(PO_MATCH_TP_CD)); // PO
            // Match
            // Type
            // Code
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(0).entPoDtlDealNetAmt_E1, (BigDecimal) map.get(ENT_PO_DTL_DEAL_NET_AMT)); // PO
            // Amount
            // (Line
            // unit)
            //START 2017/03/17 E.Kameishi [QC#14205, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistCratTs_E1, (String) map.get(XX_REC_HIST_CRAT_TS_A1));
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistCratByNm_E1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_CRAT_BY_NM_A1)));
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistUpdTs_E1, (String) map.get(XX_REC_HIST_UPD_TS_A1));
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistUpdByNm_E1, ZYPRecHistUtil.getFullNameForRecHist((String) map.get(XX_REC_HIST_UPD_BY_NM_A1)));
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxRecHistTblNm_E1, (String) map.get(XX_REC_HIST_TBL_NM_A1));
            //END 2017/03/17 E.Kameishi [QC#14205, ADD]

            // START 2018/02/27 [QC#23505, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).poNum_E1 , (String) map.get(LINE_PO_NUM));
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).invCrctDt_E1 , (String) map.get(INV_CRCT_DT));
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).apVndInvSqNum_E1, (String) map.get(LINE_AP_VND_INV_SQ_NUM));
            if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).apVndInvSqNum_E1)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).apVndInvSqNum_E1, AP_VND_INV_SQ_NUM_00);
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxLineTpCd_E1, 
                    getLineTpCd(bizMsg, bizMsg.E.no(i).xxInvAmt_E1.getValue(), bizMsg.E.no(i).apVndInvSqNum_E1.getValue()));
            // END   2018/02/27 [QC#23505, ADD]
            // START 2018/03/13 [QC#24089, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).poLineStsCd_E1, (String) map.get(PO_LINE_STS_CD));
            // END   2018/03/13 [QC#24089, ADD]

            bizMsg.E.setValidCount(1);
        }
    }

    /**
     * isTextItem
     * @param mdseCd
     * @return true:Yes / false:No
     */
    private static boolean isTextItem(String mdseCd) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getTextItem(mdseCd);

        if (ssmResult.isCodeNormal() //
                && ssmResult.getQueryResultCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getMdseDescriptionFromPo
     * @param poOrdNum String
     * @param mdseCd String
     * @return PO_DTL.MDSE_DESC_SHORT_TXT
     */
    private static String getMdseDescriptionFromPo(String poOrdNum, String mdseCd) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getMdseDescTxtFromPO(poOrdNum, mdseCd);

        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        } else {
            return "";
        }
    }

    /**
     * Method name: setDistributionsTabDebitLineInfo <dd>The method
     * explanation: Set DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param i int
     * @param validCountD int
     */
    // START 2019/01/29 [QC#30056, MOD]
    // START 2019/02/07 [QC#30136, MOD]
    //    public static void setDistributionsTabDebitLineInfo(EZDCMsg cMsg, int i, int validCountD) {
    //    public static boolean setDistributionsTabDebitLineInfo(EZDCMsg cMsg, int i, int validCountD) {
    public static boolean setDistributionsTabDebitLineInfo(EZDCMsg cMsg, int i, int validCountD, boolean isBlanketPO) {
    // END   2019/02/07 [QC#30136, MOD]
    // END   2019/01/29 [QC#30056, ADD]

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2019/01/29 [QC#30056, ADD]
        BigDecimal lineAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue());
        BigDecimal invQty = NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue());
        // START 2019/08/15 [QC#52280, ADD]
        if (ACCT_INV_STS.CANCEL.equals(bizMsg.acctInvStsCd.getValue())) {
            BigDecimal apRejQty = NFBCommonBusiness.chkNull(bizMsg.A.no(i).apRejQty_A1.getValue());
            invQty = invQty.add(apRejQty);
        }
        // END   2019/08/15 [QC#52280, ADD]
        if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
            return false;
        }
        // END   2019/01/29 [QC#30056, ADD]

        int cmInvAcctDistLineNum = 1;

        // QC#21636
        BigDecimal acInvJrnlCostAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue());
        BigDecimal apBillQty = NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue());
        // START 2019/08/15 [QC#52280, ADD]
        if (ACCT_INV_STS.CANCEL.equals(bizMsg.acctInvStsCd.getValue())) {
            BigDecimal apRejQty = NFBCommonBusiness.chkNull(bizMsg.A.no(i).apRejQty_A1.getValue());
            apBillQty = apBillQty.add(apRejQty);
        }
        // END   2019/08/15 [QC#52280, ADD]
        // START 2018/03/09 [QC#24620, MOD]
        // BigDecimal varAmt = NFBCommonBusiness.chkNull(acInvJrnlCostAmt.multiply(apBillQty));
        BigDecimal varAmt = BigDecimal.ZERO;
        if (AP_INV_CATG.ROSS.equals(bizMsg.apInvCatgCd.getValue())
                || AP_INV_CATG.LEASE.equals(bizMsg.apInvCatgCd.getValue())
                || AP_INV_CATG.AR_REFUND.equals(bizMsg.apInvCatgCd.getValue())
                || AP_INV_CATG.MAINTENANCE.equals(bizMsg.apInvCatgCd.getValue())) {
            varAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue());
        // START 2019/02/07 [QC#30136, ADD]
        } else if (isBlanketPO) {
            varAmt = NFBCommonBusiness.chkNull(acInvJrnlCostAmt.multiply(apBillQty));

        // END   2019/02/07 [QC#30136, ADD]
        } else {
            varAmt = NFBCommonBusiness.chkNull(acInvJrnlCostAmt.multiply(apBillQty));
            // START 2018/06/20 [QC#26169, ADD]
            // START 2018/08/15 [QC#27029-1, MOD]
            // if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2)) {
            //     varAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue().multiply(apBillQty));
            // }
            if (isEffectiveStkInPrice(bizMsg.A.no(i))) {
                varAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue().multiply(apBillQty));

            } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3)) {
                varAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3.getValue().multiply(apBillQty));
            }
            // END   2018/08/15 [QC#27029-1, MOD]
            // END   2018/06/20 [QC#26169, ADD]
        }
        // END   2018/03/09 [QC#24620, MOD]
        
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxDtlLineNum_D1, bizMsg.A.no(i).xxDtlLineNum_A1.getValue()); // Line_Number
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxDtlLineNum_D2, String.format(PO_ORD_DTL_LINE_NUM_FORMAT, cmInvAcctDistLineNum)); // Distribution_Line_Number
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).invDt_D1, bizMsg.invDt.getValue()); // Date
        // QC#21636
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).jrnlFuncDrAmt_D1, varAmt); // Debit
        // START QC#25902,QC#25190,QC#25141
        if (AP_LINE_TP.TAX.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue()) || AP_LINE_TP.FREIGHT.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue()) || AP_LINE_TP.MISC.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).jrnlFuncDrAmt_D1, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())); // Debit
        }
        // END QC#25902,QC#25190,QC#25141
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).jrnlFuncCrAmt_D1, BigDecimal.ZERO); // Credit
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).apAcctDescTxt_D1, bizMsg.A.no(i).apAcctDescTxt_A1.getValue()); // Account
        // Description
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxCmntTxt_D1, bizMsg.A.no(i).xxCmntTxt_A1.getValue()); // Account
        // Code
        //START 2017/03/17 E.Kameishi [QC#14205, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxRecHistCratTs_D1, bizMsg.A.no(i).xxRecHistCratTs_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxRecHistCratByNm_D1, bizMsg.A.no(i).xxRecHistCratByNm_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxRecHistUpdTs_D1, bizMsg.A.no(i).xxRecHistUpdTs_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxRecHistUpdByNm_D1, bizMsg.A.no(i).xxRecHistUpdByNm_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxRecHistTblNm_D1, bizMsg.A.no(i).xxRecHistTblNm_A1);
        //END 2017/03/17 E.Kameishi [QC#14205, ADD]

        // START 2019/01/29 [QC#30056, ADD]
        return true;
        // END   2019/01/29 [QC#30056, ADD]
    }

    /**
     * Method name: setDistributionsTabDebitLineInfo <dd>The method
     * explanation: Set DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param map Map
     * @param i int
     * @param validCountD int
     * @param dtlLineNum String
     */
    public static void setDistributionsTabVarDebitLineInfo(EZDCMsg cMsg, BigDecimal varAmt, int validCountD, String dtlLineNum) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2018/03/22 [QC#20316, MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxDtlLineNum_D1, String.format(LINE_NUM_FORMAT, Integer.valueOf(dtlLineNum).intValue())); // Line
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxDtlLineNum_D1, String.format(AP_VND_INV_LINE_NUM_FORMAT, Integer.valueOf(dtlLineNum).intValue())); // Line
        // END   2018/03/22 [QC#20316, MOD]
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxDtlLineNum_D2, String.format(PO_ORD_DTL_LINE_NUM_FORMAT, validCountD + 1)); // Distribution
        // Line
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).invDt_D1, bizMsg.invDt.getValue()); // Date
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).jrnlFuncDrAmt_D1, varAmt); // Debit
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).jrnlFuncCrAmt_D1, BigDecimal.ZERO); // Credit
        bizMsg.D.no(validCountD).apAcctDescTxt_D1.clear(); // Account
        // Description
        bizMsg.D.no(validCountD).xxCmntTxt_D1.clear(); // Account Code
    }

    /**
     * Method name: setDistributionsTabCreditLineInfo <dd>The method
     * explanation: Set DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param validCountD int
     * @param invDt String
     */
    public static void setDistributionsTabCreditLineInfo(EZDCMsg cMsg, int validCountD, int intMaxLineNumber) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2018/03/22 [QC#20316, MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxDtlLineNum_D1, String.format(PO_ORD_DTL_LINE_NUM_FORMAT, intMaxLineNumber)); // Line
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxDtlLineNum_D1, String.format(AP_VND_INV_LINE_NUM_FORMAT, intMaxLineNumber)); // Line
        // END   2018/03/22 [QC#20316, MOD]
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxDtlLineNum_D2, String.format(PO_ORD_DTL_LINE_NUM_FORMAT, 1)); // Distribution
        // Line
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).invDt_D1, bizMsg.invDt.getValue()); // Date
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).jrnlFuncDrAmt_D1, BigDecimal.ZERO); // Debit
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).jrnlFuncCrAmt_D1, getCreditAmount(bizMsg, validCountD)); // Credit
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).apAcctDescTxt_D1, "Liability"); // Account
        // Description
        // START 2018/02/27 [QC#23505, MOD] Modify QC#27026 2018/07/19.
        // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxCmntTxt_D1, getCreditLineAccountCode(bizMsg.apVndCd.getValue(), bizMsg.apVndInvNum.getValue(), AP_VND_INV_SQ_NUM_00)); // Account Code
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(validCountD).xxCmntTxt_D1, getCreditLineAccountCode(bizMsg.apVndCd.getValue())); // Account Code
        // END   2018/02/27 [QC#23505, MOD]


    }

    private static BigDecimal getCreditAmount(NFBL2040CMsg bizMsg, int validCountD) {

        BigDecimal creditAmount = BigDecimal.ZERO;
        for (int i = 0; i < validCountD; i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).jrnlFuncDrAmt_D1)) {
                creditAmount = creditAmount.add(NFBCommonBusiness.chkNull(bizMsg.D.no(i).jrnlFuncDrAmt_D1.getValue()));
            }
        }

        return creditAmount;
    }

    /**
     * Method name: setHoldsTabDetailInfo <dd>The method explanation:
     * Set DB information to screen. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param map Map
     */
    @SuppressWarnings("unchecked")
    public static void setHoldsTabDetailInfo(EZDCMsg cMsg, Map map, int j, List pmtHldRelRsnPulldownList) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2018/03/27 [QC#24277, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).apVndInvLineNum_H1, (String) map.get(AP_VND_INV_LINE_NUM));
        // END   2018/03/27 [QC#24277, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).pmtHldCd_H1, (String) map.get(PMT_HLD_CD)); // Hold
        // Code
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).pmtHldDt_H1, (String) map.get(PMT_HLD_DT)); // Hold
        // Date
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).pmtHldRsnCd_H1, (String) map.get(PMT_HLD_RSN_CD)); // Hold
        // Reason
        // Code
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).pmtHldPsnCd_H1, (String) map.get(PMT_HLD_PSN_CD)); // Hold
        // By
        // Hold Release Checkbox
        if (ZYPConstant.FLG_ON_Y.equals((String) map.get(PMT_HLD_FLG))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).xxChkBox_H1, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).xxChkBox_H1, ZYPConstant.FLG_ON_Y);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).pmtHldRelPsnCd_H1, (String) map.get(PMT_HLD_REL_PSN_CD)); // Released
        // By
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).pmtHldRelDt_H1, (String) map.get(PMT_HLD_REL_DT)); // Released
        // Date
        // Create [Release Reason] pulldown for each rows.
        createPmtHldRelRsnPulldownList(pmtHldRelRsnPulldownList, j, bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).pmtHldRelRsnCd_H1, (String) map.get(PMT_HLD_REL_RSN_CD)); // Release
        // Reason
        // Code
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).pmtHldRelCmntTxt_H1, (String) map.get(PMT_HLD_REL_CMNT_TXT)); // Release
        // Note

        // START 2020/02/17 [QC#53413, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(j).poNum_H1, (String) map.get(PO_NUM));
        // END   2020/02/17 [QC#53413, ADD]
    }

    /**
     * Method name: getPmtHldRelRsnPulldownList <dd>The method
     * explanation: Get records for [Line Type] pulldown list. <dd>
     * Remarks:
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List getApLineTpPulldownList() {

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getApLineTpPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            return resultList;
        } else {
            return new ArrayList();
        }

    }

    /**
     * Method name: getApLineTpPulldownListForPoMatch <dd>The method
     * explanation: Get records for [Line Type] pulldown list. <dd>
     * Remarks:
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List getApLineTpPulldownListForPoMatch() {

        String notDispApLineTp = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_NFBL2040_NOT_DPLY_AP_LINE_TP, GLBL_CMPY_CD);
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getApLineTpPulldownValueForPoMatch(notDispApLineTp);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            return resultList;
        } else {
            return new ArrayList();
        }

    }

    /**
     * Method name: createApLineTpPulldownList <dd>The method
     * explanation: create records for [Line Type] pulldown list. <dd>
     * Remarks:
     * @param apLineTpPulldownList List
     * @param index int
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void createApLineTpPulldownList(List apLineTpPulldownList, int index, EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        for (int i = 0; i < apLineTpPulldownList.size(); i++) {
            Map map = (Map) apLineTpPulldownList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).apLineTpCd_C.no(i), (String) map.get(AP_LINE_TP_CD));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).apLineTpDescTxt_D.no(i), (String) map.get(AP_LINE_TP_DESC_TXT));
        }

    }

    /**
     * Method name: getPmtHldRelRsnPulldownList <dd>The method
     * explanation: Get records for [Release Reason] pulldown list.
     * <dd>Remarks:
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List getPmtHldRelRsnPulldownList() {

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPmtHldRelRsnPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            return resultList;
        } else {
            return new ArrayList();
        }

    }

    /**
     * Method name: createPmtHldRelRsnPulldownList <dd>The method
     * explanation: create records for [Release Reason] pulldown list.
     * <dd>Remarks:
     * @param pmtHldRelRsnPulldownList List
     * @param index int
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void createPmtHldRelRsnPulldownList(List pmtHldRelRsnPulldownList, int index, EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        for (int i = 0; i < pmtHldRelRsnPulldownList.size(); i++) {
            Map map = (Map) pmtHldRelRsnPulldownList.get(i);
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelRsnCd_C.no(i), (String) map.get(PMT_HLD_REL_RSN_CD));
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelRsnDescTxt_D.no(i), (String) map.get(PMT_HLD_REL_RSN_DESC_TXT));
        }

    }

    /**
     * Method name: clearHiddenValue <dd>The method explanation: Clear
     * hidden values. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearHiddenValue(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        bizMsg.poStsCd_HD.clear();
        bizMsg.docMgtDocId.clear();
        bizMsg.docMgtCatgCd.clear();

    }

    /**
     * Method name: clearSearchCondition <dd>The method explanation:
     * Clear search condition values. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearSearchCondition(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        bizMsg.cmApInvTpCd_S.clear();
        bizMsg.poNum.clear();
        bizMsg.delyOrdNum_H.clear();
        bizMsg.rwsNum_H.clear();
        bizMsg.cmApInvTpCd_HD.clear();
        bizMsg.poNum_HD.clear();
        bizMsg.delyOrdNum_HD.clear();
        bizMsg.rwsNum_HD.clear();
        // START QC#25902,QC#25190,QC#25141
        bizMsg.vndRtrnNum.clear();
        bizMsg.vndRtrnNum_HD.clear();
        // END QC#25902,QC#25190,QC#25141

    }

    /**
     * Method name: clearHeader <dd>The method explanation: Clear
     * values on HEADER tab. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param xxChkBox_PO String
     */
    public static void clearHeader(EZDCMsg cMsg, String xxChkBox_PO) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        bizMsg.dplyVndNm.clear();
        bizMsg.apVndCd.clear();
        bizMsg.apVndInvNum.clear();
        bizMsg.invDt.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PA, ZYPConstant.FLG_OFF_N);
        bizMsg.apPmtChkNum.clear();
        bizMsg.prntVndCd.clear(); // Vendor#
        bizMsg.cmInvMatchCd_S.clear(); // Invoice Matching
        bizMsg.poNum_HT.clear(); // PO#
        bizMsg.invAmt.clear(); // Invoice Amount
        bizMsg.vndPmtTermCd.clear(); // Payment Term Code
        bizMsg.vndPmtTermDescTxt.clear(); // Terms
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_OFF_N); // Holds
        bizMsg.vndPmtMethCd.clear(); // Payment Method Code
        bizMsg.vndPmtMethDescTxt.clear(); // Payment Method Name
        bizMsg.poApvlDt.clear(); // PO Date
        bizMsg.entPoDtlDealNetAmt_TO.clear(); // PO Amount
        bizMsg.delyOrdNum_DH.clear(); // Receipt#
        bizMsg.rwsNum_DH.clear(); // RWS#
        // START QC#25902,QC#25190,QC#25141
        bizMsg.vndRtrnNum_H.clear(); // Vendor Return#
        // END QC#25902,QC#25190,QC#25141
        bizMsg.invHdrDescTxt.clear(); // Description
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PO, xxChkBox_PO); // PO
        // Variance
        bizMsg.acctInvStsCd.clear(); // Accounting Invoice Status Code
        bizMsg.acctInvStsDescTxt.clear(); // Status
        bizMsg.pmtDt.clear(); // Payment Date
        bizMsg.hdrPmtAmt.clear(); // Amount Paid
        bizMsg.termNetDueDt.clear(); // Terms Date
        bizMsg.acOcTotDiscAmt.clear(); // Discount
        bizMsg.apInvCatgDescTxt.clear(); // Source
        bizMsg.acctDt.clear(); // Account Date
        //START 2017/03/17 E.Kameishi [QC#14205,ADD]
        bizMsg.xxRecHistCratTs.clear();
        bizMsg.xxRecHistCratByNm.clear();
        bizMsg.xxRecHistUpdTs.clear();
        bizMsg.xxRecHistUpdByNm.clear();
        bizMsg.xxRecHistTblNm.clear();
        //END 2017/03/17 E.Kameishi [QC#14205,ADD]

        // START 2017/12/26 [QC#23022, ADD]
        bizMsg.apInvCatgCd.clear();
        // END   2017/12/26 [QC#23022, ADD]
        // START 2018/02/28 [QC#23505, ADD]
        bizMsg.xxChkBox_CR.clear();
        // END   2017/12/26 [QC#23505, ADD]
        // START 2019/07/18 [QC#51675, ADD]
        bizMsg.xxWrnSkipFlg.clear();
        // END   2019/07/18 [QC#51675, ADD]
        // START 2020/02/17 [QC#53413, ADD]
        bizMsg.xxChkBox_MP.clear();
        // END   2020/02/17 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        bizMsg.apVndInvSqNum_MX.clear();
        // add end 2022/02/15 QC#57090
        
    }

    /**
     * Method name: clearHolds <dd>The method explanation: Clear
     * values on HOLDS tab. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearHolds(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // HOLDS Tab
        bizMsg.apVndInvNum_HH.clear();
        bizMsg.invAmt_HH.clear();
        bizMsg.H.clear();
        bizMsg.H.setValidCount(0);
    }

    /**
     * Method name: clearLines <dd>The method explanation: Clear
     * values on LINES tab. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearLines(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // LINES Tab
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        ZYPTableUtil.clear(bizMsg.E);
        bizMsg.E.setValidCount(0);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.entPoDtlDealNetAmt_TO, BigDecimal.ZERO);
        // START 2020/02/17 [QC#53413, ADD]
        bizMsg.poNum_L.clear();
        bizMsg.delyOrdNum_L.clear();
        bizMsg.rwsNum_L.clear();
        // END   2020/02/17 [QC#53413, ADD]
        // add start 2022/02/15 QC#57090
        bizMsg.vndRtrnNum_L.clear();
        // add end 2022/02/15 QC#57090
    }

    /**
     * Method name: clearDistributions <dd>The method explanation:
     * Clear values on DISTRIBUTIONS tab. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearDistributions(EZDCMsg cMsg) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // DISTRIBUTIONS Tab
        bizMsg.D.clear();
        bizMsg.D.setValidCount(0);
    }

    /**
     * Method name: Clear assigned status from CM_STK_IN table. <dd>
     * The method explanation: Clear assigned status from CM_STK_IN
     * table. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @SuppressWarnings("unchecked")
    public static void clearCmStkInAsgSts(EZDCMsg cMsg) {
        EZDDebugOutput.println(5, "clearCmStkInAsgSts================================START", null);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        List<List> listKey = new ArrayList<List>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            // START QC#25902,QC#25190,QC#25141
            String poNum = "";
            List<String> pkList = new ArrayList<String>();
            // mod start 2022/02/15 QC#57090
            //if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
            //    pkList.add(bizMsg.poNum_HT.getValue());
            //    poNum = bizMsg.poNum_HT.getValue();
            //// START 2020/02/17 [QC#53413, ADD]
            //} else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
            //    pkList.add(bizMsg.A.no(i).poNum_A1.getValue());
            //    poNum = bizMsg.A.no(i).poNum_A1.getValue();
            //// END   2020/02/17 [QC#53413, ADD]
            //} else {
            //    pkList.add(bizMsg.vndRtrnNum_H.getValue());
            //    poNum = bizMsg.vndRtrnNum_H.getValue();
            //}
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                pkList.add(bizMsg.A.no(i).poNum_A1.getValue());
                poNum = bizMsg.A.no(i).poNum_A1.getValue();
            } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).vndRtrnNum_A1)) {
                pkList.add(bizMsg.A.no(i).vndRtrnNum_A1.getValue());
                poNum = bizMsg.A.no(i).vndRtrnNum_A1.getValue();
            } else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                pkList.add(bizMsg.poNum_HT.getValue());
                poNum = bizMsg.poNum_HT.getValue();
            } else if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
                pkList.add(bizMsg.vndRtrnNum_H.getValue());
                poNum = bizMsg.vndRtrnNum_H.getValue();
            }
            // mod end 2022/02/15 QC#57090
            // END QC#25902,QC#25190,QC#25141
            pkList.add(bizMsg.A.no(i).mdseCd_A1.getValue());
            if (!listKey.contains(pkList)) {
                List<Map> resultList = (List<Map>) getCmStkInPkListByPartialValue(poNum, bizMsg.A.no(i).mdseCd_A1.getValue());
                if (resultList != null) {
                    for (int j = 0; j < resultList.size(); j++) {
                        Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);
                        CM_STK_INTMsg cmStkIn = new CM_STK_INTMsg();
                        ZYPEZDItemValueSetter.setValue(cmStkIn.glblCmpyCd, GLBL_CMPY_CD);
                        ZYPEZDItemValueSetter.setValue(cmStkIn.cmStkInPk, (BigDecimal) resultMap.get(CM_STK_IN_PK));
                        cmStkIn = (CM_STK_INTMsg) EZDTBLAccessor.findByKeyForUpdate(cmStkIn);
                        if (cmStkIn != null) {
                            cmStkIn.invAsgDt.clear();
                            ZYPEZDItemValueSetter.setValue(cmStkIn.invAsgFlg, ZYPConstant.FLG_OFF_N);
                            EZDTBLAccessor.update(cmStkIn);
                        }
                    }
                }
                listKey.add(pkList);
            }
        }
        EZDDebugOutput.println(5, "clearCmStkInAsgSts================================END", null);
    }

    /**
     * Method name: csvDownLoadHoldTab <dd>The method explanation: CSV
     * Download Process <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void csvDownLoadHoldTab(NFBL2040CMsg bizMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(FILE_NAME_HOLDS), CSV_EXT);

        NFBL2040F00FMsg f00Msg = new NFBL2040F00FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), f00Msg);

        csvOutFile.writeHeader(CSV_HEADER_HOLDS);

        List pmtHldPulldownList = getPmtHldPulldownList();
        List pmtHldRsnPulldownList = getPmtHldRsnPulldownList();
        List pmtHldRelRsnPulldownList = getPmtHldRelRsnPulldownList();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {

            /* Copy a record into FMsg, and write it by copy. */
            EZDMsg.copy(bizMsg.H.no(i), null, f00Msg, null);
            if (ZYPCommonFunc.hasValue(bizMsg.H.no(i).pmtHldDt_H1)) {
                ZYPEZDItemValueSetter.setValue(f00Msg.xxDtTxt_H1, ZYPDateUtil.formatEzd8ToDisp(bizMsg.H.no(i).pmtHldDt_H1.getValue()));
            }
            if (ZYPCommonFunc.hasValue(bizMsg.H.no(i).pmtHldRelDt_H1)) {
                ZYPEZDItemValueSetter.setValue(f00Msg.xxDtTxt_H2, ZYPDateUtil.formatEzd8ToDisp(bizMsg.H.no(i).pmtHldRelDt_H1.getValue()));
            }

            // START 2018/01/25 S.Katsuma [QC#19579,ADD]
            // Hold Name
            for (int index = 0; index < pmtHldPulldownList.size(); index++) {
                Map map = (Map) pmtHldPulldownList.get(index);
                String pmtHldCd = (String) map.get(PMT_HLD_CD);
                String pmtHldCdH1 = bizMsg.H.no(i).pmtHldCd_H1.getValue();
                if (pmtHldCdH1.equals(pmtHldCd)) {
                    ZYPEZDItemValueSetter.setValue(f00Msg.pmtHldDescTxt_H1, (String) map.get(PMT_HLD_DESC_TXT));
                    break;
                }
            }

            // Hold Reason
            for (int index = 0; index < pmtHldRsnPulldownList.size(); index++) {
                Map map = (Map) pmtHldRsnPulldownList.get(index);
                String pmtHldRsnCd = (String) map.get(PMT_HLD_RSN_CD);
                String pmtHldRsnCdH1 = bizMsg.H.no(i).pmtHldRsnCd_H1.getValue();
                if (pmtHldRsnCdH1.equals(pmtHldRsnCd)) {
                    ZYPEZDItemValueSetter.setValue(f00Msg.pmtHldRsnDescTxt_H1, (String) map.get(PMT_HLD_RSN_DESC_TXT));
                    break;
                }
            }
            // END 2018/01/25 S.Katsuma [QC#19579,ADD]

            // Release Reason
            for (int index = 0; index < pmtHldRelRsnPulldownList.size(); index++) {
                Map map = (Map) pmtHldRelRsnPulldownList.get(index);
                String pmtHldRelRsnCd = (String) map.get(PMT_HLD_REL_RSN_CD);
                String pmtHldRelRsnCdH1 = bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue();
                if (pmtHldRelRsnCdH1.equals(pmtHldRelRsnCd)) {
                    ZYPEZDItemValueSetter.setValue(f00Msg.pmtHldRelRsnDescTxt_H1, (String) map.get(PMT_HLD_REL_RSN_DESC_TXT));
                    break;
                }
            }
            csvOutFile.write();
        }
        csvOutFile.close();

    }

    /**
     * Method name: csvDownLoadLinesTab <dd>The method explanation:
     * CSV Download Process <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void csvDownLoadLinesTab(NFBL2040CMsg bizMsg) {
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(FILE_NAME_LINES), CSV_EXT);

        NFBL2040F01FMsg f01Msg = new NFBL2040F01FMsg();
        // START 2018/08/23 [QC#27247-1, ADD]
        f01Msg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        // START 2018/09/25 [QC#28441, MOD]
//        f01Msg.addExclusionItem("vndUomCd_A1");
        f01Msg.addExclusionItem("xxChkBox_A1");
        // END 2018/09/25 [QC#28441, MOD]
        // END   2018/08/23 [QC#27247-1, ADD]

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), f01Msg);

        // START 2018/08/23 [QC#27247-1, MOD]
        // csvOutFile.writeHeader(CSV_HEADER_LINES);
        csvOutFile.writeHeader(CSV_HEADER_LINES, f01Msg, ZYPGUITableColumn.getColOrder(bizMsg));
        // END   2018/08/23 [QC#27247-1, MOD]

        List apLineTpPulldownList = getApLineTpPulldownList();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            /* Copy a record into FMsg, and write it by copy. */
            EZDMsg.copy(bizMsg.A.no(i), null, f01Msg, null);
            for (int index = 0; index < apLineTpPulldownList.size(); index++) {
                Map map = (Map) apLineTpPulldownList.get(index);
                String apLineTpCd = (String) map.get(AP_LINE_TP_CD);
                String apLineTpCdA1 = bizMsg.A.no(i).apLineTpCd_A1.getValue();
                if (apLineTpCdA1.equals(apLineTpCd)) {
                    ZYPEZDItemValueSetter.setValue(f01Msg.apLineTpDescTxt_A1, (String) map.get(AP_LINE_TP_DESC_TXT));
                    break;
                }
            }
            // START 2018/03/22 [QC#20316, ADD]
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).invCrctDt_A1.getValue())) {
                // START 2018/04/04 [QC#20316, MOD]
                // ZYPEZDItemValueSetter.setValue(f01Msg.invCrctDt_A1, ZYPDateUtil.formatEzd8ToDisp(bizMsg.A.no(i).invCrctDt_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(f01Msg.xxDtTxt_A1, ZYPDateUtil.formatEzd8ToDisp(bizMsg.A.no(i).invCrctDt_A1.getValue()));
                // END   2018/04/04 [QC#20316, MOD]
            }
            // END   2018/03/22 [QC#20316, ADD]
            // START 2020/02/20 [QC#53413, ADD]
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poApvlDt_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(f01Msg.xxDtTxt_A2, ZYPDateUtil.formatEzd8ToDisp(bizMsg.A.no(i).poApvlDt_A1.getValue()));
            }
            // END   2020/02/20 [QC#53413, ADD]
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    /**
     * Method name: csvDownLoadDistributionTab <dd>The method
     * explanation: CSV Download Process <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void csvDownLoadDistributionTab(NFBL2040CMsg bizMsg) {
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(FILE_NAME_DISTRIBUTIONS), CSV_EXT);

        NFBL2040F02FMsg f02Msg = new NFBL2040F02FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), f02Msg);

        csvOutFile.writeHeader(CSV_HEADER_DISTRIBUTIONS);

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {

            /* Copy a record into FMsg, and write it by copy. */
            EZDMsg.copy(bizMsg.D.no(i), null, f02Msg, null);
            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).invDt_D1)) {
                ZYPEZDItemValueSetter.setValue(f02Msg.xxDtTxt_D1, ZYPDateUtil.formatEzd8ToDisp(bizMsg.D.no(i).invDt_D1.getValue()));
            }
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    /**
     * Method name: getMdseNm <dd>The method explanation: <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void getMdseNm(NFBL2040CMsg bizMsg) {
        MDSETMsg mdse = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdse.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(mdse.mdseCd, bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.getValue());
        mdse = (MDSETMsg) EZDTBLAccessor.findByKey(mdse);
        if (mdse == null) {
            bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.setErrorInfo(1, NFBM0069E);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).mdseDescShortTxt_A1, mdse.mdseNm.getValue());
        }
    }
    /**
     * getAslInfo(QC#21727)
     * @param bizMsg
     */
    public static void getAslInfo(NFBL2040CMsg bizMsg) {
		NPZC113001PMsg npzc113001PMsg = new NPZC113001PMsg();
		ZYPEZDItemValueSetter.setValue(npzc113001PMsg.glblCmpyCd, GLBL_CMPY_CD);
		ZYPEZDItemValueSetter.setValue(npzc113001PMsg.slsDt, ZYPDateUtil.getSalesDate(GLBL_CMPY_CD));
		if(ZYPCommonFunc.hasValue(bizMsg.apVndCd) 
				&& ZYPCommonFunc.hasValue(bizMsg.prntVndCd) 
				&& ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).mdseCd_A1)) {
			
			String vndCd = bizMsg.apVndCd.getValue();
			String prntVndCd = bizMsg.prntVndCd.getValue();
			// START 2018/10/03 [QC#28099-1. ADD]
            // del start 2022/02/15 QC#57090
			//if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
			//    S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPoVendorInfo(bizMsg.poNum_HT.getValue());
		    //    if (ssmResult.isCodeNormal()) {
		    //        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
		    //        if (resultList.size() > 0) {
		    //            Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
	        //            vndCd = (String) rtn.get("VND_CD");
	        //            prntVndCd = (String) rtn.get("PRNT_VND_CD");
		    //        }
		    //    }
			//}
			//// START 2020/02/20 [QC#53413, ADD]
			//else {
	        // del end 2022/02/15 QC#57090
			    if (ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).poNum_A1)) {
		             S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPoVendorInfo(bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).poNum_A1.getValue());
		                if (ssmResult.isCodeNormal()) {
		                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
		                    if (resultList.size() > 0) {
		                        Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
		                        vndCd = (String) rtn.get("VND_CD");
		                        prntVndCd = (String) rtn.get("PRNT_VND_CD");
		                    }
		                }
			    }
	        // del start 2022/02/15 QC#57090
			//}
            // del end 2022/02/15 QC#57090
            // mod end 2022/02/15 QC#57090
			// END   2020/02/20 [QC#53413, ADD]
            // mod start 2022/02/15 QC#57090
            //if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
            //    S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVendorReturnVendorInfo(bizMsg.vndRtrnNum_H.getValue());
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).vndRtrnNum_A1)) {
                S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVendorReturnVendorInfo(bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).vndRtrnNum_A1.getValue());
            // mod end 2022/02/15 QC#57090
                if (ssmResult.isCodeNormal()) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    if (resultList.size() > 0) {
                        Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                        vndCd = (String) rtn.get("SHIP_TO_VND_CD");
                        prntVndCd = (String) rtn.get("PRNT_VND_CD");
                    }
                }
            }
            // END   2018/10/03 [QC#28099-1. ADD]
			String mdse = bizMsg.A.no(bizMsg.xxCellIdx.getValue().intValue()).mdseCd_A1.getValue();
			ZYPEZDItemValueSetter.setValue(npzc113001PMsg.vndCd, vndCd);
			ZYPEZDItemValueSetter.setValue(npzc113001PMsg.prntVndCd, prntVndCd);
			ZYPEZDItemValueSetter.setValue(npzc113001PMsg.mdseCd, mdse);
			NPZC113001 npzc113001 = new NPZC113001();
			npzc113001.execute(npzc113001PMsg, ONBATCH_TYPE.ONLINE);

			if (S21ApiUtil.isXxMsgId(npzc113001PMsg)) {
				List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(npzc113001PMsg);
				for (String xxMsgId : xxMsgIdList) {
					// Do Nothing
				}
			} else {
				if (ZYPCommonFunc.hasValue(npzc113001PMsg.vndUomCd)) {
					ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxCellIdx
							.getValue().intValue()).vndUomCd_A1,
							npzc113001PMsg.vndUomCd.getValue());
				}
				if (ZYPCommonFunc.hasValue(npzc113001PMsg.unitPrcAmt)) {
					ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxCellIdx
							.getValue().intValue()).dealGrsUnitPrcAmt_A1,
							npzc113001PMsg.unitPrcAmt.getValue());
				}
			}
		} else {
			return;
		}
	}

// START 2018/11/20 [QC#28660, MOD]
    public static void commonRefreshHoldsTab(NFBL2040CMsg bizMsg, String userId) {
        commonRefreshHoldsTab(bizMsg, userId, false);
    }

    public static boolean updateAutoReleasedRecordToH(NFBL2040CMsg bizMsg, List pmtHldRelRsnPulldownList, String userId, List<List> checkList, String apVndInvLineNum, String pmtHldCd, String pmtHldRsnCd, String xxChkBox_H1) {

        CM_INV_PMT_HLDTMsg cmInvPmtHld = new CM_INV_PMT_HLDTMsg();
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvSqNum, bizMsg.apVndInvSqNum);
        if (EMPTY_AP_VND_INV_LINE_NUM.equals(apVndInvLineNum)) {
            ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
        } else {
            ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum, apVndInvLineNum);
        }
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldCd, pmtHldCd);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldRsnCd, pmtHldRsnCd);
        cmInvPmtHld = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKey(cmInvPmtHld);

        if (cmInvPmtHld == null) {
            return false;
        }

        int index = -1;
        for (int i = 0 ; i < bizMsg.H.getValidCount() ; i++) {
            String apVndInvLineNum_H = bizMsg.H.no(i).apVndInvLineNum_H1.getValue();
            if (EMPTY_AP_VND_INV_LINE_NUM.equals(apVndInvLineNum_H)) {
                apVndInvLineNum_H = AP_VND_INV_LINE_NUM_0000;
            }
            String pmtHldCd_H = bizMsg.H.no(i).pmtHldCd_H1.getValue();
            String pmtHldRsnCd_H = bizMsg.H.no(i).pmtHldRsnCd_H1.getValue();

            if (cmInvPmtHld.apVndInvLineNum.getValue().equals(apVndInvLineNum_H)
                    && cmInvPmtHld.pmtHldCd.getValue().equals(pmtHldCd_H)
                    && cmInvPmtHld.pmtHldRsnCd.getValue().equals(pmtHldRsnCd_H)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).apVndInvLineNum_H1, apVndInvLineNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldCd_H1, pmtHldCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldDt_H1, cmInvPmtHld.pmtHldDt.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRsnCd_H1, pmtHldRsnCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldPsnCd_H1, cmInvPmtHld.pmtHldPsnCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).xxChkBox_H1, xxChkBox_H1);

        if (ZYPConstant.FLG_ON_Y.equals(xxChkBox_H1)) {
            // auto release
            String slsDt = ZYPDateUtil.getSalesDate();
            String autoPmtHldRelRsnCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_AUTO_PMT_HLD_REL_RSN_CD, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelPsnCd_H1, userId);
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelDt_H1, slsDt);
            createPmtHldRelRsnPulldownList(pmtHldRelRsnPulldownList, index, bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelRsnCd_H1, autoPmtHldRelRsnCd);
            bizMsg.H.no(index).pmtHldRelCmntTxt_H1.clear();
        }

        return true;
    }

    /**
     * Method name: commonRefreshHoldsTab <dd>The method explanation:
     * <dd>Remarks: QC#21653 add Recieve Qty Check.
     * @param bizMsg Business Component Interface Message
     * @param userId String
     */
//    public static void commonRefreshHoldsTab(NFBL2040CMsg bizMsg, String userId) {
    public static void commonRefreshHoldsTab(NFBL2040CMsg bizMsg, String userId, boolean autoReleaseFlag) {
// END   2018/11/20 [QC#28660, MOD]

        BigDecimal totLineAmt = BigDecimal.ZERO;
        List pmtHldRelRsnPulldownList = getPmtHldRelRsnPulldownList();

        int validCountH = 0;
        int hldRecCnt = 0;
        BigDecimal poQty = BigDecimal.ZERO;
        BigDecimal billedQty = BigDecimal.ZERO;
        BigDecimal totalBilledQty = BigDecimal.ZERO;
        // QC#21653 Modify
        BigDecimal rwsQty = BigDecimal.ZERO;

        List<List> checkList = new ArrayList<List>();
        List<NFBL2040_HCMsg> targetRecords = new ArrayList<NFBL2040_HCMsg>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            List<String> elementList = new ArrayList<String>();
            elementList.add(bizMsg.H.no(i).pmtHldCd_H1.getValue());
            elementList.add(bizMsg.H.no(i).pmtHldRsnCd_H1.getValue());
            // START 2019/02/19 [QC#30418, ADD]
            elementList.add(bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
            // END   2019/02/19 [QC#30418, ADD]
            if (!checkList.contains(elementList)) {
                checkList.add(elementList);
                targetRecords.add(bizMsg.H.no(i));
            }
        }

        int j = 0;
        for (j = 0; j < bizMsg.H.getValidCount(); j++) {
            if (j < targetRecords.size()) {
                EZDMsg.copy(targetRecords.get(j), null, bizMsg.H.no(j), null);
            } else {
                bizMsg.H.no(j).clear();
            }
        }
        bizMsg.H.setValidCount(targetRecords.size());
        validCountH = bizMsg.H.getValidCount();
        //  QC#18602-Sol#102
        // START 2020/02/17 [QC#53413, MOD]
        //String dsPoTpCd = getDsPoTpCd(bizMsg.poNum_HT.getValue());
        String dsPoTpCd = getDsPoTpCd(bizMsg);
        // END   2020/02/17 [QC#53413, MOD]
        HashMap<String , BigDecimal> mdseAmtList = new HashMap<String , BigDecimal>();

        // START 2018/03/23 [QC#20316, MOD]
        Map<String, Map<String, Object>> qtyMatchingMap = new HashMap<String, Map<String, Object>>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2019/02/19 [QC#30419, DEL]
//            poQty = BigDecimal.ZERO;
//            billedQty = BigDecimal.ZERO;
//            totalBilledQty = BigDecimal.ZERO;
//            // QC#21653 Modify
//            rwsQty = BigDecimal.ZERO;
            // END   2019/02/19 [QC#30419, DEL]

            // 2018/02/28 START [QC#23505, ADD]
            if (!XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                continue;
            }
            // 2018/02/28 END   [QC#23505, ADD]

            NFBL2040_ACMsg aCMsg = (NFBL2040_ACMsg) bizMsg.A.get(i);
            // QC#14858-Sol#060
            String mdseCd = aCMsg.mdseCd_A1.getValue();
            if(ZYPConstant.FLG_ON_Y.equals(aCMsg.xxCntnrFlg_A1.getValue())){
                mdseCd = aCMsg.xxMdseCd_A1.getValue();
            }

            // START 2018/03/23 [QC#20316, MOD]
            if (!ZYPCommonFunc.hasValue(mdseCd)) {
                continue;
            }

            String poOrdDtlLineNum = bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue();
            if (!ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
                poOrdDtlLineNum = PO_ORD_DTL_LINE_NUM_000;
            }
            String qtyMatchingMapKey = poOrdDtlLineNum + "|" + mdseCd;
            Map<String, Object> invQtyElmMap = null;
            if (!qtyMatchingMap.containsKey(qtyMatchingMapKey)) {
                invQtyElmMap = new HashMap<String, Object>();
                invQtyElmMap.put("PO_ORD_DTL_LINE_NUM", poOrdDtlLineNum);
                invQtyElmMap.put("MDSE_CD", mdseCd);
                invQtyElmMap.put("PO_MATCH_TP_CD", aCMsg.poMatchTpCd_A1.getValue());
                invQtyElmMap.put("AP_BILL_QTY", BigDecimal.ZERO);
                invQtyElmMap.put("AP_VND_INV_LINE_NUM_LIST", new ArrayList<String>());
                // START 2020/02/17 [QC#53413, ADD]
                invQtyElmMap.put("PO_NUM", bizMsg.A.no(i).poNum_A1.getValue());
                // END   2020/02/17 [QC#53413, ADD]
                qtyMatchingMap.put(qtyMatchingMapKey, invQtyElmMap);
            } else {
                invQtyElmMap = qtyMatchingMap.get(qtyMatchingMapKey);
            }
            BigDecimal apBillQty = (BigDecimal) invQtyElmMap.get("AP_BILL_QTY");
            invQtyElmMap.put("AP_BILL_QTY", apBillQty.add(NFBCommonBusiness.chkNull(aCMsg.apBillQty_A1.getValue())));
            ((List<String>)invQtyElmMap.get("AP_VND_INV_LINE_NUM_LIST")).add(aCMsg.xxDtlLineNum_A1.getValue());

            if (ZYPCommonFunc.hasValue(mdseCd) //
                    // START 2018/02/26 [QC#24144, ADD]
                    && ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxInvAmt_A1)
                    // END   2018/02/26 [QC#24144, ADD]
                    && BigDecimal.ZERO.compareTo(bizMsg.A.no(i).xxInvAmt_A1.getValue()) < 0) {
                // MdseCd is unique key within line.
                mdseAmtList.put(mdseCd, bizMsg.A.no(i).xxInvAmt_A1.getValue());
            }

            // QC#18602-Sol#102 Modify END.
            totLineAmt = totLineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
        }
        // END   2018/03/23 [QC#20316, MOD]

        // START 2018/03/23 [QC#20316, MOD]
        for (String qtyMatchingKey : qtyMatchingMap.keySet()) {
            // START 2019/02/19 [QC#30419, ADD]
            poQty = BigDecimal.ZERO;
            billedQty = BigDecimal.ZERO;
            totalBilledQty = BigDecimal.ZERO;
            rwsQty = BigDecimal.ZERO;
            // END   2019/02/19 [QC#30419, ADD]

            Map<String, Object> invQtyElmMap = qtyMatchingMap.get(qtyMatchingKey);
            String mdseCd = (String) invQtyElmMap.get("MDSE_CD");
            String poOrdDtlLineNum = (String) invQtyElmMap.get("PO_ORD_DTL_LINE_NUM");
            if (PO_ORD_DTL_LINE_NUM_000.equals(poOrdDtlLineNum)) {
                poOrdDtlLineNum = null;
            }
            List<String> apVndInvLineNumList = (List<String>) invQtyElmMap.get("AP_VND_INV_LINE_NUM_LIST");
        // END   2018/03/23 [QC#20316, MOD]

            // START 2018/03/28 [QC#24277, MOD]
            // S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getSumPoQty(bizMsg.poNum_HT.getValue(), mdseCd);
            // START 2020/02/17 [QC#53413, MOD]
            S21SsmEZDResult ssmResult = null;
            if (ZYPCommonFunc.hasValue((String)invQtyElmMap.get("PO_NUM"))){
                ssmResult = NFBL2040Query.getInstance().getSumPoQty((String) invQtyElmMap.get("PO_NUM"), poOrdDtlLineNum, mdseCd);
            } else {
                ssmResult = NFBL2040Query.getInstance().getSumPoQty(bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd);
            }
            // END   2020/02/17 [QC#53413, MOD]
            // END   2018/03/28 [QC#24277, MOD]
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                // QC#27493 mod start.
                //poQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_PO_DISP_QTY"));
                poQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_PO_QTY"));
                // QC#27493 mod end.
            }
            // START 2018/01/09 [QC#22143, MOD]
            // ssmResult = NFBL2040Query.getInstance().getSumBilledQty(bizMsg.poNum_HT.getValue(), mdseCd);
            String apVndInvNum = bizMsg.apVndInvNum.getValue();
            if (isInvoicePKModified(bizMsg)) {
                apVndInvNum = bizMsg.apVndInvNum_OT.getValue();
            }
            // START 2018/03/28 [QC#24277, MOD]
            // ssmResult = NFBL2040Query.getInstance().getSumBilledQty(bizMsg.poNum_HT.getValue(), mdseCd, apVndInvNum);
            // START 2018/11/20 [QC#28660, MOD]
            // ssmResult = NFBL2040Query.getInstance().getSumBilledQty(bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd, apVndInvNum);
            // START 2020/02/17 [QC#53413, MOD]
            if (ZYPCommonFunc.hasValue((String) invQtyElmMap.get("PO_NUM"))) {
                ssmResult = NFBL2040Query.getInstance().getSumBilledQty(bizMsg, (String) invQtyElmMap.get("PO_NUM"), poOrdDtlLineNum, mdseCd, apVndInvNum);
            } else {
                ssmResult = NFBL2040Query.getInstance().getSumBilledQty(bizMsg, bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd, apVndInvNum);
            }
            // END   2020/02/17 [QC#53413, MOD]
            // END   2018/11/20 [QC#28660, MOD]
            // END   2018/03/28 [QC#24277, MOD]
            // END   2018/01/09 [QC#22143, MOD]
            resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                billedQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
            }
            // START 2018/03/23 [QC#20316, MOD]
            // totalBilledQty = billedQty.add(NFBCommonBusiness.chkNull(aCMsg.apBillQty_A1.getValue()));
            totalBilledQty = billedQty.add((BigDecimal)invQtyElmMap.get("AP_BILL_QTY"));
            // END   2018/03/23 [QC#20316, MOD]

            // QC#21653 Modify Start.
            // START 2018/03/28 [QC#24277, MOD]
            // ssmResult = NFBL2040Query.getInstance().getSumRcvQty(bizMsg.poNum_HT.getValue(), mdseCd);
            if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
                // START 2020/02/17 [QC#53413, MOD]
                if (ZYPCommonFunc.hasValue((String) invQtyElmMap.get("PO_NUM"))) {
                    ssmResult = NFBL2040Query.getInstance().getSumRcvQtyByPoOrdDtlLineNum((String) invQtyElmMap.get("PO_NUM"), poOrdDtlLineNum);
                } else {
                    ssmResult = NFBL2040Query.getInstance().getSumRcvQtyByPoOrdDtlLineNum(bizMsg.poNum_HT.getValue(), poOrdDtlLineNum);
                }
                // END   2020/02/17 [QC#53413, MOD]
            } else {
                // START 2020/02/17 [QC#53413, MOD]
                if (ZYPCommonFunc.hasValue((String) invQtyElmMap.get("PO_NUM"))) {
                    ssmResult = NFBL2040Query.getInstance().getSumRcvQty((String) invQtyElmMap.get("PO_NUM"), mdseCd);
                } else {
                    ssmResult = NFBL2040Query.getInstance().getSumRcvQty(bizMsg.poNum_HT.getValue(), mdseCd);
                }
                // END   2020/02/17 [QC#53413, MOD]
            }
            // END   2018/03/28 [QC#24277, MOD]
            resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                rwsQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_RWS_QTY"));
            }

            // QC#18602-Sol#102 Modify start.
            if (!DS_PO_TP.BLANKET_PO.equals(dsPoTpCd)) {
                // QC#21653 Recipt: 3 Way Matching. No Goods Recipt: 2
                // Way Matching
                // START 2018/03/23 [QC#20316, MOD]
                // if (PO_MATCH_TP.RECEIPT.equals(aCMsg.poMatchTpCd_A1.getValue())) {
                if (PO_MATCH_TP.RECEIPT.equals((String)invQtyElmMap.get("PO_MATCH_TP_CD"))) {
                // END   2018/03/23 [QC#20316, MOD]
                    // Compare Ordered Quantity to Received
                    // Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)
                    if (poQty.compareTo(rwsQty) < 0) {
                        String xxChkBox_H1 = ZYPConstant.FLG_OFF_N; // Check_box
                        // START 2018/03/28 [QC#24277, MOD]
                        // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)) {
                        //     if (isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)) {
                        //         hldRecCnt++;
                        //         xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                        //     } else {
                        //         xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                        //     }
                        // } else {
                        //     hldRecCnt++;
                        //     xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                        // }
                        // if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011, xxChkBox_H1)) {
                        //     validCountH++;
                        // }
                        for (String apVndInvLineNum : apVndInvLineNumList) {
                            if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)) {
                                if (isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)) {
                                    hldRecCnt++;
                                    xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                                } else {
                                    xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                                }
                            } else {
                                hldRecCnt++;
                                xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                            }
                            if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011, xxChkBox_H1)) {
                                validCountH++;
                            }
                        }
                        // END   2018/03/28 [QC#24277, MOD]
                    } else {
                        String xxChkBox_H1 = ZYPConstant.FLG_ON_Y; // Check_box
                        // START 2018/03/28 [QC#24277, MOD]
                        // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011) && !isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)) {
                        //     if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011, xxChkBox_H1)) {
                        //         validCountH++;
                        //     }
                        // }
                        for (String apVndInvLineNum : apVndInvLineNumList) {
                            if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011) && !isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)) {
                                if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011, xxChkBox_H1)) {
                                    validCountH++;
                                }
                            // START 2018/11/20 [QC#28660, ADD]
                            } else if (autoReleaseFlag && existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011) && isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011)) {
                                updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_011, xxChkBox_H1);
                            }
                            // END   2018/11/20 [QC#28660, ADD]
                        }
                        // END   2018/03/28 [QC#24277, MOD]
                    }

                    // Compare Ordered Quantity to Invoice
                    // Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)
                    if (poQty.compareTo(totalBilledQty) < 0) {
                        String xxChkBox_H1 = ZYPConstant.FLG_OFF_N; // Check_box
                        // START 2018/03/28 [QC#24277, MOD]
                        // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                        //     if (isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                        //         hldRecCnt++;
                        //         xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                        //     } else {
                        //         xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                        //     }
                        // } else {
                        //     hldRecCnt++;
                        //     xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                        // }
                        // if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1)) {
                        //     validCountH++;
                        // }
                        for (String apVndInvLineNum : apVndInvLineNumList) {
                            if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                                if (isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                                    hldRecCnt++;
                                    xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                                } else {
                                    xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                                }
                            } else {
                                hldRecCnt++;
                                xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                            }
                            if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1)) {
                                validCountH++;
                            }
                        }
                        // END   2018/03/28 [QC#24277, MOD]
                    } else {
                        String xxChkBox_H1 = ZYPConstant.FLG_ON_Y; // Check_box
                        // START 2018/03/28 [QC#24277, MOD]
                        // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012) && !isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                        //     if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1)) {
                        //         validCountH++;
                        //     }
                        // }
                        for (String apVndInvLineNum : apVndInvLineNumList) {
                            if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012) && !isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                                if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1)) {
                                    validCountH++;
                                }
                            // START 2018/11/20 [QC#28660, ADD]
                            } else if (autoReleaseFlag && existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012) && isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                                updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1);
                            }
                            // END   2018/11/20 [QC#28660, ADD]
                        }
                       // END   2018/03/28 [QC#24277, MOD]
                    }

                    // Compare Received Quantity to Invoice
                    // Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)
                    if (rwsQty.compareTo(totalBilledQty) < 0) {
                        String xxChkBox_H1 = ZYPConstant.FLG_OFF_N; // Check_box
                        // START 2018/03/28 [QC#24277, MOD]
                        // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                        //     if (isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                        //         hldRecCnt++;
                        //         xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                        //     } else {
                        //         xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                        //     }
                        // } else {
                        //     hldRecCnt++;
                        //     xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                        // }
                        // if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013, xxChkBox_H1)) {
                        //     validCountH++;
                        // }
                        for (String apVndInvLineNum : apVndInvLineNumList) {
                            if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                                if (isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                                    hldRecCnt++;
                                    xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                                } else {
                                    xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                                }
                            } else {
                                hldRecCnt++;
                                xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                            }
                            if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013, xxChkBox_H1)) {
                                validCountH++;
                            }
                        }
                        // END   2018/03/28 [QC#24277, MOD]
                    } else {
                        String xxChkBox_H1 = ZYPConstant.FLG_ON_Y; // Check_box
                        // START 2018/03/28 [QC#24277, MOD]
                        // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013) && !isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                        //     if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013, xxChkBox_H1)) {
                        //         validCountH++;
                        //     }
                        // }
                        for (String apVndInvLineNum : apVndInvLineNumList) {
                            if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013) && !isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                                if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013, xxChkBox_H1)) {
                                    validCountH++;
                                }
                            // START 2018/11/20 [QC#28660, ADD]
                            } else if (autoReleaseFlag && existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013) && isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                                updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_013, xxChkBox_H1);
                            }
                            // END   2018/11/20 [QC#28660, ADD]
                        }
                        // END   2018/03/28 [QC#24277, MOD]
                    }
                } else {
                    // No Goods Receipt.
                    // Compare Ordered Quantity to Invoice
                    // Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)
                    if (poQty.compareTo(totalBilledQty) < 0) {
                        String xxChkBox_H1 = ZYPConstant.FLG_OFF_N; // Check_box
                        // START 2018/03/28 [QC#24277, MOD]
                        // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                        //     if (isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                        //         hldRecCnt++;
                        //         xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                        //     } else {
                        //         xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                        //     }
                        // } else {
                        //     hldRecCnt++;
                        //     xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                        // }
                        // if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1)) {
                        //     validCountH++;
                        // }
                        for (String apVndInvLineNum : apVndInvLineNumList) {
                            if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                                if (isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                                    hldRecCnt++;
                                    xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                                } else {
                                    xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                                }
                            } else {
                                hldRecCnt++;
                                xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                            }
                            if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1)) {
                                validCountH++;
                            }
                        }
                        // END   2018/03/28 [QC#24277, MOD]
                    } else {
                        String xxChkBox_H1 = ZYPConstant.FLG_ON_Y; // Check_box
                        // START 2018/03/28 [QC#24277, MOD]
                        // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012) && !isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                        //     if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1)) {
                        //         validCountH++;
                        //     }
                        // }
                        for (String apVndInvLineNum : apVndInvLineNumList) {
                            if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012) && !isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                                if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1)) {
                                    validCountH++;
                                }
                            // START 2018/11/20 [QC#28660, ADD]
                            } else if (autoReleaseFlag && existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012) && isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012)) {
                                updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, apVndInvLineNum, PMT_HLD_CD_010, PMT_HLD_RSN_CD_012, xxChkBox_H1);
                            }
                            // END   2018/11/20 [QC#28660, ADD]
                        }
                        // END   2018/03/28 [QC#24277, MOD]
                    }
                }
            }
            // QC#21653 Modify End.

            // START 2018/03/23 [QC#20316, DEL]
            // if (ZYPCommonFunc.hasValue(mdseCd) //
            //        // START 2018/02/26 [QC#24144, ADD]
            //         && ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxInvAmt_A1)
            //         // END   2018/02/26 [QC#24144, ADD]
            //         && BigDecimal.ZERO.compareTo(bizMsg.A.no(i).xxInvAmt_A1.getValue()) < 0) {
            //     // MdseCd is unique key within line.
            //     mdseAmtList.put(mdseCd, bizMsg.A.no(i).xxInvAmt_A1.getValue());
            // }
            // 
            // // QC#18602-Sol#102 Modify END.
            // totLineAmt = totLineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
            // END   2018/03/23 [QC#20316, DEL]
        }

        if (ZYPCommonFunc.hasValue(bizMsg.docMgtDocId) && ZYPCommonFunc.hasValue(bizMsg.docMgtCatgCd)) {
            // START 2018/03/28 [QC#24277, MOD]
            // if (addRecordToH(bizMsg, getPmtHldRelRsnPulldownList(), userId, validCountH, checkList, PMT_HLD_CD_030, PMT_HLD_RSN_CD_031, ZYPConstant.FLG_OFF_N)) {
            //     validCountH++;
            // }
            if (addRecordToH(bizMsg, getPmtHldRelRsnPulldownList(), userId, validCountH, checkList, EMPTY_AP_VND_INV_LINE_NUM, PMT_HLD_CD_030, PMT_HLD_RSN_CD_031, ZYPConstant.FLG_OFF_N)) {
                validCountH++;
            }
            // END   2018/03/28 [QC#24277, MOD]
        }

        // QC#18602-Sol#102 Modify Start.
        if (!DS_PO_TP.BLANKET_PO.equals(dsPoTpCd)) {
            // START 2018/03/28 [QC#24277, MOD]
            // if (toleranceHold(bizMsg)) {
            //     if (addRecordToH(bizMsg, getPmtHldRelRsnPulldownList(), userId, validCountH, checkList, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, ZYPConstant.FLG_OFF_N)) {
            //         validCountH++;
            //     }
            // }
            BigDecimal intTolRat = getIntTolRate(bizMsg);
            int aftDeclPntDigitNum = getAftDeclPntDigitNum();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                // QC#21321
                if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) // 
                        && AP_LINE_TP.FREIGHT.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue()) //
                        || AP_LINE_TP.TAX.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {

                    continue;
                }
                // START 2020/06/08 [QC#57007, MOD]
                //if (isToleranceHold(bizMsg.A.no(i), intTolRat, aftDeclPntDigitNum)) {
                if (isToleranceHoldForCreditMemoPO(bizMsg.A.no(i), intTolRat, aftDeclPntDigitNum)) {
                // END   2020/06/08 [QC#57007, MOD]
                    String apVndInvLineNum = bizMsg.A.no(i).xxDtlLineNum_A1.getValue();
                    if (addRecordToH(bizMsg, getPmtHldRelRsnPulldownList(), userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, ZYPConstant.FLG_OFF_N)) {
                        validCountH++;
                    }
                // START 2018/11/20 [QC#28660, ADD]
                } else if (autoReleaseFlag && existsCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021) && isHoldCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021)) {
                    String xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                    updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, xxChkBox_H1);
                }
                // END   2018/11/20 [QC#28660, ADD]
            }
            // END   2018/03/28 [QC#24277, MOD]
        } else {
            // Blanket PO
            // START 2018/03/28 [QC#24277, MOD]
            // for (String mdseCd : mdseAmtList.keySet()) {
            // 
            //     BigDecimal lineAmt = mdseAmtList.get(mdseCd);
            //     BigDecimal pastInvoicePoAmt = getInvoicePoTotalAmt(bizMsg.poNum_HT.getValue(), bizMsg.apVndInvNum.getValue(), mdseCd);
            //     BigDecimal totalInvoceAmt = lineAmt.add(pastInvoicePoAmt);
            //     BigDecimal poAmt = getTotalPoAmtOfMdse(bizMsg.poNum_HT.getValue(), mdseCd);
            // 
            //     if (poAmt.compareTo(totalInvoceAmt) < 0) {
            //         // PO Amount < Invoice Amount
            //         if (addRecordToH(bizMsg, getPmtHldRelRsnPulldownList(), userId, validCountH, checkList, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, ZYPConstant.FLG_OFF_N)) {
            //             validCountH++;
            //         }
            //     }
            // }
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                String apVndInvLineNum = bizMsg.A.no(i).xxDtlLineNum_A1.getValue();
                String mdseCd = bizMsg.A.no(i).mdseCd_A1.getValue();
                if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
                    mdseCd = bizMsg.A.no(i).xxMdseCd_A1.getValue();
                }
                BigDecimal lineAmt = bizMsg.A.no(i).xxInvAmt_A1.getValue();
                // START 2020/02/17 [QC#53413, MOD]
                BigDecimal pastInvoicePoAmt = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    pastInvoicePoAmt = getInvoicePoTotalAmt(bizMsg.A.no(i).poNum_A1.getValue(), bizMsg.apVndInvNum.getValue(), mdseCd);
                } else {
                    pastInvoicePoAmt = getInvoicePoTotalAmt(bizMsg.poNum_HT.getValue(), bizMsg.apVndInvNum.getValue(), mdseCd);
                }
                // END   2020/02/17 [QC#53413, MOD]
                BigDecimal totalInvoceAmt = lineAmt.add(pastInvoicePoAmt);
                // START 2020/02/17 [QC#53413, MOD]
                BigDecimal poAmt = BigDecimal.ZERO;
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    poAmt = getTotalPoAmtOfMdse(bizMsg.A.no(i).poNum_A1.getValue(), mdseCd);
                } else {
                    poAmt = getTotalPoAmtOfMdse(bizMsg.poNum_HT.getValue(), mdseCd);
                }
                // END   2020/02/17 [QC#53413, MOD]
                if (poAmt.compareTo(totalInvoceAmt) < 0) {
                    // PO Amount < Invoice Amount
                    if (addRecordToH(bizMsg, getPmtHldRelRsnPulldownList(), userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, ZYPConstant.FLG_OFF_N)) {
                        validCountH++;
                    }
                // START 2018/11/20 [QC#28660, ADD]
                } else if (autoReleaseFlag && existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021) && isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021)) {
                    String xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                    updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, xxChkBox_H1);
                }
                // END   2018/11/20 [QC#28660, ADD]
            }
            // END   2018/03/28 [QC#24277, MOD]
        }
        // QC#18602-Sol#102 Modify END.

        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, totLineAmt);
        // START 2017/12/25 J.Kim [QC#23059,ADD]
        if (SCREEN_ID.equals(bizMsg.getScreenAplID())) {
            return;
        }
        // END 2017/12/25 J.Kim [QC#23059,ADD]

        if (hldRecCnt == 0 && NFBCommonBusiness.chkNull(bizMsg.entPoDtlDealNetAmt_TO.getValue()).compareTo(NFBCommonBusiness.chkNull(bizMsg.invAmt.getValue())) != 0) {
            // no process.
        } else {
            String xxChkBox_H1 = ZYPConstant.FLG_ON_Y; // Check box
            // START 2018/03/28 [QC#24277, MOD]
            // if (existsCmInvPmtHld(bizMsg, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021) && !isHoldCmInvPmtHld(bizMsg, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021)) {
            //     if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, xxChkBox_H1)) {
            //         validCountH++;
            //     } else {
            //         // Success
            //     }
            // }
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                String apVndInvLineNum = bizMsg.A.no(i).xxDtlLineNum_A1.getValue();
                if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021) && !isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021)) {
                    if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, xxChkBox_H1)) {
                        validCountH++;
                    } else {
                        // Success
                    }
                }
            }
            // END   2018/03/28 [QC#24277, MOD]
        }
        bizMsg.H.setValidCount(validCountH);
        if (hldRecCnt > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum_HH, bizMsg.apVndInvNum.getValue()); // Invoice
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.invAmt_HH, bizMsg.invAmt.getValue()); // Invoice
            // Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.apVndInvNum_HH.clear(); // Invoice Number
            bizMsg.invAmt_HH.clear(); // Invoice Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_OFF_N);
        }
    }

    /**
     * getDsPoTpCd
     * QC#18602-Sol#102 Add method.
     * @param poOrdNum String
     * @return DS_PO_TP_CD String
     */
    public static String getDsPoTpCd(String poOrdNum) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getDsPoTpCd(poOrdNum);

        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        } else {
            return null;
        }
    }


    // START 2020/02/17 [QC#53413, ADD]
    public static String getDsPoTpCd(NFBL2040CMsg bizMsg) {
        String rtnCd = null;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getDsPoTpCd(bizMsg.A.no(i).poNum_A1.getValue());

                if (ssmResult.isCodeNormal()) {
                    if (DS_PO_TP.BLANKET_PO.equals((String) ssmResult.getResultObject())) {
                        return (String) ssmResult.getResultObject();
                    } else {
                        rtnCd = (String) ssmResult.getResultObject();
                        continue;
                    }
                } else {
                    return null;
                }
            }
        }
        return rtnCd;
    }
    // END  2020/02/17 [QC#28660, ADD]
    
    /**
     * getInvoicePoTotalAmt
     * QC#18602-Sol#102 Add method.
     * @param poOrdNum String
     * @param apVndInvNum String
     * @return InvoicePoTotalAmt BigDecimal
     */
    private static BigDecimal getInvoicePoTotalAmt(String poOrdNum, String apVndInvNum, String mdseCd){
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getInvoicePoTotalAmt(poOrdNum, apVndInvNum, mdseCd);

        if (ssmResult.isCodeNormal()) {
            return (BigDecimal) ssmResult.getResultObject();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * getTotalPoAmtOfMdse
     * QC#18602-Sol#102 Add method.
     * @param poOrdNum String
     * @return poTotalAmtOfMdse BigDecimal
     */
    private static BigDecimal getTotalPoAmtOfMdse(String poOrdNum, String mdseCd) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getTotalPoAmtOfMdse(poOrdNum, mdseCd);

        if (ssmResult.isCodeNormal()) {
            return (BigDecimal) ssmResult.getResultObject();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Method name: commonRefreshDistributionsTab <dd>The method
     * explanation: <dd>Remarks:
     * @param bizMsg Business Component Interface Message
     * @param userId String
     */
    public static void commonRefreshDistributionsTab(NFBL2040CMsg bizMsg, String userId) {

        bizMsg.D.clear();
        bizMsg.D.setValidCount(0);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllocTpCd_D, SELECTED_LINES);
                break;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllocTpCd_D, All_LINES);
            }
        }

        // QC#21642 Modify Start
        boolean error = false;
        for(int i = 0; i < bizMsg.A.getValidCount(); i++){
            String xxCmntTxt = bizMsg.A.no(i).xxCmntTxt_A1.getValue();
            String[] strSplit = xxCmntTxt.split("\\.");
            String acctCd = new String();
            if (strSplit.length > 3) {
                acctCd = strSplit[3];
            }

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxCmntTxt_A1) //
                    && !ZYPCommonFunc.hasValue(bizMsg.A.no(i).apAcctDescTxt_A1)) {
                boolean lineError = false;

                if (ZYPCommonFunc.hasValue(acctCd)) {
                    COA_ACCTTMsg tMsg = new COA_ACCTTMsg();
                    tMsg.glblCmpyCd.setValue(GLBL_CMPY_CD);
                    tMsg.coaAcctCd.setValue(acctCd);
                    tMsg = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(tMsg);
                    if (tMsg == null) {
                        lineError = true;
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apAcctDescTxt_A1, tMsg.coaAcctDescTxt.getValue());
                    }
                } else {
                    lineError = true;
                }

                if(!checkGlCodeCombination(bizMsg, i, strSplit)){
                    lineError = true;
                }

                if (lineError) {
                    if (All_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) //
                            || (SELECTED_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) //
                            && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue()))) {
                        // If an error is set, the framework will not
                        // overwrite the error message.
                        bizMsg.A.no(i).xxCmntTxt_A1.setErrorInfo(1, NFBM0235E, new String[] {"Charge Account", "COA Account Master" });
                        error = true;
                    }
                }
            }
        }

        if(error){
            return;
        }
        // QC#21642 Modify End

        bizMsg.invAmt.getValue();
        BigDecimal lineTotAmt = BigDecimal.ZERO;
        BigDecimal lineTotPoAmt = BigDecimal.ZERO;
        int validCountD = 0;
        int validCountE = 0;
        bizMsg.E.clear();
        bizMsg.E.setValidCount(0);
        int intMaxLineNumber = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // DISTRIBUTIONS Tab
            // Debit Line
            boolean insertFlgE = false;
            if (All_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) || (SELECTED_LINES.equals(bizMsg.xxAllocTpCd_D.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue()))) {
                // Insert variance record
                // QC#27029-1 Update Condition.
                // START 2018/08/15 [QC#27029-1, MOD]
                // boolean isVarianceTarget = CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) //
                //         || (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.poNum));
                // START 2018/10/25 [QC#28758, MOD]
                // boolean isVarianceTarget = CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) //
                // || (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()));
                boolean isVarianceTarget = CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) //
                         || (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()))
                         || (AP_INV_CATG.MERCHANDISE.equals(bizMsg.apInvCatgCd.getValue()));
                // END   2018/10/25 [QC#28758, MOD]
                // END   2018/08/15 [QC#27029-1, MOD]
                // START 2019/02/07 [QC#30136, ADD]
                // START 2020/02/17 [QC#53413, MOD]
                boolean isBlanketPO = false;
                if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
                    isBlanketPO = ZYPCommonFunc.hasValue(bizMsg.poNum) && isBlanketPO(bizMsg.poNum.getValue());
                } else {
                    isBlanketPO = ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1.getValue()) && isBlanketPO(bizMsg.A.no(i).poNum_A1.getValue());
                }
                // END   2020/02/17 [QC#53413, MOD]
                if (isBlanketPO) {
                    isVarianceTarget = false;
                }
                // END   2019/02/07 [QC#30136, ADD]

                if (isVarianceTarget && AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    validCountE = insertRowVarianceData(bizMsg, i, validCountD, validCountE);
                    if (bizMsg.E.getValidCount() != validCountE) {
                        insertFlgE = true;
                        bizMsg.E.setValidCount(validCountE);
                    }
                }
                // For Credit line and variance record
                if (validCountD < (bizMsg.A.length() - 2)) {
                    lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    lineTotPoAmt = lineTotPoAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue()));
                    // START 2019/01/29 [QC#30056, MOD]
//                    setDistributionsTabDebitLineInfo(bizMsg, i, validCountD);
//                    validCountD++;
                    // START 2019/02/07 [QC#30136, MOD]
                    // if (setDistributionsTabDebitLineInfo(bizMsg, i, validCountD)) {
                    // 	validCountD++;
                    // } else {
                    // 	insertFlgE = false;
                    // }
                    if (setDistributionsTabDebitLineInfo(bizMsg, i, validCountD, isBlanketPO)) {
                        validCountD++;
                    } else {
                    	insertFlgE = false;
                    }
                    // END   2019/02/07 [QC#30136, MOD]
                    // END   2019/01/29 [QC#30056, MOD]
                    int intCurrentLineNumber = Integer.parseInt(bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
                    if (intMaxLineNumber < intCurrentLineNumber) {
                        intMaxLineNumber = intCurrentLineNumber;
                    }
                }
                // Insert variance record to Distributions line
                if (isVarianceTarget && AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    if (insertFlgE) {
                        setDistributionsTabVarDebitLineInfoForVariance(bizMsg, validCountD, validCountE, Integer.toString(intMaxLineNumber + 1));
                        validCountD++;
                    }
                }
            }
        }

        // START 2019/01/29 [QC#30056, MOD]
//        if (bizMsg.A.getValidCount() > 0) {
        if (bizMsg.A.getValidCount() > 0 && validCountD > 0) {
        // END 2019/01/29 [QC#30056, MOD]
            // Variance Line
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_PO.getValue()) && NFBCommonBusiness.chkNull(bizMsg.invAmt.getValue()).compareTo(lineTotAmt) != 0) {
                BigDecimal varAmt = bizMsg.invAmt.getValue().subtract(lineTotAmt);
                setDistributionsTabVarDebitLineInfo(bizMsg, varAmt, validCountD, Integer.toString(intMaxLineNumber + 1));
                validCountD++;
            }
            // Credit Line
            setDistributionsTabCreditLineInfo(bizMsg, validCountD, intMaxLineNumber + 1);
            validCountD++;
            bizMsg.D.setValidCount(validCountD);
        }
    }

    /**
     * Method name: addRecordToH <dd>The method explanation: <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     * @param pmtHldRelRsnPulldownList List
     * @param userId String
     * @param index int
     * @param checkList List<List>
     * @param apVndInvLineNum String
     * @param pmtHldCd String
     * @param pmtHldRsnCd String
     * @param xxChkBox_H1 String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean addRecordToH(NFBL2040CMsg bizMsg, List pmtHldRelRsnPulldownList, String userId, int index, List<List> checkList, String apVndInvLineNum, String pmtHldCd, String pmtHldRsnCd, String xxChkBox_H1) {

        List elementList = new ArrayList();
        // START 2018/03/28 [QC#24277, ADD]
        elementList.add(apVndInvLineNum); // Inv Line#
        // END   2018/03/28 [QC#24277, ADD]
        elementList.add(pmtHldCd); // Hold Code
        elementList.add(pmtHldRsnCd); // Hold Reason Code
        if (checkList.contains(elementList)) {
            return false;
        } else {
            if (!addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, index, checkList, apVndInvLineNum, pmtHldCd, pmtHldRsnCd, xxChkBox_H1)) {
                // START 2018/03/28 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).apVndInvLineNum_H1, apVndInvLineNum);
                // END   2018/03/28 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldCd_H1, pmtHldCd); // Hold
                // START 2018/10/29 [QC#28988, MOD]
                // ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldDt_H1, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD)); // Hold
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldDt_H1, ZYPDateUtil.getSalesDate()); // Hold
                // END   2018/10/29 [QC#28988, MOD]
                // Date
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRsnCd_H1, pmtHldRsnCd); // Hold
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldPsnCd_H1, userId); // Hold
                // By
                ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).xxChkBox_H1, xxChkBox_H1); // Hold
                // Release
                // Checkbox
                bizMsg.H.no(index).pmtHldRelPsnCd_H1.clear(); // Released
                // By
                bizMsg.H.no(index).pmtHldRelDt_H1.clear(); // Released
                // Date
                createPmtHldRelRsnPulldownList(pmtHldRelRsnPulldownList, index, bizMsg);
                bizMsg.H.no(index).pmtHldRelRsnCd_H1.clear(); // Release
                // Reason
                bizMsg.H.no(index).pmtHldRelCmntTxt_H1.clear(); // Release
                // Note
            }
            checkList.add(elementList);
            return true;
        }
    }

    /**
     * Method name: addCmInvPmtHldRecordToH <dd>The method
     * explanation: <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param pmtHldRelRsnPulldownList List
     * @param userId String
     * @param index int
     * @param checkList List<List>
     * @param apVndInvLineNum String
     * @param pmtHldCd String
     * @param pmtHldRsnCd String
     * @param xxChkBox_H1 String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean addCmInvPmtHldRecordToH(NFBL2040CMsg bizMsg, List pmtHldRelRsnPulldownList, String userId, int index, List<List> checkList, String apVndInvLineNum, String pmtHldCd, String pmtHldRsnCd, String xxChkBox_H1) {
        CM_INV_PMT_HLDTMsg cmInvPmtHld = new CM_INV_PMT_HLDTMsg();
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvNum, bizMsg.apVndInvNum.getValue());
        // START 2018/02/27 [QC#23505, MOD]
        // ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvSqNum, bizMsg.apVndInvSqNum);
        // END   2018/02/27 [QC#23505, MOD]
        // START 2018/03/28 [QC#24277, ADD]
        if (EMPTY_AP_VND_INV_LINE_NUM.equals(apVndInvLineNum)) {
            ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
        } else {
            ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum, apVndInvLineNum);
        }
        // END   2018/03/28 [QC#24277, ADD]
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldCd, pmtHldCd);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldRsnCd, pmtHldRsnCd);
        cmInvPmtHld = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKey(cmInvPmtHld);
        if (cmInvPmtHld != null) {
            // START 2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).apVndInvLineNum_H1, apVndInvLineNum);
            // END   2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldCd_H1, pmtHldCd); // Hold
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldDt_H1, cmInvPmtHld.pmtHldDt.getValue()); // Hold
            // Date
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRsnCd_H1, pmtHldRsnCd); // Hold
            // Reason
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldPsnCd_H1, cmInvPmtHld.pmtHldPsnCd.getValue()); // Hold
            // By
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).xxChkBox_H1, xxChkBox_H1); // Hold
            // Release
            // Checkbox
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelPsnCd_H1, cmInvPmtHld.pmtHldRelPsnCd.getValue()); // Released
            // By
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelDt_H1, cmInvPmtHld.pmtHldRelDt.getValue()); // Released
            // Date
            createPmtHldRelRsnPulldownList(pmtHldRelRsnPulldownList, index, bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelRsnCd_H1, cmInvPmtHld.pmtHldRelRsnCd.getValue()); // Release
            // Reason
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(index).pmtHldRelCmntTxt_H1, cmInvPmtHld.pmtHldRelCmntTxt.getValue()); // Release
            // Note
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: existsCmInvPmtHld <dd>The method explanation: <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     * @param apVndInvLineNum String
     * @param pmtHldCd String
     * @param pmtHldRsnCd String
     * @return boolean
     */
    public static boolean existsCmInvPmtHld(NFBL2040CMsg bizMsg, String apVndInvLineNum, String pmtHldCd, String pmtHldRsnCd) {
        CM_INV_PMT_HLDTMsg cmInvPmtHld = new CM_INV_PMT_HLDTMsg();
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvNum, bizMsg.apVndInvNum.getValue());
        // START 2018/02/27 [QC#23505, MOD]
        // ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvSqNum, bizMsg.apVndInvSqNum);
        // END   2018/02/27 [QC#23505, MOD]
        // START 2018/03/28 [QC#24277, ADD]
        if (EMPTY_AP_VND_INV_LINE_NUM.equals(apVndInvLineNum)) {
            ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
        } else {
            ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum, apVndInvLineNum);
        }
        // END   2018/03/28 [QC#24277, ADD]
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldCd, pmtHldCd);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldRsnCd, pmtHldRsnCd);
        cmInvPmtHld = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKey(cmInvPmtHld);
        if (cmInvPmtHld != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: isHoldCmInvPmtHld <dd>The method explanation: <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     * @param apVndInvLineNum String
     * @param pmtHldCd String
     * @param pmtHldRsnCd String
     * @return boolean
     */
    public static boolean isHoldCmInvPmtHld(NFBL2040CMsg bizMsg, String apVndInvLineNum, String pmtHldCd, String pmtHldRsnCd) {
        CM_INV_PMT_HLDTMsg cmInvPmtHld = new CM_INV_PMT_HLDTMsg();
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvNum, bizMsg.apVndInvNum.getValue());
        // START 2018/02/27 [QC#23505, MOD]
        // ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvSqNum, bizMsg.apVndInvSqNum);
        // END   2018/02/27 [QC#23505, MOD]
        // START 2018/03/28 [QC#24277, ADD]
        if (EMPTY_AP_VND_INV_LINE_NUM.equals(apVndInvLineNum)) {
            ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
        } else {
            ZYPEZDItemValueSetter.setValue(cmInvPmtHld.apVndInvLineNum,apVndInvLineNum);
        }
        // END   2018/03/28 [QC#24277, ADD]
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldCd, pmtHldCd);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHld.pmtHldRsnCd, pmtHldRsnCd);
        cmInvPmtHld = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKey(cmInvPmtHld);
        if (cmInvPmtHld != null) {
            if (ZYPCommonFunc.hasValue(cmInvPmtHld.pmtHldFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(cmInvPmtHld.pmtHldFlg.getValue())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method name: getAcctInvStsDescTxt <dd>The method explanation:
     * Get ACCT_INV_STS_DESC_TXT from ACCT_INV_STS table. <dd>Remarks:
     * @param acctInvStsCd String
     * @return String
     */
    public static String getAcctInvStsDescTxt(String acctInvStsCd) {
        ACCT_INV_STSTMsg acctInvSts = new ACCT_INV_STSTMsg();
        ZYPEZDItemValueSetter.setValue(acctInvSts.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(acctInvSts.acctInvStsCd, acctInvStsCd);
        acctInvSts = (ACCT_INV_STSTMsg) EZDTBLAccessor.findByKey(acctInvSts);
        if (acctInvSts != null) {
            return acctInvSts.acctInvStsDescTxt.getValue();
        } else {
            return EMPTY_STRING;
        }
    }

    /**
     * Method name: getCmStkInPkListByPartialCondition <dd>The method
     * explanation: get CM_STK_IN_PK list by partial condition <dd>
     * Remarks:
     * @param poOrdNum String
     * @param mdseCd String
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List getCmStkInPkListByPartialValue(String poOrdNum, String mdseCd) {

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getCmStkInPkListByPartialCondition(poOrdNum, mdseCd);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            return resultList;
        }
        return null;
    }

    /**
     * updatePoInformation
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param poInvQty BigDecimal
     * @param mdseCd String
     * @return xxMsgId String
     */
    public static String updatePoInformation(String poOrdNum, String poOrdDtlLineNum, BigDecimal poInvQty, String mdseCd) {
        NPZC004001PMsg pMsg = new NPZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, poOrdDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.poInvQty, poInvQty);
        NPZC004001 apiPOUpdate = new NPZC004001();
        apiPOUpdate.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // Error
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId) && !pMsg.xxMsgIdList.no(i).xxMsgId.getValue().equals(NPZM0021E)) {
                    return pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                }
            }
        }

        return null;
    }

    /**
     * Get CM_AP_INV_DTL list by partial keys.
     * @param poOrdNum String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getListCmApInvDtlByPartialKey(String poOrdNum) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getListCmApInvDtlByPartialKey(poOrdNum);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            return resultList;
        }
        return new ArrayList<Map<String, Object>>();
    }

    /**
     * @param apInvCatgCd String
     * @return apInvCatgDescTxt String
     */
    public static String getApInvCatgDescTxt(String apInvCatgCd) {
        AP_INV_CATGTMsg apInvCatg = new AP_INV_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(apInvCatg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(apInvCatg.apInvCatgCd, apInvCatgCd);
        apInvCatg = (AP_INV_CATGTMsg) S21FastTBLAccessor.findByKey(apInvCatg);
        if (apInvCatg == null) {
            return EMPTY_STRING;
        } else {
            return apInvCatg.apInvCatgDescTxt.getValue();
        }
    }

    /**
     * Get Account Code for Credit Line
     * @param apVndCd String
     * @param apVndInvNum String
     * @param apVndInvSqNum String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getCreditLineAccountCode(String apVndCd, String apVndInvNum, String apVndInvSqNum) {
 
        String rtn9Seg = EMPTY_STRING;
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getCreditLineAccountCode(apVndCd, apVndInvNum, apVndInvSqNum);
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map map = (Map) resultList.get(0);
                rtn9Seg = (String) map.get(XX_CMNT_TXT);
            }
        }
        if (EMPTY_STRING.equals(rtn9Seg)) {
            ssmResult = NFBL2040Query.getInstance().getVndSplyCoaData(apVndCd);
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (resultList.size() > 0) {
                    Map<String, Object> coaMap = resultList.get(0);
                    StringBuffer coa = new StringBuffer();
                    coa.append(coaMap.get(SPLY_COA_CMPY_CD)).append(DOT);
                    coa.append(coaMap.get(SPLY_COA_BR_CD)).append(DOT);
                    coa.append(coaMap.get(SPLY_COA_CC_CD)).append(DOT);
                    coa.append(coaMap.get(SPLY_COA_ACCT_CD)).append(DOT);
                    coa.append(coaMap.get(SPLY_COA_PROD_CD)).append(DOT);
                    coa.append(coaMap.get(SPLY_COA_CH_CD)).append(DOT);
                    coa.append(coaMap.get(SPLY_COA_AFFL_CD)).append(DOT);
                    coa.append(coaMap.get(SPLY_COA_PROJ_CD)).append(DOT);
                    coa.append(coaMap.get(SPLY_COA_EXTN_CD));
                    rtn9Seg = coa.toString();
                }
            }
        }

        return rtn9Seg;
    }

    /**
     * Get Account Code for Credit Line
     * QC#27026 Add.
     * @param apVndCd String
     * @param apVndInvNum String
     * @param apVndInvSqNum String
     * @return String
     */
    @SuppressWarnings("unchecked")
    public static String getCreditLineAccountCode(String apVndCd) {

        String rtn9Seg = EMPTY_STRING;

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVndSplyCoaData(apVndCd);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> coaMap = resultList.get(0);
                StringBuffer coa = new StringBuffer();
                coa.append(coaMap.get(SPLY_COA_CMPY_CD)).append(DOT);
                coa.append(coaMap.get(SPLY_COA_BR_CD)).append(DOT);
                coa.append(coaMap.get(SPLY_COA_CC_CD)).append(DOT);
                coa.append(coaMap.get(SPLY_COA_ACCT_CD)).append(DOT);
                coa.append(coaMap.get(SPLY_COA_PROD_CD)).append(DOT);
                coa.append(coaMap.get(SPLY_COA_CH_CD)).append(DOT);
                coa.append(coaMap.get(SPLY_COA_AFFL_CD)).append(DOT);
                coa.append(coaMap.get(SPLY_COA_PROJ_CD)).append(DOT);
                coa.append(coaMap.get(SPLY_COA_EXTN_CD));
                rtn9Seg = coa.toString();
            }
        }

        return rtn9Seg;
    }

    /**
     * Check 9 Segment
     * @param bizMsg NFBL2040CMsg
     * @param idx Integer
     * @param coa String[]
     * @return boolean
     */
    public static boolean check9Seg(NFBL2040CMsg bizMsg, int idx, String[] coa) {
        CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
        // START 2018/03/22 [QC#20316, MOD]
        // int coaCmpyCdDigit = cmInvAcctDistTMsg.getAttr("coaCmpyCd").getDigit();
        // int coaBrCdDigit = cmInvAcctDistTMsg.getAttr("coaBrCd").getDigit();
        // int coaCcCdDigit = cmInvAcctDistTMsg.getAttr("coaCcCd").getDigit();
        // int coaAcctCdDigit = cmInvAcctDistTMsg.getAttr("coaAcctCd").getDigit();
        // int coaProdCdDigit = cmInvAcctDistTMsg.getAttr("coaProdCd").getDigit();
        // int coaChCdDigit = cmInvAcctDistTMsg.getAttr("coaChCd").getDigit();
        // int coaAfflCdDigit = cmInvAcctDistTMsg.getAttr("coaAfflCd").getDigit();
        // int coaProjCdDigit = cmInvAcctDistTMsg.getAttr("coaProjCd").getDigit();
        // int coaExtnCdDigit = cmInvAcctDistTMsg.getAttr("coaExtnCd").getDigit();
        int coaCmpyCdDigit = cmInvAcctDistTMsg.getAttr("drCoaCmpyCd").getDigit();
        int coaBrCdDigit = cmInvAcctDistTMsg.getAttr("drCoaBrCd").getDigit();
        int coaCcCdDigit = cmInvAcctDistTMsg.getAttr("drCoaCcCd").getDigit();
        int coaAcctCdDigit = cmInvAcctDistTMsg.getAttr("drCoaAcctCd").getDigit();
        int coaProdCdDigit = cmInvAcctDistTMsg.getAttr("drCoaProdCd").getDigit();
        int coaChCdDigit = cmInvAcctDistTMsg.getAttr("drCoaChCd").getDigit();
        int coaAfflCdDigit = cmInvAcctDistTMsg.getAttr("drCoaAfflCd").getDigit();
        int coaProjCdDigit = cmInvAcctDistTMsg.getAttr("drCoaProjCd").getDigit();
        int coaExtnCdDigit = cmInvAcctDistTMsg.getAttr("drCoaExtnCd").getDigit();
        // END   2018/03/22 [QC#20316, MOD]
        int coaIdx = 0;

        if (coa.length != 9) {
            String errMsg = "9 segments";
            errMsg = errMsg.concat("(");
            errMsg = errMsg.concat(String.valueOf(coa.length));
            errMsg = errMsg.concat(")");
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {errMsg });
            return false;
        }
        if (coa[coaIdx++].length() > coaCmpyCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Company" });
            return false;
        }
        if (coa[coaIdx++].length() > coaBrCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Branch" });
            return false;
        }
        if (coa[coaIdx++].length() > coaCcCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Cost Center" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAcctCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Account" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProdCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Product" });
            return false;
        }
        if (coa[coaIdx++].length() > coaChCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Channel" });
            return false;
        }
        if (coa[coaIdx++].length() > coaAfflCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Intercompany" });
            return false;
        }
        if (coa[coaIdx++].length() > coaProjCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Merchandise" });
            return false;
        }
        if (coa[coaIdx++].length() > coaExtnCdDigit) {
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, NFBM0041E, new String[] {"Business Unit" });
            return false;
        }
        return true;
    }

    /**
     * glCodeCombinationCheck
     * @param bizMsg NFBL2040CMsg
     * @param idx Integer
     * @param coa String[]
     * @return boolean
     */
    public static boolean checkGlCodeCombination(NFBL2040CMsg bizMsg, int idx, String[] coa) {
        if (!NFBL2040CommonLogic.check9Seg(bizMsg, idx, coa)) {
            return false;
        }

        NFZC102001 api = new NFZC102001();
        NFZC102001PMsg apiMsg = new NFZC102001PMsg();

        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxMstChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxArcsApiChkFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxGlCmbnChkFlg, ZYPConstant.FLG_ON_Y);

        int coaIdx = 0;
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCmpyCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaBrCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaCcCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAcctCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProdCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaChCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaAfflCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaProjCd, coa[coaIdx++]);
        ZYPEZDItemValueSetter.setValue(apiMsg.coaExtnCd, coa[coaIdx++]);
        // QC#19433 Start
        String apLineTpCd = bizMsg.A.no(idx).apLineTpCd_A1.getValue();
        ZYPEZDItemValueSetter.setValue(apiMsg.resrcObjNm, BIZ_ID + apLineTpCd);
        // QC#19433 End

        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (apiMsg.xxMsgIdList.getValidCount() > 0) {
            String msgId;
            String msgTxt;
            msgId = apiMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            msgTxt = apiMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue();
            bizMsg.A.no(idx).xxCmntTxt_A1.clearErrorInfo();
            bizMsg.A.no(idx).xxCmntTxt_A1.setErrorInfo(1, msgId, new String[] {msgTxt });
            return false;
        } else {
            bizMsg.A.no(idx).xxCmntTxt_A1.clearErrorInfo();
        }
        return true;
    }

    /**
     * MOD QC#25902,QC#25190,QC#25141
     * checkPoSts
     * @param bizMsg NFBL2040CMsg
     * @return boolean (true = error)
     */
    public static boolean checkPoSts(NFBL2040CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPoSts(bizMsg, false);
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            String poLineStsCd = "";
            String poStsCd = "";
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(i);
                poLineStsCd = (String) rtn.get("PO_LINE_STS_CD");
                poStsCd = (String) rtn.get("PO_STS_CD");

                if (PO_LINE_STS.OPEN.equals(poLineStsCd)) {
                    if (PO_STS.VALIDATED.equals(poStsCd) || PO_STS.PO_CONFIRMED.equals(poStsCd)) {
                        return true;
                    }
                } else if (PO_LINE_STS.OPEN_FOR_INVOICE.equals(poLineStsCd)) {
                    return true;
                } else if (PO_LINE_STS.OPEN_FOR_RECEIPT.equals(poLineStsCd)) {
                    return true;
                } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && PO_STS.CLOSED.equals(poStsCd)) {
                    return true;
                }
                
            }
        } else {
            bizMsg.setMessageInfo(NFBM0069E);
            return false;
        }

        bizMsg.poNum.setErrorInfo(1, NFBM0260E);
        return false;
    }

    // START 2020/02/17 [QC#53413, ADD]
    /**
     * checkPoSts
     * @param bizMsg NFBL2040CMsg
     * @return boolean (true = error)
     */
    public static boolean checkPoSts(NFBL2040CMsg bizMsg, String poNum) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPoSts(poNum);
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            String poLineStsCd = "";
            String poStsCd = "";
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(i);
                poLineStsCd = (String) rtn.get("PO_LINE_STS_CD");
                poStsCd = (String) rtn.get("PO_STS_CD");

                if (PO_LINE_STS.OPEN.equals(poLineStsCd)) {
                    if (PO_STS.VALIDATED.equals(poStsCd) || PO_STS.PO_CONFIRMED.equals(poStsCd)) {
                        return true;
                    }
                } else if (PO_LINE_STS.OPEN_FOR_INVOICE.equals(poLineStsCd)) {
                    return true;
                } else if (PO_LINE_STS.OPEN_FOR_RECEIPT.equals(poLineStsCd)) {
                    return true;
                } else if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && PO_STS.CLOSED.equals(poStsCd)) {
                    return true;
                }
                
            }
        } else {
            bizMsg.setMessageInfo(NFBM0069E);
            return false;
        }

        bizMsg.poNum_L.setErrorInfo(1, NFBM0260E);
        return false;
    }
    // END   2020/02/17 [QC#53413, ADD]

// START 2018/03/28 [QC#24277, MOD]
//    private static boolean toleranceHold(NFBL2040CMsg bizMsg) {
//        BigDecimal intTolRat = getIntTolRate(bizMsg);
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            // QC#21321
//            if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) // 
//                    && AP_LINE_TP.FREIGHT.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue()) //
//                    || AP_LINE_TP.TAX.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
//
//                continue;
//
//            }
//            // Tolerance from amount = Unit Price * Billed Qty * (100%
//            int aftDeclPntDigitNum = getAftDeclPntDigitNum();
//            BigDecimal fromAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())).multiply(
//                    (new BigDecimal(100).subtract(intTolRat).divide(new BigDecimal(100)))).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
//            // Tolerance thru amount = Unit Price * Billed Qty * (100%
//            // + DS_VND.INV_TOL_RATE / 100)
//            BigDecimal thruAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())).multiply(
//                    (new BigDecimal(100).add(intTolRat).divide(new BigDecimal(100)))).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);// END
//
//            if (NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()).compareTo(fromAmt) < 0) {
//                return true;
//            }
//            if (NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()).compareTo(thruAmt) > 0) {
//                return true;
//            }
//        }
//        return false;
//    }
    private static boolean isToleranceHold(NFBL2040_ACMsg aBizMsg, BigDecimal intTolRat, int aftDeclPntDigitNum) {
        BigDecimal fromAmt = NFBCommonBusiness.chkNull(aBizMsg.dealGrsUnitPrcAmt_A1.getValue()).multiply(NFBCommonBusiness.chkNull(aBizMsg.apBillQty_A1.getValue())).multiply(
                (new BigDecimal(100).subtract(intTolRat).divide(new BigDecimal(100)))).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        // Tolerance thru amount = Unit Price * Billed Qty * (100%
        // + DS_VND.INV_TOL_RATE / 100)
        BigDecimal thruAmt = NFBCommonBusiness.chkNull(aBizMsg.dealGrsUnitPrcAmt_A1.getValue()).multiply(NFBCommonBusiness.chkNull(aBizMsg.apBillQty_A1.getValue())).multiply(
                (new BigDecimal(100).add(intTolRat).divide(new BigDecimal(100)))).setScale(aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);// END

        // START 2018/05/07 [QC#25828, MOD]
        // if (NFBCommonBusiness.chkNull(aBizMsg.xxInvAmt_A1.getValue()).compareTo(fromAmt) < 0) {
        //     return true;
        // }
        // if (NFBCommonBusiness.chkNull(aBizMsg.xxInvAmt_A1.getValue()).compareTo(thruAmt) > 0) {
        //     return true;
        // }
        if (!ZYPCommonFunc.hasValue(aBizMsg.entDealNetUnitPrcAmt_A3)) {
            return false;
        }
        if (NFBCommonBusiness.chkNull(aBizMsg.entDealNetUnitPrcAmt_A3.getValue()).multiply(NFBCommonBusiness.chkNull(aBizMsg.apBillQty_A1.getValue())).compareTo(fromAmt) < 0) {
            return true;
        }
        if (NFBCommonBusiness.chkNull(aBizMsg.entDealNetUnitPrcAmt_A3.getValue()).multiply(NFBCommonBusiness.chkNull(aBizMsg.apBillQty_A1.getValue())).compareTo(thruAmt) > 0) {
            return true;
        }
        // END   2018/05/07 [QC#25828, MOD]

        return false;
    }
    
    /**
     * isToleranceHoldForCreditMemoPO
     * QC#27029-1 Add method
     * @param aBizMsg NFBL2040_ACMsg
     * @param intTolRat BigDecimal
     * @param aftDeclPntDigitNum int
     * @return true:Hold
     */
    private static boolean isToleranceHoldForCreditMemoPO(NFBL2040_ACMsg aBizMsg, BigDecimal intTolRat, int aftDeclPntDigitNum) {

        // Credit memo is negative qty.
        // START 2019/11/02 [QC#54439, MOD]
        BigDecimal billQtyAbs = NFBCommonBusiness.chkNull(aBizMsg.apBillQty_A1.getValue()).abs();
        // END   2019/11/02 [QC#54439, MOD]

        BigDecimal fromAmt = NFBCommonBusiness.chkNull(aBizMsg.dealGrsUnitPrcAmt_A1.getValue()).multiply(NFBCommonBusiness.chkNull(billQtyAbs)).multiply((new BigDecimal(100).subtract(intTolRat).divide(new BigDecimal(100)))).setScale(
                aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);
        // Tolerance thru amount = Unit Price * Billed Qty * (100%
        // + DS_VND.INV_TOL_RATE / 100)
        BigDecimal thruAmt = NFBCommonBusiness.chkNull(aBizMsg.dealGrsUnitPrcAmt_A1.getValue()).multiply(NFBCommonBusiness.chkNull(billQtyAbs)).multiply((new BigDecimal(100).add(intTolRat).divide(new BigDecimal(100)))).setScale(
                aftDeclPntDigitNum, BigDecimal.ROUND_HALF_UP);// END

        if (!ZYPCommonFunc.hasValue(aBizMsg.entDealNetUnitPrcAmt_A3)) {
            return false;
        }
        if (NFBCommonBusiness.chkNull(aBizMsg.entDealNetUnitPrcAmt_A3.getValue()).multiply(NFBCommonBusiness.chkNull(billQtyAbs)).compareTo(fromAmt) < 0) {
            return true;
        }
        if (NFBCommonBusiness.chkNull(aBizMsg.entDealNetUnitPrcAmt_A3.getValue()).multiply(NFBCommonBusiness.chkNull(billQtyAbs)).compareTo(thruAmt) > 0) {
            return true;
        }

        return false;
    }

// END   2018/03/28 [QC#24277, MOD]

    private static BigDecimal getIntTolRate(NFBL2040CMsg bizMsg) {
        BigDecimal rtnVal = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVendorInfo(bizMsg.apVndCd.getValue());
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                if (ZYPCommonFunc.hasValue((BigDecimal) rtn.get("INV_TOL_RATE"))) {
                    rtnVal = (BigDecimal) rtn.get("INV_TOL_RATE");
                }
            }
        }
        return rtnVal;
    }

    /**
     * insertVarianceData
     * @param bizMsg NFBL2040CMsg
     * @param cmInvAcctDistMap Map<List<String>, Map<String, Object>>
     * @param cmInvAcctDistPkList List<List<String>>
     * @param commitLineNumForInv int
     * @param exchRate BigDecimal
     * @param insertDbFlag boolean
     */
    public static void insertVarianceData(NFBL2040CMsg bizMsg, Map<List<String>, Map<String, Object>> cmInvAcctDistMap, List<List<String>> cmInvAcctDistPkList, int commitLineNumForInv, BigDecimal exchRate, boolean insertDbFlag) {
        BigDecimal varAmt = BigDecimal.ZERO;
        int varCnt = 1;
        int varidCntE = 0;
        for (int i = 0; i < cmInvAcctDistPkList.size(); i++) {

            List<String> pkList = cmInvAcctDistPkList.get(i);

            // START 2019/01/29 [QC#30056, ADD]
        	Map<String, Object> cmInvAcctDistNonPrimaryKeyMap = (Map<String, Object>) cmInvAcctDistMap.get(pkList);
            BigDecimal lineAmt = (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(LINE_AMT);
            BigDecimal invQty = (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(AP_BILL_QTY);
            if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
                continue;
            }
            // END   2019/01/29 [QC#30056, ADD]

            CM_INV_ACCT_DISTTMsg cmInvAcctDist = new CM_INV_ACCT_DISTTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.glblCmpyCd, pkList.get(0));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndCd, pkList.get(1));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvNum, pkList.get(2));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvSqNum, pkList.get(3));
            // START 2018/03/22 [QC#20316, MOD]
            // String mdseCd = pkList.get(4);
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, pkList.get(4));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvTpCd, pkList.get(5));
            //
            // if (ZYPCommonFunc.hasValue(pkList.get(6))) {
            //     ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, pkList.get(6));
            // } else {
            //     ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, NONE);
            // }
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.delyOrdNum, pkList.get(7));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, pkList.get(4));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, pkList.get(5));
            // END   2018/03/22 [QC#20316, MOD]

            // START 2019/01/29 [QC#30056, DEL]
//            Map<String, Object> cmInvAcctDistNonPrimaryKeyMap = (Map<String, Object>) cmInvAcctDistMap.get(pkList);
//            // QC#19261
//            BigDecimal lineAmt = (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(LINE_AMT);
            // END   2019/01/29 [QC#30056, DEL]
            BigDecimal apBillQty = (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(AP_BILL_QTY);
            BigDecimal dealGrsUnitPrcAmt = (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(DEAL_GRS_UNIT_PRC_AMT);
            BigDecimal dealGrsPrcAmt = dealGrsUnitPrcAmt.multiply(apBillQty);
            // START 2018/06/20 [QC#26169, ADD]
            // START 2018/08/15 [QC#27029-1, MOD]
            // if (ZYPConstant.FLG_ON_Y.equals((String) cmInvAcctDistNonPrimaryKeyMap.get(CM_STK_IN_PRC_FLG))) {
            // mod start 2022/02/15 QC#57090
            //if (ZYPConstant.FLG_ON_Y.equals((String) cmInvAcctDistNonPrimaryKeyMap.get(CM_STK_IN_PRC_FLG))
            //        && ZYPCommonFunc.hasValue((String) cmInvAcctDistNonPrimaryKeyMap.get(PO_NUM))) {
            if (ZYPConstant.FLG_ON_Y.equals((String) cmInvAcctDistNonPrimaryKeyMap.get(CM_STK_IN_PRC_FLG))
                    && (ZYPCommonFunc.hasValue((String) cmInvAcctDistNonPrimaryKeyMap.get(PO_NUM)) || ZYPCommonFunc.hasValue((String) cmInvAcctDistNonPrimaryKeyMap.get(VND_RTRN_NUM)))) {
            // mod end 2022/02/15 QC#57090
            // END   2018/08/15 [QC#27029-1, MOD]
                dealGrsUnitPrcAmt = (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_DEAL_NET_UNIT_PRC_AMT);
                dealGrsPrcAmt = dealGrsUnitPrcAmt.multiply(apBillQty);
            // START 2018/08/14 [QC#27029-1, ADD]
            } else if (ZYPCommonFunc.hasValue((BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(PD_ENT_DEAL_NET_UNIT_PRC_AMT))) {
                dealGrsUnitPrcAmt = (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(PD_ENT_DEAL_NET_UNIT_PRC_AMT);
                dealGrsPrcAmt = dealGrsUnitPrcAmt.multiply(apBillQty);
            }
            // END   2018/08/14 [QC#27029-1, ADD]
            // END   2018/06/20 [QC#26169, ADD]
            varAmt = lineAmt.subtract(dealGrsPrcAmt);
            String apLineTpCd = (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_LINE_TP_CD);

            if (varAmt.compareTo(BigDecimal.ZERO) != 0 && apLineTpCd.equals(AP_LINE_TP.ITEM)) {
                   ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, ZYPCommonFunc.concatString("VAR", String.format(LINE_NUM_FORMAT, varCnt), null));
                varCnt++;
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.acInvJrnlCostAmt, varAmt);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.ifProcStsCd, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvAuthFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apJrnlCpltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apLineTpCd, AP_LINE_TP.VARIANCE);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.invAsgDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.exchRate, exchRate);

                // START 2018/03/22 [QC#20316, MOD]
                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_VND_INV_LINE_NUM));
                // String cmInvAcctDistLineNumStr = (String) cmInvAcctDistNonPrimaryKeyMap.get(CM_INV_ACCT_DIST_LINE_NUM);
                // int cmInvAcctDistLineNum = Integer.parseInt(cmInvAcctDistLineNumStr) + 1;
                // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, String.format(LINE_NUM_FORMAT, cmInvAcctDistLineNum));
                String mdseCd = (String) cmInvAcctDistNonPrimaryKeyMap.get(MDSE_CD);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apVndInvLineNum, pkList.get(4));
                String cmInvAcctDistLineNumStr = pkList.get(5);
                int cmInvAcctDistLineNum = Integer.parseInt(cmInvAcctDistLineNumStr) + 1;
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.cmInvAcctDistLineNum, String.format(LINE_NUM_FORMAT, cmInvAcctDistLineNum));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.mdseCd, mdseCd);
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvTpCd, (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_TP_CD));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.poNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(PO_NUM));
                // add start 2022/02/15 QC#57090
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.vndRtrnNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(VND_RTRN_NUM));
                // add end 2022/02/15 QC#57090
                ZYPEZDItemValueSetter.setValue(cmInvAcctDist.delyOrdNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(DELY_ORD_NUM));
                // END   2018/03/22 [QC#20316, MOD]

                String xxCmntTxt = get9SegForVar(mdseCd);
                String[] strSplit = xxCmntTxt.split("\\.");
                if (ZYPCommonFunc.hasValue(xxCmntTxt)) {
                    if (strSplit.length > 0) {
                        // QC#24985
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaCmpyCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[0].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCmpyCd, strSplit[0]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCmpyCd, strSplit[0]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                    if (strSplit.length > 1) {
                        // QC#24985
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaBrCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[1].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaBrCd, strSplit[1]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaBrCd, strSplit[1]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                    if (strSplit.length > 2) {
                        // QC#24985
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaCcCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[2].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaCcCd, strSplit[2]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaCcCd, strSplit[2]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                    if (strSplit.length > 3) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaAcctCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[3].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAcctCd, strSplit[3]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAcctCd, strSplit[3]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                    if (strSplit.length > 4) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaProdCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[4].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProdCd, strSplit[4]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProdCd, strSplit[4]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                    if (strSplit.length > 5) {
                        // QC#24985
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaChCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[5].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaChCd, strSplit[5]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaChCd, strSplit[5]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                    if (strSplit.length > 6) {
                        // QC#24985
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaAfflCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[6].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaAfflCd, strSplit[6]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaAfflCd, strSplit[6]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                    if (strSplit.length > 7) {
                        // QC#24985
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaProjCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[7].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaProjCd, strSplit[7]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaProjCd, strSplit[7]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                    if (strSplit.length > 8) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("drCoaExtnCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[8].length()) {
                            // START 2018/03/22 [QC#20316, MOD]
                            // ZYPEZDItemValueSetter.setValue(cmInvAcctDist.coaExtnCd, strSplit[8]);
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.drCoaExtnCd, strSplit[8]);
                            // END   2018/03/22 [QC#20316, MOD]
                        }
                    }
                }
                String apAcctDescTx = getVarianceAccountDiscriptionDefaultValue(mdseCd);
                if (ZYPCommonFunc.hasValue(bizMsg.E.no(i).apAcctDescTxt_E1)) {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, bizMsg.E.no(i).apAcctDescTxt_E1);
                } else {
                    if (ZYPCommonFunc.hasValue(apAcctDescTx)) {
                        ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apAcctDescTxt, apAcctDescTx);
                    }
                }

                // START 2018/08/21 [QC#27873, ADD]
                String xxCmntTxtCr = getCreditLineAccountCode(bizMsg.apVndCd.getValue());
                strSplit = xxCmntTxtCr.split("\\.");
                if (ZYPCommonFunc.hasValue(xxCmntTxt)) {
                    if (strSplit.length > 0) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaCmpyCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[0].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCmpyCd, strSplit[0]);
                        }
                    }
                    if (strSplit.length > 1) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaBrCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[1].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaBrCd, strSplit[1]);
                        }
                    }
                    if (strSplit.length > 2) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaCcCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[2].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaCcCd, strSplit[2]);
                        }
                    }
                    if (strSplit.length > 3) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaAcctCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[3].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAcctCd, strSplit[3]);
                        }
                    }
                    if (strSplit.length > 4) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaProdCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[4].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProdCd, strSplit[4]);
                        }
                    }
                    if (strSplit.length > 5) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaChCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[5].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaChCd, strSplit[5]);
                        }
                    }
                    if (strSplit.length > 6) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaAfflCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[6].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaAfflCd, strSplit[6]);
                        }
                    }
                    if (strSplit.length > 7) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaProjCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[7].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaProjCd, strSplit[7]);
                        }
                    }
                    if (strSplit.length > 8) {
                        EZDItemAttribute columAtt = new CM_INV_ACCT_DISTTMsg().getAttr("crCoaExtnCd");
                        int colCnt = columAtt.getDigit();
                        if (colCnt >= strSplit[8].length()) {
                            ZYPEZDItemValueSetter.setValue(cmInvAcctDist.crCoaExtnCd, strSplit[8]);
                        }
                    }
                }

                EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
                int digitApInvDescTxt = getDigitApInvDescTxt.getDigit();
                String apInvDescTxt = apAcctDescTx;
                if (ZYPCommonFunc.hasValue(apInvDescTxt) && apInvDescTxt.length() > digitApInvDescTxt) {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, S21StringUtil.subStringByLength(apInvDescTxt, 0, digitApInvDescTxt));
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvAcctDist.apInvDescTxt, apInvDescTxt);
                }
                // END   2018/08/21 [QC#27873, ADD]

                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).apLineTpCd_E1, cmInvAcctDist.apLineTpCd); // Line
                // Type
                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).mdseCd_E1, cmInvAcctDist.mdseCd); // Item
                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxInvAmt_E1, cmInvAcctDist.acInvJrnlCostAmt); // Line
                // Amount
                if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).xxCmntTxt_E1)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).xxCmntTxt_E1, xxCmntTxt); // Charge
                    // Account
                }
                if (!ZYPCommonFunc.hasValue(bizMsg.E.no(i).apAcctDescTxt_E1)) {
                    if (ZYPCommonFunc.hasValue(apAcctDescTx)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.E.no(i).apAcctDescTxt_E1, apAcctDescTx); // Account
                        // Discription
                    }
                }
                varidCntE++;
                if (insertDbFlag) {
                    // Create new invoice record.
                    EZDTBLAccessor.create(cmInvAcctDist);
                }
            }
        }
        bizMsg.E.setValidCount(varidCntE);
    }

    private static int insertRowVarianceData(NFBL2040CMsg bizMsg, int i, int validCountD, int validCountE) {
        BigDecimal varAmt = BigDecimal.ZERO;

        if (AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
            BigDecimal acInvJrnlCostAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue());
            BigDecimal apBillQty = NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue());
            BigDecimal dealGrsUnitPrcAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue());
            // START 2019/08/15 [QC#52280, ADD]
            if (ACCT_INV_STS.CANCEL.equals(bizMsg.acctInvStsCd.getValue())) {
                BigDecimal apRejQty = NFBCommonBusiness.chkNull(bizMsg.A.no(i).apRejQty_A1.getValue());
                apBillQty = apBillQty.add(apRejQty);
            }
            // END   2019/08/15 [QC#52280, ADD]
            BigDecimal dealGrsPrcAmt = NFBCommonBusiness.chkNull(dealGrsUnitPrcAmt.multiply(apBillQty));
            // START 2018/06/20 [QC#26169, ADD] Modify QC#27026 2018/07/19
            // START 2018/08/15 [QC#27029-1, MOD]
            // if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2) //
            //         && BigDecimal.ZERO.compareTo(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()) != 0) {
            //     dealGrsPrcAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue().multiply(apBillQty));
            if (isEffectiveStkInPrice(bizMsg.A.no(i))) {
                dealGrsPrcAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue().multiply(apBillQty));

            // END   2018/08/15 [QC#27029-1, MOD]
            // START 2018/08/14 [QC#27029-1, ADD]
            } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3)) {
                dealGrsPrcAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3.getValue().multiply(apBillQty));
            }
            // END   2018/08/14 [QC#27029-1, ADD]
            // END   2018/06/20 [QC#26169, ADD]
            varAmt = acInvJrnlCostAmt.subtract(dealGrsPrcAmt);

            if (BigDecimal.ZERO.compareTo(varAmt) != 0) {
                // START 2018/08/24 [QC#27563-1, ADD]
                String mdseCd = bizMsg.A.no(i).mdseCd_A1.getValue();
                if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
                    mdseCd = bizMsg.A.no(i).xxMdseCd_A1.getValue();
                }
                // END   2018/08/24 [QC#27563-1, ADD]
                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).apLineTpCd_E1, AP_LINE_TP.VARIANCE); // Line
                // Type
                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).mdseCd_E1, ZYPCommonFunc.concatString("VAR", String.format(LINE_NUM_FORMAT, validCountE + 1), null));
                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).xxInvAmt_E1, varAmt); // Line
                // Amount
                // START 2018/08/24 [QC#27563-1, MOD]
                // ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).xxCmntTxt_E1, get9SegForVar(bizMsg.A.no(i).mdseCd_A1.getValue()));// Charge
                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).xxCmntTxt_E1, get9SegForVar(mdseCd));// Charge
                // END   2018/08/24 [QC#27563-1, MOD]
                // Account
                // START 2018/08/24 [QC#27563-1, MOD]
                // String apAcctDescTx = getVarianceAccountDiscriptionDefaultValue(bizMsg.A.no(i).mdseCd_A1.getValue());
                String apAcctDescTx = getVarianceAccountDiscriptionDefaultValue(mdseCd);
                // END   2018/08/24 [QC#27563-1, MOD]
                if (ZYPCommonFunc.hasValue(apAcctDescTx) && !ZYPCommonFunc.hasValue(bizMsg.E.no(validCountE).apAcctDescTxt_E1)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).apAcctDescTxt_E1, apAcctDescTx);// Account
                    // Discription
                }
                // START 2018/03/22 [QC#20316, MOD]
                // ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).xxDtlLineNum_E1, String.format(LINE_NUM_FORMAT, i + 1));
                // START 2019/01/29 [QC#30056, ADD]
//                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).xxDtlLineNum_E1, String.format(AP_VND_INV_LINE_NUM_FORMAT, i + 1));
                ZYPEZDItemValueSetter.setValue(bizMsg.E.no(validCountE).xxDtlLineNum_E1, bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
                // END   2019/01/29 [QC#30056, ADD]
                // END   2018/03/22 [QC#20316, MOD]
                validCountE++;
            }
        }
        return validCountE;
    }

    /**
     * getVarianceAccountDiscriptionDefaultValue
     * @return CmCoaAcctCd default value String
     */
    private static String getVarianceAccountDiscriptionDefaultValue(String mdseCd) {
        String cmCoaAcctCd = getCoaAcctCdForVar(mdseCd);
        if (!ZYPCommonFunc.hasValue(cmCoaAcctCd)) {
            return EMPTY_STRING;
        }

        COA_ACCTTMsg coaAcct = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(coaAcct.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(coaAcct.coaAcctCd, cmCoaAcctCd);
        coaAcct = (COA_ACCTTMsg) EZDTBLAccessor.findByKey(coaAcct);
        if (coaAcct == null) {
            return EMPTY_STRING;
        } else {
            return NFBCommonBusiness.chkNull(coaAcct.coaAcctDescTxt.getValue());
        }
    }

    /**
     * Method name: setDistributionsTabDebitLineInfo for Variance
     * @param cMsg Business Component Interface Message
     * @param validCountD int
     * @param dtlLineNum String
     */
    private static void setDistributionsTabVarDebitLineInfoForVariance(EZDCMsg cMsg, int validCountD, int validCountE, String dtlLineNum) {

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        int indexD = validCountD;
        int indexE = validCountE - 1;
        int cmInvAcctDistLineNum = 2;
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexD).xxDtlLineNum_D1, NFBCommonBusiness.chkNull(bizMsg.E.no(indexE).xxDtlLineNum_E1.getValue())); // Line
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexD).xxDtlLineNum_D2, String.format(PO_ORD_DTL_LINE_NUM_FORMAT, cmInvAcctDistLineNum)); // Distribution
        // Line
        // Number
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexD).invDt_D1, bizMsg.invDt.getValue()); // Date
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexD).jrnlFuncDrAmt_D1, NFBCommonBusiness.chkNull(bizMsg.E.no(indexE).xxInvAmt_E1.getValue())); // Debit
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexD).jrnlFuncCrAmt_D1, BigDecimal.ZERO); // Credit
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexD).apAcctDescTxt_D1, bizMsg.E.no(indexE).apAcctDescTxt_E1.getValue()); // Account
        // Description
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(indexD).xxCmntTxt_D1, bizMsg.E.no(indexE).xxCmntTxt_E1.getValue()); // Account
        // Code
    }

    /**
     * getCoaAcctDescTxt
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

    /**
     * getCcyCd
     * @return ccyCd String
     */
    public static String getCcyCd() {
        // Currency Code
        String ccyCd = EMPTY_STRING;
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, GLBL_CMPY_CD);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblCmpyTMsg);
        if (glblCmpyTMsg != null) {
            ccyCd = glblCmpyTMsg.stdCcyCd.getValue();
        }
        return ccyCd;
    }

    /**
     * getAftDeclPntDigitNum
     * @return ccyCd int
     */
    public static int getAftDeclPntDigitNum() {

        int aftDeclPntDigitNum = 0;
        String ccyCd = getCcyCd();
        if (ZYPCommonFunc.hasValue(ccyCd)) {
            CCYTMsg ccyTMsg = new CCYTMsg();
            ZYPEZDItemValueSetter.setValue(ccyTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(ccyTMsg.ccyCd, ccyCd);
            ccyTMsg = (CCYTMsg) S21FastTBLAccessor.findByKey(ccyTMsg);
            if (ccyTMsg != null) {
                aftDeclPntDigitNum = ccyTMsg.aftDeclPntDigitNum.getValueInt();
            }
        }
        return aftDeclPntDigitNum;
    }

    /**
     * calcTermsDate
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void calcTermsDate(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        if (ZYPCommonFunc.hasValue(bizMsg.invDt) && ZYPCommonFunc.hasValue(bizMsg.vndPmtTermDescTxt)) {
            S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPmtTermDt(bizMsg);
            if (ssmResult != null && ssmResult.isCodeNormal()) {
                Map<String, Object> rtn = (Map<String, Object>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.termNetDueDt, (String) rtn.get(TERM_NET_DUE_DT));
            } else {
                bizMsg.termNetDueDt.clear();
            }
        } else {
            bizMsg.termNetDueDt.clear();
        }
    }

    /**
     * get9SegForVar
     * @return 9seg For Variance String
     */
    public static String get9SegForVar(String mdseCd) {
        DefCoaValues defCoa = NFBCommonBusiness.getDefCoaValues(GLBL_CMPY_CD);
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getCoaCdForVar(GLBL_CMPY_CD, mdseCd);
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList != null) {
                StringBuffer coa = new StringBuffer();
                coa.append(defCoa.getDefCoaCmpy()).append(DOT);
                // START 2018/08/02 E.Kameishi [QC#27557,MOD]
                //coa.append(defCoa.getDefCoaBr()).append(DOT);
                if (resultList.get(0).get("CM_COA_BR_CD") != null) {
                    coa.append(resultList.get(0).get("CM_COA_BR_CD")).append(DOT);
                } else {
                    coa.append(EMPTY_STRING).append(DOT);
                }
                if (resultList.get(0).get("COA_CC_CD") != null) {
                    coa.append(resultList.get(0).get("COA_CC_CD")).append(DOT);
                } else {
                    coa.append(EMPTY_STRING).append(DOT);
                }
                if (resultList.get(0).get("COA_ACCT_CD") != null) {
                    coa.append(resultList.get(0).get("COA_ACCT_CD")).append(DOT);
                } else {
                    coa.append(EMPTY_STRING).append(DOT);
                }
                if (resultList.get(0).get("COA_PROD_CD") != null) {
                    coa.append(resultList.get(0).get("COA_PROD_CD")).append(DOT);
                } else {
                    coa.append(EMPTY_STRING).append(DOT);
                }
                //coa.append(defCoa.getDefCoaCh()).append(DOT);
                if (resultList.get(0).get("CM_COA_CH_CD") != null) {
                    coa.append(resultList.get(0).get("CM_COA_CH_CD")).append(DOT);
                } else {
                    coa.append(EMPTY_STRING).append(DOT);
                }
                //coa.append(defCoa.getDefCoaAffl()).append(DOT);
                if (resultList.get(0).get("CM_COA_AFFL_CD") != null) {
                    coa.append(resultList.get(0).get("CM_COA_AFFL_CD")).append(DOT);
                } else {
                    coa.append(EMPTY_STRING).append(DOT);
                }
                if (resultList.get(0).get("COA_MDSE_TP_CD") != null) {
                    coa.append(resultList.get(0).get("COA_MDSE_TP_CD")).append(DOT);
                } else {
                    coa.append(EMPTY_STRING).append(DOT);
                }
                //coa.append(VAR_COA_EXTN_CD_010);
                if (resultList.get(0).get("CM_COA_EXTN_CD") != null) {
                    coa.append(resultList.get(0).get("CM_COA_EXTN_CD"));
                } else {
                    coa.append(EMPTY_STRING);
                }
                // END 2018/08/02 E.Kameishi [QC#27557,MOD]
                return coa.toString();
            }
        }
        return EMPTY_STRING;
    }

    /**
     * getCoaAcctCdForVar
     * @return CoaAcctCd For Variance String
     */
    private static String getCoaAcctCdForVar(String mdseCd) {

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getCoaCdForVar(GLBL_CMPY_CD, mdseCd);
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList != null) {
                return (String) resultList.get(0).get("COA_ACCT_CD");
            }
        }
        return EMPTY_STRING;
    }

    /**
     * refreshData
     * @return Error boolean
     */
    public static boolean refreshData(NFBL2040CMsg bizMsg) {

        bizMsg.apVndCd_OT.setValue(bizMsg.apVndCd.getValue());
        bizMsg.apVndInvNum_OT.setValue(bizMsg.apVndInvNum.getValue());
        // START 2018/02/27 [QC#23505, MOD]
        // bizMsg.apVndInvSqNum_OT.setValue(AP_VND_INV_SQ_NUM_00);
        bizMsg.apVndInvSqNum_OT.setValue(bizMsg.apVndInvSqNum.getValue());
        // END   2018/02/27 [QC#23505, MOD]
        if (!NFBL2040CommonLogic.getInvRecordFromOtherScreen(bizMsg)) {
            // Error
            return true;
        }
        return false;
    }
    
    /**
     * getPoDtlListForUpdateNoWait
     * @param poOrdNum
     * @return ArrayList<PO_DTLTMsg>
     */
    public static ArrayList<PO_DTLTMsg> getPoDtlListForUpdateNoWait(String poOrdNum) {
        PO_DTLTMsgArray poDtlArray = NFBL2040Query.getInstance().getPoDtlListForUpdateNoWait(GLBL_CMPY_CD, poOrdNum);

        ArrayList<PO_DTLTMsg> poDtlList = new ArrayList<PO_DTLTMsg>();

        for (int i = 0; i < poDtlArray.length(); i++) {
            poDtlList.add(poDtlArray.no(i));
        }

        return poDtlList;
    }

    /**
     * getAccountInfo(QC#21727)
     * @param bizMsg
     * @param index
     * @return
     */
    public static boolean getAccountInfo(NFBL2040CMsg bizMsg, int index) {
        List<Map<String, String>> resultMap = null;
        S21SsmEZDResult ssmResult = new S21SsmEZDResult();

        // START 2017/12/26 [QC#23022, MOD]
        if (AP_LINE_TP.FREIGHT.equals(bizMsg.A.no(index).apLineTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).mdseCd_A1, FREIGHT);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).xxMdseCd_A1, FREIGHT);
        }
        // END   2017/12/26 [QC#23022, MOD]
        // START 2018/03/07 J.Kim [QC#24636,MOD]
        if (AP_LINE_TP.TAX.equals(bizMsg.A.no(index).apLineTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).mdseCd_A1, TAX);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).xxMdseCd_A1, TAX);
        }
        // END 2018/03/07 J.Kim [QC#24636,MOD]
        // START QC#25902,QC#25190,QC#25141
        if (AP_LINE_TP.MISC.equals(bizMsg.A.no(index).apLineTpCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).mdseCd_A1, MISC);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).xxMdseCd_A1, MISC);
        }
        // END QC#25902,QC#25190,QC#25141

        String appFuncId = "NFBL2040";
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).apLineTpCd_A1)) {
            String apLineTpCd = bizMsg.A.no(index).apLineTpCd_A1.getValue();
            if (AP_LINE_TP.ITEM.equals(apLineTpCd)) {
                appFuncId = "NFBL204001" ;
            } else if (AP_LINE_TP.TAX.equals(apLineTpCd)) {
                appFuncId = "NFBL204002" ;
            } else if (AP_LINE_TP.FREIGHT.equals(apLineTpCd)) {
                appFuncId = "NFBL204003" ;
            // START QC#25902,QC#25190,QC#25141
            } else if (AP_LINE_TP.MISC.equals(apLineTpCd)) {
                appFuncId = "NFBL204005";
            }
            // END QC#25902,QC#25190,QC#25141
        }
        ssmResult = NFBL2040Query.getInstance().getChargeAcct(bizMsg.apVndCd.getValue(), appFuncId);

        if (ssmResult.isCodeNormal()) {
            resultMap = (List<Map<String, String>>) ssmResult.getResultObject();
            if (resultMap != null && !resultMap.isEmpty()) {
                // START 2018/03/09 [QC#24140, ADD]
                String coaCmpyCd = (String) resultMap.get(0).get("COA_CMPY_CD");
                if (!ZYPCommonFunc.hasValue(coaCmpyCd)) {
                    bizMsg.A.no(index).xxCmntTxt_A1.clear();
                    return true;
                }
                // END   2018/03/09 [QC#24140, ADD]

                // QC#21727-1
                StringBuilder sb = new StringBuilder();
                sb.append((String) resultMap.get(0).get("COA_CMPY_CD"));
                sb.append(".");
                sb.append((String) resultMap.get(0).get("COA_BR_CD"));
                sb.append(".");
                sb.append((String) resultMap.get(0).get("COA_CC_CD"));
                sb.append(".");
                sb.append((String) resultMap.get(0).get("COA_ACCT_CD"));
                sb.append(".");
                sb.append((String) resultMap.get(0).get("COA_PROD_CD"));
                sb.append(".");
                sb.append((String) resultMap.get(0).get("COA_CH_CD"));
                sb.append(".");
                sb.append((String) resultMap.get(0).get("COA_AFFL_CD"));
                sb.append(".");
                sb.append((String) resultMap.get(0).get("COA_PROJ_CD"));
                sb.append(".");
                sb.append((String) resultMap.get(0).get("COA_EXTN_CD"));
//                sb.append((String) resultMap.get(0).get("COA_CMPY_CD"));
//                sb.append(".");
//                sb.append((String) resultMap.get(0).get("COA_AFFL_CD"));
//                sb.append(".");
//                sb.append((String) resultMap.get(0).get("COA_BR_CD"));
//                sb.append(".");
//                sb.append((String) resultMap.get(0).get("COA_CC_CD"));
//                sb.append(".");
//                sb.append((String) resultMap.get(0).get("COA_ACCT_CD"));
//                sb.append(".");
//                sb.append((String) resultMap.get(0).get("COA_PROD_CD"));
//                sb.append(".");
//                sb.append((String) resultMap.get(0).get("COA_CH_CD"));
//                sb.append(".");
//                sb.append((String) resultMap.get(0).get("COA_PROJ_CD"));
//                sb.append(".");
//                sb.append((String) resultMap.get(0).get("COA_EXTN_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).xxCmntTxt_A1,
                        sb.toString());
            }
        }
        return true;
    }

    // START 2018/11/20 [QC#28660, MOD]
    /**
     * getSumBilledQty (QC#21666)
     * @param poOrdNum
     * @param mdseCd
     * @return
     */
    public static BigDecimal getSumBilledQty(NFBL2040CMsg bizMsg, String poOrdNum, String mdseCd) {
    	BigDecimal sumBilledQty = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult= NFBL2040Query.getInstance().getSumBilledQtyExcluveCanInv(bizMsg, poOrdNum, mdseCd);
        List<Map<String, Object>>resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resultList.size() > 0) {
            Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
            sumBilledQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
        }
        return sumBilledQty;

    }
    // END   2018/11/20 [QC#28660, MOD]

    // START 2017/12/26 [QC#23022, ADD]
    public static boolean isBlankMdseCdByApInvCatgCd(NFBL2040CMsg bizMsg) {
        String constValue = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_EMPTY_MDSE_AP_INV_CATG_CD, GLBL_CMPY_CD);
        if (!ZYPCommonFunc.hasValue(constValue)) {
            return false;
        }

        String[] apInvCatgCds = constValue.split(",");
        for (String apInvCatgCd : apInvCatgCds) {
            if (apInvCatgCd.equals(bizMsg.apInvCatgCd.getValue())) {
                return true;
            }
        }
        return false;
    }
    // END   2017/12/26 [QC#23022, ADD]

    // START 2018/01/09 [QC#22143, ADD]
    /**
     * isInvoicePKModified
     * @param bizMsg NFBL2040CMsg
     * @return boolean
     */
    public static boolean isInvoicePKModified(NFBL2040CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.apVndCd_OT) || !ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) || !ZYPCommonFunc.hasValue(bizMsg.apVndInvSqNum_OT)) {
            return false;
        }

        if (!bizMsg.apVndCd_OT.getValue().equals(bizMsg.apVndCd.getValue())) {
            return true;
        }

        if (!bizMsg.apVndInvNum_OT.getValue().equals(bizMsg.apVndInvNum.getValue())) {
            return true;
        }

        // START 2018/02/27 [QC#23505, MOD]
        // if (!bizMsg.apVndInvSqNum_OT.getValue().equals(AP_VND_INV_SQ_NUM_00)) {
        //     return true;
        // }
        if (!bizMsg.apVndInvSqNum_OT.getValue().equals(bizMsg.apVndInvSqNum.getValue())) {
            return true;
        }
        // END   2018/02/27 [QC#23505, MOD]

        return false;
    }
    // END   2018/01/09 [QC#22143, ADD]

 // START 2018/01/25 S.Katsuma [QC#19579,ADD]
    /**
     * Method name: getPmtHldPulldownList <dd>The method
     * explanation: Get records for [Hold Name] pulldown list.
     * <dd>Remarks:
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List getPmtHldPulldownList() {

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPmtHldPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            return resultList;
        } else {
            return new ArrayList();
        }

    }

    /**
     * Method name: getPmtHldRsnPulldownList <dd>The method
     * explanation: Get records for [Hold Reason] pulldown list.
     * <dd>Remarks:
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static List getPmtHldRsnPulldownList() {

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPmtHldRsnPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            return resultList;
        } else {
            return new ArrayList();
        }

    }
    // END 2018/01/25 S.Katsuma [QC#19579,ADD]

    // START 2018/01/29 [QC#18602-Sol#102,ADD]
    /**
     * Get CM_AP_INV_DTL list by partial keys.
     * @param poOrdNum String
     * @return List<Map<String, Object>>
     */
    public static boolean chkPOCloseforBP(String poOrdNum, String apVndInvNum, String mdseCd) {

        // PO Type
        String dsPoTpCd = NFBL2040CommonLogic.getDsPoTpCd(poOrdNum);

        if (DS_PO_TP.BLANKET_PO.equals(dsPoTpCd)) {
            // Blanket PO
            BigDecimal totalInvAmt = NFBL2040Query.getInstance().getInvoicePoTotalAmtByMdse(poOrdNum, mdseCd);
            BigDecimal poAmt = NFBL2040Query.getInstance().getPoLineAmtByMdse(poOrdNum, mdseCd);

            if (poAmt.compareTo(totalInvAmt) > 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    // END 2018/01/29 [QC#18602-Sol#102,ADD]

    // START 2018/01/30 [QC#23837, ADD]
    /**
     * isBlanketPO
     * @param poOrdNum String
     * @return boolean
     */
    public static boolean isBlanketPO(String poOrdNum) {
        // PO Type
        String dsPoTpCd = NFBL2040CommonLogic.getDsPoTpCd(poOrdNum);

        if (DS_PO_TP.BLANKET_PO.equals(dsPoTpCd)) {
            return true;
        } else {
            return false;
        }
    }
    // END 2018/01/30 [QC#23837, ADD]

    // START 2018/02/23 [QC#23505, ADD]
    public static boolean isUnsubmittedCorrection(NFBL2040CMsg bizMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_CR.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean isCorrectedInvoice(NFBL2040CMsg bizMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_CR.getValue())) {
            return true;
        }
        return false;
    }

    public static boolean isCorrectableInvoice(NFBL2040CMsg bizMsg) {
        // START 2020/02/17 [QC#53413, MOD]
        //if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HT) || !ZYPCommonFunc.hasValue(bizMsg.poStsCd_HD)
        if (!ZYPCommonFunc.hasValue(bizMsg.poStsCd_HD)
        // END   2020/02/17 [QC#53413, MOD]
                || CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            return false;
        }
        if (bizMsg.acctInvStsCd.getValue().equals(ACCT_INV_STS.LINKED_TO_COST_CALCULATION)
                || bizMsg.acctInvStsCd.getValue().equals(ACCT_INV_STS.PAID) || bizMsg.acctInvStsCd.getValue().equals(ACCT_INV_STS.VOIDED)) {
            return true;
        }
        return false;
    }

    public static boolean isValidLine(NFBL2040CMsg bizMsg, NFBL2040_ACMsg bizAMsg) {
        if (XX_LINE_TP_CD_INVALID.equals(bizAMsg.xxLineTpCd_A1.getValue())
                || XX_LINE_TP_CD_CREDIT.equals(bizAMsg.xxLineTpCd_A1.getValue())) {
            return false;
        }
        return true;
    }

    private static String getLineTpCd(NFBL2040CMsg bizMsg, BigDecimal invAmt, String apVndInvSqNum) {
        if (!ZYPCommonFunc.hasValue(bizMsg.apVndInvSqNum) || !ZYPCommonFunc.hasValue(apVndInvSqNum)) {
            return null;
        }

        if (!bizMsg.apVndInvSqNum.getValue().equals(apVndInvSqNum)) {
            // START 2018/10/11 [QC#28038, ADD]
//            if (BigDecimal.ZERO.compareTo(NFBCommonBusiness.chkNull(invAmt)) == -1) {
            if (BigDecimal.ZERO.compareTo(NFBCommonBusiness.chkNull(invAmt)) == 1) {
            // END 2018/10/11 [QC#28038, ADD]
                return XX_LINE_TP_CD_CREDIT;
            }
            return XX_LINE_TP_CD_INVALID;
        }
        return XX_LINE_TP_CD_VALID;
    }

    // MOD QC#25902,QC#25190,QC#25141
    public static void addCreditLines(NFBL2040CMsg bizMsg, boolean isLine) {
        int newInvSqNum = Integer.valueOf(bizMsg.apVndInvSqNum.getValue(), 10) + 1;
        String strNewInvSqNum = String.format(AP_VND_INV_SQ_NUMFORMAT, newInvSqNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvSqNum, strNewInvSqNum);

        List<NFBL2040_ACMsg> origLineList = new ArrayList<NFBL2040_ACMsg>(bizMsg.A.getValidCount());
        // START QC#25902,QC#25190,QC#25141
        if (isLine) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue()) && XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                    origLineList.add(bizMsg.A.no(i));
                }
            }
        } else {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                    origLineList.add(bizMsg.A.no(i));
                }
            }   
        }
        // END QC#25902,QC#25190,QC#25141

        int destIdx = bizMsg.A.getValidCount() - 1;
        // START 2019/01/29 [QC#30056, ADD]
        int lineNum = Integer.parseInt(bizMsg.A.no(destIdx).xxDtlLineNum_A1.getValue());
        // END   2019/01/29 [QC#30056, ADD]
        for (NFBL2040_ACMsg origLine : origLineList) {
            destIdx++;
            bizMsg.A.no(destIdx).clear();
            EZDMsg.copy(origLine, null, bizMsg.A.get(destIdx), null);
            bizMsg.A.no(destIdx).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
            // START 2019/01/29 [QC#30056, MOD]
//            editCreditLine(bizMsg, bizMsg.A.no(destIdx), destIdx + 1, strNewInvSqNum);
            editCreditLine(bizMsg, bizMsg.A.no(destIdx), ++lineNum, strNewInvSqNum);
            // END 2019/01/29 [QC#30056, MOD]
        }
        bizMsg.A.setValidCount(destIdx + 1);
        // START 2020/02/17 [QC#53413, ADD]
        if (!isLine) {
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(j).xxLineTpCd_A1) || (ZYPCommonFunc.hasValue(bizMsg.A.no(j).xxLineTpCd_A1) && !XX_LINE_TP_CD_CREDIT.equals(bizMsg.A.no(j).xxLineTpCd_A1.getValue()))) {
                    bizMsg.A.no(j).xxLineTpCd_A1.setValue(XX_LINE_TP_CD_INVALID);
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_MP, ZYPConstant.FLG_OFF_N);
       // END   2020/02/17 [QC#53413, ADD]
    }

    private static void editCreditLine(NFBL2040CMsg bizMsg, NFBL2040_ACMsg aBizMsg, int lineNum, String apVndInvSqNum) {

        // numbering Line #
        // START 2018/03/22 [QC#20316, MOD]
        // ZYPEZDItemValueSetter.setValue(aBizMsg.xxDtlLineNum_A1, String.format(LINE_NUM_FORMAT, lineNum));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxDtlLineNum_A1, String.format(AP_VND_INV_LINE_NUM_FORMAT, lineNum));
        // END   2018/03/22 [QC#20316, MOD]

        // negate Qty / Amount
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxInvAmt_A1, NFBCommonBusiness.chkNull(aBizMsg.xxInvAmt_A1.getValue()).negate());
        ZYPEZDItemValueSetter.setValue(aBizMsg.poQty_A1, NFBCommonBusiness.chkNull(aBizMsg.poQty_A1.getValue()).negate());
        ZYPEZDItemValueSetter.setValue(aBizMsg.invRcvQty_A1, NFBCommonBusiness.chkNull(aBizMsg.invRcvQty_A1.getValue()).negate());
        ZYPEZDItemValueSetter.setValue(aBizMsg.apRejQty_A1, NFBCommonBusiness.chkNull(aBizMsg.apRejQty_A1.getValue()).negate());
        ZYPEZDItemValueSetter.setValue(aBizMsg.apBillQty_A1, NFBCommonBusiness.chkNull(aBizMsg.apBillQty_A1.getValue()).negate());
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxInvQty_A1, NFBCommonBusiness.chkNull(aBizMsg.xxInvQty_A1.getValue()).negate());

        // set SqNum : Credit line
        ZYPEZDItemValueSetter.setValue(aBizMsg.apVndInvSqNum_A1, apVndInvSqNum);
        // set Line Type : Credit line
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxLineTpCd_A1, XX_LINE_TP_CD_CREDIT);
    }

    // MOD QC#25902,QC#25190,QC#25141
    public static void addCopiedOriginalLines(NFBL2040CMsg bizMsg, boolean isLine) {

        List<NFBL2040_ACMsg> origLineList = new ArrayList<NFBL2040_ACMsg>(bizMsg.A.getValidCount());
        List<Integer> origLineIdxList = new ArrayList<Integer>(bizMsg.A.getValidCount());

        int newInvSqNum = Integer.valueOf(bizMsg.apVndInvSqNum.getValue(), 10) + 1;
        String strNewInvSqNum = String.format(AP_VND_INV_SQ_NUMFORMAT, newInvSqNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvSqNum, strNewInvSqNum);
        // START QC#25902,QC#25190,QC#25141
        if (isLine) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue()) && XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                    origLineList.add(bizMsg.A.no(i));
                    origLineIdxList.add(i);
                } else if ((ZYPConstant.FLG_OFF_N.equals(bizMsg.A.no(i).xxChkBox_A1.getValue()) || "".equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) && XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxLineTpCd_A1, XX_LINE_TP_CD_NO_CHANGE);
                }
            }
        } else {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                    origLineList.add(bizMsg.A.no(i));
                    origLineIdxList.add(i);
                }
            }
        }
        // END QC#25902,QC#25190,QC#25141

        // debug
//        boolean result = addLineRecord(bizMsg, isLine);
//        if (!result) {
//            return;
//        }

        int destIdx = bizMsg.A.getValidCount() - 1;
        // START 2019/01/29 [QC#30056, ADD]
        int lineNum = Integer.parseInt(bizMsg.A.no(destIdx).xxDtlLineNum_A1.getValue());
        // END   2019/01/29 [QC#30056, ADD]
        for (NFBL2040_ACMsg origLine : origLineList) {
            // START QC#25902,QC#25190,QC#25141
            if (!AP_LINE_TP.ITEM.equals(origLine.apLineTpCd_A1.getValue())) {
                destIdx++;
                bizMsg.A.no(destIdx).clear();
                EZDMsg.copy(origLine, null, bizMsg.A.get(destIdx), null);
                // START 2019/01/29 [QC#30056, MOD]
//                editCopiedOriginalLine(bizMsg, bizMsg.A.no(destIdx), destIdx + 1, strNewInvSqNum);
                editCopiedOriginalLine(bizMsg, bizMsg.A.no(destIdx), ++lineNum, strNewInvSqNum);
                // START 2020/02/17 [QC#53413, MOD]
                if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(destIdx).poNum_A1, bizMsg.poNum_HT);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(destIdx).poNum_A1, bizMsg.poNum);
                }
                // END   2020/02/17 [QC#53413, MOD]
                // END   2019/01/29 [QC#30056, MOD]
            }
            // END QC#25902,QC#25190,QC#25141
        }
        bizMsg.A.setValidCount(destIdx + 1);

        for (int origIdx : origLineIdxList) {
            NFBL2040_ACMsg aBizMsg = bizMsg.A.no(origIdx);
            // set Line Type : Invalid line
            ZYPEZDItemValueSetter.setValue(aBizMsg.xxLineTpCd_A1, XX_LINE_TP_CD_INVALID);
        }
    }

    private static void editCopiedOriginalLine(NFBL2040CMsg bizMsg, NFBL2040_ACMsg aBizMsg, int lineNum, String apVndInvSqNum) {
        // numbering Line #
        // START 2018/03/22 [QC#20316, MOD]
        // ZYPEZDItemValueSetter.setValue(aBizMsg.xxDtlLineNum_A1, String.format(LINE_NUM_FORMAT, lineNum));
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxDtlLineNum_A1, String.format(AP_VND_INV_LINE_NUM_FORMAT, lineNum));
        // END   2018/03/22 [QC#20316, MOD]
        // set SqNum : Credit line
        ZYPEZDItemValueSetter.setValue(aBizMsg.apVndInvSqNum_A1, apVndInvSqNum);
        // START QC#25902,QC#25190,QC#25141
        ZYPEZDItemValueSetter.setValue(aBizMsg.xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        // END QC#25902,QC#25190,QC#25141
    }

    /**
     * MOD QC#25902,QC#25190,QC#25141
     * addLineRecord
     * @param bizMsg NFBL2040CMsg
     * @param isLine boolean
     * @return boolean
     */
    public static boolean addLineRecord(NFBL2040CMsg bizMsg, boolean isLine) {
        EZDDebugOutput.println(5, "addLineRecord============================START", null);
        // START 2020/02/17 [QC#53413, MOD]
        //if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HT) 
        //        || CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) {
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) {
        // END   2020/02/17 [QC#53413, MOD]
            return false;
        }

        // Varchar Const value(NFBL2040_AP_INV_CATG_CD)
        String apInvCatgCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_AP_INV_CATG_CD, GLBL_CMPY_CD);
        // Default COA Values
        DefCoaValues defCoa = NFBCommonBusiness.getDefCoaValues(GLBL_CMPY_CD);
        // START QC#25902,QC#25190,QC#25141
        if (!isLine) {
            // check PO Status
            if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT) && !bizMsg.poNum_HT.getValue().equals(bizMsg.poNum.getValue())) {
                if (!checkPoSts(bizMsg)) {
                    return false;
                }
            }
        }
        // START 2020/02/17 [QC#53413, MOD]
        //S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getMdseCatgRecord(bizMsg, apInvCatgCd, defCoa, isLine);
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getMdseCatgRecord(bizMsg, apInvCatgCd, defCoa, isLine, true);
        // END   2020/02/17 [QC#53413, MOD]
        if (ssmResult == null || !ssmResult.isCodeNormal()) {
            return true;
        }
        // END QC#25902,QC#25190,QC#25141
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int aIndex = bizMsg.A.getValidCount();
        // START 2019/01/29 [QC#30056, ADD]
        int lineNum = Integer.parseInt(bizMsg.A.no(aIndex - 1).xxDtlLineNum_A1.getValue());
        // END   2019/01/29 [QC#30056, ADD]
        int validCount = bizMsg.A.getValidCount() + resultList.size();
        if (validCount > bizMsg.A.length()) {
            bizMsg.A.setValidCount(bizMsg.A.length());
            bizMsg.setMessageInfo(NFBM0001W, new String[] {Long.toString(bizMsg.A.length()), Long.toString(validCount) });
        } else {
            bizMsg.A.setValidCount(validCount);
        }

        // Get records for [Line Type] pulldown values.
        List apLineTpPulldownList = NFBL2040CommonLogic.getApLineTpPulldownList();

        BigDecimal lineTotAmt = BigDecimal.ZERO;
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) resultList.get(i);
            if (i == 0) {
                // Hidden Value
                setHiddenValueInfo(bizMsg, map);
                // HEADER Tab
                modifyHeadersTabInfo(bizMsg, map);
            }
            // LINES Tab
            // START 2019/01/29 [QC#30056, MOD]
//            setLinesTabDetailInfo(bizMsg, map, i + aIndex, apLineTpPulldownList);
            setLinesTabDetailInfo(bizMsg, map, i + aIndex, apLineTpPulldownList, lineNum++);
            // END   2019/01/29 [QC#30056, MOD]
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i + aIndex).apVndInvSqNum_A1, bizMsg.apVndInvSqNum);
            // START 2020/02/17 [QC#53413, ADD]
            int maxInvSqNum = 0;
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                int newInvSqNum = Integer.valueOf(bizMsg.A.no(j).apVndInvSqNum_A1.getValue(), 10);
                if (maxInvSqNum < newInvSqNum) {
                    maxInvSqNum = newInvSqNum;
                }
            }
            if (maxInvSqNum == 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i + aIndex).apVndInvSqNum_A1, AP_VND_INV_SQ_NUM_00);
            } else {
                maxInvSqNum = maxInvSqNum + 1;
                String strNewInvSqNum = String.format(AP_VND_INV_SQ_NUMFORMAT, maxInvSqNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apVndInvSqNum_A1, strNewInvSqNum);
            }

            // END   2020/02/17 [QC#53413, ADD]

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i + aIndex).xxLineTpCd_A1, XX_LINE_TP_CD_VALID); // valid line

            lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i + aIndex).xxInvAmt_A1.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, lineTotAmt);

        EZDDebugOutput.println(5, "addLineRecord============================END", null);
        return true;
    }

    private static void modifyHeadersTabInfo(NFBL2040CMsg bizMsg, Map<String, Object> result) {
        ZYPEZDItemValueSetter.setValue(bizMsg.poNum_HT, (String) result.get(PO_NUM));               // PO#
        ZYPEZDItemValueSetter.setValue(bizMsg.delyOrdNum_DH, (String) result.get(DELY_ORD_NUM));    // Receipt#
        ZYPEZDItemValueSetter.setValue(bizMsg.rwsNum_DH, (String) result.get(RWS_NUM));             // RWS#
    }
    // END  2018/02/23 [QC#23505, ADD]

    // START 2018/03/13 [QC#24274, ADD]
    /**
     * recalcLineAmount
     * @param bizMsg NFBL2040CMsg
     */
    public static void recalcLineAmount(NFBL2040CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (idx < 0 || idx >= bizMsg.A.getValidCount()) return;

        NFBL2040_ACMsg aCMsg = bizMsg.A.no(idx);
        BigDecimal invoicedQty = BigDecimal.ZERO;
        BigDecimal alreadyInvoicedQty = BigDecimal.ZERO;
        BigDecimal poQty = aCMsg.poQty_A1.getValue();
        // START 2020/02/17 [QC#53413, MOD]
        String poNum = null;
        // mod start 2022/02/15 QC#57090
        //if (!ZYPCommonFunc.hasValue(aCMsg.poNum_A1)) {
        //    poNum = bizMsg.poNum_HT.getValue();
        //} else {
        //    poNum = aCMsg.poNum_A1.getValue();
        //}
        if (ZYPCommonFunc.hasValue(aCMsg.poNum_A1)) {
            poNum = aCMsg.poNum_A1.getValue();
        }
        String vndRtrnNum = null;
        if (!ZYPCommonFunc.hasValue(aCMsg.vndRtrnNum_A1)) {
            vndRtrnNum = bizMsg.vndRtrnNum_H.getValue();
        }
        // mod end 2022/02/15 QC#57090
        // END   2020/02/17 [QC#53413, MOD]
        // START 2018/03/22 [QC#20316, ADD]
        String poOrdDtlLineNum = aCMsg.poOrdDtlLineNum_A1.getValue();
        // END   2018/03/22 [QC#20316, ADD]
        // START 2018/08/15 [QC#27029-1, ADD]
        // del start 2022/02/15 QC#57090
        //String vndRtrnNum = bizMsg.vndRtrnNum_H.getValue();
        // del end 2022/02/15 QC#57090
        // END   2018/08/15 [QC#27029-1, ADD]

        if (AP_LINE_TP.ITEM.equals(aCMsg.apLineTpCd_A1.getValue())) {
            // Line Type = 'ITEM'
            if (ZYPCommonFunc.hasValue(poNum)) {
                String mdseCd = aCMsg.mdseCd_A1.getValue();
                if(ZYPConstant.FLG_ON_Y.equals(aCMsg.xxCntnrFlg_A1.getValue())){
                    mdseCd = aCMsg.xxMdseCd_A1.getValue();
                }
                // START 2018/03/22 [QC#20316, MOD]
                // alreadyInvoicedQty = getSumBilledQty(poNum, mdseCd);
                // START 2018/11/20 [QC#28660, MOD]
                // if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
                //     alreadyInvoicedQty = getSumBilledQty(poNum, mdseCd, poOrdDtlLineNum);
                // } else {
                //     alreadyInvoicedQty = getSumBilledQty(poNum, mdseCd);
                // }
                if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
                    alreadyInvoicedQty = getSumBilledQty(bizMsg, poNum, mdseCd, poOrdDtlLineNum);
                } else {
                    alreadyInvoicedQty = getSumBilledQty(bizMsg, poNum, mdseCd);
                }
                // END   2018/11/20 [QC#28660, MOD]
                // END   2018/03/22 [QC#20316, MOD]
                invoicedQty = aCMsg.apBillQty_A1.getValue();

            // START 2018/08/15 [QC#27029-1, ADD]
            } else if (ZYPCommonFunc.hasValue(vndRtrnNum)) {
                String mdseCd = aCMsg.mdseCd_A1.getValue();
                if(ZYPConstant.FLG_ON_Y.equals(aCMsg.xxCntnrFlg_A1.getValue())){
                    mdseCd = aCMsg.xxMdseCd_A1.getValue();
                }
                // START 2018/11/20 [QC#28660, MOD]
                // alreadyInvoicedQty = getSumBilledQtyVndRtrn(vndRtrnNum, mdseCd, poOrdDtlLineNum);
                alreadyInvoicedQty = getSumBilledQtyVndRtrn(bizMsg, vndRtrnNum, mdseCd, poOrdDtlLineNum);
                // EN   2018/11/20 [QC#28660, MOD]
                invoicedQty = aCMsg.apBillQty_A1.getValue();

            // END   2018/08/15 [QC#27029-1, ADD]
            } else {
                // Invoice Type = STANDARD or CREDIT MEMO
                alreadyInvoicedQty = aCMsg.apBillQty_A1.getValue();
                invoicedQty = aCMsg.apBillQty_A1.getValue();
            }

            // Invoiced Qty
            ZYPEZDItemValueSetter.setValue(aCMsg.apBillQty_A1, invoicedQty);
            // Already Invoiced_Qty
            ZYPEZDItemValueSetter.setValue(aCMsg.xxInvQty_A1, alreadyInvoicedQty);

            BigDecimal lineAmt = BigDecimal.ZERO;
            BigDecimal unitPrice = aCMsg.dealGrsUnitPrcAmt_A1.getValue();

            // START 2018/08/15 [QC#27029-1, ADD]
            // if (ZYPCommonFunc.hasValue(poNum)
            //        && ZYPCommonFunc.hasValue(unitPrice) && ZYPCommonFunc.hasValue(invoicedQty)) {
            if ((ZYPCommonFunc.hasValue(poNum) || ZYPCommonFunc.hasValue(vndRtrnNum))
                    && ZYPCommonFunc.hasValue(unitPrice) && ZYPCommonFunc.hasValue(invoicedQty)) {
            // END   2018/08/15 [QC#27029-1, ADD]
                lineAmt = unitPrice.multiply(invoicedQty);
            } else {
                lineAmt = aCMsg.xxInvAmt_A1.getValue();
            }
            // Line Amount
            ZYPEZDItemValueSetter.setValue(aCMsg.xxInvAmt_A1, lineAmt);
        }

        // Line Total
        BigDecimal lineTotAmt = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, lineTotAmt);
    }
    // END   2018/03/13 [QC#24274, ADD]
    // START 2020/07/02 R.Kurahashi [QC#56696,ADD]
    /**
     * recalcLineAmount
     * @param bizMsg NFBL2040CMsg
     * @param int idx
     */
    public static void recalcLineAmount(NFBL2040CMsg bizMsg, int idx) {
        if (idx < 0 || idx >= bizMsg.A.getValidCount()) return;

        NFBL2040_ACMsg aCMsg = bizMsg.A.no(idx);
        BigDecimal invoicedQty = BigDecimal.ZERO;
        BigDecimal alreadyInvoicedQty = BigDecimal.ZERO;
        String poNum = null;
        if (!ZYPCommonFunc.hasValue(aCMsg.poNum_A1)) {
            poNum = bizMsg.poNum_HT.getValue();
        } else {
            poNum = aCMsg.poNum_A1.getValue();
        }
        String poOrdDtlLineNum = aCMsg.poOrdDtlLineNum_A1.getValue();
        // mod start 2022/02/15 QC#57090
        //String vndRtrnNum = bizMsg.vndRtrnNum_H.getValue();
        String vndRtrnNum = null;
        if (!ZYPCommonFunc.hasValue(aCMsg.vndRtrnNum_A1)) {
            vndRtrnNum = bizMsg.vndRtrnNum.getValue();
        } else {
            vndRtrnNum = aCMsg.vndRtrnNum_A1.getValue();
        }
        // mod end 2022/02/15 QC#57090

        if (AP_LINE_TP.ITEM.equals(aCMsg.apLineTpCd_A1.getValue())) {
            // Line Type = 'ITEM'
            if (ZYPCommonFunc.hasValue(poNum)) {
                String mdseCd = aCMsg.mdseCd_A1.getValue();
                if(ZYPConstant.FLG_ON_Y.equals(aCMsg.xxCntnrFlg_A1.getValue())){
                    mdseCd = aCMsg.xxMdseCd_A1.getValue();
                }
                if (ZYPCommonFunc.hasValue(poOrdDtlLineNum)) {
                    alreadyInvoicedQty = getSumBilledQty(bizMsg, poNum, mdseCd, poOrdDtlLineNum);
                } else {
                    alreadyInvoicedQty = getSumBilledQty(bizMsg, poNum, mdseCd);
                }
                invoicedQty = aCMsg.apBillQty_A1.getValue();

            } else if (ZYPCommonFunc.hasValue(vndRtrnNum)) {
                String mdseCd = aCMsg.mdseCd_A1.getValue();
                if(ZYPConstant.FLG_ON_Y.equals(aCMsg.xxCntnrFlg_A1.getValue())){
                    mdseCd = aCMsg.xxMdseCd_A1.getValue();
                }
                alreadyInvoicedQty = getSumBilledQtyVndRtrn(bizMsg, vndRtrnNum, mdseCd, poOrdDtlLineNum);
                invoicedQty = aCMsg.apBillQty_A1.getValue();

            } else {
                alreadyInvoicedQty = aCMsg.apBillQty_A1.getValue();
                invoicedQty = aCMsg.apBillQty_A1.getValue();
            }

            // Invoiced Qty
            ZYPEZDItemValueSetter.setValue(aCMsg.apBillQty_A1, invoicedQty);
            // Already Invoiced_Qty
            ZYPEZDItemValueSetter.setValue(aCMsg.xxInvQty_A1, alreadyInvoicedQty);

            BigDecimal lineAmt = BigDecimal.ZERO;
            BigDecimal unitPrice = aCMsg.dealGrsUnitPrcAmt_A1.getValue();

            if ((ZYPCommonFunc.hasValue(poNum) || ZYPCommonFunc.hasValue(vndRtrnNum))
                    && ZYPCommonFunc.hasValue(unitPrice) && ZYPCommonFunc.hasValue(invoicedQty)) {
                lineAmt = unitPrice.multiply(invoicedQty);
            } else {
                lineAmt = aCMsg.xxInvAmt_A1.getValue();
            }
            // Line Amount
            ZYPEZDItemValueSetter.setValue(aCMsg.xxInvAmt_A1, lineAmt);
        }

        // Line Total
        if (idx == bizMsg.A.getValidCount() - 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotPrcAmt_F, getLineTotal(bizMsg));
        }
    }
    // START 2020/07/16 R.Kurahashi [QC#56696-1,DEL]
    ///**
    // * setInvAmt
    // * @param bizMsg NFBL2040CMsg
    // */
    //public static void setInvAmt(NFBL2040CMsg bizMsg) {
    //    ZYPEZDItemValueSetter.setValue(bizMsg.invAmt, getLineTotal(bizMsg));
    //}
    // END 2020/07/16 R.Kurahashi [QC#56696-1,DEL]

    private static BigDecimal getLineTotal(NFBL2040CMsg bizMsg) {
        BigDecimal lineTotAmt = BigDecimal.ZERO;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            lineTotAmt = lineTotAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
        }
        return lineTotAmt;
    }
    // END 2020/07/02 R.Kurahashi [QC#56696,ADD]

    // START 2018/03/22 [QC#20316, ADD]
    // START 2018/11/20 [QC#28660, MOD]
    /**
     * getSumBilledQty
     * @param bizMsg NFBL2040CMsg
     * @param poOrdNum String
     * @param mdseCd String
     * @param poOrdDtlLineNum String
     * @return
     */
    public static BigDecimal getSumBilledQty(NFBL2040CMsg bizMsg, String poOrdNum, String mdseCd, String poOrdDtlLineNum) {
        BigDecimal sumBilledQty = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult= NFBL2040Query.getInstance().getSumBilledQtyExcluveCanInv(bizMsg, poOrdNum, poOrdDtlLineNum, mdseCd);
        List<Map<String, Object>>resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resultList.size() > 0) {
            Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
            sumBilledQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
        }
        return sumBilledQty;

    }
    // END   2018/11/20 [QC#28660, MOD]
    // END   2018/03/22 [QC#20316, ADD]

    /**
     * ADD QC#25902,QC#25190,QC#25141
     * checkPoSts
     * @param bizMsg NFBL2040CMsg
     * @return boolean (false = error)
     */
    public static boolean checkPoLineSts(NFBL2040CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPoSts(bizMsg, true);
        boolean isResult = true;
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            String poLineStsCd = "";
            String poStsCd = "";
            String poOrdDtlLineNum = "";
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(i);
                poLineStsCd = (String) rtn.get("PO_LINE_STS_CD");
                poStsCd = (String) rtn.get("PO_STS_CD");
                poOrdDtlLineNum = (String) rtn.get("PO_ORD_DTL_LINE_NUM");

                if (PO_LINE_STS.OPEN.equals(poLineStsCd)) {
                    if (PO_STS.VALIDATED.equals(poStsCd) || PO_STS.PO_CONFIRMED.equals(poStsCd)) {
                        continue;
                    }
                } else if (PO_LINE_STS.OPEN_FOR_INVOICE.equals(poLineStsCd)) {
                    continue;
                } else if (PO_LINE_STS.OPEN_FOR_RECEIPT.equals(poLineStsCd)) {
                    continue;
                } else {
                    for (int c = 0; c < bizMsg.A.getValidCount(); c++) {
                        if (poOrdDtlLineNum.equals(bizMsg.A.no(c).poOrdDtlLineNum_A1.getValue())) {
                            bizMsg.A.no(c).xxChkBox_A1.setErrorInfo(1, NFBM0260E);
                            isResult = false;
                        }
                    }
                }
            }
        } else {
            bizMsg.setMessageInfo(NFBM0069E);
            return false;
        }
        return isResult;
    }

    // START 2018/06/20 [QC#26169, ADD]
    public static boolean hasVariance(NFBL2040_ACMsg aCMsg) {
        if (!AP_LINE_TP.ITEM.equals(aCMsg.apLineTpCd_A1.getValue())) {
            return false;
        } 
        BigDecimal acInvJrnlCostAmt = NFBCommonBusiness.chkNull(aCMsg.xxInvAmt_A1.getValue());
        BigDecimal apBillQty = NFBCommonBusiness.chkNull(aCMsg.apBillQty_A1.getValue());
        BigDecimal dealGrsUnitPrcAmt = NFBCommonBusiness.chkNull(aCMsg.dealGrsUnitPrcAmt_A1.getValue());
        BigDecimal dealGrsPrcAmt = NFBCommonBusiness.chkNull(dealGrsUnitPrcAmt.multiply(apBillQty));

        // START 2018/08/15 [QC#27029-1, MOD]
        // BigDecimal origDealGrsUnitPrcAmt = aCMsg.entDealNetUnitPrcAmt_A2.getValue();
        // if (ZYPCommonFunc.hasValue(origDealGrsUnitPrcAmt)) {
        //     dealGrsPrcAmt = NFBCommonBusiness.chkNull(origDealGrsUnitPrcAmt.multiply(apBillQty));
        // }
        if (isEffectiveStkInPrice(aCMsg)) {
            BigDecimal origDealGrsUnitPrcAmt = aCMsg.entDealNetUnitPrcAmt_A2.getValue();
            dealGrsPrcAmt = NFBCommonBusiness.chkNull(origDealGrsUnitPrcAmt.multiply(apBillQty));

        } else if (ZYPCommonFunc.hasValue(aCMsg.entDealNetUnitPrcAmt_A3)) {
            BigDecimal origDealGrsUnitPrcAmt = aCMsg.entDealNetUnitPrcAmt_A3.getValue();
            dealGrsPrcAmt = NFBCommonBusiness.chkNull(origDealGrsUnitPrcAmt.multiply(apBillQty));
        }
        // END   2018/08/15 [QC#27029-1, MOD]

        return BigDecimal.ZERO.compareTo(acInvJrnlCostAmt.subtract(dealGrsPrcAmt)) != 0;
    }
    // END   2018/06/20 [QC#26169, ADD]

    /**
     * checkHoldForVendorReturn
     * QC#27029 Add method.
     * @param bizMsg NFBL2040CMsg
     * @param userId String
     * 
     */
    public static void checkHoldForVendorReturn(NFBL2040CMsg bizMsg, String userId) {
        int validCountH = bizMsg.H.getValidCount();
        int hldRecCnt = 0;
        List pmtHldRelRsnPulldownList = getPmtHldRelRsnPulldownList();
        List<List> checkList = new ArrayList<List>();
        List<NFBL2040_HCMsg> targetRecords = new ArrayList<NFBL2040_HCMsg>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            List<String> elementList = new ArrayList<String>();
            elementList.add(bizMsg.H.no(i).pmtHldCd_H1.getValue());
            elementList.add(bizMsg.H.no(i).pmtHldRsnCd_H1.getValue());
            // START 2019/02/19 [QC#30418, ADD]
            elementList.add(bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
            // END   2019/02/19 [QC#30418, ADD]
            if (!checkList.contains(elementList)) {
                checkList.add(elementList);
                targetRecords.add(bizMsg.H.no(i));
            }
        }

        int j = 0;
        for (j = 0; j < bizMsg.H.getValidCount(); j++) {
            if (j < targetRecords.size()) {
                EZDMsg.copy(targetRecords.get(j), null, bizMsg.H.no(j), null);
            } else {
                bizMsg.H.no(j).clear();
            }
        }
        bizMsg.H.setValidCount(targetRecords.size());
        validCountH = bizMsg.H.getValidCount();
        // Qyt Hold
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                continue;
            }
            // add start 2022/02/15 QC#57090
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).vndRtrnNum_A1)) {
                continue;
            }
            // add end 2022/02/15 QC#57090

            // START 2018/11/21 [QC#28660, MOD]
            // BigDecimal invoicedQty = NFBL2040Query.getInstance().getInvoicedQtyOfVendorReturn(bizMsg.vndRtrnNum.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue());
            // START 2019/03/19 [QC#30829, MOD]
            // BigDecimal invoicedQty = getSumBilledQtyVndRtrn(bizMsg, bizMsg.vndRtrnNum.getValue(), bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue());
            String apVndInvNum = bizMsg.apVndInvNum.getValue();
            if (isInvoicePKModified(bizMsg)) {
                apVndInvNum = bizMsg.apVndInvNum_OT.getValue();
            }
            // mod start 2022/02/15 QC#57090
            //BigDecimal invoicedQty = getSumBilledQtyVndRtrn(bizMsg, bizMsg.vndRtrnNum.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue(), bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue(), apVndInvNum);
            BigDecimal invoicedQty = getSumBilledQtyVndRtrn(bizMsg, bizMsg.A.no(i).vndRtrnNum_A1.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue(), bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue(), apVndInvNum);
            // mod end 2022/02/15 QC#57090
            // END   2019/03/19 [QC#30829, MOD]
            // END   2018/11/21 [QC#28660, MOD]
            BigDecimal invoiceQty = bizMsg.A.no(i).apBillQty_A1.getValue();
            // BigDecimal ordQty = bizMsg.A.no(i).poQty_A1.getValue();
            BigDecimal shipQty = bizMsg.A.no(i).invRcvQty_A1.getValue();

            // Compare Received Quantity to Invoice(Credit memo is
            // negative)
            // Quantity.(PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)
            if (shipQty.compareTo(invoiceQty.add(invoicedQty)) > 0) {
                // START 2018/11/21 [QC#28660, MOD]
                // String xxChkBox_H1 = ZYPConstant.FLG_ON_Y; // Check_box
                String xxChkBox_H1 = ZYPConstant.FLG_OFF_N; // Check_box
                // END   2018/11/21 [QC#28660, MOD]
                if (existsCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_010, PMT_HLD_RSN_CD_013) && !isHoldCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                    if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_010, PMT_HLD_RSN_CD_013, xxChkBox_H1)) {
                        validCountH++;
                    }
                } else {
                    hldRecCnt++;
                    xxChkBox_H1 = ZYPConstant.FLG_OFF_N;
                }
                if (addRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_010, PMT_HLD_RSN_CD_013, xxChkBox_H1)) {
                    validCountH++;
                }
            // START 2018/11/21 [QC#28660, ADD]
            } else {
                String xxChkBox_H1 = ZYPConstant.FLG_ON_Y;
                if (existsCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_010, PMT_HLD_RSN_CD_013) && isHoldCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_010, PMT_HLD_RSN_CD_013)) {
                    updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_010, PMT_HLD_RSN_CD_013, xxChkBox_H1);
                }
            }
            // END   2018/11/21 [QC#28660, ADD]
        }

        // Amount Hold
        BigDecimal intTolRat = getIntTolRate(bizMsg);
        int aftDeclPntDigitNum = getAftDeclPntDigitNum();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (AP_LINE_TP.FREIGHT.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue()) //
                    || AP_LINE_TP.TAX.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                continue;
            }
            // START 2020/06/08 [QC#57007, MOD]
            //if (isToleranceHold(bizMsg.A.no(i), intTolRat, aftDeclPntDigitNum)) {
            if (isToleranceHoldForCreditMemoPO(bizMsg.A.no(i), intTolRat, aftDeclPntDigitNum)) {
            // END   2020/06/08 [QC#57007, MOD]
                String apVndInvLineNum = bizMsg.A.no(i).xxDtlLineNum_A1.getValue();
                if (addRecordToH(bizMsg, getPmtHldRelRsnPulldownList(), userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, ZYPConstant.FLG_OFF_N)) {
                    validCountH++;
                }
            // START 2018/11/21 [QC#28660, ADD]
            } else if (existsCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021) 
                            && isHoldCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021)) {
                // auto release
                updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, ZYPConstant.FLG_ON_Y);
            }
            // END   2018/11/21 [QC#28660, ADD]
        }

        if (hldRecCnt == 0 && NFBCommonBusiness.chkNull(bizMsg.entPoDtlDealNetAmt_TO.getValue()).compareTo(NFBCommonBusiness.chkNull(bizMsg.invAmt.getValue())) != 0) {
            // no process.
        } else {
            String xxChkBox_H1 = ZYPConstant.FLG_ON_Y; // Check box
 
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                String apVndInvLineNum = bizMsg.A.no(i).xxDtlLineNum_A1.getValue();
                if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021) && !isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021)) {
                    if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, xxChkBox_H1)) {
                        validCountH++;
                    } else {
                        // Success
                    }
                }
            }
        }
        bizMsg.H.setValidCount(validCountH);
        if (hldRecCnt > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum_HH, bizMsg.apVndInvNum.getValue()); // Invoice
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.invAmt_HH, bizMsg.invAmt.getValue()); // Invoice
            // Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.apVndInvNum_HH.clear(); // Invoice Number
            bizMsg.invAmt_HH.clear(); // Invoice Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_OFF_N);
        }
    }
    
    
    /**
     * checkHoldForVendorReturn
     * QC#27029-1 Add method.
     * @param bizMsg NFBL2040CMsg
     * @param userId String
     * 
     */
    public static void checkHoldForCreditMemoPo(NFBL2040CMsg bizMsg, String userId) {
        int validCountH = bizMsg.H.getValidCount();
        int hldRecCnt = 0;
        List pmtHldRelRsnPulldownList = getPmtHldRelRsnPulldownList();
        List<List> checkList = new ArrayList<List>();
        List<NFBL2040_HCMsg> targetRecords = new ArrayList<NFBL2040_HCMsg>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            List<String> elementList = new ArrayList<String>();
            elementList.add(bizMsg.H.no(i).pmtHldCd_H1.getValue());
            elementList.add(bizMsg.H.no(i).pmtHldRsnCd_H1.getValue());
            // START 2019/02/19 [QC#30418, ADD]
            elementList.add(bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
            // END   2019/02/19 [QC#30418, ADD]
            if (!checkList.contains(elementList)) {
                checkList.add(elementList);
                targetRecords.add(bizMsg.H.no(i));
            }
        }

        int j = 0;
        for (j = 0; j < bizMsg.H.getValidCount(); j++) {
            if (j < targetRecords.size()) {
                EZDMsg.copy(targetRecords.get(j), null, bizMsg.H.no(j), null);
            } else {
                bizMsg.H.no(j).clear();
            }
        }
        bizMsg.H.setValidCount(targetRecords.size());
        validCountH = bizMsg.H.getValidCount();

        // Amount Hold
        BigDecimal intTolRat = getIntTolRate(bizMsg);
        int aftDeclPntDigitNum = getAftDeclPntDigitNum();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (AP_LINE_TP.FREIGHT.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue()) //
                    || AP_LINE_TP.TAX.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                continue;
            }
            if (isToleranceHoldForCreditMemoPO(bizMsg.A.no(i), intTolRat, aftDeclPntDigitNum)) {
                String apVndInvLineNum = bizMsg.A.no(i).xxDtlLineNum_A1.getValue();
                if (addRecordToH(bizMsg, getPmtHldRelRsnPulldownList(), userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, ZYPConstant.FLG_OFF_N)) {
                    validCountH++;
                }
            // START 2018/11/21 [QC#28660, ADD]
            } else if (existsCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021) 
                            && isHoldCmInvPmtHld(bizMsg, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021)) {
                // auto release
                updateAutoReleasedRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, checkList, bizMsg.A.no(i).xxDtlLineNum_A1.getValue(), PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, ZYPConstant.FLG_ON_Y);
            }
            // END   2018/11/21 [QC#28660, ADD]
        }

        if (hldRecCnt == 0 && NFBCommonBusiness.chkNull(bizMsg.entPoDtlDealNetAmt_TO.getValue()).compareTo(NFBCommonBusiness.chkNull(bizMsg.invAmt.getValue())) != 0) {
            // no process.
        } else {
            String xxChkBox_H1 = ZYPConstant.FLG_ON_Y; // Check box
 
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                String apVndInvLineNum = bizMsg.A.no(i).xxDtlLineNum_A1.getValue();
                if (existsCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021) && !isHoldCmInvPmtHld(bizMsg, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021)) {
                    if (addCmInvPmtHldRecordToH(bizMsg, pmtHldRelRsnPulldownList, userId, validCountH, checkList, apVndInvLineNum, PMT_HLD_CD_020, PMT_HLD_RSN_CD_021, xxChkBox_H1)) {
                        validCountH++;
                    } else {
                        // Success
                    }
                }
            }
        }

        bizMsg.H.setValidCount(validCountH);
        if (hldRecCnt > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.apVndInvNum_HH, bizMsg.apVndInvNum.getValue()); // Invoice
            // Number
            ZYPEZDItemValueSetter.setValue(bizMsg.invAmt_HH, bizMsg.invAmt.getValue()); // Invoice
            // Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_ON_Y);
        } else {
            bizMsg.apVndInvNum_HH.clear(); // Invoice Number
            bizMsg.invAmt_HH.clear(); // Invoice Amount
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_OFF_N);
        }
    }

    // START 2018/08/15 [QC#27029-1, ADD]
    public static boolean isEffectiveStkInPrice(NFBL2040_ACMsg aBizMsg) {
        if (ZYPCommonFunc.hasValue(aBizMsg.poNum_A1) && ZYPCommonFunc.hasValue(aBizMsg.entDealNetUnitPrcAmt_A2)) {
            return true;
        }
        return false;
    }

    // START  2018/11/20 [QC#28660, MOD]
    /**
     * getSumBilledQtyVndRtrn
     * @param vndRtrnNum String
     * @param mdseCd String
     * @param poOrdDtlLineNum String
     * @return
     */
    public static BigDecimal getSumBilledQtyVndRtrn(NFBL2040CMsg bizMsg, String vndRtrnNum, String mdseCd, String poOrdDtlLineNum) {
        BigDecimal sumBilledQty = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult= NFBL2040Query.getInstance().getSumBilledQtyExcluveCanVndRtrn(bizMsg, vndRtrnNum, poOrdDtlLineNum, mdseCd);
        List<Map<String, Object>>resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resultList.size() > 0) {
            Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
            sumBilledQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
        }
        return sumBilledQty;

    }
    // END   2018/11/20 [QC#28660, MOD]
    // END   2018/08/15 [QC#27029-1, ADD]

    // START 2018/09/04 [QC#28039, ADD]
    /**
     * getListCmApInvDtlByPartialKeyForCreditMemo
     * @param poOrdNum String
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getListCmApInvDtlByPartialKeyForCreditMemo(String poOrdNum) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getListCmApInvDtlByPartialKeyForCreditMemo(poOrdNum);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            return resultList;
        }
        return new ArrayList<Map<String, Object>>();
    }
    // END   2018/09/04 [QC#28039, ADD]

    // START 2019/02/21 [QC#30420, ADD]
    public static void renumberInvoiceDtlLineNumber(NFBL2040CMsg bizMsg) {
        int itemInvDtlLineNum = getLastItemInvoiceDtlLineNumber(bizMsg) + 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxDtlLineNum_A1, 
                    String.format(AP_VND_INV_LINE_NUM_FORMAT, itemInvDtlLineNum));
            itemInvDtlLineNum++;
        }
    }

    public static int getLastItemInvoiceDtlLineNumber(NFBL2040CMsg bizMsg) {
        return getItemInvoiceDtlLineNumber(bizMsg, true, false);
    }

    public static int getLastInvoiceDtlLineNumber(NFBL2040CMsg bizMsg) {
        return getItemInvoiceDtlLineNumber(bizMsg, false, false);
    }

    private static int getItemInvoiceDtlLineNumber(NFBL2040CMsg bizMsg, boolean itemLineOnly, boolean excludeNotInvoicedLine) {
        int lastLineNum = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (itemLineOnly && !AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                continue;
            }

            BigDecimal lineAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue());
            BigDecimal invQty = NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue());
            if (excludeNotInvoicedLine
                    && AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())
                    && BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
                continue;
            }

            String xxDtlLineNum = bizMsg.A.no(i).xxDtlLineNum_A1.getValue();
            if (Integer.parseInt(xxDtlLineNum) > lastLineNum) {
                lastLineNum = Integer.parseInt(xxDtlLineNum);
            }
        }
        return lastLineNum;
    }
    // END   2019/02/21 [QC#30420, ADD]

    // START  2018/11/20 [QC#28660, MOD]
    /**
     * getSumBilledQtyVndRtrn
     * @param vndRtrnNum String
     * @param mdseCd String
     * @param poOrdDtlLineNum String
     * @param apVndInvNum String
     * @return
     */
    public static BigDecimal getSumBilledQtyVndRtrn(NFBL2040CMsg bizMsg, String vndRtrnNum, String mdseCd, String poOrdDtlLineNum, String apVndInvNum) {
        BigDecimal sumBilledQty = BigDecimal.ZERO;
        S21SsmEZDResult ssmResult= NFBL2040Query.getInstance().getSumBilledQtyVndRtrn(bizMsg, vndRtrnNum, poOrdDtlLineNum, mdseCd, apVndInvNum);
        List<Map<String, Object>>resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resultList.size() > 0) {
            Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
            sumBilledQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
        }
        return sumBilledQty;

    }
    // END   2018/11/20 [QC#28660, MOD]

    // START 2020/02/17 [QC#53413, ADD]
    // mod start 2022/02/15 QC#57090
    //public static void delMultiPoData(EZDCMsg cMsg) {
    public static void clearMultiPoOrMultiVndRtrnData(EZDCMsg cMsg) {
    // mod end 2022/02/15 QC#57090
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        List<String> poNumList = new ArrayList<String>();
        // add start 2022/02/15 QC#57090
        List<String> vndRtrnNumList = new ArrayList<String>();
        // add end 2022/02/15 QC#57090
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                String poNum = bizMsg.A.no(i).poNum_A1.getValue();
                if (!poNumList.contains(poNum)) {
                    poNumList.add(poNum);
                }
            }
            // add start 2022/02/15 QC#57090
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).vndRtrnNum_A1)) {
                String vndRtrnNum = bizMsg.A.no(i).vndRtrnNum_A1.getValue();
                if (!vndRtrnNumList.contains(vndRtrnNum)) {
                    vndRtrnNumList.add(vndRtrnNum);
                }
            }
            if (poNumList.size() > 1 || vndRtrnNumList.size() > 1) {
                bizMsg.poNum_HT.clear();
                bizMsg.poApvlDt.clear();
                bizMsg.entPoDtlDealNetAmt_TO.clear();
                bizMsg.delyOrdNum_DH.clear();
                bizMsg.rwsNum_DH.clear();
                bizMsg.vndRtrnNum_H.clear();
                ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_MP, ZYPConstant.FLG_ON_Y);
                break;
            }
            // add end 2022/02/15 QC#57090
        }
        // del start 2022/02/15 QC#57090
        //if (poNumList.size() > 1) {
        //    bizMsg.poNum_HT.clear();
        //    bizMsg.poApvlDt.clear();
        //    bizMsg.entPoDtlDealNetAmt_TO.clear();
        //    bizMsg.delyOrdNum_DH.clear();
        //    bizMsg.rwsNum_DH.clear();
        //    ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_MP, ZYPConstant.FLG_ON_Y);
        //}
        // del end 2022/02/15 QC#57090
    }

    public static boolean chkMtPo (NFBL2040CMsg bizMsg, int cnt) {
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(cnt).poNum_A1)){
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(cnt).xxLineTpCd_A1)) {
                if (XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(cnt).xxLineTpCd_A1.getValue())) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }
    // END   2020/02/17 [QC#53413, ADD]

    // START 2021/04/22 R.Azucena [QC#56829, ADD]
    /**
     * getInvQtyOnHoldCntVndRtrn
     * @param bizMsg NFBL2040CMsg
     * @param vndRtrnNum String
     * @param mdseCd String
     * @param poOrdDtlLineNum String
     * @param apVndInvNum String
     * @return BigDecimal
     */
    public static BigDecimal getInvQtyOnHoldCntVndRtrn(NFBL2040CMsg bizMsg, String vndRtrnNum, String mdseCd, String poOrdDtlLineNum, String apVndInvNum) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getInvQtyOnHoldCntVndRtrn(bizMsg, vndRtrnNum, poOrdDtlLineNum, mdseCd, apVndInvNum);
        BigDecimal invQtyOnHoldCnt = (BigDecimal) ssmResult.getResultObject();
        return invQtyOnHoldCnt;
    }

    /**
     * getInvQtyOnHoldCnt
     * @param bizMsg NFBL2040CMsg
     * @param poOrdNum String
     * @param mdseCd String
     * @param poOrdDtlLineNum String
     * @param apVndInvNum String
     * @return BigDecimal
     */
    public static BigDecimal getInvQtyOnHoldCnt(NFBL2040CMsg bizMsg, String poOrdNum, String mdseCd, String poOrdDtlLineNum, String apVndInvNum) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getInvQtyOnHoldCnt(bizMsg, poOrdNum, poOrdDtlLineNum, mdseCd, apVndInvNum);
        BigDecimal invQtyOnHoldCnt = (BigDecimal) ssmResult.getResultObject();
        return invQtyOnHoldCnt;
    }
    // END 2021/04/22 R.Azucena [QC#56829, ADD]

}
