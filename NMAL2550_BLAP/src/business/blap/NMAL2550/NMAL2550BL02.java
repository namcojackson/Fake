/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2550;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2550.constant.NMAL2550Constant;
import business.db.COA_ACCTTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.parts.NFZC102001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
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
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#12766
 * 2016/11/08   Hitachi         J.Kim           Update          QC#15616
 * 2016/11/25   Hitachi         Y.Tsuchimoto    Update          QC#16198
 * 2017/07/13   CITS            K.Ogino         Update          QC#19433
 * 2019/03/20   Fujitsu         H.Ikeda         Update          QC#23696
 *</pre>
 */
public class NMAL2550BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            NMAL2550CMsg bizMsg = (NMAL2550CMsg) cMsg;
            NMAL2550SMsg glblMsg = (NMAL2550SMsg) sMsg;

            if ("NMAL2550_INIT".equals(screenAplID)) {
                doProcess_NMAL2550_INIT(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_Company".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_Company(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_Affiliate".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_Affiliate(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_Branch".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_Branch(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_CostCenter".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_CostCenter(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_Account".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_Account(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_Product".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_Product(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_Channel".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_Channel(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_Project".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_Project(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Search_Extension".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Search_Extension(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_CMN_Close(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_TBLColumnSort(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Company_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Company_Setting(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Affiliate_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Affiliate_Setting(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Branch_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Branch_Setting(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_CostCenter_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_CostCenter_Setting(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Account_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Account_Setting(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Product_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Product_Setting(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Channel_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Channel_Setting(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Project_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Project_Setting(bizMsg, glblMsg);

            } else if ("NMAL2550Scrn00_Extension_Setting".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Extension_Setting(bizMsg, glblMsg);
            } else if ("NMAL2550Scrn00_Filter".equals(screenAplID)) {
                doProcess_NMAL2550Scrn00_Filter(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550_INIT(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.appFuncId)) {
            getDefaultDisplay(bizMsg, glblMsg);
            searchName(bizMsg, glblMsg);
        } else {
            bizMsg.setMessageInfo(NMAL2550Constant.NZZM0000E);
        }
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_Company(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.COMPANY);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_Affiliate(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.AFFILIATE);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_Branch(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.BRANCH);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_CostCenter(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.COST_CENTER);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_Account(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.ACCOUNT);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_Product(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.PRODUCT);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_Channel(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.CHANNEL);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_Project(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.PROJECT);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Search_Extension(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndName(bizMsg, glblMsg, NMAL2550Constant.EXTENSION);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_PagePrev(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        pagenation(bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_PageNext(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        pagenation(bizMsg, glblMsg);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_CMN_Clear(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.clear();
        glblMsg.A.clear();
        glblMsg.A.setValidCount(0);
        glblMsg.clear();

    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_CMN_Close(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        checkCodes(bizMsg, glblMsg);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_TBLColumnSort(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            int i = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }

    /**
     * Company Setting
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Company_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.COMPANY);
    }

    private void doProcess_NMAL2550Scrn00_Affiliate_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.AFFILIATE);
    }

    private void doProcess_NMAL2550Scrn00_Branch_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.BRANCH);
    }

    private void doProcess_NMAL2550Scrn00_CostCenter_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.COST_CENTER);
    }

    private void doProcess_NMAL2550Scrn00_Account_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.ACCOUNT);
    }

    private void doProcess_NMAL2550Scrn00_Product_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.PRODUCT);
    }

    private void doProcess_NMAL2550Scrn00_Channel_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.CHANNEL);
    }

    private void doProcess_NMAL2550Scrn00_Project_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.PROJECT);
    }

    private void doProcess_NMAL2550Scrn00_Extension_Setting(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchName(bizMsg, NMAL2550Constant.EXTENSION);
    }

    /**
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2550Scrn00_Filter(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        searchCodeAndNameWithFilter(bizMsg, glblMsg, bizMsg.xxNum.getValueInt());
    }

    /**
     * Search Default Display
     * @param bizMsg NMAL2550CMsg
     * @param glblMsg NMAL2550SMsg
     */
    private void getDefaultDisplay(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {
        DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(bizMsg.appFuncId.getValue());
        if (tMsg == null) {
            bizMsg.setMessageInfo(NMAL2550Constant.NZZM0000E);
        } else {

            ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyDplyFlg_H1, tMsg.coaCmpyDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflDplyFlg_H1, tMsg.coaAfflDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDplyFlg_H1, tMsg.coaBrDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCcDplyFlg_H1, tMsg.coaCcDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctDplyFlg_H1, tMsg.coaAcctDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProdDplyFlg_H1, tMsg.coaProdDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaChDplyFlg_H1, tMsg.coaChDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDplyFlg_H1, tMsg.coaProjDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDplyFlg_H1, tMsg.coaExtnDplyFlg.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.cmbnApiCallFlg, tMsg.cmbnApiCallFlg.getValue());

            if (!ZYPCommonFunc.hasValue(bizMsg.coaCmpyCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaCmpyDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyCd_H1, tMsg.coaCmpyCd.getValue());
                }
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.coaAfflCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaAfflDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflCd_H1, tMsg.coaAfflCd.getValue());
                }
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.coaBrCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaBrDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_H1, tMsg.coaBrCd.getValue());
                }
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.coaCcCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaCcDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaCcCd_H1, tMsg.coaCcCd.getValue());
                }
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.coaAcctCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaAcctDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctCd_H1, tMsg.coaAcctCd.getValue());
                }
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaProdDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H1, tMsg.coaProdCd.getValue());
                }
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.coaChCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaChDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaChCd_H1, tMsg.coaChCd.getValue());
                }
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.coaProjCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaProjDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaProjCd_H1, tMsg.coaProjCd.getValue());
                }
            }

            if (!ZYPCommonFunc.hasValue(bizMsg.coaExtnCd_H1)) {
                if (ZYPConstant.FLG_OFF_N.equals(tMsg.coaExtnDplyFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_H1, tMsg.coaExtnCd.getValue());
                }
            }
        }
    }

    /**
     * Search xxxName from default xxxCode
     * @param bizMsg NMAL2550CMsg
     * @param glblMsg NMAL2550SMsg
     */
    private void searchName(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        // START 2016/11/25 Y.Tsuchimoto [QC#16198,MOD]
        if (ZYPCommonFunc.hasValue(bizMsg.coaCmpyCd_H1)) {
            //ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyDescTxt_H1, ZYPCodeDataUtil.getName(COA_CMPY.class, getGlobalCompanyCode(), bizMsg.coaCmpyCd_H1.getValue()));
            searchName(bizMsg, NMAL2550Constant.COMPANY);
        } else {
            bizMsg.coaCmpyDescTxt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaAfflCd_H1)) {
            //ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflDescTxt_H1, ZYPCodeDataUtil.getName(COA_AFFL.class, getGlobalCompanyCode(), bizMsg.coaAfflCd_H1.getValue()));
            searchName(bizMsg, NMAL2550Constant.AFFILIATE);
        } else {
            bizMsg.coaAfflDescTxt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaBrCd_H1)) {
            //ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt_H1, ZYPCodeDataUtil.getName(COA_BR.class, getGlobalCompanyCode(), bizMsg.coaBrCd_H1.getValue()));
            searchName(bizMsg, NMAL2550Constant.BRANCH);
        } else {
            bizMsg.coaBrDescTxt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaCcCd_H1)) {
            //ZYPEZDItemValueSetter.setValue(bizMsg.coaCcDescTxt_H1, ZYPCodeDataUtil.getName(COA_CC.class, getGlobalCompanyCode(), bizMsg.coaCcCd_H1.getValue()));
            searchName(bizMsg, NMAL2550Constant.COST_CENTER);
        } else {
            bizMsg.coaCcDescTxt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaAcctCd_H1)) {

            COA_ACCTTMsg tMsg = new COA_ACCTTMsg();
            tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            tMsg.coaAcctCd.setValue(bizMsg.coaAcctCd_H1.getValue());
            // START 2016/11/07 J.Kim [QC#15616,ADD]
            tMsg = (COA_ACCTTMsg) S21FastTBLAccessor.findByKey(tMsg);
            // END 2016/11/07 J.Kim [QC#15616,ADD]
            if (tMsg == null) {
                bizMsg.coaAcctDescTxt_H1.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctDescTxt_H1, tMsg.coaAcctDescTxt.getValue());
            }
        } else {
            bizMsg.coaAcctDescTxt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1)) {
            //ZYPEZDItemValueSetter.setValue(bizMsg.coaProdDescTxt_H1, ZYPCodeDataUtil.getName(COA_PROD.class, getGlobalCompanyCode(), bizMsg.coaProdCd_H1.getValue()));
            searchName(bizMsg, NMAL2550Constant.PRODUCT);
        } else {
            bizMsg.coaProdDescTxt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaChCd_H1)) {
            //ZYPEZDItemValueSetter.setValue(bizMsg.coaChDescTxt_H1, ZYPCodeDataUtil.getName(COA_CH.class, getGlobalCompanyCode(), bizMsg.coaChCd_H1.getValue()));
            searchName(bizMsg, NMAL2550Constant.CHANNEL);
        } else {
            bizMsg.coaChDescTxt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaProjCd_H1)) {
            //ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, ZYPCodeDataUtil.getName(COA_PROJ.class, getGlobalCompanyCode(), bizMsg.coaProjCd_H1.getValue()));
            searchName(bizMsg, NMAL2550Constant.PROJECT);
        } else {
            bizMsg.coaProjDescTxt_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaExtnCd_H1)) {
            //ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt_H1, ZYPCodeDataUtil.getName(COA_EXTN.class, getGlobalCompanyCode(), bizMsg.coaExtnCd_H1.getValue()));
            searchName(bizMsg, NMAL2550Constant.EXTENSION);
        } else {
            bizMsg.coaExtnDescTxt_H1.clear();
        }
        // END   2016/11/25 Y.Tsuchimoto [QC#16198,MOD]
    }

    /**
     * Search for Detail
     * @param bizMsg NMAL2550CMsg
     * @param glblMsg NMAL2550SMsg
     * @param int selectColumn
     */
    private void searchCodeAndName(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg, int selectColumn) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getCodeAndName(bizMsg, glblMsg, selectColumn);

        if (ssmResult != null && ssmResult.isCodeNormal()) {

            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NMAL2550Constant.NZZM0001W);
            }

        } else {
            bizMsg.setMessageInfo(NMAL2550Constant.NZZM0000E);
            return;
        }

        bizMsg.xxPageShowFromNum.setValue(0);
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        pagenation(bizMsg, glblMsg);
        bizMsg.xxNum.setValue(selectColumn);
    }

    /**
     * Search with Filter for Detail
     * @param bizMsg NMAL2550CMsg
     * @param glblMsg NMAL2550SMsg
     * @param int selectColumn
     */
    private void searchCodeAndNameWithFilter(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg, int selectColumn) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getCodeAndNameWithFilter(bizMsg, glblMsg, selectColumn);

        if (ssmResult != null && ssmResult.isCodeNormal()) {

            if (ssmResult.getQueryResultCount() > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NMAL2550Constant.NZZM0001W);
            }

        } else {
            bizMsg.setMessageInfo(NMAL2550Constant.NZZM0000E);
            return;
        }

        bizMsg.xxPageShowFromNum.setValue(0);
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        pagenation(bizMsg, glblMsg);
        bizMsg.xxNum.setValue(selectColumn);
    }

    /**
     * Check Input Codes
     * @param bizMsg NMAL2550CMsg
     * @param glblMsg NMAL2550SMsg
     */
    private void checkCodes(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaCmpyDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaCmpyCd_H1)) {
                bizMsg.coaCmpyCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.COMPANY_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.COMPANY);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.COMPANY);
                    bizMsg.coaCmpyCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }

        // START 2016/08/02 K.Kojima [QC#12766,DEL]
        // if
        // (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaAfflDplyFlg_H1.getValue()))
        // {
        // if (!ZYPCommonFunc.hasValue(bizMsg.coaAfflCd_H1)) {
        // bizMsg.coaAfflCd_H1.setErrorInfo(1,
        // NMAL2550Constant.ZZM9000E, new String[]
        // {NMAL2550Constant.AFFILIATE_CODE });
        // return;
        // } else {
        // S21SsmEZDResult ssmResult =
        // NMAL2550Query.getInstance().getName(bizMsg,
        // NMAL2550Constant.AFFILIATE);
        // if (!ssmResult.isCodeNormal()) {
        // setName(bizMsg, null, NMAL2550Constant.AFFILIATE);
        // bizMsg.coaAfflCd_H1.setErrorInfo(1,
        // NMAL2550Constant.NZZM0000E);
        // return;
        // }
        // }
        // }
        // END 2016/08/02 K.Kojima [QC#12766,DEL]

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaBrDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaBrCd_H1)) {
                bizMsg.coaBrCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.BRANCH_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.BRANCH);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.BRANCH);
                    bizMsg.coaBrCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaCcDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaCcCd_H1)) {
                bizMsg.coaCcCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.COST_CENTER_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.COST_CENTER);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.COST_CENTER);
                    bizMsg.coaCcCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaAcctDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaAcctCd_H1)) {
                bizMsg.coaAcctCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.ACCOUNT_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.ACCOUNT);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.ACCOUNT);
                    bizMsg.coaAcctCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaProdDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaProdCd_H1)) {
                bizMsg.coaProdCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.PRODUCT_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.PRODUCT);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.PRODUCT);
                    bizMsg.coaProdCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaChDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaChCd_H1)) {
                bizMsg.coaChCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.CHANNEL_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.CHANNEL);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.CHANNEL);
                    bizMsg.coaChCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }

        // START 2016/08/02 K.Kojima [QC#12766,ADD]
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaAfflDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaAfflCd_H1)) {
                bizMsg.coaAfflCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.AFFILIATE_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.AFFILIATE);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.AFFILIATE);
                    bizMsg.coaAfflCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }
        // END 2016/08/02 K.Kojima [QC#12766,ADD]

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaProjDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaProjCd_H1)) {
                bizMsg.coaProjCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.PROJECT_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.PROJECT);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.PROJECT);
                    bizMsg.coaProjCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaExtnDplyFlg_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.coaExtnCd_H1)) {
                bizMsg.coaExtnCd_H1.setErrorInfo(1, NMAL2550Constant.ZZM9000E, new String[] {NMAL2550Constant.EXTENSION_CODE });
                return;
            } else {
                S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, NMAL2550Constant.EXTENSION);
                if (!ssmResult.isCodeNormal()) {
                    setName(bizMsg, null, NMAL2550Constant.EXTENSION);
                    bizMsg.coaExtnCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
                    return;
                }
            }
        }

        // GL Code Combination Check API NFZC102001
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.cmbnApiCallFlg.getValue())) {
            NFZC102001 afzc102001 = new NFZC102001();
            NFZC102001PMsg paramMsg = new NFZC102001PMsg();

            paramMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
            paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
            paramMsg.xxArcsApiChkFlg.setValue("");
            // QC#19433 Start
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaCmpyDplyFlg_H1.getValue())) {
                paramMsg.coaCmpyCd.setValue(bizMsg.coaCmpyCd_H1.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaAfflDplyFlg_H1.getValue())) {
                paramMsg.coaAfflCd.setValue(bizMsg.coaAfflCd_H1.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaBrDplyFlg_H1.getValue())) {
                paramMsg.coaBrCd.setValue(bizMsg.coaBrCd_H1.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaCcDplyFlg_H1.getValue())) {
                paramMsg.coaCcCd.setValue(bizMsg.coaCcCd_H1.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaAcctDplyFlg_H1.getValue())) {
                paramMsg.coaAcctCd.setValue(bizMsg.coaAcctCd_H1.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaProdDplyFlg_H1.getValue())) {
                paramMsg.coaProdCd.setValue(bizMsg.coaProdCd_H1.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaChDplyFlg_H1.getValue())) {
                paramMsg.coaChCd.setValue(bizMsg.coaChCd_H1.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaProjDplyFlg_H1.getValue())) {
                paramMsg.coaProjCd.setValue(bizMsg.coaProjCd_H1.getValue());
            }
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.coaExtnDplyFlg_H1.getValue())) {
                paramMsg.coaExtnCd.setValue(bizMsg.coaExtnCd_H1.getValue());
            }
            // QC#19433 End
            paramMsg.resrcObjNm.setValue(bizMsg.appFuncId.getValue());

            afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

            // START 2019/03/20 H.Ikeda [QC#23696, MOD]
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
//
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                if (msgPrms != null && msgPrms.length > 0) {
//                    ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, msgPrms[0]);
//                }
//                bizMsg.setMessageInfo(msgId, msgPrms);
//                return;
//            }
            if (paramMsg.xxMsgIdList != null) {
                for (int i = 0; i < paramMsg.xxMsgIdList.getValidCount(); i++) {
                    String msgId = paramMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    String coaTpCd = paramMsg.xxMsgIdList.no(i).xxCoaTpCd.getValue();
                    String[] prmTxts = new String[1];
                    prmTxts[0]= paramMsg.xxMsgIdList.no(i).xxMsgPrmTxt_0.getValue();

                    if (ZYPCommonFunc.hasValue(msgId)) {
                        if (NMAL2550Constant.COAID_COA_CMPY.equals(coaTpCd)) {
                            bizMsg.coaCmpyCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_CMPY_CD);
                        }
                        else if (NMAL2550Constant.COAID_COA_BR.equals(coaTpCd)) {
                            bizMsg.coaBrCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_BR_CD);
                        }
                        else if (NMAL2550Constant.COAID_COA_CC.equals(coaTpCd)) {
                            bizMsg.coaCcCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_CC_CD);
                        }
                        else if (NMAL2550Constant.COAID_COA_ACCT.equals(coaTpCd)) {
                            bizMsg.coaAcctCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_ACCT_CD);
                        }
                        else if (NMAL2550Constant.COAID_COA_PROD.equals(coaTpCd)) {
                            bizMsg.coaProdCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_PROD_CD);
                        }
                        else if (NMAL2550Constant.COAID_COA_CH.equals(coaTpCd)) {
                            bizMsg.coaChCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_CH_CD);
                        }
                        else if (NMAL2550Constant.COAID_COA_AFFL.equals(coaTpCd)) {
                            bizMsg.coaAfflCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_AFFL_CD);
                        }
                        else if (NMAL2550Constant.COAID_COA_PROJ.equals(coaTpCd)) {
                            bizMsg.coaProjCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_PROJ_CD);
                        }
                        else if (NMAL2550Constant.COAID_COA_EXTN.equals(coaTpCd)) {
                            bizMsg.coaExtnCd_H1.setErrorInfo(1, msgId);
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxMsgPrmTxt, NMAL2550Constant.COA_EXTN_CD);
                        }
                        bizMsg.setMessageInfo(msgId, prmTxts);
                        return;
                    }
                }
            }
            // END   2019/03/20 H.Ikeda [QC#23696, MOD]
        }
    }

    /**
     * Paging
     * @param bizMsg NMAL2550CMsg
     * @param glblMsg NMAL2550SMsg
     */
    private void pagenation(NMAL2550CMsg bizMsg, NMAL2550SMsg glblMsg) {

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to page nation items
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());
    }

    /**
     * Get Default Display COA Information
     * @param bizAppId String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    private DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(String appFuncId) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        tMsg.appFuncId.setValue(appFuncId);

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * Search
     * @param bizMsg NMAL2550CMsg
     * @param glblMsg NMAL2550SMsg
     * @param int selectColumn
     */
    private void searchName(NMAL2550CMsg bizMsg, int selectColumn) {

        S21SsmEZDResult ssmResult = NMAL2550Query.getInstance().getName(bizMsg, selectColumn);

        if (!ssmResult.isCodeNormal()) {
            setName(bizMsg, null, selectColumn);

            if (NMAL2550Constant.COMPANY == selectColumn) {
                bizMsg.coaCmpyCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }
            if (NMAL2550Constant.AFFILIATE == selectColumn) {
                bizMsg.coaAfflCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }
            if (NMAL2550Constant.BRANCH == selectColumn) {
                bizMsg.coaBrCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }
            if (NMAL2550Constant.COST_CENTER == selectColumn) {
                bizMsg.coaCcCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }
            if (NMAL2550Constant.ACCOUNT == selectColumn) {
                bizMsg.coaAcctCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }
            if (NMAL2550Constant.PRODUCT == selectColumn) {
                bizMsg.coaProdCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }
            if (NMAL2550Constant.CHANNEL == selectColumn) {
                bizMsg.coaChCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }
            if (NMAL2550Constant.PROJECT == selectColumn) {
                bizMsg.coaProjCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }
            if (NMAL2550Constant.EXTENSION == selectColumn) {
                bizMsg.coaExtnCd_H1.setErrorInfo(1, NMAL2550Constant.NZZM0000E);
            }

            return;
        }

        NMAL2550_ASMsg asMsg = (NMAL2550_ASMsg) ssmResult.getResultObject();

        // START 2016/11/25 Y.Tsuchimoto [QC#16198,MOD]
        setName(bizMsg, asMsg.dtlDescTxt_A1.getValue(), selectColumn);
        // END   2016/11/25 Y.Tsuchimoto [QC#16198,MOD]
    }

    private void setName(NMAL2550CMsg bizMsg, String dtlNm, int selectColumn) {

        if (NMAL2550Constant.COMPANY == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCmpyDescTxt_H1, dtlNm);
        }
        if (NMAL2550Constant.AFFILIATE == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAfflDescTxt_H1, dtlNm);
        }
        if (NMAL2550Constant.BRANCH == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt_H1, dtlNm);
        }
        if (NMAL2550Constant.COST_CENTER == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaCcDescTxt_H1, dtlNm);
        }
        if (NMAL2550Constant.ACCOUNT == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaAcctDescTxt_H1, dtlNm);
        }
        if (NMAL2550Constant.PRODUCT == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProdDescTxt_H1, dtlNm);
        }
        if (NMAL2550Constant.CHANNEL == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaChDescTxt_H1, dtlNm);
        }
        if (NMAL2550Constant.PROJECT == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, dtlNm);
        }
        if (NMAL2550Constant.EXTENSION == selectColumn) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt_H1, dtlNm);
        }
    }
}
