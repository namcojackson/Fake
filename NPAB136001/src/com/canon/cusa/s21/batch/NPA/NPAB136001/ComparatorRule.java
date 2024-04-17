/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB136001;

import java.util.Comparator;

import business.db.INSRC_RULETMsg;

/**
 * <pre>
 * Business ID : NPAB1360 Insourcing Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class ComparatorRule implements Comparator<INSRC_RULETMsg> {

    /**
     * @param rule1 INSRC_RULETMsg
     * @param rule2 INSRC_RULETMsg
     * @return compare
     */
    @Override
    public int compare(INSRC_RULETMsg rule1, INSRC_RULETMsg rule2) {
        int ret = 0;
        ret = rule1.prchReqSrcTpCd.getValue().compareToIgnoreCase(rule2.prchReqSrcTpCd.getValue());
        if (ret != 0) {
            return ret;
        }
        ret = rule1.destLocTpCd.getValue().compareToIgnoreCase(rule2.destLocTpCd.getValue());
        if (ret != 0) {
            return ret;
        }
        ret = rule1.destRtlWhCatgCd.getValue().compareToIgnoreCase(rule2.destRtlWhCatgCd.getValue());
        if (ret != 0) {
            return ret;
        }
        ret = rule1.techLineBizTpCd.getValue().compareToIgnoreCase(rule2.techLineBizTpCd.getValue());
        if (ret != 0) {
            return ret;
        }
        return rule1.destInvtyLocCd.getValue().compareToIgnoreCase(rule2.destInvtyLocCd.getValue());
    }
}
