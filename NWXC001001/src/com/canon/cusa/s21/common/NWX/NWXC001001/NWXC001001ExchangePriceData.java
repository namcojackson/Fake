/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.util.List;

/**
 * <pre>
 * The interface to acquire the list of the converted amount of money is offered.
 * 
 * It uses it by the conversion processing of the amount of the currency of the
 * {@link com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange NWXC001001Exchange} class.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/25/2009   Fujitsu         S.Sugino        Create          N/A
 * </pre>
 */
public interface NWXC001001ExchangePriceData {

    /**
     * <pre>
     * The amount of money in which the amount of the currency is converted is
     * returned by the list form.
     * </pre>
     * @return Amount of money of conversion list
     */
    List<NWXC001001ExchangeAmount> getAmountList();

}
