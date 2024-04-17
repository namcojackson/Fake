package com.canon.cusa.s21.framework.generictable.ap;

import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDLog;
import parts.common.EZDMessageInfo;
import parts.common.EZDTMsg;
import parts.common.EZDValidatorException;

import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.generictable.fw.S21NEErrorUtil;
import com.canon.cusa.s21.framework.generictable.fw.S21NEValidator;
import com.canon.cusa.s21.framework.generictable.fw.S21NEValidatorException;

/**
 * 検索画面 共通エラー処理。<br>
 * コントロール処理のS21NEBaseReqControlのerrorメソッドのみを実装する。<br>
 * 検索画面でのエラーは全件検索+エラーの表示を行う。<br>
 * @author Administrator
 */
public abstract class S21CMSearchtScreenCommonControl extends S21CMBaseControl {

    /** その他異常 */
    private static final String OTHER_ERROR = "ZZM9501E";

    /** 登録後の検索処理起動用ダミーリクエスト情報 */
    private static final String SELECT_TABLE = "selectTable";

    /** 登録後の検索処理起動用ダミーリクエスト情報 */
    private static final String SELECT_CONDITION = "searchCondition";

    /** 登録後の検索処理起動用ダミーリクエスト情報 */
    private static final String CM_SEARCH = "cm_search";

    @Override
    protected void error(S21NEContainerDataBean bean) {

        // Exception退避
        EZDAbendException exp = (EZDAbendException) bean.getExp();

        // 検索条件退避用
        Map<String, String> condMap = null;
        Map<String, String> condOldMap = null;
        EZDTMsg condOldMsg = null;

        // ページ数退避用
        int page = -1;

        if (bean.getRequest() instanceof S21CMSearchInputBean) {
            S21CMSearchInputBean sib = (S21CMSearchInputBean) bean.getRequest();

            // 検索条件退避
            condMap = sib.getCondMap();
            if (sib.isCondSearch()) {
                condOldMap = getOldSearchConditionMap(bean, sib);
                condOldMsg = getOldSearchConditionMsg(bean, sib);
                if (condMap == null) {
                    condMap = condOldMap;
                }
            }

            // 表示中ページ
            page = getViewPage(bean);
        }

        // 検索画面(テーブル選択)へ遷移
        bean.setScreenID(CM_SEARCH);

        if (condOldMap == null || condOldMap.isEmpty() || condOldMsg == null) {
            bean.setReqID(SELECT_TABLE);
        } else {
            bean.setReqID(SELECT_CONDITION);
        }

        // Validatorクラスのインスタンス生成
        S21NEValidator validator = new S21CMSearchValidator();
        validator.valid(bean);

        // 実際に行われた検索条件を設定
        if (condOldMap != null && condOldMsg != null) {
            ((S21CMSearchInputBean) bean.getRequest()).setCondMap(condOldMap);
            ((S21CMSearchInputBean) bean.getRequest()).setCondition(condOldMsg);
        }

        // 表示されていたページを設定
        if (page != -1) {
            ((S21CMSearchInputBean) bean.getRequest()).setViewPage(page);
        }

        // コントロールクラスのインスタンス生成
        S21CMSelectTableControl control = new S21CMSelectTableControl();
        control.control(bean);

        // 入力された検索条件を設定
        if (condMap != null) {
            conditionProc(bean, condMap, condOldMap);
        }

        // Exception解析
        if (exp.getCause() instanceof S21NEValidatorException) {

            // S21でのチェックエラーを通知するExceptionを抜き出す
            S21NEValidatorException neve = (S21NEValidatorException) exp.getCause();

            // メッセージIDを取得
            String msgId = neve.getErrorCd();

            String column = bean.getField();

            // エラーメッセージ作成
            EZDMessageInfo msgInfo = new EZDMessageInfo(msgId, new String[] {column}, 9);

            // コンテナに設定
            bean.setMsgInfo(msgInfo);

        } else if (exp.getCause() instanceof EZDValidatorException) {

            // EZDでのチェックエラーを通知するExceptionを抜き出す
            EZDValidatorException ezdve = (EZDValidatorException) exp.getCause();

            // メッセージIDを取得
            String msgId = S21NEErrorUtil.convMessageCode(ezdve.getErrCode());

            String column = bean.getField();

            // エラーメッセージ作成
            EZDMessageInfo msgInfo = new EZDMessageInfo(msgId, new String[] {column}, 9);

            // コンテナに設定
            bean.setMsgInfo(msgInfo);

        } else {

            // エラーメッセージ作成
            EZDMessageInfo msgInfo = new EZDMessageInfo(OTHER_ERROR);

            // コンテナに設定
            bean.setMsgInfo(msgInfo);

            // システムログ出力
            EZDLog.println(3, exp.getMessage());
        }

        // 正常系と同じ画面へ遷移
        bean.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }

    /**
     * 現在表示中の画面の検索条件をMap形式で取得する。<br>
     * @param bean S21NEContainerDataBean
     * @param sib S21CMSearchInputBean
     * @return 検索条件を保持するMap
     */
    protected Map<String, String> getOldSearchConditionMap(S21NEContainerDataBean bean, S21CMSearchInputBean sib) {
        return S21CMCommonRequestFunc.createMsgMap(sib.getTableName(), bean, S21CMRequestDef.REQ_EZMSG_OLD);
    }

    /**
     * 現在表示中の画面の検索条件をEZDTMsg形式で取得する。<br>
     * @param bean S21NEContainerDataBean
     * @param sib S21CMSearchInputBean
     * @return 検索条件を保持するEZDTMsg
     */
    protected EZDTMsg getOldSearchConditionMsg(S21NEContainerDataBean bean, S21CMSearchInputBean sib) {
        return S21CMCommonRequestFunc.createEZDTMsg(sib.getTableName(), bean, S21CMRequestDef.REQ_EZMSG_OLD);
    }

    /**
     * 現在表示中のページを取得する。<br>
     * @param bean S21NEContainerDataBean
     * @return 表示中ページ
     */
    protected int getViewPage(S21NEContainerDataBean bean) {
        int page = S21CMCommonRequestFunc.getReqInt(bean, S21CMRequestDef.REQ_VIEW_PAGE);
        if (page <= 0) {
            return 0;
        }
        return page - 1;
    }

    /**
     * 検索条件再設定処理。<br>
     * 検索条件を設定する場合は、本メソッドに設定処理を記述する。<br>
     * @param bean S21NEContainerDataBean
     */
    protected void conditionProc(S21NEContainerDataBean bean, Map<String, String> condMap, Map<String, String> condMapOld) {

        S21CMSearchScreenBean ssb = (S21CMSearchScreenBean) bean.getResponse();

        // 検索条件を設定
        ssb.setCondition(condMap);
        ssb.setConditionOld(condMapOld);
    }
}
