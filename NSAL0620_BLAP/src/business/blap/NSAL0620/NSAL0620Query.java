/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0620;

import static business.blap.NSAL0620.constant.NSAL0620Constant.ACTV_PENDING_PO_CONTR_STS_CD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.ACTV_PENDING_PO_CONTR_STS_NM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.ACTV_RENEWAL_HOLD_CONTR_STS_CD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.ACTV_RENEWAL_HOLD_CONTR_STS_NM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.ALL_PER_TRMN_CONTR_STS_NM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.APL_ID;
import static business.blap.NSAL0620.constant.NSAL0620Constant.CONTR_DTL_STS_CD_FOR_RNW_HLD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DS_CONTR_NUM_SRCH_TYPE_EQUAL;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DS_CONTR_NUM_SRCH_TYPE_LIKE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DS_CONTR_NUM_SRCH_TYPE_LIKE_OR;
import static business.blap.NSAL0620.constant.NSAL0620Constant.CONTR_LINK_NUM_SRCH_TYPE_EQUAL;
import static business.blap.NSAL0620.constant.NSAL0620Constant.CONTR_LINK_NUM_SRCH_TYPE_LIKE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.CONTR_LINK_NUM_SRCH_TYPE_LIKE_OR;
import static business.blap.NSAL0620.constant.NSAL0620Constant.PERCENT;
import static business.blap.NSAL0620.constant.NSAL0620Constant.PND_ISTL_CONTR_STS_CD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.PND_ISTL_CONTR_STS_NM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.RPT_ID;
import static business.blap.NSAL0620.constant.NSAL0620Constant.STR_COMMA;
import static business.blap.NSAL0620.constant.NSAL0620Constant.STR_DS_CONTR_NUM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.STR_LIKE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.STR_QUOTE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.STR_SEMI_COLON;
import static business.blap.NSAL0620.constant.NSAL0620Constant.THRU_DT_LIMIT;
import static business.blap.NSAL0620.constant.NSAL0620Constant.FETCH_SIZE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.XX_CHK_BOX_A;
import static business.blap.NSAL0620.constant.NSAL0620Constant.NONEXISTENT_CODE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static parts.common.EZDCommonFunc.trimTail;

import static business.blap.NSAL0620.constant.NSAL0620Constant.DS_CONTR_BASE_USG_NM_B;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DS_CONTR_BASE_USG_NM_U;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DS_CONTR_MACH_LVL_NUM_1;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DS_CONTR_MACH_LVL_NUM_2;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DS_CONTR_MACH_LVL_NUM_3;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0620.common.NSAL0620CommonLogic;
import business.blap.NSAL0620.constant.NSAL0620Constant.DATE_TYPE;
import business.blap.NSAL0620.constant.NSAL0620Constant.QUERY_PRM_ID;
import business.db.DS_CONTR_STS_VTMsg;
import business.db.DS_CONTR_STS_VTMsgArray;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;
import business.db.MTR_READ_METHTMsg;
import business.db.MTR_READ_METHTMsgArray;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_CONTR_BRTMsgArray;
import business.db.SVC_PRC_RNW_LTR_WRKTMsg;
import business.db.SVC_PRC_RNW_LTR_WRKTMsgArray;
import business.db.SVC_RGTMsg;
import business.db.SVC_RGTMsgArray;
import business.db.SVC_TERM_COND_LTR_WRKTMsg;
import business.db.SVC_TERM_COND_LTR_WRKTMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3434
 * 2016/04/25   Hitachi         M.Gotou         Update          QC4326
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/08/10   Hitachi         K.Yamada        Update          QC#6322
 * 2016/08/25   Hitachi         A.Kohinata      Update          QC#11027
 * 2017/03/14   Hitachi         A.Kohinata      Update          QC#17750
 * 2017/06/16   Hitachi         K.Kojima        Update          QC#19256
 * 2017/08/02   Hitachi         K.Kojima        Update          QC#19906
 * 2017/08/21   Hitachi         E.Kameishi      Update          QC#8661
 * 2017/09/18   Hitachi         K.Kojima        Update          QC#21228
 * 2017/09/19   Hitachi         K.Kojima        Update          QC#21104
 * 2017/09/20   Hitachi         A.Kohinata      Update          QC#18534
 * 2017/10/02   Hitachi         K.Kojima        Update          QC#18417
 * 2017/10/06   Hitachi         A.Kohinata      Update          QC#21615
 * 2017/10/10   Hitachi         K.Kojima        Update          QC#21104-1
 * 2017/11/14   Hitachi         K.Kojima        Update          QC#21886
 * 2017/12/18   Hitachi         K.Kojima        Update          QC#22722
 * 2018/02/06   Hitachi         M.Kidokoro      Update          QC#23375
 * 2018/04/11   Hitachi         K.Kojima        Update          QC#22722-4
 * 2018/06/26   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/05   Hitachi         A.Kohinata      Update          QC#21545
 * 2018/07/18   CITS            T.Wada          Update          QC#26424
 * 2018/07/23   Hitachi         K.Kim           Update          QC#26831
 * 2018/11/05   Hitachi         K.Fujimoto      Update          QC#28627
 * 2019/01/08   Hitachi         S.Kitamura      Update          QC#29646
 * 2019/01/17   Hitachi         S.Kitamura      Update          QC#29952
 * 2019/02/21   Hitachi         K.Kim           Update          QC#30242
 * 2019/05/31   Hitachi         K.Fujimoto      Update          QC#50350
 * 2019/08/30   Hitachi         T.Aoyagi        Update          QC#53005
 * 2020/02/02   CITS            Y.Penequito     Update          QC#58312
 * 2021/09/10   CITS            L.Mandanas      Update          QC#58314-1
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/10/13   Hitachi         M.Komatsu       Update          QC#60069,60078,60537
 * 2023/05/16   CITS            T.Kimura        Update          QC#58675
 * 2023/09/05   Hitachi         Y.Nagasawa      Update          QC#61056
 *</pre>
 */
public final class NSAL0620Query extends S21SsmEZDQuerySupport {

    /** instance */
    private static final NSAL0620Query INSTANCE = new NSAL0620Query();

