/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2040;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL2040.common.NFBL2040CommonLogic;
import business.blap.NFBL2040.constant.NFBL2040Constant;
import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;
import business.db.AJE_CM_INTFCTMsg;
import business.db.AP_INV_PMTTMsg;
import business.db.CCYTMsg;
import business.db.CM_ACRL_WRITE_OFFTMsg;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_DTLTMsgArray;
import business.db.CM_AP_INV_HDRTMsg;
import business.db.CM_AP_INV_HDRTMsgArray;
import business.db.CM_INV_ACCT_DISTTMsg;
import business.db.CM_INV_ACCT_DISTTMsgArray;
import business.db.CM_INV_PMT_HLDTMsg;
import business.db.CM_INV_PMT_HLDTMsgArray;
import business.db.CM_STK_INTMsg;
import business.db.DOC_MGT_CATGTMsg;
import business.db.JRNL_ENTRYTMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.PRNT_VNDTMsg;
import business.db.PRNT_VNDTMsgArray;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.VND_PMT_TERMTMsg;
import business.db.VND_PMT_TERMTMsgArray;
import business.db.VND_RTRNTMsg;
import business.parts.NFBCommonBusiness;
import business.parts.NFZC505001PMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_HLD;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownConst;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/07/11   Hitachi         Y.Tsuchimoto    Update          QC#11502
 * 2016/07/20   Hitachi         T.Tsuchida      Update          QC#12053
 * 2016/07/27   Hitachi         T.Tsuchida      Update          QC#12221
 * 2016/07/28   Hitachi         T.Tsuchida      Update          QC#12556
 * 2016/07/29   Hitachi         T.Tsuchida      Update          QC#12210
 * 2016/08/01   Hitachi         T.Tsuchida      Update          QC#12712
 * 2016/08/03   Hitachi         K.Kojima        Update          QC#12874
 * 2016/08/05   Hitachi         T.Tsuchida      Update          QC#12935
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#12971
 * 2016/08/08   Hitachi         T.Tsuchida      Update          QC#12115
 * 2016/08/08   Hitachi         Y.Tsuchimoto    Update          QC#12648
 * 2016/08/09   Hitachi         Y.Tsuchimoto    Update          QC#12195
 * 2016/08/09   Hitachi         Y.Tsuchimoto    Update          QC#12198
 * 2016/08/10   Hitachi         Y.Tsuchimoto    Update          QC#12948
 * 2016/08/24   Hitachi         Y.Tsuchimoto    Update          QC#12549
 * 2016/08/25   Hitachi         Y.Tsuchimoto    Update          QC#12979
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Update          QC#12043
 * 2016/09/15   Hitachi         Y.Tsuchimoto    Update          QC#13333
 * 2016/09/16   Hitachi         Y.Tsuchimoto    Update          QC#13245
 * 2016/09/20   Hitachi         T.Tsuchida      Update          QC#12355
 * 2016/09/20   Hitachi         T.Tsuchida      Update          QC#14678
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13550
 * 2016/09/26   Fujitsu         W.Honda         Update          Unit Test#201
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13608
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/04   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/04   Hitachi         Y.Tsuchimoto    Update          QC#13960
 * 2016/10/05   Hitachi         Y.Tsuchimoto    Update          QC#14130
 * 2016/10/05   Hitachi         T.Tsuchida      Update          QC#14164
 * 2016/10/06   Hitachi         Y.Tsuchimoto    Update          QC#15036
 * 2016/10/07   Hitachi         T.Tsuchida      Update          QC#12053
 * 2016/10/07   Hitachi         Y.Tsuchimoto    Update          QC#15091
 * 2016/10/11   Hitachi         T.Tsuchida      Update          QC#15087
 * 2016/10/25   Fujitsu         W.Honda         Update          QC#15550
 * 2016/10/26   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2016/11/14   Hitachi         Y.Tsuchimoto    Update          QC#15773
 * 2016/11/15   Hitachi         K.Kasai         Update          QC#15896
 * 2016/11/29   Hitachi         Y.Tsuchimoto    Update          QC#16268
 * 2016/12/14   Hitachi         K.Kasai         Update          QC#15995
 * 2016/12/20   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/01/12   Hitachi         E.Kameishi      Update          QC#16950
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16948
 * 2017/01/24   Hitachi         E.Kameishi      Update          QC#16948
 * 2017/01/25   Hitachi         E.Kameishi      Update          QC#17090
 * 2017/02/06   Hitachi         E.Kameishi      Update          QC#12988
 * 2017/07/05   CITS            M.Naito         Update          QC#19261
 * 2017/10/20   CITS            T.Tokutomi      Update          QC#21653
 * 2017/11/14   CITS            K.Ogino         Update          QC#22137
 * 2017/12/15   CITS            T.Hakodate      Update          QC#23038
 * 2017/12/20   CITS            T.Tokutomi      Update          QC#14858-Sol#060
 * 2017/12/21   Hitachi         J.Kim           Update          QC#23039
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2017/12/26   Hitachi         Y.Takeno        Update          QC#23022
 * 2017/12/27   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/01/29   CITS            K.Kameoka       Update          QC#18602-Sol#102
 * 2018/02/26   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/06   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/07   Hitachi         Y.Takeno        Update          QC#24079
 * 2018/03/08   Hitachi         Y.Takeno        Update          QC#24275
 * 2018/03/16   Hitachi         Y.Takeno        Update          QC#24089
 * 2018/03/22   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/03/28   Hitachi         Y.Takeno        Update          QC#24277
 * 2018/04/04   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/04/10   Hitachi         Y.Takeno        Update          QC#24277
 * 2018/04/10   CITS            K.Ogino         Update          QC#24985
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/06/19   Hitachi         Y.Takeno        Update          QC#26169
 * 2018/06/20   Hitachi         Y.Takeno        Update          QC#26169
 * 2018/07/12   CITS            T.Tokutomi      Update          QC#27025
 * 2018/07/17   CITS            S.Katsuma       Update          QC#27078
 * 2018/07/26   CITS            T.Tokutomi      Update          QC#27029
 * 2018/07/30   Hitachi         Y.Takeno        Update          QC#27025-1
 * 2018/08/01   Hitachi         Y.Takeno        Update          QC#27025-1
 * 2018/08/02   Hitachi         Y.Takeno        Update          QC#27025-1
 * 2018/08/09   CITS            T.Tokutomi      Update          QC#27029-1
 * 2018/08/13   Hitachi         Y.Takeno        Update          QC#27025-2
 * 2018/08/14   Hitachi         Y.Takeno        Update          QC#27029-1
 * 2018/08/15   Hitachi         Y.Takeno        Update          QC#27029-1
 * 2018/08/21   Hitachi         Y.Takeno        Update          QC#27873
 * 2018/08/23   Hitachi         Y.Takeno        Update          QC#27247-1
 * 2018/08/26   Hitachi         Y.Takeno        Update          QC#27886
 * 2018/09/04   Hitachi         Y.Takeno        Update          QC#28039
 * 2018/09/27   Hitachi         Y.Takeno        Update          QC#28099
 * 2018/10/01   Hitachi         Y.Takeno        Update          QC#28099-1
 * 2018/10/09   Hitachi         Y.Takeno        Update          QC#28099-1
 * 2018/10/10   Fujitsu         S.Ohki          Update          QC#28521
 * 2018/10/29   Hitachi         Y.Takeno        Update          QC#28988
 * 2018/11/05   Hitachi         Y.Takeno        Update          QC#28982
 * 2018/11/20   Hitachi         J.Kim           Update          QC#29247
 * 2018/11/20   Hitachi         Y.Takeno        Update          QC#28660
 * 2018/11/26   Hitachi         Y.Takeno        Update          QC#28660
 * 2018/11/29   Hitachi         Y.Takeno        Update          QC#28904
 * 2019/01/29   Fujitsu         S.Ohki          Update          QC#30056
 * 2019/02/07   Hitachi         Y.Takeno        Update          QC#30136
 * 2019/02/19   Fujitsu         S.Ohki          Update          QC#30418
 * 2019/02/21   Hitachi         Y.Takeno        Update          QC#30420
 * 2019/03/19   Hitachi         Y.Takeno        Update          QC#30829
 * 2019/04/08   Hitachi         Y.Takeno        Update          QC#31071
 * 2019/07/18   Hitachi         Y.Takeno        Update          QC#51675
 * 2019/09/04   Hitachi         Y.Takeno        Update          QC#52876
 * 2020/02/06   Fujitsu         M.Ishii         Update          QC#55530
 * 2020/02/06   Fujitsu         M.Ishii         Update          QC#55694
 * 2020/03/16   Fujitsu         H.Mizukami      Update          QC#55993
 * 2020/03/23   Fujitsu         H.Ikeda         Update          QC#53413
 * 2020/07/02   CITS            R.Kurahashi     Update          QC#56696
 * 2020/07/10   CITS            R.Kurahashi     Update          QC#57322
 * 2020/07/16   CITS            R.Kurahashi     Update          QC#56696-1
 * 2021/10/15   CITS            J.Evangelista   Update          QC#59272
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * 2022/05/27   CITS            D.Mamaril       Update          QC#60084
 * 2022/06/28   CITS            D.Mamaril       Update          QC#60084-1
 * 2023/01/17   Hitachi         S.Nakatani      Update          QC#60812
 * </pre>
 */
public class NFBL2040BL06 extends S21BusinessHandler implements NFBL2040Constant {

    /** Global Company Code */
    private final String GLBL_CMPY_CD = getGlobalCompanyCode();

    /** User ID */
    private final String USER_ID = getContextUserInfo().getUserId();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            // +++++ [START] : Scrn00
            if ("NFBL2040Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_Release".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Release(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_InvoiceCancel".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_InvoiceCancel(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_Hold".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Hold(cMsg, sMsg);
                // START 2017/12/27 [QC#22143, ADD]. 2018/07/12 Delete [QC#27025]
//            } else if ("NFBL2040Scrn00_CMN_Approve".equals(screenAplID)) {
//                doProcess_NFBL2040Scrn00_CMN_Approve(cMsg, sMsg);
            // END   2017/12/27 [QC#22143, ADD]
            // START 2018/08/23 [QC#27247-1. ADD]
            } else if ("NFBL2040Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else if ("NFBL2040Scrn00_CMN_ColClear".equals(screenAplID)){
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            // END   2018/08/23 [QC#27247-1. ADD]
            // START 2018/11/29 [QC#28904, ADD]
            } else if ("NFBL2040Scrn00_Regenerate_Invoice".equals(screenAplID)) {
                doProcess_NFBL2040Scrn00_Regenerate_Invoice(cMsg, sMsg);
            // END   2018/11/29 [QC#28904, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Scrn01
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

// START 2017/12/28 [QC#22143, ADD]
    /**
     * Method name: doProcess_NFBL2040Scrn00_CMN_Approve
     * <dd>The method explanation: Submit button
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_CMN_Approve(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_CMN_Approve===============================START", this);
        submitInvoice(cMsg, sMsg, true);
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_CMN_Approve===============================END", this);
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_CMN_Submit
     * <dd>The method explanation: Submit button
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_CMN_Submit================================START", this);
        // START 2018/02/26 [QC#23505, MOD]
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        // START 2020/07/02 R.Kurahashi [QC#56696,ADD]
        // OnChange_PmtTerm
        if (ZYPCommonFunc.hasValue(bizMsg.invDt)
                && ZYPCommonFunc.hasValue(bizMsg.vndPmtTermDescTxt)) {
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
        
        // Amount_Calc
        if (bizMsg.A.getValidCount() > 0) {
            for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NFBL2040CommonLogic.recalcLineAmount(bizMsg, i);
            }
        }
        
        // set invAmt
        // START 2020/07/16 R.Kurahashi [QC#56696-1,DEL]
        //NFBL2040CommonLogic.setInvAmt(bizMsg);
        // END 2020/07/16 R.Kurahashi [QC#56696-1,DEL]
        // END 2020/07/02 R.Kurahashi [QC#56696,ADD]
        if (NFBL2040CommonLogic.isCorrectableInvoice(bizMsg) && NFBL2040CommonLogic.isCorrectedInvoice(bizMsg)
                && NFBL2040CommonLogic.isUnsubmittedCorrection(bizMsg)) {
            submitLinkedInvoice(cMsg, sMsg);
        //Start 2023/1/17 S.Naktani [QC#60812, ADD]
        } else if(ACCT_INV_STS.LINKED_TO_COST_CALCULATION.equals(bizMsg.acctInvStsCd.getValue())){
            updateInvNum(cMsg, sMsg);
        //Start 2023/1/17 S.Naktani [QC#60812, ADD]
        } else {
            submitInvoice(cMsg, sMsg, false);
        }
        // END   2018/02/26 [QC#23505, MOD]
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_CMN_Submit================================END", this);
    }
 // END   2017/12/28 [QC#22143, ADD]

    /**
     * Method name: submitInvoice
     * <dd>The method explanation: Submit button
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param approveInvoice boolean
     */
    @SuppressWarnings("unchecked")
    // START 2017/12/28 [QC#22143, MOD]
    // private void doProcess_NFBL2040Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
    private void submitInvoice(EZDCMsg cMsg, EZDSMsg sMsg, boolean approveInvoice) {
    // END   2017/12/28 [QC#22143, MOD]
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2018/03/07 [QC#24079, MOD]
        // if (!ZYPCommonFunc.hasValue(bizMsg.termNetDueDt)) {
        //     NFBL2040CommonLogic.calcTermsDate(bizMsg, sMsg);
        // }
        NFBL2040CommonLogic.calcTermsDate(bizMsg, sMsg);
        // END   2018/03/07 [QC#24079, MOD]
        if (!checkSubmit(bizMsg)) {
            return;
        }

        // START 2020/03/23 [QC#53413, ADD]
        List<String> poNumList = new ArrayList<String>();
        // add start 2022/02/15 QC#57090
        List<String> vndRtrnNumList = new ArrayList<String>();
        // add end 2022/02/15 QC#57090
        Map<String, String> poNumListMap = new HashMap<String,String>();
        for (int z = 0; z < bizMsg.A.getValidCount(); z++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(z).poNum_A1)) {
                String poNum = bizMsg.A.no(z).poNum_A1.getValue();
                if (!poNumList.contains(poNum)) {
                    poNumList.add(poNum);
                }
                poNumListMap.put(bizMsg.A.no(z).xxDtlLineNum_A1.getValue(), poNum);
            }
            // add start 2022/02/15 QC#57090
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(z).vndRtrnNum_A1)) {
                String vndRtrnNum = bizMsg.A.no(z).vndRtrnNum_A1.getValue();
                if (!vndRtrnNumList.contains(vndRtrnNum)) {
                    vndRtrnNumList.add(vndRtrnNum);
                }
                poNumListMap.put(bizMsg.A.no(z).xxDtlLineNum_A1.getValue(), vndRtrnNum);
            }
            // add end 2022/02/15 QC#57090
        }
        // END   2020/03/23 [QC#53413, ADD]
        
        // START 2018/08/02 [QC#27025-1, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.ezUpTime) && ZYPCommonFunc.hasValue(bizMsg.ezUpTimeZone)) {
            CM_AP_INV_HDRTMsg outMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndCd, bizMsg.apVndCd);
            ZYPEZDItemValueSetter.setValue(outMsg.apInvTpCd, AP_INV_TP_CD_00);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndInvNum, bizMsg.apVndInvNum);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            outMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(outMsg);
            if (outMsg != null && !ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NZZM0003E");
                return;
            }
        }
        // END   2018/08/02 [QC#27025-1, ADD]


        String acctInvStsCd = EMPTY_STRING;

        // Currency Code
        String ccyCd = NFBL2040CommonLogic.getCcyCd();
        // Exchange Rate
        BigDecimal exchRate = BigDecimal.ONE;
        ACCT_DLY_ACTL_EXCH_RATESTMsg acctDlyActlExchRatesTMsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.ccyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.actlExchRateEntDt,ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        acctDlyActlExchRatesTMsg = (ACCT_DLY_ACTL_EXCH_RATESTMsg) S21FastTBLAccessor.findByKey(acctDlyActlExchRatesTMsg);
        if (acctDlyActlExchRatesTMsg != null) {
            exchRate = acctDlyActlExchRatesTMsg.actlExchRate.getValue();
        }
        // Get Varchar Const value(NFBL2040_AP_INV_CATG_CD)
        String apInvCatgCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_AP_INV_CATG_CD, GLBL_CMPY_CD);
        String apInvCatgDescTxt = NFBL2040CommonLogic.getApInvCatgDescTxt(apInvCatgCd);

        // START 2018/11/05 [QC#28982, ADD]
        CCYTMsg ccyMsg = NFBCommonBusiness.getCcyInfo(GLBL_CMPY_CD, ccyCd);
        // END   2018/11/05 [QC#28982, ADD]

        setVendorInfo(bizMsg);

        String acctInvSts = bizMsg.acctInvStsCd.getValue();
        // START 2021/10/15 J.Evangelista [QC#59272,ADD]
        if (ACCT_INV_STS.AUTHORIZED.equals(bizMsg.acctInvStsCd.getValue())
                && hasReleasedHold(bizMsg)) {
            // Remove existing record. (CM_INV_PMT_HLD)
            CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_OT.getValue());
            S21FastTBLAccessor.removeByPartialValue(cmInvPmtHldTMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" });
        }
        // END 2021/10/15 J.Evangelista [QC#59272,ADD]
        // START 2018/07/30 [QC#27025-1, MOD]
        // if (acctInvSts.equals(ACCT_INV_STS.ENTERED)) {
        if (acctInvSts.equals(ACCT_INV_STS.ENTERED) || acctInvSts.equals(ACCT_INV_STS.AUTHORIZED)) {
        // END   2018/07/30 [QC#27025-1, MOD]
            // Check Duplicated record
            boolean isDuplicated = false;
            isDuplicated = checkPmtHold(bizMsg);
            if (isDuplicated) {
                return;
            }
            // Manual Hold
            boolean isError = false;
            // START 2020/03/23 [QC#53413, MOD]
            //isError = manualHold(bizMsg, acctInvSts);
            isError = manualHold(bizMsg, acctInvSts, poNumListMap);
            // END   2020/03/23 [QC#53413, MOD]
            if (isError) {
                return;
            }
            // START 2018/01/04 [QC#22143, DEL]
            // NFBL2040CommonLogic.refreshData(bizMsg);
            // return;
            // END   2018/01/04 [QC#22143, DEL]
        }

        // START 2018/08/13 [QC#27025-2, ADD]
        if (acctInvSts.equals(ACCT_INV_STS.AUTHORIZED)) {
            // START 2020/03/23 [QC#53413, MOD]
            //updatePoStatusForAuthorizedInvoice(bizMsg);
            updatePoStatusForAuthorizedInvoice(bizMsg, poNumList);
            // END   2020/03/23 [QC#53413, MOD]
        }
        // END   2018/08/13 [QC#27025-2, ADD]

        // START 2020/03/23 [QC#53413, MOD]
        //if(isErrorExistsCheck(bizMsg)){
        // mod start 2022/02/15 QC#57090
        //if(isErrorExistsCheck(bizMsg, poNumList)){
        if(isErrorExistsCheck(bizMsg, poNumList, vndRtrnNumList)){
        // mod end 2022/02/15 QC#57090
        // END   2020/03/23 [QC#53413, MOD]
            return;
        }

        // START 2018/01/04 [QC#22143, MOD]
        if (!ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) || isInvoicePKModified(bizMsg)) {
            if (isDuplicated(bizMsg)) {
                return;
            }
        }
        // END   2018/01/04 [QC#22143, MOD]

        // START 2020/03/23 [QC#53413, MOD]
        //if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) && isErrorVendorMatch(bizMsg)) {
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) && isErrorVendorMatch(bizMsg, poNumList)) {
        // END   2020/03/23 [QC#53413, MOD]
            return;
        }
        // START 2020/03/23 [QC#53413, MOD]
        //if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && isErrorVendorMatchForCreditMemo(bizMsg)) {
        // mod start 2022/02/15 QC#57090
        //if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && isErrorVendorMatchForCreditMemo(bizMsg, poNumList)) {
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && isErrorVendorMatchForCreditMemo(bizMsg, poNumList, vndRtrnNumList)) {
        // mod end 2022/02/15 QC#57090
        // END   2020/03/23 [QC#53413, MOD]
            return;
        }

        // Cancel assigned status on CM_STK_IN table.
        NFBL2040CommonLogic.clearCmStkInAsgSts(bizMsg);
        // Assign CM_STK_IN records to invoice.
        List<List> listKey = new ArrayList<List>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START QC#25902,QC#25190,QC#25141
            String poNum = "";
            List<String> pkList = new ArrayList<String>();
            // START 2020/03/23 [QC#53413, ADD]
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                pkList.add(bizMsg.A.no(i).poNum_A1.getValue());
                poNum = bizMsg.A.no(i).poNum_A1.getValue();
            // END   2020/03/23 [QC#53413, ADD]
            // add start 2022/02/15 QC#57090
            } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).vndRtrnNum_A1)) {
                pkList.add(bizMsg.A.no(i).vndRtrnNum_A1.getValue());
                poNum = bizMsg.A.no(i).vndRtrnNum_A1.getValue();
            // add end 2022/02/15 QC#57090
            } else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                pkList.add(bizMsg.poNum_HT.getValue());
                poNum = bizMsg.poNum_HT.getValue();
            } else {
                pkList.add(bizMsg.vndRtrnNum_H.getValue());
                poNum = bizMsg.vndRtrnNum_H.getValue();
            }
            // END QC#25902,QC#25190,QC#25141
            // QC#14858-Sol#060 text item.
            if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
                pkList.add(bizMsg.A.no(i).xxMdseCd_A1.getValue());
            } else {
                pkList.add(bizMsg.A.no(i).mdseCd_A1.getValue());
            }
            if (!listKey.contains(pkList)) {
                // START QC#25902,QC#25190,QC#25141
                List<Map> resultList = (List<Map>) NFBL2040CommonLogic.getCmStkInPkListByPartialValue(poNum, bizMsg.A.no(i).mdseCd_A1.getValue());
                // END QC#25902,QC#25190,QC#25141
                if (resultList != null) {
                    for (int j = 0; j < resultList.size(); j++) {
                        Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);
                        CM_STK_INTMsg cmStkIn = new CM_STK_INTMsg();
                        ZYPEZDItemValueSetter.setValue(cmStkIn.glblCmpyCd, GLBL_CMPY_CD);
                        ZYPEZDItemValueSetter.setValue(cmStkIn.cmStkInPk, (BigDecimal) resultMap.get(CM_STK_IN_PK));
                        cmStkIn = (CM_STK_INTMsg) EZDTBLAccessor.findByKeyForUpdate(cmStkIn);
                        if (cmStkIn != null) {
                            ZYPEZDItemValueSetter.setValue(cmStkIn.vndCd, bizMsg.apVndCd.getValue());
                            ZYPEZDItemValueSetter.setValue(cmStkIn.vndInvNum, bizMsg.apVndInvNum.getValue());
                            ZYPEZDItemValueSetter.setValue(cmStkIn.vndInvSqNum, AP_VND_INV_SQ_NUM_00);
                            ZYPEZDItemValueSetter.setValue(cmStkIn.invAsgDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                            ZYPEZDItemValueSetter.setValue(cmStkIn.invAsgFlg, ZYPConstant.FLG_ON_Y);
                            EZDTBLAccessor.update(cmStkIn);
                        }
                    }
                }
                listKey.add(pkList);
            }
        }

        // START 2018/03/22 [QC#20316, MOD]
        //// Create CM_INV_ACCT_HDR table record.
        //CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        // 
        // // START 2018/01/04 [QC#22143, ADD]
        // if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && !isInvoicePKModified(bizMsg)) {
        //     cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvAcctHdrTMsg);
        //     if (cmInvAcctHdrTMsg == null) {
        //         bizMsg.setMessageInfo(NFBM0069E);
        //         return;
        //     }
        // }
        // // END   2018/01/04 [QC#22143, ADD]
        //
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.invDt, bizMsg.invDt.getValue());
        // if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
        //     ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.poNum, bizMsg.poNum_HT);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.poNum, NONE);
        // }
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtDueDt, bizMsg.termNetDueDt);
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.vndPmtTermCd, bizMsg.vndPmtTermCd);
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apPmtChkNum, bizMsg.apPmtChkNum);
        // cmInvAcctHdrTMsg.acctBankCd.clear();
        // cmInvAcctHdrTMsg.acctBankAcctPayTpCd.clear();
        // cmInvAcctHdrTMsg.invTpCd.clear();
        // if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_HO)
        // && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_HO.getValue())) {
        //    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
        // }
        // Create CM_AP_INV_HDR table record.
        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);

        if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && !isInvoicePKModified(bizMsg)) {
            cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
            if (cmApInvHdrTMsg == null) {
                bizMsg.setMessageInfo(NFBM0069E);
                return;
            }
        }
        // END   2018/03/22 [QC#20316, MOD]

        // HOLDS tab
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            // START 2018/11/20 [QC#28660, MOD]
            // NFBL2040CommonLogic.commonRefreshHoldsTab(bizMsg, USER_ID);
            NFBL2040CommonLogic.commonRefreshHoldsTab(bizMsg, USER_ID, true);
            // END   2018/11/20 [QC#28660, MOD]
        } else {
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

            int validCountH = bizMsg.H.getValidCount();
            if (ZYPCommonFunc.hasValue(bizMsg.docMgtDocId)
                    && ZYPCommonFunc.hasValue(bizMsg.docMgtCatgCd)) {
                // START 2018/03/28 [QC#24277, MOD]
                // if (NFBL2040CommonLogic.addRecordToH(bizMsg, NFBL2040CommonLogic.getPmtHldRelRsnPulldownList(), USER_ID, validCountH, checkList, PMT_HLD_CD_030, PMT_HLD_RSN_CD_031, ZYPConstant.FLG_OFF_N)) {
                //     validCountH++;
                // }
                if (NFBL2040CommonLogic.addRecordToH(bizMsg, NFBL2040CommonLogic.getPmtHldRelRsnPulldownList(), USER_ID, validCountH, checkList, EMPTY_AP_VND_INV_LINE_NUM, PMT_HLD_CD_030, PMT_HLD_RSN_CD_031, ZYPConstant.FLG_OFF_N)) {
                    validCountH++;
                }
                // END   2018/03/28 [QC#24277, MOD]
            }

            bizMsg.H.setValidCount(validCountH);

            // mod start 2022/02/15 QC#57090
            //// QC#27029 Add.
            //if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)) {
            //    NFBL2040CommonLogic.checkHoldForVendorReturn(bizMsg, USER_ID);
            //}
            //// QC#27029-1 Add.
            //if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            //    NFBL2040CommonLogic.checkHoldForCreditMemoPo(bizMsg, USER_ID);
            //}
            if (vndRtrnNumList.size() > 0) {
                NFBL2040CommonLogic.checkHoldForVendorReturn(bizMsg, USER_ID);
            } else {
                NFBL2040CommonLogic.checkHoldForCreditMemoPo(bizMsg, USER_ID);
            }
            // mod end 2022/02/15 QC#57090
        }
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxChkBox_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_ON_Y);
                break;
            }
        }

        NFBL2040CommonLogic.commonRefreshDistributionsTab(bizMsg, USER_ID);

        // Set Hold Information on CM_AP_INV_HDR table.
        if (bizMsg.H.getValidCount() > 0) {
            boolean isHold = false;
            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.H.no(i).xxChkBox_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).xxChkBox_H1, ZYPConstant.FLG_OFF_N);
                }
                if (!isHold 
                &&   bizMsg.H.no(i).xxChkBox_H1.getValue().equals(ZYPConstant.FLG_OFF_N)
                ) {
                    isHold = true;
                    // START 2018/03/22 [QC#20316, MOD]
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue()); // Hold name
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldDt, bizMsg.H.no(i).pmtHldDt_H1.getValue()); 
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1.getValue()); // Hold Reason
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldPsnCd, bizMsg.H.no(i).pmtHldPsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, bizMsg.H.no(i).pmtHldRelPsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, bizMsg.H.no(i).pmtHldRelDt_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, bizMsg.H.no(i).pmtHldRelCmntTxt_H1.getValue());
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                    // END   2018/03/22 [QC#20316, MOD]
                }
            }
            if (!isHold) {
                // START 2018/03/22 [QC#20316, MOD]
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, bizMsg.H.no(0).pmtHldCd_H1.getValue()); // Hold name
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldDt, bizMsg.H.no(0).pmtHldDt_H1.getValue()); 
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, bizMsg.H.no(0).pmtHldRsnCd_H1.getValue()); // Hold Reason
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldPsnCd, bizMsg.H.no(0).pmtHldPsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, bizMsg.H.no(0).pmtHldRelPsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, bizMsg.H.no(0).pmtHldRelDt_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, bizMsg.H.no(0).pmtHldRelRsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, bizMsg.H.no(0).pmtHldRelCmntTxt_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                // END   2018/03/22 [QC#20316, MOD]
            }
        } else {
            // START 2018/03/22 [QC#20316, MOD]
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, PMT_HLD_CD_000);
//            cmInvAcctHdrTMsg.pmtHldDt.clear();
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, PMT_HLD_RSN_CD_000);
//            cmInvAcctHdrTMsg.pmtHldPsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelPsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelDt.clear();
//            cmInvAcctHdrTMsg.pmtHldRelRsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelCmntTxt.clear();
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
            // END   2018/03/22 [QC#20316, MOD]
        }

        if (bizMsg.H.getValidCount() > 0) {
            // START 2018/01/04 [QC#22143, ADD]
            if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && isInvoicePKModified(bizMsg)) {
                // Remove existing record. (CM_INV_PMT_HLD)
                CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd_OT.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum_OT.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_OT.getValue());
                S21FastTBLAccessor.removeByPartialValue(cmInvPmtHldTMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" });
            }
            // END   2018/01/04 [QC#22143, ADD]

            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
                // START 2018/03/28 [QC#24277, ADD]
                // START 2018/04/10 [QC#24277, MOD]
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
                if (EMPTY_AP_VND_INV_LINE_NUM.equals(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
                }
                // END   2018/04/10 [QC#24277, MOD]
                // END   2018/03/28 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue()); 
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1);

                // START 2018/01/04 [QC#22143, ADD]
                boolean updHldRecFlg = false;
                // START 2019/02/21 [QC#30420, ADD]
                boolean delHldRecFlg = false;
                // END   2019/02/21 [QC#30420, ADD]
                CM_INV_PMT_HLDTMsg updCmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
                ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
                ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
                ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
                // START 2018/03/28 [QC#24277, ADD]
                if (EMPTY_AP_VND_INV_LINE_NUM.equals(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
                } else {
                    ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
                }
                // END   2018/03/28 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue()); 
                ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1);
                updCmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(updCmInvPmtHldTMsg);
                if (updCmInvPmtHldTMsg != null) {
                    // START 2019/02/21 [QC#30420, MOD]
                    // cmInvPmtHldTMsg = updCmInvPmtHldTMsg;
                    // updHldRecFlg = true;
                    cmInvPmtHldTMsg = updCmInvPmtHldTMsg;

                    int aIndex = findInvoiceLineIndex(bizMsg, bizMsg.H.no(i));
                    if (aIndex != -1) {
                        BigDecimal lineAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(aIndex).xxInvAmt_A1.getValue());
                        BigDecimal invQty = NFBCommonBusiness.chkNull(bizMsg.A.no(aIndex).apBillQty_A1.getValue());
                        if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
                            delHldRecFlg = true;
                        } else {
                            updHldRecFlg = true;
                        }
                    } else {
                        updHldRecFlg = true;
                    }
                    // END   2019/02/21 [QC#30420, MOD]
                }
                // END   2018/01/04 [QC#22143, ADD]
                // mod start 2022/02/15 QC#57090
                //if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                //    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.poNum_HT);
                //// START 2020/03/23 [QC#53413, ADD]
                //} else if (poNumListMap.containsKey(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                //    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, poNumListMap.get(bizMsg.H.no(i).apVndInvLineNum_H1.getValue()));
                //// END   2020/03/23 [QC#53413, ADD]
                if (poNumListMap.containsKey(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, poNumListMap.get(bizMsg.H.no(i).apVndInvLineNum_H1.getValue()));
                } else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.poNum_HT);
                // mod end 2022/02/15 QC#57090
                // START QC#25902,QC#25190,QC#25141
                } else if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.vndRtrnNum_H);
                // END QC#25902,QC#25190,QC#25141
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, NONE);
                }
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, bizMsg.H.no(i).pmtHldDt_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, bizMsg.H.no(i).pmtHldPsnCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, bizMsg.H.no(i).pmtHldRelPsnCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, bizMsg.H.no(i).pmtHldRelDt_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue()); 
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, bizMsg.H.no(i).pmtHldRelCmntTxt_H1.getValue());
                if (ZYPCommonFunc.hasValue(bizMsg.H.no(i).xxChkBox_H1.getValue())
                &&  bizMsg.H.no(i).xxChkBox_H1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                }

                // START 2018/01/04 [QC#22143, MOD]
                if (updHldRecFlg) {
                    // Update CM_INV_PMT_HLD record.
                    EZDTBLAccessor.update(cmInvPmtHldTMsg);
                    if (!RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NFBM0224E, new String[] {cmInvPmtHldTMsg.getTableName(), cmInvPmtHldTMsg.getReturnCode() });
                        return;
                    }
                // START 2019/02/21 [QC#30420, MOD]
                } else if (delHldRecFlg) {
                    // Delete CM_INV_PMT_HLD record.
                    EZDTBLAccessor.remove(cmInvPmtHldTMsg);
                    if (!RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NFBM0224E, new String[] {cmInvPmtHldTMsg.getTableName(), cmInvPmtHldTMsg.getReturnCode() });
                        return;
                    }
                // END   2019/02/21 [QC#30420, MOD]
                } else {
                    // Create new CM_INV_PMT_HLD record.
                    EZDTBLAccessor.create(cmInvPmtHldTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                       bizMsg.setMessageInfo(NFBM0073E, new String[] {cmInvPmtHldTMsg.getTableName(), cmInvPmtHldTMsg.getReturnCode() });
                       return;
                    }
                }
                // END   2018/01/04 [QC#22143, MOD]
            }
        }

        // START 2018/03/22 [QC#20316, MOD]
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acInvTotCostAmt, bizMsg.invAmt.getValue());
        String apInvCatgDescTxtForDb = apInvCatgDescTxt;
        if (ZYPCommonFunc.hasValue(apInvCatgDescTxtForDb)) {
            if (apInvCatgDescTxtForDb.length() > 20) {
                apInvCatgDescTxtForDb = apInvCatgDescTxtForDb.substring(0,20);
            }
        }
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvSrcNm, apInvCatgDescTxtForDb);
        cmApInvHdrTMsg.apInvDescTxt.clear();
        if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_PA)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_PA, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.payAloneFlg, bizMsg.xxChkBox_PA.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apPmtChkNum, bizMsg.apPmtChkNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.invHdrDescTxt, bizMsg.invHdrDescTxt.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_HO)
        &&  ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_HO.getValue())) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
            acctInvStsCd = ACCT_INV_STS.ENTERED;
        // START 2017/12/28 [QC#22143, MOD] 2018/07/12 Update.
        // START 2018/07/30 [QC#27025-1, DEL]
        // } else if (0 < bizMsg.H.getValidCount()) {
        //     ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
        //     acctInvStsCd = ACCT_INV_STS.ENTERED;
        // END   2018/07/30 [QC#27025-1, DEL]
        } else {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
            acctInvStsCd = ACCT_INV_STS.AUTHORIZED;
        }
        // END   2017/12/28 [QC#22143, MOD]
        // END   2018/03/22 [QC#20316, MOD]

        // START 2018/03/22 [QC#20316, DEL]
//        cmInvAcctHdrTMsg.coaCmpyCd.clear();
//        cmInvAcctHdrTMsg.coaBrCd.clear();
//        cmInvAcctHdrTMsg.coaCcCd.clear();
//        cmInvAcctHdrTMsg.coaAcctCd.clear();
//        cmInvAcctHdrTMsg.coaProdCd.clear();
//        cmInvAcctHdrTMsg.coaChCd.clear();
//        cmInvAcctHdrTMsg.coaAfflCd.clear();
//        cmInvAcctHdrTMsg.coaProjCd.clear();
//        cmInvAcctHdrTMsg.coaExtnCd.clear();
//        // QC#14858-Sol#060 Update. Set Coa Infomation from first record.
//        if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).xxCmntTxt_A1)) {
//            String xxCmntTxt = bizMsg.A.no(0).xxCmntTxt_A1.getValue();
//            String[] strSplit = xxCmntTxt.split("\\.");
//            if (!NFBL2040CommonLogic.checkGlCodeCombination(bizMsg, 0, strSplit)) {
//                return;
//            }
//            if (strSplit.length > 0) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaCmpyCd, strSplit[0]);
//            }
//            if (strSplit.length > 1) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaBrCd, strSplit[1]);
//            }
//            if (strSplit.length > 2) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaCcCd, strSplit[2]);
//            }
//            if (strSplit.length > 3) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaAcctCd, strSplit[3]);
//            }
//            if (strSplit.length > 4) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaProdCd, strSplit[4]);
//            }
//            if (strSplit.length > 5) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaChCd, strSplit[5]);
//            }
//            if (strSplit.length > 6) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaAfflCd, strSplit[6]);
//            }
//            if (strSplit.length > 7) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaProjCd, strSplit[7]);
//            }
//            if (strSplit.length > 8) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaExtnCd, strSplit[8]);
//            }
//        }
//
//        String coaAcctCd = cmInvAcctHdrTMsg.coaAcctCd.getValue();
//        String coaProjCd = cmInvAcctHdrTMsg.coaProjCd.getValue();
        // END   2018/03/22 [QC#20316, DEL]

        // START 2018/03/22 [QC#20316, MOD]
        // cmApInvHdrTMsg.apPmtChkNum.clear();
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apPmtChkNum, bizMsg.apPmtChkNum);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.ifProcStsCd, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvCatgCd, apInvCatgCd);
        // QC#21653 Delete cmInvMatchCd_S
        // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.cmInvMatchCd, bizMsg.cmInvMatchCd_S.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.prntVndCd, bizMsg.prntVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.jrnlLinkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.cancJrnlLinkFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.cmApInvTpCd, bizMsg.cmApInvTpCd_S.getValue());
        // END   2018/03/22 [QC#20316, MOD]

        // START 2018/03/22 [QC#20316, DEL]
        // START 2018/01/04 [QC#22143, MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && !isInvoicePKModified(bizMsg)) {
