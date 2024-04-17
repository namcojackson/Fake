/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Class name: NMAL6460BL06
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         N.Sakamoto      Create          N/A   
 * 2013/08/06   Fujitsu         K.Kimura        Update          WDS#1458 Installment Invoice modification
 * 2013/08/13   Fujitsu         K.Kimura        Update          WDS#1554 Multi Exchange Rate modification                                                
 *</pre>
 */
package business.blap.NMAL6460;

import static business.blap.NMAL6460.common.NMAL6460CommonLogic.compareBigDecimal;
import static business.blap.NMAL6460.common.NMAL6460CommonLogic.compareString;
import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.isSameTimeStamp;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6460.common.NMAL6460CommonLogic;
import business.blap.NMAL6460.constant.NMAL6460Constant;
import business.db.PMT_TERM_CASH_DISCTMsg;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Class name: NMAL6460BL06
 * <dd>The class explanation: Business processing for Component ID : NMAL6460BL06
 * <dd>Remarks:
 * @version
 * @author Q03156                                                     
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 2016/05/19   Hitachi         T.Aoyagi        Update          QC#8569
 *</pre>
 */
public class NMAL6460BL06 extends S21BusinessHandler implements NMAL6460Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6460Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6460Scrn00_CMN_Submit(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL6460Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6460Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6460Scrn00_CMN_Submit START -----", null);

        NMAL6460CMsg bizMsg = (NMAL6460CMsg) cMsg;
        NMAL6460SMsg globalMsg = (NMAL6460SMsg) sMsg;
        
        NMAL6460CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        if (!NMAL6460CommonLogic.inputCheck(bizMsg, globalMsg)) {
            return;
        }
        
        // create insert/update object
        List<PMT_TERM_CASH_DISCTMsg> insList = new ArrayList<PMT_TERM_CASH_DISCTMsg>();
        List<PMT_TERM_CASH_DISCTMsg> updList = new ArrayList<PMT_TERM_CASH_DISCTMsg>();
        createRegistObject(globalMsg, insList, updList);
        
        if (!insList.isEmpty()) {
//            int insListCnt = insList.size();
//            int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new PMT_TERM_CASH_DISCTMsg[insListCnt]));
//            if (insListCnt != rsltCnt) {
//                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
//                return;
//            }
            for (PMT_TERM_CASH_DISCTMsg insTmsg : insList) {
                EZDTBLAccessor.create(insTmsg);
                if (RTNCD_DUPLICATE.equals(insTmsg.getReturnCode())) {
                    setInsertErrorInfo(bizMsg, globalMsg, insTmsg.pmtTermCashDiscCd.getValue());
                    return;
                }
            }
        }
        
        if (!updList.isEmpty()) {
            updateCodeTable(bizMsg, updList);
            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }
        }
        
        if (!insList.isEmpty() || !updList.isEmpty()) {
            S21CodeTableAccessor.flushCache("PMT_TERM_CASH_DISC");
        }
        
        bizMsg.xxBtnFlg.setValue(FLG_ON_Y);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6460Scrn00_CMN_Submit END -----", null);

    }
    
    private void setInsertErrorInfo(NMAL6460CMsg bizMsg, NMAL6460SMsg globalMsg, String code) {

        NMAL6460_ASMsgArray asMsgArray = globalMsg.A;
        bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{ "Code"} );
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6460_ASMsg asMsg = asMsgArray.no(i);
            if (code.equals(asMsg.pmtTermCashDiscCd_A1.getValue())) {
                asMsg.pmtTermCashDiscCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                NMAL6460CommonLogic.pagenation(bizMsg, globalMsg, NMAL6460CommonLogic.getPageStartRowIndex(i));
                break;
            }
        }
    }

    private void createRegistObject(NMAL6460SMsg globalMsg, List<PMT_TERM_CASH_DISCTMsg> insList, List<PMT_TERM_CASH_DISCTMsg> updList) {
        
        NMAL6460_ASMsgArray inMsgArray = globalMsg.A;
        NMAL6460_BSMsgArray origMsgArray = globalMsg.B;
        
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            NMAL6460_ASMsg inMsg = inMsgArray.no(i);
            if (!hasValue(inMsg.ezUpTime_A1)) {
                PMT_TERM_CASH_DISCTMsg insTMsg = new PMT_TERM_CASH_DISCTMsg();
                inMsg.glblCmpyCd_A1.setValue(getGlobalCompanyCode());
                setNMAL6460ASMsgToPmtTermCashDiscTMsg(inMsg, insTMsg);
                insList.add(insTMsg);
                continue;
            }
            
            if (isUpdate(origMsgArray, inMsg)) {
                PMT_TERM_CASH_DISCTMsg updTMsg = new PMT_TERM_CASH_DISCTMsg();
                setNMAL6460ASMsgToPmtTermCashDiscTMsg(inMsg, updTMsg);
                updList.add(updTMsg);
            }
        }
    }
    
   
    private void updateCodeTable(NMAL6460CMsg bizMsg, List<PMT_TERM_CASH_DISCTMsg> updList) {
        
        EZDDebugOutput.println(1, "----- updateCodeTable START -----", null);
           
        // for update
        for (PMT_TERM_CASH_DISCTMsg updMsg : updList) {
            PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = findCodeTableByKeyForUpdate(bizMsg, updMsg);
            if (pmtTermCashDiscTMsg == null) {
                bizMsg.setMessageInfo(MESSAGE_ID.NZZM0003E.toString());
                return;
            }
        }
        
        int updCnt = updList.size();
        int rsltCnt = S21FastTBLAccessor.update(updList.toArray(new PMT_TERM_CASH_DISCTMsg[0]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0177E.toString(), new String[]{"Payment Term Cash Discount"});
            return;
        }
        
        EZDDebugOutput.println(1, "----- updateCodeTable END -----", null);
    }

    private PMT_TERM_CASH_DISCTMsg findCodeTableByKeyForUpdate(NMAL6460CMsg bizMsg, PMT_TERM_CASH_DISCTMsg updCtacTpTMsg) {
        
        PMT_TERM_CASH_DISCTMsg wrkMsg = (PMT_TERM_CASH_DISCTMsg) updCtacTpTMsg.clone();
        PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg = (PMT_TERM_CASH_DISCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
        if (pmtTermCashDiscTMsg == null) {
            return null;
        }
        
        String findEzUpTime = updCtacTpTMsg.ezUpTime.getValue();
        String findEzUpTimeZone = updCtacTpTMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = pmtTermCashDiscTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = pmtTermCashDiscTMsg.ezUpTimeZone.getValue();

        if ("*".equals(findEzUpTime)) {
            if (hasValue(currentEzUpTime)) {
                return null;
            }
        } else if (!isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }
        
        return pmtTermCashDiscTMsg;
    }
    
    private void setNMAL6460ASMsgToPmtTermCashDiscTMsg(NMAL6460_ASMsg inMsg, PMT_TERM_CASH_DISCTMsg pmtTermCashDiscTMsg) {
        
        setValue(pmtTermCashDiscTMsg.glblCmpyCd, inMsg.glblCmpyCd_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermCashDiscCd, inMsg.pmtTermCashDiscCd_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermCashDiscSortNum, inMsg.pmtTermCashDiscSortNum_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermCashDiscNm, inMsg.pmtTermCashDiscNm_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermCashDiscDescTxt, inMsg.pmtTermCashDiscDescTxt_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermCd, inMsg.pmtTermCd_A1);
        setValue(pmtTermCashDiscTMsg.cashDiscTermCd, inMsg.cashDiscTermCd_A1);
        setValue(pmtTermCashDiscTMsg.ediCashDiscDueAot, inMsg.ediCashDiscDueAot_A1);
        setValue(pmtTermCashDiscTMsg.ediCashDiscPct, inMsg.ediCashDiscPct_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermSendFaxFlg, inMsg.pmtTermSendFaxFlg_A1);
        setValue(pmtTermCashDiscTMsg.istlPmtTermFlg, inMsg.istlPmtTermFlg_A1);
        setValue(pmtTermCashDiscTMsg.actlExchRateTpNum, inMsg.actlExchRateTpNum_A1);
        // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
        setValue(pmtTermCashDiscTMsg.pmtTermConslFlg, inMsg.pmtTermConslFlg_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermConslLastDomFlg, inMsg.pmtTermConslLastDomFlg_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermConslDueDay, inMsg.pmtTermConslDueDay_A1);
        setValue(pmtTermCashDiscTMsg.pmtTermConslAddMthNum, inMsg.pmtTermConslAddMthNum_A1);
        setValue(pmtTermCashDiscTMsg.pmtCashFlg, inMsg.pmtCashFlg_A1);
        setValue(pmtTermCashDiscTMsg.pmtCcFlg, inMsg.pmtCcFlg_A1);
        // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
        setValue(pmtTermCashDiscTMsg.ezUpTime, inMsg.ezUpTime_A1);
        setValue(pmtTermCashDiscTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A1);
        
    }
    
    private boolean isUpdate(NMAL6460_BSMsgArray origMsgArray, NMAL6460_ASMsg inMsg) {

        NMAL6460_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
        if (origMsg == null) {
            return false;
        }
            
        if (compareBigDecimal(origMsg.pmtTermCashDiscSortNum_B1, inMsg.pmtTermCashDiscSortNum_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pmtTermCashDiscNm_B1, inMsg.pmtTermCashDiscNm_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pmtTermCashDiscDescTxt_B1, inMsg.pmtTermCashDiscDescTxt_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pmtTermCd_B1, inMsg.pmtTermCd_A1)) {
            return true;
        }
        
        if (compareString(origMsg.cashDiscTermCd_B1, inMsg.cashDiscTermCd_A1)) {
            return true;
        }
        
        if (compareBigDecimal(origMsg.ediCashDiscDueAot_B1, inMsg.ediCashDiscDueAot_A1)) {
            return true;
        }
        
        if (compareBigDecimal(origMsg.ediCashDiscPct_B1, inMsg.ediCashDiscPct_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pmtTermSendFaxFlg_B1, inMsg.pmtTermSendFaxFlg_A1)) {
            return true;
        }
        
        if (compareString(origMsg.istlPmtTermFlg_B1, inMsg.istlPmtTermFlg_A1)) {
            return true;
        }
        if (compareString(origMsg.actlExchRateTpNum_B1, inMsg.actlExchRateTpNum_A1)) {
            return true;
        }
        // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
        if (compareString(origMsg.pmtTermConslFlg_B1, inMsg.pmtTermConslFlg_A1)) {
            return true;
        }
        if (compareString(origMsg.pmtTermConslLastDomFlg_B1, inMsg.pmtTermConslLastDomFlg_A1)) {
            return true;
        }
        if (compareString(origMsg.pmtTermConslDueDay_B1, inMsg.pmtTermConslDueDay_A1)) {
            return true;
        }
        if (compareBigDecimal(origMsg.pmtTermConslAddMthNum_B1, inMsg.pmtTermConslAddMthNum_A1)) {
            return true;
        }
        if (compareString(origMsg.pmtCashFlg_B1, inMsg.pmtCashFlg_A1)) {
            return true;
        }
        if (compareString(origMsg.pmtCcFlg_B1, inMsg.pmtCcFlg_A1)) {
            return true;
        }
        // END 2016/05/19 T.Aoyagi [QC#8569, ADD]
        return false;
        
    }
    
    private NMAL6460_BSMsg getOrigMsg(NMAL6460_BSMsgArray origMsgArray, NMAL6460_ASMsg inMsg) {

        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            if (inMsg.pmtTermCashDiscCd_A1.getValue().equals(origMsgArray.no(i).pmtTermCashDiscCd_B1.getValue())) {
                return origMsgArray.no(i);
            }
        }
        return null;
    }
}
