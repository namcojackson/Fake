/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.NYEL0020;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NYEL0020.common.NYEL0020CommonLogic;
import business.blap.NYEL0020.constant.NYEL0020Constant;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;


public class NYEL0020BL02 extends S21BusinessHandler implements NYEL0020Constant {


    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NYEL0020_INIT".equals(screenAplID)) {
                doProcess_NYEL0020_INIT((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            } else if ("NYEL0020Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_CMN_Clear((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            } else if ("NYEL0020Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_CMN_Reset((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            } else if ("NYEL0020_ZZSL1110".equals(screenAplID)) {
                doProcess_NYEL0020_ZZSL1110((NYEL0020CMsg) cMsg);
            } else if ("NYEL0020Scrn00_Search".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_Search((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            } else if ("NYEL0020Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_PageNext((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            } else if ("NYEL0020Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_PagePrev((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            } else if ("NYEL0020Scrn00_Right".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_Right((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            } else if ("NYEL0020Scrn00_Left".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_Left((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            } else if ("NYEL0020Scrn00_Up".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_Up((NYEL0020CMsg) cMsg);
            } else if ("NYEL0020Scrn00_Down".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_Down((NYEL0020CMsg) cMsg);
            } else if ("NYEL0020Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NYEL0020Scrn00_TBLColumnSort((NYEL0020CMsg) cMsg, (NYEL0020SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NYEL0020_INIT(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        String sGlobalCpyCd = getGlobalCompanyCode();

        cMsg.glblCmpyCd.setValue(sGlobalCpyCd);
        cMsg.glblCmpyCd_BK.setValue(sGlobalCpyCd);
        NYEL0020CommonLogic.doInit(sGlobalCpyCd, cMsg, sMsg);

    }

    private void doProcess_NYEL0020_ZZSL1110(NYEL0020CMsg cMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();

        if (NYEL0020CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

    }


    private void doProcess_NYEL0020Scrn00_Search(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd.getValue();
        cMsg.glblCmpyCd_BK.setValue(cMsg.glblCmpyCd.getValue());
        NYEL0020CommonLogic.doInit(sGlobalCpyCd, cMsg, sMsg);

    }


    private void doProcess_NYEL0020Scrn00_PageNext(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        copyCmsgToSmsg(cMsg, sMsg);

        // set values to items of pagenation for next page pagenation
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowToNum.getValueInt();
        int i              = pagenationFrom;
        for ( ; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }


    private void doProcess_NYEL0020Scrn00_PagePrev(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        copyCmsgToSmsg(cMsg, sMsg);

        // set values to items of pagenation for prev page
        cMsg.A.clear();
        cMsg.A.setValidCount(0);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        int i              = pagenationFrom;
        for ( ; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pagenationFrom);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount() - 1);
    }


    private void doProcess_NYEL0020Scrn00_Right(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        copyCmsgToSmsg(cMsg, sMsg);

        // Not input Check
        if (chkNoInput(sMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {ACTIVE_PROCESS_CHECK_BOX});
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
        if (NYEL0020CommonLogic.doATableSet(sGlobalCpyCd, cMsg , sMsg) == false) {
            return;
        }

    }

    private void doProcess_NYEL0020Scrn00_Left(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        if (chkNoInput(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {MY_PROCESS_CHECK_BOX});
            return;
        }

        removeB(cMsg);

        String sGlobalCpyCd = cMsg.glblCmpyCd_BK.getValue();
        if (NYEL0020CommonLogic.doATableSet(sGlobalCpyCd, cMsg , sMsg) == false) {
            return;
        }

    }

    private void doProcess_NYEL0020Scrn00_Up(NYEL0020CMsg cMsg) {

        // Not input Check
        if (chkNoInput(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {MY_PROCESS_CHECK_BOX});
            return;
        }
        // Single input Check
        if (chkSingleInput(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9008E");
            return;
        }
        // Up
        if (moveUpDown(cMsg, 0) == true) {
            // No Error
            return;
        }

    }

    private void doProcess_NYEL0020Scrn00_Down(NYEL0020CMsg cMsg) {

        // Not input Check
        if (chkNoInput(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9007E", new String[] {MY_PROCESS_CHECK_BOX});
            return;
        }

        // Single input Check
        if (chkSingleInput(cMsg) == true) {
            cMsg.setMessageInfo("ZZZM9008E");
            return;
        }
        // Down
        if (moveUpDown(cMsg, 1) == true) {
            // No Error
            return;
        }

    }

    private void doProcess_NYEL0020Scrn00_TBLColumnSort(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            if (cMsg.A.getValidCount() > 0) {

                // SMsg -> CMsg
                int i = 0;
                for (; i < sMsg.A.getValidCount(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                cMsg.xxPageShowFromNum.setValue(1);
                cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            }
        }

    }


    private void doProcess_NYEL0020Scrn00_CMN_Clear(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        cMsg.B.clear();
        cMsg.B.setValidCount(0);

        String sGlobalCpyCd = cMsg.glblCmpyCd_BK.getValue();
        if (NYEL0020CommonLogic.doATableSet(sGlobalCpyCd, cMsg , sMsg) == false) {
            return;
        }

    }


    private void doProcess_NYEL0020Scrn00_CMN_Reset(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {
        
        String sGlobalCpyCd = getGlobalCompanyCode();

        cMsg.glblCmpyCd.setValue(sGlobalCpyCd);
        cMsg.glblCmpyCd_BK.setValue(sGlobalCpyCd);
        NYEL0020CommonLogic.doInit(sGlobalCpyCd, cMsg, sMsg);
    }

    /**
     * Method name: copyCmsgToSmsg
     * <dd>The method explanation: copy Check Box from CMsg onto SMsg
     * <dd>Remarks:
     * @param cMsg NYEL0020CMsg
     * @param sMsg NYEL0020SMsg
     */
    private void copyCmsgToSmsg(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        // copy Check Box from CMsg onto SMsg
        int pagenation = cMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {
                sMsg.A.no(pagenation + i).xxChkBox_A1.setValue(cMsg.A.no(i).xxChkBox_A1.getValue());
            }
        }
    }

    /**
     * Method name: chkNoInput
     * <dd>The method explanation: Active Process No Input check. 
     * <dd>Remarks:
     * @param sMsg NYEL0020SMsg
     * @return boolean true or false
     */
    private boolean chkNoInput(NYEL0020SMsg sMsg) {

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
     * @param cMsg NYEL0020CMsg
     * @return boolean true or false
     */
    private boolean chkNoInput(NYEL0020CMsg cMsg) {

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
     * @param cMsg NYEL0020CMsg
     * @return boolean true or false
     */
    public static boolean chkSingleInput(NYEL0020CMsg cMsg) {

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
     * @param cMsg NYEL0020CMsg
     * @param sMsg NYEL0020SMsg
     * @return boolean true or false
     */
    private boolean chkMax(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

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
     * @param cMsg NYEL0020CMsg
     * @param sMsg NYEL0020SMsg
     * @return boolean true or false
     */
    private boolean moveAtoB(NYEL0020CMsg cMsg, NYEL0020SMsg sMsg) {

        int intBcount = cMsg.B.getValidCount();

        // Move [A]table of sMsg  to [B]Table of cMsg and Backup of [A]Table of sMsg
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String sCheckBox = sMsg.A.no(i).xxChkBox_A1.getValue();
            if (sCheckBox.equals("Y")) {
                cMsg.B.no(intBcount).menuProcNm_B1.setValue(sMsg.A.no(i).menuProcNm_A1.getValue());
                cMsg.B.no(intBcount).upTabNm_B1.setValue(sMsg.A.no(i).upTabNm_A1.getValue());
                cMsg.B.no(intBcount).bizAppNm_B1.setValue(sMsg.A.no(i).bizAppNm_A1.getValue());
                cMsg.B.no(intBcount).upTabCd_B1.setValue(sMsg.A.no(i).upTabCd_A1.getValue());

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
     * @param cMsg NYEL0020CMsg
     * @return boolean true or false
     */
    private boolean removeB(NYEL0020CMsg cMsg) {

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
            cMsg.B.no(i).menuProcNm_B1.setValue(strMenuProcNm[i]);
            cMsg.B.no(i).upTabNm_B1.setValue(strUpTabNm[i]);
            cMsg.B.no(i).bizAppNm_B1.setValue(strBizAppNm[i]);
            cMsg.B.no(i).upTabCd_B1.setValue(strUpTabCd[i]);
            cMsg.B.no(i).xxErrFlg_B1.setValue(strErrFlg[i]);
        }
        cMsg.B.setValidCount(intBcount);

        return true;
    }

    /**
     * Method name: moveUpDown
     * <dd>The method explanation: Up Down from B_Table 
     * <dd>Remarks:
     * @param cMsg NYEL0020CMsg
     * @param UpDown int
     * @return boolean true or false
     */
    private boolean moveUpDown(NYEL0020CMsg cMsg, int upDown) {

        int intBcount = 0;
        String strMenuProcNm   = "";
        String strUpTabNm      = "";
        String strBizAppNm     = "";
        String strUpTabCd      = "";
        String strErrFlg       = "";

        boolean errFlg = true;

        // Backup of [B]Table of cMsg
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String sCheckBox = cMsg.B.no(i).xxChkBox_B1.getValue();
            if (sCheckBox.equals("Y")) {
                // Up
                if (upDown == 0) {
                    intBcount = i - 1;
                }
                // Down
                if (upDown == 1) {
                    intBcount = i + 1;
                }
                if ((intBcount >= 0) && (intBcount < cMsg.B.getValidCount())) {

                    // Backup
                    strMenuProcNm   = cMsg.B.no(intBcount).menuProcNm_B1.getValue();
                    strUpTabNm      = cMsg.B.no(intBcount).upTabNm_B1.getValue();
                    strBizAppNm     = cMsg.B.no(intBcount).bizAppNm_B1.getValue();
                    strUpTabCd      = cMsg.B.no(intBcount).upTabCd_B1.getValue();
                    strErrFlg       = cMsg.B.no(intBcount).xxErrFlg_B1.getValue();

                    // Copy 
                    cMsg.B.no(intBcount).xxChkBox_B1.setValue(cMsg.B.no(i).xxChkBox_B1.getValue());
                    cMsg.B.no(intBcount).menuProcNm_B1.setValue(cMsg.B.no(i).menuProcNm_B1.getValue());
                    cMsg.B.no(intBcount).upTabNm_B1.setValue(cMsg.B.no(i).upTabNm_B1.getValue());
                    cMsg.B.no(intBcount).bizAppNm_B1.setValue(cMsg.B.no(i).bizAppNm_B1.getValue());
                    cMsg.B.no(intBcount).upTabCd_B1.setValue(cMsg.B.no(i).upTabCd_B1.getValue());
                    cMsg.B.no(intBcount).xxErrFlg_B1.setValue(cMsg.B.no(i).xxErrFlg_B1.getValue());

                    cMsg.B.no(i).xxChkBox_B1.setValue("");
                    cMsg.B.no(i).menuProcNm_B1.setValue(strMenuProcNm);
                    cMsg.B.no(i).upTabNm_B1.setValue(strUpTabNm);
                    cMsg.B.no(i).bizAppNm_B1.setValue(strBizAppNm);
                    cMsg.B.no(i).upTabCd_B1.setValue(strUpTabCd);
                    cMsg.B.no(i).xxErrFlg_B1.setValue(strErrFlg);

                    errFlg = false;
                }

                break;
            }
        }

        return errFlg;

    }


}
