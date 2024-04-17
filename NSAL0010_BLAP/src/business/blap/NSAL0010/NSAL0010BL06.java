/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0010;

import static business.blap.NSAL0010.common.NSAL0010CommonLogic.setSvcPrvdPtyPulldown;
import static business.blap.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0010.common.NSAL0010CommonLogic;
import business.blap.NSAL0010.constant.NSAL0010Constant;
import business.db.CTAC_PSNTMsg;
import business.db.CTAC_PSNTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CTAC_PNTTMsg;
import business.db.MACH_STS_VLD_MAPTMsg;
import business.db.MDSETMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONFIG_MSTR_DTLTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_MACH_MSTR_STSTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC036001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NSZ.NSZC036001.NSZC036001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetBrCd;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetBrCdBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/06   Hitachi         T.Tomita        Update          QC#474
 * 2015/11/11   Hitachi         T.Tomita        Update          QC#569
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2015/11/20   Hitachi         T.Tomita        Update          QC#969
 * 2015/11/25   Hitachi         T.Tomita        Update          QC#970
 * 2015/12/09   Hitachi         T.Tomita        Update          QC#951
 * 2015/12/10   Hitachi         T.Tomita        Update          QC#1794
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#2255
 * 2016/02/17   Hitachi         A.Kohinata      Update          QC#1986
 * 2016/02/26   Hitachi         T.Tomita        Update          QC#942
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4888
 * 2016/03/30   Hitachi         T.Tomita        Update          QC#5398
 * 2016/03/31   Hitachi         A.Kohinata      Update          QC#4891
 * 2016/04/08   Hitachi         M.Gotou         Update          QC#4905
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6218
 * 2016/04/25   Hitachi         T.Tomita        Update          QC#5522
 * 2016/04/25   Hitachi         T.Tomita        Update          QC#6672
 * 2016/05/09   Hitachi         T.Tomita        Update          QC#7842
 * 2016/05/09   Hitachi         T.Tomita        Update          QC#6796
 * 2016/05/10   Hitachi         T.Tomita        Update          QC#6142
 * 2016/05/11   Hitachi         M.Gotou         Update          QC#7820
 * 2016/05/13   Hitachi         T.Tomita        Update          QC#7794
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/05/26   Hitachi         T.Tomita        Update          QC#8782
 * 2016/06/01   Hitachi         T.Tomita        Update          QC#7794
 * 2016/06/02   Hitachi         T.Tomita        Update          QC#6142
 * 2016/06/29   Hitachi         T.Tomita        Update          QC#10489
 * 2016/07/01   Hitachi         T.Tomita        Update          QC#11110
 * 2016/07/04   Hitachi         T.Tomita        Update          QC#11164
 * 2016/07/22   Hitachi         T.Tomita        Update          QC#11161
 * 2016/07/29   Hitachi         T.Tomita        Update          QC#12638
 * 2016/08/08   Hitachi         T.Tomita        Update          QC#7794
 * 08/10/2016   CSAI            Y.Imazu         Update          QC#12496
 * 2016/08/29   Hitachi         A.Kohinata      Update          QC#13240
 * 2016/08/29   Hitachi         T.Tomita        Update          QC#13532
 * 2016/09/06   Hitachi         T.Tomita        Update          QC#13532
 * 2016/10/13   Hitachi         T.Tomita        Update          CSA QC#14734
 * 2016/10/27   Hitachi         Y.Takeno        Update          QC#15607
 * 2016/11/14   Hitachi         N.Arai          Update          QC#15829
 * 2016/12/13   Hitachi         K.Ochiai        Update          QC#16563
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/16   Hitachi         Y.Takeno        Update          QC#16217
 * 2016/12/19   Hitachi         K.Kojima        Update          QC#16600
 * 2017/01/20   Hitachi         K.Kojima        Update          QC#16600
 * 2017/01/27   Hitachi         K.Kojima        Update          QC#16600
 * 2017/02/14   Hitachi         K.Kitachi       Update          QC#17309
 * 2017/03/14   Hitachi         T.Tomita        Update          QC#17822
 * 2017/03/22   Hitachi         K.Kitachi       Update          QC#15679
 * 2017/06/22   Hitachi         Y.Osawa         Update          QC#19054
 * 2017/06/27   Hitachi         K.Kim           Update          QC#18479
 * 2017/08/08   Hitachi         K.Kim           Update          QC#18015
 * 2017/08/22   Fujitsu         N.Sugiura       Update          QC#8598
 * 2017/10/18   CITS            M.Naito         Update          QC#20246
 * 2017/11/21   Fujitsu         W.Honda         Update          QC#22087
 * 2018/01/22   Hitachi         M.Kidokoro      Update          QC#21975
 * 2018/05/08   Hitachi         K.Kitachi       Update          QC#23604
 * 2018/05/28   Hitachi         K.Kitachi       Update          QC#19932
 * 2018/05/28   Hitachi         K.Kitachi       Update          QC#26298
 * 2018/05/29   Hitachi         K.Kim           Update          QC#15410(Sol#246)
 * 2018/06/05   Hitachi         T.Tomita        Update          QC#23428
 * 2018/08/23   Hitachi         K.Kitachi       Update          QC#27773
 * 2018/09/27   Hitachi         K.Fujimoto      Update          QC#27788
 * 2018/09/28   Hitachi         K.Kitachi       Update          QC#27788
 * 2019/01/16   Fujitsu         S.Kosaka        Update          QC#29642
 * 2019/03/25   Hitachi         K.Kitachi       Update          QC#30749
 * 2019/08/07   Hitachi         A.Kohinata      Update          QC#52198
 * 2019/11/28   Hitachi         K.Kitachi       Update          QC#53162
 * 2020/04/15   Hitachi         K.Sakurai       Update          QC#56528
 * 2020/04/24   Hitachi         K.Sakurai       Update          QC#56672
 * 2021/08/17   CITS            R.Cabral        Update          QC#59010
 * 2023/07/10   Hitachi         Y.Nagasawa      Update          QC#61524
 * 2023/07/31   Hitachi         Y.nagasawa      Update          QC#61632
 * 2023/08/23   CITS            R.Jin           Update          QC#61808
 * 2023/09/22   Hitachi         T.Kuroda        Update          QC#61265
 * 2023/10/06   Hitachi         K.Ishizuka      Update          QC#54186
 * 2024/01/06   CITS            R.Kurahashi     Update          QC#63402
 * 2023/12/19   Hitachi         T.Fukuta        Update          CSA-QC#61841
 *</pre>
 */
public class NSAL0010BL06 extends S21BusinessHandler {

    /**
     * Function ID List
     */
    private List<String> funcIdList = new ArrayList<String>();

    // START 2016/09/06 T.Tomita [QC#13532, ADD]
    /**
     * Service Machine Master Status Customer Group
     */
    private List<String> svcMachMstrStsCustGrp;

    /**
     * Service Machine Master Status Warehouse Group
     */
    private List<String> svcMachMstrStsWhGrp;
    // END 2016/09/06 T.Tomita [QC#13532, ADD]

    // add start 2016/04/08 CSA Defect#4905
    @Override
    protected boolean checkInput(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        NSAL0010CMsg cMsg = (NSAL0010CMsg) ezdCMsg;
        NSAL0010SMsg sMsg = (NSAL0010SMsg) ezdSMsg;
        String screenAplID = cMsg.getScreenAplID();
        if ("NSAL0010Scrn00_CMN_Submit".equals(screenAplID)) {
            return checkInput_NSAL0010Scrn00_CMN_Submit(cMsg, sMsg);
        // add start 2016/05/11 CSA Defect#7820
        } else if ("NSAL0010Scrn00_CMN_ColClear".equals(screenAplID)) {
            return true;
        } else if ("NSAL0010Scrn00_CMN_ColSave".equals(screenAplID)) {
            return true;
        // add end 2016/05/11 CSA Defect#7820
        } else {
            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
        }
    }
    private boolean checkInput_NSAL0010Scrn00_CMN_Submit(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        boolean valid = true;
        NSAL0010Query query = NSAL0010Query.getInstance();
        String glblCmpyCd = getGlobalCompanyCode();

        // START 2016/07/29 T.Tomita [QC#12638, DEL]
//        if (ZYPCommonFunc.hasValue(cMsg.svcBrCd_D)) {
//            SVC_CONTR_BRTMsg tMsg = query.getSvcContrBr(glblCmpyCd, cMsg.svcBrCd_D.getValue());
//            if (tMsg == null) {
//                cMsg.svcBrCd_D.setErrorInfo(1, NSAM0011E, new String[] {"Service Branch CD" });
//                valid = false;
//            }
//        }
        // END 2016/07/29 T.Tomita [QC#12638, DEL]

        if (ZYPCommonFunc.hasValue(cMsg.prfTechCd_D)) {
            // START 2016/12/14 K.Kojima [QC#15653,MOD]
            // TECH_MSTRTMsg tMsg = query.getTechMstr(glblCmpyCd, cMsg.prfTechCd_D.getValue());
            // if (tMsg == null) {
            String techTocCd = query.getTechMstr(glblCmpyCd, cMsg.prfTechCd_D.getValue(), cMsg.slsDt.getValue());
            if (techTocCd == null) {
            // END 2016/12/14 K.Kojima [QC#15653,MOD]
                cMsg.prfTechCd_D.setErrorInfo(1, NSAM0011E, new String[] {"Preferred Tech CD" });
                valid = false;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.reqTechCd_D)) {
            // START 2016/12/14 K.Kojima [QC#15653,MOD]
            // TECH_MSTRTMsg tMsg = query.getTechMstr(glblCmpyCd, cMsg.reqTechCd_D.getValue());
            // if (tMsg == null) {
            String techTocCd = query.getTechMstr(glblCmpyCd, cMsg.reqTechCd_D.getValue(), cMsg.slsDt.getValue());
            if (techTocCd == null) {
            // END 2016/12/14 K.Kojima [QC#15653,MOD]
                cMsg.reqTechCd_D.setErrorInfo(1, NSAM0011E, new String[] {"Requested Tech CD" });
                valid = false;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.bizHrsSunFromTm_D) && ZYPCommonFunc.hasValue(cMsg.bizHrsSunToTm_D)) {
            if (cMsg.bizHrsSunFromTm_D.getValue().compareTo(cMsg.bizHrsSunToTm_D.getValue()) >= 0) {
                cMsg.xxSvcFromHourMnTxt_D1.setErrorInfo(1, NSAM0062E);
                cMsg.xxSvcToHourMnTxt_D1.setErrorInfo(1, NSAM0062E);
                valid = false;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.bizHrsMonFriFromTm_D) && ZYPCommonFunc.hasValue(cMsg.bizHrsMonFriToTm_D)) {
            if (cMsg.bizHrsMonFriFromTm_D.getValue().compareTo(cMsg.bizHrsMonFriToTm_D.getValue()) >= 0) {
                cMsg.xxSvcFromHourMnTxt_D2.setErrorInfo(1, NSAM0062E);
                cMsg.xxSvcToHourMnTxt_D2.setErrorInfo(1, NSAM0062E);
                valid = false;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.bizHrsSatFromTm_D) && ZYPCommonFunc.hasValue(cMsg.bizHrsSatToTm_D)) {
            if (cMsg.bizHrsSatFromTm_D.getValue().compareTo(cMsg.bizHrsSatToTm_D.getValue()) >= 0) {
                cMsg.xxSvcFromHourMnTxt_D3.setErrorInfo(1, NSAM0062E);
                cMsg.xxSvcToHourMnTxt_D3.setErrorInfo(1, NSAM0062E);
                valid = false;
            }
        }

        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.E.no(i).nonPrfTechCd_E)) {
                String techTocCd = query.getTechMstr(glblCmpyCd, cMsg.E.no(i).nonPrfTechCd_E.getValue(), cMsg.slsDt.getValue());
                if (techTocCd == null) {
                    cMsg.E.no(i).nonPrfTechCd_E.setErrorInfo(1, NSAM0011E, new String[] {"Tech Code" });
                    valid = false;
                }
            }
        }
        // END 2016/12/14 K.Kojima [QC#15653,ADD]

        return valid;
    }
    // add end 2016/04/08 CSA Defect#4905

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        // START 2016/02/26 T.Tomita [QC#942 ADD]
        this.funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        // END 2016/02/26 T.Tomita [QC#942 ADD]
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0010Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_CMN_Submit((NSAL0010CMsg) cMsg, (NSAL0010SMsg) sMsg);
            } else if ("NSAL0010Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            } else if ("NSAL0010Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_CMN_Submit(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        if (TAB_MACH_CONFIG.equals(cMsg.xxDplyTab_01.getValue())) {
            doProcess_CMN_Submit_Header(cMsg, sMsg, getUserProfileService());
        } else if (TAB_SOLUTION.equals(cMsg.xxDplyTab_01.getValue())) {
            doProcess_CMN_Submit_Solutions(cMsg, sMsg, getUserProfileService());
        } else if (TAB_CONTACTS.equals(cMsg.xxDplyTab_01.getValue())) {
            doProcess_CMN_Submit_Contacts(cMsg, sMsg, getUserProfileService());
        } else if (TAB_ADDL_ATTRB.equals(cMsg.xxDplyTab_01.getValue())) {
            doProcess_CMN_Submit_AddlAttrb(cMsg, sMsg, getUserProfileService());
        // START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
        // } else if (TAB_CURRENT_LOC.equals(cMsg.xxDplyTab_01.getValue())) {
        //  doProcess_CMN_Submit_CurrentLoc(cMsg, sMsg, getUserProfileService());
        // END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
        }
        EZDMsg.copy(sMsg, null, cMsg, null);
    }

    /**
     * Screen event - Submit
     * @param cMsg DSAL0090CMsg
     * @param sMsg DSAL0090SMsg
     * @param profile S21UserProfileService
     */
    private void doProcess_CMN_Submit_Header(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, S21UserProfileService profile) {

        // Save current page to SMsg
        NSAL0010CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);

        // Error check
        if (!validation(cMsg, sMsg)) {
            return;
        }

        // Check time stamp
        if (!checkTimeStamp(cMsg, sMsg)) {
            return;
        }

        // Update DB
        if (!doUpdate_Header(cMsg, sMsg)) {
            return;
        }

        // Process complete message
        cMsg.setMessageInfo("ZZM8100I");
    }

    private void doProcess_CMN_Submit_Solutions(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, S21UserProfileService profile) {

        // Save current page to SMsg
        NSAL0010CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);

        BigDecimal solSq = null;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.B.no(i).svcSlnSq_B)) {
                solSq = sMsg.B.no(i).svcSlnSq_B.getValue();
                break;
            }
        }

        if (!ZYPCommonFunc.hasValue(solSq)) {
            cMsg.setMessageInfo(NSAM0063E, new String[]{"Solution#", "selected records"});
            return;
        }

        NSZC036001PMsg pMsg = new NSZC036001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.svcSlnSq, solSq);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt);
        // START 2015/11/11 T.Tomita [QC#569, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcSlnUpdPsnCd, getUserProfileService().getContextUserInfo().getUserId());
        // END 2015/11/11 T.Tomita [QC#569, ADD]

        int index = 0;
        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).svcSlnSq_B)) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcConfigList.no(index).svcConfigMstrPk, sMsg.B.no(i).svcConfigMstrPk_B);
                ZYPEZDItemValueSetter.setValue(pMsg.xxSvcConfigList.no(index).delFlg, ZYPConstant.FLG_OFF_N);
                index++;
            }
        }
        for (int i = 0; i < sMsg.M.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcConfigList.no(index).svcConfigMstrPk, sMsg.M.no(i).svcConfigMstrPk_M);
            ZYPEZDItemValueSetter.setValue(pMsg.xxSvcConfigList.no(index).delFlg, ZYPConstant.FLG_ON_Y);
            index++;
        }
        pMsg.xxSvcConfigList.setValidCount(index);

        new NSZC036001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return;
        }

        // Process complete message
        cMsg.setMessageInfo("ZZM8100I");
    }

    /**
     * Screen event - Submit
     * @param cMsg DSAL0090CMsg
     * @param sMsg DSAL0090SMsg
     * @param profile S21UserProfileService
     */
    private void doProcess_CMN_Submit_Contacts(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, S21UserProfileService profile) {

        // Save current page to SMsg
        NSAL0010CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);

        // START 2018/09/28 K.Kitachi [QC#27788, MOD]
//        // START 2017/08/22 N.Sugiura [QC#8598, DEL]
        Map<String, Integer> primMap = new HashMap<String, Integer>();
//        // END 2017/08/22 N.Sugiura [QC#8598, DEL]
        // END 2018/09/28 K.Kitachi [QC#27788, MOD]

        // mod start 2016/04/19 CSA Defect#6218
        boolean isError = false;
        int errIndex = -1;
        DS_CTAC_PNTTMsg dsCtacPntTMsg;
        // add start 2016/04/25 CSA Defect#6672
        Map<String, Object> effDtMap;
        // add end 2016/04/25 CSA Defect#6672
        // START 2016/05/13 T.Tomita [QC#7794, ADD]
        List<BigDecimal> dsCtacPntPkList = new ArrayList<BigDecimal>();
        // END 2016/05/13 T.Tomita [QC#7794, ADD]
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL0010_CSMsg ctacMsg = sMsg.C.no(i);
            // START 2018/09/28 K.Kitachi [QC#27788, MOD]
//            // START 2017/08/22 N.Sugiura [QC#8598, DEL]
            if (ZYPConstant.FLG_ON_Y.equals(ctacMsg.xxChkBox_C.getValue())) {
                String primMapKey = S21StringUtil.concatStrings(ctacMsg.dsCtacPntTpCd_CS.getValue(), DELIMITER, ctacMsg.svcCtacTpCd_CS.getValue());
//                if (primMap.containsKey(ctacMsg.dsCtacPntTpCd_CS.getValue())) {
                if (primMap.containsKey(primMapKey)) {
                    // START 2016/03/31 [QC#4891, MOD]
                    cMsg.setMessageInfo(NSAM0445E);
                    // END 2016/03/31 [QC#4891, MOD]
                    isError = true;
                    break;
                } else {
//                    primMap.put(ctacMsg.dsCtacPntTpCd_CS.getValue(), i);
                    primMap.put(primMapKey, i);
                }
            }
