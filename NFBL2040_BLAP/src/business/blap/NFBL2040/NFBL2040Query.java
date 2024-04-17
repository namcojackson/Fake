/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2040;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL2040.constant.NFBL2040Constant;
import business.db.CM_AP_INV_DTLTMsg;
import business.db.CM_AP_INV_DTLTMsgArray;
import business.db.PO_DTLTMsg;
import business.db.PO_DTLTMsgArray;
import business.parts.NFBCommonBusiness.DefCoaValues;

import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_INV_MATCH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * 2016/07/11   Hitachi         Y.Tsuchimoto    Update          QC#11502
 * 2016/07/27   Hitachi         Y.Tsuchimoto    Update          QC#12037
 * 2016/08/03   Hitachi         K.Kojima        Update          QC#12589
 * 2016/08/08   Hitachi         Y.Tsuchimoto    Update          QC#12648
 * 2016/08/09   Hitachi         Y.Tsuchimoto    Update          QC#12198
 * 2016/08/10   Hitachi         T.Tsuchida      Update          QC#12038
 * 2016/08/10   Hitachi         Y.Tsuchimoto    Update          QC#12948
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#12038
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#13157
 * 2016/09/26   Fujitsu         W.Honda         Update          Unit Test#201
 * 2016/09/28   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/05   Hitachi         T.Tsuchida      Update          QC#14164
 * 2016/10/07   Hitachi         T.Tsuchida      Update          QC#13960
 * 2016/10/11   Hitachi         T.Tsuchida      Update          QC#15087
 * 2016/11/11   Hitachi         K.Kasai         Update          QC#15445
 * 2016/11/15   Hitachi         K.Kasai         Update          QC#15904
 * 2016/12/13   Hitachi         K.Kasai         Update          QC#16139,16476
 * 2017/01/25   Hitachi         E.Kameishi      Update          QC#17090
 * 2017/05/22   CITS            R.Shimamoto     Update          QC#18533
 * 2017/10/20   CITS            T.Tokutomi      Update          QC#21653
 * 2017/10/23   CITS            T.Tokutomi      Update          QC#21662,21754
 * 2017/11/14   CITS            T.Wada          Update          QC#21727
 * 2017/12/19   CITS            T.Tokutomi      Update          QC#14858-Sol#060
 * 2018/01/09   Hitachi         Y.Takeno        Update          QC#22143
 * 2018/01/24   CITS            T.Tokutomi      Update          QC#18602-Sol#102
 * 2018/02/02   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/02/20   Hitachi         Y.Takeno        Update          QC#21703
 * 2018/02/23   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/06   Hitachi         Y.Takeno        Update          QC#23505
 * 2018/03/16   Hitachi         Y.Takeno        Update          QC#24089
 * 2018/03/22   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/04/06   Hitachi         Y.Takeno        Update          QC#25086
 * 2018/04/27   Hitachi         Y.Takeno        Update          QC#25139
 * 2018/05/08   Hitachi         Y.Takeno        Update          QC#25139
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2018/06/26   CITS            S.Katsuma       Update          QC#24617,26566
 * 2018/07/26   CITS            T.Tokutomi      Update          QC#27029
 * 2018/08/09   CITS            T.Tokutomi      Update          QC#27029-1
 * 2018/08/15   Hitachi         Y.Takeno        Update          QC#27029-1
 * 2018/09/04   Hitachi         Y.Takeno        Update          QC#28039
 * 2018/09/26   Hitachi         Y.Takeno        Update          QC#28099
 * 2018/10/03   Hitachi         Y.Takeno        Update          QC#28099-1
 * 2018/10/10   Fujitsu         S.Ohki          Update          QC#28521
 * 2018/11/20   Hitachi         Y.Takeno        Update          QC#28660
 * 2019/03/19   Hitachi         Y.Takeno        Update          QC#30829
 * 2019/04/08   Hitachi         Y.Takeno        Update          QC#31071
 * 2020/02/17   Fujitsu         H.ikeda         Update          QC#53413
 * 2020/04/22   CITS            R.Azucena       Update          QC#56829
 * 2021/09/28   CITS            G.Delgado       Update          QC#58775
 * 2022/02/08   Hitachi         R.Onozuka       Update          QC#59613
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * 2023/01/17   Hitachi         S.Nakatani      Update          QC#60812
 * </pre>
 */
public final class NFBL2040Query extends S21SsmEZDQuerySupport implements NFBL2040Constant {
    /**
     * Singleton instance.
     */
    private static final NFBL2040Query INSTANCE = new NFBL2040Query();
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
    private NFBL2040Query() {
        super();
    }
    /**
     * Singleton instance getter.
     * @return NFBL2040Query
     */
    public static NFBL2040Query getInstance() {
        return INSTANCE;
    }

