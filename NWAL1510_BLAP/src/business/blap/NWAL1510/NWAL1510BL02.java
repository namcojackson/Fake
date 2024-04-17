/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1510;

import static business.blap.NWAL1510.common.NWAL1510CommonLogic.setAmPmPulldownList;
import static business.blap.NWAL1510.common.NWAL1510CommonLogic.setCtacPsnTpPulldown;
import static business.blap.NWAL1510.common.NWAL1510CommonLogic.setSvcIstlRulePulldown;
import static business.blap.NWAL1510.common.NWAL1510CommonLogic.setSvcPrvdPtyPulldown;
import static business.blap.NWAL1510.constant.NWAL1510Constant.BUTTON_DISP_FLG_ON;
import static business.blap.NWAL1510.constant.NWAL1510Constant.MESSAGE_KIND_ERROR;
import static business.blap.NWAL1510.constant.NWAL1510Constant.MESSAGE_NAME_SCHD_CMNT;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0142E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0181E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0325E;
import static business.blap.NWAL1510.constant.NWAL1510Constant.NWAM0931W;
import static business.blap.NWAL1510.constant.NWAL1510Constant.SVC_ISTL_RULE_NUM_NO_INSTALL;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TAB_ENABLE_FLG_OFF;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TAB_ENABLE_FLG_ON;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TIME_SUBSTRING_FROM;
import static business.blap.NWAL1510.constant.NWAL1510Constant.TIME_SUBSTRING_TO;
import static business.blap.NWAL1510.constant.NWAL1510Constant.VALID_COUNT_ZERO;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1510.common.NWAL1510CommonLogic;
import business.db.CPOTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DELY_TRNSP_OPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * D&I/Contact/Site Survey Business Logic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/09   Fujitsu         S.Ohki          Update          S21_NA#1622
 * 2016/05/11   Fujitsu         M.Yamada        Update          S21_NA#7626
 * 2016/07/11   Fujitsu         H.Ikeda         Update          S21_NA#5030
 * 2016/07/25   Fujitsu         S.Iidaka        Update          S21_NA#11404
 * 2016/08/02   Fujitsu         M.Hara          Update          S21_NA#7306
 * 2016/09/20   Fujitsu         R.Nakamura      Update          QC#13738
 * 2016/10/12   Fujitsu         W.Honda         Update          S21_NA#13021
 * 2016/12/05   Fujitsu         W.Honda         Update          S21_NA#16371
 * 2017/02/09   Fujitsu         M.Ohno          Update          S21_NA#17500
 * 2017/02/16   Fujitsu         M.Ohno          Update          S21_NA#17639
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 * 2017/06/28   Fujitsu         M.Yamada        Update          S21_NA#19610
 * 2017/07/31   Fujitsu         S.Ohki          Update          S21_NA#19999
 * 2017/08/08   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2017/08/25   Fujitsu         R.Nakamura      Update          S21_NA#20623
 * 2017/09/25   Fujitsu         A.Kosai         Update          S21_NA#20799
 * 2018/01/10   Fujitsu         K.Ishizuka      Update          S21_NA#18460(Sol#087)
 * 2018/1/18    CITS            T.Hakodate      Update          S21_NA#18460(Sol#087)
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/03/26   Fujitsu         T.Aoi           Update          S21_NA#22954
 * 2018/07/13   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 * 2018/08/02   Fujitsu         K.Ishizuka      Update          S21_NA#26188-2
 * 2019/03/08   Fujitsu         Hd.Sugawara     Update          QC#30712
 * 2019/10/25   Fujitsu         Mz.Takahashi    Update          QC#53993
 * 2019/12/26   Fujitsu         S.Kosaka        Update          QC#54725
 * 2020/02/10   Fujitsu         C.Hara          Update          QC#54725-1
 * 2020/04/02   Fujitsu         Y.Kanefusa      Update          S21_NA#56247
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300

 *</pre>
 */
