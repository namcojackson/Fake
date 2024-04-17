package business.blap.NLBL0040.common;

import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NLBL0040.NLBL0040CMsg;
import business.blap.NLBL0040.NLBL0040Query;
import business.blap.NLBL0040.NLBL0040SMsg;
import business.blap.NLBL0040.constant.NLBL0040Constant;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityProfile;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * It is common processing in the business application program of BusinessID NLBL0040. 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/28   Fujitsu         D.Fukaya         Create          N/A
 * 2013/05/13   Fujitsu         H.Mizutani       Update          R-OM025 Inventory Transaction
 * 2013/10/29   Fujitsu         T.Yoshida        Update          DefID:2852
 * 2013/11/20   Fujitsu         M.Yamada         Update          DefID:2852
 *</pre>
 */
public class NLBL0040CommonLogic implements NLBL0040Constant {

	/**
     * The method explanation: set value of effective period pulldown list.
     * @param cMsg Business Component Interface Message
     */
    public static void setEffPerList(NLBL0040CMsg cMsg) {

        cMsg.effFromDt_H1.clear();
        cMsg.xxEdtCdNm_H2.clear();

        S21SsmEZDResult ssmResultForGetEffFromToList = NLBL0040Query.getInstance().getEffFromToList(cMsg);

        if (ssmResultForGetEffFromToList.isCodeNormal()) {
            List<Map> effFromToList = (List<Map>) ssmResultForGetEffFromToList.getResultObject();
            String salesDate = ZYPDateUtil.getSalesDate();

            String effFromDate;
            String effThruDate;
            String effFromDateConverted;
            String effThruDateConverted;
            
            for (int i = 0; i < effFromToList.size(); i++) {

                Map effFromThruData = effFromToList.get(i);

                effFromDate = (String)effFromThruData.get(EFF_FROM_DT);
                effThruDate = (String)effFromThruData.get(EFF_THRU_DT);

                effFromDateConverted = ZYPDateUtil.formatEzd8ToDisp(effFromDate);
                effThruDateConverted = ZYPDateUtil.formatEzd8ToDisp(effThruDate);
                // effFromDateConverted = ZYPDateUtil.convertFormat(effFromDate, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, ZYPDateUtil.SEPARATOR_SLASH);
                // effThruDateConverted = ZYPDateUtil.convertFormat(effThruDate, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYYYY, ZYPDateUtil.SEPARATOR_SLASH);

                // set selection value of pull-down list. An initial value is currently effective data. 
                if (ZYPDateUtil.compare(salesDate, effFromDate) == 0 ||
                        ZYPDateUtil.compare(salesDate, effThruDate) == 0 ||
                        (ZYPDateUtil.compare(salesDate, effFromDate) == 1 && ZYPDateUtil.compare(salesDate, effThruDate) == -1)) {

                    cMsg.effFromDt_H2.setValue(effFromDate);
                }
                
                // set Code and Display Name of pull-down list
                if (i < cMsg.effFromDt_H1.length()) {

                    cMsg.effFromDt_H1.no(i).setValue(effFromDate);
                    cMsg.xxEdtCdNm_H2.no(i).setValue(ZYPCommonFunc.concatString(effFromDateConverted, EFF_FROM_TO_LIST_BOX_DELIMITER, effThruDateConverted));

                } else {
                    // When the number of cases of the data set to the pull-down list exceeds the maximum display number of pull-downs
                    break;
                }
            }
        } else {
        	// There is no processing.
        }
	}
	
