package com.canon.cusa.s21.framework.codetable.ap;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean;
import com.canon.cusa.s21.framework.codetable.fw.S21NEContainerUploadDataBean;

/**
 * コードメンテナンス画面 CSVアップロードイベント。<br>
 * @author Administrator
 */
public class S21CMUploadTableControl extends S21CMCommonUpdateControl {

    @Override
    public void doProc(HttpServletRequest req, HttpServletResponse resp, S21NEContainerDataBean bean) throws ServletException, IOException {
        try {
            super.doProc(req, resp, bean);
        } finally {
            if (bean instanceof S21NEContainerUploadDataBean) {
                S21NEContainerUploadDataBean utb = (S21NEContainerUploadDataBean) bean;

                // Streamのクローズ
                if (utb.getInputStream() != null) {
                    utb.getInputStream().close();
                }

                // テンポラリファイル削除
                if (utb.getFileName() != null) {
                    File file = new File(utb.getFileName());
                    file.delete();
                }
            }
        }
    }

    /**
     * 業務ID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 業務ID
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getEzBizID(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getEzBizID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_BIZ_ID;
    }

    /**
     * アプリケーションID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return アプリケーションID
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getEzAplID(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getEzAplID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_APL_ID_MAINTENANCE;
    }

    /**
     * 画面ID取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面ID
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getEzScreenID(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getEzScreenID(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_ID_SEARCH;
    }

    /**
     * 画面名称取得。<br>
     * @param bean S21NEContainerDataBean
     * @return 画面名称
     * @see com.canon.cusa.s21.framework.codetable.fw.S21NEReqControl#getScreenName(com.canon.cusa.s21.framework.codetable.fw.S21NEContainerDataBean)
     */
    public String getScreenName(S21NEContainerDataBean bean) {
        return S21CMBizScreenIdDef.CM_SCREEN_NAME_SEARCH;
    }

}
