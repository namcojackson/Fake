/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6460CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/24/2010   Fujitsu         N.Sakamoto      Create          N/A
 *</pre>
 */
package business.blap.NMAL6460.common;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6460.NMAL6460CMsg;
import business.blap.NMAL6460.NMAL6460Query;
import business.blap.NMAL6460.NMAL6460SMsg;
import business.blap.NMAL6460.NMAL6460_ASMsgArray;
import business.blap.NMAL6460.constant.NMAL6460Constant;
import business.db.CASH_DISC_TERMTMsg;
import business.db.CASH_DISC_TERMTMsgArray;
import business.db.ISTL_PMT_TERMTMsg;
import business.db.ISTL_PMT_TERMTMsgArray;
import business.db.PMT_TERMTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6460CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 * @author N.Sakamoto
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 2016/05/19   Hitachi         T.Aoyagi        Update          QC#8569
 */
public class NMAL6460CommonLogic implements NMAL6460Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    
    /**
     * It copy 'NMAL6460CMsg.A' to 'NMAL6460SMsg.A' .
     * @param bizMsg
     * @param globalMsg
     */
    public static void saveCurrentPageToSMsg(NMAL6460CMsg bizMsg, NMAL6460SMsg globalMsg) {

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
     * It copy 'NMAL6460SMsg.A' to 'NMAL6460CMsg.A' .
     * @param bizMsg
     * @param globalMsg
     * @param index start index position
     */
    public static void loadOnePageToCMsg(NMAL6460CMsg bizMsg, NMAL6460SMsg globalMsg, int index) {
        
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        int startIndex = getPageStartRowIndex(index);
        pagenation(bizMsg, globalMsg, startIndex);
        
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
    }

    /**
     * It copy 'NMAL6460SMsg.A' to 'NMAL6460CMsg.A' .
     * @param bizMsg
     * @param globalMsg
     * @param fromIdx
     */
    public static void pagenation(NMAL6460CMsg bizMsg, NMAL6460SMsg globalMsg, int fromIdx) {
        
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
    public static boolean inputCheck(NMAL6460CMsg bizMsg, NMAL6460SMsg globalMsg) {
        
        boolean isNotError = true;
        int firstErrIdx = -1;
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        
        // 1. Duplicate Check(Sort Number, Code)
        Map<BigDecimal, Integer> sortNumMap = new HashMap<BigDecimal, Integer>();
        Map<String, Integer> codeMap = new HashMap<String, Integer>();
        // 2. Exists Check(Payment Term, Cash Discount)
        Map<String, Integer> pmtTermMap = new HashMap<String, Integer>();
        Map<String, Integer> cashDicTermMap = new HashMap<String, Integer>();
        Map<String, Integer> istlPmtTermMap = new HashMap<String, Integer>();
        
        NMAL6460_ASMsgArray asMsgArray = globalMsg.A;
        
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            BigDecimal sortNum = asMsgArray.no(i).pmtTermCashDiscSortNum_A1.getValue();
            String code = asMsgArray.no(i).pmtTermCashDiscCd_A1.getValue();
            String pmtTermCd = asMsgArray.no(i).pmtTermCd_A1.getValue();
            String cashDiscTermCd = asMsgArray.no(i).cashDiscTermCd_A1.getValue();
            String istlPmtTermFlg = asMsgArray.no(i).istlPmtTermFlg_A1.getValue();
            // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
            String pmtTermConslDueDay = asMsgArray.no(i).pmtTermConslDueDay_A1.getValue();
            // END 2016/05/19 T.Aoyagi [QC#8569, ADD]

            if (!sortNumMap.containsKey(sortNum)) {
                sortNumMap.put(sortNum, i);
            } else {
                int duplSortIdx = sortNumMap.get(sortNum);
                if (!asMsgArray.no(duplSortIdx).pmtTermCashDiscSortNum_A1.isError()) {
                    asMsgArray.no(duplSortIdx).pmtTermCashDiscSortNum_A1.setErrorInfo(1
                                                                                    , MESSAGE_ID.NMAM0072E.toString()
                                                                                    , new String[]{DISPLAY_NAME.SORT_NUM.toString()});
                    if (firstErrIdx == -1) {
                        firstErrIdx = duplSortIdx;
                    }
                }
                asMsgArray.no(i).pmtTermCashDiscSortNum_A1.setErrorInfo(1
                                                                    , MESSAGE_ID.NMAM0072E.toString()
                                                                    , new String[]{DISPLAY_NAME.SORT_NUM.toString()});
                if (firstErrIdx == -1) {
                    firstErrIdx = i;
                }
                isNotError = false;
            }
            
            if (!codeMap.containsKey(code)) {
                codeMap.put(code, i);
            } else {
                int duplIdx = codeMap.get(code);
                if (!asMsgArray.no(duplIdx).pmtTermCashDiscCd_A1.isError() && !hasValue(asMsgArray.no(duplIdx).ezUpTime_A1)) {
                    asMsgArray.no(duplIdx).pmtTermCashDiscCd_A1.setErrorInfo(1
                                                                            , MESSAGE_ID.NMAM0072E.toString()
                                                                            , new String[]{DISPLAY_NAME.CODE.toString()});
                    if (firstErrIdx == -1) {
                        firstErrIdx = duplIdx;
                    }
                }
                
                if (!hasValue(asMsgArray.no(i).ezUpTime_A1)) {
                    asMsgArray.no(i).pmtTermCashDiscCd_A1.setErrorInfo(1
                                                                    , MESSAGE_ID.NMAM0072E.toString()
                                                                    , new String[]{DISPLAY_NAME.CODE.toString()});
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                }
                
                isNotError = false;
            }
            
            if (!pmtTermMap.containsKey(pmtTermCd)) {
                PMT_TERMTMsg pmtTermInfo = findPmtTermByKey(glblCmpyCd, pmtTermCd);
                if (pmtTermInfo != null) {
                    pmtTermMap.put(pmtTermCd, i);
                } else {
                    asMsgArray.no(i).pmtTermCd_A1.setErrorInfo(1
                                                            , MESSAGE_ID.NMAM0009E.toString()
                                                            , new String[]{DISPLAY_NAME.PMT_TERM.toString()});
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                    isNotError = false;
                }
            }
            
            if (hasValue(cashDiscTermCd)) {
                if (!cashDicTermMap.containsKey(cashDiscTermCd)) {
                    
                    CASH_DISC_TERMTMsgArray cashDiscTermList = findCashDiscTerm(bizMsg.glblCmpyCd.getValue(), cashDiscTermCd);
                    
                    if (cashDiscTermList.length() > 0) {
                        cashDicTermMap.put(cashDiscTermCd, i);
                    } else {
                        asMsgArray.no(i).cashDiscTermCd_A1.setErrorInfo(1
                                                                    , MESSAGE_ID.NMAM0009E.toString()
                                                                    , new String[]{DISPLAY_NAME.CASH_DISC_TERM.toString()});
                        if (firstErrIdx == -1) {
                            firstErrIdx = i;
                        }
                        isNotError = false;
                    }
                }
            }
            
            if (FLG_ON_Y.equals(istlPmtTermFlg)) {
                 if (!istlPmtTermMap.containsKey(pmtTermCd)) {
                    ISTL_PMT_TERMTMsgArray istlPmtTermList = findIstlPmtTerm(bizMsg.glblCmpyCd.getValue(), pmtTermCd);
                    
                    if (istlPmtTermList.length() > 0) {
                        istlPmtTermMap.put(pmtTermCd, i);
                    } else {
                        asMsgArray.no(i).pmtTermCd_A1.setErrorInfo(1
                                                            , MESSAGE_ID.NMAM0009E.toString()
                                                            , new String[]{DISPLAY_NAME.ISTL_PMT_TERM.toString()});
                        if (firstErrIdx == -1) {
                            firstErrIdx = i;
                        }
                        isNotError = false;
                    }
                 }
            }

            // START 2016/05/19 T.Aoyagi [QC#8569, ADD]
            if (hasValue(pmtTermConslDueDay)) {
                if (ZYPCommonFunc.isNumeric(pmtTermConslDueDay)) {

                    if (!ZYPCommonFunc.isCheckSuutiHanni(Integer.parseInt(pmtTermConslDueDay), 1, MONTH_END)) {
                        asMsgArray.no(i).pmtTermConslDueDay_A1.setErrorInfo(1
                                                                , MESSAGE_ID.NMAM0153E.toString()
                                                                , new String[]{DISPLAY_NAME.PMT_TERM_CONSL_DUE_DAY.toString()});
                        if (firstErrIdx == -1) {
                            firstErrIdx = i;
                        }
                        isNotError = false;
                    }
                } else {
                    asMsgArray.no(i).pmtTermConslDueDay_A1.setErrorInfo(1
                                                            , MESSAGE_ID.NMAM0031E.toString()
                                                            , new String[]{DISPLAY_NAME.PMT_TERM_CONSL_DUE_DAY.toString()});
                    if (firstErrIdx == -1) {
                        firstErrIdx = i;
                    }
                    isNotError = false;
                }
            }
            // END 2016/05/19 T.Aoyagi [QC#8569, ADD]

        }

        if (!isNotError) {
            NMAL6460CommonLogic.pagenation(bizMsg, globalMsg, getPageStartRowIndex(firstErrIdx));
        }
        return isNotError;

    }
    
    public static PMT_TERMTMsg findPmtTermByKey(String glblCmpyCd, String pmtTermCd) {
        PMT_TERMTMsg inMsg = new PMT_TERMTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.pmtTermCd.setValue(pmtTermCd);
        return (PMT_TERMTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    public static ISTL_PMT_TERMTMsgArray findIstlPmtTerm(String glblCmpyCd, String istlPmtTermCd) {
        
        ISTL_PMT_TERMTMsg inMsg = new ISTL_PMT_TERMTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("istlPmtTermCd01", istlPmtTermCd);
        return (ISTL_PMT_TERMTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    
    private static CASH_DISC_TERMTMsgArray findCashDiscTerm(String glblCmpyCd, String cashDiscTermCd) {
        
        CASH_DISC_TERMTMsg cashDiscTermTMsg = new CASH_DISC_TERMTMsg();
        cashDiscTermTMsg.setSQLID("001");
        cashDiscTermTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cashDiscTermTMsg.setConditionValue("cashDiscTermCd01", cashDiscTermCd);
        cashDiscTermTMsg.setConditionValue("cashDiscTermEffFromDt01", ZYPDateUtil.getSalesDate());
        cashDiscTermTMsg.setConditionValue("cashDiscTermEffThruDt01", ZYPDateUtil.getSalesDate());
        
        return (CASH_DISC_TERMTMsgArray) EZDTBLAccessor.findByCondition(cashDiscTermTMsg);
        
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
    
    public static NMAL6460Query getQuery() {
        return NMAL6460Query.getInstance();
    }
    
}