	/**
     * The method explanation: get value of effective period pulldown list, and set to sMsg.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
	public static void setEffPerListFromCMsgToSMsg(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        sMsg.effFromDt_H1.clear();
        sMsg.xxEdtCdNm_H2.clear();

        for (int i = 0; i < cMsg.effFromDt_H1.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.effFromDt_H1.no(i))) {
                break;
            }

            sMsg.effFromDt_H1.no(i).setValue(cMsg.effFromDt_H1.no(i).getValue());
            sMsg.xxEdtCdNm_H2.no(i).setValue(cMsg.xxEdtCdNm_H2.no(i).getValue());
        }
    }

	/**
     * The method explanation: The init processing is executed. 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void init(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21DataSecurityProfile dataSecurityProfile = profileService.getDataSecurityProfileFor(cMsg.getBusinessID());
        List<S21DataSecurityAttributeData> dataSecurityAttrList = dataSecurityProfile.getDataSecurityAttributeDataListFor(S21DataSecurityAttributeData.NAME_WAREHOUSE);
        
        if (dataSecurityAttrList == null || dataSecurityAttrList.isEmpty()) {

            // There is no processing.
        	
        } else {

            boolean whAllFlg = false;
            for (int i = 0; i < dataSecurityAttrList.size(); i++) {

                if (WH_ALL_VALUE.equals(dataSecurityAttrList.get(i).getValue())) {

                    whAllFlg = true;
                    break;
                }
            }
            if (whAllFlg) {

                cMsg.whCd_G1.setValue(WH_ALL_VALUE);
                sMsg.whCd_G1.setValue(WH_ALL_VALUE);
            }

            // =============================================
            // Making of WH pull-down list
            // =============================================

         // 2013/05/13 R-OM025 Inventory Transaction Delete Start
//            S21SsmEZDResult ssmResultForGetWHList = NLBL0040Query.getInstance().getWHList(cMsg, dataSecurityAttrList);
//            if (ssmResultForGetWHList.isCodeNormal()) {
//                List<Map> whList = (List<Map>) ssmResultForGetWHList.getResultObject();
//
//                for (int i = 0; i < whList.size(); i++) {
//
//                    Map whData = whList.get(i);
//
//                    if (i < cMsg.whCd_H1.length()) {
//                        cMsg.whCd_H1.no(i).setValue((String) whData.get(WH_CD));
//                        cMsg.xxEdtCdNm_H1.no(i).setValue(ZYPCommonFunc.concatString((String)whData.get(WH_CD), LIST_BOX_DELIMITER, (String)whData.get(LOC_NM)));
//                    } else {
//                        // When the number of cases of the data set to the pull-down list exceeds the maximum display number of pull-downs
//                        break;
//                    }
//                }
//                
//                // Setting of initial value
//                cMsg.whCd_H2.setValue(cMsg.whCd_H1.no(0).getValue());
//                
//            } else {
//            	
//                // There is no processing. 
//            }        	
// 2013/05/13 R-OM025 Inventory Transaction Delete End
        }

        // =============================================
        // Making of Effective Period pull-down list
        // =============================================
        NLBL0040CommonLogic.setEffPerList(cMsg);

        // =============================================
        // Making of State pull-down list
        // =============================================        
        S21SsmEZDResult ssmResultForGetStList = NLBL0040Query.getInstance().getStList(cMsg);

        cMsg.stCd_H1.no(0).setValue(ST_LIST_ALL_VALUE);
        // #2852
        EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        String i18nString = converter.convLabel2i18nLabel("", ST_LIST_ALL_DISPLAY_VALUE);
        cMsg.stNm_H1.no(0).setValue(i18nString);
        
        if (ssmResultForGetStList.isCodeNormal()) {
        	
            List<Map> stList = (List<Map>) ssmResultForGetStList.getResultObject();

            for (int i = 0; i < stList.size(); i++) {

                Map stData = stList.get(i);

                if (i+1 < cMsg.stCd_H1.length()) {

                    cMsg.stCd_H1.no(i+1).setValue((String) stData.get(ST_CD));
                    cMsg.stNm_H1.no(i+1).setValue((String) stData.get(ST_NM));

                } else {

                    // When the number of cases of the data set to the pull-down list exceeds the maximum display number of pull-downs
                    break;
                }
            }

        } else {

            // There is no processing. 
        }
    }

    /**
     * The method explanation: copy AREA_LEAD_TIME Table Data from cMsg to sMsg.
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     */
    public static void copyAreaLeadTimeTblDataFromCMsgToSMsg(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
        int pagenationTo = cMsg.xxPageShowToNum_A1.getValueInt();

        for (int i = pagenationFrom; i <= pagenationTo; i++) {

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i - 1).delyLeadAot_A1, cMsg.A.no(i - pagenationFrom).delyLeadAot_A1.getValue());
        }
    }

    /**
     * The method explanation: copy TRNSP_LT Table Data from cMsg to sMsg.
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     */
    public static void copyTrnspLtTblDataFromCMsgToSMsg(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        if (sMsg.B.getValidCount() > 0) {

            int pagenationFrom = cMsg.xxPageShowFromNum_B1.getValueInt();
            int pagenationTo = cMsg.xxPageShowToNum_B1.getValueInt();

            for (int i = pagenationFrom; i <= pagenationTo; i++) {

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i - 1).xxChkBox_B1, cMsg.B.no(i - pagenationFrom).xxChkBox_B1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i - 1).fromZipCd_B1, cMsg.B.no(i - pagenationFrom).fromZipCd_B1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i - 1).toZipCd_B1, cMsg.B.no(i - pagenationFrom).toZipCd_B1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i - 1).trnspLtAot_B1, cMsg.B.no(i - pagenationFrom).trnspLtAot_B1.getValue());
            }
        }
    }

    /**
     * The method explanation: copy error page data of AREA_LEAD_TM Table from sMsg to cMsg.
     * @param errorRowNum int
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     */
    public static void copyErrorPageDataOfAreaLeadTmTblFromSMsgToCMsg(int errorRowNum, NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        int errorPageNum = errorRowNum / cMsg.A.length();
        int pagenationFrom = cMsg.A.length() * errorPageNum + 1;
        int validCount = 0;

        for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.length(); i++) {

            EZDMsg.copy(sMsg.A.no(i - 1), null, cMsg.A.no(i - pagenationFrom), null);

            validCount++;

            if (i == sMsg.A.getValidCount()) {
                break;
            }
        }

        cMsg.A.setValidCount(validCount);
        cMsg.xxPageShowFromNum_A1.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_A1.setValue(pagenationFrom + validCount - 1);
        cMsg.xxPageShowOfNum_A1.setValue(sMsg.A.getValidCount());
    }

    /**
     * The method explanation: copy error page data of TRNSP_LT Table from sMsg to cMsg.
     * @param errorRowNum int
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     */
    public static void copyErrorPageDataOfTrnspLtTblFromSMsgToCMsg(int errorRowNum, NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        int errorPageNum = errorRowNum / cMsg.B.length();
        int pagenationFrom = cMsg.B.length() * errorPageNum + 1;
        int validCount = 0;

        for (int i = pagenationFrom; i < pagenationFrom + cMsg.B.length(); i++) {

            EZDMsg.copy(sMsg.B.no(i - 1), null, cMsg.B.no(i - pagenationFrom), null);

            validCount++;

            if (i == sMsg.B.getValidCount()) {
                break;
            }
        }

        cMsg.B.setValidCount(validCount);
        cMsg.xxPageShowFromNum_B1.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_B1.setValue(pagenationFrom + validCount -1);
        cMsg.xxPageShowOfNum_B1.setValue(sMsg.B.getValidCount());
    }
    
    /**
     * The method explanation: set search condition item value.
     * @param cMsg NLBL0040CMsg
     * @param sMsg NLBL0040SMsg
     */
    public static void setSearchConditionItemValue(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        NLBL0040CommonLogic.setEffPerListFromSMsgToCMsg(cMsg, sMsg);

        cMsg.whCd_H2.setValue(sMsg.whCd_G1.getValue());
        cMsg.stCd_H2.setValue(sMsg.stCd_G1.getValue());
        cMsg.effFromDt_H2.setValue(sMsg.effFromDt_G1.getValue());
    }

    /**
     * The method explanation: get value of effective period pulldown
     * list, and set to cMsg.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private static void setEffPerListFromSMsgToCMsg(NLBL0040CMsg cMsg, NLBL0040SMsg sMsg) {

        cMsg.effFromDt_H1.clear();
        cMsg.xxEdtCdNm_H2.clear();

        for (int i = 0; i < sMsg.effFromDt_H1.length(); i++) {

            if (!ZYPCommonFunc.hasValue(sMsg.effFromDt_H1.no(i))) {
                break;
            }

            cMsg.effFromDt_H1.no(i).setValue(sMsg.effFromDt_H1.no(i).getValue());
            cMsg.xxEdtCdNm_H2.no(i).setValue(sMsg.xxEdtCdNm_H2.no(i).getValue());
        }
    }

    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    /**
     * Get Location Name
     * @param  bizMsg NLBL0040CMsg
     * @param  dataSecurityList Data Security List
     * @return Location Name
     */
    public static String getInvtyLocNm(NLBL0040CMsg bizMsg, List<S21DataSecurityAttributeData> dataSecurityList,String glblCmpyCd) {
        NMXC100001EnableWHData locInfo = getInvtyLocInfo(bizMsg, dataSecurityList,glblCmpyCd);
        if (locInfo == null || ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
            return null;
        }
        return locInfo.getInvtyLocNm();
    }

    /**
     * Get Location Information
     * @param  bizMsg NLBL0040CMsg
     * @param  dataSecurityList Data Security List
     * @return Location Information Bean
     */
    public static NMXC100001EnableWHData getInvtyLocInfo(NLBL0040CMsg bizMsg, List<S21DataSecurityAttributeData> dataSecurityList,String glblCmpyCd) {
        return NMXC100001EnableWH.checkEnableWH(glblCmpyCd,
                                                    bizMsg.whCd_H2.getValue(),
                                                    NLBL0040Constant.BUSINESS_ID,
                                                    dataSecurityList,
                                                    ZYPConstant.FLG_OFF_N);
    }

    /**
     * Get Location Role Type(COMMA Format)
     * @param glblCmpyCd Global Company Code
     * @return Location Role Type Code List
     */
    public static String getLocRoleTpForPopup(String glblCmpyCd) {
        return NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, NLBL0040Constant.BUSINESS_ID);
    }
    // 2013/05/21 R-OM025 Inventory Transaction Add End

}
