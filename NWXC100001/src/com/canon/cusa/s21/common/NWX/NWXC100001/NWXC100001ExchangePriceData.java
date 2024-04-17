/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.util.List;

/**
 * <pre>
 * The interface to acquire the list of the converted amount of money is offered.
 * 
 * It uses it by the conversion processing of the amount of the currency of the
 * {@link com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001Exchange NWXC100001Exchange} class.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2013   Fujitsu         D.Yanagisawa    CREATE          #MEX-LC004
 * </pre>
 */
public interface NWXC100001ExchangePriceData {

    /**
     * <pre>
     * The amount of money in which the amount of the currency is converted is
     * returned by the list form.
     * </pre>
     * @return Amount of money of conversion list
     */
    List<NWXC100001ExchangeAmount> getAmountList();

}