//            // Update invoice record.
//            EZDTBLAccessor.update(cmInvAcctHdrTMsg);
//            if (!RTNCD_NORMAL.equals(cmInvAcctHdrTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NFBM0224E, new String[] {cmInvAcctHdrTMsg.getTableName(), cmInvAcctHdrTMsg.getReturnCode() });
//                return;
//            }
//        } else {
//            if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && isInvoicePKModified(bizMsg)) {
//                // Remove old invoice record.
//                CM_INV_ACCT_HDRTMsg  rmvCmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//                ZYPEZDItemValueSetter.setValue(rmvCmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
//                ZYPEZDItemValueSetter.setValue(rmvCmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd_OT.getValue());
//                ZYPEZDItemValueSetter.setValue(rmvCmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum_OT.getValue());
//                ZYPEZDItemValueSetter.setValue(rmvCmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_OT.getValue());
//                EZDTBLAccessor.findByKeyForUpdate(rmvCmInvAcctHdrTMsg);
//                if (rmvCmInvAcctHdrTMsg == null) {
//                    bizMsg.setMessageInfo(NFBM0069E);
//                    return;
//                }
//                EZDTBLAccessor.remove(rmvCmInvAcctHdrTMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvCmInvAcctHdrTMsg.getReturnCode())) {
//                    bizMsg.setMessageInfo(NFBM0073E, new String[] {rmvCmInvAcctHdrTMsg.getTableName(), rmvCmInvAcctHdrTMsg.getReturnCode() });
//                    return;
//                }
//            }
//            // Create new invoice record.
//            EZDTBLAccessor.create(cmInvAcctHdrTMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctHdrTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NFBM0073E, new String[] {cmInvAcctHdrTMsg.getTableName(), cmInvAcctHdrTMsg.getReturnCode() });
//                return;
//            }
//        }
        // END   2018/01/04 [QC#22143, MOD]
        // START 2018/03/22 [QC#20316, DEL]

        // START 2018/03/22 [QC#20316, DEL]
        // // Create CM_AP_INV_HDR table record.
        // CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
        // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
        // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
        // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
        // 
        // // START 2018/01/04 [QC#22143, ADD]
        // if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && !isInvoicePKModified(bizMsg)) {
        //     cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
        //     if (cmApInvHdrTMsg == null) {
        //         bizMsg.setMessageInfo(NFBM0069E);
        //         return;
        //     }
        // }
        // END   2018/01/04 [QC#22143, ADD]
        // END   2018/03/22 [QC#20316, DEL]

        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.imptInvCnsgnCd, EMPTY_STRING);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.invDt, bizMsg.invDt.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.ccyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtDueDt, bizMsg.termNetDueDt);
        // START 2018/03/22 [QC#20316, MOD]
        // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.vndPmtTermCd, EMPTY_STRING);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.vndPmtTermCd, bizMsg.vndPmtTermCd);
        // START 2018/03/22 [QC#20316, MOD]
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctBankCd, EMPTY_STRING);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctBankAcctPayTpCd, EMPTY_STRING);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.invTpCd, EMPTY_STRING);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.lastTrxDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotFobCostAmt, bizMsg.invAmt.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotFrtCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotInsCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotDomExpCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotOthCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotGndCostAmt, bizMsg.invAmt.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acOcTotGndInvAmt, bizMsg.invAmt.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotFobCostAmt, bizMsg.invAmt.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotFrtCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotInsCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotDomExpCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotOthCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotTaxAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotDiscAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotGndCostAmt, bizMsg.invAmt.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acScTotGndInvAmt, bizMsg.invAmt.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apDiscRate, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.intfcId, EMPTY_STRING);

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_HO)
        &&  ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_HO.getValue())) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
        }

        // START 2018/03/22 [QC#20316, ADD]
        String coaAcctCd = null;
        String coaProjCd = null;
        String xxCmntTxtHdr = bizMsg.A.no(0).xxCmntTxt_A1.getValue();
        String[] strSplitHdr = xxCmntTxtHdr.split("\\.");
        if (strSplitHdr.length > 3) {
            coaAcctCd = strSplitHdr[3];
        }
        if (strSplitHdr.length > 7) {
            coaProjCd = strSplitHdr[7];
        }
        // END   2018/03/22 [QC#20316, ADD]

        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.coaAcctCd, coaAcctCd);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.coaProjCd, coaProjCd);
        // QC#23038 MOD START
        //ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.vndInvImptTpCd, EMPTY_STRING);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.vndInvImptTpCd, VND_INV_IMPT_TP_CD_10);
        // QC#23038 MOD END
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.origScTotFobCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.origScTotFrtCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.origScTotInsCostAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.origScTotOthCostAmt, BigDecimal.ZERO);
        // mod start 2022/02/15 QC#57090
        //if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
        //    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.poNum, bizMsg.poNum_HT);
        //// START 2020/03/23 [QC#53413, ADD]
        //} else if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).poNum_A1)) {
        //    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.poNum, bizMsg.A.no(0).poNum_A1.getValue());
        //// END   2020/03/23 [QC#53413, ADD]
        // START QC#25902,QC#25190,QC#25141
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).poNum_A1)) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.poNum, bizMsg.A.no(0).poNum_A1.getValue());
        } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).vndRtrnNum_A1)) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.vndRtrnNum, bizMsg.A.no(0).vndRtrnNum_A1.getValue());
        } else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.poNum, bizMsg.poNum_HT);
        // mod end 2022/02/15 QC#57090
        } else if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.vndRtrnNum, bizMsg.vndRtrnNum_H);
        // END QC#25902,QC#25190,QC#25141
        } else {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.poNum, NONE);
        }

        // START 2018/01/04 [QC#22143, MOD]
        if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && bizMsg.apVndInvNum_OT.getValue().equals(bizMsg.apVndInvNum.getValue())) {
            // Update invoice record.
            EZDTBLAccessor.update(cmApInvHdrTMsg);
            if (!RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0224E, new String[] {cmApInvHdrTMsg.getTableName(), cmApInvHdrTMsg.getReturnCode() });
                return;
            }
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && isInvoicePKModified(bizMsg)) {
                // Remove old invoice record.
                CM_AP_INV_HDRTMsg rmvCmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
                ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.apVndCd, bizMsg.apVndCd_OT.getValue());
                ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum_OT.getValue());
                ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_OT.getValue());
                ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
                EZDTBLAccessor.findByKeyForUpdate(rmvCmApInvHdrTMsg);
                if (rmvCmApInvHdrTMsg == null) {
                    bizMsg.setMessageInfo(NFBM0069E);
                    return;
                }
                EZDTBLAccessor.remove(rmvCmApInvHdrTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvCmApInvHdrTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NFBM0073E, new String[] {rmvCmApInvHdrTMsg.getTableName(), rmvCmApInvHdrTMsg.getReturnCode() });
                    return;
                }
            }
            // Create new invoice record.
            EZDTBLAccessor.create(cmApInvHdrTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0073E, new String[] {cmApInvHdrTMsg.getTableName(), cmApInvHdrTMsg.getReturnCode() });
                return;
            }
        }
        // END   2018/01/04 [QC#22143, MOD]

        // START 2018/01/04 [QC#22143, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT)) {
            // Remove existing record. (CM_INV_ACCT_DIST)
            CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, bizMsg.apVndCd_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvNum, bizMsg.apVndInvNum_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_OT.getValue());
            S21FastTBLAccessor.removeByPartialValue(cmInvAcctDistTMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" });

            // Remove existing record. (CM_AP_INV_DTL)
            CM_AP_INV_DTLTMsg cmApInvDtlTMsg = new CM_AP_INV_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndCd, bizMsg.apVndCd_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvNum, bizMsg.apVndInvNum_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apInvTpCd, AP_INV_TP_CD_00);
            S21FastTBLAccessor.removeByPartialValue(cmApInvDtlTMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum", "apInvTpCd" });
        }
        // END   2018/01/04 [QC#22143, ADD]

        // START 2018/08/21 [QC#27873, ADD]
        String xxCmntTxtCr = NFBL2040CommonLogic.getCreditLineAccountCode(bizMsg.apVndCd.getValue());
        // END   2018/08/21 [QC#27873, ADD]

        // START 2020/03/23 [QC#53413, MOD]
        // START 2019/02/07 [QC#30136, ADD]
        boolean isBlanketPO = false;
        if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            isBlanketPO = ZYPCommonFunc.hasValue(bizMsg.poNum) && NFBL2040CommonLogic.isBlanketPO(bizMsg.poNum.getValue());
        } else {
            isBlanketPO = ZYPCommonFunc.hasValue(bizMsg.A.no(0).poNum_A1) && NFBL2040CommonLogic.isBlanketPO(bizMsg.A.no(0).poNum_A1.getValue());
        }
        // END   2019/02/07 [QC#30136, ADD]
        // END   2020/03/23 [QC#53413, MOD]

        // Create record on CM_INV_ACCT_DIST table.
        Map<List<String>,Map<String, Object>> cmInvAcctDistMap = new HashMap<List<String>,Map<String, Object>>();
        List<List<String>> cmInvAcctDistPkList = new ArrayList<List<String>>();
        // Create record on CM_AP_INV_DTL table.
        Map<List<String>,Map<String, Object>> cmApInvDtlMap = new HashMap<List<String>,Map<String, Object>>();
        List<List<String>> cmApInvDtlPkList = new ArrayList<List<String>>();
        int commitLineNumForInv = 1;
        int commitLineNumForApInv = 1;
        // START 2019/02/21 [QC#30420, DEL]
        // int apVndInvLineNumItem = 1;
        // int apVndInvLineNum = 1;
        // int itemCnt = 0;
        // START 2018/03/16 [QC#24089, MOD]
        // for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //    if ((!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
        //             && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)
        //             && bizMsg.A.no(i).apBillQty_A1.getValue().compareTo(BigDecimal.ZERO) > 0
        //             && bizMsg.A.no(i).xxInvAmt_A1.getValue().compareTo(BigDecimal.ZERO) >= 0)
        //         || bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)) {
        //        itemCnt++;
        //     }
        // }
        // for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //     if ((!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
        //             && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM))
        //         || bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)) {
        //         itemCnt++;
        //     }
        // }
        // END   2018/03/16 [QC#24089, MOD]
        // int apVndInvLineNumOther = itemCnt + 1;
        // END   2019/02/21 [QC#30420, DEL]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            // CM_INV_ACCT_DIST table primary key values.
            List<String> pkList = new ArrayList<String>();
            // START 2018/03/22 [QC#20316, MOD]
            // pkList.add(GLBL_CMPY_CD);
            // pkList.add(bizMsg.apVndCd.getValue());
            // pkList.add(bizMsg.apVndInvNum.getValue());
            // pkList.add(AP_VND_INV_SQ_NUM_00);
            // // QC#14858-Sol#060 text item.
            // if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
            //     pkList.add(bizMsg.A.no(i).xxMdseCd_A1.getValue());
            // } else {
            //     pkList.add(bizMsg.A.no(i).mdseCd_A1.getValue());
            // }
            // pkList.add(AP_INV_TP_CD_00);
            // pkList.add(bizMsg.poNum_HT.getValue());
            // pkList.add(bizMsg.delyOrdNum_DH.getValue());
            // pkList.add(bizMsg.A.no(i).apLineTpCd_A1.getValue());
            pkList.add(GLBL_CMPY_CD);
            pkList.add(bizMsg.apVndCd.getValue());
            pkList.add(bizMsg.apVndInvNum.getValue());
            pkList.add(AP_VND_INV_SQ_NUM_00);
            // START 2019/02/21 [QC#30420, MOD]
            // if (bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)) {
            //     pkList.add(String.format(AP_VND_INV_LINE_NUM_FORMAT, apVndInvLineNumItem));
            //     apVndInvLineNumItem++;
            // } else {
            //     pkList.add(String.format(AP_VND_INV_LINE_NUM_FORMAT, apVndInvLineNumOther));
            //     apVndInvLineNumOther++;
            // }
            pkList.add(bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
            // END   2019/02/21 [QC#30420, MOD]
            pkList.add(String.format(LINE_NUM_FORMAT, 1));
            // END   2018/03/22 [QC#20316, MOD]

            // CM_INV_ACCT_DIST table non primary key values.
            Map<String, Object> cmInvAcctDistNonPrimaryKeyMap = cmInvAcctDistMap.get(pkList);
            // START 2018/03/16 [QC#24089, MOD]
            // if (bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
            //         || (!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
            //                 && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)
            //                 && bizMsg.A.no(i).apBillQty_A1.getValue().compareTo(BigDecimal.ZERO) > 0
            //                 && bizMsg.A.no(i).xxInvAmt_A1.getValue().compareTo(BigDecimal.ZERO) >= 0)
            //                 || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.TAX)
            //                 || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)) {
            if (bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
                    || (!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
                            && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM))
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.TAX)
                            // START QC#25902,QC#25190,QC#25141
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.MISC)) {
                            // END QC#25902,QC#25190,QC#25141
            // END   2018/03/16 [QC#24089, MOD]
                if (cmInvAcctDistNonPrimaryKeyMap == null) {
                    // START 2018/03/22 [QC#20316, DEL]
                    // int cmInvAcctDistLineNum = 1;
                    // END   2018/03/22 [QC#20316, DEL]
                    // Not exists primary key in the map
                    cmInvAcctDistPkList.add(pkList);
                    cmInvAcctDistNonPrimaryKeyMap = new HashMap<String, Object>();
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_TP_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(PO_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_RCV_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).invRcvQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_BILL_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_REJ_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).apRejQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(UOM_CD, bizMsg.A.no(i).vndUomCd_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(THIS_MTH_FOB_COST_AMT, BigDecimal.ZERO);
                    cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_TAX_AMT, BigDecimal.ZERO);
                    // QC#19261
                    if (bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)) {
                      cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                      // START 2018/06/20 [QC#26169, ADD]
                      // START 2019/02/07 [QC#30136, MOD]
                      // if (NFBL2040CommonLogic.hasVariance(bizMsg.A.no(i))) {
                      if (NFBL2040CommonLogic.hasVariance(bizMsg.A.no(i)) && !isBlanketPO) {
                      // END   2019/02/07 [QC#30136, MOD]
                          // START 2018/08/15 [QC#27029-1, MOD]
                          // cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                          if (NFBL2040CommonLogic.isEffectiveStkInPrice(bizMsg.A.no(i))) {
                              cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));

                          } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3)) {
                              cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                          }
                          // END   2018/08/15 [QC#27029-1, MOD]
                      }
                      // END   2018/06/20 [QC#26169, ADD]
                    } else {
                      cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    }
                    cmInvAcctDistNonPrimaryKeyMap.put(LINE_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_ASG_DT, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                    cmInvAcctDistNonPrimaryKeyMap.put(STK_IN_DT, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CSMP_INV_NUM, bizMsg.A.no(i).csmpInvNum_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(SER_NUM, bizMsg.A.no(i).serNum_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(CONTR_NUM, bizMsg.A.no(i).dsContrNum_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(CUST_DLR_CD, bizMsg.A.no(i).custDlrCd_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_FIRST_LINE_ADDR, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_FL_NM, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_CTY_ADDR, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_ST_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_POST_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_LINE_DESC_TXT, null);
                    // START 2018/03/22 [QC#20316, MOD]
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CMPY_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_BR_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CC_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_ACCT_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_PROD_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CH_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_AFFL_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_PROJ_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_EXTN_CD, null);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxCmntTxt_A1)) {
                        String xxCmntTxt = bizMsg.A.no(i).xxCmntTxt_A1.getValue();
                        String[] strSplit = xxCmntTxt.split("\\.");
                        if (!NFBL2040CommonLogic.checkGlCodeCombination(bizMsg, i, strSplit)) {
                            return;
                        }
                        if (strSplit.length > 0) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CMPY_CD, strSplit[0]);
                        }
                        if (strSplit.length > 1) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_BR_CD, strSplit[1]);
                        }
                        if (strSplit.length > 2) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CC_CD, strSplit[2]);
                        }
                        if (strSplit.length > 3) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_ACCT_CD, strSplit[3]);
                        }
                        if (strSplit.length > 4) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_PROD_CD, strSplit[4]);
                        }
                        if (strSplit.length > 5) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CH_CD, strSplit[5]);
                        }
                        if (strSplit.length > 6) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_AFFL_CD, strSplit[6]);
                        }
                        if (strSplit.length > 7) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_PROJ_CD, strSplit[7]);
                        }
                        if (strSplit.length > 8) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_EXTN_CD, strSplit[8]);
                        }
                    }
                    // END   2018/03/22 [QC#20316, MOD]
                    // START 2018/03/22 [QC#20316, ADD]
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CMPY_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_BR_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CC_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_ACCT_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_PROD_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CH_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_AFFL_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_PROJ_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_EXTN_CD, null);
                    // START 2018/08/21 [QC#27873, MOD]
                    // if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).xxCmntTxt_A1)) {
                    //     String xxCmntTxt = bizMsg.A.no(0).xxCmntTxt_A1.getValue();
                    //     String[] strSplit = xxCmntTxt.split("\\.");
                    if (ZYPCommonFunc.hasValue(xxCmntTxtCr)) {
                        String[] strSplit = xxCmntTxtCr.split("\\.");
                    // END   2018/08/21 [QC#27873, MOD]
                        // START 2018/07/17 S.Katsuma [QC#27078,DEL]
//                        if (!NFBL2040CommonLogic.checkGlCodeCombination(bizMsg, i, strSplit)) {
//                            return;
//                        }
                        // END 2018/07/17 S.Katsuma [QC#27078,DEL]
                        if (strSplit.length > 0) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CMPY_CD, strSplit[0]);
                        }
                        if (strSplit.length > 1) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_BR_CD, strSplit[1]);
                        }
                        if (strSplit.length > 2) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CC_CD, strSplit[2]);
                        }
                        if (strSplit.length > 3) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_ACCT_CD, strSplit[3]);
                        }
                        if (strSplit.length > 4) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_PROD_CD, strSplit[4]);
                        }
                        if (strSplit.length > 5) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CH_CD, strSplit[5]);
                        }
                        if (strSplit.length > 6) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_AFFL_CD, strSplit[6]);
                        }
                        if (strSplit.length > 7) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_PROJ_CD, strSplit[7]);
                        }
                        if (strSplit.length > 8) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_EXTN_CD, strSplit[8]);
                        }
                    }
                    // END   2018/03/22 [QC#20316, ADD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apAcctDescTxt_A1, NFBL2040CommonLogic.getCoaAcctDescTxt(GLBL_CMPY_CD, (String) cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_ACCT_CD)));
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_INV_DESC_TXT, bizMsg.A.no(i).apAcctDescTxt_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_ACCT_DESC_TXT, bizMsg.A.no(i).apAcctDescTxt_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(IF_PROC_STS_CD, ZYPConstant.FLG_OFF_N);
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_INV_AUTH_FLG, ZYPConstant.FLG_OFF_N);
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_JRNL_CPLT_FLG, ZYPConstant.FLG_OFF_N);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_DEAL_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_DEAL_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue()));
                    // START 2018/11/05 [QC#28982, MOD]
                    // cmInvAcctDistNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, BigDecimal.ZERO);
                    // cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, BigDecimal.ZERO);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, BigDecimal.ZERO);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1)) {
                        BigDecimal entFuncNetUnitPrcAmt = NFBCommonBusiness.calcStdAmt(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue(),
                                exchRate, ccyMsg.acctArthTpCd.getValue(), ccyMsg.aftDeclPntDigitNum.getValue());
                        cmInvAcctDistNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, entFuncNetUnitPrcAmt);
                    }
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, BigDecimal.ZERO);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A1)) {
                        BigDecimal entPoDtlFuncNetAmt = NFBCommonBusiness.calcStdAmt(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue(),
                                exchRate, ccyMsg.acctArthTpCd.getValue(), ccyMsg.aftDeclPntDigitNum.getValue());
                        cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, entPoDtlFuncNetAmt);
                    }
                    // END   2018/11/05 [QC#28982, MOD]
                    // QC#14858-Sol#060
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A2)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(CM_STK_IN_PRC_FLG, ZYPConstant.FLG_ON_Y);
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(CM_STK_IN_PRC_FLG, ZYPConstant.FLG_OFF_N);
                    }
                    cmInvAcctDistNonPrimaryKeyMap.put(CSI_ENT_DEAL_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(CSI_ENT_PO_DTL_DEAL_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A2.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(CSI_ENT_FUNC_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entFuncNetUnitPrcAmt_A2.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(CSI_ENT_PO_DTL_FUNC_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlFuncNetAmt_A2.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(EXCH_RATE, exchRate);

                    // START 2018/03/22 [QC#20316, DEL]
                    // if (bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)) {
                    //     cmInvAcctDistNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(LINE_NUM_FORMAT, apVndInvLineNumItem));
                    //     apVndInvLineNumItem++;
                    // } else {
                    //     cmInvAcctDistNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(LINE_NUM_FORMAT, apVndInvLineNumOther));
                    //     apVndInvLineNumOther++;
                    // }
                    // cmInvAcctDistNonPrimaryKeyMap.put(CM_INV_ACCT_DIST_LINE_NUM, String.format(LINE_NUM_FORMAT, cmInvAcctDistLineNum));
                    // END   2018/03/22 [QC#20316, DEL]

                    commitLineNumForInv = commitLineNumForInv + 1;
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_LINE_TP_CD, bizMsg.A.no(i).apLineTpCd_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(MDSE_DESC_SHORT_TXT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).mdseDescShortTxt_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(DEAL_GRS_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()));

                    // START 2018/03/22 [QC#20316, ADD]
                    if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
                        cmInvAcctDistNonPrimaryKeyMap.put(MDSE_CD, bizMsg.A.no(i).xxMdseCd_A1.getValue());
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(MDSE_CD, bizMsg.A.no(i).mdseCd_A1.getValue());
                    }
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_INV_TP_CD, AP_INV_TP_CD_00);
                    // mod start 2022/02/15 QC#57090
                    //if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    //    cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(i).poNum_A1.getValue());
                    // mod start 2022/02/15 QC#57090
                    //} else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT.getValue())) {
                    //    cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, bizMsg.poNum_HT.getValue());
                    // START QC#25902,QC#25190,QC#25141
                    //} else if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H.getValue())) {
                    //    cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, bizMsg.vndRtrnNum_H.getValue()); 
                    // END QC#25902,QC#25190,QC#25141
                    //} else {
                    //    cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, NONE);
                    //}
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(i).poNum_A1.getValue());
                        cmInvAcctDistNonPrimaryKeyMap.put(VND_RTRN_NUM, null);
                    } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).vndRtrnNum_A1)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, null);
                        cmInvAcctDistNonPrimaryKeyMap.put(VND_RTRN_NUM, bizMsg.A.no(i).vndRtrnNum_A1.getValue());
                    } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).poNum_A1)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(0).poNum_A1.getValue());
                        cmInvAcctDistNonPrimaryKeyMap.put(VND_RTRN_NUM, null);
                    } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).vndRtrnNum_A1)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, null);
                        cmInvAcctDistNonPrimaryKeyMap.put(VND_RTRN_NUM, bizMsg.A.no(0).vndRtrnNum_A1.getValue());
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, NONE);
                        cmInvAcctDistNonPrimaryKeyMap.put(VND_RTRN_NUM, null);
                    }
                    // mod end 2022/02/15 QC#57090
                    // START 2020/03/23 [QC#53413, MOD]
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).delyOrdNum_A2)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(DELY_ORD_NUM, bizMsg.A.no(i).delyOrdNum_A2.getValue());
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(DELY_ORD_NUM, bizMsg.delyOrdNum_DH.getValue());
                    }
                    // START 2020/03/23 [QC#53413, MOD]
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_LINE_TP_CD, bizMsg.A.no(i).apLineTpCd_A1.getValue());
                    // END   2018/03/22 [QC#20316, ADD]
                    // START 2018/08/14 [QC#27029-1, ADD]
                    cmInvAcctDistNonPrimaryKeyMap.put(PD_ENT_DEAL_NET_UNIT_PRC_AMT, bizMsg.A.no(i).entDealNetUnitPrcAmt_A3.getValue());
                    // END   2018/08/14 [QC#27029-1, ADD]

                } else {
                    // Already exists primary key in the map
                    BigDecimal poQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(PO_QTY);
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_QTY, poQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue())));
                    cmInvAcctDistNonPrimaryKeyMap.put(PO_QTY, poQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue())));
                    BigDecimal invRcvQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(INV_RCV_QTY);
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_RCV_QTY, invRcvQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).invRcvQty_A1.getValue())));
                    BigDecimal apBillQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_BILL_QTY);
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_BILL_QTY, apBillQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                    BigDecimal apRejQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_REJ_QTY);
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_REJ_QTY, apRejQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apRejQty_A1.getValue())));
                    BigDecimal thisMthFobCostAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(THIS_MTH_FOB_COST_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(THIS_MTH_FOB_COST_AMT, thisMthFobCostAmt.add(BigDecimal.ZERO));
                    BigDecimal acInvTaxAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AC_INV_TAX_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_TAX_AMT, acInvTaxAmt.add(BigDecimal.ZERO));
                    BigDecimal acInvJrnlCostAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AC_INV_JRNL_COST_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, acInvJrnlCostAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    BigDecimal entPoDtlDealNetAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(ENT_PO_DTL_DEAL_NET_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_DEAL_NET_AMT, entPoDtlDealNetAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue())));
                    BigDecimal entFuncNetUnitPrcAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(ENT_FUNC_NET_UNIT_PRC_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, entFuncNetUnitPrcAmt.add(BigDecimal.ZERO));
                    BigDecimal entPoDtlFuncNetAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(ENT_PO_DTL_FUNC_NET_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, entPoDtlFuncNetAmt.add(BigDecimal.ZERO));
                }
            }
            cmInvAcctDistMap.put(pkList, cmInvAcctDistNonPrimaryKeyMap);

            // CM_AP_INV_DTL table primary key values.
            pkList = new ArrayList<String>();
            // START 2018/03/22 [QC#20316, MOD]
            // pkList.add(GLBL_CMPY_CD);
            // pkList.add(bizMsg.apVndCd.getValue());
            // pkList.add(bizMsg.apVndInvNum.getValue());
            // pkList.add(AP_VND_INV_SQ_NUM_00);
            // pkList.add(bizMsg.apVndCd.getValue());
            // pkList.add(bizMsg.apVndInvNum.getValue());
            // QC#14858-Sol#060 text item.
            // if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
            //     pkList.add(bizMsg.A.no(i).xxMdseCd_A1.getValue());
            // } else {
            //     pkList.add(bizMsg.A.no(i).mdseCd_A1.getValue());
            // }
            // pkList.add(bizMsg.delyOrdNum_DH.getValue());
            // pkList.add(AP_INV_TP_CD_00);
            // pkList.add(bizMsg.poNum_HT.getValue());
            // pkList.add(bizMsg.A.no(i).apLineTpCd_A1.getValue());
            pkList.add(GLBL_CMPY_CD);
            pkList.add(bizMsg.apVndCd.getValue());
            pkList.add(bizMsg.apVndInvNum.getValue());
            pkList.add(AP_VND_INV_SQ_NUM_00);
            // START 2019/02/21 [QC#30420, MOD]
            // pkList.add(String.format(AP_VND_INV_LINE_NUM_FORMAT, apVndInvLineNum));
            // apVndInvLineNum++;
            pkList.add(bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
            // END   2019/02/21 [QC#30420, MOD]
            // END   2018/03/22 [QC#20316, MOD]

            // CM_AP_INV_DTL table non primary key values.
            Map<String, Object> cmApInvDtlNonPrimaryKeyMap = cmApInvDtlMap.get(pkList);
            // START 2018/03/16 [QC#24089, MOD]
            // if (bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
            //         || (!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
            //                 && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)
            //                 && bizMsg.A.no(i).apBillQty_A1.getValue().compareTo(BigDecimal.ZERO) > 0
            //                 && bizMsg.A.no(i).xxInvAmt_A1.getValue().compareTo(BigDecimal.ZERO) >= 0)
            //                 || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.TAX)
            //                 || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)) {
            if (bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
                    || (!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
                            && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM))
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.TAX)
                            // START QC#25902,QC#25190,QC#25141
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.MISC)) {
                            // END QC#25902,QC#25190,QC#25141
            // END   2018/03/16 [QC#24089, MOD]
                if (cmApInvDtlNonPrimaryKeyMap == null) {
                    // Not exists primary key in the map
                    cmApInvDtlPkList.add(pkList);
                    cmApInvDtlNonPrimaryKeyMap = new HashMap<String, Object>();
                    cmApInvDtlNonPrimaryKeyMap.put(IMPT_INV_CNSGN_CD, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(CM_LIC_NUM, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_TP_CD, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_FOB_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_FRT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_INS_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_DOM_EXP_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_OTH_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TAX_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TOT_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TOT_INV_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_FOB_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_FRT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_INS_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_DOM_EXP_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_OTH_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TAX_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TOT_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TOT_INV_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TRNST_JRNL_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_STK_IN_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_ASG_DT, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                    //QC#23038 MOD START
                    //cmApInvDtlNonPrimaryKeyMap.put(CM_AP_INV_ASG_CD, ZYPConstant.FLG_ON_Y);
                    cmApInvDtlNonPrimaryKeyMap.put(CM_AP_INV_ASG_CD, ZYPConstant.FLG_OFF_N);
                    //QC#23038 MOD END
                    cmApInvDtlNonPrimaryKeyMap.put(CSTM_ARV_DT, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(APPLY_EXCH_RATE, exchRate);
                    cmApInvDtlNonPrimaryKeyMap.put(STK_IN_DT, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(IMPT_REF_NUM, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(TRNST_JRNL_CPLT_FLG, ZYPConstant.FLG_OFF_N);
                    cmApInvDtlNonPrimaryKeyMap.put(STK_IN_JRNL_CPLT_FLG, ZYPConstant.FLG_OFF_N);
                    cmApInvDtlNonPrimaryKeyMap.put(AP_INV_AUTH_FLG, ZYPConstant.FLG_OFF_N);
                    cmApInvDtlNonPrimaryKeyMap.put(AP_JRNL_CPLT_FLG, ZYPConstant.FLG_OFF_N);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_RCV_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).invRcvQty_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(VND_REF_NUM, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(VND_FOC_TP_CD, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(ORIG_SC_FOB_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ORIG_SC_FRT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ORIG_SC_INS_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ORIG_SC_OTH_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(OC_FRT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(OC_INS_OTH_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(OC_UNIT_EXT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(OC_EXT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(SC_UNIT_EXT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(SC_EXT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(PO_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(THIS_MTH_FOB_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ENT_DEAL_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(ENT_PO_DTL_DEAL_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue()));
                    // START 2018/11/05 [QC#28982, MOD]
                    // cmApInvDtlNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, BigDecimal.ZERO);
                    // cmApInvDtlNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, BigDecimal.ZERO);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1)) {
                        BigDecimal entFuncNetUnitPrcAmt = NFBCommonBusiness.calcStdAmt(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue(),
                                exchRate, ccyMsg.acctArthTpCd.getValue(), ccyMsg.aftDeclPntDigitNum.getValue());
                        cmApInvDtlNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, entFuncNetUnitPrcAmt);
                    }
                    cmApInvDtlNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, BigDecimal.ZERO);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A1)) {
                        BigDecimal entPoDtlFuncNetAmt = NFBCommonBusiness.calcStdAmt(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue(),
                                exchRate, ccyMsg.acctArthTpCd.getValue(), ccyMsg.aftDeclPntDigitNum.getValue());
                        cmApInvDtlNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, entPoDtlFuncNetAmt);
                    }
                    // END   2018/11/05 [QC#28982, MOD]
                    // QC#14858-Sol#060
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A2)) {
                        cmApInvDtlNonPrimaryKeyMap.put(CM_STK_IN_PRC_FLG, ZYPConstant.FLG_ON_Y);
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(CM_STK_IN_PRC_FLG, ZYPConstant.FLG_OFF_N);
                    }
                    cmApInvDtlNonPrimaryKeyMap.put(CSI_ENT_DEAL_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(CSI_ENT_PO_DTL_DEAL_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A2.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(CSI_ENT_FUNC_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entFuncNetUnitPrcAmt_A2.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(CSI_ENT_PO_DTL_FUNC_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlFuncNetAmt_A2.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(EXCH_RATE, exchRate);
                    // QC#19261
                    // START 2018/03/22 [QC#20316, DEL]
                    // cmApInvDtlNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(LINE_NUM_FORMAT, apVndInvLineNum));
                    // apVndInvLineNum++;
                    // END   2018/03/22 [QC#20316, DEL]
                    cmApInvDtlNonPrimaryKeyMap.put(PO_ORD_DTL_LINE_NUM, bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                    commitLineNumForApInv = commitLineNumForApInv + 1;
                    cmApInvDtlNonPrimaryKeyMap.put(MDSE_DESC_SHORT_TXT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).mdseDescShortTxt_A1.getValue()));

                    // START 2018/03/22 [QC#20316, ADD]
                    cmApInvDtlNonPrimaryKeyMap.put(AP_VND_CD, bizMsg.apVndCd.getValue());
                    cmApInvDtlNonPrimaryKeyMap.put(AP_VND_INV_NUM, bizMsg.apVndInvNum.getValue());
                    if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
                        cmApInvDtlNonPrimaryKeyMap.put(MDSE_CD, bizMsg.A.no(i).xxMdseCd_A1.getValue());
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(MDSE_CD, bizMsg.A.no(i).mdseCd_A1.getValue());
                    }
                    cmApInvDtlNonPrimaryKeyMap.put(AP_INV_TP_CD, AP_INV_TP_CD_00);
                    // mod start 2022/02/15 QC#57090
                    //if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    //    cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(i).poNum_A1.getValue());
                    //    // START 2018/11/08 J.Kim [QC#23037,ADD]
                    //    cmInvAcctDistNonPrimaryKeyMap.put(PO_ORD_DTL_LINE_NUM, bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                    //    // END 2018/11/08 J.Kim [QC#23037,ADD]
                    //} else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT.getValue())) {
                    //    cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, bizMsg.poNum_HT.getValue());
                    //// START QC#25902,QC#25190,QC#25141
                    //}  else if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H.getValue())) {
                    //    cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, bizMsg.vndRtrnNum_H.getValue());
                    // END QC#25902,QC#25190,QC#25141
                    //} else {
                    //    cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, NONE);
                    //}
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(i).poNum_A1.getValue());
                        cmApInvDtlNonPrimaryKeyMap.put(VND_RTRN_NUM, null);
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_ORD_DTL_LINE_NUM, bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                    } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).vndRtrnNum_A1)) {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, null);
                        cmApInvDtlNonPrimaryKeyMap.put(VND_RTRN_NUM, bizMsg.A.no(i).vndRtrnNum_A1.getValue());
                    } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).poNum_A1)) {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(0).poNum_A1.getValue());
                        cmApInvDtlNonPrimaryKeyMap.put(VND_RTRN_NUM, null);
                    } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).vndRtrnNum_A1)) {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, null);
                        cmApInvDtlNonPrimaryKeyMap.put(VND_RTRN_NUM, bizMsg.A.no(0).vndRtrnNum_A1.getValue());
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, NONE);
                        cmApInvDtlNonPrimaryKeyMap.put(VND_RTRN_NUM, null);
                    }
                    // mod end 2022/02/15 QC#57090
                    // START 2020/03/23 [QC#53413, MOD]
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).delyOrdNum_A2)) {
                        cmApInvDtlNonPrimaryKeyMap.put(DELY_ORD_NUM, bizMsg.A.no(i).delyOrdNum_A2.getValue());
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(DELY_ORD_NUM, bizMsg.delyOrdNum_DH.getValue());
                    }
                    // END   2020/03/23 [QC#53413, MOD]
                    // END   2018/03/22 [QC#20316, ADD]

                    // START 2019/02/21 [QC#30420, ADD]
                    cmApInvDtlNonPrimaryKeyMap.put(AP_LINE_TP_CD, bizMsg.A.no(i).apLineTpCd_A1.getValue());
                    // END   2019/02/21 [QC#30420, ADD]
                
                } else {
                    // Already exists primary key in the map
                    BigDecimal poQty = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(PO_QTY);
                    BigDecimal apBillQty = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_QTY);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_QTY, apBillQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(PO_QTY, poQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue())));
                    BigDecimal invRcvQty = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_RCV_QTY);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_RCV_QTY, invRcvQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).invRcvQty_A1.getValue())));
                    BigDecimal lineAmt = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_FOB_COST_AMT);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_FOB_COST_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TOT_COST_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TOT_INV_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_FOB_COST_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TOT_COST_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TOT_INV_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                }
            }
            cmApInvDtlMap.put(pkList, cmApInvDtlNonPrimaryKeyMap);
        }

        // START 2019/02/22 [QC#30420, ADD]
        removeNotInvoicedLineForDist(cmInvAcctDistPkList, cmInvAcctDistMap);
        renumberInvLineNumber(cmInvAcctDistPkList, cmInvAcctDistMap);
        // END   2019/02/22 [QC#30420, ADD]

        EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
        int digitApInvDescTxt = getDigitApInvDescTxt.getDigit();
        for (int i = 0; i < cmInvAcctDistPkList.size(); i++) {
            List<String> pkList = cmInvAcctDistPkList.get(i);

            // START 2019/01/29 [QC#30056, ADD]
            Map<String, Object> cmInvAcctDistNonPrimaryKeyMap = (Map<String, Object>) cmInvAcctDistMap.get(pkList);
            // BigDecimal lineAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(LINE_AMT);
            // BigDecimal invQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_BILL_QTY);
            // if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
            //     continue;
            // }
            // END   2019/02/22 [QC#30420, DEL]
            // END   2019/01/29 [QC#30056, ADD]

            CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.glblCmpyCd, pkList.get(0));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, pkList.get(1));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvNum, pkList.get(2));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvSqNum, pkList.get(3));
            // START 2018/03/22 [QC#20316, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvLineNum, pkList.get(4));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum, pkList.get(5));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseCd, pkList.get(4));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvTpCd, pkList.get(5));
            // if (ZYPCommonFunc.hasValue(pkList.get(6))) {
            //     ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, pkList.get(6));
            // } else {
            //     ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, NONE);
            // }
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.delyOrdNum, pkList.get(7));
            // END   2018/03/22 [QC#20316, MOD]

            // START 2019/01/29 [QC#30056, DEL]
