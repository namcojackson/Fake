package com.canon.cusa.s21.framework.generictable.ap;

import com.canon.cusa.s21.framework.generictable.fw.S21NEBusiness;
import com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * コードメンテナンス画面 条件検索イベント業務処理。<br>
 * @author Administrator
 */
public class S21CMSearchConditionBiz extends S21NEBusiness {

    @Override
    protected void execute(S21NEContainerDataBean req) {

        // 入力情報を取得
        S21CMSearchInputBean sib = (S21CMSearchInputBean) req.getRequest();

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = createSearchList(req.getScreenID(), sib);

        // レスポンス情報としてS21CMSearchListBeanを設定
        req.setResponse(slb);

        // 成功ステータス設定
        req.setStatus("success");
    }

    /**
     * テーブル検索処理。<br>
     * @param sib 検索条件Bean
     * @return 検索結果Bean
     */
    private S21CMSearchListBean createSearchList(String screen, S21CMSearchInputBean sib) {

        // 表示行数
        //int line = S21NEConfig.getInstance().getDispLine(screen);
        int line = 20;
    	if (sib.getDisplayNumber() > 0) {
    		line = sib.getDisplayNumber();
    	}

        // 検索処理結果保持Beanの作成
        S21CMSearchListBean slb = S21CMSearchCommon.createSearchListBean();

//        // 条件指定検索処理を行う
//        slb.setMsgs(S21CodeTableAccessor.findByCondition(sib.getCondition()));
//        slb.setMsgs(S21CodeTableAccessor.findByConditionRowNum(sib.getCondition(), sib.getViewPage() * line, line));
        
//        S21GMDBAccessQuery.getInstance().getByConditionRowNum(sib.getTableName(), sib.getCondition());
        slb.setMsgs(S21GenericTableAccessor.findByConditionRowNum(sib.getCondition(), sib.getViewPage() * line, line));
        
        // 件数取得
        slb.setAllRecord(S21CodeTableAccessor.count(sib.getCondition()));
        slb.setAllRecord(S21GenericTableAccessor.count(sib.getCondition()));

        // カラム情報を取得する
        //slb.setColInfo(S21CodeTableAccessor.getColumnInfoAll(sib.getTableName()));
        slb.setColInfo(S21GenericTableAccessor.getColumnInfoAll(sib.getTableName()));
        slb.setColumnComment(S21GMDBAccessQuery.getInstance().getColumnCommentList(sib.getTableName()));

        // 検索テーブル情報
        slb.setTableName(sib.getTableName());
//        slb.setTableLongName(S21CodeTableAccessor.getLongName(sib.getTableName()));
        slb.setTableLongName(S21GMDBAccessQuery.getInstance().getGmTableLongName(sib.getTableName()));
        slb.setViewPage(sib.getViewPage());
        slb.setDisplayNumber(sib.getDisplayNumber());

        // 検索条件
        slb.setCondition(sib.getCondition());

        return slb;
    }

}
