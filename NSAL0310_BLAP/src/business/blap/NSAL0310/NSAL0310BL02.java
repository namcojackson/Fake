/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0310;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0310.common.NSAL0310CommonLogic;
import business.blap.NSAL0310.constant.NSAL0310Constant;
import business.db.DS_CONTRTMsg;
import business.file.NSAL0310F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2015/10/08   Hitachi         T.Tomita        Update          N/A
 * 2016/01/05   Hitachi         T.Tomita        Update          QC#1029
 * 2016/02/12   Hitachi         A.Kohinata      Update          QC#3019
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#3017
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC#4086
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/05/24   Hitachi         T.Mizuki        Update          QC#447
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2016/12/19   Hitachi         K.Kojima        Update          QC#16600
 * 2017/03/01   Hitachi         N.Arai          Update          QC#17620
 * 2018/10/30   Hitachi         K.Kim           Update          QC#28890
 * 2022/01/21   CITS            R.Cabral        Update          QC#59502
 * 2022/02/03   CITS            R.Cabral        Update          QC#59502
 * 2022/03/08   CITS            R.Cabral        Update          QC#59502-1
 * 2023/04/21   Hitachi         K.Watanabe      Update          QC#61146
 *</pre>
 */
public class NSAL0310BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            NSAL0310CMsg cMsg = (NSAL0310CMsg) ezdCMsg;
            NSAL0310SMsg sMsg = (NSAL0310SMsg) ezdSMsg;
            String screenAplId = cMsg.getScreenAplID();
            if ("NSAL0310_INIT".equals(screenAplId)) {
                doProcess_NSAL0310_INIT(cMsg, sMsg);
            } else if ("NSAL0310_NMAL6010".equals(screenAplId)) {
                doProcess_NSAL0310_NMAL6010(cMsg, sMsg);
            } else if ("NSAL0310_NMAL6050".equals(screenAplId)) {
                doProcess_NSAL0310_NMAL6050(cMsg, sMsg);
            // START 2016/01/05 T.Tomita [QC#1029, ADD]
            } else if ("NSAL0310_NMAL6760".equals(screenAplId)) {
                doProcess_NSAL0310_NMAL6760(cMsg, sMsg);
            // END 2016/01/05 T.Tomita [QC#1029, ADD]
            } else if ("NSAL0310_NSAL0030".equals(screenAplId)) {
                doProcess_NSAL0310_NSAL0030(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_Add_Machines".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Add_Machines(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_CMN_Clear".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_CMN_Close".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_CMN_Close(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_Collapse_Machines".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Collapse_Machines(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_Expand_Machines".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Expand_Machines(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_OpenWin_Model".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_OpenWin_Model(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_OpenWin_Serial".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_OpenWin_Serial(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_OpenWin_ShipTo".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_OpenWin_ShipTo(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_PageNext".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_PagePrev".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_Search".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_Search_ShipTo".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Search_ShipTo(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_Select_All".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Select_All(cMsg, sMsg);
            } else if ("NSAL0310Scrn00_Unselect_All".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Unselect_All(cMsg, sMsg);
            // START 2016/02/12 [QC#3019, ADD]
            } else if ("NSAL0310Scrn00_TBLColumnSort".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_TBLColumnSort(cMsg, sMsg);
            // END 2016/02/12 [QC#3019, ADD]
            // START 2017/03/01 N.Arai [QC#17620, ADD]
            } else if ("NSAL0310Scrn00_Check_MainMachine".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Check_MainMachine(cMsg, sMsg);
            // END 2017/03/01 N.Arai [QC#17620, ADD]
            // START 2023/04/21 K.Watanabe [QC#61146, ADD]
            } else if ("NSAL0310Scrn00_Download".equals(screenAplId)) {
                doProcess_NSAL0310Scrn00_Download(cMsg, sMsg);
            // END 2023/04/21 K.Watanabe [QC#61146, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NSAL0310_INIT(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        // START 2016/12/19 K.Kojima [QC#16600,ADD]
        String serNum = cMsg.serNum.getValue();
        // END 2016/12/19 K.Kojima [QC#16600,ADD]
        // START 2015/10/08 T.Tomita [N/A, MOD]
//        NSAL0310CommonLogic.resetParameter(cMsg, sMsg);
        NSAL0310CommonLogic.resetParameter(getGlobalCompanyCode(), cMsg, sMsg);
        // END 2015/10/08 T.Tomita [N/A, MOD]
        // START 2016/12/19 K.Kojima [QC#16600,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.serNum, serNum);
        if (ZYPCommonFunc.hasValue(cMsg.serNum)) {
            doProcess_NSAL0310Scrn00_Search(cMsg, sMsg);
        }
        // END 2016/12/19 K.Kojima [QC#16600,ADD]
    }

    private void doProcess_NSAL0310_NMAL6010(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    }

    private void doProcess_NSAL0310_NMAL6050(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    }

    // START 2016/01/05 T.Tomita [QC#1029, ADD]
    private void doProcess_NSAL0310_NMAL6760(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        // [QC#3017,DEL]START
        //if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
        //    doProcess_NSAL0310Scrn00_Search_ShipTo(cMsg, sMsg);
        //}
        // [QC#3017,DEL]END
    }
    // END 2016/01/05 T.Tomita [QC#1029, ADD]

    private void doProcess_NSAL0310_NSAL0030(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    }

    private void doProcess_NSAL0310Scrn00_Add_Machines(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);
        // START 2017/03/01 N.Arai [QC#2017/03/01, MOD]
        if (!NSAL0310CommonLogic.isValidMachineSelection(cMsg, sMsg)) {
            return;
        }
        // END 2017/03/01 N.Arai [QC#17620, MOD]
        // Start 2016/05/24 T.Mizuki
        if (!NSAL0310CommonLogic.isValidMachines(cMsg, sMsg, getGlobalCompanyCode())) {
            // End 2016/05/24 T.Mizuki
            // START 2017/03/01 N.Arai [QC#17620, MOD]
            NSAL0310CommonLogic.createAddMachinesTableA(cMsg, sMsg);
            cMsg.setCommitSMsg(true);
            // END 2017/03/01 N.Arai [QC#17620, MOD]
            return;
        }

        ZYPTableUtil.clear(cMsg.P);
        int p = 0;
        HashMap<BigDecimal, BigDecimal> machAddedMap = new HashMap<BigDecimal, BigDecimal>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String svcMachTpCd = sMsg.A.no(i).svcMachTpCd_A.getValue();
            String chkMach = sMsg.A.no(i).xxChkBox_AM.getValue();
            String chkAsry = sMsg.A.no(i).xxChkBox_AA.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd) && ZYPConstant.FLG_ON_Y.equals(chkMach)) {
            BigDecimal chkDsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd) && ZYPConstant.FLG_ON_Y.equals(chkMach) && !ZYPCommonFunc.hasValue(chkDsContrDtlPk)) {
                // Not a machine that's pre-existing in the contract, add the svcConfigMstrPk and create a DS_CONTR_DTL_PK
                BigDecimal dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_DTL_SQ");
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).dsContrDtlPk_P1, dsContrDtlPk);
                machAddedMap.put(sMsg.A.no(i).svcConfigMstrPk_A.getValue(), dsContrDtlPk);
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
                // START 2015/10/08 T.Tomita [N/A, MOD]
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).svcConfigMstrPk_P, sMsg.A.no(i).svcConfigMstrPk_A);
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).svcMachMstrPk_P, sMsg.A.no(i).svcMachMstrPk_A);
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).contrEffFromDt_P, sMsg.A.no(i).contrEffFromDt_A);
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).contrEffThruDt_P, sMsg.A.no(i).contrEffThruDt_A);
                String billUsageFlg = sMsg.A.no(i).xxChkBox_AB.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(billUsageFlg)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).bllgFlg_P, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).bllgFlg_P, ZYPConstant.FLG_OFF_N);
                }
                // END 2015/10/08 T.Tomita [N/A, MOD]
                p++;
            }
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd) && ZYPConstant.FLG_ON_Y.equals(chkAsry)) {
            if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd) && ZYPConstant.FLG_ON_Y.equals(chkAsry) && !ZYPCommonFunc.hasValue(chkDsContrDtlPk)) {
            // END   2022/01/21 R.Cabral [QC#59502, ADD]
                // START 2015/10/08 T.Tomita [N/A, MOD]
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).svcConfigMstrPk_P, sMsg.A.no(i).svcConfigMstrPk_A);
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).svcMachMstrPk_P, sMsg.A.no(i).svcMachMstrPk_A);
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).contrEffFromDt_P, sMsg.A.no(i).contrEffFromDt_A);
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).contrEffThruDt_P, sMsg.A.no(i).contrEffThruDt_A);
                String billUsageFlg = sMsg.A.no(i).xxChkBox_AB.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(billUsageFlg)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).bllgFlg_P, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).bllgFlg_P, ZYPConstant.FLG_OFF_N);
                }
                // END 2015/10/08 T.Tomita [N/A, MOD]
                // START 2022/01/21 R.Cabral [QC#59502, ADD]
                BigDecimal dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_DTL_SQ");
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).dsContrDtlPk_P1, dsContrDtlPk);
                BigDecimal prntDsContrDtlPk = sMsg.A.no(i).prntDsContrDtlPk_A.getValue();
                if (!ZYPCommonFunc.hasValue(prntDsContrDtlPk)) {
                    prntDsContrDtlPk = machAddedMap.get(sMsg.A.no(i).svcConfigMstrPk_A.getValue());
                }
                ZYPEZDItemValueSetter.setValue(cMsg.P.no(p).prntDsContrDtlPk_P, prntDsContrDtlPk);
                // END   2022/01/21 R.Cabral [QC#59502, ADD]
                p++;
            }
        }
        cMsg.P.setValidCount(p);
    }

    private void doProcess_NSAL0310Scrn00_CMN_Clear(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        // START 2015/10/08 T.Tomita [N/A, MOD]
//        NSAL0310CommonLogic.resetParameter(cMsg, sMsg);
        NSAL0310CommonLogic.resetParameter(getGlobalCompanyCode(), cMsg, sMsg);
        // END 2015/10/08 T.Tomita [N/A, MOD]
    }

    private void doProcess_NSAL0310Scrn00_CMN_Close(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    }

    private void doProcess_NSAL0310Scrn00_Collapse_Machines(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);

        // START 2017/03/01 N.Arai [QC#17620, DEL]
