package business.blap.NFDL0100.common;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0100.NFDL0100CMsg;
import business.blap.NFDL0100.NFDL0100Query;
import business.blap.NFDL0100.NFDL0100SMsg;
import business.blap.NFDL0100.NFDL0100_ACMsgArray;
import business.blap.NFDL0100.NFDL0100_ASMsgArray;
import business.blap.NFDL0100.NFDL0100_BCMsg;
import business.blap.NFDL0100.constant.NFDL0100Constant;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PRT_CTRL_REC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 * 2019/07/26   Fujitsu         M.Ishii         Update          QC#51679
 *</pre>
 */
public class NFDL0100CommonLogic implements NFDL0100Constant {

    /**
     * Get AR Account Date
     * @param glblCmpyCd String
     * @return String
     */
    public static String getDsAccountCustName(String glblCmpyCd, String dsAcctNum) {

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

        String dsAcctNm = null;
        SELL_TO_CUSTTMsgArray outMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray != null && outMsgArray.getValidCount() > 0) {
            dsAcctNm = outMsgArray.no(0).dsAcctNm.getValue();
        }
        return dsAcctNm;
    }

    /**
     * Get AR Account Date
     * @param glblCmpyCd String
     * @return String
     */
    public static String getBillToCustName(String glblCmpyCd, String billToCustCd) {

        Map<String, String> param = new HashMap<String, String>(); 
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("billToCustCd", billToCustCd);

        S21SsmEZDResult result = NFDL0100Query.getInstance().getBillToCustName(param);
        String billToCustNm = (String) result.getResultObject();
        return billToCustNm;
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFDL0100CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
        }
        bizMsgAry.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {

        NFDL0100_ACMsgArray bizMsgAry_A = bizMsg.A;

        NFDL0100_ASMsgArray shareMsgAry_A = globalMsg.A;

        ZYPTableUtil.clear(bizMsgAry_A);

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry_A.length() && startIndex + dispRowNum < shareMsgAry_A.getValidCount(); dispRowNum++) {
            EZDMsg.copy(shareMsgAry_A.get(startIndex + dispRowNum), null, bizMsgAry_A.get(dispRowNum), null);
        }
        bizMsgAry_A.setValidCount(dispRowNum);
        bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
        bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry_A.getValidCount());
    }

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void copyPage(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {
        NFDL0100_ACMsgArray bizMsgAry_A = bizMsg.A;
        NFDL0100_ASMsgArray shareMsgAry_A = globalMsg.A;

        int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int dispRowNum = 0;
        if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
            return;
        }
        for (; dispRowNum < bizMsgAry_A.length() && dispRowNum < bizMsgAry_A.getValidCount(); dispRowNum++) {
            EZDMsg.copy(bizMsgAry_A.get(dispRowNum), null, shareMsgAry_A.get(startIndex + dispRowNum), null);
        }
    }

    /**
     * Previous Page
     * @param bizMsg NFDL0100CMsg
     * @param globalMsg NFDL0100SMsg
     */
    public static void prevPage(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsg.A.length());
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Next Page
     * @param bizMsg NFDL0100CMsg
     * @param globalMsg NFDL0100SMsg
     */
    public static void nextPage(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {
        bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() + bizMsg.A.length());
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Next Page
     * @param bizMsg NFDL0100CMsg
     * @param globalMsg NFDL0100SMsg
     */
    public static void jumpPage(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_A, new BigDecimal(bizMsg.A.length() * (bizMsg.xxPageShowCurNum_A.getValueInt() - 1) + 1));
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Last Page
     * @param bizMsg NFDL0100CMsg
     * @param globalMsg NFDL0100SMsg
     */
    public static void lastPage(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {
        BigDecimal lastPageFromNum = getLastPageFromNum(bizMsg, globalMsg);
        bizMsg.xxPageShowFromNum_A.setValue(lastPageFromNum);
        dispPage(bizMsg, globalMsg);
    }

    /**
     * Get Last Page From Number
     * @param bizMsg NFDL0100CMsg
     * @param globalMsg NFDL0100SMsg
     * @return BigDecimal
     */
    public static BigDecimal getLastPageFromNum(NFDL0100CMsg bizMsg, NFDL0100SMsg globalMsg) {
        int pageCnt = globalMsg.A.getValidCount() / bizMsg.A.length();
        int lastPageFromNum = bizMsg.A.length() * pageCnt + 1;
        if (globalMsg.A.getValidCount() % bizMsg.A.length() == 0) {
            lastPageFromNum = lastPageFromNum - bizMsg.A.length();
        }
        return new BigDecimal(lastPageFromNum);
    }

    /**
     * is Any CheckBox is Checked.
     * @param cMsg NFDL0100CMsg
     * @param sMsg NFDL0100SMsg
     * @param selectedRows Selected Rows
     * @return true : Any Checked
     */
    public static boolean isAnyCheckBoxChecked(NFDL0100CMsg cMsg, NFDL0100SMsg sMsg, List<Integer> selectedRows) {

        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo("NWCM0096E");
            return false;
        }

        return true;
    }

    /**
     * check select max count
     * @param cMsg NFDL0100CMsg
     * @param sMsg NFDL0100SMsg
     * @param selectedRows Selected Rows
     * @return true : No Error
     */
    public static boolean checkSelectMaxCnt(String glblCmpyCd, NFDL0100CMsg cMsg, NFDL0100SMsg sMsg, List<Integer> selectedRows) {

        int maxRowCnt = ZYPCodeDataUtil.getNumConstValue(CONST_MAX_ROW_CNT, glblCmpyCd).intValue();

        if (selectedRows.size() > maxRowCnt) {
            cMsg.setMessageInfo("NWCM0099E", new String[] {String.valueOf(maxRowCnt) });
            return false;
        }

        return true;
    }

    /**
     * Check Consolidated
     * @param cMsg NFDL0100CMsg
     * @param sMsg NFDL0100SMsg
     * @param selectedRows Selected Rows
     * @param xxDplyCtrlFlg Control Flag
     * @return true : No Error
     */
    public static boolean checkConsolidated(NFDL0100CMsg cMsg, NFDL0100SMsg sMsg, List<Integer> selectedRows, EZDCStringItem xxDplyCtrlFlg) {

        int pageFromIdx = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
        int pageToIdx = cMsg.xxPageShowToNum_A.getValueInt() - 1;

        for (int i = 0; i < selectedRows.size(); i++) {
            int checkedRowNum = selectedRows.get(i);
            String invPrtCtrlRecCd = sMsg.A.no(checkedRowNum).invPrtCtrlRecCd_A.getValue();

            if (INV_PRT_CTRL_REC.CONSOLIDATED_BILL.equals(invPrtCtrlRecCd)) {
                if (checkedRowNum >= pageFromIdx && checkedRowNum <= pageToIdx) {
                    cMsg.A.no(checkedRowNum - pageFromIdx).xxChkBox_A.setErrorInfo(2, "NWCM0097W");
                }

                cMsg.setMessageInfo("NWCM0097W");
                xxDplyCtrlFlg.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(xxDplyCtrlFlg.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * get File Name
     * @param invFileUrl Invoice File URL
     * @return File Name
     */
    public static String getFileName(String invFileUrl) {

        if (!ZYPCommonFunc.hasValue(invFileUrl)) {
            return null;
        }

        return new File(invFileUrl).getName();
    }
    // 2019/07/26 QC#51679 Add Start
    /**
     * check Duplicate Invoice Bill Num
     * @param cMsg NFDL0100CMsg
     * @param sMsg NFDL0100SMsg
     * @param selectedRows List<Integer>
     * @param i int
     * @return true : No duplicate
     */
    public static boolean checkDupulicateInvBill(NFDL0100CMsg cMsg, NFDL0100SMsg sMsg, List<Integer> selectedRows, int i) {
        List<String> dplctChkList = new ArrayList<String>();
        for (int j = 0; j < i; j++) {
            int checkedRowNum = selectedRows.get(j);
            if (ZYPCommonFunc.hasValue(sMsg.A.no(checkedRowNum).grpInvNum_A)) {
                dplctChkList.add(sMsg.A.no(checkedRowNum).grpInvNum_A.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(sMsg.A.no(selectedRows.get(i)).grpInvNum_A)) {
            if (dplctChkList.contains(sMsg.A.no(selectedRows.get(i)).grpInvNum_A.getValue())) {
                return false;
            }
        }
        return true;
    }
    // 2019/07/26 QC#51679 Add End
}
