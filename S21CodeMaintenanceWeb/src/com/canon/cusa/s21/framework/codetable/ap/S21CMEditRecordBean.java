package com.canon.cusa.s21.framework.codetable.ap;

import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;

import com.canon.cusa.s21.framework.codetable.fw.S21NEDataBean;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableColumnInfo;

/**
 * 編集レコード入出力Bean。<br>
 * @author Administrator
 */
public class S21CMEditRecordBean implements S21NEDataBean, S21CMTableBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 対象テーブル名 */
    private String tableName;

    /** 対象ロングネーム */
    private String tableLongName;

    /** 入力値 EZDTMsg */
    private EZDTMsg msg;

    /** 入力値 EZDTMsg相当Map */
    private Map<String, String> msgMap;

    /** カラム名一覧 */
    private List<S21CodeTableColumnInfo> colInfos;

    /** 更新タイプ */
    private S21CMEditType type;

    /**
     * 更新タイプenum。<br>
     * @author Administrator
     */
    public enum S21CMEditType {
        /** 編集 */
        EDIT,

        /** 追加 */
        ADD
    }

    /**
     * @return msg
     */
    public EZDTMsg getMsg() {
        return msg;
    }

    /**
     * @param msg 設定する msg
     */
    public void setMsg(EZDTMsg msg) {
        this.msg = msg;
    }

    /**
     * @return tableLongName
     */
    public String getTableLongName() {
        return tableLongName;
    }

    /**
     * @param tableLongName 設定する tableLongName
     */
    public void setTableLongName(String tableLongName) {
        this.tableLongName = tableLongName;
    }

    /**
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName 設定する tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

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
     * @return type
     */
    public S21CMEditType getType() {
        return type;
    }

    /**
     * @param type 設定する type
     */
    public void setType(S21CMEditType type) {
        this.type = type;
    }

    /**
     * @return msgMap
     */
    public Map<String, String> getMsgMap() {
        return msgMap;
    }

    /**
     * @param msgMap 設定する msgMap
     */
    public void setMsgMap(Map<String, String> msgMap) {
        this.msgMap = msgMap;
    }

}
