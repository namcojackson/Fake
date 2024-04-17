CREATE OR REPLACE PACKAGE CANON_E618_SERVICE_REQ_STG_PKG
AS
   /****--------------------------------------------------------------------------
     Name      :   CANON_E618_SERVICE_REQ_STG_PKG.sql
     Created On:   16-FEB-2017
     Created By:   Lakshmi Majeti
     Purpose   :   This program populates S21 Service Request data into custom tables.
     Modification History:
     Version   By                 Date         Comments
     ------    -----------------  --------     ------------------------------
     1.0       Lakshmi Majeti     16-FEB-2017  Populate S21 SR data into custom table.
											   S21 Changes on Revision 3.3 of the PVCS File.   
     -- ---------------------------------------------------------------------------
   *****/

   PROCEDURE service_req_extract (p_run_date_from    IN     DATE,
                                  p_run_date_to      IN     DATE,
                                  o_return_status       OUT VARCHAR2,
                                  o_return_message      OUT VARCHAR2);

   PROCEDURE main (p_errbuf             OUT VARCHAR2,
                   p_retcode            OUT VARCHAR2,
                   p_run_date_from   IN     DATE DEFAULT '01-JAN-1900',
                   p_run_date_to     IN     DATE DEFAULT '31-DEC-2199');

   PROCEDURE log_error (p_process_name   IN VARCHAR2,
                        p_in_key1        IN VARCHAR2,
                        p_in_key2        IN VARCHAR2,
                        p_in_key3        IN VARCHAR2,
                        p_in_key4        IN VARCHAR2,
                        p_in_key5        IN VARCHAR2,
                        p_error_msg      IN VARCHAR2);
						
