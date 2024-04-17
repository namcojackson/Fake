CREATE OR REPLACE PACKAGE CANON_E479_INV_PKG
AS
   /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_INV_PKG.sql                                                 *
     * DESCRIPTION     : This Package Contains Components for searching Excel Invoice           *
     *                   Search for approval and e-mail                                         *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/15/2015     Initial Creation                                        *
	 * Lakshmi Gopalsami 09/21/2017     GSD Special Billing changes                             *
	 * Anil              03/16/2018     GSD Custom Billing Non-Serialized Issue                 *
   *************************************************************************************************/
   
   TYPE RESULT_CURSOR IS REF CURSOR;

   l_package_name   VARCHAR2 (30) := 'CANON_E479_INV_PKG';
   PROCEDURE get_inv_details (p_inv_number   IN     NUMBER,
                              x_data            OUT result_cursor);


   PROCEDURE get_urn_info (p_urn_number     IN     VARCHAR2,
                           x_control_data      OUT RESULT_CURSOR,
                           x_remit_data        OUT RESULT_CURSOR,
                           x_line_data         OUT RESULT_CURSOR,
                           x_summary_data      OUT RESULT_CURSOR,
                           x_tax_data          OUT result_cursor,
                           x_comments          OUT VARCHAR2,
                           x_term              OUT VARCHAR2);

   PROCEDURE get_sale_info (p_urn_number   IN     VARCHAR2,
                            x_sale_data       OUT RESULT_CURSOR);


   PROCEDURE create_invoice_record (p_invoice_id             IN     NUMBER,
                                    p_urn_number             IN     VARCHAR2,
                                    p_invoice_type           IN     VARCHAR2,
                                    p_invoice_region         IN     VARCHAR2,
                                    p_biller_name            IN     VARCHAR2,
                                    p_biller_email           IN     VARCHAR2,
                                    p_customer_email         IN     VARCHAR2,
                                    p_other_email            IN     VARCHAR2,
                                    p_review_required        IN     VARCHAR2,
                                    p_parent_customer_name   IN     VARCHAR2,
                                    p_customer_group         IN     VARCHAR2,
                                    p_customer_name          IN     VARCHAR2,
                                    p_bill_location          IN     VARCHAR2,
                                    p_bill_number            IN     VARCHAR2,
                                    p_bill_date              IN     DATE,
                                    p_invoice_break          IN     VARCHAR2,
                                    p_bill_period            IN     VARCHAR2,
                                    p_amount_due             IN     NUMBER,
                                    p_invoice_path           IN     VARCHAR2,
                                    p_pending_action         IN     VARCHAR2,
                                    p_user_id                IN     NUMBER,
                                    p_file_name              IN     VARCHAR2,
                                    p_remarks                IN     VARCHAR2,
                                    x_status                    OUT NUMBER);

   PROCEDURE get_invoices_to_create (x_data OUT RESULT_CURSOR);



   PROCEDURE get_company_info (x_company_name   OUT VARCHAR2,
                               x_address1       OUT VARCHAR2,
                               x_address2       OUT VARCHAR2,
                               x_city           OUT VARCHAR2,
                               x_state          OUT VARCHAR2,
                               x_postal_code    OUT VARCHAR2,
                               x_phone1         OUT VARCHAR2,
                               x_url            OUT VARCHAR2);


   PROCEDURE find_any_pending_invoices (p_invoice_id   IN     NUMBER,
                                        x_flag            OUT VARCHAR2);

   FUNCTION is_any_pending_invoice (p_invoice_id IN NUMBER)
      RETURN VARCHAR2;

   PROCEDURE get_inv_details_srch_tbl (p_inv_number   IN     NUMBER,
                                       x_data            OUT result_cursor);

   PROCEDURE search_custom_invoices (
      p_user_id                IN     NUMBER,
      p_user_role              IN     VARCHAR2,
      p_bill_number            IN     VARCHAR2,
      p_pending_action         IN     VARCHAR2,
      p_period                 IN     VARCHAR2,
      p_urn_number             IN     VARCHAR2,
      p_biller_name            IN     VARCHAR2,
      p_parent_customer_name   IN     VARCHAR2,
      p_customer_group         IN     VARCHAR2,
      p_customer_name          IN     VARCHAR2,
      p_location               IN     VARCHAR2,
      p_bill_from_date         IN     DATE,
      p_bill_to_date           IN     DATE,
      p_start_range            IN     NUMBER,
      p_end_range              IN     NUMBER,
      p_sort_by                IN     VARCHAR2,
      p_sort_type              IN     VARCHAR2,
      x_data                      OUT result_cursor,
      x_count                     OUT NUMBER,
      x_amount_tot                OUT NUMBER);


   PROCEDURE approve_reject_invoices (p_invoice_id   IN     NUMBER,
                                      p_status       IN     VARCHAR2,
                                      p_user_id      IN     NUMBER,
                                      p_comments     IN     VARCHAR2,
                                      x_status          OUT NUMBER);

   PROCEDURE get_inv_details_for_approval (
      p_inv_number   IN     NUMBER,
      x_data            OUT result_cursor);

   PROCEDURE get_initial_path(
       p_source  IN     VARCHAR2,
       x_data       OUT VARCHAR2);

   PROCEDURE get_print_server_url (x_data OUT VARCHAR2);

   FUNCTION get_unique_count_maint_rental (p_order_type     IN VARCHAR2,
                                           p_product_type   IN VARCHAR2,
                                           p_charge_type    IN VARCHAR2,
                                           p_urn_number     IN VARCHAR2)
      RETURN NUMBER;

   PROCEDURE process_dups_fleet_serials (p_urn_number IN VARCHAR2);

   PROCEDURE get_urn_term_comments (p_urn_number   IN     VARCHAR2,
                                    x_comments        OUT VARCHAR2,
                                    x_term            OUT VARCHAR2);


   PROCEDURE get_urn_details_for_text (p_urn_number   IN     VARCHAR2,
                                       x_data            OUT result_cursor);
									   
   PROCEDURE get_bill_message (
      p_ds_acct_num   IN       NUMBER,
      p_loc_num       IN       NUMBER,
      x_message            OUT      VARCHAR2
   );								

   PROCEDURE get_bill_invoices_for_update(
       p_source       IN     VARCHAR2, -- AUTOMATIC/MANUAL_INVOICE_LIST/MANUAL_INVOICE_LIST_UPDATE
       p_urn_number   IN     VARCHAR2,	   
	   x_bill_data        OUT result_cursor,
	   x_inv_data         OUT result_cursor,
	   p_process_status        OUT VARCHAR2,
	   p_process_message       OUT VARCHAR2);

   PROCEDURE update_urn (p_urn_number IN VARCHAR2);	   
									   
/*
procedure is_user_biller(p_user_id in number, x_flag out varchar2);
procedure get_body_subject(p_name in varchar2, x_data out varchar2);
*/

END CANON_E479_INV_PKG;
/

SHOW ERRORS;

