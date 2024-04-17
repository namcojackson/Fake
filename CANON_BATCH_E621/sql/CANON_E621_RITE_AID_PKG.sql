CREATE OR REPLACE PACKAGE CANON_E621_RITE_AID_PKG AS 

TYPE REFCURSOR IS REF CURSOR;

PROCEDURE CREATE_RITE_AID_SR(X_result  out REFCURSOR);

PROCEDURE GET_RITE_AID_EMAIL_ADD (X_result  out REFCURSOR);

    procedure insert_data (L_SERIAL_NUMBER in varchar2
					   ,L_INSERT_FLAG in varchar2
					   ,L_INCIDENT_NUMBER in varchar2
					   ,L_INCIDENT_STATUS in varchar2
					   ,L_INCIDENT_DATE in date
					   ,L_TASK_NUMBER in varchar2
                       ,l_email_id in varchar2
                       ,L_TASK_STATUS in varchar2);
                       
 PROCEDURE SEND_STATUS(X_RESULT OUT REFCURSOR);  
 
 PROCEDURE STATUS_UPDATE_EMAIL_TBL;
 
 procedure   update_status  (L_SERIAL_NUMBER in varchar2
					   ,lv_email_sts_flag in varchar2
					   ,L_INCIDENT_NUMBER in varchar2
					   ,L_INCIDENT_STATUS in varchar2
					   ,L_INCIDENT_DATE in date
					   ,L_TASK_NUMBER in varchar2
                       ,l_email_id in varchar2
                       ,L_TASK_STATUS in varchar2);
                      
PROCEDURE PREPARE_FROM_EMAIL_REQUEST(p_subject     IN       VARCHAR2,
                                     o_status      OUT      VARCHAR2);

END CANON_E621_RITE_AID_PKG;
/


