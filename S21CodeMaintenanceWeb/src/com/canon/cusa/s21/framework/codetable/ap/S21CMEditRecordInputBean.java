package com.canon.cusa.s21.framework.codetable.ap;

/**
 * レコード入力Bean。<br>
 * @author Administrator
 */
public class S21CMEditRecordInputBean extends S21CMEditRecordBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 編集対象インデックス */
    private int index;

    /**
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index 設定する index
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