CREATE OR REPLACE PACKAGE BODY CANON_E479_INV_PKG
AS
    /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_INV_PKG.sql                                                 *
     * DESCRIPTION     : This Package Contains Components for searching Excel Invoice           *
     *                   Search for approval and e-mail                                         *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/15/2015     Initial Creation                                        *
	 * Lakshmi Gopalsami 09/21/2017     GSD Special Billing changes                             *
	 * Anil              03/16/2018     GSD Custom Billing Non-Serialized Issue                 *																						  
   *************************************************************************************************/
   
   
   PROCEDURE get_urn_info (p_urn_number     IN     VARCHAR2,
                           x_control_data      OUT RESULT_CURSOR,
                           x_remit_data        OUT RESULT_CURSOR,
                           x_line_data         OUT RESULT_CURSOR,
                           x_summary_data      OUT RESULT_CURSOR,
                           x_tax_data          OUT result_cursor,
                           x_comments          OUT VARCHAR2,
                           x_term              OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_urn_info';
      l_bill_number      VARCHAR2 (200);
      l_account_number   VARCHAR2 (100);
      l_location         VARCHAR2 (100);
      l_account_id       NUMBER;
      l_SITE_USE_ID      NUMBER;
      ln_terms           NUMBER;
	  l_prefix_bill_num   VARCHAR2 (100);
   BEGIN
     dbms_output.put_line('+++ Start get_urn_info +++');	 
     BEGIN
         SELECT SUBSTR (bill_number, 1, 6)
           INTO l_prefix_bill_num
           FROM canon_e479_inv_srch_tbl
          WHERE urn_number = p_urn_number;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_prefix_bill_num := NULL;                          -- 03-FEB-2017
      END;

      IF l_prefix_bill_num = 'MB_URN'
      THEN
         OPEN x_control_data FOR
            SELECT invoice_id inv_id,
                   urn_number,
                   '' seq_no,
                   '' control_type,
                   '' tab_name,
                   'BillPeriod=' || bill_period field1,
                   'Biller=' || biller_name field2,
                   'Company1=Canon Solutions America' field3,
                   'Company2=Inc.' field4,
                   ' ' field5,
                   'BillerEmail=' || biller_email field6,
                   'BillDate=' || bill_date field7,
                   '' field8,
                   '' field9,
                   '' field10,
                   '' field11,
                   '' field12,
                   '' field13,
                   '' field14,
                   '' field15,
                   '' field16,
                   '' field17,
                   '' field18,
                   '' field19,
                   '' field20,
                   '' field21,
                   '' field22,
                   '' field23,
                   '' field24,
                   '' field25,
                   '' field26,
                   '' field27,
                   '' field28,
                   '' field29,
                   '' field30,
                   '' field31,
                   '' field32,
                   '' field33,
                   '' field34,
                   '' field35,
                   '' field36,
                   '' field37,
                   '' field38,
                   '' field39,
                   '' field40,
                   '' field41,
                   '' field42,
                   '' field43,
                   '' field44,
                   '' field45,
                   '' field46,
                   '' field47,
                   '' field48,
                   '' field49,
                   '' field50,
                   '' field51,
                   '' field52,
                   '' field53,
                   '' field54,
                   '' field55,
                   '' field56,
                   '' field57,
                   '' field58,
                   '' field59,
                   '' field60,
                   '' field61,
                   '' field62,
                   '' field63,
                   '' field64,
                   '' field65,
                   '' field66,
                   '' field67,
                   '' field68,
                   '' field69,
                   '' field70,
                   '' field71,
                   '' field72,
                   '' field73,
                   '' field74,
                   '' field75,
                   '' field76,
                   '' field77,
                   '' field78,
                   '' field79,
                   '' field80,
                   '' field81,
                   '' field82,
                   '' field83,
                   '' field84,
                   '' field85,
                   '' field86,
                   '' field87,
                   '' field88,
                   '' field89,
                   '' field90,
                   '' field91,
                   '' field92,
                   '' field93,
                   '' field94,
                   '' field95,
                   '' field96,
                   '' field97,
                   '' field98,
                   '' field99,
                   '' field100,
                   created_by,
                   creation_date,
                   last_updated_by,
                   last_update_date
              FROM canon_e479_inv_srch_tbl
             WHERE urn_number = p_urn_number;
       			 
      ELSE 
		  BEGIN
			 process_dups_fleet_serials (p_urn_number);
		  EXCEPTION
			 WHEN OTHERS
			 THEN
				CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
														 l_procedure_name,
														 'SQL',
														 p_urn_number,
														 NULL,
														 NULL,
														 NULL,
														 NULL,
														 NULL,
														 SQLERRM);
		  END;

		  BEGIN
			 SELECT bill_number
			   INTO l_bill_number
			   FROM CANON_E479_EXCEL_CONTROL b
			  WHERE b.urn = p_urn_number;
		  EXCEPTION
			 WHEN OTHERS
			 THEN
				l_bill_number := '-1';
		  END;
		  
		  dbms_output.put_line('l_bill_number: '||l_bill_number);	 

		  IF (l_bill_number <> '-1')
		  THEN
			 BEGIN
				SELECT customer_number, bill_location
				  INTO l_account_number, l_location
				  FROM CANON_E479_INVOICE_MASTER
				 WHERE bill_number = l_bill_number AND ROWNUM < 2;
			 EXCEPTION
				WHEN OTHERS
				THEN
				   l_account_number := NULL;
				   l_location := NULL;
				   CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
															l_procedure_name,
															'SQL',
															p_urn_number,
															NULL,
															NULL,
															NULL,
															NULL,
															NULL,
															SQLERRM);
			 END;
			 dbms_output.put_line('l_bill_number: '||l_bill_number);	 
		  
		  
			  /*  Get the bill messages */
			  
			  CANON_E479_INV_PKG.get_bill_message(
				p_ds_acct_num => l_account_number,		
				p_loc_num     => l_location,
				x_message     => x_comments);
			  /* Get the terms from remittance table */
			  begin
					  SELECT TERMS
					  INTO x_term
					  FROM CANON_E479_EXCEL_remittance
					  WHERE bill_number = l_bill_number
						AND rownum=1;
			  exception
			  when others then
				 CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
							   l_procedure_name,
							   'SQL',
								p_urn_number,
								NULL,
								NULL,
								NULL,
								NULL,
								NULL,
								SQLERRM
						  );
			 end;

		  END IF;


		  OPEN x_control_data FOR
			 SELECT *
			   FROM CANON_E479_INV_DTL_TBL
			  WHERE urn_number = p_urn_number AND CONTROL_TYPE = 'CONTROL';

		  OPEN x_remit_data FOR
			 SELECT *
			   FROM CANON_E479_INV_DTL_TBL
			  WHERE urn_number = p_urn_number AND CONTROL_TYPE = 'REMITTANCESTUB';

		  OPEN x_line_data FOR
			   SELECT *
				 FROM CANON_E479_INV_DTL_TBL
				WHERE     urn_number = p_urn_number
					  AND CONTROL_TYPE NOT IN ('REMITTANCESTUB', 'CONTROL')
			 ORDER BY seq_no ASC;

		  OPEN x_summary_data FOR
			   SELECT urn_number,
					  FIELD1 orderTYpe,
					  FIELD2 productType,
					  COUNT (TO_NUMBER (FIELD3)) baseCount,
					  SUM (TO_NUMBER (FIELD3)) baseCharge,
					  COUNT (TO_NUMBER (FIELD4)) serviceCount,
					  SUM (TO_NUMBER (FIELD4)) serviceCharge,
					  COUNT (TO_NUMBER (FIELD5)) attachCount,
					  SUM (TO_NUMBER (FIELD5)) attachCharge,
					  COUNT (TO_NUMBER (FIELD6)) usageCount,
					  SUM (TO_NUMBER (FIELD6)) usageCharge
				 FROM CANON_E479_INV_DTL_TBL ceidt
				WHERE     CONTROL_TYPE = 'DETAIL'
					  AND field1 NOT IN ('SALE', 'MAINTENANCE', 'RENTAL')
					  AND urn_number = p_urn_number
					  AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   )
			 GROUP BY FIELD1, FIELD2, urn_number
			 UNION ALL
			   SELECT urn_number,
					  FIELD1 orderTYpe,
					  FIELD2 productType,
					  CANON_E479_INV_PKG.get_unique_count_maint_rental (FIELD1,
													 FIELD2,
													 'BASE',
													 urn_number)
						 baseCount,
					  --count(to_number(FIELD3)) baseCount,
					  NVL (SUM (TO_NUMBER (FIELD3)), 0) baseCharge,
					  CANON_E479_INV_PKG.get_unique_count_maint_rental (FIELD1,
													 FIELD2,
													 'SERVICE',
													 urn_number)
						 serviceCount,
					  -- count(to_number(FIELD4)) serviceCount,
					  SUM (TO_NUMBER (FIELD4)) serviceCharge,
					  CANON_E479_INV_PKG.get_unique_count_maint_rental (FIELD1,
													 FIELD2,
													 'ATTACH',
													 urn_number)
						 attachCount,
					  --count(to_number(FIELD5)) attachCount,
					  SUM (TO_NUMBER (FIELD5)) attachCharge,
					  CANON_E479_INV_PKG.get_unique_count_maint_rental (FIELD1,
													 FIELD2,
													 'USAGE',
													 urn_number)
						 usageCount,
					  --count(to_number(FIELD6)) usageCount, sum(to_number(FIELD6))  usageCharge,
					  SUM (TO_NUMBER (FIELD6)) usageCharge
				 FROM CANON_E479_INV_DTL_TBL ceidt
				WHERE     CONTROL_TYPE = 'DETAIL'
					  AND field1 IN ('MAINTENANCE', 'RENTAL')
					  AND urn_number = p_urn_number
					   AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   )
			 GROUP BY FIELD1, FIELD2, urn_number
			 ORDER BY 2;

		  OPEN x_tax_data FOR
			 SELECT SUM (TO_NUMBER (FIELD7)) stateTax,
					SUM (TO_NUMBER (FIELD8)) countyTax,
					SUM (TO_NUMBER (FIELD9)) cityTax
			   FROM CANON_E479_INV_DTL_TBL ceidt
			  WHERE CONTROL_TYPE = 'DETAIL' 
			    AND urn_number = p_urn_number
				AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   )	
				;
       END IF;			  
		dbms_output.put_line('+++ End get_urn_info +++');	 	  
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_urn_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_control_data FOR
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;

         OPEN x_remit_data FOR
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;

         OPEN x_line_data FOR
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;

         OPEN x_summary_data FOR
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;

         OPEN x_tax_data FOR
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;
   END get_urn_info;

   PROCEDURE get_sale_info (p_urn_number   IN     VARCHAR2,
                            x_sale_data       OUT RESULT_CURSOR)
   AS
      l_procedure_name   VARCHAR2 (100) := 'get_sale_info';
   BEGIN
      OPEN x_sale_data FOR
           SELECT FIELD1 orderTYpe,
                  FIELD2 productType,
                  Field10 Model_NAME,
                  COUNT (TO_NUMBER (FIELD3)) Total_Count,
                  SUM (TO_NUMBER (FIELD3)) total_Charge
             FROM CANON_E479_INV_DTL_TBL ceidt
            WHERE     CONTROL_TYPE = 'DETAIL'
                  AND field1 = 'SALE'
				  AND tab_name = 'Sale'
                  AND urn_number = p_urn_number
				  AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   )
         GROUP BY FIELD1, FIELD2, Field10
         ORDER BY 2;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_urn_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_sale_data FOR
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;
   END get_sale_info;

   PROCEDURE get_inv_details (p_inv_number   IN     NUMBER,
                              x_data            OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_inv_details';
   BEGIN
      OPEN x_data FOR
           SELECT DISTINCT urn_number
             FROM CANON_E479_INV_DTL_TBL
            WHERE inv_id = p_inv_number AND control_type = 'DETAIL'
         ORDER BY TO_NUMBER (urn_number);
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_inv_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;
   END get_inv_details;

   PROCEDURE create_invoice_record (p_invoice_id             IN     NUMBER,
                                    p_urn_number             IN     VARCHAR2,
                                    p_invoice_type           IN     VARCHAR2,
                                    p_invoice_region         IN     VARCHAR2,
                                    p_biller_name            IN     VARCHAR2,
                                    p_biller_email           IN     VARCHAR2,
                                    p_customer_email         IN     VARCHAR2,
                                    p_other_email            IN     VARCHAR2,
                                    p_review_required        IN     VARCHAR2,
                                    p_parent_customer_name   IN     VARCHAR2,
                                    p_customer_group         IN     VARCHAR2,
                                    p_customer_name          IN     VARCHAR2,
                                    p_bill_location          IN     VARCHAR2,
                                    p_bill_number            IN     VARCHAR2,
                                    p_bill_date              IN     DATE,
                                    p_invoice_break          IN     VARCHAR2,
                                    p_bill_period            IN     VARCHAR2,
                                    p_amount_due             IN     NUMBER,
                                    p_invoice_path           IN     VARCHAR2,
                                    p_pending_action         IN     VARCHAR2,
                                    p_user_id                IN     NUMBER,
                                    p_file_name              IN     VARCHAR2,
                                    p_remarks                IN     VARCHAR2,
                                    x_status                    OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (200) := 'create_invoice_record';
      l_server_url       VARCHAR2 (2000) := '';
	  lv_created_from    VARCHAR2(500) := 'S21 Invoice Excel Creation';
   BEGIN
      x_status := 1;
      update_urn (p_urn_number); 
      get_print_server_url (l_server_url);
	  
	  IF p_bill_number like 'MB%' THEN 
	     lv_created_from := 'S21 Manual Billing Upload';
	  END IF;

      INSERT INTO CANON_E479_INV_SRCH_TBL (INVOICE_ID,
                                           URN_NUMBER,
                                           INVOICE_TYPE,
                                           INV_REGION,
                                           BILLER_NAME,
                                           BILLER_EMAIL,
                                           CUSTOMER_EMAIL,
                                           OTHER_EMAIL,
                                           REVIEW_REQUIRED,
                                           PARENT_CUSTOMER_NAME,
                                           DS_ACCT_GRP_NM,
                                           CUSTOMER_NAME,
                                           BILL_LOCATION,
                                           BILL_NUMBER,
                                           BILL_DATE,
                                           INVOICE_BREAK,
                                           BILL_PERIOD,
                                           AMOUNT_DUE,
                                           INVOICE_PATH,
                                           PENDING_ACTION,
                                           CREATION_DATE,
                                           CREATED_BY,
                                           LAST_UPDATED_BY,
                                           LAST_UPDATE_DATE,
                                           server_url,
                                           invoice_file_name,
                                           remarks,
										   created_from)
           VALUES (p_invoice_id,
                   p_urn_number,
                   p_invoice_type,
                   p_invoice_region,
                   p_biller_name,
                   p_biller_email,
                   p_customer_email,
                   p_other_email,
                   p_review_required,
                   p_parent_customer_name,
                   p_customer_group,
                   p_customer_name,
                   p_bill_location,
                   p_bill_number,
                   p_bill_date,
                   p_invoice_break,
                   p_bill_period,
                   p_amount_due,
                   p_invoice_path,
                   p_pending_action,
                   SYSDATE,
                   p_user_id,
                   p_user_id,
                   SYSDATE,
                   NULL, -- This is no longer required.l
                   p_file_name,
                   p_remarks,
				   lv_created_from);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_urn_number,
                                                  p_invoice_id,
                                                  p_bill_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END create_invoice_record;

   PROCEDURE get_invoices_to_create (x_data OUT RESULT_CURSOR)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_invoices_to_create';
   BEGIN
      OPEN x_data FOR
         SELECT DISTINCT
                inv_id,
                (SELECT urn_number
                   FROM CANON_E479_INV_DTL_TBL
                  WHERE     inv_id = a.inv_id
                        AND NVL (Field21, 'NegativeRead=N') =
                               'NegativeRead=Y'
                        AND control_type = 'CONTROL'
                        AND Field7 = 'ReviewRequired=Y'
                        AND ROWNUM = 1)
                   Negative_READ_URN,
                (SELECT urn_number
                   FROM CANON_E479_INV_DTL_TBL
                  WHERE     inv_id = a.inv_id
                        AND NVL (Field22, 'HighDollar=N') = 'HighDollar=Y'
                        AND control_type = 'CONTROL'
                        AND Field7 = 'ReviewRequired=Y'
                        AND ROWNUM = 1)
                   High_DOLLAR_URN,
                (SELECT urn_number
                   FROM CANON_E479_INV_DTL_TBL
                  WHERE     inv_id = a.inv_id
                        AND control_type = 'CONTROL'
                        AND ROWNUM = 1)
                   RANDOM_URN
           FROM CANON_E479_INV_DTL_TBL a
          WHERE     control_type = 'DETAIL'
                --and urn_number in ('5868','5913', '5914', '5936', '6035', '6125','6126')
                --and urn_number in ('6125','6126')
                AND NOT EXISTS
                       (SELECT 1
                          FROM CANON_E479_INV_SRCH_TBL b
                         WHERE a.inv_id = b.INVOICE_ID);
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, 1
              FROM DUAL
             WHERE 1 = -1;
   END get_invoices_to_create;

   PROCEDURE get_company_info (x_company_name   OUT VARCHAR2,
                               x_address1       OUT VARCHAR2,
                               x_address2       OUT VARCHAR2,
                               x_city           OUT VARCHAR2,
                               x_state          OUT VARCHAR2,
                               x_postal_code    OUT VARCHAR2,
                               x_phone1         OUT VARCHAR2,
                               x_url            OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_company_info';
   BEGIN
      /* S21 Replacement

      DBMS_APPLICATION_INFO.set_client_info('81');

      SELECT NVL (attribute10, NAME) org_name,
             address_line_1 ,
             address_line_2 ,
             town_or_city ,
             region_2 ,
             postal_code,
             telephone_number_1,
             attribute9
         INTO x_company_name,
             x_address1,
             x_address2,
             x_city,
             x_state,
             x_postal_code,
             x_phone1,
             x_url
        FROM hr_organization_units_v
       WHERE TRIM (TO_CHAR (organization_id)) =NVL(TO_NUMBER(DECODE (SUBSTRB (USERENV ('CLIENT_INFO'),1,1
                                            ),
                                    ' ', NULL,
                                    SUBSTRB (USERENV ('CLIENT_INFO'),
                                             1,
                                             10
                                            )
                                   )
                           ),
                    -99
                   );
      */
      SELECT GLBL_CMPY_NM,
             'Address1',
             'Address12',
             'City',
             'State',
             'Zip',
             'xxx-xxx-xxxx',
             'www.csa.canon.com'
        INTO x_company_name,
             x_address1,
             x_address2,
             x_city,
             x_state,
             x_postal_code,
             x_phone1,
             x_url
        FROM GLBL_CMPY
       WHERE glbl_cmpy_cd = 'ADB';
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_company_info;



   PROCEDURE find_any_pending_invoices (p_invoice_id   IN     NUMBER,
                                        x_flag            OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (200) := 'find_any_pending_invoices';
      l_count            NUMBER;
   BEGIN
      x_flag := 'Y';

      SELECT DECODE (COUNT (*), 0, 'N', 'Y')
        INTO x_flag
        FROM CANON_E479_INV_SRCH_TBL
       WHERE     invoice_id = p_invoice_id
             AND NVL (REVIEW_REQUIRED, 'N') = 'Y'
             AND NVL (PENDING_ACTION, 'Y') = 'Y';
   EXCEPTION
      WHEN OTHERS
      THEN
         x_flag := 'Y';
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END find_any_pending_invoices;

   PROCEDURE get_inv_details_srch_tbl (p_inv_number   IN     NUMBER,
                                       x_data            OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_inv_details_srch_tbl';
   BEGIN
      OPEN x_data FOR
           SELECT *
             FROM CANON_E479_INV_SRCH_TBL
            WHERE invoice_id = p_inv_number
         ORDER BY TO_NUMBER (urn_number);
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_inv_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;
   END get_inv_details_srch_tbl;


   -- S Approved
   -- N Not Required
   -- R Rejected
   -- Y Pending
   PROCEDURE search_custom_invoices (
      p_user_id                IN     NUMBER,
      p_user_role              IN     VARCHAR2,
      p_bill_number            IN     VARCHAR2,
      p_pending_action         IN     VARCHAR2,
      p_period                 IN     VARCHAR2,
      p_urn_number             IN     VARCHAR2,
      p_biller_name            IN     VARCHAR2,
      p_parent_customer_name   IN     VARCHAR2,
      p_customer_group         IN     VARCHAR2,
      p_customer_name          IN     VARCHAR2,
      p_location               IN     VARCHAR2,
      p_bill_from_date         IN     DATE,
      p_bill_to_date           IN     DATE,
      p_start_range            IN     NUMBER,
      p_end_range              IN     NUMBER,
      p_sort_by                IN     VARCHAR2,
      p_sort_type              IN     VARCHAR2,
      x_data                      OUT result_cursor,
      x_count                     OUT NUMBER,
      x_amount_tot                OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (30) := 'search_custom_invoices';
      l_sql_common       VARCHAR2 (3999) := ' ';
      l_sql_qry          VARCHAR2 (3999) := ' ';
      l_sql_count_qry    VARCHAR2 (3999) := ' ';
      l_sort_by          VARCHAR2 (200) := ' ';
      l_sort_type        VARCHAR2 (200) := ' ';

      l_biller_flag      VARCHAR2 (20);

      l_pending_action   VARCHAR2 (20) := NVL (p_pending_action, 'Y');

      l_sql_sum_qry      VARCHAR2 (3999) := ' ';
   BEGIN
      l_biller_flag := 'N';

      IF p_user_role IN ('Approver')
      THEN
         l_biller_flag := 'Y';
      END IF;

      --is_user_biller(p_user_id, l_biller_flag);

      l_sql_qry :=
            l_sql_qry
         || '  SELECT  invSrch.* , CANON_E479_INV_PKG.is_any_pending_invoice(invSrch.INVOICE_ID) pending_inv_flag ';



      l_sql_common :=
         l_sql_common || ' FROM CANON_E479_INV_SRCH_TBL invSrch where 1 = 1';


      IF (l_biller_flag = 'N')
      THEN
         IF (p_pending_action IS NOT NULL AND p_pending_action = 'S')
         THEN
            l_sql_common :=
                  l_sql_common
               || ' and REVIEW_REQUIRED = ''Y'' and nvl(PENDING_ACTION,''Y'') = ''S''';
         ELSIF (p_pending_action IS NOT NULL AND p_pending_action = 'N')
         THEN
            l_sql_common := l_sql_common || ' and REVIEW_REQUIRED = ''N''';
         ELSE
            l_sql_common := l_sql_common || ' and (( REVIEW_REQUIRED = ''N'' AND (pending_action IS NULL OR pending_action =''RETURNED'')) ';
            l_sql_common :=
                  l_sql_common
               || '  or  (REVIEW_REQUIRED = ''Y'' and  nvl(PENDING_ACTION,''Y'') in ( ''S'',''RETURNED'',''INVOICE REJECTED'')))';
         END IF;
      END IF;



      IF (p_bill_number IS NOT NULL AND LENGTH (TRIM (p_bill_number)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and BILL_NUMBER = '''
            || TRIM (p_bill_number)
            || '''';
      END IF;


      IF (    l_biller_flag = 'Y'
          AND p_pending_action IS NOT NULL
          AND LENGTH (TRIM (p_pending_action)) > 0)
      THEN
	     IF (p_pending_action IS NOT NULL AND p_pending_action ='RETURNED') THEN 
		     l_sql_common :=
                  l_sql_common 
				   || ' AND nvl(PENDING_ACTION,''Y'') = '''
				   || p_pending_action
                   || '''';
         ELSIF (p_pending_action = 'N' OR p_pending_action = 'S')
         THEN
            l_sql_common := l_sql_common || ' and ((REVIEW_REQUIRED = ''N'' AND pending_action IS NULL) ';
            l_sql_common :=
                  l_sql_common
               || '  or  (REVIEW_REQUIRED = ''Y'' and  nvl(PENDING_ACTION,''Y'') = ''S''))';
         ELSIF (p_pending_action = 'R') THEN
            l_sql_common :=
                  l_sql_common
               || '  and REVIEW_REQUIRED = ''Y'' and  nvl(PENDING_ACTION,''Y'') in  (''R'',''INVOICE REJECTED'')';
		 ELSIF (p_pending_action = 'Y') THEN
            l_sql_common :=
                  l_sql_common
               || '  and REVIEW_REQUIRED = ''Y'' and  nvl(PENDING_ACTION,''Y'') =  ''Y''';
		 ELSE
            l_sql_common :=
                  l_sql_common
               || '  and REVIEW_REQUIRED = ''Y'' and  nvl(PENDING_ACTION,''Y'') IN ( ''INVOICE REJECTED'','''
               || p_pending_action
               || ''')';	    
         END IF;
      END IF;

      IF (p_location IS NOT NULL AND LENGTH (TRIM (p_location)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and BILL_LOCATION = '''
            || TRIM (p_location)
            || '''';
      END IF;

      IF (p_urn_number IS NOT NULL AND LENGTH (TRIM (p_urn_number)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and URN_NUMBER like '''
            || TRIM (p_urn_number)
             || '%''';
      END IF;

      IF (    p_parent_customer_name IS NOT NULL
          AND LENGTH (TRIM (p_parent_customer_name)) > 0)
      THEN
         l_sql_common :=
               l_sql_common	
            || '  and parent_customer_name like '''
            || TRIM (p_parent_customer_name)
            || '%''';
      END IF;

      IF (    p_customer_group IS NOT NULL
          AND LENGTH (TRIM (p_customer_group)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and ds_acct_grp_nm like '''
            || TRIM (p_customer_group)
            || '%''';
      END IF;

      IF (p_period IS NOT NULL AND LENGTH (TRIM (p_period)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and BILL_PERIOD = '''
            || TRIM (p_period)
            || '''';
      END IF;

      IF (p_bill_from_date IS NULL AND p_bill_to_date IS NOT NULL)
      THEN
         l_sql_common :=
            l_sql_common || ' and BILL_DATE <= ''' || p_bill_to_date || '''';
      END IF;

      IF (p_bill_from_date IS NOT NULL AND p_bill_to_date IS NULL)
      THEN
         l_sql_common :=
               l_sql_common
            || ' and BILL_DATE >= '''
            || p_bill_from_date
            || '''';
      END IF;

      IF (p_bill_from_date IS NOT NULL AND p_bill_to_date IS NOT NULL)
      THEN
         l_sql_common :=
               l_sql_common
            || ' and BILL_DATE >= '''
            || p_bill_from_date
            || '''';
         l_sql_common :=
            l_sql_common || ' and BILL_DATE <= ''' || p_bill_to_date || '''';
      END IF;

      IF (p_biller_name IS NOT NULL AND LENGTH (TRIM (p_biller_name)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and BILLER_NAME like '''
            || TRIM (p_biller_name)
            || '%''';
      END IF;

      IF (p_customer_name IS NOT NULL AND LENGTH (TRIM (p_customer_name)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and CUSTOMER_NAME like '''
            || TRIM (p_customer_name)
            || '%''';
      END IF;


      l_sql_sum_qry :=
            l_sql_sum_qry
         || 'select nvl(sum(nvl(amount_due,0)),0) from ( '
         || l_sql_qry
         || l_sql_common
         || ' )';



      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         l_sort_by := p_sort_by;

         IF (p_sort_by = 'CUSTOMER_GROUP')
         THEN
            l_sort_by := ' DS_ACCT_GRP_NM';
         END IF;
      ELSE
         l_sort_by := 'URN_NUMBER ';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;



      l_sql_count_qry := ' select count(*) ' || l_sql_common;

      l_sql_qry :=
            'SELECT * '
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || l_sql_common
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;
		 
	  dbms_output.put_line('Query to be executed: '|| l_sql_qry);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     'COUNT',
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     'SQL',
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_sum_qry,
                                                     'SUM',
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      EXECUTE IMMEDIATE l_sql_sum_qry INTO x_amount_tot;

      OPEN x_data FOR l_sql_qry;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, '1'
              FROM DUAL
             WHERE 1 = -1;

         x_count := 0;

         x_amount_tot := 0;
   END search_custom_invoices;

   FUNCTION is_any_pending_invoice (p_invoice_id IN NUMBER)
      RETURN VARCHAR2
   AS
      l_flag             VARCHAR2 (10) := 'Y';
      l_procedure_name   VARCHAR2 (25) := 'is_any_pending_invoice';
   BEGIN
      SELECT DECODE (COUNT (*), 0, 'N', 'Y')
        INTO l_flag
        FROM CANON_E479_INV_SRCH_TBL
       WHERE     invoice_id = p_invoice_id
             AND NVL (REVIEW_REQUIRED, 'N') = 'Y'
             AND NVL (PENDING_ACTION, 'Y') = 'Y';

      RETURN l_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_flag := 'Y';
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
         RETURN l_flag;
   END is_any_pending_invoice;

   PROCEDURE approve_reject_invoices (p_invoice_id   IN     NUMBER,
                                      p_status       IN     VARCHAR2,
                                      p_user_id      IN     NUMBER,
                                      p_comments     IN     VARCHAR2,
                                      x_status          OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (25) := 'approve_reject_invoices';
   BEGIN
      x_status := 1;

      UPDATE CANON_E479_INV_SRCH_TBL
         SET PENDING_ACTION = p_status,
             LAST_UPDATED_BY = p_user_id,
             COMMENTS = p_comments,
             LAST_UPDATE_Date = SYSDATE
       WHERE     invoice_id = p_invoice_id
             AND REVIEW_REQUIRED = 'Y'
             AND NVL (PENDING_ACTION, 'Y') = 'Y';
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END approve_reject_invoices;

   PROCEDURE get_inv_details_for_approval (
      p_inv_number   IN     NUMBER,
      x_data            OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (35) := 'get_inv_details_for_approval';
   BEGIN
      OPEN x_data FOR
         SELECT a.*, NVL (b.field23, 'EmailTextData=N') emailText
           FROM CANON_E479_INV_SRCH_TBL a, canon_e479_inv_dtl_tbl b
          WHERE     a.invoice_id = p_inv_number
                AND a.invoice_id = b.inv_id
                AND A.URN_NUMBER = b.urn_number
                AND b.control_type = 'CONTROL';
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, '1'
              FROM DUAL
             WHERE 1 = -1;
   END get_inv_details_for_approval;

   PROCEDURE get_initial_path (
       p_source  IN     VARCHAR2,
       x_data       OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (100) := 'get_server_initial_path';
      l_db_name          VARCHAR2 (100) := NULL;
	  lv_profile_name    VARCHAR2 (100) := NULL;
	  lv_profile_by_num   NUMBER;
	  lv_profile_by_date  DATE;
	  lv_validation_status VARCHAR2(1);
	  lv_error_msg       VARCHAR2(4000);
	  
   BEGIN
      
	  IF p_source = 'ONLINE' THEN 
	     lv_profile_name := 'CANON_E479_ONLINE_INITIAL_PATH';
	  ELSIF p_source = 'SERVER' THEN 
	     lv_profile_name := 'CANON_E479_SERVER_INITIAL_PATH';
	  END IF;
	  
	  CANON_E001_CODETABLE_UI_PKG.retrieve_profile_values(
      p_user_name             =>  NULL,
	  p_role_name             => NULL,
	  p_profile_name          => lv_profile_name,
	  p_profile_value         => x_data,
	  p_validation_status     => lv_validation_status,
	  p_error_msg             => lv_error_msg);
	  
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
         x_data := NULL;
   END get_initial_path;

   PROCEDURE get_print_server_url (x_data OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (100) := 'get_print_server_url';
      l_db_name          VARCHAR2 (100) := NULL;
   BEGIN
      x_data := ' ';

      --> S21 Replacement
      SELECT SYS_CONTEXT ('userenv', 'db_name') INTO l_db_name FROM DUAL;

      IF (UPPER (l_db_name) = 'CANPRD')
      THEN
         x_data := 'http://invoiceprint.cusa.canon.com/';
      ELSIF (INSTR (l_db_name, 'DEV') > 0)
      THEN
         x_data :=
               'http://invoiceprint.dev.cusa.canon.com/'
            || LOWER (l_db_name)
            || '/';
      ELSIF (UPPER (l_db_name) <> 'CANPRD' AND INSTR (l_db_name, 'DEV') <= 0)
      THEN
         x_data :=
               'http://invoiceprint.tst.cusa.canon.com/'
            || LOWER (l_db_name)
            || '/';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
         x_data := NULL;
   END get_print_server_url;

   FUNCTION get_unique_count_maint_rental (p_order_type     IN VARCHAR2,
                                           p_product_type   IN VARCHAR2,
                                           p_charge_type    IN VARCHAR2,
                                           p_urn_number     IN VARCHAR2)
      RETURN NUMBER
   AS
      l_procedure_name   VARCHAR2 (100) := 'get_unique_count_maint_rental';
      x_count            NUMBER;
   BEGIN
      x_count := 0;

      IF (p_charge_type = 'BASE')
      THEN                                                       --Sercie fld3
         SELECT COUNT (DISTINCT FIELD11)
           INTO x_count
           FROM CANON_E479_INV_DTL_TBL ceidt
          WHERE     FIELD1 = p_order_type
                AND FIELD2 = p_product_type
                AND urn_number = p_urn_number
                AND FIELD11 NOT LIKE '%<->%'
                --and field3 is not null;
                AND TO_NUMBER (NVL (field3, '0')) <> 0
				AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   )
				;
      ELSIF (p_charge_type = 'SERVICE')
      THEN                                                       --Sercie fld4
         SELECT COUNT (DISTINCT FIELD11)
           INTO x_count
           FROM CANON_E479_INV_DTL_TBL ceidt
          WHERE     FIELD1 = p_order_type
                AND FIELD2 = p_product_type
                AND FIELD11 NOT LIKE '%<->%'
                AND urn_number = p_urn_number
                --and field4 is not null;
                AND TO_NUMBER (NVL (field4, '0')) <> 0
				 AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   );
      ELSIF (p_charge_type = 'ATTACH')
      THEN
         SELECT COUNT (*)
           INTO x_count
           FROM                                                  --Attach fld5
               CANON_E479_INV_DTL_TBL ceidt
          WHERE     FIELD1 = p_order_type
                AND FIELD2 = p_product_type
               -- AND FIELD11 NOT LIKE '%<->%'  -- Non-serialized records billing is being included on the Summary tab
                AND urn_number = p_urn_number
                --and field5 is not null;
                AND TO_NUMBER (NVL (field5, '0')) <> 0
				 AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   );
      ELSIF (p_charge_type = 'USAGE')
      THEN
         SELECT COUNT (DISTINCT FIELD11)
           INTO x_count
           FROM                                                   --USage fld6
               CANON_E479_INV_DTL_TBL ceidt
          WHERE     FIELD1 = p_order_type
                AND FIELD2 = p_product_type
                AND FIELD11 NOT LIKE '%<->%'
                AND urn_number = p_urn_number
                --and field6 is not null;
                AND TO_NUMBER (NVL (field6, '0')) <> 0
				AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   );
      END IF;

      RETURN x_count;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
         RETURN x_count;
   END get_unique_count_maint_rental;


   PROCEDURE process_dups_fleet_serials (p_urn_number IN VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (30) := 'process_dups_fleet_serials';
      l_base_count       NUMBER := 0;
      l_usage_count      NUMBER := 0;
   BEGIN
      FOR rec
         IN (SELECT DISTINCT FIELD11
               FROM CANON_E479_INV_DTL_TBL
              WHERE     URN_NUMBER = p_urn_number
                    AND FIELD11 LIKE 'FLT%'
                    AND (    FIELD11 NOT LIKE 'FLT%<BASE>'
                         AND FIELD11 NOT LIKE 'FLT%<USAGE>'))
      LOOP
         SELECT COUNT (*)
           INTO l_usage_count
           FROM CANON_E479_INV_DTL_TBL ceidt
          WHERE URN_NUMBER = p_urn_number AND FIELD11 = rec.FIELD11 --and field6 is not null;  --usage
                AND TO_NUMBER (NVL (field6, '0')) <> 0
				AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   )
				;


         SELECT COUNT (*)
           INTO l_base_count
           FROM CANON_E479_INV_DTL_TBL ceidt
          WHERE     URN_NUMBER = p_urn_number
                AND FIELD11 = rec.FIELD11
                --and (field3 is not null or field4 is not null); --base
                AND (   (TO_NUMBER (NVL (field3, '0')) <> 0)
                     OR (TO_NUMBER (NVL (field4, '0')) <> 0))
				AND NOT EXISTS (
					     SELECT 'Y'
						    FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
						   WHERE 1=1
  						      AND cetevlv.enabled_flag = 'Y'
							  AND cetevlv.view_name = ceidt.view_name
					   );

         IF (l_usage_count > 0 AND l_base_count > 0)
         THEN                                                   -- delete base
            DELETE FROM CANON_E479_INV_DTL_TBL
                  WHERE     FIELD11 LIKE rec.FIELD11 || '%' || '<BASE>'
                        AND URN_NUMBER = p_urn_number;
         END IF;

         IF (l_usage_count > 0)
         THEN
            UPDATE CANON_E479_INV_DTL_TBL
               SET FIELD11 = REPLACE (FIELD11, '<USAGE>', '')
             WHERE     FIELD11 LIKE rec.FIELD11 || '%' || '<USAGE>'
                   AND URN_NUMBER = p_urn_number;
         END IF;

         IF (l_base_count > 0)
         THEN
            UPDATE CANON_E479_INV_DTL_TBL
               SET FIELD11 = REPLACE (FIELD11, '<BASE>', '')
             WHERE     FIELD11 LIKE rec.FIELD11 || '%' || '<BASE>'
                   AND URN_NUMBER = p_urn_number;
         END IF;

         COMMIT;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END process_dups_fleet_serials;

   PROCEDURE get_urn_term_comments (p_urn_number   IN     VARCHAR2,
                                    x_comments        OUT VARCHAR2,
                                    x_term            OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_urn_term_comments';
      l_bill_number      VARCHAR2 (200);
      l_account_number   VARCHAR2 (100);
      l_location         VARCHAR2 (100);
      l_account_id       NUMBER;
      l_SITE_USE_ID      NUMBER;
      ln_terms           NUMBER;
   BEGIN
      BEGIN
         SELECT bill_number
           INTO l_bill_number
           FROM CANON_E479_EXCEL_CONTROL b
          WHERE b.urn = p_urn_number;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_bill_number := '-1';
      END;

      IF (l_bill_number <> '-1')
      THEN
         BEGIN
            SELECT customer_number, bill_location
              INTO l_account_number, l_location
              FROM CANON_E479_INVOICE_MASTER
             WHERE bill_number = l_bill_number AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                        l_procedure_name,
                                                        'SQL',
                                                        p_urn_number,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        NULL,
                                                        SQLERRM);
         END;
      --> S21 Replacement
      --> for standard terms and messages
      /*

      if(l_account_number is not null) then
          begin
              select cust_account_id into l_account_id
              from hz_cust_accounts where account_number = l_account_number;
          exception
          when others then
             CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                           l_procedure_name,
                           'SQL',
                            p_urn_number,
                            NULL,
                            NULL,
                            NULL,
                            NULL,
                            NULL,
                            SQLERRM
                      );
          end;
      end if;

      if(l_location is not null) then
          begin
              select SITE_USE_ID
               into l_SITE_USE_ID
              from bill_to_cust
              where bill_to_cust_cd = l_location;
          exception
          when others then
             CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                           l_procedure_name,
                           'SQL',
                            p_urn_number,
                            NULL,
                            NULL,
                            NULL,
                            NULL,
                            NULL,
                            SQLERRM
                      );

          end ;
      end if;

      if(l_account_id is not null and l_SITE_USE_ID is not null) then
           begin
              SELECT /*+ ORDERED
                    standard_terms
              INTO  ln_terms
              FROM hz_customer_profiles sp
              WHERE 1 = 1
               AND sp.cust_account_id = l_account_id
               AND sp.site_use_id = l_SITE_USE_ID
               AND sp.site_use_id IS NOT NULL;

           exception
           when others then
             CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                           l_procedure_name,
                           'SQL',
                            p_urn_number,
                            NULL,
                            NULL,
                            NULL,
                            NULL,
                            NULL,
                            SQLERRM
                      );
           end;

          if( ln_terms is not null) then
              begin
                  SELECT description
                  INTO x_term
                  FROM ra_terms
                  WHERE term_id = ln_terms;
              exception
              when others then
                 CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                               l_procedure_name,
                               'SQL',
                                p_urn_number,
                                NULL,
                                NULL,
                                NULL,
                                NULL,
                                NULL,
                                SQLERRM
                          );
              end;

          end if;

          canon_e512_consinv_pkg.get_bill_message(l_account_id,l_SITE_USE_ID,x_comments);

      end if;
      */
	  
	  /*  Get the bill messages */
	  CANON_E479_INV_PKG.get_bill_message(
		p_ds_acct_num => l_account_number,		
		p_loc_num     => l_location,
		x_message     => x_comments);
		  /* Get the terms from remittance table */
		  begin
				  SELECT TERMS
				  INTO x_term
				  FROM CANON_E479_EXCEL_remittance
				  WHERE bill_number = l_bill_number
					AND rownum=1;
		  exception
		  when others then
			 CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
						   l_procedure_name,
						   'SQL',
							p_urn_number,
							NULL,
							NULL,
							NULL,
							NULL,
							NULL,
							SQLERRM
					  );
		 end;
		   



      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_comments := NULL;

         x_term := NULL;

         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_urn_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_urn_term_comments;


   PROCEDURE get_urn_details_for_text (p_urn_number   IN     VARCHAR2,
                                       x_data            OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (100) := 'get_urn_details_for_text';
   BEGIN
      OPEN x_data FOR
           SELECT *
             FROM CANON_E479_INV_DTL_TBL
            WHERE     URN_NUMBER = p_urn_number
                  AND control_type IN ('DETAIL', 'HEADER')
         ORDER BY seq_no ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_urn_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, 1
              FROM DUAL
             WHERE 1 = -1;
   END get_urn_details_for_text;
   
   PROCEDURE get_bill_message (
      p_ds_acct_num   IN       NUMBER,
      p_loc_num       IN       NUMBER,
      x_message            OUT      VARCHAR2
   )
   IS
      CURSOR c_cust_site_msg
      IS
         SELECT   msg.ds_cust_msg_txt comments
             FROM ds_cust_spcl_msg msg, bill_to_cust bill_to 
            WHERE msg.ds_biz_area_cd IN 
			     (SELECT ds_biz_area_cd 
				    FROM ds_biz_area 
				   WHERE DS_BIZ_AREA_NM='INVOICING')
              AND msg.DS_PRINT_ON_INV_FLG = 'Y'
              AND msg.loc_num = bill_to.loc_num
			  AND bill_to.bill_to_cust_cd =p_loc_num
			  AND msg.ds_acct_num = p_ds_acct_num
              AND msg.CUST_EFF_LVL_CD = 20
			  AND bill_to.ezcancelflag ='0'
			  AND bill_to.glbl_cmpy_cd ='ADB'
			  AND msg.ezcancelflag ='0'
			  AND msg.glbl_cmpy_cd ='ADB'
         ORDER BY DS_CUST_SPCL_MSG_PK;

      CURSOR c_cust_loc_msg
      IS
        SELECT   ds_cust_msg_txt comments
             FROM ds_cust_spcl_msg msg
            WHERE ds_biz_area_cd IN 
			     (SELECT ds_biz_area_cd 
				    FROM ds_biz_area 
				   WHERE DS_BIZ_AREA_NM='INVOICING')
              AND DS_PRINT_ON_INV_FLG = 'Y'
			  AND ds_acct_num = p_ds_acct_num
              AND CUST_EFF_LVL_CD = 10
			  AND msg.ezcancelflag ='0'
			  AND msg.glbl_cmpy_cd ='ADB'
         ORDER BY DS_CUST_SPCL_MSG_PK;

      CURSOR c_cust_msg
      IS
         SELECT   ds_cust_msg_txt comments
             FROM ds_cust_spcl_msg msg
            WHERE ds_biz_area_cd IN 
			     (SELECT ds_biz_area_cd 
				    FROM ds_biz_area 
				   WHERE DS_BIZ_AREA_NM='INVOICING')
              AND DS_PRINT_ON_INV_FLG = 'Y'
			  AND ds_acct_num = p_ds_acct_num
              AND CUST_EFF_LVL_CD = 0
			  AND msg.ezcancelflag ='0'
			  AND msg.glbl_cmpy_cd ='ADB'
         ORDER BY DS_CUST_SPCL_MSG_PK;


      l_message      VARCHAR2 (32767) DEFAULT NULL;
      l_default_message   VARCHAR2 (32767) DEFAULT NULL;
	  l_procedure_name    VARCHAR2(100) := 'get_bill_message';
	  lr_result_cursor      RESULT_CURSOR;
	  def_msg_cur           RESULT_CURSOR;
	  ln_result_count       NUMBER;
	  lv_validation_Status  VARCHAR2(1);
	  lv_error_msg          VARCHAR2(4000);
	  lv_msg_name           VARCHAR2(2000);
	  lv_msg_description    VARCHAR2(2000);
   BEGIN
      x_message := NULL;

      FOR i IN c_cust_site_msg
      LOOP
         IF l_message IS NULL
         THEN
            l_message := i.comments;
         ELSE
            l_message := l_message || CHR (10) || i.comments;
         END IF;
      END LOOP;
	  
	  dbms_output.put_line('l_message after bill-to-loc: '||l_message);	 

      IF l_message IS NULL
      THEN
         FOR i IN c_cust_loc_msg
         LOOP
            IF l_message IS NULL
            THEN
               l_message := i.comments;
            ELSE
               l_message := l_message || CHR (10) || i.comments;
            END IF;
         END LOOP;
      END IF;
	  
	  dbms_output.put_line('l_message after bill-to-loc: '||l_message);	 

      IF l_message IS NULL
      THEN
         FOR i IN c_cust_msg
         LOOP
            IF l_message IS NULL
            THEN
               l_message := i.comments;
            ELSE
               l_message := l_message || CHR (10) || i.comments;
            END IF;
         END LOOP;
      END IF;
	  
	  dbms_output.put_line('l_message after bill-to-loc: '||l_message);	 

	  /* S21 Replacement. Derive from code table for default messages  */
      FOR i IN (
	       SELECT message_token_value comments
             FROM CANON_E479_EXCEL_MESSAGES_V
            WHERE message_token_name = 'RS09'
		ORDER BY SORT_ORDER)
      LOOP
         IF l_default_message IS NULL
         THEN
            l_default_message := i.comments;
         ELSE
            l_default_message := l_default_message || CHR (10) || i.comments;
         END IF;
      END LOOP;
	  
	  dbms_output.put_line('l_default_message from code table: '||l_default_message);	 
	  
	  /*CANON_COMMON_UTIL_PKG.get_code_value(
	            p_cd_name             => 'CANON_E479_EXCEL_MESSAGES',
				p_result_cursor       => lr_result_cursor,
				p_result_count        => ln_result_count,
				p_validation_status   => lv_validation_Status,
				p_error_msg           => lv_error_msg);
	  
	  IF ln_result_count > 0 THEN 
		   LOOP
			 FETCH  lr_result_cursor INTO lv_msg_name,lv_msg_description;
			   EXIT WHEN lr_result_cursor%NOTFOUND;
			 IF l_default_message IS NULL
			 THEN
				l_default_message := lv_msg_description;
			 ELSE
				l_default_message := l_default_message || CHR (10) || lv_msg_description;
			 END IF;
		   END LOOP;
		  CLOSE lr_result_cursor;
	  END IF;
	  */

      IF l_message IS NOT NULL AND l_default_message IS NOT NULL
      THEN
         x_message := LTRIM (l_message) || CHR (10) || l_default_message;
      ELSIF l_message IS NOT NULL AND l_default_message IS NULL
      THEN
         x_message := LTRIM (l_message);
      ELSIF l_message IS NULL AND l_default_message IS NOT NULL
      THEN
         x_message := LTRIM (l_default_message);
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_message := NULL;
		 dbms_output.put_line('Error while getting bill-to message : '||SQLERRM);	 
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_ds_acct_num,
                                                  p_loc_num,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_bill_message;
   
   PROCEDURE get_bill_invoices_for_update(
       p_source       IN     VARCHAR2, -- AUTOMATIC/MANUAL_INVOICE_LIST/MANUAL_INVOICE_LIST_UPDATE
       p_urn_number   IN     VARCHAR2,
	   x_bill_data        OUT result_cursor,
	   x_inv_data         OUT result_cursor,
	   p_process_status   OUT VARCHAR2,
	   p_process_message  OUT VARCHAR2)
   IS
      l_procedure_name    VARCHAR2(100) := 'get_bill_message';
   BEGIN 
   
     /* Update the invoices in staging table to mark as processed */
	 
	 IF p_source ='AUTOMATIC' THEN 
	 
		 UPDATE CANON_E479_CUST_BILL_STG
			SET SPL_BILL_PROCESS_FLAG ='Y', 
			SPL_BILL_PROCESS_DATE = SYSDATE
		  WHERE invoice_number IN 
		  (  SELECT ceed.invoice_number
			 FROM canon_e479_excel_detail ceed, 
				   canon_e479_excel_control ceec
			WHERE ceed.ref_id = ceec.ref_id
			  AND ceed.sequence_id = ceec.sequence_id
			  AND ceed.rectype = 'DETAIL'
			  AND ceec.urn = p_urn_number
		  );
		  
		 OPEN x_bill_data FOR 
		 SELECT DISTINCT 
					ceed.bill_number,
					ceec.bill_date
		  FROM canon_e479_excel_detail ceed, canon_e479_excel_control ceec
		  WHERE ceed.ref_id = ceec.ref_id
		   AND ceed.sequence_id = ceec.sequence_id
		   AND ceed.rectype = 'DETAIL'
		   AND ceed.bill_number IS NOT NULL
		   AND ceec.urn = p_urn_number; 
	   
		 OPEN x_inv_data FOR 
		 SELECT DISTINCT ceec.urn,
					ceed.bill_number,
					ceed.invoice_number,
					ceec.bill_date
		  FROM canon_e479_excel_detail ceed, canon_e479_excel_control ceec
		  WHERE ceed.ref_id = ceec.ref_id
		   AND ceed.sequence_id = ceec.sequence_id
		   AND ceed.rectype = 'DETAIL'
		   AND ceec.urn = p_urn_number;
	 ELSIF p_source ='MANUAL_INVOICE_LIST' THEN 
	 
	     OPEN x_bill_data FOR 
		 SELECT DISTINCT 
					cecbs.bill_number,
					cecbs.consol_bill_date bill_date
		  FROM CANON_E479_CUST_BILL_STG cecbs
		  WHERE SPL_BILL_PROCESS_FLAG ='N'
		    AND bill_number IS NOT NULL
			AND SB_PROFILE_VALUE = 'Manual Special Bill';
	 
		 OPEN x_inv_data FOR 
		 SELECT DISTINCT 
		            NULL urn,
					cecbs.bill_number,
					cecbs.invoice_number,
					cecbs.consol_bill_date bill_date
		  FROM CANON_E479_CUST_BILL_STG cecbs
		  WHERE SPL_BILL_PROCESS_FLAG ='N'
			AND SB_PROFILE_VALUE = 'Manual Special Bill';
		   
     ELSIF p_source ='MANUAL_INVOICE_LIST_UPDATE' THEN 		   
	       
		 UPDATE CANON_E479_CUST_BILL_STG
			SET SPL_BILL_PROCESS_FLAG ='Y', 
			SPL_BILL_PROCESS_DATE = SYSDATE
		  WHERE SPL_BILL_PROCESS_FLAG ='N'
			AND SB_PROFILE_VALUE = 'Manual Special Bill';	   
			
		OPEN x_bill_data FOR
            SELECT 1,1
              FROM DUAL
             WHERE 1 = -1;	
		 
		 
         OPEN x_inv_data FOR
            SELECT 1, 1,1,1
              FROM DUAL
             WHERE 1 = -1;
			 
		  COMMIT;	 
			 
	 END IF;
	   
	   p_process_status  := 'S';
	   p_process_message := NULL;  
   
   EXCEPTION
      WHEN OTHERS
      THEN
       p_process_status  := 'S';
	   p_process_message := NULL;
	    OPEN x_inv_data FOR
            SELECT 1, 1,1,1
              FROM DUAL
             WHERE 1 = -1;
			 
	    OPEN x_bill_data FOR
            SELECT 1,1
              FROM DUAL
             WHERE 1 = -1;	
			 
		 dbms_output.put_line('Error while getting bill-to message : '||SQLERRM);	 
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_urn_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_bill_invoices_for_update;
   
   
   PROCEDURE update_urn (p_urn_number IN VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (100) := 'update_urn';
   BEGIN
      UPDATE canon_e479_inv_srch_tbl
         SET pending_action = 'R'
       WHERE     SUBSTR (
                    urn_number,
                    1,
                    DECODE (INSTR (urn_number, '-'),
                            0, LENGTH (urn_number),
                            INSTR (urn_number, '-') - 1)) =
                    SUBSTR (
                       p_urn_number,
                       1,
                       DECODE (INSTR (p_urn_number, '-'),
                               0, LENGTH (p_urn_number),
                               INSTR (p_urn_number, '-') - 1))
             AND (pending_action = 'S' OR pending_action IS NULL);
   EXCEPTION
      WHEN OTHERS
      THEN
         canon_e479_cust_bill_util_pkg.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_urn_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END update_urn;
   
/*
procedure is_user_biller(p_user_id in number, x_flag out varchar2)
as
  l_procedure_name varchar2(30):=   'is_user_biller';
begin
  x_flag := 'Y';

  --> S21 Replacement
  /* Need to code if required as access is validated from java
  /*
  select decode(count(*),0,'N','Y') into x_flag
  from jtf_rs_defresroles_vl a,
   jtf_rs_resource_extns b,
   jtf_rs_role_details_vl c,
   fnd_user d
  where role_resource_id = b.resource_id
  and b.resource_id = a.role_resource_id
  and b.source_number = d.user_name
  and a.ROLE_RESOURCE_TYPE = 'RS_INDIVIDUAL'
  and a.role_type_code = 'CANON_CUSTOMER_PROFILE'
  and a.role_name = c.role_name
  and c.role_code = 'CANON_E479_APPROVER'
  and a.delete_flag = 'N'
  and trunc(sysdate) between trunc(nvl(RES_RL_START_DATE,sysdate))
  and trunc(nvl(RES_RL_END_DATE,sysdate))
  and c.active_flag='Y'
  and trunc(sysdate) between trunc(nvl(START_DATE_ACTIVE,sysdate))
  and trunc(nvl(END_DATE_ACTIVE,sysdate))
  and trunc(sysdate) between trunc(nvl(d.START_DATE,sysdate))
  and trunc(nvl(d.END_DATE,sysdate))
  and d.user_id = p_user_id;


exception
when others then
    x_flag := 'N';
   CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                         l_procedure_name,
                                         'SQL',
                                          null,
                                          null,
                                          null,
                                          NULL,
                                          NULL,
                                          NULL,
                                          SQLERRM
                                    );
end is_user_biller;

/*
procedure get_body_subject(p_name in varchar2, x_data out varchar2)
   AS
     l_procedure_name  varchar2(100) := 'get_body_subject';
   BEGIN


    --> S21 Replacement if needed
    /*
          SELECT description into x_data
            FROM fnd_flex_values_vl
           WHERE  flex_value_set_id IN (
                           SELECT flex_value_set_id
                             FROM fnd_flex_value_sets
                            WHERE flex_value_set_name =
                                                       'CANON_E479_EMAIL_SUBJECT_AND_BODY')
           and   enabled_flag ='Y'
           and  sysdate between nvl(start_date_active,sysdate) and nvl(end_date_active,sysdate)
           and flex_value_meaning = p_name;

     x_data :=


   exception
   when others then
               CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                 l_procedure_name,
                                                 'SQL',
                                                  p_name,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM
                                            );
      x_data := null;

  END get_body_subject;


*/

END CANON_E479_INV_PKG;
/

SHOW ERRORS;