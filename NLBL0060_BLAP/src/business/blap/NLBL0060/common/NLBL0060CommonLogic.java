/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0060.common;

import java.util.List;

import parts.common.EZDMsg;
import business.blap.NLBL0060.NLBL0060CMsg;
import business.blap.NLBL0060.NLBL0060SMsg;
import business.blap.NLBL0060.constant.NLBL0060Constant;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
/**
 * <pre>
 * This class does Common business process of BusinessID NLBL0060.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   CUSA            Mizutani         Update          R-OM025 Inventory Transaction
 *</pre>
 */
public class NLBL0060CommonLogic implements NLBL0060Constant {

    /**
     * The method explanation: set search condition item value.
     * @param cMsg NLBL0060CMsg
     * @param sMsg NLBL0060SMsg
     */
    public static void setSearchConditionItemValue(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {
    	
    	cMsg.whCd_H2.setValue(sMsg.whCd_S1.getValue());
    	cMsg.effFromDt_H1.setValue(sMsg.effFromDt_S1.getValue());
    	cMsg.effThruDt_H1.setValue(sMsg.effThruDt_S1.getValue());
    }
    
    /**
     * The method explanation: copy current Table Data from cMsg to sMsg.
     * @param cMsg NLBL0060CMsg
     * @param sMsg NLBL0060SMsg
     */
    public static void copyCurrentTblDataFromCMsgToSMsg(NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

    	int newRowNumCurrentKey = 0;
    	String whCdCurrentKey = "";
    	String effFromCurrentKey = "";
    	
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        	
        	if (newRowNumCurrentKey == cMsg.A.no(i).xxNewRowNum_A9.getValueInt() &&
        			whCdCurrentKey.equals(cMsg.A.no(i).whCd_A9.getValue()) &&
        			effFromCurrentKey.equals(cMsg.A.no(i).effFromDt_A9.getValue())) {
        		
        		continue;
        		
        	}

        	newRowNumCurrentKey = cMsg.A.no(i).xxNewRowNum_A9.getValueInt();
	    	whCdCurrentKey = cMsg.A.no(i).whCd_A9.getValue();
	    	effFromCurrentKey = cMsg.A.no(i).effFromDt_A9.getValue();
        	
        	for (int j = 0; j < sMsg.A.getValidCount(); j++) {
	    		
        	//Mizutani	if (!WH_ALL_CD_VALUE.equals(sMsg.whCd_S1.getValue())) {
        		 if (ZYPCommonFunc.hasValue(sMsg.whCd_S1)) {
        		  // 2013/05/21 R-OM025 Inventory Transaction Modify Start
        			sMsg.A.no(j).whCd_A1.setValue(cMsg.A.no(0).whCd_A1.getValue());
                    //sMsg.A.no(j).whCd_A1.setValue(cMsg.A.no(0).whCd_A2.getValue());
        			// 2013/05/21 R-OM025 Inventory Transaction Modify End
        		}
        		
	    		if (newRowNumCurrentKey == sMsg.A.no(j).xxNewRowNum_A9.getValueInt() &&
	    				whCdCurrentKey.equals(sMsg.A.no(j).whCd_A9.getValue()) &&
	    				effFromCurrentKey.equals(sMsg.A.no(j).effFromDt_A9.getValue())) {
	    			
	    			// Mizutaniif (WH_ALL_CD_VALUE.equals(sMsg.whCd_S1.getValue())) {
	                    if (!ZYPCommonFunc.hasValue(sMsg.whCd_S1)) {
	                     // 2013/05/21 R-OM025 Inventory Transaction Modify Start
	    				sMsg.A.no(j).whCd_A1.setValue(cMsg.A.no(i).whCd_A1.getValue());	
                        //sMsg.A.no(j).whCd_A1.setValue(cMsg.A.no(i).whCd_A2.getValue()); 
	    				// 2013/05/21 R-OM025 Inventory Transaction Modify End
	    			}
	    			
	    			sMsg.A.no(j).effFromDt_A1.setValue(cMsg.A.no(i).effFromDt_A1.getValue());
					sMsg.A.no(j).effThruDt_A1.setValue(cMsg.A.no(i).effThruDt_A1.getValue());
	    		}
	    	}
        }
        
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
        	
        	for (int j = 0; j < sMsg.A.getValidCount(); j++) {
	    		
	    		if (cMsg.A.no(i).xxNewRowNum_A9.getValueInt() == sMsg.A.no(j).xxNewRowNum_A9.getValueInt() &&
	    				cMsg.A.no(i).whCd_A9.getValue().equals(sMsg.A.no(j).whCd_A9.getValue()) &&
	    				cMsg.A.no(i).effFromDt_A9.getValue().equals(sMsg.A.no(j).effFromDt_A9.getValue()) &&
	    				cMsg.A.no(i).shpgModeCd_A9.getValue().equals(sMsg.A.no(j).shpgModeCd_A9.getValue()) &&
	    				cMsg.A.no(i).shpgSvcLvlCd_A9.getValue().equals(sMsg.A.no(j).shpgSvcLvlCd_A9.getValue())) {
	    			
	    			sMsg.A.no(j).shpgCloTmTs_A1.setValue(cMsg.A.no(i).shpgCloTmTs_A1.getValue());
					sMsg.A.no(j).xxDplyLeadTmDaysAot_A1.setValue(cMsg.A.no(i).xxDplyLeadTmDaysAot_A1.getValueInt());
	    		}
	    	}
        }
    }
    
    /**
     * The method explanation: clear cMsg.
     * @param cMsg Business Component Interface Message
     */
    public static void clearCMsg(NLBL0060CMsg cMsg) {
    	cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
    }
    
    /**
     * The method explanation: clear sMsg. 
     * @param sMsg Global area information
     */
    public static void clearSMsg(NLBL0060SMsg sMsg) {
    	sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.X);
        ZYPTableUtil.clear(sMsg.Y);
        ZYPTableUtil.clear(sMsg.T);
    }
    
    /**
     * The method explanation: copy error page data in table from sMsg to cMsg.
     * @param errorRowNum int
     * @param cMsg NLBL0060CMsg
     * @param sMsg NLBL0060SMsg
     */
    public static void copyErrorPageDataFromSMsgToCMsg(int errorRowNum, NLBL0060CMsg cMsg, NLBL0060SMsg sMsg) {

    	int errorPageNum = errorRowNum / cMsg.A.length();
    	int pagenationFrom = cMsg.A.length() * errorPageNum + 1;
        int validCount = 0;
        
        for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.length(); i++) {
            
            EZDMsg.copy(sMsg.A.no(i-1), null, cMsg.A.no(i - pagenationFrom), null);

            validCount++;
            
			if (i == sMsg.A.getValidCount()) {
				break;
			} 
        }

    	// setting pagenation data
        cMsg.A.setValidCount(validCount);
        cMsg.xxPageShowFromNum_A1.setValue(pagenationFrom);
        cMsg.xxPageShowToNum_A1.setValue(pagenationFrom + validCount -1);
        cMsg.xxPageShowOfNum_A1.setValue(sMsg.A.getValidCount());
    }

    // 2013/05/21 R-OM025 Inventory Transaction Add Start
    /**
     * Get Location Name
     * @param  bizMsg NLBL0060CMsg
     * @param  glblCmpyCd String
     * @param  dataSecurityList Data Security List
     * @return Location Name
     */
    public static String getInvtyLocNm(NLBL0060CMsg bizMsg, List<S21DataSecurityAttributeData> dataSecurityList, String glblCmpyCd) {
        NMXC100001EnableWHData locInfo = getInvtyLocInfo(bizMsg, dataSecurityList, glblCmpyCd);
        if (locInfo == null || ZYPCommonFunc.hasValue(locInfo.getXxMsgId())) {
            return null;
        }
        return locInfo.getInvtyLocNm();
    }

    /**
     * Get Location Information
     * @param  bizMsg NLBL0060CMsg
     * @param  glblCmpyCd String
     * @param  dataSecurityList Data Security List
     * @return Location Information Bean
     */
    public static NMXC100001EnableWHData getInvtyLocInfo(NLBL0060CMsg bizMsg, List<S21DataSecurityAttributeData> dataSecurityList, String glblCmpyCd) {
        return getInvtyLocInfoForDetail(bizMsg.whCd_H2.getValue(), dataSecurityList, glblCmpyCd);
    }

    /**
     * Get Location Information
     * @param  whCd String
     * @param  glblCmpyCd String
     * @param  dataSecurityList Data Security List
     * @return Location Information Bean
     */
    public static NMXC100001EnableWHData getInvtyLocInfoForDetail(String whCd, List<S21DataSecurityAttributeData> dataSecurityList, String glblCmpyCd) {
        return NMXC100001EnableWH.checkEnableWH(glblCmpyCd,
                                                    whCd,
                                                    NLBL0060Constant.BUSINESS_ID,
                                                    dataSecurityList,
                                                    ZYPConstant.FLG_OFF_N);
    }

    /**
     * Get Location Role Type(COMMA Format)
     * @param glblCmpyCd Global Company Code
     * @return Location Role Type Code List
     */
    public static String getLocRoleTpForPopup(String glblCmpyCd) {
        return NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, NLBL0060Constant.BUSINESS_ID);
    }
    // 2013/05/21 R-OM025 Inventory Transaction Add End

}
