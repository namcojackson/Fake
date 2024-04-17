package business.blap.ZZML0040;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;

import business.blap.ZZML0040.constant.ZZML0040Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZML0040Query extends S21SsmEZDQuerySupport implements ZZML0040Constant {

    /**
     * Singleton instance.
     */
    private static final ZZML0040Query myInstance = new ZZML0040Query();

    /**
     * Constructor.
     */
    private ZZML0040Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return ZZIL0030Query
     */
    public static ZZML0040Query getInstance() {
        return myInstance;
    }

    /**
     * 画面入力された条件を元に検索し、一覧表のデータを取得します。
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getList(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        // set glblCmpyCd
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());

        // set mlTmplId
        ssmParam.put(PM_ML_TMPL_ID, cMsg.mlTmplId.getValue());

        // set mlSubjTxt
        ssmParam.put(PM_ML_SUBJ_TMPL_TXT, cMsg.mlSubjTmplTxt.getValue());

        EZDMsg.copy(cMsg, null, sMsg, null);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        return getSsmEZDClient().queryEZDMsgArray(QUERY_LIST, ssmParam, sMsg.A);
    }

    /**
     * 一覧表から指定されたメールテンプレートの詳細データを取得します。
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param rownum int row number
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getDetail(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg, int rownum) {

        S21SsmEZDResult ssmResult = null;
        
        String glblCmpyCd = sMsg.glblCmpyCd.getValue();
        String mlTmplId = sMsg.A.no(rownum).mlTmplId_A.getValue();
        String mlLocId = sMsg.A.no(rownum).mlLocId_A.getValue();

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(PM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(PM_ML_TMPL_ID, mlTmplId);
        ssmParam.put(PM_ML_LOC_ID, mlLocId);
        
        // get search result
        ssmResult = getSsmEZDClient().queryEZDMsg(QUERY_DETAIL, ssmParam, cMsg);
        if (ssmResult.isCodeNormal()) {
            // get body text
            ssmResult = this.getMlBodyTxt(QUERY_DETAIL_MLBODY, ssmParam, cMsg);
        }

        return ssmResult;
    }

    /**
     * get mail template body
     * @param statementId String
     * @param ssmParam    Object
     * @param cMsg        ZZML0040CMsg
     * @return S21SsmEZDResult search result
     */
    private S21SsmEZDResult getMlBodyTxt(String statementId, Object ssmParam, ZZML0040CMsg cMsg) {
        
        // search execute
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject(statementId, ssmParam);
        if ( !ssmResult.isCodeNormal() ) {
            return ssmResult;
        }

        // get search result
        Map map = (Map) ssmResult.getResultObject();
        
        if (map.get(DB_CHUNK_SIZE) != null) {
            int size = ((BigDecimal) map.get(DB_CHUNK_SIZE)).intValue();
            Clob clob = (Clob) map.get(DB_ML_BODY_TMPL_TXT);
            
            // get clob data
            StringBuilder strMlBody = new StringBuilder();
            BufferedReader reader = null;
            try {
                if ( clob != null ) {
                    reader = new BufferedReader(clob.getCharacterStream());
                    
                    char[] cbuffer = new char[size];
                    int length = -1;
                    
                    while( ( length = reader.read( cbuffer ) ) != -1 ) {

                        strMlBody.append( new String( cbuffer, 0, length ) );
                    }
                }

                cMsg.xxMlBodyTxt_01.setValue(strMlBody.toString());

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
        }
        return ssmResult;
    }
    
    /**
     * get Language List
     * @param cMsg ZZML0040CMsg
     * @return S21SsmEZDResult search result
     */
    public S21SsmEZDResult getLangList(ZZML0040CMsg cMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList(QUERY_LANGLIST, ssmParam);
    }

    /**
     * update ML_BODY_TMPL_TXT of ML_TMPL
     * @param cMsg
     * @return S21SsmEZDResult search result
     */
    public int updMlBody(ZZML0040CMsg cMsg) {

        // set parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(PM_ML_BODY_TMPL_TXT, cMsg.xxMlBodyTxt_01.getValue());
        ssmParam.put(PM_GLBL_CMPY_CD, cMsg.glblCmpyCd_01.getValue());
        ssmParam.put(PM_ML_TMPL_ID, cMsg.mlTmplId_01.getValue());
        ssmParam.put(PM_ML_LOC_ID, cMsg.mlLocId_01.getValue());
        
        return ZZML0040QuerySupport.getClient(this.getClass()).update(QUERY_UPDATE, ssmParam);
    }
    
}
