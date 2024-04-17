/*
* Copyright (c) 2011 Canon USA Inc. All rights reserved.
* Original Author: AUTO generated File : S21WDS CodeTable Generator Tool
* Company: Canon USA Inc.
* Date Generated:2013/05/10 4:15:56
*/
package com.canon.cusa.s21.framework.ZYP.constant.cdtable;

/**
 * <p>
 * This interface provides the logical code names as constants for TRX_RSN table.
 * </p>
 *
 * @author $Author$
 * @version $Revision$
 */
public interface TRX_RSN {

    /** 000:Purchase Stock-In */
    String PURCHASE_STOCK_IN = "01";
    String EXPORT_VENDOR_RETURN = "02";
    String DOMESTIC_VENDOR_RETURN = "03";
    String EXPENSE_STOCK_IN = "04";
    String EXPORT_VENDOR_RETURN_ASSET = "05";
    String DOMESTIC_VENDOR_RETURN_ASSET = "06";

    /** 010:Sales */
    String REGULAR_SALES_WITH_COST = "01";
    String REGULAR_SALES_WITHOUT_COST = "02";
    String REGULAR_SALES_CASH_LEASE_MDSE = "A1";
    String REGULAR_SALES_CASH_LEASE_INIT_SUP = "A2";
    String REGULAR_SALES_CASH_LEASE_SUP = "A3";
    String REGULAR_SALES_CASH_LEASE_CNTR_SUP = "A5";
    // Add Start 2018/08/09 QC#27442
    String CASH_LEASE_BILL_ONLY = "A6";
    // Add End 2018/08/09 QC#27442
    String REGULAR_SALES_PRT_BILLABLE = "B1";
    String CREDIT_OR_DEBIT = "03";
    String CREDIT_AND_REBILL = "04";
    //String RETURN = "05";
    String RETURN = "A7";
    //String LOAN_TO_SALES = "06";
    String TRIAL_TO_SALES = "07";
    String LOAN_TO_SALES = "07";
    String RENTAL_TO_SALES = "08";
    String REGULAR_SALES_ASSET_FOR_AJE_LINK = "08";
    String DEFERED_REVENUE_FOR_EZPACK_II_OR_TONER_PLAN = "14";
    String DEFERED_REVENUE_FOR_OPTIMIZE_IT = "15";
    String DEFERED_REVENUE_FOR_CPC_SUPPLIES_ORDER = "16";
    String DEFERED_REVENUE_FOR_SCHEDULE_AGRMNT_PART = "17";
    String REGULAR_SALES_ASSET = "18";

    // START 2017/10/20 Mz.Takahashi [Sol#430(QC#16347) ADD]
    String  CASH_LEASE_INIT_SUP_CREDIT = "20";
    String  CASH_LEASE_CNTR_SUP_CREDIT = "21";
    String  LOAN_TO_SALES_CREDIT = "22";
    String  REGULAR_SALES_ASSET_FOR_AJE_LINK_CREDIT = "23";
    // END 2017/10/20 Mz.Takahashi [Sol#430(QC#16347) ADD]

