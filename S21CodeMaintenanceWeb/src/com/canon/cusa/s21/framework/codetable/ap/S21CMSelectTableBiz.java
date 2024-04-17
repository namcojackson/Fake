package com.canon.cusa.s21.framework.codetable.ap;

import parts.common.EZDTMsgArray;

import com.canon.cusa.s21.framework.codetable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.codetable.fw.S21NEConfig;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面 テーブル一覧選択イベント業務処理。<br>
 * @author Administrator
 */
public class S21CMSelectTableBiz extends S21NEBusiness {

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 入力情報を取得
        S21CMSearchInputBean sib = (S21CMSearchInputBean) req.getRequest();

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = createSearchList(req.getScreenID(), sib);

        // レスポンス情報としてS21CMSearchListBeanを設定
        req.setResponse(slb);

        // 成功ステータス設定
        req.setStatus(S21NEContainerDataBean.STATUS_SUCCESS);
    }

    /**
     * テーブル検索処理。<br>
     * @param sib 検索条件Bean
     * @return 検索結果Bean
     */
    private S21CMSearchListBean createSearchList(String screen, S21CMSearchInputBean sib) {

        // 表示行数
        int line = S21NEConfig.getInstance().getDispLine(screen);

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = S21CMSearchCommon.createSearchListBean(!sib.isDownload());

//        // テーブル内容検索処理を行う
//        slb.setMsgs(S21CodeTableAccessor.findAll(sib.getTableName()));

        // 全レコード件数取得
        int allRec = S21CodeTableAccessor.count(sib.getTableName());

        EZDTMsgArray array;

        if (allRec > line) {
            // 指定行数検索
            array = S21CodeTableAccessor.findAllRowNum(sib.getTableName(), sib.getViewPage() * line, line);
        } else {
            // 全件検索
            array = S21CodeTableAccessor.findAll(sib.getTableName());
        }
        slb.setMsgs(array);

        // 全件数取得
        slb.setAllRecord(allRec);

        // カラム情報を取得する
        slb.setColInfo(S21CodeTableAccessor.getColumnInfoAll(sib.getTableName()));

        // 検索テーブル情報
        slb.setTableName(sib.getTableName());
        slb.setTableLongName(S21CodeTableAccessor.getLongName(sib.getTableName()));
        slb.setViewPage(sib.getViewPage());

        // 検索条件
        slb.setCondition(sib.getCondition());

        return slb;
    }

}
