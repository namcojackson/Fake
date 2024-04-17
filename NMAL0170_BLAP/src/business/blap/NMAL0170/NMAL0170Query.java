/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0170;

import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_ID_FINDSUPDRELNSTAGEPK;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_ID_SEARCH;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_FROM_DT;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_GLBLCMPYCD;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_ITEMCLSTP;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_ITEMTP;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_ITEM_NUMBER;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_ROWNUM;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_SUBMITFLG;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_SUPDCD;
import static business.blap.NMAL0170.constant.NMAL0170Constant.SQL_PRM_TO_DT;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL0170Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Arima         Create          N/A
 * 2015/12/11   Fujitsu         T.Arima         Update          for CSA
 * 2016/01/04   Fujitsu         M.Nakamura       Update          QC#2528
 * 2016/02/23   CITS            S.Tanikawa       Update          QC#2669
 * 2016/03/17   CITS            S.Tanikawa       Update          QC#5353
 * 2016/04/21   CITS            S.Tanikawa       Update          QC#6176
 *</pre>
 */
public final class NMAL0170Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL0170Query MY_INSTANCE = new NMAL0170Query();

    /**
     * Private constructor
     */
    private NMAL0170Query() {
        super();
    }

    /**
     * Get the NMAL0170Query instance.
     * @return NMAL0170Query instance
     */
    public static NMAL0170Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search SUPD_RELN_STAGE
     * @param bizMsg NMAL0170CMsg
     * @param glblMsg NMAL0170SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SQL_PRM_GLBLCMPYCD,  getGlobalCompanyCode());
        params.put(SQL_PRM_ROWNUM,      glblMsg.A.length());
        params.put(SQL_PRM_ITEM_NUMBER, bizMsg.supdToMdseCd);
        params.put(SQL_PRM_FROM_DT,     bizMsg.supdRelnStageDt_FM);
        params.put(SQL_PRM_TO_DT,       bizMsg.supdRelnStageDt_TO);
        params.put(SQL_PRM_ITEMCLSTP,   bizMsg.mdseItemClsTpCd_H1);
        params.put(SQL_PRM_ITEMTP,      bizMsg.mdseItemTpCd_H1);
        // ADD START 2016/03/17 QC#5353
        params.put(SQL_PRM_SUBMITFLG,   ZYPConstant.FLG_OFF_N);
        // ADD END   2016/03/17 QC#5353
        //ADD START 12/11/2015
//        params.put(SQL_PRM_SUBMITFLG,   FLG_OFF_N);
        //ADD END 12/11/2015
        S21SsmEZDResult retVal = getSsmEZDClient().queryEZDMsgArray(SQL_ID_SEARCH, params, glblMsg.A);

        return retVal;
    }

    /**
     * ExistSupdRelnStagePk
     * @param lineMsg scrnMsg.A
     * @return exist SupdRelnStagePk
     */
    public S21SsmEZDResult existSupdRelnStagePk(NMAL0170_ACMsg lineMsg) {
        if (!ZYPCommonFunc.hasValue(lineMsg.supdFromMdseCd_A1) || !ZYPCommonFunc.hasValue(lineMsg.supdToMdseCd_A1)) {
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        params.put(SQL_PRM_ITEM_NUMBER, lineMsg.supdToMdseCd_A1);
        // DEL START 2016/01/04 QC#2528
//        params.put(SQL_PRM_SUBMITFLG, FLG_OFF_N);
        // DEL END   2016/01/04 QC#2528
        // ADD START 2016/03/17 QC#5353
        params.put(SQL_PRM_SUBMITFLG,   ZYPConstant.FLG_OFF_N);
        // ADD END   2016/03/17 QC#5353

        params.put(SQL_PRM_SUPDCD, lineMsg.supdFromMdseCd_A1);
        //DELETE START 12/11/2015
        //params.put(SQL_PRM_ITEMTP, lineMsg.mdseItemTpCd_A1);
        //params.put(SQL_PRM_ITEMCLSTP, lineMsg.mdseItemClsTpCd_A1);
        //DELETE END 12/11/2015
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList(SQL_ID_FINDSUPDRELNSTAGEPK, params);

        return ssmResult;

    }

    // ADD START 2016/02/23 QC#2669
    /**
     * getCountSupdReln
     * @param lineMsg NMAL0170_ACMsg
     * @return Integer
     */
    public Integer getCountSupdReln(NMAL0170_ACMsg lineMsg) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        queryParams.put(SQL_PRM_ITEM_NUMBER, lineMsg.supdToMdseCd_A1);
        queryParams.put(SQL_PRM_SUPDCD, lineMsg.supdFromMdseCd_A1);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCountSupdReln", queryParams);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    /**
     * getCountSupdRelnStage
     * @param lineMsg NMAL0170_ACMsg
     * @return Integer
     */
    public Integer getCountSupdRelnStage(NMAL0170_ACMsg lineMsg) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        queryParams.put(SQL_PRM_ITEM_NUMBER, lineMsg.supdToMdseCd_A1);
        queryParams.put(SQL_PRM_SUPDCD, lineMsg.supdFromMdseCd_A1);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCountSupdRelnStage", queryParams);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    /**
     * getCountSupdFrom
     * @param lineMsg NMAL0170_ACMsg
     * @return Integer
     */
    public Integer getCountSupdFrom(NMAL0170_ACMsg lineMsg) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        queryParams.put(SQL_PRM_SUPDCD, lineMsg.supdFromMdseCd_A1);
        queryParams.put(SQL_PRM_SUBMITFLG, ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getCountSupdFrom", queryParams);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        return resCnt;
    }

    /**
     * getSupdRelnList
     * @param lineMsg NMAL0170_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSupdRelnList(NMAL0170_ACMsg lineMsg) {

        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        queryParams.put(SQL_PRM_SUPDCD, lineMsg.supdFromMdseCd_A1);
        queryParams.put(SQL_PRM_SUBMITFLG, ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getSupdRelnList", queryParams);

        return ssmResult;
    }
    // ADD END 2016/02/23 QC#2669
    // ADD START 2016/04/21 QC#6176
    /**
     * getItemDescription
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemDescription(String mdseCd) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put(SQL_PRM_GLBLCMPYCD, getGlobalCompanyCode());
        queryParams.put(SQL_PRM_SUPDCD, mdseCd);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getItemDescription", queryParams);

        return ssmResult;
    }
    // ADD END 2016/04/21 QC#6176
}