//        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg)) {
//            return;
//        }
        // END 2017/03/01 N.Arai [QC#17620, DEL]

        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            int cIdx = cMsg.xxRowNum.getValueInt();
            BigDecimal cPk = cMsg.A.no(cIdx).svcConfigMstrPk_A.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, ADD]
            BigDecimal rowDsContrDtlPk = cMsg.A.no(cIdx).dsContrDtlPk_A.getValue();
            BigDecimal prntDsContrDtlPk;
            BigDecimal dsContrDtlPk;
            // END   2022/01/21 R.Cabral [QC#59502, ADD]
            if (ZYPCommonFunc.hasValue(cPk)) {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    BigDecimal sPk = sMsg.A.no(i).svcConfigMstrPk_A.getValue();
                    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                    if (ZYPCommonFunc.hasValue(sPk) && cPk.compareTo(sPk) == 0) {
                    prntDsContrDtlPk = sMsg.A.no(i).prntDsContrDtlPk_A.getValue();
                    dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
                    boolean isMachineDsContrDtlPkEqual = SVC_MACH_TP.MACHINE.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())
                       && ((rowDsContrDtlPk == null && dsContrDtlPk == null)
                               || (rowDsContrDtlPk != null && dsContrDtlPk != null && rowDsContrDtlPk.compareTo(dsContrDtlPk) == 0));
                    boolean isAccessoryDsContrDtlPkEqual = SVC_MACH_TP.ACCESSORY.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())
                       && ((rowDsContrDtlPk == null && prntDsContrDtlPk == null)
                               || (rowDsContrDtlPk != null && prntDsContrDtlPk != null && rowDsContrDtlPk.compareTo(prntDsContrDtlPk) == 0));
                    if ((ZYPCommonFunc.hasValue(sPk) && cPk.compareTo(sPk) == 0)
                            && (isMachineDsContrDtlPkEqual || isAccessoryDsContrDtlPkEqual)) {
                    // END   2022/01/21 R.Cabral [QC#59502, MOD]
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtlStsCd_A, NSAL0310Constant.DTL_STS_CLPS);
                    }
                }
            }
            // START 2017/03/01 N.Arai [QC#17620, MOD]
//            int startIndex = NSAL0310CommonLogic.convertIndexInPageToIndexInSMsgArrayView(cMsg, sMsg, cIdx);
//            int startIndex = NSAL0310CommonLogic.convertIndexInPageToIndexInSMsgArrayView(cMsg, sMsg, cMsg.A.no(cIdx).svcMachMstrPk_A.getValue());
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
            int startIndex = NSAL0310CommonLogic.convertIndexInPageToIndexInSMsgArrayView(cMsg, sMsg, cMsg.A.no(cIdx).svcMachMstrPk_A.getValue(),
                    cMsg.A.no(cIdx).dsContrDtlPk_A.getValue(), cMsg.A.no(cIdx).prntDsContrDtlPk_A.getValue());
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
            NSAL0310CommonLogic.paginateTableAToItem(cMsg, sMsg, startIndex);
            // END 2017/03/01 N.Arai [QC#17620, MOD]
        }
    }

    private void doProcess_NSAL0310Scrn00_Expand_Machines(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);

        // START 2017/03/01 N.Arai [QC#17620, DEL]
//        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg)) {
//            return;
//        }
        // END 2017/03/01 N.Arai [QC#17620, DEL]

        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            int cIdx = cMsg.xxRowNum.getValueInt();
            BigDecimal cPk = cMsg.A.no(cIdx).svcConfigMstrPk_A.getValue();
            // START 2022/01/21 R.Cabral [QC#59502, ADD]
            BigDecimal rowDsContrDtlPk = cMsg.A.no(cIdx).dsContrDtlPk_A.getValue();
            BigDecimal prntDsContrDtlPk;
            BigDecimal dsContrDtlPk;
            // END   2022/01/21 R.Cabral [QC#59502, ADD]
            if (ZYPCommonFunc.hasValue(cPk)) {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    BigDecimal sPk = sMsg.A.no(i).svcConfigMstrPk_A.getValue();
                    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                    if (ZYPCommonFunc.hasValue(sPk) && cPk.compareTo(sPk) == 0) {
                    prntDsContrDtlPk = sMsg.A.no(i).prntDsContrDtlPk_A.getValue();
                    dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
                    boolean isMachineDsContrDtlPkEqual = SVC_MACH_TP.MACHINE.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())
                        && ((rowDsContrDtlPk == null && dsContrDtlPk == null)
                                || (rowDsContrDtlPk != null && dsContrDtlPk != null && rowDsContrDtlPk.compareTo(dsContrDtlPk) == 0));
                    boolean isAccessoryDsContrDtlPkEqual = SVC_MACH_TP.ACCESSORY.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())
                        && ((rowDsContrDtlPk == null && prntDsContrDtlPk == null)
                                || (rowDsContrDtlPk != null && prntDsContrDtlPk != null && rowDsContrDtlPk.compareTo(prntDsContrDtlPk) == 0));
                    if ((ZYPCommonFunc.hasValue(sPk) && cPk.compareTo(sPk) == 0)
                            && (isMachineDsContrDtlPkEqual || isAccessoryDsContrDtlPkEqual)) {
                    // END   2022/01/21 R.Cabral [QC#59502, MOD]
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtlStsCd_A, NSAL0310Constant.DTL_STS_XPND);
                    }
                }
            }
            // START 2017/03/01 N.Arai [QC#17620, MOD]
//            int startIndex = NSAL0310CommonLogic.convertIndexInPageToIndexInSMsgArrayView(cMsg, sMsg, cIdx);
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            int startIndex = NSAL0310CommonLogic.convertIndexInPageToIndexInSMsgArrayView(cMsg, sMsg, cMsg.A.no(cIdx).svcMachMstrPk_A.getValue());
            int startIndex = NSAL0310CommonLogic.convertIndexInPageToIndexInSMsgArrayView(cMsg, sMsg, cMsg.A.no(cIdx).svcMachMstrPk_A.getValue(),
                    cMsg.A.no(cIdx).dsContrDtlPk_A.getValue(), cMsg.A.no(cIdx).prntDsContrDtlPk_A.getValue());
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
            // END 2017/03/01 N.Arai [QC#17620, MOD]
            NSAL0310CommonLogic.paginateTableAToItem(cMsg, sMsg, startIndex);
        }
    }

    private void doProcess_NSAL0310Scrn00_OpenWin_Model(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    }

    private void doProcess_NSAL0310Scrn00_OpenWin_Serial(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    }

    private void doProcess_NSAL0310Scrn00_OpenWin_ShipTo(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
    }

    private void doProcess_NSAL0310Scrn00_PageNext(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);

        // START 2017/03/01 N.Arai [QC#17620, DEL]
//        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg)) {
//            return;
//        }
        // END 2017/03/01 N.Arai [QC#17620, DEL]

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSAL0310CommonLogic.paginateTableAToItem(cMsg, sMsg, startIndex);
    }

    private void doProcess_NSAL0310Scrn00_PagePrev(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);

        // START 2017/03/01 N.Arai [QC#17620, DEL]
