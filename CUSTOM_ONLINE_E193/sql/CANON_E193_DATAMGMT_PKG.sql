CREATE OR REPLACE PACKAGE CANON_E193_DATAMGMT_PKG IS
--***********************************************************************************
--Program Name            : CANON_E193_DATAMGMT_PKG
--Program Desc            : CANON_E193_DATAMGMT_PKG
--***********************************************************************************

TYPE cs_ref_cur_typ IS REF CURSOR;

PROCEDURE CREATE_TICKET ( p_out_status OUT VARCHAR2);

--PROCEDURE GET_ATTACH_DETAILS ( p_attach_cur  OUT   cs_ref_cur_typ);

PROCEDURE CREATE_QUICK_TICKET (p_out_status OUT VARCHAR2);

PROCEDURE INSERT_DM_EMAIL_STG ( p_from_email IN VARCHAR2
			       ,p_subject IN VARCHAR2
			       ,p_body IN CLOB
			       ,p_message_id IN VARCHAR2
			       ,p_received_date IN DATE
			       ,o_id OUT NUMBER
			       ,o_status OUT VARCHAR2);

PROCEDURE INSERT_E193_DM_ATTCHMENT ( p_id IN NUMBER,
				p_file_type IN VARCHAR2
			       ,p_file_name IN VARCHAR2
			       ,p_attachment IN BLOB
			       ,o_status OUT VARCHAR2);	
			       
PROCEDURE INSERT_E193_DATA_MGMT_ATT ( p_id IN NUMBER,
				p_file_type IN VARCHAR2
			       ,p_file_name IN VARCHAR2
			       ,p_file_path IN VARCHAR2
			       ,o_status OUT VARCHAR2);			       
			       
PROCEDURE GET_MESSAGE_DETAILS ( p_message_id IN VARCHAR2
			       ,p_message_cur  OUT   cs_ref_cur_typ);	
			       
PROCEDURE INSERT_DATA_MGMT_STG (p_action               IN     VARCHAR2,
                                p_sub_action           IN     VARCHAR2,
                                p_created_by           IN     VARCHAR2,
                                p_last_updated_by      IN     VARCHAR2,
                                p_employee_name        IN     VARCHAR2,
                                p_employee_phone       IN     VARCHAR2,
                                p_sub_company          IN     VARCHAR2,
                                p_department           IN     VARCHAR2,
                                p_customer_number      IN     VARCHAR2,
                                p_customer_name        IN     VARCHAR2,
                                p_cust_contact_name    IN     VARCHAR2,
                                p_cust_contact_phone   IN     VARCHAR2,
                                p_notes                IN     VARCHAR2,
                                p_attribute1           IN     VARCHAR2,
                                p_attribute2           IN     VARCHAR2,
                                p_attribute3           IN     VARCHAR2,
                                p_attribute4           IN     VARCHAR2,
                                p_attribute5           IN     VARCHAR2,
                                p_attribute6           IN     VARCHAR2,
                                p_attribute7           IN     VARCHAR2,
                                p_attribute8           IN     VARCHAR2,
                                p_attribute9           IN     VARCHAR2,
                                p_attribute10          IN     VARCHAR2,
                                p_file_type            IN     VARCHAR2,
                                p_file_name            IN     VARCHAR2,
                                p_file_path           IN     VARCHAR2,
                                o_id                      OUT NUMBER,
                                o_status                  OUT VARCHAR2,
                                o_message                 OUT VARCHAR2);			       

END CANON_E193_DATAMGMT_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E193_DATAMGMT_PKG IS
--***********************************************************************************
--Program Name            : CANON_E193_DATAMGMT_PKG
--Program Desc            : CANON_E193_DATAMGMT_PKG
--***********************************************************************************

