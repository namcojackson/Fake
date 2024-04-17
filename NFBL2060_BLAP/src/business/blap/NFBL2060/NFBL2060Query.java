/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2060;

import java.util.HashMap;
import java.util.Map;

import business.blap.NFBL2060.constant.NFBL2060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
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
 * 2016/07/26   Hitachi         T.Tsuchida      Update          QC#12239
 * 2016/09/27   Hitachi         Y.Tsuchimoto    Update          QC#14797
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/05   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2016/11/22   Hitachi         T.Tsuchida      Update          QC#16210
 * 2017/06/26   CITS            M.Naito         Update          QC#19392
 * 2018/03/23   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 * 2018/10/18   Hitachi         J.Kim           Update          QC#28816
 * 2024/02/05   Hitachi         S.Ikariya       Update          QC#63451
 * </pre>
 */
public final class NFBL2060Query extends S21SsmEZDQuerySupport implements NFBL2060Constant {
    /**
     * Singleton instance.
     */
    private static final NFBL2060Query INSTANCE = new NFBL2060Query();
    /**
     * User Profile
     */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /**
     * Global Company Code.
     */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    /**
     * Constructor.
     */
    private NFBL2060Query() {
        super();
    }
    /**
     * Singleton instance getter.
     * @return NFBL2060Query
     */
    public static NFBL2060Query getInstance() {
        return INSTANCE;
    }

