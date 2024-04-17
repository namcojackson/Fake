/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6550CommonLogic
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.blap.NMAL6550.common;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6550.NMAL6550CMsg;
import business.blap.NMAL6550.NMAL6550SMsg;
import business.blap.NMAL6550.NMAL6550_ASMsg;
import business.blap.NMAL6550.NMAL6550_ASMsgArray;
import business.blap.NMAL6550.constant.NMAL6550Constant;
import business.db.CCYTMsg;
import business.db.COA_AFFLTMsg;
import business.db.CTRYTMsg;
import business.db.IMPT_INV_CNSGNTMsg;
import business.db.IMPT_INV_CNSGNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * Class name: NMAL6550CommonLogic
 * <dd>The class explanation: Common Logic for business component.
 * <dd>Remarks:
 * 
 * @version 1.0
 * @author H.Nagashima
 */
public class NMAL6550CommonLogic implements NMAL6550Constant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    
    /**
     * It copy 'NMAL6550CMsg.A' to 'NMAL6550SMsg.A' .
     * @param bizMsg
     * @param globalMsg
     */
    public static void saveCurrentPageToSMsg(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {

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
     * It copy 'NMAL6550SMsg.A' to 'NMAL6550CMsg.A' .
     * @param bizMsg
     * @param globalMsg
     * @param index start index position
     */
    public static void loadOnePageToCMsg(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg, int index) {
        
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        int startIndex = getPageStartRowIndex(index);
        pagenation(bizMsg, globalMsg, startIndex);
        
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
    }

    /**
     * It copy 'NMAL6550SMsg.A' to 'NMAL6550CMsg.A' .
     * @param bizMsg
     * @param globalMsg
     * @param fromIdx
     */
    public static void pagenation(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg, int fromIdx) {
        
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
    public static void sortNMAL6550ASMsgArray(EZDSMsgArray sMsgArray, String sortItemNm, String sortOrdBy, String[][] baseContents, boolean nullLast) {
        
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
    public static boolean inputCheckAll(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
        
        boolean isNotError = true;
        
        // 1. Duplicate Check(Sort Number, Code)
        Map<BigDecimal, Integer> sortNumMap = new HashMap<BigDecimal, Integer>();
        Map<String, Integer> codeMap = new HashMap<String, Integer>();
        S21LRUMap<String, CTRYTMsg> ctryCache = new S21LRUMap<String, CTRYTMsg>();
        S21LRUMap<String, CCYTMsg> ccyCache = new S21LRUMap<String, CCYTMsg>();
        S21LRUMap<String, COA_AFFLTMsg> caCache = new S21LRUMap<String, COA_AFFLTMsg>();
        S21LRUMap<String, IMPT_INV_CNSGNTMsg> iicCache = new S21LRUMap<String, IMPT_INV_CNSGNTMsg>();

        NMAL6550_ASMsgArray asMsgArray = globalMsg.A;
        int errIdx = -1;
        
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6550_ASMsg asMsg = asMsgArray.no(i);
            BigDecimal sortNum = asMsg.glblCmpySortNum_A1.getValue();
            String code = asMsg.glblCmpyCd_A1.getValue();
            
            if (!sortNumMap.containsKey(sortNum)) {
                sortNumMap.put(sortNum, i);
            } else {
                int duplSortIdx = sortNumMap.get(sortNum);
                if (!asMsgArray.no(duplSortIdx).glblCmpySortNum_A1.isError()) {
                    asMsgArray.no(duplSortIdx).glblCmpySortNum_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.SORT_NUM.toString()});
                    if (errIdx == -1) {
                        errIdx = duplSortIdx;
                    }
                }
                asMsg.glblCmpySortNum_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.SORT_NUM.toString()});
                if (errIdx == -1) {
                    errIdx = i;
                }
                isNotError = false;
            }
            
            if (!codeMap.containsKey(code)) {
                codeMap.put(code, i);
            } else {
                int duplIdx = codeMap.get(code);
                if (!asMsgArray.no(duplIdx).glblCmpyCd_A1.isError() && !hasValue(asMsgArray.no(duplIdx).ezUpTime_A1)) {
                    asMsgArray.no(duplIdx).glblCmpyCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                    if (errIdx == -1) {
                        errIdx = duplIdx;
                    }
                }
                if (!hasValue(asMsgArray.no(i).ezUpTime_A1)) {
                    asMsg.glblCmpyCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0072E.toString(), new String[]{DISPLAY_NAME.CODE.toString()});
                    if (errIdx == -1) {
                        errIdx = i;
                    }
                }
                isNotError = false;
            }
        }
        
        if (!isNotError) {
            NMAL6550CommonLogic.pagenation(bizMsg, globalMsg, NMAL6550CommonLogic.getPageStartRowIndex(errIdx));
            return isNotError;
        }
        
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            NMAL6550_ASMsg asMsg = asMsgArray.no(i);
            
            String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
            // 2. Master Existance Check
            //CTRY Existance Check
            String ctryCd = asMsg.ctryCd_A1.getValue();
            if (hasValue(ctryCd)) {
                CTRYTMsg ctryTMsg = ctryCache.get(ctryCd);
                if (ctryTMsg == null) {
                    ctryTMsg =NMAL6550CommonLogic.getCountry(glblCmpyCd, ctryCd);
                    if (ctryTMsg == null) {
                        asMsg.ctryCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0011E.toString(), new String[]{DISPLAY_NAME.CTRY_CD.toString()});
                        if (errIdx == -1) {
                            errIdx = i;
                        }
                        isNotError = false;
                    } else {
                        ctryCache.put(ctryCd, ctryTMsg);
                    }
                }
            }

            //CCY Existance Check
            String ccyCd = asMsg.stdCcyCd_A1.getValue();
            asMsg.stdCcyNm_A1.clear();
            if (hasValue(ccyCd)) {
                CCYTMsg ccyTMsg = ccyCache.get(ccyCd);
                if (ccyTMsg == null) {
                    ccyTMsg =NMAL6550CommonLogic.getCcy(glblCmpyCd, ccyCd);
                    if (ccyTMsg == null) {
                        asMsg.stdCcyCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0011E.toString(), new String[]{DISPLAY_NAME.CCY_CD.toString()});
                        if (errIdx == -1) {
                            errIdx = i;
                        }
                        isNotError = false;
                    } else {
                        ccyCache.put(ccyCd, ccyTMsg);
                        asMsg.stdCcyNm_A1.setValue(ccyTMsg.ccyNm.getValue());
                    }
                } else {
                    asMsg.stdCcyNm_A1.setValue(ccyTMsg.ccyNm.getValue());
                }
            }

            //COA_AFFL Existance Check
            String coaAfflCd = asMsg.coaAfflCd_A1.getValue();
            if (hasValue(coaAfflCd)) {
                COA_AFFLTMsg caTMsg = caCache.get(coaAfflCd);
                if (caTMsg == null) {
                    caTMsg =NMAL6550CommonLogic.getCoaAffl(glblCmpyCd, coaAfflCd);
                    if (caTMsg == null) {
                        asMsg.coaAfflCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0011E.toString(), new String[]{DISPLAY_NAME.COA_AFFL_CD.toString()});
                        if (errIdx == -1) {
                            errIdx = i;
                        }
                        isNotError = false;
                    } else {
                        caCache.put(coaAfflCd, caTMsg);
                    }
                }
            }

            //ACCT Existance Check
            String acctCd = asMsg.acctCd_A1.getValue();
            if (hasValue(acctCd)) {
                IMPT_INV_CNSGNTMsg iicTMsg = iicCache.get(acctCd);
                if (iicTMsg == null) {
                    iicTMsg =NMAL6550CommonLogic.getAcct(glblCmpyCd, acctCd);
                    if (iicTMsg == null) {
                        asMsg.acctCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0011E.toString(), new String[]{DISPLAY_NAME.ACCT_CD.toString()});
                        if (errIdx == -1) {
                            errIdx = i;
                        }
                        isNotError = false;
                    } else {
                        iicCache.put(coaAfflCd, iicTMsg);
                    }
                }
            }

        }
        
        if (!isNotError) {
            NMAL6550CommonLogic.pagenation(bizMsg, globalMsg, NMAL6550CommonLogic.getPageStartRowIndex(errIdx));
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


    public static boolean searchCoaAffl(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg, int idx) {

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String coaAfflCd = bizMsg.A.no(idx).coaAfflCd_A1.getValue();

        if (!hasValue(coaAfflCd)) {
            bizMsg.A.no(idx).coaAfflNm_A1.clear();
            return true;
        }

        COA_AFFLTMsg tmsg = getCoaAffl(glblCmpyCd, coaAfflCd);
        
        if (tmsg != null) {
            bizMsg.A.no(idx).coaAfflNm_A1.setValue(tmsg.coaAfflNm.getValue());
            return true;
        } else {
            bizMsg.A.no(idx).coaAfflCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0011E.toString(), new String[]{DISPLAY_NAME.COA_AFFL_CD.toString()});
            bizMsg.A.no(idx).coaAfflNm_A1.clear();
            return false;
        }
    }
    public static COA_AFFLTMsg getCoaAffl(String glblCmpyCd, String coaAfflCd) {

        COA_AFFLTMsg tmsg = new COA_AFFLTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.coaAfflCd.setValue(coaAfflCd);
        
        tmsg = (COA_AFFLTMsg) S21CacheTBLAccessor.findByKey(tmsg);
        
        if (tmsg == null) {
            return null;
        }

        return tmsg;

    }

    public static boolean searchAcct(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg, int idx) {

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String acctCd = bizMsg.A.no(idx).acctCd_A1.getValue();
        
        if (!hasValue(acctCd)) {
            bizMsg.A.no(idx).imptInvCnsgnNm_A1.clear();
            return true;
        }
        
        IMPT_INV_CNSGNTMsg tmsg = getAcct(glblCmpyCd, acctCd);
        
        if (tmsg != null) {
            bizMsg.A.no(idx).imptInvCnsgnNm_A1.setValue(tmsg.imptInvCnsgnNm.getValue());
            return true;
        } else {
            bizMsg.A.no(idx).acctCd_A1.setErrorInfo(1, MESSAGE_ID.NMAM0011E.toString(), new String[]{DISPLAY_NAME.ACCT_CD.toString()});
            bizMsg.A.no(idx).imptInvCnsgnNm_A1.clear();
            return false;
        }
    }
    public static IMPT_INV_CNSGNTMsg getAcct(String glblCmpyCd, String acctCd) {
        
        String rgtnStsCd = "P20";
        String slsDt = ZYPDateUtil.getSalesDate();
        
        IMPT_INV_CNSGNTMsg tmsg = new IMPT_INV_CNSGNTMsg();
        tmsg.setSQLID("002");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("imptInvCnsgnCd01", acctCd);
        tmsg.setConditionValue("effFromDt01", slsDt);
        tmsg.setConditionValue("effThruDt01", slsDt);
        tmsg.setConditionValue("rgtnStsCd01", rgtnStsCd);

        IMPT_INV_CNSGNTMsgArray tmsgArray = (IMPT_INV_CNSGNTMsgArray) EZDTBLAccessor.findByCondition(tmsg);

        if (tmsgArray.length() != 0) {
            tmsg = tmsgArray.no(0);
            if ("0010".equals(tmsg.getReturnCode())) {
                return null;
            }

        } else {
            return null;
        }
        return tmsg;
        
    }

    public static CTRYTMsg getCountry(String glblCmpyCd, String ctryCd) {

        CTRYTMsg tmsg = new CTRYTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.ctryCd.setValue(ctryCd);
        
        tmsg = (CTRYTMsg) S21CacheTBLAccessor.findByKey(tmsg);
        
        if (tmsg == null) {
            return null;
        }

        return tmsg;

    }

    public static CCYTMsg getCcy(String glblCmpyCd, String ccyCd) {

        CCYTMsg tmsg = new CCYTMsg();
        tmsg.glblCmpyCd.setValue(glblCmpyCd);
        tmsg.ccyCd.setValue(ccyCd);
        
        tmsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(tmsg);
        
        if (tmsg == null) {
            return null;
        }

        return tmsg;

    }

}

