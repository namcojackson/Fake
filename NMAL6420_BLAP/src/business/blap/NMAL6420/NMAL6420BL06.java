/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Class name: NMAL6420BL06
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         N.Sakamoto      Create          N/A   
 *</pre>
 */
package business.blap.NMAL6420;

import static business.blap.NMAL6420.common.NMAL6420CommonLogic.compareBigDecimal;
import static business.blap.NMAL6420.common.NMAL6420CommonLogic.compareString;
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
import business.blap.NMAL6420.common.NMAL6420CommonLogic;
import business.blap.NMAL6420.constant.NMAL6420Constant;
import business.db.LOC_ROLE_TPTMsg;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Class name: NMAL6420BL06
 * <dd>The class explanation: Business processing for Component ID : NMAL6420BL06
 * <dd>Remarks:
 * @version
 * @author Q03156                                                     
 *</pre>
 */
public class NMAL6420BL06 extends S21BusinessHandler implements NMAL6420Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6420Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6420Scrn00_CMN_Submit(cMsg, sMsg);
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
     * Method name: doProcess_NMAL6420Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6420Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6420Scrn00_CMN_Submit START -----", null);

        NMAL6420CMsg bizMsg = (NMAL6420CMsg) cMsg;
        NMAL6420SMsg globalMsg = (NMAL6420SMsg) sMsg;
        
        NMAL6420CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        if (!NMAL6420CommonLogic.inputCheck(bizMsg, globalMsg)) {
            return;
        }
        
        // create insert/update object
        List<LOC_ROLE_TPTMsg> insList = new ArrayList<LOC_ROLE_TPTMsg>();
        List<LOC_ROLE_TPTMsg> updList = new ArrayList<LOC_ROLE_TPTMsg>();
        createRegistObject(globalMsg, insList, updList);
        
        if (!insList.isEmpty()) {
//            int insListCnt = insList.size();
//            int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new LOC_ROLE_TPTMsg[insListCnt]));
//            if (insListCnt != rsltCnt) {
//                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
//                return;
//            }
            for (LOC_ROLE_TPTMsg insTmsg : insList) {
                EZDTBLAccessor.create(insTmsg);
                if (RTNCD_DUPLICATE.equals(insTmsg.getReturnCode())) {
                    setInsertErrorInfo(bizMsg, globalMsg, insTmsg.locRoleTpCd.getValue());
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
            S21CodeTableAccessor.flushCache("LOC_ROLE_TP");
        }
        
        bizMsg.xxBtnFlg.setValue(FLG_ON_Y);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6420Scrn00_CMN_Submit END -----", null);

    }
    
    private void setInsertErrorInfo(NMAL6420CMsg bizMsg, NMAL6420SMsg globalMsg, String code) {

        NMAL6420_ASMsgArray asMsgArray = globalMsg.A;
        bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{ "Code"} );
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6420_ASMsg asMsg = asMsgArray.no(i);
            if (code.equals(asMsg.locRoleTpCd_A1.getValue())) {
                asMsg.locRoleTpCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                NMAL6420CommonLogic.pagenation(bizMsg, globalMsg, NMAL6420CommonLogic.getPageStartRowIndex(i));
                break;
            }
        }
        
    }
    
    private void createRegistObject(NMAL6420SMsg globalMsg, List<LOC_ROLE_TPTMsg> insList, List<LOC_ROLE_TPTMsg> updList) {
        
        NMAL6420_ASMsgArray inMsgArray = globalMsg.A;
        NMAL6420_BSMsgArray origMsgArray = globalMsg.B;
        
        int origIdx = 0;
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            NMAL6420_ASMsg inMsg = inMsgArray.no(i);
            if (!hasValue(inMsg.ezUpTime_A1)) {
                LOC_ROLE_TPTMsg insTMsg = new LOC_ROLE_TPTMsg();
                inMsg.glblCmpyCd_A1.setValue(getGlobalCompanyCode());
                setNMAL6420ASMsgToLocRoleTpTMsg(inMsg, insTMsg);
                insList.add(insTMsg);
                continue;
            }

            if (isUpdate(origMsgArray, inMsg)) {
                LOC_ROLE_TPTMsg updTMsg = new LOC_ROLE_TPTMsg();
                setNMAL6420ASMsgToLocRoleTpTMsg(inMsg, updTMsg);
                updList.add(updTMsg);
            }
            origIdx++;
        }
    }
    
    private void updateCodeTable(NMAL6420CMsg bizMsg, List<LOC_ROLE_TPTMsg> updList) {
        
        EZDDebugOutput.println(1, "----- updateCodeTable START -----", null);
           
        // for update
        for (LOC_ROLE_TPTMsg updMsg : updList) {
            LOC_ROLE_TPTMsg locRoleTpTMsg = findCodeTableByKeyForUpdate(bizMsg, updMsg);
            if (locRoleTpTMsg == null) {
                bizMsg.setMessageInfo(MESSAGE_ID.NZZM0003E.toString());
                return;
            }
        }
        
        int updCnt = updList.size();
        int rsltCnt = S21FastTBLAccessor.update(updList.toArray(new LOC_ROLE_TPTMsg[updCnt]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0177E.toString(), new String[]{"Location Role Type"});
            return;
        }
        
        EZDDebugOutput.println(1, "----- updateCodeTable END -----", null);
    }

    private LOC_ROLE_TPTMsg findCodeTableByKeyForUpdate(NMAL6420CMsg bizMsg, LOC_ROLE_TPTMsg updCtacTpTMsg) {
        
        LOC_ROLE_TPTMsg wrkMsg = (LOC_ROLE_TPTMsg) updCtacTpTMsg.clone();
        LOC_ROLE_TPTMsg locRoleTpTMsg = (LOC_ROLE_TPTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
        if (locRoleTpTMsg == null) {
            return null;
        }
        
        String findEzUpTime = updCtacTpTMsg.ezUpTime.getValue();
        String findEzUpTimeZone = updCtacTpTMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = locRoleTpTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = locRoleTpTMsg.ezUpTimeZone.getValue();

        if ("*".equals(findEzUpTime)) {
            if (hasValue(currentEzUpTime)) {
                return null;
            }
        } else if (!isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }
        
        return locRoleTpTMsg;
    }
    
    private void setNMAL6420ASMsgToLocRoleTpTMsg(NMAL6420_ASMsg inMsg, LOC_ROLE_TPTMsg locRoleTpTMsg) {
        
        setValue(locRoleTpTMsg.glblCmpyCd, inMsg.glblCmpyCd_A1);
        setValue(locRoleTpTMsg.locRoleTpCd, inMsg.locRoleTpCd_A1);
        setValue(locRoleTpTMsg.locRoleTpSortNum, inMsg.locRoleTpSortNum_A1);
        setValue(locRoleTpTMsg.locRoleTpNm, inMsg.locRoleTpNm_A1);
        setValue(locRoleTpTMsg.locRoleTpDescTxt, inMsg.locRoleTpDescTxt_A1);
        setValue(locRoleTpTMsg.acctCdFlg, inMsg.acctCdFlg_A1);
        setValue(locRoleTpTMsg.locGrpCd, inMsg.locGrpCd_A1);
        setValue(locRoleTpTMsg.ezUpTime, inMsg.ezUpTime_A1);
        setValue(locRoleTpTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A1);
        
    }
    
    private boolean isUpdate(NMAL6420_BSMsgArray origMsgArray, NMAL6420_ASMsg inMsg) {
        
        NMAL6420_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
        if (origMsg == null) {
            return false;
        }

        if (compareBigDecimal(origMsg.locRoleTpSortNum_B1, inMsg.locRoleTpSortNum_A1)) {
            return true;
        }
        
        if (compareString(origMsg.locRoleTpNm_B1, inMsg.locRoleTpNm_A1)) {
            return true;
        }
        
        if (compareString(origMsg.locRoleTpDescTxt_B1, inMsg.locRoleTpDescTxt_A1)) {
            return true;
        }
        
        if (compareString(origMsg.acctCdFlg_B1, inMsg.acctCdFlg_A1)) {
            return true;
        }
        
        if (compareString(origMsg.locGrpCd_B1, inMsg.locGrpCd_A1)) {
            return true;
        }
        
        return false;

    }
    
    private NMAL6420_BSMsg getOrigMsg(NMAL6420_BSMsgArray origMsgArray, NMAL6420_ASMsg inMsg) {
        
        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            if (inMsg.locRoleTpCd_A1.getValue().equals(origMsgArray.no(i).locRoleTpCd_B1.getValue())) {
                return origMsgArray.no(i);
            }
        }
        return null;
    }
    
}
