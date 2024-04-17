/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0300.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/24   Hitachi         T.Kanasaka      Create          QC#4806
 *</pre>
 */
public class NSAL0300DisplayControllBean {

    /** start page index */
    private BigDecimal pageFromNum = BigDecimal.ONE;

    /** End Customer Opened */
    private boolean endCustomerOpenFlg = false;

    /** Fleet Aggregate Opened */
    private boolean fleetAggregateOpenFlg = false;

    /** Machine Header List Opened */
    private boolean machineListOpenFlg = false;

    /** Filter Item */
    private String filterItem;

    /** Filter Condition */
    private String filterCondition;

    /** Filter Value */
    private String filterValue;

    /** Filtered Machine List */
    private List<BigDecimal> filteredDsContrDtlPkList = new ArrayList<BigDecimal>();

    /** Opend Machine List */
    private List<BigDecimal> openedDsContrDtlPkList = new ArrayList<BigDecimal>();

    /** Opend Machine Base List */
    private List<BigDecimal> openedBaseDsContrDtlPkList = new ArrayList<BigDecimal>();

    /** Opend Machine Overage List */
    private List<BigDecimal> openedOverageDsContrDtlPkList = new ArrayList<BigDecimal>();

    /** Opend Machine Billing Counter List */
    private List<BigDecimal> openedDsContrBllgMtrPkList = new ArrayList<BigDecimal>();

    /**
     * setPageFromNum
     * @param pageFromNum BigDecimal
     */
    public void setPageFromNum(BigDecimal pageFromNum) {
        if (pageFromNum != null) {
            this.pageFromNum = pageFromNum;
        }
    }

    /**
     * getPageFromNum
     * @return pageFromNum
     */
    public BigDecimal getPageFromNum() {
        return this.pageFromNum;
    }

    /**
     * setEndCustomerOpenFlg
     * @param endCustomerOpenFlg boolean
     */
    public void setEndCustomerOpenFlg(boolean endCustomerOpenFlg) {
        this.endCustomerOpenFlg = endCustomerOpenFlg;
    }

    /**
     * getEndCustomerOpenFlg
     * @return endCustomerOpenFlg
     */
    public boolean getEndCustomerOpenFlg() {
        return this.endCustomerOpenFlg;
    }

    /**
     * setFleetAggregateOpenFlg
     * @param fleetAggregateOpenFlg boolean
     */
    public void setFleetAggregateOpenFlg(boolean fleetAggregateOpenFlg) {
        this.fleetAggregateOpenFlg = fleetAggregateOpenFlg;
    }

    /**
     * getFleetAggregateOpenFlg
     * @return fleetAggregateOpenFlg
     */
    public boolean getFleetAggregateOpenFlg() {
        return this.fleetAggregateOpenFlg;
    }

    /**
     * setMachineListOpenFlg
     * @param machineListOpenFlg boolean
     */
    public void setMachineListOpenFlg(boolean machineListOpenFlg) {
        this.machineListOpenFlg = machineListOpenFlg;
    }

    /**
     * getMachineListOpenFlg
     * @return machineListOpenFlg
     */
    public boolean getMachineListOpenFlg() {
        return this.machineListOpenFlg;
    }

    /**
     * setFilterItem
     * @param filterItem String
     */
    public void setFilterItem(String filterItem) {
        this.filterItem = filterItem;
    }

    /**
     * getFilterItem
     * @return filterItem
     */
    public String getFilterItem() {
        return this.filterItem;
    }

    /**
     * setFilterCondition
     * @param filterCondition String
     */
    public void setFilterCondition(String filterCondition) {
        this.filterCondition = filterCondition;
    }

    /**
     * getFilterCondition
     * @return filterCondition
     */
    public String getFilterCondition() {
        return this.filterCondition;
    }

    /**
     * setFilterValue
     * @param filterValue String
     */
    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    /**
     * getFilterValue
     * @return filterValuev
     */
    public String getFilterValue() {
        return this.filterValue;
    }

    /**
     * addFilteredDsContrDtlPkList
     * @param dsContrDtlPk BigDecimal
     */
    public void addFilteredDsContrDtlPkList(BigDecimal dsContrDtlPk) {
        if (!filteredDsContrDtlPkList.contains(dsContrDtlPk)) {
            filteredDsContrDtlPkList.add(dsContrDtlPk);
        }
    }

    /**
     * getFilteredDsContrDtlPkList
     * @return filteredDsContrDtlPkList
     */
    public List<BigDecimal> getFilteredDsContrDtlPkList() {
        return this.filteredDsContrDtlPkList;
    }

    /**
     * addOpenedDsContrDtlPkList
     * @param dsContrDtlPk BigDecimal
     */
    public void addOpenedDsContrDtlPkList(BigDecimal dsContrDtlPk) {
        if (!openedDsContrDtlPkList.contains(dsContrDtlPk)) {
            openedDsContrDtlPkList.add(dsContrDtlPk);
        }
    }

    /**
     * getOpenedDsContrDtlPkList
     * @return openedDsContrDtlPkList
     */
    public List<BigDecimal> getOpenedDsContrDtlPkList() {
        return this.openedDsContrDtlPkList;
    }

    /**
     * addOpenedBaseDsContrDtlPkList
     * @param dsContrDtlPk BigDecimal
     */
    public void addOpenedBaseDsContrDtlPkList(BigDecimal dsContrDtlPk) {
        if (!openedBaseDsContrDtlPkList.contains(dsContrDtlPk)) {
            openedBaseDsContrDtlPkList.add(dsContrDtlPk);
        }
    }

    /**
     * getOpenedBaseDsContrDtlPkList
     * @return openedBaseDsContrDtlPkList
     */
    public List<BigDecimal> getOpenedBaseDsContrDtlPkList() {
        return this.openedBaseDsContrDtlPkList;
    }

    /**
     * addOpenedOverageDsContrDtlPkList
     * @param dsContrDtlPk BigDecimal
     */
    public void addOpenedOverageDsContrDtlPkList(BigDecimal dsContrDtlPk) {
        if (!openedOverageDsContrDtlPkList.contains(dsContrDtlPk)) {
            openedOverageDsContrDtlPkList.add(dsContrDtlPk);
        }
    }

    /**
     * getOpenedOverageDsContrDtlPkList
     * @return openedOverageDsContrDtlPkList
     */
    public List<BigDecimal> getOpenedOverageDsContrDtlPkList() {
        return this.openedOverageDsContrDtlPkList;
    }

    /**
     * addOpenedDsContrBllgMtrPkList
     * @param dsContrBllgMtrPk BigDecimal
     */
    public void addOpenedDsContrBllgMtrPkList(BigDecimal dsContrBllgMtrPk) {
        if (!openedDsContrBllgMtrPkList.contains(dsContrBllgMtrPk)) {
            openedDsContrBllgMtrPkList.add(dsContrBllgMtrPk);
        }
    }

    /**
     * getOpenedDsContrBllgMtrPkList
     * @return openedDsContrBllgMtrPkList
     */
    public List<BigDecimal> getOpenedDsContrBllgMtrPkList() {
        return this.openedDsContrBllgMtrPkList;
    }
}
