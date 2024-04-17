package com.canon.cusa.s21.batch.NSA.NSAB004001;

import java.util.List;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/11   Hitachi         S.Moriai        Create          QC#62117
 * </pre>
 */
public class MissingStartMeterMailHeaderData {

    /** Sales Date */
    private String salesDate;

    /** Detail List */
    private List<MissingStartMeterMailDetailData>  detailList;
    /**
     * @return salesDate
     */
    public String getSalesDate() {
        return salesDate;
    }

    /**
     * @param salesDate String
     */
    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public List<MissingStartMeterMailDetailData> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<MissingStartMeterMailDetailData> detailList) {
        this.detailList = detailList;
    }
}
