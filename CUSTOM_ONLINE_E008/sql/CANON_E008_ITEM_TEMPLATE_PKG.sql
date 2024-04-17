create or replace PACKAGE CANON_E008_ITEM_TEMPLATE_PKG
AS
   /********************************************************************
   Modification History:

   Version      Date               Author                 Remarks
   =======   ===========   ====================   ====================
   1.0        02/01/2016        Lakshmi Majeti   Item Template Cretaion Package
   1.1        02/01/2017        Madhusudan Duna  Item Template Cretaion Package
   **********************************************************************/

   TYPE G_REF_CUR_TYP IS REF CURSOR;

   PROCEDURE TEMPLATE_ATTRIBUTE_LIST (P_TEMPL_ATTR_LIST   OUT G_REF_CUR_TYP,
                                      P_ERR_FLG           OUT VARCHAR2,
                                      P_ERR_MSG           OUT VARCHAR2);

   PROCEDURE TEMPLATE_NAME_LIST (P_TEMPL_NAME_LIST   OUT G_REF_CUR_TYP,
                                 P_ERR_FLG           OUT VARCHAR2,
                                 P_ERR_MSG           OUT VARCHAR2);

   PROCEDURE TEMPLATE_STATUS_LIST (P_TEMPL_STS_LIST   OUT G_REF_CUR_TYP,
                                   P_ERR_FLG          OUT VARCHAR2,
                                   P_ERR_MSG          OUT VARCHAR2);

   PROCEDURE TEMPLATE_CATEGORY_LIST (P_TEMPL_CATEGORY_LIST   OUT G_REF_CUR_TYP,
                                     P_ERR_FLG               OUT VARCHAR2,
                                     P_ERR_MSG               OUT VARCHAR2);

   PROCEDURE WB_DISPLAY_LIST (P_WB_LIST   OUT G_REF_CUR_TYP,
                              P_ERR_FLG   OUT VARCHAR2,
                              P_ERR_MSG   OUT VARCHAR2);

   PROCEDURE APPROVAL_GROUP_LIST (--P_LIST_CODE             IN     VARCHAR2,
                                  P_APPROVAL_GROUP_LIST      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                  OUT VARCHAR2,
                                  P_ERR_MSG                  OUT VARCHAR2);

   PROCEDURE GET_TEMPLATE_LIST (P_TEMPLATE_LIST_TBL   OUT G_REF_CUR_TYP,
                                P_ERR_FLG             OUT VARCHAR2,
                                P_ERR_MSG             OUT VARCHAR2);

   PROCEDURE TEMPLATE_TYPE_LIST (P_TEMPL_TYPE_LIST   OUT G_REF_CUR_TYP,
                                 P_ERR_FLG           OUT VARCHAR2,
                                 P_ERR_MSG           OUT VARCHAR2);

   ---Start New LOV APIs for Item Template performance

   PROCEDURE MANUFACTURER_LIST (P_LIST_CODE           IN     VARCHAR2,
                                P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                P_ERR_FLG                OUT VARCHAR2,
                                P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE ITEM_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MERCHANDISE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                    P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE ITEM_CLASSIFICATION_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PRODUCT_CODE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                P_ERR_FLG                OUT VARCHAR2,
                                P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MARKETING_MODEL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MARKETING_SEGMENT_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE UNIT_OF_MEASURE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE UOM_CLASS_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE RETURN_CONTROL_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PRODUCT_LEVEL1_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PRODUCT_LEVEL2_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PRODUCT_LEVEL3_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PRODUCT_LEVEL4_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2,
                                  P_LEVEL3_CODE           IN     VARCHAR2);

   PROCEDURE PRODUCT_LEVEL4_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PRODUCT_LEVEL5_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2);


   PROCEDURE MATERIAL_GROUP1_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MATERIAL_GROUP2_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MATERIAL_GROUP3_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE COST_OF_GOODS_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE EXPENSE_LIST (P_LIST_CODE           IN     VARCHAR2,
                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                           P_ERR_FLG                OUT VARCHAR2,
                           P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE REVENUE_LIST (P_LIST_CODE           IN     VARCHAR2,
                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                           P_ERR_FLG                OUT VARCHAR2,
                           P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE ACCRUAL_LIST (P_LIST_CODE           IN     VARCHAR2,
                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                           P_ERR_FLG                OUT VARCHAR2,
                           P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE OWNING_DIVISION_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SERIAL_CONTROL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2);


   PROCEDURE SERVICE_MODEL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE RETURN_CONTROLLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE COVERAGE_BASE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE COVERAGE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SERVICE_ALLOCATION_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE TC_OPTION_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PART_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PARTS_DROPSHIP_DISABLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PARTS_SURVEY_REQUIRED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                         P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                         P_ERR_FLG                OUT VARCHAR2,
                                         P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE WARRANTY_LIST (P_LIST_CODE           IN     VARCHAR2,
                            P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                            P_ERR_FLG                OUT VARCHAR2,
                            P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE IMAGEWARE_REMOTE_ENABLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                            P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                            P_ERR_FLG                OUT VARCHAR2,
                                            P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE IMAGEWARE_REMOTE_MODEL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                          P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                          P_ERR_FLG                OUT VARCHAR2,
                                          P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE METERED_MACHINE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE INSTALLBASE_CONTROLLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                          P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                          P_ERR_FLG                OUT VARCHAR2,
                                          P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SERVICE_CALL_ENABLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SOFTWARE_CATEGORY_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SOFTWARE_PRODUCT_CATEGORY_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE LICENSE_CONTROL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE ELAN_CONTROLLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SOFTWARE_MAINTENANCE_CONT_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MAINTENANCE_POP_AVALIABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE EXTENDED_MAINT_POP_AVAILA_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MAINTENANCE_ITEM_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                         P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                         P_ERR_FLG                OUT VARCHAR2,
                                         P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SUPPLY_OEM_MANUFACTURER_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SUPPLY_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SUPPLY_COLOR_LIST (P_LIST_CODE           IN     VARCHAR2,
                                P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                P_ERR_FLG                OUT VARCHAR2,
                                P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SUPPLY_YIELD_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SUPPLY_YIELD_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                                    P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PRIVATE_LABEL_FLAG_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE ACCOUNTING_RULES_LIST (P_LIST_CODE           IN     VARCHAR2,
                                    P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE INVOICEABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE TAX_CODE_LIST (P_LIST_CODE           IN     VARCHAR2,
                            P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                            P_ERR_FLG                OUT VARCHAR2,
                            P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE INVOICING_RULES_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE THIRD_PARTY_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE PLANNING_GROUP_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SAFETY_HAZARDOUS_MATERIAL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SAFETY_HAZARDOUS_NUMBER_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE RMA_INSPECTION_REQUIRED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE RMA_ALLOWED_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE COSTED_LIST (P_LIST_CODE           IN     VARCHAR2,
                          P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                          P_ERR_FLG                OUT VARCHAR2,
                          P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE INVENTORY_TRACKABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2);
                                       
   PROCEDURE INTERNAL_ITEM_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2);
                                      

   PROCEDURE NMFC_CLASS_LIST (P_LIST_CODE           IN     VARCHAR2,
                              P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                              P_ERR_FLG                OUT VARCHAR2,
                              P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE REMAN_AVAILABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SOW_REQUIRED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                P_ERR_FLG                OUT VARCHAR2,
                                P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MAIN_ENGINE_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE CRITICALITY_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE CUSTOMER_ORDEREABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE REMANUFACTURED_ITEM_EXIST_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE ASSEMBLED_COUNTRY_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE MANUFACTURED_COUNTRY_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE DEFAULT_SRC_WH_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE DEFAULT_SRC_SUB_WH_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE RETURN_WAREHOUSE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                    P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE RETURN_SUB_WAREHOUSE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE TARIFF_CODE_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE FREIGHT_CLASS_CODE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE INTANGIBLE_MDSE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SUPPLIER_LIST (P_LIST_CODE           IN     VARCHAR2,
                            P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                            P_ERR_FLG                OUT VARCHAR2,
                            P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE SUPPLIER_SITE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2);


   PROCEDURE SUPPLIER_SITE_LIST (P_SUPPLIER            IN     VARCHAR2,
                                 P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE ITEM_BILLING_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE CONFIGURATION_FLAG_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE RETURN_VENDOR_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE RETURN_VENDOR_SITE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE HARD_ALLOCATION_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE DEFAULT_SOURCE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE EASY_PACK_I_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2);

   --End New LOV signatures for Item Template performance

   PROCEDURE GET_TEMPL_ATTR_ASSIGN (P_TEMPLATE_ID         IN     NUMBER,
                                    P_ATTRIBUTE_ID        IN     NUMBER,
                                    P_TEMPL_ATTR_ASSIGN      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE TEMPLATE_SEARCH (P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                              P_ERR_FLG                OUT VARCHAR2,
                              P_ERR_MSG                OUT VARCHAR2,
                              P_TEMPLATE_NAME       IN     VARCHAR2,
                              P_TEMPLATE_TYPE       IN     VARCHAR2,
                              P_TEMPLATE_STATUS     IN     VARCHAR2,
                              P_TEMPLATE_DESC       IN     VARCHAR2,
                              P_ITEM_ASSIGNED       IN     VARCHAR2,
                              P_ATTRIBUTE1          IN     VARCHAR2,
                              P_ATTRIBUTE1_VAL      IN     VARCHAR2,
                              P_ATTRIBUTE2          IN     VARCHAR2,
                              P_ATTRIBUTE2_VAL      IN     VARCHAR2,
                              P_ATTRIBUTE3          IN     VARCHAR2,
                              P_ATTRIBUTE3_VAL      IN     VARCHAR2,
                              P_ATTRIBUTE4          IN     VARCHAR2,
                              P_ATTRIBUTE4_VAL      IN     VARCHAR2,
                              P_ATTRIBUTE5          IN     VARCHAR2,
                              P_ATTRIBUTE5_VAL      IN     VARCHAR2,
                              P_TEMPLATE_ID         IN     NUMBER);

   PROCEDURE GET_TEMPLATE_ATTRIBUTES (P_CATEGORY_NAME             IN     VARCHAR2,
                                      P_TEMPLATE_ATTRIBUTES_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                      OUT VARCHAR2,
                                      P_ERR_MSG                      OUT VARCHAR2);

   PROCEDURE CREATE_TEMPLATE_HEADER (P_TEMPLATE_REC   IN     CANON_E008_TEMPL_REC,
                                     P_TEMPLATE_ID       OUT NUMBER,
                                     P_ERR_FLG           OUT VARCHAR2,
                                     P_ERR_MSG           OUT VARCHAR2);

   PROCEDURE SAVE_TEMPLATE_HEADER (P_TEMPLATE_TBL   IN     CANON_E008_TEMPL_TBL_TYPE,
                                   P_ERR_FLG           OUT VARCHAR2,
                                   P_ERR_MSG           OUT VARCHAR2);

   PROCEDURE SAVE_TEMPLATE_ATTRIBUTES (
      P_TEMPL_ATTR_TBL   IN     CANON_E008_TEMPL_ATTR_TBL_TYPE,
      P_ERR_FLG             OUT VARCHAR2,
      P_ERR_MSG             OUT VARCHAR2);

   PROCEDURE SAVE_TEMPL_ATTR_ASSGMT (
      P_TEMPL_ATTR_ASSGN_TBL   IN     CANON_E008_TEMPL_AA_TBL_TYPE,
      P_ERR_FLG                   OUT VARCHAR2,
      P_ERR_MSG                   OUT VARCHAR2);

   PROCEDURE SAVE_TEMPLATE_SEARCH (P_TEMPLATE_NAME             IN     VARCHAR2,
                                   P_TEMPLATE_TYPE             IN     VARCHAR2,
                                   P_TEMPLATE_CAT              IN     VARCHAR2,
                                   P_TEMPLATE_STATUS           IN     VARCHAR2,
                                   P_TEMPL_ITEM_ASSIGN         IN     VARCHAR2,
                                   P_TEMPLATE_DESC             IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE1       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE1_VAL   IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE2       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE2_VAL   IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE3       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE3_VAL   IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE4       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE4_VAL   IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE5       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE5_VAL   IN     VARCHAR2,
                                   P_USER                      IN     VARCHAR2,
                                   P_SAVED_NAME                IN     VARCHAR2,
                                   P_ERR_FLG                      OUT VARCHAR2,
                                   P_ERR_MSG                      OUT VARCHAR2);

   PROCEDURE GET_SAVEDSEARCH_LIST (P_SAVEDSEARCH_LIST_TBL   OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE GET_SAVEDSEARCH_VALUES (P_SAVEDSEARCH_NAME         VARCHAR2,
                                     P_USER                     VARCHAR2,
                                     P_SAVEDSEARCH_VALUES   OUT G_REF_CUR_TYP,
                                     P_ERR_FLG              OUT VARCHAR2,
                                     P_ERR_MSG              OUT VARCHAR2);

   PROCEDURE COMMISSIONS_GROUP_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE UNBOXED_WEIGHT_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE UNBOXED_LENGTH_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);
   PROCEDURE UNBOXED_WIDTH_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);
   PROCEDURE UNBOXED_HEIGHT_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2);

END CANON_E008_ITEM_TEMPLATE_PKG;
/

create or replace PACKAGE BODY CANON_E008_ITEM_TEMPLATE_PKG
AS
   /********************************************************************
   Modification History:

   Version      Date               Author                 Remarks
   =======   ===========   ====================   ====================
   1.0        02/01/2016        Lakshmi Majeti   Item Template Cretaion Package
   **********************************************************************/

   PROCEDURE GET_COLUMN_HEADERS (P_COLUMN_HEADER   OUT G_REF_CUR_TYP,
                                 P_ERR_FLG         OUT VARCHAR2,
                                 P_ERR_MSG         OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_COLUMN_HEADER FOR
         SELECT ATTRIBUTE_ID,
                CREATED_BY,
                CREATION_DATE,
                DEFAULT_VALUE,
                EZINCOMPANYCODE,
                EZTABLEID,
                LAST_UPDATE_BY,
                LAST_UPDATE_DATE,
                REQUIRED_VALUE,
                TEMPLATE_ID,
                VALID
           FROM CANON_E008_TEMPL_ATTR_ASGN_TBL;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END;

   PROCEDURE TEMPLATE_ATTRIBUTE_LIST (P_TEMPL_ATTR_LIST   OUT G_REF_CUR_TYP,
                                      P_ERR_FLG           OUT VARCHAR2,
                                      P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPL_ATTR_LIST FOR
           SELECT DISTINCT ATTRIBUTE_NAME
             FROM CANON_E008_TEMPLATE_ATTR_TBL
            WHERE ENABLE_FLAG = 'Y'
         ORDER BY ATTRIBUTE_NAME;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TEMPLATE_ATTRIBUTE_LIST;

   PROCEDURE TEMPLATE_NAME_LIST (P_TEMPL_NAME_LIST   OUT G_REF_CUR_TYP,
                                 P_ERR_FLG           OUT VARCHAR2,
                                 P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPL_NAME_LIST FOR
           SELECT DISTINCT TEMPLATE_NAME
             FROM CANON_E008_TEMPLATE_HDR_TBL
         ORDER BY TEMPLATE_NAME;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TEMPLATE_NAME_LIST;

   PROCEDURE TEMPLATE_TYPE_LIST (P_TEMPL_TYPE_LIST   OUT G_REF_CUR_TYP,
                                 P_ERR_FLG           OUT VARCHAR2,
                                 P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPL_TYPE_LIST FOR
           SELECT DISTINCT TEMPLATE_TYPE
             FROM CANON_E008_TEMPLATE_TYPES_V
         ORDER BY TEMPLATE_TYPE;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TEMPLATE_TYPE_LIST;

   PROCEDURE TEMPLATE_STATUS_LIST (P_TEMPL_STS_LIST   OUT G_REF_CUR_TYP,
                                   P_ERR_FLG          OUT VARCHAR2,
                                   P_ERR_MSG          OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPL_STS_LIST FOR
         SELECT 'ACTIVE' FROM DUAL
         UNION
         SELECT 'INACTIVE' FROM DUAL;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TEMPLATE_STATUS_LIST;

   PROCEDURE TEMPLATE_CATEGORY_LIST (P_TEMPL_CATEGORY_LIST   OUT G_REF_CUR_TYP,
                                     P_ERR_FLG               OUT VARCHAR2,
                                     P_ERR_MSG               OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPL_CATEGORY_LIST FOR
           SELECT DISTINCT CATEGORY
             FROM CANON_E008_TEMPLATE_CATEGORI_V
         ORDER BY CATEGORY;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TEMPLATE_CATEGORY_LIST;

   PROCEDURE WB_DISPLAY_LIST (P_WB_LIST   OUT G_REF_CUR_TYP,
                              P_ERR_FLG   OUT VARCHAR2,
                              P_ERR_MSG   OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_WB_LIST FOR
         SELECT 'MAIN' FROM DUAL
         UNION
         SELECT 'ADDITIONAL' FROM DUAL
         UNION
         SELECT 'NONE' FROM DUAL;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END WB_DISPLAY_LIST;

   PROCEDURE YES_NO_LIST (P_TEMPLATE_LIST_TBL   OUT G_REF_CUR_TYP,
                          P_ERR_FLG             OUT VARCHAR2,
                          P_ERR_MSG             OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
         SELECT '' LISTCODE, '' LISTNAME FROM DUAL
         UNION
         SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL
         UNION
         SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
         ORDER BY 1 DESC;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END YES_NO_LIST;

   PROCEDURE GET_TEMPL_ATTR_ASSIGN (P_TEMPLATE_ID         IN     NUMBER,
                                    P_ATTRIBUTE_ID        IN     NUMBER,
                                    P_TEMPL_ATTR_ASSIGN      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPL_ATTR_ASSIGN FOR
         SELECT A.EZTABLEID,
                A.EZINCOMPANYCODE,
                B.TEMPLATE_ID,
                C.ATTRIBUTE_ID,
                NVL (A.REQUIRED_VALUE, 'No') REQUIRED_VALUE,
                NVL (A.VALID, 'No') VALID,
                A.DEFAULT_VALUE,
                A.CREATED_BY,
                A.CREATION_DATE,
                A.LAST_UPDATE_BY,
                A.LAST_UPDATE_DATE,
                A.TEMPL_ATTR_ASGN_VER
           FROM CANON_E008_TEMPL_ATTR_ASGN_TBL A,
                CANON_E008_TEMPLATE_HDR_TBL B,
                CANON_E008_TEMPLATE_ATTR_TBL C
          WHERE     B.TEMPLATE_ID = P_TEMPLATE_ID
                AND C.ATTRIBUTE_ID = P_ATTRIBUTE_ID
                AND C.ENABLE_FLAG = 'Y'
                AND A.TEMPLATE_ID(+) = B.TEMPLATE_ID
                AND A.ATTRIBUTE_ID(+) = C.ATTRIBUTE_ID;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_TEMPL_ATTR_ASSIGN;

   PROCEDURE TEMPLATE_SEARCH (P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                              P_ERR_FLG                OUT VARCHAR2,
                              P_ERR_MSG                OUT VARCHAR2,
                              P_TEMPLATE_NAME       IN     VARCHAR2,
                              P_TEMPLATE_TYPE       IN     VARCHAR2,
                              P_TEMPLATE_STATUS     IN     VARCHAR2,
                              P_TEMPLATE_DESC       IN     VARCHAR2,
                              P_ITEM_ASSIGNED       IN     VARCHAR2,
                              P_ATTRIBUTE1          IN     VARCHAR2,
                              P_ATTRIBUTE1_VAL      IN     VARCHAR2,
                              P_ATTRIBUTE2          IN     VARCHAR2,
                              P_ATTRIBUTE2_VAL      IN     VARCHAR2,
                              P_ATTRIBUTE3          IN     VARCHAR2,
                              P_ATTRIBUTE3_VAL      IN     VARCHAR2,
                              P_ATTRIBUTE4          IN     VARCHAR2,
                              P_ATTRIBUTE4_VAL      IN     VARCHAR2,
                              P_ATTRIBUTE5          IN     VARCHAR2,
                              P_ATTRIBUTE5_VAL      IN     VARCHAR2,
                              P_TEMPLATE_ID         IN     NUMBER)
   IS
      L_ATTR_LIST_NAME      VARCHAR2 (100);
      L_TEMPLATE_LIST_TBL   G_REF_CUR_TYP;
      L_LIST_CODE           VARCHAR2 (4000);
      L_LIST_NAME           VARCHAR2 (4000);
      L_ERR_FLG             VARCHAR2 (100);
      L_ERR_MSG             VARCHAR2 (2000);
      L_DYN_PRC_SQL         VARCHAR2 (32000);
      L_ATTR                VARCHAR2 (4000);
      L_ATTR_VAL            VARCHAR2 (4000);
      L_ATTR_MATCH1         VARCHAR2 (4000);
      L_ATTR_MATCH2         VARCHAR2 (4000);
      L_ATTR_MATCH3         VARCHAR2 (4000);
      L_ATTR_MATCH4         VARCHAR2 (4000);
      L_ATTR_MATCH5         VARCHAR2 (4000);
   BEGIN
      FOR I IN 1 .. 5
      LOOP
         IF I = 1
         THEN
            L_ATTR := P_ATTRIBUTE1;
            L_ATTR_VAL := P_ATTRIBUTE1_VAL;
         ELSIF I = 2
         THEN
            L_ATTR := P_ATTRIBUTE2;
            L_ATTR_VAL := P_ATTRIBUTE2_VAL;
         ELSIF I = 3
         THEN
            L_ATTR := P_ATTRIBUTE3;
            L_ATTR_VAL := P_ATTRIBUTE3_VAL;
         ELSIF I = 4
         THEN
            L_ATTR := P_ATTRIBUTE4;
            L_ATTR_VAL := P_ATTRIBUTE4_VAL;
         ELSIF I = 5
         THEN
            L_ATTR := P_ATTRIBUTE5;
            L_ATTR_VAL := P_ATTRIBUTE5_VAL;
         END IF;

         IF L_ATTR IS NOT NULL
         THEN
            BEGIN
               SELECT SUBSTR (E008_WB_TBL_MAP, 1, 25) || '_LIST'
                 INTO L_ATTR_LIST_NAME
                 FROM CANON_E008_TEMPLATE_ATTR_TBL
                WHERE ATTRIBUTE_NAME = L_ATTR AND LOV_FLAG = 'Y';
            --   INSERT INTO test123
            --        VALUES ('l_attr_list_name:' || l_attr_list_name);
            EXCEPTION
               WHEN OTHERS
               THEN
                  L_ATTR_LIST_NAME := NULL;
            END;

            IF L_ATTR_LIST_NAME IS NOT NULL
            THEN
               L_DYN_PRC_SQL :=
                     'BEGIN CANON_E008_ITEM_TEMPLATE_PKG.'
                  || L_ATTR_LIST_NAME
                  || '( :l_TEMPLATE_LIST_TBL, :l_ERR_FLG , :l_ERR_MSG); END;';

               --   INSERT INTO test123
               --      VALUES ('L_DYN_PRC_SQL:' || L_DYN_PRC_SQL);

               EXECUTE IMMEDIATE L_DYN_PRC_SQL
                  USING OUT L_TEMPLATE_LIST_TBL, OUT L_ERR_FLG, OUT L_ERR_MSG;

               LOOP
                  FETCH L_TEMPLATE_LIST_TBL INTO L_LIST_CODE, L_LIST_NAME;

                  EXIT WHEN L_TEMPLATE_LIST_TBL%NOTFOUND;

                  IF UPPER (L_LIST_NAME) LIKE '%' || UPPER (L_ATTR_VAL)
                  THEN
                     IF I = 1
                     THEN
                        L_ATTR_MATCH1 := L_LIST_CODE;
                     ELSIF I = 2
                     THEN
                        L_ATTR_MATCH2 := L_LIST_CODE;
                     ELSIF I = 3
                     THEN
                        L_ATTR_MATCH3 := L_LIST_CODE;
                     ELSIF I = 4
                     THEN
                        L_ATTR_MATCH4 := L_LIST_CODE;
                     ELSIF I = 5
                     THEN
                        L_ATTR_MATCH5 := L_LIST_CODE;
                     END IF;
                  END IF;
               END LOOP;                                           --Dynamic List Loop end

               CLOSE L_TEMPLATE_LIST_TBL;

               IF I = 1 AND L_ATTR_MATCH1 IS NULL
               THEN
                  L_ATTR_MATCH1 := 'XXYYZZ';
               ELSIF I = 2 AND L_ATTR_MATCH2 IS NULL
               THEN
                  L_ATTR_MATCH2 := 'XXYYZZ';
               ELSIF I = 3 AND L_ATTR_MATCH3 IS NULL
               THEN
                  L_ATTR_MATCH3 := 'XXYYZZ';
               ELSIF I = 4 AND L_ATTR_MATCH4 IS NULL
               THEN
                  L_ATTR_MATCH4 := 'XXYYZZ';
               ELSIF I = 5 AND L_ATTR_MATCH5 IS NULL
               THEN
                  L_ATTR_MATCH5 := 'XXYYZZ';
               END IF;
            ELSE                                              --  l_attr_list_name is null
               IF I = 1
               THEN
                  L_ATTR_MATCH1 := L_ATTR_VAL;
               ELSIF I = 2
               THEN
                  L_ATTR_MATCH2 := L_ATTR_VAL;
               ELSIF I = 3
               THEN
                  L_ATTR_MATCH3 := L_ATTR_VAL;
               ELSIF I = 4
               THEN
                  L_ATTR_MATCH4 := L_ATTR_VAL;
               ELSIF I = 5
               THEN
                  L_ATTR_MATCH5 := L_ATTR_VAL;
               END IF;
            END IF;                                         --l_attr_list_name is not null
         END IF;                                                      --l_attr is not null
      END LOOP;                                       --Input Template Attributes Loop end

      /*
               INSERT INTO test123
                    VALUES ('l_attr_match1:' || l_attr_match1);

               INSERT INTO test123
                    VALUES ('l_attr_match2:' || l_attr_match2);

               INSERT INTO test123
                    VALUES ('l_attr_match3:' || l_attr_match3);

               INSERT INTO test123
                    VALUES ('l_attr_match4:' || l_attr_match4);

               INSERT INTO test123
                    VALUES ('l_attr_match5:' || l_attr_match5);

            COMMIT;
      */
      OPEN P_TEMPLATE_LIST_TBL FOR
         SELECT EZTABLEID,
                EZINCOMPANYCODE,
                TEMPLATE_ID,
                TEMPLATE_NAME,
                TEMPLATE_TYPE,
                TEMPLATE_STATUS,
                TEMPLATE_DESCRIPTION,
                ITEM_ASSIGNED,
                TEMPLATE_HDR_VERSION,
                CREATED_BY,
                CREATION_DATE,
                LAST_UPDATE_BY,
                LAST_UPDATE_DATE
           FROM CANON_E008_TEMPLATE_HDR_TBL CTH
          WHERE     1 = 1
                AND TEMPLATE_ID = NVL (P_TEMPLATE_ID, TEMPLATE_ID)
                --AND TEMPLATE_NAME = NVL (P_TEMPLATE_NAME, TEMPLATE_NAME)
                AND UPPER (TEMPLATE_NAME) LIKE
                       '%' || UPPER (NVL (P_TEMPLATE_NAME, TEMPLATE_NAME)) || '%'
                AND NVL (TEMPLATE_TYPE, 'X') =
                       NVL (P_TEMPLATE_TYPE, NVL (TEMPLATE_TYPE, 'X'))
                AND NVL (TEMPLATE_STATUS, 'X') =
                       NVL (P_TEMPLATE_STATUS, NVL (TEMPLATE_STATUS, 'X'))
                AND NVL (TEMPLATE_DESCRIPTION, 'X') LIKE
                      '%' || NVL (P_TEMPLATE_DESC, NVL (TEMPLATE_DESCRIPTION, 'X')) || '%'
                AND NVL (ITEM_ASSIGNED, 'X') =
                       NVL (P_ITEM_ASSIGNED, NVL (ITEM_ASSIGNED, 'X'))
                AND EXISTS
                       (SELECT CTAA.TEMPLATE_ID
                          FROM CANON_E008_TEMPLATE_ATTR_TBL CTA,
                               CANON_E008_TEMPL_ATTR_ASGN_TBL CTAA
                         WHERE     CTAA.TEMPLATE_ID(+) = CTH.TEMPLATE_ID
                               AND CTA.ATTRIBUTE_ID = CTAA.ATTRIBUTE_ID(+)
                               AND CTA.ENABLE_FLAG = 'Y'
                               AND CTA.ATTRIBUTE_NAME =
                                      NVL (P_ATTRIBUTE1, CTA.ATTRIBUTE_NAME)
                               AND NVL (CTAA.DEFAULT_VALUE, 'X') =
                                      NVL (L_ATTR_MATCH1,              --P_ATTRIBUTE1_VAL,
                                                         NVL (CTAA.DEFAULT_VALUE, 'X')))
                AND EXISTS
                       (SELECT CTAA.TEMPLATE_ID
                          FROM CANON_E008_TEMPLATE_ATTR_TBL CTA,
                               CANON_E008_TEMPL_ATTR_ASGN_TBL CTAA
                         WHERE     CTAA.TEMPLATE_ID(+) = CTH.TEMPLATE_ID
                               AND CTA.ATTRIBUTE_ID = CTAA.ATTRIBUTE_ID(+)
                               AND CTA.ENABLE_FLAG = 'Y'
                               AND CTA.ATTRIBUTE_NAME =
                                      NVL (P_ATTRIBUTE2, CTA.ATTRIBUTE_NAME)
                               AND NVL (CTAA.DEFAULT_VALUE, 'X') =
                                      NVL (L_ATTR_MATCH2,              --P_ATTRIBUTE2_VAL,
                                                         NVL (CTAA.DEFAULT_VALUE, 'X')))
                AND EXISTS
                       (SELECT CTAA.TEMPLATE_ID
                          FROM CANON_E008_TEMPLATE_ATTR_TBL CTA,
                               CANON_E008_TEMPL_ATTR_ASGN_TBL CTAA
                         WHERE     CTAA.TEMPLATE_ID(+) = CTH.TEMPLATE_ID
                               AND CTA.ATTRIBUTE_ID = CTAA.ATTRIBUTE_ID(+)
                               AND CTA.ENABLE_FLAG = 'Y'
                               AND CTA.ATTRIBUTE_NAME =
                                      NVL (P_ATTRIBUTE3, CTA.ATTRIBUTE_NAME)
                               AND NVL (CTAA.DEFAULT_VALUE, 'X') =
                                      NVL (L_ATTR_MATCH3,              --P_ATTRIBUTE3_VAL,
                                                         NVL (CTAA.DEFAULT_VALUE, 'X')))
                AND EXISTS
                       (SELECT CTAA.TEMPLATE_ID
                          FROM CANON_E008_TEMPLATE_ATTR_TBL CTA,
                               CANON_E008_TEMPL_ATTR_ASGN_TBL CTAA
                         WHERE     CTAA.TEMPLATE_ID(+) = CTH.TEMPLATE_ID
                               AND CTA.ATTRIBUTE_ID = CTAA.ATTRIBUTE_ID(+)
                               AND CTA.ENABLE_FLAG = 'Y'
                               AND CTA.ATTRIBUTE_NAME =
                                      NVL (P_ATTRIBUTE4, CTA.ATTRIBUTE_NAME)
                               AND NVL (CTAA.DEFAULT_VALUE, 'X') =
                                      NVL (L_ATTR_MATCH4,              --P_ATTRIBUTE4_VAL,
                                                         NVL (CTAA.DEFAULT_VALUE, 'X')))
                AND EXISTS
                       (SELECT CTAA.TEMPLATE_ID
                          FROM CANON_E008_TEMPLATE_ATTR_TBL CTA,
                               CANON_E008_TEMPL_ATTR_ASGN_TBL CTAA
                         WHERE     CTAA.TEMPLATE_ID(+) = CTH.TEMPLATE_ID
                               AND CTA.ATTRIBUTE_ID = CTAA.ATTRIBUTE_ID(+)
                               AND CTA.ENABLE_FLAG = 'Y'
                               AND CTA.ATTRIBUTE_NAME =
                                      NVL (P_ATTRIBUTE5, CTA.ATTRIBUTE_NAME)
                               AND NVL (CTAA.DEFAULT_VALUE, 'X') =
                                      NVL (L_ATTR_MATCH5,              --P_ATTRIBUTE5_VAL,
                                                         NVL (CTAA.DEFAULT_VALUE, 'X')));

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TEMPLATE_SEARCH;

   PROCEDURE GET_TEMPLATE_LIST (P_TEMPLATE_LIST_TBL   OUT G_REF_CUR_TYP,
                                P_ERR_FLG             OUT VARCHAR2,
                                P_ERR_MSG             OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT EZTABLEID,
                  EZINCOMPANYCODE,
                  TEMPLATE_ID,
                  TEMPLATE_NAME,
                  TEMPLATE_TYPE,
                  TEMPLATE_STATUS,
                  TEMPLATE_DESCRIPTION,
                  ITEM_ASSIGNED,
                  TEMPLATE_HDR_VERSION,
                  CREATED_BY,
                  CREATION_DATE,
                  LAST_UPDATE_BY,
                  LAST_UPDATE_DATE
             FROM CANON_E008_TEMPLATE_HDR_TBL
         ORDER BY TEMPLATE_ID ASC;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_TEMPLATE_LIST;

   PROCEDURE GET_TEMPLATE_ATTRIBUTES (P_CATEGORY_NAME             IN     VARCHAR2,
                                      P_TEMPLATE_ATTRIBUTES_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                      OUT VARCHAR2,
                                      P_ERR_MSG                      OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_ATTRIBUTES_TBL FOR
           SELECT EZTABLEID,
                  EZINCOMPANYCODE,
                  ATTRIBUTE_ID,
                  WORKBENCH_DISPLAY,
                  DISPLAY_SORT,
                  CATEGORY_NAME,
                  APPROVAL_GROUP_OWNER,
                  ATTRIBUTE_NAME,
                  TEMPLATE_ATTR_VERSION,
                  CREATED_BY,
                  --'ADB' CREATED_BY_NAME,
                  SUBSTR (E008_WB_TBL_MAP, 1, 25) CREATED_BY_NAME,
                  CREATION_DATE,
                  LAST_UPDATE_BY,
                  --'ADB' LAST_UPDATE_BY_NAME,
                  LOV_FLAG LAST_UPDATE_BY_NAME,
                  LAST_UPDATE_DATE
             FROM CANON_E008_TEMPLATE_ATTR_TBL
            WHERE     CATEGORY_NAME = NVL (P_CATEGORY_NAME, CATEGORY_NAME)
                  AND ENABLE_FLAG = 'Y'
         ORDER BY CASE WORKBENCH_DISPLAY
                     WHEN 'MAIN' THEN 1
                     WHEN 'ADDITIONAL' THEN 2
                     ELSE 3
                  END,
                  DISPLAY_SORT;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_TEMPLATE_ATTRIBUTES;

   PROCEDURE CREATE_TEMPLATE_HEADER (P_TEMPLATE_REC   IN     CANON_E008_TEMPL_REC,
                                     P_TEMPLATE_ID       OUT NUMBER,
                                     P_ERR_FLG           OUT VARCHAR2,
                                     P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG                VARCHAR2 (2000);
      L_INDEX                  NUMBER;
      L_TEMPLATE_ID            NUMBER;
      L_SOURCE_TEMPLATE_ID     NUMBER;
      L_SOURCE_TEMPLATE_NAME   VARCHAR2 (100);
   BEGIN
      IF P_TEMPLATE_REC.TEMPLATE_NAME IS NULL
      THEN
         P_ERR_MSG := 'Template Name must be entered';
         P_ERR_FLG := 'E';
      ELSE
         BEGIN
            SELECT TEMPLATE_ID
              INTO L_TEMPLATE_ID
              FROM CANON_E008_TEMPLATE_HDR_TBL
             WHERE TEMPLATE_NAME = P_TEMPLATE_REC.TEMPLATE_NAME;
         EXCEPTION
            WHEN OTHERS
            THEN
               L_TEMPLATE_ID := NULL;
         END;

         IF L_TEMPLATE_ID IS NOT NULL
         THEN
            P_ERR_MSG := 'Template Already Exist';
            P_ERR_FLG := 'E';
         ELSE
            BEGIN
               SELECT CANON_E008_TEMPLATE_HDR_SEQ.NEXTVAL INTO L_TEMPLATE_ID FROM DUAL;
            EXCEPTION
               WHEN OTHERS
               THEN
                  L_TEMPLATE_ID := NULL;
            END;

            P_TEMPLATE_ID := L_TEMPLATE_ID;

            IF P_TEMPLATE_REC.SOURCE_TEMPLATE_NAME IS NOT NULL
            THEN
               BEGIN
                  SELECT TEMPLATE_ID
                    INTO L_SOURCE_TEMPLATE_ID
                    FROM CANON_E008_TEMPLATE_HDR_TBL
                   WHERE TEMPLATE_NAME = P_TEMPLATE_REC.SOURCE_TEMPLATE_NAME;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     L_SOURCE_TEMPLATE_ID := NULL;
               END;
            END IF;

            INSERT INTO CANON_E008_TEMPLATE_HDR_TBL (EZTABLEID,
                                                     EZINCOMPANYCODE,
                                                     TEMPLATE_ID,
                                                     TEMPLATE_NAME,
                                                     TEMPLATE_TYPE,
                                                     TEMPLATE_STATUS,
                                                     TEMPLATE_DESCRIPTION,
                                                     SOURCE_TEMPLATE_ID,
                                                     ITEM_ASSIGNED,
                                                     TEMPLATE_HDR_VERSION,
                                                     CREATED_BY,
                                                     CREATION_DATE,
                                                     LAST_UPDATE_BY,
                                                     LAST_UPDATE_DATE)
                 VALUES ('CANON_E008_TEMPLATE_HDR_TBL',
                         'ADB',
                         L_TEMPLATE_ID,
                         UPPER (P_TEMPLATE_REC.TEMPLATE_NAME),
                         P_TEMPLATE_REC.TEMPLATE_TYPE,
                         P_TEMPLATE_REC.TEMPLATE_STATUS,
                         P_TEMPLATE_REC.TEMPLATE_DESCRIPTION,
                         L_SOURCE_TEMPLATE_ID,
                         P_TEMPLATE_REC.ITEM_ASSIGNED,
                         1,
                         P_TEMPLATE_REC.CREATED_BY,
                         SYSDATE,
                         P_TEMPLATE_REC.LAST_UPDATE_BY,
                         SYSDATE);

            IF SQL%ROWCOUNT > 0
            THEN
               FOR TEMPL_ATTR_REC IN (SELECT ATTRIBUTE_ID,
                                             REQUIRED_VALUE,
                                             VALID,
                                             DEFAULT_VALUE
                                        FROM CANON_E008_TEMPL_ATTR_ASGN_TBL
                                       WHERE TEMPLATE_ID = L_SOURCE_TEMPLATE_ID)
               LOOP
                  INSERT INTO CANON_E008_TEMPL_ATTR_ASGN_TBL (EZTABLEID,
                                                              EZINCOMPANYCODE,
                                                              TEMPLATE_ID,
                                                              ATTRIBUTE_ID,
                                                              REQUIRED_VALUE,
                                                              VALID,
                                                              DEFAULT_VALUE,
                                                              TEMPL_ATTR_ASGN_VER,
                                                              CREATED_BY,
                                                              CREATION_DATE,
                                                              LAST_UPDATE_BY,
                                                              LAST_UPDATE_DATE)
                       VALUES ('CANON_E008_TEMPL_ATTR_ASGN_TBL',
                               'ADB',
                               L_TEMPLATE_ID,
                               TEMPL_ATTR_REC.ATTRIBUTE_ID,
                               TEMPL_ATTR_REC.REQUIRED_VALUE,
                               TEMPL_ATTR_REC.VALID,
                               TEMPL_ATTR_REC.DEFAULT_VALUE,
                               1,
                               P_TEMPLATE_REC.CREATED_BY,
                               SYSDATE,
                               P_TEMPLATE_REC.LAST_UPDATE_BY,
                               SYSDATE);
               END LOOP;
            END IF;

            COMMIT;
            P_ERR_FLG := 'S';
         END IF;                                               --L_TEMPLATE_ID IS NOT NULL
      END IF;                                               --P_TEMPLATE_REC.TEMPLATE_NAME
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         P_TEMPLATE_ID := NULL;
         ROLLBACK;
   END CREATE_TEMPLATE_HEADER;

   PROCEDURE SAVE_TEMPLATE_HEADER (P_TEMPLATE_TBL   IN     CANON_E008_TEMPL_TBL_TYPE,
                                   P_ERR_FLG           OUT VARCHAR2,
                                   P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG    VARCHAR2 (2000);
      L_INDEX      NUMBER;
      L_TEMPL_ID   NUMBER;
   BEGIN
      L_INDEX := P_TEMPLATE_TBL.FIRST;

      WHILE L_INDEX IS NOT NULL
      LOOP
         L_TEMPL_ID := P_TEMPLATE_TBL (L_INDEX).TEMPLATE_ID;

         UPDATE CANON_E008_TEMPLATE_HDR_TBL
            SET TEMPLATE_TYPE = P_TEMPLATE_TBL (L_INDEX).TEMPLATE_TYPE,
                TEMPLATE_STATUS = P_TEMPLATE_TBL (L_INDEX).TEMPLATE_STATUS,
                TEMPLATE_DESCRIPTION = P_TEMPLATE_TBL (L_INDEX).TEMPLATE_DESCRIPTION,
                ITEM_ASSIGNED = P_TEMPLATE_TBL (L_INDEX).ITEM_ASSIGNED,
                LAST_UPDATE_BY = P_TEMPLATE_TBL (L_INDEX).LAST_UPDATE_BY,
                LAST_UPDATE_DATE = SYSDATE
          WHERE TEMPLATE_ID = L_TEMPL_ID;

         L_INDEX := P_TEMPLATE_TBL.NEXT (L_INDEX);
      END LOOP;

      COMMIT;
      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         ROLLBACK;
   END SAVE_TEMPLATE_HEADER;

   PROCEDURE SAVE_TEMPLATE_ATTRIBUTES (
      P_TEMPL_ATTR_TBL   IN     CANON_E008_TEMPL_ATTR_TBL_TYPE,
      P_ERR_FLG             OUT VARCHAR2,
      P_ERR_MSG             OUT VARCHAR2)
   IS
      L_ERR_MSG        VARCHAR2 (2000);
      L_INDEX          NUMBER;
      L_ATTRIBUTE_ID   NUMBER;
   BEGIN
      L_INDEX := P_TEMPL_ATTR_TBL.FIRST;

      WHILE L_INDEX IS NOT NULL
      LOOP
         L_ATTRIBUTE_ID := P_TEMPL_ATTR_TBL (L_INDEX).ATTRIBUTE_ID;

         UPDATE CANON_E008_TEMPLATE_ATTR_TBL
            SET WORKBENCH_DISPLAY =
                   NVL (P_TEMPL_ATTR_TBL (L_INDEX).WORKBENCH_DISPLAY, WORKBENCH_DISPLAY),
                DISPLAY_SORT = NVL (P_TEMPL_ATTR_TBL (L_INDEX).DISPLAY_SORT, DISPLAY_SORT),
                CATEGORY_NAME =
                   NVL (P_TEMPL_ATTR_TBL (L_INDEX).CATEGORY_NAME, CATEGORY_NAME),
                APPROVAL_GROUP_OWNER =
                   NVL (P_TEMPL_ATTR_TBL (L_INDEX).APPROVAL_GROUP_OWNER,
                        APPROVAL_GROUP_OWNER),
                --TEMPLATE_ATTR_VERSION = TEMPLATE_ATTR_VERSION + 1,
                --LAST_UPDATE_BY = p_templ_attr_tbl (l_index).LAST_UPDATE_BY,
                LAST_UPDATE_DATE = SYSDATE
          WHERE ATTRIBUTE_ID = L_ATTRIBUTE_ID AND ENABLE_FLAG = 'Y';

         L_INDEX := P_TEMPL_ATTR_TBL.NEXT (L_INDEX);
      END LOOP;

      COMMIT;
      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         ROLLBACK;
   END SAVE_TEMPLATE_ATTRIBUTES;

   PROCEDURE SAVE_TEMPL_ATTR_ASSGMT (
      P_TEMPL_ATTR_ASSGN_TBL   IN     CANON_E008_TEMPL_AA_TBL_TYPE,
      P_ERR_FLG                   OUT VARCHAR2,
      P_ERR_MSG                   OUT VARCHAR2)
   IS
      L_ERR_MSG        VARCHAR2 (2000);
      L_INDEX          NUMBER;
      L_TEMPL_ID       NUMBER;
      L_ATTRIBUTE_ID   NUMBER;
      L_CNT            NUMBER;
   BEGIN
      L_INDEX := P_TEMPL_ATTR_ASSGN_TBL.FIRST;

      WHILE L_INDEX IS NOT NULL
      LOOP
         L_TEMPL_ID := P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).TEMPLATE_ID;
         L_ATTRIBUTE_ID := P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).ATTRIBUTE_ID;
         L_CNT := 0;

         BEGIN
            SELECT 1
              INTO L_CNT
              FROM CANON_E008_TEMPL_ATTR_ASGN_TBL
             WHERE TEMPLATE_ID = L_TEMPL_ID AND ATTRIBUTE_ID = L_ATTRIBUTE_ID;
         EXCEPTION
            WHEN OTHERS
            THEN
               L_CNT := 0;
         END;

         IF L_CNT = 0
         THEN
            INSERT INTO CANON_E008_TEMPL_ATTR_ASGN_TBL (EZTABLEID,
                                                        EZINCOMPANYCODE,
                                                        TEMPLATE_ID,
                                                        ATTRIBUTE_ID,
                                                        REQUIRED_VALUE,
                                                        VALID,
                                                        DEFAULT_VALUE,
                                                        TEMPL_ATTR_ASGN_VER,
                                                        CREATED_BY,
                                                        CREATION_DATE,
                                                        LAST_UPDATE_BY,
                                                        LAST_UPDATE_DATE)
                    VALUES (
                              'CANON_E008_TEMPL_ATTR_ASGN_TBL',
                              'ADB',
                              P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).TEMPLATE_ID,
                              P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).ATTRIBUTE_ID,
                              DECODE (P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).REQUIRED_VALUE,
                                      'on', 'YES',
                                      'NO'),
                              DECODE (P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).VALID,
                                      'on', 'YES',
                                      'NO'),
                              P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).DEFAULT_VALUE,
                              1,
                              P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).CREATED_BY,
                              SYSDATE,
                              P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).LAST_UPDATE_BY,
                              SYSDATE);
         ELSE
            UPDATE CANON_E008_TEMPL_ATTR_ASGN_TBL
               SET REQUIRED_VALUE =
                      DECODE (P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).REQUIRED_VALUE,
                              'on', 'YES',
                              'NO'),
                   VALID =
                      DECODE (P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).VALID, 'on', 'YES', 'NO'),
                   DEFAULT_VALUE = P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).DEFAULT_VALUE,
                   TEMPL_ATTR_ASGN_VER = TEMPL_ATTR_ASGN_VER + 1,
                   LAST_UPDATE_BY = P_TEMPL_ATTR_ASSGN_TBL (L_INDEX).LAST_UPDATE_BY,
                   LAST_UPDATE_DATE = SYSDATE
             WHERE TEMPLATE_ID = L_TEMPL_ID AND ATTRIBUTE_ID = L_ATTRIBUTE_ID;
         END IF;

         L_INDEX := P_TEMPL_ATTR_ASSGN_TBL.NEXT (L_INDEX);
      END LOOP;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         ROLLBACK;
   END SAVE_TEMPL_ATTR_ASSGMT;

   PROCEDURE SAVE_TEMPLATE_SEARCH (P_TEMPLATE_NAME             IN     VARCHAR2,
                                   P_TEMPLATE_TYPE             IN     VARCHAR2,
                                   P_TEMPLATE_CAT              IN     VARCHAR2,
                                   P_TEMPLATE_STATUS           IN     VARCHAR2,
                                   P_TEMPL_ITEM_ASSIGN         IN     VARCHAR2,
                                   P_TEMPLATE_DESC             IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE1       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE1_VAL   IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE2       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE2_VAL   IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE3       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE3_VAL   IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE4       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE4_VAL   IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE5       IN     VARCHAR2,
                                   P_TEMPLATE_ATTRIBUTE5_VAL   IN     VARCHAR2,
                                   P_USER                      IN     VARCHAR2,
                                   P_SAVED_NAME                IN     VARCHAR2,
                                   P_ERR_FLG                      OUT VARCHAR2,
                                   P_ERR_MSG                      OUT VARCHAR2)
   IS
      L_ERR_MSG    VARCHAR2 (2000);
      L_INDEX      NUMBER;
      L_TEMPL_ID   NUMBER;
   BEGIN
      INSERT INTO CANON_E008_TEMPL_SEARCH_TBL (EZTABLEID,
                                               EZINCOMPANYCODE,
                                               USER_ID,
                                               SEARCH_NAME,
                                               TEMPLATE_NAME,
                                               TEMPLATE_TYPE,
                                               TEMPLATE_STATUS,
                                               TEMPLATE_CATEGORY,
                                               TEMPLATE_DESCRIPTION,
                                               ITEM_ASSIGNED,
                                               ATTRIBUTE_NAME1,
                                               ATTRIBUTE_VAL1,
                                               ATTRIBUTE_NAME2,
                                               ATTRIBUTE_VAL2,
                                               ATTRIBUTE_NAME3,
                                               ATTRIBUTE_VAL3,
                                               ATTRIBUTE_NAME4,
                                               ATTRIBUTE_VAL4,
                                               ATTRIBUTE_NAME5,
                                               ATTRIBUTE_VAL5,
                                               CREATED_BY,
                                               CREATION_DATE,
                                               LAST_UPDATE_BY,
                                               LAST_UPDATE_DATE)
           VALUES ('CANON_E008_TEMPL_SEARCH_TBL',
                   'ADB',
                   P_USER,
                   P_SAVED_NAME,
                   P_TEMPLATE_NAME,
                   P_TEMPLATE_TYPE,
                   P_TEMPLATE_STATUS,
                   P_TEMPLATE_CAT,
                   P_TEMPLATE_DESC,
                   P_TEMPL_ITEM_ASSIGN,
                   P_TEMPLATE_ATTRIBUTE1,
                   P_TEMPLATE_ATTRIBUTE1_VAL,
                   P_TEMPLATE_ATTRIBUTE2,
                   P_TEMPLATE_ATTRIBUTE2_VAL,
                   P_TEMPLATE_ATTRIBUTE3,
                   P_TEMPLATE_ATTRIBUTE3_VAL,
                   P_TEMPLATE_ATTRIBUTE4,
                   P_TEMPLATE_ATTRIBUTE4_VAL,
                   P_TEMPLATE_ATTRIBUTE5,
                   P_TEMPLATE_ATTRIBUTE5_VAL,
                   P_USER,
                   SYSDATE,
                   P_USER,
                   SYSDATE);

      COMMIT;
      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         ROLLBACK;
   END SAVE_TEMPLATE_SEARCH;

   PROCEDURE GET_SAVEDSEARCH_LIST (P_SAVEDSEARCH_LIST_TBL   OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_SAVEDSEARCH_LIST_TBL FOR
           SELECT SEARCH_NAME
             FROM CANON_E008_TEMPL_SEARCH_TBL
         ORDER BY CREATION_DATE ASC;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_SAVEDSEARCH_LIST;

   PROCEDURE GET_SAVEDSEARCH_VALUES (P_SAVEDSEARCH_NAME         VARCHAR2,
                                     P_USER                     VARCHAR2,
                                     P_SAVEDSEARCH_VALUES   OUT G_REF_CUR_TYP,
                                     P_ERR_FLG              OUT VARCHAR2,
                                     P_ERR_MSG              OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_SAVEDSEARCH_NAME IS NOT NULL
      THEN
         OPEN P_SAVEDSEARCH_VALUES FOR
            SELECT '-'
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT template_name
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT TEMPLATE_TYPE
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT TEMPLATE_CATEGORY
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT TEMPLATE_STATUS
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT NVL (ITEM_ASSIGNED, '')
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT NVL (TEMPLATE_DESCRIPTION, '')
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT ATTRIBUTE_NAME1
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT NVL (ATTRIBUTE_VAL1, '')
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT ATTRIBUTE_NAME2
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT NVL (ATTRIBUTE_VAL2, '')
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT ATTRIBUTE_NAME3
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT NVL (ATTRIBUTE_VAL3, '')
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT ATTRIBUTE_NAME4
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT NVL (ATTRIBUTE_VAL4, '')
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT ATTRIBUTE_NAME5
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT NVL (ATTRIBUTE_VAL5, '')
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER
            UNION ALL
            SELECT '-'
              FROM canon_e008_templ_search_tbl
             WHERE search_name = P_SAVEDSEARCH_NAME AND user_id = P_USER;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_SAVEDSEARCH_VALUES;

   ---Start New LOV APIs for Item Template performance

   PROCEDURE MANUFACTURER_LIST (P_LIST_CODE           IN     VARCHAR2,
                                P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                P_ERR_FLG                OUT VARCHAR2,
                                P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT MDSE_ITEM_MNF_CD LISTCODE, UPPER (MDSE_ITEM_MNF_NM) LISTNAME
             --MDSE_ITEM_MNF_CD || '-' || MDSE_ITEM_MNF_NM LISTNAME
             FROM MDSE_ITEM_MNF
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND MDSE_ITEM_MNF_CD = NVL (P_LIST_CODE, MDSE_ITEM_MNF_CD)
         ORDER BY MDSE_ITEM_MNF_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MANUFACTURER_LIST;

   PROCEDURE ITEM_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT MDSE_ITEM_TP_CD LISTCODE, UPPER (MDSE_ITEM_TP_NM) LISTNAME
             -- MDSE_ITEM_TP_CD || '-' || UPPER (MDSE_ITEM_TP_NM) LISTNAME
             FROM MDSE_ITEM_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND MDSE_ITEM_TP_CD = NVL (P_LIST_CODE, MDSE_ITEM_TP_CD)
         ORDER BY MDSE_ITEM_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END ITEM_TYPE_LIST;

   PROCEDURE YES_NO_LIST (P_LIST_CODE           IN     VARCHAR2,
                          P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                          P_ERR_FLG                OUT VARCHAR2,
                          P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
         SELECT '' LISTCODE, '' LISTNAME FROM DUAL
         UNION
         SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL
         UNION
         SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
         ORDER BY 1 DESC;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END YES_NO_LIST;

   PROCEDURE MERCHANDISE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                    P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT COA_PROJ_CD LISTCODE, COA_PROJ_CD || '-' || UPPER (COA_PROJ_NM) LISTNAME
             FROM COA_PROJ
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND COA_PROJ_CD = NVL (P_LIST_CODE, COA_PROJ_CD)
         ORDER BY COA_PROJ_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MERCHANDISE_TYPE_LIST;

   PROCEDURE ITEM_CLASSIFICATION_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT MDSE_ITEM_CLS_TP_CD LISTCODE, UPPER (MDSE_ITEM_CLS_TP_NM) LISTNAME
             FROM MDSE_ITEM_CLS_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND MDSE_ITEM_CLS_TP_CD = NVL (P_LIST_CODE, MDSE_ITEM_CLS_TP_CD)
         ORDER BY MDSE_ITEM_CLS_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END ITEM_CLASSIFICATION_LIST;

   PROCEDURE PRODUCT_CODE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                P_ERR_FLG                OUT VARCHAR2,
                                P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT COA_PROD_CD LISTCODE,
                  COA_PROD_CD || '-' || REPLACE (UPPER (COA_PROD_NM), '''', '') LISTNAME
             FROM COA_PROD
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND COA_PROD_CD = NVL (P_LIST_CODE, COA_PROD_CD)
         ORDER BY COA_PROD_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PRODUCT_CODE_LIST;


   PROCEDURE MARKETING_MODEL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
      l_record_count    NUMBER;
      l_Flag            VARCHAR2 (1):='N';
      L_LIST_CODE   VARCHAR2 (200);
   BEGIN
    /*
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT MKT_MDL_CD LISTCODE, REPLACE (UPPER (MKT_MDL_NM), '''', '') LISTNAME
             FROM MKT_MDL
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  --AND MKT_MDL_CD = NVL (P_LIST_CODE, MKT_MDL_CD)
                   AND (   MKT_MDL_CD = P_LIST_CODE
                       OR UPPER (MKT_MDL_NM) LIKE '%'||
					      UPPER (DECODE(P_LIST_CODE,'-00000',MKT_MDL_NM, NVL(P_LIST_CODE,'-999999'))) || '%' )
         ORDER BY REPLACE (UPPER (MKT_MDL_NM), '''', '');

    */

      IF SUBSTR(P_LIST_CODE,1,3)='NF-' THEN

        L_LIST_CODE := SUBSTR(P_LIST_CODE,4,LENGTH(P_LIST_CODE));
        l_flag := 'Y';
        OPEN P_TEMPLATE_LIST_TBL FOR
               SELECT MKT_MDL_CD LISTCODE, REPLACE (UPPER (MKT_MDL_NM), '''', '') LISTNAME
                 FROM MKT_MDL
                WHERE     GLBL_CMPY_CD = 'ADB'
                      AND EZCANCELFLAG = '0'
                      --AND MKT_MDL_CD = NVL (P_LIST_CODE, MKT_MDL_CD)
                       AND    MKT_MDL_CD = L_LIST_CODE
             ORDER BY REPLACE (UPPER (MKT_MDL_NM), '''', '') desc;
      END IF;

      IF l_flag = 'N' THEN

          SELECT count(1)
            INTO l_record_count
            FROM MKT_MDL
           WHERE    GLBL_CMPY_CD = 'ADB'
             AND EZCANCELFLAG = '0'
             AND (   MKT_MDL_CD = P_LIST_CODE
                       OR UPPER (MKT_MDL_NM) LIKE '%'||
					      UPPER (DECODE(P_LIST_CODE,'-00000',MKT_MDL_NM, NVL(P_LIST_CODE,'-999999'))) || '%' );
         --ORDER BY REPLACE (UPPER (MKT_MDL_NM), '''', '');

          IF l_record_count = 0 THEN
               IF P_LIST_CODE IS NOT NULL AND P_LIST_CODE <> '-#*' THEN
                OPEN P_TEMPLATE_LIST_TBL FOR
                  SELECT P_LIST_CODE LISTCODE, P_LIST_CODE LISTNAME
                  FROM DUAL;
               ELSE
                OPEN P_TEMPLATE_LIST_TBL FOR
                  SELECT ' ' LISTCODE, ' ' LISTNAME
                  FROM DUAL;

               END IF;

          ELSE

                  OPEN P_TEMPLATE_LIST_TBL FOR
                       SELECT MKT_MDL_CD LISTCODE, REPLACE (UPPER (MKT_MDL_NM), '''', '') LISTNAME
                         FROM MKT_MDL
                        WHERE     GLBL_CMPY_CD = 'ADB'
                              AND EZCANCELFLAG = '0'
                              --AND MKT_MDL_CD = NVL (P_LIST_CODE, MKT_MDL_CD)
                               AND (   MKT_MDL_CD = P_LIST_CODE
                                   OR UPPER (MKT_MDL_NM) LIKE '%'||
                                      UPPER (DECODE(P_LIST_CODE,'-00000',MKT_MDL_NM, NVL(P_LIST_CODE,'-999999'))) || '%' )
                     ORDER BY REPLACE (UPPER (MKT_MDL_NM), '''', '') desc;

          END IF;

      END IF;


      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MARKETING_MODEL_LIST;

   PROCEDURE MARKETING_SEGMENT_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT MKT_MDL_SEG_CD LISTCODE,
                  REPLACE (UPPER (MKT_MDL_SEG_NM), '''', '') LISTNAME
             FROM MKT_MDL_SEG
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND MKT_MDL_SEG_CD = NVL (P_LIST_CODE, MKT_MDL_SEG_CD)
         ORDER BY REPLACE (UPPER (MKT_MDL_SEG_NM), '''', '');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MARKETING_SEGMENT_LIST;

   PROCEDURE UNIT_OF_MEASURE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PKG_UOM_CD LISTCODE, UPPER (PKG_UOM_NM) LISTNAME
             FROM PKG_UOM
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND PKG_UOM_CD = NVL (P_LIST_CODE, PKG_UOM_CD)
         ORDER BY PKG_UOM_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END UNIT_OF_MEASURE_LIST;

   PROCEDURE UOM_CLASS_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PKG_UOM_CLS_CD LISTCODE, UPPER (PKG_UOM_CLS_NM) LISTNAME
             FROM PKG_UOM_CLS
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND PKG_UOM_CLS_CD = NVL (P_LIST_CODE, PKG_UOM_CLS_CD)
         ORDER BY PKG_UOM_CLS_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END UOM_CLASS_LIST;

   PROCEDURE RETURN_CONTROL_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT RTRN_CTRL_TP_CD LISTCODE, UPPER (RTRN_CTRL_TP_NM) LISTNAME
             FROM RTRN_CTRL_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND RTRN_CTRL_TP_CD = NVL (P_LIST_CODE, RTRN_CTRL_TP_CD)
         ORDER BY RTRN_CTRL_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END RETURN_CONTROL_TYPE_LIST;

   PROCEDURE PRODUCT_LEVEL1_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PROD_CTRL_CD LISTCODE,
                  UPPER (PROD_CTRL_NM)LISTNAME
                  --REPLACE (UPPER (PROD_CTRL_NM), ',', ' ') LISTNAME
             FROM PROD_CTRL
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
				  AND MDSE_STRU_ELMNT_TP_CD = 'PLG'
                  AND PROD_CTRL_CD = NVL (P_LIST_CODE, PROD_CTRL_CD)
         ORDER BY UPPER (PROD_CTRL_NM);
         --ORDER BY REPLACE (UPPER (PROD_CTRL_NM), ',', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PRODUCT_LEVEL1_LIST;

   PROCEDURE PRODUCT_LEVEL2_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PROD_CTRL_CD LISTCODE,
                  UPPER (PROD_CTRL_NM) LISTNAME
                  --REPLACE (UPPER (PROD_CTRL_NM), ',', ' ') LISTNAME
             FROM PROD_CTRL
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
				  AND MDSE_STRU_ELMNT_TP_CD = 'PL'
                  AND PROD_CTRL_CD = NVL (P_LIST_CODE, PROD_CTRL_CD)
         ORDER BY UPPER (PROD_CTRL_NM);
         --ORDER BY REPLACE (UPPER (PROD_CTRL_NM), ',', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PRODUCT_LEVEL2_LIST;

   PROCEDURE PRODUCT_LEVEL3_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PROD_CTRL_CD LISTCODE,
                  UPPER (PROD_CTRL_NM) LISTNAME
                  --REPLACE (UPPER (PROD_CTRL_NM), ',', ' ') LISTNAME
             FROM PROD_CTRL
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
				  AND MDSE_STRU_ELMNT_TP_CD = 'PL2'
                  AND PROD_CTRL_CD = NVL (P_LIST_CODE, PROD_CTRL_CD)
         ORDER BY UPPER (PROD_CTRL_NM);
         --ORDER BY REPLACE (UPPER (PROD_CTRL_NM), ',', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PRODUCT_LEVEL3_LIST;

   PROCEDURE PRODUCT_LEVEL4_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2,
                                  P_LEVEL3_CODE           IN     VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PROD_CTRL_CD LISTCODE,
                  UPPER (PROD_CTRL_NM) LISTNAME
                  --REPLACE (UPPER (PROD_CTRL_NM), ',', ' ') LISTNAME
             FROM PROD_CTRL
            WHERE GLBL_CMPY_CD = 'ADB'
              AND EZCANCELFLAG = '0'
              AND MDSE_STRU_ELMNT_TP_CD = 'PL3'
              AND PROD_CTRL_CD = NVL (P_LIST_CODE, PROD_CTRL_CD)
              AND SCD_PROD_HRCH_CD = P_LEVEL3_CODE
         ORDER BY UPPER (PROD_CTRL_NM);
         --ORDER BY REPLACE (UPPER (PROD_CTRL_NM), ',', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PRODUCT_LEVEL4_LIST;

   PROCEDURE PRODUCT_LEVEL4_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PROD_CTRL_CD LISTCODE,
                  UPPER (PROD_CTRL_NM) LISTNAME
                  --REPLACE (UPPER (PROD_CTRL_NM), ',', ' ') LISTNAME
             FROM PROD_CTRL
            WHERE GLBL_CMPY_CD = 'ADB'
              AND EZCANCELFLAG = '0'
              AND MDSE_STRU_ELMNT_TP_CD = 'PL3'
              AND PROD_CTRL_CD = NVL (P_LIST_CODE, PROD_CTRL_CD)
         ORDER BY UPPER (PROD_CTRL_NM);
         --ORDER BY REPLACE (UPPER (PROD_CTRL_NM), ',', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PRODUCT_LEVEL4_LIST;

   PROCEDURE PRODUCT_LEVEL5_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DISTINCT PROD_CTRL_CD LISTCODE,
                  UPPER (PROD_CTRL_NM) LISTNAME
                  --REGEXP_REPLACE (UPPER (PROD_CTRL_NM), '(,|'')', ' ') LISTNAME
             FROM PROD_CTRL
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND MDSE_STRU_ELMNT_TP_CD = 'PL4'
                  AND (   PROD_CTRL_CD = P_LIST_CODE
				       OR UPPER (PROD_CTRL_NM) LIKE '%'||
					      UPPER (DECODE(P_LIST_CODE,'-00000',PROD_CTRL_NM, NVL(P_LIST_CODE,'-999999'))) || '%' )
         ORDER BY UPPER (PROD_CTRL_NM);
         --ORDER BY REGEXP_REPLACE (UPPER (PROD_CTRL_NM), '(,|'')', ' ');


         /*  SELECT PROD_CTRL_CD LISTCODE,
                  REPLACE (UPPER (PROD_CTRL_NM), ',', ' ') LISTNAME
             FROM PROD_CTRL
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
				  AND MDSE_STRU_ELMNT_TP_CD = 'PL4'
                  AND PROD_CTRL_CD = NVL (P_LIST_CODE, PROD_CTRL_CD)
         ORDER BY REPLACE (UPPER (PROD_CTRL_NM), ',', ' ');  */



      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PRODUCT_LEVEL5_LIST;

   /*
      PROCEDURE ACC_SEG_LIST (P_LIST_CODE      IN  VARCHAR2,P_TEMPLATE_LIST_TBL   OUT G_REF_CUR_TYP,
                              P_ERR_FLG             OUT VARCHAR2,
                              P_ERR_MSG             OUT VARCHAR2)
      IS
         L_ERR_MSG   VARCHAR2 (2000);
      BEGIN
         OPEN P_TEMPLATE_LIST_TBL FOR
              SELECT UPPER(COA_ACCT_NM)
                FROM COA_ACCT
               WHERE GLBL_CMPY_CD = 'ADB' AND EZCANCELFLAG = '0'      AND MDSE_ITEM_MNF_CD = NVL(P_LIST_CODE, MDSE_ITEM_MNF_CD)
            ORDER BY COA_ACCT_NM;

         P_ERR_FLG := 'S';
      EXCEPTION
         WHEN OTHERS
         THEN
            L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
            P_ERR_FLG := 'E';
            P_ERR_MSG := L_ERR_MSG;
      END ACC_SEG_LIST;
   */
   PROCEDURE COST_OF_GOODS_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT COA_ACCT_CD LISTCODE, --UPPER (COA_ACCT_NM) LISTNAME
                  COA_ACCT_CD || '-' || REPLACE (UPPER (COA_ACCT_NM), '''', '') LISTNAME
             FROM COA_ACCT
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND COA_ACCT_CD = NVL (P_LIST_CODE, COA_ACCT_CD)
         ORDER BY COA_ACCT_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END COST_OF_GOODS_LIST;

   PROCEDURE EXPENSE_LIST (P_LIST_CODE           IN     VARCHAR2,
                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                           P_ERR_FLG                OUT VARCHAR2,
                           P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT COA_ACCT_CD LISTCODE, --UPPER (COA_ACCT_NM) LISTNAME
                  COA_ACCT_CD || '-' || REPLACE (UPPER (COA_ACCT_NM), '''', '') LISTNAME
             FROM COA_ACCT
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND COA_ACCT_CD = NVL (P_LIST_CODE, COA_ACCT_CD)
                  --and CC_EXP_FLG = 'Y'
         ORDER BY COA_ACCT_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END EXPENSE_LIST;

   PROCEDURE REVENUE_LIST (P_LIST_CODE           IN     VARCHAR2,
                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                           P_ERR_FLG                OUT VARCHAR2,
                           P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT COA_ACCT_CD LISTCODE, -- UPPER (COA_ACCT_CD - COA_ACCT_NM) LISTNAME
                  COA_ACCT_CD || '-' || REPLACE (UPPER (COA_ACCT_NM), '''', '') LISTNAME
             FROM COA_ACCT
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND COA_ACCT_CD = NVL (P_LIST_CODE, COA_ACCT_CD)
                  --AND NVL(cc_exp_flg,'N') <> 'Y'
         ORDER BY COA_ACCT_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END REVENUE_LIST;

   PROCEDURE ACCRUAL_LIST (P_LIST_CODE           IN     VARCHAR2,
                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                           P_ERR_FLG                OUT VARCHAR2,
                           P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT COA_ACCT_CD LISTCODE, UPPER (COA_ACCT_NM) LISTNAME
             FROM COA_ACCT
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND COA_ACCT_CD = NVL (P_LIST_CODE, COA_ACCT_CD)
                  AND NVL(cc_exp_flg,'N') <> 'Y'
         ORDER BY COA_ACCT_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END ACCRUAL_LIST;


   PROCEDURE OWNING_DIVISION_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT LINE_BIZ_TP_CD LISTCODE, UPPER (LINE_BIZ_TP_NM) LISTNAME
             FROM LINE_BIZ_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND LINE_BIZ_TP_CD = NVL (P_LIST_CODE, LINE_BIZ_TP_CD)
         ORDER BY LINE_BIZ_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END OWNING_DIVISION_LIST;

   PROCEDURE SERIAL_CONTROL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE IS NOT NULL
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT P_LIST_CODE LISTCODE, DECODE(P_LIST_CODE,'NOTCONTROLLED','NOT CONTROLLED','ONRECEIPTANDSHIPMENT','ON RECEIPT AND SHIPMENT','ONSHIPMENT','ON SHIPMENT') LISTNAME FROM DUAL;
         --OPEN P_TEMPLATE_LIST_TBL FOR SELECT P_LIST_CODE LISTCODE, P_LIST_CODE LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'NOTCONTROLLED' LISTCODE, 'NOT CONTROLLED' LISTNAME FROM DUAL
            UNION
            SELECT 'ONRECEIPTANDSHIPMENT' LISTCODE, 'ON RECEIPT AND SHIPMENT' LISTNAME
              FROM DUAL
            UNION
            SELECT 'ONSHIPMENT' LISTCODE, 'ON SHIPMENT' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SERIAL_CONTROL_LIST;

   PROCEDURE SERVICE_MODEL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
      l_record_count NUMBER;
      l_Flag            VARCHAR2 (1):='N';
      L_LIST_CODE   VARCHAR2 (200);
   BEGIN


      IF SUBSTR(P_LIST_CODE,1,3)='NF-' THEN

        L_LIST_CODE := SUBSTR(P_LIST_CODE,4,LENGTH(P_LIST_CODE));
        l_flag := 'Y';
        OPEN P_TEMPLATE_LIST_TBL FOR
               SELECT REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ') LISTCODE,
                REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ') LISTNAME
                 FROM MDL_NM
                WHERE     T_GLBL_CMPY_CD = 'ADB'
                      AND EZCANCELFLAG = '0'
                       AND    UPPER (T_MDL_NM) = L_LIST_CODE;
      END IF;

    IF l_flag = 'N' THEN
      SELECT count(1)
            INTO l_record_count
             FROM MDL_NM
            WHERE     T_GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  --AND ( T_MDL_NM = P_LIST_CODE
                  --OR  UPPER (T_MDL_NM) LIKE UPPER(P_LIST_CODE) )
				  AND UPPER (T_MDL_NM) LIKE '%'||
				      UPPER (DECODE(P_LIST_CODE,'-00000',T_MDL_NM, NVL(P_LIST_CODE,'-999999'))) || '%'
                  --AND REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ') LIKE
                      --   '%' || NVL (UPPER(P_LIST_CODE), REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ')) || '%'
         ORDER BY REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ');



      IF l_record_count = 0 THEN
           IF P_LIST_CODE IS NOT NULL AND P_LIST_CODE <> '-#*' THEN
            OPEN P_TEMPLATE_LIST_TBL FOR
              SELECT P_LIST_CODE LISTCODE, P_LIST_CODE LISTNAME
              FROM DUAL;
           ELSE
            OPEN P_TEMPLATE_LIST_TBL FOR
              SELECT ' ' LISTCODE, ' ' LISTNAME
              FROM DUAL;

           END IF;

      ELSE

            OPEN P_TEMPLATE_LIST_TBL FOR
                 SELECT REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ') LISTCODE,                --T_MDL_ID
                REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ') LISTNAME
                   FROM MDL_NM
                  WHERE     T_GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0'
                        --AND ( T_MDL_NM = P_LIST_CODE
                        --OR  UPPER (T_MDL_NM) LIKE UPPER(P_LIST_CODE) )
                AND UPPER (T_MDL_NM) LIKE '%'||
                    UPPER (DECODE(P_LIST_CODE,'-00000',T_MDL_NM, NVL(P_LIST_CODE,'-999999'))) || '%'
                        --AND REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ') LIKE
                            --   '%' || NVL (UPPER(P_LIST_CODE), REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ')) || '%'
               ORDER BY REGEXP_REPLACE (UPPER (T_MDL_NM), '(,|'')', ' ') desc;

      END IF;
     END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SERVICE_MODEL_LIST;

   PROCEDURE RETURN_CONTROLLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END RETURN_CONTROLLED_LIST;

   PROCEDURE COVERAGE_BASE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SVC_COV_BASE_TP_CD LISTCODE, UPPER (SVC_COV_BASE_TP_NM) LISTNAME
             FROM SVC_COV_BASE_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SVC_COV_BASE_TP_CD = NVL (P_LIST_CODE, SVC_COV_BASE_TP_CD)
         ORDER BY SVC_COV_BASE_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END COVERAGE_BASE_TYPE_LIST;

   PROCEDURE COVERAGE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SVC_COV_TMPL_TP_CD LISTCODE, UPPER (SVC_COV_TMPL_TP_NM) LISTNAME
             FROM SVC_COV_TMPL_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SVC_COV_TMPL_TP_CD = NVL (P_LIST_CODE, SVC_COV_TMPL_TP_CD)
         ORDER BY SVC_COV_TMPL_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END COVERAGE_TYPE_LIST;

   PROCEDURE SERVICE_ALLOCATION_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SVC_ALLOC_TP_CD LISTCODE, UPPER (SVC_ALLOC_TP_NM) LISTNAME
             FROM SVC_ALLOC_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SVC_ALLOC_TP_CD = NVL (P_LIST_CODE, SVC_ALLOC_TP_CD)
         ORDER BY SVC_ALLOC_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SERVICE_ALLOCATION_TYPE_LIST;

   PROCEDURE TC_OPTION_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SVC_TERM_COND_OPT_CD LISTCODE, UPPER (SVC_TERM_COND_OPT_NM) LISTNAME
             FROM SVC_TERM_COND_OPT
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SVC_TERM_COND_OPT_CD = NVL (P_LIST_CODE, SVC_TERM_COND_OPT_CD)
         ORDER BY SVC_TERM_COND_OPT_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TC_OPTION_LIST;

   PROCEDURE PART_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PRT_ITEM_TP_CD LISTCODE, UPPER (PRT_ITEM_TP_NM) LISTNAME
             FROM PRT_ITEM_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND PRT_ITEM_TP_CD = NVL (P_LIST_CODE, PRT_ITEM_TP_CD)
         ORDER BY PRT_ITEM_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PART_TYPE_LIST;

   PROCEDURE PARTS_DROPSHIP_DISABLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PARTS_DROPSHIP_DISABLED_LIST;

   PROCEDURE PARTS_SURVEY_REQUIRED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                         P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                         P_ERR_FLG                OUT VARCHAR2,
                                         P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PARTS_SURVEY_REQUIRED_LIST;

   PROCEDURE WARRANTY_LIST (P_LIST_CODE           IN     VARCHAR2,
                            P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                            P_ERR_FLG                OUT VARCHAR2,
                            P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SVC_WTY_TP_CD LISTCODE, UPPER (SVC_WTY_TP_NM) LISTNAME
             FROM SVC_WTY_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SVC_WTY_TP_CD = NVL (P_LIST_CODE, SVC_WTY_TP_CD)
         ORDER BY SVC_WTY_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END WARRANTY_LIST;

   PROCEDURE IMAGEWARE_REMOTE_ENABLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                            P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                            P_ERR_FLG                OUT VARCHAR2,
                                            P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END IMAGEWARE_REMOTE_ENABLED_LIST;

   PROCEDURE IMAGEWARE_REMOTE_MODEL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                          P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                          P_ERR_FLG                OUT VARCHAR2,
                                          P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT IWR_MDL_CD LISTCODE, UPPER (IWR_MDL_NM) LISTNAME
             FROM IWR_MDL
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND IWR_MDL_CD = NVL (P_LIST_CODE, IWR_MDL_CD)
         ORDER BY IWR_MDL_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END IMAGEWARE_REMOTE_MODEL_LIST;

   PROCEDURE METERED_MACHINE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END METERED_MACHINE_LIST;

   PROCEDURE INSTALLBASE_CONTROLLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                          P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                          P_ERR_FLG                OUT VARCHAR2,
                                          P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END INSTALLBASE_CONTROLLED_LIST;

   PROCEDURE SERVICE_CALL_ENABLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SERVICE_CALL_ENABLED_LIST;

   PROCEDURE SOFTWARE_CATEGORY_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SW_CATG_CD LISTCODE, UPPER (SW_CATG_NM) LISTNAME
             FROM SW_CATG
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SW_CATG_CD = NVL (P_LIST_CODE, SW_CATG_CD)
         ORDER BY SW_CATG_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SOFTWARE_CATEGORY_LIST;

   PROCEDURE SOFTWARE_PRODUCT_CATEGORY_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SW_PROD_CATG_CD LISTCODE, UPPER (SW_PROD_CATG_NM) LISTNAME
             FROM SW_PROD_CATG
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SW_PROD_CATG_CD = NVL (P_LIST_CODE, SW_PROD_CATG_CD)
         ORDER BY SW_PROD_CATG_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SOFTWARE_PRODUCT_CATEGORY_LIST;

   PROCEDURE LICENSE_CONTROL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SW_LIC_CTRL_TP_CD LISTCODE, UPPER (SW_LIC_CTRL_TP_NM) LISTNAME
             FROM SW_LIC_CTRL_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SW_LIC_CTRL_TP_CD = NVL (P_LIST_CODE, SW_LIC_CTRL_TP_CD)
         ORDER BY SW_LIC_CTRL_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END LICENSE_CONTROL_LIST;

   PROCEDURE ELAN_CONTROLLED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END ELAN_CONTROLLED_LIST;

   PROCEDURE SOFTWARE_MAINTENANCE_CONT_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SW_MAINT_CTRL_TP_CD LISTCODE, UPPER (SW_MAINT_CTRL_TP_NM) LISTNAME
             FROM SW_MAINT_CTRL_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SW_MAINT_CTRL_TP_CD = NVL (P_LIST_CODE, SW_MAINT_CTRL_TP_CD)
         ORDER BY SW_MAINT_CTRL_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SOFTWARE_MAINTENANCE_CONT_LIST;

   PROCEDURE MAINTENANCE_POP_AVALIABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MAINTENANCE_POP_AVALIABLE_LIST;

   PROCEDURE EXTENDED_MAINT_POP_AVAILA_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END EXTENDED_MAINT_POP_AVAILA_LIST;

   PROCEDURE MAINTENANCE_ITEM_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                         P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                         P_ERR_FLG                OUT VARCHAR2,
                                         P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT MAINT_ITEM_TP_CD LISTCODE, UPPER (MAINT_ITEM_TP_NM) LISTNAME
             FROM MAINT_ITEM_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND MAINT_ITEM_TP_CD = NVL (P_LIST_CODE, MAINT_ITEM_TP_CD)
         ORDER BY MAINT_ITEM_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MAINTENANCE_ITEM_TYPE_LIST;

   PROCEDURE SUPPLY_OEM_MANUFACTURER_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT IMG_SPLY_OEM_MNF_CD LISTCODE, UPPER (IMG_SPLY_OEM_MNF_NM) LISTNAME
             FROM IMG_SPLY_OEM_MNF
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND IMG_SPLY_OEM_MNF_CD = NVL (P_LIST_CODE, IMG_SPLY_OEM_MNF_CD)
         ORDER BY IMG_SPLY_OEM_MNF_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SUPPLY_OEM_MANUFACTURER_LIST;

   PROCEDURE SUPPLY_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT IMG_SPLY_TP_CD LISTCODE, UPPER (IMG_SPLY_TP_NM) LISTNAME
             FROM IMG_SPLY_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND IMG_SPLY_TP_CD = NVL (P_LIST_CODE, IMG_SPLY_TP_CD)
         ORDER BY IMG_SPLY_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SUPPLY_TYPE_LIST;

   PROCEDURE SUPPLY_COLOR_LIST (P_LIST_CODE           IN     VARCHAR2,
                                P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                P_ERR_FLG                OUT VARCHAR2,
                                P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT IMG_SPLY_COLOR_TP_CD LISTCODE, UPPER (IMG_SPLY_COLOR_TP_NM) LISTNAME
             FROM IMG_SPLY_COLOR_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND IMG_SPLY_COLOR_TP_CD = NVL (P_LIST_CODE, IMG_SPLY_COLOR_TP_CD)
         ORDER BY IMG_SPLY_COLOR_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SUPPLY_COLOR_LIST;

   PROCEDURE SUPPLY_YIELD_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT IMG_SPLY_YIELD_TP_CD LISTCODE, UPPER (IMG_SPLY_YIELD_TP_NM) LISTNAME
             FROM IMG_SPLY_YIELD_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND IMG_SPLY_YIELD_TP_CD = NVL (P_LIST_CODE, IMG_SPLY_YIELD_TP_CD)
         ORDER BY IMG_SPLY_YIELD_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SUPPLY_YIELD_TYPE_LIST;

   PROCEDURE SUPPLY_YIELD_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                                    P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT IMG_SPLY_YIELD_UOM_CD LISTCODE, UPPER (IMG_SPLY_YIELD_UOM_NM) LISTNAME
             FROM IMG_SPLY_YIELD_UOM
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND IMG_SPLY_YIELD_UOM_CD = NVL (P_LIST_CODE, IMG_SPLY_YIELD_UOM_CD)
         ORDER BY IMG_SPLY_YIELD_UOM_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SUPPLY_YIELD_UOM_LIST;

   PROCEDURE PRIVATE_LABEL_FLAG_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT IMG_SPLY_PVT_LB_TP_CD LISTCODE, UPPER (IMG_SPLY_PVT_LB_TP_NM) LISTNAME
             FROM IMG_SPLY_PVT_LB_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND IMG_SPLY_PVT_LB_TP_CD = NVL (P_LIST_CODE, IMG_SPLY_PVT_LB_TP_CD)
         ORDER BY IMG_SPLY_PVT_LB_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PRIVATE_LABEL_FLAG_LIST;

   PROCEDURE ACCOUNTING_RULES_LIST (P_LIST_CODE           IN     VARCHAR2,
                                    P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DFRD_ACCTG_RULE_CD LISTCODE, UPPER (DFRD_ACCTG_RULE_NM) LISTNAME
             FROM DFRD_ACCTG_RULE
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND DFRD_ACCTG_RULE_CD = NVL (P_LIST_CODE, DFRD_ACCTG_RULE_CD)
         ORDER BY DFRD_ACCTG_RULE_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END ACCOUNTING_RULES_LIST;

   PROCEDURE INVOICEABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END INVOICEABLE_LIST;

   PROCEDURE TAX_CODE_LIST (P_LIST_CODE           IN     VARCHAR2,
                            P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                            P_ERR_FLG                OUT VARCHAR2,
                            P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT TAX_EXEM_TP_CD LISTCODE, UPPER (TAX_EXEM_TP_NM) LISTNAME
             FROM TAX_EXEM_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND TAX_EXEM_TP_CD = NVL (P_LIST_CODE, TAX_EXEM_TP_CD)
         ORDER BY TAX_EXEM_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TAX_CODE_LIST;

   PROCEDURE INVOICING_RULES_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DFRD_INV_RULE_CD LISTCODE, UPPER (DFRD_INV_RULE_NM) LISTNAME
             FROM DFRD_INV_RULE
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND DFRD_INV_RULE_CD = NVL (P_LIST_CODE, DFRD_INV_RULE_CD)
         ORDER BY DFRD_INV_RULE_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END INVOICING_RULES_LIST;

   PROCEDURE PLANNING_GROUP_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PRCH_GRP_CD LISTCODE, UPPER (PRCH_GRP_NM) LISTNAME
             FROM PRCH_GRP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND PRCH_GRP_CD = NVL (P_LIST_CODE, PRCH_GRP_CD)
         ORDER BY PRCH_GRP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PLANNING_GROUP_LIST;

   PROCEDURE THIRD_PARTY_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END THIRD_PARTY_LIST;

   PROCEDURE SAFETY_HAZARDOUS_MATERIAL_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SAFETY_HAZARDOUS_MATERIAL_LIST;

   PROCEDURE SAFETY_HAZARDOUS_NUMBER_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT HAZ_MAT_CD LISTCODE, UPPER (HAZ_MAT_NM) LISTNAME
             FROM HAZ_MAT
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND HAZ_MAT_CD = NVL (P_LIST_CODE, HAZ_MAT_CD)
         ORDER BY HAZ_MAT_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SAFETY_HAZARDOUS_NUMBER_LIST;

   PROCEDURE RMA_INSPECTION_REQUIRED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                           P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                           P_ERR_FLG                OUT VARCHAR2,
                                           P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END RMA_INSPECTION_REQUIRED_LIST;

   PROCEDURE RMA_ALLOWED_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT RMA_REQ_TP_CD LISTCODE, UPPER (RMA_REQ_TP_NM) LISTNAME
             FROM RMA_REQ_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND RMA_REQ_TP_CD = NVL (P_LIST_CODE, RMA_REQ_TP_CD)
         ORDER BY RMA_REQ_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END RMA_ALLOWED_LIST;

   PROCEDURE COSTED_LIST (P_LIST_CODE           IN     VARCHAR2,
                          P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                          P_ERR_FLG                OUT VARCHAR2,
                          P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END COSTED_LIST;

   PROCEDURE INVENTORY_TRACKABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END INVENTORY_TRACKABLE_LIST;

   PROCEDURE INTERNAL_ITEM_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END INTERNAL_ITEM_LIST;

   PROCEDURE NMFC_CLASS_LIST (P_LIST_CODE           IN     VARCHAR2,
                              P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                              P_ERR_FLG                OUT VARCHAR2,
                              P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT NMFC_CLS_TP_CD LISTCODE, UPPER (NMFC_CLS_TP_NM) LISTNAME
             FROM NMFC_CLS_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND NMFC_CLS_TP_CD = NVL (P_LIST_CODE, NMFC_CLS_TP_CD)
         ORDER BY NMFC_CLS_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END NMFC_CLASS_LIST;

   PROCEDURE REMAN_AVAILABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END REMAN_AVAILABLE_LIST;

   PROCEDURE SOW_REQUIRED_LIST (P_LIST_CODE           IN     VARCHAR2,
                                P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                P_ERR_FLG                OUT VARCHAR2,
                                P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SOW_REQUIRED_LIST;

   PROCEDURE MAIN_ENGINE_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MAIN_ENGINE_LIST;

   PROCEDURE CRITICALITY_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT BACK_ORD_IMPCT_TP_CD LISTCODE, UPPER (BACK_ORD_IMPCT_TP_NM) LISTNAME
             FROM BACK_ORD_IMPCT_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND BACK_ORD_IMPCT_TP_CD = NVL (P_LIST_CODE, BACK_ORD_IMPCT_TP_CD)
         ORDER BY BACK_ORD_IMPCT_TP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END CRITICALITY_LIST;

   PROCEDURE CUSTOMER_ORDEREABLE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END CUSTOMER_ORDEREABLE_LIST;

   PROCEDURE REMANUFACTURED_ITEM_EXIST_LIST (P_LIST_CODE           IN     VARCHAR2,
                                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                             P_ERR_FLG                OUT VARCHAR2,
                                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END REMANUFACTURED_ITEM_EXIST_LIST;

   PROCEDURE MANUFACTURED_COUNTRY_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT CTRY_CD LISTCODE, UPPER (CTRY_NM) LISTNAME
             FROM CTRY
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND CTRY_CD = NVL (P_LIST_CODE, CTRY_CD)
         ORDER BY CTRY_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MANUFACTURED_COUNTRY_LIST;

   PROCEDURE ASSEMBLED_COUNTRY_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT CTRY_CD LISTCODE, UPPER (CTRY_NM) LISTNAME
             FROM CTRY
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND CTRY_CD = NVL (P_LIST_CODE, CTRY_CD)
         ORDER BY CTRY_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END ASSEMBLED_COUNTRY_LIST;

   PROCEDURE COMMISSIONS_GROUP_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DS_CMSN_GRP_CD LISTCODE, UPPER (DS_CMSN_GRP_NM) LISTNAME
             FROM DS_CMSN_GRP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND DS_CMSN_GRP_CD = NVL (P_LIST_CODE, DS_CMSN_GRP_CD)
         ORDER BY DS_CMSN_GRP_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END COMMISSIONS_GROUP_LIST;

   PROCEDURE DEFAULT_SRC_WH_LIST (P_LIST_CODE           IN     VARCHAR2,
                                  P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                OUT VARCHAR2,
                                  P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DISTINCT RTL_WH_CD LISTCODE, REGEXP_REPLACE (UPPER (RTL_WH_NM), '(,|'')', ' ') LISTNAME
             FROM RTL_WH
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND (   RTL_WH_CD = P_LIST_CODE
				       OR UPPER (RTL_WH_NM) LIKE '%'||
					      UPPER (DECODE(P_LIST_CODE,'-00000',RTL_WH_NM, NVL(P_LIST_CODE,'-999999'))) || '%' )
         --AND RTL_WH_CD = NVL (P_LIST_CODE, RTL_WH_CD)
         ORDER BY REGEXP_REPLACE (UPPER (RTL_WH_NM), '(,|'')', ' ');

		 P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END DEFAULT_SRC_WH_LIST;

   PROCEDURE DEFAULT_SRC_SUB_WH_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DISTINCT RTL_SWH_CD LISTCODE, UPPER (RTL_SWH_NM) LISTNAME
             FROM RTL_SWH
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND RTL_SWH_CD = NVL (P_LIST_CODE, RTL_SWH_CD)
         ORDER BY UPPER (RTL_SWH_NM);

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END DEFAULT_SRC_SUB_WH_LIST;

   PROCEDURE RETURN_WAREHOUSE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                    P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                    P_ERR_FLG                OUT VARCHAR2,
                                    P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR                                               --RQ16627
           SELECT DISTINCT RCTVR.RTRN_WH_CD LISTCODE, RWH.RTL_WH_NM LISTNAME
             FROM RTRN_CTRL_TP_VND_RELN RCTVR,
                  RTRN_CTRL_TP RC,
                  PO_VND_V V,
                  PRCH_REQ_TP RPT,
                  RTL_WH RWH
            WHERE     RCTVR.RTRN_CTRL_TP_CD = RC.RTRN_CTRL_TP_CD(+)
                  AND RCTVR.VND_CD = V.VND_CD(+)
                  AND RCTVR.PRCH_REQ_TP_CD = RPT.PRCH_REQ_TP_CD(+)
                  AND RCTVR.EZCANCELFLAG = '0'
                  AND V.EZCANCELFLAG(+) = '0'
                  AND RPT.EZCANCELFLAG(+) = '0'
                  --AND RCTVR.RTRN_WH_CD = NVL (P_LIST_CODE, RWH.RTL_WH_CD(+))
                  AND RCTVR.RTRN_WH_CD = RWH.RTL_WH_CD
                  AND RWH.RTL_WH_CD = NVL (P_LIST_CODE, RWH.RTL_WH_CD)
                  AND RWH.EZCANCELFLAG(+) = '0'
                  AND RWH.GLBL_CMPY_CD = 'ADB'
                  AND RC.GLBL_CMPY_CD = 'ADB'
                  AND V.GLBL_CMPY_CD = 'ADB'
                  AND RPT.GLBL_CMPY_CD = 'ADB'
                  AND RWH.GLBL_CMPY_CD = 'ADB'
                  AND RCTVR.RTRN_WH_CD IS NOT NULL
         ORDER BY RCTVR.RTRN_WH_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END RETURN_WAREHOUSE_LIST;

   PROCEDURE RETURN_SUB_WAREHOUSE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR                                               --RQ16627
           SELECT RTRN_DSPL_TP_CD LISTCODE, RTRN_DSPL_TP_DESC_TXT LISTNAME
             FROM RTRN_DSPL_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND RTRN_DSPL_TP_CD = NVL (P_LIST_CODE, RTRN_DSPL_TP_CD)
         ORDER BY RTRN_DSPL_TP_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END RETURN_SUB_WAREHOUSE_LIST;

   PROCEDURE RETURN_VENDOR_LIST (P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR                                               --RQ16627
           SELECT DISTINCT V.PRNT_VND_CD LISTCODE, V.PRNT_VND_NM LISTNAME
             FROM RTRN_CTRL_TP_VND_RELN RCTVR,
                  RTRN_CTRL_TP RC,
                  PO_VND_V V,
                  PRCH_REQ_TP RPT
            WHERE     RCTVR.RTRN_CTRL_TP_CD = RC.RTRN_CTRL_TP_CD(+)
                  AND RCTVR.VND_CD = V.VND_CD(+)
                  AND RCTVR.PRCH_REQ_TP_CD = RPT.PRCH_REQ_TP_CD(+)
                  AND RCTVR.EZCANCELFLAG = '0'
                  AND V.EZCANCELFLAG(+) = '0'
                  AND RPT.EZCANCELFLAG(+) = '0'
                  AND RC.GLBL_CMPY_CD = 'ADB'
                  AND V.GLBL_CMPY_CD = 'ADB'
                  AND RPT.GLBL_CMPY_CD = 'ADB'
                  --AND V.PRNT_VND_CD IS NOT NULL
                  AND NVL (V.PRNT_VND_CD, '-XXYYZZ') =
                         NVL2 (P_LIST_CODE, P_LIST_CODE, NVL (V.PRNT_VND_CD, '-XYZ'))
         ORDER BY V.PRNT_VND_NM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END RETURN_VENDOR_LIST;

   PROCEDURE RETURN_VENDOR_SITE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE IS NOT NULL
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR                                            --RQ16627
              SELECT DISTINCT V.VND_CD LISTCODE, V.VND_NM LISTNAME
                FROM RTRN_CTRL_TP_VND_RELN RCTVR,
                     RTRN_CTRL_TP RC,
                     PO_VND_V V,
                     PRCH_REQ_TP RPT
               WHERE     RCTVR.RTRN_CTRL_TP_CD = RC.RTRN_CTRL_TP_CD(+)
					 AND RCTVR.VND_CD = V.VND_CD(+)
                     AND RCTVR.VND_CD = P_LIST_CODE
                     AND RCTVR.PRCH_REQ_TP_CD = RPT.PRCH_REQ_TP_CD(+)
                     AND RCTVR.EZCANCELFLAG = '0'
                     AND V.EZCANCELFLAG(+) = '0'
                     AND RPT.EZCANCELFLAG(+) = '0'
                     AND RC.GLBL_CMPY_CD = 'ADB'
                     AND V.GLBL_CMPY_CD = 'ADB'
                     AND RPT.GLBL_CMPY_CD = 'ADB'
                     AND V.PRNT_VND_CD IS NOT NULL
            ORDER BY V.VND_NM;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR                                            --RQ16627
              SELECT DISTINCT V.VND_CD LISTCODE, V.VND_NM LISTNAME
                FROM RTRN_CTRL_TP_VND_RELN RCTVR,
                     RTRN_CTRL_TP RC,
                     PO_VND_V V,
                     PRCH_REQ_TP RPT
               WHERE     RCTVR.RTRN_CTRL_TP_CD = RC.RTRN_CTRL_TP_CD(+)
                     AND RCTVR.VND_CD = V.VND_CD(+)
                     AND RCTVR.PRCH_REQ_TP_CD = RPT.PRCH_REQ_TP_CD(+)
                     AND RCTVR.EZCANCELFLAG = '0'
                     AND V.EZCANCELFLAG(+) = '0'
                     AND RPT.EZCANCELFLAG(+) = '0'
                     AND RC.GLBL_CMPY_CD = 'ADB'
                     AND V.GLBL_CMPY_CD = 'ADB'
                     AND RPT.GLBL_CMPY_CD = 'ADB'
                     AND V.PRNT_VND_CD IS NOT NULL
            ORDER BY V.VND_NM;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END RETURN_VENDOR_SITE_LIST;

   PROCEDURE HARD_ALLOCATION_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR                                               --RQ16627
           SELECT HARD_ALLOC_TP_CD LISTCODE, UPPER (HARD_ALLOC_TP_NM) LISTNAME
             FROM HARD_ALLOC_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND HARD_ALLOC_TP_CD = NVL (P_LIST_CODE, HARD_ALLOC_TP_CD)
         ORDER BY UPPER (HARD_ALLOC_TP_CD);

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END HARD_ALLOCATION_TYPE_LIST;

   PROCEDURE DEFAULT_SOURCE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                       P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR                                               --RQ16627
           SELECT PROCR_TP_CD LISTCODE, UPPER (PROCR_TP_NM) LISTNAME
             FROM PROCR_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND PROCR_TP_CD = NVL (P_LIST_CODE, PROCR_TP_CD)
         ORDER BY UPPER (PROCR_TP_CD);

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END DEFAULT_SOURCE_TYPE_LIST;

   PROCEDURE EASY_PACK_I_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR                                               --RQ16627
           SELECT EASY_PACK_TP_CD LISTCODE, UPPER (EASY_PACK_TP_NM) LISTNAME
             FROM EASY_PACK_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND EASY_PACK_TP_CD = NVL (P_LIST_CODE, EASY_PACK_TP_CD)
         ORDER BY UPPER (EASY_PACK_TP_CD);

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END EASY_PACK_I_LIST;

   PROCEDURE INTANGIBLE_MDSE_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                        P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                OUT VARCHAR2,
                                        P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DS_INTG_MDSE_TP_CD LISTCODE, UPPER (DS_INTG_MDSE_TP_NM) LISTNAME
             FROM DS_INTG_MDSE_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND DS_INTG_MDSE_TP_CD = NVL (P_LIST_CODE, DS_INTG_MDSE_TP_CD)
         ORDER BY UPPER (DS_INTG_MDSE_TP_NM);

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END INTANGIBLE_MDSE_TYPE_LIST;

   PROCEDURE TARIFF_CODE_LIST (P_LIST_CODE           IN     VARCHAR2,
                               P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                               P_ERR_FLG                OUT VARCHAR2,
                               P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT TRF_CD LISTCODE, REGEXP_REPLACE (UPPER (TRF_NM), '(,|'')', ' ') LISTNAME
             FROM TRF
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND (TRF_CD = P_LIST_CODE
				      OR UPPER (TRF_NM) LIKE '%'||
					     UPPER (DECODE(P_LIST_CODE,'-00000',TRF_NM, NVL(P_LIST_CODE,'-999999'))) || '%' )
         --AND TRF_CD = NVL (P_LIST_CODE, TRF_CD)
         ORDER BY REGEXP_REPLACE (UPPER (TRF_NM), '(,|'')', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TARIFF_CODE_LIST;

   PROCEDURE FREIGHT_CLASS_CODE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT FRT_CLS_CD LISTCODE, UPPER (FRT_CLS_NM) LISTNAME
             FROM FRT_CLS
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND FRT_CLS_CD = NVL (P_LIST_CODE, FRT_CLS_CD)
         ORDER BY UPPER (FRT_CLS_NM);

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END FREIGHT_CLASS_CODE_LIST;

   PROCEDURE APPROVAL_GROUP_LIST (--P_LIST_CODE             IN     VARCHAR2,
                                  P_APPROVAL_GROUP_LIST      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG                  OUT VARCHAR2,
                                  P_ERR_MSG                  OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_APPROVAL_GROUP_LIST FOR
           SELECT DISTINCT APPROVAL_GROUP_DESC
             FROM CANON_E008_APPRV_GROUP_OWNER_V
         ORDER BY APPROVAL_GROUP_DESC;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END APPROVAL_GROUP_LIST;

   PROCEDURE MATERIAL_GROUP1_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SLS_MAT_GRP_CD LISTCODE, REGEXP_REPLACE (UPPER (SLS_MAT_GRP_NM), '(,|'')', ' ') LISTNAME
             FROM SLS_MAT_GRP
            WHERE     SLS_MAT_GRP_SQ_NUM = 1
                  AND GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  --AND SLS_MAT_GRP_CD = NVL (P_LIST_CODE, SLS_MAT_GRP_CD)
                  AND (   SLS_MAT_GRP_CD = P_LIST_CODE
				       OR UPPER (SLS_MAT_GRP_NM) LIKE '%'||
					      UPPER (DECODE(P_LIST_CODE,'-00000',SLS_MAT_GRP_NM, NVL(P_LIST_CODE,'-999999'))) || '%' )
         ORDER BY REGEXP_REPLACE (UPPER (SLS_MAT_GRP_NM), '(,|'')', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MATERIAL_GROUP1_LIST;

   PROCEDURE MATERIAL_GROUP2_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SLS_MAT_GRP_CD LISTCODE, UPPER (SLS_MAT_GRP_NM) LISTNAME
             FROM SLS_MAT_GRP
            WHERE     SLS_MAT_GRP_SQ_NUM = 2
                  AND GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SLS_MAT_GRP_CD = NVL (P_LIST_CODE, SLS_MAT_GRP_CD)
         ORDER BY UPPER (SLS_MAT_GRP_NM);

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MATERIAL_GROUP2_LIST;

   PROCEDURE MATERIAL_GROUP3_LIST (P_LIST_CODE           IN     VARCHAR2,
                                   P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                OUT VARCHAR2,
                                   P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT SLS_MAT_GRP_CD LISTCODE, UPPER (SLS_MAT_GRP_NM) LISTNAME
             FROM SLS_MAT_GRP
            WHERE     SLS_MAT_GRP_SQ_NUM = 3
                  AND GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND SLS_MAT_GRP_CD = NVL (P_LIST_CODE, SLS_MAT_GRP_CD)
         ORDER BY UPPER (SLS_MAT_GRP_NM);

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MATERIAL_GROUP3_LIST;

   PROCEDURE SUPPLIER_LIST (P_LIST_CODE           IN     VARCHAR2,
                            P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                            P_ERR_FLG                OUT VARCHAR2,
                            P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT PRNT_VND_CD LISTCODE, --REGEXP_REPLACE (UPPER (PRNT_VND_NM), '(,|'')', ' ') LISTNAME
                  UPPER(PRNT_VND_NM) LISTNAME
             FROM PRNT_VND
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND NVL(INAC_DT,TO_CHAR(SYSDATE+1,'YYYYMMDD')) >= TO_CHAR(SYSDATE,'YYYYMMDD')
                  AND (   PRNT_VND_CD = P_LIST_CODE
                       OR UPPER (PRNT_VND_NM) LIKE '%'||
					      UPPER (DECODE(P_LIST_CODE,'-00000',PRNT_VND_NM, NVL(P_LIST_CODE,'-999999'))) || '%' )
            ORDER BY UPPER (PRNT_VND_NM);
         --ORDER BY REGEXP_REPLACE (UPPER (PRNT_VND_NM), '(,|'')', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SUPPLIER_LIST;

 PROCEDURE SUPPLIER_SITE_LIST (  P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DISTINCT
                  INV_VND_CD LISTCODE,
                  UPPER (ARCS_SPLY_SITE_CD) LISTNAME
                  --REGEXP_REPLACE (UPPER (ARCS_SPLY_SITE_CD), '(,|'')', ' ') LISTNAME
             FROM VND
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND INV_VND_CD IS NOT NULL
                  AND ARCS_SPLY_SITE_CD IS NOT NULL
                  AND (   INV_VND_CD = P_LIST_CODE
				       OR UPPER (ARCS_SPLY_SITE_CD) LIKE '%'||
					      UPPER (DECODE(P_LIST_CODE,'-00000',ARCS_SPLY_SITE_CD, NVL(P_LIST_CODE,'-999999'))) || '%' )
         ORDER BY UPPER (ARCS_SPLY_SITE_CD);
         --ORDER BY REGEXP_REPLACE (UPPER (ARCS_SPLY_SITE_CD), '(,|'')', ' ');

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SUPPLIER_SITE_LIST;

   PROCEDURE SUPPLIER_SITE_LIST (P_SUPPLIER            IN     VARCHAR2,
                                 P_LIST_CODE           IN     VARCHAR2,
                                 P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG                OUT VARCHAR2,
                                 P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
        select VN.INV_VND_CD LISTCODE,
               UPPER (VN.ARCS_SPLY_SITE_CD) LISTNAME
               --REGEXP_REPLACE (UPPER (VN.ARCS_SPLY_SITE_CD), '(,|'')', ' ') LISTNAME
          from VND VN ,
               PRNT_VND PVN
          WHERE     PVN.GLBL_CMPY_CD = 'ADB'
            AND PVN.EZCANCELFLAG = '0'
            AND PVN.PRNT_VND_CD = P_SUPPLIER
            AND VN.GLBL_CMPY_CD = 'ADB'
            AND VN.EZCANCELFLAG = '0'
            AND NVL(PVN.INAC_DT,TO_CHAR(SYSDATE+1,'YYYYMMDD')) >= TO_CHAR(SYSDATE,'YYYYMMDD')
            AND VN.INV_VND_CD IS NOT NULL
            AND VN.ARCS_SPLY_SITE_CD IS NOT NULL
            AND PVN.PRNT_VND_PK = VN.PRNT_VND_PK
            --AND (   VN.INV_VND_CD = P_LIST_CODE
				    --   OR UPPER (VN.ARCS_SPLY_SITE_CD) LIKE '%'||
					  --    UPPER (DECODE(P_LIST_CODE,'-00000',VN.ARCS_SPLY_SITE_CD, NVL(P_LIST_CODE,'-999999'))) || '%' )
            ORDER BY UPPER (VN.ARCS_SPLY_SITE_CD);
            --ORDER BY REGEXP_REPLACE (UPPER (VN.ARCS_SPLY_SITE_CD), '(,|'')', ' ');
          /*
           SELECT DISTINCT
                  INV_VND_CD LISTCODE,
                  REGEXP_REPLACE (UPPER (ARCS_SPLY_SITE_CD), '(,|'')', ' ') LISTNAME
             FROM VND
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND INV_VND_CD IS NOT NULL
                  AND ARCS_SPLY_SITE_CD IS NOT NULL
                  AND (   INV_VND_CD = P_LIST_CODE
				       OR UPPER (ARCS_SPLY_SITE_CD) LIKE '%'||
					      UPPER (DECODE(P_LIST_CODE,'-00000',ARCS_SPLY_SITE_CD, NVL(P_LIST_CODE,'-999999'))) || '%' )
         ORDER BY REGEXP_REPLACE (UPPER (ARCS_SPLY_SITE_CD), '(,|'')', ' ');
          */

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SUPPLIER_SITE_LIST;

   PROCEDURE ITEM_BILLING_TYPE_LIST (P_LIST_CODE           IN     VARCHAR2,
                                     P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG                OUT VARCHAR2,
                                     P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT DISTINCT
                  MDSE_ITEM_BILL_TP_CD LISTCODE, UPPER (MDSE_ITEM_BILL_TP_NM) LISTNAME
             FROM MDSE_ITEM_BILL_TP
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND MDSE_ITEM_BILL_TP_CD = NVL (P_LIST_CODE, MDSE_ITEM_BILL_TP_CD)
         ORDER BY MDSE_ITEM_BILL_TP_CD;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END ITEM_BILLING_TYPE_LIST;

   PROCEDURE CONFIGURATION_FLAG_LIST (P_LIST_CODE           IN     VARCHAR2,
                                      P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                                      P_ERR_FLG                OUT VARCHAR2,
                                      P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_LIST_CODE = 'Y'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL;
      ELSIF P_LIST_CODE = 'N'
      THEN
         OPEN P_TEMPLATE_LIST_TBL FOR SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      ELSE
         OPEN P_TEMPLATE_LIST_TBL FOR
            SELECT 'Y' LISTCODE, 'YES' LISTNAME FROM DUAL
            UNION
            SELECT 'N' LISTCODE, 'NO' LISTNAME FROM DUAL;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END CONFIGURATION_FLAG_LIST;
---End New LOV APIs for Item Template performance

   PROCEDURE UNBOXED_WEIGHT_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT pkg_uom_cd LISTCODE, UPPER (pkg_uom_nm) LISTNAME
             FROM PKG_UOM
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND pkg_uom_nm in ( 'POUND')
                  AND pkg_uom_cd = NVL (P_LIST_CODE, pkg_uom_cd)
         ORDER BY pkg_uom_cd;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END UNBOXED_WEIGHT_UOM_LIST;

   PROCEDURE UNBOXED_LENGTH_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT pkg_uom_cd LISTCODE, UPPER (pkg_uom_nm) LISTNAME
             FROM PKG_UOM
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND pkg_uom_cd in ( 'FT','IN','YRD')
                  AND pkg_uom_cd = NVL (P_LIST_CODE, pkg_uom_cd)
         ORDER BY pkg_uom_cd;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END UNBOXED_LENGTH_UOM_LIST;

   PROCEDURE UNBOXED_WIDTH_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT pkg_uom_cd LISTCODE, UPPER (pkg_uom_nm) LISTNAME
             FROM PKG_UOM
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND pkg_uom_cd in ( 'FT','IN','YRD')
                  AND pkg_uom_cd = NVL (P_LIST_CODE, pkg_uom_cd)
         ORDER BY pkg_uom_cd;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END UNBOXED_WIDTH_UOM_LIST;

   PROCEDURE UNBOXED_HEIGHT_UOM_LIST (P_LIST_CODE           IN     VARCHAR2,
                             P_TEMPLATE_LIST_TBL      OUT G_REF_CUR_TYP,
                             P_ERR_FLG                OUT VARCHAR2,
                             P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPLATE_LIST_TBL FOR
           SELECT pkg_uom_cd LISTCODE, UPPER (pkg_uom_nm) LISTNAME
             FROM PKG_UOM
            WHERE     GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND pkg_uom_cd in ( 'FT','IN','YRD')
                  AND pkg_uom_cd = NVL (P_LIST_CODE, pkg_uom_cd)
         ORDER BY pkg_uom_cd;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END UNBOXED_HEIGHT_UOM_LIST;


END CANON_E008_ITEM_TEMPLATE_PKG;
/