package com.canon.cusa.s21.framework.codetable.fw;

import parts.common.EZDCMsg;
import parts.common.EZDMsgArray;

/**
 * 課金情報出力用ダミーEZDCMsg。<br>
 * 最低限の課金情報を出力するために使用するEZDCMsg派生クラス。<br>
 * 課金情報を出力するためにだけ使用し、実際の業務処理には使用しない。<br>
 * @author Administrator
 */
public class S21NEDummyCMsg extends EZDCMsg {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    /**
     * EZ user ID
     */
    private String ezUserID;
    
    /**
     * EZ screen application program ID
     */
    private String ezScreenAplID;
    

    /**
     * コンストラクタ。<br>
     * @param dataBean S21NEContainerDataBean
     */
    public S21NEDummyCMsg(S21NEContainerDataBean dataBean) {
        super(false, 0);

        // コンテナに登録されている情報をコピーする
        this.setBusinessID(dataBean.getEzBizID());
        this.setScreenID(dataBean.getEzScreenID());
        this.setOnlOperationDate(dataBean.getReqTime().substring(0, 8));
        this.setServerCallTime(dataBean.getReqTime());
        
        this.setBusinessID(dataBean.getEzBizID());
        this.setUserID(dataBean.getQid());
        this.setScreenID(dataBean.getEzScreenID());
        
        String tableName = "";
        if (dataBean.getRequest() != null) {
            tableName = ": " + dataBean.getRequest().getTableName();
        }
        
        this.setScreenAplID(dataBean.getEzBizID() + "_" +dataBean.getReqID() + tableName);
        this.setMessageInfo(dataBean.getMsgCode());
        
    }

    /* (non-Javadoc)
     * @see parts.common.EZDMsg#getBaseContents()
     */
    @Override
    protected String[][] getBaseContents() {
        return new String[0][0];
    }

    /**
     * 自身の配列型クラスのインスタンスを取得する。<br>
     * @return 配列型
     * @see parts.common.EZDMsg#getMyArray()
     */
    @Override
    public EZDMsgArray getMyArray() {
        return null;
    }
    

    void setUserID(String userID) {
        ezUserID = userID;
    }
    
    @Override
    public String getUserID() {
        if (ezUserID == null) {
            return "";
        }
        return ezUserID;
    }
    
    void setScreenAplID(String screenAplID) {
        ezScreenAplID = screenAplID;
    }

    @Override
    public String getScreenAplID() {

        if (ezScreenAplID == null) {
            return "";
        }
        return ezScreenAplID;
    }

}