create or replace PACKAGE BODY CANON_E621_RITE_AID_PKG AS

  PROCEDURE CREATE_RITE_AID_SR(X_result  out REFCURSOR) AS

 BEGIN

	open X_result for
    SELECT
			incident_number
			,incident_date1
			,incident_date
			,creation_date
			,incident_status
			,task_number
			,task_status
      ,serial_number
			,PROB_DESCRIPTION
			,CONTACT_INFO
    FROM
    ( SELECT fsr.fsr_num incident_number,
             TO_CHAR(TO_DATE(substr(task.svc_task_rcv_dt || task.svc_task_rcv_tm,1,14),'YYYYMMDD HH24:MI:SS')) incident_date1,
             TO_DATE(substr(task.svc_task_rcv_dt || task.svc_task_rcv_tm,1,14),'YYYYMMDD HH24:MI:SS') incident_date,
             TO_CHAR(TO_DATE(substr(task.ezintime,1,14),'YYYYMMDD HH24:MI:SS')) creation_date,
			 (SELECT svc_task_sts_nm
                FROM svc_task_sts task_sts1
                WHERE svc_task_sts_cd = fsr.FSR_STS_CD) incident_status
			,task.svc_task_num task_number,
            (SELECT svc_task_sts_nm
                FROM svc_task_sts task_sts1
                WHERE svc_task_sts_cd = visit.fsr_visit_sts_cd) task_status,
             fsr.ser_num serial_number,
            (SELECT svc_pblm_tp_nm
                FROM svc_pblm_tp pblm
                WHERE  pblm.svc_pblm_tp_cd = visit_task.svc_pblm_tp_cd
                 AND ROWNUM = 1) PROB_DESCRIPTION,
             task.ds_svc_call_tp_cd ds_svc_call_tp_cd,
            ( SELECT substr(memo.svc_cmnt_txt,11)
                FROM svc_memo memo,
                    svc_memo_tp tp
                WHERE 1 = 1
                AND tp.svc_memo_tp_cd = memo.svc_memo_tp_cd
                AND memo.svc_task_num = task.svc_task_num
                AND substr(memo.svc_cmnt_txt,1,10) = 'RITE AID -'
                AND tp.svc_memo_tp_nm = 'SRV_CONT'
                AND TO_DATE(substr(memo.ezintime,1,8),'YYYYMMDD')
                     = ( SELECT MAX(TO_DATE(substr(memo1.ezintime,1,8),'YYYYMMDD') )
                         FROM svc_memo memo1,
                            svc_memo_tp tp1
                        WHERE 1 = 1
                            AND tp1.svc_memo_tp_cd = memo1.svc_memo_tp_cd
                            AND memo1.svc_task_num = task.svc_task_num
                            AND substr(memo1.svc_cmnt_txt,1,10) = 'RITE AID -'
                            AND tp1.svc_memo_tp_nm = 'SRV_CONT'
                         )
             AND ROWNUM = 1 ) CONTACT_INFO,
            to_date(SUBSTR(fsr.EZINTIME ,1,8),'YYYYMMDD') fsr_date
        FROM
            fsr,
            svc_task task,
            fsr_visit visit,
            fsr_visit_task visit_task
        WHERE
            fsr.fsr_num = task.fsr_num
            AND visit.svc_task_num = task.svc_task_num
            AND visit.fsr_num = fsr.fsr_num
            AND visit_task.fsr_num (+) = fsr.fsr_num
            AND visit_task.svc_task_num (+) = task.svc_task_num         
   ) main_data
    where  main_data.fsr_date>= trunc(SYSDATE -(nvl((select days from CANON_E621_RITEAID_NO_OF_DAY_V
                                                        where upper(ENABLED )= 'Y'),10)))
    AND NOT EXISTS ( SELECT 'X'
                    FROM  canon_e621_rite_aid_tbl e621
                    WHERE 1 = 1
                    AND e621.task_number = main_data.task_number )
    AND EXISTS ( SELECT 'X'
                FROM canon_e621_riteaid_ser_num_v num_v
                WHERE 1 = 1
                  AND num_v.serial_number = main_data.serial_number
                  AND upper(enabled) = 'Y' )
    AND NOT EXISTS ( SELECT 'X'
                        FROM canon_e621_riteaid_dontsend_v v
                        WHERE v.status = main_data.task_status
                            AND upper(v.enabled) = 'Y')
    AND NOT EXISTS ( SELECT 'X'
                    FROM canon_e621_riteaid_exc_types_v v,
                         ds_svc_call_tp call_cp
                    WHERE call_cp.ds_svc_call_tp_cd = main_data.ds_svc_call_tp_cd
                      AND v.call_type = call_cp.ds_svc_call_tp_nm
                      AND upper(v.enabled) = 'Y');

 EXCEPTION
     WHEN OTHERS THEN
     dbms_output.put_line(substr(sqlerrm,1,200));
     open X_result for
    SELECT
			1 incident_number
			,1 incident_date1
			,1 incident_date
			,1 creation_date
			,1 incident_status
			,1 task_number
			,1 task_status
            ,1  serial_number
			,1 problem
			,1 cont_info from dual where 1<>1;
 END CREATE_RITE_AID_SR;

    PROCEDURE get_rite_aid_email_add ( x_result OUT refcursor )
        IS
    BEGIN
        OPEN x_result FOR
            SELECT  email  FROM canon_e621_riteaid_email_add_v
            WHERE upper(enabled) = 'Y';
    EXCEPTION
        WHEN OTHERS THEN
            OPEN x_result FOR SELECT 1 email
                              FROM dual
                              WHERE 1 <> 1;

    END get_rite_aid_email_add;

    procedure insert_data (L_SERIAL_NUMBER in varchar2
					   ,L_INSERT_FLAG in varchar2
					   ,L_INCIDENT_NUMBER in varchar2
					   ,L_INCIDENT_STATUS in varchar2
					   ,L_INCIDENT_DATE in date
					   ,L_TASK_NUMBER in varchar2
                       ,l_email_id in varchar2
                       ,L_TASK_STATUS in varchar2)

is