//            // END 2017/08/22 N.Sugiura [QC#8598, DEL]
            // END 2018/09/28 K.Kitachi [QC#27788, MOD]

            // add start 2016/04/25 CSA Defect#6672
            if (ZYPCommonFunc.hasValue(ctacMsg.ctacPsnPk_C)) {
                effDtMap = NSAL0010Query.getInstance().getEffDtInfo(createSsmParamIsGetEffDt(cMsg, ctacMsg.ctacPsnPk_C.getValue()));
                // mod start 2016/06/01 CSA Defect#7794
                if (effDtMap != null) {
                    if (ctacMsg.effFromDt_C.getValue().compareTo((String) effDtMap.get("EFF_FROM_DT")) < 0) {
                        ctacMsg.effFromDt_C.setErrorInfo(1, NSAM0284E, new String[] {"Start Date", "Effective From Date in Master" });
                        if (errIndex == -1) {
                            errIndex = i;
                        }
                        isError = true;
                    }
                    if (ctacMsg.effThruDt_C.getValue().compareTo((String) effDtMap.get("EFF_THRU_DT")) > 0) {
                        ctacMsg.effThruDt_C.setErrorInfo(1, NSAM0285E, new String[] {"End Date", "Effective Through Date in Master" });
                        if (errIndex == -1) {
                            errIndex = i;
                        }
                        isError = true;
                    }
                } else {
                    // Not Exist Master
                    // mod start 2016/07/01 CSA Defect#11110
                    setErrForCtacPnt(ctacMsg, NSAM0011E, new String[] {"Contact Person" });
                    // mod end 2016/07/01 CSA Defect#11110
                    if (errIndex == -1) {
                        errIndex = i;
                    }
                    isError = true;
                }
                // mod end 2016/06/01 CSA Defect#7794
            } else {
                if (ctacMsg.effFromDt_C.getValue().compareTo(cMsg.slsDt.getValue()) < 0) {
                    ctacMsg.effFromDt_C.setErrorInfo(1, NSAM0284E, new String[] {"Start Date", "Sales Date" });
                    if (errIndex == -1) {
                        errIndex = i;
                    }
                    isError = true;
                }
            }
            // add end 2016/04/25 CSA Defect#6672

            // START 2016/07/04 T.Tomita [QC#11164, ADD]
            if (ZYPCommonFunc.hasValue(ctacMsg.effFromDt_C) && ZYPCommonFunc.hasValue(ctacMsg.effThruDt_C) && ZYPDateUtil.compare(ctacMsg.effFromDt_C.getValue(), ctacMsg.effThruDt_C.getValue()) > 0) {
                ctacMsg.effFromDt_C.setErrorInfo(1, NSAM0285E, new String[] {"Start Date", "End Date" });
                ctacMsg.effThruDt_C.setErrorInfo(1, NSAM0284E, new String[] {"End Date", "Start Date" });
                if (errIndex == -1) {
                    errIndex = i;
                }
                isError = true;
            }
            // END 2016/07/04 T.Tomita [QC#11164, ADD]

            // START 2016/08/08 T.Tomita [QC#7794, ADD]
            List<Integer> dupIdxList = getDuplicateIdx(cMsg, sMsg, i);
            for (Integer dupIdx : dupIdxList) {
                // set error info
                sMsg.C.no(dupIdx).ctacPsnLastNm_C.setErrorInfo(1, NSAM0474E, new String[] {"contact points", "selected records" });
                sMsg.C.no(dupIdx).ctacPsnFirstNm_C.setErrorInfo(1, NSAM0474E, new String[] {"contact points", "selected records" });
                sMsg.C.no(dupIdx).dsCtacPntTpCd_CS.setErrorInfo(1, NSAM0474E, new String[] {"contact points", "selected records" });
                sMsg.C.no(dupIdx).dsCtacPntValTxt_C.setErrorInfo(1, NSAM0474E, new String[] {"contact points", "selected records" });
                sMsg.C.no(dupIdx).dsCtacPsnExtnNum_C.setErrorInfo(1, NSAM0474E, new String[] {"contact points", "selected records" });
                sMsg.C.no(dupIdx).svcCtacTpCd_CS.setErrorInfo(1, NSAM0474E, new String[] {"contact points", "selected records" });
                sMsg.C.no(dupIdx).effFromDt_C.setErrorInfo(1, NSAM0474E, new String[] {"contact points", "selected records" });
                sMsg.C.no(dupIdx).effThruDt_C.setErrorInfo(1, NSAM0474E, new String[] {"contact points", "selected records" });
                if (errIndex == -1) {
                    errIndex = dupIdx;
                }
                isError = true;
            }
            // END 2016/08/08 T.Tomita [QC#7794, ADD]

            // Opt Out Check
            if (ZYPCommonFunc.hasValue(ctacMsg.dsCtacPntPk_C)) {
                dsCtacPntTMsg = NSAL0010Query.getInstance().getDsCtacPnt(getGlobalCompanyCode(), ctacMsg.dsCtacPntPk_C.getValue());
                if (dsCtacPntTMsg != null) {
                    // Opt Out Check
                    if (!ZYPConstant.FLG_ON_Y.equals(dsCtacPntTMsg.dsOpsOutFlg.getValue())) {
                        continue;
                    }
                    setErrForCtacPnt(ctacMsg, NSAM0448E, new String[] {"Contact Point",  "Selected Contact Point", "Opt Out"});
                    if (errIndex == -1) {
                        errIndex = i;
                    }
                    isError = true;
                } else {
                    // Not Exist Master
                    setErrForCtacPnt(ctacMsg, NSAM0011E, new String[] {"Contact Point" });
                    if (errIndex == -1) {
                        errIndex = i;
                    }
                    isError = true;
                }
            }
        }

        if (isError) {
            if (errIndex > -1) {
                setValue(cMsg.xxPageShowFromNum_A, NSAL0010CommonLogic.getPageShowFromNum(errIndex, cMsg.C.length()));
                NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            }
            return;
        }
        // mod start 2016/04/19 CSA Defect#6218

        // Update DB
        // START 2016/05/13 T.Tomita [QC#7794, MOD]
        if (!doUpdate_Contacts(cMsg, sMsg, dsCtacPntPkList)) {
            return;
        }
        // END 2016/05/13 T.Tomita [QC#7794, MOD]

        // Process complete message
        cMsg.setMessageInfo("ZZM8100I");
    }

    /**
     * Screen event - Submit
     * @param cMsg DSAL0090CMsg
     * @param sMsg DSAL0090SMsg
     * @param profile S21UserProfileService
     */
    private void doProcess_CMN_Submit_AddlAttrb(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, S21UserProfileService profile) {

        // Save current page to SMsg
        NSAL0010CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);

        // START 2016/07/22 T.Tomita [QC#11161, ADD]
        // validation
        if (!validation_AddlAttrb(cMsg, sMsg)) {
            return;
        }
        // END 2016/07/22 T.Tomita [QC#11161, ADD]

        // START 2018/05/08 K.Kitachi [QC#23604, ADD]
        SVC_MACH_MSTRTMsg bfMachTMsg = getSvcMachMstrTMsg(cMsg.glblCmpyCd, cMsg.svcMachMstrPk_H1);
        // END 2018/05/08 K.Kitachi [QC#23604, ADD]

        // Update DB
        if (!doUpdate_AddlAttrb(cMsg, sMsg)) {
            return;
        }

        // START 2018/05/08 K.Kitachi [QC#23604, ADD]
        SVC_MACH_MSTRTMsg afMachTMsg = getSvcMachMstrTMsg(cMsg.glblCmpyCd, cMsg.svcMachMstrPk_H1);
        // Update Accessory IB
        if (isUpdateAccIB(bfMachTMsg, afMachTMsg)) {
            updateAccIB(afMachTMsg, cMsg.slsDt.getValue(), ProcessMode.UPDATE_ATTRIBUTE.code);
        }
        // END 2018/05/08 K.Kitachi [QC#23604, ADD]

//        // Refresh screen
//        doProcess_DSAL0090Scrn00_TAB_Detail(cMsg, sMsg);
//
//        // Show first updated/inserted record
//        showFisrtUpdatedRow_TabDetail(cMsg, sMsg);
//
        // Process complete message
        cMsg.setMessageInfo("ZZM8100I");
    }

    /**
     * Screen event - Submit
     * @param cMsg DSAL0090CMsg
     * @param sMsg DSAL0090SMsg
     * @param profile S21UserProfileService
     */
 // START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//    private void doProcess_CMN_Submit_CurrentLoc(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, S21UserProfileService profile) {

        // Save current page to SMsg
//        NSAL0010CommonLogic.copyBizMsgToGlblMsg(cMsg, sMsg);

        // Update DB
//        if (!doUpdate_CurrentLoc(cMsg, sMsg)) {
//            return;
//        }

//        // Refresh screen
//        doProcess_DSAL0090Scrn00_TAB_Detail(cMsg, sMsg);
//
//        // Show first updated/inserted record
//        showFisrtUpdatedRow_TabDetail(cMsg, sMsg);
//
        // Process complete message
//        cMsg.setMessageInfo("ZZM8100I");
//    }
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]

    private boolean doUpdate_Header(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        // mod start 2016/08/30 CSA Defect#13532
        SVC_MACH_MSTRTMsg bfMachTMsg = getSvcMachMstrTMsg(cMsg.glblCmpyCd, cMsg.svcMachMstrPk_H1);
        // Insert process
        if (!doUpdate_Insert_Header(cMsg, sMsg)) {
            return false;
        }

        SVC_MACH_MSTRTMsg afMachTMsg = getSvcMachMstrTMsg(cMsg.glblCmpyCd, cMsg.svcMachMstrPk_H1);
        // Update Accessory IB
        if (isUpdateAccIB(bfMachTMsg, afMachTMsg)) {
            // START 2018/05/08 K.Kitachi [QC#23604, MOD]
//            updateAccIB(afMachTMsg, cMsg.slsDt.getValue());
            updateAccIB(afMachTMsg, cMsg.slsDt.getValue(), null);
            // END 2018/05/08 K.Kitachi [QC#23604, MOD]
        }
        // mod end 2016/08/30 CSA Defect#13532
        return true;
    }

    private boolean doUpdate_Insert_Header(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        // START 2016/08/29 A.Kohinata [QC#13240, ADD]
        // START 2016/12/19 K.Kojima [QC#16600,MOD]
        // if (NSAL0010CommonLogic.existsSerNum(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H2.getValue())) {
        if (NSAL0010CommonLogic.existsSerNum(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H2.getValue(), cMsg.mdseCd_H1.getValue())) {
        // END 2016/12/19 K.Kojima [QC#16600,MOD]
            // START 2017/01/27 K.Kojima [QC#16600,MOD]
            // cMsg.serNum_H2.setErrorInfo(1, NSAM0059E, new String[] {"New Serial#" });
            cMsg.serNum_H2.setErrorInfo(1, NSAM0622E, new String[] {"New Serial#" });
            cMsg.mdseCd_H1.setErrorInfo(1, NSAM0622E, new String[] {"New Serial#" });
            // END 2017/01/27 K.Kojima [QC#16600,MOD]
            return false;
        }
        // END 2016/08/29 A.Kohinata [QC#13240, ADD]
        boolean doUpdateInsert = true;
        if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            // START 2015/11/15 T.Tomita [QC#970, ADD]
            // START 2016/12/19 K.Kojima [QC#16600,MOD]
            // if (NSAL0010CommonLogic.existsSerNum(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H1.getValue())) {
            if (NSAL0010CommonLogic.existsSerNum(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H1.getValue(), cMsg.mdseCd_H1.getValue())) {
            // END 2016/12/19 K.Kojima [QC#16600,MOD]
                // START 2017/01/27 K.Kojima [QC#16600,MOD]
                // cMsg.serNum_H1.setErrorInfo(1, NSAM0059E, new String[] {"Serial#" });
                cMsg.serNum_H1.setErrorInfo(1, NSAM0622E, new String[] {"Serial#" });
                cMsg.mdseCd_H1.setErrorInfo(1, NSAM0622E, new String[] {"Serial#" });
                // END 2017/01/27 K.Kojima [QC#16600,MOD]
                return false;
            }
            // END 2015/11/15 T.Tomita [QC#970, ADD]
            //insert
            NSZC001001PMsg pMsg = new NSZC001001PMsg();
            setParamInsert(pMsg, cMsg, sMsg);
            // TODO QC#17822 add log
            S21InfoLogOutput.println(pMsg.toString());
            new NSZC001001().execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                doUpdateInsert = false;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk_H1, pMsg.svcMachMstrPk.getValue());
            }
        // Add Start 2018/06/05 QC#23428
        } else if (isSvcExch(cMsg)) {
            NSZC001001PMsg pMsg = new NSZC001001PMsg();
            setParamUpdateForExch(pMsg, cMsg);
            new NSZC001001().execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                doUpdateInsert = false;
            }
        // Add End 2018/06/05 QC#23428
        } else {
            //update Mode

            // START 2017/01/20 K.Kojima [QC#16600,ADD]
            if (NSAL0010CommonLogic.existsSerNum(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H1.getValue(), cMsg.mdseCd_H1.getValue(), cMsg.svcMachMstrPk_H1.getValue())) {
                // START 2017/01/27 K.Kojima [QC#16600,MOD]
                // cMsg.serNum_H1.setErrorInfo(1, NSAM0059E, new String[] {"Serial#" });
                cMsg.serNum_H1.setErrorInfo(1, NSAM0622E, new String[] {"Serial#" });
                cMsg.mdseCd_H1.setErrorInfo(1, NSAM0622E, new String[] {"Serial#" });
                // END 2017/01/27 K.Kojima [QC#16600,MOD]
                return false;
            }
            // END 2017/01/20 K.Kojima [QC#16600,ADD]

            NSZC001001PMsg pMsg = new NSZC001001PMsg();
            setParamUpdate(pMsg, cMsg, sMsg);
            // TODO QC#17822 add log
            S21InfoLogOutput.println(pMsg.toString());
            new NSZC001001().execute(pMsg, ONBATCH_TYPE.ONLINE);

            if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                S21ApiMessage msg = msgList.get(0);
                cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                doUpdateInsert = false;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk_H1, pMsg.svcMachMstrPk.getValue());
            }
            // START 2023/07/31 Y.Nagasawa [QC#61632, ADD]
            boolean swLicIdUpdateFlg = false;
            String svcMachMstrSwLicId =  NSAL0010Query.getInstance().getSwLicId(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue());
            if (cMsg.swLicId_H1.getValue().equals("")) {
                if (!svcMachMstrSwLicId.equals("")) {
                    swLicIdUpdateFlg = true;
                }
            } else {
                if (svcMachMstrSwLicId.equals("")) {
                    swLicIdUpdateFlg = true;
                } else {
                    if (!svcMachMstrSwLicId.equals(cMsg.swLicId_H1.getValue())) {
                        swLicIdUpdateFlg = true;
                    }
                }
            }
            if (swLicIdUpdateFlg) {
                NSAL0010Query.getInstance().updateSwLicId(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue(), cMsg.swLicId_H1.getValue());
            }
            // END 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        }
        return doUpdateInsert;
    }

    private void setParamInsert(NSZC001001PMsg pMsg, NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt.getValue());
        // START 2016/10/13 T.Tomita [QC#14734, MOD]
        if (SVC_MACH_MSTR_STS.CREATED.equals(cMsg.svcMachMstrStsCd_H3.getValue()) || SVC_MACH_MSTR_STS.REMOVED.equals(cMsg.svcMachMstrStsCd_H3.getValue()) || SVC_MACH_MSTR_STS.TERMINATED.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.DUPLICATE.equals(cMsg.svcMachMstrStsCd_H3.getValue())) {
        // END 2016/10/13 T.Tomita [QC#14734, MOD]
            //mode ins_wh
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
        // START 2016/10/13 T.Tomita [QC#14734, MOD]
        } else if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.INSTALLED.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(cMsg.svcMachMstrStsCd_H3.getValue())) {
        // END 2016/10/13 T.Tomita [QC#14734, MOD]
            //mode ins-MIF
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSERT_MACHINE_IN_FIELD.code);
        }
        if (sMsg.A.getValidCount() > 0) {
            if (sMsg.A.getValidCount() > 1 && SVC_MACH_TP.MACHINE.equals(sMsg.A.no(1).svcMachTpCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, sMsg.A.no(1).svcMachMstrPk_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, sMsg.A.no(0).svcMachMstrPk_A1);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, cMsg.svcMachTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, cMsg.serNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, cMsg.mdseCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.custMachCtrlNum, cMsg.custMachCtrlNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, cMsg.svcMachMstrStsCd_H3.getValue());
        // START 2016/02/26 T.Tomita [QC#942, MOD]
        if (funcIdList.contains(FUNC_ID_LGSC)) {
            ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, cMsg.stkStsCd_H3.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, cMsg.svcMachMstrLocStsCd_H3.getValue());
        } else {
            NSAL0010CommonLogic.setDefaultValue(pMsg, cMsg, sMsg);
        }
        // END 2016/02/26 T.Tomita [QC#942, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_OFF_N);
        // START 2015/11/16 T.Tomita [QC647, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.istlDt, cMsg.istlDt_H1);
        // END 2015/11/16 T.Tomita [QC647, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, cMsg.startDt_H1.getValue());
        // START 2016/10/13 T.Tomita [QC#14734, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, cMsg.svcMachRmvDt_H1);
        // END 2016/10/13 T.Tomita [QC#14734, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, cMsg.svcMachUsgStsCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, cMsg.prntSerNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTrxTpCd, cMsg.svcMachTrxTpCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, cMsg.svcMachQty_H1.getValue());
        // START 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.swLicId, cMsg.swLicId_H1.getValue());
        // END 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        int cmptCnt = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // START 2015/11/16 T.Tomita [QC#647, ADD]
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A)) {
                continue;
            }
            // END 2015/11/16 T.Tomita [QC#647, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, sMsg.A.no(i).mdseCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).effThruDt, sMsg.A.no(i).svcMachRmvDt_A.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, sMsg.A.no(i).svcMachTpCd_A);
            // START 2015/11/06 T.Tomita [QC#474, ADD]
            if (NSAL0010CommonLogic.isSame(cMsg.svcMachMstrPk_H1.getValue(), sMsg.A.no(i).svcMachMstrPk_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, sMsg.A.no(i).svcMachTpCd_A);
            }
            // END 2015/11/06 T.Tomita [QC#474, ADD]
            cmptCnt++;
        }
        pMsg.xxCmptMachList.setValidCount(cmptCnt);
        if (ProcessMode.INSERT_WAREHOUSE.name().equals(pMsg.xxModeCd.getValue())) {
            //mode ins_wh
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
        }
        // START 2016/05/16 T.Tomita [QC#4578, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, cMsg.ownrAcctNum_M1.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, cMsg.ownrLocNum_M1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, cMsg.billToAcctNum_M2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, NSAL0010CommonLogic.getBillToCustCd(cMsg.glblCmpyCd.getValue(), cMsg.indBillToLocNum_M2.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.curLocAcctNum, cMsg.curLocAcctNum_M3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, NSAL0010CommonLogic.getShipToCustCd(cMsg.glblCmpyCd.getValue(), cMsg.indCurLocNum_M3.getValue()));
        // END 2016/05/16 T.Tomita [QC#4578, MOD]
        // START 2017/02/14 K.Kitachi [QC#17309, ADD]
        // START 2018/05/28 K.Kitachi [QC#26298, MOD]