/*******************************************************************************************
 Procedure Name: CREATE_TICKET
 Description: Procedure to create DM ticket in Customer Care
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

PROCEDURE CREATE_TICKET (p_out_status OUT VARCHAR2)
IS
   CURSOR C1
   IS
      SELECT id,
             action,
             employee_name,
             employee_phone,
             customer_number,
             created_by,
             customer_name,
             DECODE (sub_action, NULL, action, action || '-' || sub_action)
                reason_code,
             cust_contact_name,
             cust_contact_phone,
             notes,document_Id
        FROM CANON_E193_DATA_MGMT_STG
       WHERE NVL (PROCESS_FLAG, 'N') = 'N'
       --TBD uncomment below ID check
       --AND id=958
       ;

   lv_category          VARCHAR2 (500);
   lv_par_cat_id        NUMBER;
   lv_cat_id            NUMBER;
   lv_jtf_note_flag     VARCHAR2 (5) := 'N';
   lv_h_ticket_id       NUMBER;
   lv_h_insert_count    NUMBER;
   lv_l_insert_count    NUMBER;
   lv_count             NUMBER;
   lv_line_id           NUMBER;
   lv_cat_desc          VARCHAR2 (1000);
   lv_dept_code         VARCHAR2 (1000);
   lv_dept_name         VARCHAR2 (1000);
   lv_crm_role_id       VARCHAR2 (1000);
   lv_crm_role_code     VARCHAR2 (1000);
   lv_crm_role_name     VARCHAR2 (1000);
   lv_psn_cd            VARCHAR2 (1000);
   lv_created_by        VARCHAR2 (1000);
   lv_cat_code          VARCHAR2 (1000);
   l_a_insert_count     NUMBER;
   l_assign_id          NUMBER;

   l_role_code          VARCHAR2 (1000);
   l_resource_name      VARCHAR2 (1000);
   l_owner_res_id       VARCHAR2 (1000);
   l_owner_role_id      VARCHAR2 (1000);
   l_owner_dept_code    VARCHAR2 (1000);
   lv_wrap_update_cnt   NUMBER;
   lv_line_id_cur       cs_ref_cur_typ;
   
   lv_email	VARCHAR2 (1000);
   lv_att_cnt NUMBER:=0;
BEGIN
p_out_status:='S';
   BEGIN
      SELECT cat_id
        INTO lv_par_cat_id
        FROM canon_e193_cs_categories
       WHERE cat_code = 'NONBILL' AND region = 'EAST_REGION';
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_par_cat_id := NULL;
   END;

   DBMS_OUTPUT.put_line ('lv_par_cat_id= ' || lv_par_cat_id);

   BEGIN
      SELECT cat_id
        INTO lv_cat_id
        FROM canon_e193_cs_categories
       WHERE cat_code = 'DATA MANAGEMENT' AND region = 'EAST_REGION';
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_cat_id := NULL;
   END;

   --Logic to get the Data Management ticket Owner
   BEGIN
      SELECT val.val2 role_code, val.val5 resource_name
        INTO l_role_code, l_resource_name
        FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
       WHERE     cd.cd_id = val.cd_id
             AND cd_name = 'CSR_E193_CATEGORY_OWNERS'
             AND val.val4 = 'DATA MANAGEMENT'
             AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                       AND NVL (val.end_date_active, SYSDATE);
   EXCEPTION
      WHEN OTHERS
      THEN
         l_role_code := NULL;
         l_resource_name := NULL;
   END;

   BEGIN
      SELECT asg.psn_cd role_resource_id,
             role_tp.org_func_role_tp_cd role_id,
             CANON_E193_CS_EVOLUTION_PKG.GET_CD_VAL (
                'CANON_E193_DEPT',
                role_tp.org_func_role_tp_nm,
                '',
                '',
                'val3')
                role_type_code
        INTO l_owner_res_id, l_owner_role_id, l_owner_dept_code
        FROM s21_psn psn,
             org_func_asg asg,
             toc,
             org_func_role_tp role_tp
       WHERE     psn.psn_cd = asg.psn_cd
             AND asg.toc_cd = toc.toc_cd
             AND toc.org_func_role_tp_cd = role_tp.org_func_role_tp_cd
             AND role_tp.org_func_role_tp_nm = l_role_code
            -- AND REPLACE (UPPER (psn.psn_last_nm || ',' || psn.psn_first_nm), ' ', '') =
            --        UPPER (REPLACE (l_resource_name, ' ', ''))
            AND UPPER (psn.psn_cd)=upper(l_resource_name)
             AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
	                           TO_CHAR (SYSDATE, 'YYYYMMDD')       
             AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
             AND nvl(psn.del_flg,'N')<>'Y'
             AND asg.glbl_cmpy_cd = toc.glbl_cmpy_cd
             AND asg.ezcancelflag = toc.ezcancelflag
             AND toc.glbl_cmpy_cd = role_tp.glbl_cmpy_cd
             AND toc.ezcancelflag = role_tp.ezcancelflag
             AND asg.glbl_cmpy_cd = psn.glbl_cmpy_cd
             AND asg.ezcancelflag = psn.ezcancelflag
             AND psn.ezcancelflag = 0
             AND psn.glbl_cmpy_cd = 'ADB'
             AND NVL (role_tp.actv_flg, 'Y') = 'Y';
   EXCEPTION
      WHEN OTHERS
      THEN
         l_owner_res_id := NULL;
         l_owner_role_id := NULL;
         l_owner_dept_code := NULL;
   END;

   DBMS_OUTPUT.put_line ('lv_cat_id= ' || lv_cat_id);

   FOR rec1 IN C1
   LOOP
      lv_att_cnt:=0;
      BEGIN
         SELECT DECODE (to_char(rec1.notes), NULL, 'N', 'Y')
           INTO lv_jtf_note_flag
           FROM DUAL;
      END;
      
      BEGIN
            SELECT eml_addr
            INTO lv_email
            FROM s21_psn psn
            WHERE psn_cd=rec1.created_by
            AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
	                           TO_CHAR (SYSDATE, 'YYYYMMDD')
	    AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
            AND ezcancelflag=0
            AND glbl_cmpy_cd='ADB';
            EXCEPTION WHEN OTHERS THEN
            lv_email:=NULL;
      END;

      CANON_E193_CS_EVOLUTION_PKG.INSERT_NONBILL (
         -- header attributes --
         lv_par_cat_id            --p_h_parent_cat_id          IN       NUMBER
         ,'UNASSIGNED'           --p_h_ticket_status          IN       VARCHAR2
         ,'N'                    --p_h_recurring              IN       VARCHAR2
         ,'N'                    --p_h_billing_issue          IN       VARCHAR2
         ,'81'                     --p_h_org_id                 IN       NUMBER
         ,rec1.customer_number     --p_h_cust_account_id        IN       NUMBER
         ,rec1.customer_name     --p_h_customer_name          IN       VARCHAR2
         ,rec1.customer_number   --p_h_customer_number        IN       VARCHAR2
         ,NULL                   --p_h_invoice_number         IN       VARCHAR2
         ,NULL                   --p_h_contract_number        IN       VARCHAR2
         ,NULL                   --p_h_contract_modifier      IN       VARCHAR2
         ,NULL                     --p_h_order_number           IN       NUMBER
         ,NULL                   --p_h_order_type             IN       VARCHAR2
         ,rec1.employee_name     --p_h_orig_name              IN       VARCHAR2
         ,rec1.employee_phone    --p_h_orig_ph_number         IN       VARCHAR2
         ,lv_email               --p_h_orig_e_mail            IN       VARCHAR2
         ,'internal'             --p_h_orig_type              IN       VARCHAR2
         ,rec1.cust_contact_name --p_h_cust_contact           IN       VARCHAR2
         ,rec1.cust_contact_phone --p_h_cust_ph_number         IN       VARCHAR2
         ,NULL                   --p_h_cust_e_mail            IN       VARCHAR2
         ,'N'       --p_h_jtf_notes_flag         IN       VARCHAR2
         ,NULL                   --p_h_attribute1             IN       VARCHAR2
         ,NULL                   --p_h_attribute2             IN       VARCHAR2
         ,NULL                   --p_h_attribute3             IN       VARCHAR2
         ,NULL                   --p_h_attribute4             IN       VARCHAR2
         ,'EAST'                 --p_h_attribute5             IN       VARCHAR2
         ,NULL                   --p_h_attribute6             IN       VARCHAR2
         ,NULL                   --p_h_attribute7             IN       VARCHAR2
         ,NULL                   --p_h_attribute8             IN       VARCHAR2
         ,NULL                   --p_h_attribute9             IN       VARCHAR2
         ,NULL                   --p_h_attribute10            IN       VARCHAR2
         ,NULL                   --p_h_attribute11            IN       VARCHAR2
         ,NULL                   --p_h_attribute12            IN       VARCHAR2
         ,NULL                   --p_h_attribute13            IN       VARCHAR2
         ,NULL                   --p_h_attribute14            IN       VARCHAR2
         ,NULL                   --p_h_attribute15            IN       VARCHAR2
         ,l_owner_role_id --'CC002'--p_h_owner_role_id          IN       VARCHAR2
         ,l_owner_res_id --'Q08693'--p_h_owner_res_id           IN       VARCHAR2
         ,l_owner_dept_code --'CSR_E193_CUSTOMER_SERVICE'--p_h_owner_dept_code        IN       VARCHAR2
         ,rec1.created_by        --p_h_created_by             IN       VARCHAR2
         ,NULL                   --p_h_created_by_role_id     IN       VARCHAR2
         ,rec1.created_by             --NULL--p_h_created_by_res_id      IN       VARCHAR2
         ,NULL                   --p_h_created_by_dept_code   IN       VARCHAR2
         -- line attributes --
         ,lv_cat_id               -- p_l_cat_id                 IN       NUMBER
         ,'UNASSIGNED'           --p_l_line_status            IN       VARCHAR2
         ,'NORMAL'               --p_l_severity               IN       VARCHAR2
         ,'CSR_E193_NONBILL',--p_l_reason_code            IN       VARCHAR2
          rec1.reason_code ,       --p_l_reason                 IN       VARCHAR2
         lv_jtf_note_flag       --p_l_jtf_notes_flag         IN       VARCHAR2
         ,NULL                   --p_l_attribute1             IN       VARCHAR2
         ,NULL                   --p_l_attribute2             IN       VARCHAR2
         ,NULL                   --p_l_attribute3             IN       VARCHAR2
         ,NULL                   --p_l_attribute4             IN       VARCHAR2
         ,NULL                  --p_l_attribute5             IN       VARCHAR2
          ,rec1.created_by      --p_l_created_by             IN       VARCHAR2
         ,rec1.notes             --p_jtf_notes_detail         IN       VARCHAR2
         ,'N'                      --p_send_email_flag          IN     VARCHAR2
         ,lv_h_ticket_id
         ,lv_h_insert_count
         ,lv_l_insert_count);

      DBMS_OUTPUT.put_line ('lv_h_insert_count= ' || lv_h_insert_count);
      DBMS_OUTPUT.put_line ('lv_l_insert_count= ' || lv_l_insert_count);
      DBMS_OUTPUT.put_line ('lv_h_ticket_id= ' || lv_h_ticket_id);

      IF     lv_h_insert_count <> -1
         AND lv_l_insert_count <> -1
         AND lv_h_ticket_id IS NOT NULL
      THEN
         lv_count := lv_count + 1;
         
         --Create Assignment for the ticket
         BEGIN
            SELECT l.line_id,
                   cat.cat_desc,
                   cat.crm_role_type_code dept_code,
                   lookup_val.val3 dept_name,
                   cat.crm_role_id,
                   cat.crm_role_code,
                   cat.crm_role_name,
                   TO_CHAR (cat.crm_resource_id) resource_id,
                   l.created_by,
                   cat.cat_code
              INTO lv_line_id,
                   lv_cat_desc,
                   lv_dept_code,
                   lv_dept_name,
                   lv_crm_role_id,
                   lv_crm_role_code,
                   lv_crm_role_name,
                   lv_psn_cd,
                   lv_created_by,
                   lv_cat_code
              FROM canon_e193_cs_lines l,
                   canon_e193_cs_categories cat,
                   canon_s21_cd_tbl lookup,
                   canon_s21_cd_val_tbl lookup_val
             WHERE     parent_cat_id <> -1
                   AND l.ticket_id = lv_h_ticket_id
                   AND l.line_status = 'UNASSIGNED'
                   AND l.cat_id = cat.cat_id
                   AND lookup_val.val1 = cat.crm_role_type_code
                   AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                   AND lookup.cd_id = lookup_val.cd_id
                   AND NOT EXISTS
                          (SELECT 'x'
                             FROM canon_e193_cs_assignments assign
                            WHERE assign.line_id = l.line_id);
         EXCEPTION
            WHEN OTHERS
            THEN
               DBMS_OUTPUT.put_line (
                  'In Exception lv_line_id:=NULL ' || SQLERRM);
               lv_line_id := NULL;
         END;

         IF lv_line_id IS NOT NULL
         THEN
            CANON_E193_CS_EVOLUTION_PKG.ADD_ASSIGNMENTS (lv_line_id,
                                                         lv_dept_code,
                                                         lv_crm_role_id,
                                                         lv_psn_cd,
                                                         lv_created_by,
                                                         l_assign_id,
                                                         l_a_insert_count);

            IF l_a_insert_count >= 1
            THEN
               CANON_E193_CS_EVOLUTION_PKG.TICKET_SUMMARY_CALL_WRAP (
                  '',
                  lv_h_ticket_id,
                  '0',
                  'ASSIGNED',
                  rec1.created_by,
                  lv_wrap_update_cnt,
                  lv_line_id_cur);

               IF lv_wrap_update_cnt >= 1
               THEN
                  UPDATE CANON_E193_DATA_MGMT_STG
                     SET ticket_number = lv_h_ticket_id,
                         process_flag = 'S',
                         process_message = 'Success',
                         last_update_date=sysdate,
                         last_updated_by='-1'
                   WHERE id = rec1.id;
               ELSE
                  UPDATE CANON_E193_DATA_MGMT_STG
                     SET ticket_number = lv_h_ticket_id,
                         process_flag = 'W',
                         process_message = 'Unable to perform the call wrap',
                         last_update_date=sysdate,
                         last_updated_by='-1'
                   WHERE id = rec1.id;

                  lv_count := lv_count + 1;
               END IF;
            ELSE
               UPDATE CANON_E193_DATA_MGMT_STG
                  SET ticket_number = lv_h_ticket_id,
                  	process_flag = 'W',
                      process_message = 'Unable to create assignment',
                         last_update_date=sysdate,
                         last_updated_by='-1'
                WHERE id = rec1.id;

               lv_count := lv_count + 1;
            END IF;
         ELSE
            UPDATE CANON_E193_DATA_MGMT_STG
               SET ticket_number = lv_h_ticket_id,
                    process_flag = 'W',
                   process_message = 'Ticket Line does not exist. Unable to create assignments',
                         last_update_date=sysdate,
                         last_updated_by='-1'
             WHERE id = rec1.id;

            lv_count := lv_count + 1;
         END IF;
      ELSE
         UPDATE CANON_E193_DATA_MGMT_STG
            SET process_flag = 'E',
                process_message = 'Error while creating ticket',
                         last_update_date=sysdate,
                         last_updated_by='-1'
          WHERE id = rec1.id;

         lv_count := lv_count + 1;
      END IF;
 
      BEGIN
      Select count(1) 
      INTO lv_att_cnt
      FROM canon_e193_data_mgmt_att att
      WHERE att.document_id=rec1.document_Id;
      EXCEPTION WHEN OTHERS
      THEN
      lv_att_cnt:=0;
      END;
      
      IF lv_att_cnt>0
      THEN
      UPDATE canon_e193_data_mgmt_att
      SET ticket_number=lv_h_ticket_id,
      last_update_date=sysdate
      WHERE document_id=rec1.document_Id;
      END IF;
      
      IF lv_count = 50
      THEN
         COMMIT;
         lv_count := 0;
      END IF;      
      
   END LOOP;

   COMMIT;
EXCEPTION
WHEN OTHERS THEN
p_out_status:='E';   
END CREATE_TICKET;

/*******************************************************************************************
 Procedure Name: GET_MESSAGE_DETAILS
 Description: Procedure to message details from CANON_E193_DM_EMAIL_STG table
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

/*PROCEDURE GET_ATTACH_DETAILS ( p_attach_cur  OUT   cs_ref_cur_typ)
IS

lv_id  NUMBER;
lv_doc_id NUMBER;
  
BEGIN
	OPEN p_attach_cur FOR
	SELECT stg.ticket_number,
       att.file_type,
       att.file_name,
       att.file_path
  FROM CANON_E193_DATA_MGMT_ATT att, CANON_E193_DATA_MGMT_STG stg
 WHERE     NVL (att.process_flag, 'N') = 'N'
       AND stg.ticket_number IS NOT NULL
       AND att.document_id = stg.document_id;
	

EXCEPTION
WHEN OTHERS THEN
OPEN p_attach_cur FOR
         SELECT NULL
           FROM DUAL
          WHERE ROWNUM = 0;
   
END GET_ATTACH_DETAILS;*/

