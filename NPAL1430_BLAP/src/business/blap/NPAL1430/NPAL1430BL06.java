package business.blap.NPAL1430;

import static business.blap.NPAL1430.constant.NPAL1430Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1430.constant.NPAL1430Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1430.constant.NPAL1430Constant.DB_PARAM_RGTN_STS_CD_LIST;
import static business.blap.NPAL1430.constant.NPAL1430Constant.DB_PARAM_T_MDL_NM;
import static business.blap.NPAL1430.constant.NPAL1430Constant.EVENT_CMN_SUBMIT;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NPAM0005I;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NPAM0006E;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NPAM0007E;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NPAM0076E;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NPAM1483E;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NPAM1584E;
import static business.blap.NPAL1430.constant.NPAL1430Constant.NPAM1647E;
import static business.blap.NPAL1430.constant.NPAL1430Constant.ZZZM9003I;
import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.db.RMNF_MDL_STD_PRTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * /**
 * 
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * Function Name : update business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 * 08/28/2016   CITS            T.Gotoda        Update          QC#13404
 * 12/05/2019   Fujitsu         T.Ogura         Update          QC#54842
 *</pre>
 */
public class NPAL1430BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_Submit((NPAL1430CMsg) cMsg, (NPAL1430SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Submit Event
     * </pre>
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_Submit(NPAL1430CMsg cMsg, NPAL1430SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // get model id
        List<BigDecimal> rmnfMdlIdList = getModel(cMsg.t_MdlNm.getValue(), glblCmpyCd);
        if (rmnfMdlIdList.size() == 0) {
            cMsg.setMessageInfo(NPAM0076E, new String[] {"Model" });
            return;
        }
        if (rmnfMdlIdList.size() > 1) {
            cMsg.setMessageInfo(NPAM1483E);
            return;
        }

        // if (sMsg.A.getValidCount() == 0) {
        // cMsg.setMessageInfo(NPAM1351E);
        // return;
        // }

        // Delete Row
        List<RMNF_MDL_STD_PRTTMsg> deleteMsgs = new ArrayList<RMNF_MDL_STD_PRTTMsg>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).delFlg_A1.getValue())) {
                RMNF_MDL_STD_PRTTMsg tMsg = new RMNF_MDL_STD_PRTTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfMdlId, rmnfMdlIdList.get(0));
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, sMsg.A.no(i).mdseCd_A1);

                RMNF_MDL_STD_PRTTMsg prodCtrlTMsg = (RMNF_MDL_STD_PRTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

                if (prodCtrlTMsg != null) {
                    String findEzUpTime = sMsg.A.no(i).xxRqstTs_A1.getValue();
                    String findEzUpTimeZone = sMsg.A.no(i).xxRqstTz_A1.getValue();
                    String currentEzUpTime = prodCtrlTMsg.ezUpTime.getValue();
                    String currentEzUpTimeZone = prodCtrlTMsg.ezUpTimeZone.getValue();
                    if (ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                        deleteMsgs.add(prodCtrlTMsg);
                    } else {
                        cMsg.setMessageInfo(NPAM0006E);
                        return;
                    }
                }
            }
        }
        if (!deleteMsgs.isEmpty()) {
            int retDelete = S21FastTBLAccessor.removePhysical(deleteMsgs.toArray(new RMNF_MDL_STD_PRTTMsg[0]));
            if (retDelete != deleteMsgs.size()) {
                cMsg.setMessageInfo(NPAM0007E);
                return;
            }
        }

        // insert or update
        List<RMNF_MDL_STD_PRTTMsg> insertMsgs = new ArrayList<RMNF_MDL_STD_PRTTMsg>();
        List<RMNF_MDL_STD_PRTTMsg> updateMsgs = new ArrayList<RMNF_MDL_STD_PRTTMsg>();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            // Master Check Item Number
            if (!chkMdse(cMsg.A.no(i).mdseCd_A1.getValue(), glblCmpyCd)) {
                cMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM0076E, new String[] {"Item Number" });
                continue;
            }

            // intangible check
            if (!chkMdseIntangible(cMsg.A.no(i).mdseCd_A1.getValue(), glblCmpyCd)) {
                cMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM1584E);
                continue;
            }

            // START 2019/12/05 T.Ogura [QC#54842,ADD]
            // IB Trackable Item check
            if (!chkIbTrackableItem(cMsg.A.no(i).mdseCd_A1.getValue(), glblCmpyCd)) {
                cMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM1647E);
                continue;
            }
            // END   2019/12/05 T.Ogura [QC#54842,ADD]

            RMNF_MDL_STD_PRTTMsg tMsg = new RMNF_MDL_STD_PRTTMsg();

            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.rmnfMdlId, rmnfMdlIdList.get(0));
            ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, cMsg.A.no(i).mdseCd_A1);

            if (cMsg.A.no(i).xxNewRowNum_A1.getValue().compareTo(ONE) == 0) {
                // New record
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfMdlStdPrtDescTxt, cMsg.rmnfMdlStdPrtDescTxt);
                ZYPEZDItemValueSetter.setValue(tMsg.rmnfReqQty, cMsg.A.no(i).rmnfReqQty_A1);
                ZYPEZDItemValueSetter.setValue(tMsg.lastUpdDt, salesDate);
                insertMsgs.add(tMsg);
                continue;
            }

            RMNF_MDL_STD_PRTTMsg prodCtrlTMsg = (RMNF_MDL_STD_PRTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tMsg);

            if (prodCtrlTMsg == null) {
                cMsg.setMessageInfo(NPAM0006E);
                return;
            }

            String findEzUpTime = cMsg.A.no(i).xxRqstTs_A1.getValue();
            String findEzUpTimeZone = cMsg.A.no(i).xxRqstTz_A1.getValue();
            String currentEzUpTime = prodCtrlTMsg.ezUpTime.getValue();
            String currentEzUpTimeZone = prodCtrlTMsg.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
                cMsg.setMessageInfo(NPAM0006E);
                return;
            }

            // Update record
            ZYPEZDItemValueSetter.setValue(prodCtrlTMsg.rmnfMdlStdPrtDescTxt, cMsg.rmnfMdlStdPrtDescTxt);
            ZYPEZDItemValueSetter.setValue(prodCtrlTMsg.rmnfReqQty, cMsg.A.no(i).rmnfReqQty_A1);
            ZYPEZDItemValueSetter.setValue(prodCtrlTMsg.lastUpdDt, salesDate);
            updateMsgs.add(prodCtrlTMsg);
        }

        if ((insertMsgs.size() + updateMsgs.size()) < cMsg.A.getValidCount()) {
            cMsg.setMessageInfo(NPAM0007E);
            return;
        }

        if (!insertMsgs.isEmpty()) {
            int retInsert = S21FastTBLAccessor.insert(insertMsgs.toArray(new RMNF_MDL_STD_PRTTMsg[0]));
            if (retInsert != insertMsgs.size()) {
                cMsg.setMessageInfo(NPAM0007E);
                return;
            }
        }
        if (!updateMsgs.isEmpty()) {
            int retUpdate = S21FastTBLAccessor.update(updateMsgs.toArray(new RMNF_MDL_STD_PRTTMsg[0]));
            if (retUpdate != updateMsgs.size()) {
                cMsg.setMessageInfo(NPAM0007E);
                return;
            }
        }

        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
        cMsg.setMessageInfo(NPAM0005I);
    }

    private List<BigDecimal> getModel(String mdlNm, String glblCmpyCd) {
        List<BigDecimal> ret = new ArrayList<BigDecimal>();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_T_MDL_NM, mdlNm);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NPAL1430Query.getInstance().searchModel(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                ret.add((BigDecimal) recode.get("T_MDL_ID"));
            }
        }
        return ret;
    }

    private boolean chkMdse(String mdseCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);
        ArrayList<String> rgtnStsCdList = new ArrayList<String>();
        rgtnStsCdList.add(RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);
        rgtnStsCdList.add(RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put(DB_PARAM_RGTN_STS_CD_LIST, rgtnStsCdList);

        S21SsmEZDResult result = NPAL1430Query.getInstance().chkMdseCd(ssmParam);
        if (!result.isCodeNormal() || (Integer) result.getResultObject() == 0) {
            // Does not exist in master.
            return false;
        }
        return true;
    }

    /**
     * chkMdseIntangible
     * @param mdseCd
     * @param glblCmpyCd
     * @return intangible = Y , return false
     */
    private boolean chkMdseIntangible(String mdseCd, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);

        S21SsmEZDResult result = NPAL1430Query.getInstance().chkMdseIntangible(ssmParam);

        if (!result.isCodeNormal() || (Integer) result.getResultObject() == 0) {
            return false;
        }

        return true;
    }

    // START 2019/12/05 T.Ogura [QC#54842,ADD]
    /**
     * chkIbTrackableItem
     * @param mdseCd
     * @param glblCmpyCd
     * @return true(Non IB Trackable Item) / false (IB Trackable Item)
     */
    private boolean chkIbTrackableItem(String mdseCd, String glblCmpyCd) {

        MDSETMsg mdse = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdse.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdse.mdseCd, mdseCd);
        mdse = (MDSETMsg) EZDTBLAccessor.findByKey(mdse);

        if (mdse != null && ZYPConstant.FLG_ON_Y.equals(mdse.instlBaseCtrlFlg.getValue())) {
            // IB Trackable Item.
            return false;
        }

        return true;
    }
    // END   2019/12/05 T.Ogura [QC#54842,ADD]

}