public class NWAL1510BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;
        String scrnAplID = bizMsg.getScreenAplID();
        try {
            if ("NWAL1510_INIT".equals(scrnAplID)) {
                doProcess_NWAL1510_INIT(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_CMN_Reset".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_CMN_Reset(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_Search_TechNm".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_Search_TechNm(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_Add_Row".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_Add_Row(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_Delete_Row".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_Delete_Row(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_CMN_Clear".equals(scrnAplID)) {
                doProcess_NWAL1510_CLEAR(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_CMN_Save".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn01_CMN_Save(bizMsg, (NWAL1510SMsg) sMsg);
            // 2017/02/09 S21_NA#17500 Add Star
            } else if ("NWAL1510Scrn00_PageJump".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_PageJump(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_PageNext".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_PageNext(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_PagePrev".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_PagePrev(bizMsg, (NWAL1510SMsg) sMsg);
            // 2017/02/09 S21_NA#17500 Add End
            // QC#16452 Add Start
            } else if ("NWAL1510_NMAL6770".equals(scrnAplID)) {
                doProcess_NWAL1510_NMAL6770(bizMsg);
            // QC#16452 Add End
            // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD START
            } else if ("NWAL1510Scrn00_OnChange_InstallType".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_OnChange_InstallType(bizMsg, (NWAL1510SMsg) sMsg);
            // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD END
            // 2018/07/17 S21_NA#26188 Add Start
            }  else if ("NWAL1510Scrn00_TAB_Contact".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_TAB(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_TAB_Install".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_TAB(bizMsg, (NWAL1510SMsg) sMsg);

            } else if ("NWAL1510Scrn00_TAB_Survey".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_TAB(bizMsg, (NWAL1510SMsg) sMsg);
            // 2018/07/17 S21_NA#26188 Add End
            // 2019/11/01 QC#53993 Add Start
            } else if ("NWAL1510Scrn00_OnChange_ToBeInstalledBy".equals(scrnAplID)) {
                doProcess_NWAL1510Scrn00_OnChange_ToBeInstalledBy(bizMsg, (NWAL1510SMsg) sMsg);
            // 2019/11/01 QC#53993 Add End
            } else {
                // do nothing
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1510Scrn00_Search_TechNm(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        // QC#19610
//        TECH_MSTRTMsg tMsg = NWAL1510Query.getInstance().getTechMstr(bizMsg);
//        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.techNm)) {
//            ZYPEZDItemValueSetter.setValue(bizMsg.techNm_DI, tMsg.techNm);
//        } else {
//            bizMsg.istlTechCd_DI.setErrorInfo(1, NWAM0181E, new String[] {"Install Technician" });
//        }

        S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getTechNm(bizMsg);
        if (ssmResult == null || ssmResult.isCodeNotFound()) {
            bizMsg.istlTechCd_DI.setErrorInfo(1, NWAM0181E, new String[] {"Install Technician" });
            bizMsg.techNm_DI.clear();
            return;
        }
        String techNm = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.techNm_DI, techNm);
    }

    private void doProcess_NWAL1510_INIT(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        createPulldown(bizMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H0)) {
            return;
        }

        clearAll(bizMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        NWAL1510CommonLogic.setAuthority(bizMsg, getUserProfileService()); // S21_NA#16035 Add
        searchOrder(bizMsg, sMsg);
    }

    private void doProcess_NWAL1510Scrn00_CMN_Reset(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        createPulldown(bizMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_H0)) {
            return;
        }

        clearAll(bizMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        NWAL1510CommonLogic.setAuthority(bizMsg, getUserProfileService()); // S21_NA#16035 Add
        searchOrder(bizMsg, sMsg);
    }

    private void createPulldown(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        // Configuration Category
        bizMsg.configCatgCd_L0.clear();
        bizMsg.configCatgDescTxt_L0.clear();
        ZYPCodeDataUtil.createPulldownList(CONFIG_CATG.class, bizMsg.configCatgCd_L0, bizMsg.configCatgDescTxt_L0);

        // Service Install Rule
        bizMsg.svcIstlRuleNum_DI.clear();
        bizMsg.svcIstlRuleNum_L0.clear();
        bizMsg.svcIstlRuleNm_L0.clear();
        setSvcIstlRulePulldown(bizMsg, getGlobalCompanyCode());

        // Install Division
        // 2019/10/25 QC#53993 Del Start
        //bizMsg.istlDivCd_DI.clear();
        //bizMsg.istlDivCd_L0.clear();
        //bizMsg.lineBizTpDescTxt_L0.clear();
        //ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.istlDivCd_L0, bizMsg.lineBizTpDescTxt_L0);
        // 2019/10/25 QC#53993 Del End

        // 2019/10/25 QC#53993 Add Start
        // To Be Installed By
        bizMsg.istlBySvcPrvdPtyCd_DI.clear();
        bizMsg.svcPrvdPtyDescTxt_L0.clear();
        bizMsg.istlBySvcPrvdPtyCd_L0.clear();
        setSvcPrvdPtyPulldown(bizMsg, true, this.getGlobalCompanyCode());
        // 2019/10/25 QC#53993 Add End

        // Transport Option
        bizMsg.delyTrnspOptCd_SS.clear();
        bizMsg.delyTrnspOptCd_L0.clear();
        bizMsg.delyTrnspOptNm_L0.clear();
        ZYPCodeDataUtil.createPulldownList(DELY_TRNSP_OPT.class, bizMsg.delyTrnspOptCd_L0, bizMsg.delyTrnspOptNm_L0);

        // AM/PM
        bizMsg.xxTpCd.clear();
        bizMsg.xxTpNm.clear();
        setAmPmPulldownList(bizMsg);

        // Contact Parson
        bizMsg.ctacPsnTpCd_L0.clear();
        bizMsg.ctacTpNm_L0.clear();
        // S21_NA#7306
//        ZYPCodeDataUtil.createPulldownList(CTAC_TP.class, bizMsg.ctacPsnTpCd_L0, bizMsg.ctacTpNm_L0);
        setCtacPsnTpPulldown(bizMsg, getGlobalCompanyCode());

        //QC#16452 add Start
        // Contact Customer Reference Type
        bizMsg.ctacCustRefTpCd_L0.clear();
        bizMsg.ctacCustRefTpDescTxt_L0.clear();
        ZYPCodeDataUtil.createPulldownList("CTAC_CUST_REF_TP", bizMsg.ctacCustRefTpCd_L0, bizMsg.ctacCustRefTpDescTxt_L0);
        //QC#16452 add End

        // 2019/10/25 QC#53993 Add Start
        // Service Provided By
        bizMsg.svcBySvcPrvdPtyCd_DI.clear();
        bizMsg.svcPrvdPtyDescTxt_L1.clear();
        bizMsg.svcBySvcPrvdPtyCd_L0.clear();
        setSvcPrvdPtyPulldown(bizMsg, false, this.getGlobalCompanyCode());
        // 2019/10/25 QC#53993 Add End

        // 2019/12/26 QC#54725 Add Start
        // Delivery Scheduling Status
        bizMsg.delySchdStsCd_L0.clear();
        // QC#56247 2020/04/02 Add Start
        // bizMsg.delySchdStsNm_L0.clear();
        // ZYPCodeDataUtil.createPulldownList("DELY_SCHD_STS", bizMsg.delySchdStsCd_L0, bizMsg.delySchdStsNm_L0);
        bizMsg.delySchdStsDescTxt_L0.clear();
        ZYPCodeDataUtil.createPulldownList("DELY_SCHD_STS", bizMsg.delySchdStsCd_L0, bizMsg.delySchdStsDescTxt_L0);
        // QC#56247 2020/04/02 Add End
        // 2019/12/26 QC#54725 Add End
    }

    /**
     * Set Tab Protect Flg
     * @param bizMsg NWAL1510CMsg
     */
    private void setTabProtectFlg(NWAL1510CMsg bizMsg) {

        S21SsmEZDResult ssmResult = null;

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
            if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd_H0.getValue())) {
                ssmResult = NWAL1510Query.getInstance().getCountCpoDtlOpened(bizMsg);
            } else {
                ssmResult = NWAL1510Query.getInstance().getCountCpoRtnDtlOpened(bizMsg);
            }
        } else {
            ssmResult = NWAL1510Query.getInstance().getCountOrderOpened(bizMsg);
        }

        Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

        if (((BigDecimal) resultMap.get("COUNT")).compareTo(BigDecimal.ZERO) == 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DS, TAB_ENABLE_FLG_OFF); // 2020/02/10 QC#54725-1 Add
            return;
        // 2020/02/10 QC#54725-1 Add Start
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DS, TAB_ENABLE_FLG_ON);
        // 2020/02/10 QC#54725-1 Add End
        }

        // S21_NA#7626
        if (NWAL1510CommonLogic.isHeaderOnly(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_ON);
            return;
        }

        ssmResult = new S21SsmEZDResult();
        resultMap = new HashMap<String, Object>();

//        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
        if (bizMsg.H.getValidCount() > 0) {

            if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd_H0.getValue())) {
                ssmResult = NWAL1510Query.getInstance().getCountOutboundAllocation(bizMsg);
            } else {
                ssmResult = NWAL1510Query.getInstance().getCountInboundAllocation(bizMsg);
            }
        } else {

            ssmResult = NWAL1510Query.getInstance().getCountOutboundAllocation(bizMsg);

            resultMap = (Map<String, Object>) ssmResult.getResultObject();

            if (((BigDecimal) resultMap.get("COUNT")).compareTo(BigDecimal.ZERO) == 0) {
                ssmResult = NWAL1510Query.getInstance().getCountInboundAllocation(bizMsg);
            }
        }

        resultMap = (Map<String, Object>) ssmResult.getResultObject();
        if (((BigDecimal) resultMap.get("COUNT")).compareTo(BigDecimal.ZERO) > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_ON);//S21_NA#11404
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_OFF);//S21_NA#11404
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_ON);
    }

    private void searchOrder(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        if (bizMsg.H.getValidCount() > 1) {

            // 2017/07/31 S21_NA#19999 Add Start
            // Del Start 2019/03/08 QC#30712
            //if (!NWAL1510Query.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS)) {
            //    ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, SVC_ISTL_RULE_NUM_NO_INSTALL);
            //}
            // Del End 2019/03/08 QC#30712
            // 2017/07/31 S21_NA#19999 Add End

            setTabProtectFlg(bizMsg);
            // Set Default Radio Button
            setDefRadioBtn(bizMsg); // 2018/08/02 S21_NA#26188-2 Add
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_MU, ZYPConstant.FLG_ON_Y); // 2018/07/13 S21_NA#26188 Add 
            // 2018/07/17 S21_NA#26188 Add Start
            EZDMsg.copy(bizMsg, null, sMsg, null);
            NWAL1510CommonLogic.convTimeforScreen(sMsg);
            // 2018/07/17 S21_NA#26188 Add End
            return;
        }

        // Tab
        if (!setHeaderItem(bizMsg, sMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_OFF);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRsltFlg_DS, TAB_ENABLE_FLG_OFF); // 2020/02/10 QC#54725-1 Add
            return;
        }

        // ///////////////////////////////////////
        // Delivery & Install Tab
        // Header Area
        // Delivery Area
        setDelivItem(bizMsg);

        // Install Area
        setIstlItemItem(bizMsg);

        // Install Details Area
        setIstlDtlList(bizMsg);

        // ///////////////////////////////////////
        // Survey

        // Company/Site/Additional Comments/Elevator Info
        setSurveyInfo(bizMsg, sMsg); // 2017/02/09 S21_NA#17500 Mod

        // ///////////////////////////////////////
        // Contact
        setCtacList(bizMsg, sMsg);

        // Set Tab Enable Flag
        setTabProtectFlg(bizMsg);
        
        // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD START
        NWAL1510CommonLogic.getServiceInstallTypeEditableFlag(bizMsg);
        // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD END
        

        ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg, BUTTON_DISP_FLG_ON);
        
        // Set Default Radio Button
        setDefRadioBtn(bizMsg); // 2018/08/02 S21_NA#26188-2 Add
        
        // 2018/07/17 S21_NA#26188 Add Start
        EZDMsg.copy(bizMsg, null, sMsg, null);
        NWAL1510CommonLogic.convTimeforScreen(sMsg);
        // 2018/07/17 S21_NA#26188 Add End
    }

    private void doProcess_NWAL1510_CLEAR(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {

        cMsg.A.setValidCount(VALID_COUNT_ZERO);
        cMsg.B.setValidCount(VALID_COUNT_ZERO);
        cMsg.C.setValidCount(VALID_COUNT_ZERO);

        createPulldown(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg_DI, TAB_ENABLE_FLG_OFF);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg_SS, TAB_ENABLE_FLG_OFF);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg_CO, TAB_ENABLE_FLG_OFF);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg_DS, TAB_ENABLE_FLG_OFF); // 2020/02/10 QC#54725-1 Add
    }

    private void doProcess_NWAL1510Scrn01_CMN_Save(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {
        if (bizMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return;
        }

        if (bizMsg.H.getValidCount() <= 1) {
            clearAll(bizMsg, sMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        }

        searchOrder(bizMsg, sMsg);
    }
    
    // 2018/07/20 S21_NA#26188 Add Start
    private void doProcess_NWAL1510Scrn00_TAB(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {

        boolean inputResetCheck = ZYPConstant.FLG_ON_Y.equals(cMsg.xxDplyCtrlFlg_TR.getValue());

        if (inputResetCheck) {
            cMsg.xxDplyCtrlFlg_TR.clear();
            EZDMsg.copy(sMsg, null, cMsg, null);
            return;
        }

        if (NWAL1510CommonLogic.isChangedItem(cMsg, sMsg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_TR, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo("NWAM0611W");
        }

    }
    // 2018/07/20 S21_NA#26188 Add End

    /**
     * Set Header Area Item
     * @param bizMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result(false: error)
     */
    private boolean setHeaderItem(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {
        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {

            S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().searchWithConfNum(bizMsg);

            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo(NWAM0142E);
                return false;
            }

            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            // DS_CPO_CONFIG_PK
            ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoConfigPk_H0, (BigDecimal) resultMap.get("DS_CPO_CONFIG_PK"));
            // Category
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt_H0, (String) resultMap.get("DS_ORD_CATG_DESC_TXT"));
            // Reason Code
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_H0, (String) resultMap.get("DS_ORD_TP_DESC_TXT"));
            // Ship To Cust Acct
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_CD"));
            // Location Code
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_H0, (String) resultMap.get("SHIP_TO_CUST_LOC_CD"));
            // Name
            // 2016/10/12 S21_NA#13021 Mod Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            // 2016/10/12 S21_NA#13021 Mod End
            // Address Line 1
            ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr_H0, (String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));
            // Address Line 2
            ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr_H0, (String) resultMap.get("SHIP_TO_SCD_LINE_ADDR"));

            // 2017/07/31 S21_NA#19999 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_H0, (String) resultMap.get("DS_ORD_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_H0, (String) resultMap.get("DS_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnCd_H0, (String) resultMap.get("DS_ORD_RSN_CD"));
            // 2017/07/31 S21_NA#19999 Add End

            // QC#16452 add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd_H0, (String) resultMap.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_H0, (String) resultMap.get("SOLD_TO_CUST_LOC_CD"));
            // QC#16452 add End
            // 2018/01/05 S21_NA#18460(Sol#087) ADD START
            ZYPEZDItemValueSetter.setValue(bizMsg.tmZoneCd_H0, getTmZoneCd((String) resultMap.get("SHIP_TO_CTRY_CD"), //
                    (String) resultMap.get("SHIP_TO_POST_CD")));
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H0, (String) resultMap.get("SHIP_TO_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.postCd_H0, (String) resultMap.get("SHIP_TO_POST_CD"));
            // 2018/01/05 S21_NA#18460(Sol#087) ADD END
            // 2018/03/09 S21_NA#22387 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToLocNum_H0, (String) resultMap.get("SELL_TO_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToLocNum_H0, (String) resultMap.get("BILL_TO_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNum_H0, (String) resultMap.get("SHIP_TO_LOC_NUM"));
            // 2018/03/09 S21_NA#22387 Add End
        } else {

            S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().searchWithOrdNum(bizMsg);

            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo(NWAM0142E);
                return false;
            }

            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            // Category
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt_H0, (String) resultMap.get("DS_ORD_CATG_DESC_TXT"));
            // Reason Code
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_H0, (String) resultMap.get("DS_ORD_TP_DESC_TXT"));
            // Ship To Cust Acct
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_H0, (String) resultMap.get("SELL_TO_CUST_CD"));
            // Location Code
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_H0, (String) resultMap.get("ADD_SHIP_TO_CUST_CD"));
            // Name
            // 2016/10/12 S21_NA#13021 Mod Start
