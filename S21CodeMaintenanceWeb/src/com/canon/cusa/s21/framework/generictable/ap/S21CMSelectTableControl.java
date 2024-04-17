package com.canon.cusa.s21.framework.generictable.ap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;

/**
 * コードメンテナンス画面 テーブル一覧選択イベント。<br>
 * テーブル選択と条件指定検索の双方で使用する。<br>
 * @author Administrator
 */
public class S21CMSelectTableControl extends S21CMSearchtScreenCommonControl {

    /**
     * HTTPリクエストに対する処理を行う。<br>
     * @param bean リクエスト情報コンテナ
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEBaseReqControl#control(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    @Override
    protected void control(S21NEContainerDataBean bean) {

        // 業務処理起動
        fireBiz(bean);

        // 業務処理結果をコンテナのレスポンスから取得
        S21CMSearchListBean result = (S21CMSearchListBean) bean.getResponse();

        // 表示用Beanに詰め替え
        S21CMSearchScreenBean view = new S21CMSearchScreenBean();
        view.setTableNames(result.getTableNames());
        view.setTableLongNames(result.getTableLongNames());
        view.setSearchTableComments(result.getSearchTableComments());
        view.setDisplayNumber(result.getDisplayNumber());

        // 選択情報
        view.setTableName(result.getTableName());
        view.setTableLongName(result.getTableLongName());
        view.setCondSearch(result.getCondition() != null);

        // レコード情報を設定する
        setPage(bean, result, view);

        // 検索条件を表示する
        view.setShowCondition(true);

        // 検索結果を表示する
        view.setShowResult(true);

        // 編集可能として表示する
        view.setUpdatable(isUpdatable(result.getTableName()));

        // レスポンスとして設定
        bean.setResponse(view);
    }

    /**
     * カラム情報から非EZDカラムのみを抽出する。<br>
     * @param columns 全カラム情報配列
     * @return 非EZDカラム情報配列
     */
    protected List<S21CodeTableColumnInfo> chooseNoEzdColumns(List<S21CodeTableColumnInfo> columns) {

        List<S21CodeTableColumnInfo> list = new ArrayList<S21CodeTableColumnInfo>();

        Iterator<S21CodeTableColumnInfo> it = columns.iterator();

        while (it.hasNext()) {
            S21CodeTableColumnInfo column = it.next();
            if (!column.isEzd()) {
                list.add(column);
            }
        }

        return list;
    }

    /**
     * EZDTMsgArrayの内容を配列に展開する。<br>
     * すべてのレコードを展開する。<br>
     * @param msgs EZDTMsgArray
     * @param columns カラム情報
     * @return EZDTMsgArrayを展開した配列
     */
    protected List<List<String>> convertMsgArray2List(EZDTMsgArray msgs, List<S21CodeTableColumnInfo> columns) {
        return convertMsgArray2List(msgs, columns, 0, msgs.length());
    }

    /**
     * EZDTMsgArrayの内容を配列に展開する。<br>
     * 指定レコードの内容を展開する。<br>
     * @param msgs EZDTMsgArray
     * @param columns カラム情報
     * @param 開始レコード
     * @param レコード数
     * @return EZDTMsgArrayを展開した配列
     */
    protected List<List<String>> convertMsgArray2List(EZDTMsgArray msgs, List<S21CodeTableColumnInfo> columns,
                                                    int start, int length) {

        // 全体を保持するList
        List<List<String>> list = new ArrayList<List<String>>(length);

        int max = start + length;

        if (max > msgs.length()) {
            max = msgs.length();
        }

        for (int i = start; i < max; i++) {

            // 1レコードのデータを保持するList
            List<String> row = new ArrayList<String>(columns.size());

            EZDTMsg msg = (EZDTMsg) msgs.get(i);

            Iterator<S21CodeTableColumnInfo> it = columns.iterator();

            while (it.hasNext()) {
                // 対象カラムのデータを抜き出しListに設定
                S21CodeTableColumnInfo column = it.next();

                String val = null;

                if (column.getItemType() == S21CodeTableColumnInfo.TYPE_BIGDECIMAL) {
                    BigDecimal v = msg.getValueBigDecimal(column.getKeyName(), -1);
                    if (v != null) {
                        val = v.toPlainString();
                    }
                } else {
                    val = msg.getValueString(column.getKeyName(), -1);
                }

                row.add(val);
            }

            // 1レコードのデータを設定
            list.add(row);
        }

        return list;
    }