    String EMSD_REVENUE = "E1";     //QC#18131
    // START 2017/08/28 M.Kidokoro [QC#20757, ADD]
    String SVC_SVC_MAN_ALLOC_BR_ONLY = "SA";
    String SVC_SPLY_MAN_ALLOC_BR_ONLY = "SB";
    String SVC_EQIP_MAN_ALLOC_BR_ONLY = "SC";
    // END 2017/08/28 M.Kidokoro [QC#20757, ADD]
    // 09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
    String RETURN_INITIAL_SUPPLY = "AA";
    String RETURN_CONTRACT_SUPPLY = "AB";
    // 09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END
    // START 2018/01/12 E.Kameishi [QC#23135 ADD]
    String OPTIMA_CREDIT = "C3";
    // END 2018/01/12 E.Kameishi [QC#23135 ADD]
    // START 2018/02/22 E.Kameishi [QC#22657 ADD]
    String CREDIT = "CR";
    // END 2018/02/22 E.Kameishi [QC#22657 ADD]
    // START 2018/02/22 E.Kameishi [QC#23378 ADD]
    String SVC_SVC_REV_BASE = "S1";
    String SVC_SUP_REV_BASE = "S2";
    String SVC_EQUIP_REV_BASE = "S3";
    // START 2023/04/23 S.Nakatani [QC#61387,ADD]
    String SVC_SVC_REV_USG = "S4";
    String SVC_SUP_REV_USG = "S5";
    // END 2023/04/23 S.Nakatani [QC#61387,ADD]
    // END 2018/02/22 E.Kameishi [QC#23378 ADD]
    /** 020:Movement */
    String INBOUND_IN_TRANSIT_STOCK_IN = "01";
    String INBOUND_IN_TRANSIT_STOCK_OUT = "02";
    String WAREHOUSE_TRANSFER_STOCK_OUT = "03";
    String WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN = "04";
    String STOCK_STATUS_CHANGE_STOCK_OUT = "05";
    String STOCK_STATUS_CHANGE_STOCK_IN = "06";
    String DOMESTIC_SHIPMENT_STOCK_OUT = "07";
    String DOMESTIC_SHIPMENT_STOCK_IN = "08";
    String TRIAL_SHIPMENT_STOCK_OUT = "09";
    String TRIAL_SHIPMENT_STOCK_IN = "10";
    String LOAN_SHIPMENT_STOCK_OUT = "09";
    String LOAN_SHIPMENT_STOCK_IN = "10";
    String DIRECT_SALE_SHIPMENT_STOCK_OUT = "11";
    String DIRECT_SALE_SHIPMENT_STOCK_IN = "12";
    String EXPORT_SALE_SHIPMENT_STOCK_OUT = "13";
    String EXPORT_SALE_SHIPMENT_STOCK_IN = "14";
    String VENDOR_TRANSFER_STOCK_OUT = "15";
    String VENDOR_TRANSFER_STOCK_IN = "16";
    String LOCATION_STATUS_CHANGE_STOCK_OUT = "17";
    String LOCATION_STATUS_CHANGE_STOCK_IN = "18";
    String OWNER_SHIP_CHANGE_STOCK_OUT = "19";
    String OWNER_SHIP_CHANGE_STOCK_IN = "20";
    String WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT = "21";
    String WAREHOUSE_TRANSFER_STOCK_IN = "22";
    String VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR = "23";
    String VENDOR_TRANSFER_STOCK_IN_FROM_VENDOR = "24";
    String TRIAL_TO_INVENTORY_STOCK_OUT = "25";
    String TRIAL_TO_INVENTORY_STOCK_IN = "26";
    String LOAN_TO_INVENTORY_STOCK_OUT = "25";
    String LOAN_TO_INVENTORY_STOCK_IN = "26";
    String DIRECT_SALE_SHIPPMENT_CANCEL_STOCK_OUT = "27";
    String DIRECT_SALE_SHIPPMENT_CANCEL_STOCK_IN = "28";
    String DROP_SHIPMENT_STOCK_OUT = "29";
    String DROP_SHIPMENT_STOCK_IN = "30";
    String DROP_SHIPMENT_TRIAL_STOCK_OUT = "31";
    String DROP_SHIPMENT_TRIAL_STOCK_IN = "32";
    String DROP_SHIPMENT_LOAN_STOCK_OUT = "31";
    String DROP_SHIPMENT_LOAN_STOCK_IN = "32";
    String EXPORT_TRIAL_STOCK_OUT = "33";
    String EXPORT_TRIAL_STOCK_IN = "34";
    String EXPORT_VENDOR_RETURN_STOCK_OUT = "35";
    String EXPORT_VENDOR_RETURN_STOCK_IN = "36";
    String TRIAL_STOCK_OUT_INTANGIBLE_WITH_COST = "37";
    String TRIAL_STOCK_IN_INTANGIBLE_WITH_COST = "38";
    String DIRECT_SALE_STOCK_OUT_INTANGIBLE_WITH_COST = "39";
    String DIRECT_SALE_STOCK_IN_INTANGIBLE_WITH_COST = "40";
    String OFF_BALANCE_SHEET_STOCK_OUT = "41";
    String LEASE_RETURN_STOCK_IN = "42";
    String INTERNAL_WH_TRANSFER_STOCK_OUT = "44";
    String INTERNAL_WH_TRANSFER_STOCK_IN = "45";
    String OFF_THE_BOOK_STOCK_OUT = "46";
    String OFF_THE_BOOK_STOCK_IN = "47";
    String OFF_THE_BOOK_SHIPMENT_STOCK_OUT = "48";
    String OFF_THE_BOOK_SHIPMENT_STOCK_IN = "49";
    String ASSET_WAREHOUSE_TRANSFER_STOCK_OUT = "50";
    String ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN = "51";
    String ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT = "52";
    String ASSET_WAREHOUSE_TRANSFER_STOCK_IN = "53";
    String OFF_THE_BOOK_WAREHOUSE_TRANSFER_STOCK_OUT = "54";
    String OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_IN = "55";
    String OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_STOCK_OUT = "56";
    String OFF_THE_BOOK_WAREHOUSE_TRANSFER_STOCK_IN = "57";
    String SHOWROOM_TRANSFER_STOCK_OUT = "58";
    String SHOWROOM_TRANSFER_IN_TRANSIT_STOCK_IN = "59";
    String SHOWROOM_TRANSFER_IN_TRANSIT_STOCK_OUT = "60";
    String SHOWROOM_TRANSFER_STOCK_IN = "61";
    String DIRECT_SALE_SHIPMENT_STOCK_OUT_ASSET = "62";
    String DIRECT_SALE_SHIPMENT_STOCK_IN_ASSET = "63";
    String REFURB_VENDOR_TRANSFER_STOCK_OUT = "64";
    String REFURB_VENDOR_TRANSFER_STOCK_IN = "65";
    String LOCATION_STATUS_CHANGE_CANCEL_STOCK_OUT = "66";
    String LOCATION_STATUS_CHANGE_CANCEL_STOCK_IN = "67";
    String ASSET_WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT = "68";
    String OFF_THE_BOOK_WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT = "69";
    String CONFIG_CHANGE_STOCK_OUT = "74";
    String CONFIG_CHANGE_STOCK_IN = "75";
    String REMAN_PARTS_TRANSFER_STOCK_OUT = "70";
    String REMAN_PARTS_TRANSFER_STOCK_IN = "71";
    String REMAN_PARTS_TRANSFER_BACK_STOCK_OUT = "72";
    String REMAN_PARTS_TRANSFER_BACK_STOCK_IN = "73";
    // QC#63527 2024/03/14 Start
    String OFF_THE_BOOK_LOAN_SHIPMENT_STOCK_OUT = "76";
    String OFF_THE_BOOK_LOAN_SHIPMENT_STOCK_IN = "77";
    String OFF_THE_BOOK_TRIAL_TO_INVENTORY_STOCK_OUT = "78";
    String OFF_THE_BOOK_TRIAL_TO_INVENTORY_STOCK_IN = "79";
    String OFF_THE_BOOK_LOAN_TO_INVENTORY_STOCK_OUT = "78";
    String OFF_THE_BOOK_LOAN_TO_INVENTORY_STOCK_IN = "79";
    // QC#63527 2024/03/14 End