//            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H0, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            // 2016/10/12 S21_NA#13021 Mod End
            // Address Line 1
            ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr_H0, (String) resultMap.get("ADD_SHIP_TO_FIRST_LINE_ADDR"));
            // Address Line 2
            ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr_H0, (String) resultMap.get("ADD_SHIP_TO_SCD_LINE_ADDR"));

            // 2017/07/31 S21_NA#19999 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_H0, (String) resultMap.get("DS_ORD_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_H0, (String) resultMap.get("DS_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnCd_H0, (String) resultMap.get("DS_ORD_RSN_CD"));
            // 2017/07/31 S21_NA#19999 Add End

            // QC#16452 add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd_H0, (String) resultMap.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_H0, (String) resultMap.get("SOLD_TO_CUST_LOC_CD"));
            // QC#16452 add End
            // 2018/01/05 S21_NA#18460(Sol#087) ADD START
            ZYPEZDItemValueSetter.setValue(bizMsg.tmZoneCd_H0, getTmZoneCd((String) resultMap.get("ADD_SHIP_TO_CTRY_CD"), //
                    (String) resultMap.get("ADD_SHIP_TO_POST_CD")));
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H0, (String) resultMap.get("ADD_SHIP_TO_CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.postCd_H0, (String) resultMap.get("ADD_SHIP_TO_POST_CD"));
            // 2018/01/05 S21_NA#18460(Sol#087) ADD END
            // 2018/03/09 S21_NA#22387 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToLocNum_H0, (String) resultMap.get("SELL_TO_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToLocNum_H0, (String) resultMap.get("BILL_TO_LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNum_H0, (String) resultMap.get("SHIP_TO_LOC_NUM"));
            // 2018/03/09 S21_NA#22387 Add End
        }

        // GET LOC_NUM
        S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getLocNum(bizMsg);

        if (ssmResult.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            // Category
            ZYPEZDItemValueSetter.setValue(bizMsg.locNum_H0, (String) resultMap.get("LOC_NUM"));
        }

        return true;
    }

    /**
     * Set Derivery Area Item
     * @param bizMsg NWAL1510CMsg
     */
     private void setDelivItem(NWAL1510CMsg bizMsg) {
        Map<String, Object> resultMap = null;

        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {

            S21SsmEZDResult delvDtl = NWAL1510Query.getInstance().getDeliveryDtlWithConfNum(bizMsg);

            if (delvDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) delvDtl.getResultObject();
            }

        } else {

            S21SsmEZDResult delvDtl = NWAL1510Query.getInstance().getDeliveryDtlWithOrdNum(bizMsg);

            if (delvDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) delvDtl.getResultObject();
            }
        }

        if (resultMap != null) {
            // DS_CPO_DELY_INFO_PK
            ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoDelyInfoPk_DI, (BigDecimal) resultMap.get("DS_CPO_DELY_INFO_PK"));
            // 2018/01/09 S21_NA#18460 MOD START
            // Hours of Operation From
            //ZYPEZDItemValueSetter.setValue(bizMsg.opsFromHourMn_DI, (String) resultMap.get("OPS_FROM_HOUR_MN"));
            if (ZYPCommonFunc.hasValue((String) resultMap.get("OPS_FROM_HOUR_MN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.opsFromHourMn_DI, getConvertTime(bizMsg, (String) resultMap.get("OPS_FROM_HOUR_MN")));
            }
            // Hours of Operation To
            //ZYPEZDItemValueSetter.setValue(bizMsg.opsToHourMn_DI, (String) resultMap.get("OPS_TO_HOUR_MN"));
            if (ZYPCommonFunc.hasValue((String) resultMap.get("OPS_TO_HOUR_MN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.opsToHourMn_DI, getConvertTime(bizMsg, (String) resultMap.get("OPS_TO_HOUR_MN")));
            }
            // 2018/01/09 S21_NA#18460 MOD END
            // Loading Dock
            if (ZYPCommonFunc.hasValue((String) resultMap.get("LOAD_DOCK_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFlg_DI, (String) resultMap.get("LOAD_DOCK_AVAL_FLG"));
            }
            // Stairs
            if (ZYPCommonFunc.hasValue((String) resultMap.get("STAIR_CRAW_REQ_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.stairCrawReqFlg_DI, (String) resultMap.get("STAIR_CRAW_REQ_FLG"));
            }
            // Number of Stairs
            ZYPEZDItemValueSetter.setValue(bizMsg.stairCrawNum_DI, (String) resultMap.get("STAIR_CRAW_NUM"));
            // Elevator
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFlg_DI, (String) resultMap.get("ELEV_AVAL_FLG"));
            }
            // Delivery Instructions
            ZYPEZDItemValueSetter.setValue(bizMsg.delyAddlCmntTxt_DI, (String) resultMap.get("DELY_ADDL_CMNT_TXT"));
            // EZD update Time
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_DI, (String) resultMap.get("EZUPTIME"));
            // EZD Update TimeZone
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_DI, (String) resultMap.get("EZUPTIMEZONE"));
            // 2019/12/26 QC#54725 Add Srart
            // Delivery Scheduling Status Code
            ZYPEZDItemValueSetter.setValue(bizMsg.delySchdStsCd_DI, (String) resultMap.get("DELY_SCHD_STS_CD"));
            // Delivery Scheduling Comments
            if ((Clob) resultMap.get("DELY_SCHD_CMNT_CLOD") != null) {
                try {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxAttDataCmntTxt_DI, NWAL1510CommonLogic.clobToString((Clob) resultMap.get("DELY_SCHD_CMNT_CLOD")));
                } catch (SQLException e) {
                    bizMsg.xxAttDataCmntTxt_DI.setErrorInfo(1, NWAM0325E, new String[] {MESSAGE_NAME_SCHD_CMNT});
                }
            }
            // 2019/12/26 QC#54725 Add End
        }
    }

    /**
     * Set Install Area Item
     * @param bizMsg NWAL1510CMsg
     */
    private void setIstlItemItem(NWAL1510CMsg bizMsg) {

        Map<String, Object> resultMap = null;
        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {

            S21SsmEZDResult istlDtl = NWAL1510Query.getInstance().getIstlDtlWithConfNum(bizMsg);

            if (istlDtl.isCodeNotFound()) {

                // 2017/07/31 S21_NA#19999 Add Start
                if (!NWAL1510Query.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, SVC_ISTL_RULE_NUM_NO_INSTALL);
                    return;
                }
                // 2017/07/31 S21_NA#19999 Add End

                S21SsmEZDResult defultInstType = NWAL1510Query.getInstance().getDefaultInstTypeWithConfNum(bizMsg);
                if (defultInstType.isCodeNormal()) {

                    Map<String, Object> defaultInstTypeMap = (Map<String, Object>) defultInstType.getResultObject();

                    // Install Type
                    // 2017/02/16 S21_NA#17639 Mod Start
                    if (S21StringUtil.isEquals(bizMsg.configCatgCd_H0.getValue(), CONFIG_CATG.OUTBOUND)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) defaultInstTypeMap.get("SVC_ISTL_RULE_NUM"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) defaultInstTypeMap.get("SVC_DEINS_RULE_NUM"));
                        // START 2023/12/12 K.Watanabe [QC#61300, ADD]
                        setDeinsItemItem(bizMsg);
                        // END 2023/12/12 K.Watanabe [QC#61300, ADD]
                    }
                    // 2017/02/16 S21_NA#17639 Mod End
                // 2017/09/25 S21_NA#20799 Mod Start
                //}
                } else {

                    bizMsg.istlDivCd_DI.clear();
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, SVC_ISTL_RULE_NUM_NO_INSTALL);
                    return;
                }
                // 2017/09/25 S21_NA#20799 Mod End

                // START 2023/09/05 K.Watanabe [QC#53408, ADD]
                S21SsmEZDResult defaultAccessoryInstType = NWAL1510Query.getInstance().getDefaultAccessoryInstTypeWithConfNum(bizMsg);
                if (defaultAccessoryInstType.isCodeNormal()) {

                    Map<String, Object> defaultAccessoryInstTypeMap = (Map<String, Object>) defaultAccessoryInstType.getResultObject();

                    if (ZYPCommonFunc.hasValue((String) defaultAccessoryInstTypeMap.get("SVC_ISTL_RULE_NUM"))) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) defaultAccessoryInstTypeMap.get("SVC_ISTL_RULE_NUM"));
                    }
                    else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, SVC_ISTL_RULE_NUM_NO_INSTALL);
                    }
                }
                // END 2023/09/05 K.Watanabe [QC#53408, ADD]

                // 2017/02/16 S21_NA#17639 Add Start
                CPOTMsg dsCpoTMsg = new CPOTMsg();
                ZYPEZDItemValueSetter.setValue(dsCpoTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCpoTMsg.cpoOrdNum, bizMsg.cpoOrdNum_H0);
                dsCpoTMsg = (CPOTMsg) EZDTBLAccessor.findByKey(dsCpoTMsg);

                if (dsCpoTMsg == null) {
                    return;
                }

                DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfn = new DS_ORD_TP_PROC_DFNTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfn.dsOrdTpCd, dsCpoTMsg.dsOrdTpCd);
                dsOrdTpProcDfn = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(dsOrdTpProcDfn);

                if (dsOrdTpProcDfn == null) {
                    return;
                }

                ZYPEZDItemValueSetter.setValue(bizMsg.istlDivCd_DI, dsOrdTpProcDfn.lineBizTpCd);
                // 2017/02/16 S21_NA#17639 Add End

                return;

            } else {

                resultMap = (Map<String, Object>) istlDtl.getResultObject();
            }

        // Order Header Level
        } else {

            S21SsmEZDResult istlDtl = NWAL1510Query.getInstance().getIstlDtlWithOrdNum(bizMsg);
            if (istlDtl.isCodeNormal()) {
                resultMap = (Map<String, Object>) istlDtl.getResultObject();
            } else {

                // 2017/07/31 S21_NA#19999 Add Start
                if (!NWAL1510Query.getInstance().isExistOrdCatg(bizMsg, ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, SVC_ISTL_RULE_NUM_NO_INSTALL);
                }
                // 2017/07/31 S21_NA#19999 Add End

                return;
            }
        }

        if (resultMap != null) {
            // DS_CPO_ISTL_INFO_PK
            ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoIstlInfoPk_DI, (BigDecimal) resultMap.get("DS_CPO_ISTL_INFO_PK"));
            // Install Type
            ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, (String) resultMap.get("SVC_ISTL_RULE_NUM"));
            // Install Division
            ZYPEZDItemValueSetter.setValue(bizMsg.istlDivCd_DI, (String) resultMap.get("ISTL_DIV_CD"));
            // Install Technician Code
            ZYPEZDItemValueSetter.setValue(bizMsg.istlTechCd_DI, (String) resultMap.get("ISTL_TECH_CD"));
            // Install Technician Name
            ZYPEZDItemValueSetter.setValue(bizMsg.techNm_DI, (String) resultMap.get("TECH_NM"));
            // Install Date
            //ZYPEZDItemValueSetter.setValue(bizMsg.rqstIstlDt_DI, (String) resultMap.get("RQST_ISTL_DT")); // 2018/01/09 S21_NA#18460(Sol#087) DEL
            // 2018/01/09 S21_NA#18460(Sol#087) MOD START
            // Install Time
            //ZYPEZDItemValueSetter.setValue(bizMsg.rqstIstlTm_DI, (String) resultMap.get("RQST_ISTL_TM"));
            if (ZYPCommonFunc.hasValue((String) resultMap.get("RQST_ISTL_TM"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.rqstIstlTm_DI, getConvertTime(bizMsg, (String) resultMap.get("RQST_ISTL_TM")));
            }
            // 2018/01/09 S21_NA#18460(Sol#087) MOD END
            // Install Instructions
            ZYPEZDItemValueSetter.setValue(bizMsg.istlAddlCmntTxt_DI, (String) resultMap.get("ISTL_ADDL_CMNT_TXT"));
            // EZD update Time
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_IS, (String) resultMap.get("EZUPTIME"));
            // EZD Update TimeZone
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_IS, (String) resultMap.get("EZUPTIMEZONE"));

            // 2019/11/01 QC#53993 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.istlBySvcPrvdPtyCd_DI, (String) resultMap.get("ISTL_BY_SVC_PRVD_PTY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.svcBySvcPrvdPtyCd_DI, (String) resultMap.get("SVC_BY_SVC_PRVD_PTY_CD"));
            // 2019/11/01 QC#53993 Add End
        }
    }

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * Set Deinstall Area Item
     * @param bizMsg NWAL1510CMsg
     */
    private void setDeinsItemItem(NWAL1510CMsg bizMsg) {
        if (S21StringUtil.isEquals(bizMsg.configCatgCd_H0.getValue(), CONFIG_CATG.INBOUND)) {
            S21SsmEZDResult deinstallInfo = NWAL1510Query.getInstance().getDeinstallInfo(bizMsg);
            if (deinstallInfo.isCodeNormal()) {
                Map<String, Object>deinstallInfoMap = (Map<String, Object>) deinstallInfo.getResultObject();
                String svcBySvcPrvdPtyCd = (String) deinstallInfoMap.get("SVC_BY_SVC_PRVD_PTY_CD");
                String svcDeinsRuleNum = (String) deinstallInfoMap.get("SVC_DEINS_RULE_NUM");
                if (!ZYPCommonFunc.hasValue(bizMsg.istlBySvcPrvdPtyCd_DI) && ZYPCommonFunc.hasValue(svcBySvcPrvdPtyCd)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.istlBySvcPrvdPtyCd_DI, svcBySvcPrvdPtyCd);
                }
                if (ZYPCommonFunc.hasValue(svcDeinsRuleNum)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, svcDeinsRuleNum);
                } else {
                    BigDecimal dsCpoConfigPk = (BigDecimal) deinstallInfoMap.get("DS_CPO_CONFIG_PK");
                    BigDecimal svcMachMstrPk = (BigDecimal) deinstallInfoMap.get("SVC_MACH_MSTR_PK");
                    S21SsmEZDResult svcDeinsRuleForMdse = NWAL1510Query.getInstance().getMdlSvcDeinsRuleNum(bizMsg.glblCmpyCd.getValue(), dsCpoConfigPk, svcMachMstrPk);
                    if (svcDeinsRuleForMdse.isCodeNotFound()) {
                        S21SsmEZDResult svcDeinsRuleForRtrnDtl = NWAL1510Query.getInstance().getMdseSvcDeinsRuleNum(bizMsg.glblCmpyCd.getValue(), dsCpoConfigPk);
                        if (svcDeinsRuleForRtrnDtl.isCodeNormal()) {
                            Map<String, Object>svcDeinsRuleForRtrnDtlMap = (Map<String, Object>) svcDeinsRuleForRtrnDtl.getResultObject();
                            String svcDeinsRuleNumForRtrnDtl = (String) svcDeinsRuleForRtrnDtlMap.get("SVC_DEINS_RULE_NUM");
                            ZYPEZDItemValueSetter.setValue(bizMsg.svcIstlRuleNum_DI, svcDeinsRuleNumForRtrnDtl);
                        }
                    }
                }
            }
        }
    }
    // END 2023/12/12 K.Watanabe [QC#61300, ADD]

    /**
     * Set Install Details Table Item
     * @param bizMsg NWAL1510CMsg
     */
    private void setIstlDtlList(NWAL1510CMsg bizMsg) {

        List resultList = new ArrayList();

        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {

            S21SsmEZDResult istlDtl = NWAL1510Query.getInstance().getInstDtlListbyConfNum(bizMsg);
            // 2016/12/05 S21_NA#16371 Mod Start
//            if (istlDtl.isCodeNotFound()) {
            if (!istlDtl.isCodeNotFound()) {
            // 2016/12/05 S21_NA#16371 Mod End
                resultList = (List) istlDtl.getResultObject();
            }

        } else {
            S21SsmEZDResult istlDtl = NWAL1510Query.getInstance().getInstDtlListbyOrdNum(bizMsg);
            // 2016/12/05 S21_NA#16371 Mod Start
//            if (istlDtl.isCodeNotFound()) {
            if (!istlDtl.isCodeNotFound()) {
            // 2016/12/05 S21_NA#16371 Mod End
                resultList = (List) istlDtl.getResultObject();
            }
        }

        if (!resultList.isEmpty()) {

            for (int idx = 0; idx < resultList.size(); idx++) {
                Map map = (Map) resultList.get(idx);
                // Call Status
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).svcCallStsNm_A0, (String) map.get("SVC_TASK_STS_NM"));
                // Mod Start 2017/08/25 QC#20623
                // Service Request Number
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).fsrNum_A0, (String) map.get("FSR_NUM"));
                // Task Number
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).svcTaskNum_A0, (String) map.get("SVC_TASK_NUM"));
                // Mod End 2017/08/25 QC#20623
                // Call Type
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).dsSvcCallTpNm_A0, (String) map.get("DS_SVC_CALL_TP_NM"));
                // Scheduled Start Date
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).fsrVisitSchdDt_A0, (String) map.get("FSR_VISIT_SCHD_DT"));
                // Scheduled Start Time
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).fsrVisitSchdTm_A0, (String) map.get("FSR_VISIT_SCHD_TM"));
                // End Time (Date)
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).fsrCpltDt_A0, (String) map.get("FSR_CPLT_DT"));
                // End Time (Time)
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).fsrCpltTm_A0, (String) map.get("FSR_CPLT_TM"));
                // Assigned Tech
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).techCd_A0, (String) map.get("TECH_CD"));
                // 2016/12/05 S21_NA#16371 Add Start
                // Mod Start 2017/08/25 QC#20623
                // Last Modified Date/Time
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).lastUpdDt_A0, (String) map.get("LAST_UPD_DT"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).updTm_A0, (String) map.get("LAST_UPD_TM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxTsDsp19Txt_A0, ZYPDateUtil.formatEzd14ToDisp((String) map.get("LAST_UPD_TS")));
                // Mod Start 2017/08/25 QC#20623
                // 2016/12/05 S21_NA#16371 Add End
                bizMsg.A.setValidCount(idx + 1);
            }
        }
    }

    /**
     * Set Site Survey Info
     * @param bizMsg NWAL1510CMsg
     */
    private void setSurveyInfo(NWAL1510CMsg bizMsg, NWAL1510SMsg glblMsg) { // 2017/02/09 S21_NA#17500 Mod

        S21SsmEZDResult ssmResult = null;

        S21SsmEZDResult materialResult = null;

        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
            ssmResult = NWAL1510Query.getInstance().getSiteSurveybyConfNum(bizMsg);

            if (CONFIG_CATG.OUTBOUND.equals(bizMsg.configCatgCd_H0.getValue())) {
                materialResult = NWAL1510Query.getInstance().getMaterialInfobyConfNum(bizMsg, glblMsg); // 2017/02/09 S21_NA#17500 Mod
            } else {
                materialResult = NWAL1510Query.getInstance().getMaterialRtnInfobyConfNum(bizMsg, glblMsg); // 2017/02/09 S21_NA#17500 Mod
            }

        } else {
            ssmResult = NWAL1510Query.getInstance().getSiteSurveybyOrdNum(bizMsg);

            // 2018/03/26 QC#22954 Mod Start
            //materialResult = NWAL1510Query.getInstance().getMaterialInfobyOrdNum(bizMsg, glblMsg); // 2017/02/09 S21_NA#17500 Mod
            if (CONFIG_CATG.INBOUND.equals(bizMsg.configCatgCd_H0.getValue())) {
                materialResult = NWAL1510Query.getInstance().getMaterialRtrnInfobyOrdNum(bizMsg, glblMsg);
            } else {
                materialResult = NWAL1510Query.getInstance().getMaterialInfobyOrdNum(bizMsg, glblMsg);
            }
            // 2018/03/26 QC#22954 Mod End
        }

        // 2017/02/09 S21_NA#17500 Add Star
        if (materialResult.getQueryResultCount() > glblMsg.B.length()) {
            bizMsg.setMessageInfo(NWAM0931W);
        }

        bizMsg.xxPageShowFromNum.setValue(1);
        NWAL1510CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);
        // 2017/02/09 S21_NA#17500 Add End

        if (ssmResult.isCodeNormal()) {

            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();

            // Survey Key
            ZYPEZDItemValueSetter.setValue(bizMsg.dsSiteSrvyPk_SS, (BigDecimal) resultMap.get("DS_SITE_SRVY_PK"));

            // ///////////////////
            // Company Information

            // Company Name
            // 2016/10/12 S21_NA#13021 Mod Start
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_SS, (String) resultMap.get("SHIP_TO_CUST_ACCT_NM"));
            // 2016/10/12 S21_NA#13021 Mod End
            // Customer Number
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd_SS, (String) resultMap.get("SHIP_TO_CUST_LOC_CD"));
            // Street
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_SS, (String) resultMap.get("SHIP_TO_FIRST_LINE_ADDR"));
            // City
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_SS, (String) resultMap.get("SHIP_TO_CTY_ADDR"));
            // Apt. or Building
            ZYPEZDItemValueSetter.setValue(bizMsg.cmpyInfoAptBldgNm_SS, (String) resultMap.get("CMPY_INFO_APT_BLDG_NM"));
            // State
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_SS, (String) resultMap.get("SHIP_TO_ST_CD"));
            // Floor
            ZYPEZDItemValueSetter.setValue(bizMsg.cmpyInfoFlNm_SS, (String) resultMap.get("CMPY_INFO_FL_NM"));
            // Postal
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_SS, (String) resultMap.get("SHIP_TO_POST_CD"));
            // Department
            ZYPEZDItemValueSetter.setValue(bizMsg.cmpyInfoDeptNm_SS, (String) resultMap.get("CMPY_INFO_DEPT_NM"));
            // Floor Protection Req
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_PROT_REQ_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevProtReqFlg_SS, (String) resultMap.get("ELEV_PROT_REQ_FLG"));
            }

            // Mod Start 2016/09/20 QC#13738
            // ///////////////////
            // Additional Comments
