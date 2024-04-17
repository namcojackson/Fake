/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6770;

import static business.blap.NMAL6770.constant.NMAL6770Constant.MULT_SEL_MODE_CD;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6770.constant.NMAL6770Constant;
import business.db.CTAC_TPTMsg;
import business.db.CTAC_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_TTL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/23   Fujitsu         M.Nakamura      Create          N/A
 * 2015/03/31   Fujitsu         S.Tsunaki       Update          N/A
 * 2015/10/01   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#2041
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 * 2017/10/17   Fujitsu         K.Ishizuka      Update          QC#20249(Sol#454)
 * 2017/12/06   Fujitsu         Hd.Sugawara     Update          QC#21897
 * 2018/02/28   Fujitsu         A.Kosai         Update          QC#21075
 * 2018/06/29   Fujitsu         T.Murai         Update          QC#26794
 * 2018/10/11   Fujitsu         T.Noguchi       Update          QC#27869
 *</pre>
 */
public class NMAL6770BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            // QC#7781
            cMsg.setCommitSMsg(true);

            if ("NMAL6770_INIT".equals(screenAplID)) {
                doProcess_NMAL6770_INIT((NMAL6770CMsg) cMsg, (NMAL6770SMsg) sMsg);
            } else if ("NMAL6770Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL6770Scrn00_Search((NMAL6770CMsg) cMsg, (NMAL6770SMsg) sMsg);
            } else if ("NMAL6770Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6770Scrn00_PageNext((NMAL6770CMsg) cMsg, (NMAL6770SMsg) sMsg);
            } else if ("NMAL6770Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6770Scrn00_PagePrev((NMAL6770CMsg) cMsg, (NMAL6770SMsg) sMsg);
            } else if ("NMAL6770Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL6770Scrn00_CMN_Clear((NMAL6770CMsg) cMsg, (NMAL6770SMsg) sMsg);
            } else if ("NMAL6770Scrn00_SelectContactId".equals(screenAplID)) {
                doProcess_NMAL6770Scrn00_SelectContactId((NMAL6770CMsg) cMsg, (NMAL6770SMsg) sMsg);
                // QC#7781
            } else if ("NMAL6770Scrn00_AddSelected".equals(screenAplID)) {
                doProcess_NMAL6770Scrn00_AddSelected((NMAL6770CMsg) cMsg, (NMAL6770SMsg) sMsg);
            } else if ("NMAL6770Scrn00_DeleteSelected".equals(screenAplID)) {
                doProcess_NMAL6770Scrn00_DeleteSelected((NMAL6770CMsg) cMsg, (NMAL6770SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6770Scrn00_Search(NMAL6770CMsg bizMsg, NMAL6770SMsg sMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL6770Query.getInstance().getContact(bizMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            NMAL6770_ASMsg aSMsg;
            NMAL6770_ACMsg aCMsg;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }

                aSMsg = sMsg.A.no(i);
                aCMsg = bizMsg.A.no(i);
                EZDMsg.copy(aSMsg, null, aCMsg, null);
                if (ZYPCommonFunc.hasValue(aSMsg.dsAcctNm_A2)) {
                    ZYPEZDItemValueSetter.setValue(aCMsg.dsAcctNm_A1, aSMsg.dsAcctNm_A2);
                }
                if (ZYPCommonFunc.hasValue(aSMsg.dsAcctNum_A2)) {
                    ZYPEZDItemValueSetter.setValue(aCMsg.dsAcctNum_A1, aSMsg.dsAcctNum_A2);
                }
            }
            bizMsg.A.setValidCount(i);

            // Set page number
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }

    }

    private void doProcess_NMAL6770Scrn00_SelectContactId(NMAL6770CMsg bizMsg, NMAL6770SMsg sMsg) {

        bizMsg.P.clear();
        bizMsg.P.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL6770Query.getInstance().getDsCtacPnt(bizMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            int i = 0;
            for (; i < sMsg.P.getValidCount(); i++) {
                if (i == bizMsg.P.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.P.no(i), null, bizMsg.P.no(i), null);
            }
            bizMsg.P.setValidCount(i);
        } else {
            return;
        }
    }

    /**
     * @param bizMsg NMAL6770CMsg
     * @param sMsg NMAL6770SMsg
     */
    private void doProcess_NMAL6770_INIT(NMAL6770CMsg bizMsg, NMAL6770SMsg sMsg) {

        createPullDown(bizMsg);

        // Add Start 2118/06/29 [QC#26794]
        // contact Type is only View able Type
        validateCtacTp(bizMsg);
        // Add End 2118/06/29 [QC#26794]

        // When the parameter is set.
        //QC#16452 mod Start
//        if (ZYPCommonFunc.hasValue(bizMsg.ctacTpCd_H1) || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H1) || ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1) || ZYPCommonFunc.hasValue(bizMsg.dsLocNm_H1) || ZYPCommonFunc.hasValue(bizMsg.locNum_H1)
//                || ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_H1) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_H1) || ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H1) || ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H2)
//                || ZYPCommonFunc.hasValue(bizMsg.dsCtacPsnTtlCd_H1) || ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H1) || ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H2)) {
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H1) || ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1) || ZYPCommonFunc.hasValue(bizMsg.dsLocNm_H1) || ZYPCommonFunc.hasValue(bizMsg.locNum_H1)
                || ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_H1) || ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_H1) || ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H1) || ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H2)
                || ZYPCommonFunc.hasValue(bizMsg.dsCtacPsnTtlCd_H1) || ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H1) || ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H2)) {
            doProcess_NMAL6770Scrn00_Search(bizMsg, sMsg);
        }
        //QC#16452 mod End

        // QC#7781
        if (MULT_SEL_MODE_CD.equals(bizMsg.xxModeCd_H1.getValue())) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                BigDecimal ctacPsnPk = bizMsg.B.no(i).ctacPsnPk_B1.getValue();
                boolean isFound = false;
                S21SsmEZDResult ssmResult = NMAL6770Query.getInstance().getContactDetail(bizMsg, ctacPsnPk);
                if (ssmResult.isCodeNormal()) {
                    List<Map> list = (List<Map>) ssmResult.getResultObject();
                    if (list.size() > 0) {
                        isFound = true;
                        Map map = list.get(0);

                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacPsnPk_B1, (BigDecimal) map.get("CTAC_PSN_PK"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacPsnNum_B1, (String) map.get("CTAC_PSN_NUM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsAcctNum_B1, (String) map.get("SELL_TO_CUST_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsAcctNm_B1, (String) map.get("DS_ACCT_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxDplyByItemNm_B1, (String) map.get("LINE_ADDR"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).locNum_B1, (String) map.get("LOC_NUM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsLocNm_B1, (String) map.get("DS_LOC_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacTpCd_B1, (String) map.get("CTAC_TP_CD"));
                        // Mod Start 2017/12/06 QC#21897
                        //ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacTpNm_B1, (String) map.get("CTAC_TP_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacTpDescTxt_B1, (String) map.get("CTAC_TP_DESC_TXT"));
                        // Mod End 2017/12/06 QC#21897
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacPsnFirstNm_B1, (String) map.get("CTAC_PSN_FIRST_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacPsnLastNm_B1, (String) map.get("CTAC_PSN_LAST_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPsnSaltNm_B1, (String) map.get("DS_CTAC_PSN_SALT_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPsnTtlCd_B1, (String) map.get("DS_CTAC_PSN_TTL_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPsnTtlNm_B1, (String) map.get("DS_CTAC_PSN_TTL_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntValTxt_B1, (String) map.get("PHONE"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPsnExtnNum_B1, (String) map.get("EXT"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntValTxt_B2, (String) map.get("EMAIL"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntValTxt_B3, (String) map.get("FAX"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntValTxt_B4, (String) map.get("ASSISTANT"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntValTxt_B5, (String) map.get("MOBILE"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntPk_B1, (BigDecimal) map.get("PHONE_PK"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntPk_B2, (BigDecimal) map.get("EMAIL_PK"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntPk_B3, (BigDecimal) map.get("FAX_PK"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsCtacPntTpNm_B1, (String) map.get("DS_CTAC_PNT_TP_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacPsnCmntTxt_B1, (String) map.get("CTAC_PSN_CMNT_TXT"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).billToCustCd_B1, (String) map.get("BILL_TO_CUST_CD"));
                        // 2018/02/28 S21_NA#21075 Add Start
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).shipToCustCd_B1, (String) map.get("SHIP_TO_CUST_CD"));
                        // 2018/02/28 S21_NA#21075 Add End
                    }
                }
                if (!isFound) {
                    String ctacPsnNum = bizMsg.B.no(i).ctacPsnPk_B1.getValue().toPlainString();
                    if (ctacPsnNum.length() <= bizMsg.B.no(i).getAttr("ctacPsnNum_B1").getDigit()) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ctacPsnNum_B1, ctacPsnNum);
                    }
                }
            }
        }
    }

    private void doProcess_NMAL6770Scrn00_PageNext(NMAL6770CMsg cMsg, NMAL6770SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = paginationFrom;
        for (; i < paginationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - paginationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - paginationFrom);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(paginationFrom + 1);
        cMsg.xxPageShowToNum.setValue(paginationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NMAL6770Scrn00_PagePrev(NMAL6770CMsg cMsg, NMAL6770SMsg sMsg) {
        // copy data from SMsg to CMsg
        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = paginationFrom;
        for (; i < paginationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - paginationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - paginationFrom);

        // set value to pagination items
        paginationFrom = paginationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(paginationFrom);
        cMsg.xxPageShowToNum.setValue(paginationFrom + cMsg.A.getValidCount() - 1);
    }

    private void doProcess_NMAL6770Scrn00_CMN_Clear(NMAL6770CMsg cMsg, NMAL6770SMsg sMsg) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.clear();

        cMsg.ctacTpCd_H1.clear();
        cMsg.dsCtacPntValTxt_H1.clear();
        cMsg.dsCtacPntValTxt_H2.clear();
        cMsg.ctacPsnFirstNm_H1.clear();
        cMsg.ctacPsnLastNm_H1.clear();
        if (!ZYPCommonFunc.hasValue(cMsg.I.no(0).xxDplyCtrlFlg_I1)) {
            cMsg.dsAcctNum_H1.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.I.no(1).xxDplyCtrlFlg_I1)) {
            cMsg.dsAcctNm_H1.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.I.no(2).xxDplyCtrlFlg_I1)) {
            cMsg.locNum_H1.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.I.no(3).xxDplyCtrlFlg_I1)) {
            cMsg.dsLocNm_H1.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.I.no(4).xxDplyCtrlFlg_I1)) {
            cMsg.billToCustCd_H1.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.I.no(5).xxDplyCtrlFlg_I1)) {
            cMsg.xxChkBox_H1.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.I.no(6).xxDplyCtrlFlg_I1)) {
            cMsg.xxChkBox_H2.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.I.no(7).xxDplyCtrlFlg_I1)) {
            cMsg.dsCtacPsnTtlCd_H1.clear();
        }
        cMsg.xxChkBox_H3.setValue(ZYPConstant.FLG_ON_Y);    //QC#16452 add
        // 2018/02/28 S21_NA#21075 Add Start
        cMsg.shipToCustCd_H1.clear();
        // 2018/02/28 S21_NA#21075 Add End
        doProcess_NMAL6770_INIT(cMsg, sMsg);
    }

    private void createPullDown(NMAL6770CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(DS_CTAC_PSN_TTL.class, cMsg.dsCtacPsnTtlCd_H2, cMsg.dsCtacPsnTtlDescTxt_H1);

        CTAC_TPTMsg ctacTpTMsg = new CTAC_TPTMsg();
        ctacTpTMsg.setSQLID("002");
        ctacTpTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        ctacTpTMsg.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
        ctacTpTMsg.setConditionValue("mstrViewAvalFlg01", ZYPConstant.FLG_ON_Y); // S21_NA ADD QC#20249(Sol#454)

        CTAC_TPTMsgArray ctacTpTMsgArray = (CTAC_TPTMsgArray) EZDTBLAccessor.findByCondition(ctacTpTMsg);
        if (ctacTpTMsgArray.length() == 0) {
            cMsg.setMessageInfo("NMAM8111E", new String[] {"CTAC_TP" });
            return;
        }

        for (int i = 0; i < ctacTpTMsgArray.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.ctacTpCd_H2.no(i), ctacTpTMsgArray.no(i).ctacTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.ctacTpDescTxt_H1.no(i), ctacTpTMsgArray.no(i).ctacTpDescTxt.getValue());
        }

        // 2018/10/11 QC#27869 Add Start
        S21SsmEZDResult ssmResult = NMAL6770Query.getInstance().getSvcCatgTpList();

        if (ssmResult.isCodeNormal()) {
            List<Map> list = (List<Map>) ssmResult.getResultObject();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = list.get(i);
                    ZYPEZDItemValueSetter.setValue(cMsg.ctacTpCd_H2.no(i + ctacTpTMsgArray.getValidCount()), (String) map.get("SVC_CTAC_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctacTpDescTxt_H1.no(i + ctacTpTMsgArray.getValidCount()), (String) map.get("SVC_CTAC_TP_DESC_TXT"));
                }
            } else {
                cMsg.setMessageInfo("NMAM8111E", new String[] {"SVC_CTAC_TP" });
            }
        } else {
            cMsg.setMessageInfo("NMAM8111E", new String[] {"SVC_CTAC_TP" });
        }
        // 2018/10/11 QC#27869 Add End
    }

    // QC#7781
    private void doProcess_NMAL6770Scrn00_AddSelected(NMAL6770CMsg cMsg, NMAL6770SMsg sMsg) {
        // save check box input to SMsg
        updateGlblMsg(cMsg, sMsg);

        // check duplicate
        int firstErrorRowIndex = -1;
        List<Integer> listSelected = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        for (int selectedIndex : listSelected) {
            String ctacPsnPk = sMsg.A.no(selectedIndex).ctacPsnPk_A1.getValue().toPlainString();
            if (existInSelectedRecords(cMsg, ctacPsnPk)) {
                sMsg.A.no(selectedIndex).xxChkBox_A1.setErrorInfo(1, NMAL6770Constant.NMAM0072E, new String[] {"Contact" });
                firstErrorRowIndex = selectedIndex;
            }
        }
        if (firstErrorRowIndex >= 0) {
            cMsg.xxPageShowFromNum.setValue(firstErrorRowIndex + 1);
            loadOnePageToCMsg(cMsg, cMsg.A, sMsg.A);
            return;
        }

        // check max selected rows
        if (listSelected.size() == 0) {
            cMsg.setMessageInfo(NMAL6770Constant.NMAM8461E, new String[] {"Contact" });
            return;
        } else if (listSelected.size() + cMsg.B.getValidCount() > cMsg.B.length()) {
            cMsg.setMessageInfo(NMAL6770Constant.NMAM8187E, new String[] {"Selected Row", Integer.toString(cMsg.B.length()) });
            return;
        }

        // add selected rows to bottom list
        for (int i = 0; i < listSelected.size(); i++) {
            int upperListRowIndex = listSelected.get(i);
            int bottomListRowIndex = cMsg.B.getValidCount();
            EZDMsg.copy(sMsg.A.no(upperListRowIndex), "A1", cMsg.B.no(bottomListRowIndex), "B1");
            EZDMsg.copy(sMsg.A.no(upperListRowIndex), "A2", cMsg.B.no(bottomListRowIndex), "B2");
            EZDMsg.copy(sMsg.A.no(upperListRowIndex), "A3", cMsg.B.no(bottomListRowIndex), "B3");
            EZDMsg.copy(sMsg.A.no(upperListRowIndex), "A4", cMsg.B.no(bottomListRowIndex), "B4");
            EZDMsg.copy(sMsg.A.no(upperListRowIndex), "A5", cMsg.B.no(bottomListRowIndex), "B5");
            sMsg.A.no(upperListRowIndex).xxChkBox_A1.clear();
            cMsg.B.setValidCount(bottomListRowIndex + 1);
        }

        // refresh current page
        loadOnePageToCMsg(cMsg, cMsg.A, sMsg.A);
    }

    private boolean existInSelectedRecords(NMAL6770CMsg cMsg, String ctacPsnPk) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (ctacPsnPk.equals(cMsg.B.no(i).ctacPsnPk_B1.getValue().toPlainString())) {
                return true;
            }
        }
        return false;
    }

    private void doProcess_NMAL6770Scrn00_DeleteSelected(NMAL6770CMsg cMsg, NMAL6770SMsg sMsg) {
        List<Integer> listSelected = ZYPTableUtil.getSelectedRows(cMsg.B, "xxChkBox_B1", ZYPConstant.CHKBOX_ON_Y);
        if (listSelected.size() == 0) {
            cMsg.setMessageInfo(NMAL6770Constant.NMAM8461E, new String[] {"Contact" });
            return;
        }
        ZYPTableUtil.deleteRows(cMsg.B, listSelected);
    }

    private void updateGlblMsg(NMAL6770CMsg bizMsg, NMAL6770SMsg glblMsg) {
        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    private void loadOnePageToCMsg(NMAL6770CMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {
        NMAL6770CMsg bizMsg = (NMAL6770CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;
        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;
                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    // Add Start 2018/06/29 QC#26794
    private void validateCtacTp(NMAL6770CMsg cMsg) {

        String curCtacTpCd = cMsg.ctacTpCd_H1.getValue();
        for (int i = 0; i < cMsg.ctacTpCd_H2.length(); i++) {
            if (cMsg.ctacTpCd_H2.no(i).getValue().equals(curCtacTpCd)) {
                return;
            }
        }
        cMsg.ctacTpCd_H1.clear();
    }
    // Add End 2018/06/29 QC#26794

}
