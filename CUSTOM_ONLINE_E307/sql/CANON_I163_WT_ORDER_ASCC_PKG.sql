CREATE OR REPLACE PACKAGE CANON_I163_WT_ORDER_ASCC_PKG
AS
   TYPE g_ref_cur_typ IS REF CURSOR; 
   
   PROCEDURE get_serial_numbers(
      p_serial_number             IN      VARCHAR2,
      v_ser_cursor                   OUT     g_ref_cur_typ
      );
   PROCEDURE GET_CUSTOMERS(
        p_cust_name              IN      VARCHAR2,
        x_cust_cursor         OUT     g_ref_cur_typ
      );
  FUNCTION check_tech_required(p_serial_number IN VARCHAR2)
   RETURN VARCHAR2; 
 FUNCTION get_user_email(p_serial_number IN VARCHAR2)
   RETURN VARCHAR2;   
 PROCEDURE MACHINE_INFO(
      p_serial                 IN      VARCHAR2,
      p_cust_name              IN      VARCHAR2,
      p_address                IN      VARCHAR2,
      p_start                  IN      NUMBER DEFAULT 1,
      p_end                    IN      NUMBER DEFAULT 10,
      x_mach_details_tbl       OUT     CANON_E307_WASTE_TONER_TBL,
      x_count                  OUT     NUMBER
      );
 PROCEDURE STAGE_WT_ORDERS (p_serial           IN     VARCHAR2,
                              p_model            IN     VARCHAR2,
                              p_customer         IN     VARCHAR2,
                              p_contact_info     IN     VARCHAR2,
                              p_contact_phone    IN     VARCHAR2,
                              p_contact_ext      IN     VARCHAR2,
                              p_email            IN     VARCHAR2,
                              p_wt_shelfstock    IN     VARCHAR2,
                              p_wt_quantity      IN     NUMBER,
                              o_return_status       OUT VARCHAR2,
                              o_return_message      OUT VARCHAR2);  
  FUNCTION THIRD_PARTY_CHECK (p_serial_number IN VARCHAR2)
      RETURN VARCHAR2;
  
      
END CANON_I163_WT_ORDER_ASCC_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_I163_WT_ORDER_ASCC_PKG
IS
   PROCEDURE get_serial_numbers(
      p_serial_number             IN      VARCHAR2,
      v_ser_cursor                OUT     g_ref_cur_typ
      )
     IS
       lv_sql                     VARCHAR2 (32000);
    BEGIN
    
     lv_sql:='
         SELECT DISTINCT
                smm.svc_mach_mstr_pk,
                mdl.T_MDL_NM model,
                smm.SER_NUM ser_num,
                smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
                smm.cur_loc_acct_num ship_to_acct_no,
                ship_to.loc_nm ship_to_cust_name,
                ship_to.first_line_addr address_1,
                ship_to.scd_line_addr address_2,
                ship_to.cty_addr city,
                ship_to.st_cd state,
                ship_to.post_cd,
                   ship_to.first_line_addr
                || '', ''
                || ship_to.cty_addr
                || '', ''
                || ship_to.st_cd
                || '', ''
                || ship_to.post_cd
                   Address
           FROM svc_mach_mstr smm,
                svc_config_mstr config,
                mdl_nm mdl,
                svc_mach_ctac_psn contact,
                ship_to_cust ship_to,
                mdse mdse
         WHERE     1 = 1                 
             AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
             AND smm.SVC_MACH_MSTR_PK = contact.SVC_MACH_MSTR_PK(+)
             AND smm.MDSE_CD = mdse.MDSE_CD
             AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
             AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >=
                TO_CHAR (SYSDATE, ''YYYYMMDD'')
             AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                TO_CHAR (SYSDATE, ''YYYYMMDD'')
             AND NVL (config.glbl_cmpy_cd, ''ADB'') = ''ADB''
             AND NVL (smm.glbl_cmpy_cd, ''ADB'') = ''ADB''
             AND NVL (contact.glbl_cmpy_cd, ''ADB'') = ''ADB''
             AND NVL (ship_to.glbl_cmpy_cd, ''ADB'') = ''ADB''
             AND NVL (mdse.glbl_cmpy_cd, ''ADB'') = ''ADB''
             AND NVL (config.EZCANCELFLAG,''0'') = ''0''
             AND NVL (smm.EZCANCELFLAG, ''0'') = ''0''
             AND NVL (contact.EZCANCELFLAG, ''0'') = ''0''
             AND NVL (ship_to.EZCANCELFLAG, ''0'') = ''0''
             AND NVL (mdse.EZCANCELFLAG, ''0'') = ''0''
             AND mdl.T_MDL_ID(+) = config.MDL_ID
             AND smm.SVC_MACH_TP_CD = ''1''
             AND SVC_CALL_ENBL_FLG = ''Y''
             AND smm.SER_NUM IS NOT NULL 
             AND smm.SER_NUM NOT LIKE ''%-BAD%'' ';
             
     IF p_serial_number is not null
     THEN
			 lv_sql := lv_sql
			 || ' AND (UPPER(nvl(smm.ser_num,''X'')) like upper(  '''
			 || TRIM (p_serial_number)
			 || '%'') '
			 || ' OR UPPER(nvl(smm.cust_mach_ctrl_num,''X'')) like upper(  '''
			 || TRIM (p_serial_number)
			 || '%'')) ' ;
     END IF;  
     
      lv_sql := lv_sql || ' ORDER BY smm.SER_NUM';
  dbms_output.put_line('lv_sql: '||lv_sql);        
      OPEN v_ser_cursor FOR lv_sql;  
      
  EXCEPTION WHEN OTHERS THEN
   OPEN v_ser_cursor FOR	
     SELECT ''svc_mach_mstr_pk,
     '' model, '' ser_num, 
     '' cust_mach_ctrl_num,
     '' ship_to_acct_no,
     '' ship_to_cust_name,
     '' address_1,
     '' address_2,
     '' city,
     '' state,
     '' post_cd,
     '' address
   FROM dual WHERE 1<>1;
  END;