/*******************************************************************************************
 Procedure Name: CREATE_QUICK_TICKET
 Description: Procedure to create DM Quick ticket in Customer Care
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

PROCEDURE CREATE_QUICK_TICKET (p_out_status OUT VARCHAR2)
IS
   CURSOR C1
   IS
      SELECT id,
             subject,
             body,
             from_email
            FROM CANON_E193_DM_EMAIL_STG
       WHERE NVL (PROCESS_FLAG, 'N') = 'N';

   lv_category          VARCHAR2 (500);
   lv_par_cat_id        NUMBER;
   lv_cat_id            NUMBER;
   lv_jtf_note_flag     VARCHAR2 (5) := 'N';
   lv_h_ticket_id       NUMBER;
   lv_h_insert_count    NUMBER;
   lv_l_insert_count    NUMBER;
   lv_count             NUMBER;
   lv_line_id           NUMBER;
   lv_cat_desc          VARCHAR2 (1000);
   lv_dept_code         VARCHAR2 (1000);
   lv_dept_name         VARCHAR2 (1000);
   lv_crm_role_id       VARCHAR2 (1000);
   lv_crm_role_code     VARCHAR2 (1000);
   lv_crm_role_name     VARCHAR2 (1000);
   lv_psn_cd            VARCHAR2 (1000);
   lv_created_by        VARCHAR2 (1000);
   lv_cat_code          VARCHAR2 (1000);
   l_a_insert_count     NUMBER;
   l_assign_id          NUMBER;

   l_role_code          VARCHAR2 (1000);
   l_resource_name      VARCHAR2 (1000);
   l_owner_res_id       VARCHAR2 (1000);
   l_owner_role_id      VARCHAR2 (1000);
   l_owner_dept_code    VARCHAR2 (1000);
   lv_wrap_update_cnt   NUMBER;
   lv_line_id_cur       cs_ref_cur_typ;
   
   lv_orig_name   VARCHAR2(500);
   lv_orig_ph_num VARCHAR2(50);
   lv_user_id VARCHAR2(200);
   l_reason_code  VARCHAR2(500);
BEGIN
p_out_status:='S';
   BEGIN
      SELECT cat_id
        INTO lv_par_cat_id
        FROM canon_e193_cs_categories
       WHERE cat_code = 'NONBILL' AND region = 'EAST_REGION';
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_par_cat_id := NULL;
   END;

   DBMS_OUTPUT.put_line ('lv_par_cat_id= ' || lv_par_cat_id);

   BEGIN
      SELECT cat_id
        INTO lv_cat_id
        FROM canon_e193_cs_categories
       WHERE cat_code = 'DATA MANAGEMENT' AND region = 'EAST_REGION';
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_cat_id := NULL;
   END;

   --Logic to get the Data Management ticket Owner
   
    BEGIN
         SELECT val.val2 role_code, val.val5 resource_name
           INTO l_role_code, l_resource_name
           FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
          WHERE     cd.cd_id = val.cd_id
                AND cd_name = 'CSR_E193_CATEGORY_OWNERS'
                AND val.val4 = 'DATA MANAGEMENT'
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                       AND NVL (val.end_date_active, SYSDATE);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_role_code := NULL;
            l_resource_name := NULL;
      END;
   
      BEGIN
         SELECT asg.psn_cd role_resource_id,
                role_tp.org_func_role_tp_cd role_id,
                CANON_E193_CS_EVOLUTION_PKG.GET_CD_VAL (
                   'CANON_E193_DEPT',
                   role_tp.org_func_role_tp_nm,
                   '',
                   '',
                   'val3')
                   role_type_code
           INTO l_owner_res_id, l_owner_role_id, l_owner_dept_code
           FROM s21_psn psn,
                org_func_asg asg,
                toc,
                org_func_role_tp role_tp
          WHERE psn.psn_cd = asg.psn_cd
                AND asg.toc_cd = toc.toc_cd
                AND toc.org_func_role_tp_cd = role_tp.org_func_role_tp_cd
                AND role_tp.org_func_role_tp_nm = l_role_code
               -- AND REPLACE (UPPER (psn.psn_last_nm || ',' || psn.psn_first_nm), ' ', '') =
               --        UPPER (REPLACE (l_resource_name, ' ', ''))
               AND UPPER (psn.psn_cd)=upper(l_resource_name)
                AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
                AND nvl(psn.del_flg,'N')<>'Y'
                AND asg.glbl_cmpy_cd = toc.glbl_cmpy_cd
                AND asg.ezcancelflag = toc.ezcancelflag
                AND toc.glbl_cmpy_cd = role_tp.glbl_cmpy_cd
                AND toc.ezcancelflag = role_tp.ezcancelflag
                AND asg.glbl_cmpy_cd = psn.glbl_cmpy_cd
                AND asg.ezcancelflag = psn.ezcancelflag
                AND psn.ezcancelflag = 0
                AND psn.glbl_cmpy_cd = 'ADB'
                AND NVL (role_tp.actv_flg, 'Y') = 'Y';
      EXCEPTION
         WHEN OTHERS
         THEN
            l_owner_res_id := NULL;
            l_owner_role_id := NULL;
            l_owner_dept_code := NULL;
      END;
      
      dbms_output.put_line('l_owner_res_id0 ='||l_owner_res_id);
      dbms_output.put_line('l_owner_role_id0 ='||l_owner_role_id);
dbms_output.put_line('l_owner_dept_code0 ='||l_owner_dept_code);

  --Get the default reason code 
     BEGIN
         SELECT val.val1 
           INTO l_reason_code
           FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
          WHERE     cd.cd_id = val.cd_id
                AND cd_name = 'CANON_E193_DM_QUICK_TKT_RSN'
          AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                       AND NVL (val.end_date_active, SYSDATE)  ;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_reason_code := NULL;
   END;


   DBMS_OUTPUT.put_line ('lv_cat_id= ' || lv_cat_id);

   FOR rec1 IN C1
   LOOP
      BEGIN
         SELECT DECODE (to_char(rec1.body), NULL, 'N', 'Y')
           INTO lv_jtf_note_flag
           FROM DUAL;
      END;
      
      BEGIN
      SELECT psn_cd,psn_last_nm||','||psn_first_nm,nvl(work_tel_num,ofc_tel_num)
      INTO lv_user_id,lv_orig_name,lv_orig_ph_num
      FROM S21_PSN psn
      WHERE upper(eml_addr)=upper(rec1.from_email)
      AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                             TO_CHAR (SYSDATE, 'YYYYMMDD')
      AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
      AND ezcancelflag=0
      AND glbl_cmpy_cd='ADB';
      EXCEPTION WHEN OTHERS THEN
      lv_user_id:='-1';
      lv_orig_name:=NULL;
      lv_orig_ph_num:=NULL;
      END;

      CANON_E193_CS_EVOLUTION_PKG.INSERT_NONBILL (
         -- header attributes --
         lv_par_cat_id            --p_h_parent_cat_id          IN       NUMBER
         ,'UNASSIGNED'           --p_h_ticket_status          IN       VARCHAR2
         ,'N'                    --p_h_recurring              IN       VARCHAR2
         ,'N'                    --p_h_billing_issue          IN       VARCHAR2
         ,'81'                     --p_h_org_id                 IN       NUMBER
         ,'-1'     --p_h_cust_account_id        IN       NUMBER
         ,NULL     --p_h_customer_name          IN       VARCHAR2
         ,'-1'--rec1.customer_number   --p_h_customer_number        IN       VARCHAR2
         ,NULL                   --p_h_invoice_number         IN       VARCHAR2
         ,NULL                   --p_h_contract_number        IN       VARCHAR2
         ,NULL                   --p_h_contract_modifier      IN       VARCHAR2
         ,NULL                     --p_h_order_number           IN       NUMBER
         ,NULL                   --p_h_order_type             IN       VARCHAR2
         ,lv_orig_name     --p_h_orig_name              IN       VARCHAR2
         ,lv_orig_ph_num    --p_h_orig_ph_number         IN       VARCHAR2
         ,rec1.from_email        --p_h_orig_e_mail            IN       VARCHAR2
         ,''             --p_h_orig_type              IN       VARCHAR2
         ,lv_orig_name --p_h_cust_contact           IN       VARCHAR2
         ,lv_orig_ph_num --p_h_cust_ph_number         IN       VARCHAR2
         ,rec1.from_email                   --p_h_cust_e_mail            IN       VARCHAR2
         ,'N'       --p_h_jtf_notes_flag         IN       VARCHAR2
         ,NULL                   --p_h_attribute1             IN       VARCHAR2
         ,NULL                   --p_h_attribute2             IN       VARCHAR2
         ,NULL                   --p_h_attribute3             IN       VARCHAR2
         ,NULL                   --p_h_attribute4             IN       VARCHAR2
         ,'EAST'                 --p_h_attribute5             IN       VARCHAR2
         ,NULL                   --p_h_attribute6             IN       VARCHAR2
         ,NULL                   --p_h_attribute7             IN       VARCHAR2
         ,NULL                   --p_h_attribute8             IN       VARCHAR2
         ,NULL                   --p_h_attribute9             IN       VARCHAR2
         ,NULL                   --p_h_attribute10            IN       VARCHAR2
         ,NULL                   --p_h_attribute11            IN       VARCHAR2
         ,NULL                   --p_h_attribute12            IN       VARCHAR2
         ,NULL                   --p_h_attribute13            IN       VARCHAR2
         ,NULL                   --p_h_attribute14            IN       VARCHAR2
         ,NULL                   --p_h_attribute15            IN       VARCHAR2
         ,l_owner_role_id --'CC002'--p_h_owner_role_id          IN       VARCHAR2
         ,l_owner_res_id --'Q08693'--p_h_owner_res_id           IN       VARCHAR2
         ,l_owner_dept_code --'CSR_E193_CUSTOMER_SERVICE'--p_h_owner_dept_code        IN       VARCHAR2
         ,lv_user_id        --p_h_created_by             IN       VARCHAR2
         ,NULL                   --p_h_created_by_role_id     IN       VARCHAR2
         ,lv_user_id             --NULL--p_h_created_by_res_id      IN       VARCHAR2
         ,NULL                   --p_h_created_by_dept_code   IN       VARCHAR2
         -- line attributes --
         ,lv_cat_id               -- p_l_cat_id                 IN       NUMBER
         ,'UNASSIGNED'           --p_l_line_status            IN       VARCHAR2
         ,'NORMAL'               --p_l_severity               IN       VARCHAR2
         ,'CSR_E193_NONBILL'--p_l_reason_code            IN       VARCHAR2
         , l_reason_code        --p_l_reason                 IN       VARCHAR2
         ,lv_jtf_note_flag       --p_l_jtf_notes_flag         IN       VARCHAR2
         ,NULL                   --p_l_attribute1             IN       VARCHAR2
         ,NULL                   --p_l_attribute2             IN       VARCHAR2
         ,NULL                   --p_l_attribute3             IN       VARCHAR2
         ,NULL                   --p_l_attribute4             IN       VARCHAR2
         ,NULL                  --p_l_attribute5             IN       VARCHAR2
          ,lv_user_id     --p_l_created_by             IN       VARCHAR2
         , CHR (10)||'Subject: '||rec1.subject||chr(10)|| rec1.body             --p_jtf_notes_detail         IN       VARCHAR2
         ,'N'                      --p_send_email_flag          IN     VARCHAR2
         ,lv_h_ticket_id
         ,lv_h_insert_count
         ,lv_l_insert_count);

      DBMS_OUTPUT.put_line ('lv_h_insert_count= ' || lv_h_insert_count);
      DBMS_OUTPUT.put_line ('lv_l_insert_count= ' || lv_l_insert_count);
      DBMS_OUTPUT.put_line ('lv_h_ticket_id= ' || lv_h_ticket_id);

      IF     lv_h_insert_count <> -1
         AND lv_l_insert_count <> -1
         AND lv_h_ticket_id IS NOT NULL
      THEN
         lv_count := lv_count + 1;
	UPDATE CANON_E193_DM_EMAIL_STG
                     SET ticket_number = lv_h_ticket_id,
                         process_flag = 'P',
                         process_message = 'Processing',
                         last_update_date=SYSDATE
                   WHERE id = rec1.id;
                   COMMIT;
         --Create Assignment for the ticket
         BEGIN
            SELECT l.line_id,
                   cat.cat_desc,
                   cat.crm_role_type_code dept_code,
                   lookup_val.val3 dept_name,
                   cat.crm_role_id,
                   cat.crm_role_code,
                   cat.crm_role_name,
                   TO_CHAR (cat.crm_resource_id) resource_id,
                   l.created_by,
                   cat.cat_code
              INTO lv_line_id,
                   lv_cat_desc,
                   lv_dept_code,
                   lv_dept_name,
                   lv_crm_role_id,
                   lv_crm_role_code,
                   lv_crm_role_name,
                   lv_psn_cd,
                   lv_created_by,
                   lv_cat_code
              FROM canon_e193_cs_lines l,
                   canon_e193_cs_categories cat,
                   canon_s21_cd_tbl lookup,
                   canon_s21_cd_val_tbl lookup_val
             WHERE     parent_cat_id <> -1
                   AND l.ticket_id = lv_h_ticket_id
                   AND l.line_status = 'UNASSIGNED'
                   AND l.cat_id = cat.cat_id
                   AND lookup_val.val1 = cat.crm_role_type_code
                   AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                   AND lookup.cd_id = lookup_val.cd_id
                   AND NOT EXISTS
                          (SELECT 'x'
                             FROM canon_e193_cs_assignments assign
                            WHERE assign.line_id = l.line_id);
         EXCEPTION
            WHEN OTHERS
            THEN
               DBMS_OUTPUT.put_line (
                  'In Exception lv_line_id:=NULL ' || SQLERRM);
               lv_line_id := NULL;
         END;

         IF lv_line_id IS NOT NULL
         THEN
            CANON_E193_CS_EVOLUTION_PKG.ADD_ASSIGNMENTS (lv_line_id,
                                                         lv_dept_code,
                                                         lv_crm_role_id,
                                                         lv_psn_cd,
                                                         lv_created_by,
                                                         l_assign_id,
                                                         l_a_insert_count);

            IF l_a_insert_count >= 1
            THEN
               CANON_E193_CS_EVOLUTION_PKG.TICKET_SUMMARY_CALL_WRAP (
                  '',
                  lv_h_ticket_id,
                  '0',
                  'ASSIGNED',
                  lv_user_id,
                  lv_wrap_update_cnt,
                  lv_line_id_cur);

               IF lv_wrap_update_cnt >= 1
               THEN
                  UPDATE CANON_E193_DM_EMAIL_STG
                     SET process_flag = 'S',
                         process_message = 'Success',
                         last_update_date=SYSDATE
                   WHERE id = rec1.id;
               ELSE
                  UPDATE CANON_E193_DM_EMAIL_STG
                     SET process_flag = 'W',
                         process_message = 'Unable to perform the call wrap',
                         last_update_date=SYSDATE
                   WHERE id = rec1.id;

                  lv_count := lv_count + 1;
               END IF;
            ELSE
               UPDATE CANON_E193_DM_EMAIL_STG
                  SET process_flag = 'W',
                      process_message = 'Unable to create assignment',
                      last_update_date=SYSDATE
                WHERE id = rec1.id;

               lv_count := lv_count + 1;
            END IF;
         ELSE
            UPDATE CANON_E193_DM_EMAIL_STG
               SET process_flag = 'W',
                   process_message = 'Unable to create assignments',
                   last_update_date=SYSDATE
             WHERE id = rec1.id;

            lv_count := lv_count + 1;
         END IF;
      ELSE
         UPDATE CANON_E193_DM_EMAIL_STG
            SET process_flag = 'E',
                process_message = 'Error while creating assignment',
                last_update_date=SYSDATE
          WHERE id = rec1.id;

         lv_count := lv_count + 1;
      END IF;

      IF lv_count = 50
      THEN
         COMMIT;
         lv_count := 0;
      END IF;
   END LOOP;
    COMMIT;
EXCEPTION
WHEN OTHERS THEN
p_out_status:='E';
  
END CREATE_QUICK_TICKET;

/*******************************************************************************************
 Procedure Name: INSERT_DM_EMAIL_STG
 Description: Procedure to insert data into CANON_E193_DM_EMAIL_STG table
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

PROCEDURE INSERT_DM_EMAIL_STG ( p_from_email IN VARCHAR2
			       ,p_subject IN VARCHAR2
			       ,p_body IN CLOB
			       ,p_message_id IN VARCHAR2
			       ,p_received_date IN DATE
			       ,o_id OUT NUMBER
			       ,o_status OUT VARCHAR2)
IS

lv_id  NUMBER;
  
BEGIN

	lv_id:=CANON_E193_DM_EMAIL_S.NEXTVAL;
	
	INSERT INTO CANON_E193_DM_EMAIL_STG
	(id,subject ,body ,from_email ,creation_date,last_update_date,process_flag,message_id,received_date)
	VALUES(lv_id,p_subject,p_body,p_from_email,SYSDATE,SYSDATE,'N',p_message_id,p_received_date);
	COMMIT;
o_id:=	lv_id;
o_status:='S';

EXCEPTION
WHEN OTHERS THEN
o_id:=NULL;
o_status:='E';
   
END INSERT_DM_EMAIL_STG;

/*******************************************************************************************
 Procedure Name: INSERT_DM_EMAIL_STG
 Description: Procedure to insert data into CANON_E193_DM_EMAIL_STG table
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

PROCEDURE INSERT_E193_DM_ATTCHMENT ( p_id IN NUMBER,
				p_file_type IN VARCHAR2
			       ,p_file_name IN VARCHAR2
			       ,p_attachment IN BLOB
			       ,o_status OUT VARCHAR2)
IS

lv_id  NUMBER;
lv_doc_id NUMBER;
lv_doc_id1 NUMBER;
  
BEGIN
dbms_output.put_line('Inside INSERT_E193_DM_ATTCHMENT');
dbms_output.put_line('p_id ='||p_id);
	lv_doc_id :=CANON_E193_DM_ATTACH_S.NEXTVAL;
dbms_output.put_line('lv_doc_id ='||lv_doc_id);
	UPDATE CANON_E193_DM_EMAIL_STG
	SET document_id=lv_doc_id
	WHERE id=p_id
	AND document_id is null;
	
	COMMIT;
	
	BEGIN
	SELECT document_id
	INTO lv_doc_id1
	FROM CANON_E193_DM_EMAIL_STG
	WHERE id=p_id;
	EXCEPTION WHEN OTHERS
	THEN
	lv_doc_id1:=NULL;
	END;
	dbms_output.put_line('lv_doc_id1 ='||lv_doc_id1);
	IF lv_doc_id1 IS NOT NULL
	THEN
	lv_id:=CANON_E193_DM_ATTACHMENT_S.NEXTVAL;
	
	INSERT INTO CANON_E193_DM_ATTACHMENT
	(ID,DOCUMENT_ID ,FILE_TYPE ,FILE_NAME ,ATTACHMENT,CREATION_DATE)
	VALUES(lv_id,lv_doc_id1,p_file_type,p_file_name,p_attachment,SYSDATE);
	COMMIT;
	ELSE
	o_status:='E';
	END IF;
	
o_status:='S';

EXCEPTION
WHEN OTHERS THEN
o_status:='E';
   
END INSERT_E193_DM_ATTCHMENT;

/*******************************************************************************************
 Procedure Name: INSERT_DM_EMAIL_STG
 Description: Procedure to insert data into CANON_E193_DM_EMAIL_STG table
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

PROCEDURE INSERT_E193_DATA_MGMT_ATT ( p_id IN NUMBER,
				p_file_type IN VARCHAR2
			       ,p_file_name IN VARCHAR2
			       ,p_file_path IN VARCHAR2
			       ,o_status OUT VARCHAR2)
IS

lv_id  NUMBER;
lv_doc_id NUMBER;
lv_doc_id1 NUMBER;
  
BEGIN
dbms_output.put_line('Inside INSERT_E193_DATA_MGMT_ATT');
dbms_output.put_line('p_id ='||p_id);
	lv_doc_id :=CANON_E193_DM_ATTACH_S.NEXTVAL;
dbms_output.put_line('lv_doc_id ='||lv_doc_id);
	UPDATE canon_e193_data_mgmt_stg
	SET document_id=lv_doc_id
	WHERE id=p_id
	AND document_id is null;
	
	COMMIT;
	
	BEGIN
	SELECT document_id
	INTO lv_doc_id1
	FROM canon_e193_data_mgmt_stg
	WHERE id=p_id;
	EXCEPTION WHEN OTHERS
	THEN
	lv_doc_id1:=NULL;
	END;
	dbms_output.put_line('lv_doc_id1 ='||lv_doc_id1);
	IF lv_doc_id1 IS NOT NULL
	THEN
	lv_id:=CANON_E193_DATA_MGMT_ATT_S.NEXTVAL;
	
	INSERT INTO CANON_E193_DATA_MGMT_ATT
	(id,document_id ,file_type ,file_name ,file_path,active_flag,creation_date,last_update_date)
	VALUES(lv_id,lv_doc_id1,p_file_type,p_file_name,p_file_path,'Y',SYSDATE,SYSDATE);
	COMMIT;
	ELSE
	o_status:='E';
	END IF;
	
o_status:='S';

EXCEPTION
WHEN OTHERS THEN
o_status:='E';
   
END INSERT_E193_DATA_MGMT_ATT;

/*******************************************************************************************
 Procedure Name: GET_MESSAGE_DETAILS
 Description: Procedure to message details from CANON_E193_DM_EMAIL_STG table
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

PROCEDURE GET_MESSAGE_DETAILS ( p_message_id IN VARCHAR2
			       ,p_message_cur  OUT   cs_ref_cur_typ)
IS

lv_id  NUMBER;
lv_doc_id NUMBER;
  
BEGIN
	OPEN p_message_cur FOR
	SELECT from_email,subject,received_date
	FROM canon_e193_dm_email_stg
	WHERE message_id=p_message_id;
	

EXCEPTION
WHEN OTHERS THEN
OPEN p_message_cur FOR
         SELECT NULL
           FROM DUAL
          WHERE ROWNUM = 0;
   
END GET_MESSAGE_DETAILS;

/*******************************************************************************************
 Procedure Name: INSERT_DM_EMAIL_STG
 Description: Procedure to insert data into CANON_E193_DATA_MGMT_STG table
 Input Parameters: None
                               
 Output Parameters: p_out_status
 
*******************************************************************************************/ 