//        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg)) {
//            return;
//        }
        // END 2017/03/01 N.Arai [QC#17620, DEL]

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSAL0310CommonLogic.paginateTableAToItem(cMsg, sMsg, startIndex);
    }

    @SuppressWarnings("unchecked")
    private void doProcess_NSAL0310Scrn00_Search(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        String glblCmpyCd = getGlobalCompanyCode();

        NSAL0310Query query = NSAL0310Query.getInstance();
        // START 2015/10/08 T.Tomita [N/A, MOD]
        // [QC#3017,MOD]START
        // START 2016/02/24 T.Kanasaka [QC4086, MOD]
        DS_CONTRTMsg dsContrTmsg = query.getDsContrForPk(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (dsContrTmsg == null) {
            cMsg.setMessageInfo(NSAL0310Constant.NSAM0045E, new String[] {"DS Contract" });
            return;
        }

        // START 2018/10/30 [QC#28890, ADD]
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H)) {
            String dsAcctNm = NSAL0310Query.getInstance().getDsAcct(glblCmpyCd, cMsg.dsAcctNum_H.getValue());
            if (ZYPCommonFunc.hasValue(dsAcctNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, dsAcctNm);
            } else {
                cMsg.dsAcctNum_H.setErrorInfo(1, NSAL0310Constant.NSAM0072E, new String[] {"Customer" });
                return;
            }
        } else {
            cMsg.dsAcctNm.clear();
        }
        // END 2018/10/30 [QC#28890, ADD]
        // START 2022/01/21 R.Cabral [QC#59502, ADD]
        int addedContrMachCnt = 0;
        // START 2022/02/03 R.Cabral [QC#59502, ADD]
        ArrayList<BigDecimal> contractedSvcMachMstrPks = null;
        List<Map<String, Object>> dsContrDtlResultList = null;
        // END   2022/02/03 R.Cabral [QC#59502, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.contrInacFlg.getValue())) {
            S21SsmEZDResult dsContrDtlResult = query.getDsContrDtlMachInfo(glblCmpyCd, cMsg.dsContrPk.getValue());
            // START 2022/02/03 R.Cabral [QC#59502, ADD]
            contractedSvcMachMstrPks = new ArrayList<BigDecimal>();
            // END   2022/02/03 R.Cabral [QC#59502, ADD]

            if (dsContrDtlResult != null) {
                int dsContrDtlCnt = dsContrDtlResult.getQueryResultCount();
                // START 2022/02/03 R.Cabral [QC#59502, MOD]
//                List<Map<String, Object>> dsContrDtlResultList = (List<Map<String, Object>>) dsContrDtlResult.getResultObject();
                dsContrDtlResultList = (List<Map<String, Object>>) dsContrDtlResult.getResultObject();
                // END   2022/02/03 R.Cabral [QC#59502, MOD]
                for (int idx = 0; idx < dsContrDtlCnt; idx++) {
                    Map<String, Object> dsContrDtlMap = dsContrDtlResultList.get(idx);
                    // START 2022/02/03 R.Cabral [QC#59502, MOD]
//                    S21SsmEZDResult rslt = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(), cMsg.mdseDescShortTxt
//                            .getValue(), cMsg.xxConfigFlg.getValue(), sMsg.A.length() + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt.getValue(),
//                            dsContrTmsg.contrVrsnEffThruDt.getValue(), (BigDecimal) dsContrDtlMap.get("SVC_MACH_MSTR_PK"));
//
//                    if (rslt == null) {
//                        continue;
//                    }
//
//                    List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
//                    if (rsltList == null || (rsltList != null && rsltList.size() == 0)) {
//                        continue;
//                    }
//                    Map<String, Object> rsltMap = rsltList.get(0);
//                    ArrayList<BigDecimal> contractAddedAccessories = new ArrayList<BigDecimal>();
//
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).xxDtlStsCd_A, NSAL0310Constant.DTL_STS_CLPS);
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).dsAcctNum_A, (String) rsltMap.get("BILL_TO_ACCT_NUM"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).svcConfigMstrPk_A, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).svcMachTpCd_A, (String) rsltMap.get("SVC_MACH_TP_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).svcMachMstrPk_A, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
//                    contractAddedAccessories.add((BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).serNum_A, (String) rsltMap.get("SER_NUM"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).mdlNm_A, (String) rsltMap.get("T_MDL_NM"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).mdseDescShortTxt_A, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).indCurLocNum_A, (String) rsltMap.get("IND_CUR_LOC_NUM"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).shipToLocNm_A, (String) rsltMap.get("SHIP_TO_LOC_NM"));
//
//                    String addr = NSAL0310CommonLogic.formatAddress((String) rsltMap.get("FIRST_LINE_ADDR"), (String) rsltMap.get("CTY_ADDR"), (String) rsltMap.get("ST_CD"), (String) rsltMap.get("POST_CD"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).xxGenlFldAreaTxt_A, addr);
//
//                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfigFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.contrInacFlg.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).contrEffFromDt_A, (String) dsContrDtlMap.get("CONTR_EFF_FROM_DT"));
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).contrEffThruDt_A, (String) dsContrDtlMap.get("CONTR_EFF_THRU_DT"));
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).mdseNm_A, (String) dsContrDtlMap.get("MDSE_NM"));
//                    }
//
//                    String mtrReqMdlFlg = (String) rsltMap.get("MTR_REQ_MDL_FLG");
//                    if (ZYPConstant.FLG_ON_Y.equals(mtrReqMdlFlg)) {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).xxChkBox_AB, ZYPConstant.FLG_ON_Y);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).xxChkBox_AB, ZYPConstant.FLG_OFF_N);
//                    }
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).prntDsContrDtlPk_A, (BigDecimal) dsContrDtlMap.get("PRNT_DS_CONTR_DTL_PK"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).dsContrDtlPk_A, (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
//                    addedContrMachCnt++;
//
//                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfigFlg.getValue())) {
//                        // Get Contracted Accessories
//                        S21SsmEZDResult dsContrDtlAccResult = query.getDsContrDtlAccInfo(glblCmpyCd, cMsg.dsContrPk.getValue(), (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
//
//                        if (dsContrDtlAccResult != null) {
//                            int dsContrDtlAccCnt = dsContrDtlAccResult.getQueryResultCount();
//                            List<Map<String, Object>> dsContrDtlAccResultList = (List<Map<String, Object>>) dsContrDtlAccResult.getResultObject();
//
//                            for (int idxAcc = 0; idxAcc < dsContrDtlAccCnt; idxAcc++) {
//                                Map<String, Object> dsContrDtlAccMap = dsContrDtlAccResultList.get(idxAcc);
//                                S21SsmEZDResult rsltAcc = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(),
//                                        cMsg.mdseDescShortTxt.getValue(), cMsg.xxConfigFlg.getValue(), sMsg.A.length() + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt
//                                                .getValue(), dsContrTmsg.contrVrsnEffThruDt.getValue(), (BigDecimal) dsContrDtlAccMap.get("SVC_MACH_MSTR_PK"));
//
//                                if (rsltAcc == null) {
//                                    continue;
//                                }
//
//                                int incrementIdx = addedContrMachCnt;
//                                List<Map<String, Object>> rsltAccList = (List<Map<String, Object>>) rsltAcc.getResultObject();
//                                if (rsltAccList == null || (rsltAccList != null && rsltAccList.size() == 0)) {
//                                    continue;
//                                }
//                                Map<String, Object> rsltAccMap = rsltAccList.get(0);
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxDtlStsCd_A, NSAL0310Constant.DTL_STS_CLPS);
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).dsAcctNum_A, (String) rsltAccMap.get("BILL_TO_ACCT_NUM"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcConfigMstrPk_A, (BigDecimal) rsltAccMap.get("SVC_CONFIG_MSTR_PK"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcMachTpCd_A, (String) rsltAccMap.get("SVC_MACH_TP_CD"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcMachMstrPk_A, (BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
//                                contractAddedAccessories.add((BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).serNum_A, (String) rsltAccMap.get("SER_NUM"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).mdlNm_A, (String) rsltAccMap.get("T_MDL_NM"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).mdseDescShortTxt_A, (String) rsltAccMap.get("MDSE_DESC_SHORT_TXT"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).indCurLocNum_A, (String) rsltAccMap.get("IND_CUR_LOC_NUM"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).shipToLocNm_A, (String) rsltAccMap.get("SHIP_TO_LOC_NM"));
//
//                                String addrAcc = NSAL0310CommonLogic.formatAddress((String) rsltAccMap.get("FIRST_LINE_ADDR"), (String) rsltAccMap.get("CTY_ADDR"), (String) rsltAccMap.get("ST_CD"), (String) rsltAccMap.get("POST_CD"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxGenlFldAreaTxt_A, addrAcc);
//
//                                if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfigFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.contrInacFlg.getValue())) {
//                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).contrEffFromDt_A, (String) dsContrDtlAccMap.get("CONTR_EFF_FROM_DT"));
//                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).contrEffThruDt_A, (String) dsContrDtlAccMap.get("CONTR_EFF_THRU_DT"));
//                                }
//
//                                String mtrReqMdlAccFlg = (String) rsltAccMap.get("MTR_REQ_MDL_FLG");
//                                if (ZYPConstant.FLG_ON_Y.equals(mtrReqMdlAccFlg)) {
//                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxChkBox_AB, ZYPConstant.FLG_ON_Y);
//                                } else {
//                                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxChkBox_AB, ZYPConstant.FLG_OFF_N);
//                                }
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).prntDsContrDtlPk_A, (BigDecimal) dsContrDtlAccMap.get("PRNT_DS_CONTR_DTL_PK"));
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).dsContrDtlPk_A, (BigDecimal) dsContrDtlAccMap.get("DS_CONTR_DTL_PK"));
//                                addedContrMachCnt++;
//                            }
//                        }
//
//                        S21SsmEZDResult dsContrDtlOthAccResult = query.getMachAccInfo(glblCmpyCd, (BigDecimal) dsContrDtlMap.get("SVC_CONFIG_MSTR_PK"),
//                                (BigDecimal[]) contractAddedAccessories.toArray(new BigDecimal[contractAddedAccessories.size()]));
//                        if (dsContrDtlOthAccResult == null) {
//                            // Continue to next item since current machine has no accessory
//                            continue;
//                        }
//                        int dsContrDtlOthAccCnt = dsContrDtlOthAccResult.getQueryResultCount();
//                        if (dsContrDtlOthAccCnt == 0) {
//                            // No other accessories can be added, move on to next item
//                            continue;
//                        }
//                        List<Map<String, Object>> dsContrDtlOthAccResultList = (List<Map<String, Object>>) dsContrDtlOthAccResult.getResultObject();
//
//                        for (int idxAcc = 0; idxAcc < dsContrDtlOthAccCnt; idxAcc++) {
//                            Map<String, Object> dsContrDtlAccMap = dsContrDtlOthAccResultList.get(idxAcc);
//                            S21SsmEZDResult rsltAcc = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(),
//                                    cMsg.mdseDescShortTxt.getValue(), cMsg.xxConfigFlg.getValue(), sMsg.A.length() + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt
//                                            .getValue(), dsContrTmsg.contrVrsnEffThruDt.getValue(), (BigDecimal) dsContrDtlAccMap.get("SVC_MACH_MSTR_PK"));
//                            int incrementIdx = addedContrMachCnt;
//
//                            if (rsltAcc == null) {
//                                continue;
//                            }
//
//                            List<Map<String, Object>> rsltAccList = (List<Map<String, Object>>) rsltAcc.getResultObject();
//                            if (rsltAccList == null || (rsltAccList != null && rsltAccList.size() == 0)) {
//                                continue;
//                            }
//
//                            Map<String, Object> rsltAccMap = rsltAccList.get(0);
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxDtlStsCd_A, NSAL0310Constant.DTL_STS_CLPS);
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).dsAcctNum_A, (String) rsltAccMap.get("BILL_TO_ACCT_NUM"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcConfigMstrPk_A, (BigDecimal) rsltAccMap.get("SVC_CONFIG_MSTR_PK"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcMachTpCd_A, (String) rsltAccMap.get("SVC_MACH_TP_CD"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcMachMstrPk_A, (BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
//                            contractAddedAccessories.add((BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).serNum_A, (String) rsltAccMap.get("SER_NUM"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).mdlNm_A, (String) rsltAccMap.get("T_MDL_NM"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).mdseDescShortTxt_A, (String) rsltAccMap.get("MDSE_DESC_SHORT_TXT"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).indCurLocNum_A, (String) rsltAccMap.get("IND_CUR_LOC_NUM"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).shipToLocNm_A, (String) rsltAccMap.get("SHIP_TO_LOC_NM"));
//
//                            String addrAcc = NSAL0310CommonLogic.formatAddress((String) rsltAccMap.get("FIRST_LINE_ADDR"), (String) rsltAccMap.get("CTY_ADDR"), (String) rsltAccMap.get("ST_CD"), (String) rsltAccMap.get("POST_CD"));
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxGenlFldAreaTxt_A, addrAcc);
//
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).contrEffFromDt_A, cMsg.contrVrsnEffFromDt_H);
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).contrEffThruDt_A, cMsg.contrVrsnEffThruDt_H);
//
//                            String mtrReqMdlAccFlg = (String) rsltAccMap.get("MTR_REQ_MDL_FLG");
//                            if (ZYPConstant.FLG_ON_Y.equals(mtrReqMdlAccFlg)) {
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxChkBox_AB, ZYPConstant.FLG_ON_Y);
//                            } else {
//                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxChkBox_AB, ZYPConstant.FLG_OFF_N);
//                            }
//                            // Note: Accessory to be added to the machine. Use DS_CONTR_DTL_PK of machine for accessory's PRNT_DS_CONTR_DTL_PK_A
//                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).prntDsContrDtlPk_A, (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
//                            addedContrMachCnt++;
//                        }
//                    }
                    contractedSvcMachMstrPks.add((BigDecimal) dsContrDtlMap.get("SVC_MACH_MSTR_PK"));
                    // END   2022/02/03 R.Cabral [QC#59502, MOD]
                }
            }
        }
        // END   2022/01/21 R.Cabral [QC#59502, ADD]
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // S21SsmEZDResult rslt = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(), cMsg.mdseNm.getValue(),
        //         cMsg.xxConfigFlg.getValue(), sMsg.A.length() + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt.getValue(), dsContrTmsg.contrVrsnEffThruDt
        //                 .getValue());
        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//        S21SsmEZDResult rslt = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(), cMsg.mdseDescShortTxt
