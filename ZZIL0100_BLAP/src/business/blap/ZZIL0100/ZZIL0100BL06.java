/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/05/2013   Fujitsu         M.Ugaki      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0100;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;

import business.blap.ZZIL0100.common.InternalTransactionTableAccessor;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZIL0100BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZIL0100Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZIL0100Scrn00_CMN_Submit((ZZIL0100CMsg) cMsg, (ZZIL0100SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * submit processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0100Scrn00_CMN_Submit(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg) {

        String procssedFlag = null;
        String changeStatus = null;
        int cnt = 0;

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {

                // get change_status
                procssedFlag = sMsg.A.no(i).processedFlag_A.getValue();
                changeStatus = cMsg.A.no(i - pagenationFrom).processedFlag_AS.getValue();

                // check change_status
                if (changeStatus.equals("") || changeStatus.equals(procssedFlag)) {
                    continue;
                }

                // get search result
                InternalTransactionTableAccessor accessor = new InternalTransactionTableAccessor(sMsg.itrlIntfcTrxConfigId_PS.getValue());
                String newUpDate = null;
                try{
                	 
                	newUpDate = ZZIL0100Query.getInstance().selectForupdateItrlProcFlag(accessor, sMsg, i);
                	if (newUpDate == null) {
                		cMsg.setMessageInfo("ZZZM9004E");
                		break;
                	}
                }
                catch (EZDDBRecordLockedException le) {
                 	cMsg.setMessageInfo("ZZM0207E");
                   	break;
                 }               	

                // check the update date and time and update time zone
                String upDate = sMsg.A.no(i).xxDtTm_AU.getValue();
                if (!upDate.equals(newUpDate)) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }

                // status change
                try{
                	ZZIL0100Query.getInstance().updateItrlProcFlag(accessor, sMsg, i, changeStatus);
                }
                catch (EZDDBRecordLockedException le) {
                	cMsg.setMessageInfo("ZZM0207E");
                	break;
                }
                cnt++;

            } else {
                break;
            }
        }

        // error check
        if (cMsg.getMessageInfo() == null) {

            // message setting
            if (cnt > 0) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update" });
                setCopyVal(cMsg, sMsg, pagenationFrom);//
            } else {
                cMsg.setMessageInfo("ZZZM9020E");
                setCopyVal(cMsg, sMsg, pagenationFrom);
            }
        } else {
            setCopyVal(cMsg, sMsg, pagenationFrom);
        }
    }

    /**
     * copy data value
     * @param cMsg
     * @param sMsg
     * @param pagenationFrom
     */
    private void setCopyVal(ZZIL0100CMsg cMsg, ZZIL0100SMsg sMsg, int pagenationFrom) {

        // set ezintime, ezuptime, processedFlag
        for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {
                cMsg.A.no(i - pagenationFrom).xxDtTm_AU.setValue(sMsg.A.no(i).xxDtTm_AU.getValue());
                cMsg.A.no(i - pagenationFrom).xxIntfcUpdTz_AU.setValue(sMsg.A.no(i).xxIntfcUpdTz_AU.getValue());
                cMsg.A.no(i - pagenationFrom).procPgmId_AU.setValue(sMsg.A.no(i).procPgmId_AU.getValue());
                cMsg.A.no(i - pagenationFrom).processedFlag_A.setValue(sMsg.A.no(i).processedFlag_A.getValue());
                cMsg.A.no(i - pagenationFrom).processedFlag_AS.clear();
            } else {
                break;
            }
        }
    }

}