//        setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
        // START 2018/08/23 K.Kitachi [QC#27773, MOD]
//        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.wtyAutoCratFlg_H1.getValue())) {
//            setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
//        }
        setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.wtyAutoCratFlg, cMsg.wtyAutoCratFlg_H1);
        // END 2018/08/23 K.Kitachi [QC#27773, MOD]
        // END 2018/05/28 K.Kitachi [QC#26298, MOD]
        // END 2017/02/14 K.Kitachi [QC#17309, ADD]
    }

    private void setParamUpdate(NSZC001001PMsg pMsg, NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt.getValue());
        // START 2023/09/22 T.Kuroda [QC#61265, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, cMsg.svcMachMstrLocStsCd_H3);
        // END   2022/09/22 T.Kuroda [QC#61265, ADD]
        if (SVC_MACH_MSTR_STS.CREATED.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.REMOVED.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.DUPLICATE.equals(cMsg.svcMachMstrStsCd_H3.getValue())) {
            //mode upd_wh
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
        // START 2016/10/13 T.Tomita [QC#14734, MOD]
        } else if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.INSTALLED.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(cMsg.svcMachMstrStsCd_H3.getValue())) {
        // END 2016/10/13 T.Tomita [QC#14734, MOD]
            //mode upd-mif
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_MACHINE_IN_FIELD.code);
        } else if (SVC_MACH_MSTR_STS.TERMINATED.equals(cMsg.svcMachMstrStsCd_H3.getValue())) {
            //mode upd-trmn
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_TERMINATION.code);
            // START 2023/09/22 T.Kuroda [QC#61265, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS_BLANK);
            // END   2022/09/22 T.Kuroda [QC#61265, ADD]
            // START 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.xxTrmnFlg, ZYPConstant.FLG_ON_Y);
            // END 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
        }
        if (sMsg.A.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, sMsg.A.no(0).svcConfigMstrPk_A.getValue());
        // START 2024/01/06 R.Kurahashi [QC#63402, MOD]
        //}
        } else {
            SVC_MACH_MSTRTMsg tMsg = NSAL0010CommonLogic.findSvcMachMstrForUpdate(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue());
            if (hasValue(tMsg.svcConfigMstrPk.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, tMsg.svcConfigMstrPk.getValue());
            }
        }
        // END 2024/01/06 R.Kurahashi [QC#63402, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk_H1.getValue());
        if (!ZYPCommonFunc.hasValue(cMsg.xxChkBox_H1)
                || ZYPConstant.FLG_OFF_N.equals(cMsg.xxChkBox_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.serNum, cMsg.serNum_H1.getValue());
            // START 2023/08/23 R.Jin [QC#61808, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, cMsg.prntSerNum_H1.getValue());
            // END 2023/08/23 R.Jin [QC#61808, ADD]
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.serNum, cMsg.serNum_H2.getValue());
            // START 2017/03/22 K.Kitachi [QC#15679, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.oldSerNum, cMsg.serNum_H1.getValue());
            // END 2017/03/22 K.Kitachi [QC#15679, ADD]
            // START 2023/08/23 R.Jin [QC#61808, ADD]
            if(cMsg.svcMachTpCd_H1.getValue().equals(SVC_MACH_TP.MACHINE)) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum,  cMsg.serNum_H2.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, cMsg.prntSerNum_H1.getValue());
            }
            // END 2023/08/23 R.Jin [QC#61808, ADD]
        }
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, cMsg.mdseCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.custMachCtrlNum, cMsg.custMachCtrlNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, cMsg.svcMachMstrStsCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, cMsg.stkStsCd_H3.getValue());
        // START 2023/09/22 T.Kuroda [QC#61265, DEL]
        // START 2016/02/26 T.Tomita [QC#942, ADD]
        //ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, cMsg.svcMachMstrLocStsCd_H3);
        // END 2016/02/26 T.Tomita [QC#942, ADD]
        // END   2022/09/22 T.Kuroda [QC#61265, DEL]
        // START 2017/03/22 K.Kitachi [QC#15679, DEL]
//        ZYPEZDItemValueSetter.setValue(pMsg.oldSerNum, cMsg.serNum_H1.getValue());
        // END 2017/03/22 K.Kitachi [QC#15679, DEL]
        if (sMsg.A.getValidCount() > 0) {
            if (sMsg.A.getValidCount() > 1 && SVC_MACH_TP.MACHINE.equals(sMsg.A.no(1).svcMachTpCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, sMsg.A.no(1).svcMachMstrPk_A1);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, sMsg.A.no(0).svcMachMstrPk_A1);
            }
        }
        // START 2015/11/16 T.Tomita [QC647, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.istlDt, cMsg.istlDt_H1);
        // END 2015/11/16 T.Tomita [QC647, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, cMsg.startDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, cMsg.svcMachRmvDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, cMsg.svcMachUsgStsCd_H3.getValue());
        // START 2023/08/23 R.Jin [QC#61808, DEL]
//        ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, cMsg.prntSerNum_H1.getValue());
        // END 2023/08/23 R.Jin [QC#61808, DEL]
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTrxTpCd, cMsg.svcMachTrxTpCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, cMsg.svcMachQty_H1.getValue());
        // START 2015/11/06 T.Tomita [QC#474, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, cMsg.svcMachTpCd_H1.getValue());
        // END 2015/11/06 T.Tomita [QC#474, ADD]
        int cmptCnt = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // START 2015/11/16 T.Tomita [QC#647, ADD]
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).mdseCd_A)) {
                continue;
            }
            // END 2015/11/16 T.Tomita [QC#647, ADD]
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A1.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, sMsg.A.no(i).mdseCd_A.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).effThruDt, sMsg.A.no(i).svcMachRmvDt_A.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, sMsg.A.no(i).svcMachTpCd_A);
            // START 2015/11/06 T.Tomita [QC#474, ADD]
            if (NSAL0010CommonLogic.isSame(cMsg.svcMachMstrPk_H1.getValue(), sMsg.A.no(i).svcMachMstrPk_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, sMsg.A.no(i).svcMachTpCd_A);
            }
            // END 2015/11/06 T.Tomita [QC#474, ADD]
            cmptCnt++;
        }
        pMsg.xxCmptMachList.setValidCount(cmptCnt);
        // START 2016/02/26 T.Tomita [QC#942, DEL]
//        if (ProcessMode.UPDATE_WAREHOUSE.name().equals(pMsg.xxModeCd.getValue())) {
//            //mode upd_wh
//            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, LOC_STS.DC_STOCK);
//        }
        // END 2016/02/26 T.Tomita [QC#942, DEL]

        // START 2016/05/16 T.Tomita [QC#4578, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, cMsg.ownrAcctNum_M1.getValue());
//        ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, cMsg.ownrLocNum_M1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, cMsg.billToAcctNum_M2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, NSAL0010CommonLogic.getBillToCustCd(cMsg.glblCmpyCd.getValue(), cMsg.indBillToLocNum_M2.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.curLocAcctNum, cMsg.curLocAcctNum_M3.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, NSAL0010CommonLogic.getShipToCustCd(cMsg.glblCmpyCd.getValue(), cMsg.indCurLocNum_M3.getValue()));
        // END 2016/05/16 T.Tomita [QC#4578, MOD]
        // START 2017/02/14 K.Kitachi [QC#17309, ADD]
        // START 2018/05/28 K.Kitachi [QC#26298, MOD]
//        setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
        // START 2018/08/23 K.Kitachi [QC#27773, MOD]
//        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.wtyAutoCratFlg_H1.getValue())) {
//            setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
//        }
        setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.wtyAutoCratFlg, cMsg.wtyAutoCratFlg_H1);
        // END 2018/08/23 K.Kitachi [QC#27773, MOD]
        // END 2018/05/28 K.Kitachi [QC#26298, MOD]
        // END 2017/02/14 K.Kitachi [QC#17309, ADD]
    }

    // START 2016/05/13 T.Tomita [QC#7794, MOD]
    private boolean doUpdate_Contacts(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, List<BigDecimal> dsCtacPntPkList) {

        for (int i = 0; i < sMsg.N.getValidCount(); i++) {
            if (!deleteSVC_MACH_CTAC_PSN(cMsg, sMsg.N.no(i))) {
                return false;
            }
        }

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NSAL0010_CSMsg ccMsg = sMsg.C.no(i);
            // START 2016/02/22 T.Tomita [QC#969 MOD]
            // call Contact Update API
            if (!ZYPCommonFunc.hasValue(ccMsg.ctacPsnPk_C)) {
                // insert mode
                if (!insertCtacPsnInfo(cMsg, ccMsg, dsCtacPntPkList)) {
                    return false;
                }
            } else {
                // update mode
                if (!updateCtacPsnInfo(cMsg, ccMsg, dsCtacPntPkList)) {
                    return false;
                }
            }
            // update SVC_MACH_CTAC_PSN
            if (!ZYPCommonFunc.hasValue(ccMsg.svcMachCtacPsnPk_C)) {
                if (!insertSVC_MACH_CTAC_PSN(cMsg, ccMsg)) {
                    return false;
                }
            } else {
                if (!updateSVC_MACH_CTAC_PSN(cMsg, ccMsg)) {
                    return false;
                }
            }
            // END 2016/02/22 T.Tomita [QC#969 MOD]
        }
        return true;
    }
    // END 2016/05/13 T.Tomita [QC#7794, MOD]

    private boolean deleteSVC_MACH_CTAC_PSN(NSAL0010CMsg cMsg, NSAL0010_NSMsg nsMsg) {
        SVC_MACH_CTAC_PSNTMsg tMsg = new SVC_MACH_CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachCtacPsnPk, nsMsg.svcMachCtacPsnPk_N);
        tMsg = (SVC_MACH_CTAC_PSNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg != null) {
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAL0010Constant.NSAM0001E, new String[] {"SVC_MACH_CTAC_PSN", "glblCmpyCd=" + tMsg.glblCmpyCd + ", svcMachCtacPsnPk=" + tMsg.svcMachCtacPsnPk });
                return false;
            }
        }
        return true;
    }

    private boolean updateSVC_MACH_CTAC_PSN(NSAL0010CMsg cMsg, NSAL0010_CSMsg ccMsg) {
        SVC_MACH_CTAC_PSNTMsg tMsg = new SVC_MACH_CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachCtacPsnPk, ccMsg.svcMachCtacPsnPk_C);
        tMsg = (SVC_MACH_CTAC_PSNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnFirstNm, ccMsg.ctacPsnFirstNm_C);
            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnLastNm, ccMsg.ctacPsnLastNm_C);
            ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPntPk, ccMsg.dsCtacPntPk_C);
            ZYPEZDItemValueSetter.setValue(tMsg.svcCtacTpCd, ccMsg.svcCtacTpCd_CS);
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, ccMsg.effFromDt_C);
            ZYPEZDItemValueSetter.setValue(tMsg.effThruDt, ccMsg.effThruDt_C);
            // START 2018/09/27 K.Fujimoto [QC#27788 ADD]
            if (ZYPConstant.FLG_ON_Y.equals(ccMsg.xxChkBox_C.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsPrimLocFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
            }
            // END 2018/09/27 K.Fujimoto [QC#27788 ADD]
            EZDTBLAccessor.update(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAL0010Constant.NSAM0001E, new String[] {"SVC_MACH_CTAC_PSN", "glblCmpyCd=" + tMsg.glblCmpyCd + ", svcMachCtacPsnPk=" + tMsg.svcMachCtacPsnPk });
                return false;
            }
        }
        return true;
    }

    private boolean insertSVC_MACH_CTAC_PSN(NSAL0010CMsg cMsg, NSAL0010_CSMsg ccMsg) {
        SVC_MACH_CTAC_PSNTMsg psnTMsg = new SVC_MACH_CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(psnTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(psnTMsg.svcMachCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_MACH_CTAC_PSN_SQ"));
        ZYPEZDItemValueSetter.setValue(psnTMsg.svcMachMstrPk, cMsg.svcMachMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(psnTMsg.ctacPsnFirstNm, ccMsg.ctacPsnFirstNm_C);
        ZYPEZDItemValueSetter.setValue(psnTMsg.ctacPsnLastNm, ccMsg.ctacPsnLastNm_C);
        ZYPEZDItemValueSetter.setValue(psnTMsg.dsCtacPntPk, ccMsg.dsCtacPntPk_C);
        ZYPEZDItemValueSetter.setValue(psnTMsg.svcCtacTpCd, ccMsg.svcCtacTpCd_CS);
        ZYPEZDItemValueSetter.setValue(psnTMsg.effFromDt, ccMsg.effFromDt_C);
        ZYPEZDItemValueSetter.setValue(psnTMsg.effThruDt, ccMsg.effThruDt_C);
        // START 2018/09/27 K.Fujimoto [QC#27788 ADD]
        if (ZYPConstant.FLG_ON_Y.equals(ccMsg.xxChkBox_C.getValue())) {
            ZYPEZDItemValueSetter.setValue(psnTMsg.dsPrimLocFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(psnTMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2018/09/27 K.Fujimoto [QC#27788 ADD]
        EZDTBLAccessor.insert(psnTMsg);
        if (!RTNCD_NORMAL.equals(psnTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAL0010Constant.NSAM0012E, new String[] {"SVC_MACH_CTAC_PSN", "glblCmpyCd=" + psnTMsg.glblCmpyCd + ", svcMachMstrPk=" + psnTMsg.svcMachMstrPk });
            return false;
        }
        return true;
    }

    private boolean doUpdate_AddlAttrb(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        boolean doUpdateInsert = true;
        //update Mode
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        setParamUpdateForAddlAttrb(pMsg, cMsg, sMsg);
        // TODO QC#17822 add log
        S21InfoLogOutput.println(pMsg.toString());
        new NSZC001001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            doUpdateInsert = false;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk_H1, pMsg.svcMachMstrPk.getValue());
        }

        return doUpdateInsert;
    }

// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//    private boolean doUpdate_CurrentLoc(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        //
//        SVC_MACH_MSTRTMsg inParam = new SVC_MACH_MSTRTMsg();
//        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, cMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(inParam.svcMachMstrPk, cMsg.svcMachMstrPk_H1);

//        inParam = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inParam);
//        if (inParam == null) {
//            cMsg.setMessageInfo("ZZOM0013E");
//            return false;
//        }

        // Anyone update
//        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_H1.getValue(), cMsg.ezUpTimeZone_H1.getValue()
//                , inParam.ezUpTime.getValue(), inParam.ezUpTimeZone.getValue())) {
//            cMsg.setMessageInfo("ZZSM4104E");
//            return false;
//        }

// START 2016/11/14 N.Arai [QC#15829, MOD]
//        Map<String, String> beforParamMap = new HashMap<String, String>();
//        beforParamMap.put(LOC_NM, inParam.locNm.getValue());
//        beforParamMap.put(ADDL_LOC_NM, inParam.addlLocNm.getValue());
//        beforParamMap.put(CTRY_CD, inParam.ctryCd.getValue());
//        beforParamMap.put(FIRST_LINE_ADDR, inParam.firstLineAddr.getValue());
//        beforParamMap.put(SCD_LINE_ADDR, inParam.scdLineAddr.getValue());
//        beforParamMap.put(THIRD_LINE_ADDR, inParam.thirdLineAddr.getValue());
//        beforParamMap.put(FRTH_LINE_ADDR, inParam.frthLineAddr.getValue());
//        beforParamMap.put(SVC_MACH_FL_NM, inParam.svcMachFlNm.getValue());
//        beforParamMap.put(POST_CD, inParam.postCd.getValue());
//        beforParamMap.put(CTY_ADDR, inParam.ctyAddr.getValue());
//        beforParamMap.put(ST_CD, inParam.stCd.getValue());
//        beforParamMap.put(CNTY_NM, inParam.cntyNm.getValue());
//        beforParamMap.put(PROV_NM, inParam.provNm.getValue());
// END 2016/11/14 N.Arai [QC#15829, MOD]

//        ZYPEZDItemValueSetter.setValue(inParam.locNm, cMsg.locNm_F);
//        ZYPEZDItemValueSetter.setValue(inParam.addlLocNm, cMsg.addlLocNm_F);
//        ZYPEZDItemValueSetter.setValue(inParam.ctryCd, cMsg.ctryCd_FS);
//        ZYPEZDItemValueSetter.setValue(inParam.firstLineAddr, cMsg.firstLineAddr_F);
//        ZYPEZDItemValueSetter.setValue(inParam.scdLineAddr, cMsg.scdLineAddr_F);
//        ZYPEZDItemValueSetter.setValue(inParam.thirdLineAddr, cMsg.thirdLineAddr_F);
//        ZYPEZDItemValueSetter.setValue(inParam.frthLineAddr, cMsg.frthLineAddr_F);
//        ZYPEZDItemValueSetter.setValue(inParam.svcMachFlNm, cMsg.svcMachFlNm_F);
//        ZYPEZDItemValueSetter.setValue(inParam.postCd, cMsg.postCd_F);
//        ZYPEZDItemValueSetter.setValue(inParam.ctyAddr, cMsg.ctyAddr_F);
//        ZYPEZDItemValueSetter.setValue(inParam.stCd, cMsg.stCd_F);
//        ZYPEZDItemValueSetter.setValue(inParam.cntyNm, cMsg.cntyNm_F);
//        ZYPEZDItemValueSetter.setValue(inParam.provNm, cMsg.provNm_F);
//        EZDTBLAccessor.update(inParam);
//        if (!RTNCD_NORMAL.equals(inParam.getReturnCode())) {
//            cMsg.setMessageInfo("NSZM0036E");
//            return false;
//        }

// START 2016/11/14 N.Arai [QC#15829, MOD]
//        if (!insertSvcMachMstrTrk(cMsg, beforParamMap, inParam)) {
//            cMsg.setMessageInfo(NSAM0032E, new String[] {"SVC_MACH_MSTR_TRK"});
//            return false;
//        }
// END 2016/11/14 N.Arai [QC#15829, MOD]
//        return true;
//    }
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]

    private void setParamUpdateForAddlAttrb(NSZC001001PMsg pMsg, NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt.getValue());
        // mode upd_wh
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.UPDATE_ATTRIBUTE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.prfTechCd, cMsg.prfTechCd_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, cMsg.stkStsCd_H3.getValue());
        // START 2016/12/13 K.Ochiai [QC#16563, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, cMsg.custIssPoNum_D.getValue());
        // END 2016/12/13 K.Ochiai [QC#16563, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_01, cMsg.ctrlFldTxt_01.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_02, cMsg.ctrlFldTxt_02.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_03, cMsg.ctrlFldTxt_03.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_04, cMsg.ctrlFldTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_05, cMsg.ctrlFldTxt_05.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_06, cMsg.ctrlFldTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, cMsg.prcContrNum_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.corpAdvtgStsCd, cMsg.corpAdvtgStsCd_DS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsPoExprDt, cMsg.dsPoExprDt_D.getValue());

        // START 2017/06/22 Y.Osawa [QC#19054, MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveRmvInclFlg, cMsg.xxChkBox_D1.getValue());
//      ZYPEZDItemValueSetter.setValue(pMsg.hardDriveEraseInclFlg, cMsg.xxChkBox_D2.getValue());
//      ZYPEZDItemValueSetter.setValue(pMsg.leaseRtrnFeeInclFlg, cMsg.xxChkBox_D3.getValue());
//      ZYPEZDItemValueSetter.setValue(pMsg.dsKeyAcctFlg, cMsg.xxChkBox_D4.getValue());

        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_D1) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_D2) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_D2.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_D3) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_D3.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.leaseRtrnFeeInclFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.leaseRtrnFeeInclFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_D4) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_D4.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsKeyAcctFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.dsKeyAcctFlg, ZYPConstant.FLG_OFF_N);
        }
        // END   2017/06/22 Y.Osawa [QC#19054, MOD]

        ZYPEZDItemValueSetter.setValue(pMsg.svcNtwkConnStsCd, cMsg.svcNtwkConnStsCd_DS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.sldByLineBizTpCd, cMsg.sldByLineBizTpCd_DS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcLicCnt, cMsg.svcLicCnt_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.reqTechCd, cMsg.reqTechCd_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.prfTechCd, cMsg.prfTechCd_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.fldSvcBrCd, cMsg.svcBrCd_D.getValue());
        // START 2023/10/06 K.Ishizuka [QC#54186, MOD]