    /** DS Contract Detail Status Code List */
    private static final String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.RENEWAL_HOLD, DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO};

    // START 2016/02/18 T.Aoyagi [QC3434, ADD]
    /** DS Contract Detail Type Code List */
    private static final String[] dsContrDtlTpCdList = new String[] {DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE};
    // END 2016/02/18 T.Aoyagi [QC3434, ADD]

    /**
     * Private constructor
     */
    private NSAL0620Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0510Query singleton instance
     */
    public static NSAL0620Query getInstance() {
        return INSTANCE;
    }


    /**
     * getSearchData
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getSearchData", getSsmParam(cMsg, sMsg, sMsg.A.length() + 1), sMsg.A);

    }

    /**
     * getSsmParam
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @param rownum int
     * @return Map<String, Object>
     */
    public Map<String, Object> getSsmParam(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg, int rownum) {

        // add start 2017/09/20 CSA Defect#18534
        String pndIstlContrStsCd = ZYPCodeDataUtil.getVarCharConstValue(PND_ISTL_CONTR_STS_CD, cMsg.glblCmpyCd.getValue());
        String pndIstlContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(PND_ISTL_CONTR_STS_NM, cMsg.glblCmpyCd.getValue());
        // add end 2017/09/20 CSA Defect#18534

        // START 2017/10/02 K.Kojima [QC#18417,ADD]
        String activePendingPOContrStsCd = ZYPCodeDataUtil.getVarCharConstValue(ACTV_PENDING_PO_CONTR_STS_CD, cMsg.glblCmpyCd.getValue());
        String activePendingPOContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ACTV_PENDING_PO_CONTR_STS_NM, cMsg.glblCmpyCd.getValue());
        String activeRenewalHoldContrStsCd = ZYPCodeDataUtil.getVarCharConstValue(ACTV_RENEWAL_HOLD_CONTR_STS_CD, cMsg.glblCmpyCd.getValue());
        String activeRenewalHoldContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ACTV_RENEWAL_HOLD_CONTR_STS_NM, cMsg.glblCmpyCd.getValue());
        // END 2017/10/02 K.Kojima [QC#18417,ADD]

        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        String allPerTrmnContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ALL_PER_TRMN_CONTR_STS_NM, cMsg.glblCmpyCd.getValue());
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]

        // add start 2018/07/05 CSA Defect#21545
        String contrDtlStsCdForRnwHld = ZYPCodeDataUtil.getVarCharConstValue(CONTR_DTL_STS_CD_FOR_RNW_HLD, cMsg.glblCmpyCd.getValue());
        String[] contrDtlStsCdListForRnwHld = null;
        if (ZYPCommonFunc.hasValue(contrDtlStsCdForRnwHld)) {
            contrDtlStsCdListForRnwHld = contrDtlStsCdForRnwHld.split(STR_COMMA);
        }
        // add end 2018/07/05 CSA Defect#21545

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), cMsg.glblCmpyCd.getValue());
        //START 2017/08/21 E.Kameishi [QC#8661,ADD]
        //params.put(QUERY_PRM_ID.DS_CONTR_NUM.getQueryPrm(), cMsg.dsContrNum_H.getValue());
        // START 2018/04/11 K.Kojima [QC#22722-4,MOD]
        // params.put(QUERY_PRM_ID.DS_CONTR_NUM.getQueryPrm(), createSearchCondition(cMsg.dsContrNum_H.getValue()));
        String dsContrNumSearchType = getDsContrNumSearchType(cMsg.dsContrNum_H.getValue());
        if (hasValue(dsContrNumSearchType)) {
            if (dsContrNumSearchType.equals(DS_CONTR_NUM_SRCH_TYPE_EQUAL)) {
                params.put(QUERY_PRM_ID.DS_CONTR_NUM_EQUAL.getQueryPrm(), cMsg.dsContrNum_H.getValue());
            } else if (dsContrNumSearchType.equals(DS_CONTR_NUM_SRCH_TYPE_LIKE)) {
                params.put(QUERY_PRM_ID.DS_CONTR_NUM_LIKE.getQueryPrm(), cMsg.dsContrNum_H.getValue());
            } else if (dsContrNumSearchType.equals(DS_CONTR_NUM_SRCH_TYPE_LIKE_OR)) {
                params.put(QUERY_PRM_ID.DS_CONTR_NUM_LIKE_OR.getQueryPrm(), createSearchCondition(cMsg.dsContrNum_H.getValue()));
            }
        }
        // END 2018/04/11 K.Kojima [QC#22722-4,MOD]
        // START 2018/11/05 K.Fujimoto [QC#28627,ADD]
        String contrLinkNumSearchType = getContrLinkNumSearchType(cMsg.contrLinkNum_H.getValue());
        if (hasValue(contrLinkNumSearchType)) {
            if (contrLinkNumSearchType.equals(CONTR_LINK_NUM_SRCH_TYPE_EQUAL)) {
                params.put(QUERY_PRM_ID.CONTR_LINK_NUM_EQUAL.getQueryPrm(), cMsg.contrLinkNum_H.getValue());
            } else if (contrLinkNumSearchType.equals(CONTR_LINK_NUM_SRCH_TYPE_LIKE)) {
                params.put(QUERY_PRM_ID.CONTR_LINK_NUM_LIKE.getQueryPrm(), cMsg.contrLinkNum_H.getValue());
            } else if (contrLinkNumSearchType.equals(CONTR_LINK_NUM_SRCH_TYPE_LIKE_OR)) {
                params.put(QUERY_PRM_ID.CONTR_LINK_NUM_LIKE_OR.getQueryPrm(), createSearchCondition(cMsg.contrLinkNum_H.getValue()));
            }
        }
        // END   2018/11/05 K.Fujimoto [QC#28627,ADD]
        //END 2017/08/21 E.Kameishi [QC#8661,ADD]
        params.put(QUERY_PRM_ID.DS_CONTR_CRAT_DT_FROM.getQueryPrm(), cMsg.dsContrCratDt_H1.getValue());
        params.put(QUERY_PRM_ID.DS_CONTR_CRAT_DT_TO.getQueryPrm(), cMsg.dsContrCratDt_H2.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // params.put(QUERY_PRM_ID.DS_CONTR_CATG_CD.getQueryPrm(), cMsg.dsContrCatgCd_H.getValue());
        String[] dsContrCatgDescs = cMsg.xxComnScrColValTxt_H1.getValue().split(";");
        List<String> dsContrCatgCdList = NSAL0620CommonLogic.convertDescs2Codes(dsContrCatgDescs, cMsg.dsContrCatgDescTxt_L, cMsg.dsContrCatgCd_L);
        if (dsContrCatgCdList.size() > 0) {
            params.put(QUERY_PRM_ID.DS_CONTR_CATG_CD_LIST.getQueryPrm(), dsContrCatgCdList);
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        // mod start 2017/09/20 CSA Defect#18534
        //params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD.getQueryPrm(), cMsg.dsContrCtrlStsCd_H.getValue());
        // START 2019/02/21 [QC#30242,MOD]
        // if (hasValue(pndIstlContrStsCd) && pndIstlContrStsCd.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //     params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD.getQueryPrm(), DS_CONTR_CTRL_STS.ENTERED);
        // START 2017/10/02 K.Kojima [QC#18417,ADD]
        // } else if (hasValue(activePendingPOContrStsCd) && activePendingPOContrStsCd.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        // START 2022/10/13 M.Komatsu [QC#60069,60078,MOD]
        // if (hasValue(activePendingPOContrStsCd) && activePendingPOContrStsCd.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //     params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD.getQueryPrm(), DS_CONTR_CTRL_STS.ACTIVE);
        // } else if (hasValue(activeRenewalHoldContrStsCd) && activeRenewalHoldContrStsCd.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //     params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD.getQueryPrm(), DS_CONTR_CTRL_STS.ACTIVE);
        // END 2017/10/02 K.Kojima [QC#18417,ADD]
        // } else if (ZYPCommonFunc.hasValue(cMsg.dsContrCtrlStsCd_H)) {
            // START 2017/11/14 K.Kojima [QC#21886,MOD]
            // params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD.getQueryPrm(), cMsg.dsContrCtrlStsCd_H.getValue());
        //     if (DS_CONTR_CTRL_STS.ENTERED.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //         params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_SBMT.getQueryPrm(), DS_CONTR_DTL_STS.SUBMITED);
        //     } else if (hasValue(pndIstlContrStsCd) && pndIstlContrStsCd.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //         params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_SBMT.getQueryPrm(), DS_CONTR_DTL_STS.SUBMITED);
            // START 2019/05/31 [QC#50350,ADD]
        //     } else if (DS_CONTR_CTRL_STS.ACTIVE.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //         params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_ACTV.getQueryPrm(), DS_CONTR_DTL_STS.ACTIVE);
            // END 2019/05/31 [QC#50350,ADD]
        //     } else {
        //         List<String> statusList = getSameNameCtrlSts(cMsg.glblCmpyCd.getValue(), cMsg.dsContrCtrlStsCd_H.getValue());
        //         if (statusList.size() > 1) {
        //             params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD_LIST.getQueryPrm(), statusList);
        //         } else {
        //             params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD.getQueryPrm(), cMsg.dsContrCtrlStsCd_H.getValue());
        //         }
        //     }
            // END 2017/11/14 K.Kojima [QC#21886,MOD]
        // END 2019/02/21 [QC#30242,MOD]
        // }

        String[] dsContrCtrlStsDescs = cMsg.xxComnScrColValTxt_H2.getValue().split(";");

        List<String> dsContrCtrlStsCdList = new ArrayList<String>();
        List<String> dsContrCtrlStsNmList = new ArrayList<String>();
        boolean sbmtConditionEnabled = false;
        boolean pdilConditinoEnabled = false;
        boolean actvConditionEnabled = false;
        boolean appoConditionEnabled = false;
        boolean arhdConditionEnabled = false;
        boolean nmConditionEnabled = false;
        boolean cdConditionEnabled = false;

        for (String statusDesc : dsContrCtrlStsDescs) {
            if (statusDesc.isEmpty()) {
                continue;
            }
            String statusCode = NSAL0620CommonLogic.convertDesc2Code(statusDesc, cMsg.dsContrCtrlStsNm_L, cMsg.dsContrCtrlStsCd_L);

            if (statusCode.equals(DS_CONTR_CTRL_STS.ENTERED)) {
                params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_SBMT.getQueryPrm(), DS_CONTR_DTL_STS.SUBMITED);
                sbmtConditionEnabled = true;
            } else if (statusCode.equals(pndIstlContrStsCd)) {
                params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_PDIL.getQueryPrm(), DS_CONTR_DTL_STS.SUBMITED);
                pdilConditinoEnabled = true;
            } else if (statusCode.equals(DS_CONTR_CTRL_STS.ACTIVE)) {
                params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_ACTV.getQueryPrm(), DS_CONTR_DTL_STS.ACTIVE);
                actvConditionEnabled = true;
            } else if (statusCode.equals(activePendingPOContrStsCd)) {
                params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_APPO.getQueryPrm(), DS_CONTR_DTL_STS.ACTIVE);
                appoConditionEnabled = true;
            } else if (statusCode.equals(activeRenewalHoldContrStsCd)) {
                params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_ARHD.getQueryPrm(), DS_CONTR_DTL_STS.ACTIVE);
                arhdConditionEnabled = true;
            } else if (statusCode.equals(DS_CONTR_CTRL_STS.LINK_ERROR_BILLING_HOLD) || statusCode.equals(DS_CONTR_CTRL_STS.LINK_ERROR_HOLD) || statusCode.equals(DS_CONTR_CTRL_STS.LINK_ERROR_SYSTEM_HOLD)
                    || statusCode.equals(DS_CONTR_CTRL_STS.LINK_ERROR_QA_HOLD)) {
                dsContrCtrlStsNmList.add(statusDesc);
                nmConditionEnabled = true;
            } else {
                if (statusCode.equals(NONEXISTENT_CODE)) {
                    dsContrCtrlStsCdList.add(NONEXISTENT_CODE);
                } else {
                    List<String> statusList = getSameNameCtrlSts(cMsg.glblCmpyCd.getValue(), statusCode);
                    dsContrCtrlStsCdList.addAll(statusList);
                }
                cdConditionEnabled = true;
            }
        }

        if (dsContrCtrlStsCdList.size() > 0) {
            params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD_LIST.getQueryPrm(), dsContrCtrlStsCdList);
        }
        if (dsContrCtrlStsNmList.size() > 0) {
            params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_NM_LIST.getQueryPrm(), dsContrCtrlStsNmList);
        }

        if (sbmtConditionEnabled || pdilConditinoEnabled || actvConditionEnabled || appoConditionEnabled || arhdConditionEnabled || nmConditionEnabled || cdConditionEnabled) {
            params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_COND.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        if (nmConditionEnabled || cdConditionEnabled) {
            params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_ETC_COND.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }

        if (sbmtConditionEnabled && (pdilConditinoEnabled || actvConditionEnabled || appoConditionEnabled || arhdConditionEnabled || nmConditionEnabled || cdConditionEnabled)) {
            params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_OR_1.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        if (pdilConditinoEnabled && (actvConditionEnabled || appoConditionEnabled || arhdConditionEnabled || nmConditionEnabled || cdConditionEnabled)) {
            params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_OR_2.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        if (actvConditionEnabled && (appoConditionEnabled || arhdConditionEnabled || nmConditionEnabled || cdConditionEnabled)) {
            params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_OR_3.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        if (appoConditionEnabled && (arhdConditionEnabled || nmConditionEnabled || cdConditionEnabled)) {
            params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_OR_4.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        if (arhdConditionEnabled && (nmConditionEnabled || cdConditionEnabled)) {
            params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_OR_5.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        if (nmConditionEnabled && cdConditionEnabled) {
            params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_OR_6.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        // END 2022/10/13 M.Komatsu [QC#60069,60078,MOD]

        // mod end 2017/09/20 CSA Defect#18534
        params.put(QUERY_PRM_ID.INV_SEPT_BASE_USG_FLG.getQueryPrm(), cMsg.invSeptBaseUsgFlg_H.getValue());
        // mod start 2016/04/25 CSA Defect#4326
        params.put(QUERY_PRM_ID.DS_CONTR_RPT_GRP_NUM.getQueryPrm(), cMsg.dsContrRptGrpNum_H.getValue());
        // mod start 2016/04/25 CSA Defect#4326
        params.put(QUERY_PRM_ID.SVC_CONTR_REF_CMNT_TXT.getQueryPrm(), cMsg.svcContrRefCmntTxt_H.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // params.put(QUERY_PRM_ID.DS_CONTR_CLS_CD.getQueryPrm(), cMsg.dsContrClsCd_H.getValue());
        String[] dsContrClsCdDescs = cMsg.xxComnScrColValTxt_H3.getValue().split(";");
        List<String> dsContrClsCdList = NSAL0620CommonLogic.convertDescs2Codes(dsContrClsCdDescs, cMsg.dsContrClsNm_L, cMsg.dsContrClsCd_L);
        if (dsContrClsCdList.size() > 0) {
            params.put(QUERY_PRM_ID.DS_CONTR_CLS_CD_LIST.getQueryPrm(), dsContrClsCdList);
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        params.put(QUERY_PRM_ID.BILL_TO_CUST_CD.getQueryPrm(), cMsg.billToCustCd_H1.getValue());
        // add start 2016/07/01 CSA Defect#11261
//        params.put(QUERY_PRM_ID.SVC_PGM_MDSE_CD.getQueryPrm(), cMsg.svcPgmMdseCd_H.getValue());
        params.put(QUERY_PRM_ID.MDSE_DESC_SHORT_TXT.getQueryPrm(), cMsg.mdseDescShortTxt_H.getValue());
        // add end 2016/07/01 CSA Defect#11261
        params.put(QUERY_PRM_ID.DATE_TYPE.getQueryPrm(), cMsg.xxRadioBtn_H1.getValue());
        params.put(QUERY_PRM_ID.SELECT_TYPE.getQueryPrm(), cMsg.xxRadioBtn_H2.getValue());
        params.put(QUERY_PRM_ID.CONTR_EFF_FROM_DT.getQueryPrm(), cMsg.xxFromDt_H.getValue());
        params.put(QUERY_PRM_ID.CONTR_EFF_THRU_DT.getQueryPrm(), cMsg.xxThruDt_H.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // params.put(QUERY_PRM_ID.BASE_BLLG_CYCLE_CD.getQueryPrm(), cMsg.bllgCycleCd_HB.getValue());
        // params.put(QUERY_PRM_ID.MTR_BLLG_CYCLE_CD.getQueryPrm(), cMsg.bllgCycleCd_HM.getValue());
        String[] baseBllgCycleDesc = cMsg.xxComnScrColValTxt_H5.getValue().split(";");
        List<String> baseBllgCycleCdList = NSAL0620CommonLogic.convertDescs2Codes(baseBllgCycleDesc, cMsg.bllgCycleNm_LB, cMsg.bllgCycleCd_LB);
        if (baseBllgCycleCdList.size() > 0) {
            params.put(QUERY_PRM_ID.BASE_BLLG_CYCLE_CD_LIST.getQueryPrm(), baseBllgCycleCdList);
        }
        String[] mtrBllgCycleDesc = cMsg.xxComnScrColValTxt_H6.getValue().split(";");
        List<String> mtrBllgCycleCdList = NSAL0620CommonLogic.convertDescs2Codes(mtrBllgCycleDesc, cMsg.bllgCycleNm_LM, cMsg.bllgCycleCd_LM);
        if (mtrBllgCycleCdList.size() > 0) {
            params.put(QUERY_PRM_ID.MTR_BLLG_CYCLE_CD_LIST.getQueryPrm(), mtrBllgCycleCdList);
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        // START 2016/02/18 T.Aoyagi [QC3434, ADD]
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_TP_CD.getQueryPrm(), dsContrDtlTpCdList);
        // END 2016/02/18 T.Aoyagi [QC3434, ADD]
        params.put(QUERY_PRM_ID.SUB_CONTR_FLG.getQueryPrm(), cMsg.xxChkBox_H1.getValue());
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD.getQueryPrm(), dsContrDtlStsCdList);
        params.put(QUERY_PRM_ID.DS_ACCT_NUM.getQueryPrm(), cMsg.dsAcctNum_H.getValue());
        params.put(QUERY_PRM_ID.DS_ACCT_NM.getQueryPrm(), cMsg.dsAcctNm_H.getValue());
        params.put(QUERY_PRM_ID.SER_NUM.getQueryPrm(), cMsg.serNum_H.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // params.put(QUERY_PRM_ID.SVC_MACH_MSTR_STS_CD.getQueryPrm(), cMsg.svcMachMstrStsCd_H.getValue());
        String[] svcMachMstrStsDescs = cMsg.xxComnScrColValTxt_H4.getValue().split(";");
        List<String> svcMachMstrStsCdList = NSAL0620CommonLogic.convertDescs2Codes(svcMachMstrStsDescs, cMsg.svcMachMstrStsNm_L, cMsg.svcMachMstrStsCd_L);
        if (svcMachMstrStsCdList.size() > 0) {
            params.put(QUERY_PRM_ID.SVC_MACH_MSTR_STS_CD_LIST.getQueryPrm(), svcMachMstrStsCdList);
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        params.put(QUERY_PRM_ID.SVC_MACH_MSTR_STS_CD_IS_RMV.getQueryPrm(), SVC_MACH_MSTR_STS.REMOVED);
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        params.put(QUERY_PRM_ID.MDSE_CD.getQueryPrm(), cMsg.mdseCd_H.getValue());
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        params.put(QUERY_PRM_ID.CUR_LOC_NUM.getQueryPrm(), cMsg.dsAcctNm_H2.getValue());
        // mod start 2016/04/25 CSA Defect#4326
        params.put(QUERY_PRM_ID.T_MDL_NM.getQueryPrm(), cMsg.t_MdlNm_H.getValue());
        // START 2019/08/30 [QC#53005,ADD]
        params.put(QUERY_PRM_ID.SVC_CONFIG_MSTR_PK.getQueryPrm(), cMsg.svcConfigMstrPk_H.getValue());
        // END 2019/08/30 [QC#53005,ADD]
        params.put(QUERY_PRM_ID.LOC_NUM.getQueryPrm(), cMsg.locNum_H.getValue());
        params.put(QUERY_PRM_ID.SLS_DT.getQueryPrm(), cMsg.slsDt.getValue());
        params.put(QUERY_PRM_ID.BASE_CHRG_FLG.getQueryPrm(), cMsg.baseChrgToLeaseCmpyFlg_H.getValue());
        params.put(QUERY_PRM_ID.USG_CHRG_FLG.getQueryPrm(), cMsg.usgChrgToLeaseCmpyFlg_H.getValue());
        params.put(QUERY_PRM_ID.LIMIT_ROWNUM.getQueryPrm(), rownum);
        params.put(QUERY_PRM_ID.THRU_DT_LIMIT.getQueryPrm(), THRU_DT_LIMIT);
        params.put(QUERY_PRM_ID.ONLY_MYTASK_FLG.getQueryPrm(), cMsg.xxChkBox_H2.getValue());
        // START 2018/02/06 M.Kidokoro [QC#23375,ADD]
        params.put(QUERY_PRM_ID.MY_TEAM_FLG.getQueryPrm(), cMsg.xxChkBox_H3.getValue());
        // END 2018/02/06 M.Kidokoro [QC#23375,ADD]
        params.put(QUERY_PRM_ID.USER_ID.getQueryPrm(), cMsg.getUserID());
        params.put(QUERY_PRM_ID.FLG_OFF_N.getQueryPrm(), ZYPConstant.FLG_OFF_N);
        // START 2017/06/16 K.Kojima [QC#19256,ADD]
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_STS_CD_ORDR.getQueryPrm(), DS_CONTR_DTL_STS.ORDER);
        // END 2017/06/16 K.Kojima [QC#19256,ADD]
        // START 2017/09/18 K.Kojima [QC#21228,ADD]
        params.put(QUERY_PRM_ID.DS_CONTR_STS_CD_ORDR.getQueryPrm(), DS_CONTR_STS.ORDER);
        // END 2017/09/18 K.Kojima [QC#21228,ADD]
        // START 2017/09/19 K.Kojima [QC#21104,ADD]
        params.put(QUERY_PRM_ID.INV_TP_CD.getQueryPrm(), INV_TP.INVOICE);
        // END 2017/09/19 K.Kojima [QC#21104,ADD]
        // START 2017/10/10 K.Kojima [QC#21104-1,ADD]
        params.put(QUERY_PRM_ID.DS_BLLG_SCHD_STS_OPEN.getQueryPrm(), DS_BLLG_SCHD_STS.OPEN);
        // END 2017/10/10 K.Kojima [QC#21104-1,ADD]

        // mod start 2017/03/14 CSA Defect#17750
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg_H.getValue())
            || ZYPConstant.FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg_H.getValue())
            // START 2017/12/18 K.Kojima [QC#22722,DEL]
            // || (ZYPCommonFunc.hasValue(cMsg.xxRadioBtn_H1)
            //         && DATE_TYPE.SCHEDULE_DATE.getDateType().compareTo(cMsg.xxRadioBtn_H1.getValue()) == 0)
            // END 2017/12/18 K.Kojima [QC#22722,DEL]
            || (ZYPCommonFunc.hasValue(cMsg.xxRadioBtn_H1)
                    // START 2017/12/18 K.Kojima [QC#22722,ADD]
                    && (ZYPCommonFunc.hasValue(cMsg.xxFromDt_H) || ZYPCommonFunc.hasValue(cMsg.xxThruDt_H))
                    // END 2017/12/18 K.Kojima [QC#22722,ADD]
                    && DATE_TYPE.INTERFACE_DATE.getDateType().compareTo(cMsg.xxRadioBtn_H1.getValue()) == 0)) {
            params.put(QUERY_PRM_ID.SCHD_FLG.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        // mod end 2017/03/14 CSA Defect#17750

        // START 2017/12/18 K.Kojima [QC#22722,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.xxRadioBtn_H1)
                && (ZYPCommonFunc.hasValue(cMsg.xxFromDt_H) || ZYPCommonFunc.hasValue(cMsg.xxThruDt_H))
                && DATE_TYPE.SCHEDULE_DATE.getDateType().compareTo(cMsg.xxRadioBtn_H1.getValue()) == 0) {
            params.put(QUERY_PRM_ID.SCHD_FLG_NEXT_BLLG_DT.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        }
        // END 2017/12/18 K.Kojima [QC#22722,ADD]

        // mod start 2016/04/25 CSA Defect#4326
        if (ZYPCommonFunc.hasValue(cMsg.xxGenlFldAreaTxt_H1)) {
            String st = cMsg.xxGenlFldAreaTxt_H1.getValue();
            String[] st2 = st.split(STR_COMMA, 0);
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < st2.length; i++) {
                if (i != 0) {
                    sb.append(" ");
                }
                sb.append(st2[i].trim());
            }
            String billToLocNm = sb.toString();

            params.put(QUERY_PRM_ID.BILL_TO_LOC_NM.getQueryPrm(), billToLocNm);
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxGenlFldAreaTxt_H2)) {
            String st = cMsg.xxGenlFldAreaTxt_H2.getValue();
            String[] st2 = st.split(STR_COMMA, 0);
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < st2.length; i++) {
                if (i != 0) {
                    sb.append(" ");
                }
                sb.append(st2[i].trim());
            }
            String locNm = sb.toString();

            params.put(QUERY_PRM_ID.LOC_NM.getQueryPrm(), locNm);
        }
        // mod end 2016/04/25 CSA Defect#4326

        List<String> listB = new ArrayList<String>();
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.B.no(i).xxChkBox_B.getValue())) {
                listB.add(new String(cMsg.B.no(i).svcRgNm_B.getValue()));
            }
        }
        String[] arrayB = (String[]) listB.toArray(new String[0]);
        params.put(QUERY_PRM_ID.SVC_RG_NM.getQueryPrm(), arrayB);

        List<String> listC = new ArrayList<String>();
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.C.no(i).xxChkBox_C.getValue())) {
                listC.add(new String(cMsg.C.no(i).svcContrBrCd_C.getValue()));
            }
        }
        String[] arrayC = (String[]) listC.toArray(new String[0]);
        params.put(QUERY_PRM_ID.SVC_CONTR_BR_CD.getQueryPrm(), arrayC);

        List<String> listD = new ArrayList<String>();
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.D.no(i).xxChkBox_D.getValue())) {
                listD.add(new String(cMsg.D.no(i).mtrReadMethCd_D.getValue()));
            }
        }
        String[] arrayD = (String[]) listD.toArray(new String[0]);
        params.put(QUERY_PRM_ID.MTR_READ_METH_CD.getQueryPrm(), arrayD);

        List<String> listE = new ArrayList<String>();
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.E.no(i).xxChkBox_E.getValue())) {
                listE.add(new String(cMsg.E.no(i).mtrLbCd_E.getValue()));
            }
        }
        String[] arrayE = (String[]) listE.toArray(new String[0]);
        params.put(QUERY_PRM_ID.MTR_LB_CD.getQueryPrm(), arrayE);
        if (arrayE.length > 0) {
            params.put(QUERY_PRM_ID.MTR_LB_FLG.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        } else {
            params.put(QUERY_PRM_ID.MTR_LB_FLG.getQueryPrm(), ZYPConstant.FLG_OFF_N);
        }

        // add start 2016/08/10 CSA Defect#6322
        HashSet<String> listF = new HashSet<String>();
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H3)) {
            //get parent account
            S21SsmEZDResult ssmResult = getParentAcct(cMsg);
            addAcctList(ssmResult, listF);

            //get child account
            ssmResult = getChildAcct(cMsg);
            addAcctList(ssmResult, listF);

            if (listF.isEmpty()) {
                listF.add("DATA_DOES_NOT_EXIST");
            }
        }
        String[] arrayF = (String[]) listF.toArray(new String[0]);
        params.put("dsAcctList", arrayF);
        // add end 2016/08/10 CSA Defect#6322

        // add start 2017/09/20 CSA Defect#18534
        params.put("entered", DS_CONTR_CTRL_STS.ENTERED);
        // del start 2017/10/06 QC#21615
