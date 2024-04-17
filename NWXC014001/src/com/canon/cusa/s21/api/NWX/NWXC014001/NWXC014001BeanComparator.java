/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWX.NWXC014001;

import java.io.Serializable;
import java.util.Comparator;

/**
 * <pre>
 * Price Rule Derivation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         Y.Kanefusa      Create          N/A
 * </pre>
 */
public class NWXC014001BeanComparator implements Comparator<NWXC014001prcRuleWrkBean>, Serializable {
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(NWXC014001prcRuleWrkBean t1, NWXC014001prcRuleWrkBean t2) {
        if (t1.getPrcRuleCatgCd().compareTo(t2.getPrcRuleCatgCd()) > 0) {
            return 1;
        } else if (t1.getPrcRuleCatgCd().compareTo(t2.getPrcRuleCatgCd()) == 0) {
            if (t1.getConfigCatgCd().compareTo(t2.getConfigCatgCd()) > 0) {
                return -1;
            } else if (t1.getConfigCatgCd().compareTo(t2.getConfigCatgCd()) == 0) {
                if (t1.getTrxLineNum().compareTo(t2.getTrxLineNum()) > 0) {
                    return 1;
                } else if (t1.getTrxLineNum().compareTo(t2.getTrxLineNum()) == 0) {
                    if (t1.getTrxLineSubNum().compareTo(t2.getTrxLineSubNum()) > 0) {
                        return 1;
                    } else if (t1.getTrxLineSubNum().compareTo(t2.getTrxLineSubNum()) == 0) {
                        if (t1.getPrcRulePrcdSqNum().compareTo(t2.getPrcRulePrcdSqNum()) > 0) {
                            return 1;
                        } else if (t1.getPrcRulePrcdSqNum().compareTo(t2.getPrcRulePrcdSqNum()) == 0) {
                            if (t1.getPrcRuleHdrPk().compareTo(t2.getPrcRuleHdrPk()) > 0) {
                                return 1;
                            } else if (t1.getPrcRuleHdrPk().compareTo(t2.getPrcRuleHdrPk()) == 0) {
                                return 0;
                            } else {
                                return -1;
                            }
                        } else {
                            return -1;
                        }
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }
}
