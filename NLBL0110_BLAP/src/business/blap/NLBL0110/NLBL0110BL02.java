package business.blap.NLBL0110;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL0110.common.NLBL0110CommonLogic;
import business.blap.NLBL0110.constant.NLBL0110Constant;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityProfile;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * This class does search business process of BusinessID NLBL0110.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/02/04   Fujitsu         S.Uehara        Create          N/A
 * 2013/05/24   Fujitsu         T.Tozuka        Create          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0110BL02 extends S21BusinessHandler implements NLBL0110Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if (sMsg != null) {
                sMsg.clearErrorInfo();
            }

            if (EVENT_NM_NLBL0110_INIT.equals(screenAplID)) {
                doProcess_NLBL0110_INIT((NLBL0110CMsg) cMsg, (NLBL0110SMsg) sMsg);

            } else if (EVENT_NM_NLBL0110SCRN00_SEARCH.equals(screenAplID)) {
                doProcess_NLBL0110Scrn00_Search((NLBL0110CMsg) cMsg, (NLBL0110SMsg) sMsg);

            } else if (EVENT_NM_NLBL0110SCRN00_VIEW.equals(screenAplID)) {
                doProcess_NLBL0110Scrn00_View((NLBL0110CMsg) cMsg, (NLBL0110SMsg) sMsg);

            } else if (EVENT_NM_NLBL0110SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLBL0110Scrn00_CMN_Submit((NLBL0110CMsg) cMsg, (NLBL0110SMsg) sMsg);

            } else if (EVENT_NM_NLBL0110SCRN00_CMN_RESET.equals(screenAplID)) {
                doProcess_NLBL0110Scrn00_CMN_Reset((NLBL0110CMsg) cMsg, (NLBL0110SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: It is a method of the execution when the initial display event is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0110_INIT(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        init(cMsg, sMsg);

    }

    /**
     * The method explanation: It is a method of the execution when the event[Search] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0110Scrn00_Search(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        cMsg.xxRadioBtn.clear();
        cMsg.whCd_D1.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);

        NLBL0110CommonLogic.clearSMsg(sMsg);

        EZDMsg.copy(cMsg, null, sMsg, null);

        // 2013/05/24 R-OM025 Inventory Transaction Add Start
        // 10/06/2015 Remove start
        /*
        if (!checkForSearch(cMsg, sMsg)) {
            return;
        }
        */
        // 10/06/2015 Remove end
        // 2013/05/24 R-OM025 Inventory Transaction Add End
        search(cMsg, sMsg);

        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NLBM0010I);
        } else {
            cMsg.setMessageInfo(ZZM8002I);
        }
    }

    /**
     * The method explanation: It is a method of the execution when the event[View] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0110Scrn00_View(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        int selectedRow = cMsg.xxRadioBtn.getValue().intValue();

        String whCd = cMsg.A.no(selectedRow).whCd_A1.getValue();
        int bizDays = cMsg.A.no(selectedRow).endMthBizDaysAot_A1.getValueInt();

        String saleDate = ZYPDateUtil.getSalesDate();
        String strYearMonth = saleDate.substring(0, 6);
        String strYear = saleDate.substring(0, 4);
        String strMonth = saleDate.substring(4, 6);

        cMsg.B.setValidCount(DISP_MONTH);

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {

            cMsg.B.no(i).xxYrMth_B1.setValue(strYearMonth);

            String fromDate = "";
            String toDate = "";
            if (bizDays > 0) {

                // Get W/H calender code
                String whCalCd = getCalCd(sMsg.glblCmpyCd.getValue(), CAL_SUB_TP.WAREHOUSE_CALENDAR, whCd, strYearMonth);
                if (whCalCd == null) {
                    // 10/07/2015 mod start
                    // cMsg.setMessageInfo(NLBM0024E);
                    cMsg.setMessageInfo(NLBM0024E, new String[] {"View"});
                    // 10/07/2015 mod end
                    return;
                }

                fromDate = getStartDate(sMsg.glblCmpyCd.getValue(), whCalCd, strYear, strMonth, bizDays);
                if (!ZYPCommonFunc.hasValue(fromDate)) {
                    ZYPTableUtil.clear(cMsg.B);
                    // 10/07/2015 mod start
                    // cMsg.setMessageInfo(NLBM0024E);
                    cMsg.setMessageInfo(NLBM0024E, new String[] {"View"});
                    // 10/07/2015 mod end
                    return;
                }

                toDate = getEndDate(strYear, strMonth);
                if (!ZYPCommonFunc.hasValue(toDate)) {
                    ZYPTableUtil.clear(cMsg.B);
                    // 10/07/2015 mod start
                    // cMsg.setMessageInfo(NLBM0024E);
                    cMsg.setMessageInfo(NLBM0024E, new String[] {"View"});
                    // 10/07/2015 mod end
                    return;
                }
            }

            cMsg.B.no(i).xxFromDt_B1.setValue(fromDate);
            cMsg.B.no(i).xxToDt_B1.setValue(toDate);

            strYearMonth = addYearMonth(strYear, strMonth);
            strYear = strYearMonth.substring(0, 4);
            strMonth = strYearMonth.substring(4, 6);
        }
        cMsg.whCd_D1.setValue(whCd);
    }

    /**
     * The method explanation: It is a method of the execution when the event[CMN_Submit] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0110Scrn00_CMN_Submit(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        cMsg.whCd_H2.setValue(sMsg.whCd_H2.getValue());

        EZDMsg.copy(sMsg, null, cMsg, null);

        search(cMsg, sMsg);

        cMsg.setMessageInfo(NLBM0006I);

    }

    /**
     * The method explanation: It is a method of the execution when the event[CMN_Reset] is generated. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0110Scrn00_CMN_Reset(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        init(cMsg, sMsg);

    }

    /**
     * Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void init(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        NLBL0110CommonLogic.clearCMsg(cMsg);
        NLBL0110CommonLogic.clearSMsg(sMsg);
        sMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // 2013/05/22 R-OM025 Inventory Transaction Delete End
//        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
//        S21DataSecurityProfile dataSecurityProfile = profileService.getDataSecurityProfileFor(cMsg.getBusinessID());
//        List<S21DataSecurityAttributeData> dataSecurityAttrList = dataSecurityProfile.getDataSecurityAttributeDataListFor(
//                                                                                        S21DataSecurityAttributeData.NAME_WAREHOUSE);

//
//        if (dataSecurityAttrList != null && !dataSecurityAttrList.isEmpty()) {
//
//            boolean whAllFlg = false;
//
//            for (int i = 0; i < dataSecurityAttrList.size(); i++) {
//
//                if (WH_ALL_VALUE.equals(dataSecurityAttrList.get(i).getValue())) {
//
//                    whAllFlg = true;
//                    break;
//                }
//            }
//
//            if (whAllFlg) {
//                sMsg.whCd_H2.setValue(WH_ALL_VALUE);
//            }
//
//            // =============================================
//            // Making of WH pull-down list
//            // =============================================
//            S21SsmEZDResult ssmResultForGetWHList = NLBL0110Query.getInstance().getWHList(sMsg, dataSecurityAttrList);
//
//            if (ssmResultForGetWHList.isCodeNormal()) {
//
//                List<Map> whList = (List<Map>) ssmResultForGetWHList.getResultObject();
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
//                        sMsg.xxEdtCdNm_H1.no(i + 1).setValue(ZYPCommonFunc.concatString((String) whData.get(WH_CD),
//                                                                                        LIST_BOX_DELIMITER,
//                                                                                        (String) whData.get(LOC_NM)));
//
//                    } else {
//
//                        break;
//                    }
//                }
//            }
//
//            EZDMsg.copy(sMsg, null, cMsg, null);
//        }
        // 2013/05/22 R-OM025 Inventory Transaction Delete End

        // 2013/05/22 R-OM025 Inventory Transaction Add Start
        // Set Location Role Type Code List
        ZYPEZDItemValueSetter.setValue(cMsg.xxLocRoleTpCdListTxt_LI,
                NLBL0110CommonLogic.getLocRoleTpForPopup(getGlobalCompanyCode()));
        // 2013/05/22 R-OM025 Inventory Transaction Add End

    }

    /**
     * check for search button
     * @param cMsg NLBL0110CMsg
     * @param sMsg NLBL0110SMsg
     * @return true if success (no error set)
     */
    private boolean checkForSearch(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21DataSecurityProfile dataSecurityProfile = profileService.getDataSecurityProfileFor(cMsg.getBusinessID());
        List<S21DataSecurityAttributeData> dataSecurityAttrList = dataSecurityProfile.getDataSecurityAttributeDataListFor(
                                                                                      S21DataSecurityAttributeData.NAME_WAREHOUSE);

        if (dataSecurityAttrList != null && !dataSecurityAttrList.isEmpty()) {
            if (ZYPCommonFunc.hasValue(cMsg.whCd_H2)) {
                NMXC100001EnableWHData locInfo = NMXC100001EnableWH.checkEnableWH(
                        getGlobalCompanyCode(),
                        cMsg.whCd_H2.getValue(),
                        BUSINESS_ID,
                        dataSecurityAttrList,
                        ZYPConstant.FLG_OFF_N);
                if (ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
                    cMsg.setMessageInfo(locInfo.getXxMsgId());
                    return false;
                }
            }
        } else {
            cMsg.setMessageInfo("NMXM0004E");
            return false;
        }

        return true;
    }

    /**
     * Search
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void search(NLBL0110CMsg cMsg, NLBL0110SMsg sMsg) {

        sMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        // 2013/05/24 R-OM025 Inventory Transaction Mod Start
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21DataSecurityProfile dataSecurityProfile = profileService.getDataSecurityProfileFor(cMsg.getBusinessID());
        List<S21DataSecurityAttributeData> dataSecurityAttrList = dataSecurityProfile.getDataSecurityAttributeDataListFor(
                                                                                      S21DataSecurityAttributeData.NAME_WAREHOUSE);

        boolean allCodeAvalable = false;
        for (S21DataSecurityAttributeData dataSecurityAttr : dataSecurityAttrList) {
            if (WH_ALL_VALUE.equals(dataSecurityAttr.getValue())) {
                allCodeAvalable = true;
            }
        }

        // do not filtering warehouse list if the code "*" is available
        if (allCodeAvalable) {
            dataSecurityAttrList = new ArrayList<S21DataSecurityAttributeData>();
        }
//        S21SsmEZDResult ssmResult = NLBL0110Query.getInstance().getWHBizDaysOfEndOfMonth(sMsg);
        S21SsmEZDResult ssmResult = NLBL0110Query.getInstance().getWHBizDaysOfEndOfMonth(sMsg, dataSecurityAttrList);
        // 2013/05/24 R-OM025 Inventory Transaction Mod End


        if (ssmResult.isCodeNormal()) {

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

        }
    }

    /**
     * Get warehouse calender code
     * @param glblCmpyCd String
     * @param calSubTpCd CAL_SUB_TP_CD
     * @param calMultCd CAL_MULT_CD
     * @param date String
     * @return String
     */
    private String getCalCd(String glblCmpyCd, String calSubTpCd, String calMultCd, String date) {

        S21SsmEZDResult ssmResult = NLBL0110Query.getInstance().getCalTpCd(glblCmpyCd, calSubTpCd, calMultCd, date);
        String calTpCd = (String) ssmResult.getResultObject();

        if (ZYPCommonFunc.hasValue(calTpCd)) {
            return calTpCd;
        } else {
            return getDefaultCalCd(glblCmpyCd, date);
        }
    }

    /**
     * Get default calender code
     * @param glblCmpyCd String
     * @param date String 
     * @return String
     */
    private String getDefaultCalCd(String glblCmpyCd, String date) {

        S21SsmEZDResult ssmResult = NLBL0110Query.getInstance().getCalTpCd(glblCmpyCd, CAL_SUB_TP.COMPANY_CALENDAR, glblCmpyCd, date);
        String calTpCd = (String) ssmResult.getResultObject();

        if (ZYPCommonFunc.hasValue(calTpCd)) {
            return calTpCd;
        } else {
            return null;
        }
    }

    /**
     * Add 1 month with parametar(yyyy, mm)
     * @param strYear String
     * @param strMonth String
     * @return String
     */
    private String addYearMonth(String strYear, String strMonth) {

        int month = Integer.parseInt(strMonth) + 1;
        if (month > 12) {
            month = 1;
            int year = Integer.parseInt(strYear) + 1;
            strYear = ZYPCommonFunc.leftPad(Integer.toString(year), 4, PADDING_ZERO);
        }
        strMonth = ZYPCommonFunc.leftPad(Integer.toString(month), 2, PADDING_ZERO);

        return strYear.concat(strMonth);
    }

    /**
     * Get end date of end of month
     * @param strYear String
     * @param strMonth String
     * @return String
     */
    private String getEndDate(String strYear, String strMonth) {

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(strYear), Integer.parseInt(strMonth) - 1, 1);
        int day = cal.getActualMaximum(Calendar.DATE);

        String strDay = Integer.toString(day);
        strDay = ZYPCommonFunc.leftPad(strDay, 2, PADDING_ZERO);

        return ZYPCommonFunc.concatString(strYear, strMonth, strDay);
    }

    /**
     * Get start date of end of month
     * @param glblCmpyCd String
     * @param whCalCd String
     * @param year String
     * @param month String
     * @param bizDays int
     * @return String
     */
    private String getStartDate(String glblCmpyCd, String whCalCd, String year, String month, int bizDays) {

        String startDate = "";

        String[] bizDate = ZYPDateUtil.getBusinessDaysEx(glblCmpyCd, whCalCd, year, month);

        if (bizDate != null) {
            if (bizDays > bizDate.length) {
                startDate = ZYPCommonFunc.concatString(year, month, FIRST_DAY);
            } else {
                startDate = bizDate[bizDate.length - bizDays];
            }
        }

        return startDate;
    }
}
