package business.blap.ZZOL0010.common;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0010.ZZOL0010CMsg;
import business.blap.ZZOL0010.ZZOL0010Query;
import business.blap.ZZOL0010.ZZOL0010SMsg;
import business.blap.ZZOL0010.constant.ZZOL0010Constant;
import business.db.AOM02TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public class ZZOL0010CommonLogic implements ZZOL0010Constant {

    /**
     * Method: Search Method for AOM02
     * @param handler EZDCommonHandler
     */
    public static void searchAOM02(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
        ZZOL0010SMsg shareMsg = (ZZOL0010SMsg) sMsg;

        // initialize the table data
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        shareMsg.clear();
        shareMsg.A.clear();
        shareMsg.A.setValidCount(0);
        
        S21SsmEZDResult ssmResult = ZZOL0010Query.getInstance().getAOM02Data(bizMsg, shareMsg);

        if (ssmResult.isCodeNormal()) {
            // over the maximum length
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > shareMsg.A.length()) {

                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = shareMsg.A.length();
            }

            shareMsg.A.setValidCount(queryResCnt);

            int i = 0;
            for (; i < shareMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(shareMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // set value for Pagenenation
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

            // no search result
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.A.setValidCount(0);
        }
    }
    
    /**
     * Method: Insert Update Method for AOM02
     * @param handler EZDCommonHandler
     */
    public static void submitAOM02(EZDCMsg cMsg, EZDSMsg sMsg) {

        ZZOL0010CMsg bizMsg = (ZZOL0010CMsg) cMsg;
        AOM02TMsg tMsg = new AOM02TMsg();
        AOM02TMsg rtnMsg = new AOM02TMsg();

        tMsg.EZBusinessApplicationID.setValue(bizMsg.ezBusinessID_01.getValue());
        tMsg.EZCompanyCode.setValue(bizMsg.ezCompanyCode_01.getValue());
        tMsg.EZOnlineOperationStartTime.setValue(getStartTime(bizMsg));
        tMsg.EZOnlineOperationEndTime.setValue(getEndTime(bizMsg));
        tMsg.EZBillingInformationOutputType.setValue(bizMsg.ezAcctInfoMode_01.getValue());
        tMsg.EZOnlineBlockingFlag.setValue(bizMsg.ezOnlStopFlag_01.getValue());
        tMsg.EZDebugLevel.setValue(bizMsg.ezDebugLevel_01.getValue());

        rtnMsg = (AOM02TMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);

        if (bizMsg.xxScrEventNm.getValue().equals("Add")) {
            if (rtnMsg != null) {
                // The data already exist.
                bizMsg.ezBusinessID_01.setErrorInfo(1, "ZZZM9015E");
                bizMsg.setMessageInfo("ZZZM9015E");
                return;
            }
            EZDTBLAccessor.create(tMsg);
        } else {

            // Time stamp check
            if (rtnMsg == null 
                    || !ZYPDateUtil.isSameTimeStamp(
                            bizMsg.ezUpTime_01.getValue(), bizMsg.ezUpTimeZone_01.getValue(), 
                            rtnMsg.EZUpdateDateTime.getValue(), rtnMsg.EZUpTimeZone.getValue())) {
                bizMsg.setMessageInfo("ZZZM9004E");
                return;
            }
            EZDTBLAccessor.update(tMsg);
            bizMsg.ezUpTime_01.setValue(tMsg.EZUpdateDateTime.getValue());
            bizMsg.ezUpTimeZone_01.setValue(tMsg.EZUpTimeZone.getValue());
        }
    }
    
    /**
     * Method: Modify the time.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @return Time
     */
    public static String getStartTime(ZZOL0010CMsg cMsg) {
        String hours = cMsg.xxHrs_SV.getValue();
        String minutes = cMsg.xxMn_SV.getValue();

        return hours + minutes + "00";

    }

    /**
     * Method: Modify the time.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @return Time
     */
    public static String getEndTime(ZZOL0010CMsg cMsg) {
        String hours = cMsg.xxHrs_EV.getValue();
        String minutes = cMsg.xxMn_EV.getValue();

        return hours + minutes + "00";
    }

}
