/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7090;

import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_GLBL_CMPY_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_MDSE_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_PRC_CATG_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_PRC_LIST_ACT_TP_DESC_TXT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_PRC_LIST_TP_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_PRC_LIST_TP_DESC_TXT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_PRC_QLFY_TP_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_PRC_QLFY_VAL_TXT;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_RQST_STS_TP_CD_NEW;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_RQST_STS_TP_CD_REQUESTED;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_SUPD_FROM_MDSE_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.DB_PRM_SUPD_TO_MDSE_CD;
import static business.blap.NMAL7090.constant.NMAL7090Constant.MAX_ROWS;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_STS_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NMAL7090 Item Supersessions Mass Add
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/05/06   CITS            S.Tanikawa      UPDATE          QC#7724
 *</pre>
 */
public final class NMAL7090Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NMAL7090Query MY_INSTANCE = new NMAL7090Query();

    /**
     * Constructor.
     */
    private NMAL7090Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL7090Query
     */
    public static NMAL7090Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of PRC_LIST_TP
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListTp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPrcListTpPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of PRC_LIST_ACT_TP
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListActTp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getPrcListActTpPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of RQST_STS_TP
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRqstStsTp(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getRqstStsTpPulldownList", ssmParam);
    }

    /**
     * getItemList: get Item List Table A
     * @param bizMsg NMAL7090CMsg
     * @param glblMsg NMAL7090SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemList(NMAL7090CMsg bizMsg, NMAL7090SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rowNum", MAX_ROWS + 1);
        params.put("cMsg", bizMsg);
        params.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        // UPDATE START 2016/05/06 QC#7724
        params.put(DB_PRM_PRC_QLFY_TP_CD, PRC_QLFY_TP.ITEM_CODE);
        // UPDATE END 2016/05/06 QC#7724

        if (ZYPConstant.FLG_ON_Y.equals(glblMsg.xxYesNoCd_FA.getValue())) {
            params.put("filterOldItemNumber", glblMsg.supdFromMdseCd_FA.getValue());
            params.put("filterOldItemDescription", glblMsg.mdseDescShortTxt_FO.getValue());
            params.put("filterNewItemNumber", glblMsg.supdToMdseCd_FA.getValue());
            params.put("filterNewItemDescription", glblMsg.mdseDescShortTxt_FN.getValue());
            params.put("filterAddDate", glblMsg.supdCratDt_FA.getValue());
        }

        int skipRows = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;

        return getSsmEZDClient().queryEZDMsgArray("getItemList", params, skipRows, glblMsg.A.length(), glblMsg.A);
    }

    /**
     * getCntPrcListEquip
     * @param itemNum String
     * @return integer
     */
    public Integer getCntPrcListEquip(String itemNum) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        queryParams.put(DB_PRM_PRC_QLFY_VAL_TXT, itemNum);
        queryParams.put(DB_PRM_PRC_QLFY_TP_CD, PRC_QLFY_TP.ITEM_CODE);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCntPrcListEquip", queryParams);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    /**
     * getCntPrcListEquipReq
     * @param supdFrom String
     * @param supdTo   String
     * @return Integer
     */
    public Integer getCntPrcListEquipReq(String supdFrom, String supdTo) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        queryParams.put(DB_PRM_SUPD_FROM_MDSE_CD, supdFrom);
        queryParams.put(DB_PRM_SUPD_TO_MDSE_CD, supdTo);
        queryParams.put(DB_PRM_RQST_STS_TP_CD_NEW, RQST_STS_TP.NEW);
        queryParams.put(DB_PRM_RQST_STS_TP_CD_REQUESTED, RQST_STS_TP.REQUESTED);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCntPrcListEquipReq", queryParams);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    /**
     * getNewRequest
     * @param bizMsg NMAL7090CMsg
     * @param glblMsg NMAL7090SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNewRequest(NMAL7090CMsg bizMsg, NMAL7090SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rowNum", MAX_ROWS + 1);
        params.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(DB_PRM_RQST_STS_TP_CD_NEW, RQST_STS_TP.NEW);

        if (ZYPConstant.FLG_ON_Y.equals(glblMsg.xxYesNoCd_FB.getValue())) {
            params.put("filter_oldMdseCd", glblMsg.oldMdseCd_FB.getValue());
            params.put("filter_oldMdseDescShortTxt", glblMsg.oldMdseDescShortTxt_FB.getValue());
            params.put("filter_newMdseCd", glblMsg.newMdseCd_FB.getValue());
            params.put("filter_newMdseDescShortTxt", glblMsg.newMdseDescShortTxt_FB.getValue());
            params.put("filter_prcListTpCd", glblMsg.prcListTpCd_FS.getValue());
            params.put("filter_prcListActTpCd", glblMsg.prcListActTpCd_FS.getValue());
        }
        return getSsmEZDClient().queryEZDMsgArray("getNewRequest", params, glblMsg.B);
    }

    /**
     * getHistRequest
     * @param bizMsg NMAL7090CMsg
     * @param glblMsg NMAL7090SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHistRequest(NMAL7090CMsg bizMsg, NMAL7090SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rowNum", MAX_ROWS + 1);
        params.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        params.put(DB_PRM_RQST_STS_TP_CD_NEW, RQST_STS_TP.NEW);
        if (ZYPConstant.FLG_ON_Y.equals(glblMsg.xxYesNoCd_FC.getValue())) {
            params.put("filter_oldMdseCd", glblMsg.oldMdseCd_FC.getValue());
            params.put("filter_oldMdseDescShortTxt", glblMsg.oldMdseDescShortTxt_FC.getValue());
            params.put("filter_newMdseCd", glblMsg.newMdseCd_FC.getValue());
            params.put("filter_newMdseDescShortTxt", glblMsg.newMdseDescShortTxt_FC.getValue());
            params.put("filter_prcListTpCd", glblMsg.prcListTpCd_CS.getValue());
            params.put("filter_prcListActTpCd", glblMsg.prcListActTpCd_CS.getValue());
            params.put("filter_newPrcAmt", glblMsg.newPrcAmt_FC.getValue());
            params.put("filter_rqstStsTpCd", glblMsg.rqstStsTpCd_CS.getValue());
            params.put("filter_prcListEquipRqstPk", glblMsg.xxAuthByNm_FC.getValue());
            params.put("filter_rqstStsTpCd_LogFile", RQST_STS_TP.REQUESTED);
        }
        int skipRows = bizMsg.xxPageShowFromNum_C.getValueInt() - 1;

        return getSsmEZDClient().queryEZDMsgArray("getHistRequest", params, skipRows, glblMsg.C.length(), glblMsg.C);
    }

    /**
     * getCntPrcListTpCd
     * @param line NMAL7090_BSMsg
     * @return Integer
     */
    public Integer getCntPrcListTpCd(NMAL7090_BSMsg line) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PRM_PRC_LIST_TP_CD, line.prcListTpCd_BS);

        String[] prcLists = line.prcListsDescTxt_B.getValue().split(",", 0);
        String strId = "";
        for (int k = 0; k < prcLists.length; k++) {
            if (k == 0) {
                strId = "'";
            } else {
                strId += ",'";
            }
            strId += prcLists[k] + "'";
        }
        ssmParam.put(DB_PRM_PRC_CATG_CD, strId);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCntPrcListTpCd", ssmParam);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    /**
     * getCntOrdTakeMdse
     * @param mdseCd String
     * @return Integer
     */
    public Integer getCntOrdTakeMdse(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PRM_MDSE_CD, mdseCd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCntOrdTakeMdse", ssmParam);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    /**
     * getCntMdse
     * @param mdseCd String
     * @return Integer
     */
    public Integer getCntMdse(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PRM_MDSE_CD, mdseCd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCntMdse", ssmParam);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    /**
     * getPrcListTpCd
     * @param prcListTpDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListTpCd(String prcListTpDescTxt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PRM_PRC_LIST_TP_DESC_TXT, prcListTpDescTxt);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getPrcListTpCd", ssmParam);

        return ssmResult;

    }

    /**
     * getPrcListActTpCd
     * @param prcListActTpDescTxt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcListActTpCd(String prcListActTpDescTxt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PRM_PRC_LIST_ACT_TP_DESC_TXT, prcListActTpDescTxt);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getPrcListActTpCd", ssmParam);

        return ssmResult;

    }

    /**
     * getItemInfo
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemInfo(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PRM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PRM_MDSE_CD, mdseCd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getItemInfo", ssmParam);

        return ssmResult;
    }
}
