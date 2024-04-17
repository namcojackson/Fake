/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;

/**
 * <pre>
 * Common function for Sales Rep
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/04/24   Fujitsu         Hd.Sugawara     Create          QC#31125,QC#31126
 * </pre>
 */
public class NWXC150001SalesRep {

    /** Default serial Version */
    private static final long serialVersionUID = 1L;

    /**
     * updateToLatestSalesRep
     * @param cpoUpdApiPMsg NWZC150001PMsg
     * @return NWZC150001PMsg
     */
    public static NWZC150001PMsg updateToLatestSalesRep(NWZC150001PMsg cpoUpdApiPMsg) {

        String glblCmpyCd = cpoUpdApiPMsg.glblCmpyCd.getValue();
        String slsDt = cpoUpdApiPMsg.slsDt.getValue();
        Map<String, String> tocCdMap = new HashMap<String, String>();

        // Header
        if (ZYPCommonFunc.hasValue(cpoUpdApiPMsg.slsRepCd)) {
            String oldToc = cpoUpdApiPMsg.slsRepCd.getValue();

            if (ZYPCommonFunc.hasValue(oldToc)) {
                String newToc = getNewToc(glblCmpyCd, slsDt, oldToc, tocCdMap);

                if (ZYPCommonFunc.hasValue(newToc) && !oldToc.equals(newToc)) {
                    ZYPEZDItemValueSetter.setValue(cpoUpdApiPMsg.slsRepCd, newToc);
                }
            }
        }

        // Line Config
        if (cpoUpdApiPMsg.A != null && cpoUpdApiPMsg.A.getValidCount() > 0) {
            for (int i = 0; i < cpoUpdApiPMsg.A.getValidCount(); i++) {
                NWZC150001_APMsg cpoDtl = cpoUpdApiPMsg.A.no(i);
                String oldToc = cpoDtl.slsRepOrSlsTeamTocCd_A1.getValue();

                if (ZYPCommonFunc.hasValue(oldToc)) {
                    String newToc = getNewToc(glblCmpyCd, slsDt, oldToc, tocCdMap);

                    if (ZYPCommonFunc.hasValue(newToc) && !oldToc.equals(newToc)) {
                        ZYPEZDItemValueSetter.setValue(cpoDtl.slsRepOrSlsTeamTocCd_A1, newToc);
                    }
                }
            }
        }

        // RMA
        if (cpoUpdApiPMsg.rtnDtl != null && cpoUpdApiPMsg.rtnDtl.getValidCount() > 0) {
            for (int i = 0; i < cpoUpdApiPMsg.rtnDtl.getValidCount(); i++) {
                NWZC150001_rtnDtlPMsg rtnDtl = cpoUpdApiPMsg.rtnDtl.no(i);
                String oldToc = rtnDtl.slsRepOrSlsTeamTocCd_B1.getValue();

                if (ZYPCommonFunc.hasValue(oldToc)) {
                    String newToc = getNewToc(glblCmpyCd, slsDt, oldToc, tocCdMap);

                    if (ZYPCommonFunc.hasValue(newToc) && !oldToc.equals(newToc)) {
                        ZYPEZDItemValueSetter.setValue(rtnDtl.slsRepOrSlsTeamTocCd_B1, newToc);
                    }
                }
            }
        }

        // Sales Credit
        if (cpoUpdApiPMsg.cpoSlsCr != null && cpoUpdApiPMsg.cpoSlsCr.getValidCount() > 0) {
            for (int i = 0; i < cpoUpdApiPMsg.cpoSlsCr.getValidCount(); i++) {
                NWZC150001_cpoSlsCrPMsg cpoSlsCr = cpoUpdApiPMsg.cpoSlsCr.no(i);

                if (!ZYPConstant.FLG_ON_Y.equals(cpoSlsCr.slsCrQuotFlg.getValue())) {
                    continue;
                }

                String oldToc = cpoSlsCr.slsRepCd.getValue();

                if (ZYPCommonFunc.hasValue(oldToc)) {
                    String newToc = getNewToc(glblCmpyCd, slsDt, oldToc, tocCdMap);

                    if (ZYPCommonFunc.hasValue(newToc) && !oldToc.equals(newToc)) {
                        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsRepCd, newToc);
                    }
                }
            }
        }

        return cpoUpdApiPMsg;
    }

    /**
     * getNewToc
     * @param glblCmpyCd String
     * @param slsDt String
     * @param oldToc String
     * @param tocCdMap Map<String, String>
     * @return String
     */
    private static String getNewToc(String glblCmpyCd, String slsDt, String oldToc, Map<String, String> tocCdMap) {
        String newToc = null;

        if (tocCdMap.containsKey(oldToc)) {
            newToc = tocCdMap.get(oldToc);
        } else {
            newToc = getLatestSlsRepInfo(glblCmpyCd, slsDt, oldToc);
            tocCdMap.put(oldToc, newToc);
        }

        return newToc;
    }

    /**
     * getLatestSlsRepInfo
     * @param glblCmpyCd Sring
     * @param slsDt String
     * @param tocCd String
     * @return
     */
    private static String getLatestSlsRepInfo(String glblCmpyCd, String slsDt, String tocCd) {
        String result = NWXC150001Query.getInstance().getLatestSlsRepInfo(glblCmpyCd, slsDt, tocCd);

        return result;
    }
}
