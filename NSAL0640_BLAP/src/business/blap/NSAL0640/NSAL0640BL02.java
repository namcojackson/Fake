/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0640;

import static business.blap.NSAL0640.constant.NSAL0640Constant.DIV_STR;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0013E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0045E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0065E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.LOB_OR_BR;
import static business.blap.NSAL0640.constant.NSAL0640Constant.REP_NAME;
import static business.blap.NSAL0640.constant.NSAL0640Constant.DEF_EFF_THRU_DT;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0640.common.NSAL0640CommonLogic;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2015/12/14   Hitachi         T.Tsuchida      Update          QC#1577
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/04/01   Hitachi         M.Gotou         Update          QC#4916
 * 2016/10/11   Hitachi         T.Mizuki        Update          QC#14606
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/10   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 * 2018/06/29   Fujitsu         T.Ogura         Update          QC#26786
 *</pre>
 */
public class NSAL0640BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0640CMsg cMsg = (NSAL0640CMsg) arg0;
        // mod start 2016/12/08 CSA QC#4210
        NSAL0640SMsg sMsg = (NSAL0640SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0640_INIT".equals(screenAplID)) {
                doProcess_NSAL0640_INIT(cMsg, sMsg);
            // START 2017/02/10 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0640Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0640_CMN_Reset(cMsg, sMsg);
            // END   2017/02/10 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0640Scrn00_OpenWin_SelectBranch".equals(screenAplID)) {
                doProcess_NSAL0640Scrn00_OpenWin_SelectBranch(cMsg);
            // del start 2016/04/04 CSA Defect#4916
            //} else if ("NSAL0640_NSAL0420".equals(screenAplID)) {
            //    doProcess_NSAL0640_NSAL0420(cMsg);
            // del end 2016/04/04 CSA Defect#4916
            } else if ("NSAL0640Scrn00_ApplyToAll".equals(screenAplID)) {
                doProcess_NSAL0640_ApplyToAll(cMsg, sMsg);
            // mod start 2016/04/01 CSA Defect#4916
            //} else if ("NSAL0640Scrn00_Search_Branch".equals(screenAplID)) {
            //    doProcess_NSAL0640Scrn00_Search_Branch(cMsg);
            //} else if ("NSAL0640Scrn00_Search_Rep".equals(screenAplID)) {
            //    doProcess_NSAL0640Scrn00_Search_Rep(cMsg);
            //}
            } else if ("NSAL0640Scrn00_ChangeBranch".equals(screenAplID)) {
                doProcess_NSAL0640Scrn00_ChangeBranch(cMsg);
            } else if ("NSAL0640Scrn00_ChangeRep".equals(screenAplID)) {
                doProcess_NSAL0640Scrn00_ChangeRep(cMsg);
            } else if ("NSAL0640Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0640Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0640Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0640Scrn00_PagePrev(cMsg, sMsg);
            }
            // mod start 2016/04/01 CSA Defect#4916
            
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0640_INIT(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {
        init(cMsg, sMsg);
    }

    // START 2017/02/10 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0640_CMN_Reset(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {
    // END 2017/02/10 K.Ochiai [QC#16331, MOD]
        init(cMsg, sMsg);
     // mod end 2016/12/08 CSA QC#4210
    }

    private void doProcess_NSAL0640Scrn00_OpenWin_SelectBranch(NSAL0640CMsg cMsg) {
// del start 2016/03/28 CSA Defect#4702,4703,4915
//        if (hasValue(cMsg.xxRowNum)) {
//            int index = cMsg.xxRowNum.getValue().intValue();
//            ORG_FUNC_ASGTMsg orgFuncAsgTMsg = null;
//            if (index >= 0) {
//                orgFuncAsgTMsg = NSAL0640Query.getInstance().findOrgFuncAsgByToc(cMsg.glblCmpyCd.getValue(), cMsg.A.no(index).tocCd_A2.getValue());
//                if (orgFuncAsgTMsg != null) {
//                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).psnCd_A2, orgFuncAsgTMsg.psnCd);
//                } else {
//                    cMsg.A.no(index).psnCd_A2.clear();
//                }
//            } else {
//                orgFuncAsgTMsg = NSAL0640Query.getInstance().findOrgFuncAsgByToc(cMsg.glblCmpyCd.getValue(), cMsg.tocCd_H.getValue());
//                if (orgFuncAsgTMsg != null) {
//                    ZYPEZDItemValueSetter.setValue(cMsg.psnCd_H, orgFuncAsgTMsg.psnCd);
//                } else {
//                    cMsg.psnCd_H.clear();
//                }
//            }
//        }
// del end 2016/03/28 CSA Defect#4702,4703,4915
    }

