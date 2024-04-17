/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Class name: NMAL6370BL06
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         N.Sakamoto      Create          N/A   
 *</pre>
 */
package business.blap.NMAL6370;

import static business.blap.NMAL6370.common.NMAL6370CommonLogic.compareBigDecimal;
import static business.blap.NMAL6370.common.NMAL6370CommonLogic.compareString;
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
import business.blap.NMAL6370.common.NMAL6370CommonLogic;
import business.blap.NMAL6370.constant.NMAL6370Constant;
import business.db.CTAC_TPTMsg;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Class name: NMAL6370BL06
 * <dd>The class explanation: Business processing for Component ID : NMAL6370BL06
 * <dd>Remarks:
 * @version
 * @author Q03156                                                     
 *</pre>
 */
public class NMAL6370BL06 extends S21BusinessHandler implements NMAL6370Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6370Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6370Scrn00_CMN_Submit(cMsg, sMsg);
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
     * Method name: doProcess_NMAL6370Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6370Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6370Scrn00_CMN_Submit START -----", null);

        NMAL6370CMsg bizMsg = (NMAL6370CMsg) cMsg;
        NMAL6370SMsg globalMsg = (NMAL6370SMsg) sMsg;
        
        NMAL6370CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        if (!NMAL6370CommonLogic.inputCheck(bizMsg, globalMsg)) {
            return;
        }
        
        // create insert/update object
        List<CTAC_TPTMsg> insList = new ArrayList<CTAC_TPTMsg>();
        List<CTAC_TPTMsg> updList = new ArrayList<CTAC_TPTMsg>();
        createRegistObject(globalMsg, insList, updList);
        
        if (!insList.isEmpty()) {
//            int insListCnt = insList.size();
//            int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new CTAC_TPTMsg[insListCnt]));
//            if (insListCnt != rsltCnt) {
//                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
//                return;
//            }
            for (CTAC_TPTMsg insTmsg : insList) {
                EZDTBLAccessor.create(insTmsg);
                if (RTNCD_DUPLICATE.equals(insTmsg.getReturnCode())) {
                    setInsertErrorInfo(bizMsg, globalMsg, insTmsg.ctacTpCd.getValue());
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
            S21CodeTableAccessor.flushCache("CTAC_TP");
        }
        
        bizMsg.xxBtnFlg.setValue(FLG_ON_Y);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6370Scrn00_CMN_Submit END -----", null);

    }
    
    private void setInsertErrorInfo(NMAL6370CMsg bizMsg, NMAL6370SMsg globalMsg, String code) {

        NMAL6370_ASMsgArray asMsgArray = globalMsg.A;
        bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{ "Code"} );
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6370_ASMsg asMsg = asMsgArray.no(i);
            if (code.equals(asMsg.ctacTpCd_A1.getValue())) {
                asMsg.ctacTpCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                NMAL6370CommonLogic.pagenation(bizMsg, globalMsg, NMAL6370CommonLogic.getPageStartRowIndex(i));
                break;
            }
        }
        
    }

    private void createRegistObject(NMAL6370SMsg globalMsg, List<CTAC_TPTMsg> insList, List<CTAC_TPTMsg> updList) {
        
        NMAL6370_ASMsgArray inMsgArray = globalMsg.A;
        NMAL6370_BSMsgArray origMsgArray = globalMsg.B;
        
        int origIdx = 0;
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            NMAL6370_ASMsg inMsg = inMsgArray.no(i);
            if (!hasValue(inMsg.ezUpTime_A1)) {
                CTAC_TPTMsg insCtacTpTMsg = new CTAC_TPTMsg();
                inMsg.glblCmpyCd_A1.setValue(getGlobalCompanyCode());
                setNMAL6370ASMsgToCtacTpTMsg(inMsg, insCtacTpTMsg);
                insList.add(insCtacTpTMsg);
                continue;
            }

            if (isUpdate(origMsgArray, inMsg)) {
                CTAC_TPTMsg updCtacTpTMsg = new CTAC_TPTMsg();
                setNMAL6370ASMsgToCtacTpTMsg(inMsg, updCtacTpTMsg);
                updList.add(updCtacTpTMsg);
            }
            origIdx++;
        }
    }
    
    private void updateCodeTable(NMAL6370CMsg bizMsg, List<CTAC_TPTMsg> updList) {
        
        EZDDebugOutput.println(1, "----- updateCodeTable START -----", null);
           
        // for update
        for (CTAC_TPTMsg updMsg : updList) {
            CTAC_TPTMsg ctacTpTMsg = findCodeTableByKeyForUpdate(bizMsg, updMsg);
            if (ctacTpTMsg == null) {
                bizMsg.setMessageInfo(MESSAGE_ID.NZZM0003E.toString());
                return;
            }
        }
        
        int updCnt = updList.size();
        int rsltCnt = S21FastTBLAccessor.update(updList.toArray(new CTAC_TPTMsg[updCnt]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0177E.toString(), new String[]{"Contact Type"});
            return;
        }
        
        EZDDebugOutput.println(1, "----- updateCodeTable END -----", null);
    }

    private CTAC_TPTMsg findCodeTableByKeyForUpdate(NMAL6370CMsg bizMsg, CTAC_TPTMsg updCtacTpTMsg) {
        
        CTAC_TPTMsg wrkMsg = (CTAC_TPTMsg) updCtacTpTMsg.clone();
        CTAC_TPTMsg ctacTpTMsg = (CTAC_TPTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
        if (ctacTpTMsg == null) {
            return null;
        }
        
        String findEzUpTime = updCtacTpTMsg.ezUpTime.getValue();
        String findEzUpTimeZone = updCtacTpTMsg.ezUpTimeZone.getValue();
        String currentEzUpTime = ctacTpTMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = ctacTpTMsg.ezUpTimeZone.getValue();

        if ("*".equals(findEzUpTime)) {
            if (hasValue(currentEzUpTime)) {
                return null;
            }
        } else if (!isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            return null;
        }
        
        return ctacTpTMsg;
    }
    
    private void setNMAL6370ASMsgToCtacTpTMsg(NMAL6370_ASMsg inMsg, CTAC_TPTMsg ctacTpTMsg) {
        
        setValue(ctacTpTMsg.glblCmpyCd, inMsg.glblCmpyCd_A1);
        setValue(ctacTpTMsg.ctacTpCd, inMsg.ctacTpCd_A1);
        setValue(ctacTpTMsg.ctacTpSortNum, inMsg.ctacTpSortNum_A1);
        setValue(ctacTpTMsg.ctacTpNm, inMsg.ctacTpNm_A1);
        setValue(ctacTpTMsg.ctacTpDescTxt, inMsg.ctacTpDescTxt_A1);
        setValue(ctacTpTMsg.techFlg, inMsg.techFlg_A1);
        setValue(ctacTpTMsg.actvFlg, inMsg.actvFlg_A1);
        setValue(ctacTpTMsg.execFlg, inMsg.execFlg_A1);
        setValue(ctacTpTMsg.svcIndFlg, inMsg.svcIndFlg_A1);
        setValue(ctacTpTMsg.ezUpTime, inMsg.ezUpTime_A1);
        setValue(ctacTpTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A1);
        
    }
    
    private boolean isUpdate(NMAL6370_BSMsgArray origMsgArray, NMAL6370_ASMsg inMsg) {
        
        NMAL6370_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
        if (origMsg == null) {
            return false;
        }

        if (compareBigDecimal(origMsg.ctacTpSortNum_B1, inMsg.ctacTpSortNum_A1)) {
            return true;
        }
        
        if (compareString(origMsg.ctacTpNm_B1, inMsg.ctacTpNm_A1)) {
            return true;
        }
        
        if (compareString(origMsg.ctacTpDescTxt_B1, inMsg.ctacTpDescTxt_A1)) {
            return true;
        }
        
        if (compareString(origMsg.techFlg_B1, inMsg.techFlg_A1)) {
            return true;
        }
        
        if (compareString(origMsg.actvFlg_B1, inMsg.actvFlg_A1)) {
            return true;
        }
        
        if (compareString(origMsg.execFlg_B1, inMsg.execFlg_A1)) {
            return true;
        }
        
        if (compareString(origMsg.svcIndFlg_B1, inMsg.svcIndFlg_A1)) {
            return true;
        }
        
        return false;
        
    }

    private NMAL6370_BSMsg getOrigMsg(NMAL6370_BSMsgArray origMsgArray, NMAL6370_ASMsg inMsg) {

        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            if (inMsg.ctacTpCd_A1.getValue().equals(origMsgArray.no(i).ctacTpCd_B1.getValue())) {
                return origMsgArray.no(i);
            }
        }
        return null;
    }
}
