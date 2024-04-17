/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0300;

import static business.blap.NLCL0300.constant.NLCL0300Constant.BUSINESS_ID;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLCL0300.common.NLCL0300CommonLogic;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 * 2016/07/05   CSAI            K.Lee           Update          QC#11396
 * 2017/02/07   CITS            Y.IWASAKI       Update          QC#17234
 *</pre>
 */
public class NLCL0300BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
 
            String screenAplID = cMsg.getScreenAplID();

            if ("NLCL0300_INIT".equals(screenAplID)) {
                doProcess_NLCL0300_INIT(cMsg, sMsg);

            } else if ("NLCL0300Scrn00_Search".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_Search((NLCL0300CMsg) cMsg, (NLCL0300SMsg) sMsg);

            } else if ("NLCL0300Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_CMN_Clear((NLCL0300CMsg) cMsg, (NLCL0300SMsg) sMsg);

            } else if ("NLCL0300Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_CMN_Submit(cMsg, sMsg);

            } else if ("NLCL0300Scrn00_Delete_Line".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_Delete_Line((NLCL0300CMsg) cMsg, (NLCL0300SMsg) sMsg);

            } else if ("NLCL0300Scrn00_Add_Line".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_Add_Line((NLCL0300CMsg) cMsg, (NLCL0300SMsg) sMsg);

            } else if ("NLCL0300Scrn00_Add_NewConfigFromModel".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_Add_NewConfigFromModel((NLCL0300CMsg) cMsg, (NLCL0300SMsg) sMsg);

            } else if ("NLCL0300Scrn00_Add_ExistingConfig".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_Add_ExistingConfig((NLCL0300CMsg) cMsg, (NLCL0300SMsg) sMsg);

            } else if ("NLCL0300Scrn00_Search_Item".equals(screenAplID)) {
                doProcess_NLCL0300Scrn00_Search_Item((NLCL0300CMsg) cMsg, (NLCL0300SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);

            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NLCL0300_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300_INIT START ----- ", this);
        NLCL0300CMsg bizMsg = (NLCL0300CMsg) cMsg;
        NLCL0300SMsg globalMsg = (NLCL0300SMsg) sMsg;

        String invtyOrdNum = bizMsg.invtyOrdNum_H.getValue();
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        NLCL0300CommonLogic.setPulldownStkStsList(getGlobalCompanyCode(), bizMsg);
        if (ZYPCommonFunc.hasValue(invtyOrdNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_H, invtyOrdNum);
            doProcess_NLCL0300Scrn00_Search(bizMsg, globalMsg);
            if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                bizMsg.setMessageInfo("NPAM0005I");
            }
        }
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300_INIT END ----- ", this);
    }

    private void doProcess_NLCL0300Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_CMN_Clear START ----- ", this);
        NLCL0300CMsg bizMsg = (NLCL0300CMsg) cMsg;
        NLCL0300SMsg globalMsg = (NLCL0300SMsg) sMsg;
        bizMsg.invtyOrdNum_H.clear();
        doProcess_NLCL0300_INIT(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_CMN_Clear END ----- ", this);
    }

    private void doProcess_NLCL0300Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_CMN_Submit START ----- ", this);

        NLCL0300CMsg bizMsg = (NLCL0300CMsg) cMsg;
        NLCL0300SMsg globalMsg = (NLCL0300SMsg) sMsg;

        doProcess_NLCL0300Scrn00_Search(bizMsg, globalMsg);
        if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
            bizMsg.setMessageInfo("NPAM0005I");
        }

        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_CMN_Submit END ----- ", this);
    }

    private void doProcess_NLCL0300Scrn00_Delete_Line(NLCL0300CMsg bizMsg, NLCL0300SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Delete_Line START ----- ", this);
        int procCnt = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.A.no(i).xxChkBox_A.getValue())) {
                procCnt++;
            }
        }
        if (procCnt == 0) {
            bizMsg.setMessageInfo("NFAM0075E");
            return;
        }

        NLCL0300CMsg tmpMsg = new NLCL0300CMsg();
        EZDMsg.copy(bizMsg, null, tmpMsg, null);
        ZYPTableUtil.clear(bizMsg.A);

        int j = 0;
        for (int i = 0; i < tmpMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(tmpMsg.A.no(i).xxChkBox_A.getValue())) {
                EZDMsg.copy(tmpMsg.A.no(i), null, bizMsg.A.no(j), null);
                j++;
            }
        }
        bizMsg.A.setValidCount(j);
        bizMsg.setMessageInfo("NZZM0002I");
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Delete_Line END ----- ", this);
    }

    private void doProcess_NLCL0300Scrn00_Add_Line(NLCL0300CMsg bizMsg, NLCL0300SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Add_Line START ----- ", this);

        Map<String, Object> rtlWhMap = NLCL0300CommonLogic.getRtlWhMap(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), bizMsg.rtlWhNm_H.getValue());
        if (rtlWhMap == null) {
            bizMsg.rtlWhCd_H.setErrorInfo(1, "NPAM1361E", new String[]{"Warehouse"});
            return;
        }
        String rtlWhCd = (String) rtlWhMap.get("RTL_WH_CD");
        String rtlWhNm = (String) rtlWhMap.get("RTL_WH_NM");
        String rtlSwhCd = (String) rtlWhMap.get("RTL_SWH_CD");
        String invtyLocCd = (String) rtlWhMap.get("INVTY_LOC_CD");
        NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(getGlobalCompanyCode(), invtyLocCd, BUSINESS_ID, null, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(enableWHData.getXxMsgId())) {
            bizMsg.rtlWhCd_H.setErrorInfo(1, enableWHData.getXxMsgId());
        }

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_HL, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H, rtlWhNm);
        NLCL0300CommonLogic.setPulldownRtlSwhList(getGlobalCompanyCode(), bizMsg);

        Map<String, Object> mdseMap = NLCL0300CommonLogic.getMdseMap(getGlobalCompanyCode(), bizMsg.mdseCd_H.getValue());
        if (mdseMap == null) {
            bizMsg.mdseCd_H.setErrorInfo(1, "NPAM1361E", new String[]{"Item"});
            return;
        }
        String mdseDescShortTxt = (String) mdseMap.get("MDSE_DESC_SHORT_TXT");
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_H, mdseDescShortTxt);
        if (!ZYPConstant.FLG_ON_Y.equals((String) mdseMap.get("INSTL_BASE_CTRL_FLG"))) {
            bizMsg.mdseCd_H.setErrorInfo(1, "NLZM2464E", new String[]{});
            return;
        }

        int no = bizMsg.A.getValidCount();

        if (no == bizMsg.A.length()) {
            bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(bizMsg.A.length()) });
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).mdseCd_A, bizMsg.mdseCd_H);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).mdseDescShortTxt_A, bizMsg.mdseDescShortTxt_H);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).stkStsCd_A, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).rtlSwhCd_A, rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).ordQty_A, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).serNum_A, bizMsg.serNum_H);

        bizMsg.A.setValidCount(no + 1);
        bizMsg.mdseCd_H.clear();
        bizMsg.mdseDescShortTxt_H.clear();
        bizMsg.serNum_H.clear();
        bizMsg.setMessageInfo("NZZM0002I");

        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Add_Line END ----- ", this);
    }

    private void doProcess_NLCL0300Scrn00_Add_NewConfigFromModel(NLCL0300CMsg bizMsg, NLCL0300SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Add_NewConfigFromModel START ----- ", this);

        Map<String, Object> rtlWhMap = NLCL0300CommonLogic.getRtlWhMap(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), bizMsg.rtlWhNm_H.getValue());
        if (rtlWhMap == null) {
            bizMsg.rtlWhCd_H.setErrorInfo(1, "NPAM1361E", new String[]{"Warehouse"});
            return;
        }
        String rtlWhCd = (String) rtlWhMap.get("RTL_WH_CD");
        String rtlWhNm = (String) rtlWhMap.get("RTL_WH_NM");
        String rtlSwhCd = (String) rtlWhMap.get("RTL_SWH_CD");
        String invtyLocCd = (String) rtlWhMap.get("INVTY_LOC_CD");
        NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(getGlobalCompanyCode(), invtyLocCd, BUSINESS_ID, null, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(enableWHData.getXxMsgId())) {
            bizMsg.rtlWhCd_H.setErrorInfo(1, enableWHData.getXxMsgId());
        }

        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_HL, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H, rtlWhNm);
        NLCL0300CommonLogic.setPulldownRtlSwhList(getGlobalCompanyCode(), bizMsg);

        if (!ZYPCommonFunc.hasValue(bizMsg.mdlDescTxt_H)) {
            bizMsg.mdlDescTxt_H.setErrorInfo(1, "NSAM0315E", new String[]{"Model Name"});
            return;
        }

        List<Map<String, Object>> mdlList = NLCL0300CommonLogic.getModelConfigList(getGlobalCompanyCode(), bizMsg.mdlDescTxt_H.getValue());
        if (mdlList == null || mdlList.isEmpty()) {
            bizMsg.mdlDescTxt_H.setErrorInfo(1, "NPAM1361E", new String[]{"Model ID"});
            return;
        }

        for (int i = 0; i < mdlList.size(); i++) {
            Map<String, Object> mdlMap = mdlList.get(i);
            String mdseCd = (String) mdlMap.get("MDSE_CD");
            String mdseDescShortTxt = null;

            Map<String, Object> mdseMap = NLCL0300CommonLogic.getMdseMap(getGlobalCompanyCode(), mdseCd);
            if (mdseMap == null) {
                bizMsg.mdlDescTxt_H.setErrorInfo(1, "NPAM1361E", new String[]{"Item [" + mdseCd + "]"});
                ZYPTableUtil.clear(bizMsg.A);
                return;
            }
            mdseCd = (String) mdseMap.get("MDSE_CD");
            mdseDescShortTxt = (String) mdseMap.get("MDSE_DESC_SHORT_TXT");
            if (!ZYPConstant.FLG_ON_Y.equals((String) mdseMap.get("INSTL_BASE_CTRL_FLG"))) {
                bizMsg.mdseCd_H.setErrorInfo(1, "NLZM2464E", new String[]{});
                return;
            }

            int no = bizMsg.A.getValidCount();

            if (no == bizMsg.A.length()) {
                bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(bizMsg.A.length()) });
                return;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).mdseCd_A, mdseCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).mdseDescShortTxt_A, mdseDescShortTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).stkStsCd_A, STK_STS.GOOD);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).rtlSwhCd_A, rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).ordQty_A, BigDecimal.ONE);

            bizMsg.A.setValidCount(no + 1);
        }
        bizMsg.setMessageInfo("NZZM0002I");

        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Add_ExistingConfig END ----- ", this);
    }

    private void doProcess_NLCL0300Scrn00_Add_ExistingConfig(NLCL0300CMsg bizMsg, NLCL0300SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Add_ExistingConfig START ----- ", this);

        Map<String, Object> rtlWhMap = NLCL0300CommonLogic.getRtlWhMap(getGlobalCompanyCode(), bizMsg.rtlWhCd_H.getValue(), bizMsg.rtlWhNm_H.getValue());
        if (rtlWhMap == null) {
            bizMsg.rtlWhCd_H.setErrorInfo(1, "NPAM1361E", new String[]{"Warehouse"});
            return;
        } else {
            String rtlWhCd = (String) rtlWhMap.get("RTL_WH_CD");
            String rtlWhNm = (String) rtlWhMap.get("RTL_WH_NM");
            String invtyLocCd = (String) rtlWhMap.get("INVTY_LOC_CD");
            NMXC100001EnableWHData enableWHData = NMXC100001EnableWH.checkEnableWH(getGlobalCompanyCode(), invtyLocCd, BUSINESS_ID, null, ZYPConstant.FLG_ON_Y);
            if (ZYPCommonFunc.hasValue(enableWHData.getXxMsgId())) {
                bizMsg.rtlWhCd_H.setErrorInfo(1, enableWHData.getXxMsgId());
            }

            if ("E".equals(bizMsg.getMessageKind())) {
                return;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_HL, rtlWhCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H, rtlWhNm);
            NLCL0300CommonLogic.setPulldownRtlSwhList(getGlobalCompanyCode(), bizMsg);
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.svcConfigMstrPk_H)) {
            bizMsg.svcConfigMstrPk_H.setErrorInfo(1, "NSAM0315E", new String[]{"Config ID"});
            return;
        }

        List<Map<String, Object>> configList = NLCL0300CommonLogic.getConfigList(getGlobalCompanyCode(), bizMsg.svcConfigMstrPk_H.getValue());
        if (configList == null || configList.isEmpty()) {
            bizMsg.svcConfigMstrPk_H.setErrorInfo(1, "NPAM1361E", new String[]{"Config ID"});
            return;
        }

        BigDecimal mdlId = null;
        String mdlDescTxt = null;

        for (int i = 0; i < configList.size(); i++) {
            Map<String, Object> configMap = configList.get(i);
            BigDecimal svcConfigMstrPk = (BigDecimal) configMap.get("SVC_CONFIG_MSTR_PK");
            String mdseCd = (String) configMap.get("MDSE_CD");
            String serNum = (String) configMap.get("SER_NUM");
            String rtlWhCd = (String) configMap.get("RTL_WH_CD");
            String rtlSwhCd = (String) configMap.get("RTL_SWH_CD");
            String stkStsCd = (String) configMap.get("STK_STS_CD");
            String svcMachMstrStsCd = (String) configMap.get("SVC_MACH_MSTR_STS_CD");
            mdlId = (BigDecimal) configMap.get("MDL_ID");
            mdlDescTxt = (String) configMap.get("MDL_DESC_TXT");
            String mdseDescShortTxt = null;

            if (!bizMsg.rtlWhCd_HL.getValue().equals(rtlWhCd)) {
                bizMsg.svcConfigMstrPk_H.setErrorInfo(1, "NLZM2465E");
                ZYPTableUtil.clear(bizMsg.A);
                return;
            }

            if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {
                bizMsg.svcConfigMstrPk_H.setErrorInfo(1, "NLZM2295E");
                ZYPTableUtil.clear(bizMsg.A);
                return;
            }

            Map<String, Object> mdseMap = NLCL0300CommonLogic.getMdseMap(getGlobalCompanyCode(), mdseCd);
            if (mdseMap == null) {
                bizMsg.svcConfigMstrPk_H.setErrorInfo(1, "NPAM1361E", new String[]{"Item :" + mdseCd});
                ZYPTableUtil.clear(bizMsg.A);
                return;
            } else {
                mdseDescShortTxt = (String) mdseMap.get("MDSE_DESC_SHORT_TXT");
            }

            int no = bizMsg.A.getValidCount();

            if (no == bizMsg.A.length()) {
                bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(bizMsg.A.length()) });
                ZYPTableUtil.clear(bizMsg.A);
                return;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).svcConfigMstrPk_A, svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).mdseCd_A, mdseCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).mdseDescShortTxt_A, mdseDescShortTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).rtlSwhCd_A, rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).stkStsCd_A, stkStsCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).serNum_A, serNum);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).ordQty_A, BigDecimal.ONE);

            bizMsg.A.setValidCount(no + 1);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.mdlDescTxt_H, mdlDescTxt);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_H, mdlId);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_HL, mdlId);
        ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_HL, bizMsg.svcConfigMstrPk_H);
        bizMsg.setMessageInfo("NZZM0002I");

        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Add_ExistingConfig END ----- ", this);
    }

    private void doProcess_NLCL0300Scrn00_Search_Item(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Search_Item START ----- ", this);

        NLCL0300CMsg bizMsg = (NLCL0300CMsg) cMsg;

        Map<String, Object> mdseMap = NLCL0300CommonLogic.getMdseMap(getGlobalCompanyCode(), bizMsg.mdseCd_H.getValue());

        if (mdseMap == null) {
            bizMsg.mdseCd_H.setErrorInfo(1, "NMZM0154E", new String[]{"Item"});
            bizMsg.mdseDescShortTxt_H.clear();
            return;

        } else {
            String mdseDescShortTxt = (String) mdseMap.get("MDSE_DESC_SHORT_TXT");
            ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_H, mdseDescShortTxt);
        }
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Search_Item END ----- ", this);
    }

    private void doProcess_NLCL0300Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Search START ----- ", this);

        NLCL0300CMsg bizMsg = (NLCL0300CMsg) cMsg;
        NLCL0300SMsg globalMsg = (NLCL0300SMsg) sMsg;
        getConfigChangeOrder(bizMsg, globalMsg);
        EZDDebugOutput.println(1, "----- doProcess_NLCL0300Scrn00_Search END ----- ", this);
    }

    private void getConfigChangeOrder(NLCL0300CMsg bizMsg, NLCL0300SMsg globalMsg) {
        EZDDebugOutput.println(1, "----- getAdjustmentOrder START ----- ", this);

        NLCL0300_ACMsgArray bizMsgArray = bizMsg.A;

        ZYPTableUtil.clear(bizMsgArray);

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("invtyOrdNum", bizMsg.invtyOrdNum_H.getValue());
        prmMap.put("rowNum", String.valueOf(bizMsg.A.length()));

        S21SsmEZDResult ssmResult = NLCL0300Query.getInstance().getConfigChangeOrder(bizMsg, globalMsg, prmMap);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            int maxCnt = bizMsgArray.length();

            if (queryResCnt > maxCnt) {
                queryResCnt = maxCnt;
                bizMsg.setMessageInfo("NMAM0098I", new String[] {Integer.toString(maxCnt) });
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_H, bizMsg.A.no(0).invtyOrdNum_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_HD, bizMsg.A.no(0).invtyOrdNum_A);
            ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdStsDescTxt_H, bizMsg.A.no(0).invtyOrdStsDescTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.soNum_H, bizMsg.A.no(0).soNum_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_H, bizMsg.A.no(0).rtlWhCd_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_HL, bizMsg.A.no(0).rtlWhCd_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_H, bizMsg.A.no(0).rtlWhNm_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_H, bizMsg.A.no(0).svcConfigMstrPk_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_HL, bizMsg.A.no(0).svcConfigMstrPk_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_H, bizMsg.A.no(0).mdlId_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdlDescTxt_H, bizMsg.A.no(0).mdlDescTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.firstInvtyOrdCmntTxt_H, bizMsg.A.no(0).firstInvtyOrdCmntTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.scdInvtyOrdCmntTxt_H, bizMsg.A.no(0).scdInvtyOrdCmntTxt_AH);
            ZYPEZDItemValueSetter.setValue(bizMsg.thirdInvtyOrdCmntTxt_H, bizMsg.A.no(0).thirdInvtyOrdCmntTxt_AH);

            NLCL0300CommonLogic.setPulldownRtlSwhList(getGlobalCompanyCode(), bizMsg);

        } else {
            bizMsg.setMessageInfo("NMAM0007I");
            return;
        }

        EZDDebugOutput.println(1, "----- getAdjustmentOrder END ----- ", this);
    }
}
