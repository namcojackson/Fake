/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB136001;

import java.math.BigDecimal;
import java.util.Comparator;

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
public class ComparatorPrDetail implements Comparator<PrInfoBean> {

    /**
     * @param info1 PrInfoBean
     * @param info2 PrInfoBean
     * @return compare
     */
    @Override
    public int compare(PrInfoBean info1, PrInfoBean info2) {
        if ((info1.getRuleInfo() == null) || (info2.getRuleInfo() == null)) {
            if ((info1.getRuleInfo() == null) && (info2.getRuleInfo() == null)) {
                return 0;
            } else if (info1.getRuleInfo() == null) {
                return 1;
            } else {
                return -1;
            }
        }
        int ret = 0;
        ret = (new BigDecimal(info1.getRuleInfo().insrcPrtyNum.getValue())).compareTo((new BigDecimal(info2.getRuleInfo().insrcPrtyNum.getValue())));
        if (ret != 0) {
            return ret;
        }
        ret = info1.getDetailInfo().rqstRcvDt.getValue().compareToIgnoreCase(info2.getDetailInfo().rqstRcvDt.getValue());
        if (ret != 0) {
            return ret;
        }
        ret = info1.getDetailInfo().rqstRcvTm.getValue().compareToIgnoreCase(info2.getDetailInfo().rqstRcvTm.getValue());
        if (ret != 0) {
            return ret;
        }
        ret = info1.getDetailInfo().prchReqNum.getValue().compareToIgnoreCase(info2.getDetailInfo().prchReqNum.getValue());
        if (ret != 0) {
            return ret;
        }
        return info1.getDetailInfo().prchReqLineNum.getValue().compareToIgnoreCase(info2.getDetailInfo().prchReqLineNum.getValue());
    }
}
