create or replace PACKAGE canon_e404_owner_at_change_pkg
AS

PROCEDURE extract_owner_at_changes
(x_errbuf                OUT VARCHAR2
,x_retcode               OUT VARCHAR2
,p_from_date              IN VARCHAR2 --DD-MON-YYYY HH24:MI:SS
,p_to_date                IN VARCHAR2 --DD-MON-YYYY HH24:MI:SS
,p_debug                  IN VARCHAR2 --Y/N
);

PROCEDURE get_sf_account_id
(x_sf_id OUT VARCHAR2
,p_psn    IN VARCHAR2
);
/*
PROCEDURE get_owner_at_change(
   x_cursor OUT sys_refcursor
);

PROCEDURE sfdc_feedback_owneratchange(
   p_result_tbl IN canon.canon_e404_sf_ib_req_rslt_tbl
);
PROCEDURE get_delete_owner_at_change(
    x_cursor OUT sys_refcursor
);
PROCEDURE sfdc_feedback_delowneratchange(
   p_result_tbl IN canon.canon_e404_sf_ib_req_rslt_tbl
);
*/
END canon_e404_owner_at_change_pkg;
/

create or replace PACKAGE BODY  canon_e404_owner_at_change_pkg
AS

G_PACKAGE_NAME          VARCHAR2(30) := 'canon_e404_owner_at_change_pkg';
G_CREATED_BY            NUMBER       := NULL;
G_LAST_UPDATED_BY       NUMBER       := NULL;
G_LAST_UPDATE_LOGIN     NUMBER       := NULL;
G_REQUEST_ID            NUMBER;
G_ERRBUFF               VARCHAR2(5000)  :=    'SUCCESS';
G_RETCODE               VARCHAR2(5000)  :=    '0';
E_USER_EXCEPTION        EXCEPTION;
G_ERROR_MESSAGE         VARCHAR2(3999);

PROCEDURE reset_seq
(p_seq_name IN VARCHAR2)
IS
   l_val NUMBER;
BEGIN
   EXECUTE IMMEDIATE 'select ' || p_seq_name || '.nextval from dual' INTO l_val;

   EXECUTE IMMEDIATE 'alter sequence ' || p_seq_name || ' increment by -' || l_val || ' minvalue 0';

   EXECUTE IMMEDIATE 'select ' || p_seq_name || '.nextval from dual' INTO l_val;

   EXECUTE IMMEDIATE 'alter sequence ' || p_seq_name || ' increment by 1 minvalue 0';
END reset_seq;

PROCEDURE extract_owner_at_changes
(x_errbuf                OUT VARCHAR2
,x_retcode               OUT VARCHAR2
,p_from_date              IN VARCHAR2 --DD-MON-YYYY HH24:MI:SS
,p_to_date                IN VARCHAR2 --DD-MON-YYYY HH24:MI:SS
,p_debug                  IN VARCHAR2 --Y/N
)
IS
   l_PROCEDURE_NAME    VARCHAR2(30) := 'extract_owner_at_changes';
   l_dynsql          LONG;
   l_loop_index      NUMBER;
   l_validate_date   DATE;
   l_from_date       VARCHAR2(25);
   l_program_start   VARCHAR2(25);
   l_profile_saved   BOOLEAN;

   E_INVALID_PARAMS EXCEPTION;

  /*CURSOR dynamic_cols_cur
        (customer_or_prospect_value VARCHAR2
        )
      IS
  SELECT e200_relationship_flag flex_value
          ,e200_flag_meaning description
        ,e200_site_sec_attribute_ter_id attribute1
        ,e200_site_secu_value_terr_name attribute2
        ,COUNT(e200_relationship_flag) OVER (PARTITION BY e200_customer_or_prospect) no_of_attributes
    FROM q_canon_e200_territories_lob_v
   WHERE e200_overall_lob_rank IS NOT NULL
     AND e200_customer_or_prospect = customer_or_prospect_value
     AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(e200_effective_start_date, SYSDATE)) AND TRUNC(NVL(e200_effective_end_date, SYSDATE))
   ORDER BY e200_overall_lob_rank;*/
   
