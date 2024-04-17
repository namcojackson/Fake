/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Class name: NMAL6450BL06
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         N.Sakamoto      Create          N/A   
 *</pre>
 */
package business.blap.NMAL6450;

import static business.blap.NMAL6450.common.NMAL6450CommonLogic.compareBigDecimal;
import static business.blap.NMAL6450.common.NMAL6450CommonLogic.compareString;
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
import business.blap.NMAL6450.common.NMAL6450CommonLogic;
import business.blap.NMAL6450.constant.NMAL6450Constant;
import business.db.PMT_TERMTMsg;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Class name: NMAL6450BL06
 * <dd>The class explanation: Business processing for Component ID : NMAL6450BL06
 * <dd>Remarks:
 * @version
 * @author Q03156                                                     
 *</pre>
 */
public class NMAL6450BL06 extends S21BusinessHandler implements NMAL6450Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6450Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6450Scrn00_CMN_Submit(cMsg, sMsg);
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
     * Method name: doProcess_NMAL6450Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6450Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450Scrn00_CMN_Submit START -----", null);

        NMAL6450CMsg bizMsg = (NMAL6450CMsg) cMsg;
        NMAL6450SMsg globalMsg = (NMAL6450SMsg) sMsg;
        
        NMAL6450CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        if (!NMAL6450CommonLogic.inputCheck(bizMsg, globalMsg)) {
            return;
        }
        
        // create insert/update object
        List<PMT_TERMTMsg> insList = new ArrayList<PMT_TERMTMsg>();
        List<PMT_TERMTMsg> updList = new ArrayList<PMT_TERMTMsg>();
        createRegistObject(globalMsg, insList, updList);
        
        if (!insList.isEmpty()) {
//            int insListCnt = insList.size();
//            int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new PMT_TERMTMsg[insListCnt]));
//            if (insListCnt != rsltCnt) {
//                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
//                return;
//            }
            for (PMT_TERMTMsg insTmsg : insList) {
                EZDTBLAccessor.create(insTmsg);
                if (RTNCD_DUPLICATE.equals(insTmsg.getReturnCode())) {
                    setInsertErrorInfo(bizMsg, globalMsg, insTmsg.pmtTermCd.getValue());
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
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6450Scrn00_CMN_Submit END -----", null);

    }
    
    private void setInsertErrorInfo(NMAL6450CMsg bizMsg, NMAL6450SMsg globalMsg, String code) {

        NMAL6450_ASMsgArray asMsgArray = globalMsg.A;
        bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{ "Code"} );
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6450_ASMsg asMsg = asMsgArray.no(i);
            if (code.equals(asMsg.pmtTermCd_A1.getValue())) {
                asMsg.pmtTermCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                NMAL6450CommonLogic.pagenation(bizMsg, globalMsg, NMAL6450CommonLogic.getPageStartRowIndex(i));
                break;
            }
        }
        
    }

    private void createRegistObject(NMAL6450SMsg globalMsg, List<PMT_TERMTMsg> insList, List<PMT_TERMTMsg> updList) {
        
        NMAL6450_ASMsgArray inMsgArray = globalMsg.A;
        NMAL6450_BSMsgArray origMsgArray = globalMsg.B;
        
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            NMAL6450_ASMsg inMsg = inMsgArray.no(i);
            if (!hasValue(inMsg.ezUpTime_A1)) {
                PMT_TERMTMsg insTMsg = new PMT_TERMTMsg();
                inMsg.glblCmpyCd_A1.setValue(getGlobalCompanyCode());
                setNMAL6450ASMsgToPmtTermTMsg(inMsg, insTMsg);
                insList.add(insTMsg);
                continue;
            }
            
            if (isUpdate(origMsgArray, inMsg)) {
                PMT_TERMTMsg updTMsg = new PMT_TERMTMsg();
                setNMAL6450ASMsgToPmtTermTMsg(inMsg, updTMsg);
                updList.add(updTMsg);
            }
        }
    }
    
    private void updateCodeTable(NMAL6450CMsg bizMsg, List<PMT_TERMTMsg> updList) {
        
        EZDDebugOutput.println(1, "----- updateCodeTable START -----", null);
           
        // for update
        for (PMT_TERMTMsg updMsg : updList) {
            PMT_TERMTMsg pmtTermTMsg = findCodeTableByKeyForUpdate(bizMsg, updMsg);
            if (pmtTermTMsg == null) {
                bizMsg.setMessageInfo(MESSAGE_ID.NZZM0003E.toString());
                return;
            }
        }
        
        int updCnt = updList.size();
        int rsltCnt = S21FastTBLAccessor.update(updList.toArray(new PMT_TERMTMsg[updCnt]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0177E.toString(), new String[]{"Location Role Type"});
            return;
        }
        
        EZDDebugOutput.println(1, "----- updateCodeTable END -----", null);
    }

    private PMT_TERMTMsg findCodeTableByKeyForUpdate(NMAL6450CMsg bizMsg, PMT_TERMTMsg updCtacTpTMsg) {
        
        PMT_TERMTMsg wrkMsg = (PMT_TERMTMsg) updCtacTpTMsg.clone();
        PMT_TERMTMsg pmtTermTMsg = (PMT_TERMTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
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
    
    private void setNMAL6450ASMsgToPmtTermTMsg(NMAL6450_ASMsg inMsg, PMT_TERMTMsg pmtTermTMsg) {
        
        setValue(pmtTermTMsg.glblCmpyCd, inMsg.glblCmpyCd_A1);
        setValue(pmtTermTMsg.pmtTermCd, inMsg.pmtTermCd_A1);
        setValue(pmtTermTMsg.pmtTermSortNum, inMsg.pmtTermSortNum_A1);
        setValue(pmtTermTMsg.pmtTermNm, inMsg.pmtTermNm_A1);
        setValue(pmtTermTMsg.pmtTermDescTxt, inMsg.pmtTermDescTxt_A1);
        setValue(pmtTermTMsg.pmtTermAot, inMsg.pmtTermAot_A1);
        setValue(pmtTermTMsg.ezUpTime, inMsg.ezUpTime_A1);
        setValue(pmtTermTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A1);
        
    }
    
    private boolean isUpdate(NMAL6450_BSMsgArray origMsgArray, NMAL6450_ASMsg inMsg) {

        NMAL6450_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
        if (origMsg == null) {
            return false;
        }
        
        if (compareBigDecimal(origMsg.pmtTermSortNum_B1, inMsg.pmtTermSortNum_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pmtTermNm_B1, inMsg.pmtTermNm_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pmtTermDescTxt_B1, inMsg.pmtTermDescTxt_A1)) {
            return true;
        }
        
        if (compareBigDecimal(origMsg.pmtTermAot_B1, inMsg.pmtTermAot_A1)) {
            return true;
        }
        
        return false;
        
    }
    
    private NMAL6450_BSMsg getOrigMsg(NMAL6450_BSMsgArray origMsgArray, NMAL6450_ASMsg inMsg) {

        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            if (inMsg.pmtTermCd_A1.getValue().equals(origMsgArray.no(i).pmtTermCd_B1.getValue())) {
                return origMsgArray.no(i);
            }
        }
        return null;
    }
}