//            ZYPEZDItemValueSetter.setValue(bizMsg.siteSrvyAddlCmntTxt_SS, (String) resultMap.get("SITE_SRVY_ADDL_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxFldValTxt_SS, (String) resultMap.get("SITE_SRVY_ADDL_CMNT_TXT"));
            // Mod End 2016/09/20 QC#13738

            // ///////////////////
            // Site Information

            // No of Steps Outside
            ZYPEZDItemValueSetter.setValue(bizMsg.otsdStepNum_SS, (String) resultMap.get("OTSD_STEP_NUM"));
            // No of Steps Inside
            ZYPEZDItemValueSetter.setValue(bizMsg.insdStepNum_SS, (String) resultMap.get("INSD_STEP_NUM"));
            // Step Crawler required
            if (ZYPCommonFunc.hasValue((String) resultMap.get("STAIR_CRAW_REQ_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.stairCrawReqFlg_SS, (String) resultMap.get("STAIR_CRAW_REQ_FLG"));
            }
            // No of Flights
            ZYPEZDItemValueSetter.setValue(bizMsg.flgtStairNum_SS, (String) resultMap.get("FLGT_STAIR_NUM"));
            // Width of stairs and landings
            ZYPEZDItemValueSetter.setValue(bizMsg.stairAndLdgWdt_SS, (BigDecimal) resultMap.get("STAIR_AND_LDG_WDT"));
            // Loading Dock Available
            if (ZYPCommonFunc.hasValue((String) resultMap.get("LOAD_DOCK_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFlg_SS, (String) resultMap.get("LOAD_DOCK_AVAL_FLG"));
            }
            // Dock Height
            ZYPEZDItemValueSetter.setValue(bizMsg.loadDockHgt_SS, (BigDecimal) resultMap.get("LOAD_DOCK_HGT"));
            // 2018/01/09 S21_NA#18460(Sol#087) MOD START
            // Delivery Hours From
            //ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFromHourMn_SS, (String) resultMap.get("LOAD_DOCK_AVAL_FROM_HOUR_MN"));
            if (ZYPCommonFunc.hasValue((String) resultMap.get("LOAD_DOCK_AVAL_FROM_HOUR_MN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFromHourMn_SS, getConvertTime(bizMsg, (String) resultMap.get("LOAD_DOCK_AVAL_FROM_HOUR_MN")));
            }
            // Delivery Hours To
            //ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalToHourMn_SS, (String) resultMap.get("LOAD_DOCK_AVAL_TO_HOUR_MN"));
            if (ZYPCommonFunc.hasValue((String) resultMap.get("LOAD_DOCK_AVAL_TO_HOUR_MN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalToHourMn_SS, getConvertTime(bizMsg, (String) resultMap.get("LOAD_DOCK_AVAL_TO_HOUR_MN")));
            }
            // 2018/01/09 S21_NA#18460(Sol#087) MOD END
            // Tractor Trailer Accessible
            if (ZYPCommonFunc.hasValue((String) resultMap.get("TRCTR_AND_TRAIL_ACCS_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.trctrAndTrailAccsFlg_SS, (String) resultMap.get("TRCTR_AND_TRAIL_ACCS_FLG"));
            }
            // 2018/01/09 S21_NA#18460(Sol#087) MOD START
            // Timestop
            //ZYPEZDItemValueSetter.setValue(bizMsg.carrDelyTmHourMn_SS, (String) resultMap.get("CARR_DELY_TM_HOUR_MN"));
            if (ZYPCommonFunc.hasValue((String) resultMap.get("CARR_DELY_TM_HOUR_MN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.carrDelyTmHourMn_SS, getConvertTime(bizMsg, (String) resultMap.get("CARR_DELY_TM_HOUR_MN")));
            }
            // 2018/01/09 S21_NA#18460(Sol#087) MOD END
            // Timestop
            ZYPEZDItemValueSetter.setValue(bizMsg.delyTrnspOptCd_SS, (String) resultMap.get("DELY_TRNSP_OPT_CD"));

            // ///////////////////
            // Elevator Information & Dimensions
            // Elevator Available
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_AVAL_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFlg_SS, (String) resultMap.get("ELEV_AVAL_FLG"));
            }
            // 2018/01/09 S21_NA#18460(Sol#087) MOD START
            // Elevator Hoiurs From
            //ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFromHourMn_SS, (String) resultMap.get("ELEV_AVAL_FROM_HOUR_MN"));
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_AVAL_FROM_HOUR_MN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFromHourMn_SS, getConvertTime(bizMsg, (String) resultMap.get("ELEV_AVAL_FROM_HOUR_MN")));
            }
            // Elevator Hoiurs To
            //ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalToHourMn_SS, (String) resultMap.get("ELEV_AVAL_TO_HOUR_MN"));
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_AVAL_TO_HOUR_MN"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalToHourMn_SS, getConvertTime(bizMsg, (String) resultMap.get("ELEV_AVAL_TO_HOUR_MN")));
            }
            // 2018/01/09 S21_NA#18460(Sol#087) MOD END
            // Elevetor Appinted Needed
            if (ZYPCommonFunc.hasValue((String) resultMap.get("ELEV_APPT_REQ_FLG"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.elevApptReqFlg_SS, (String) resultMap.get("ELEV_APPT_REQ_FLG"));
            }
            // Elevator Contact Parson Name
            ZYPEZDItemValueSetter.setValue(bizMsg.elevCtacPsnNm_SS, (String) resultMap.get("ELEV_CTAC_PSN_NM"));
            // Elevator Contact Phone
            ZYPEZDItemValueSetter.setValue(bizMsg.elevCtacTelNum_SS, (String) resultMap.get("ELEV_CTAC_TEL_NUM"));

            // Building Entrance(Height)
            ZYPEZDItemValueSetter.setValue(bizMsg.bldgEntDoorHgt_SS, (BigDecimal) resultMap.get("BLDG_ENT_DOOR_HGT"));
            // Building Entrance(Width)
            ZYPEZDItemValueSetter.setValue(bizMsg.bldgEntDoorWdt_SS, (BigDecimal) resultMap.get("BLDG_ENT_DOOR_WDT"));
            // Building Entrance(Corridor Width)
            ZYPEZDItemValueSetter.setValue(bizMsg.crdrWdt_SS, (BigDecimal) resultMap.get("CRDR_WDT"));
            // Building Entrance(Door Width)
            ZYPEZDItemValueSetter.setValue(bizMsg.doorWdt_SS, (BigDecimal) resultMap.get("DOOR_WDT"));

            // Elevator(Width)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevWdt_SS, (BigDecimal) resultMap.get("ELEV_WDT"));
            // Elevator(Depth)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevDepthNum_SS, (BigDecimal) resultMap.get("ELEV_DEPTH_NUM"));
            // Elevator(Capacity)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevCapWt_SS, (BigDecimal) resultMap.get("ELEV_CAP_WT"));
            // Door Opening(Height)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevDoorHgt_SS, (BigDecimal) resultMap.get("ELEV_DOOR_HGT"));
            // Door Opening(Height)
            ZYPEZDItemValueSetter.setValue(bizMsg.elevDoorWdt_SS, (BigDecimal) resultMap.get("ELEV_DOOR_WDT"));

            // EZD update Time
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_SS, (String) resultMap.get("EZUPTIME"));
            // EZD Update TimeZone
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_SS, (String) resultMap.get("EZUPTIMEZONE"));
        }
    }

    /**
     * Set Contact List
     * @param bizMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     */
    private void setCtacList(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        S21SsmEZDResult ssmResult = null;

        // Configuration Number is not empty
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdPosnNum_H0)) {
            ssmResult = NWAL1510Query.getInstance().getCtacListbyConfNum(bizMsg, sMsg);
        } else {
            ssmResult = NWAL1510Query.getInstance().getCtacListbyOrdNum(bizMsg, sMsg);
        }

        for (int i = 0; i < bizMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(i), null, bizMsg.C.no(i), null);
            } else {
                break;
            }
        }
        bizMsg.C.setValidCount(sMsg.C.getValidCount());
    }

    /**
     * Add Contact Row
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     */
    private void doProcess_NWAL1510Scrn00_Add_Row(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        // 2018/07/23 S21_NA#26188 Mod sMsg→bizMsg Start
        // copyCMsgToSMsg(bizMsg, sMsg);
        // int addRowIndex = sMsg.C.getValidCount();
        int addRowIndex = bizMsg.C.getValidCount();

        // sMsg.C.setValidCount(addRowIndex + 1);
        bizMsg.C.setValidCount(addRowIndex + 1);

        // ZYPEZDItemValueSetter.setValue(sMsg.C.no(addRowIndex).ctacPsnTpCd_C0, bizMsg.ctacPsnTpCd_L0.no(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(addRowIndex).ctacPsnTpCd_C0, bizMsg.ctacPsnTpCd_L0.no(0));
        // QC#16452 add Start
        // set default
        // ZYPEZDItemValueSetter.setValue(sMsg.C.no(addRowIndex).ctacCustRefTpCd_C0, bizMsg.L.no(0).ctacCustRefTpCd_L.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(addRowIndex).ctacCustRefTpCd_C0, bizMsg.L.no(0).ctacCustRefTpCd_L.getValue());
        // QC#16452 add End

        // copySMsgToCMsg(bizMsg, sMsg);
        // 2018/07/23 S21_NA#26188 Mod sMsg→bizMsg End
    }

    /**
     * Delete Contact Row
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     */
    private void doProcess_NWAL1510Scrn00_Delete_Row(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {

        // copyCMsgToSMsg(bizMsg, sMsg); // 2018/07/23 S21_NA#26188 Mod sMsg→bizMsg

        List<Integer> chkYList = ZYPTableUtil.getSelectedRows(bizMsg.C, "delFlg_C0", ZYPConstant.FLG_ON_Y);

        for (int chkYRow : chkYList) {
            if (ZYPCommonFunc.hasValue(bizMsg.C.no(chkYRow).dsCpoCtacPsnPk_C0)) {
                EZDMsg.copy(sMsg.C.no(chkYRow), "C0", sMsg.D.no(sMsg.D.getValidCount()), "D0");
                sMsg.D.setValidCount(sMsg.D.getValidCount() + 1);
            }
        }

        // ZYPTableUtil.deleteRows(sMsg.C, chkYList);
        ZYPTableUtil.deleteRows(bizMsg.C, chkYList);

        // copySMsgToCMsg(bizMsg, sMsg); // 2018/07/23 S21_NA#26188 Mod sMsg→bizMsg
    }

    /**
     * Delete Contact Row
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     */
    private void clearAll(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.D);

        bizMsg.C.setValidCount(0);
        sMsg.C.setValidCount(0);
        sMsg.D.setValidCount(0);

        bizMsg.dsSiteSrvyPk_SS.clear();
        // 2016/10/12 S21_NA#13021 Mod Start
//        bizMsg.shipToLocNm_SS.clear();
        bizMsg.dsAcctNm_SS.clear();
        // 2016/10/12 S21_NA#13021 Mod End
        bizMsg.shipToCustLocCd_SS.clear();
        bizMsg.shipToFirstLineAddr_SS.clear();
        bizMsg.shipToCtyAddr_SS.clear();
        bizMsg.cmpyInfoAptBldgNm_SS.clear();
        bizMsg.shipToStCd_SS.clear();
        bizMsg.cmpyInfoFlNm_SS.clear();
        bizMsg.shipToPostCd_SS.clear();
        bizMsg.cmpyInfoDeptNm_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod Start
        //bizMsg.elevProtReqFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.elevProtReqFlg_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod End
        // Mod Start 2016/09/20 QC#13738
//        bizMsg.siteSrvyAddlCmntTxt_SS.clear();
        bizMsg.xxFldValTxt_SS.clear();
        // Mod End 2016/09/20 QC#13738
        bizMsg.otsdStepNum_SS.clear();
        bizMsg.insdStepNum_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod Start
        //bizMsg.stairCrawReqFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.stairCrawReqFlg_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod End
        bizMsg.flgtStairNum_SS.clear();
        bizMsg.stairAndLdgWdt_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod Start
        //bizMsg.loadDockAvalFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.loadDockAvalFlg_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod End
        bizMsg.loadDockHgt_SS.clear();
        bizMsg.loadDockAvalFromHourMn_SS.clear();
        bizMsg.loadDockAvalToHourMn_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod Start
        //bizMsg.trctrAndTrailAccsFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.trctrAndTrailAccsFlg_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod End
        bizMsg.carrDelyTmHourMn_SS.clear();
        bizMsg.delyTrnspOptCd_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod Start
        //bizMsg.elevAvalFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        //bizMsg.elevApptReqFlg_SS.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.elevAvalFlg_SS.clear();
        bizMsg.elevApptReqFlg_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod End
        bizMsg.elevAvalFromHourMn_SS.clear();
        bizMsg.elevAvalToHourMn_SS.clear();
        bizMsg.elevCtacPsnNm_SS.clear();
        bizMsg.elevCtacTelNum_SS.clear();
        bizMsg.bldgEntDoorHgt_SS.clear();
        bizMsg.bldgEntDoorWdt_SS.clear();
        bizMsg.crdrWdt_SS.clear();
        bizMsg.doorWdt_SS.clear();
        bizMsg.elevWdt_SS.clear();
        bizMsg.elevDepthNum_SS.clear();
        bizMsg.elevCapWt_SS.clear();
        bizMsg.elevDoorHgt_SS.clear();
        bizMsg.elevDoorWdt_SS.clear();
        bizMsg.ezUpTime_SS.clear();
        bizMsg.ezUpTimeZone_SS.clear();
        // 2016/07/11 S21_NA#5030 Mod Start
        //ZYPEZDItemValueSetter.setValue(bizMsg.rqstIstlTm_AP, bizMsg.xxTpCd.no(0));
        bizMsg.rqstIstlTm_AP.clear();
        //ZYPEZDItemValueSetter.setValue(bizMsg.opsFromHourMn_AP, bizMsg.xxTpCd.no(0));
        bizMsg.opsFromHourMn_AP.clear();
        //ZYPEZDItemValueSetter.setValue(bizMsg.opsToHourMn_AP, bizMsg.xxTpCd.no(0));
        bizMsg.opsToHourMn_AP.clear();
        //ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFromHourMn_AP, bizMsg.xxTpCd.no(0));
        bizMsg.loadDockAvalFromHourMn_AP.clear();
        //ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalToHourMn_AP, bizMsg.xxTpCd.no(0));
        bizMsg.loadDockAvalToHourMn_AP.clear();
        //ZYPEZDItemValueSetter.setValue(bizMsg.carrDelyTmHourMn_AP, bizMsg.xxTpCd.no(0));
        bizMsg.carrDelyTmHourMn_AP.clear();
        //ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFromHourMn_AP, bizMsg.xxTpCd.no(0));
        bizMsg.elevAvalFromHourMn_AP.clear();
        //ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalToHourMn_AP, bizMsg.xxTpCd.no(0));
        bizMsg.elevAvalToHourMn_AP.clear();
        // 2016/07/11 S21_NA#5030 Mod End
        bizMsg.dsCpoIstlInfoPk_DI.clear();
        bizMsg.svcIstlRuleNum_DI.clear();
        bizMsg.istlDivCd_DI.clear();
        bizMsg.istlTechCd_DI.clear();
        bizMsg.techNm_DI.clear();
        bizMsg.rqstIstlDt_DI.clear();
        bizMsg.rqstIstlTm_DI.clear();
        bizMsg.istlAddlCmntTxt_DI.clear();
        bizMsg.ezUpTime_IS.clear();
        bizMsg.ezUpTimeZone_IS.clear();
        bizMsg.dsCpoDelyInfoPk_DI.clear();
        bizMsg.opsFromHourMn_DI.clear();
        bizMsg.opsToHourMn_DI.clear();
        // 2016/07/11 S21_NA#5030 Mod Start
        //bizMsg.loadDockAvalFlg_DI.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.loadDockAvalFlg_DI.clear();
        //bizMsg.stairCrawReqFlg_DI.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.stairCrawReqFlg_DI.clear();
        // 2016/07/11 S21_NA#5030 Mod End
        bizMsg.stairCrawNum_DI.clear();
        // 2016/07/11 S21_NA#5030 Mod Start
        //bizMsg.elevAvalFlg_DI.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.elevAvalFlg_DI.clear();
        // 2016/07/11 S21_NA#5030 Mod End
        bizMsg.delyAddlCmntTxt_DI.clear();
        bizMsg.ezUpTime_DI.clear();
        bizMsg.ezUpTimeZone_DI.clear();
        // 2019/12/26 QC#54725 Add Srart
        bizMsg.delySchdStsCd_DI.clear();
        bizMsg.xxAttDataCmntTxt_DI.clear();
        // 2019/12/26 QC#54725 Add End
        bizMsg.dsOrdCatgDescTxt_H0.clear();
        bizMsg.dsOrdTpDescTxt_H0.clear();
        bizMsg.shipToCustAcctCd_H0.clear();
        bizMsg.shipToCustLocCd_H0.clear();
        // 2016/10/12 S21_NA#13021 Mod Start
//        bizMsg.shipToLocNm_H0.clear();
//        bizMsg.firstLineAddr_H0.clear();
//        bizMsg.scdLineAddr_H0.clear();
//        bizMsg.dsCpoConfigPk_H0.clear();
//        bizMsg.dsOrdCatgDescTxt_H0.clear();
//        bizMsg.dsOrdTpDescTxt_H0.clear();
//        bizMsg.shipToCustAcctCd_H0.clear();
//        bizMsg.shipToCustLocCd_H0.clear();
//        bizMsg.shipToLocNm_H0.clear();
        // 2016/10/12 S21_NA#13021 Mod End
        bizMsg.firstLineAddr_H0.clear();
        bizMsg.scdLineAddr_H0.clear();
        bizMsg.xxSvcFromHourMnTxt_OP.clear();
        bizMsg.xxSvcToHourMnTxt_OP.clear();
        bizMsg.xxSvcFromHourMnTxt_RQ.clear();
        bizMsg.xxSvcFromHourMnTxt_LD.clear();
        bizMsg.xxSvcToHourMnTxt_LD.clear();
        bizMsg.xxSvcFromHourMnTxt_CD.clear();
        bizMsg.xxSvcFromHourMnTxt_EA.clear();
        bizMsg.xxSvcToHourMnTxt_EA.clear();
        bizMsg.xxRsltFlg_DI.clear();
        bizMsg.xxRsltFlg_SS.clear();
        bizMsg.xxRsltFlg_CO.clear();
        bizMsg.xxRsltFlg_DS.clear(); // 2020/02/10 QC#54725-1 Add
    }

    // 2017/02/09 S21_NA#17500 Add Star
    /**
     * PageJump Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1510Scrn00_PageJump(NWAL1510CMsg bizMsg, NWAL1510SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue((bizMsg.B.length() * (bizMsg.xxPageShowCurNum.getValueInt() - 1)) + 1);
        NWAL1510CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);

    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1510Scrn00_PageNext(NWAL1510CMsg bizMsg, NWAL1510SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1510CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1510Scrn00_PagePrev(NWAL1510CMsg bizMsg, NWAL1510SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.B.length());
        NWAL1510CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B);

    }
    // 2017/02/09 S21_NA#17500 Add End
    // QC#16452 Add Start
    /**
     * do Process (NWAL1510_NMAL6770)
     * @param bizMsg NWAL1510CMsg
     */
    private void doProcess_NWAL1510_NMAL6770(NWAL1510CMsg bizMsg) {

        int idx = bizMsg.xxCellIdx_CO.getValueInt();
        bizMsg.C.no(idx).ctacCustRefTpCd_CP.clear();
        String ctacTpCd = bizMsg.P.no(1).xxPopPrm.getValue();
        String ctacCustRefTpCd = "";
        String locNum = "";
        if (ZYPCommonFunc.hasValue(bizMsg.C.no(idx).ctacCustRefTpCd_C0)) {
            ctacCustRefTpCd = bizMsg.C.no(idx).ctacCustRefTpCd_C0.getValue();
        } else {
            ctacCustRefTpCd = CTAC_CUST_REF_TP.SHIP_TO;
        }

        if (CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
            locNum = bizMsg.billToCustLocCd_H0.getValue();

        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(ctacCustRefTpCd)) {
            locNum = bizMsg.shipToCustLocCd_H0.getValue();

        } else if (CTAC_CUST_REF_TP.SOLD_TO.equals(ctacCustRefTpCd)) {
            locNum = bizMsg.soldToCustLocCd_H0.getValue();
        }

        
        S21SsmEZDResult ssmResult = NWAL1510Query.getInstance().getCtacCustRefTp(bizMsg, ctacTpCd, locNum);

        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).ctacCustRefTpCd_CP, ctacCustRefTpCd);// not null -> protect
        }

    }
    // QC#16452 Add End
    
    // 2018/01/05 S21_NA#18460 ADD START 
    private String getTmZoneCd(String ctryCd, String postCd) {
        SvcTimeZoneInfo timeZoneInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, ZYPDateUtil.getSalesDate(), ctryCd, postCd);
        
        return timeZoneInfo.getTimeZone();
    }

    private String getConvertTime(NWAL1510CMsg bizMsg, String time) {
        String convertTime = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC,//
                ZYPDateUtil.getSalesDate() + time, bizMsg.ctryCd_H0.getValue(), bizMsg.postCd_H0.getValue()).getDateTime().substring(TIME_SUBSTRING_FROM, TIME_SUBSTRING_TO);

        return convertTime;
    }
    // 2018/01/05 S21_NA#18460 ADD END

    // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD START
    private void doProcess_NWAL1510Scrn00_OnChange_InstallType(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {
        NWAL1510CommonLogic.getServiceInstallTypeEditableFlag(bizMsg);
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxRsltFlg_IT.getValue())) {
            bizMsg.rqstIstlTm_AP.clear();
            bizMsg.xxSvcFromHourMnTxt_RQ.clear();
        }

        // 2019/11/01 QC#53993 Add Start
        if (S21StringUtil.isEquals(bizMsg.svcIstlRuleNum_DI.getValue(), SVC_ISTL_RULE_NUM_NO_INSTALL)){
            bizMsg.istlDivCd_DI.clear();
        }
        // 2019/11/01 QC#53993 Add End
    }
    // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD END

    // 2019/11/01 QC#53993 Add Start
    private void doProcess_NWAL1510Scrn00_OnChange_ToBeInstalledBy(NWAL1510CMsg bizMsg, NWAL1510SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.istlBySvcPrvdPtyCd_DI)){
            boolean isChangeInstallType = NWAL1510CommonLogic.getInstallTypeAndInstallDivision(bizMsg, getGlobalCompanyCode());

            if (isChangeInstallType){
                doProcess_NWAL1510Scrn00_OnChange_InstallType(bizMsg, sMsg);
            }
        } else {
            bizMsg.istlDivCd_DI.clear();
        }
    }
    // 2019/11/01 QC#53993 Add End

    // 2018/08/02 S21_NA#26188-2 Add Start
    private void setDefRadioBtn(NWAL1510CMsg bizMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.loadDockAvalFlg_DI)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFlg_DI, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.stairCrawReqFlg_DI)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.stairCrawReqFlg_DI, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.elevAvalFlg_DI)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFlg_DI, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.elevProtReqFlg_SS)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.elevProtReqFlg_SS, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.stairCrawReqFlg_SS)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.stairCrawReqFlg_SS, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.loadDockAvalFlg_SS)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.loadDockAvalFlg_SS, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.trctrAndTrailAccsFlg_SS)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.trctrAndTrailAccsFlg_SS, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.elevAvalFlg_SS)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.elevAvalFlg_SS, ZYPConstant.FLG_OFF_N);
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.elevApptReqFlg_SS)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.elevApptReqFlg_SS, ZYPConstant.FLG_OFF_N);
        }
    }
    // 2018/08/02 S21_NA#26188-2 Add End

}
