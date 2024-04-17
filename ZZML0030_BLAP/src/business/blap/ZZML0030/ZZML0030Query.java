/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/06/2009   Fujitsu         T.Kawamura      Create          N/A
 * 03/03/2020   Fujitsu         K.takahama      Update          QC#56127
 *</pre>
 */
package business.blap.ZZML0030;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;

import business.blap.ZZML0030.constant.ZZML0030Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc; // 2013/04/15 ADD M.Sumida

public final class ZZML0030Query extends S21SsmEZDQuerySupport implements ZZML0030Constant {

    /**
     * Singleton instance.
     */
    private static final ZZML0030Query myInstance = new ZZML0030Query();

    /**
     * Constructor.
     */
    private ZZML0030Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0030Query
     */
    public static ZZML0030Query getInstance() {
        return myInstance;
    }

    /**
     * 画面入力された条件を元に検索し、一覧表のデータを取得します。
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getList(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_EZDCMSG, cMsg);

        // get Mail Send Date From & Mail Send Time From
        String updatedDateFrom = cMsg.xxFromDt_H.getValue();
        String updatedTimeFrom = cMsg.xxHrs_F.getValue();
        convDate(ssmParam, PM_UPD_DT_FROM, updatedDateFrom, PM_UPD_TM_FROM, updatedTimeFrom);

        // get Mail Send Date To & Mail Send Time To
        String updatedDateTo = cMsg.xxToDt_H.getValue();
        String updatedTimeTo = cMsg.xxHrs_T.getValue();
        convDate(ssmParam, PM_UPD_DT_TO, updatedDateTo, PM_UPD_TM_TO, updatedTimeTo);

        // time setting
        if (updatedDateFrom.length() > 0 && updatedDateTo.length() == 0) {
            if (updatedTimeFrom.length() > 0 && updatedTimeTo.length() > 0) {
                ssmParam.put(PM_UPD_TM_FROM, updatedTimeFrom + MMSS_ZERO);
            }
        } else if (updatedDateFrom.length() == 0 && updatedDateTo.length() > 0) {
            if (updatedTimeFrom.length() > 0 && updatedTimeTo.length() > 0) {
                ssmParam.put(PM_UPD_TM_TO, updatedTimeTo + MMSS_ZERO);
            }
        }
        
        // set mlSubjTxt
        ssmParam.put(PM_ML_SUBJ_TXT, cMsg.mlSubjTxt_H.getValue());

        // set mlUsrAddr
        ssmParam.put(PM_ML_USR_ADDR, cMsg.mlUsrAddr_H.getValue());

        // 03/03/2020 Mod QC#56127 Start
        // set mlUsrAddrTo
        ssmParam.put(PM_ML_USR_ADDR_TO, cMsg.mlUsrAddr_T.getValue());
        // 03/03/2020 Mod QC#56127 End

        EZDMsg.copy(cMsg, null, sMsg, null);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        return getSsmEZDClient().queryEZDMsgArray(QUERY_LIST, ssmParam, sMsg.A);
    }

    /**
     * 一覧表から指定されたメールの詳細データ(ユーザ情報)を取得します。
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param rownum int row number
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getMlUsr(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg, int rownum) {

        String glblCmpyCd = sMsg.glblCmpyCd.getValue();
        BigDecimal mlSendPk = sMsg.A.no(rownum).mlSendPk_A.getValue();
        BigDecimal mlStsPk = sMsg.A.no(rownum).mlStsPk_A.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(PM_EZDCMSG, cMsg);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put(PM_ML_SEND_PK, mlSendPk);
        ssmParam.put(PM_ML_STS_PK, mlStsPk);

        sMsg.B.clear();
        sMsg.B.setValidCount(0);

        return getSsmEZDClient().queryEZDMsgArray(QUERY_MLUSR, ssmParam, sMsg.B);
    }

    /**
     * 一覧表から指定されたメールの詳細データ(添付ファイル情報)を取得します。
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param rownum int row number
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getMlAtt(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg, int rownum) {

        String glblCmpyCd = sMsg.glblCmpyCd.getValue();
        BigDecimal mlSendPk = sMsg.A.no(rownum).mlSendPk_A.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(PM_EZDCMSG, cMsg);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put(PM_ML_SEND_PK, mlSendPk);

        sMsg.C.clear();
        sMsg.C.setValidCount(0);

        return getSsmEZDClient().queryEZDMsgArray(QUERY_MLATT, ssmParam, sMsg.C);
    }

    /**
     * 一覧表から指定されたメールの詳細データ(メール情報)を取得します。
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param rownum int row number
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getMlSend(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg, int rownum) {

        S21SsmEZDResult ssmResult = null;
        
        String glblCmpyCd = sMsg.glblCmpyCd.getValue();
        BigDecimal mlSendPk = sMsg.A.no(rownum).mlSendPk_A.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(PM_EZDCMSG, cMsg);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put(PM_ML_SEND_PK, mlSendPk);

        ssmResult = getSsmEZDClient().queryEZDMsg(QUERY_MLSEND, ssmParam, sMsg);
        if (ssmResult.isCodeNormal()) {
            // get body text
            ssmResult = this.getMlBodyTxt(QUERY_MLSEND_BODY, ssmParam, sMsg);
        }
        
        return ssmResult;
    }

    /**
     * get mail body
     * @param statementId String
     * @param ssmParam    Object
     * @param sMsg        ZZML0030SMsg
     * @return S21SsmEZDResult search result
     */
    private S21SsmEZDResult getMlBodyTxt(String statementId, Object ssmParam, ZZML0030SMsg sMsg) {
        
        // search execute
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject(statementId, ssmParam);
        if ( !ssmResult.isCodeNormal() ) {
            return ssmResult;
        }

        // get search result
        Map map = (Map) ssmResult.getResultObject();
        BigDecimal chunkSz = (BigDecimal) map.get(DB_CHUNK_SIZE);
        if ( chunkSz == null ) {
            sMsg.xxMlBodyTxt_01.clear();
            return ssmResult;
        }
        
        Clob clob = (Clob) map.get(DB_ML_BODY_TXT);
        
        // get clob data
        StringBuilder strMlBody = new StringBuilder();
        BufferedReader reader = null;
        try {
            if ( clob != null ) {
                reader = new BufferedReader(clob.getCharacterStream());
                
                char[] cbuffer = new char[chunkSz.intValue()];
                int length = -1;
                
                while( ( length = reader.read( cbuffer ) ) != -1 ) {

                    strMlBody.append( new String( cbuffer, 0, length ) );
                }
            }

            // START 2013/04/15 Mod M.Sumida
            // sMsg.xxMlBodyTxt_01.setValue(strMlBody.toString());
            int digit  = sMsg.getAttr("xxMlBodyTxt_01").getDigit()-1;
            if (strMlBody.length() > digit / 2) {
                sMsg.xxMlBodyTxt_01.setValue(ZYPCommonFunc.subByteString(strMlBody.toString(), digit));
            } else {
                sMsg.xxMlBodyTxt_01.setValue(strMlBody.toString());
            }
            // END   2013/04/15 Mod M.Sumida

        } catch (Exception e) {

            String msg = "Failed select. statementId=" + statementId + "\n param=" + ssmParam;
            throw new S21AbendException(e, msg);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {

                String msg = "Failed select. statementId=" + statementId + "\n param=" + ssmParam;
                throw new S21AbendException(e, msg);
            }
        }
        
        return ssmResult;
    }
    
    /**
     * 検索する日付文字列を設定する
     * @param ssmParam
     * @param dateName
     * @param dateVal
     * @param timeName
     * @param timeVal
     */
    private void convDate(Map<String, Object> ssmParam, String dateName, String dateVal, String timeName, String timeVal) {

        if (dateVal.length() > 0 && timeVal.length() == 0) {
            ssmParam.put(dateName, dateVal + HH24MMSSFF3_ZERO);
            ssmParam.put(timeName, null);
        } else if (dateVal.length() == 0 && timeVal.length() > 0) {
            ssmParam.put(dateName, null);
            ssmParam.put(timeName, timeVal + MMSS_ZERO);
        } else if (dateVal.length() > 0 && timeVal.length() > 0) {
            ssmParam.put(dateName, dateVal + timeVal + MMSSFF3_ZERO);
            ssmParam.put(timeName, null);
        }
    }

}
