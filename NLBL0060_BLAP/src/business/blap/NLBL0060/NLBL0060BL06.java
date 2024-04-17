/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0060;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NLBL0060.common.NLBL0060CommonLogic;
import business.blap.NLBL0060.constant.NLBL0060Constant;
import business.db.WH_LEAD_TMTMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

/**
 * <pre>
 * This class does search business process of BusinessID NLBL0060.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/14   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/21   CUSA            Mizutani         Update          R-OM025 Inventory Transaction
 * 2018/09/04   CITS            K.Ogino          Update          QC#28077
 *</pre>
 */
public class NLBL0060BL06 extends S21BusinessHandler implements NLBL0060Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLBL0060SCRN00_CMN_SUBMIT.equals(screenAplID)) {

                doProcess_NLBL0060Scrn00_CMN_Submit((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_DeleteRow] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_CMN_Submit(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLBL0060Scrn00_CMN_Submit================================START", this);

        cMsg.setCommitSMsg(true);

        NLBL0060CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        NLBL0060CommonLogic.copyCurrentTblDataFromCMsgToSMsg(cMsg, sMsg);
        // ======================================================================================
        // check input
        // ======================================================================================
        if (!checkInput(cMsg, sMsg)) {

            return;
        }

        // ======================================================================================
        // check if data has been updated by other users after search
        // event was generated.
        // ======================================================================================
        NLBL0060Query.getInstance().getWHLeadTmListForSubmitSnapshot(sMsg);

        if (!checkDataExclusive(cMsg, sMsg)) {

            return;
        }

        // ======================================================================================
        // updating Existing data
        // ======================================================================================
        if (!updateExistingData(cMsg, sMsg)) {

            return;
        }

        // ======================================================================================
        // create new data
        // ======================================================================================
        if (!createNewData(cMsg, sMsg)) {

            return;
        }

        cMsg.setMessageInfo(ZZM8100I);

        EZDDebugOutput.println(1, "doProcess_NLBL0060Scrn00_CMN_Submit================================END", this);
    }

    private boolean checkInput(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        if (!checkSingleUnitAttribution(cMsg, sMsg)) {

            return false;
        }

        if (!checkEffPerValidation(cMsg, sMsg)) {

            return false;
        }

        if (!checkEffPerDuplication(cMsg, sMsg)) {

            return false;
        }

        if (!checkEffPerContinuousness(cMsg, sMsg)) {

            return false;
        }

        return true;
    }

    private boolean checkSingleUnitAttribution(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        boolean errorFlag = false;
        boolean errorAlreadySetFlag = false;
        int firstErrorRowNum = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // 2013/05/21 R-OM025 Inventory Transaction Add Start
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).whCd_A1)) {

                sMsg.A.no(i).whCd_A1.setErrorInfo(1, ZZM9000E, new String[] {NAME_FOR_MESSAGE_WH });
                errorFlag = true;
            } else {
                // Location Check
                // 10/06/2015 remove start
                /*
                if (!isLocCdCheckForDetail(sMsg.A.no(i))) {
                    errorFlag = true;
                }
                */
                // 10/06/2015 remove end
            }
            // 2013/05/21 R-OM025 Inventory Transaction Add End

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {

                sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, ZZM9000E, new String[] {NAME_FOR_MESSAGE_EFF_FROM_DT });
                errorFlag = true;

            } else if (ZYPDateUtil.isPastDate(sMsg.A.no(i).effFromDt_A1.getValue())) {

                sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NLBM0082E, new String[] {ZYPDateUtil.convertFormat(ZYPDateUtil.getSalesDate(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, DATE_SEPARATOR) });
                errorFlag = true;
            }

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)) {

                sMsg.A.no(i).effThruDt_A1.setErrorInfo(1, ZZM9000E, new String[] {NAME_FOR_MESSAGE_EFF_THRU_DT });
                errorFlag = true;

            } else if (ZYPDateUtil.isPastDate(sMsg.A.no(i).effThruDt_A1.getValue())) {

                sMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NLBM0083E, new String[] {ZYPDateUtil.convertFormat(ZYPDateUtil.getSalesDate(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, DATE_SEPARATOR) });
                errorFlag = true;
            }

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).shpgCloTmTs_A1)) {

                sMsg.A.no(i).shpgCloTmTs_A1.setErrorInfo(1, ZZM9000E, new String[] {NAME_FOR_MESSAGE_SHPG_CLO_TM_TS });
                errorFlag = true;

            } else {

                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHPG_CLO_TM_TS);
                sdf.setLenient(false);

                try {

                    sdf.parse(sMsg.A.no(i).shpgCloTmTs_A1.getValue());

                } catch (ParseException e) {

                    sMsg.A.no(i).shpgCloTmTs_A1.setErrorInfo(1, NLBM0028E);
                    errorFlag = true;
                }

            }

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).xxDplyLeadTmDaysAot_A1)) {

                sMsg.A.no(i).xxDplyLeadTmDaysAot_A1.setErrorInfo(1, ZZM9000E, new String[] {NAME_FOR_MESSAGE_PICK_PACK_AOT });
                errorFlag = true;

            } else if (sMsg.A.no(i).xxDplyLeadTmDaysAot_A1.getValueInt() < 0 || 30 < sMsg.A.no(i).xxDplyLeadTmDaysAot_A1.getValueInt()) {

                sMsg.A.no(i).xxDplyLeadTmDaysAot_A1.setErrorInfo(1, NLBM1097E);
                errorFlag = true;
            }

            if (errorAlreadySetFlag == false && errorFlag == true) {

                errorAlreadySetFlag = true;
                firstErrorRowNum = i;
            }

        }

        if (errorAlreadySetFlag) {

            NLBL0060CommonLogic.copyErrorPageDataFromSMsgToCMsg(firstErrorRowNum, cMsg, sMsg);
            return false;
        }

        return true;
    }

    private boolean checkEffPerValidation(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        boolean errorFlag = false;
        boolean errorAlreadySetFlag = false;
        int firstErrorRowNum = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            String effFrom = sMsg.A.no(i).effFromDt_A1.getValue();
            String effThru = sMsg.A.no(i).effThruDt_A1.getValue();

            if (ZYPDateUtil.compare(effFrom, effThru) == 1) {

                sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NLBM0008E);
                sMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NLBM0008E);

                errorFlag = true;
            }

            if (errorAlreadySetFlag == false && errorFlag == true) {

                errorAlreadySetFlag = true;
                firstErrorRowNum = i;
            }
        }

        if (errorAlreadySetFlag) {

            NLBL0060CommonLogic.copyErrorPageDataFromSMsgToCMsg(firstErrorRowNum, cMsg, sMsg);
            return false;
        }

        return true;
    }

    private boolean checkEffPerDuplication(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        boolean errorAlreadySetFlag = false;
        int firstErrorRowNum = 0;

        String whCdCurrentKey1 = "";
        String effFromCurrentKey1 = "";
        int newRowNumCurrentKey1 = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (whCdCurrentKey1.equals(sMsg.A.no(i).whCd_A9.getValue()) && effFromCurrentKey1.equals(sMsg.A.no(i).effFromDt_A9.getValue()) && newRowNumCurrentKey1 == sMsg.A.no(i).xxNewRowNum_A9.getValueInt()) {

                continue;

            } else {

                boolean effPerDuplicationCheckErrorFlag = false;

                whCdCurrentKey1 = sMsg.A.no(i).whCd_A9.getValue();
                effFromCurrentKey1 = sMsg.A.no(i).effFromDt_A9.getValue();
                newRowNumCurrentKey1 = sMsg.A.no(i).xxNewRowNum_A9.getValueInt();

                String whCdCurrentKey2 = "";
                String effFromCurrentKey2 = "";
                int newRowNumCurrentKey2 = 0;

                for (int j = 0; j < sMsg.A.getValidCount(); j++) {

                    if (whCdCurrentKey2.equals(sMsg.A.no(j).whCd_A9.getValue()) && effFromCurrentKey2.equals(sMsg.A.no(j).effFromDt_A9.getValue()) && newRowNumCurrentKey2 == sMsg.A.no(j).xxNewRowNum_A9.getValueInt()) {

                        continue;

                    } else {

                        whCdCurrentKey2 = sMsg.A.no(j).whCd_A9.getValue();
                        effFromCurrentKey2 = sMsg.A.no(j).effFromDt_A9.getValue();
                        newRowNumCurrentKey2 = sMsg.A.no(j).xxNewRowNum_A9.getValueInt();

                        if (i == j) {

                            continue;
                        }
                        // 2013/05/21 R-OM025 Inventory Transaction
                        // Modify Start
                        if (sMsg.A.no(i).whCd_A1.getValue().equals(sMsg.A.no(j).whCd_A1.getValue())) {
                        // if(sMsg.A.no(i).whCd_A2.getValue().equals(sMsg.A.no(j).whCd_A1.getValue())){
                        // 2013/05/21 R-OM025 Inventory Transaction Modify End
                            if (ZYPDateUtil.compare(sMsg.A.no(i).effThruDt_A1.getValue(), sMsg.A.no(j).effFromDt_A1.getValue()) == -1 || ZYPDateUtil.compare(sMsg.A.no(j).effThruDt_A1.getValue(), sMsg.A.no(i).effFromDt_A1.getValue()) == -1) {

                                // There is no processing.

                            } else {

                                effPerDuplicationCheckErrorFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (effPerDuplicationCheckErrorFlag) {

                    sMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NLBM0031E);
                    sMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NLBM0031E);

                    effPerDuplicationCheckErrorFlag = true;

                }

                if (errorAlreadySetFlag == false && effPerDuplicationCheckErrorFlag == true) {

                    errorAlreadySetFlag = true;
                    firstErrorRowNum = i;
                }
            }
        }

        if (errorAlreadySetFlag) {

            NLBL0060CommonLogic.copyErrorPageDataFromSMsgToCMsg(firstErrorRowNum, cMsg, sMsg);
            return false;
        }

        return true;
    }

    private boolean checkEffPerContinuousness(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        List<String> listOfWHInputFromScrn = getListOfWHInputFromScrn(cMsg, sMsg);

        for (int i = 0; i < listOfWHInputFromScrn.size(); i++) {

            String whCd = listOfWHInputFromScrn.get(i);
            S21SsmEZDResult ssmResultForGetWHCnt = NLBL0060Query.getInstance().getWHCnt(sMsg, whCd);
            Integer cntObj = (Integer) ssmResultForGetWHCnt.getResultObject();

            if (cntObj.intValue() > 0) {

                // There is no processing.

            } else {

                boolean errorFlag = false;
                int firstErrorRowNum = 0;

                String salesDate = ZYPDateUtil.getSalesDate();

                if (MAX_VALUE_OF_EFFECTIVE_PERIOD.equals(getEffThru(cMsg, sMsg, whCd, salesDate))) {

                    // There is no processing.

                } else {

                    for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                        // 2013/05/21 R-OM025 Inventory Transaction
                        // Modify Start
                        if (whCd.equals(sMsg.A.no(j).whCd_A1.getValue())) {
                            // if
                            // (whCd.equals(sMsg.A.no(j).whCd_A1.getValue()))
                            // {
                            // 2013/05/21 R-OM025 Inventory
                            // Transaction Modify End

                            sMsg.A.no(j).effFromDt_A1.setErrorInfo(1, NLBM0085E, new String[] {ZYPDateUtil.convertFormat(ZYPDateUtil.getSalesDate(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, DATE_SEPARATOR) });
                            sMsg.A.no(j).effThruDt_A1.setErrorInfo(1, NLBM0085E, new String[] {ZYPDateUtil.convertFormat(ZYPDateUtil.getSalesDate(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, DATE_SEPARATOR) });

                            if (errorFlag == false) {

                                errorFlag = true;
                                firstErrorRowNum = j;
                            }
                        }
                    }

                    NLBL0060CommonLogic.copyErrorPageDataFromSMsgToCMsg(firstErrorRowNum, cMsg, sMsg);
                    return false;
                }
            }
        }

        return true;
    }

    private List<String> getListOfWHInputFromScrn(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        List<String> listOfWHInputFromScrn = new ArrayList<String>();
        Map<String, String> whMapWrk = new HashMap<String, String>();

        String whCdCurrentKey = "";
        String effFromCurrentKey = "";
        int newRowNumCurrentKey = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (whCdCurrentKey.equals(sMsg.A.no(i).whCd_A9.getValue()) && effFromCurrentKey.equals(sMsg.A.no(i).effFromDt_A9.getValue()) && newRowNumCurrentKey == sMsg.A.no(i).xxNewRowNum_A9.getValueInt()) {

                continue;

            }

            whCdCurrentKey = sMsg.A.no(i).whCd_A9.getValue();
            effFromCurrentKey = sMsg.A.no(i).effFromDt_A9.getValue();
            newRowNumCurrentKey = sMsg.A.no(i).xxNewRowNum_A9.getValueInt();

            // 2013/05/21 R-OM025 Inventory Transaction Modify Start
            if (whMapWrk.containsKey(sMsg.A.no(i).whCd_A1.getValue())) {
            // if(whMapWrk.containsKey(sMsg.A.no(i).whCd_A1.getValue())){
            // 2013/05/21 R-OM025 Inventory Transaction Modify End
                // There is no processing.

            } else {
                // 2013/05/21 R-OM025 Inventory Transaction Modify
                // Start
                whMapWrk.put(sMsg.A.no(i).whCd_A1.getValue(), "");
                // whMapWrk.put(sMsg.A.no(i).whCd_A2.getValue(), "");
                listOfWHInputFromScrn.add(sMsg.A.no(i).whCd_A1.getValue());
                // listOfWHInputFromScrn.add(sMsg.A.no(i).whCd_A2.getValue());
                // 2013/05/21 R-OM025 Inventory Transaction Modify
                // Start
            }
        }

        return listOfWHInputFromScrn;
    }

    private String getEffThru(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg, String whCd, String effFrom) {

        String whCdCurrentKey = "";
        String effFromCurrentKey = "";
        int newRowNumCurrentKey = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (whCdCurrentKey.equals(sMsg.A.no(i).whCd_A9.getValue()) && effFromCurrentKey.equals(sMsg.A.no(i).effFromDt_A9.getValue()) && newRowNumCurrentKey == sMsg.A.no(i).xxNewRowNum_A9.getValueInt()) {

                continue;

            }

            whCdCurrentKey = sMsg.A.no(i).whCd_A9.getValue();
            effFromCurrentKey = sMsg.A.no(i).effFromDt_A9.getValue();
            newRowNumCurrentKey = sMsg.A.no(i).xxNewRowNum_A9.getValueInt();

            // 2013/05/21 R-OM025 Inventory Transaction Modify Start
            if (whCd.equals(sMsg.A.no(i).whCd_A1.getValue()) && effFrom.equals(sMsg.A.no(i).effFromDt_A1.getValue())) {
          // if (whCd.equals(sMsg.A.no(i).whCd_A1.getValue()) && effFrom.equals(sMsg.A.no(i).effFromDt_A1.getValue())) {
                // 2013/05/21 R-OM025 Inventory Transaction Modify End
                if (MAX_VALUE_OF_EFFECTIVE_PERIOD.equals(sMsg.A.no(i).effThruDt_A1.getValue())) {

                    return sMsg.A.no(i).effThruDt_A1.getValue();
                }

                return getEffThru(cMsg, sMsg, whCd, ZYPDateUtil.addDays(sMsg.A.no(i).effThruDt_A1.getValue(), 1));
            }
        }

        return null;
    }

    private boolean checkDataExclusive(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        if (sMsg.X.getValidCount() != sMsg.Y.getValidCount()) {

            cMsg.setMessageInfo(NLBM0009E);
            return false;
        }

        for (int i = 0; i < sMsg.X.getValidCount(); i++) {

            if (sMsg.X.no(i).ezUpTime_X1.getValue().equals(sMsg.Y.no(i).ezUpTime_Y1.getValue()) && sMsg.X.no(i).ezUpTimeZone_X1.getValue().equals(sMsg.Y.no(i).ezUpTimeZone_Y1.getValue())) {

                continue;

            } else {

                cMsg.setMessageInfo(NLBM0009E);
                return false;
            }
        }

        return true;
    }

    private boolean updateExistingData(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        int newRowNumCurrentKey = 0;
        String whCdCurrentKey = "";
        String effFromCurrentKey = "";

        String whCd;
        String effFromDt;
        String effThruDt;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (newRowNumCurrentKey == sMsg.A.no(i).xxNewRowNum_A9.getValueInt() && whCdCurrentKey.equals(sMsg.A.no(i).whCd_A9.getValue()) && effFromCurrentKey.equals(sMsg.A.no(i).effFromDt_A9.getValue())) {

                continue;

            }

            newRowNumCurrentKey = sMsg.A.no(i).xxNewRowNum_A9.getValueInt();
            whCdCurrentKey = sMsg.A.no(i).whCd_A9.getValue();
            effFromCurrentKey = sMsg.A.no(i).effFromDt_A9.getValue();
            // 2013/05/21 R-OM025 Inventory Transaction Modify Start
            whCd = sMsg.A.no(i).whCd_A1.getValue();
            // whCd = sMsg.A.no(i).whCd_A2.getValue();
            // 2013/05/21 R-OM025 Inventory Transaction Modify End
            effFromDt = sMsg.A.no(i).effFromDt_A1.getValue();
            effThruDt = sMsg.A.no(i).effThruDt_A1.getValue();

            S21SsmEZDResult ssmResultForGetWHCnt = NLBL0060Query.getInstance().getWHCnt(sMsg, whCd);
            Integer cntObj = (Integer) ssmResultForGetWHCnt.getResultObject();

            if (cntObj.intValue() == 0) {

                // There is no processing.

            } else {

                WH_LEAD_TMTMsg ssmParamWHLeadTmTMsg = new WH_LEAD_TMTMsg();
                ssmParamWHLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
                ssmParamWHLeadTmTMsg.whCd.setValue(whCd);
                ssmParamWHLeadTmTMsg.effFromDt.setValue(effFromDt);
                ssmParamWHLeadTmTMsg.effThruDt.setValue(effThruDt);

                S21SsmEZDResult ssmResultForGetWHLeadTmListForUpdateInsert = NLBL0060Query.getInstance().getWHLeadTmListForUpdateInsert(ssmParamWHLeadTmTMsg);
                List<WH_LEAD_TMTMsg> WHLeadTmTMsgListForUpdateInsert = (List<WH_LEAD_TMTMsg>) ssmResultForGetWHLeadTmListForUpdateInsert.getResultObject();

                if (WHLeadTmTMsgListForUpdateInsert.size() > 0) {

                    if (!updateInsertWHLeadTm(cMsg, sMsg, WHLeadTmTMsgListForUpdateInsert, effFromDt, effThruDt)) {

                        return false;
                    }

                } else {

                    if (!updateWHLeadTm(cMsg, sMsg, whCd, effFromDt)) {

                        return false;
                    }

                    if (!deleteInsertWHLeadTm(cMsg, sMsg, whCd, effThruDt)) {

                        return false;
                    }

                    if (!deleteWHLeadTm(cMsg, sMsg, whCd, effFromDt, effThruDt)) {

                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean updateInsertWHLeadTm(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg, List<WH_LEAD_TMTMsg> WHLeadTmTMsgListForUpdateInsert, String effFromDt, String effThruDt) {

        for (int i = 0; i < WHLeadTmTMsgListForUpdateInsert.size(); i++) {

            WH_LEAD_TMTMsg WHLeadTmTMsgFoUpdateInsert = WHLeadTmTMsgListForUpdateInsert.get(i);

            WH_LEAD_TMTMsg WHLeadTmTMsgFoUpdate = new WH_LEAD_TMTMsg();
            EZDMsg.copy(WHLeadTmTMsgFoUpdateInsert, null, WHLeadTmTMsgFoUpdate, null);
            WHLeadTmTMsgFoUpdate.effThruDt.setValue(ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(WHLeadTmTMsgFoUpdate);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(WHLeadTmTMsgFoUpdate.getReturnCode())) {

                cMsg.setMessageInfo(NLBM0024E, new String[]{"Update WH_LEAD_TM"});
                return false;
            }

            WH_LEAD_TMTMsg WHLeadTmTMsgForInsert = new WH_LEAD_TMTMsg();
            EZDMsg.copy(WHLeadTmTMsgFoUpdateInsert, null, WHLeadTmTMsgForInsert, null);
            WHLeadTmTMsgForInsert.effFromDt.setValue(ZYPDateUtil.addDays(effThruDt, 1));
            EZDTBLAccessor.create(WHLeadTmTMsgForInsert);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(WHLeadTmTMsgForInsert.getReturnCode())) {

                cMsg.setMessageInfo(NLBM0024E, new String[]{"Create WH_LEAD_TM"});
                return false;
            }
        }

        return true;
    }

    private boolean updateWHLeadTm(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg, String whCd, String effFromDt) {

        WH_LEAD_TMTMsg ssmParamWHLeadTmTMsg = new WH_LEAD_TMTMsg();
        ssmParamWHLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
        ssmParamWHLeadTmTMsg.whCd.setValue(whCd);
        ssmParamWHLeadTmTMsg.effFromDt.setValue(effFromDt);

        S21SsmEZDResult ssmResultForGetWHLeadTmListForUpdate = NLBL0060Query.getInstance().getWHLeadTmListForUpdate(ssmParamWHLeadTmTMsg);
        List<WH_LEAD_TMTMsg> WHLeadTmTMsgListForUpdate = (List<WH_LEAD_TMTMsg>) ssmResultForGetWHLeadTmListForUpdate.getResultObject();

        for (int i = 0; i < WHLeadTmTMsgListForUpdate.size(); i++) {

            WH_LEAD_TMTMsg WHLeadTmTMsgForUpdate = WHLeadTmTMsgListForUpdate.get(i);
            WHLeadTmTMsgForUpdate.effThruDt.setValue(ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(WHLeadTmTMsgForUpdate);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(WHLeadTmTMsgForUpdate.getReturnCode())) {
                cMsg.setMessageInfo(NLBM0024E, new String[]{"Update WH_LEAD_TM"});
                return false;
            }
        }

        return true;
    }

    private boolean deleteInsertWHLeadTm(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg, String whCd, String effThruDt) {

        WH_LEAD_TMTMsg ssmParamWHLeadTmTMsg = new WH_LEAD_TMTMsg();
        ssmParamWHLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
        ssmParamWHLeadTmTMsg.whCd.setValue(whCd);
        ssmParamWHLeadTmTMsg.effThruDt.setValue(effThruDt);

        S21SsmEZDResult ssmResultForGetWHLeadTmListForDeleteInsert = NLBL0060Query.getInstance().getWHLeadTmListForDeleteInsert(ssmParamWHLeadTmTMsg);
        List<WH_LEAD_TMTMsg> WHLeadTmTMsgListForDeleteInsert = (List<WH_LEAD_TMTMsg>) ssmResultForGetWHLeadTmListForDeleteInsert.getResultObject();

        for (int i = 0; i < WHLeadTmTMsgListForDeleteInsert.size(); i++) {

            WH_LEAD_TMTMsg WHLeadTmTMsgFoDeleteInsert = WHLeadTmTMsgListForDeleteInsert.get(i);
            EZDTBLAccessor.logicalRemove(WHLeadTmTMsgFoDeleteInsert);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(WHLeadTmTMsgFoDeleteInsert.getReturnCode())) {

                cMsg.setMessageInfo(NLBM0024E, new String[]{"Remove WH_LEAD_TM"});
                return false;
            }
            // QC#28077
            if (!"99991231".equals(effThruDt)) {
                WHLeadTmTMsgFoDeleteInsert.effFromDt.setValue(ZYPDateUtil.addDays(effThruDt, 1));
            }
            EZDTBLAccessor.create(WHLeadTmTMsgFoDeleteInsert);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(WHLeadTmTMsgFoDeleteInsert.getReturnCode())) {

                cMsg.setMessageInfo(NLBM0024E, new String[]{"Create WH_LEAD_TM"});
                return false;
            }
        }

        return true;
    }

    private boolean deleteWHLeadTm(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg, String whCd, String effFromDt, String effThruDt) {

        WH_LEAD_TMTMsg ssmParamWHLeadTmTMsg = new WH_LEAD_TMTMsg();
        ssmParamWHLeadTmTMsg.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());
        ssmParamWHLeadTmTMsg.whCd.setValue(whCd);
        ssmParamWHLeadTmTMsg.effFromDt.setValue(effFromDt);
        ssmParamWHLeadTmTMsg.effThruDt.setValue(effThruDt);

        S21SsmEZDResult ssmResultForGetWHLeadTmListForDelete = NLBL0060Query.getInstance().getWHLeadTmListForDelete(ssmParamWHLeadTmTMsg);
        List<WH_LEAD_TMTMsg> WHLeadTmTMsgListForDelete = (List<WH_LEAD_TMTMsg>) ssmResultForGetWHLeadTmListForDelete.getResultObject();

        for (int i = 0; i < WHLeadTmTMsgListForDelete.size(); i++) {

            WH_LEAD_TMTMsg WHLeadTmTMsgForDelete = WHLeadTmTMsgListForDelete.get(i);
            EZDTBLAccessor.logicalRemove(WHLeadTmTMsgForDelete);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(WHLeadTmTMsgForDelete.getReturnCode())) {

                cMsg.setMessageInfo(NLBM0024E, new String[]{"Remove WH_LEAD_TM"});
                return false;
            }
        }

        return true;
    }

    private boolean createNewData(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        WH_LEAD_TMTMsg WHLeadTmTMsgForInsert = new WH_LEAD_TMTMsg();
        WHLeadTmTMsgForInsert.glblCmpyCd.setValue(sMsg.glblCmpyCd_G1.getValue());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // 2013/05/21 R-OM025 Inventory Transaction Modify Start
            WHLeadTmTMsgForInsert.whCd.setValue(sMsg.A.no(i).whCd_A1.getValue());
            // WHLeadTmTMsgForInsert.whCd.setValue(sMsg.A.no(i).whCd_A1.getValue());
            // 2013/05/21 R-OM025 Inventory Transaction Modify End
            WHLeadTmTMsgForInsert.shpgSvcLvlCd.setValue(sMsg.A.no(i).shpgSvcLvlCd_A9.getValue());
            WHLeadTmTMsgForInsert.shpgModeCd.setValue(sMsg.A.no(i).shpgModeCd_A9.getValue());
            WHLeadTmTMsgForInsert.effFromDt.setValue(sMsg.A.no(i).effFromDt_A1.getValue());
            WHLeadTmTMsgForInsert.effThruDt.setValue(sMsg.A.no(i).effThruDt_A1.getValue());
            WHLeadTmTMsgForInsert.shpgCloTmTs.setValue(sMsg.A.no(i).shpgCloTmTs_A1.getValue());
            WHLeadTmTMsgForInsert.pickPackAot.setValue(sMsg.A.no(i).xxDplyLeadTmDaysAot_A1.getValueInt() * 24);
            EZDTBLAccessor.create(WHLeadTmTMsgForInsert);

            if (!DB_ACCESS_PARTS_RETURN_CODE_NORMAL.equals(WHLeadTmTMsgForInsert.getReturnCode())) {

                cMsg.setMessageInfo(NLBM0024E, new String[]{"Create WH_LEAD_TM"});
                return false;
            }
        }

        return true;
    }

    /**
     * Check Location Code
     * @param bizMsg NLCL0110CMsg
     * @return OK(true) or NG(false)
     */
    private boolean isLocCdCheckForDetail(NLBL0060_ASMsg asMsg) {
        // Get Security Attribute
        List<S21DataSecurityAttributeData> securityAttr = getUserProfileService().getDataSecurityAttributeDataListFor(getContextUserInfo().getUserId(), NLBL0060Constant.BUSINESS_ID, S21DataSecurityAttributeData.NAME_WAREHOUSE);
        // Get Location Info
        NMXC100001EnableWHData locInfo = NLBL0060CommonLogic.getInvtyLocInfoForDetail(asMsg.whCd_A1.getValue(), securityAttr, getGlobalCompanyCode());

        // NG LocCd
        if (locInfo == null) {
            asMsg.whCd_A1.setErrorInfo(1, "NLCM0004E");
            return false;
        } else if (ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
            asMsg.whCd_A1.setErrorInfo(1, locInfo.getXxMsgId());
            return false;
        }

        // Set Location Name
        ZYPEZDItemValueSetter.setValue(asMsg.locNm_A1, locInfo.getInvtyLocNm());

        return true;
    }
}