PROCEDURE GET_CUSTOMERS(
      p_cust_name              IN      VARCHAR2,
      x_cust_cursor         OUT     g_ref_cur_typ
      )
   IS
    
    BEGIN
        OPEN x_cust_cursor FOR
        Select DISTINCT sell_to.sell_to_cust_cd  account_number, ship_to.loc_nm customer_name
                        FROM sell_to_cust sell_to , ship_to_cust  ship_to
                        WHERE  1 = 1
                        AND    NVL (sell_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND    NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND    NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND    NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND    sell_to.sell_to_cust_cd = ship_to.sell_to_cust_cd
                        AND    ship_to.loc_nm like  UPPER(p_cust_name);

    EXCEPTION WHEN OTHERS
    THEN
         OPEN x_cust_cursor FOR
         SELECT '' account_number, '' customer_name
         FROM DUAL;
    END GET_CUSTOMERS;  
  FUNCTION check_tech_required
            (p_serial_number IN VARCHAR2
            )
  RETURN VARCHAR2
  IS
  l_tech_required VARCHAR2(100);
  
 BEGIN
  BEGIN
        SELECT DECODE(wt.character2, 'Yes', 'No', 'No', 'Yes')
          INTO l_tech_required
        FROM CANON_I163_WT_PART_MAPPING_v wt,
          svc_mach_mstr smm,
          svc_config_mstr config,
          mdl_nm mdl
        WHERE smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
        AND mdl.T_MDL_ID(+)          = config.MDL_ID
        AND mdl.T_MDL_NM             = wt.character1
        AND smm.ser_num              = p_serial_number;--'NQF00139';
  EXCEPTION
   WHEN NO_DATA_FOUND
    THEN
     l_tech_required := 'No';
   WHEN OTHERS
    THEN
     l_tech_required := 'No';
  END;
  
  RETURN l_tech_required;
  
 EXCEPTION
     WHEN OTHERS
      THEN
         l_tech_required := 'No';
         RETURN l_tech_required;
 END check_tech_required; 
 FUNCTION get_user_email(p_serial_number IN VARCHAR2)
   RETURN VARCHAR2
 IS
      l_tsk_no                 VARCHAR2 (50);
      l_email_address          VARCHAR2 (100);
      lv_contact               VARCHAR2 (100);
      l_cust_tel_num           VARCHAR2 (50);
      l_cust_tel_ext           VARCHAR2 (50);
 BEGIN
   BEGIN
            SELECT MIN (SVC_TASK_NUM)
              INTO l_tsk_no
              FROM svc_task
             WHERE fsr_num IN (SELECT MAX (fsr_num)
                                 FROM fsr
                                WHERE ser_num = p_serial_number);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_tsk_no := '';
         END;
           BEGIN
            SELECT st.SVC_CUST_ATTN_TXT contact,
                   st.CUST_TEL_NUM,
                   st.CUST_TEL_EXTN_NUM,
                   st.CUST_EML_ADDR
              INTO lv_contact,
                   l_cust_tel_num,
                   l_cust_tel_ext,
                   l_email_address
              FROM SVC_TASK st
             WHERE SVC_TASK_NUM = l_tsk_no 
             AND GLBL_CMPY_CD = 'ADB'
             AND ezcancelflag = '0';
         EXCEPTION
            WHEN OTHERS
            THEN
               CANON_E307_CREATE_SR_PKG.debug_msg (
                  l_program_name   => 'get_user_email: CUST INFORMATION',
                  l_attribute3     => 'l_tsk_no: ' || l_tsk_no,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_contact := NULL;
               l_cust_tel_num := NULL;
               l_cust_tel_ext := NULL;
               l_email_address := NULL;
         END;
         RETURN l_email_address;
  EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
  END;
 PROCEDURE MACHINE_INFO(
      p_serial                 IN      VARCHAR2,
      p_cust_name              IN      VARCHAR2,
      p_address                IN      VARCHAR2,
      p_start                  IN      NUMBER DEFAULT 1,
      p_end                    IN      NUMBER DEFAULT 10,
      x_mach_details_tbl       OUT     CANON_E307_WASTE_TONER_TBL,
      x_count                  OUT     NUMBER
      )
   IS
     l_rec_mach_details   CANON_E307_WASTE_TONER_REC;
     l_sql                VARCHAR2 (32000);
     l_sql_count_qry      VARCHAR2 (32000);
     l_default_from       VARCHAR2 (32000);
     v_sql                VARCHAR2 (32000);
     lv_sql               VARCHAR2 (32000);
     v_mach_cursor        g_ref_cur_typ;
     ln_rec_cnt1          NUMBER := 1;
     lv_ser_num           VARCHAR2 (50);
     lv_ship_to_cust_name VARCHAR2 (100);
     lv_location_address  VARCHAR2 (100);
     lv_ship_to_cust_cd   VARCHAR2 (100);
     lv_model             VARCHAR2 (50);
     lv_contact_name 			VARCHAR2 (100);
     lv_contact_phone 		VARCHAR2 (20);
     lv_contact_phone_ext VARCHAR2 (20);
     lv_email_address 		VARCHAR2 (50);
     lv_shelf_stock 		  VARCHAR2 (100);
     lv_quantity 			    VARCHAR2 (50);
     l_tsk_no             VARCHAR2(50);
     lv_rownum            NUMBER;
   BEGIN
     x_mach_details_tbl := CANON_E307_WASTE_TONER_TBL ();
     
     l_sql:= ' SELECT distinct smm.ser_num, mdl.T_MDL_NM model,
                ship_to.loc_nm ship_to_cust_name,
                          (ship_to.first_line_addr || '' '' ||
                          ship_to.scd_line_addr || '' '' ||
                          ship_to.cty_addr || '' '' ||
                          ship_to.st_cd) Location_Address,
                          ship_to.ship_to_cust_cd ';
         
         l_default_from:='
             FROM svc_mach_mstr smm,
                  SVC_CONFIG_MSTR config,
                  MDL_NM mdl,
                  SHIP_TO_CUST ship_to
            WHERE     1 = 1 
                AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
                AND smm.SVC_MACH_TP_CD = ''1''
               and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=
                                   TO_CHAR (SYSDATE, ''YYYYMMDD'')
                            AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                          TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND config.glbl_cmpy_cd = ''ADB''
                AND smm.glbl_cmpy_cd = ''ADB''
                AND mdl.T_MDL_ID = config.MDL_ID
                AND ship_to.ship_to_cust_cd = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >=
                       TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                       TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND ship_to.glbl_cmpy_cd = ''ADB''';
                
      IF  p_cust_name IS NOT NULL AND p_address IS NOT NULL
      THEN
           l_default_from:= l_default_from||'     
                    AND upper(ship_to.loc_nm) like upper('''||p_cust_name||''') --LITHEXCEL COMMUNICATIONS
                    AND UPPER((ship_to.first_line_addr || '' '' ||
                    ship_to.scd_line_addr || '' '' ||
                    ship_to.cty_addr || '' '' ||
                    ship_to.st_cd)) like '''||p_address||'''';
      END IF;    
      IF p_serial IS NOT NULL THEN
        l_default_from:= l_default_from||' AND smm.ser_num = '''||p_serial||'''';
      END IF;
      
    --   dbms_output.put_line('l_sql: '||l_sql);   
       
       lv_sql:= 'SELECT ROWNUM ROW_NUM,  a.*
                        FROM ('||l_sql||' '||l_default_from||') a';
       
        dbms_output.put_line('lv_sql: '||lv_sql); 
        
    v_sql :=
            'SELECT * FROM( '
         || lv_sql
         || ' )  WHERE row_num BETWEEN '
         || p_start
         || ' AND '
         || p_end;
         
       dbms_output.put_line('v_sql: '||v_sql); 
       
       l_sql_count_qry := ' select count(*) ' || l_default_from ;
       
        dbms_output.put_line('l_sql_count_qry:  '||l_sql_count_qry); 

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;
      dbms_output.put_line('After count '||x_count); 
      
      OPEN v_mach_cursor FOR v_sql;
      LOOP  
       dbms_output.put_line('Inside LOOP '); 
       
       FETCH v_mach_cursor 
       INTO   lv_rownum, 
              lv_ser_num,  
              lv_model,
              lv_ship_to_cust_name,
              lv_location_address,
              lv_ship_to_cust_cd;
              
      EXIT WHEN v_mach_cursor%NOTFOUND;
      
     dbms_output.put_line('lv_ser_num: '||lv_ser_num||' lv_model: '||lv_model||' lv_ship_to_cust_name: '||lv_ship_to_cust_name);           
         BEGIN
            lv_shelf_stock:=  check_tech_required(lv_ser_num);
          EXCEPTION WHEN OTHERS 
          THEN
            lv_shelf_stock:='';
          END;
           dbms_output.put_line('lv_shelf_stock: '||lv_shelf_stock);  
        BEGIN
            SELECT MIN (SVC_TASK_NUM)
              INTO l_tsk_no
              FROM svc_task
             WHERE fsr_num IN (SELECT MAX (fsr_num)
                                 FROM fsr
                                WHERE ser_num = lv_ser_num);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_tsk_no := '';
         END;

           BEGIN
            SELECT st.SVC_CUST_ATTN_TXT contact,
                   st.CUST_TEL_NUM,
                   st.CUST_TEL_EXTN_NUM,
                   st.CUST_EML_ADDR
              INTO lv_contact_name,
                   lv_contact_phone,
                   lv_contact_phone_ext,
                   lv_email_address
              FROM SVC_TASK st
             WHERE SVC_TASK_NUM = l_tsk_no 
             AND GLBL_CMPY_CD = 'ADB'
             AND ezcancelflag = '0';
         EXCEPTION
            WHEN OTHERS
            THEN
               CANON_E307_CREATE_SR_PKG.debug_msg (
                  l_program_name   => 'get_user_email: CUST INFORMATION',
                  l_attribute3     => 'l_tsk_no: ' || l_tsk_no,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
                   lv_contact_name:='';
                   lv_contact_phone:='';
                   lv_contact_phone_ext:='';
                   lv_email_address:='';
         END;
dbms_output.put_line('lv_contact_name: '||lv_contact_name||' lv_email_address: '||lv_email_address);  
      
         l_rec_mach_details :=
            CANON_E307_WASTE_TONER_REC (
                lv_ser_num,   
                lv_model,             
                lv_ship_to_cust_name,
                lv_location_address,
                lv_contact_name,
                lv_contact_phone,
                lv_contact_phone_ext,
                lv_email_address,
                lv_shelf_stock,
                lv_quantity 
            );
         x_mach_details_tbl.EXTEND ();
         x_mach_details_tbl (ln_rec_cnt1) := l_rec_mach_details;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP; 
   EXCEPTION WHEN OTHERS
   THEN
        CANON_E307_CREATE_SR_PKG.debug_msg (
            l_program_name   => 'MACHINE_INFO: ',
            l_attribute3     =>    'p_serial: '
                                || p_serial
                                || 'p_cust_name: '
                                || p_cust_name
                                ||' p_address: '
                                || p_address,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;  
   END MACHINE_INFO;
   PROCEDURE STAGE_WT_ORDERS (p_serial           IN     VARCHAR2,
                              p_model            IN     VARCHAR2,
                              p_customer         IN     VARCHAR2,
                              p_contact_info     IN     VARCHAR2,
                              p_contact_phone    IN     VARCHAR2,
                              p_contact_ext      IN     VARCHAR2,
                              p_email            IN     VARCHAR2,
                              p_wt_shelfstock    IN     VARCHAR2,
                              p_wt_quantity      IN     NUMBER,
                              o_return_status       OUT VARCHAR2,
                              o_return_message      OUT VARCHAR2)
   IS
      l_tech_required   VARCHAR2 (100);
      l_instance_id     NUMBER := NULL;
      lv_val            NUMBER;
   BEGIN
      IF NVL (p_wt_shelfstock, 'No') = 'No'
      THEN
         SELECT check_tech_required (p_serial)
           INTO l_tech_required
           FROM DUAL;
      ELSE
         l_tech_required := 'No';
      END IF;

       INSERT INTO CANON_ASCC_WT_REQ_STAGE_TBL
         (
         seq_number,
         serial_number           ,
         sr_type                 ,
         incident_date           ,
         sr_creation_date        ,
         contact_name            ,
         phone                   ,
         extension               ,
         email                   ,
         notes                   ,
         problem_summary         ,
         wt_order                ,
         wt_shelfstock           ,
         wt_quantity             ,
         WT_TECH_REQUIRED        ,
         third_party_serviced    ,
         customer                ,
         creation_date           ,
         created_by,
         updated_date,
         updated_by
         )
         VALUES
         (
            CANON_ASCC_WT_REQ_STAGE_SEQ.nextval,
            p_serial              ,
            '1-SERV CALL'         ,
            SYSDATE               ,
            SYSDATE               ,
            p_contact_info        ,
            p_contact_phone       ,
            p_contact_ext         ,
            p_email               ,            
            'Waste Toner Container Install Request',
            'Waste Toner Container Install Request',
            'Yes' ,
            NVL(p_wt_shelfstock, 'No') ,
            p_wt_quantity,
            l_tech_required       ,
            third_party_check(p_serial),
            p_customer              ,
            sysdate               ,
            -1,
            sysdate               ,
            -1
         );
    COMMIT;

      o_return_status := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
         o_return_status := 'E';
         o_return_message :=
            'Unknown error in STAGE_WT_ORDERS' || ' - ' || SQLERRM;
   END STAGE_WT_ORDERS;  
   FUNCTION THIRD_PARTY_CHECK (p_serial_number IN VARCHAR2)
      RETURN VARCHAR2
   IS
      v_return_flag VARCHAR2(1) := 'N';

   BEGIN
   
      BEGIN

  SELECT DECODE (COUNT (1), 0, 'N', 'Y')
  INTO v_return_flag
    FROM SVC_MACH_MSTR SMM
   WHERE     1 = 1
         AND SMM.SER_NUM = p_serial_number
         AND SMM.GLBL_CMPY_CD = 'ADB'
         AND SMM.EZCANCELFLAG = '0'
         AND EXISTS
                (SELECT 1
                   FROM S21_PSN SP,
                        CTAC_PSN CP,
                        DS_CTAC_PSN_RELN DCPR,
                        CTAC_TP CT,
                        VND V
                  WHERE     SP.GLBL_CMPY_CD = SMM.GLBL_CMPY_CD
                        AND SP.PSN_CD = SMM.PRF_TECH_CD
                        AND SP.GLBL_CMPY_CD = SMM.GLBL_CMPY_CD
                        AND SP.PSN_CD = SMM.PRF_TECH_CD
                        AND SP.EZCANCELFLAG = '0'
                        AND SP.DEL_FLG = 'N'
                        AND SP.PSN_TP_CD <> '1'
                        AND SP.EFF_FROM_DT <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND NVL (SP.EFF_THRU_DT, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                               TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND CP.GLBL_CMPY_CD = SP.GLBL_CMPY_CD
                        AND CP.CTAC_PSN_PK = SP.CTAC_PSN_PK
                        AND CP.EZCANCELFLAG = '0'
                        AND DCPR.EZCANCELFLAG = '0'
                        AND DCPR.GLBL_CMPY_CD = CP.GLBL_CMPY_CD
                        AND DCPR.CTAC_PSN_PK = CP.CTAC_PSN_PK
                        AND DCPR.GLBL_CMPY_CD = CT.GLBL_CMPY_CD
                        AND DCPR.CTAC_TP_CD = CT.CTAC_TP_CD
                        AND CT.SPLY_IND_FLG = 'Y'
                        AND CT.ACTV_FLG = 'Y'
                        AND CT.EZCANCELFLAG = '0'
                        AND V.EZCANCELFLAG = '0'
                        AND V.GLBL_CMPY_CD = DCPR.GLBL_CMPY_CD
                        AND V.LOC_NUM = DCPR.LOC_NUM);
      EXCEPTION
         WHEN OTHERS
         THEN
            v_return_flag := 'N';
      END;

      RETURN v_return_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         v_return_flag := 'N';
         RETURN v_return_flag;
   END THIRD_PARTY_CHECK;     
   
END  CANON_I163_WT_ORDER_ASCC_PKG;
/
SHOW ERRORS