package business.blap.ZYPL0220.common;

import parts.common.EZDFMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZYPL0220.ZYPL0220CMsg;
import business.blap.ZYPL0220.constant.ZYPL0220Constant;
import business.db.UPLD_CSV_HDRTMsg;
import business.db.UPLD_CSV_HDRTMsgArray;
import business.db.UPLD_CSV_RST_BIZ_APP_IDTMsg;
import business.db.UPLD_CSV_RST_BIZ_APP_IDTMsgArray;
import business.db.UPLD_CSV_RST_PROCTMsg;
import business.db.UPLD_CSV_RST_PROCTMsgArray;
import business.parts.ZYEC022001PMsg;

import com.canon.cusa.s21.framework.ZYP.csvupload.ZYECSVUploadConfiguration;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZYPL0220CommonLogic implements ZYPL0220Constant {

    /**
     * アップロードヘッダ列テーブル(UPLD_CSV_MSTR)を検索します。
     *
     * @param cMsg 業務アプリケーションメッセージ
     * @param lock 排他ロックを取得する場合はtrue。
     * @return 検索結果
     */
    public static UPLD_CSV_HDRTMsgArray findUpldCsvHdrByUpldCsvId(ZYPL0220CMsg cMsg, boolean lock) {
        UPLD_CSV_HDRTMsg condition = new UPLD_CSV_HDRTMsg();
        condition.setConditionValue("ezCancelFlag01", "0");
        condition.setConditionValue("glblCmpyCd01", S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        condition.setConditionValue("upldCsvId01", cMsg.upldCsvId.getValue());
        condition.setSQLID(SQL_ID_UPLD_CSV_HDR_SET_UPLDCSVID);
        if (lock) {
            return (UPLD_CSV_HDRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(condition);
        } else {
            return (UPLD_CSV_HDRTMsgArray) EZDTBLAccessor.findByCondition(condition);
        }
    }

    /**
     * アップロード業務プロセス制限テーブル(UPLD_CSV_RST_PROC)を検索します。
     * @param cMsg 業務アプリケーションメッセージ
     * @param lock 排他ロックを取得する場合はtrue。
     * @return 検索結果
     */
    public static UPLD_CSV_RST_PROCTMsgArray findUpldCsvRstProc(ZYPL0220CMsg cMsg, boolean lock) {
        UPLD_CSV_RST_PROCTMsg condition = new UPLD_CSV_RST_PROCTMsg();
        condition.setConditionValue("ezCancelFlag01", "0");
        condition.setConditionValue("glblCmpyCd01", S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        condition.setConditionValue("upldCsvId01", cMsg.upldCsvId.getValue());
        condition.setSQLID(SQL_ID_GET_PROCESS_NAME);
        if (lock) {
            return (UPLD_CSV_RST_PROCTMsgArray) EZDTBLAccessor.findByConditionForUpdate(condition);
        } else {
            return (UPLD_CSV_RST_PROCTMsgArray) EZDTBLAccessor.findByCondition(condition);
        }

    }

    /**
     * アップロード業務ID制限テーブル(UPLD_CSV_RST_BIZ_APP_ID)を検索します。
     * @param cMsg 業務アプリケーションメッセージ
     * @param lock 排他ロックを取得する場合はtrue。
     * @return 検索結果
     */
    public static UPLD_CSV_RST_BIZ_APP_IDTMsgArray findUpldCsvRstBizAppId(ZYPL0220CMsg cMsg, boolean lock) {
        UPLD_CSV_RST_BIZ_APP_IDTMsg condition = new UPLD_CSV_RST_BIZ_APP_IDTMsg();
        condition.setConditionValue("ezCancelFlag01", "0");
        condition.setConditionValue("glblCmpyCd01", S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        condition.setConditionValue("upldCsvId01", cMsg.upldCsvId.getValue());
        condition.setSQLID(SQL_ID_GET_BIZ_APP_ID);
        if (lock) {
            return (UPLD_CSV_RST_BIZ_APP_IDTMsgArray) EZDTBLAccessor.findByCondition(condition);
        } else {
            return (UPLD_CSV_RST_BIZ_APP_IDTMsgArray) EZDTBLAccessor.findByCondition(condition);
        }

    }

    /**
     * File IDからEZDFMsgのオブジェクトを生成します。
     * @param cMsg FileIDが格納された業務アプリケーションメッセージ
     * @return EZDFMsgオブジェクト (オブジェクトが生成できない場合は、cMsgにエラー情報を設定しnullを返す)
     */
    public static EZDFMsg newUpldEZDFMsg(ZYPL0220CMsg cMsg){
        String className = UPLD_CSV_FILE_CLS_NM_TEMP.replaceAll(UPLD_CSV_FILE_CLS_NM_ARG, cMsg.upldCsvFileId.getValue());
        Class fMsgClass = null;
        EZDFMsg fmsg = null;
        try {
            fMsgClass = Class.forName(className);
            fmsg = (EZDFMsg) fMsgClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_NOT_FOUND_FMSG_UPLD);
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_NOT_FOUND_FMSG_UPLD);
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvFileId.setErrorInfo(1, MSG_CD_NOT_FOUND_FMSG_UPLD);
            return null;
        }

        return fmsg;
    }

    /**
     * Table IDからEZDFMsgのオブジェクトを生成します。
     * @param cMsg TableIDが格納された業務アプリケーションメッセージ
     * @return EZDFMsgオブジェクト (オブジェクトが生成できない場合は、cMsgにエラー情報を設定しnullを返す)
     */
    public static EZDFMsg newDnldEZDFMsg(ZYPL0220CMsg cMsg){
        String className = DNLD_CSV_FILE_CLS_NM_TEMP.replaceAll(DNLD_CSV_FILE_CLS_NM_ARG, cMsg.upldCsvTempTblId.getValue());

        Class fMsgClass = null;
        EZDFMsg fmsg = null;
        try {
            fMsgClass = Class.forName(className);
            fmsg = (EZDFMsg) fMsgClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvTempTblId.setErrorInfo(1, MSG_CD_NOT_FOUND_DNLD_FMSG);
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvTempTblId.setErrorInfo(1, MSG_CD_NOT_FOUND_DNLD_FMSG);
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvTempTblId.setErrorInfo(1, MSG_CD_NOT_FOUND_DNLD_FMSG);
            return null;
        }

        return fmsg;
    }

    /**
     * Table IDからEZDTMsgのオブジェクトを生成します。
     * @param cMsg TableIDが格納された業務アプリケーションメッセージ
     * @return EZDTMsgオブジェクト (オブジェクトが生成できない場合は、cMsgにエラー情報を設定しnullを返す)
     */
    public static EZDTMsg newEZDTMsg(ZYPL0220CMsg cMsg){
        String className = DNLD_CSV_TEMP_TBL_CLS_NM_TEMP.replace(DNLD_CSV_TEMP_TBL_CLS_NM_ARG, cMsg.upldCsvTempTblId.getValue());

        Class tMsgClass = null;
        EZDTMsg tmsg = null;
        try {
            tMsgClass = Class.forName(className);
            tmsg = (EZDTMsg) tMsgClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvTempTblId.setErrorInfo(1, MSG_CD_NOT_FOUND_TMSG);
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvTempTblId.setErrorInfo(1, MSG_CD_NOT_FOUND_TMSG);
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            cMsg.setTransactionMode("0");
            cMsg.upldCsvTempTblId.setErrorInfo(1, MSG_CD_NOT_FOUND_TMSG);
            return null;
        }

        return tmsg;
    }

    /**
     *
     * @param cMsg
     * @param sMsg
     */
    public static void doSearch(ZYPL0220CMsg cMsg, EZDSMsg sMsg){
        // -----------------------------------------------------
        // 2) Search data UPLD_CSV_MSTR
        // -----------------------------------------------------
        boolean isExistsMstr = doSearchMstr(cMsg, sMsg);
        if (!isExistsMstr) {
            return;
        }

        // -----------------------------------------------------
        // 3) Search data UPLD_CSV_HDR
        // -----------------------------------------------------
        doSearchHdr(cMsg, sMsg);

        // -----------------------------------------------------
        // 4) Search data UPLD_CSV_RST_PROC
        // -----------------------------------------------------
        doSearchRstProc(cMsg, sMsg);

        // -----------------------------------------------------
        // 5) Search data UPLD_CSV_BIZ_APP_ID
        // -----------------------------------------------------
        doSearchRstBizAppId(cMsg, sMsg);
    }

    /**
     * 検索処理
     * @param cMsg 業務アプリインターフェースメッセージ
     * @param sMsg 業務共有メッセージ
     */
    private static boolean doSearchMstr(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {
        ZYEC022001PMsg configMsg = new ZYEC022001PMsg();
        configMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        configMsg.upldCsvId.setValue(cMsg.upldCsvId.getValue());
        ZYECSVUploadConfiguration config = ZYECSVUploadConfiguration.getInstance();
        config.exec(configMsg);
        if (ZYECSVUploadConfiguration.RETURN_CODE.ERR_NONE_UPLD_CSV_MSTR == config.convReturnCode(configMsg)) {
            cMsg.upldCsvId.setErrorInfo(2, MSG_CD_NOT_FOUND_UPLD_CSV_MSTR);
            return false;
        }

        // 3.
        String defaultUploadCsvId = cMsg.upldCsvId_DF.getValue();
        cMsg.clear();
        cMsg.upldCsvId_DF.setValue(defaultUploadCsvId);
        for (int i = 0; i < cMsg.B.length(); i++) {
            cMsg.B.no(i).upldCsvHdrNum_1B.setValue(i + 1);
        }
        cMsg.B.setValidCount(cMsg.B.length());
        for (int i = 0; i < cMsg.C.length(); i++) {
            cMsg.C.no(i).upldCsvHdrNum_1C.setValue(i + 1);
        }
        cMsg.C.setValidCount(cMsg.C.length());

        EZDMsg.copy(configMsg, null, cMsg, null);

        return true;
    }

    /**
     * UPLD_CSV_HDRテーブルからHeader名を取得します。
     * @param cMsg
     * @param sMsg
     * @return 取得件数
     */
    private static int doSearchHdr(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {

        // 1.find head data from UPLD_CSV_HDR table.
        final UPLD_CSV_HDRTMsgArray headerRecords = ZYPL0220CommonLogic.findUpldCsvHdrByUpldCsvId(cMsg, false);

        int i = 0;
        for (; i < headerRecords.length(); i++) {
            EZDMsg.copy(headerRecords.no(i), null, cMsg.A.no(i), null);
            cMsg.A.no(i).ezUpTime_0D.setValue(headerRecords.no(i).ezUpTime.getValue());
            cMsg.A.no(i).ezUpTimeZone_0D.setValue(headerRecords.no(i).ezUpTimeZone.getValue());
        }
        cMsg.A.setValidCount(i);

        return i;

    }

    /**
     * UPLD_CSV_RST_PROCテーブルから指定のUploadCSVIDが利用できる業務プロセス名を取得します。
     * @param cMsg
     * @param sMsg
     * @return
     */
    private static int doSearchRstProc(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {

        // 1.find head data from UPLD_CSV_HDR table.
        UPLD_CSV_RST_PROCTMsgArray rstProcRecords = ZYPL0220CommonLogic.findUpldCsvRstProc(cMsg, false);

        int i = 0;
        for (; i < rstProcRecords.length(); i++) {
            cMsg.B.no(i).upldCsvRstProcNm.setValue(rstProcRecords.no(i).upldCsvRstProcNm.getValue());
            cMsg.B.no(i).ezUpTime_1B.setValue(rstProcRecords.no(i).ezUpTime.getValue());
            cMsg.B.no(i).ezUpTimeZone_1B.setValue(rstProcRecords.no(i).ezUpTimeZone.getValue());
        }

        return i;

    }

    /**
     * UPLD_CSV_RST_BIZ_APP_IDテーブルからHeader名を取得します。
     * @param msg
     * @param msg2
     * @return 取得件数
     */
    private static int doSearchRstBizAppId(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {

        // 1.find head data from UPLD_CSV_HDR table.
        UPLD_CSV_RST_BIZ_APP_IDTMsgArray rstBizIdRecords = ZYPL0220CommonLogic.findUpldCsvRstBizAppId(cMsg, false);

        int i = 0;
        for (; i < rstBizIdRecords.length(); i++) {
            cMsg.C.no(i).upldCsvRstBizAppId.setValue(rstBizIdRecords.no(i).upldCsvRstBizAppId.getValue());
            cMsg.C.no(i).ezUpTime_1C.setValue(rstBizIdRecords.no(i).ezUpTime.getValue());
            cMsg.C.no(i).ezUpTimeZone_1C.setValue(rstBizIdRecords.no(i).ezUpTimeZone.getValue());
        }

        return i;

    }

}
