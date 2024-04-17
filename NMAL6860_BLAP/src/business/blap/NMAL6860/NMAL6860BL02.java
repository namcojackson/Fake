/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6860;

import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_CMN_CLEAR;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_CMN_RESET;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_CMN_SUBMIT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_GET_ADDRESS;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_INIT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_ON_CLICK_ADD_CONTACT_INFO;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_ON_CLICK_ADD_SUPPLIER_SITE;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_ON_CLICK_LIABILITYACCOUNT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_OPEN_WIN_PREPAYACCOUNT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_OPEN_WIN_VENDORRETURNACCOUNT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_SELECT_NWAL1130;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_TAB_DETAIL;
import static business.blap.NMAL6860.constant.NMAL6860Constant.EVENT_NM_TAB_GENERAL;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MAX_LOC_NM_LENGTH;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NEW_SUPPLIER;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM0038I;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM0039E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM8114E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM8675I;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NZZM0000E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NZZM0001W;
import static business.blap.NMAL6860.constant.NMAL6860Constant.TAB_NM_DETAIL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6860.common.NMAL6860CommonLogic;
import business.db.PRNT_VND_TPTMsg;
import business.parts.NMZC610001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_DEPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_TTL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INC_TAX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_MATCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MNRTY_OWND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PAY_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_ORG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRSMT_METH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NMAL6860 Supplier Entry.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 * 08/09/2016   CITS            S.Endo          Update          QC#10839
 * 09/06/2016   CITS            T.Gotoda        Update          QC#13264
 * 09/15/2016   CITS            T.Gotoda        Update          QC#13313
 * 09/26/2016   CITS            T.Gotoda        Update          QC#13163
 * 10/03/2016   CITS            R.Shimamoto     Update          QC#12768
 * 02/09/2017   CITS            R.Shimamoto     Update          QC#16594
 * 02/20/2017   Fujitsu         T.Murai         Update          QC#16081
 * 02/15/2018   CITS            T.Gotoda        Update          QC#22054
 * 06/11/2018   CITS            K.Ogino         Update          QC#26498
 * 2018/08/07   CITS            K.Ogino         Update          QC#27280
 * 2019/12/23   Fujitsu         R.Nakamura      Update          QC#54971-1
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 * 2021/09/28   CITS            S.Go            Update          QC#57815
 *</pre>
 */