    /**
     * 入力値の情報からEZDTmsg相当の情報を抜き出し、Mapに設定する。<br>
     * Mapはカラム名をキーに値を設定する。<br>
     * @param bean S21NEContainerDataBean
     * @param columns カラム情報
     * @return EZDTMsgのMap展開
     */
    protected Map<String, String> convertInpCond2Map(S21NEContainerDataBean bean, List<S21CodeTableColumnInfo> columns) {

        Map<String, String> map = new HashMap<String, String>();

        Iterator<S21CodeTableColumnInfo> it = columns.iterator();

        while (it.hasNext()) {
            // 対象カラムのデータを抜き出す
            S21CodeTableColumnInfo column = it.next();

            if (!column.isEzd()) {
                String val = bean.getReq(S21CMRequestDef.REQ_EZMSG + column.getColumnName());
                if (val != null && !val.equals("")) {
                    map.put(column.getColumnName(), val);
                }
            }
        }

        return map;
    }

    /**
     * 指定のテーブルを更新可能であるかチェックする。<br>
     * @param tableName テーブル名称
     * @return 更新可否
     */
    private boolean isUpdatable(String tableName) {
        // 更新可能であるかチェックする
//        return S21CMSecurityAccessor.getInstance().isTableWrite(tableName);
    	return S21GMDBAccessQuery.getInstance().isEditable(tableName);
    }

    /**
     * ページ情報を設定する。<br>
     * @param screen 画面名称
     * @param result 業務処理結果
     * @param view 表示データBean
     */
    protected void setPage(S21NEContainerDataBean bean, S21CMSearchListBean result, S21CMSearchScreenBean view) {

        // 表示行数
        //int line = S21NEConfig.getInstance().getDispLine(bean.getScreenID());
    	// 表示件数初期値を 50 に設定
    	int line = 20;
    	if (result.getDisplayNumber() > 0) {
    		line = result.getDisplayNumber();
    	}

        // ページ表示
        view.setPage(result.getViewPage() + 1);
//        int allPage = result.getMsgs().length() / line;
        //[Fix] Page Count Logic [START] 
//      int allPage = result.getAllRecord() / line;
//      if (allPage == 0
//              || result.getMsgs().length() % (allPage * line) > 0) {
//          allPage += 1;
//      }
      
      int allPage = result.getAllRecord() / line;
      if (allPage == 0 
              || result.getAllRecord() % line > 0){
          allPage += 1;
      }
      //[Fix] Page Count Logic [END] 
        view.setPageAll(allPage);

        // 全レコード数
        view.setAllRecord(result.getAllRecord());

        // 非EZDカラム情報を設定
//        List<S21CodeTableColumnInfo> columns = chooseNoEzdColumns(result.getColInfo());
//        view.setColInfos(columns);
        List<S21CodeTableColumnInfo> columns = result.getColInfo();
        view.setColInfos(columns);
        
        // カラムコメントを追加する
        //Map columnComment = new HashMap<String, String>();
        //columnComment.put("test_key", "test_value");
        Map columnComment = result.getColumnComment();
        view.setColumnComment(columnComment);

        // ソートを行う
//        EZDTMsgArray sortMsg = sortMsg(result);
        EZDTMsgArray sortMsg = result.getMsgs();

        // EZDTMsgの内容を設定
//        view.setValues(convertMsgArray2List(sortMsg, columns, result.getViewPage() * line, line));
        view.setValues(convertMsgArray2List(sortMsg, columns));

        // 検索条件を設定
        view.setCondition(convertInpCond2Map(bean, columns));
    }
//
//    /**
//     * 処理結果のEZDTMsgArrayをソートする。<br>
//     * ソートキーがある場合は、コードテーブルアクセッサにてソートされるため
//     * 何も処理しない。<br>
//     * ソートキーが存在しない場合は、キーカラムをソートする。<br>
//     * @param result
//     * @return
//     */
//    private EZDTMsgArray sortMsg(S21CMSearchListBean result) {
//
//        EZDTMsgArray sortMsg;
//        boolean ownSortCol = false;
//        String tableName = result.getTableName();
//
//        Iterator<S21CodeTableColumnInfo> it = result.getColInfo().iterator();
//
//        while (it.hasNext()) {
//            S21CodeTableColumnInfo info = it.next();
//            if (info.isS21sort()) {
//                ownSortCol = true;
//                break;
//            }
//        }
//
//        if (ownSortCol) {
//            sortMsg = result.getMsgs();
//        } else {
//            sortMsg = S21MsgAccessor.getInstance().sortKey(tableName, result.getMsgs());
//        }
//
//        return sortMsg;
//    }

    /**
     * 業務ID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 業務ID
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getEzBizID(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getEzBizID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_BIZ_ID;
    }

    /**
     * アプリケーションID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return アプリケーションID
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getEzAplID(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getEzAplID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_APL_ID_MANAGER;
    }

    /**
     * 画面ID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面ID
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getEzScreenID(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getEzScreenID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_ID_SEARCH;
    }

    /**
     * 画面名称取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面名称
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEReqControl#getScreenName(com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public String getScreenName(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_NAME_SEARCH;
    }
}
