package business.blap.ZZIL0040;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZIL0040.common.ZZIL0040CommonLogic;
import business.db.MDB_INBD_INTFC_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZIL0040Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZIL0040Scrn00_Search((ZZIL0040CMsg) cMsg, (ZZIL0040SMsg) sMsg);
            } else if ("ZZIL0040Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZIL0040Scrn00_CMN_Clear((ZZIL0040CMsg) cMsg, (ZZIL0040SMsg) sMsg);
            } else if ("ZZIL0040Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZIL0040Scrn00_PageNext((ZZIL0040CMsg) cMsg, (ZZIL0040SMsg) sMsg);
            } else if ("ZZIL0040Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZIL0040Scrn00_PagePrev((ZZIL0040CMsg) cMsg, (ZZIL0040SMsg) sMsg);
            } else if ("ZZIL0040Scrn00_View".equals(screenAplID)) {
                doProcess_ZZIL0040Scrn00_View((ZZIL0040CMsg) cMsg, (ZZIL0040SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_ZZIL0040Scrn00_Search(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg) {

        ZZIL0040CommonLogic.searchRequestList(cMsg, sMsg);

    }

    private void doProcess_ZZIL0040Scrn00_CMN_Clear(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

    }

    private void doProcess_ZZIL0040Scrn00_PageNext(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // check change status flag
        if (ZZIL0040CommonLogic.chkChangeFlg(cMsg, sMsg, pagenationFrom - cMsg.A.length(), true)) {
            cMsg.setMessageInfo("ZZZM9019W");
            return;
        }
        ZZIL0040CommonLogic.pageMove(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum);

    }

    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZIL0040Scrn00_PagePrev(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg) {
        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // check change status flag
        if (ZZIL0040CommonLogic.chkChangeFlg(cMsg, sMsg, pagenationFrom + cMsg.A.length(), true)) {
            cMsg.setMessageInfo("ZZZM9019W");
            return;
        }
        ZZIL0040CommonLogic.pageMove(cMsg.A, sMsg.A, cMsg.xxPageShowFromNum, cMsg.xxPageShowToNum);
    }

    private void doProcess_ZZIL0040Scrn00_View(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg) {

        MDB_INBD_INTFC_RQSTTMsg tMsg = new MDB_INBD_INTFC_RQSTTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.mdbInbdIntfcRqstPk, cMsg.mdbInbdIntfcRqstPk_D.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.intfcId, cMsg.intfcId_D.getValue());
        tMsg = (MDB_INBD_INTFC_RQSTTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.mdbInbdIntfcRqstPk_D, tMsg.mdbInbdIntfcRqstPk.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.intfcId_D, tMsg.intfcId.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.bizApiId_D, tMsg.bizApiId.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.rqstJmsMsgId_D, tMsg.rqstJmsMsgId.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm_DS, tMsg.bizApiStartTs.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm_DE, tMsg.bizApiEndTs.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.intfcRqstStsTxt_D, tMsg.intfcRqstStsTxt.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.onBatTpNm_D, tMsg.onBatTpNm.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.errLogDtlTxt_D, tMsg.errLogDtlTxt.getValue());
        }
    }

}