//            Map<String, Object> cmInvAcctDistNonPrimaryKeyMap = (Map<String, Object>) cmInvAcctDistMap.get(pkList);
            // END   2019/01/29 [QC#30056, DEL]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invTpCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(INV_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(PO_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invRcvQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(INV_RCV_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apBillQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_BILL_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apRejQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_REJ_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.uomCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(UOM_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.thisMthFobCostAmt, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(THIS_MTH_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvTaxAmt, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AC_INV_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvJrnlCostAmt, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AC_INV_JRNL_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invAsgDt, (String)cmInvAcctDistNonPrimaryKeyMap.get(INV_ASG_DT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.stkInDt, (String)cmInvAcctDistNonPrimaryKeyMap.get(STK_IN_DT));
            String apInvDescTxtFullLength = (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_DESC_TXT);
            if (ZYPCommonFunc.hasValue(apInvDescTxtFullLength) && apInvDescTxtFullLength.length() > digitApInvDescTxt) {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, S21StringUtil.subStringByLength(apInvDescTxtFullLength, 0, digitApInvDescTxt));
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_DESC_TXT));
            }
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.csmpInvNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(CSMP_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.serNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(SER_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.contrNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(CONTR_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.custDlrCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CUST_DLR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocFirstLineAddr, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocFlNm, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_FL_NM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocCtyAddr, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocStCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_ST_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocPostCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_POST_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invLineDescTxt, (String)cmInvAcctDistNonPrimaryKeyMap.get(INV_LINE_DESC_TXT));
            // START 2018/03/22 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaCmpyCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_CMPY_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaBrCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_BR_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaCcCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_CC_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaAcctCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_ACCT_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaProdCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_PROD_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaChCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_CH_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaAfflCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_AFFL_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaProjCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_PROJ_CD));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaExtnCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCmpyCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaBrCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCcCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAcctCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProdCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaChCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAfflCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProjCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaExtnCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaCmpyCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaBrCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaCcCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaAcctCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaProdCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaChCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaAfflCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaProjCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaExtnCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_EXTN_CD));
            // END   2018/03/22 [QC#20316, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apAcctDescTxt, (String)cmInvAcctDistNonPrimaryKeyMap.get(AP_ACCT_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.ifProcStsCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(IF_PROC_STS_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvAuthFlg, (String)cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_AUTH_FLG));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apJrnlCpltFlg, (String)cmInvAcctDistNonPrimaryKeyMap.get(AP_JRNL_CPLT_FLG));
            // QC#14858-Sol#060
            if (ZYPConstant.FLG_ON_Y.equals((String) cmInvAcctDistNonPrimaryKeyMap.get(CM_STK_IN_PRC_FLG))) {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entDealNetUnitPrcAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlDealNetAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_PO_DTL_DEAL_NET_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entFuncNetUnitPrcAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_FUNC_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlFuncNetAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_PO_DTL_FUNC_NET_AMT));
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entDealNetUnitPrcAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlDealNetAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(ENT_PO_DTL_DEAL_NET_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entFuncNetUnitPrcAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(ENT_FUNC_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlFuncNetAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(ENT_PO_DTL_FUNC_NET_AMT));
            }
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.exchRate, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(EXCH_RATE));
            // START 2018/03/22 [QC#20316, DEL]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvLineNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(AP_VND_INV_LINE_NUM));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(CM_INV_ACCT_DIST_LINE_NUM));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apLineTpCd, pkList.get(8));
            // START 2018/03/22 [QC#20316, DEL]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseDescShortTxt, (String) cmInvAcctDistNonPrimaryKeyMap.get(MDSE_DESC_SHORT_TXT));

            // START 2018/03/22 [QC#20316, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseCd, (String) cmInvAcctDistNonPrimaryKeyMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvTpCd, (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_TP_CD));
            // START QC#25902,QC#25190,QC#25141
            // mod start 2022/02/15 QC#57090
            //if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(PO_NUM));
            //} else {
            //    ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.vndRtrnNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(PO_NUM));
            //}
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(PO_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.vndRtrnNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(VND_RTRN_NUM));
            // mod end 2022/02/15 QC#57090
            // END QC#25902,QC#25190,QC#25141
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.delyOrdNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(DELY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apLineTpCd, (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_LINE_TP_CD));
            // END   2018/03/22 [QC#20316, ADD]
            // START 2018/11/08 J.Kim [QC#23037,ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poOrdDtlLineNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(PO_ORD_DTL_LINE_NUM));
            // END 2018/11/08 J.Kim [QC#23037,ADD]

            // Create new invoice record.
            EZDTBLAccessor.create(cmInvAcctDistTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctDistTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0073E, new String[] {cmInvAcctDistTMsg.getTableName(), cmInvAcctDistTMsg.getReturnCode() });
                return;
            }
        }

        // START 2018/08/14 [QC#27029-1, MOD]
        // if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
        //     NFBL2040CommonLogic.insertVarianceData(bizMsg, cmInvAcctDistMap, cmInvAcctDistPkList, commitLineNumForInv, exchRate, true);
        // }
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) || CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            // START 2019/02/07 [QC#30136, MOD]
            // NFBL2040CommonLogic.insertVarianceData(bizMsg, cmInvAcctDistMap, cmInvAcctDistPkList, commitLineNumForInv, exchRate, true);
            if (!isBlanketPO) {
                NFBL2040CommonLogic.insertVarianceData(bizMsg, cmInvAcctDistMap, cmInvAcctDistPkList, commitLineNumForInv, exchRate, true);
            }
            // END   2019/02/07 [QC#30136, MOD]
        }
        // END   2018/08/14 [QC#27029-1, MOD]

        // START 2019/02/22 [QC#30420, ADD]
        removeNotInvoicedLineForDtl(cmApInvDtlPkList, cmApInvDtlMap);
        renumberInvLineNumber(cmApInvDtlPkList, cmApInvDtlMap);
        // END   2019/02/22 [QC#30420, ADD]

        for (int i = 0; i < cmApInvDtlPkList.size(); i++) {
            List<String> pkList = cmApInvDtlPkList.get(i);

            // START 2019/01/29 [QC#30056, ADD]
            Map<String, Object> cmApInvDtlNonPrimaryKeyMap = (Map<String, Object>) cmApInvDtlMap.get(pkList);
            // START 2019/02/22 [QC#30420, DEL]
            // BigDecimal lineAmt = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_FOB_COST_AMT);
            // BigDecimal invQty = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_QTY);
            // if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
            //     continue;
            // }
            // END   2019/02/22 [QC#30420, DEL]
            // END   2019/01/29 [QC#30056, ADD]

            CM_AP_INV_DTLTMsg cmApInvDtlTMsg = new CM_AP_INV_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.glblCmpyCd, pkList.get(0));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndCd, pkList.get(1));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvNum, pkList.get(2));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvSqNum, pkList.get(3));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvLineNum, pkList.get(4));
            // START 2018/03/22 [QC#20316, MOD]
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndCd, pkList.get(4));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndInvNum, pkList.get(5));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.mdseCd, pkList.get(6));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.delyOrdNum, pkList.get(7));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apInvTpCd, pkList.get(8));
            // if (ZYPCommonFunc.hasValue(pkList.get(9))) {
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poNum, pkList.get(9));
            // } else {
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poNum, NONE);
            // }
            // END   2018/03/22 [QC#20316, MOD]

            // START 2019/01/29 [QC#30056, DEL]
//            Map<String, Object> cmApInvDtlNonPrimaryKeyMap = (Map<String, Object>) cmApInvDtlMap.get(pkList);
            // END   2019/01/29 [QC#30056, DEL]
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.imptInvCnsgnCd, (String)cmApInvDtlNonPrimaryKeyMap.get(IMPT_INV_CNSGN_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.cmLicNum, (String)cmApInvDtlNonPrimaryKeyMap.get(CM_LIC_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.invTpCd, (String)cmApInvDtlNonPrimaryKeyMap.get(INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.invQty, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_QTY));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcFobCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcFrtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcInsCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_INS_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcDomExpCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_DOM_EXP_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcOthCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcTaxAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcTotCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_TOT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcTotInvAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_TOT_INV_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScFobCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScFrtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScInsCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_INS_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScDomExpCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_DOM_EXP_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScOthCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScTaxAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScTotCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_TOT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScTotInvAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_TOT_INV_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScTrnstJrnlAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_TRNST_JRNL_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScStkInCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_STK_IN_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.invAsgDt, (String)cmApInvDtlNonPrimaryKeyMap.get(INV_ASG_DT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.cmApInvAsgCd, (String)cmApInvDtlNonPrimaryKeyMap.get(CM_AP_INV_ASG_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.cstmArvDt, (String)cmApInvDtlNonPrimaryKeyMap.get(CSTM_ARV_DT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.applyExchRate, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(APPLY_EXCH_RATE));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.stkInDt, (String)cmApInvDtlNonPrimaryKeyMap.get(STK_IN_DT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.imptRefNum, (String)cmApInvDtlNonPrimaryKeyMap.get(IMPT_REF_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.trnstJrnlCpltFlg, (String)cmApInvDtlNonPrimaryKeyMap.get(TRNST_JRNL_CPLT_FLG));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.stkInJrnlCpltFlg, (String)cmApInvDtlNonPrimaryKeyMap.get(STK_IN_JRNL_CPLT_FLG));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apInvAuthFlg, (String)cmApInvDtlNonPrimaryKeyMap.get(AP_INV_AUTH_FLG));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apJrnlCpltFlg, (String)cmApInvDtlNonPrimaryKeyMap.get(AP_JRNL_CPLT_FLG));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.invRcvQty, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_RCV_QTY));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndRefNum, (String)cmApInvDtlNonPrimaryKeyMap.get(VND_REF_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndFocTpCd, (String)cmApInvDtlNonPrimaryKeyMap.get(VND_FOC_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.origScFobCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ORIG_SC_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.origScFrtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ORIG_SC_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.origScInsCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ORIG_SC_INS_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.origScOthCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ORIG_SC_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.ocFrtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(OC_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.ocInsOthCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(OC_INS_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.ocUnitExtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(OC_UNIT_EXT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.ocExtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(OC_EXT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.scUnitExtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(SC_UNIT_EXT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.scExtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(SC_EXT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poQty, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(PO_QTY));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.thisMthFobCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(THIS_MTH_FOB_COST_AMT));

            // START 2018/06/19 [QC#26169, MOD]
            // QC#14858-Sol#060
            // if(ZYPConstant.FLG_ON_Y.equals((String) cmApInvDtlNonPrimaryKeyMap.get(CM_STK_IN_PRC_FLG))){
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entDealNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(CSI_ENT_DEAL_NET_UNIT_PRC_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlDealNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(CSI_ENT_PO_DTL_DEAL_NET_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entFuncNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(CSI_ENT_FUNC_NET_UNIT_PRC_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlFuncNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(CSI_ENT_PO_DTL_FUNC_NET_AMT));
            // }else {
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entDealNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_DEAL_NET_UNIT_PRC_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlDealNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_PO_DTL_DEAL_NET_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entFuncNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_FUNC_NET_UNIT_PRC_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlFuncNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_PO_DTL_FUNC_NET_AMT));
            // }
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entDealNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlDealNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_PO_DTL_DEAL_NET_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entFuncNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_FUNC_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlFuncNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_PO_DTL_FUNC_NET_AMT));
            // END   2018/06/19 [QC#26169, MOD]

            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.exchRate, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(EXCH_RATE));
            // START 2018/03/22 [QC#20316, DEL]
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvLineNum, (String)cmApInvDtlNonPrimaryKeyMap.get(AP_VND_INV_LINE_NUM));
            // START 2018/03/22 [QC#20316, DEL]
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poOrdDtlLineNum, (String)cmApInvDtlNonPrimaryKeyMap.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.mdseDescShortTxt, (String) cmApInvDtlNonPrimaryKeyMap.get(MDSE_DESC_SHORT_TXT));

            // START 2018/03/22 [QC#20316, ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndCd, (String) cmApInvDtlNonPrimaryKeyMap.get(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndInvNum, (String) cmApInvDtlNonPrimaryKeyMap.get(AP_VND_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.mdseCd, (String) cmApInvDtlNonPrimaryKeyMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.delyOrdNum, (String) cmApInvDtlNonPrimaryKeyMap.get(DELY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apInvTpCd, (String) cmApInvDtlNonPrimaryKeyMap.get(AP_INV_TP_CD));
            // START QC#25902,QC#25190,QC#25141
            // mod start 2022/02/15 QC#57090
            //if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            //    ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poNum, (String) cmApInvDtlNonPrimaryKeyMap.get(PO_NUM));
            //} else {
            //    ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndRtrnNum, (String) cmApInvDtlNonPrimaryKeyMap.get(PO_NUM));
            //}
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poNum, (String) cmApInvDtlNonPrimaryKeyMap.get(PO_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndRtrnNum, (String) cmApInvDtlNonPrimaryKeyMap.get(VND_RTRN_NUM));
            // mod end 2022/02/15 QC#57090
            // END QC#25902,QC#25190,QC#25141
            // END   2018/03/22 [QC#20316, ADD]

            // Create new invoice record.
            EZDTBLAccessor.create(cmApInvDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmApInvDtlTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0073E, new String[] {cmApInvDtlTMsg.getTableName(), cmApInvDtlTMsg.getReturnCode() });
                return;
            }
            if (acctInvStsCd.equals(ACCT_INV_STS.AUTHORIZED)) {
                callPoStsUpdateAPI(bizMsg, cmApInvDtlTMsg);
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.docMgtDocId)
                && ZYPCommonFunc.hasValue(bizMsg.docMgtCatgCd)) {
            String attDocTpCd = null;
            DOC_MGT_CATGTMsg docMgtCatgTMsg = new DOC_MGT_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(docMgtCatgTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(docMgtCatgTMsg.docMgtCatgCd, bizMsg.docMgtCatgCd);
            docMgtCatgTMsg = (DOC_MGT_CATGTMsg) S21FastTBLAccessor.findByKey(docMgtCatgTMsg);
            if (docMgtCatgTMsg != null) {
                attDocTpCd = docMgtCatgTMsg.attDocTpCd.getValue();
            }

            callApiForTherefore(bizMsg
                    , bizMsg.apVndCd.getValue()
                    , bizMsg.apVndInvNum.getValue()
                    , bizMsg.docMgtCatgCd.getValue()
                    , bizMsg.docMgtDocId.getValue()
                    , attDocTpCd
                    , null
                    , true);
            if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
                return;
            }
        }

        // START 2019/07/18 [QC#51675, ADD]
        bizMsg.xxWrnSkipFlg.clear();
        // END   2019/07/18 [QC#51675, ADD]

        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsCd, acctInvStsCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsDescTxt, NFBL2040CommonLogic.getAcctInvStsDescTxt(acctInvStsCd));
        if (NFBL2040CommonLogic.refreshData(bizMsg)) {
            return;
        }
        bizMsg.setMessageInfo(ZZM8100I);
        // START 2018/01/04 [QC#22143, DEL]
        // EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_CMN_Submit================================END", this);
        // END   2018/01/04 [QC#22143, DEL]
    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_Release
     * <dd>The method explanation: Submit button
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Release(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Release================================START", this);
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // Accounting Invoice Status Code
        String acctInvStsCd = EMPTY_STRING;

        List<Integer> checkedRows = new ArrayList<Integer>();
        List<Integer> uncheckedRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxChkBox_H1.getValue())) {
                // START 2020/03/16 [QC#55993, MOD]
                if (!ZYPCommonFunc.hasValue(bizMsg.H.no(i).pmtHldRelPsnCd_H1) && !ZYPCommonFunc.hasValue(bizMsg.H.no(i).pmtHldRelDt_H1)) {
                    checkedRows.add(i);
                }
                // END   2020/03/16 [QC#55993, MOD]
            } else if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                uncheckedRows.add(i);
            }
        }
        if (checkedRows.isEmpty()) {
            bizMsg.setMessageInfo(NFBM0017E, new String[] {"Hold Release" });
            return;
        }

        // START 2018/08/02 [QC#27025-1, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.ezUpTime) && ZYPCommonFunc.hasValue(bizMsg.ezUpTimeZone)) {
            CM_AP_INV_HDRTMsg outMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndCd, bizMsg.apVndCd);
            ZYPEZDItemValueSetter.setValue(outMsg.apInvTpCd, AP_INV_TP_CD_00);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndInvNum, bizMsg.apVndInvNum);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            outMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(outMsg);
            if (outMsg != null && !ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NZZM0003E");
                return;
            }
        }
        // END   2018/08/02 [QC#27025-1, ADD]

        // START 2018/03/08 [QC#24275, ADD]
        if (!checkHoldRelease(bizMsg, checkedRows)) {
            return;
        }
        // END   2018/03/08 [QC#24275, ADD]

        // Detail
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
            // START 2018/02/27 [QC#23505, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            // END   2018/02/27 [QC#23505, MOD]
            // START 2018/03/28 [QC#24277, ADD]
            if (EMPTY_AP_VND_INV_LINE_NUM.equals(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
            }
            // END   2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1.getValue());
            cmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvPmtHldTMsg);
            if (cmInvPmtHldTMsg != null) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxChkBox_H1.getValue())) {
                    if (ZYPConstant.FLG_ON_Y.equals(cmInvPmtHldTMsg.pmtHldFlg.getValue())) {
                        // Release
                        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, USER_ID);
                        // START 2018/10/29 [QC#28988, MOD]
                        // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, ZYPDateUtil.getSalesDate());
                        // END   2018/10/29 [QC#28988, MOD]
                        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue());
                        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, bizMsg.H.no(i).pmtHldRelCmntTxt_H1.getValue());
                        EZDTBLAccessor.update(cmInvPmtHldTMsg);
                        
                    }
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).pmtHldRelPsnCd_H1, USER_ID);
                    // START 2018/10/29 [QC#28988, MOD]
                    // ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).pmtHldRelDt_H1, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).pmtHldRelDt_H1, ZYPDateUtil.getSalesDate());
                    // END   2018/10/29 [QC#28988, MOD]
                } else {
                    if (ZYPConstant.FLG_OFF_N.equals(cmInvPmtHldTMsg.pmtHldFlg.getValue())) {
                        // Hold
                        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                        // START 2018/10/29 [QC#28988, MOD]
                        // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, ZYPDateUtil.getSalesDate());
                        // END   2018/10/29 [QC#28988, MOD]
                        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, USER_ID);
                        EZDTBLAccessor.update(cmInvPmtHldTMsg);
                    }
                }
            }
        }
        // Header
        if (uncheckedRows.isEmpty()) {
            // START 2018/03/22 [QC#20316, DEL]
//            // All the hold reasons is released.
//            CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
//            // START 2018/02/27 [QC#23505, MOD]
//            // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
//            // END   2018/02/27 [QC#23505, MOD]
//            cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvAcctHdrTMsg);
//            if (cmInvAcctHdrTMsg != null) {
//                if (ZYPConstant.FLG_ON_Y.equals(cmInvAcctHdrTMsg.pmtHldFlg.getValue())) {
//                    // Release
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, USER_ID);
//                    if (bizMsg.H.getValidCount() > 0) {
//                        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, bizMsg.H.no(0).pmtHldRelRsnCd_H1.getValue());
//                        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, bizMsg.H.no(0).pmtHldRelCmntTxt_H1.getValue());
//                    }
//                    // START 2017/12/28 [QC#22143, MOD]
//                    // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
//                    // END   2017/12/28 [QC#22143, MOD]
//                    EZDTBLAccessor.update(cmInvAcctHdrTMsg);
//                }
//            }
            // END   2018/03/22 [QC#20316, DEL]

            CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
            // START 2018/02/27 [QC#23505, MOD]
            // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            // END   2018/02/27 [QC#23505, MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
            cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
            if (cmApInvHdrTMsg != null) {
                if (ZYPConstant.FLG_ON_Y.equals(cmApInvHdrTMsg.pmtHldFlg.getValue())) {
                    // Release
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                    // START 2018/03/22 [QC#20316, ADD]
                    // START 2018/07/30 [QC#27025-1, MOD]
                    // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.AUTHORIZED);
                    // END   2018/07/30 [QC#27025-1, MOD]
                    // END   2018/03/22 [QC#20316, ADD]
                    EZDTBLAccessor.update(cmApInvHdrTMsg);
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_OFF_N);
            // START 2017/12/28 [QC#22143, MOD]
            // acctInvStsCd = ACCT_INV_STS.AUTHORIZED;
            // START 2018/07/30 [QC#27025-1, MOD]
            // acctInvStsCd = ACCT_INV_STS.ENTERED;
            acctInvStsCd = ACCT_INV_STS.AUTHORIZED;
            // END   2018/07/30 [QC#27025-1, MOD]
            // START 2017/12/28 [QC#22143, MOD]
        } else {
            // START 2018/03/22 [QC#20316, DEL]
//            CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
//            // START 2018/02/27 [QC#23505, MOD]
//            // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
//            // END   2018/02/27 [QC#23505, MOD]
//            cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvAcctHdrTMsg);
//            if (cmInvAcctHdrTMsg != null) {
//                if (ZYPConstant.FLG_OFF_N.equals(cmInvAcctHdrTMsg.pmtHldFlg.getValue())) {
//                    // Hold
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldPsnCd, USER_ID);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
//                    EZDTBLAccessor.update(cmInvAcctHdrTMsg);
//                }
//            }
            // END   2018/03/22 [QC#20316, DEL]

            CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
            // START 2018/02/27 [QC#23505, MOD]
            // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            // END   2018/02/27 [QC#23505, MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
            cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
            if (cmApInvHdrTMsg != null) {
                if (ZYPConstant.FLG_OFF_N.equals(cmApInvHdrTMsg.pmtHldFlg.getValue())) {
                    // Hold
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                    // START 2018/03/22 [QC#20316, ADD]
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
                    // END   2018/03/22 [QC#20316, ADD]
                    EZDTBLAccessor.update(cmApInvHdrTMsg);
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_ON_Y);
            acctInvStsCd = ACCT_INV_STS.ENTERED;
        }
        // START 2018/02/27 [QC#23505, MOD]
        if (acctInvStsCd.equals(ACCT_INV_STS.AUTHORIZED)) {
            // CM_AP_INV_DTLTMsgArray cmApInvDtlTMsgArray = NFBL2040Query.getInstance().findCmApInvDtlList(GLBL_CMPY_CD, bizMsg.apVndCd.getValue(), bizMsg.apVndInvNum.getValue(), AP_VND_INV_SQ_NUM_00);
            CM_AP_INV_DTLTMsgArray cmApInvDtlTMsgArray = NFBL2040Query.getInstance().findCmApInvDtlList(GLBL_CMPY_CD, bizMsg.apVndCd.getValue(), bizMsg.apVndInvNum.getValue(), bizMsg.apVndInvSqNum.getValue());
            for (int i = 0; i < cmApInvDtlTMsgArray.length(); i++) {
                CM_AP_INV_DTLTMsg cmApInvDtlTMsg = cmApInvDtlTMsgArray.no(i);
                callPoStsUpdateAPI(bizMsg, cmApInvDtlTMsg);
            }
        }
        // END   2018/02/27 [QC#23505, MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsCd, acctInvStsCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsDescTxt, NFBL2040CommonLogic.getAcctInvStsDescTxt(acctInvStsCd));

        if (!NFBL2040CommonLogic.refreshData(bizMsg)) {
            return;
        }
        bizMsg.setMessageInfo(ZZM8100I);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Release================================END", this);
    }

    private void doProcess_NFBL2040Scrn00_InvoiceCancel(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // START 2018/08/02 [QC#27025-1, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.ezUpTime) && ZYPCommonFunc.hasValue(bizMsg.ezUpTimeZone)) {
            CM_AP_INV_HDRTMsg outMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndCd, bizMsg.apVndCd);
            ZYPEZDItemValueSetter.setValue(outMsg.apInvTpCd, AP_INV_TP_CD_00);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndInvNum, bizMsg.apVndInvNum);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            outMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(outMsg);
            if (outMsg != null && !ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NZZM0003E");
                return;
            }
        }
        // END   2018/08/02 [QC#27025-1, ADD]

        // START 2018/11/20 [QC#28660, ADD]
        if (!checkAssociatedCreditMemo(bizMsg)) {
            return;
        }
        // END   2018/11/20 [QC#28660, ADD]

        // Therefore Hold Relese
        int hldCnt = 0;
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (PMT_HLD.THEREFORE.equals(bizMsg.H.no(i).pmtHldCd_H1.getValue())) {
                CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
                // START 2018/03/28 [QC#24277, ADD]
                // START 2018/10/29 [QC#28988, MOD]
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
                if (EMPTY_AP_VND_INV_LINE_NUM.equals(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
                }
                // END   2018/10/29 [QC#28988, MOD]
                // END   2018/03/28 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, PMT_HLD_CD_030);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, PMT_HLD_RSN_CD_031);
                cmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(cmInvPmtHldTMsg);
                if (cmInvPmtHldTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, USER_ID);
                    // START 2018/10/29 [QC#28988, MOD]
                    // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, ZYPDateUtil.getSalesDate());
                    // END   2018/10/29 [QC#28988, MOD]
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, PMT_HLD_RSN_CD_000);
                    EZDTBLAccessor.update(cmInvPmtHldTMsg);
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).xxChkBox_H1, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).pmtHldRelPsnCd_H1, cmInvPmtHldTMsg.pmtHldRelPsnCd);
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).pmtHldRelDt_H1, cmInvPmtHldTMsg.pmtHldRelDt);
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).pmtHldRelRsnCd_H1, cmInvPmtHldTMsg.pmtHldRelRsnCd);
                }
            } else {
                if (!ZYPCommonFunc.hasValue(bizMsg.H.no(i).pmtHldFlg_H1.getValue())) {
                    hldCnt++;
                }
            }
        }

        // START 2018/03/22 [QC#20316, MOD]
        // Update CM_INV_ACCT_HDR table record.
