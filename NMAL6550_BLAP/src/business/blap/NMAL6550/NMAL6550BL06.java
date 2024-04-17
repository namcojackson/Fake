/*
 * <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Class name: NMAL6550BL06
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.blap.NMAL6550;

import static business.blap.NMAL6550.common.NMAL6550CommonLogic.compareBigDecimal;
import static business.blap.NMAL6550.common.NMAL6550CommonLogic.compareString;
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
import business.blap.NMAL6550.common.NMAL6550CommonLogic;
import business.blap.NMAL6550.constant.NMAL6550Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Class name: NMAL6550BL06
 * <dd>The class explanation: Business processing for Component ID : NMAL6550BL06
 * <dd>Remarks:
 * @version
 * @author Q03156                                                     
 *</pre>
 */
public class NMAL6550BL06 extends S21BusinessHandler implements NMAL6550Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6550Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6550Scrn00_CMN_Submit(cMsg, sMsg);
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
     * Method name: doProcess_NMAL6550Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6550Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_CMN_Submit START -----", null);

        NMAL6550CMsg bizMsg = (NMAL6550CMsg) cMsg;
        NMAL6550SMsg globalMsg = (NMAL6550SMsg) sMsg;
        
        NMAL6550CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        if (!NMAL6550CommonLogic.inputCheckAll(bizMsg, globalMsg)) {
            return;
        }
        
        // create insert/update object
        List<GLBL_CMPYTMsg> insList = new ArrayList<GLBL_CMPYTMsg>();
        List<GLBL_CMPYTMsg> updList = new ArrayList<GLBL_CMPYTMsg>();
        createRegistObject(globalMsg, insList, updList);
        
        if (!insList.isEmpty()) {
//            int insListCnt = insList.size();
//            int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new GLBL_CMPYTMsg[insListCnt]));
//            if (insListCnt != rsltCnt) {
//                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{"Code"} );
//                return;
//            }
            for (GLBL_CMPYTMsg insTmsg : insList) {
                EZDTBLAccessor.create(insTmsg);
                if (RTNCD_DUPLICATE.equals(insTmsg.getReturnCode())) {
                    setInsertErrorInfo(bizMsg, globalMsg, insTmsg.glblCmpyCd.getValue());
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
            S21CodeTableAccessor.flushCache("GLBL_CMPY");
        }
        
        bizMsg.xxBtnFlg.setValue(FLG_ON_Y);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6550Scrn00_CMN_Submit END -----", null);

    }
    
    private void setInsertErrorInfo(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg, String code) {

        NMAL6550_ASMsgArray asMsgArray = globalMsg.A;
        bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{ "Code"} );
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6550_ASMsg asMsg = asMsgArray.no(i);
            if (code.equals(asMsg.glblCmpyCd_A1.getValue())) {
                asMsg.glblCmpyCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                NMAL6550CommonLogic.pagenation(bizMsg, globalMsg, NMAL6550CommonLogic.getPageStartRowIndex(i));
                break;
            }
        }
        
    }
    
    private void createRegistObject(NMAL6550SMsg globalMsg, List<GLBL_CMPYTMsg> insList, List<GLBL_CMPYTMsg> updList) {
        
        NMAL6550_ASMsgArray inMsgArray = globalMsg.A;
        NMAL6550_BSMsgArray origMsgArray = globalMsg.B;
        
        int origIdx = 0;
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            NMAL6550_ASMsg inMsg = inMsgArray.no(i);
            if (!hasValue(inMsg.ezUpTime_A1)) {
                GLBL_CMPYTMsg insTMsg = new GLBL_CMPYTMsg();
                setNMAL6550ASMsgToglblCmpyTMsg(inMsg, insTMsg);
                insList.add(insTMsg);
                continue;
            }

            if (isUpdate(origMsgArray, inMsg)) {
                GLBL_CMPYTMsg updTMsg = new GLBL_CMPYTMsg();
                setNMAL6550ASMsgToglblCmpyTMsg(inMsg, updTMsg);
                updList.add(updTMsg);
            }
            origIdx++;
        }
    }
    
    private void setNMAL6550ASMsgToglblCmpyTMsg(NMAL6550_ASMsg inMsg, GLBL_CMPYTMsg glblCmpyTMsg) {
        
        setValue(glblCmpyTMsg.glblCmpyCd, inMsg.glblCmpyCd_A1);
        setValue(glblCmpyTMsg.glblCmpyCd, inMsg.glblCmpyCd_A1);
        setValue(glblCmpyTMsg.glblCmpySortNum, inMsg.glblCmpySortNum_A1);
        setValue(glblCmpyTMsg.glblCmpyNm, inMsg.glblCmpyNm_A1);
        setValue(glblCmpyTMsg.glblCmpyDescTxt, inMsg.glblCmpyDescTxt_A1);
        setValue(glblCmpyTMsg.ctryCd, inMsg.ctryCd_A1);
        setValue(glblCmpyTMsg.stdCcyCd, inMsg.stdCcyCd_A1);
        setValue(glblCmpyTMsg.stdCcyNm, inMsg.stdCcyNm_A1);
        setValue(glblCmpyTMsg.coaAfflCd, inMsg.coaAfflCd_A1);
        setValue(glblCmpyTMsg.acctCd, inMsg.acctCd_A1);
        setValue(glblCmpyTMsg.ezUpTime, inMsg.ezUpTime_A1);
        setValue(glblCmpyTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A1);
        
    }
    
    private void updateCodeTable(NMAL6550CMsg bizMsg, List<GLBL_CMPYTMsg> updList) {
        
        EZDDebugOutput.println(1, "----- updateCodeTable START -----", null);
           
        // for update
        for (GLBL_CMPYTMsg updMsg : updList) {
            GLBL_CMPYTMsg glblCmpyTMsg = findCodeTableByKeyForUpdate(bizMsg, updMsg);
            if (glblCmpyTMsg == null) {
                bizMsg.setMessageInfo(MESSAGE_ID.NZZM0003E.toString());
                return;
            }
        }
        
        int updCnt = updList.size();
        int rsltCnt = S21FastTBLAccessor.update(updList.toArray(new GLBL_CMPYTMsg[updCnt]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0177E.toString(), new String[]{"Tariff"});
            return;
        }
        
        EZDDebugOutput.println(1, "----- updateCodeTable END -----", null);
    }

    
    
    private GLBL_CMPYTMsg findCodeTableByKeyForUpdate(NMAL6550CMsg bizMsg, GLBL_CMPYTMsg updateTMsg) {
        
        GLBL_CMPYTMsg wrkMsg = (GLBL_CMPYTMsg) updateTMsg.clone();
        GLBL_CMPYTMsg beforeUpdateTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
        if (beforeUpdateTMsg == null) {
            return null;
        }
        
        String findEzUpTime = updateTMsg.ezUpTime.getValue();
        String findEzUpTimeZone = updateTMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = beforeUpdateTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = beforeUpdateTMsg.ezUpTimeZone.getValue();

        if ("*".equals(findEzUpTime)) {
            if (hasValue(currentEzUpTime)) {
                return null;
            }
        } else if (!isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }
        return beforeUpdateTMsg;
    }
    
    private boolean isUpdate(NMAL6550_BSMsgArray origMsgArray, NMAL6550_ASMsg inMsg) {
        
        NMAL6550_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
        if (origMsg == null) {
            return false;
        }

        if (compareBigDecimal(origMsg.glblCmpySortNum_B1, inMsg.glblCmpySortNum_A1)) {
            return true;
        }
        
        if (compareString(origMsg.glblCmpyNm_B1, inMsg.glblCmpyNm_A1)) {
            return true;
        }
        
        if (compareString(origMsg.glblCmpyDescTxt_B1, inMsg.glblCmpyDescTxt_A1)) {
            return true;
        }

        if (compareString(origMsg.ctryCd_B1, inMsg.ctryCd_A1)) {
            return true;
        }

        if (compareString(origMsg.stdCcyCd_B1, inMsg.stdCcyCd_A1)) {
            return true;
        }

        if (compareString(origMsg.coaAfflCd_B1, inMsg.coaAfflCd_A1)) {
            return true;
        }

        if (compareString(origMsg.acctCd_B1, inMsg.acctCd_A1)) {
            return true;
        }
        
        return false;
        
    }

    private NMAL6550_BSMsg getOrigMsg(NMAL6550_BSMsgArray origMsgArray, NMAL6550_ASMsg inMsg) {

        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            if (inMsg.glblCmpyCd_A1.getValue().equals(origMsgArray.no(i).glblCmpyCd_B1.getValue())) {
                return origMsgArray.no(i);
            }
        }
        return null;
    }

}