    /** 030:Expense Shipment */
    String EXPENSE_SHIPMENT = "01";
    String NO_SHIP_EXPENSE = "02";
    String EXPENSE_RETURN = "03";
    String EXPENSE_SHIPMENT_ASSET = "04";
    String EXPENSE_SHIPMENT_ASSET_FOR_AJE_LINK = "05";
    String EXPENSE_LOAN_SHIPMENT_STOCK_OUT = "06";
    String EXPENSE_LOAN_TO_INVTY_SHORT_STOCK_OUT = "07";
    String EXPENSE_LOAN_DROP_SHIPMENT_STOCK_OUT = "08";
    String EXPENSE_LOAN_SHIPMENT_STOCK_OUT_INTANGIBLE_WITH_COST = "09";
    // QC#63527 2024/03/14 Start
    String OFF_THE_BOOK_EXPENSE_LOAN_SHIPMENT_STOCK_OUT = "10";
    String OFF_THE_BOOK_EXPENSE_LOAN_TO_INVTY_SHORT_STOCK_OUT = "11";
    // QC#63527 2024/03/14 End

    /** 040:Loan Shipment */
    //String LOAN_SHIPMENT_STOCK_OUT = "01";
    //String LOAN_SHIPMENT_STOCK_IN = "02";
    //String DROP_SHIPMENT_LOAN_STOCK_OUT = "03";
    //String DROP_SHIPMENT_LOAN_STOCK_IN = "04";
    String LOAN_STOCK_OUT_INTANGIBLE_WITH_COST = "05";
    String LOAN_STOCK_IN_INTANGIBLE_WITH_COST = "06";
    String LOAN_TO_ASSET_RETURN = "07";
    String LOAN_SHIPMENT_STOCK_OUT_ASSET = "08";
    String RENTAL_TO_LOAN = "09";