// del start 2016/04/04 CSA Defect#4916
//    private void doProcess_NSAL0640_NSAL0420(NSAL0640CMsg cMsg) {
// del start 2016/03/28 CSA Defect#4702,4703,4915
//        if (hasValue(cMsg.xxRowNum)) {
//            int index = cMsg.xxRowNum.getValue().intValue();
//            ORG_FUNC_ASGTMsg orgFuncAsgTMsg = null;
//            if (index >= 0) {
//                orgFuncAsgTMsg = NSAL0640Query.getInstance().findOrgFuncAsgByPsnCd(cMsg.glblCmpyCd.getValue(), cMsg.A.no(index).psnCd_A2.getValue());
//                if (orgFuncAsgTMsg != null) {
//                    TOCTMsg tocTMsg = NSAL0640Query.getInstance().findRepInfo(cMsg.glblCmpyCd.getValue(), orgFuncAsgTMsg.tocCd.getValue());
//                    if (tocTMsg != null) {
//                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).tocCd_A2, tocTMsg.tocCd);
//                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).tocNm_A2, tocTMsg.tocNm);
//                    }
//                } else {
//                    cMsg.A.no(index).tocCd_A2.clear();
//                    cMsg.A.no(index).tocNm_A2.setErrorInfo(1, NSAM0045E, new String[] {TOC });
//                    cMsg.A.no(index).psnCd_A2.clear();
//                }
//            } else {
//                orgFuncAsgTMsg = NSAL0640Query.getInstance().findOrgFuncAsgByPsnCd(cMsg.glblCmpyCd.getValue(), cMsg.psnCd_H.getValue());
//                if (orgFuncAsgTMsg != null) {
//                    TOCTMsg tocTMsg = NSAL0640Query.getInstance().findRepInfo(cMsg.glblCmpyCd.getValue(), orgFuncAsgTMsg.tocCd.getValue());
//                    if (tocTMsg != null) {
//                        ZYPEZDItemValueSetter.setValue(cMsg.tocCd_H, tocTMsg.tocCd);
//                        ZYPEZDItemValueSetter.setValue(cMsg.tocNm_H, tocTMsg.tocNm);
//                    }
//                } else {
//                    cMsg.tocCd_H.setErrorInfo(1, NSAM0045E, new String[] {TOC });
//                    cMsg.tocNm_H.clear();
//                    cMsg.psnCd_H.clear();
//                }
//            }
//        }
// del end 2016/03/28 CSA Defect#4702,4703,4915
//    }
// del end 2016/04/04 CSA Defect#4916

    // mod start 2016/12/08 CSA QC#4210
    private void doProcess_NSAL0640_ApplyToAll(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {
        NSAL0640CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        if (!NSAL0640CommonLogic.checkSelect(cMsg, sMsg)) {
        // mod end 2016/12/08 CSA QC#4210
            return;
        }

        String brNm = "";
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                // mod start 2016/04/04 CSA Defect#4916
                // mod start 2016/03/28 CSA Defect#4702,4703,4915
                brNm = "";
                if (hasValue(cMsg.svcContrBrCd_H) && hasValue(cMsg.svcContrBrDescTxt_H) && hasValue(cMsg.xxDplyByCdNmCnctTxt_H)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).svcContrBrCd_A2, cMsg.svcContrBrCd_H);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).svcContrBrDescTxt_A2, cMsg.svcContrBrDescTxt_H);
                    if (hasValue(cMsg.svcLineBizDescTxt_P)) {
                        brNm = brNm.concat(cMsg.svcLineBizDescTxt_P.getValue());
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).svcLineBizCd_A2, cMsg.svcLineBizDescTxt_P);
                    } else {
                        brNm = brNm.concat(cMsg.A.no(i).svcLineBizCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).svcLineBizCd_A2, cMsg.A.no(i).svcLineBizCd_A1);
                    }
                }
                // del start 2016/04/04 CSA Defect#4916
                //if (hasValue(brNm)) {
                //    brNm = brNm.concat(DIV_STR);
                //}
                //brNm = brNm.concat(cMsg.svcContrBrCd_H.getValue());
                // del end 2016/04/04 CSA Defect#4916
                if (hasValue(brNm)) {
                    brNm = brNm.concat(DIV_STR);
                }
                brNm = brNm.concat(cMsg.svcContrBrDescTxt_H.getValue());
                //if (hasValue(cMsg.svcContrBrCd_H) && hasValue(cMsg.svcContrBrDescTxt_H) && hasValue(brNm)) {
                if (hasValue(cMsg.xxDplyByCdNmCnctTxt_H) && hasValue(brNm)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxGenlFldAreaTxt_A2, brNm);
                }
                //ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).tocCd_A2, cMsg.tocCd_H);
                //ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).tocNm_A2, cMsg.tocNm_H);
                if (hasValue(cMsg.psnCd_H) && hasValue(cMsg.xxPsnNm_H)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).psnCd_A2, cMsg.psnCd_H);
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxPsnNm_A2, cMsg.xxPsnNm_H);
                }
                // mod end 2016/03/28 CSA Defect#4702,4703,4915
                // mod end 2016/04/04 CSA Defect#4916
            }
        }
    }

    // mod start 2016/04/01 CSA Defect#4916