//                .getValue(), cMsg.xxConfigFlg.getValue(), sMsg.A.length() + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt.getValue(),
//                dsContrTmsg.contrVrsnEffThruDt.getValue());
        S21SsmEZDResult rslt = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(), cMsg.mdseDescShortTxt
                // START 2023/04/21 K.Watanabe [QC#61146, MOD]
                // .getValue(), cMsg.xxConfigFlg.getValue(), sMsg.A.length() + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt.getValue(),
                .getValue(), cMsg.xxConfigFlg.getValue(), NSAL0310Constant.LIMIT_SEARCH + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt.getValue(),
                // END 2023/04/21 K.Watanabe [QC#61146, MOD]
                dsContrTmsg.contrVrsnEffThruDt.getValue(), null, null);
        // END   2022/01/21 R.Cabral [QC#59502, MOD]
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        // END 2016/02/24 T.Kanasaka [QC4086, MOD]
        // [QC#3017,MOD]END
        // END 2015/10/08 T.Tomita [N/A, MOD]
        if (rslt == null || !rslt.isCodeNormal()) {
            cMsg.setMessageInfo(NSAL0310Constant.ZZZM9005W);
            return;
        } else {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            int rsltCnt = rslt.getQueryResultCount();
            // START 2022/02/03 R.Cabral [QC#59502, MOD]
//            int rsltCnt = rslt.getQueryResultCount() + addedContrMachCnt;
            int rsltCnt = rslt.getQueryResultCount();
            // START 2023/04/21 K.Watanabe [QC#61146, MOD]
            // int sMsgMaxCnt = sMsg.A.length();
            int searchMaxCnt = NSAL0310Constant.LIMIT_SEARCH;
            if (searchMaxCnt > NSAL0310Constant.SMSG_MAX_CNT) {
                searchMaxCnt = NSAL0310Constant.SMSG_MAX_CNT;
            }
            // END 2023/04/21 K.Watanabe [QC#61146, MOD]
            // END   2022/02/03 R.Cabral [QC#59502, MOD]
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
            // START 2022/02/03 R.Cabral [QC#59502, MOD]
//            if (rsltCnt > sMsg.A.length()) {
//                rsltCnt = sMsg.A.length();
            // START 2023/04/21 K.Watanabe [QC#61146, MOD]
//            if (rsltCnt > sMsgMaxCnt) {
//                rsltCnt = sMsgMaxCnt;
            if (rsltCnt > searchMaxCnt) {
                rsltCnt = searchMaxCnt;
            // END 2023/04/21 K.Watanabe [QC#61146, MOD]
            // END   2022/02/03 R.Cabral [QC#59502, MOD]
                cMsg.setMessageInfo(NSAL0310Constant.NZZM0001W);
            }
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            for (int i = 0; i < rsltCnt; i++) {
//                Map<String, Object> rsltMap = rsltList.get(i);
            // START 2022/02/03 R.Cabral [QC#59502, MOD]
//            for (int i = addedContrMachCnt; i < rsltCnt; i++) {
//                Map<String, Object> rsltMap = rsltList.get(i - addedContrMachCnt);
            for (Integer index = 0; index < rsltCnt; index++) {
                Map<String, Object> rsltMap = rsltList.get(index);
            // END   2022/02/03 R.Cabral [QC#59502, MOD]
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
                // START 2022/02/03 R.Cabral [QC#59502, MOD]
                if (contractedSvcMachMstrPks != null && dsContrDtlResultList != null) {
                    while (contractedSvcMachMstrPks.contains((BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"))) {
                        int idx = contractedSvcMachMstrPks.indexOf((BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                        Map<String, Object> dsContrDtlMap = dsContrDtlResultList.get(idx);
                        // START 2023/04/21 K.Watanabe [QC#61146, MOD]
                        // int machinesAdded = addContractedMachine(cMsg, sMsg, glblCmpyCd, dsContrTmsg, index + addedContrMachCnt, rsltCnt, sMsgMaxCnt, dsContrDtlMap);
                        int machinesAdded = addContractedMachine(cMsg, sMsg, glblCmpyCd, dsContrTmsg, index + addedContrMachCnt, rsltCnt, searchMaxCnt, dsContrDtlMap);
                        // END 2023/04/21 K.Watanabe [QC#61146, MOD]
                        if (machinesAdded > 0) {
                            contractedSvcMachMstrPks.set(idx, BigDecimal.ONE.negate());
                            addedContrMachCnt += machinesAdded;
                        }
                        // START 2023/04/21 K.Watanabe [QC#61146, MOD]
                        // if (rsltCnt + addedContrMachCnt >= sMsgMaxCnt) {
                        if (rsltCnt + addedContrMachCnt >= searchMaxCnt) {
                        // END 2023/04/21 K.Watanabe [QC#61146, MOD]
                            break;
                        }
                    }
                }
                int i = index + addedContrMachCnt;
                // START 2023/04/21 K.Watanabe [QC#61146, ADD]
                if (i > NSAL0310Constant.SMSG_MAX_CNT) {
                    cMsg.setMessageInfo(NSAL0310Constant.NZZM0001W);
                    break;
                }
                // END 2023/04/21 K.Watanabe [QC#61146, ADD]
                // END  2022/02/03 R.Cabral [QC#59502, MOD]
                // START 2015/10/08 T.Tomita [N/A, MOD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDtlStsCd_A, NSAL0310Constant.DTL_STS_CLPS);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsAcctNum_A, (String) rsltMap.get("BILL_TO_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcConfigMstrPk_A, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachTpCd_A, (String) rsltMap.get("SVC_MACH_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk_A, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).serNum_A, (String) rsltMap.get("SER_NUM"));
//                sMsg.A.no(i).mdlNm_A.clear();
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdlNm_A, (String) rsltMap.get("T_MDL_NM"));
                // START 2016/09/15 N.Arai [QC#11616, MOD]
                // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseNm_A, (String) rsltMap.get("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_A, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
                // END 2016/09/15 N.Arai [QC#11616, MOD]
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToCustCd_A, (String) rsltMap.get("SHIP_TO_CUST_CD"));
                // mod start 2016/05/16 CSA Defect#4578
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).indCurLocNum_A, (String) rsltMap.get("IND_CUR_LOC_NUM"));
                // mod end 2016/05/16 CSA Defect#4578
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToLocNm_A, (String) rsltMap.get("SHIP_TO_LOC_NM"));

                String addr = NSAL0310CommonLogic.formatAddress((String) rsltMap.get("FIRST_LINE_ADDR"), (String) rsltMap.get("CTY_ADDR"), (String) rsltMap.get("ST_CD"), (String) rsltMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxGenlFldAreaTxt_A, addr);

//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffFromDt_A, cMsg.contrEffFromDt);
//                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffThruDt_A, cMsg.contrEffThruDt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffFromDt_A, cMsg.contrVrsnEffFromDt_H);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffThruDt_A, cMsg.contrVrsnEffThruDt_H);
//                sMsg.A.no(i).xxChkBox_AB.clear();
                String mtrReqMdlFlg = (String) rsltMap.get("MTR_REQ_MDL_FLG");
                if (ZYPConstant.FLG_ON_Y.equals(mtrReqMdlFlg)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_AB, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_AB, ZYPConstant.FLG_OFF_N);
                }
                // START 2015/10/08 T.Tomita [N/A, MOD]
            }
            // START 2022/02/03 R.Cabral [QC#59502, MOD]