PROCEDURE INSERT_DATA_MGMT_STG (p_action               IN     VARCHAR2,
                                p_sub_action           IN     VARCHAR2,
                                p_created_by           IN     VARCHAR2,
                                p_last_updated_by      IN     VARCHAR2,
                                p_employee_name        IN     VARCHAR2,
                                p_employee_phone       IN     VARCHAR2,
                                p_sub_company          IN     VARCHAR2,
                                p_department           IN     VARCHAR2,
                                p_customer_number      IN     VARCHAR2,
                                p_customer_name        IN     VARCHAR2,
                                p_cust_contact_name    IN     VARCHAR2,
                                p_cust_contact_phone   IN     VARCHAR2,
                                p_notes                IN     VARCHAR2,
                                p_attribute1           IN     VARCHAR2,
                                p_attribute2           IN     VARCHAR2,
                                p_attribute3           IN     VARCHAR2,
                                p_attribute4           IN     VARCHAR2,
                                p_attribute5           IN     VARCHAR2,
                                p_attribute6           IN     VARCHAR2,
                                p_attribute7           IN     VARCHAR2,
                                p_attribute8           IN     VARCHAR2,
                                p_attribute9           IN     VARCHAR2,
                                p_attribute10          IN     VARCHAR2,
                                p_file_type            IN     VARCHAR2,
                                p_file_name            IN     VARCHAR2,
                                p_file_path           IN     VARCHAR2,
                                o_id                      OUT NUMBER,
                                o_status                  OUT VARCHAR2,
                                o_message                 OUT VARCHAR2)