    /**
     * NFBL2040Query.xml id="getApInvCatgPulldownValue"
     * 
     * <pre>
     * Create Pulldown list of AP_INV_CATG_CD and AP_INV_CATG_DESC_TXT from AP_INV_CATG table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCmApInvTpPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getCmApInvTpPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getCmInvMatchPulldownValue"
     * 
     * <pre>
     * Create Pulldown list of CM_INV_MATCH_CD and CM_INV_MATCH_DESC_TXT from CM_INV_MATCH table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCmInvMatchPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getCmInvMatchPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getHoldRecord"
     * @param bizMsg NFBL2040CMsg
     * @param globalMsg NFBL2040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHoldRecord(NFBL2040CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apVndCd", bizMsg.apVndCd.getValue());
        ssmParam.put("apVndInvNum", bizMsg.apVndInvNum.getValue());
        // START 2018/02/27 [QC#23505, MOD]
        // ssmParam.put("apVndInvSqNum", AP_VND_INV_SQ_NUM_00);
        ssmParam.put("apVndInvSqNum", bizMsg.apVndInvSqNum);
        // END   2018/02/27 [QC#23505, MOD]
        // START 2018/03/28 [QC#24277, ADD]
        ssmParam.put("apVndInvLineNum0000", AP_VND_INV_LINE_NUM_0000);
        ssmParam.put("emptyApVndInvLineNum", EMPTY_AP_VND_INV_LINE_NUM);
        // END   2018/03/28 [QC#24277, ADD]

        return getSsmEZDClient().queryObjectList("getHoldRecord", ssmParam, -1, -1);
    }
    
    /**
     * NFBL2040Query.xml id="getApLineTpPulldownValue"
     * 
     * <pre>
     * Create Pulldown list of AP_LINE_TP_CD and AP_LINE_TP_DESC_TXT from AP_LINE_TP table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApLineTpPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("variance", AP_LINE_TP.VARIANCE);
        return getSsmEZDClient().queryObjectList("getApLineTpPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getApLineTpPulldownValue"
     * 
     * <pre>
     * Create Pulldown list of AP_LINE_TP_CD and AP_LINE_TP_DESC_TXT from AP_LINE_TP table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApLineTpPulldownValueForPoMatch(String notDispApLineTp) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("variance", AP_LINE_TP.VARIANCE);
        queryParam.put("notDispApLineTp", notDispApLineTp);
        return getSsmEZDClient().queryObjectList("getApLineTpPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getPmtHldRelRsnPulldownValue"
     * 
     * <pre>
     * Create Pulldown list of PMT_HLD_REL_RSN_CD and AP_INV_CATG_DESC_TXT from AP_INV_CATG table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPmtHldRelRsnPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getPmtHldRelRsnPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getInvRecordFromOtherScreen"
     * @param bizMsg NFBL2040CMsg
     * @param globalMsg NFBL2040SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvRecordFromOtherScreen(NFBL2040CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apVndCd", bizMsg.apVndCd_OT.getValue());
        ssmParam.put("apVndInvNum", bizMsg.apVndInvNum_OT.getValue());
        ssmParam.put("apVndInvSqNum", bizMsg.apVndInvSqNum_OT.getValue());
        ssmParam.put("apLineTpItem", AP_LINE_TP.ITEM);
        ssmParam.put("none", NONE);
        ssmParam.put("poMdseCmpsnTpChild", PO_MDSE_CMPSN_TP.CHILD);
        ssmParam.put("apInvTpCd00", AP_INV_TP_CD_00);
        ssmParam.put("apInvTpCd02", AP_INV_TP_CD_02);
        // START 2018/02/20 [QC#21703, MOD]
        String apInvCatgCd = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_AP_INV_CATG_CD, GLBL_CMPY_CD);
        ssmParam.put("apInvCatgCd", apInvCatgCd);
        // END   2018/02/20 [QC#21703, MOD]
        // START 2018/02/28 [QC#23505, MOD]
        String isCorrected = ZYPConstant.FLG_OFF_N;
        if (!AP_VND_INV_SQ_NUM_00.equals(bizMsg.apVndInvSqNum.getValue())) {
            isCorrected = ZYPConstant.FLG_ON_Y;
        }
        ssmParam.put("isCorrected", isCorrected);
        // END   2018/02/28 [QC#23505, MOD]
        // START 2018/04/06 [QC#25086, ADD]
        // START QC#25902,QC#25190,QC#25141
        ssmParam.put("cmApInvTpCd01", CM_AP_INV_TP.PO_MATCH);
        // END QC#25902,QC#25190,QC#25141
        ssmParam.put("cmApInvTpCd02", CM_AP_INV_TP.CREDIT_MEMO);
        // END   2018/04/06 [QC#25086, ADD]

        return getSsmEZDClient().queryObjectList("getInvRecordFromOtherScreen", ssmParam, -1, -1);
    }

    /**
     * MOD QC#25902,QC#25190,QC#25141
     * NFBL2040Query.xml id="getMdseCatgRecord"
     * @param bizMsg NFBL2040CMsg
     * @param apInvCatgCd String
     * @param defCoa DefCoaValues
     * @param isLine boolean
     * @param searchFlg boolean
     * @return S21SsmEZDResult
     */
    // START 2020/02/17 [QC#53413, MOD]
    //public S21SsmEZDResult getMdseCatgRecord(NFBL2040CMsg bizMsg, String apInvCatgCd, DefCoaValues defCoa, boolean isLine) {
    public S21SsmEZDResult getMdseCatgRecord(NFBL2040CMsg bizMsg, String apInvCatgCd, DefCoaValues defCoa, boolean isLine, boolean searchFlg) {
    // END   2020/02/17 [QC#53413, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        // START 2020/02/17 [QC#53413, MOD]
        if (searchFlg) {
            ssmParam.put("poNum", bizMsg.poNum.getValue());
            ssmParam.put("delyOrdNum", bizMsg.delyOrdNum_H.getValue());
            ssmParam.put("rwsNum", bizMsg.rwsNum_H.getValue());
        } else {
            ssmParam.put("poNum", bizMsg.poNum_L.getValue());
            ssmParam.put("delyOrdNum", bizMsg.delyOrdNum_L.getValue());
            ssmParam.put("rwsNum", bizMsg.rwsNum_L.getValue());
        }
        // END   2020/02/17 [QC#53413, MOD]
        ssmParam.put("apLineTpCd", AP_LINE_TP_CD_01);
        ssmParam.put("poAcctTpCd", PO_ACCT_TP.CHARGE);
        ssmParam.put("trxCd", TRX.PURCHASE_STOCK_IN);
        ssmParam.put("trxRsnCd", TRX_RSN.PURCHASE_STOCK_IN);
        ssmParam.put("apInvCatgCd", apInvCatgCd);
        ssmParam.put("poMatch", CM_INV_MATCH.PO_MATCH);
        ssmParam.put("receiptMatch", CM_INV_MATCH.RECEIPT_MATCH);
        ssmParam.put("poLineTpItt", PO_LINE_TP.ITT);
        ssmParam.put("poLineTpExp", PO_LINE_TP.EXPENSE);
        // QC#14858-Sol#060
        ssmParam.put("poLineTpExpWR", PO_LINE_TP.EXPENSE_WITH_RECEIPT);
        ssmParam.put("poLineTpAsset", PO_LINE_TP.ASSET);
        ssmParam.put("coaProjAcctTpAccrual", COA_PROJ_ACCT_TP.ACCRUAL);
        ssmParam.put("defCoaCmpyCd", defCoa.getDefCoaCmpy());
        ssmParam.put("defCoaBrCd", defCoa.getDefCoaBr());
        ssmParam.put("defCoaCcCd", defCoa.getDefCoaCc());
        ssmParam.put("defCoaAcctCd", defCoa.getDefCoaAcct());
        ssmParam.put("defCoaProdCd", defCoa.getDefCoaProd());
        ssmParam.put("defCoaChCd", defCoa.getDefCoaCh());
        ssmParam.put("defCoaAfflCd", defCoa.getDefCoaAffl());
        ssmParam.put("defCoaProjCd", defCoa.getDefCoaProj());
        ssmParam.put("defCoaExtnCd", defCoa.getDefCoaExtn());
        ssmParam.put("assetTpCdEmsd", ASSET_TP.EMSD_ASSET);
        ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(GLBL_CMPY_CD));
        ssmParam.put("poMdseCmpsnTpChild", PO_MDSE_CMPSN_TP.CHILD);
        // START 2018/03/16 [QC#24089, DEL]
        // QC#21754 Add parameter.
        // ssmParam.put("poLineStsClose", PO_LINE_STS.CLOSED);
        // END   2018/03/16 [QC#24089, DEL]
        // START 2022/02/08 [QC#59613, ADD]
        ssmParam.put("poLineStsCancelled", PO_LINE_STS.CANCELLED);
        // END 2022/02/08 [QC#59613, ADD]
        // START QC#25902,QC#25190,QC#25141
        if (isLine) {
            List<String> poOrdDtlLines = new ArrayList<String>();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
                    poOrdDtlLines.add(bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                }
            }
            ssmParam.put("poOrdDtlLines", poOrdDtlLines);
        }
        // END QC#25902,QC#25190,QC#25141
        // QC#27029-1 Update.
        if(CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue())){
            ssmParam.put("isInvoicedPo", ZYPConstant.FLG_ON_Y);
        }

        // START 2018/03/28 [QC#28099, ADD]
        ssmParam.put("splyPmtFlgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("splyPmtFlgN", ZYPConstant.FLG_OFF_N);
        // END   2018/03/28 [QC#28099, ADD]

        return getSsmEZDClient().queryObjectList("getMdseCatgRecord", ssmParam, -1, -1);
    }

    /**
     * NFBL2040Query.xml id="getMdseReturnCatgRecord"
     * @param bizMsg NFBL2040CMsg
     * @param apInvCatgCd String
     * @param defCoa DefCoaValues
     * @param searchFlg boolean
     * @return S21SsmEZDResult
     */
    // mod start 2022/02/15 QC#57090
    //public S21SsmEZDResult getMdseReturnCatgRecord(NFBL2040CMsg bizMsg, String apInvCatgCd, DefCoaValues defCoa) {
    public S21SsmEZDResult getMdseReturnCatgRecord(NFBL2040CMsg bizMsg, String apInvCatgCd, DefCoaValues defCoa, boolean searchFlg) {
    // mod end 2022/02/15 QC#57090

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        // mod start 2022/02/15 QC#57090
        //ssmParam.put("poNum", bizMsg.poNum.getValue());
        //ssmParam.put("delyOrdNum", bizMsg.delyOrdNum_H.getValue());
        if (searchFlg) {
            ssmParam.put("poNum", bizMsg.poNum.getValue());
            ssmParam.put("delyOrdNum", bizMsg.delyOrdNum_H.getValue());
            ssmParam.put("vndRtrnNum", bizMsg.vndRtrnNum.getValue());
        } else {
            ssmParam.put("poNum", bizMsg.poNum_L.getValue());
            ssmParam.put("delyOrdNum", bizMsg.delyOrdNum_L.getValue());
            ssmParam.put("vndRtrnNum", bizMsg.vndRtrnNum_L.getValue());
        }
        // mod end 2022/02/15 QC#57090
        ssmParam.put("apLineTpCd", AP_LINE_TP_CD_01);
        ssmParam.put("poAcctTpCd", PO_ACCT_TP.ACCRUAL);
        ssmParam.put("trxCd", TRX.PURCHASE_STOCK_IN);
        ssmParam.put("trxRsnCd", TRX_RSN.DOMESTIC_VENDOR_RETURN);
        ssmParam.put("apInvCatgCd", apInvCatgCd);
        ssmParam.put("poMatch", CM_INV_MATCH.PO_MATCH);
        ssmParam.put("receiptMatch", CM_INV_MATCH.RECEIPT_MATCH);
        ssmParam.put("defCoaCmpyCd", defCoa.getDefCoaCmpy());
        ssmParam.put("defCoaBrCd", defCoa.getDefCoaBr());
        ssmParam.put("defCoaCcCd", defCoa.getDefCoaCc());
        ssmParam.put("defCoaAcctCd", defCoa.getDefCoaAcct());
        ssmParam.put("defCoaProdCd", defCoa.getDefCoaProd());
        ssmParam.put("defCoaChCd", defCoa.getDefCoaCh());
        ssmParam.put("defCoaAfflCd", defCoa.getDefCoaAffl());
        ssmParam.put("defCoaProjCd", defCoa.getDefCoaProj());
        ssmParam.put("defCoaExtnCd", defCoa.getDefCoaExtn());
        // START 2018/02/02 [QC#21703, MOD]
        String cmVndPmtTermCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_NFBL2040_CM_VND_PMT_TERM_CD, GLBL_CMPY_CD);
        ssmParam.put("cmVndPmtTermCd", cmVndPmtTermCd);
        // START QC#25902,QC#25190,QC#25141
        // del start 2022/02/15 QC#57090
        //ssmParam.put("vndRtrnNum", bizMsg.vndRtrnNum.getValue());
        // del end 2022/02/15 QC#57090
        // END QC#25902,QC#25190,QC#25141
        // END   2018/02/02 [QC#21703, MOD]
        // START 2018/06/26 S.Katsuma [QC#26566,ADD]
        ssmParam.put("cmDefAcctCdRtrn", CM_DEF_ACCT.RETURN_COA);
        // END 2018/06/26 S.Katsuma [QC#26566,ADD]
        // START 2019/04/08 [QC#31071, ADD]
        ssmParam.put("splyPmtFlgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("splyPmtFlgN", ZYPConstant.FLG_OFF_N);
        // END   2019/04/08 [QC#31071, ADD]

        return getSsmEZDClient().queryObjectList("getMdseReturnCatgRecord", ssmParam, -1, -1);
    }

    /**
     * MOD QC#25902,QC#25190,QC#25141
     * NFBL2040Query.xml id="getPoSts"
     * @param bizMsg NFBL2040CMsg
     * @param isLine boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoSts(NFBL2040CMsg bizMsg, boolean isLine) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poNum", bizMsg.poNum.getValue());
        // START QC#25902,QC#25190,QC#25141
        if (isLine) {
            List<String> poOrdDtlLines = new ArrayList<String>();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
                    poOrdDtlLines.add(bizMsg.A.no(i).poOrdDtlLineNum_A1.getValue());
                }
            }
            ssmParam.put("poOrdDtlLines", poOrdDtlLines);
        }
        // END QC#25902,QC#25190,QC#25141

        return getSsmEZDClient().queryObjectList("getPoSts", ssmParam, -1, -1);
    }

    // START 2020/02/17 [QC#53413, ADD]
    /**
     * NFBL2040Query.xml id="getPoSts"
     * @param bizMsg NFBL2040CMsg
     * @param isLine boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoSts(String poNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poNum", poNum);

        return getSsmEZDClient().queryObjectList("getPoSts", ssmParam, -1, -1);
    }
    // END   2020/02/17 [QC#53413, ADD]

    /**
     * NFBL2040Query.xml id="getPmtHldDescTxtByPmtHldCd"
     * 
     * <pre>
     * Get PMT_HLD_NM from PMT_HLD table by PMT_HLD_CD
     * </pre>
     * 
     * @param pmtHldCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPmtHldDescTxtByPmtHldCd(String pmtHldCd) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("pmtHldCd", pmtHldCd);

        return getSsmEZDClient().queryObjectList("getPmtHldDescTxtByPmtHldCd", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getPmtHldRsnDescTxtByPmtHldRsnCd"
     * 
     * <pre>
     * Get PMT_HLD_RSN_DESC_TXT from PMT_HLD table by PMT_HLD_RSN_CD
     * </pre>
     * 
     * @param pmtHldCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPmtHldRsnDescTxtByPmtHldRsnCd(String pmtHldRsnCd) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("pmtHldRsnCd", pmtHldRsnCd);

        return getSsmEZDClient().queryObjectList("getPmtHldRsnDescTxtByPmtHldRsnCd", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getCmStkInPkListByPartialCondition"
     * 
     * <pre>
     * Get CM_STK_IN_PK list by partial condition
     * </pre>
     * 
     * @param poOrdNum String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCmStkInPkListByPartialCondition(String poOrdNum, String mdseCd) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("poOrdNum", poOrdNum);
        queryParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getCmStkInPkListByPartialCondition", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getApInvCatgCdFromDescTxt"
     * 
     * <pre>
     * Get AP_INV_CATG_CD from AP_INV_CATG_DESC_TXT
     * </pre>
     * 
     * @param apInvCatgDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getApInvCatgCdFromDescTxt(String apInvCatgDescTxt) {

        Map<String, String> queryParam = new HashMap<String, String>();

        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("apInvCatgDescTxt", apInvCatgDescTxt);

        return getSsmEZDClient().queryObjectList("getApInvCatgCdFromDescTxt", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getListCmApInvDtlByPartialKey"
     * 
     * <pre>
     * Get CM_AP_INV_DTL list by partial keys.
     * </pre>
     * 
     * @param poOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getListCmApInvDtlByPartialKey(String poOrdNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("poOrdNum", poOrdNum);
        queryParam.put("poLineStsCdIsOpen", PO_LINE_STS.OPEN);
        queryParam.put("poStsCdIsOpenList", new String[] {PO_STS.VALIDATED, PO_STS.PO_CONFIRMED});
        queryParam.put("poLinsStsCdIsOpenForXXList", new String[] {PO_LINE_STS.OPEN_FOR_RECEIPT, PO_LINE_STS.OPEN_FOR_INVOICE });

        return getSsmEZDClient().queryObjectList("getListCmApInvDtlByPartialKey", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getCreditLineAccountCode"
     * @param apVndCd String 
     * @param apVndInvNum String 
     * @param apVndInvSqNum String 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCreditLineAccountCode(String apVndCd, String apVndInvNum, String apVndInvSqNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apVndCd", apVndCd);
        ssmParam.put("apVndInvNum", apVndInvNum);
        ssmParam.put("apVndInvSqNum", apVndInvSqNum);

        return getSsmEZDClient().queryObjectList("getCreditLineAccountCode", ssmParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getVndSplyCoaData"
     * @param apVndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVndSplyCoaData(String apVndCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apVndCd", apVndCd);

        return getSsmEZDClient().queryObjectList("getVndSplyCoaData", ssmParam, -1, -1);
    }

    /**
     * NFBL2040Query.xml id="getVendorInfo"
     * @param apVndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVendorInfo(String apVndCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apVndCd", apVndCd);
        ssmParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(GLBL_CMPY_CD));
        ssmParam.put("supplier", VND_TP.SUPPLIER);
        return getSsmEZDClient().queryObjectList("getVendorInfo", ssmParam, -1, -1);
    }

    // START 2018/03/28 [QC#24277, MOD]
    /**
     * NFBL2040Query.xml id="getSumPoQty"
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSumPoQty(String poOrdNum, String poOrdDtlLineNum, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getSumPoQty", ssmParam, -1, -1);
    }
    // END   2018/03/28 [QC#24277, MOD]

    // START 2018/03/28 [QC#24277, MOD]
    /**
     * NFBL2040Query.xml id="getSumBilledQty"
     * @param bizMsg NFBL2040CMsg
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @param mdseCd String
     * @param apVndInvNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSumBilledQty(NFBL2040CMsg bizMsg, String poOrdNum, String poOrdDtlLineNum, String mdseCd, String apVndInvNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        ssmParam.put("mdseCd", mdseCd);
        // START 2018/01/09 [QC#22143, ADD]
        ssmParam.put("cancel", ACCT_INV_STS.CANCEL);
        ssmParam.put("apVndInvNum", apVndInvNum);
        // END   2018/01/09 [QC#22143, ADD]
        // START 2018/05/08 [QC#25139, ADD]
        // START 2018/11/20 [QC#28660, MOD]
        // ssmParam.put("entered", ACCT_INV_STS.ENTERED);
        if (!(CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) || !ZYPCommonFunc.hasValue(bizMsg.poNum))) {
            ssmParam.put("entered", ACCT_INV_STS.ENTERED);
        }
        // END   2018/11/20 [QC#28660, MOD]
        // END   2018/05/08 [QC#25139, ADD]
        return getSsmEZDClient().queryObjectList("getSumBilledQty", ssmParam, -1, -1);
    }
    // END   2018/03/28 [QC#24277, MOD]

    /**
     * NFBL2040Query.xml id="getSumRcvQty"
     * QC#21653 Add method.
     * QC#14858-Sol#060 Update.
     * @param poOrdNum String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSumRcvQty(String poOrdNum, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("poLineTpItt", PO_LINE_TP.ITT);
        ssmParam.put("poLineTpExpWR", PO_LINE_TP.EXPENSE_WITH_RECEIPT);

        return getSsmEZDClient().queryObjectList("getSumRcvQty", ssmParam, -1, -1);
    }

    /**
     * NFBL2040Query.xml id="getSumRcvQtyByPoOrdDtlLineNum"
     * QC#21653 Add method.
     * QC#14858-Sol#060 Update.
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSumRcvQtyByPoOrdDtlLineNum(String poOrdNum, String poOrdDtlLineNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        ssmParam.put("poLineTpItt", PO_LINE_TP.ITT);
        ssmParam.put("poLineTpExpWR", PO_LINE_TP.EXPENSE_WITH_RECEIPT);

        return getSsmEZDClient().queryObjectList("getSumRcvQtyByPoOrdDtlLineNum", ssmParam, -1, -1);
    }

    /**
     * NFBL2040Query.xml id="getPoInfo"
     * @param poOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoInfo(String poOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("closed", PO_HDR_STS.CLOSED);
        // START 2018/03/02 [QC#23505, ADD]
        ssmParam.put("excludeClosedOrder", ZYPConstant.FLG_ON_Y);
        // END   2018/03/02 [QC#23505, ADD]
        return getSsmEZDClient().queryObjectList("getPoInfo", ssmParam, -1, -1);
    }

    /**
     * NFBL2040Query.xml id="getPmtHldPulldownValue"
     * 
     * <pre>
     * Create Pulldown list of PMT_HLD and PMT_HLD_DESC_TXT from PMT_HLD table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPmtHldPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getPmtHldPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getPmtHldRsnPulldownValue"
     * 
     * <pre>
     * Create Pulldown list of PMT_HLD_RSN and PMT_HLD_RSN_DESC_TXT from PMT_HLD_RSN table.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPmtHldRsnPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getPmtHldRsnPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL2040Query.xml id="getPoVendorInfo"
     * @param poOrdNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoVendorInfo(String poOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        return getSsmEZDClient().queryObjectList("getPoVendorInfo", ssmParam, -1, -1);
    }

    /**
     * NFBL2040Query.xml id="getVndPmt"
     * @param bizMsg NFBL2040CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVndPmt(NFBL2040CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("apVndCd", bizMsg.apVndCd.getValue());
        ssmParam.put("invDt", bizMsg.invDt.getValue());

        return getSsmEZDClient().queryObject("getVndPmt", ssmParam);
    }

    /**
     * NFBL2040Query.xml id="getPmtTermDt"
     * @param bizMsg NFBL2040CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPmtTermDt(NFBL2040CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("vndPmtTermDescTxt", bizMsg.vndPmtTermDescTxt.getValue());
        ssmParam.put("invDt", bizMsg.invDt.getValue());

        return getSsmEZDClient().queryObject("getPmtTermDt", ssmParam);
    }

    /**
     * NFBL2040Query.xml id="getVndPmt"
     * @param bizMsg NFBL2040CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAttDataForTherefore(NFBL2040CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        StringBuilder attDataGrpTxt = new StringBuilder();
        attDataGrpTxt.append(getGlobalCompanyCode());
        attDataGrpTxt.append(PARAMS_AP_VND_CD_KEY);
        attDataGrpTxt.append(bizMsg.apVndCd.getValue());
        attDataGrpTxt.append(PARAMS_AP_VND_INV_NUM_KEY);
        attDataGrpTxt.append(bizMsg.apVndInvNum.getValue());
        ssmParam.put("attDataGrpTxt", attDataGrpTxt.toString());
        String thereforeCatg = ZYPCodeDataUtil.getVarCharConstValue(CONST_NM_NFBL2040_THEREFORE_CATG_LIST, getGlobalCompanyCode());
        String[] thereforeCatgArray = null;
        if (thereforeCatg != null
                && thereforeCatg.contains(",")) {
            thereforeCatgArray = thereforeCatg.split(",");
        } else {
            thereforeCatgArray = new String[1];
            thereforeCatgArray[0] = thereforeCatg;
        }
        ssmParam.put("thereforeCatgList", thereforeCatgArray);

        return getSsmEZDClient().queryObjectList("getAttDataForTherefore", ssmParam);
    }

    /**
     * Find Cost Management Accounts Payable Invoice Detail
     * @param glblCmpyCd String
     * @param apVndCd String
     * @param apVndInvNum String
     * @param apVndInvSqNum String
     * @return CM_AP_INV_DTLTMsgArray
     */
    public CM_AP_INV_DTLTMsgArray findCmApInvDtlList(String glblCmpyCd, String apVndCd, String apVndInvNum, String apVndInvSqNum) {
        CM_AP_INV_DTLTMsg inMsg = new CM_AP_INV_DTLTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("apVndCd01", apVndCd);
        inMsg.setConditionValue("apVndInvNum01", apVndInvNum);
        inMsg.setConditionValue("apVndInvSqNum01", apVndInvSqNum);
        return (CM_AP_INV_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * NFBL2040Query.xml id="getCoaCdForVar"
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaCdForVar(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("coaProjAcctTpCd", COA_PROJ_ACCT_TP.IPV);
        ssmParam.put("cmDefAcctCdVar", CM_DEF_ACCT.INV_RATE_VARIANCE_COA);
        return getSsmEZDClient().queryObjectList("getCoaCdForVar", ssmParam);
    }

    /**
     * getPoDtlListForUpdateNoWait
     * QC#21662 Add method.
     * @param glblCmpyCd
     * @param poOrdNum
     * @return PO_DTLTMsgArray
     */
    public PO_DTLTMsgArray getPoDtlListForUpdateNoWait(String glblCmpyCd, String poOrdNum){
        PO_DTLTMsg poDtl = new PO_DTLTMsg();
        poDtl.setSQLID("001");
        poDtl.setConditionValue("glblCmpyCd01", glblCmpyCd);
        poDtl.setConditionValue("poOrdNum01", poOrdNum);
        return (PO_DTLTMsgArray)EZDTBLAccessor.findByCondition(poDtl);
    }

    /**
     * getChargeAcct(QC#21727)
     * @param glblCmpyCd String
     * @param vndCd String
     * @return Charge Account Info S21SsmEZDResult
     */
    public S21SsmEZDResult getChargeAcct(String vndCd, String appFuncId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", GLBL_CMPY_CD);
        params.put("appFuncId", appFuncId);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getChargeAcct", params);
    }
    /**
     * getSumBilledQtyExcluveCanInv (QC#21666)
     * @param bizMsg
     * @param poOrdNum
     * @param mdseCd
     * @return
     */
    public S21SsmEZDResult getSumBilledQtyExcluveCanInv(NFBL2040CMsg bizMsg, String poOrdNum, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("cancel", ACCT_INV_STS.CANCEL);
        // START 2018/05/08 [QC#25139, ADD]
        // START 2018/11/20 [QC#28660, MOD]
        // ssmParam.put("entered", ACCT_INV_STS.ENTERED);
        if (!(CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) || !ZYPCommonFunc.hasValue(bizMsg.poNum))) {
            ssmParam.put("entered", ACCT_INV_STS.ENTERED);
        }
        // END   2018/11/20 [QC#28660, MOD]
        // END   2018/05/08 [QC#25139, ADD]
        return getSsmEZDClient().queryObjectList("getSumBilledQtyExcluveCanInv", ssmParam, -1, -1);
    }

    /**
     * getTextItem
     * QC#14858-Sol#06 Add method.
     * @param mdseCd
     * @return
     */
    public S21SsmEZDResult getTextItem(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("tpTextItem", MDSE_ITEM_TP.TEXT_ITEM);
        return getSsmEZDClient().queryObjectList("getTextItem", ssmParam, -1, -1);
    }
    /**
    * getMdseDescTxtFromPO
    * QC#14858-Sol#06 Add method.
    * @param poOrdNum String
    * @param mdseCd String
    * @return 
    */
   public S21SsmEZDResult getMdseDescTxtFromPO(String poOrdNum, String mdseCd) {
        // logical unique key.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getMdseDescTxtFromPO", ssmParam);
    }
   /**
    * getDsPoTpCd
    * QC#18602-Sol#102 Add method.
    * @param poOrdNum String
    * @return 
    */
   public S21SsmEZDResult getDsPoTpCd(String poOrdNum) {
        // logical unique key.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);

        return getSsmEZDClient().queryObject("getDsPoTpCd", ssmParam);
    }
   /**
    * getInvoicePoTotalAmt
    * QC#18602-Sol#102 Add method.
    * @param poOrdNum String
    * @return 
    */
   public S21SsmEZDResult getInvoicePoTotalAmt(String poOrdNum, String apVndInvNum, String mdseCd) {
        // logical unique key.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("apVndInvNum", apVndInvNum);
        ssmParam.put("Canceled", ACCT_INV_STS.CANCEL);
        ssmParam.put("Voided", ACCT_INV_STS.VOIDED);
        ssmParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getInvoicePoTotalAmt", ssmParam);
    }
    /**
    * getInvoicePoTotalAmt
    * QC#18602-Sol#102 Add method.
    * @param poOrdNum String
    * @return 
    */
   public S21SsmEZDResult getTotalPoAmtOfMdse(String poOrdNum, String mdseCd) {
        // logical unique key.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getTotalPoAmtOfMdse", ssmParam);
    }
   
    // START 2018/01/29 [QC#18602-Sol#102,ADD]
   /**
    * getInvoicePoTotalAmtByMdse
    * QC#18602-Sol#102 Add method.
    * @param poOrdNum String
    * @param mdseCd String
    * @return 
    */
   public BigDecimal getInvoicePoTotalAmtByMdse(String poOrdNum,  String mdseCd) {
        // logical unique key.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("mdseCd", mdseCd);        
        ssmParam.put("Canceled", ACCT_INV_STS.CANCEL);
        ssmParam.put("Voided", ACCT_INV_STS.VOIDED);

        return (BigDecimal) getSsmEZDClient().queryObject("getInvoicePoTotalAmtByMdse", ssmParam).getResultObject();
    }
   
   /**
    * getPoLineAmtByMdse
    * QC#18602-Sol#102 Add method.
    * @param poOrdNum String
    * @param mdseCd String
    * @return 
    */
   public BigDecimal getPoLineAmtByMdse(String poOrdNum, String mdseCd) {
        // logical unique key.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("mdseCd", mdseCd);

        return (BigDecimal) getSsmEZDClient().queryObject("getPoLineAmtByMdse", ssmParam).getResultObject();
    }
    // END 2018/01/29 [QC#18602-Sol#102,ADD]
   
   // START 2018/03/02 [QC#23505, ADD]
   /**
    * NFBL2040Query.xml id="getPoInfo"
    * @param poOrdNum String
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult getPoInfoIncludeClosed(String poOrdNum, String prntVndCd, String vndCd) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("poOrdNum", poOrdNum);
       ssmParam.put("closed", PO_HDR_STS.CLOSED);
       ssmParam.put("excludeClosedOrder", ZYPConstant.FLG_OFF_N);
       ssmParam.put("prntVndCd", prntVndCd);
       ssmParam.put("vndCd", vndCd);
       return getSsmEZDClient().queryObjectList("getPoInfo", ssmParam, -1, -1);
   }

   /**
    * getSumBilledQtyExcluveCanInv
    * @param bizMsg NFBL2040CMsg
    * @param poOrdNum String
    * @param poOrdDtlLineNum String
    * @param mdseCd String
    * @return
    */
   public S21SsmEZDResult getSumBilledQtyExcluveCanInv(NFBL2040CMsg bizMsg, String poOrdNum, String poOrdDtlLineNum, String mdseCd) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("poOrdNum", poOrdNum);
       ssmParam.put("mdseCd", mdseCd);
       ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
       ssmParam.put("cancel", ACCT_INV_STS.CANCEL);
       // START 2018/04/27 [QC#25139, ADD]
       // START 2018/11/20 [QC#28660, MOD]
       // ssmParam.put("entered", ACCT_INV_STS.ENTERED);
       if (!(CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) || !ZYPCommonFunc.hasValue(bizMsg.poNum))) {
           ssmParam.put("entered", ACCT_INV_STS.ENTERED);
       }
       // END   2018/11/20 [QC#28660, MOD]
       // END   2018/04/27 [QC#25139, ADD]
       return getSsmEZDClient().queryObjectList("getSumBilledQtyExcluveCanInv", ssmParam, -1, -1);
   }

   // END   2018/03/02 [QC#23505, ADD]

   /**
    * ADD QC#25902,QC#25190,QC#25141
    * NFBL2040Query.xml id="getMdseCatgRecord"
    * @param bizMsg NFBL2040CMsg
    * @param apInvCatgCd String
    * @param defCoa DefCoaValues
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult getMdseCatgRecordCorrection(NFBL2040CMsg bizMsg, String apInvCatgCd, DefCoaValues defCoa) {

       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("poNum", bizMsg.poNum_HT.getValue());
       ssmParam.put("apLineTpCd", AP_LINE_TP_CD_01);
       ssmParam.put("poAcctTpCd", PO_ACCT_TP.CHARGE);
       ssmParam.put("trxCd", TRX.PURCHASE_STOCK_IN);
       ssmParam.put("trxRsnCd", TRX_RSN.PURCHASE_STOCK_IN);
       ssmParam.put("apInvCatgCd", apInvCatgCd);
       ssmParam.put("poMatch", CM_INV_MATCH.PO_MATCH);
       ssmParam.put("receiptMatch", CM_INV_MATCH.RECEIPT_MATCH);
       ssmParam.put("poLineTpItt", PO_LINE_TP.ITT);
       ssmParam.put("poLineTpExp", PO_LINE_TP.EXPENSE);
       // QC#14858-Sol#060
       ssmParam.put("poLineTpExpWR", PO_LINE_TP.EXPENSE_WITH_RECEIPT);
       ssmParam.put("poLineTpAsset", PO_LINE_TP.ASSET);
       ssmParam.put("coaProjAcctTpAccrual", COA_PROJ_ACCT_TP.ACCRUAL);
       ssmParam.put("defCoaCmpyCd", defCoa.getDefCoaCmpy());
       ssmParam.put("defCoaBrCd", defCoa.getDefCoaBr());
       ssmParam.put("defCoaCcCd", defCoa.getDefCoaCc());
       ssmParam.put("defCoaAcctCd", defCoa.getDefCoaAcct());
       ssmParam.put("defCoaProdCd", defCoa.getDefCoaProd());
       ssmParam.put("defCoaChCd", defCoa.getDefCoaCh());
       ssmParam.put("defCoaAfflCd", defCoa.getDefCoaAffl());
       ssmParam.put("defCoaProjCd", defCoa.getDefCoaProj());
       ssmParam.put("defCoaExtnCd", defCoa.getDefCoaExtn());
       ssmParam.put("assetTpCdEmsd", ASSET_TP.EMSD_ASSET);
       ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(GLBL_CMPY_CD));
       ssmParam.put("poMdseCmpsnTpChild", PO_MDSE_CMPSN_TP.CHILD);
       // START 2018/03/16 [QC#24089, DEL]
       // QC#21754 Add parameter.
       // ssmParam.put("poLineStsClose", PO_LINE_STS.CLOSED);
       // END   2018/03/16 [QC#24089, DEL]
       return getSsmEZDClient().queryObjectList("getMdseCatgRecord", ssmParam, -1, -1);
   }

   // START 2018/06/26 S.Katsuma [QC#24617,ADD]
   /**
    * getRtlWhNmByPo
    * @param poOrdNum String
    * @param poOrdDtlLineNum String
    * @return 
    */
   public String getRtlWhNmByPo(String poOrdNum, String poOrdDtlLineNum) {
        // logical unique key.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);

        return (String) getSsmEZDClient().queryObject("getRtlWhNmByPo", ssmParam).getResultObject();
   }
   // END 2018/06/26 S.Katsuma [QC#24617,ADD]
   
   /**
    * getInvoicedQtyOfVendorReturn
    * QC#27029 add method.
    * @param vndRtnNum String
    * @param mdseCd String
    * @return 
    */
   public BigDecimal getInvoicedQtyOfVendorReturn(String vndRtnNum, String mdseCd) {
        // logical unique key.
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("vndRtnNum", vndRtnNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("Canceled", ACCT_INV_STS.CANCEL);
        ssmParam.put("Voided", ACCT_INV_STS.VOIDED);

        return (BigDecimal) getSsmEZDClient().queryObject("getInvoicedQtyOfVendorReturn", ssmParam).getResultObject();
    }

   // START 2018/08/15 [QC#27029-1, ADD]
   /**
    * getSumBilledQtyExcluveCanVndRtrn
    * @param bizMsg NFBL2040CMsg 
    * @param vndRtrnNum String
    * @param poOrdDtlLineNum String
    * @param mdseCd String
    * @return
    */
   public S21SsmEZDResult getSumBilledQtyExcluveCanVndRtrn(NFBL2040CMsg bizMsg, String vndRtrnNum, String poOrdDtlLineNum, String mdseCd) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("vndRtrnNum", vndRtrnNum);
       ssmParam.put("mdseCd", mdseCd);
       ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
       ssmParam.put("cancel", ACCT_INV_STS.CANCEL);
       // START 2018/11/20 [QC#28660, MOD]
       // ssmParam.put("entered", ACCT_INV_STS.ENTERED);
       if (!(CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) || !ZYPCommonFunc.hasValue(bizMsg.poNum))) {
           ssmParam.put("entered", ACCT_INV_STS.ENTERED);
       }
       // END   2018/11/20 [QC#28660, MOD]
       return getSsmEZDClient().queryObjectList("getSumBilledQtyExcluveCanInv", ssmParam, -1, -1);
   }
   // END   2018/08/15 [QC#27029-1, ADD]

   // START 2018/09/04 [QC#28039, ADD]
   /**
    * NFBL2040Query.xml id="getListCmApInvDtlByPartialKeyForCreditMemo"
    * 
    * <pre>
    * Get CM_AP_INV_DTL list by partial keys.
    * </pre>
    * 
    * @param poOrdNum String
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult getListCmApInvDtlByPartialKeyForCreditMemo(String poOrdNum) {

       Map<String, Object> queryParam = new HashMap<String, Object>();

       queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
       queryParam.put("poOrdNum", poOrdNum);
       queryParam.put("poLineStsCdIsOpen", PO_LINE_STS.OPEN);
       queryParam.put("poStsCdIsOpenList", new String[] {PO_STS.VALIDATED, PO_STS.PO_CONFIRMED});
       queryParam.put("poLinsStsCdIsOpenForXXList", new String[] {PO_LINE_STS.OPEN_FOR_RECEIPT, PO_LINE_STS.OPEN_FOR_INVOICE });
       queryParam.put("poStsCdIsClosed", PO_STS.CLOSED);
       return getSsmEZDClient().queryObjectList("getListCmApInvDtlByPartialKeyForCreditMemo", queryParam, -1, -1);

   }
   // END   2018/09/04 [QC#28039, ADD]

   // START 2018/09/26 [QC#28099, ADD]
   /**
    * NFBL2040Query.xml id="getVendorInfo"
    * @param apVndCd String
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult getVendorInfoSplyPmtOnly(String apVndCd) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("apVndCd", apVndCd);
       ssmParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
       ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(GLBL_CMPY_CD));
       ssmParam.put("supplier", VND_TP.SUPPLIER);
       ssmParam.put("splyPmtFlgY", ZYPConstant.FLG_ON_Y);
       return getSsmEZDClient().queryObjectList("getVendorInfo", ssmParam, -1, -1);
   }
   // END   2018/09/26 [QC#28099, ADD]

   // START 2018/10/03 [QC#28099-1, ADD]
   /**
    * NFBL2040Query.xml id="getVendorReturnVendorInfo"
    * @param vndRtrnNum String
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult getVendorReturnVendorInfo(String vndRtrnNum) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("vndRtrnNum", vndRtrnNum);
       return getSsmEZDClient().queryObjectList("getVendorReturnVendorInfo", ssmParam, -1, -1);
   }
   // END   2018/10/03 [QC#28099-1, ADD]

   // START 2018/10/10 [QC#28521, ADD]
   /**
    * getSumBilledQtyExcluveCanInv
    * @param poOrdNum Strings
    * @param poOrdDtlLineNum String
    * @param mdseCd String
    * @param apVndInvNum String
    * @return
    */
   public S21SsmEZDResult getBilledQtyInvMdse(String poOrdNum, String poOrdDtlLineNum, String mdseCd, String apVndInvNum) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("poOrdNum", poOrdNum);
       ssmParam.put("mdseCd", mdseCd);
       ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
       ssmParam.put("apVndInvNum", apVndInvNum);

       return getSsmEZDClient().queryObjectList("getBilledQtyInvMdse", ssmParam, -1, -1);
   }
   // END 2018/10/10 [QC#28521, ADD]

   // START 2018/11/20 [QC#28660, ADD]
   public S21SsmEZDResult getAssociatedInvoice(String poNum) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("cmApInvTpCd", CM_AP_INV_TP.CREDIT_MEMO);
       ssmParam.put("entered", ACCT_INV_STS.ENTERED);
       ssmParam.put("authorized", ACCT_INV_STS.AUTHORIZED);
       ssmParam.put("poNum", poNum);

       return getSsmEZDClient().queryObjectList("getAssociatedInvoice", ssmParam, -1, -1);
   }

   public S21SsmEZDResult getAssociatedCreditMemo(String poNum) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("cmApInvTpCd", CM_AP_INV_TP.CREDIT_MEMO);
       ssmParam.put("entered", ACCT_INV_STS.ENTERED);
       ssmParam.put("authorized", ACCT_INV_STS.AUTHORIZED);
       ssmParam.put("poNum", poNum);

       return getSsmEZDClient().queryObjectList("getAssociatedCreditMemo", ssmParam, -1, -1);
   }
   // END   2018/11/20 [QC#28660, ADD]

   // START 2018/03/19 [QC#30829, ADD]
   /**
    * NFBL2040Query.xml id="getSumBilledQtyExcluveCanInv"
    * @param bizMsg NFBL2040CMsg
    * @param vndRtrnNum String
    * @param poOrdDtlLineNum String
    * @param mdseCd String
    * @param apVndInvNum String
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult getSumBilledQtyVndRtrn(NFBL2040CMsg bizMsg, String vndRtrnNum, String poOrdDtlLineNum, String mdseCd, String apVndInvNum) {
      Map<String, Object> ssmParam = new HashMap<String, Object>();
      ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
      ssmParam.put("vndRtrnNum", vndRtrnNum);
      ssmParam.put("apVndInvNum", apVndInvNum);
      ssmParam.put("mdseCd", mdseCd);
      ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
      ssmParam.put("cancel", ACCT_INV_STS.CANCEL);
      if (!(CM_AP_INV_TP.CREDIT_MEMO.equals(bizMsg.cmApInvTpCd_S.getValue()) || !ZYPCommonFunc.hasValue(bizMsg.poNum))) {
          ssmParam.put("entered", ACCT_INV_STS.ENTERED);
      }
      return getSsmEZDClient().queryObjectList("getSumBilledQtyExcluveCanInv", ssmParam, -1, -1);
   }
   // END   2018/03/28 [QC#30829, ADD]

   // START 2021/04/22 R.Azucena [QC#56829, ADD]
   /**
    * getInvQtyOnHoldCntVndRtrn
    * @param bizMsg NFBL2040CMsg 
    * @param vndRtrnNum String
    * @param poOrdDtlLineNum String
    * @param mdseCd String
    * @param apVndInvNum String
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult getInvQtyOnHoldCntVndRtrn(NFBL2040CMsg bizMsg, String vndRtrnNum, String poOrdDtlLineNum, String mdseCd, String apVndInvNum) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("vndRtrnNum", vndRtrnNum);
       ssmParam.put("mdseCd", mdseCd);
       ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
       ssmParam.put("apVndInvNum", apVndInvNum);

       return getSsmEZDClient().queryObject("getPmtHldQtyCnt", ssmParam);
   }

   /**
    * getInvQtyOnHoldCnt
    * @param bizMsg NFBL2040CMsg
    * @param poOrdNum String
    * @param poOrdDtlLineNum String
    * @param mdseCd String
    * @param apVndInvNum String
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult getInvQtyOnHoldCnt(NFBL2040CMsg bizMsg, String poOrdNum, String poOrdDtlLineNum, String mdseCd, String apVndInvNum) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("poOrdNum", poOrdNum);
       ssmParam.put("mdseCd", mdseCd);
       ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
       ssmParam.put("apVndInvNum", apVndInvNum);

       return getSsmEZDClient().queryObject("getPmtHldQtyCnt", ssmParam);
   }
   // END 2021/04/22 R.Azucena [QC#56829, ADD]

   // Start 2023/1/17 S.Nakatani [QC#60812, ADD]
   /**
    * NFBL2040Query.xml id="getUpateAjeCmIntfcRecord"
    * @param bizMsg NFBL2040CMsg
    * @param globalMsg NFBL2040SMsg
    * @return List<Map<String, Object>>
    */
   public List<Map<String, Object>> getUpateAjeCmIntfcRecord(String apVndCd, String apVndInvNuM) {

       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("apVndCd", apVndCd);
       ssmParam.put("apVndInvNum", apVndInvNuM);

       return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getUpateAjeCmIntfcRecord", ssmParam).getResultObject();
   }
   
   /**
    * NFBL2040Query.xml id="getUpateAjegetUpateCmAcrlWriteOffRecordCmIntfcRecord"
    * @param bizMsg NFBL2040CMsg
    * @param globalMsg NFBL2040SMsg
    * @return List<Map<String, Object>>
    */
   public List<Map<String, Object>> getUpateCmAcrlWriteOffRecord(String apVndCd, String apVndInvNuM) {

       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("apVndCd", apVndCd);
       ssmParam.put("apVndInvNum", apVndInvNuM);

       return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getUpateCmAcrlWriteOffRecord", ssmParam).getResultObject();
   }

   /**
    * NFBL2040Query.xml id="getUpateJrnlEntryRecord"
    * @param bizMsg NFBL2040CMsg
    * @param globalMsg NFBL2040SMsg
    * @return List<Map<String, Object>>
    */
   public List<Map<String, Object>> getUpateJrnlEntryRecord(String apVndCd, String apVndInvNuM) {

       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("apVndCd", apVndCd);
       ssmParam.put("apVndInvNum", apVndInvNuM);
       ssmParam.put("trxCostCalc", TRX.COST_CALCULATION);
       ssmParam.put("trxRsnList", new String[] {"27", "28"});
       ssmParam.put("txtApInvNum", "AP Invoice Number");

       return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getUpateJrnlEntryRecord", ssmParam).getResultObject();
   }
   
   /**
    * NFBL2040Query.xml id="getArcsPmtId"
    * @param bizMsg NFBL2040CMsg
    * @param globalMsg NFBL2040SMsg
    * @return List<Map<String, Object>>
    */
   public List<Map<String, Object>> getArcsPmtId(String apVndCd, String apVndInvNuM) {

       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
       ssmParam.put("apVndCd", apVndCd);
       ssmParam.put("apVndInvNum", apVndInvNuM);

       return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getArcsPmtId", ssmParam).getResultObject();
   }
   // End 2023/1/17 S.Nakatani [QC#60812, ADD]
   
}
