/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6370CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/24/2010   Fujitsu         N.Sakamoto      Create          N/A
 *</pre>
 */
package business.blap.NMAL6370.common;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import business.blap.NMAL6370.NMAL6370CMsg;
import business.blap.NMAL6370.NMAL6370Query;
import business.blap.NMAL6370.NMAL6370SMsg;
import business.blap.NMAL6370.NMAL6370_ASMsgArray;
import business.blap.NMAL6370.constant.NMAL6370Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6370CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 * @author N.Sakamoto
 */
public class NMAL6370CommonLogic implements NMAL6370Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    
    /**
     * It copy 'NMAL6370CMsg.A' to 'NMAL6370SMsg.A' .
     * @param bizMsg
     * @param globalMsg
     */
    public static void saveCurrentPageToSMsg(NMAL6370CMsg bizMsg, NMAL6370SMsg globalMsg) {

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
     * It copy 'NMAL6370SMsg.A' to 'NMAL6370CMsg.A' .
     * @param bizMsg
     * @param globalMsg
     * @param index start index position
     */
    public static void loadOnePageToCMsg(NMAL6370CMsg bizMsg, NMAL6370SMsg globalMsg, int index) {
        
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        int startIndex = getPageStartRowIndex(index);
        pagenation(bizMsg, globalMsg, startIndex);
        
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
    }

    /**
     * It copy 'NMAL6370SMsg.A' to 'NMAL6370CMsg.A' .
     * @param bizMsg
     * @param globalMsg
     * @param fromIdx
     */
    public static void pagenation(NMAL6370CMsg bizMsg, NMAL6370SMsg globalMsg, int fromIdx) {
        
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
    public static boolean inputCheck(NMAL6370CMsg bizMsg, NMAL6370SMsg globalMsg) {
        
        boolean isNotError = true;
        int firstErrIdx = -1;
        
        // 1. Duplicate Check(Sort Number, Code)
        // 2. Exist Check(FIRST_PROD_CTRL_CD, CHRG_RATE_VND_GRP_CD)
        Map<BigDecimal, Integer> sortNumMap = new HashMap<BigDecimal, Integer>();
        Map<String, Integer> codeMap = new HashMap<String, Integer>();
        
        NMAL6370_ASMsgArray asMsgArray = globalMsg.A;
        
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            BigDecimal sortNum = asMsgArray.no(i).ctacTpSortNum_A1.getValue();
            String code = asMsgArray.no(i).ctacTpCd_A1.getValue();
             
            if (!sortNumMap.containsKey(sortNum)) {
                sortNumMap.put(sortNum, i);
            } else {
                int duplSortIdx = sortNumMap.get(sortNum);
                if (!asMsgArray.no(duplSortIdx).ctacTpSortNum_A1.isError()) {
                    asMsgArray.no(duplSortIdx).ctacTpSortNum_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.SORT_NUM.toString()});
                    if (firstErrIdx == -1) {
                        firstErrIdx = duplSortIdx;
                    }
                }
                asMsgArray.no(i).ctacTpSortNum_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.SORT_NUM.toString()});
                if (firstErrIdx == -1) {
                    firstErrIdx = i;
                }
                isNotError = false;
            }
            
            if (!codeMap.containsKey(code)) {
                codeMap.put(code, i);
            } else {
                int duplIdx = codeMap.get(code);
                if (!asMsgArray.no(duplIdx).ctacTpCd_A1.isError() && !hasValue(asMsgArray.no(duplIdx).ezUpTime_A1)) {
                    asMsgArray.no(duplIdx).ctacTpCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                    if (firstErrIdx == -1) {
                        firstErrIdx = duplIdx;
                    }
                }
                
                if (!hasValue(asMsgArray.no(i).ezUpTime_A1)) {
                    asMsgArray.no(i).ctacTpCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
                
                isNotError = false;
            }
            
        }

        if (!isNotError) {
            NMAL6370CommonLogic.pagenation(bizMsg, globalMsg, getPageStartRowIndex(firstErrIdx));
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
    
    public static NMAL6370Query getQuery() {
        return NMAL6370Query.getInstance();
    }
}

