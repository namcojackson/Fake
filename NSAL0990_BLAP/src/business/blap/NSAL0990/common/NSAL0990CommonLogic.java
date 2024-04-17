/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0990.common;

import static business.blap.NSAL0990.constant.NSAL0990Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.blap.NSAL0990.NSAL0990Query;
import business.blap.NSAL0990.NSAL0990_CCMsg;
import business.blap.NSAL0990.NSAL0990_ECMsg;
import business.blap.NSAL0990.NSAL0990_FCMsg;
import business.blap.NSAL0990.NSAL0990_HCMsg;
import business.blap.NSAL0990.constant.NSAL0990Constant;
import business.db.CCYTMsg;
import business.db.FRT_CONDTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SUB_CONTR_BR_MAPTMsg;
import business.db.SVC_SPLY_ABUSE_STAGETMsg;
import business.db.SVC_SPLY_ABUSE_STAGETMsgArray;
import business.db.SVC_SPLY_ORDTMsg;
import business.db.SVC_SPLY_ORD_DTLTMsg;
import business.db.SVC_SPLY_ORD_TP_RELNTMsg;
import business.db.SVC_SPLY_ORD_TP_RELNTMsgArray;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC002001_ContactPointInfoListPMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NMZC611001PMsg;
import business.parts.NSZC048001PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_COLOR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Supply Order
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         K.Kasai         Create          N/A
 * 2016/03/08   Hitachi         A.Kohinata      Update          QC#5143
 * 2016/03/09   Hitachi         A.Kohinata      Update          QC#5196
 * 2016/03/10   Hitachi         A.Kohinata      Update          QC#5210
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5354
 * 2016/03/14   Hitachi         A.Kohinata      Update          QC#5375
 * 2016/03/16   Hitachi         A.Kohinata      Update          QC#5530
 * 2016/03/16   Hitachi         A.Kohinata      Update          QC#5540
 * 2016/03/23   Hitachi         A.Kohinata      Update          QC#5908
 * 2016/03/29   Hitachi         A.Kohinata      Update          QC#5790
 * 2016/03/31   Hitachi         K.Kasai         Update          QC#6344
 * 2016/04/05   Hitachi         K.Kasai         Update          QC#6583
 * 2016/06/09   Hitachi         M.Gotou         Update          QC#9628
 * 2016/06/27   Hitachi         K.Kasai         Update          QC#9888
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2016/09/21   Hitachi         A.Kohinata      Update          QC#13267
 * 2016/10/04   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/10/13   Hitachi         A.Kohinata      Update          QC#9885
 * 2016/10/19   Hitachi         A.Kohinata      Update          QC#15352
 * 2016/10/19   Hitachi         A.Kohinata      Update          QC#15344
 * 2016/10/20   Hitachi         A.Kohinata      Update          QC#15323
 * 2016/10/25   Hitachi         A.Kohinata      Update          QC#15338
 * 2017/01/06   Hitachi         K.Ochiai        Update          QC#16012
 * 2017/05/09   Hitachi         K.Kitachi       Update          QC#18277
 * 2017/07/03   Hitachi         K.Kitachi       Update          QC#19026
 * 2017/09/28   Hitachi         U.Kim           Update          QC#18746
 * 2017/11/22   Hitachi         M.Kidokoro      Update          QC#20497
 * 2017/12/05   Hitachi         M.Kidokoro      Update          QC#21992
 * 2017/12/26   Fujitsu         K.Ishizuka      Update          QC#20164(Sol#356)
 * 2018/01/15   Hitachi         K.Kojima        Update          QC#23214
 * 2018/01/26   Hitachi         K.Kojima        Update          QC#22797
 * 2018/02/09   Hitachi         M.Kidokoro      Update          QC#23065
 * 2018/02/13   Hitachi         M.Kidokoro      Update          QC#23144
 * 2018/02/14   Hitachi         U.Kim           Update          QC#20297(Sol#379)
 * 2018/02/21   Hitachi         M.Kidokoro      Update          QC#23144-1
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/04/26   Hitachi         U.Kim           Update          QC#22293
 * 2018/05/15   CITS            T.Wada          Update          QC#24819
 * 2018/05/17   Fujitsu         Nagashima       Update          QC#25440
 * 2018/05/25   CITS            M.Naito         Update          QC#15410
 * 2018/05/30   Fujitsu         Nagashima       Update          QC#26157
 * 2018/06/01   Fujitsu         Nagashima       Update          QC#25966
 * 2018/06/21   CITS            Wada            Update          QC#22796
 * 2018/07/03   Hitachi         K.Kitachi       Update          QC#26924
 * 2018/07/04   CITS            T.Wada          Update          QC#23726
 * 2018/07/11   Hitachi         K.Kojima        Update          QC#26976
 * 2018/07/19   Hitachi         K.Kitachi       Update          QC#26978
 * 2018/07/30   Hitachi         T.Tomita        Update          QC#14307
 * 2018/08/09   Hitachi         K.Kim           Update          QC#27251
 * 2018/08/14   Hitachi         K.Kim           Update          QC#27251-1
 * 2018/08/24   CITS            T.Kikuhara      Update          QC#27853
 * 2018/08/24   CITS            T.Kikuhara      Update          QC#27746
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * 2018/09/20   CITS            T.Wada          Update          QC#28333
 * 2018/09/25   Hitachi         K.Kitachi       Update          QC#26260
 * 2018/10/09   Hitachi         K.Kojima        Update          QC#28692
 * 2018/11/09   Hitachi         K.Kim           Update          QC#29169
 * 2018/11/15   Fujitsu         M.Yamada        Update          QC#29191
 * 2018/11/20   Fujitsu         M.Yamada        Update          QC#29302
 * 2018/12/12   Fujitsu         H.Kumagai       Update          QC#29315
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 * 2019/06/17   Fujitsu         W.Honda         Update          QC#50842
 * 2019/06/19   Hitachi         K.Kim           Update          QC#50879
 * 2019/07/09   Hitachi         K.Kim           Update          QC#50966
 * 2020/02/10   Hitachi         K.Kim           Update          QC#55795
 * 2022/05/18   CITS            K.Ogino         Update          QC#60044
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2023/04/03   CITS            T.Wada          Update          QC#61362
 * 2023/04/13   CITS            F.Fadriquela    Update          QC#61366
 * 2024/03/07   Hitachi         K.Kishimoto     Update          QC#63547
 *</pre>
 */
public class NSAL0990CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL0990CMsg
     */
    public static void createPullDown(NSAL0990CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_CD, cMsg.shpgSvcLvlDescTxt_NM);
        ZYPCodeDataUtil.createPulldownList(DS_PMT_METH.class, cMsg.dsPmtMethCd_CD, cMsg.dsPmtMethDescTxt_NM);
        createPeriodPullDown(cMsg);
        setValue(cMsg.xxCondCd, PERIOD_1);
    }

    /**
     * @param cMsg
     */
    private static void createPeriodPullDown(NSAL0990CMsg cMsg) {
        setValue(cMsg.xxCondCd_CD.no(0), PERIOD_1);
        setValue(cMsg.xxCondCd_CD.no(1), PERIOD_2);
        setValue(cMsg.xxCondCd_CD.no(2), PERIOD_3);
        setValue(cMsg.xxCondNm_NM.no(0), PERIOD_VAL_1);
        setValue(cMsg.xxCondNm_NM.no(1), PERIOD_VAL_2);
        setValue(cMsg.xxCondNm_NM.no(2), PERIOD_VAL_3);
    }

    // mod start 2019/01/21 QC#27304
    /**
     * Set Header Info
     * @param cMsg NSAL0990CMsg
     */
    public static void setHeaderInfo(NSAL0990CMsg cMsg, String initFlg) {
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;
        Map<String, Object> rsltMap = null;

        // get SVC_MACH_MSTR Info
        rslt = query.getSvcMachMstr(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            rsltMap = rsltList.get(0);
            setValue(cMsg.serNum, (String) rsltMap.get("SER_NUM"));
            // add start 2016/06/27 CSA Defect#9888
            setValue(cMsg.serNum_HD, (String) rsltMap.get("SER_NUM"));
            // add end 2016/06/27 CSA Defect#9888
            if (ZYPConstant.FLG_ON_Y.equals(initFlg)) {
                if (!ZYPCommonFunc.hasValue(cMsg.ownrAcctNum)) {
                    setValue(cMsg.ownrAcctNum, (String) rsltMap.get("OWNR_ACCT_NUM"));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.ownrLocNum)) {
                    setValue(cMsg.ownrLocNum, (String) rsltMap.get("OWNR_LOC_NUM"));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.billToAcctNum)) {
                    setValue(cMsg.billToAcctNum, (String) rsltMap.get("BILL_TO_ACCT_NUM"));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.billToLocNum)) {
                    setValue(cMsg.billToLocNum, (String) rsltMap.get("BILL_TO_LOC_NUM"));
                    setValue(cMsg.billToLocNum_D, (String) rsltMap.get("BILL_TO_LOC_NUM"));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.curLocAcctNum)) {
                    setValue(cMsg.curLocAcctNum, (String) rsltMap.get("CUR_LOC_ACCT_NUM"));
                }
                if (!ZYPCommonFunc.hasValue(cMsg.curLocNum)) {
                    setValue(cMsg.curLocNum, (String) rsltMap.get("CUR_LOC_NUM"));
                }
            }
            setValue(cMsg.xxScrItem34Txt, (String) rsltMap.get("T_MDL_NM"));
            setValue(cMsg.t_MdlId, (BigDecimal) rsltMap.get("T_MDL_ID"));
            setValue(cMsg.brCd, (String) rsltMap.get("FIN_BR_CD"));
            setValue(cMsg.coaBrNm, (String) rsltMap.get("COA_BR_NM"));
            setValue(cMsg.svcConfigMstrPk, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
            setValue(cMsg.svcByLineBizTpCd, (String) rsltMap.get("SVC_BY_LINE_BIZ_TP_CD"));
            // add start 2016/10/20 CSA Defect#15323
            setValue(cMsg.slsRepCd, (String) rsltMap.get("TOC_CD"));
            // add end 2016/10/20 CSA Defect#15323
            // START 2018/09/10 K.Kitachi [QC#26260, ADD]
            setValue(cMsg.sldByLineBizTpCd, (String) rsltMap.get("SLD_BY_LINE_BIZ_TP_CD"));
            // END 2018/09/10 K.Kitachi [QC#26260, ADD]
        }

        setHeaderInfoCommon(cMsg);
    }

    private static void setHeaderInfoCommon(NSAL0990CMsg cMsg) {
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;
        Map<String, Object> rsltMap = null;
        String svcTermCondAttrbNm = null;
        String tonerTp = null;
        String stplIncl = null;
        String yieldTp = null;

        // get DS_CONTR Info
        rslt = query.getDsContr(cMsg.glblCmpyCd.getValue(), cMsg.dsContrDtlPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            rsltMap = rsltList.get(0);
            setValue(cMsg.dsContrPk, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
            setValue(cMsg.dsContrNum, (String) rsltMap.get("DS_CONTR_NUM"));
            setValue(cMsg.dsContrNum_A, (String) rsltMap.get("DS_CONTR_NUM"));
            setValue(cMsg.dsContrCatgCd, (String) rsltMap.get("DS_CONTR_CATG_CD"));
            setValue(cMsg.dsContrCatgDescTxt, (String) rsltMap.get("DS_CONTR_CATG_DESC_TXT"));
            setValue(cMsg.dsAcctNum, (String) rsltMap.get("DS_ACCT_NUM"));
            if (!hasValue(cMsg.ownrAcctNum)) {
                setValue(cMsg.ownrAcctNum, cMsg.dsAcctNum);
            }
            setValue(cMsg.svcPgmMdseCd, (String) rsltMap.get("SVC_PGM_MDSE_CD"));
            setValue(cMsg.ccyCd, (String) rsltMap.get("CCY_CD"));
         // START 2016/09/21 N.Arai [QC#11616, MOD]
         // setValue(cMsg.mdseNm_SP, (String) rsltMap.get("MDSE_NM"));
            setValue(cMsg.mdseDescShortTxt_SP, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
         // END 2016/09/21 N.Arai [QC#11616, MOD]
            // START 2018/07/11 K.Kojima [QC#26976,DEL]
            // if (DS_CONTR_CATG.FLEET.equals((String) rsltMap.get("DS_CONTR_CATG_CD"))) {
            //     // mod start 2016/10/04 CSA Defect#12898
            //     //StringBuilder str = new StringBuilder();
            //     //str.append("FLT_");
            //     //str.append((String) rsltMap.get("DS_CONTR_NUM"));
            //     //setValue(cMsg.xxScrItem34Txt, str.toString());
            //     setValue(cMsg.xxScrItem34Txt, getFltMdlNm(cMsg));
            //     // mod end 2016/10/04 CSA Defect#12898
            // }
            // END 2018/07/11 K.Kojima [QC#26976,DEL]
            if (!hasValue(cMsg.coaBrNm)) {
                setValue(cMsg.brCd, (String) rsltMap.get("SVC_CONTR_BR_CD"));
                setValue(cMsg.coaBrNm, (String) rsltMap.get("COA_BR_NM"));
            }
            setValue(cMsg.tocCd, (String) rsltMap.get("TOC_CD"));
            if (!hasValue(cMsg.svcByLineBizTpCd)) {
                setValue(cMsg.svcByLineBizTpCd, (String) rsltMap.get("SVC_LINE_BIZ_CD"));
            }
            // START 2018/01/15 K.Kojima [QC#23214,ADD]
            String hdrRepUseFlg = (String) rsltMap.get("HDR_REP_USE_FLG");
            if (hdrRepUseFlg != null && ZYPConstant.FLG_ON_Y.equals(hdrRepUseFlg)) {
                setValue(cMsg.slsRepCd, (String) rsltMap.get("TOC_CD"));
            }
            // END 2018/01/15 K.Kojima [QC#23214,ADD]
        }

        // add start 2016/10/04 CSA Defect#12898
        if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
            setFltLineInfo(cMsg);
        }
        // add end 2016/10/04 CSA Defect#12898

        // get Toner Type(Term and Condition)
        svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_TONER_TP, cMsg.glblCmpyCd.getValue());
        // mod start 2016/10/04 CSA Defect#12898
        tonerTp = query.getSvcTermCondDataDispTxt(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.dsContrDtlPk.getValue(), cMsg.dsContrPk.getValue(), svcTermCondAttrbNm, cMsg.dsContrCatgCd.getValue());
        // mod end 2016/10/04 CSA Defect#12898
        setValue(cMsg.svcTermCondDataDispTxt_01, tonerTp);

        // START 2018/05/25 M.Naito [QC#15410, ADD]
        // get Staples Included(Term and Condition)
        svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_STAPLE_INCL, cMsg.glblCmpyCd.getValue());
        S21SsmEZDResult resltContrDtlRec = query.getContrDtlKeysByMachMstrPk(cMsg);
        if (resltContrDtlRec.isCodeNormal()) {
            List<Map<String, Object>> contrDtlList = (List<Map<String, Object>>) resltContrDtlRec.getResultObject();
            String getStplIncl = null;
            for (Map<String, Object> contrDtlRec : contrDtlList) {
                getStplIncl = query.getSvcTermCondDataDispTxt(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), (BigDecimal) contrDtlRec.get("DS_CONTR_DTL_PK"), (BigDecimal) contrDtlRec.get("DS_CONTR_PK"), svcTermCondAttrbNm,
                        cMsg.dsContrCatgCd.getValue());
                if (!SVC_TERM_COND_DATA_YES.equals(stplIncl)) {
                    stplIncl = getStplIncl;
                }
            }
        }
        // mod start 2016/10/04 CSA Defect#12898
