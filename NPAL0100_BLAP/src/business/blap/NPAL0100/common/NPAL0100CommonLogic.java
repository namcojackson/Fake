/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/10/14   Fujitsu         I.Kondo         Create          N/A
 * 2010/01/21   Fujitsu         H.Suganuma      Update          3131
 * 2009/02/09   Fujitsu         H.Suganuma      Update          3496
 * 2011/10/27   CSAI            N.Sasaki        Update          362045
 *</pre>
 */
package business.blap.NPAL0100.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL0100.NPAL0100CMsg;
import business.blap.NPAL0100.NPAL0100Query;
import business.blap.NPAL0100.NPAL0100SMsg;
import business.blap.NPAL0100.constant.NPAL0100Constant;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

public class NPAL0100CommonLogic implements NPAL0100Constant {

    /**
     * get bill to customer code.
     * @param bizMsg NPAL0100CMsg
     * @param globalCompanyCode global company code
     * @return bill to customer code
     */
    public static String getBillToCustCd(NPAL0100CMsg bizMsg, String globalCompanyCode) {

        BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID(SQL_ID_BILL_TO_CUST_CD);
        condition.setMaxCount(1);
        condition.setConditionValue(SQL_PARAM_BILL_TO_CUST.glblCmpyCd01.toString(), globalCompanyCode);
        condition.setConditionValue(SQL_PARAM_BILL_TO_CUST.billToCustCd01.toString(), bizMsg.billToCustCd.getValue());

        BILL_TO_CUSTTMsgArray resultArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (0 == resultArray.length()) {
            bizMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {MESSAGE_PARAM_BILL_TO_CUST_CD });
            bizMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return "";
        } else {
            return resultArray.no(0).locNm.getValue();
        }
    }

    /**
     * get mdse code
     * @param bizMsg NPAL0100CMsg
     * @param globalCompanyCode global company code
     * @return mdse code
     */
    public static String getMdseTpCd(NPAL0100CMsg bizMsg, String globalCompanyCode) {

        MDSETMsg mdseRecord = new MDSETMsg();
        mdseRecord.setSQLID(SQL_ID_MDSE_TP_CD);
        mdseRecord.setMaxCount(1);
        mdseRecord.setConditionValue(SQL_PARAM_MDSE_TP_CD.glblCmpyCd01.toString(), globalCompanyCode);
        mdseRecord.setConditionValue(SQL_PARAM_MDSE_TP_CD.mdseCd01.toString(), bizMsg.mdseCd.getValue());

        MDSETMsgArray mdseResultArray = (MDSETMsgArray) EZDTBLAccessor.findByCondition(mdseRecord);
        if (0 == mdseResultArray.length()) {
            bizMsg.setMessageInfo(MESSAGE_CD.NPAM0014E.toString(), new String[] {MESSAGE_PARAM_MDSE_TP_CD });
            bizMsg.xxErrFlg.setValue(ERROR_FLG_ON);
            return "";
        } else {
            return mdseResultArray.no(0).mdseTpCd.getValue();
        }
    }

    /**
     * get serial# list and set list to global msg
     * @param bizMsg NPAL0100CMsg
     * @param globalMsg NPAL0100SMsg
     */
    public static void setSerialNumberList(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {

        String newEntFlg = bizMsg.xxPgFlg_NW.getValue();
        // Flag is Y (balance qty is NOT 0)
        if (ZYPConstant.FLG_ON_Y.equals(newEntFlg)) {
            return;
        }

        S21SsmEZDResult result = NPAL0100Query.getInstance().getOriginalRecord(getSQLParam(bizMsg, SORT_BY_KEYS));
        if (result.isCodeNormal()) {
            List list = (List) result.getResultObject();
            if (list != null && list.size() > 0) {

                String prevKey1 = "";
                String prevKey2 = "";
                String prevKey3 = "";
                String prevKey4 = "";
                String prevKey5 = "";
                String prevKey6 = "";
                String prevKey7 = "";
                String prevRowNum = "";

                int index = 0;
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String serNum = (String) map.get("SER_NUM");
                    String key1 = checkNull((String) map.get("KEY_INFO_CD_01"));
                    String key2 = checkNull((String) map.get("KEY_INFO_CD_02"));
                    String key3 = checkNull((String) map.get("KEY_INFO_CD_03"));
                    String key4 = checkNull((String) map.get("KEY_INFO_CD_04"));
                    String key5 = checkNull((String) map.get("KEY_INFO_CD_05"));
                    String key6 = checkNull((String) map.get("KEY_INFO_CD_06"));
                    String key7 = checkNull((String) map.get("KEY_INFO_CD_07"));
                    String rowNum = checkNull((String) map.get("KEY_INFO_CD_08"));

                    if( i != 0 
                            && ( !prevKey1.equals(key1) 
                            || !prevKey2.equals(key2) 
                            || !prevKey3.equals(key3) 
                            || !prevKey4.equals(key4) 
                            || !prevKey5.equals(key5) 
                            || !prevKey6.equals(key6) 
                            || !prevKey7.equals(key7) 
                            || !prevRowNum.equals(rowNum))) {
                        index++;
                    }

                    // Set serial#
                    globalMsg.B.no(index).serNum_B1.setValue(serNum);
                    // Set rownum of view
                    globalMsg.B.no(index).xxRowNum_B2.setValue(new BigDecimal(rowNum));                    

                    prevKey1 = key1;
                    prevKey2 = key2;
                    prevKey3 = key3;
                    prevKey4 = key4;
                    prevKey5 = key5;
                    prevKey6 = key6;
                    prevKey7 = key7;
                    prevRowNum = rowNum;
                }
            }
        }
    }

    private static Map<String, String> getSQLParam(NPAL0100CMsg bizMsg, String sortKey ) {

        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("serOwnrId", SER_OWNR_ID);
        param.put("mdseCd", bizMsg.mdseCd.getValue());
        param.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        param.put("cpoDtlLineNum", bizMsg.cpoDtlLineNum.getValue());
        param.put("cpoDtlLineSubNum", bizMsg.cpoDtlLineSubNum.getValue());
        param.put("poOrdNum", bizMsg.poOrdNum.getValue());
        param.put("poOrdDtlLineNum", bizMsg.poOrdDtlLineNum.getValue());
        param.put("poRcvNum", bizMsg.poRcvNum.getValue());
        param.put("poRcvLineNum", bizMsg.poRcvLineNum.getValue());
        param.put("sortKey", sortKey);

        // Check intangible
        NPAL0100Query.getInstance().checkIntangibleItem(bizMsg);
        if (isIntangibleItem(bizMsg)) {
            param.put("invtyCtrlFlg", ZYPConstant.FLG_OFF_N);
        } else {
            param.put("invtyCtrlFlg", ZYPConstant.FLG_ON_Y);
        }
        return param;
    }

    /**
     * Set serial number data for apply, close, paging
     * @param bizMsg NPAL0100CMsg
     * @param globalMsg NPAL0100SMsg
     */
    public void setSerialNumberData(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {
        // Check PageShowFromNum
        if (bizMsg.xxPageShowFromNum.getValueInt() == 0) {
            return;
        }

        checkInputDataForApply(bizMsg, globalMsg);
        if (ERROR_FLG_ON.equals(bizMsg.xxErrFlg.getValue())) {
            return;
        }

        int qtyNum = 0;
        for (int i = 0; i < globalMsg.B.length(); i++) {
            if (ZYPCommonFunc.hasValue(globalMsg.B.no(i).xxRowNum_B2)) {
                qtyNum++;
            } else {
                break;
            }
        }
        int pageShowFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (PROC_FLG_UPDATE.equals(bizMsg.A.no(i).xxSetFlg_A1.getValue())) {
                if (!bizMsg.A.no(i).serNum_A1.getValue().equals(globalMsg.B.no(pageShowFrom + i).serNum_B1.getValue())) {
                    EZDMsg.copy(bizMsg.A.no(i), CMSG_TABLE_MDF.A1.toString(), globalMsg.B.no(pageShowFrom + i), SMSG_TABLE_MDF.B1.toString());
                    EZDMsg.copy(bizMsg.A.no(i), CMSG_TABLE_MDF.A2.toString(), globalMsg.B.no(pageShowFrom + i), SMSG_TABLE_MDF.B2.toString());
                }

            } else if (PROC_FLG_NEW.equals(bizMsg.A.no(i).xxSetFlg_A1.getValue()) && !ZYPCommonFunc.hasValue(globalMsg.B.no(pageShowFrom + i).serNum_B1)) {

                EZDMsg.copy(bizMsg.A.no(i), CMSG_TABLE_MDF.A1.toString(), globalMsg.B.no(qtyNum), SMSG_TABLE_MDF.B1.toString());
                EZDMsg.copy(bizMsg.A.no(i), CMSG_TABLE_MDF.A2.toString(), globalMsg.B.no(qtyNum), SMSG_TABLE_MDF.B2.toString());
                // rownum numbering
                globalMsg.B.no(qtyNum).xxRowNum_B1.setValue(qtyNum + 1);
                globalMsg.B.no(qtyNum).xxRowNum_B2.setValue(qtyNum + 1);

                // copy globalMsg to bizMsg, case empty record
                // include.
                EZDMsg.copy(globalMsg.B.no(pageShowFrom + i), SMSG_TABLE_MDF.B1.toString(), bizMsg.A.no(i), CMSG_TABLE_MDF.A1.toString());
                EZDMsg.copy(globalMsg.B.no(pageShowFrom + i), SMSG_TABLE_MDF.B2.toString(), bizMsg.A.no(i), CMSG_TABLE_MDF.A2.toString());

                qtyNum++;
            }
        }

        for (int i = pageShowFrom; i < pageShowFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.B.getValidCount()) {
                EZDMsg.copy(globalMsg.B.no(i), SMSG_TABLE_MDF.B1.toString(), bizMsg.A.no(i - pageShowFrom), CMSG_TABLE_MDF.A1.toString());
                EZDMsg.copy(globalMsg.B.no(i), SMSG_TABLE_MDF.B2.toString(), bizMsg.A.no(i - pageShowFrom), CMSG_TABLE_MDF.A2.toString());
            } else {
                break;
            }
        }
    }

    private void checkInputDataForApply(NPAL0100CMsg bizMsg, NPAL0100SMsg globalMsg) {
        List<String> serialNumList = new ArrayList<String>();
        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
            serialNumList.add(globalMsg.B.no(i).serNum_B1.getValue());
        }
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).serNum_A1)) {
                serialNumList.set(pagenationFrom + i - 1, bizMsg.A.no(i).serNum_A1.getValue());
            }
        }
        for (int i = 0; i < serialNumList.size(); i++) {
            if ("".equals(serialNumList.get(i))) {
                continue;
            }
            for (int j = i + 1; j < serialNumList.size(); j++) {
                if (serialNumList.get(i).equals(serialNumList.get(j))) {
                    if (i >= pagenationFrom - 1 && i <= bizMsg.xxPageShowToNum.getValueInt() - 1) {
                        // NPAM0039E=0,The entered "Serial Number" is duplicated.
                        bizMsg.A.no(i - (pagenationFrom - 1)).serNum_A1.setErrorInfo(1, MESSAGE_CD.NPAM0039E.toString());
                    }
                    if (j >= pagenationFrom - 1 && j <= bizMsg.xxPageShowToNum.getValueInt() - 1) {
                        bizMsg.A.no(j - (pagenationFrom - 1)).serNum_A1.setErrorInfo(1, MESSAGE_CD.NPAM0039E.toString());
                    }
                    bizMsg.xxErrFlg.setValue(ERROR_FLG_ON);
                }
            }
        }
    }

    /**
     * Method name: isIntangibleItem
     * @param bizMsg NPAL0100CMsg
     * @return boolean
     */
    public static boolean isIntangibleItem(NPAL0100CMsg bizMsg) {
        if (bizMsg.invtyCtrlFlg.getValue().equals("N")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: checkNull no decimal place
     * @param input String
     * @return boolean
     */
    public static String checkNull(String val) {
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }
    
    /**
     * Method name: setEventId
     * @param bizMsg NPAL0100CMsg
     */    
    public static void setEventId(EZDCMsg cMsg) {
        NPAL0100CMsg bizMsg = (NPAL0100CMsg) cMsg;        
        String eventName = bizMsg.getScreenAplID();
        if (eventName.equals(SCRN_APL_ID.NPAL0100_INIT.toString())) {
            bizMsg.eventId.setValue(INIT);
        } else if (eventName.equals(SCRN_APL_ID.NPAL0100Scrn00_Apply.toString())) {
            bizMsg.eventId.setValue(APPLY);
        } else if (eventName.equals(SCRN_APL_ID.NPAL0100Scrn00_CMN_Clear.toString())) {
            bizMsg.eventId.setValue(CLEAR);
        } else if (eventName.equals(SCRN_APL_ID.NPAL0100Scrn00_CMN_Submit.toString())) {
            bizMsg.eventId.setValue(SUBMIT);
        }
    }
    
    /**
     * Method name: isNumber
     * <dd>The method explanation: Check if the input is number with
     * no decimal place
     * @param input String
     * @return boolean
     */
    public static boolean isNumber(String input) {
        if (input == null || input.equals("")) {
            return false;
        } else {
            input = input.trim();
            for (int i = 0; i < input.length(); i++) {
                if (!isDigit(input.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isDigit(char c) {
        if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9') {
            return false;
        } else {
            return true;
        }
    }
}
