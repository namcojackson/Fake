/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1050;

import static business.blap.NSAL1050.constant.NSAL1050Constant.BUSINESS_ID;
import static business.blap.NSAL1050.constant.NSAL1050Constant.LIMIT_DOWNLOAD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1050.common.NSAL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         T.Mizuki        Create          N/A
 * 2017/01/20   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL1050BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSAL1050CMsg cMsg = (NSAL1050CMsg) arg0;
        NSAL1050SMsg sMsg = (NSAL1050SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL1050_INIT".equals(screenAplID)) {
                doProcess_NSAL1050_Init(cMsg);
                ZYPGUITableColumn.getColData((NSAL1050CMsg) cMsg, (NSAL1050SMsg) sMsg);
            // START 2017/01/20 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL1050Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1050Scrn00_CMN_Clear(cMsg);
            // END 2017/01/20 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL1050Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1050_Search(cMsg);
            } else if ("NSAL1050Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL1050Scrn00_Download((NSAL1050CMsg) cMsg);
            } else if ("NSAL1050Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1050_Search(cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1050_Init(NSAL1050CMsg cMsg) {

        cMsg.clear();
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        NSAL1050CommonLogic.createPullDown(cMsg);
    }

    private void doProcess_NSAL1050_Search(NSAL1050CMsg cMsg) {

        NSAL1050CommonLogic.searchInfo(cMsg);
    }

    /**
     * do process (download)
     * @param cMsg NSAL1050CMsg
     */
    private void doProcess_NSAL1050Scrn00_Download(NSAL1050CMsg cMsg) {

        // create csv file
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".xls");
        NSAL1050Query.getInstance().getDownloadData(cMsg, LIMIT_DOWNLOAD + 1);
    }

    // START 2017/01/20 K.Ochiai [QC#16331,MOD]
    private void doProcess_NSAL1050Scrn00_CMN_Clear(NSAL1050CMsg cMsg) {

        String col = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NSAL1050_Init(cMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, col);
    }
    // END 2017/01/20 K.Ochiai [QC#16331,MOD]

}