//        stplIncl = query.getSvcTermCondDataDispTxt(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.dsContrDtlPk.getValue(), cMsg.dsContrPk.getValue(), svcTermCondAttrbNm, cMsg.dsContrCatgCd.getValue());
        // END 2018/05/25 M.Naito [QC#15410, ADD]
        // mod end 2016/10/04 CSA Defect#12898
        // START 2016/06/09 M.Gotou [QC#9628, ADD]
        if (!hasValue(stplIncl)) {
            stplIncl = ZYPConstant.FLG_OFF_N;
        }
        // END 2016/06/09 M.Gotou [QC#9628, ADD]
        setValue(cMsg.svcTermCondDataDispTxt_02, stplIncl);

        // get Toner Yield(Term and Condition)
        svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_TONER_YIELD, cMsg.glblCmpyCd.getValue());
        // mod start 2016/10/04 CSA Defect#12898
        yieldTp = query.getSvcTermCondDataDispTxt(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.dsContrDtlPk.getValue(), cMsg.dsContrPk.getValue(), svcTermCondAttrbNm, cMsg.dsContrCatgCd.getValue());
        // mod end 2016/10/04 CSA Defect#12898
        setValue(cMsg.svcTermCondDataDispTxt_03, yieldTp);
    }
    // mod end 2019/01/21 QC#27304

    /**
     * Set Order Header Details
     * @param cMsg NSAL0990CMsg
     */
    public static void setOrdHdrDtl(NSAL0990CMsg cMsg) {
        // get BILL_TO Info
        getBillToAddr(cMsg);
        // get SHIP_TO Info
        getShipToAddr(cMsg);
    }

    // Mod Start 2018/07/31 QC#14307
    /**
     * @param cMsg NSAL0990CMsg
     */
    // public static void getShipToAddr(NSAL0990CMsg cMsg) {
    public static boolean getShipToAddr(NSAL0990CMsg cMsg) {
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;
        Map<String, Object> rsltMap = null;
        rslt = query.getShipToInfo(cMsg);
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            rsltMap = rsltList.get(0);
            setValue(cMsg.xxLocAddrNm_A4, (String) rsltMap.get("LOC_NM"));
            // 2018/12/12 Add Start QC#29315
            setValue(cMsg.locNum, (String) rsltMap.get("LOC_NUM"));
            // 2018/12/12 Add End QC#29315
            setValue(cMsg.xxLocAddrNm_A5, (String) rsltMap.get("FIRST_LINE_ADDR"));
            setValue(cMsg.xxLocAddrNm_A6, (String) rsltMap.get("SCD_LINE_ADDR"));
            setValue(cMsg.ctyAddr_A2, (String) rsltMap.get("CTY_ADDR"));
            setValue(cMsg.stCd_A2, (String) rsltMap.get("ST_CD"));
//            setValue(cMsg.telNum_A2, (String) rsltMap.get("TEL_NUM"));
            setValue(cMsg.postCd_A2, (String) rsltMap.get("POST_CD"));  //QC#25440 mod
            setValue(cMsg.ctryCd_A2, (String) rsltMap.get("CTRY_CD"));
            // START 2017/09/28 U.Kim [QC#18746, ADD]
            setValue(cMsg.curLocAcctNum, (String) rsltMap.get("SELL_TO_CUST_CD"));
            // START 2017/09/28 U.Kim [QC#18746, ADD]
            return true;
        } else {
            cMsg.xxLocAddrNm_A4.clear();
            cMsg.xxLocAddrNm_A5.clear();
            cMsg.xxLocAddrNm_A6.clear();
            cMsg.ctyAddr_A2.clear();
            cMsg.stCd_A2.clear();
            cMsg.postCd_A2.clear();
            cMsg.ctryCd_A2.clear();
            cMsg.curLocAcctNum.clear();
            cMsg.curLocNum_SI.clear();
            return false;
        }
    }
    // Mod End 2018/07/31 QC#14307

    // Mod Start 2018/07/31 QC#14307
    /**
     * @param cMsg NSAL0990CMsg
     */
    // public static void getBillToAddr(NSAL0990CMsg cMsg) {
    public static boolean getBillToAddr(NSAL0990CMsg cMsg) {
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;
        Map<String, Object> rsltMap = null;
        rslt = query.getBillToInfo(cMsg.glblCmpyCd.getValue(), cMsg.billToLocNum.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            rsltMap = rsltList.get(0);
            setValue(cMsg.xxLocAddrNm_A1, (String) rsltMap.get("LOC_NM"));
            setValue(cMsg.xxLocAddrNm_A2, (String) rsltMap.get("FIRST_LINE_ADDR"));
            setValue(cMsg.xxLocAddrNm_A3, (String) rsltMap.get("SCD_LINE_ADDR"));
            setValue(cMsg.ctyAddr_A1, (String) rsltMap.get("CTY_ADDR"));
            setValue(cMsg.stCd_A1, (String) rsltMap.get("ST_CD"));
//            setValue(cMsg.telNum_A1, (String) rsltMap.get("TEL_NUM"));
            setValue(cMsg.postCd_A1, (String) rsltMap.get("POST_CD"));  //QC#25440 mod
            setValue(cMsg.ctryCd_A1, (String) rsltMap.get("CTRY_CD"));
            setValue(cMsg.xxLocAddrNm_D, (String) rsltMap.get("LOC_NM"));
            return true;
        } else {
            cMsg.xxLocAddrNm_A1.clear();
            cMsg.xxLocAddrNm_A2.clear();
            cMsg.xxLocAddrNm_A3.clear();
            cMsg.ctyAddr_A1.clear();
            cMsg.stCd_A1.clear();
            cMsg.postCd_A1.clear();
            cMsg.ctryCd_A1.clear();
            cMsg.xxLocAddrNm_D.clear();
            cMsg.billToLocNum_SI.clear();
            return false;
        }
    }
    // Mod End 2018/07/31 QC#14307

    /**
     * @param cMsg NSAL0990CMsg
     */
    private static void getBillToNm(NSAL0990CMsg cMsg) {
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;
        Map<String, Object> rsltMap = null;
        rslt = query.getBillToInfo(cMsg.glblCmpyCd.getValue(), cMsg.billToLocNum_D.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            rsltMap = rsltList.get(0);
            setValue(cMsg.xxLocAddrNm_D, (String) rsltMap.get("LOC_NM"));
        }
    }

    /**
     * Set Shipping Info
     * @param cMsg NSAL0990CMsg
     */
    public static void setShpgInfo(NSAL0990CMsg cMsg) {
        // get Carrier Code
        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(cMsg);
        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            return;
        }
        setValue(cMsg.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum);
    }

    // QC#23726 Add Start
    public static void setDefCarrSvcLvl(NSAL0990CMsg cMsg) {

        cMsg.carrAcctNum.clear();
        cMsg.carrSvcLvlCd.clear();
        cMsg.carrSvcLvlDescTxt.clear();

        // get Carrier Code
        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(cMsg);
        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            return;
        }
        String frtCondCd = cMsg.frtCondCd.getValue();
        if (ZYPCommonFunc.hasValue(frtCondCd) && FRT_COND.COLLECT.equals(frtCondCd)) {
            setValue(cMsg.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum);
        } else {
            cMsg.carrAcctNum.clear();
        }

        String vndCd = defCarrApiPMsg.vndCd_O.getValue();
        if (ZYPCommonFunc.hasValue(vndCd)) {
            S21SsmEZDResult ssmResult = NSAL0990Query.getInstance().getCarrSvcLvl(cMsg, vndCd);
            if (ssmResult.isCodeNormal()) {
                Map<String, String> carrSvcLvlInfo = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(cMsg.carrSvcLvlCd, carrSvcLvlInfo.get("CARR_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.carrSvcLvlDescTxt, carrSvcLvlInfo.get("CARR_SVC_LVL_DESC_TXT"));
            }
        }
    }
    public static void setCarrSvcLvl(NSAL0990CMsg cMsg) {
        String vndCd = NSAL0990Query.getInstance().getCarrCd(cMsg);
        if (ZYPCommonFunc.hasValue(vndCd)) {
            S21SsmEZDResult ssmResult = NSAL0990Query.getInstance().getCarrSvcLvl(cMsg, vndCd);
            if (ssmResult.isCodeNormal()) {
                Map<String, String> carrSvcLvlInfo = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(cMsg.carrSvcLvlCd, carrSvcLvlInfo.get("CARR_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.carrSvcLvlDescTxt, carrSvcLvlInfo.get("CARR_SVC_LVL_DESC_TXT"));
            }
        } else {
            cMsg.carrSvcLvlCd.clear();
            cMsg.carrSvcLvlDescTxt.clear();
        }
    }
    // QC#23726 Add End
    // START 2017/11/22 M.Kidokoro [QC#20497, ADD]
    /**
     * Set Shipping Info
     * @param cMsg NSAL0990CMsg
     */
    public static void setDefFrtCond(NSAL0990CMsg cMsg) {

        // START 2018/02/14 U.Kim [QC#20297(Sol#379), ADD]
        if (setDefFrtInfoFromCustInfoApi(cMsg)) {
            return;
        }
        // END 2018/02/14 U.Kim [QC#20297(Sol#379), ADD]

        // get Freight Terms
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult ssmResult = query.getDefaultFreightTermInfo(cMsg);

        if (ssmResult.isCodeNotFound()) {
            cMsg.frtCondCd.clear();
            cMsg.frtCondDescTxt.setErrorInfo(1, NSAM0011E, new String[] {"Freight Terms" });
            cMsg.setMessageInfo(NSAM0011E, new String[] {"Freight Terms" }); // QC#29191
            return;
        }
        Map<String, String> freightTermInfoList = (Map<String, String>) ssmResult.getResultObject();
        cMsg.frtCondCd.clear();
        cMsg.frtCondDescTxt.clear();
        cMsg.shpgSvcLvlCd.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd, freightTermInfoList.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.frtCondDescTxt, freightTermInfoList.get("FRT_COND_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd, freightTermInfoList.get("DEF_SHPG_SVC_LVL_CD"));

        NSAL0990CommonLogic.setShpgSvcLvlPullDown(cMsg);
    }
    // END 2017/11/22 M.Kidokoro [QC#20497, ADD]

    /**
     * Set Toner Allotments Info
     * @param cMsg NSAL0990CMsg
     */
    public static void setTonerAllotInfo(NSAL0990CMsg cMsg) {

        // add start 2016/10/04 CSA Defect#12898
        if (isCappedContract(cMsg)) {
            setValue(cMsg.xxDplyCtrlFlg_A, ZYPConstant.FLG_OFF_N);
            return;
        } else {
            setValue(cMsg.xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
        }
        // add end 2016/10/04 CSA Defect#12898

        // add start 2016/10/04 CSA Defect#12898
        if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
            setFltTonerAllotInfo(cMsg);
            return;
        }
        // add end 2016/10/04 CSA Defect#12898

        // get MDSE Info
        SVC_SPLY_ABUSE_STAGETMsgArray outTMsgArray = getSvcSplyAbuseStage(cMsg, cMsg.dsContrPk.getValue(), cMsg.svcPgmMdseCd.getValue());
        if (outTMsgArray.getValidCount() > 0) {
            SVC_SPLY_ABUSE_STAGETMsg outTMsg = (SVC_SPLY_ABUSE_STAGETMsg) outTMsgArray.get(0);
            setValue(cMsg.dsContrNum_A, cMsg.dsContrNum);
            if (hasValue(outTMsg.bwPrrtQty)) {
                setValue(cMsg.bwPrrtQty_A, outTMsg.bwPrrtQty);
            } else {
                setValue(cMsg.bwPrrtQty_A, BigDecimal.ZERO);
            }
            if (hasValue(outTMsg.colorPrrtQty)) {
                setValue(cMsg.colorPrrtQty_A, outTMsg.colorPrrtQty);
            } else {
                setValue(cMsg.colorPrrtQty_A, BigDecimal.ZERO);
            }
            setValue(cMsg.totQty_A, cMsg.bwPrrtQty_A.getValue().add(cMsg.colorPrrtQty_A.getValue()));
        } else {
            setValue(cMsg.bwPrrtQty_A, BigDecimal.ZERO);
            setValue(cMsg.colorPrrtQty_A, BigDecimal.ZERO);
            setValue(cMsg.totQty_A, cMsg.bwPrrtQty_A.getValue().add(cMsg.colorPrrtQty_A.getValue()));
        }
    }

    /**
     * Set Toner Allotments Info
     * @param cMsg NSAL0990CMsg
     */
    public static void setOrderHist(NSAL0990CMsg cMsg) {
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;

        // get Order History
        // mod start 2016/10/04 CSA Defect#12898
        rslt = query.getOrderHistory(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.svcConfigMstrPk.getValue(), cMsg.xxCondCd.getValue(), cMsg.dsOrdCatgCd.getValue(), cMsg.dsContrNum.getValue());
        // mod end 2016/10/04 CSA Defect#12898
        ZYPTableUtil.clear(cMsg.B);
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int i = 0;
            for (Map<String, Object> rsltMap : rsltList) {
                setValue(cMsg.B.no(i).xxOrdDt_B, (String) rsltMap.get("CPO_ORD_DT"));
                setValue(cMsg.B.no(i).cpoOrdNum_B, (String) rsltMap.get("CPO_ORD_NUM"));
                setValue(cMsg.B.no(i).mdseCd_B, (String) rsltMap.get("MDSE_CD"));
             // START 2016/09/21 N.Arai [QC#11616, MOD]
             // setValue(cMsg.B.no(i).mdseNm_B, (String) rsltMap.get("MDSE_NM"));
                setValue(cMsg.B.no(i).mdseDescShortTxt_B, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
             // END 2016/09/21 N.Arai [QC#11616, MOD]
                setValue(cMsg.B.no(i).ordQty_B, (BigDecimal) rsltMap.get("ORD_QTY"));
                setValue(cMsg.B.no(i).ordLineStsNm_B, (String) rsltMap.get("ORD_LINE_STS_NM"));
                i++;
                cMsg.B.setValidCount(i);
            }
        }
    }

    // mod start 2019/01/21 QC#27304
    /**
     * Set Line Detail
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    // mod start 2016/10/19 CSA Defect#15293
    public static void setLineDetail(NSAL0990CMsg cMsg) {

    // mod end 2016/10/19 CSA Defect#15293
        // get Service Program Info
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;

        // get Line Detail
        cMsg.mdseCd.clear();
     // START 2016/09/21 N.Arai [QC#11616, MOD]
     // cMsg.mdseNm.clear();
        cMsg.mdseDescShortTxt.clear();
     // END 2016/09/21 N.Arai [QC#11616, MOD]
        ZYPTableUtil.clear(cMsg.C);

        // add start 2016/10/04 CSA Defect#12898
        if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
            // START 2017/05/09 K.Kitachi [QC#18277, MOD]
//            rslt = query.getFltLineDetailForSupplyOrder(cMsg.glblCmpyCd.getValue(), cMsg.dsContrPk.getValue(), cMsg.slsDt.getValue());
            rslt = query.getFltLineDetailForSupplyOrder(cMsg.glblCmpyCd.getValue(), cMsg.dsContrPk.getValue(), cMsg.slsDt.getValue(), cMsg.C.length());
            // END 2017/05/09 K.Kitachi [QC#18277, MOD]
            if (rslt != null && rslt.isCodeNormal()) {
                rsltList = (List<Map<String, Object>>) rslt.getResultObject();
                int i = 0;
                for (Map<String, Object> rsltMap : rsltList) {
                    // START 2017/05/09 K.Kitachi [QC#18277, ADD]
                    if (cMsg.C.getValidCount() == cMsg.C.length()) {
                        cMsg.setMessageInfo(NSAL0990Constant.NSAM0684W, new String[] {String.valueOf(cMsg.C.length()), "Line Details" });
                        break;
                    }
                    // END 2017/05/09 K.Kitachi [QC#18277, ADD]

                    // QC#24819 Add Start
                    // Item registered in Model Supply is displayed as a list. However, if it is not Staples Inclusive, do not display it。
                    String imgSplyTpCd = (String) rsltMap.get("IMG_SPLY_TP_CD");
                    if (ZYPCommonFunc.hasValue(imgSplyTpCd) && IMG_SPLY_TP.STAPLE.equals(imgSplyTpCd)) {
                        if (!("Yes".equals(cMsg.svcTermCondDataDispTxt_02.getValue()))) {
                            continue;
                        }
                    }
                    // QC#24819 Add End

                    // START 2018/07/19 K.Kitachi [QC#26978, MOD]
//                    setValue(cMsg.C.no(i).serNum_C, cMsg.serNum);
                    setValue(cMsg.C.no(i).serNum_C, NSAL0990Constant.SFX_FLT_SER_NUM + cMsg.dsContrNum.getValue());
                    // END 2018/07/19 K.Kitachi [QC#26978, MOD]
                    setValue(cMsg.C.no(i).mdseCd_C, (String) rsltMap.get("MDSE_CD"));
                    setValue(cMsg.C.no(i).mdseDescShortTxt_C, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
                    setValue(cMsg.C.no(i).cpoMinOrdQty_C, (BigDecimal) rsltMap.get("CPO_MIN_ORD_QTY"));
                    setValue(cMsg.C.no(i).inPoundWt_C, (BigDecimal) rsltMap.get("IN_POUND_WT"));
                    // START 2018/08/09 K.Kim [QC#27251, DEL]
                    // setValue(cMsg.C.no(i).ordCustUomQty_C, BigDecimal.ZERO);
                    // END 2018/08/09 K.Kim [QC#27251, DEL]
                    setValue(cMsg.C.no(i).entCpoDtlDealSlsAmt_C, BigDecimal.ZERO);
                    setValue(cMsg.C.no(i).blackColorFlg_C, (String) rsltMap.get("BLACK_COLOR_FLG"));
                    setValue(cMsg.C.no(i).xxDplyCtrlFlg_C, ZYPConstant.FLG_ON_Y);
                    // START 2018/07/19 K.Kitachi [QC#26978, MOD]
//                    setValue(cMsg.C.no(i).prcCondManDelFlg_C, ZYPConstant.FLG_OFF_N);
//                    setValue(cMsg.C.no(i).xxTblSortNum_C, BigDecimal.ONE);
//                    setValue(cMsg.C.no(i).xxSortNum_C, new BigDecimal(i));
                    if (cMsg.svcMachMstrPk.getValue().compareTo(cMsg.svcMachMstrPk_BK.getValue()) == 0) {
                        setValue(cMsg.C.no(i).prcCondManDelFlg_C, ZYPConstant.FLG_OFF_N);
                        setValue(cMsg.C.no(i).xxTblSortNum_C, BigDecimal.ONE);
                        setValue(cMsg.C.no(i).xxSortNum_C, new BigDecimal(i));
                    } else {
                        setValue(cMsg.C.no(i).prcCondManDelFlg_C, ZYPConstant.FLG_ON_Y);
                        setValue(cMsg.C.no(i).xxTblSortNum_C, getMaxTblSortNum(cMsg).add(BigDecimal.ONE));
                        setValue(cMsg.C.no(i).xxSortNum_C, new BigDecimal(i));
                    }
                    // END 2018/07/19 K.Kitachi [QC#26978, MOD]
                    setValue(cMsg.C.no(i).imgSplyColorTpNm_C, (String) rsltMap.get("IMG_SPLY_COLOR_TP_NM"));
                    // add start 2016/10/13 CSA Defect#9885
                    setValue(cMsg.C.no(i).t_MdlId_C, (BigDecimal) rsltMap.get("MDL_ID"));
                    // add end 2016/10/13 CSA Defect#9885
                    // START 2017/12/05 M.Kidokoro [QC#21992, ADD]
                    setValue(cMsg.C.no(i).imgSplyOemCd_C, (String) rsltMap.get("IMG_SPLY_OEM_CD"));
                    // END 2017/12/05 M.Kidokoro [QC#21992, ADD]
                    // START 2018/06/01 H.Nagashima [QC#25966, ADD]
                    setValue(cMsg.C.no(i).imgSplyTpCd_C, (String) rsltMap.get("IMG_SPLY_TP_CD"));
                    // End 2018/06/01 H.Nagashima [QC#25966, ADD]

                    // START 2018/07/19 K.Kitachi [QC#26978, ADD]
                    setValue(cMsg.C.no(i).dsContrNum_C, cMsg.dsContrNum);
                    setValue(cMsg.C.no(i).dsContrCatgCd_C, cMsg.dsContrCatgCd);
                    // END 2018/07/19 K.Kitachi [QC#26978, ADD]

                    // get Price List
                    NWZC157001PMsg prcApiPMsg = callPricingApiOfGetBasePriceMode(cMsg, cMsg.C.no(i).mdseCd_C.getValue(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.intValue());
                    // set Field Amount
                    if (checkPrcApiParam(cMsg, prcApiPMsg)) {
                        NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
                        setValue(cMsg.C.no(i).entDealNetUnitPrcAmt_C, prcTotalPMsg.xxUnitGrsPrcAmt);
                    } else {
                        cMsg.setMessageInfo(null);
                        cMsg.setMessageInfo(NSAM0745W);
                        setValue(cMsg.C.no(i).entDealNetUnitPrcAmt_C, BigDecimal.ZERO);
                    }

                    // START 2017/07/03 K.Kitachi [QC#19026, ADD]
                    // START 2018/01/26 K.Kojima [QC#22797,MOD]
                    // if (query.isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.C.no(i).mdseCd_C.getValue())) {
                    // START 2018/02/09 M.Kidokoro [QC#23065,DEL]
//                    if (query.isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.C.no(i).mdseCd_C.getValue(), cMsg.svcTermCondDataDispTxt_02.getValue())) {
                    // END 2018/02/09 M.Kidokoro [QC#23065,DEL]
                    // END 2018/01/26 K.Kojima [QC#22797,MOD]
                    // START 2018/02/09 M.Kidokoro [QC#23065,DEL]
//                        setValue(cMsg.C.no(i).entDealNetUnitPrcAmt_C, BigDecimal.ZERO);
//                    }
                    // END 2018/02/09 M.Kidokoro [QC#23065,DEL]
                    // END 2017/07/03 K.Kitachi [QC#19026, ADD]

                    i++;
                    cMsg.C.setValidCount(i);
                }
                setValue(cMsg.xxTotAmt_C, BigDecimal.ZERO);
            }
            return;
        }
        // add end 2016/10/04 CSA Defect#12898

        // START 2017/05/09 K.Kitachi [QC#18277, MOD]
//        rslt = query.getLineDetailForSupplyOrder(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk.getValue(), cMsg.t_MdlId.getValue(), cMsg.slsDt.getValue());
        rslt = query.getLineDetailForSupplyOrder(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk.getValue(), cMsg.t_MdlId.getValue(), cMsg.slsDt.getValue(), cMsg.C.length());
        // END 2017/05/09 K.Kitachi [QC#18277, MOD]
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int i = 0;
            for (Map<String, Object> rsltMap : rsltList) {
                // START 2017/05/09 K.Kitachi [QC#18277, ADD]
                if (cMsg.C.getValidCount() == cMsg.C.length()) {
                    cMsg.setMessageInfo(NSAL0990Constant.NSAM0684W, new String[] {String.valueOf(cMsg.C.length()), "Line Details" });
                    break;
                }

                // QC#24819 Add Start
                // Item registered in Model Supply is displayed as a list. However, if it is not Staples Inclusive, do not display it。
                String imgSplyTpCd = (String) rsltMap.get("IMG_SPLY_TP_CD");
                if (ZYPCommonFunc.hasValue(imgSplyTpCd) && IMG_SPLY_TP.STAPLE.equals(imgSplyTpCd)) {
                    if (!("Yes".equals(cMsg.svcTermCondDataDispTxt_02.getValue()))) {
                        continue;
                    }
                }
                // QC#24819 Add End

                // END 2017/05/09 K.Kitachi [QC#18277, ADD]
                setValue(cMsg.C.no(i).serNum_C, cMsg.serNum);
                setValue(cMsg.C.no(i).mdseCd_C, (String) rsltMap.get("MDSE_CD"));
             // START 2016/09/21 N.Arai [QC#11616, MOD]
             // setValue(cMsg.C.no(i).mdseNm_C, (String) rsltMap.get("MDSE_NM"));
                setValue(cMsg.C.no(i).mdseDescShortTxt_C, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
             // END 2016/09/21 N.Arai [QC#11616, MOD]
                // del start 2016/10/04 CSA Defect#12898
                //setValue(cMsg.C.no(i).xxAllwQty_C, (BigDecimal) rsltMap.get("ALLW_QTY"));
                //setValue(cMsg.C.no(i).xxUsedQty_C, (BigDecimal) rsltMap.get("USED_QTY"));
                // del end 2016/10/04 CSA Defect#12898
                setValue(cMsg.C.no(i).cpoMinOrdQty_C, (BigDecimal) rsltMap.get("CPO_MIN_ORD_QTY"));
                setValue(cMsg.C.no(i).inPoundWt_C, (BigDecimal) rsltMap.get("IN_POUND_WT"));
                // START 2018/08/09 K.Kim [QC#27251, DEL]
                // setValue(cMsg.C.no(i).ordCustUomQty_C, BigDecimal.ZERO);
                // END 2018/08/09 K.Kim [QC#27251, DEL]
                setValue(cMsg.C.no(i).entCpoDtlDealSlsAmt_C, BigDecimal.ZERO);
                setValue(cMsg.C.no(i).blackColorFlg_C, (String) rsltMap.get("BLACK_COLOR_FLG"));
                setValue(cMsg.C.no(i).xxDplyCtrlFlg_C, ZYPConstant.FLG_ON_Y);
                if (cMsg.svcMachMstrPk.getValue().compareTo(cMsg.svcMachMstrPk_BK.getValue()) == 0) {
                    setValue(cMsg.C.no(i).prcCondManDelFlg_C, ZYPConstant.FLG_OFF_N);
                    setValue(cMsg.C.no(i).xxTblSortNum_C, BigDecimal.ONE);
                    setValue(cMsg.C.no(i).xxSortNum_C, new BigDecimal(i));
                } else {
                    setValue(cMsg.C.no(i).prcCondManDelFlg_C, ZYPConstant.FLG_ON_Y);
                    setValue(cMsg.C.no(i).xxTblSortNum_C, getMaxTblSortNum(cMsg).add(BigDecimal.ONE));
                    setValue(cMsg.C.no(i).xxSortNum_C, new BigDecimal(i));
                }
                // add start 2016/10/13 CSA Defect#9885
                setValue(cMsg.C.no(i).t_MdlId_C, cMsg.t_MdlId);
                // add end 2016/10/13 CSA Defect#9885
                // START 2017/12/05 M.Kidokoro [QC#21992, ADD]
                setValue(cMsg.C.no(i).imgSplyOemCd_C, (String) rsltMap.get("IMG_SPLY_OEM_CD"));
                // END 2017/12/05 M.Kidokoro [QC#21992, ADD]
                // START 2018/06/01 H.Nagashima [QC#25966, ADD]
                setValue(cMsg.C.no(i).imgSplyTpCd_C, (String) rsltMap.get("IMG_SPLY_TP_CD"));
                // End 2018/06/01 H.Nagashima [QC#25966, ADD]

                // START 2018/07/19 K.Kitachi [QC#26978, ADD]
                setValue(cMsg.C.no(i).imgSplyColorTpNm_C, (String) rsltMap.get("IMG_SPLY_COLOR_TP_NM"));
                setValue(cMsg.C.no(i).dsContrNum_C, cMsg.dsContrNum);
                setValue(cMsg.C.no(i).dsContrCatgCd_C, cMsg.dsContrCatgCd);
                // END 2018/07/19 K.Kitachi [QC#26978, ADD]

                // get Price List
                NWZC157001PMsg prcApiPMsg = callPricingApiOfGetBasePriceMode(cMsg, cMsg.C.no(i).mdseCd_C.getValue(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.intValue());
                // set Field Amount
                if (checkPrcApiParam(cMsg, prcApiPMsg)) {
                    NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
                    setValue(cMsg.C.no(i).entDealNetUnitPrcAmt_C, prcTotalPMsg.xxUnitGrsPrcAmt);
                } else {
                    cMsg.setMessageInfo(null);
                    cMsg.setMessageInfo(NSAM0745W);
                    setValue(cMsg.C.no(i).entDealNetUnitPrcAmt_C, BigDecimal.ZERO);
                }

                // START 2017/07/03 K.Kitachi [QC#19026, ADD]
                // START 2018/01/26 K.Kojima [QC#22797,MOD]
                // if (query.isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.C.no(i).mdseCd_C.getValue())) {
                // START 2018/02/09 M.Kidokoro [QC#23065,DEL]
//                if (query.isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.C.no(i).mdseCd_C.getValue(), cMsg.svcTermCondDataDispTxt_02.getValue())) {
                // END 2018/02/09 M.Kidokoro [QC#23065,DEL]
                // END 2018/01/26 K.Kojima [QC#22797,MOD]
                // START 2018/02/09 M.Kidokoro [QC#23065,DEL]
//                    setValue(cMsg.C.no(i).entDealNetUnitPrcAmt_C, BigDecimal.ZERO);
//                }
                // END 2018/02/09 M.Kidokoro [QC#23065,DEL]
                // END 2017/07/03 K.Kitachi [QC#19026, ADD]

                i++;
                cMsg.C.setValidCount(i);
            }
            setValue(cMsg.xxTotAmt_C, BigDecimal.ZERO);
        }
        return;
    }
    // mod end 2019/01/21 QC#27304

    // mod start 2017/01/06 CSA Defect#16012
    /**
     * Set Supply Stats
     * @param cMsg NSAL0990CMsg
     */
    public static void setSupplyStats(NSAL0990CMsg cMsg) {
        if (hasValue(cMsg.billToLocNum_D)) {
            getBillToNm(cMsg);
        }
        // get Revenue Cost Info
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;

        int targetYr = Integer.parseInt(cMsg.slsDt.getValue().substring(0, 4));
        String targetlastYr = String.valueOf(targetYr - 1);
        String targetTwoYrAgo = String.valueOf(targetYr - 2);
        rslt = query.getRevenueCost(cMsg, String.valueOf(targetYr), targetTwoYrAgo);
        setValue(cMsg.dwhTrgtYr_01, String.valueOf(targetYr));
        setValue(cMsg.dwhTrgtYr_02, targetlastYr);
        setValue(cMsg.dwhTrgtYr_03, targetTwoYrAgo);
        clearCmsgPrftStats(cMsg);
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int i = 0;
            BigDecimal totRevAmt = BigDecimal.ZERO;
            BigDecimal totCostAmt = BigDecimal.ZERO;

            for (Map<String, Object> rsltMap : rsltList) {
                if (rsltMap.get("DWH_TRGT_YR").equals(String.valueOf(targetYr))) {
                    setValue(cMsg.totRevAmt_01, (BigDecimal) rsltMap.get("TOT_REV_AMT"));
                    setValue(cMsg.totCostAmt_01, (BigDecimal) rsltMap.get("TOT_COST_AMT"));
                    BigDecimal prftAmt = ((BigDecimal) rsltMap.get("TOT_REV_AMT")).subtract(((BigDecimal) rsltMap.get("TOT_COST_AMT")));
                    setValue(cMsg.totPrftAmt_01, prftAmt);
                    setValue(cMsg.totPrftPct_01, getPrftPct((BigDecimal) rsltMap.get("TOT_COST_AMT"), prftAmt));
                    totRevAmt = totRevAmt.add((BigDecimal) rsltMap.get("TOT_REV_AMT"));
                    totCostAmt = totCostAmt.add((BigDecimal) rsltMap.get("TOT_COST_AMT"));
                } else if (rsltMap.get("DWH_TRGT_YR").equals(targetlastYr)) {
                    setValue(cMsg.totRevAmt_02, (BigDecimal) rsltMap.get("TOT_REV_AMT"));
                    setValue(cMsg.totCostAmt_02, (BigDecimal) rsltMap.get("TOT_COST_AMT"));
                    BigDecimal prftAmt = ((BigDecimal) rsltMap.get("TOT_REV_AMT")).subtract(((BigDecimal) rsltMap.get("TOT_COST_AMT")));
                    setValue(cMsg.totPrftAmt_02, prftAmt);
                    setValue(cMsg.totPrftPct_02, getPrftPct((BigDecimal) rsltMap.get("TOT_COST_AMT"), prftAmt));
                    totRevAmt = totRevAmt.add((BigDecimal) rsltMap.get("TOT_REV_AMT"));
                    totCostAmt = totCostAmt.add((BigDecimal) rsltMap.get("TOT_COST_AMT"));
                } else {
                    setValue(cMsg.totRevAmt_03, (BigDecimal) rsltMap.get("TOT_REV_AMT"));
                    setValue(cMsg.totCostAmt_03, (BigDecimal) rsltMap.get("TOT_COST_AMT"));
                    BigDecimal prftAmt = ((BigDecimal) rsltMap.get("TOT_REV_AMT")).subtract(((BigDecimal) rsltMap.get("TOT_COST_AMT")));
                    setValue(cMsg.totPrftAmt_03, prftAmt);
                    setValue(cMsg.totPrftPct_03, getPrftPct((BigDecimal) rsltMap.get("TOT_COST_AMT"), prftAmt));
                    totRevAmt = totRevAmt.add((BigDecimal) rsltMap.get("TOT_REV_AMT"));
                    totCostAmt = totCostAmt.add((BigDecimal) rsltMap.get("TOT_COST_AMT"));
                }
                i++;
            }
            // Calc last Three Year Total
            BigDecimal totPrftAmt = BigDecimal.ZERO;
            totPrftAmt = totRevAmt.subtract(totCostAmt);
            setValue(cMsg.totRevAmt_04, totRevAmt);
            setValue(cMsg.totCostAmt_04, totCostAmt);
            setValue(cMsg.totPrftAmt_04, totPrftAmt);
            setValue(cMsg.totPrftPct_04, getPrftPct(totCostAmt, totPrftAmt));
        }
    }

    private static BigDecimal getPrftPct(BigDecimal totCostAmt, BigDecimal totPrftAmt) {
        BigDecimal totPrftPct = BigDecimal.ZERO;
        if (totCostAmt.compareTo(BigDecimal.ZERO) != 0) {
            totPrftPct = (totPrftAmt.multiply(ONE_HUNDRED)).divide(totCostAmt, 2, RoundingMode.HALF_UP);
            // START 2020/02/10 [QC#55795, ADD]
            if (totPrftPct.abs().compareTo(PCT_TOTAL_PROFIT_LIMIT) > 0) {
                totPrftPct = PCT_TOTAL_PROFIT_LIMIT.multiply(BigDecimal.valueOf(totPrftPct.signum()));
            }
            // END 2020/02/10 [QC#55795, ADD]
        }
        return totPrftPct;
    }

    private static void clearCmsgPrftStats(NSAL0990CMsg cMsg) {
        cMsg.totRevAmt_01.clear();
        cMsg.totCostAmt_01.clear();
        cMsg.totPrftAmt_01.clear();
        cMsg.totPrftPct_01.clear();
        cMsg.totRevAmt_02.clear();
        cMsg.totCostAmt_02.clear();
        cMsg.totPrftAmt_02.clear();
        cMsg.totPrftPct_02.clear();
        cMsg.totRevAmt_03.clear();
        cMsg.totCostAmt_03.clear();
        cMsg.totPrftAmt_03.clear();
        cMsg.totPrftPct_03.clear();
        cMsg.totRevAmt_04.clear();
        cMsg.totCostAmt_04.clear();
        cMsg.totPrftAmt_04.clear();
        cMsg.totPrftPct_04.clear();
    }

    // mod end 2017/01/06 CSA Defect#16012

    /**
     * Get Currency Digit Number
     * @param cMsg NSAL0990CMsg
     */
    public static void getCcyDigitNum(NSAL0990CMsg cMsg) {
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        glblTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        glblTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg != null) {
            CCYTMsg ccyMsg = new CCYTMsg();
            ccyMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
            ccyMsg.ccyCd.setValue(glblTMsg.stdCcyCd.getValue());
            ccyMsg = (CCYTMsg) EZDTBLAccessor.findByKey(ccyMsg);
            if (ccyMsg != null) {
                setValue(cMsg.aftDeclPntDigitNum, ccyMsg.aftDeclPntDigitNum);
            }
        }
    }

    /**
     * Get Additional Line Detail
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public static boolean getAddLineDetail(NSAL0990CMsg cMsg) {
        // get Service Program Info
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;

        //get MDSE info
        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue());

        // START 2018/10/09 K.Kojima [QC#28692,ADD]
        if (mdseTMsg == null) {
            cMsg.mdseCd.setErrorInfo(1, NSAM0413E);
            return false;
        }
        // END 2018/10/09 K.Kojima [QC#28692,ADD]

        // get Additional Line Detail
        rslt = query.getAddlLineDetail(cMsg.glblCmpyCd.getValue(), mdseTMsg.mdseCd.getValue(), cMsg.svcMachMstrPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            if (query.getSvcSplyMisc(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.mdseCd.getValue()) != null) {
                int i = cMsg.C.getValidCount();
                rsltList = (List<Map<String, Object>>) rslt.getResultObject();
                Map<String, Object> rsltMap = rsltList.get(0);
                setValue(cMsg.C.no(i).serNum_C, cMsg.serNum);
                setValue(cMsg.C.no(i).mdseCd_C, cMsg.mdseCd);
             // START 2016/09/21 N.Arai [QC#11616, MOD]
             // setValue(cMsg.C.no(i).mdseNm_C, (String) rsltMap.get("MDSE_NM"));
                setValue(cMsg.C.no(i).mdseDescShortTxt_C, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
             // END 2016/09/21 N.Arai [QC#11616, MOD]
                // del start 2016/10/04 CSA Defect#12898
                //setValue(cMsg.C.no(i).xxAllwQty_C, (BigDecimal) rsltMap.get("ALLW_QTY"));
                //setValue(cMsg.C.no(i).xxUsedQty_C, (BigDecimal) rsltMap.get("USED_QTY"));
                // del end 2016/10/04 CSA Defect#12898
                setValue(cMsg.C.no(i).cpoMinOrdQty_C, (BigDecimal) rsltMap.get("CPO_MIN_ORD_QTY"));
                setValue(cMsg.C.no(i).inPoundWt_C, (BigDecimal) rsltMap.get("IN_POUND_WT"));
                setValue(cMsg.C.no(i).blackColorFlg_C, (String) rsltMap.get("BLACK_COLOR_FLG"));
                if (cMsg.svcMachMstrPk.getValue().compareTo(cMsg.svcMachMstrPk_BK.getValue()) == 0) {
                    setValue(cMsg.C.no(i).prcCondManDelFlg_C, ZYPConstant.FLG_OFF_N);
                } else {
                    setValue(cMsg.C.no(i).prcCondManDelFlg_C, ZYPConstant.FLG_ON_Y);
                }
                // add start 2016/10/13 CSA Defect#9885
                setValue(cMsg.C.no(i).t_MdlId_C, cMsg.t_MdlId);
                // add end 2016/10/13 CSA Defect#9885
                // START 2017/12/05 M.Kidokoro [QC#21992, ADD]
                setValue(cMsg.C.no(i).imgSplyOemCd_C, (String) rsltMap.get("IMG_SPLY_OEM_CD"));
                // END 2017/12/05 M.Kidokoro [QC#21992, ADD]
            } else {
                cMsg.mdseCd.setErrorInfo(1, NSAM0011E, new String[] {"Entered Mede Code" });
                return false;
            }
        } else {
            cMsg.mdseCd.setErrorInfo(1, NSAM0413E);
            return false;
        }
        return true;
    }

    /**
     * Get Order Type
     * @param cMsg NSAL0990CMsg
     */
    public static void getOrdTp(NSAL0990CMsg cMsg) {
        // get suplIncl
        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
        CovTmplInfo tmplInfo = new CovTmplInfo();
        tmplInfo.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        tmplInfo.setSlsDt(cMsg.slsDt.getValue());
        tmplInfo.setSvcPgmMdseCd(cMsg.svcPgmMdseCd.getValue());
        boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
        // get laser Plus Contract
        boolean isLaserPlusContr = covTmpl.isLaserPlusContr(tmplInfo);
        // get Order Type
        SVC_SPLY_ORD_TP_RELNTMsg condition = new SVC_SPLY_ORD_TP_RELNTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        // START 2018/09/10 K.Kitachi [QC#26260, DEL]
//        condition.setConditionValue("lineBizTpCd01", cMsg.svcByLineBizTpCd.getValue());
        // END 2018/09/10 K.Kitachi [QC#26260, DEL]
        if (isSuplIncl) {
            condition.setConditionValue("splyInclFlg01", ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(cMsg.splyInclFlg, ZYPConstant.FLG_ON_Y); // QC#29302
        } else {
            condition.setConditionValue("splyInclFlg01", ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cMsg.splyInclFlg, ZYPConstant.FLG_OFF_N); // QC#29302
        }
        if (isLaserPlusContr) {
            condition.setConditionValue("laserPlusFlg01", ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(cMsg.laserPlusFlg, ZYPConstant.FLG_ON_Y); // QC#29302
        } else {
            condition.setConditionValue("laserPlusFlg01", ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cMsg.laserPlusFlg, ZYPConstant.FLG_OFF_N); // QC#29302
        }

        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//        SVC_SPLY_ORD_TP_RELNTMsgArray outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
//        if (outTMsgArray.getValidCount() > 0) {
//            setValue(cMsg.dsOrdCatgCd, outTMsgArray.no(0).dsOrdCatgCd);
//            setValue(cMsg.dsOrdTpCd, outTMsgArray.no(0).dsOrdTpCd);
//            setValue(cMsg.dsOrdRsnCd, outTMsgArray.no(0).dsOrdRsnCd);
//            setValue(cMsg.dsOrdLineCatgCd, outTMsgArray.no(0).dsOrdLineCatgCd);
//        // add start 2016/04/05 CSA Defect#6583
//        } else {
//            condition.setConditionValue("lineBizTpCd01", DEF_LINE_BIZ_CD);
//            outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
//            if (outTMsgArray.getValidCount() > 0) {
//                setValue(cMsg.dsOrdCatgCd, outTMsgArray.no(0).dsOrdCatgCd);
//                setValue(cMsg.dsOrdTpCd, outTMsgArray.no(0).dsOrdTpCd);
//                setValue(cMsg.dsOrdRsnCd, outTMsgArray.no(0).dsOrdRsnCd);
//                setValue(cMsg.dsOrdLineCatgCd, outTMsgArray.no(0).dsOrdLineCatgCd);
//            }
//        }
//        // add end 2016/04/05 CSA Defect#6583
        SVC_SPLY_ORD_TP_RELNTMsgArray outTMsgArray = null;
        String convSldByLineBizTpCd = NSAL0990Query.getInstance().convLineBizTpToSvcLineBiz(cMsg.glblCmpyCd.getValue(), cMsg.sldByLineBizTpCd.getValue());
        if (hasValue(convSldByLineBizTpCd)) {
            condition.setConditionValue("lineBizTpCd01", convSldByLineBizTpCd);
            outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
            if (outTMsgArray.getValidCount() > 0) {
                setValue(cMsg.dsOrdCatgCd, outTMsgArray.no(0).dsOrdCatgCd);
                setValue(cMsg.dsOrdTpCd, outTMsgArray.no(0).dsOrdTpCd);
                setValue(cMsg.dsOrdRsnCd, outTMsgArray.no(0).dsOrdRsnCd);
                setValue(cMsg.dsOrdLineCatgCd, outTMsgArray.no(0).dsOrdLineCatgCd);
                return;
            }
        }
        condition.setConditionValue("lineBizTpCd01", cMsg.svcByLineBizTpCd.getValue());
        outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (outTMsgArray.getValidCount() > 0) {
            setValue(cMsg.dsOrdCatgCd, outTMsgArray.no(0).dsOrdCatgCd);
            setValue(cMsg.dsOrdTpCd, outTMsgArray.no(0).dsOrdTpCd);
            setValue(cMsg.dsOrdRsnCd, outTMsgArray.no(0).dsOrdRsnCd);
            setValue(cMsg.dsOrdLineCatgCd, outTMsgArray.no(0).dsOrdLineCatgCd);
            return;
        }
        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
    }

    /**
     * Get SVC_SPLY_ABUSE_STAGE
     * @param cMsg NSAL0990CMsg
     * @param dsContrPk
     * @param svcPgmMdseCd
     * @return
     */
    private static SVC_SPLY_ABUSE_STAGETMsgArray getSvcSplyAbuseStage(NSAL0990CMsg cMsg, BigDecimal dsContrPk, String svcPgmMdseCd) {

        final SVC_SPLY_ABUSE_STAGETMsg condition = new SVC_SPLY_ABUSE_STAGETMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        condition.setConditionValue("dsContrPk01", dsContrPk);
        condition.setConditionValue("svcPgmMdseCd01", svcPgmMdseCd);
        condition.setConditionValue("frzFlg01", ZYPConstant.FLG_OFF_N);

        return (SVC_SPLY_ABUSE_STAGETMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Mandatory Check for Additional Header
     * @param cMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkMandatory(NSAL0990CMsg cMsg) {

        boolean isNormal = true;
        if (!hasValue(cMsg.billToLocNum)) {
            cMsg.billToLocNum.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Bill To" });
            isNormal = false;
        }
        if (!hasValue(cMsg.curLocNum)) {
            cMsg.curLocNum.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Ship To" });
            isNormal = false;
        }
        if (!hasValue(cMsg.frtCondDescTxt)) {
            cMsg.frtCondDescTxt.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Freight Terms" });
            isNormal = false;
        }
        if (!hasValue(cMsg.shpgSvcLvlCd)) {
            cMsg.shpgSvcLvlCd.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Service Level" });
            isNormal = false;
        }
        if (DS_PMT_METH.CREDIT_CARD.equals(cMsg.dsPmtMethCd.getValue()) && !hasValue(cMsg.dsCrCardPk)) {
            cMsg.dsPmtMethCd.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Credit Card" });
            isNormal = false;
        }
        // add start 2016/10/20 CSA Defect#15323
        // START 2018/09/25 K.Kitachi [QC#26260, DEL]
//        if (!hasValue(cMsg.slsRepCd)) {
//            cMsg.setMessageInfo(NSAL0990Constant.NSAM0179E, new String[] {"Sales Rep" });
//            isNormal = false;
//        }
        // END 2018/09/25 K.Kitachi [QC#26260, DEL]
        // add end 2016/10/20 CSA Defect#15323
        return isNormal;
    }

    /**
     * Mandatory Check for Additional Header
     * @param cMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkValidation(NSAL0990CMsg cMsg) {

        boolean isNormal = true;
        // check Bill To Cust and Account Code
        if (!checkBillToCust(cMsg)) {
            isNormal = false;
        }
        // check Ship To Cust and Account Code
        if (!checkShipToCust(cMsg)) {
            isNormal = false;
        }
        // check Bill To Cust and Ship To Cust
        // START 2017/09/28 U.Kim [QC18746#, ADD]
        if (!checkBillShipSoldRelation(cMsg)) {
            isNormal = false;
        }
        // END 2017/09/28 U.Kim [QC18746#, ADD]
        NSAL0990Query query = NSAL0990Query.getInstance();
//        // check Freight Terms
//        if (!checkValidationForCarrSvclvl(cMsg)) {
//            isNormal = false;
//        }
//        // check Carrier Shipping Service Level
//        if (!checkCarrSvcLvl(cMsg)) {
//            isNormal = false;
//        }
        // add start 2016/10/19 CSA Defect#15344
        // check Carrier Service Level
        if (!checkCarrSvcLvlRelation(cMsg)) {
            cMsg.carrSvcLvlCd.setErrorInfo(1, NSAM0614E);
            cMsg.carrSvcLvlDescTxt.setErrorInfo(1, NSAM0614E);
            isNormal = false;
        }
        // add end 2016/10/19 CSA Defect#15344
        if (hasValue(cMsg.carrSvcLvlCd)) {
            // check Carrier Shipping Service Level and Shipping
            // Service Level
            if (BigDecimal.ZERO.compareTo(query.checkCarrShpgSvcLvlAndShpgSvcLvl(cMsg)) == 0) {
                cMsg.carrSvcLvlCd.setErrorInfo(1, NSAM0138E, new String[] {"Carrier Service Level", "Service Level" });
                cMsg.carrSvcLvlDescTxt.setErrorInfo(1, NSAM0138E, new String[] {"Carrier Service Level", "Service Level" });
                isNormal = false;
            }
        }
        // check Freight Condition
        if (BigDecimal.ZERO.compareTo(query.checkFrtCondRelation(cMsg)) == 0) {
            // mod start 2016/10/19 CSA Defect#15344
            if (hasValue(cMsg.carrSvcLvlCd)) {
                cMsg.frtCondDescTxt.setErrorInfo(1, NSAM0040E, new String[] {"'Freight Terms' or 'Carrier Service Level'" });
                cMsg.carrSvcLvlCd.setErrorInfo(1, NSAM0040E, new String[] {"'Freight Terms' or 'Carrier Service Level'" });
                cMsg.carrSvcLvlDescTxt.setErrorInfo(1, NSAM0040E, new String[] {"'Freight Terms' or 'Carrier Service Level'" });
            } else {
                cMsg.frtCondDescTxt.setErrorInfo(1, NSAM0040E, new String[] {"'Freight Terms'" });
            }
            // mod end 2016/10/19 CSA Defect#15344
            isNormal = false;
        }
        // START 2019/06/19 [QC#50879, ADD]
        // check Shipping Instructions
        if (!checkShpgInstn(cMsg)) {
            isNormal = false;
        }
        // END 2019/06/19 [QC#50879, ADD]
        // check Credit Card
        // QC#27746 DEL START
        //if (hasValue(cMsg.dsCrCardPk) && !checkCrCard(cMsg)) {
        //    cMsg.billToLocNum.setErrorInfo(1, NSAM0138E, new String[] {"Credit Card", "Bill To" });
        //    cMsg.dsPmtMethCd.setErrorInfo(1, NSAM0138E, new String[] {"Credit Card", "Bill To" });
        //    isNormal = false;
        //}
        // QC#27746 DEL END
        // check PO#
        // mod start 2016/10/25 CSA Defect#15338
        if (!checkPO(cMsg)) {
            cMsg.custIssPoNum.setErrorInfo(1, NSAM0442E, new String[] {"PO#", "Customer", "PO Required" });
            isNormal = false;
        }
        // mod end 2016/10/25CSA Defect#15338
        // check Qty and Toner Cap
        // START 2018/06/01 H.Nagashima [QC#25966, ADD]
        List<String> trgtImgSplyTpCdList = new ArrayList<String>();
        String trgtImgSplyTpCdCsv = ZYPCodeDataUtil.getVarCharConstValue(NSAL0990Constant.CONST_TRGT_IMG_SPLY_TP, cMsg.glblCmpyCd.getValue());
        if (trgtImgSplyTpCdCsv != null) {
            trgtImgSplyTpCdList = Arrays.asList(trgtImgSplyTpCdCsv.split(","));
        }
        // END 2018/06/01 H.Nagashima [QC#25966, ADD]

        // get toner cap
        int tonerBwOrg = getTonerInfo(VAR_CHAR_TONER_CAP_BW_ORG, cMsg, query);
        int tonerClrOrg = getTonerInfo(VAR_CHAR_TONER_CAP_CLR_ORG, cMsg, query);
        int tonerTotOrg = getTonerInfo(VAR_CHAR_TONER_CAP_TOT_ORG, cMsg, query);
        int tonerBwRun = getTonerInfo(VAR_CHAR_TONER_CAP_BW_RUN, cMsg, query);
        int tonerClrRun = getTonerInfo(VAR_CHAR_TONER_CAP_CLR_RUN, cMsg, query);
        int tonerTotRun = getTonerInfo(VAR_CHAR_TONER_CAP_TOT_RUN, cMsg, query);

        int bwCnt = 0;
        int clrCnt = 0;
        int totCnt = 0;
        if (NSAL0990Constant.MODE_1.equals(cMsg.xxScrDply.getValue())) {
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                NSAL0990_CCMsg lineMsg = cMsg.C.no(i);
                // START 2018/06/01 H.Nagashima [QC#25966, ADD]
                if (!trgtImgSplyTpCdList.contains(lineMsg.imgSplyTpCd_C.getValue())) {
                    continue;
                }
                // END 2018/06/01 H.Nagashima [QC#25966, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.blackColorFlg_C.getValue())) {
                    bwCnt = bwCnt + lineMsg.ordCustUomQty_C.getValueInt();
                } else if (ZYPConstant.FLG_OFF_N.equals(lineMsg.blackColorFlg_C.getValue())) {
                    clrCnt = clrCnt + lineMsg.ordCustUomQty_C.getValueInt();
                }
            }
            totCnt = bwCnt + clrCnt;
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                NSAL0990_CCMsg lineMsg = cMsg.C.no(i);
                // START 2018/06/01 H.Nagashima [QC#25966, ADD]
                if (!trgtImgSplyTpCdList.contains(lineMsg.imgSplyTpCd_C.getValue())) {
                    continue;
                }
                // END 2018/06/01 H.Nagashima [QC#25966, ADD]
                // QC#27853 MOD START
                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.blackColorFlg_C.getValue()) && !checkTonerInfo(tonerBwOrg, tonerBwRun, bwCnt)) {
                    lineMsg.ordCustUomQty_C.setErrorInfo(1, NSAM0421E, new String[] {"Black Toner", Integer.toString(tonerBwOrg), Integer.toString(tonerBwRun) });
                    isNormal = false;
                } else if (ZYPConstant.FLG_OFF_N.equals(lineMsg.blackColorFlg_C.getValue()) && !checkTonerInfo(tonerClrOrg, tonerClrRun, clrCnt)) {
                    lineMsg.ordCustUomQty_C.setErrorInfo(1, NSAM0421E, new String[] {"Color Toner", Integer.toString(tonerClrOrg), Integer.toString(tonerClrRun) });
                    isNormal = false;
                }
                if (ZYPCommonFunc.hasValue(lineMsg.blackColorFlg_C) && !checkTonerInfo(tonerTotOrg, tonerTotRun, totCnt)) {
                    lineMsg.ordCustUomQty_C.setErrorInfo(1, NSAM0421E, new String[] {"Total Toner", Integer.toString(tonerTotOrg), Integer.toString(tonerTotRun) });
                    isNormal = false;
                }
                // QC#27853 MOD END
            }
        } else {
            for (int i = 0; i < cMsg.E.getValidCount(); i++) {
                NSAL0990_ECMsg lineMsg = cMsg.E.no(i);
                // START 2018/06/01 H.Nagashima [QC#25966, ADD]
                if (!trgtImgSplyTpCdList.contains(lineMsg.imgSplyTpCd_E.getValue())) {
                    continue;
                }
                // END 2018/06/01 H.Nagashima [QC#25966, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.blackColorFlg_E.getValue())) {
                    bwCnt = bwCnt + lineMsg.ordCustUomQty_E.getValueInt();
                } else if (ZYPConstant.FLG_OFF_N.equals(lineMsg.blackColorFlg_E.getValue())) {
                    clrCnt = clrCnt + lineMsg.ordCustUomQty_E.getValueInt();
                }
            }
            totCnt = bwCnt + clrCnt;
            for (int i = 0; i < cMsg.E.getValidCount(); i++) {
                NSAL0990_ECMsg lineMsg = cMsg.E.no(i);
                // START 2018/06/01 H.Nagashima [QC#25966, ADD]
                if (!trgtImgSplyTpCdList.contains(lineMsg.imgSplyTpCd_E.getValue())) {
                    continue;
                }
                // END 2018/06/01 H.Nagashima [QC#25966, ADD]
                // QC#27853 MOD START
                if (ZYPConstant.FLG_ON_Y.equals(lineMsg.blackColorFlg_E.getValue()) && !checkTonerInfo(tonerBwOrg, tonerBwRun, bwCnt)) {
                    lineMsg.ordCustUomQty_E.setErrorInfo(1, NSAM0421E, new String[] {"Black Toner", Integer.toString(tonerBwOrg), Integer.toString(tonerBwRun) });
                    isNormal = false;
                } else if (ZYPConstant.FLG_OFF_N.equals(lineMsg.blackColorFlg_E.getValue()) && !checkTonerInfo(tonerClrOrg, tonerClrRun, clrCnt)) {
                    lineMsg.ordCustUomQty_E.setErrorInfo(1, NSAM0421E, new String[] {"Color Toner", Integer.toString(tonerClrOrg), Integer.toString(tonerClrRun) });
                    isNormal = false;
                }
                if (ZYPCommonFunc.hasValue(lineMsg.blackColorFlg_E) && !checkTonerInfo(tonerTotOrg, tonerTotRun, totCnt)) {
                    lineMsg.ordCustUomQty_E.setErrorInfo(1, NSAM0421E, new String[] {"Total Toner", Integer.toString(tonerTotOrg), Integer.toString(tonerTotRun) });
                    isNormal = false;
                }
                // QC#27853 MOD END
            }
        }
        return isNormal;
    }

    // add start 2016/09/21 CSA Defect#13267
    /**
     * Validation Check for Submit
     * @param cMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkValidationForSubmit(NSAL0990CMsg cMsg) {

        boolean isNormal = true;
        int ordCnt = 0;
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            NSAL0990_ECMsg lineMsg = cMsg.E.no(i);
            ordCnt += lineMsg.ordCustUomQty_E.getValueInt();
        }
        if (ordCnt == 0) {
            cMsg.setMessageInfo(NSAM0608E);
            isNormal = false;
        }
        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        if (hasValue(cMsg.ctacPsnFirstNm) || hasValue(cMsg.ctacPsnLastNm) || hasValue(cMsg.ctacPsnEmlAddr)) {
            if (!hasValue(cMsg.ctacPsnFirstNm)) {
                cMsg.ctacPsnFirstNm.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"First Name" });
                isNormal = false;
            }
            if (!hasValue(cMsg.ctacPsnLastNm)) {
                cMsg.ctacPsnLastNm.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Last Name" });
                isNormal = false;
            }
            if (!hasValue(cMsg.ctacPsnEmlAddr)) {
                cMsg.ctacPsnEmlAddr.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Email" });
                isNormal = false;
            }
        }
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]
        // ADD START 2022/08/19 H.Watanabe [QC#60255]
        // Check Carrier Account Number
        if (!checkCarrAcctNumValidation(cMsg)) {
            isNormal = false;
        }
        // ADD END   2022/08/19 H.Watanabe [QC#60255]

        return isNormal;
    }
    // add end 2016/09/21 CSA Defect#13267

    // START 2018/02/21 M.Kidokoro [QC#23144-1,ADD]
    /**
     * Validation Check for Disp SupplyOrderEdit
     * @param cMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkValidationForDisp_SupplyOrderEdit(NSAL0990CMsg cMsg) {
        boolean isNormal = true;
        int ordCnt = 0;
        String screenAplID = cMsg.getScreenAplID();
        if (!"NSAL0990Scrn00_Disp_SupplyOrderEdit".equals(screenAplID)) {
            return isNormal;
        }
        for (int i = 0; i < cMsg.H.getValidCount(); i++) {
            NSAL0990_HCMsg lineMsg = cMsg.H.no(i);
            ordCnt += lineMsg.ordCustUomQty_H.getValueInt();
        }
        if (ordCnt == 0) {
            cMsg.setMessageInfo(NSAM0608E);
            isNormal = false;
        }
        return isNormal;
    }
    // END 2018/02/21 M.Kidokoro [QC#23144-1,ADD]

    /**
     * get Toner Capture
     * @param tonerId String
     * @param cMsg NSAL0990CMsg
     * @param query NSAL0990Query
     * @return integer
     */
    private static int getTonerInfo(String tonerId, NSAL0990CMsg cMsg, NSAL0990Query query) {
        int rtnVal = 0;
        String tonerCapNm = ZYPCodeDataUtil.getVarCharConstValue(tonerId, cMsg.glblCmpyCd.getValue());
        // mod start 2016/10/04 CSA Defect#12898
        String tonerCapStr = query.getSvcTermAttrbMapValCd(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.dsContrDtlPk.getValue(), cMsg.dsContrPk.getValue(), tonerCapNm);
        // mod end 2016/10/04 CSA Defect#12898
        if (ZYPCommonFunc.isNumberCheck(tonerCapStr)) {
            rtnVal = Integer.parseInt(tonerCapStr);
        }
        return rtnVal;
    }

    /**
     * check Toner Info
     * @param tonerOrg String
     * @param tonerRun NSAL0990CMsg
     * @param tonerQty NSAL0990Query
     * @return boolean
     */
    private static boolean checkTonerInfo(int tonerOrg, int tonerRun, int tonerQty) {
        if (tonerOrg > 0 && tonerOrg < tonerRun + tonerQty) {
            return false;
        }
        return true;
    }

    // add start 2016/10/19 CSA Defect#15344
    /**
     * check Carrier Service Level Relation
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    private static boolean checkCarrSvcLvlRelation(NSAL0990CMsg cMsg) {
        String val = ZYPCodeDataUtil.getVarCharConstValue(CLT_FRT_COND, cMsg.glblCmpyCd.getValue());
        if (hasValue(val)) {
            List<String> collectList = S21StringUtil.toList(val);
            if (collectList.contains(cMsg.frtCondCd.getValue())) {
                return (ZYPCommonFunc.hasValue(cMsg.carrSvcLvlCd));
            }
        }
        return true;
    }
    // add end 2016/10/19 CSA Defect#15344

    // QC#27746 DEL START
    ///**
    // * @param cMsg
    // * @return boolean
    // */
    //private static boolean checkCrCard(NSAL0990CMsg cMsg) {
    //    DS_CR_CARDTMsg tMsg = new DS_CR_CARDTMsg();
    //    setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
    //    setValue(tMsg.dsCrCardPk, cMsg.dsCrCardPk);
    //    DS_CR_CARDTMsg outTMsg = (DS_CR_CARDTMsg) EZDTBLAccessor.findByKey(tMsg);
    //    if (outTMsg == null) {
    //        return false;
    //    }
    //    if (!hasValue(outTMsg.billToCustCd) || !outTMsg.billToCustCd.getValue().equals(cMsg.billToLocNum.getValue())) {
    //        return false;
    //    }
    //    return true;
    //}
    // QC#27746 DEL END

    // START 2019/06/19 [QC#50879, ADD]
    /**
     * check Shipping Instructions
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public static boolean checkShpgInstn(NSAL0990CMsg cMsg) {
        if (hasValue(cMsg.shpgInstnCmntTxt)) {
            BigDecimal shpgCmntTxtLmtSize = ZYPCodeDataUtil.getNumConstValue(SHPG_CMNT_TXT_LIMIT_SIZE, cMsg.glblCmpyCd.getValue());
            if (cMsg.shpgInstnCmntTxt.getValue().length() > shpgCmntTxtLmtSize.intValue()) {
                cMsg.shpgInstnCmntTxt.setErrorInfo(1, NSAM0749E, new String[] {String.valueOf(shpgCmntTxtLmtSize)});
                return false;
            }
        }
        return true;
    }
    // END 2019/06/19 [QC#50879, ADD]

    /**
     * @param cMsg
     * @return boolean
     */
    private static boolean checkPO(NSAL0990CMsg cMsg) {
        boolean isNormal = true;
        BigDecimal rsltCnt = NSAL0990Query.getInstance().checkPOAndBillTo(cMsg);
        if (BigDecimal.ZERO.compareTo(rsltCnt) == 0) {
            rsltCnt = NSAL0990Query.getInstance().checkPOAndAccount(cMsg);
        }
        // mod start 2016/10/25 CSA Defect#15338
        if (BigDecimal.ONE.compareTo(rsltCnt) <= 0 && !hasValue(cMsg.custIssPoNum)) {
        // mod end 2016/10/25 CSA Defect#15338
            isNormal = false;
        }
        return isNormal;
    }

    /**
     * @param cMsg
     * @param isNormal
     * @return
     */
    public static boolean checkShipToCust(NSAL0990CMsg cMsg) {
        NMZC610001PMsg shipToCustInfoApiPMsg = callCustomerInfomationGetApiForCheck(cMsg, SHIP_TO);
        if (S21ApiUtil.isXxMsgId(shipToCustInfoApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(shipToCustInfoApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        } else if (!ZYPConstant.CHKBOX_ON_Y.equals(shipToCustInfoApiPMsg.EligibleCheckList.no(0).dsAcctRelnShipToFlg_S.getValue())) {
            cMsg.curLocNum.setErrorInfo(1, NSAM0138E, new String[] {"Account Number", "Ship To" });
            return false;
        }
        return true;
    }

    /**
     * @param cMsg
     * @param isNormal
     * @return
     */
    public static boolean checkBillToCust(NSAL0990CMsg cMsg) {
        NMZC610001PMsg billToCustInfoApiPMsg = callCustomerInfomationGetApiForCheck(cMsg, BILL_TO);
        if (S21ApiUtil.isXxMsgId(billToCustInfoApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(billToCustInfoApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        } else if (!ZYPConstant.FLG_ON_Y.equals(billToCustInfoApiPMsg.EligibleCheckList.no(0).dsAcctRelnBillToFlg_B.getValue())) {
            cMsg.billToLocNum.setErrorInfo(1, NSAM0138E, new String[] {"Account Number", "Bill To" });
            return false;
        }
        return true;
    }


    // START 2017/09/28 U.Kim [QC18746#, ADD]
    /**
     * checkBillShipSoldRelation
     * @param pMsg NWZC150001PMsg
     * @param msgIdMgr S21ApiMessageIdMgr
     * @param commonBean NWZC150001DsCpoCommonBean
     * @return boolean if error then return true.
     */
    public static boolean checkBillShipSoldRelation(NSAL0990CMsg cMsg) {
        NMZC610001PMsg custInfoGetApiMsg = callCustInfoGetApi(cMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }
        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())
//                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())   //QC#26157 del
                    ) {
                cMsg.billToLocNum.setErrorInfo(1, NSAM0138E, new String[] {"Bill To", "Ship To" });
                cMsg.curLocNum.setErrorInfo(1, NSAM0138E, new String[] {"Bill To", "Ship To" });
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * callCustInfoGetApi
     * @param glblCmpyCd global company code
     * @param billToCustCd bill to customer code
     * @param sellToCustCd sell to cosutomer code (sold to customer)
     * @param shipToCustAcctCd ship to customer account code
     * @param onBatchType
     * @return bool if error then return true.
     * </pre>
     */
    private static NMZC610001PMsg callCustInfoGetApi(NSAL0990CMsg cMsg, ONBATCH_TYPE onBatchType) {
        NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, cMsg.billToLocNum);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, cMsg.ownrAcctNum); //QC#26157 del
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I2, cMsg.curLocAcctNum);
        NMZC610001 nmzc610001 = new NMZC610001();
        nmzc610001.execute(custInfoGetApiMsg, onBatchType);
        return custInfoGetApiMsg;
    }
    // END 2017/09/28 U.Kim [QC18746#, ADD]

    /**
     * @param cMsg NSAL0990CMsg
     * @param checkStr String
     * @return result NMZC610001PMsg
     */
    public static NMZC610001PMsg callCustomerInfomationGetApiForCheck(NSAL0990CMsg cMsg, String checkStr) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        if (checkStr.equals(BILL_TO)) {
            setValue(pMsg.billToCustCd, cMsg.billToLocNum);
            setValue(pMsg.dsAcctNum_I2, cMsg.billToAcctNum);
        } else if (checkStr.equals(SHIP_TO)) {
            setValue(pMsg.shipToCustCd, cMsg.curLocNum);
            setValue(pMsg.dsAcctNum_I2, cMsg.curLocAcctNum);
        }
        setValue(pMsg.dsAcctNum_I1, cMsg.ownrAcctNum);
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    /**
     * @param cMsg NSAL0990CMsg
     * @param checkStr String
     * @return result NMZC610001PMsg
     */
    public static NMZC610001PMsg callCustomerInfomationGetApiForGet(NSAL0990CMsg cMsg, String checkStr) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
        setValue(pMsg.dsBizAreaCd, DS_BIZ_AREA.SUPPLIES);
        setValue(pMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.MSG);
        if (checkStr.equals(BILL_TO)) {
            setValue(pMsg.billToCustCd, cMsg.billToLocNum);
        } else if (checkStr.equals(SHIP_TO)) {
            setValue(pMsg.shipToCustCd, cMsg.curLocNum);
        }
        setValue(pMsg.dsAcctNum_I1, cMsg.ownrAcctNum);
        setValue(pMsg.lineBizTpCd, cMsg.svcByLineBizTpCd);
        setValue(pMsg.slsDt, cMsg.slsDt);
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }

    /**
     * Call NWZC1570 Pricing API (01:Get Price List Mode)
     * @param cMsg NSAL0990CMsg
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetPriceListMode(NSAL0990CMsg cMsg) {

        NWZC157001PMsg pMsg = new NWZC157001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        setValue(pMsg.prcBaseDt, cMsg.slsDt.getValue());
        setValue(pMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        setValue(pMsg.dsOrdCatgCd, cMsg.dsOrdCatgCd);
        setValue(pMsg.dsOrdTpCd, cMsg.dsOrdTpCd);
        setValue(pMsg.lineBizTpCd, cMsg.svcByLineBizTpCd);
        setValue(pMsg.dsAcctNum, cMsg.ownrAcctNum);
        setValue(pMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        return pMsg;
    }

    /**
     * Call NWZC1570 Pricing API (02:Get Base Price Mode)
     * @param cMsg NSAL0990CMsg
     * @param mdseCd String
     * @param ordQty BigDecimal
     * @param uomQty BigDecimal
     * @param lineNum int
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetBasePriceMode(NSAL0990CMsg cMsg, String mdseCd, BigDecimal ordQty, BigDecimal uomQty, int lineNum) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // Header
        setValue(prcApiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        setValue(prcApiPMsg.prcBaseDt, cMsg.slsDt.getValue());
        setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        setValue(prcApiPMsg.orgRqstDelyDt, cMsg.slsDt.getValue());
        setValue(prcApiPMsg.dsOrdCatgCd, cMsg.dsOrdCatgCd);
        setValue(prcApiPMsg.dsOrdTpCd, cMsg.dsOrdTpCd);
        setValue(prcApiPMsg.lineBizTpCd, cMsg.svcByLineBizTpCd);
        // 2023/04/03 QC#61362 Mod Start
        //setValue(prcApiPMsg.dsAcctNum, cMsg.ownrAcctNum);
        setValue(prcApiPMsg.dsAcctNum, cMsg.billToAcctNum);
        // 2023/04/03 QC#61362 Mod End
        setValue(prcApiPMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        setValue(prcApiPMsg.custIssPoNum, cMsg.custIssPoNum);
        setValue(prcApiPMsg.dsPmtMethCd, cMsg.dsPmtMethCd);
        setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        // Detail : Supply Order Line
        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);

        setValue(linePrcApiPMsg.trxLineNum, ZYPCommonFunc.leftPad(String.valueOf(lineNum + 1), NSAL0990Constant.NUM_THREE, "0"));
        setValue(linePrcApiPMsg.trxLineSubNum, "001");
        setValue(linePrcApiPMsg.billToCustCd, cMsg.billToLocNum);
        setValue(linePrcApiPMsg.shipToCustCd, cMsg.curLocNum);
        setValue(linePrcApiPMsg.dsAcctNum_SH, cMsg.curLocAcctNum);
        setValue(linePrcApiPMsg.dsAcctNum_BL, cMsg.billToAcctNum);
        setValue(linePrcApiPMsg.coaBrCd, cMsg.brCd);
        setValue(linePrcApiPMsg.ccyCd, cMsg.ccyCd);
        setValue(linePrcApiPMsg.mdseCd, mdseCd);
        setValue(linePrcApiPMsg.pkgUomCd, PKG_UOM.EACH);
        setValue(linePrcApiPMsg.dsOrdLineCatgCd, cMsg.dsOrdLineCatgCd);
        setValue(linePrcApiPMsg.ordQty, ordQty);
        setValue(linePrcApiPMsg.ordCustUomQty, uomQty);
        setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
        setValue(linePrcApiPMsg.mdlId, cMsg.t_MdlId);
        setValue(linePrcApiPMsg.ctyAddr_SH, cMsg.ctyAddr_A2);
        setValue(linePrcApiPMsg.stCd_SH, cMsg.stCd_A2);
        setValue(linePrcApiPMsg.ctryCd_SH, cMsg.ctryCd_A2);
        setValue(linePrcApiPMsg.frtCondCd, cMsg.frtCondCd);
        setValue(linePrcApiPMsg.shpgSvcLvlCd, cMsg.shpgSvcLvlCd);
        setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        prcApiPMsg.NWZC157002PMsg.setValidCount(1);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    /**
     * @param cMsg NSAL0990CMsg
     */
    public static void clearAddRow(NSAL0990CMsg cMsg) {
        int i = cMsg.C.getValidCount();
        cMsg.C.no(i).serNum_C.clear();
        cMsg.C.no(i).mdseCd_C.clear();
     // START 2016/09/21 N.Arai [QC#11616, MOD]
     // cMsg.C.no(i).mdseNm_C.clear();
        cMsg.C.no(i).mdseDescShortTxt_C.clear();
     // END 2016/09/21 N.Arai [QC#11616, MOD]
        cMsg.C.no(i).cpoMinOrdQty_C.clear();
        cMsg.C.no(i).inPoundWt_C.clear();
    }

    /**
     * Call NMZC6110 Default Carrier API
     * @param cMsg NSAL0990CMsg
     * @return NMZC611001PMsg
     */
    public static NMZC611001PMsg callDefaultCarrierApi(NSAL0990CMsg cMsg) {

        NMZC611001PMsg pMsg = new NMZC611001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.slsDt, cMsg.slsDt.getValue());
        setValue(pMsg.dsAcctNum, cMsg.curLocAcctNum);
        // 2018/12/12 Add Start QC#29315
        setValue(pMsg.locNum, cMsg.locNum);
        setValue(pMsg.lineBizTpCd, cMsg.svcByLineBizTpCd);
        setValue(pMsg.dsBizAreaCd, cMsg.dsBizAreaCd);
        // 2018/12/12 Add End QC#29315
        new NMZC611001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    /**
     * @param cMsg NSAL0990CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @return boolean
     */
    public static boolean checkPrcApiParam(NSAL0990CMsg cMsg, NWZC157001PMsg prcApiPMsg) {
        boolean checkPrcApi = true;
        if (prcApiPMsg == null) {
            return false;
        }
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                if ("E".equals(cMsg.getMessageKind())) {
                    checkPrcApi = false;
                }
            }
            return checkPrcApi;
        }
        // set Calc Base
        NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(0);
        if (S21ApiUtil.isXxMsgId(prcLinePMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcLinePMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                if ("E".equals(cMsg.getMessageKind())) {
                    checkPrcApi = false;
                }
            }
            return checkPrcApi;
        }
        // set Calc Base
        NWZC157004PMsg prcLineSumPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
        if (S21ApiUtil.isXxMsgId(prcLineSumPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcLineSumPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                if ("E".equals(cMsg.getMessageKind())) {
                    checkPrcApi = false;
                }
            }
            return checkPrcApi;
        }
        return checkPrcApi;
    }

    /**
     * @param cMsg NSAL0990CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg callPricingApiOfGetOrderAllMode(NSAL0990CMsg cMsg, NWZC157001PMsg prcApiPMsg) {
        // Header
        setValue(prcApiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(NWZC157001.GET_ORDER_ALL);
        setValue(prcApiPMsg.prcBaseDt, cMsg.slsDt.getValue());
        setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        setValue(prcApiPMsg.orgRqstDelyDt, cMsg.slsDt.getValue());
        setValue(prcApiPMsg.dsOrdCatgCd, cMsg.dsOrdCatgCd);
        setValue(prcApiPMsg.dsOrdTpCd, cMsg.dsOrdTpCd);
        setValue(prcApiPMsg.lineBizTpCd, cMsg.svcByLineBizTpCd);

        // 2023/04/03 QC#61362 Mod Start
        //setValue(prcApiPMsg.dsAcctNum, cMsg.ownrAcctNum);
        setValue(prcApiPMsg.dsAcctNum, cMsg.billToAcctNum);
        // 2023/04/03 QC#61362 Mod End

        setValue(prcApiPMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        setValue(prcApiPMsg.custIssPoNum, cMsg.custIssPoNum);
        setValue(prcApiPMsg.dsPmtMethCd, cMsg.dsPmtMethCd);
        setValue(prcApiPMsg.negoDealAmt, BigDecimal.ZERO);
        setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        setValue(prcApiPMsg.coaBrCd, cMsg.brCd);
        setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        // Detail : Supply Order Line
        int i = 0;
        // Sales Rep or Sales Team TOC Code
        // START 2023/04/13 F.Fadriquela [QC#61366, ADD]
        int x = 0;
        // END 2023/04/13 F.Fadriquela [QC#61366, ADD]
        Map<String, Object> tocMap = null;
        if (ZYPCommonFunc.hasValue(cMsg.tocCd)) {
            tocMap = (Map<String, Object>) NSAL0990Query.getInstance().getToc(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.tocCd.getValue());
        }
        FRT_CONDTMsg frtCondTMsg = getFrtCond(cMsg);
        for (; i < cMsg.F.getValidCount(); i++) {

            // START 2023/04/13 F.Fadriquela [QC#61366, MOD]
            //NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            if (!hasValue(cMsg.F.no(i).ordCustUomQty_F) || BigDecimal.ZERO.compareTo(cMsg.F.no(i).ordCustUomQty_F.getValue()) == 0) {
                continue;
            }

            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(x);
            // END 2023/04/13 F.Fadriquela [QC#61366, MOD]
            setValue(linePrcApiPMsg.trxLineNum, ZYPCommonFunc.leftPad(String.valueOf(i + 1), NSAL0990Constant.NUM_THREE, "0"));
            setValue(linePrcApiPMsg.trxLineSubNum, "001");
            setValue(linePrcApiPMsg.billToCustCd, cMsg.billToLocNum);
            setValue(linePrcApiPMsg.shipToCustCd, cMsg.curLocNum);
            setValue(linePrcApiPMsg.dsAcctNum_SH, cMsg.curLocAcctNum);
            setValue(linePrcApiPMsg.dsAcctNum_BL, cMsg.billToAcctNum);
            setValue(linePrcApiPMsg.coaBrCd, cMsg.brCd);
            setValue(linePrcApiPMsg.ccyCd, cMsg.ccyCd);
            setValue(linePrcApiPMsg.mdseCd, cMsg.F.no(i).mdseCd_F);
            setValue(linePrcApiPMsg.pkgUomCd, PKG_UOM.EACH);
            setValue(linePrcApiPMsg.dsOrdLineCatgCd, cMsg.F.no(i).dsOrdCatgCd_F);
            // START 2018/08/14 K.Kim [QC#27251-1, MOD]
            if (hasValue(cMsg.F.no(i).ordCustUomQty_F)) {
                setValue(linePrcApiPMsg.ordQty, cMsg.F.no(i).ordCustUomQty_F);
            } else {
                setValue(linePrcApiPMsg.ordQty, BigDecimal.ZERO);
            }
            // END 2018/08/14 K.Kim [QC#27251-1, MOD]
            setValue(linePrcApiPMsg.ordCustUomQty, cMsg.F.no(i).cpoMinOrdQty_F);
            setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
            setValue(linePrcApiPMsg.mdlId, cMsg.t_MdlId);
            setValue(linePrcApiPMsg.ctyAddr_SH, cMsg.ctyAddr_A2);
            setValue(linePrcApiPMsg.stCd_SH, cMsg.stCd_A2);
            setValue(linePrcApiPMsg.ctryCd_SH, cMsg.ctryCd_A2);
            // QC#60044 2022/05/18 Add start
            setValue(linePrcApiPMsg.postCd_SH, cMsg.postCd_A2);
            // QC#60044 2022/05/18 Add end
            setValue(linePrcApiPMsg.frtCondCd, cMsg.frtCondCd);
            setValue(linePrcApiPMsg.shpgSvcLvlCd, cMsg.shpgSvcLvlCd);
            setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
            if (tocMap != null) {
                setValue(linePrcApiPMsg.ctyAddr_SR, (String) tocMap.get("CTY_ADDR"));
                setValue(linePrcApiPMsg.stCd_SR, (String) tocMap.get("ST_CD"));
                setValue(linePrcApiPMsg.ctryCd_SR, (String) tocMap.get("CTRY_CD"));
            }
            if (frtCondTMsg != null) {
                setValue(linePrcApiPMsg.frtChrgToCd, frtCondTMsg.frtChrgToCd);
                setValue(linePrcApiPMsg.frtChrgMethCd, frtCondTMsg.frtChrgMethCd);
            }
            // START 2017/07/03 K.Kitachi [QC#19026, ADD]
            // START 2018/01/26 K.Kojima [QC#22797,MOD]
            // if (NSAL0990Query.getInstance().isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.F.no(i).mdseCd_F.getValue())) {
            // START 2018/07/03 K.Kitachi [QC#26924, MOD]
//            if (NSAL0990Query.getInstance().isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.F.no(i).mdseCd_F.getValue(), cMsg.svcTermCondDataDispTxt_02.getValue())) {

            // START 2018/9/19 T.Wada [QC#28333 MOD]
            BigDecimal t_MdlId = null;
            // START 2018/11/09 [QC#29169 MOD]
            // if (ZYPCommonFunc.hasValue(cMsg.t_MdlId)) {
            //     t_MdlId =  cMsg.t_MdlId.getValue();
            // }
            if (ZYPCommonFunc.hasValue(cMsg.F.no(i).t_MdlId_F)) {
                t_MdlId = cMsg.F.no(i).t_MdlId_F.getValue();
            }
            // END 2018/11/09 [QC#29169 MOD]
          //if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.F.no(i).mdseCd_F.getValue())) {
//            if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.F.no(i).mdseCd_F.getValue(), t_MdlId)) { // QC#29302
            if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg, cMsg.F.no(i).mdseCd_F.getValue(), t_MdlId)) { // QC#29302
            // END 2018/9/19 T.Wada [QC#28333 MOD]

            // END 2018/07/03 K.Kitachi [QC#26924, MOD]
            // END 2018/01/26 K.Kojima [QC#22797,MOD]
                setValue(linePrcApiPMsg.xxUnitPrc, BigDecimal.ZERO);
                setValue(linePrcApiPMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
            }
            // END 2017/07/03 K.Kitachi [QC#19026, ADD]
            // set Pricing Element
            int k = 0;
            for (int j = 0; j < cMsg.G.getValidCount(); j++) {
                if (linePrcApiPMsg.trxLineNum.getValue().equals(cMsg.G.no(j).trxLineNum_G.getValue())) {
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).trxLineNum, cMsg.G.no(j).trxLineNum_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).trxLineSubNum, cMsg.G.no(j).trxLineSubNum_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).configCatgCd, cMsg.G.no(j).configCatgCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcCondTpCd, cMsg.G.no(j).prcCondTpCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcCondTpDescTxt, cMsg.G.no(j).prcCondTpDescTxt_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcDtlGrpCd, cMsg.G.no(j).prcDtlGrpCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcJrnlGrpCd, cMsg.G.no(j).prcJrnlGrpCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcCatgCd, cMsg.G.no(j).prcCatgCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcCondManEntryFlg, cMsg.G.no(j).prcCondManEntryFlg_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcCondManAddFlg, cMsg.G.no(j).prcCondManAddFlg_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcCondManDelFlg, cMsg.G.no(j).prcCondManDelFlg_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).pkgUomCd, cMsg.G.no(j).pkgUomCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcCondUnitCd, cMsg.G.no(j).prcCondUnitCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).prcCalcMethCd, cMsg.G.no(j).prcCalcMethCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).autoPrcCcyCd, cMsg.G.no(j).autoPrcCcyCd_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).autoPrcAmtRate, cMsg.G.no(j).autoPrcAmtRate_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).maxPrcAmtRate, cMsg.G.no(j).maxPrcAmtRate_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).minPrcAmtRate, cMsg.G.no(j).minPrcAmtRate_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).manPrcAmtRate, cMsg.G.no(j).manPrcAmtRate_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).calcPrcAmtRate, cMsg.G.no(j).calcPrcAmtRate_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).unitPrcAmt, cMsg.G.no(j).unitPrcAmt_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).dsMdsePrcPk, cMsg.G.no(j).dsMdsePrcPk_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).specCondPrcPk, cMsg.G.no(j).specCondPrcPk_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).frtPerWtPk, cMsg.G.no(j).frtPerWtPk_G);
                    setValue(linePrcApiPMsg.NWZC157003PMsg.no(k).ordPrcCalcBasePk, cMsg.G.no(j).ordPrcCalcBasePk_G);

                    linePrcApiPMsg.NWZC157003PMsg.setValidCount(k + 1);
                    k++;
                }
            }
            // START 2023/04/13 F.Fadriquela [QC#61366, ADD]
            x++;
            // END 2023/04/13 F.Fadriquela [QC#61366, ADD]
        }
        // START 2023/04/13 F.Fadriquela [QC#61366, MOD]
        //prcApiPMsg.NWZC157002PMsg.setValidCount(i);
        prcApiPMsg.NWZC157002PMsg.setValidCount(x);
        // END 2023/04/13 F.Fadriquela [QC#61366, MOD]

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    /**
     * @param cMsg NSAL0990CMsg
     * @return FRT_CONDTMsg
     */
    public static FRT_CONDTMsg getFrtCond(NSAL0990CMsg cMsg) {
        if (hasValue(cMsg.frtCondCd)) {
            FRT_CONDTMsg inTMsg = new FRT_CONDTMsg();
            setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(inTMsg.frtCondCd, cMsg.frtCondCd);
            return (FRT_CONDTMsg) EZDTBLAccessor.findByKey(inTMsg);
        }
        return null;
    }

    /**
     * Get Order Type
     * @param cMsg NSAL0990CMsg
     */
    public static void getSalesRep(NSAL0990CMsg cMsg) {
        SUB_CONTR_BR_MAPTMsg inTMsg = new SUB_CONTR_BR_MAPTMsg();
        setValue(inTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inTMsg.svcContrBrCd, cMsg.brCd);
        SUB_CONTR_BR_MAPTMsg outTMsg = (SUB_CONTR_BR_MAPTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg != null && hasValue(outTMsg.tocCd)) {
            setValue(cMsg.tocCd, outTMsg.tocCd);
        }
    }

    /**
     * get Max Table Sort Number
     * @param cMsg NSAL0990CMsg
     * @return BigDecimal
     */
    public static BigDecimal getMaxTblSortNum(NSAL0990CMsg cMsg) {
        BigDecimal sortTblNum = BigDecimal.ZERO;
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            if (cMsg.E.no(i).xxTblSortNum_E.getValue().compareTo(sortTblNum) > 0) {
                sortTblNum = cMsg.E.no(i).xxTblSortNum_E.getValue();
            }
        }
        return sortTblNum;
    }

    /**
     * Mandatory Check for Additional Header
     * @param cMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkMandatoryForCarrSvclvl(NSAL0990CMsg cMsg) {

        boolean isNormal = true;
        if (!hasValue(cMsg.frtCondDescTxt)) {
            cMsg.frtCondDescTxt.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Freight Terms" });
            isNormal = false;
        }
        if (!hasValue(cMsg.shpgSvcLvlCd)) {
            cMsg.shpgSvcLvlCd.setErrorInfo(1, NSAL0990Constant.ZZZM9007E, new String[] {"Service Level" });
            isNormal = false;
        }
        return isNormal;
    }

    /**
     * Mandatory Check for Additional Header
     * @param cMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkValidationForCarrSvclvl(NSAL0990CMsg cMsg) {
        boolean isNormal = true;
        // check freight Condition
        FRT_CONDTMsg frtCondTMsg = NSAL0990Query.getInstance().findFrtCond(cMsg);
        if (!hasValue(frtCondTMsg.frtCondCd)) {
            cMsg.frtCondDescTxt.setErrorInfo(1, NSAM0037E, new String[] {"Entered Freight Terms" });
            isNormal = false;
        } else {
            setValue(cMsg.frtCondCd, frtCondTMsg.frtCondCd);
        }
        return isNormal;
    }

    /**
     * check Carrier Service Level
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public static boolean checkCarrSvcLvl(NSAL0990CMsg cMsg) {
        // get Carrier Service Level
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = null;
        List<Map<String, Object>> rsltList = null;
        rslt = query.checkCarrSvcLvl(cMsg);
        if (rslt != null && rslt.isCodeNormal()) {
            rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            setValue(cMsg.carrSvcLvlCd, (String) rsltList.get(0).get("CARR_SVC_LVL_CD"));
        } else {
            cMsg.carrSvcLvlDescTxt.setErrorInfo(1, NSAM0011E, new String[] {"Entered Carrier Service Level" });
            return false;
        }
        return true;
    }

    /**
     * Get Freight Term Information
     * @param cMsg NSAL0990CMsg
     * @return Freight Term Information
     */
    public static Map<String, String> getFreightTermInfo(NSAL0990CMsg cMsg) {
        // QC#29191
        if (!ZYPCommonFunc.hasValue(cMsg.dsOrdTpCd)) {
            return null;
        }

        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult ssmResult = query.getFreightTermInfoList(cMsg);

        if (ssmResult.isCodeNotFound()) {
            cMsg.frtCondCd.clear();
            cMsg.frtCondDescTxt.setErrorInfo(1, NSAM0011E, new String[] {"Freight Terms" });
            return null;
        }

        List<Map<String, String>> freightTermInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(cMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        if (freightTermInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }
        return freightTermInfoList.get(0);
    }

    /**
     * set Shipping Service Level Pull Down
     * @param cMsg NSAL0990CMsg
     */
    public static void setShpgSvcLvlPullDown(NSAL0990CMsg cMsg) {
        // QC#29191
        if (!ZYPCommonFunc.hasValue(cMsg.dsOrdTpCd)) {
            cMsg.shpgSvcLvlCd_CD.clear();
            cMsg.shpgSvcLvlDescTxt_NM.clear();
            return;
        }

        NSAL0990Query query = NSAL0990Query.getInstance();
//        S21SsmEZDResult resltShpgSvcLvlRec = query.getShpgSvcLvlDataList(cMsg.glblCmpyCd.getValue(), cMsg.svcByLineBizTpCd.getValue(), cMsg.frtCondCd.getValue()); // QC#29191
        S21SsmEZDResult resltShpgSvcLvlRec = query.getShpgSvcLvlDataList(cMsg.glblCmpyCd.getValue(), cMsg.frtCondCd.getValue(), cMsg.dsOrdTpCd.getValue());

        if (resltShpgSvcLvlRec.isCodeNormal()) {
            List<Map<String, Object>> shpgSvcLvlRecList = (List<Map<String, Object>>) resltShpgSvcLvlRec.getResultObject();
            cMsg.shpgSvcLvlCd_CD.clear();
            cMsg.shpgSvcLvlDescTxt_NM.clear();
            int validCnt = 0;
            for (Map<String, Object> shpgSvcLvlRec : shpgSvcLvlRecList) {
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_CD.no(validCnt), (String) shpgSvcLvlRec.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlDescTxt_NM.no(validCnt), (String) shpgSvcLvlRec.get("SHPG_SVC_LVL_DESC_TXT"));
                validCnt++;
            }
            // START 2017/11/29 K.Kojima [QC#20497,ADD]
            // QC#29191
            if (validCnt == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd, cMsg.shpgSvcLvlCd_CD.no(0));
            }
        } else {
            cMsg.shpgSvcLvlCd_CD.clear();
            cMsg.shpgSvcLvlDescTxt_NM.clear();
            // END 2017/11/29 K.Kojima [QC#20497,ADD]
        }
    }

    /**
     * Get Carrier Service Level Information
     * @param cMsg NSAL0990CMsg
     * @return Carrier Service Level Information
     */
    public static Map<String, String> getCarrSvcLvlInfo(NSAL0990CMsg cMsg) {

        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult ssmResult = query.getCarrSvcLvlInfoList(cMsg);

        if (ssmResult.isCodeNotFound()) {
            cMsg.carrSvcLvlCd.clear();
            cMsg.carrSvcLvlDescTxt.setErrorInfo(1, NSAM0011E, new String[] {"Carrier Service Level" });
            return null;
        }

        List<Map<String, String>> carrSvcLvlInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(cMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (carrSvcLvlInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return carrSvcLvlInfoList.get(0);
    }

    // mod start 2019/01/21 QC#27304
    /**
     * Calculate LineDtl For Submit
     * @param cMsg NSAL0990CMsg
     * @return NWZC157001PMsg
     */
    public static NWZC157001PMsg calcuLineDtlForSubmit(NSAL0990CMsg cMsg) {
        sortLineDetailToFMsgForEditMode(cMsg);
        // add start 2016/10/19 CSA Defect#15293
        boolean result = true;
        // add end 2016/10/19 CSA Defect#15293

        // display Supply Order Edit Info
        // call Pricing API for Get Price Mode
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        int prcElemntCnt = 0;
        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            // QC#22796 Add Start
            // START 2018/08/09 K.Kim [QC#27251, MOD]
            // if ( BigDecimal.ZERO.compareTo(cMsg.F.no(i).ordCustUomQty_F.getValue()) == 0) {
            if (!hasValue(cMsg.F.no(i).ordCustUomQty_F) || BigDecimal.ZERO.compareTo(cMsg.F.no(i).ordCustUomQty_F.getValue()) == 0) {
            // END 2018/08/09 K.Kim [QC#27251, MOD]
                continue;
            }
            // QC#22796 Add End
            prcApiPMsg = callPricingApiOfGetBasePriceMode(cMsg, cMsg.F.no(i).mdseCd_F.getValue(), cMsg.F.no(i).ordCustUomQty_F.getValue(), BigDecimal.ONE, i);
            if (checkPrcApiParam(cMsg, prcApiPMsg)) {
                // set Field Amount
                NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
                setValue(cMsg.F.no(i).entCpoDtlDealSlsAmt_F, prcTotalPMsg.xxGrsAmt);
                setValue(cMsg.F.no(i).entDealNetUnitPrcAmt_F, prcTotalPMsg.xxUnitGrsPrcAmt);
            // add start 2016/10/19 CSA Defect#15293
            } else {
                result = false;
            // add end 2016/10/19 CSA Defect#15293
            }

            // START 2017/07/03 K.Kitachi [QC#19026, ADD]
            // START 2018/01/26 K.Kojima [QC#22797,MOD]
            // if (NSAL0990Query.getInstance().isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.F.no(i).mdseCd_F.getValue())) {
            // START 2018/07/03 K.Kitachi [QC#26924, MOD]
//            if (NSAL0990Query.getInstance().isTonerExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.F.no(i).mdseCd_F.getValue(), cMsg.svcTermCondDataDispTxt_02.getValue())) {

            // START 2018/9/19 T.Wada [QC#28333 MOD]
            //if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.F.no(i).mdseCd_F.getValue())) {
            BigDecimal t_MdlId = null;
            // START 2018/11/09 [QC#29169 MOD]
            // if (ZYPCommonFunc.hasValue(cMsg.t_MdlId)) {
            //     t_MdlId =  cMsg.t_MdlId.getValue();
            // }
            if (ZYPCommonFunc.hasValue(cMsg.F.no(i).t_MdlId_F)) {
                t_MdlId = cMsg.F.no(i).t_MdlId_F.getValue();
            }
            // END 2018/11/09 [QC#29169 MOD]
//            if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg.glblCmpyCd.getValue(), cMsg.F.no(i).mdseCd_F.getValue(), t_MdlId)) { // QC#29302
            if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg, cMsg.F.no(i).mdseCd_F.getValue(), t_MdlId)) { // QC#29302
            // END 2018/9/19 T.Wada [QC#28333 MOD]

            // END 2018/07/03 K.Kitachi [QC#26924, MOD]
            // END 2018/01/26 K.Kojima [QC#22797,MOD]
                setValue(cMsg.F.no(i).entCpoDtlDealSlsAmt_F, BigDecimal.ZERO);
                // START 2018/02/09 M.Kidokoro [QC#23065,DEL]
//                setValue(cMsg.F.no(i).entDealNetUnitPrcAmt_F, BigDecimal.ZERO);
                // END 2018/02/09 M.Kidokoro [QC#23065,DEL]
            }
            // END 2017/07/03 K.Kitachi [QC#19026, ADD]

            // set Price Element
            ZYPTableUtil.clear(cMsg.G);
            for (int k = 0; k < prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.getValidCount(); k++) {
                prcElemntCnt = cMsg.G.getValidCount();
                setValue(cMsg.G.no(prcElemntCnt).trxLineNum_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).trxLineNum);
                setValue(cMsg.G.no(prcElemntCnt).trxLineSubNum_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).trxLineSubNum);
                setValue(cMsg.G.no(prcElemntCnt).configCatgCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).configCatgCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCondTpCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondTpCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCondTpDescTxt_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondTpDescTxt);
                setValue(cMsg.G.no(prcElemntCnt).prcDtlGrpCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcDtlGrpCd);
                setValue(cMsg.G.no(prcElemntCnt).prcJrnlGrpCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcJrnlGrpCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCatgCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCatgCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCondManEntryFlg_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondManEntryFlg);
                setValue(cMsg.G.no(prcElemntCnt).prcCondManAddFlg_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondManAddFlg);
                setValue(cMsg.G.no(prcElemntCnt).prcCondManDelFlg_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondManDelFlg);
                setValue(cMsg.G.no(prcElemntCnt).pkgUomCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).pkgUomCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCondUnitCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondUnitCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCalcMethCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCalcMethCd);
                setValue(cMsg.G.no(prcElemntCnt).autoPrcCcyCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).autoPrcCcyCd);
                setValue(cMsg.G.no(prcElemntCnt).autoPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).autoPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).maxPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).maxPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).minPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).minPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).manPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).manPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).calcPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).calcPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).unitPrcAmt_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).unitPrcAmt);
                setValue(cMsg.G.no(prcElemntCnt).dsMdsePrcPk_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).dsMdsePrcPk);
                setValue(cMsg.G.no(prcElemntCnt).specCondPrcPk_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).specCondPrcPk);
                setValue(cMsg.G.no(prcElemntCnt).frtPerWtPk_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).frtPerWtPk);
                setValue(cMsg.G.no(prcElemntCnt).ordPrcCalcBasePk_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).ordPrcCalcBasePk);
                cMsg.G.setValidCount(prcElemntCnt + 1);
            }
            prcApiPMsg.clear();
        }
        // call Pricing API for Get Orde All Mode
        prcApiPMsg = callPricingApiOfGetOrderAllMode(cMsg, prcApiPMsg);
        if (checkPrcApiParam(cMsg, prcApiPMsg)) {
            // set Field Amount
            BigDecimal totalTaxAmt = BigDecimal.ZERO;
            BigDecimal totalAmt = BigDecimal.ZERO;
            for (int k = 0; k < prcApiPMsg.NWZC157004PMsg.getValidCount(); k++) {
                if (hasValue(prcApiPMsg.NWZC157004PMsg.no(k).xxTotTaxAmt)) {
                    totalTaxAmt = totalTaxAmt.add(prcApiPMsg.NWZC157004PMsg.no(k).xxTotTaxAmt.getValue());
                }
                if (hasValue(prcApiPMsg.NWZC157004PMsg.no(k).xxTotAmt)) {
                    totalAmt = totalAmt.add(prcApiPMsg.NWZC157004PMsg.no(k).xxTotAmt.getValue());
                }
            }
            ZYPTableUtil.clear(cMsg.E);
            for (int k = 0; k < cMsg.F.getValidCount(); k++) {
                EZDMsg.copy(cMsg.F.no(k), "F", cMsg.E.no(k), "E");
                cMsg.E.setValidCount(k + 1);
            }
            setValue(cMsg.xxTotTaxAmt_E, totalTaxAmt);
            setValue(cMsg.xxTotAmt_E, totalAmt);
        } else {
            result = false;
        }
        if (!result) {
            return null;
        }
        return prcApiPMsg;
    }
    // mod end 2019/01/21 QC#27304

    // add start 2019/01/21 QC#27304
    /**
     * Calculate LineDtl For EditMode
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public static boolean calcuLineDtlForEditMode(NSAL0990CMsg cMsg) {
        if (NSAL0990Constant.MODE_1.equals(cMsg.xxScrDply.getValue())) {
            sortLineDetailToFMsgForSplyOrdMode(cMsg);
        } else if (NSAL0990Constant.MODE_2.equals(cMsg.xxScrDply.getValue())) {
            sortLineDetailToFMsgForEditMode(cMsg);
        }

        // display Supply Order Edit Info
        // call Pricing API for Get Price Mode
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        int prcElemntCnt = 0;
        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            if (!hasValue(cMsg.F.no(i).ordCustUomQty_F) || BigDecimal.ZERO.compareTo(cMsg.F.no(i).ordCustUomQty_F.getValue()) == 0) {
                prcApiPMsg = callPricingApiOfGetBasePriceMode(cMsg, cMsg.F.no(i).mdseCd_F.getValue(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE.intValue());
                if (checkPrcApiParam(cMsg, prcApiPMsg)) {
                    NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
                    setValue(cMsg.F.no(i).entCpoDtlDealSlsAmt_F, BigDecimal.ZERO);
                    setValue(cMsg.F.no(i).entDealNetUnitPrcAmt_F, prcTotalPMsg.xxUnitGrsPrcAmt);
                } else {
                    cMsg.setMessageInfo(null);
                    cMsg.setMessageInfo(NSAM0745W);
                    setValue(cMsg.F.no(i).entCpoDtlDealSlsAmt_F, BigDecimal.ZERO);
                    setValue(cMsg.F.no(i).entDealNetUnitPrcAmt_F, BigDecimal.ZERO);
                }
                prcApiPMsg.clear();
                continue;
            }
            prcApiPMsg = callPricingApiOfGetBasePriceMode(cMsg, cMsg.F.no(i).mdseCd_F.getValue(), cMsg.F.no(i).ordCustUomQty_F.getValue(), BigDecimal.ONE, i);
            if (checkPrcApiParam(cMsg, prcApiPMsg)) {
                // set Field Amount
                NWZC157004PMsg prcTotalPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
                setValue(cMsg.F.no(i).entCpoDtlDealSlsAmt_F, prcTotalPMsg.xxGrsAmt);
                setValue(cMsg.F.no(i).entDealNetUnitPrcAmt_F, prcTotalPMsg.xxUnitGrsPrcAmt);
            } else {
                cMsg.setMessageInfo(null);
                cMsg.setMessageInfo(NSAM0745W);
                setValue(cMsg.F.no(i).entCpoDtlDealSlsAmt_F, BigDecimal.ZERO);
                setValue(cMsg.F.no(i).entDealNetUnitPrcAmt_F, BigDecimal.ZERO);
                prcApiPMsg.clear();
                continue;
            }

            BigDecimal t_MdlId = null;
            if (ZYPCommonFunc.hasValue(cMsg.F.no(i).t_MdlId_F)) {
                t_MdlId = cMsg.F.no(i).t_MdlId_F.getValue();
            }
            if (NSAL0990Query.getInstance().isExistsSplyReln(cMsg, cMsg.F.no(i).mdseCd_F.getValue(), t_MdlId)) { // QC#29302
                setValue(cMsg.F.no(i).entCpoDtlDealSlsAmt_F, BigDecimal.ZERO);
            }

            // set Price Element
            ZYPTableUtil.clear(cMsg.G);
            for (int k = 0; k < prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.getValidCount(); k++) {
                prcElemntCnt = cMsg.G.getValidCount();
                setValue(cMsg.G.no(prcElemntCnt).trxLineNum_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).trxLineNum);
                setValue(cMsg.G.no(prcElemntCnt).trxLineSubNum_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).trxLineSubNum);
                setValue(cMsg.G.no(prcElemntCnt).configCatgCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).configCatgCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCondTpCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondTpCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCondTpDescTxt_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondTpDescTxt);
                setValue(cMsg.G.no(prcElemntCnt).prcDtlGrpCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcDtlGrpCd);
                setValue(cMsg.G.no(prcElemntCnt).prcJrnlGrpCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcJrnlGrpCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCatgCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCatgCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCondManEntryFlg_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondManEntryFlg);
                setValue(cMsg.G.no(prcElemntCnt).prcCondManAddFlg_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondManAddFlg);
                setValue(cMsg.G.no(prcElemntCnt).prcCondManDelFlg_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondManDelFlg);
                setValue(cMsg.G.no(prcElemntCnt).pkgUomCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).pkgUomCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCondUnitCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCondUnitCd);
                setValue(cMsg.G.no(prcElemntCnt).prcCalcMethCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).prcCalcMethCd);
                setValue(cMsg.G.no(prcElemntCnt).autoPrcCcyCd_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).autoPrcCcyCd);
                setValue(cMsg.G.no(prcElemntCnt).autoPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).autoPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).maxPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).maxPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).minPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).minPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).manPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).manPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).calcPrcAmtRate_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).calcPrcAmtRate);
                setValue(cMsg.G.no(prcElemntCnt).unitPrcAmt_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).unitPrcAmt);
                setValue(cMsg.G.no(prcElemntCnt).dsMdsePrcPk_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).dsMdsePrcPk);
                setValue(cMsg.G.no(prcElemntCnt).specCondPrcPk_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).specCondPrcPk);
                setValue(cMsg.G.no(prcElemntCnt).frtPerWtPk_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).frtPerWtPk);
                setValue(cMsg.G.no(prcElemntCnt).ordPrcCalcBasePk_G, prcApiPMsg.NWZC157002PMsg.no(0).NWZC157003PMsg.no(k).ordPrcCalcBasePk);
                cMsg.G.setValidCount(prcElemntCnt + 1);
            }
            prcApiPMsg.clear();
        }
        // call Pricing API for Get Orde All Mode
        prcApiPMsg = callPricingApiOfGetOrderAllMode(cMsg, prcApiPMsg);
        BigDecimal totalTaxAmt = BigDecimal.ZERO;
        BigDecimal totalAmt = BigDecimal.ZERO;
        if (checkPrcApiParam(cMsg, prcApiPMsg)) {
            // set Field Amount
            for (int k = 0; k < prcApiPMsg.NWZC157004PMsg.getValidCount(); k++) {
                if (hasValue(prcApiPMsg.NWZC157004PMsg.no(k).xxTotTaxAmt)) {
                    totalTaxAmt = totalTaxAmt.add(prcApiPMsg.NWZC157004PMsg.no(k).xxTotTaxAmt.getValue());
                }
                if (hasValue(prcApiPMsg.NWZC157004PMsg.no(k).xxTotAmt)) {
                    totalAmt = totalAmt.add(prcApiPMsg.NWZC157004PMsg.no(k).xxTotAmt.getValue());
                }
            }
        } else {
            cMsg.setMessageInfo(null);
            cMsg.setMessageInfo(NSAM0745W);
        }

        ZYPTableUtil.clear(cMsg.E);
        String screenAplID = cMsg.getScreenAplID();
        if ("NSAL0990Scrn00_Disp_SupplyOrderEdit".equals(screenAplID)) {
            int j = 0;
            for (int k = 0; k < cMsg.F.getValidCount(); k++) {
                if (hasValue(cMsg.F.no(k).ordCustUomQty_F) && cMsg.F.no(k).ordCustUomQty_F.getValue().compareTo(BigDecimal.ZERO) > 0) {
                    EZDMsg.copy(cMsg.F.no(k), "F", cMsg.E.no(j), "E");
                    cMsg.E.setValidCount(j + 1);
                    j++;
                }
            }
        } else {
            for (int k = 0; k < cMsg.F.getValidCount(); k++) {
                EZDMsg.copy(cMsg.F.no(k), "F", cMsg.E.no(k), "E");
                cMsg.E.setValidCount(k + 1);
            }
        }
        setValue(cMsg.xxTotTaxAmt_E, totalTaxAmt);
        setValue(cMsg.xxTotAmt_E, totalAmt);
        return true;
    }
    // add end 2019/01/21 QC#27304

    /**
     * sortLineDetailToFMsgForSplyOrdMode
     * @param cMsg NSAL0990CMsg
     */
    public static void sortLineDetailToFMsgForSplyOrdMode(NSAL0990CMsg cMsg) {
        // update Line Detail from Line Detail of Supply Order Mode
        ZYPTableUtil.clear(cMsg.F);
        int j = 0;
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            if (cMsg.E.no(i).svcMachMstrPk_E.getValue().compareTo(cMsg.svcMachMstrPk.getValue()) != 0) {
                EZDMsg.copy(cMsg.E.no(i), "E", cMsg.F.no(j), "F");
                cMsg.F.no(j).xxChkBox_F.clear();
                j++;
            }
        }
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            // START 2019/07/09 [QC#50966, ADD]
            if (j == cMsg.F.length()) {
                cMsg.setMessageInfo(NSAL0990Constant.NSAM0112E);
                break;
            }
            // END 2019/07/09 [QC#50966, ADD]
            EZDMsg.copy(cMsg.C.no(i), "C", cMsg.F.no(j), "F");
            setValue(cMsg.F.no(j).svcMachMstrPk_F, cMsg.svcMachMstrPk);
            setValue(cMsg.F.no(j).dsContrDtlPk_F, cMsg.dsContrDtlPk);
            setValue(cMsg.F.no(j).dsAcctNum_F, cMsg.ownrAcctNum);
            setValue(cMsg.F.no(j).t_MdlNm_F, cMsg.xxScrItem34Txt);
            setValue(cMsg.F.no(j).dsContrNum_F, cMsg.dsContrNum);
            setValue(cMsg.F.no(j).dsOrdCatgCd_F, cMsg.dsOrdCatgCd);
            setValue(cMsg.F.no(j).dsOrdTpCd_F, cMsg.dsOrdTpCd);
            setValue(cMsg.F.no(j).dsOrdRsnCd_F, cMsg.dsOrdRsnCd);
            setValue(cMsg.F.no(j).dsOrdLineCatgCd_F, cMsg.dsOrdLineCatgCd);
            setValue(cMsg.F.no(j).coaBrNm_F, cMsg.coaBrNm);
            setValue(cMsg.F.no(j).svcConfigMstrPk_F, cMsg.svcConfigMstrPk);
            setValue(cMsg.F.no(j).svcByLineBizTpCd_F, cMsg.svcByLineBizTpCd);
            cMsg.F.no(j).xxChkBox_F.clear();
            j++;
        }
        cMsg.F.setValidCount(j);

        // Sort xxTblSortNum_F, xxSortNum_F
        List<NSAL0990_FCMsg> sortList = new ArrayList<NSAL0990_FCMsg>();
        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            NSAL0990_FCMsg dtlPMsg = (NSAL0990_FCMsg) cMsg.F.no(i).clone();
            sortList.add(dtlPMsg);
        }
        Collections.sort(sortList, new Comparator<NSAL0990_FCMsg>() {
            public int compare(NSAL0990_FCMsg line1, NSAL0990_FCMsg line2) {
                int compared;
                compared = line1.xxTblSortNum_F.getValue().compareTo(line2.xxTblSortNum_F.getValue());
                if (compared != 0) {
                    return compared;
                }
                compared = line1.xxSortNum_F.getValue().compareTo(line2.xxSortNum_F.getValue());
                if (compared != 0) {
                    return compared;
                }
                return 0;
            }
        });
        ZYPTableUtil.clear(cMsg.F);
        j = 0;
        for (int i = 0; i < sortList.size(); i++) {
            NSAL0990_FCMsg dtlPMsg = sortList.get(i);
            EZDMsg.copy(dtlPMsg, null, cMsg.F.no(j), null);
            j++;
        }
        cMsg.F.setValidCount(j);
    }

    /**
     * sortLineDetailToFMsgForEditMode
     * @param cMsg NSAL0990CMsg
     */
    public static void sortLineDetailToFMsgForEditMode(NSAL0990CMsg cMsg) {
        // update Line Detail from Line Detail of Supply Order Mode
        ZYPTableUtil.clear(cMsg.F);
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            EZDMsg.copy(cMsg.E.no(i), "E", cMsg.F.no(i), "F");
            cMsg.F.setValidCount(i + 1);
        }
    }

    // add start 2016/10/04 CSA Defect#12898
    private static String getFltMdlNm(NSAL0990CMsg cMsg) {
        String spclFltMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, cMsg.glblCmpyCd.getValue());
        if (!hasValue(spclFltMdseCd)) {
            return null;
        }

        NSZC048001PMsg pMsg = new NSZC048001PMsg();
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.slsDt, cMsg.slsDt);
        setValue(pMsg.prntMdseCd, spclFltMdseCd);
        new NSZC048001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return null;
        }
        if (!hasValue(pMsg.mdlId)) {
            return null;
        }

        NSAL0990Query query = NSAL0990Query.getInstance();
        MDL_NMTMsg tMsg = query.getMdlNm(cMsg.glblCmpyCd.getValue(), pMsg.mdlId.getValue());
        if (tMsg == null) {
            return null;
        }
        return tMsg.t_MdlNm.getValue();
    }

    private static void setFltLineInfo(NSAL0990CMsg cMsg) {

        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult rslt = query.getFltLineInfo(cMsg.glblCmpyCd.getValue(), cMsg.dsContrPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            Map<String, Object> rsltMap = rsltList.get(0);
            setValue(cMsg.dsContrDtlPk_FL, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            setValue(cMsg.billToLocNum, (String) rsltMap.get("BILL_TO_CUST_CD"));
            setValue(cMsg.billToLocNum_D, (String) rsltMap.get("BILL_TO_CUST_CD"));
            setValue(cMsg.billToAcctNum, (String) rsltMap.get("BILL_SELL_TO_CUST_CD"));
            setValue(cMsg.curLocNum, (String) rsltMap.get("SHIP_TO_CUST_CD"));
            setValue(cMsg.curLocAcctNum, (String) rsltMap.get("SHIP_SELL_TO_CUST_CD"));
        }
    }

    private static boolean isCappedContract(NSAL0990CMsg cMsg) {

        NSAL0990Query query = NSAL0990Query.getInstance();
        int tonerBwOrg = getTonerInfo(VAR_CHAR_TONER_CAP_BW_ORG, cMsg, query);
        int tonerClrOrg = getTonerInfo(VAR_CHAR_TONER_CAP_CLR_ORG, cMsg, query);
        int tonerTotOrg = getTonerInfo(VAR_CHAR_TONER_CAP_TOT_ORG, cMsg, query);
        if (tonerBwOrg + tonerClrOrg + tonerTotOrg == 0) {
            return false;
        }
        return true;
    }

    private static void setFltTonerAllotInfo(NSAL0990CMsg cMsg) {

        BigDecimal totalBwPrrtQty = BigDecimal.ZERO;
        BigDecimal totalColorPrrtQty = BigDecimal.ZERO;

        NSAL0990Query query = NSAL0990Query.getInstance();
        List<Map<String, Object>> rsltList = query.getDsContrDtlForFltMach(cMsg.glblCmpyCd.getValue(), cMsg.dsContrPk.getValue(), cMsg.slsDt.getValue());
        int cnt = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            BigDecimal dsContrDtlPk = (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK");
            BigDecimal svcMachMstrPk = (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK");

            // 2.2.getSupplyMdseData
            Map<String, Object> supplyMdseMap = new HashMap<String, Object>();
            if (!getSupplyMdseInfo(cMsg.glblCmpyCd.getValue(), svcMachMstrPk, supplyMdseMap)) {
                continue;
            }

            // 3.2.2.1. Get [StartMeterCount of BW]
            BigDecimal startMtrCntBW = BigDecimal.ZERO;
            String startReadMtrDtBW = null;
            Map<String, Object> startMtrBW = query.getStartMtrInfo(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, MDL_MTR_TP_BW);
            if (startMtrBW != null) {
                startMtrCntBW = (BigDecimal) startMtrBW.get("READ_MTR_CNT");
                startReadMtrDtBW = (String) startMtrBW.get("MTR_READ_DT");
            }

            // 3.2.2.2. Get [BillingMeterCount of BW]
            BigDecimal bllgReadMtrCntBW = BigDecimal.ZERO;
            String bllgMtrReadDtBW = cMsg.slsDt.getValue();
            List<Map<String, Object>> bllgMtrBW = query.getBllgMtrList(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, MDL_MTR_TP_BW);
            if (bllgMtrBW != null && bllgMtrBW.size() > 0) {
                bllgReadMtrCntBW = (BigDecimal) bllgMtrBW.get(0).get("READ_MTR_CNT");
                bllgMtrReadDtBW = (String) bllgMtrBW.get(0).get("MTR_READ_DT");
            } else {
                bllgReadMtrCntBW = startMtrCntBW;
            }

            // 3.2.2.3. Calculate [TotalMeterCount of BW]
            BigDecimal totalMtrCntBW = bllgReadMtrCntBW.subtract(startMtrCntBW);

            // 3.2.3.1. Get [StartMeterCount of Color ]
            BigDecimal startMtrCntCL = BigDecimal.ZERO;
            String startReadMtrDtCL = null;
            Map<String, Object> startMtrCL = query.getStartMtrInfo(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, MDL_MTR_TP_CL);
            if (startMtrCL != null) {
                startMtrCntCL = (BigDecimal) startMtrCL.get("READ_MTR_CNT");
                startReadMtrDtCL = (String) startMtrCL.get("MTR_READ_DT");
            }

            // 3.2.3.2. Get [BillingMeterCount of Color]
            BigDecimal bllgReadMtrCntCL = BigDecimal.ZERO;
            List<Map<String, Object>> bllgMtrCL = query.getBllgMtrList(cMsg.glblCmpyCd.getValue(), dsContrDtlPk, MDL_MTR_TP_CL);
            if (bllgMtrCL.size() > 0) {
                bllgReadMtrCntCL = (BigDecimal) bllgMtrCL.get(0).get("READ_MTR_CNT");
            } else {
                bllgReadMtrCntCL = startMtrCntCL;
            }

            // 3.2.2.3. Calculate [TotalMeterCount of BW]
            BigDecimal totalMtrCntCL = bllgReadMtrCntCL.subtract(startMtrCntCL);

            if (totalMtrCntBW.compareTo(BigDecimal.ZERO) == 0 && totalMtrCntCL.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }

            // 3.2.6. Calculate [fromLastBillDtDaysAot]
            Integer fromLastBillDtDaysAot = ZYPDateUtil.getDiffDays(cMsg.slsDt.getValue(), bllgMtrReadDtBW);
            supplyMdseMap.put("fromLastBillDtDaysAot", new BigDecimal(fromLastBillDtDaysAot));

            // 3.2.7. Calculate [bwAdcvCnt]
            BigDecimal bwAdcvCnt = calcAdcvCnt(bllgMtrBW, startMtrCntBW, startReadMtrDtBW, COEFFICIENT_BW);

            // 3.2.8. Calculate [colorAdcvCnt]
            BigDecimal colorAdcvCnt = calcAdcvCnt(bllgMtrCL, startMtrCntCL, startReadMtrDtCL, COEFFICIENT_CL);

            // 3.2.9. Calculate [bwPrrtQty]
            BigDecimal bwPrrtQty = calcQty(MDL_MTR_TP_BW, bwAdcvCnt, colorAdcvCnt, supplyMdseMap);

            // 3.2.10. Calculate [colorPrrtQty]
            BigDecimal colorPrrtQty = calcQty(MDL_MTR_TP_CL, BigDecimal.ZERO, colorAdcvCnt, supplyMdseMap);

            totalBwPrrtQty = totalBwPrrtQty.add(bwPrrtQty);
            totalColorPrrtQty = totalColorPrrtQty.add(colorPrrtQty);
            cnt++;
        }

        if (cnt > 0) {
            totalBwPrrtQty = totalBwPrrtQty.divide(BigDecimal.valueOf(cnt), 0, BigDecimal.ROUND_UP);
            totalColorPrrtQty = totalColorPrrtQty.divide(BigDecimal.valueOf(cnt), 0, BigDecimal.ROUND_UP);
        }

        setValue(cMsg.bwPrrtQty_A, totalBwPrrtQty);
        setValue(cMsg.colorPrrtQty_A, totalColorPrrtQty);
        setValue(cMsg.totQty_A, cMsg.bwPrrtQty_A.getValue().add(cMsg.colorPrrtQty_A.getValue()));
    }

    private static boolean getSupplyMdseInfo(String glblCmpyCd, BigDecimal svcMachMstrPk, Map<String, Object> supplyMdseMap) {

        NSAL0990Query query = NSAL0990Query.getInstance();

        for (int i = 0; i < IMG_SPLY_COLOR_TP_LIST.length; i++) {
            BigDecimal custStkQty = BigDecimal.ZERO;
            BigDecimal stdYieldCnt = BigDecimal.ZERO;
            BigDecimal splyTolPct = BigDecimal.ZERO;
            BigDecimal thisMthTotStdCostAmt = BigDecimal.ZERO;
            String clTp = IMG_SPLY_COLOR_TP_LIST[i];
            Map<String, Object> supplyMdse = query.getSupplyMdseInfo(glblCmpyCd, svcMachMstrPk, clTp);
            if (supplyMdse != null) {
                custStkQty = (BigDecimal) supplyMdse.get("CUST_STK_QTY");
                stdYieldCnt = (BigDecimal) supplyMdse.get("STD_YIELD_CNT");
                splyTolPct = (BigDecimal) supplyMdse.get("SPLY_TOL_PCT");
                thisMthTotStdCostAmt = (BigDecimal) supplyMdse.get("THIS_MTH_TOT_STD_COST_AMT");
            }
            if (!ZYPCommonFunc.hasValue(custStkQty) || !ZYPCommonFunc.hasValue(stdYieldCnt) || !ZYPCommonFunc.hasValue(splyTolPct) || !ZYPCommonFunc.hasValue(thisMthTotStdCostAmt)) {
                return false;
            }
            supplyMdseMap.put("custStkQty_" + clTp, custStkQty);
            supplyMdseMap.put("stdYieldCnt_" + clTp, stdYieldCnt);
            supplyMdseMap.put("splyTolPct_" + clTp, splyTolPct);
            supplyMdseMap.put("thisMthTotStdCostAmt_" + clTp, thisMthTotStdCostAmt);
        }
        return true;
    }

    private static BigDecimal calcQty(String colorTp, BigDecimal mtrCntBW, BigDecimal mtrCntCL, Map<String, Object> supplyMdseMap) {

        BigDecimal returnQty = BigDecimal.ZERO;

        BigDecimal yield = null;
        BigDecimal tolerance = null;
        BigDecimal coefficient = null;
        BigDecimal custStkQty = null;

        if (colorTp.equals(MDL_MTR_TP_BW)) {
            yield = (BigDecimal) supplyMdseMap.get("stdYieldCnt_" + IMG_SPLY_COLOR_TP.BLACK);
            tolerance = (BigDecimal) supplyMdseMap.get("splyTolPct_" + IMG_SPLY_COLOR_TP.BLACK);
            coefficient = COEFFICIENT_BW;
            custStkQty = (BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.BLACK);

        } else if (colorTp.equals(MDL_MTR_TP_CL)) {
            yield = (BigDecimal) supplyMdseMap.get("stdYieldCnt_" + IMG_SPLY_COLOR_TP.YELLOW);
            yield = yield.add((BigDecimal) supplyMdseMap.get("stdYieldCnt_" + IMG_SPLY_COLOR_TP.MAGENTA));
            yield = yield.add((BigDecimal) supplyMdseMap.get("stdYieldCnt_" + IMG_SPLY_COLOR_TP.CYAN));
            if (yield.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO;
            }
            tolerance = (BigDecimal) supplyMdseMap.get("splyTolPct_" + IMG_SPLY_COLOR_TP.YELLOW);
            if (tolerance.compareTo(BigDecimal.ZERO) == 0) {
                tolerance = (BigDecimal) supplyMdseMap.get("splyTolPct_" + IMG_SPLY_COLOR_TP.MAGENTA);
            }
            if (yield.compareTo(BigDecimal.ZERO) == 0) {
                tolerance = (BigDecimal) supplyMdseMap.get("splyTolPct_" + IMG_SPLY_COLOR_TP.CYAN);
            }
            coefficient = COEFFICIENT_CL;
            custStkQty = (BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.YELLOW);
            custStkQty = custStkQty.add((BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.MAGENTA));
            custStkQty = custStkQty.add((BigDecimal) supplyMdseMap.get("custStkQty_" + IMG_SPLY_COLOR_TP.CYAN));
        }

        returnQty = calcPrrtQty(mtrCntBW, mtrCntCL, yield, tolerance, coefficient, (BigDecimal) supplyMdseMap.get("fromLastBillDtDaysAot"));
        return returnQty;
    }

    private static BigDecimal calcPrrtQty(BigDecimal readMtrCntBW, BigDecimal readMtrCntCL, BigDecimal yield, BigDecimal tolerance, BigDecimal coefficient, BigDecimal aot) {
        // QC#29302
        if (!ZYPCommonFunc.hasValue(aot)) {
            return BigDecimal.ZERO;
        }

        BigDecimal returnQty = BigDecimal.ZERO;
        BigDecimal wkYield = BigDecimal.ZERO;

        returnQty = readMtrCntBW.add(readMtrCntCL);
        returnQty = returnQty.multiply(aot);
        wkYield = yield.subtract(yield.multiply(tolerance.divide(ONE_HUNDRED, 2, BigDecimal.ROUND_UP)));
        if (wkYield.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        returnQty = returnQty.divide(wkYield, 0, BigDecimal.ROUND_UP);
        returnQty = returnQty.multiply(coefficient);

        return returnQty;
    }

    private static BigDecimal calcAdcvCnt(List<Map<String, Object>> bllgMtrList, BigDecimal startMtrReadCnt, String startReadMtrDt, BigDecimal coefficient) {

        BigDecimal adcvCnt = BigDecimal.ZERO;
        int bllgCnt = bllgMtrList.size();

        if (bllgCnt == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal[] avrMtrCnt = new BigDecimal[bllgCnt];
        BigDecimal[] mtrCnt = new BigDecimal[bllgCnt + 1];
        String[] thruDt = new String[bllgCnt + 1];
        int i;

        for (i = 0; i < bllgCnt; i++) {
            mtrCnt[i] = (BigDecimal) bllgMtrList.get(i).get("READ_MTR_CNT");
            thruDt[i] = (String) bllgMtrList.get(i).get("BLLG_SCHD_THRU_DT");

            if (i > 0) {
                avrMtrCnt[i - 1] = mtrCnt[i - 1].subtract(mtrCnt[i]);
                BigDecimal diffday = new BigDecimal(ZYPDateUtil.getDiffDays(thruDt[i - 1], thruDt[i]));
                avrMtrCnt[i - 1] = avrMtrCnt[i - 1].divide(diffday, 0, BigDecimal.ROUND_UP);
                adcvCnt = adcvCnt.add(avrMtrCnt[i - 1]);
            }
        }
        if (bllgCnt < GET_BLLG_SCHD_NUM) {
            avrMtrCnt[i - 1] = mtrCnt[i - 1].subtract(startMtrReadCnt);
            BigDecimal diffday = new BigDecimal(ZYPDateUtil.getDiffDays(thruDt[i - 1], startReadMtrDt));
            avrMtrCnt[i - 1] = avrMtrCnt[i - 1].divide(diffday, 0, BigDecimal.ROUND_UP);
            adcvCnt = adcvCnt.add(avrMtrCnt[i - 1]);
            bllgCnt++;
        }

        adcvCnt = adcvCnt.divide((new BigDecimal(bllgCnt - 1)), 0, BigDecimal.ROUND_UP);
        adcvCnt = adcvCnt.multiply(coefficient);

        return adcvCnt;
    }
    // add end 2016/10/04 CSA Defect#12898

    // add start 2016/10/13 CSA Defect#9885
    public static void setAllLineDetail(NSAL0990CMsg cMsg, String mode) {

        ZYPTableUtil.clear(cMsg.H);
        if (NSAL0990Constant.MODE_1.equals(mode)) {
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                EZDMsg.copy(cMsg.C.no(i), "C", cMsg.H.no(i), "H");
                setValue(cMsg.H.no(i).xxSetFlg_H, ZYPConstant.FLG_ON_Y);
                setValue(cMsg.C.no(i).xxRowNum_C, BigDecimal.valueOf(i));
            }
            cMsg.H.setValidCount(cMsg.C.getValidCount());
        } else {
            for (int i = 0; i < cMsg.E.getValidCount(); i++) {
                EZDMsg.copy(cMsg.E.no(i), "E", cMsg.H.no(i), "H");
                setValue(cMsg.H.no(i).xxSetFlg_H, ZYPConstant.FLG_ON_Y);
                setValue(cMsg.E.no(i).xxRowNum_E, BigDecimal.valueOf(i));
            }
            cMsg.H.setValidCount(cMsg.E.getValidCount());
        }
    }

    public static void createModelPullDown(NSAL0990CMsg cMsg, String mode) {

        if (NSAL0990Constant.MODE_1.equals(mode)) {
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                EZDMsg.copy(cMsg.C.no(i), "C", cMsg.H.no(cMsg.C.no(i).xxRowNum_C.getValueInt()), "H");
            }
        } else {
            for (int i = 0; i < cMsg.E.getValidCount(); i++) {
                EZDMsg.copy(cMsg.E.no(i), "E", cMsg.H.no(cMsg.E.no(i).xxRowNum_E.getValueInt()), "H");
            }
        }

        cMsg.t_MdlId_S.clear();
        cMsg.t_MdlId_CD.clear();
        cMsg.xxScrItem34Txt_NM.clear();
        NSAL0990Query query = NSAL0990Query.getInstance();
        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
        int j = 0;
        for (int i = 0; i < cMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_OFF_N.equals(cMsg.H.no(i).xxSetFlg_H.getValue())) {
                continue;
            }
            BigDecimal mdlId = cMsg.H.no(i).t_MdlId_H.getValue();
            if (hasValue(mdlId) && !mdlIdList.contains(mdlId)) {
                MDL_NMTMsg tMsg = query.getMdlNm(cMsg.glblCmpyCd.getValue(), mdlId);
                if (tMsg == null) {
                    continue;
                }
                setValue(cMsg.t_MdlId_CD.no(j), mdlId);
                setValue(cMsg.xxScrItem34Txt_NM.no(j), tMsg.t_MdlNm);
                j++;
                mdlIdList.add(mdlId);
            }
        }
    }

    public static void resetFilter(NSAL0990CMsg cMsg, String mode) {

        cMsg.t_MdlId_S.clear();

        if (NSAL0990Constant.MODE_1.equals(mode)) {
            for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                EZDMsg.copy(cMsg.C.no(i), "C", cMsg.H.no(cMsg.C.no(i).xxRowNum_C.getValueInt()), "H");
            }

            ZYPTableUtil.clear(cMsg.C);
            int j = 0;
            for (int i = 0; i < cMsg.H.getValidCount(); i++) {
                if (ZYPConstant.FLG_OFF_N.equals(cMsg.H.no(i).xxSetFlg_H.getValue())) {
                    continue;
                }
                EZDMsg.copy(cMsg.H.no(i), "H", cMsg.C.no(j), "C");
                setValue(cMsg.C.no(j).xxRowNum_C, BigDecimal.valueOf(i));
                j++;
            }
            cMsg.C.setValidCount(j);

        } else {
            for (int i = 0; i < cMsg.E.getValidCount(); i++) {
                EZDMsg.copy(cMsg.E.no(i), "E", cMsg.H.no(cMsg.E.no(i).xxRowNum_E.getValueInt()), "H");
            }

            ZYPTableUtil.clear(cMsg.E);
            int j = 0;
            for (int i = 0; i < cMsg.H.getValidCount(); i++) {
                if (ZYPConstant.FLG_OFF_N.equals(cMsg.H.no(i).xxSetFlg_H.getValue())) {
                    continue;
                }
                EZDMsg.copy(cMsg.H.no(i), "H", cMsg.E.no(j), "E");
                setValue(cMsg.E.no(j).xxRowNum_E, BigDecimal.valueOf(i));
                j++;
            }
            cMsg.E.setValidCount(j);
        }
    }
    // add end 2016/10/13 CSA Defect#9885

    // START 2017/12/26 K.Ishizuka [QC#20164, ADD]
    /**
     * get Shipping Label
     * @param cMsg NSAL0990CMsg
     */
    @SuppressWarnings("unchecked")
    public static void setShpgLbl(NSAL0990CMsg cMsg) {

        S21SsmEZDResult rslt = NSAL0990Query.getInstance().getShpgLbl(cMsg);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
            Map<String, String> rsltMap = rsltList.get(0);
            // START 2018/04/26 U.Kim [QC#22293, MOD]
            // setValue(cMsg.scrLbNm, rsltMap.get("CTAC_PSN_FIRST_NM") + " " + rsltMap.get("CTAC_PSN_LAST_NM"));
            String shpgLbl = rsltMap.get("CTAC_PSN_FIRST_NM") + NSAL0990Constant.SPACE + rsltMap.get("CTAC_PSN_LAST_NM");
            if (ZYPCommonFunc.hasValue(cMsg.serNum.getValue())) {
                shpgLbl = shpgLbl + NSAL0990Constant.HYPHEN + cMsg.serNum.getValue();
            }
            if (shpgLbl.length() > NSAL0990Constant.SHPG_LBL_END_IDX) {
                shpgLbl = shpgLbl.substring(NSAL0990Constant.SHPG_LBL_START_IDX, NSAL0990Constant.SHPG_LBL_END_IDX);
            }
            setValue(cMsg.scrLbNm, shpgLbl);
            // END 2018/04/26 U.Kim [QC#22293, MOD]
        }
    }
    // END 2017/12/26 K.Ishizuka [QC#20164, ADD]

    // START 2018/02/14 U.Kim [QC#20297(Sol#379), ADD]
    public static boolean setDefFrtInfoFromCustInfoApi(NSAL0990CMsg cMsg) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_SHIPPING_DEFAULT_INFORMATION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, cMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, cMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, cMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, cMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt);
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
            }
            return false;
        }

        if (hasValue(pMsg.ShippingDefaultInfoList.no(0).frtCondCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.frtCondCd, pMsg.ShippingDefaultInfoList.no(0).frtCondCd);
            FRT_CONDTMsg tMsg = getFrtCond(cMsg);
            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.frtCondDescTxt, tMsg.frtCondDescTxt);
            }
            setShpgSvcLvlPullDown(cMsg);
        } else {
            return false;
        }

        if (hasValue(pMsg.ShippingDefaultInfoList.no(0).shpgSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd, pMsg.ShippingDefaultInfoList.no(0).shpgSvcLvlCd);
        }

        return true;
    }

    // 2018/12/12 Add Start QC#29315