//        CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd);
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum);
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
//        cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(cmInvAcctHdrTMsg);
//        if (cmInvAcctHdrTMsg != null) {
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.acctInvStsCd, ACCT_INV_STS.CANCEL);
//            if (ZYPConstant.FLG_ON_Y.equals(cmInvAcctHdrTMsg.pmtHldFlg.getValue())
//                    && bizMsg.H.getValidCount() > 0) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, bizMsg.H.no(0).pmtHldRelPsnCd_H1);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, bizMsg.H.no(0).pmtHldRelDt_H1);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, bizMsg.H.no(0).pmtHldRelRsnCd_H1);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, bizMsg.H.no(0).pmtHldRelCmntTxt_H1);
//            }
//            EZDTBLAccessor.update(cmInvAcctHdrTMsg);
//            if (!RTNCD_NORMAL.equals(cmInvAcctHdrTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NFBM0224E, new String[] {"Invoice Account Header", cmInvAcctHdrTMsg.getReturnCode() });
//                return;
//            }
        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
        cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
        if (cmApInvHdrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.CANCEL);
            if (ZYPConstant.FLG_ON_Y.equals(cmApInvHdrTMsg.pmtHldFlg.getValue())
                    && bizMsg.H.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
            }
            EZDTBLAccessor.update(cmApInvHdrTMsg);
            if (!RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0224E, new String[] {"CM AP Invoice Header", cmApInvHdrTMsg.getReturnCode() });
                return;
            }
            // END   2018/03/22 [QC#20316, MOD]

            //Update CM_INV_ACCT_DIST table record.
            CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
            cmInvAcctDistTMsg.setSQLID("001");
            cmInvAcctDistTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
            cmInvAcctDistTMsg.setConditionValue("apVndCd01", bizMsg.apVndCd.getValue());
            cmInvAcctDistTMsg.setConditionValue("apVndInvNum01", bizMsg.apVndInvNum.getValue());
            cmInvAcctDistTMsg.setConditionValue("apVndInvSqNum01", AP_VND_INV_SQ_NUM_00);
            CM_INV_ACCT_DISTTMsgArray cmInvAcctDistTMsgArray = (CM_INV_ACCT_DISTTMsgArray) EZDTBLAccessor.findByCondition(cmInvAcctDistTMsg);

            // QC#21662 get po_dtl.
            // START 2018/03/22 [QC#20316, MOD]
            // ArrayList<PO_DTLTMsg> poDtlList = NFBL2040CommonLogic.getPoDtlListForUpdateNoWait(cmInvAcctHdrTMsg.poNum.getValue());
            ArrayList<PO_DTLTMsg> poDtlList = NFBL2040CommonLogic.getPoDtlListForUpdateNoWait(cmApInvHdrTMsg.poNum.getValue());
            // END   2018/03/22 [QC#20316, MOD]
            for (int i = 0; i < cmInvAcctDistTMsgArray.getValidCount(); i++) {
                CM_INV_ACCT_DISTTMsg inTMsg = cmInvAcctDistTMsgArray.no(i);
                if (inTMsg.invQty.getValueInt() > 0) {
                    inTMsg = (CM_INV_ACCT_DISTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inTMsg);
                    if (inTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(inTMsg.apRejQty, inTMsg.apBillQty);
                        ZYPEZDItemValueSetter.setValue(inTMsg.apBillQty, BigDecimal.ZERO);
                        EZDTBLAccessor.update(inTMsg);
                        if (!RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NFBM0224E, new String[] {"Invoice Account Distribution", inTMsg.getReturnCode() });
                            return;
                        }
                    }

                    // START 2017/12/21 J.Kim [QC#23039,ADD]
                    if (!ACCT_INV_STS.AUTHORIZED.equals(bizMsg.acctInvStsCd.getValue())) {
                        continue;
                    }
                    // END 2017/12/21 J.Kim [QC#23039,ADD]

                    // START 2018/11/20 [QC#28660, ADD]
                    holdAssociatedInvoice(bizMsg);
                    // END   2018/11/20 [QC#28660, ADD]

                    // QC#21662 Update PO Information
                    String invMdseCd = inTMsg.mdseCd.getValue();
                    BigDecimal invQty = inTMsg.invQty.getValue();
                    // QC#22137 Start
                    BigDecimal apRejQty = inTMsg.apRejQty.getValue();
                    int j = 0;
                    // START 2018/08/28 [QC#27886, MOD]
                    // while (invQty.compareTo(BigDecimal.ZERO) > 0 && j < poDtlList.size()) {
                    while (apRejQty.compareTo(BigDecimal.ZERO) != 0 && j < poDtlList.size()) {
                    // END   2018/08/28 [QC#27886, MOD]
                        PO_DTLTMsg poDtl = poDtlList.get(j);
                        String poMdseCd = poDtl.mdseCd.getValue();
                        BigDecimal poInvQty = poDtl.poInvQty.getValue();

                        // START 2018/08/28 [QC#27886, MOD]
                        CM_AP_INV_DTLTMsg invDtlTMsg = new CM_AP_INV_DTLTMsg();
                        ZYPEZDItemValueSetter.setValue(invDtlTMsg.glblCmpyCd, inTMsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(invDtlTMsg.apVndCd, inTMsg.apVndCd);
                        ZYPEZDItemValueSetter.setValue(invDtlTMsg.apVndInvNum, inTMsg.apVndInvNum);
                        ZYPEZDItemValueSetter.setValue(invDtlTMsg.apVndInvSqNum, inTMsg.apVndInvSqNum);
                        ZYPEZDItemValueSetter.setValue(invDtlTMsg.apVndInvLineNum, inTMsg.apVndInvLineNum);
                        invDtlTMsg = (CM_AP_INV_DTLTMsg) S21FastTBLAccessor.findByKey(invDtlTMsg);
                        if (invDtlTMsg == null) {
                            continue;
                        }

                        String poOrdDtlLineNum = invDtlTMsg.poOrdDtlLineNum.getValue();
                        String poOrdDtlLineNumOfPO = poDtl.poOrdDtlLineNum.getValue();
                        if (!poOrdDtlLineNumOfPO.equals(poOrdDtlLineNum)) {
                            j++;
                            continue;
                        }

                        BigDecimal upInvQty = apRejQty;
                        // START 2018/11/26 [QC#28660, DEL]
                        // if (poInvQty.compareTo(upInvQty) < 0) {
                        //     upInvQty = poInvQty;
                        // }
                        // END   2018/11/26 [QC#28660, DEL]

                        String msgId = NFBL2040CommonLogic.updatePoInformation(
                                  poDtl.poOrdNum.getValue()
                                , poDtl.poOrdDtlLineNum.getValue()
                                , upInvQty.negate()
                                , poMdseCd);
                        if (msgId != null) {
                            bizMsg.setMessageInfo(msgId);
                            break;
                        }
                        j++;
                        // if (invMdseCd.equals(poMdseCd)) {
                        //     BigDecimal upInvQty = apRejQty;
                        //     if (poInvQty.compareTo(upInvQty) < 0) {
                        //         upInvQty = poInvQty;
                        //     }
                        //     // QC#22137 End
                        //     // 2018/07/12 Delete. [QC#27025]
                        //     // 2018/07/26 Update. [QC#27029]
                        //     // START 2018/08/01 [QC#27025-1, MOD]
                        //     String msgId = NFBL2040CommonLogic.updatePoInformation(// 
                        //             poDtl.poOrdNum.getValue() //
                        //             , poDtl.poOrdDtlLineNum.getValue()//
                        //             , upInvQty.negate()//
                        //             , poMdseCd);
                        //     if (msgId != null) {
                        //         bizMsg.setMessageInfo(msgId);
                        //         break;
                        //     }
                        //     // END   2018/08/01 [QC#27025-1, MOD]
                        //     // 2018/07/26 Update end. [QC#27029]
                        //     // 2018/07/12 Delete end. [QC#27025]
                        //     invQty = invQty.add(poInvQty.negate());
                        // }
                        // j++;
                        // END   2018/08/28 [QC#27886, MOD]
                    }
                }
            }
        } else {
            bizMsg.setMessageInfo(NFBM0069E);
            return;
        }

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getAttDataForTherefore(bizMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> resultMap : resultList) {
                callApiForTherefore(bizMsg
                        , bizMsg.apVndCd.getValue()
                        , bizMsg.apVndInvNum.getValue()
                        , (String) resultMap.get(DOC_MGT_CATG_CD)
                        , new BigDecimal((String) resultMap.get(DOC_MGT_DOC_ID))
                        , (String) resultMap.get(ATT_DOC_TP_CD)
                        , (BigDecimal) resultMap.get(ATT_DATA_PK)
                        , false);

                if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
                    return;
                }
            }
        }

        // START 2018/03/22 [QC#20316, MOD]
        // ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsCd, cmInvAcctHdrTMsg.acctInvStsCd);
        // ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsDescTxt, NFBL2040CommonLogic.getAcctInvStsDescTxt(cmInvAcctHdrTMsg.acctInvStsCd.getValue()));
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsCd, cmApInvHdrTMsg.acctInvStsCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsDescTxt, NFBL2040CommonLogic.getAcctInvStsDescTxt(cmApInvHdrTMsg.acctInvStsCd.getValue()));
        // END   2018/03/22 [QC#20316, MOD]

        bizMsg.setMessageInfo(ZZM8100I);

    }

    /**
     * Method name: doProcess_NFBL2040Scrn00_Hold
     * <dd>The method explanation: Hold button
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Hold(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        // Accounting Invoice Status Code
        String acctInvStsCd = EMPTY_STRING;

        List<Integer> checkedRows = new ArrayList<Integer>();
        List<Integer> uncheckedRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                checkedRows.add(i);
            } else {
                uncheckedRows.add(i);
            }
        }
        if (checkedRows.isEmpty()) {
            bizMsg.setMessageInfo(NFBM0238E);
            return;
        }

        if (isDuplicatedHoldLine(bizMsg)) {
            return;
        }

        // Detail
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if( !ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())){
                continue;
            }
            CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
            // START 2018/02/27 [QC#23505, MOD]
            // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            // END   2018/02/27 [QC#23505, MOD]
            // START 2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
            // END   2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1.getValue());
            if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.poNum_HT.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, NONE);
            }
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, bizMsg.H.no(i).pmtHldDt_H1.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, bizMsg.H.no(i).pmtHldPsnCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
            EZDTBLAccessor.insert(cmInvPmtHldTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0073E, new String[] {cmInvPmtHldTMsg.getTableName(), cmInvPmtHldTMsg.getReturnCode() });
                return;
            }
        }
        // Header
        if (checkedRows.size() > 0) {
            // START 2018/03/22 [QC#20316, DEL]
//            CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
//            // START 2018/02/27 [QC#23505, MOD]
//            // ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
//            // END   2018/02/27 [QC#23505, MOD]
//            cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvAcctHdrTMsg);
//            if (cmInvAcctHdrTMsg != null) {
//                if (ZYPConstant.FLG_OFF_N.equals(cmInvAcctHdrTMsg.pmtHldFlg.getValue())) {
//                    // Hold
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldPsnCd, USER_ID);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
//                    EZDTBLAccessor.update(cmInvAcctHdrTMsg);
//                }
//            }
            // END   2018/03/22 [QC#20316, DEL]
            
            CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
            // START 2018/02/27 [QC#23505, MOD]
            // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            // END   2018/02/27 [QC#23505, MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
            cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
            if (cmApInvHdrTMsg != null) {
                if (ZYPConstant.FLG_OFF_N.equals(cmApInvHdrTMsg.pmtHldFlg.getValue())) {
                    // Hold
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                    // START 2018/03/22 [QC#20316, MOD]
                    // EZDTBLAccessor.update(cmInvAcctHdrTMsg);
                    EZDTBLAccessor.update(cmApInvHdrTMsg);
                    // END   2018/03/22 [QC#20316, MOD]
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_ON_Y);
            acctInvStsCd = ACCT_INV_STS.ENTERED;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsCd, acctInvStsCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.acctInvStsDescTxt, NFBL2040CommonLogic.getAcctInvStsDescTxt(acctInvStsCd));

        bizMsg.setMessageInfo(ZZM8100I);

    }

    private boolean isDuplicatedHoldLine(NFBL2040CMsg bizMsg) {
        if (bizMsg == null) {
            return true;
        }

        Set<String> dupliHold = new HashSet<String>();
        Set<String> dupliCheckHold = new HashSet<String>();

        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            String nowHold = bizMsg.H.no(i).pmtHldCd_H1.getValue() + ',' + bizMsg.H.no(i).pmtHldRsnCd_H1.getValue();
            if (dupliCheckHold.contains(nowHold)) {
                dupliHold.add(nowHold);
            } else {
                dupliCheckHold.add(nowHold);
            }
        }
        boolean errorFlg = false;
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            String nowHold = bizMsg.H.no(i).pmtHldCd_H1.getValue() + ',' + bizMsg.H.no(i).pmtHldRsnCd_H1.getValue();
            if (dupliHold.contains(nowHold) && ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                bizMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0181E, new String[] {"Hold Name and Hold Reason" });
                bizMsg.H.no(i).pmtHldRsnCd_H1.setErrorInfo(1, NFBM0181E, new String[] {"Hold Name and Hold Reason" });
                errorFlg = true;
            }
        }
        return errorFlg;
    }

    private boolean checkVndPmtTerm(NFBL2040CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.vndPmtTermDescTxt)) {
            bizMsg.vndPmtTermCd.clear();
            if (!CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) {
                bizMsg.vndPmtTermDescTxt.setErrorInfo(1, ZZM9000E, new String[] {"Terms"});
                return false;
            }
            return true;
        }
        VND_PMT_TERMTMsg vndPmtTermTMsg = new VND_PMT_TERMTMsg();
        vndPmtTermTMsg.setSQLID("802");
        vndPmtTermTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
        vndPmtTermTMsg.setConditionValue("vndPmtTermDescTxt01", bizMsg.vndPmtTermDescTxt.getValue());
        VND_PMT_TERMTMsgArray vndPmtTermTMsgArray = (VND_PMT_TERMTMsgArray) EZDTBLAccessor.findByCondition(vndPmtTermTMsg);
        if (vndPmtTermTMsgArray.length() == 0) {
            bizMsg.vndPmtTermDescTxt.setErrorInfo(1, NFBM0069E);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermCd, vndPmtTermTMsgArray.no(0).vndPmtTermCd);
        return true;
    }

    private boolean checkPoPmtHld(NFBL2040CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd)) {
            PRNT_VNDTMsg prntVndTMsg = new PRNT_VNDTMsg();
            prntVndTMsg.setSQLID("002");
            prntVndTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
            prntVndTMsg.setConditionValue("prntVndCd01", bizMsg.prntVndCd.getValue());
            PRNT_VNDTMsgArray prntVndTMsgArray = (PRNT_VNDTMsgArray) EZDTBLAccessor.findByCondition(prntVndTMsg);
            if (prntVndTMsgArray.length() == 0) {
                bizMsg.prntVndCd.setErrorInfo(1, NFBM0069E);
                return false;
            }
            if (ZYPConstant.FLG_ON_Y.equals(prntVndTMsgArray.no(0).poPmtHldFlg.getValue())) {
                bizMsg.prntVndCd.setErrorInfo(1, NFBM0265E);
                return false;
            }
        }
        return true;
    }

    private Map<String, Object> getVndSplyCoaData(String apVndCd) {
        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVndSplyCoaData(apVndCd);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                return (Map<String, Object>) resultList.get(0);
            } else {
                return null;
            }
        }
        return null;
    }

    private boolean checkSubmit(NFBL2040CMsg bizMsg) {
        boolean rtnVal = true;

        String slsDt = ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode());
        if (ZYPCommonFunc.hasValue(bizMsg.invDt)
                && ZYPCommonFunc.hasValue(slsDt)
                && ZYPDateUtil.compare(bizMsg.invDt.getValue(), slsDt) > 0) {
            bizMsg.invDt.setErrorInfo(1, NFBM0106E);
            rtnVal = false;
        }

        if (!checkVndPmtTerm(bizMsg)) {
            rtnVal = false;
        }

        if (!checkPoPmtHld(bizMsg)) {
            rtnVal = false;
        }

        if (ZYPDateUtil.compare(bizMsg.invDt.getValue(), bizMsg.termNetDueDt.getValue()) > 0) {
            bizMsg.termNetDueDt.setErrorInfo(1, NFBM0142E, new String[] {"Terms Date", "Invoice Date"});
            rtnVal = false;
        }

        // START 2018/11/20 [QC#28660, ADD]
        if (!checkAssociatedCreditMemo(bizMsg)) {
            rtnVal = false;
        }
        // END   2018/11/20 [QC#28660, ADD]

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxCmntTxt_A1)) {
                String xxCmntTxt = bizMsg.A.no(i).xxCmntTxt_A1.getValue();
                String[] strSplit = xxCmntTxt.split("\\.");
                if (!NFBL2040CommonLogic.checkGlCodeCombination(bizMsg, i, strSplit)) {
                    rtnVal = false;
                }
            }
            // START 2018/09/04 [QC#28039, ADD]
            // mod start 2022/02/15 QC#57090
            //if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
            // mod end 2022/02/15 QC#57090
                String poOrdDtlLineNum = bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue();
                String mdseCd = bizMsg.A.no(i).mdseCd_A1.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())) {
                    mdseCd = bizMsg.A.no(i).xxMdseCd_A1.getValue();
                }
                String apVndInvNum = bizMsg.apVndInvNum.getValue();
                if (isInvoicePKModified(bizMsg)) {
                    apVndInvNum = bizMsg.apVndInvNum_OT.getValue();
                }
                BigDecimal billedQty = BigDecimal.ZERO;

                // START 2019/09/04 [QC#52876, ADD]
                if (!AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                    continue;
                }
                // END   2019/09/04 [QC#52876, ADD]

                // START 2018/11/20 [QC#28660, MOD]
                // S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getSumBilledQty(
                //         bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd, apVndInvNum);
                // List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                // if (resultList.size() > 0) {
                //     Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                //     billedQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
                // }
                // START 2020/03/23 [QC#53413, MOD]
                S21SsmEZDResult ssmResult = null;
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    ssmResult = NFBL2040Query.getInstance().getSumBilledQty(bizMsg, bizMsg.A.no(i).poNum_A1.getValue(), poOrdDtlLineNum, mdseCd, apVndInvNum);
                } else {
                    ssmResult = NFBL2040Query.getInstance().getSumBilledQty(bizMsg, bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd, apVndInvNum);
                }
                // END   2020/03/23 [QC#53413, MOD]
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (resultList.size() > 0) {
                    Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                    billedQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
                }
                // END   2018/11/20 [QC#28660, MOD]
                // START 2018/11/21 [QC#28660, MOD]
                BigDecimal totalBilledQty = billedQty.add(bizMsg.A.no(i).apBillQty_A1.getValue());
                BigDecimal poQty = bizMsg.A.no(i).poQty_A1.getValue();
                // if (totalBilledQty.compareTo(BigDecimal.ZERO) < 0) {
                //     bizMsg.A.no(i).apBillQty_A1.setErrorInfo(1, "NFBM0284E", new String[] {"Invoiced Qty", "-1", billedQty.negate().toPlainString() });
                //     rtnVal = false;
                // }
                if (totalBilledQty.compareTo(BigDecimal.ZERO) < 0 || totalBilledQty.compareTo(poQty) > 0) {
                    bizMsg.A.no(i).apBillQty_A1.setErrorInfo(1, "NFBM0284E", new String[] {"Invoiced Qty", poQty.subtract(billedQty).toPlainString(), billedQty.negate().toPlainString() });
                    rtnVal = false;
                }
                // END   2018/11/21 [QC#28660, MOD]
            }
            // END   2018/09/04 [QC#28039, ADD]
        }

        // START 2019/07/18 [QC#51675, ADD]
        if (ACCT_INV_STS.AUTHORIZED.equals(bizMsg.acctInvStsCd.getValue())
                && hasReleasedHold(bizMsg)
                && !(ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue()))) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
            bizMsg.setMessageInfo("NFBM0287W");
            rtnVal = false;
        }
        // END   2019/07/18 [QC#51675, ADD]

        return rtnVal;
    }

    // START 2019/07/18 [QC#51675, ADD]
    private boolean hasReleasedHold(NFBL2040CMsg bizMsg) {
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxChkBox_H1.getValue())
                    && ZYPCommonFunc.hasValue(bizMsg.H.no(i).pmtHldRelDt_H1)) {
                return true;
            }
        }
        return false;
    }
    // END   2019/07/18 [QC#51675, ADD]

    private void setVendorInfo(NFBL2040CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd) && ZYPCommonFunc.hasValue(bizMsg.dplyVndNm)) {
            return;
        }

        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVendorInfo(bizMsg.apVndCd.getValue());
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                if (!ZYPCommonFunc.hasValue(bizMsg.prntVndCd)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, (String) rtn.get(PRNT_VND_CD));
                }
                if (!ZYPCommonFunc.hasValue(bizMsg.dplyVndNm)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dplyVndNm, (String) rtn.get(DPLY_VND_NM));
                }
            }
        }
    }

    private boolean isDuplicated(NFBL2040CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.prntVndCd) || !ZYPCommonFunc.hasValue(bizMsg.apVndInvNum) || !ZYPCommonFunc.hasValue(bizMsg.invDt)) {
            return false;
        }

        // START 2018/03/22 [QC#20316, MOD]
//        CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//        cmInvAcctHdrTMsg.setSQLID("001");
//        cmInvAcctHdrTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
//        cmInvAcctHdrTMsg.setConditionValue("prntVndCd01", bizMsg.prntVndCd.getValue());
//        cmInvAcctHdrTMsg.setConditionValue("apVndInvNum01", bizMsg.apVndInvNum.getValue());
//        CM_INV_ACCT_HDRTMsgArray cmInvAcctHdrTMsgArray = (CM_INV_ACCT_HDRTMsgArray) EZDTBLAccessor.findByCondition(cmInvAcctHdrTMsg);
//        if (cmInvAcctHdrTMsgArray.length() > 0) {
//            bizMsg.prntVndCd.setErrorInfo(1, NFBM0233E, new String[] {bizMsg.prntVndCd.getValue(), bizMsg.apVndInvNum.getValue() });
//            bizMsg.apVndInvNum.setErrorInfo(1, NFBM0233E, new String[] {bizMsg.prntVndCd.getValue(), bizMsg.apVndInvNum.getValue() });
//            return true;
//        }

        // TODO : create CM_AP_INV_HDR.xml & define SQLID

        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        // QC#26475 Mod Start
        cmApInvHdrTMsg.setSQLID("002");
        //cmApInvHdrTMsg.setSQLID("001");
        // QC#26475 Mod End
        cmApInvHdrTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
        cmApInvHdrTMsg.setConditionValue("prntVndCd01", bizMsg.prntVndCd.getValue());
        cmApInvHdrTMsg.setConditionValue("apVndInvNum01", bizMsg.apVndInvNum.getValue());
        CM_AP_INV_HDRTMsgArray cmApInvHdrTMsgArray = (CM_AP_INV_HDRTMsgArray) EZDTBLAccessor.findByCondition(cmApInvHdrTMsg);
        if (cmApInvHdrTMsgArray.length() > 0) {
            bizMsg.prntVndCd.setErrorInfo(1, NFBM0233E, new String[] {bizMsg.prntVndCd.getValue(), bizMsg.apVndInvNum.getValue() });
            bizMsg.apVndInvNum.setErrorInfo(1, NFBM0233E, new String[] {bizMsg.prntVndCd.getValue(), bizMsg.apVndInvNum.getValue() });
            return true;
        }

        // END   2018/03/22 [QC#20316, MOD]
        return false;
    }

    // START 2020/03/23 [QC#53413, MOD]
//    private boolean isErrorExistsCheck(NFBL2040CMsg bizMsg) {
//        boolean rtnErrorFlg = false;
//
//        // Check Vendor
//        S21SsmEZDResult ssmResult = null;
//        if (ZYPCommonFunc.hasValue(bizMsg.apVndCd)) {
//            // START 2018/09/27 [QC#28099, MOD]
//            // START 2019/04/08 [QC#31071, MOD]
//            // ssmResult = NFBL2040Query.getInstance().getVendorInfo(bizMsg.apVndCd.getValue());
//            // if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
//            //     ssmResult = NFBL2040Query.getInstance().getVendorInfoSplyPmtOnly(bizMsg.apVndCd.getValue());
//            if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT) || ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
//                ssmResult = NFBL2040Query.getInstance().getVendorInfoSplyPmtOnly(bizMsg.apVndCd.getValue());
//            } else {
//                ssmResult = NFBL2040Query.getInstance().getVendorInfo(bizMsg.apVndCd.getValue());
//            }
//            // END   2019/04/08 [QC#31071, MOD]
//            // END   2018/09/27 [QC#28099, MOD]
//
//            if (ssmResult.isCodeNormal()) {
//                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                if (resultList.size() > 0) {
//                    Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
//                    if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd) && !bizMsg.prntVndCd.getValue().equals((String) rtn.get("PRNT_VND_CD"))) {
//                        // START 2017/12/22 [QC#22831, MOD]
//                        bizMsg.prntVndCd.setErrorInfo(1, NFBM0235E, new String[] {"Supplier Number", "Parent Vendor" });
//                        // END   2017/12/22 [QC#22831, MOD]
//                        rtnErrorFlg = true;
//                    }
//                    if (ZYPCommonFunc.hasValue(bizMsg.dplyVndNm) && !bizMsg.dplyVndNm.getValue().equals((String) rtn.get("DPLY_VND_NM"))) {
//                        // START 2017/12/22 [QC#22831, MOD]
//                        bizMsg.dplyVndNm.setErrorInfo(1, NFBM0235E, new String[] {"Supplier Name", "Parent Vendor" });
//                        // END   2017/12/22 [QC#22831, MOD]
//                        rtnErrorFlg = true;
//                    }
//                } else {
//                    // START 2017/12/22 [QC#22831, MOD]
//                    bizMsg.apVndCd.setErrorInfo(1, NFBM0235E, new String[] {"Supplier Site Code", "Vendor" });
//                    // END   2017/12/22 [QC#22831, MOD]
//                    rtnErrorFlg = true;
//                }
//            } else {
//                // START 2017/12/22 [QC#22831, MOD]
//                bizMsg.apVndCd.setErrorInfo(1, NFBM0235E, new String[] {"Supplier Site Code", "Vendor" });
//                // END   2017/12/22 [QC#22831, MOD]
//                rtnErrorFlg = true;
//            }
//        }
//
//        // Check PO#
//        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
//            if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
//                ssmResult = NFBL2040Query.getInstance().getPoInfo(bizMsg.poNum_HT.getValue());
//                if (ssmResult.isCodeNormal()) {
//                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                    if (resultList.size() == 0) {
//                        bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
//                        rtnErrorFlg = true;
//                    }
//                } else {
//                    bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
//                    rtnErrorFlg = true;
//                }
//            }
//        }
//
//        return rtnErrorFlg;
//    }
    // mod start 2022/02/15 QC#57090
    //private boolean isErrorExistsCheck(NFBL2040CMsg bizMsg, List<String> poNumList) {
    private boolean isErrorExistsCheck(NFBL2040CMsg bizMsg, List<String> poNumList, List<String> vndRtrnNumList) {
    // mod end 2022/02/15 QC#57090

            boolean rtnErrorFlg = false;
            boolean sqlFlg = false;

            // Check Vendor
            S21SsmEZDResult ssmResult = null;
            if (ZYPCommonFunc.hasValue(bizMsg.apVndCd)) {
                // mod start 2022/02/15 QC#57090
                //if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
                //    sqlFlg = true;
                //} else {
                //    if (0 != poNumList.size()) {
                //        sqlFlg = true;
                //    }
                //}
                //if (sqlFlg) {
                if (poNumList.size() > 0 || vndRtrnNumList.size() > 0) {
                // mod end 2022/02/15 QC#57090
                    ssmResult = NFBL2040Query.getInstance().getVendorInfoSplyPmtOnly(bizMsg.apVndCd.getValue());
                } else {
                    ssmResult = NFBL2040Query.getInstance().getVendorInfo(bizMsg.apVndCd.getValue());
                }
                if (ssmResult.isCodeNormal()) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    if (resultList.size() > 0) {
                        Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd) && !bizMsg.prntVndCd.getValue().equals((String) rtn.get("PRNT_VND_CD"))) {
                            bizMsg.prntVndCd.setErrorInfo(1, NFBM0235E, new String[] {"Supplier Number", "Parent Vendor" });
                            rtnErrorFlg = true;
                        }
                        if (ZYPCommonFunc.hasValue(bizMsg.dplyVndNm) && !bizMsg.dplyVndNm.getValue().equals((String) rtn.get("DPLY_VND_NM"))) {
                            bizMsg.dplyVndNm.setErrorInfo(1, NFBM0235E, new String[] {"Supplier Name", "Parent Vendor" });
                            rtnErrorFlg = true;
                        }
                    } else {
                        bizMsg.apVndCd.setErrorInfo(1, NFBM0235E, new String[] {"Supplier Site Code", "Vendor" });
                        rtnErrorFlg = true;
                    }
                } else {
                    bizMsg.apVndCd.setErrorInfo(1, NFBM0235E, new String[] {"Supplier Site Code", "Vendor" });
                    rtnErrorFlg = true;
                }
                
            }

            // Check PO#
            if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                    ssmResult = NFBL2040Query.getInstance().getPoInfo(bizMsg.poNum_HT.getValue());
                    if (ssmResult.isCodeNormal()) {
                        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                        if (resultList.size() == 0) {
                            bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
                            rtnErrorFlg = true;
                        }
                    } else {
                        bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
                        rtnErrorFlg = true;
                    }
                // mod start 2022/02/15 QC#57090
                //} else {
                }
                // mod end 2022/02/15 QC#57090
                    for (int i = 0; i < poNumList.size(); i++) {
                        ssmResult = NFBL2040Query.getInstance().getPoInfo(poNumList.get(i));
                        if (ssmResult.isCodeNormal()) {
                            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                            if (resultList.size() == 0) {
                                setPurchaseOrderErr (bizMsg, poNumList.get(i));
                                rtnErrorFlg = true;
                            }
                        } else {
                            setPurchaseOrderErr (bizMsg, poNumList.get(i));
                            rtnErrorFlg = true;
                        }
                    }
                // del start 2022/02/15 QC#57090
                //}
                // del end 2022/02/15 QC#57090
            }
            return rtnErrorFlg;
        }
    // END   2020/03/23 [QC#53413, MOD]
    // START 2020/03/23 [QC#53413, ADD]
    /**
     * Set PurchaseOrder Error
     * @param bizMsg NFBL2040CMsg
     * @param poNum  String
     */
    private void setPurchaseOrderErr (NFBL2040CMsg bizMsg, String poNum) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (poNum.equals(bizMsg.A.no(i).poNum_A1.getValue())) {
                bizMsg.A.no(i).poNum_A1.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
            }
        }
    }
    // END   2020/03/23 [QC#53413, ADD]
    
    // START 2020/03/23 [QC#53413, MOD]
//    private boolean isErrorVendorMatch(NFBL2040CMsg bizMsg) {
//        boolean rtnErrorFlg = false;
//        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPoVendorInfo(bizMsg.poNum_HT.getValue());
//        if (ssmResult.isCodeNormal()) {
//            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//            if (resultList.size() > 0) {
//                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
//
//                // START 2018/10/01 [QC#28099-1, ADD]
//                String splyPmtFlg = ZYPConstant.FLG_ON_Y;
//                VNDTMsg vndTMsg = new VNDTMsg();
//                vndTMsg.setSQLID("001");
//                vndTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
//                vndTMsg.setConditionValue("vndCd01", rtn.get("VND_CD"));
//                VNDTMsgArray vndArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(vndTMsg);
//                if (vndArray.length() > 0) {
//                    splyPmtFlg = vndArray.no(0).splyPmtFlg.getValue();
//                }
//                // END   2018/10/01 [QC#28099-1, ADD]
//
//                // START 2018/10/01 [QC#28099-1, MOD]
//                // if (!bizMsg.apVndCd.getValue().equals((String) rtn.get("VND_CD"))) {
//                if (ZYPConstant.FLG_ON_Y.equals(splyPmtFlg) && !bizMsg.apVndCd.getValue().equals((String) rtn.get("VND_CD"))) {
//                // END   2018/10/01 [QC#28099-1, MOD]
//                    bizMsg.apVndCd.setErrorInfo(1, NFBM0259E);
//                    rtnErrorFlg = true;
//                }
//                if (!bizMsg.prntVndCd.getValue().equals((String) rtn.get("PRNT_VND_CD"))) {
//                    bizMsg.prntVndCd.setErrorInfo(1, NFBM0259E);
//                    rtnErrorFlg = true;
//                }
//            } else {
//                bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E);
//                rtnErrorFlg = true;
//            }
//        } else {
//            bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E);
//            rtnErrorFlg = true;
//        }
//        return rtnErrorFlg;
//    }
    private boolean isErrorVendorMatch(NFBL2040CMsg bizMsg, List<String> poNumList) {
        boolean rtnErrorFlg = false;

        // START 2022/05/27 D.Mamaril [QC#60084, ADD]
        if (!poNumList.isEmpty()) {
            List<String> diffPoSupplierNumList = new ArrayList<String>();

            // Get the 1st PO# and the PO Details
            POTMsg poTMsg = new POTMsg();
            POTMsg poTarget = new POTMsg();
            POTMsg poCompare = new POTMsg();

            String poNum = poNumList.get(0);
            ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, poNum);
            poTarget = (POTMsg) EZDTBLAccessor.findByKey(poTMsg);

            if (poTarget == null) {
                setPoNumErr(bizMsg, poNum);
                return true;
            }

            for (int i = 1; i < poNumList.size(); i++) {
                // Get the succeeding PO# and the PO Details
                poNum = poNumList.get(i);
                ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, poNum);
                poCompare = (POTMsg) EZDTBLAccessor.findByKey(poTMsg);

                if (poCompare != null) {
                    if (!poCompare.prntVndCd.getValue().equals(poTarget.prntVndCd.getValue())) {
                        diffPoSupplierNumList.add(poCompare.poOrdNum.getValue());
                    }
                } else {
                    setPoNumErr(bizMsg, poNum);
                    return true;
                }
            }

            // Put Error on all PO Lines with different Supplier Number
            if (!diffPoSupplierNumList.isEmpty()) {
                for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                    if (diffPoSupplierNumList.contains((bizMsg.A.no(j).poNum_A1.getValue()))) {
                        bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NFBM0296E);
                    }
                }
                return true;
            }
        }
        // END 2022/05/27 D.Mamaril [QC#60084, ADD]

        if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
            S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPoVendorInfo(bizMsg.poNum_HT.getValue());
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (resultList.size() > 0) {
                    rtnErrorFlg = chkErrorVendorMatch (bizMsg, resultList);
                } else {
                    bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E);
                    rtnErrorFlg = true;
                }
            } else {
                bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E);
                rtnErrorFlg = true;
            }
        // mod start 2022/02/15 QC#57090
        //} else {
        }
        // mod end 2022/02/15 QC#57090
            for (int i = 0; i < poNumList.size(); i++) {
                String poNum = poNumList.get(i);
                S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getPoVendorInfo(poNum);
                if (ssmResult.isCodeNormal()) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    if (resultList.size() > 0) {
                        rtnErrorFlg = chkErrorVendorMatch (bizMsg, resultList);
                        if (rtnErrorFlg) {
                            break;
                        }
                    } else {
                        setPoNumErr (bizMsg, poNum) ;
                        rtnErrorFlg = true;
                    }
                } else {
                    setPoNumErr (bizMsg, poNum) ;
                    rtnErrorFlg = true;
                }
            }
        // del start 2022/02/15 QC#57090
        //}
        // del end 2022/02/15 QC#57090
        
        return rtnErrorFlg;
    }

    /**
     * Set PurchaseOrder Error
     * @param bizMsg NFBL2040CMsg
     * @param poNum  String
     */
    private void setPoNumErr (NFBL2040CMsg bizMsg, String poNum) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (poNum.equals(bizMsg.A.no(i).poNum_A1.getValue())) {
                bizMsg.A.no(i).poNum_A1.setErrorInfo(1, NFBM0235E);
            }
        }
    }

    /**
     * chkErrorVendorMatch
     * @param bizMsg     NFBL2040CMsg
     * @param resultList List<Map<String, Object>>
     * @return boolean
     */
    private boolean chkErrorVendorMatch (NFBL2040CMsg bizMsg, List<Map<String, Object>> resultList) {
        boolean rtnErrorFlg = false;
        Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);

        String splyPmtFlg = ZYPConstant.FLG_ON_Y;
        VNDTMsg vndTMsg = new VNDTMsg();
        // START 2020/07/10 R.Kurahashi [QC#57322,MOD]
        //vndTMsg.setSQLID("001");
        vndTMsg.setSQLID("002");
        // END 2020/07/10 R.Kurahashi [QC#57322,MOD]
        vndTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
        vndTMsg.setConditionValue("vndCd01", rtn.get("VND_CD"));
        VNDTMsgArray vndArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(vndTMsg);
        if (vndArray.length() > 0) {
            splyPmtFlg = vndArray.no(0).splyPmtFlg.getValue();
        }
        // START 2020/07/10 R.Kurahashi [QC#57322,DEL]
        //if (ZYPConstant.FLG_ON_Y.equals(splyPmtFlg) && !bizMsg.apVndCd.getValue().equals((String) rtn.get("VND_CD"))) {
        //    bizMsg.apVndCd.setErrorInfo(1, NFBM0259E);
        //    rtnErrorFlg = true;
        //}
        // START 2020/07/10 R.Kurahashi [QC#57322,DEL]
        if (!bizMsg.prntVndCd.getValue().equals((String) rtn.get("PRNT_VND_CD"))) {
            bizMsg.prntVndCd.setErrorInfo(1, NFBM0259E);
            rtnErrorFlg = true;
        }

        return rtnErrorFlg;
    }