//      ZYPEZDItemValueSetter.setValue(pMsg.svcByLineBizTpCd, cMsg.svcByLineBizTpCd_DS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.istlBySvcPrvdPtyCd, cMsg.istlBySvcPrvdPtyCd_DS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcBySvcPrvdPtyCd, cMsg.svcBySvcPrvdPtyCd_DS.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, cMsg.svcMachMstrStsCd_H3.getValue());
        // END 2023/10/06 K.Ishizuka [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.finBrCd, cMsg.finBrCd_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.bizHrsSunFromTm, cMsg.bizHrsSunFromTm_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.bizHrsSunToTm, cMsg.bizHrsSunToTm_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.bizHrsMonFriFromTm, cMsg.bizHrsMonFriFromTm_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.bizHrsMonFriToTm, cMsg.bizHrsMonFriToTm_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.bizHrsSatFromTm, cMsg.bizHrsSatFromTm_D.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.bizHrsSatToTm, cMsg.bizHrsSatToTm_D.getValue());
        // START 2015/11/16 T.Tomita [QC#647, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.enetCmntTxt_01, cMsg.enetCmntTxt_01);
        ZYPEZDItemValueSetter.setValue(pMsg.enetCmntTxt_02, cMsg.enetCmntTxt_02);
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_D5) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_D5.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.enetPlotFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.enetPlotFlg, ZYPConstant.FLG_OFF_N);
        }

        int nonPrfCnt = 0;
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxNonPrfTechList.no(nonPrfCnt).dsCustNonPrfTechPk, sMsg.E.no(i).svcNonPrfTechPk_E.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxNonPrfTechList.no(nonPrfCnt).nonPrfTechCd, sMsg.E.no(i).nonPrfTechCd_E.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxNonPrfTechList.no(nonPrfCnt).effThruDt, sMsg.E.no(i).effThruDt_E.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.xxNonPrfTechList.no(nonPrfCnt).delFlg, ZYPConstant.FLG_OFF_N);
            nonPrfCnt++;
        }

        // mod start 2016/04/25 CSA Defect#5522
        for (int i = 0; i < sMsg.P.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxNonPrfTechList.no(nonPrfCnt).dsCustNonPrfTechPk, sMsg.P.no(i).svcNonPrfTechPk_P);
            ZYPEZDItemValueSetter.setValue(pMsg.xxNonPrfTechList.no(nonPrfCnt).delFlg, ZYPConstant.FLG_ON_Y);
            nonPrfCnt++;
        }
        pMsg.xxNonPrfTechList.setValidCount(nonPrfCnt);
        // mod end 2016/04/25 CSA Defect#5522
        // END 2015/11/16 T.Tomita [QC#647, MOD]
    }

    // START 2016/02/22 T.Tomita [QC#969 MOD]
    // START 2016/05/13 T.Tomita [QC#7794, MOD]
    private boolean insertCtacPsnInfo(NSAL0010CMsg cMsg, NSAL0010_CSMsg ccMsg, List<BigDecimal> dsCtacPntPkList) {
        //insert service machine contacts person
        NMZC002001PMsg pMsg = new NMZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt);
        // START 2016/06/29 T.Tomita [QC#10489, MOD]
        // START 2017/11/21 W.Honda [QC#22087, MOD]
//        BigDecimal ctacPsnPk = getCtacPsnPk(cMsg, ccMsg);
//        if (!ZYPCommonFunc.hasValue(ctacPsnPk)) {
//            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
//        } else {
//            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
//            ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnPk, ctacPsnPk);
//        }
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        // END 2017/11/21 W.Honda [QC#22087, MOD]
        // END 2016/06/29 T.Tomita [QC#10489, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, cMsg.curLocAcctNum_M3);
        // del start 2016/04/25 CSA Defect#6672
//        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, ccMsg.effFromDt_C);
//        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, ccMsg.effThruDt_C);
        // del end 2016/04/25 CSA Defect#6672
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnFirstNm, ccMsg.ctacPsnFirstNm_C);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnLastNm, ccMsg.ctacPsnLastNm_C);
        // START 2017/10/18 M.Naito [QC#20246, MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.ctacTpCd, CTAC_TP.IB_CONTACT);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacTpCd, CTAC_TP.DELIVERY_INSTALL);
        // END 2017/10/18 M.Naito [QC#20246, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPntTpCd, ccMsg.dsCtacPntTpCd_CS);
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPntValTxt, ccMsg.dsCtacPntValTxt_C);
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPsnExtnNum, ccMsg.dsCtacPsnExtnNum_C);
        // 2019/01/16 QC#29642 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).updCtrlFlg, ZYPConstant.FLG_ON_Y);
        // 2019/01/16 QC#29642 Add End
        // START 2017/08/22 N.Sugiura [QC#8598, DEL]
        // String dsCtacPntActvFlg = ZYPConstant.FLG_OFF_N;
        // if (ZYPCommonFunc.hasValue(ccMsg.xxChkBox_C)) {
        //    dsCtacPntActvFlg = ccMsg.xxChkBox_C.getValue();
        // }
        // START 2016/10/27 [QC#15607, mod]
//        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPntActvFlg, dsCtacPntActvFlg);
        // ZYPEZDItemValueSetter.setValue(pMsg.dsPrimLocFlg, dsCtacPntActvFlg);
        // END 2016/10/27 [QC#15607, mod]
       // END 2017/08/22 N.Sugiura [QC#8598, DEL]
        pMsg.ContactPointInfoList.setValidCount(1);

        new NMZC002001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }

        // START 2017/06/27 K.Kim [QC#18479, DEL]
//        // check duplicate DS_CTAC_PNT_PK
//        if (isDuplicateDsCtacPntPk(ccMsg, dsCtacPntPkList, pMsg.ContactPointInfoList.no(0).dsCtacPntPk.getValue())) {
//            return false;
//        }
        // END 2017/06/27 K.Kim [QC#18479, DEL]

        // set DS_CTAC_PNT_PK
        ZYPEZDItemValueSetter.setValue(ccMsg.dsCtacPntPk_C, pMsg.ContactPointInfoList.no(0).dsCtacPntPk);
        return true;
    }

    private boolean updateCtacPsnInfo(NSAL0010CMsg cMsg, NSAL0010_CSMsg ccMsg, List<BigDecimal> dsCtacPntPkList) {
        //update service machine contacts person
        NMZC002001PMsg pMsg = new NMZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnPk, ccMsg.ctacPsnPk_C);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, cMsg.curLocAcctNum_M3);
        // del start 2016/04/25 CSA Defect#6672
//        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, ccMsg.effFromDt_C);
//        ZYPEZDItemValueSetter.setValue(pMsg.effThruDt, ccMsg.effThruDt_C);
        // del end 2016/04/25 CSA Defect#6672
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnFirstNm, ccMsg.ctacPsnFirstNm_C);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacPsnLastNm, ccMsg.ctacPsnLastNm_C);
        // START 2017/10/18 M.Naito [QC#20246, MOD]
//        ZYPEZDItemValueSetter.setValue(pMsg.ctacTpCd, CTAC_TP.IB_CONTACT);
        ZYPEZDItemValueSetter.setValue(pMsg.ctacTpCd, CTAC_TP.DELIVERY_INSTALL);
        // END 2017/10/18 M.Naito [QC#20246, MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPntPk, ccMsg.dsCtacPntPk_C);
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPntTpCd, ccMsg.dsCtacPntTpCd_CS);
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPntValTxt, ccMsg.dsCtacPntValTxt_C);
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPsnExtnNum, ccMsg.dsCtacPsnExtnNum_C);
        // 2019/01/16 QC#29642 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).updCtrlFlg, ZYPConstant.FLG_ON_Y);
        // 2019/01/16 QC#29642 Add End
        // START 2017/08/22 N.Sugiura [QC#8598, DEL]
        // String dsCtacPntActvFlg = ZYPConstant.FLG_OFF_N;
        // if (ZYPCommonFunc.hasValue(ccMsg.xxChkBox_C)) {
        //     dsCtacPntActvFlg = ccMsg.xxChkBox_C.getValue();
        // }
        // START 2016/10/27 [QC#15607, mod]
//        ZYPEZDItemValueSetter.setValue(pMsg.ContactPointInfoList.no(0).dsCtacPntActvFlg, dsCtacPntActvFlg);
        // ZYPEZDItemValueSetter.setValue(pMsg.dsPrimLocFlg, dsCtacPntActvFlg);
        // END 2016/10/27 [QC#15607, mod]
        // END 2017/08/22 N.Sugiura [QC#8598, DEL]
        pMsg.ContactPointInfoList.setValidCount(1);

        new NMZC002001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }

        // START 2017/06/27 K.Kim [QC#18479, DEL]
//        // check duplicate DS_CTAC_PNT_PK
//        if (isDuplicateDsCtacPntPk(ccMsg, dsCtacPntPkList, pMsg.ContactPointInfoList.no(0).dsCtacPntPk.getValue())) {
//            return false;
//        }
        // END 2017/06/27 K.Kim [QC#18479, DEL]
        // set DS_CTAC_PNT_PK
        ZYPEZDItemValueSetter.setValue(ccMsg.dsCtacPntPk_C, pMsg.ContactPointInfoList.no(0).dsCtacPntPk);
        return true;
    }
    // END 2016/05/13 T.Tomita [QC#7794, MOD]
    // END 2016/02/22 T.Tomita [QC#969 MOD]

    // START 2017/06/27 K.Kim [QC#18479, DEL]
//    // START 2016/05/13 T.Tomita [QC#7794, ADD]
//    private boolean isDuplicateDsCtacPntPk(NSAL0010_CSMsg ccMsg, List<BigDecimal> dsCtacPntPkList, BigDecimal dsCtacPntPk) {
//        if (dsCtacPntPkList.contains(dsCtacPntPk)) {
//            setErrForCtacPnt(ccMsg, NSAM0474E, new String[] {"contact points", "selected records" });
//            return true;
//        }
//        dsCtacPntPkList.add(dsCtacPntPk);
//        return false;
//    }
//    // END 2016/05/13 T.Tomita [QC#7794, ADD]
    // END 2017/06/27 K.Kim [QC#18479, DEL]

    private boolean validation(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        if (!checkHeaderMandatory(cMsg, sMsg)) {
            return false;
        }

        // START 2016/02/26 T.Tomita [QC#942 ADD]
        if (!checkLgscItemMandatory(cMsg)) {
            return false;
        }
        // END 2016/02/26 T.Tomita [QC#942 ADD]

        // START 2016/03/30 T.Tomita [QC#5398, MOD]
        if (!NSAL0010CommonLogic.checkHeaderMaster(cMsg, sMsg)) {
            return false;
        }
        // END 2016/03/30 T.Tomita [QC#5398, MOD]

        // START 2016/02/16 [QC#2255, ADD]
        if (!checkAccount(cMsg, sMsg)) {
            return false;
        }
        // END 2016/02/16 [QC#2255, ADD]

        if (!checkActiveContr(cMsg, sMsg)) {
            return false;
        }

        if (!checkOpenOrder(cMsg, sMsg)) {
            return false;
        }

        // START 2016/02/16 [QC#2255, ADD]
        if (!checkStatusRelation(cMsg, sMsg)) {
            return false;
        }
        // END 2016/02/16 [QC#2255, ADD]

        // START 2016/03/28 [QC#4888, ADD]
        if (!checkRtlSWH(cMsg, sMsg)) {
            return false;
        }
        // END 2016/03/28 [QC#4888, ADD]

        // START 2021/08/17 R. Cabral [QC#59010, ADD]
        if (!checkTransactionTypeTransition(cMsg, sMsg)) {
            return false;
        }
        // END   2021/08/17 R. Cabral [QC#59010, ADD]

        // START 2018/05/28 K.Kitachi [QC#19932, ADD]
        if (!checkWarehouse(cMsg)) {
            cMsg.setMessageInfo(NSAM0722E);
            return false;
        }
        // END 2018/05/28 K.Kitachi [QC#19932, ADD]

        // START 2015/11/16 T.Tomita [QC#647, ADD]
        int errLine = checkConfig(cMsg, sMsg);
        if (errLine > -1) {
            int startLine = errLine + 1;
            if (startLine % cMsg.A.length() > 0) {
                startLine = startLine / cMsg.A.length() * cMsg.A.length() + 1;
                setValue(cMsg.xxPageShowFromNum_A, new BigDecimal(startLine));
            } else {
                startLine = startLine / cMsg.A.length() * cMsg.A.length();
                setValue(cMsg.xxPageShowFromNum_A, new BigDecimal(startLine));
            }
            NSAL0010CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
            setValue(cMsg.xxPageShowOfNum_A, new BigDecimal(sMsg.A.getValidCount()));
            setValue(cMsg.xxPageShowToNum_A, NSAL0010CommonLogic.calcXxPageShowToNum(cMsg.xxPageShowFromNum_A.getValueInt(), cMsg.A.getValidCount()));

            return false;
        }
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        // START 2020/04/15 K.Sakurai [QC#56528, ADD]
        Boolean errorFlg = false;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            String removeDate = cMsg.A.no(i).svcMachRmvDt_A.getValue();
            String installDate = cMsg.A.no(i).istlDt_A.getValue();
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).svcMachRmvDt_A) && ZYPDateUtil.compare(removeDate, installDate) < 0) {
                sMsg.A.no(i).svcMachRmvDt_A.setErrorInfo(1, NSAM0346E, new String[]{"Remove Date", "Install Date"});
                errorFlg = true;
            }
        }
        if (errorFlg) {
            return false;
        }
        // END 2020/04/15 K.Sakurai [QC#56528, ADD]
        return true;

    }

    private boolean checkHeaderMandatory(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        boolean result = true;

        if (!ZYPCommonFunc.hasValue(cMsg.mdseCd_H1)) {
            cMsg.mdseCd_H1.setErrorInfo(1, ZZM9000E, new String[]{"Mdse Code"});
            result = false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.startDt_H1)) {
            cMsg.startDt_H1.setErrorInfo(1, ZZM9000E, new String[]{"Start Date"});
            result = false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.svcMachUsgStsCd_H3)) {
            cMsg.svcMachUsgStsCd_H3.setErrorInfo(1, ZZM9000E, new String[]{"IB Usage"});
            result = false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.svcMachQty_H1)) {
            cMsg.svcMachQty_H1.setErrorInfo(1, ZZM9000E, new String[]{"Quantity"});
            result = false;
        }
        // START 2016/05/16 T.Tomita [QC#4578, MOD]
// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//        if (!ZYPCommonFunc.hasValue(cMsg.indCurLocNum_M3)) {
//            cMsg.indCurLocNum_M3.setErrorInfo(1, ZZM9000E, new String[]{"Current Loc#"});
//            result = false;
//        }
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
        // END 2016/05/16 T.Tomita [QC#4578, MOD]

        // START 2016/02/26 T.Tomita [QC#942, DEL]
//        if (!ZYPCommonFunc.hasValue(cMsg.stkStsCd_H3)) {
//            // START 2015/11/16 T.Tomita [QC#647, ADD]
//            cMsg.stkStsCd_H3.setErrorInfo(1, ZZM9000E, new String[]{"Stock Status"});
//            // END 2015/11/16 T.Tomita [QC#647, ADD]
//            result = false;
//        }
        // START 2016/02/26 T.Tomita [QC#942, DEL]

        String svcMachSts = cMsg.svcMachMstrStsCd_H3.getValue();
        if (!ZYPCommonFunc.hasValue(svcMachSts)) {
            cMsg.svcMachMstrStsCd_H3.setErrorInfo(1, ZZM9000E, new String[]{"IB Status"});
            result = false;
        }
        if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachSts) || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachSts)) {
            if (!ZYPCommonFunc.hasValue(cMsg.istlDt_H1)) {
                cMsg.istlDt_H1.setErrorInfo(1, ZZM9000E, new String[]{"Install Date"});
                result = false;
            }
        }
        if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachSts)) {
            if (!ZYPCommonFunc.hasValue(cMsg.svcMachRmvDt_H1)) {
                cMsg.svcMachRmvDt_H1.setErrorInfo(1, ZZM9000E, new String[]{"End Date"});
                result = false;
            }
        }
        if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachSts) ||  SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachSts)
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachSts)) {
            // START 2016/05/16 T.Tomita [QC#4578, MOD]
            if (!ZYPCommonFunc.hasValue(cMsg.ownrAcctNum_M1)) {
                cMsg.ownrAcctNum_M1.setErrorInfo(1, ZZM9000E, new String[]{"Account#"});
                result = false;
            }
//            if (!ZYPCommonFunc.hasValue(cMsg.ownrLocNum_M1)) {
//                cMsg.ownrLocNum_M1.setErrorInfo(1, ZZM9000E, new String[]{"Loc#"});
//                result = false;
//            }
            if (!ZYPCommonFunc.hasValue(cMsg.billToAcctNum_M2)) {
                cMsg.billToAcctNum_M2.setErrorInfo(1, ZZM9000E, new String[]{"Account#"});
                result = false;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.indBillToLocNum_M2)) {
                cMsg.indBillToLocNum_M2.setErrorInfo(1, ZZM9000E, new String[]{"Loc#"});
                result = false;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.curLocAcctNum_M3)) {
                cMsg.curLocAcctNum_M3.setErrorInfo(1, ZZM9000E, new String[]{"Account#"});
                result = false;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.indCurLocNum_M3)) {
                cMsg.indCurLocNum_M3.setErrorInfo(1, ZZM9000E, new String[]{"Loc#"});
                result = false;
            }
            // END 2016/05/16 T.Tomita [QC#4578, MOD]
            // START 2015/11/16 T.Tomita [QC#647, ADD]
            // START 2016/12/16 [QC#16217, DEL]
//            if (!ZYPCommonFunc.hasValue(cMsg.svcMachTrxTpCd_H3)) {
//                cMsg.svcMachTrxTpCd_H3.setErrorInfo(1, ZZM9000E, new String[]{"Transaction Type"});
//                result = false;
//            }
            // END 2016/12/16 [QC#16217, DEL]
            // END 2015/11/16 T.Tomita [QC#647, ADD]
        }

        if (ZYPCommonFunc.hasValue(cMsg.startDt_H1) && ZYPCommonFunc.hasValue(cMsg.svcMachRmvDt_H1)) {
            if (cMsg.startDt_H1.getValue().compareTo(cMsg.svcMachRmvDt_H1.getValue()) > 0) {
                cMsg.startDt_H1.setErrorInfo(1, NSAM0062E);
                cMsg.svcMachRmvDt_H1.setErrorInfo(1, NSAM0062E);
                result = false;
            }
        }
        return result;
    }

    // START 2016/02/26 T.Tomita [QC#942 ADD]
    private boolean checkLgscItemMandatory(NSAL0010CMsg cMsg) {
        boolean result = true;
        if (funcIdList.contains(FUNC_ID_LGSC)) {
            SVC_MACH_MSTR_STSTMsg tMsg = (SVC_MACH_MSTR_STSTMsg) ZYPCodeDataUtil.findByCode(SVC_MACH_MSTR_STS.class, getGlobalCompanyCode(), cMsg.svcMachMstrStsCd_H3.getValue());
            // Stock Status Check
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.stkStsMndFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(cMsg.stkStsCd_H3)) {
                    cMsg.stkStsCd_H3.setErrorInfo(1, ZZM9000E, new String[]{"Stock Status"});
                    result = false;
                }
            }
            // Location Status
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.locStsMndFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrLocStsCd_H3)) {
                    cMsg.svcMachMstrLocStsCd_H3.setErrorInfo(1, ZZM9000E, new String[]{"Location Status"});
                    result = false;
                }
            }
        }
        return result;
    }
    // END 2016/02/26 T.Tomita [QC#942 ADD]

    // START 2016/02/17 [QC#1986, ADD]
    private boolean checkAccount(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        // START 2016/05/16 T.Tomita [QC#4578, MOD]
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String ownrAcctNum = cMsg.ownrAcctNum_M1.getValue();
//        String ownrLocNum = cMsg.ownrLocNum_M1.getValue();
        String billToAcctNum = cMsg.billToAcctNum_M2.getValue();
        String billToLocNum = cMsg.indBillToLocNum_M2.getValue();
        String curLocAcctNum = cMsg.curLocAcctNum_M3.getValue();
        String curLocNum = cMsg.indCurLocNum_M3.getValue();
        // add start 2019/08/07 QC#52198
        String svcMachTrxTpCd = cMsg.svcMachTrxTpCd_H3.getValue();
        // add end 2019/08/07 QC#52198

        if (NSAL0010CommonLogic.hasValues(ownrAcctNum) && !NSAL0010CommonLogic.existsSellToCust(glblCmpyCd, ownrAcctNum)) {
            cMsg.ownrAcctNum_M1.setErrorInfo(1, NSAM0063E, new String[] {"Account#", "Sell To Cust Master" });
            return false;
        }
        if (NSAL0010CommonLogic.hasValues(billToAcctNum) && !NSAL0010CommonLogic.existsSellToCust(glblCmpyCd, billToAcctNum)) {
            cMsg.billToAcctNum_M2.setErrorInfo(1, NSAM0063E, new String[] {"Account#", "Sell To Cust Master" });
            return false;
        }
        if (NSAL0010CommonLogic.hasValues(curLocAcctNum) && !NSAL0010CommonLogic.existsSellToCust(glblCmpyCd, curLocAcctNum)) {
            cMsg.curLocAcctNum_M3.setErrorInfo(1, NSAM0063E, new String[] {"Account#", "Sell To Cust Master" });
            return false;
        }
//        if (NSAL0010CommonLogic.hasValues(ownrLocNum) && !NSAL0010CommonLogic.existsBillToCust(glblCmpyCd, ownrLocNum)) {
//            cMsg.ownrLocNum_M1.setErrorInfo(1, NSAM0063E, new String[] {"Location#", "Bill to Cust Master" });
//            return false;
//        }
        if (NSAL0010CommonLogic.hasValues(billToLocNum) && !NSAL0010CommonLogic.existsBillToCust(glblCmpyCd, billToLocNum)) {
            cMsg.indBillToLocNum_M2.setErrorInfo(1, NSAM0063E, new String[] {"Location#", "Bill to Cust Master" });
            return false;
        }
        if (NSAL0010CommonLogic.hasValues(curLocNum) && !NSAL0010CommonLogic.existsShipToCust(glblCmpyCd, curLocNum)) {
            cMsg.indCurLocNum_M3.setErrorInfo(1, NSAM0063E, new String[] {"Location#", "Ship to Cust Master" });
            return false;
        }

//        if (NSAL0010CommonLogic.hasValues(ownrAcctNum, ownrLocNum) && !NSAL0010CommonLogic.checkAcctBillEligible(glblCmpyCd, ownrAcctNum, ownrLocNum)) {
//            cMsg.ownrAcctNum_M1.setErrorInfo(1, NSAM0138E, new String[] {"Owner Account", "Owner Location" });
//            cMsg.ownrLocNum_M1.setErrorInfo(1, NSAM0138E, new String[] {"Owner Account", "Owner Location" });
//            return false;
//        }
        if (NSAL0010CommonLogic.hasValues(billToAcctNum, billToLocNum) && !NSAL0010CommonLogic.checkAcctBillEligible(glblCmpyCd, billToAcctNum, billToLocNum)) {
            cMsg.billToAcctNum_M2.setErrorInfo(1, NSAM0138E, new String[] {"Bill to Account", "Bill to Location" });
            cMsg.indBillToLocNum_M2.setErrorInfo(1, NSAM0138E, new String[] {"Bill to Account", "Bill to Location" });
            return false;
        }
        if (NSAL0010CommonLogic.hasValues(curLocAcctNum, curLocNum) && !NSAL0010CommonLogic.checkAcctShipEligible(glblCmpyCd, curLocAcctNum, curLocNum)) {
            cMsg.curLocAcctNum_M3.setErrorInfo(1, NSAM0138E, new String[] {"Current Location Account", "Current Location" });
            cMsg.indCurLocNum_M3.setErrorInfo(1, NSAM0138E, new String[] {"Current Location Account", "Current Location" });
            return false;
        }
        // START 2016/05/09 T.Tomita [QC#6796, MOD]
//        if (NSAL0010CommonLogic.hasValues(ownrAcctNum, billToLocNum) && !NSAL0010CommonLogic.checkAcctBillEligible(glblCmpyCd, ownrAcctNum, billToLocNum)) {
//            cMsg.ownrAcctNum_M1.setErrorInfo(1, NSAM0138E, new String[] {"Owner Account", "Bill to Location" });
//            cMsg.billToLocNum_M2.setErrorInfo(1, NSAM0138E, new String[] {"Owner Account", "Bill to Location" });
//            return false;
//        }
//        if (NSAL0010CommonLogic.hasValues(ownrAcctNum, curLocNum) && !NSAL0010CommonLogic.checkAcctShipEligible(glblCmpyCd, ownrAcctNum, curLocNum)) {
//            cMsg.ownrAcctNum_M1.setErrorInfo(1, NSAM0138E, new String[] {"Owner Account", "Current Location" });
//            cMsg.curLocNum_M3.setErrorInfo(1, NSAM0138E, new String[] {"Owner Account", "Current Location" });
//            return false;
//        }
//        if (NSAL0010CommonLogic.hasValues(billToAcctNum, ownrLocNum) && !NSAL0010CommonLogic.checkAcctBillEligible(glblCmpyCd, billToAcctNum, ownrLocNum)) {
//            cMsg.billToAcctNum_M2.setErrorInfo(1, NSAM0138E, new String[] {"Bill to Account", "Owner Location" });
//            cMsg.ownrLocNum_M1.setErrorInfo(1, NSAM0138E, new String[] {"Bill to Account", "Owner Location" });
//            return false;
//        }
        if (NSAL0010CommonLogic.hasValues(billToAcctNum, curLocNum) && !NSAL0010CommonLogic.checkAcctShipEligible(glblCmpyCd, billToAcctNum, curLocNum)) {
            cMsg.billToAcctNum_M2.setErrorInfo(1, NSAM0138E, new String[] {"Bill to Account", "Current Location" });
            cMsg.indCurLocNum_M3.setErrorInfo(1, NSAM0138E, new String[] {"Bill to Account", "Current Location" });
            return false;
        }
        // END 2016/05/09 T.Tomita [QC#6796, MOD]
        // END 2016/05/16 T.Tomita [QC#4578, MOD]
        // START 2016/05/26 T.Tomita [QC#8782, ADD]
        // mod start 2019/08/07 QC#52198
        //if (NSAL0010CommonLogic.hasValues(ownrAcctNum, billToAcctNum) && !NSAL0010CommonLogic.checkAcctOwnrBillTo(glblCmpyCd, ownrAcctNum, billToAcctNum)) {
        if (NSAL0010CommonLogic.hasValues(ownrAcctNum, billToAcctNum) && !NSAL0010CommonLogic.checkAcctOwnrBillTo(glblCmpyCd, ownrAcctNum, billToAcctNum, svcMachTrxTpCd)) {
        // mod end 2019/08/07 QC#52198
            cMsg.ownrAcctNum_M1.setErrorInfo(1, NSAM0138E, new String[] {"Bill to Account", "Owner Account" });
            cMsg.billToAcctNum_M2.setErrorInfo(1, NSAM0138E, new String[] {"Bill to Account", "Owner Account" });
            return false;
        }
        // END 2016/05/26 T.Tomita [QC#8782, ADD]

        return true;
    }
    // END 2016/02/17 [QC#1986, ADD]

    private boolean checkActiveContr(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            return true;
        }

        if (cMsg.svcMachMstrStsCd_H3.getValue().equals(cMsg.svcMachMstrStsCd_H4.getValue())) {
            return true;
        }

        // START 2018/05/29 K.Kim [QC#15410(Sol#246),MOD]
        // DS_CONTR_DTLTMsg contrDtl = NSXC001001GetContr.getContrDtlByMachMstrPk(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue(), cMsg.slsDt.getValue());
        // if (contrDtl == null) {
        //     return true;
        // }
        List<DS_CONTR_DTLTMsg> contrDtlList = NSXC001001GetContr.getContrDtlByMachMstrPkList(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue(), cMsg.slsDt.getValue());
        if (contrDtlList.size() == 0) {
            return true;
        }
        // END 2018/05/29 K.Kim [QC#15410(Sol#246),MOD]

        // START 2018/05/29 K.Kim [QC#15410(Sol#246),MOD]
        for (DS_CONTR_DTLTMsg contrDtl:contrDtlList) {
            // START 2015/12/10 T.Tomita [QC1794, MOD]
            Map<String, Object> resultMap = NSAL0010Query.getInstance().getDsContrDtlCtrlSts(cMsg.glblCmpyCd.getValue(), contrDtl.dsContrDtlPk.getValue());
            if (resultMap != null) {
                String dsContrDtlCtrlSts = (String) resultMap.get("DS_CONTR_CTRL_STS_CD");
                String dsContrNum = (String) resultMap.get("DS_CONTR_NUM");
                if (DS_CONTR_CTRL_STS.ACTIVE.equals(dsContrDtlCtrlSts)) {
                    // START 2017/08/08 K.Kim [QC#18015, ADD]
                    String svcMachSts = cMsg.svcMachMstrStsCd_H3.getValue();
                    if (!SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachSts) && !SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachSts)
                            && !SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachSts)){
                    // END 2017/08/08 K.Kim [QC#18015, ADD]
                        cMsg.setMessageInfo(NSAM0381E, new String[]{"Contract#:" + dsContrNum });
                        return false;
                    }
                }
            }
            // END 2015/12/10 T.Tomita [QC1794, MOD]
        }
        // END 2018/05/29 K.Kim [QC#15410(Sol#246),MOD]
        return true;

    }

    private boolean checkOpenOrder(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            return true;
        }

        if (cMsg.svcMachMstrStsCd_H3.getValue().equals(cMsg.svcMachMstrStsCd_H4.getValue())) {
            return true;
        }

        String openCpoOrdNum = NSAL0010Query.getInstance().getOpenOrder(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue());

        if (ZYPCommonFunc.hasValue(openCpoOrdNum)) {

            /* 08/10/2016 CSAI Y.Imazu Add QC#12496 START */
            String svcIstlReqFlg = NSAL0010Query.getInstance().getSvcIstlReqFlg(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue());

            // Not Required Install for Revenue
            if (!ZYPCommonFunc.hasValue(svcIstlReqFlg) || !ZYPConstant.FLG_ON_Y.equals(svcIstlReqFlg)) {

                return true;
            }

            // Check Status
            if (chkStsForOpenOrd(cMsg.svcMachMstrStsCd_H3.getValue())) {

                return true;
            }

            cMsg.svcMachMstrStsCd_H3.setErrorInfo(1, NSAM0602E, new String[]{getStsDescForOpenOrd(cMsg) , "Order#:" + openCpoOrdNum });
            /* 08/10/2016 CSAI Y.Imazu Add QC#12496 END */

            /* 08/10/2016 CSAI Y.Imazu Del QC#12496 START */
//            // START 2015/12/10 T.Tomita [QC1794, MOD]
//            cMsg.setMessageInfo(NSAM0382E, new String[]{"Order#:" + openCpoOrdNum });
//            // END 2015/12/10 T.Tomita [QC1794, MOD]
            /* 08/10/2016 CSAI Y.Imazu Del QC#12496 END */

            return false;
        }

        return true;
    }

    /* 08/10/2016 CSAI Y.Imazu Add QC#12496 START */
    /**
     * Check Status for Open Order
     * @param svcMachMstrStsCd String
     * @return boolean true : OK, false : NG
     */
    private boolean chkStsForOpenOrd(String svcMachMstrStsCd) {

        if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd) || SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)) {

            return true;
        }

        return false;
    }

    /**
     * getStsDescForOpenOrd
     * @param cMsg NSAL0010CMsg
     * @return String
     */
    private String getStsDescForOpenOrd(NSAL0010CMsg cMsg) {

        String newSvcMachMstrStsCd = "";

        if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(cMsg.svcMachMstrStsCd_H4.getValue())) {

            newSvcMachMstrStsCd = SVC_MACH_MSTR_STS.INSTALLED;

        } else if (SVC_MACH_MSTR_STS.INSTALLED.equals(cMsg.svcMachMstrStsCd_H4.getValue())) {

            newSvcMachMstrStsCd = SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION;
        }

        for (int i = 0; i < cMsg.svcMachMstrStsCd_H1.length(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrStsCd_H1.no(i))) {

                continue;
            }

            if (cMsg.svcMachMstrStsCd_H1.no(i).getValue().equals(newSvcMachMstrStsCd)) {

                return cMsg.svcMachMstrStsDescTxt_H2.no(i).getValue();
            }
        }

        return "";
    }
    /* 08/10/2016 CSAI Y.Imazu Add QC#12496 END */

    // START 2016/02/16 [QC#2255, ADD]
    private boolean checkStatusRelation(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        String svcMachStsCd = cMsg.svcMachMstrStsCd_H3.getValue();
        String svcMachUsgStsCd = cMsg.svcMachUsgStsCd_H3.getValue();

        if (!ZYPCommonFunc.hasValue(svcMachStsCd) || !ZYPCommonFunc.hasValue(svcMachUsgStsCd)) {
            return true;
        }

        MACH_STS_VLD_MAPTMsg tMsg = NSAL0010CommonLogic.getMachStsVldMap(getGlobalCompanyCode(), svcMachStsCd, svcMachUsgStsCd);
        if (tMsg == null || ZYPConstant.FLG_OFF_N.equals(tMsg.stsVldFlg.getValue())) {
            cMsg.svcMachMstrStsCd_H3.setErrorInfo(1, NSZM0869E);
            cMsg.svcMachUsgStsCd_H3.setErrorInfo(1, NSZM0869E);
            return false;
        }
        return true;
    }
    // END 2016/02/16 [QC#2255, ADD]

    // START 2016/03/28 M.Gotou [QC#4888, ADD]
    private boolean checkRtlSWH(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        String svcMachMstrStsCd = cMsg.svcMachMstrStsCd_H3.getValue();
        // START 2016/05/16 T.Tomita [QC#4578, MOD]
        String curLocNum = cMsg.indCurLocNum_M3.getValue();
        // END 2016/05/16 T.Tomita [QC#4578, MOD]
        // START 2018/01/22 M.Kidokoro [QC#21975, ADD]
        String svcMachUsgStsCd = cMsg.svcMachUsgStsCd_H3.getValue();
        // END 2018/01/22 M.Kidokoro [QC#21975, ADD]

        if (!ZYPCommonFunc.hasValue(svcMachMstrStsCd) || !ZYPCommonFunc.hasValue(curLocNum)) {
            return true;
        }

        List<String> list = new ArrayList<String>();
        list.add(SVC_MACH_MSTR_STS.CREATED);
        list.add(SVC_MACH_MSTR_STS.REMOVED);
        list.add(SVC_MACH_MSTR_STS.TERMINATED);
        list.add(SVC_MACH_MSTR_STS.DUPLICATE);

        if (!list.contains(svcMachMstrStsCd)) {
            return true;
        }

        // START 2018/01/22 M.Kidokoro [QC#21975, ADD]
        if (SVC_MACH_MSTR_STS.TERMINATED.equals(svcMachMstrStsCd) && SVC_MACH_USG_STS.GONE.equals(svcMachUsgStsCd)) {
            return true;
        }
        // END 2018/01/22 M.Kidokoro [QC#21975, ADD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("invtyLocCd", curLocNum);
        ssmParam.put("slsDt", cMsg.slsDt.getValue());
        ssmParam.put("endDt", MAX_DT_VAL);

        BigDecimal count = NSAL0010Query.getInstance().countRtlSWH(ssmParam);
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            cMsg.setMessageInfo(NSAM0063E, new String[] {"Current Location#", "Retail Sub Warehouse Master" });
            return false;
        }
        return true;
    }
    // END 2016/03/28 M.Gotou [QC#4888, ADD]

    private int checkConfig(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        int firstErrIndex = -1;

        int mainUnitCnt = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0010_ASMsg configMsg = sMsg.A.no(i);
            if (SVC_MACH_TP.MACHINE.equals(configMsg.svcMachTpCd_A.getValue())) {
                mainUnitCnt++;
            }
        }

        // add start 2016/03/30 CSA Defect#6092
        // START 2019/11/28 K.Kitachi [QC#53162, DEL]
