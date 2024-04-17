package business.blap.NLBL0110;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL0110.constant.NLBL0110Constant;
import business.db.WH_END_MTHTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * This class does update business process of BusinessID NLBL0110.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/02/05   Fujitsu         S.Uehara        Create          N/A
 * 2013/05/24   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0110BL06 extends S21BusinessHandler implements NLBL0110Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if (sMsg != null) {
                sMsg.clearErrorInfo();
            }

            if (EVENT_NM_NLBL0110SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLBL0110Scrn00_CMN_Submit((NLBL0110CMsg) cMsg, (NLBL0110SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * The method explanation: It is a method of the execution when the event[CMN_Submit] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0110Scrn00_CMN_Submit(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        sMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        if (!checkExculsiveForSubmit(sMsg)) {
            cMsg.setMessageInfo(NLBM0009E);
            return;
        }

        if (!updateWHEndMth(cMsg)) {
            cMsg.setMessageInfo(NLBM0024E);
            return;
        }

    }

    /**
     * Check Exclusive 
     * @param sMsg Global area information
     * @return boolean
     */
    private boolean checkExculsiveForSubmit(NLBL0110SMsg sMsg) {

        // 2013/05/24 R-OM025 Inventory Transaction Mod Start
        // (We should not use 'update no wait' in SSM)

//        NLBL0110Query.getInstance().getWHEndMthForUpdate(sMsg);
//
//        S21SsmEZDResult ssmResult = NLBL0110Query.getInstance().getWHEndMthForSubmitSnapshot(sMsg);
//
//        List<NLBL0110_ASMsg> snapshotArray = (ArrayList<NLBL0110_ASMsg>) ssmResult.getResultObject();
//
//       
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//
//            NLBL0110_ASMsg snapshot = snapshotArray.get(i);
//
//            if (sMsg.A.no(i).whCd_A1.getValue().equals(snapshot.whCd_A1.getValue())) {
//                if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue(),
//                        snapshot.ezUpTime_A1.getValue(), snapshot.ezUpTimeZone_A1.getValue())) {
//
//                    return false;
//                }
//            }
//        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            WH_END_MTHTMsg condition = new WH_END_MTHTMsg();
            ZYPEZDItemValueSetter.setValue(condition.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(condition.whCd, sMsg.A.no(i).whCd_A1);
            WH_END_MTHTMsg snapshot = (WH_END_MTHTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(condition);
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).ezUpTime_A1)) {
                // new record, show error when anyone insert same record
                if (snapshot != null) {
                    return false;
                }
            } else {
                // update record, show error when anyone update same record
                if (snapshot == null) {
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue(),
                        snapshot.ezUpTime.getValue(), snapshot.ezUpTimeZone.getValue())) {
                    return false;
                }
            }
        }
        // 2013/05/24 R-OM025 Inventory Transaction Mod Start

        return true;
    }

    /**
     * Update or insert WH_END_MTH table
     * @param cMsg Business Component Interface Message
     * @return boolean
     */
    private boolean updateWHEndMth(NLBL0110CMsg cMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            WH_END_MTHTMsg updMsg = new WH_END_MTHTMsg();

            ZYPEZDItemValueSetter.setValue(updMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updMsg.whCd, cMsg.A.no(i).whCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(updMsg.endMthBizDaysAot, cMsg.A.no(i).endMthBizDaysAot_A1.getValue());

            EZDTBLAccessor.update(updMsg);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(updMsg.getReturnCode())) {

                if (DB_ACCESS_PARTS_RETURN_CODE_NODATE.equals(updMsg.getReturnCode())) {
                    EZDTBLAccessor.create(updMsg);

                    if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(updMsg.getReturnCode())) {

                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
