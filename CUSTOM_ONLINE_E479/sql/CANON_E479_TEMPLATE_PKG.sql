create or replace PACKAGE CANON_E479_TEMPLATE_PKG
AS
    /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_INV_SRCH_UTIL_PKG.sql                                       *
     * DESCRIPTION     : This package is used in defining special billing template. This is     *
	 *                   used in search page, create/update template, delete/add template views *
	 *                   and columns                                                            *
	 *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/03/2015     Initial Creation
     *                                  2/21/2017 - As per Kohei Aratani,
	 *                                  ds_acct_reln.ds_acct_num is parent,
	 *                                  ds_acct_reln.reln_ds_acct_num is child.
	 * Lakshmi Gopalsami 06/12/2017     DB2 changes *
     */

   TYPE RESULT_CURSOR IS REF CURSOR;

   l_package_name           VARCHAR2 (30) := 'CANON_E479_TEMPLATE_PKG';

   --> S21 Replacement
   --C_SEARCH_PROFILE varchar2(30) := 'PROFILE';

   C_SEARCH_PARENT          VARCHAR2 (30) := 'PARENT_CUSTOMER';

   C_SEARCH_GROUP           VARCHAR2 (30) := 'CUSTOMER_GROUP';

   C_SEARCH_PARENT_GRP      VARCHAR2 (30) := 'PARENT CUSTOMER/GROUP';

   C_SEARCH_CUSTOMER        VARCHAR2 (30) := 'CUSTOMER';

   C_SEARCH_SITE            VARCHAR2 (30) := 'BILL_TO_SITE';

   C_CSTM_INVOICE           VARCHAR2 (20) := 'CUSTOM INVOICE';

   C_CSTM_INVOICE_AUT_YES   VARCHAR2 (25) := 'SPECIAL BILL REVIEW REQD';

   C_CSTM_INVOICE_AUT_NO    VARCHAR2 (25) := 'SPECIAL BILL NO REVIEW';

   C_SITE_BILL_TO           VARCHAR2 (20) := 'BILL_TO';

   C_STS_INCOMPLETE         VARCHAR2 (100) := 'Incomplete';

   C_STS_COMPLETE           VARCHAR2 (100) := 'Complete';

   C_STS_DELETED            VARCHAR2 (100) := 'Deleted';

   PROCEDURE get_templates (p_search        IN     VARCHAR2,
                            p_search_type   IN     VARCHAR2,
                            x_data             OUT RESULT_CURSOR);

   FUNCTION append_message (p_message IN VARCHAR2, new_message IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE get_valueset_data (p_valueset_name   IN     VARCHAR2,
                                x_data               OUT result_cursor);

   PROCEDURE get_sorting_seq1 (p_view_name   IN     VARCHAR2,
                               x_data           OUT result_cursor);

   PROCEDURE get_sorting_seq2 (p_view_name   IN     VARCHAR2,
                               p_sort_seq1   IN     VARCHAR2 DEFAULT NULL,
                               x_data           OUT result_cursor);

   PROCEDURE get_sorting_seq3 (p_view_name   IN     VARCHAR2,
                               p_sort_seq1   IN     VARCHAR2 DEFAULT NULL,
                               p_sort_seq2   IN     VARCHAR2 DEFAULT NULL,
                               x_data           OUT result_cursor);

   PROCEDURE get_view_details (P_HEADER_ID   IN     NUMBER,
                               x_data           OUT result_cursor);

   PROCEDURE get_column_details (P_HEADER_ID   IN     NUMBER,
                                 P_VIEW_ID     IN     NUMBER,
                                 x_data           OUT result_cursor);

   PROCEDURE get_initial_column_list (P_VIEW_NAME   IN     VARCHAR2,
                                      x_data           OUT result_cursor);

   PROCEDURE delete_view_details (P_HEADER_ID   IN     NUMBER,
                                  P_VIEW_ID     IN     NUMBER,
                                  x_status         OUT NUMBER);

   PROCEDURE insert_view_details (P_HEADER_ID         IN     NUMBER,
                                  P_VIEW_NAME         IN     VARCHAR2,
                                  P_VIEW_ALIAS        IN     VARCHAR2,
                                  P_VIEW_SEQ          IN     NUMBER,
                                  P_SORT_PREF1        IN     VARCHAR2,
                                  P_SORT_PREF2        IN     VARCHAR2,
                                  P_SORT_PREF3        IN     VARCHAR2,
                                  P_CREATED_BY        IN     VARCHAR2,
                                  P_LAST_UPDATED_BY   IN     VARCHAR2,
                                  x_status               OUT NUMBER);

   PROCEDURE update_view_details (P_HEADER_ID         IN     NUMBER,
                                  P_VIEW_ID           IN     NUMBER,
                                  P_VIEW_NAME         IN     VARCHAR2,
                                  P_VIEW_ALIAS        IN     VARCHAR2,
                                  P_VIEW_SEQ          IN     NUMBER,
                                  P_SORT_PREF1        IN     VARCHAR2,
                                  P_SORT_PREF2        IN     VARCHAR2,
                                  P_SORT_PREF3        IN     VARCHAR2,
                                  P_LAST_UPDATED_BY   IN     VARCHAR2,
                                  x_status               OUT NUMBER);

   PROCEDURE insert_column_details (P_HEADER_ID         IN     NUMBER,
                                    P_VIEW_ID           IN     NUMBER,
                                    P_COLUMN_TYPE       IN     VARCHAR2,
                                    P_COLUMN_NAME       IN     VARCHAR2,
                                    P_COLUMN_ALIAS      IN     VARCHAR2,
                                    P_COLUMN_POSITION   IN     VARCHAR2,
                                    P_AGGREGATE_BY      IN     VARCHAR2,
                                    P_CREATED_BY        IN     VARCHAR2,
                                    P_LAST_UPDATED_BY   IN     VARCHAR2,
                                    x_status               OUT NUMBER);

   PROCEDURE update_column_details (P_HEADER_ID         IN     NUMBER,
                                    P_VIEW_ID           IN     NUMBER,
                                    P_COLUMN_ID         IN     NUMBER,
                                    P_COLUMN_TYPE       IN     VARCHAR2,
                                    P_COLUMN_NAME       IN     VARCHAR2,
                                    P_COLUMN_ALIAS      IN     VARCHAR2,
                                    P_COLUMN_POSITION   IN     VARCHAR2,
                                    P_AGGREGATE_BY      IN     VARCHAR2,
                                    P_LAST_UPDATED_BY   IN     VARCHAR2,
                                    x_status               OUT NUMBER);

   PROCEDURE delete_view_columns (P_HEADER_ID   IN     NUMBER,
                                  P_VIEW_ID     IN     NUMBER,
                                  P_COLUMN_ID   IN     NUMBER,
                                  x_status         OUT NUMBER);

   PROCEDURE get_eligible_sum_cols (P_VIEW_NAME   IN     VARCHAR2,
                                    x_data           OUT result_cursor);

   PROCEDURE get_elgble_non_num_cols (P_VIEW_NAME   IN     VARCHAR2,
                                      x_data           OUT result_cursor);

   PROCEDURE delete_template (p_header_id IN NUMBER, x_status OUT VARCHAR2);

   PROCEDURE get_template_info (p_template_id   IN     VARCHAR2,
                                x_data             OUT RESULT_CURSOR);

   PROCEDURE update_template_info (p_template_id         IN     VARCHAR2,
                                   p_biller_first_name   IN     VARCHAR2,
                                   p_biller_last_name    IN     VARCHAR2,
                                   p_biller_contact_no   IN     VARCHAR2,
                                   p_biller_fax_no       IN     VARCHAR2,
                                   p_biller_email_id     IN     VARCHAR2,
                                   p_customer_email      IN     VARCHAR2,
                                   p_other_email         IN     VARCHAR2,
                                   p_invoice_break       IN     VARCHAR2,
                                   p_multi_tab           IN     VARCHAR2,
                                   p_within_tab          IN     VARCHAR2,
                                   p_user_id             IN     VARCHAR2,
                                   p_text_format         IN     VARCHAR2,
                                   p_suppress_zero       IN     VARCHAR2,
                                   x_status                 OUT NUMBER);

   PROCEDURE get_completed_templates (p_search        IN     VARCHAR2,
                                      p_search_type   IN     VARCHAR2,
                                      x_data             OUT RESULT_CURSOR);

   PROCEDURE is_ready_to_mark_completed (p_header_id   IN     NUMBER,
                                         x_status         OUT VARCHAR2,
                                         x_message        OUT VARCHAR2);

   PROCEDURE validate_template_record (p_profile_name    IN     VARCHAR2,
                                       p_party_name      IN     VARCHAR2,
                                       p_site_location   IN     VARCHAR2,
                                       p_template_id     IN     NUMBER,
                                       x_status             OUT VARCHAR2,
                                       x_messge             OUT VARCHAR2);

   PROCEDURE update_for_comp_incomp (p_header_id   IN     NUMBER,
                                     p_status      IN     VARCHAR2,
                                     x_message        OUT VARCHAR2,
                                     x_status         OUT NUMBER);

   PROCEDURE get_bill_to_sites (p_bill_location   IN     VARCHAR2,
                                p_customer_name   IN     VARCHAR2,
                                p_start_range     IN     NUMBER,
                                p_end_range       IN     NUMBER,
                                p_sort_by         IN     VARCHAR2,
                                p_sort_type       IN     VARCHAR2,
                                x_data               OUT result_cursor,
                                x_count              OUT NUMBER);

   PROCEDURE get_customer_names (p_customer_name   IN     VARCHAR2,
                                 p_start_range     IN     NUMBER,
                                 p_end_range       IN     NUMBER,
                                 p_sort_by         IN     VARCHAR2,
                                 p_sort_type       IN     VARCHAR2,
                                 x_data               OUT result_cursor,
                                 x_count              OUT NUMBER);

   FUNCTION is_customer_exist_in_template (p_customer_name   IN VARCHAR2,
                                           p_template_id     IN NUMBER)
      RETURN VARCHAR2;

   PROCEDURE get_parent_customer
                                    (p_parent_customer  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     p_sort_by        IN     VARCHAR2,
                                     p_sort_type      IN     VARCHAR2,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER);

   PROCEDURE get_customer_group
                                    (p_customer_group  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER);



   PROCEDURE create_template (p_customer_name    IN     VARCHAR2,
                              p_group_name       IN     VARCHAR2,
                              p_parent_customer     IN     VARCHAR2,
                              p_bill_location    IN     VARCHAR2,
                              p_template_level   IN     VARCHAR2,
                              p_user_id          IN     VARCHAR2,
                              x_status              OUT VARCHAR2,
                              x_template_id         OUT NUMBER);


   PROCEDURE copy_template (p_header_id        IN     NUMBER,
                            p_customer_name    IN     VARCHAR2,
                            p_group_name       IN     VARCHAR2,
                            p_parent_customer     IN     VARCHAR2,
                            p_bill_location    IN     VARCHAR2,
                            p_template_level   IN     VARCHAR2,
                            p_user_id          IN     VARCHAR2,
                            x_status              OUT VARCHAR2,
                            x_template_id         OUT NUMBER);


   PROCEDURE is_concurrent_program_running (x_status OUT VARCHAR2);

   PROCEDURE get_par_cust_heirarchy (
        p_customer_name IN       VARCHAR2,
		x_data               OUT result_cursor,
		x_count              OUT NUMBER);

   PROCEDURE get_cust_grp_heirarchy (
        p_customer_group  IN     VARCHAR2,
		x_data               OUT result_cursor,
		x_count              OUT NUMBER);
END CANON_E479_TEMPLATE_PKG;
 
/
SHOW ERRORS;			

create or replace PACKAGE BODY CANON_E479_TEMPLATE_PKG
AS
  /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_INV_SRCH_UTIL_PKG.sql                                       *
     * DESCRIPTION     : This package is used in defining special billing template. This is     *
	 *                   used in search page, create/update template, delete/add template views *
	 *                   and columns                                                            *
	 *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/03/2015     Initial Creation
     *                                  2/21/2017 - As per Kohei Aratani,
	 *                                  ds_acct_reln.ds_acct_num is parent,
	 *                                  ds_acct_reln.reln_ds_acct_num is child.
	 * Lakshmi Gopalsami 06/12/2017     DB2 changes *
     */
   PROCEDURE get_templates (p_search        IN     VARCHAR2,
                            p_search_type   IN     VARCHAR2,
                            x_data             OUT RESULT_CURSOR)
   AS
      l_procedure_name   VARCHAR2 (35) := 'get_templates';
      l_search           VARCHAR2 (200);
   BEGIN
      IF (p_search IS NOT NULL AND LENGTH (TRIM (p_search)) > 0)
      THEN
         l_search := l_search || TRIM (p_search) || '%';
      ELSE
         l_search := '%';
      END IF;

      /* S21 Replacement for parent customer/group
     */
      if(p_search_type = C_SEARCH_PARENT) then
             open x_data for
             SELECT DISTINCT result.*
                FROM (
				    SELECT DISTINCT
                             NVL (header_id, -1) header_id,
                             NVL (acct.ds_acct_nm, '') parent_acct,
                             '' group_name,
                              '' customer_name,
                             -1 bill_to_use,
                             NVL (templ.other_email, '') other_email,
                             NVL (templ.customer_email, '') customer_email,
                             NVL (templ.status_flag, ' ') status_flag,
                             NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
                             NVL (templ.SUPPRESS_ZERO_INVOICE, 'N')
                                suppress_zero,
                             'PARENT_CUSTOMER' template_level
                        FROM (SELECT *
                                FROM CANON_E479_WEB_TEMPL_HEADER
                               WHERE NVL (status_flag, '~') <> C_STS_DELETED) templ,
                             sell_to_cust acct,
                             DS_CUST_INV_RULE acct_setup,
                             cust_bllg_vcle bllg_vcle
                       WHERE     templ.parent_customer_name = acct.ds_acct_nm
                             AND acct.ds_acct_nm LIKE l_search
                             AND TEMPL.TEMPLATE_LEVEL = 'PARENT_CUSTOMER'
							 AND acct.ezcancelflag ='0'
							 AND acct.glbl_cmpy_cd ='ADB'
							 AND acct_setup.ezcancelflag ='0'
							 AND acct_setup.glbl_cmpy_cd ='ADB'
							 AND bllg_vcle.ezcancelflag ='0'
							 AND bllg_vcle.glbl_cmpy_cd ='ADB'
                             AND (    acct.sell_to_cust_cd = acct_setup.ds_acct_num
                                  AND acct_setup.cust_bllg_vcle_cd =
                                         bllg_vcle.cust_bllg_vcle_cd
                                  AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
                                         ('SPECIAL BILL REVIEW REQD',
                                          'SPECIAL BILL NO REVIEW')
                                  and acct.sell_to_cust_cd in
                                          (select distinct reln.ds_acct_num--acct.ds_accT_num
                                      from ds_acct_reln reln --, DS_ACCT_CUST acct
                                      --where reln.ds_acct_reln_tp_cd =2
									  where reln.ds_acct_reln_tp_cd =1 -- 3/30/2017. change as per Kohei Aratani
                                      and reln.ds_acct_num <> reln.reln_ds_acct_num
                                      --and reln.reln_ds_acct_num = acct.ds_acct_num
									  --and reln.ds_acct_num = acct.ds_acct_num
									  AND reln.ezcancelflag ='0'
									  AND reln.glbl_cmpy_cd ='ADB'
									  and DS_ACCT_RELN_BILL_TO_FLG = 'Y'
									  AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt,'yyyymmdd'),SYSDATE - 1)
									  AND NVL (TO_DATE (reln.EFF_thru_DT,'yyyymmdd'),SYSDATE + 1)  ))
                      UNION ALL
                      SELECT DISTINCT
                             NVL (header_id, -1) header_id,
                              NVL (acct.ds_acct_nm, '') parent_acct,
                             '' group_name,
                             '' customer_name,
                             -1 bill_to_use,
                             NVL (templ.other_email, '') other_email,
                             NVL (templ.customer_email, '') customer_email,
                             NVL (templ.status_flag, ' ') status_flag,
                             NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
                             NVL (templ.SUPPRESS_ZERO_INVOICE, 'N')
                                suppress_zero,
                             'PARENT_CUSTOMER' template_level
                        FROM (SELECT *
                                FROM CANON_E479_WEB_TEMPL_HEADER
                               WHERE NVL (status_flag, '~') <> C_STS_DELETED) templ,
                             sell_to_cust acct
                       WHERE     templ.parent_customer_name(+) = acct.ds_acct_nm
                             AND TEMPL.TEMPLATE_LEVEL(+) = 'PARENT_CUSTOMER'
                             AND acct.ds_acct_nm LIKE l_search
							 AND acct.ezcancelflag ='0'
							 AND acct.glbl_cmpy_cd ='ADB'
                             AND acct.sell_to_cust_cd IN
                                    (SELECT loc.ds_acct_num
                                       FROM DS_CUST_INV_RULE acct_setup,
                                            cust_bllg_vcle bllg_vcle,
                                            acct_loc loc,
                                            BILL_TO_CUST bill_to
                                      WHERE 1=1
									       AND loc.ezcancelflag ='0'
										   AND loc.glbl_cmpy_cd ='ADB'
										   AND bill_to.ezcancelflag ='0'
										   AND bill_to.glbl_cmpy_cd ='ADB'
										   AND acct_setup.ezcancelflag ='0'
										   AND acct_setup.glbl_cmpy_cd ='ADB'
										   AND bllg_vcle.ezcancelflag ='0'
										   AND bllg_vcle.glbl_cmpy_cd ='ADB'
 									        AND loc.pty_loc_pk =bill_to.pty_loc_pk
                                            AND loc.loc_num = bill_to.loc_num
                                            AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD
                                            AND bill_to.loc_num =acct_setup.loc_num
                                            AND acct_setup.cust_bllg_vcle_cd =
                                                   bllg_vcle.cust_bllg_vcle_cd
                                            AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
                                                   ('SPECIAL BILL REVIEW REQD',
                                                    'SPECIAL BILL NO REVIEW')
											/* Exclude bill-to for which the template is already setup at bill-to location */
                                            AND bill_to.BILL_TO_CUST_CD not in
											( select bill_to_location
											     from CANON_E479_WEB_TEMPL_HEADER
												where customer_name = acct.ds_acct_nm
												   and status_flag ='Complete'
											)
                        and acct.sell_to_cust_cd in
                                          (select distinct  reln.ds_accT_num-- acct.ds_accT_num
                                      from ds_acct_reln reln --, DS_ACCT_CUST acct
                                      --where reln.ds_acct_reln_tp_cd =2
									  where reln.ds_acct_reln_tp_cd =1 -- 3/30/2017. change as per Kohei Aratani
                                      and reln.ds_acct_num <> reln.reln_ds_acct_num
                                      --and reln.ds_accT_num = acct.ds_acct_num
									  and DS_ACCT_RELN_BILL_TO_FLG = 'Y'
									  AND reln.ezcancelflag ='0'
									  AND reln.glbl_cmpy_cd ='ADB'
									  AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt,'yyyymmdd'),SYSDATE - 1)
									  AND NVL (TO_DATE (reln.EFF_thru_DT,'yyyymmdd'),SYSDATE + 1))
                      )) result
        ORDER BY result.parent_acct;

      ELSIF (p_search_type = C_SEARCH_GROUP) THEN
	           OPEN x_data FOR
              SELECT DISTINCT result.*
                FROM (
						SELECT DISTINCT NVL (header_id, -1) header_id,
							'' parent_acct,
							GRP.ds_acct_grp_nm group_name,
							'' customer_name,
							-1 bill_to_use,
							NVL (templ.other_email, '') other_email,
							NVL (templ.customer_email, '') customer_email,
							NVL (templ.status_flag, ' ') status_flag,
							NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
							NVL (templ.SUPPRESS_ZERO_INVOICE, 'N') suppress_zero,
							'CUSTOMER_GROUP' template_level
						  FROM (SELECT *
								  FROM CANON_E479_WEB_TEMPL_HEADER
								 WHERE NVL (status_flag, '~') <> C_STS_DELETED) templ,
							   ds_acct_grp_asg grp_assgn,
							   ds_acct_grp grp,
							   sell_to_cust acct
						 WHERE  1=1
						       AND acct.ezcancelflag ='0'
							   AND acct.glbl_cmpy_cd ='ADB'
							   AND grp_assgn.ezcancelflag ='0'
							   AND grp_assgn.glbl_cmpy_cd ='ADB'
							   AND grp.ezcancelflag ='0'
							   AND grp.glbl_cmpy_cd ='ADB'
     						   AND templ.ds_acct_grp_nm(+) = GRP.ds_acct_grp_nm
							   AND TEMPL.TEMPLATE_LEVEL(+) = 'CUSTOMER_GROUP'
							   AND grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
							   AND SYSDATE BETWEEN NVL (TO_DATE (grp_assgn.eff_from_dt, 'yyyymmdd'),
														SYSDATE - 1)
											   AND NVL (TO_DATE (grp_assgn.EFF_thru_DT, 'yyyymmdd'),
														SYSDATE + 1)
							   AND acct.sell_to_cust_cd = grp_assgn.DS_ACCT_NUM
							   AND GRP.ds_acct_grp_nm LIKE l_search
							   AND acct.sell_to_cust_cd IN
									  ( /* Bill-to level setup */
									   SELECT loc.ds_acct_num
										 FROM DS_CUST_INV_RULE acct_setup,
											  cust_bllg_vcle bllg_vcle,
											  acct_loc loc,
											  BILL_TO_CUST bill_to
										WHERE 1=1
										      AND loc.ezcancelflag ='0'
											  AND loc.glbl_cmpy_cd ='ADB'
											  AND bill_to.ezcancelflag ='0'
											  AND bill_to.glbl_cmpy_cd ='ADB'
											  AND acct_setup.ezcancelflag ='0'
											  AND acct_setup.glbl_cmpy_cd ='ADB'
											  AND bllg_vcle.ezcancelflag ='0'
											  AND bllg_vcle.glbl_cmpy_cd ='ADB'
  										      AND loc.pty_loc_pk = bill_to.pty_loc_pk
											  AND loc.loc_num = bill_to.loc_num
											  AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD
											  AND bill_to.loc_num = acct_setup.loc_num
											  AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd
											  AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
													 ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
										UNION all
										/* Customer level setup */
										SELECT acct.sell_to_cust_cd
										 FROM DS_CUST_INV_RULE acct_setup,
											  cust_bllg_vcle bllg_vcle,
											  sell_to_cust acct
										WHERE 1=1
										      AND acct.ezcancelflag ='0'
											  AND acct.glbl_cmpy_cd ='ADB'
											  AND acct_setup.ezcancelflag ='0'
											  AND acct_setup.glbl_cmpy_cd ='ADB'
											  AND bllg_vcle.ezcancelflag ='0'
											  AND bllg_vcle.glbl_cmpy_cd ='ADB'
										      AND acct.sell_to_cust_cd =acct_setup.ds_acct_num
											  AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd
											  AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
													 ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
													 )) result
           ORDER BY result.group_name;
      ELSIF (p_search_type = C_SEARCH_CUSTOMER)
      THEN
         OPEN x_data FOR
              SELECT DISTINCT result.*
                FROM (
				    SELECT DISTINCT
                             NVL (header_id, -1) header_id,
                             '' parent_acct,
                             '' group_name,
                             NVL (acct.ds_acct_nm, '') customer_name,
                             -1 bill_to_use,
                             NVL (templ.other_email, '') other_email,
                             NVL (templ.customer_email, '') customer_email,
                             NVL (templ.status_flag, ' ') status_flag,
                             NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
                             NVL (templ.SUPPRESS_ZERO_INVOICE, 'N')
                                suppress_zero,
                             'CUSTOMER' template_level
                        FROM (SELECT *
                                FROM CANON_E479_WEB_TEMPL_HEADER
                               WHERE NVL (status_flag, '~') <> C_STS_DELETED) templ,
                             sell_to_cust acct,
                             DS_CUST_INV_RULE acct_setup,
                             cust_bllg_vcle bllg_vcle
                       WHERE  1=1
					         AND acct.ezcancelflag ='0'
							 AND acct.glbl_cmpy_cd ='ADB'
							 AND acct_setup.ezcancelflag ='0'
							 AND acct_setup.glbl_cmpy_cd ='ADB'
							 AND bllg_vcle.ezcancelflag ='0'
							 AND bllg_vcle.glbl_cmpy_cd ='ADB'
   					         AND templ.customer_name = acct.ds_acct_nm
                             AND acct.ds_acct_nm LIKE l_search
                             AND TEMPL.TEMPLATE_LEVEL = 'CUSTOMER'
                             AND (    acct.sell_to_cust_cd = acct_setup.ds_acct_num
                                  AND acct_setup.cust_bllg_vcle_cd =
                                         bllg_vcle.cust_bllg_vcle_cd
                                  AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) NOT IN
                                         ('SPECIAL BILL REVIEW REQD',
                                          'SPECIAL BILL NO REVIEW'))
                      UNION ALL
                      SELECT DISTINCT
                             NVL (header_id, -1) header_id,
                             '' parent_acct,
                             '' group_name,
                             NVL (acct.ds_acct_nm, '') customer_name,
                             -1 bill_to_use,
                             NVL (templ.other_email, '') other_email,
                             NVL (templ.customer_email, '') customer_email,
                             NVL (templ.status_flag, ' ') status_flag,
                             NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
                             NVL (templ.SUPPRESS_ZERO_INVOICE, 'N')
                                suppress_zero,
                             'CUSTOMER' template_level
                        FROM (SELECT *
                                FROM CANON_E479_WEB_TEMPL_HEADER
                               WHERE NVL (status_flag, '~') <> C_STS_DELETED) templ,
                             sell_to_cust acct
                       WHERE   1=1
					         AND acct.ezcancelflag ='0'
							 AND acct.glbl_cmpy_cd ='ADB'
							 AND templ.customer_name(+) = acct.ds_acct_nm
                             AND TEMPL.TEMPLATE_LEVEL(+) = 'CUSTOMER'
                             AND acct.ds_acct_nm LIKE l_search
                             AND acct.sell_to_cust_cd IN
                                    (SELECT loc.ds_acct_num
                                       FROM DS_CUST_INV_RULE acct_setup,
                                            cust_bllg_vcle bllg_vcle,
                                            acct_loc loc,
                                            BILL_TO_CUST bill_to
                                      WHERE  1=1
										    AND loc.ezcancelflag ='0'
											AND loc.glbl_cmpy_cd ='ADB'
											AND bill_to.ezcancelflag ='0'
											AND bill_to.glbl_cmpy_cd ='ADB'
											AND acct_setup.ezcancelflag ='0'
											AND acct_setup.glbl_cmpy_cd ='ADB'
											AND bllg_vcle.ezcancelflag ='0'
											AND bllg_vcle.glbl_cmpy_cd ='ADB'
											AND loc.pty_loc_pk =bill_to.pty_loc_pk
                                            AND loc.loc_num = bill_to.loc_num
                                            AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD
                                            AND bill_to.loc_num =
                                                   acct_setup.loc_num
                                            AND acct_setup.cust_bllg_vcle_cd =
                                                   bllg_vcle.cust_bllg_vcle_cd
                                            AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM)  IN
                                                   ('SPECIAL BILL REVIEW REQD',
                                                    'SPECIAL BILL NO REVIEW')
											/* Exclude bill-to for which the template is already setup at bill-to location */
                                            AND bill_to.BILL_TO_CUST_CD  in
											( select bill_to_location
											     from CANON_E479_WEB_TEMPL_HEADER
												where customer_name = acct.ds_acct_nm
												   and status_flag ='Complete'
											)
                                            )) result
            ORDER BY result.customer_name;
      ELSIF (p_search_type = C_SEARCH_SITE)
      THEN
         OPEN x_data FOR
              SELECT DISTINCT
                     NVL (header_id, -1) header_id,
                     '' parent_acct,
                     '' group_name,
                     NVL (acct.ds_acct_nm, '') customer_name,
                     --NVL (bill_to.loc_num, -1) bill_to_use,
					 NVL(bill_to.BILL_TO_CUST_CD, -1) bill_to_use,
                     NVL (templ.other_email, '') other_email,
                     NVL (templ.customer_email, '') customer_email,
                     NVL (templ.status_flag, ' ') status_flag,
                     NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
                     NVL (templ.SUPPRESS_ZERO_INVOICE, 'N') suppress_zero,
                     'BILL_TO' template_level
                FROM (SELECT *
                        FROM CANON_E479_WEB_TEMPL_HEADER
                       WHERE NVL (status_flag, '~') <> C_STS_DELETED) templ,
                     sell_to_cust acct,
                     DS_CUST_INV_RULE acct_setup,
                     cust_bllg_vcle bllg_vcle,
                     acct_loc loc,
                     BILL_TO_CUST bill_to
               WHERE   1=1
			         AND loc.ezcancelflag ='0'
					 AND loc.glbl_cmpy_cd ='ADB'
					 AND bill_to.ezcancelflag ='0'
					 AND bill_to.glbl_cmpy_cd ='ADB'
					 AND acct_setup.ezcancelflag ='0'
					 AND acct_setup.glbl_cmpy_cd ='ADB'
					 AND bllg_vcle.ezcancelflag ='0'
					 AND bllg_vcle.glbl_cmpy_cd ='ADB'
					 AND templ.bill_to_location(+) = bill_to.BILL_TO_CUST_CD
			         AND TEMPL.TEMPLATE_LEVEL(+) = 'BILL_TO'
					 AND templ.customer_name(+) = acct.ds_acct_nm
                     AND bill_to.BILL_TO_CUST_CD LIKE l_search
                     AND acct.sell_to_cust_cd = loc.ds_acct_num
                     AND loc.pty_loc_pk = bill_to.pty_loc_pk
                     AND loc.loc_num = bill_to.loc_num
                     AND bill_to.BILL_TO_CUST_CD = acct_setup.BILL_TO_CUST_CD
                     AND bill_to.loc_num = acct_setup.loc_num
                     AND acct_setup.cust_bllg_vcle_cd =
                            bllg_vcle.cust_bllg_vcle_cd
                     AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
                            ('SPECIAL BILL REVIEW REQD',
                             'SPECIAL BILL NO REVIEW')
            ORDER BY customer_name, bill_to_use;
      END IF;
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
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;
   END get_templates;

   FUNCTION append_message (p_message IN VARCHAR2, new_message IN VARCHAR2)
      RETURN VARCHAR2
   AS
      x_message          VARCHAR2 (3000);
      l_procedure_name   VARCHAR2 (20) := 'append_message';
   BEGIN
      IF (p_message IS NOT NULL AND LENGTH (TRIM (p_message)) > 0)
      THEN
         x_message := p_message || '<p>' || new_message;
      ELSE
         x_message := new_message;
      END IF;

      RETURN x_message;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_message,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
         x_message := ' ';
   END append_message;

   PROCEDURE get_valueset_data (p_valueset_name   IN     VARCHAR2,
                                x_data               OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'get_valueset_data';
      l_qry_string       VARCHAR2 (4000);
	  lv_validation_status VARCHAR2(1);
	  lv_error_msg       VARCHAR2(4000);
	  ln_count           NUMBER;

   BEGIN
      /*  S21 replacement
          Currently created tables for each value set. Need to check whether can be merged with code table in S21
       */
		/* CANON_COMMON_UTIL_PKG.get_code_value(
		    p_cd_name            =>  p_valueset_name,
            p_result_cursor      =>  x_data,
			p_result_count       => ln_count,
			p_validation_status  => lv_validation_status,
			p_error_msg          => lv_error_msg);
        */

		IF p_valueset_name='CANON_E479_TEMPL_VIEW_LIST' THEN
		  OPEN x_data for select view_name from CANON_E479_TEMPL_VIEW_LIST_V WHERE VIEW_TYPE='AUTOMATIC_EXCEL';
		ELSIF p_valueset_name='CANON_E479_TEMPL_COMP_COLS' THEN
		  OPEN x_data for select column_name from CANON_E479_TEMPL_COMP_COLS_V WHERE NVL(IS_ACTIVE,'N') ='Y';
		ELSIF p_valueset_name='CANON_E479_TEMPL_CONCAT_COLS' THEN
		  OPEN x_data for select column_name from CANON_E479_TEMPL_CONCAT_COLS_V WHERE NVL(IS_ACTIVE,'N') ='Y';
		ELSIF p_valueset_name='CANON_E479_TEMPL_INV_BREAK' THEN
		  OPEN x_data for select column_name from CANON_E479_TEMPL_INV_BREAK_V;
		ELSIF p_valueset_name='CANON_E479_TEMPL_MULTITAB_BRK' THEN
		  OPEN x_data for select column_name from CANON_E479_TEMPL_MULTITAB_BR_V;
		END IF;

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
   END get_valueset_data;

   PROCEDURE get_sorting_seq1 (p_view_name   IN     VARCHAR2,
                               x_data           OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'get_sorting_seq1';
   BEGIN
      OPEN x_data FOR
           SELECT column_name
             FROM all_tab_columns
            WHERE table_name = p_view_name
         ORDER BY column_name ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_view_name,
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
   END get_sorting_seq1;


   PROCEDURE get_sorting_seq2 (p_view_name   IN     VARCHAR2,
                               p_sort_seq1   IN     VARCHAR2 DEFAULT NULL,
                               x_data           OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'get_sorting_seq2';
   BEGIN
      OPEN x_data FOR
           SELECT column_name
             FROM all_tab_columns
            WHERE     table_name = p_view_name
                  AND column_name <> NVL (p_sort_seq1, 'x')
         ORDER BY column_name ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_view_name,
                                                  p_sort_seq1,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, 1
              FROM DUAL
             WHERE 1 = -1;
   END get_sorting_seq2;



   PROCEDURE get_sorting_seq3 (p_view_name   IN     VARCHAR2,
                               p_sort_seq1   IN     VARCHAR2 DEFAULT NULL,
                               p_sort_seq2   IN     VARCHAR2 DEFAULT NULL,
                               x_data           OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'get_sorting_seq3';
   BEGIN
      OPEN x_data FOR
           SELECT column_name
             FROM all_tab_columns
            WHERE     table_name = p_view_name
                  AND column_name <> NVL (p_sort_seq1, 'x')
                  AND column_name <> NVL (p_sort_seq2, 'x')
         ORDER BY column_name ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_view_name,
                                                  p_sort_seq1,
                                                  p_sort_seq2,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, 1
              FROM DUAL
             WHERE 1 = -1;
   END get_sorting_seq3;

   PROCEDURE get_view_details (P_HEADER_ID   IN     NUMBER,
                               x_data           OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'GET_VIEW_DETAILS';
   BEGIN
      OPEN x_data FOR
           SELECT *
             FROM CANON_E479_WEB_TEMPL_VIEW
            WHERE HEADER_ID = P_HEADER_ID
         ORDER BY view_sequence;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_HEADER_ID,
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
   END get_view_details;

   PROCEDURE get_column_details (P_HEADER_ID   IN     NUMBER,
                                 P_VIEW_ID     IN     NUMBER,
                                 x_data           OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'GET_COLUMN_DETAILS';
   BEGIN
      OPEN x_data FOR
           SELECT *
             FROM CANON_E479_WEB_TEMPL_COLS
            WHERE HEADER_ID = P_HEADER_ID AND VIEW_ID = P_VIEW_ID
         ORDER BY COLUMN_POSITION;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_HEADER_ID,
                                                  P_VIEW_ID,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, 1
              FROM DUAL
             WHERE 1 = -1;
   END get_column_details;

   PROCEDURE get_initial_column_list (P_VIEW_NAME   IN     VARCHAR2,
                                      x_data           OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'GET_INITIAL_COLUMN_LIST';
   BEGIN
      OPEN x_data FOR
           SELECT COLUMN_NAME
             FROM all_tab_columns
            WHERE TABLE_NAME = P_VIEW_NAME
         ORDER BY COLUMN_NAME ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_VIEW_NAME,
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
   END get_initial_column_list;

   PROCEDURE delete_view_details (P_HEADER_ID   IN     NUMBER,
                                  P_VIEW_ID     IN     NUMBER,
                                  x_status         OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (25) := 'DELETE_VIEW_DETAILS';
   BEGIN
      x_status := 1;

      DELETE FROM CANON_E479_WEB_TEMPL_COLS
            WHERE HEADER_ID = P_HEADER_ID AND VIEW_ID = P_VIEW_ID;



      DELETE FROM CANON_E479_WEB_TEMPL_VIEW
            WHERE HEADER_ID = P_HEADER_ID AND VIEW_ID = P_VIEW_ID;

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;

         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_HEADER_ID,
                                                  P_VIEW_ID,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END delete_view_details;

   PROCEDURE insert_view_details (P_HEADER_ID         IN     NUMBER,
                                  P_VIEW_NAME         IN     VARCHAR2,
                                  P_VIEW_ALIAS        IN     VARCHAR2,
                                  P_VIEW_SEQ          IN     NUMBER,
                                  P_SORT_PREF1        IN     VARCHAR2,
                                  P_SORT_PREF2        IN     VARCHAR2,
                                  P_SORT_PREF3        IN     VARCHAR2,
                                  P_CREATED_BY        IN     VARCHAR2,
                                  P_LAST_UPDATED_BY   IN     VARCHAR2,
                                  x_status               OUT NUMBER)
   AS
      L_VIEW_ID          NUMBER;
      l_procedure_name   VARCHAR2 (30) := 'INSERT_VIEW_DETAILS';
      l_view_cols        result_cursor;
      l_column_name      VARCHAR2 (100);
      l_counter          NUMBER := 0;
      l_column_alias     VARCHAR2 (100);
      l_column_id        NUMBER;
   BEGIN
      x_status := -1;

      SELECT CANON_E479_WEB_TEMPL_VIEW_SEQ.NEXTVAL INTO L_VIEW_ID FROM DUAL;



      IF L_VIEW_ID IS NOT NULL
      THEN
         INSERT INTO CANON_E479_WEB_TEMPL_VIEW (HEADER_ID,
                                                VIEW_ID,
                                                VIEW_NAME,
                                                VIEW_ALIAS,
                                                VIEW_SEQUENCE,
                                                SORT_PREF_COL1,
                                                SORT_PREF_COL2,
                                                SORT_PREF_COL3,
                                                CREATION_DATE,
                                                CREATED_BY,
                                                LAST_UPDATE_DATE,
                                                LAST_UPDATED_BY)
              VALUES (P_HEADER_ID,
                      L_VIEW_ID,
                      P_VIEW_NAME,
                      P_VIEW_ALIAS,
                      P_VIEW_SEQ,
                      P_SORT_PREF1,
                      P_SORT_PREF2,
                      P_SORT_PREF3,
                      SYSDATE,
                      P_CREATED_BY,
                      SYSDATE,
                      P_LAST_UPDATED_BY);

         COMMIT;

         x_status := L_VIEW_ID;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_HEADER_ID,
                                                  P_VIEW_NAME,
                                                  P_VIEW_ALIAS,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END insert_view_details;

   PROCEDURE update_view_details (P_HEADER_ID         IN     NUMBER,
                                  P_VIEW_ID           IN     NUMBER,
                                  P_VIEW_NAME         IN     VARCHAR2,
                                  P_VIEW_ALIAS        IN     VARCHAR2,
                                  P_VIEW_SEQ          IN     NUMBER,
                                  P_SORT_PREF1        IN     VARCHAR2,
                                  P_SORT_PREF2        IN     VARCHAR2,
                                  P_SORT_PREF3        IN     VARCHAR2,
                                  P_LAST_UPDATED_BY   IN     VARCHAR2,
                                  x_status               OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (30) := 'UPDATE_VIEW_DETAILS';
   BEGIN
      x_status := -1;

      UPDATE CANON_E479_WEB_TEMPL_VIEW
         SET VIEW_NAME = P_VIEW_NAME,
             VIEW_ALIAS = P_VIEW_ALIAS,
             VIEW_SEQUENCE = P_VIEW_SEQ,
             SORT_PREF_COL1 = P_SORT_PREF1,
             SORT_PREF_COL2 = P_SORT_PREF2,
             SORT_PREF_COL3 = P_SORT_PREF3,
             LAST_UPDATE_DATE = SYSDATE,
             LAST_UPDATED_BY = P_LAST_UPDATED_BY
       WHERE HEADER_ID = P_HEADER_ID AND VIEW_ID = P_VIEW_ID;

      COMMIT;

      x_status := 1;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;

         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_HEADER_ID,
                                                  P_VIEW_NAME,
                                                  P_VIEW_ALIAS,
                                                  P_VIEW_SEQ,
                                                  P_SORT_PREF1,
                                                  P_SORT_PREF2,
                                                  SQLERRM);
   END update_view_details;

   PROCEDURE insert_column_details (P_HEADER_ID         IN     NUMBER,
                                    P_VIEW_ID           IN     NUMBER,
                                    P_COLUMN_TYPE       IN     VARCHAR2,
                                    P_COLUMN_NAME       IN     VARCHAR2,
                                    P_COLUMN_ALIAS      IN     VARCHAR2,
                                    P_COLUMN_POSITION   IN     VARCHAR2,
                                    P_AGGREGATE_BY      IN     VARCHAR2,
                                    P_CREATED_BY        IN     VARCHAR2,
                                    P_LAST_UPDATED_BY   IN     VARCHAR2,
                                    x_status               OUT NUMBER)
   AS
      L_COLUMN_ID        NUMBER;
      l_procedure_name   VARCHAR2 (30) := 'INSERT_COLUMN_DETAILS';
   BEGIN
      SELECT CANON_E479_WEB_TEMPL_COLS_SEQ.NEXTVAL INTO L_COLUMN_ID FROM DUAL;

      IF L_COLUMN_ID IS NOT NULL
      THEN
         INSERT INTO CANON_E479_WEB_TEMPL_COLS (HEADER_ID,
                                                VIEW_ID,
                                                COLUMN_ID,
                                                COLUMN_TYPE,
                                                COLUMN_NAME,
                                                COLUMN_ALIAS,
                                                COLUMN_POSITION,
                                                AGGREGATE_BY,
                                                CREATION_DATE,
                                                CREATED_BY,
                                                LAST_UPDATE_DATE,
                                                LAST_UPDATED_BY)
              VALUES (P_HEADER_ID,
                      P_VIEW_ID,
                      L_COLUMN_ID,
                      P_COLUMN_TYPE,
                      P_COLUMN_NAME,
                      P_COLUMN_ALIAS,
                      P_COLUMN_POSITION,
                      P_AGGREGATE_BY,
                      SYSDATE,
                      P_CREATED_BY,
                      SYSDATE,
                      P_LAST_UPDATED_BY);

         COMMIT;


         x_status := L_COLUMN_ID;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;

         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_HEADER_ID,
                                                  P_VIEW_ID,
                                                  P_COLUMN_TYPE,
                                                  P_COLUMN_NAME,
                                                  P_COLUMN_ALIAS,
                                                  P_COLUMN_POSITION,
                                                  SQLERRM);
   END insert_column_details;

   PROCEDURE update_column_details (P_HEADER_ID         IN     NUMBER,
                                    P_VIEW_ID           IN     NUMBER,
                                    P_COLUMN_ID         IN     NUMBER,
                                    P_COLUMN_TYPE       IN     VARCHAR2,
                                    P_COLUMN_NAME       IN     VARCHAR2,
                                    P_COLUMN_ALIAS      IN     VARCHAR2,
                                    P_COLUMN_POSITION   IN     VARCHAR2,
                                    P_AGGREGATE_BY      IN     VARCHAR2,
                                    P_LAST_UPDATED_BY   IN     VARCHAR2,
                                    x_status               OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (25) := 'UPDATE_COLUMN_DETAILS';
   BEGIN
      UPDATE CANON_E479_WEB_TEMPL_COLS
         SET COLUMN_TYPE = P_COLUMN_TYPE,
             COLUMN_NAME = P_COLUMN_NAME,
             COLUMN_ALIAS = P_COLUMN_ALIAS,
             COLUMN_POSITION = P_COLUMN_POSITION,
             AGGREGATE_BY = P_AGGREGATE_BY,
             LAST_UPDATE_DATE = SYSDATE,
             LAST_UPDATED_BY = P_LAST_UPDATED_BY
       WHERE     HEADER_ID = P_HEADER_ID
             AND VIEW_ID = P_VIEW_ID
             AND COLUMN_ID = P_COLUMN_ID;

      COMMIT;
      x_status := 1;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_HEADER_ID,
                                                  P_VIEW_ID,
                                                  P_COLUMN_ID,
                                                  P_COLUMN_TYPE,
                                                  P_COLUMN_NAME,
                                                  P_COLUMN_ALIAS,
                                                  SQLERRM);
   END update_column_details;

   PROCEDURE delete_view_columns (P_HEADER_ID   IN     NUMBER,
                                  P_VIEW_ID     IN     NUMBER,
                                  P_COLUMN_ID   IN     NUMBER,
                                  x_status         OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (30) := 'DELETE_VIEW_COLUMNS';
   BEGIN
      x_status := 1;

      DELETE FROM CANON_E479_WEB_TEMPL_COLS
            WHERE     HEADER_ID = P_HEADER_ID
                  AND VIEW_ID = P_VIEW_ID
                  AND COLUMN_ID = P_COLUMN_ID;

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_HEADER_ID,
                                                  P_VIEW_ID,
                                                  P_COLUMN_ID,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END delete_view_columns;

   PROCEDURE get_eligible_sum_cols (P_VIEW_NAME   IN     VARCHAR2,
                                    x_data           OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'GET_ELIGIBLE_SUM_COLS';
   BEGIN
      OPEN x_data FOR
           SELECT COLUMN_NAME
             FROM all_tab_columns
            WHERE TABLE_NAME = P_VIEW_NAME AND DATA_TYPE = 'NUMBER'
         ORDER BY COLUMN_NAME ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_VIEW_NAME,
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
   END get_eligible_sum_cols;

   PROCEDURE get_elgble_non_num_cols (P_VIEW_NAME   IN     VARCHAR2,
                                      x_data           OUT result_cursor)
   AS
      l_procedure_name   VARCHAR2 (25) := 'GET_ELGBLE_NON_NUM_COLS';
   BEGIN
      OPEN x_data FOR
           SELECT COLUMN_NAME
             FROM all_tab_columns
            WHERE TABLE_NAME = P_VIEW_NAME AND DATA_TYPE <> 'NUMBER'
         --and length(COLUMN_NAME) <=25
         ORDER BY COLUMN_NAME ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  P_VIEW_NAME,
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
   END get_elgble_non_num_cols;

   PROCEDURE delete_template (p_header_id IN NUMBER, x_status OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (100) := 'delete_template';
   BEGIN
      UPDATE CANON_E479_WEB_TEMPL_HEADER
         SET STATUS_FLAG = C_STS_DELETED, last_update_date = SYSDATE
       WHERE header_id = p_header_id;

      COMMIT;

      x_status := 'Y';
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := 'N';
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_header_id,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END delete_template;

   PROCEDURE get_template_info (p_template_id   IN     VARCHAR2,
                                x_data             OUT RESULT_CURSOR)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_template_info';
   BEGIN
      OPEN x_data FOR
         SELECT *
           FROM CANON_E479_WEB_TEMPL_HEADER
          WHERE header_id = p_template_id;
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
   END get_template_info;

   PROCEDURE update_template_info (p_template_id         IN     VARCHAR2,
                                   p_biller_first_name   IN     VARCHAR2,
                                   p_biller_last_name    IN     VARCHAR2,
                                   p_biller_contact_no   IN     VARCHAR2,
                                   p_biller_fax_no       IN     VARCHAR2,
                                   p_biller_email_id     IN     VARCHAR2,
                                   p_customer_email      IN     VARCHAR2,
                                   p_other_email         IN     VARCHAR2,
                                   p_invoice_break       IN     VARCHAR2,
                                   p_multi_tab           IN     VARCHAR2,
                                   p_within_tab          IN     VARCHAR2,
                                   p_user_id             IN     VARCHAR2,
                                   p_text_format         IN     VARCHAR2,
                                   p_suppress_zero       IN     VARCHAR2,
                                   x_status                 OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (25) := 'update_template_info';
      l_within_tab       VARCHAR2 (100);
      l_invoice_break    VARCHAR2 (100);
      l_multi_tab        VARCHAR2 (100);
   BEGIN
      x_status := 1;

      l_invoice_break := NVL (p_invoice_break, 'N/A');

      l_multi_tab := NVL (p_multi_tab, 'N/A');

      l_within_tab := NVL (p_within_tab, 'N');



      IF (    (    UPPER (l_invoice_break) <> 'N/A'
               AND UPPER (l_invoice_break) <> 'VIEW')
          AND (l_multi_tab = 'N/A'))
      THEN
         l_within_tab := 'Y';
      ELSE
         l_within_tab := 'N';
      END IF;


      UPDATE CANON_E479_WEB_TEMPL_HEADER
         SET customer_email = p_customer_email,
             other_email = p_other_email,
             invoice_break = p_invoice_break,
             multi_tab = p_multi_tab,
             within_tab = l_within_tab,
             INCLUDE_TXT_FORMAT = p_text_format,
             SUPPRESS_ZERO_INVOICE = p_suppress_zero,
             last_update_date = SYSDATE,
             last_updated_by = p_user_id,
             biller_first_name = p_biller_first_name,
             biller_last_name = p_biller_last_name,
             biller_contact_no = p_biller_contact_no,
             biller_fax_no = p_biller_fax_no,
             biller_email_id = p_biller_email_id
       WHERE header_id = p_template_id;

      COMMIT;
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
   END update_template_info;

   PROCEDURE get_completed_templates (p_search        IN     VARCHAR2,
                                      p_search_type   IN     VARCHAR2,
                                      x_data             OUT RESULT_CURSOR)
   AS
      l_procedure_name   VARCHAR2 (35) := 'get_completed_templates';


      l_search           VARCHAR2 (200);
   BEGIN
      IF (p_search IS NOT NULL AND LENGTH (TRIM (p_search)) > 0)
      THEN
         l_search := l_search || TRIM (p_search) || '%';
      ELSE
         l_search := '%';
      END IF;

      /* S21 Replacement for parent customer/group
         */
      IF (p_search_type = C_SEARCH_PARENT)
      THEN

	      open x_data for
			SELECT DISTINCT
					 NVL (header_id, -1) header_id,
					 NVL (acct.ds_acct_nm, '') parent_acct,
					 '' group_name,
					  '' customer_name,
					 -1 bill_to_use,
					 NVL (templ.other_email, '') other_email,
					 NVL (templ.customer_email, '') customer_email,
					 NVL (templ.status_flag, ' ') status_flag,
					 NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
					 NVL (templ.SUPPRESS_ZERO_INVOICE, 'N') suppress_zero,
					 NVL (templ.template_level, '') template_level
				FROM (SELECT *
						FROM CANON_E479_WEB_TEMPL_HEADER
					   WHERE UPPER (status_flag) = UPPER ('COMPLETE')
					AND template_level = 'PARENT_CUSTOMER') templ,
					 sell_to_cust acct,
					 DS_CUST_INV_RULE acct_setup,
					 cust_bllg_vcle bllg_vcle
			   WHERE     templ.parent_customer_name = acct.ds_acct_nm
					 AND acct.ds_acct_nm LIKE l_search
					 AND acct.ezcancelflag ='0'
					 AND acct.glbl_cmpy_cd ='ADB'
					 AND acct_setup.ezcancelflag ='0'
					 AND acct_setup.glbl_cmpy_cd ='ADB'
					 AND bllg_vcle.ezcancelflag ='0'
					 AND bllg_vcle.glbl_cmpy_cd ='ADB'
					 AND (    acct.sell_to_cust_cd = acct_setup.ds_acct_num
						  AND acct_setup.cust_bllg_vcle_cd =
								 bllg_vcle.cust_bllg_vcle_cd
						  AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
								 ('SPECIAL BILL REVIEW REQD',
								  'SPECIAL BILL NO REVIEW')
						  and acct.sell_to_cust_cd in
								  (select distinct reln.ds_acct_num--acct.ds_accT_num
							  from ds_acct_reln reln --, DS_ACCT_CUST acct
							  --where reln.ds_acct_reln_tp_cd =2
							  where reln.ds_acct_reln_tp_cd =1 -- 3/30/2017. change as per Kohei Aratani
							  and reln.ds_acct_num <> reln.reln_ds_acct_num
							  --and reln.reln_ds_acct_num = acct.ds_acct_num
							  --and reln.ds_acct_num = acct.ds_acct_num
							  AND reln.ezcancelflag ='0'
							  AND reln.glbl_cmpy_cd ='ADB'
							  and DS_ACCT_RELN_BILL_TO_FLG = 'Y'
							  AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt,'yyyymmdd'),SYSDATE - 1)
							  AND NVL (TO_DATE (reln.EFF_thru_DT,'yyyymmdd'),SYSDATE + 1)  ));

	  ELSIF (p_search_type = C_SEARCH_GROUP)
      THEN
	     open x_data for
	     SELECT DISTINCT NVL (header_id, -1) header_id,
							'' parent_acct,
							GRP.ds_acct_grp_nm group_name,
							'' customer_name,
							-1 bill_to_use,
							NVL (templ.other_email, '') other_email,
							NVL (templ.customer_email, '') customer_email,
							NVL (templ.status_flag, ' ') status_flag,
							NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
							NVL (templ.SUPPRESS_ZERO_INVOICE, 'N') suppress_zero,
							'CUSTOMER_GROUP' template_level
						  FROM (SELECT *
								  FROM CANON_E479_WEB_TEMPL_HEADER
								 WHERE  UPPER (status_flag) = UPPER ('COMPLETE')
					               AND template_level = 'CUSTOMER_GROUP') templ,
							   ds_acct_grp_asg grp_assgn,
							   ds_acct_grp grp,
							   sell_to_cust acct
						 WHERE  1=1
						       AND acct.ezcancelflag ='0'
							   AND acct.glbl_cmpy_cd ='ADB'
							   AND grp_assgn.ezcancelflag ='0'
							   AND grp_assgn.glbl_cmpy_cd ='ADB'
							   AND grp.ezcancelflag ='0'
							   AND grp.glbl_cmpy_cd ='ADB'
     						   AND templ.ds_acct_grp_nm = GRP.ds_acct_grp_nm
							   AND grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
							   AND SYSDATE BETWEEN NVL (TO_DATE (grp_assgn.eff_from_dt, 'yyyymmdd'),
														SYSDATE - 1)
											   AND NVL (TO_DATE (grp_assgn.EFF_thru_DT, 'yyyymmdd'),
														SYSDATE + 1)
							   AND acct.sell_to_cust_cd = grp_assgn.DS_ACCT_NUM
							   AND GRP.ds_acct_grp_nm LIKE l_search
							   AND acct.sell_to_cust_cd IN
									  ( /* Bill-to level setup */
									   SELECT loc.ds_acct_num
										 FROM DS_CUST_INV_RULE acct_setup,
											  cust_bllg_vcle bllg_vcle,
											  acct_loc loc,
											  BILL_TO_CUST bill_to
										WHERE 1=1
										      AND loc.ezcancelflag ='0'
											  AND loc.glbl_cmpy_cd ='ADB'
											  AND bill_to.ezcancelflag ='0'
											  AND bill_to.glbl_cmpy_cd ='ADB'
											  AND acct_setup.ezcancelflag ='0'
											  AND acct_setup.glbl_cmpy_cd ='ADB'
											  AND bllg_vcle.ezcancelflag ='0'
											  AND bllg_vcle.glbl_cmpy_cd ='ADB'
  										      AND loc.pty_loc_pk = bill_to.pty_loc_pk
											  AND loc.loc_num = bill_to.loc_num
											  AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD
											  AND bill_to.loc_num = acct_setup.loc_num
											  AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd
											  AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
													 ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
										UNION all
										/* Customer level setup */
										SELECT acct.sell_to_cust_cd
										 FROM DS_CUST_INV_RULE acct_setup,
											  cust_bllg_vcle bllg_vcle,
											  sell_to_cust acct
										WHERE 1=1
										      AND acct.ezcancelflag ='0'
											  AND acct.glbl_cmpy_cd ='ADB'
											  AND acct_setup.ezcancelflag ='0'
											  AND acct_setup.glbl_cmpy_cd ='ADB'
											  AND bllg_vcle.ezcancelflag ='0'
											  AND bllg_vcle.glbl_cmpy_cd ='ADB'
										      AND acct.sell_to_cust_cd =acct_setup.ds_acct_num
											  AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd
											  AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
													 ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
													 );

      ELSIF (p_search_type = C_SEARCH_CUSTOMER)
      THEN
         OPEN x_data FOR
            SELECT DISTINCT
                   NVL (templ.header_id, -1) header_id,
                   '' parent_acct,
                   '' group_name,
                   NVL (acct.ds_acct_nm, '') customer_name,
                   -1 bill_to_use,
                   NVL (templ.other_email, '') other_email,
                   NVL (templ.customer_email, '') customer_email,
                   NVL (templ.status_flag, ' ') status_flag,
                   NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
                   NVL (templ.SUPPRESS_ZERO_INVOICE, 'N') suppress_zero,
                   NVL (templ.template_level, '') template_level
              FROM (SELECT *
                      FROM CANON_E479_WEB_TEMPL_HEADER
                     WHERE     UPPER (status_flag) = UPPER ('COMPLETE')
                           AND template_level = 'CUSTOMER') templ,
                   sell_to_cust acct,
                   DS_CUST_INV_RULE acct_setup,
                   cust_bllg_vcle bllg_vcle
             WHERE   1=1
					AND acct.ezcancelflag ='0'
					AND acct.glbl_cmpy_cd ='ADB'
					AND acct_setup.ezcancelflag ='0'
					AND acct_setup.glbl_cmpy_cd ='ADB'
					AND bllg_vcle.ezcancelflag ='0'
					AND bllg_vcle.glbl_cmpy_cd ='ADB'
					AND templ.customer_name = acct.ds_acct_nm
                    AND acct.ds_acct_nm LIKE l_search
                    AND (   (    --acct.ds_acct_num = acct_setup.ds_acct_num
                             acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   ('SPECIAL BILL REVIEW REQD',
                                    'SPECIAL BILL NO REVIEW'))
                        OR EXISTS(
--                          EXISTS    (
							  SELECT 'Y'
                                 FROM DS_CUST_INV_RULE acct_setup,
                                      cust_bllg_vcle bllg_vcle,
                                      acct_loc loc,
                                      BILL_TO_CUST bill_to
                                WHERE   1=1
										    AND loc.ezcancelflag ='0'
											AND loc.glbl_cmpy_cd ='ADB'
											AND bill_to.ezcancelflag ='0'
											AND bill_to.glbl_cmpy_cd ='ADB'
											AND acct_setup.ezcancelflag ='0'
											AND acct_setup.glbl_cmpy_cd ='ADB'
											AND bllg_vcle.ezcancelflag ='0'
											AND bllg_vcle.glbl_cmpy_cd ='ADB'
											AND   acct.sell_to_cust_cd = loc.ds_acct_num
                                      AND loc.pty_loc_pk = bill_to.pty_loc_pk
                                      AND loc.loc_num = bill_to.loc_num
                                      AND bill_to.BILL_TO_CUST_CD =
                                             acct_setup.BILL_TO_CUST_CD
                                      AND bill_to.loc_num =
                                             acct_setup.loc_num
                                      AND acct_setup.cust_bllg_vcle_cd =
                                             bllg_vcle.cust_bllg_vcle_cd
                                      AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
                                             ('SPECIAL BILL REVIEW REQD',
                                              'SPECIAL BILL NO REVIEW')));
      ELSIF (p_search_type = C_SEARCH_SITE)
      THEN
         OPEN x_data FOR
            SELECT DISTINCT
                   NVL (header_id, -1) header_id,
                   '' parent_acct,
                   '' group_name,
                   NVL (acct.ds_acct_nm, '') customer_name,
                   NVL (bill_to.BILL_TO_CUST_CD, -1) bill_to_use,
                   NVL (templ.other_email, '') other_email,
                   NVL (templ.customer_email, '') customer_email,
                   NVL (templ.status_flag, ' ') status_flag,
                   NVL (templ.INCLUDE_TXT_FORMAT, 'N') include_text,
                   NVL (templ.SUPPRESS_ZERO_INVOICE, 'N') suppress_zero,
                   templ.template_level
              FROM (SELECT *
                      FROM CANON_E479_WEB_TEMPL_HEADER
                     WHERE     UPPER (status_flag) = UPPER ('COMPLETE')
                           AND template_level = 'BILL_TO') templ,
                   sell_to_cust acct,
                   DS_CUST_INV_RULE acct_setup,
                   cust_bllg_vcle bllg_vcle,
                   acct_loc loc,
                   BILL_TO_CUST bill_to
             WHERE  1=1
					AND loc.ezcancelflag ='0'
					AND loc.glbl_cmpy_cd ='ADB'
					AND bill_to.ezcancelflag ='0'
					AND bill_to.glbl_cmpy_cd ='ADB'
					AND acct_setup.ezcancelflag ='0'
					AND acct_setup.glbl_cmpy_cd ='ADB'
					AND bllg_vcle.ezcancelflag ='0'
					AND bllg_vcle.glbl_cmpy_cd ='ADB'
					AND templ.bill_to_location = bill_to.BILL_TO_CUST_CD
					AND bill_to.BILL_TO_CUST_CD LIKE l_search
					AND acct.sell_to_cust_cd = loc.ds_acct_num
					AND loc.pty_loc_pk = bill_to.pty_loc_pk
					AND loc.loc_num = bill_to.loc_num
					AND bill_to.BILL_TO_CUST_CD = acct_setup.BILL_TO_CUST_CD
					AND bill_to.loc_num = acct_setup.loc_num
					AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
					AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN
						  ('SPECIAL BILL REVIEW REQD',
						   'SPECIAL BILL NO REVIEW');
      END IF;
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
            SELECT '1', '1'
              FROM DUAL
             WHERE 1 = -1;
   END get_completed_templates;

   /*
          Scenario 1  Invoice Break N/A
                      Tab Break VIEW
          Result
            With in Tab N   Done
            Multiple Views Allowed Done


          Scenario 2  Invoice Break N/A
                      Tab Break Other than VIEW and N/A
          Result
            Only One View Allowed  Done
            With in Tab N
            Tab Break Column should be mandatory  Pending

          Scenario 3  Invoice Break VIEW
                      Tab Break N/A
          Result
            With in Tab N
            Multiple Views Allowed

          Scenario 4  Invoice Break Other than VIEW  and N/A
                      Tab Break VIEW
          Result
            With in Tab N
            Multiple Views Allowed
            File Break Columns should be Mandatory

          Scenario 5  Invoice Break Other than VIEW  and N/A
                      Tab Break N/A
          Result
            With in Tab Y  Done
            Multiple Views Allowed
            File Break Columns should be Mandatory


         Scenario 6 Both Invoice/Break and Tab Break as N/A are
                    not allowed
                    Just added  on July 29,2012

          Scenario 7 No more than one subtotal





     */

   PROCEDURE is_ready_to_mark_completed (p_header_id   IN     NUMBER,
                                         x_status         OUT VARCHAR2,
                                         x_message        OUT VARCHAR2)
   AS
      l_procedure_name           VARCHAR2 (200) := 'is_ready_to_mark_completed';
      l_biller_last_name         VARCHAR2 (100);
      l_biller_first_name        VARCHAR2 (100);
      l_biller_phone_number      VARCHAR2 (100);
      l_biller_fax_number        VARCHAR2 (100);
      l_biller_email_address     VARCHAR2 (100);
      l_emp_number               VARCHAR2 (100);

      l_profile_name             VARCHAR2 (200);
      l_customer_name               VARCHAR2 (200);
      l_bill_location            VARCHAR2 (200);

      l_valid_status             VARCHAR2 (200);
      l_valid_message            VARCHAR2 (2000);

      l_view_count_flag          VARCHAR2 (100) := 'N';



      l_col_count                NUMBER;
      l_multi_tab                VARCHAR2 (100);
      l_invoice_break            VARCHAR2 (100);

      l_customer_email_address   VARCHAR2 (200);
      l_view_count               NUMBER;

      l_subtotal_count           NUMBER;

      l_sum_count                NUMBER;
   BEGIN
      x_message := ' ';
      x_status := 'N';



      CANON_E479_TEMPLATE_UTIL_PKG.get_biller_detail (p_header_id,
                                                      l_biller_last_name,
                                                      l_biller_first_name,
                                                      l_biller_phone_number,
                                                      l_biller_fax_number,
                                                      l_biller_email_address,
                                                      l_emp_number);


      IF (l_biller_email_address IS NULL)
      THEN
         x_status := 'Y';
         x_message := append_message (x_message, 'Biller Email Address is  missing');
      END IF;

	  IF ( l_biller_first_name IS NULL)
      THEN
         x_status := 'Y';
         x_message := append_message (x_message, 'Biller First Name is missing');
      END IF;


      SELECT parent_customer_name,
             customer_name,
             bill_to_location,
             TRIM (customer_email),
             NVL (invoice_break, 'N/A'),
             NVL (multi_tab, 'N/A')
        INTO l_profile_name,
             l_customer_name,
             l_bill_location,
             l_customer_email_address,
             l_invoice_break,
             l_multi_tab
        FROM CANON_E479_WEB_TEMPL_HEADER
       WHERE header_id = p_header_id;


      IF (   (l_customer_email_address IS NULL)
          OR (    l_customer_email_address IS NOT NULL
              AND LENGTH (TRIM (l_customer_email_address)) <= 0))
      THEN
         x_status := 'Y';
         x_message :=
            append_message (x_message, 'Customer email address is missing');
      END IF;

      /* uncomment after adding the procedure
         validate_template_record(l_profile_name,
                                 l_customer_name,
                                 l_bill_location ,
                                 p_header_id ,
                                 l_valid_status ,
                                 l_valid_message);
      */
      IF (l_valid_status IS NOT NULL AND l_valid_status = 'Y')
      THEN
         x_status := 'Y';

         x_message := x_message || '<p>' || NVL (l_valid_message, ' ');
      END IF;

      CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                               l_procedure_name,
                                               'SQL',
                                               p_header_id,
                                               NULL,
                                               NULL,
                                               NULL,
                                               NULL,
                                               NULL,
                                               l_valid_status);


      SELECT DECODE (COUNT (*), 0, 'Y', 'N')
        INTO l_view_count_flag
        FROM CANON_E479_WEB_TEMPL_VIEW
       WHERE header_id = p_header_id;

      IF (l_view_count_flag = 'Y')
      THEN
         x_status := 'Y';
         x_message := append_message (x_message, 'No Views are attached');
      END IF;

      FOR viewRec IN (  SELECT *
                          FROM CANON_E479_WEB_TEMPL_VIEW
                         WHERE header_id = P_HEADER_ID
                      ORDER BY view_sequence)
      LOOP
         l_subtotal_count := 0;

         l_sum_count := 0;

         SELECT COUNT (*)
           INTO l_col_count
           FROM CANON_E479_WEB_TEMPL_COLS
          WHERE HEADER_ID = P_HEADER_ID AND VIEW_ID = viewRec.view_id;

         IF (l_col_count <= 0)
         THEN
            x_status := 'Y';
            x_message := append_message (x_message,'No columns are added in view ' || viewRec.view_name);
			x_message := append_message (x_message,'No columns are added in view ' || l_col_count);
         END IF;

         SELECT COUNT (*)
           INTO l_subtotal_count
           FROM CANON_E479_WEB_TEMPL_COLS
          WHERE     VIEW_ID = viewRec.view_id
                AND TRIM (NVL (AGGREGATE_BY, '-1')) = 'SUBTOTAL';

         SELECT COUNT (*)
           INTO l_sum_count
           FROM CANON_E479_WEB_TEMPL_COLS
          WHERE     VIEW_ID = viewRec.view_id
                AND TRIM (NVL (AGGREGATE_BY, '-1')) = 'SUM';

         IF (l_subtotal_count > 1)
         THEN
            x_status := 'Y';
            x_message :=
               append_message (
                  x_message,
                     'More than one SUBTOTAL cannot be selected in view  '
                  || viewRec.view_name);
         END IF;

         IF ( (l_subtotal_count = 1) AND (l_sum_count = 0))
         THEN
            x_status := 'Y';
            x_message :=
               append_message (
                  x_message,
                     'Atleast one or more columns should have SUM if you selected SUBTOTAL in view '
                  || viewRec.view_name);
         END IF;
      END LOOP;



      IF (    (l_invoice_break = 'N/A')
          AND (UPPER (l_multi_tab) NOT IN ('N/A', 'VIEW')))
      THEN
         SELECT COUNT (*)
           INTO l_view_count
           FROM CANON_E479_WEB_TEMPL_VIEW
          WHERE header_id = p_header_id;


         IF (l_view_count > 1)
         THEN
            x_status := 'Y';
            x_message :=
               append_message (
                  x_message,
                  'Only One View is allowed for this combination');
         END IF;
      END IF;


      IF (    (UPPER (l_multi_tab) = 'N/A')
          AND (UPPER (l_invoice_break) = 'N/A'))
      THEN
         x_status := 'Y';
         x_message :=
            append_message (
               x_message,
               'Both File Break  and Tab Break   cannont be N/A');
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := 'Y';
         x_message := SUBSTR (SQLERRM, 1, 255);
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_header_id,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END is_ready_to_mark_completed;

   PROCEDURE validate_template_record (p_profile_name    IN     VARCHAR2,
                                       p_party_name      IN     VARCHAR2,
                                       p_site_location   IN     VARCHAR2,
                                       p_template_id     IN     NUMBER,
                                       x_status             OUT VARCHAR2,
                                       x_messge             OUT VARCHAR2)
   AS
      l_procedure_name      VARCHAR2 (30) := 'validate_template_record';
      l_customer_name          VARCHAR2 (500);
      l_party_search_flag   VARCHAR2 (10);
      l_cust_account_id     NUMBER;
      l_profile_flag        VARCHAR2 (10);
      l_profile_name        VARCHAR2 (200);
      l_count               NUMBER := 0;
   BEGIN
      x_status := 'N';
      x_messge := ' ';

      /* S21 replacement
      if (p_site_location is not null and to_number(p_site_location) >0) then

           select party_name, hca.cust_account_id  into l_customer_name, l_cust_account_id
           from
           hz_parties hp,
           hz_cust_accounts hca,
           hz_cust_acct_sites_all hcas,
           hz_cust_site_uses_all hcsua
           WHERE hp.party_id = hca.party_id
           and hca.cust_account_id  = hcas.cust_account_id
           and hcsua.cust_acct_site_id = hcas.cust_acct_site_id
           AND hcsua.site_use_code = C_SITE_BILL_TO
           and location = p_site_location;

           l_party_search_flag :=  is_customer_exist_in_template(l_customer_name, p_template_id);

           if(l_party_search_flag = 'Y') then

              x_status := 'Y';
              x_messge := append_message(x_messge, ' Template for  party '||
                               l_customer_name || ' already exists for  this Bill Location ' || p_site_location);



           end if;

           select decode(count(*),0,'N','Y')  into l_profile_flag
           from
            canon_e490_cust_prof_main_tbl a,
            canon_e490_cust_prof_asign_tbl b,
            CANON_E479_WEB_TEMPL_HEADER headerTbl
            where b.cust_account_id = l_cust_account_id
            and b.profile_id = a.profile_id
            and  TRUNC(SYSDATE) BETWEEN TRUNC(NVL(b.start_date,SYSDATE))
            and TRUNC (NVL (b.end_date, SYSDATE))
            and TRUNC (SYSDATE) BETWEEN TRUNC  (NVL (a.start_date_active, SYSDATE))
            and  TRUNC (NVL (a.end_date_active, SYSDATE))
            and a.profile_name  =  headerTbl.account_name
            and upper(headerTbl.status_flag) = Upper('COMPLETE')
            and headerTbl.header_id <> p_template_id;


            if(l_profile_flag = 'Y') then

                 begin

                      select a.profile_name into l_profile_name
                      from
                      canon_e490_cust_prof_main_tbl a,
                      canon_e490_cust_prof_asign_tbl b,
                      CANON_E479_WEB_TEMPL_HEADER headerTbl
                      where b.cust_account_id = l_cust_account_id
                      and b.profile_id = a.profile_id
                      and  TRUNC(SYSDATE) BETWEEN TRUNC(NVL(b.start_date,SYSDATE))
                      and TRUNC (NVL (b.end_date, SYSDATE))
                      and TRUNC (SYSDATE) BETWEEN TRUNC  (NVL (a.start_date_active, SYSDATE))
                      and  TRUNC (NVL (a.end_date_active, SYSDATE))
                      and a.profile_name  =  headerTbl.account_name
                      and headerTbl.header_id <> p_template_id
                      and upper(headerTbl.status_flag) = Upper('COMPLETE')
                      and rownum < 2;

                 exception
                 when others then
                     l_profile_name := '';
                 end;

                 x_status := 'Y';
                 x_messge := append_message(x_messge, 'Customer Profile '||
                               l_profile_name || ' already exists for  this Bill Location  ' || p_site_location);
            end if;

      elsif((p_site_location is null or nvl(to_number(trim(p_site_location)),-1) < 0 )
                      and (p_party_name is not null)) then

                l_party_search_flag :=  is_customer_exist_in_template(p_party_name,p_template_id);

                if(l_party_search_flag = 'Y') then

                     x_status := 'Y';
                     x_messge := append_message(x_messge, ' Template for  party '||
                               l_customer_name || ' already exists ');

                end if;

                l_profile_name := null;

                begin
                     select profile_name into l_profile_name
                     from hz_parties hp,
                     hz_cust_accounts hca,
                     CANON_E479_WEB_TEMPL_HEADER headerTbl,
                     canon_e490_cust_prof_main_tbl a,
                     canon_e490_cust_prof_asign_tbl b
                     where
                     hp.party_id = hca.party_id
                     and hca.status = 'A'
                     and hp.party_name = p_party_name
                     and hca.cust_account_id = b.cust_account_id
                     and b.profile_id = a.profile_id
                     and  TRUNC(SYSDATE) BETWEEN TRUNC(NVL(b.start_date,SYSDATE))
                     and TRUNC (NVL (b.end_date, SYSDATE))
                     and TRUNC (SYSDATE) BETWEEN TRUNC  (NVL (a.start_date_active, SYSDATE))
                     and  TRUNC (NVL (a.end_date_active, SYSDATE))
                     and a.profile_name  =  headerTbl.account_name
                     and upper(headerTbl.status_flag) = Upper('COMPLETE')
                     and headerTbl.header_id <> p_template_id
                     and rownum < 2;

                exception
                when others then
                  l_profile_name := null;

                end ;

                if(l_profile_name is not null) then
                     x_status := 'Y';
                     x_messge := append_message(x_messge, 'Customer Profile '||
                               l_profile_name || ' already exists for  this party ' || p_party_name);

                end if;


      elsif(p_profile_name is not null) then

                l_count := 0;

                /*
                select count(*) into l_count from
                CANON_E479_WEB_TEMPL_HEADER headerTbl
                where   headerTbl.header_id <> p_template_id
                and upper(headerTbl.status_flag) = Upper('COMPLETE')
                and  exists(select 1 from hz_parties hp,
                            hz_cust_accounts hca,
                            canon_e490_cust_prof_main_tbl a,
                            canon_e490_cust_prof_asign_tbl b
                            where hp.party_id = hca.party_id
                            and hca.cust_account_id = b.cust_account_id
                            and b.profile_id = a.profile_id
                            and  TRUNC(SYSDATE) BETWEEN TRUNC(NVL(b.start_date,SYSDATE))
                            and TRUNC (NVL (b.end_date, SYSDATE))
                            and TRUNC (SYSDATE) BETWEEN TRUNC  (NVL (a.start_date_active, SYSDATE))
                            and  TRUNC (NVL (a.end_date_active, SYSDATE))
                            and headerTbl.party_name = hp.party_name
                           )  ;


                 select count(*) into l_count from
                CANON_E479_WEB_TEMPL_HEADER headerTbl
                where   headerTbl.header_id <> p_template_id
                and upper(headerTbl.status_flag) = Upper('COMPLETE')
                and headerTbl.party_name  is not null
                and  headerTbl.party_name  in
                (select hp.party_name from hz_parties hp,
                            hz_cust_accounts hca,
                            canon_e490_cust_prof_main_tbl a,
                            canon_e490_cust_prof_asign_tbl b
                            where hp.party_id = hca.party_id
                            and hca.cust_account_id = b.cust_account_id
                            and b.profile_id = a.profile_id
                            and  TRUNC(SYSDATE) BETWEEN TRUNC(NVL(b.start_date,SYSDATE))
                            and TRUNC (NVL (b.end_date, SYSDATE))
                            and TRUNC (SYSDATE) BETWEEN TRUNC  (NVL (a.start_date_active, SYSDATE))
                            and  TRUNC (NVL (a.end_date_active, SYSDATE))
                            and a.profile_name = p_profile_name
                );

                if(l_count >0 ) then
                     x_status := 'Y';
                     x_messge := append_message(x_messge, 'Template  already  setup for the Customers '||
                                                        ' in this profile ');

                end if;


                l_count := 0;


                select count(*) into l_count from
                CANON_E479_WEB_TEMPL_HEADER headerTbl
                where   headerTbl.header_id <> p_template_id
                and upper(headerTbl.status_flag) = Upper('COMPLETE')
                and  exists(select 1 from hz_parties hp,
                            hz_cust_accounts hca,
                            hz_cust_acct_sites_all hcas,
                            hz_cust_site_uses_all hcsua,
                            canon_e490_cust_prof_main_tbl a,
                            canon_e490_cust_prof_asign_tbl b
                            where hp.party_id = hca.party_id
                            and hca.cust_account_id = b.cust_account_id
                            and hcas.cust_account_id = hca.cust_account_id
                            and hcsua.cust_acct_site_id = hcas.cust_acct_site_id
                            and hcsua.site_use_code = C_SITE_BILL_TO
                            and hcsua.attribute15   in
                               (C_CSTM_INVOICE,C_CSTM_INVOICE_AUT_YES,C_CSTM_INVOICE_AUT_NO)
                            and b.profile_id = a.profile_id
                            and  TRUNC(SYSDATE) BETWEEN TRUNC(NVL(b.start_date,SYSDATE))
                            and TRUNC (NVL (b.end_date, SYSDATE))
                            and TRUNC (SYSDATE) BETWEEN TRUNC  (NVL (a.start_date_active, SYSDATE))
                            and  TRUNC (NVL (a.end_date_active, SYSDATE))
                            and headerTbl.bill_to_location  = hcsua.location
                           ) ;


                select count(*) into l_count from
                CANON_E479_WEB_TEMPL_HEADER headerTbl
                where   headerTbl.header_id <> p_template_id
                and upper(headerTbl.status_flag) = Upper('COMPLETE')
                and headerTbl.bill_to_location is not null
                and headerTbl.bill_to_location in
                (select  hcsua.location  from hz_parties hp,
                            hz_cust_accounts hca,
                            hz_cust_acct_sites_all hcas,
                            hz_cust_site_uses_all hcsua,
                            canon_e490_cust_prof_main_tbl a,
                            canon_e490_cust_prof_asign_tbl b
                            where hp.party_id = hca.party_id
                            and hca.cust_account_id = b.cust_account_id
                            and hcas.cust_account_id = hca.cust_account_id
                            and hcsua.cust_acct_site_id = hcas.cust_acct_site_id
                            and hcsua.site_use_code = C_SITE_BILL_TO
                            and hcsua.attribute15   in
                               (C_CSTM_INVOICE,C_CSTM_INVOICE_AUT_YES,C_CSTM_INVOICE_AUT_NO)
                            and b.profile_id = a.profile_id
                            and  TRUNC(SYSDATE) BETWEEN TRUNC(NVL(b.start_date,SYSDATE))
                            and TRUNC (NVL (b.end_date, SYSDATE))
                            and TRUNC (SYSDATE) BETWEEN TRUNC  (NVL (a.start_date_active, SYSDATE))
                            and  TRUNC (NVL (a.end_date_active, SYSDATE))
                            and a.profile_name = p_profile_name
                ) ;

                if(l_count >0 ) then
                     x_status := 'Y';
                     x_messge := append_message(x_messge, 'Template  already  setup for Bill to Sites for Customers '||
                                ' in this profile ');

                end if;
      end if;

    */

      IF (x_status = 'N')
      THEN
         x_messge := 'You can successfully create/update the template ';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := 'Y';
         x_messge :=
            'Error in validation, please contact system administrator';
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_profile_name,
                                                  p_party_name,
                                                  p_site_location,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END validate_template_record;

   PROCEDURE update_for_comp_incomp (p_header_id   IN     NUMBER,
                                     p_status      IN     VARCHAR2,
                                     x_message        OUT VARCHAR2,
                                     x_status         OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (100) := 'update_for_comp_incomp';
      l_valid_status     VARCHAR2 (100);
   BEGIN
      x_status := 1;

      IF (UPPER (p_status) = 'COMPLETE')
      THEN
         is_ready_to_mark_completed (p_header_id, l_valid_status, x_message);

         IF (l_valid_status = 'Y')
         THEN
            x_status := -1;
         ELSIF (l_valid_status = 'N')
         THEN
            UPDATE CANON_E479_WEB_TEMPL_HEADER
               SET STATUS_FLAG = p_status
             WHERE header_id = p_header_id;

            COMMIT;
         END IF;
      ELSE
         UPDATE CANON_E479_WEB_TEMPL_HEADER
            SET STATUS_FLAG = p_status
          WHERE header_id = p_header_id;

         COMMIT;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;
         x_message := SUBSTR (SQLERRM, 1, 255);
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_header_id,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END update_for_comp_incomp;

   PROCEDURE get_bill_to_sites (p_bill_location   IN     VARCHAR2,
                                p_customer_name   IN     VARCHAR2,
                                p_start_range     IN     NUMBER,
                                p_end_range       IN     NUMBER,
                                p_sort_by         IN     VARCHAR2,
                                p_sort_type       IN     VARCHAR2,
                                x_data               OUT result_cursor,
                                x_count              OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_bill_to_sites';
      l_sql_common       VARCHAR2 (3999) := ' ';
      l_sql_qry          VARCHAR2 (3999) := ' ';
      l_sql_count_qry    VARCHAR2 (3999) := ' ';
      l_sort_by          VARCHAR2 (200) := ' ';
      l_sort_type        VARCHAR2 (200) := ' ';
   BEGIN
      l_sql_qry :=
            l_sql_qry
         || 'select distinct acct.ds_acct_nm customer_name, '
		 || ' bill_to.BILL_TO_CUST_CD location , '
		 || ' bill_to.FIRST_LINE_ADDR address1, '
		 || ' bill_to.SCD_LINE_ADDR address2, '
		 || ' bill_to.CTY_ADDR city, '
		 || ' bill_to.ST_CD state, '
		 || ' bill_to.POST_CD postal_code ';
      l_sql_common :=
			l_sql_common
			|| ' FROM '
			|| ' sell_to_cust ACCT, '
			|| ' DS_CUST_INV_RULE ACCT_SETUP, '
			|| ' CUST_BLLG_VCLE BLLG_VCLE, '
			|| ' ACCT_LOC LOC, '
			|| ' BILL_TO_CUST BILL_TO '
			|| ' WHERE 1=1 '
			||' AND bllg_vcle.ezcancelflag =''0'' '
			||' AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
			||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
			||' AND acct_setup.ezcancelflag = BILL_TO.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = BILL_TO.glbl_cmpy_cd '
			||' AND bill_to.ezcancelflag = loc.ezcancelflag '
			||' AND bill_to.glbl_cmpy_cd = loc.glbl_cmpy_cd '
			||' AND ACCT.glbl_cmpy_cd = loc.glbl_cmpy_cd '
			||' AND ACCT.ezcancelflag = loc.ezcancelflag '
			|| ' AND ACCT.sell_to_cust_cd = LOC.DS_ACCT_NUM '
			|| ' AND LOC.PTY_LOC_PK = BILL_TO.PTY_LOC_PK '
			|| ' AND LOC.LOC_NUM = BILL_TO.LOC_NUM '
			|| ' AND BILL_TO.BILL_TO_CUST_CD = ACCT_SETUP.BILL_TO_CUST_CD '
			|| ' AND BILL_TO.LOC_NUM = ACCT_SETUP.LOC_NUM '
			|| ' AND ACCT_SETUP.CUST_BLLG_VCLE_CD = '
			|| ' BLLG_VCLE.CUST_BLLG_VCLE_CD '
			|| ' AND UPPER(BLLG_VCLE.CUST_BLLG_VCLE_NM) IN '
			|| ' (''SPECIAL BILL REVIEW REQD'', '
			|| ' ''SPECIAL BILL NO REVIEW'') ';


      IF (p_bill_location IS NOT NULL AND LENGTH (TRIM (p_bill_location)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and bill_to.BILL_TO_CUST_CD = '''
            || TRIM (p_bill_location)
            || '''';
      END IF;

      IF (p_customer_name IS NOT NULL AND LENGTH (TRIM (p_customer_name)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and  acct.ds_acct_nm like '''
            || TRIM (UPPER (p_customer_name))
            || '%''';
      END IF;



      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
         THEN
            l_sort_by := 'acct.ds_acct_nm';
         ELSIF (UPPER (p_sort_by) = 'LOCATION')
         THEN
            l_sort_by := 'bill_to.bill_to_cust_cd';
         ELSIF (UPPER (p_sort_by) = 'ADDRESS1')
         THEN
            l_sort_by := 'bill_to.FIRST_LINE_ADDR';
         END IF;
      ELSE
         l_sort_by := 'acct.ds_acct_nm';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;

      l_sql_count_qry := 'select count(*) from ('||    l_sql_qry        || l_sql_common||')';


      l_sql_qry :=
            'SELECT customer_name, location, address1, address2,city,state,postal_code'
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

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);
      dbms_output.put_line(' Count Query: '||l_sql_count_qry );
	  dbms_output.put_line(' SQL Query to be executed: '||l_sql_qry );

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN x_data FOR l_sql_qry;

   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_bill_location,
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
   END get_bill_to_sites;

   PROCEDURE get_customer_names (p_customer_name   IN     VARCHAR2,
                                 p_start_range     IN     NUMBER,
                                 p_end_range       IN     NUMBER,
                                 p_sort_by         IN     VARCHAR2,
                                 p_sort_type       IN     VARCHAR2,
                                 x_data               OUT result_cursor,
                                 x_count              OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_customer_names';
      l_sql_common       VARCHAR2 (3999) := ' ';
      l_sql_qry          VARCHAR2 (3999) := ' ';
      l_sql_count_qry    VARCHAR2 (3999) := ' ';
      l_sort_by          VARCHAR2 (200) := ' ';
      l_sort_type        VARCHAR2 (200) := ' ';
   BEGIN

      l_sql_qry :=
            'SELECT acct.DS_ACCT_NM customer_name, '
         || ' acct.sell_to_cust_cd customer_number';
      /* Added union to check on the bill-to site as well. */
      l_sql_common := ' FROM sell_to_cust acct where 1=1  '
		||' AND acct.ezcancelflag =''0'' '
		||' AND acct.glbl_cmpy_cd =''ADB'' '
		|| ' AND acct.sell_to_cust_cd IN  '
		||' (SELECT loc.ds_acct_num  '
		||'FROM DS_CUST_INV_RULE acct_setup, '
		||'cust_bllg_vcle bllg_vcle, '
		||'acct_loc loc, '
		||'BILL_TO_CUST bill_to '
		||'WHERE  1=1 '
		||' AND bllg_vcle.ezcancelflag =''0'' '
		||' AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
		||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
		||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
		||' AND acct_setup.ezcancelflag = BILL_TO.ezcancelflag '
		||' AND acct_setup.glbl_cmpy_cd = BILL_TO.glbl_cmpy_cd '
		||' AND bill_to.ezcancelflag = loc.ezcancelflag '
		||' AND bill_to.glbl_cmpy_cd = loc.glbl_cmpy_cd '
		|| ' AND loc.pty_loc_pk = bill_to.pty_loc_pk '
		||' AND loc.loc_num = bill_to.loc_num '
		||' AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD '
		||' AND bill_to.loc_num =acct_setup.loc_num '
		||' AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd '
		||' AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN'
		||' (''SPECIAL BILL REVIEW REQD'', '
		||'	''SPECIAL BILL NO REVIEW'') '
		|| ' UNION ALL  '
		|| ' SELECT acct_setup.ds_acct_num  '
		||'FROM DS_CUST_INV_RULE acct_setup, '
		||'cust_bllg_vcle bllg_vcle '
		||'WHERE  1=1 '
		||' AND bllg_vcle.ezcancelflag =''0'' '
		||' AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
		||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
		||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
		||' AND acct_setup.ds_acct_num IS NOT NULL '
		||' AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd '
		||' AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN'
		||' (''SPECIAL BILL REVIEW REQD'', '
		||'	''SPECIAL BILL NO REVIEW'') '
		|| ' )';




      IF (p_customer_name IS NOT NULL AND LENGTH (TRIM (p_customer_name)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and acct.DS_ACCT_NM like '''
            || TRIM (UPPER (p_customer_name))
            || '%''';
      END IF;


      l_sort_by := 'acct.ds_acct_nm';
      l_sort_type := 'asc';

      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
         THEN
            l_sort_by := 'acct.DS_ACCT_NM';
         ELSIF (UPPER (p_sort_by) = 'CUSTOMER_number')
         THEN
            l_sort_by := 'acct.sell_to_cust_cd';
         END IF;
      ELSE
         l_sort_by := 'acct.DS_ACCT_NM';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;

      l_sql_count_qry :=
         ' select count(*) from (' || l_sql_qry || ' ' || l_sql_common || ')';

      l_sql_qry :=
            'SELECT customer_name, customer_number '
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

      DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN x_data FOR l_sql_qry;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_customer_name,
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
   END get_customer_names;

   FUNCTION is_customer_exist_in_template (p_customer_name   IN VARCHAR2,
                                           p_template_id     IN NUMBER)
      RETURN VARCHAR2
   AS
      l_flag             VARCHAR2 (20);
      l_procedure_name   VARCHAR2 (40) := 'is_customer_exist_in_template';
   BEGIN
      SELECT DECODE (COUNT (*), 0, 'N', 'Y')
        INTO l_flag
        FROM CANON_E479_WEB_TEMPL_HEADER
       WHERE     customer_name = p_customer_name
             AND bill_to_location IS NULL
             AND UPPER (status_flag) = UPPER ('COMPLETE')
             AND header_id <> NVL (p_template_id, -1);

      RETURN l_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_flag := 'Y';

         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_customer_name,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         RETURN l_flag;
   END is_customer_exist_in_template;

    PROCEDURE get_parent_customer
                                    (p_parent_customer  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     p_sort_by        IN     VARCHAR2,
                                     p_sort_type      IN     VARCHAR2,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_parent_customer';
      l_sql_common       VARCHAR2 (3999) := ' ';
      l_sql_qry          VARCHAR2 (3999) := ' ';
      l_sql_count_qry    VARCHAR2 (3999) := ' ';
      l_sort_by          VARCHAR2 (200) := ' ';
      l_sort_type        VARCHAR2 (200) := ' ';
   BEGIN
      /* S21 REPLACEMENT */
       l_sql_qry :=
         'SELECT DISTINCT '
			||'acct.ds_acct_nm customer_name, acct.sell_to_cust_cd customer_number '
			||'FROM ds_acct_reln reln, sell_to_cust acct '
			--||'WHERE     reln.ds_acct_reln_tp_cd = 2 '
			 -- 3/30/2017. change as per Kohei Aratani
			||'WHERE     reln.ds_acct_reln_tp_cd = 1 '
			||'AND reln.ds_acct_num <> reln.reln_ds_acct_num '
			--||'AND reln.reln_ds_acct_num = acct.ds_acct_num '
			||'AND reln.ds_acct_num = acct.sell_to_cust_cd '
			||' and DS_ACCT_RELN_BILL_TO_FLG = ''Y'''
			||' AND reln.ezcancelflag =''0'' '
			||' AND reln.glbl_cmpy_cd =''ADB'' '
			||' AND acct.ezcancelflag =''0'' '
			||' AND acct.glbl_cmpy_cd =''ADB'' '
			|| '  AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt,''yyyymmdd''),SYSDATE - 1) '
			|| '  AND NVL (TO_DATE (reln.EFF_thru_DT,''yyyymmdd''),SYSDATE + 1)  '
			||'AND reln.ds_acct_num IN '
			||'(                                      /* Bill-to level setup */ '
			||'SELECT loc.ds_acct_num '
			||'FROM DS_CUST_INV_RULE acct_setup, '
			||'cust_bllg_vcle bllg_vcle, '
			||'acct_loc loc ,'
			||'BILL_TO_CUST bill_to '
			||'WHERE 1=1'
			||'AND loc.pty_loc_pk = bill_to.pty_loc_pk '
			||'AND loc.loc_num = bill_to.loc_num '
			||'AND bill_to.BILL_TO_CUST_CD = '
			||'acct_setup.BILL_TO_CUST_CD '
			||'AND bill_to.loc_num = acct_setup.loc_num '
			||'AND acct_setup.cust_bllg_vcle_cd = '
			||'bllg_vcle.cust_bllg_vcle_cd '
			||'AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN '
			||'(''SPECIAL BILL REVIEW REQD'' , '
			||'''SPECIAL BILL NO REVIEW'') '
			||' AND bllg_vcle.ezcancelflag =''0'' '
			||' AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
			||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
			||' AND acct_setup.ezcancelflag = loc.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = loc.glbl_cmpy_cd '
			||' AND bill_to.ezcancelflag = loc.ezcancelflag '
			||' AND bill_to.glbl_cmpy_cd = loc.glbl_cmpy_cd '
			||'UNION ALL '
			||'/* Customer level setup */ '
			||'SELECT acct.sell_to_cust_cd '
			||'FROM DS_CUST_INV_RULE acct_setup, '
			||'cust_bllg_vcle bllg_vcle, '
			||'sell_to_cust acct '
			||'WHERE  1=1'
			||' AND bllg_vcle.ezcancelflag =''0'' '
			||' AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
			||' AND acct.sell_to_cust_cd = acct_setup.ds_acct_num '
			||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
			||' AND acct_setup.ezcancelflag = acct.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = acct.glbl_cmpy_cd '
			||'AND acct_setup.cust_bllg_vcle_cd = '
			||'bllg_vcle.cust_bllg_vcle_cd '
			||'AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN '
			||'(''SPECIAL BILL REVIEW REQD'', '
			||'''SPECIAL BILL NO REVIEW'')) ';

      IF (p_parent_customer IS NOT NULL AND LENGTH (TRIM (p_parent_customer)) > 0)
      THEN
         l_sql_qry :=
               l_sql_qry
            || '  and acct.DS_ACCT_NM like '''
            || TRIM (UPPER (p_parent_customer))
            || '%''';
      END IF;


      l_sort_by := 'acct.ds_acct_nm';
      l_sort_type := 'asc';

      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
         THEN
            l_sort_by := 'acct.DS_ACCT_NM';
         ELSIF (UPPER (p_sort_by) = 'CUSTOMER_number')
         THEN
            l_sort_by := 'acct.sell_to_cust_cd';
         END IF;
      ELSE
         l_sort_by := 'acct.DS_ACCT_NM';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;

      l_sql_count_qry :=
         ' select count(*) from (' || l_sql_qry || ')';

      l_sql_qry :=
            'SELECT customer_name, customer_number '
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

      DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN x_data FOR l_sql_qry;


   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_parent_customer,
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
   END get_parent_customer;

   PROCEDURE get_customer_group
                                    (p_customer_group  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER)
IS
  l_procedure_name   VARCHAR2 (30) := 'get_customer_group';
      l_sql_common       VARCHAR2 (3999) := ' ';
      l_sql_qry          VARCHAR2 (3999) := ' ';
      l_sql_count_qry    VARCHAR2 (3999) := ' ';
      l_sort_by          VARCHAR2 (200) := ' ';
      l_sort_type        VARCHAR2 (200) := ' ';
BEGIN

		   l_sql_qry :=
		    'select result.* from ('
            ||'SELECT DISTINCT GRP.ds_acct_grp_nm group_name '
				||'FROM '
				||'ds_acct_grp_asg grp_assgn, '
				||'ds_acct_grp grp, '
				||'sell_to_cust acct '
				||'WHERE 1=1 '
				||' AND grp.ezcancelflag =''0'' '
				||' AND grp.glbl_cmpy_cd =''ADB'' '
				||' AND grp_assgn.ezcancelflag =''0'' '
				||' AND grp_assgn.glbl_cmpy_cd =''ADB'' '
				||' AND grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd '
				||'AND SYSDATE BETWEEN NVL (TO_DATE (grp_assgn.eff_from_dt, ''yyyymmdd''), '
				||'SYSDATE - 1) '
				||'AND NVL (TO_DATE (grp_assgn.EFF_thru_DT, ''yyyymmdd''), '
				||'SYSDATE + 1) '
				||'AND acct.sell_to_cust_cd = grp_assgn.DS_ACCT_NUM '
				||'AND acct.sell_to_cust_cd IN '
				||'(SELECT /* Bill-to location level */ '
				|| ' loc.ds_acct_num '
				||'FROM DS_CUST_INV_RULE acct_setup, '
				||'cust_bllg_vcle bllg_vcle, '
				||'acct_loc loc, '
				||'BILL_TO_CUST bill_to '
				||'WHERE  1=1'
				||' AND loc.ezcancelflag =''0'' '
				||' AND loc.glbl_cmpy_cd =''ADB'' '
				||' AND bill_to.ezcancelflag =''0'' '
				||' AND bill_to.glbl_cmpy_cd =''ADB'' '
				||' AND acct_setup.ezcancelflag =''0'' '
				||' AND acct_setup.glbl_cmpy_cd =''ADB'' '
				||' AND bllg_vcle.ezcancelflag =''0'' '
				||' AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
				||'AND loc.pty_loc_pk = bill_to.pty_loc_pk '
				||'AND loc.loc_num = bill_to.loc_num '
				||'AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD '
				||'AND bill_to.loc_num = acct_setup.loc_num '
				||'AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd '
				||'AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN '
				||'(''SPECIAL BILL REVIEW REQD'',''SPECIAL BILL NO REVIEW'') '
				||' UNION all '
				||' /* Customer level setup */ '
				||' SELECT acct.sell_to_cust_cd '
				||'  FROM DS_CUST_INV_RULE acct_setup, '
				||' 	  cust_bllg_vcle bllg_vcle, '
				||' 	  sell_to_cust acct '
				||' WHERE 1=1 '
				||' 	  AND acct.ezcancelflag =''0'' '
				||' 	  AND acct.glbl_cmpy_cd =''ADB'' '
				||' 	  AND acct_setup.ezcancelflag =''0'' '
				||' 	  AND acct_setup.glbl_cmpy_cd =''ADB'' '
				||' 	  AND bllg_vcle.ezcancelflag =''0'' '
				||' 	  AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
				||' 	  AND acct.sell_to_cust_cd =acct_setup.ds_acct_num '
				||' 	  AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd '
				||' 	  AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN '
				||' 			 (''SPECIAL BILL REVIEW REQD'',''SPECIAL BILL NO REVIEW'') '
				||')) result  where 1=1 ' ;


      IF (p_customer_group IS NOT NULL AND LENGTH (TRIM (p_customer_group)) > 0)
      THEN
         l_sql_qry :=
               l_sql_qry
            || '  and group_name like '''
            || TRIM (UPPER (p_customer_group))
            || '%''';
      END IF;


      l_sort_by := 'group_name';
      l_sort_type := 'asc';

      l_sql_count_qry :=
         ' select count(*) from (' || l_sql_qry ||')';

      l_sql_qry :=
            'SELECT group_name '
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;

      DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (l_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN x_data FOR l_sql_qry;
EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_customer_group,
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
END get_customer_group;


   PROCEDURE create_template (p_customer_name    IN     VARCHAR2,
                              p_group_name       IN     VARCHAR2,
                              p_parent_customer     IN     VARCHAR2,
                              p_bill_location    IN     VARCHAR2,
                              p_template_level   IN     VARCHAR2,
                              p_user_id          IN     VARCHAR2,
                              x_status              OUT VARCHAR2,
                              x_template_id         OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (30) := 'create_template';
   BEGIN
      x_status := 1;

      SELECT CANON_E479_WEB_TEMPL_HDR_SEQ.NEXTVAL
        INTO x_template_id
        FROM DUAL;

      INSERT INTO CANON_E479_WEB_TEMPL_HEADER (header_id,
                                               parent_customer_name,
                                               customer_name,
                                               bill_to_location,
                                               status_flag,
                                               customer_email,
                                               other_email,
                                               within_tab,
                                               creation_date,
                                               created_by,
                                               last_update_date,
                                               last_updated_by,
                                               include_txt_format,
                                               suppress_zero_invoice,
                                               ds_acct_grp_nm,
                                               template_level,
                                               biller_first_name,
                                               biller_last_name,
                                               biller_contact_no,
                                               biller_fax_no,
                                               biller_email_id)
           VALUES (x_template_id,
                   p_parent_customer,
                   p_customer_name,
                   DECODE (p_bill_location, -1, NULL, p_bill_location),
                   C_STS_INCOMPLETE,
                   ' ',
                   ' ',
                   ' ',
                   SYSDATE,
                   p_user_id,
                   SYSDATE,
                   p_user_id,
                   'N',
                   'N',
                   p_group_name,
                   p_template_level,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   NULL);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := -1;
         x_template_id := -1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_customer_name,
                                                  p_parent_customer,
                                                  p_bill_location,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END create_template;

   PROCEDURE copy_template (p_header_id        IN     NUMBER,
                            p_customer_name    IN     VARCHAR2,
                            p_group_name       IN     VARCHAR2,
                            p_parent_customer     IN     VARCHAR2,
                            p_bill_location    IN     VARCHAR2,
                            p_template_level   IN     VARCHAR2,
                            p_user_id          IN     VARCHAR2,
                            x_status              OUT VARCHAR2,
                            x_template_id         OUT NUMBER)
   AS
      l_procedure_name       VARCHAR2 (100) := 'copy_template';
      l_view_id              NUMBER := 0;
      l_col_id               NUMBER := 0;
      l_invoice_break        VARCHAR2 (100);
      l_multi_tab            VARCHAR2 (100);
      l_within_tab           VARCHAR2 (100);
      l_include_text         VARCHAR2 (100);
      l_suppress_zero        VARCHAR2 (100);
      lv_biller_first_name   VARCHAR2 (50);
      lv_biller_last_name    VARCHAR2 (50);
      lv_biller_contact_no   VARCHAR2 (50);
      lv_biller_fax_no       VARCHAR2 (50);
      lv_biller_email_id     VARCHAR2 (50);
   BEGIN
      BEGIN
         create_template (p_customer_name,
                          p_group_name,
                          p_parent_customer,
                          p_bill_location,
                          p_template_level,
                          p_user_id,
                          x_status,
                          x_template_id);

         SELECT INVOICE_BREAK,
                MULTI_TAB,
                WITHIN_TAB,
                NVL (INCLUDE_TXT_FORMAT, 'N'),
                NVL (SUPPRESS_ZERO_INVOICE, 'N'),
                biller_first_name,
                biller_last_name,
                biller_contact_no,
                biller_fax_no,
                biller_email_id
           INTO l_invoice_break,
                l_multi_tab,
                l_within_tab,
                l_include_text,
                l_suppress_zero,
                lv_biller_first_name,
                lv_biller_last_name,
                lv_biller_contact_no,
                lv_biller_fax_no,
                lv_biller_email_id
           FROM CANON_E479_WEB_TEMPL_HEADER
          WHERE header_id = P_HEADER_ID;

         update_template_info (x_template_id,
                               lv_biller_first_name,
                               lv_biller_last_name,
                               lv_biller_contact_no,
                               lv_biller_fax_no,
                               lv_biller_email_id,
                               NULL,                          -- email Address
                               NULL,                            -- other email
                               l_invoice_break,
                               l_multi_tab,
                               l_within_tab,
                               p_user_id,
                               l_include_text,
                               l_suppress_zero,
                               x_status);

         IF (x_template_id > 0)
         THEN
            FOR viewRec IN (  SELECT *
                                FROM CANON_E479_WEB_TEMPL_VIEW
                               WHERE header_id = P_HEADER_ID
                            ORDER BY view_sequence)
            LOOP
               l_view_id := 0;


               INSERT_VIEW_DETAILS (x_template_id,
                                    viewRec.View_name,
                                    viewRec.VIEW_ALIAS,
                                    viewRec.VIEW_SEQUENCE,
                                    viewRec.SORT_PREF_COL1,
                                    viewRec.SORT_PREF_COL2,
                                    viewRec.SORT_PREF_COL3,
                                    p_user_id,
                                    p_user_id,
                                    l_view_id);

               IF (l_view_id > 0)
               THEN
                  FOR columnRec
                     IN (  SELECT *
                             FROM CANON_E479_WEB_TEMPL_COLS
                            WHERE     HEADER_ID = P_HEADER_ID
                                  AND VIEW_ID = viewRec.view_id
                         ORDER BY COLUMN_POSITION)
                  LOOP
                     l_col_id := 0;
                     INSERT_COLUMN_DETAILS (x_template_id,
                                            l_view_id,
                                            columnRec.COLUMN_TYPE,
                                            columnRec.COLUMN_NAME,
                                            columnRec.COLUMN_ALIAS,
                                            columnRec.COLUMN_POSITION,
                                            columnRec.AGGREGATE_BY,
                                            p_user_id,
                                            p_user_id,
                                            l_col_id);
                  END LOOP;
               END IF;
            END LOOP;
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            x_template_id := -1;
            CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                     l_procedure_name,
                                                     'SQL',
                                                     NULL,
                                                     p_customer_name,
                                                     p_parent_customer,
                                                     p_bill_location,
                                                     p_user_id,
                                                     NULL,
                                                     SQLERRM);
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_template_id := -1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  p_customer_name,
                                                  p_parent_customer,
                                                  p_bill_location,
                                                  p_user_id,
                                                  NULL,
                                                  SQLERRM);
   END copy_template;

   PROCEDURE is_concurrent_program_running (x_status OUT VARCHAR2)
   AS
      l_procedure_name   VARCHAR2 (100) := 'is_concurrent_program_running';
   BEGIN
      NULL;

      x_status := 'N';
   /* S21 REPLACEMENT
   select  decode(count(*), 0,'N', 'Y') into x_status from FND_CONC_REQ_SUMMARY_V
   where Program = 'Invoice Master Excel Extract'
   and status_code ='R'
   and phase_code = 'R';
   */

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
   END is_concurrent_program_running;

   PROCEDURE get_par_cust_heirarchy (
        p_customer_name IN       VARCHAR2,
		x_data               OUT result_cursor,
		x_count              OUT NUMBER)
   IS
   l_procedure_name   VARCHAR2 (100) := 'get_par_cust_heirarchy';
   BEGIN
      NULL;

      x_count := 0;

         OPEN x_data FOR
            SELECT 1, '1'
              FROM DUAL
             WHERE 1 = -1;
   /* S21 REPLACEMENT
     */

   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_customer_name,
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
   END get_par_cust_heirarchy;

   PROCEDURE get_cust_grp_heirarchy (
        p_customer_group  IN     VARCHAR2,
		x_data               OUT result_cursor,
		x_count              OUT NUMBER)
   IS
   l_procedure_name   VARCHAR2 (100) := 'get_cust_grp_heirarchy';
   BEGIN
      NULL;

        x_count := 0;

         OPEN x_data FOR
            SELECT 1, '1'
              FROM DUAL
             WHERE 1 = -1;
   /* S21 REPLACEMENT
     */

   EXCEPTION
      WHEN OTHERS
      THEN
        CANON_E479_CUST_BILL_UTIL_PKG.log_error (l_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_customer_group,
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
   END get_cust_grp_heirarchy;


END CANON_E479_TEMPLATE_PKG;
 
/

SHOW ERRORS;
			