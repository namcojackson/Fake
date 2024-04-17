CREATE OR REPLACE PACKAGE CANON_E193_CSQUICK_SQLS_PKG
IS

/*******************************************************************************
Program Name        : CANON_E193_CS_EVOLUTION_PKG.sql
Functional Overview : All SQL's used by E193 Quick Ticket screen.
Table modified      : None
Comments            :
********************************************************************************/
 
g_glbl_cmpy_cd   VARCHAR2 (10) := 'ADB';
g_cancel_flg     VARCHAR2 (10) := '0';
c_unassigned_status       CONSTANT VARCHAR2 (30) := 'UNASSIGNED';
c_active_status           CONSTANT VARCHAR2 (30) := 'ACTIVE';
c_close_status            CONSTANT VARCHAR2 (30) := 'CLOSE';
c_l_assigned_status       CONSTANT VARCHAR2 (30) := 'ASSIGNED';

TYPE QUICK_REF_CUR_TYP IS REF CURSOR;
 
FUNCTION GET_CUST_SERVICE_GROUP( p_res_id  IN   VARCHAR2) 
RETURN VARCHAR2;

PROCEDURE GET_ACCOUNT_DETAILS(
    p_org_id        IN      NUMBER,
    p_value_type    IN      VARCHAR2,
    p_value         IN      VARCHAR2,
    p_acct_cur      OUT     quick_ref_cur_typ
);

PROCEDURE GET_SERIAL_ACCT_DETAILS (p_ser_num    IN     VARCHAR2,
                                      p_account_cur      OUT quick_ref_cur_typ);

PROCEDURE GET_REASON_CODES(p_reason_cur      OUT     quick_ref_cur_typ);

PROCEDURE GET_CAT_CODE(
    p_region     IN      VARCHAR2,
    p_code_cur   OUT     quick_ref_cur_typ);
    
FUNCTION GET_LINE_ID(p_ticket_id   IN  NUMBER)
RETURN NUMBER;    

PROCEDURE UPDATE_QUICK_TICKET(
    p_ticket_no     IN      NUMBER,
    p_org_id        IN      NUMBER,
    p_line_id       IN      NUMBER,
    p_severity      IN      VARCHAR2,
    p_status        IN      VARCHAR2,
    p_updated_by    IN      VARCHAR2,
    p_notes         IN      VARCHAR2,
    p_ret_val       OUT     NUMBER
    );
    
 PROCEDURE GET_QT_SEARCH_TYPES(p_code_cur   OUT     quick_ref_cur_typ);
 
 END CANON_E193_CSQUICK_SQLS_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E193_CSQUICK_SQLS_PKG
