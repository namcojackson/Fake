package business.blap.ZZVL0020;

import java.math.BigDecimal;
import java.util.HashMap;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.ZZVL0020.common.ZZVL0020CommonLogic;
import business.blap.ZZVL0020.constant.ZZVL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/26   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZVL0020BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("ZZVL0020_INIT".equals(screenAplID)) {
                doProcess_ZZVL0020_INIT((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn00_Search((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn00_Select".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn00_Select((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn00_PageNext((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn00_PagePrev((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn00_TBLColumnSort((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn00_CMN_Clear((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_CMN_Reset".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn00_CMN_Reset((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_CMN_Return".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn01_CMN_Return((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_Left".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn01_Left((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_Right".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn01_Right((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_PageNext".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn01_PageNext((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_PagePrev".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn01_PagePrev((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            } else if ("ZZVL0020Scrn01_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZVL0020Scrn01_TBLColumnSort((ZZVL0020CMsg) cMsg, (ZZVL0020SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_ZZVL0020_INIT(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("rowNum", sMsg.C.length() + 1);
        param.put("cMsg", cMsg);

        S21SsmEZDResult ssmResult = ZZVL0020Query.getInstance().getRoleList(param, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.C.length();
            }

            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_1, BigDecimal.valueOf(cMsg.C.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_1, BigDecimal.valueOf(queryResCnt));

        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum_1.clear();
            cMsg.xxPageShowToNum_1.clear();
            cMsg.xxPageShowOfNum_1.clear();
        }

    }

    private void doProcess_ZZVL0020Scrn00_Search(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("rowNum", sMsg.C.length() + 1);
        param.put("cMsg", cMsg);

        S21SsmEZDResult ssmResult = ZZVL0020Query.getInstance().getRoleList(param, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.C.length();
            } else {
                cMsg.setMessageInfo("ZZM8002I");
            }

            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_1, BigDecimal.valueOf(cMsg.C.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_1, BigDecimal.valueOf(queryResCnt));

            if (cMsg.C.getValidCount() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxNum_1, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, cMsg.glblCmpyCd_1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_BK, cMsg.glblCmpyCd_1.getValue());
                ZZVL0020CommonLogic.doInit(cMsg.glblCmpyCd_1.getValue(), cMsg, sMsg);
            }
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum_1.clear();
            cMsg.xxPageShowToNum_1.clear();
            cMsg.xxPageShowOfNum_1.clear();
        }

    }

    private void doProcess_ZZVL0020Scrn00_Select(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, cMsg.glblCmpyCd_1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_BK, cMsg.glblCmpyCd_1.getValue());
        ZZVL0020CommonLogic.doInit(cMsg.glblCmpyCd_1.getValue(), cMsg, sMsg);

    }

    private void doProcess_ZZVL0020Scrn00_PageNext(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_1.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + cMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_1, BigDecimal.valueOf(pagenationFrom + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_1, BigDecimal.valueOf(pagenationFrom + cMsg.C.getValidCount()));
    }

    private void doProcess_ZZVL0020Scrn00_PagePrev(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_1.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + cMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_1, BigDecimal.valueOf(pagenationFrom));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_1, BigDecimal.valueOf(pagenationFrom + cMsg.C.getValidCount() - 1));

    }

    private void doProcess_ZZVL0020Scrn00_TBLColumnSort(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_1.getValue();
        String sortItemNm = cMsg.xxSortItemNm_1.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_1.getValue();

        // Table:C
        if ("C".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.C, sMsg.C.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.C.getValidCount());

            // copy(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_1, BigDecimal.valueOf(cMsg.C.getValidCount()));
        }

    }

    private void doProcess_ZZVL0020Scrn00_CMN_Clear(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        cMsg.B.clear();
        cMsg.B.setValidCount(0);

        String sGlobalCpyCd = cMsg.glblCmpyCd_BK.getValue();
        if (ZZVL0020CommonLogic.doATableSet(sGlobalCpyCd, cMsg , sMsg) == false) {
            return;
        }

    }

    private void doProcess_ZZVL0020Scrn00_CMN_Reset(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, cMsg.glblCmpyCd_1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_BK, cMsg.glblCmpyCd_1.getValue());
        ZZVL0020CommonLogic.doInit(cMsg.glblCmpyCd_1.getValue(), cMsg, sMsg);

    }

    private void doProcess_ZZVL0020Scrn01_CMN_Return(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("rowNum", sMsg.C.length() + 1);
        param.put("cMsg", cMsg);

        S21SsmEZDResult ssmResult = ZZVL0020Query.getInstance().getRoleList(param, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.C.length();
            }

            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_1, BigDecimal.valueOf(cMsg.C.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_1, BigDecimal.valueOf(queryResCnt));

        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum_1.clear();
            cMsg.xxPageShowToNum_1.clear();
            cMsg.xxPageShowOfNum_1.clear();
        }

    }

    private void doProcess_ZZVL0020Scrn01_Left(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        if (chkNoInput(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {ZZVL0020Constant.MY_PROCESS_CHECK_BOX});
            return;
        }

        removeB(cMsg);

        String sGlobalCpyCd = cMsg.glblCmpyCd_BK.getValue();
        if (ZZVL0020CommonLogic.doATableSet(sGlobalCpyCd, cMsg , sMsg) == false) {
            return;
        }

    }

    private void doProcess_ZZVL0020Scrn01_Right(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        copyCmsgToSmsg(cMsg, sMsg);

        // Not input Check
        if (chkNoInput(sMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {ZZVL0020Constant.ACTIVE_PROCESS_CHECK_BOX});
            return;
        }
        // Max Check
        if (chkMax(cMsg, sMsg) == false) {
            String sMaxcount = String.valueOf(cMsg.B.length());
            cMsg.setMessageInfo("ZZZM9011E", new String[] {sMaxcount});
            return;
         }

        moveAtoB(cMsg, sMsg);

        String sGlobalCpyCd = cMsg.glblCmpyCd_BK.getValue();
        if (ZZVL0020CommonLogic.doATableSet(sGlobalCpyCd, cMsg , sMsg) == false) {
            return;
        }

    }

    private void doProcess_ZZVL0020Scrn01_PageNext(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A1, BigDecimal.valueOf(pagenationFrom + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A1, BigDecimal.valueOf(pagenationFrom + cMsg.A.getValidCount()));
    }

    private void doProcess_ZZVL0020Scrn01_PagePrev(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A1.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A1, BigDecimal.valueOf(pagenationFrom));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A1, BigDecimal.valueOf(pagenationFrom + cMsg.A.getValidCount() - 1));

    }

    private void doProcess_ZZVL0020Scrn01_TBLColumnSort(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_A1.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A1.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_A1.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A1, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A1, BigDecimal.valueOf(cMsg.A.getValidCount()));
        }

    }

    /**
     * Method name: copyCmsgToSmsg
     * <dd>The method explanation: copy Check Box from CMsg onto SMsg
     * <dd>Remarks:
     * @param cMsg ZZVL0020CMsg
     * @param sMsg ZZVL0020SMsg
     */
    private void copyCmsgToSmsg(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        // copy Check Box from CMsg onto SMsg
        int pagenation = cMsg.xxPageShowFromNum_A1.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(pagenation + i).xxChkBox_A1, cMsg.A.no(i).xxChkBox_A1.getValue());
            }
        }
    }

    /**
     * Method name: chkNoInput
     * <dd>The method explanation: Active Process No Input check. 
     * <dd>Remarks:
     * @param sMsg ZZVL0020SMsg
     * @return boolean true or false
     */
    private boolean chkNoInput(ZZVL0020SMsg sMsg) {

        boolean errFlg = true;

        // Check Box
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String sCheckBox = sMsg.A.no(i).xxChkBox_A1.getValue();
            if (sCheckBox.equals("Y")) {
                errFlg = false;
                break;
            }
        }

        return errFlg;
    }

    /**
     * Method name: chkNoInput
     * <dd>The method explanation: Active Process No Input check. 
     * <dd>Remarks:
     * @param cMsg ZZVL0020CMsg
     * @return boolean true or false
     */
    private boolean chkNoInput(ZZVL0020CMsg cMsg) {

        boolean errFlg = true;

        // Check Box
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String sCheckBox = cMsg.B.no(i).xxChkBox_B1.getValue();
            if (sCheckBox.equals("Y")) {
                errFlg = false;
                break;
            }
        }

        return errFlg;
    }

    /**
     * Method name: chkSingleInput
     * <dd>The method explanation: My Process Single Input check. 
     * <dd>Remarks:
     * @param cMsg ZZVL0020CMsg
     * @return boolean true or false
     */
    public static boolean chkSingleInput(ZZVL0020CMsg cMsg) {

        boolean errFlg = true;
        int mCount = 0;

        // My Process Check Box
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String sCheckBox = cMsg.B.no(i).xxChkBox_B1.getValue();
            if (sCheckBox.equals("Y")) {
                mCount++;
            }
        }

        // OK
        if (mCount == 1) {
            errFlg = false;
        }
        return errFlg;
    }

    /**
     * Method name: chkMax
     * <dd>The method explanation: Active Process Max Input check. 
     * <dd>Remarks:
     * @param cMsg ZZVL0020CMsg
     * @param sMsg ZZVL0020SMsg
     * @return boolean true or false
     */
    private boolean chkMax(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        int iAcount = 0;
        int iTotalcount = 0;

        // Check Box
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String sCheckBox = sMsg.A.no(i).xxChkBox_A1.getValue();
            if (sCheckBox.equals("Y")) {
                iAcount++;
            }
        }

        iTotalcount = iAcount + cMsg.B.getValidCount();

        if (iTotalcount > cMsg.B.length()) {
            return false;
        }

        return true;
    }

    /**
     * Method name: moveAtoB
     * <dd>The method explanation: move [A]table to [B]Table
     * <dd>Remarks:
     * @param cMsg ZZVL0020CMsg
     * @param sMsg ZZVL0020SMsg
     * @return boolean true or false
     */
    private boolean moveAtoB(ZZVL0020CMsg cMsg, ZZVL0020SMsg sMsg) {

        int intBcount = cMsg.B.getValidCount();

        // Move [A]table of sMsg  to [B]Table of cMsg and Backup of [A]Table of sMsg
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String sCheckBox = sMsg.A.no(i).xxChkBox_A1.getValue();
            if (sCheckBox.equals("Y")) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(intBcount).menuProcNm_B1, sMsg.A.no(i).menuProcNm_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(intBcount).upTabNm_B1, sMsg.A.no(i).upTabNm_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(intBcount).bizAppNm_B1, sMsg.A.no(i).bizAppNm_A1.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(intBcount).upTabCd_B1, sMsg.A.no(i).upTabCd_A1.getValue());

                intBcount++;
            }
        }
        cMsg.B.setValidCount(intBcount);

        return true;
    }

    /**
     * Method name: removeB
     * <dd>The method explanation: remove [B]table
     * <dd>Remarks:
     * @param cMsg ZZVL0020CMsg
     * @return boolean true or false
     */
    private boolean removeB(ZZVL0020CMsg cMsg) {

        int intBcount = 0;

        String[] strMenuProcNm   = new String[cMsg.B.getValidCount()];
        String[] strUpTabNm      = new String[cMsg.B.getValidCount()];
        String[] strBizAppNm     = new String[cMsg.B.getValidCount()];
        String[] strUpTabCd      = new String[cMsg.B.getValidCount()];
        String[] strErrFlg       = new String[cMsg.B.getValidCount()];

        // Backup to [B]table of cMsg
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String sCheckBox = cMsg.B.no(i).xxChkBox_B1.getValue();
            if (!sCheckBox.equals("Y")) {
                strMenuProcNm[intBcount]   = cMsg.B.no(i).menuProcNm_B1.getValue();
                strUpTabNm[intBcount]      = cMsg.B.no(i).upTabNm_B1.getValue();
                strBizAppNm[intBcount]     = cMsg.B.no(i).bizAppNm_B1.getValue();
                strUpTabCd[intBcount]      = cMsg.B.no(i).upTabCd_B1.getValue();
                strErrFlg[intBcount]       = cMsg.B.no(i).xxErrFlg_B1.getValue();

                intBcount++;
            }
        }

        // [B]table of cMsg Reset
        cMsg.B.clear();
        for (int i = 0; i < intBcount; i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).menuProcNm_B1, strMenuProcNm[i]);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).upTabNm_B1, strUpTabNm[i]);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bizAppNm_B1, strBizAppNm[i]);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).upTabCd_B1, strUpTabCd[i]);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxErrFlg_B1, strErrFlg[i]);
        }
        cMsg.B.setValidCount(intBcount);

        return true;
    }

}
