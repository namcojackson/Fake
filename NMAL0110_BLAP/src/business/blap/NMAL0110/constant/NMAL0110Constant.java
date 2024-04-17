package business.blap.NMAL0110.constant;

import java.math.BigDecimal;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 * 07/03/2015   Fujitsu         C.Tanaka        Update
 * 10/02/2015   SRAA            K.Aratani       Update
 * 02/11/2016   SRAA            Y.Chen          Update          QC#3579
 * 11/08/2016   Fujitsu         R.Nakamura      Update          QC#2872
 * 06/10/2019   Fujitsu         M.Ohno          Update          QC#50763
 * 2019/10/28   Fujitsu         K.Kato          Update          QC#53741
 *</pre>
 */
public interface NMAL0110Constant {

    /**
     * Pull down Delimiter
     */
    String PULLDOWN_DELIMITER = ":";

    /**
     * Search Action
     */
    String INIT = "init";
    
    /**
     * Copy Action
     */
    String COPY = "copy";
    
    /**
     * Template Search Action
     */
    String SEARCH_TMPL = "searchTmpl";
    
    /**
     * Tab Name -- General
     */
    String TAB_GENERAL = "General";

    /**
     * Tab Name -- Inventory
     */
    String TAB_INVENTORY = "Inventory";

    /**
     * Tab Name -- Accounting
     */
    String TAB_ACCOUNTING = "Accounting";

    /**
     * Tab Name -- FieldService 
     */
    String TAB_FIELD_SERVICE = "FieldService";

    /**
     * Tab Name -- OrderProcessing 
     */
    String TAB_ORDER_PROCESSING = "OrderProcessing";

    /**
     * Tab Name -- Supercession 
     */
    String TAB_SUPD = "Supercession";

    /**
     * Tab Name -- CustomerRef 
     */
    String TAB_CUST_REF = "CustomerRef";

    /**
     * Mode -- New
     */
    String MODE_NEW = "1";

    /**
     * Mode -- Modify
     */
    String MODE_MOD = "2";

    /**
     * Mode -- Read Only
     */
    String MODE_READ_ONLY = "3";

    /**
     * RETURN_CD_NORMAL -- 0000
     */
    String RETURN_CD_NORMAL = "0000";

    //Security
    //Header
    /**
     * FUNC_EDIT_HEADER -- NMAL0110T010
     */
    String FUNC_EDIT_HEADER = "NMAL0110T010";
    
    //General
    /**
     * FUNC_READ_GENERAL -- NMAL0110T020
     */
    String FUNC_READ_GENERAL = "NMAL0110T020";
    /**
     * FUNC_EDIT_GENERAL -- NMAL0110T030
     */
    String FUNC_EDIT_GENERAL = "NMAL0110T030";
    
    //Inventory
    /**
     * FUNC_READ_INVENTORY -- NMAL0110T040
     */
    String FUNC_READ_INVENTORY = "NMAL0110T040";

    /**
     * FUNC_EDIT_INVENTORY -- NMAL0110T050
     */
    String FUNC_EDIT_INVENTORY = "NMAL0110T050";
    
    //Accounting
    /**
     * FUNC_READ_ACCOUNTING -- NMAL0110T060
     */
    String FUNC_READ_ACCOUNTING = "NMAL0110T060";

    /**
     * FUNC_EDIT_ACCOUNTING -- NMAL0110T070
     */
    String FUNC_EDIT_ACCOUNTING = "NMAL0110T070";
    
    //FieldService
    /**
     * FUNC_READ_FIELD_SERVICE -- NMAL0110T080
     */
    String FUNC_READ_FIELD_SERVICE = "NMAL0110T080";

    /**
     * FUNC_EDIT_FIELD_SERVICE -- NMAL0110T090
     */
    String FUNC_EDIT_FIELD_SERVICE = "NMAL0110T090";
    //OrderProcessing
    /**
     * FUNC_READ_ORDER_PROCESSING -- NMAL0110T100
     */
    String FUNC_READ_ORDER_PROCESSING = "NMAL0110T100";

    /**
     * FUNC_EDIT_ORDER_PROCESSING -- NMAL0110T110
     */
    String FUNC_EDIT_ORDER_PROCESSING = "NMAL0110T110";
    
    //Supercession
    /**
     * FUNC_READ_SUPD -- NMAL0110T140
     */
    String FUNC_READ_SUPD = "NMAL0110T140";

    /**
     * FUNC_EDIT_SUPD -- NMAL0110T150
     */
    String FUNC_EDIT_SUPD = "NMAL0110T150";
    
    //CustomerRef
    /**
     * FUNC_READ_CUST_REF -- NMAL0110T160
     */
    String FUNC_READ_CUST_REF = "NMAL0110T160";

    /**
     * FUNC_EDIT_CUST_REF -- NMAL0110T170
     */
    String FUNC_EDIT_CUST_REF = "NMAL0110T170";

    /**
     * MDSE Code key Name
     */
    String MODE_CD_KEY_NAME = "MDSE_CD";

    /**
     * Referenced Entity Item
     */
    String REF_ENTITY_ITEM = "ITEM";
    
    /**
     * Default Days Primary Allocation Number
     */
    BigDecimal DEF_DAYS_PRI_ALLOC_NUM = new BigDecimal("2");

    // QC#3579
    /**
     * Business ID -- NMAL0110
     */
    String BUSINESS_ID = "NMAL0110";

    /**
     * Business ID -- BOM
     */
    String BOM_BUSINESS_ID = "NMAL0240";

    /**
     * Item History Report ID
     */
    String ITEM_HIST_RPT_ID = "NMAL0110F00";

    /**
     * New Line Character for SQL statement
     */
    String NEW_LINE_CHAR = "\n";

    /**
     * DA Action - Insert
     */
    String DB_INSR_ACT_NM = "INSERT";

    /**
     * DA Action - Update
     */
    String DB_UPD_ACT_NM = "UPDATE";

    /**
     * DA Action - Delete
     */
    String DB_DEL_ACT_NM = "DELETE";

    /**
     * Max column definition per table
     */
    int ITEM_HIST_RPT_MAX_COL_PER_TBL = 200;

    /**
     * Max length of column value for item history report
     */
    int ITEM_HIST_RPT_MAX_COL_VAL_LEN = 1000;

    /**
     * Table Name MDSE_STORE_PKG
     */
    String TABLE_MDSE_STORE_PKG = "MDSE_STORE_PKG";

    /**
     * Table Name MDSE_SFTY_INFO
     */
    String TABLE_MDSE_SFTY_INFO = "MDSE_SFTY_INFO";

    // 2019/06/10 QC#50763 Add Start
    /**
     * REV_ACCT_START_WITH_CD
     */
     String REV_ACCT_START_WITH_CD= "4";
    // 2019/06/10 QC#50763 Add End
    
    // 2019/10/28 QC#53741 Add Strat
    /**
     * Package Uom cd Inch
     */
     String PKG_UOM_INCH= "IN";

    /**
     * Package Uom cd Feet
     */
     String PKG_UOM_FEET= "FT";

    /**
     * Package Uom cd Yard
     */
     String PKG_UOM_YARD= "YRD";

    /**
     * Package Uom cd Pound
     */
     String PKG_UOM_POUND= "LB";
    // 2019/10/28 QC#53741 Add End
    
}