CURSOR dynamic_cols_cur (Customer_OR_Prospect_Value VARCHAR2)
       IS
       SELECT lbrrd.asg_trty_attrb_cd attribute2
              ,lbrrd.asg_trty_attrb_nm attribute1
              ,COUNT(line_biz_role_tp_cd) OVER (PARTITION BY lbrrd.ds_acct_tp_cd) no_of_attributes
        FROM line_biz_role_rank_decn lbrrd
             ,trty_grp_tp tgt
       WHERE lbrrd.ds_acct_tp_cd = Customer_OR_Prospect_Value
         AND lbrrd.trty_grp_tp_cd = tgt.trty_grp_tp_cd
         AND tgt.line_biz_tp_cd = 'ESS'
         AND lbrrd.src_line_biz_tp_cd = 'ESS'
    ORDER BY lbrrd.ds_acct_tp_cd,tgt.trty_grp_rank_num
             ,lbrrd.line_biz_rank_num;      

BEGIN
   x_errbuf     := 'SUCCESS';
   x_retcode    := '0';

   l_program_start := TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS');

   dbms_output.put_line('INPUT PARAMS');
   dbms_output.put_line('------------');

   dbms_output.put_line('p_from_date              :'||p_from_date);
   dbms_output.put_line('p_to_date                :'||p_to_date);
   dbms_output.put_line('p_debug                  :'||p_debug);

   /* VALIDATE INPUT PARAMETERS */
   l_from_date := NULL;
   IF p_from_date IS NOT NULL THEN
      BEGIN
         SELECT TO_DATE(p_from_date, 'DD-MON-YYYY HH24:MI:SS')
           INTO l_validate_date
           FROM dual;
         l_from_date := p_from_date;
      EXCEPTION
         WHEN OTHERS THEN
            RAISE E_INVALID_PARAMS;
      END;
   ELSE
    BEGIN
	SELECT TO_CHAR(TO_DATE(profile_value,'DD-MON-YYYY HH24:MI:SS'),'DD-MON-YYYY HH24:MI:SS')
        INTO l_from_date
        FROM canon_e001_profiles_tbl cp
             ,canon_e001_profile_values_tbl cpv
       WHERE 1 = 1    
         AND cp.profile_name = 'CANON_E404_OWNERAT_REFRESHFROM'
         AND cp.profile_id = cpv.profile_id
         AND SYSDATE BETWEEN NVL(cpv.start_date,SYSDATE-1) AND NVL(cpv.end_date,SYSDATE+1);
    EXCEPTION
		WHEN OTHERS THEN
			l_from_date := TO_CHAR(SYSDATE-1, 'DD-MON-YYYY')||' 08:00:00';
	END;
     /* BEGIN
         IF fnd_profile.value('CANON_E404_OWNERAT_REFRESHFROM') IS NULL THEN
            l_from_date := TO_CHAR(SYSDATE-1, 'DD-MON-YYYY')||' 08:00:00';
         ELSE
            l_from_date := TO_CHAR(TO_DATE(fnd_profile.value('CANON_E404_OWNERAT_REFRESHFROM'), 'DD-MON-YYYY HH24:MI:SS'), 'DD-MON-YYYY HH24:MI:SS');
         END IF;
      EXCEPTION
         WHEN OTHERS THEN
            l_from_date :=  TO_CHAR(SYSDATE-1, 'DD-MON-YYYY')||' 08:00:00';
      END;*/
   END IF;

   dbms_output.put_line('From Date used by Program:'||l_from_date);

   IF p_to_date IS NOT NULL THEN
      BEGIN
         SELECT TO_DATE(p_to_date, 'DD-MON-YYYY HH24:MI:SS')
           INTO l_validate_date
           FROM dual;
      EXCEPTION
         WHEN OTHERS THEN
            RAISE E_INVALID_PARAMS;
      END;
   END IF;

   IF NVL(p_debug, 'N') NOT IN ('Y', 'N') THEN
      RAISE E_INVALID_PARAMS;
   END IF;

   /* RESET SEQUENCE so new records can be inserted with SEQ_NUMBER 1 onwards */
   --reset_seq('CANON_E404_OWNER_AT_CHANGE_SEQ');

   dbms_output.put_line('TRUNCATE TABLE canon_e404_owner_at_change_tmp. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_owner_at_change_tmp';
   dbms_output.put_line('TRUNCATE TABLE canon_e404_owner_at_change_tmp. END: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

   /* Backup any errors or unprocessed accounts from previous runs. So we can reprocess them */
   dbms_output.put_line('Backup accounts that still need to be processed. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

   INSERT
     INTO canon_e404_owner_at_change_tmp
   SELECT *
     FROM canon_e404_owner_at_change_tbl
    WHERE status_flag IN ('I', 'IE');

   dbms_output.put_line('Backup accounts that still need to be processed. END: COUNT: '||SQL%ROWCOUNT||' : '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

   COMMIT;

   dbms_output.put_line('TRUNCATE TABLE canon_e404_owner_at_change_tbl. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e404_owner_at_change_tbl';
   dbms_output.put_line('TRUNCATE TABLE canon_e404_owner_at_change_tbl. END: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

   /* By comparing the latest record for a PSN in E200 Archive and it's current record in E200, identify true "account owner" changes */
   dbms_output.put_line('Capture Account Owner Changes. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

   l_dynsql :=
   'INSERT '||
   '  INTO canon_e404_owner_at_change_tbl '||
   '      (seq_number '||
   '      ,party_id '||
   '      ,party_site_id '||
   '      ,party_site_number ' ||
   '      ,status_flag '||
   '      ,creation_date '||
   '      ,created_by '||
   '      ,last_update_date '||
   '      ,last_updated_by '||
   '      ,owner_or_acctteam '||
   '      ) '||
   'SELECT CANON_E404_OWNER_AT_CHANGE_SEQ.nextval '||
   '      ,plw.cmpy_pk party_id '||
   '      ,plw.pty_loc_pk party_site_id '||
   '      ,e200.loc_num party_site_number ' ||
   '      ,''I'' '||
   '      ,SYSDATE '||
   '      ,NULL '||
   '      ,SYSDATE '||
   '      ,NULL '||
   '      ,''OWNER'' '||
   '  FROM ACCT_TRTY_RESRC_ASG e200 '||
   '      ,pty_loc_wrk plw ' ||
   '      ,(SELECT loc_num ';
   
   l_loop_index := 1;
   FOR dynamic_cols_rec IN dynamic_cols_cur('10') LOOP
      IF l_loop_index = 1 THEN
      l_dynsql := l_dynsql || '              ,SUBSTR(attributes, 1, INSTR(attributes, ''|'', 1)-1) '||dynamic_cols_rec.attribute1||' ';
      ELSE
      l_dynsql := l_dynsql || '              ,SUBSTR(attributes, INSTR(attributes, ''|'', 1, '||TO_CHAR(l_loop_index-1)||')+1, (INSTR(attributes, ''|'', 1, '||TO_CHAR(l_loop_index)||')-INSTR(attributes, ''|'', 1, '||TO_CHAR(l_loop_index-1)||'))-1) '||dynamic_cols_rec.attribute1||' ';
      END IF;
      l_loop_index := l_loop_index + 1;
   END LOOP;

   l_dynsql := l_dynsql ||
   '          FROM( '||
   '        SELECT DISTINCT '||
   '               loc_num '||
   '              ,FIRST_VALUE( ';

   l_loop_index := 1;
   FOR dynamic_cols_rec IN dynamic_cols_cur('10') LOOP
      IF l_loop_index < dynamic_cols_rec.no_of_attributes THEN
         l_dynsql := l_dynsql ||
                               dynamic_cols_rec.attribute1||'||''|''||';
      ELSE
         l_dynsql := l_dynsql ||
                               dynamic_cols_rec.attribute1||'||''|''';
      END IF;
      l_loop_index := l_loop_index + 1;
   END LOOP;

   l_dynsql := l_dynsql ||
   '                         ) OVER(PARTITION BY loc_num ORDER BY ezuptime DESC) attributes '||
   '         FROM ACCT_TRTY_RESRC_LOG '||
   '             ) '||
   '      ) e200_arc   '||
   'WHERE 1 = 1 ' ||
   '  AND e200.loc_num = plw.loc_num';

   IF l_from_date IS NOT NULL THEN
      l_dynsql := l_dynsql ||
   '  AND to_date(substr(e200.ezuptime,1,14), ''RRRRmmddhh24miss'') > TO_DATE('''||l_from_date||''', ''DD-MON-YYYY HH24:MI:SS'') ';
   END IF;

   IF p_to_date IS NOT NULL THEN
      l_dynsql := l_dynsql ||
   '  AND to_date(substr(e200.ezuptime,1,14), ''RRRRmmddhh24miss'') <= TO_DATE('''||p_to_date||''', ''DD-MON-YYYY HH24:MI:SS'') ';
   END IF;

   l_dynsql := l_dynsql ||
   '  AND(   ';

   l_loop_index := 1;
   FOR dynamic_cols_rec IN dynamic_cols_cur('10') LOOP
      IF l_loop_index = 1 THEN
         l_dynsql := l_dynsql ||
   '         e200.'||dynamic_cols_rec.attribute1||' IS NOT NULL ';
      ELSE
         l_dynsql := l_dynsql ||
   '      OR e200.'||dynamic_cols_rec.attribute1||' IS NOT NULL ';
      END IF;
      l_loop_index := l_loop_index + 1;
   END LOOP;

   l_dynsql := l_dynsql ||
   '     ) '||
   '  AND e200.loc_num = e200_arc.loc_num(+) '||
   '  AND (CASE ';

   FOR dynamic_cols_rec IN dynamic_cols_cur('10') LOOP
         l_dynsql := l_dynsql ||
   '       WHEN e200.'||dynamic_cols_rec.attribute1||' IS NOT NULL THEN '||
   '            e200.'||dynamic_cols_rec.attribute1||' ';
   END LOOP;

   l_dynsql := l_dynsql ||
   '       ELSE '||
   '            ''-1'' '||
   '        END '||
   '      ) != (CASE ';

   FOR dynamic_cols_rec IN dynamic_cols_cur('10') LOOP
         l_dynsql := l_dynsql ||
   '            WHEN e200_arc.'||dynamic_cols_rec.attribute1||' IS NOT NULL THEN '||
   '                 e200_arc.'||dynamic_cols_rec.attribute1||' ';
   END LOOP;

   l_dynsql := l_dynsql ||
   '            ELSE '||
   '                 ''-1'' '||
   '             END '||
   '           )'; 

  /* IF p_debug = 'Y' THEN
      fnd_file.put_line(fnd_file.log, l_dynsql);

      INSERT
        INTO CANON_E200_DYNSQL_TBL
      VALUES(G_REQUEST_ID
            ,l_dynsql
            ,SYSDATE
            );

      COMMIT;
   END IF;*/
   dbms_output.put_line('dynamic sql : ' || l_dynsql);

   EXECUTE IMMEDIATE l_dynsql;

   dbms_output.put_line('Capture Account Owner Changes. END: COUNT: '||SQL%ROWCOUNT||' : '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   COMMIT;

   /* Attempt to reprocess error records and unprocessed records from previous run. */
   dbms_output.put_line('Reprocess Error/Unprocessed records. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

   MERGE
    INTO canon_e404_owner_at_change_tbl e404_oat
   USING (SELECT DISTINCT
                 party_id
                ,party_site_id
                ,party_site_number
            FROM canon_e404_owner_at_change_tmp
         ) e404_oat_tmp
      ON(    e404_oat.party_id = e404_oat_tmp.party_id
         AND e404_oat.party_site_id = e404_oat_tmp.party_site_id
        )
    WHEN MATCHED THEN
  UPDATE
     SET status_flag = 'I'
        ,owner_or_acctteam = DECODE(owner_or_acctteam, NULL, 'PREVIOUS', owner_or_acctteam||'|PREVIOUS')
        ,last_update_date = SYSDATE
        ,last_updated_by = G_LAST_UPDATED_BY
    WHEN NOT MATCHED THEN
  INSERT(seq_number
        ,party_id
        ,party_site_id
        ,party_site_number
        ,status_flag
        ,creation_date
        ,created_by
        ,last_update_date
        ,last_updated_by
        ,owner_or_acctteam
        )
  VALUES(CANON_E404_OWNER_AT_CHANGE_SEQ.nextval
        ,e404_oat_tmp.party_id
        ,e404_oat_tmp.party_site_id
        ,e404_oat_tmp.party_site_number
        ,'I'
        ,SYSDATE
        ,G_CREATED_BY
        ,SYSDATE
        ,G_LAST_UPDATED_BY
        ,'PREVIOUS'
        );

  dbms_output.put_line('Reprocess Error/Unprocessed records. END: COUNT: '||SQL%ROWCOUNT||' : '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   COMMIT;

   --ITG596330 -- start*/
   /*Identify any territory resource changes*/
  dbms_output.put_line('Capture Territory Resource Changes. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   MERGE INTO canon_e404_owner_at_change_tbl e404_oat
     USING ( SELECT DISTINCT
					plw.cmpy_pk party_id
					,plw.pty_loc_pk party_site_id
          ,atra.loc_num party_site_number
			   FROM acct_trty_role_asg atra
			        ,ds_org_resrc_reln dorr
              ,line_biz_role_rank_decn lbrrd
              ,pty_loc_wrk plw
              ,trty_grp_tp tgt
        WHERE atra.loc_num = plw.loc_num
          AND atra.org_cd = dorr.org_cd
          AND atra.psn_cd = dorr.psn_cd
          AND atra.trty_tp_cd = lbrrd.trty_tp_cd
          AND atra.line_biz_role_tp_cd = lbrrd.line_biz_role_tp_cd
          AND atra.trty_grp_tp_cd = lbrrd.trty_grp_tp_cd
          AND lbrrd.trty_grp_tp_cd = tgt.trty_grp_tp_cd
          AND lbrrd.src_line_biz_tp_cd = 'ESS'
          AND tgt.line_biz_tp_cd = 'ESS'
          AND lbrrd.sls_frce_accs_flg = 'Y'
          AND lbrrd.ds_acct_tp_cd = '10' -- for customer
          AND to_date(substr(dorr.ezuptime,1,14), 'RRRRmmddhh24miss')  > TO_DATE(l_from_date, 'DD-MON-YYYY HH24:MI:SS')
          AND to_date(substr(dorr.ezuptime,1,14), 'RRRRmmddhh24miss') <= DECODE(p_to_date,NULL,sysdate,TO_DATE(p_to_date, 'DD-MON-YYYY HH24:MI:SS'))
--			  WHERE jtr.terr_id = armv.terr_id
--				AND jtr.resource_id = armv.resource_id
--				AND armv.sfdc_visibility = 'Y'
--				AND jtr.last_update_date > TO_DATE(l_from_date, 'DD-MON-YYYY HH24:MI:SS')
--				AND jtr.last_update_date <= DECODE(p_to_date,NULL,sysdate,TO_DATE(p_to_date, 'DD-MON-YYYY HH24:MI:SS'))
      UNION
        SELECT DISTINCT
					plw.cmpy_pk party_id
					,plw.pty_loc_pk party_site_id
          ,atra.loc_num party_site_number
			   FROM acct_trty_role_asg atra
			        ,ds_org_resrc_reln dorr
              ,line_biz_role_rank_decn lbrrd
              ,pty_loc_wrk plw
              ,trty_grp_tp tgt
        WHERE atra.loc_num = plw.loc_num
          AND atra.org_cd = dorr.org_cd
          AND atra.psn_cd = dorr.psn_cd
          AND atra.trty_tp_cd = lbrrd.trty_tp_cd
          AND atra.line_biz_role_tp_cd = lbrrd.line_biz_role_tp_cd
          AND atra.trty_grp_tp_cd = lbrrd.trty_grp_tp_cd
          AND lbrrd.trty_grp_tp_cd = tgt.trty_grp_tp_cd
          AND lbrrd.src_line_biz_tp_cd = 'ESS'
          AND tgt.line_biz_tp_cd = 'ESS'
          AND lbrrd.sls_frce_accs_flg = 'Y'
          AND lbrrd.ds_acct_tp_cd = '0' -- for prospect
          AND to_date(substr(dorr.ezuptime,1,14), 'RRRRmmddhh24miss')  > TO_DATE(l_from_date, 'DD-MON-YYYY HH24:MI:SS')
          AND to_date(substr(dorr.ezuptime,1,14), 'RRRRmmddhh24miss') <= DECODE(p_to_date,NULL,sysdate,TO_DATE(p_to_date, 'DD-MON-YYYY HH24:MI:SS'))
--			 UNION
--			 SELECT DISTINCT
--					party_id
--					,party_site_id
--			   FROM canon_e404_pros_res_mview prmv
--			        ,jtf_terr_rsc jtr
--			  WHERE jtr.terr_id = prmv.terr_id
--				AND jtr.resource_id = prmv.resource_id
--				AND prmv.sfdc_visibility = 'Y'
--				AND jtr.last_update_date > TO_DATE(l_from_date, 'DD-MON-YYYY HH24:MI:SS')
--				AND jtr.last_update_date <= DECODE(p_to_date,NULL,sysdate,TO_DATE(p_to_date, 'DD-MON-YYYY HH24:MI:SS'))
			) res
	 ON ( e404_oat.party_site_id = res.party_site_id AND
		  e404_oat.party_id = res.party_id)
	WHEN MATCHED THEN
	UPDATE
		SET status_flag = 'I'
		   ,owner_or_acctteam = DECODE(owner_or_acctteam, NULL, 'TERRRSC', owner_or_acctteam||'|TERRRSC')
		   ,last_update_date = SYSDATE
		   ,last_updated_by = NULL
	WHEN NOT MATCHED THEN
	INSERT(seq_number
			,party_id
			,party_site_id
      ,party_site_number
			,status_flag
			,creation_date
			,created_by
			,last_update_date
			,last_updated_by
			,owner_or_acctteam
		   )
	 VALUES(CANON_E404_OWNER_AT_CHANGE_SEQ.nextval
			,res.party_id
			,res.party_site_id
      ,res.party_site_number
			,'I'
			,sysdate
			,NULL
			,SYSDATE
			,NULL
			,'TERRRSC'
			);
   dbms_output.put_line('Capture Territory Resource Changes. END: COUNT: '||SQL%ROWCOUNT||' : '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   COMMIT;
   --ITG596330 - END

   /* Use last_update_date and processed flag in AccountTeamMember's oracle companion table to identify Account Team member changes. */
   dbms_output.put_line('Capture Account Team Changes. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   l_dynsql :=
   '   MERGE '||
   '     INTO canon_e404_owner_at_change_tbl e404_oat '||
   '    USING(SELECT DISTINCT '||
   '                 party_id '||
   '                ,party_site_id '||
   '                ,plw.loc_num party_site_number ' ||
   '                ,site_sfdc_id '||
   '            FROM canon_e404_sf_acct_team_tbl '||
   '                 ,pty_loc_wrk plw ' ||
   '          WHERE status_flag LIKE ''%P'' ' ||
   '             AND plw.cmpy_pk = party_id ' ||
   '             AND plw.pty_loc_pk = party_site_id ';

   IF l_from_date IS NOT NULL THEN
      l_dynsql := l_dynsql ||
   '            AND last_update_date > TO_DATE('''||l_from_date||''', ''DD-MON-YYYY HH24:MI:SS'') ';
   END IF;

   IF p_to_date IS NOT NULL THEN
      l_dynsql := l_dynsql ||
   '            AND last_update_date <= TO_DATE('''||p_to_date||''', ''DD-MON-YYYY HH24:MI:SS'') ';
   END IF;

   l_dynsql := l_dynsql ||
   '         ) atm '||
   '       ON(    e404_oat.party_id = atm.party_id '||
   '          AND e404_oat.party_site_id = atm.party_site_id '||
   '         ) '||
   '     WHEN MATCHED THEN '||
   '   UPDATE '||
   '      SET sf_account_id = NVL(site_sfdc_id, sf_account_id) '||
   '         ,last_update_date = SYSDATE '||
   '         ,last_updated_by = NULL '||
   '         ,owner_or_acctteam = DECODE(owner_or_acctteam, NULL, ''ATM'', owner_or_acctteam||''|ATM'') '||
   '         ,status_flag = ''I'' '||
   '     WHEN NOT MATCHED THEN '||
   '   INSERT(seq_number '||
   '         ,party_id '||
   '         ,party_site_id '||
   '         ,party_site_number ' ||
   '         ,sf_account_id '||
   '         ,status_flag '||
   '         ,creation_date '||
   '         ,created_by '||
   '         ,last_update_date '||
   '         ,last_updated_by '||
   '         ,owner_or_acctteam '||
   '         ) '||
   '   VALUES(CANON_E404_OWNER_AT_CHANGE_SEQ.nextval '||
   '         ,atm.party_id '||
   '         ,atm.party_site_id '||
   '         ,atm.party_site_number ' ||
   '         ,atm.site_sfdc_id '||
   '         ,''I'' '||
   '         ,SYSDATE '||
   '         ,NULL '||
   '         ,SYSDATE '||
   '         ,NULL '||
   '         ,''ATM'' '||
   '         ) ';
    
    dbms_output.put_line('Account Team Changes sql: ' ||l_dynsql);
    
--   IF p_debug = 'Y' THEN
--      fnd_file.put_line(fnd_file.log, l_dynsql);

--      INSERT
--        INTO CANON_E200_DYNSQL_TBL
--      VALUES(G_REQUEST_ID
--            ,l_dynsql
--            ,SYSDATE
--            );
--
--      COMMIT;
--   END IF;

   EXECUTE IMMEDIATE l_dynsql;

   dbms_output.put_line( 'Capture Account Team Changes. END: COUNT: '||SQL%ROWCOUNT||' : '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   COMMIT;

   /* Fetch SF_ACCOUNT_ID from CANON_E404_SF_ACCCT_MAPPING_TBL. Handle duplicate PSNs in this table if any. */
   dbms_output.put_line('Fetch SF_ACCOUNT_ID for PSNs missing it. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

   MERGE
    INTO canon_e404_owner_at_change_tbl e404_oat
   USING(SELECT DISTINCT
                e404_oat1.party_id
               ,e404_oat1.party_site_id
               ,e404_oat1.party_site_number
               ,e404_acct.sf_account_id
             --  ,FIRST_VALUE(e404_acct.sf_account_id) OVER (PARTITION BY e404_oat1.party_site_id ORDER BY e404_acct.sf_update_date DESC) sf_account_id
           FROM canon_e404_owner_at_change_tbl e404_oat1
               ,canon_e404_sf_acct_mapping_tbl e404_acct
          WHERE e404_oat1.sf_account_id IS NULL
            AND e404_oat1.party_id = e404_acct.party_id
            AND e404_oat1.party_site_id = e404_acct.party_site_id
            AND e404_oat1.party_site_number = e404_acct.party_site_number
            AND e404_acct.status_flag = 'P'
        ) e404_acct
      ON(    e404_oat.party_id      = e404_acct.party_id
         AND e404_oat.party_site_id = e404_acct.party_site_id
         AND e404_oat.party_site_number = e404_acct.party_site_number
        )
    WHEN MATCHED THEN
  UPDATE
     SET e404_oat.sf_account_id = e404_acct.sf_account_id
        ,e404_oat.last_update_date = SYSDATE;
--        ,e404_oat.last_updated_by = G_LAST_UPDATED_BY;

   dbms_output.put_line('Fetch SF_ACCOUNT_ID for PSNs missing it. END: COUNT: '||SQL%ROWCOUNT||' : '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   COMMIT;

   --fnd_file.put_line(fnd_file.log, 'Fetch SF_PROSPECT_ID for PSNs missing it. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));*/
   /* Fetch SF_PROSPECT_ID from CANON_E404_SF_PROS_MAPPING_TBL. Handle duplicate PSNs in this table if any. */
  /* MERGE
    INTO canon_e404_owner_at_change_tbl e404_oat
   USING(SELECT DISTINCT
                e404_oat1.party_id
               ,e404_oat1.party_site_id
               ,FIRST_VALUE(e404_pros.sf_prospect_id) OVER(PARTITION BY e404_oat1.party_site_id ORDER BY e404_pros.sf_update_date DESC) sf_prospect_id
           FROM canon_e404_owner_at_change_tbl e404_oat1
               ,canon_e404_sf_pros_mapping_tbl e404_pros
          WHERE e404_oat1.sf_account_id IS NULL
            AND e404_oat1.party_id = e404_pros.party_id
            AND e404_oat1.party_site_id = e404_pros.party_site_id
            AND e404_pros.load_status = 'P'
        ) e404_pros
      ON(    e404_oat.party_id      = e404_pros.party_id
         AND e404_oat.party_site_id = e404_pros.party_site_id
        )
    WHEN MATCHED THEN
  UPDATE
     SET e404_oat.sf_account_id = e404_pros.sf_prospect_id
        ,e404_oat.last_update_date = SYSDATE
        ,e404_oat.last_updated_by = G_LAST_UPDATED_BY;

   fnd_file.put_line(fnd_file.log, 'Fetch SF_PROSPECT_ID for PSNs missing it. END: COUNT: '||SQL%ROWCOUNT||' : '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   COMMIT;

   MERGE
    INTO canon_e404_owner_at_change_tbl e404_oat
   USING(SELECT DISTINCT
                e404_oat1.party_id
               ,e404_oat1.party_site_id
               ,hps.party_site_number
           FROM canon_e404_owner_at_change_tbl e404_oat1
               ,hz_party_sites hps
          WHERE hps.party_site_id = e404_oat1.party_site_id
        ) hps
      ON(    hps.party_site_id = e404_oat.party_site_id
         AND hps.party_id = e404_oat.party_id
        )
   WHEN MATCHED THEN
 UPDATE
    SET party_site_number = hps.party_site_number
       ,last_update_date = SYSDATE
       ,last_updated_by = G_LAST_UPDATED_BY;

   COMMIT;*/

   /* If we cannot identify Salesforce Account ID, mark those PSN as Errors so they can be retried the next day.*/
   dbms_output.put_line('Mark records in error for missing SF Account ID. START: '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   UPDATE canon_e404_owner_at_change_tbl
      SET status_flag = 'IE'
         ,status_message = 'Unable to find Salesforce Account ID using PSN in E404 tables. Marking this record as an Error as a part of Extract Program.'
         ,last_update_date = SYSDATE
         ,last_updated_by = NULL
    WHERE sf_account_id IS NULL;

   dbms_output.put_line('Mark records in error for missing SF Account ID. END: COUNT: '||SQL%ROWCOUNT||' : '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   COMMIT;

   /*l_profile_saved := fnd_profile.save('CANON_E404_OWNERAT_REFRESHFROM', l_program_start, 'SITE');

   IF l_profile_saved THEN
      fnd_file.put_line(fnd_file.log, '"Canon: E404 Owner AT Refresh From" profile date set to '||l_program_start);
   ELSE
      fnd_file.put_line(fnd_file.log, 'Unable to set "Canon: E404 Owner AT Refresh From" profile date to '||l_program_start);
   END IF;*/
   
   UPDATE canon_e001_profile_values_tbl
     SET profile_value = l_program_start
    WHERE profile_id = (SELECT profile_id
                          FROM canon_e001_profiles_tbl
                          WHERE profile_name = 'CANON_E404_OWNERAT_REFRESHFROM');
   COMMIT;
   
EXCEPTION
   WHEN E_INVALID_PARAMS THEN
      x_errbuf := 'Invalid Params. From Date and To Date should be in DD-MON-YYYY HH24:MI:SS format. Debug should be Y/N';
      x_retcode := '2';
   WHEN OTHERS THEN
   dbms_output.put_line('error : ' || SQLERRM);
      canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
      x_errbuf := SQLERRM;
      x_retcode := '2';
END extract_owner_at_changes;

PROCEDURE get_sf_account_id(x_sf_id OUT VARCHAR2, p_psn IN VARCHAR2)
IS
   l_PROCEDURE_NAME    VARCHAR2(30) := 'get_sf_account_id';
BEGIN
   BEGIN
    SELECT sf_Account_id
      INTO x_sf_id
      FROM canon_e404_sf_acct_mapping_tbl
      WHERE party_site_number = p_psn;
    EXCEPTION WHEN NO_DATA_FOUND THEN
        x_sf_id := null;
    END;
EXCEPTION WHEN OTHERS THEN
      x_sf_id := null;
      dbms_output.put_line('error : ' || SQLERRM);
      canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END get_sf_account_id;


END canon_e404_owner_at_change_pkg;
/