//    private boolean isErrorVendorMatchForCreditMemo(NFBL2040CMsg bizMsg) {
//        // START QC#25902,QC#25190,QC#25141
//        if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HT) && !ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
//            return false;
//        }
//        // END QC#25902,QC#25190,QC#25141
//        boolean rtnErrorFlg = false;
//        // START QC#25902,QC#25190,QC#25141
//        if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)) {
//            VND_RTRNTMsg vndRtrnTMsg = new VND_RTRNTMsg();
//            ZYPEZDItemValueSetter.setValue(vndRtrnTMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(vndRtrnTMsg.vndRtrnNum, bizMsg.vndRtrnNum);
//            VND_RTRNTMsg target = (VND_RTRNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(vndRtrnTMsg);
//            if (target != null) {
//
//                // START 2019/04/08 [QC#31071, ADD]
//                String splyPmtFlg = ZYPConstant.FLG_ON_Y;
//                VNDTMsg vndTMsg = new VNDTMsg();
//                vndTMsg.setSQLID("001");
//                vndTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
//                vndTMsg.setConditionValue("vndCd01", target.shipToVndCd.getValue());
//                VNDTMsgArray vndArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(vndTMsg);
//                if (vndArray.length() > 0) {
//                    splyPmtFlg = vndArray.no(0).splyPmtFlg.getValue();
//                }
//                // END   2019/04/08 [QC#31071, ADD]
//
//                // START 2019/04/08 [QC#31071, ADD]
//                // if (ZYPCommonFunc.hasValue(bizMsg.apVndCd) && !bizMsg.apVndCd.getValue().equals(target.shipToVndCd.getValue())) {
//                if (ZYPConstant.FLG_ON_Y.equals(splyPmtFlg) && ZYPCommonFunc.hasValue(bizMsg.apVndCd) && !bizMsg.apVndCd.getValue().equals(target.shipToVndCd.getValue())) {
//                // END   2019/04/08 [QC#31071, ADD]
//                    bizMsg.apVndCd.setErrorInfo(1, NFBM0267E);
//                    rtnErrorFlg = true;
//                }
//            } else {
//                bizMsg.vndRtrnNum_H.setErrorInfo(1, NFBM0235E, new String[] {"Vender Return#", "Vender Return" });
//                rtnErrorFlg = true;
//            }
//        } else {
//            POTMsg poTMsg = new POTMsg();
//            ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, bizMsg.poNum);
//            POTMsg target = (POTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poTMsg);
//            if (target != null) {
//
//                // START 2018/10/01 [QC#28099-1, ADD]
//                String splyPmtFlg = ZYPConstant.FLG_ON_Y;
//                VNDTMsg vndTMsg = new VNDTMsg();
//                vndTMsg.setSQLID("001");
//                vndTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
//                vndTMsg.setConditionValue("vndCd01", target.vndCd.getValue());
//                VNDTMsgArray vndArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(vndTMsg);
//                if (vndArray.length() > 0) {
//                    splyPmtFlg = vndArray.no(0).splyPmtFlg.getValue();
//                }
//                // END   2018/10/01 [QC#28099-1, ADD]
//
//                // START 2018/10/01 [QC#28099-1, ADD]
//                // if (ZYPCommonFunc.hasValue(bizMsg.apVndCd) && !bizMsg.apVndCd.getValue().equals(target.vndCd.getValue())) {
//                if (ZYPConstant.FLG_ON_Y.equals(splyPmtFlg) && ZYPCommonFunc.hasValue(bizMsg.apVndCd) && !bizMsg.apVndCd.getValue().equals(target.vndCd.getValue())) {
//                // END   2018/10/01 [QC#28099-1, ADD]
//                    bizMsg.apVndCd.setErrorInfo(1, NFBM0259E);
//                    rtnErrorFlg = true;
//                }
//            } else {
//                bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "PO" });
//                rtnErrorFlg = true;
//            }
//        }
//        // END QC#25902,QC#25190,QC#25141
//        return rtnErrorFlg;
//    }
    // mod start 2022/02/15 QC#57090
    //private boolean isErrorVendorMatchForCreditMemo(NFBL2040CMsg bizMsg, List<String> poNumList) {
    private boolean isErrorVendorMatchForCreditMemo(NFBL2040CMsg bizMsg, List<String> poNumList, List<String> vndRtrnNumList) {
    // mod end 2022/02/15 QC#57090
        // del start 2022/02/15 QC#57090
        //if ((0 == poNumList.size() || !ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) && !ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
        //    return false;
        //}
        // del end 2022/02/15 QC#57090
        boolean rtnErrorFlg = false;
        // mod start 2022/02/15 QC#57090
//        if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)) {
//            VND_RTRNTMsg vndRtrnTMsg = new VND_RTRNTMsg();
//            ZYPEZDItemValueSetter.setValue(vndRtrnTMsg.glblCmpyCd, getGlobalCompanyCode());
//            ZYPEZDItemValueSetter.setValue(vndRtrnTMsg.vndRtrnNum, bizMsg.vndRtrnNum);
//            VND_RTRNTMsg target = (VND_RTRNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(vndRtrnTMsg);
//            if (target != null) {
//
//                String splyPmtFlg = ZYPConstant.FLG_ON_Y;
//                VNDTMsg vndTMsg = new VNDTMsg();
//                vndTMsg.setSQLID("001");
//                vndTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
//                vndTMsg.setConditionValue("vndCd01", target.shipToVndCd.getValue());
//                VNDTMsgArray vndArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(vndTMsg);
//                if (vndArray.length() > 0) {
//                    splyPmtFlg = vndArray.no(0).splyPmtFlg.getValue();
//                }
//                if (ZYPConstant.FLG_ON_Y.equals(splyPmtFlg) && ZYPCommonFunc.hasValue(bizMsg.apVndCd) && !bizMsg.apVndCd.getValue().equals(target.shipToVndCd.getValue())) {
//                    bizMsg.apVndCd.setErrorInfo(1, NFBM0267E);
//                    rtnErrorFlg = true;
//                }
//            } else {
//                bizMsg.vndRtrnNum_H.setErrorInfo(1, NFBM0235E, new String[] {"Vender Return#", "Vender Return" });
//                rtnErrorFlg = true;
//            }
//        } else {
        if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
            S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVendorReturnVendorInfo(bizMsg.vndRtrnNum_H.getValue());
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (resultList.size() > 0) {
                    Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                    if (!bizMsg.prntVndCd.getValue().equals((String) rtn.get("PRNT_VND_CD"))) {
                        bizMsg.prntVndCd.setErrorInfo(1, NFBM0267E);
                        rtnErrorFlg = true;
                    }
                } else {
                    bizMsg.vndRtrnNum_H.setErrorInfo(1, NFBM0235E, new String[] {"Vender Return#", "Vender Return" });
                    rtnErrorFlg = true;
                }
            } else {
                bizMsg.vndRtrnNum_H.setErrorInfo(1, NFBM0235E, new String[] {"Vender Return#", "Vender Return" });
                rtnErrorFlg = true;
            }
        }
        // mod end 2022/02/15 QC#57090

        // add start 2022/02/15 QC#57090
        for (int i = 0; i < vndRtrnNumList.size(); i++) {
            S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getVendorReturnVendorInfo(vndRtrnNumList.get(i));
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (resultList.size() > 0) {
                    Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                    if (!bizMsg.prntVndCd.getValue().equals((String) rtn.get("PRNT_VND_CD"))) {
                        bizMsg.prntVndCd.setErrorInfo(1, NFBM0267E);
                        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                            if (vndRtrnNumList.get(i).equals(bizMsg.A.no(j).vndRtrnNum_A1.getValue())) {
                                bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NFBM0267E);
                            }
                        }
                        rtnErrorFlg = true;
                    }
                } else {
                    for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                        if (vndRtrnNumList.get(i).equals(bizMsg.A.no(j).vndRtrnNum_A1.getValue())) {
                            bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NFBM0235E, new String[] {"Vender Return#", "Vender Return" });
                        }
                    }
                    rtnErrorFlg = true;
                }
            } else {
                for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                    if (vndRtrnNumList.get(i).equals(bizMsg.A.no(j).vndRtrnNum_A1.getValue())) {
                        bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NFBM0235E, new String[] {"Vender Return#", "Vender Return" });
                    }
                }
                rtnErrorFlg = true;
            }
        }
        // add end 2022/02/15 QC#57090

        // START 2022/06/28 D.Mamaril [QC#60084-1, ADD]
        if (!poNumList.isEmpty()) {
            List<String> diffPoSupplierNumList = new ArrayList<String>();

            // Get the 1st PO# and the PO Details
            POTMsg poTMsg = new POTMsg();
            POTMsg poTarget = new POTMsg();
            POTMsg poCompare = new POTMsg();

            String poNum = poNumList.get(0);
            ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, poNum);
            poTarget = (POTMsg) EZDTBLAccessor.findByKey(poTMsg);

            if (poTarget == null) {
                setPoNumErr(bizMsg, poNum);
                return true;
            }

            for (int i = 1; i < poNumList.size(); i++) {
                // Get the succeeding PO# and the PO Details
                poNum = poNumList.get(i);
                ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, poNum);
                poCompare = (POTMsg) EZDTBLAccessor.findByKey(poTMsg);

                if (poCompare != null) {
                    if (!poCompare.prntVndCd.getValue().equals(poTarget.prntVndCd.getValue())) {
                        diffPoSupplierNumList.add(poCompare.poOrdNum.getValue());
                    }
                } else {
                    setPoNumErr(bizMsg, poNum);
                    return true;
                }
            }

            // Put Error on all PO Lines with different Supplier Number
            if (!diffPoSupplierNumList.isEmpty()) {
                for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                    if (diffPoSupplierNumList.contains((bizMsg.A.no(j).poNum_A1.getValue()))) {
                        bizMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NFBM0296E);
                    }
                }
                return true;
            }
        }
        // END 2022/06/28 D.Mamaril [QC#60084-1, ADD]

            POTMsg poTMsg = new POTMsg();
            ZYPEZDItemValueSetter.setValue(poTMsg.glblCmpyCd, getGlobalCompanyCode());
            if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, bizMsg.poNum_HT.getValue());
                POTMsg target = (POTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poTMsg);
                if (target != null) {
                    rtnErrorFlg = chkVndErr (bizMsg, target.vndCd.getValue());
                } else {
                    bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "PO" });
                    rtnErrorFlg = true;
                }
            // mod start 2022/02/15 QC#57090
            //} else {
            }
            // mod end 2022/02/15 QC#57090
                for (int i = 0; i < poNumList.size(); i++) {
                    ZYPEZDItemValueSetter.setValue(poTMsg.poOrdNum, poNumList.get(i));
                    POTMsg target = (POTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(poTMsg);
                    if (target != null) {
                        rtnErrorFlg = chkVndErr (bizMsg, target.vndCd.getValue());
                    } else {
                        for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                            if (poNumList.get(j).equals(bizMsg.A.no(j).poNum_A1.getValue())) {
                                bizMsg.A.no(j).poNum_A1.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "PO" });
                            }
                        }
                        rtnErrorFlg = true;
                    }
                    if (rtnErrorFlg) {
                        break;
                    }
                }
        // del start 2022/02/15 QC#57090
        //    }
        //}
        // del end 2022/02/15 QC#57090
        return rtnErrorFlg;
    }

    /**
     * Cheak Vnd Err
     * @param bizMsg
     * @param vndCd
     * @return
     */
    private boolean chkVndErr (NFBL2040CMsg bizMsg, String vndCd) {
        boolean rtnErrorFlg = false;
        // START 2020/07/10 R.Kurahashi [QC#57322,DEL]
        //String splyPmtFlg = ZYPConstant.FLG_ON_Y;
        //VNDTMsg vndTMsg = new VNDTMsg();
        //vndTMsg.setSQLID("001");
        //vndTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
        //vndTMsg.setConditionValue("vndCd01", vndCd);
        //VNDTMsgArray vndArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(vndTMsg);
        //if (vndArray.length() > 0) {
        //    splyPmtFlg = vndArray.no(0).splyPmtFlg.getValue();
        //}
        //if (ZYPConstant.FLG_ON_Y.equals(splyPmtFlg) && ZYPCommonFunc.hasValue(bizMsg.apVndCd) && !bizMsg.apVndCd.getValue().equals(vndCd)) {
        //    bizMsg.apVndCd.setErrorInfo(1, NFBM0259E);
        //    rtnErrorFlg = true;
        //}
        // START 2020/07/10 R.Kurahashi [QC#57322,DEL]
        return rtnErrorFlg;
    }
    // END   2020/03/23 [QC#53413, MOD]

    /**
     * callApiForTherefore
     * @param bizMsg Business Component Interface Message
     */
    private void callApiForTherefore(NFBL2040CMsg bizMsg, String apVndCd, String apVndInvNum, String docMgtCatgCd, BigDecimal docMgtDocId, String attDocTpCd, BigDecimal attDataPk, boolean isCreate) {

        // Create API Parameter
        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        StringBuilder sqNum = new StringBuilder();
        sqNum.append(getGlobalCompanyCode());
        sqNum.append(PARAMS_AP_VND_CD_KEY);
        sqNum.append(apVndCd);
        sqNum.append(PARAMS_AP_VND_INV_NUM_KEY);
        sqNum.append(apVndInvNum);

        params.setBusinessId(BIZ_ID);
        params.setAttDataGrp(sqNum.toString());
        params.setBusinessNm(PARAMS_FUNCTION_NAME);
        params.setAttDocTpCd(attDocTpCd);
        params.setThereforeDocId(docMgtDocId.toString());

        NFZC505001PMsg pMsg = new NFZC505001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(pMsg.docMgtDocId, docMgtDocId);
        ZYPEZDItemValueSetter.setValue(pMsg.docMgtCatgCd, docMgtCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.apVndCd, apVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.apVndInvNum, apVndInvNum);

        params.setBizApiIdTherefore(THEREFORE_ATT_BIZ_ID);
        if (isCreate) {
            params.setArgsForBizApiTherefore(new Object[] {pMsg, ONBATCH_TYPE.ONLINE, ZYPFileUpDownConst.BIZAPI_TYPE_CREATE});

            // Call Therefore Doc Attach API
            int attDataKey = ZYPFileUpDown.uploadTherefore(params);

            // error handling
            if (attDataKey == -1) {
                bizMsg.setMessageInfo(NFBM0263E, new String[] {"ATT_DATA"});
            }
        } else {
            params.setArgsForBizApiTherefore(new Object[] {pMsg, ONBATCH_TYPE.ONLINE, ZYPFileUpDownConst.BIZAPI_TYPE_DELETE});

            if (ZYPCommonFunc.hasValue(attDataPk)) {
                params.setAttDataPk(attDataPk.intValue());
                String returnCd = ZYPFileUpDown.detachTherefore(params);

                if (API_RETURN_CD_ERROR.equals(returnCd)) {
                    bizMsg.setMessageInfo(NFBM0266E, new String[] {"ATT_DATA"});
                }
            }
        }

        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
        if (msgList.size() > 0) {
            bizMsg.setMessageInfo(msgList.get(0).getXxMsgid());
        }
    }
    
    /**
     * Method name: callPoStsUpdateAPI
     * <dd>The method explanation: Call PO Status Update API
     * <dd>Remarks:
     * @param bizMsg NFBL2040CMsg
     * @param cmApInvDtlTMsg CM_AP_INV_DTLTMsg
     */
    private void callPoStsUpdateAPI(NFBL2040CMsg bizMsg, CM_AP_INV_DTLTMsg cmApInvDtlTMsg) {
        String poNum = cmApInvDtlTMsg.poNum.getValue();
        String mdseCd = cmApInvDtlTMsg.mdseCd.getValue();
        // START 2018/08/26 [QC#27886, MOD]
        // String poOrdDtlLineNum = "";
        String poOrdDtlLineNum = cmApInvDtlTMsg.poOrdDtlLineNum.getValue();
        // END   2018/08/26 [QC#27886, MOD]
        BigDecimal invQty = cmApInvDtlTMsg.invQty.getValue();
        // START 2018/08/26 [QC#27886, DEL]
        // BigDecimal invBalQty = BigDecimal.ZERO;
        // boolean callAPIFlg = false;
        // boolean overQtyFlg = false;
        // END   2018/08/26 [QC#27886, DEL]
        //QC#18602-Sol#102 ADD START
        String apVndInvNum = cmApInvDtlTMsg.apVndInvNum.getValue();
        // For Blanket PO Amount check
        boolean blanketPOFlg = false;

        blanketPOFlg = NFBL2040CommonLogic.chkPOCloseforBP(poNum, apVndInvNum, mdseCd); 

        // QC#27026 Update.
        boolean isCreditMemoOfPo = CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.poNum);

        //PO Type and Amount Check
        if (blanketPOFlg) {
        // QC#18602-Sol#102 ADD END
            // START 2018/08/23 [QC#27886, MOD]
            // START 2018/09/04 [QC#28039, MOD]
            // List<Map<String, Object>> listCmApInvDtl = (List<Map<String, Object>>) NFBL2040CommonLogic.getListCmApInvDtlByPartialKey(poNum);
            List<Map<String, Object>> listCmApInvDtl = new ArrayList<Map<String, Object>>();
            if (isCreditMemoOfPo) {
                listCmApInvDtl = (List<Map<String, Object>>) NFBL2040CommonLogic.getListCmApInvDtlByPartialKeyForCreditMemo(poNum);
            } else if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
                listCmApInvDtl = (List<Map<String, Object>>) NFBL2040CommonLogic.getListCmApInvDtlByPartialKey(poNum);
            }
            // END   2018/09/04 [QC#28039, MOD]
            for (int j = 0; j < listCmApInvDtl.size(); j++) {
                Map<String, Object> map = (Map<String, Object>) listCmApInvDtl.get(j);
                String poOrdDtlLineNumOfPO = (String) map.get(PO_ORD_DTL_LINE_NUM);

                if (!poOrdDtlLineNumOfPO.equals(poOrdDtlLineNum)) {
                    continue;
                }

                if (BigDecimal.ZERO.compareTo(invQty) > 0) {
                    // Credit Memo is negative.
                    String msgId = NFBL2040CommonLogic.updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
                    if (msgId != null) {
                        bizMsg.setMessageInfo(msgId);
                    }
                // START 2020/03/23 [QC#53413, ADD]
                //} else if (BigDecimal.ZERO.compareTo(invQty) < 0 && ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                } else if (BigDecimal.ZERO.compareTo(invQty) < 0 && ZYPCommonFunc.hasValue(poNum)) {
                // END   2020/03/23 [QC#53413, ADD]
                    // Invoice is positive.
                    String msgId = NFBL2040CommonLogic.updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
                    if (msgId != null) {
                        bizMsg.setMessageInfo(msgId);
                    }
                }
            }
            // List<Map<String, Object>> listCmApInvDtl = (List<Map<String, Object>>) NFBL2040CommonLogic.getListCmApInvDtlByPartialKey(poNum);
            // for (int j = 0; j < listCmApInvDtl.size(); j++) {
            //     Map<String, Object> map = (Map<String, Object>) listCmApInvDtl.get(j);
            //     String mdseCdOfPO = (String) map.get(MDSE_CD);
            //     String poOrdDtlLineNumOfPO = (String) map.get(PO_ORD_DTL_LINE_NUM);
            //     BigDecimal invBalQtyOfPO = (BigDecimal) map.get(PO_INV_BAL_QTY);
            //
            //     if (callAPIFlg) {
            //         if (overQtyFlg && !mdseCd.equals(mdseCdOfPO)) {
            //             invQty = invQty.add(invBalQty);
            //         }
            //         // 2018/07/12 Delete. [QC#27025]
            //         // 2018/07/26 Update. [QC#27029]
            //         // Credit Memo is negative.
            //         if (BigDecimal.ZERO.compareTo(invQty) > 0) {
            //             String msgId = NFBL2040CommonLogic.updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
            //             if (msgId != null) {
            //                 bizMsg.setMessageInfo(msgId);
            //             }
            //         // START 2018/08/01 [QC#27025-1, MOD]
            //         // START 2018/08/13 [QC#27025-2, MOD]
            //         } else if (BigDecimal.ZERO.compareTo(invQty) < 0 && ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
            //             String msgId = NFBL2040CommonLogic.updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
            //             if (msgId != null) {
            //                 bizMsg.setMessageInfo(msgId);
            //             }
            //         }
            //         // END   2018/08/13 [QC#27025-2, MOD]
            //         // END   2018/08/01 [QC#27025-1, MOD]
            //         // 2018/07/26 Update end. [QC#27029]    
            //         // 2018/07/12 Delete end. [QC#27025]
            //         invQty = invBalQty;
            //         callAPIFlg = false;
            //     }
            // 
            //     if (mdseCd.equals(mdseCdOfPO)) {
            //         callAPIFlg = true;
            //         poOrdDtlLineNum = poOrdDtlLineNumOfPO;
            //         if (invBalQtyOfPO.compareTo(invQty) >= 0) {
            //             invBalQty = BigDecimal.ZERO;
            //             overQtyFlg = false;
            //         } else {
            //             invBalQty = invQty.subtract(invBalQtyOfPO);
            //             invQty = invBalQtyOfPO;
            //             overQtyFlg = true;
            //         }
            //     }
            // }
            // 
            // if (callAPIFlg) {
            //     if (overQtyFlg) {
            //         invQty = invQty.add(invBalQty);
            //     }
            //     // 2018/07/12 Delete. [QC#27025]
            //     // 2018/07/26 Update. [QC#27029]
            //     if (isCreditMemoOfPo) {
            //         // Credit Memo is negative.
            //         if (BigDecimal.ZERO.compareTo(invQty) > 0) {
            //             String msgId = NFBL2040CommonLogic.updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
            //             if (msgId != null) {
            //                 bizMsg.setMessageInfo(msgId);
            //             }
            //         }
            //     // START 2018/08/01 [QC#27025-1, MOD]
            //     // START 2018/08/13 [QC#27025-2, MOD]
            //     } else if (BigDecimal.ZERO.compareTo(invQty) < 0 && ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
            //         String msgId = NFBL2040CommonLogic.updatePoInformation(poNum, poOrdDtlLineNum, invQty, mdseCd);
            //         if (msgId != null) {
            //             bizMsg.setMessageInfo(msgId);
            //         }
            //     }
            //     // END   2018/08/13 [QC#27025-2, MOD]
            //     // END   2018/08/01 [QC#27025-1, MOD]
            //     // 2018/07/26 Update end. [QC#27029]
            //     // 2018/07/12 Delete end. [QC#27025]
            //     return;
            // }
            // END   2018/08/23 [QC#27886, MOD]

            // START 2018/09/04 [QC#28039, DEL]
            // QC#27029 Update.
            // if(isCreditMemoOfPo && listCmApInvDtl.isEmpty()){
            //     // listCmApInvDtl is Empty = Close Line.
            //     // Call PO Update API
            //     String msgId = NFBL2040CommonLogic.updatePoInformation(//
            //             cmApInvDtlTMsg.poNum.getValue()//
            //             , cmApInvDtlTMsg.poOrdDtlLineNum.getValue()//
            //             , cmApInvDtlTMsg.invQty.getValue()//
            //             , cmApInvDtlTMsg.mdseCd.getValue());
            //     if (msgId != null) {
            //         bizMsg.setMessageInfo(msgId);
            //     }
            // }
            // END  2018/09/04 [QC#28039, DEL]

        } else {
            return;
        }
    }

    // START 2020/03/23 [QC#53413, MOD]
    //private boolean manualHold(NFBL2040CMsg bizMsg, String acctInvSts) {
    private boolean manualHold(NFBL2040CMsg bizMsg, String acctInvSts, Map<String, String> poNumListMap) {
    // END   2020/03/23 [QC#53413, MOD]
        // START 2018/03/22 [QC#20316, MOD]
//        // Update CM_INV_ACCT_HDR table record.
//        CM_INV_ACCT_HDRTMsg checkCmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//        ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
//        ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
//        ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
//        // START 2018/02/27 [QC#23505, MOD]
//        // ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
//        ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
//        // END   2018/02/27 [QC#23505, MOD]
//        CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(checkCmInvAcctHdrTMsg);
//        // START 2018/01/04 [QC#22143, ADD]
//        if (cmInvAcctHdrTMsg == null && isInvoicePKModified(bizMsg)) {
//            ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
//            ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd_OT.getValue());
//            ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum_OT.getValue());
//            ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_OT.getValue());
//            cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(checkCmInvAcctHdrTMsg);
//            if (cmInvAcctHdrTMsg == null) {
//                return false;
//            }
//            ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
//            ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
//            // START 2018/02/27 [QC#23505, MOD]
//            // ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
//            ZYPEZDItemValueSetter.setValue(checkCmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
//            // END   2018/02/27 [QC#23505, MOD]
//        }
        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
        cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
        // START 2018/11/16 [QC#29247, ADD]
        // if (cmApInvHdrTMsg != null && isInvoicePKModified(bizMsg)) {
        if (cmApInvHdrTMsg == null && isInvoicePKModified(bizMsg)) {
            // END 2018/11/16 [QC#29247, ADD]
            cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum_OT.getValue());
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_OT);
            cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
            if (cmApInvHdrTMsg == null) {
                return false;
            }
            // START 2018/11/16 [QC#29247, DEL]
            // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
            // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
            // ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            // END 2018/11/16 [QC#29247, DEL]
        }
        // END   2018/03/22 [QC#20316, MOD]

        // END   2018/01/04 [QC#22143, ADD]
        if (bizMsg.H.getValidCount() > 0) {
            boolean isHold = false;
            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(bizMsg.H.no(i).xxChkBox_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).xxChkBox_H1, ZYPConstant.FLG_OFF_N);
                }
                if (!isHold && bizMsg.H.no(i).xxChkBox_H1.getValue().equals(ZYPConstant.FLG_OFF_N)
                ) {
                    isHold = true;
                    // START 2018/03/22 [QC#20316, MOD]
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue()); // Hold name
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldDt, bizMsg.H.no(i).pmtHldDt_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1.getValue()); // Hold Reason
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldPsnCd, bizMsg.H.no(i).pmtHldPsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, bizMsg.H.no(i).pmtHldRelPsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, bizMsg.H.no(i).pmtHldRelDt_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, bizMsg.H.no(i).pmtHldRelCmntTxt_H1.getValue());
                    ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                    // END  START 2018/03/22 [QC#20316, MOD]
                
                }
            }
            if (!isHold) {
                // START 2018/03/22 [QC#20316, MOD]
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, bizMsg.H.no(0).pmtHldCd_H1.getValue()); // Hold name
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldDt, bizMsg.H.no(0).pmtHldDt_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, bizMsg.H.no(0).pmtHldRsnCd_H1.getValue()); // Hold Reason
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldPsnCd, bizMsg.H.no(0).pmtHldPsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, bizMsg.H.no(0).pmtHldRelPsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, bizMsg.H.no(0).pmtHldRelDt_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, bizMsg.H.no(0).pmtHldRelRsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, bizMsg.H.no(0).pmtHldRelCmntTxt_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                // END  START 2018/03/22 [QC#20316, MOD]
            }
        } else {
            // START 2018/03/22 [QC#20316, MOD]
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, PMT_HLD_CD_000);
//            cmInvAcctHdrTMsg.pmtHldDt.clear();
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, PMT_HLD_RSN_CD_000);
//            cmInvAcctHdrTMsg.pmtHldPsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelPsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelDt.clear();
//            cmInvAcctHdrTMsg.pmtHldRelRsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelCmntTxt.clear();
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
            // END   2018/03/22 [QC#20316, MOD]
        }
        // START 2018/03/22 [QC#20316, MOD]
        // EZDTBLAccessor.update(cmInvAcctHdrTMsg);
        // if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctHdrTMsg.getReturnCode())) {
        //     bizMsg.setMessageInfo(NFBM0224E, new String[] {cmInvAcctHdrTMsg.getTableName()});
        //     return true;
        // }
        EZDTBLAccessor.update(cmApInvHdrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFBM0224E, new String[] {cmApInvHdrTMsg.getTableName(), cmApInvHdrTMsg.getReturnCode()});
            return true;
        }
        // END   2018/03/22 [QC#20316, MOD]
        
        // Create CM_INV_PMT_HLD table record
        if (bizMsg.H.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
                // START 2018/02/27 [QC#23505, MOD]
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
                // END   2018/02/27 [QC#23505, MOD]
                // START 2018/03/28 [QC#24277, ADD]
                if ( ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())
                        || EMPTY_AP_VND_INV_LINE_NUM.equals(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
                }
                // END   2018/03/28 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1);
                // mod start 2022/02/15 QC#57090
                //if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                //    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.poNum_HT);
                //// START 2020/03/23 [QC#53413, ADD]
                //} else if (ZYPCommonFunc.hasValue(poNumListMap.get(cmInvPmtHldTMsg.apVndInvLineNum.getValue()))) {
                //    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, poNumListMap.get(cmInvPmtHldTMsg.apVndInvLineNum.getValue()));
                //// END   2020/03/23 [QC#53413, ADD]                    
                if (poNumListMap.containsKey(cmInvPmtHldTMsg.apVndInvLineNum.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, poNumListMap.get(cmInvPmtHldTMsg.apVndInvLineNum.getValue()));
                } else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.poNum_HT);
                // mod end 2022/02/15 QC#57090
                // START QC#25902,QC#25190,QC#25141
                } else if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum_H)) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.vndRtrnNum_H);
                // END QC#25902,QC#25190,QC#25141
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, NONE);
                }
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, bizMsg.H.no(i).pmtHldDt_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, bizMsg.H.no(i).pmtHldPsnCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, bizMsg.H.no(i).pmtHldRelPsnCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, bizMsg.H.no(i).pmtHldRelDt_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, bizMsg.H.no(i).pmtHldRelCmntTxt_H1.getValue());
                if (ZYPCommonFunc.hasValue(bizMsg.H.no(i).xxChkBox_H1.getValue())
                &&  bizMsg.H.no(i).xxChkBox_H1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                }

                // START 2018/03/28 [QC#24277, ADD]
                // Check Duplicated record
                String insFlg = bizMsg.H.no(i).xxInsUpdDelFlg_H1.getValue();
                if (insFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    for (int j = 0; j < bizMsg.H.getValidCount(); j++) {
                        if (bizMsg.H.no(i).pmtHldCd_H1.getValue().equals(bizMsg.H.no(j).pmtHldCd_H1.getValue())
                                && bizMsg.H.no(i).pmtHldRsnCd_H1.getValue().equals(bizMsg.H.no(j).pmtHldRsnCd_H1.getValue())
                                && bizMsg.H.no(j).xxInsUpdDelFlg_H1.getValue().equals(ZYPConstant.FLG_ON_Y)
                                && i != j) {
                            bizMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0181E, new String[] {"Hold Name and Hold Reason" });
                            bizMsg.H.no(i).pmtHldRsnCd_H1.setErrorInfo(1, NFBM0181E, new String[] {"Hold Name and Hold Reason" });
                            return true;
                            }
                        }
                }
                // Create new CM_INV_PMT_HLD record.
                if (insFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    CM_INV_PMT_HLDTMsg checkCmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKey(cmInvPmtHldTMsg);
                    if (checkCmInvPmtHldTMsg == null) {
                        EZDTBLAccessor.create(cmInvPmtHldTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NFBM0073E, new String[] {cmInvPmtHldTMsg.getTableName()});
                            return true;
                        } else {
                            bizMsg.H.no(i).xxInsUpdDelFlg_H1.clear();
                        }
                    } else if (checkCmInvPmtHldTMsg.pmtHldFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                        EZDTBLAccessor.update(cmInvPmtHldTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NFBM0224E, new String[] {cmInvPmtHldTMsg.getTableName()});
                            return true;
                        } else {
                            bizMsg.H.no(i).xxInsUpdDelFlg_H1.clear();
                        }
                    } else {
                        bizMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0181E, new String[] {"Hold Name and Hold Reason" });
                        bizMsg.H.no(i).pmtHldRsnCd_H1.setErrorInfo(1, NFBM0181E, new String[] {"Hold Name and Hold Reason" });
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkPmtHold(NFBL2040CMsg bizMsg) {
        if (bizMsg.H.getValidCount() > 0) {
            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, AP_VND_INV_SQ_NUM_00);
                // START 2018/03/28 [QC#24277, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())
                        || EMPTY_AP_VND_INV_LINE_NUM.equals(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, AP_VND_INV_LINE_NUM_0000);
                } else {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
                }
                // END   2018/03/28 [QC#24277, ADD]
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1);
                // if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                //     ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.poNum_HT);
                // } else {
                //     ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, NONE);
                // }
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, bizMsg.H.no(i).pmtHldDt_H1.getValue());
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, bizMsg.H.no(i).pmtHldPsnCd_H1.getValue());
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, bizMsg.H.no(i).pmtHldRelPsnCd_H1.getValue());
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, bizMsg.H.no(i).pmtHldRelDt_H1.getValue());
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue());
                // ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, bizMsg.H.no(i).pmtHldRelCmntTxt_H1.getValue());
                // if (ZYPCommonFunc.hasValue(bizMsg.H.no(i).xxChkBox_H1.getValue())
                // &&  bizMsg.H.no(i).xxChkBox_H1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                //     ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
                // } else {
                //     ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                // }

                // END   2018/03/28 [QC#24277, MOD]
                // Check Hold and Hold Release exists
                CM_INV_PMT_HLDTMsg checkCmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKey(cmInvPmtHldTMsg);
                if (checkCmInvPmtHldTMsg != null 
                        && bizMsg.H.no(i).xxChkBox_H1.getValue().equals(ZYPConstant.FLG_ON_Y)
                        && checkCmInvPmtHldTMsg.pmtHldFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    bizMsg.H.no(i).xxChkBox_H1.setErrorInfo(1, NFBM0272E);
                    return true;
                }
                // Check Duplicated record
                String insFlg = bizMsg.H.no(i).xxInsUpdDelFlg_H1.getValue();
                if (insFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    if (checkCmInvPmtHldTMsg != null && checkCmInvPmtHldTMsg.pmtHldFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                        bizMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NFBM0181E, new String[] {"Hold Name and Hold Reason" });
                        bizMsg.H.no(i).pmtHldRsnCd_H1.setErrorInfo(1, NFBM0181E, new String[] {"Hold Name and Hold Reason" });
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // START 2018/01/09 [QC#22143, MOD]
    // START 2018/01/04 [QC#22143, ADD]
    private boolean isInvoicePKModified(NFBL2040CMsg bizMsg) {
        return NFBL2040CommonLogic.isInvoicePKModified(bizMsg);
    }
    // END   2018/01/04 [QC#22143, ADD]
    // START 2018/01/09 [QC#22143, MOD]

    // START 2018/02/26 [QC#23505, ADD]
    private boolean checkSubmitForLinkedArcs(NFBL2040CMsg bizMsg) {
        boolean rtnVal = true;

        // START 2020/03/23 [QC#53413, DEL]
//        for (int i = 0 ; i < bizMsg.A.getValidCount() ; i++) {
//            if (!XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
//                continue;
//            }
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)
//                    && !bizMsg.poNum_HT.getValue().equals(bizMsg.A.no(i).poNum_A1.getValue())) {
//                bizMsg.poNum_HT.setErrorInfo(1, NFBM0281E, new String[] { "PO#", "Lines" } );
//                bizMsg.A.no(i).poNum_A1.setErrorInfo(1, NFBM0281E, new String[] { "PO#", "Lines" } );
//                rtnVal = false;
//            }
//        }
        // END   2020/03/23 [QC#53413, DEL]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxCmntTxt_A1)) {
                String xxCmntTxt = bizMsg.A.no(i).xxCmntTxt_A1.getValue();
                String[] strSplit = xxCmntTxt.split("\\.");
                if (!NFBL2040CommonLogic.checkGlCodeCombination(bizMsg, i, strSplit)) {
                    rtnVal = false;
                }
            }
        }

        // Exclusion check
        // START 2018/03/22 [QC#20316, MOD]
        // START 2018/03/06 [QC#23505, MOD]
        // CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
        // cmInvAcctHdrTMsg.setSQLID("001");
        // cmInvAcctHdrTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
        // cmInvAcctHdrTMsg.setConditionValue("prntVndCd01", bizMsg.prntVndCd.getValue());
        // cmInvAcctHdrTMsg.setConditionValue("apVndInvNum01", bizMsg.apVndInvNum.getValue());
        // CM_INV_ACCT_HDRTMsgArray cmInvAcctHdrTMsgArray = (CM_INV_ACCT_HDRTMsgArray) EZDTBLAccessor.findByCondition(cmInvAcctHdrTMsg);
        // if (cmInvAcctHdrTMsgArray.length() > 0) {
        //     CM_INV_ACCT_HDRTMsg tMsg = cmInvAcctHdrTMsgArray.no(0);
        //     if(!tMsg.apVndInvSqNum.getValue().equals(bizMsg.apVndInvSqNum_BK.getValue())) {
        //         bizMsg.setMessageInfo(NZZM0003E);
        //         return false;
        //     }
        // }
        // END   2018/03/06 [QC#23505, MOD]
        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        // QC#26475 Mod Start
        cmApInvHdrTMsg.setSQLID("002");
        //cmApInvHdrTMsg.setSQLID("001");
        // QC#26475 Mod End
        cmApInvHdrTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
        cmApInvHdrTMsg.setConditionValue("prntVndCd01", bizMsg.prntVndCd.getValue());
        cmApInvHdrTMsg.setConditionValue("apVndInvNum01", bizMsg.apVndInvNum.getValue());
        CM_AP_INV_HDRTMsgArray cmApInvHdrTMsgArray = (CM_AP_INV_HDRTMsgArray) EZDTBLAccessor.findByCondition(cmApInvHdrTMsg);
        if (cmApInvHdrTMsgArray.length() > 0) {
            CM_AP_INV_HDRTMsg tMsg = cmApInvHdrTMsgArray.no(0);
            if(!tMsg.apVndInvSqNum.getValue().equals(bizMsg.apVndInvSqNum_BK.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }
        }
        // END   2018/03/22 [QC#20316, MOD]

        // START 2020/03/23 [QC#53413, ADD]
        List<String> poNumList = new ArrayList<String>();
        for (int z = 0; z < bizMsg.A.getValidCount(); z++) {
            if (NFBL2040CommonLogic.chkMtPo (bizMsg, z)) {
                String poNum = bizMsg.A.no(z).poNum_A1.getValue();
                if (!poNumList.contains(poNum)) {
                    poNumList.add(poNum);
                }
            }
        }
        // END   2020/03/23 [QC#53413, ADD]

        // START 2020/03/23 [QC#53413, MOD]
        // Existence check
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            if (poNumList.size() == 1) {
                S21SsmEZDResult ssmResult = null;
                ssmResult = NFBL2040Query.getInstance().getPoInfoIncludeClosed(poNumList.get(0), bizMsg.prntVndCd.getValue(), null);
                if (ssmResult.isCodeNormal()) {
                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                    if (resultList.size() == 0) {
                        bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
                        rtnVal = false;
                    }
                } else {
                    bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
                    rtnVal = false;
                }
            }
            else {
                for (int j = 0; j < poNumList.size(); j ++) {
                    S21SsmEZDResult ssmResult = null;
                    ssmResult = NFBL2040Query.getInstance().getPoInfoIncludeClosed(poNumList.get(j), bizMsg.prntVndCd.getValue(), null);
                    if (ssmResult.isCodeNormal()) {
                        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                        if (resultList.size() == 0) {
                            for (int k = 0; k < bizMsg.A.getValidCount(); k++) {
                                if (ZYPCommonFunc.hasValue(bizMsg.A.no(k).poNum_A1)) {
                                    if (poNumList.get(j).equals(bizMsg.A.no(k).poNum_A1.getValue())) {
                                        bizMsg.A.no(k).poNum_A1.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
                                    }
                                }
                            }
                            rtnVal = false;
                        }
                    } else {
                        for (int k = 0; k < bizMsg.A.getValidCount(); k++) {
                            if (ZYPCommonFunc.hasValue(bizMsg.A.no(k).poNum_A1)) {
                                if (poNumList.get(j).equals(bizMsg.A.no(k).poNum_A1.getValue())) {
                                    bizMsg.A.no(k).poNum_A1.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
                                }
                            }
                        }
                        rtnVal = false;
                    }
                }
            }
        }

//        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
//            if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
//                S21SsmEZDResult ssmResult = null;
//                // START 2018/03/06 [QC#23505, MOD]
//                // ssmResult = NFBL2040Query.getInstance().getPoInfoIncludeClosed(bizMsg.poNum_HT.getValue());
//                // START 2018/10/09 [QC#28099-1, MOD]
//                // ssmResult = NFBL2040Query.getInstance().getPoInfoIncludeClosed(bizMsg.poNum_HT.getValue(),
//                //         bizMsg.prntVndCd.getValue(), bizMsg.apVndCd.getValue());
//                ssmResult = NFBL2040Query.getInstance().getPoInfoIncludeClosed(bizMsg.poNum_HT.getValue(),
//                        bizMsg.prntVndCd.getValue(), null);
//                // END   2018/10/09 [QC#28099-1, MOD]
//                // END   2018/03/06 [QC#23505, MOD]
//                if (ssmResult.isCodeNormal()) {
//                    List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                    if (resultList.size() == 0) {
//                        bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
//                        rtnVal = false;
//                    }
//                } else {
//                    bizMsg.poNum_HT.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
//                    rtnVal = false;
//                }
//            }
//            // START 2020/03/23 [QC#53413, ADD]
//            else {
//                for (int j = 0; j < poNumList.size(); j ++) {
//                    S21SsmEZDResult ssmResult = null;
//                    ssmResult = NFBL2040Query.getInstance().getPoInfoIncludeClosed(poNumList.get(j), bizMsg.prntVndCd.getValue(), null);
//                    if (ssmResult.isCodeNormal()) {
//                        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                        if (resultList.size() == 0) {
//                            for (int k = 0; k < bizMsg.A.getValidCount(); k++) {
//                                if (ZYPCommonFunc.hasValue(bizMsg.A.no(k).poNum_A1)) {
//                                    if (poNumList.get(j).equals(bizMsg.A.no(k).poNum_A1.getValue())) {
//                                        bizMsg.A.no(k).poNum_A1.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
//                                    }
//                                }
//                            }
//                            rtnVal = false;
//                        }
//                    } else {
//                        for (int k = 0; k < bizMsg.A.getValidCount(); k++) {
//                            if (ZYPCommonFunc.hasValue(bizMsg.A.no(k).poNum_A1)) {
//                                if (poNumList.get(j).equals(bizMsg.A.no(k).poNum_A1.getValue())) {
//                                    bizMsg.A.no(k).poNum_A1.setErrorInfo(1, NFBM0235E, new String[] {"PO#", "Purchase Order" });
//                                }
//                            }
//                        }
//                        rtnVal = false;
//                    }
//                }
//            }
//        }
          // END   2020/03/23 [QC#53413, MOD]

        return rtnVal;
    }

    private void openPoStatus(NFBL2040CMsg bizMsg, String poNum) {
        ArrayList<PO_DTLTMsg> poDtlList = NFBL2040CommonLogic.getPoDtlListForUpdateNoWait(poNum);
        for (int i = 0 ; i < bizMsg.A.getValidCount() ; i++) {
            if (!XX_LINE_TP_CD_INVALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                continue;
            }
            if (Integer.valueOf(bizMsg.apVndInvSqNum_BK.getValue(), 10) 
                    != Integer.valueOf(bizMsg.A.no(i).apVndInvSqNum_A1.getValue(), 10)) {
                continue;
            }

            // START 2018/08/28 [QC#27886, DEL]
            // BigDecimal invQty = bizMsg.A.no(i).invQty_A1.getValue();
            // END   2018/08/28 [QC#27886, DEL]
            for (PO_DTLTMsg poDtl : poDtlList) {
                // START 2018/08/28 [QC#27886, MOD]
                // String invMdseCd = bizMsg.A.no(i).mdseCd_A1.getValue();
                // BigDecimal poInvQty = poDtl.poInvQty.getValue();
                String poMdseCd = poDtl.mdseCd.getValue();
                // END   2018/08/28 [QC#27886, MOD]

                // START 2018/08/28 [QC#27886, MOD]
                // if (!invMdseCd.equals(poMdseCd)) {
                //     continue;
                // }
                String poOrdDtlLineNum = bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue();
                String poOrdDtlLineNumOfPO = poDtl.poOrdDtlLineNum.getValue();
                if (!poOrdDtlLineNumOfPO.equals(poOrdDtlLineNum)) {
                    continue;
                }
                // END   2018/08/28 [QC#27886, MOD]

                BigDecimal upInvQty = bizMsg.A.no(i).apBillQty_A1.getValue();
                // START 2018/08/28 [QC#27886, DEL]
                // if (poInvQty.compareTo(upInvQty) < 0) {
                //     upInvQty = poInvQty;
                // }
                // END   2018/08/28 [QC#27886, DEL]

                // START 2018/07/31 [QC#27025-1, MOD]
                // update PO Information 
                String msgId = NFBL2040CommonLogic.updatePoInformation(
                        poDtl.poOrdNum.getValue(), poDtl.poOrdDtlLineNum.getValue(), upInvQty.negate(), poMdseCd);
                if (msgId != null) {
                    bizMsg.setMessageInfo(msgId);
                    return;
                }
                // END   2018/07/31 [QC#27025-1, MOD]

                // START 2018/08/28 [QC#27886, DEL]
                // invQty = invQty.add(poInvQty.negate());
                //
                // if (invQty.compareTo(BigDecimal.ZERO) <= 0) {
                //     break;
                // }
                // END   2018/08/28 [QC#27886, DEL]
            }
        }
    }
    
    private void submitLinkedInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        if (!checkSubmitForLinkedArcs(bizMsg)) {
            return;
        }

        // Cancel assigned status on CM_STK_IN table.
        clearStatusCmStkIn(bizMsg);

        // assign CM_STK_IN to new invoice line
        assignCmStkInToInvoice(bizMsg);

        // Open PO associated with old invoice lines
        String oldPoNum = null;
        // START 2020/03/23 [QC#53413, ADD]
        List<String> poNumList = new ArrayList<String>();
        // END   2020/03/23 [QC#53413, ADD]
        for (int i = 0 ; i < bizMsg.A.getValidCount() ; i++) {
            if (!XX_LINE_TP_CD_INVALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                continue;
            }
            if (Integer.valueOf(bizMsg.apVndInvSqNum_BK.getValue(), 10) 
                    != Integer.valueOf(bizMsg.A.no(i).apVndInvSqNum_A1.getValue(), 10)) {
                continue;
            }

            if (!NFBL2040CommonLogic.chkMtPo (bizMsg, i)) {
                oldPoNum = bizMsg.A.no(i).poNum_A1.getValue();
                // START 2020/03/23 [QC#53413, MOD]
                if (ZYPCommonFunc.hasValue(oldPoNum)) {
                    if (!poNumList.contains(oldPoNum)) {
                        poNumList.add(oldPoNum);
                    }
                }
                //break;
                // END   2020/03/23 [QC#53413, MOD]
            }
        }
        // START 2020/03/23 [QC#53413, MOD]
        for (int j = 0; j < poNumList.size(); j++) {
            openPoStatus(bizMsg, poNumList.get(j));
        }
        //if (ZYPCommonFunc.hasValue(oldPoNum)) {
        //    openPoStatus(bizMsg, oldPoNum);
        //}
        // END   2020/03/23 [QC#53413, MOD]

        // refresh Holds
        refreshHolds(bizMsg);

        // refresh Distribution
        NFBL2040CommonLogic.commonRefreshDistributionsTab(bizMsg, USER_ID);

        // START 2018/03/22 [QC#20316, DEL]
        // update CM_INV_ACCT_HDR
        // CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = updateCmInvAcctHdrForLinkedInvoice(bizMsg);
        // if (cmInvAcctHdrTMsg == null) {
        //     return;
        // }
        // END   2018/03/22 [QC#20316, DEL]

        // insert CM_INV_PMT_HLD
        int hldRecCnt = insertCmInvPmtHldForLinkedInvoice(bizMsg);

        // update CM_AP_INV_HDR
        // START 2018/03/22 [QC#20316, MOD]
        // updateCmApInvHdrForLinkedInvoice(bizMsg, cmInvAcctHdrTMsg);
        updateCmApInvHdrForLinkedInvoice(bizMsg);
        // END   2018/03/22 [QC#20316, MOD]

        // insert CM_AP_INV_DTL, CM_INV_ACCT_DIST
        insertDtlDistTables(bizMsg, (hldRecCnt > 0));

        if (NFBL2040CommonLogic.refreshData(bizMsg)) {
            return;
        }

        // clear submit flag
        bizMsg.xxBtnFlg_CR.clear();
        
        bizMsg.setMessageInfo(ZZM8100I);
    }

