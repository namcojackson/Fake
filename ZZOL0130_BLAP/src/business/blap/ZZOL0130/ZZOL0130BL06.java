package business.blap.ZZOL0130;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDSeqTable;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0130.common.ZZOL0130CommonLogic;
import business.blap.ZZOL0130.constant.ZZOL0130Constant;
import business.db.DEL_TBL_CONFIGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZOL0130BL06 extends S21BusinessHandler implements ZZOL0130Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZOL0130Scrn00_Add".equals(screenAplID)) {
                doProcess_ZZOL0130Scrn00_Add((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else if ("ZZOL0130Scrn00_Upd".equals(screenAplID)) {
                doProcess_ZZOL0130_CMN_Upd((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else if ("ZZOL0130Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0130_CMN_Delete((ZZOL0130CMsg) cMsg, (ZZOL0130SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_ZZOL0130_CMN_Upd(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {

        if (!isValidGlobalCompanyCD(cMsg)) {
            return;
        }

        // select for update
        DEL_TBL_CONFIGTMsg condTmsg = new DEL_TBL_CONFIGTMsg();
        condTmsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        condTmsg.delTblConfigPk.setValue(cMsg.delTblConfigPk.getValue());
        DEL_TBL_CONFIGTMsg updTmsg = (DEL_TBL_CONFIGTMsg) EZDFastTBLAccessor.findByKeyForUpdate(condTmsg);
        if (updTmsg == null) {
            cMsg.setMessageInfo("ZZOM0013E");
            cMsg.setTransactionMode(ROLLBACK);
            return;
        }

        String timeBefore = cMsg.ezUpTime.getValue();
        String timeZoneBefore = cMsg.ezUpTimeZone.getValue();
        String time = updTmsg.ezUpTime.getValue();
        String timeZone = updTmsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
            cMsg.setMessageInfo("ZZZM9004E");
            cMsg.setTransactionMode(ROLLBACK);
            return;
        }

        // How Update ?
        HOW_UPDATE howUpdate;
        String delTblIdBefore = updTmsg.delTblId.getValue();
        int specEffMthBefore = updTmsg.specEffMthAot.getValueInt();
        if (delTblIdBefore.equalsIgnoreCase(cMsg.delTblId.getValue()) && specEffMthBefore == cMsg.specEffMthAot.getValueInt()) {
            howUpdate = HOW_UPDATE.UPDATE;
        } else {
            howUpdate = HOW_UPDATE.DELETE_INSERT;
        }

        // do
        switch (howUpdate) {
            case UPDATE:
                updTmsg.specEffCancCd.setValue(cMsg.specEffCancCd_L.getValue());
                updTmsg.delTblCmntTxt.setValue(cMsg.delTblCmntTxt.getValue());
                updTmsg.delExecDt.setValue(cMsg.delExecDt.getValue());
                EZDFastTBLAccessor.update(new EZDTMsg[] {updTmsg });
                String updateResult = updTmsg.getReturnCode();
                if (!updateResult.equals(DB_NORMAL_0000)) {
                    cMsg.setMessageInfo("ZZOM0013E", new String[] {updateResult });
                    cMsg.setTransactionMode(ROLLBACK);
                    return;
                }

                break;

            case DELETE_INSERT:
                // logical remove
                EZDFastTBLAccessor.removeLogical(new EZDTMsg[] {updTmsg });
                String deleteResult = updTmsg.getReturnCode();
                if (!deleteResult.equals(DB_NORMAL_0000)) {
                    cMsg.setMessageInfo("ZZZM9014E", new String[] {deleteResult });
                    cMsg.setTransactionMode(ROLLBACK);
                    return;
                }
                
                if (!isAlreadyExistInDB(cMsg)) {
                    return;
                }        
                
                if (!isValidTableId(cMsg)) {
                    return;
                }

                boolean addResult = insertOneRec(cMsg);
                if (!addResult) {
                    return;
                }

                break;

            default:
                throw new S21AbendException("");
        }

        // clear & search
        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        String sDelTblId = cMsg.delTblId_A0.getValue();
        ZZOL0130CommonLogic.doClear(sGlobalCpyCd, sDelTblId, cMsg, sMsg);
        ZZOL0130CommonLogic.getDeleteTableList(cMsg, sMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update" });
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZOL0130Scrn00_Add(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {

        if (!isValidGlobalCompanyCD(cMsg)) {
            return;
        }

        if (!isAlreadyExistInDB(cMsg)) {
            return;
        }

        if (!isValidTableId(cMsg)) {
            return;
        }

        // add
        boolean addResult = insertOneRec(cMsg);
        if (!addResult) {
            return;
        }

        // make screen data
        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        String sDelTblId = cMsg.delTblId_A0.getValue();
        if (cMsg.delTblId_A0.isClear()) {
            sDelTblId = cMsg.delTblId.getValue();
        }

        ZZOL0130CommonLogic.doClear(sGlobalCpyCd, sDelTblId, cMsg, sMsg);
        ZZOL0130CommonLogic.getDeleteTableList(cMsg, sMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Add" });

    }

    private void doProcess_ZZOL0130_CMN_Delete(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {

        if (!isValidGlobalCompanyCD(cMsg)) {
            return;
        }

        copyCheckboxToSMsg(cMsg, sMsg);

        List<Integer> indexList = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);

        // Not input Check
        if (indexList.size() == 0) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {"CHECK BOX" });
            return;
        }

        // logical remove
        for (Integer index : indexList) {
            int i = index;

            // select for update
            DEL_TBL_CONFIGTMsg condTmsg = new DEL_TBL_CONFIGTMsg();
            condTmsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            condTmsg.delTblConfigPk.setValue(sMsg.A.no(i).delTblConfigPk_A1.getValue());
            DEL_TBL_CONFIGTMsg delTmsg = (DEL_TBL_CONFIGTMsg) EZDFastTBLAccessor.findByKeyForUpdate(condTmsg);
            if (delTmsg == null) {
                cMsg.setMessageInfo("ZZZM9004E");
                cMsg.setTransactionMode(ROLLBACK);
                return;
            }

            // is the record updated by other session?
            String timeBefore = sMsg.A.no(i).ezUpTime_A1.getValue();
            String timeZoneBefore = sMsg.A.no(i).ezUpTimeZone_A1.getValue();
            String time = delTmsg.ezUpTime.getValue();
            String timeZone = delTmsg.ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(timeBefore, timeZoneBefore, time, timeZone)) {
                cMsg.setMessageInfo("ZZZM9004E");
                cMsg.setTransactionMode(ROLLBACK);
                return;
            }

            // logical remove
            EZDFastTBLAccessor.removeLogical(new EZDTMsg[] {delTmsg });
            String sReturnCode = delTmsg.getReturnCode();
            if (!sReturnCode.equals(DB_NORMAL_0000)) {
                cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode });
                cMsg.setTransactionMode(ROLLBACK);
                return;
            }
        }

        // make screen data
        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        String sDelTblId = cMsg.delTblId_A0.getValue();
        ZZOL0130CommonLogic.doClear(sGlobalCpyCd, sDelTblId, cMsg, sMsg);
        ZZOL0130CommonLogic.getDeleteTableList(cMsg, sMsg);

        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Delete" });
    }

    /**
     * @param cMsg
     * @return
     */
    private boolean isValidGlobalCompanyCD(ZZOL0130CMsg cMsg) {
        if (ZZOL0130CommonLogic.getGlbCmpNm(cMsg.glblCmpyCd.getValue(), cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE });
            return false;
        }

        return true;
    }

    /**
     * @param cMsg
     */
    private boolean isAlreadyExistInDB(ZZOL0130CMsg cMsg) {
        S21SsmEZDResult duplipateResult = ZZOL0130Query.getInstance().getDuplicateCount(cMsg);
        int duplicateCount = ((Integer) duplipateResult.getResultObject()).intValue();
        if (duplicateCount > 0) {
            cMsg.delTblId.setErrorInfo(1, "ZZZM9015E");
            cMsg.specEffMthAot.setErrorInfo(1, "ZZZM9015E");
            cMsg.setTransactionMode(ROLLBACK);
            return false;
        }
        return true;
    }

    /**
     * @param cMsg
     * @return
     */
    private boolean isValidTableId(ZZOL0130CMsg cMsg) {
//        StringBuilder tMsgClassNmBuf = new StringBuilder();
//        tMsgClassNmBuf.append("business.db.");
//        tMsgClassNmBuf.append(cMsg.delTblId.getValue());
//        tMsgClassNmBuf.append("TMsg");
//        try {
//            Class.forName(tMsgClassNmBuf.toString());
//        } catch (ClassNotFoundException e) {
//            cMsg.delTblId.setErrorInfo(1, "ZZOM0012E");
//            cMsg.setTransactionMode(ROLLBACK);
//            return false;
//        }
    	boolean result = false;
    	Connection con = null;
    	PreparedStatement stm = null;
    	ResultSet rst = null;
    	StringBuffer sql = new StringBuffer();
    	sql.append("SELECT COUNT(*) FROM ");
    	sql.append(cMsg.delTblId.getValue());
    	try {
    		con = EZDConnectionMgr.getInstance().getConnection();
    		stm = con.prepareStatement(sql.toString());
    		rst = stm.executeQuery();
  			result = true;
    	} catch (Exception e) {
            cMsg.delTblId.setErrorInfo(1, "ZZOM0012E");
            cMsg.setTransactionMode(ROLLBACK);
    	} finally {
    		try {
	    		if (rst != null) {
	    			rst.close();
	    		}
	    		if (stm != null) {
	    			stm.close();
	    		}
    		} catch(Exception e) {
                cMsg.delTblId.setErrorInfo(1, "ZZOM0012E");
                cMsg.setTransactionMode(ROLLBACK);
    		}
    	}
        return result;
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void copyCheckboxToSMsg(ZZOL0130CMsg cMsg, ZZOL0130SMsg sMsg) {
        int sMsgIdx = cMsg.xxPageShowFromNum_A0.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            sMsg.A.no(sMsgIdx + i).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());
        }
    }

    /**
     * @param cMsg
     */
    private boolean insertOneRec(ZZOL0130CMsg cMsg) {
        DEL_TBL_CONFIGTMsg addTmsg = new DEL_TBL_CONFIGTMsg();
        addTmsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        addTmsg.delTblConfigPk.setValue(EZDSeqTable.getNumberInt("DEL_TBL_CONFIG_SQ"));
        addTmsg.delTblId.setValue(cMsg.delTblId.getValue());
        addTmsg.specEffCancCd.setValue(cMsg.specEffCancCd_L.getValue());
        addTmsg.specEffMthAot.setValue(cMsg.specEffMthAot.getValue());
        addTmsg.delTblCmntTxt.setValue(cMsg.delTblCmntTxt.getValue());
        addTmsg.delExecDt.setValue(cMsg.delExecDt.getValue());
        EZDTBLAccessor.create(addTmsg);
        String sReturnCode = addTmsg.getReturnCode();
        if (!sReturnCode.equals(DB_NORMAL_0000)) {
            cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode });
            return false;
        }
        return true;
    }
}