l_email_count  NUMBER;
begin


 IF L_INSERT_FLAG = 'S' then
						BEGIN
                        INSERT INTO CANON_E621_RITE_AID_TBL
                            (SERIAL_NUMBER,
                             INCIDENT_ID,
                             INCIDENT_NUMBER    ,
                             INCIDENT_STATUS,
                             INCIDENT_DATE,
                             TASK_ID,
                             TASK_NUMBER,
                             TASK_STATUS,
                             RITE_AID_HDESK_NUM,
                             EMAIL_SENT_FLAG,
                             LAST_EMAIL_STATUS,
                             CREATED_BY,
                             CREATION_DATE,
                             LAST_UPDATED_BY,
                             LAST_UPDATE_DATE)
                        VALUES(
                             L_SERIAL_NUMBER,
                             L_INCIDENT_NUMBER,
                             L_INCIDENT_NUMBER,
                             L_INCIDENT_STATUS,
                             L_INCIDENT_DATE,
                             L_TASK_NUMBER,
                             L_TASK_NUMBER,
                             L_TASK_STATUS,
                             Null,
                             'Y',
                             'S',
                             -1,
                             SYSDATE,
                             -1,
                             SYSDATE);
                    EXCEPTION
                    WHEN OTHERS THEN
                    DBMS_OUTPUT.PUT_LINE(SQLERRM);
                    END;
                    --Insert into email table
                    BEGIN
                        INSERT INTO CANON_E621_RITE_AID_EMAIL_TBL
                             (REQUEST_ID,
                              SERIAL_NUMBER,
                              INCIDENT_ID,
                              INCIDENT_NUMBER,
                              INCIDENT_STATUS,
                              INCIDENT_DATE,
                             TASK_ID,
                             TASK_NUMBER,
                             TASK_STATUS,
                             EMAIL_SENT_TO,
                             EMAIL_SENT_FLAG,
                             LAST_EMAIL_STATUS,
                             LAST_EMAIL_SENT_DATE,
                             CREATED_BY,
                             CREATION_DATE,
                             LAST_UPDATED_BY,
                             LAST_UPDATE_DATE)
                       VALUES(-1,
                             L_SERIAL_NUMBER,
                             L_INCIDENT_NUMBER,
                             L_INCIDENT_NUMBER,
                             L_INCIDENT_STATUS,
                             L_INCIDENT_DATE,
                             L_TASK_NUMBER,
                             L_TASK_NUMBER,
                             L_TASK_STATUS,
                             L_EMAIL_ID,
                             'Y',
                             NULL,
                             SYSDATE,
                             -1,
                             SYSDATE,
                             -1,
                             SYSDATE);
                    EXCEPTION
                        WHEN OTHERS THEN
                        DBMS_OUTPUT.PUT_LINE(SQLERRM);
                    END;
  ELSE
                     BEGIN
                     l_email_count := 0;
                      BEGIN
                        SELECT count(1) INTO l_email_count
                        from CANON_E621_RITE_AID_EMAIL_TBL
                        where INCIDENT_ID = L_INCIDENT_NUMBER
                        AND SERIAL_NUMBER = L_SERIAL_NUMBER
                        AND TASK_NUMBER = L_TASK_NUMBER
                        AND TASK_STATUS = L_TASK_STATUS
                        AND EMAIL_SENT_TO = l_email_id;
                      EXCEPTION WHEN OTHERS THEN
                        l_email_count := 0;
                      END;
                   IF(l_email_count = 0) THEN
                        INSERT INTO CANON_E621_RITE_AID_EMAIL_TBL
                             (REQUEST_ID,
                              SERIAL_NUMBER,
                              INCIDENT_ID,
                              INCIDENT_NUMBER,
                              INCIDENT_STATUS,
                              INCIDENT_DATE,
                             TASK_ID,
                             TASK_NUMBER,
                             TASK_STATUS,
                             EMAIL_SENT_TO,
                             EMAIL_SENT_FLAG,
                             LAST_EMAIL_STATUS,
                             LAST_EMAIL_SENT_DATE,
                             CREATED_BY,
                             CREATION_DATE,
                             LAST_UPDATED_BY,
                             LAST_UPDATE_DATE)
                       VALUES(-1,
                             L_SERIAL_NUMBER,
                             L_INCIDENT_NUMBER,
                             L_INCIDENT_NUMBER,
                             L_INCIDENT_STATUS,
                             L_INCIDENT_DATE,
                             L_TASK_NUMBER,
                             L_TASK_NUMBER,
                             L_TASK_STATUS,
                             l_email_id,
                             'N',
                             NULL,
                             SYSDATE,
                             -1,
                             SYSDATE,
                             -1,
                             SYSDATE);
                    END IF;
		            EXCEPTION
                        WHEN OTHERS THEN
                        DBMS_OUTPUT.PUT_LINE(SQLERRM);
					END;
	END IF;
  COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE(SQLERRM);
