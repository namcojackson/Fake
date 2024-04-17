/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC001001;

/**
 * <pre>
 * Machine Master Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/24/2013   SRA             N.Otsuji        Create
 * 09/17/2015   Fujitsu         S.Nakai         Update
 * 10/01/2015   Hitachi         T.Tsuchida      Update          NA#CSA
 * 10/19/2015   Fujitsu         T.Nakamura      Update          NA#Fixed degradation by deleting unused mode.
 * 2019/11/14   Hitachi         K.Kitachi       Update          QC#54327
 * </pre>
 */
public enum ProcessMode {

    /**
     * Insert Warehouse
     */
    INSERT_WAREHOUSE("01"),

    /**
     * Insert Machine in Field
     */
    INSERT_MACHINE_IN_FIELD("02"),

    /**
     * Update Warehouse
     */
    UPDATE_WAREHOUSE("03"),

    /**
     * Update Machine in Field
     */
    UPDATE_MACHINE_IN_FIELD("04"),

    /**
     * Update Termination
     */
    UPDATE_TERMINATION("05"),

    /**
     * Shipment
     */
    SHIPMENT("06"),

    /**
     * Installation
     */
    INSTALLATION("07"),

    /**
     * RMA
     */
    RMA("08"),

    /**
     * RMA Cancel
     */
    RMA_CANCEL("09"),

    /**
     * RWS
     */
    RWS("10"),

    /**
     * Disposal
     */
    DISPOSAL("11"),

    /**
     * Transfer OUT
     */
    TRANSFER_OUT("12"),

    /**
     * Transfer IN
     */
    TRANSFER_IN("13"),

    /**
     * Service Update
     */
    SERVICE_UPDATE("14"),

    /**
     * Conversion Order
     */
    CONVERSION_ORDER("15"),

    /**
     * Update Attribute
     */
    UPDATE_ATTRIBUTE("16"),

    /**
     * Update Location
     */
    UPDATE_LOCATION("17"),

    /**
     * Allocation ON
     */
    ALLOCATION_ON("18"),

    /**
     * Allocation OFF
     */
    ALLOCATION_OFF("19"),

    /**
     * Service Exchange
     */
    SERVICE_EXCHANGE("20"),

    /**
     * Update Inventory
     */
    UPDATE_INVENTORY("21"),

    /**
     * Item Change
     */
    ITEM_CHANGE("22"),

    // START 2019/11/14 K.Kitachi [QC#54327, ADD]
    /**
     * Remove Configuration
     */
    REMOVE_CONFIG("23"),
    // END 2019/11/14 K.Kitachi [QC#54327, ADD]

//Following mode is unused.
    /**
     * New
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    NEW("01"),

    /**
     * Modify
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    MODIFY("02"),

    /**
     * Return Order Entry
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    RETURN_ORDER_ENTRY("03"),

    /**
     * Return Order Cancel
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    RETURN_ORDER_CANCEL("04"),

    /**
     * Return Receiving
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    RETURN_RECEIVING("05"),

    /**
     * Remove
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    REMOVE("06"),

    /**
     * Remove Cancel
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    REMOVE_CANCEL("07"),

//    /**
//     * Service Update
//     */
//    SERVICE_UPDATE("08"),
//
//    /**
//     * Installation
//     */
//    INSTALLATION("09"),
//
    /**
     * Accessory Relation
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    ACCESSORY_RELATION("10"),

    /**
     * Accessory Relation Cancel
     * @deprecated FIXME This code is deleted but still exists to avoid build error. 
     */
    ACCESSORY_RELATION_CANCEL("11")

//,
//    /**
//     * Conversion Order
//     */
//    CONVERSION_ORDER("12");
//
;

    /**
     * String representation of the process mode
     */
    public final String code;

    private ProcessMode(String code) {
        this.code = code;
    }

    /**
     * Returns all the ProcessMode codes
     * @return ProcessMode codes
     */
    public static String[] codes() {

        ProcessMode[] values = values();
        int length = values.length;

        String[] codes = new String[length];

        for (int i = 0; i < length; i++) {
            codes[i] = values[i].code;
        }

        return codes;
    }

    /**
     * Returns the ProcessMode of the specified ProcessMode code
     * @param code ProcessMode code
     * @return ProcessMode
     */
    public static ProcessMode codeOf(String code) {

        for (ProcessMode mode : values()) {
            if (mode.code.equals(code)) {
                return mode;
            }
        }

        return null;
    }

}
