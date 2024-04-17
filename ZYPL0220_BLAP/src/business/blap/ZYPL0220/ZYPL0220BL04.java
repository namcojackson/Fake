package business.blap.ZYPL0220;

import parts.common.EZDCMsg;
import parts.common.EZDFMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZYPL0220.common.ZYPL0220CommonLogic;
import business.blap.ZYPL0220.constant.ZYPL0220Constant;
import business.db.UPLD_CSV_HDRTMsg;
import business.db.UPLD_CSV_HDRTMsgArray;
import business.db.UPLD_CSV_MSTRTMsg;
import business.db.UPLD_CSV_RST_BIZ_APP_IDTMsg;
import business.db.UPLD_CSV_RST_BIZ_APP_IDTMsgArray;
import business.db.UPLD_CSV_RST_PROCTMsg;
import business.db.UPLD_CSV_RST_PROCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZYPL0220BL04 extends S21BusinessHandler implements ZYPL0220Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        // Dispatch event
        try {
            String screenAplID = cMsg.getScreenAplID();

            if (SCRN_UPDATE.equals(screenAplID)) {
                // 1.INIT Event
                doProcess_ZYPL0220Scrn00_Update((ZYPL0220CMsg) cMsg, sMsg);

            } else if (SCRN_ADD.equals(screenAplID)) {
                // 1.INIT Event
                doProcess_ZYPL0220Scrn00_Add((ZYPL0220CMsg) cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Update Event
     * @param msg
     * @param msg2
     */
    private void doProcess_ZYPL0220Scrn00_Update(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {

        // ------------------------------------------
        // (1)check db update
        // ------------------------------------------
        UPLD_CSV_MSTRTMsg findMstrTMsg = this.findUpldCsvMstr(cMsg);
        if(findMstrTMsg == null){
            return;
        }

        UPLD_CSV_MSTRTMsg orgMstrTMsg = new UPLD_CSV_MSTRTMsg();
        EZDMsg.copy(findMstrTMsg, null, orgMstrTMsg, null);
        boolean upldCsvMstrUpdateFlg = this.doUpdateUpldCsvMstr(cMsg, findMstrTMsg);
        if (!upldCsvMstrUpdateFlg) {
            return;
        }

        // ------------------------------------------
        // (2)check db update
        // ------------------------------------------
        boolean upldHdrMode = doUpdateUpldCsvHdr(cMsg, orgMstrTMsg);
        if (!upldHdrMode) {
            return;
        }

        // ------------------------------------------
        // (3)check db update
        // ------------------------------------------
        boolean upldCsvRstProcUpdateFlg = doUpdateUpldCsvRstProc(cMsg);
        if (!upldCsvRstProcUpdateFlg) {
            return;
        }

        // ------------------------------------------
        // (4)check db update
        // ------------------------------------------
        doUpdateUpldCsvRstBizAppId(cMsg);

        // -----------------------------------------------------
        // do Search
        // -----------------------------------------------------
        ZYPL0220CommonLogic.doSearch(cMsg, sMsg);

    }

    /**
     * Add Event
     * @param msg
     * @param msg2
     */
    private void doProcess_ZYPL0220Scrn00_Add(ZYPL0220CMsg cMsg, EZDSMsg sMsg) {
        // ------------------------------------------
        // (2)insert UPLD_CSV_MSTR
        // ------------------------------------------
        if (!doAddUpldCsvMstr(cMsg)) {
            return;
        }

        // ------------------------------------------
        // (3)insert UPLD_CSV_HDR
        // ------------------------------------------
        if (!doAddUpldCsvHdr(cMsg)) {
            return;
        }

        // ------------------------------------------
        // (4)insert UPLD_CSV_RST_PROC
        // ------------------------------------------
        if (!doAddUpldCsvRstProc(cMsg)) {
            return;
        }

        // ------------------------------------------
        // (5)insert UPLD_CSV_RST_BIZ_APP_ID
        // ------------------------------------------
        if (!doAddUpldCsvRstBizAppId(cMsg)) {
            return;
        }

        // -----------------------------------------------------
        // do Search
        // -----------------------------------------------------
        ZYPL0220CommonLogic.doSearch(cMsg, sMsg);
    }

    /**
     * UPLD_CSV_MSTRの更新処理を行う。
     * @param cMsg
     * @param findMstrTMsg
     * @return
     */
    private boolean doUpdateUpldCsvMstr(ZYPL0220CMsg cMsg, UPLD_CSV_MSTRTMsg findMstrTMsg) {

        // ------------------------------------------
        // (2)-1check EZDFMsg for upload
        // ------------------------------------------
        EZDFMsg fmsg4Upld = ZYPL0220CommonLogic.newUpldEZDFMsg(cMsg);
        if (fmsg4Upld == null) {
            return false;
        }

        // ------------------------------------------
        // (2)-2check EZDFMsg for upload
        // ------------------------------------------
        EZDFMsg fmsg4Dnld = ZYPL0220CommonLogic.newDnldEZDFMsg(cMsg);
        if (fmsg4Dnld == null) {
            return false;
        }

        // ------------------------------------------
        // (2)-3 check EZDTMsg
        // ------------------------------------------
        EZDTMsg tmsg = ZYPL0220CommonLogic.newEZDTMsg(cMsg);
        if (tmsg == null) {
            return false;
        }

        // ------------------------------------------
        // (2)-4 check UPLD_CSV_MSTR
        // ------------------------------------------
        if (findMstrTMsg == null) {
            cMsg.setTransactionMode("0");
            cMsg.setMessageInfo(MSG_CD_NOT_FOUND_UPLD_CSV_MSTR);
            return false;
        }

        // ------------------------------------------
        // (2)-5 check Timestamp
        // ------------------------------------------
        String scrnEzUpTime = cMsg.ezUpTime.getValue();
        String scrnEzUpTimeZone = cMsg.ezUpTimeZone.getValue();
        String dbEzUpTime = findMstrTMsg.ezUpTime.getValue();
        String dbEzUpTimeZone = findMstrTMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(scrnEzUpTime, scrnEzUpTimeZone, dbEzUpTime, dbEzUpTimeZone)) {
            cMsg.setTransactionMode("0");
            cMsg.setMessageInfo(MSG_CD_NOT_SAME_TIMESTAMP);
            return false;
        }

        // ------------------------------------------
        // (2)-6 check data changed
        // ------------------------------------------
        boolean upldCsvNmUpdateFlg = !(cMsg.upldCsvNm.getValue()).equals(findMstrTMsg.upldCsvNm.getValue());
        boolean upldCsvFileIdUpdateFlg = !(cMsg.upldCsvFileId.getValue()).equals(findMstrTMsg.upldCsvFileId.getValue());
        boolean upldCsvTempTblIdUpdateFlg = !(cMsg.upldCsvTempTblId.getValue()).equals(findMstrTMsg.upldCsvTempTblId.getValue());
        boolean ezReqBusinessIDUpdateFlg = !(cMsg.ezReqBusinessID.getValue()).equals(findMstrTMsg.ezReqBusinessID.getValue());
        if (upldCsvNmUpdateFlg || upldCsvFileIdUpdateFlg || upldCsvTempTblIdUpdateFlg || ezReqBusinessIDUpdateFlg) {
            updateUpldCsvMstr(cMsg, findMstrTMsg);
        }

        return true;
    }

    /**
     * UPLD_CSV_HDRテーブルの更新処理を行います。
     * @param cMsg
     * @param findMstrTMsg
     * @return
     * @throws FailedUpdateException
     */
    private boolean doUpdateUpldCsvHdr(ZYPL0220CMsg cMsg, UPLD_CSV_MSTRTMsg findMstrTMsg) {

        // UPLD_CSV_HDR
        UPLD_CSV_HDRTMsgArray findHdrTMsgArray = ZYPL0220CommonLogic.findUpldCsvHdrByUpldCsvId(cMsg, true);
        if (cMsg.A.getValidCount() != findHdrTMsgArray.getValidCount()) {
            if (!deleteUpldCsvHdr(cMsg, findHdrTMsgArray)) {
                return false;
            }
            if (!insertUpldCsvHdr(cMsg)) {
                return false;
            }
            return true;
        }

        if (!cMsg.upldCsvFileId.getValue().equals(findMstrTMsg.upldCsvFileId.getValue())) {
            if (!deleteUpldCsvHdr(cMsg, findHdrTMsgArray)) {
                return false;
            }
            if (!insertUpldCsvHdr(cMsg)) {
                return false;
            }
            return true;
        }

        // UPLD_CSV_HDRをfindByConditionForUpdateで検索し、更新する必要があるかチェックする。
        for (int i = 0; i < findHdrTMsgArray.length(); i++) {

            // comp timestamp
            String scrnUpTime = cMsg.A.no(i).ezUpTime_0D.getValue();
            String scrnEzUpTimeZone = cMsg.A.no(i).ezUpTimeZone_0D.getValue();
            String dbEzUpTime = findHdrTMsgArray.no(i).ezUpTime.getValue();
            String dbEzUpTimeZone = findHdrTMsgArray.no(i).ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(scrnUpTime, scrnEzUpTimeZone, dbEzUpTime, dbEzUpTimeZone)) {
                cMsg.setMessageInfo(MSG_CD_NOT_SAME_TIMESTAMP);
                cMsg.A.no(i).upldCsvHdrNm.setErrorInfo(1, MSG_CD_NOT_SAME_TIMESTAMP);
                return false;
            }

            String scrnUpldCsvHdrNm = cMsg.A.no(i).upldCsvHdrNm.getValue();
            String dbUpldCsvHdrNm = findHdrTMsgArray.no(i).upldCsvHdrNm.getValue();

            if (!(scrnUpldCsvHdrNm.equals(dbUpldCsvHdrNm))) {
                String upldCsvHdrNm = cMsg.A.no(i).upldCsvHdrNm.getValue();
                boolean uploadFlg = this.updateUpldCsvHdr(cMsg, findHdrTMsgArray.no(i), upldCsvHdrNm);
                if (!uploadFlg) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * UPLD_CSV_RSC_PROCテーブルのレコードを更新します。
     * @param cMsg 業務アプリケーションメッセージ
     * @return 処理結果
     */
    private boolean doUpdateUpldCsvRstProc(ZYPL0220CMsg cMsg) {
        UPLD_CSV_RST_PROCTMsgArray findTMsgArray = ZYPL0220CommonLogic.findUpldCsvRstProc(cMsg, true);

        // UPLD_CSV_RST_PROCをfindByConditionForUpdateで検索し、更新する必要があるかチェックする。
        boolean updateFlg = false;

        int inputCnt = 0;
        for (int i = 0; i < cMsg.B.length(); i++) {
            if (!cMsg.B.no(i).upldCsvRstProcNm.isClear()) {
                inputCnt++;
            }
        }
        if (findTMsgArray.getValidCount() != inputCnt) {
            updateFlg = true;
        }

        for (int i = 0; i < findTMsgArray.length(); i++) {
            if (!updateFlg) {
                String scrnuUldCsvRstProcNm = cMsg.B.no(i).upldCsvRstProcNm.getValue();
                String dbUpldCsvRstProcNm = findTMsgArray.no(i).upldCsvRstProcNm.getValue();
                updateFlg = !(scrnuUldCsvRstProcNm.equals(dbUpldCsvRstProcNm));
            }

            // comp timestamp
            String scrnUpTime = cMsg.B.no(i).ezUpTime_1B.getValue();
            String scrnEzUpTimeZone = cMsg.B.no(i).ezUpTimeZone_1B.getValue();
            String dbEzUpTime = findTMsgArray.no(i).ezUpTime.getValue();
            String dbEzUpTimeZone = findTMsgArray.no(i).ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(scrnUpTime, scrnEzUpTimeZone, dbEzUpTime, dbEzUpTimeZone)) {
                cMsg.setMessageInfo(MSG_CD_NOT_SAME_TIMESTAMP);
                cMsg.B.no(i).upldCsvRstProcNm.setErrorInfo(1, MSG_CD_NOT_SAME_TIMESTAMP);
                return false;
            }

        }

        if (updateFlg) {
            if (!deleteUpldCsvRstProc(cMsg, findTMsgArray)) {
                return false;
            }

            if (!insertUpldCsvRstProc(cMsg)) {
                return false;
            }
        }

        return true;

    }

    /**
     * UPLD_CSV_RST_BIZ_APP_IDテーブルの更新処理を行います。
     * @param cMsg
     * @return
     */
    private boolean doUpdateUpldCsvRstBizAppId(ZYPL0220CMsg cMsg) {
        UPLD_CSV_RST_BIZ_APP_IDTMsgArray findTMsgArray = ZYPL0220CommonLogic.findUpldCsvRstBizAppId(cMsg, true);

        // UPLD_CSV_RST_BIZ_APP_IDをfindByConditionForUpdateで検索し、更新する必要があるかチェックする。
        boolean updateFlg = false;

        int inputCnt = 0;
        for (int i = 0; i < cMsg.C.length(); i++) {
            if (!cMsg.C.no(i).upldCsvRstBizAppId.isClear()) {
                inputCnt++;
            }
        }
        if (findTMsgArray.getValidCount() != inputCnt) {
            updateFlg = true;
        }

        for (int i = 0; i < findTMsgArray.length(); i++) {
            if (!updateFlg) {
                String scrnuUldCsvRstBizId = cMsg.C.no(i).upldCsvRstBizAppId.getValue();
                String dbUpldCsvRstBizId = findTMsgArray.no(i).upldCsvRstBizAppId.getValue();
                updateFlg = !(scrnuUldCsvRstBizId.equals(dbUpldCsvRstBizId));
            }

            // comp timestamp
            String scrnUpTime = cMsg.C.no(i).ezUpTime_1C.getValue();
            String scrnEzUpTimeZone = cMsg.C.no(i).ezUpTimeZone_1C.getValue();
            String dbEzUpTime = findTMsgArray.no(i).ezUpTime.getValue();
            String dbEzUpTimeZone = findTMsgArray.no(i).ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(scrnUpTime, scrnEzUpTimeZone, dbEzUpTime, dbEzUpTimeZone)) {
                cMsg.setMessageInfo(MSG_CD_NOT_SAME_TIMESTAMP);
                cMsg.C.no(i).upldCsvRstBizAppId.setErrorInfo(1, MSG_CD_NOT_SAME_TIMESTAMP);
                return false;
            }
        }

        if (updateFlg) {
            if (!deleteUpldCsvRstBizAppId(cMsg, findTMsgArray)) {
                return false;
            }
            if (!insertUpldCsvRstBizAppId(cMsg)) {
                return false;
            }
        }

        return true;

    }

    /**
     * UPLD_CSV_MSTRテーブルへ登録処理を行います。
     * @param cMsg
     * @return
     */
    private boolean doAddUpldCsvMstr(ZYPL0220CMsg cMsg) {
        UPLD_CSV_MSTRTMsg findMstrTMsg = new UPLD_CSV_MSTRTMsg();

        // ------------------------------------------
        // (2)-1check EZDFMsg for upload
        // ------------------------------------------
        EZDFMsg fmsg4Upld = ZYPL0220CommonLogic.newUpldEZDFMsg(cMsg);
        if (fmsg4Upld == null) {
            return false;
        }

        // ------------------------------------------
        // (2)-2check EZDFMsg for upload
        // ------------------------------------------
        EZDFMsg fmsg4Dnld = ZYPL0220CommonLogic.newDnldEZDFMsg(cMsg);
        if (fmsg4Dnld == null) {
            return false;
        }

        // ------------------------------------------
        // (2)-3 check EZDTMsg
        // ------------------------------------------
        EZDTMsg tmsg = ZYPL0220CommonLogic.newEZDTMsg(cMsg);
        if (tmsg == null) {
            return false;
        }

        // ------------------------------------------
        // (2)-4 update
        // ------------------------------------------
        findMstrTMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        findMstrTMsg.upldCsvId.setValue(cMsg.upldCsvId.getValue());
        findMstrTMsg.upldCsvNm.setValue(cMsg.upldCsvNm.getValue());
        findMstrTMsg.upldCsvFileId.setValue(cMsg.upldCsvFileId.getValue());
        findMstrTMsg.upldCsvTempTblId.setValue(cMsg.upldCsvTempTblId.getValue());
        findMstrTMsg.ezReqBusinessID.setValue(cMsg.ezReqBusinessID.getValue());
        findMstrTMsg.upldCsvFileClsNm.setValue(fmsg4Upld.getClass().getCanonicalName());
        findMstrTMsg.dnldCsvFileClsNm.setValue(fmsg4Dnld.getClass().getCanonicalName());
        findMstrTMsg.upldCsvTempTblClsNm.setValue(tmsg.getClass().getCanonicalName());

        EZDTBLAccessor.create(findMstrTMsg);
        if (!"0000".equals(findMstrTMsg.getReturnCode())) {
            cMsg.upldCsvId.setErrorInfo(1, MSG_CD_ALREADY_EXIST_UPLD_CSV_MSTR);
            cMsg.setTransactionMode("0");
            return false;
        }

        return true;
    }

    /**
     * UPLD_CSV_HDRテーブルへの登録処理を行います。
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return
     */
    private boolean doAddUpldCsvHdr(ZYPL0220CMsg cMsg) {

        // UPLD_CSV_HDR
        UPLD_CSV_HDRTMsgArray findHdrTMsgArray = ZYPL0220CommonLogic.findUpldCsvHdrByUpldCsvId(cMsg, false);
        if (findHdrTMsgArray.getValidCount() != 0) {
            cMsg.upldCsvId.setErrorInfo(1, MSG_CD_ALREADY_EXIST_UPLD_CSV_HDR);
            cMsg.setTransactionMode("0");
            return false;
        }

        return insertUpldCsvHdr(cMsg);

    }

    /**
     * UPLD_CSV_RST_PROCテーブルへの登録処理を行います。
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return
     */
    private boolean doAddUpldCsvRstProc(ZYPL0220CMsg cMsg) {
        // UPLD_CSV_RST_PROC
        return insertUpldCsvRstProc(cMsg);
    }

    /**
     * UPLD_CSV_RST_BIZ_APP_IDテーブルへの登録処理を行います。
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return
     */
    private boolean doAddUpldCsvRstBizAppId(ZYPL0220CMsg cMsg) {
        // UPLD_CSV_RST_BIZ_APP_ID
        return insertUpldCsvRstBizAppId(cMsg);
    }

    /**
     * UPLD_CSV_MSTR テーブルを主キー検索します。
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return 検索結果
     */
    private UPLD_CSV_MSTRTMsg findUpldCsvMstr(ZYPL0220CMsg cMsg) {
        UPLD_CSV_MSTRTMsg condMstrTMsg = new UPLD_CSV_MSTRTMsg();
        condMstrTMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        condMstrTMsg.upldCsvId.setValue(cMsg.upldCsvId.getValue());
        UPLD_CSV_MSTRTMsg findMstrTMsg = (UPLD_CSV_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(condMstrTMsg);

        if(findMstrTMsg == null){
            cMsg.upldCsvId.setErrorInfo(2, MSG_CD_NOT_FOUND_UPLD_CSV_MSTR);
            return null;
        }

        return findMstrTMsg;
    }

    /**
     * UPLD_CSV_MSTRテーブルを1件更新します。
     * @param cMsg 業務アプリインターフェースメッセージ
     * @param findMstrTMsg 更新するレコードのTMsg
     */
    private boolean updateUpldCsvMstr(ZYPL0220CMsg cMsg, UPLD_CSV_MSTRTMsg findMstrTMsg) {
        findMstrTMsg.upldCsvNm.setValue(cMsg.upldCsvNm.getValue());
        findMstrTMsg.upldCsvFileId.setValue(cMsg.upldCsvFileId.getValue());
        findMstrTMsg.upldCsvTempTblId.setValue(cMsg.upldCsvTempTblId.getValue());
        findMstrTMsg.ezReqBusinessID.setValue(cMsg.ezReqBusinessID.getValue());
        EZDTBLAccessor.update(findMstrTMsg);
        if (!"0000".equals(findMstrTMsg.getReturnCode())) {
            cMsg.setTransactionMode("0");
            cMsg.setMessageInfo(MSG_CD_NOT_FOUND_UPLD_CSV_MSTR);
            return false;
        }
        return true;
    }

    /**
     * UPLD_CSV_HDRを1件更新します。
     * @param cMsg
     * @param findHdrTMsg
     * @param scrnUpldCsvHdrNm
     * @return
     */
    private boolean updateUpldCsvHdr(ZYPL0220CMsg cMsg, UPLD_CSV_HDRTMsg findHdrTMsg, String scrnUpldCsvHdrNm) {

        findHdrTMsg.upldCsvHdrNm.setValue(scrnUpldCsvHdrNm);
        EZDTBLAccessor.update(findHdrTMsg);
        if (!"0000".equals(findHdrTMsg.getReturnCode())) {
            cMsg.setTransactionMode("0");
            cMsg.setMessageInfo(MSG_CD_NOT_FOUND_UPLD_CSV_HDR);
            return false;
        }

        return true;
    }

    /**
     * logical remove UPLD_CSV_HDR
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return 検索結果
     */
    private boolean deleteUpldCsvHdr(ZYPL0220CMsg cMsg, UPLD_CSV_HDRTMsgArray findHdrTMsgArray) {
        for (int i = 0; i < findHdrTMsgArray.length(); i++) {
            EZDTBLAccessor.logicalRemove(findHdrTMsgArray.no(i));
            if (!"0000".equals(findHdrTMsgArray.no(i).getReturnCode())) {
                cMsg.setTransactionMode("0");
                cMsg.setMessageInfo(MSG_CD_NOT_FOUND_UPLD_CSV_HDR);
                return false;
            }
        }
        return true;
    }

    /**
     * Insert to UPLD_CSV_HDR
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return 検索結果
     */
    private boolean insertUpldCsvHdr(ZYPL0220CMsg cMsg) {
        UPLD_CSV_HDRTMsg condHdrTMsg = new UPLD_CSV_HDRTMsg();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            condHdrTMsg.clear();

            condHdrTMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
            condHdrTMsg.upldCsvId.setValue(cMsg.upldCsvId.getValue());
            condHdrTMsg.upldCsvHdrNum.setValue(i + 1);
            condHdrTMsg.upldCsvHdrNm.setValue(cMsg.A.no(i).upldCsvHdrNm.getValue());
            condHdrTMsg.upldCsvHdrDataTpNm.setValue(cMsg.A.no(i).upldCsvHdrDataTpNm.getValue());
            condHdrTMsg.upldCsvHdrDataLg.setValue(cMsg.A.no(i).upldCsvHdrDataLg.getValue());
            condHdrTMsg.upldCsvHdrDefNm.setValue(cMsg.A.no(i).upldCsvHdrDefNm.getValue());

            EZDTBLAccessor.create(condHdrTMsg);
            if (!"0000".equals(condHdrTMsg.getReturnCode())) {
                cMsg.setTransactionMode("0");
                cMsg.A.no(i).upldCsvHdrNm.setErrorInfo(1, MSG_CD_ALREADY_EXIST_UPLD_CSV_HDR);
                return false;
            }
        }

        return true;

    }

    /**
     * UPLD_CSV_RST_PROCテーブルに対して論理削除処理を行います。
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return 検索結果
     */
    private boolean deleteUpldCsvRstProc(ZYPL0220CMsg cMsg, UPLD_CSV_RST_PROCTMsgArray findTMsgArray) {
        for (int i = 0; i < findTMsgArray.getValidCount(); i++) {
            EZDTBLAccessor.logicalRemove(findTMsgArray.no(i));
            if (!"0000".equals(findTMsgArray.no(i).getReturnCode())) {
                cMsg.setTransactionMode("0");
                cMsg.setMessageInfo(MSG_CD_NOT_FOUND_UPLD_CSV_RST_PROC);
                return false;
            }
        }

        return true;
    }

    /**
     * UPLD_CSV_RST_PROCテーブルに対して新規登録処理を行います。
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return 検索結果
     */
    private boolean insertUpldCsvRstProc(ZYPL0220CMsg cMsg) {
        UPLD_CSV_RST_PROCTMsg condTMsg = new UPLD_CSV_RST_PROCTMsg();
        for (int i = 0; i < cMsg.B.length(); i++) {
            if (cMsg.B.no(i).upldCsvRstProcNm.isClear()) {
                continue;
            }

            condTMsg.clear();

            condTMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
            condTMsg.upldCsvId.setValue(cMsg.upldCsvId.getValue());
            condTMsg.upldCsvRstProcNm.setValue(cMsg.B.no(i).upldCsvRstProcNm.getValue());

            EZDTBLAccessor.create(condTMsg);
            if (!"0000".equals(condTMsg.getReturnCode())) {
                cMsg.setTransactionMode("0");
                cMsg.setMessageInfo(MSG_CD_NOT_FOUND_UPLD_CSV_RST_PROC);
                return false;
            }
        }

        return true;

    }

    /**
     * UPLD_CSV_BIZ_APP_IDテーブルに対して論理削除処理を行います。
     * @param cMsg 業務アプリインターフェースメッセージ
     * @return 検索結果
     */
    private boolean deleteUpldCsvRstBizAppId(ZYPL0220CMsg cMsg, UPLD_CSV_RST_BIZ_APP_IDTMsgArray findTMsgArray) {
        for (int i = 0; i < findTMsgArray.length(); i++) {
            EZDTBLAccessor.logicalRemove(findTMsgArray.no(i));
            if (!"0000".equals(findTMsgArray.no(i).getReturnCode())) {
                cMsg.setTransactionMode("0");
                cMsg.setMessageInfo(MSG_CD_NOT_FOUND_UPLD_CSV_RST_BIZ_APP_ID);
                return false;
            }
        }

        return true;
    }

    /**
     * insert to UPLD_CSV_RST_BIZ_APP_ID
     * @param cMsg
     * @return
     */
    private boolean insertUpldCsvRstBizAppId(ZYPL0220CMsg cMsg) {
        UPLD_CSV_RST_BIZ_APP_IDTMsg condTMsg = new UPLD_CSV_RST_BIZ_APP_IDTMsg();
        for (int i = 0; i < cMsg.B.length(); i++) {
            if (cMsg.C.no(i).upldCsvRstBizAppId.isClear()) {
                continue;
            }

            condTMsg.clear();

            condTMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
            condTMsg.upldCsvId.setValue(cMsg.upldCsvId.getValue());
            condTMsg.upldCsvRstBizAppId.setValue(cMsg.C.no(i).upldCsvRstBizAppId.getValue());

            EZDTBLAccessor.create(condTMsg);
            if (!"0000".equals(condTMsg.getReturnCode())) {
                cMsg.setTransactionMode("0");
                cMsg.setMessageInfo(MSG_CD_ALREADY_EXIST_UPLD_CSV_RST_BIZ_APP_ID);
                return false;
            }
        }

        return true;

    }

}