// START 2018/03/22 [QC#20316, DEL]
//    private CM_INV_ACCT_HDRTMsg updateCmInvAcctHdrForLinkedInvoice(NFBL2040CMsg bizMsg) {
//
//        CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg = new CM_INV_ACCT_HDRTMsg();
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_BK.getValue());
//        cmInvAcctHdrTMsg = (CM_INV_ACCT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmInvAcctHdrTMsg);
//        if (cmInvAcctHdrTMsg == null) {
//            bizMsg.setMessageInfo(NFBM0069E);
//            return null;
//        }
//
//        // logical remove
//        EZDTBLAccessor.logicalRemove(cmInvAcctHdrTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctHdrTMsg.getReturnCode())) {
//            bizMsg.setMessageInfo(NFBM0069E);
//            return null;
//        }
//
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum.getValue());
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.poNum, bizMsg.poNum);
//        ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.invHdrDescTxt, bizMsg.invHdrDescTxt.getValue());
//        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_HO)
//                && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_HO.getValue())) {
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
//        } else {
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//        }
//
//        // Set Hold Information on CM_INV_ACCT_HDR table.
//        if (bizMsg.H.getValidCount() > 0) {
//            boolean isHold = false;
//            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
//                if (!ZYPCommonFunc.hasValue(bizMsg.H.no(i).xxChkBox_H1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(i).xxChkBox_H1, ZYPConstant.FLG_OFF_N);
//                }
//                if (!isHold 
//                &&   bizMsg.H.no(i).xxChkBox_H1.getValue().equals(ZYPConstant.FLG_OFF_N)
//                ) {
//                    isHold = true;
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue()); // Hold name
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldDt, bizMsg.H.no(i).pmtHldDt_H1.getValue()); 
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1.getValue()); // Hold Reason
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldPsnCd, bizMsg.H.no(i).pmtHldPsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, bizMsg.H.no(i).pmtHldRelPsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, bizMsg.H.no(i).pmtHldRelDt_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue());
//                    ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, bizMsg.H.no(i).pmtHldRelCmntTxt_H1.getValue());
//                }
//            }
//            if (!isHold) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, bizMsg.H.no(0).pmtHldCd_H1.getValue()); // Hold name
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldDt, bizMsg.H.no(0).pmtHldDt_H1.getValue()); 
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, bizMsg.H.no(0).pmtHldRsnCd_H1.getValue()); // Hold Reason
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldPsnCd, bizMsg.H.no(0).pmtHldPsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelPsnCd, bizMsg.H.no(0).pmtHldRelPsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelDt, bizMsg.H.no(0).pmtHldRelDt_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelRsnCd, bizMsg.H.no(0).pmtHldRelRsnCd_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRelCmntTxt, bizMsg.H.no(0).pmtHldRelCmntTxt_H1.getValue());
//            }
//        } else {
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldCd, PMT_HLD_CD_000);
//            cmInvAcctHdrTMsg.pmtHldDt.clear();
//            ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.pmtHldRsnCd, PMT_HLD_RSN_CD_000);
//            cmInvAcctHdrTMsg.pmtHldPsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelPsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelDt.clear();
//            cmInvAcctHdrTMsg.pmtHldRelRsnCd.clear();
//            cmInvAcctHdrTMsg.pmtHldRelCmntTxt.clear();
//        }
//
//        cmInvAcctHdrTMsg.coaCmpyCd.clear();
//        cmInvAcctHdrTMsg.coaBrCd.clear();
//        cmInvAcctHdrTMsg.coaCcCd.clear();
//        cmInvAcctHdrTMsg.coaAcctCd.clear();
//        cmInvAcctHdrTMsg.coaProdCd.clear();
//        cmInvAcctHdrTMsg.coaChCd.clear();
//        cmInvAcctHdrTMsg.coaAfflCd.clear();
//        cmInvAcctHdrTMsg.coaProjCd.clear();
//        cmInvAcctHdrTMsg.coaExtnCd.clear();
//
//        int curTopIdx = 0;
//        for (int i = 0 ; i < bizMsg.A.getValidCount() ; i++) {
//            if (bizMsg.apVndInvSqNum.getValue().equals(bizMsg.A.no(i).apVndInvSqNum_A1.getValue())) {
//                curTopIdx = i;
//                break;
//            }
//        }
//
//        // QC#14858-Sol#060 Update. Set Coa Infomation from first record.
//        if (ZYPCommonFunc.hasValue(bizMsg.A.no(curTopIdx).xxCmntTxt_A1)) {
//            String xxCmntTxt = bizMsg.A.no(curTopIdx).xxCmntTxt_A1.getValue();
//            String[] strSplit = xxCmntTxt.split("\\.");
//            if (!NFBL2040CommonLogic.checkGlCodeCombination(bizMsg, 0, strSplit)) {
//                return null;
//            }
//            if (strSplit.length > 0) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaCmpyCd, strSplit[0]);
//            }
//            if (strSplit.length > 1) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaBrCd, strSplit[1]);
//            }
//            if (strSplit.length > 2) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaCcCd, strSplit[2]);
//            }
//            if (strSplit.length > 3) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaAcctCd, strSplit[3]);
//            }
//            if (strSplit.length > 4) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaProdCd, strSplit[4]);
//            }
//            if (strSplit.length > 5) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaChCd, strSplit[5]);
//            }
//            if (strSplit.length > 6) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaAfflCd, strSplit[6]);
//            }
//            if (strSplit.length > 7) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaProjCd, strSplit[7]);
//            }
//            if (strSplit.length > 8) {
//                ZYPEZDItemValueSetter.setValue(cmInvAcctHdrTMsg.coaExtnCd, strSplit[8]);
//            }
//        }
//
//        // Insert invoice record.
//        EZDTBLAccessor.insert(cmInvAcctHdrTMsg);
//        if (!RTNCD_NORMAL.equals(cmInvAcctHdrTMsg.getReturnCode())) {
//            bizMsg.setMessageInfo(NFBM0224E, new String[] {cmInvAcctHdrTMsg.getTableName(), cmInvAcctHdrTMsg.getReturnCode() });
//        }
//
//        return cmInvAcctHdrTMsg;
//    }
// END   2018/03/22 [QC#20316, DEL]

    private int insertCmInvPmtHldForLinkedInvoice(NFBL2040CMsg bizMsg) {
        int recCnt = 0;
        
        if (bizMsg.H.getValidCount() == 0) {
            return recCnt;
        }

        // START 2020/03/23 [QC#53413, ADD]
        Map<String, String> poNumListMap = new HashMap<String,String>();
        for (int z = 0; z < bizMsg.A.getValidCount(); z++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(z).poNum_A1)) {
                String poNum = bizMsg.A.no(z).poNum_A1.getValue();
                poNumListMap.put(bizMsg.A.no(z).xxDtlLineNum_A1.getValue(), poNum);
            }
        }
        // END   2020/03/23 [QC#53413, ADD]

        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum.getValue());
            // START 2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
            // END   2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue()); 
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1);

            boolean updHldRecFlg = false;
            // START 2019/02/21 [QC#30420, MOD]
            boolean delHldRecFlg = false;
            // END   2019/02/21 [QC#30420, MOD]
            CM_INV_PMT_HLDTMsg updCmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
            ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
            ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
            ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum.getValue());
            // START 2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1.getValue());
            // END   2018/03/28 [QC#24277, ADD]
            ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1.getValue()); 
            ZYPEZDItemValueSetter.setValue(updCmInvPmtHldTMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1);
            updCmInvPmtHldTMsg = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(updCmInvPmtHldTMsg);
            if (updCmInvPmtHldTMsg != null) {
                // START 2019/02/21 [QC#30420, MOD]
                // cmInvPmtHldTMsg = updCmInvPmtHldTMsg;
                // updHldRecFlg = true;
                cmInvPmtHldTMsg = updCmInvPmtHldTMsg;

                int aIndex = findInvoiceLineIndex(bizMsg, bizMsg.H.no(i));
                if (aIndex != -1) {
                    BigDecimal lineAmt = NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue());
                    BigDecimal invQty = NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue());
                    if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
                        delHldRecFlg = true;
                    } else {
                        updHldRecFlg = true;
                    }
                } else {
                    updHldRecFlg = true;
                }
                // END   2019/02/21 [QC#30420, MOD]
            }

            if (!bizMsg.apVndInvSqNum.getValue().equals(cmInvPmtHldTMsg.apVndInvSqNum.getValue())) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, bizMsg.poNum_HT);
            // START 2020/03/23 [QC#53413, ADD]
            } else if (poNumListMap.containsKey(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, poNumListMap.get(bizMsg.H.no(i).apVndInvLineNum_H1.getValue()));
            // END   2020/03/23 [QC#53413, ADD]
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.poNum, NONE);
            }
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, bizMsg.H.no(i).pmtHldDt_H1.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, bizMsg.H.no(i).pmtHldPsnCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelPsnCd, bizMsg.H.no(i).pmtHldRelPsnCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelDt, bizMsg.H.no(i).pmtHldRelDt_H1.getValue());
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelRsnCd, bizMsg.H.no(i).pmtHldRelRsnCd_H1.getValue()); 
            ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldRelCmntTxt, bizMsg.H.no(i).pmtHldRelCmntTxt_H1.getValue());
            if (ZYPCommonFunc.hasValue(bizMsg.H.no(i).xxChkBox_H1.getValue())
            &&  bizMsg.H.no(i).xxChkBox_H1.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
            }

            if (updHldRecFlg) {
                // Update CM_INV_PMT_HLD record.
                EZDTBLAccessor.update(cmInvPmtHldTMsg);
                if (!RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NFBM0224E, new String[] {cmInvPmtHldTMsg.getTableName(), cmInvPmtHldTMsg.getReturnCode() });
                    return -1;
                }
            // START 2019/02/21 [QC#30420, MOD]
            } else if (delHldRecFlg) {
                // Delete CM_INV_PMT_HLD record.
                EZDTBLAccessor.remove(cmInvPmtHldTMsg);
                if (!RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NFBM0224E, new String[] {cmInvPmtHldTMsg.getTableName(), cmInvPmtHldTMsg.getReturnCode() });
                    return -1;
                }
            // END   2019/02/21 [QC#30420, MOD]
            } else {
                // Create new CM_INV_PMT_HLD record.
                EZDTBLAccessor.create(cmInvPmtHldTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                   bizMsg.setMessageInfo(NFBM0073E, new String[] {cmInvPmtHldTMsg.getTableName(), cmInvPmtHldTMsg.getReturnCode() });
                   return -1;
                }
            }
            recCnt++;
        }
        return recCnt;
    }

    // START 2018/03/22 [QC#20316, MOD]
    // private void updateCmApInvHdrForLinkedInvoice(NFBL2040CMsg bizMsg, CM_INV_ACCT_HDRTMsg cmInvAcctHdrTMsg) {
    private void updateCmApInvHdrForLinkedInvoice(NFBL2040CMsg bizMsg) {
    // END   2018/03/22 [QC#20316, MOD]

        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum_BK.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);

        cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
        if (cmApInvHdrTMsg == null) {
            bizMsg.setMessageInfo(NFBM0069E);
            return;
        }

        // logical remove
        EZDTBLAccessor.logicalRemove(cmApInvHdrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFBM0069E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum.getValue());
        // START 2018/03/22 [QC#20316, MOD]
        // START 2020/03/23 [QC#53413, MOD]
        String poNum = null;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++ ) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxLineTpCd_A1) && XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                    poNum = bizMsg.A.no(i).poNum_A1.getValue();
                    break;
                }
            }
        }
        if (!ZYPCommonFunc.hasValue(poNum) && ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            poNum = bizMsg.poNum.getValue();
        }
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.poNum, poNum);
        // END   2020/03/23 [QC#53413, MOD]
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.invHdrDescTxt, bizMsg.invHdrDescTxt.getValue());
        // END   2018/03/22 [QC#20316, MOD]
        
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_HO)
        &&  ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_HO.getValue())) {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_OFF_N);
        }

        // QC#14858-Sol#060 Update. Set Coa Infomation from first record.
        // START 2018/03/22 [QC#20316, MOD]
        // String coaAcctCd = cmInvAcctHdrTMsg.coaAcctCd.getValue();
        // String coaProjCd = cmInvAcctHdrTMsg.coaProjCd.getValue();
        String coaAcctCd = null;
        String coaProjCd = null;
        String xxCmntTxtHdr = bizMsg.A.no(0).xxCmntTxt_A1.getValue();
        String[] strSplitHdr = xxCmntTxtHdr.split("\\.");
        if (strSplitHdr.length > 3) {
            coaAcctCd = strSplitHdr[3];
        }
        if (strSplitHdr.length > 7) {
            coaProjCd = strSplitHdr[7];
        }
        // END   2018/03/22 [QC#20316, MOD]
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.coaAcctCd, coaAcctCd);
        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.coaProjCd, coaProjCd);
        // START 2020/03/23 [QC#53413, DEL]
        //ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.poNum, bizMsg.poNum_HT);
        // END   2020/03/23 [QC#53413, DEL]
        // Insert invoice record.
        EZDTBLAccessor.insert(cmApInvHdrTMsg);
        if (!RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFBM0224E, new String[] {cmApInvHdrTMsg.getTableName(), cmApInvHdrTMsg.getReturnCode() });
            return;
        }
    }

    private void insertDtlDistTables(NFBL2040CMsg bizMsg, boolean existsHold) {

        // Currency Code
        String ccyCd = NFBL2040CommonLogic.getCcyCd();

        // Exchange Rate
        BigDecimal exchRate = BigDecimal.ONE;
        ACCT_DLY_ACTL_EXCH_RATESTMsg acctDlyActlExchRatesTMsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.ccyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(acctDlyActlExchRatesTMsg.actlExchRateEntDt,ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
        acctDlyActlExchRatesTMsg = (ACCT_DLY_ACTL_EXCH_RATESTMsg) S21FastTBLAccessor.findByKey(acctDlyActlExchRatesTMsg);
        if (acctDlyActlExchRatesTMsg != null) {
            exchRate = acctDlyActlExchRatesTMsg.actlExchRate.getValue();
        }

        // START 2018/11/05 [QC#28982, ADD]
        CCYTMsg ccyMsg = NFBCommonBusiness.getCcyInfo(GLBL_CMPY_CD, ccyCd);
        // END   2018/11/05 [QC#28982, ADD]

        // START 2018/08/21 [QC#27873, ADD]
        String xxCmntTxtCr = NFBL2040CommonLogic.getCreditLineAccountCode(bizMsg.apVndCd.getValue());
        // END   2018/08/21 [QC#27873, ADD]

        // START 2019/02/07 [QC#30136, ADD]
        boolean isBlanketPO = ZYPCommonFunc.hasValue(bizMsg.poNum) && NFBL2040CommonLogic.isBlanketPO(bizMsg.poNum.getValue());
        // END   2019/02/07 [QC#30136, ADD]

        // Create record on CM_INV_ACCT_DIST table.
        Map<List<String>,Map<String, Object>> cmInvAcctDistMap = new HashMap<List<String>,Map<String, Object>>();
        List<List<String>> cmInvAcctDistPkList = new ArrayList<List<String>>();
        // Create record on CM_AP_INV_DTL table.
        Map<List<String>,Map<String, Object>> cmApInvDtlMap = new HashMap<List<String>,Map<String, Object>>();
        List<List<String>> cmApInvDtlPkList = new ArrayList<List<String>>();
        int commitLineNumForInv = 1;
        int commitLineNumForApInv = 1;
        // int apVndInvLineNumItem = 1;
        // int apVndInvLineNum = 1;
        // START 2019/02/21 [QC#30420, DEL]
        // int itemCnt = 0;
        // for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //     if ((!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
        //             && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)
        //             && bizMsg.A.no(i).apBillQty_A1.getValue().compareTo(BigDecimal.ZERO) != 0
        //             && bizMsg.A.no(i).xxInvAmt_A1.getValue().compareTo(BigDecimal.ZERO) != 0)
        //         || bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)) {
        //         itemCnt++;
        //     }
        // }
        // int apVndInvLineNumOther = itemCnt + 1;
        // END   2019/02/21 [QC#30420, DEL]
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            // skip record if apVndInvSqNum_BK >= apVndInvSqNum_A1
            if (Integer.valueOf(bizMsg.apVndInvSqNum_BK.getValue(), 10) 
                        >= Integer.valueOf(bizMsg.A.no(i).apVndInvSqNum_A1.getValue(), 10)) {
                continue;
            }

            // CM_INV_ACCT_DIST table primary key values.
            List<String> pkList = new ArrayList<String>();
            // START 2018/04/04 [QC#20316, MOD]
            // pkList.add(GLBL_CMPY_CD);
            // pkList.add(bizMsg.apVndCd.getValue());
            // pkList.add(bizMsg.apVndInvNum.getValue());
            // pkList.add(bizMsg.A.no(i).apVndInvSqNum_A1.getValue());
            // // QC#14858-Sol#060 text item.
            // if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
            //     pkList.add(bizMsg.A.no(i).xxMdseCd_A1.getValue());
            // } else {
            //     pkList.add(bizMsg.A.no(i).mdseCd_A1.getValue());
            // }
            // pkList.add(AP_INV_TP_CD_00);
            // pkList.add(bizMsg.A.no(i).poNum_A1.getValue());
            // pkList.add(bizMsg.A.no(i).delyOrdNum_A1.getValue());
            // pkList.add(bizMsg.A.no(i).apLineTpCd_A1.getValue());
            pkList.add(GLBL_CMPY_CD);
            pkList.add(bizMsg.apVndCd.getValue());
            pkList.add(bizMsg.apVndInvNum.getValue());
            pkList.add(bizMsg.A.no(i).apVndInvSqNum_A1.getValue());
            // START 2019/02/21 [QC#30420, MOD]
            // // QC#24985
            // if (bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM) || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)) {
            //     pkList.add(bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
            // } else {
            //     pkList.add(String.format(AP_VND_INV_LINE_NUM_FORMAT, apVndInvLineNumOther));
            //     apVndInvLineNumOther++;
            // }
            pkList.add(bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
            // END   2019/02/21 [QC#30420, MOD]
            pkList.add(String.format(LINE_NUM_FORMAT, 1));
            // END   2018/04/04 [QC#20316, MOD]

            // CM_INV_ACCT_DIST table non primary key values.
            Map<String, Object> cmInvAcctDistNonPrimaryKeyMap = cmInvAcctDistMap.get(pkList);
            // START 2018/03/16 [QC#24089, MOD]
            // if (bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
            //         || (!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
            //                 && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)
            //                 // && bizMsg.A.no(i).apBillQty_A1.getValue().compareTo(BigDecimal.ZERO) > 0
            //                 // && bizMsg.A.no(i).xxInvAmt_A1.getValue().compareTo(BigDecimal.ZERO) >= 0)
            //                 && bizMsg.A.no(i).apBillQty_A1.getValue().compareTo(BigDecimal.ZERO) != 0
            //                 && bizMsg.A.no(i).xxInvAmt_A1.getValue().compareTo(BigDecimal.ZERO) != 0)
            //                 || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.TAX)
            //                 || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)) {
            if (bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
                    || (!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
                            && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM))
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.TAX)
                            // START QC#25902,QC#25190,QC#25141
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.MISC)) {
                            // END QC#25902,QC#25190,QC#25141
            // END   2018/03/16 [QC#24089, MOD]
                if (cmInvAcctDistNonPrimaryKeyMap == null) {
                    // START 2018/04/04 [QC#20316, DEL]
                    // int cmInvAcctDistLineNum = 1;
                    // END   2018/04/04 [QC#20316, DEL]
                    // Not exists primary key in the map
                    cmInvAcctDistPkList.add(pkList);
                    cmInvAcctDistNonPrimaryKeyMap = new HashMap<String, Object>();
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_TP_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(PO_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_RCV_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).invRcvQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_BILL_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_REJ_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).apRejQty_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(UOM_CD, bizMsg.A.no(i).vndUomCd_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(THIS_MTH_FOB_COST_AMT, BigDecimal.ZERO);
                    cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_TAX_AMT, BigDecimal.ZERO);
                    // QC#19261
                    if (bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)) {
                      cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                      // START 2018/06/20 [QC#26169, ADD]
                      // START 2019/02/07 [QC#30136, MOD]
                      // if (NFBL2040CommonLogic.hasVariance(bizMsg.A.no(i))) {
                      if (NFBL2040CommonLogic.hasVariance(bizMsg.A.no(i)) && !isBlanketPO) {
                      // END   2019/02/07 [QC#30136, MOD]
                          // START 2018/08/15 [QC#27029-1, MOD]
                          // cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                          if (NFBL2040CommonLogic.isEffectiveStkInPrice(bizMsg.A.no(i))) {
                              cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));

                          } else if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3)) {
                              cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A3.getValue()).multiply(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                          }
                          // END   2018/08/15 [QC#27029-1, MOD]
                      }
                      // END   2018/06/20 [QC#26169, ADD]
                    } else {
                      cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    }
                    cmInvAcctDistNonPrimaryKeyMap.put(LINE_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_ASG_DT, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                    cmInvAcctDistNonPrimaryKeyMap.put(STK_IN_DT, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CSMP_INV_NUM, bizMsg.A.no(i).csmpInvNum_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(SER_NUM, bizMsg.A.no(i).serNum_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(CONTR_NUM, bizMsg.A.no(i).dsContrNum_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(CUST_DLR_CD, bizMsg.A.no(i).custDlrCd_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_FIRST_LINE_ADDR, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_FL_NM, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_CTY_ADDR, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_ST_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(ISTL_LOC_POST_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_LINE_DESC_TXT, null);
                    // START 2018/03/22 [QC#20316, MOD]
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CMPY_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_BR_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CC_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_ACCT_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_PROD_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CH_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_AFFL_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_PROJ_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_EXTN_CD, null);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxCmntTxt_A1)) {
                        String xxCmntTxt = bizMsg.A.no(i).xxCmntTxt_A1.getValue();
                        String[] strSplit = xxCmntTxt.split("\\.");
                        if (!NFBL2040CommonLogic.checkGlCodeCombination(bizMsg, i, strSplit)) {
                            return;
                        }
                        if (strSplit.length > 0) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CMPY_CD, strSplit[0]);
                        }
                        if (strSplit.length > 1) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_BR_CD, strSplit[1]);
                        }
                        if (strSplit.length > 2) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CC_CD, strSplit[2]);
                        }
                        if (strSplit.length > 3) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_ACCT_CD, strSplit[3]);
                        }
                        if (strSplit.length > 4) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_PROD_CD, strSplit[4]);
                        }
                        if (strSplit.length > 5) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_CH_CD, strSplit[5]);
                        }
                        if (strSplit.length > 6) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_AFFL_CD, strSplit[6]);
                        }
                        if (strSplit.length > 7) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_PROJ_CD, strSplit[7]);
                        }
                        if (strSplit.length > 8) {
                            cmInvAcctDistNonPrimaryKeyMap.put(DR_COA_EXTN_CD, strSplit[8]);
                        }
                    }
                    // END   2018/03/22 [QC#20316, MOD]
                    // START 2018/03/22 [QC#20316, ADD]
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CMPY_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_BR_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CC_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_ACCT_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_PROD_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CH_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_AFFL_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_PROJ_CD, null);
                    cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_EXTN_CD, null);
                    // START 2018/08/21 [QC#27873, MOD]
                    // if (ZYPCommonFunc.hasValue(bizMsg.A.no(0).xxCmntTxt_A1)) {
                    //     String xxCmntTxt = bizMsg.A.no(0).xxCmntTxt_A1.getValue();
                    //     String[] strSplit = xxCmntTxt.split("\\.");
                    if (ZYPCommonFunc.hasValue(xxCmntTxtCr)) {
                        String[] strSplit = xxCmntTxtCr.split("\\.");
                    // END   2018/08/21 [QC#27873, MOD]
                        // START 2018/07/17 S.Katsuma [QC#27078,DEL]
//                        if (!NFBL2040CommonLogic.checkGlCodeCombination(bizMsg, i, strSplit)) {
//                            return;
//                        }
                        // END 2018/07/17 S.Katsuma [QC#27078,DEL]
                        if (strSplit.length > 0) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CMPY_CD, strSplit[0]);
                        }
                        if (strSplit.length > 1) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_BR_CD, strSplit[1]);
                        }
                        if (strSplit.length > 2) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CC_CD, strSplit[2]);
                        }
                        if (strSplit.length > 3) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_ACCT_CD, strSplit[3]);
                        }
                        if (strSplit.length > 4) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_PROD_CD, strSplit[4]);
                        }
                        if (strSplit.length > 5) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_CH_CD, strSplit[5]);
                        }
                        if (strSplit.length > 6) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_AFFL_CD, strSplit[6]);
                        }
                        if (strSplit.length > 7) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_PROJ_CD, strSplit[7]);
                        }
                        if (strSplit.length > 8) {
                            cmInvAcctDistNonPrimaryKeyMap.put(CR_COA_EXTN_CD, strSplit[8]);
                        }
                    }
                    // END   2018/03/22 [QC#20316, ADD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).apAcctDescTxt_A1, NFBL2040CommonLogic.getCoaAcctDescTxt(GLBL_CMPY_CD, (String) cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_ACCT_CD)));
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_INV_DESC_TXT, bizMsg.A.no(i).apAcctDescTxt_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_ACCT_DESC_TXT, bizMsg.A.no(i).apAcctDescTxt_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(IF_PROC_STS_CD, ZYPConstant.FLG_OFF_N);
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_INV_AUTH_FLG, ZYPConstant.FLG_OFF_N);
                    // QC#23505
                    // cmInvAcctDistNonPrimaryKeyMap.put(AP_JRNL_CPLT_FLG, ZYPConstant.FLG_OFF_N);
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_JRNL_CPLT_FLG, ZYPConstant.FLG_ON_Y);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_DEAL_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_DEAL_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue()));
                    // START 2018/11/05 [QC#28982, MOD]
                    // cmInvAcctDistNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, BigDecimal.ZERO);
                    // cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, BigDecimal.ZERO);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, BigDecimal.ZERO);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1)) {
                        BigDecimal entFuncNetUnitPrcAmt = NFBCommonBusiness.calcStdAmt(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue(),
                                exchRate, ccyMsg.acctArthTpCd.getValue(), ccyMsg.aftDeclPntDigitNum.getValue());
                        cmInvAcctDistNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, entFuncNetUnitPrcAmt);
                    }
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, BigDecimal.ZERO);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A1)) {
                        BigDecimal entPoDtlFuncNetAmt = NFBCommonBusiness.calcStdAmt(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue(),
                                exchRate, ccyMsg.acctArthTpCd.getValue(), ccyMsg.aftDeclPntDigitNum.getValue());
                        cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, entPoDtlFuncNetAmt);
                    }
                    // END   2018/11/05 [QC#28982, MOD]
                    // QC#14858-Sol#060
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A2)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(CM_STK_IN_PRC_FLG, ZYPConstant.FLG_ON_Y);
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(CM_STK_IN_PRC_FLG, ZYPConstant.FLG_OFF_N);
                    }
                    cmInvAcctDistNonPrimaryKeyMap.put(CSI_ENT_DEAL_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(CSI_ENT_PO_DTL_DEAL_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A2.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(CSI_ENT_FUNC_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entFuncNetUnitPrcAmt_A2.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(CSI_ENT_PO_DTL_FUNC_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlFuncNetAmt_A2.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(EXCH_RATE, exchRate);

                    // START 2018/04/04 [QC#20316, DEL]
                    // if (bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)) {
                    //     // cmInvAcctDistNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(LINE_NUM_FORMAT, apVndInvLineNumItem));
                    //     // apVndInvLineNumItem++;
                    //     cmInvAcctDistNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
                    // } else {
                    //     // START 2018/03/22 [QC#20316, MOD]
                    //     // cmInvAcctDistNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(LINE_NUM_FORMAT, apVndInvLineNumOther));
                    //     cmInvAcctDistNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(AP_VND_INV_LINE_NUM_FORMAT, apVndInvLineNumOther));
                    //     // END   2018/03/22 [QC#20316, MOD]
                    //     apVndInvLineNumOther++;
                    // }
                    // // START 2018/03/22 [QC#20316, MOD]
                    // cmInvAcctDistNonPrimaryKeyMap.put(CM_INV_ACCT_DIST_LINE_NUM, String.format(LINE_NUM_FORMAT, cmInvAcctDistLineNum));
                    // // END   2018/03/22 [QC#20316, MOD]
                    // END   2018/04/04 [QC#20316, DEL]

                    commitLineNumForInv = commitLineNumForInv + 1;
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_LINE_TP_CD, bizMsg.A.no(i).apLineTpCd_A1.getValue());
                    cmInvAcctDistNonPrimaryKeyMap.put(MDSE_DESC_SHORT_TXT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).mdseDescShortTxt_A1.getValue()));
                    cmInvAcctDistNonPrimaryKeyMap.put(DEAL_GRS_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()));

                    // START 2018/04/04 [QC#20316, ADD]
                    if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
                        cmInvAcctDistNonPrimaryKeyMap.put(MDSE_CD, bizMsg.A.no(i).xxMdseCd_A1.getValue());
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(MDSE_CD, bizMsg.A.no(i).mdseCd_A1.getValue());
                    }
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_INV_TP_CD, AP_INV_TP_CD_00);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(i).poNum_A1.getValue());
                        // START 2018/11/08 J.Kim [QC#23037,ADD]
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_ORD_DTL_LINE_NUM, bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                        // END 2018/11/08 J.Kim [QC#23037,ADD]
                    } else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT.getValue())) {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, bizMsg.poNum_HT.getValue());
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, NONE);
                    }
                    // START 2020/03/23 [QC#53413, ADD]
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).delyOrdNum_A2)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(DELY_ORD_NUM, bizMsg.A.no(i).delyOrdNum_A2.getValue());
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(DELY_ORD_NUM, bizMsg.delyOrdNum_DH.getValue());
                    }
                    
                    // END   2020/03/23 [QC#53413, ADD]
                    // END   2018/04/04 [QC#20316, ADD]
                    // START 2018/08/14 [QC#27029-1, ADD]
                    cmInvAcctDistNonPrimaryKeyMap.put(PD_ENT_DEAL_NET_UNIT_PRC_AMT, bizMsg.A.no(i).entDealNetUnitPrcAmt_A3.getValue());
                    // END   2018/08/14 [QC#27029-1, ADD]

                    // QC#23505
                    if (XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                        cmInvAcctDistNonPrimaryKeyMap.put(INV_CRCT_DT, ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode()));
                    } else {
                        cmInvAcctDistNonPrimaryKeyMap.put(INV_CRCT_DT, null);
                    }
                } else {
                    // Already exists primary key in the map
                    BigDecimal poQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(PO_QTY);
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_QTY, poQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue())));
                    cmInvAcctDistNonPrimaryKeyMap.put(PO_QTY, poQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue())));
                    BigDecimal invRcvQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(INV_RCV_QTY);
                    cmInvAcctDistNonPrimaryKeyMap.put(INV_RCV_QTY, invRcvQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).invRcvQty_A1.getValue())));
                    BigDecimal apBillQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_BILL_QTY);
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_BILL_QTY, apBillQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                    BigDecimal apRejQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_REJ_QTY);
                    cmInvAcctDistNonPrimaryKeyMap.put(AP_REJ_QTY, apRejQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apRejQty_A1.getValue())));
                    BigDecimal thisMthFobCostAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(THIS_MTH_FOB_COST_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(THIS_MTH_FOB_COST_AMT, thisMthFobCostAmt.add(BigDecimal.ZERO));
                    BigDecimal acInvTaxAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AC_INV_TAX_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_TAX_AMT, acInvTaxAmt.add(BigDecimal.ZERO));
                    BigDecimal acInvJrnlCostAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AC_INV_JRNL_COST_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(AC_INV_JRNL_COST_AMT, acInvJrnlCostAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    BigDecimal entPoDtlDealNetAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(ENT_PO_DTL_DEAL_NET_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_DEAL_NET_AMT, entPoDtlDealNetAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue())));
                    BigDecimal entFuncNetUnitPrcAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(ENT_FUNC_NET_UNIT_PRC_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, entFuncNetUnitPrcAmt.add(BigDecimal.ZERO));
                    BigDecimal entPoDtlFuncNetAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(ENT_PO_DTL_FUNC_NET_AMT);
                    cmInvAcctDistNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, entPoDtlFuncNetAmt.add(BigDecimal.ZERO));
                }
            }
            cmInvAcctDistMap.put(pkList, cmInvAcctDistNonPrimaryKeyMap);

            // CM_AP_INV_DTL table primary key values.
            pkList = new ArrayList<String>();
            // START 2018/04/04 [QC#20316, MOD]
            // pkList.add(GLBL_CMPY_CD);
            // pkList.add(bizMsg.apVndCd.getValue());
            // pkList.add(bizMsg.apVndInvNum.getValue());
            // pkList.add(bizMsg.A.no(i).apVndInvSqNum_A1.getValue());
            // pkList.add(bizMsg.apVndCd.getValue());
            // pkList.add(bizMsg.apVndInvNum.getValue());
            // // QC#14858-Sol#060 text item.
            // if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
            //     pkList.add(bizMsg.A.no(i).xxMdseCd_A1.getValue());
            // } else {
            //     pkList.add(bizMsg.A.no(i).mdseCd_A1.getValue());
            // }
            // pkList.add(bizMsg.delyOrdNum_DH.getValue());
            // pkList.add(AP_INV_TP_CD_00);
