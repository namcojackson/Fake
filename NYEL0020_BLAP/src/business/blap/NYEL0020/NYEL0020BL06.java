/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.NYEL0020;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NYEL0020.common.NYEL0020CommonLogic;
import business.blap.NYEL0020.constant.NYEL0020Constant;
import business.db.MY_PROCTMsg;
import business.db.MY_PROCTMsgArray;

import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class NYEL0020BL06 extends S21BusinessHandler implements NYEL0020Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NYEL0020Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_CMN_Submit((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NYEL0020Scrn00_CMN_Submit(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        // Get UserID from UserProfile 
        String sUserId = getContextUserInfo().getUserId();
        
        MY_PROCTMsg rMyProc = new MY_PROCTMsg();
        rMyProc.setSQLID("001");
        rMyProc.setConditionValue("usrNm01", sUserId);
        MY_PROCTMsgArray tMsgArray = (MY_PROCTMsgArray) EZDTBLAccessor.findByCondition(rMyProc);
        // No Data
        if (tMsgArray == null || tMsgArray.length() == 0) {
            // Not Error
        } else {
            // Remove
            for (int i = 0; i < tMsgArray.length(); i++) {
                // Search MY_PROC table by Primary Key
                MY_PROCTMsg tMsg = new MY_PROCTMsg();

                tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_BK.getValue());
                tMsg.myProcPk.setValue(tMsgArray.no(i).myProcPk.getValue());
                tMsg = (MY_PROCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg != null) {
                    EZDTBLAccessor.remove(tMsg);
                    String sReturnCode = tMsg.getReturnCode();
                    if (!sReturnCode.equals("0000")){
                        cMsg.setMessageInfo("ZZZM9014E", new String[] {sReturnCode});
                        return;
                    }
                }
            }
        }
        String sGblCpyCd = cMsg.glblCmpyCd_BK.getValue();
        // create
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String xxUpTabCd = cMsg.B.no(i).upTabCd_B1.getValue();

            MY_PROCTMsg tMsg = new MY_PROCTMsg();
            tMsg.glblCmpyCd.setValue(sGblCpyCd);
            tMsg.myProcPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal("MY_PROC_SQ"));
            tMsg.usrCmpyCd.setValue(sGblCpyCd);
            tMsg.usrNm.setValue(sUserId);
            tMsg.upTabOpSortNum.setValue(i);
            tMsg.upTabCd.setValue(xxUpTabCd);
            EZDTBLAccessor.create(tMsg);
            String sReturnCode = tMsg.getReturnCode();
            if (!sReturnCode.equals("0000")){
                cMsg.setMessageInfo("ZZZM9012E", new String[] {sReturnCode});
                return;
            }

        }

        NYEL0020CommonLogic.doInit(sGblCpyCd, cMsg, sMsg);
        // correct
        cMsg.setMessageInfo("ZZZM9003I", new String[] {"Maintenance"});
       
    }

}
