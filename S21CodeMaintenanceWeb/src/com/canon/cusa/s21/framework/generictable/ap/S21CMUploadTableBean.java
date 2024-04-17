package com.canon.cusa.s21.framework.generictable.ap;

import java.io.InputStream;

//import parts.common.EZDTMsgArray;

/**
 * 検索画面用入力BeanにUpload情報を付加した入力情報Bean。<br>
 * @author Administrator
 */
public class S21CMUploadTableBean extends S21CMSearchInputBean {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** InputStream */
    private InputStream inputstream;

    private String fileName;

    /**
     * @return
     */
    public InputStream getInputstream() {
        return inputstream;
    }

    /**
     * @param inputstream
     */
    public void setInputstream(InputStream inputstream) {
        this.inputstream = inputstream;
    }

    /**
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
