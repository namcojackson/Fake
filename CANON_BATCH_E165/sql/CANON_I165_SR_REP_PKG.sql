CREATE OR REPLACE PACKAGE CANON_I165_SR_REP_PKG
IS

 PROCEDURE LOAD_SEED_AND_LAUNCH  (o_err_buf  OUT VARCHAR2,
                       o_ret_code OUT VARCHAR2);

 PROCEDURE LOAD_STG (p_batch_id IN NUMBER);
  FUNCTION
    SERV_RESP_TIME_FUNC
        (p_from_date                  DATE,
         p_to_date                    DATE,
         p_shift_start_time           VARCHAR2,
         p_shift_end_time             VARCHAR2,
         p_weekend_count_flag         VARCHAR2
       )
      RETURN NUMBER;
      
     PROCEDURE MERGE_BAD_SERIAL;  

    PROCEDURE INCIDENT_LINK;
  
END CANON_I165_SR_REP_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_I165_SR_REP_PKG
IS
  PROCEDURE LOAD_SEED_AND_LAUNCH
                        (o_err_buf  OUT VARCHAR2,
                         o_ret_code OUT VARCHAR2)
  IS
    l_batch_id NUMBER := 1;
    l_request_number NUMBER;
    l_not_complete  VARCHAR2(1);
    l_last_days number := 0;
    l_loop_batch_id NUMBER := 0;
	
    cursor oki_cur is select distinct contract_id from canon_i165_oki_tbl;
    cursor batch_cur is select distinct batch_id from canon_i165_oki_tbl;
    
	BEGIN
     EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_I165_OKI_TBL';   
     BEGIN
       SELECT val.VAL51 
         INTO l_last_days
         FROM CANON_S21_CD_TBL cd,  
              CANON_S21_CD_VAL_TBL val 
        WHERE cd.cd_id = val.cd_id  
          AND nvl(is_active,'Y') ='Y' 
          AND cd.cd_name = 'CANON_I165_TERM_EXP_DAYS'; 
     EXCEPTION
     WHEN OTHERS THEN
       l_last_days := 90;
     END;	
      
      INSERT INTO canon_i165_oki_tbl(instance_id, contract_id, sub_line_id, attribute1, contract_number)
      SELECT dtl.svc_mach_mstr_pk,
             hdr.ds_contr_pk,
             dtl.ds_contr_dtl_pk,
             hdr.SVC_CONTR_BR_CD,
             hdr.ds_contr_num
      FROM DS_CONTR hdr,
           DS_CONTR_DTL dtl,
           DS_CONTR_TP cont_type
       WHERE hdr.DS_CONTR_PK = dtl.DS_CONTR_PK
         AND dtl.glbl_cmpy_cd = 'ADB'
         AND hdr.glbl_cmpy_cd = 'ADB'
		 AND cont_type.glbl_cmpy_cd = 'ADB'
         AND dtl.ezcancelflag = '0'
         AND hdr.ezcancelflag = '0'
		 AND cont_type.ezcancelflag = '0'
		 AND hdr.DS_CONTR_TP_CD = cont_type.DS_CONTR_TP_CD
         AND UPPER(cont_type.ds_contr_tp_nm) = UPPER('Service')
         AND dtl.DS_CONTR_DTL_STS_CD NOT IN ('TRMD','EXPD','CANC')
		 AND hdr.SVC_CONTR_BR_CD IN (SELECT val.VAL1
                                       FROM CANON_S21_CD_TBL cd,  
                                            CANON_S21_CD_VAL_TBL val 
                                      WHERE cd.cd_id = val.cd_id  
                                        AND nvl(is_active,'Y') ='Y' 
                                        AND cd.cd_name = 'CANON_I165_BRANCH_CODE')
    UNION ALL
    SELECT dtl.svc_mach_mstr_pk,
           hdr.ds_contr_pk,
           dtl.ds_contr_dtl_pk,
           hdr.SVC_CONTR_BR_CD,
           hdr.ds_contr_num
      FROM DS_CONTR hdr,
           DS_CONTR_DTL dtl,
           DS_CONTR_TP cont_type
       WHERE hdr.DS_CONTR_PK = dtl.DS_CONTR_PK
         AND dtl.glbl_cmpy_cd = 'ADB'
         AND hdr.glbl_cmpy_cd = 'ADB'
		 AND dtl.ezcancelflag = '0'
         AND hdr.ezcancelflag = '0'
		 AND cont_type.ezcancelflag = '0'
		 AND TO_DATE(dtl.CONTR_CLO_DT,'YYYYMMDD') >= sysdate - l_last_days
         AND hdr.DS_CONTR_TP_CD = cont_type.DS_CONTR_TP_CD
         AND cont_type.glbl_cmpy_cd = 'ADB'
         AND UPPER(cont_type.ds_contr_tp_nm) = UPPER('Service')
         AND dtl.DS_CONTR_DTL_STS_CD IN ('TRMD','EXPD')
		 AND hdr.SVC_CONTR_BR_CD IN (SELECT val.VAL1
                                       FROM CANON_S21_CD_TBL cd,  
                                            CANON_S21_CD_VAL_TBL val 
                                      WHERE cd.cd_id = val.cd_id  
                                        AND nvl(is_active,'Y') ='Y' 
                                        AND cd.cd_name = 'CANON_I165_BRANCH_CODE')		 
         AND NOT EXISTS (SELECT 'X'
                         FROM DS_CONTR hdr1,
                              DS_CONTR_DTL dtl1,
                              DS_CONTR_TP cont_type1
                          WHERE hdr1.DS_CONTR_PK = dtl1.DS_CONTR_PK
                            AND dtl1.glbl_cmpy_cd = 'ADB'
                            AND hdr1.glbl_cmpy_cd = 'ADB'
                            AND hdr1.DS_CONTR_TP_CD = cont_type1.DS_CONTR_TP_CD
                            AND cont_type1.glbl_cmpy_cd = 'ADB'
							AND dtl1.ezcancelflag = '0'
                            AND hdr1.ezcancelflag = '0'
		                    AND cont_type1.ezcancelflag = '0'
                            AND dtl.svc_mach_mstr_pk = dtl1.svc_mach_mstr_pk
                            AND UPPER(cont_type1.ds_contr_tp_nm) = UPPER('Service')
                            AND dtl1.DS_CONTR_DTL_STS_CD NOT IN ('TRMD','EXPD','CANC')
							AND hdr.SVC_CONTR_BR_CD IN (SELECT val.VAL1
                                                          FROM CANON_S21_CD_TBL cd,  
                                                               CANON_S21_CD_VAL_TBL val 
                                                         WHERE cd.cd_id = val.cd_id  
                                                           AND nvl(is_active,'Y') ='Y' 
                                                           AND cd.cd_name = 'CANON_I165_BRANCH_CODE'));
      COMMIT;	
     
     UPDATE canon_i165_oki_tbl i165
        set serial_number = (select ser_num from svc_mach_mstr smm
                                where i165.instance_id = smm.SVC_MACH_MSTR_PK);
  
     COMMIT;	
     
     DELETE FROM canon_i165_oki_tbl a
      WHERE ROWID NOT IN (SELECT MAX(ROWID) FROM  canon_i165_oki_tbl b WHERE a.instance_id = b.instance_id)
      AND EXISTS (SELECT INSTANCE_ID, COUNT(*) FROM canon_i165_oki_tbl c WHERE a.instance_id = c.instance_id GROUP BY c.instance_id HAVING COUNT(*) > 1);
     
     COMMIT;
     
     EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_I165_OKI_TBL COMPUTE STATISTICS';
     
     for oki_rec in oki_cur loop
         update canon_i165_oki_tbl
           set batch_id = l_batch_id
          where contract_id = oki_rec.contract_id;
		 l_batch_id := l_batch_id + 1;
         if l_batch_id > 15
         then
		   IF l_loop_batch_id = 0 THEN
             l_loop_batch_id := l_batch_id - 1;
		   END IF;
             l_batch_id := 1;
         end if;
      end loop;
      
     --DBMS_OUTPUT.PUT_LINE('l_loop_batch_id: '||l_loop_batch_id);
    
     EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_I165_CIAB_STG_TBL';
     
     FOR i in 1..l_loop_batch_id
     LOOP
       canon_i165_sr_rep_pkg.LOAD_STG(p_batch_id => i);
     END LOOP;
     
     canon_i165_sr_rep_pkg.MERGE_BAD_SERIAL;
     canon_i165_sr_rep_pkg.INCIDENT_LINK;
     
    END;
    
  PROCEDURE LOAD_STG (p_batch_id IN NUMBER)
  IS
   cursor main is
   SELECT instance_id,
          serial_number,
          attribute1 branch_code,
          contract_id,
          sub_line_id contract_line_id
     FROM canon_i165_oki_tbl i165
    WHERE batch_id = p_batch_id
	  AND NOT EXISTS 
	      (SELECT 'X'
		     FROM CANON_S21_CD_TBL cd,  
			      CANON_S21_CD_VAL_TBL val 
            WHERE cd.cd_id = val.cd_id  
              AND nvl(is_active,'Y') ='Y' 
              AND cd.cd_name = 'CANON_E504_CRXSRV_SERIAL_MAP'
              AND val.VAL3 = i165.serial_number);
  
  cursor ciab (p_instance_id IN NUMBER)
    is
	  SELECT TO_DATE(SUBSTR(ciab.FSR_CRAT_DT||ciab.FSR_CRAT_TM,1,14),'YYYYMMDDHH24MISS') incident_creation_date
	        ,ciab.SVC_MACH_MSTR_PK instance_id
			,ciab.FSR_TP_CD
	        ,ciab.FSR_NUM incident_id
			,ciab.FSR_NUM incident_number
			,ciab.ship_to_cust_cd install_site_id
			,st.CUST_EML_ADDR caller_email
			,TO_DATE(SUBSTR(ciab.SVC_CALL_INCDT_DT||ciab.SVC_CALL_INCDT_TM,1,14),'YYYYMMDDHH24MISS') incident_date
			,fvt.SVC_PBLM_TP_CD problem_code
			,fvt.SVC_PBLM_CRCT_TP_CD resolution_code
			,(SELECT svc_pblm_tp_desc_txt FROM SVC_PBLM_TP spt WHERE spt.SVC_PBLM_TP_CD = ciab.SVC_PBLM_TP_CD) problem_summary
			,inc_types.DS_SVC_CALL_TP_NM incident_type
            ,ST.SVC_TASK_NUM
			,fvt.FSR_VISIT_NUM
	    FROM FSR ciab, svc_task_sts cisb, DS_SVC_CALL_TP inc_types, FSR_VISIT_TASK fvt, SVC_TASK st
	   WHERE ciab.SVC_MACH_MSTR_PK = p_instance_id
	     AND ciab.fsr_sts_cd = cisb.svc_task_sts_cd
	     AND ciab.FSR_TP_CD = inc_types.DS_SVC_CALL_TP_CD
		 AND ciab.FSR_NUM = st.FSR_NUM
		 AND ciab.FSR_NUM = fvt.FSR_NUM
		 AND st.SVC_TASK_NUM = fvt.SVC_TASK_NUM
		 AND ciab.glbl_cmpy_cd = 'ADB'
		 AND cisb.glbl_cmpy_cd = 'ADB'
		 AND inc_types.glbl_cmpy_cd = 'ADB'
		 AND fvt.glbl_cmpy_cd = 'ADB'
		 AND st.glbl_cmpy_cd = 'ADB'
		 AND ciab.EZCANCELFLAG = '0'
		 AND cisb.EZCANCELFLAG = '0'
		 AND inc_types.EZCANCELFLAG = '0'
		 AND fvt.EZCANCELFLAG = '0'
		 AND st.EZCANCELFLAG = '0'
	     AND UPPER(cisb.svc_task_sts_nm) = UPPER('Closed')
		 AND TRUNC(TO_DATE(ciab.FSR_CRAT_DT||ciab.FSR_CRAT_TM,'YYYYMMDDHH24MISS')) >= TO_DATE('20-JUN-2017','DD-MON-YYYY')
		 AND EXISTS (SELECT 1 
		               FROM CANON_S21_CD_TBL cd,  CANON_S21_CD_VAL_TBL val 
		              WHERE cd.cd_id = val.cd_id  
					    AND nvl(is_active,'Y') ='Y' 
                        AND cd.cd_name = 'CANON_I165_ELIG_CALL_TYPE'
						AND val.VAL1 = inc_types.DS_SVC_CALL_TP_CD||'-'||inc_types.DS_SVC_CALL_TP_NM)
	  ORDER BY ciab.FSR_NUM,st.SVC_TASK_NUM,fvt.FSR_VISIT_NUM;
  
    l_task_id			VARCHAR2(50);
    l_task_creation_date 		DATE;
    l_t_actual_start_date 		DATE;
    l_t_actual_end_date		DATE;
    l_dispatch_date			DATE;
    l_bw_read			NUMBER;
    l_bw_counter_value_id 		NUMBER;
    l_total_read 			NUMBER;
    l_total_counter_value_id 	NUMBER;
    l_dealer_code			VARCHAR2(4000);
    l_account_number		VARCHAR2(50);
    l_party_id			NUMBER;
    l_party_number			VARCHAR2(50);
    l_party_site_number		NUMBER;
    l_party_name    VARCHAr2(4000);
    l_counter NUMBER := 0;
    l_time_to_dispatch    NUMBER    :=    0;
    l_response_time       NUMBER    :=    0;
    l_repair_time         NUMBER    :=    0;
    l_downtime            NUMBER    :=    0;
    l_seq_no              NUMBER;
    l_bllg_mstr_pk        NUMBER;
    l_shift_start         VARCHAR2(30) := '08:30:00';
    l_shift_end           VARCHAR2(30) := '17:00:00';
    l_weekend_count_flag  VARCHAR2(30) := 'N';
    l_cusa_problem_code    VARCHAR2(50);
    l_cusa_correction_code VARCHAR2(50);
    l_color_read          NUMBER := 0;
    l_contact_note        VARCHAR2(4000);
  
  BEGIN
  
    FOR main_rec IN main
    LOOP
        l_dealer_code			:=  NULL;
        l_seq_no                :=  NULL;
        l_bllg_mstr_pk          :=  NULL;
        l_counter               := l_counter + 1;
    
	BEGIN
	  --SELECT V.VND_CD
	    --INTO l_dealer_code
       -- FROM SVC_MACH_MSTR smm,
       --      S21_PSN SP,
       --      CTAC_PSN CP,
       --      DS_CTAC_PSN_RELN DCPR,
       --      CTAC_TP CT,
       --      VND V
       --WHERE smm.SVC_MACH_MSTR_PK = main_rec.INSTANCE_ID
         --AND smm.PRF_TECH_CD = sp.PSN_CD
         --AND smm.GLBL_CMPY_CD = 'ADB'
		     --AND sp.glbl_cmpy_cd = 'ADB'
         --AND cp.glbl_cmpy_cd = 'ADB'
		     --AND dcpr.glbl_cmpy_cd = 'ADB'
		     --AND ct.glbl_cmpy_cd = 'ADB'
		     --AND v.glbl_cmpy_cd = 'ADB'
         --AND SMM.EZCANCELFLAG = '0'
		     --AND sp.EZCANCELFLAG = '0'
		     --AND cp.EZCANCELFLAG = '0'
		     --AND dcpr.EZCANCELFLAG = '0'
		     --AND ct.EZCANCELFLAG = '0'
		     --AND v.EZCANCELFLAG = '0'
         --AND SP.CTAC_PSN_PK = CP.CTAC_PSN_PK
         --AND CP.CTAC_PSN_PK = DCPR.CTAC_PSN_PK
         --AND DCPR.CTAC_TP_CD = CT.CTAC_TP_CD
         --AND DCPR.LOC_NUM = V.LOC_NUM;
         SELECT VND_CD
           INTO l_dealer_code
           FROM S21_PSN SP,
                CTAC_PSN CP,
                DS_CTAC_PSN_RELN DCPR,
                CTAC_TP CT,
                VND V, 
                svc_mach_mstr smm
          WHERE 1=1
            AND smm.SVC_MACH_MSTR_PK = main_rec.INSTANCE_ID
            AND SP.GLBL_CMPY_CD = SMM.GLBL_CMPY_CD
            AND SP.PSN_CD = SMM.PRF_TECH_CD
            AND SP.GLBL_CMPY_CD = SMM.GLBL_CMPY_CD
            AND SP.EZCANCELFLAG = '0'
            AND SP.DEL_FLG = 'N'
            AND SP.PSN_TP_CD <> '1'
            AND SP.EFF_FROM_DT <= TO_CHAR (SYSDATE, 'YYYYMMDD')
            AND NVL (SP.EFF_THRU_DT, TO_CHAR (SYSDATE, 'YYYYMMDD')) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
            AND CP.GLBL_CMPY_CD = SP.GLBL_CMPY_CD
            AND CP.CTAC_PSN_PK = SP.CTAC_PSN_PK
            AND CP.EZCANCELFLAG = '0'
            AND DCPR.EZCANCELFLAG = '0'
            AND DCPR.GLBL_CMPY_CD = CP.GLBL_CMPY_CD
            AND DCPR.CTAC_PSN_PK = CP.CTAC_PSN_PK
            AND DCPR.GLBL_CMPY_CD = CT.GLBL_CMPY_CD
            AND DCPR.CTAC_TP_CD = CT.CTAC_TP_CD
            --  AND CT.SPLY_IND_FLG = 'Y'
            AND CT.ACTV_FLG = 'Y'
            AND CT.EZCANCELFLAG = '0'
            AND V.EZCANCELFLAG = '0'
            AND V.GLBL_CMPY_CD = DCPR.GLBL_CMPY_CD
            AND V.LOC_NUM = DCPR.LOC_NUM
            AND rownum = 1;
	EXCEPTION
	WHEN OTHERS THEN
      l_dealer_code := NULL;
	END;
	
	BEGIN
	  SELECT MAX(ROSS_INTFC_CONTR_PK)
	    INTO l_seq_no
	 	  FROM ROSS_INTFC_CONTR
 	   WHERE SER_NUM = main_rec.SERIAL_NUMBER;
    --SELECT max(seq_no)
    --  INTO l_seq_no
    --  FROM CANON_ROSS_CONTRACT
    -- WHERE serial_nbr = main_rec.SERIAL_NUMBER;
	EXCEPTION
	  WHEN OTHERS THEN
	    l_seq_no := NULL;
    END;
	
  IF l_seq_no IS NOT NULL
  THEN
      SELECT BLLG_MSTR_PK 
	    INTO l_bllg_mstr_pk 
		FROM ROSS_INTFC_CONTR
       WHERE SER_NUM = main_rec.SERIAL_NUMBER
         AND ROSS_INTFC_CONTR_PK = l_seq_no
	     AND rownum = 1;
    --SELECT BM_ID
	  --  INTO l_bllg_mstr_pk 
		--FROM CANON_ROSS_CONTRACT
  --WHERE SERIAL_NBR = main_rec.SERIAL_NUMBER
  --  AND SEQ_NO = l_seq_no
	--  AND rownum = 1;
    
    END IF;
	
	for ciab_rec in ciab(main_rec.instance_id)
    loop
          l_task_id			:= NULL;
          l_task_creation_date 		:= NULL;
          l_t_actual_start_date 		:= NULL;
          l_t_actual_end_date		:= NULL;
          l_dispatch_date			:= NULL;
          l_bw_read			:= NULL;
          l_color_read := NULL;
          l_bw_counter_value_id 		:= NULL;
          l_total_read 			:= NULL;
          l_total_counter_value_id 	:= NULL;
          l_account_number		:= NULL;
          l_party_id			:= NULL;
          l_party_number			:= NULL;
          l_party_site_number		:= NULL;
          l_time_to_dispatch      :=    0;
          l_response_time         :=    0;
          l_repair_time           :=    0;
          l_downtime              :=    0;
          l_cusa_problem_code     :=  NULL;
          l_cusa_correction_code  :=  NULL;
		  l_contact_note := NULL;
	 
	  BEGIN
	  SELECT st.SVC_CUST_ATTN_TXT||' '||DECODE(st.CUST_TEL_NUM,'0',NULL,st.CUST_TEL_NUM)||' '||st.CUST_TEL_EXTN_NUM
	    INTO l_contact_note
		FROM SVC_TASK st,
		     SVC_MACH_MSTR smm
	   WHERE smm.SVC_MACH_MSTR_PK = st.SVC_MACH_MSTR_PK
	     AND smm.glbl_cmpy_cd = 'ADB'
		 AND st.glbl_cmpy_cd = 'ADB'
		 AND smm.EZCANCELFLAG = '0'
		 AND st.EZCANCELFLAG = '0'
		 AND smm.ser_num = main_rec.serial_number
		 AND st.SVC_TASK_NUM = ciab_rec.SVC_TASK_NUM
		 AND st.FSR_NUM = ciab_rec.incident_number
		 AND st.FIRST_SVC_TASK_FLG = 'Y';
	 -- SELECT sm.svc_cmnt_txt
	   -- INTO l_contact_note
        --FROM SVC_MACH_MSTR smm,
        --     DS_CONTR_DTL dcd,
        --     SVC_MEMO sm
       --WHERE smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk
       --  AND dcd.DS_CONTR_DTL_PK = sm.DS_CONTR_DTL_PK
       --  AND smm.ser_num = main_rec.serial_number
	   --	 AND smm.glbl_cmpy_cd = 'ADB'
	   --	 AND dcd.glbl_cmpy_cd = 'ADB'
	   --	 AND sm.glbl_cmpy_cd = 'ADB'
	   --	 AND smm.EZCANCELFLAG = '0'
	   --	 AND dcd.EZCANCELFLAG = '0'
	   --	 AND sm.EZCANCELFLAG = '0'
       --  AND TO_DATE(SUBSTR(sm.last_upd_ts,1,14),'YYYYMMDDHH24MISS') =
       --    (
       --     SELECT MAX(TO_DATE(SUBSTR(sm.last_upd_ts,1,14),'YYYYMMDDHH24MISS')) time
       --       FROM SVC_MACH_MSTR smm,
       --            DS_CONTR_DTL dcd,
       --            SVC_MEMO sm
       --      WHERE smm.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk
       --        AND dcd.DS_CONTR_DTL_PK = sm.DS_CONTR_DTL_PK
       --        AND smm.ser_num = main_rec.serial_number
	   --		   AND smm.glbl_cmpy_cd = 'ADB'
	   --	       AND dcd.glbl_cmpy_cd = 'ADB'
	--	       AND sm.glbl_cmpy_cd = 'ADB'
	--	       AND smm.EZCANCELFLAG = '0'
	--	       AND dcd.EZCANCELFLAG = '0'
	--	       AND sm.EZCANCELFLAG = '0'
	--	   );                
	EXCEPTION
	WHEN OTHERS THEN
	  l_contact_note := NULL;
	END;
	  
	  BEGIN
        SELECT jtb.SVC_TASK_NUM,
               TO_DATE(jtb.SVC_TASK_RCV_DT||jtb.SVC_TASK_RCV_TM,'YYYYMMDDHH24MISS') creation_date,
               TO_DATE(fv.FSR_VISIT_ARV_DT||fv.FSR_VISIT_ARV_TM,'YYYYMMDDHH24MISS') actual_start_date,
               TO_DATE(jtb.SVC_TASK_CPLT_DT||jtb.SVC_TASK_CPLT_TM,'YYYYMMDDHH24MISS') actual_end_date
          INTO l_task_id,
               l_task_creation_date,
               l_t_actual_start_date,
               l_t_actual_end_date
          FROM SVC_TASK jtb
		      ,FSR_VISIT fv
         WHERE jtb.FSR_NUM = ciab_rec.incident_id
		   AND jtb.SVC_TASK_NUM = ciab_rec.SVC_TASK_NUM
		   AND jtb.SVC_TASK_NUM = fv.SVC_TASK_NUM
		   AND fv.EZCANCELFLAG = '0'
		   AND fv.glbl_cmpy_cd = 'ADB'
		   AND jtb.EZCANCELFLAG = '0'
		   AND jtb.glbl_cmpy_cd = 'ADB';
      EXCEPTION
        WHEN OTHERS
          THEN
          l_task_id 		:= NULL;
          l_task_creation_date 	:= NULL;
          l_t_actual_start_date	:= NULL;
          l_t_actual_end_date	:= NULL;
      END;
	  
    BEGIN
      SELECT MIN(TO_DATE(fv.FSR_VISIT_DISPT_DT||fv.FSR_VISIT_DISPT_TM,'YYYYMMDDHH24MISS'))
        INTO l_dispatch_date
        FROM FSR_VISIT fv,
		     SVC_TASK st,
			 SVC_TASK_STS sts
       WHERE fv.fsr_num = ciab_rec.incident_id
	     AND fv.SVC_TASK_NUM = ciab_rec.SVC_TASK_NUM
		 AND fv.FSR_VISIT_NUM = ciab_rec.FSR_VISIT_NUM
	     AND fv.fsr_num = st.fsr_num
		 AND fv.svc_task_num = st.svc_task_num
		 AND st.svc_task_sts_cd = sts.svc_task_sts_cd
	     AND fv.glbl_cmpy_cd = 'ADB'
		 AND fv.EZCANCELFLAG = '0'
		 AND st.glbl_cmpy_cd = 'ADB'
		 AND st.EZCANCELFLAG = '0'
		 AND sts.glbl_cmpy_cd = 'ADB'
		 AND sts.EZCANCELFLAG = '0'
	     AND sts.svc_task_sts_cd IN (SELECT val.VAL1
                                         FROM CANON_S21_CD_TBL cd,  CANON_S21_CD_VAL_TBL val 
                                        WHERE cd.cd_id = val.cd_id  
			   							  AND nvl(is_active,'Y') ='Y' 
                                          AND cd.cd_name = 'CANON_I165_DISP_STATUSES');
	EXCEPTION
	  WHEN OTHERS THEN
	    l_dispatch_date := NULL;
    END;
	
	BEGIN
	  SELECT MAX (DECODE (upper(meter_description),'BW COPIES', BILLING_COUNTER_READ)) BW
      INTO l_bw_read
        FROM
          (
           SELECT DISTINCT 
		          spm.mdl_mtr_lb_note_txt meter_description, 
                  spmr.MTR_READ_DT BILLING_COUNTER_DATE,
                  spmr.READ_MTR_CNT BILLING_COUNTER_READ,
                  spmr.SVC_PHYS_MTR_READ_GRP_SQ,
                  spm.SVC_MACH_MSTR_PK
		     FROM SVC_PHYS_MTR spm,
		          SVC_PHYS_MTR_READ spmr
	        WHERE spm.SVC_PHYS_MTR_PK = spmr.SVC_PHYS_MTR_PK
	          AND spm.glbl_cmpy_cd = 'ADB'
		      AND spmr.glbl_cmpy_cd = 'ADB'
			  AND spm.EZCANCELFLAG = '0'
			  AND spmr.EZCANCELFLAG = '0'
		      AND spm.actv_flg = 'Y'
		      AND spm.mdl_mtr_lb_note_txt IN ('BW Copies')
		      AND spmr.DS_MTR_READ_TP_CD = 'SVC'
		      AND spmr.vld_mtr_flg = 'Y'
              AND spm.SVC_MACH_MSTR_PK = ciab_rec.instance_id
			  AND spmr.FSR_NUM = ciab_rec.incident_number
			  AND spmr.SVC_TASK_NUM = ciab_rec.SVC_TASK_NUM
          );
	EXCEPTION
	WHEN OTHERS THEN
	  l_bw_read := 0;
	END;
	
    BEGIN
	  SELECT MAX (DECODE (upper(meter_description),'TOTAL COPIES', BILLING_COUNTER_READ)) BW
        INTO l_total_read
        FROM
          (
           SELECT DISTINCT 
		          spm.mdl_mtr_lb_note_txt meter_description, 
                  spmr.MTR_READ_DT BILLING_COUNTER_DATE,
                  spmr.READ_MTR_CNT BILLING_COUNTER_READ,
                  spmr.SVC_PHYS_MTR_READ_GRP_SQ,
                  spm.SVC_MACH_MSTR_PK
		     FROM SVC_PHYS_MTR spm,
		          SVC_PHYS_MTR_READ spmr
	        WHERE spm.SVC_PHYS_MTR_PK = spmr.SVC_PHYS_MTR_PK
	          AND spm.glbl_cmpy_cd = 'ADB'
		      AND spmr.glbl_cmpy_cd = 'ADB'
			  AND spm.EZCANCELFLAG = '0'
			  AND spmr.EZCANCELFLAG = '0'
		      AND spm.actv_flg = 'Y'
		      AND spm.mdl_mtr_lb_note_txt IN ('Total Copies')
		      AND spmr.DS_MTR_READ_TP_CD = 'SVC'
		      AND spmr.vld_mtr_flg = 'Y'
              AND spm.SVC_MACH_MSTR_PK = ciab_rec.instance_id
			  AND spmr.FSR_NUM = ciab_rec.incident_number
			  AND spmr.SVC_TASK_NUM = ciab_rec.SVC_TASK_NUM
          );
	EXCEPTION
	WHEN OTHERS THEN
	  l_total_read := 0;
	END;
	
	IF l_bw_read = 0  AND l_total_read <> 0
    THEN
     l_color_read := 0;
     l_bw_read := l_total_read;
    ELSIF l_bw_read > 0 and l_total_read > 0
    THEN
     l_color_read := l_total_read - l_bw_read;
    END IF;
	
	BEGIN
	  SELECT ship_to.ship_to_cust_cd,ship_to.ship_to_cust_cd,ship_to.ship_to_cust_cd,ship_to.loc_nm,sell_to.sell_to_cust_cd
	    INTO l_party_id,l_party_number,l_party_site_number,l_party_name,l_account_number
		FROM svc_mach_mstr ib
		    ,ship_to_cust ship_to
			,sell_to_cust sell_to
	   WHERE ship_to.ship_to_cust_cd = ib.cur_loc_num
	     AND ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
	     AND ib.svc_mach_mstr_pk = ciab_rec.instance_id
		 AND ib.glbl_cmpy_cd = 'ADB'
		 AND ship_to.glbl_cmpy_cd = 'ADB'
		 AND sell_to.glbl_cmpy_cd = 'ADB'
		 AND ib.EZCANCELFLAG = '0'
		 AND ship_to.EZCANCELFLAG = '0'
		 AND sell_to.EZCANCELFLAG = '0'
	     AND NVL (ship_to.eff_thru_dt,TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
         AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <= TO_CHAR (SYSDATE, 'YYYYMMDD')
		 AND NVL (sell_to.eff_thru_dt,TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
         AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <= TO_CHAR (SYSDATE, 'YYYYMMDD');
		 
	EXCEPTION
	WHEN OTHERS THEN
	  l_party_id 		    := NULL;
	  l_party_number 		:= NULL;
	  l_party_site_number   := NULL;
	  l_party_name          := NULL;
	  l_account_number      := NULL;
	END;
	
	--- Time to Dispatch Calculation
    IF l_dispatch_date IS NOT NULL
    THEN
      l_time_to_dispatch :=
        SERV_RESP_TIME_FUNC
        (p_from_date                  =>  ciab_rec.incident_date,
         p_to_date                    =>  l_dispatch_date,
         p_shift_start_time           =>  l_shift_start,
         p_shift_end_time             =>  l_shift_end,
         p_weekend_count_flag         =>  l_weekend_count_flag
        );
    END IF;
	
	--- Response Time Calculation
    IF l_t_actual_start_date IS NOT NULL
    THEN
      l_response_time :=
        SERV_RESP_TIME_FUNC
        (p_from_date                  =>  ciab_rec.incident_date,
         p_to_date                    =>  l_t_actual_start_date,
         p_shift_start_time           =>  l_shift_start,
         p_shift_end_time             =>  l_shift_end,
         p_weekend_count_flag         =>  l_weekend_count_flag
        );
    END IF;

    IF l_t_actual_start_date IS NOT NULL
    THEN
      l_repair_time := (l_t_actual_end_date - l_t_actual_start_date)*24 ;
	END IF;
	
	IF l_t_actual_start_date IS NOT NULL
    THEN
      l_downtime :=
        SERV_RESP_TIME_FUNC
        (p_from_date                  =>  ciab_rec.incident_date,
         p_to_date                    =>  l_t_actual_end_date,
         p_shift_start_time           =>  l_shift_start,
         p_shift_end_time             =>  l_shift_end,
         p_weekend_count_flag         =>  l_weekend_count_flag
         );
    END IF;
	
	BEGIN
	  --SELECT val.VAL3 
	  --  INTO l_cusa_problem_code
      --  FROM CANON_S21_CD_TBL cd,  
      --       CANON_S21_CD_VAL_TBL val 
      -- WHERE cd.cd_id = val.cd_id  
      --   AND nvl(is_active,'Y') ='Y' 
      --   AND cd.cd_name = 'CANON_I165_PROBLEM_CODES';
	  SELECT SVC_PBLM_TP_CD
	    INTO l_cusa_problem_code
		FROM SVC_PBLM_TP spt
	   WHERE spt.EZCANCELFLAG = '0'
	     AND spt.GLBL_CMPY_CD = 'ADB'
		 AND spt.SVC_PBLM_TP_CD = ciab_rec.PROBLEM_CODE;
	EXCEPTION
	WHEN OTHERS THEN
	  l_cusa_problem_code := ciab_rec.PROBLEM_CODE;
	END;
	
	BEGIN
	  --SELECT val.VAL3 
	  --  INTO l_cusa_correction_code
      --  FROM CANON_S21_CD_TBL cd,  
      --       CANON_S21_CD_VAL_TBL val 
      -- WHERE cd.cd_id = val.cd_id  
      --   AND nvl(is_active,'Y') ='Y' 
      --   AND cd.cd_name = 'CANON_I165_CORRECTION_CODES';
	  SELECT SVC_PBLM_CRCT_TP_CD
	    INTO l_cusa_correction_code
		FROM SVC_PBLM_CRCT_TP spt
	   WHERE spt.EZCANCELFLAG = '0'
	     AND spt.GLBL_CMPY_CD = 'ADB'
		 AND spt.SVC_PBLM_CRCT_TP_CD = ciab_rec.RESOLUTION_CODE;
	EXCEPTION
	WHEN OTHERS THEN
	  l_cusa_correction_code := ciab_rec.RESOLUTION_CODE;
	END;
	
	BEGIN
	INSERT INTO CANON_I165_CIAB_STG_TBL
	   (INCIDENT_ID 		,
	    INCIDENT_NUMBER	 	,
	    INSTANCE_ID			,
	    CUSTOMER_NAME		,
	    PARTY_SITE_ID		,
	    PARTY_SITE_NUMBER		,
	    ACCOUNT_NUMBER		,
	    PARTY_ID			,
	    PARTY_NUMBER		,
	    CALLER_EMAIL		,
	    INCIDENT_DATE		,
	    INCIDENT_CREATION_DATE	,
        PROBLEM_CODE,
	    RESOULTION_CODE		,
	    PROBLEM_SUMMARY		,
	    CONTRACT_ID			,
	    CONTRACT_LINE_ID		,
	    TASK_ID			,
	    TASK_CREATION_DATE		,
	    ACTUAL_START_DATE		,
	    ACTUAL_END_DATE		,
	    DISPATCH_DATE		,
	    BW_READING			,
        TOTAL_READING,
        COLOR_READING,
	    DEALER_CODE			,
	    CREATION_DATE,
        BRANCH_CODE,
        TIME_TO_DISPATCH,
        RESPONSE_TIME,
        REPAIR_TIME,
        DOWN_TIME,
        BLLG_MSTR_PK,
        SERIAL_NUMBER,
        CONTACT_NOTE,
        SR_TYPE
)
	VALUES
	    (
		ciab_rec.INCIDENT_ID,
		ciab_rec.INCIDENT_NUMBER,
		main_rec.INSTANCE_ID,
		l_party_name,
		ciab_rec.install_site_id,
        l_party_site_number,
		l_account_number,
		l_party_id,
		l_party_number,
		ciab_rec.caller_email,
		ciab_rec.incident_date,
		ciab_rec.incident_creation_date,
        l_cusa_problem_code,
		l_cusa_correction_code,
		ciab_rec.problem_summary,
		main_rec.contract_id,
		main_rec.contract_line_id,
		l_task_id,
		l_task_creation_date,
        l_t_actual_start_date,
	    l_t_actual_end_date,
		l_dispatch_date,
		l_bw_read,
		l_total_read,
        l_color_read,
		l_dealer_code,
        SYSDATE,
        main_rec.branch_code,
        l_time_to_dispatch,
        l_response_time,
        l_repair_time,
        l_downtime,
        l_bllg_mstr_pk,
        main_rec.serial_number,
        l_contact_note,
        ciab_rec.incident_type
    );
    END;
	END LOOP;
	IF l_counter > 100 THEN
      COMMIT;
      l_counter := 0;
    END IF;
   END LOOP;
END LOAD_STG;  
  FUNCTION SERV_RESP_TIME_FUNC
      (p_from_date                  DATE,
       p_to_date                    DATE,
       p_shift_start_time           VARCHAR2,
       p_shift_end_time             VARCHAR2,
       p_weekend_count_flag         VARCHAR2
       )
    RETURN NUMBER
     IS
      l_total_days                  NUMBER;
      l_curr_date                   DATE;
      --l_e_flag                      VARCHAR2(30);
      l_total_hours                 NUMBER := 0;
      l_day_hours                   NUMBER := 0;
      l_shift_start_time            VARCHAR2(30);
      l_shift_end_time              VARCHAR2(30);
      l_shift_start_date            DATE;
      l_shift_end_date              DATE;
      l_working_hours               NUMBER;
       BEGIN
         l_total_days           :=  trunc(p_to_date) - trunc(p_from_date) + 1;
         l_total_hours          :=  0;
         l_shift_start_time     :=  p_shift_start_time;
         l_shift_end_time       :=  p_shift_end_time;
         l_working_hours := to_date('01-JAN-1980' || ' ' || p_shift_end_time, 'DD-MON-YYYY HH24:MI:SS') -
                            to_date('01-JAN-1980' || ' ' || p_shift_start_time, 'DD-MON-YYYY HH24:MI:SS');
            
			FOR i in 1..l_total_days
            LOOP
               l_curr_date := TRUNC(p_from_date) + i - 1;
               l_day_hours := NULL;
			   
              IF substr(to_char(l_curr_date,'DAY'),1,3)  IN ('SAT', 'SUN') AND p_weekend_count_flag = 'N'
              THEN
                l_day_hours := 0;
              END IF;
              IF l_day_hours IS NULL
              THEN
                l_shift_start_date := to_date(to_char(l_curr_date, 'DD-MON-RRRR')  || ' ' || l_shift_start_time, 'DD-MON-RRRR HH24:MI:SS');
                l_shift_end_date   := to_date(to_char(l_curr_date, 'DD-MON-RRRR')  || ' ' || l_shift_end_time, 'DD-MON-RRRR HH24:MI:SS');
                IF i = 1 and l_total_days > 1
                THEN
                  l_day_hours := l_shift_end_date - p_from_date;
                  IF l_day_hours < 0
                  THEN
                    l_day_hours := 0;
                  END IF;
                END IF;
                 IF i = l_total_days and l_total_days > 1
                 THEN
                   l_day_hours := LEAST(p_to_date, l_shift_end_date) - l_shift_start_date;
                 END IF;
                 IF TRUNC(p_from_date) = TRUNC(p_to_date)
                 THEN
                   l_day_hours := LEAST(p_to_date, l_shift_end_date) - GREATEST(p_from_date, l_shift_start_date);
                 ELSIF i != 1 and i != l_total_days
                 THEN
                   l_day_hours := l_working_hours;
                 END IF;
               END IF;
               IF l_day_hours < 0
               THEN
                 l_day_hours := 0;
               END IF;
               l_total_hours := l_total_hours + nvl(l_day_hours,0);
             END LOOP;
             RETURN l_total_hours*24;
     END SERV_RESP_TIME_FUNC;

	 PROCEDURE INCIDENT_LINK IS
	   CURSOR C1 IS
	     SELECT FV.FSR_NUM, FV.SVC_TASK_NUM, FV.FSR_VISIT_NUM
		   FROM CANON_I165_CIAB_STG_TBL CICST,
		        FSR_VISIT FV
		  WHERE CICST.incident_id = FV.FSR_NUM
		    AND FV.glbl_cmpy_cd = 'ADB'
			AND FV.EZCANCELFLAG = '0'
		ORDER BY FV.FSR_NUM,FV.SVC_TASK_NUM,FV.FSR_VISIT_NUM;
		
	   CURSOR C2 IS
	     SELECT DISTINCT INCIDENT_ID, GROUP_ID
		   FROM CANON_I165_CIAB_LINK_TBL;
		
     l_incident_id              VARCHAR2(50) := NULL;
	 l_task_id                  VARCHAR2(50) := NULL;
	 l_previous_incident_id     VARCHAR2(50) := NULL;
	 l_previous_task_id         VARCHAR2(50);
	 l_previous_visit_number    VARCHAR2(10);
	 l_incident_status_id       VARCHAR2(2);
	 l_customer_product_id      NUMBER;
	 l_incident_status          VARCHAR2(50);
	 l_task_creation_date       DATE;
	 l_t_actual_start_date      DATE; 
	 l_t_actual_end_date        DATE; 
	 l_incident_date            DATE;
     l_repair_time              NUMBER;
	 l_accumulated_repair_time  NUMBER;
	 l_parent_flag              VARCHAR2(1);
	 l_shift_start              VARCHAR2(30) := '08:30:00';
     l_shift_end                VARCHAR2(30) := '17:00:00';
     l_weekend_count_flag       VARCHAR2(30) := 'N';
	 l_down_time               NUMBER;
     l_link_group_id      NUMBER;
     l_dt_incident_date      DATE;
     l_dt_last_task_end_date DATE;
     l_exist_count  NUMBER;
     l_visit_number VARCHAR2(2);
	 l_sequence_number NUMBER;
	 
	 BEGIN
	 
	   EXECUTE IMMEDIATE 'TRUNCATE TABLE CANON_I165_CIAB_LINK_TBL';
	   dbms_output.put_line('table truncated:');
     
	   FOR c1rec IN c1 LOOP
     
	     l_incident_id := c1rec.FSR_NUM;
		 l_task_id := c1rec.SVC_TASK_NUM;
	     l_visit_number := c1rec.FSR_VISIT_NUM;
	     l_down_time := 0;
		 l_previous_incident_id := NULL;
		 l_previous_task_id := NULL;
		 l_previous_visit_number := NULL;
		 l_exist_count := NULL;
	 
	   SELECT COUNT(*) 
	     INTO l_exist_count
         FROM CANON_I165_CIAB_LINK_TBL
        WHERE incident_id = c1rec.FSR_NUM
		  AND task_id = c1rec.svc_task_num
		  AND visit_number = c1rec.fsr_visit_num;

	 IF l_exist_count = 0
     THEN
	   
          l_incident_status_id := NULL;
          l_customer_product_id := NULL;
          l_incident_status := NULL;
          l_task_creation_date := NULL;
          l_t_actual_start_date := NULL;
          l_t_actual_end_date := NULL;
		  l_incident_date := NULL;
		  l_repair_time := 0;
		  --l_accumulated_repair_time := 0;
          --l_link_group_id := NULL;
                
      BEGIN
		    SELECT ciab.FSR_STS_CD, ciab.SVC_MACH_MSTR_PK, cist.svc_task_sts_nm
			    INTO l_incident_status_id, l_customer_product_id, l_incident_status
			    FROM FSR ciab, svc_task_sts cist
			   WHERE ciab.FSR_STS_CD = cist.SVC_TASK_STS_CD
			     AND ciab.FSR_NUM = l_incident_id
			     AND ciab.glbl_cmpy_cd = 'ADB'
			     AND cist.glbl_cmpy_cd = 'ADB'
				 AND ciab.EZCANCELFLAG = '0'
				 AND cist.EZCANCELFLAG = '0';
      END;
		
     BEGIN
			SELECT TO_DATE(jtb.SVC_TASK_RCV_DT||jtb.SVC_TASK_RCV_TM,'YYYYMMDDHH24MISS'),
                   TO_DATE(fsr_visits.FSR_VISIT_ARV_DT||fsr_visits.FSR_VISIT_ARV_TM,'YYYYMMDDHH24MISS'),
                   TO_DATE(jtb.SVC_TASK_CPLT_DT||jtb.SVC_TASK_CPLT_TM,'YYYYMMDDHH24MISS'),
				   TO_DATE(SUBSTR(ciab.SVC_CALL_INCDT_DT||ciab.SVC_CALL_INCDT_TM,1,14),'YYYYMMDDHH24MISS')
			  INTO l_task_creation_date, l_t_actual_start_date, l_t_actual_end_date, l_incident_date
			  FROM SVC_TASK jtb,FSR ciab, DS_SVC_CALL_TP inc_types, FSR_VISIT fsr_visits
			 WHERE jtb.FSR_NUM = ciab.FSR_NUM
			   AND jtb.FSR_NUM = l_incident_id
			   AND jtb.SVC_TASK_NUM = l_task_id
			   AND jtb.glbl_cmpy_cd = 'ADB' 
			   AND ciab.glbl_cmpy_cd = 'ADB'
			   AND inc_types.glbl_cmpy_cd = 'ADB'
			   AND fsr_visits.FSR_VISIT_NUM = l_visit_number
			   AND fsr_visits.glbl_cmpy_cd = 'ADB'
			   AND jtb.EZCANCELFLAG = '0'
			   AND ciab.EZCANCELFLAG = '0'
			   AND inc_types.EZCANCELFLAG = '0'
			   AND fsr_visits.EZCANCELFLAG = '0'
			   AND fsr_visits.fsr_num = ciab.fsr_num
			   AND fsr_visits.svc_task_num = jtb.svc_task_num 
			   AND ciab.FSR_TP_CD = inc_types.DS_SVC_CALL_TP_CD
			   AND EXISTS (SELECT 1 
		                   FROM CANON_S21_CD_TBL cd,  CANON_S21_CD_VAL_TBL val 
		                  WHERE cd.cd_id = val.cd_id  
		    	            AND nvl(is_active,'Y') ='Y' 
                            AND cd.cd_name = 'CANON_I165_ELIG_CALL_TYPE'
                            AND val.VAL1 = inc_types.DS_SVC_CALL_TP_CD||'-'||inc_types.DS_SVC_CALL_TP_NM);
          EXCEPTION
            WHEN OTHERS
                THEN
                l_task_creation_date 	:= NULL;
                l_t_actual_start_date	:= NULL;
                l_t_actual_end_date	:= NULL;
                l_incident_date := NULL;
          END;
		   
	 IF l_t_actual_start_date IS NOT NULL and l_t_actual_end_date IS NOT NULL
     THEN
       l_repair_time := (l_t_actual_end_date - l_t_actual_start_date)*24;
     END IF;
     
	 IF NVL(l_previous_incident_id,'-1') <> l_incident_id 
     THEN		
	   
	   l_accumulated_repair_time := 0;
       l_accumulated_repair_time := l_accumulated_repair_time + l_repair_time;
	   
	   l_sequence_number := 1;
	   
	   SELECT CANON_I165_CIAB_LINK_S.NEXTVAL 
		 INTO l_link_group_id 
		 FROM DUAL;
	   
	 ELSE
	   l_accumulated_repair_time := l_accumulated_repair_time + l_repair_time;
	 END IF;
		 
	 --IF c1rec.FSR_VISIT_NUM = '01' THEN 
     --  l_parent_flag := 'Y'; 
     --END IF;
	 
	 IF l_sequence_number = 1 THEN 
	   l_parent_flag := 'Y';
	 END IF;
	 
	 INSERT  
     WHEN EXISTS
		 (SELECT 'X'
			FROM FSR ciab, DS_SVC_CALL_TP inc_types
		   WHERE 1 = 1
			 AND ciab.FSR_NUM = l_incident_id
			 AND ciab.FSR_TP_CD = inc_types.DS_SVC_CALL_TP_CD
			 AND ciab.glbl_cmpy_cd = 'ADB'
			 AND inc_types.glbl_cmpy_cd = 'ADB'
			 AND ciab.EZCANCELFLAG = '0'
			 AND inc_types.EZCANCELFLAG = '0'
			 AND EXISTS (SELECT 1 
                     FROM CANON_S21_CD_TBL cd,  CANON_S21_CD_VAL_TBL val 
                    WHERE cd.cd_id = val.cd_id  
                      AND nvl(is_active,'Y') ='Y' 
                      AND cd.cd_name = 'CANON_I165_ELIG_CALL_TYPE'
                      AND val.VAL1 = inc_types.DS_SVC_CALL_TP_CD||'-'||inc_types.DS_SVC_CALL_TP_NM)
		 ) THEN
       INTO CANON_I165_CIAB_LINK_TBL
              (INCIDENT_ID,
               GROUP_ID,
			   SEQUENCE_NUMBER,
               VISIT_NUMBER,
               INCIDENT_DATE,
               TASK_ID,
               TASK_CREATION_DATE,
               TASK_START_DATE,
               TASK_END_DATE,
               PARENT_FLAG,
               ACCUMULATED_REPAIR_TIME,
               CUSTOMER_PRODUCT_ID,
               INCIDENT_STATUS_ID,
               INCIDENT_STATUS
			  )
         VALUES
         (l_incident_id,
          l_link_group_id,
		  l_sequence_number,
          c1rec.fsr_visit_num,
          l_incident_date,
          l_task_id,
          l_task_creation_date,
          l_t_actual_start_date,
          l_t_actual_end_date,
          l_parent_flag,
          l_accumulated_repair_time,
          l_customer_product_id,
          l_incident_status_id,
          l_incident_status
          )
          SELECT 1 FROM DUAL;
     	 
   END IF;

      l_previous_incident_id := l_incident_id;
	  l_previous_task_id := l_task_id;
	  l_previous_visit_number := l_visit_number;
      l_sequence_number := l_sequence_number + 1;	  
	  
	 END LOOP;
	 
	 COMMIT;
	 
	    UPDATE CANON_I165_CIAB_LINK_TBL a
		   SET accumulated_repair_time = (SELECT MAX(accumulated_repair_time)
		                                    FROM CANON_I165_CIAB_LINK_TBL b
		  								                 WHERE a.group_id = b.group_id)
      WHERE EXISTS(SELECT 1 FROM CANON_I165_CIAB_LINK_TBL c WHERE a.group_id = c.group_id HAVING COUNT(*) > 1);
		   
        UPDATE CANON_I165_CIAB_LINK_TBL a
           SET parent_flag = 'L'
         WHERE SEQUENCE_NUMBER IN  (SELECT MAX(SEQUENCE_NUMBER) 
		                              FROM CANON_I165_CIAB_LINK_TBL b
                                     WHERE a.group_id = b.group_id
                                       AND UPPER(b.incident_status) NOT IN ('CANCELLED')
									   AND EXISTS (SELECT 1 FROM CANON_I165_CIAB_LINK_TBL c WHERE b.group_id = c.group_id HAVING COUNT(*) > 1));
       
       UPDATE CANON_I165_CIAB_LINK_TBL a
          SET (first_task_start_date, first_task_end_date) = (SELECT task_start_date, task_end_date 
		                                                        FROM CANON_I165_CIAB_LINK_TBL b
                                                               WHERE a.group_id = b.group_id
															     AND a.incident_id = b.incident_id
                                                                 AND b.parent_flag = 'Y'
                                                             );
                                      
       UPDATE CANON_I165_CIAB_LINK_TBL a
          SET (last_task_start_date, last_task_end_date) = (SELECT task_start_date, task_end_date 
		                                                      FROM CANON_I165_CIAB_LINK_TBL b
                                                             WHERE a.group_id = b.group_id
															   AND a.incident_id = b.incident_id
                                                               AND b.parent_flag = 'L');  
		

	     UPDATE CANON_I165_CIAB_LINK_TBL a
          SET (last_task_start_date, last_task_end_date) = (SELECT task_start_date, task_end_date 
		                                                      FROM CANON_I165_CIAB_LINK_TBL b
                                                             WHERE a.group_id = b.group_id
															   AND a.incident_id = b.incident_id
                                                               AND b.parent_flag = 'Y')
		 WHERE last_task_start_date IS NULL
		   AND last_task_end_date IS NULL;
		   
	   COMMIT;

     FOR c2rec IN C2 LOOP
       BEGIN   
         SELECT a.incident_date, a.last_task_end_date
           INTO l_dt_incident_date, l_dt_last_task_end_date
           FROM CANON_I165_CIAB_LINK_TBL a
          WHERE a.group_id = c2rec.group_id
            AND a.parent_flag = 'Y'
            AND NOT EXISTS (SELECT 'X' 
			                  FROM CANON_I165_CIAB_LINK_TBL b
                             WHERE a.GROUP_ID = b.GROUP_ID
                               AND UPPER(b.INCIDENT_STATUS) IN ('Open'));
      EXCEPTION 
	    WHEN OTHERS THEN
          l_dt_incident_date := NULL;
          l_dt_last_task_end_date := NULL;       
      END;
	  
      IF(l_dt_incident_date IS NOT NULL AND l_dt_last_task_end_date is NOT NULL) THEN
        BEGIN       
          l_down_time := SERV_RESP_TIME_FUNC(l_dt_incident_date, l_dt_last_task_end_date,l_shift_start, l_shift_end,l_weekend_count_flag);
        EXCEPTION
        WHEN OTHERS THEN
          l_down_time := 0;    
        END; 
      END IF;
        
       UPDATE CANON_I165_CIAB_LINK_TBL a
          SET down_time = TO_NUMBER(l_down_time)
        WHERE a.group_id = c2rec.group_id;
          --AND a.parent_flag = 'Y';		 
      
      UPDATE CANON_I165_CIAB_STG_TBL a
         SET (REPAIR_TIME_TOTAL,
		      DOWN_TIME_TOTAL,
			  FC_ACTUAL_START_DATE, 
			  FC_ACTUAL_END_DATE, 
			  LC_ACTUAL_START_DATE, 
			  LC_ACTUAL_END_DATE,
			  GROUP_ID, 
			  RECORD_FLAG
			 )
           = (SELECT ACCUMULATED_REPAIR_TIME, 
		             DOWN_TIME, 
					 FIRST_TASK_START_DATE,  
					 FIRST_TASK_END_DATE, 
					 LAST_TASK_START_DATE, 
					 LAST_TASK_END_DATE, 
					 GROUP_ID, 
					 'Y'
                FROM CANON_I165_CIAB_LINK_TBL b
               WHERE a.INCIDENT_ID = b.INCIDENT_ID
			     AND a.TASK_ID = b.TASK_ID )
       WHERE EXISTS (SELECT 'X' 
	                   FROM CANON_I165_CIAB_LINK_TBL c
                      WHERE a.INCIDENT_ID = c.INCIDENT_ID)
		 AND incident_id = c2rec.incident_id;
					  
     END LOOP;

     COMMIT;
	 
	 UPDATE CANON_I165_CIAB_STG_TBL a
        SET RECORD_FLAG = 'N'
      WHERE EXISTS 
        (SELECT  'X' 
		   FROM CANON_I165_CIAB_LINK_TBL b
          WHERE a.INCIDENT_ID = b.INCIDENT_ID
            AND NVL(b.PARENT_FLAG, 'X') != 'Y');
       
     COMMIT;
	 
	 UPDATE CANON_I165_CIAB_STG_TBL a
        SET RECORD_FLAG = 'N',
             OPEN_FLAG = 'Y'
      WHERE EXISTS 
         (SELECT 'X' 
		    FROM CANON_I165_CIAB_LINK_TBL b
           WHERE a.GROUP_ID = b.GROUP_ID
             AND b.INCIDENT_STATUS NOT IN ('Closed', 'Cancelled'));
       
	 COMMIT;	  
      
    EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_I165_CIAB_STG_TBL COMPUTE STATISTICS';
    EXECUTE IMMEDIATE 'ANALYZE TABLE CANON_I165_CIAB_LINK_TBL COMPUTE STATISTICS';
	 
  END;
       
  PROCEDURE MERGE_BAD_SERIAL
  IS
    CURSOR bad_cur 
	   IS
         SELECT DISTINCT serial_number, 
         instance_id, substr(serial_number,1,length(serial_number)-4) active_serial
          FROM CANON_I165_CIAB_STG_TBL
         WHERE SERIAL_NUMBER LIKE '%-BAD';
        
		l_active_instance_id NUMBER;
        
      BEGIN
        FOR bad_rec in bad_cur
        LOOP
          BEGIN
            SELECT SVC_MACH_MSTR_PK into l_active_instance_id
              FROM SVC_MACH_MSTR
             WHERE SER_NUM = bad_rec.active_serial;
          EXCEPTION
          WHEN OTHERS
          THEN
            l_active_instance_id := NULL;
          END;
          UPDATE CANON_I165_CIAB_STG_TBL
             SET INSTANCE_ID = l_active_instance_id, 
			     SERIAL_NUMBER = bad_rec.active_serial, 
				 ORIG_INSTANCE_ID = bad_rec.instance_id, 
				 ORIG_SERIAL_NUMBER = bad_rec.serial_number, 
				 bad_flag = 'Y'
            WHERE INSTANCE_ID = bad_rec.INSTANCE_ID;
          
		  COMMIT;
        END LOOP;
      EXCEPTION 
        WHEN OTHERS THEN
          DBMS_OUTPUT.put_line('error while updating bad serial numbers');
      END MERGE_BAD_SERIAL;
END CANON_I165_SR_REP_PKG;
/
SHOW ERRORS;