/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6670CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 2013/08/14   Fujitsu         K.Kimura        Create          WDS#1458 Installment Invoice modification
 *</pre>
 */
package business.blap.NMAL6670.common;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import business.blap.NMAL6670.common.NMAL6670CommonLogic;
import business.blap.NMAL6670.NMAL6670CMsg;
import business.blap.NMAL6670.NMAL6670Query;
import business.blap.NMAL6670.NMAL6670SMsg;
import business.blap.NMAL6670.NMAL6670_ASMsgArray;
import business.blap.NMAL6670.constant.NMAL6670Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6670CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 */
public class NMAL6670CommonLogic implements NMAL6670Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    
    /**
     * It copy 'NMAL6670CMsg.A' to 'NMAL6670SMsg.A' .
     * @param bizMsg
     * @param globalMsg
     */
    public static void saveCurrentPageToSMsg(NMAL6670CMsg bizMsg, NMAL6670SMsg globalMsg) {

        bizMsg.clearErrorInfo();
        bizMsg.A.clearErrorInfo();
        globalMsg.clearErrorInfo();
        globalMsg.A.clearErrorInfo();
        
        int fromIdx = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(fromIdx + i), null);
        }
    }
    
    /**
     * It copy 'NMAL6670SMsg.A' to 'NMAL6670CMsg.A' .
     * @param bizMsg
     * @param globalMsg
     * @param index start index position
     */
    public static void loadOnePageToCMsg(NMAL6670CMsg bizMsg, NMAL6670SMsg globalMsg, int index) {
        
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        int startIndex = getPageStartRowIndex(index);
        pagenation(bizMsg, globalMsg, startIndex);
        
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
    }

    /**
     * It copy 'NMAL6670SMsg.A' to 'NMAL6670CMsg.A' .
     * @param bizMsg
     * @param globalMsg
     * @param fromIdx
     */
    public static void pagenation(NMAL6670CMsg bizMsg, NMAL6670SMsg globalMsg, int fromIdx) {
        
        int i = fromIdx;
        for( ; i < fromIdx + bizMsg.A.length(); i++ ) {
            if( i < globalMsg.A.getValidCount() ) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - fromIdx), null );
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - fromIdx);

        // set value to pagenation items
        bizMsg.xxPageShowFromNum.setValue(fromIdx + 1);
        bizMsg.xxPageShowToNum.setValue(fromIdx + bizMsg.A.getValidCount());
    }
    
    /**
     * 
     * @param index
     * @return
     */
    public static int getPageStartRowIndex(int index) {
        int startIndex = (index / MAX_DISPALY_ROWS) * MAX_DISPALY_ROWS;
        return startIndex;
    }
    
    /**
     * 
     * @param rowNum
     * @return
     */
    public static int getPageNum(int rowNum) {
        return ((rowNum - 1) / MAX_DISPALY_ROWS + 1);
    }
    
    /**
     * 
     * @param sMsgArray
     * @param sortItemNm
     * @param sortOrdBy
     * @param baseContents
     * @param nullLast
     */
    public static void sortSMsgArray(EZDSMsgArray sMsgArray, String sortItemNm, String sortOrdBy, String[][] baseContents, boolean nullLast) {
        
        S21SortTarget sortTarget = new S21SortTarget(sMsgArray, baseContents);
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        if (nullLast) {
            S21EZDMsgArraySort.sortNullsLast(sortTarget, sortKey, 0, sMsgArray.getValidCount());
        } else {
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsgArray.getValidCount());
        }
        
    }
    
    /**
     * 
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static boolean inputCheck(NMAL6670CMsg bizMsg, NMAL6670SMsg globalMsg) {
        
        boolean isNotError = true;
        int firstErrIdx = -1;
        
        // 1. Duplicate Check(Code, Detail Number)
        // 2. Percent Value Check
        
        NMAL6670_ASMsgArray asMsgArray = globalMsg.A;
        
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            String code1 = asMsgArray.no(i).istlPmtTermCd_A1.getValue();
            String dtlNum1 = asMsgArray.no(i).istlPmtTermDtlNum_A1.getValue();
            BigDecimal amtPct1 = asMsgArray.no(i).istlPmtTermPct_A1.getValue();
            BigDecimal totAmtPct = amtPct1;
            boolean codeExist = false;
            
            for (int j = 0; j < asMsgArray.getValidCount(); j++) {
                String code2 = asMsgArray.no(j).istlPmtTermCd_A1.getValue();
                String dtlNum2 = asMsgArray.no(j).istlPmtTermDtlNum_A1.getValue();
                BigDecimal amtPct2 = asMsgArray.no(j).istlPmtTermPct_A1.getValue();
                
                if (code1.equals(code2) && dtlNum1.equals(dtlNum2)) {
                    if (codeExist) {
                        asMsgArray.no(i).istlPmtTermCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.CODE.toString() + "," + DISPLAY_NAME.DTL_NUM.toString()});
                        if (firstErrIdx == -1) {
                            firstErrIdx = i;
                        }
                        isNotError = false;
                    }
                    codeExist = true;
                }
                
                if (code1.equals(code2) && !dtlNum1.equals(dtlNum2)) {
                    totAmtPct = totAmtPct.add(amtPct2);
                }
            }
            
            if (!(totAmtPct.compareTo(MAX_PCT_VALUE) == 0)) {
                asMsgArray.no(i).istlPmtTermPct_A1.setErrorInfo(1, MESSAGE_ID.NMAM0046E.toString(), new String[]{DISPLAY_NAME.SUM_PCT.toString(), DISPLAY_NAME.TOT_PCT.toString()});
                if (firstErrIdx == -1) {
                    firstErrIdx = i;
                }
                isNotError = false;
            }
        }

        if (!isNotError) {
            NMAL6670CommonLogic.pagenation(bizMsg, globalMsg, getPageStartRowIndex(firstErrIdx));
        }
        return isNotError;

    }
    
    /**
     * When origValue equals inValue, return true.
     * @param origValue
     * @param inValue
     * @return
     */
    public static boolean compareString(EZDSStringItem origValue, EZDSStringItem inValue) {
        if (hasValue(origValue)) {
            if (!origValue.getValue().equals(inValue.getValue())) {
                return true;
            }
        } else if (hasValue(inValue)) {
            return true;
        }
        return false;
    }
    
    /**
     * When origValue equals inValue, return true.
     * @param origValue
     * @param inValue
     * @return
     */
    public static boolean compareBigDecimal(EZDSBigDecimalItem origValue, EZDSBigDecimalItem inValue) {
        if (hasValue(origValue)) {
            BigDecimal compValue = ZERO;
            if (hasValue(inValue)) {
                compValue = inValue.getValue();
            }
            if (origValue.getValue().compareTo(compValue) != 0) {
                return true;
            }
        } else if (hasValue(inValue)) {
            return true;
        }
        return false;
    }
    
    public static NMAL6670Query getQuery() {
        return NMAL6670Query.getInstance();
    }
}