    /** 050:Rental Shipment */
    String RENTAL_SHIPMENT_STOCK_OUT = "01";
    String RENTAL_SHIPMENT_STOCK_IN = "02";
    String DROP_SHIPMENT_RENTAL_STOCK_OUT = "03";
    String RENTAL_STOCK_OUT_INTANGIBLE_WITH_COST = "05";
    String RENTAL_TO_ASSET_RETURN = "07";
    String RENTAL_SHIPMENT_STOCK_OUT_ASSET = "08";
    String LOAN_TO_RENTAL = "09";
    String RENTAL_SHIPMENT_STOCK_OUT_EXPENSE = "10";
    String RENTAL_ASSET_IMPORT = "11";
    // START 2016/09/26 C.Tanaka [QC#11422,ADD]
    String RENTAL_MANUAL_ASSET_CREATION = "12";
    // END 2016/09/26 C.Tanaka [QC#11422,ADD]
    // 09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
    String RETURN_RENTAL_INITIAL_SUPPLY = "13";
    // 09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END

    // START 2017/10/20 Mz.Takahashi [Sol#430(QC#16347) ADD]
    String RENTAL_SHIPMENT_STOCK_OUT_CREDIT = "14";
    String RENTAL_SHIPMENT_STOCK_OUT_EXPENSE_CREDIT = "15";
    // END 2017/10/20 Mz.Takahashi [Sol#430(QC#16347) ADD]

    /** 060:Adjustment */
    String ADJUSTMENT = "01";
    String DISPOSAL = "02";
    String LOAN_TO_DISPOSAL = "03";
    String RENTAL_TO_DISPOSAL = "04";
    String ASSET_DISPOSAL_FOR_AJE_LINK = "04";
    String WRITE_OFF = "05";
    String ITEM_CHANGE_STOCK_OUT = "06";
    String ITEM_CHANGE_STOCK_IN = "07";
    String ITEM_CHANGE_STOCK_OUT_FROM_VENDOR = "08";
    String ITEM_CHANGE_STOCK_IN_FROM_VENDOR = "09";
    String PRODUCT_TRANSFER_OUT = "10";
    String PRODUCT_TRANSFER_IN = "11";
    String ASSET_DISPOSAL = "12";
    String ASSET_TO_INVENTORY = "13";  // CCH-RD105 Add 2014/08/07
    String ASSET_COST_CHANGE = "14";   // CCH-RD066 Add 2014/08/13
    String ASSET_ITEM_CHANGE_STOCK_OUT = "13";
    String ASSET_ITEM_CHANGE_STOCK_IN = "14";
    String CYCLE_COUNT_ADJUSTMENT = "13";
    String PHYSICAL_INVENTORY_ADJUSTMENT = "14";
    String REFURB_VENDOR_TRANSFER_STOCK_OUT_FROM_VENDOR = "15";
    String REFURB_EXPENSE_SHIP_OUT = "16";
    String BUY_BACK_STOCK_OUT = "17";
    String KITTING_ITEM_CHANGE_STOCK_OUT = "18";
    String KITTING_ITEM_CHANGE_STOCK_IN = "19";
    String WAREHOUSE_TRANSFER_IN_TRANSIT_SHORTAGE_OUT = "20";
    // START 2016/09/16 J.Kim [QC#10360,ADD]
    String ASSET_INITIAL_COST_CHANGE = "21";
    // END 2016/09/16 J.Kim [QC#10360,ADD]
    // START 03/19/2018 T.Tokutomi [QC#12110,ADD]
    String REPLEN_TOOL_EXPENSE_OUT = "22";
    // END 03/19/2018 T.Tokutomi [QC#12110,ADD]

    /** 070:Insurance Claim */
    String LOSS_ON_SHIPMENT = "01";
    String LOSS_ON_RECEIVING = "02";

