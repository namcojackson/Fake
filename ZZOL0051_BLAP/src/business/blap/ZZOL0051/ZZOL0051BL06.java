package business.blap.ZZOL0051;

import java.util.HashMap;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0051.common.ZZOL0051CommonLogic;
import business.blap.ZZOL0051.constant.ZZOL0051Constant;
import business.db.GLBL_CMPYTMsg;
import business.db.UPLD_CSV_HDRTMsg;
import business.db.UPLD_CSV_MSTRTMsg;
import business.db.UPLD_CSV_RST_BIZ_APP_IDTMsg;
import business.db.UPLD_CSV_RST_PROCTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZOL0051BL06 extends S21BusinessHandler implements ZZOL0051Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZOL0051Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZOL0051Scrn00_CMN_Submit((ZZOL0051CMsg) cMsg, (ZZOL0051SMsg) sMsg);

            } else if ("ZZOL0051Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0051Scrn00_CMN_Delete((ZZOL0051CMsg) cMsg, (ZZOL0051SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * submit processing
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     */
    private void doProcess_ZZOL0051Scrn00_CMN_Submit(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {

        if ( !chkCsvClsNm(cMsg, sMsg) ) {
            return;
        }
        
        if ( !updCsvHdr(cMsg, sMsg) ) {
            return;
        }
        if ( !updCsvRstBizAppId(cMsg, sMsg) ) {
            return;
        }
        if ( !updCsvRstProc(cMsg, sMsg) ) {
            return;
        }

        updCsvMstr(cMsg, sMsg);
    }


    /**
     * delete processing
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     */
    private void doProcess_ZZOL0051Scrn00_CMN_Delete(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        String curttab = cMsg.xxDplyTab.getValue();
        if ( curttab.equals( TAB_BIZAPP ) ) {
            delCsvRstBizAppId(cMsg, sMsg);
        } else if ( curttab.equals( TAB_PROCID ) ) {
            delCsvRstProc(cMsg, sMsg);
        }
    }
        
    /**************************************************************************************************/
    /**
     * File ID and Table ID check
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     * @return boolean  true:Success false:failed
     */
    private boolean chkCsvClsNm(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        boolean rtnFlg = true;
        String cvsId = null;
        String clsNm = null;
        
        // existence check Global Company Code
        GLBL_CMPYTMsg chkMsg = new GLBL_CMPYTMsg();
        chkMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        GLBL_CMPYTMsg glblComtTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(chkMsg);
        if ( glblComtTMsg == null ) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] { "Global Company CD" });
            rtnFlg = false;
        }

        cvsId = cMsg.upldCsvFileId.getValue();
        if ( !cvsId.equals( sMsg.upldCsvFileId.getValue() ) ) {
            //cMsg.setMessageInfo("ZZZM9026E", new String[] { "File ID" });
            cMsg.upldCsvFileId.setErrorInfo(1, "ZZZM9026E", new String[] { "File ID" });
            rtnFlg = false;
        }

        cvsId = cMsg.upldCsvTempTblId.getValue();
        if ( !cvsId.equals( sMsg.upldCsvTempTblId.getValue() ) ) {
            
            clsNm = ZZOL0051CommonLogic.getEZDClsNm( EZDFMSG_DB, cvsId );            
            if ( clsNm == null ) {
                //cMsg.setMessageInfo("ZZZM9006E", new String[] { "Table ID" });
                cMsg.upldCsvTempTblId.setErrorInfo(1, "ZZZM9006E", new String[] { "Table ID" });
                return false;
            } else {
                sMsg.dnldCsvFileClsNm.setValue( clsNm );
            }
            
            clsNm = ZZOL0051CommonLogic.getEZDClsNm( EZDTMSG_DB, cvsId );
            if ( clsNm == null ) {
                //cMsg.setMessageInfo("ZZZM9006E", new String[] { "Table ID" });
                cMsg.upldCsvTempTblId.setErrorInfo(1, "ZZZM9006E", new String[] { "Table ID" });
                rtnFlg = false;
            } else {
                sMsg.upldCsvTempTblClsNm.setValue( clsNm );
                sMsg.upldCsvTempTblId.setValue( cvsId );
            }
        }
        
        return rtnFlg;
    }

    /**************************************************************************************************/
    /**
     * Insert/Update UPLD_CSV_MSTR
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     * @return boolean  true:Success false:failed
     */
    private boolean updCsvMstr(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        SCRMSG scrMsg = null;

        boolean newFlg = true;
        if ( sMsg.upldCsvId.getValue().length() > 0 ) {
            newFlg = false;
        }

        // set search parameter
        UPLD_CSV_MSTRTMsg cond = new UPLD_CSV_MSTRTMsg();
        cond.glblCmpyCd.setValue( cMsg.glblCmpyCd.getValue() );
        cond.upldCsvId.setValue(  cMsg.upldCsvId.getValue() );

        // get search result
        UPLD_CSV_MSTRTMsg regTMsg = (UPLD_CSV_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait( cond );
        if ( regTMsg == null && newFlg) {
            // no record & new mode
            regTMsg = new UPLD_CSV_MSTRTMsg();
            EZDMsg.copy(cond, null, regTMsg, null);
            scrMsg = SCRMSG.INSERT;
        } else if ( regTMsg == null && !newFlg) {
            // no record & uodate mode
            cMsg.setMessageInfo("ZZZM9004E");
            return false;
        } else {
            // get the update date and time and update time zone
            String ezUpTime = sMsg.ezUpTime.getValue();
            String ezUpTimeZone = sMsg.ezUpTimeZone.getValue();

            // check the update date and time and update time zone
            if ( !ezUpTime.equals(regTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(regTMsg.ezUpTimeZone.getValue()) ) {
                cMsg.setMessageInfo("ZZZM9004E");
                return false;
            }
            scrMsg = SCRMSG.UPDATE;
        }

        // set value
        EZDMsg.copy(cMsg, null, regTMsg, null);
        regTMsg.upldCsvFileClsNm.setValue(    sMsg.upldCsvFileClsNm.getValue() );
        regTMsg.dnldCsvFileClsNm.setValue(    sMsg.dnldCsvFileClsNm.getValue() );
        regTMsg.upldCsvTempTblClsNm.setValue( sMsg.upldCsvTempTblClsNm.getValue() );
        
        // create/update
        switch (scrMsg) {
            case INSERT: EZDTBLAccessor.create(regTMsg); break;
            case UPDATE: EZDTBLAccessor.update(regTMsg); break;
        }
        
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(regTMsg.getReturnCode())) {
            cMsg.setMessageInfo(scrMsg.getMsgId(), new String[] {regTMsg.getReturnCode() });
            return false;
        }
        // information message
        cMsg.setMessageInfo("ZZZM9003I", new String[] { scrMsg.getParam() });

        return true;
    }

    /**************************************************************************************************/
    /**
     * Insert/Update UPLD_CSV_HDR
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     * @return boolean  true:Success false:failed
     */
    private boolean updCsvHdr(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        SCRMSG scrMsg = null;

        // set search parameter
        UPLD_CSV_HDRTMsg cond = new UPLD_CSV_HDRTMsg();
        cond.glblCmpyCd.setValue( cMsg.glblCmpyCd.getValue() );
        cond.upldCsvId.setValue(  cMsg.upldCsvId.getValue() );
        
        // for delete
        for (int idx = cMsg.H.getValidCount(); idx <  sMsg.H.getValidCount(); idx++ ) {
            
            // logical remove recode
            cond.upldCsvHdrNum.setValue( sMsg.H.no(idx).upldCsvHdrNum_H.getValue()  );
            EZDTBLAccessor.logicalRemove(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {cond.getReturnCode() });
                return false;
            }
        }

        // for insert/update
        for ( int idx = 0; idx < cMsg.H.getValidCount(); idx++ ) {
            
            cond.upldCsvHdrNum.setValue( cMsg.H.no(idx).upldCsvHdrNum_H.getValue() );
            UPLD_CSV_HDRTMsg regTMsg = (UPLD_CSV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait( cond );
            // search result
            if ( idx < sMsg.H.getValidCount() ) {
                if ( regTMsg == null ) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }

                // get the update date and time and update time zone
                String ezUpTime = sMsg.H.no(idx).ezUpTime_H.getValue();
                String ezUpTimeZone = sMsg.H.no(idx).ezUpTimeZone_H.getValue();

                // check the update date and time and update time zone
                if (!ezUpTime.equals(regTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(regTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }
                scrMsg = SCRMSG.UPDATE;
            } else {
                if ( regTMsg == null ) {
                    regTMsg = new UPLD_CSV_HDRTMsg();
                    EZDMsg.copy(cond, null, regTMsg, null);
                    scrMsg = SCRMSG.INSERT;
                } else {
                    scrMsg = SCRMSG.UPDATE;
                }
            }

            // set value
            EZDMsg.copy(cMsg.H.no(idx), "H", regTMsg, "");
            // Data revision
            String hdrNm = cMsg.H.no(idx).upldCsvHdrDefNm_H.getValue();
            if ( hdrNm.length() == 0 ) {
                hdrNm = cMsg.H.no(idx).upldCsvHdrNm_H.getValue();
            }
            regTMsg.upldCsvHdrNm.setValue( hdrNm );
            regTMsg.upldCsvHdrDefNm.clear();
            
            // create/update
            switch (scrMsg) {
                case INSERT: EZDTBLAccessor.create(regTMsg); break;
                case UPDATE: EZDTBLAccessor.update(regTMsg); break;
            }
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(regTMsg.getReturnCode())) {
                cMsg.setMessageInfo(scrMsg.getMsgId(), new String[] {regTMsg.getReturnCode() });
                return false;
            }
            // information message
            cMsg.setMessageInfo("ZZZM9003I", new String[] { scrMsg.getParam() });
        }

        return true;
    }
    
    /**************************************************************************************************/
    /**
     * Insert/Update UPLD_CSV_RST_BIZ_APP_ID
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     * @return boolean  true:Success false:failed
     */
    private boolean updCsvRstBizAppId(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        String upldCsvRstBizAppId = null;
        SCRMSG scrMsg = null;

        // key parameter set
        UPLD_CSV_RST_BIZ_APP_IDTMsg cond = new UPLD_CSV_RST_BIZ_APP_IDTMsg();
        cond.glblCmpyCd.setValue( cMsg.glblCmpyCd.getValue() );
        cond.upldCsvId.setValue(  cMsg.upldCsvId.getValue() );

        // Data close inspection
        HashMap<String, Integer> sMsgMap = new HashMap<String, Integer>();
        HashMap<String, Integer> cMsgMap = new HashMap<String, Integer>();
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            cMsgMap.put( cMsg.B.no(i).upldCsvRstBizAppId_B.getValue(), i );
        }
        for (int idx = 0; idx < sMsg.B.getValidCount(); idx++) {
            upldCsvRstBizAppId = sMsg.B.no(idx).upldCsvRstBizAppId_B.getValue();

            if ( cMsgMap.containsKey( upldCsvRstBizAppId ) ) {
                sMsgMap.put( upldCsvRstBizAppId, idx );
                continue;
            }
            
            // for delete
            cond.upldCsvRstBizAppId.setValue( upldCsvRstBizAppId );
            UPLD_CSV_RST_BIZ_APP_IDTMsg regTMsg = (UPLD_CSV_RST_BIZ_APP_IDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait( cond );
            if ( regTMsg == null ) {
                cMsg.setMessageInfo("ZZZM9004E");
                return false;
            } else {
                // get the update date and time and update time zone
                String ezUpTime = sMsg.B.no(idx).ezUpTime_B.getValue();
                String ezUpTimeZone = sMsg.B.no(idx).ezUpTimeZone_B.getValue();

                // check the update date and time and update time zone
                if (!ezUpTime.equals(regTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(regTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }
            }
            // logical remove recode
            EZDTBLAccessor.logicalRemove(regTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(regTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {regTMsg.getReturnCode() });
                return false;
            }
        }

        // for insert/update
        for ( int idx = 0; idx < cMsg.B.getValidCount(); idx++ ) {
            upldCsvRstBizAppId = cMsg.B.no(idx).upldCsvRstBizAppId_B.getValue();

            cond.upldCsvRstBizAppId.setValue(  upldCsvRstBizAppId );
            UPLD_CSV_RST_BIZ_APP_IDTMsg regTMsg = (UPLD_CSV_RST_BIZ_APP_IDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait( cond );
            // search result
            if ( sMsgMap.containsKey(upldCsvRstBizAppId) ) {
                if ( regTMsg == null ) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }

                int num = sMsgMap.get( upldCsvRstBizAppId );

                // compare the update date and time and update time zone
                String ezUpTime = sMsg.B.no(num).ezUpTime_B.getValue();
                String ezUpTimeZone = sMsg.B.no(num).ezUpTimeZone_B.getValue();

                // check the update date and time and update time zone
                if (!ezUpTime.equals(regTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(regTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }
                scrMsg = SCRMSG.UPDATE;
            } else {
                if ( regTMsg == null ) {
                    regTMsg = new UPLD_CSV_RST_BIZ_APP_IDTMsg();
                    EZDMsg.copy(cond, null, regTMsg, null);
                    scrMsg = SCRMSG.INSERT;
                } else {
                    scrMsg = SCRMSG.UPDATE;
                }
            }
           
            // set value
            EZDMsg.copy(cMsg.H.no(idx), "B", regTMsg, "");
            
            // create/update
            switch (scrMsg) {
                case INSERT: EZDTBLAccessor.create(regTMsg); break;
                case UPDATE: EZDTBLAccessor.update(regTMsg); break;
            }
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(regTMsg.getReturnCode())) {
                cMsg.setMessageInfo(scrMsg.getMsgId(), new String[] {regTMsg.getReturnCode() });
                return false;
            }
            // information message
            cMsg.setMessageInfo("ZZZM9003I", new String[] { scrMsg.getParam() });
        }
        
        return true;
    }

    /**************************************************************************************************/
    /**
     * Insert/Update UPLD_CSV_RST_PROC
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     * @return boolean  true:Success false:failed
     */
    private boolean updCsvRstProc(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        String upldCsvRstProcNm = null;
        SCRMSG scrMsg = null;

        // key parameter set
        UPLD_CSV_RST_PROCTMsg cond = new UPLD_CSV_RST_PROCTMsg();
        cond.glblCmpyCd.setValue( cMsg.glblCmpyCd.getValue() );
        cond.upldCsvId.setValue(  cMsg.upldCsvId.getValue() );

        // Data close inspection
        HashMap<String, Integer> sMsgMap = new HashMap<String, Integer>();
        HashMap<String, Integer> cMsgMap = new HashMap<String, Integer>();
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            cMsgMap.put( cMsg.P.no(i).upldCsvRstProcNm_P.getValue(), i );
        }
        for (int idx = 0; idx < sMsg.P.getValidCount(); idx++) {
            upldCsvRstProcNm = sMsg.P.no(idx).upldCsvRstProcNm_P.getValue();

            if ( cMsgMap.containsKey( upldCsvRstProcNm ) ) {
                sMsgMap.put( upldCsvRstProcNm, idx );
                continue;
            }
            
            // for delete
            cond.upldCsvRstProcNm.setValue( upldCsvRstProcNm );
            UPLD_CSV_RST_PROCTMsg regTMsg = (UPLD_CSV_RST_PROCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait( cond );
            if ( regTMsg == null ) {
                cMsg.setMessageInfo("ZZZM9004E");
                return false;
            } else {
                // get the update date and time and update time zone
                String ezUpTime = sMsg.P.no(idx).ezUpTime_P.getValue();
                String ezUpTimeZone = sMsg.P.no(idx).ezUpTimeZone_P.getValue();

                // check the update date and time and update time zone
                if (!ezUpTime.equals(regTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(regTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }
            }
            // logical remove recode
            EZDTBLAccessor.logicalRemove(regTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(regTMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {regTMsg.getReturnCode() });
                return false;
            }
        }
        
        for ( int idx = 0; idx < cMsg.P.getValidCount(); idx++ ) {
            upldCsvRstProcNm = cMsg.P.no(idx).upldCsvRstProcNm_P.getValue();

            cond.upldCsvRstProcNm.setValue( upldCsvRstProcNm );
            UPLD_CSV_RST_PROCTMsg regTMsg = (UPLD_CSV_RST_PROCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait( cond );
            // search result
            if ( sMsgMap.containsKey(upldCsvRstProcNm) ) {
                if ( regTMsg == null ) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }

                int num = sMsgMap.get( upldCsvRstProcNm );

                // compare the update date and time and update time zone
                String ezUpTime = sMsg.P.no(num).ezUpTime_P.getValue();
                String ezUpTimeZone = sMsg.P.no(num).ezUpTimeZone_P.getValue();

                // check the update date and time and update time zone
                if (!ezUpTime.equals(regTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(regTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    return false;
                }
                scrMsg = SCRMSG.UPDATE;
            } else {
                if ( regTMsg == null ) {
                    regTMsg = new UPLD_CSV_RST_PROCTMsg();
                    EZDMsg.copy(cond, null, regTMsg, null);
                    scrMsg = SCRMSG.INSERT;
                } else {
                    scrMsg = SCRMSG.UPDATE;
                }
            }

            // set value
            EZDMsg.copy(cMsg.H.no(idx), "P", regTMsg, "");
            
            // create/update
            switch (scrMsg) {
                case INSERT: EZDTBLAccessor.create(regTMsg); break;
                case UPDATE: EZDTBLAccessor.update(regTMsg); break;
            }
            
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(regTMsg.getReturnCode())) {
                cMsg.setMessageInfo(scrMsg.getMsgId(), new String[] {regTMsg.getReturnCode() });
                return false;
            }
            // information message
            cMsg.setMessageInfo("ZZZM9003I", new String[] { scrMsg.getParam() });
        }
    
        return true;
    }

    /**************************************************************************************************/
    /**
     * Delete UPLD_CSV_RST_BIZ_APP_ID
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     */
    private void delCsvRstBizAppId(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        // Storage the key to EZDSMsg 
        HashMap<String, Integer> sMsgMap = new HashMap<String, Integer>();
        for (int idx = 0; idx < cMsg.B.getValidCount(); idx++) {
            sMsgMap.put(sMsg.B.no(idx).upldCsvRstBizAppId_B.getValue(), idx);
        }
        
        String chkFlgVal = null;
        for (int idx = 0; idx < cMsg.B.getValidCount(); idx++) {

            // checkbox status check
            chkFlgVal = cMsg.B.no(idx).xxChkBox_B.getValue();
            if (chkFlgVal.length() == 0) {
                continue;
            }
            
            // EZDSMsg existence check
            String upldCsvRstBizAppId = cMsg.B.no(idx).upldCsvRstBizAppId_B.getValue();
            if ( upldCsvRstBizAppId.length() == 0 || !sMsgMap.containsKey(upldCsvRstBizAppId) ) {
                continue;
            }
            
            // for logical remove
            UPLD_CSV_RST_BIZ_APP_IDTMsg cond = new UPLD_CSV_RST_BIZ_APP_IDTMsg();
            
            // set parameter
            cond.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            cond.upldCsvId.setValue(cMsg.upldCsvId.getValue());
            cond.upldCsvRstBizAppId.setValue( cMsg.B.no(idx).upldCsvRstBizAppId_B.getValue() );
            
            // get search result
            UPLD_CSV_RST_BIZ_APP_IDTMsg tMsg = (UPLD_CSV_RST_BIZ_APP_IDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(cond);
            if (tMsg == null) {
                cMsg.setMessageInfo("ZZZM9004E");
                return;
            }

            // get the update date and time and update time zone
            int num = sMsgMap.get(upldCsvRstBizAppId);
            String ezUpTime = sMsg.B.no(num).ezUpTime_B.getValue();
            String ezUpTimeZone = sMsg.B.no(num).ezUpTimeZone_B.getValue();

            // check the update date and time and update time zone
            if (!ezUpTime.equals(tMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo("ZZZM9004E");
                return;
            }
            
            // logical remove recode
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {tMsg.getReturnCode() });
                return;
            }
        }
        
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete" });
    }
    
    /**************************************************************************************************/
    /**
     * Delete UPLD_CSV_RST_PROC
     * @param cMsg ZZOL0051CMsg
     * @param sMsg ZZOL0051SMsg
     */
    private void delCsvRstProc(ZZOL0051CMsg cMsg, ZZOL0051SMsg sMsg) {
        
        // Storage the key to EZDSMsg 
        HashMap<String, Integer> sMsgMap = new HashMap<String, Integer>();
        for (int idx = 0; idx < cMsg.P.getValidCount(); idx++) {
            sMsgMap.put(sMsg.P.no(idx).upldCsvRstProcNm_P.getValue(), idx);
        }
        
        String chkFlgVal = null;
        for (int idx = 0; idx < cMsg.P.getValidCount(); idx++) {

            // checkbox status check
            chkFlgVal = cMsg.P.no(idx).xxChkBox_P.getValue();
            if (chkFlgVal.length() == 0) {
                continue;
            }
            
            // EZDSMsg existence check
            String upldCsvRstProcNm = cMsg.P.no(idx).upldCsvRstProcNm_P.getValue();
            if ( upldCsvRstProcNm.length() == 0 || !sMsgMap.containsKey(upldCsvRstProcNm) ) {
                continue;
            }
            
            // for logical remove
            UPLD_CSV_RST_PROCTMsg cond = new UPLD_CSV_RST_PROCTMsg();
            
            // set parameter
            cond.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            cond.upldCsvId.setValue(cMsg.upldCsvId.getValue());
            cond.upldCsvRstProcNm.setValue( cMsg.P.no(idx).upldCsvRstProcNm_P.getValue() );
            
            // get search result
            UPLD_CSV_RST_PROCTMsg tMsg = (UPLD_CSV_RST_PROCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(cond);
            if (tMsg == null) {
                cMsg.setMessageInfo("ZZZM9004E");
                return;
            }

            // get the update date and time and update time zone
            int num = sMsgMap.get(upldCsvRstProcNm);
            String ezUpTime = sMsg.P.no(num).ezUpTime_P.getValue();
            String ezUpTimeZone = sMsg.P.no(num).ezUpTimeZone_P.getValue();

            // check the update date and time and update time zone
            if (!ezUpTime.equals(tMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(tMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo("ZZZM9004E");
                return;
            }
            
            // logical remove recode
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {tMsg.getReturnCode() });
                return;
            }
        }
        
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete" });
    }

}
