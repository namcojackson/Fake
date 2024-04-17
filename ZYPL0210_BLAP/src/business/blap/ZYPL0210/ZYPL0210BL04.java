package business.blap.ZYPL0210;

import java.io.File;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZYPL0210.common.ZYPL0210CommonLogic;
import business.blap.ZYPL0210.constant.ZYPL0210Constant;
import business.parts.ZYEC024001PMsg;

import com.canon.cusa.s21.framework.ZYP.csvupload.ZYECSVUploadFileUploader;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * ZYPL0210BL02
 * @author A.Hosono CommonTeam
 */
public class ZYPL0210BL04 extends S21BusinessHandler implements ZYPL0210Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        // Dispatch event
        try {
            String screenAplID = cMsg.getScreenAplID();

            if (SCRN_UPLOAD.equals(screenAplID)) {
                // 4.Upload Event
                doProcess_ZYPL0210Scrn00_Upload((ZYPL0210CMsg) cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Template Download Event process.
     * @param cMsg
     * @param sMsg
     */
    protected void doProcess_ZYPL0210Scrn00_Upload(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {
        // 1. do file upload
        String glblCmpyCd = getGlobalCompanyCode();



        ZYEC024001PMsg inoutMsg = new ZYEC024001PMsg();
        EZDMsg.copy(cMsg, null, inoutMsg, null);

        inoutMsg.glblCmpyCd.setValue(glblCmpyCd);
        inoutMsg.upldCsvId.setValue(cMsg.upldCsvId_0H.getValue());

        File uploadFile = new File(cMsg.xxUpldCsvFilePathTxt.getValue());
        inoutMsg.upldCsvFileNm.setValue(uploadFile.getName());
        inoutMsg.upldCsvFileDescTxt.setValue(cMsg.upldCsvFileDescTxt_0H.getValue());
        inoutMsg.xxUpldCsvStartBatFlg.setValue(ZYECSVUploadFileUploader.UPLD_CSV_START_BAT_FLG_YES);

        inoutMsg.ezReqInputDeptCode.setValue(getContextUserInfo().getUserCompanyCode());
        inoutMsg.ezReqInputUserID.setValue(getContextUserInfo().getUserId());

        boolean uploadPartial = CSV_ID_LIST_UPLOAD_WITH_FORMAT_ERROR.contains(inoutMsg.upldCsvId.getValue());
        boolean putDefaultValueNToFlg = CSV_ID_LIST_UPLOAD_TO_N_FLG.contains(inoutMsg.upldCsvId.getValue());
        
        if(uploadPartial){
            inoutMsg.xxUpldCsvStartBatFlg_PA.setValue(ZYECSVUploadFileUploader.UPLD_CSV_START_BAT_FLG_YES);
        }
        if(putDefaultValueNToFlg){
        	inoutMsg.xxUpldCsvStartBatFlg_CM.setValue(ZYECSVUploadFileUploader.UPLD_CSV_START_BAT_FLG_YES);
        }
        
        ZYECSVUploadFileUploader fileUpload = ZYECSVUploadFileUploader.getInstance();
        fileUpload.exec(inoutMsg);

        // 2.
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        ZYPL0210CommonLogic.doSearch(cMsg, sMsg);

        // 3.
        ZYECSVUploadFileUploader.RETURN_CODE returnCode = ZYECSVUploadFileUploader.RETURN_CODE.valueFromCode(inoutMsg.getReturnCode());
        if (ZYECSVUploadFileUploader.RETURN_CODE.SUCCESS == returnCode) {
            EZDMsg.copy(inoutMsg, null, cMsg, null);
            cMsg.setMessageInfo(MSG_CD_UPLOAD_NORMAL_END);
        } else {
            cMsg.setMessageInfo(inoutMsg.xxMsgIdList.no(0).xxMsgId.getValue());
        }
    }
}