//            pkList.add(bizMsg.poNum_HT.getValue());
            // pkList.add(bizMsg.A.no(i).poNum_A1.getValue());
            // pkList.add(bizMsg.A.no(i).apLineTpCd_A1.getValue());
            pkList.add(GLBL_CMPY_CD);
            pkList.add(bizMsg.apVndCd.getValue());
            pkList.add(bizMsg.apVndInvNum.getValue());
            pkList.add(bizMsg.A.no(i).apVndInvSqNum_A1.getValue());

            // START 2019/02/21 [QC#30420, MOD]
            // // QC#24985
            // if (bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM) || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)) {
            //     pkList.add(bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
            // } else {
            //     pkList.add(String.format(AP_VND_INV_LINE_NUM_FORMAT, apVndInvLineNumOther - 1));
            // }
            pkList.add(bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
            // END   2019/02/21 [QC#30420, MOD]
            // END   2018/04/04 [QC#20316, MOD]

            // CM_AP_INV_DTL table non primary key values.
            Map<String, Object> cmApInvDtlNonPrimaryKeyMap = cmApInvDtlMap.get(pkList);
            // START 2018/03/16 [QC#24089, MOD]
            // if (bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
            //         || (!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
            //                 && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)
            //                 // && bizMsg.A.no(i).apBillQty_A1.getValue().compareTo(BigDecimal.ZERO) > 0
            //                 // && bizMsg.A.no(i).xxInvAmt_A1.getValue().compareTo(BigDecimal.ZERO) >= 0)
            //                 && bizMsg.A.no(i).apBillQty_A1.getValue().compareTo(BigDecimal.ZERO) != 0
            //                 && bizMsg.A.no(i).xxInvAmt_A1.getValue().compareTo(BigDecimal.ZERO) != 0)
            //                 || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.TAX)
            //                 || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)) {
            if (bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
                    || (!bizMsg.cmApInvTpCd_S.getValue().equals(CM_AP_INV_TP.CREDIT_MEMO)
                            && bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM))
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.TAX)
                            // START QC#25902,QC#25190,QC#25141
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.FREIGHT)
                            || bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.MISC)) {
                            // END QC#25902,QC#25190,QC#25141
            // END   2018/03/16 [QC#24089, MOD]
                if (cmApInvDtlNonPrimaryKeyMap == null) {
                    // Not exists primary key in the map
                    cmApInvDtlPkList.add(pkList);
                    cmApInvDtlNonPrimaryKeyMap = new HashMap<String, Object>();
                    cmApInvDtlNonPrimaryKeyMap.put(IMPT_INV_CNSGN_CD, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(CM_LIC_NUM, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_TP_CD, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_FOB_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_FRT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_INS_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_DOM_EXP_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_OTH_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TAX_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TOT_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TOT_INV_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_FOB_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_FRT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_INS_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_DOM_EXP_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_OTH_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TAX_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TOT_COST_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TOT_INV_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TRNST_JRNL_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_STK_IN_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_ASG_DT, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                    //QC#23038 MOD START
                    //cmApInvDtlNonPrimaryKeyMap.put(CM_AP_INV_ASG_CD, ZYPConstant.FLG_ON_Y);
                    cmApInvDtlNonPrimaryKeyMap.put(CM_AP_INV_ASG_CD, ZYPConstant.FLG_OFF_N);
                    //QC#23038 MOD END
                    cmApInvDtlNonPrimaryKeyMap.put(CSTM_ARV_DT, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(APPLY_EXCH_RATE, exchRate);
                    cmApInvDtlNonPrimaryKeyMap.put(STK_IN_DT, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(IMPT_REF_NUM, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(TRNST_JRNL_CPLT_FLG, ZYPConstant.FLG_OFF_N);
                    cmApInvDtlNonPrimaryKeyMap.put(STK_IN_JRNL_CPLT_FLG, ZYPConstant.FLG_OFF_N);
                    cmApInvDtlNonPrimaryKeyMap.put(AP_INV_AUTH_FLG, ZYPConstant.FLG_OFF_N);
                    cmApInvDtlNonPrimaryKeyMap.put(AP_JRNL_CPLT_FLG, ZYPConstant.FLG_OFF_N);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_RCV_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).invRcvQty_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(VND_REF_NUM, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(VND_FOC_TP_CD, EMPTY_STRING);
                    cmApInvDtlNonPrimaryKeyMap.put(ORIG_SC_FOB_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ORIG_SC_FRT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ORIG_SC_INS_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ORIG_SC_OTH_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(OC_FRT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(OC_INS_OTH_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(OC_UNIT_EXT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(OC_EXT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(SC_UNIT_EXT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(SC_EXT_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(PO_QTY, NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(THIS_MTH_FOB_COST_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ENT_DEAL_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(ENT_PO_DTL_DEAL_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue()));
                    // START 2018/11/05 [QC#28982, MOD]
                    // cmApInvDtlNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, BigDecimal.ZERO);
                    // cmApInvDtlNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, BigDecimal.ZERO);
                    cmApInvDtlNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, BigDecimal.ZERO);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1)) {
                        BigDecimal entFuncNetUnitPrcAmt = NFBCommonBusiness.calcStdAmt(bizMsg.A.no(i).dealGrsUnitPrcAmt_A1.getValue(),
                                exchRate, ccyMsg.acctArthTpCd.getValue(), ccyMsg.aftDeclPntDigitNum.getValue());
                        cmApInvDtlNonPrimaryKeyMap.put(ENT_FUNC_NET_UNIT_PRC_AMT, entFuncNetUnitPrcAmt);
                    }
                    cmApInvDtlNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, BigDecimal.ZERO);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A1)) {
                        BigDecimal entPoDtlFuncNetAmt = NFBCommonBusiness.calcStdAmt(bizMsg.A.no(i).entPoDtlDealNetAmt_A1.getValue(),
                                exchRate, ccyMsg.acctArthTpCd.getValue(), ccyMsg.aftDeclPntDigitNum.getValue());
                        cmApInvDtlNonPrimaryKeyMap.put(ENT_PO_DTL_FUNC_NET_AMT, entPoDtlFuncNetAmt);
                    }
                    // END   2018/11/05 [QC#28982, MOD]
                    // QC#14858-Sol#060
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).entPoDtlDealNetAmt_A2)) {
                        cmApInvDtlNonPrimaryKeyMap.put(CM_STK_IN_PRC_FLG, ZYPConstant.FLG_ON_Y);
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(CM_STK_IN_PRC_FLG, ZYPConstant.FLG_OFF_N);
                    }
                    cmApInvDtlNonPrimaryKeyMap.put(CSI_ENT_DEAL_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entDealNetUnitPrcAmt_A2.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(CSI_ENT_PO_DTL_DEAL_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlDealNetAmt_A2.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(CSI_ENT_FUNC_NET_UNIT_PRC_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entFuncNetUnitPrcAmt_A2.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(CSI_ENT_PO_DTL_FUNC_NET_AMT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).entPoDtlFuncNetAmt_A2.getValue()));
                    cmApInvDtlNonPrimaryKeyMap.put(EXCH_RATE, exchRate);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                        cmInvAcctDistNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(i).poNum_A1.getValue());
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, bizMsg.poNum_HT.getValue());
                    }
                    // QC#19261
                    // cmApInvDtlNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(LINE_NUM_FORMAT, apVndInvLineNum));
                    // apVndInvLineNum++;
                    // START 2018/04/04 [QC#20316, DEL]
                    // if (bizMsg.A.no(i).apLineTpCd_A1.getValue().equals(AP_LINE_TP.ITEM)) {
                    //     cmApInvDtlNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, bizMsg.A.no(i).xxDtlLineNum_A1.getValue());
                    // } else {
                    //     // START 2018/03/22 [QC#20316, MOD]
                    //     // cmApInvDtlNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(LINE_NUM_FORMAT, apVndInvLineNumOther - 1));
                    //     cmApInvDtlNonPrimaryKeyMap.put(AP_VND_INV_LINE_NUM, String.format(AP_VND_INV_LINE_NUM_FORMAT, apVndInvLineNumOther - 1));
                    //     // END   2018/03/22 [QC#20316, MOD]
                    // }
                    // END   2018/04/04 [QC#20316, DEL]

                    cmApInvDtlNonPrimaryKeyMap.put(PO_ORD_DTL_LINE_NUM, bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                    commitLineNumForApInv = commitLineNumForApInv + 1;
                    cmApInvDtlNonPrimaryKeyMap.put(MDSE_DESC_SHORT_TXT, NFBCommonBusiness.chkNull(bizMsg.A.no(i).mdseDescShortTxt_A1.getValue()));
                    // QC#23505
                    cmApInvDtlNonPrimaryKeyMap.put(XX_LINE_TP_CD, bizMsg.A.no(i).xxLineTpCd_A1.getValue());

                    // START 2018/04/04 [QC#20316, ADD]
                    cmApInvDtlNonPrimaryKeyMap.put(AP_VND_CD, bizMsg.apVndCd.getValue());
                    cmApInvDtlNonPrimaryKeyMap.put(AP_VND_INV_NUM, bizMsg.apVndInvNum.getValue());
                    if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
                        cmApInvDtlNonPrimaryKeyMap.put(MDSE_CD, bizMsg.A.no(i).xxMdseCd_A1.getValue());
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(MDSE_CD, bizMsg.A.no(i).mdseCd_A1.getValue());
                    }
                    cmApInvDtlNonPrimaryKeyMap.put(AP_INV_TP_CD, AP_INV_TP_CD_00);
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, bizMsg.A.no(i).poNum_A1.getValue());
                    } else if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT.getValue())) {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, bizMsg.poNum_HT.getValue());
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(PO_NUM, NONE);
                    }
                    // START 2020/03/23 [QC#53413, ADD]
                    if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).delyOrdNum_A2)) {
                        cmApInvDtlNonPrimaryKeyMap.put(DELY_ORD_NUM, bizMsg.A.no(i).delyOrdNum_A2.getValue());
                    } else {
                        cmApInvDtlNonPrimaryKeyMap.put(DELY_ORD_NUM, bizMsg.delyOrdNum_DH.getValue());
                    }
                    // END   2020/03/23 [QC#53413, ADD]
                    // END   2018/04/04 [QC#20316, ADD]

                    // START 2019/02/21 [QC#30420, ADD]
                    cmApInvDtlNonPrimaryKeyMap.put(AP_LINE_TP_CD, bizMsg.A.no(i).apLineTpCd_A1.getValue());
                    // END   2019/02/21 [QC#30420, ADD]

                } else {
                    // Already exists primary key in the map
                    BigDecimal poQty = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(PO_QTY);
                    BigDecimal apBillQty = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_QTY);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_QTY, apBillQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).apBillQty_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(PO_QTY, poQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).poQty_A1.getValue())));
                    BigDecimal invRcvQty = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_RCV_QTY);
                    cmApInvDtlNonPrimaryKeyMap.put(INV_RCV_QTY, invRcvQty.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).invRcvQty_A1.getValue())));
                    BigDecimal lineAmt = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_FOB_COST_AMT);
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_FOB_COST_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TOT_COST_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_OC_TOT_INV_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_FOB_COST_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TOT_COST_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                    cmApInvDtlNonPrimaryKeyMap.put(AC_SC_TOT_INV_AMT, lineAmt.add(NFBCommonBusiness.chkNull(bizMsg.A.no(i).xxInvAmt_A1.getValue())));
                }
            }
            cmApInvDtlMap.put(pkList, cmApInvDtlNonPrimaryKeyMap);
        }

        // START 2019/02/22 [QC#30420, ADD]
        removeNotInvoicedLineForDist(cmInvAcctDistPkList, cmInvAcctDistMap);
        renumberInvLineNumber(cmInvAcctDistPkList, cmInvAcctDistMap);
        // END   2019/02/22 [QC#30420, ADD]

        EZDItemAttribute getDigitApInvDescTxt = new CM_INV_ACCT_DISTTMsg().getAttr("apInvDescTxt");
        int digitApInvDescTxt = getDigitApInvDescTxt.getDigit();
        for (int i = 0; i < cmInvAcctDistPkList.size(); i++) {
            List<String> pkList = cmInvAcctDistPkList.get(i);

            // START 2019/01/29 [QC#30056, ADD]
            Map<String, Object> cmInvAcctDistNonPrimaryKeyMap = (Map<String, Object>) cmInvAcctDistMap.get(pkList);
            // START 2019/02/22 [QC#30420, DEL]
            // BigDecimal lineAmt = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(LINE_AMT);
            // BigDecimal invQty = (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_BILL_QTY);
            // if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
            //     continue;
            // }
            // END   2019/02/22 [QC#30420, DEL]
            // END   2019/01/29 [QC#30056, ADD]

            CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.glblCmpyCd, pkList.get(0));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, pkList.get(1));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvNum, pkList.get(2));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvSqNum, pkList.get(3));
            // START 2018/04/04 [QC#20316, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvLineNum, pkList.get(4));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum, pkList.get(5));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseCd, pkList.get(4));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvTpCd, pkList.get(5));
            // if (ZYPCommonFunc.hasValue(pkList.get(6))) {
            //     ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, pkList.get(6));
            // } else {
            //     ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, NONE);
            // }
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.delyOrdNum, pkList.get(7));
            // END   2018/04/04 [QC#20316, MOD]

            // START 2019/01/29 [QC#30056, DEL]
//            Map<String, Object> cmInvAcctDistNonPrimaryKeyMap = (Map<String, Object>) cmInvAcctDistMap.get(pkList);
            // END   2019/01/29 [QC#30056, DEL]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invTpCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(INV_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(PO_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invRcvQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(INV_RCV_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apBillQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_BILL_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apRejQty, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AP_REJ_QTY));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.uomCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(UOM_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.thisMthFobCostAmt, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(THIS_MTH_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvTaxAmt, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AC_INV_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.acInvJrnlCostAmt, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(AC_INV_JRNL_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invAsgDt, (String)cmInvAcctDistNonPrimaryKeyMap.get(INV_ASG_DT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.stkInDt, (String)cmInvAcctDistNonPrimaryKeyMap.get(STK_IN_DT));
            String apInvDescTxtFullLength = (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_DESC_TXT);
            if (ZYPCommonFunc.hasValue(apInvDescTxtFullLength) && apInvDescTxtFullLength.length() > digitApInvDescTxt) {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, S21StringUtil.subStringByLength(apInvDescTxtFullLength, 0, digitApInvDescTxt));
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvDescTxt, (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_DESC_TXT));
            }
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.csmpInvNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(CSMP_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.serNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(SER_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.contrNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(CONTR_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.custDlrCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CUST_DLR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocFirstLineAddr, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocFlNm, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_FL_NM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocCtyAddr, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocStCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_ST_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.istlLocPostCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(ISTL_LOC_POST_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invLineDescTxt, (String)cmInvAcctDistNonPrimaryKeyMap.get(INV_LINE_DESC_TXT));
            // START 2018/03/22 [QC#20316, MOD]
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaCmpyCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_CMPY_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaBrCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_BR_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaCcCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_CC_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaAcctCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_ACCT_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaProdCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_PROD_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaChCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_CH_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaAfflCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_AFFL_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaProjCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_PROJ_CD));
//            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.coaExtnCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCmpyCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaBrCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaCcCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAcctCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProdCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaChCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaAfflCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaProjCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.drCoaExtnCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(DR_COA_EXTN_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaCmpyCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_CMPY_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaBrCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_BR_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaCcCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_CC_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaAcctCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_ACCT_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaProdCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaChCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_CH_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaAfflCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_AFFL_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaProjCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_PROJ_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.crCoaExtnCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(CR_COA_EXTN_CD));
            // END   2018/03/22 [QC#20316, MOD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apAcctDescTxt, (String)cmInvAcctDistNonPrimaryKeyMap.get(AP_ACCT_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.ifProcStsCd, (String)cmInvAcctDistNonPrimaryKeyMap.get(IF_PROC_STS_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvAuthFlg, (String)cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_AUTH_FLG));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apJrnlCpltFlg, (String)cmInvAcctDistNonPrimaryKeyMap.get(AP_JRNL_CPLT_FLG));
            // QC#14858-Sol#060
            if (ZYPConstant.FLG_ON_Y.equals((String) cmInvAcctDistNonPrimaryKeyMap.get(CM_STK_IN_PRC_FLG))) {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entDealNetUnitPrcAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlDealNetAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_PO_DTL_DEAL_NET_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entFuncNetUnitPrcAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_FUNC_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlFuncNetAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(CSI_ENT_PO_DTL_FUNC_NET_AMT));
            } else {
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entDealNetUnitPrcAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlDealNetAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(ENT_PO_DTL_DEAL_NET_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entFuncNetUnitPrcAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(ENT_FUNC_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.entPoDtlFuncNetAmt, (BigDecimal) cmInvAcctDistNonPrimaryKeyMap.get(ENT_PO_DTL_FUNC_NET_AMT));
            }
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.exchRate, (BigDecimal)cmInvAcctDistNonPrimaryKeyMap.get(EXCH_RATE));
            // START 2018/04/04 [QC#20316, DEL]
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvLineNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(AP_VND_INV_LINE_NUM));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.cmInvAcctDistLineNum, (String)cmInvAcctDistNonPrimaryKeyMap.get(CM_INV_ACCT_DIST_LINE_NUM));
            // ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apLineTpCd, pkList.get(8));
            // END   2018/04/04 [QC#20316, DEL]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseDescShortTxt, (String) cmInvAcctDistNonPrimaryKeyMap.get(MDSE_DESC_SHORT_TXT));
            // QC#23505
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.invCrctDt, (String) cmInvAcctDistNonPrimaryKeyMap.get(INV_CRCT_DT));

            // START 2018/04/04 [QC#20316, ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.mdseCd, (String) cmInvAcctDistNonPrimaryKeyMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apInvTpCd, (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(PO_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.delyOrdNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(DELY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apLineTpCd, (String) cmInvAcctDistNonPrimaryKeyMap.get(AP_LINE_TP_CD));
            // END   2018/04/04 [QC#20316, ADD]
            // START 2018/11/08 J.Kim [QC#23037,ADD]
            ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.poOrdDtlLineNum, (String) cmInvAcctDistNonPrimaryKeyMap.get(PO_ORD_DTL_LINE_NUM));
            // END 2018/11/08 J.Kim [QC#23037,ADD]

            EZDTBLAccessor.create(cmInvAcctDistTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmInvAcctDistTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0073E, new String[] {cmInvAcctDistTMsg.getTableName(), cmInvAcctDistTMsg.getReturnCode() });
                return;
            }
        }

        // START 2018/08/14 [QC#27029-1, MOD]
        // if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
        //     NFBL2040CommonLogic.insertVarianceData(bizMsg, cmInvAcctDistMap, cmInvAcctDistPkList, commitLineNumForInv, exchRate, true);
        // }
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue()) || CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            // START 2019/02/07 [QC#30136, MOD]
            // NFBL2040CommonLogic.insertVarianceData(bizMsg, cmInvAcctDistMap, cmInvAcctDistPkList, commitLineNumForInv, exchRate, true);
            if (!isBlanketPO) {
                NFBL2040CommonLogic.insertVarianceData(bizMsg, cmInvAcctDistMap, cmInvAcctDistPkList, commitLineNumForInv, exchRate, true);
            }
            // END   2019/02/07 [QC#30136, MOD]
        }
        // END   2018/08/14 [QC#27029-1, MOD]

        // START 2019/02/22 [QC#30420, ADD]
        removeNotInvoicedLineForDtl(cmApInvDtlPkList, cmApInvDtlMap);
        renumberInvLineNumber(cmApInvDtlPkList, cmApInvDtlMap);
        // END   2019/02/22 [QC#30420, ADD]

        for (int i = 0; i < cmApInvDtlPkList.size(); i++) {
            List<String> pkList = cmApInvDtlPkList.get(i);

            // START 2019/01/29 [QC#30056, ADD]
            Map<String, Object> cmApInvDtlNonPrimaryKeyMap = (Map<String, Object>) cmApInvDtlMap.get(pkList);
            // START 2019/02/22 [QC#30420, DEL]
            // BigDecimal lineAmt = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_FOB_COST_AMT);
            // BigDecimal invQty = (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_QTY);
            // if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
            //     continue;
            // }
            // END   2019/02/22 [QC#30420, DEL]
            // END   2019/01/29 [QC#30056, ADD]

            CM_AP_INV_DTLTMsg cmApInvDtlTMsg = new CM_AP_INV_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.glblCmpyCd, pkList.get(0));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndCd, pkList.get(1));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvNum, pkList.get(2));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvSqNum, pkList.get(3));
            // START 2018/04/04 [QC#20316, MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvLineNum, pkList.get(4));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndCd, pkList.get(4));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndInvNum, pkList.get(5));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.mdseCd, pkList.get(6));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.delyOrdNum, pkList.get(7));
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apInvTpCd, pkList.get(8));
            // if (ZYPCommonFunc.hasValue(pkList.get(9))) {
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poNum, pkList.get(9));
            // } else {
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poNum, NONE);
            // }
            // END   2018/04/04 [QC#20316, MOD]

            // START 2019/01/29 [QC#30056, DEL]
//            Map<String, Object> cmApInvDtlNonPrimaryKeyMap = (Map<String, Object>) cmApInvDtlMap.get(pkList);
            // END   2019/01/29 [QC#30056, DEL]
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.imptInvCnsgnCd, (String)cmApInvDtlNonPrimaryKeyMap.get(IMPT_INV_CNSGN_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.cmLicNum, (String)cmApInvDtlNonPrimaryKeyMap.get(CM_LIC_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.invTpCd, (String)cmApInvDtlNonPrimaryKeyMap.get(INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.invQty, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_QTY));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcFobCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcFrtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcInsCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_INS_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcDomExpCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_DOM_EXP_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcOthCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcTaxAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcTotCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_TOT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acOcTotInvAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_OC_TOT_INV_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScFobCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScFrtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScInsCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_INS_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScDomExpCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_DOM_EXP_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScOthCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScTaxAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_TAX_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScTotCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_TOT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScTotInvAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_TOT_INV_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScTrnstJrnlAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_TRNST_JRNL_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.acScStkInCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(AC_SC_STK_IN_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.invAsgDt, (String)cmApInvDtlNonPrimaryKeyMap.get(INV_ASG_DT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.cmApInvAsgCd, (String)cmApInvDtlNonPrimaryKeyMap.get(CM_AP_INV_ASG_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.cstmArvDt, (String)cmApInvDtlNonPrimaryKeyMap.get(CSTM_ARV_DT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.applyExchRate, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(APPLY_EXCH_RATE));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.stkInDt, (String)cmApInvDtlNonPrimaryKeyMap.get(STK_IN_DT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.imptRefNum, (String)cmApInvDtlNonPrimaryKeyMap.get(IMPT_REF_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.trnstJrnlCpltFlg, (String)cmApInvDtlNonPrimaryKeyMap.get(TRNST_JRNL_CPLT_FLG));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.stkInJrnlCpltFlg, (String)cmApInvDtlNonPrimaryKeyMap.get(STK_IN_JRNL_CPLT_FLG));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apInvAuthFlg, (String)cmApInvDtlNonPrimaryKeyMap.get(AP_INV_AUTH_FLG));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apJrnlCpltFlg, (String)cmApInvDtlNonPrimaryKeyMap.get(AP_JRNL_CPLT_FLG));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.invRcvQty, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(INV_RCV_QTY));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndRefNum, (String)cmApInvDtlNonPrimaryKeyMap.get(VND_REF_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndFocTpCd, (String)cmApInvDtlNonPrimaryKeyMap.get(VND_FOC_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.origScFobCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ORIG_SC_FOB_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.origScFrtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ORIG_SC_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.origScInsCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ORIG_SC_INS_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.origScOthCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ORIG_SC_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.ocFrtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(OC_FRT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.ocInsOthCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(OC_INS_OTH_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.ocUnitExtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(OC_UNIT_EXT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.ocExtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(OC_EXT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.scUnitExtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(SC_UNIT_EXT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.scExtCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(SC_EXT_COST_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poQty, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(PO_QTY));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.thisMthFobCostAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(THIS_MTH_FOB_COST_AMT));
            // START 2018/06/19 [QC#26169, MOD]
            // // QC#14858-Sol#060
            // if(ZYPConstant.FLG_ON_Y.equals((String) cmApInvDtlNonPrimaryKeyMap.get(CM_STK_IN_PRC_FLG))){
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entDealNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(CSI_ENT_DEAL_NET_UNIT_PRC_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlDealNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(CSI_ENT_PO_DTL_DEAL_NET_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entFuncNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(CSI_ENT_FUNC_NET_UNIT_PRC_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlFuncNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(CSI_ENT_PO_DTL_FUNC_NET_AMT));
            // }else {
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entDealNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_DEAL_NET_UNIT_PRC_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlDealNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_PO_DTL_DEAL_NET_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entFuncNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_FUNC_NET_UNIT_PRC_AMT));
            //     ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlFuncNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_PO_DTL_FUNC_NET_AMT));
            // }
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entDealNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlDealNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_PO_DTL_DEAL_NET_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entFuncNetUnitPrcAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_FUNC_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.entPoDtlFuncNetAmt, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(ENT_PO_DTL_FUNC_NET_AMT));
            // END   2018/06/19 [QC#26169, MOD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.exchRate, (BigDecimal)cmApInvDtlNonPrimaryKeyMap.get(EXCH_RATE));
            // START 2018/04/04 [QC#20316, DEL]
            // ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvLineNum, (String)cmApInvDtlNonPrimaryKeyMap.get(AP_VND_INV_LINE_NUM));
            // END   2018/04/04 [QC#20316, DEL]
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poOrdDtlLineNum, (String)cmApInvDtlNonPrimaryKeyMap.get(PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.mdseDescShortTxt, (String) cmApInvDtlNonPrimaryKeyMap.get(MDSE_DESC_SHORT_TXT));

            // START 2018/04/04 [QC#20316, ADD]
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndCd, (String) cmApInvDtlNonPrimaryKeyMap.get(AP_VND_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.vndInvNum, (String) cmApInvDtlNonPrimaryKeyMap.get(AP_VND_INV_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.mdseCd, (String) cmApInvDtlNonPrimaryKeyMap.get(MDSE_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.delyOrdNum, (String) cmApInvDtlNonPrimaryKeyMap.get(DELY_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apInvTpCd, (String) cmApInvDtlNonPrimaryKeyMap.get(AP_INV_TP_CD));
            ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.poNum, (String) cmApInvDtlNonPrimaryKeyMap.get(PO_NUM));
            // END   2018/04/04 [QC#20316, ADD]

            EZDTBLAccessor.create(cmApInvDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cmApInvDtlTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0073E, new String[] {cmApInvDtlTMsg.getTableName(), cmApInvDtlTMsg.getReturnCode() });
                return;
            }

            String xxLineTpCd = (String) cmApInvDtlNonPrimaryKeyMap.get(XX_LINE_TP_CD);
            if (!existsHold && XX_LINE_TP_CD_VALID.equals(xxLineTpCd)) {
                callPoStsUpdateAPI(bizMsg, cmApInvDtlTMsg);
            }
        }
    }

    private void clearStatusCmStkIn(NFBL2040CMsg bizMsg) {
        List<List> listKey = new ArrayList<List>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (XX_LINE_TP_CD_CREDIT.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                continue;
            }

            if (XX_LINE_TP_CD_INVALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())
                    && !bizMsg.apVndInvSqNum_BK.getValue().equals(bizMsg.A.no(i).apVndInvSqNum_A1.getValue())) {
                continue;
            }

            List<String> pkList = new ArrayList<String>();
            // START 2020/03/23 [QC#53413, MOD]
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                pkList.add(bizMsg.A.no(i).poNum_A1.getValue());
            } else {
                pkList.add(bizMsg.poNum_HD.getValue());
            }
            // END   2020/03/23 [QC#53413, MOD]
            pkList.add(bizMsg.A.no(i).mdseCd_A1.getValue());
            if (!listKey.contains(pkList)) {
                List<Map> resultList = (List<Map>) NFBL2040CommonLogic.getCmStkInPkListByPartialValue(
                        bizMsg.A.no(i).poNum_A1.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue());
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
    }

    private void assignCmStkInToInvoice(NFBL2040CMsg bizMsg) {

        // Assign CM_STK_IN records to invoice.
        List<List> listKey = new ArrayList<List>();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!XX_LINE_TP_CD_VALID.equals(bizMsg.A.no(i).xxLineTpCd_A1.getValue())) {
                continue;
            }

            List<String> pkList = new ArrayList<String>();
            // START 2020/03/23 [QC#53413, MOD]
            String poNum = null;
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                poNum = bizMsg.A.no(i).poNum_A1.getValue();
            } else {
                poNum = bizMsg.poNum.getValue();
            }
            pkList.add(poNum);
            // END   2020/03/23 [QC#53413, MOD]

            // QC#14858-Sol#060 text item.
            if(ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxCntnrFlg_A1.getValue())){
                pkList.add(bizMsg.A.no(i).xxMdseCd_A1.getValue());
            } else {
                pkList.add(bizMsg.A.no(i).mdseCd_A1.getValue());
            }

            if (!listKey.contains(pkList)) {
                // START 2020/03/23 [QC#53413, MOD]
                //List<Map> resultList = (List<Map>) NFBL2040CommonLogic.getCmStkInPkListByPartialValue(bizMsg.poNum_HT.getValue(), bizMsg.A.no(i).mdseCd_A1.getValue());
                List<Map> resultList = (List<Map>) NFBL2040CommonLogic.getCmStkInPkListByPartialValue(poNum, bizMsg.A.no(i).mdseCd_A1.getValue());
                // START 2020/03/23 [QC#53413, ADD]
                if (resultList != null) {
                    for (int j = 0; j < resultList.size(); j++) {
                        Map<String, Object> resultMap = (Map<String, Object>) resultList.get(j);
                        CM_STK_INTMsg cmStkIn = new CM_STK_INTMsg();
                        ZYPEZDItemValueSetter.setValue(cmStkIn.glblCmpyCd, GLBL_CMPY_CD);
                        ZYPEZDItemValueSetter.setValue(cmStkIn.cmStkInPk, (BigDecimal) resultMap.get(CM_STK_IN_PK));
                        cmStkIn = (CM_STK_INTMsg) EZDTBLAccessor.findByKeyForUpdate(cmStkIn);
                        if (cmStkIn != null) {
                            ZYPEZDItemValueSetter.setValue(cmStkIn.vndCd, bizMsg.apVndCd.getValue());
                            ZYPEZDItemValueSetter.setValue(cmStkIn.vndInvNum, bizMsg.apVndInvNum.getValue());
                            ZYPEZDItemValueSetter.setValue(cmStkIn.vndInvSqNum, bizMsg.apVndInvSqNum.getValue());
                            ZYPEZDItemValueSetter.setValue(cmStkIn.invAsgDt, ZYPDateUtil.getCurrentSystemTime(YYYYMMDD));
                            ZYPEZDItemValueSetter.setValue(cmStkIn.invAsgFlg, ZYPConstant.FLG_ON_Y);
                            EZDTBLAccessor.update(cmStkIn);
                        }
                    }
                }
                listKey.add(pkList);
            }
        }
    }

    private void refreshHolds(NFBL2040CMsg bizMsg) {

        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            // START 2018/11/20 [QC#28660, MOD]
            // NFBL2040CommonLogic.commonRefreshHoldsTab(bizMsg, USER_ID);
            NFBL2040CommonLogic.commonRefreshHoldsTab(bizMsg, USER_ID, true);
            // END   2018/11/20 [QC#28660, MOD]
        } else {
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

            int validCountH = bizMsg.H.getValidCount();
            if (ZYPCommonFunc.hasValue(bizMsg.docMgtDocId)
                    && ZYPCommonFunc.hasValue(bizMsg.docMgtCatgCd)) {
                // START 2018/03/28 [QC#24277, MOD]
                // if (NFBL2040CommonLogic.addRecordToH(bizMsg, NFBL2040CommonLogic.getPmtHldRelRsnPulldownList(), USER_ID, validCountH, checkList, PMT_HLD_CD_030, PMT_HLD_RSN_CD_031, ZYPConstant.FLG_OFF_N)) {
                //     validCountH++;
                // }
                if (NFBL2040CommonLogic.addRecordToH(bizMsg, NFBL2040CommonLogic.getPmtHldRelRsnPulldownList(), USER_ID, validCountH, checkList, EMPTY_AP_VND_INV_LINE_NUM, PMT_HLD_CD_030, PMT_HLD_RSN_CD_031, ZYPConstant.FLG_OFF_N)) {
                    validCountH++;
                }
                // END   2018/03/28 [QC#24277, MOD]
            }
            bizMsg.H.setValidCount(validCountH);
        }
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.H.no(i).xxChkBox_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_HO, ZYPConstant.FLG_ON_Y);
                break;
            }
        }
    }
    // END   2018/02/26 [QC#23505, ADD]

    // START 2018/03/08 [QC#24275, ADD]
    private boolean checkHoldRelease(NFBL2040CMsg bizMsg, List<Integer> checkedRows) {
        boolean result = true;
        // START 2018/10/10 [QC#28521, DEL]
//        boolean cmpInvQty = false;
//        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
//            if (PMT_HLD_RSN_CD_012.equals(bizMsg.H.no(i).pmtHldRsnCd_H1.getValue()) && checkedRows.contains(i)) {
//                cmpInvQty = true;
//                break;
//            }
//        }
        // END 2018/10/10 [QC#28521, DEL]

        // START 2019/03/19 [QC#30829, ADD]
        // mod start 2022/02/15 QC#57090
        //if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.vndRtrnNum)) {
        //    return checkHoldReleaseForVendorReturn(bizMsg, checkedRows);
        //}
        if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            result = checkHoldReleaseForVendorReturn(bizMsg, checkedRows);
        }
        if (!result) {
            return result;
        }
        // mod end 2022/02/15 QC#57090
        // END   2019/03/19 [QC#30829, ADD]

        for (int i = 0 ; i < bizMsg.A.getValidCount(); i++) {
            // START 2020/02/07 [QC#55530, ADD]
            if (!AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                continue;
            }
            // END 2019/03/19 [QC#55530, ADD]
            NFBL2040_ACMsg aCMsg = bizMsg.A.no(i);
            // add start 2022/02/15 QC#57090
            String poNum = aCMsg.poNum_A1.getValue();
            if (!ZYPCommonFunc.hasValue(poNum)) {
                continue;
            }
            // add end 2022/02/15 QC#57090
            String mdseCd = aCMsg.mdseCd_A1.getValue();
            if(ZYPConstant.FLG_ON_Y.equals(aCMsg.xxCntnrFlg_A1.getValue())){
                mdseCd = aCMsg.xxMdseCd_A1.getValue();
            }
            // START 2018/03/28 [QC#24277, ADD]
            String apVndInvLineNum = (String) aCMsg.xxDtlLineNum_A1.getValue();
            String poOrdDtlLineNum = (String) aCMsg.poOrdDtlLineNum_A1.getValue();
            // END   2018/03/28 [QC#24277, ADD]

            // START 2018/10/10 [QC#28521, DEL]
//            if (cmpInvQty) {
            // END 2018/10/10 [QC#28521, DEL]
            // Compare Ordered Quantity to Invoice
            String apVndInvNum = bizMsg.apVndInvNum.getValue();
            BigDecimal billedQty = BigDecimal.ZERO;
            BigDecimal poQty = BigDecimal.ZERO;

            // START 2018/03/28 [QC#24277, MOD]
            // S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getSumPoQty(bizMsg.poNum_HT.getValue(), mdseCd);
            // mod start 2022/02/15 QC#57090
            //S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getSumPoQty(bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd);
            S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getSumPoQty(poNum, poOrdDtlLineNum, mdseCd);
            // mod end 2022/02/15 QC#57090
            // END   2018/03/28 [QC#24277, MOD]
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                // START 2018/08/28 [QC#27886, MOD]
                // poQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_PO_DISP_QTY"));
                poQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_PO_QTY"));
                // END   2018/08/28 [QC#27886, MOD]
            }

            // START 2018/11/20 [QC#28660, MOD]
            // ssmResult = NFBL2040Query.getInstance().getSumBilledQtyExcluveCanInv(bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd);
            // mod start 2022/02/15 QC#57090
            //ssmResult = NFBL2040Query.getInstance().getSumBilledQtyExcluveCanInv(bizMsg, bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd);
            ssmResult = NFBL2040Query.getInstance().getSumBilledQtyExcluveCanInv(bizMsg, poNum, poOrdDtlLineNum, mdseCd);
            // mod end 2022/02/15 QC#57090
            // END   2018/11/20 [QC#28660, MOD]
            resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                billedQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
            }

            // START 2018/10/10 [QC#28521, ADD]
            // mod start 2022/02/15 QC#57090
            //ssmResult = NFBL2040Query.getInstance().getBilledQtyInvMdse(bizMsg.poNum_HT.getValue(), poOrdDtlLineNum, mdseCd, apVndInvNum);
            ssmResult = NFBL2040Query.getInstance().getBilledQtyInvMdse(poNum, poOrdDtlLineNum, mdseCd, apVndInvNum);
            // mod end 2022/02/15 QC#57090
            resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (resultList.size() > 0) {
                Map<String, Object> rtn = (Map<String, Object>) resultList.get(0);
                BigDecimal selfQty = NFBCommonBusiness.chkNull((BigDecimal) rtn.get("SUM_AP_BILL_QTY"));
                billedQty = billedQty.add(selfQty);
            }
            // END 2018/10/10 [QC#28521, ADD]

            if (poQty.compareTo(billedQty) < 0) {
                // START 2020/03/16 [QC#55993, MOD]
                // START 2018/03/28 [QC#24277, MOD]
                //int hIdx = findHoldReleaseTargetIndex(bizMsg, checkedRows, apVndInvLineNum);
                //if (hIdx >= 0) {
                //    bizMsg.H.no(hIdx).xxChkBox_H1.setErrorInfo(1, NFBM0282E, new String[] { "Invoiced Qty", "Order Qty" } );
                //}
                //aCMsg.apBillQty_A1.setErrorInfo(1, NFBM0282E, new String[] { "Invoiced Qty", "Order Qty" } );
                // END   2018/03/28 [QC#24277, MOD]
                //result = false;
                if (!ZYPCommonFunc.hasValue(bizMsg.xxWrnSkipFlg_A.getValue()) || ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_A.getValue())) {
                    int hIdx = findHoldReleaseTargetIndex(bizMsg, checkedRows, apVndInvLineNum);
                    if (hIdx >= 0) {
                        bizMsg.H.no(hIdx).xxChkBox_H1.setErrorInfo(2, NFBM0292W, new String[] { "Invoiced Qty", "Order Qty" } );
                        if(bizMsg.H.no(hIdx).apVndInvLineNum_H1.getValue().equals(apVndInvLineNum)) {
                            aCMsg.apBillQty_A1.setErrorInfo(2, NFBM0292W, new String[] { "Invoiced Qty", "Order Qty" } );
                        }
                    }
                    bizMsg.setMessageInfo("NFBM0291W", new String[] { "Invoiced Qty", "Order Qty" });
                    result = false;
                }
                // END  2020/03/16 [QC#55993, MOD]
            }
            // START 2018/10/10 [QC#28521, DEL]
