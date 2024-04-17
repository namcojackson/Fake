create or replace PACKAGE CANON_E008_ITEM_WORKBENCH_PKG
AS
   /********************************************************************
   Modification History:

   Version      Date               Author                 Remarks
   =======   ===========   ====================   ====================
   1.0        02/15/2016        Lakshmi Majeti   Item Project Workbench
   1.1        02/15/2017        Madhusudan Duna  Item Project Workbench
   **********************************************************************/

   TYPE G_REF_CUR_TYP IS REF CURSOR;

   PROCEDURE SAVE_PROJECT_ITEMS (P_PROJECT_REC   IN     CANON_E008_PROJECT_REC,
                                 P_ITEM_TBL      IN     CANON_E008_PROJ_ITEMS_TBL_TYPE,
                                 P_PROJECT_NO       OUT NUMBER,
                                 P_ERR_FLG          OUT VARCHAR2,
                                 P_ERR_MSG          OUT VARCHAR2);

   PROCEDURE UPLOAD_PROJECT_ITEMS (P_PROJECT_REC   IN     CANON_E008_PROJECT_REC,
                                   P_ITEM_TBL      IN     CANON_E008_PROJ_ITEMS_TBL_TYPE,
                                   P_PROJECT_NO       OUT NUMBER,
                                   P_ERR_FLG          OUT VARCHAR2,
                                   P_ERR_MSG          OUT VARCHAR2);

   PROCEDURE GET_PROJECT_ITEMS (P_PROJECT_NO    IN     NUMBER,
                                P_PROJECT_REC      OUT CANON_E008_PROJECT_REC,
                                P_ITEM_TBL         OUT G_REF_CUR_TYP,
                                P_ERR_FLG          OUT VARCHAR2,
                                P_ERR_MSG          OUT VARCHAR2);

   PROCEDURE GET_PROJECT_ITEMS_DETAILS (P_PROJECT_NO   IN     NUMBER,
                                        P_ITEM_TBL        OUT G_REF_CUR_TYP,
                                        P_ERR_FLG         OUT VARCHAR2,
                                        P_ERR_MSG         OUT VARCHAR2);

   FUNCTION CHECK_CREATE_PROJECT (P_PROJECT_ID   IN NUMBER,
                                  P_ROLE_ID      IN VARCHAR2,
                                  P_USER_ID      IN VARCHAR2)
      RETURN CHAR;

   FUNCTION CHECK_MODIFY_PROJECT (P_PROJECT_ID   IN NUMBER,
                                  P_ROLE_ID      IN VARCHAR2,
                                  P_USER_ID      IN VARCHAR2)
      RETURN CHAR;

   FUNCTION CHECK_VALIDATE_REJECT_PROJECT (P_PROJECT_ID   IN NUMBER,
                                           P_ROLE_ID      IN VARCHAR2,
                                           P_USER_ID      IN VARCHAR2)
      RETURN CHAR;

   PROCEDURE DELETE_PROJECT_ITEMS (P_PROJECT_ID   IN     NUMBER,
                                   P_ITEM_ID      IN     NUMBER,
                                   P_ERR_FLG         OUT VARCHAR2,
                                   P_ERR_MSG         OUT VARCHAR2);

   PROCEDURE DELETE_PROJECT (P_PROJECT_ID   IN     NUMBER,
                             P_ERR_FLG         OUT VARCHAR2,
                             P_ERR_MSG         OUT VARCHAR2);

   PROCEDURE CLOSE_PROJECT (P_PROJECT_ID   IN     NUMBER,
                            P_ERR_FLG         OUT VARCHAR2,
                            P_ERR_MSG         OUT VARCHAR2);

   TYPE ARRAY_T IS VARRAY (3) OF VARCHAR2 (10);

   ARRAY   ARRAY_T;

   PROCEDURE GET_ROLE_EMAIL (P_ROLE_ID   IN     VARCHAR2,
                             P_EMAILS       OUT G_REF_CUR_TYP,
                             P_ERR_MSG      OUT VARCHAR2);


   FUNCTION CHECK_DELETE_PROJECT (P_PROJECT_ID   IN NUMBER,
                                  P_ROLE_ID      IN VARCHAR2,
                                  P_USER_ID      IN VARCHAR2)
      RETURN CHAR;

   FUNCTION CHECK_SUBMIT_APPROVAL_PROJECT (P_PROJECT_ID   IN NUMBER,
                                           P_ROLE_ID      IN VARCHAR2,
                                           P_USER_ID      IN VARCHAR2)
      RETURN CHAR;


   FUNCTION CHECK_VIEW_PROJECT (P_PROJECT_ID   IN NUMBER,
                                P_ROLE_ID      IN VARCHAR2,
                                P_USER_ID      IN VARCHAR2)
      RETURN CHAR;


   PROCEDURE INSERT_ERROR_PROJ_TBL (P_PROJECT_ID   IN NUMBER,
                                    P_USER_ID      IN VARCHAR2,
                                    P_ITEM_ID      IN VARCHAR2,
                                    P_FIELD        IN VARCHAR2,
                                    P_ERR_MSG      IN VARCHAR2);


   PROCEDURE INSERT_HISTORY (PROJECT_ID           NUMBER,
                             PROCESS_ID           NUMBER,
                             APPROVER_ROLE        VARCHAR2,
                             APPROVER_NAME        VARCHAR2,
                             APPROVER_STATUS      VARCHAR2,
                             APPROVER_COMMENTS    VARCHAR2);

   PROCEDURE INSERT_ERROR (P_PROCESS     IN     VARCHAR2,
                           P_ERROR_MSG   IN     VARCHAR2,
                           ERRBUF           OUT VARCHAR2,
                           RETCODE          OUT VARCHAR2);

   PROCEDURE ASSIGN_APPROVAL_GROUPS (P_PROJECT_ID IN NUMBER, P_PROCESS_ID IN NUMBER);

   FUNCTION CHECK_STATUS_MONITOR (P_PROJECT_ID IN NUMBER)
      RETURN VARCHAR2;

   PROCEDURE GET_PROJECT_HISTORY (P_PROJECT_NO     IN     NUMBER,
                                  P_PROJECT_HIST      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG           OUT VARCHAR2,
                                  P_ERR_MSG           OUT VARCHAR2);

   PROCEDURE UPDATE_HISTORY (PROCESS_ID           VARCHAR2,
                             PROJECT_ID           VARCHAR2,
                             PROJECT_STATUS       VARCHAR2,
                             APPROVER_NAME        VARCHAR2,
                             APPROVER_STATUS      VARCHAR2,
                             APPROVER_COMMENTS    VARCHAR2,
                             X_PROGRESS           VARCHAR2);

   PROCEDURE GET_APPR_HIST (P_PROJECT_NO     IN     NUMBER,
                            P_PROJECT_HIST      OUT G_REF_CUR_TYP,
                            P_ERR_FLG           OUT VARCHAR2,
                            P_ERR_MSG           OUT VARCHAR2);

   PROCEDURE VALIDATE_PROJECT (P_PROJECT_NO      IN     NUMBER,
                               P_ROLE_ID         IN     VARCHAR2,
                               P_USER_ID         IN     VARCHAR2,
                               P_PROJECT_ERROR      OUT G_REF_CUR_TYP,
                               P_ERR_FLG            OUT VARCHAR2,
                               P_ERR_MSG            OUT VARCHAR2);

   PROCEDURE NOTIFICATION_BODY_DOC (PROJECT_ID      IN     VARCHAR2,
                                    DISPLAY_TYPE    IN     VARCHAR2,
                                    DOCUMENT        IN OUT VARCHAR2,
                                    DOCUMENT_TYPE   IN OUT VARCHAR2);

   PROCEDURE MASTER_PROJECT_LIST (P_MASTER_PROJECT_LIST   OUT G_REF_CUR_TYP,
                                  P_ERR_FLG               OUT VARCHAR2,
                                  P_ERR_MSG               OUT VARCHAR2);

   /*
      PROCEDURE APPROVAL_STATUS_LIST (P_PROJECT_STS_LIST   OUT G_REF_CUR_TYP,
                                      P_ERR_FLG            OUT VARCHAR2,
                                      P_ERR_MSG            OUT VARCHAR2);
   */
   PROCEDURE GET_ITEM_MAIN_ATTRIBUTES (P_PROJECT_ID             IN     NUMBER,
                                       P_USER_ID                IN     VARCHAR2,
                                       P_ITEM_MAIN_ATTRIBUTES      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                   OUT VARCHAR2,
                                       P_ERR_MSG                   OUT VARCHAR2);

   PROCEDURE GET_ITEM_MAIN_ATTR_VALUES (P_TEMPLATE_NAME               VARCHAR2,
                                        P_ITEM_MAIN_ATTR_VALUES   OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                 OUT VARCHAR2,
                                        P_ERR_MSG                 OUT VARCHAR2);

   PROCEDURE GET_ITEM_ADDL_ATTRIBUTES (P_ITEM_ADDL_ATTRIBUTES   OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2);

   PROCEDURE TEMPLATE_NAME_LIST (P_TEMPL_NAME_LIST   OUT G_REF_CUR_TYP,
                                 P_ERR_FLG           OUT VARCHAR2,
                                 P_ERR_MSG           OUT VARCHAR2);


   PROCEDURE CAT_TEMPLATE_LIST (P_PROJECT_CATEGORY   IN     VARCHAR2,
                                P_TEMPL_NAME_LIST       OUT G_REF_CUR_TYP,
                                P_ERR_FLG               OUT VARCHAR2,
                                P_ERR_MSG               OUT VARCHAR2);


   PROCEDURE GET_PROJ_ITEM_ATTR_VALUES (P_PROJECT_ID                  NUMBER,
                                        P_ITEM_ID                     NUMBER,
                                        P_USER_ID                     VARCHAR2,
                                        P_ITEM_MAIN_ATTR_VALUES   OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                 OUT VARCHAR2,
                                        P_ERR_MSG                 OUT VARCHAR2);

   PROCEDURE GET_PROJ_ITEM_ADD_ATTR_VALUES (P_PROJECT_ID                  NUMBER,
                                            P_ITEM_ID                     NUMBER,
                                            P_ITEM_MAIN_ATTR_VALUES   OUT G_REF_CUR_TYP,
                                            P_ERR_FLG                 OUT VARCHAR2,
                                            P_ERR_MSG                 OUT VARCHAR2);

   PROCEDURE PROJECT_TYPE_LIST (P_PROJ_TYPE_LIST   OUT G_REF_CUR_TYP,
                                P_ERR_FLG          OUT VARCHAR2,
                                P_ERR_MSG          OUT VARCHAR2);

   PROCEDURE ADD_NOTES (P_PROJECT_ID   IN     NUMBER,
                        P_USER_ID      IN     VARCHAR2,
                        P_COMMENTS     IN     VARCHAR2,
                        ERRBUF            OUT VARCHAR2,
                        RETCODE           OUT VARCHAR2);

   PROCEDURE START_APPRV_PROCESS (P_PROJECT_ID   IN     NUMBER,
                                  P_ERR_FLG         OUT VARCHAR2,
                                  P_ERR_MSG         OUT VARCHAR2);

   PROCEDURE APPREJ_ACTION (P_PROJECT_ID   IN     VARCHAR2,
                            P_USER_ID      IN     VARCHAR2,
                            P_COMMENTS     IN     VARCHAR2,
                            P_ROLE_ID      IN     VARCHAR2,
                            --p_respid       IN     NUMBER,
                            P_ACTION       IN     VARCHAR2,
                            ERRBUF            OUT VARCHAR2,
                            RETCODE           OUT VARCHAR2);

   PROCEDURE UPDATE_PROJECT_ITEMS_STATUS (P_PROJECT_ID   IN     NUMBER,
                                          P_ITEM_ID      IN     NUMBER,
                                          P_ERROR_CODE   IN     VARCHAR2,
                                          P_ERROR_MESS   IN     VARCHAR2,
                                          P_ERROR_FLAG      OUT VARCHAR2);

   PROCEDURE VALIDATE_ITEM_CREATION (P_PROJECT_NO        IN     VARCHAR2,
                                     P_PROJECT_DEATILS      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG              OUT VARCHAR2);

   PROCEDURE CHECK_PRICING_ASSIGN (P_PROJECT_NO IN VARCHAR2, P_ERR_FLG OUT VARCHAR2);

   PROCEDURE SAVE_PROJECT_SEARCH (P_PROJECT_NUMBER            NUMBER,
                                  P_PROJECT_NAME              VARCHAR2,
                                  P_PROJECT_CATEGORY          VARCHAR2,
                                  P_PROJECT_DESCRIPTION       VARCHAR2,
                                  P_MASTER_PROJECT            VARCHAR2,
                                  P_PROJECT_REQUESTOR         VARCHAR2,
                                  P_PROJECT_STATUS            VARCHAR2,
                                  P_REQ_DATE_FROM             DATE,
                                  P_REQ_DATE_TO               DATE,
                                  P_USER                      VARCHAR2,
                                  P_SAVED_NAME                VARCHAR2,
                                  P_ERR_FLG               OUT VARCHAR2,
                                  P_ERR_MSG               OUT VARCHAR2);

   PROCEDURE GET_SAVEDSEARCH_LIST (P_USER                   IN     VARCHAR2,
                                   P_SAVEDSEARCH_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                   OUT VARCHAR2,
                                   P_ERR_MSG                   OUT VARCHAR2);

   PROCEDURE GET_SAVEDSEARCH_VALUES (P_SAVEDSEARCH_NAME         VARCHAR2,
                                     P_USER                     VARCHAR2,
                                     P_SAVEDSEARCH_VALUES   OUT G_REF_CUR_TYP,
                                     P_ERR_FLG              OUT VARCHAR2,
                                     P_ERR_MSG              OUT VARCHAR2);

   PROCEDURE GET_COMPONENT_LIST (P_COMPONENT        IN     VARCHAR2,
                                 P_COMPONENT_LIST      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG             OUT VARCHAR2,
                                 P_ERR_MSG             OUT VARCHAR2);

   PROCEDURE GET_COMP_ITEM_DETAILS (P_COMPONENT_NAME          VARCHAR2,
                                    P_COMP_ITEM_DETAILS   OUT G_REF_CUR_TYP,
                                    P_ERR_FLG             OUT VARCHAR2,
                                    P_ERR_MSG             OUT VARCHAR2);

   PROCEDURE SAVE_BOM_ITEMS (P_BOM_ITEMS_TBL   IN     CANON_E008_BOM_ITEMS_TBL_TYPE,
                             P_ERR_FLG            OUT VARCHAR2,
                             P_ERR_MSG            OUT VARCHAR2);

   PROCEDURE GET_COMP_ITEMS (P_SET_ITEM_ID       NUMBER,
                             P_COMP_ITEMS    OUT G_REF_CUR_TYP,
                             P_ERR_FLG       OUT VARCHAR2,
                             P_ERR_MSG       OUT VARCHAR2);

   PROCEDURE GET_COMP_ITEMS_DETAILS (P_PROJECT_NO     IN     NUMBER,
                                     P_SET_ITEM_ID    IN     NUMBER,
                                     P_SET_ITEM_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG           OUT VARCHAR2,
                                     P_ERR_MSG           OUT VARCHAR2);

   PROCEDURE GET_ASL_NAMES (P_MERCH_TYPE   IN     VARCHAR2,
                            P_SUPPLIER_CODE IN VARCHAR2,
                            P_SUPPLIER_SITE_CODE IN VARCHAR2,
                            P_ASL_NAME        OUT VARCHAR2,
                            P_ERR_FLG         OUT VARCHAR2,
                            P_ERR_MSG         OUT VARCHAR2);

   PROCEDURE GET_PRICELIST_NAMES (P_OWNING_DIVISION  IN  VARCHAR2,
                                  P_PRICE_LIST_TBL   OUT G_REF_CUR_TYP,
                                  P_ERR_FLG          OUT VARCHAR2,
                                  P_ERR_MSG          OUT VARCHAR2);

   PROCEDURE VALIDATE_UPLOAD_ITEMS (P_PROJECT_NO      IN     NUMBER,
                                    P_ITEM_TBL        IN     CANON_E008_PROJ_ITEMS_TBL_TYPE,
                                    P_USER_ID         IN     VARCHAR2,
                                    P_PROJECT_ERROR      OUT VARCHAR2,    --G_REF_CUR_TYP,
                                    P_ERR_FLG            OUT VARCHAR2,
                                    P_ERR_MSG            OUT VARCHAR2);

   PROCEDURE GET_PROJ_ERRORS (P_PROJECT_NO       IN     VARCHAR2,
                              P_PROJECT_ERRORS      OUT G_REF_CUR_TYP,
                              P_ERR_FLG             OUT VARCHAR2);

   PROCEDURE GET_CUSA_ITEMS (P_TEMPLATE_NAME   IN  VARCHAR2,
                            P_PROJECT_NO      IN     NUMBER,
                            P_ITEM_NO         IN     VARCHAR2,
                            P_ITEM_MAIN_ATTR_VALUES   OUT G_REF_CUR_TYP,
                            P_BOM_COMPONENTS          OUT G_REF_CUR_TYP,
                            P_ERR_FLG                 OUT VARCHAR2,
                            P_ERR_MSG                 OUT VARCHAR2);

   PROCEDURE SAVE_CUSA_BOM_ITEMS (P_PROJECT_NO      IN     NUMBER,
                                  P_ITEM_NO         IN     VARCHAR2,
                                  P_ITEM_ID         IN     NUMBER,
                                  P_USER_ID         IN     VARCHAR2,
                                  P_ERR_FLG            OUT VARCHAR2,
                                  P_ERR_MSG            OUT VARCHAR2);


END CANON_E008_ITEM_WORKBENCH_PKG;
/

create or replace PACKAGE BODY CANON_E008_ITEM_WORKBENCH_PKG
AS
   /********************************************************************
   Modification History:

   Version      Date               Author                 Remarks
   =======   ===========   ====================   ====================
   1.0        02/15/2016        Lakshmi Majeti   Item Project Workbench
   1.1        05/15/2017        Madhusudan Duna  Item Project Workbench
   **********************************************************************/

   PROCEDURE SAVE_PROJECT_ITEMS (P_PROJECT_REC   IN     CANON_E008_PROJECT_REC,
                                 P_ITEM_TBL      IN     CANON_E008_PROJ_ITEMS_TBL_TYPE,
                                 P_PROJECT_NO       OUT NUMBER,
                                 P_ERR_FLG          OUT VARCHAR2,
                                 P_ERR_MSG          OUT VARCHAR2)
   IS
      CURSOR CUR_DEFAULT_ATTR_NONE (
         P_TEMPLATE_ID    NUMBER)
      IS
         SELECT TAA.ATTRIBUTE_ID, TAA.DEFAULT_VALUE, TAT.E008_WB_TBL_MAP MAP_COL
           FROM CANON_E008_TEMPL_ATTR_ASGN_TBL TAA, CANON_E008_TEMPLATE_ATTR_TBL TAT
          WHERE     1 = 1
                AND TAA.TEMPLATE_ID = P_TEMPLATE_ID
                AND TAT.ATTRIBUTE_ID = TAA.ATTRIBUTE_ID
                AND WORKBENCH_DISPLAY = 'NONE'
                AND TAT.ENABLE_FLAG = 'Y'
                AND DEFAULT_VALUE IS NOT NULL;

      L_INDEX          NUMBER;
      L_PROJECT_ID     NUMBER;
      L_PROJ_ITEM_ID   NUMBER;
      L_ERR_MSG        VARCHAR2 (2000);
      L_PROJ_ITEMS     VARCHAR2 (32000);
      L_DYN_SQL        VARCHAR2 (32000);
      L_TEMPLATE_ID    NUMBER;
      L_DYN_UPD_SQL    VARCHAR2 (32000);
      L_ERRBUF         VARCHAR2 (200);
      L_RETCODE        VARCHAR2 (200);
      L_MANUFACTURER   VARCHAR2 (200);

   BEGIN
      BEGIN
         IF P_PROJECT_REC.PROJECT_ID IS NULL OR P_PROJECT_REC.PROJECT_ID = 0
         THEN
            BEGIN
               SELECT CANON_E008_PROJECT_ID_SEQ.NEXTVAL INTO L_PROJECT_ID FROM DUAL;

               P_PROJECT_NO := L_PROJECT_ID;
            EXCEPTION
               WHEN OTHERS
               THEN
                  NULL;
            END;


            INSERT INTO CANON_E008_PROJECT_WB_TBL (EZTABLEID,
                                                   EZINCOMPANYCODE,
                                                   PROJECT_ID,
                                                   PROJECT_NUMBER,
                                                   PROJECT_NAME,
                                                   PROJECT_TYPE,
                                                   PROJECT_DESC,
                                                   MASTER_PROJECT,
                                                   PROJECT_REQUESTOR,
                                                   REQUESTED_DATE,
                                                   --PROJECT_NOTES,
                                                   APPROVAL_STATUS,
                                                   APPROVAL_COMMENTS,
                                                   CREATED_BY,
                                                   CREATION_DATE,
                                                   LAST_UPDATE_BY,
                                                   LAST_UPDATE_DATE)
                 VALUES ('CANON_E008_PROJECT_WB_TBL',
                         'ADB',
                         L_PROJECT_ID,
                         L_PROJECT_ID,                     --p_project_rec.project_number,
                         P_PROJECT_REC.PROJECT_NAME,
                         P_PROJECT_REC.PROJECT_TYPE,
                         P_PROJECT_REC.PROJECT_DESC,
                         P_PROJECT_REC.MASTER_PROJECT,
                         P_PROJECT_REC.PROJECT_REQUESTOR,
                         P_PROJECT_REC.REQUESTED_DATE,
                         --P_PROJECT_REC.PROJECT_NOTES,
                         'ENTERED',
                         P_PROJECT_REC.APPROVAL_COMMENTS,
                         P_PROJECT_REC.CREATED_BY,
                         SYSDATE,
                         P_PROJECT_REC.LAST_UPDATE_BY,
                         SYSDATE);
         ELSE
            L_PROJECT_ID := P_PROJECT_REC.PROJECT_ID;
            P_PROJECT_NO := L_PROJECT_ID;

            --DBMS_OUTPUT.PUT_LINE('INITIATE PROJECT_ID 2 :' ||p_project_rec.project_id);

            UPDATE CANON_E008_PROJECT_WB_TBL
               SET PROJECT_NAME = P_PROJECT_REC.PROJECT_NAME,
                   PROJECT_TYPE = P_PROJECT_REC.PROJECT_TYPE,
                   PROJECT_DESC = P_PROJECT_REC.PROJECT_DESC,
                   MASTER_PROJECT = P_PROJECT_REC.MASTER_PROJECT,
                   PROJECT_REQUESTOR = P_PROJECT_REC.PROJECT_REQUESTOR,
                   --PROJECT_NOTES = PROJECT_NOTES || P_PROJECT_REC.PROJECT_NOTES,
                   LAST_UPDATE_BY = P_PROJECT_REC.LAST_UPDATE_BY,
                   LAST_UPDATE_DATE = SYSDATE
             WHERE PROJECT_ID = L_PROJECT_ID;
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            INSERT_ERROR ('SAVE_PROJECT_ITEMS HDR INSERT',
                          SUBSTR (SQLERRM, 1, 400),
                          L_ERRBUF,
                          L_RETCODE);
      END;

      L_INDEX := P_ITEM_TBL.FIRST;

      WHILE L_INDEX IS NOT NULL
      LOOP
         BEGIN
            SELECT TEMPLATE_ID
              INTO L_TEMPLATE_ID
              FROM CANON_E008_TEMPLATE_HDR_TBL TH
             WHERE TH.TEMPLATE_NAME = (P_ITEM_TBL (L_INDEX).TEMPLATE_NAME);
         EXCEPTION
            WHEN OTHERS
            THEN
               L_TEMPLATE_ID := NULL;
         END;

          BEGIN
                SELECT UPPER (MDSE_ITEM_MNF_NM) LISTNAME
                  INTO L_MANUFACTURER
                 FROM MDSE_ITEM_MNF
                WHERE  GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0'
                  AND MDSE_ITEM_MNF_CD = P_ITEM_TBL (L_INDEX).MANUFACTURER;

           EXCEPTION
              WHEN OTHERS
              THEN
                L_MANUFACTURER := '';
           END;


         BEGIN
            IF P_ITEM_TBL (L_INDEX).PROJ_ITEM_ID IS NULL
            THEN
               BEGIN
                  SELECT CANON_E008_PROJ_ITEM_ID_SEQ.NEXTVAL
                    INTO L_PROJ_ITEM_ID
                    FROM DUAL;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     NULL;
               END;


               INSERT INTO CANON_E008_PROJ_ITEMS_TBL (EZTABLEID,
                                                      COMPANY_CODE,
                                                      PROJECT_ID,
                                                      TEMPLATE_ID,
                                                      PROJ_ITEM_ID,
                                                      ACCOUNTING_RULES,
                                                      ACCRUAL,
                                                      ASSEMBLED_COUNTRY,
                                                      ASSET_RECOVERY_COST,
                                                      ASSURANCE_POINTS_MIN,
                                                      ASSURANCE_POINTS_MAX,
                                                      ASSURANCE_POINTS_PER_LICENSE,
                                                      ASSURANCE_PTS_FIXED_PERORD_QTY,
                                                      BUNDLED_MAINTENANCE_ITEM,
                                                      COMMISSIONS_GROUP,
                                                      COST_OF_GOODS,
                                                      COSTED,
                                                      COVERAGE_BASE_TYPE,
                                                      COVERAGE_TYPE,
                                                      CRITICALITY,
                                                      CUSTOMER_ORDEREABLE,
                                                      DEFAULT_SRC_SUB_WH,
                                                      DEFAULT_SRC_WH,
                                                      ITEM_DEPTH,
                                                      ELAN_CONTROLLED,
                                                      EXPENSE,
                                                      EXTENDED_MAINT_POP_AVAILABLE,
                                                      FIXED_NO_OF_SEATS,
                                                      HEIGHT,
                                                      IMAGEWARE_REMOTE_ITEM,
                                                      IMAGEWARE_REMOTE_ENABLED,
                                                      IMAGEWARE_REMOTE_MODEL,
                                                      INSTALLBASE_CONTROLLED,
                                                      INVENTORY_TRACKABLE,
                                                      INVOICEABLE,
                                                      INVOICING_RULES,
                                                      ITEM_CLASSIFICATION,
                                                      ITEM_DESCRIPTION,
                                                      ITEM_NUMBER,
                                                      ITEM_TYPE,
                                                      ITEM_LENGTH,
                                                      LICENSE_CONTROL,
                                                      LONG_DESCRIPTION,
                                                      MAIN_ENGINE,
                                                      MAINTENANCE_ITEM_TERM,
                                                      MAINTENANCE_ITEM_TYPE,
                                                      MAINTENANCE_POP_AVALIABLE,
                                                      MANUFACTURED_COUNTRY,
                                                      MANUFACTURER,
                                                      MANUFACTURER_ITEM_NUMBER,
                                                      MARKETING_MODEL,
                                                      MARKETING_SEGMENT,
                                                      MATERIAL_GROUP1,
                                                      MATERIAL_GROUP2,
                                                      MATERIAL_GROUP3,
                                                      MAXIMUM_ORDER_QUANTITY,
                                                      MERCHANDISE_TYPE,
                                                      METERED_MACHINE,
                                                      MINIMUM_ORDER_QUANTITY,
                                                      NMFC_CLASS,
                                                      ORDER_INCREMENTS,
                                                      OWNING_DIVISION,
                                                      PART_TYPE,
                                                      PART_YIELD_DAYS,
                                                      PART_YIELD_OUTPUTS,
                                                      PARTS_DROPSHIP_DISABLED,
                                                      PARTS_SURVEY_REQUIRED,
                                                      PLANNING_GROUP,
                                                      PRIVATE_LABEL_FLAG,
                                                      PRODUCT_CODE,
                                                      PRODUCT_LEVEL1,
                                                      PRODUCT_LEVEL2,
                                                      PRODUCT_LEVEL3,
                                                      PRODUCT_LEVEL4,
                                                      PRODUCT_LEVEL5,
                                                      REMAN_AVAILABLE,
                                                      REMANUFACTURED_ITEM_EXISTS,
                                                      RETURN_CONTROL_TYPE,
                                                      RETURN_CONTROLLED,
                                                      RETURN_SUB_WAREHOUSE,
                                                      REVENUE,
                                                      RMA_ALLOWED,
                                                      RMA_INSPECTION_REQUIRED,
                                                      SAFETY_HAZARDOUS_CLASS,
                                                      SAFETY_HAZARDOUS_ID,
                                                      SAFETY_HAZARDOUS_MATERIAL,
                                                      SAFETY_HAZARDOUS_NUMBER,
                                                      SAFETY_HAZARDOUS_SHIP_LABEL,
                                                      SEATS_FROM,
                                                      SEATS_TO,
                                                      SERIAL_CONTROL,
                                                      SERIAL_FROM,
                                                      SERIAL_TO,
                                                      SERVICE_ALLOCATION_TYPE,
                                                      SERVICE_CALL_ENABLED,
                                                      SERVICE_MODEL,
                                                      SOFTWARE_CATEGORY,
                                                      SOFTWARE_MAINTENANCE_CONTROL,
                                                      SOFTWARE_PRODUCT_CATEGORY,
                                                      SOFTWARE_VERSION,
                                                      SOW_REQUIRED,
                                                      STANDARD_COST,
                                                      SUPPLY_COLOR,
                                                      SUPPLY_OEM_CODE,
                                                      SUPPLY_OEM_MANUFACTURER,
                                                      SUPPLY_TYPE,
                                                      SUPPLY_YIELD,
                                                      SUPPLY_YIELD_TYPE,
                                                      SUPPLY_YIELD_UOM,
                                                      TAX_CODE,
                                                      TC_OPTION,
                                                      TC_OPTION_VALUE,
                                                      THIRD_PARTY,
                                                      UNIT_OF_MEASURE,
                                                      UOM_CLASS,
                                                      UPC_CODE,
                                                      WARRANTY,
                                                      WARRANTY_PERIOD,
                                                      WEIGHT,
                                                      TARIFF_CODE,
                                                      FREIGHT_CLASS_CODE,
                                                      RETURN_WAREHOUSE,
                                                      INTANGIBLE_MDSE_TYPE,
                                                      PURCHASE_PRICE,
                                                      SUPPLIER,
                                                      SUPPLIER_ITEM,
                                                      SUPPLIER_SITE,
                                                      SUPERSEDED_BY,
                                                      AREA_OF_PAPER,
                                                      ITEM_BILLING_TYPE,
                                                      CONFIGURATION_FLAG,
                                                      RETURN_VENDOR,
                                                      RETURN_VENDOR_SITE,
                                                      HARD_ALLOCATION_TYPE,
                                                      DEFAULT_SOURCE_TYPE,
                                                      EASY_PACK_I,
                                                      CREATED_BY,
                                                      CREATION_DATE,
                                                      LAST_UPDATE_BY,
                                                      LAST_UPDATE_DATE,
                                                      MERCURY_INCLUDE,
                                                      MSRP_COST,
                                                      UNBOXED_WEIGHT ,
                                                       UNBOXED_WEIGHT_UOM,
                                                       UNBOXED_ITEM_LENGTH,
                                                       UNBOXED_LENGTH_UOM,
                                                       UNBOXED_WIDTH,
                                                       UNBOXED_WIDTH_UOM,
                                                       UNBOXED_HEIGHT,
                                                       UNBOXED_HEIGHT_UOM,
                                                       LEAD_TIME,
                                                       INTERNAL_ITEM)
                       VALUES (
                                 'CANON_E008_PROJ_ITEMS_TBL',
                                 'ADB',
                                 L_PROJECT_ID,
                                 L_TEMPLATE_ID,
                                 L_PROJ_ITEM_ID,
                                 DECODE (P_ITEM_TBL (L_INDEX).ACCOUNTING_RULES,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ACCOUNTING_RULES),
                                 DECODE (P_ITEM_TBL (L_INDEX).ACCRUAL,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ACCRUAL),
                                 DECODE (P_ITEM_TBL (L_INDEX).ASSEMBLED_COUNTRY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ASSEMBLED_COUNTRY),
                                 DECODE (P_ITEM_TBL (L_INDEX).ASSET_RECOVERY_COST,
                                         'NULL', '0.00',
                                          NVL(TO_CHAR(P_ITEM_TBL (L_INDEX).ASSET_RECOVERY_COST,'FM999999999999999.00'),'0.00')),
                                 DECODE (P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MIN,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MIN),
                                 DECODE (P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MAX,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MAX),
                                 DECODE (
                                    P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_PER_LICENSE,
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_PER_LICENSE),
                                 DECODE (
                                    P_ITEM_TBL (L_INDEX).ASSURANCE_PTS_FIXED_PERORD_QTY,
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).ASSURANCE_PTS_FIXED_PERORD_QTY),
                                 DECODE (P_ITEM_TBL (L_INDEX).BUNDLED_MAINTENANCE_ITEM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).BUNDLED_MAINTENANCE_ITEM),
                                 DECODE (P_ITEM_TBL (L_INDEX).COMMISSIONS_GROUP,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COMMISSIONS_GROUP),
                                 DECODE (P_ITEM_TBL (L_INDEX).COST_OF_GOODS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COST_OF_GOODS),
                                 DECODE (P_ITEM_TBL (L_INDEX).COSTED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COSTED),
                                 DECODE (P_ITEM_TBL (L_INDEX).COVERAGE_BASE_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COVERAGE_BASE_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).COVERAGE_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COVERAGE_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).CRITICALITY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).CRITICALITY),
                                 DECODE (P_ITEM_TBL (L_INDEX).CUSTOMER_ORDEREABLE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).CUSTOMER_ORDEREABLE),
                                 DECODE (P_ITEM_TBL (L_INDEX).DEFAULT_SRC_SUB_WH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).DEFAULT_SRC_SUB_WH),
                                 DECODE (P_ITEM_TBL (L_INDEX).DEFAULT_SRC_WH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).DEFAULT_SRC_WH),
                                 DECODE (P_ITEM_TBL (L_INDEX).ITEM_DEPTH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_DEPTH),
                                 DECODE (P_ITEM_TBL (L_INDEX).ELAN_CONTROLLED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ELAN_CONTROLLED),
                                 DECODE (P_ITEM_TBL (L_INDEX).EXPENSE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).EXPENSE),
                                 DECODE (
                                    P_ITEM_TBL (L_INDEX).EXTENDED_MAINT_POP_AVAILABLE,
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).EXTENDED_MAINT_POP_AVAILABLE),
                                 DECODE (P_ITEM_TBL (L_INDEX).FIXED_NO_OF_SEATS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).FIXED_NO_OF_SEATS),
                                 DECODE (P_ITEM_TBL (L_INDEX).HEIGHT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).HEIGHT),
                                 DECODE (P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ITEM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ITEM),
                                 DECODE (P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ENABLED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ENABLED),
                                 DECODE (P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_MODEL,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_MODEL),
                                 DECODE (P_ITEM_TBL (L_INDEX).INSTALLBASE_CONTROLLED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INSTALLBASE_CONTROLLED),
                                 DECODE (P_ITEM_TBL (L_INDEX).INVENTORY_TRACKABLE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INVENTORY_TRACKABLE),
                                 DECODE (P_ITEM_TBL (L_INDEX).INVOICEABLE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INVOICEABLE),
                                 DECODE (P_ITEM_TBL (L_INDEX).INVOICING_RULES,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INVOICING_RULES),
                                 DECODE (P_ITEM_TBL (L_INDEX).ITEM_CLASSIFICATION,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_CLASSIFICATION),
                                 DECODE (P_ITEM_TBL (L_INDEX).ITEM_DESCRIPTION,
                                         'NULL', '',
                                         UPPER(P_ITEM_TBL (L_INDEX).ITEM_DESCRIPTION)),
                                 DECODE (P_ITEM_TBL (L_INDEX).ITEM_NUMBER,
                                         'NULL', '',
                                         UPPER(P_ITEM_TBL (L_INDEX).ITEM_NUMBER)),
                                 DECODE (P_ITEM_TBL (L_INDEX).ITEM_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).ITEM_LENGTH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_LENGTH),
                                 DECODE (P_ITEM_TBL (L_INDEX).LICENSE_CONTROL,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).LICENSE_CONTROL),
                                 DECODE (P_ITEM_TBL (L_INDEX).LONG_DESCRIPTION,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).LONG_DESCRIPTION),
                                 DECODE (P_ITEM_TBL (L_INDEX).MAIN_ENGINE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAIN_ENGINE),
                                 DECODE (P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TERM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TERM),
                                 DECODE (P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).MAINTENANCE_POP_AVALIABLE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAINTENANCE_POP_AVALIABLE),
                                 DECODE (P_ITEM_TBL (L_INDEX).MANUFACTURED_COUNTRY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MANUFACTURED_COUNTRY),
                                 DECODE (P_ITEM_TBL (L_INDEX).MANUFACTURER,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MANUFACTURER),
                                 DECODE (P_ITEM_TBL (L_INDEX).MANUFACTURER_ITEM_NUMBER,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MANUFACTURER_ITEM_NUMBER),
                                 DECODE (P_ITEM_TBL (L_INDEX).MARKETING_MODEL,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MARKETING_MODEL),
                                 DECODE (P_ITEM_TBL (L_INDEX).MARKETING_SEGMENT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MARKETING_SEGMENT),
                                 DECODE (P_ITEM_TBL (L_INDEX).MATERIAL_GROUP1,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MATERIAL_GROUP1),
                                 DECODE (P_ITEM_TBL (L_INDEX).MATERIAL_GROUP2,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MATERIAL_GROUP2),
                                 DECODE (P_ITEM_TBL (L_INDEX).MATERIAL_GROUP3,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MATERIAL_GROUP3),
                                 DECODE (P_ITEM_TBL (L_INDEX).MAXIMUM_ORDER_QUANTITY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAXIMUM_ORDER_QUANTITY),
                                 DECODE (P_ITEM_TBL (L_INDEX).MERCHANDISE_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MERCHANDISE_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).METERED_MACHINE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).METERED_MACHINE),
                                 DECODE (P_ITEM_TBL (L_INDEX).MINIMUM_ORDER_QUANTITY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MINIMUM_ORDER_QUANTITY),
                                 DECODE (P_ITEM_TBL (L_INDEX).NMFC_CLASS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).NMFC_CLASS),
                                 DECODE (P_ITEM_TBL (L_INDEX).ORDER_INCREMENTS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ORDER_INCREMENTS),
                                 DECODE (P_ITEM_TBL (L_INDEX).OWNING_DIVISION,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).OWNING_DIVISION),
                                 DECODE (P_ITEM_TBL (L_INDEX).PART_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PART_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).PART_YIELD_DAYS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PART_YIELD_DAYS),
                                 DECODE (P_ITEM_TBL (L_INDEX).PART_YIELD_OUTPUTS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PART_YIELD_OUTPUTS),
                                 DECODE (P_ITEM_TBL (L_INDEX).PARTS_DROPSHIP_DISABLED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PARTS_DROPSHIP_DISABLED),
                                 DECODE (P_ITEM_TBL (L_INDEX).PARTS_SURVEY_REQUIRED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PARTS_SURVEY_REQUIRED),
                                 DECODE (P_ITEM_TBL (L_INDEX).PLANNING_GROUP,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PLANNING_GROUP),
                                 DECODE (P_ITEM_TBL (L_INDEX).PRIVATE_LABEL_FLAG,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRIVATE_LABEL_FLAG),
                                 DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_CODE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_CODE),
                                 DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL1,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL1),
                                 DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL2,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL2),
                                 DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL3,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL3),
                                 DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL4,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL4),
                                 DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL5,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL5),
                                 DECODE (P_ITEM_TBL (L_INDEX).REMAN_AVAILABLE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).REMAN_AVAILABLE),
                                 DECODE (P_ITEM_TBL (L_INDEX).REMANUFACTURED_ITEM_EXISTS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).REMANUFACTURED_ITEM_EXISTS),
                                 DECODE (P_ITEM_TBL (L_INDEX).RETURN_CONTROL_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_CONTROL_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).RETURN_CONTROLLED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_CONTROLLED),
                                 DECODE (P_ITEM_TBL (L_INDEX).RETURN_SUB_WAREHOUSE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_SUB_WAREHOUSE),
                                 DECODE (P_ITEM_TBL (L_INDEX).REVENUE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).REVENUE),
                                 DECODE (P_ITEM_TBL (L_INDEX).RMA_ALLOWED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RMA_ALLOWED),
                                 DECODE (P_ITEM_TBL (L_INDEX).RMA_INSPECTION_REQUIRED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RMA_INSPECTION_REQUIRED),
                                 DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_CLASS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_CLASS),
                                 DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_ID,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_ID),
                                 DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_MATERIAL,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_MATERIAL),
                                 DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_NUMBER,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_NUMBER),
                                 DECODE (
                                    P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_SHIP_LABEL,
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_SHIP_LABEL),
                                 DECODE (P_ITEM_TBL (L_INDEX).SEATS_FROM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SEATS_FROM),
                                 DECODE (P_ITEM_TBL (L_INDEX).SEATS_TO,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SEATS_TO),
                                 DECODE (P_ITEM_TBL (L_INDEX).SERIAL_CONTROL,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERIAL_CONTROL),
                                 DECODE (P_ITEM_TBL (L_INDEX).SERIAL_FROM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERIAL_FROM),
                                 DECODE (P_ITEM_TBL (L_INDEX).SERIAL_TO,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERIAL_TO),
                                 DECODE (P_ITEM_TBL (L_INDEX).SERVICE_ALLOCATION_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERVICE_ALLOCATION_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).SERVICE_CALL_ENABLED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERVICE_CALL_ENABLED),
                                 DECODE (P_ITEM_TBL (L_INDEX).SERVICE_MODEL,
                                         'NULL', '',
                                         LTRIM(P_ITEM_TBL (L_INDEX).SERVICE_MODEL)),
                                 DECODE (P_ITEM_TBL (L_INDEX).SOFTWARE_CATEGORY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SOFTWARE_CATEGORY),
                                 DECODE (
                                    P_ITEM_TBL (L_INDEX).SOFTWARE_MAINTENANCE_CONTROL,
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).SOFTWARE_MAINTENANCE_CONTROL),
                                 DECODE (P_ITEM_TBL (L_INDEX).SOFTWARE_PRODUCT_CATEGORY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SOFTWARE_PRODUCT_CATEGORY),
                                 DECODE (P_ITEM_TBL (L_INDEX).SOFTWARE_VERSION,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SOFTWARE_VERSION),
                                 DECODE (P_ITEM_TBL (L_INDEX).SOW_REQUIRED,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SOW_REQUIRED),
                                 DECODE (P_ITEM_TBL (L_INDEX).STANDARD_COST,
                                         'NULL', '0.00',
                                         NVL(TO_CHAR(P_ITEM_TBL (L_INDEX).STANDARD_COST,'FM999999999999999.00'),'0.00')),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_COLOR,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_COLOR),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_OEM_CODE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_OEM_CODE),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_OEM_MANUFACTURER,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_OEM_MANUFACTURER),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_YIELD,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_YIELD),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).TAX_CODE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).TAX_CODE),
                                 DECODE (P_ITEM_TBL (L_INDEX).TC_OPTION,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).TC_OPTION),
                                 DECODE (P_ITEM_TBL (L_INDEX).TC_OPTION_VALUE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).TC_OPTION_VALUE),
                                 DECODE (P_ITEM_TBL (L_INDEX).THIRD_PARTY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).THIRD_PARTY),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNIT_OF_MEASURE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNIT_OF_MEASURE),
                                 DECODE (P_ITEM_TBL (L_INDEX).UOM_CLASS,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UOM_CLASS),
                                 DECODE (P_ITEM_TBL (L_INDEX).UPC_CODE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UPC_CODE),
                                 DECODE (P_ITEM_TBL (L_INDEX).WARRANTY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).WARRANTY),
                                 DECODE (P_ITEM_TBL (L_INDEX).WARRANTY_PERIOD,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).WARRANTY_PERIOD),
                                 DECODE (P_ITEM_TBL (L_INDEX).WEIGHT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).WEIGHT),
                                 DECODE (P_ITEM_TBL (L_INDEX).TARIFF_CODE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).TARIFF_CODE),
                                 DECODE (P_ITEM_TBL (L_INDEX).FREIGHT_CLASS_CODE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).FREIGHT_CLASS_CODE),
                                 DECODE (P_ITEM_TBL (L_INDEX).RETURN_WAREHOUSE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_WAREHOUSE),
                                 DECODE (P_ITEM_TBL (L_INDEX).INTANGIBLE_MDSE_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INTANGIBLE_MDSE_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).PURCHASE_PRICE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PURCHASE_PRICE),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLIER,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLIER),
                                 DECODE(L_MANUFACTURER,'CANON',
                                                   DECODE (P_ITEM_TBL (L_INDEX).SUPPLIER,
                                                          'NULL', '',
                                                          P_ITEM_TBL (L_INDEX).ITEM_NUMBER),
                                                   DECODE (P_ITEM_TBL (L_INDEX).SUPPLIER_ITEM,
                                                           'NULL', '',
                                                           P_ITEM_TBL (L_INDEX).SUPPLIER_ITEM)),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPPLIER_SITE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLIER_SITE),
                                 DECODE (P_ITEM_TBL (L_INDEX).SUPERSEDED_BY,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPERSEDED_BY),
                                 DECODE (P_ITEM_TBL (L_INDEX).AREA_OF_PAPER,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).AREA_OF_PAPER),
                                 DECODE (P_ITEM_TBL (L_INDEX).ITEM_BILLING_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_BILLING_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).CONFIGURATION_FLAG,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).CONFIGURATION_FLAG),
                                 DECODE (P_ITEM_TBL (L_INDEX).RETURN_VENDOR,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_VENDOR),
                                 DECODE (P_ITEM_TBL (L_INDEX).RETURN_VENDOR_SITE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_VENDOR_SITE),
                                 DECODE (P_ITEM_TBL (L_INDEX).HARD_ALLOCATION_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).HARD_ALLOCATION_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).DEFAULT_SOURCE_TYPE,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).DEFAULT_SOURCE_TYPE),
                                 DECODE (P_ITEM_TBL (L_INDEX).EASY_PACK_I,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).EASY_PACK_I),
                                 P_ITEM_TBL (L_INDEX).CREATED_BY,
                                 SYSDATE,
                                 P_ITEM_TBL (L_INDEX).LAST_UPDATE_BY,
                                 SYSDATE,
                                 DECODE (P_ITEM_TBL (L_INDEX).MERCURY_INCLUDE,
                                      'on', 'Y',
                                      'N'),
                                 NVL(TO_CHAR(P_ITEM_TBL (L_INDEX).MSRP_COST,'FM999999999999999.00'),'0.00'),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_ITEM_LENGTH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_ITEM_LENGTH),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_LENGTH_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_LENGTH_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).LEAD_TIME,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).LEAD_TIME),
                                 DECODE (P_ITEM_TBL (L_INDEX).INTERNAL_ITEM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INTERNAL_ITEM)                                 );
            ELSE
               L_PROJ_ITEM_ID := P_ITEM_TBL (L_INDEX).PROJ_ITEM_ID;

               UPDATE CANON_E008_PROJ_ITEMS_TBL
                  SET ACCOUNTING_RULES = P_ITEM_TBL (L_INDEX).ACCOUNTING_RULES,
                      ACCRUAL = P_ITEM_TBL (L_INDEX).ACCRUAL,
                      ASSEMBLED_COUNTRY = P_ITEM_TBL (L_INDEX).ASSEMBLED_COUNTRY,
                      ASSET_RECOVERY_COST = NVL(TO_CHAR(P_ITEM_TBL (L_INDEX).ASSET_RECOVERY_COST,'FM999999999999999.00'),'0.00'),  --P_ITEM_TBL (L_INDEX).ASSET_RECOVERY_COST,
                      ASSURANCE_POINTS_MIN = P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MIN,
                      ASSURANCE_POINTS_MAX = P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MAX,
                      ASSURANCE_POINTS_PER_LICENSE =
                         P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_PER_LICENSE,
                      ASSURANCE_PTS_FIXED_PERORD_QTY =
                         P_ITEM_TBL (L_INDEX).ASSURANCE_PTS_FIXED_PERORD_QTY,
                      BUNDLED_MAINTENANCE_ITEM =
                         P_ITEM_TBL (L_INDEX).BUNDLED_MAINTENANCE_ITEM,
                      COMMISSIONS_GROUP = P_ITEM_TBL (L_INDEX).COMMISSIONS_GROUP,
                      COST_OF_GOODS = P_ITEM_TBL (L_INDEX).COST_OF_GOODS,
                      COSTED = P_ITEM_TBL (L_INDEX).COSTED,
                      COVERAGE_BASE_TYPE = P_ITEM_TBL (L_INDEX).COVERAGE_BASE_TYPE,
                      COVERAGE_TYPE = P_ITEM_TBL (L_INDEX).COVERAGE_TYPE,
                      CRITICALITY = P_ITEM_TBL (L_INDEX).CRITICALITY,
                      CUSTOMER_ORDEREABLE = P_ITEM_TBL (L_INDEX).CUSTOMER_ORDEREABLE,
                      DEFAULT_SRC_SUB_WH = P_ITEM_TBL (L_INDEX).DEFAULT_SRC_SUB_WH,
                      DEFAULT_SRC_WH = P_ITEM_TBL (L_INDEX).DEFAULT_SRC_WH,
                      ITEM_DEPTH = P_ITEM_TBL (L_INDEX).ITEM_DEPTH,
                      ELAN_CONTROLLED = P_ITEM_TBL (L_INDEX).ELAN_CONTROLLED,
                      EXPENSE = P_ITEM_TBL (L_INDEX).EXPENSE,
                      EXTENDED_MAINT_POP_AVAILABLE =
                         P_ITEM_TBL (L_INDEX).EXTENDED_MAINT_POP_AVAILABLE,
                      FIXED_NO_OF_SEATS = P_ITEM_TBL (L_INDEX).FIXED_NO_OF_SEATS,
                      HEIGHT = P_ITEM_TBL (L_INDEX).HEIGHT,
                      IMAGEWARE_REMOTE_ITEM = P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ITEM,
                      IMAGEWARE_REMOTE_ENABLED =
                         P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ENABLED,
                      IMAGEWARE_REMOTE_MODEL = P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_MODEL,
                      INSTALLBASE_CONTROLLED = P_ITEM_TBL (L_INDEX).INSTALLBASE_CONTROLLED,
                      INVENTORY_TRACKABLE = P_ITEM_TBL (L_INDEX).INVENTORY_TRACKABLE,
                      INVOICEABLE = P_ITEM_TBL (L_INDEX).INVOICEABLE,
                      INVOICING_RULES = P_ITEM_TBL (L_INDEX).INVOICING_RULES,
                      ITEM_CLASSIFICATION = P_ITEM_TBL (L_INDEX).ITEM_CLASSIFICATION,
                      ITEM_DESCRIPTION = UPPER(P_ITEM_TBL (L_INDEX).ITEM_DESCRIPTION),
                      ITEM_NUMBER = UPPER(P_ITEM_TBL (L_INDEX).ITEM_NUMBER),
                      ITEM_TYPE = P_ITEM_TBL (L_INDEX).ITEM_TYPE,
                      ITEM_LENGTH = P_ITEM_TBL (L_INDEX).ITEM_LENGTH,
                      LICENSE_CONTROL = P_ITEM_TBL (L_INDEX).LICENSE_CONTROL,
                      LONG_DESCRIPTION = P_ITEM_TBL (L_INDEX).LONG_DESCRIPTION,
                      MAIN_ENGINE = P_ITEM_TBL (L_INDEX).MAIN_ENGINE,
                      MAINTENANCE_ITEM_TERM = P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TERM,
                      MAINTENANCE_ITEM_TYPE = P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TYPE,
                      MAINTENANCE_POP_AVALIABLE =
                         P_ITEM_TBL (L_INDEX).MAINTENANCE_POP_AVALIABLE,
                      MANUFACTURED_COUNTRY = P_ITEM_TBL (L_INDEX).MANUFACTURED_COUNTRY,
                      MANUFACTURER = P_ITEM_TBL (L_INDEX).MANUFACTURER,
                      MANUFACTURER_ITEM_NUMBER =
                         P_ITEM_TBL (L_INDEX).MANUFACTURER_ITEM_NUMBER,
                      MARKETING_MODEL = P_ITEM_TBL (L_INDEX).MARKETING_MODEL,
                      MARKETING_SEGMENT = P_ITEM_TBL (L_INDEX).MARKETING_SEGMENT,
                      MATERIAL_GROUP1 = P_ITEM_TBL (L_INDEX).MATERIAL_GROUP1,
                      MATERIAL_GROUP2 = P_ITEM_TBL (L_INDEX).MATERIAL_GROUP2,
                      MATERIAL_GROUP3 = P_ITEM_TBL (L_INDEX).MATERIAL_GROUP3,
                      MAXIMUM_ORDER_QUANTITY = P_ITEM_TBL (L_INDEX).MAXIMUM_ORDER_QUANTITY,
                      MERCHANDISE_TYPE = P_ITEM_TBL (L_INDEX).MERCHANDISE_TYPE,
                      METERED_MACHINE = P_ITEM_TBL (L_INDEX).METERED_MACHINE,
                      MINIMUM_ORDER_QUANTITY = P_ITEM_TBL (L_INDEX).MINIMUM_ORDER_QUANTITY,
                      NMFC_CLASS = P_ITEM_TBL (L_INDEX).NMFC_CLASS,
                      ORDER_INCREMENTS = P_ITEM_TBL (L_INDEX).ORDER_INCREMENTS,
                      OWNING_DIVISION = P_ITEM_TBL (L_INDEX).OWNING_DIVISION,
                      PART_TYPE = P_ITEM_TBL (L_INDEX).PART_TYPE,
                      PART_YIELD_DAYS = P_ITEM_TBL (L_INDEX).PART_YIELD_DAYS,
                      PART_YIELD_OUTPUTS = P_ITEM_TBL (L_INDEX).PART_YIELD_OUTPUTS,
                      PARTS_DROPSHIP_DISABLED =
                         P_ITEM_TBL (L_INDEX).PARTS_DROPSHIP_DISABLED,
                      PARTS_SURVEY_REQUIRED = P_ITEM_TBL (L_INDEX).PARTS_SURVEY_REQUIRED,
                      PLANNING_GROUP = P_ITEM_TBL (L_INDEX).PLANNING_GROUP,
                      PRIVATE_LABEL_FLAG = P_ITEM_TBL (L_INDEX).PRIVATE_LABEL_FLAG,
                      PRODUCT_CODE = P_ITEM_TBL (L_INDEX).PRODUCT_CODE,
                      PRODUCT_LEVEL1 = P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL1,
                      PRODUCT_LEVEL2 = P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL2,
                      PRODUCT_LEVEL3 = P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL3,
                      PRODUCT_LEVEL4 = P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL4,
                      PRODUCT_LEVEL5 = P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL5,
                      REMAN_AVAILABLE = P_ITEM_TBL (L_INDEX).REMAN_AVAILABLE,
                      REMANUFACTURED_ITEM_EXISTS =
                         P_ITEM_TBL (L_INDEX).REMANUFACTURED_ITEM_EXISTS,
                      RETURN_CONTROL_TYPE = P_ITEM_TBL (L_INDEX).RETURN_CONTROL_TYPE,
                      RETURN_CONTROLLED = P_ITEM_TBL (L_INDEX).RETURN_CONTROLLED,
                      RETURN_SUB_WAREHOUSE = P_ITEM_TBL (L_INDEX).RETURN_SUB_WAREHOUSE,
                      REVENUE = P_ITEM_TBL (L_INDEX).REVENUE,
                      RMA_ALLOWED = P_ITEM_TBL (L_INDEX).RMA_ALLOWED,
                      RMA_INSPECTION_REQUIRED =
                         P_ITEM_TBL (L_INDEX).RMA_INSPECTION_REQUIRED,
                      SAFETY_HAZARDOUS_CLASS = P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_CLASS,
                      SAFETY_HAZARDOUS_ID = P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_ID,
                      SAFETY_HAZARDOUS_MATERIAL =
                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_MATERIAL,
                      SAFETY_HAZARDOUS_NUMBER =
                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_NUMBER,
                      SAFETY_HAZARDOUS_SHIP_LABEL =
                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_SHIP_LABEL,
                      SEATS_FROM = P_ITEM_TBL (L_INDEX).SEATS_FROM,
                      SEATS_TO = P_ITEM_TBL (L_INDEX).SEATS_TO,
                      SERIAL_CONTROL = P_ITEM_TBL (L_INDEX).SERIAL_CONTROL,
                      SERIAL_FROM = P_ITEM_TBL (L_INDEX).SERIAL_FROM,
                      SERIAL_TO = P_ITEM_TBL (L_INDEX).SERIAL_TO,
                      SERVICE_ALLOCATION_TYPE =
                         P_ITEM_TBL (L_INDEX).SERVICE_ALLOCATION_TYPE,
                      SERVICE_CALL_ENABLED = P_ITEM_TBL (L_INDEX).SERVICE_CALL_ENABLED,
                      SERVICE_MODEL = LTRIM(P_ITEM_TBL (L_INDEX).SERVICE_MODEL),
                      SOFTWARE_CATEGORY = P_ITEM_TBL (L_INDEX).SOFTWARE_CATEGORY,
                      SOFTWARE_MAINTENANCE_CONTROL =
                         P_ITEM_TBL (L_INDEX).SOFTWARE_MAINTENANCE_CONTROL,
                      SOFTWARE_PRODUCT_CATEGORY =
                         P_ITEM_TBL (L_INDEX).SOFTWARE_PRODUCT_CATEGORY,
                      SOFTWARE_VERSION = P_ITEM_TBL (L_INDEX).SOFTWARE_VERSION,
                      SOW_REQUIRED = P_ITEM_TBL (L_INDEX).SOW_REQUIRED,
                      STANDARD_COST = NVL(TO_CHAR(P_ITEM_TBL (L_INDEX).STANDARD_COST,'FM999999999999999.00'),'0.00'),
                      SUPPLY_COLOR = P_ITEM_TBL (L_INDEX).SUPPLY_COLOR,
                      SUPPLY_OEM_CODE = P_ITEM_TBL (L_INDEX).SUPPLY_OEM_CODE,
                      SUPPLY_OEM_MANUFACTURER =
                         P_ITEM_TBL (L_INDEX).SUPPLY_OEM_MANUFACTURER,
                      SUPPLY_TYPE = P_ITEM_TBL (L_INDEX).SUPPLY_TYPE,
                      SUPPLY_YIELD = P_ITEM_TBL (L_INDEX).SUPPLY_YIELD,
                      SUPPLY_YIELD_TYPE = P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_TYPE,
                      SUPPLY_YIELD_UOM = P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_UOM,
                      TAX_CODE = P_ITEM_TBL (L_INDEX).TAX_CODE,
                      TC_OPTION = P_ITEM_TBL (L_INDEX).TC_OPTION,
                      TC_OPTION_VALUE = P_ITEM_TBL (L_INDEX).TC_OPTION_VALUE,
                      THIRD_PARTY = P_ITEM_TBL (L_INDEX).THIRD_PARTY,
                      UNIT_OF_MEASURE = P_ITEM_TBL (L_INDEX).UNIT_OF_MEASURE,
                      UOM_CLASS = P_ITEM_TBL (L_INDEX).UOM_CLASS,
                      UPC_CODE = P_ITEM_TBL (L_INDEX).UPC_CODE,
                      WARRANTY = P_ITEM_TBL (L_INDEX).WARRANTY,
                      WARRANTY_PERIOD = P_ITEM_TBL (L_INDEX).WARRANTY_PERIOD,
                      WEIGHT = P_ITEM_TBL (L_INDEX).WEIGHT,
                      TARIFF_CODE = P_ITEM_TBL (L_INDEX).TARIFF_CODE,
                      FREIGHT_CLASS_CODE = P_ITEM_TBL (L_INDEX).FREIGHT_CLASS_CODE,
                      RETURN_WAREHOUSE = P_ITEM_TBL (L_INDEX).RETURN_WAREHOUSE,
                      INTANGIBLE_MDSE_TYPE = P_ITEM_TBL (L_INDEX).INTANGIBLE_MDSE_TYPE,
                      PURCHASE_PRICE = P_ITEM_TBL (L_INDEX).PURCHASE_PRICE,
                      SUPPLIER = P_ITEM_TBL (L_INDEX).SUPPLIER,
                      SUPPLIER_ITEM =  DECODE(L_MANUFACTURER,'CANON',
                                                   DECODE(P_ITEM_TBL (L_INDEX).SUPPLIER,'','',P_ITEM_TBL (L_INDEX).ITEM_NUMBER),P_ITEM_TBL (L_INDEX).SUPPLIER_ITEM),
                      SUPPLIER_SITE = P_ITEM_TBL (L_INDEX).SUPPLIER_SITE,
                      SUPERSEDED_BY = P_ITEM_TBL (L_INDEX).SUPERSEDED_BY,
                      AREA_OF_PAPER = P_ITEM_TBL (L_INDEX).AREA_OF_PAPER,
                      ITEM_BILLING_TYPE = P_ITEM_TBL (L_INDEX).ITEM_BILLING_TYPE,
                      CONFIGURATION_FLAG = P_ITEM_TBL (L_INDEX).CONFIGURATION_FLAG,
                      RETURN_VENDOR = P_ITEM_TBL (L_INDEX).RETURN_VENDOR,
                      RETURN_VENDOR_SITE = P_ITEM_TBL (L_INDEX).RETURN_VENDOR_SITE,
                      HARD_ALLOCATION_TYPE = P_ITEM_TBL (L_INDEX).HARD_ALLOCATION_TYPE,
                      DEFAULT_SOURCE_TYPE = P_ITEM_TBL (L_INDEX).DEFAULT_SOURCE_TYPE,
                      EASY_PACK_I = P_ITEM_TBL (L_INDEX).EASY_PACK_I,
                      LAST_UPDATE_BY = P_ITEM_TBL (L_INDEX).LAST_UPDATE_BY,
                      LAST_UPDATE_DATE = SYSDATE,
                      MERCURY_INCLUDE = DECODE (P_ITEM_TBL (L_INDEX).MERCURY_INCLUDE,
                                      'on', 'Y',
                                      'N'),
                      MSRP_COST = NVL(TO_CHAR(P_ITEM_TBL (L_INDEX).MSRP_COST,'FM999999999999999.00'),'0.00'), --NVL(P_ITEM_TBL (L_INDEX).MSRP_COST,'0')
                      UNBOXED_WEIGHT = P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT,
                      UNBOXED_WEIGHT_UOM = P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT_UOM,
                      UNBOXED_ITEM_LENGTH = P_ITEM_TBL (L_INDEX).UNBOXED_ITEM_LENGTH,
                      UNBOXED_LENGTH_UOM = P_ITEM_TBL (L_INDEX).UNBOXED_LENGTH_UOM,
                      UNBOXED_WIDTH = P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH,
                      UNBOXED_WIDTH_UOM = P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH_UOM,
                      UNBOXED_HEIGHT = P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT,
                      UNBOXED_HEIGHT_UOM = P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT_UOM,
                      LEAD_TIME = P_ITEM_TBL (L_INDEX).LEAD_TIME,
                      INTERNAL_ITEM = P_ITEM_TBL (L_INDEX).INTERNAL_ITEM
                WHERE PROJECT_ID = L_PROJECT_ID                 --p_project_rec.PROJECT_ID
                                                --AND template_id = p_item_tbl (l_index).template_id
                      AND TEMPLATE_ID = L_TEMPLATE_ID AND PROJ_ITEM_ID = L_PROJ_ITEM_ID;
            END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               INSERT_ERROR ('SAVE_PROJECT_ITEMS INSERT',
                             SUBSTR (SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);
         END;


         FOR DEFAULT_ATTR_NONE_REC IN CUR_DEFAULT_ATTR_NONE (L_TEMPLATE_ID)
         LOOP
            BEGIN
               L_DYN_UPD_SQL :=
                     'UPDATE CANON_E008_PROJ_ITEMS_TBL  pit SET '
                  || DEFAULT_ATTR_NONE_REC.MAP_COL
                  || ' = '''
                  || DEFAULT_ATTR_NONE_REC.DEFAULT_VALUE
                  || ''''
                  || ' WHERE pit.PROJECT_ID = '
                  || L_PROJECT_ID
                  || ' AND pit.TEMPLATE_ID = '
                  || L_TEMPLATE_ID;

               EXECUTE IMMEDIATE L_DYN_UPD_SQL;
            EXCEPTION
               WHEN OTHERS
               THEN
                  INSERT_ERROR ('SAVE_PROJECT_ITEMS-DYN UPDATE',
                                SUBSTR (SQLERRM, 1, 400),
                                L_ERRBUF,
                                L_RETCODE);
            END;
         END LOOP;


         IF P_ITEM_TBL (L_INDEX).ITEM_TYPE IN (10,11) THEN
            SAVE_CUSA_BOM_ITEMS ( L_PROJECT_ID,
                                  P_ITEM_TBL (L_INDEX).ITEM_NUMBER,
                                  L_PROJ_ITEM_ID,
                                  P_ITEM_TBL (L_INDEX).CREATED_BY,
                                  L_ERRBUF,
                                  L_RETCODE);
        END IF;

         L_INDEX := P_ITEM_TBL.NEXT (L_INDEX);
      END LOOP;

      BEGIN
             /*
            UPDATE CANON_E008_PROJ_ITEMS_TBL
            SET Item_description = REGEXP_REPLACE(item_description, '[^0-9A-Za-z &,-/:]', ' '),
                Item_number = replace(Item_number,'"',''), --REGEXP_REPLACE(Item_number, '[^0-9A-Za-z &,-/:]', ' '),
                Manufacturer_item_number = REGEXP_REPLACE(Manufacturer_item_number, '[^0-9A-Za-z &,-/:]', ' '),
                upc_code = REGEXP_REPLACE(upc_code, '[^0-9A-Za-z &,-/:]', ' '),
                Long_description = REGEXP_REPLACE(long_description, '[^0-9A-Za-z &,-/:]', ' '),
                superseded_by = REGEXP_REPLACE(superseded_by, '[^0-9A-Za-z &,-/:]', ' '),
                area_of_paper = REGEXP_REPLACE(area_of_paper, '[^0-9A-Za-z &,-/:]', ' ')
            WHERE Project_id in (Select Project_id from CANON_E008_PROJECT_WB_TBL where approval_status <> 'CLOSED');
                        */
	    UPDATE CANON_E008_TEMPLATE_ATTR_TBL
	       SET attribute_name = 'Maintenance - POP Available'
	     where e008_wb_tbl_map = 'MAINTENANCE_POP_AVALIABLE'
	     and  attribute_name =  'Maintenance  - POP Available';

      END;

      P_ERR_FLG := 'S';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END SAVE_PROJECT_ITEMS;

   PROCEDURE GET_PROJECT_ITEMS (P_PROJECT_NO    IN     NUMBER,
                                P_PROJECT_REC      OUT CANON_E008_PROJECT_REC,
                                P_ITEM_TBL         OUT G_REF_CUR_TYP,
                                P_ERR_FLG          OUT VARCHAR2,
                                P_ERR_MSG          OUT VARCHAR2)
   IS
      L_ERR_FLAG      VARCHAR2 (1);
      L_ERR_MSG       VARCHAR2 (2000);
      L_INDEX         NUMBER := 1;
      L_PROJECT_REC   CANON_E008_PROJECT_REC;
   BEGIN
      -- DBMS_OUTPUT.PUT_LINE ('MESSAGE1 ');
      L_PROJECT_REC := CANON_E008_PROJECT_REC ();

      -- DBMS_OUTPUT.PUT_LINE ('MESSAGE2 ');

      FOR PROJECT_CUR IN (SELECT *
                            FROM CANON_E008_PROJECT_WB_TBL
                           WHERE PROJECT_ID = P_PROJECT_NO)
      LOOP
         --DBMS_OUTPUT.PUT_LINE ('MESSAGE3 ');
         L_PROJECT_REC.EZTABLEID := PROJECT_CUR.EZTABLEID;
         L_PROJECT_REC.EZINCOMPANYCODE := PROJECT_CUR.EZINCOMPANYCODE;
         L_PROJECT_REC.PROJECT_ID := PROJECT_CUR.PROJECT_ID;
         L_PROJECT_REC.PROJECT_NUMBER := PROJECT_CUR.PROJECT_NUMBER;
         L_PROJECT_REC.PROJECT_NAME := PROJECT_CUR.PROJECT_NAME;
         L_PROJECT_REC.PROJECT_TYPE := PROJECT_CUR.PROJECT_TYPE;
         L_PROJECT_REC.PROJECT_DESC := PROJECT_CUR.PROJECT_DESC;
         L_PROJECT_REC.MASTER_PROJECT := PROJECT_CUR.MASTER_PROJECT;
         L_PROJECT_REC.PROJECT_REQUESTOR := PROJECT_CUR.PROJECT_REQUESTOR;
         L_PROJECT_REC.REQUESTED_DATE := PROJECT_CUR.REQUESTED_DATE;
         L_PROJECT_REC.PROJECT_NOTES := PROJECT_CUR.PROJECT_NOTES;
         L_PROJECT_REC.APPROVAL_STATUS := PROJECT_CUR.APPROVAL_STATUS;
         L_PROJECT_REC.APPROVAL_COMMENTS := PROJECT_CUR.APPROVAL_COMMENTS;
         L_PROJECT_REC.PROCESS_FLAG := PROJECT_CUR.PROCESS_FLAG;
         --l_project_rec.created_by := project_cur.created_by;
         L_PROJECT_REC.CREATION_DATE := PROJECT_CUR.CREATION_DATE;
         L_PROJECT_REC.LAST_UPDATE_BY := PROJECT_CUR.LAST_UPDATE_BY;
         L_PROJECT_REC.LAST_UPDATE_DATE := PROJECT_CUR.LAST_UPDATE_DATE;

         BEGIN
            SELECT FIRST_NM || ' ' || LAST_NM
              INTO L_PROJECT_REC.CREATED_BY
              FROM AUTH_PSN
             WHERE USR_NM = PROJECT_CUR.CREATED_BY AND GLBL_CMPY_CD = 'ADB';
         EXCEPTION
            WHEN OTHERS
            THEN
               L_PROJECT_REC.CREATED_BY := PROJECT_CUR.CREATED_BY;
         END;

         P_PROJECT_REC := L_PROJECT_REC;
      END LOOP;


      OPEN P_ITEM_TBL FOR
           SELECT PI.PROJECT_ID,
                  PI.PROJ_ITEM_ID ITEM_ID,
                  TH.TEMPLATE_ID,
                  TH.TEMPLATE_NAME TEMPLATE,
                  NULL ATTRIBUTE,
                  NULL ATTRIBUTE_VALUE,
                  NULL ATTRIBUTE_REQ,
                  NULL ATTRIBUTE_VALID,
                  NULL ATTRIBUTE_LOVFLAG,
                  NULL ATTRIBUTE_LOVNAME,
                  PI.PROCESS_FLAG ATTRIBUTE_DEFVALUE,
                  --NULL ATTRIBUTE_DEFVALUE,
                  PI.MERCURY_INCLUDE
             FROM CANON_E008_PROJ_ITEMS_TBL PI, CANON_E008_TEMPLATE_HDR_TBL TH
            WHERE PI.TEMPLATE_ID = TH.TEMPLATE_ID(+) AND PROJECT_ID = P_PROJECT_NO
         ORDER BY ITEM_ID;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_PROJECT_ITEMS;

   PROCEDURE GET_PROJECT_ITEMS_DETAILS (P_PROJECT_NO   IN     NUMBER,
                                        P_ITEM_TBL        OUT G_REF_CUR_TYP,
                                        P_ERR_FLG         OUT VARCHAR2,
                                        P_ERR_MSG         OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_ITEM_TBL FOR
           SELECT PI.EZTABLEID,
                  PI.COMPANY_CODE,
                  PI.PROJECT_ID,
                  PI.TEMPLATE_ID,
                  TH.TEMPLATE_NAME,
                  PI.PROJ_ITEM_ID,
                  PI.ACCOUNTING_RULES,
                  PI.ACCRUAL,
                  PI.ASSEMBLED_COUNTRY,
                  PI.ASSET_RECOVERY_COST,
                  PI.ASSURANCE_POINTS_MIN,
                  PI.ASSURANCE_POINTS_MAX,
                  PI.ASSURANCE_POINTS_PER_LICENSE,
                  PI.ASSURANCE_PTS_FIXED_PERORD_QTY,
                  PI.BUNDLED_MAINTENANCE_ITEM,
                  PI.COMMISSIONS_GROUP,
                  PI.COST_OF_GOODS,
                  PI.COSTED,
                  PI.COVERAGE_BASE_TYPE,
                  PI.COVERAGE_TYPE,
                  PI.CRITICALITY,
                  PI.CUSTOMER_ORDEREABLE,
                  PI.DEFAULT_SRC_SUB_WH,
                  PI.DEFAULT_SRC_WH,
                  NVL(PI.ITEM_DEPTH,0) ITEM_DEPTH,
                  PI.ELAN_CONTROLLED,
                  PI.EXPENSE,
                  PI.EXTENDED_MAINT_POP_AVAILABLE,
                  PI.FIXED_NO_OF_SEATS,
                  NVL(PI.HEIGHT,0) HEIGHT,
                  PI.IMAGEWARE_REMOTE_ITEM,
                  PI.IMAGEWARE_REMOTE_ENABLED,
                  PI.IMAGEWARE_REMOTE_MODEL,
                  PI.INSTALLBASE_CONTROLLED,
                  PI.INVENTORY_TRACKABLE,
                  PI.INVOICEABLE,
                  PI.INVOICING_RULES,
                  PI.ITEM_CLASSIFICATION,
                  PI.ITEM_DESCRIPTION,
                  PI.ITEM_NUMBER,
                  PI.ITEM_TYPE,
                  NVL(PI.ITEM_LENGTH,0) ITEM_LENGTH,
                  PI.LICENSE_CONTROL,
                  PI.LONG_DESCRIPTION,
                  PI.MAIN_ENGINE,
                  PI.MAINTENANCE_ITEM_TERM,
                  PI.MAINTENANCE_ITEM_TYPE,
                  PI.MAINTENANCE_POP_AVALIABLE,
                  PI.MANUFACTURED_COUNTRY,
                  PI.MANUFACTURER,
                  PI.MANUFACTURER_ITEM_NUMBER,
                  PI.MARKETING_MODEL,
                  PI.MARKETING_SEGMENT,
                  PI.MATERIAL_GROUP1,
                  PI.MATERIAL_GROUP2,
                  PI.MATERIAL_GROUP3,
                  PI.MAXIMUM_ORDER_QUANTITY,
                  PI.MERCHANDISE_TYPE,
                  PI.METERED_MACHINE,
                  PI.MINIMUM_ORDER_QUANTITY,
                  PI.NMFC_CLASS,
                  PI.ORDER_INCREMENTS,
                  PI.OWNING_DIVISION,
                  PI.PART_TYPE,
                  PI.PART_YIELD_DAYS,
                  PI.PART_YIELD_OUTPUTS,
                  PI.PARTS_DROPSHIP_DISABLED,
                  PI.PARTS_SURVEY_REQUIRED,
                  PI.PLANNING_GROUP,
                  PI.PRIVATE_LABEL_FLAG,
                  PI.PRODUCT_CODE,
                  (SELECT PROD_CTRL_CD || '|' || PROD_CTRL_NM
                     FROM PROD_CTRL
                    WHERE     GLBL_CMPY_CD = 'ADB'
                          AND EZCANCELFLAG = '0'
						  AND MDSE_STRU_ELMNT_TP_CD = 'PLG'
                          AND PROD_CTRL_CD = PI.PRODUCT_LEVEL1)
                     PRODUCT_LEVEL1,
                  (SELECT PROD_CTRL_CD || '|' || PROD_CTRL_NM
                     FROM PROD_CTRL
                    WHERE     GLBL_CMPY_CD = 'ADB'
                          AND EZCANCELFLAG = '0'
						  AND MDSE_STRU_ELMNT_TP_CD = 'PL'
                          AND PROD_CTRL_CD = PI.PRODUCT_LEVEL2)
                     PRODUCT_LEVEL2,
                  (SELECT PROD_CTRL_CD || '|' || PROD_CTRL_NM
                     FROM PROD_CTRL
                    WHERE     GLBL_CMPY_CD = 'ADB'
                          AND EZCANCELFLAG = '0'
						  AND MDSE_STRU_ELMNT_TP_CD = 'PL2'
                          AND PROD_CTRL_CD = PI.PRODUCT_LEVEL3)
                     PRODUCT_LEVEL3,
                  (SELECT PROD_CTRL_CD || '|' || PROD_CTRL_NM
                     FROM PROD_CTRL
                    WHERE     GLBL_CMPY_CD = 'ADB'
                          AND EZCANCELFLAG = '0'
						  AND MDSE_STRU_ELMNT_TP_CD = 'PL3'
                          AND PROD_CTRL_CD = PI.PRODUCT_LEVEL4)
                     PRODUCT_LEVEL4,
                  (SELECT PROD_CTRL_CD || '|' || PROD_CTRL_NM
                     FROM PROD_CTRL
                    WHERE     GLBL_CMPY_CD = 'ADB'
                          AND EZCANCELFLAG = '0'
						  AND MDSE_STRU_ELMNT_TP_CD = 'PL4'
                          AND PROD_CTRL_CD = PI.PRODUCT_LEVEL5)
                     PRODUCT_LEVEL5,
                  PI.REMAN_AVAILABLE,
                  PI.REMANUFACTURED_ITEM_EXISTS,
                  PI.RETURN_CONTROL_TYPE,
                  PI.RETURN_CONTROLLED,
                  PI.RETURN_SUB_WAREHOUSE,
                  PI.REVENUE,
                  PI.RMA_ALLOWED,
                  PI.RMA_INSPECTION_REQUIRED,
                  PI.SAFETY_HAZARDOUS_CLASS,
                  PI.SAFETY_HAZARDOUS_ID,
                  PI.SAFETY_HAZARDOUS_MATERIAL,
                  PI.SAFETY_HAZARDOUS_NUMBER,
                  PI.SAFETY_HAZARDOUS_SHIP_LABEL,
                  PI.SEATS_FROM,
                  PI.SEATS_TO,
                  PI.SERIAL_CONTROL,
                  PI.SERIAL_FROM,
                  PI.SERIAL_TO,
                  PI.SERVICE_ALLOCATION_TYPE,
                  PI.SERVICE_CALL_ENABLED,
                  PI.SERVICE_MODEL,
                  PI.SOFTWARE_CATEGORY,
                  PI.SOFTWARE_MAINTENANCE_CONTROL,
                  PI.SOFTWARE_PRODUCT_CATEGORY,
                  PI.SOFTWARE_VERSION,
                  PI.SOW_REQUIRED,
                  PI.STANDARD_COST,
                  PI.SUPPLY_COLOR,
                  PI.SUPPLY_OEM_CODE,
                  PI.SUPPLY_OEM_MANUFACTURER,
                  PI.SUPPLY_TYPE,
                  PI.SUPPLY_YIELD,
                  PI.SUPPLY_YIELD_TYPE,
                  PI.SUPPLY_YIELD_UOM,
                  PI.TAX_CODE,
                  PI.TC_OPTION,
                  PI.TC_OPTION_VALUE,
                  PI.THIRD_PARTY,
                  PI.UNIT_OF_MEASURE,
                  PI.UOM_CLASS,
                  PI.UPC_CODE,
                  PI.WARRANTY,
                  PI.WARRANTY_PERIOD,
                  NVL(PI.WEIGHT,0) WEIGHT,
                  PI.TARIFF_CODE,
                  PI.FREIGHT_CLASS_CODE,
                  PI.RETURN_WAREHOUSE,
                  PI.INTANGIBLE_MDSE_TYPE,
                  PI.PURCHASE_PRICE,
                  PI.SUPPLIER,
                  PI.SUPPLIER_ITEM,
                  PI.SUPPLIER_SITE,
                  PI.SUPERSEDED_BY,
                  PI.AREA_OF_PAPER,
                  PI.ITEM_BILLING_TYPE,
                  PI.CONFIGURATION_FLAG,
                  PI.RETURN_VENDOR,
                  PI.RETURN_VENDOR_SITE,
                  PI.HARD_ALLOCATION_TYPE,
                  PI.DEFAULT_SOURCE_TYPE,
                  PI.EASY_PACK_I,
                  PI.APPROVAL_STATUS,
                  PI.PROCESS_FLAG,
                  PI.PROCESS_MESSAGE,
                  PI.CREATED_BY,
                  PI.CREATION_DATE,
                  PI.LAST_UPDATE_BY,
                  PI.LAST_UPDATE_DATE,
                  PI.MSRP_COST,
                  NVL(PI.UNBOXED_WEIGHT,0) UNBOXED_WEIGHT,
                  NVL(PI.UNBOXED_WEIGHT_UOM,0) UNBOXED_WEIGHT_UOM,
                  NVL(PI.UNBOXED_ITEM_LENGTH,0) UNBOXED_ITEM_LENGTH,
                  NVL(PI.UNBOXED_LENGTH_UOM,0) UNBOXED_LENGTH_UOM,
                  NVL(PI.UNBOXED_WIDTH,0) UNBOXED_WIDTH,
                  NVL(PI.UNBOXED_WIDTH_UOM,0) UNBOXED_WIDTH_UOM,
                  NVL(PI.UNBOXED_HEIGHT,0) UNBOXED_HEIGHT,
                  NVL(PI.UNBOXED_HEIGHT_UOM, 0) UNBOXED_HEIGHT_UOM,
                  PI.LEAD_TIME,
                  PI.INTERNAL_ITEM
             FROM CANON_E008_PROJ_ITEMS_TBL PI, CANON_E008_TEMPLATE_HDR_TBL TH
            WHERE     PI.TEMPLATE_ID = TH.TEMPLATE_ID(+)
                  AND PROJECT_ID = DECODE (P_PROJECT_NO, 1, PROJECT_ID, P_PROJECT_NO)
                  AND EXISTS
                         (SELECT PROJECT_ID
                            FROM CANON_E008_PROJECT_WB_TBL PW
                           WHERE     PW.PROJECT_ID = PI.PROJECT_ID
                                 AND APPROVAL_STATUS = 'PENDING ITEM CREATION'
                                 AND NVL (PROCESS_FLAG, 'F') <> 'S'
                                 AND BATCH_FIRST_DATE IS NULL
                                 AND BATCH_SECOND_DATE IS NULL)
                  AND NVL (PROCESS_FLAG, 'F') <> 'S'
         ORDER BY PROJECT_ID,
                  CASE PI.ITEM_TYPE
                     WHEN '11' THEN 'B'
                     WHEN '10' THEN 'A'
                     ELSE PI.ITEM_TYPE
                  END;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_PROJECT_ITEMS_DETAILS;

   FUNCTION CHECK_CREATE_PROJECT (P_PROJECT_ID   IN NUMBER,
                                  P_ROLE_ID      IN VARCHAR2,
                                  P_USER_ID      IN VARCHAR2)
      RETURN CHAR
   IS
      L_APPROVAL_STATUS   VARCHAR2 (100);
      L_COUNT             NUMBER := 0;
   BEGIN
      IF P_PROJECT_ID IS NULL
      THEN
         RETURN 'Y';
      END IF;

      SELECT APPROVAL_STATUS
        INTO L_APPROVAL_STATUS
        FROM CANON_E008_PROJECT_WB_TBL
       WHERE PROJECT_ID = P_PROJECT_ID;

      IF L_APPROVAL_STATUS IN ('ENTERED', 'REJECTED')
      THEN
         RETURN 'Y';
      END IF;

      IF L_APPROVAL_STATUS IN ('ITEM MASTER REVIEW')
      THEN
         SELECT COUNT (1)
           INTO L_COUNT
           FROM CANON_E008_PROJ_APPRV_HIST_TBL
          WHERE     PROJECT_ID = P_PROJECT_ID
                AND APPROVAL_STATUS = 'PENDING'
                AND PROCESS_ID = (SELECT  PROCESS_ID FROM CANON_E008_PROJECT_WB_TBL WHERE PROJECT_ID = P_PROJECT_ID)
                AND EXISTS
                    ( SELECT 1
                       FROM AUTH_PSN AUTH,
                         USR_ROLE US_R,
                         ROLE R,
                         RESRC_ROLE RR,
                         PROT_RESRC PR,
                         PROT_RESRC_OBJ PRO,
                         RESRC_OBJ RO,
                         RESRC_CLS RCLS
                     WHERE 1=1
                         AND R.ACTV_FLG = 'Y'
                         AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                         AND US_R.ROLE_ID = R.ROLE_ID
                         AND R.ROLE_ID = RR.ROLE_ID
                         AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                         AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                         AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                         AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                         AND AUTH.EZCANCELFLAG = 0
                         AND US_R.EZCANCELFLAG = 0
                         AND R.EZCANCELFLAG = 0
                         AND RR.EZCANCELFLAG = 0
                         AND PR.EZCANCELFLAG = 0
                         AND RO.EZCANCELFLAG = 0
                         AND RCLS.EZCANCELFLAG = 0
                         AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                         AND r.role_desc_txt <> 'All Functions'
                         AND AUTH.USR_NM = P_USER_ID
                         --AND RO.XTRNAL_NM = APPROVER_ROLE
                         --AND RO.XTRNAL_NM IN ('E008_MERCH', 'E008_PART')
                         AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = APPROVER_ROLE
                         AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) IN ('E008_MERCH', 'E008_PART')
                     );

                     /*(SELECT 1
                          FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                         WHERE     USR_NM = P_USER_ID
                               AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                               AND UR.ROLE_ID = R.ROLE_ID
                               AND R.ROLE_NM = APPROVER_ROLE
                               AND R.ROLE_NM IN ('E008_MERCH', 'E008_PART'));*/
      END IF;

      IF L_COUNT > 0
      THEN
         RETURN 'Y';
      ELSE
         RETURN 'N';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END CHECK_CREATE_PROJECT;


   FUNCTION CHECK_MODIFY_PROJECT (P_PROJECT_ID   IN NUMBER,
                                  P_ROLE_ID      IN VARCHAR2,
                                  P_USER_ID      IN VARCHAR2)
      RETURN CHAR
   IS
      L_COUNT             NUMBER;
      L_APPROVAL_STATUS   VARCHAR2 (100);
   BEGIN
      BEGIN
         SELECT APPROVAL_STATUS
           INTO L_APPROVAL_STATUS
           FROM CANON_E008_PROJECT_WB_TBL
          WHERE PROJECT_ID = P_PROJECT_ID;

         IF L_APPROVAL_STATUS IN ('ENTERED', 'REJECTED')
         THEN
            RETURN 'Y';
         END IF;

         IF L_APPROVAL_STATUS IN ('ITEM MASTER REVIEW')
         THEN
            SELECT COUNT (1)
              INTO L_COUNT
              FROM CANON_E008_PROJ_APPRV_HIST_TBL
             WHERE     PROJECT_ID = P_PROJECT_ID
                   AND APPROVAL_STATUS = 'PENDING'
                   AND PROCESS_ID = (SELECT  PROCESS_ID FROM CANON_E008_PROJECT_WB_TBL WHERE PROJECT_ID = P_PROJECT_ID)
                   AND EXISTS
                        ( SELECT 1
                           FROM AUTH_PSN AUTH,
                             USR_ROLE US_R,
                             ROLE R,
                             RESRC_ROLE RR,
                             PROT_RESRC PR,
                             PROT_RESRC_OBJ PRO,
                             RESRC_OBJ RO,
                             RESRC_CLS RCLS
                         WHERE 1=1
                             AND R.ACTV_FLG = 'Y'
                             AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                             AND US_R.ROLE_ID = R.ROLE_ID
                             AND R.ROLE_ID = RR.ROLE_ID
                             AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                             AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                             AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                             AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                             AND AUTH.EZCANCELFLAG = 0
                             AND US_R.EZCANCELFLAG = 0
                             AND R.EZCANCELFLAG = 0
                             AND RR.EZCANCELFLAG = 0
                             AND PR.EZCANCELFLAG = 0
                             AND RO.EZCANCELFLAG = 0
                             AND RCLS.EZCANCELFLAG = 0
                             AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                             AND r.role_desc_txt <> 'All Functions'
                             AND AUTH.USR_NM = P_USER_ID
                             --AND RO.XTRNAL_NM = APPROVER_ROLE
                             --AND RO.XTRNAL_NM IN ('E008_MERCH', 'E008_PART')
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = APPROVER_ROLE
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) IN ('E008_MERCH', 'E008_PART')
                         );

                       /*   (SELECT 1
                             FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                            WHERE     USR_NM = P_USER_ID
                                  AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                  AND UR.ROLE_ID = R.ROLE_ID
                                  AND R.ROLE_NM = APPROVER_ROLE
                                  AND R.ROLE_NM IN ('E008_MERCH', 'E008_PART')); */

         ELSIF L_APPROVAL_STATUS IN ('PENDING APPROVAL')
         THEN
            SELECT COUNT (1)
              INTO L_COUNT
              FROM CANON_E008_PROJ_APPRV_HIST_TBL
             WHERE     PROJECT_ID = P_PROJECT_ID
                   AND APPROVAL_STATUS = 'PENDING'
                   AND PROCESS_ID = (SELECT  PROCESS_ID FROM CANON_E008_PROJECT_WB_TBL WHERE PROJECT_ID = P_PROJECT_ID)
                   AND EXISTS
                        ( SELECT 1
                           FROM AUTH_PSN AUTH,
                             USR_ROLE US_R,
                             ROLE R,
                             RESRC_ROLE RR,
                             PROT_RESRC PR,
                             PROT_RESRC_OBJ PRO,
                             RESRC_OBJ RO,
                             RESRC_CLS RCLS
                         WHERE 1=1
                             AND R.ACTV_FLG = 'Y'
                             AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                             AND US_R.ROLE_ID = R.ROLE_ID
                             AND R.ROLE_ID = RR.ROLE_ID
                             AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                             AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                             AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                             AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                             AND AUTH.EZCANCELFLAG = 0
                             AND US_R.EZCANCELFLAG = 0
                             AND R.EZCANCELFLAG = 0
                             AND RR.EZCANCELFLAG = 0
                             AND PR.EZCANCELFLAG = 0
                             AND RO.EZCANCELFLAG = 0
                             AND RCLS.EZCANCELFLAG = 0
                             AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                             AND r.role_desc_txt <> 'All Functions'
                             AND AUTH.USR_NM = P_USER_ID
                             --AND RO.XTRNAL_NM = APPROVER_ROLE
                             --AND RO.XTRNAL_NM NOT IN ('E008_MERCH', 'E008_PART')
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = APPROVER_ROLE
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) NOT IN ('E008_MERCH', 'E008_PART')
                         );

                        /*  (SELECT 1
                             FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                            WHERE     USR_NM = P_USER_ID
                                  AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                  AND UR.ROLE_ID = R.ROLE_ID
                                  AND R.ROLE_NM = APPROVER_ROLE
                                  AND R.ROLE_NM NOT IN ('E008_MERCH', 'E008_PART'));  */
         END IF;

         IF L_COUNT > 0
         THEN
            RETURN 'Y';
         ELSE
            RETURN 'N';
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            RETURN 'N';
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END CHECK_MODIFY_PROJECT;

   FUNCTION CHECK_VALIDATE_REJECT_PROJECT (P_PROJECT_ID   IN NUMBER,
                                           P_ROLE_ID      IN VARCHAR2,
                                           P_USER_ID      IN VARCHAR2)
      RETURN CHAR
   IS
      L_COUNT             NUMBER := 0;
      L_APPROVAL_STATUS   VARCHAR2 (50);
   BEGIN
      BEGIN
         SELECT APPROVAL_STATUS
           INTO L_APPROVAL_STATUS
           FROM CANON_E008_PROJECT_WB_TBL
          WHERE PROJECT_ID = P_PROJECT_ID;

         IF L_APPROVAL_STATUS IN ('ITEM MASTER REVIEW')
         THEN
            SELECT COUNT (1)
              INTO L_COUNT
              FROM CANON_E008_PROJ_APPRV_HIST_TBL
             WHERE     PROJECT_ID = P_PROJECT_ID
                   AND APPROVAL_STATUS = 'PENDING'
                   AND PROCESS_ID = (SELECT  PROCESS_ID FROM CANON_E008_PROJECT_WB_TBL WHERE PROJECT_ID = P_PROJECT_ID)
                   AND EXISTS
                    ( SELECT 1
                           FROM AUTH_PSN AUTH,
                             USR_ROLE US_R,
                             ROLE R,
                             RESRC_ROLE RR,
                             PROT_RESRC PR,
                             PROT_RESRC_OBJ PRO,
                             RESRC_OBJ RO,
                             RESRC_CLS RCLS
                         WHERE 1=1
                             AND R.ACTV_FLG = 'Y'
                             AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                             AND US_R.ROLE_ID = R.ROLE_ID
                             AND R.ROLE_ID = RR.ROLE_ID
                             AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                             AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                             AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                             AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                             AND AUTH.EZCANCELFLAG = 0
                             AND US_R.EZCANCELFLAG = 0
                             AND R.EZCANCELFLAG = 0
                             AND RR.EZCANCELFLAG = 0
                             AND PR.EZCANCELFLAG = 0
                             AND RO.EZCANCELFLAG = 0
                             AND RCLS.EZCANCELFLAG = 0
                             AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                             AND r.role_desc_txt <> 'All Functions'
                             AND AUTH.USR_NM = P_USER_ID
                             --AND RO.XTRNAL_NM = APPROVER_ROLE
                             --AND RO.XTRNAL_NM IN ('E008_MERCH', 'E008_PART')
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = APPROVER_ROLE
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) IN ('E008_MERCH', 'E008_PART')
                         );

                         /* (SELECT 1
                             FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                            WHERE     USR_NM = P_USER_ID
                                  AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                  AND UR.ROLE_ID = R.ROLE_ID
                                  AND R.ROLE_NM = APPROVER_ROLE
                                  AND R.ROLE_NM IN ('E008_MERCH', 'E008_PART'));  */
         ELSIF L_APPROVAL_STATUS IN ('PENDING APPROVAL')
         THEN
            SELECT COUNT (1)
              INTO L_COUNT
              FROM CANON_E008_PROJ_APPRV_HIST_TBL
             WHERE     PROJECT_ID = P_PROJECT_ID
                   AND APPROVAL_STATUS = 'PENDING'
                   AND PROCESS_ID = (SELECT  PROCESS_ID FROM CANON_E008_PROJECT_WB_TBL WHERE PROJECT_ID = P_PROJECT_ID)
                   AND EXISTS
                    ( SELECT 1
                           FROM AUTH_PSN AUTH,
                             USR_ROLE US_R,
                             ROLE R,
                             RESRC_ROLE RR,
                             PROT_RESRC PR,
                             PROT_RESRC_OBJ PRO,
                             RESRC_OBJ RO,
                             RESRC_CLS RCLS
                         WHERE 1=1
                             AND R.ACTV_FLG = 'Y'
                             AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                             AND US_R.ROLE_ID = R.ROLE_ID
                             AND R.ROLE_ID = RR.ROLE_ID
                             AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                             AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                             AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                             AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                             AND AUTH.EZCANCELFLAG = 0
                             AND US_R.EZCANCELFLAG = 0
                             AND R.EZCANCELFLAG = 0
                             AND RR.EZCANCELFLAG = 0
                             AND PR.EZCANCELFLAG = 0
                             AND RO.EZCANCELFLAG = 0
                             AND RCLS.EZCANCELFLAG = 0
                             AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                             AND r.role_desc_txt <> 'All Functions'
                             AND AUTH.USR_NM = P_USER_ID
                             --AND RO.XTRNAL_NM = APPROVER_ROLE
                             ---AND RO.XTRNAL_NM NOT IN ('E008_MERCH', 'E008_PART')
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = APPROVER_ROLE
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) NOT IN ('E008_MERCH', 'E008_PART')
                         );
                          /*(SELECT 1
                             FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                            WHERE     USR_NM = P_USER_ID
                                  AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                  AND UR.ROLE_ID = R.ROLE_ID
                                  AND R.ROLE_NM = APPROVER_ROLE
                                  AND R.ROLE_NM NOT IN ('E008_MERCH', 'E008_PART'));*/
         END IF;

         IF L_COUNT > 0
         THEN
            RETURN 'Y';
         ELSE
            RETURN 'N';
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            RETURN 'N';
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.PUT_LINE ('XXX' || SQLERRM);
         RETURN 'N';
   END CHECK_VALIDATE_REJECT_PROJECT;

   PROCEDURE DELETE_PROJECT_ITEMS (P_PROJECT_ID   IN     NUMBER,
                                   P_ITEM_ID      IN     NUMBER,
                                   P_ERR_FLG         OUT VARCHAR2,
                                   P_ERR_MSG         OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_ITEM_ID IS NOT NULL
      THEN
         DELETE FROM CANON_E008_PROJ_ITEMS_TBL
               WHERE PROJ_ITEM_ID = P_ITEM_ID AND PROJECT_ID = P_PROJECT_ID;

         DELETE FROM CANON_E008_BOM_ASSIGN_TBL
               WHERE SET_ITEM_ID = P_ITEM_ID;
      END IF;

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         ROLLBACK;
   END;

   PROCEDURE DELETE_PROJECT (P_PROJECT_ID   IN     NUMBER,
                             P_ERR_FLG         OUT VARCHAR2,
                             P_ERR_MSG         OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_PROJECT_ID IS NOT NULL
      THEN
         DELETE FROM CANON_E008_PROJ_ITEMS_TBL
               WHERE PROJECT_ID = P_PROJECT_ID;

         DELETE FROM CANON_E008_PROJECT_WB_TBL
               WHERE PROJECT_ID = P_PROJECT_ID;
      END IF;

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         ROLLBACK;
   END;

   PROCEDURE CLOSE_PROJECT (P_PROJECT_ID   IN     NUMBER,
                            P_ERR_FLG         OUT VARCHAR2,
                            P_ERR_MSG         OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      --check_pricing_assign (p_project_id, p_err_flg);

      IF P_ERR_FLG = 'E'
      THEN
         P_ERR_MSG := 'CHECK THE PRICING';
      ELSE
         P_ERR_MSG := NULL;
      END IF;
   /*
   IF p_project_id IS NOT NULL
   THEN
      UPDATE CANON_E008_PROJECT_WB_TBL
         SET APPROVAL_STATUS = 'CLOSED',
             LAST_UPDATE_DATE = SYSDATE
       WHERE PROJECT_ID = p_project_id;

   END IF;

   COMMIT;
   */

   EXCEPTION
      WHEN OTHERS
      THEN
         --DBMS_OUTPUT.put_line ('XXX'  || SQLERRM);
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         ROLLBACK;
   END;

   PROCEDURE GET_ROLE_EMAIL (P_ROLE_ID   IN     VARCHAR2,
                             P_EMAILS       OUT G_REF_CUR_TYP,
                             P_ERR_MSG      OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_ROLE_ID IN ('E008_ACCT',
                       'E008_MERCH',
                       'E008_MKT',
                       --'E008_MSTR',
                       'E008_PART',
                       'E008_PRC',
                       'E008_SALES',
                       'E008_SOL',
                       'E008_SUP',
                       'E008_SVC')
      THEN
         OPEN P_EMAILS FOR
                    SELECT  DISTINCT AUTH.EML_ADDR
                           FROM AUTH_PSN AUTH,
                             USR_ROLE US_R,
                             ROLE R,
                             RESRC_ROLE RR,
                             PROT_RESRC PR,
                             PROT_RESRC_OBJ PRO,
                             RESRC_OBJ RO,
                             RESRC_CLS RCLS
                         WHERE 1=1
                             AND R.ACTV_FLG = 'Y'
                             AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                             AND US_R.ROLE_ID = R.ROLE_ID
                             AND R.ROLE_ID = RR.ROLE_ID
                             AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                             AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                             AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                             AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                             AND AUTH.EZCANCELFLAG = 0
                             AND US_R.EZCANCELFLAG = 0
                             AND R.EZCANCELFLAG = 0
                             AND RR.EZCANCELFLAG = 0
                             AND PR.EZCANCELFLAG = 0
                             AND RO.EZCANCELFLAG = 0
                             AND RCLS.EZCANCELFLAG = 0
                             AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                             AND r.role_desc_txt <> 'All Functions'
                             --AND RO.XTRNAL_NM = P_ROLE_ID
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = P_ROLE_ID
                             AND AUTH.EML_ADDR IS NOT NULL ;
          /*
            SELECT DISTINCT EML_ADDR
              FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
             WHERE                          --    USR_NM IN ('Q07107', 'Q07281', 'Q07548')
                  1     = 1
                   AND AP.EML_ADDR IS NOT NULL
                   AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                   AND UR.ROLE_ID = R.ROLE_ID
                   AND R.ROLE_NM = P_ROLE_ID
                   AND AP.GLBL_CMPY_CD = R.GLBL_CMPY_CD
                   AND AP.EZCANCELFLAG = R.EZCANCELFLAG
                   AND AP.EZCANCELFLAG = UR.EZCANCELFLAG
                   AND AP.GLBL_CMPY_CD = 'ADB'
                   AND AP.EZCANCELFLAG = '0'; */
      ELSE
         OPEN P_EMAILS FOR
            SELECT DISTINCT EML_ADDR
              FROM CANON_E008_PROJECT_WB_TBL PWT, AUTH_PSN AP
             WHERE     PWT.PROJECT_ID = P_ROLE_ID
                   AND PWT.PROJECT_REQUESTOR = AP.USR_NM
                   AND AP.EML_ADDR IS NOT NULL;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         -- p_emails := 'E';
         P_ERR_MSG := L_ERR_MSG;
         ROLLBACK;
   END;

   FUNCTION CHECK_DELETE_PROJECT (P_PROJECT_ID   IN NUMBER,
                                  P_ROLE_ID      IN VARCHAR2,
                                  P_USER_ID      IN VARCHAR2)
      RETURN CHAR
   IS
      L_APPROVAL_STATUS   VARCHAR2 (100);
      L_COUNT             NUMBER;
   BEGIN
      SELECT APPROVAL_STATUS
        INTO L_APPROVAL_STATUS
        FROM CANON_E008_PROJECT_WB_TBL
       WHERE PROJECT_ID = P_PROJECT_ID;

      IF L_APPROVAL_STATUS IN ('ENTERED', 'REJECTED')
      THEN
         RETURN 'Y';
      END IF;

      IF L_APPROVAL_STATUS IN ('ITEM MASTER REVIEW')
      THEN
         SELECT COUNT (1)
           INTO L_COUNT
           FROM CANON_E008_PROJ_APPRV_HIST_TBL
          WHERE     PROJECT_ID = P_PROJECT_ID
                AND APPROVAL_STATUS = 'PENDING'
                AND EXISTS
                 ( SELECT 1
                           FROM AUTH_PSN AUTH,
                             USR_ROLE US_R,
                             ROLE R,
                             RESRC_ROLE RR,
                             PROT_RESRC PR,
                             PROT_RESRC_OBJ PRO,
                             RESRC_OBJ RO,
                             RESRC_CLS RCLS
                         WHERE 1=1
                             AND R.ACTV_FLG = 'Y'
                             AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                             AND US_R.ROLE_ID = R.ROLE_ID
                             AND R.ROLE_ID = RR.ROLE_ID
                             AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                             AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                             AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                             AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                             AND AUTH.EZCANCELFLAG = 0
                             AND US_R.EZCANCELFLAG = 0
                             AND R.EZCANCELFLAG = 0
                             AND RR.EZCANCELFLAG = 0
                             AND PR.EZCANCELFLAG = 0
                             AND RO.EZCANCELFLAG = 0
                             AND RCLS.EZCANCELFLAG = 0
                             AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                             AND r.role_desc_txt <> 'All Functions'
                             AND AUTH.USR_NM = P_USER_ID
                             --AND RO.XTRNAL_NM = APPROVER_ROLE
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = APPROVER_ROLE
                             --AND RO.XTRNAL_NM IN ('E008_MERCH', 'E008_PART')
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) IN ('E008_MERCH', 'E008_PART')
                         );

                       /*(SELECT 1
                          FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                         WHERE     USR_NM = P_USER_ID
                               AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                               AND UR.ROLE_ID = R.ROLE_ID
                               AND R.ROLE_NM = APPROVER_ROLE
                               AND R.ROLE_NM IN ('E008_MERCH', 'E008_PART'));  */
      END IF;

      IF L_COUNT > 0
      THEN
         RETURN 'Y';
      ELSE
         RETURN 'N';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.PUT_LINE ('XXX' || SQLERRM);
         RETURN 'N';
   END CHECK_DELETE_PROJECT;

   FUNCTION CHECK_SUBMIT_APPROVAL_PROJECT (P_PROJECT_ID   IN NUMBER,
                                           P_ROLE_ID      IN VARCHAR2,
                                           P_USER_ID      IN VARCHAR2)
      RETURN CHAR
   IS
      L_CHECK        VARCHAR2 (3) := 0;
      L_COUNT        NUMBER;
      L_HIST_COUNT   NUMBER;
      L_ITEM_TYPE    VARCHAR2 (10) := 'WFE590IP';
   BEGIN
      BEGIN
         SELECT COUNT (1)
           INTO L_COUNT
           FROM CANON_E008_PROJECT_WB_TBL
          WHERE     PROJECT_ID = P_PROJECT_ID
                AND UPPER (APPROVAL_STATUS) IN ('ENTERED', 'REJECTED')
                AND EXISTS
                       (SELECT 1
                          FROM CANON_E008_PROJ_ITEMS_TBL
                         WHERE PROJECT_ID = P_PROJECT_ID);

         IF L_COUNT = 1
         THEN
            RETURN 'Y';
         ELSE
            RETURN 'N';
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            RETURN 'N';
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.PUT_LINE ('XXX' || SQLERRM);
         RETURN 'N';
   END CHECK_SUBMIT_APPROVAL_PROJECT;


   FUNCTION CHECK_VIEW_PROJECT (P_PROJECT_ID   IN NUMBER,
                                P_ROLE_ID      IN VARCHAR2,
                                P_USER_ID      IN VARCHAR2)
      RETURN CHAR
   IS
      L_CHECK   VARCHAR2 (3);
      L_COUNT   NUMBER;
   BEGIN
      RETURN 'Y';
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.PUT_LINE ('XXX' || SQLERRM);
         RETURN 'N';
   END CHECK_VIEW_PROJECT;


   PROCEDURE INSERT_ERROR_PROJ_TBL (P_PROJECT_ID   IN NUMBER,
                                    P_USER_ID      IN VARCHAR2,
                                    P_ITEM_ID      IN VARCHAR2,
                                    P_FIELD        IN VARCHAR2,
                                    P_ERR_MSG      IN VARCHAR2)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
      L_ERRBUF    VARCHAR2 (200);
      L_RETCODE   VARCHAR2 (200);
   BEGIN
      INSERT INTO CANON_E008_PROJECT_ERR_TBL
           VALUES (P_PROJECT_ID,
                   P_USER_ID,
                   P_ITEM_ID,
                   P_FIELD,
                   P_ERR_MSG,
                   P_USER_ID,
                   SYSDATE,
                   P_USER_ID,
                   SYSDATE);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         INSERT_ERROR ('INSERT_ERROR_PROJ_TBL INSERT',
                       SUBSTR (SQLERRM, 1, 400),
                       L_ERRBUF,
                       L_RETCODE);
   END INSERT_ERROR_PROJ_TBL;

   ----------------------------------------------------------------
   -- Procedure for insertion of records into CANON ERROR Table
   ----------------------------------------------------------------

   PROCEDURE INSERT_ERROR (P_PROCESS     IN     VARCHAR2,
                           P_ERROR_MSG   IN     VARCHAR2,
                           ERRBUF           OUT VARCHAR2,
                           RETCODE          OUT VARCHAR2)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO CANON_E008_ERRORS (PROGRAM_NAME,
                                     PROCESS_NAME,
                                     ERROR_MSG,
                                     ERROR_DATE)
           VALUES ('CANON_E008_ITEM_WORKBENCH_PKG',
                   P_PROCESS,
                   P_ERROR_MSG,
                   SYSDATE);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         ROLLBACK;
   END;


   FUNCTION CHECK_STATUS_MONITOR (P_PROJECT_ID IN NUMBER)
      RETURN VARCHAR2
   IS
      L_COUNT   NUMBER;
   BEGIN
      RETURN 'Y';
   /*   SELECT COUNT (1)
        INTO L_COUNT
        FROM CANON_E008_PROJ_APPRV_HIST_TBL
       WHERE PROJECT_ID = P_PROJECT_ID;

      /* AND item_key = (SELECT attribute1
                         FROM CANON_E008_PROJECT_WB_TBL
                        WHERE project_id = p_project_id);  */

      --IF l_count = 4 THEN  commnted by the New enhancement  */
     /* IF L_COUNT = 5
      THEN
         RETURN 'Y';
      ELSE
         RETURN 'Y';
      END IF;  */
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'Y';
   END CHECK_STATUS_MONITOR;

   PROCEDURE GET_PROJECT_HISTORY (P_PROJECT_NO     IN     NUMBER,
                                  P_PROJECT_HIST      OUT G_REF_CUR_TYP,
                                  P_ERR_FLG           OUT VARCHAR2,
                                  P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG    VARCHAR2 (2000);
      L_INDEX      NUMBER := 1;
      L_FND_USER   VARCHAR2 (240);
   BEGIN
      OPEN P_PROJECT_HIST FOR
           SELECT PWB.PROJECT_ID,
                  PRH.APPROVING_DEPARTMENT,
                  PRH.APPROVER_ROLE,
                  REPLACE(FIRST_NM || ' ' || LAST_NM || ' ('||PRH.APPROVER_NAME || ')','()','') APPROVER_NAME,
                  PRH.APPROVAL_STATUS,
                  PRH.APPROVER_COMMENTS,
                  DECODE (PRH.APPROVAL_STATUS,
                          'APPROVED', ROUND (PRH.LAST_UPDATE_DATE - PRH.CREATION_DATE),
                          'PENDING', ROUND (SYSDATE - PRH.CREATION_DATE),
                          NULL)
                     AGING_DAYS,
                  PRH.CREATED_BY,
                  PRH.CREATION_DATE,
                  PRH.LAST_UPDATE_BY,
                  PRH.LAST_UPDATE_DATE
             FROM CANON_E008_PROJ_APPRV_HIST_TBL PRH, CANON_E008_PROJECT_WB_TBL PWB, AUTH_PSN AP
            WHERE     1 = 1
                  AND PWB.PROJECT_ID = PRH.PROJECT_ID
                  AND PWB.PROCESS_ID = PRH.PROCESS_ID
                  AND PWB.PROJECT_ID = P_PROJECT_NO
                  AND AP.USR_NM(+) = PRH.APPROVER_NAME
                  AND AP.GLBL_CMPY_CD(+) = 'ADB'
         ORDER BY PRH.APPROVING_DEPARTMENT;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END;

   PROCEDURE APPREJ_ACTION (P_PROJECT_ID   IN     VARCHAR2,
                            P_USER_ID      IN     VARCHAR2,
                            P_COMMENTS     IN     VARCHAR2,
                            P_ROLE_ID      IN     VARCHAR2,
                            --p_respid       IN     NUMBER,
                            P_ACTION       IN     VARCHAR2,
                            ERRBUF            OUT VARCHAR2,
                            RETCODE           OUT VARCHAR2)
   IS
      L_PROCESS_ID       VARCHAR2 (100);
      L_PROGRESS         VARCHAR2 (100);
      L_PROJECT_STATUS   VARCHAR2 (100);
      L_ERRBUF           VARCHAR2 (200);
      L_RETCODE          VARCHAR2 (200);
   BEGIN
      BEGIN
         SELECT PROCESS_ID, APPROVAL_STATUS
           INTO L_PROCESS_ID, L_PROJECT_STATUS
           FROM CANON_E008_PROJECT_WB_TBL
          WHERE PROJECT_ID = P_PROJECT_ID;
      END;

      INSERT_ERROR ('APPREJ_ACTION',
                    'BEFORE ',
                    L_ERRBUF,
                    L_RETCODE);
      UPDATE_HISTORY (L_PROCESS_ID,
                      P_PROJECT_ID,
                      L_PROJECT_STATUS,
                      P_USER_ID,
                      P_ACTION,
                      P_COMMENTS,
                      L_PROGRESS);
      INSERT_ERROR ('APPREJ_ACTION',
                    'AFTER ',
                    L_ERRBUF,
                    L_RETCODE);
      ERRBUF := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         ERRBUF := 'E';
         INSERT_ERROR ('APPREJ_ACTION',
                       'EXCEPTION' || '-' || SQLERRM,
                       ERRBUF,
                       RETCODE);
   END APPREJ_ACTION;


   PROCEDURE UPDATE_HISTORY (PROCESS_ID           VARCHAR2,
                             PROJECT_ID           VARCHAR2,
                             PROJECT_STATUS       VARCHAR2,
                             APPROVER_NAME        VARCHAR2,
                             APPROVER_STATUS      VARCHAR2,
                             APPROVER_COMMENTS    VARCHAR2,
                             X_PROGRESS           VARCHAR2)
   IS
      L_PROJECT_ID          VARCHAR2 (200);
      L_PROCESS_ID          VARCHAR2 (200);
      L_ERRBUF              VARCHAR2 (200);
      L_RETCODE             VARCHAR2 (200);
      L_APPROVER_NAME       VARCHAR2 (200);
      L_APPROVER_COMMENTS   VARCHAR2 (2000);
      L_APP_COUNT           NUMBER;
   BEGIN
      BEGIN
         L_APPROVER_NAME := APPROVER_NAME;
         L_APPROVER_COMMENTS := APPROVER_COMMENTS;
         L_PROJECT_ID := PROJECT_ID;
         L_PROCESS_ID := PROCESS_ID;

         IF PROJECT_STATUS = 'ITEM MASTER REVIEW'
         THEN
            INSERT_ERROR (
               'UPDATE_HISTORY',
                  '0.1 :'
               || APPROVER_NAME
               || ':'
               || PROCESS_ID
               || ':'
               || L_PROJECT_ID
               || ':'
               || PROJECT_STATUS,
               L_ERRBUF,
               L_RETCODE);

            UPDATE CANON_E008_PROJ_APPRV_HIST_TBL
               SET APPROVER_NAME = NVL (TRIM (L_APPROVER_NAME), ' '),
                   APPROVAL_STATUS = APPROVER_STATUS,
                   APPROVER_COMMENTS = NVL (TRIM (L_APPROVER_COMMENTS), ' '),
                   LAST_UPDATE_DATE = SYSDATE
             WHERE     PROCESS_ID = L_PROCESS_ID
                   AND PROJECT_ID = L_PROJECT_ID
                   AND APPROVER_ROLE IN ('E008_MERCH', 'E008_PART');
         END IF;

         IF PROJECT_STATUS = 'PENDING APPROVAL'
         THEN
            INSERT_ERROR (
               'UPDATE_HISTORY',
                  '1.1 :'
               || APPROVER_NAME
               || ':'
               || PROCESS_ID
               || ':'
               || L_PROJECT_ID
               || ':'
               || PROJECT_STATUS,
               L_ERRBUF,
               L_RETCODE);

            UPDATE CANON_E008_PROJ_APPRV_HIST_TBL
               SET APPROVER_NAME = NVL (TRIM (L_APPROVER_NAME), ' '),
                   APPROVAL_STATUS = APPROVER_STATUS,
                   APPROVER_COMMENTS = NVL (TRIM (L_APPROVER_COMMENTS), ' '),
                   LAST_UPDATE_DATE = SYSDATE
             WHERE     PROCESS_ID = L_PROCESS_ID
                   AND PROJECT_ID = L_PROJECT_ID
                   AND APPROVAL_STATUS = 'PENDING'
                   AND APPROVER_ROLE IN
                       (SELECT TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) --RO.XTRNAL_NM
                           FROM AUTH_PSN AUTH,
                             USR_ROLE US_R,
                             ROLE R,
                             RESRC_ROLE RR,
                             PROT_RESRC PR,
                             PROT_RESRC_OBJ PRO,
                             RESRC_OBJ RO,
                             RESRC_CLS RCLS
                         WHERE 1=1
                             AND R.ACTV_FLG = 'Y'
                             AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                             AND US_R.ROLE_ID = R.ROLE_ID
                             AND R.ROLE_ID = RR.ROLE_ID
                             AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                             AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                             AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                             AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                             AND AUTH.EZCANCELFLAG = 0
                             AND US_R.EZCANCELFLAG = 0
                             AND R.EZCANCELFLAG = 0
                             AND RR.EZCANCELFLAG = 0
                             AND PR.EZCANCELFLAG = 0
                             AND RO.EZCANCELFLAG = 0
                             AND RCLS.EZCANCELFLAG = 0
                             AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                             AND r.role_desc_txt <> 'All Functions'
                             AND AUTH.USR_NM = TRIM (L_APPROVER_NAME)
                             --AND RO.XTRNAL_NM LIKE 'E008%'
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles',''))  LIKE 'E008%'
                             --AND RO.XTRNAL_NM NOT IN ('E008_MERCH', 'E008_PART'));
                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) NOT IN ('E008_MERCH', 'E008_PART'));


                      /*    (SELECT DISTINCT R.ROLE_NM
                             FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                            WHERE     USR_NM = TRIM (L_APPROVER_NAME)
                                  AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                  AND UR.ROLE_ID = R.ROLE_ID
                                  AND R.ROLE_NM LIKE 'E008%'
                                  AND R.ROLE_NM NOT IN ('E008_MERCH', 'E008_PART'));*/

            COMMIT;
         END IF;


         INSERT_ERROR (
            'UPDATE_HISTORY',
               '1 :'
            || APPROVER_NAME
            || ':'
            || PROCESS_ID
            || ':'
            || L_PROJECT_ID
            || ':'
            || PROJECT_STATUS,
            L_ERRBUF,
            L_RETCODE);
      EXCEPTION
         WHEN OTHERS
         THEN
            INSERT_ERROR ('UPDATE_HISTORY',
                          'Exception' || '-' || SQLERRM,
                          L_ERRBUF,
                          L_RETCODE);
      END;

      INSERT_ERROR ('UPDATE_HISTORY',
                    '2 ' || APPROVER_STATUS,
                    L_ERRBUF,
                    L_RETCODE);

      IF APPROVER_STATUS = 'APPROVED'
      THEN
         INSERT_ERROR ('UPDATE_HISTORY',
                       '3 ' || APPROVER_STATUS,
                       L_ERRBUF,
                       L_RETCODE);

         BEGIN
            SELECT COUNT (1)
              INTO L_APP_COUNT
              FROM CANON_E008_PROJ_APPRV_HIST_TBL
             WHERE     PROCESS_ID = L_PROCESS_ID
                   AND PROJECT_ID = L_PROJECT_ID --AND APPROVING_DEPARTMENT <> 'ITEM MASTER'
                   AND APPROVAL_STATUS = 'PENDING';
         EXCEPTION
            WHEN OTHERS
            THEN
               L_APP_COUNT := NULL;
         END;


         IF PROJECT_STATUS = 'ITEM MASTER REVIEW'
         THEN
            INSERT_ERROR ('UPDATE_HISTORY',
                          '4 ' || PROJECT_STATUS || 'l_project_id ' || L_PROJECT_ID,
                          L_ERRBUF,
                          L_RETCODE);

            IF L_APP_COUNT = 0
            THEN
               UPDATE CANON_E008_PROJECT_WB_TBL
                  SET APPROVAL_STATUS = 'PENDING ITEM CREATION',
                      LAST_UPDATE_DATE = SYSDATE
                WHERE PROJECT_ID = L_PROJECT_ID;
            ELSE
               BEGIN
                  UPDATE CANON_E008_PROJECT_WB_TBL
                     SET APPROVAL_STATUS = 'PENDING APPROVAL', LAST_UPDATE_DATE = SYSDATE
                   WHERE PROJECT_ID = L_PROJECT_ID;
               END;
            END IF;
         ELSE
            INSERT_ERROR ('UPDATE_HISTORY',
                          '5 ' || PROJECT_STATUS,
                          L_ERRBUF,
                          L_RETCODE);

            IF L_APP_COUNT = 0
            THEN
               UPDATE CANON_E008_PROJECT_WB_TBL
                  SET APPROVAL_STATUS = 'PENDING ITEM CREATION',
                      LAST_UPDATE_DATE = SYSDATE
                WHERE PROJECT_ID = L_PROJECT_ID;
            END IF;
         END IF;
      ELSIF APPROVER_STATUS = 'REJECTED'
      THEN
         UPDATE CANON_E008_PROJECT_WB_TBL
            SET APPROVAL_STATUS = 'REJECTED', LAST_UPDATE_DATE = SYSDATE
          WHERE PROJECT_ID = L_PROJECT_ID AND PROCESS_ID = L_PROCESS_ID;
      END IF;

      INSERT_ERROR ('UPDATE_HISTORY',
                    '6 COMPLETED',
                    L_ERRBUF,
                    L_RETCODE);
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         INSERT_ERROR ('UPDATE_HISTORY',
                       SQLERRM,
                       L_ERRBUF,
                       L_RETCODE);
         ROLLBACK;
   END UPDATE_HISTORY;

   -- same as above Get_Project_History but shows the data from end dated wf items

   PROCEDURE GET_APPR_HIST (P_PROJECT_NO     IN     NUMBER,
                            P_PROJECT_HIST      OUT G_REF_CUR_TYP,
                            P_ERR_FLG           OUT VARCHAR2,
                            P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG    VARCHAR2 (2000);
      L_INDEX      NUMBER := 1;
      L_FND_USER   VARCHAR2 (240);
   BEGIN
      OPEN P_PROJECT_HIST FOR
           SELECT PWB.PROJECT_ID,
                  PRH.APPROVING_DEPARTMENT,
                  PRH.APPROVER_ROLE,
                  PRH.APPROVER_NAME,
                  PRH.APPROVAL_STATUS,
                  PRH.APPROVER_COMMENTS,
                  DECODE (PRH.APPROVAL_STATUS,
                          'APPROVED', ROUND (PRH.LAST_UPDATE_DATE - PRH.CREATION_DATE),
                          'PENDING', ROUND (SYSDATE - PRH.CREATION_DATE),
                          NULL)
                     AGING_DAYS,
                  PRH.CREATED_BY,
                  PRH.CREATION_DATE,
                  PRH.LAST_UPDATE_BY,
                  PRH.LAST_UPDATE_DATE
             FROM CANON_E008_PROJ_APPRV_HIST_TBL PRH, CANON_E008_PROJECT_WB_TBL PWB
            WHERE     1 = 1
                  AND PWB.PROJECT_ID = PRH.PROJECT_ID --AND pwb.process_id = prh.process_id
                  AND PWB.PROJECT_ID = P_PROJECT_NO
                  AND PWB.PROCESS_ID != PRH.PROCESS_ID
         ORDER BY PRH.CREATION_DATE DESC, PRH.ROWID;               --APPROVING_DEPARTMENT;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_APPR_HIST;

   PROCEDURE VALIDATE_PROJECT (P_PROJECT_NO      IN     NUMBER,
                               P_ROLE_ID         IN     VARCHAR2,
                               P_USER_ID         IN     VARCHAR2,
                               P_PROJECT_ERROR      OUT G_REF_CUR_TYP,
                               P_ERR_FLG            OUT VARCHAR2,
                               P_ERR_MSG            OUT VARCHAR2)
   IS
      CURSOR CUR_PROJ_ITEMS
      IS
         SELECT *
           FROM CANON_E008_PROJ_ITEMS_TBL
          WHERE PROJECT_ID = P_PROJECT_NO;

      CURSOR CUR_REQ_COLUMNS (
         C_TEMPLATE_ID    NUMBER)
      IS
           SELECT ATC.COLUMN_NAME MAP_COL,
                  TAT.ATTRIBUTE_NAME,
                  TAA.REQUIRED_VALUE,
                  TAA.VALID,
                  TAA.DEFAULT_VALUE,
                  TAT.WORKBENCH_DISPLAY,
                  TAT.LOV_FLAG,
                  TAT.S21_MAP_COL_SIZE
             FROM ALL_TAB_COLS ATC,
                  CANON_E008_TEMPL_ATTR_ASGN_TBL TAA,
                  CANON_E008_TEMPLATE_ATTR_TBL TAT
            WHERE     TABLE_NAME = 'CANON_E008_PROJ_ITEMS_TBL'
                  AND ATC.COLUMN_NAME = TAT.E008_WB_TBL_MAP
                  AND TAA.TEMPLATE_ID = C_TEMPLATE_ID
                  AND TAT.ATTRIBUTE_ID = TAA.ATTRIBUTE_ID
                  --AND WORKBENCH_DISPLAY IN ('ADDITIONAL', 'MAIN')
                  AND TAT.ENABLE_FLAG = 'Y'
         --AND (REQUIRED_VALUE = 'YES' OR VALID = 'YES')
         ORDER BY COLUMN_NAME;

      L_ERR_MSG        VARCHAR2 (2000);
      L_PROJECT_TYPE   CANON_E008_PROJECT_WB_TBL.PROJECT_TYPE%TYPE;
      L_DYN_SQL        VARCHAR2 (32000);
      L_COL_VALUE      VARCHAR2 (2000);
      L_COUNT          VARCHAR2 (30);
      L_PI_CNT         VARCHAR2 (30);
      L_PR_CNT         VARCHAR2 (30);
      L_ITEM           MDSE.MDSE_CD%TYPE;
      L_PRJ_ITEM_ID    NUMBER := -1;
      L_ROW            NUMBER := 0;
      L_COL_MAIN       NUMBER := 0;
      L_COL_ADDL       NUMBER := 0;
      L_PRCL_CNT       NUMBER := 0;
      L_STR            VARCHAR2 (200);
      L_ERRBUF         VARCHAR2 (200);
      L_RETCODE        VARCHAR2 (200);
      l_valid_item     VARCHAR2 (2);
      l_item_type      VARCHAR2 (40);
      l_check_merch_item  NUMBER;
      l_MERCURY_INCLUDE  VARCHAR2 (2);
      L_ASL_CNT         VARCHAR2 (20);

      l_ATTRIBUTE_NAME  VARCHAR2 (200);
   BEGIN
      DELETE FROM CANON_E008_PROJECT_ERR_TBL
            WHERE PROJECT_ID = P_PROJECT_NO AND USER_ID = P_USER_ID;

      /*
            -- Delete all test projects data

            DELETE FROM CANON_E008_PROJECT_WB_TBL
                  WHERE PROJECT_ID IN (1019,1020);

            DELETE FROM CANON_E008_PROJ_ITEMS_TBL
                  WHERE PROJECT_ID IN (1019,1020);

            DELETE FROM CANON_E008_PROJ_APPRV_HIST_TBL
                  WHERE PROJECT_ID IN (1019,1020);

            DELETE FROM CANON_E008_ERRORS
                  WHERE ERROR_DATE < '25-AUG-2016';

            -- Delete all test projects data
      */
      COMMIT;

      FOR REC_ITEMS IN CUR_PROJ_ITEMS
      LOOP
         L_ITEM := REC_ITEMS.ITEM_NUMBER;
         l_MERCURY_INCLUDE :=  REC_ITEMS.MERCURY_INCLUDE;





         FOR REQ_COL IN CUR_REQ_COLUMNS (REC_ITEMS.TEMPLATE_ID)
         LOOP
            BEGIN
               L_DYN_SQL :=
                     ' SELECT '
                  || REQ_COL.MAP_COL
                  || ' FROM CANON_E008_PROJ_ITEMS_TBL WHERE PROJECT_ID = '
                  || P_PROJECT_NO
                  || ' AND PROJ_ITEM_ID = '
                  || REC_ITEMS.PROJ_ITEM_ID;

               --DBMS_OUTPUT.PUT_LINE ('SQL: ' || L_DYN_SQL);

               EXECUTE IMMEDIATE L_DYN_SQL INTO L_COL_VALUE;
            EXCEPTION
               WHEN OTHERS
               THEN
                  INSERT_ERROR ('VALIDATE_PROJECT-DYN SELECT',
                                SUBSTR (SQLERRM, 1, 400),
                                L_ERRBUF,
                                L_RETCODE);
            END;

            --DBMS_OUTPUT.PUT_LINE (REQ_COL.MAP_COL || ': ' || L_COL_VALUE);

            IF REQ_COL.REQUIRED_VALUE = 'YES' AND L_COL_VALUE IS NULL
            THEN
               P_ERR_FLG := 'S';
               P_ERR_MSG :=
                     'Canon Item#->'
                  || L_ITEM
                  || ' - ['
                  || REQ_COL.ATTRIBUTE_NAME
                  || '] is Required.';
               INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                      P_USER_ID,
                                      REC_ITEMS.PROJ_ITEM_ID,
                                      REQ_COL.ATTRIBUTE_NAME,
                                      P_ERR_MSG);
            ELSIF L_COL_VALUE IS NOT NULL                      --AND REQ_COL.VALID = 'YES'
            THEN

               IF REQ_COL.MAP_COL = 'ITEM_NUMBER'
               THEN


                    BEGIN
                         SELECT  UPPER (MDSE_ITEM_TP_NM)
                            INTO l_item_type
                           FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                                CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                                CANON_E008_TEMPLATE_HDR_TBL TH,
                                MDSE_ITEM_TP MIT
                          WHERE     1 = 1
                                AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                                AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                                AND TAT.ATTRIBUTE_NAME = 'Item Type'
                                AND TH.TEMPLATE_ID = REC_ITEMS.TEMPLATE_ID
                                AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                                AND TAT.ENABLE_FLAG = 'Y'
                                AND MIT.MDSE_ITEM_TP_CD = TAAT.default_value;

                        --IF l_item_type <> 'PARTS' AND l_MERCURY_INCLUDE = 'Y' THEN
                        IF l_MERCURY_INCLUDE = 'Y' THEN

                            select count(1)
                            into l_check_merch_item
                            from AMER_CMPY
                            where amer_cmpy_cd = 'ADB'
                            and   amer_xpnd_tp_cd = '1'
                            and   amer_mdse_cd = L_ITEM;

                            IF l_check_merch_item > 0 THEN
                               l_valid_item := 'Y';
                            ELSE
                               l_valid_item := 'N';

                                P_ERR_FLG := 'S';
                                 P_ERR_MSG :=
                                       'Canon Item#->'
                                    || L_ITEM
                                    || ' - ['
                                    || 'Should exist in Mercury and Should be Disclosed to CSA.'
                                    || ']. ';
                                 INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                                        P_USER_ID,
                                                        REC_ITEMS.PROJ_ITEM_ID,
                                                        REQ_COL.ATTRIBUTE_NAME,
                                                        P_ERR_MSG);

                            END IF;
                        END IF;

                         IF l_MERCURY_INCLUDE = 'N' THEN
                            --l_valid_item := 'Y';
                            select count(1)
                            into l_check_merch_item
                            from AMER_MSTR
                            where (amer_mdse_cd LIKE substr(L_ITEM, 1,8)||'%'
                                OR amer_mdse_cd = L_ITEM)
                                and AMER_NEW_OLD_TP_CD <> '2';

                            IF l_check_merch_item > 0 THEN
                               l_valid_item := 'N';

                                P_ERR_FLG := 'S';
                                P_ERR_MSG :=
                                       'Canon Item#->'
                                    || L_ITEM
                                    || ' - ['
                                    || 'Should not be Mercury Style Item Code.'
                                    || ']. ';
                                 INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                                        P_USER_ID,
                                                        REC_ITEMS.PROJ_ITEM_ID,
                                                        REQ_COL.ATTRIBUTE_NAME,
                                                        P_ERR_MSG);
                            ELSE
                               l_valid_item := 'Y';
                            END IF;

                        END IF;

                     EXCEPTION
                        WHEN OTHERS THEN
                         l_valid_item := 'N';

                         P_ERR_FLG := 'S';
                                 P_ERR_MSG :=
                                       'Canon Item#->'
                                    || L_ITEM
                                    || ' - ['
                                    || ' Should exist in Mercury and Should be Disclosed to CSA.'
                                    || ']. ';
                                 INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                                        P_USER_ID,
                                                        REC_ITEMS.PROJ_ITEM_ID,
                                                        REQ_COL.ATTRIBUTE_NAME,
                                                        P_ERR_MSG);

                     END;


                  BEGIN
                     SELECT COUNT (MDSE_CD)
                       INTO L_COUNT
                       FROM MDSE
                      WHERE MDSE_CD = L_COL_VALUE AND GLBL_CMPY_CD = 'ADB';

                     SELECT COUNT (ITEM_NUMBER)
                       INTO L_PI_CNT
                       FROM CANON_E008_PROJ_ITEMS_TBL
                      WHERE ITEM_NUMBER = L_COL_VALUE AND PROJECT_ID = P_PROJECT_NO;

                     SELECT COUNT (ITEM_NUMBER)
                       INTO L_PR_CNT
                       FROM CANON_E008_PROJ_ITEMS_TBL
                      WHERE ITEM_NUMBER = L_COL_VALUE
                        AND nvl(PROCESS_FLAG,'x') <> 'F';


                     IF L_COUNT > 0
                     THEN
                        P_ERR_FLG := 'S';
                        P_ERR_MSG :=
                           'Canon Item#->' || L_COL_VALUE || ' - Already exist in S21.';
                        INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                               P_USER_ID,
                                               REC_ITEMS.PROJ_ITEM_ID,
                                               REQ_COL.ATTRIBUTE_NAME,
                                               P_ERR_MSG);
                     END IF;
                     IF L_PI_CNT > 1
                     THEN
                        P_ERR_FLG := 'S';
                        P_ERR_MSG :=
                              'Canon Item#->'
                           || L_COL_VALUE
                           || ' - Duplicated in the project.';
                        INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                               P_USER_ID,
                                               REC_ITEMS.PROJ_ITEM_ID,
                                               REQ_COL.ATTRIBUTE_NAME,
                                               P_ERR_MSG);
                     END IF;
                     /*     ELSIF LENGTH (L_COL_VALUE) NOT IN (8, 10)
                          THEN
                             P_ERR_FLG := 'S';
                             P_ERR_MSG :=
                                   'Canon Item#->'
                                || L_COL_VALUE
                                || ' - [Item Number] Length should be either 8 or 10 digits.';
                             INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                                    P_USER_ID,
                                                    REC_ITEMS.PROJ_ITEM_ID,
                                                    REQ_COL.ATTRIBUTE_NAME,
                                                    P_ERR_MSG);  */
                     IF L_PR_CNT > 1
                     THEN
                        P_ERR_FLG := 'S';
                        P_ERR_MSG :=
                              'Canon Item#->'
                           || L_COL_VALUE
                           || ' -  Already exist in the other project.';
                        INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                               P_USER_ID,
                                               REC_ITEMS.PROJ_ITEM_ID,
                                               REQ_COL.ATTRIBUTE_NAME,
                                               P_ERR_MSG);
                     END IF;
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        L_COUNT := 0;
                  END;
               ELSIF REQ_COL.MAP_COL = 'PRODUCT_LEVEL4'
               THEN
                  SELECT COUNT (PC.PROD_CTRL_CD)
                    INTO L_COUNT
                    FROM PROD_CTRL PC
                   WHERE     PC.EZCANCELFLAG = '0'
                         AND PC.GLBL_CMPY_CD = 'ADB'
                         AND PC.MDSE_STRU_ELMNT_TP_CD = 'PL3'
                         AND PC.PROD_CTRL_CD = L_COL_VALUE
                         AND PC.SCD_PROD_HRCH_CD IN
                                (SELECT PC1.PROD_CTRL_CD
                                   FROM PROD_CTRL PC1
                                  WHERE     PC1.PROD_CTRL_CD = REC_ITEMS.PRODUCT_LEVEL3
                                        AND PC1.EZCANCELFLAG = PC.EZCANCELFLAG
                                        AND PC1.GLBL_CMPY_CD = PC.GLBL_CMPY_CD
										AND PC1.MDSE_STRU_ELMNT_TP_CD = 'PL2');

                  IF L_COUNT = 0
                  THEN
                     P_ERR_FLG := 'S';
                     P_ERR_MSG :=
                           'Canon Item#->'
                        || L_ITEM
                        || ' - [Product Level4] is incompatible with [Product Level3].';
                     INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                            P_USER_ID,
                                            REC_ITEMS.PROJ_ITEM_ID,
                                            REQ_COL.ATTRIBUTE_NAME,
                                            P_ERR_MSG);
                  END IF;
               ELSIF REQ_COL.MAP_COL = 'SUPPLIER_SITE'
               THEN
                  SELECT COUNT (DV.INV_VND_CD)
                    INTO L_COUNT
                    FROM VND DV
                   WHERE     DV.EZCANCELFLAG = '0'
                         AND DV.GLBL_CMPY_CD = 'ADB'
                         AND DV.INV_VND_CD = L_COL_VALUE
                         AND DV.PRNT_VND_PK IN
                                (SELECT PV.PRNT_VND_PK
                                   FROM PRNT_VND PV
                                  WHERE     PV.PRNT_VND_CD = REC_ITEMS.SUPPLIER
                                        AND PV.EZCANCELFLAG = DV.EZCANCELFLAG
                                        AND PV.GLBL_CMPY_CD = DV.GLBL_CMPY_CD);

                  IF L_COUNT = 0
                  THEN
                     P_ERR_FLG := 'S';
                     P_ERR_MSG :=
                           'Canon Item#->'
                        || L_ITEM
                        || ' - [Supplier Site] does not exist for [Supplier].';
                     INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                            P_USER_ID,
                                            REC_ITEMS.PROJ_ITEM_ID,
                                            REQ_COL.ATTRIBUTE_NAME,
                                            P_ERR_MSG);
                  END IF;
               ELSIF REQ_COL.MAP_COL = 'UNIT_OF_MEASURE'
               THEN
                  SELECT COUNT (PU.PKG_UOM_CD)
                    INTO L_COUNT
                    FROM PKG_UOM PU
                   WHERE     PU.EZCANCELFLAG = '0'
                         AND PU.GLBL_CMPY_CD = 'ADB'
                         AND PU.PKG_UOM_CD = L_COL_VALUE
                         AND PU.PKG_UOM_CLS_CD IN
                                (SELECT PUC.PKG_UOM_CLS_CD
                                   FROM PKG_UOM_CLS PUC
                                  WHERE     PUC.PKG_UOM_CLS_CD = REC_ITEMS.UOM_CLASS
                                        AND PUC.EZCANCELFLAG = PU.EZCANCELFLAG
                                        AND PUC.GLBL_CMPY_CD = PU.GLBL_CMPY_CD);

                  IF L_COUNT = 0
                  THEN
                     P_ERR_FLG := 'S';
                     P_ERR_MSG :=
                           'Canon Item#->'
                        || L_ITEM
                        || ' - [Unit of Measure] and [UOM Class] are Incompatible.';
                     INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                            P_USER_ID,
                                            REC_ITEMS.PROJ_ITEM_ID,
                                            REQ_COL.ATTRIBUTE_NAME,
                                            P_ERR_MSG);
                  END IF;
               ELSIF     REQ_COL.MAP_COL = 'MARKETING_MODEL'
                     --AND L_COL_VALUE = 'Y'
                     AND REC_ITEMS.MARKETING_MODEL IS NOT NULL
               THEN

                  BEGIN
                        SELECT COUNT(1)
                         INTO L_COUNT
                         FROM MKT_MDL
                        WHERE     GLBL_CMPY_CD = 'ADB'
                          AND EZCANCELFLAG = '0'
                          AND MKT_MDL_CD =REC_ITEMS.MARKETING_MODEL
                        ORDER BY REPLACE (UPPER (MKT_MDL_NM), '''', '');

                  EXCEPTION
                      WHEN OTHERS
                      THEN
                        L_COUNT :=0;
                  END;

                  IF L_COUNT=0 THEN
                      P_ERR_FLG := 'S';
                      P_ERR_MSG :=
                            'Canon Item#->'
                         || L_ITEM
                         || ' - '
                         || ' Please enter the New Marketing Model ' || REC_ITEMS.MARKETING_MODEL || ' in S21';
                      INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                             P_USER_ID,
                                             REC_ITEMS.PROJ_ITEM_ID,
                                             REQ_COL.ATTRIBUTE_NAME,
                                             P_ERR_MSG);
                   END IF;
               ELSIF     REQ_COL.MAP_COL = 'SERVICE_MODEL'
                     --AND L_COL_VALUE = 'Y'
                     AND REC_ITEMS.SERVICE_MODEL IS NOT NULL
               THEN

                  BEGIN
                        SELECT COUNT(1)
                         INTO L_COUNT
                         FROM MDL_NM
                        WHERE     T_GLBL_CMPY_CD = 'ADB'
                          AND EZCANCELFLAG = '0'
                          AND T_MDL_NM =REC_ITEMS.SERVICE_MODEL
                        ORDER BY REPLACE (UPPER (T_MDL_NM), '''', '');

                  EXCEPTION
                      WHEN OTHERS
                      THEN
                        L_COUNT :=0;
                  END;

                  IF L_COUNT=0 THEN
                      P_ERR_FLG := 'S';
                      P_ERR_MSG :=
                            'Canon Item#->'
                         || L_ITEM
                         || ' - '
                         || ' Please enter the New Service Model ' || REC_ITEMS.SERVICE_MODEL || ' in S21';
                      INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                             P_USER_ID,
                                             REC_ITEMS.PROJ_ITEM_ID,
                                             REQ_COL.ATTRIBUTE_NAME,
                                             P_ERR_MSG);
                   END IF;
               ELSIF     REQ_COL.MAP_COL = 'IMAGEWARE_REMOTE_ENABLED'
                     AND L_COL_VALUE = 'Y'
                     AND REC_ITEMS.IMAGEWARE_REMOTE_MODEL IS NULL
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - '
                     || ' If [Imageware Remote Enabled] is [Y], then [Imageware Remote Model] is mandatory.';
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     REQ_COL.MAP_COL = 'SERVICE_CALL_ENABLED'
                     AND L_COL_VALUE = 'Y'
                     AND NVL (REC_ITEMS.INSTALLBASE_CONTROLLED, 'N') = 'N'
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - '
                     || ' If [Service Call Enabled] is [Y], then [Installbase Controlled] must be [Y].';
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     REQ_COL.MAP_COL = 'SERIAL_CONTROL'
                     AND NVL (L_COL_VALUE, 'NOTCONTROLLED') != 'NOTCONTROLLED'
                     AND NVL (REC_ITEMS.INSTALLBASE_CONTROLLED, 'N') = 'N'
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - '
                     || ' If [Serial Control] is controlled, then [Installbase Controlled] must be [Y].';
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);

               END IF;
            END IF;

            IF REQ_COL.WORKBENCH_DISPLAY = 'NONE'
            THEN
               L_STR := '. Change default value for this attribute in the Template.';
            ELSE
               L_STR := NULL;
            END IF;

            --To validate Item Attribute values against S21 Column size
            IF REQ_COL.LOV_FLAG = 'N' AND L_COL_VALUE IS NOT NULL
            THEN
               IF LENGTH (L_COL_VALUE) > SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 4)
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] exceeds S21 mapping column length:'
                     || SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 4)
                     || L_STR;
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 1, 3) = 'NUM'
                     AND NOT REGEXP_LIKE (TRIM (L_COL_VALUE), '^\d+$')
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] should only accept whole number.'
                     || L_STR;
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 1, 3) = 'NPC'
                     AND REQ_COL.MAP_COL IN
                            ('STANDARD_COST', 'ASSET_RECOVERY_COST', 'PURCHASE_PRICE')
                     AND NOT REGEXP_LIKE (L_COL_VALUE,
                                          '^(\d|(\.\d{1,2})?$)+(\.\d{1,2})?$')
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] accepts either whole or decimal number upto 2 decimals.'
                     || L_STR;
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 1, 3) = 'NPC'
                     AND NOT REGEXP_LIKE (L_COL_VALUE,
                                          '^(\d|(\.\d{1,4})?$)+(\.\d{1,4})?$')
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] accepts either whole or decimal number upto 4 decimals.'
                     || L_STR;
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               END IF;
            END IF;                                                     --REQ_COL.LOV_FLAG
         END LOOP;

         IF     (   REC_ITEMS.SUPPLIER IS NOT NULL
                 OR REC_ITEMS.SUPPLIER_ITEM IS NOT NULL
                 OR REC_ITEMS.SUPPLIER_SITE IS NOT NULL)
            AND (   REC_ITEMS.SUPPLIER IS NULL
                 OR REC_ITEMS.SUPPLIER_ITEM IS NULL
                 OR REC_ITEMS.SUPPLIER_SITE IS NULL)
         THEN
            P_ERR_FLG := 'S';
            P_ERR_MSG :=
                  'Canon Item#->'
               || L_ITEM
               || ' - [Supplier] [Supplier Site] [Supplier Item] all or none required.';
            INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                   P_USER_ID,
                                   REC_ITEMS.PROJ_ITEM_ID,
                                   NULL,
                                   P_ERR_MSG);
         END IF;

        /*
        IF (REC_ITEMS.SUPPLIER IS NOT NULL
                   AND REC_ITEMS.SUPPLIER_ITEM IS NOT NULL
                   AND REC_ITEMS.SUPPLIER_SITE IS NOT NULL) THEN

            SELECT COUNT(1)
              INTO L_ASL_CNT
              FROM CANON_E008_ASL_NAME_V  asl                              --Custom Code Table View
             WHERE MERCH_TYPE = REC_ITEMS.MERCHANDISE_TYPE
             AND   SUPPLIER_CODE = REC_ITEMS.SUPPLIER
             AND EXISTS (SELECT 1 FROM ASL_HDR WHERE ASL_NM = asl.ASL_NAME AND PRNT_VND_CD = asl.Supplier_code) ;

              IF L_ASL_CNT = 0
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                  'Canon Item#->'
                   || L_ITEM
                   || ' - Code table CANON_E008_ASL_NAME_V has ASL not defined in S21.';

                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         NULL,
                                         NULL,
                                         P_ERR_MSG);
               END IF;

        END IF;
        */


      END LOOP;

      --Start Validation for Price Lists defined in the custom code table
      BEGIN
         SELECT COUNT (PRICELIST_NAME) INTO L_PRCL_CNT FROM CANON_E008_PRICELIST_NAMES_V;

        /* IF L_PRCL_CNT > 2
         THEN
            P_ERR_FLG := 'S';
            P_ERR_MSG :=
               'More than two price lists exist in the custom code table CANON_E008_PRICELIST_NAMES.';
            INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                   P_USER_ID,
                                   NULL,
                                   NULL,
                                   P_ERR_MSG);
         END IF; */

         SELECT COUNT (PRICELIST_NAME)
           INTO L_PRCL_CNT
           FROM CANON_E008_PRICELIST_NAMES_V
          WHERE PRICELIST_NAME NOT IN
                   (SELECT PC.PRC_CATG_NM
                      FROM PRC_CATG PC, PRC_CATG DPC, PRC_LIST_TP PLT
                     WHERE     DPC.PRC_LIST_TP_CD = PLT.PRC_LIST_TP_CD
                           AND DPC.PRC_CATG_CD = PC.PRC_CATG_CD
                           AND PLT.FL_LIST_PRC_FLG = 'Y'
                           AND DPC.ACTV_FLG = 'Y'
                           AND PLT.PRC_LIST_TP_CD IN ('01', '10') --  10   MSRP    and    01    Equipment Floor
                           AND DPC.GLBL_CMPY_CD = PC.GLBL_CMPY_CD
                           AND PLT.GLBL_CMPY_CD = PC.GLBL_CMPY_CD
                           AND DPC.EZCANCELFLAG = PC.EZCANCELFLAG
                           AND PLT.EZCANCELFLAG = PC.EZCANCELFLAG
                           AND DPC.GLBL_CMPY_CD = 'ADB'
                           AND DPC.EZCANCELFLAG = '0'
                           AND NVL (TO_DATE (DPC.EFF_FROM_DT, 'RRRRMMDD'),
                                    TRUNC (SYSDATE - 1)) <= TRUNC (SYSDATE)
                           AND NVL (TO_DATE (DPC.EFF_THRU_DT, 'RRRRMMDD'),
                                    TRUNC (SYSDATE + 1)) >= TRUNC (SYSDATE));

         IF L_PRCL_CNT > 0
         THEN
            P_ERR_FLG := 'S';
            P_ERR_MSG :=
               'Code table CANON_E008_PRICELIST_NAMES has entries other than MSRP and/or Equipment Floor pricelists.';
            INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                   P_USER_ID,
                                   NULL,
                                   NULL,
                                   P_ERR_MSG);
         END IF;
      END;




      --End Validation for Price Lists defined in the custom code table
      FOR PRJ_ERR IN (  SELECT FIELD_NAME,
                               USER_ID,
                               PROJECT_ID,
                               ITEM_ID
                          FROM CANON_E008_PROJECT_ERR_TBL
                         WHERE PROJECT_ID = P_PROJECT_NO AND USER_ID = P_USER_ID
                      ORDER BY ITEM_ID)
      LOOP
         --INSERT INTO test123   VALUES ('PRJ_ERR.ITEM_ID:'||PRJ_ERR.ITEM_ID || '-'|| 'L_PROJ_ITEM_ID:' ||L_PRJ_ITEM_ID);
         IF PRJ_ERR.ITEM_ID != L_PRJ_ITEM_ID
         THEN
            L_ROW := 0;

            FOR L_ITEM_REC IN (  SELECT PROJ_ITEM_ID
                                   FROM CANON_E008_PROJ_ITEMS_TBL
                                  WHERE PROJECT_ID = P_PROJECT_NO
                               ORDER BY PROJ_ITEM_ID)
            LOOP
               -- INSERT INTO test123   VALUES ( 'PRJ_ERR.ITEM_ID:'||PRJ_ERR.ITEM_ID
               -- || ' - ' ||'L_ITEM_REC.PROJ_ITEM_ID:'||L_ITEM_REC.PROJ_ITEM_ID ||' - ' || L_ROW );
               IF L_ITEM_REC.PROJ_ITEM_ID = PRJ_ERR.ITEM_ID
               THEN
                  EXIT;
               END IF;

               L_ROW := L_ROW + 1;
            END LOOP;
         END IF;

         L_PRJ_ITEM_ID := PRJ_ERR.ITEM_ID;
         L_COL_MAIN := 0;
         l_ATTRIBUTE_NAME := '';

         FOR UI_MAIN IN (  SELECT ATTRIBUTE_NAME
                             FROM CANON_E008_TEMPLATE_ATTR_TBL
                            WHERE UPPER (WORKBENCH_DISPLAY) = 'MAIN' AND ENABLE_FLAG = 'Y'
                         --AND APPROVAL_GROUP_OWNER = 'ITEM MASTER'
                         ORDER BY DISPLAY_SORT)
         LOOP

            IF UI_MAIN.ATTRIBUTE_NAME IN ( 'Marketing Model','Service Model','Supplier','Material Group1','Tariff Code','Default Source Warehouse') THEN
                l_ATTRIBUTE_NAME := 'default_text';
            --ELSIF UI_MAIN.ATTRIBUTE_NAME = 'Marketing Model' THEN
            --    l_ATTRIBUTE_NAME := 'modtemplateRowName';
            ELSE
                l_ATTRIBUTE_NAME := 'templateRowName';
            END IF;

            IF UI_MAIN.ATTRIBUTE_NAME = PRJ_ERR.FIELD_NAME
            THEN
               EXIT;
            END IF;

            L_COL_MAIN := L_COL_MAIN + 1;


         END LOOP;

         UPDATE CANON_E008_PROJECT_ERR_TBL
            --SET FIELD_NAME = 'templateRow' || L_ROW || L_COL_MAIN
            --SET FIELD_NAME = 'templateRowName' || L_ROW || L_COL_MAIN
            SET FIELD_NAME = l_ATTRIBUTE_NAME || L_ROW ||'-'|| L_COL_MAIN
          WHERE     PROJECT_ID = PRJ_ERR.PROJECT_ID
                AND USER_ID = PRJ_ERR.USER_ID
                AND ITEM_ID = PRJ_ERR.ITEM_ID
                AND FIELD_NAME = PRJ_ERR.FIELD_NAME
                AND NOT EXISTS
                       (SELECT ATTRIBUTE_NAME
                          FROM CANON_E008_TEMPLATE_ATTR_TBL
                         WHERE     UPPER (WORKBENCH_DISPLAY) IN ('ADDITIONAL', 'NONE')
                               AND ENABLE_FLAG = 'Y'
                               AND ATTRIBUTE_NAME = PRJ_ERR.FIELD_NAME);

         L_COL_ADDL := 0;
         l_ATTRIBUTE_NAME := '';

         FOR UI_ADDL
            IN (  SELECT ATTRIBUTE_NAME
                    FROM CANON_E008_TEMPLATE_ATTR_TBL
                   WHERE UPPER (WORKBENCH_DISPLAY) = 'ADDITIONAL' AND ENABLE_FLAG = 'Y'
                ORDER BY DISPLAY_SORT)
         LOOP

            IF UI_ADDL.ATTRIBUTE_NAME IN ( 'Marketing Model','Service Model','Supplier','Material Group1','Tariff Code','Default Source Warehouse') THEN
                l_ATTRIBUTE_NAME := 'default_text';
            --ELSIF UI_ADDL.ATTRIBUTE_NAME = 'Marketing Model' THEN
            --    l_ATTRIBUTE_NAME := 'modtemplateRowName';
            ELSE
                l_ATTRIBUTE_NAME := 'templateReqaddRow';
            END IF;

            IF UI_ADDL.ATTRIBUTE_NAME = PRJ_ERR.FIELD_NAME
            THEN
               EXIT;
            END IF;

            L_COL_ADDL := L_COL_ADDL + 1;
         END LOOP;

         UPDATE CANON_E008_PROJECT_ERR_TBL
            SET FIELD_NAME = l_ATTRIBUTE_NAME || L_ROW||'-' || L_COL_ADDL -- 'templateRowAddl' || L_ROW || L_COL_ADDL
                --FIELD_NAME = 'templateReqaddRow' || L_ROW || L_COL_ADDL -- 'templateRowAddl' || L_ROW || L_COL_ADDL
          WHERE     PROJECT_ID = PRJ_ERR.PROJECT_ID
                AND USER_ID = PRJ_ERR.USER_ID
                AND ITEM_ID = PRJ_ERR.ITEM_ID
                AND FIELD_NAME = PRJ_ERR.FIELD_NAME;
      END LOOP;

      COMMIT;

      OPEN P_PROJECT_ERROR FOR
           SELECT DISTINCT PROJECT_ID,
                           USER_ID,
                           ITEM_ID,
                           FIELD_NAME,
                           MESSAGE
             FROM CANON_E008_PROJECT_ERR_TBL
            WHERE PROJECT_ID = P_PROJECT_NO AND USER_ID = P_USER_ID
         ORDER BY ITEM_ID;                        --, TO_NUMBER (SUBSTR (FIELD_NAME, 12));
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END VALIDATE_PROJECT;

   PROCEDURE NOTIFICATION_BODY_DOC (PROJECT_ID      IN     VARCHAR2,
                                    DISPLAY_TYPE    IN     VARCHAR2,
                                    DOCUMENT        IN OUT VARCHAR2,
                                    DOCUMENT_TYPE   IN OUT VARCHAR2)
   IS
      MESSAGE_TEXT        CLOB;
      --NL                  VARCHAR2 (1) := fnd_global.newline;
      PRECOUNT            NUMBER;
      ----
      --L_ITEM_TYPE         WF_ITEMS.ITEM_TYPE%TYPE;
      --L_ITEM_KEY          WF_ITEMS.ITEM_KEY%TYPE;
      L_DOCUMENT          VARCHAR2 (32000);
      L_URL               VARCHAR2 (1000);
      L_GFM_AGENT         VARCHAR2 (255);
      -----
      TABLE_WIDTH         VARCHAR2 (6) := '"75%"';
      TABLE_BORDER        VARCHAR2 (3) := '"0"';
      TABLE_CELLPADDING   VARCHAR2 (3) := '"3"';
      TABLE_CELLSPACING   VARCHAR2 (3) := '"1"';
      TABLE_BGCOLOR       VARCHAR2 (7) := '"white"';
      TH_BGCOLOR          VARCHAR2 (9) := '"#cccc99"';
      TH_FONTCOLOR        VARCHAR2 (9) := '"#336699"';
      TH_FONTFACE         VARCHAR2 (80) := '"Arial, Helvetica, Geneva, sans-serif"';
      TD_BGCOLOR          VARCHAR2 (9) := '"#f7f7e7"';
      TD_FONTCOLOR        VARCHAR2 (7) := '"black"';
      TD_FONTFACE         VARCHAR2 (80) := '"Arial, Helvetica, Geneva, sans-serif"';
      L_TEST_URL          VARCHAR2 (2000);
      L_DOCUMENT_MAX      NUMBER := 25000;
      MESSAGE             VARCHAR2 (32767) := NULL;

      CURSOR C_PROJECT
      IS
         SELECT PROJECT_NUMBER,
                PROJECT_NAME,
                PROJECT_DESC,
                --UPPER (PPF.FIRST_NAME || ' ' || PPF.LAST_NAME)
                PROJECT_REQUESTOR,
                PROJECT_NOTES
           FROM CANON_E008_PROJECT_WB_TBL CPT
          --PER_ALL_PEOPLE_F PPF,
          --FND_USER FU
          WHERE                                         --  ppf.PERSON_ID = fu.EMPLOYEE_ID
                --AND CPT.PROJECT_REQUESTOR = USER_ID
                PROJECT_ID = PROJECT_ID;
   /*
    SELECT PROJECT_NUMBER,
           PROJECT_NAME,
           PROJECT_DESC,
           UPPER (PPF.FIRST_NAME || ' ' || PPF.LAST_NAME)
              PROJECT_REQUESTOR,
           PROJECT_NOTES
      FROM CANON.CANON_E008_PROJECT_WB_TBL CPT,
           PER_ALL_PEOPLE_F PPF,
           FND_USER FU
     WHERE     ppf.PERSON_ID = fu.EMPLOYEE_ID
           AND CPT.PROJECT_REQUESTOR = USER_ID
           AND project_id = l_item_key;
   */

   BEGIN
      DOCUMENT_TYPE := DISPLAY_TYPE;
      --L_ITEM_TYPE :=  NVL (SUBSTR (document_id, 1, INSTR (document_id, ':') - 1), 'WFERROR');
      --l_item_key := SUBSTR (document_id, 1, INSTR (document_id, '-') - 1); --SUBSTR (document_id, INSTR (document_id, ':') + 1);
      DBMS_LOB.CREATETEMPORARY (MESSAGE_TEXT, FALSE);

      IF (DISPLAY_TYPE = 'text/html')
      THEN
         DBMS_OUTPUT.PUT_LINE ('MESSAGE ' || MESSAGE);
         MESSAGE := MESSAGE || '</br></br></br>';
         MESSAGE :=
               MESSAGE
            || '<table bgcolor='
            || TABLE_BGCOLOR
            || ' width='
            || TABLE_WIDTH
            || ' border='
            || TABLE_BORDER
            || ' cellpadding='
            || TABLE_CELLPADDING
            || ' cellspacing='
            || TABLE_CELLSPACING
            || '>';
         MESSAGE := MESSAGE || '<tr bgcolor=' || TH_BGCOLOR || '>';
         MESSAGE :=
               MESSAGE
            || '<td align="left"><font color='
            || TH_FONTCOLOR
            || ' face='
            || TH_FONTFACE
            || '><b>'
            || 'Project Number '
            || '</b></td>';
         MESSAGE :=
               MESSAGE
            || '<td align="left"><font color='
            || TH_FONTCOLOR
            || ' face='
            || TH_FONTFACE
            || '><b>'
            || 'Project Description '
            || '</b></td>';
         MESSAGE :=
               MESSAGE
            || '<td align="left"><font color='
            || TH_FONTCOLOR
            || ' face='
            || TH_FONTFACE
            || '><b>'
            || 'Project Submitter '
            || '</b></td>';
         MESSAGE :=
               MESSAGE
            || '<td align="left"><font color='
            || TH_FONTCOLOR
            || ' face='
            || TH_FONTFACE
            || '><b>'
            || 'Project Notes '
            || '</b></td>';
         MESSAGE := MESSAGE || '</B></TR>';
         DBMS_LOB.WRITE (MESSAGE_TEXT,
                         LENGTH (MESSAGE),
                         1,
                         MESSAGE);
         DBMS_OUTPUT.PUT_LINE ('MESSAGE ' || MESSAGE);

         --MESSAGE := NULL;
          FOR CUR_PROJECT IN C_PROJECT
         LOOP
            MESSAGE := MESSAGE || '<TR><FONT SIZE="-2">';
            MESSAGE := MESSAGE || '<TD>' || CUR_PROJECT.PROJECT_NUMBER || '</TD>';
            MESSAGE := MESSAGE || '<TD>' || CUR_PROJECT.PROJECT_DESC || '</TD>';
            MESSAGE := MESSAGE || '<TD>' || CUR_PROJECT.PROJECT_REQUESTOR || '</TD>';
            MESSAGE := MESSAGE || '<TD>' || CUR_PROJECT.PROJECT_NOTES || '</TD>';
            MESSAGE := MESSAGE || '</FONT></TR>';
         --DBMS_LOB.writeappend (MESSAGE_TEXT, LENGTH (MESSAGE), MESSAGE);
         --MESSAGE := NULL;
         END LOOP;

         MESSAGE := MESSAGE || '</TABLE>';
         DBMS_LOB.WRITE (MESSAGE_TEXT,
                         LENGTH (MESSAGE),
                         1,
                         MESSAGE);
      END IF;

      DBMS_OUTPUT.PUT_LINE ('MESSAGE ' || MESSAGE);

      DOCUMENT := MESSAGE;
   /*
   -- document := message_text;
   preCount := DBMS_LOB.getLength (MESSAGE_TEXT);

   DECLARE
      l_index   NUMBER := 1;
      l_size    NUMBER := 32000;
      l_rsize   NUMBER := 0;
      l_var     VARCHAR2 (32001) := NULL;
   BEGIN
      l_rsize := preCount;

      WHILE l_index <= preCount
      LOOP
         IF l_rsize < l_size AND l_rsize > 0
         THEN
            l_var := DBMS_LOB.SUBSTR (MESSAGE_TEXT, l_rsize, l_index);
         ELSE
            l_var := DBMS_LOB.SUBSTR (MESSAGE_TEXT, l_size, l_index);
         END IF;

         IF l_var IS NOT NULL AND l_rsize > 0
         THEN
            wf_notification.writeToClob (document, l_var);
            l_index := l_index + l_size;
            l_rsize := preCount - l_index;
         END IF;
      END LOOP;
   END;
  */
   END NOTIFICATION_BODY_DOC;

   /*
         PROCEDURE APPROVAL_STATUS_LIST (P_PROJECT_STS_LIST   OUT G_REF_CUR_TYP,
                                         P_ERR_FLG            OUT VARCHAR2,
                                         P_ERR_MSG            OUT VARCHAR2)
         IS
            L_ERR_MSG   VARCHAR2 (2000);
         BEGIN
            OPEN P_PROJECT_STS_LIST FOR
                 SELECT DISTINCT VAL1
                   FROM CANON_S21_CD_VAL_TBL CD_VAL, CANON_S21_CD_TBL CD
                  WHERE     CD.CD_ID = CD_VAL.CD_ID
                        AND CD.CD_NAME = 'CANON_E008_PROJ_APPRV_STATUS'
               ORDER BY VAL1;

            P_ERR_FLG := 'S';
         EXCEPTION
            WHEN OTHERS
            THEN
               L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
               P_ERR_FLG := 'E';
               P_ERR_MSG := L_ERR_MSG;
         END APPROVAL_STATUS_LIST;
      */

   PROCEDURE MASTER_PROJECT_LIST (P_MASTER_PROJECT_LIST   OUT G_REF_CUR_TYP,
                                  P_ERR_FLG               OUT VARCHAR2,
                                  P_ERR_MSG               OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_MASTER_PROJECT_LIST FOR
           SELECT DISTINCT MASTER_PROJECT
             FROM CANON_E008_PROJECT_WB_TBL
            WHERE MASTER_PROJECT IS NOT NULL                 --APPROVAL_STATUS <> 'CLOSED'
         ORDER BY MASTER_PROJECT;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END MASTER_PROJECT_LIST;

   PROCEDURE GET_ITEM_MAIN_ATTRIBUTES (P_PROJECT_ID             IN     NUMBER,
                                       P_USER_ID                IN     VARCHAR2,
                                       P_ITEM_MAIN_ATTRIBUTES      OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                   OUT VARCHAR2,
                                       P_ERR_MSG                   OUT VARCHAR2)
   IS
      L_ERR_MSG       VARCHAR2 (2000);
      L_PROJ_STATUS   VARCHAR2 (100);
   BEGIN
      BEGIN
         SELECT APPROVAL_STATUS
           INTO L_PROJ_STATUS
           FROM CANON_E008_PROJECT_WB_TBL
          WHERE PROJECT_ID = P_PROJECT_ID;
      EXCEPTION
         WHEN OTHERS
         THEN
            L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
            P_ERR_FLG := 'E';
            P_ERR_MSG := L_ERR_MSG;
      END;

      IF L_PROJ_STATUS <> 'PENDING APPROVAL'
      THEN
         OPEN P_ITEM_MAIN_ATTRIBUTES FOR
              SELECT ATTRIBUTE_NAME, E008_WB_TBL_MAP, LOV_FLAG
                FROM CANON_E008_TEMPLATE_ATTR_TBL
               WHERE UPPER (WORKBENCH_DISPLAY) = 'MAIN' AND ENABLE_FLAG = 'Y'
            --AND APPROVAL_GROUP_OWNER = 'ITEM MASTER'
            ORDER BY DISPLAY_SORT;
      ELSE
         OPEN P_ITEM_MAIN_ATTRIBUTES FOR
              SELECT ATTRIBUTE_NAME, E008_WB_TBL_MAP, LOV_FLAG
                FROM (SELECT *
                        FROM (SELECT CAST ('2.' || DISPLAY_SORT AS NUMBER) DISPLAY_SORT,
                                     ATTRIBUTE_NAME,
                                     E008_WB_TBL_MAP,
                                     LOV_FLAG,
                                     (CASE APPROVAL_GROUP_OWNER
                                         WHEN 'ITEM MASTER - ACCOUNTING' THEN 'E008_ACCT'
                                         --WHEN 'ITEM MASTER' THEN 'E008_MERCH'
                                      WHEN 'ITEM MASTER - MARKETING' THEN 'E008_MKT'
                                         --WHEN 'ITEM MASTER' THEN 'E008_MSTR'
                                         --WHEN 'ITEM MASTER' THEN 'E008_PART'
                                      WHEN 'ITEM MASTER - PRICING' THEN 'E008_PRC'
                                         WHEN 'ITEM MASTER - SALES COMP' THEN 'E008_SALES'
                                         WHEN 'ITEM MASTER - SOLUTIONS' THEN 'E008_SOL'
                                         WHEN 'ITEM MASTER - SUPPLIES' THEN 'E008_SUP'
                                         WHEN 'ITEM MASTER - SERVICE' THEN 'E008_SVC'
                                      --ELSE 'E008_MERCH'
                                      END)
                                        APPROVAL_GROUP_OWNER
                                FROM CANON_E008_TEMPLATE_ATTR_TBL
                               WHERE     UPPER (WORKBENCH_DISPLAY) = 'MAIN'
                                     AND ENABLE_FLAG = 'Y'
                                     AND APPROVAL_GROUP_OWNER <> 'ITEM MASTER') Y
                       WHERE EXISTS
                               ( SELECT TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles',''))   --RO.XTRNAL_NM
                                   FROM AUTH_PSN AUTH,
                                     USR_ROLE US_R,
                                     ROLE R,
                                     RESRC_ROLE RR,
                                     PROT_RESRC PR,
                                     PROT_RESRC_OBJ PRO,
                                     RESRC_OBJ RO,
                                     RESRC_CLS RCLS
                                 WHERE 1=1
                                     AND R.ACTV_FLG = 'Y'
                                     AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                                     AND US_R.ROLE_ID = R.ROLE_ID
                                     AND R.ROLE_ID = RR.ROLE_ID
                                     AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                                     AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                                     AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                                     AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                                     AND AUTH.EZCANCELFLAG = 0
                                     AND US_R.EZCANCELFLAG = 0
                                     AND R.EZCANCELFLAG = 0
                                     AND RR.EZCANCELFLAG = 0
                                     AND PR.EZCANCELFLAG = 0
                                     AND RO.EZCANCELFLAG = 0
                                     AND RCLS.EZCANCELFLAG = 0
                                     AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                                     AND r.role_desc_txt <> 'All Functions'
                                     AND AUTH.USR_NM = P_USER_ID
                                     AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = Y.APPROVAL_GROUP_OWNER)
                                     --AND RO.XTRNAL_NM = Y.APPROVAL_GROUP_OWNER)

                               /* (SELECT ROLE_NM
                                   FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                                  WHERE     USR_NM = P_USER_ID
                                        AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                        AND UR.ROLE_ID = R.ROLE_ID
                                        AND R.ROLE_NM = Y.APPROVAL_GROUP_OWNER) */
                      UNION
                      SELECT *
                        FROM (SELECT DISPLAY_SORT,
                                     ATTRIBUTE_NAME,
                                     E008_WB_TBL_MAP,
                                     LOV_FLAG,
                                     (CASE APPROVAL_GROUP_OWNER
                                         WHEN 'ITEM MASTER - ACCOUNTING' THEN 'E008_ACCT'
                                         WHEN 'ITEM MASTER - MARKETING' THEN 'E008_MKT'
                                         WHEN 'ITEM MASTER - PRICING' THEN 'E008_PRC'
                                         WHEN 'ITEM MASTER - SALES COMP' THEN 'E008_SALES'
                                         WHEN 'ITEM MASTER - SOLUTIONS' THEN 'E008_SOL'
                                         WHEN 'ITEM MASTER - SUPPLIES' THEN 'E008_SUP'
                                         WHEN 'ITEM MASTER - SERVICE' THEN 'E008_SVC'
                                      --ELSE 'E008_MERCH'
                                      END)
                                        APPROVAL_GROUP_OWNER
                                FROM CANON_E008_TEMPLATE_ATTR_TBL
                               WHERE     UPPER (WORKBENCH_DISPLAY) = 'MAIN'
                                     AND ENABLE_FLAG = 'Y'
                                     AND APPROVAL_GROUP_OWNER <> 'ITEM MASTER') Y
                       WHERE NOT EXISTS
                                ( SELECT TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) --RO.XTRNAL_NM
                                           FROM AUTH_PSN AUTH,
                                             USR_ROLE US_R,
                                             ROLE R,
                                             RESRC_ROLE RR,
                                             PROT_RESRC PR,
                                             PROT_RESRC_OBJ PRO,
                                             RESRC_OBJ RO,
                                             RESRC_CLS RCLS
                                         WHERE 1=1
                                             AND R.ACTV_FLG = 'Y'
                                             AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                                             AND US_R.ROLE_ID = R.ROLE_ID
                                             AND R.ROLE_ID = RR.ROLE_ID
                                             AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                                             AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                                             AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                                             AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                                             AND AUTH.EZCANCELFLAG = 0
                                             AND US_R.EZCANCELFLAG = 0
                                             AND R.EZCANCELFLAG = 0
                                             AND RR.EZCANCELFLAG = 0
                                             AND PR.EZCANCELFLAG = 0
                                             AND RO.EZCANCELFLAG = 0
                                             AND RCLS.EZCANCELFLAG = 0
                                             AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                                             AND r.role_desc_txt <> 'All Functions'
                                             AND AUTH.USR_NM = P_USER_ID
                                             AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = Y.APPROVAL_GROUP_OWNER)
                                             --AND RO.XTRNAL_NM = Y.APPROVAL_GROUP_OWNER)


                             /*   (SELECT ROLE_NM
                                   FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                                  WHERE     USR_NM = P_USER_ID
                                        AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                        AND UR.ROLE_ID = R.ROLE_ID
                                        AND R.ROLE_NM = Y.APPROVAL_GROUP_OWNER)  */
                      UNION
                      SELECT DISPLAY_SORT,
                             ATTRIBUTE_NAME,
                             E008_WB_TBL_MAP,
                             LOV_FLAG,
                             APPROVAL_GROUP_OWNER
                        FROM CANON_E008_TEMPLATE_ATTR_TBL
                       WHERE     UPPER (WORKBENCH_DISPLAY) = 'MAIN'
                             AND ENABLE_FLAG = 'Y'
                             AND APPROVAL_GROUP_OWNER = 'ITEM MASTER')
            ORDER BY DISPLAY_SORT;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_ITEM_MAIN_ATTRIBUTES;

   PROCEDURE GET_ITEM_MAIN_ATTR_VALUES (P_TEMPLATE_NAME               VARCHAR2,
                                        P_ITEM_MAIN_ATTR_VALUES   OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                 OUT VARCHAR2,
                                        P_ERR_MSG                 OUT VARCHAR2)
   IS
      L_ERR_MSG                 VARCHAR2 (2000);

      CURSOR CUR_DEFAULT_ATTR_NONE
      IS
           SELECT TEMPLATE_NAME,
                  DISPLAY_SORT,
                  WORKBENCH_DISPLAY,
                  ATTRIBUTE_NAME,
                  DEFAULT_VALUE,
                  REQUIRED_VALUE,
                  E008_WB_TBL_MAP,
                  LOV_FLAG
             FROM (SELECT TEMPLATE_NAME,
                          0 DISPLAY_SORT,
                          --'MAIN' WORKBENCH_DISPLAY,
                          'X' APPROVAL_GROUP_OWNER,
                          'Template Id' ATTRIBUTE_NAME,
                          'MAIN' WORKBENCH_DISPLAY,
                          NULL REQUIRED_VALUE,
                          TO_CHAR (TEMPLATE_ID) DEFAULT_VALUE,
                          NULL E008_WB_TBL_MAP,
                          NULL LOV_FLAG
                     FROM CANON_E008_TEMPLATE_HDR_TBL
                    WHERE TEMPLATE_NAME = P_TEMPLATE_NAME
                   UNION
                   SELECT th.TEMPLATE_NAME,
                          TAT.DISPLAY_SORT,
                          --TAT.WORKBENCH_DISPLAY,
                          APPROVAL_GROUP_OWNER,
                          TAT.ATTRIBUTE_NAME,
                          DECODE (TAT.WORKBENCH_DISPLAY, 'MAIN', 'M', 'A')
                             WORKBENCH_DISPLAY,
                          DECODE (REQUIRED_VALUE, 'YES', 'Y', 'N') REQUIRED_VALUE,
                          NVL (TAAT.DEFAULT_VALUE, '') DEFAULT_VALUE, --NVL (TAAT.DEFAULT_VALUE, '')
                          TAT.E008_WB_TBL_MAP,
                          TAT.LOV_FLAG
                     FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                          CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                          CANON_E008_TEMPLATE_HDR_TBL TH
                    WHERE     1 = 1
                          AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                          AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                          AND TH.TEMPLATE_NAME = P_TEMPLATE_NAME
                          AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                          AND TAT.ENABLE_FLAG = 'Y'
                   --AND TAT.APPROVAL_GROUP_OWNER = 'ITEM MASTER'
                   UNION
                   SELECT th.TEMPLATE_NAME,
                          TAT.DISPLAY_SORT,
                          --TAT.WORKBENCH_DISPLAY,
                          APPROVAL_GROUP_OWNER,
                          TAT.ATTRIBUTE_NAME,
                          DECODE (TAT.WORKBENCH_DISPLAY, 'MAIN', 'M', 'A')
                             WORKBENCH_DISPLAY,
                          DECODE (REQUIRED_VALUE, 'YES', 'Y', 'N') REQUIRED_VALUE,
                          NVL (TAAT.DEFAULT_VALUE, 'NULL') DEFAULT_VALUE, --NVL (TAAT.DEFAULT_VALUE, '')
                          TAT.E008_WB_TBL_MAP,
                          TAT.LOV_FLAG
                     FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                          CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                          CANON_E008_TEMPLATE_HDR_TBL TH
                    WHERE     1 = 1
                          AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                          AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                          AND TH.TEMPLATE_NAME = P_TEMPLATE_NAME
                          AND TAT.WORKBENCH_DISPLAY = 'ADDITIONAL'
                          AND TAT.ENABLE_FLAG = 'Y')
         ORDER BY DISPLAY_SORT;

      v_sql                     VARCHAR2 (4000);
      p_input1                  VARCHAR2 (200);
      l_item_main_attr_values   G_REF_CUR_TYP;

      TYPE MyRec IS RECORD
      (
         col1   VARCHAR2 (100),
         col2   VARCHAR2 (200)
      );                                                               --define the record

      rec                       MyRec;
   BEGIN
      DELETE FROM CANON_E008_TEMP_LOV_TBL;

      FOR REC_ITEMS IN CUR_DEFAULT_ATTR_NONE
      LOOP
         IF     REC_ITEMS.LOV_FLAG = 'Y'
            AND REC_ITEMS.DEFAULT_VALUE IS NOT NULL
            AND REC_ITEMS.DEFAULT_VALUE <> 'NULL'
         THEN
            v_sql :=
                  'BEGIN CANON_E008_ITEM_TEMPLATE_PKG.'
               || SUBSTR (REC_ITEMS.E008_WB_TBL_MAP, 1, 25)
               || '_LIST(:p_input1, :l_item_main_attr_values, :P_ERR_FLG,:P_ERR_MSG); END;';
            DBMS_OUTPUT.PUT_LINE ('v_sql ' || v_sql);

            p_input1 := REC_ITEMS.DEFAULT_VALUE;

            EXECUTE IMMEDIATE v_sql
               USING IN p_input1,
                     OUT l_item_main_attr_values,
                     OUT P_ERR_FLG,
                     OUT P_ERR_MSG;

            LOOP
               FETCH l_item_main_attr_values INTO rec;

               EXIT WHEN l_item_main_attr_values%NOTFOUND;
               DBMS_OUTPUT.put_line (
                  REC_ITEMS.template_name || ',' || rec.col1 || ',' || rec.col2);

               INSERT INTO CANON_E008_TEMP_LOV_TBL
                       VALUES (
                                 REC_ITEMS.DISPLAY_SORT,
                                 REC_ITEMS.ATTRIBUTE_NAME,
                                    REC_ITEMS.WORKBENCH_DISPLAY
                                 || REC_ITEMS.REQUIRED_VALUE
                                 || REC_ITEMS.LOV_FLAG
                                 || REPLACE (rec.col1, ',', '')
                                 || '**'
                                 || REPLACE (rec.col2, ',', '')
                                 || '**'
                                 || REC_ITEMS.E008_WB_TBL_MAP);

               EXIT;
            END LOOP;

            DBMS_OUTPUT.PUT_LINE ('P_ERR_FLG ' || P_ERR_FLG);
            DBMS_OUTPUT.PUT_LINE ('P_ERR_MSG ' || P_ERR_MSG);
         ELSIF    REC_ITEMS.LOV_FLAG = 'Y'
		      AND REC_ITEMS.DEFAULT_VALUE IS NULL
         THEN
               INSERT INTO CANON_E008_TEMP_LOV_TBL
                       VALUES (
                                 REC_ITEMS.DISPLAY_SORT,
                                 REC_ITEMS.ATTRIBUTE_NAME,
                                    REC_ITEMS.WORKBENCH_DISPLAY
                                 || REC_ITEMS.REQUIRED_VALUE
                                 || REC_ITEMS.LOV_FLAG
                                 || null
                                 || '**'
                                 || null
                                 || '**'
                                 || REC_ITEMS.E008_WB_TBL_MAP);
         ELSE
            INSERT INTO CANON_E008_TEMP_LOV_TBL
                    VALUES (
                              REC_ITEMS.DISPLAY_SORT,
                              REC_ITEMS.ATTRIBUTE_NAME,
                                 REC_ITEMS.WORKBENCH_DISPLAY
                              || REC_ITEMS.REQUIRED_VALUE
                              || REC_ITEMS.LOV_FLAG
                              || REPLACE (REC_ITEMS.DEFAULT_VALUE, ',', ''));
         END IF;
      END LOOP;

      COMMIT;

      OPEN P_ITEM_MAIN_ATTR_VALUES FOR
           SELECT FIELD2, FIELD3
             FROM CANON_E008_TEMP_LOV_TBL
         ORDER BY Field1 ASC;

      /*
       IF P_TEMPLATE_NAME IS NOT NULL
         THEN
            OPEN P_ITEM_MAIN_ATTR_VALUES FOR
                 SELECT ATTRIBUTE_NAME, DEFAULT_VALUE
                   FROM (SELECT 0 DISPLAY_SORT,
                                'MAIN' WORKBENCH_DISPLAY,
                                'X' APPROVAL_GROUP_OWNER,
                                'Template Id' ATTRIBUTE_NAME,
                                TO_CHAR (TEMPLATE_ID) DEFAULT_VALUE
                           FROM CANON_E008_TEMPLATE_HDR_TBL
                          WHERE TEMPLATE_NAME = P_TEMPLATE_NAME
                         UNION
                         SELECT TAT.DISPLAY_SORT,
                                TAT.WORKBENCH_DISPLAY,
                                APPROVAL_GROUP_OWNER,
                                TAT.ATTRIBUTE_NAME,
                                   DECODE (TAT.WORKBENCH_DISPLAY, 'MAIN', 'M', 'A')
                                || DECODE (REQUIRED_VALUE, 'YES', 'Y', 'N')
                                || NVL (TAAT.DEFAULT_VALUE, '')
                                   DEFAULT_VALUE               --NVL (TAAT.DEFAULT_VALUE, '')
                           FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                                CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                                CANON_E008_TEMPLATE_HDR_TBL TH
                          WHERE     1 = 1
                                AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                                AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                                AND TH.TEMPLATE_NAME = P_TEMPLATE_NAME
                                AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                                AND TAT.ENABLE_FLAG = 'Y'
                         --AND TAT.APPROVAL_GROUP_OWNER = 'ITEM MASTER'
                         UNION
                         SELECT TAT.DISPLAY_SORT,
                                TAT.WORKBENCH_DISPLAY,
                                APPROVAL_GROUP_OWNER,
                                TAT.ATTRIBUTE_NAME,
                                   DECODE (TAT.WORKBENCH_DISPLAY, 'MAIN', 'M', 'A')
                                || DECODE (REQUIRED_VALUE, 'YES', 'Y', 'N')
                                || NVL (TAAT.DEFAULT_VALUE, 'NULL')
                                   DEFAULT_VALUE               --NVL (TAAT.DEFAULT_VALUE, '')
                           FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                                CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                                CANON_E008_TEMPLATE_HDR_TBL TH
                          WHERE     1 = 1
                                AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                                AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                                AND TH.TEMPLATE_NAME = P_TEMPLATE_NAME
                                AND TAT.WORKBENCH_DISPLAY = 'ADDITIONAL'
                                AND TAT.ENABLE_FLAG = 'Y')
               ORDER BY DISPLAY_SORT;
         END IF; */

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_ITEM_MAIN_ATTR_VALUES;

   PROCEDURE GET_ITEM_ADDL_ATTRIBUTES (P_ITEM_ADDL_ATTRIBUTES   OUT G_REF_CUR_TYP,
                                       P_ERR_FLG                OUT VARCHAR2,
                                       P_ERR_MSG                OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_ITEM_ADDL_ATTRIBUTES FOR
           SELECT ATTRIBUTE_NAME
             FROM CANON_E008_TEMPLATE_ATTR_TBL
            WHERE WORKBENCH_DISPLAY = 'ADDITIONAL' AND ENABLE_FLAG = 'Y'
         --AND approval_group_owner = 'ITEM MASTER'
         ORDER BY DISPLAY_SORT;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_ITEM_ADDL_ATTRIBUTES;

   PROCEDURE TEMPLATE_NAME_LIST (P_TEMPL_NAME_LIST   OUT G_REF_CUR_TYP,
                                 P_ERR_FLG           OUT VARCHAR2,
                                 P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
      L_INDEX     NUMBER := 1;
   BEGIN
      OPEN P_TEMPL_NAME_LIST FOR
           SELECT DISTINCT TEMPLATE_NAME
             FROM CANON_E008_TEMPLATE_HDR_TBL
            WHERE TEMPLATE_STATUS = 'ACTIVE'
         ORDER BY TEMPLATE_NAME;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END TEMPLATE_NAME_LIST;

   PROCEDURE CAT_TEMPLATE_LIST (P_PROJECT_CATEGORY   IN     VARCHAR2,
                                P_TEMPL_NAME_LIST       OUT G_REF_CUR_TYP,
                                P_ERR_FLG               OUT VARCHAR2,
                                P_ERR_MSG               OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_TEMPL_NAME_LIST FOR
           SELECT DISTINCT TEMPLATE_NAME
             FROM CANON_E008_TEMPLATE_HDR_TBL TH
            WHERE     TEMPLATE_STATUS = 'ACTIVE'
                  AND EXISTS
                         (SELECT CTV.TEMPLATE_TYPE
                            FROM CANON_E008_PROJ_CAT_TEMPL_TY_V CTV --Custom Code Table View
                           WHERE     CTV.TEMPLATE_TYPE = TH.TEMPLATE_TYPE
                                 AND PROJECT_CATEGORY = P_PROJECT_CATEGORY)
         ORDER BY TEMPLATE_NAME;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END CAT_TEMPLATE_LIST;

   PROCEDURE GET_PROJ_ITEM_ATTR_VALUES (P_PROJECT_ID                  NUMBER,
                                        P_ITEM_ID                     NUMBER,
                                        P_USER_ID                     VARCHAR2,
                                        P_ITEM_MAIN_ATTR_VALUES   OUT G_REF_CUR_TYP,
                                        P_ERR_FLG                 OUT VARCHAR2,
                                        P_ERR_MSG                 OUT VARCHAR2)
   IS
      L_ERR_MSG       VARCHAR2 (6000);
      L_SQL           VARCHAR2 (32000);
      NEW_SQL         VARCHAR2 (32000);
      L_COUNT         NUMBER := 0;
      L_PROJ_STATUS   VARCHAR2 (100);

      CURSOR ATTRIBUTE_CUR
      IS
           SELECT ATTRIBUTE_NAME,
                  DEFAULT_VALUE,
                     --E008_WB_TBL_MAP,
                     E008_WB_TBL_MAP
                  || (SELECT ITEM_TYPE
                        FROM CANON_E008_PROJ_ITEMS_TBL
                       WHERE     PROJECT_ID = P_PROJECT_ID
                             AND PROJ_ITEM_ID = P_ITEM_ID
                             AND E008_WB_TBL_MAP = 'ITEM_NUMBER'
                             AND ITEM_TYPE IN ('10', '11'))
                     E008_WB_TBL_MAP,
                  LOV_FLAG,
                  REQUIRED_VALUE,
                  DECODE (APPROVAL_STATUS,
                          'PENDING APPROVAL', DECODE (ROLE_NM, '', 'NO', 'YES'),
                          'AWAITING PRICING', 'NO',
                          'PENDING ITEM CREATION', 'NO',
                          'YES')
                     VALID
             FROM (SELECT TAT.ATTRIBUTE_NAME,
                          TAAT.DEFAULT_VALUE,
                          E008_WB_TBL_MAP,
                          LOV_FLAG,
                          REQUIRED_VALUE,
                          VALID,
                          APPROVAL_GROUP_OWNER,
                          PW.APPROVAL_STATUS,
                          (CASE TAT.APPROVAL_GROUP_OWNER
                              WHEN 'ITEM MASTER - ACCOUNTING' THEN 'E008_ACCT'
                              --WHEN 'ITEM MASTER' THEN 'E008_MERCH'
                           WHEN 'ITEM MASTER - MARKETING' THEN 'E008_MKT'
                              --WHEN 'ITEM MASTER' THEN 'E008_MSTR'
                              --WHEN 'ITEM MASTER' THEN 'E008_PART'
                           WHEN 'ITEM MASTER - PRICING' THEN 'E008_PRC'
                              WHEN 'ITEM MASTER - SALES COMP' THEN 'E008_SALES'
                              WHEN 'ITEM MASTER - SOLUTIONS' THEN 'E008_SOL'
                              WHEN 'ITEM MASTER - SUPPLIES' THEN 'E008_SUP'
                              WHEN 'ITEM MASTER - SERVICE' THEN 'E008_SVC'
                           --ELSE 'E008_MERCH'
                           END)
                             Y,
                          TAT.DISPLAY_SORT
                     FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                          CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                          CANON_E008_TEMPLATE_HDR_TBL TH,
                          CANON_E008_PROJ_ITEMS_TBL PI,
                          CANON_E008_PROJECT_WB_TBL PW
                    WHERE     1 = 1
                          AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                          AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                          --AND th.template_name = 'USA_EQUIPMENT LOT CONTROL'
                          AND TH.TEMPLATE_ID = PI.TEMPLATE_ID
                          AND PI.PROJECT_ID = PW.PROJECT_ID
                          AND PI.PROJECT_ID = P_PROJECT_ID
                          AND PI.PROJ_ITEM_ID = P_ITEM_ID
                          AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                          AND TAT.ENABLE_FLAG = 'Y') A,
                  ( SELECT DISTINCT TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) ROLE_NM  -- RO.XTRNAL_NM ROLE_NM
                       FROM AUTH_PSN AUTH,
                         USR_ROLE US_R,
                         ROLE R,
                         RESRC_ROLE RR,
                         PROT_RESRC PR,
                         PROT_RESRC_OBJ PRO,
                         RESRC_OBJ RO,
                         RESRC_CLS RCLS
                     WHERE 1=1
                         AND R.ACTV_FLG = 'Y'
                         AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                         AND US_R.ROLE_ID = R.ROLE_ID
                         AND R.ROLE_ID = RR.ROLE_ID
                         AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                         AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                         AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                         AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                         AND AUTH.EZCANCELFLAG = 0
                         AND US_R.EZCANCELFLAG = 0
                         AND R.EZCANCELFLAG = 0
                         AND RR.EZCANCELFLAG = 0
                         AND PR.EZCANCELFLAG = 0
                         AND RO.EZCANCELFLAG = 0
                         AND RCLS.EZCANCELFLAG = 0
                         AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                         AND r.role_desc_txt <> 'All Functions'
                         AND AUTH.USR_NM = P_USER_ID
                         AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) LIKE 'E008%' ) X
                         --AND RO.XTRNAL_NM LIKE 'E008%' ) X
                 /* (SELECT ROLE_NM
                     FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                    WHERE     USR_NM = P_USER_ID
                          AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                          AND UR.ROLE_ID = R.ROLE_ID
                          AND R.ROLE_NM LIKE '%E008%') X  */

            WHERE A.Y = X.ROLE_NM(+)
         ORDER BY DISPLAY_SORT;

      CURSOR ATTRIBUTE_PENDING_CUR
      IS
           SELECT ATTRIBUTE_NAME,
                  DEFAULT_VALUE,
                     --E008_WB_TBL_MAP,
                     E008_WB_TBL_MAP
                  || (SELECT ITEM_TYPE
                        FROM CANON_E008_PROJ_ITEMS_TBL
                       WHERE     PROJECT_ID = P_PROJECT_ID
                             AND PROJ_ITEM_ID = P_ITEM_ID
                             AND E008_WB_TBL_MAP = 'ITEM_NUMBER'
                             AND ITEM_TYPE IN ('10', '11'))
                     E008_WB_TBL_MAP,
                  LOV_FLAG,
                  REQUIRED_VALUE,
                  DECODE (APPROVAL_STATUS,
                          'PENDING APPROVAL', VALID,--DECODE (ROLE_NM, '', 'NO', 'YES'),
                          'AWAITING PRICING', 'NO',
                          'PENDING ITEM CREATION', 'NO',
                          'YES')
                     VALID
             FROM (SELECT *
                     FROM (SELECT TAT.ATTRIBUTE_NAME,
                                  TAAT.DEFAULT_VALUE,
                                  E008_WB_TBL_MAP,
                                  LOV_FLAG,
                                  REQUIRED_VALUE,
                                  'YES' VALID,
                                  APPROVAL_GROUP_OWNER,
                                  PW.APPROVAL_STATUS,
                                  (CASE TAT.APPROVAL_GROUP_OWNER
                                      WHEN 'ITEM MASTER - ACCOUNTING' THEN 'E008_ACCT'
                                      --WHEN 'ITEM MASTER' THEN 'E008_MERCH'
                                   WHEN 'ITEM MASTER - MARKETING' THEN 'E008_MKT'
                                      --WHEN 'ITEM MASTER' THEN 'E008_MSTR'
                                      --WHEN 'ITEM MASTER' THEN 'E008_PART'
                                   WHEN 'ITEM MASTER - PRICING' THEN 'E008_PRC'
                                      WHEN 'ITEM MASTER - SALES COMP' THEN 'E008_SALES'
                                      WHEN 'ITEM MASTER - SOLUTIONS' THEN 'E008_SOL'
                                      WHEN 'ITEM MASTER - SUPPLIES' THEN 'E008_SUP'
                                      WHEN 'ITEM MASTER - SERVICE' THEN 'E008_SVC'
                                   --ELSE 'E008_MERCH'
                                   END)
                                     ROLE_NM,
                                  CAST ('2.' || TAT.DISPLAY_SORT AS NUMBER) DISPLAY_SORT
                             FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                                  CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                                  CANON_E008_TEMPLATE_HDR_TBL TH,
                                  CANON_E008_PROJ_ITEMS_TBL PI,
                                  CANON_E008_PROJECT_WB_TBL PW
                            WHERE     1 = 1
                                  AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                                  AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                                  --AND th.template_name = 'USA_EQUIPMENT LOT CONTROL'
                                  AND TH.TEMPLATE_ID = PI.TEMPLATE_ID
                                  AND PI.PROJECT_ID = PW.PROJECT_ID
                                  AND PI.PROJECT_ID = P_PROJECT_ID
                                  AND PI.PROJ_ITEM_ID = P_ITEM_ID
                                  AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                                  AND TAT.ENABLE_FLAG = 'Y') A
                    WHERE EXISTS
                            ( SELECT DISTINCT TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) ROLE_NM  --RO.XTRNAL_NM ROLE_NM
                               FROM AUTH_PSN AUTH,
                                 USR_ROLE US_R,
                                 ROLE R,
                                 RESRC_ROLE RR,
                                 PROT_RESRC PR,
                                 PROT_RESRC_OBJ PRO,
                                 RESRC_OBJ RO,
                                 RESRC_CLS RCLS
                             WHERE 1=1
                                 AND R.ACTV_FLG = 'Y'
                                 AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                                 AND US_R.ROLE_ID = R.ROLE_ID
                                 AND R.ROLE_ID = RR.ROLE_ID
                                 AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                                 AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                                 AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                                 AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                                 AND AUTH.EZCANCELFLAG = 0
                                 AND US_R.EZCANCELFLAG = 0
                                 AND R.EZCANCELFLAG = 0
                                 AND RR.EZCANCELFLAG = 0
                                 AND PR.EZCANCELFLAG = 0
                                 AND RO.EZCANCELFLAG = 0
                                 AND RCLS.EZCANCELFLAG = 0
                                 AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                                 AND r.role_desc_txt <> 'All Functions'
                                 AND AUTH.USR_NM = P_USER_ID
                                 AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = A.ROLE_NM)
                                 --AND RO.XTRNAL_NM = A.ROLE_NM)

                           /*  (SELECT ROLE_NM
                                FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                               WHERE     USR_NM = P_USER_ID
                                     AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                     AND UR.ROLE_ID = R.ROLE_ID
                                     AND R.ROLE_NM = A.ROLE_NM)  */
                   UNION
                   SELECT *
                     FROM (SELECT TAT.ATTRIBUTE_NAME,
                                  TAAT.DEFAULT_VALUE,
                                  E008_WB_TBL_MAP,
                                  LOV_FLAG,
                                  REQUIRED_VALUE,
                                  'NO' VALID,
                                  APPROVAL_GROUP_OWNER,
                                  PW.APPROVAL_STATUS,
                                  (CASE TAT.APPROVAL_GROUP_OWNER
                                      WHEN 'ITEM MASTER - ACCOUNTING' THEN 'E008_ACCT'
                                      --WHEN 'ITEM MASTER' THEN 'E008_MERCH'
                                   WHEN 'ITEM MASTER - MARKETING' THEN 'E008_MKT'
                                      --WHEN 'ITEM MASTER' THEN 'E008_MSTR'
                                      --WHEN 'ITEM MASTER' THEN 'E008_PART'
                                   WHEN 'ITEM MASTER - PRICING' THEN 'E008_PRC'
                                      WHEN 'ITEM MASTER - SALES COMP' THEN 'E008_SALES'
                                      WHEN 'ITEM MASTER - SOLUTIONS' THEN 'E008_SOL'
                                      WHEN 'ITEM MASTER - SUPPLIES' THEN 'E008_SUP'
                                      WHEN 'ITEM MASTER - SERVICE' THEN 'E008_SVC'
                                   --ELSE 'E008_MERCH'
                                   END)
                                     ROLE_NM,
                                  TAT.DISPLAY_SORT
                             FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                                  CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                                  CANON_E008_TEMPLATE_HDR_TBL TH,
                                  CANON_E008_PROJ_ITEMS_TBL PI,
                                  CANON_E008_PROJECT_WB_TBL PW
                            WHERE     1 = 1
                                  AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                                  AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                                  --AND th.template_name = 'USA_EQUIPMENT LOT CONTROL'
                                  AND TH.TEMPLATE_ID = PI.TEMPLATE_ID
                                  AND PI.PROJECT_ID = PW.PROJECT_ID
                                  AND PI.PROJECT_ID = P_PROJECT_ID
                                  AND PI.PROJ_ITEM_ID = P_ITEM_ID
                                  AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                                  AND TAT.ENABLE_FLAG = 'Y') A
                    WHERE NOT EXISTS
                            ( SELECT DISTINCT TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) ROLE_NM -- RO.XTRNAL_NM ROLE_NM
                                       FROM AUTH_PSN AUTH,
                                         USR_ROLE US_R,
                                         ROLE R,
                                         RESRC_ROLE RR,
                                         PROT_RESRC PR,
                                         PROT_RESRC_OBJ PRO,
                                         RESRC_OBJ RO,
                                         RESRC_CLS RCLS
                                     WHERE 1=1
                                         AND R.ACTV_FLG = 'Y'
                                         AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
                                         AND US_R.ROLE_ID = R.ROLE_ID
                                         AND R.ROLE_ID = RR.ROLE_ID
                                         AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
                                         AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
                                         AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
                                         AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
                                         AND AUTH.EZCANCELFLAG = 0
                                         AND US_R.EZCANCELFLAG = 0
                                         AND R.EZCANCELFLAG = 0
                                         AND RR.EZCANCELFLAG = 0
                                         AND PR.EZCANCELFLAG = 0
                                         AND RO.EZCANCELFLAG = 0
                                         AND RCLS.EZCANCELFLAG = 0
                                         AND RCLS.RESRC_CLS_NM = 'FUNCTION'
                                         AND r.role_desc_txt <> 'All Functions'
                                         AND AUTH.USR_NM = P_USER_ID
                                         AND TRIM(REPLACE(ro.resrc_obj_desc_txt,'Roles','')) = A.ROLE_NM))
                                         --AND RO.XTRNAL_NM = A.ROLE_NM))

                          /*   (SELECT ROLE_NM
                                FROM AUTH_PSN AP, USR_ROLE UR, ROLE R
                               WHERE     USR_NM = P_USER_ID
                                     AND UR.AUTH_PSN_ID = AP.AUTH_PSN_ID
                                     AND UR.ROLE_ID = R.ROLE_ID
                                     AND R.ROLE_NM = A.ROLE_NM)) */
         ORDER BY DISPLAY_SORT;
   BEGIN
      DBMS_OUTPUT.PUT_LINE ('MESSAGE1 ');

      BEGIN
         SELECT APPROVAL_STATUS
           INTO L_PROJ_STATUS
           FROM CANON_E008_PROJECT_WB_TBL
          WHERE PROJECT_ID = P_PROJECT_ID;
      EXCEPTION
         WHEN OTHERS
         THEN
            L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
            P_ERR_FLG := 'E';
            P_ERR_MSG := L_ERR_MSG;
      END;

      --l_PROJ_STATUS := 'PENDING APPROVAL';

      IF     P_PROJECT_ID IS NOT NULL
         AND P_ITEM_ID IS NOT NULL
         AND L_PROJ_STATUS <> 'PENDING APPROVAL'
      THEN
         DBMS_OUTPUT.PUT_LINE ('MESSAGE2 ');

         FOR ATTRIBUTE_REC IN ATTRIBUTE_CUR
         LOOP
            L_COUNT := L_COUNT + 1;

            L_SQL :=
                  L_SQL
               || ' Select project_id, proj_item_id item_id,template_id, '''
               || ATTRIBUTE_REC.ATTRIBUTE_NAME
               || ''' template, '
               || L_COUNT
               || ' attribute,'
               --|| NVL (ATTRIBUTE_REC.E008_WB_TBL_MAP, 'NULL')
               /*|| NVL (
                     REPLACE (ATTRIBUTE_REC.E008_WB_TBL_MAP,
                              'ITEM_NUMBER11',
                              'ITEM_NUMBER'),
                     'NULL')  */
               || NVL (REGEXP_REPLACE (ATTRIBUTE_REC.E008_WB_TBL_MAP, '10|11', ''),
                       'NULL')
               || ' attribute_value,'''
               || ATTRIBUTE_REC.REQUIRED_VALUE
               || ''' attribute_req, '''
               || ATTRIBUTE_REC.VALID
               || ''' attribute_valid, '''
               || NVL (ATTRIBUTE_REC.LOV_FLAG, 'NULL')
               || ''' attribute_lovflag, '''
               || NVL (SUBSTR (ATTRIBUTE_REC.E008_WB_TBL_MAP, 1, 25), 'NULL')
               || ''' attribute_lovname, '''
               || NVL (ATTRIBUTE_REC.DEFAULT_VALUE, 'NULL')
               || ''' attribute_defvalue,mercury_include from canon_e008_proj_items_tbl WHERE PROJECT_ID = '
               || P_PROJECT_ID
               || ' and proj_item_id = '
               || P_ITEM_ID;

            DBMS_OUTPUT.PUT_LINE ('MESSAGE ROWCOUNT ' || ATTRIBUTE_CUR%ROWCOUNT);

            L_SQL := L_SQL || ' UNION ';
         END LOOP;

         NEW_SQL := SUBSTR (L_SQL, 1, (LENGTH (L_SQL) - 6));
         NEW_SQL := NEW_SQL || ' order by attribute ';
         DBMS_OUTPUT.PUT_LINE ('l_sql ' || NEW_SQL);
      --EXECUTE IMMEDIATE new_sql INTO p_item_main_attr_values;
      END IF;

      IF     P_PROJECT_ID IS NOT NULL
         AND P_ITEM_ID IS NOT NULL
         AND L_PROJ_STATUS = 'PENDING APPROVAL'
      THEN
         DBMS_OUTPUT.PUT_LINE ('MESSAGE3 ');

         FOR ATTRIBUTE_PENDING_REC IN ATTRIBUTE_PENDING_CUR
         LOOP
            L_COUNT := L_COUNT + 1;

            L_SQL :=
                  L_SQL
               || ' Select project_id, proj_item_id item_id,template_id, '''
               || ATTRIBUTE_PENDING_REC.ATTRIBUTE_NAME
               || ''' template, '
               || L_COUNT
               || ' attribute,'
               --|| NVL (ATTRIBUTE_PENDING_REC.E008_WB_TBL_MAP, 'NULL')
               /*|| NVL (
                     REPLACE (ATTRIBUTE_PENDING_REC.E008_WB_TBL_MAP,
                              'ITEM_NUMBER11',
                              'ITEM_NUMBER'),
                     'NULL')  */
               || NVL (
                     REGEXP_REPLACE (ATTRIBUTE_PENDING_REC.E008_WB_TBL_MAP, '10|11', ''),
                     'NULL')
               || ' attribute_value,'''
               || ATTRIBUTE_PENDING_REC.REQUIRED_VALUE
               || ''' attribute_req, '''
               || ATTRIBUTE_PENDING_REC.VALID
               || ''' attribute_valid, '''
               || NVL (ATTRIBUTE_PENDING_REC.LOV_FLAG, 'NULL')
               || ''' attribute_lovflag, '''
               || NVL (SUBSTR (ATTRIBUTE_PENDING_REC.E008_WB_TBL_MAP, 1, 25), 'NULL')
               || ''' attribute_lovname, '''
               || NVL (ATTRIBUTE_PENDING_REC.DEFAULT_VALUE, 'NULL')
               || ''' attribute_defvalue,mercury_include from canon_e008_proj_items_tbl WHERE PROJECT_ID = '
               || P_PROJECT_ID
               || ' and proj_item_id = '
               || P_ITEM_ID;

            DBMS_OUTPUT.PUT_LINE ('MESSAGE ROWCOUNT ' || ATTRIBUTE_PENDING_CUR%ROWCOUNT);

            L_SQL := L_SQL || ' UNION ';
         END LOOP;

         NEW_SQL := SUBSTR (L_SQL, 1, (LENGTH (L_SQL) - 6));
         NEW_SQL := NEW_SQL || ' order by attribute ';
         DBMS_OUTPUT.PUT_LINE ('l_sql ' || NEW_SQL);
      --EXECUTE IMMEDIATE new_sql INTO p_item_main_attr_values;
      END IF;

      OPEN P_ITEM_MAIN_ATTR_VALUES FOR NEW_SQL;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_PROJ_ITEM_ATTR_VALUES;

   PROCEDURE GET_PROJ_ITEM_ADD_ATTR_VALUES (P_PROJECT_ID                  NUMBER,
                                            P_ITEM_ID                     NUMBER,
                                            P_ITEM_MAIN_ATTR_VALUES   OUT G_REF_CUR_TYP,
                                            P_ERR_FLG                 OUT VARCHAR2,
                                            P_ERR_MSG                 OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (6000);
      L_SQL       VARCHAR2 (32767);
      NEW_SQL     VARCHAR2 (32767);
      L_COUNT     NUMBER := 0;

      CURSOR ATTRIBUTE_CUR
      IS
           SELECT TAT.CATEGORY_NAME,
                  TAT.ATTRIBUTE_NAME,
                  TAAT.DEFAULT_VALUE,
                  E008_WB_TBL_MAP,
                  REQUIRED_VALUE,
                  VALID,
                  LOV_FLAG
             FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                  CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                  CANON_E008_TEMPLATE_HDR_TBL TH
            WHERE     1 = 1
                  AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                  AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                  AND TH.TEMPLATE_ID =
                         (SELECT TEMPLATE_ID
                            FROM CANON_E008_PROJ_ITEMS_TBL
                           WHERE PROJECT_ID = P_PROJECT_ID AND PROJ_ITEM_ID = P_ITEM_ID)
                  AND TAT.WORKBENCH_DISPLAY = 'ADDITIONAL'
                  AND TAT.ENABLE_FLAG = 'Y'
         ORDER BY TAT.DISPLAY_SORT;
   BEGIN
      DBMS_OUTPUT.PUT_LINE ('MESSAGE1 ');

      COMMIT;

      IF P_PROJECT_ID = 1 AND P_ITEM_ID = 1
      THEN
         OPEN P_ITEM_MAIN_ATTR_VALUES FOR
              SELECT NULL PROJECT_ID,
                     NULL ITEM_ID,
                     NULL TEMPLATE_ID,
                     TAT.CATEGORY_NAME TEMPLATE,
                     TAT.ATTRIBUTE_NAME ATTRIBUTE,
                     --TAAT.DEFAULT_VALUE ATTRIBUTE_VALUE,
                     NULL ATTRIBUTE_VALUE,
                     REQUIRED_VALUE ATTRIBUTE_REQ,
                     VALID ATTRIBUTE_VALID,
                     LOV_FLAG ATTRIBUTE_LOVFLAG,
                     SUBSTR (E008_WB_TBL_MAP, 1, 25) ATTRIBUTE_LOVNAME,
                     --TAAT.DEFAULT_VALUE ATTRIBUTE_DEFVALUE
                     NULL ATTRIBUTE_DEFVALUE,
                     NULL MERCURY_INCLUDE
                FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                     CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                     CANON_E008_TEMPLATE_HDR_TBL TH
               WHERE     1 = 1
                     AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                     AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                     AND TH.TEMPLATE_ID = (SELECT TEMPLATE_ID
                                             FROM CANON_E008_TEMPLATE_HDR_TBL
                                            WHERE ROWNUM < 2)
                     AND TAT.WORKBENCH_DISPLAY = 'ADDITIONAL'
                     AND TAT.ENABLE_FLAG = 'Y'
            ORDER BY TAT.DISPLAY_SORT;
      END IF;

      IF P_PROJECT_ID IS NOT NULL AND P_ITEM_ID IS NOT NULL
      THEN
         DBMS_OUTPUT.PUT_LINE ('MESSAGE2 ');

         FOR ATTRIBUTE_REC IN ATTRIBUTE_CUR
         LOOP
            L_COUNT := L_COUNT + 1;

            --l_sql := l_sql || ' Select project_id, proj_item_id item_id,template_id, null template, '|| l_count ||' attribute,'|| nvl(attribute_rec.E008_WB_TBL_MAP,'NULL') || ' attribute_value from canon_e008_proj_items_tbl WHERE PROJECT_ID = ' || p_project_id || ' and proj_item_id = ' || p_item_id;
            L_SQL :=
                  L_SQL
               || ' Select project_id, '
               || L_COUNT
               || ' item_id,template_id,'''
               || ATTRIBUTE_REC.CATEGORY_NAME
               || ''' template, '''
               || ATTRIBUTE_REC.ATTRIBUTE_NAME
               || ''' attribute,'
               || NVL (ATTRIBUTE_REC.E008_WB_TBL_MAP, 'NULL')
               --|| NVL(ATTRIBUTE_REC.DEFAULT_VALUE,'NULL')
               || ' attribute_value, '''
               || ATTRIBUTE_REC.REQUIRED_VALUE
               || ''' attribute_req, '''
               || ATTRIBUTE_REC.VALID
               || ''' attribute_valid, '''
               || ATTRIBUTE_REC.LOV_FLAG
               || ''' attribute_lovflag, '''
               || NVL (SUBSTR (ATTRIBUTE_REC.E008_WB_TBL_MAP, 1, 25), 'NULL')
               || ''' attribute_lovname ,'''
               || NVL (ATTRIBUTE_REC.DEFAULT_VALUE, 'NULL')
               || ''' attribute_defvalue, NULL MERCURY_INCLUDE '
               || ' from canon_e008_proj_items_tbl WHERE PROJECT_ID = '
               || P_PROJECT_ID
               || ' and proj_item_id = '
               || P_ITEM_ID;

            DBMS_OUTPUT.PUT_LINE ('MESSAGE ROWCOUNT ' || ATTRIBUTE_CUR%ROWCOUNT);

            L_SQL := L_SQL || ' UNION ';
         END LOOP;

         NEW_SQL := SUBSTR (L_SQL, 1, (LENGTH (L_SQL) - 6));

        -- DBMS_OUTPUT.PUT_LINE ('l_sql ' || NEW_SQL || ' order by item_id');
      --EXECUTE IMMEDIATE new_sql INTO p_item_main_attr_values;
      END IF;

      OPEN P_ITEM_MAIN_ATTR_VALUES FOR NEW_SQL;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         DBMS_OUTPUT.PUT_LINE ('P_ERR_MSG ' || P_ERR_MSG );
   END GET_PROJ_ITEM_ADD_ATTR_VALUES;

   PROCEDURE PROJECT_TYPE_LIST (P_PROJ_TYPE_LIST   OUT G_REF_CUR_TYP,
                                P_ERR_FLG          OUT VARCHAR2,
                                P_ERR_MSG          OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
      L_INDEX     NUMBER := 1;
   BEGIN
      OPEN P_PROJ_TYPE_LIST FOR
           SELECT DISTINCT PROJECT_CATEGORY
             FROM CANON_E008_PROJ_CATG_DEF_V                      --Custom Code Table View
         ORDER BY PROJECT_CATEGORY;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END PROJECT_TYPE_LIST;

   PROCEDURE ADD_NOTES (P_PROJECT_ID   IN     NUMBER,
                        P_USER_ID      IN     VARCHAR2,
                        P_COMMENTS     IN     VARCHAR2,
                        ERRBUF            OUT VARCHAR2,
                        RETCODE           OUT VARCHAR2)
   IS
      L_COMMENTS   VARCHAR2 (8000);
   BEGIN
      SELECT    CHR (13)
             || TO_CHAR (SYSDATE, 'MM/DD/YYYY HH24:MI:SS')
             || CHR (9)
             || P_USER_ID
             || CHR (9)
             || FIRST_NM
             || ' '
             || LAST_NM
             || CHR (13)
             || P_COMMENTS
        INTO L_COMMENTS
        FROM AUTH_PSN A
       WHERE USR_NM = P_USER_ID;

      UPDATE CANON_E008_PROJECT_WB_TBL
         SET PROJECT_NOTES =
                NVL2 (PROJECT_NOTES,
                      L_COMMENTS || CHR (13) || PROJECT_NOTES || CHR (13),
                      L_COMMENTS)
       WHERE PROJECT_ID = P_PROJECT_ID;

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         INSERT_ERROR ('ADD NOTES',
                       'EXCEPTION - ' || P_USER_ID || ' does not exist - ' || SQLERRM,
                       ERRBUF,
                       RETCODE);
   END ADD_NOTES;

   PROCEDURE START_APPRV_PROCESS (P_PROJECT_ID   IN     NUMBER,
                                  P_ERR_FLG         OUT VARCHAR2,
                                  P_ERR_MSG         OUT VARCHAR2)
   IS
      L_ROLE_NAME    VARCHAR2 (100);
      L_PROCESS_ID   NUMBER;
   BEGIN
      L_PROCESS_ID := CANON_E008_PRJ_PRC_SEQ.NEXTVAL;

      UPDATE CANON_E008_PROJECT_WB_TBL
         SET APPROVAL_STATUS = 'ITEM MASTER REVIEW',
             LAST_UPDATE_DATE = SYSDATE,
             PROCESS_ID = L_PROCESS_ID
       WHERE PROJECT_ID = P_PROJECT_ID;

      BEGIN
         SELECT DISTINCT PROJECT_OWNER
           INTO L_ROLE_NAME
           FROM CANON_E008_PROJ_CATG_DEF_V CDT                    --Custom Code Table View
          WHERE EXISTS
                   (SELECT PROJECT_TYPE
                      FROM CANON_E008_PROJECT_WB_TBL
                     WHERE     PROJECT_ID = P_PROJECT_ID
                           AND PROJECT_TYPE = CDT.PROJECT_CATEGORY);
      EXCEPTION
         WHEN OTHERS
         THEN
            L_ROLE_NAME := NULL;
      END;

      INSERT_HISTORY (P_PROJECT_ID,
                      L_PROCESS_ID,
                      L_ROLE_NAME,
                      NULL,
                      'PENDING',
                      NULL);


      ASSIGN_APPROVAL_GROUPS (P_PROJECT_ID, L_PROCESS_ID);
      P_ERR_FLG := 'S';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         P_ERR_FLG := 'E';
         P_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         ROLLBACK;
   END START_APPRV_PROCESS;

   PROCEDURE INSERT_HISTORY (PROJECT_ID           NUMBER,
                             PROCESS_ID           NUMBER,
                             APPROVER_ROLE        VARCHAR2,
                             APPROVER_NAME        VARCHAR2,
                             APPROVER_STATUS      VARCHAR2,
                             APPROVER_COMMENTS    VARCHAR2)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;

      L_PROJECT_ID      NUMBER := NULL;
      L_APPRV_DEPT      VARCHAR2 (200);
      L_APPRV_ROLE_ID   VARCHAR2 (200);
      L_ERRBUF          VARCHAR2 (200);
      L_RETCODE         VARCHAR2 (200);
      L_PROCESS_ID      NUMBER;
   BEGIN
      L_PROJECT_ID := PROJECT_ID;
      L_PROCESS_ID := PROCESS_ID;
      L_APPRV_DEPT := APPROVER_ROLE;

      BEGIN
         SELECT CASE APPROVER_ROLE
                   WHEN 'ITEM MASTER - ACCOUNTING' THEN 'E008_ACCT'
                   WHEN 'ITEM MASTER - MERCH' THEN 'E008_MERCH'
                   WHEN 'ITEM MASTER - MARKETING' THEN 'E008_MKT'
                   --WHEN 'ITEM MASTER' THEN 'E008_MSTR'
                   WHEN 'ITEM MASTER - PARTS' THEN 'E008_PART'
                   WHEN 'ITEM MASTER - PRICING' THEN 'E008_PRC'
                   WHEN 'ITEM MASTER - SALES COMP' THEN 'E008_SALES'
                   WHEN 'ITEM MASTER - SOLUTIONS' THEN 'E008_SOL'
                   WHEN 'ITEM MASTER - SUPPLIES' THEN 'E008_SUP'
                   WHEN 'ITEM MASTER - SERVICE' THEN 'E008_SVC'
                   ELSE 'E008_MERCH'
                END
           INTO L_APPRV_ROLE_ID
           FROM DUAL;
      EXCEPTION
         WHEN OTHERS
         THEN
            L_APPRV_ROLE_ID := NULL;
      END;

      BEGIN
         INSERT INTO CANON_E008_PROJ_APPRV_HIST_TBL (PROJECT_ID,
                                                     PROCESS_ID,
                                                     APPROVING_DEPARTMENT,
                                                     APPROVER_ROLE,
                                                     APPROVER_NAME,
                                                     APPROVAL_STATUS,
                                                     APPROVER_COMMENTS,
                                                     CREATED_BY,
                                                     CREATION_DATE,
                                                     LAST_UPDATE_BY,
                                                     LAST_UPDATE_DATE)
              VALUES (L_PROJECT_ID,
                      L_PROCESS_ID,
                      L_APPRV_DEPT,
                      L_APPRV_ROLE_ID,
                      APPROVER_NAME,
                      APPROVER_STATUS,
                      APPROVER_COMMENTS,
                      -1,
                      SYSDATE,
                      -1,
                      NULL);
      EXCEPTION
         WHEN OTHERS
         THEN
            INSERT_ERROR ('VALIDATE_UPLOAD_ITEMS- INSERT GT',
                          SUBSTR (SQLERRM, 1, 400),
                          L_ERRBUF,
                          L_RETCODE);
      END;

      /*
            IF APPROVER_STATUS = 'REJECTED'
            THEN
               UPDATE CANON_E008_PROJECT_WB_TBL
                  SET APPROVAL_STATUS = 'REJECTED', LAST_UPDATE_DATE = SYSDATE
                WHERE PROJECT_ID = L_PROJECT_ID;
            END IF;
      */
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         INSERT_ERROR ('INSERT_HISTORY',
                       SUBSTR (SQLERRM, 1, 400),
                       L_ERRBUF,
                       L_RETCODE);
   END INSERT_HISTORY;


   PROCEDURE ASSIGN_APPROVAL_GROUPS (P_PROJECT_ID IN NUMBER, P_PROCESS_ID IN NUMBER)
   IS
      L_ERRBUF    VARCHAR2 (200);
      L_RETCODE   VARCHAR2 (200);
   BEGIN
      IF P_PROJECT_ID IS NOT NULL
      THEN
         FOR ITEM_TYPE_ROLE
            IN (SELECT DISTINCT ROLE_NAME
                  FROM CANON_E008_PROJ_ITEM_TYPE_AP_V CDT         --Custom Code Table View
                 WHERE EXISTS
                          (SELECT ITEM_TYPE
                             FROM CANON_E008_PROJ_ITEMS_TBL PI, MDSE_ITEM_TP MI
                            WHERE     PROJECT_ID = P_PROJECT_ID
                                  AND PI.ITEM_TYPE = MI.MDSE_ITEM_TP_CD
                                  AND UPPER (MI.MDSE_ITEM_TP_NM) = UPPER (CDT.ITEM_TYPE)))
         LOOP
            INSERT_HISTORY (P_PROJECT_ID,
                            P_PROCESS_ID,
                            ITEM_TYPE_ROLE.ROLE_NAME,
                            NULL,
                            'PENDING',
                            NULL);
         END LOOP;

         FOR ITEM_TYPE_ROLE
            IN (SELECT DISTINCT ROLE_NAME
                  FROM CANON_E008_PROJ_ITEM_TYPE_AP_V CDT         --Custom Code Table View
                 WHERE ROLE_NAME NOT IN
                          (SELECT DISTINCT ROLE_NAME
                             FROM CANON_E008_PROJ_ITEM_TYPE_AP_V CDT --Custom Code Table View
                            WHERE EXISTS
                                     (SELECT ITEM_TYPE
                                        FROM CANON_E008_PROJ_ITEMS_TBL PI,
                                             MDSE_ITEM_TP MI
                                       WHERE     PROJECT_ID = P_PROJECT_ID
                                             AND PI.ITEM_TYPE = MI.MDSE_ITEM_TP_CD
                                             AND UPPER (MI.MDSE_ITEM_TP_NM) =
                                                    UPPER (CDT.ITEM_TYPE))))
         LOOP
            INSERT_HISTORY (P_PROJECT_ID,
                            P_PROCESS_ID,
                            ITEM_TYPE_ROLE.ROLE_NAME,
                            NULL,
                            'NOT REQUIRED',
                            NULL);
         END LOOP;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         INSERT_ERROR ('ASSIGN_APPROVAL_GROUPS',
                       SUBSTR (SQLERRM, 1, 400),
                       L_ERRBUF,
                       L_RETCODE);
   END ASSIGN_APPROVAL_GROUPS;

   PROCEDURE UPDATE_PROJECT_ITEMS_STATUS (P_PROJECT_ID   IN     NUMBER,
                                          P_ITEM_ID      IN     NUMBER,
                                          P_ERROR_CODE   IN     VARCHAR2,
                                          P_ERROR_MESS   IN     VARCHAR2,
                                          P_ERROR_FLAG      OUT VARCHAR2)
   IS
      L_ERRBUF          VARCHAR2 (200) := P_ERROR_CODE;
      L_RETCODE         VARCHAR2 (200);
      L_TOTAL_COUNT     NUMBER;
      L_SUCCESS_COUNT   NUMBER;
   BEGIN
      INSERT_ERROR ('UPDATE_PROJECT_ITEMS_STATUS',
                    'PROJECT_ID: ' || P_PROJECT_ID || ' ITEM_ID: ' || P_ITEM_ID,
                    L_ERRBUF,
                    L_RETCODE);


      UPDATE CANON_E008_PROJ_ITEMS_TBL
         SET PROCESS_FLAG = DECODE (P_ERROR_CODE, NULL, 'S', 'F'),
             PROCESS_MESSAGE =
                   TO_CHAR (SYSDATE, 'YYYY/MM/DD HH24:MI:SS')
                || '-'
                || P_ERROR_CODE
                || '--'
                || P_ERROR_MESS
                || CHR (13)
                || PROCESS_MESSAGE
       WHERE PROJECT_ID = P_PROJECT_ID AND PROJ_ITEM_ID = P_ITEM_ID;


      BEGIN
         SELECT COUNT (1)
           INTO L_TOTAL_COUNT
           FROM CANON_E008_PROJ_ITEMS_TBL
          WHERE PROJECT_ID = P_PROJECT_ID;

         SELECT COUNT (1)
           INTO L_SUCCESS_COUNT
           FROM CANON_E008_PROJ_ITEMS_TBL
          WHERE PROJECT_ID = P_PROJECT_ID AND NVL (PROCESS_FLAG, 'F') = 'S';

         IF L_TOTAL_COUNT = L_SUCCESS_COUNT
         THEN
            UPDATE CANON_E008_PROJECT_WB_TBL
               SET PROCESS_FLAG = 'S',
                   APPROVAL_STATUS = 'AWAITING PRICING',
                   BATCH_FIRST_DATE = TO_CHAR (SYSDATE, 'YYYY/MM/DD HH24:MI:SS')
             WHERE PROJECT_ID = P_PROJECT_ID;
         ELSE
            UPDATE CANON_E008_PROJECT_WB_TBL
               SET PROCESS_FLAG = 'F',
                   APPROVAL_STATUS = 'FAILED',
                   BATCH_FIRST_DATE = TO_CHAR (SYSDATE, 'YYYY/MM/DD HH24:MI:SS')
             WHERE PROJECT_ID = P_PROJECT_ID;
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            INSERT_ERROR (
               'UPDATE_PROJECT_ITEMS_STATUS INSIDE',
                  'PROJECT_ID: '
               || P_PROJECT_ID
               || ' ITEM_ID: '
               || P_ITEM_ID
               || SUBSTR (SQLERRM, 1, 400),
               L_ERRBUF,
               L_RETCODE);
            ROLLBACK;
      END;

      P_ERROR_FLAG := 'SUCCESS';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         P_ERROR_FLAG := 'FAILED';
         INSERT_ERROR ('UPDATE_PROJECT_ITEMS_STATUS',
                       SUBSTR (SQLERRM, 1, 400),
                       L_ERRBUF,
                       L_RETCODE);
         ROLLBACK;
   END UPDATE_PROJECT_ITEMS_STATUS;

   PROCEDURE VALIDATE_ITEM_CREATION (P_PROJECT_NO        IN     VARCHAR2,
                                     P_PROJECT_DEATILS      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG              OUT VARCHAR2)
   IS
      CURSOR C_PROJECT
      IS
         SELECT PROJECT_ID,
                PROJECT_DESC,
                PROJECT_REQUESTOR,
                PROJECT_NOTES,
                APPROVAL_STATUS,
                PROCESS_FLAG
           FROM CANON_E008_PROJECT_WB_TBL
          WHERE     APPROVAL_STATUS IN ('AWAITING PRICING', 'FAILED') --'PENDING ITEM CREATION')
                AND PROCESS_FLAG IS NOT NULL
                AND BATCH_FIRST_DATE IS NOT NULL
                AND BATCH_SECOND_DATE IS NULL
                AND PROJECT_ID = NVL (P_PROJECT_NO, PROJECT_ID);
   BEGIN
      -- DBMS_OUTPUT.PUT_LINE('INITIATE PROJECT_ID :' ||p_project_no);
      OPEN P_PROJECT_DEATILS FOR
         SELECT PROJECT_ID,
                PROJECT_DESC,
                PROJECT_REQUESTOR,
                PROJECT_NOTES,
                APPROVAL_STATUS,
                PROCESS_FLAG,
                DECODE(PROCESS_FLAG,'S','E008_PRC','F',(SELECT DECODE(PROJECT_OWNER,'ITEM MASTER - MERCH','E008_MERCH','ITEM MASTER - PARTS','E008_PART')
                                                          FROM CANON_E008_PROJ_CATG_DEF_V CDT                    --Custom Code Table View
                                                          WHERE CDT.PROJECT_CATEGORY = PROJECT_TYPE)) NOTIFY_ROLE
           FROM CANON_E008_PROJECT_WB_TBL
          WHERE     APPROVAL_STATUS IN ('AWAITING PRICING', 'FAILED') --'PENDING ITEM CREATION')
                AND PROCESS_FLAG IS NOT NULL
                AND BATCH_FIRST_DATE IS NOT NULL
                AND BATCH_SECOND_DATE IS NULL
                AND PROJECT_ID = NVL (P_PROJECT_NO, PROJECT_ID);

      -- DBMS_OUTPUT.PUT_LINE('INITIATE PROJECT_ID :' ||p_project_no);
      FOR CUR_PROJECT IN C_PROJECT
      LOOP
         UPDATE CANON_E008_PROJECT_WB_TBL
            SET BATCH_SECOND_DATE = TO_CHAR (SYSDATE, 'YYYY/MM/DD HH24:MI:SS')
          WHERE PROJECT_ID = CUR_PROJECT.PROJECT_ID;
      END LOOP;

      P_ERR_FLG := 'S';
      COMMIT;
   --DBMS_OUTPUT.PUT_LINE('DONE' );

   EXCEPTION
      WHEN OTHERS
      THEN
         --DBMS_OUTPUT.put_line ('XXX'  || SQLERRM);
         P_ERR_FLG := 'F';
         ROLLBACK;
   END VALIDATE_ITEM_CREATION;

   PROCEDURE CHECK_PRICING_ASSIGN (P_PROJECT_NO IN VARCHAR2, P_ERR_FLG OUT VARCHAR2)
   IS
      L_COUNT      NUMBER;
      L_PRC_CNT    NUMBER;
      L_PRC_FLAG   VARCHAR2 (1);

      CURSOR C_PROJECT
      IS
           SELECT PROJECT_ID,
                  PROJECT_DESC,
                  PROJECT_REQUESTOR,
                  PROJECT_NOTES,
                  APPROVAL_STATUS STATUS
             FROM CANON_E008_PROJECT_WB_TBL
            WHERE     PROJECT_ID = NVL (P_PROJECT_NO, PROJECT_ID)
                  AND APPROVAL_STATUS IN ('AWAITING PRICING')
                  AND BATCH_FIRST_DATE IS NOT NULL
                  AND BATCH_SECOND_DATE IS NOT NULL
         ORDER BY ROWID DESC, CREATION_DATE ASC;

      CURSOR C_PROJECT_ITEMS (L_PROJ_NO VARCHAR2)
      IS
         SELECT *
           FROM CANON_E008_PROJ_ITEMS_TBL
          WHERE PROJECT_ID = L_PROJ_NO AND PROCESS_FLAG = 'S';
   BEGIN
      -- DBMS_OUTPUT.PUT_LINE('INITIATE PROJECT_ID :' ||p_project_no);
      FOR CUR_PROJECT IN C_PROJECT
      LOOP
         FOR CUR_PROJECT_ITEMS IN C_PROJECT_ITEMS (CUR_PROJECT.PROJECT_ID)
         LOOP
         IF CUR_PROJECT_ITEMS.MSRP_COST IS NOT NULL  THEN
            SELECT SUM(CNT)
              INTO L_COUNT
            FROM (
            SELECT COUNT (PC.PRC_CATG_NM) CNT
              FROM PRC_CATG PC, PRC_LIST_EQUIP PL
             WHERE     PC.PRC_CATG_CD = PL.PRC_CATG_CD
                   --AND PRC_CATG_NM IN ('Floor Price List - CBS')
                   AND PRC_QLFY_VAL_TXT = TRIM(CUR_PROJECT_ITEMS.ITEM_NUMBER)
                   AND PC.GLBL_CMPY_CD = PL.GLBL_CMPY_CD
                   AND PC.EZCANCELFLAG = PL.EZCANCELFLAG
                   AND PC.GLBL_CMPY_CD = 'ADB'
                   AND PC.EZCANCELFLAG = '0'
                   AND PC.PRC_CATG_NM IN (SELECT PRICELIST_NAME
                                            FROM CANON_E008_PRICELIST_NAMES_V where owning_division = CUR_PROJECT_ITEMS.owning_division )
            UNION
            SELECT COUNT (PC.PRC_CATG_NM) CNT
              FROM S21_CSA_EXTN.PRC_CATG PC, S21_CSA_EXTN.PRC_LIST_EQUIP PL
                  ,S21_CSA_APPS.ORD_TAKE_MDSE OTM
             WHERE     PC.PRC_CATG_CD = PL.PRC_CATG_CD
                   --AND PRC_CATG_NM IN ('Floor Price List - CBS')
                   AND PRC_QLFY_VAL_TXT = SUBSTR(TRIM(CUR_PROJECT_ITEMS.ITEM_NUMBER), 1, 8)
                   AND PC.GLBL_CMPY_CD = PL.GLBL_CMPY_CD
                   AND PC.EZCANCELFLAG = PL.EZCANCELFLAG
                   AND PC.GLBL_CMPY_CD = OTM.GLBL_CMPY_CD
                   AND PC.EZCANCELFLAG = OTM.EZCANCELFLAG
                   AND PC.PRC_CATG_NM IN (SELECT PRICELIST_NAME
                                            FROM S21_CSA_EXTN.CANON_E008_PRICELIST_NAMES_V where owning_division = CUR_PROJECT_ITEMS.owning_division)
                   AND OTM.ORD_TAKE_MDSE_CD = SUBSTR(TRIM(CUR_PROJECT_ITEMS.ITEM_NUMBER), 1, 8)
				   AND PC.GLBL_CMPY_CD = 'ADB'
                   AND PC.EZCANCELFLAG = '0');

            SELECT COUNT (DISTINCT PRICELIST_NAME)
              INTO L_PRC_CNT
              FROM CANON_E008_PRICELIST_NAMES_V  where owning_division = CUR_PROJECT_ITEMS.owning_division;

        ELSE
            L_PRC_CNT := 0;
            L_COUNT := 0;
        END IF;

            IF L_COUNT = L_PRC_CNT
            THEN
               P_ERR_FLG := P_ERR_FLG || CUR_PROJECT_ITEMS.ITEM_NUMBER || '-S-';
               L_PRC_FLAG := 'S';
            ELSE
               P_ERR_FLG := P_ERR_FLG || CUR_PROJECT_ITEMS.ITEM_NUMBER || '-E-';
               L_PRC_FLAG := 'E';
            END IF;
         END LOOP;

         IF L_PRC_FLAG = 'S'
         THEN
            UPDATE CANON_E008_PROJECT_WB_TBL
               SET APPROVAL_STATUS = 'CLOSED'
             WHERE PROJECT_ID = CUR_PROJECT.PROJECT_ID;
         END IF;
      END LOOP;


      COMMIT;
   --DBMS_OUTPUT.PUT_LINE('DONE' );

   EXCEPTION
      WHEN OTHERS
      THEN
         --DBMS_OUTPUT.put_line ('XXX'  || SQLERRM);
         P_ERR_FLG := 'E';
   --ROLLBACK;
   END CHECK_PRICING_ASSIGN;

   PROCEDURE SAVE_PROJECT_SEARCH (P_PROJECT_NUMBER            NUMBER,
                                  P_PROJECT_NAME              VARCHAR2,
                                  P_PROJECT_CATEGORY          VARCHAR2,
                                  P_PROJECT_DESCRIPTION       VARCHAR2,
                                  P_MASTER_PROJECT            VARCHAR2,
                                  P_PROJECT_REQUESTOR         VARCHAR2,
                                  P_PROJECT_STATUS            VARCHAR2,
                                  P_REQ_DATE_FROM             DATE,
                                  P_REQ_DATE_TO               DATE,
                                  P_USER                      VARCHAR2,
                                  P_SAVED_NAME                VARCHAR2,
                                  P_ERR_FLG               OUT VARCHAR2,
                                  P_ERR_MSG               OUT VARCHAR2)
   IS
      L_ERR_MSG    VARCHAR2 (2000);
      L_INDEX      NUMBER;
      L_TEMPL_ID   NUMBER;
      L_ERRBUF     VARCHAR2 (200);
      L_RETCODE    VARCHAR2 (200);
   BEGIN
      INSERT INTO CANON_E008_PROJ_SEARCH_TBL (EZTABLEID,
                                              EZINCOMPANYCODE,
                                              USER_ID,
                                              SEARCH_NAME,
                                              PROJECT_NUMBER,
                                              PROJECT_NAME,
                                              PROJECT_CATEGORY,
                                              PROJECT_DESCRIPTION,
                                              PROJECT_STATUS,
                                              MASTER_PROJECT,
                                              PROJECT_REQUESTOR,
                                              REQ_DATE_FROM,
                                              REQ_DATE_TO,
                                              CREATED_BY,
                                              CREATION_DATE,
                                              LAST_UPDATE_BY,
                                              LAST_UPDATE_DATE)
           VALUES ('CANON_E008_PROJ_SEARCH_TBL',
                   'ADB',
                   P_USER,
                   P_SAVED_NAME,
                   P_PROJECT_NUMBER,
                   P_PROJECT_NAME,
                   P_PROJECT_CATEGORY,
                   P_PROJECT_DESCRIPTION,
                   P_PROJECT_STATUS,
                   P_MASTER_PROJECT,
                   P_PROJECT_REQUESTOR,
                   P_REQ_DATE_FROM,
                   P_REQ_DATE_TO,
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
         INSERT_ERROR ('SAVE_PROJECT_SEARCH INSERT',
                       SUBSTR (SQLERRM, 1, 400),
                       L_ERRBUF,
                       L_RETCODE);
   END SAVE_PROJECT_SEARCH;

   PROCEDURE GET_SAVEDSEARCH_LIST (P_USER                   IN     VARCHAR2,
                                   P_SAVEDSEARCH_LIST_TBL      OUT G_REF_CUR_TYP,
                                   P_ERR_FLG                   OUT VARCHAR2,
                                   P_ERR_MSG                   OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_SAVEDSEARCH_LIST_TBL FOR
           SELECT SEARCH_NAME
             FROM CANON_E008_PROJ_SEARCH_TBL
            WHERE USER_ID = P_USER
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
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT TO_CHAR (PROJECT_NUMBER)
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT PROJECT_NAME
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT PROJECT_CATEGORY
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT PROJECT_DESCRIPTION
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT MASTER_PROJECT
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT PROJECT_REQUESTOR
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT PROJECT_STATUS
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT TO_CHAR (REQ_DATE_FROM)
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT TO_CHAR (REQ_DATE_TO)
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER
            UNION ALL
            SELECT '-'
              FROM CANON_E008_PROJ_SEARCH_TBL
             WHERE SEARCH_NAME = P_SAVEDSEARCH_NAME AND USER_ID = P_USER;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_SAVEDSEARCH_VALUES;

   PROCEDURE GET_COMPONENT_LIST (P_COMPONENT        IN     VARCHAR2,
                                 P_COMPONENT_LIST      OUT G_REF_CUR_TYP,
                                 P_ERR_FLG             OUT VARCHAR2,
                                 P_ERR_MSG             OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_COMPONENT_LIST FOR
         SELECT DISTINCT ITEM_NUMBER
           FROM CANON_E008_PROJ_ITEMS_TBL PIT
          WHERE     PROCESS_FLAG IS NULL
                AND ITEM_NUMBER LIKE '%' || TRIM (P_COMPONENT) || '%'
                AND EXISTS
                       (SELECT MDSE_ITEM_TP_CD
                          FROM MDSE_ITEM_TP MIT
                         WHERE     GLBL_CMPY_CD = 'ADB'
                               AND EZCANCELFLAG = '0'
                               AND MDSE_ITEM_TP_NM IN ('Machine',
                                                       'Accessory',
                                                       'Parts',
                                                       'Supply',
                                                       'Set',
                                                       'Kit')
                               AND MIT.MDSE_ITEM_TP_CD = PIT.ITEM_TYPE)
         UNION
         SELECT MDSE_CD ITEM_NUMBER
           FROM ALL_MDSE_V A
          WHERE A.MDSE_CD LIKE '%' || TRIM (P_COMPONENT) || '%'
         ORDER BY ITEM_NUMBER;
   /*
   SELECT DMI.MDSE_CD
     FROM MDSE DMI, MDSE_ITEM_TP MIT
    WHERE     DMI.MDSE_ITEM_TP_CD = MIT.MDSE_ITEM_TP_CD
          AND MIT.MDSE_ITEM_TP_NM IN ('Machine', 'Accessory', 'Parts','Supply','Set','Kit')
          AND DMI.GLBL_CMPY_CD = 'ADB'
          AND DMI.EZCANCELFLAG = '0'
          AND DMI.MDSE_CD LIKE '%' || TRIM (P_COMPONENT) || '%'
   ORDER BY ITEM_NUMBER;  */
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_COMPONENT_LIST;

   PROCEDURE GET_COMP_ITEM_DETAILS (P_COMPONENT_NAME          VARCHAR2,
                                    P_COMP_ITEM_DETAILS   OUT G_REF_CUR_TYP,
                                    P_ERR_FLG             OUT VARCHAR2,
                                    P_ERR_MSG             OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      IF P_COMPONENT_NAME IS NOT NULL
      THEN
         OPEN P_COMP_ITEM_DETAILS FOR
            SELECT Y, X
              FROM (SELECT MDSE_CD X, 1 Y
                      FROM ALL_MDSE_V                                       --MDSE
                     WHERE     MDSE_CD = P_COMPONENT_NAME
                           AND GLBL_CMPY_CD = 'ADB'
                           AND EZCANCELFLAG = '0'
                    UNION
                    SELECT MDSE_NM X, 2 Y
                      FROM ALL_MDSE_V                                               --MDSE
                     WHERE     MDSE_CD = P_COMPONENT_NAME
                           AND GLBL_CMPY_CD = 'ADB'
                           AND EZCANCELFLAG = '0'
                    UNION
                    SELECT MIM.MDSE_ITEM_MNF_NM X, 3 Y
                      FROM ALL_MDSE_V DMI,                             --MDSE DMI,
                                          MDSE_ITEM_MNF MIM
                     WHERE     DMI.MDSE_ITEM_MNF_CD = MIM.MDSE_ITEM_MNF_CD(+)
                           AND DMI.GLBL_CMPY_CD = MIM.GLBL_CMPY_CD(+)
                           AND DMI.EZCANCELFLAG = MIM.EZCANCELFLAG(+)
                           AND DMI.GLBL_CMPY_CD = 'ADB'
                           AND DMI.EZCANCELFLAG = '0'
                           AND DMI.MDSE_CD = P_COMPONENT_NAME
                    UNION
                    SELECT CMT.COA_PROJ_DESC_TXT X, 4 Y
                      FROM ALL_MDSE_V DMI,                             --MDSE DMI,
                                          COA_PROJ CMT
                     WHERE     DMI.COA_MDSE_TP_CD = CMT.COA_PROJ_CD
                           AND DMI.GLBL_CMPY_CD = CMT.GLBL_CMPY_CD
                           AND DMI.EZCANCELFLAG = CMT.EZCANCELFLAG
                           AND DMI.GLBL_CMPY_CD = 'ADB'
                           AND DMI.EZCANCELFLAG = '0'
                           AND DMI.MDSE_CD = P_COMPONENT_NAME
                    /*SELECT CMT.COA_MDSE_TP_NM X, 4 Y
                      FROM ALL_MDSE_V DMI,--MDSE DMI,
                            COA_MDSE_TP CMT
                     WHERE     DMI.COA_MDSE_TP_CD = CMT.COA_MDSE_TP_CD
                           AND DMI.GLBL_CMPY_CD = CMT.GLBL_CMPY_CD
                           AND DMI.EZCANCELFLAG = CMT.EZCANCELFLAG
                           AND DMI.GLBL_CMPY_CD = 'ADB'
                           AND DMI.EZCANCELFLAG = '0'
                           AND DMI.MDSE_CD = P_COMPONENT_NAME*/
                    UNION
                    SELECT CA.COA_PROD_NM X, 5 Y
                      FROM ALL_MDSE_V ITEM,                                   --MDSE ITEM,
                                           COA_PROD CA
                     WHERE     ITEM.COA_PROD_CD = CA.COA_PROD_CD
                           AND ITEM.GLBL_CMPY_CD = CA.GLBL_CMPY_CD
                           AND ITEM.EZCANCELFLAG = CA.EZCANCELFLAG
                           AND ITEM.GLBL_CMPY_CD = 'ADB'
                           AND ITEM.EZCANCELFLAG = '0'
                           AND ITEM.MDSE_CD = P_COMPONENT_NAME
                    UNION
                    SELECT MDSE_ITEM_TP_NM X, 6 Y
                      FROM ALL_MDSE_V DMI,                             --MDSE DMI,
                                          MDSE_ITEM_TP MIT
                     WHERE     DMI.MDSE_ITEM_TP_CD = MIT.MDSE_ITEM_TP_CD
                           AND DMI.GLBL_CMPY_CD = MIT.GLBL_CMPY_CD
                           AND DMI.EZCANCELFLAG = MIT.EZCANCELFLAG
                           AND DMI.GLBL_CMPY_CD = 'ADB'
                           AND DMI.EZCANCELFLAG = '0'
                           AND DMI.MDSE_CD = P_COMPONENT_NAME
                    UNION
                    SELECT TO_CHAR (THIS_MTH_TOT_STD_COST_AMT) X, 7 Y
                      FROM ALL_MDSE_V                                               --MDSE
                     WHERE     MDSE_CD = P_COMPONENT_NAME
                           AND GLBL_CMPY_CD = 'ADB'
                           AND EZCANCELFLAG = '0')
            UNION
            SELECT Y, X
              FROM (SELECT ITEM_NUMBER X, 1 Y
                      FROM CANON_E008_PROJ_ITEMS_TBL
                     WHERE     ITEM_NUMBER = P_COMPONENT_NAME
                           AND NVL (PROCESS_FLAG, 'X') <> 'S'
                    UNION
                    SELECT ITEM_DESCRIPTION X, 2 Y
                      FROM CANON_E008_PROJ_ITEMS_TBL
                     WHERE     ITEM_NUMBER = P_COMPONENT_NAME
                           AND NVL (PROCESS_FLAG, 'X') <> 'S'
                    UNION
                    SELECT MIM.MDSE_ITEM_MNF_NM X, 3 Y
                      FROM CANON_E008_PROJ_ITEMS_TBL EPI, MDSE_ITEM_MNF MIM
                     WHERE     ITEM_NUMBER = P_COMPONENT_NAME
                           AND EPI.MANUFACTURER = MIM.MDSE_ITEM_MNF_CD
                           AND MIM.GLBL_CMPY_CD = 'ADB'
                           AND MIM.EZCANCELFLAG = '0'
                           AND NVL (PROCESS_FLAG, 'X') <> 'S'
                    UNION
                    SELECT COA_PROJ_NM X, 4 Y
                      FROM CANON_E008_PROJ_ITEMS_TBL EPI, COA_PROJ CP
                     WHERE     ITEM_NUMBER = P_COMPONENT_NAME
                           AND EPI.MERCHANDISE_TYPE = CP.COA_PROJ_CD
                           AND CP.GLBL_CMPY_CD = 'ADB'
                           AND CP.EZCANCELFLAG = '0'
                           AND NVL (PROCESS_FLAG, 'X') <> 'S'
                    UNION
                    SELECT CA.COA_PROD_NM X, 5 Y
                      FROM CANON_E008_PROJ_ITEMS_TBL EPI, COA_PROD CA
                     WHERE     ITEM_NUMBER = P_COMPONENT_NAME
                           AND EPI.PRODUCT_CODE = CA.COA_PROD_CD
                           AND CA.GLBL_CMPY_CD = 'ADB'
                           AND CA.EZCANCELFLAG = '0'
                           AND NVL (PROCESS_FLAG, 'X') <> 'S'
                    UNION
                    SELECT MIM.MDSE_ITEM_TP_NM X, 6 Y
                      FROM CANON_E008_PROJ_ITEMS_TBL EPI, MDSE_ITEM_TP MIM
                     WHERE     ITEM_NUMBER = P_COMPONENT_NAME
                           AND EPI.ITEM_TYPE = MIM.MDSE_ITEM_TP_CD
                           AND MIM.GLBL_CMPY_CD = 'ADB'
                           AND MIM.EZCANCELFLAG = '0'
                           AND NVL (PROCESS_FLAG, 'X') <> 'S'
                    UNION
                    SELECT STANDARD_COST X, 7 Y
                      FROM CANON_E008_PROJ_ITEMS_TBL
                     WHERE     ITEM_NUMBER = P_COMPONENT_NAME
                           AND NVL (PROCESS_FLAG, 'X') <> 'S')
            ORDER BY 1;
      END IF;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_COMP_ITEM_DETAILS;

   PROCEDURE SAVE_BOM_ITEMS (P_BOM_ITEMS_TBL   IN     CANON_E008_BOM_ITEMS_TBL_TYPE,
                             P_ERR_FLG            OUT VARCHAR2,
                             P_ERR_MSG            OUT VARCHAR2)
   IS
      L_INDEX     NUMBER;
      L_ERR_MSG   VARCHAR2 (2000);
      L_EXISTS    VARCHAR2 (1);
      L_ERRBUF    VARCHAR2 (200);
      L_RETCODE   VARCHAR2 (200);
   BEGIN
      L_INDEX := P_BOM_ITEMS_TBL.FIRST;

      IF L_INDEX IS NOT NULL
      THEN
         BEGIN
            DELETE FROM CANON_E008_BOM_ASSIGN_TBL
                  WHERE SET_ITEM_ID = P_BOM_ITEMS_TBL (L_INDEX).SET_ITEM_ID;
         EXCEPTION
            WHEN OTHERS
            THEN
               INSERT_ERROR ('SAVE_BOM_ITEMS Delete Exception',
                             '2',
                             P_ERR_FLG,
                             P_ERR_MSG);
         END;
      END IF;

      WHILE L_INDEX IS NOT NULL
      LOOP
         BEGIN
            INSERT INTO CANON_E008_BOM_ASSIGN_TBL (EZTABLEID,
                                                   EZINCOMPANYCODE,
                                                   SET_ITEM_ID,
                                                   BOM_INSTRUCTIONS,
                                                   COMPONENT_ITEM,
                                                   QTY,
                                                   CREATED_BY,
                                                   CREATION_DATE,
                                                   LAST_UPDATE_BY,
                                                   LAST_UPDATE_DATE)
                 VALUES ('CANON_E008_BOM_ASSIGN_TBL',
                         'ADB',
                         TRIM (P_BOM_ITEMS_TBL (L_INDEX).SET_ITEM_ID),
                         P_BOM_ITEMS_TBL (L_INDEX).BOM_INSTRUCTIONS,
                         TRIM (P_BOM_ITEMS_TBL (L_INDEX).COMPONENT_ITEM),
                         P_BOM_ITEMS_TBL (L_INDEX).QTY,
                         P_BOM_ITEMS_TBL (L_INDEX).CREATED_BY,
                         SYSDATE,
                         P_BOM_ITEMS_TBL (L_INDEX).LAST_UPDATE_BY,
                         SYSDATE);
         EXCEPTION
            WHEN OTHERS
            THEN
               INSERT_ERROR ('SAVE_BOM_ITEMS INSERT',
                             SUBSTR (SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);
         END;

         L_INDEX := P_BOM_ITEMS_TBL.NEXT (L_INDEX);
      END LOOP;


      P_ERR_FLG := 'S';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   --COMMIT;
   END SAVE_BOM_ITEMS;

   PROCEDURE GET_COMP_ITEMS (P_SET_ITEM_ID       NUMBER,
                             P_COMP_ITEMS    OUT G_REF_CUR_TYP,
                             P_ERR_FLG       OUT VARCHAR2,
                             P_ERR_MSG       OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_COMP_ITEMS FOR
         SELECT SET_ITEM_ID,
                BOM_INSTRUCTIONS,
                COMPONENT_ITEM,
                DMI.MDSE_NM COMPONENT_DESCRIPTION,
                (SELECT MDSE_ITEM_MNF_NM
                   FROM MDSE_ITEM_MNF
                  WHERE     MDSE_ITEM_MNF_CD = DMI.MDSE_ITEM_MNF_CD
                        AND GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0')
                   MANUFACTURER,
                QTY,
                (SELECT COA_PROJ_NM
                   FROM COA_PROJ
                  WHERE     COA_PROJ_CD = DMI.COA_MDSE_TP_CD
                        AND GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0')
                   MERCH_TYPE,
                (SELECT COA_PROD_NM
                   FROM COA_PROD
                  WHERE     COA_PROD_CD = DMI.COA_PROD_CD
                        AND GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0')
                   PROD_CODE,
                (SELECT MDSE_ITEM_TP_NM
                   FROM MDSE_ITEM_TP
                  WHERE     MDSE_ITEM_TP_CD = DMI.MDSE_ITEM_TP_CD
                        AND GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0')
                   ITEM_TYPE,
                TO_CHAR (DMI.THIS_MTH_TOT_STD_COST_AMT) COST,
                CREATED_BY,
                CREATION_DATE,
                LAST_UPDATE_BY,
                LAST_UPDATE_DATE
           FROM CANON_E008_BOM_ASSIGN_TBL BOM, MDSE DMI
          WHERE  	DMI.MDSE_CD = BOM.COMPONENT_ITEM
                AND DMI.GLBL_CMPY_CD = 'ADB'
                AND DMI.EZCANCELFLAG = '0'
                AND BOM.SET_ITEM_ID = P_SET_ITEM_ID
         UNION
         SELECT SET_ITEM_ID,
                BOM_INSTRUCTIONS,
                COMPONENT_ITEM,
                PIT.ITEM_DESCRIPTION COMPONENT_DESCRIPTION,
                (SELECT MDSE_ITEM_MNF_NM
                   FROM MDSE_ITEM_MNF
                  WHERE     MDSE_ITEM_MNF_CD = PIT.MANUFACTURER
                        AND GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0')
                   MANUFACTURER,
                QTY,
                (SELECT COA_PROJ_NM
                   FROM COA_PROJ
                  WHERE     COA_PROJ_CD = PIT.MERCHANDISE_TYPE
                        AND GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0')
                   MERCH_TYPE,
                (SELECT COA_PROD_NM
                   FROM COA_PROD
                  WHERE     COA_PROD_CD = PIT.PRODUCT_CODE
                        AND GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0')
                   PROD_CODE,
                (SELECT MDSE_ITEM_TP_NM
                   FROM MDSE_ITEM_TP
                  WHERE     MDSE_ITEM_TP_CD = PIT.ITEM_TYPE
                        AND GLBL_CMPY_CD = 'ADB'
                        AND EZCANCELFLAG = '0')
                   ITEM_TYPE,
                PIT.STANDARD_COST COST,
                PIT.CREATED_BY,
                PIT.CREATION_DATE,
                PIT.LAST_UPDATE_BY,
                PIT.LAST_UPDATE_DATE
           FROM CANON_E008_BOM_ASSIGN_TBL BOM, CANON_E008_PROJ_ITEMS_TBL PIT
          WHERE     1 = 1
                AND PIT.ITEM_NUMBER = BOM.COMPONENT_ITEM
                --AND PIT.PROJ_ITEM_ID = BOM.SET_ITEM_ID
                AND BOM.SET_ITEM_ID = P_SET_ITEM_ID
                AND NOT EXISTS
                       (SELECT MDSE_CD
                          FROM MDSE
                         WHERE     MDSE_CD = BOM.COMPONENT_ITEM
                               AND GLBL_CMPY_CD = 'ADB'
                               AND EZCANCELFLAG = '0')
         ORDER BY COMPONENT_ITEM;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         INSERT_ERROR ('GET_COMP_ITEMS',
                       'P_SET_ITEM_ID ' || P_SET_ITEM_ID,
                       P_ERR_FLG,
                       P_ERR_MSG);
   END GET_COMP_ITEMS;

   PROCEDURE GET_COMP_ITEMS_DETAILS (P_PROJECT_NO     IN     NUMBER,
                                     P_SET_ITEM_ID    IN     NUMBER,
                                     P_SET_ITEM_TBL      OUT G_REF_CUR_TYP,
                                     P_ERR_FLG           OUT VARCHAR2,
                                     P_ERR_MSG           OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_SET_ITEM_TBL FOR
         SELECT PI.ITEM_NUMBER PARENT_ITEM,
                DECODE (PI.ITEM_TYPE,  10, 1,  11, 3)    ----1 -'SET MDSE'  3 - 'KIT MDSE'
                                                     CODE_ORGANIZATION_LEVEL,
                BOM.COMPONENT_ITEM,
                BOM.QTY,
                PI.UNIT_OF_MEASURE UOM
           FROM CANON_E008_PROJ_ITEMS_TBL PI, CANON_E008_BOM_ASSIGN_TBL BOM
          WHERE     1 = 1
                AND PROJECT_ID = P_PROJECT_NO
                AND BOM.SET_ITEM_ID = PI.PROJ_ITEM_ID
                AND BOM.SET_ITEM_ID = P_SET_ITEM_ID;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_COMP_ITEMS_DETAILS;

   PROCEDURE GET_ASL_NAMES (P_MERCH_TYPE   IN     VARCHAR2,
                            P_SUPPLIER_CODE IN VARCHAR2,
                            P_SUPPLIER_SITE_CODE IN VARCHAR2,
                            P_ASL_NAME        OUT VARCHAR2,
                            P_ERR_FLG         OUT VARCHAR2,
                            P_ERR_MSG         OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
      l_COUNT     NUMBER;
   BEGIN

      /*SELECT DISTINCT ASL_NAME
        INTO P_ASL_NAME
        FROM CANON_E008_ASL_NAME_V                                --Custom Code Table View
       WHERE MERCH_TYPE = P_MERCH_TYPE
         AND   SUPPLIER_CODE = NVL(P_SUPPLIER_CODE,SUPPLIER_CODE); */

      /*   SELECT COUNT(1)
         INTO l_COUNT
         FROM ASL_HDR
         WHERE PRNT_VND_CD = P_SUPPLIER_CODE;  */

        SELECT  COUNT(1)
        INTO l_COUNT
        FROM
            ( SELECT
                    a.asl_nm,
                    COUNT(1)
                FROM
                    s21_csa_apps.asl_hdr a,
                    s21_csa_apps.asl_dtl b
                WHERE
                    a.asl_hdr_pk = b.asl_hdr_pk
                    AND a.prnt_vnd_cd = P_SUPPLIER_CODE
                    AND b.vnd_cd = P_SUPPLIER_SITE_CODE
                GROUP BY
                    a.asl_nm
                ORDER BY
                    COUNT(1) DESC
            )
        WHERE ROWNUM = 1;

         IF l_COUNT = 0 THEN
            SELECT PRNT_VND_NM
            INTO P_ASL_NAME
            FROM PRNT_VND
            WHERE PRNT_VND_CD = P_SUPPLIER_CODE
            AND EZCANCELFLAG = '0'
            AND GLBL_CMPY_CD = 'ADB';
         ELSE
          /*   SELECT ASL_NM
             INTO P_ASL_NAME
             FROM ASL_HDR
             WHERE PRNT_VND_CD = P_SUPPLIER_CODE
               AND EZCANCELFLAG = '0'
               AND GLBL_CMPY_CD = 'ADB';  */

                SELECT  asl_nm
                INTO P_ASL_NAME
                FROM
                    ( SELECT
                            a.asl_nm,
                            COUNT(1)
                        FROM
                            s21_csa_apps.asl_hdr a,
                            s21_csa_apps.asl_dtl b
                        WHERE
                            a.asl_hdr_pk = b.asl_hdr_pk
                            AND a.prnt_vnd_cd = P_SUPPLIER_CODE
                            AND b.vnd_cd = P_SUPPLIER_SITE_CODE
                        GROUP BY
                            a.asl_nm
                        ORDER BY
                            COUNT(1) DESC
                    )
                WHERE ROWNUM = 1;

         END IF;

   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SQLERRM;
         P_ASL_NAME := NULL;
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_ASL_NAMES;

   PROCEDURE GET_PRICELIST_NAMES (P_OWNING_DIVISION  IN  VARCHAR2,
                                  P_PRICE_LIST_TBL   OUT G_REF_CUR_TYP,
                                  P_ERR_FLG          OUT VARCHAR2,
                                  P_ERR_MSG          OUT VARCHAR2)
   IS
      L_ERR_MSG   VARCHAR2 (2000);
   BEGIN
      OPEN P_PRICE_LIST_TBL FOR
           SELECT PC.PRC_CATG_CD
             FROM PRC_CATG PC, PRC_CATG DPC, PRC_LIST_TP PLT, CANON_E008_PRICELIST_NAMES_V PCT
            WHERE     DPC.PRC_LIST_TP_CD = PLT.PRC_LIST_TP_CD
                  AND DPC.PRC_CATG_CD = PC.PRC_CATG_CD
                  AND PLT.FL_LIST_PRC_FLG = 'Y'
                  AND DPC.ACTV_FLG = 'Y'
                  AND PLT.PRC_LIST_TP_CD IN ('01', '10') --  10   MSRP    and    01    Equipment Floor
                  AND DPC.GLBL_CMPY_CD = PC.GLBL_CMPY_CD
                  AND PLT.GLBL_CMPY_CD = PC.GLBL_CMPY_CD
                  AND DPC.EZCANCELFLAG = PC.EZCANCELFLAG
                  AND PLT.EZCANCELFLAG = PC.EZCANCELFLAG
                  AND PC.GLBL_CMPY_CD = 'ADB'
                  AND PC.EZCANCELFLAG = '0'
                  AND NVL (TO_DATE (DPC.EFF_FROM_DT, 'RRRRMMDD'), TRUNC (SYSDATE - 1)) <=
                         TRUNC (SYSDATE)
                  AND NVL (TO_DATE (DPC.EFF_THRU_DT, 'RRRRMMDD'), TRUNC (SYSDATE + 1)) >=
                         TRUNC (SYSDATE)
                  AND PCT.PRICELIST_NAME = PC.PRC_CATG_NM
                  AND PCT.OWNING_DIVISION IN (P_OWNING_DIVISION,'ALL')
         ORDER BY PCT.OWNING_DIVISION ASC;

         /*  SELECT PC.PRC_CATG_CD
             FROM PRC_CATG PC, PRC_CATG DPC, PRC_LIST_TP PLT
            WHERE     DPC.PRC_LIST_TP_CD = PLT.PRC_LIST_TP_CD
                  AND DPC.PRC_CATG_CD = PC.PRC_CATG_CD
                  AND PLT.FL_LIST_PRC_FLG = 'Y'
                  AND DPC.ACTV_FLG = 'Y'
                  AND PLT.PRC_LIST_TP_CD IN ('01', '10') --  10   MSRP    and    01    Equipment Floor
                  AND DPC.GLBL_CMPY_CD = PC.GLBL_CMPY_CD
                  AND PLT.GLBL_CMPY_CD = PC.GLBL_CMPY_CD
                  AND DPC.EZCANCELFLAG = PC.EZCANCELFLAG
                  AND PLT.EZCANCELFLAG = PC.EZCANCELFLAG
                  AND PC.GLBL_CMPY_CD = 'ADB'
                  AND PC.EZCANCELFLAG = '0'
                  AND NVL (TO_DATE (DPC.EFF_FROM_DT, 'RRRRMMDD'), TRUNC (SYSDATE - 1)) <=
                         TRUNC (SYSDATE)
                  AND NVL (TO_DATE (DPC.EFF_THRU_DT, 'RRRRMMDD'), TRUNC (SYSDATE + 1)) >=
                         TRUNC (SYSDATE)
                  AND EXISTS
                         (SELECT PRICELIST_NAME
                            FROM CANON_E008_PRICELIST_NAMES_V PCT --Custom Code Table View
                           WHERE PCT.PRICELIST_NAME = PC.PRC_CATG_NM)
         ORDER BY PLT.PRC_LIST_TP_CD DESC; */

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_PRICELIST_NAMES;

   PROCEDURE UPLOAD_PROJECT_ITEMS (P_PROJECT_REC   IN     CANON_E008_PROJECT_REC,
                                   P_ITEM_TBL      IN     CANON_E008_PROJ_ITEMS_TBL_TYPE,
                                   P_PROJECT_NO       OUT NUMBER,
                                   P_ERR_FLG          OUT VARCHAR2,
                                   P_ERR_MSG          OUT VARCHAR2)
   IS
      CURSOR CUR_DEFAULT_ATTR (
         C_TEMPLATE_ID    NUMBER)
      IS
         SELECT TAA.ATTRIBUTE_ID, TAA.DEFAULT_VALUE, TAT.E008_WB_TBL_MAP MAP_COL
           FROM CANON_E008_TEMPL_ATTR_ASGN_TBL TAA, CANON_E008_TEMPLATE_ATTR_TBL TAT
          WHERE     1 = 1
                AND TAA.TEMPLATE_ID = C_TEMPLATE_ID
                AND TAT.ATTRIBUTE_ID = TAA.ATTRIBUTE_ID
                --AND WORKBENCH_DISPLAY = 'NONE'
                AND TAT.ENABLE_FLAG = 'Y'
                AND DEFAULT_VALUE IS NOT NULL;

      CURSOR CUR_LOV_ATTR (
         C_TEMPLATE_ID    NUMBER)
      IS
           SELECT ATC.COLUMN_NAME MAP_COL,
                  SUBSTR (TAT.E008_WB_TBL_MAP, 1, 25) || '_LIST' MAP_COL_LIST,
                  TAT.ATTRIBUTE_NAME,
                  TAA.REQUIRED_VALUE,
                  TAA.VALID,
                  TAT.WORKBENCH_DISPLAY,
                  TAT.LOV_FLAG
             FROM ALL_TAB_COLS ATC,
                  CANON_E008_TEMPL_ATTR_ASGN_TBL TAA,
                  CANON_E008_TEMPLATE_ATTR_TBL TAT
            WHERE     TABLE_NAME = 'CANON_E008_PROJ_ITEMS_TBL'
                  AND ATC.COLUMN_NAME = TAT.E008_WB_TBL_MAP
                  AND TAA.TEMPLATE_ID = C_TEMPLATE_ID
                  AND TAT.ATTRIBUTE_ID = TAA.ATTRIBUTE_ID
                  AND TAT.ENABLE_FLAG = 'Y'
                  AND TAT.LOV_FLAG = 'Y'
         ORDER BY COLUMN_NAME;

      L_INDEX               NUMBER;
      L_PROJECT_ID          NUMBER;
      L_PROJ_ITEM_ID        NUMBER;
      L_ERR_MSG             VARCHAR2 (2000);
      L_PROJ_ITEMS          VARCHAR2 (32000);
      L_DYN_SQL             VARCHAR2 (32000);
      L_TEMPLATE_ID         NUMBER;
      L_ERR_FLG             VARCHAR2 (100);
      L_DYN_UPD_SQL         VARCHAR2 (32000);
      L_DYN_LOV_SQL         VARCHAR2 (32000);
      L_TEMPLATE_LIST_TBL   G_REF_CUR_TYP;
      L_LIST_CODE           VARCHAR2 (4000);
      L_LIST_CODE1          VARCHAR2 (4000);
      L_LIST_NAME           VARCHAR2 (4000);
      L_COL_VALUE           VARCHAR2 (2000);
      L_ERRBUF              VARCHAR2 (200);
      L_RETCODE             VARCHAR2 (200);

      L_SUPPLIER_CODE       VARCHAR2 (200);
   BEGIN
      L_PROJECT_ID := P_PROJECT_REC.PROJECT_ID;
      P_PROJECT_NO := L_PROJECT_ID;

      L_INDEX := P_ITEM_TBL.FIRST;

      WHILE L_INDEX IS NOT NULL
      LOOP
         BEGIN
            SELECT TEMPLATE_ID
              INTO L_TEMPLATE_ID
              FROM CANON_E008_TEMPLATE_HDR_TBL TH
             WHERE TH.TEMPLATE_NAME = (P_ITEM_TBL (L_INDEX).TEMPLATE_NAME);
         EXCEPTION
            WHEN OTHERS
            THEN
               L_TEMPLATE_ID := NULL;
         END;

         BEGIN
            IF P_ITEM_TBL (L_INDEX).PROJ_ITEM_ID IS NULL
            THEN
               BEGIN
                  SELECT CANON_E008_PROJ_ITEM_ID_SEQ.NEXTVAL
                    INTO L_PROJ_ITEM_ID
                    FROM DUAL;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     NULL;
               END;

               INSERT INTO CANON_E008_PROJ_ITEMS_TBL (EZTABLEID,
                                                      COMPANY_CODE,
                                                      PROJECT_ID,
                                                      TEMPLATE_ID,
                                                      PROJ_ITEM_ID,
                                                      ACCOUNTING_RULES,
                                                      ACCRUAL,
                                                      ASSEMBLED_COUNTRY,
                                                      ASSET_RECOVERY_COST,
                                                      ASSURANCE_POINTS_MIN,
                                                      ASSURANCE_POINTS_MAX,
                                                      ASSURANCE_POINTS_PER_LICENSE,
                                                      ASSURANCE_PTS_FIXED_PERORD_QTY,
                                                      BUNDLED_MAINTENANCE_ITEM,
                                                      COMMISSIONS_GROUP,
                                                      COST_OF_GOODS,
                                                      COSTED,
                                                      COVERAGE_BASE_TYPE,
                                                      COVERAGE_TYPE,
                                                      CRITICALITY,
                                                      CUSTOMER_ORDEREABLE,
                                                      DEFAULT_SRC_SUB_WH,
                                                      DEFAULT_SRC_WH,
                                                      ITEM_DEPTH,
                                                      ELAN_CONTROLLED,
                                                      EXPENSE,
                                                      EXTENDED_MAINT_POP_AVAILABLE,
                                                      FIXED_NO_OF_SEATS,
                                                      HEIGHT,
                                                      IMAGEWARE_REMOTE_ITEM,
                                                      IMAGEWARE_REMOTE_ENABLED,
                                                      IMAGEWARE_REMOTE_MODEL,
                                                      INSTALLBASE_CONTROLLED,
                                                      INVENTORY_TRACKABLE,
                                                      INVOICEABLE,
                                                      INVOICING_RULES,
                                                      ITEM_CLASSIFICATION,
                                                      ITEM_DESCRIPTION,
                                                      ITEM_NUMBER,
                                                      ITEM_TYPE,
                                                      ITEM_LENGTH,
                                                      LICENSE_CONTROL,
                                                      LONG_DESCRIPTION,
                                                      MAIN_ENGINE,
                                                      MAINTENANCE_ITEM_TERM,
                                                      MAINTENANCE_ITEM_TYPE,
                                                      MAINTENANCE_POP_AVALIABLE,
                                                      MANUFACTURED_COUNTRY,
                                                      MANUFACTURER,
                                                      MANUFACTURER_ITEM_NUMBER,
                                                      MARKETING_MODEL,
                                                      MARKETING_SEGMENT,
                                                      MATERIAL_GROUP1,
                                                      MATERIAL_GROUP2,
                                                      MATERIAL_GROUP3,
                                                      MAXIMUM_ORDER_QUANTITY,
                                                      MERCHANDISE_TYPE,
                                                      METERED_MACHINE,
                                                      MINIMUM_ORDER_QUANTITY,
                                                      NMFC_CLASS,
                                                      ORDER_INCREMENTS,
                                                      OWNING_DIVISION,
                                                      PART_TYPE,
                                                      PART_YIELD_DAYS,
                                                      PART_YIELD_OUTPUTS,
                                                      PARTS_DROPSHIP_DISABLED,
                                                      PARTS_SURVEY_REQUIRED,
                                                      PLANNING_GROUP,
                                                      PRIVATE_LABEL_FLAG,
                                                      PRODUCT_CODE,
                                                      PRODUCT_LEVEL1,
                                                      PRODUCT_LEVEL2,
                                                      PRODUCT_LEVEL3,
                                                      PRODUCT_LEVEL4,
                                                      PRODUCT_LEVEL5,
                                                      REMAN_AVAILABLE,
                                                      REMANUFACTURED_ITEM_EXISTS,
                                                      RETURN_CONTROL_TYPE,
                                                      RETURN_CONTROLLED,
                                                      RETURN_SUB_WAREHOUSE,
                                                      REVENUE,
                                                      RMA_ALLOWED,
                                                      RMA_INSPECTION_REQUIRED,
                                                      SAFETY_HAZARDOUS_CLASS,
                                                      SAFETY_HAZARDOUS_ID,
                                                      SAFETY_HAZARDOUS_MATERIAL,
                                                      SAFETY_HAZARDOUS_NUMBER,
                                                      SAFETY_HAZARDOUS_SHIP_LABEL,
                                                      SEATS_FROM,
                                                      SEATS_TO,
                                                      SERIAL_CONTROL,
                                                      SERIAL_FROM,
                                                      SERIAL_TO,
                                                      SERVICE_ALLOCATION_TYPE,
                                                      SERVICE_CALL_ENABLED,
                                                      SERVICE_MODEL,
                                                      SOFTWARE_CATEGORY,
                                                      SOFTWARE_MAINTENANCE_CONTROL,
                                                      SOFTWARE_PRODUCT_CATEGORY,
                                                      SOFTWARE_VERSION,
                                                      SOW_REQUIRED,
                                                      STANDARD_COST,
                                                      SUPPLY_COLOR,
                                                      SUPPLY_OEM_CODE,
                                                      SUPPLY_OEM_MANUFACTURER,
                                                      SUPPLY_TYPE,
                                                      SUPPLY_YIELD,
                                                      SUPPLY_YIELD_TYPE,
                                                      SUPPLY_YIELD_UOM,
                                                      TAX_CODE,
                                                      TC_OPTION,
                                                      TC_OPTION_VALUE,
                                                      THIRD_PARTY,
                                                      UNIT_OF_MEASURE,
                                                      UOM_CLASS,
                                                      UPC_CODE,
                                                      WARRANTY,
                                                      WARRANTY_PERIOD,
                                                      WEIGHT,
                                                      TARIFF_CODE,
                                                      FREIGHT_CLASS_CODE,
                                                      RETURN_WAREHOUSE,
                                                      INTANGIBLE_MDSE_TYPE,
                                                      PURCHASE_PRICE,
                                                      SUPPLIER,
                                                      SUPPLIER_ITEM,
                                                      SUPPLIER_SITE,
                                                      SUPERSEDED_BY,
                                                      AREA_OF_PAPER,
                                                      ITEM_BILLING_TYPE,
                                                      CONFIGURATION_FLAG,
                                                      RETURN_VENDOR,
                                                      RETURN_VENDOR_SITE,
                                                      HARD_ALLOCATION_TYPE,
                                                      DEFAULT_SOURCE_TYPE,
                                                      EASY_PACK_I,
                                                      CREATED_BY,
                                                      CREATION_DATE,
                                                      LAST_UPDATE_BY,
                                                      LAST_UPDATE_DATE,
                                                      MSRP_COST,
                                                       UNBOXED_WEIGHT ,
                                                       UNBOXED_WEIGHT_UOM,
                                                       UNBOXED_ITEM_LENGTH,
                                                       UNBOXED_LENGTH_UOM,
                                                       UNBOXED_WIDTH,
                                                       UNBOXED_WIDTH_UOM,
                                                       UNBOXED_HEIGHT,
                                                       UNBOXED_HEIGHT_UOM,
                                                       LEAD_TIME,
                                                       INTERNAL_ITEM)
                       VALUES (
                                 'CANON_E008_PROJ_ITEMS_TBL',
                                 'ADB',
                                 L_PROJECT_ID,
                                 L_TEMPLATE_ID,
                                 L_PROJ_ITEM_ID,
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ACCOUNTING_RULES),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ACCOUNTING_RULES),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ACCRUAL),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ACCRUAL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ASSEMBLED_COUNTRY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ASSEMBLED_COUNTRY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ASSET_RECOVERY_COST),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ASSET_RECOVERY_COST),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MIN),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MIN),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MAX),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MAX),
                                 DECODE (
                                    UPPER(P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_PER_LICENSE),
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_PER_LICENSE),
                                 DECODE (
                                    UPPER(P_ITEM_TBL (L_INDEX).ASSURANCE_PTS_FIXED_PERORD_QTY),
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).ASSURANCE_PTS_FIXED_PERORD_QTY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).BUNDLED_MAINTENANCE_ITEM),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).BUNDLED_MAINTENANCE_ITEM),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).COMMISSIONS_GROUP),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COMMISSIONS_GROUP),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).COST_OF_GOODS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COST_OF_GOODS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).COSTED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COSTED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).COVERAGE_BASE_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COVERAGE_BASE_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).COVERAGE_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).COVERAGE_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).CRITICALITY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).CRITICALITY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).CUSTOMER_ORDEREABLE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).CUSTOMER_ORDEREABLE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).DEFAULT_SRC_SUB_WH),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).DEFAULT_SRC_SUB_WH),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).DEFAULT_SRC_WH),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).DEFAULT_SRC_WH),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ITEM_DEPTH),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_DEPTH),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ELAN_CONTROLLED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ELAN_CONTROLLED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).EXPENSE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).EXPENSE),
                                 DECODE (
                                    UPPER(P_ITEM_TBL (L_INDEX).EXTENDED_MAINT_POP_AVAILABLE),
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).EXTENDED_MAINT_POP_AVAILABLE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).FIXED_NO_OF_SEATS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).FIXED_NO_OF_SEATS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).HEIGHT),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).HEIGHT),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ITEM),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ITEM),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ENABLED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ENABLED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_MODEL),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_MODEL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).INSTALLBASE_CONTROLLED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INSTALLBASE_CONTROLLED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).INVENTORY_TRACKABLE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INVENTORY_TRACKABLE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).INVOICEABLE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INVOICEABLE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).INVOICING_RULES),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INVOICING_RULES),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ITEM_CLASSIFICATION),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_CLASSIFICATION),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ITEM_DESCRIPTION),
                                         'NULL', '',
                                         UPPER (P_ITEM_TBL (L_INDEX).ITEM_DESCRIPTION)),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ITEM_NUMBER),
                                         'NULL', '',
                                         UPPER (P_ITEM_TBL (L_INDEX).ITEM_NUMBER)),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ITEM_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ITEM_LENGTH),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_LENGTH),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).LICENSE_CONTROL),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).LICENSE_CONTROL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).LONG_DESCRIPTION),
                                         'NULL', '',
                                         UPPER (P_ITEM_TBL (L_INDEX).LONG_DESCRIPTION)),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MAIN_ENGINE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAIN_ENGINE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TERM),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TERM),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MAINTENANCE_POP_AVALIABLE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAINTENANCE_POP_AVALIABLE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MANUFACTURED_COUNTRY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MANUFACTURED_COUNTRY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MANUFACTURER),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MANUFACTURER),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MANUFACTURER_ITEM_NUMBER),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MANUFACTURER_ITEM_NUMBER),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MARKETING_MODEL),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MARKETING_MODEL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MARKETING_SEGMENT),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MARKETING_SEGMENT),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MATERIAL_GROUP1),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MATERIAL_GROUP1),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MATERIAL_GROUP2),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MATERIAL_GROUP2),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MATERIAL_GROUP3),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MATERIAL_GROUP3),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MAXIMUM_ORDER_QUANTITY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MAXIMUM_ORDER_QUANTITY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MERCHANDISE_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MERCHANDISE_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).METERED_MACHINE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).METERED_MACHINE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).MINIMUM_ORDER_QUANTITY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).MINIMUM_ORDER_QUANTITY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).NMFC_CLASS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).NMFC_CLASS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ORDER_INCREMENTS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ORDER_INCREMENTS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).OWNING_DIVISION),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).OWNING_DIVISION),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PART_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PART_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PART_YIELD_DAYS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PART_YIELD_DAYS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PART_YIELD_OUTPUTS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PART_YIELD_OUTPUTS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PARTS_DROPSHIP_DISABLED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PARTS_DROPSHIP_DISABLED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PARTS_SURVEY_REQUIRED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PARTS_SURVEY_REQUIRED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PLANNING_GROUP),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PLANNING_GROUP),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PRIVATE_LABEL_FLAG),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRIVATE_LABEL_FLAG),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PRODUCT_CODE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_CODE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL1),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL1),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL2),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL2),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL3),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL3),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL4),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL4),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL5),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL5),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).REMAN_AVAILABLE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).REMAN_AVAILABLE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).REMANUFACTURED_ITEM_EXISTS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).REMANUFACTURED_ITEM_EXISTS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).RETURN_CONTROL_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_CONTROL_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).RETURN_CONTROLLED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_CONTROLLED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).RETURN_SUB_WAREHOUSE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_SUB_WAREHOUSE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).REVENUE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).REVENUE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).RMA_ALLOWED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RMA_ALLOWED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).RMA_INSPECTION_REQUIRED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RMA_INSPECTION_REQUIRED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_CLASS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_CLASS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_ID),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_ID),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_MATERIAL),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_MATERIAL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_NUMBER),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_NUMBER),
                                 DECODE (
                                    UPPER(P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_SHIP_LABEL),
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_SHIP_LABEL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SEATS_FROM),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SEATS_FROM),
                                 DECODE (P_ITEM_TBL (L_INDEX).SEATS_TO,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SEATS_TO),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SERIAL_CONTROL),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERIAL_CONTROL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SERIAL_FROM),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERIAL_FROM),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SERIAL_TO),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERIAL_TO),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SERVICE_ALLOCATION_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERVICE_ALLOCATION_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SERVICE_CALL_ENABLED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERVICE_CALL_ENABLED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SERVICE_MODEL),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SERVICE_MODEL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SOFTWARE_CATEGORY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SOFTWARE_CATEGORY),
                                 DECODE (
                                    UPPER(P_ITEM_TBL (L_INDEX).SOFTWARE_MAINTENANCE_CONTROL),
                                    'NULL', '',
                                    P_ITEM_TBL (L_INDEX).SOFTWARE_MAINTENANCE_CONTROL),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SOFTWARE_PRODUCT_CATEGORY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SOFTWARE_PRODUCT_CATEGORY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SOFTWARE_VERSION),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SOFTWARE_VERSION),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SOW_REQUIRED),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SOW_REQUIRED),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).STANDARD_COST),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).STANDARD_COST),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLY_COLOR),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_COLOR),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLY_OEM_CODE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_OEM_CODE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLY_OEM_MANUFACTURER),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_OEM_MANUFACTURER),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLY_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLY_YIELD),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_YIELD),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_UOM),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_UOM),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).TAX_CODE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).TAX_CODE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).TC_OPTION),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).TC_OPTION),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).TC_OPTION_VALUE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).TC_OPTION_VALUE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).THIRD_PARTY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).THIRD_PARTY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).UNIT_OF_MEASURE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNIT_OF_MEASURE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).UOM_CLASS),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UOM_CLASS),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).UPC_CODE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UPC_CODE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).WARRANTY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).WARRANTY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).WARRANTY_PERIOD),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).WARRANTY_PERIOD),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).WEIGHT),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).WEIGHT),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).TARIFF_CODE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).TARIFF_CODE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).FREIGHT_CLASS_CODE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).FREIGHT_CLASS_CODE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).RETURN_WAREHOUSE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_WAREHOUSE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).INTANGIBLE_MDSE_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INTANGIBLE_MDSE_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).PURCHASE_PRICE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).PURCHASE_PRICE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLIER),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLIER),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLIER_ITEM),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLIER_ITEM),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPPLIER_SITE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPPLIER_SITE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).SUPERSEDED_BY),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).SUPERSEDED_BY),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).AREA_OF_PAPER),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).AREA_OF_PAPER),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).ITEM_BILLING_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).ITEM_BILLING_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).CONFIGURATION_FLAG),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).CONFIGURATION_FLAG),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).RETURN_VENDOR),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_VENDOR),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).RETURN_VENDOR_SITE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).RETURN_VENDOR_SITE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).HARD_ALLOCATION_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).HARD_ALLOCATION_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).DEFAULT_SOURCE_TYPE),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).DEFAULT_SOURCE_TYPE),
                                 DECODE (UPPER(P_ITEM_TBL (L_INDEX).EASY_PACK_I),
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).EASY_PACK_I),
                                 P_ITEM_TBL (L_INDEX).CREATED_BY,
                                 SYSDATE,
                                 P_ITEM_TBL (L_INDEX).LAST_UPDATE_BY,
                                 SYSDATE,
                                 P_ITEM_TBL (L_INDEX).MSRP_COST,
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_ITEM_LENGTH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_ITEM_LENGTH),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_LENGTH_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_LENGTH_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT_UOM),
                                DECODE (P_ITEM_TBL (L_INDEX).LEAD_TIME,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).LEAD_TIME),
                                DECODE (P_ITEM_TBL (L_INDEX).INTERNAL_ITEM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INTERNAL_ITEM)
                                         );

    /*            BEGIN
                    UPDATE CANON_E008_PROJ_ITEMS_TBL
                    SET Item_description = REGEXP_REPLACE(item_description, '[^0-9A-Za-z &,/-:]', ' '),
                        Item_number = replace(Item_number,'"',''), --REGEXP_REPLACE(Item_number, '[^0-9A-Za-z &,-/:]', ' '),
                        Manufacturer_item_number = REGEXP_REPLACE(Manufacturer_item_number, '[^0-9A-Za-z &,-/:]', ' '),
                        upc_code = REGEXP_REPLACE(upc_code, '[^0-9A-Za-z &,/-:]', ' '),
                        Long_description = REGEXP_REPLACE(long_description, '[^0-9A-Za-z &,/-:]', ' '),
                        superseded_by = REGEXP_REPLACE(superseded_by, '[^0-9A-Za-z &,/-:]', ' '),
                        area_of_paper = REGEXP_REPLACE(area_of_paper, '[^0-9A-Za-z &,/-:]', ' ')
                    WHERE PROJECT_ID = L_PROJECT_ID;
                END;  */

               FOR DEFAULT_ATTR_REC IN CUR_DEFAULT_ATTR (L_TEMPLATE_ID)
               LOOP
                  BEGIN
                     L_DYN_UPD_SQL :=
                           'UPDATE CANON_E008_PROJ_ITEMS_TBL  PIT SET '
                        || DEFAULT_ATTR_REC.MAP_COL
                        || ' = '''
                        || DEFAULT_ATTR_REC.DEFAULT_VALUE
                        || ''''
                        || ' WHERE PIT.PROJECT_ID = '
                        || L_PROJECT_ID
                        || ' AND PIT.TEMPLATE_ID = '
                        || L_TEMPLATE_ID;

                     EXECUTE IMMEDIATE L_DYN_UPD_SQL;
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        INSERT_ERROR ('UPLOAD_PROJECT_ITEMS-DYN UPDATE',
                                      SUBSTR (SQLERRM, 1, 400),
                                      L_ERRBUF,
                                      L_RETCODE);
                  END;
               END LOOP;
            END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               INSERT_ERROR ('UPLOAD_PROJECT_ITEMS INSERT',
                             SUBSTR (SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);
         END;

         --Template Attributes with LOV. To retrive code for the List name stored in the project items table.
         FOR LOV_ATTR_REC IN CUR_LOV_ATTR (L_TEMPLATE_ID)
         LOOP
            BEGIN
               L_DYN_LOV_SQL :=
                     'SELECT '
                  || LOV_ATTR_REC.MAP_COL
                  || ' FROM CANON_E008_PROJ_ITEMS_TBL WHERE PROJECT_ID = '
                  || L_PROJECT_ID
                  || ' AND ITEM_NUMBER = '''
                  || UPPER (P_ITEM_TBL (L_INDEX).ITEM_NUMBER)
                  || '''';

               /*  INSERT INTO test123
                      VALUES ('L_DYN_LOV_SQL:' || L_DYN_LOV_SQL);*/

               EXECUTE IMMEDIATE L_DYN_LOV_SQL INTO L_COL_VALUE;
            EXCEPTION
               WHEN OTHERS
               THEN
                  INSERT_ERROR ('UPLOAD_PROJECT_ITEMS-DYN SELECT',
                                SUBSTR (L_DYN_LOV_SQL||'-'|| SQLERRM, 1, 500),
                                L_ERRBUF,
                                L_RETCODE);
            END;

            IF L_COL_VALUE IS NOT NULL
            THEN
			   IF LOV_ATTR_REC.MAP_COL_LIST IN ('DEFAULT_SRC_WH_LIST',
												'MATERIAL_GROUP1_LIST',
												'SERVICE_MODEL_LIST',
												'SUPPLIER_LIST',
												'SUPPLIER_SITE_LIST',
												'TARIFF_CODE_LIST',
                                                'PRODUCT_LEVEL5_LIST')
			   THEN
					L_LIST_CODE1 := '-00000';
			   ELSE
					L_LIST_CODE1 := NULL;
			   END IF;

               IF  LOV_ATTR_REC.MAP_COL_LIST = 'SUPPLIER_SITE_LIST' THEN
                   BEGIN

                   SELECT SUPPLIER
                     INTO L_SUPPLIER_CODE
                     FROM CANON_E008_PROJ_ITEMS_TBL
                    WHERE PROJECT_ID = L_PROJECT_ID
                      AND ITEM_NUMBER = UPPER (P_ITEM_TBL (L_INDEX).ITEM_NUMBER);
                   EXCEPTION
                        WHEN OTHERS THEN
                            L_SUPPLIER_CODE :='';
                   END;

                   L_DYN_LOV_SQL := 'BEGIN CANON_E008_ITEM_TEMPLATE_PKG.'
                                      || LOV_ATTR_REC.MAP_COL_LIST
                                      || '( :L_SUPPLIER_CODE,:L_LIST_CODE1, :l_TEMPLATE_LIST_TBL, :l_ERR_FLG , :l_ERR_MSG); END;';

                   EXECUTE IMMEDIATE L_DYN_LOV_SQL
                      USING IN L_SUPPLIER_CODE, IN L_LIST_CODE1, OUT L_TEMPLATE_LIST_TBL, OUT L_ERR_FLG, OUT L_ERR_MSG;



               ELSE

                    L_DYN_LOV_SQL := 'BEGIN CANON_E008_ITEM_TEMPLATE_PKG.'
                                      || LOV_ATTR_REC.MAP_COL_LIST
                                      || '( :L_LIST_CODE1, :l_TEMPLATE_LIST_TBL, :l_ERR_FLG , :l_ERR_MSG); END;';

                   EXECUTE IMMEDIATE L_DYN_LOV_SQL
                      USING IN L_LIST_CODE1, OUT L_TEMPLATE_LIST_TBL, OUT L_ERR_FLG, OUT L_ERR_MSG;

               END IF;


               LOOP
                  FETCH L_TEMPLATE_LIST_TBL INTO L_LIST_CODE, L_LIST_NAME;

                  EXIT WHEN L_TEMPLATE_LIST_TBL%NOTFOUND;

                  IF    UPPER (L_LIST_NAME) = UPPER (L_COL_VALUE)
                     OR L_LIST_CODE = L_COL_VALUE
                  THEN
                     BEGIN
                        L_DYN_UPD_SQL :=
                              'UPDATE CANON_E008_PROJ_ITEMS_TBL  PIT SET '
                           || LOV_ATTR_REC.MAP_COL
                           || ' = '''
                           || L_LIST_CODE
                           || ''''
                           || ' WHERE PIT.PROJECT_ID = '
                           || L_PROJECT_ID
                           || ' AND ITEM_NUMBER = '''
						   || UPPER (P_ITEM_TBL (L_INDEX).ITEM_NUMBER)
                           || '''';

                        /* INSERT INTO test123
                                 VALUES ('L_DYN_UPD_SQL:' || L_DYN_UPD_SQL);  */

                        EXECUTE IMMEDIATE L_DYN_UPD_SQL;
                     EXCEPTION
                        WHEN OTHERS
                        THEN
                           INSERT_ERROR ('UPLOAD_PROJECT_ITEMS-DYN UPDATE',
                                         SUBSTR (L_DYN_UPD_SQL||'-'|| SQLERRM, 1, 500),
                                         L_ERRBUF,
                                         L_RETCODE);
                     END;

                     EXIT;
                  END IF;
               END LOOP;

               CLOSE L_TEMPLATE_LIST_TBL;
            END IF;                                              --L_COL_VALUE IS NOT NULL
         END LOOP;                                                          --CUR_LOV_ATTR

         L_INDEX := P_ITEM_TBL.NEXT (L_INDEX);
      END LOOP;

      P_ERR_FLG := 'S';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
         INSERT_ERROR ('UPLOAD_PROJECT_ITEMS',
                       L_ERR_MSG,
                       L_ERRBUF,
                       L_RETCODE);
   END UPLOAD_PROJECT_ITEMS;

   PROCEDURE VALIDATE_UPLOAD_ITEMS (P_PROJECT_NO      IN     NUMBER,
                                    P_ITEM_TBL        IN     CANON_E008_PROJ_ITEMS_TBL_TYPE,
                                    P_USER_ID         IN     VARCHAR2,
                                    P_PROJECT_ERROR      OUT VARCHAR2,    --G_REF_CUR_TYP,
                                    P_ERR_FLG            OUT VARCHAR2,
                                    P_ERR_MSG            OUT VARCHAR2)
   IS
      CURSOR CUR_UPLD_ITEMS
      IS
         SELECT *
           FROM CANON_E008_UPLD_ITEMS_TBL
          WHERE PROJECT_ID = P_PROJECT_NO;

      CURSOR CUR_REQ_COLUMNS (
         C_TEMPLATE_ID    NUMBER)
      IS
           SELECT ATC.COLUMN_NAME MAP_COL,
                  SUBSTR (TAT.E008_WB_TBL_MAP, 1, 25) || '_LIST' MAP_COL_LIST,
                  TAT.ATTRIBUTE_NAME,
                  TAA.REQUIRED_VALUE,
                  TAA.VALID,
                  TAA.DEFAULT_VALUE,
                  TAT.WORKBENCH_DISPLAY,
                  TAT.LOV_FLAG,
                  TAT.S21_MAP_COL_SIZE
             FROM ALL_TAB_COLS ATC,
                  CANON_E008_TEMPL_ATTR_ASGN_TBL TAA,
                  CANON_E008_TEMPLATE_ATTR_TBL TAT
            WHERE     TABLE_NAME = 'CANON_E008_PROJ_ITEMS_TBL'
                  AND ATC.COLUMN_NAME = TAT.E008_WB_TBL_MAP
                  AND TAA.TEMPLATE_ID = C_TEMPLATE_ID
                  AND TAT.ATTRIBUTE_ID = TAA.ATTRIBUTE_ID
                  --AND WORKBENCH_DISPLAY IN ('ADDITIONAL', 'MAIN')
                  AND TAT.ENABLE_FLAG = 'Y'
         --AND (REQUIRED_VALUE = 'YES' OR VALID = 'YES')
         ORDER BY COLUMN_NAME;

      L_ERR_MSG             VARCHAR2 (2000);
      L_PROJECT_TYPE        CANON_E008_PROJECT_WB_TBL.PROJECT_TYPE%TYPE;
      L_DYN_SQL             VARCHAR2 (32000);
      L_COL_VALUE           VARCHAR2 (2000);
      L_COUNT               VARCHAR2 (30);
      L_PI_CNT              VARCHAR2 (30);
      L_ITEM                MDSE.MDSE_CD%TYPE;
      L_PRJ_ITEM_ID         NUMBER := -1;
      L_ROW                 NUMBER := 0;
      L_COL_MAIN            NUMBER := 0;
      L_COL_ADDL            NUMBER := 0;
      L_PRCL_CNT            NUMBER := 0;
      --L_STR                 VARCHAR2 (200);
      L_TEMPLATE_ID         NUMBER;
      L_INDEX               NUMBER;
      L_DYN_LOV_SQL         VARCHAR2 (32000);
      L_ERR_FLG             VARCHAR2 (100);
      L_VAL_EXIST           VARCHAR2 (30);
      L_TEMPLATE_LIST_TBL   G_REF_CUR_TYP;
      L_LIST_CODE           VARCHAR2 (4000);
      L_LIST_CODE1          VARCHAR2 (4000);
      L_LIST_NAME           VARCHAR2 (4000);
      L_PROJECT_ERROR       VARCHAR2 (32000);
      L_ERRBUF              VARCHAR2 (200);
      L_RETCODE             VARCHAR2 (200);
   BEGIN
      DELETE FROM CANON_E008_PROJECT_ERR_TBL
            WHERE PROJECT_ID = P_PROJECT_NO AND USER_ID = P_USER_ID;

      COMMIT;
      L_INDEX := P_ITEM_TBL.FIRST;

      --Insert  upload items data into Global Temp Table to process validations before calling save into Item workbench project Items table.

      WHILE L_INDEX IS NOT NULL
      LOOP
         BEGIN
            SELECT TEMPLATE_ID
              INTO L_TEMPLATE_ID
              FROM CANON_E008_TEMPLATE_HDR_TBL
             WHERE TEMPLATE_NAME = P_ITEM_TBL (L_INDEX).TEMPLATE_NAME;
         EXCEPTION
            WHEN OTHERS
            THEN
               L_TEMPLATE_ID := NULL;
         END;


         IF    P_ITEM_TBL (L_INDEX).TEMPLATE_NAME IS NULL
            OR P_ITEM_TBL (L_INDEX).ITEM_NUMBER IS NULL
            OR P_ITEM_TBL (L_INDEX).ITEM_DESCRIPTION IS NULL
         THEN
            P_ERR_FLG := 'S';
            P_ERR_MSG := 'Template Name, Item and Item Description are mandatory...';
            P_PROJECT_ERROR := P_ERR_MSG;
         ELSIF L_TEMPLATE_ID IS NULL
         THEN
            P_ERR_FLG := 'S';
            P_ERR_MSG :=
                  'Template '''
               || P_ITEM_TBL (L_INDEX).TEMPLATE_NAME
               || ''' does not exist.'
               || '##';
            P_PROJECT_ERROR := P_PROJECT_ERROR || P_ERR_MSG;
         END IF;


         L_INDEX := P_ITEM_TBL.NEXT (L_INDEX);
      END LOOP;


      P_PROJECT_ERROR := SUBSTR (P_PROJECT_ERROR, 1, LENGTH (P_PROJECT_ERROR) - 2);

     IF P_PROJECT_ERROR IS NOT NULL
      THEN
         RETURN;
      END IF;

      L_INDEX := P_ITEM_TBL.FIRST;

      --Insert  upload items data into Global Temp Table to process validations before calling save into Item workbench project Items table.
      WHILE L_INDEX IS NOT NULL
      LOOP
         BEGIN
            INSERT INTO CANON_E008_UPLD_ITEMS_TBL (EZTABLEID,
                                                   COMPANY_CODE,
                                                   PROJECT_ID,
                                                   TEMPLATE_ID,
                                                   PROJ_ITEM_ID,
                                                   ACCOUNTING_RULES,
                                                   ACCRUAL,
                                                   ASSEMBLED_COUNTRY,
                                                   ASSET_RECOVERY_COST,
                                                   ASSURANCE_POINTS_MIN,
                                                   ASSURANCE_POINTS_MAX,
                                                   ASSURANCE_POINTS_PER_LICENSE,
                                                   ASSURANCE_PTS_FIXED_PERORD_QTY,
                                                   BUNDLED_MAINTENANCE_ITEM,
                                                   COMMISSIONS_GROUP,
                                                   COST_OF_GOODS,
                                                   COSTED,
                                                   COVERAGE_BASE_TYPE,
                                                   COVERAGE_TYPE,
                                                   CRITICALITY,
                                                   CUSTOMER_ORDEREABLE,
                                                   DEFAULT_SRC_SUB_WH,
                                                   DEFAULT_SRC_WH,
                                                   ITEM_DEPTH,
                                                   ELAN_CONTROLLED,
                                                   EXPENSE,
                                                   EXTENDED_MAINT_POP_AVAILABLE,
                                                   FIXED_NO_OF_SEATS,
                                                   HEIGHT,
                                                   IMAGEWARE_REMOTE_ITEM,
                                                   IMAGEWARE_REMOTE_ENABLED,
                                                   IMAGEWARE_REMOTE_MODEL,
                                                   INSTALLBASE_CONTROLLED,
                                                   INVENTORY_TRACKABLE,
                                                   INVOICEABLE,
                                                   INVOICING_RULES,
                                                   ITEM_CLASSIFICATION,
                                                   ITEM_DESCRIPTION,
                                                   ITEM_NUMBER,
                                                   ITEM_TYPE,
                                                   ITEM_LENGTH,
                                                   LICENSE_CONTROL,
                                                   LONG_DESCRIPTION,
                                                   MAIN_ENGINE,
                                                   MAINTENANCE_ITEM_TERM,
                                                   MAINTENANCE_ITEM_TYPE,
                                                   MAINTENANCE_POP_AVALIABLE,
                                                   MANUFACTURED_COUNTRY,
                                                   MANUFACTURER,
                                                   MANUFACTURER_ITEM_NUMBER,
                                                   MARKETING_MODEL,
                                                   MARKETING_SEGMENT,
                                                   MATERIAL_GROUP1,
                                                   MATERIAL_GROUP2,
                                                   MATERIAL_GROUP3,
                                                   MAXIMUM_ORDER_QUANTITY,
                                                   MERCHANDISE_TYPE,
                                                   METERED_MACHINE,
                                                   MINIMUM_ORDER_QUANTITY,
                                                   NMFC_CLASS,
                                                   ORDER_INCREMENTS,
                                                   OWNING_DIVISION,
                                                   PART_TYPE,
                                                   PART_YIELD_DAYS,
                                                   PART_YIELD_OUTPUTS,
                                                   PARTS_DROPSHIP_DISABLED,
                                                   PARTS_SURVEY_REQUIRED,
                                                   PLANNING_GROUP,
                                                   PRIVATE_LABEL_FLAG,
                                                   PRODUCT_CODE,
                                                   PRODUCT_LEVEL1,
                                                   PRODUCT_LEVEL2,
                                                   PRODUCT_LEVEL3,
                                                   PRODUCT_LEVEL4,
                                                   PRODUCT_LEVEL5,
                                                   REMAN_AVAILABLE,
                                                   REMANUFACTURED_ITEM_EXISTS,
                                                   RETURN_CONTROL_TYPE,
                                                   RETURN_CONTROLLED,
                                                   RETURN_SUB_WAREHOUSE,
                                                   REVENUE,
                                                   RMA_ALLOWED,
                                                   RMA_INSPECTION_REQUIRED,
                                                   SAFETY_HAZARDOUS_CLASS,
                                                   SAFETY_HAZARDOUS_ID,
                                                   SAFETY_HAZARDOUS_MATERIAL,
                                                   SAFETY_HAZARDOUS_NUMBER,
                                                   SAFETY_HAZARDOUS_SHIP_LABEL,
                                                   SEATS_FROM,
                                                   SEATS_TO,
                                                   SERIAL_CONTROL,
                                                   SERIAL_FROM,
                                                   SERIAL_TO,
                                                   SERVICE_ALLOCATION_TYPE,
                                                   SERVICE_CALL_ENABLED,
                                                   SERVICE_MODEL,
                                                   SOFTWARE_CATEGORY,
                                                   SOFTWARE_MAINTENANCE_CONTROL,
                                                   SOFTWARE_PRODUCT_CATEGORY,
                                                   SOFTWARE_VERSION,
                                                   SOW_REQUIRED,
                                                   STANDARD_COST,
                                                   SUPPLY_COLOR,
                                                   SUPPLY_OEM_CODE,
                                                   SUPPLY_OEM_MANUFACTURER,
                                                   SUPPLY_TYPE,
                                                   SUPPLY_YIELD,
                                                   SUPPLY_YIELD_TYPE,
                                                   SUPPLY_YIELD_UOM,
                                                   TAX_CODE,
                                                   TC_OPTION,
                                                   TC_OPTION_VALUE,
                                                   THIRD_PARTY,
                                                   UNIT_OF_MEASURE,
                                                   UOM_CLASS,
                                                   UPC_CODE,
                                                   WARRANTY,
                                                   WARRANTY_PERIOD,
                                                   WEIGHT,
                                                   TARIFF_CODE,
                                                   FREIGHT_CLASS_CODE,
                                                   RETURN_WAREHOUSE,
                                                   INTANGIBLE_MDSE_TYPE,
                                                   PURCHASE_PRICE,
                                                   SUPPLIER,
                                                   SUPPLIER_ITEM,
                                                   SUPPLIER_SITE,
                                                   SUPERSEDED_BY,
                                                   AREA_OF_PAPER,
                                                   ITEM_BILLING_TYPE,
                                                   CONFIGURATION_FLAG,
                                                   RETURN_VENDOR,
                                                   RETURN_VENDOR_SITE,
                                                   HARD_ALLOCATION_TYPE,
                                                   DEFAULT_SOURCE_TYPE,
                                                   EASY_PACK_I,
                                                   CREATED_BY,
                                                   CREATION_DATE,
                                                   LAST_UPDATE_BY,
                                                   LAST_UPDATE_DATE,
                                                   MSRP_COST,
                                                   UNBOXED_WEIGHT ,
                                                   UNBOXED_WEIGHT_UOM,
                                                   UNBOXED_ITEM_LENGTH,
                                                   UNBOXED_LENGTH_UOM,
                                                   UNBOXED_WIDTH,
                                                   UNBOXED_WIDTH_UOM,
                                                   UNBOXED_HEIGHT,
                                                   UNBOXED_HEIGHT_UOM,
                                                   LEAD_TIME,
                                                   INTERNAL_ITEM)
                    VALUES (
                              'CANON_E008_UPLD_ITEMS_TBL',
                              'ADB',
                              P_PROJECT_NO,
                              L_TEMPLATE_ID,
                              NULL,                                      --L_PROJ_ITEM_ID,
                              DECODE (P_ITEM_TBL (L_INDEX).ACCOUNTING_RULES,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ACCOUNTING_RULES),
                              DECODE (P_ITEM_TBL (L_INDEX).ACCRUAL,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ACCRUAL),
                              DECODE (P_ITEM_TBL (L_INDEX).ASSEMBLED_COUNTRY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ASSEMBLED_COUNTRY),
                              DECODE (P_ITEM_TBL (L_INDEX).ASSET_RECOVERY_COST,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ASSET_RECOVERY_COST),
                              DECODE (P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MIN,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MIN),
                              DECODE (P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MAX,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_MAX),
                              DECODE (P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_PER_LICENSE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ASSURANCE_POINTS_PER_LICENSE),
                              DECODE (
                                 P_ITEM_TBL (L_INDEX).ASSURANCE_PTS_FIXED_PERORD_QTY,
                                 'NULL', '',
                                 P_ITEM_TBL (L_INDEX).ASSURANCE_PTS_FIXED_PERORD_QTY),
                              DECODE (P_ITEM_TBL (L_INDEX).BUNDLED_MAINTENANCE_ITEM,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).BUNDLED_MAINTENANCE_ITEM),
                              DECODE (P_ITEM_TBL (L_INDEX).COMMISSIONS_GROUP,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).COMMISSIONS_GROUP),
                              DECODE (P_ITEM_TBL (L_INDEX).COST_OF_GOODS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).COST_OF_GOODS),
                              DECODE (P_ITEM_TBL (L_INDEX).COSTED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).COSTED),
                              DECODE (P_ITEM_TBL (L_INDEX).COVERAGE_BASE_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).COVERAGE_BASE_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).COVERAGE_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).COVERAGE_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).CRITICALITY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).CRITICALITY),
                              DECODE (P_ITEM_TBL (L_INDEX).CUSTOMER_ORDEREABLE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).CUSTOMER_ORDEREABLE),
                              DECODE (P_ITEM_TBL (L_INDEX).DEFAULT_SRC_SUB_WH,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).DEFAULT_SRC_SUB_WH),
                              DECODE (P_ITEM_TBL (L_INDEX).DEFAULT_SRC_WH,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).DEFAULT_SRC_WH),
                              DECODE (P_ITEM_TBL (L_INDEX).ITEM_DEPTH,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ITEM_DEPTH),
                              DECODE (P_ITEM_TBL (L_INDEX).ELAN_CONTROLLED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ELAN_CONTROLLED),
                              DECODE (P_ITEM_TBL (L_INDEX).EXPENSE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).EXPENSE),
                              DECODE (P_ITEM_TBL (L_INDEX).EXTENDED_MAINT_POP_AVAILABLE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).EXTENDED_MAINT_POP_AVAILABLE),
                              DECODE (P_ITEM_TBL (L_INDEX).FIXED_NO_OF_SEATS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).FIXED_NO_OF_SEATS),
                              DECODE (P_ITEM_TBL (L_INDEX).HEIGHT,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).HEIGHT),
                              DECODE (P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ITEM,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ITEM),
                              DECODE (P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ENABLED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_ENABLED),
                              DECODE (P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_MODEL,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).IMAGEWARE_REMOTE_MODEL),
                              DECODE (P_ITEM_TBL (L_INDEX).INSTALLBASE_CONTROLLED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).INSTALLBASE_CONTROLLED),
                              DECODE (P_ITEM_TBL (L_INDEX).INVENTORY_TRACKABLE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).INVENTORY_TRACKABLE),
                              DECODE (P_ITEM_TBL (L_INDEX).INVOICEABLE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).INVOICEABLE),
                              DECODE (P_ITEM_TBL (L_INDEX).INVOICING_RULES,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).INVOICING_RULES),
                              DECODE (P_ITEM_TBL (L_INDEX).ITEM_CLASSIFICATION,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ITEM_CLASSIFICATION),
                              DECODE (P_ITEM_TBL (L_INDEX).ITEM_DESCRIPTION,
                                      'NULL', '',
                                      UPPER (P_ITEM_TBL (L_INDEX).ITEM_DESCRIPTION)),
                              DECODE (P_ITEM_TBL (L_INDEX).ITEM_NUMBER,
                                      'NULL', '',
                                      UPPER (P_ITEM_TBL (L_INDEX).ITEM_NUMBER)),
                              DECODE (P_ITEM_TBL (L_INDEX).ITEM_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ITEM_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).ITEM_LENGTH,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ITEM_LENGTH),
                              DECODE (P_ITEM_TBL (L_INDEX).LICENSE_CONTROL,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).LICENSE_CONTROL),
                              DECODE (P_ITEM_TBL (L_INDEX).LONG_DESCRIPTION,
                                      'NULL', '',
                                      UPPER (P_ITEM_TBL (L_INDEX).LONG_DESCRIPTION)),
                              DECODE (P_ITEM_TBL (L_INDEX).MAIN_ENGINE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MAIN_ENGINE),
                              DECODE (P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TERM,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TERM),
                              DECODE (P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MAINTENANCE_ITEM_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).MAINTENANCE_POP_AVALIABLE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MAINTENANCE_POP_AVALIABLE),
                              DECODE (P_ITEM_TBL (L_INDEX).MANUFACTURED_COUNTRY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MANUFACTURED_COUNTRY),
                              DECODE (P_ITEM_TBL (L_INDEX).MANUFACTURER,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MANUFACTURER),
                              DECODE (P_ITEM_TBL (L_INDEX).MANUFACTURER_ITEM_NUMBER,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MANUFACTURER_ITEM_NUMBER),
                              DECODE (P_ITEM_TBL (L_INDEX).MARKETING_MODEL,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MARKETING_MODEL),
                              DECODE (P_ITEM_TBL (L_INDEX).MARKETING_SEGMENT,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MARKETING_SEGMENT),
                              DECODE (P_ITEM_TBL (L_INDEX).MATERIAL_GROUP1,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MATERIAL_GROUP1),
                              DECODE (P_ITEM_TBL (L_INDEX).MATERIAL_GROUP2,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MATERIAL_GROUP2),
                              DECODE (P_ITEM_TBL (L_INDEX).MATERIAL_GROUP3,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MATERIAL_GROUP3),
                              DECODE (P_ITEM_TBL (L_INDEX).MAXIMUM_ORDER_QUANTITY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MAXIMUM_ORDER_QUANTITY),
                              DECODE (P_ITEM_TBL (L_INDEX).MERCHANDISE_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MERCHANDISE_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).METERED_MACHINE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).METERED_MACHINE),
                              DECODE (P_ITEM_TBL (L_INDEX).MINIMUM_ORDER_QUANTITY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).MINIMUM_ORDER_QUANTITY),
                              DECODE (P_ITEM_TBL (L_INDEX).NMFC_CLASS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).NMFC_CLASS),
                              DECODE (P_ITEM_TBL (L_INDEX).ORDER_INCREMENTS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ORDER_INCREMENTS),
                              DECODE (P_ITEM_TBL (L_INDEX).OWNING_DIVISION,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).OWNING_DIVISION),
                              DECODE (P_ITEM_TBL (L_INDEX).PART_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PART_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).PART_YIELD_DAYS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PART_YIELD_DAYS),
                              DECODE (P_ITEM_TBL (L_INDEX).PART_YIELD_OUTPUTS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PART_YIELD_OUTPUTS),
                              DECODE (P_ITEM_TBL (L_INDEX).PARTS_DROPSHIP_DISABLED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PARTS_DROPSHIP_DISABLED),
                              DECODE (P_ITEM_TBL (L_INDEX).PARTS_SURVEY_REQUIRED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PARTS_SURVEY_REQUIRED),
                              DECODE (P_ITEM_TBL (L_INDEX).PLANNING_GROUP,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PLANNING_GROUP),
                              DECODE (P_ITEM_TBL (L_INDEX).PRIVATE_LABEL_FLAG,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PRIVATE_LABEL_FLAG),
                              DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_CODE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PRODUCT_CODE),
                              DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL1,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL1),
                              DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL2,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL2),
                              DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL3,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL3),
                              DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL4,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL4),
                              DECODE (P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL5,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PRODUCT_LEVEL5),
                              DECODE (P_ITEM_TBL (L_INDEX).REMAN_AVAILABLE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).REMAN_AVAILABLE),
                              DECODE (P_ITEM_TBL (L_INDEX).REMANUFACTURED_ITEM_EXISTS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).REMANUFACTURED_ITEM_EXISTS),
                              DECODE (P_ITEM_TBL (L_INDEX).RETURN_CONTROL_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).RETURN_CONTROL_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).RETURN_CONTROLLED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).RETURN_CONTROLLED),
                              DECODE (P_ITEM_TBL (L_INDEX).RETURN_SUB_WAREHOUSE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).RETURN_SUB_WAREHOUSE),
                              DECODE (P_ITEM_TBL (L_INDEX).REVENUE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).REVENUE),
                              DECODE (P_ITEM_TBL (L_INDEX).RMA_ALLOWED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).RMA_ALLOWED),
                              DECODE (P_ITEM_TBL (L_INDEX).RMA_INSPECTION_REQUIRED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).RMA_INSPECTION_REQUIRED),
                              DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_CLASS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_CLASS),
                              DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_ID,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_ID),
                              DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_MATERIAL,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_MATERIAL),
                              DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_NUMBER,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_NUMBER),
                              DECODE (P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_SHIP_LABEL,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SAFETY_HAZARDOUS_SHIP_LABEL),
                              DECODE (P_ITEM_TBL (L_INDEX).SEATS_FROM,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SEATS_FROM),
                              DECODE (P_ITEM_TBL (L_INDEX).SEATS_TO,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SEATS_TO),
                              DECODE (P_ITEM_TBL (L_INDEX).SERIAL_CONTROL,
                                      'NULL', '',
                                      DECODE(P_ITEM_TBL (L_INDEX).SERIAL_CONTROL,'NOT CONTROLLED','NOTCONTROLLED','ON RECEIPT AND SHIPMENT','ONRECEIPTANDSHIPMENT','ON SHIPMENT','ONSHIPMENT')),
                              DECODE (P_ITEM_TBL (L_INDEX).SERIAL_FROM,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SERIAL_FROM),
                              DECODE (P_ITEM_TBL (L_INDEX).SERIAL_TO,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SERIAL_TO),
                              DECODE (P_ITEM_TBL (L_INDEX).SERVICE_ALLOCATION_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SERVICE_ALLOCATION_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).SERVICE_CALL_ENABLED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SERVICE_CALL_ENABLED),
                              DECODE (P_ITEM_TBL (L_INDEX).SERVICE_MODEL,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SERVICE_MODEL),
                              DECODE (P_ITEM_TBL (L_INDEX).SOFTWARE_CATEGORY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SOFTWARE_CATEGORY),
                              DECODE (P_ITEM_TBL (L_INDEX).SOFTWARE_MAINTENANCE_CONTROL,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SOFTWARE_MAINTENANCE_CONTROL),
                              DECODE (P_ITEM_TBL (L_INDEX).SOFTWARE_PRODUCT_CATEGORY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SOFTWARE_PRODUCT_CATEGORY),
                              DECODE (P_ITEM_TBL (L_INDEX).SOFTWARE_VERSION,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SOFTWARE_VERSION),
                              DECODE (P_ITEM_TBL (L_INDEX).SOW_REQUIRED,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SOW_REQUIRED),
                              DECODE (P_ITEM_TBL (L_INDEX).STANDARD_COST,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).STANDARD_COST),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_COLOR,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLY_COLOR),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_OEM_CODE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLY_OEM_CODE),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_OEM_MANUFACTURER,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLY_OEM_MANUFACTURER),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLY_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_YIELD,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLY_YIELD),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_UOM,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLY_YIELD_UOM),
                              DECODE (P_ITEM_TBL (L_INDEX).TAX_CODE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).TAX_CODE),
                              DECODE (P_ITEM_TBL (L_INDEX).TC_OPTION,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).TC_OPTION),
                              DECODE (P_ITEM_TBL (L_INDEX).TC_OPTION_VALUE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).TC_OPTION_VALUE),
                              DECODE (P_ITEM_TBL (L_INDEX).THIRD_PARTY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).THIRD_PARTY),
                              DECODE (P_ITEM_TBL (L_INDEX).UNIT_OF_MEASURE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).UNIT_OF_MEASURE),
                              DECODE (P_ITEM_TBL (L_INDEX).UOM_CLASS,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).UOM_CLASS),
                              DECODE (P_ITEM_TBL (L_INDEX).UPC_CODE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).UPC_CODE),
                              DECODE (P_ITEM_TBL (L_INDEX).WARRANTY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).WARRANTY),
                              DECODE (P_ITEM_TBL (L_INDEX).WARRANTY_PERIOD,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).WARRANTY_PERIOD),
                              DECODE (P_ITEM_TBL (L_INDEX).WEIGHT,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).WEIGHT),
                              DECODE (P_ITEM_TBL (L_INDEX).TARIFF_CODE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).TARIFF_CODE),
                              DECODE (P_ITEM_TBL (L_INDEX).FREIGHT_CLASS_CODE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).FREIGHT_CLASS_CODE),
                              DECODE (P_ITEM_TBL (L_INDEX).RETURN_WAREHOUSE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).RETURN_WAREHOUSE),
                              DECODE (P_ITEM_TBL (L_INDEX).INTANGIBLE_MDSE_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).INTANGIBLE_MDSE_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).PURCHASE_PRICE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).PURCHASE_PRICE),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLIER,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLIER),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLIER_ITEM,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLIER_ITEM),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPPLIER_SITE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPPLIER_SITE),
                              DECODE (P_ITEM_TBL (L_INDEX).SUPERSEDED_BY,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).SUPERSEDED_BY),
                              DECODE (P_ITEM_TBL (L_INDEX).AREA_OF_PAPER,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).AREA_OF_PAPER),
                              DECODE (P_ITEM_TBL (L_INDEX).ITEM_BILLING_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).ITEM_BILLING_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).CONFIGURATION_FLAG,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).CONFIGURATION_FLAG),
                              DECODE (P_ITEM_TBL (L_INDEX).RETURN_VENDOR,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).RETURN_VENDOR),
                              DECODE (P_ITEM_TBL (L_INDEX).RETURN_VENDOR_SITE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).RETURN_VENDOR_SITE),
                              DECODE (P_ITEM_TBL (L_INDEX).HARD_ALLOCATION_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).HARD_ALLOCATION_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).DEFAULT_SOURCE_TYPE,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).DEFAULT_SOURCE_TYPE),
                              DECODE (P_ITEM_TBL (L_INDEX).EASY_PACK_I,
                                      'NULL', '',
                                      P_ITEM_TBL (L_INDEX).EASY_PACK_I),
                              P_ITEM_TBL (L_INDEX).CREATED_BY,
                              SYSDATE,
                              P_ITEM_TBL (L_INDEX).LAST_UPDATE_BY,
                              SYSDATE,
                              P_ITEM_TBL (L_INDEX).MSRP_COST,
                              DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WEIGHT_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_ITEM_LENGTH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_ITEM_LENGTH),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_LENGTH_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_LENGTH_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_WIDTH_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT),
                                 DECODE (P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT_UOM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).UNBOXED_HEIGHT_UOM),
                                 DECODE (P_ITEM_TBL (L_INDEX).LEAD_TIME,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).LEAD_TIME),
                                 DECODE (P_ITEM_TBL (L_INDEX).INTERNAL_ITEM,
                                         'NULL', '',
                                         P_ITEM_TBL (L_INDEX).INTERNAL_ITEM)
                                         );

         EXCEPTION
            WHEN OTHERS
            THEN
               INSERT_ERROR ('VALIDATE_UPLOAD_ITEMS- INSERT GT',
                             SUBSTR (SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);
         END;

         L_INDEX := P_ITEM_TBL.NEXT (L_INDEX);
      END LOOP;
     -- commit;
    /*  BEGIN
        UPDATE CANON_E008_UPLD_ITEMS_TBL
        SET Item_description = REGEXP_REPLACE(item_description, '[^0-9A-Za-z &,/-:]', ' '),
            Item_number = replace(Item_number,'"',''), --REGEXP_REPLACE(Item_number, '[^0-9A-Za-z &,-/:]', ' '),
            Manufacturer_item_number = REGEXP_REPLACE(Manufacturer_item_number, '[^0-9A-Za-z &,-/:]', ' '),
            upc_code = REGEXP_REPLACE(upc_code, '[^0-9A-Za-z &,/-:]', ' '),
            Long_description = REGEXP_REPLACE(long_description, '[^0-9A-Za-z &,/-:]', ' '),
            superseded_by = REGEXP_REPLACE(superseded_by, '[^0-9A-Za-z &,/-:]', ' '),
            area_of_paper = REGEXP_REPLACE(area_of_paper, '[^0-9A-Za-z &,/-:]', ' ')
        WHERE PROJECT_ID = P_PROJECT_NO;

      EXCEPTION
        WHEN OTHERS THEN
                INSERT_ERROR ('VALIDATE_UPLOAD_ITEMS- INSERT GT',
                             SUBSTR (SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);
      END; */


      --INSERT INTO test123 VALUES ('GT Success');
      FOR REC_ITEMS IN CUR_UPLD_ITEMS
      LOOP
         L_ITEM := REC_ITEMS.ITEM_NUMBER;

         --INSERT INTO test123 VALUES ('L_ITEM:'||L_ITEM);
         FOR REQ_COL IN CUR_REQ_COLUMNS (REC_ITEMS.TEMPLATE_ID)
         LOOP
            BEGIN
               L_DYN_SQL :=
                     ' SELECT '
                  || REQ_COL.MAP_COL
                  || ' FROM CANON_E008_UPLD_ITEMS_TBL WHERE PROJECT_ID = '
                  || P_PROJECT_NO
                  || ' AND ITEM_NUMBER = '''
                  || L_ITEM
                  || ''' AND ROWNUM = 1';

               --  INSERT INTO test123 VALUES ('L_DYN_SQL:' || L_DYN_SQL);

               EXECUTE IMMEDIATE L_DYN_SQL INTO L_COL_VALUE;
            EXCEPTION
               WHEN OTHERS
               THEN
                  INSERT_ERROR ('VALIDATE_UPLOAD_ITEMS-DYN SELECT',
                                SUBSTR (SQLERRM, 1, 400),
                                L_ERRBUF,
                                L_RETCODE);
            END;

            --  INSERT INTO test123 VALUES ('L_COL_VALUE:' || L_COL_VALUE);

            /* IF REQ_COL.REQUIRED_VALUE = 'YES' AND L_COL_VALUE IS NULL
            THEN
               P_ERR_FLG := 'S';
               P_ERR_MSG :=
                     'Canon Item#->'
                  || L_ITEM
                  || '- '
                  || REQ_COL.ATTRIBUTE_NAME
                  || ' is Required.';
               INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                      P_USER_ID,
                                      P_ITEM_TBL (L_INDEX).PROJ_ITEM_ID,
                                      REQ_COL.ATTRIBUTE_NAME,
                                      P_ERR_MSG);
            ELSIF  */
            IF L_COL_VALUE IS NOT NULL                        -- AND REQ_COL.VALID = 'YES'
            THEN
               IF REQ_COL.MAP_COL = 'ITEM_NUMBER'
               THEN
                  BEGIN
                     SELECT COUNT (MDSE_CD)
                       INTO L_COUNT
                       FROM MDSE
                      WHERE UPPER (MDSE_CD) = L_COL_VALUE AND GLBL_CMPY_CD = 'ADB';

                     SELECT COUNT (item_number)
                       INTO L_PI_CNT
                       FROM (SELECT ITEM_NUMBER
                               FROM CANON_E008_PROJ_ITEMS_TBL
                              WHERE     ITEM_NUMBER = L_COL_VALUE
                              AND nvl(PROCESS_FLAG,'x') <> 'F'
                                    --AND PROJECT_ID = P_PROJECT_NO
                             UNION ALL
                             SELECT ITEM_NUMBER
                               FROM CANON_E008_UPLD_ITEMS_TBL
                              WHERE     ITEM_NUMBER = L_COL_VALUE
                                    AND PROJECT_ID = P_PROJECT_NO);

                     IF L_COUNT > 0
                     THEN
                        P_ERR_FLG := 'S';
                        P_ERR_MSG :=
                           'Canon Item#->' || L_COL_VALUE || ' - Already exist in S21.';
                        INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                               P_USER_ID,
                                               L_ITEM,
                                               REQ_COL.ATTRIBUTE_NAME,
                                               P_ERR_MSG);
                     END IF;

                     IF L_PI_CNT > 1
                     THEN
                        P_ERR_FLG := 'S';
                        P_ERR_MSG :=
                              'Canon Item#->'
                           || L_COL_VALUE
                           || ' - Already exist in the project or duplicated in the upload excel.';
                        INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                               P_USER_ID,
                                               L_ITEM,
                                               REQ_COL.ATTRIBUTE_NAME,
                                               P_ERR_MSG);
                     /*ELSIF LENGTH (L_COL_VALUE) NOT IN (8, 10)
                     THEN
                        P_ERR_FLG := 'S';
                        P_ERR_MSG :=
                              'Canon Item#->'
                           || L_COL_VALUE
                           || ' - [Item Number] Length should be either 8 or 10 digits.';
                        INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                               P_USER_ID,
                                               L_ITEM,
                                               REQ_COL.ATTRIBUTE_NAME,
                                               P_ERR_MSG);  */
                     END IF;
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        L_COUNT := 0;
                  END;
               ELSIF REQ_COL.MAP_COL = 'PRODUCT_LEVEL4'
               THEN
                  SELECT COUNT (PC.PROD_CTRL_CD)
                    INTO L_COUNT
                    FROM PROD_CTRL PC
                   WHERE     PC.EZCANCELFLAG = '0'
                         AND PC.GLBL_CMPY_CD = 'ADB'
                         AND PC.MDSE_STRU_ELMNT_TP_CD = 'PL3'
                         AND UPPER(PC.PROD_CTRL_NM) = UPPER(REC_ITEMS.PRODUCT_LEVEL4)
                         AND PC.SCD_PROD_HRCH_CD IN
                                (SELECT PC1.PROD_CTRL_CD
                                   FROM PROD_CTRL PC1
                                  WHERE  UPPER(PC1.PROD_CTRL_NM) = UPPER(REC_ITEMS.PRODUCT_LEVEL3)
                                  --WHERE  REPLACE (UPPER (PC1.PROD_CTRL_NM), ',', ' ') = REC_ITEMS.PRODUCT_LEVEL3
                                       --PC1.PROD_CTRL_NM = REC_ITEMS.PRODUCT_LEVEL3
                                    AND PC1.EZCANCELFLAG = PC.EZCANCELFLAG
                                    AND PC1.GLBL_CMPY_CD = PC.GLBL_CMPY_CD
                                    AND PC1.MDSE_STRU_ELMNT_TP_CD = 'PL2');
                  IF L_COUNT = 0
                  THEN
                     P_ERR_FLG := 'S';
                     P_ERR_MSG :=
                           'Canon Item#->'
                        || L_ITEM
                        || ' - [Product Level4] is incompatible with [Product Level3].';
                     INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                            P_USER_ID,
                                            L_ITEM,
                                            REQ_COL.ATTRIBUTE_NAME,
                                            P_ERR_MSG);
                  END IF;
               ELSIF REQ_COL.MAP_COL = 'SUPPLIER_SITE'
               THEN
                  SELECT COUNT (DV.INV_VND_CD)
                    INTO L_COUNT
                    FROM VND DV
                   WHERE     DV.EZCANCELFLAG = '0'
                         AND DV.GLBL_CMPY_CD = 'ADB'
                         AND UPPER (L_COL_VALUE) IN
                                (UPPER (DV.INV_VND_CD), UPPER (DV.ARCS_SPLY_SITE_CD))
                         AND DV.PRNT_VND_PK IN
                                (SELECT PV.PRNT_VND_PK
                                   FROM PRNT_VND PV
                                  WHERE     UPPER (REC_ITEMS.SUPPLIER) IN
                                               (UPPER (PV.PRNT_VND_CD),
                                                UPPER (PV.PRNT_VND_NM))
                                        AND PV.EZCANCELFLAG = DV.EZCANCELFLAG
                                        AND PV.GLBL_CMPY_CD = DV.GLBL_CMPY_CD);

                  IF L_COUNT = 0
                  THEN
                     P_ERR_FLG := 'S';
                     P_ERR_MSG :=
                           'Canon Item#->'
                        || L_ITEM
                        || ' - [Supplier Site] does not exist for [Supplier].';
                     INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                            P_USER_ID,
                                            L_ITEM,
                                            REQ_COL.ATTRIBUTE_NAME,
                                            P_ERR_MSG);
                  END IF;
            /*   ELSIF REQ_COL.MAP_COL = 'UNIT_OF_MEASURE'
               THEN
                  SELECT COUNT (PU.PKG_UOM_CD)
                    INTO L_COUNT
                    FROM PKG_UOM PU
                   WHERE     PU.EZCANCELFLAG = '0'
                         AND PU.GLBL_CMPY_CD = 'ADB'
                         AND UPPER (PU.PKG_UOM_CD) = UPPER (L_COL_VALUE)
                         AND PU.PKG_UOM_CLS_CD IN
                                (SELECT PUC.PKG_UOM_CLS_CD
                                   FROM PKG_UOM_CLS PUC
                                  WHERE     PUC.PKG_UOM_CLS_CD = REC_ITEMS.UOM_CLASS
                                        AND PUC.EZCANCELFLAG = PU.EZCANCELFLAG
                                        AND PUC.GLBL_CMPY_CD = PU.GLBL_CMPY_CD);

                  IF L_COUNT = 0
                  THEN
                     P_ERR_FLG := 'S';
                     P_ERR_MSG :=
                           'Canon Item#->'
                        || L_ITEM
                        || ' - [Unit of Measure] and [UOM Class] are Incompatible.';
                     INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                            P_USER_ID,
                                            L_ITEM,
                                            REQ_COL.ATTRIBUTE_NAME,
                                            P_ERR_MSG);
                  END IF;  */
               ELSIF     REQ_COL.MAP_COL = 'IMAGEWARE_REMOTE_ENABLED'
                     AND UPPER (L_COL_VALUE) IN ('Y', 'YES')
                     AND REC_ITEMS.IMAGEWARE_REMOTE_MODEL IS NULL
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - '
                     || ' If [Imageware Remote Enabled] is [Y], then [Imageware Remote Model] is mandatory.';
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     REQ_COL.MAP_COL = 'SERVICE_CALL_ENABLED'
                     AND UPPER (L_COL_VALUE) IN ('Y', 'YES')
                     AND UPPER (NVL (REC_ITEMS.INSTALLBASE_CONTROLLED, 'N')) IN
                            ('N', 'NO')
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - '
                     || ' If [Service Call Enabled] is [Y], then [Installbase Controlled] must be [Y].';
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     REQ_COL.MAP_COL = 'SERIAL_CONTROL'
                     AND UPPER (NVL (L_COL_VALUE, 'NOTCONTROLLED')) != 'NOTCONTROLLED'
                     AND UPPER (NVL (REC_ITEMS.INSTALLBASE_CONTROLLED, 'N')) IN
                            ('N', 'NO')
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - '
                     || ' If [Serial Control] is controlled, then [Installbase Controlled] must be [Y].';
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         REC_ITEMS.PROJ_ITEM_ID,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);

               END IF;
            END IF;

            /*   IF REQ_COL.WORKBENCH_DISPLAY = 'NONE'
               THEN
                  L_STR :=
                     '. Change default value for this attribute in the Template.';
               ELSE
                  L_STR := NULL;
               END IF;   */

            --To validate Item Attribute values against S21 code tables.
            IF     REQ_COL.LOV_FLAG = 'Y'
               AND REQ_COL.MAP_COL_LIST IS NOT NULL
               AND L_COL_VALUE IS NOT NULL
            THEN
               L_VAL_EXIST := 'N';

               IF REQ_COL.MAP_COL_LIST IN ('DEFAULT_SRC_WH_LIST',
                             'MATERIAL_GROUP1_LIST',
                             --'SERVICE_MODEL_LIST',
                             'SUPPLIER_SITE_LIST',
                             'SUPPLIER_LIST',
                             'TARIFF_CODE_LIST',
                             'PRODUCT_LEVEL5_LIST')
               THEN
                L_LIST_CODE1 := '-00000';
               ELSE
                L_LIST_CODE1 := NULL;
               END IF;

               BEGIN
                  L_DYN_LOV_SQL :=
                        'BEGIN CANON_E008_ITEM_TEMPLATE_PKG.'
                     || REQ_COL.MAP_COL_LIST
                     || '( :L_LIST_CODE1, :l_TEMPLATE_LIST_TBL, :l_ERR_FLG , :l_ERR_MSG); END;';

                  EXECUTE IMMEDIATE L_DYN_LOV_SQL
                     USING IN L_LIST_CODE1, OUT L_TEMPLATE_LIST_TBL, OUT L_ERR_FLG, OUT L_ERR_MSG;

                  LOOP
                     FETCH L_TEMPLATE_LIST_TBL INTO L_LIST_CODE, L_LIST_NAME;

                     EXIT WHEN L_TEMPLATE_LIST_TBL%NOTFOUND;

                     IF  UPPER (L_LIST_NAME) = UPPER (L_COL_VALUE) -- (UPPER (L_LIST_NAME) LIKE '%' || UPPER (L_COL_VALUE))
                        OR L_LIST_CODE = L_COL_VALUE
                     THEN
                        L_VAL_EXIST := 'Y';

                            EXIT;
                     END IF;
                  END LOOP;

                  CLOSE L_TEMPLATE_LIST_TBL;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     INSERT_ERROR ('VALIDATE_UPLOAD_ITEMS-DYN LOV',
                                   SUBSTR (SQLERRM, 1, 400),
                                   L_ERRBUF,
                                   L_RETCODE);
               END;

               IF NVL (L_VAL_EXIST, 'N') = 'N' AND REQ_COL.MAP_COL_LIST NOT IN ('MARKETING_MODEL_LIST', 'SERVICE_MODEL_LIST' )
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] has incorrect value. Populate value from Template attribute values list.';
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         L_ITEM,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               END IF;
            --To validate Item Attribute values against S21 Column size
            ELSIF REQ_COL.LOV_FLAG = 'N' AND L_COL_VALUE IS NOT NULL
            THEN
               IF LENGTH (L_COL_VALUE) > SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 4)
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] exceeds S21 mapping column length:'
                     || SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 4);
                  --|| L_STR;
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         L_ITEM,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 1, 3) = 'NUM'
                     AND NOT REGEXP_LIKE (TRIM (L_COL_VALUE), '^\d+$')
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] should only accept whole number.';
                  -- || L_STR;
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         L_ITEM,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 1, 3) = 'NPC'
                     AND REQ_COL.MAP_COL IN
                            ('STANDARD_COST', 'ASSET_RECOVERY_COST', 'PURCHASE_PRICE')
                     AND NOT REGEXP_LIKE (L_COL_VALUE,
                                          '^(\d|(\.\d{1,2})?$)+(\.\d{1,2})?$')
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] accepts either whole or decimal number upto 2 decimals.';
                  --|| L_STR;
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         L_ITEM,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               ELSIF     SUBSTR (REQ_COL.S21_MAP_COL_SIZE, 1, 3) = 'NPC'
                     AND NOT REGEXP_LIKE (L_COL_VALUE,
                                          '^(\d|(\.\d{1,4})?$)+(\.\d{1,4})?$')
               THEN
                  P_ERR_FLG := 'S';
                  P_ERR_MSG :=
                        'Canon Item#->'
                     || L_ITEM
                     || ' - ['
                     || REQ_COL.ATTRIBUTE_NAME
                     || '] accepts either whole or decimal number upto 4 decimals.';
                  --|| L_STR;
                  INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                         P_USER_ID,
                                         L_ITEM,
                                         REQ_COL.ATTRIBUTE_NAME,
                                         P_ERR_MSG);
               END IF;
            END IF;                                                     --REQ_COL.LOV_FLAG
         END LOOP;

         IF     (   REC_ITEMS.SUPPLIER IS NOT NULL
                 OR REC_ITEMS.SUPPLIER_ITEM IS NOT NULL
                 OR REC_ITEMS.SUPPLIER_SITE IS NOT NULL)
            AND (   REC_ITEMS.SUPPLIER IS NULL
                 OR REC_ITEMS.SUPPLIER_ITEM IS NULL
                 OR REC_ITEMS.SUPPLIER_SITE IS NULL)
         THEN
            P_ERR_FLG := 'S';
            P_ERR_MSG :=
                  'Canon Item#->'
               || L_ITEM
               || ' - [Supplier] [Supplier Site] [Supplier Item] all or none required.';
            INSERT_ERROR_PROJ_TBL (P_PROJECT_NO,
                                   P_USER_ID,
                                   REC_ITEMS.PROJ_ITEM_ID,
                                   NULL,
                                   P_ERR_MSG);
         END IF;
      END LOOP;

      FOR PROJ_ERR IN (  SELECT DISTINCT ITEM_ID, MESSAGE
                           FROM CANON_E008_PROJECT_ERR_TBL
                          WHERE PROJECT_ID = P_PROJECT_NO AND USER_ID = P_USER_ID
                       ORDER BY ITEM_ID)
      LOOP
         L_PROJECT_ERROR := SUBSTR (L_PROJECT_ERROR || PROJ_ERR.MESSAGE || '##', 1, 32000);
      END LOOP;

      P_PROJECT_ERROR := SUBSTR (L_PROJECT_ERROR, 1, LENGTH (L_PROJECT_ERROR) - 2);
   /*  OPEN P_PROJECT_ERROR FOR
          SELECT DISTINCT PROJECT_ID,
                          USER_ID,
                          ITEM_ID,
                          FIELD_NAME,
                          MESSAGE
            FROM CANON_E008_PROJECT_ERR_TBL
           WHERE PROJECT_ID = P_PROJECT_NO AND USER_ID = P_USER_ID
        ORDER BY ITEM_ID;            --, TO_NUMBER (SUBSTR (FIELD_NAME, 12));  */
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END VALIDATE_UPLOAD_ITEMS;

   PROCEDURE GET_PROJ_ERRORS (P_PROJECT_NO       IN     VARCHAR2,
                              P_PROJECT_ERRORS      OUT G_REF_CUR_TYP,
                              P_ERR_FLG             OUT VARCHAR2)
   IS
   BEGIN
      OPEN P_PROJECT_ERRORS FOR
         SELECT ITEM_NUMBER, ITEM_DESCRIPTION, PROCESS_MESSAGE
           FROM CANON_E008_PROJ_ITEMS_TBL
          WHERE PROCESS_FLAG = 'F' AND PROJECT_ID = P_PROJECT_NO;

      P_ERR_FLG := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         P_ERR_FLG := 'F';
         ROLLBACK;
   END GET_PROJ_ERRORS;

   /*
   PROCEDURE GET_CUSA_ITEMS (P_PROJECT_NO   IN     NUMBER,
                              P_ITEM_NO         IN     VARCHAR2,
                              P_ITEM_TBL        OUT G_REF_CUR_TYP,
                              P_ERR_FLG         OUT VARCHAR2,
                              P_ERR_MSG         OUT VARCHAR2)
   IS
   BEGIN
      OPEN P_ITEM_TBL FOR
          SELECT 1,2,3,ITEM_NUMBER, ITEM_DESCRIPTION, PROCESS_MESSAGE,'','','','',''
           FROM CANON_E008_PROJ_ITEMS_TBL
          WHERE PROJECT_ID = 1416;
      P_ERR_FLG := 'S';

   EXCEPTION
      WHEN OTHERS
      THEN
         P_ERR_FLG := 'F';
   END GET_CUSA_ITEMS;  */

   PROCEDURE GET_CUSA_ITEMS (P_TEMPLATE_NAME   IN  VARCHAR2,
                            P_PROJECT_NO      IN     NUMBER,
                            P_ITEM_NO         IN     VARCHAR2,
                            P_ITEM_MAIN_ATTR_VALUES   OUT G_REF_CUR_TYP,
                            P_BOM_COMPONENTS          OUT G_REF_CUR_TYP,
                            P_ERR_FLG                 OUT VARCHAR2,
                            P_ERR_MSG                 OUT VARCHAR2)
   IS
      L_ERR_MSG                 VARCHAR2 (2000);

      CURSOR CUR_DEFAULT_ATTR_NONE
      IS
           SELECT TEMPLATE_NAME,
                  DISPLAY_SORT,
                  WORKBENCH_DISPLAY,
                  ATTRIBUTE_NAME,
                  DEFAULT_VALUE,
                  REQUIRED_VALUE,
                  E008_WB_TBL_MAP,
                  LOV_FLAG,
                  CSA_TABLE,
                  CSA_COLUMN
             FROM (SELECT TEMPLATE_NAME,
                          0 DISPLAY_SORT,
                          --'MAIN' WORKBENCH_DISPLAY,
                          'X' APPROVAL_GROUP_OWNER,
                          'Template Id' ATTRIBUTE_NAME,
                          'MAIN' WORKBENCH_DISPLAY,
                          NULL REQUIRED_VALUE,
                          TO_CHAR (TEMPLATE_ID) DEFAULT_VALUE,
                          NULL E008_WB_TBL_MAP,
                          NULL LOV_FLAG,
                          NULL CSA_TABLE,
                          NULL CSA_COLUMN
                     FROM CANON_E008_TEMPLATE_HDR_TBL
                    WHERE TEMPLATE_NAME = DECODE(P_TEMPLATE_NAME,'','EQUIPMENT - COPIER - CANON',P_TEMPLATE_NAME)
                   UNION
                   SELECT th.TEMPLATE_NAME,
                          TAT.DISPLAY_SORT,
                          --TAT.WORKBENCH_DISPLAY,
                          APPROVAL_GROUP_OWNER,
                          TAT.ATTRIBUTE_NAME,
                          DECODE (TAT.WORKBENCH_DISPLAY, 'MAIN', 'M', 'A')
                             WORKBENCH_DISPLAY,
                          DECODE (REQUIRED_VALUE, 'YES', 'Y', 'N') REQUIRED_VALUE,
                          NVL (TAAT.DEFAULT_VALUE, '') DEFAULT_VALUE, --NVL (TAAT.DEFAULT_VALUE, '')
                          TAT.E008_WB_TBL_MAP,
                          TAT.LOV_FLAG,
                          TAT.CSA_TABLE,
                          TAT.CSA_COLUMN
                     FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                          CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                          CANON_E008_TEMPLATE_HDR_TBL TH
                    WHERE     1 = 1
                          AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                          AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                          AND TH.TEMPLATE_NAME = DECODE(P_TEMPLATE_NAME,'','EQUIPMENT - COPIER - CANON',P_TEMPLATE_NAME)
                          AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                          AND TAT.ENABLE_FLAG = 'Y'
                   --AND TAT.APPROVAL_GROUP_OWNER = 'ITEM MASTER'
                   UNION
                   SELECT th.TEMPLATE_NAME,
                          TAT.DISPLAY_SORT,
                          --TAT.WORKBENCH_DISPLAY,
                          APPROVAL_GROUP_OWNER,
                          TAT.ATTRIBUTE_NAME,
                          DECODE (TAT.WORKBENCH_DISPLAY, 'MAIN', 'M', 'A')
                             WORKBENCH_DISPLAY,
                          DECODE (REQUIRED_VALUE, 'YES', 'Y', 'N') REQUIRED_VALUE,
                          NVL (TAAT.DEFAULT_VALUE, 'NULL') DEFAULT_VALUE, --NVL (TAAT.DEFAULT_VALUE, '')
                          TAT.E008_WB_TBL_MAP,
                          TAT.LOV_FLAG,
                          TAT.CSA_TABLE,
                          TAT.CSA_COLUMN
                     FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                          CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                          CANON_E008_TEMPLATE_HDR_TBL TH
                    WHERE     1 = 1
                          AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                          AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                          AND TH.TEMPLATE_NAME = DECODE(P_TEMPLATE_NAME,'','EQUIPMENT - COPIER - CANON',P_TEMPLATE_NAME)
                          AND TAT.WORKBENCH_DISPLAY = 'ADDITIONAL'
                          AND TAT.ENABLE_FLAG = 'Y')
         ORDER BY DISPLAY_SORT;

      v_sql                     VARCHAR2 (4000);
      p_input1                  VARCHAR2 (200);
      l_item_main_attr_values   G_REF_CUR_TYP;
      l_csa_value               VARCHAR2 (4000):=NULL;
      l_item_type               VARCHAR2 (200);
      l_valid_item              VARCHAR2 (1):='N';
      l_check_merch_item        NUMBER;
      l_spec_wght_part          NUMBER;
      l_code_wght_unit_part     VARCHAR2 (10);
      l_uom                     VARCHAR2 (10);
      TYPE MyRec IS RECORD
      (
         col1   VARCHAR2 (100),
         col2   VARCHAR2 (200)
      );                                                               --define the record

      rec                       MyRec;
   BEGIN
     DELETE FROM CANON_E008_TEMP_LOV_TBL;

     BEGIN
         SELECT  UPPER (MDSE_ITEM_TP_NM)
            INTO l_item_type
           FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                CANON_E008_TEMPLATE_HDR_TBL TH,
                MDSE_ITEM_TP MIT
          WHERE     1 = 1
                AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                AND TAT.ATTRIBUTE_NAME = 'Item Type'
                AND TH.TEMPLATE_NAME = DECODE(P_TEMPLATE_NAME,'','EQUIPMENT - COPIER - CANON',P_TEMPLATE_NAME)
                AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                AND TAT.ENABLE_FLAG = 'Y'
                AND MIT.MDSE_ITEM_TP_CD = TAAT.default_value;

        IF l_item_type <> 'PARTS' THEN

            select count(1)
            into l_check_merch_item
            from AMER_CMPY
            where amer_cmpy_cd = 'ADB'
            and   amer_xpnd_tp_cd = '1'
            and   amer_mdse_cd = P_ITEM_NO
            and  EXISTS ( select 1
                          from CSA_MDSE_ITEM_V@S21cusa.cusa.canon.com
                          where mdse_cd = P_ITEM_NO );

            IF l_check_merch_item > 0 THEN
               l_valid_item := 'Y';
            ELSE
               l_valid_item := 'N';
            END IF;
        ELSE
            l_valid_item := 'Y';

           BEGIN
             SELECT P_SPEC_WGHT_PART,P_code_WGHT_UNIT_PART
             INTO  l_spec_wght_part,l_code_wght_unit_part
             FROM CSA_PRT_V@S21cusa.cusa.canon.com
             WHERE p_parts_num = P_ITEM_NO;
           EXCEPTION
              WHEN OTHERS THEN
                   l_spec_wght_part := 0;
                   l_code_wght_unit_part := '';
           END;

        END IF;

     EXCEPTION
        WHEN OTHERS THEN
         l_valid_item := 'N';
     END;


      IF l_valid_item = 'Y' THEN

          FOR REC_ITEMS IN CUR_DEFAULT_ATTR_NONE
          LOOP

               IF REC_ITEMS.CSA_COLUMN IS NOT NULL THEN
                     BEGIN

                     IF REC_ITEMS.CSA_COLUMN IN ( 'IN_POUND_WT','IN_INCH_LG','IN_INCH_WDT','IN_INCH_HGT') THEN

                        BEGIN

                           SELECT NVL (TAAT.DEFAULT_VALUE, '')
                            INTO l_uom
                             FROM CANON_E008_TEMPLATE_ATTR_TBL TAT,
                                  CANON_E008_TEMPL_ATTR_ASGN_TBL TAAT,
                                  CANON_E008_TEMPLATE_HDR_TBL TH
                            WHERE     1 = 1
                                  AND TAT.ATTRIBUTE_ID = TAAT.ATTRIBUTE_ID
                                  AND TH.TEMPLATE_ID = TAAT.TEMPLATE_ID
                                  AND TH.TEMPLATE_NAME = DECODE(P_TEMPLATE_NAME,'','EQUIPMENT - COPIER - CANON',P_TEMPLATE_NAME)
                                  AND TAT.WORKBENCH_DISPLAY = 'MAIN'
                                  AND TAT.ATTRIBUTE_NAME = 'UOM'
                                  AND TAT.ENABLE_FLAG = 'Y';

                        EXCEPTION
                          WHEN OTHERS THEN
                                l_uom := '';
                        END;
                        v_sql := 'SELECT ' || REC_ITEMS.CSA_COLUMN || ' FROM ' || REC_ITEMS.CSA_TABLE || ' WHERE MDSE_CD = ''' || P_ITEM_NO || ''' AND PKG_UOM_CD = ''' || l_uom || '''' ;
                     ELSE
                        v_sql := 'SELECT ' || REC_ITEMS.CSA_COLUMN || ' FROM ' || REC_ITEMS.CSA_TABLE || ' WHERE MDSE_CD = ''' || P_ITEM_NO || '''' ;
                     END IF;


                     EXECUTE IMMEDIATE v_sql INTO l_csa_value;

                     EXCEPTION
                        WHEN OTHERS
                        THEN
                          l_csa_value := NULL;
                          EXIT;
                     END;
                ELSE
                    l_csa_value := NULL;
                END IF;

               IF     REC_ITEMS.LOV_FLAG = 'Y'
                   AND l_csa_value IS NOT NULL
                  --AND REC_ITEMS.DEFAULT_VALUE IS NOT NULL
                  ---AND REC_ITEMS.DEFAULT_VALUE <> 'NULL'
               THEN

                        v_sql :=
                              'BEGIN CANON_E008_ITEM_TEMPLATE_PKG.'
                           || SUBSTR (REC_ITEMS.E008_WB_TBL_MAP, 1, 25)
                           || '_LIST(:p_input1, :l_item_main_attr_values, :P_ERR_FLG,:P_ERR_MSG); END;';

                        DBMS_OUTPUT.PUT_LINE ('v_sql ' || v_sql);

                        p_input1 := l_csa_value;
                          --p_input1 := REC_ITEMS.DEFAULT_VALUE;


                        EXECUTE IMMEDIATE v_sql
                           USING IN p_input1,
                                 OUT l_item_main_attr_values,
                                 OUT P_ERR_FLG,
                                 OUT P_ERR_MSG;

                        LOOP
                           FETCH l_item_main_attr_values INTO rec;

                           EXIT WHEN l_item_main_attr_values%NOTFOUND;
                           DBMS_OUTPUT.put_line (
                              REC_ITEMS.template_name || ',' || rec.col1 || ',' || rec.col2);


                           INSERT INTO CANON_E008_TEMP_LOV_TBL
                                   VALUES (
                                             REC_ITEMS.DISPLAY_SORT,
                                             REC_ITEMS.ATTRIBUTE_NAME,
                                                REC_ITEMS.WORKBENCH_DISPLAY
                                             || REC_ITEMS.REQUIRED_VALUE
                                             || REC_ITEMS.LOV_FLAG
                                             || REPLACE (rec.col1, ',', '')
                                             || '**'
                                             || REPLACE (rec.col2, ',', '')
                                             || '**'
                                             || REC_ITEMS.E008_WB_TBL_MAP);

                        EXIT;
                        END LOOP;

                        DBMS_OUTPUT.PUT_LINE ('P_ERR_FLG ' || P_ERR_FLG);
                        DBMS_OUTPUT.PUT_LINE ('P_ERR_MSG ' || P_ERR_MSG);
               ELSIF    REC_ITEMS.LOV_FLAG = 'Y'
                        AND l_csa_value IS NULL
                --AND REC_ITEMS.DEFAULT_VALUE IS NULL
               THEN
                     INSERT INTO CANON_E008_TEMP_LOV_TBL
                             VALUES (
                                       REC_ITEMS.DISPLAY_SORT,
                                       REC_ITEMS.ATTRIBUTE_NAME,
                                          REC_ITEMS.WORKBENCH_DISPLAY
                                       || REC_ITEMS.REQUIRED_VALUE
                                       || REC_ITEMS.LOV_FLAG
                                       || null
                                       || '**'
                                       || null
                                       || '**'
                                       || REC_ITEMS.E008_WB_TBL_MAP);
               ELSE
                         IF REC_ITEMS.CSA_COLUMN IS NOT NULL
                            AND REC_ITEMS.E008_WB_TBL_MAP= 'WEIGHT'
                              AND l_spec_wght_part <> 0
                                AND  l_code_wght_unit_part = 'MG' THEN

                              l_csa_value := (l_spec_wght_part*0.00000220462);

                          END IF;

                         IF REC_ITEMS.CSA_COLUMN IS NOT NULL THEN
                             INSERT INTO CANON_E008_TEMP_LOV_TBL
                                  VALUES (
                                            REC_ITEMS.DISPLAY_SORT,
                                            REC_ITEMS.ATTRIBUTE_NAME,
                                               REC_ITEMS.WORKBENCH_DISPLAY
                                            || REC_ITEMS.REQUIRED_VALUE
                                            || REC_ITEMS.LOV_FLAG
                                            || REPLACE (l_csa_value, ',', ''));
                         ELSE
                             INSERT INTO CANON_E008_TEMP_LOV_TBL
                                    VALUES (
                                              REC_ITEMS.DISPLAY_SORT,
                                              REC_ITEMS.ATTRIBUTE_NAME,
                                                 REC_ITEMS.WORKBENCH_DISPLAY
                                              || REC_ITEMS.REQUIRED_VALUE
                                              || REC_ITEMS.LOV_FLAG
                                              || null);
                         END IF;
              END IF;
           END LOOP;
      END IF;

      COMMIT;

      OPEN P_ITEM_MAIN_ATTR_VALUES FOR
           SELECT FIELD2, FIELD3
             FROM CANON_E008_TEMP_LOV_TBL
         ORDER BY Field1 ASC;


      OPEN P_BOM_COMPONENTS FOR
         SELECT CHILD_MDSE_CD
                FROM CSA_CMPSN_V@S21cusa.cusa.canon.com
                WHERE prnt_mdse_cd = P_ITEM_NO
                AND CHILD_MDSE_CD IS NOT NULL;

      P_ERR_FLG := 'S';

   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
   END GET_CUSA_ITEMS;

   PROCEDURE SAVE_CUSA_BOM_ITEMS (P_PROJECT_NO      IN     NUMBER,
                                  P_ITEM_NO         IN     VARCHAR2,
                                  P_ITEM_ID         IN     NUMBER,
                                  P_USER_ID         IN     VARCHAR2,
                                  P_ERR_FLG            OUT VARCHAR2,
                                  P_ERR_MSG            OUT VARCHAR2)
   IS
      L_INDEX     NUMBER;
      L_ERR_MSG   VARCHAR2 (2000);
      L_EXISTS    VARCHAR2 (1);
      L_ERRBUF    VARCHAR2 (200);
      L_RETCODE   VARCHAR2 (200);
      L_PROJ_ITEM_ID  NUMBER;
      l_csa_value  VARCHAR2 (240);
      v_sql        VARCHAR2 (2000);
      v_updsql     VARCHAR2 (2000);

      CURSOR CUR_BOM_COMP
      IS
      SELECT PRNT_MDSE_CD,
              MDSE_CMPSN_TP_CD,
              CHILD_MDSE_CD,
              CHILD_ORD_TAKE_MDSE_CD,
              CHILD_MDSE_QTY,
              MAIN_CMPSN_FLG,
              EFF_FROM_DT,
              'EA'
      FROM CSA_CMPSN_V@S21cusa.cusa.canon.com
      WHERE prnt_mdse_cd = P_ITEM_NO
      AND CHILD_MDSE_CD IS NOT NULL
      AND NOT EXISTS (SELECT 1 FROM CANON_E008_BOM_ASSIGN_TBL WHERE SET_ITEM_ID =P_ITEM_ID AND COMPONENT_ITEM = CHILD_MDSE_CD) ;


      CURSOR CUR_DEFAULT_ATTR_NONE
      IS
           SELECT ---TAT.DISPLAY_SORT,
                  TAT.ATTRIBUTE_NAME,
                  --DECODE (TAT.WORKBENCH_DISPLAY, 'MAIN', 'M', 'A')  WORKBENCH_DISPLAY,
                  TAT.E008_WB_TBL_MAP,
                  --TAT.LOV_FLAG,
                  TAT.CSA_TABLE,
                  TAT.CSA_COLUMN
             FROM CANON_E008_TEMPLATE_ATTR_TBL TAT
            WHERE     TAT.ENABLE_FLAG = 'Y'
            AND CSA_TABLE IS NOT NULL;

   BEGIN

         INSERT_ERROR ('SAVE_CUSA_BOM_ITEMS',
                       'BEGIN',
                       L_ERRBUF,
                       L_RETCODE);


     FOR REC_ITEMS IN CUR_BOM_COMP
      LOOP

       /*
        BEGIN

              SELECT CANON_E008_PROJ_ITEM_ID_SEQ.NEXTVAL
                    INTO L_PROJ_ITEM_ID
                    FROM DUAL;

               INSERT INTO CANON_E008_PROJ_ITEMS_TBL (EZTABLEID,
                                                      COMPANY_CODE,
                                                      PROJECT_ID,
                                                      TEMPLATE_ID,
                                                      PROJ_ITEM_ID,
                                                      --ITEM_DESCRIPTION,
                                                      ITEM_NUMBER,
                                                      CREATED_BY,
                                                      CREATION_DATE,
                                                      LAST_UPDATE_BY,
                                                      LAST_UPDATE_DATE)
                       VALUES (
                                 'CANON_E008_PROJ_ITEMS_TBL',
                                 'ADB',
                                 P_PROJECT_NO,
                                 1,
                                 L_PROJ_ITEM_ID,
                                 --NULL,
                                 REC_ITEMS.CHILD_MDSE_CD,
                                 P_USER_ID,
                                 SYSDATE,
                                 P_USER_ID,
                                 SYSDATE);

         EXCEPTION
            WHEN OTHERS
            THEN
               INSERT_ERROR ('SAVE_CUSA_BOM_ITEMS',
                             SUBSTR ('1'||SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);
         END;  */

        /*
         FOR REC_ITEMS IN CUR_DEFAULT_ATTR_NONE
         LOOP

             IF REC_ITEMS.CSA_COLUMN IS NOT NULL THEN

               BEGIN
               v_sql := 'SELECT ' || REC_ITEMS.CSA_COLUMN || ' FROM ' || REC_ITEMS.CSA_TABLE || ' WHERE MDSE_CD = ''' || P_ITEM_NO || '''' ;

               INSERT_ERROR ('SAVE_CUSA_BOM_ITEMS',
                             SUBSTR ('1222 '||v_sql, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);

               EXECUTE IMMEDIATE v_sql INTO l_csa_value;

               EXCEPTION
                  WHEN OTHERS
                  THEN
                    l_csa_value := NULL;
               END;
            ELSE
              l_csa_value := NULL;
            END IF;

         IF l_csa_value IS NOT NULL THEN
              BEGIN
                 v_updsql := 'UPDATE CANON_E008_PROJ_ITEMS_TBL SET ' || REC_ITEMS.E008_WB_TBL_MAP || ' = ''' || l_csa_value || ''' WHERE PROJ_ITEM_ID = ' || P_ITEM_ID  ;
                  INSERT_ERROR ('SAVE_CUSA_BOM_ITEMS',
                             SUBSTR ('1333 '||v_updsql, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);
                 EXECUTE IMMEDIATE v_updsql;

                 EXCEPTION
                    WHEN OTHERS
                    THEN
                      NULL;
                     INSERT_ERROR ('SAVE_CUSA_BOM_ITEMS',
                             SUBSTR ('2'||SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);

                 END;
          END IF;

        END LOOP;
        */

         BEGIN
            INSERT INTO CANON_E008_BOM_ASSIGN_TBL (EZTABLEID,
                                                   EZINCOMPANYCODE,
                                                   SET_ITEM_ID,
                                                   BOM_INSTRUCTIONS,
                                                   COMPONENT_ITEM,
                                                   QTY,
                                                   CREATED_BY,
                                                   CREATION_DATE,
                                                   LAST_UPDATE_BY,
                                                   LAST_UPDATE_DATE)
                 VALUES ('CANON_E008_BOM_ASSIGN_TBL',
                         'ADB',
                         P_ITEM_ID,
                         NULL,
                         TRIM (REC_ITEMS.CHILD_MDSE_CD),
                         REC_ITEMS.CHILD_MDSE_QTY,
                         P_USER_ID,
                         SYSDATE,
                         P_USER_ID,
                         SYSDATE);
         EXCEPTION
            WHEN OTHERS
            THEN
               INSERT_ERROR ('SAVE_CUSA_BOM_ITEMS',
                             SUBSTR ('3'||SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);
         END;

      END LOOP;




      P_ERR_FLG := 'S';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         L_ERR_MSG := SUBSTR (SQLERRM, 1, 400);
         P_ERR_FLG := 'E';
         P_ERR_MSG := L_ERR_MSG;
               INSERT_ERROR ('SAVE_CUSA_BOM_ITEMS',
                             SUBSTR ('4'||SQLERRM, 1, 400),
                             L_ERRBUF,
                             L_RETCODE);

        ROLLBACK;
   END SAVE_CUSA_BOM_ITEMS;



END CANON_E008_ITEM_WORKBENCH_PKG;
/