//        params.put("cancelled", SVC_TASK_STS.CANCELLED);
//        params.put("istlCallTpCd", ISTL_CALL_TP_CD);
        // del end 2017/10/06 QC#21615
        // add start 2017/10/06 QC#21615
        params.put("w4i", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        // add end 2017/10/06 QC#21615
        params.put("pendingInstallation", pndIstlContrStsNm);
        // START 2022/10/13 M.Komatsu [QC#60078,DEL]
        // if (hasValue(pndIstlContrStsCd) && pndIstlContrStsCd.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //     params.put("pdilFlg", ZYPConstant.FLG_ON_Y);
        // } else if (DS_CONTR_CTRL_STS.ENTERED.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //     params.put("pdilFlg", ZYPConstant.FLG_OFF_N);
        // END 2022/10/13 M.Komatsu [QC#60078,DEL]
        // add end 2017/09/20 CSA Defect#18534

        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        params.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        params.put("allPeriodTermination", allPerTrmnContrStsNm);
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]

        // START 2017/10/02 K.Kojima [QC#18417,ADD]
        params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD_ACTV.getQueryPrm(), DS_CONTR_CTRL_STS.ACTIVE);
        params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD_RNPO.getQueryPrm(), DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO);
        params.put(QUERY_PRM_ID.DS_CONTR_CTRL_STS_CD_RNWH.getQueryPrm(), DS_CONTR_CTRL_STS.RENEWAL_HOLD);
        params.put(QUERY_PRM_ID.ACTIVE_PENDING_PO.getQueryPrm(), activePendingPOContrStsNm);
        params.put(QUERY_PRM_ID.ACTIVE_RENEWAL_HOLD.getQueryPrm(), activeRenewalHoldContrStsNm);
        params.put(QUERY_PRM_ID.DS_CONTR_CATG_CD_FLEET.getQueryPrm(), DS_CONTR_CATG.FLEET);
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_TP_CD_FLEET.getQueryPrm(), DS_CONTR_DTL_TP.FLEET);
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // if (hasValue(activePendingPOContrStsCd) && activePendingPOContrStsCd.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //     params.put(QUERY_PRM_ID.ACTIVE_PENDING_PO_SEARCH_FLG.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        // }
        // if (hasValue(activeRenewalHoldContrStsCd) && activeRenewalHoldContrStsCd.equals(cMsg.dsContrCtrlStsCd_H.getValue())) {
        //     params.put(QUERY_PRM_ID.ACtIVE_RENEWAL_HOLD_SEARCH_FLG.getQueryPrm(), ZYPConstant.FLG_ON_Y);
        // }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        // END 2017/10/02 K.Kojima [QC#18417,ADD]

        // add start 2018/07/05 CSA Defect#21545
        params.put(QUERY_PRM_ID.CONTR_DTL_STS_CD_LIST_FOR_RNW_HLD.getQueryPrm(), contrDtlStsCdListForRnwHld);
        // add end 2018/07/05 CSA Defect#21545

        // START 2018/07/11 T.Wada [QC#26424,ADD]
        params.put(QUERY_PRM_ID.SVC_MEMO_TP_CD_TERM.getQueryPrm(), SVC_MEMO_TP.TERMINATION_NOTE);
        params.put(QUERY_PRM_ID.DS_CONTR_BASE_USG_NM_B.getQueryPrm(), DS_CONTR_BASE_USG_NM_B);
        params.put(QUERY_PRM_ID.DS_CONTR_BASE_USG_NM_U.getQueryPrm(), DS_CONTR_BASE_USG_NM_U);
        params.put(QUERY_PRM_ID.DS_CONTR_MACH_LVL_NUM_1.getQueryPrm(), DS_CONTR_MACH_LVL_NUM_1);
        params.put(QUERY_PRM_ID.DS_CONTR_MACH_LVL_NUM_2.getQueryPrm(), DS_CONTR_MACH_LVL_NUM_2);
        params.put(QUERY_PRM_ID.DS_CONTR_MACH_LVL_NUM_3.getQueryPrm(), DS_CONTR_MACH_LVL_NUM_3);
        params.put(QUERY_PRM_ID.DS_CONTR_CATG_CD_REG.getQueryPrm(), DS_CONTR_CATG.REGULAR);
        params.put(QUERY_PRM_ID.DS_CONTR_CATG_CD_AGG.getQueryPrm(), DS_CONTR_CATG.AGGREGATE);
        // END 2018/07/11 T.Wada [QC#26424,ADD]
        // START 2023/09/05 Y.Nagasawa [QC#61056,ADD]
        boolean createdBetweenFlg = false;
        if (!(cMsg.dsContrCratDt_H1.getValue().isEmpty()) || !(cMsg.dsContrCratDt_H2.getValue().isEmpty())) {
            createdBetweenFlg = true;
            params.put("DsContrToStsCd", DS_CONTR_CTRL_STS.DRAFT);
        }
        params.put("createdBetweenFlg", createdBetweenFlg);
        // END 2023/09/05 Y.Nagasawa [QC#61056,ADD]

        return params;

    }

    // add start 2016/08/10 CSA Defect#6322
    private S21SsmEZDResult getParentAcct(NSAL0620CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());
        params.put(QUERY_PRM_ID.DS_ACCT_NM.getQueryPrm(), cMsg.dsAcctNm_H3.getValue());
        params.put("parentAcct", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        params.put("p20", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getParentAcct", params);
    }

    private S21SsmEZDResult getChildAcct(NSAL0620CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());
        params.put(QUERY_PRM_ID.DS_ACCT_NM.getQueryPrm(), cMsg.dsAcctNm_H3.getValue());
        params.put("parentAcct", DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        params.put("p20", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getChildAcct", params);
    }

    private void addAcctList(S21SsmEZDResult ssmResult, HashSet<String> acctList) {
        if (!ssmResult.isCodeNormal()) {
            return;
        }
        List<Map<String, String>> acctInfo = (List<Map<String, String>>) ssmResult.getResultObject();
        for (Map<String, String> acct : acctInfo) {
            String dsAcctNum = acct.get("DS_ACCT_NUM");
            String relnDsAcctNum = acct.get("RELN_DS_ACCT_NUM");
            if (ZYPCommonFunc.hasValue(dsAcctNum)) {
                acctList.add(dsAcctNum);
            }
            if (ZYPCommonFunc.hasValue(relnDsAcctNum)) {
                acctList.add(relnDsAcctNum);
            }
        }
    }
    // add end 2016/08/10 CSA Defect#6322

    /**
     * getSavedSearchOptionList
     * @param userId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());
        params.put(QUERY_PRM_ID.SRCH_OPT_APL_ID.getQueryPrm(), APL_ID);
        params.put(QUERY_PRM_ID.SRCH_OPT_USR_ID.getQueryPrm(), userId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * getSvcRgList
     * @param glblCmpyCd String
     * @return SVC_RGTMsgArray
     */
    public SVC_RGTMsgArray getSvcRgList(String glblCmpyCd) {
        SVC_RGTMsg prmTMsg = new SVC_RGTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_RGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getSvcContrBrList
     * @param glblCmpyCd String
     * @return SVC_CONTR_BRTMsgArray
     */
    public SVC_CONTR_BRTMsgArray getSvcContrBrList(String glblCmpyCd) {
        SVC_CONTR_BRTMsg prmTMsg = new SVC_CONTR_BRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_CONTR_BRTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getMtrReadMethList
     * @param glblCmpyCd String
     * @return MTR_READ_METHTMsgArray
     */
    public MTR_READ_METHTMsgArray getMtrReadMethList(String glblCmpyCd) {
        MTR_READ_METHTMsg prmTMsg = new MTR_READ_METHTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (MTR_READ_METHTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    /**
     * getMtrLbList
     * @param glblCmpyCd String
     * @return MTR_LBTMsgArray
     */
    public MTR_LBTMsgArray getMtrLbList(String glblCmpyCd) {
        MTR_LBTMsg prmTMsg = new MTR_LBTMsg();
        // START 2017/08/02 K.Kojima [QC#19906,MOD]
        // prmTMsg.setSQLID("001");
        prmTMsg.setSQLID("003");
        // END 2017/08/02 K.Kojima [QC#19906,MOD]
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // START 2017/08/02 K.Kojima [QC#19906,ADD]
        prmTMsg.setConditionValue("mtrLbTpCd01", MTR_LB_TP.BILLING_METER);
        // END 2017/08/02 K.Kojima [QC#19906,ADD]
        return (MTR_LBTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
    }

    // START 2017/11/14 K.Kojima [QC#21886,DEL]
    // /**
    //  * Find Contract Control Status Code PulldownList
    //  * @param glblCmpyCd String
    //  * @return DS_CONTR_CTRL_STSTMsgArray
    //  */
    // public DS_CONTR_CTRL_STSTMsgArray findContrCtrlStsCdPulldownList(String glblCmpyCd) {
    //     DS_CONTR_CTRL_STSTMsg inMsg = new DS_CONTR_CTRL_STSTMsg();
    //     inMsg.setSQLID("201");
    //     inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //     inMsg.setConditionValue("dsContrDtlStsCd01", DS_CONTR_DTL_STS.ORDER);
    //     return (DS_CONTR_CTRL_STSTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    // }
    // END 2017/11/14 K.Kojima [QC#21886,DEL]

    // START 2017/11/14 K.Kojima [QC#21886,ADD]
    /**
     * getContrCtrlStsCdPulldownList
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrCtrlStsCdPulldownList(String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        // START 2019/1/8 S.Kitamura [QC#29646,MOD]
        // params.put("dsContrCtrlStsOrdr", DS_CONTR_CTRL_STS.ORDER);
        ArrayList<String> dsContrCtrlStsList = new ArrayList<String>();
        dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.ORDER);
        // END 2022/10/13 M.Komatsu [QC#60069,DEL]
        // dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.LINK_ERROR_BILLING_HOLD);
        // dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.LINK_ERROR_HOLD);
        // dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.LINK_ERROR_SYSTEM_HOLD);
        // START 2019/1/17 S.Kitamura [QC#29646,MOD]
        // dsContrCtrlStsList.add(DS_CONTR_CTRL_STS.LINK_ERROR_QA_HOLD);
        // END 2022/10/13 M.Komatsu [QC#60069,DEL]
        // END 2019/1/17 S.Kitamura [QC#29646,MOD]
        params.put("dsContrCtrlStsList", dsContrCtrlStsList);
        // END 2019/1/8 S.Kitamura [QC#29646,MOD]
        return getSsmEZDClient().queryObjectList("getContrCtrlStsCdPulldownList", params);
    }

    /**
     * getSameNameCtrlSts
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public List<String> getSameNameCtrlSts(String glblCmpyCd, String dsContrCtrlStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrCtrlStsCd", dsContrCtrlStsCd);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getSameNameCtrlSts", params);
        if (!result.isCodeNormal()) {
            return new ArrayList<String>();
        }
        return (List<String>) result.getResultObject();
    }

    // END 2017/11/14 K.Kojima [QC#21886,ADD]

    // add start 2016/07/28 CSA Defect#6923
    /**
     * getContrForManualRenew
     * @param glblCmpyCd String
     * @param dsContrPkList List
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrForManualRenew(String glblCmpyCd, List<BigDecimal> dsContrPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPkList", dsContrPkList);
        return getSsmEZDClient().queryObjectList("getContrForManualRenew", params);
    }
    // add end 2016/07/28 CSA Defect#6923

    // START 2018/07/23 K.Kim [QC#26831,MOD]
    // add start 2016/08/25 CSA Defect#11027
    /**
     * getContrTrmnAvalFlg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return String
     */
    // public String getDsContrCtrlStsCd(String glblCmpyCd, BigDecimal dsContrPk) {
    public String getContrTrmnAvalFlg(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTR_STS_VTMsg tMsg = new DS_CONTR_STS_VTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_STS_VTMsgArray dsContrStsVTMsgArray = (DS_CONTR_STS_VTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (dsContrStsVTMsgArray == null || dsContrStsVTMsgArray.getValidCount() == 0) {
            return null;
        }
        // return dsContrStsVTMsgArray.no(0).dsContrCtrlStsCd.getValue();
        return dsContrStsVTMsgArray.no(0).contrTrmnAvalFlg.getValue();
    }
    // add end 2016/08/25 CSA Defect#11027
    // END 2018/07/23 K.Kim [QC#26831,MOD]

    // START 2018/04/11 K.Kojima [QC#22722-4,ADD]
    private String getDsContrNumSearchType(String searchCondition) {
        if (!hasValue(searchCondition)) {
            return null;
        }
        String[] srchTxtArray = getSrchTxt(trimTail(searchCondition));
        if (srchTxtArray == null) {
            if (searchCondition.indexOf(PERCENT) >= 0) {
                return DS_CONTR_NUM_SRCH_TYPE_LIKE;
            } else {
                return DS_CONTR_NUM_SRCH_TYPE_EQUAL;
            }
        } else {
            return DS_CONTR_NUM_SRCH_TYPE_LIKE_OR;
        }
    }

    // END 2018/04/11 K.Kojima [QC#22722-4,ADD]

    // START 2018/11/05 K.Fujimoto [QC#28627,ADD]
    private String getContrLinkNumSearchType(String searchCondition) {
        if (!hasValue(searchCondition)) {
            return null;
        }
        String[] srchTxtArray = getSrchTxt(trimTail(searchCondition));
        if (srchTxtArray == null) {
            if (searchCondition.indexOf(PERCENT) >= 0) {
                return CONTR_LINK_NUM_SRCH_TYPE_LIKE;
            } else {
                return CONTR_LINK_NUM_SRCH_TYPE_EQUAL;
            }
        } else {
            return CONTR_LINK_NUM_SRCH_TYPE_LIKE_OR;
        }
    }
    // END   2018/11/05 K.Fujimoto [QC#28627,ADD]
    
    //START 2017/08/21 E.Kameishi [QC#8661,ADD]
    private String createSearchCondition(String searchCondition) {

        if (!hasValue(searchCondition)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String[] srchTxtArray = getSrchTxt(trimTail(searchCondition));

        if (srchTxtArray == null) {
            // START 2018/04/11 K.Kojima [QC#22722-4,MOD]
            // append(sb, " AND ", STR_DS_CONTR_NUM, " ", STR_LIKE, " ", STR_QUOTE, searchCondition, STR_QUOTE);
            return "";
            // END 2018/04/11 K.Kojima [QC#22722-4,MOD]
        } else {
            append(sb, " AND (");
            for (int i = 0; i < srchTxtArray.length; i++) {
                if (i > 0) {
                    append(sb, " OR ");
                }
                append(sb, STR_DS_CONTR_NUM, " ", STR_LIKE, " ", STR_QUOTE, srchTxtArray[i], STR_QUOTE);
            }
            append(sb, ")");
        }
        return sb.toString();
    }
    
    private void append(StringBuilder sb, String... strs) {
        for (String str : strs) {
            sb.append(str);
        }
    }
    
    private String[] getSrchTxt(String item) {
        if (item != null && item.length() > 0) {
            if (item.indexOf(STR_SEMI_COLON) != -1) {
                String[] itemArray = item.split(STR_SEMI_COLON);
                boolean isExists = false;
                for (int i = 0; i < itemArray.length; i++) {
                    if (!itemArray[i].trim().equals("") && itemArray[i].length() > 0) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists == true) {
                    return itemArray;
                }
            }
        }
        return null;
    }

    /**
     * getContrDtlList
     * @param List<BigDecimal> dsContrDtlPkList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtlList(List<BigDecimal> dsContrDtlPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());
        BigDecimal[] dsContrDtlListArray = (BigDecimal[]) dsContrDtlPkList.toArray(new BigDecimal[0]);
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_PK.getQueryPrm(), dsContrDtlListArray);

        return getSsmEZDClient().queryObjectList("getContrDtlList", params);
    }
    
    /**
     * getSvcPrcRmwLtrWrkInfo
     * @param BigDecimal dsContrPk
     * @return SVC_PRC_RNW_LTR_WRKTMsgArray
     */
    public static SVC_PRC_RNW_LTR_WRKTMsgArray getSvcPrcRmwLtrWrkInfo(NSAL0620CMsg cMsg, BigDecimal dsContrPk) {
        SVC_PRC_RNW_LTR_WRKTMsg inTMsg = new SVC_PRC_RNW_LTR_WRKTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("dsContrPk01", dsContrPk);
        inTMsg.setConditionValue("bizAppId01", APL_ID);
        inTMsg.setConditionValue("rptId01", RPT_ID);

        SVC_PRC_RNW_LTR_WRKTMsgArray result = (SVC_PRC_RNW_LTR_WRKTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return result;
    }

    /**
     * getSvcTermCondLtrWrkInfo
     * @param BigDecimal dsContrPk
     * @return SVC_PRC_RNW_LTR_WRKTMsgArray
     */
    public static SVC_TERM_COND_LTR_WRKTMsgArray getSvcTermCondLtrWrkInfo(NSAL0620CMsg cMsg, BigDecimal dsContrPk) {
        SVC_TERM_COND_LTR_WRKTMsg inTMsg = new SVC_TERM_COND_LTR_WRKTMsg();
        inTMsg.setSQLID("002");
        inTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inTMsg.setConditionValue("dsContrPk01", dsContrPk);
        inTMsg.setConditionValue("bizAppId01", APL_ID);

        SVC_TERM_COND_LTR_WRKTMsgArray result = (SVC_TERM_COND_LTR_WRKTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return result;
    }

    /**
     * getWrkDataForRegAndAgg
     * @param cMsg NSAL0620CMsg
     * @param dsContrPkList List < BigDecimal >
     * @param hasMtrFlg String
     * @return S21SsmEZDResult
     */
    //START 2021/09/10 L.Mandanas [QC#58314-1, MOD]
      //public S21SsmEZDResult getWrkDataForRegAndAgg(NSAL0620CMsg cMsg, List<BigDecimal> dsContrPkList) {
    public S21SsmEZDResult getWrkDataForRegAndAgg(final NSAL0620CMsg cMsg,
            final List < BigDecimal > dsContrPkList, final String hasMtrFlg) {
    //END 2021/09/10 L.Mandanas [QC#58314-1, MOD]
        Map<String, Object> params = new HashMap<String, Object>();
        BigDecimal[] dsContrListArray = (BigDecimal[]) dsContrPkList.toArray(new BigDecimal[0]);

        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), cMsg.glblCmpyCd.getValue());
        params.put(QUERY_PRM_ID.DS_CONTR_PK.getQueryPrm(), dsContrListArray);
        params.put(QUERY_PRM_ID.SVC_MACH_TP_CD_MACH.getQueryPrm(), SVC_MACH_TP.MACHINE);
        params.put(QUERY_PRM_ID.SVC_MACH_TP_CD_ACC.getQueryPrm(), SVC_MACH_TP.ACCESSORY);
        //START 2021/09/10 L.Mandanas [QC#58314-1, ADD]
        params.put(QUERY_PRM_ID.DS_CONTR_CATG_CD_AGG.getQueryPrm(), DS_CONTR_CATG.AGGREGATE);
        params.put("hasMtrFlg", hasMtrFlg);
        //END 2021/09/10 L.Mandanas [QC#58314-1, ADD]
        return getSsmEZDClient().queryObjectList("getWrkDataForRegAndAgg", params);
    }

    /**
     * getWrkDataForFlt
     * @param List<BigDecimal> dsContrDtlPkList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrkDataForFlt(NSAL0620CMsg cMsg, List<BigDecimal> dsContrPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        BigDecimal[] dsContrListArray = (BigDecimal[]) dsContrPkList.toArray(new BigDecimal[0]);

        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), cMsg.glblCmpyCd.getValue());
        params.put(QUERY_PRM_ID.DS_CONTR_PK.getQueryPrm(), dsContrListArray);
        params.put(QUERY_PRM_ID.SVC_MACH_TP_CD_MACH.getQueryPrm(), SVC_MACH_TP.MACHINE);
        params.put(QUERY_PRM_ID.SVC_MACH_TP_CD_ACC.getQueryPrm(), SVC_MACH_TP.ACCESSORY);
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_TP_CD_FLT.getQueryPrm(), DS_CONTR_DTL_TP.FLEET);
        
        
        return getSsmEZDClient().queryObjectList("getWrkDataForFlt", params);
    }

    /**
     * getWrkDataForTermAndCond
     * @param List<BigDecimal> dsContrDtlPkList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWrkDataForTermAndCond(NSAL0620CMsg cMsg, List<BigDecimal> dsContrPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        BigDecimal[] dsContrListArray = (BigDecimal[]) dsContrPkList.toArray(new BigDecimal[0]);

        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), cMsg.glblCmpyCd.getValue());
        params.put(QUERY_PRM_ID.DS_CONTR_PK.getQueryPrm(), dsContrListArray);
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_TP_CD_FLT.getQueryPrm(), DS_CONTR_DTL_TP.FLEET);
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_TP_CD_AGG.getQueryPrm(), DS_CONTR_DTL_TP.AGGREGATE);
        params.put(QUERY_PRM_ID.SLS_DT.getQueryPrm(), cMsg.slsDt);
        params.put(QUERY_PRM_ID.MAX_THRU_DT.getQueryPrm(), THRU_DT_LIMIT);

        return getSsmEZDClient().queryObjectList("getWrkDataForTermAndCond", params);
    }

    /**
     * getPrintData
     * @param List<BigDecimal> dsContrPkList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrintData(List<BigDecimal> dsContrPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(), getGlobalCompanyCode());
        BigDecimal[] dsContrListArray = (BigDecimal[]) dsContrPkList.toArray(new BigDecimal[0]);
        params.put(QUERY_PRM_ID.DS_CONTR_PK.getQueryPrm(), dsContrListArray);
        params.put(QUERY_PRM_ID.BIZ_APP_ID.getQueryPrm(), APL_ID);

        return getSsmEZDClient().queryObjectList("getPrintData", params);
    }
    //END 2017/08/21 E.Kameishi [QC#8661,ADD]

    // START 2018/06/26 T.Ogura [QC#26336,ADD]
    /**
     * createCSV
     * @param bizMsg NSAL0620CMsg
     * @param glblMsg NSAL0620SMsg
     */
    public void createCSV(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("rowNum", sMsg.F.length());
        // START 2023/06/28 T.Kimura [QC#58675, ADD]
        params.put("invTpCd", INV_TP.CREDIT_MEMO);
        // END 2023/06/28 T.Kimura [QC#58675, ADD]
        Set<String> svcMachMstrPkList = new HashSet<String>();
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);
        for (int row : selectedRows) {
            svcMachMstrPkList.add(sMsg.A.no(row).svcMachMstrPk_A.getValue().toString());
        }
        String[] paramsvcMachMstrPkList = new String[svcMachMstrPkList.size()];
        svcMachMstrPkList.toArray(paramsvcMachMstrPkList);
        params.put("svcMachMstrPkList", paramsvcMachMstrPkList);

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm(APL_ID);
            cMsg.xxFileData.setTempFilePath(null, csvFileNm, ".csv");

            stmtSelect = ssmLLClient.createPreparedStatement("getDownloadList", params, execParam);
            rs = stmtSelect.executeQuery();

            NSAL0620CommonLogic.writeMeter_HistoryCsvFile(cMsg, rs);
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }
    // END   2018/06/26 T.Ogura [QC#26336,ADD]
    // START 2021/02/02 Y.Penequito [QC#58312, ADD]
    /**
     * getBasePrcDealAmtTxtMainUnit
     * @param glblCmpyCd String
     * @param dsCntrPk BigDecimal
     * @return SVC_PRC_RNW_LTR_WRKTMsgArray
     */
    public SVC_PRC_RNW_LTR_WRKTMsgArray getBasePrcDealAmtTxtMainUnit(String glblCmpyCd, BigDecimal dsCntrPk) {
        SVC_PRC_RNW_LTR_WRKTMsg svcParam = new SVC_PRC_RNW_LTR_WRKTMsg();
        svcParam.setSQLID("001");
        svcParam.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcParam.setConditionValue("dsContrPk01", dsCntrPk);
        SVC_PRC_RNW_LTR_WRKTMsgArray result =  (SVC_PRC_RNW_LTR_WRKTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(svcParam);
        return result;
    }
    // END 2021/02/02 Y.Penequito [QC#58312, ADD]

    //START 2021/09/10 L.Mandanas [QC#58314-1, ADD]
    /**.
     * checkDsContrBllgMtr
     * @param cMsg NSAL0620CMsg
     * @param dsContrDtlPkList List < BigDecimal >
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkDsContrBllgMtr(final NSAL0620CMsg cMsg,
            final List < BigDecimal > dsContrDtlPkList) {
        Map < String, Object > params = new HashMap < String, Object>();
        BigDecimal[] dsContrDtlListArray =
            (BigDecimal[]) dsContrDtlPkList.toArray(new BigDecimal[0]);

        params.put(QUERY_PRM_ID.GLBL_CMPY_CD.getQueryPrm(),
                cMsg.glblCmpyCd.getValue());
        params.put(QUERY_PRM_ID.DS_CONTR_DTL_PK.getQueryPrm(),
                dsContrDtlListArray);

        return getSsmEZDClient().queryObjectList("checkDsContrBllgMtr", params);
    }
    //END 2021/09/10 L.Mandanas [QC#58314-1, ADD]
}
