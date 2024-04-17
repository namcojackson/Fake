CREATE OR REPLACE PACKAGE CANON_E193_SLM_CI_CR_BATCH_PKG
IS
   --***********************************************************************************
   --Program Name            : CANON_E193_SLM_CI_CR_BATCH_PKG
   --Program Desc            : CANON_E193_SLM_CI_CR_BATCH_PKG is used to create and assign
   --                          tickets from RMA Orders
   --***********************************************************************************
   TYPE cs_ref_cur_typ IS REF CURSOR;

   g_glbl_cmpy_cd                 VARCHAR2 (10) := 'ADB';

   PROCEDURE MAIN (errbuf OUT VARCHAR2, retcode OUT NUMBER);

   FUNCTION GET_VALUE (p_valueset_name IN VARCHAR2, p_flex_value IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE POPULATE_E389_DFF_DATA (p_ticket_id          IN     NUMBER,
                                     p_ticket_line_id     IN     NUMBER,
                                     p_rma_order_number   IN     VARCHAR2,
                                     p_order_header_id    IN     VARCHAR2,
                                     p_order_line_id      IN     VARCHAR2,
                                     x_return_status         OUT VARCHAR2,
                                     x_msg_data              OUT VARCHAR2);

   PROCEDURE MAIN_ASSIGN (errbuf OUT VARCHAR2, retcode OUT NUMBER);

   g_e392_default_valueset_name   VARCHAR2 (30)
                                     := 'CANON_E392_DEFAULT_VALUES';
END CANON_E193_SLM_CI_CR_BATCH_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E193_SLM_CI_CR_BATCH_PKG
IS
   --***********************************************************************************
   --Program Name            : CANON_E193_SLM_CI_CR_BATCH_PKG
   --Program Desc            : CANON_E193_SLM_CI_CR_BATCH_PKG is used to create and assign
   --                          tickets from RMA Orders
   --***********************************************************************************

   PROCEDURE MAIN (errbuf OUT VARCHAR2, retcode OUT NUMBER)
   IS
      CURSOR cur_c1 (
         v_order_from_date       DATE,
         v_order_to_date         DATE,
         p_source_application    VARCHAR2)
      IS
           SELECT DISTINCT
                  CPO_ORD_NUM order_number,
                  (SELECT dcc.ship_to_cust_loc_cd
                     FROM ds_cpo_config dcc
                    WHERE     1 = 1
                          AND rd.ds_cpo_config_pk = dcc.ds_cpo_config_pk
                          AND dcc.glbl_cmpy_cd = 'ADB'
                          AND dcc.ezcancelflag = '0'
                          AND ROWNUM = 1)
                     site_use_id,
                  (SELECT cot.cpo_ord_tp_nm
                     FROM cpo_ord_tp cot
                    WHERE     rd.cpo_ord_tp_cd = cot.cpo_ord_tp_cd
                          AND cot.glbl_cmpy_cd = 'ADB'
                          AND cot.ezcancelflag = 0)
                     TYPE,
                  (SELECT doc.ds_ord_catg_nm
                     FROM cpo dc, ds_ord_catg doc
                    WHERE     dc.cpo_ord_num = rd.cpo_ord_num
                          AND dc.ds_ord_catg_cd = doc.ds_ord_catg_cd
                          AND dc.glbl_cmpy_cd = 'ADB'
                          AND dc.ezcancelflag = 0
                          AND dc.glbl_cmpy_cd = doc.glbl_cmpy_cd
                          AND dc.ezcancelflag = doc.ezcancelflag)
                     order_type,
                  cpo_ord_num order_header_id,
                  ds_cpo_rtrn_line_num order_line_id,
                     CONCAT (rd.ds_ord_posn_num, '.')
                  || CONCAT (rd.ds_cpo_line_num,
                             DECODE (rd.ds_cpo_line_sub_num, NULL, '', '.'))
                  || rd.ds_cpo_line_sub_num
                     line_number,
                  rd.mdse_cd item_code,
                  SUBSTR (rd.rtl_wh_cd, 2) region
             FROM ds_cpo_rtrn_dtl rd, mdse dmi
            WHERE     1 = 1                         
            --AND CPO_ORD_NUM = '02281918'
                  AND rd.mdse_cd = dmi.mdse_cd
                  AND dmi.coa_mdse_tp_cd = '10'
                  AND rd.rtrn_line_sts_cd NOT IN ('10', '99') --Entered, Cancelled
                  AND NVL (ord_line_src_cd, '90') = '90'
                  AND rd.glbl_cmpy_cd = 'ADB'
                  AND rd.ezcancelflag = 0
                  AND rd.glbl_cmpy_cd = dmi.glbl_cmpy_cd
                  AND rd.ezcancelflag = dmi.ezcancelflag
                  AND TO_DATE (rd.cpo_ord_ts, 'YYYYMMDD') >=
                         NVL (v_order_from_date, TRUNC (SYSDATE - 100))
                  AND TO_DATE (rd.cpo_ord_ts, 'YYYYMMDD') <=
                         NVL (v_order_to_date, TRUNC (SYSDATE)) 
                  AND rd.rtl_swh_cd IN
                         (SELECT cvt.val1
                            FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
                           WHERE     ct.cd_name = 'CANON_E193_CANONBALL_SUBIN'
                                 AND ct.cd_id = cvt.cd_id
                                 AND (SYSDATE BETWEEN NVL (
                                                         cvt.start_date_active,
                                                         SYSDATE - 1)
                                                  AND NVL (cvt.end_date_active,
                                                           SYSDATE + 1)))
                  AND NOT EXISTS
                         (SELECT 1
                            FROM canon_e193_jsp_dff_util jspdff
                           WHERE     jspdff.attribute3 = rd.cpo_ord_num
                                 AND jspdff.attribute16 = ds_cpo_rtrn_line_num 
                                 AND jspdff.source_application =
                                        p_source_application
                                 AND jspdff.source_context =
                                        'CS_E193_NONBILL_CANONBALL')
         ORDER BY cpo_ord_num;

     
      CURSOR cur_cust_dtls (
         p_ship_to_cust_loc_cd    VARCHAR2)
      IS
         SELECT stc.sell_to_cust_cd cust_account_id,
                stc.sell_to_cust_cd account_number,
                stc.loc_nm party_name
           FROM ship_to_cust stc
          WHERE     stc.ship_to_cust_cd = p_ship_to_cust_loc_cd
          AND NVL (stc.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
	  	    	                           TO_CHAR (SYSDATE, 'YYYYMMDD')       
             AND NVL(stc.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
                AND stc.ezcancelflag = '0'
                AND stc.glbl_cmpy_cd = 'ADB';


      v_order_from_date            DATE;
      v_order_to_date              DATE;
      v_customer_id                NUMBER;
      v_customer_number            VARCHAR2 (30);
      v_customer_name              VARCHAR2 (360);
      v_notes_detail               VARCHAR2 (2000);
      v_parent_cat_id              NUMBER;
      v_line_cat_id                NUMBER;
      v_resource_id                VARCHAR2 (240);
      v_user_id                    VARCHAR2 (240);
      v_ticket_line_id             NUMBER;
      v_l_owner_res_id             VARCHAR2 (240);
      v_l_owner_role_id            VARCHAR2 (240);
      v_l_owner_dept_code          VARCHAR2 (100);

      v_default_status             VARCHAR2 (240);
      v_default_org_id             VARCHAR2 (240);
      v_default_cust_contact       VARCHAR2 (240);
      v_default_cust_contact_num   VARCHAR2 (240);
      v_default_cust_e_mail        VARCHAR2 (240);
      v_default_caller_type        VARCHAR2 (240);
      v_default_region             VARCHAR2 (240);
      v_default_user               VARCHAR2 (240);
      v_default_resource           VARCHAR2 (240);
      v_default_h_category         VARCHAR2 (240);
      v_default_l_category         VARCHAR2 (240);
      v_default_l_reason_code      VARCHAR2 (240);
      v_default_l_reason           VARCHAR2 (240);
      v_default_notes              VARCHAR2 (240);
      v_default_ra_status          VARCHAR2 (240);
      v_default_doc_type_name      VARCHAR2 (240);
      v_default_function_name      VARCHAR2 (240);
      v_default_source_appl        VARCHAR2 (240);

      x_ticket_id                  NUMBER;
      x_h_insert_count             NUMBER;
      x_l_insert_count             NUMBER;
      x_assign_id                  NUMBER;
      x_a_insert_count             NUMBER;
      x_update_count               NUMBER;
      x_return_status              VARCHAR2 (1);
      x_msg_data                   VARCHAR2 (2000);
      l_region_code                VARCHAR2 (240); 
      x_line_id_cur                cs_ref_cur_typ;
   BEGIN
      --DBMS_OUTPUT.put_line ('Inside Main');
      v_default_status :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_STATUS');
      v_default_org_id := GET_VALUE (g_e392_default_valueset_name, 'ORG_ID');
      v_default_cust_contact :=
         GET_VALUE (g_e392_default_valueset_name, 'CUST_CONTACT');
      v_default_cust_contact_num :=
         GET_VALUE (g_e392_default_valueset_name, 'CUST_CONTACT_NUM');
      v_default_cust_e_mail :=
         GET_VALUE (g_e392_default_valueset_name, 'CUST_E_MAIL');
      v_default_caller_type :=
         GET_VALUE (g_e392_default_valueset_name, 'CALLER_TYPE');
      v_default_region :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_REGION');
      v_default_user :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_USER');
      v_default_resource :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_RESOURCE');
      v_default_h_category :=
         GET_VALUE (g_e392_default_valueset_name, 'HEADER_CATEGORY');
      v_default_l_category :=
         GET_VALUE (g_e392_default_valueset_name, 'LINE_CATEGORY');
      v_default_l_reason_code :=
         GET_VALUE (g_e392_default_valueset_name, 'LINE_REASON_CODE');
      v_default_l_reason :=
         GET_VALUE (g_e392_default_valueset_name, 'LINE_REASON');
      v_default_notes :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_NOTES');
      v_default_ra_status :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_RA_STATUS');
      v_default_doc_type_name :=
         GET_VALUE (g_e392_default_valueset_name, 'DOC_TYPE_NAME');
      v_default_function_name :=
         GET_VALUE (g_e392_default_valueset_name, 'FUNCTION_NAME');
      v_default_source_appl :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_APPLICATION');

     
      BEGIN
         SELECT TO_DATE (VALUE, 'DD-MON-YYYY')
           INTO v_order_from_date
           FROM canon_e193_pgm_in_val_v
          WHERE     parameter_name = 'p_order_from_date'
                AND program_name = 'CANONBALL_TICKET_CREATION';

         SELECT TO_DATE (VALUE, 'DD-MON-YYYY')
           INTO v_order_to_date
           FROM canon_e193_pgm_in_val_v
          WHERE     parameter_name = 'p_order_to_date'
                AND program_name = 'CANONBALL_TICKET_CREATION';
      EXCEPTION
         WHEN OTHERS
         THEN
            v_order_from_date := NULL;
            v_order_to_date := NULL;
      END;

     

      BEGIN
         SELECT cat_id
           INTO v_parent_cat_id
           FROM canon_e193_cs_categories
          WHERE     cat_code = v_default_h_category
                AND region = NVL (v_default_region, 'EAST_REGION');
      EXCEPTION
         WHEN OTHERS
         THEN
            v_parent_cat_id := NULL;
      END;

      BEGIN
         SELECT cat_id
           INTO v_line_cat_id
           FROM canon_e193_cs_categories
          WHERE     cat_code = v_default_l_category
                AND region = NVL (v_default_region, 'EAST_REGION');
      EXCEPTION
         WHEN OTHERS
         THEN
            v_line_cat_id := NULL;
      END;

     -- DBMS_OUTPUT.put_line ('Before Loop cur_c1');
     -- DBMS_OUTPUT.put_line ('v_order_from_date = ' || v_order_from_date);
     -- DBMS_OUTPUT.put_line ('v_order_to_date = ' || v_order_to_date);
     -- DBMS_OUTPUT.put_line ('v_default_source_appl = ' || v_default_source_appl);

      FOR rec_c1
         IN cur_c1 (v_order_from_date,
                    v_order_to_date,
                    v_default_source_appl)
      LOOP
         --DBMS_OUTPUT.put_line ('Inside Loop');
         
         v_customer_id := NULL;
         v_customer_number := NULL;
         v_customer_name := NULL;
         v_notes_detail := NULL;
         l_region_code := NULL; 
         v_notes_detail :=
               v_default_notes
            || CHR (10)
            || 'Order Number  : '
            || rec_c1.order_number
            || CHR (10)
            || 'Line Number   : '
            || rec_c1.line_number
            || CHR (10)
            || 'RMA Item Code : '
            || rec_c1.item_code;

         OPEN cur_cust_dtls (rec_c1.site_use_id);

         FETCH cur_cust_dtls
            INTO v_customer_id, v_customer_number, v_customer_name;

         CLOSE cur_cust_dtls;

         DBMS_OUTPUT.put_line ('v_customer_id = ' || v_customer_id);

         BEGIN
          /*  SELECT cvt.val2
              INTO l_region_code
              FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
             WHERE     ct.cd_id = cvt.cd_id
                   AND ct.cd_name = 'CANON_E392_ORG_REGION_MAP'
                   AND cvt.val1 = rec_c1.region
                   AND (SYSDATE BETWEEN NVL (cvt.start_date_active,
                                             SYSDATE - 1)
                                    AND NVL (cvt.end_date_active,
                                             SYSDATE + 1));*/
      
     /* Select DECODE (REGION ,
      			'EAST_REGION', 'East',
                        'CENTRAL_REGION', 'Central',
                        'WEST_REGION', 'West',
                        REGION)
      From s21_psn psn,org_func_asg ofa,toc,csr_e193_contract_branch_v branch
      where branch_code=toc.toc_cd--'221'
      AND toc.toc_cd=ofa.toc_cd
      AND  ofa.psn_cd=psn.psn_cd 
      AND psn.psn_cd=v_employee_number;*/
        SELECT val.val3
           INTO l_region_code
           FROM cpo,
                toc,
                canon_s21_cd_tbl cd,
                canon_s21_cd_val_tbl val
          WHERE     1 = 1                   --dcrt.cpo_ord_num=cpo.cpo_ord_num
                AND cpo.sls_rep_toc_cd = toc.toc_cd
                AND toc.coa_br_cd = val.val1
                AND cd.cd_id = val.cd_id
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                AND cd.cd_name = 'CSR_E193_CONTRACT_BRANCH'
                AND cpo.cpo_ord_num = rec_c1.order_number
                 AND ROWNUM = 1;
          EXCEPTION
            WHEN OTHERS
            THEN
               l_region_code := NVL (v_default_region, 'EAST_REGION');
         END;

         DBMS_OUTPUT.put_line ('l_region_code= ' || l_region_code);

         SELECT crm_role_type_code, crm_role_id, crm_resource_id
           INTO v_l_owner_dept_code, v_l_owner_role_id, v_l_owner_res_id
           FROM canon_e193_cs_categories
          WHERE cat_code = v_default_l_category 
                AND region = l_region_code;

         DBMS_OUTPUT.put_line ( 'v_l_owner_dept_code= ' || v_l_owner_dept_code);

         DBMS_OUTPUT.put_line (
            'Calling CANON_E193_CS_EVOLUTION_PKG.INSERT_NONBILL ');
         CANON_E193_CS_EVOLUTION_PKG.INSERT_NONBILL (
            p_h_parent_cat_id          => v_parent_cat_id,
            p_h_ticket_status          => v_default_status,
            p_h_recurring              => 'N',
            p_h_billing_issue          => 'N',
            p_h_org_id                 => v_default_org_id,
            p_h_cust_account_id        => v_customer_id,
            p_h_customer_name          => v_customer_name,
            p_h_customer_number        => v_customer_number,
            p_h_invoice_number         => NULL,
            p_h_contract_number        => NULL,
            p_h_contract_modifier      => NULL,
            p_h_order_number           => rec_c1.order_number,
            p_h_order_type             => rec_c1.order_type,
            p_h_orig_name              => v_default_cust_contact,
            p_h_orig_ph_number         => v_default_cust_contact_num,
            p_h_orig_e_mail            => v_default_cust_e_mail,
            p_h_orig_type              => v_default_caller_type,
            p_h_cust_contact           => v_default_cust_contact,
            p_h_cust_ph_number         => v_default_cust_contact_num,
            p_h_cust_e_mail            => v_default_cust_e_mail,
            p_h_jtf_notes_flag         => 'N',
            p_h_attribute1             => NULL,
            p_h_attribute2             => NULL,
            p_h_attribute3             => NULL,
            p_h_attribute4             => NULL,
            p_h_attribute5             => REPLACE (l_region_code, '_REGION') ,
            p_h_attribute6             => v_default_region,
            p_h_attribute7             => NULL,
            p_h_attribute8             => NULL,
            p_h_attribute9             => NULL,
            p_h_attribute10            => NULL,
            p_h_attribute11            => NULL,
            p_h_attribute12            => NULL,
            p_h_attribute13            => NULL,
            p_h_attribute14            => NULL,
            p_h_attribute15            => NULL,
            p_h_owner_role_id          => v_l_owner_role_id,
            p_h_owner_res_id           => v_l_owner_res_id,
            p_h_owner_dept_code        => v_l_owner_dept_code,
            p_h_created_by             => v_default_user,
            p_h_created_by_role_id     => NULL,
            p_h_created_by_res_id      => v_default_resource,
            p_h_created_by_dept_code   => NULL-- line attributes --
            ,
            p_l_cat_id                 => v_line_cat_id,
            p_l_line_status            => v_default_status,
            p_l_severity               => 'NORMAL',
            p_l_reason_code            => v_default_l_reason_code,
            p_l_reason                 => v_default_l_reason,
            p_l_jtf_notes_flag         => 'Y',
            p_l_attribute1             => NULL,
            p_l_attribute2             => NULL,
            p_l_attribute3             => NULL,
            p_l_attribute4             => NULL,
            p_l_attribute5             => NULL,
            p_l_created_by             => v_default_user,
            p_jtf_notes_detail         => v_notes_detail,
            p_send_email_flag          => NULL,
            p_h_ticket_id              => x_ticket_id,
            p_h_insert_count           => x_h_insert_count,
            p_l_insert_count           => x_l_insert_count);
         DBMS_OUTPUT.put_line ('p_h_ticket_id= ' || x_ticket_id);

         IF ( (x_ticket_id > 0) AND (x_l_insert_count > 0))
         THEN
            
            SELECT line_id
              INTO v_ticket_line_id
              FROM canon_e193_cs_lines
             WHERE ticket_id = x_ticket_id;

            CANON_E193_CS_EVOLUTION_PKG.ADD_ASSIGNMENTS (
               p_line_id               => v_ticket_line_id,
               p_assign_to_dept_code   => v_l_owner_dept_code,
               p_assign_to_role_id     => v_l_owner_role_id,
               p_assign_to_res_id      => v_l_owner_res_id,
               p_a_updated_by          => v_default_user,
               p_assign_id             => x_assign_id,
               p_a_insert_count        => x_a_insert_count);

            

            POPULATE_E389_DFF_DATA (
               p_ticket_id          => x_ticket_id,
               p_ticket_line_id     => v_ticket_line_id,
               p_rma_order_number   => rec_c1.order_number,
               p_order_header_id    => rec_c1.order_header_id,
               p_order_line_id      => rec_c1.order_line_id,
               x_return_status      => x_return_status,
               x_msg_data           => x_msg_data);

            

            CANON_E193_CS_EVOLUTION_PKG.TICKET_SUMMARY_CALL_WRAP (
               p_org_id         => v_default_org_id,
               p_ticket_id      => x_ticket_id,
               p_line_id        => v_ticket_line_id,
               p_status         => 'ASSIGNED',
               p_updated_by     => v_default_user,
               p_update_count   => x_update_count,
               p_line_id_cur    => x_line_id_cur);

            
         END IF;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         errbuf := SQLERRM;
         retcode := 2;
   END MAIN;

   FUNCTION GET_VALUE (p_valueset_name IN VARCHAR2, p_flex_value IN VARCHAR2)
      RETURN VARCHAR2
   IS
      v_return_value   VARCHAR2 (500);
   BEGIN
      
      SELECT cd_val_tbl.val2 reason_code
        INTO v_return_value
        FROM canon_s21_cd_tbl cd_tbl, canon_s21_cd_val_tbl cd_val_tbl
       WHERE     cd_tbl.cd_id = cd_val_tbl.cd_id
             AND cd_tbl.cd_name = p_valueset_name --'CANON_E193_SLM_DEFAULT_VALUE'
             AND cd_val_tbl.val1 = p_flex_value
             AND NVL (end_date_active, SYSDATE + 1) > SYSDATE;

      RETURN (v_return_value);
   EXCEPTION
      WHEN OTHERS
      THEN
         v_return_value := NULL;
         RETURN (v_return_value);
   END GET_VALUE;

   PROCEDURE POPULATE_E389_DFF_DATA (p_ticket_id          IN     NUMBER,
                                     p_ticket_line_id     IN     NUMBER,
                                     p_rma_order_number   IN     VARCHAR2,
                                     p_order_header_id    IN     VARCHAR2,
                                     p_order_line_id      IN     VARCHAR2,
                                     x_return_status         OUT VARCHAR2,
                                     x_msg_data              OUT VARCHAR2)
   IS
      v_source_application      VARCHAR2 (30);
      v_source_context          VARCHAR2 (60);
      v_default_user            VARCHAR2 (60);
      v_program_code            VARCHAR2 (240);
      v_serial_number           VARCHAR2 (30);
      v_line_number             VARCHAR2 (30);
      v_user_id                 VARCHAR2 (240);
      v_warehouse               VARCHAR2 (240);
      v_default_ra_status       VARCHAR2 (60);
      v_attached_document_id    NUMBER;
      v_item_code               VARCHAR2 (240);
      v_sql                     VARCHAR2 (2000);
      v_default_doc_type_name   VARCHAR2 (240);
      v_default_function_name   VARCHAR2 (240);
      v_no_of_accessories       NUMBER;
      v_accessories_flag        VARCHAR2 (1);
      v_created_by              VARCHAR2 (240);
      v_employee_number         VARCHAR2 (240);
      v_sales_order_region      VARCHAR2 (340);
      v_attributesRec           CANON_E389_DFF_UTILITY_PKG.AttributesRec;
   BEGIN
      v_default_user :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_USER');
      v_default_ra_status :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_RA_STATUS');
      v_default_doc_type_name :=
         GET_VALUE (g_e392_default_valueset_name, 'DOC_TYPE_NAME');
      v_default_function_name :=
         GET_VALUE (g_e392_default_valueset_name, 'FUNCTION_NAME');
      v_source_application :=
         GET_VALUE (g_e392_default_valueset_name, 'DEFAULT_APPLICATION');

      
      SELECT 'CS_E193_' || cat1.cat_code || '_' || cat2.cat_code
        INTO v_source_context
        FROM canon_e193_cs_headers hdr,
             canon_e193_cs_lines line,
             canon_e193_cs_categories cat1,
             canon_e193_cs_categories cat2
       WHERE     hdr.ticket_id = p_ticket_id
             AND line.ticket_id = hdr.ticket_id
             AND line.line_id = p_ticket_line_id
             AND cat1.cat_id = hdr.cat_id
             AND cat2.cat_id = line.cat_id;

      SELECT    CONCAT (c.ds_ord_posn_num, '.')
             || CONCAT (c.ds_cpo_line_num,
                        DECODE (c.ds_cpo_line_sub_num, NULL, '', '.'))
             || c.ds_cpo_line_sub_num
                line_number,
             (SELECT rw.rtl_wh_nm
                FROM rtl_wh rw
               WHERE     c.rtl_wh_cd = rw.rtl_wh_cd
                     AND rw.glbl_cmpy_cd = 'ADB'
                     AND rw.ezcancelflag = 0
                     AND ROWNUM = 1)
                organization_name,
             mdse_cd ordered_item,
             SER_NUM serial_number,
             (SELECT c.mdse_nm
                FROM canon_s21_cd_tbl ct, canon_s21_cd_val_tbl cvt
               WHERE     ct.cd_name = 'CANON_E25_DI_CHECK'
                     AND ct.cd_id = cvt.cd_id
                     AND cvt.val2 = 'ITEM_CODE'
                     AND cvt.val3 = c.mdse_cd
                     AND (SYSDATE BETWEEN NVL (cvt.start_date_active,
                                               SYSDATE - 1)
                                      AND NVL (cvt.end_date_active,
                                               SYSDATE + 1)))
                program_code,
             (SELECT COUNT (1)
                FROM ds_cpo_rtrn_dtl c1
               WHERE     c1.cpo_ord_num = c.cpo_ord_num
                     AND c1.ds_cpo_line_num = c.ds_cpo_line_num
                     AND c1.rtl_swh_cd IN
                            (SELECT cvt.val1
                               FROM canon_s21_cd_tbl ct,
                                    canon_s21_cd_val_tbl cvt
                              WHERE     ct.cd_name =
                                           'CANON_E193_CANONBALL_SUBIN'
                                    AND ct.cd_id = cvt.cd_id
                                    AND (SYSDATE BETWEEN NVL (
                                                            cvt.start_date_active,
                                                            SYSDATE - 1)
                                                     AND NVL (
                                                            cvt.end_date_active,
                                                            SYSDATE + 1)))
                     AND EXISTS
                            (SELECT 1
                               FROM mdse dmi1
                              WHERE     dmi1.mdse_cd = c1.mdse_cd
                                    AND dmi1.glbl_cmpy_cd = 'ADB'
                                    AND dmi1.ezcancelflag = 0
                                    AND dmi1.coa_mdse_tp_cd = '20'))
                no_of_accessories,
             ezinuserid
        INTO v_line_number,
             v_warehouse,
             v_item_code,
             v_serial_number,
             v_program_code,
             v_no_of_accessories,
             v_created_by
        FROM ds_cpo_rtrn_dtl c
       WHERE     1 = 1
             AND c.cpo_ord_num = p_order_header_id
             AND c.glbl_cmpy_cd = 'ADB'
             AND c.ezcancelflag = 0
             AND c.ds_cpo_rtrn_line_num = p_order_line_id
             AND ROWNUM = 1;

    
      IF (v_no_of_accessories = 0)
      THEN
         v_accessories_flag := 'N';
      ELSE
         v_accessories_flag := 'Y';
      END IF;

      v_employee_number := v_created_by;

      BEGIN
      
     /* Select DECODE (REGION ,
      			'EAST_REGION', 'East',
                        'CENTRAL_REGION', 'Central',
                        'WEST_REGION', 'West',
                        REGION)
      From s21_psn psn,org_func_asg ofa,toc,csr_e193_contract_branch_v branch
      where branch_code=toc.toc_cd--'221'
      AND toc.toc_cd=ofa.toc_cd
      AND  ofa.psn_cd=psn.psn_cd 
      AND psn.psn_cd=v_employee_number;*/
        SELECT DECODE (val.val3,
                        'EAST_REGION', 'East',
                        'CENTRAL_REGION', 'Central',
                        'WEST_REGION', 'West',
                        val.val3)
           INTO v_sales_order_region
           FROM cpo,
                toc,
                canon_s21_cd_tbl cd,
                canon_s21_cd_val_tbl val
          WHERE     1 = 1                   --dcrt.cpo_ord_num=cpo.cpo_ord_num
                AND cpo.sls_rep_toc_cd = toc.toc_cd
                AND toc.coa_br_cd = val.val1
                AND cd.cd_id = val.cd_id
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                AND cd.cd_name = 'CSR_E193_CONTRACT_BRANCH'
                AND cpo.cpo_ord_num = p_rma_order_number
                 AND ROWNUM = 1;
      EXCEPTION
         WHEN OTHERS
         THEN
            v_sales_order_region := NULL;
      END;

      v_attributesRec.ATTRIBUTE1 := v_program_code;
      v_attributesRec.ATTRIBUTE2 := v_serial_number;
      v_attributesRec.ATTRIBUTE3 := p_rma_order_number;
      v_attributesRec.ATTRIBUTE4 := v_line_number;
      v_attributesRec.ATTRIBUTE5 := v_warehouse;
      v_attributesRec.ATTRIBUTE6 := v_accessories_flag;
      v_attributesRec.ATTRIBUTE14 := v_default_ra_status;
      v_attributesRec.ATTRIBUTE15 := v_attached_document_id;
      v_attributesRec.ATTRIBUTE16 := p_order_line_id;
      v_attributesRec.ATTRIBUTE17 := v_item_code;
      v_attributesRec.ATTRIBUTE18 := v_sales_order_region;
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE1 = '|| v_attributesRec.ATTRIBUTE1    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE2 = '|| v_attributesRec.ATTRIBUTE2    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE3 = '|| v_attributesRec.ATTRIBUTE3    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE4 = '|| v_attributesRec.ATTRIBUTE4    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE5 = '|| v_attributesRec.ATTRIBUTE5    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE6 = '|| v_attributesRec.ATTRIBUTE6    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE14 = '|| v_attributesRec.ATTRIBUTE14    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE15 = '|| v_attributesRec.ATTRIBUTE15    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE16 = '|| v_attributesRec.ATTRIBUTE16    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE17 = '|| v_attributesRec.ATTRIBUTE17    );
      --dbms_output.put_line('v_attributesRec.ATTRIBUTE18 = '|| v_attributesRec.ATTRIBUTE18    );

      --dbms_output.put_line('v_source_application= '|| v_source_application    );
      CANON_E389_DFF_UTILITY_PKG.INSERT_DFF_DATA (
         p_commit               => 'S',
         p_source_application   => v_source_application,
         p_source_context       => v_source_context,
         p_context_value        => p_ticket_id,
         p_last_update_date     => SYSDATE,
         p_last_updated_by      => v_default_user,
         p_creation_date        => SYSDATE,
         p_created_by           => v_default_user,
         p_attributesRec        => v_attributesRec,
         x_return_status        => x_return_status,
         x_msg_data             => x_msg_data);
   --dbms_output.put_line('After calling CANON_E389_DFF_UTILITY_PKG.INSERT_DFF_DATA'    );
   EXCEPTION
      WHEN OTHERS
      THEN
         x_return_status := 'E';
         x_msg_data := 'POPULATE_E389_DFF_DATA Exception : ' || SQLERRM;
   --dbms_output.put_line('POPULATE_E389_DFF_DATA Exception : ' || SQLERRM    );
   END POPULATE_E389_DFF_DATA;

   /*******************************************************************************************
       Procedure Name: MAIN_ASSIGN
       Description: Procedure to Assign The CI's back To CS Dept. Once The Wholesale Order Is Shipped.
       Input Parameters: None

       Output Parameter: errbuf
               retcode

   *******************************************************************************************/

   PROCEDURE MAIN_ASSIGN (errbuf OUT VARCHAR2, retcode OUT NUMBER)
   IS
      CURSOR cur_c1 (p_resource_id VARCHAR2, p_default_source_appl VARCHAR2)
      IS
         SELECT hdr.ticket_id,
                line.line_id,
                TO_NUMBER (jspdff.attribute7) ship_order_number,
                jspdff.attribute2 serial_number,
                jspdff.attribute17 item_code,
                hdr.attribute5 ticket_region
           FROM canon_e193_cs_headers hdr,
                canon_e193_cs_lines line,
                canon_e193_cs_assignments assign,
                canon_e193_jsp_dff_util jspdff
          WHERE     hdr.ticket_status NOT IN ('UNASSIGNED', 'CLOSE')
                AND line.ticket_id = hdr.ticket_id
                AND line.line_status NOT IN ('UNASSIGNED', 'CLOSE')
                AND assign.line_id = line.line_id
                AND assign.line_status NOT IN ('UNASSIGNED', 'CLOSE')
                AND assign.assign_status = 'ACTIVE'
                AND assign.assign_to_res_id = p_resource_id
                AND assign.assign_id = (SELECT MAX (assign_id)
                                          FROM canon_e193_cs_assignments
                                         WHERE line_id = line.line_id)
                AND jspdff.context_value = hdr.ticket_id
                AND jspdff.source_application = p_default_source_appl
                AND jspdff.source_context = 'CS_E193_NONBILL_CANONBALL';

      CURSOR cur_c2 (
         p_ship_order_number    VARCHAR2,
         p_item_code            VARCHAR2,
         p_serial_number        VARCHAR2)
      IS
         SELECT UPPER (ols.ord_line_sts_nm) flow_status_code,
                dcdv.dply_line_num line_number,
                to_char(to_date(sp.actl_ship_dt,'YYYYMMDD'),'DD-MON-YYYY' ) ship_date
           FROM shpg_ord_dtl sod,
                shpg_pln sp,
                cpo_dtl_v dcdv,
                ship_ser_num ssn,
                ord_line_sts ols
          WHERE     sp.glbl_cmpy_cd = 'ADB'
                AND sp.ezcancelflag = '0'
                AND sod.glbl_cmpy_cd = 'ADB'
                AND sod.ezcancelflag = '0'
                AND dcdv.glbl_cmpy_cd = 'ADB'
                AND dcdv.ezcancelflag = '0'
                AND ols.glbl_cmpy_cd = 'ADB'
                AND ols.ezcancelflag = '0'
                AND sod.shpg_pln_num = sp.shpg_pln_num
                AND sp.trx_hdr_num = dcdv.cpo_ord_num
                AND sp.trx_line_num = dcdv.cpo_dtl_line_num
                AND sp.trx_line_sub_num = dcdv.cpo_dtl_line_sub_num
                AND sod.so_num = ssn.so_num
                AND sod.so_slp_num = ssn.so_slp_num
                AND dcdv.ord_line_sts_cd = ols.ord_line_sts_cd
                AND dcdv.cpo_ord_num = p_ship_order_number
                AND dcdv.mdse_cd = p_item_code
                AND NVL (ssn.ser_num, '-XCANONX-') =
                       NVL (p_serial_number, '-XCANONX-');


      v_note_details          VARCHAR2 (1000);
      v_default_resource      VARCHAR2 (240);
      v_default_user          VARCHAR2 (240);
      v_default_source_appl   VARCHAR2 (240);
      v_default_org_id        VARCHAR2 (240);
      v_default_dept          VARCHAR2 (240);
      v_e_default_resource    VARCHAR2 (240);
      v_c_default_resource    VARCHAR2 (240);
      v_w_default_resource    VARCHAR2 (240);
      v_source_name           VARCHAR2 (240);
      v_l_owner_dept_code     VARCHAR2 (150);
      v_line_status           VARCHAR2 (30);
      v_line_number           VARCHAR2 (30);
      v_ship_date             VARCHAR2 (30);
      v_user_id               VARCHAR2 (300);
      v_resource_id           VARCHAR2 (300);
      v_l_owner_role_id       VARCHAR2 (300);
      v_l_owner_res_id        VARCHAR2 (300);

      x_assign_id             NUMBER;
      x_a_insert_count        NUMBER;
      x_note_id               NUMBER;
      x_return_status         VARCHAR2 (1);
   BEGIN
      DBMS_OUTPUT.put_line ('Inside begin');
      v_default_resource :=
         CANON_E193_SLM_CI_CR_BATCH_PKG.GET_VALUE (
            g_e392_default_valueset_name,
            'DEFAULT_RESOURCE');
      v_default_user :=
         CANON_E193_SLM_CI_CR_BATCH_PKG.GET_VALUE (
            g_e392_default_valueset_name,
            'DEFAULT_USER');
      v_default_source_appl :=
         CANON_E193_SLM_CI_CR_BATCH_PKG.GET_VALUE (
            g_e392_default_valueset_name,
            'DEFAULT_APPLICATION');
      v_default_org_id :=
         CANON_E193_SLM_CI_CR_BATCH_PKG.GET_VALUE (
            g_e392_default_valueset_name,
            'ORG_ID');
      v_default_dept :=
         CANON_E193_SLM_CI_CR_BATCH_PKG.GET_VALUE (
            g_e392_default_valueset_name,
            'DEFAULT_DEPT');
      v_e_default_resource :=
         CANON_E193_SLM_CI_CR_BATCH_PKG.GET_VALUE (
            g_e392_default_valueset_name,
            'DEFAULT_RESOURCE_EAST');
      v_c_default_resource :=
         CANON_E193_SLM_CI_CR_BATCH_PKG.GET_VALUE (
            g_e392_default_valueset_name,
            'DEFAULT_RESOURCE_CENTRAL');
      v_w_default_resource :=
         CANON_E193_SLM_CI_CR_BATCH_PKG.GET_VALUE (
            g_e392_default_valueset_name,
            'DEFAULT_RESOURCE_WEST');

      /*BEGIN
         SELECT resource_id
         INTO v_resource_id
         FROM jtf_rs_resource_extns@csa_oracle
         WHERE source_name = v_default_resource;
      EXCEPTION
         WHEN OTHERS THEN
            v_resource_id := -1;
      END;*/

        BEGIN
           SELECT psn_cd
           INTO v_user_id
           FROM s21_psn psn
           WHERE psn_cd = v_default_user
           AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
	    	                           TO_CHAR (SYSDATE, 'YYYYMMDD')       
           AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
           AND psn.ezcancelflag = 0
           AND psn.glbl_cmpy_cd = 'ADB';
        EXCEPTION
           WHEN OTHERS THEN
              v_user_id := -1;
        END;

      DBMS_OUTPUT.put_line ('v_default_resource =' || v_default_resource);
      DBMS_OUTPUT.put_line (
         'v_default_source_appl = ' || v_default_source_appl);

      FOR rec_c1 IN cur_c1 (v_default_resource, v_default_source_appl)
      LOOP
         DBMS_OUTPUT.put_line ('Inside cur1');
         v_line_status := NULL;
         v_line_number := NULL;
         v_ship_date := NULL;
         DBMS_OUTPUT.put_line (
            'rec_c1.ship_order_number =' || rec_c1.ship_order_number);

         OPEN cur_c2 (rec_c1.ship_order_number,
                      rec_c1.item_code,
                      rec_c1.serial_number);

         FETCH cur_c2 INTO v_line_status, v_line_number, v_ship_date;

         DBMS_OUTPUT.put_line ('v_line_status =' || v_line_status);
         DBMS_OUTPUT.put_line ('v_line_status = ' || v_line_status);

         IF cur_c2%NOTFOUND
         THEN
            v_line_status := NULL;
            v_line_number := NULL;
            v_ship_date := NULL;
         END IF;

         CLOSE cur_c2;

         IF (v_line_status IS NOT NULL) AND (v_line_status = 'CLOSED')
         THEN
            v_l_owner_dept_code := NULL;
            v_l_owner_role_id := NULL;
            v_l_owner_res_id := NULL;
            v_l_owner_dept_code := v_default_dept;

            IF (rec_c1.ticket_region || '_REGION' = 'EAST_REGION')
            THEN
               v_source_name := v_e_default_resource;
            ELSIF (rec_c1.ticket_region || '_REGION' = 'CENTRAL_REGION')
            THEN
               v_source_name := v_c_default_resource;
            ELSIF (rec_c1.ticket_region || '_REGION' = 'WEST_REGION')
            THEN
               v_source_name := v_w_default_resource;
            END IF;

            BEGIN
               /*   SELECT resource_id
                  INTO v_l_owner_res_id
                  FROM jtf_rs_resource_extns
                  WHERE source_name = v_source_name
                  AND   nvl(end_date_active, SYSDATE) >= SYSDATE;*/

               /*  SELECT jrrv.role_id
                 INTO v_l_owner_role_id
                 FROM jtf_rs_role_relations  jrrr,
                      jtf_rs_roles_vl        jrrv
                 WHERE jrrr.role_resource_id = v_l_owner_res_id
                 AND   jrrv.role_id = jrrr.role_id
                 AND   nvl(jrrr.end_date_active, SYSDATE) >= SYSDATE
                 AND   jrrv.role_code LIKE v_default_dept || '%'
                 AND   ROWNUM = 1;*/
                 
               SELECT ofrt.org_func_role_tp_cd role_id
               INTO v_l_owner_role_id
	                 FROM s21_psn psn,
	                       org_func_asg ofa,
	                       toc tc,
	                       org_func_role_tp ofrt
	                 WHERE     1 = 1
	                       AND psn.psn_cd = ofa.psn_cd
	                       AND psn.psn_cd = v_source_name
	                       AND psn.ezcancelflag = ofa.ezcancelflag
	                       AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
	                       AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
	                              TO_CHAR (SYSDATE, 'YYYYMMDD')
	                       AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
	                              TO_CHAR (SYSDATE, 'YYYYMMDD')
	                       AND ofa.toc_cd = tc.toc_cd
	                       AND ofa.ezcancelflag = tc.ezcancelflag
	                       AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
	                       AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
	                       AND tc.ezcancelflag = ofrt.ezcancelflag
	                       AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
	                       AND ofrt.org_func_role_tp_nm like v_default_dept || '%'
	                       AND psn.del_flg = 'N'
	                       AND psn.ezcancelflag = 0
	                       AND psn.glbl_cmpy_cd = 'ADB'
	                       AND EXISTS
	                              (SELECT 'X'
	                                 FROM ds_org_unit dou
	                                WHERE     1 = 1
	                                    AND dou.org_stru_tp_cd = ofrt.org_stru_tp_cd
	                                      AND dou.ezcancelflag = ofrt.ezcancelflag
	                                      AND dou.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                               AND dou.org_nm LIKE v_default_dept || '%') 
                               AND ROWNUM = 1;
               
            EXCEPTION
               WHEN OTHERS
               THEN
                  SELECT owner_dept_code, owner_role_id, owner_res_id
                    INTO v_l_owner_dept_code,
                         v_l_owner_role_id,
                         v_l_owner_res_id
                    FROM canon_e193_cs_headers
                   WHERE ticket_id = rec_c1.ticket_id;
            END;

            DBMS_OUTPUT.put_line ('Before calling Assignment');
            CANON_E193_CS_EVOLUTION_PKG.ADD_ASSIGNMENTS (
               p_line_id               => rec_c1.line_id,
               p_assign_to_dept_code   => v_l_owner_dept_code,
               p_assign_to_role_id     => v_l_owner_role_id,
               p_assign_to_res_id      => v_source_name     --v_l_owner_res_id
                                                       ,
               p_a_updated_by          => v_default_user,
               p_assign_id             => x_assign_id,
               p_a_insert_count        => x_a_insert_count);

            IF (x_a_insert_count > 0)
            THEN
               -- Fnd_File.Put_Line(Fnd_File.Log, 'E193 - Ticket : ' || rec_c1.ticket_id || ' : Assigned Back To Its Owner Successfully.');

               v_note_details := NULL;
               v_note_details :=
                     'The Stored Lease Machine on this CI is now shipped and awaiting POD. '
                  || 'CI assigned to warehouse. Ship Order details below : ';
               v_note_details :=
                     v_note_details
                  || canon_e193_cs_sqls_pkg.newline
                  || 'Ship Order Number : '
                  || rec_c1.ship_order_number;
               v_note_details :=
                     v_note_details
                  || canon_e193_cs_sqls_pkg.newline
                  || 'Line Number : '
                  || v_line_number;
               v_note_details :=
                     v_note_details
                  || canon_e193_cs_sqls_pkg.newline
                  || 'Ship Date   : '
                  || v_ship_date;

               CANON_E193_CS_EVOLUTION_PKG.ADD_NOTES (
                  p_org_id             => v_default_org_id,
                  p_source_object_id   => rec_c1.line_id,
                  p_created_by         => v_user_id,
                  p_jtf_notes_detail   => v_note_details,
                  x_note_id            => x_note_id,
                  x_ret_status         => x_return_status);

               IF (x_return_status <> 'S')
               THEN
                  COMMIT;
               --Fnd_File.Put_Line(Fnd_File.Log, 'E193 - Ticket : ' || rec_c1.ticket_id || ' : Notes Updated Successfully.');
               END IF;
            END IF;
         --Fnd_File.Put_Line(Fnd_File.Log, Fnd_Global.NewLine);
         END IF;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         errbuf := SQLERRM;
         retcode := 2;
   -- Fnd_File.Put_Line(Fnd_File.Log, 'Exception : ' || SQLERRM);

   END MAIN_ASSIGN;
END CANON_E193_SLM_CI_CR_BATCH_PKG;
/

SHOW ERR