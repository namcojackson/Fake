package com.canon.cusa.s21.framework.generictable.ap;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;

/**
 * コードメンテナンス画面 検索画面入力情報チェック。<br>
 * 検索条件なしでの検索用。<br>
 * @author Administrator
 */
public class S21CMSearchValidator extends S21CMBaseValidator {

    /**
     * 検索画面の入力内容をチェックする。<br>
     * @param bean リクエスト情報を保持するコンテナBean
     * @see com.canon.cusa.s21.framework.generictable.fw.S21NEValidator#valid(javax.servlet.http.HttpServletRequest, com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean)
     */
    public void valid(S21NEContainerDataBean bean) {

        // 検索画面の入力情報を保持するBeanを作成
        S21CMSearchInputBean sib = createInputBean();

        // コンテナに入力情報を保持するBeanを設定
        bean.setRequest(sib);

        // 共通リクエストを解析
        String tableName = S21CMCommonRequestFunc.getReqString(bean, S21CMRequestDef.REQ_SHOW_TABLE);
        if (tableName == null || tableName.equals("")) {
            tableName = S21CMCommonRequestFunc.getReqString(bean, S21CMRequestDef.REQ_SELECT_TABLE);
        }

        sib.setTableName(tableName);
        sib.setTableSearchKey(S21CMCommonRequestFunc.getReqString(bean, S21CMRequestDef.REQ_SEARCH_TABLE));
        sib.setSearchLongName(S21CMCommonRequestFunc.getReqBoolean(bean, S21CMRequestDef.REQ_SEARCH_LONGN_NAME));
        sib.setSearchType(S21CMCommonRequestFunc.getReqString(bean, S21CMRequestDef.REQ_SEARCH_TYPE));
        sib.setRecordCount(S21CMCommonRequestFunc.getReqInt(bean, S21CMRequestDef.REQ_REC_COUNT));
        sib.setCondSearch(S21CMCommonRequestFunc.getReqBoolean(bean, S21CMRequestDef.REQ_COND_SEARCH));
        sib.setDisplayNumber(S21CMCommonRequestFunc.getReqInt(bean, S21CMRequestDef.REQ_DISPLAY_NUMBER));

        // 表示ページとして遷移先ページを設定
        int page = S21CMCommonRequestFunc.getReqInt(bean, S21CMRequestDef.REQ_NEXT_PAGE);
        if (page <= 0) {
            page = 1;
        }
        sib.setViewPage(page - 1);

        // 検索条件のチェックと設定
        checkCondition(bean);
    }

    /**
     * 検索条件をチェックし問題なければBeanに設定する。<br>
     * @param req HttpServletRequest
     * @param sib リクエスト情報保持Bean
     */
    protected void checkCondition(S21NEContainerDataBean bean) {
        // 検索条件なし

        S21CMSearchInputBean sib = (S21CMSearchInputBean) bean.getRequest();

        // キーカラム情報消去
        // 前画面の情報を消去する
        S21CodeTableColumnInfo[] infos = S21MsgAccessor.getInstance().getKeyColumnInfos(sib.getTableName());

        for (int i = 0; i < infos.length; i++) {
            bean.removeReq(S21CMRequestDef.REQ_EZMSG + infos[i].getColumnName());
        }
    }

    /**
     * 検索画面用入力情報Beanのインスタンスを生成する。<br>
     * @return S21CMSearchInputBeanインスタンス
     */
    protected S21CMSearchInputBean createInputBean() {
        return new S21CMSearchInputBean();
    }
}
