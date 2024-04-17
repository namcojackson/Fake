package business.blap.NMAL3200;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCItem;
import parts.common.EZDItemAttribute;
import parts.common.EZDTItem;
import parts.common.EZDTStringItem;

/**
 * <pre>
 * MktgMapWrkBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/30/2016   CITS            K.Ogino         Create          N/A
 * </pre>
 */
public class MktgMapWrkBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** rptRqstHdrPk */
    private BigDecimal rptRqstHdrPk = null;

    /** attr */
    private EZDItemAttribute attr = null;

    /** item */
    private EZDTItem item = null;

    /** hdrLineList */
    private List<String> hdrLineList = null;

    /** upldLineList */
    private List<String> upldLineList = null;

    /** dbStr */
    private String dbStr = null;

    /** cItem */
    private EZDCItem cItem = null;

    /** upldCsvRqstCmntTxtItem */
    private EZDTStringItem upldCsvRqstCmntTxtItem = null;

    /**
     * @return rptRqstHdrPk
     */
    public BigDecimal getRptRqstHdrPk() {
        return rptRqstHdrPk;
    }

    /**
     * @param rptRqstHdrPk set rptRqstHdrPk
     */
    public void setRptRqstHdrPk(BigDecimal rptRqstHdrPk) {
        this.rptRqstHdrPk = rptRqstHdrPk;
    }

    /**
     * Constructor
     * @param attr EZDItemAttribute
     * @param item EZDTItem
     * @param hdrLineList List<String>
     * @param upldLineList List<String>
     * @param dbStr String
     * @param cItem EZDCItem
     * @param upldCsvRqstCmntTxtItem EZDTStringItem
     */
    public MktgMapWrkBean(EZDItemAttribute attr, EZDTItem item, List<String> hdrLineList, List<String> upldLineList, String dbStr, EZDCItem cItem, EZDTStringItem upldCsvRqstCmntTxtItem) {
        this.attr = attr;
        this.item = item;
        this.hdrLineList = hdrLineList;
        this.upldLineList = upldLineList;
        this.dbStr = dbStr;
        this.cItem = cItem;
        this.upldCsvRqstCmntTxtItem = upldCsvRqstCmntTxtItem;
    }

    /**
     * @return attr
     */
    public EZDItemAttribute getAttr() {
        return attr;
    }

    /**
     * @param attr set attr
     */
    public void setAttr(EZDItemAttribute attr) {
        this.attr = attr;
    }

    /**
     * @return item
     */
    public EZDTItem getItem() {
        return item;
    }

    /**
     * @param item set item
     */
    public void setItem(EZDTItem item) {
        this.item = item;
    }

    /**
     * @return hdrLineList
     */
    public List<String> getHdrLineList() {
        return hdrLineList;
    }

    /**
     * @param hdrLineList set hdrLineList
     */
    public void setHdrLineList(List<String> hdrLineList) {
        this.hdrLineList = hdrLineList;
    }

    /**
     * @return upldLineList
     */
    public List<String> getUpldLineList() {
        return upldLineList;
    }

    /**
     * @param upldLineList set upldLineList
     */
    public void setUpldLineList(List<String> upldLineList) {
        this.upldLineList = upldLineList;
    }

    /**
     * @return dbStr
     */
    public String getDbStr() {
        return dbStr;
    }

    /**
     * @param dbStr set dbStr
     */
    public void setDbStr(String dbStr) {
        this.dbStr = dbStr;
    }

    /**
     * @return cItem
     */
    public EZDCItem getCItem() {
        return cItem;
    }

    /**
     * @param citem EZDCItem
     */
    public void setCItem(EZDCItem citem) {
        this.cItem = citem;
    }

    /**
     * @return upldCsvRqstCmntTxtItem
     */
    public EZDTStringItem getUpldCsvRqstCmntTxtItem() {
        return upldCsvRqstCmntTxtItem;
    }

    /**
     * @param upldCsvRqstCmntTxtItem set upldCsvRqstCmntTxtItem
     */
    public void setUpldCsvRqstCmntTxtItem(EZDTStringItem upldCsvRqstCmntTxtItem) {
        this.upldCsvRqstCmntTxtItem = upldCsvRqstCmntTxtItem;
    }

}
