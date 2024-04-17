package business.blap.ZZIL0030.common;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.ZZIL0030.ZZIL0030CMsg;
import business.blap.ZZIL0030.ZZIL0030Query;
import business.blap.ZZIL0030.ZZIL0030SMsg;
import business.db.MDB_INBD_INTFC_CONFIGTMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0030CommonLogic {

    /**
     * search Interface from Master Table
     * @param cMsg ZZIL0030CMsg
     * @param sMsg ZZIL0030SMsg
     */
    public static void searchMaster(ZZIL0030CMsg cMsg, ZZIL0030SMsg sMsg) {

        // initialize the table data
        EZDMsg.copy(cMsg, null, sMsg, null);
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        S21SsmEZDResult ssmResult = ZZIL0030Query.getInstance().getTargetIFList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // Max Over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            // Copy(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set pagenation parameters
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Method: Insert Update Method for MDB_INBD_INTFC_CONFIG
     * @param bizMsg ZZIL0030CMsg
     * @param shareMsg ZZIL0030SMsg
     * @param glblCmpyCd String
     */
    public static void submitIF(ZZIL0030CMsg bizMsg, ZZIL0030SMsg shareMsg, String glblCmpyCd) {

        MDB_INBD_INTFC_CONFIGTMsg tMsg = new MDB_INBD_INTFC_CONFIGTMsg();
        MDB_INBD_INTFC_CONFIGTMsg rtnMsg = new MDB_INBD_INTFC_CONFIGTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.intfcId, bizMsg.intfcId_2.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.bizApiId, bizMsg.bizApiId_2.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.intfcIdDescTxt, bizMsg.intfcIdDescTxt_2.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.trgtBatJobNm, bizMsg.trgtBatJobNm_2.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.trgtBatJobDescTxt, bizMsg.trgtBatJobDescTxt_2.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.actvFlg, bizMsg.actvFlg_AF.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.frceQueueEnblFlg, bizMsg.frceQueueEnblFlg_FF.getValue());

        rtnMsg = (MDB_INBD_INTFC_CONFIGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);

        if (bizMsg.xxScrEventNm.getValue().equals("AddIF")) {
            if (rtnMsg != null) {
                // The data already exist.
                bizMsg.intfcId_2.setErrorInfo(1, "ZZZM9015E");
                bizMsg.setMessageInfo("ZZZM9015E");
                return;
            }
            EZDTBLAccessor.create(tMsg);
        } else {

            if (rtnMsg == null) {
                bizMsg.setMessageInfo("ZZZM9004E");
                return;
            }

            // Time stamp check
            String currentTime = bizMsg.ezUpTime.getValue();
            String currentTimeZone = bizMsg.ezUpTimeZone.getValue();
            String newTime = rtnMsg.ezUpTime.getValue();
            String newTimeZone = rtnMsg.ezUpTimeZone.getValue();
            if (!ZYPDateUtil.isSameTimeStamp(currentTime, currentTimeZone, newTime, newTimeZone)) {
                bizMsg.setMessageInfo("ZZZM9004E");
                return;
            }
            EZDTBLAccessor.update(tMsg);

            bizMsg.ezUpTime.setValue(tMsg.ezUpTime.getValue());
            bizMsg.ezUpTimeZone.setValue(tMsg.ezUpTimeZone.getValue());
        }
    }
}