//        if (mainUnitCnt == 0 && sMsg.A.getValidCount() > 0) {
//            cMsg.setMessageInfo(NSAM0063E, new String[]{"Parent", "Machine Configration List"});
//            firstErrIndex = 0;
//            return firstErrIndex;
//        }
        // END 2019/11/28 K.Kitachi [QC#53162, DEL]
        // add end 2016/03/30 CSA Defect#6092

        if (mainUnitCnt > 1) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(0).svcMachRmvDt_A)) {
                cMsg.setMessageInfo(NSAM0377E);
            }
        }

        BigDecimal firstMachPk = sMsg.A.no(0).svcMachMstrPk_A1.getValue();
        for (int i = 1; i < sMsg.A.getValidCount(); i++) {
            NSAL0010_ASMsg configMsg = sMsg.A.no(i);
            // START 2015/11/16 T.Tomita [QC647, ADD]
            if (!ZYPCommonFunc.hasValue(configMsg.mdseCd_A)) {
                continue;
            }
            if (NSAL0010CommonLogic.isSame(firstMachPk, configMsg.svcMachMstrPk_A1.getValue())) {
                cMsg.setMessageInfo(NSAM0378E);
            }
            // END 2015/11/16 T.Tomita [QC647, ADD]
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0010_ASMsg configMsg = sMsg.A.no(i);
            // START 2015/11/16 T.Tomita [QC647, ADD]
            if (!ZYPCommonFunc.hasValue(configMsg.mdseCd_A)) {
                continue;
            }
            // END 2015/11/16 T.Tomita [QC647, ADD]
            if (ZYPCommonFunc.hasValue(configMsg.svcMachRmvDt_A)) {
                if (configMsg.svcMachRmvDt_A.getValue().compareTo(cMsg.slsDt.getValue()) > 0) {
                    configMsg.svcMachRmvDt_A.setErrorInfo(1, NSAM0379E, new String[]{"Remove Date", "Remove Date"});
                    if (firstErrIndex < 0) {
                        firstErrIndex = i;
                        break;
                    }
                }
            }

            if (!ZYPCommonFunc.hasValue(configMsg.svcMachRmvDt_A) && !ZYPCommonFunc.hasValue(configMsg.svcConfigMstrPk_A)) {
                SVC_MACH_MSTRTMsg machMstr = NSAL0010CommonLogic.getSvcMachMstr(cMsg.glblCmpyCd.getValue(), configMsg.svcMachMstrPk_A1.getValue());
                // START 2015/11/16 T.Tomita [QC647, MOD]
                if (machMstr != null && ZYPCommonFunc.hasValue(machMstr.svcConfigMstrPk)) {
                    configMsg.svcMachRmvDt_A.setErrorInfo(1, NSAM0380E, new String[]{configMsg.svcMachMstrPk_A1.getValue().toString()});
                    if (firstErrIndex < 0) {
                        firstErrIndex = i;
                        break;
                    }
                }
                // END 2015/11/16 T.Tomita [QC647, MOD]
            }

        }

        return firstErrIndex;

    }

    private boolean checkTimeStamp(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_H1)) {
            return true;
        }

        SVC_MACH_MSTRTMsg inParam = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcMachMstrPk, cMsg.svcMachMstrPk_H1);

        inParam = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inParam);
        if (inParam == null) {
            cMsg.setMessageInfo("ZZOM0013E");
            return false;
        }

        // Anyone update
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_H1.getValue(), cMsg.ezUpTimeZone_H1.getValue()
                , inParam.ezUpTime.getValue(), inParam.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo("ZZSM4104E");
            return false;
        }

        return true;
    }

    // add start 2016/04/19 CSA Defect#6218
    private void setErrForCtacPnt(NSAL0010_CSMsg ctacMsg, String msgId, String[] items) {
        if (items != null) {
            ctacMsg.dsCtacPntTpCd_CS.setErrorInfo(1, msgId, items);
            ctacMsg.dsCtacPntValTxt_C.setErrorInfo(1, msgId, items);
            ctacMsg.dsCtacPsnExtnNum_C.setErrorInfo(1, msgId, items);
        } else {
            ctacMsg.dsCtacPntTpCd_CS.setErrorInfo(1, msgId);
            ctacMsg.dsCtacPntValTxt_C.setErrorInfo(1, msgId);
            ctacMsg.dsCtacPsnExtnNum_C.setErrorInfo(1, msgId);
        }
    }
    // add end 2016/04/19 CSA Defect#6218

    // add start 2016/04/25 CSA Defect#6672
    private Map<String, Object> createSsmParamIsGetEffDt(NSAL0010CMsg cMsg, BigDecimal ctacPsnPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("ctacPsnPk", ctacPsnPk);
        ssmParam.put("dsAcctNum", cMsg.curLocAcctNum_M3.getValue());
        // add start 2016/06/01 CSA Defect#7794
        ssmParam.put("locNum", cMsg.indCurLocNum_M3.getValue());
        // add end 2016/06/01 CSA Defect#7794
        ssmParam.put("slsDt", cMsg.slsDt.getValue());
        ssmParam.put("endDt", MAX_DT_VAL);
        return ssmParam;
    }
    // add end 2016/04/25 CSA Defect#6672

    // START 2016/06/29 T.Tomita [QC#10489, ADD]
    private BigDecimal getCtacPsnPk(NSAL0010CMsg cMsg, NSAL0010_CSMsg ccMsg) {
        CTAC_PSNTMsg tMsg = new CTAC_PSNTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("ctacPsnFirstNm01", ccMsg.ctacPsnFirstNm_C.getValue());
        tMsg.setConditionValue("ctacPsnLastNm01", ccMsg.ctacPsnLastNm_C.getValue());
        CTAC_PSNTMsgArray tMsgArray = (CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0).ctacPsnPk.getValue();
    }
    // END 2016/06/29 T.Tomita [QC#10489, ADD]

    // START 2016/07/22 T.Tomita [QC#11161, ADD]
    private boolean validation_AddlAttrb(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        // START 2020/04/24 K.Sakurai [QC#56672, MOD]
        if (SVC_MACH_MSTR_STS.INSTALLED.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(cMsg.svcMachMstrStsCd_H3.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(cMsg.svcMachMstrStsCd_H3.getValue())) {
            Boolean errorFlg = false;
            if (!ZYPCommonFunc.hasValue(cMsg.finBrCd_D)) {
                cMsg.finBrCd_D.setErrorInfo(1, NSAM0681E,  new String[]{"Financial Branch CD"});
                errorFlg = true;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.svcBrCd_D)) {
                cMsg.svcBrCd_D.setErrorInfo(1, NSAM0681E,  new String[]{"Service Branch CD"});
                errorFlg = true;
            }
            if (errorFlg) {
                return false;
            }
        } else {
            if (!ZYPCommonFunc.hasValue(cMsg.svcBrCd_D)) {
                return true;
            }
        }
        // END 2020/04/24 K.Sakurai [QC#56672, MOD]
        // START 2023/10/06 K.Ishizuka [QC#54186, MOD]
//        if (!ZYPCommonFunc.hasValue(cMsg.svcByLineBizTpCd_DS)) {
//            cMsg.svcByLineBizTpCd_DS.setErrorInfo(1, ZZM9000E, new String[] {"Serviced By BU" });
//            return false;
//        }
        if (!ZYPCommonFunc.hasValue(cMsg.istlBySvcPrvdPtyCd_DS)) {
            cMsg.istlBySvcPrvdPtyCd_DS.setErrorInfo(1, ZZM9000E, new String[] {"To Be Installed By" });
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.svcBySvcPrvdPtyCd_DS)){
            cMsg.svcBySvcPrvdPtyCd_DS.setErrorInfo(1, ZZM9000E, new String[] {"Service Provided By" });
            return false;
        }
        // END 2023/10/06 K.Ishizuka [QC#54186, MOD]
        
        // START 2016/07/29 T.Tomita [QC#12638, MOD]
//        Map<String, Object> ssmParam = createSsmParamIsCountSvcBr(cMsg);
//        BigDecimal countSvcBrPostCdFull = NSAL0010Query.getInstance().countSvcBrPostCdFull(ssmParam);
//        BigDecimal countSvcBrPostCdShort = NSAL0010Query.getInstance().countSvcBrPostCdShort(ssmParam);
        NSXC002001GetBrCdBean brCdBean = getBrCd(cMsg);
//        if (countSvcBrPostCdFull.compareTo(BigDecimal.ZERO) == 0 && countSvcBrPostCdShort.compareTo(BigDecimal.ZERO) == 0) {
        if (!cMsg.svcBrCd_D.getValue().equals(brCdBean.getFldSvcBrCd())) {
            cMsg.svcBrCd_D.setErrorInfo(1, NSAM0433E, new String[] {"Branch Code", "Branch Code of Serviced By BU and Current Location#" });
            return false;
        }
        // END 2016/07/29 T.Tomita [QC#12638, MOD]
        return true;

    }

    // START 2016/07/29 T.Tomita [QC#12638, DEL]