//            sMsg.A.setValidCount(rsltCnt);
            sMsg.A.setValidCount(rsltCnt + addedContrMachCnt);
            // END   2022/02/03 R.Cabral [QC#59502, MOD]

            NSAL0310CommonLogic.paginateTableAToItem(cMsg, sMsg, 0);
        }
    }

    // START 2022/02/03 R.Cabral [QC#59502, ADD]
    // START 2023/04/21 K.Watanabe [QC#61146, MOD]
    // private int addContractedMachine(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String glblCmpyCd, DS_CONTRTMsg dsContrTmsg, Integer addedContrMachCnt, int rsltCnt, int sMsgMaxCnt, Map<String, Object> dsContrDtlMap) {
    private int addContractedMachine(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, String glblCmpyCd, DS_CONTRTMsg dsContrTmsg, Integer addedContrMachCnt, int rsltCnt, int searchMaxCnt, Map<String, Object> dsContrDtlMap) {
    // END 2023/04/21 K.Watanabe [QC#61146, MOD]
        NSAL0310Query query = NSAL0310Query.getInstance();
        int addedMachines = 0;

        S21SsmEZDResult rslt = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(), cMsg.mdseDescShortTxt
                .getValue(), cMsg.xxConfigFlg.getValue(), sMsg.A.length() + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt.getValue(),
                dsContrTmsg.contrVrsnEffThruDt.getValue(), (BigDecimal) dsContrDtlMap.get("SVC_MACH_MSTR_PK"), null);

        if (rslt == null) {
            return addedMachines;
        }

        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        // START 2023/04/21 K.Watanabe [QC#61146, MOD]
        // if (rsltList == null || (rsltList != null && rsltList.size() == 0)) {
        if (rsltList == null || (rsltList != null && rsltList.size() == 0) || addedContrMachCnt > NSAL0310Constant.SMSG_MAX_CNT) {
        // END 2023/04/21 K.Watanabe [QC#61146, MOD]
            return addedMachines;
        }
        Map<String, Object> rsltMap = rsltList.get(0);
        ArrayList<BigDecimal> contractAddedAccessories = new ArrayList<BigDecimal>();

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).xxDtlStsCd_A, NSAL0310Constant.DTL_STS_CLPS);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).dsAcctNum_A, (String) rsltMap.get("BILL_TO_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).svcConfigMstrPk_A, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).svcMachTpCd_A, (String) rsltMap.get("SVC_MACH_TP_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).svcMachMstrPk_A, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
        contractAddedAccessories.add((BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).serNum_A, (String) rsltMap.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).mdlNm_A, (String) rsltMap.get("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).mdseDescShortTxt_A, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).indCurLocNum_A, (String) rsltMap.get("IND_CUR_LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).shipToLocNm_A, (String) rsltMap.get("SHIP_TO_LOC_NM"));

        String addr = NSAL0310CommonLogic.formatAddress((String) rsltMap.get("FIRST_LINE_ADDR"), (String) rsltMap.get("CTY_ADDR"), (String) rsltMap.get("ST_CD"), (String) rsltMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).xxGenlFldAreaTxt_A, addr);

        // START 2022/03/08 R.Cabral [QC#59502-1, MOD]
