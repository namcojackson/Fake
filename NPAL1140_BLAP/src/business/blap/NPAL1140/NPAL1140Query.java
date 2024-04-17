/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL1140;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import static business.blap.NPAL1140.constant.NPAL1140Constant.*;

import business.db.PO_ACK_DTL_WRKTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 06/11/2013   Hitachi         K.Kishimoto     Update          1233
 * 07/10/2013   Hitachi         K.Kishimoto     Update          QC1233
 * 07/26/2013   Hitachi         K.Kishimoto     Update          QC1388
 * 08/14/2013   Hitachi         K.Kishimoto     Update          QC1233
 * 04/28/2014   Hitachi         T.Kawazu        Update          ITG#506831
 *</pre>
 */
public class NPAL1140Query extends S21SsmEZDQuerySupport {
    /**
     * Singleton instance.
     */
    private static final NPAL1140Query MYINSTANCE = new NPAL1140Query();

    /**
     * Constructor
     */
    public NPAL1140Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWBL0180Query
     */
    public static NPAL1140Query getInstance() {
        return MYINSTANCE;
    }

    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Search ITRL_INTFC_ID
     * @return List<String>
     */
    public List<String> getIntdfcId() {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        @SuppressWarnings("unchecked")
        List<String> resultList = (List<String>) getSsmBatchClient().queryObjectList("getIntdfcId", ssmParam);
        if (resultList == null) {
            resultList = new ArrayList<String>();
        }
        return resultList;
    }

    /**
     * Search PO_ACK_HDR_WRK
     * @param poAckHdrWrkPk BigDecimal
     * @return result.getSearchList
     */
    public List<Map<String, Object>> getAckHeader(BigDecimal poAckHdrWrkPk, String ediPoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("poAckHdrWrkPk", poAckHdrWrkPk);
        ssmParam.put("asterisk", STR_ASTERISK);
        ssmParam.put("poOrdNumLength", String.valueOf(PO_ORD_NUM_LENGTH));
        ssmParam.put("ediPoOrdNum", ediPoOrdNum);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) getSsmBatchClient().queryObjectList("getAckHeader", ssmParam);
        if (resultList == null) {
            resultList = new ArrayList<Map<String, Object>>();
        }
        return resultList;
    }
    /**
     * Search PO_ACK_DTL_WRK
     * @param poAckHdrWrkPk BigDecimal
     * @return result.getSearchList
     */
    public List<Map<String, Object>> getAckDetial(BigDecimal poAckHdrWrkPk, String ediPoOrdNum, String ediPoOrdDtlLineNum) {

        PO_ACK_DTL_WRKTMsg inMsg = new PO_ACK_DTL_WRKTMsg();

        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("ediPoOrdNum01", poAckHdrWrkPk);

        int nullCnt = EZDTBLAccessor.count(inMsg);

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("poAckHdrWrkPk", poAckHdrWrkPk);
        if (nullCnt == 0) {
            ssmParam.put("xrefUseFlg", ZYPConstant.FLG_ON_Y);
        }
        ssmParam.put("rowNumSize", SELECT_MAX_ROW_COUNT + 1);
        ssmParam.put("ediPoOrdNum", ediPoOrdNum);
        ssmParam.put("ediPoOrdDtlLineNum", ediPoOrdDtlLineNum);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) getSsmBatchClient().queryObjectList("getAckDetial", ssmParam);

        if (resultList == null) {
            resultList = new ArrayList<Map<String, Object>>();
        }
        return resultList;
    }

    /**
     * Search List
     * @param sMsg NPAL1140SMsg
     * @return result.getSearchList S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchList(NPAL1140SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rowNumSize", SELECT_MAX_ROW_COUNT + 2);
        ssmParam.put("sMsg", sMsg);
        ssmParam.put("endTime", STR_END_TIME);

        return getSsmEZDClient().queryObjectList("getSearchList", ssmParam);
    }

    /**
     * Search PO_DTL
     * @param poOrdNum String
     * @param poOrdDtlLineNum String
     * @return result.getSearchList
     */
    public Map<String, Object> getLogicLineNum(String poOrdNum, String poOrdDtlLineNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("poOrdNum", poOrdNum);
        ssmParam.put("poOrdDtlLineNum", poOrdDtlLineNum);

        @SuppressWarnings("unchecked")
        Map<String, Object> resultList = (Map<String, Object>) getSsmBatchClient().queryObject("getLogicLineNum", ssmParam);

        if (resultList == null) {
            resultList = new HashMap<String, Object>();
        }
        return resultList;
    }
}
