/**
 * 
 */
package com.canon.cusa.s21.framework.generictable.ap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

/**
 * @author q02638
 *
 */
public class S21GMDBAccessQuery {

    /**
     * Singleton instance.
     */
    private static final S21GMDBAccessQuery myInstance = new S21GMDBAccessQuery();

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

    public static S21GMDBAccessQuery getInstance() {
        return myInstance;
    }

    public List getDefinedGmTableList() {    	
        return ssmBatchClient.queryObjectList("getDefinedGmTableList", null);
    }

    public String getGmTableLongName(String tableName) {
        Map<String, String> condition = new HashMap<String, String>();
        condition.put("genlTblNm", tableName);
        return (String)ssmBatchClient.queryObject("getGmTableLongName", condition);
    }

    public boolean isEditable (String tableName) {
        Map<String, String> condition = new HashMap<String, String>();
        condition.put("genlTblNm", tableName);
        Object flag = ssmBatchClient.queryObject("isEditable", condition);
        if (flag != null) {
            String strFlag = (String)flag;
            if (strFlag.equalsIgnoreCase("Y")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (tableName.equals("GENL_TBL_DFN")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 指定したテーブルの全カラム名とカラムにつけられているコメントを
     * 取得する
     * @param tableName テーブル物理名
     * @return カラム名をkey、カラムコメントをvalueとするHashMap
     */
    public Map getColumnCommentList(String tableName) {
        Map<String, String> condition = new HashMap<String, String>();
        Map<String, String> resultList = new HashMap<String, String>();
        condition.put("genlTblNm", tableName);
        List searchList = ssmBatchClient.queryObjectList("getColumnCommentList", condition);
        Iterator it = searchList.iterator();
        while (it.hasNext()) {
            Map columnComment = (Map) it.next();
            resultList.put((String) columnComment.get("COLUMN_NAME"), (String) columnComment.get("COMMENTS"));
        }
        return resultList;
    }

    /**
     * テーブルコメント一覧を取得する
     * @return テーブル名をkey、テーブルコメントをvalueとするHashMap
     */
    public Map getTableCommentList() {
        Map<String, String> resultList = new HashMap<String, String>();

        List searchList = ssmBatchClient.queryObjectList("getTableCommentList", null);
        Iterator it = searchList.iterator();
        while (it.hasNext()) {
            Map tableComment = (Map) it.next();
            resultList.put((String) tableComment.get("TABLE_NAME"), (String) tableComment.get("COMMENTS"));
        }
        return resultList;
    }

    /**
     * 部分一致検索を行う
     * @param tableName テーブル名
     * @param condition 検索条件
     * @param bean 入力データbean
     * @param start 検索結果開始番号(offset番号)
     * @param len 検索結果返却件数(limit値)
     * @return 検索結果オブジェクト(EZDTMsg)のリスト
     */
    public EZDTMsgArray getByConditionRowNum(String tableName, EZDTMsg condition, S21NEContainerDataBean bean, int start, int len) {

        Map<String, Object> searchCondition = new HashMap<String, Object>();
        // テーブル名の設定
        searchCondition.put("tableName", tableName);
        // 検索条件の設定
        searchCondition.put("whereCondition", getSearchConditionList(condition, true));
        // オフセットの指定
        searchCondition.put("start", start);
        // 取得件数の指定
        searchCondition.put("len", start + len);

        List searchList = ssmBatchClient.queryObjectList("findByConditionRowNum", searchCondition);

        // 返却する配列
        ArrayList rsltMsgList = new ArrayList();

        Iterator it1 = searchList.iterator();
        while (it1.hasNext()) {
            Map searchResult = (Map) it1.next();
            EZDTMsg msg = S21CMCommonRequestFunc.createEZDTMsgAll(tableName, bean, searchResult);
            rsltMsgList.add(msg);
        }

        // 返却するレスポンス
        EZDTMsgArray rtnArray = (EZDTMsgArray) condition.getMyArray();
        rtnArray.setMsgList((EZDTMsg[]) rsltMsgList.toArray(new EZDTMsg[0]));

        return rtnArray;
    }

    /**
     * 検索条件に一致する件数を取得する
     * @param tableName テーブル名
     * @param condition 検索条件
     * @return 検索条件に一致するレコード件数
     */
    public int countByCondition(String tableName, EZDTMsg condition) {
        Map<String, Object> searchCondition = new HashMap<String, Object>();
        // テーブル名の設定
        searchCondition.put("tableName", tableName);
        // 検索条件
        searchCondition.put("whereCondition", getSearchConditionList(condition, true));

        ArrayList[] keys = condition.getKeyColumnList();
        String countKey = condition.getDBColumnName((String) keys[0].get(0));

        searchCondition.put("keycolumn", countKey);

        int counter = (Integer) ssmBatchClient.queryObject("countCondition", searchCondition);

        return counter;
    }
    
    /**
     * 編集対象のレコード情報を取得
     * @param tableName
     * @param condition
     * @param bean
     * @return
     */
    public EZDTMsg getEditRecord(String tableName, EZDTMsg condition, S21NEContainerDataBean bean) {

        Map<String, Object> searchCondition = new HashMap<String, Object>();
        // テーブル名の設定
        searchCondition.put("tableName", tableName);
        // 検索条件の設定
        searchCondition.put("whereCondition", getSearchConditionList(condition, false));

        List searchList = ssmBatchClient.queryObjectList("findByKey", searchCondition);

        EZDTMsg msg = null;
        Iterator it1 = searchList.iterator();
        while (it1.hasNext()) {
            Map searchResult = (Map) it1.next();
            msg = S21CMCommonRequestFunc.createEZDTMsgAll(tableName, bean, searchResult);
        }
        return msg;
    }
    
    /**
     * 部分一致検索条件(Where句)を生成するためのリストを取得する
     * @param condition 検索条件EZDTMsgオブジェクト
     * @return 検索条件配列
     */
    private List<Object> getSearchConditionList(EZDTMsg condition, boolean partialMatch) {
        ArrayList[] cols = condition.getSelectColumnList();
        ArrayList conditionList = new ArrayList();

        ArrayList k = cols[0];
        Iterator it = k.iterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            String v = (String) condition.getDBValue(s);
            if (v != null) {
                StringBuffer conditionValue = new StringBuffer();
                if (partialMatch) {
                    conditionValue.append("%").append(v).append("%");
                } else {
                    conditionValue.append(v);
                }
                Map<String, String> conditionKeyValue = new HashMap<String, String>();
                conditionKeyValue.put("key", condition.getDBColumnName(s));
                conditionKeyValue.put("value", conditionValue.toString());
                conditionList.add(conditionKeyValue);
            }
        }
        return conditionList;
    }
}
