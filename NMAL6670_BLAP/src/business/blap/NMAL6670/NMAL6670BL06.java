/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Class name: NMAL6670BL06
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------
 * 2013/08/14   Fujitsu         K.Kimura        Create          WDS#1458 Installment Invoice modification
 *</pre>
 */
package business.blap.NMAL6670;

import static business.blap.NMAL6670.common.NMAL6670CommonLogic.compareBigDecimal;
import static business.blap.NMAL6670.common.NMAL6670CommonLogic.compareString;
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
import business.blap.NMAL6670.NMAL6670CMsg;
import business.blap.NMAL6670.NMAL6670SMsg;
import business.blap.NMAL6670.NMAL6670_ASMsg;
import business.blap.NMAL6670.NMAL6670_ASMsgArray;
import business.blap.NMAL6670.NMAL6670_BSMsg;
import business.blap.NMAL6670.NMAL6670_BSMsgArray;
import business.blap.NMAL6670.common.NMAL6670CommonLogic;
import business.blap.NMAL6670.constant.NMAL6670Constant;
import business.db.ISTL_PMT_TERMTMsg;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Class name: NMAL6670BL06
 * <dd>The class explanation: Business processing for Component ID : NMAL6670BL06
 * <dd>Remarks:
 * @version                                    
 *</pre>
 */