    /** 080:Expense Charge */
    String EXPENSE_CHARGE = "01";
    String OFF_SET_A_OR_R = "02";

    /** 090:Depreciation */
    String LOAN_DEPRECIATION = "01";
    String DEPRECIATION = "01";
    String RENTAL_DEPRECIATION = "02";
    String USEFUL_LIFE_CHANGE = "02";

    /** 100:CO-OP SPP */
    String CO_OP_SPP = "01";

    /** 110:AR RECEIPT */
    String ON_ACCOUNT = "01";
    String ADVANCE_RECEIPT = "04";
    String A_OR_R_TO_AFFILIATE = "10";
    String EXPORT_BANK_COMMISSION = "20";
    String EXPORT_A_OR_R_OFFSET_AFFILIATE = "21";
    String DEPOSIT_ADJUSTMENT_ADVANCE = "22";
    String DEPOSIT_ADJUSTMENT = "23";

    /** 120:AR RECEIPT ADJUSTMENT */
    String MISCELLANEOUS_INCOME = "01";
    String INTEREST_OTHERS = "02";
    String A_OR_R_CASH_REFUND = "03";
    String MISCELLANEOUS_INCOME_ADVANCE = "04";
    String INTEREST_OTHERS_ADVANCE = "05";
    String A_OR_R_CASH_REFUND_ADVANCE = "06";
    String EXPORT_MISCELLANEOUS_EXPENSE = "20";
    String EXPORT_A_OR_R_OFFSET_AFFILIATE_2 = "21";
    String EXPORT_BANK_COMMISSION_FEE = "22";
    String EXPORT_OPEN_A_OR_R_REFUND = "23";
    String EXPORT_A_OR_R_WRITE_OFF = "24";

    /** 130:AR INVOICE ADJUSTMENT */
    String FREIGHT_OUT = "01";
    String NON_OPERATING_MISCELLANEOUS = "02";
    String PROFESSIONAL_FEE = "03";
    String WRITE_OFF_2 = "04";
    String ACCRUED_SALES_TAX = "05";
    String A_OR_R_TO_AFFILIATES = "06";
    String OPEN_A_OR_R_REFUND = "07";
    String CASH_DISCOUNT = "08";
    String SUSPENSE = "09";
    String BANK_COMMISSION_FEE = "10";

    /** 140:AR CASH DISCOUNT */
    String CASH_DISCOUNT_2 = "01";

    /** 150:AR DEDUCTION */
    String DEDUCTION_OR_ON_ACCOUNT = "01";
    String BANK_COMMISSION_FEE_2 = "02";

    /** 160:AR CASH APPLICATION */
    String CASH_APPLICATION = "01";
    String CASH_APPLICATION_ADVANCE = "02";
    String EXPORT_CASH_APPLICATION = "20";

    /** 170:AR RECLASS */
    String A_OR_R_RECLASS = "01";
    String CASH_DISCOUNT_RECLASS = "02";
    String EXPORT_A_OR_R_RECLASS = "03";
    String EXPORT_A_OR_R_RECLASS_REVERSAL = "04";
    // 2019/12/18 QC#54624 add Start
    String AR_RECLASS = "05";
    String AR_RECLASS_REVERSAL = "06";
    // 2019/12/18 QC#54624 add End

    /** 180:AR REVALUATION */
    String A_OR_R_TRANSACTION_REVALUATION = "01";
    String A_OR_R_RECEIPT_REVALUATION = "02";
    String A_OR_R_ADVANCE_REVALUATION = "03";
    String AFFILIATED_COMPANY = "20";