public class NMAL6860BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String scrnAplId = cMsg.getScreenAplID();
            String glblCmpyCd = getGlobalCompanyCode();

            NMAL6860CMsg bizMsg = (NMAL6860CMsg) cMsg;
            NMAL6860SMsg glblMsg = (NMAL6860SMsg) sMsg;

            if (EVENT_NM_INIT.equals(scrnAplId)) {
                doProcessInit(bizMsg, glblMsg);
                doProcessSearch(bizMsg, glblMsg);
            } else if (EVENT_NM_ON_CLICK_ADD_SUPPLIER_SITE.equals(scrnAplId)) {
                doProcessAddSupplierSite(bizMsg, glblMsg, glblCmpyCd);
            } else if (EVENT_NM_ON_CLICK_ADD_CONTACT_INFO.equals(scrnAplId)) {
                doProcessAddContactInfo(bizMsg, glblMsg);
            } else if (EVENT_NM_TAB_GENERAL.equals(scrnAplId)) {
                doProcessTabGeneral(bizMsg, glblMsg, glblCmpyCd);
            } else if (EVENT_NM_TAB_DETAIL.equals(scrnAplId)) {
                doProcessTabDetail(bizMsg, glblMsg, glblCmpyCd);
            } else if (EVENT_NM_CMN_SUBMIT.equals(scrnAplId)) {
                // Add Start 2019/12/23 QC#54971-1
                String xxDplyTab = bizMsg.xxDplyTab.getValue();
                // Add End 2019/12/23 QC#54971-1
                // START 2021/03/01 G.Delgado [QC#56057,ADD]
                BigDecimal xxRadioBtnA = bizMsg.xxRadioBtn_A.getValue();
                // END 2021/03/01 G.Delgado [QC#56057,ADD]

                doProcessClear(bizMsg, glblMsg);
                doProcessInit(bizMsg, glblMsg);
                doProcessSearch(bizMsg, glblMsg);

                // Add Start 2019/12/23 QC#54971-1
                if (S21StringUtil.isEquals(xxDplyTab, TAB_NM_DETAIL)) {
                    // START 2021/03/01 G.Delgado [QC#56057,ADD]
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_A, xxRadioBtnA);
                    // END 2021/03/01 G.Delgado [QC#56057,ADD]
                    doProcessTabDetail(bizMsg, glblMsg, glblCmpyCd);
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, xxDplyTab);
                // Add End 2019/12/23 QC#54971-1
            } else if (EVENT_NM_CMN_CLEAR.equals(scrnAplId)) {
                doProcessClear(bizMsg, glblMsg);
                doProcessInit(bizMsg, glblMsg);
                // QC#27280
                String prntVndTpCd = ZYPCodeDataUtil.getVarCharConstValue("INTERNAL_PRNT_VND_TP_CD", bizMsg.glblCmpyCd.getValue());
                // 2020/02/28 QC#55971 Mod Start
                if (ZYPCommonFunc.hasValue(prntVndTpCd)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpCd, prntVndTpCd);
                    PRNT_VND_TPTMsg tMsg = new PRNT_VND_TPTMsg();
                    tMsg.glblCmpyCd.setValue(glblCmpyCd);
                    tMsg.prntVndTpCd.setValue(bizMsg.prntVndTpCd.getValue());
                    tMsg = (PRNT_VND_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpDescTxt, tMsg.prntVndTpDescTxt);
                }
                // 2020/02/28 QC#55971 Mod End
            } else if (EVENT_NM_CMN_RESET.equals(scrnAplId)) {
                doProcessClear(bizMsg, glblMsg);
                doProcessInit(bizMsg, glblMsg);
                doProcessSearch(bizMsg, glblMsg);
            } else if (EVENT_NM_GET_ADDRESS.equals(scrnAplId)) {
                doProcessGetAddress(bizMsg, glblMsg);
            } else if (EVENT_NM_ON_CLICK_LIABILITYACCOUNT.equals(scrnAplId)) {
                openWinLiabilityAccountCheck(cMsg, sMsg, glblCmpyCd);
            } else if (EVENT_NM_OPEN_WIN_PREPAYACCOUNT.equals(scrnAplId)) {
                openWinPrePayAccountCheck(cMsg, sMsg, glblCmpyCd);
            } else if (EVENT_NM_OPEN_WIN_VENDORRETURNACCOUNT.equals(scrnAplId)) {
                openWinVendorReturnAccountCheck(cMsg, sMsg, glblCmpyCd);
            // 2020/02/28 QC#55971 Add Start
            } else if (EVENT_NM_SELECT_NWAL1130.equals(scrnAplId)) {
                doProcessSetVenderCd(bizMsg, glblMsg);
            // 2020/02/28 QC#55971 Add End
            } else {
                throw new S21AbendException("Illegal Screen Application Id. : " + scrnAplId);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcessGetAddress(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        int idx= bizMsg.xxNum_AD.getValueInt();

        // QC#22054 MOD START
        if (!CTRY.UNITED_STATES_OF_AMERICA.equals(bizMsg.A.no(idx).ctryCd_A.getValue())) {
            bizMsg.setMessageInfo(NMAM8675I);
            return;
        }
        // QC#22054 MOD END

        List<Map<String, Object>> list = null;
        String postCd = bizMsg.A.no(idx).postCd_A.getValue(); 

        S21SsmEZDResult res = NMAL6860Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd);

        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            // Post Code xxxxx-yyyy => use xxxxx.
            if (postCd.length() > 5) {
                res = NMAL6860Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd.substring(0, 5));
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }

        if (list == null) {
            bizMsg.A.no(idx).postCd_A.setErrorInfo(1, NMAM0039E, new String[] {"Ship to Location Postal Code" });
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).ctyAddr_A, listCtyAddr.get(0));
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).stCd_A, listStCd.get(0));
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).cntyNm_A, listCntyNm.get(0));
            }
        }
    }

    /**
     * getDistinctValueList
     * @param list List<Map<String, Object>>
     * @param colNm String
     * @return listDistinct List<String>
     */
    private List<String> getDistinctValueList(List<Map<String, Object>> list, String colNm) {
        List<String> listDistinct = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String value = (String) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }
    /**
     * <p>
     * Initialization.
     * </p>
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcessInit(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        // set the global company code.
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());

        // create the pull-down lists.
        // QC#13313 Start
        // NMAL6860CommonLogic.createPullDownPrntVndTp(bizMsg); // 2020/02/28 QC#55971 Del
        // QC#13313 End
        NMAL6860CommonLogic.createPulldownList(MNRTY_OWND_TP.class, bizMsg.mnrtyOwndTpCd_L, bizMsg.mnrtyOwndTpDescTxt_L);
        NMAL6860CommonLogic.createPulldownList(SPLY_ORG_TP.class, bizMsg.splyOrgTpCd_L, bizMsg.splyOrgTpDescTxt_L);
        NMAL6860CommonLogic.createPulldownList(VND_PMT_METH.class, bizMsg.vndPmtMethCd_L, bizMsg.vndPmtMethDescTxt_L);
        NMAL6860CommonLogic.createPulldownList(PAY_GRP.class, bizMsg.payGrpCd_L, bizMsg.payGrpDescTxt_L);
        NMAL6860CommonLogic.createPulldownList(INC_TAX_TP.class, bizMsg.incTaxTpCd_L, bizMsg.incTaxTpDescTxt_L);

        NMAL6860CommonLogic.createPulldownList(INV_MATCH_TP.class, bizMsg.invMatchTpCd_L, bizMsg.invMatchTpDescTxt_L);
        NMAL6860CommonLogic.createPulldownList(TRSMT_METH_TP.class, bizMsg.trsmtMethTpCd_L, bizMsg.trsmtMethTpDescTxt_L);
        NMAL6860CommonLogic.createPulldownList(DS_CTAC_PSN_TTL.class, bizMsg.dsCtacPsnTtlCd_L, bizMsg.dsCtacPsnTtlDescTxt_L);
        NMAL6860CommonLogic.createPulldownList(DS_CTAC_PSN_DEPT.class, bizMsg.dsCtacPsnDeptCd_L, bizMsg.dsCtacPsnDeptDescTxt_L);
        // QC#13264 Start
        NMAL6860CommonLogic.createPullDownCtacTp(bizMsg);
        // QC#13264 End

        // Account Enabled Check
        NMAL6860CommonLogic.getAccountEnabled(bizMsg, getGlobalCompanyCode());
        // QC#27280
        String prntVndTpCd = ZYPCodeDataUtil.getVarCharConstValue("ARREFUND_PRNT_VND_TP_CD", bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpCd_DF, prntVndTpCd);

        EZDMsg.copy(bizMsg, null, glblMsg, null);
    }

    /**
     * <p>
     * Initial search.
     * </p>
     * @param bizMsg
     * @param glblMsg
     */
    @SuppressWarnings("unchecked")
    private void doProcessSearch(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.prntVndPk_P)) {

            // search Supplier and Supplier Sites
            searchSupplierAndSupplierSites(bizMsg, glblMsg);
            // search Contact Info
            searchContactInfo(bizMsg, glblMsg);

        } else if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_P) || ZYPCommonFunc.hasValue(bizMsg.billToCustCd_P)) {

            // 2017/02/20 QC#16081 Del Start
            // String prntVndTpCd = ZYPCodeDataUtil.getVarCharConstValue("ARREFUND_PRNT_VND_TP_CD", bizMsg.glblCmpyCd.getValue());

            // ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpCd, prntVndTpCd);
            // 2017/02/20 QC#16081 Del End

            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_H1, bizMsg.dsAcctNum_P); // 2017/02/20 QC#16081 ADD
            
            if (!ZYPCommonFunc.hasValue(bizMsg.billToCustCd_P)) {
                // get default bill to customer code
                NMZC610001PMsg custInfoGetApiPMsg = NMAL6860CommonLogic.callCustomerInfomationGetApi(bizMsg);

                if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
                    for (int i = 0; i < msgList.size(); i++) {
                        S21ApiMessage msg = msgList.get(i);
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        bizMsg.setMessageInfo(msgId, msgPrms);

                        if (msgId.endsWith("E")) {
                            bizMsg.setMessageInfo(NMAM0038I);
                            return;
                        }
                    }
                }

                if (ZYPCommonFunc.hasValue(custInfoGetApiPMsg.DefaultBillShipList.no(0).billToCustCd)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_P, custInfoGetApiPMsg.DefaultBillShipList.no(0).billToCustCd);
                } else {
                    bizMsg.setMessageInfo(NMAM0038I);
                    return;
                }
            }

            // Search Account Customer.
            S21SsmEZDResult result = NMAL6860Query.getInstance().searchAccountCustomer(bizMsg);
            Map acctCustInfo = null;
            if (result.isCodeNormal()) {
                acctCustInfo = (Map) result.getResultObject();
            }

            // search Default VendorInfo
            // QC#27280
            ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpCd, bizMsg.prntVndTpCd_DF);

            // START 2021/03/01 G.Delgado [QC#56057,ADD]
            PRNT_VND_TPTMsg prntVndTp = new PRNT_VND_TPTMsg();
            ZYPEZDItemValueSetter.setValue(prntVndTp.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prntVndTp.prntVndTpCd, bizMsg.prntVndTpCd_DF);
            prntVndTp = (PRNT_VND_TPTMsg) EZDTBLAccessor.findByKey(prntVndTp);

            if (prntVndTp != null && ZYPCommonFunc.hasValue(prntVndTp.prntVndTpDescTxt)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpDescTxt, prntVndTp.prntVndTpDescTxt);
            }
            // END 2021/03/01 G.Delgado [QC#56057,ADD]

            result = NMAL6860Query.getInstance().searchDefaultVendorInfo(bizMsg);
            Map defaultVndInfo = null;
            if (result.isCodeNormal()) {
                defaultVndInfo = (Map) result.getResultObject();
            }

            // Check exist Direct Sales Vendor record
            S21SsmEZDResult dsVnd = NMAL6860Query.getInstance().checkDirectSalesVendor(bizMsg);
            Map dsVndInfo = null;
            boolean dsVndExist = false;
            // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
            //if (dsVnd.isCodeNormal()) {
            if (dsVnd.isCodeNormal() && !(ZYPCommonFunc.hasValue(bizMsg.prntVndNm_DF) && bizMsg.prntVndNm_DF.getValue().equals(NEW_SUPPLIER))) {
            // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
                if (dsVnd.getQueryResultCount() > 0) {
                    dsVndInfo = (Map) dsVnd.getResultObject();
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndPk_P, (BigDecimal) dsVndInfo.get("PRNT_VND_PK"));

                    dsVndExist = true;
                }
            }

            if (dsVndExist) {

                searchSupplierAndSupplierSites(bizMsg, glblMsg);

                // checks if the result counts exceeds maximum counts.
                if (bizMsg.A.getValidCount() >= bizMsg.A.length()) {
                    bizMsg.setMessageInfo(NZZM0001W);
                }
            } else {

                // Set default Supplier values
                if (acctCustInfo != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, (String) acctCustInfo.get("DS_ACCT_NM"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_H1, (String) acctCustInfo.get("DS_ACCT_NUM"));
                }
                if (defaultVndInfo != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.taxPayerId_H1, (String) defaultVndInfo.get("TAX_PAYER_ID"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.splyOneTmFlg_H1, (String) defaultVndInfo.get("SPLY_ONE_TM_FLG"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.smBizFlg_H1, (String) defaultVndInfo.get("SM_BIZ_FLG"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.womenOwndFlg_H1, (String) defaultVndInfo.get("WOMEN_OWND_FLG"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.payAloneFlg_H1, (String) defaultVndInfo.get("PAY_ALONE_FLG"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.discTakeFlg_H1, (String) defaultVndInfo.get("DISC_TAKE_FLG"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.poPmtHldFlg_H1, (String) defaultVndInfo.get("PO_PMT_HLD_FLG"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermDescTxt_H1, (String) defaultVndInfo.get("VND_PMT_TERM_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtMethCd_H1, (String) defaultVndInfo.get("VND_PMT_METH_CD"));
                    ZYPEZDItemValueSetter.setValue(bizMsg.payGrpCd_H1, (String) defaultVndInfo.get("PAY_GRP_CD"));
                }
            }

            // Add Supplier Site
            int lastIndex = bizMsg.A.getValidCount();
            if (acctCustInfo != null) {
                // QC#26498
                String locNm = (String) acctCustInfo.get("LOC_NM");
                if (ZYPCommonFunc.hasValue(locNm)) {
                    if (locNm.length() > MAX_LOC_NM_LENGTH) {
                        locNm = locNm.substring(0, MAX_LOC_NM_LENGTH);
                    }
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).locNm_A, locNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).ctryCd_A, (String) acctCustInfo.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).xxComnScrFirstValTxt_A, (String) acctCustInfo.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).xxComnScrScdValTxt_A, (String) acctCustInfo.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).ctyAddr_A, (String) acctCustInfo.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).postCd_A, (String) acctCustInfo.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).stCd_A, (String) acctCustInfo.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).cntyNm_A, (String) acctCustInfo.get("CNTY_NM"));
            }
            if (defaultVndInfo != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).splyPmtFlg_A, (String) defaultVndInfo.get("SPLY_PMT_FLG"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).splyPoFlg_A, (String) defaultVndInfo.get("SPLY_PO_FLG"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).xxComnScrFirstValTxt_AL, (String) defaultVndInfo.get("SPLY_COA"));
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(lastIndex).billToCustCd_A, bizMsg.billToCustCd_P);

            // Account Enabled Check
            NMAL6860CommonLogic.getAccountEnabled(bizMsg, getGlobalCompanyCode());

            bizMsg.A.setValidCount(lastIndex + 1);
            bizMsg.xxRadioBtn_A.setValue(lastIndex);

        } else {
            // QC#27280
            String prntVndTpCd = ZYPCodeDataUtil.getVarCharConstValue("INTERNAL_PRNT_VND_TP_CD", bizMsg.glblCmpyCd.getValue());
            // 2020/02/28 QC#55971 Mod Start
            if (ZYPCommonFunc.hasValue(prntVndTpCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpCd, prntVndTpCd);
                PRNT_VND_TPTMsg tMsg = new PRNT_VND_TPTMsg();
                tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
                tMsg.prntVndTpCd.setValue(bizMsg.prntVndTpCd.getValue());
                tMsg = (PRNT_VND_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpDescTxt, tMsg.prntVndTpDescTxt);
            }
            // 2020/02/28 QC#55971 Mod End
        }
    }

    /**
     * <p>
     * Search Supplier Sites.
     * </p>
     * @param bizMsg
     * @param glblMsg
     */
    private void searchSupplierAndSupplierSites(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        BigDecimal prntVndPk = bizMsg.prntVndPk_P.getValue();

        S21SsmEZDResult result = NMAL6860Query.getInstance().searchSupplier(bizMsg, glblMsg);

        if (!result.isCodeNormal()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        EZDMsg.copy(glblMsg, null, bizMsg, null);

        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndPk_P, prntVndPk);

        // searches Supplier Sites.
        result = NMAL6860Query.getInstance().searchSupplierSites(bizMsg, glblMsg);

        if (!result.isCodeNormal()) {
            return;
        }

        // checks if the result counts exceeds maximum counts.
        int displayCount = result.getQueryResultCount();
        if (displayCount > glblMsg.A.length()) {
            displayCount = glblMsg.A.length();
            bizMsg.setMessageInfo(NZZM0001W);
        }

        // copies SMsg to CMsg.
        for (int index = 0; index < displayCount; index++) {
            EZDMsg.copy(glblMsg.A.no(index), null, bizMsg.A.no(index), null);
        }
        bizMsg.A.setValidCount(displayCount);

        bizMsg.xxRadioBtn_A.setValue(0);
    }

    /**
     * <p>
     * Search Contact Info.
     * </p>
     * @param bizMsg
     * @param glblMsg
     */
    private void searchContactInfo(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        S21SsmEZDResult result = NMAL6860Query.getInstance().searchContactInfo(bizMsg, glblMsg);

        if (!result.isCodeNormal()) {
            return;
        }

        // checks if the result counts exceeds maximum counts.
        int displayCount = 0;
        if (result.getQueryResultCount() > glblMsg.B.length()) {
            displayCount = glblMsg.B.length();
            bizMsg.setMessageInfo(NZZM0001W);
        } else {
            displayCount = result.getQueryResultCount();
        }
        glblMsg.B.setValidCount(displayCount);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {
                // Set index of AMsg
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).vndCd_A) && ZYPCommonFunc.hasValue(glblMsg.B.no(j).vndCd_B) && bizMsg.A.no(i).vndCd_A.getValue().equals(glblMsg.B.no(j).vndCd_B.getValue())) {

                    glblMsg.B.no(j).xxCellIdx_BA.setValue(i);
                }
            }
        }

        // Add Start 2019/12/24 QC#54971-1
        EZDMsg.copy(glblMsg.B, null, glblMsg.E, null);
        // Add End 2019/12/24 QC#54971-1
    }

    /**
     * <p>
     * Runs the onclick event of "Add Supplier Site" button.
     * </p>
     * @param bizMsg CMsg
     * @param glblMsg SMsg
     */
    private void doProcessAddSupplierSite(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg, String glblCmpyCd) {

        // checks if the row counts exceeds the maximum counts.
        int count = bizMsg.A.getValidCount();
        if (count == bizMsg.A.length()) {
            bizMsg.setMessageInfo(NMAM8114E);
            return;
        }

        // inserts a row.
        bizMsg.A.setValidCount(count + 1);
        bizMsg.xxRadioBtn_A.setValue(count);

        // QC#27280 search Default VendorInfo
        S21SsmEZDResult result = NMAL6860Query.getInstance().searchDefaultVendorInfo(bizMsg);
        Map defaultVndInfo = null;
        if (result.isCodeNormal()) {
            defaultVndInfo = (Map) result.getResultObject();
        }
        if (defaultVndInfo != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(count).splyPmtFlg_A, (String) defaultVndInfo.get("SPLY_PMT_FLG"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(count).splyPoFlg_A, (String) defaultVndInfo.get("SPLY_PO_FLG"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(count).xxComnScrFirstValTxt_AL, (String) defaultVndInfo.get("SPLY_COA"));
        }

        // Account Enabled Check
        NMAL6860CommonLogic.getAccountEnabled(bizMsg, glblCmpyCd);
    }

    /**
     * <p>
     * Runs the onclick event of "Add Contact Info" button.
     * </p>
     * @param bizMsg CMsg
     * @param glblMsg SMsg
     */
    private void doProcessAddContactInfo(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        // checks if the row counts exceeds the maximum counts.
        if (bizMsg.B.getValidCount() == bizMsg.B.length()) {
            bizMsg.setMessageInfo(NMAM8114E);
            return;
        }

        // inserts a row.
        int bizCount = bizMsg.B.getValidCount();
        int glblCount = glblMsg.B.getValidCount();
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bizCount).xxCellIdx_BA, bizMsg.xxRadioBtn_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bizCount).xxCellIdx_BS, new BigDecimal(glblMsg.B.getValidCount()));
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsg.xxRadioBtn_A.getValueInt()).vndCd_A)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bizCount).vndCd_B, bizMsg.A.no(bizMsg.xxRadioBtn_A.getValueInt()).vndCd_A);
            // QC#16594 Add.
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(bizCount).locNum_B, bizMsg.A.no(bizMsg.xxRadioBtn_A.getValueInt()).locNum_A1);
        }
        bizMsg.B.setValidCount(bizCount + 1);

        EZDMsg.copy(bizMsg.B.no(bizCount), null, glblMsg.B.no(glblCount), null);
        glblMsg.B.setValidCount(glblCount + 1);
    }

    /**
     * <p>
     * Runs the onclick event of "General" Tab.
     * </p>
     * @param bizMsg CMsg
     * @param glblMsg SMsg
     */
    private void doProcessTabGeneral(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg, String glblCmpyCd) {

//        if (!NMAL6860CommonLogic.checkDetailTab(bizMsg, glblMsg, glblCmpyCd)) {
//            return;
//        }

        // Account Enabled Check
        NMAL6860CommonLogic.getAccountEnabled(bizMsg, glblCmpyCd);

        // START 2021/09/28 S.Go [QC#57815,ADD]
        String xxDplyTab = bizMsg.xxDplyTab.getValue();
        // only update Detail of General Tab if transition is from Detail Tab (not from General Tab)
        if (S21StringUtil.isEquals(xxDplyTab, TAB_NM_DETAIL)) {
        // END 2021/09/28 S.Go [QC#57815,ADD]

            // copies from Header of Detail Tab to Detail of General Tab.
            NMAL6860CommonLogic.copyDetailToGeneral(bizMsg, glblMsg);

        // START 2021/09/28 S.Go [QC#57815,ADD]
        }
        // END 2021/09/28 S.Go [QC#57815,ADD]
    }

    /**
     * <p>
     * Runs the onclick event of "General" Tab.
     * </p>
     * @param bizMsg CMsg
     * @param glblMsg SMsg
     */
    private void doProcessTabDetail(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg, String glblCmpyCd) {

//        if (!NMAL6860CommonLogic.checkGeneralTab(bizMsg, glblMsg, glblCmpyCd)) {
//            return;
//        }
        // Account Enabled Check
        NMAL6860CommonLogic.getAccountEnabled(bizMsg, glblCmpyCd);

        // sets the input fields of Common Header.
        int index = bizMsg.xxRadioBtn_A.getValueInt();
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPk_H2, bizMsg.A.no(index).vndPk_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndCd_H2, bizMsg.A.no(index).vndCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.locNm_H2, bizMsg.A.no(index).locNm_A);

        ZYPEZDItemValueSetter.setValue(bizMsg.invMatchTpCd_H2, bizMsg.A.no(index).invMatchTpCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.invTolRate_H2, bizMsg.A.no(index).invTolRate_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.rcvTolRate_H2, bizMsg.A.no(index).rcvTolRate_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermDescTxt_H2, bizMsg.A.no(index).vndPmtTermDescTxt_A);
        // QC#13163 Start
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermCd_H2, bizMsg.A.no(index).vndPmtTermCd_A);
        // QC#13163 End
        ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtMethCd_H2, bizMsg.A.no(index).vndPmtMethCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.payGrpCd_H2, bizMsg.A.no(index).payGrpCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyEdiLocCd_H2, bizMsg.A.no(index).splyEdiLocCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.splyEdiNum_H2, bizMsg.A.no(index).splyEdiNum_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.inacDt_H2, bizMsg.A.no(index).inacDt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.inacDt_G2, bizMsg.A.no(index).inacDt_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.trsmtMethTpCd_H2, bizMsg.A.no(index).trsmtMethTpCd_A);
        // PrePay Account
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnScrFirstValTxt_H2, bizMsg.A.no(index).xxComnScrFirstValTxt_AP);
        // Vendor Return Account
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnScrScdValTxt_H2, bizMsg.A.no(index).xxComnScrFirstValTxt_AV);
        ZYPEZDItemValueSetter.setValue(bizMsg.xtrnlRefNum_H2, bizMsg.A.no(index).xtrnlRefNum_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.endCustNum_H2, bizMsg.A.no(index).endCustNum_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.arcsSplySiteCd_H2, bizMsg.A.no(index).arcsSplySiteCd_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.arcsSplySiteId_H2, bizMsg.A.no(index).arcsSplySiteId_A);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_H2, bizMsg.A.no(index).billToCustCd_A);
        // QC#16594 Mod.
        ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H1, bizMsg.A.no(index).locNum_A1);
        ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H2, bizMsg.A.no(index).locNum_A2);
        ZYPEZDItemValueSetter.setValue(bizMsg.invVndCd_H2, bizMsg.A.no(index).invVndCd_A);

        // Contact Info copies SMsg to CMsg.
        ZYPTableUtil.clear(bizMsg.B);
        int count = 0;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (glblMsg.B.no(i).xxCellIdx_BA.getValueInt() == index) {
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(count), null);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(count).xxCellIdx_BS, new BigDecimal(i));
                count++;
            }
        }

        bizMsg.B.setValidCount(count);
    }

    /**
     * <p>
     * Runs the onclick event of "Clear" button.
     * </p>
     * @param bizMsg CMsg
     * @param glblMsg SMsg
     */
    private void doProcessClear(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        // Save params
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndPk_P)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.prntVndPk_P, bizMsg.prntVndPk_P);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_P)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.dsAcctNum_P, bizMsg.dsAcctNum_P);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd_P)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.billToCustCd_P, bizMsg.billToCustCd_P);
        }

        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);

        EZDMsg.copy(glblMsg, null, bizMsg, null);
    }

    /**
     * <pre>
     * openWinLiabilityAccountCheck
     * </pre>
     * @param cMsg
     * @param sMsg
     */
    private boolean openWinLiabilityAccountCheck(EZDCMsg cMsg, EZDSMsg sMsg, String glblCmpyCd) {

        NMAL6860CMsg bizMsg = (NMAL6860CMsg) cMsg;

        int index = bizMsg.xxNum_AD.getValueInt();
        
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(index).xxComnScrFirstValTxt_AL)) {
            return true;
        }

        return NMAL6860CommonLogic.validateSegmentStringForLiabilityAccount(glblCmpyCd, bizMsg, false, index);
    }
    
    /**
     * <pre>
     * openWinPrePayAccountCheck
     * </pre>
     * @param cMsg
     * @param sMsg
     */
    private boolean openWinPrePayAccountCheck(EZDCMsg cMsg, EZDSMsg sMsg, String glblCmpyCd) {

        NMAL6860CMsg bizMsg = (NMAL6860CMsg) cMsg;

        if (!ZYPCommonFunc.hasValue(bizMsg.xxComnScrFirstValTxt_H2)) {
            return true;
        }

        return NMAL6860CommonLogic.validateSegmentStringForPrePayAccount(glblCmpyCd, bizMsg, false, 0);
    }
    
    /**
     * <pre>
     * openWinVendorReturnAccountCheck
     * </pre>
     * @param cMsg
     * @param sMsg
     */
    private boolean openWinVendorReturnAccountCheck(EZDCMsg cMsg, EZDSMsg sMsg, String glblCmpyCd) {

        NMAL6860CMsg bizMsg = (NMAL6860CMsg) cMsg;

        if (!ZYPCommonFunc.hasValue(bizMsg.xxComnScrScdValTxt_H2)) {
            return true;
        }

        return NMAL6860CommonLogic.validateSegmentStringForVendorReturnAccount(glblCmpyCd, bizMsg, false, 0);
    }

    // 2020/02/28 QC#55971 Add Start
    /**
     * <p>
     * Search Vender Type Code and Set Supply PO Flag if Print Vender Type Code is selected.
     * </p>
     * @param bizMsg CMsg
     * @param glblMsg SMsg
     */
    private void doProcessSetVenderCd(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.prntVndTpCd)) {
            PRNT_VND_TPTMsg tMsg = new PRNT_VND_TPTMsg();
            tMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
            tMsg.prntVndTpCd.setValue(bizMsg.prntVndTpCd.getValue());
            tMsg = (PRNT_VND_TPTMsg) EZDTBLAccessor.findByKey(tMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.vndTpCd, tMsg.vndTpCd);

            for (int index = 0; index < bizMsg.A.getValidCount(); index++) {
                if (VND_TP.SUPPLIER.equals(bizMsg.vndTpCd.getValue())
                        && !ZYPConstant.FLG_ON_Y.equals(bizMsg.A.no(index).splyPoFlg_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).splyPoFlg_A, ZYPConstant.FLG_ON_Y);
                }
            }
        }
    }
    // 2020/02/28 QC#55971 Add Start

}