end insert_data;

 PROCEDURE SEND_STATUS(X_RESULT OUT REFCURSOR) IS

 BEGIN

   OPEN X_RESULT FOR   select serial_number
        ,incident_number
        ,task_number
        , decode (ln_task_sts,'Closed','..iemclose..',UPPER(ln_task_sts)) task_status
        ,rite_aid_hdesk_num
        , CONTACT_INFO
        ,INCIDENT_STATUS
        ,INCIDENT_DATE
        from (   SELECT e621.*, (SELECT svc_task_sts_nm
                FROM svc_task_sts task_sts1, fsr_visit visit
                WHERE svc_task_sts_cd = visit.fsr_visit_sts_cd
				AND visit.SVC_TASK_NUM =e621.TASK_NUMBER
				AND EXISTS (SELECT 'X' FROM CANON_E621_RITEAID_STS_CHG_V v
				WHERE 	V.ENABLED = 'Y'
				and STATUS = task_sts1.svc_task_sts_nm
				) ) ln_task_sts,
				( SELECT substr(memo.svc_cmnt_txt,11)
                FROM svc_memo memo,
                    svc_memo_tp tp
                WHERE 1 = 1
                AND tp.svc_memo_tp_cd = memo.svc_memo_tp_cd
                AND memo.svc_task_num = e621.task_number
                AND substr(memo.svc_cmnt_txt,1,10) = 'RITE AID -'
                AND tp.svc_memo_tp_nm = 'SRV_CONT'
                AND TO_DATE(substr(memo.ezintime,1,8),'YYYYMMDD')
                     = ( SELECT MAX(TO_DATE(substr(memo1.ezintime,1,8),'YYYYMMDD') )
                         FROM svc_memo memo1,
                            svc_memo_tp tp1
                        WHERE 1 = 1
                            AND tp1.svc_memo_tp_cd = memo1.svc_memo_tp_cd
                            AND memo1.svc_task_num = e621.task_number
                            AND substr(memo1.svc_cmnt_txt,1,10) = 'RITE AID -'
                            AND tp1.svc_memo_tp_nm = 'SRV_CONT'
                         )
             AND ROWNUM = 1 ) CONTACT_INFO
        FROM  canon_e621_rite_aid_tbl e621
        WHERE 1=1
        AND e621.rite_aid_hdesk_num IS NOT NULL
        AND NOT EXISTS (select 'X' from CANON_E621_RITEAID_DONTSEND_v v
						where upper(v.ENABLED) = 'Y'
						and e621.task_status =v.STATUS ))
		where 	ln_task_sts is not null
		and 	UPPER(ln_task_sts) <>	UPPER(task_status);


		CANON_E621_RITE_AID_PKG.STATUS_UPDATE_EMAIL_TBL();

	EXCEPTION
		WHEN OTHERS THEN
		OPEN X_RESULT FOR   select 1 serial_number
        ,1 incident_number
        ,1  task_status
        ,1  email_type
        ,1 subject
        ,1 CONTACT_INFO
        ,1 INCIDENT_STATUS
        ,1 INCIDENT_DATE FROM DUAL WHERE 1<>1;
 END SEND_STATUS;

 Procedure STATUS_UPDATE_EMAIL_TBL IS
 CURSOR MAIL_REC_DATA IS select *
        from (   SELECT e621.*, (SELECT svc_task_sts_nm
                FROM svc_task_sts task_sts1, fsr_visit visit
                WHERE svc_task_sts_cd = visit.fsr_visit_sts_cd
				AND visit.SVC_TASK_NUM =e621.TASK_NUMBER
				AND EXISTS (SELECT 'X' FROM CANON_E621_RITEAID_STS_CHG_V v
				WHERE 	V.ENABLED = 'Y'
				and STATUS = task_sts1.svc_task_sts_nm
				) ) ln_task_sts
        FROM  canon_e621_rite_aid_tbl e621
        WHERE 1=1
        AND e621.rite_aid_hdesk_num IS NOT NULL
        AND NOT EXISTS (select 'X' from CANON_E621_RITEAID_DONTSEND_v v
						where upper(v.ENABLED) = 'Y'
						and e621.task_status =v.STATUS ))
		where 	ln_task_sts is null	;

        cursor c_email_add is SELECT  email  FROM canon_e621_riteaid_email_add_v
            WHERE upper(enabled) = 'Y';

	lv_error_msg varchar2(4000);
	ln_task_status svc_task_sts.svc_task_sts_nm%type;
  l_email_count NUMBER;