public class NMAL6670BL06 extends S21BusinessHandler implements NMAL6670Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6670Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6670Scrn00_CMN_Submit(cMsg, sMsg);
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
     * Method name: doProcess_NMAL6670Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6670Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6670Scrn00_CMN_Submit START -----", null);

        NMAL6670CMsg bizMsg = (NMAL6670CMsg) cMsg;
        NMAL6670SMsg globalMsg = (NMAL6670SMsg) sMsg;
        
        NMAL6670CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        if (!NMAL6670CommonLogic.inputCheck(bizMsg, globalMsg)) {
            return;
        }
        
        // create insert/update object
        List<ISTL_PMT_TERMTMsg> insList = new ArrayList<ISTL_PMT_TERMTMsg>();
        List<ISTL_PMT_TERMTMsg> updList = new ArrayList<ISTL_PMT_TERMTMsg>();
        createRegistObject(globalMsg, insList, updList);
        
        if (!insList.isEmpty()) {

            for (ISTL_PMT_TERMTMsg insTmsg : insList) {
                EZDTBLAccessor.create(insTmsg);
                if (RTNCD_DUPLICATE.equals(insTmsg.getReturnCode())) {
                    setInsertErrorInfo(bizMsg, globalMsg, insTmsg.istlPmtTermCd.getValue());
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
            S21CodeTableAccessor.flushCache("PMT_TERM");
        }
        
        bizMsg.xxBtnFlg.setValue(FLG_ON_Y);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6670Scrn00_CMN_Submit END -----", null);

    }
    
    private void setInsertErrorInfo(NMAL6670CMsg bizMsg, NMAL6670SMsg globalMsg, String code) {

        NMAL6670_ASMsgArray asMsgArray = globalMsg.A;
        bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{ "Code"} );
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6670_ASMsg asMsg = asMsgArray.no(i);
            if (code.equals(asMsg.istlPmtTermCd_A1.getValue())) {
                asMsg.istlPmtTermCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                NMAL6670CommonLogic.pagenation(bizMsg, globalMsg, NMAL6670CommonLogic.getPageStartRowIndex(i));
                break;
            }
        }
        
    }

    private void createRegistObject(NMAL6670SMsg globalMsg, List<ISTL_PMT_TERMTMsg> insList, List<ISTL_PMT_TERMTMsg> updList) {
        
        NMAL6670_ASMsgArray inMsgArray = globalMsg.A;
        NMAL6670_BSMsgArray origMsgArray = globalMsg.B;
        
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            NMAL6670_ASMsg inMsg = inMsgArray.no(i);
            if (!hasValue(inMsg.ezUpTime_A1)) {
            	ISTL_PMT_TERMTMsg insTMsg = new ISTL_PMT_TERMTMsg();
                inMsg.glblCmpyCd_A1.setValue(getGlobalCompanyCode());
                setNMAL6670ASMsgToPmtTermTMsg(inMsg, insTMsg);
                insList.add(insTMsg);
                continue;
            }
            
            if (isUpdate(origMsgArray, inMsg)) {
            	ISTL_PMT_TERMTMsg updTMsg = new ISTL_PMT_TERMTMsg();
                setNMAL6670ASMsgToPmtTermTMsg(inMsg, updTMsg);
                updList.add(updTMsg);
            }
        }
    }
    
    private void updateCodeTable(NMAL6670CMsg bizMsg, List<ISTL_PMT_TERMTMsg> updList) {
        
        EZDDebugOutput.println(1, "----- updateCodeTable START -----", null);
           
        // for update
        for (ISTL_PMT_TERMTMsg updMsg : updList) {
        	ISTL_PMT_TERMTMsg pmtTermTMsg = findCodeTableByKeyForUpdate(bizMsg, updMsg);
            if (pmtTermTMsg == null) {
                bizMsg.setMessageInfo(MESSAGE_ID.NZZM0003E.toString());
                return;
            }
        }
        
        int updCnt = updList.size();
        int rsltCnt = S21FastTBLAccessor.update(updList.toArray(new ISTL_PMT_TERMTMsg[updCnt]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0177E.toString(), new String[]{"Location Role Type"});
            return;
        }
        
        EZDDebugOutput.println(1, "----- updateCodeTable END -----", null);
    }

    private ISTL_PMT_TERMTMsg findCodeTableByKeyForUpdate(NMAL6670CMsg bizMsg, ISTL_PMT_TERMTMsg updCtacTpTMsg) {
        
    	ISTL_PMT_TERMTMsg wrkMsg = (ISTL_PMT_TERMTMsg) updCtacTpTMsg.clone();
    	ISTL_PMT_TERMTMsg pmtTermTMsg = (ISTL_PMT_TERMTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
        if (pmtTermTMsg == null) {
            return null;
        }
        
        String findEzUpTime = updCtacTpTMsg.ezUpTime.getValue();
        String findEzUpTimeZone = updCtacTpTMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = pmtTermTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = pmtTermTMsg.ezUpTimeZone.getValue();

        if ("*".equals(findEzUpTime)) {
            if (hasValue(currentEzUpTime)) {
                return null;
            }
        } else if (!isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }
        
        return pmtTermTMsg;
    }
    
    private void setNMAL6670ASMsgToPmtTermTMsg(NMAL6670_ASMsg inMsg, ISTL_PMT_TERMTMsg istlPmtTermTMsg) {
        
        setValue(istlPmtTermTMsg.glblCmpyCd, inMsg.glblCmpyCd_A1);
        setValue(istlPmtTermTMsg.istlPmtTermCd, inMsg.istlPmtTermCd_A1);
        setValue(istlPmtTermTMsg.istlPmtTermDtlNum, inMsg.istlPmtTermDtlNum_A1);
        setValue(istlPmtTermTMsg.istlPmtTermAot, inMsg.istlPmtTermAot_A1);
        setValue(istlPmtTermTMsg.istlPmtTermPct, inMsg.istlPmtTermPct_A1);
        setValue(istlPmtTermTMsg.ezUpTime, inMsg.ezUpTime_A1);
        setValue(istlPmtTermTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A1);
        
    }
    
    private boolean isUpdate(NMAL6670_BSMsgArray origMsgArray, NMAL6670_ASMsg inMsg) {

        NMAL6670_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
        if (origMsg == null) {
            return false;
        }
        
        if (compareString(origMsg.istlPmtTermCd_B1, inMsg.istlPmtTermCd_A1)) {
            return true;
        }
        
        if (compareString(origMsg.istlPmtTermDtlNum_B1, inMsg.istlPmtTermDtlNum_A1)) {
            return true;
        }
        
        if (compareBigDecimal(origMsg.istlPmtTermAot_B1, inMsg.istlPmtTermAot_A1)) {
            return true;
        }
        
        if (compareBigDecimal(origMsg.istlPmtTermPct_B1, inMsg.istlPmtTermPct_A1)) {
            return true;
        }
        
        return false;
        
    }
    
    private NMAL6670_BSMsg getOrigMsg(NMAL6670_BSMsgArray origMsgArray, NMAL6670_ASMsg inMsg) {

        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            if (inMsg.istlPmtTermCd_A1.getValue().equals(origMsgArray.no(i).istlPmtTermCd_B1.getValue()) &&
                    inMsg.istlPmtTermDtlNum_A1.getValue().equals(origMsgArray.no(i).istlPmtTermDtlNum_B1.getValue())) {
                return origMsgArray.no(i);
            }
        }
        return null;
    }
}
