/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NPAL1340;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL1340.common.NPAL1340CommonLogic;
import business.blap.NPAL1340.constant.NPAL1340Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 * 2016/03/25   CSAI            K.Lee           Update          QC#5957
 * 2016/10/07   CITS            K.Ogino         Update          QC#14566
 * 09/26/2017   CITS            T.Tokutomi      Update          QC#21191
 * 06/05/2018   CITS            K.Ogino         Update          QC#26104
 * 06/25/2020   CITS            M.Furugoori     Update          QC#56979
 *</pre>
 */
public class NPAL1340BL02 extends S21BusinessHandler implements NPAL1340Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            NPAL1340CMsg bizMsg = (NPAL1340CMsg) cMsg;
            NPAL1340SMsg globalMsg = (NPAL1340SMsg) sMsg;

            if ("NPAL1340Scrn00_CMN_ColSave".equals(screenAplID)) {

            } else if ("NPAL1340Scrn00_CMN_ColClear".equals(screenAplID)) {

            } else if ("NPAL1340_INIT".equals(screenAplID)) {
                doProcess_NPAL1340_INIT(bizMsg, globalMsg);
                ZYPGUITableColumn.getColData(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_Search".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_Search(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_OnChange_PONum".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_OnChange_PONum(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_CMN_Clear(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_CMN_Reset(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_CMN_Submit(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_OnCheck_AllCancel".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_OnCheck_AllCancel(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_OnCheck_AllRelease".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_OnCheck_AllRelease(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_PageNext(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_PagePrev(bizMsg, globalMsg);
            } else if ("NPAL1340_NPAL0100".equals(screenAplID)) {
                doProcess_NPAL1340_NPAL0100(bizMsg, globalMsg);
            } else if ("NPAL1340Scrn00_Apply".equals(screenAplID)) {
                doProcess_NPAL1340Scrn00_Apply(bizMsg, globalMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID :" + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NPAL1340Scrn00_Search
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    private void doProcess_NPAL1340_INIT(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {

        String poOrdNum = bizMsg.poOrdNum_H0.getValue();
        String cpoOrdNum = bizMsg.cpoOrdNum_H0.getValue();
        String xxComnColOrdTxt = bizMsg.xxComnColOrdTxt.getValue();
        bizMsg.clear();
        globalMsg.clear();
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);
        bizMsg.N.clear();
        bizMsg.N.setValidCount(0);
        globalMsg.N.clear();
        globalMsg.N.setValidCount(0);
        ZYPEZDItemValueSetter.setValue(bizMsg.poOrdNum_H1, poOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum_H1, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, xxComnColOrdTxt);

        if (ZYPCommonFunc.hasValue(bizMsg.poOrdNum_H1) || ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H1)) {
            doProcess_NPAL1340Scrn00_Search(bizMsg, globalMsg);
        }
    }

    /**
     * doProcess_NPAL1340Scrn00_Search
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    private void doProcess_NPAL1340Scrn00_CMN_Clear(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        bizMsg.poOrdNum_H0.clear();
        bizMsg.cpoOrdNum_H0.clear();
        doProcess_NPAL1340_INIT(bizMsg, globalMsg);
    }

    /**
     * doProcess_NPAL1340Scrn00_Search
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    private void doProcess_NPAL1340Scrn00_CMN_Reset(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        doProcess_NPAL1340_INIT(bizMsg, globalMsg);
    }

    /**
     * doProcess_NPAL1340Scrn00_Search
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    private void doProcess_NPAL1340Scrn00_Search(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {

        NPAL1340CommonLogic.clearHeaderArea(bizMsg, globalMsg);
        NPAL1340CommonLogic.clearDetailArea(bizMsg, globalMsg);

        String custDropShipPoQlfy = ZYPCodeDataUtil.getVarCharConstValue("CUST_DROP_SHIP_PO_QLFY", getGlobalCompanyCode());
        if (custDropShipPoQlfy == null) {
            custDropShipPoQlfy = DEF_DROP_SHIP_PO_QLFY;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("poHdrStsCdOpen", PO_HDR_STS.OPEN);
        ssmParam.put("custDropShipPoQlfy", custDropShipPoQlfy);
        ssmParam.put("cMsg", bizMsg);
        S21SsmEZDResult ezdResult = NPAL1340Query.getInstance().searchPoNumberList(ssmParam, bizMsg);
        if (ezdResult.isCodeNormal()) {
            List<Map<String, String>> poNumberList = (List<Map<String, String>>) ezdResult.getResultObject();
            for (int i = 0; i < poNumberList.size(); i++) {
                Map<String, String> poNumberMap = poNumberList.get(i);
                if (i >= bizMsg.poOrdNum_LC.length() || i >= bizMsg.poOrdNum_LD.length()) {
                    bizMsg.setMessageInfo("NPAM0015W");
                    break;
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.poOrdNum_LC.no(i), poNumberMap.get("PO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.poOrdNum_LD.no(i), poNumberMap.get("PO_ORD_NUM"));
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.poOrdNum_H2, bizMsg.poOrdNum_LC.no(0).getValue());
            doProcess_NPAL1340Scrn00_OnChange_PONum(bizMsg, globalMsg);
        } else {
            bizMsg.setMessageInfo("NPAM0002E");
        }
    }
    /**
     * doProcess_NPAL1340Scrn00_Search
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    private void doProcess_NPAL1340Scrn00_OnChange_PONum(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {

        NPAL1340CommonLogic.clearDetailArea(bizMsg, globalMsg);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("poOrdNum", bizMsg.poOrdNum_H2.getValue());
        ssmParam.put("poMdseCmpsnTpCd", PO_MDSE_CMPSN_TP.PARENT);
        ssmParam.put("rowNum", globalMsg.A.length() + 1);
        //QC#21191 Modify
        ssmParam.put("poClose", PO_STS.CLOSED);
        // QC#26104
        ssmParam.put("poReceiving", PO_STS.RECEIVING);
        ssmParam.put("poReceivingComp", PO_STS.RECEIVING_COMPLETION);
        S21SsmEZDResult ssmResult = NPAL1340Query.getInstance().searchPoNumber(ssmParam, globalMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.A.length()) {

                bizMsg.setMessageInfo("NZZM0001W");
                queryResCnt = globalMsg.A.length();
            }

            boolean openFlg = false;
            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (!openFlg && (PO_LINE_STS.OPEN.equals(globalMsg.A.no(i).poLineStsCd_A1.getValue()) || PO_LINE_STS.OPEN_FOR_RECEIPT.equals(globalMsg.A.no(i).poLineStsCd_A1.getValue()))) {
                    openFlg = true;
                }
            }

            int i;
            for (i = 0; i < globalMsg.A.getValidCount(); i++) {

                if (i == 0) {
                    EZDMsg.copy(globalMsg.A.no(i), "AH", bizMsg, "H2");
                }
                NPAL1340CommonLogic.setSerialList(getGlobalCompanyCode(), globalMsg, i);

                if (i < bizMsg.A.length()) {
                    // START 2020/06/25 [QC#56979,ADD]
                    if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).proNum_A1) && ZYPCommonFunc.hasValue(globalMsg.A.no(i).carrTrkUrl_A1)) {
                        editCarrTrkUrl(globalMsg, i);
                    }
                    // END 2020/06/25 [QC#56979,ADD]
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                    EZDMsg.copy(globalMsg.N.no(i), null, bizMsg.N.no(i), null);
                    NPAL1340CommonLogic.setSerialLabelText(bizMsg, i);
                }
                if (i == bizMsg.A.length()) {
                    break;
                }
            }

            if(openFlg) {
                bizMsg.xxBtnFlg.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                bizMsg.xxBtnFlg.setValue(ZYPConstant.FLG_OFF_N);
            }

            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(queryResCnt);

        } else {
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(0);
        }
    }

    // START 2020/06/25 [QC#56979,ADD]
    /**
     * <pre>
     * editCarrTrkUrl
     * ADD QC:56979
     * </pre>
     * @param sMsg NLCL0620SMsg
     */
    private static void editCarrTrkUrl(NPAL1340SMsg globalMsg, int i) {
        String url = globalMsg.A.no(i).carrTrkUrl_A1.getValue();
        Pattern pattern = Pattern.compile(REPLACE_CHAR);
        Matcher matcher = pattern.matcher(url);
        String str = matcher.replaceAll(globalMsg.A.no(i).proNum_A1.getValue());
        globalMsg.A.no(i).carrTrkUrl_A1.setValue(str);
    }
    // END 2020/06/25 [QC#56979,ADD]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1340Scrn00_OnCheck_AllCancel(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_OnCheck_AllCancel================================START", this);
        NPAL1340CommonLogic.copyPage(bizMsg, globalMsg);
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (bizMsg.xxChkBox_H3.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    if (globalMsg.A.no(i).poStsCd_A1.getValue().equals(PO_STS.CLOSED) || globalMsg.A.no(i).poStsCd_A1.getValue().equals(PO_STS.CANCELLED)) {
                        globalMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
                        globalMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.FLG_OFF_N);
                    } else {
                        globalMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
                        globalMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.FLG_ON_Y);
                    }
                } else {
                    globalMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
                    globalMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.FLG_OFF_N);
                }
        }
        NPAL1340CommonLogic.dispPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_OnCheck_AllCancel================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1340Scrn00_OnCheck_AllRelease(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_OnCheck_AllRelease================================START", this);
        NPAL1340CommonLogic.copyPage(bizMsg, globalMsg);
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (bizMsg.xxChkBox_H2.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    if (globalMsg.A.no(i).poStsCd_A1.getValue().equals(PO_STS.CLOSED) || globalMsg.A.no(i).poStsCd_A1.getValue().equals(PO_STS.CANCELLED)) {
                        globalMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
                        globalMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.FLG_OFF_N);
                    } else {
                        globalMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_ON_Y);
                        globalMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.FLG_OFF_N);
                    }
                } else {
                    globalMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
                    globalMsg.A.no(i).xxChkBox_A2.setValue(ZYPConstant.FLG_OFF_N);
                }
        }
        NPAL1340CommonLogic.dispPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_OnCheck_AllRelease================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1340Scrn00_PageNext(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_PageNext================================START", this);
        NPAL1340CommonLogic.copyPage(bizMsg, globalMsg);
        NPAL1340CommonLogic.nextPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1340Scrn00_PagePrev(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_PageNext================================START", this);
        NPAL1340CommonLogic.copyPage(bizMsg, globalMsg);
        NPAL1340CommonLogic.prevPage(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1340_NPAL0100(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_PageNext================================START", this);
        int no = bizMsg.xxNum.getValueInt();
        BigDecimal serCnt = NPAL1340CommonLogic.getSerialCount(bizMsg.N.no(no));
        if (serCnt.compareTo(bizMsg.A.no(no).poRcvQty_A1.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).xxScrItem20Txt_A1, "Completed");
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).xxScrItem20Txt_A1, "No Entry");
        }
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_PageNext================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NPAL1340Scrn00_CMN_Submit(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_CMN_Submit================================START", this);
        doProcess_NPAL1340Scrn00_OnChange_PONum(bizMsg, globalMsg);
        bizMsg.setMessageInfo("NPAM0005I");
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_CMN_Submit================================END", this);
    }

    /**
     * doProcess_NPAL1340Scrn00_Apply
     * QC#21191 Add method.
     * @param bizMsg NPAL1340CMsg
     * @param globalMsg NPAL1340SMsg
     */
    private void doProcess_NPAL1340Scrn00_Apply(NPAL1340CMsg bizMsg, NPAL1340SMsg globalMsg) {
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_Apply================================START", this);

        // Check CarrNm
        if (ZYPCommonFunc.hasValue(bizMsg.carrNm_H2)) {
            String carrCd = NPAL1340CommonLogic.getCarrierCd(getGlobalCompanyCode(), bizMsg.carrNm_H2.getValue());

            if (ZYPCommonFunc.hasValue(carrCd)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.carrCd_H2, carrCd);
            } else {
                // Error
                bizMsg.carrNm_H2.setErrorInfo(1, "NPAM0076E", new String[] {"Carrier" });
                return;
            }
        }

        // Set Tracking# & Carrier
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            // set sMsg
            String poStsCd = globalMsg.A.no(i).poStsCd_A1.getValue();
            if (!PO_STS.CLOSED.equals(poStsCd) //
                    && !PO_STS.CANCELLED.equals(poStsCd)) {
                if (ZYPCommonFunc.hasValue(bizMsg.proNum_H2)) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).proNum_A1, bizMsg.proNum_H2);
                }
                if (ZYPCommonFunc.hasValue(bizMsg.carrCd_H2)) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).carrCd_A1, bizMsg.carrCd_H2);
                }
                if (ZYPCommonFunc.hasValue(bizMsg.carrNm_H2)) {
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(i).carrNm_A1, bizMsg.carrNm_H2);
                }
            }
            // set cMsg
            if (i < bizMsg.A.getValidCount()) {
                String poStsCdDisp = bizMsg.A.no(i).poStsCd_A1.getValue();
                if (!PO_STS.CLOSED.equals(poStsCdDisp) //
                        && !PO_STS.CANCELLED.equals(poStsCdDisp)) {
                    if (ZYPCommonFunc.hasValue(bizMsg.proNum_H2)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).proNum_A1, bizMsg.proNum_H2);
                    }
                    if (ZYPCommonFunc.hasValue(bizMsg.carrCd_H2)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).carrCd_A1, bizMsg.carrCd_H2);
                    }
                    if (ZYPCommonFunc.hasValue(bizMsg.carrNm_H2)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).carrNm_A1, bizMsg.carrNm_H2);
                    }
                }
            }
        }

        bizMsg.setMessageInfo("ZZZM9003I", new String[] {"Apply" });
        EZDDebugOutput.println(1, "doProcess_NPAL1340Scrn00_Apply================================END", this);
    }
}