    /**
     * NFBL2060Query.xml id="getApInvCatgPulldownValue"
     * 
     * <pre>
     * Create Pulldown list of AP_INV_CATG_CD and AP_INV_CATG_DESC_TXT from AP_INV_CATG table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApInvCatgPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getApInvCatgPulldownValue", queryParam, -1, -1);

    }
    
    /**
     * NFBL2060Query.xml id="getDetailedTabRecord"
     * @param bizMsg NFBL2060CMsg
     * @param globalMsg NFBL2060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailedTabRecord(NFBL2060CMsg bizMsg, NFBL2060SMsg globalMsg, boolean blSearch) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apVndCd", bizMsg.apVndCd.getValue());
        ssmParam.put("prntVndCd", bizMsg.prntVndCd.getValue());
        ssmParam.put("dplyVndNm", bizMsg.dplyVndNm.getValue());
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        ssmParam.put("poNum", bizMsg.poNum.getValue());
        // QC#25902
        ssmParam.put("vndRtrnNum", bizMsg.vndRtrnNum.getValue());
        ssmParam.put("vndRtrnSubmtDt_FR", bizMsg.vndRtrnSubmtDt_FR.getValue());
        ssmParam.put("vndRtrnSubmtDt_TO", bizMsg.vndRtrnSubmtDt_TO.getValue());
        // QC#25902 End
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        ssmParam.put("delyOrdNum", bizMsg.delyOrdNum.getValue());
        ssmParam.put("poDt_FR", bizMsg.poDt_FR.getValue());
        ssmParam.put("poDt_TO", bizMsg.poDt_TO.getValue());
        // START 2018/03/23 [QC#20316, ADD]
        ssmParam.put("dispPoDtlLineNum", bizMsg.dispPoDtlLineNum.getValue());
        // END   2018/03/23 [QC#20316, ADD]
        ssmParam.put("vndPmtTermDescTxt", bizMsg.vndPmtTermDescTxt.getValue());
        ssmParam.put("apVndInvNum", bizMsg.apVndInvNum.getValue());
        ssmParam.put("acctInvStsCd", bizMsg.acctInvStsCd_S.getValue());
        ssmParam.put("invDt_FR", bizMsg.invDt_FR.getValue());
        ssmParam.put("invDt_TO", bizMsg.invDt_TO.getValue());
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        ssmParam.put("acInvTotCostAmt_FR", bizMsg.acInvTotCostAmt_FR.getValue());
        ssmParam.put("acInvTotCostAmt_TO", bizMsg.acInvTotCostAmt_TO.getValue());
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]
        
        // Charge Account From
        ssmParam.put("xxCmntTxt_FR", bizMsg.xxCmntTxt_FR.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxCmntTxt_FR)) {
            String xxCmntTxt_FR = bizMsg.xxCmntTxt_FR.getValue();
            String[] strSplit_FR = xxCmntTxt_FR.split("\\.");
            ssmParam.put("coaCmpyCd_FR", strSplit_FR[0]);
            ssmParam.put("coaBrCd_FR", strSplit_FR[1]);
            ssmParam.put("coaCcCd_FR", strSplit_FR[2]);
            ssmParam.put("coaAcctCd_FR", strSplit_FR[3]);
            ssmParam.put("coaProdCd_FR", strSplit_FR[4]);
            ssmParam.put("coaChCd_FR", strSplit_FR[5]);
            ssmParam.put("coaAfflCd_FR", strSplit_FR[6]);
            ssmParam.put("coaProjCd_FR", strSplit_FR[7]);
            ssmParam.put("coaExtnCd_FR", strSplit_FR[8]);
        } else {
            ssmParam.put("coaCmpyCd_FR", EMPTY_STRING);
            ssmParam.put("coaBrCd_FR", EMPTY_STRING);
            ssmParam.put("coaCcCd_FR", EMPTY_STRING);
            ssmParam.put("coaAcctCd_FR", EMPTY_STRING);
            ssmParam.put("coaProdCd_FR", EMPTY_STRING);
            ssmParam.put("coaChCd_FR", EMPTY_STRING);
            ssmParam.put("coaAfflCd_FR", EMPTY_STRING);
            ssmParam.put("coaProjCd_FR", EMPTY_STRING);
            ssmParam.put("coaExtnCd_FR", EMPTY_STRING);
        }

        // Charge Account To
        ssmParam.put("xxCmntTxt_TO", bizMsg.xxCmntTxt_TO.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxCmntTxt_TO)) {
            String xxCmntTxt_TO = bizMsg.xxCmntTxt_TO.getValue();
            String[] strSplit_TO = xxCmntTxt_TO.split("\\.");
            ssmParam.put("coaCmpyCd_TO", strSplit_TO[0]);
            ssmParam.put("coaBrCd_TO", strSplit_TO[1]);
            ssmParam.put("coaCcCd_TO", strSplit_TO[2]);
            ssmParam.put("coaAcctCd_TO", strSplit_TO[3]);
            ssmParam.put("coaProdCd_TO", strSplit_TO[4]);
            ssmParam.put("coaChCd_TO", strSplit_TO[5]);
            ssmParam.put("coaAfflCd_TO", strSplit_TO[6]);
            ssmParam.put("coaProjCd_TO", strSplit_TO[7]);
            ssmParam.put("coaExtnCd_TO", strSplit_TO[8]);
        } else {
            ssmParam.put("coaCmpyCd_TO", EMPTY_STRING);
            ssmParam.put("coaBrCd_TO", EMPTY_STRING);
            ssmParam.put("coaCcCd_TO", EMPTY_STRING);
            ssmParam.put("coaAcctCd_TO", EMPTY_STRING);
            ssmParam.put("coaProdCd_TO", EMPTY_STRING);
            ssmParam.put("coaChCd_TO", EMPTY_STRING);
            ssmParam.put("coaAfflCd_TO", EMPTY_STRING);
            ssmParam.put("coaProjCd_TO", EMPTY_STRING);
            ssmParam.put("coaExtnCd_TO", EMPTY_STRING);
        }

        ssmParam.put("apInvCatgCd_S", bizMsg.apInvCatgCd_S.getValue());
        ssmParam.put("vndPmtMethDescTxt", bizMsg.vndPmtMethDescTxt.getValue());
        ssmParam.put("apPmtChkNum", bizMsg.apPmtChkNum.getValue());
        ssmParam.put("pmtDt_FR", bizMsg.pmtDt_FR.getValue());
        ssmParam.put("pmtDt_TO", bizMsg.pmtDt_TO.getValue());
        ssmParam.put("xxChkBox_01", bizMsg.xxChkBox_01.getValue()); // Holds check box
        ssmParam.put("xxChkBox_02", bizMsg.xxChkBox_02.getValue()); // PO Variance check box
        ssmParam.put("rowNum", globalMsg.D.length() + 1);
        // START 2016/09/27 Y.Tsuchimoto [QC#14797,MOD]
        ssmParam.put("variance", AP_LINE_TP.VARIANCE);
        // END   2016/09/27 Y.Tsuchimoto [QC#14797,MOD]
        ssmParam.put("none", NONE);
        // START 2016/11/22 T.Tsuchida [QC#16210,ADD]
        ssmParam.put("apLineTpItem", AP_LINE_TP.ITEM);
        // END 2016/11/22 T.Tsuchida [QC#16210,ADD]
        // START 2017/12/15 S.Katsuma [QC#22457,ADD]
        ssmParam.put("acctInvStsCdCancelled", ACCT_INV_STS.CANCEL);
        // END 2017/12/15 S.Katsuma [QC#22457,ADD]
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        ssmParam.put("invAuthDt_FR", bizMsg.invAuthDt_FR.getValue());
        ssmParam.put("invAuthDt_TO", bizMsg.invAuthDt_TO.getValue());
        ssmParam.put("acctInvStsL", ACCT_INV_STS.LINKED_TO_COST_CALCULATION);
        ssmParam.put("acctInvStsP", ACCT_INV_STS.PAID);
        ssmParam.put("acctInvStsV", ACCT_INV_STS.VOIDED);
        // END 2018/10/18 J.Kim [QC#28816,ADD]

        return getSsmEZDClient().queryEZDMsgArray("getDetailedTabRecord", ssmParam, globalMsg.D);
    }

    /**
     * NFBL2060Query.xml id="getSummaryTabRecord"
     * @param bizMsg NFBL2060CMsg
     * @param globalMsg NFBL2060SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSummaryTabRecord(NFBL2060CMsg bizMsg, NFBL2060SMsg globalMsg, boolean blSearch) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apVndCd", bizMsg.apVndCd.getValue());
        ssmParam.put("prntVndCd", bizMsg.prntVndCd.getValue());
        ssmParam.put("dplyVndNm", bizMsg.dplyVndNm.getValue());
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        ssmParam.put("poNum", bizMsg.poNum.getValue());
        // QC#25902
        ssmParam.put("vndRtrnNum", bizMsg.vndRtrnNum.getValue());
        ssmParam.put("vndRtrnSubmtDt_FR", bizMsg.vndRtrnSubmtDt_FR.getValue());
        ssmParam.put("vndRtrnSubmtDt_TO", bizMsg.vndRtrnSubmtDt_TO.getValue());
        // QC#25902 End
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        ssmParam.put("delyOrdNum", bizMsg.delyOrdNum.getValue());
        ssmParam.put("poDt_FR", bizMsg.poDt_FR.getValue());
        ssmParam.put("poDt_TO", bizMsg.poDt_TO.getValue());
        ssmParam.put("vndPmtTermDescTxt", bizMsg.vndPmtTermDescTxt.getValue());
        ssmParam.put("apVndInvNum", bizMsg.apVndInvNum.getValue());
        ssmParam.put("acctInvStsCd", bizMsg.acctInvStsCd_S.getValue());
        ssmParam.put("invDt_FR", bizMsg.invDt_FR.getValue());
        ssmParam.put("invDt_TO", bizMsg.invDt_TO.getValue());
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        ssmParam.put("acInvTotCostAmt_FR", bizMsg.acInvTotCostAmt_FR.getValue());
        ssmParam.put("acInvTotCostAmt_TO", bizMsg.acInvTotCostAmt_TO.getValue());
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]
        
        // Charge Account From
        ssmParam.put("xxCmntTxt_FR", bizMsg.xxCmntTxt_FR.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxCmntTxt_FR)) {
            String xxCmntTxt_FR = bizMsg.xxCmntTxt_FR.getValue();
            String[] strSplit_FR = xxCmntTxt_FR.split("\\.");
            ssmParam.put("coaCmpyCd_FR", strSplit_FR[0]);
            ssmParam.put("coaBrCd_FR", strSplit_FR[1]);
            ssmParam.put("coaCcCd_FR", strSplit_FR[2]);
            ssmParam.put("coaAcctCd_FR", strSplit_FR[3]);
            ssmParam.put("coaProdCd_FR", strSplit_FR[4]);
            ssmParam.put("coaChCd_FR", strSplit_FR[5]);
            ssmParam.put("coaAfflCd_FR", strSplit_FR[6]);
            ssmParam.put("coaProjCd_FR", strSplit_FR[7]);
            ssmParam.put("coaExtnCd_FR", strSplit_FR[8]);
        } else {
            ssmParam.put("coaCmpyCd_FR", EMPTY_STRING);
            ssmParam.put("coaBrCd_FR", EMPTY_STRING);
            ssmParam.put("coaCcCd_FR", EMPTY_STRING);
            ssmParam.put("coaAcctCd_FR", EMPTY_STRING);
            ssmParam.put("coaProdCd_FR", EMPTY_STRING);
            ssmParam.put("coaChCd_FR", EMPTY_STRING);
            ssmParam.put("coaAfflCd_FR", EMPTY_STRING);
            ssmParam.put("coaProjCd_FR", EMPTY_STRING);
            ssmParam.put("coaExtnCd_FR", EMPTY_STRING);
        }

        // Charge Account To
        ssmParam.put("xxCmntTxt_TO", bizMsg.xxCmntTxt_TO.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxCmntTxt_TO)) {
            String xxCmntTxt_TO = bizMsg.xxCmntTxt_TO.getValue();
            String[] strSplit_TO = xxCmntTxt_TO.split("\\.");
            ssmParam.put("coaCmpyCd_TO", strSplit_TO[0]);
            ssmParam.put("coaBrCd_TO", strSplit_TO[1]);
            ssmParam.put("coaCcCd_TO", strSplit_TO[2]);
            ssmParam.put("coaAcctCd_TO", strSplit_TO[3]);
            ssmParam.put("coaProdCd_TO", strSplit_TO[4]);
            ssmParam.put("coaChCd_TO", strSplit_TO[5]);
            ssmParam.put("coaAfflCd_TO", strSplit_TO[6]);
            ssmParam.put("coaProjCd_TO", strSplit_TO[7]);
            ssmParam.put("coaExtnCd_TO", strSplit_TO[8]);
        } else {
            ssmParam.put("coaCmpyCd_TO", EMPTY_STRING);
            ssmParam.put("coaBrCd_TO", EMPTY_STRING);
            ssmParam.put("coaCcCd_TO", EMPTY_STRING);
            ssmParam.put("coaAcctCd_TO", EMPTY_STRING);
            ssmParam.put("coaProdCd_TO", EMPTY_STRING);
            ssmParam.put("coaChCd_TO", EMPTY_STRING);
            ssmParam.put("coaAfflCd_TO", EMPTY_STRING);
            ssmParam.put("coaProjCd_TO", EMPTY_STRING);
            ssmParam.put("coaExtnCd_TO", EMPTY_STRING);
        }

        ssmParam.put("apInvCatgCd_S", bizMsg.apInvCatgCd_S.getValue());
        ssmParam.put("apPmtChkNum", bizMsg.apPmtChkNum.getValue());
        ssmParam.put("vndPmtMethDescTxt", bizMsg.vndPmtMethDescTxt.getValue());
        ssmParam.put("pmtDt_FR", bizMsg.pmtDt_FR.getValue());
        ssmParam.put("pmtDt_TO", bizMsg.pmtDt_TO.getValue());
        ssmParam.put("xxChkBox_01", bizMsg.xxChkBox_01.getValue()); // Holds check box
        ssmParam.put("xxChkBox_02", bizMsg.xxChkBox_02.getValue()); // PO Variance check box
        ssmParam.put("none", NONE);
        ssmParam.put("rowNum", globalMsg.S.length() + 1);
        // START 2017/06/26 M.Naito [QC#19392,MOD]
        ssmParam.put("variance", AP_LINE_TP.VARIANCE);
        // END 2017/06/26 M.Naito [QC#19392,MOD]
        // START 2017/12/15 S.Katsuma [QC#22457,ADD]
        ssmParam.put("acctInvStsCdCancelled", ACCT_INV_STS.CANCEL);
        // END 2017/12/15 S.Katsuma [QC#22457,ADD]
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        ssmParam.put("invAuthDt_FR", bizMsg.invAuthDt_FR.getValue());
        ssmParam.put("invAuthDt_TO", bizMsg.invAuthDt_TO.getValue());
        ssmParam.put("acctInvStsL", ACCT_INV_STS.LINKED_TO_COST_CALCULATION);
        ssmParam.put("acctInvStsP", ACCT_INV_STS.PAID);
        ssmParam.put("acctInvStsV", ACCT_INV_STS.VOIDED);
        // END 2018/10/18 J.Kim [QC#28816,ADD]

        return getSsmEZDClient().queryEZDMsgArray("getSummaryTabRecord", ssmParam, globalMsg.S);
    }

}
