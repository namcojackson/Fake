/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/11/25   Fujitsu         H.Ogawa         Update          WDS Defect#2852
 *</pre>
 */
package business.blap.NMAL4690.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL4690.NMAL4690CMsg;
import business.blap.NMAL4690.NMAL4690Query;
import business.blap.NMAL4690.NMAL4690SMsg;
import business.blap.NMAL4690.constant.NMAL4690Constant;
import business.db.MSTR_ACTV_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL4690CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NMAL4690CommonLogic implements NMAL4690Constant {

    /** S21UserProfileService profileService */
    private static S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();

    /** String globalCompanyCode */
    private String globalCompanyCode = profileService.getGlobalCompanyCode();

    /**
     * Method name: getGlobalCompanyCode
     * <dd>The method explanation:
     * <dd>Remarks:
     * @return String globalCompanyCode
     */
    public String getGlobalCompanyCode() {
        return globalCompanyCode;
    }

    /**
     * Method name: getRecord
     * <dd>The method explanation:
     * <dd>Remarks:
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    public void getRecord(EZDCMsg cMsg, EZDSMsg sMsg) {

        NMAL4690CMsg bizMsg = (NMAL4690CMsg) cMsg;
        NMAL4690SMsg globalMsg = (NMAL4690SMsg) sMsg;

        int size = 0;
        bizMsg.A.clear();
        bizMsg.A.setValidCount(size);

        S21SsmEZDResult ssmResult = NMAL4690Query.getInstance().getRecord(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            size = resultList.size();
            // has a result
            // in case of exceesing maximum limit of record
            int queryResCnt = ssmResult.getQueryResultCount();
            globalMsg.A.setValidCount(queryResCnt);

            if (queryResCnt > globalMsg.A.length()) {
                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = globalMsg.A.length();
            }
            
            // find master att data activity
            MSTR_ACTV_TPTMsgArray tMsgArray = (MSTR_ACTV_TPTMsgArray)ZYPCodeDataUtil.findAll("MSTR_ACTV_TP");
            
            // Set Data to globalMsg
            for (int i = 0; i < size; i++) {
                Map map = (Map) resultList.get(i);

                String ezUpTime = checkNull((String) map.get("EZUPTIME"));
                BigDecimal attDataPk = checkNull((BigDecimal) map.get("MSTR_ATT_DATA_PK"));
                String ezBusinessID = checkNull((String) map.get("MSTR_BIZ_ID"));
                String mstrActvCd = checkNull((String) map.get("MSTR_ACTV_CD"));
                String attDataNm = checkNull((String) map.get("MSTR_ATT_DATA_NM"));
                BigDecimal attDataVol = checkNull((BigDecimal) map.get("MSTR_ATT_DATA_VOL"));
                String attDataDescTxt = checkNull((String) map.get("MSTR_ATT_DATA_DESC_TXT"));

                String uploadedProc = BLANK;
                if (ezBusinessID.equals(NMAL4750)) {
                    uploadedProc = "Chng Loc";
                } else if (ezBusinessID.equals(NMAL4000)) {
                    uploadedProc = "New Cust/Loc";
                }

                String dataTime = BLANK;
                if (ezUpTime.length() == 17 && isNumber(ezUpTime)) {
                    // Mod Start 2013/11/25 WDS Defect#2852
//                    String year = ezUpTime.substring(0, 4);
//                    String month = new DecimalFormat("00").format(Double.parseDouble(ezUpTime.substring(4, 6)));
//                    String date = new DecimalFormat("00").format(Double.parseDouble(ezUpTime.substring(6, 8)));
//                    String hour = new DecimalFormat("00").format(Double.parseDouble(ezUpTime.substring(8, 10)));
//                    String min = new DecimalFormat("00").format(Double.parseDouble(ezUpTime.substring(10, 12)));
//                    String sec = new DecimalFormat("00").format(Double.parseDouble(ezUpTime.substring(12, 14)));
//
//                    dataTime = year + "/" + month + "/" + date + " " + hour + ":" + min + ":" + sec;

                    String yyyymmdd = ezUpTime.substring(0, 8);
                    String hour = new DecimalFormat("00").format(Double.parseDouble(ezUpTime.substring(8, 10)));
                    String min = new DecimalFormat("00").format(Double.parseDouble(ezUpTime.substring(10, 12)));
                    String sec = new DecimalFormat("00").format(Double.parseDouble(ezUpTime.substring(12, 14)));

                    dataTime = ZYPDateUtil.formatEzd8ToDisp(yyyymmdd) + " " + hour + ":" + min + ":" + sec;
                    // Mod End 2013/11/25 WDS Defect#2852
                }

                globalMsg.A.no(i).mstrAttDataNm_A.setValue(attDataNm);
                globalMsg.A.no(i).xxCondNm_A.setValue(uploadedProc);
                globalMsg.A.no(i).xxAllPtyAddr_A.setValue(dataTime);
                globalMsg.A.no(i).ezUpTime_A.setValue(ezUpTime);
                globalMsg.A.no(i).mstrAttDataPk_A.setValue(attDataPk);
                globalMsg.A.no(i).mstrBizId_A.setValue(ezBusinessID);
                
                if (ZYPCommonFunc.hasValue(mstrActvCd)) {

                	for (int j=0,len=tMsgArray.length();j<len;j++) {
                		if (tMsgArray.no(j).mstrActvCd.getValue().equals(mstrActvCd)) {
                        	ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).mstrActvNm_A,tMsgArray.no(j).mstrActvNm);            			
                		}
                	}
                }
                
                globalMsg.A.no(i).mstrAttDataVol_A.setValue(attDataVol);
                globalMsg.A.no(i).mstrAttDataDescTxt_A.setValue(attDataDescTxt);
            }

            // Set Data to the first page
            int firstPg = 0;
            for (; firstPg < size; firstPg++) {
                if (firstPg == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(globalMsg.A.no(firstPg), null, bizMsg.A.no(firstPg), null);
            }
            bizMsg.A.setValidCount(firstPg);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            // has no result
            bizMsg.setMessageInfo("ZZSM0001E", new String[] {"No Attachment Found" });
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
        }
    }

    private boolean isNumber(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isDigit(char c) {
        if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9') {
            return false;
        } else {
            return true;
        }
    }

    private String checkNull(String input) {
        if (input == null) {
            return BLANK;
        } else {
            return input.trim();
        }
    }

    private BigDecimal checkNull(BigDecimal input) {

        BigDecimal num = new BigDecimal("0");
        if (input == null) {
            return num;
        } else {
            return input;
        }
    }
}
