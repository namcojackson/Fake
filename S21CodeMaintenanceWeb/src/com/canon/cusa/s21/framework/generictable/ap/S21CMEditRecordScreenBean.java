package com.canon.cusa.s21.framework.generictable.ap;

import java.util.List;

import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;

/**
 * 編集レコード出力Bean。<br>
 * @author Administrator
 */
public class S21CMEditRecordScreenBean extends S21CMEditRecordBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** カラム名一覧 */
    private List<S21CodeTableColumnInfo> colInfos;

    /** 確認画面フラグ */
    private boolean confirm;

    /**
     * @return colInfos
     */
    public List<S21CodeTableColumnInfo> getColInfos() {
        return colInfos;
    }

    /**
     * @param colInfos 設定する colInfos
     */
    public void setColInfos(List<S21CodeTableColumnInfo> colInfos) {
        this.colInfos = colInfos;
    }

    /**
     * @return confirm
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * @param confirm 設定する confirm
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

}
