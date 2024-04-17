/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/06/2009   Fujitsu         T.Kawamura      Create          N/A
 *</pre>
 */
package business.blap.ZZIL0010;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZIL0010.constant.ZZIL0010Constant;
import business.db.INTERFACE_TRANSACTIONTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZIL0010BL06 extends S21BusinessHandler implements ZZIL0010Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZIL0010Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZIL0010Scrn00_CMN_Submit((ZZIL0010CMsg) cMsg, (ZZIL0010SMsg) sMsg);

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
    private void doProcess_ZZIL0010Scrn00_CMN_Submit(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg) {

        String procssedFlag = null;
        String changeStatus = null;
        int cnt = 0;

        INTERFACE_TRANSACTIONTMsg tMsg = new INTERFACE_TRANSACTIONTMsg();
        INTERFACE_TRANSACTIONTMsg updTMsg = new INTERFACE_TRANSACTIONTMsg();

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

                // set search parameter
                tMsg.interfaceId.setValue(sMsg.interfaceId.getValue());
                tMsg.transactionId.setValue(sMsg.A.no(i).transactionId_A.getValue());

                // get search result
                updTMsg = (INTERFACE_TRANSACTIONTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
                if (updTMsg == null) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }

                // check the update date and time and update time zone
                String upDate = sMsg.A.no(i).ezUpTime_A.getValue();
                String upZone = sMsg.A.no(i).ezUpTimeZone_A.getValue();
                if (!upDate.equals(updTMsg.ezUpTime.getValue()) || !upZone.equals(updTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }

                // Record count clear
                if (changeStatus.equals("0")) {
                    updTMsg.processedRecCnt.setValue(0);
                    updTMsg.failedRecCnt.setValue(0);
                }
                
                // status change
                updTMsg.processedFlag.setValue(changeStatus);

                EZDTBLAccessor.update(updTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                    cMsg.setMessageInfo("ZZZM9013E", new String[] {updTMsg.getReturnCode() });
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
    private void setCopyVal(ZZIL0010CMsg cMsg, ZZIL0010SMsg sMsg, int pagenationFrom) {

        // set ezintime, ezuptime, processedFlag
        for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {
                cMsg.A.no(i - pagenationFrom).ezInTime_A.setValue(sMsg.A.no(i).ezInTime_A.getValue());
                cMsg.A.no(i - pagenationFrom).ezUpTime_A.setValue(sMsg.A.no(i).ezUpTime_A.getValue());
                cMsg.A.no(i - pagenationFrom).processedFlag_A.setValue(sMsg.A.no(i).processedFlag_A.getValue());
            } else {
                break;
            }
        }
    }

}
