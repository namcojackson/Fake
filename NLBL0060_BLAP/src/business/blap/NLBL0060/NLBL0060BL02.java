/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0060;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL0060.common.NLBL0060CommonLogic;
import business.blap.NLBL0060.constant.NLBL0060Constant;
import business.db.RTL_WHTMsg;
import business.file.NLBL0060F00FMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityProfile;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * This class does search business process of BusinessID NLBL0060.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/20   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/21   CUSA            Mizutani         Update          R-OM025 Inventory Transaction
 * 2013/10/22   Hitachi         H.Narumi         Update          QC2852
 *</pre>
 */
public class NLBL0060BL02 extends S21BusinessHandler implements NLBL0060Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            sMsg.clearErrorInfo();

            if (EVENT_NM_NLBL0060_INIT.equals(screenAplID)) {
                doProcess_NLBL0060_INIT((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else if (EVENT_NM_NLBL0060SCRN00_ONCLICK_SEARCH.equals(screenAplID)) {
                doProcess_NLBL0060Scrn00_OnClick_Search((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else if (EVENT_NM_NLBL0060SCRN00_PAGEPREV.equals(screenAplID)) {
                doProcess_NLBL0060Scrn00_PagePrev((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else if (EVENT_NM_NLBL0060SCRN00_PAGENEXT.equals(screenAplID)) {
                doProcess_NLBL0060Scrn00_PageNext((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else if (EVENT_NM_NLBL0060SCRN00_ONCLICK_DELETEROW.equals(screenAplID)) {
                doProcess_NLBL0060Scrn00_OnClick_DeleteRow((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else if (EVENT_NM_NLBL0060SCRN00_ONCLICK_INSERTROW.equals(screenAplID)) {
                doProcess_NLBL0060Scrn00_OnClick_InsertRow((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else if (EVENT_NM_NLBL0060SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLBL0060Scrn00_CMN_Submit((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else if (EVENT_NM_NLBL0060SCRN00_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NLBL0060Scrn00_CMN_Download((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else if (EVENT_NM_NLBL0060SCRN00_CMN_RESET.equals(screenAplID)) {
                doProcess_NLBL0060Scrn00_CMN_Reset((NLBL0060CMsg) cMsg, (NLBL0060SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the initial display event is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060_INIT(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLBL0060_INIT================================START", this);

        init(cMsg, sMsg);

        EZDDebugOutput.println(1, "doProcess_NLBL0060_INIT================================END", this);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_Seach] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_OnClick_Search(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLBL0060Scrn00_OnClick_Seach================================START", this);
        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        // Location Check
        NLBL0060CMsg bizMsg = (NLBL0060CMsg) cMsg;
        // 10/06/2015 remove start
        /*
        if (!isLocCdCheck(bizMsg)) {
            return;
        }
        */
        // 10/06/2015 remove end
        // 2013/05/21 R-OM025 Inventory Transaction Add End
        cMsg.xxRadioBtn_A1.clear();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A1.setValue(0);
        cMsg.xxPageShowToNum_A1.setValue(0);
        cMsg.xxPageShowOfNum_A1.setValue(0);
        cMsg.whCd_S1.setValue(cMsg.whCd_H2.getValue());
        cMsg.effFromDt_S1.setValue(cMsg.effFromDt_H1.getValue());
        cMsg.effThruDt_S1.setValue(cMsg.effThruDt_H1.getValue());

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.X);
        ZYPTableUtil.clear(sMsg.Y);
        ZYPTableUtil.clear(sMsg.T);

        EZDMsg.copy(cMsg, null, sMsg, null);

        search(cMsg, sMsg);

        EZDDebugOutput.println(1, "doProcess_NLBL0060Scrn00_OnClick_Seach================================END", this);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[PagePrev] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_PagePrev(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        cMsg.xxRadioBtn_A1.clear();
        NLBL0060CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        NLBL0060CommonLogic.copyCurrentTblDataFromCMsgToSMsg(cMsg, sMsg);

        int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
        int prevPagenationFrom = pagenationFrom - cMsg.A.length();
        int prevPagenationTo = pagenationFrom - 1;

        ZYPTableUtil.clear(cMsg.A);

        for (int i = prevPagenationFrom; i < prevPagenationFrom + cMsg.A.length(); i++) {

            EZDMsg.copy(sMsg.A.no(i - 1), null, cMsg.A.no(i - prevPagenationFrom), null);
        }

        cMsg.A.setValidCount(cMsg.A.length());
        cMsg.xxPageShowFromNum_A1.setValue(prevPagenationFrom);
        cMsg.xxPageShowToNum_A1.setValue(prevPagenationTo);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[PageNext] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_PageNext(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        cMsg.xxRadioBtn_A1.clear();
        NLBL0060CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        NLBL0060CommonLogic.copyCurrentTblDataFromCMsgToSMsg(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);

        int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
        int nextPagenationFrom = pagenationFrom + cMsg.A.length();
        int validCount = 0;

        for (int i = nextPagenationFrom; i < nextPagenationFrom + cMsg.A.length(); i++) {

            validCount++;

            EZDMsg.copy(sMsg.A.no(i - 1), null, cMsg.A.no(i - nextPagenationFrom), null);

            if (i == sMsg.A.getValidCount()) {

                break;
            }
        }

        cMsg.A.setValidCount(validCount);
        cMsg.xxPageShowFromNum_A1.setValue(nextPagenationFrom);
        cMsg.xxPageShowToNum_A1.setValue(nextPagenationFrom + validCount - 1);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_DeleteRow] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_OnClick_DeleteRow(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLBL0060Scrn00_OnClick_DeleteRow================================START", this);

        NLBL0060CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        NLBL0060CommonLogic.copyCurrentTblDataFromCMsgToSMsg(cMsg, sMsg);

        int index = cMsg.xxRadioBtn_A1.getValueInt();
        cMsg.xxRadioBtn_A1.clear();
        int selectedRecKeyNewRowNum = cMsg.A.no(index).xxNewRowNum_A9.getValueInt();
        String selectedRecKeyWhCd = cMsg.A.no(index).whCd_A9.getValue();
        String selectedRecKeyEffFromDt = cMsg.A.no(index).effFromDt_A9.getValue();

        int newRecCnt = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (selectedRecKeyNewRowNum == sMsg.A.no(i).xxNewRowNum_A9.getValueInt() && selectedRecKeyWhCd.equals(sMsg.A.no(i).whCd_A9.getValue()) && selectedRecKeyEffFromDt.equals(sMsg.A.no(i).effFromDt_A9.getValue())) {

                continue;
            }

            EZDMsg.copy(sMsg.A.no(i), null, sMsg.T.no(newRecCnt), null);
            newRecCnt++;
        }

        sMsg.T.setValidCount(newRecCnt);

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);

        EZDMsg.copy(sMsg.T, null, sMsg.A, null);

        sMsg.A.setValidCount(newRecCnt);

        ZYPTableUtil.clear(sMsg.T);

        if (newRecCnt == 0) {

            cMsg.A.setValidCount(0);
            cMsg.xxPageShowFromNum_A1.setValue(0);
            cMsg.xxPageShowToNum_A1.setValue(0);
            cMsg.xxPageShowOfNum_A1.setValue(0);

        } else {

            int pagenationFrom;

            if (newRecCnt < cMsg.xxPageShowFromNum_A1.getValueInt()) {

                pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt() - cMsg.A.length();

            } else {

                pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
            }

            int validCountScreen = 0;

            for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.length(); i++) {

                EZDMsg.copy(sMsg.A.no(i - 1), null, cMsg.A.no(i - pagenationFrom), null);

                validCountScreen++;

                if (i == sMsg.A.getValidCount()) {

                    break;
                }
            }

            cMsg.A.setValidCount(validCountScreen);
            cMsg.xxPageShowFromNum_A1.setValue(pagenationFrom);
            cMsg.xxPageShowToNum_A1.setValue(pagenationFrom + validCountScreen - 1);
            cMsg.xxPageShowOfNum_A1.setValue(newRecCnt);
        }

        EZDDebugOutput.println(1, "doProcess_NLBL0060Scrn00_OnClick_DeleteRow================================END", this);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_InsertRow] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_OnClick_InsertRow(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NLBL0060Scrn00_OnClick_InsertRow================================START", this);

        int index = cMsg.xxRadioBtn_A1.getValueInt();

        NLBL0060CommonLogic.setSearchConditionItemValue(cMsg, sMsg);
        NLBL0060CommonLogic.copyCurrentTblDataFromCMsgToSMsg(cMsg, sMsg);

        cMsg.xxRadioBtn_A1.clear();
        int selectedRecKeyNewRowNum = cMsg.A.no(index).xxNewRowNum_A9.getValueInt();
        String selectedRecKeyWhCd = cMsg.A.no(index).whCd_A9.getValue();
        String selectedRecKeyEffFromDt = cMsg.A.no(index).effFromDt_A9.getValue();

// 2013/05/21 R-OM025 Inventory Transaction Add Start
//        List<S21DataSecurityAttributeData> securityAttr = getUserProfileService().getDataSecurityAttributeDataListFor(getContextUserInfo().getUserId(), NLBL0060Constant.BUSINESS_ID, S21DataSecurityAttributeData.NAME_WAREHOUSE);
//        
//        // Get Location Info
//        NMXC100001EnableWHData locInfo = NLBL0060CommonLogic.getInvtyLocInfoForDetail(cMsg.A.no(index).whCd_A1.getValue(), securityAttr,getGlobalCompanyCode());
//
//        // NG LocCd
//        if (locInfo == null) {
//            cMsg.A.no(index).whCd_A1.setErrorInfo(1, "NLCM0004E");
//            return;
//        } else if (ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
//            cMsg.A.no(index).whCd_A1.setErrorInfo(1, locInfo.getXxMsgId());
//            return;
//        }
// 2013/05/21 R-OM025 Inventory Transaction Add End

        int copyRecFromNum = 0;
        int newRecCnt = 0;
        boolean copyRecCountingFlg = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (selectedRecKeyNewRowNum == sMsg.A.no(i).xxNewRowNum_A9.getValueInt() 
                    && selectedRecKeyWhCd.equals(sMsg.A.no(i).whCd_A9.getValue()) 
                    && selectedRecKeyEffFromDt.equals(sMsg.A.no(i).effFromDt_A9.getValue())) {

                if (copyRecCountingFlg == false) {

                    copyRecCountingFlg = true;
                    copyRecFromNum = i;
                    newRecCnt++;

                } else {

                    newRecCnt++;
                }

            } else {

                if (copyRecCountingFlg == true) {

                    break;
                }
            }
        }

        if (sMsg.A.getValidCount() + newRecCnt > MAX_NUM_OF_TABLE) {

            cMsg.setMessageInfo(NLBM0081E);
            return;
        }

        sMsg.xxNewRowNum_G1.setValue(sMsg.xxNewRowNum_G1.getValueInt() + 1);
        int newRowNum = sMsg.xxNewRowNum_G1.getValueInt();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (i == copyRecFromNum + newRecCnt) {

                break;
            }

            EZDMsg.copy(sMsg.A.no(i), null, sMsg.T.no(i), null);
        }

        for (int i = copyRecFromNum; i < copyRecFromNum + newRecCnt; i++) {

            EZDMsg.copy(sMsg.A.no(i), null, sMsg.T.no(i + newRecCnt), null);
            sMsg.T.no(i + newRecCnt).xxNewRowNum_A9.setValue(newRowNum);
            // 2013/05/21 R-OM025 Inventory Transaction Modify Start
            if (!ZYPCommonFunc.hasValue(sMsg.whCd_S1)) {
            // if (WH_ALL_CD_VALUE.equals(sMsg.whCd_S1.getValue())) {
             // 2013/05/21 R-OM025 Inventory Transaction Modify End

                // 2013/05/21 R-OM025 Inventory Transaction Modify Start
                sMsg.T.no(i + newRecCnt).whCd_A1.clear();
                //sMsg.T.no(i + newRecCnt).whCd_A2.clear();
                // 2013/05/21 R-OM025 Inventory Transaction Modify End
            }

            // 2013/05/21 R-OM025 Inventory Transaction Modify Start
            sMsg.T.no(i + newRecCnt).locNm_A1.clear();
            // 2013/05/21 R-OM025 Inventory Transaction Modify End

            sMsg.T.no(i + newRecCnt).effFromDt_A1.clear();
            sMsg.T.no(i + newRecCnt).effThruDt_A1.clear();
            sMsg.T.no(i + newRecCnt).whCd_A9.clear();
            sMsg.T.no(i + newRecCnt).effFromDt_A9.clear();
            sMsg.T.no(i + newRecCnt).effThruDt_A9.clear();
        }

        for (int i = copyRecFromNum + newRecCnt; i < sMsg.A.getValidCount(); i++) {

            EZDMsg.copy(sMsg.A.no(i), null, sMsg.T.no(i + newRecCnt), null);
        }

        sMsg.T.setValidCount(sMsg.A.getValidCount() + newRecCnt);

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);

        EZDMsg.copy(sMsg.T, null, sMsg.A, null);
        sMsg.A.setValidCount(sMsg.T.getValidCount());

        ZYPTableUtil.clear(sMsg.T);

        int pageNum;
        int pagenationFrom;

        pageNum = (copyRecFromNum + newRecCnt) / cMsg.A.length();
        pagenationFrom = pageNum * cMsg.A.length();

        int validCountScreen = 0;

        for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i == sMsg.A.getValidCount()) {

                break;
            }

            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            validCountScreen++;
        }

        cMsg.A.setValidCount(validCountScreen);
        cMsg.xxPageShowFromNum_A1.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_A1.setValue(pagenationFrom + validCountScreen);
        cMsg.xxPageShowOfNum_A1.setValue(sMsg.A.getValidCount());

        EZDDebugOutput.println(1, "doProcess_NLBL0060Scrn00_OnClick_InsertRow================================END", this);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[CMN_Submit] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_CMN_Submit(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        NLBL0060CommonLogic.setSearchConditionItemValue(cMsg, sMsg);

        cMsg.xxRadioBtn_A1.clear();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A1.setValue(0);
        cMsg.xxPageShowToNum_A1.setValue(0);
        cMsg.xxPageShowOfNum_A1.setValue(0);

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.X);
        ZYPTableUtil.clear(sMsg.Y);
        ZYPTableUtil.clear(sMsg.T);

        search(cMsg, sMsg);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[CMN_Download] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_CMN_Download(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        // set value for search condition
        NLBL0060CommonLogic.setSearchConditionItemValue(cMsg, sMsg);

        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXTENTION);

        S21SsmEZDResult ssmResult = NLBL0060Query.getInstance().getCSVDownloadData(sMsg);

        if (!ssmResult.isCodeNormal()) {

            cMsg.setMessageInfo(NZZM0000E);
            return;

        }

        if (MAX_COUNT_OF_CSV_DOWNLOAD < ssmResult.getQueryResultCount()) {

            cMsg.setMessageInfo(NZZM0007E);
            return;

        }

        NLBL0060F00FMsg fMsg = new NLBL0060F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        List<String> csvHeaderList = new ArrayList<String>();
        csvHeaderList.add(CSV_HEADER_WH);
        csvHeaderList.add(CSV_HEADER_EFFECTIVE_PERIOD_FROM);
        csvHeaderList.add(CSV_HEADER_EFFECTIVE_PERIOD_TO);
        csvHeaderList.add(CSV_HEADER_SHPG_MODE);
        csvHeaderList.add(CSV_HEADER_SHPG_SVC_LVL);
        csvHeaderList.add(CSV_HEADER_SHPG_CLO_TM_TS);
        csvHeaderList.add(CSV_HEADER_PICK_PACK_AOT);

        String[] csvHeader = (String[]) csvHeaderList.toArray(new String[0]);

        csvOutFile.writeHeader(csvHeader);

        List<NLBL0060F00FMsg> ssmResList = (List<NLBL0060F00FMsg>) ssmResult.getResultObject();
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_SHPG_CLO_TM_TS);

        for (int i = 0; i < ssmResList.size(); i++) {

            EZDMsg.copy(ssmResList.get(i), null, fMsg, null);

            if (ZYPCommonFunc.hasValue(ssmResList.get(i).moveEffFromDtTxt)) {

                // START 10/22/2013 H.Narumi [Defect.#QC2852,MOD]
                //ZYPEZDItemValueSetter.setValue(fMsg.effFromDtTxt, ZYPDateUtil.DateFormatter(ssmResList.get(i).effFromDtTxt.getValue(), DATE_FORMATT_FOR_DB, DATE_FORMATT_FOR_CSV));
                ZYPEZDItemValueSetter.setValue(fMsg.moveEffFromDtTxt, ZYPDateUtil.formatEzd8ToDisp(ssmResList.get(i).moveEffFromDtTxt.getValue(), true));
                // END 10/22/2013 H.Narumi [Defect.#QC2852,MOD]
            }

            if (ZYPCommonFunc.hasValue(ssmResList.get(i).moveEffThruDtTxt)) {

                // START 10/22/2013 H.Narumi [Defect.#QC2852,MOD]
                //ZYPEZDItemValueSetter.setValue(fMsg.effThruDtTxt, ZYPDateUtil.DateFormatter(ssmResList.get(i).effThruDtTxt.getValue(), DATE_FORMATT_FOR_DB, DATE_FORMATT_FOR_CSV));
                ZYPEZDItemValueSetter.setValue(fMsg.moveEffThruDtTxt, ZYPDateUtil.formatEzd8ToDisp(ssmResList.get(i).moveEffThruDtTxt.getValue(), true));
                // END 10/22/2013 H.Narumi [Defect.#QC2852,MOD]
            }

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[CMN_Reset] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0060Scrn00_CMN_Reset(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        init(cMsg, sMsg);
    }

    private void init(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        NLBL0060CommonLogic.clearCMsg(cMsg);
        NLBL0060CommonLogic.clearSMsg(sMsg);
        sMsg.glblCmpyCd_G1.setValue(getGlobalCompanyCode());

        // 2013/05/21 R-OM025 Inventory Transaction Add Start
        // Set Location Role Type Code List
        NLBL0060CMsg bizMsg = (NLBL0060CMsg) cMsg;
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_P2, NLBL0060CommonLogic.getLocRoleTpForPopup(getGlobalCompanyCode()));
        // 2013/05/21 R-OM025 Inventory Transaction Add End


        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21DataSecurityProfile dataSecurityProfile = profileService.getDataSecurityProfileFor(cMsg.getBusinessID());
        List<S21DataSecurityAttributeData> dataSecurityAttrList = dataSecurityProfile.getDataSecurityAttributeDataListFor(S21DataSecurityAttributeData.NAME_WAREHOUSE);

        if (dataSecurityAttrList == null || dataSecurityAttrList.isEmpty()) {

            // There is no processing.

        } else {

            boolean whAllFlg = false;
            
            for (int i = 0; i < dataSecurityAttrList.size(); i++) {
                sMsg.whCd_H1.no(i).setValue(dataSecurityAttrList.get(i).getValue());
                if (WH_ALL_VALUE.equals(dataSecurityAttrList.get(i).getValue())) {

                    whAllFlg = true;
                    break;
                }
            }

            if (whAllFlg) {

                sMsg.whCd_G1.setValue(WH_ALL_VALUE);
            }

            // =============================================
            // Making of WH pull-down list
            // =============================================
// 2013/05/13 R-OM025 Inventory Transaction Delete Start
//            S21SsmEZDResult ssmResultForGetWHList = NLBL0060Query.getInstance().getWHList(sMsg, dataSecurityAttrList);
//
//            if (ssmResultForGetWHList.isCodeNormal()) {
//
//                List<Map> whList = (List<Map>) ssmResultForGetWHList.getResultObject();
//
//                
//                // "00: ALL" is set to the head of selection of the pull-down list[WH].
//                sMsg.whCd_H1.no(0).setValue(WH_ALL_CD_VALUE);
//                sMsg.xxEdtCdNm_H1.no(0).setValue(WH_ALL_DISPLAY_VALUE);
//
//                for (int i = 0; i < whList.size(); i++) {
//
//                    Map whData = whList.get(i);
//
//                    if (i + 1 < sMsg.whCd_H1.length()) {
//
//                        sMsg.whCd_H1.no(i + 1).setValue((String) whData.get(WH_CD));
//                        sMsg.xxEdtCdNm_H1.no(i + 1).setValue(ZYPCommonFunc.concatString((String) whData.get(WH_CD), LIST_BOX_DELIMITER, (String) whData.get(LOC_NM)));
//                    } else {
//
//                        break;
//                    }
//                }
//
//            } else {
//
//                // There is no processing.
//            }
// 2013/05/13 R-OM025 Inventory Transaction Delete End

            
            cMsg.xxPageShowFromNum_A1.setValue(0);
            cMsg.xxPageShowToNum_A1.setValue(0);
            cMsg.xxPageShowOfNum_A1.setValue(0);
            EZDMsg.copy(sMsg, null, cMsg, null);
        }

    }

    private void search(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        S21SsmEZDResult ssmResult = NLBL0060Query.getInstance().getWHLeadTmList(sMsg);

        if (ssmResult.isCodeNormal()) {

            if (ssmResult.getQueryResultCount() > MAX_NUM_OF_TABLE) {

                if (!EVENT_NM_NLBL0060SCRN00_CMN_SUBMIT.equals(cMsg.getScreenAplID())) {

                    // setting of message when the maximum acquisition
                    // number is exceeded
                    cMsg.setMessageInfo(NZZM0001W);
                }
            }

            removeHalfwaySearchResult(cMsg, sMsg);
            // 2013/05/21 R-OM025 Inventory Transaction DELETE Start
//            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//
//                for (int j = 1; j < sMsg.whCd_.length(); j++) {
//
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).whCd_A1.no(j - 1), sMsg.whCd_H1.no(j));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxEdtCdNm_A1.no(j - 1), sMsg.xxEdtCdNm_H1.no(j));
//                }
//            }
            // 2013/05/21 R-OM025 Inventory Transaction DELETE End

            // 10/06/2015 add start
            // Refresh Line WH name
            refreshLineWhName(cMsg, sMsg);
            // 10/06/2015 add end

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);
            cMsg.xxPageShowFromNum_A1.setValue(1);
            cMsg.xxPageShowToNum_A1.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A1.setValue(sMsg.A.getValidCount());

            // search snapshot data (This data is used when submit
            // event is generated.)
            NLBL0060Query.getInstance().getWHLeadTmListForSearchSnapshot(sMsg);

            if (!EVENT_NM_NLBL0060SCRN00_CMN_SUBMIT.equals(cMsg.getScreenAplID())) {

                cMsg.setMessageInfo(NZZM0002I);
            }

        } else {

            // When there is no search result

            if (!EVENT_NM_NLBL0060SCRN00_CMN_SUBMIT.equals(cMsg.getScreenAplID())) {

                cMsg.setMessageInfo(NZZM0000E);
            }
        }

        // 10/06/2015 add start
        // Refresh Header WH name
        refreshHeaderWhName(cMsg, sMsg);
        // 10/06/2015 add end
    }

    private void removeHalfwaySearchResult(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

        if (sMsg.A.getValidCount() <= MAX_NUM_OF_TABLE) {

            return;
        }

        String whCdForRemove = sMsg.A.no(MAX_NUM_OF_TABLE).whCd_A1.getValue();
        String effFromForRemove = sMsg.A.no(MAX_NUM_OF_TABLE).effFromDt_A1.getValue();

        int validCount = sMsg.A.getValidCount();
        int currentIndex = sMsg.A.getValidCount() - 1;

        while (currentIndex >= 0) {

            if (whCdForRemove.equals(sMsg.A.no(currentIndex).whCd_A1.getValue()) && effFromForRemove.equals(sMsg.A.no(currentIndex).effFromDt_A1.getValue())) {

                sMsg.A.no(currentIndex).clear();
                validCount--;
                currentIndex--;

            } else {

                break;
            }
        }

        sMsg.A.setValidCount(validCount);
    }

    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    /**
     * Check Location Code
     * @param bizMsg NLCL0110CMsg
     * @return OK(true) or NG(false)
     */
    private boolean isLocCdCheck(NLBL0060CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.whCd_H2.getValue())){
            bizMsg.locNm_H2.clear();
            return true;
        }
        // Get Security Attribute
        List<S21DataSecurityAttributeData> securityAttr = getUserProfileService().getDataSecurityAttributeDataListFor(getContextUserInfo().getUserId(), NLBL0060Constant.BUSINESS_ID, S21DataSecurityAttributeData.NAME_WAREHOUSE);
        // Get Location Info
        NMXC100001EnableWHData locInfo = NLBL0060CommonLogic.getInvtyLocInfo(bizMsg, securityAttr, getGlobalCompanyCode());

        // NG LocCd
        if (locInfo == null) {
            bizMsg.whCd_H2.setErrorInfo(1, "NLBM0072E");
            return false;
        } else if (ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
            bizMsg.whCd_H2.setErrorInfo(1, locInfo.getXxMsgId());
            return false;
        }

        // Set Location Name
        ZYPEZDItemValueSetter.setValue(bizMsg.locNm_H2, locInfo.getInvtyLocNm());

        return true;
    }
    // 2013/05/21 R-OM025 Inventory Transaction Add End

    // 10/06/2015 add start
    /**
     * Refresh WH Name
     * @param cMsg NLBL0060CMsg
     * @param sMsg NLBL0060SMsg
     */
    private void refreshHeaderWhName(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {
        // Header
        if (ZYPCommonFunc.hasValue(cMsg.whCd_H2.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.locNm_H2, getWhName(cMsg.whCd_H2.getValue()));
        }
    }

    /**
     * Refresh WH Name
     * @param cMsg NLBL0060CMsg
     * @param sMsg NLBL0060SMsg
     */
    private void refreshLineWhName(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {
        // Line
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_A1, getWhName(sMsg.A.no(i).whCd_A1.getValue()));
        }
    }

    /**
     * Get WH Name
     * @param rtlWhCd  String
     * @return rtlWhNm String
     */
    private String getWhName(String rtlWhCd) {

        RTL_WHTMsg rtlWh = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, rtlWhCd);
        rtlWh = (RTL_WHTMsg) S21FastTBLAccessor.findByKey(rtlWh);
        if (rtlWh == null) {
            return BLANK;
        } else {
            return rtlWh.rtlWhNm.getValue();
        }
    }
    // 10/06/2015 add end
}
