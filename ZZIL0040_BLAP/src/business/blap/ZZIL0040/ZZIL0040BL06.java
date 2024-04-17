package business.blap.ZZIL0040;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.ZZIL0040.common.ZZIL0040CommonLogic;
import business.db.MDB_INBD_INTFC_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZIL0040Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZIL0040Scrn00_CMN_Submit((ZZIL0040CMsg) cMsg, (ZZIL0040SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Method: Update MDB_INBD_INTFC_RQST Status <dd>The method
     * explanation: The business peculiarity processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Shared Data Area
     */
    private void doProcess_ZZIL0040Scrn00_CMN_Submit(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg) {

        String currentStatus = null;
        String changeStatus = null;
        int cnt = 0;

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {

                // get change_status
                currentStatus = sMsg.A.no(i).intfcRqstStsTxt_A.getValue();
                changeStatus = cMsg.A.no(i - pagenationFrom).intfcRqstStsTxt_AS.getValue();

                // check change_status
                if (changeStatus.equals("") || changeStatus.equals(currentStatus)) {
                    continue;
                }

                MDB_INBD_INTFC_RQSTTMsg tMsg = new MDB_INBD_INTFC_RQSTTMsg();

                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.mdbInbdIntfcRqstPk, sMsg.A.no(i).mdbInbdIntfcRqstPk_A.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.intfcId, sMsg.A.no(i).intfcId_A.getValue());
                tMsg = (MDB_INBD_INTFC_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (tMsg == null) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }

                // check the update date and time and update time zone
                String upDate = sMsg.A.no(i).ezUpTime_A.getValue();
                if (!upDate.equals(tMsg.ezUpTime.getValue())) {
                    cMsg.setMessageInfo("ZZZM9004E");
                    break;
                }

                // status change
                ZYPEZDItemValueSetter.setValue(tMsg.intfcRqstStsTxt, changeStatus);
                tMsg.bizApiStartTs.clear();
                tMsg.bizApiEndTs.clear();
                tMsg.errLogDtlTxt.clear();

                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo("ZZZM9013E", new String[] {tMsg.getReturnCode() });
                    break;
                }

                MDB_INBD_INTFC_RQSTTMsg newtMsg = (MDB_INBD_INTFC_RQSTTMsg) EZDTBLAccessor.findByKey(tMsg);
                if (newtMsg != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).intfcRqstStsTxt_A, newtMsg.intfcRqstStsTxt.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_A, newtMsg.ezUpTime.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtTm_AS, newtMsg.bizApiStartTs.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtTm_AE, newtMsg.bizApiEndTs.getValue());
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
                ZZIL0040CommonLogic.setCopyVal(cMsg, sMsg, pagenationFrom);
            } else {
                cMsg.setMessageInfo("ZZZM9020E");
            }
        }

    }

}