//    private static String getDsBizAreaCd(NSAL0990CMsg cMsg) {
    public static String getDsBizAreaCd(NSAL0990CMsg cMsg) {
    // 2018/12/12 Add End QC#29315

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();

        // START 2019/06/17 W.Honda [QC#50842, MOD]]
//        condition = new ORD_CATG_BIZ_CTXTMsg(); QC#29302
//        condition.setSQLID("002");
//        condition.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
//        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
//        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
//        condition.setConditionValue("dsOrdCatgCd01", cMsg.dsOrdCatgCd.getValue());
//
//        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        condition.setSQLID("004");
        condition.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", cMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", cMsg.dsOrdTpCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).scdBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();

        condition.setSQLID("004");
        condition.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01", SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", cMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", cMsg.dsOrdTpCd.getValue());

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        // START 2019/06/17 W.Honda [QC#50842, MOD]
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).scdBizCtxAttrbTxt.getValue();
        }
        return BLANK;
    }

    public static void setDefShpgCmt(NSAL0990CMsg cMsg) {

        if (hasValue(cMsg.shpgInstnCmntTxt)) {
            return;
        }

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, cMsg.svcByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, getDsBizAreaCd(cMsg));
        ZYPEZDItemValueSetter.setValue(pMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, cMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt);

        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
            }
        }

        // START 2019/06/19 [QC#50879, MOD]
        int count = pMsg.InstructionList.getValidCount();
        StringBuilder sb = new StringBuilder("");
        BigDecimal shpgCmntTxtLmtSize = ZYPCodeDataUtil.getNumConstValue(SHPG_CMNT_TXT_LIMIT_SIZE, cMsg.glblCmpyCd.getValue());
        for (int i = 0; i < count; i++) {
            if (hasValue(pMsg.InstructionList.no(i).dsCustMsgTxt)) {
                if (i != 0) {
                    sb.append(NEW_LINE);
                }
                sb.append(pMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
                // if (sb.length() > SHPG_CMT_TXT_LIMIT_SIZE) {
                //     ZYPEZDItemValueSetter.setValue(cMsg.shpgInstnCmntTxt, sb.substring(0, SHPG_CMT_TXT_LIMIT_SIZE));
                if (sb.length() > shpgCmntTxtLmtSize.intValue()) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shpgInstnCmntTxt, sb.substring(0, shpgCmntTxtLmtSize.intValue()));
                    return;
                }
            }
        }
        // END 2019/06/19 [QC#50879, MOD]

        ZYPEZDItemValueSetter.setValue(cMsg.shpgInstnCmntTxt, sb.toString());
    }
    // END 2018/02/14 U.Kim [QC#20297(Sol#379), ADD]

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    /**
     * get Location Number
     * @param cMsg NSAL0990CMsg
     * @return Location Number
     */
    public static String getLocNum(NSAL0990CMsg cMsg) {
        NSAL0990Query query = NSAL0990Query.getInstance();
        SHIP_TO_CUSTTMsg tMsg = query.getShipToCust(cMsg.glblCmpyCd.getValue(), cMsg.curLocNum.getValue());
        if (tMsg == null) {
            return null;
        }
        return tMsg.locNum.getValue();
    }

    /**
     * create Contact Update API PMsg
     * @param cMsg NSAL0990CMsg
     * @return NMZC002001PMsg
     */
    public static NMZC002001PMsg createCtacUpdApiReqPMsg(NSAL0990CMsg cMsg) {
        NMZC002001PMsg ctacUpdApiReqPMsg = new NMZC002001PMsg();
        setValue(ctacUpdApiReqPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        setValue(ctacUpdApiReqPMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(ctacUpdApiReqPMsg.slsDt, cMsg.slsDt);
        setValue(ctacUpdApiReqPMsg.locNum, getLocNum(cMsg));
        setValue(ctacUpdApiReqPMsg.ctacPsnFirstNm, cMsg.ctacPsnFirstNm);
        setValue(ctacUpdApiReqPMsg.ctacPsnLastNm, cMsg.ctacPsnLastNm);
        setValue(ctacUpdApiReqPMsg.ctacTpCd, CTAC_TP.DELIVERY_INSTALL);

        NMZC002001_ContactPointInfoListPMsg ctacPntInfo = ctacUpdApiReqPMsg.ContactPointInfoList.no(0);
        setValue(ctacPntInfo.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        setValue(ctacPntInfo.dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
        setValue(ctacPntInfo.dsCtacPntValTxt, cMsg.ctacPsnEmlAddr);
        ctacUpdApiReqPMsg.ContactPointInfoList.setValidCount(1);

        return ctacUpdApiReqPMsg;
    }

    /**
     * call Contact Update API
     * @param cMsg NSAL0990CMsg
     * @param ctacUpdApiReqPMsg NMZC002001PMsg
     * @return boolean
     */
    public static boolean callCtacUpdApi(NSAL0990CMsg cMsg, NMZC002001PMsg ctacUpdApiReqPMsg) {
        NMZC002001 ctacUpdApi = new NMZC002001();
        ctacUpdApi.execute(ctacUpdApiReqPMsg, ONBATCH_TYPE.ONLINE);
        setCtacUpdateApiErrMsg(cMsg, ctacUpdApiReqPMsg);
        if ("E".equals(cMsg.getMessageKind())) {
            return false;
        }
        setValue(cMsg.ctacPsnPk_HD, ctacUpdApiReqPMsg.ctacPsnPk);
        return true;
    }

    private static void setCtacUpdateApiErrMsg(NSAL0990CMsg cMsg, NMZC002001PMsg ctacUpdApiReqPMsg) {
        if (S21ApiUtil.isXxMsgId(ctacUpdApiReqPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(ctacUpdApiReqPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.equals(NMAM8347E)) {
                    cMsg.ctacPsnEmlAddr.setErrorInfo(1, msgId);
                }
            }
        }
    }
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]
    // Add Start 2018/07/30 QC#14307
    public static void setSpclInstructionItem(NSAL0990CMsg cMsg) {
        // funcMstrIdDescTxt
        String funcMstrIdDescTxt = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(cMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, cMsg.dsOrdCatgCd.getValue(), cMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.funcMstrIdDescTxt, funcMstrIdDescTxt);
        // dsTrxRuleTpCd
        String dsTrxRuleTpCd = getDsTrxRuleTpCd(cMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.dsTrxRuleTpCd, dsTrxRuleTpCd);

        setBillToLocNumSI(cMsg);
        setCurLocNumSI(cMsg);
    }

    private static String getDsTrxRuleTpCd(NSAL0990CMsg cMsg) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("005");
        condition.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", cMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", cMsg.dsOrdTpCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }

    public static void setBillToLocNumSI(NSAL0990CMsg cMsg) {
        cMsg.billToLocNum_SI.clear();
        if (!ZYPCommonFunc.hasValue(cMsg.billToLocNum)) {
            return;
        }

        boolean billToFlg = NWXC150001DsCheck.spclInstnDisplayDetermination(
                 cMsg.glblCmpyCd.getValue()
                ,cMsg.dsTrxRuleTpCd.getValue()
                ,null
                ,null
                ,cMsg.billToLocNum.getValue()
                ,null
                ,BUSINESS_ID
                ,cMsg.funcMstrIdDescTxt.getValue()
                ,cMsg.svcByLineBizTpCd.getValue()
                );
        if (billToFlg) {
            setValue(cMsg.billToLocNum_SI, cMsg.billToLocNum);
        }
    }

    public static void setCurLocNumSI(NSAL0990CMsg cMsg) {
        cMsg.curLocNum_SI.clear();
        if (!ZYPCommonFunc.hasValue(cMsg.curLocNum)) {
            return;
        }

        boolean shipToFlg = NWXC150001DsCheck.spclInstnDisplayDetermination(
                 cMsg.glblCmpyCd.getValue()
                ,cMsg.dsTrxRuleTpCd.getValue()
                ,null
                ,null
                ,null
                ,cMsg.curLocNum.getValue()
                ,BUSINESS_ID
                ,cMsg.funcMstrIdDescTxt.getValue()
                ,cMsg.svcByLineBizTpCd.getValue()
                );
        if (shipToFlg) {
            setValue(cMsg.curLocNum_SI, cMsg.curLocNum);
        }
    }
    // Add End 2018/07/30 QC#14307

    // add start 2019/01/21 QC#27304
    /**
     * Set Person Name
     * @param cMsg NSAL0990CMsg
     */
    public static void setPsnNm(NSAL0990CMsg cMsg) {
        String fullPsnNm = NSAL0990Query.getInstance().getPsnNm(cMsg.glblCmpyCd.getValue(), S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        if (fullPsnNm.length() > FULL_PSN_NM_END_IDX) {
            fullPsnNm = fullPsnNm.substring(0, FULL_PSN_NM_END_IDX);
        }
        setValue(cMsg.fullPsnNm, fullPsnNm);
    }

    /**
     * Insert SVC_SPLY_ORD
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public static boolean insertSvcSplyOrd(NSAL0990CMsg cMsg) {
        BigDecimal svcSplyOrdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SPLY_ORD_SQ);
        SVC_SPLY_ORDTMsg tMsg = new SVC_SPLY_ORDTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcSplyOrdPk, svcSplyOrdPk);
        setValue(tMsg.svcMachMstrPk, cMsg.svcMachMstrPk_BK);
        setValue(tMsg.dsContrDtlPk, cMsg.dsContrDtlPk_BK);
        setValue(tMsg.billToLocNum, cMsg.billToLocNum);
        setValue(tMsg.curLocNum, cMsg.curLocNum);
        setValue(tMsg.custIssPoNum, cMsg.custIssPoNum);
        setValue(tMsg.ctacPsnFirstNm, cMsg.ctacPsnFirstNm);
        setValue(tMsg.ctacPsnLastNm, cMsg.ctacPsnLastNm);
        setValue(tMsg.ctacPsnEmlAddr, cMsg.ctacPsnEmlAddr);
        setValue(tMsg.svcCmntTxt, cMsg.xxDsMultMsgDplyTxt);
        setValue(tMsg.frtCondDescTxt, cMsg.frtCondDescTxt);
        setValue(tMsg.shpgSvcLvlCd, cMsg.shpgSvcLvlCd);
        setValue(tMsg.carrSvcLvlDescTxt, cMsg.carrSvcLvlDescTxt);
        setValue(tMsg.carrAcctNum, cMsg.carrAcctNum);
        setValue(tMsg.scrLbNm, cMsg.scrLbNm);
        setValue(tMsg.shpgInstnCmntTxt, cMsg.shpgInstnCmntTxt);
        setValue(tMsg.dsPmtMethCd, cMsg.dsPmtMethCd);
        setValue(tMsg.invCmntTxt, cMsg.invCmntTxt);

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"SVC_SPLY_ORD" });
            return false;
        }

        if (!insertSvcSplyOrdDtl(cMsg, svcSplyOrdPk)) {
            return false;
        }

        setValue(cMsg.svcSplyOrdPk, svcSplyOrdPk);
        setValue(cMsg.ezUpTime, tMsg.ezUpTime);
        setValue(cMsg.ezUpTimeZone, tMsg.ezUpTimeZone);
        return true;
    }

    /**
     * Insert SVC_SPLY_ORD_DTL
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    private static boolean insertSvcSplyOrdDtl(NSAL0990CMsg cMsg, BigDecimal svcSplyOrdPk) {
        if (NSAL0990Constant.MODE_1.equals(cMsg.xxScrDply.getValue())) {
            sortLineDetailToFMsgForSplyOrdMode(cMsg);
        } else if (NSAL0990Constant.MODE_2.equals(cMsg.xxScrDply.getValue())) {
            sortLineDetailToFMsgForEditMode(cMsg);
        }

        List<SVC_SPLY_ORD_DTLTMsg> tMsgList = new ArrayList<SVC_SPLY_ORD_DTLTMsg>();
        SVC_SPLY_ORD_DTLTMsg tMsg;
        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            tMsg = new SVC_SPLY_ORD_DTLTMsg();
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.svcSplyOrdDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_SPLY_ORD_DTL_SQ));
            setValue(tMsg.svcSplyOrdPk, svcSplyOrdPk);
            setValue(tMsg.svcMachMstrPk, cMsg.F.no(i).svcMachMstrPk_F);
            setValue(tMsg.dsContrDtlPk, cMsg.F.no(i).dsContrDtlPk_F);
            setValue(tMsg.mdseCd, cMsg.F.no(i).mdseCd_F);
            if (hasValue(cMsg.F.no(i).ordCustUomQty_F)) {
                setValue(tMsg.ordCustUomQty, cMsg.F.no(i).ordCustUomQty_F);
            } else {
                setValue(tMsg.ordCustUomQty, BigDecimal.ZERO);
            }
            tMsgList.add(tMsg);
        }
        int result = S21FastTBLAccessor.insert(tMsgList.toArray(new SVC_SPLY_ORD_DTLTMsg[0]));
        if (result != tMsgList.size()) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"SVC_SPLY_ORD_DTL" });
            return false;
        }
        return true;
    }

    /**
     * Update SVC_SPLY_ORD
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public static boolean updateSvcSplyOrd(NSAL0990CMsg cMsg) {
        SVC_SPLY_ORDTMsg tMsg = new SVC_SPLY_ORDTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcSplyOrdPk, cMsg.svcSplyOrdPk);
        tMsg = (SVC_SPLY_ORDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            cMsg.svcSplyOrdPk.clear();
            return insertSvcSplyOrd(cMsg);
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return false;
        }

        setValue(tMsg.svcMachMstrPk, cMsg.svcMachMstrPk_BK);
        setValue(tMsg.dsContrDtlPk, cMsg.dsContrDtlPk_BK);
        setValue(tMsg.billToLocNum, cMsg.billToLocNum);
        setValue(tMsg.curLocNum, cMsg.curLocNum);
        setValue(tMsg.custIssPoNum, cMsg.custIssPoNum);
        setValue(tMsg.ctacPsnFirstNm, cMsg.ctacPsnFirstNm);
        setValue(tMsg.ctacPsnLastNm, cMsg.ctacPsnLastNm);
        setValue(tMsg.ctacPsnEmlAddr, cMsg.ctacPsnEmlAddr);
        setValue(tMsg.svcCmntTxt, cMsg.xxDsMultMsgDplyTxt);
        setValue(tMsg.frtCondDescTxt, cMsg.frtCondDescTxt);
        setValue(tMsg.shpgSvcLvlCd, cMsg.shpgSvcLvlCd);
        setValue(tMsg.carrSvcLvlDescTxt, cMsg.carrSvcLvlDescTxt);
        setValue(tMsg.carrAcctNum, cMsg.carrAcctNum);
        setValue(tMsg.scrLbNm, cMsg.scrLbNm);
        setValue(tMsg.shpgInstnCmntTxt, cMsg.shpgInstnCmntTxt);
        setValue(tMsg.dsPmtMethCd, cMsg.dsPmtMethCd);
        setValue(tMsg.invCmntTxt, cMsg.invCmntTxt);
        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"SVC_SPLY_ORD" });
            return false;
        }

        if (!deleteSvcSplyOrdDtl(cMsg, DEL_MODE_PHYSICAL)) {
            return false;
        }
        if (!insertSvcSplyOrdDtl(cMsg, cMsg.svcSplyOrdPk.getValue())) {
            return false;
        }

        setValue(cMsg.ezUpTime, tMsg.ezUpTime);
        setValue(cMsg.ezUpTimeZone, tMsg.ezUpTimeZone);
        return true;
    }

    /**
     * Delete SVC_SPLY_ORD
     * @param cMsg NSAL0990CMsg
     */
    public static void deleteSvcSplyOrd(NSAL0990CMsg cMsg) {
        SVC_SPLY_ORDTMsg tMsg = new SVC_SPLY_ORDTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcSplyOrdPk, cMsg.svcSplyOrdPk);
        EZDTBLAccessor.logicalRemove(tMsg);

        deleteSvcSplyOrdDtl(cMsg, DEL_MODE_LOGICAL);
    }

    /**
     * Delete SVC_SPLY_ORD_DTL
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    private static boolean deleteSvcSplyOrdDtl(NSAL0990CMsg cMsg, int mode) {
        List<BigDecimal> svcSplyOrdDtlPkList = NSAL0990Query.getInstance().getSvcSplyOrdDtlPk(cMsg.glblCmpyCd.getValue(), cMsg.svcSplyOrdPk.getValue());
        List<SVC_SPLY_ORD_DTLTMsg> tMsgList = new ArrayList<SVC_SPLY_ORD_DTLTMsg>();
        SVC_SPLY_ORD_DTLTMsg tMsg;
        for (BigDecimal svcSplyOrdDtlPk : svcSplyOrdDtlPkList) {
            tMsg = new SVC_SPLY_ORD_DTLTMsg();
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.svcSplyOrdDtlPk, svcSplyOrdDtlPk);
            tMsgList.add(tMsg);
        }
        int result;
        if (mode == DEL_MODE_PHYSICAL) {
            result = S21FastTBLAccessor.removePhysical(tMsgList.toArray(new SVC_SPLY_ORD_DTLTMsg[0]));

        } else {
            result = S21FastTBLAccessor.removeLogical(tMsgList.toArray(new SVC_SPLY_ORD_DTLTMsg[0]));
        }
        if (result != tMsgList.size()) {
            cMsg.setMessageInfo(NSAM0033E, new String[] {"SVC_SPLY_ORD_DTL" });
            return false;
        }
        return true;
    }

    /**
     *Set Header Info By Service Supply Order
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public static boolean setHeaderInfoBySvcSplyOrd(NSAL0990CMsg cMsg) {
        if (!hasValue(cMsg.svcSplyOrdPk)) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"Service Supply Order" });
            return false;
        }
        NSAL0990Query query = NSAL0990Query.getInstance();
        S21SsmEZDResult ssmResult = query.getSvcSplyOrd(cMsg.glblCmpyCd.getValue(), cMsg.svcSplyOrdPk.getValue());
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"Service Supply Order" });
            return false;
        }
        Map<String, Object> rsltMap = (Map<String, Object>) ssmResult.getResultObject();
        setValue(cMsg.ezUpTime, (String) rsltMap.get("EZUPTIME"));
        setValue(cMsg.ezUpTimeZone, (String) rsltMap.get("EZUPTIMEZONE"));
        setValue(cMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
        setValue(cMsg.svcMachMstrPk_BK, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
        setValue(cMsg.dsContrDtlPk, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
        setValue(cMsg.dsContrDtlPk_BK, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
        setValue(cMsg.serNum, (String) rsltMap.get("SER_NUM"));
        setValue(cMsg.serNum_HD, (String) rsltMap.get("SER_NUM"));
        setValue(cMsg.serNum_BK, (String) rsltMap.get("SER_NUM"));
        setValue(cMsg.ownrAcctNum, (String) rsltMap.get("OWNR_ACCT_NUM"));
        setValue(cMsg.ownrLocNum, (String) rsltMap.get("OWNR_LOC_NUM"));
        setValue(cMsg.billToAcctNum, (String) rsltMap.get("BILL_TO_ACCT_NUM"));
        setValue(cMsg.billToLocNum, (String) rsltMap.get("BILL_TO_LOC_NUM"));
        setValue(cMsg.billToLocNum_D, (String) rsltMap.get("BILL_TO_LOC_NUM"));
        setValue(cMsg.curLocAcctNum, (String) rsltMap.get("CUR_LOC_ACCT_NUM"));
        setValue(cMsg.curLocNum, (String) rsltMap.get("CUR_LOC_NUM"));
        setValue(cMsg.xxScrItem34Txt, (String) rsltMap.get("T_MDL_NM"));
        setValue(cMsg.t_MdlId, (BigDecimal) rsltMap.get("T_MDL_ID"));
        setValue(cMsg.brCd, (String) rsltMap.get("FIN_BR_CD"));
        setValue(cMsg.coaBrNm, (String) rsltMap.get("COA_BR_NM"));
        setValue(cMsg.svcConfigMstrPk, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
        setValue(cMsg.svcByLineBizTpCd, (String) rsltMap.get("SVC_BY_LINE_BIZ_TP_CD"));
        setValue(cMsg.slsRepCd, (String) rsltMap.get("TOC_CD"));
        setValue(cMsg.sldByLineBizTpCd, (String) rsltMap.get("SLD_BY_LINE_BIZ_TP_CD"));
        setValue(cMsg.custIssPoNum, (String) rsltMap.get("CUST_ISS_PO_NUM"));
        setValue(cMsg.ctacPsnFirstNm, (String) rsltMap.get("CTAC_PSN_FIRST_NM"));
        setValue(cMsg.ctacPsnLastNm, (String) rsltMap.get("CTAC_PSN_LAST_NM"));
        setValue(cMsg.ctacPsnEmlAddr, (String) rsltMap.get("CTAC_PSN_EML_ADDR"));
        setValue(cMsg.xxDsMultMsgDplyTxt, (String) rsltMap.get("SVC_CMNT_TXT"));
        setValue(cMsg.frtCondDescTxt, (String) rsltMap.get("FRT_COND_DESC_TXT"));
        setValue(cMsg.shpgSvcLvlCd, (String) rsltMap.get("SHPG_SVC_LVL_CD"));
        setValue(cMsg.carrSvcLvlDescTxt, (String) rsltMap.get("CARR_SVC_LVL_DESC_TXT"));
        setValue(cMsg.carrAcctNum, (String) rsltMap.get("CARR_ACCT_NUM"));
        setValue(cMsg.scrLbNm, (String) rsltMap.get("SCR_LB_NM"));
        setValue(cMsg.shpgInstnCmntTxt, (String) rsltMap.get("SHPG_INSTN_CMNT_TXT"));
        setValue(cMsg.dsPmtMethCd, (String) rsltMap.get("DS_PMT_METH_CD"));
        setValue(cMsg.invCmntTxt, (String) rsltMap.get("INV_CMNT_TXT"));

        setHeaderInfoCommon(cMsg);
        return true;
    }

    /**
     * Set Line Detail By Service Supply Order
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public static boolean setLineDetailBySvcSplyOrd(NSAL0990CMsg cMsg) {
        NSAL0990Query query = NSAL0990Query.getInstance();

        cMsg.mdseCd.clear();
        cMsg.mdseDescShortTxt.clear();
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(cMsg.F);
        ZYPTableUtil.clear(cMsg.H);

        S21SsmEZDResult ssmResult = query.getSvcSplyOrdDtl(cMsg.glblCmpyCd.getValue(), cMsg.svcSplyOrdPk.getValue(), cMsg.E.length());
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"Service Supply Order Detail" });
            return false;
        }
        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int i = 0;
        for (Map<String, Object> rsltMap : rsltList) {
            if (cMsg.E.getValidCount() == cMsg.E.length()) {
                cMsg.setMessageInfo(NSAL0990Constant.NSAM0684W, new String[] {String.valueOf(cMsg.E.length()), "Line Details" });
                break;
            }

            BigDecimal svcMachMstrPk = (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK");
            setValue(cMsg.E.no(i).xxDplyCtrlFlg_E, ZYPConstant.FLG_ON_Y);
            setValue(cMsg.E.no(i).svcMachMstrPk_E, svcMachMstrPk);
            setValue(cMsg.E.no(i).dsContrDtlPk_E, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            setValue(cMsg.E.no(i).dsAcctNum_E, (String) rsltMap.get("OWNR_ACCT_NUM"));
            setValue(cMsg.E.no(i).serNum_E, (String) rsltMap.get("SER_NUM"));
            setValue(cMsg.E.no(i).t_MdlNm_E, (String) rsltMap.get("T_MDL_NM"));
            setValue(cMsg.E.no(i).mdseCd_E, (String) rsltMap.get("MDSE_CD"));
            setValue(cMsg.E.no(i).mdseDescShortTxt_E, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            setValue(cMsg.E.no(i).cpoMinOrdQty_E, (BigDecimal) rsltMap.get("CPO_MIN_ORD_QTY"));
            setValue(cMsg.E.no(i).entCpoDtlDealSlsAmt_E, BigDecimal.ZERO);
            setValue(cMsg.E.no(i).ordCustUomQty_E, (BigDecimal) rsltMap.get("ORD_CUST_UOM_QTY"));
            setValue(cMsg.E.no(i).entDealNetUnitPrcAmt_E, BigDecimal.ZERO);
            setValue(cMsg.E.no(i).dsContrNum_E, (String) rsltMap.get("DS_CONTR_NUM"));
            setValue(cMsg.E.no(i).dsContrCatgCd_E, (String) rsltMap.get("DS_CONTR_CATG_CD"));
            setValue(cMsg.E.no(i).coaBrNm_E, (String) rsltMap.get("COA_BR_NM"));
            setValue(cMsg.E.no(i).svcConfigMstrPk_E, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
            setValue(cMsg.E.no(i).svcByLineBizTpCd_E, (String) rsltMap.get("SVC_BY_LINE_BIZ_TP_CD"));
            setValue(cMsg.E.no(i).inPoundWt_E, (BigDecimal) rsltMap.get("IN_POUND_WT"));
            setValue(cMsg.E.no(i).blackColorFlg_E, (String) rsltMap.get("BLACK_COLOR_FLG"));
            if (cMsg.svcMachMstrPk_BK.getValue().compareTo(svcMachMstrPk) == 0) {
                setValue(cMsg.E.no(i).prcCondManDelFlg_E, ZYPConstant.FLG_OFF_N);
                setValue(cMsg.E.no(i).xxTblSortNum_E, BigDecimal.ONE);
                setValue(cMsg.E.no(i).xxSortNum_E, new BigDecimal(i));
            } else {
                setValue(cMsg.E.no(i).prcCondManDelFlg_E, ZYPConstant.FLG_ON_Y);
                setValue(cMsg.E.no(i).xxTblSortNum_E, getMaxTblSortNum(cMsg).add(BigDecimal.ONE));
                setValue(cMsg.E.no(i).xxSortNum_E, new BigDecimal(i));
            }
            setValue(cMsg.E.no(i).imgSplyColorTpNm_E, (String) rsltMap.get("IMG_SPLY_COLOR_TP_NM"));
            setValue(cMsg.E.no(i).t_MdlId_E, (BigDecimal) rsltMap.get("T_MDL_ID"));
            setValue(cMsg.E.no(i).imgSplyOemCd_E, (String) rsltMap.get("IMG_SPLY_OEM_CD"));
            setValue(cMsg.E.no(i).imgSplyTpCd_E, (String) rsltMap.get("IMG_SPLY_TP_CD"));
            i++;
            cMsg.E.setValidCount(i);
        }
        return true;
    }
    // add end 2019/01/21 QC#27304

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /**
     * checkCarrAcctNumValidation
     * @param cMsg
     * @return true : no error / false : error
     */
    public static boolean checkCarrAcctNumValidation(NSAL0990CMsg cMsg) {
        if (!NWXC150001DsCheck.chkCarrierAccountNumberNeedValidation(cMsg.glblCmpyCd.getValue(), cMsg.carrAcctNum.getValue(), cMsg.shpgSvcLvlCd.getValue(), cMsg.carrSvcLvlCd.getValue())) {
            cMsg.carrAcctNum.setErrorInfo(1, NSAM0206E, new String[] {"Carrier Acct Num" });
            return false;
        }
        return true;
    }
    // ADD END   2022/08/19 H.Watanabe [QC#60255]

    // START 2024/03/07 [QC#63547, ADD]
    /**
     * Validation Check for Add More
     * @param cMsg Business Message
     * @return true: normal false: has error
     */
    public static boolean checkValidationForAddMore(NSAL0990CMsg cMsg) {
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            NSAL0990_CCMsg lineMsg = cMsg.C.no(i);
            if (hasValue(lineMsg.ordCustUomQty_C)) {
                return true;
            }
        }
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            NSAL0990_CCMsg lineMsg = cMsg.C.no(i);
            lineMsg.ordCustUomQty_C.setErrorInfo(1, NSAM0785E);
        }
        return false;
    }
    // END   2024/03/07 [QC#63547, ADD]
}