IS
/**************************************************************************************************
Program Name  : CANON_E193_CSQUICK_SQLS_PKG.sql
Functional Overview : All SQL's used by E193 Quick Ticket screen.
Table modified      : None
Comments            :
**************************************************************************************************/
/*******************************************************************************************
 Function Name: GET_CUST_SERVICE_GROUP
 Description: To get the customer service group
 Input Parameters: p_res_id
            
 Return: VARCHAR2
*******************************************************************************************/ 
FUNCTION GET_CUST_SERVICE_GROUP (p_res_id IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_role   VARCHAR2 (100) := NULL;
BEGIN
   --debug_pkg1.debug_proc ('p_res_id =' || p_res_id);

   --TBD Customer Service Role Name to be confirmed with S21 team
   
   
   SELECT DISTINCT 'Y'
     INTO l_role
     FROM s21_psn psn,
          org_func_asg asg,
          toc,
          org_func_role_tp role_tp
    WHERE     psn.psn_cd = asg.psn_cd
          AND asg.toc_cd = toc.toc_cd
          AND toc.org_func_role_tp_cd = role_tp.org_func_role_tp_cd
          AND psn.psn_cd = p_res_id
          AND psn.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND psn.ezcancelflag = g_cancel_flg
          AND asg.glbl_cmpy_cd = psn.glbl_cmpy_cd
          AND asg.ezcancelflag = psn.ezcancelflag
          AND toc.glbl_cmpy_cd = asg.glbl_cmpy_cd
          AND toc.ezcancelflag = asg.ezcancelflag
          AND role_tp.glbl_cmpy_cd = toc.glbl_cmpy_cd
          AND role_tp.ezcancelflag = toc.ezcancelflag
          AND role_tp.org_func_role_tp_nm LIKE 'CSR_E193_CUSTOMER_SERVICE%'
          AND EXISTS
                             (SELECT 'X'
                                FROM                      --org_func_asg ofa1,
                                    s21_org s,
                                     ds_org_unit dou1,
                                     canon_s21_cd_tbl cd,
                                     canon_s21_cd_val_tbl val
                               WHERE     1 = 1
                                     AND asg.toc_cd = s.toc_cd
                                     AND (   s.FIRST_ORG_CD = dou1.ORG_CD
                                          OR s.SCD_ORG_CD = dou1.ORG_CD
                                          OR s.THIRD_ORG_CD = dou1.ORG_CD
                                          OR s.FRTH_ORG_CD = dou1.ORG_CD
                                          OR s.FIFTH_ORG_CD = dou1.ORG_CD
                                          OR s.SIXTH_ORG_CD = dou1.ORG_CD
                                          OR s.SVNTH_ORG_CD = dou1.ORG_CD
                                          OR s.EIGHTH_ORG_CD = dou1.ORG_CD
                                          OR s.NINTH_ORG_CD = dou1.ORG_CD
                                          OR s.TENTH_ORG_CD = dou1.ORG_CD
                                          OR s.ELVTH_ORG_CD = dou1.ORG_CD)
                                     AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                                     AND s.ezcancelflag = 0
                                     AND s.glbl_cmpy_cd = 'ADB'
                                     AND s.ezcancelflag = dou1.ezcancelflag
                                     AND s.glbl_cmpy_cd = dou1.glbl_cmpy_cd
                                     AND NVL (dou1.eff_from_dt,
                                              TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                                     AND NVL (dou1.eff_thru_dt,
                                              TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                                     AND cd.cd_id = val.cd_id
                                     AND cd_name = 'CSR_E193_REG_GROUP'
                                     AND SYSDATE BETWEEN NVL (
                                                            val.start_date_active,
                                                            SYSDATE)
                                                     AND NVL (
                                                            val.end_date_active,
                                                            SYSDATE)
                                     AND val.val1 = dou1.org_nm);
          
   /*AND    EXISTS (
       SELECT 'X'
       FROM   jtf_rs_group_members@candev gm
             ,jtf_rs_groups_b@candev grp
       WHERE  to_char(gm.resource_id) = rl.role_resource_id
       AND    gm.GROUP_ID = grp.GROUP_ID
       AND    gm.delete_flag = 'N'
    --   AND    grp.attribute_category = g_csr_reg   --g_csr_org
       AND    NVL (grp.start_date_active, SYSDATE) <= SYSDATE
       AND    NVL (grp.end_date_active, SYSDATE) >= SYSDATE);*/
   ----debug_pkg1.debug_proc ('l_role ='||l_role);
   RETURN l_role;
EXCEPTION
   WHEN OTHERS
   THEN
      --debug_pkg1.debug_proc (' In exception l_role =' || SQLERRM);
      RETURN 'N';
END GET_CUST_SERVICE_GROUP;
   
 /*******************************************************************************************
 Procedure Name: GET_ACCOUNT_DETAILS
 Description: To get the account details
 Input Parameters: p_org_id
 		   p_value_type
 		   p_value
            
 Output Parameters: p_acct_cur
*******************************************************************************************/ 

PROCEDURE GET_ACCOUNT_DETAILS (p_org_id       IN     NUMBER,
                               p_value_type   IN     VARCHAR2,
                               p_value        IN     VARCHAR2,
                               p_acct_cur        OUT quick_ref_cur_typ)
IS
BEGIN
          /*INSERT INTO canon_e484_dynsql_tbl
           VALUES (-1, 'p_org_id : '||p_org_id ||' p_value_type : '||p_value_type||' p_value : '||p_value, SYSDATE);
          COMMIT;
fnd_client_info.set_org_context (p_org_id);*/
   IF p_value_type = 'serialNum'
   THEN
      OPEN p_acct_cur FOR
         SELECT loc_nm party_name,
                cur_loc_acct_num account_number,
                cur_loc_acct_num cust_account_id,
                NULL trx_type
           FROM svc_mach_mstr ib
          WHERE     ib.ser_num = p_value
                 AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND ROWNUM = 1;
   ELSIF p_value_type = 'invoiceNum'
   THEN
      OPEN p_acct_cur FOR
         SELECT inv.sell_to_loc_nm party_name,
                inv.sell_to_cust_cd account_number,
                inv.sell_to_cust_cd cust_account_id,
                'INV' trx_type,
                inv_num invoice_num
           FROM inv
          WHERE 1 = 1 
          AND inv.inv_num = p_value
          AND inv.ezcancelflag = g_cancel_flg
          AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
          UNION ALL
          SELECT inv.sell_to_loc_nm party_name,
	                  inv.sell_to_cust_cd account_number,
	                  inv.sell_to_cust_cd cust_account_id,
	                  'INV' trx_type,
	                  inv_num invoice_num
	  FROM inv
          WHERE 1 = 1 
          AND inv.ezcancelflag = g_cancel_flg
          AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd 
          AND EXISTS(
          	SELECT 1  
                FROM inv_prt_ctrl ipc
                WHERE 1=1
                AND ipc.ezcancelflag = g_cancel_flg
                AND ipc.glbl_cmpy_cd = g_glbl_cmpy_cd 
                AND ipc.inv_num = inv.inv_num
                AND ipc.consl_bill_num=p_value) ;

   ELSIF p_value_type = 'accountNum'
   THEN
      OPEN p_acct_cur FOR
            SELECT sell_to.loc_nm party_name,
                   sell_to.sell_to_cust_cd account_number,
                   sell_to.sell_to_cust_cd cust_account_id,
                   NULL trx_type
              FROM  sell_to_cust sell_to
             WHERE     1 = 1
                   AND NVL (sell_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND sell_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND sell_to.ezcancelflag = g_cancel_flg
                   AND sell_to.sell_to_cust_cd = p_value;
                   
        /* SELECT ship_to.loc_nm party_name,
                sell_to.sell_to_cust_cd account_number,
                sell_to.sell_to_cust_cd cust_account_id,
                NULL trx_type
           FROM ship_to_cust ship_to, sell_to_cust sell_to
          WHERE     1 = 1
                AND ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                 AND ship_to.ezcancelflag = g_cancel_flg
          	 AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd
          	 AND sell_to.ezcancelflag = ship_to.ezcancelflag
          	 AND sell_to.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                AND sell_to.sell_to_cust_cd = p_value;*/
   ELSIF p_value_type = 'ordNum'
   THEN
      OPEN p_acct_cur FOR
            SELECT sell_to.loc_nm party_name,
                   sell_to.sell_to_cust_cd account_number,
                   sell_to.sell_to_cust_cd cust_account_id,
                   NULL trx_type
              FROM cpo ord_hdr,
                   --cpo_dtl ord_dtl,
                   bill_to_cust bill_to,
                   sell_to_cust sell_to
             WHERE     1 = 1
                   --AND ord_hdr.cpo_ord_num = ord_dtl.cpo_ord_num
                   AND ord_hdr.bill_to_cust_cd = bill_to.bill_to_cust_cd
                   AND bill_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                   AND NVL (bill_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (bill_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND ord_hdr.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ord_hdr.ezcancelflag = g_cancel_flg
                   AND ord_hdr.glbl_cmpy_cd = bill_to.glbl_cmpy_cd
                   AND ord_hdr.ezcancelflag = bill_to.ezcancelflag
                   AND bill_to.glbl_cmpy_cd = sell_to.glbl_cmpy_cd
                   AND bill_to.ezcancelflag = sell_to.ezcancelflag
                   AND ord_hdr.cpo_ord_num = p_value;      
  /*        SELECT ship_to.loc_nm party_name,
                sell_to.sell_to_cust_cd account_number,
                sell_to.sell_to_cust_pk cust_account_id,
                NULL trx_type
           FROM cpo ord_hdr,
                cpo_dtl ord_dtl,
                ship_to_cust ship_to,
                sell_to_cust sell_to
          WHERE     1 = 1
                AND ord_hdr.cpo_ord_num = ord_dtl.cpo_ord_num
                AND ord_dtl.ship_to_cust_cd = ship_to.ship_to_cust_cd
                AND ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ord_hdr.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ord_hdr.ezcancelflag = g_cancel_flg
                AND ord_dtl.glbl_cmpy_cd = ord_hdr.glbl_cmpy_cd
                AND ord_dtl.ezcancelflag = ord_hdr.ezcancelflag
                AND ship_to.glbl_cmpy_cd = ord_dtl.glbl_cmpy_cd
                AND ship_to.ezcancelflag = ord_dtl.ezcancelflag
                AND sell_to.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                AND sell_to.ezcancelflag = ship_to.ezcancelflag
                AND ord_hdr.cpo_ord_num = p_value;*/
 --Start changes for QC#31243
 ELSIF p_value_type = 'ponum'
      THEN
         OPEN p_acct_cur FOR
               SELECT distinct inv.sell_to_loc_nm party_name,
                   inv.sell_to_cust_cd account_number,
                   inv.sell_to_cust_cd cust_account_id,
                   NULL trx_type
                  -- ,inv_num invoice_num
              FROM inv
             WHERE     1 = 1
                   AND upper(trim(inv.cust_iss_po_num)) = upper(trim(p_value))
                   AND inv.ezcancelflag = g_cancel_flg
                   AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd   ; 
  --End changes for QC#31243                  
   ELSE
      OPEN p_acct_cur FOR
         SELECT NULL
           FROM DUAL
          WHERE ROWNUM = 0;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
	 OPEN p_acct_cur FOR
         SELECT NULL
           FROM DUAL
          WHERE ROWNUM = 0;
END GET_ACCOUNT_DETAILS;

  /*******************************************************************************************
    Procedure Name: GET_SERIAL_ACCT_DETAILS
    Description: Procedure to get account details for serial
    Input Parameters: p_ser_num
    Output Parameters: p_account_cur
    
   *******************************************************************************************/
   PROCEDURE GET_SERIAL_ACCT_DETAILS (p_ser_num    IN     VARCHAR2,
                                      p_account_cur      OUT quick_ref_cur_typ)
   IS
   BEGIN
      
         OPEN p_account_cur FOR
	SELECT loc_nm party_name,
                cur_loc_acct_num account_number,
                cur_loc_acct_num cust_account_id,
                (select MKT_MDL_CD FROM MDSE 
		where mdse.mdse_cd=ib.mdse_cd
		AND mdse.glbl_cmpy_cd = 'ADB'
                   AND mdse.ezcancelflag = 0)model
           FROM svc_mach_mstr ib
          WHERE     ib.ser_num = p_ser_num
                 AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND ROWNUM = 1;         
         
/*            SELECT distinct ship_to.loc_nm party_name,
                   sell_to.sell_to_cust_cd account_number,
                   sell_to.sell_to_cust_cd cust_account_id,
                   (select MKT_MDL_CD FROM MDSE 
		                      where mdse.mdse_cd=ib.mdse_cd
		                      AND mdse.glbl_cmpy_cd = 'ADB'
                   AND mdse.ezcancelflag = 0)model
              FROM svc_mach_mstr ib,
                   ship_to_cust ship_to,
                   sell_to_cust sell_to
             WHERE  upper(trim(ib.ser_num)) =upper(trim( p_ser_num))
                   AND NVL (ship_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND ship_to.ship_to_cust_cd = ib.cur_loc_num
                   AND ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                   AND ib.glbl_cmpy_cd = 'ADB'
                   AND ib.ezcancelflag = 0
                   AND ib.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                   AND ib.ezcancelflag =ship_to.ezcancelflag
                   AND ship_to.glbl_cmpy_cd = sell_to.glbl_cmpy_cd
                   AND ship_to.ezcancelflag =sell_to.ezcancelflag;*/

   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_account_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

   END GET_SERIAL_ACCT_DETAILS;     

     
 /*******************************************************************************************
 Procedure Name: GET_REASON_CODES
 Description: To get the account details
 Input Parameters: None
            
 Output Parameters: p_reason_cur
*******************************************************************************************/ 
     
PROCEDURE GET_REASON_CODES (p_reason_cur OUT quick_ref_cur_typ)
IS
BEGIN
   OPEN p_reason_cur FOR
      SELECT lookup_val.val1 reason, lookup_val.val2 description
        FROM canon_s21_cd_tbl lookup, canon_s21_cd_val_tbl lookup_val
       WHERE     lookup.cd_name = 'CSR_E193_QUICKTICKET'
             AND lookup.cd_id = lookup_val.cd_id
             AND SYSDATE BETWEEN NVL (lookup_val.start_date_active, SYSDATE)
                                AND NVL (lookup_val.end_date_active, SYSDATE)
                                ORDER BY to_number(val3) ;
EXCEPTION
   WHEN OTHERS
   THEN
      OPEN p_reason_cur FOR
         SELECT NULL
           FROM DUAL
          WHERE ROWNUM = 0;
END;

 /*******************************************************************************************
 Procedure Name: GET_CAT_CODE
 Description: To get the category codes
 Input Parameters: p_region
            
 Output Parameters: p_code_cur
*******************************************************************************************/ 

PROCEDURE GET_CAT_CODE (p_region     IN     VARCHAR2,
                        p_code_cur      OUT quick_ref_cur_typ)
IS
BEGIN
   OPEN p_code_cur FOR
      SELECT *
        FROM canon_e193_cs_categories
       WHERE     parent_cat_id IN (SELECT cat_id
                                     FROM canon_e193_cs_categories
                                    WHERE cat_code = 'QUICKTICKET')
             AND region = p_region;
EXCEPTION
   WHEN OTHERS
   THEN
      OPEN p_code_cur FOR
         SELECT NULL
           FROM DUAL
          WHERE ROWNUM = 0;
END GET_CAT_CODE;
    
/*******************************************************************************************
 Function Name: GET_LINE_ID
 Description: To get the E193 line Id for the ticket number passed
 Input Parameters: p_ticket_id
            
 Return: NUMBER
 *******************************************************************************************/     
    
FUNCTION GET_LINE_ID (p_ticket_id IN NUMBER)
   RETURN NUMBER
IS
   l_line_id   NUMBER;
BEGIN
   l_line_id := -1;

   SELECT line_id
     INTO l_line_id
     FROM canon_e193_cs_assignments a
    WHERE     a.line_status = c_l_assigned_status
          AND a.assign_status = c_close_status
          AND EXISTS
                 (SELECT 'x'
                    FROM canon_e193_cs_lines l
                   WHERE l.line_id = a.line_id AND l.ticket_id = p_ticket_id);


   RETURN l_line_id;
EXCEPTION
   WHEN OTHERS
   THEN
      l_line_id := -1;
      RETURN l_line_id;
END GET_LINE_ID;
   
/*******************************************************************************************
 Procedure Name: UPDATE_QUICK_TICKET
 Description: To update the quick ticket status
 Input Parameters: p_ticket_no
 		   p_org_id
 		   p_line_id
            	   p_severity
            	   p_status
            	   p_updated_by
            	   p_notes
            	   
 Output Parameters: p_ret_val
*******************************************************************************************/    

PROCEDURE UPDATE_QUICK_TICKET (p_ticket_no    IN     NUMBER,
                               p_org_id       IN     NUMBER,
                               p_line_id      IN     NUMBER,
                               p_severity     IN     VARCHAR2,
                               p_status       IN     VARCHAR2,
                               p_updated_by   IN     VARCHAR2,
                               p_notes        IN     VARCHAR2,
                               p_ret_val         OUT NUMBER)
IS
   l_line_id      NUMBER;
   l_org_id       NUMBER;
   l_created_by   VARCHAR2 (100);
   l_note_id      NUMBER;
   l_ret_status   VARCHAR2 (1);
BEGIN
--debug_pkg1.debug_proc('CANON_E193_CSQUICK_SQLS_PKG. UPDATE_QUICK_TICKET');
   UPDATE canon_e193_cs_headers
      SET ticket_status = 'CLOSE'
    WHERE 1 = 1 AND ticket_status <> 'CLOSE' AND ticket_number = p_ticket_no;

   UPDATE canon_e193_cs_assignments cca
      SET line_status = 'CLOSE', assign_status = 'CLOSE'
    WHERE     1 = 1                             --cca.assign_status = 'ACTIVE'
          -- AND cca.line_status = 'ASSIGNED'
          AND EXISTS
                 (SELECT 'x'
                    FROM canon_e193_cs_lines l
                   WHERE     1 = 1
                         AND l.line_id = cca.line_id
                         AND l.line_status <> 'CLOSE'
                         AND EXISTS
                                (SELECT 'x'
                                   FROM canon_e193_cs_headers h
                                  WHERE     h.ticket_id = l.ticket_id
                                        AND h.ticket_status = 'CLOSE'
                                        AND h.ticket_number = p_ticket_no));

   --  p_err_val := SQL%ROWCOUNT || ' row(s) updated in canon_e193_cs_assignments.';
   p_ret_val := 0;
   canon_e193_cs_evolution_pkg.update_ticket_line (p_org_id,
                                                   p_line_id,
                                                   p_severity,
                                                   p_status,
                                                   p_updated_by,
                                                   p_notes,
                                                   p_ret_val);

   COMMIT;
EXCEPTION
   WHEN OTHERS
   THEN
      --   p_err_val := SQLERRM;
      p_ret_val := -1;
END UPDATE_QUICK_TICKET;
   
/*******************************************************************************************
 Procedure Name: GET_QT_SEARCH_TYPES
 Description: Procedure to populate search types
 Input Parameters: None
            	   
 Output Parameters: p_code_cur
*******************************************************************************************/    

PROCEDURE GET_QT_SEARCH_TYPES (p_code_cur OUT quick_ref_cur_typ)
IS
BEGIN
   OPEN p_code_cur FOR
      SELECT val.val1 flex_value, val.val2 description
        FROM CANON_S21_CD_TBL csct, CANON_S21_CD_VAL_TBL val
       WHERE     csct.cd_name = 'CANON_E193_QUICKTICKET_SEARCH_TYPE'
             AND csct.cd_id = val.cd_id
             AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                                ORDER by to_number(val.val3) ;
EXCEPTION
   WHEN OTHERS
   THEN
      OPEN p_code_cur FOR
         SELECT NULL
           FROM DUAL
          WHERE ROWNUM = 0;
END GET_QT_SEARCH_TYPES;
   
END CANON_E193_CSQUICK_SQLS_PKG;
/