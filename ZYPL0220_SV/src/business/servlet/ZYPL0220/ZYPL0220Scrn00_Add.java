package business.servlet.ZYPL0220;

import java.util.HashMap;
import java.util.HashSet;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0220.ZYPL0220CMsg;
import business.servlet.ZYPL0220.constant.ZYPL0220Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZYPL0220Scrn00_Add extends S21CommonHandler implements ZYPL0220Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // ------------------------------------------
        // (1)-1mandatory check for Upload Csv ID
        // ------------------------------------------
        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.upldCsvId);
        scrnMsg.addCheckItem(scrnMsg.upldCsvNm);
        scrnMsg.addCheckItem(scrnMsg.upldCsvFileId);
        scrnMsg.addCheckItem(scrnMsg.upldCsvTempTblId);
        scrnMsg.addCheckItem(scrnMsg.ezReqBusinessID);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();

        // ------------------------------------------
        // (1)-2check same file id
        // ------------------------------------------
        String scrnUploadCsvFileId = scrnMsg.upldCsvFileId.getValue();
        String saveUploadCsvFileId = scrnMsg.upldCsvFileId_LD.getValue();
        if (!saveUploadCsvFileId.equals(scrnUploadCsvFileId)) {
            scrnMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_INCONSISTENT_FILE_ID);
            scrnMsg.addCheckItem(scrnMsg.upldCsvFileId);
            scrnMsg.putErrorScreen();
        }

        // ------------------------------------------
        // (1)-3check header count
        // ------------------------------------------
        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_NO_RECORD_FILE_ID);
            scrnMsg.putErrorScreen();
        }
        
        // ------------------------------------------
        // (1)-4 duplicate business proc name
        // ------------------------------------------
        HashSet<String> setProc = new HashSet<String>();
        for(int i=0;i<scrnMsg.B.length();i++){
            if(scrnMsg.B.no(i).upldCsvRstProcNm.isClear()){
                continue;
            }
            String value = scrnMsg.B.no(i).upldCsvRstProcNm.getValue();
            if(setProc.contains(value)){
                scrnMsg.B.no(i).upldCsvRstProcNm.setErrorInfo(1, MSG_CD_NO_DUPLICATE_RECORD);
            }else{
                setProc.add(value);
            }
        }
        
        HashSet<String> setBizId = new HashSet<String>();
        for(int i=0;i<scrnMsg.C.length();i++){
            if(scrnMsg.C.no(i).upldCsvRstBizAppId.isClear()){
                continue;
            }
            String value = scrnMsg.C.no(i).upldCsvRstBizAppId.getValue();
            if(setBizId.contains(value)){
                scrnMsg.C.no(i).upldCsvRstBizAppId.setErrorInfo(1, MSG_CD_NO_DUPLICATE_RECORD);
            }else{
                setBizId.add(value);
            }
        }
        
        scrnMsg.addCheckItem(scrnMsg.B,"Upload Restriction");
        scrnMsg.B.setCheckParam(new String[]{"upldCsvRstProcNm"}, 1);
        scrnMsg.addCheckItem(scrnMsg.C,"Upload Restriction");
        scrnMsg.C.setCheckParam(new String[]{"upldCsvRstBizAppId"}, 1);
        
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;

        ZYPL0220CMsg bizMsg = new ZYPL0220CMsg();
        bizMsg.setBusinessID("ZYPL0220");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0220BMsg scrnMsg = (ZYPL0220BMsg) bMsg;
        ZYPL0220CMsg bizMsg = (ZYPL0220CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // ------------------------------------------
        // (6)add check item
        // ------------------------------------------
        scrnMsg.addCheckItem(scrnMsg.upldCsvId);
        scrnMsg.addCheckItem(scrnMsg.upldCsvFileId);
        scrnMsg.addCheckItem(scrnMsg.upldCsvTempTblId);
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{"upldCsvHdrNm"}, 1);
        scrnMsg.addCheckItem(scrnMsg.B);
        scrnMsg.B.setCheckParam(new String[]{"upldCsvRstProcNm"}, 1);
        scrnMsg.addCheckItem(scrnMsg.C);
        scrnMsg.C.setCheckParam(new String[]{"upldCsvRstBizAppId"}, 1);
        scrnMsg.putErrorScreen();
    
        // ------------------------------------------
        // (7)set success message
        // ------------------------------------------
        scrnMsg.setMessageInfo(MSG_CD_SUCCESS_PROCESS, null);
    }

}