BEGIN

FOR fr_cr_e621 IN MAIL_REC_DATA LOOP

        lv_error_msg:=Null;
        ln_task_status:=Null;
        l_email_count := 0;

            BEGIN
                SELECT
                    svc_task_sts_nm
                INTO ln_task_status
                FROM
                    svc_task_sts task_sts1,
                    fsr_visit visit
                WHERE
                    task_sts1.svc_task_sts_cd = visit.fsr_visit_sts_cd
                    AND visit.svc_task_num = fr_cr_e621.task_number;

            EXCEPTION
                WHEN OTHERS THEN
                    lv_error_msg := 'For ' || fr_cr_e621.task_number || ' Task Number -  '
                                    || ''''|| ln_task_status|| '''' || ' Task status is not exists in VS.. ';
            END;

        for fr_c_email_add in c_email_add loop
            BEGIN
               l_email_count := 0;
                      BEGIN
                        SELECT count(1) INTO l_email_count
                        from CANON_E621_RITE_AID_EMAIL_TBL
                        where INCIDENT_ID = fr_cr_e621.incident_number
                        AND SERIAL_NUMBER = fr_cr_e621.serial_number
                        AND TASK_NUMBER = fr_cr_e621.task_number
                        AND TASK_STATUS = ln_task_status
                        AND EMAIL_SENT_TO = fr_c_email_add.email;
                      EXCEPTION WHEN OTHERS THEN
                        l_email_count := 0;
                      END;

               IF(l_email_count = 0) THEN
                INSERT INTO CANON_E621_RITE_AID_EMAIL_TBL
                              (REQUEST_ID,
                               SERIAL_NUMBER,
                               INCIDENT_ID,
                               INCIDENT_NUMBER,
                               INCIDENT_DATE,
                               INCIDENT_STATUS,
                               TASK_ID,
                               TASK_NUMBER,
                               TASK_STATUS,
                               EMAIL_SENT_TO,
                               EMAIL_SENT_FLAG,
                               LAST_EMAIL_STATUS,
                               LAST_EMAIL_SENT_DATE,
                               CREATED_BY,
                               CREATION_DATE,
                               LAST_UPDATED_BY,
                               LAST_UPDATE_DATE)
                        VALUES(-1,
                               fr_cr_e621.serial_number,
                               fr_cr_e621.incident_id,
                               fr_cr_e621.incident_number,
                               fr_cr_e621.incident_date,
                               fr_cr_e621.incident_status,
                               fr_cr_e621.task_id,
                               fr_cr_e621.task_number,
                               ln_task_status,
                               fr_c_email_add.email,
                               'N',
                               lv_error_msg,
                               SYSDATE,
                               -1,
                               SYSDATE,
                               -1,
                               SYSDATE);
                               COMMIT;
            END IF;
            EXCEPTION
				WHEN OTHERS THEN
					dbms_output.put_line(substr(sqlerrm,1,200));
            END;
            end loop;
END LOOP;
EXCEPTION
	WHEN OTHERS THEN
	dbms_output.put_line(substr(sqlerrm,1,200));
end STATUS_UPDATE_EMAIL_TBL;