//            }
            // END 2018/10/10 [QC#28521, DEL]
        }
        // START 2020/03/16 [QC#55993, ADD]
        if (!result) {
            if (!ZYPCommonFunc.hasValue(bizMsg.xxWrnSkipFlg_A.getValue()) || ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_OFF_N);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_OFF_N);
        }
        // END   2020/03/16 [QC#55993, ADD]

        return result;
    }
    // END   2018/03/08 [QC#24275, ADD]

    // START 2018/03/28 [QC#24277, ADD]
    private int findHoldReleaseTargetIndex(NFBL2040CMsg bizMsg, List<Integer> checkedRows, String apVndInvLineNum) {
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (PMT_HLD_RSN_CD_012.equals(bizMsg.H.no(i).pmtHldRsnCd_H1.getValue()) && checkedRows.contains(i)
                    && ZYPCommonFunc.hasValue(apVndInvLineNum) && apVndInvLineNum.equals(bizMsg.H.no(i).apVndInvLineNum_H1.getValue())) {
                return i;
            }
        }
        return -1;
    }
    // END   2018/03/28 [QC#24277, ADD]

    // START 2018/08/13 [QC#27025-2, ADD]
    // START 2020/03/23 [QC#53413, MOD]
    //private void updatePoStatusForAuthorizedInvoice(NFBL2040CMsg bizMsg) {
    private void updatePoStatusForAuthorizedInvoice(NFBL2040CMsg bizMsg, List<String> poNumList) {

        //if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
        if (!ZYPCommonFunc.hasValue(bizMsg.poNum_HT) && poNumList.size() == 0) {
            return;
        }

        ArrayList<PO_DTLTMsg> poDtlList = new ArrayList<PO_DTLTMsg>();
        if (ZYPCommonFunc.hasValue(bizMsg.poNum_HT)) {
            poDtlList = NFBL2040CommonLogic.getPoDtlListForUpdateNoWait(bizMsg.poNum_HT.getValue());
            if (poDtlList == null) {
                return;
            }
        } else {
            
            for (int z = 0; z < poNumList.size(); z++) {
                ArrayList<PO_DTLTMsg> dataPoDtlList = NFBL2040CommonLogic.getPoDtlListForUpdateNoWait(poNumList.get(z));
                for (int x = 0; x < dataPoDtlList.size(); x++) {
                    poDtlList.add(dataPoDtlList.get(x));
                }
            }
            if (poDtlList.size() == 0) {
                return;
            }
        }
        // END   2020/03/23 [QC#53413, MOD]


        CM_AP_INV_DTLTMsg inTMsg = new CM_AP_INV_DTLTMsg();
        inTMsg.setSQLID("001");
        if (ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) && isInvoicePKModified(bizMsg)) {
            inTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
            inTMsg.setConditionValue("apVndCd01", bizMsg.apVndCd_OT.getValue());
            inTMsg.setConditionValue("apVndInvNum01", bizMsg.apVndInvNum_OT.getValue());
            inTMsg.setConditionValue("apVndInvSqNum01", bizMsg.apVndInvSqNum_OT.getValue());

        } else {
            inTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
            inTMsg.setConditionValue("apVndCd01", bizMsg.apVndCd.getValue());
            inTMsg.setConditionValue("apVndInvNum01", bizMsg.apVndInvNum.getValue());
            inTMsg.setConditionValue("apVndInvSqNum01", AP_VND_INV_SQ_NUM_00);
        }
        CM_AP_INV_DTLTMsgArray cmApInvDtlTMsgArray = (CM_AP_INV_DTLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);

        if (cmApInvDtlTMsgArray == null) {
            return;
        }

        for (int i = 0 ; i < cmApInvDtlTMsgArray.getValidCount() ; i++) {
            CM_AP_INV_DTLTMsg cmApInvDtlTMsg = cmApInvDtlTMsgArray.no(i);
            String apVndInvNum = cmApInvDtlTMsg.apVndInvNum.getValue();
            String poNum = cmApInvDtlTMsg.poNum.getValue();
            String poOrdDtlLineNum = cmApInvDtlTMsg.poOrdDtlLineNum.getValue();
            String mdseCd = cmApInvDtlTMsg.mdseCd.getValue();
            BigDecimal apInvQty = cmApInvDtlTMsg.invQty.getValue();

            if (apInvQty.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }

            // For Blanket PO Amount check
            boolean blanketPOFlg = NFBL2040CommonLogic.chkPOCloseforBP(poNum, apVndInvNum, mdseCd);
            if (!blanketPOFlg) {
                continue;
            }

            for (int j = 0 ; j < poDtlList.size() ; j++) {
                BigDecimal upInvQty = apInvQty;
                PO_DTLTMsg poDtlTMsg = poDtlList.get(j);
                if (poDtlTMsg.poOrdNum.getValue().equals(poNum) && poDtlTMsg.poOrdDtlLineNum.getValue().equals(poOrdDtlLineNum)
                        && poDtlTMsg.mdseCd.getValue().equals(mdseCd)) {

                    // START 2018/11/26 [QC#28660, DEL]
                    // BigDecimal poInvQty = poDtlTMsg.poInvQty.getValue();
                    // if (poInvQty.compareTo(apInvQty) < 0) {
                    //     upInvQty = poInvQty;
                    // }
                    // END   2018/11/26 [QC#28660, DEL]

                    String msgId = NFBL2040CommonLogic.updatePoInformation(poNum, poOrdDtlLineNum, upInvQty.negate(), mdseCd);
                    if (msgId != null) {
                        bizMsg.setMessageInfo(msgId);
                        break;
                    }
                }
            }
        }
    }
    // END   2018/08/13 [QC#27025-2, ADD]

    // START 2018/11/20 [QC#28660, ADD]
    private boolean checkAssociatedCreditMemo(NFBL2040CMsg bizMsg) {
        // START 2020/03/23 [QC#53413, MOD]
//        if ((!CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())) && ZYPCommonFunc.hasValue(bizMsg.poNum)
//                &&  ACCT_INV_STS.AUTHORIZED.equals(bizMsg.acctInvStsCd.getValue())) {
//            S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getAssociatedCreditMemo(bizMsg.poNum.getValue());
//            if (ssmResult.isCodeNormal()) {
//                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
//                if (resultList.size() > 0) {
//                    bizMsg.setMessageInfo(NFBM0286E);
//                    return false;
//                }
//            }
//        }
        List<String> poNumList = new ArrayList<String>();
        if (!CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ACCT_INV_STS.AUTHORIZED.equals(bizMsg.acctInvStsCd.getValue())) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).poNum_A1)) {
                    String poNum = bizMsg.A.no(i).poNum_A1.getValue();
                    if (!poNumList.contains(poNum)) {
                        continue;
                    } else {
                        poNumList.add(poNum);
                        S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getAssociatedCreditMemo(poNum);
                        if (ssmResult.isCodeNormal()) {
                            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                            if (resultList.size() > 0) {
                                bizMsg.setMessageInfo(NFBM0286E);
                                return false;
                            }
                        }
                    }
                }
            }
        }
        // END   2020/03/23 [QC#53413, MOD]
        return true;
    }

    private void holdAssociatedInvoice(NFBL2040CMsg bizMsg) {

        if (CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) && ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            S21SsmEZDResult ssmResult = NFBL2040Query.getInstance().getAssociatedInvoice(bizMsg.poNum.getValue());
            if (ssmResult.isCodeNormal()) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                if (resultList.size() > 0) {
                    for(Map<String, Object> result : resultList) {

                        CM_AP_INV_HDRTMsg cmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndCd, (String)result.get("AP_VND_CD"));
                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvNum, (String)result.get("AP_VND_INV_NUM"));
                        ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.apVndInvSqNum, (String)result.get("AP_VND_INV_SQ_NUM"));
                        cmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdrTMsg);
                        if (cmApInvHdrTMsg != null) {
                            if (!ACCT_INV_STS.AUTHORIZED.equals(cmApInvHdrTMsg.acctInvStsCd.getValue())) {
                                continue;
                            }

                            // START 2018/11/26 [QC#28660, ADD]
                            // update CM_INV_PMT_HLD
                            CM_INV_PMT_HLDTMsg param = new CM_INV_PMT_HLDTMsg();
                            param.setSQLID("003");
                            param.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
                            param.setConditionValue("apVndCd01", (String)result.get("AP_VND_CD"));
                            param.setConditionValue("apVndInvNum01", (String)result.get("AP_VND_INV_NUM"));
                            param.setConditionValue("apVndInvSqNum01", (String)result.get("AP_VND_INV_SQ_NUM"));
                            param.setConditionValue("pmtHldFlg01", ZYPConstant.FLG_OFF_N);
                            param.setConditionValue("pmtHldCd01", PMT_HLD.THEREFORE);
                            CM_INV_PMT_HLDTMsgArray holdArray = (CM_INV_PMT_HLDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
                            if (holdArray != null) {
                                for (int i = 0 ; i < holdArray.getValidCount() ; i++) {
                                    CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = holdArray.no(i);

                                    if (PMT_HLD.SUCCESS.equals(cmInvPmtHldTMsg.pmtHldCd.getValue())) {
                                        continue;
                                    }

                                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldDt, ZYPDateUtil.getSalesDate());
                                    ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.pmtHldPsnCd, USER_ID);
                                    cmInvPmtHldTMsg.pmtHldRelDt.clear();
                                    cmInvPmtHldTMsg.pmtHldRelRsnCd.clear();
                                    cmInvPmtHldTMsg.pmtHldRelPsnCd.clear();
                                    cmInvPmtHldTMsg.pmtHldRelCmntTxt.clear();
                                    EZDTBLAccessor.update(cmInvPmtHldTMsg);
                                    if (!RTNCD_NORMAL.equals(cmInvPmtHldTMsg.getReturnCode())) {
                                        bizMsg.setMessageInfo(NFBM0224E, new String[] {"CM Invoice Payment Hold", cmInvPmtHldTMsg.getReturnCode() });
                                        return;
                                    }
                                }
                            }
                            // END   2018/11/26 [QC#28660, ADD]

                            // update CM_AP_INV_HDR
                            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.acctInvStsCd, ACCT_INV_STS.ENTERED);
                            ZYPEZDItemValueSetter.setValue(cmApInvHdrTMsg.pmtHldFlg, ZYPConstant.FLG_ON_Y);
                            EZDTBLAccessor.update(cmApInvHdrTMsg);
                            if (!RTNCD_NORMAL.equals(cmApInvHdrTMsg.getReturnCode())) {
                                bizMsg.setMessageInfo(NFBM0224E, new String[] {"CM AP Invoice Header", cmApInvHdrTMsg.getReturnCode() });
                                return;
                            }

                            // update PO Invoiced Qty
                            updatePoStatusForAuthorizedInvoice(bizMsg,
                                    (String)result.get("AP_VND_CD"), (String)result.get("AP_VND_INV_NUM"), (String)result.get("AP_VND_INV_SQ_NUM"), bizMsg.poNum.getValue());
                        } 
                        
                    }
                }
            }
        }
    }

    private void updatePoStatusForAuthorizedInvoice(NFBL2040CMsg bizMsg, String vndCd, String vndInvNum, String vndInvSqNum, String poOrdNum) {

        ArrayList<PO_DTLTMsg> poDtlList = NFBL2040CommonLogic.getPoDtlListForUpdateNoWait(poOrdNum);
        if (poDtlList == null) {
            return;
        }

        CM_AP_INV_DTLTMsg inTMsg = new CM_AP_INV_DTLTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", GLBL_CMPY_CD);
        inTMsg.setConditionValue("apVndCd01", vndCd);
        inTMsg.setConditionValue("apVndInvNum01", vndInvNum);
        inTMsg.setConditionValue("apVndInvSqNum01", vndInvSqNum);

        CM_AP_INV_DTLTMsgArray cmApInvDtlTMsgArray = (CM_AP_INV_DTLTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (cmApInvDtlTMsgArray == null) {
            return;
        }

        for (int i = 0 ; i < cmApInvDtlTMsgArray.getValidCount() ; i++) {
            CM_AP_INV_DTLTMsg cmApInvDtlTMsg = cmApInvDtlTMsgArray.no(i);
            String apVndInvNum = cmApInvDtlTMsg.apVndInvNum.getValue();
            String poNum = cmApInvDtlTMsg.poNum.getValue();
            String poOrdDtlLineNum = cmApInvDtlTMsg.poOrdDtlLineNum.getValue();
            String mdseCd = cmApInvDtlTMsg.mdseCd.getValue();
            BigDecimal apInvQty = cmApInvDtlTMsg.invQty.getValue();

            if (apInvQty.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }

            // For Blanket PO Amount check
            boolean blanketPOFlg = NFBL2040CommonLogic.chkPOCloseforBP(poNum, apVndInvNum, mdseCd);
            if (!blanketPOFlg) {
                continue;
            }

            for (int j = 0 ; j < poDtlList.size() ; j++) {
                BigDecimal upInvQty = apInvQty;
                PO_DTLTMsg poDtlTMsg = poDtlList.get(j);
                if (poDtlTMsg.poOrdNum.getValue().equals(poNum) && poDtlTMsg.poOrdDtlLineNum.getValue().equals(poOrdDtlLineNum)
                        && poDtlTMsg.mdseCd.getValue().equals(mdseCd)) {

                    // START 2018/11/26 [QC#28660, DEL]
                    // BigDecimal poInvQty = poDtlTMsg.poInvQty.getValue();
                    // if (poInvQty.compareTo(apInvQty) < 0) {
                    //     upInvQty = poInvQty;
                    // }
                    // END   2018/11/26 [QC#28660, DEL]

                    String msgId = NFBL2040CommonLogic.updatePoInformation(poNum, poOrdDtlLineNum, upInvQty.negate(), mdseCd);
                    if (msgId != null) {
                        bizMsg.setMessageInfo(msgId);
                        break;
                    }
                }
            }
        }
    }
    // END   2018/11/20 [QC#28660, ADD]

    // START 2018/11/29 [QC#28904, ADD]
    /**
     * Method name: doProcess_NFBL2040Scrn00_Regenerate_Invoice
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFBL2040Scrn00_Regenerate_Invoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Regenerate_Invoice========================START", this);

        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.poNum)) {
            if (!NFBL2040CommonLogic.checkPoSts(bizMsg)) {
                return;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.ezUpTime) && ZYPCommonFunc.hasValue(bizMsg.ezUpTimeZone)) {
            CM_AP_INV_HDRTMsg outMsg = new CM_AP_INV_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndCd, bizMsg.apVndCd);
            ZYPEZDItemValueSetter.setValue(outMsg.apInvTpCd, AP_INV_TP_CD_00);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndInvNum, bizMsg.apVndInvNum);
            ZYPEZDItemValueSetter.setValue(outMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            outMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(outMsg);
            if (outMsg == null) {
                bizMsg.setMessageInfo("NFBM0069E");
                return;
            } else if (outMsg != null && !ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("NZZM0003E");
                return;
            }
        }

        // delete CM_AP_INV_HDR, CM_AP_INV_DTL, CM_INV_ACCT_DIST, CM_INV_PMT_HLD
        removeInvoice(bizMsg);

        EZDDebugOutput.println(5, "doProcess_NFBL2040Scrn00_Regenerate_Invoice==========================END", this);
    }

    private void removeInvoice(NFBL2040CMsg bizMsg)  {

        // remove record. (CM_AP_INV_HDR)
        CM_AP_INV_HDRTMsg rmvCmApInvHdrTMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum.getValue());
        ZYPEZDItemValueSetter.setValue(rmvCmApInvHdrTMsg.apInvTpCd, AP_INV_TP_CD_00);
        rmvCmApInvHdrTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(rmvCmApInvHdrTMsg);
        if (rmvCmApInvHdrTMsg == null) {
            bizMsg.setMessageInfo(NFBM0069E);
            return;
        }
        EZDTBLAccessor.remove(rmvCmApInvHdrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rmvCmApInvHdrTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFBM0073E, new String[] {rmvCmApInvHdrTMsg.getTableName(), rmvCmApInvHdrTMsg.getReturnCode() });
            return;
        }

        // remove records. (CM_AP_INV_DTL)
        CM_AP_INV_DTLTMsg cmApInvDtlTMsg = new CM_AP_INV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmApInvDtlTMsg.apInvTpCd, AP_INV_TP_CD_00);
        S21FastTBLAccessor.removeByPartialValue(cmApInvDtlTMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum", "apInvTpCd" });

        // remove records. (CM_INV_ACCT_DIST)
        CM_INV_ACCT_DISTTMsg cmInvAcctDistTMsg = new CM_INV_ACCT_DISTTMsg();
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvAcctDistTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum.getValue());
        S21FastTBLAccessor.removeByPartialValue(cmInvAcctDistTMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" });

        // remove records. (CM_INV_PMT_HLD)
        CM_INV_PMT_HLDTMsg cmInvPmtHldTMsg = new CM_INV_PMT_HLDTMsg();
        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndCd, bizMsg.apVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvNum, bizMsg.apVndInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(cmInvPmtHldTMsg.apVndInvSqNum, bizMsg.apVndInvSqNum.getValue());
        S21FastTBLAccessor.removeByPartialValue(cmInvPmtHldTMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" });
    }
    // END   2018/11/29 [QC#28904, ADD]

    // START 2019/02/21 [QC#30420, MOD]
    private int findInvoiceLineIndex(NFBL2040CMsg bizMsg, NFBL2040_HCMsg hBizMsg) {
        String apVndInvLineNum = hBizMsg.apVndInvLineNum_H1.getValue();

        if(!ZYPCommonFunc.hasValue(apVndInvLineNum)) {
            return -1;
        }

        if (EMPTY_AP_VND_INV_LINE_NUM.equals(apVndInvLineNum)) {
            return -1;
        }

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (apVndInvLineNum.equals(bizMsg.A.no(i).xxDtlLineNum_A1.getValue())) {
                return i;
            }
        }
        return -1;
    }

    private void removeNotInvoicedLineForDtl(List<List<String>> recordPkList, Map<List<String>, Map<String, Object>> recordDataMap) {
        removeNotInvoicedLine(recordPkList, recordDataMap, AC_OC_FOB_COST_AMT, INV_QTY);
    }

    private void removeNotInvoicedLineForDist(List<List<String>> recordPkList, Map<List<String>, Map<String, Object>> recordDataMap) {
        removeNotInvoicedLine(recordPkList, recordDataMap, LINE_AMT, AP_BILL_QTY);
    }

    private void removeNotInvoicedLine(List<List<String>> recordPkList, Map<List<String>, Map<String, Object>> recordDataMap, String lineAmtColName, String invQtyColName) {

        String currentInvSqNum = getLatestApVndInvSeqNum(recordPkList, recordDataMap);

        List<List<String>> newRecordPkList = new ArrayList<List<String>>();
        for (int i = 0; i < recordPkList.size(); i++) {
            List<String> recordPk = recordPkList.get(i);
            Map<String, Object> recordData = (Map<String, Object>) recordDataMap.get(recordPk);

            String invSqNum = recordPk.get(3); // AP_VND_INV_SQ_NUM
            if (!currentInvSqNum.equals(invSqNum)) {
                // skip invalid line, credit line
                newRecordPkList.add(recordPk);
                continue;
            }

            BigDecimal lineAmt = NFBCommonBusiness.chkNull((BigDecimal)recordData.get(lineAmtColName));
            BigDecimal invQty = NFBCommonBusiness.chkNull((BigDecimal)recordData.get(invQtyColName));
            if (BigDecimal.ZERO.compareTo(lineAmt) == 0 && BigDecimal.ZERO.compareTo(invQty) == 0) {
                // remove record from map
                recordDataMap.remove(recordPk);

            } else {
                // add new primary key list
                newRecordPkList.add(recordPk);
            }
        }

        // renewal primary key list
        recordPkList.clear();
        recordPkList.addAll(newRecordPkList);
    }


    private void renumberInvLineNumber(List<List<String>> recordPkList, Map<List<String>, Map<String, Object>> recordDataMap) {

        String currentInvSqNum = getLatestApVndInvSeqNum(recordPkList, recordDataMap);
        int lastApVndInvLineNum = getLastApVndInvLineNumOfItemLine(recordPkList, recordDataMap);

        List<List<String>> newRecordPkList = new ArrayList<List<String>>();
        Map<List<String>, Map<String, Object>> newRecordDataMap = new HashMap<List<String>,Map<String, Object>>();;

        int curApVndInvLineNum = lastApVndInvLineNum + 1;
        for (int i = 0; i < recordPkList.size(); i++) {
            List<String> recordPk = recordPkList.get(i);
            Map<String, Object> recordData = (Map<String, Object>) recordDataMap.get(recordPk);

            String invSqNum = recordPk.get(3); // AP_VND_INV_SQ_NUM
            if (!currentInvSqNum.equals(invSqNum)) {
                // skip invalid line, credit line
                newRecordPkList.add(recordPk);
                newRecordDataMap.put(recordPk, recordData);
                continue;
            }

            if (AP_LINE_TP.ITEM.equals((String)recordData.get(AP_LINE_TP_CD))) {
                // skip item line
                newRecordPkList.add(recordPk);
                newRecordDataMap.put(recordPk, recordData);
                continue;
            }

            // update pk & record
            recordPk.set(4, String.format(AP_VND_INV_LINE_NUM_FORMAT, curApVndInvLineNum));
            newRecordPkList.add(recordPk);
            newRecordDataMap.put(recordPk, recordData);
            curApVndInvLineNum++;
        }

        // replace pk list, record map
        recordPkList.clear();
        recordPkList.addAll(newRecordPkList);
        recordDataMap.clear();
        for (int i = 0; i < newRecordPkList.size(); i++) {
            List<String> recordPk = newRecordPkList.get(i);
            Map<String, Object> recordData = (Map<String, Object>) newRecordDataMap.get(recordPk);
            recordDataMap.put(recordPk, recordData);
        }
    }

    private int getLastApVndInvLineNumOfItemLine(List<List<String>> recordPkList, Map<List<String>, Map<String, Object>> recordDataMap) {
        int lastApVndInvLineNum = 0;
        for (int i = 0; i < recordPkList.size(); i++) {
            List<String> recordPk = recordPkList.get(i);
            Map<String, Object> recordData = (Map<String, Object>) recordDataMap.get(recordPk);

            if (!AP_LINE_TP.ITEM.equals((String)recordData.get(AP_LINE_TP_CD))) {
                continue;
            }

            String invLineNum = recordPk.get(4); // AP_VND_INV_LINE_NUM
            if (Integer.parseInt(invLineNum) > lastApVndInvLineNum) {
                lastApVndInvLineNum = Integer.parseInt(invLineNum);
            }
        }
        return lastApVndInvLineNum;
    }

    private String getLatestApVndInvSeqNum(List<List<String>> recordPkList, Map<List<String>, Map<String, Object>> recordDataMap) {

        String latestApVndInvSeqNum = AP_VND_INV_SQ_NUM_00;

        for (int i = 0; i < recordPkList.size(); i++) {
            List<String> recordPk = recordPkList.get(i);

            String apVndInvSqNum = recordPk.get(3); // AP_VND_INV_SQ_NUM
            if (Integer.parseInt(apVndInvSqNum) > Integer.parseInt(latestApVndInvSeqNum)) {
                latestApVndInvSeqNum = apVndInvSqNum;
            }
        }

        return latestApVndInvSeqNum;
    }
    // END   2019/02/21 [QC#30420, MOD]

    // START 2019/03/19 [QC#30829, ADD]
    private boolean checkHoldReleaseForVendorReturn(NFBL2040CMsg bizMsg, List<Integer> checkedRows) {
        boolean result = true;

        for (int i = 0 ; i < bizMsg.A.getValidCount(); i++) {
            NFBL2040_ACMsg aCMsg = bizMsg.A.no(i);
            // START 2020/02/07 [QC#55694, ADD]
            if (!AP_LINE_TP.ITEM.equals(bizMsg.A.no(i).apLineTpCd_A1.getValue())) {
                continue;
            }
            // END 2020/02/07 [QC#55694, ADD]
            // mod start 2022/02/15 QC#57090
           //String vndRtrnNum = bizMsg.vndRtrnNum.getValue();
            String vndRtrnNum = aCMsg.vndRtrnNum_A1.getValue();
            if (!ZYPCommonFunc.hasValue(vndRtrnNum)) {
                continue;
            }
            // mod end 2022/02/15 QC#57090
            String apVndInvLineNum = (String) aCMsg.xxDtlLineNum_A1.getValue();
            String poOrdDtlLineNum = (String) aCMsg.poOrdDtlLineNum_A1.getValue();
            String mdseCd = aCMsg.mdseCd_A1.getValue();

            // Compare Received Quantity to Invoice(Credit memo is negative)
            BigDecimal invoicedQty = NFBL2040CommonLogic.getSumBilledQtyVndRtrn(bizMsg, vndRtrnNum, mdseCd, poOrdDtlLineNum);
            BigDecimal shipQty = bizMsg.A.no(i).invRcvQty_A1.getValue();

            if (shipQty.compareTo(invoicedQty) > 0) {
                // START 2020/03/16 [QC#55993, MOD]
                //int hIdx = findHoldReleaseTargetIndex(bizMsg, checkedRows, apVndInvLineNum);
                //if (hIdx >= 0) {
                //    bizMsg.H.no(hIdx).xxChkBox_H1.setErrorInfo(1, NFBM0282E, new String[] { "Invoiced Qty", "Received Qty" } );
                //}
                //aCMsg.apBillQty_A1.setErrorInfo(1, NFBM0282E, new String[] { "Invoiced Qty", "Received Qty" } );
                //result = false;
                if (!ZYPCommonFunc.hasValue(bizMsg.xxWrnSkipFlg_A.getValue()) || ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_A.getValue())) {
                    int hIdx = findHoldReleaseTargetIndex(bizMsg, checkedRows, apVndInvLineNum);
                    if (hIdx >= 0) {
                        bizMsg.H.no(hIdx).xxChkBox_H1.setErrorInfo(2, NFBM0292W, new String[] { "Invoiced Qty", "Received Qty" } );
                        if(bizMsg.H.no(hIdx).apVndInvLineNum_H1.getValue().equals(apVndInvLineNum)) {
                            aCMsg.apBillQty_A1.setErrorInfo(2, NFBM0292W, new String[] { "Invoiced Qty", "Received Qty" } );
                        }
                    }
                    bizMsg.setMessageInfo("NFBM0291W", new String[] { "Invoiced Qty", "Received Qty" });
                    result = false;
                }
                // END   2020/03/16 [QC#55993, MOD]
            }
        }
        // START 2020/03/16 [QC#55993, ADD]
        if (!result) {
            if (!ZYPCommonFunc.hasValue(bizMsg.xxWrnSkipFlg_A.getValue()) || ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_OFF_N);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_OFF_N);
        }
        // END   2020/03/16 [QC#55993, ADD]

        return result;
    }
    // END   2019/03/19 [QC#30829, ADD]
    
    // Start 2023/1/17 S.Nakatani [QC#60812, ADD]
    private void updateInvNum(EZDCMsg cMsg, EZDSMsg sMsg){
        NFBL2040CMsg bizMsg = (NFBL2040CMsg) cMsg;
        
        // check can update Invoice# or not
        if(!isInvNumUpdatable(bizMsg)){
            return;
        }
        
        // update AP_INV_PMT
        if(!updateApInvPmt(bizMsg)){
            return;
        }
        
        // update CM_AP_INV_HDR
        if(!updateCmAPInvHdr(bizMsg)){
            return;
        }
        
        // update CM_AP_INVD_DTL
        if(!updateCmAPInvDtl(bizMsg)){
            return;
        }
        
        // update CM_INV_ACCT_DIST
        if(!updateCmInvAcctDist(bizMsg)){
            return;
        }
        
        // update CM_INV_PMT_HLD
        if(!updateCmInvPmtHld(bizMsg)){
            return;
        }
        
        // update AJE_CM_INTFC
        if(!updateAjeCmIntfc(bizMsg)){
            return;
        }
        
        // update CM_ACRL_WRITE_OFF
        if(!updateCmAcrlWriteOff(bizMsg)){
            return;
        }
        
        // update JRNL_ENTRY
        if(!updateJrnlEntry(bizMsg)){
            return;
        }
        
        if (NFBL2040CommonLogic.refreshData(bizMsg)) {
            return;
        }
        bizMsg.setMessageInfo(ZZM8100I);

    }
    
    private boolean isInvNumUpdatable(NFBL2040CMsg bizMsg){
        
        // check Invoice# was changed or not
        if (!ZYPCommonFunc.hasValue(bizMsg.apVndInvNum_OT) || bizMsg.apVndInvNum_OT.getValue().equals(bizMsg.apVndInvNum.getValue())) {
            return false;
        }
        
        // check Invoice# dupulication
        if (isDuplicated(bizMsg)) {
            return false;
        }
        
        // check the record updated by others or not
        CM_AP_INV_HDRTMsg outMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(outMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(outMsg.apVndCd, bizMsg.apVndCd);
        ZYPEZDItemValueSetter.setValue(outMsg.apVndInvNum, bizMsg.apVndInvNum_OT);
        ZYPEZDItemValueSetter.setValue(outMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
        outMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(outMsg);
        if (outMsg != null && !ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime.getValue(), bizMsg.ezUpTimeZone.getValue(), outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo("NZZM0003E");
            return false;
        }
        
        return true;
    }
    
    private boolean updateApInvPmt(NFBL2040CMsg bizMsg){
        
        AP_INV_PMTTMsg condMsg = new AP_INV_PMTTMsg();
        
        List<Map<String, Object>> resultList = NFBL2040Query.getInstance().getArcsPmtId(bizMsg.apVndCd.getValue(), bizMsg.apVndInvNum_OT.getValue());
        for(Map<String, Object> map: resultList){
            condMsg = new AP_INV_PMTTMsg();
            ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(condMsg.apVndInvNum, bizMsg.apVndInvNum_OT);
            ZYPEZDItemValueSetter.setValue(condMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            ZYPEZDItemValueSetter.setValue(condMsg.arcsPmtId, (BigDecimal) map.get("ARCS_PMT_ID"));
            ZYPEZDItemValueSetter.setValue(condMsg.apVndCd, (String) map.get("AP_VND_CD"));
            condMsg = (AP_INV_PMTTMsg) EZDTBLAccessor.findByKey(condMsg);
            if(condMsg != null){

                AP_INV_PMTTMsg upMsg = new AP_INV_PMTTMsg();
                ZYPEZDItemValueSetter.setValue(upMsg.apVndInvNum, bizMsg.apVndInvNum);
                
                S21FastTBLAccessor.updateByPartialValue(condMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" }, upMsg, new String[] {"apVndInvNum" });
                if (!RTNCD_NORMAL.equals(upMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NFBM0224E, new String[] {upMsg.getTableName(), upMsg.getReturnCode() });
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean updateCmAPInvHdr(NFBL2040CMsg bizMsg){
        
        CM_AP_INV_HDRTMsg condMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndCd, bizMsg.apVndCd);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndInvNum, bizMsg.apVndInvNum_OT);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
        
        CM_AP_INV_HDRTMsg upMsg = new CM_AP_INV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(upMsg.apVndInvNum, bizMsg.apVndInvNum);
        
        S21FastTBLAccessor.updateByPartialValue(condMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" }, upMsg, new String[] {"apVndInvNum" });
        if (!RTNCD_NORMAL.equals(upMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFBM0224E, new String[] {upMsg.getTableName(), upMsg.getReturnCode() });
            return false;
        }
        
        return true;
    }
    
    private boolean updateCmAPInvDtl(NFBL2040CMsg bizMsg){
        
        CM_AP_INV_DTLTMsg condMsg = new CM_AP_INV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndCd, bizMsg.apVndCd);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndInvNum, bizMsg.apVndInvNum_OT);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
        
        CM_AP_INV_DTLTMsg upMsg = new CM_AP_INV_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(upMsg.apVndInvNum, bizMsg.apVndInvNum);
        
        S21FastTBLAccessor.updateByPartialValue(condMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" }, upMsg, new String[] {"apVndInvNum" });
        if (!RTNCD_NORMAL.equals(upMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFBM0224E, new String[] {upMsg.getTableName(), upMsg.getReturnCode() });
            return false;
        }
        
        return true;
    }
    
    private boolean updateCmInvAcctDist(NFBL2040CMsg bizMsg){
        
        CM_INV_ACCT_DISTTMsg condMsg = new CM_INV_ACCT_DISTTMsg();
        ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndCd, bizMsg.apVndCd);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndInvNum, bizMsg.apVndInvNum_OT);
        ZYPEZDItemValueSetter.setValue(condMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
        
        CM_INV_ACCT_DISTTMsg upMsg = new CM_INV_ACCT_DISTTMsg();
        ZYPEZDItemValueSetter.setValue(upMsg.apVndInvNum, bizMsg.apVndInvNum);
        
        S21FastTBLAccessor.updateByPartialValue(condMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum" }, upMsg, new String[] {"apVndInvNum" });
        if (!RTNCD_NORMAL.equals(upMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NFBM0224E, new String[] {upMsg.getTableName(), upMsg.getReturnCode() });
            return false;
        }
        
        return true;
    }

    private boolean updateCmInvPmtHld(NFBL2040CMsg bizMsg){
        
        CM_INV_PMT_HLDTMsg condMsg = new CM_INV_PMT_HLDTMsg();
        
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            condMsg = new CM_INV_PMT_HLDTMsg();
            ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(condMsg.apVndCd, bizMsg.apVndCd);
            ZYPEZDItemValueSetter.setValue(condMsg.apVndInvNum, bizMsg.apVndInvNum_OT);
            ZYPEZDItemValueSetter.setValue(condMsg.apVndInvSqNum, bizMsg.apVndInvSqNum);
            ZYPEZDItemValueSetter.setValue(condMsg.apVndInvLineNum, bizMsg.H.no(i).apVndInvLineNum_H1);
            ZYPEZDItemValueSetter.setValue(condMsg.pmtHldCd, bizMsg.H.no(i).pmtHldCd_H1);
            ZYPEZDItemValueSetter.setValue(condMsg.pmtHldRsnCd, bizMsg.H.no(i).pmtHldRsnCd_H1);
            
            condMsg = (CM_INV_PMT_HLDTMsg) EZDTBLAccessor.findByKeyForUpdate(condMsg);
            if(condMsg != null){
                CM_INV_PMT_HLDTMsg upMsg = new CM_INV_PMT_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(upMsg.apVndInvNum, bizMsg.apVndInvNum);
                
                S21FastTBLAccessor.updateByPartialValue(condMsg, new String[] {"glblCmpyCd", "apVndCd", "apVndInvNum", "apVndInvSqNum", "apVndInvLineNum", "pmtHldCd", "pmtHldRsnCd" }
                                                        , upMsg, new String[] {"apVndInvNum" });
                if (!RTNCD_NORMAL.equals(upMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NFBM0224E, new String[] {upMsg.getTableName(), upMsg.getReturnCode() });
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean updateAjeCmIntfc(NFBL2040CMsg bizMsg){
        
        List<Map<String, Object>> resultList = NFBL2040Query.getInstance().getUpateAjeCmIntfcRecord(bizMsg.apVndCd.getValue(), bizMsg.apVndInvNum_OT.getValue());

        AJE_CM_INTFCTMsg condMsg =  new AJE_CM_INTFCTMsg();
        for(Map<String, Object> map: resultList){
            ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(condMsg.ajeCmIntfcPk, (BigDecimal) map.get("AJE_CM_INTFC_PK"));
            condMsg = (AJE_CM_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdate(condMsg);
            if(condMsg == null){
                continue;
            }
            
            ZYPEZDItemValueSetter.setValue(condMsg.apVndInvNum, bizMsg.apVndInvNum);
            EZDTBLAccessor.update(condMsg);
            if (!RTNCD_NORMAL.equals(condMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0224E, new String[] {condMsg.getTableName(), condMsg.getReturnCode() });
                return false;
            }
        }
        
        return true;
    }
    
    private boolean updateCmAcrlWriteOff(NFBL2040CMsg bizMsg){
        
        List<Map<String, Object>> resultList = NFBL2040Query.getInstance().getUpateCmAcrlWriteOffRecord(bizMsg.apVndCd.getValue(), bizMsg.apVndInvNum_OT.getValue());

        CM_ACRL_WRITE_OFFTMsg condMsg =  new CM_ACRL_WRITE_OFFTMsg();
        for(Map<String, Object> map: resultList){
            ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(condMsg.cmAcrlWriteOffPk, (BigDecimal) map.get("CM_ACRL_WRITE_OFF_PK"));
            condMsg = (CM_ACRL_WRITE_OFFTMsg) EZDTBLAccessor.findByKeyForUpdate(condMsg);
            if(condMsg == null){
                continue;
            }
            
            ZYPEZDItemValueSetter.setValue(condMsg.apVndInvNum, bizMsg.apVndInvNum);
            EZDTBLAccessor.update(condMsg);
            if (!RTNCD_NORMAL.equals(condMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0224E, new String[] {condMsg.getTableName(), condMsg.getReturnCode() });
                return false;
            }
        }
        
        return true;
    }
    
    private boolean updateJrnlEntry(NFBL2040CMsg bizMsg){
        
        List<Map<String, Object>> resultList = NFBL2040Query.getInstance().getUpateJrnlEntryRecord(bizMsg.apVndCd.getValue(), bizMsg.apVndInvNum_OT.getValue());

        JRNL_ENTRYTMsg condMsg =  new JRNL_ENTRYTMsg();
        for(Map<String, Object> map: resultList){
            ZYPEZDItemValueSetter.setValue(condMsg.glblCmpyCd, GLBL_CMPY_CD);
            ZYPEZDItemValueSetter.setValue(condMsg.jrnlEntryPk, (BigDecimal) map.get("JRNL_ENTRY_PK"));
            condMsg = (JRNL_ENTRYTMsg) EZDTBLAccessor.findByKeyForUpdate(condMsg);
            if(condMsg == null){
                continue;
            }
            
            ZYPEZDItemValueSetter.setValue(condMsg.jrnlEntryScdRefTxt, bizMsg.apVndInvNum);
            EZDTBLAccessor.update(condMsg);
            if (!RTNCD_NORMAL.equals(condMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NFBM0224E, new String[] {condMsg.getTableName(), condMsg.getReturnCode() });
                return false;
            }
        }
        
        return true;
    }
    // END 2023/1/17 S.Nakatani [QC#60812, ADD]
}
