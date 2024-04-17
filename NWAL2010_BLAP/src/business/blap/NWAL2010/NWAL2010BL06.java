package business.blap.NWAL2010;

import java.math.BigDecimal;

import static business.blap.NWAL2010.constant.NWAL2010Constant.NZZM0003E;
import static business.blap.NWAL2010.constant.NWAL2010Constant.NZZM0002I;
import static business.blap.NWAL2010.constant.NWAL2010Constant.NWAM0447E;
import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CR_CARDTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL2010BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/12/16   Hitachi         R.Takau         Create         QC#60823
 *</pre>
 */

public class NWAL2010BL06 extends S21BusinessHandler {
    
    /** Global Company Code */
    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
    
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NWAL2010Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NWAL2010Scrn00_CMN_Delete(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
         super.postDoProcess(cMsg, sMsg);
        }
    }
    
    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NWAL2010Scrn00_CMN_Delete(EZDCMsg cMsg, EZDSMsg sMsg){
        
        NWAL2010CMsg bizMsg = (NWAL2010CMsg) cMsg;
        NWAL2010SMsg globalMsg = (NWAL2010SMsg) sMsg;
     
        
        BigDecimal idxOfSelectedRow = bizMsg.xxRadioBtn_A.getValue();
        if (idxOfSelectedRow == null) {
            bizMsg.setMessageInfo("NWAM0827E");
            return;
        }
        
        String crCardCustRefNum = globalMsg.A.no(idxOfSelectedRow.intValue()).crCardCustRefNum_A.getValue();
        BigDecimal Result =  NWAL2010Query.getInstance().getOpenTRX(crCardCustRefNum, globalMsg);
        
        if(!(Result.compareTo(BigDecimal.ZERO) == 0)) {
            bizMsg.setMessageInfo("NWAM8470E");
            return;
        }
        
        BigDecimal dsCrCardPk = globalMsg.A.no(idxOfSelectedRow.intValue()).dsCrCardPk_A.getValue();  
            
        DS_CR_CARDTMsg dsCrCardTMsg = new DS_CR_CARDTMsg();
       
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.glblCmpyCd, GLBL_CMPY_CD);
        ZYPEZDItemValueSetter.setValue(dsCrCardTMsg.dsCrCardPk, dsCrCardPk);
        dsCrCardTMsg = (DS_CR_CARDTMsg) EZDTBLAccessor.findByKey(dsCrCardTMsg);       
        if (dsCrCardTMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return;
        }    
        if (!dsCrCardTMsg.ezUpTime.getValue().equals(globalMsg.A.no(idxOfSelectedRow.intValue()).ezUpTime_A.getValue()) ||
                !dsCrCardTMsg.ezUpTimeZone.getValue().equals(globalMsg.A.no(idxOfSelectedRow.intValue()).ezUpTimeZone_A.getValue())) {
            bizMsg.setMessageInfo(NZZM0003E);
            return;
        }
       
        // logical remove
        EZDTBLAccessor.logicalRemove(dsCrCardTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCrCardTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NWAM0447E, new String[] {"Delete process", "DS_CR_CARD"});
            return;
        }
        
        bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        bizMsg.setMessageInfo(NZZM0002I);       
    }   
}