    /** 190:COST CALCULATION */
    String COST_VARIANCE = "01";
    String TRANSIT = "02";
    String TRANSIT_REVERSAL = "03";
    String ESTIMATE = "04";
    String ESTIMATE_REVERSAL = "05";
    String REVALUATION = "06";
    String WRITE_DOWN = "07";
    String RECLASS_INVENTORY = "08";
    String RECLASS_INVENTORY_REVERSAL = "09";
    String RECLASS_TRANSIT = "10";
    String RECLASS_TRANSIT_REVERSAL = "11";
    String RECLASS_PARTS = "12";
    String RECLASS_PARTS_REVERSAL = "13";
    String COST_CURRENCY_REVALUATION = "14";
    String PURCHASE_EXPENSE = "15";
    String IMPORT_VENDOR_L_OR_C_PAY = "16";
    String IMPORT_VENDOR_REMITEE_PAY = "17";
    String IMPORT_VENDOR_D_OR_A_PAY = "18";
    String DOMESTIC_AFFILIATE_PAY = "19";
    String DOMESTIC_NON_AFFIL_PAY = "20";
    String COST_VARIANCE_DEL_STOCK_IN = "21";
    String COST_VARIANCE_DEL_OUTSTANDING = "22";
    String ESTIMATE_STOCK_IN_ERROR = "23";
    String ESTIMATE_REVERSAL_STOCK_IN_ERROR = "24";
    String ESTIMATE_OUTSTANDING_ITEM = "25";
    String ESTIMATE_REVERSAL_OUTSTANDING_ITEM = "26";
    // START 2022/04/11 [QC#57935 ADD]
    String ACCRUAL_WRITE_OFF = "27";
    String ACCRUAL_WRITE_OFF_RVRSL = "28";
    // END 2022/04/11 [QC#57935 ADD]

    /** 200:FREIGHT COST ALLOCATION */
    String NTC_WITH_TOC = "01";
    String NTC_MDSE_WITHOUT_TOC = "02";
    String NTC_PARTS_WITHOUT_TOC = "03";

    /** 210:Journalizer */
    String AR_DEFERRED_REVENUE_INVOICE_OR_DEBIT = "01";
    String DEFERRED_REVENUE_INVOICE_OR_DEBIT = "03";
    String TAX_INVOICE_OR_DEBIT = "05";

    /** 230:Parts Usage */
    String PARTS_USAGE = "01";
    String PARTS_USAGE_RETURN = "02";
    /* 01/21/2015 CSAI Y.Imazu Add CCH-QC610 START */
    String PARTS_USAGE_RENTAL = "03";
    String PARTS_USAGE_WITHOUT_CONTRACT = "04";
    /* 01/21/2015 CSAI Y.Imazu Add CCH-QC610 END */

    /** 240:Re-manufacturing */
    //String PARTS_USAGE_RETURN_FOR_REMAN = "05";
    String PARTS_USAGE_FOR_REMAN = "01";
    String REMAN_ITEM_CHANGE_STOCK_OUT = "02";
    String REMAN_ITEM_CHANGE_STOCK_IN = "03";
    String PARTS_USAGE_RETURN_FOR_REMAN = "04";
    String REMAN_ITEM_CHANGE_STOCK_OUT_ACSRY = "05";
    String REMAN_ITEM_CHANGE_STOCK_IN_ACSRY = "06";

    /** 250:Inventory Valuation */
    String SWH_TRANSFER_STOCK_OUT = "06";
    String SWH_TRANSFER_STOCK_IN = "07";

    /** 310:EMSD Shipment */
    String EMSD_SHIPMENT_STOCK_OUT = "01";
    String EMSD_DROP_SHIPMENT_STOCK_OUT = "03";
    String EMSD_STOCK_OUT_INTANGIBLE_WITH_COST = "05";
    String EMSD_TO_ASSET_RETURN = "07";
    String EMSD_SHIPMENT_STOCK_OUT_ASSET = "08";
    String EMSD_SHIPMENT_STOCK_OUT_EXPENSE = "10";
    String EMSD_ASSET_IMPORT = "11";
    // START 2016/09/26 C.Tanaka [QC#11422,ADD]
    String EMSD_MANUAL_ASSET_CREATION = "12";
    // END 2016/09/26 C.Tanaka [QC#11422,ADD]
    // 09/14/2017 CITS S.Endo Add Sol#373(QC#16346) START
    String RETURN_EMSD_INITIAL_SUPPLY = "13";
    // 09/14/2017 CITS S.Endo Add Sol#373(QC#16346) END

    // START 2017/10/20 Mz.Takahashi [Sol#430(QC#16347) ADD]
    String EMSD_SHIPMENT_STOCK_OUT_CREDIT = "14";
    String EMSD_SHIPMENT_STOCK_OUT_EXPENSE_CREDIT = "15";
    // END 2017/10/20 Mz.Takahashi [Sol#430(QC#16347) ADD]
}