//    private Map<String, Object> createSsmParamIsCountSvcBr(NSAL0010CMsg cMsg) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
//        ssmParam.put("svcLineBizCd", cMsg.svcByLineBizTpCd_DS.getValue());
//        ssmParam.put("svcBrCd", cMsg.svcBrCd_D.getValue());
//        ssmParam.put("locNum", cMsg.indCurLocNum_M3.getValue());
//        ssmParam.put("postCdLength", POST_CD_LENGTH);
//        return ssmParam;
//    }
    // END 2016/07/29 T.Tomita [QC#12638, DEL]
    // END 2016/07/22 T.Tomita [QC#11161, ADD]

    // START 2016/07/29 T.Tomita [QC#12638, ADD]
    private NSXC002001GetBrCdBean getBrCd(NSAL0010CMsg cMsg) {
        String postCd = null;
        SHIP_TO_CUSTTMsg prmTMsg = new SHIP_TO_CUSTTMsg();
        prmTMsg.setSQLID("048");
        prmTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        prmTMsg.setConditionValue("locNum01", cMsg.indCurLocNum_M3.getValue());
        SHIP_TO_CUSTTMsgArray resultList = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
        if (resultList.length() > 0) {
            postCd = resultList.no(0).postCd.getValue();
        }

        NSXC002001GetBrCdBean brCdBean = new NSXC002001GetBrCdBean();
        brCdBean.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        brCdBean.setPostCd(postCd);
        // START 2023/10/06 K.Ishizuka [QC#54186, MOD]
//      brCdBean.setSvcLineBizCd(cMsg.svcByLineBizTpCd_DS.getValue());
        S21SsmEZDResult result;
        if(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(cMsg.svcMachMstrStsCd_H3.getValue())){
            result = NSAL0010Query.getInstance().getSvcPrvdPtyFromSetSLBCd(cMsg, cMsg.istlBySvcPrvdPtyCd_DS.getValue(), this.getGlobalCompanyCode());
        }else{
            result = NSAL0010Query.getInstance().getSvcPrvdPtyFromSetSLBCd(cMsg, cMsg.svcBySvcPrvdPtyCd_DS.getValue(), this.getGlobalCompanyCode());
        }
        Map<String, Object> map = (Map<String, Object>) result.getResultObject();
        String str = (String) map.get("LINE_BIZ_TP_CD");
        brCdBean.setSvcLineBizCd(str);
        // END 2023/10/06 K.Ishizuka [QC#54186, MOD]
        brCdBean.setSalesDate(cMsg.slsDt.getValue());
        NSXC002001GetBrCd.getBrCd(brCdBean);
        return brCdBean;
    }
    // END 2016/07/29 T.Tomita [QC#12638, ADD]

    // START 2016/08/08 T.Tomita [QC#7794, ADD]
    private List<Integer> getDuplicateIdx(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, int index) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("svcMachMstrPk", cMsg.svcMachMstrPk_H1.getValue());

        List<Integer> dupIdxList = new ArrayList<Integer>();

        List<Map<String, Object>> mapList = NSAL0010Query.getInstance().getCtacInfo(queryMap);
        for (int i = 0; i < mapList.size(); i++) {
            if (isEqualsForBigDecimal((BigDecimal) mapList.get(i).get("SVC_MACH_CTAC_PSN_PK"), sMsg.C.no(index).svcMachCtacPsnPk_C.getValue())) {
                continue;
            }
            // Last Name
            if (!isEqualsForString((String) mapList.get(i).get("CTAC_PSN_LAST_NM"), sMsg.C.no(index).ctacPsnLastNm_C.getValue())) {
                continue;
            }
            // First Name
            if (!isEqualsForString((String) mapList.get(i).get("CTAC_PSN_FIRST_NM"), sMsg.C.no(index).ctacPsnFirstNm_C.getValue())) {
                continue;
            }
            // Contact Point
            if (!isEqualsForString((String) mapList.get(i).get("DS_CTAC_PNT_TP_CD"), sMsg.C.no(index).dsCtacPntTpCd_CS.getValue())) {
                continue;
            }
            // Contact Value
            if (!isEqualsForString((String) mapList.get(i).get("DS_CTAC_PNT_VAL_TXT"), sMsg.C.no(index).dsCtacPntValTxt_C.getValue())) {
                continue;
            }
            // Ext
            if (!isEqualsForString((String) mapList.get(i).get("DS_CTAC_PSN_EXTN_NUM"), sMsg.C.no(index).dsCtacPsnExtnNum_C.getValue())) {
                continue;
            }
            // IB Contact Type
            if (!isEqualsForString((String) mapList.get(i).get("SVC_CTAC_TP_CD"), sMsg.C.no(index).svcCtacTpCd_CS.getValue())) {
                continue;
            }
            // Start Date, End Date
            if (!isDuplicateDate((String) mapList.get(i).get("EFF_FROM_DT"), (String) mapList.get(i).get("EFF_THRU_DT"), sMsg.C.no(index).effFromDt_C.getValue(), sMsg.C.no(index).effThruDt_C.getValue())) {
                continue;
            }
            dupIdxList.add(index);
            break;
        }

        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (i == index) {
                continue;
            }
            // Last Name
            if (!isEqualsForString(sMsg.C.no(i).ctacPsnLastNm_C.getValue(), sMsg.C.no(index).ctacPsnLastNm_C.getValue())) {
                continue;
            }
            // First Name
            if (!isEqualsForString(sMsg.C.no(i).ctacPsnFirstNm_C.getValue(), sMsg.C.no(index).ctacPsnFirstNm_C.getValue())) {
                continue;
            }
            // Contact Point
            if (!isEqualsForString(sMsg.C.no(i).dsCtacPntTpCd_CS.getValue(), sMsg.C.no(index).dsCtacPntTpCd_CS.getValue())) {
                continue;
            }
            // Contact Value
            if (!isEqualsForString(sMsg.C.no(i).dsCtacPntValTxt_C.getValue(), sMsg.C.no(index).dsCtacPntValTxt_C.getValue())) {
                continue;
            }
            // Ext
            if (!isEqualsForString(sMsg.C.no(i).dsCtacPsnExtnNum_C.getValue(), sMsg.C.no(index).dsCtacPsnExtnNum_C.getValue())) {
                continue;
            }
            // IB Contact Type
            if (!isEqualsForString(sMsg.C.no(i).svcCtacTpCd_CS.getValue(), sMsg.C.no(index).svcCtacTpCd_CS.getValue())) {
                continue;
            }
            // Start Date, End Date
            if (!isDuplicateDate(sMsg.C.no(i).effFromDt_C.getValue(), sMsg.C.no(i).effThruDt_C.getValue(), sMsg.C.no(index).effFromDt_C.getValue(), sMsg.C.no(index).effThruDt_C.getValue())) {
                continue;
            }
            if (!dupIdxList.contains(i)) {
                dupIdxList.add(i);
            }
        }

        return dupIdxList;
    }

    private boolean isEqualsForBigDecimal(BigDecimal val1, BigDecimal val2) {
        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (hasValue(val1) && !hasValue(val2)) {
            return false;
        }
        if (!hasValue(val1) && hasValue(val2)) {
            return false;
        }
        if (val1.compareTo(val2) == 0) {
            return true;
        }
        return false;
    }

    private boolean isEqualsForString(String val1, String val2) {
        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (hasValue(val1) && !hasValue(val2)) {
            return false;
        }
        if (!hasValue(val1) && hasValue(val2)) {
            return false;
        }
        if (val1.equals(val2)) {
            return true;
        }
        return false;
    }

    private boolean isDuplicateDate(String fromDt1, String thruDt1, String fromDt2, String thruDt2) {
        if (!hasValue(thruDt1)) {
            thruDt1 = MAX_DT_VAL;
        }
        if (!hasValue(thruDt2)) {
            thruDt2 = MAX_DT_VAL;
        }
        if (ZYPDateUtil.compare(fromDt1, fromDt2) <= 0 && ZYPDateUtil.compare(fromDt2, thruDt1) <= 0) {
            return true;
        }
        if (ZYPDateUtil.compare(fromDt1, thruDt2) <= 0 && ZYPDateUtil.compare(thruDt2, thruDt1) <= 0) {
            return true;
        }
        if (ZYPDateUtil.compare(fromDt1, fromDt2) > 0 && ZYPDateUtil.compare(thruDt1, thruDt2) < 0) {
            return true;
        }
        return false;
    }
    // END 2016/08/08 T.Tomita [QC#7794, ADD]

    // add start 2016/08/30 CSA Defect#13532
    private SVC_MACH_MSTRTMsg getSvcMachMstrTMsg(EZDCStringItem glblCmpyCd, EZDCBigDecimalItem svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = NSAL0010CommonLogic.getSvcMachMstr(glblCmpyCd.getValue(), svcMachMstrPk.getValue());
        if (tMsg == null) {
            tMsg = new SVC_MACH_MSTRTMsg();
        }
        return tMsg;
    }

    private boolean isUpdateAccIB(SVC_MACH_MSTRTMsg bfMachTMsg, SVC_MACH_MSTRTMsg afMachTMsg) {
        if (!hasValue(afMachTMsg.svcConfigMstrPk) || !hasValue(afMachTMsg.svcMachTpCd) || !SVC_MACH_TP.MACHINE.equals(afMachTMsg.svcMachTpCd.getValue())) {
            return false;
        }

        if (!isDefMachItem(bfMachTMsg, afMachTMsg)) {
            return false;
        }
        return true;
    }

    private boolean isDefMachItem(SVC_MACH_MSTRTMsg bfMachTMsg, SVC_MACH_MSTRTMsg afMachTMsg) {
        // OWNR_ACCT_NUM
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.ownrAcctNum, afMachTMsg.ownrAcctNum)) {
            return true;
        }
        // OWNR_LOC_NUM
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.ownrLocNum, afMachTMsg.ownrLocNum)) {
            return true;
        }
        // BILL_TO_ACCT_NUM
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.billToAcctNum, afMachTMsg.billToAcctNum)) {
            return true;
        }
        // BILL_TO_LOC_NUM
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.billToLocNum, afMachTMsg.billToLocNum)) {
            return true;
        }
        // CUR_LOC_ACCT_NUM
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.curLocAcctNum, afMachTMsg.curLocAcctNum)) {
            return true;
        }
        // CUR_LOC_NUM
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.curLocNum, afMachTMsg.curLocNum)) {
            return true;
        }
        // IND_BILL_TO_LOC_NUM
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.indBillToLocNum, afMachTMsg.indBillToLocNum)) {
            return true;
        }
        // IND_CUR_LOC_NUM
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.indCurLocNum, afMachTMsg.indCurLocNum)) {
            return true;
        }
        // START 2018/05/08 K.Kitachi [QC#23604, ADD]
        // CTRL_FLD_TXT_01
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.ctrlFldTxt_01, afMachTMsg.ctrlFldTxt_01)) {
            return true;
        }
        // CTRL_FLD_TXT_02
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.ctrlFldTxt_02, afMachTMsg.ctrlFldTxt_02)) {
            return true;
        }
        // CTRL_FLD_TXT_03
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.ctrlFldTxt_03, afMachTMsg.ctrlFldTxt_03)) {
            return true;
        }
        // CTRL_FLD_TXT_04
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.ctrlFldTxt_04, afMachTMsg.ctrlFldTxt_04)) {
            return true;
        }
        // CTRL_FLD_TXT_05
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.ctrlFldTxt_05, afMachTMsg.ctrlFldTxt_05)) {
            return true;
        }
        // CTRL_FLD_TXT_06
        if (!NSAL0010CommonLogic.isSame(bfMachTMsg.ctrlFldTxt_06, afMachTMsg.ctrlFldTxt_06)) {
            return true;
        }
        // END 2018/05/08 K.Kitachi [QC#23604, ADD]
        return false;
    }

    // START 2018/05/08 K.Kitachi [QC#23604, MOD]