END CANON_E618_SERVICE_REQ_STG_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E618_SERVICE_REQ_STG_PKG
AS
   /****--------------------------------------------------------------------------
     Name      :   CANON_E618_SERVICE_REQ_STG_PKG.sql
     Created On:   16-FEB-2017
     Created By:   Lakshmi Majeti
     Purpose   :   This program populates S21 Service Request data into custom tables.
     Modification History:
     Version   By                 Date         Comments
     ------    -----------------  --------     ------------------------------
     1.0       Lakshmi Majeti     16-FEB-2017  Populate S21 SR data into custom table.
     -- ---------------------------------------------------------------------------
   *****/
   -- Declare common Variable
   v_error_msg   VARCHAR2 (4000) := 'No Error';

   PROCEDURE service_req_extract (p_run_date_from    IN     DATE,
                                  p_run_date_to      IN     DATE,
                                  o_return_status       OUT VARCHAR2,
                                  o_return_message      OUT VARCHAR2)
   IS
      --Header Cursor
      CURSOR sr_header
      IS
           SELECT sr.fsr_num incident_id,
                  plw.pty_loc_pk party_site_id,
                  stc.loc_num party_site_number,
                  sr.fsr_num incident_number,
                  smm.ser_num serial_number,
                  smm.svc_mach_mstr_pk instance_id,
                  CAST (TO_TIMESTAMP (sr.fsr_crat_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) incident_date,
                  CAST (TO_TIMESTAMP (sr.svc_call_incdt_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) actual_start_date,
                  CAST (TO_TIMESTAMP (sr.fsr_clo_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) actual_end_date,
                  'S21' data_source,
                  sr.ezupuserid logged_by,
                  NULL closed_by,
                  CAST (TO_TIMESTAMP (sr.fsr_clo_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) close_date
             FROM fsr sr,
                  ship_to_cust stc,
                  pty_loc_wrk plw,
                  svc_mach_mstr smm
            WHERE     1 = 1
                  AND sr.glbl_cmpy_cd = smm.glbl_cmpy_cd
                  AND sr.ezcancelflag = smm.ezcancelflag
                  AND sr.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                  AND sr.glbl_cmpy_cd = stc.glbl_cmpy_cd
                  AND sr.ezcancelflag = stc.ezcancelflag
                  AND sr.ship_to_cust_cd = stc.ship_to_cust_cd
                  AND sr.glbl_cmpy_cd = plw.glbl_cmpy_cd
                  AND sr.ezcancelflag = plw.ezcancelflag
                  AND stc.loc_num = plw.loc_num
                  AND sr.glbl_cmpy_cd = 'ADB'
                  AND sr.ezcancelflag = '0'
                  --AND SR.FSR_TP_CD = '1'                         -- 'SERVICE CALL' ---'SR'
                  AND sr.fsr_sts_cd != '99'
         /*  AND EXISTS
                  (SELECT PARTY_SITE_ID
                     FROM CANON_E618_LOCATIONS_TBL LOC
                    WHERE LOC.PARTY_SITE_ID = PLW.PTY_LOC_PK)  */
         ORDER BY sr.fsr_num;

      --Lines Cursor
      CURSOR sr_lines (crp_inc_num  fsr.fsr_num%TYPE)
      IS
           SELECT sr.fsr_num,
                  st.svc_task_num task_number,
                  st.svc_task_num task_id,
                  dsct.ds_svc_call_tp_desc_txt task_type,
                  sts.svc_task_sts_desc_txt task_status,
                  CAST (TO_TIMESTAMP (fv.fsr_visit_arv_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) actual_start_date,
                  CAST (TO_TIMESTAMP (fv.fsr_visit_cplt_dt, 'RRRRMMDDHH24MISSFF3') AS DATE) actual_end_date,
                  (SELECT sp.psn_first_nm || ', ' || sp.psn_last_nm
                     FROM s21_psn sp
                    WHERE sp.psn_cd = fv.tech_cd
                      AND sp.glbl_cmpy_cd = st.glbl_cmpy_cd
                      AND sp.ezcancelflag = st.ezcancelflag) tech_assigned,
                  sts.svc_task_sts_desc_txt incident_status,
                  dsct.ds_svc_call_tp_desc_txt incident_type,
                  (SELECT svc_cmnt_txt
                     FROM (  SELECT svc_cmnt_txt
                               FROM svc_memo sm
                              WHERE sm.fsr_num = sr.fsr_num
								AND sm.glbl_cmpy_cd = sr.glbl_cmpy_cd
								AND sm.ezcancelflag = sr.ezcancelflag
								AND sm.svc_task_num = st.svc_task_num
								AND sr.fsr_num = sm.fsr_num
                           ORDER BY svc_memo_pk DESC)
                    WHERE ROWNUM = 1) inc_problem_summary,
                  spt.svc_pblm_tp_desc_txt problem_code_desc,
                  spt.svc_pblm_tp_cd problem_code,
                  spct.svc_pblm_crct_tp_desc_txt correction_code_desc,
                  sprt.svc_pblm_rsn_tp_desc_txt reason_code_description,
                  splt.svc_pblm_loc_tp_desc_txt location_code_description,
                  st.svc_cust_attn_txt || '  ' || st.cust_tel_num incident_requestor
             FROM fsr sr,
                  fsr_visit fv,
                  fsr_visit_task fvt,
                  svc_task st,
                  svc_task_sts sts,
                  ds_svc_call_tp dsct,
                  svc_pblm_tp spt,
                  svc_pblm_crct_tp spct,
                  svc_pblm_rsn_tp sprt,
                  svc_pblm_loc_tp splt
            WHERE     1 = 1
                  AND sr.fsr_num = crp_inc_num
                  AND sr.glbl_cmpy_cd = fv.glbl_cmpy_cd(+)
                  AND sr.ezcancelflag = fv.ezcancelflag(+)
                  AND sr.fsr_num = st.fsr_num
                  AND sr.fsr_num = fv.fsr_num(+)
                  AND sr.glbl_cmpy_cd = st.glbl_cmpy_cd
                  AND sr.ezcancelflag = st.ezcancelflag
                  AND st.svc_task_num = fv.svc_task_num(+)
                  AND sr.glbl_cmpy_cd = fvt.glbl_cmpy_cd(+)
                  AND sr.ezcancelflag = fvt.ezcancelflag(+)
                  AND fv.fsr_visit_num = fvt.fsr_visit_num(+)
                  AND sr.fsr_num = fvt.fsr_num(+)
                  AND sr.glbl_cmpy_cd = spt.glbl_cmpy_cd(+)
                  AND sr.ezcancelflag = spt.ezcancelflag(+)
                  AND sr.svc_pblm_tp_cd = spt.svc_pblm_tp_cd(+)
                  AND sr.glbl_cmpy_cd = spct.glbl_cmpy_cd(+)
                  AND sr.ezcancelflag = spct.ezcancelflag(+)
                  AND fvt.svc_pblm_crct_tp_cd = spct.svc_pblm_crct_tp_cd(+)
                  AND sr.glbl_cmpy_cd = sprt.glbl_cmpy_cd(+)
                  AND sr.ezcancelflag = sprt.ezcancelflag(+)
                  AND fvt.svc_pblm_rsn_tp_cd = sprt.svc_pblm_rsn_tp_cd(+)
                  AND sr.glbl_cmpy_cd = splt.glbl_cmpy_cd(+)
                  AND sr.ezcancelflag = splt.ezcancelflag(+)
                  AND fvt.svc_pblm_loc_tp_cd = splt.svc_pblm_loc_tp_cd(+)
                  AND sts.glbl_cmpy_cd = st.glbl_cmpy_cd
                  AND sts.ezcancelflag = st.ezcancelflag
                  AND sts.svc_task_sts_cd = st.svc_task_sts_cd
                  AND sr.glbl_cmpy_cd = dsct.glbl_cmpy_cd(+)
                  AND sr.ezcancelflag = dsct.ezcancelflag(+)
                  AND st.ds_svc_call_tp_cd = dsct.ds_svc_call_tp_cd(+)
                  --AND ST.FIRST_SVC_TASK_FLG = 'Y'
                  AND sr.glbl_cmpy_cd = 'ADB'
                  AND sr.ezcancelflag = '0'
                  AND st.svc_task_sts_cd != '99'                             --'Cancelled'
                  --AND SR.FSR_TP_CD = '1'                         -- 'SERVICE CALL' ---'SR'
                  AND sr.fsr_sts_cd != '99'
         --AND SR.FSR_NUM ='50000045'
         ORDER BY sr.fsr_num, st.svc_task_num DESC;

      v_return_status    VARCHAR2 (240) := 'S';
      v_return_message   VARCHAR2 (240) := 'No Error';
      l_hdr_cnt          NUMBER := 0;
      l_line_cnt         NUMBER := 0;
   --L_TASK_CNT         NUMBER;
   
   BEGIN
   
      DBMS_OUTPUT.PUT_LINE ('Truncate Tables');

      EXECUTE IMMEDIATE 'TRUNCATE TABLE E618_SR_HEADERS_TBL';

      EXECUTE IMMEDIATE 'TRUNCATE TABLE E618_SR_LINES_TBL';


      DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');
      DBMS_OUTPUT.PUT_LINE ('Current system time is ' || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
      DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');

      --====================================================================================================
      -- Create SR Incidents
      BEGIN
         FOR sr_header_rec IN sr_header
         LOOP
            BEGIN
               INSERT INTO e618_sr_headers_tbl (incident_number,
                                                serial_number,
                                                --incident_status,
                                                incident_date,
                                                actual_start_date,
                                                actual_end_date,
                                                --incident_type,
                                                --inc_problem_summary,
                                                --problem_code_desc,
                                                --problem_code,
                                                --correction_code_desc,
                                                --reason_code_description,
                                                --location_code_description,
                                                logged_by,
                                                --incident_requestor,
                                                closed_by,
                                                closed_date,
                                                data_source,
                                                incident_id,
                                                party_site_id,
                                                party_site_number,
                                                creation_date,
                                                created_by,
                                                last_update_date,
                                                last_updated_by)
                    VALUES (sr_header_rec.incident_number,
                            sr_header_rec.serial_number,
                            --sr_header_rec.incident_status,
                            sr_header_rec.incident_date,
                            sr_header_rec.actual_start_date,
                            sr_header_rec.actual_end_date,
                            --sr_header_rec.incident_type,
                            --sr_header_rec.problem_summary,
                            --sr_header_rec.problem_code_desc,
                            --sr_header_rec.problem_code,
                            --sr_header_rec.correction_code_desc,
                            --sr_header_rec.reason_code_description,
                            --sr_header_rec.location_code_description,
                            sr_header_rec.logged_by,
                            --sr_header_rec.incident_requestor,
                            sr_header_rec.closed_by,
                            sr_header_rec.close_date,
                            sr_header_rec.data_source,
                            sr_header_rec.incident_id,
                            sr_header_rec.party_site_id,
                            sr_header_rec.party_site_number,
                            SYSDATE,
                            '-1',
                            SYSDATE,
                            '-1');

               l_hdr_cnt := l_hdr_cnt + 1;
			   
            EXCEPTION
               WHEN OTHERS
               THEN
                  log_error ('SERVICE_REQ_EXTRACT',
                     sr_header_rec.incident_number,
                     sr_header_rec.serial_number,
                     NULL,
                     NULL,
                     NULL,
                        'Error while insert into the table E618_SR_HEADERS_TBL: '
                     || SQLCODE
                     || ' - '
                     || SUBSTR (SQLERRM, 1, 500));
            END;

            --L_TASK_CNT := 1;

            --Lines Cursor
            FOR sr_line_rec IN sr_lines (sr_header_rec.incident_number)
            LOOP
               BEGIN
                  INSERT INTO e618_sr_lines_tbl (task_number,
                                                 task_id,
                                                 task_type,
                                                 task_status,
                                                 actual_start_date,
                                                 actual_end_date,
                                                 problem_code_desc,
                                                 tech_assigned,
                                                 incident_id,
                                                 incident_number,
                                                 creation_date,
                                                 created_by,
                                                 last_update_date,
                                                 last_updated_by)
                       VALUES (sr_line_rec.task_number,
                               sr_line_rec.task_id,
                               sr_line_rec.task_type,
                               sr_line_rec.task_status,
                               sr_line_rec.actual_start_date,
                               sr_line_rec.actual_end_date,
                               sr_line_rec.problem_code_desc,
                               sr_line_rec.tech_assigned,
                               sr_header_rec.incident_id,
                               sr_header_rec.incident_number,
                               SYSDATE,
                               '-1',
                               SYSDATE,
                               '-1');

                  l_line_cnt := l_line_cnt + 1;
				  
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     LOG_ERROR (
                        'SERVICE_REQ_EXTRACT',
                        sr_header_rec.incident_number,
                        sr_header_rec.serial_number,
                        sr_line_rec.task_number,
                        NULL,
                        NULL,
                           'Error while insert into the table E618_SR_LINES_TBL: '
                        || SQLCODE
                        || ' - '
                        || SUBSTR (SQLERRM, 1, 500));
               END;

               -- IF L_TASK_CNT = 1
               -- THEN
               BEGIN
                  UPDATE e618_sr_headers_tbl
                     SET incident_status = sr_line_rec.incident_status,
                         incident_type = sr_line_rec.incident_type,
                         inc_problem_summary = sr_line_rec.inc_problem_summary,
                         problem_code_desc = sr_line_rec.problem_code_desc,
                         problem_code = sr_line_rec.problem_code,
                         correction_code_desc = sr_line_rec.correction_code_desc,
                         reason_code_description = sr_line_rec.reason_code_description,
                         location_code_description = sr_line_rec.location_code_description,
                         incident_requestor = sr_line_rec.incident_requestor
                   WHERE incident_number = sr_header_rec.incident_number;
               --  L_TASK_CNT := L_TASK_CNT + 1;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     LOG_ERROR (
                        'SERVICE_REQ_EXTRACT',
                        sr_header_rec.incident_number,
                        sr_header_rec.serial_number,
                        sr_line_rec.task_number,
                        NULL,
                        NULL,
                           'Error while updating table E618_SR_LINES_TBL: '
                        || SQLCODE
                        || ' - '
                        || SUBSTR (SQLERRM, 1, 500));
               END;

               --  END IF;

               IF MOD (l_line_cnt, 500) = 0
               THEN
                  COMMIT;
               END IF;
            END LOOP;
         END LOOP;

         DBMS_OUTPUT.PUT_LINE ('No. of Service Request Headers Inserted: ' || L_HDR_CNT);
         DBMS_OUTPUT.PUT_LINE ('No. of Service Request Lines Inserted: ' || L_LINE_CNT);
         DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');
         COMMIT;

         EXECUTE IMMEDIATE 'ANALYZE TABLE E618_SR_HEADERS_TBL COMPUTE STATISTICS';

         EXECUTE IMMEDIATE 'ANALYZE TABLE E618_SR_LINES_TBL COMPUTE STATISTICS';
      EXCEPTION
         WHEN OTHERS
         THEN
            v_error_msg :=
                  'Error while insert SR Data using SERVICE_REQ_EXTRACT api: '
               || SQLCODE
               || ' - '
               || SUBSTR (SQLERRM, 1, 500);
            o_return_status := 'E';
            o_return_message := v_error_msg;

            LOG_ERROR ('SERVICE_REQ_EXTRACT',
                       NULL,
                       NULL,
                       NULL,
                       NULL,
                       NULL,
                       v_error_msg);
      END;
   END service_req_extract;

   -----------------------------------------------------------------------------------------------------------
   PROCEDURE main (p_errbuf             OUT VARCHAR2,
                   p_retcode            OUT VARCHAR2,
                   p_run_date_from   IN     DATE DEFAULT '01-JAN-1900',
                   p_run_date_to     IN     DATE DEFAULT '31-DEC-2199')
   IS
      v_return_status    VARCHAR2 (240) := 'S';
      v_return_message   VARCHAR2 (240) := 'No Error';

      v_run_date_from    DATE := p_run_date_from;
      v_run_date_to      DATE := p_run_date_to;
   BEGIN
      ------------------------------------------------------------------------------------------------------
      DBMS_OUTPUT.PUT_LINE ('Current system time is ' || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
      DBMS_OUTPUT.PUT_LINE ('+----------------------------------------------------------------+');
      ------------------------------------------------------------------------------------------------------
      DBMS_OUTPUT.PUT_LINE ('Calling SERVICE_REQ_EXTRACT');

      DELETE FROM e618_emsd_errors_tbl
		    WHERE program_name = 'CANON_E618_SERVICE_REQ_STG_PKG'
			  AND error_date < SYSDATE - 60;

      service_req_extract (p_run_date_from    => v_run_date_from,             --  in date,
                           p_run_date_to      => v_run_date_to,               --  in date,
                           o_return_status    => v_return_status,          --out varchar2,
                           o_return_message   => v_return_message          -- out varchar2
                                                                 );

      DBMS_OUTPUT.PUT_LINE ('+---------------------------------------------------+');
      ------------------------------------------------------------------------------------------------------
      DBMS_OUTPUT.PUT_LINE ('Current system time is ' || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	  
	  p_errbuf := v_return_message;
	  p_retcode := v_return_status;
	  
   EXCEPTION
      WHEN OTHERS
      THEN
         v_error_msg := ' terminated due to ' || SQLCODE || ' - ' || SUBSTR (SQLERRM, 1, 500);
         log_error ('MAIN',
                    NULL,
                    NULL,
                    NULL,
                    NULL,
                    NULL,
                    v_error_msg);
					
		 p_errbuf := v_error_msg;
		 p_retcode := 'E';
	  
   END main;

   PROCEDURE log_error (p_process_name   IN VARCHAR2,
                        p_in_key1        IN VARCHAR2,
                        p_in_key2        IN VARCHAR2,
                        p_in_key3        IN VARCHAR2,
                        p_in_key4        IN VARCHAR2,
                        p_in_key5        IN VARCHAR2,
                        p_error_msg      IN VARCHAR2)
   AS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO e618_emsd_errors_tbl (program_name,
                                        process_name,
                                        key1,
                                        key2,
                                        key3,
                                        key4,
                                        key5,
                                        error_msg,
                                        error_date)
           VALUES ('CANON_E618_SERVICE_REQ_STG_PKG',
                   p_process_name,
                   p_in_key1,
                   p_in_key2,
                   p_in_key3,
                   p_in_key4,
                   p_in_key5,
                   p_error_msg,
                   SYSDATE);

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.PUT_LINE (
               'Unexpected error occured while logging the errors in E618_EMSD_ERRORS_TBL. Oracle Error Message:'
            || SUBSTR (SQLERRM, 1, 500));
   END log_error;
   
END CANON_E618_SERVICE_REQ_STG_PKG;
/