procedure   update_status  (L_SERIAL_NUMBER in varchar2
					   ,lv_email_sts_flag in varchar2
					   ,L_INCIDENT_NUMBER in varchar2
					   ,L_INCIDENT_STATUS in varchar2
					   ,L_INCIDENT_DATE in date
					   ,L_TASK_NUMBER in varchar2
                       ,l_email_id in varchar2
                       ,L_TASK_STATUS in varchar2) is

  x_error_msg varchar2(4000);
	BEGIN

  x_error_msg := NULL;

			IF 	lv_email_sts_flag = 'S' then

				    BEGIN
                        UPDATE canon_e621_rite_aid_tbl
                        SET task_status = L_TASK_STATUS,
                                email_sent_flag = 'Y',
                                last_email_status = lv_email_sts_flag,
                                last_email_sent_date = SYSDATE
                        WHERE  incident_number = L_INCIDENT_NUMBER
                        AND task_number = L_TASK_NUMBER;

                   EXCEPTION
                        WHEN OTHERS THEN
                        dbms_output.put_line('Error - '||substr(sqlerrm,1,200));
                   END;

                   BEGIN
                        INSERT INTO CANON_E621_RITE_AID_EMAIL_TBL
                                      (REQUEST_ID,
                                       SERIAL_NUMBER,
                                       INCIDENT_ID,
                                       INCIDENT_NUMBER,
                                       INCIDENT_DATE,
                                       INCIDENT_STATUS,
                                       TASK_ID,
                                       TASK_NUMBER,
                                       TASK_STATUS,
                                       EMAIL_SENT_TO,
                                       EMAIL_SENT_FLAG,
                                       LAST_EMAIL_STATUS,
                                       LAST_EMAIL_SENT_DATE,
                                       CREATED_BY,
                                       CREATION_DATE,
                                       LAST_UPDATED_BY,
                                       LAST_UPDATE_DATE)
								VALUES(-1,
                                      L_SERIAL_NUMBER,
                                       L_INCIDENT_NUMBER,
                                       L_INCIDENT_NUMBER,
                                       L_INCIDENT_DATE,
                                       L_INCIDENT_STATUS,
                                       L_TASK_NUMBER,
                                       L_TASK_NUMBER,
                                       L_TASK_STATUS,
                                       l_email_id,
                                       'Y',
                                       null,
                                       SYSDATE,
                                       -1,
                                       SYSDATE,
                                       -1,
                                       SYSDATE);

                    EXCEPTION
                    WHEN OTHERS THEN
                        dbms_output.put_line('Error - '||substr(sqlerrm,1,200));
					END;
            ELSE
                     BEGIN
                        UPDATE canon_e621_rite_aid_tbl
                        SET task_status = L_TASK_STATUS,
                                email_sent_flag = 'N',
                                last_email_status = lv_email_sts_flag,
                                last_email_sent_date = SYSDATE,
                                last_updated_by = -1,
                                last_update_date = SYSDATE
                        WHERE  incident_number = L_INCIDENT_NUMBER
                        AND task_number = L_TASK_NUMBER;
                     EXCEPTION
                        WHEN OTHERS THEN
                           dbms_output.put_line('Error - '||substr(sqlerrm,1,200));
                      END;

                     BEGIN
                        INSERT INTO CANON_E621_RITE_AID_EMAIL_TBL
                                      (REQUEST_ID,
                                       SERIAL_NUMBER,
                                       INCIDENT_ID,
                                       INCIDENT_NUMBER,
                                       INCIDENT_DATE,
                                       INCIDENT_STATUS,
                                       TASK_ID,
                                       TASK_NUMBER,
                                       TASK_STATUS,
                                       EMAIL_SENT_TO,
                                       EMAIL_SENT_FLAG,
                                       LAST_EMAIL_STATUS,
                                       LAST_EMAIL_SENT_DATE,
                                       CREATED_BY,
                                       CREATION_DATE,
                                       LAST_UPDATED_BY,
                                       LAST_UPDATE_DATE)
                                VALUES(-1,
                                       L_SERIAL_NUMBER,
                                       L_INCIDENT_NUMBER,
                                       L_INCIDENT_NUMBER,
                                       L_INCIDENT_DATE,
                                       L_INCIDENT_STATUS,
                                       L_TASK_NUMBER,
                                       L_TASK_NUMBER,
                                       L_TASK_STATUS,
                                       l_email_id,
                                       'N',
                                       null,
                                       SYSDATE,
                                       -1,
                                       SYSDATE,
                                       -1,
                                       SYSDATE);
                    EXCEPTION
                    WHEN OTHERS THEN
                      dbms_output.put_line('Error - '||substr(sqlerrm,1,200));
					END;
			END IF;
      COMMIT;
	End update_status;

  PROCEDURE PREPARE_FROM_EMAIL_REQUEST(p_subject     IN       VARCHAR2,
                                       o_status      OUT      VARCHAR2) IS

   raid_issue_num_value    VARCHAR2(50):=Null;
   raid_ser_num_value      VARCHAR2(50):=Null;
   raid_inc_num_value      VARCHAR2(50):=Null;

  BEGIN

  o_status:='S';

        IF p_subject IS NOT NULL THEN
          raid_issue_num_value:=substr(p_subject,
                                                  instr(p_subject,'#',1,1)+1,
                                                  instr(p_subject,',',1,1)-
                                                   (instr(p_subject,'#',1,1)+1));

         raid_ser_num_value:=trim(substr(replace(substr(p_subject,
                                                        instr(p_subject,'CANON SERVICE',13)),'CANON SERVICE'),1,
                                                        instr(replace(substr(p_subject,
                                                        instr(p_subject,'CANON SERVICE',13)),'CANON SERVICE'),',')-1));

         raid_inc_num_value:=trim(replace(replace(
                                                  substr(p_subject,
                                                  instr(p_subject,'SR #',4)),'SR #'),']'));


        END IF;

        if o_status <>'E' then

        BEGIN
          UPDATE canon_e621_rite_aid_tbl
          SET rite_aid_hdesk_num = raid_issue_num_value,
                      last_updated_by = -1,
                      last_update_date = SYSDATE
              WHERE serial_number = raid_ser_num_value
              AND incident_number = raid_inc_num_value;
         EXCEPTION
              WHEN OTHERS THEN
               o_status:='E';
               dbms_output.put_line('Error at the time of updating rite aid help desk num in CANON_E621_RITE_AID_TBL .. '||SQLERRM);
         END;

         BEGIN
              UPDATE canon_e621_rite_aid_email_tbl
              SET rite_aid_hdesk_num = raid_issue_num_value,
                      --last_email_status = 'Updated Rite Aid Help Desk Number',
                      last_updated_by = -1,
                      last_update_date = SYSDATE
              WHERE serial_number = raid_ser_num_value
              AND incident_number = raid_inc_num_value;
         EXCEPTION
              WHEN OTHERS THEN
              o_status:='E';
              dbms_output.put_line('Error at the time of updating rite aid help desk num in CANON_E621_RITE_AID_EMAIL_TBL .. '||SQLERRM);
        END;

            if o_status <>'E' then

                insert into CANON_E621_RAID_EMAIL_TEXT_TBL
                (EMAIL_TEXT_SEQUENCE,
                 EMAIL_TEXT,
                 raid_issue_num_value
                ,raid_ser_num_value
                ,raid_inc_num_value
                ,PROCESS_FLAG
                ,creation_date
                ,last_update_date )
                values
                (CANON_E621_RITE_AID_TMP_SEQ.nextval
                ,p_subject
                ,raid_issue_num_value
                ,raid_ser_num_value
                ,raid_inc_num_value
                ,'P'
                ,sysdate
                ,sysdate );

            else
                insert into CANON_E621_RAID_EMAIL_TEXT_TBL
                    (EMAIL_TEXT_SEQUENCE,
                    EMAIL_TEXT,
                    raid_issue_num_value
                    ,raid_ser_num_value
                    ,raid_inc_num_value
                    ,PROCESS_FLAG
                    ,creation_date
                    ,last_update_date )
                    values
                    (CANON_E621_RITE_AID_TMP_SEQ.nextval
                    ,p_subject
                    ,raid_issue_num_value
                    ,raid_ser_num_value
                    ,raid_inc_num_value
                    ,'E'
                    ,sysdate
                    ,sysdate );
            end if;
        end if;
        commit;

  END PREPARE_FROM_EMAIL_REQUEST;

END CANON_E621_RITE_AID_PKG;
/