//    private void doProcess_NSAL0640Scrn00_Search_Branch(NSAL0640CMsg cMsg) {
//        SVC_CONTR_BRTMsg tMsg = NSAL0640Query.getInstance().findBrNmInfo(cMsg.glblCmpyCd.getValue(), cMsg.svcContrBrCd_H.getValue());
//        if (tMsg != null) {
//            ZYPEZDItemValueSetter.setValue(cMsg.svcContrBrDescTxt_H, tMsg.svcContrBrDescTxt);
//        } else {
//            cMsg.svcContrBrCd_H.setErrorInfo(1, NSAM0045E, new String[] {SVC_CONTR_BR });
//            cMsg.svcContrBrDescTxt_H.clear();
//        }
//    }
    private void doProcess_NSAL0640Scrn00_ChangeBranch(NSAL0640CMsg cMsg) {
        if (hasValue(cMsg.xxRowNum)) {
            int index = cMsg.xxRowNum.getValue().intValue();
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
            String st = "";
            if (index >= 0) {
                if (!hasValue(cMsg.A.no(index).xxGenlFldAreaTxt_A2)) {
                    cMsg.A.no(index).svcLineBizCd_A2.clear();
                    cMsg.A.no(index).svcContrBrCd_A2.clear();
                    cMsg.A.no(index).svcContrBrDescTxt_A2.clear();
                    return;
                }
                st = cMsg.A.no(index).xxGenlFldAreaTxt_A2.getValue();
            } else {
                if (!hasValue(cMsg.xxDplyByCdNmCnctTxt_H)) {
                    cMsg.svcLineBizCd_H.clear();
                    cMsg.svcContrBrCd_H.clear();
                    cMsg.svcContrBrDescTxt_H.clear();
                    return;
                }
                st = cMsg.xxDplyByCdNmCnctTxt_H.getValue();
            }

            // mod start 2016/04/07 CSA Defect#4916
            String[] st2 = st.split(DIV_STR, 2);
            String[] para = new String[st2.length];
            for (int i = 0; i < st2.length; i++) {
                para[i] = st2[i];
            }
            String lob = para[0].trim();
            String branchName = "%";
            if (st2.length > 1) {
                para[1] = para[1].trim();
                branchName = para[1] + branchName;
            }
            // mod end 2016/04/07 CSA Defect#4916

            S21SsmEZDResult rslt = NSAL0640Query.getInstance().findBranchNameInfo(cMsg.glblCmpyCd.getValue(), lob, branchName);
            if (rslt != null && rslt.isCodeNormal()) {
                List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
                if (rslt.getQueryResultCount() > 1) {
                    setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    Map<String, String> rsltMap = rsltList.get(0);
                    lob = lob.concat(DIV_STR);
                    lob = lob.concat((String) rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
                    if (index >= 0) {
                        setValue(cMsg.A.no(index).xxGenlFldAreaTxt_A2, lob);
                        setValue(cMsg.A.no(index).svcContrBrCd_A2, rsltMap.get("SVC_CONTR_BR_CD"));
                        setValue(cMsg.A.no(index).svcContrBrDescTxt_A2, rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
                        setValue(cMsg.A.no(index).svcLineBizCd_A2, rsltMap.get("SVC_LINE_BIZ_CD"));
                        setValue(cMsg.svcLineBizDescTxt_P, cMsg.A.no(index).svcLineBizCd_A2);
                    } else {
                        setValue(cMsg.xxDplyByCdNmCnctTxt_H, lob);
                        setValue(cMsg.svcContrBrCd_H, rsltMap.get("SVC_CONTR_BR_CD"));
                        setValue(cMsg.svcContrBrDescTxt_H, rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
                        setValue(cMsg.svcLineBizCd_H, rsltMap.get("SVC_LINE_BIZ_CD"));
                        setValue(cMsg.svcLineBizDescTxt_P, cMsg.svcLineBizCd_H);
                    }
                }
            } else {
                if (index >= 0) {
                    cMsg.A.no(index).xxGenlFldAreaTxt_A2.setErrorInfo(1, NSAM0045E, new String[] {LOB_OR_BR });
                } else {
                    cMsg.xxDplyByCdNmCnctTxt_H.setErrorInfo(1, NSAM0045E, new String[] {LOB_OR_BR });
                }
            }
        }
    }

//    private void doProcess_NSAL0640Scrn00_Search_Rep(NSAL0640CMsg cMsg) {
//        // mod start 2016/03/28 CSA Defect#4702,4703,4915
//        //TOCTMsg tMsg = NSAL0640Query.getInstance().findRepInfo(cMsg.glblCmpyCd.getValue(), cMsg.tocCd_H.getValue());
//        S21_PSNTMsg tMsg = NSAL0640Query.getInstance().findRepInfo(cMsg.glblCmpyCd.getValue(), cMsg.psnCd_H.getValue());
//        if (tMsg != null) {
//            //ZYPEZDItemValueSetter.setValue(cMsg.tocNm_H, tMsg.tocNm);
//            String psnNm = "";
//            psnNm = psnNm.concat(tMsg.psnFirstNm.getValue());
//            psnNm = psnNm.concat(" ");
//            psnNm = psnNm.concat(tMsg.psnLastNm.getValue());
//            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_H, psnNm);
//        } else {
//            //cMsg.tocCd_H.setErrorInfo(1, NSAM0045E, new String[] {TOC });
//            //cMsg.tocNm_H.clear();
//            cMsg.psnCd_H.setErrorInfo(1, NSAM0045E, new String[] {S21_PSN });
//            cMsg.xxPsnNm_H.clear();
//        }
//        // mod end 2016/03/28 CSA Defect#4702,4703,4915
//    }

    private void doProcess_NSAL0640Scrn00_ChangeRep(NSAL0640CMsg cMsg) {
        if (hasValue(cMsg.xxRowNum)) {
            int index = cMsg.xxRowNum.getValue().intValue();
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
            String st = "";
            if (index >= 0) {
                if (!hasValue(cMsg.A.no(index).xxPsnNm_A2)) {
                    cMsg.A.no(index).psnCd_A2.clear();
                    return;
                }
                st = cMsg.A.no(index).xxPsnNm_A2.getValue();
            } else {
                if (!hasValue(cMsg.xxPsnNm_H)) {
                    cMsg.psnCd_H.clear();
                    return;
                }
                st = cMsg.xxPsnNm_H.getValue();
            }

            String[] st2 = st.split(" ");
            String[] para = new String[st2.length];
            for (int i = 0; i < st2.length; i++) {
                para[i] = st2[i];
            }
            String firstNm = para[0];
            String lastNm = "";
            if (st2.length > 1) {
                lastNm = para[1] + "%";
            } else {
                firstNm = firstNm + "%";
            }

            S21SsmEZDResult rslt = NSAL0640Query.getInstance().findRepNameInfo(cMsg.glblCmpyCd.getValue(), firstNm, lastNm);
            if (rslt != null && rslt.isCodeNormal()) {
                List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
                if (rslt.getQueryResultCount() > 1) {
                    setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    Map<String, String> rsltMap = rsltList.get(0);
                    String repName = "";
                    repName = repName.concat((String) rsltMap.get("FIRST_NM"));
                    repName = repName.concat(" ");
                    repName = repName.concat((String) rsltMap.get("LAST_NM"));
                    if (index >= 0) {
                        setValue(cMsg.A.no(index).xxPsnNm_A2, repName);
                        setValue(cMsg.A.no(index).psnCd_A2, rsltMap.get("PSN_CD"));
                    } else {
                        setValue(cMsg.xxPsnNm_H, repName);
                        setValue(cMsg.psnCd_H, rsltMap.get("PSN_CD"));
                    }
                }
            } else {
                if (index >= 0) {
                    cMsg.A.no(index).xxPsnNm_A2.setErrorInfo(1, NSAM0045E, new String[] {REP_NAME });
                } else {
                    cMsg.xxPsnNm_H.setErrorInfo(1, NSAM0045E, new String[] {REP_NAME });
                }
            }
        }
    }
    // mod end 2016/04/01 CSA Defect#4916
    // mod start 2016/12/08 CSA QC#4210
    private void init(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {
        cMsg.svcContrBrCd_H.clear();
        cMsg.svcContrBrDescTxt_H.clear();
        // add start 2016/04/01 CSA Defect#4916
        cMsg.svcLineBizCd_H.clear();
        cMsg.xxDplyByCdNmCnctTxt_H.clear();
        // add end 2016/04/01 CSA Defect#4916
        // mod start 2016/03/28 CSA Defect#4702,4703,4915
        //cMsg.tocCd_H.clear();
        //cMsg.tocNm_H.clear();
        cMsg.psnCd_H.clear();
        cMsg.xxPsnNm_H.clear();
        // mod end 2016/03/28 CSA Defect#4702,4703,4915
        cMsg.svcMemoRsnCd_H.clear();
        cMsg.svcCmntTxt_H.clear();
        cMsg.xxChkBox_AL.clear();
        ZYPTableUtil.clear(cMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0640CommonLogic.createPullDown(cMsg);
        findContrInfo(cMsg, sMsg);
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsMsgTxt_A1, NSAL0640CommonLogic.getRtnMsg(NSAM0065E));
            }
        }

        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void findContrInfo(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryMap.put("dsContrCatgCdIsWty", DS_CONTR_CATG.WARRANTY);
        // START 2018/06/29 T.Ogura [QC#26786,MOD]
//        queryMap.put("dsContrStsCdIsExpired", DS_CONTR_STS.EXPIRED);
//        queryMap.put("dsContrStsCdIsCancelled", DS_CONTR_STS.CANCELLED);
//        queryMap.put("dsContrStsCdIsTerminated", DS_CONTR_STS.TERMINATED);
//        queryMap.put("dsContrStsCdIsApproved", DS_CONTR_STS.APPROVED);
//        queryMap.put("dsContrStsCdIsEffectived", DS_CONTR_STS.EFFECTIVE);
        queryMap.put("dsContrCtrlStsCdIsCancelled", DS_CONTR_CTRL_STS.CANCELLED);
        queryMap.put("dsContrCtrlStsCdIsExpired", DS_CONTR_CTRL_STS.EXPIRED);
        queryMap.put("dsContrCtrlStsCdIsExpiredHold", DS_CONTR_CTRL_STS.EXPIRED_HOLD);
        queryMap.put("dsContrCtrlStsCdIsTerminated", DS_CONTR_CTRL_STS.TERMINATED);
        queryMap.put("dsContrCtrlStsCdIsTerminatedHold", DS_CONTR_CTRL_STS.TERMINATED_HOLD);
        // END   2018/06/29 T.Ogura [QC#26786,MOD]
        BigDecimal[] dsContrPkList = new BigDecimal[cMsg.P.getValidCount()];
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList[i] = cMsg.P.no(i).dsContrPk_P1.getValue();
        }
        queryMap.put("dsContrPkList", dsContrPkList);
        // mod start 2016/10/11 CSA QC#14606
        queryMap.put("slsDt", ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        queryMap.put("defEffThruDt", DEF_EFF_THRU_DT);
        // mod end 2016/10/11 CSA QC#14606
        queryMap.put("limitCnt", sMsg.A.length() + 1);

        S21SsmEZDResult ssmResult = NSAL0640Query.getInstance().getContrInfo(queryMap, sMsg.A);
        sMsg.A.setValidCount(ssmResult.getQueryResultCount());

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRowNum_A1, BigDecimal.valueOf(i));
        }
        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
    }

    private void doProcess_NSAL0640Scrn00_PageNext(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {

        NSAL0640CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0640_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageTo = cMsg.xxPageShowToNum.getValueInt();
        int i = pageTo;
        for (; i < pageTo + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageTo), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageTo);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSAL0640Scrn00_PagePrev(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg) {

        NSAL0640CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0640_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int i = pageFrom;

        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }
    // mod end 2016/12/08 CSA QC#4210
}