//    private void updateAccIB(SVC_MACH_MSTRTMsg svcMachMstrTMsg, String slsDt) {
    private void updateAccIB(SVC_MACH_MSTRTMsg svcMachMstrTMsg, String slsDt, String modeCd) {
    // END 2018/05/08 K.Kitachi [QC#23604, MOD]
        SVC_MACH_MSTRTMsgArray accList = NSAL0010CommonLogic.getUpdateAccMachList(svcMachMstrTMsg);

        // START 2016/09/06 T.Tomita [QC#13532, ADD]
        this.svcMachMstrStsCustGrp = getSvcMachMstrStsGrp(svcMachMstrTMsg.glblCmpyCd.getValue(), CONST_KEY_MACH_STS_CUST_GRP);
        this.svcMachMstrStsWhGrp = getSvcMachMstrStsGrp(svcMachMstrTMsg.glblCmpyCd.getValue(), CONST_KEY_MACH_STS_WH_GRP);
        // END 2016/09/06 T.Tomita [QC#13532, ADD]

        NSZC001001PMsg pMsg;
        NSZC001001 api = new NSZC001001();
        for (int i = 0; i < accList.getValidCount(); i++) {
            if (!isSameAccToMach(svcMachMstrTMsg, accList.no(i))) {
                continue;
            }

            pMsg = new NSZC001001PMsg();
            updateTMsgItemForAcc(svcMachMstrTMsg, accList.no(i));
            // START 2018/05/08 K.Kitachi [QC#23604, MOD]
//            setParamUpdateForAcc(pMsg, accList.no(i), slsDt);
            if (hasValue(modeCd) && modeCd.equals(ProcessMode.UPDATE_ATTRIBUTE.code)) {
                setParamUpdAttrModeForAcc(pMsg, accList.no(i), slsDt);
            } else {
                setParamUpdateForAcc(pMsg, accList.no(i), slsDt);
            }
            // END 2018/05/08 K.Kitachi [QC#23604, MOD]
            // TODO QC#17822 add log
            S21InfoLogOutput.println(pMsg.toString());
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        }
    }

    private boolean isSameAccToMach(SVC_MACH_MSTRTMsg machTMsg, SVC_MACH_MSTRTMsg accTMsg) {
        // START 2016/09/06 T.Tomita [QC#13532, MOD]
        String machSvcMachMstrStsCd = machTMsg.svcMachMstrStsCd.getValue();
        String accSvcMachMstrStsCd = accTMsg.svcMachMstrStsCd.getValue();
        if (this.svcMachMstrStsCustGrp.contains(machSvcMachMstrStsCd) && this.svcMachMstrStsCustGrp.contains(accSvcMachMstrStsCd)) {
            return true;
        }
        if (this.svcMachMstrStsWhGrp.contains(machSvcMachMstrStsCd) && this.svcMachMstrStsWhGrp.contains(accSvcMachMstrStsCd)) {
            return true;
        }
        return false;
        // END 2016/09/06 T.Tomita [QC#13532, MOD]
    }

    private void updateTMsgItemForAcc(SVC_MACH_MSTRTMsg machTMsg, SVC_MACH_MSTRTMsg accTMsg) {
        setValue(accTMsg.ownrAcctNum, machTMsg.ownrAcctNum);
        setValue(accTMsg.ownrLocNum, machTMsg.ownrLocNum);
        setValue(accTMsg.billToAcctNum, machTMsg.billToAcctNum);
        setValue(accTMsg.billToLocNum, machTMsg.billToLocNum);
        setValue(accTMsg.curLocAcctNum, machTMsg.curLocAcctNum);
        setValue(accTMsg.curLocNum, machTMsg.curLocNum);
        setValue(accTMsg.indBillToLocNum, machTMsg.indBillToLocNum);
        setValue(accTMsg.indCurLocNum, machTMsg.indCurLocNum);
        // START 2018/05/08 K.Kitachi [QC#23604, ADD]
        setValue(accTMsg.ctrlFldTxt_01, machTMsg.ctrlFldTxt_01);
        setValue(accTMsg.ctrlFldTxt_02, machTMsg.ctrlFldTxt_02);
        setValue(accTMsg.ctrlFldTxt_03, machTMsg.ctrlFldTxt_03);
        setValue(accTMsg.ctrlFldTxt_04, machTMsg.ctrlFldTxt_04);
        setValue(accTMsg.ctrlFldTxt_05, machTMsg.ctrlFldTxt_05);
        setValue(accTMsg.ctrlFldTxt_06, machTMsg.ctrlFldTxt_06);
        // END 2018/05/08 K.Kitachi [QC#23604, ADD]
    }

    private void setParamUpdateForAcc(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String slsDt) {
        setValue(pMsg.glblCmpyCd, svcMachMstrTMsg.glblCmpyCd);
        setValue(pMsg.slsDt, slsDt);
        if (SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.DUPLICATE.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
            // mode upd_wh
            setValue(pMsg.xxModeCd, ProcessMode.UPDATE_WAREHOUSE.code);
        } else if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())
                || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
            // mode upd-mif
            setValue(pMsg.xxModeCd, ProcessMode.UPDATE_MACHINE_IN_FIELD.code);
        } else {
            // other
            return;
        }
        setValue(pMsg.svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
        setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(pMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(pMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        setValue(pMsg.custMachCtrlNum, svcMachMstrTMsg.custMachCtrlNum);
        setValue(pMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd);
        setValue(pMsg.stkStsCd, svcMachMstrTMsg.stkStsCd);
        setValue(pMsg.svcMachMstrLocStsCd, svcMachMstrTMsg.svcMachMstrLocStsCd);
        // START 2017/03/22 K.Kitachi [QC#15679, DEL]
//        setValue(pMsg.oldSerNum, svcMachMstrTMsg.serNum);
        // END 2017/03/22 K.Kitachi [QC#15679, DEL]
        setValue(pMsg.istlDt, svcMachMstrTMsg.istlDt);
        setValue(pMsg.effFromDt, svcMachMstrTMsg.effFromDt);
        setValue(pMsg.effThruDt, svcMachMstrTMsg.effThruDt);
        setValue(pMsg.svcMachUsgStsCd, svcMachMstrTMsg.svcMachUsgStsCd);
        setValue(pMsg.prntSerNum, svcMachMstrTMsg.prntSerNum);
        setValue(pMsg.svcMachTrxTpCd, svcMachMstrTMsg.svcMachTrxTpCd);
        setValue(pMsg.svcMachQty, svcMachMstrTMsg.svcMachQty);
        setValue(pMsg.svcMachTpCd, svcMachMstrTMsg.svcMachTpCd);
        setValue(pMsg.ownrAcctNum, svcMachMstrTMsg.ownrAcctNum);
        setValue(pMsg.billToAcctNum, svcMachMstrTMsg.billToAcctNum);
        setValue(pMsg.billToLocNum, svcMachMstrTMsg.billToLocNum);
        setValue(pMsg.curLocAcctNum, svcMachMstrTMsg.curLocAcctNum);
        setValue(pMsg.curLocNum, svcMachMstrTMsg.curLocNum);
        // START 2018/05/08 K.Kitachi [QC#23604, ADD]
        setValue(pMsg.ctrlFldTxt_01, svcMachMstrTMsg.ctrlFldTxt_01);
        setValue(pMsg.ctrlFldTxt_02, svcMachMstrTMsg.ctrlFldTxt_02);
        setValue(pMsg.ctrlFldTxt_03, svcMachMstrTMsg.ctrlFldTxt_03);
        setValue(pMsg.ctrlFldTxt_04, svcMachMstrTMsg.ctrlFldTxt_04);
        setValue(pMsg.ctrlFldTxt_05, svcMachMstrTMsg.ctrlFldTxt_05);
        setValue(pMsg.ctrlFldTxt_06, svcMachMstrTMsg.ctrlFldTxt_06);
        // END 2018/05/08 K.Kitachi [QC#23604, ADD]
        // START 2017/02/14 K.Kitachi [QC#17309, ADD]
        setValue(pMsg.manCratFlg, ZYPConstant.FLG_ON_Y);
        // END 2017/02/14 K.Kitachi [QC#17309, ADD]
    }
    // add end 2016/08/30 CSA Defect#13532

    // START 2018/05/08 K.Kitachi [QC#23604, ADD]
    private void setParamUpdAttrModeForAcc(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String slsDt) {
        setValue(pMsg.glblCmpyCd, svcMachMstrTMsg.glblCmpyCd);
        setValue(pMsg.slsDt, slsDt);
        setValue(pMsg.xxModeCd, ProcessMode.UPDATE_ATTRIBUTE.code);
        setValue(pMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(pMsg.prfTechCd, svcMachMstrTMsg.prfTechCd);
        setValue(pMsg.stkStsCd, svcMachMstrTMsg.stkStsCd);
        setValue(pMsg.custIssPoNum, svcMachMstrTMsg.custIssPoNum);
        setValue(pMsg.ctrlFldTxt_01, svcMachMstrTMsg.ctrlFldTxt_01);
        setValue(pMsg.ctrlFldTxt_02, svcMachMstrTMsg.ctrlFldTxt_02);
        setValue(pMsg.ctrlFldTxt_03, svcMachMstrTMsg.ctrlFldTxt_03);
        setValue(pMsg.ctrlFldTxt_04, svcMachMstrTMsg.ctrlFldTxt_04);
        setValue(pMsg.ctrlFldTxt_05, svcMachMstrTMsg.ctrlFldTxt_05);
        setValue(pMsg.ctrlFldTxt_06, svcMachMstrTMsg.ctrlFldTxt_06);
        setValue(pMsg.prcContrNum, svcMachMstrTMsg.prcContrNum);
        setValue(pMsg.corpAdvtgStsCd, svcMachMstrTMsg.corpAdvtgStsCd);
        setValue(pMsg.dsPoExprDt, svcMachMstrTMsg.dsPoExprDt);
        setValue(pMsg.hardDriveRmvInclFlg, svcMachMstrTMsg.hardDriveRmvInclFlg);
        setValue(pMsg.hardDriveEraseInclFlg, svcMachMstrTMsg.hardDriveEraseInclFlg);
        setValue(pMsg.leaseRtrnFeeInclFlg, svcMachMstrTMsg.leaseRtrnFeeInclFlg);
        setValue(pMsg.dsKeyAcctFlg, svcMachMstrTMsg.dsKeyAcctFlg);
        setValue(pMsg.svcNtwkConnStsCd, svcMachMstrTMsg.svcNtwkConnStsCd);
        setValue(pMsg.sldByLineBizTpCd, svcMachMstrTMsg.sldByLineBizTpCd);
        setValue(pMsg.svcLicCnt, svcMachMstrTMsg.svcLicCnt);
        setValue(pMsg.reqTechCd, svcMachMstrTMsg.reqTechCd);
        setValue(pMsg.prfTechCd, svcMachMstrTMsg.prfTechCd);
        setValue(pMsg.fldSvcBrCd, svcMachMstrTMsg.fldSvcBrCd);
        //START 2023/10/06 K.Ishizuka [QC#54186, MOD]
//      setValue(pMsg.svcByLineBizTpCd, svcMachMstrTMsg.svcByLineBizTpCd);
        setValue(pMsg.istlBySvcPrvdPtyCd, svcMachMstrTMsg.istlBySvcPrvdPtyCd);
        setValue(pMsg.svcBySvcPrvdPtyCd, svcMachMstrTMsg.svcBySvcPrvdPtyCd);
        setValue(pMsg.svcMachMstrStsCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
        //END 2023/10/06 K.Ishizuka [QC#54186, MOD]
        setValue(pMsg.finBrCd, svcMachMstrTMsg.finBrCd);
        setValue(pMsg.bizHrsSunFromTm, svcMachMstrTMsg.bizHrsSunFromTm);
        setValue(pMsg.bizHrsSunToTm, svcMachMstrTMsg.bizHrsSunToTm);
        setValue(pMsg.bizHrsMonFriFromTm, svcMachMstrTMsg.bizHrsMonFriFromTm);
        setValue(pMsg.bizHrsMonFriToTm, svcMachMstrTMsg.bizHrsMonFriToTm);
        setValue(pMsg.bizHrsSatFromTm, svcMachMstrTMsg.bizHrsSatFromTm);
        setValue(pMsg.bizHrsSatToTm, svcMachMstrTMsg.bizHrsSatToTm);
        setValue(pMsg.enetCmntTxt_01, svcMachMstrTMsg.enetCmntTxt_01);
        setValue(pMsg.enetCmntTxt_02, svcMachMstrTMsg.enetCmntTxt_02);
        setValue(pMsg.enetPlotFlg, svcMachMstrTMsg.enetPlotFlg);
    }
    // END 2018/05/08 K.Kitachi [QC#23604, ADD]

    // START 2016/09/06 T.Tomita [QC#13532, ADD]
    private List<String> getSvcMachMstrStsGrp(String glblCmpyCd, String constKey) {
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(constKey, glblCmpyCd);
        if (!hasValue(constVal)) {
            return new ArrayList<String>();
        }
        String[] constValList = constVal.split(DELIMITER);
        return Arrays.asList(constValList);
    }
    // END 2016/09/06 T.Tomita [QC#13532, ADD]

// START 2016/11/14 N.Arai [QC#15829, MOD]
// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//    private boolean insertSvcMachMstrTrk(NSAL0010CMsg cMsg, Map<String, String> beforMap, SVC_MACH_MSTRTMsg newTMsg) {

//        String glblCmpyCd = this.getGlobalCompanyCode();
//        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
//        String userId = cMsg.getUserID();
//        BigDecimal svcMachMstrPk = cMsg.svcMachMstrPk_H1.getValue();

//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, LOC_NM, beforMap.get(LOC_NM), newTMsg.locNm.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, ADDL_LOC_NM, beforMap.get(ADDL_LOC_NM), newTMsg.addlLocNm.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, CTRY_CD, beforMap.get(CTRY_CD), newTMsg.ctryCd.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, FIRST_LINE_ADDR, beforMap.get(FIRST_LINE_ADDR), newTMsg.firstLineAddr.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, SCD_LINE_ADDR, beforMap.get(SCD_LINE_ADDR), newTMsg.scdLineAddr.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, THIRD_LINE_ADDR, beforMap.get(THIRD_LINE_ADDR), newTMsg.thirdLineAddr.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, FRTH_LINE_ADDR, beforMap.get(FRTH_LINE_ADDR), newTMsg.frthLineAddr.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, SVC_MACH_FL_NM, beforMap.get(SVC_MACH_FL_NM), newTMsg.svcMachFlNm.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, POST_CD, beforMap.get(POST_CD), newTMsg.postCd.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, CTY_ADDR, beforMap.get(CTY_ADDR), newTMsg.ctyAddr.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, ST_CD, beforMap.get(ST_CD), newTMsg.stCd.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, CNTY_NM, beforMap.get(CNTY_NM), newTMsg.cntyNm.getValue())) {
//            return false;
//        }
//        if (!setSvcMachMstrTrk(glblCmpyCd, slsDt, userId, svcMachMstrPk, PROV_NM, beforMap.get(PROV_NM), newTMsg.provNm.getValue())) {
//            return false;
//        }
//        return true;

//    }
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]

// START 2023/07/10 Y.Nagasawa [QC#61524, DEL]
//    private boolean setSvcMachMstrTrk(String glblCmpyCd, String slsDt, String userId, BigDecimal svcMachMstrPk, String updFld, String oldVal, String newVal) {

//        if (!ZYPCommonFunc.hasValue(oldVal) && !ZYPCommonFunc.hasValue(newVal)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(oldVal) && newVal.equals(oldVal)) {
//            return true;
//        }
//        if (ZYPCommonFunc.hasValue(newVal) && oldVal.equals(newVal)) {
//            return true;
//        }

//        SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();

//        setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        setValue(tMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
//        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
//        setValue(tMsg.trxRgtnDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
//        setValue(tMsg.updFldTxt, updFld);
//        setValue(tMsg.oldValTxt, oldVal);
//        setValue(tMsg.newValTxt, newVal);
//        setValue(tMsg.updUsrId, userId);
//        setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
//        EZDTBLAccessor.create(tMsg);
//        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//            return false;
//        }
//        return true;
//    }
// END 2023/07/10 Y.Nagasawa [QC#61524, DEL]
// END 2016/11/14 N.Arai [QC#15829, MOD]

    // START 2018/05/28 K.Kitachi [QC#19932, ADD]
    private boolean checkWarehouse(NSAL0010CMsg cMsg) {
        // START 2019/03/25 K.Kitachi [QC#30749, ADD]
        MDSETMsg mdse = NSAL0010CommonLogic.getMdse(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd_H1.getValue());
        if (mdse == null) {
            cMsg.mdseCd_H1.setErrorInfo(1, NSAM0063E, new String[] {"Mdse Code", "Mdse Master" });
            return false;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(mdse.invtyCtrlFlg.getValue())) {
            return true;
        }
        // END 2019/03/25 K.Kitachi [QC#30749, ADD]
        if (!hasValue(cMsg.svcMachMstrPk_H1)) {
            if (!insertWarehouseValidation(cMsg)) {
                return false;
            }
        }
        if (hasValue(cMsg.svcMachMstrPk_H1)) {
            if (!updateWarehouseValidation(cMsg)) {
                return false;
            }
        }
        return true;
    }

    private boolean isWarehouse(String glblCmpyCd, String slsDt, String invtyLocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("invtyLocCd", invtyLocCd);
        ssmParam.put("endDt", MAX_DT_VAL);

        BigDecimal count = NSAL0010Query.getInstance().countRtlSWH(ssmParam);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    private boolean insertWarehouseValidation(NSAL0010CMsg cMsg) {

        // START 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
        int countFlg = NSAL0010Query.getInstance().countLocStsMndFlgByStsCd(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrStsCd_H3.getValue());
        if (countFlg == 0) {
            // No need to check warehouse
            return true;
        }
        // END 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]

        if (!isWarehouse(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.indCurLocNum_M3.getValue())) {
            return true;
        }
        BigDecimal svcMachMstrQty = getSvcMachMstrQty(cMsg.glblCmpyCd.getValue(), null, cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        BigDecimal invtyDtlDlyQty = getInvtyDtlDlyQty(cMsg.glblCmpyCd.getValue(), null, cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        if (svcMachMstrQty.compareTo(invtyDtlDlyQty) >= 0) {
            return false;
        }
        if (!hasValue(cMsg.serNum_H1)) {
            return true;
        }
        BigDecimal qty = getInvtyDtlDlyQty(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H1.getValue(), cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        if (qty.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }

    private boolean updateWarehouseValidation(NSAL0010CMsg cMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstrTMsg(cMsg.glblCmpyCd, cMsg.svcMachMstrPk_H1);

        // START 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        NSAL0010Query query = NSAL0010Query.getInstance();
        int countFlgInput = query.countLocStsMndFlgByStsCd(glblCmpyCd, cMsg.svcMachMstrStsCd_H3.getValue());
        int countFlgSmm = query.countLocStsMndFlgByStsCd(glblCmpyCd, svcMachMstrTMsg.svcMachMstrStsCd.getValue());
        if (countFlgInput == 0 && countFlgSmm == 0) {
            // No need to check warehouse
            return true;
        }
        // END 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]

        if (isWarehouse(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), svcMachMstrTMsg.indCurLocNum.getValue())) {
            if (!cMsg.indCurLocNum_M3.getValue().equals(svcMachMstrTMsg.indCurLocNum.getValue())) {
                return false;
            }
        }
        if (!isWarehouse(cMsg.glblCmpyCd.getValue(), cMsg.slsDt.getValue(), cMsg.indCurLocNum_M3.getValue())) {
            return true;
        }
        if (!statusWarehouseValidation(cMsg, svcMachMstrTMsg)) {
            return false;
        }
        if (!terminateWarehouseValidation(cMsg, svcMachMstrTMsg)) {
            return false;
        }
        if (!serialChangeWarehouseValidation(cMsg)) {
            return false;
        }
        return true;
    }

    private boolean statusWarehouseValidation(NSAL0010CMsg cMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (!cMsg.svcMachMstrStsCd_H3.getValue().equals(SVC_MACH_MSTR_STS.TERMINATED)) {
            if (!cMsg.svcMachMstrStsCd_H3.getValue().equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
                return false;
            }
        }
        if (!cMsg.svcMachUsgStsCd_H3.getValue().equals(svcMachMstrTMsg.svcMachUsgStsCd.getValue())) {
            return false;
        }
        if (!cMsg.svcMachMstrLocStsCd_H3.getValue().equals(svcMachMstrTMsg.svcMachMstrLocStsCd.getValue())) {
            return false;
        }
        return true;
    }

    private boolean terminateWarehouseValidation(NSAL0010CMsg cMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (!cMsg.svcMachMstrStsCd_H3.getValue().equals(SVC_MACH_MSTR_STS.TERMINATED)) {
            return true;
        }
        if (svcMachMstrTMsg.svcMachMstrStsCd.getValue().equals(SVC_MACH_MSTR_STS.TERMINATED)) {
            return true;
        }
        BigDecimal svcMachMstrQty = getSvcMachMstrQty(cMsg.glblCmpyCd.getValue(), null, cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        BigDecimal invtyDtlDlyQty = getInvtyDtlDlyQty(cMsg.glblCmpyCd.getValue(), null, cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        if (svcMachMstrQty.compareTo(invtyDtlDlyQty) <= 0) {
            return false;
        }
        if (!hasValue(cMsg.serNum_H1)) {
            return true;
        }
        BigDecimal qty = getInvtyDtlDlyQty(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H1.getValue(), cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        if (qty.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }

    private boolean serialChangeWarehouseValidation(NSAL0010CMsg cMsg) {
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            return true;
        }
        BigDecimal qty = getInvtyDtlDlyQty(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H1.getValue(), cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        if (qty.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        qty = getInvtyDtlDlyQty(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H2.getValue(), cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        if (qty.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        qty = getSvcMachMstrQty(cMsg.glblCmpyCd.getValue(), cMsg.serNum_H2.getValue(), cMsg.mdseCd_H1.getValue(), cMsg.indCurLocNum_M3.getValue(), cMsg.svcMachMstrLocStsCd_H3.getValue(), cMsg.stkStsCd_H3.getValue());
        if (qty.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }

    private BigDecimal getSvcMachMstrQty(String glblCmpyCd, String serNum, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("invtyLocCd", invtyLocCd);
        ssmParam.put("locStsCd", locStsCd);
        ssmParam.put("stkStsCd", stkStsCd);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        ssmParam.put("svcMachMstrStsCdList", svcMachMstrStsCdList);

        return NSAL0010Query.getInstance().getSvcMachMstrQty(ssmParam);
    }

    private BigDecimal getInvtyDtlDlyQty(String glblCmpyCd, String serNum, String mdseCd, String invtyLocCd, String locStsCd, String stkStsCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("invtyLocCd", invtyLocCd);
        ssmParam.put("locStsCd", locStsCd);
        ssmParam.put("stkStsCd", stkStsCd);

        return NSAL0010Query.getInstance().getInvtyDtlDlyQty(ssmParam);
    }
    // END 2018/05/28 K.Kitachi [QC#19932, ADD]
    // Add Start 2018/06/05 QC#23428
    private boolean isSvcExch(NSAL0010CMsg cMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = NSAL0010CommonLogic.getSvcMachMstr(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue());
        if (svcMachMstrTMsg == null) {
            return false;
        }
        if (hasValue(svcMachMstrTMsg.svcConfigMstrPk)) {
            return false;
        }
        if (!hasValue(svcMachMstrTMsg.svcMachTpCd)) {
            return false;
        }

        // Status Check
        if (!SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
            return false;
        }
        if (!SVC_MACH_MSTR_STS.INSTALLED.equals(cMsg.svcMachMstrStsCd_H3.getValue())) {
            return false;
        }

        // Check SVC_CONFIG_MSTR_DTL
        SVC_CONFIG_MSTR_DTLTMsg svcConfigMstrDtlTMsg = NSAL0010CommonLogic.getSvcConfigMstrDtlTMsg(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrPk_H1.getValue());
        if (svcConfigMstrDtlTMsg == null) {
            return false;
        }
        if (!hasValue(svcConfigMstrDtlTMsg.svcConfigMstrPk)) {
            return false;
        }

        // Order Number Check
        if (!hasValue(svcMachMstrTMsg.cpoOrdNum)) {
            return false;
        }
        if (!isExchOrdCatg(svcMachMstrTMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue())) {
            return false;
        }
        return true;
    }

    private boolean isExchOrdCatg(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        BigDecimal cnt = NSAL0010Query.getInstance().cntExchOrd(glblCmpyCd, svcMachMstrPk);
        if (BigDecimal.ZERO.compareTo(cnt) == 0) {
            return false;
        }
        return true;
    }

    private void setParamUpdateForExch(NSZC001001PMsg pMsg, NSAL0010CMsg cMsg) {
        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.slsDt, cMsg.slsDt);
        setValue(pMsg.xxModeCd, ProcessMode.INSTALLATION.code);
        setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk_H1);
        setValue(pMsg.svcMachMstrStsCd, cMsg.svcMachMstrStsCd_H3);
        setValue(pMsg.istlDt, cMsg.istlDt_H1);
        setValue(pMsg.stkStsCd, cMsg.stkStsCd_H3);
        setValue(pMsg.svcMachMstrLocStsCd, cMsg.svcMachMstrLocStsCd_H3);
        setValue(pMsg.svcMachUsgStsCd, cMsg.svcMachUsgStsCd_H3);
    }
    // Add End 2018/06/05 QC#23428

    // START 2021/08/17 R. Cabral [QC#59010, ADD]
    /**
     * checkTransactionTypeTransition
     * @param cMsg NSAL0010CMsg
     * @param sMsg NSAL0010SMsg
     * @return boolean
     */
    private boolean checkTransactionTypeTransition(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        String currVal = cMsg.svcMachTrxTpCd_H3.getValue();
        BigDecimal svcMachMstrPk = cMsg.svcMachMstrPk_H1.getValue();
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        SVC_MACH_MSTRTMsg machMstr = NSAL0010CommonLogic.getSvcMachMstr(glblCmpyCd, svcMachMstrPk);

        if (machMstr == null) {
            // null machMstr result means machine has not been registered yet
            return true;
        }

        String beforeVal = machMstr.svcMachTrxTpCd.getValue();

        if (!currVal.equalsIgnoreCase(beforeVal)) {
            if (currVal.isEmpty()) {
                currVal = BLANK_TEXT;
            }

            if (beforeVal.isEmpty()) {
                beforeVal = BLANK_TEXT;
            }

            boolean transitionPossible = NSAL0010Query.getInstance().checkTransitionFlags(glblCmpyCd, beforeVal, currVal);

            if (!transitionPossible) {
                cMsg.svcMachTrxTpCd_H3.setErrorInfo(1, NSZM1379E);
                return false;
            }
        }
        return true;
    }
    // END   2021/08/17 R. Cabral [QC#59010, ADD]
}
