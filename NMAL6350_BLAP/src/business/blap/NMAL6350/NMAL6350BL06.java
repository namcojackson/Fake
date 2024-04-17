/*
 * <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Class name: NMAL6350BL06
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 * 09/04/2018   Fujitsu         T.Noguchi       Update          QC#28019
 *</pre>
 */
package business.blap.NMAL6350;

import static business.blap.NMAL6350.common.NMAL6350CommonLogic.compareBigDecimal;
import static business.blap.NMAL6350.common.NMAL6350CommonLogic.compareString;
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
import business.blap.NMAL6350.common.NMAL6350CommonLogic;
import business.blap.NMAL6350.constant.NMAL6350Constant;
import business.db.PKG_UOMTMsg;

import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Class name: NMAL6350BL06
 * <dd>The class explanation: Business processing for Component ID : NMAL6350BL06
 * <dd>Remarks:
 * @version
 * @author Q03156                                                     
 *</pre>
 */
public class NMAL6350BL06 extends S21BusinessHandler implements NMAL6350Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        cMsg.setCommitSMsg(true);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6350Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6350Scrn00_CMN_Submit(cMsg, sMsg);
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
     * Method name: doProcess_NMAL6350Scrn00_CMN_Submit
     * <dd>The method explanation: submit button event
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6350Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350Scrn00_CMN_Submit START -----", null);

        NMAL6350CMsg bizMsg = (NMAL6350CMsg) cMsg;
        NMAL6350SMsg globalMsg = (NMAL6350SMsg) sMsg;
        
        NMAL6350CommonLogic.saveCurrentPageToSMsg(bizMsg, globalMsg);
        
        if (!NMAL6350CommonLogic.inputCheckAll(bizMsg, globalMsg)) {
            return;
        }
        
        // create insert/update object
        List<PKG_UOMTMsg> insList = new ArrayList<PKG_UOMTMsg>();
        List<PKG_UOMTMsg> updList = new ArrayList<PKG_UOMTMsg>();
        createRegistObject(globalMsg, insList, updList);
        
        if (!insList.isEmpty()) {
//            int insListCnt = insList.size();
//            int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new PKG_UOMTMsg[insListCnt]));
//            if (insListCnt != rsltCnt) {
//                bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{"Code"} );
//                return;
//            }
            for (PKG_UOMTMsg insTmsg : insList) {
                EZDTBLAccessor.create(insTmsg);
                if (RTNCD_DUPLICATE.equals(insTmsg.getReturnCode())) {
                    setInsertErrorInfo(bizMsg, globalMsg, insTmsg.pkgUomCd.getValue());
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
            S21CodeTableAccessor.flushCache("PKG_UOM");
        }
        
        bizMsg.xxBtnFlg.setValue(FLG_ON_Y);
        
        EZDDebugOutput.println(1, "----- doProcess_NMAL6350Scrn00_CMN_Submit END -----", null);

    }
    
    private void setInsertErrorInfo(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg, String code) {

        NMAL6350_ASMsgArray asMsgArray = globalMsg.A;
        bizMsg.setMessageInfo(MESSAGE_ID.NMAM0010E.toString(), new String[]{ "Code"} );
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6350_ASMsg asMsg = asMsgArray.no(i);
            if (code.equals(asMsg.pkgUomCd_A1.getValue())) {
                asMsg.pkgUomCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0010E.toString(), new String[]{DISPLAY_NAME.Code.toString()});
                NMAL6350CommonLogic.pagenation(bizMsg, globalMsg, NMAL6350CommonLogic.getPageStartRowIndex(i));
                break;
            }
        }
        
    }

    private void createRegistObject(NMAL6350SMsg globalMsg, List<PKG_UOMTMsg> insList, List<PKG_UOMTMsg> updList) {
        
        NMAL6350_ASMsgArray inMsgArray = globalMsg.A;
        NMAL6350_BSMsgArray origMsgArray = globalMsg.B;
        
        int origIdx = 0;
        for (int i = 0; i < inMsgArray.getValidCount(); i++) {
            NMAL6350_ASMsg inMsg = inMsgArray.no(i);
            if (!hasValue(inMsg.ezUpTime_A1)) {
                PKG_UOMTMsg inspkgUomTMsg = new PKG_UOMTMsg();
                inMsg.glblCmpyCd_A1.setValue(getGlobalCompanyCode());
                setNMAL6350ASMsgTopkgUomTMsg(inMsg, inspkgUomTMsg);
                insList.add(inspkgUomTMsg);
                continue;
            }

            if (isUpdate(origMsgArray, inMsg)) {
                PKG_UOMTMsg updpkgUomTMsg = new PKG_UOMTMsg();
                setNMAL6350ASMsgTopkgUomTMsg(inMsg, updpkgUomTMsg);
                updList.add(updpkgUomTMsg);
            }
            origIdx++;
        }
    }
    
    private void setNMAL6350ASMsgTopkgUomTMsg(NMAL6350_ASMsg inMsg, PKG_UOMTMsg pkgUomTMsg) {
        
        setValue(pkgUomTMsg.glblCmpyCd, inMsg.glblCmpyCd_A1);
        setValue(pkgUomTMsg.pkgUomCd, inMsg.pkgUomCd_A1);
        setValue(pkgUomTMsg.pkgUomSortNum, inMsg.pkgUomSortNum_A1);
        setValue(pkgUomTMsg.pkgUomNm, inMsg.pkgUomNm_A1);
        setValue(pkgUomTMsg.pkgUomDescTxt, inMsg.pkgUomDescTxt_A1);
        setValue(pkgUomTMsg.pkgUomStdFlg, inMsg.pkgUomStdFlg_A1);
        setValue(pkgUomTMsg.pkgUomMndFlg, inMsg.pkgUomMndFlg_A1);
        setValue(pkgUomTMsg.pkgUomPkgLvlNum, inMsg.pkgUomPkgLvlNum_A1);
        // 2018/09/04 QC#28019 Add Start
        setValue(pkgUomTMsg.pkgUomClsCd, inMsg.pkgUomClsCd_A1);
        // 2018/09/04 QC#28019 Add End
        setValue(pkgUomTMsg.ezUpTime, inMsg.ezUpTime_A1);
        setValue(pkgUomTMsg.ezUpTimeZone, inMsg.ezUpTimeZone_A1);
        
    }
    
    private void updateCodeTable(NMAL6350CMsg bizMsg, List<PKG_UOMTMsg> updList) {
        
        EZDDebugOutput.println(1, "----- updateCodeTable START -----", null);
           
        // for update
        for (PKG_UOMTMsg updMsg : updList) {
            PKG_UOMTMsg pkgUomTMsg = findCodeTableByKeyForUpdate(bizMsg, updMsg);
            if (pkgUomTMsg == null) {
                bizMsg.setMessageInfo(MESSAGE_ID.NZZM0003E.toString());
                return;
            }
        }
        
        int updCnt = updList.size();
        int rsltCnt = S21FastTBLAccessor.update(updList.toArray(new PKG_UOMTMsg[updCnt]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(MESSAGE_ID.NMAM0177E.toString(), new String[]{"Tariff"});
            return;
        }
        
        EZDDebugOutput.println(1, "----- updateCodeTable END -----", null);
    }

    
    
    private PKG_UOMTMsg findCodeTableByKeyForUpdate(NMAL6350CMsg bizMsg, PKG_UOMTMsg updateTMsg) {
        
        PKG_UOMTMsg wrkMsg = (PKG_UOMTMsg) updateTMsg.clone();
        PKG_UOMTMsg beforeUpdateTMsg = (PKG_UOMTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(wrkMsg);
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
    
    private boolean isUpdate(NMAL6350_BSMsgArray origMsgArray, NMAL6350_ASMsg inMsg) {
        
        NMAL6350_BSMsg origMsg = getOrigMsg(origMsgArray, inMsg);
        if (origMsg == null) {
            return false;
        }
        
        if (compareBigDecimal(origMsg.pkgUomSortNum_B1, inMsg.pkgUomSortNum_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pkgUomNm_B1, inMsg.pkgUomNm_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pkgUomDescTxt_B1, inMsg.pkgUomDescTxt_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pkgUomStdFlg_B1, inMsg.pkgUomStdFlg_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pkgUomMndFlg_B1, inMsg.pkgUomMndFlg_A1)) {
            return true;
        }
        
        if (compareString(origMsg.pkgUomPkgLvlNum_B1, inMsg.pkgUomPkgLvlNum_A1)) {
            return true;
        }

        // 2018/09/04 QC#28019 Add Start
        if (compareString(origMsg.pkgUomClsCd_B1, inMsg.pkgUomClsCd_A1)) {
            return true;
        }
        // 2018/09/04 QC#28019 Add Start
        return false;
        
    }
    
    private NMAL6350_BSMsg getOrigMsg(NMAL6350_BSMsgArray origMsgArray, NMAL6350_ASMsg inMsg) {

        for (int i = 0; i < origMsgArray.getValidCount(); i++) {
            if (inMsg.pkgUomCd_A1.getValue().equals(origMsgArray.no(i).pkgUomCd_B1.getValue())) {
                return origMsgArray.no(i);
            }
        }
        return null;
    }

}