IS
   lv_id       NUMBER;
   lv_status   VARCHAR2 (100);
BEGIN
   lv_id := CANON_E193_DATA_MGMT_STG_S.NEXTVAL;


   BEGIN
      INSERT INTO CANON_E193_DATA_MGMT_STG (id,
                                            action,
                                            sub_action,
                                            created_by,
                                            creation_date,
                                            last_updated_by,
                                            last_update_date,
                                            employee_name,
                                            employee_phone,
                                            sub_company,
                                            department,
                                            customer_number,
                                            customer_name,
                                            cust_contact_name,
                                            cust_contact_phone,
                                            process_flag,
                                            process_message,
                                            ticket_number,
                                            notes,
                                            attribute1,
                                            attribute2,
                                            attribute3,
                                            attribute4,
                                            attribute5,
                                            attribute6,
                                            attribute7,
                                            attribute8,
                                            attribute9,
                                            attribute10)
           VALUES (lv_id,
                   p_action,
                   p_sub_action,
                   p_created_by,
                   SYSDATE,
                   p_last_updated_by,
                   SYSDATE,
                   p_employee_name,
                   p_employee_phone,
                   p_sub_company,
                   p_department,
                   p_customer_number,
                   p_customer_name,
                   p_cust_contact_name,
                   p_cust_contact_phone,
                   'N',
                   NULL,
                   NULL,
                   p_notes,
                   p_attribute1,
                   p_attribute2,
                   p_attribute3,
                   p_attribute4,
                   p_attribute5,
                   p_attribute6,
                   p_attribute7,
                   p_attribute8,
                   p_attribute9,
                   p_attribute10);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         o_status := 'E';
         o_message := 'Error while inserting the record. ';
   END;

   o_id := lv_id;
   o_status := 'S';
   o_message := 'Record inserted successfully. ';

   IF p_file_path IS NOT NULL AND o_status = 'S'
   THEN
   dbms_output.put_line('Before calling Attachment proc');
      INSERT_E193_DATA_MGMT_ATT (lv_id,
                                p_file_type,
                                p_file_name,
                                p_file_path,
                                lv_status);

      o_status := lv_status;

      IF o_status = 'E'
      THEN
         o_message := o_message || 'Error while inserting the attachment. ';
      END IF;
   END IF;
   
EXCEPTION
   WHEN OTHERS
   THEN
      o_id := NULL;
      o_status := 'E';
      o_message := 'Error ' || SQLERRM;
END INSERT_DATA_MGMT_STG;

END CANON_E193_DATAMGMT_PKG;
/
Show Err
