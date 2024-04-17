/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC270001;

import java.util.List;

/**
 *<pre>
 * NMZC2700 Territory Rule Validation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         C.Yokoi         Create          CSA-QC#5658
 *</pre>
 */
public class NWZC270001Bean {

    /** List<NWZC270001_TrtyRuleBean> */
    private List<NWZC270001_TrtyRuleBean> trtyRuleBeanList;


    /**
     * getTrtyRuleBeanList
     * @return List<NWZC270001_TrtyRuleBean>
     */
    public List<NWZC270001_TrtyRuleBean> getTrtyRuleBeanList() {
        return this.trtyRuleBeanList;
    }

    /**
     * trtyRuleBeanList
     * @param trtyRuleBeanList List<NWZC270001_TrtyRuleBean>
     */
    public void setTrtyRuleBeanList(List<NWZC270001_TrtyRuleBean> trtyRuleBeanList) {
        this.trtyRuleBeanList = trtyRuleBeanList;
    }
}
