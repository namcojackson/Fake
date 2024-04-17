/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/14/2010   Fujitsu         K.Tajima        Create          N/A
 *</pre>
 */
package business.blap.NWXL0010;

import static business.blap.NWXL0010.common.NWXL0010CommonLogic.checkSqlStatement;
import static business.blap.NWXL0010.common.NWXL0010CommonLogic.getUserProfService;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWXL0010.constant.NWXL0010Constant;
import business.db.RPT_SQLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NWXL0010BL06 extends S21BusinessHandler implements NWXL0010Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {

            String scrnAplId = cMsg.getScreenAplID();

            NWXL0010CMsg myCMsg = (NWXL0010CMsg) cMsg;

            // --------------------------------------------------
            // Scrn01
            // --------------------------------------------------
            if (scrnAplId.contains(ScrnId.NWXL0010Scrn01.toString())) {

                if (scrnAplId.endsWith("_CMN_Submit")) {
                    doProcess_Scrn01_CMN_Submit(myCMsg);
                    return;
                }

                if (scrnAplId.endsWith("_CMN_Delete")) {
                    doProcess_Scrn01_CMN_Delete(myCMsg);
                    return;
                }
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Scrn01_CMN_Delete.
     * 
     * @param cMsg  NWXL0010CMsg
     */
    private void doProcess_Scrn01_CMN_Delete(NWXL0010CMsg cMsg) {

        RPT_SQLTMsg rptSqlTMsg = new RPT_SQLTMsg();
        setValue(rptSqlTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(rptSqlTMsg.rptSqlId,   cMsg.rptSqlId_01);

        // ***** findByKeyForUpdateNoWait
        rptSqlTMsg = (RPT_SQLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rptSqlTMsg);

        if (rptSqlTMsg == null) {
            cMsg.rptSqlId_01.setErrorInfo(1, MessageId.NZZM0000E.toString());
            return;
        }
        
        // ***** logicalRemove
        EZDTBLAccessor.logicalRemove(rptSqlTMsg);

        if (!RTNCD_NORMAL.equals(rptSqlTMsg.getReturnCode())) {
            cMsg.rptSqlId_01.setErrorInfo(1, MessageId.NZZM0003E.toString());
            return;
        }
    }
    
    /**
     * Scrn01_CMN_Submit.
     * 
     * @param cMsg  NWXL0010CMsg
     */
    private void doProcess_Scrn01_CMN_Submit(NWXL0010CMsg cMsg) {

        // --------------------------------------------------
        // SQL Structure Check.
        // --------------------------------------------------
        boolean isNoemalEnd = checkSqlStatement(cMsg.xxRptSqlTxt_01);
        if (!isNoemalEnd) {
            return;
        }
        
        final RPT_SQLTMsg rptSqlTMsg = new RPT_SQLTMsg();
        setValue(rptSqlTMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(rptSqlTMsg.rptSqlId,   cMsg.rptSqlId_01);

        // ***** findByKeyForUpdateNoWait
        final RPT_SQLTMsg resRptSqlTMsg = (RPT_SQLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rptSqlTMsg);

        // --------------------------------------------------
        // New Entry Mode
        // --------------------------------------------------
        if (isNewEntryMode(cMsg)) {

            if (resRptSqlTMsg != null) {
                cMsg.rptSqlId_01.setErrorInfo(1, MessageId.NWZM0820E.toString());
                return;
            }

            // ***** create
            final boolean isNormalEnd = createRptSql(cMsg);
            if (!isNormalEnd) {
                return;
            }

        // --------------------------------------------------
        // Modification Mode
        // --------------------------------------------------
        } else {

            if (resRptSqlTMsg == null) {
                
                // ***** create
                final boolean isNormalEnd = createRptSql(cMsg);
                if (!isNormalEnd) {
                    return;
                }

            } else {

                if (!isSameTimeStamp(cMsg, resRptSqlTMsg)) {
                    cMsg.setMessageInfo(MessageId.NZZM0003E.toString());
                    return;
                }

                // ***** update
                final boolean isNormalEnd = updateRptSql(cMsg, resRptSqlTMsg);
                if (!isNormalEnd) {
                    return;
                }
            }
        }
    }

    private static boolean createRptSql(NWXL0010CMsg cMsg) {

        final RPT_SQLTMsg rptSqlTMsg = new RPT_SQLTMsg();
        setValue(rptSqlTMsg.glblCmpyCd,         getUserProfService().getGlobalCompanyCode());
        setValue(rptSqlTMsg.rptSqlId,           cMsg.rptSqlId_01);
        setValue(rptSqlTMsg.rptSqlNm,           cMsg.rptSqlNm_01);
        setValue(rptSqlTMsg.rptSqlFuncId,       cMsg.rptSqlFuncId_01);
        setValue(rptSqlTMsg.rptSqlDataTmgTxt,   cMsg.rptSqlDataTmgTxt_01);
        setValue(rptSqlTMsg.rptSqlReqPsnCd,     cMsg.rptSqlReqPsnCd_01);
        setValue(rptSqlTMsg.rptSqlReqDt,        cMsg.rptSqlReqDt_01);
        setValue(rptSqlTMsg.rptSqlRgtnPsnCd,    cMsg.rptSqlRgtnPsnCd_01);
        setValue(rptSqlTMsg.rptSqlRgtnDt,       cMsg.rptSqlRgtnDt_01);
        setValue(rptSqlTMsg.rptSqlDescTxt,      cMsg.rptSqlDescTxt_01);

        // ***** insert
        EZDTBLAccessor.create(rptSqlTMsg);

        final String rtnCd = rptSqlTMsg.getReturnCode();

        if (!RTNCD_NORMAL.equals(rtnCd)) {
            if (RTNCD_DUPLICATE.equals(rtnCd)) {
                cMsg.rptSqlId_01.setErrorInfo(1, MessageId.NWZM0820E.toString());
                return false;
            }
        }

        // ***** update 'RPT_SQL_TXT' (CLOB)
        new RptSqlTxtAccessor(rptSqlTMsg).updateRptSqlTxt(cMsg.xxRptSqlTxt_01.getValue());
        
        return true;
    }
    
    private static boolean isNewEntryMode(NWXL0010CMsg cMsg) {
        return cMsg.xxScrEventNm.getValue().endsWith("_NewReport");
    }

    private static boolean isSameTimeStamp(NWXL0010CMsg cMsg, RPT_SQLTMsg rptSqlTMsg) {
     
        return ZYPDateUtil.isSameTimeStamp(
                    cMsg.ezUpTime.getValue(), 
                    cMsg.ezUpTimeZone.getValue(), 
                    rptSqlTMsg.ezUpTime.getValue(), 
                    rptSqlTMsg.ezUpTimeZone.getValue()
               );
    }

    private static boolean updateRptSql(NWXL0010CMsg cMsg, RPT_SQLTMsg rptSqlTMsg) {
        
        setValue(rptSqlTMsg.rptSqlNm,           cMsg.rptSqlNm_01);
        setValue(rptSqlTMsg.rptSqlDescTxt,      cMsg.rptSqlDescTxt_01);
        setValue(rptSqlTMsg.rptSqlFuncId,       cMsg.rptSqlFuncId_01);
        setValue(rptSqlTMsg.rptSqlDataTmgTxt,   cMsg.rptSqlDataTmgTxt_01);
        setValue(rptSqlTMsg.rptSqlReqPsnCd,     cMsg.rptSqlReqPsnCd_01);
        setValue(rptSqlTMsg.rptSqlReqDt,        cMsg.rptSqlReqDt_01);
        setValue(rptSqlTMsg.rptSqlRgtnPsnCd,    cMsg.rptSqlRgtnPsnCd_01);
        setValue(rptSqlTMsg.rptSqlRgtnDt,       cMsg.rptSqlRgtnDt_01);
        
        // ***** update
        EZDTBLAccessor.update(rptSqlTMsg);

        final String rtnCd = rptSqlTMsg.getReturnCode();

        if (!RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.rptSqlId_01.setErrorInfo(1, MessageId.NZZM0003E.toString());
            return false;
        }

        // ***** update 'RPT_SQL_TXT' (CLOB)
        new RptSqlTxtAccessor(rptSqlTMsg).updateRptSqlTxt(cMsg.xxRptSqlTxt_01.getValue());
        
        return true;
    }
    
}