//        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfigFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.contrInacFlg.getValue())) {
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).contrEffFromDt_A, (String) dsContrDtlMap.get("CONTR_EFF_FROM_DT"));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).contrEffThruDt_A, (String) dsContrDtlMap.get("CONTR_EFF_THRU_DT"));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).mdseNm_A, (String) dsContrDtlMap.get("MDSE_NM"));
//        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).contrEffFromDt_A, (String) dsContrDtlMap.get("CONTR_EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).contrEffThruDt_A, (String) dsContrDtlMap.get("CONTR_EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).mdseNm_A, (String) dsContrDtlMap.get("MDSE_NM"));
        // END   2022/03/08 R.Cabral [QC#59502-1, MOD]

        String mtrReqMdlFlg = (String) rsltMap.get("MTR_REQ_MDL_FLG");
        if (ZYPConstant.FLG_ON_Y.equals(mtrReqMdlFlg)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).xxChkBox_AB, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).xxChkBox_AB, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).prntDsContrDtlPk_A, (BigDecimal) dsContrDtlMap.get("PRNT_DS_CONTR_DTL_PK"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addedContrMachCnt).dsContrDtlPk_A, (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
        addedMachines++;

        // START 2023/04/21 K.Watanabe [QC#61146, MOD]
        // if (rsltCnt + addedMachines >= sMsgMaxCnt) {
        if (rsltCnt + addedMachines >= searchMaxCnt) {
        // END 2023/04/21 K.Watanabe [QC#61146, MOD]
            return addedMachines;
        }

        ArrayList<BigDecimal> contractedAccessories = new ArrayList<BigDecimal>();

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfigFlg.getValue())) {
            // Get Contracted Accessories
            S21SsmEZDResult dsContrDtlAccResult = query.getDsContrDtlAccInfo(glblCmpyCd, cMsg.dsContrPk.getValue(), (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
            List<Map<String, Object>> dsContrDtlAccResultList = null;

            if (dsContrDtlAccResult != null) {
                int dsContrDtlAccCnt = dsContrDtlAccResult.getQueryResultCount();
                dsContrDtlAccResultList = (List<Map<String, Object>>) dsContrDtlAccResult.getResultObject();

                for (int idxAcc = 0; idxAcc < dsContrDtlAccCnt; idxAcc++) {
                    Map<String, Object> dsContrDtlAccMap = dsContrDtlAccResultList.get(idxAcc);
                    contractedAccessories.add((BigDecimal) dsContrDtlAccMap.get("SVC_MACH_MSTR_PK"));
                }
            }

            // START 2023/04/21 K.Watanabe [QC#61146, MOD]
            // if (rsltCnt + addedMachines >= searchMaxCnt) {
            if (rsltCnt + addedMachines >= searchMaxCnt) {
            // END 2023/04/21 K.Watanabe [QC#61146, MOD]
                return addedMachines;
            }

            S21SsmEZDResult dsContrDtlOthAccResult = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(),
                    // START 2023/04/21 K.Watanabe [QC#61146, MOD]
                    // cMsg.mdseDescShortTxt.getValue(), cMsg.xxConfigFlg.getValue(), sMsg.A.length() + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt
                    cMsg.mdseDescShortTxt.getValue(), cMsg.xxConfigFlg.getValue(), NSAL0310Constant.LIMIT_SEARCH + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt
                    // END 2023/04/21 K.Watanabe [QC#61146, MOD]
                    .getValue(), dsContrTmsg.contrVrsnEffThruDt.getValue(), null, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
            if (dsContrDtlOthAccResult == null) {
                // Continue to next item since current machine has no accessory
                return addedMachines;
            }
            int dsContrDtlOthAccCnt = dsContrDtlOthAccResult.getQueryResultCount();
            if (dsContrDtlOthAccCnt == 0) {
                // No other accessories can be added, move on to next item
                return addedMachines;
            }
            List<Map<String, Object>> dsContrDtlOthAccResultList = (List<Map<String, Object>>) dsContrDtlOthAccResult.getResultObject();

            if (dsContrDtlOthAccResultList == null || (dsContrDtlOthAccResultList != null && dsContrDtlOthAccResultList.size() == 0)) {
                return addedMachines;
            }

            for (int idxAcc = 0; idxAcc < dsContrDtlOthAccCnt; idxAcc++) {
                // START 2023/04/21 K.Watanabe [QC#61146, ADD]
                int incrementIdx = addedContrMachCnt + addedMachines;
                if (incrementIdx > NSAL0310Constant.SMSG_MAX_CNT) {
                    return addedMachines;
                }
                // END 2023/04/21 K.Watanabe [QC#61146, ADD]
                Map<String, Object> rsltAccMap = dsContrDtlOthAccResultList.get(idxAcc);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxDtlStsCd_A, NSAL0310Constant.DTL_STS_CLPS);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).dsAcctNum_A, (String) rsltAccMap.get("BILL_TO_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcConfigMstrPk_A, (BigDecimal) rsltAccMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcMachTpCd_A, (String) rsltAccMap.get("SVC_MACH_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).svcMachMstrPk_A, (BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
                contractAddedAccessories.add((BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).serNum_A, (String) rsltAccMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).mdlNm_A, (String) rsltAccMap.get("T_MDL_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).mdseDescShortTxt_A, (String) rsltAccMap.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).indCurLocNum_A, (String) rsltAccMap.get("IND_CUR_LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).shipToLocNm_A, (String) rsltAccMap.get("SHIP_TO_LOC_NM"));

                String addrAcc = NSAL0310CommonLogic.formatAddress((String) rsltAccMap.get("FIRST_LINE_ADDR"), (String) rsltAccMap.get("CTY_ADDR"), (String) rsltAccMap.get("ST_CD"), (String) rsltAccMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxGenlFldAreaTxt_A, addrAcc);

                String mtrReqMdlAccFlg = (String) rsltAccMap.get("MTR_REQ_MDL_FLG");
                if (ZYPConstant.FLG_ON_Y.equals(mtrReqMdlAccFlg)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxChkBox_AB, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).xxChkBox_AB, ZYPConstant.FLG_OFF_N);
                }
                int contractedIndex = contractedAccessories.indexOf((BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
                if (contractedIndex >= 0) {
                    Map<String, Object> dsContrDtlAccMap = dsContrDtlAccResultList.get(contractedIndex);
                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfigFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.contrInacFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).contrEffFromDt_A, (String) dsContrDtlAccMap.get("CONTR_EFF_FROM_DT"));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).contrEffThruDt_A, (String) dsContrDtlAccMap.get("CONTR_EFF_THRU_DT"));
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).prntDsContrDtlPk_A, (BigDecimal) dsContrDtlAccMap.get("PRNT_DS_CONTR_DTL_PK"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).dsContrDtlPk_A, (BigDecimal) dsContrDtlAccMap.get("DS_CONTR_DTL_PK"));
                } else {
                    // Non-contracted accessory
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).contrEffFromDt_A, cMsg.contrVrsnEffFromDt_H);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).contrEffThruDt_A, cMsg.contrVrsnEffThruDt_H);
                    // Note: Accessory to be added to the machine. Use DS_CONTR_DTL_PK of machine for accessory's PRNT_DS_CONTR_DTL_PK_A
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(incrementIdx).prntDsContrDtlPk_A, (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
                }

                addedMachines++;
                // START 2023/04/21 K.Watanabe [QC#61146, MOD]
                if (rsltCnt + addedMachines >= searchMaxCnt) {
                // END 2023/04/21 K.Watanabe [QC#61146, MOD]
                    break;
                }
            }
        }
        return addedMachines;
    }
    // END   2022/02/03 R.Cabral [QC#59502, MOD]

    private void doProcess_NSAL0310Scrn00_Search_ShipTo(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        // [QC#3017,DEL]START
        //if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
        //    NSAL0310Query query = NSAL0310Query.getInstance();
        //    SHIP_TO_CUSTTMsg tMsg = query.getShipToCust(getGlobalCompanyCode(), cMsg.shipToCustCd.getValue());
        //    if (tMsg == null) {
        //        cMsg.shipToCustCd.setErrorInfo(1, NSAL0310Constant.NSAM0045E, new String[] {"Ship to" });
        //    } else {
        //        ZYPEZDItemValueSetter.setValue(cMsg.shipToLocNm, tMsg.locNm);
        //    }
        //} else {
        //    cMsg.shipToCustCd.setErrorInfo(1, NSAL0310Constant.NWZM0201E);
        //}
        // [QC#3017,DEL]START
    }

    private void doProcess_NSAL0310Scrn00_Select_All(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        // START 2017/03/01 N.Arai [QC#17620, MOD]
//        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);
//
//        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg)) {
//            return;
//        }
//
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            String xxDtlStsCd = cMsg.A.no(i).xxDtlStsCd_A.getValue();
//            String svcMachTpCd = cMsg.A.no(i).svcMachTpCd_A.getValue();
//            if (NSAL0310Constant.DTL_STS_XPND.equals(xxDtlStsCd)) {
//                if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
//                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_AM, ZYPConstant.FLG_ON_Y);
//                } else if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
//                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_AB, ZYPConstant.FLG_ON_Y);
//                }
//            } else if (NSAL0310Constant.DTL_STS_CLPS.equals(xxDtlStsCd)) {
//                if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
//                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_AM, ZYPConstant.FLG_ON_Y);
//                }
//            }
//        }
//
//        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);
        NSAL0310CommonLogic.selectOperation(cMsg, sMsg, ZYPConstant.FLG_ON_Y);
        // END 2017/03/01 N.Arai [QC#17620, MOD]
    }

    private void doProcess_NSAL0310Scrn00_Unselect_All(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        // START 2017/03/01 N.Arai [QC#17620, MOD]
//        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);
//
//        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg)) {
//            return;
//        }
//
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            String xxDtlStsCd = cMsg.A.no(i).xxDtlStsCd_A.getValue();
//            String svcMachTpCd = cMsg.A.no(i).svcMachTpCd_A.getValue();
//            if (NSAL0310Constant.DTL_STS_XPND.equals(xxDtlStsCd)) {
//                if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
//                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_AM, ZYPConstant.FLG_OFF_N);
//                } else if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
//                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_AB, ZYPConstant.FLG_OFF_N);
//                }
//            } else if (NSAL0310Constant.DTL_STS_CLPS.equals(xxDtlStsCd)) {
//                if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
//                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_AM, ZYPConstant.FLG_OFF_N);
//                }
//            }
//        }
//
//        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);
        NSAL0310CommonLogic.selectOperation(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        // END 2017/03/01 N.Arai [QC#17620, MOD]
    }

    // START 2016/02/12 [QC#3019, ADD]
    private void doProcess_NSAL0310Scrn00_TBLColumnSort(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);

        // START 2017/03/01 N.Arai [QC#17620, DEL]
//        if (!NSAL0310CommonLogic.isValidEffectiveDate(cMsg, sMsg)) {
//            return;
//        }
        // END 2017/03/01 N.Arai [QC#17620, DEL]

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            NSAL0310CommonLogic.paginateTableAToItem(cMsg, sMsg, 0);
        }
    }
    // END 2016/02/12 [QC#3019, ADD]

    // START 2017/03/01 N.Arai [QC#17620, ADD]
    private void doProcess_NSAL0310Scrn00_Check_MainMachine(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {

        NSAL0310CommonLogic.copyTableA(cMsg, sMsg);
        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            String stsCd = null;
            int rNum = cMsg.xxRowNum.getValueInt();
            String chkFlg = cMsg.A.no(rNum).xxChkBox_AM.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                stsCd = NSAL0310Constant.DTL_STS_XPND;
                NSAL0310CommonLogic.checkBoxOperation(cMsg, sMsg, rNum, stsCd, chkFlg);
            } else {
                stsCd = NSAL0310Constant.DTL_STS_CLPS;
                NSAL0310CommonLogic.checkBoxOperation(cMsg, sMsg, rNum, stsCd, chkFlg);
            }
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            int startIndex = NSAL0310CommonLogic.convertIndexInPageToIndexInSMsgArrayView(cMsg, sMsg, cMsg.A.no(rNum).svcMachMstrPk_A.getValue());
            int startIndex = NSAL0310CommonLogic.convertIndexInPageToIndexInSMsgArrayView(cMsg, sMsg, cMsg.A.no(rNum).svcMachMstrPk_A.getValue(),
                    cMsg.A.no(rNum).dsContrDtlPk_A.getValue(), cMsg.A.no(rNum).prntDsContrDtlPk_A.getValue());
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
            NSAL0310CommonLogic.paginateTableAToItem(cMsg, sMsg, startIndex);
        }
    }
    // END 2017/03/01 N.Arai [QC#17620, ADD]

    // START 2023/04/21 K.Watanabe [QC#61146, ADD]
    private void doProcess_NSAL0310Scrn00_Download(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg) {
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NSAL0310Constant.BIZ_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        String glblCmpyCd = getGlobalCompanyCode();

        NSAL0310F00FMsg fMsg = new NSAL0310F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        String[] csvHeader = new String[] {"IB ID", "Serial#", "Model", "Item Name", "Ship to", "Ship to Name", "Ship to Address", "Effective From Date", "Effective Thru Date", "Bill Usg", "Service Program" };
        csvOutFile.writeHeader(csvHeader);

        NSAL0310Query query = NSAL0310Query.getInstance();
        DS_CONTRTMsg dsContrTmsg = query.getDsContrForPk(glblCmpyCd, cMsg.dsContrPk.getValue());

        if (dsContrTmsg == null) {
            csvOutFile.close();
            cMsg.setMessageInfo(NSAL0310Constant.NSAM0045E, new String[] {"DS Contract" });
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H)) {
            String dsAcctNm = NSAL0310Query.getInstance().getDsAcct(glblCmpyCd, cMsg.dsAcctNum_H.getValue());
            if (ZYPCommonFunc.hasValue(dsAcctNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, dsAcctNm);
            } else {
                cMsg.dsAcctNum_H.setErrorInfo(1, NSAL0310Constant.NSAM0072E, new String[] {"Customer" });
                return;
            }
        } else {
            cMsg.dsAcctNm.clear();
        }

        int addedContrMachCnt = 0;
        ArrayList<BigDecimal> contractedSvcMachMstrPks = null;
        List<Map<String, Object>> dsContrDtlResultList = null;
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.contrInacFlg.getValue())) {
            S21SsmEZDResult dsContrDtlResult = query.getDsContrDtlMachInfo(glblCmpyCd, cMsg.dsContrPk.getValue());
            contractedSvcMachMstrPks = new ArrayList<BigDecimal>();
            if (dsContrDtlResult != null) {
                int dsContrDtlCnt = dsContrDtlResult.getQueryResultCount();
                dsContrDtlResultList = (List<Map<String, Object>>) dsContrDtlResult.getResultObject();
                for (int idx = 0; idx < dsContrDtlCnt; idx++) {
                    Map<String, Object> dsContrDtlMap = dsContrDtlResultList.get(idx);
                    contractedSvcMachMstrPks.add((BigDecimal) dsContrDtlMap.get("SVC_MACH_MSTR_PK"));
                }
            }
        }

        S21SsmEZDResult rslt = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(), cMsg.mdseDescShortTxt
                .getValue(), cMsg.xxConfigFlg.getValue(), NSAL0310Constant.LIMIT_DOWNLOAD + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt.getValue(),
                dsContrTmsg.contrVrsnEffThruDt.getValue(), null, null);

        if (rslt == null || !rslt.isCodeNormal()) {
            csvOutFile.close();
            cMsg.setMessageInfo(NSAL0310Constant.ZZZM9005W);
            return;
        } else {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            int downloadMaxCnt = NSAL0310Constant.LIMIT_DOWNLOAD;

            if (rsltCnt == 0) {
                csvOutFile.close();
                cMsg.setMessageInfo(NSAL0310Constant.ZZZM9005W);
                return;
            }

            if (downloadMaxCnt > -1 && rsltCnt >= downloadMaxCnt) {
                rsltCnt = downloadMaxCnt;
                cMsg.setMessageInfo(NSAL0310Constant.NZZM0001W);
            }

            BigDecimal currentSvcConfigMstrPk = null;
            for (Integer index = 0; index < rsltCnt; index++) {
                Map<String, Object> rsltMap = rsltList.get(index);

                if (contractedSvcMachMstrPks != null && dsContrDtlResultList != null) {
                    while (contractedSvcMachMstrPks.contains((BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"))) {
                        int idx = contractedSvcMachMstrPks.indexOf((BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                        Map<String, Object> dsContrDtlMap = dsContrDtlResultList.get(idx);
                        int machinesAdded = addPrintContractedMachine(cMsg, sMsg, fMsg, glblCmpyCd, dsContrTmsg, rsltCnt, dsContrDtlMap, csvOutFile);
                        if (machinesAdded > 0) {
                            contractedSvcMachMstrPks.set(idx, BigDecimal.ONE.negate());
                            addedContrMachCnt += machinesAdded;
                        }
                        if (downloadMaxCnt > -1 && rsltCnt + addedContrMachCnt >= downloadMaxCnt) {
                            break;
                        }
                    }
                }
                if (SVC_MACH_TP.MACHINE.equals((String) rsltMap.get("SVC_MACH_TP_CD"))) {
                    currentSvcConfigMstrPk = (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK");
                } else if (currentSvcConfigMstrPk == null || !currentSvcConfigMstrPk.equals((BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"))){ 
                    continue;
                }
                ZYPEZDItemValueSetter.setValue(fMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.serNum, (String) rsltMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, (String) rsltMap.get("T_MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.indCurLocNum, (String) rsltMap.get("IND_CUR_LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToLocNm, (String) rsltMap.get("SHIP_TO_LOC_NM"));

                String firstLineAddr = (String) rsltMap.get("FIRST_LINE_ADDR");
                String secondLineAddr = (String) rsltMap.get("SCD_LINE_ADDR");
                String thirdLineAddr = (String) rsltMap.get("THIRD_LINE_ADDR");
                String fourthLineAddr = (String) rsltMap.get("FRTH_LINE_ADDR");
                String ctyAddr = (String) rsltMap.get("CTY_ADDR");
                String stCd = (String) rsltMap.get("ST_CD");
                String postCd = (String) rsltMap.get("POST_CD");
                String addr = NSAL0310CommonLogic.formatAddress(firstLineAddr, secondLineAddr, thirdLineAddr, fourthLineAddr, ctyAddr, stCd, postCd);

                ZYPEZDItemValueSetter.setValue(fMsg.xxGenlFldAreaTxt, addr);
                ZYPEZDItemValueSetter.setValue(fMsg.mtrReqMdlFlg, (String) rsltMap.get("MTR_REQ_MDL_FLG"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FD, NSAL0310CommonLogic.convertDateEzd8ToDisp(cMsg.contrVrsnEffFromDt_H.getValue()));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TD, NSAL0310CommonLogic.convertDateEzd8ToDisp(cMsg.contrVrsnEffThruDt_H.getValue()));

                csvOutFile.write();
                fMsg.clear();
            }
            csvOutFile.close();
        }
    }

    private int addPrintContractedMachine(NSAL0310CMsg cMsg, NSAL0310SMsg sMsg, NSAL0310F00FMsg fMsg, String glblCmpyCd, DS_CONTRTMsg dsContrTmsg, int rsltCnt, Map<String, Object> dsContrDtlMap, ZYPCSVOutFile csvOutFile) {
        NSAL0310Query query = NSAL0310Query.getInstance();
        int addedMachines = 0;

        S21SsmEZDResult rslt = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(), cMsg.mdseDescShortTxt
                .getValue(), cMsg.xxConfigFlg.getValue(), NSAL0310Constant.LIMIT_DOWNLOAD + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt.getValue(),
                dsContrTmsg.contrVrsnEffThruDt.getValue(), (BigDecimal) dsContrDtlMap.get("SVC_MACH_MSTR_PK"), null);

        if (rslt == null) {
            return addedMachines;
        }

        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
        if (rsltList == null || (rsltList != null && rsltList.size() == 0)) {
            return addedMachines;
        }
        Map<String, Object> rsltMap = rsltList.get(0);
        ArrayList<BigDecimal> contractAddedAccessories = new ArrayList<BigDecimal>();

        ZYPEZDItemValueSetter.setValue(fMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
        contractAddedAccessories.add((BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.serNum, (String) rsltMap.get("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, (String) rsltMap.get("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.indCurLocNum, (String) rsltMap.get("IND_CUR_LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.shipToLocNm, (String) rsltMap.get("SHIP_TO_LOC_NM"));

        String addr = NSAL0310CommonLogic.formatAddress((String) rsltMap.get("FIRST_LINE_ADDR"), (String) rsltMap.get("CTY_ADDR"), (String) rsltMap.get("ST_CD"), (String) rsltMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxGenlFldAreaTxt, addr);
        ZYPEZDItemValueSetter.setValue(fMsg.mtrReqMdlFlg, (String) rsltMap.get("MTR_REQ_MDL_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FD, NSAL0310CommonLogic.convertDateEzd8ToDisp((String) dsContrDtlMap.get("CONTR_EFF_FROM_DT")));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TD, NSAL0310CommonLogic.convertDateEzd8ToDisp((String) dsContrDtlMap.get("CONTR_EFF_THRU_DT")));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseNm, (String) dsContrDtlMap.get("MDSE_NM"));
        csvOutFile.write();
        fMsg.clear();
        addedMachines++;

        if (NSAL0310Constant.LIMIT_DOWNLOAD > -1 && rsltCnt + addedMachines >= NSAL0310Constant.LIMIT_DOWNLOAD) {
            return addedMachines;
        }

        ArrayList<BigDecimal> contractedAccessories = new ArrayList<BigDecimal>();

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfigFlg.getValue())) {
            // Get Contracted Accessories
            S21SsmEZDResult dsContrDtlAccResult = query.getDsContrDtlAccInfo(glblCmpyCd, cMsg.dsContrPk.getValue(), (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK"));
            List<Map<String, Object>> dsContrDtlAccResultList = null;

            if (dsContrDtlAccResult != null) {
                int dsContrDtlAccCnt = dsContrDtlAccResult.getQueryResultCount();
                dsContrDtlAccResultList = (List<Map<String, Object>>) dsContrDtlAccResult.getResultObject();

                for (int idxAcc = 0; idxAcc < dsContrDtlAccCnt; idxAcc++) {
                    Map<String, Object> dsContrDtlAccMap = dsContrDtlAccResultList.get(idxAcc);
                    contractedAccessories.add((BigDecimal) dsContrDtlAccMap.get("SVC_MACH_MSTR_PK"));
                }
            }

            if (NSAL0310Constant.LIMIT_DOWNLOAD > -1 && rsltCnt + addedMachines >= NSAL0310Constant.LIMIT_DOWNLOAD) {
                return addedMachines;
            }

            S21SsmEZDResult dsContrDtlOthAccResult = query.getSvcMachMstr(glblCmpyCd, cMsg.dsAcctNum_H.getValue(), cMsg.serNum.getValue(), cMsg.mdlNm.getValue(), cMsg.xxComnScrColValTxt_H.getValue(), cMsg.xxScrItem29Txt.getValue(),
                    cMsg.mdseDescShortTxt.getValue(), cMsg.xxConfigFlg.getValue(), NSAL0310Constant.LIMIT_DOWNLOAD + 1, cMsg.dsContrPk.getValue(), cMsg.contrInacFlg.getValue(), dsContrTmsg.dsContrCatgCd.getValue(), dsContrTmsg.contrVrsnEffFromDt
                    .getValue(), dsContrTmsg.contrVrsnEffThruDt.getValue(), null, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
            if (dsContrDtlOthAccResult == null) {
                // Continue to next item since current machine has no accessory
                return addedMachines;
            }
            int dsContrDtlOthAccCnt = dsContrDtlOthAccResult.getQueryResultCount();
            if (dsContrDtlOthAccCnt == 0) {
                // No other accessories can be added, move on to next item
                return addedMachines;
            }
            List<Map<String, Object>> dsContrDtlOthAccResultList = (List<Map<String, Object>>) dsContrDtlOthAccResult.getResultObject();

            if (dsContrDtlOthAccResultList == null || (dsContrDtlOthAccResultList != null && dsContrDtlOthAccResultList.size() == 0)) {
                return addedMachines;
            }

            for (int idxAcc = 0; idxAcc < dsContrDtlOthAccCnt; idxAcc++) {
                Map<String, Object> rsltAccMap = dsContrDtlOthAccResultList.get(idxAcc);
                ZYPEZDItemValueSetter.setValue(fMsg.svcMachMstrPk, (BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
                contractAddedAccessories.add((BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.serNum, (String) rsltAccMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, (String) rsltAccMap.get("T_MDL_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, (String) rsltAccMap.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.indCurLocNum, (String) rsltAccMap.get("IND_CUR_LOC_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.shipToLocNm, (String) rsltAccMap.get("SHIP_TO_LOC_NM"));

                String addrAcc = NSAL0310CommonLogic.formatAddress((String) rsltAccMap.get("FIRST_LINE_ADDR"), (String) rsltAccMap.get("CTY_ADDR"), (String) rsltAccMap.get("ST_CD"), (String) rsltAccMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxGenlFldAreaTxt, addrAcc);
                ZYPEZDItemValueSetter.setValue(fMsg.mtrReqMdlFlg, (String) rsltAccMap.get("MTR_REQ_MDL_FLG"));
                int contractedIndex = contractedAccessories.indexOf((BigDecimal) rsltAccMap.get("SVC_MACH_MSTR_PK"));
                if (contractedIndex >= 0) {
                    Map<String, Object> dsContrDtlAccMap = dsContrDtlAccResultList.get(contractedIndex);
                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxConfigFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.contrInacFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FD, NSAL0310CommonLogic.convertDateEzd8ToDisp((String) dsContrDtlAccMap.get("CONTR_EFF_FROM_DT")));
                        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TD, NSAL0310CommonLogic.convertDateEzd8ToDisp((String) dsContrDtlAccMap.get("CONTR_EFF_THRU_DT")));
                    }
                } else {
                    // Non-contracted accessory
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FD, NSAL0310CommonLogic.convertDateEzd8ToDisp(cMsg.contrVrsnEffFromDt_H.getValue()));
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TD, NSAL0310CommonLogic.convertDateEzd8ToDisp(cMsg.contrVrsnEffThruDt_H.getValue()));
                }

                csvOutFile.write();
                fMsg.clear();
                addedMachines++;
                if (NSAL0310Constant.LIMIT_DOWNLOAD > -1 && rsltCnt + addedMachines >= NSAL0310Constant.LIMIT_DOWNLOAD) {
                    break;
                }
            }
        }
        return addedMachines;
    }
    // END 2023/04/21 K.Watanabe [QC#61146, ADD]
}
