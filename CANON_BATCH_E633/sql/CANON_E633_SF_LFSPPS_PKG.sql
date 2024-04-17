create or replace PACKAGE                    CANON_E633_SF_LFSPPS_PKG
AS
G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E633_SF_LFSPPS_PKG';

PROCEDURE load_customers(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE populate_lfs_resrc(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE populate_pps_resrc(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE delete_ib(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_ib(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE update_dependIDs_4_IB (p_handler_name IN varchar2, x_return_status OUT VARCHAR2);
PROCEDURE load_config_ib(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_meter_reads(retcode OUT VARCHAR2,errbuff OUT VARCHAR2);
PROCEDURE load_contracts(retcode OUT VARCHAR2,errbuff OUT VARCHAR2);
PROCEDURE load_closed_service_calls(retcode OUT VARCHAR2,errbuff OUT VARCHAR2);
PROCEDURE load_open_service_calls(retcode OUT VARCHAR2,errbuff OUT VARCHAR2);

PROCEDURE load_ib_contacts(retcode OUT VARCHAR2,errbuff OUT VARCHAR2);
PROCEDURE load_ar_aging(retcode OUT VARCHAR2,errbuff OUT VARCHAR2);
PROCEDURE load_order_headers(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_order_details(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_order_comments(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE load_sales_comp(retcode OUT VARCHAR2, errbuff OUT VARCHAR2);
PROCEDURE update_dependIDs_4_AR (p_handler_name IN varchar2, x_return_status OUT VARCHAR2);
PROCEDURE update_dependIDs(p_handler_name IN varchar2, x_return_status OUT VARCHAR2);
PROCEDURE update_dependIDs_4_ord(p_handler_name IN varchar2, x_return_status OUT VARCHAR2);
PROCEDURE load_cfslease(retcode OUT VARCHAR2,errbuff OUT VARCHAR2);
PROCEDURE extract(errbuff OUT VARCHAR2, retcode OUT VARCHAR2, p_extract IN VARCHAR2);
FUNCTION format_phone (v_phone IN VARCHAR2) RETURN VARCHAR2;
PROCEDURE delete_contracts(retcode OUT VARCHAR2, errbuff OUT VARCHAR2); --QC58464
PROCEDURE delete_open_service_calls(retcode OUT VARCHAR2, errbuff OUT VARCHAR2); --QC58464
PROCEDURE delete_closed_service_calls(retcode OUT VARCHAR2, errbuff OUT VARCHAR2); --QC58464
PROCEDURE delete_meter_reads(retcode OUT VARCHAR2, errbuff OUT VARCHAR2); --QC58464

END canon_e633_sf_LFSPPS_pkg;
/
create or replace PACKAGE BODY              CANON_E633_SF_LFSPPS_PKG
AS
PROCEDURE populate_lfs_resrc(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
  l_i_ret_sts     VARCHAR2(1); --insert retun status
  l_i_msg_data    VARCHAR2(2000); --insert message
  l_u_ret_sts     VARCHAR2(1) := 'S'; --update return status
  l_u_msg_data    VARCHAR2(2000); --update message
  l_tier_mgr_psn_col VARCHAR2(100) := NULL;
  l_sql             VARCHAR2(32767) := NULL;
  l_tier_mgr_psn_cd VARCHAR2(100) := NULL;
  l_tier_mgr_nm VARCHAR2(500) := NULL;
  l_is_mgr_active VARCHAR2(10) := 'FALSE';
  l_PROCEDURE_NAME VARCHAR2(25) := 'populate_lfs_resrc';
BEGIN

EXECUTE IMMEDIATE  'TRUNCATE TABLE CANON_E633_LFS_RSCTERR_TBL';
  dbms_output.put_line('Truncated Table CANON_E633_LFS_RSCTERR_TBL');
  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Truncated Table CANON_E633_LFS_RSCTERR_TBL',NULL,NULL,NULL,NULL);


FOR rscterr_rec IN (SELECT distinct org.org_cd terr_id
                              ,org.org_nm territory
                              ,org_tier_cd
                              ,org.psn_cd resource_id
                              ,(case WHEN org.psn_cd IS NOT NULL THEN psn.psn_last_nm || ', ' || psn.psn_first_nm
                                            ELSE '' END) resource_name
                              ,(case WHEN org.psn_cd IS NOT NULL THEN psn.psn_num ELSE '' END) employee_number
                              ,org.comments
                           FROM (SELECT DISTINCT
                                        org_cd
                                        ,org_nm
                                        ,org_tier_cd
                                        ,(CASE WHEN rep_count = 1 THEN psn_cd ELSE NULL END) psn_cd
                                        ,(CASE WHEN rep_count = 0 THEN 'No salesreps assigned to the territory.'
                                               WHEN rep_count > 1 THEN 'Multiple salesreps assigned to the territory.'
                                            ELSE NULL
                                           END
                                          ) comments
                                    FROM( SELECT dorr.org_cd
                                                ,dorr.psn_cd
                                                ,dou.org_nm
                                                ,dou.org_tier_cd
                                                ,(CASE WHEN dorr.acct_team_role_tp_cd IS NULL THEN 'Y' ELSE 'N' END) rep
                                                ,SUM((CASE WHEN dorr.acct_team_role_tp_cd IS NULL AND dorr.psn_cd IS NOT NULL THEN 1 ELSE 0 END)) OVER(PARTITION BY dorr.org_cd) rep_count
                                            FROM ds_org_unit          dou
                                                ,ds_org_resrc_reln    dorr
                                                ,biz_area_org bao
                                                ,org_stru_tp ost
                                           WHERE dorr.org_cd(+) = dou.org_cd
                                             AND dorr.glbl_cmpy_cd(+) = 'ADB'
                                             AND dorr.ezcancelflag(+) = '0'
                                             AND dou.glbl_cmpy_cd = 'ADB'
                                             AND dou.ezcancelflag = '0'
                                             AND bao.glbl_cmpy_cd = 'ADB'
                                             AND bao.ezcancelflag = '0'
                                             AND dorr.glbl_cmpy_cd(+) = dou.glbl_cmpy_cd
                                             AND dorr.org_stru_tp_cd(+) = ost.org_stru_tp_cd
                                             AND dorr.org_stru_tp_cd(+) = bao.org_stru_tp_cd
                                             AND dorr.acct_team_role_tp_cd(+) IS NULL
                                             AND bao.sls_flg = 'Y'
											 AND dou.first_org_cd = bao.biz_area_org_cd
                                             AND ost.glbl_cmpy_cd = 'ADB'
                                             AND ost.ezcancelflag = '0'
                                             AND ost.trty_stru_flg = 'Y'
                                             AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dorr.eff_FROM_DT(+),'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dorr.eff_THRU_DT(+), 'yyyymmdd'),SYSDATE))
                                             AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                         )
                                         ) org
                                        ,s21_psn psn
                                        ,canon_e633_lfs_user_map_tbl ceum
                                       WHERE psn.psn_cd = org.psn_cd
                                         AND psn.ezcancelflag = '0'
                                         AND psn.glbl_cmpy_cd = 'ADB'
                                         AND psn.line_biz_tp_cd IN ('LFS','ALL')
                                         AND ceum.resource_id = psn.psn_cd
                                         AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(psn.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(psn.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                         AND ( ceum.is_active = 'TRUE' OR
                                                (ceum.is_active = 'FALSE' AND
                                                    (upper(ceum.last_name) like 'OPEN TERR%' OR  --QC51650
                                                    upper(ceum.first_name) like 'OPEN TERR%')
                                                 )
                                              )
                                         AND ceum.sf_user_id IS NOT NULL
                                        AND ceum.status_flag = 'P'
                      ) LOOP
                        --dbms_output.put_line('org_tier_cd : ' ||rscterr_rec.org_tier_cd);

                            /* ***** commented for QC54017

							FOR tier_cntr IN REVERSE 1..rscterr_rec.org_tier_cd
                              LOOP
                                l_tier_mgr_psn_col := NULL;
                                l_sql := NULL;
                                l_tier_mgr_psn_cd := NULL;
                                l_tier_mgr_nm := NULL;

                                --dbms_output.put_line('org_tier_cd : ' ||rscterr_rec.org_tier_cd);

                                l_tier_mgr_psn_col := (CASE tier_cntr WHEN '0' THEN 'first_org_mgr_'  --QC51650
                                                                          WHEN '1' THEN 'scd_org_mgr_'
                                                                          WHEN '2' THEN 'third_org_mgr_'
                                                                          WHEN '3' THEN 'frth_org_mgr_'
                                                                          WHEN '4' THEN 'fifth_org_mgr_'
                                                                          WHEN '5' THEN 'sixth_org_mgr_'
                                                                          WHEN '6' THEN 'svnth_org_mgr_'
                                                                          WHEN '7' THEN 'eighth_org_mgr_'
                                                                          WHEN '8' THEN 'ninth_org_mgr_'
                                                                          WHEN '9' THEN 'tenth_org_mgr_'
                                                                          WHEN '10' THEN 'elvth_org_mgr_'
                                                                          ELSE '' END);
                                --dbms_output.put_line('l_tier_mgr_psn_col : ' || l_tier_mgr_psn_col);


                                l_sql := 'SELECT distinct ' || l_tier_mgr_psn_col || 'psn_cd,' || l_tier_mgr_psn_col || 'first_nm || ' || l_tier_mgr_psn_col || 'last_nm' ||

                                 ' FROM toc_org_mgr_info tomi' ||
                                 '     ,org_func_asg ofa ' ||
								 '     ,toc t ' ||
                                 ' WHERE tomi.ezcancelflag = ''0'' ' ||
                                 '   AND tomi.glbl_cmpy_cd = ''ADB'' ' ||
                                 '   AND ofa.ezcancelflag = ''0'' '||
                                 '   AND ofa.glbl_cmpy_cd = ''ADB'' ' ||
								 '   AND t.ezcancelflag = ''0'' ' ||
								 '   AND t.glbl_cmpy_cd = ''ADB'' ' ||
                                 '   AND ofa.psn_cd = ''' || rscterr_rec.resource_id || '''' ||
--                                 ' AND ofa.toc_cd = tomi.toc_cd '  ||
                                 ' AND tomi.psn_cd = ofa.psn_cd ' ||
								 ' AND ofa.toc_cd = t.toc_cd ' ||
                                 ' AND tomi.org_func_role_tp_cd = t.org_func_role_tp_cd ' ||
                                 ' AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ofa.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(ofa.eff_THRU_DT, ''yyyymmdd''),SYSDATE))' ||
                                 ' AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, ''yyyymmdd''),SYSDATE))';
--                                 ' AND tomi.' || l_tier_mgr_psn_col || 'psn_cd' || ' IS NOT NULL' ;

                                 --dbms_output.put_line('l_sql: ' ||l_sql);

                                 BEGIN
                                    EXECUTE IMMEDIATE l_sql INTO l_tier_mgr_psn_cd,l_tier_mgr_nm;
                                 EXCEPTION
                                    WHEN OTHERS THEN
                                         l_tier_mgr_psn_cd := NULL;
                                         l_tier_mgr_nm := NULL;
                                        dbms_output.put_line(sqlerrm);
                                  END;


                                EXIT WHEN l_tier_mgr_psn_cd IS NOT NULL AND l_tier_mgr_nm IS NOT NULL;
                              END LOOP;

                              --dbms_output.put_line('l_tier_mgr_psn_cd : ' || l_tier_mgr_psn_cd);
                              --dbms_output.put_line('l_tier_mgr_nm : '|| l_tier_mgr_nm);

                              l_is_mgr_active := 'TRUE';
                              ---commented for testing
--                              l_is_mgr_active := 'FALSE';

                              BEGIN
                              SELECT 'TRUE'
                                 INTO l_is_mgr_active
                                 FROM canon_e633_lfs_user_map_tbl
                                WHERE employee_number = l_tier_mgr_psn_cd
                                  AND is_active = 'TRUE'
                                  AND status_flag = 'P'
--                                  AND run_status = 'S'
                                  AND sf_user_id IS NOT NULL;
                              EXCEPTION
                                WHEN OTHERS THEN
                                    l_is_mgr_active := 'FALSE';
                                    dbms_output.put_line('l_is_mgr_active : ' || sqlerrm);
                              END;
                                  */

                             BEGIN
                             --dbms_output.put_line('!!!!!l_is_mgr_active : '|| l_is_mgr_active);
                             --dbms_output.put_line('rscterr_rec.territory : ' || rscterr_rec.territory);
                             --dbms_output.put_line('rscterr_rec.terr_id : ' || rscterr_rec.terr_id);
                             --dbms_output.put_line('rscterr_rec.resource_id : ' || rscterr_rec.resource_id);
                             --dbms_output.put_line('rscterr_rec.resource_name : ' || rscterr_rec.resource_name);
                             --dbms_output.put_line('rscterr_rec.employee_number : ' || rscterr_rec.employee_number);
                             --dbms_output.put_line('l_tier_mgr_psn_cd  : ' || l_tier_mgr_psn_cd );
                             --dbms_output.put_line('l_tier_mgr_nm  : ' || l_tier_mgr_nm );
                             --dbms_output.put_line('l_tier_mgr_psn_cd  : ' || l_tier_mgr_psn_cd );
                             --dbms_output.put_line('rscterr_rec.comments  : ' || rscterr_rec.comments );
                             /*IF l_is_mgr_active = 'TRUE' THEN
                                INSERT INTO canon_e633_lfs_rscterr_tbl(
                                                    territory_name
                                                    ,terr_id
                                                    ,resource_id
                                                    ,resource_name
                                                    ,employee_number
                                                    ,mgr_resource_id
                                                    ,mgr_resource_name
                                                    ,mgr_employee_number
                                                    ,comments
                                                  ) values ( rscterr_rec.territory
                                                             ,rscterr_rec.terr_id
                                                             ,rscterr_rec.resource_id
                                                             ,rscterr_rec.resource_name
                                                             ,rscterr_rec.employee_number
                                                             ,l_tier_mgr_psn_cd
                                                             ,l_tier_mgr_nm
                                                             ,l_tier_mgr_psn_cd
                                                             ,rscterr_rec.comments);

                             ELSE */
                                INSERT INTO canon_e633_lfs_rscterr_tbl(
                                                    territory_name
                                                    ,terr_id
                                                    ,resource_id
                                                    ,resource_name
                                                    ,employee_number
                                                    ,mgr_resource_id
                                                    ,mgr_resource_name
                                                    ,mgr_employee_number
                                                    ,comments
                                                  ) values ( rscterr_rec.territory
                                                             ,rscterr_rec.terr_id
                                                             ,rscterr_rec.resource_id
                                                             ,rscterr_rec.resource_name
                                                             ,rscterr_rec.employee_number
                                                             ,NULL
                                                             ,NULL
                                                             ,NULL
                                                             ,'Manager resource is inactive / no Salesforce User ID in mapping table');
                            --END IF;
                            EXCEPTION
                                WHEN OTHERS THEN
                                    dbms_output.put_line('Inserted CANON_E633_LFS_RSCTERR_TBL : ' ||sqlerrm);
                            END;

                      END LOOP;

      dbms_output.put_line('Inserted into CANON_E633_LFS_RSCTERR_TBL:' ||SQL%ROWCOUNT);
	  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Inserted into CANON_E633_LFS_RSCTERR_TBL:' ||SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;
 EXCEPTION
    WHEN OTHERS THEN
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
 END populate_lfs_resrc;

 PROCEDURE populate_pps_resrc(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
AS
    l_i_ret_sts     VARCHAR2(1); --insert retun status
  l_i_msg_data    VARCHAR2(2000); --insert message
  l_u_ret_sts     VARCHAR2(1) := 'S'; --update return status
  l_u_msg_data    VARCHAR2(2000); --update message
  l_tier_mgr_psn_col VARCHAR2(100) := NULL;
  l_sql             VARCHAR2(32767) := NULL;
  l_tier_mgr_psn_cd VARCHAR2(100) := NULL;
  l_tier_mgr_nm VARCHAR2(500) := NULL;
  l_is_mgr_active VARCHAR2(10) := 'FALSE';
  l_PROCEDURE_NAME VARCHAR2(25) := 'populate_pps_resrc';
BEGIN

EXECUTE IMMEDIATE  'TRUNCATE TABLE CANON_E633_PPS_RSCTERR_TBL';
  dbms_output.put_line('Truncated Table CANON_E633_PPS_RSCTERR_TBL');
  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Truncated Table CANON_E633_PPS_RSCTERR_TBL',NULL,NULL,NULL,NULL);

FOR rscterr_rec IN (SELECT distinct org.org_cd terr_id
                              ,org.org_nm territory
                              ,org_tier_cd
                              ,org.psn_cd resource_id
                              ,(case WHEN org.psn_cd IS NOT NULL THEN psn.psn_last_nm || ', ' || psn.psn_first_nm
                                            ELSE '' END) resource_name
                              ,(case WHEN org.psn_cd IS NOT NULL THEN psn.psn_num ELSE '' END) employee_number
                              ,org.comments
                           FROM (SELECT DISTINCT
                                        org_cd
                                        ,org_nm
                                        ,org_tier_cd
                                        ,(CASE WHEN rep_count = 1 THEN psn_cd ELSE NULL END) psn_cd
                                        ,(CASE WHEN rep_count = 0 THEN 'No salesreps assigned to the territory.'
                                               WHEN rep_count > 1 THEN 'Multiple salesreps assigned to the territory.'
                                            ELSE NULL
                                           END
                                          ) comments
                                    FROM( SELECT dorr.org_cd
                                                ,dorr.psn_cd
                                                ,dou.org_nm
                                                ,dou.org_tier_cd
                                                ,(CASE WHEN dorr.acct_team_role_tp_cd IS NULL THEN 'Y' ELSE 'N' END) rep
                                                ,SUM((CASE WHEN dorr.acct_team_role_tp_cd IS NULL AND dorr.psn_cd IS NOT NULL THEN 1 ELSE 0 END)) OVER(PARTITION BY dorr.org_cd) rep_count
                                            FROM ds_org_unit          dou
                                                ,ds_org_resrc_reln    dorr
                                                ,biz_area_org bao
                                                ,org_stru_tp ost
                                           WHERE dorr.org_cd(+) = dou.org_cd
                                             AND dorr.glbl_cmpy_cd(+) = 'ADB'
                                             AND dorr.ezcancelflag(+) = '0'
                                             AND dou.glbl_cmpy_cd = 'ADB'
                                             AND dou.ezcancelflag = '0'
                                             AND bao.glbl_cmpy_cd = 'ADB'
                                             AND bao.ezcancelflag = '0'
                                             AND dorr.glbl_cmpy_cd(+) = dou.glbl_cmpy_cd
                                             AND dorr.org_stru_tp_cd(+) = ost.org_stru_tp_cd
                                             AND dorr.org_stru_tp_cd(+) = bao.org_stru_tp_cd
                                             AND dorr.acct_team_role_tp_cd(+) IS NULL
                                             AND bao.sls_flg = 'Y'
											 AND dou.first_org_cd = bao.biz_area_org_cd
                                             AND ost.glbl_cmpy_cd = 'ADB'
                                             AND ost.trty_stru_flg = 'Y'
                                             AND ost.glbl_cmpy_cd = 'ADB'
                                             AND ost.ezcancelflag = '0'
                                             AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dorr.eff_FROM_DT(+),'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dorr.eff_THRU_DT(+), 'yyyymmdd'),SYSDATE))
                                             AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                         )
                                         ) org
                                        ,s21_psn psn
                                        ,canon_e633_pps_user_map_tbl ceum
                                       WHERE psn.psn_cd = org.psn_cd
                                         AND psn.line_biz_tp_cd IN ('PPS','ALL')
                                         AND ceum.resource_id = psn.psn_cd
                                         AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(psn.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(psn.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                                         AND ( ceum.is_active = 'TRUE' OR
                                                (ceum.is_active = 'FALSE' AND
                                                    (upper(ceum.last_name) like 'OPEN TERR%' OR  --QC51650
                                                    upper(ceum.first_name) like 'OPEN TERR%')
                                                 )
                                              )
                                         AND ceum.sf_user_id IS NOT NULL
                                        AND ceum.status_flag = 'P'

                      ) LOOP
                        --dbms_output.put_line('org_tier_cd : ' ||rscterr_rec.org_tier_cd);

						/* **** commented for QC54017
                            FOR tier_cntr IN REVERSE 1..rscterr_rec.org_tier_cd
                              LOOP
                                l_tier_mgr_psn_col := NULL;
                                l_sql := NULL;
                                l_tier_mgr_psn_cd := NULL;
                                l_tier_mgr_nm := NULL;

                                --dbms_output.put_line('org_tier_cd : ' ||rscterr_rec.org_tier_cd);

                                l_tier_mgr_psn_col := (CASE tier_cntr WHEN '0' THEN 'first_org_mgr_'  --QC51650
                                                                          WHEN '1' THEN 'scd_org_mgr_'
                                                                          WHEN '2' THEN 'third_org_mgr_'
                                                                          WHEN '3' THEN 'frth_org_mgr_'
                                                                          WHEN '4' THEN 'fifth_org_mgr_'
                                                                          WHEN '5' THEN 'sixth_org_mgr_'
                                                                          WHEN '6' THEN 'svnth_org_mgr_'
                                                                          WHEN '7' THEN 'eighth_org_mgr_'
                                                                          WHEN '8' THEN 'ninth_org_mgr_'
                                                                          WHEN '9' THEN 'tenth_org_mgr_'
                                                                          WHEN '10' THEN 'elvth_org_mgr_'
                                                                          ELSE '' END);
                                --dbms_output.put_line('l_tier_mgr_psn_col : ' || l_tier_mgr_psn_col);


                                l_sql := 'SELECT distinct ' || l_tier_mgr_psn_col || 'psn_cd,' || l_tier_mgr_psn_col || 'first_nm || ' || l_tier_mgr_psn_col || 'last_nm' ||

                                 ' FROM toc_org_mgr_info tomi' ||
                                 '     ,org_func_asg ofa ' ||
								 '     ,toc t ' ||
                                 ' WHERE tomi.ezcancelflag = ''0'' ' ||
                                 '   AND tomi.glbl_cmpy_cd = ''ADB'' ' ||
                                 '   AND ofa.ezcancelflag = ''0'' '||
                                 '   AND ofa.glbl_cmpy_cd = ''ADB'' ' ||
								 '   AND t.ezcancelflag = ''0'' ' ||
								 '   AND t.glbl_cmpy_cd = ''ADB'' ' ||
                                 '  AND ofa.psn_cd = ''' || rscterr_rec.resource_id || '''' ||
--                                 ' AND ofa.toc_cd = tomi.toc_cd '  ||
                                 ' AND tomi.psn_cd = ofa.psn_cd ' ||
								 ' AND ofa.toc_cd = t.toc_cd ' ||
                                 ' AND tomi.org_func_role_tp_cd = t.org_func_role_tp_cd ' ||
                                 ' AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ofa.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(ofa.eff_THRU_DT, ''yyyymmdd''),SYSDATE))' ||
                                 ' AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, ''yyyymmdd''),SYSDATE))';
--                                 ' AND tomi.' || l_tier_mgr_psn_col || 'psn_cd' || ' IS NOT NULL' ;

                                 --dbms_output.put_line('l_sql: ' ||l_sql);

                                 BEGIN
                                    EXECUTE IMMEDIATE l_sql INTO l_tier_mgr_psn_cd,l_tier_mgr_nm;
                                 EXCEPTION
                                    WHEN OTHERS THEN
                                         l_tier_mgr_psn_cd := NULL;
                                         l_tier_mgr_nm := NULL;
                                        dbms_output.put_line(sqlerrm);
                                  END;


                                EXIT WHEN l_tier_mgr_psn_cd IS NOT NULL AND l_tier_mgr_nm IS NOT NULL;
                              END LOOP;

                              --dbms_output.put_line('l_tier_mgr_psn_cd : ' || l_tier_mgr_psn_cd);
                              --dbms_output.put_line('l_tier_mgr_nm : '|| l_tier_mgr_nm);

                              l_is_mgr_active := 'TRUE';
                              ---commented for testing
--                              l_is_mgr_active := 'FALSE';

                              BEGIN
                              SELECT 'TRUE'
                                 INTO l_is_mgr_active
                                 FROM canon_e633_pps_user_map_tbl
                                WHERE employee_number = l_tier_mgr_psn_cd
                                  AND is_active = 'TRUE'
                                  AND status_flag = 'P'
--                                  AND run_status = 'S'
                                  AND sf_user_id IS NOT NULL;
                              EXCEPTION
                                WHEN OTHERS THEN
                                    l_is_mgr_active := 'FALSE';
                                    dbms_output.put_line('l_is_mgr_active : ' || sqlerrm);
                              END;
                                  */

                             BEGIN
                            -- dbms_output.put_line('!!!!!l_is_mgr_active : '|| l_is_mgr_active);
                            -- dbms_output.put_line('rscterr_rec.territory : ' || rscterr_rec.territory);
                            -- dbms_output.put_line('rscterr_rec.terr_id : ' || rscterr_rec.terr_id);
                            -- dbms_output.put_line('rscterr_rec.resource_id : ' || rscterr_rec.resource_id);
                            -- dbms_output.put_line('rscterr_rec.resource_name : ' || rscterr_rec.resource_name);
                            -- dbms_output.put_line('rscterr_rec.employee_number : ' || rscterr_rec.employee_number);
                            -- dbms_output.put_line('l_tier_mgr_psn_cd  : ' || l_tier_mgr_psn_cd );
                            -- dbms_output.put_line('l_tier_mgr_nm  : ' || l_tier_mgr_nm );
                            -- dbms_output.put_line('l_tier_mgr_psn_cd  : ' || l_tier_mgr_psn_cd );
                            -- dbms_output.put_line('rscterr_rec.comments  : ' || rscterr_rec.comments );
                            /* IF l_is_mgr_active = 'TRUE' THEN
                                INSERT INTO canon_e633_pps_rscterr_tbl(
                                                    territory_name
                                                    ,terr_id
                                                    ,resource_id
                                                    ,resource_name
                                                    ,employee_number
                                                    ,mgr_resource_id
                                                    ,mgr_resource_name
                                                    ,mgr_employee_number
                                                    ,comments
                                                  ) values ( rscterr_rec.territory
                                                             ,rscterr_rec.terr_id
                                                             ,rscterr_rec.resource_id
                                                             ,rscterr_rec.resource_name
                                                             ,rscterr_rec.employee_number
                                                             ,l_tier_mgr_psn_cd
                                                             ,l_tier_mgr_nm
                                                             ,l_tier_mgr_psn_cd
                                                             ,rscterr_rec.comments);

                             ELSE */
                                INSERT INTO canon_e633_pps_rscterr_tbl(
                                                    territory_name
                                                    ,terr_id
                                                    ,resource_id
                                                    ,resource_name
                                                    ,employee_number
                                                    ,mgr_resource_id
                                                    ,mgr_resource_name
                                                    ,mgr_employee_number
                                                    ,comments
                                                  ) values ( rscterr_rec.territory
                                                             ,rscterr_rec.terr_id
                                                             ,rscterr_rec.resource_id
                                                             ,rscterr_rec.resource_name
                                                             ,rscterr_rec.employee_number
                                                             ,NULL
                                                             ,NULL
                                                             ,NULL
                                                             ,'Manager resource is inactive / no Salesforce User ID in mapping table');
                           -- END IF;
                            EXCEPTION
                                WHEN OTHERS THEN
                                    dbms_output.put_line('Inserted CANON_E633_PPS_RSCTERR_TBL : ' ||sqlerrm);
                            END;

                      END LOOP;

      dbms_output.put_line('Inserted into CANON_E633_PPS_RSCTERR_TBL:' ||SQL%ROWCOUNT);
	  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Inserted into CANON_E633_PPS_RSCTERR_TBL:' ||SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
      COMMIT;
 EXCEPTION
    WHEN OTHERS THEN
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
 END populate_pps_resrc;

	PROCEDURE load_customers(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
	AS
		l_procedure_name         VARCHAR2(60) := 'load_customers';
		l_sf_user_id    VARCHAR2(18) := null;
		l_lfs_resrc_retcode VARCHAR2(10);
		l_lfs_resrc_errbuff VARCHAR2(10);
		l_pps_resrc_retcode varchar2(10);
		l_pps_resrc_errbuff varchar2(10);
		l_dyn_sql varchar2(32767) := null;
		l_sf_user_emp_num varchar2(20) := null;
		L_RUN_DATE  DATE    := SYSDATE;
		L_LAST_RUN_DATE DATE;
		L_PROGRAM_START_DATE DATE := SYSDATE;
		l_last_run_date_num VARCHAR2(17);

		CURSOR dynamic_cols_cur (Customer_OR_Prospect_Value VARCHAR2, business_unit varchar2)
       IS
       SELECT lbrrd.asg_trty_attrb_cd attribute_id
              ,lbrrd.asg_trty_attrb_nm attribute_value
        FROM line_biz_role_rank_decn lbrrd
             ,trty_grp_tp tgt
       WHERE lbrrd.ds_acct_tp_cd = Customer_OR_Prospect_Value
         AND lbrrd.trty_grp_tp_cd = tgt.trty_grp_tp_cd
         AND tgt.line_biz_tp_cd = business_unit
         AND lbrrd.src_line_biz_tp_cd = tgt.line_biz_tp_cd
		 AND lbrrd.trty_grp_tp_cd <> 'IS'  -- QC57599 to exclude IS reps to be the owners or account team members
         AND lbrrd.line_biz_rank_num is not null --QC57599
    ORDER BY tgt.trty_grp_rank_num
             ,lbrrd.line_biz_rank_num;

		BEGIN

            retcode := '0';

			BEGIN
			   SELECT LAST_RUN_DT
				 INTO l_last_run_date
				 FROM CANON_E633_INCRDT_TRACKER_V
				WHERE extract_prg = 'CUSTOMER';

			   IF l_last_run_date IS NULL THEN
				  L_LAST_RUN_DATE := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
			   END IF;

			   L_LAST_RUN_DATE_NUM := TO_CHAR(L_LAST_RUN_DATE, 'RRRRMMDDHH24MISS');
			END;

            --populate resource tables
            populate_lfs_resrc(l_lfs_resrc_retcode, l_lfs_resrc_errbuff);
            populate_pps_resrc(l_pps_resrc_retcode, l_pps_resrc_errbuff);

           /* --reprocess
            delete from canon_e633_cust_site_stg_tbl
            where lfs_sf_account_id IS NULL;
            canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','deleted from CANON_E633_CUST_SITE_STG_TBL where lfs account id is NULL:' ||SQL%ROWCOUNT,NULL,NULL,NULL,NULL);

			--reprocess
            delete from canon_e633_cust_site_stg_tbl
            where pps_sf_account_id IS NULL;
            canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','deleted from CANON_E633_CUST_SITE_STG_TBL where pps account id is NULL:' ||SQL%ROWCOUNT,NULL,NULL,NULL,NULL);
			*/
           --to reprocess error records
			UPDATE canon_e633_cust_site_stg_tbl
			   SET lfs_sf_status_flag = CASE WHEN lfs_sf_account_id IS NOT NULL THEN 'U' ELSE 'I' END
                  ,lfs_sf_status_message = NULL
			 WHERE lfs_sf_status_flag = 'E';

--            //fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' lfs error records reset.');
            dbms_output.put_line(SQL%ROWCOUNT||' lfs error records reset.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' lfs error records reset.',NULL,NULL,NULL,NULL);

			UPDATE canon_e633_cust_site_stg_tbl
			   SET pps_sf_status_flag = CASE WHEN pps_sf_account_id IS NOT NULL THEN 'U' ELSE 'I' END
                  ,pps_sf_status_message = NULL
			 WHERE pps_sf_status_flag = 'E';

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' pps error records reset.');
            dbms_output.put_line(SQL%ROWCOUNT||' pps error records reset.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' pps error records reset.',NULL,NULL,NULL,NULL);

            COMMIT;

			--if any changes on the LFS inactive accounts(for converted accounts) --QC52188
      MERGE INTO canon_e633_cust_site_stg_tbl e633
       USING (SELECT plw.loc_num location_number
                     ,dac.sell_to_cust_cd account_number
                FROM sell_to_cust dac
                     ,pty_loc_wrk plw
                     ,canon_e633_cust_site_stg_tbl e633
                WHERE e633.is_deleted = 'Y'
                  AND e633.location_number = plw.loc_num
                  AND plw.cmpy_pk = dac.cmpy_pk
                      AND plw.ezcancelflag = '0'
                      AND plw.glbl_cmpy_cd = 'ADB'
                      AND dac.ezcancelflag = plw.ezcancelflag
                      AND dac.glbl_cmpy_cd = plw.glbl_cmpy_cd
                      AND (plw.rgtn_sts_cd = 'P99' OR dac.rgtn_sts_cd = 'P99')
                      AND lfs_sf_account_id IS NOT NULL
                      AND NVL(lfs_owner_employee_number,'NA') <> 'CANON'
                UNION
					SELECT plw.loc_num location_number
                 ,dac.ds_acct_num account_number
                        FROM ds_acct_pros dac
                             ,pros_pty_loc_wrk plw
                             ,canon_e633_cust_site_stg_tbl e633
                        WHERE e633.is_deleted = 'Y'
                          AND e633.location_number = plw.loc_num
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND dac.glbl_cmpy_cd = plw.glbl_cmpy_cd
                          AND dac.ezcancelflag = plw.ezcancelflag
						  AND plw.loc_num = dac.loc_num --QC51955
                          AND (plw.rgtn_sts_cd = 'P99' OR dac.rgtn_sts_cd = 'P99')
                          AND lfs_sf_account_id IS NOT NULL
                          AND NVL(lfs_owner_employee_number,'NA') <> 'CANON'
				) S21
			ON (e633.location_number = s21.location_number)
              WHEN MATCHED THEN UPDATE
                SET e633.lfs_sf_status_flag = 'U'
                    ,e633.lfs_sf_status_message = NULL
                    ,e633.last_update_date = sysdate
					,e633.lfs_owner_employee_number = NULL
          ,e633.account_number = s21.account_number;
            dbms_output.put_line(SQL%rowcount||' records updated for LFS converted inactive customers');
			canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records updated for LFS converted inactive customers.',NULL,NULL,NULL,NULL);

               --if any changes on the PPS inactive accounts(for converted accounts) --QC52188
			  MERGE INTO canon_e633_cust_site_stg_tbl e633
				USING ( SELECT plw.loc_num location_number
                       ,dac.sell_to_cust_cd account_number
						FROM sell_to_cust dac
							 ,pty_loc_wrk plw
							 ,canon_e633_cust_site_stg_tbl e633
						WHERE e633.is_deleted = 'Y'
						  AND e633.location_number = plw.loc_num
						  AND plw.cmpy_pk = dac.cmpy_pk
							  AND plw.ezcancelflag = '0'
							  AND plw.glbl_cmpy_cd = 'ADB'
							  AND dac.ezcancelflag = plw.ezcancelflag
							  AND dac.glbl_cmpy_cd = plw.glbl_cmpy_cd
							  AND (plw.rgtn_sts_cd = 'P99' OR dac.rgtn_sts_cd = 'P99')
							  AND pps_sf_account_id IS NOT NULL
							  AND NVL(pps_owner_employee_number,'NA') <> 'CANON'
                    UNION
                        SELECT plw.loc_num location_number
                               ,dac.ds_acct_num account_number
                        FROM ds_acct_pros dac
                             ,pros_pty_loc_wrk plw
                             ,canon_e633_cust_site_stg_tbl e633
                        WHERE e633.is_deleted = 'Y'
                          AND e633.location_number = plw.loc_num
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND dac.glbl_cmpy_cd = plw.glbl_cmpy_cd
                          AND dac.ezcancelflag = plw.ezcancelflag
                          AND (plw.rgtn_sts_cd = 'P99' OR dac.rgtn_sts_cd = 'P99')
						  AND plw.loc_num = dac.loc_num --QC51955
                          AND pps_sf_account_id IS NOT NULL
                          AND NVL(pps_owner_employee_number,'NA') <> 'CANON'
                          )s21
              ON (e633.location_number = s21.location_number)
              WHEN MATCHED THEN UPDATE
                SET e633.pps_sf_status_flag = 'U'
                    ,e633.pps_sf_status_message = NULL
                    ,e633.pps_owner_employee_number = NULL
                    ,e633.last_update_date = SYSDATE
                    ,e633.account_number = s21.account_number;
            dbms_output.put_line(SQL%rowcount||' records updated for PPS converted inactive customers');
			canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records updated for PPS converted inactive customers.',NULL,NULL,NULL,NULL);


			 --for inactive accounts/locations
            MERGE INTO canon_e633_cust_site_stg_tbl e633
            USING (SELECT plw.loc_num location_number
                          ,dac.sell_to_cust_cd account_number
                     FROM sell_to_cust dac
                          ,pty_loc_wrk plw
                        --  ,acct_loc al
                          ,canon_e633_cust_site_stg_tbl e633
                    WHERE NVL(e633.is_deleted,'N') = 'N' --Feb132019
                      AND e633.location_number = plw.loc_num
                     -- AND plw.loc_num = al.loc_num --qc52188
					  --QC50716--start
					 -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
					 -- AND al.rgtn_sts_cd <> 'P99' -- qc52188
					  --QC#50716--end
                     -- AND al.ds_acct_num = dac.sell_to_cust_cd
					  AND plw.cmpy_pk = dac.cmpy_pk
                      AND plw.ezcancelflag = '0'
                      AND plw.glbl_cmpy_cd = 'ADB'
                    --  AND al.glbl_cmpy_cd = plw.glbl_cmpy_cd
                     -- AND al.ezcancelflag = plw.ezcancelflag --QC52188
                      AND dac.ezcancelflag = plw.ezcancelflag
                      AND dac.glbl_cmpy_cd = plw.glbl_cmpy_cd
                      AND (plw.rgtn_sts_cd = 'P99' OR dac.rgtn_sts_cd = 'P99')
                      UNION
                      SELECT plw.loc_num location_number
                             ,dac.ds_acct_num account_number
                        FROM ds_acct_pros dac
                             ,pros_pty_loc_wrk plw
                             ,canon_e633_cust_site_stg_tbl e633
                        WHERE NVL(e633.is_deleted,'N') = 'N' --Feb132019
                          AND e633.location_number = plw.loc_num
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND dac.glbl_cmpy_cd = plw.glbl_cmpy_cd
                          AND dac.ezcancelflag = plw.ezcancelflag
						  AND plw.loc_num = dac.loc_num --QC51955
                          AND (plw.rgtn_sts_cd = 'P99' OR dac.rgtn_sts_cd = 'P99')
                      ) s21
              ON(e633.location_number = s21.location_number)
              WHEN MATCHED THEN UPDATE
                SET is_deleted = 'Y'
                    ,e633.lfs_sf_status_flag = (CASE WHEN e633.lfs_sf_account_id IS NULL AND (LFSBU IS NOT NULL OR SUPBU IS NOT NULL) THEN 'I'  --QC52188
                                                     WHEN e633.lfs_sf_account_id IS NOT NULL THEN 'U'
                                                 END)
                    ,e633.lfs_sf_status_message = NULL
                    ,e633.pps_sf_status_flag = (CASE WHEN e633.pps_sf_account_id IS NULL AND ppsbu IS NOT NULL THEN 'I'  --QC52188
                                                     WHEN e633.pps_sf_account_id IS NOT NULL THEN 'U'
                                                 END)
                    ,e633.pps_sf_status_message = NULL
                    ,e633.last_update_date = SYSDATE
                    ,e633.account_number = s21.account_number;
            dbms_output.put_line(SQL%ROWCOUNT||' records updated for inactive customers');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for inactive customers.',NULL,NULL,NULL,NULL);

                --for any LFS accounts which no longer have territory assignments
            MERGE INTO canon_e633_cust_site_stg_tbl e633
            USING (SELECT plw.loc_num location_number
                          ,dac.sell_to_cust_cd account_number
                     FROM pty_loc_wrk plw
                          ,canon_e633_cust_site_stg_tbl e633
                          ,sell_to_cust dac
                          ,acct_loc al
                    WHERE e633.location_number = plw.loc_num
                      AND plw.ezcancelflag = '0'
                      AND plw.glbl_cmpy_cd = 'ADB'
                      AND dac.ezcancelflag = '0'
                      AND dac.glbl_cmpy_cd = 'ADB'
                      AND al.glbl_cmpy_cd = dac.glbl_cmpy_cd
                      AND al.ezcancelflag = dac.ezcancelflag
                      AND plw.loc_num = al.loc_num
                      and al.ds_acct_num = dac.sell_to_cust_cd
                      AND plw.rgtn_sts_cd <> 'P99'
                      AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                      AND al.rgtn_sts_cd <> 'P99'
                      AND NOT EXISTS (SELECT 1
                                        FROM acct_trty_role_asg atra
                                             ,trty_grp_tp tgt
                                        WHERE atra.loc_num = plw.loc_num
                                          AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                                         -- AND atra.line_biz_role_tp_cd IS NOT NULL
										  AND atra.psn_cd IS NOT NULL --QC54017
                                          --AND CAST(TO_TIMESTAMP (atra.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date --Feb132019
                                          AND tgt.line_biz_tp_cd = 'LFS')
                      UNION
                      SELECT plw.loc_num location_number
                             ,dac.ds_acct_num account_number
                        FROM pros_pty_loc_wrk plw
                             ,canon_e633_cust_site_stg_tbl e633
                             ,ds_acct_pros dac
                        WHERE e633.location_number = plw.loc_num
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND dac.glbl_cmpy_cd = plw.glbl_cmpy_cd
                          AND dac.ezcancelflag = plw.ezcancelflag
                          AND dac.loc_num = plw.loc_num
                          AND plw.rgtn_sts_cd <> 'P99'
                          AND NOT EXISTS (SELECT 1
                                            FROM pros_trty_role_asg atra
                                                 ,trty_grp_tp tgt
                                            WHERE atra.loc_num = plw.loc_num
                                              AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                                              --AND atra.line_biz_role_tp_cd IS NOT NULL
											  AND atra.psn_cd IS NOT NULL --QC54017
                                              --AND CAST(TO_TIMESTAMP (atra.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                                              AND tgt.line_biz_tp_cd = 'LFS')
                      ) s21
              ON(e633.location_number = s21.location_number)
              WHEN MATCHED THEN UPDATE
                SET e633.lfs_sf_status_flag = (CASE WHEN e633.lfs_sf_account_id IS NULL AND (LFSBU IS NOT NULL OR SUPBU IS NOT NULL)THEN 'I' --QC52188
                                                     WHEN e633.lfs_sf_account_id IS NOT NULL THEN 'U'
                                                 END)
                    ,e633.lfs_sf_status_message = NULL --QC52188
                    ,e633.lfs_owner_employee_number = NULL --QC52188
                    ,e633.lfs_account_owner_id = NULL
                    ,e633.last_update_date = sysdate
                    ,e633.account_number = s21.account_number;
                dbms_output.put_line(SQL%ROWCOUNT||' LFS records updated for customers with no territory assignments');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' LFS records updated for customers with no territory assignments.',NULL,NULL,NULL,NULL);

            -- for any PPS accounts which no longer have territory assignments
              MERGE INTO canon_e633_cust_site_stg_tbl e633
            USING (SELECT plw.loc_num location_number
                          ,dac.sell_to_cust_cd account_number
                     FROM pty_loc_wrk plw
                          ,canon_e633_cust_site_stg_tbl e633
                          ,sell_to_cust dac
                          ,acct_loc al
                    WHERE e633.location_number = plw.loc_num
                      AND plw.ezcancelflag = '0'
                      AND plw.glbl_cmpy_cd = 'ADB'
                      AND plw.rgtn_sts_cd <> 'P99'
                      AND plw.ezcancelflag = al.ezcancelflag
                      AND plw.glbl_cmpy_cd = al.glbl_cmpy_cd
                      AND plw.loc_num = al.loc_num
                      AND dac.ezcancelflag = al.ezcancelflag
                      AND dac.glbl_cmpy_cd = al.glbl_cmpy_cd
                      AND al.ds_acct_num = dac.sell_to_cust_cd
                      AND trunc(SYSDATE) BETWEEN trunc(nvl(to_date(al.eff_from_dt,'yyyymmdd'),SYSDATE)) AND trunc(nvl(to_date(al.eff_thru_dt, 'yyyymmdd'),SYSDATE))
                      AND al.rgtn_sts_cd <> 'P99'
                      AND NOT EXISTS (SELECT 1
                                        FROM acct_trty_role_asg atra
                                             ,trty_grp_tp tgt
                                        WHERE atra.loc_num = plw.loc_num
                                          AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                                          --AND atra.line_biz_role_tp_cd IS NOT NULL
										  AND atra.psn_cd IS NOT NULL --QC54017
                                         -- AND CAST(TO_TIMESTAMP (atra.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                                          AND tgt.line_biz_tp_cd = 'PPS')
                      UNION
                      SELECT plw.loc_num location_number
                             ,dac.ds_acct_num account_number
                        FROM pros_pty_loc_wrk plw
                             ,canon_e633_cust_site_stg_tbl e633
                             ,ds_acct_pros dac
                        WHERE e633.location_number = plw.loc_num
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND plw.rgtn_sts_cd <> 'P99'
                          AND dac.glbl_cmpy_cd = plw.glbl_cmpy_cd
                          AND dac.ezcancelflag = plw.ezcancelflag
                          AND dac.loc_num = plw.loc_num
                          AND NOT EXISTS (SELECT 1
                                            FROM pros_trty_role_asg atra
                                                 ,trty_grp_tp tgt
                                            WHERE atra.loc_num = plw.loc_num
                                              AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                                              --AND atra.line_biz_role_tp_cd IS NOT NULL
											  AND atra.psn_cd IS NOT NULL --QC54017
                                             -- AND CAST(TO_TIMESTAMP (atra.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                                              AND tgt.line_biz_tp_cd = 'PPS')
                      ) s21
              ON(e633.location_number = s21.location_number)
              WHEN MATCHED THEN UPDATE
                SET e633.pps_sf_status_flag = (CASE WHEN e633.pps_sf_account_id IS NULL AND PPSBU IS NOT NULL THEN 'I' --QC52188
                                                     WHEN e633.pps_sf_account_id IS NOT NULL THEN 'U'
                                                 END)
                    ,e633.pps_sf_status_message = NULL --QC52188
                    ,e633.pps_owner_employee_number = NULL
                    ,e633.pps_account_owner_id = NULL
                    ,e633.last_update_date = SYSDATE
                    ,e633.account_number = s21.account_number
                   ;
				   dbms_output.put_line(SQL%ROWCOUNT||' PPS records updated for customers with no territory assignments');
				   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' PPS records updated for customers with no territory assignments.',NULL,NULL,NULL,NULL);



--LFS accounts
                MERGE INTO canon_e633_cust_site_stg_tbl e633
                   using (   --customers
                            (SELECT distinct dac.sell_to_cust_cd account_number -- DB Changes
                               ,dac.ds_acct_nm account_name
                               ,dat.ds_acct_tp_nm account_type
                               ,plw.loc_num location_number
                               ,plw.first_line_addr || ' ' || plw.scd_line_addr || ' ' || plw.third_line_addr || ' ' || plw.frth_line_addr street
                               ,plw.cty_addr town
                               ,c1.cnty_nm county
                               ,CASE WHEN c2.ctry_nm = 'UNITED STATES OF AMERICA' THEN 'US' END country
                               ,plw.post_cd postal_code
                               ,plw.fax_num fax
                               ,plw.tel_num phone
                               ,plw.st_cd state
                               ,dac.dba_nm
                               ,NULL credit_stop
                               ,(CASE WHEN (dac.rgtn_sts_cd = 'P99' or plw.rgtn_sts_cd = 'P99') THEN 'Y' ELSE 'N' END) is_deleted
                               ,tgt.line_biz_tp_cd business_unit -- LOB changes
                               ,plw.duns_num duns_number
                               ,plw.ds_ult_duns_num ult_duns_num -- DB Changes
                               --,plw.indy_tp_cd sic_code -- DB Changes
                               --,it.indy_tp_nm sic_code_desc
							   ,plw.ds_cust_sic_cd sic_code --QC53489
                               ,plw.DS_CUST_SIC_DESC_TXT sic_code_desc --QC53489
                               ,plw.hq_duns_num duns_hq_num -- DB Changes
                               ,dxa.ds_xref_acct_cd legacy_ref_id
                         FROM sell_to_cust dac -- DB Changes
                              ,ds_acct_tp dat
                              ,pty_loc_wrk plw
--                              ,DS_PTY_LOC_WRK dplw
                              ,acct_loc al
                              ,cnty c1
                              ,ctry c2
                              ,indy_tp it
                              ,ds_xref_acct dxa
                              ,ds_xref_acct_tp dxat
                              ,acct_trty_role_asg atra
                              ,trty_grp_tp tgt
                        WHERE 1 = 1
                          AND dac.glbl_cmpy_cd = 'ADB'
                          AND dac.ezcancelflag = '0'
                          AND al.glbl_cmpy_cd = 'ADB'
                          AND al.ezcancelflag = '0'
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND dat.glbl_cmpy_cd = 'ADB'
                          AND dat.ezcancelflag = '0'
                          AND c1.glbl_cmpy_cd(+) = 'ADB'
                          AND c1.ezcancelflag(+) = '0'
                          AND c2.glbl_cmpy_cd(+) = 'ADB'
                          AND c2.ezcancelflag(+) = '0'
                          AND it.glbl_cmpy_cd(+) = 'ADB'
                          AND it.ezcancelflag(+) = '0'
                          AND dxa.ezcancelflag(+) = '0'
                          AND dxa.glbl_cmpy_cd(+) = 'ADB'
                          AND dxat.ezcancelflag = '0'
                          AND dxat.glbl_cmpy_cd = 'ADB'
                          AND atra.glbl_cmpy_cd = dac.glbl_cmpy_cd
                          AND atra.ezcancelflag = dac.ezcancelflag
                          AND dac.sell_to_cust_cd = al.ds_acct_num -- DB Changes
                          AND al.loc_num = plw.loc_num
                          AND dac.ds_acct_tp_cd = dat.ds_acct_tp_cd
                          AND atra.loc_num = plw.loc_num
                          AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                          and tgt.line_biz_tp_cd in ('LFS')
                          and atra.line_biz_role_tp_cd is not null
                          AND c2.ctry_cd(+) = plw.ctry_cd
                          AND c1.st_cd(+) = plw.st_cd
                          AND c1.cnty_pk(+) = plw.cnty_pk
                          AND it.indy_tp_cd(+) = plw.indy_tp_cd -- DB Changes
                          AND dxa.loc_num(+) = plw.loc_num
                          AND dxa.ds_xref_acct_tp_cd(+) = dxat.ds_xref_acct_tp_cd
                          AND dxat.ds_xref_acct_tp_nm = 'SAP'
                          --AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dac.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dac.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
						  --QC50716--start
                          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
						  AND al.rgtn_sts_cd <> 'P99'
						  --QC50716--end
                         -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
						  --AND (to_number(dac.ezuptime) > to_number(l_last_run_date_num) OR
              AND (CAST(to_timestamp (dac.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                   CAST(to_timestamp (plw.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                   CAST(TO_TIMESTAMP (atra.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date)
--                               to_number(plw.ezuptime) > to_number(l_last_run_date_num)OR
--                               to_number(atra.ezuptime) > to_number(l_last_run_date_num))
						  )
                            --prospects
                            UNION
                            (SELECT distinct dac.ds_acct_num account_number
                                   ,dac.ds_acct_nm account_name
                                   ,dat.ds_acct_tp_nm account_type
                                   ,plw.loc_num location_number
                                   ,plw.first_line_addr || ' ' || plw.scd_line_addr || ' ' || plw.third_line_addr || ' ' || plw.frth_line_addr street
                                   ,plw.cty_addr town
                                   ,c1.cnty_nm county
                                   ,CASE WHEN c2.ctry_nm = 'UNITED STATES OF AMERICA' THEN 'US' END country
                                   ,plw.post_cd postal_code
                                   ,plw.fax_num fax
                                   ,plw.tel_num phone
                                   ,plw.st_cd state
                                   ,dac.dba_nm
                                   ,NULL credit_stop

                       --  ,c2.ctry_nm

--                               ,DECODE(c2.ctry_nm,'UNITED STATES OF AMERICA','US',c2.ctry_nm) country
                          --  ,NULL Billing_postal_code
                                   ,(CASE WHEN (dac.rgtn_sts_cd = 'P99' or plw.rgtn_sts_cd = 'P99') THEN 'Y' ELSE 'N' END) is_deleted
                                   ,tgt.line_biz_tp_cd business_unit -- LOB change
                                   ,plw.duns_num duns_number
                                   ,plw.ds_ult_duns_num ult_duns_num -- DB Change
                                   --,plw.indy_tp_cd sic_code -- DB Change
                                  -- ,it.indy_tp_nm sic_code_desc
								   ,plw.ds_cust_sic_cd sic_code --QC53489
                                  ,plw.DS_CUST_SIC_DESC_TXT sic_code_desc --QC53489
                                   ,plw.hq_duns_num duns_hq_num -- DB Change
                                   ,dxa.ds_xref_acct_cd legacy_ref_id
                         FROM ds_acct_pros dac
                              ,ds_acct_tp dat
                              ,pros_pty_loc_wrk plw  -- new table change
                              ,cnty c1
                              ,ctry c2
                              ,indy_tp it
                              ,ds_xref_acct dxa
                              ,ds_xref_acct_tp dxat
                              ,pros_trty_role_asg atra
                              ,trty_grp_tp tgt
                        WHERE 1 = 1
                          AND dac.glbl_cmpy_cd = 'ADB'
                          AND dac.ezcancelflag = '0'
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND dat.glbl_cmpy_cd = 'ADB'
                          AND dat.ezcancelflag = '0'
                          AND c1.glbl_cmpy_cd(+) = 'ADB'
                          AND c1.ezcancelflag(+) = '0'
                          AND c2.glbl_cmpy_cd(+) = 'ADB'
                          AND c2.ezcancelflag(+) = '0'
                          AND it.glbl_cmpy_cd(+) = 'ADB'
                          AND it.ezcancelflag(+) = '0'
                          AND dxa.ezcancelflag = '0'
                          AND dxa.glbl_cmpy_cd = 'ADB'
                          AND dxat.ezcancelflag = '0'
                          AND dxat.glbl_cmpy_cd = 'ADB'
                          AND atra.glbl_cmpy_cd = dac.glbl_cmpy_cd
                          AND atra.ezcancelflag = dac.ezcancelflag
                          AND tgt.glbl_cmpy_cd = dac.glbl_cmpy_cd
                          AND tgt.ezcancelflag = dac.ezcancelflag
                          AND dac.loc_num = plw.loc_num
                          AND dac.ds_acct_tp_cd = dat.ds_acct_tp_cd
                          AND atra.loc_num = plw.loc_num
                          AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                          and tgt.line_biz_tp_cd in ('LFS')
                          and atra.line_biz_role_tp_cd is not null
                          AND c2.ctry_cd(+) = plw.ctry_cd
                          AND c1.st_cd(+) = plw.st_cd
                          AND c1.cnty_pk(+) = plw.cnty_pk
                          AND it.indy_tp_cd(+) = plw.indy_tp_cd -- DB Change
                          AND dxa.loc_num = plw.loc_num
                          AND dxa.ds_xref_acct_tp_cd = dxat.ds_xref_acct_tp_cd
                          AND dxat.ds_xref_acct_tp_nm IN ('SAP','SFDC')

                         -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dac.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dac.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                          --AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                         -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
--						  AND (TO_NUMBER(DAC.EZUPTIME) > TO_NUMBER(L_LAST_RUN_DATE_NUM) OR
--                               TO_NUMBER(plw.ezuptime) > TO_NUMBER(l_last_run_date_num)OR
--                               to_number(atra.ezuptime) > to_number(l_last_run_date_num))
                          AND (CAST(to_timestamp (dac.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                               CAST(to_timestamp (plw.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                               CAST(TO_TIMESTAMP (atra.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date)
						  ))s21
               ON (s21.location_number = e633.location_number)
             WHEN MATCHED THEN UPDATE SET
                    e633.legacy_ref_id = s21.legacy_ref_id
                    ,e633.account_name = s21.account_name
                    ,e633.account_number = s21.account_number --QC30303
             	    ,e633.ACCOUNT_TYPE = s21.ACCOUNT_TYPE
						  ,e633.ADDRESS = s21.street
						  ,e633.TOWN = s21.town
						  ,e633.COUNTY = s21.county
						  ,e633.country = s21.country
						  ,e633.postal_code = s21.postal_code
						  ,e633.FAX = s21.fax
						  ,e633.TELEPHONE = s21.phone
						  ,e633.CREDIT_STOP = s21.credit_stop
						  ,e633.GEO_AREA = s21.state
						  ,e633.IS_DELETED = s21.IS_DELETED
						  ,e633.DOES_BUSINESS_AS_DBA = s21.dba_nm
						  ,e633.lfsbu = CASE WHEN s21.business_unit = 'LFS' THEN 'LFS' ELSE NULL END
						 -- ,e633.PPSBU = CASE WHEN s21.business_unit = 'PPS' THEN 'PPS' ELSE NULL END
						  ,e633.DUNS_NUMBER = s21.duns_number
						  ,e633.DUNS_HQ_NUM = s21.duns_hq_num
						  ,e633.ULT_DUNS = s21.ult_duns_num
						  ,e633.SIC_CODE = s21.sic_code
						  ,e633.SIC_CODE_DESCRIPTION = s21.sic_code_desc
						  ,e633.last_update_date = SYSDATE

						  ,e633.lfs_sf_status_flag = (CASE WHEN e633.lfs_sf_account_id IS NULL AND (s21.business_unit = 'LFS') THEN 'I'
                                                          WHEN e633.lfs_sf_account_id IS NOT NULL AND (s21.business_unit = 'LFS') THEN 'U'
                                                     ELSE e633.lfs_sf_status_flag
                                                      END
                                                    )
						  ,e633.lfs_sf_status_message = (CASE WHEN e633.lfs_sf_account_id IS NULL AND (s21.business_unit = 'LFS') THEN NULL
                                                          WHEN e633.lfs_sf_account_id IS NOT NULL AND (s21.business_unit = 'LFS') THEN NULL
                                                     ELSE e633.lfs_sf_status_message
                                                      END
                                                    )
				WHEN NOT MATCHED THEN
				INSERT (LEGACY_REF_ID
						  ,ACCOUNT_NAME
						  ,ACCOUNT_TYPE
						  ,LOCATION_NUMBER
						  ,ADDRESS
						  ,TOWN
						  ,COUNTY
						  ,COUNTRY
						  ,POSTAL_CODE
						  ,FAX
						  ,TELEPHONE
						  ,CREDIT_STOP
						  ,GEO_AREA
						  ,IS_DELETED
						  ,DOES_BUSINESS_AS_DBA
						  ,lfsbu
						 -- ,PPSBU
						  ,DUNS_NUMBER
						  ,ULT_DUNS
						  ,DUNS_HQ_NUM
						  ,SIC_CODE
						  ,SIC_CODE_DESCRIPTION
						  ,last_update_date
						  ,lfs_sf_status_flag

                           ,ACCOUNT_NUMBER
						  ) VALUES (s21.legacy_ref_id
                                                      ,s21.Account_name
                                                      ,s21.ACCOUNT_TYPE
                                                      ,s21.LOCATION_NUMBER
                                                      ,s21.STREET
                                                      ,s21.TOWN
                                                      ,s21.county
                                                      ,s21.COUNTRY
                                                      ,s21.POSTAL_CODE
                                                      ,s21.FAX
                                                      ,s21.phone
                                                      ,s21.CREDIT_STOP
                                                      ,s21.state
                                                      ,s21.IS_DELETED
                                                      ,s21.dba_nm
                                                      ,CASE WHEN s21.business_unit = 'LFS' THEN 'LFS' ELSE NULL END
                                                     -- ,CASE WHEN s21.business_unit = 'PPS' THEN 'PPS' ELSE NULL END
                                                      ,s21.duns_number
                                                      ,s21.duns_hq_num
                                                      ,s21.ult_duns_num
                                                      ,s21.sic_code
                                                      ,s21.sic_code_desc
                                                      ,SYSDATE
                                                      ,(CASE WHEN s21.business_unit = 'LFS' THEN 'I' ELSE NULL END)
                                                      ,s21.account_number); --QC30303
                                                     -- ,(CASE WHEN S21.business_unit = 'PPS' THEN 'I' ELSE NULL END));

            dbms_output.put_line(SQL%ROWCOUNT||' LFS records from the S21 merged with staging table CANON_E633_CUST_SITE_STG_TBL.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records from the S21 merged with staging table CANON_E633_CUST_SITE_STG_TBL.',NULL,NULL,NULL,NULL);
            COMMIT;

            --- PPS accounts
             MERGE INTO canon_e633_cust_site_stg_tbl e633
                   USING (   --customers
                            (SELECT distinct dac.sell_to_cust_cd account_number -- DB Changes
                               ,dac.ds_acct_nm account_name
                               ,dat.ds_acct_tp_nm account_type
                               ,plw.loc_num location_number
                               ,plw.first_line_addr || ' ' || plw.scd_line_addr || ' ' || plw.third_line_addr || ' ' || plw.frth_line_addr street
                               ,plw.cty_addr town
                               ,c1.cnty_nm county
                               ,CASE WHEN c2.ctry_nm = 'UNITED STATES OF AMERICA' THEN 'US' END country
                               ,plw.post_cd postal_code
                               ,plw.fax_num fax
                               ,plw.tel_num phone
                               ,plw.st_cd state
                               ,dac.dba_nm
                               ,NULL credit_stop
                               ,(CASE WHEN (dac.rgtn_sts_cd = 'P99' or plw.rgtn_sts_cd = 'P99') THEN 'Y' ELSE 'N' END) is_deleted
--                               ,plw.line_biz_tp_cd business_unit -- DB Changes
                               ,tgt.line_biz_tp_cd business_unit -- LOB changes
                               ,plw.duns_num duns_number
                               ,plw.ds_ult_duns_num ult_duns_num -- DB Changes
                              -- ,plw.indy_tp_cd sic_code -- DB Changes
                              -- ,it.indy_tp_nm sic_code_desc
							   ,plw.ds_cust_sic_cd sic_code --QC53489
                               ,plw.DS_CUST_SIC_DESC_TXT sic_code_desc --QC53489
                               ,plw.hq_duns_num duns_hq_num -- DB Changes
                               ,dxa.ds_xref_acct_cd legacy_ref_id
                         FROM sell_to_cust dac -- DB Changes
                              ,ds_acct_tp dat
                              ,pty_loc_wrk plw
--                             ,DS_PTY_LOC_WRK dplw
                              ,acct_loc al
                              ,cnty c1
                              ,ctry c2
                              ,indy_tp it
                              ,ds_xref_acct dxa
                              ,ds_xref_acct_tp dxat
                              ,acct_trty_role_asg atra
                              ,trty_grp_tp tgt
                        WHERE 1 = 1
                          AND dac.glbl_cmpy_cd = 'ADB'
                          AND dac.ezcancelflag = '0'
                          AND al.glbl_cmpy_cd = 'ADB'
                          AND al.ezcancelflag = '0'
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND dat.glbl_cmpy_cd = 'ADB'
                          AND dat.ezcancelflag = '0'
                          AND c1.glbl_cmpy_cd(+) = 'ADB'
                          AND c1.ezcancelflag(+) = '0'
                          AND c2.glbl_cmpy_cd(+) = 'ADB'
                          AND c2.ezcancelflag(+) = '0'
                          AND it.glbl_cmpy_cd(+) = 'ADB'
                          AND it.ezcancelflag(+) = '0'
                          AND dxa.ezcancelflag(+) = '0'
                          AND dxa.glbl_cmpy_cd(+) = 'ADB'
                          AND dxat.ezcancelflag = '0'
                          AND dxat.glbl_cmpy_cd = 'ADB'
                          AND atra.glbl_cmpy_cd = dac.glbl_cmpy_cd
                          AND atra.ezcancelflag = dac.ezcancelflag
                          AND dac.sell_to_cust_cd = al.ds_acct_num -- DB Changes
                          AND al.loc_num = plw.loc_num
                          AND dac.ds_acct_tp_cd = dat.ds_acct_tp_cd
                          AND atra.loc_num = plw.loc_num
                          AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                          and tgt.line_biz_tp_cd in ('PPS')
                          and atra.line_biz_role_tp_cd is not null
                          AND c2.ctry_cd(+) = plw.ctry_cd
                          AND c1.st_cd(+) = plw.st_cd
                          AND c1.cnty_pk(+) = plw.cnty_pk
                          AND it.indy_tp_cd(+) = plw.indy_tp_cd -- DB Changes
                          AND dxa.loc_num(+) = plw.loc_num
                          AND dxa.ds_xref_acct_tp_cd(+) = dxat.ds_xref_acct_tp_cd
                          AND dxat.ds_xref_acct_tp_nm = 'SAP'
                         -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dac.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dac.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
						 --QC50716--start
                          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
						  AND al.rgtn_sts_cd <> 'P99'
						  --QC50716--end
                         -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
--						  AND (to_number(dac.ezuptime) > to_number(l_last_run_date_num) OR
--                               to_number(plw.ezuptime) > to_number(l_last_run_date_num)OR
--                               to_number(atra.ezuptime) > to_number(l_last_run_date_num))
                          AND (CAST(to_timestamp (dac.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                                CAST(to_timestamp (plw.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                                CAST(TO_TIMESTAMP (atra.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date)
						  )
                            --prospects
                            UNION
                            (SELECT distinct dac.ds_acct_num account_number
                                   ,dac.ds_acct_nm account_name
                                   ,dat.ds_acct_tp_nm account_type
                                   ,plw.loc_num location_number
                                   ,plw.first_line_addr || ' ' || plw.scd_line_addr || ' ' || plw.third_line_addr || ' ' || plw.frth_line_addr street
                                   ,plw.cty_addr town
                                   ,c1.cnty_nm county
                                   ,CASE WHEN c2.ctry_nm = 'UNITED STATES OF AMERICA' THEN 'US' END country
                                   ,plw.post_cd postal_code
                                   ,plw.fax_num fax
                                   ,plw.tel_num phone
                                   ,plw.st_cd state
                                   ,dac.dba_nm
                                   ,NULL credit_stop
                                   ,(CASE WHEN (dac.rgtn_sts_cd = 'P99' or plw.rgtn_sts_cd = 'P99') THEN 'Y' ELSE 'N' END) is_deleted
--                                   ,plw.line_biz_tp_cd business_unit -- DB Change
                                   ,tgt.line_biz_tp_cd business_unit -- LOB change
                                   ,plw.duns_num duns_number
                                   ,plw.ds_ult_duns_num ult_duns_num -- DB Change
                                  -- ,plw.indy_tp_cd sic_code -- DB Change
                                  -- ,it.indy_tp_nm sic_code_desc
								   ,plw.ds_cust_sic_cd sic_code --QC53489
                                  ,plw.DS_CUST_SIC_DESC_TXT sic_code_desc --QC53489
                                   ,plw.hq_duns_num duns_hq_num -- DB Change
                                   ,dxa.ds_xref_acct_cd legacy_ref_id
                         FROM ds_acct_pros dac
                              ,ds_acct_tp dat
                              ,pros_pty_loc_wrk plw  -- new table change
                              ,cnty c1
                              ,ctry c2
                              ,indy_tp it
                              ,ds_xref_acct dxa
                              ,ds_xref_acct_tp dxat
                              ,pros_trty_role_asg atra
                              ,trty_grp_tp tgt
                        WHERE 1 = 1
                          AND dac.glbl_cmpy_cd = 'ADB'
                          AND dac.ezcancelflag = '0'
                          AND plw.glbl_cmpy_cd = 'ADB'
                          AND plw.ezcancelflag = '0'
                          AND dat.glbl_cmpy_cd = 'ADB'
                          AND dat.ezcancelflag = '0'
                          AND c1.glbl_cmpy_cd(+) = 'ADB'
                          AND c1.ezcancelflag(+) = '0'
                          AND c2.glbl_cmpy_cd(+) = 'ADB'
                          AND c2.ezcancelflag(+) = '0'
                          AND it.glbl_cmpy_cd(+) = 'ADB'
                          AND it.ezcancelflag(+) = '0'
                          AND dxa.ezcancelflag = '0'
                          AND dxa.glbl_cmpy_cd = 'ADB'
                          AND dxat.ezcancelflag = '0'
                          AND dxat.glbl_cmpy_cd = 'ADB'
                          AND atra.glbl_cmpy_cd = dac.glbl_cmpy_cd
                          AND atra.ezcancelflag = dac.ezcancelflag
                          AND tgt.glbl_cmpy_cd = dac.glbl_cmpy_cd
                          AND tgt.ezcancelflag = dac.ezcancelflag
                          AND dac.loc_num = plw.loc_num
                          AND dac.ds_acct_tp_cd = dat.ds_acct_tp_cd
                          AND atra.loc_num = plw.loc_num
                          AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
                          and tgt.line_biz_tp_cd in ('PPS')
                          and atra.line_biz_role_tp_cd is not null
                          AND c2.ctry_cd(+) = plw.ctry_cd
                          AND c1.st_cd(+) = plw.st_cd
                          AND c1.cnty_pk(+) = plw.cnty_pk
                          AND it.indy_tp_cd(+) = plw.indy_tp_cd -- DB Change
                          AND dxa.loc_num = plw.loc_num
                          AND dxa.ds_xref_acct_tp_cd = dxat.ds_xref_acct_tp_cd
                          AND dxat.ds_xref_acct_tp_nm IN ('SAP','SFDC')
                         -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dac.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dac.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                          --AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                         -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(plw.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(plw.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
--						  AND (TO_NUMBER(DAC.EZUPTIME) > TO_NUMBER(L_LAST_RUN_DATE_NUM) OR
--                               TO_NUMBER(plw.ezuptime) > TO_NUMBER(l_last_run_date_num) OR
--                               to_number(atra.ezuptime) > to_number(l_last_run_date_num))
                         AND (CAST(to_timestamp (dac.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                              CAST(to_timestamp (plw.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                              CAST(TO_TIMESTAMP (atra.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date)
						  ))s21
               ON (s21.location_number = e633.location_number)
             WHEN MATCHED THEN UPDATE SET
                    e633.legacy_ref_id = s21.legacy_ref_id
                    ,e633.account_name = s21.account_name
                    ,e633.account_number = s21.account_number --QC30303
             	    ,e633.ACCOUNT_TYPE = s21.ACCOUNT_TYPE
						  ,e633.ADDRESS = s21.street
						  ,e633.TOWN = s21.town
						  ,e633.COUNTY = s21.county
						  ,e633.country = s21.country
						  ,e633.postal_code = s21.postal_code
						  ,e633.FAX = s21.fax
						  ,e633.TELEPHONE = s21.phone
						  ,e633.CREDIT_STOP = s21.credit_stop
						  ,e633.GEO_AREA = s21.state
						  ,e633.IS_DELETED = s21.IS_DELETED
						  ,e633.does_business_as_dba = s21.dba_nm
						  --,e633.LFSBU = CASE WHEN s21.business_unit = 'LFS' THEN 'LFS' ELSE NULL END
						  ,e633.PPSBU = CASE WHEN s21.business_unit = 'PPS' THEN 'PPS' ELSE NULL END
						  ,e633.DUNS_NUMBER = s21.duns_number
						  ,e633.DUNS_HQ_NUM = s21.duns_hq_num
						  ,e633.ULT_DUNS = s21.ult_duns_num
						  ,e633.SIC_CODE = s21.sic_code
						  ,e633.SIC_CODE_DESCRIPTION = s21.sic_code_desc
						  ,e633.last_update_date = SYSDATE
						  ,e633.pps_sf_status_flag = (CASE WHEN e633.pps_sf_account_id IS NULL AND s21.business_unit = 'PPS' THEN 'I'
                                                          WHEN e633.pps_sf_account_id IS NOT NULL AND s21.business_unit = 'PPS' THEN 'U'
						                             ELSE e633.pps_sf_status_flag
						                              END
						                            )
						  ,e633.pps_sf_status_message = (CASE WHEN e633.pps_sf_account_id IS NULL AND s21.business_unit = 'PPS' THEN NULL
                                                          WHEN e633.pps_sf_account_id IS NOT NULL AND s21.business_unit = 'PPS' THEN NULL
						                             ELSE e633.pps_sf_status_message
						                              END
						                            )
				WHEN NOT MATCHED THEN
				INSERT (LEGACY_REF_ID
						  ,ACCOUNT_NAME
						  ,ACCOUNT_TYPE
						  ,LOCATION_NUMBER
						  ,ADDRESS
						  ,TOWN
						  ,COUNTY
						  ,COUNTRY
						  ,POSTAL_CODE
						  ,FAX
						  ,TELEPHONE
						  ,CREDIT_STOP
						  ,GEO_AREA
						  ,IS_DELETED
						  ,DOES_BUSINESS_AS_DBA
						 -- ,LFSBU
						  ,PPSBU
						  ,DUNS_NUMBER
						  ,ULT_DUNS
						  ,DUNS_HQ_NUM
						  ,SIC_CODE
						  ,SIC_CODE_DESCRIPTION
						  ,LAST_UPDATE_DATE
						--  ,LFS_SF_STATUS_FLAG
						  ,pps_sf_status_flag

              ,account_number) VALUES (s21.legacy_ref_id
                                                      ,s21.Account_name
                                                      ,s21.ACCOUNT_TYPE
                                                      ,s21.LOCATION_NUMBER
                                                      ,s21.STREET
                                                      ,s21.TOWN
                                                      ,s21.county
                                                      ,s21.COUNTRY
                                                      ,s21.POSTAL_CODE
                                                      ,s21.FAX
                                                      ,s21.phone
                                                      ,s21.CREDIT_STOP
                                                      ,s21.state
                                                      ,s21.IS_DELETED
                                                      ,s21.dba_nm
                                                   --   ,CASE WHEN s21.business_unit = 'LFS' THEN 'LFS' ELSE NULL END
                                                      ,CASE WHEN s21.business_unit = 'PPS' THEN 'PPS' ELSE NULL END
                                                      ,s21.duns_number
                                                      ,s21.duns_hq_num
                                                      ,s21.ult_duns_num
                                                      ,s21.sic_code
                                                      ,s21.sic_code_desc
                                                      ,SYSDATE
                                                    --  ,(CASE WHEN S21.business_unit = 'LFS' THEN 'I' ELSE NULL END)
                                                      ,(CASE WHEN s21.business_unit = 'PPS' THEN 'I' ELSE NULL END)
                                                      ,s21.account_number); --QC30303

            dbms_output.put_line(SQL%ROWCOUNT||' PPS records from the S21 merged with staging table CANON_E633_CUST_SITE_STG_TBL.');
			canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records from the S21 merged with staging table CANON_E633_CUST_SITE_STG_TBL.',NULL,NULL,NULL,NULL);
            COMMIT;


            --QC#55744 update Bill to address is Start
   Begin
MERGE INTO canon_e633_cust_site_stg_tbl e633
   using ( SELECT distinct -- DB Changes
                btc.first_line_addr || ' ' || btc.scd_line_addr || ' ' || btc.third_line_addr || ' ' || btc.frth_line_addr Billing_street --QC#55744
               ,btc.cty_addr Billing_town
               ,plw.loc_num location_number
               ,bill_c1.cnty_nm billing_county
               ,btc.st_cd Billing_state
               ,DECODE(bill_c2.ctry_nm,'UNITED STATES OF AMERICA','US',bill_c2.ctry_nm) bill_country
               ,btc.post_cd Billing_postalcode
         FROM  -- DB Changes
            s21_csa_apps.bill_to_cust btc
              ,s21_csa_apps.cnty bill_c1
              ,s21_csa_apps.ctry bill_c2
              ,s21_csa_apps.pty_loc_wrk plw
              ,s21_csa_extn.canon_e633_cust_site_stg_tbl e633
            WHERE 1 = 1
          AND bill_c1.glbl_cmpy_cd(+) = 'ADB'
          AND bill_c1.ezcancelflag(+) = '0'
          AND bill_c2.glbl_cmpy_cd(+) = 'ADB'
          AND bill_c2.ezcancelflag(+) = '0'
          and bill_c1.st_cd(+) = btc.st_cd
          AND bill_c1.cnty_pk(+) = btc.cnty_pk
          AND bill_c2.ctry_cd(+) = btc.ctry_cd
          AND btc.pty_loc_pk(+) = plw.pty_loc_pk
          AND plw.loc_num (+) = e633.location_number
          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(btc.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(btc.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
         AND (nvl(btc.st_cd,'null')<> nvl(e633.Billing_state,'null')
         or nvl(btc.cty_addr,'null') <> nvl(e633.Billing_town,'null')
         or nvl(btc.post_cd,'null')<> nvl(e633.Billing_postalcode,'null')
         or nvl(bill_c2.ctry_nm,'null')<> nvl(e633.BILL_COUNTRY,'null')
         or nvl(bill_c1.cnty_nm,'null')<>nvl(e633.BILLING_COUNTY,'null')
         or nvl(btc.first_line_addr || ' ' || btc.scd_line_addr || ' ' || btc.third_line_addr || ' ' || btc.frth_line_addr ,null)<>nvl(e633.Billing_street,'null'))
)s21
ON (s21.location_number = e633.location_number)
WHEN MATCHED THEN UPDATE SET
                  e633.Billing_street=s21.Billing_street
                 ,e633.Billing_town = s21.Billing_town
                 ,e633.BILL_COUNTRY=s21.BILL_COUNTRY
                 ,e633.Billing_state=s21.Billing_state
                 ,e633.BILLING_COUNTY=s21.BILLING_COUNTY
                 ,e633.Billing_postalcode =s21.Billing_postalcode
                 ,e633.lfs_sf_status_flag = (CASE WHEN e633.lfs_sf_account_id IS NULL AND (LFSBU IS NOT NULL OR SUPBU IS NOT NULL) THEN 'I'
                                                                          WHEN e633.lfs_sf_account_id IS NOT NULL THEN 'U'
                                                                 END)
                 ,e633.lfs_sf_status_message = NULL
                 ,e633.pps_sf_status_flag = (CASE WHEN e633.pps_sf_account_id IS NULL AND ppsbu IS NOT NULL THEN 'I'  --QC52188
                                                                                WHEN e633.pps_sf_account_id IS NOT NULL THEN 'U'
                                                                        END)
                 ,e633.pps_sf_status_message = NULL
                 ,e633.last_update_date = SYSDATE;
                dbms_output.put_line(SQL%rowcount||' Records updated for bill to address.');
                canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records updated for bill to address',NULL,NULL,NULL,NULL);
               COMMIT;
            exception WHEN others THEN
                dbms_output.put_line('Bill to address Update Exception: ' ||sqlerrm);
                canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR','Bill to address Update Exception : ',NULL,NULL,NULL,SQLERRM);

end;

--end QC#55744 update bill to address
--Start Update umberella ACCOUNT QC#54556

MERGE INTO s21_csa_extn.canon_e633_cust_site_stg_tbl e633
USING (
          SELECT DISTINCT
              e633.account_number,
              dag.ds_acct_grp_nm
          FROM
              s21_csa_apps.ds_acct_grp                    dag,
              s21_csa_apps.ds_acct_grp_asg                daga,
              s21_csa_extn.canon_e633_cust_site_stg_tbl   e633
          WHERE
              1 = 1
              AND daga.ds_acct_grp_cd = dag.ds_acct_grp_cd (+)
              AND daga.ds_acct_num (+) = e633.account_number
              AND daga.ezcancelflag (+) = '0'
              AND daga.glbl_cmpy_cd (+) = 'ADB'
              AND dag.ezcancelflag (+) = '0'
              AND dag.glbl_cmpy_cd (+) = 'ADB'
              AND daga.ds_acct_grp_cd (+) NOT IN (
                  2,
                  238
              )
              AND trunc(sysdate) BETWEEN trunc(nvl(to_date(daga.eff_from_dt(+), 'yyyymmdd'), sysdate)) AND trunc(nvl(to_date(daga
              .eff_thru_dt(+), 'yyyymmdd'), sysdate))
--                      AND nvl(e633.acct_grp_name, 'XYZ') <> nvl(dag.ds_acct_grp_nm, 'XYZ')
              AND ( ( dag.ds_acct_grp_nm IS NOT NULL
                      AND nvl(e633.acct_grp_name, 'NA') <> dag.ds_acct_grp_nm )
                    OR ( dag.ds_acct_grp_nm IS NULL
                         AND nvl(e633.acct_grp_name, 'NA') <> e633.account_name ) )--QC#56594
      )
s21 ON ( e633.account_number = s21.account_number)
WHEN MATCHED THEN UPDATE
SET e633.acct_grp_name = nvl(s21.ds_acct_grp_nm, e633.account_name),--QC#56594
    e633.lfs_sf_status_flag = (
    CASE
        WHEN e633.lfs_sf_account_id IS NULL
             AND ( lfsbu IS NOT NULL
                   OR supbu IS NOT NULL ) THEN
            'I'
        WHEN e633.lfs_sf_account_id IS NOT NULL THEN
            'U'
    END
),
    e633.lfs_sf_status_message = NULL,
    e633.pps_sf_status_flag = (
    CASE
        WHEN e633.pps_sf_account_id IS NULL
             AND ppsbu IS NOT NULL THEN
            'I'  --QC52188
        WHEN e633.pps_sf_account_id IS NOT NULL THEN
            'U'
    END
),
    e633.pps_sf_status_message = NULL,
    e633.last_update_date = sysdate;

        dbms_output.put_line(SQL%rowcount || ' Records updated for umberella account.');
        canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', SQL%rowcount || ' records updated for umberella account'
        , NULL,
                                              NULL, NULL, NULL);

        COMMIT;


--end Umberella Account Qc#54556



            --credit_stop location level
            MERGE INTO canon_e633_cust_site_stg_tbl stg
               USING (SELECT stc.loc_num location_number, dccp.cust_hard_hld_flg credit_stop
                        FROM cust_cr_prfl dccp -- DB Changes
                             ,ship_to_cust stc
                             ,bill_to_cust btc
--                             ,ds_ship_to_cust dstc -- DB Change
                          --   ,canon_e633_cust_site_stg_tbl e633
                        WHERE 1 = 1 --e633.location_number = stc.loc_num
--                         AND stc.ship_to_cust_pk = dstc.ship_to_cust_pk -- DB Change
                         AND stc.reln_bill_to_cust_cd = btc.bill_to_cust_cd -- DB Change
                         AND btc.bill_to_cust_pk = dccp.bill_to_cust_pk
                         -- AND e633.credit_stop IS NULL
                         AND dccp.ezcancelflag = '0'
                         AND dccp.glbl_cmpy_cd = 'ADB'
                         AND stc.ezcancelflag = '0'
                         AND stc.glbl_cmpy_cd = 'ADB'
                         AND btc.ezcancelflag = '0'
                         AND btc.glbl_cmpy_cd = 'ADB'
--                         AND dstc.ezcancelflag = '0'
--                         AND dstc.glbl_cmpy_cd = 'ADB'
                      )s21
                  ON(stg.location_number = s21.location_number)
                  WHEN MATCHED THEN UPDATE
                        SET stg.credit_stop = s21.credit_stop;
              dbms_output.put_line(SQL%ROWCOUNT||' records updated for credit_stop from S21 for location level.');
			  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for credit_stop from S21 for location level.',NULL,NULL,NULL,NULL);

              COMMIT;
            --credit_stop account level
            MERGE INTO canon_e633_cust_site_stg_tbl stg
               USING (SELECT DISTINCT plw.loc_num, dacp.cust_hard_hld_flg credit_stop
                        FROM sell_to_cust dac -- DB Change
                               ,pty_loc_wrk plw
                               ,acct_loc al
                              -- ,canon_e633_cust_site_stg_tbl e633
                               ,ds_acct_cr_prfl dacp
                       WHERE 1 = 1 --e633.location_number = plw.loc_num
                         AND plw.loc_num = al.loc_num
                         AND al.ds_acct_num = dac.sell_to_cust_cd -- DB Change
                         AND dacp.ds_acct_num = dac.sell_to_cust_cd -- DB Change
                         --AND e633.credit_stop IS NULL
                         AND dac.ezcancelflag = '0'
                         AND dac.glbl_cmpy_cd = 'ADB'
                         AND plw.ezcancelflag = '0'
                         AND plw.glbl_cmpy_cd = 'ADB'
                         AND al.ezcancelflag = '0'
                         AND al.glbl_cmpy_cd = 'ADB'
                         AND dacp.ezcancelflag = '0'
                         AND dacp.glbl_cmpy_cd = 'ADB'
						 --QC50716--start
						 AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(al.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(al.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
						  AND al.rgtn_sts_cd <> 'P99'
						  --QC50716--end
                         UNION
                         SELECT DISTINCT plw.loc_num, dacp.cust_hard_hld_flg credit_stop
                           FROM ds_acct_pros dac
                               ,pros_pty_loc_wrk plw --DB Changes
                              -- ,acct_loc al
                             --  ,canon_e633_cust_site_stg_tbl e633
                               ,ds_acct_cr_prfl dacp
                         WHERE 1 =1 --e633.location_number = plw.loc_num
                           AND plw.loc_num = dac.loc_num -- DB Changes
                         --  AND al.ds_acct_num = dac.ds_acct_num
                           AND dacp.ds_acct_num = dac.ds_acct_num
                          -- AND e633.credit_stop IS NULL
                           AND dac.ezcancelflag = '0'
                           AND dac.glbl_cmpy_cd = 'ADB'
                           AND plw.ezcancelflag = '0'
                           AND plw.glbl_cmpy_cd = 'ADB'
                          -- AND al.ezcancelflag = '0'
                          -- AND al.glbl_cmpy_cd = 'ADB'
                           AND dacp.ezcancelflag = '0'
                           AND dacp.glbl_cmpy_cd = 'ADB'
                         ) S21
                 ON(stg.location_number = S21.loc_num)
                 WHEN MATCHED THEN UPDATE
                    SET stg.credit_Stop = s21.credit_stop;
               dbms_output.put_line(SQL%ROWCOUNT||' records updated for credit_stop from S21 for account level.');
			   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for credit_stop from S21 for account level.',NULL,NULL,NULL,NULL);
               COMMIT;

                --update LFS customer accounts owner ids
               l_dyn_sql := 'MERGE INTO canon_e633_cust_site_stg_tbl stg ' ||
                             ' USING(SELECT e633.location_number ' ||
                                            ',cert.territory_name writing_territory'||
                                            ',cert.resource_id writing_resource_id' || --QC54017
                                            ',cert.resource_name writing_rep_name' || --QC54017
                                            ',cert.employee_number writing_employee_number' || --QC545017
--                                            ',(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_id  ELSE cert.resource_id  END  )  writing_resource_id  '||
--                                            ',(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_name  ELSE cert.resource_name  END  )  writing_rep_name '||
--                                            ',(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_employee_number  ELSE cert.employee_number  END  )  writing_employee_number '||
                                     ' FROM canon_e633_cust_site_stg_tbl e633 ' ||
                                     '      ,acct_trty_resrc_asg atra ' ||
                                     '      ,canon_e633_lfs_rscterr_tbl cert ' ||
                                     ' WHERE E633.LFSBU IS NOT NULL ' ||
                                     '   AND e633.account_type = ''CUSTOMER'' ' ||
                                     '   AND atra.glbl_cmpy_cd = ''ADB'' ' ||
                                     '   AND ezcancelflag = ''0'' ' ||
                                     '   AND atra.loc_num = e633.location_number ' ||
                                     '   AND NVL(e633.lfs_owner_employee_number,''NA'') <> NVL(cert.employee_number,''NA'') ' || --QC50417
                                     '   AND cert.terr_id = (CASE ' ;
                                    FOR dynamic_cols_rec IN dynamic_cols_cur('10','LFS')
                                    LOOP
                                        l_dyn_sql := l_dyn_sql ||
                                         ' WHEN atra.' || dynamic_cols_rec.attribute_value || ' IS NOT NULL THEN atra. ' || dynamic_cols_rec.attribute_value;
                                    END LOOP ;
                                    l_dyn_sql := l_dyn_sql || ' ELSE '''' END ))s21 ' ||
                              ' ON(s21.location_number = stg.location_number) ' ||
                              ' WHEN MATCHED THEN UPDATE ' ||
                              ' SET lfs_owner_employee_number = s21.writing_employee_number ' ||
                              '     ,lfs_account_owner_id = (SELECT sf_user_id FROM CANON_E633_LFS_USER_MAP_TBL WHERE employee_number = s21.writing_employee_number) ' ||
                              '     ,lfs_sf_status_flag = (CASE WHEN lfs_sf_account_id IS NOT NULL THEN ''U'' ELSE lfs_sf_status_flag END)'; --QC54017

                --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,l_dyn_sql);
                EXECUTE IMMEDIATE l_dyn_sql;
                dbms_output.put_line(SQL%ROWCOUNT||' LFS customer records updated for account owner ID');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' LFS customer records updated for account owner ID',NULL,NULL,NULL,NULL);
                COMMIT;

                --update LFS prospect accounts owner ids
               l_dyn_sql := 'MERGE INTO canon_e633_cust_site_stg_tbl stg ' ||
                             ' USING(SELECT e633.location_number ' ||
                                            ',cert.territory_name writing_territory'||
                                            ',cert.resource_id writing_resource_id' || --QC54017
                                            ',cert.resource_name writing_rep_name' || --QC54017
                                            ',cert.employee_number writing_employee_number' || --QC545017
                                           /* ',(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_id  ELSE cert.resource_id  END  )  writing_resource_id  '||
                                            ',(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_resource_name  ELSE cert.resource_name  END  )  writing_rep_name '||
                                            ',(CASE WHEN (cert.resource_name LIKE ''%ADJ%'' OR cert.resource_name LIKE ''%TERRITORY%'') THEN  cert.mgr_employee_number  ELSE cert.employee_number  END  )  writing_employee_number '|| */
                                     ' FROM canon_e633_cust_site_stg_tbl e633 ' ||
                                     '      ,pros_trty_resrc_asg atra ' || --DB Changes
                                     '      ,canon_e633_lfs_rscterr_tbl cert ' ||
                                     ' WHERE E633.LFSBU IS NOT NULL ' ||
                                     '   AND e633.account_type = ''PROSPECT'' ' ||
                                     '   AND atra.glbl_cmpy_cd = ''ADB'' ' ||
                                     '   AND ezcancelflag = ''0'' ' ||
                                     '   AND atra.loc_num = e633.location_number ' ||
                                     '   AND NVL(e633.lfs_owner_employee_number,''NA'') <> NVL(cert.employee_number,''NA'') ' || --QC50417
                                     '   AND cert.terr_id = (CASE ' ;
                                    FOR dynamic_cols_rec IN dynamic_cols_cur('0','LFS')
                                    LOOP
                                        l_dyn_sql := l_dyn_sql ||
                                         ' WHEN atra.' || dynamic_cols_rec.attribute_value || ' IS NOT NULL THEN atra. ' || dynamic_cols_rec.attribute_value;
                                    END LOOP ;
                                    l_dyn_sql := l_dyn_sql || ' ELSE '''' END ) )s21 ' ||
                              ' ON(s21.location_number = stg.location_number) ' ||
                              ' WHEN MATCHED THEN UPDATE ' ||
                              ' SET lfs_owner_employee_number = s21.writing_employee_number ' ||
                              '     ,lfs_account_owner_id = (SELECT sf_user_id FROM CANON_E633_LFS_USER_MAP_TBL WHERE employee_number = s21.writing_employee_number) ' ||
                              '     ,lfs_sf_status_flag = (CASE WHEN lfs_sf_account_id IS NOT NULL THEN ''U'' ELSE lfs_sf_status_flag END)'; --QC54017
                --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,l_dyn_sql);
                EXECUTE IMMEDIATE l_dyn_sql;
                dbms_output.put_line(SQL%ROWCOUNT||' PPS prospect records updated for account owner ID');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' PPS prospect records updated for account owner ID.',NULL,NULL,NULL,NULL);
                COMMIT;

                --update PPS customer accounts owner ids
                l_dyn_sql := 'MERGE INTO canon_e633_cust_site_stg_tbl stg ' ||
                             ' USING(SELECT e633.location_number ' ||
                                            ',cert.territory_name writing_territory'|| --QC51955
                                            ',cert.resource_id writing_resource_id' || --QC54017
                                            ',cert.resource_name writing_rep_name' || --QC54017
                                            ',cert.employee_number writing_employee_number' || --QC545017
                                          /*  ',(CASE WHEN (upper(cert.resource_name) LIKE ''%OPEN TERR%'' OR upper(cert.resource_name) LIKE ''%ADJ%'') THEN  cert.mgr_resource_id  ELSE cert.resource_id  END  )  writing_resource_id  '||
                                            ',(CASE WHEN (upper(cert.resource_name) LIKE ''%ADJ%'' OR upper(cert.resource_name) LIKE ''%OPEN TERR%'') THEN  cert.mgr_resource_name  ELSE cert.resource_name  END  )  writing_rep_name '||
                                            ',(CASE WHEN (upper(cert.resource_name) LIKE ''%ADJ%'' OR upper(cert.resource_name) LIKE ''%OPEN TERR%'') THEN  cert.mgr_employee_number  ELSE cert.employee_number  END  )  writing_employee_number '|| */
                                     ' FROM canon_e633_cust_site_stg_tbl e633 ' ||
                                     '      ,acct_trty_resrc_asg atra ' ||
                                     '      ,canon_e633_pps_rscterr_tbl cert ' ||
                                     ' WHERE E633.PPSBU IS NOT NULL ' ||
                                     '   AND e633.account_type = ''CUSTOMER'' ' ||
                                     '   AND atra.glbl_cmpy_cd = ''ADB'' ' ||
                                     '   AND ezcancelflag = ''0'' ' ||
                                     '   AND atra.loc_num = e633.location_number ' ||
                                     '   AND NVL(e633.pps_owner_employee_number,''NA'') <> NVL(cert.employee_number,''NA'') ' || --QC50417
                                     '   AND cert.terr_id = (CASE ' ;
                                    FOR dynamic_cols_rec IN dynamic_cols_cur('10','PPS')
                                    LOOP
                                        l_dyn_sql := l_dyn_sql ||
                                         ' WHEN atra.' || dynamic_cols_rec.attribute_value || ' IS NOT NULL THEN atra. ' || dynamic_cols_rec.attribute_value;
                                    END LOOP ;
                                    l_dyn_sql := l_dyn_sql || ' ELSE '''' END ) )s21 ' ||
                              ' ON(s21.location_number = stg.location_number) ' ||
                              ' WHEN MATCHED THEN UPDATE ' ||
                              ' SET pps_owner_employee_number = s21.writing_employee_number ' ||
                              '     ,pps_account_owner_id = (SELECT sf_user_id FROM CANON_E633_PPS_USER_MAP_TBL WHERE employee_number = s21.writing_employee_number) ' ||
                              '     ,pps_sf_status_flag = (CASE WHEN pps_sf_account_id IS NOT NULL THEN ''U'' ELSE pps_sf_status_flag END)'; --QC54017
                --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,l_dyn_sql);
                EXECUTE IMMEDIATE l_dyn_sql;
                dbms_output.put_line(SQL%ROWCOUNT||' PPS customer records updated for account owner ID');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' PPS customer records updated for account owner ID',NULL,NULL,NULL,NULL);
                COMMIT;
                --update PPS prospect accounts owner ids
                l_dyn_sql := 'MERGE INTO canon_e633_cust_site_stg_tbl stg ' ||
                            ' USING (SELECT e633.location_number ' ||
                                            ',cert.territory_name writing_territory'|| --QC51955
                                            ',cert.resource_id writing_resource_id' || --QC54017
                                            ',cert.resource_name writing_rep_name' || --QC54017
                                            ',cert.employee_number writing_employee_number' || --QC545017
                                          /*  ',(CASE WHEN (upper(cert.resource_name) LIKE ''%ADJ%'' OR upper(cert.resource_name) LIKE ''%OPEN TERR%'') THEN  cert.mgr_resource_id  ELSE cert.resource_id  END  )  writing_resource_id  '||
                                            ',(CASE WHEN (upper(cert.resource_name) LIKE ''%ADJ%'' OR upper(cert.resource_name) LIKE ''%OPEN TERR%'') THEN  cert.mgr_resource_name  ELSE cert.resource_name  END  )  writing_rep_name '||
                                            ',(CASE WHEN (upper(cert.resource_name) LIKE ''%ADJ%'' OR upper(cert.resource_name) LIKE ''%OPEN TERR%'') THEN  cert.mgr_employee_number  ELSE cert.employee_number  END  )  writing_employee_number '|| */
                                     ' FROM canon_e633_cust_site_stg_tbl e633 ' ||
                                     '      ,pros_trty_resrc_asg atra ' || --DB Changes
                                     '      ,canon_e633_pps_rscterr_tbl cert ' ||
                                     ' WHERE E633.PPSBU IS NOT NULL ' ||
                                     '   AND e633.account_type = ''PROSPECT'' ' ||
                                     '   AND atra.glbl_cmpy_cd = ''ADB'' ' ||
                                     '   AND ezcancelflag = ''0'' ' ||
                                     '   AND atra.loc_num = e633.location_number ' ||
                                     '   AND NVL(e633.pps_owner_employee_number,''NA'') <> NVL(cert.employee_number,''NA'') ' || --QC50417
                                     '   AND cert.terr_id = (CASE ' ;
                                    FOR dynamic_cols_rec IN dynamic_cols_cur('0','PPS')
                                    LOOP
                                        l_dyn_sql := l_dyn_sql ||
                                         ' WHEN atra.' || dynamic_cols_rec.attribute_value || ' IS NOT NULL THEN atra. ' || dynamic_cols_rec.attribute_value;
                                    END LOOP ;
                                    l_dyn_sql := l_dyn_sql || ' ELSE '''' END ) )s21 ' ||
                              ' ON(s21.location_number = stg.location_number) ' ||
                              ' WHEN MATCHED THEN UPDATE ' ||
                              ' SET pps_owner_employee_number = s21.writing_employee_number ' ||
                              '     ,pps_account_owner_id = (SELECT sf_user_id FROM CANON_E633_PPS_USER_MAP_TBL WHERE employee_number = s21.writing_employee_number) ' ||
                              '     ,pps_sf_status_flag = (CASE WHEN pps_sf_account_id IS NOT NULL THEN ''U'' ELSE pps_sf_status_flag END)'; --QC54017

                EXECUTE IMMEDIATE l_dyn_sql;
                dbms_output.put_line(SQL%ROWCOUNT||' PPS prospect records updated for account owner ID');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' PPS prospect records updated for account owner ID',NULL,NULL,NULL,NULL);
                COMMIT;

    --QC56599 - for the existing data if the owner is a IS rep then change it to CSA Interface
     UPDATE canon_e633_cust_site_stg_tbl e633
        SET lfs_sf_status_flag = (CASE WHEN lfs_sf_account_id IS NOT NULL THEN 'U' ELSE lfs_sf_status_flag END)
            ,lfs_owner_employee_number = null
--            pps_sf_status_flag = (CASE WHEN ppsbu IS NOT NULL THEN 'U' ELSE NULL END),
       WHERE lfs_owner_employee_number IS NOT NULL
         AND lfs_owner_employee_number IN (SELECT psn.psn_num
                                          FROM ds_org_unit dou
                                               ,ds_org_resrc_reln dorr
                                               ,s21_psn psn
                                          where 1 = 1
                                          and dou.glbl_cmpy_cd = 'ADB'
                                          and dou.ezcancelflag = '0'
                                          and dorr.glbl_cmpy_cd = 'ADB'
                                          and dorr.ezcancelflag = '0'
                                          and psn.glbl_cmpy_cd = 'ADB'
                                          and psn.ezcancelflag = '0'
                                          and dou.trty_grp_tp_cd = 'IS'
                                          and dou.org_cd = dorr.org_cd
                                          and dorr.psn_cd = psn.psn_cd
                                          AND trunc(sysdate) BETWEEN trunc(nvl(to_date(dou.eff_from_dt, 'yyyymmdd'), sysdate)) AND trunc(nvl(to_date(dou.eff_thru_dt, 'yyyymmdd'), sysdate))
                                          AND trunc(sysdate) BETWEEN trunc(nvl(to_date(dorr.eff_from_dt, 'yyyymmdd'), sysdate)) AND trunc(nvl(to_date(dorr.eff_thru_dt, 'yyyymmdd'), sysdate))
                                          AND dorr.acct_team_role_tp_cd is null);

        UPDATE canon_e633_cust_site_stg_tbl e633
        SET pps_sf_status_flag = (CASE WHEN pps_sf_account_id IS NOT NULL THEN 'U' ELSE pps_sf_status_flag END)
            ,pps_owner_employee_number = null
--            pps_sf_status_flag = (CASE WHEN ppsbu IS NOT NULL THEN 'U' ELSE NULL END),
       WHERE pps_owner_employee_number IS NOT NULL
         AND pps_owner_employee_number IN (SELECT psn.psn_num
                                          FROM ds_org_unit dou
                                               ,ds_org_resrc_reln dorr
                                               ,s21_psn psn
                                          where 1 = 1
                                          and dou.glbl_cmpy_cd = 'ADB'
                                          and dou.ezcancelflag = '0'
                                          and dorr.glbl_cmpy_cd = 'ADB'
                                          and dorr.ezcancelflag = '0'
                                          and psn.glbl_cmpy_cd = 'ADB'
                                          and psn.ezcancelflag = '0'
                                          and dou.trty_grp_tp_cd = 'IS'
                                          and dou.org_cd = dorr.org_cd
                                          AND dorr.psn_cd = psn.psn_cd
                                          AND trunc(sysdate) BETWEEN trunc(nvl(to_date(dou.eff_from_dt, 'yyyymmdd'), sysdate)) AND trunc(nvl(to_date(dou.eff_thru_dt, 'yyyymmdd'), sysdate))
                                          AND trunc(sysdate) BETWEEN trunc(nvl(to_date(dorr.eff_from_dt, 'yyyymmdd'), sysdate)) AND trunc(nvl(to_date(dorr.eff_thru_dt, 'yyyymmdd'), sysdate))
                                          AND dorr.acct_team_role_tp_cd is null);
        -- QC56599 - END LFS IS rep check

               --QC53489 -- SIC Code updates - start
            BEGIN
              MERGE INTO canon_e633_cust_site_stg_tbl e633
                USING (SELECT distinct loc_num, ds_cust_sic_cd ,ds_cust_sic_desc_txt
                         FROM pty_loc_wrk plw
                              ,canon_e633_cust_site_stg_tbl e633
                        WHERE plw.ezcancelflag = '0'
                          AND plw.glbl_cmpy_cd = 'ADB'
                          and e633.location_number = plw.loc_num
                          AND (lfs_sf_account_id IS NOT NULL OR pps_sf_account_id IS NOT NULL)
                          AND NVL(plw.ds_cust_sic_cd,'-1') <> NVL(e633.sic_code,'-1')
                       )s21
                ON(e633.location_number = s21.loc_num)
              WHEN MATCHED THEN UPDATE
                SET e633.sic_code = s21.ds_cust_sic_cd
                    ,e633.SIC_CODE_DESCRIPTION = ds_cust_sic_desc_txt
                    ,last_update_date = SYSDATE
                    ,lfs_sf_status_flag = CASE WHEN lfs_sf_account_id IS NOT NULL THEN 'U' END
                    ,pps_sf_status_flag = CASE WHEN pps_sf_account_id IS NOT NULL THEN 'U' END;

              dbms_output.put_line(SQL%rowcount||' Records updated for SIC CODE.');
                canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records updated for SIC CODE',NULL,NULL,NULL,NULL);
               COMMIT;
            exception WHEN others THEN
                dbms_output.put_line('SIC CODE Update Exception: ' ||sqlerrm);
                canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR','SIC CODE Exception: ',NULL,NULL,NULL,SQLERRM);

            END;
          --QC53489 --SIC Code Updates --END
		          --phone formatsQC#57749 --start

        UPDATE canon_e633_cust_site_stg_tbl e633
        SET
            telephone = format_phone(telephone),
            lfs_sf_status_flag = (
                CASE
                    WHEN lfs_sf_account_id IS NOT NULL THEN
                        'U'
                    ELSE
                        lfs_sf_status_flag
                END
            ),
            pps_sf_status_flag = (
                CASE
                    WHEN pps_sf_account_id IS NOT NULL THEN
                        'U'
                    ELSE
                        pps_sf_status_flag
                END
            )
        WHERE
            length(telephone) >= 11
            AND ( telephone NOT LIKE '%(%'
                  OR telephone NOT LIKE '%)%'
                  OR telephone LIKE '% %' ); -- contains spaces

        dbms_output.put_line('Updated records for phone formats: ' || SQL%rowcount);
  -- QC#57749---end



--			--get the Account Owner ID if the record already exists in Salesforce
--			MERGE INTO canon_e633_cust_site_stg_tbl stg
--			 USING (SELECT legacy_ref_id
--                          ,account_number
--                          ,account_owner_id
--                          ,sf_prospect_number
--                          ,DUNS_HQ_NUM_MKTG
--                          ,DUNS_HQ_NAME_MKTG
--                          ,DUNS_NUMBER_MKTG
--                          ,SIC_CODE_MKTG
--                          ,SIC_CODE_DESCRIPTION_MKTG
--                          ,INDUSTRY_MKTG
--                          ,ULTIMATE_DUNS_MKTG
--                          ,ULTIMATE_DUNS_NAME_MKTG
--					  FROM CANON_E633_LFS_CUST_DWLD_TBL
--					)lfs
--			  ON(stg.legacy_ref_id = lfs.legacy_ref_id)
--			WHEN MATCHED THEN UPDATE
--				SET --stg.account_number = lfs.account_number
--				     stg.lfs_account_owner_id = lfs.account_owner_id
--					,stg.lfs_sfdc_number = lfs.sf_prospect_number
--					,stg.lfs_duns_number = NVL(lfs.DUNS_NUMBER_MKTG, stg.lfs_duns_number)
--					,stg.lfs_duns_hq_num =  NVL(lfs.DUNS_HQ_NUM_MKTG, stg.lfs_duns_hq_num)
--					,stg.LFS_DUNS_HQ_NAME =  NVL(lfs.DUNS_HQ_NAME_MKTG, stg.LFS_DUNS_HQ_NAME)
--					,stg.lfs_ult_duns =  NVL(lfs.ULTIMATE_DUNS_MKTG, stg.lfs_ult_duns)
--					,stg.lfs_ult_duns_name =  NVL(lfs.ULTIMATE_DUNS_NAME_MKTG, stg.lfs_ult_duns_name)
--					,stg.LFS_SIC_CODE =  NVL(lfs.SIC_CODE_MKTG, stg.LFS_SIC_CODE)
--					--,stg.LFS_SIC_CODE_DESCRIPTION =  NVL(lfs.SIC_CODE_DESCRIPTION_MKTG, stg.LFS_SIC_CODE_DESCRIPTION)
--					,stg.LFS_INDUSTRY_MKTG = NVL(lfs.INDUSTRY_MKTG, stg.LFS_INDUSTRY_MKTG)
--					;
--
----            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records from CANON_E633_LFS_CUST_DWLD_TBL merged with staging table CANON_E633_CUST_SITE_STG_TBL.');
--            dbms_output.put_line(SQL%ROWCOUNT||' records from CANON_E633_LFS_CUST_DWLD_TBL merged with staging table CANON_E633_CUST_SITE_STG_TBL.');
--
--			--get the Account Owner ID if the record already exists in Salesforce
--			MERGE INTO canon_e633_cust_site_stg_tbl stg
--			 USING (SELECT legacy_ref_id
--                          ,account_number
--                          ,account_owner_id
--                          ,sf_prospect_number
--                          ,DUNS_HQ_NUM_MKTG
--                          ,DUNS_HQ_NAME_MKTG
--                          ,DUNS_NUMBER_MKTG
--                          ,SIC_CODE_MKTG
--                          ,SIC_CODE_DESCRIPTION_MKTG
--                          ,INDUSTRY_MKTG
--                          ,ULTIMATE_DUNS_MKTG
--                          ,ULTIMATE_DUNS_NAME_MKTG
--					  FROM CANON_E633_PPS_CUST_DWLD_TBL
--					)PPS
--			  ON(stg.legacy_ref_id = PPS.legacy_ref_id)
--			WHEN MATCHED THEN UPDATE
--				SET --stg.account_number = PPS.account_number
--				     stg.PPS_account_owner_id = pps.account_owner_id
--				    ,stg.PPS_sfdc_number = PPS.sf_prospect_number
--					,stg.PPS_duns_number = NVL(PPS.DUNS_NUMBER_MKTG, stg.PPS_duns_number)
--					,stg.PPS_duns_hq_num =  NVL(PPS.DUNS_HQ_NUM_MKTG, stg.PPS_duns_hq_num)
--					,stg.PPS_DUNS_HQ_NAME =  NVL(PPS.DUNS_HQ_NAME_MKTG, stg.PPS_DUNS_HQ_NAME)
--					,stg.PPS_ult_duns =  NVL(PPS.ULTIMATE_DUNS_MKTG, stg.PPS_ult_duns)
--					,stg.PPS_ult_duns_name =  NVL(PPS.ULTIMATE_DUNS_NAME_MKTG, stg.PPS_ult_duns_name)
--					,stg.PPS_SIC_CODE =  NVL(PPS.SIC_CODE_MKTG, stg.PPS_SIC_CODE)
--					--,stg.PPS_SIC_CODE_DESCRIPTION =  NVL(PPS.SIC_CODE_DESCRIPTION_MKTG, stg.PPS_SIC_CODE_DESCRIPTION)
--					,stg.PPS_INDUSTRY_MKTG = NVL(PPS.INDUSTRY_MKTG, stg.PPS_INDUSTRY_MKTG)
--					;
--
----            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records from CANON_E633_PPS_CUST_DWLD_TBL merged with staging table CANON_E633_CUST_SITE_STG_TBL.');
--            dbms_output.put_line(SQL%ROWCOUNT||' records from CANON_E633_PPS_CUST_DWLD_TBL merged with staging table CANON_E633_CUST_SITE_STG_TBL.');

           UPDATE canon_e633_cust_site_stg_tbl
              SET validation_status = NULL
                 ,validation_status_message = NULL
            WHERE 1 = 1
		        AND(lfs_sf_status_flag IN ('I', 'U', 'E') OR
                    pps_sf_status_flag IN ('I', 'U', 'E')
		           );

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' I U E records had validation_status reset');
            dbms_output.put_line(SQL%ROWCOUNT||' I U E records had validation_status reset');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' I U E records had validation_status reset',NULL,NULL,NULL,NULL);

		--VALIDATION checks
		   UPDATE canon_e633_cust_site_stg_tbl
		      SET  lfs_sf_status_flag = (CASE WHEN LFSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,pps_sf_status_flag = (CASE WHEN PPSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,validation_status = 'E'
		          ,validation_status_message = (CASE WHEN validation_status_message IS NULL
                                             THEN 'Shipping Zip not Valid. Should be all numbers of the format 99999-8888'
                                             ELSE validation_status_message || '+Shipping Zip not Valid. Should be all numbers of the format 99999-8888'
                                             END)
		      WHERE 1 = 1
		        AND(lfs_sf_status_flag IN ('I', 'U', 'E') OR
                    pps_sf_status_flag IN ('I', 'U', 'E')
		           )
		        AND country = 'US'
		        AND length(replace(replace(postal_code, '-', ''), ' ', '')) NOT IN (5, 9);

		--VALIDATION checks
		   UPDATE canon_e633_cust_site_stg_tbl
		      SET lfs_sf_status_flag = (CASE WHEN LFSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,pps_sf_status_flag = (CASE WHEN PPSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,validation_status = 'E'
		          ,validation_status_message = (CASE WHEN validation_status_message IS NULL
                                             THEN 'Country can not be more than 2 characters. For "United States", please use "US". For "Canada", please use "CA".'
                                             ELSE validation_status_message || '+Country can not be more than 2 characters. For "United States", please use "US". For "Canada", please use "CA".'
                                             END)
		      WHERE 1 = 1
		        AND(lfs_sf_status_flag IN ('I', 'U', 'E') OR
                    pps_sf_status_flag IN ('I', 'U', 'E')
		           )
		        AND length(country) != 2;

		--VALIDATION checks
		   UPDATE canon_e633_cust_site_stg_tbl
		      SET lfs_sf_status_flag = (CASE WHEN LFSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,pps_sf_status_flag = (CASE WHEN PPSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,validation_status = 'E'
		          ,validation_status_message = (CASE WHEN validation_status_message IS NULL
                                             THEN 'Shipping City is Required'
                                             ELSE validation_status_message || '+Shipping City is Required'
                                             END)
		      WHERE 1 = 1
		        AND(lfs_sf_status_flag IN ('I', 'U', 'E') OR
                    pps_sf_status_flag IN ('I', 'U', 'E')
		           )
		        AND town is null;

		--VALIDATION checks
		   UPDATE canon_e633_cust_site_stg_tbl
		      SET lfs_sf_status_flag = (CASE WHEN LFSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,pps_sf_status_flag = (CASE WHEN PPSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,validation_status = 'E'
		          ,validation_status_message = (CASE WHEN validation_status_message IS NULL
                                             THEN 'Shipping State can not be less than 2 characters'
                                             ELSE validation_status_message || '+Shipping State can not be less than 2 characters'
                                             END)
		      WHERE 1 = 1
		        AND(lfs_sf_status_flag IN ('I', 'U', 'E') OR
                    pps_sf_status_flag IN ('I', 'U', 'E')
		           )
		        AND LENGTH(geo_area) != 2;

		--VALIDATION checks
		   UPDATE canon_e633_cust_site_stg_tbl
		      SET lfs_sf_status_flag = (CASE WHEN LFSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,pps_sf_status_flag = (CASE WHEN PPSBU IS NOT NULL THEN 'E' ELSE NULL END)
		          ,validation_status = 'E'
		          ,validation_status_message = (CASE WHEN validation_status_message IS NULL
                                             THEN 'Shipping Street is Required'
                                             ELSE validation_status_message || '+Shipping Street is Required'
                                             END)
		      WHERE 1 = 1
		        AND(lfs_sf_status_flag IN ('I', 'U', 'E') OR
                    pps_sf_status_flag IN ('I', 'U', 'E')
		           )
		        AND address is null;

		--update the account owner id to CSA Interace if the account is deleted
		--LFS changes
      BEGIN
		SELECT sf_user_id, employee_number
		    INTO l_sf_user_id, l_sf_user_emp_num
		    FROM canon_e633_lfs_user_dwld_tbl
		   WHERE employee_number = 'CANON';
	  EXCEPTION WHEN OTHERS THEN
			l_sf_user_id := null;
			l_sf_user_emp_num := null;
	  END;

		  UPDATE canon_e633_cust_site_stg_tbl a
		     SET lfs_account_owner_id = l_sf_user_id
		         ,lfs_owner_employee_number = l_sf_user_emp_num
		   WHERE lfs_sf_status_flag in ('I','U')
		     AND (lfs_owner_employee_number IS NULL OR is_deleted = 'Y');
		dbms_output.put_line(SQL%ROWCOUNT||' LFS records account owner id updated to CSA');
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' LFS records account owner id updated to CSA',NULL,NULL,NULL,NULL);
--		fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' LFS records account owner id updated to CSA');

		  --for PPS Account Owner ID
		BEGIN
		  SELECT sf_user_id, employee_number
		    INTO l_sf_user_id, l_sf_user_emp_num
		    FROM canon_e633_pps_user_dwld_tbl
		   WHERE employee_number = 'CANON';
		EXCEPTION
			WHEN OTHERS THEN
				l_sf_user_id := null;
				l_sf_user_emp_num := null;
		END;

		  UPDATE canon_e633_cust_site_stg_tbl a
		     SET pps_account_owner_id = l_sf_user_id
		         ,pps_owner_employee_number = l_sf_user_emp_num
		   WHERE pps_sf_status_flag in ('I','U')
		     AND (pps_owner_employee_number IS NULL OR is_deleted = 'Y');
		     dbms_output.put_line(SQL%ROWCOUNT||' PPS records account owner id updated to CSA');
			 canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' PPS records account owner id updated to CSA',NULL,NULL,NULL,NULL);
--		fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' PPS records account owner id updated to CSA');

		--update prospect_sf_id for prospect records
   MERGE INTO canon_e633_cust_site_stg_tbl a
      USING(SELECT distinct pros_rvw_id, rvw_pros_num, loc_num
             FROM ds_acct_rvw_pros
             WHERE line_biz_tp_cd IN ('LFS')) pros
        ON(a.location_number = pros.loc_num AND a.lfs_sf_status_flag = 'I')
    WHEN MATCHED THEN
      UPDATE SET a.lfs_sf_account_id = pros.pros_rvw_id
	             ,a.legacy_ref_id = pros.rvw_pros_num;
    dbms_output.put_line('Updated CANON_E633_CUST_SITE_STG_TBL with LFS prospect id : ' ||SQL%ROWCOUNT);
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' LFS prospect id updated',NULL,NULL,NULL,NULL);

     MERGE INTO canon_e633_cust_site_stg_tbl a
      USING(SELECT distinct pros_rvw_id, rvw_pros_num, loc_num
             FROM ds_acct_rvw_pros
             WHERE line_biz_tp_cd IN ('PPS')) pros
        ON(a.location_number = pros.loc_num AND a.pps_sf_status_flag = 'I')
    WHEN MATCHED THEN
      UPDATE SET a.pps_sf_account_id = pros.pros_rvw_id
				 ,a.legacy_ref_id = pros.rvw_pros_num;
    dbms_output.put_line('Updated CANON_E633_CUST_SITE_STG_TBL with PPS prospect id : ' ||SQL%ROWCOUNT);
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' PPS prospect id updated',NULL,NULL,NULL,NULL);

           COMMIT;

	  BEGIN
           UPDATE canon_s21_cd_val_tbl v
		      SET V.VAL76 = L_PROGRAM_START_DATE
		    WHERE v.val1 = 'CUSTOMER'
		      AND v.cd_id = (SELECT cd_id
						       FROM CANON_S21_CD_TBL
						      WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
		  CANON_E633_SF_ERROR_LOG_PKG.LOG_ERROR(G_PACKAGE_NAME,L_PROCEDURE_NAME,'INFO','Updated S21 profiles code table',NULL,NULL,NULL,NULL);
	 EXCEPTION
		 WHEN OTHERS THEN
		   dbms_output.put_line('in update S21 profiles code table exception '|| SQLERRM);
		   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR','update s21 profile code table',NULL,NULL,NULL,SQLERRM);
	 END;

     COMMIT;

		EXCEPTION
		   WHEN OTHERS THEN
              rollback;
              retcode := 2;
              errbuff := SQLERRM;
--			  fnd_file.put_line(fnd_file.log, SQLERRM);
              dbms_output.put_line(SQLERRM);
			  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
		END load_customers;

	--PROCEDURE delete_ib
 	PROCEDURE delete_ib(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
		IS
            l_procedure_name        VARCHAR2(60) := 'delete_ib';
            l_run_date  DATE    := SYSDATE;
            l_last_run_date DATE;
            l_program_start_date DATE := SYSDATE;
            l_last_run_date_num VARCHAR2(17);

	  BEGIN
	  retcode := '0';
		-- delete old data from the archive table
		DELETE
		  FROM canon_e633_ib_del_arc
		 WHERE created_date <= add_months(sysdate, -6);

		-- archive the processed records
		--BEGIN
			INSERT INTO canon_e633_ib_del_arc(svc_mach_mstr_pk,
										install_customer,
										config_nbr,
										config_type,
										commercial_name,
										machine_bus_unit,
										model_series,
										model_group,
										model_group_desc,
										technology_class,
										volume_class,
										marketing_prod_gp,
										serial_number,
										install_date,
										life_cycle_stat,
										instal,
										install_code,
										type_of_sale,
										hw_cont_duration,
										hw_cont_str_date,
										hw_cont_exp_date,
										hw_cont_end_date,
										fs_cont_duration,
										fs_cont_str_date,
										fs_cont_exp_date,
										fs_cont_end_date,
										warr_end_date,
										warranty_duratn,
										warr_start_date,
										date_last_prd_man,
										config_inv_ref,
										cost_allocate_ref,
										service_po_nbr,
										avg_cpy_vol_per_m,
										hrdware_cnt_name,
										supply_type,
										promo_code_1,
										promo_code_2,
										promo_code_3,
										promo_code_4,
										promo_code_5,
										promo_code_6,
										promo_code_7,
										hw_cont_exp_flag,
										marketing_order_t,
										order_type,
										last_meter_read,
										lst_mtr_read_dt,
										warranty_type,
										removal_date,
										order_number,
										invoice_address,
										ofsi_flag,
										inactive_date,
										source,
										selling_bu,
										sold_to_address,
										free_to_use_1,
										free_to_use_2,
										free_to_use_3,
										free_to_use_4,
										free_to_use_5,
										free_to_use_6,
										lfs_sf_account_id,
										lfs_sf_soldto_account_id,
										lfs_sf_ib_id,
										lfs_sf_status_flag,
										lfs_sf_status_message,
										lfs_last_update_date,
										pps_sf_ib_id,
										pps_sf_status_flag,
										pps_sf_status_message,
										pps_last_update_date,
										lfsbu,
										ppsbu,
										dpsbu,
										pps_sf_account_id,
										pps_sf_soldto_account_id,
										insert_flag,
										load_date,
										itembu,
										created_date) SELECT svc_mach_mstr_pk,
														install_customer,
														config_nbr,
														config_type,
														commercial_name,
														machine_bus_unit,
														model_series,
														model_group,
														model_group_desc,
														technology_class,
														volume_class,
														marketing_prod_gp,
														serial_number,
														install_date,
														life_cycle_stat,
														instal,
														install_code,
														type_of_sale,
														hw_cont_duration,
														hw_cont_str_date,
														hw_cont_exp_date,
														hw_cont_end_date,
														fs_cont_duration,
														fs_cont_str_date,
														fs_cont_exp_date,
														fs_cont_end_date,
														warr_end_date,
														warranty_duratn,
														warr_start_date,
														date_last_prd_man,
														config_inv_ref,
														cost_allocate_ref,
														service_po_nbr,
														avg_cpy_vol_per_m,
														hrdware_cnt_name,
														supply_type,
														promo_code_1,
														promo_code_2,
														promo_code_3,
														promo_code_4,
														promo_code_5,
														promo_code_6,
														promo_code_7,
														hw_cont_exp_flag,
														marketing_order_t,
														order_type,
														last_meter_read,
														lst_mtr_read_dt,
														warranty_type,
														removal_date,
														order_number,
														invoice_address,
														ofsi_flag,
														inactive_date,
														source,
														selling_bu,
														sold_to_address,
														free_to_use_1,
														free_to_use_2,
														free_to_use_3,
														free_to_use_4,
														free_to_use_5,
														free_to_use_6,
														lfs_sf_account_id,
														lfs_sf_soldto_account_id,
														lfs_sf_ib_id,
														lfs_sf_status_flag,
														lfs_sf_status_message,
														lfs_last_update_date,
														pps_sf_ib_id,
														pps_sf_status_flag,
														pps_sf_status_message,
														pps_last_update_date,
														lfsbu,
														ppsbu,
														dpsbu,
														pps_sf_account_id,
														pps_sf_soldto_account_id,
														insert_flag,
														load_date,
														itembu, sysdate
													  FROM canon_e633_ib_del_tbl
													 WHERE 1 = 1
													   AND NVL(lfs_sf_status_flag,'P') IN ('P','DP' )
													   AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
													canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows archived : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
													COMMIT;
--		EXCEPTION WHEN OTHERS THEN
--			rollback;
--			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'ERROR', 'error while archiving',NULL,NULL, NULL, sqlerrm);
--		END;

		--delete the processed records which are archived from delete table
		--BEGIN
			DELETE
			  FROM canon_e633_ib_del_tbl
			  WHERE 1 = 1
			    AND NVL(lfs_sf_status_flag,'P') IN ('P','DP')
             AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from delete table : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--		EXCEPTION WHEN OTHERS THEN
--			rollback;
--			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'ERROR','Error while deleting the processed records',NULL,NULL, NULL, sqlerrm);
--		END;
--
		--reprocess the errored records
		--BEGIN
			UPDATE canon_e633_ib_del_tbl
			   SET lfs_sf_status_flag = 'D'
			 WHERE lfs_sf_status_flag IN ('E','DE')
			   AND lfsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of lfs rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--		EXCEPTION WHEN OTHERS THEN
--			rollback;
--			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'ERROR','Error while updating to reprocess lfs records',NULL,NULL, NULL, sqlerrm);
--		END;
--
--		BEGIN
			UPDATE canon_e633_ib_del_tbl
			   SET pps_sf_status_flag = 'D'
			 WHERE pps_sf_status_flag IN ('E','DE')
			   AND ppsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of pps rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--		EXCEPTION WHEN OTHERS THEN
--			rollback;
--			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'ERROR','Error while updating to reprocess pps records',NULL,NULL, NULL, sqlerrm);
--		END;


--		BEGIN
			INSERT INTO canon_e633_ib_del_tbl ( svc_mach_mstr_pk,
											install_customer,
											config_nbr,
											config_type,
											commercial_name,
											machine_bus_unit,
											model_series,
											model_group,
											model_group_desc,
											technology_class,
											volume_class,
											marketing_prod_gp,
											serial_number,
											install_date,
											life_cycle_stat,
											instal,
											install_code,
											type_of_sale,
											hw_cont_duration,
											hw_cont_str_date,
											hw_cont_exp_date,
											hw_cont_end_date,
											fs_cont_duration,
											fs_cont_str_date,
											fs_cont_exp_date,
											fs_cont_end_date,
											warr_end_date,
											warranty_duratn,
											warr_start_date,
											date_last_prd_man,
											config_inv_ref,
											cost_allocate_ref,
											service_po_nbr,
											avg_cpy_vol_per_m,
											hrdware_cnt_name,
											supply_type,
											promo_code_1,
											promo_code_2,
											promo_code_3,
											promo_code_4,
											promo_code_5,
											promo_code_6,
											promo_code_7,
											hw_cont_exp_flag,
											marketing_order_t,
											order_type,
											last_meter_read,
											lst_mtr_read_dt,
											warranty_type,
											removal_date,
											order_number,
											invoice_address,
											ofsi_flag,
											inactive_date,
											source,
											selling_bu,
											sold_to_address,
											free_to_use_1,
											free_to_use_2,
											free_to_use_3,
											free_to_use_4,
											free_to_use_5,
											free_to_use_6,
											lfs_sf_account_id,
											lfs_sf_soldto_account_id,
											lfs_sf_ib_id,
											lfs_sf_status_flag,
											lfs_sf_status_message,
											lfs_last_update_date,
											pps_sf_ib_id,
											pps_sf_status_flag,
											pps_sf_status_message,
											pps_last_update_date,
											lfsbu,
											ppsbu,
											dpsbu,
											pps_sf_account_id,
											pps_sf_soldto_account_id,
											insert_flag,
											load_date,
											itembu,
											created_date) SELECT svc_mach_mstr_pk,install_customer,
															config_nbr,
															config_type,
															commercial_name,
															machine_bus_unit,
															model_series,
															model_group,
															model_group_desc,
															technology_class,
															volume_class,
															marketing_prod_gp,
															serial_number,
															install_date,
															life_cycle_stat,
															instal,
															install_code,
															type_of_sale,
															hw_cont_duration,
															hw_cont_str_date,
															hw_cont_exp_date,
															hw_cont_end_date,
															fs_cont_duration,
															fs_cont_str_date,
															fs_cont_exp_date,
															fs_cont_end_date,
															warr_end_date,
															warranty_duratn,
															warr_start_date,
															date_last_prd_man,
															config_inv_ref,
															cost_allocate_ref,
															service_po_nbr,
															avg_cpy_vol_per_m,
															hrdware_cnt_name,
															supply_type,
															promo_code_1,
															promo_code_2,
															promo_code_3,
															promo_code_4,
															promo_code_5,
															promo_code_6,
															promo_code_7,
															hw_cont_exp_flag,
															marketing_order_t,
															order_type,
															last_meter_read,
															lst_mtr_read_dt,
															warranty_type,
															removal_date,
															order_number,
															invoice_address,
															ofsi_flag,
															inactive_date,
															source,
															selling_bu,
															sold_to_address,
															free_to_use_1,
															free_to_use_2,
															free_to_use_3,
															free_to_use_4,
															free_to_use_5,
															free_to_use_6,
															lfs_sf_account_id,
															lfs_sf_soldto_account_id,
															lfs_sf_ib_id,
															(CASE WHEN lfs_sf_ib_id IS NOT NULL AND lfsbu IS NOT NULL THEN 'D'
                                                                  WHEN lfs_sf_ib_id IS NULL THEN 'P' ELSE lfs_sf_status_flag END) lfs_sf_status_flag,
															lfs_sf_status_message,
															lfs_last_update_date,
															pps_sf_ib_id,
															(CASE WHEN pps_sf_ib_id IS NOT NULL AND ppsbu IS NOT NULL THEN 'D'
                                                                  WHEN pps_sf_ib_id IS NULL THEN 'P' ELSE pps_sf_status_flag END) pps_sf_status_flag,
															pps_sf_status_message,
															pps_last_update_date,
															lfsbu,
															ppsbu,
															dpsbu,
															pps_sf_account_id,
															pps_sf_soldto_account_id,
															insert_flag,
															load_date,
															itembu, sysdate
													  FROM canon_e633_ib_stg_tbl ib
													 WHERE 1 = 1
													   AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
                                                       AND life_cycle_stat NOT IN ('REMOVED')
													   AND NOT EXISTS (SELECT 1
																		 FROM svc_mach_mstr smm
																		WHERE smm.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
																		  AND smm.svc_config_mstr_pk = ib.config_nbr
																		  AND smm.ind_cur_loc_num = ib.install_customer
																		  AND smm.glbl_cmpy_cd = 'ADB'
																		  AND smm.ezcancelflag = '0');
					canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows identified for delete : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
					COMMIT;
--		EXCEPTION WHEN OTHERS THEN
--			rollback;
--			canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR','error getting records to delete',NULL,NULL,NULL,sqlerrm);
--		END;
		--delete these records from ib_stg table
		DELETE
		  FROM canon_e633_ib_stg_tbl ib
		 WHERE 1 = 1
		  AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
		   AND NOT EXISTS (SELECT 1
							 FROM svc_mach_mstr smm
							WHERE smm.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
							  AND smm.svc_config_mstr_pk = ib.config_nbr
							  AND smm.ind_cur_loc_num = ib.install_customer
							  AND smm.glbl_cmpy_cd = 'ADB'
							  AND smm.ezcancelflag = '0');
		canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from IB STG : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
		COMMIT;
        	EXCEPTION
            WHEN OTHERS THEN
            retcode := 2;
              errbuff := SQLERRM;
                canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		END delete_ib;
--	  EXCEPTION WHEN OTHERS THEN
--		rollback;
--		canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,sqlerrm);
--    END delete_ib;


		PROCEDURE load_ib(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
		IS
            l_procedure_name         VARCHAR2(60) := 'load_ib';
            l_run_date  DATE    := SYSDATE;
            l_last_run_date DATE;
            l_program_start_date DATE := SYSDATE;

            l_last_run_date_num VARCHAR2(17);
		BEGIN

		retcode := '0';

        BEGIN
           SELECT last_run_dt
             INTO l_last_run_date
             FROM canon_e633_incrdt_tracker_v
            WHERE extract_prg = 'IB';
           IF l_last_run_date IS NULL THEN
              l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
           END IF;
           l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
        END;

		--to reprocess error and unprocessed records
		UPDATE canon_e633_ib_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_ib_id  IS NOT NULL THEN 'U' ELSE 'I' END
              ,lfs_sf_status_message = NULL
          WHERE lfs_sf_status_flag = 'E';
        DBMS_OUTPUT.PUT_LINE('No. of LFS error records updated to U : ' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of LFS error records updated to U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
--        fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U : ' ||sql%rowcount);
        COMMIT;

         UPDATE canon_e633_ib_stg_tbl stg
           SET pps_sf_status_flag =  CASE WHEN pps_sf_ib_id  IS NOT NULL THEN 'U' ELSE 'I' END
              ,pps_sf_status_message = NULL
          WHERE pps_sf_status_flag = 'E' ;
        DBMS_OUTPUT.PUT_LINE('No. of PPS error records updated to U : ' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
--        fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U : ' ||sql%rowcount);
        COMMIT;


        --QC54684 --start
         UPDATE canon_e633_ib_stg_tbl stg
            SET lfs_sf_status_flag = 'I'
                ,pps_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_ib_id IS NULL
              AND lfs_sf_account_id IS NULL
              AND lfsbu IS  NULL
              AND pps_sf_status_flag IS NULL
              AND pps_sf_ib_id IS NULL
              AND pps_sf_account_id IS NULL 
              AND ppsbu IS  NULL;
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' unprocessed records in canon_e633_ib_stg_tbl' ,NULL,NULL,NULL,NULL );--QC54684
          COMMIT;
           --end  -- QC54684
        INSERT
          INTO canon_e633_ib_ser2process_tbl
              (svc_mach_mstr_pk
              )
        SELECT smm.svc_mach_mstr_pk
          FROM svc_mach_mstr smm
         WHERE 1 = 1
           AND smm.glbl_cmpy_cd = 'ADB'
           AND smm.ezcancelflag = '0'
		   AND svc_mach_tp_cd = '1'  --machine only
--           AND to_number(smm.ezuptime) > to_number(l_last_run_date_num)
           AND CAST(to_timestamp (smm.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date --Feb132019
		   AND NOT EXISTS (SELECT 1 --QC56364
							 FROM canon_e633_ib_del_tbl ib
							 WHERE 1 = 1
							   AND ib.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
							   AND ib.config_nbr = smm.svc_config_mstr_pk
							   AND (NVL(ib.lfs_sf_status_flag,'P') <> 'P' AND NVL(ib.pps_sf_status_flag,'P') <> 'P'))

         UNION
        SELECT scm.svc_mach_mstr_pk
          FROM svc_config_mstr scm
         WHERE 1 = 1
           AND scm.glbl_cmpy_cd = 'ADB'
           AND scm.ezcancelflag = '0'
--           AND to_number(scm.ezuptime) > to_number(l_last_run_date_num)
           AND CAST(to_timestamp (scm.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date --Feb132019
         UNION
        SELECT DISTINCT
               dcd.svc_mach_mstr_pk
          FROM ds_contr dc
              ,ds_contr_dtl dcd
         WHERE 1 = 1
           AND dc.glbl_cmpy_cd = 'ADB'
           AND dc.ezcancelflag = '0'
           AND dcd.glbl_cmpy_cd = 'ADB'
           AND dcd.ezcancelflag = '0'
           AND dcd.ds_contr_pk = dc.ds_contr_pk
--           AND(   TO_NUMBER(dcd.ezuptime) > TO_NUMBER(l_last_run_date_num)
--               OR TO_NUMBER(dc.ezuptime) > TO_NUMBER(l_last_run_date_num)
--              )
           AND ( CAST(to_timestamp (dcd.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
                 CAST(to_timestamp (dc.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
              )--Feb132019--Feb132019
         UNION
        SELECT DISTINCT
               sabs.svc_mach_mstr_pk
          FROM svc_adcv_by_ser sabs
         WHERE 1 = 1
           AND sabs.glbl_cmpy_cd = 'ADB'
           AND sabs.ezcancelflag = '0'
--           AND to_number(sabs.ezuptime) > to_number(l_last_run_date_num)
           AND CAST(to_timestamp (sabs.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date --Feb132019
         UNION
        SELECT DISTINCT
               spmr.svc_mach_mstr_pk
          FROM svc_phys_mtr_read spmr
         WHERE 1 = 1
           AND spmr.glbl_cmpy_cd = 'ADB'
           AND spmr.ezcancelflag = '0'
--           AND to_number(spmr.ezuptime) > to_number(l_last_run_date_num)
           AND CAST(to_timestamp (spmr.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date --Feb132019;
		 --QC53489 --start
        UNION
        SELECT DISTINCT smm.svc_mach_mstr_pk
          FROM svc_mach_mstr smm
               ,mdse m
               ,coa_prod p
            WHERE 1 = 1
              AND p.glbl_cmpy_cd = 'ADB'
              AND p.ezcancelflag = '0'
              and smm.ezcancelflag = p.ezcancelflag
              AND smm.glbl_cmpy_cd = p.glbl_cmpy_cd
              AND m.glbl_cmpy_cd = smm.glbl_cmpy_cd
              and m.ezcancelflag = smm.ezcancelflag
              and smm.mdse_cd = m.mdse_cd
              AND m.coa_prod_cd = p.coa_prod_cd
             -- AND smm.svc_mach_mstr_pk = '8113'
              AND CAST(to_timestamp (p.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date;
        --QC53489 --end

       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','inserted ' || sql%rowcount || 'into canon_e633_ib_ser2process_tbl' ,NULL,NULL,NULL,NULL );--QC54684

        MERGE
         INTO canon_e633_ib_ser2process_tbl e633
        USING(SELECT svc_mach_mstr_pk, sld_by_line_biz_tp_cd, svc_by_line_biz_tp_cd -- Start --QC54684
                FROM svc_mach_mstr smm
                WHERE 1 = 1
                AND glbl_cmpy_cd = 'ADB'
                AND ezcancelflag = '0')smm --end --QC54684
           ON(e633.svc_mach_mstr_pk = smm.svc_mach_mstr_pk)
         WHEN MATCHED THEN
       UPDATE
          SET sld_by_line_biz_tp_cd = smm.sld_by_line_biz_tp_cd
             ,svc_by_line_biz_tp_cd = smm.svc_by_line_biz_tp_cd;

		   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','merged ' || sql%rowcount|| 'into canon_e633_ib_ser2process_tbl',NULL,NULL,NULL,NULL );--QC54684
         COMMIT;
	   DELETE
          FROM canon_e633_ib_ser2process_tbl
         WHERE NOT(   NVL(sld_by_line_biz_tp_cd, 'ESS') IN ('PPS','LFS')
                  -- OR NVL(svc_by_line_biz_tp_cd, 'ESS') IN ('PPS','LFS') --as suggested by Chris -12182018
                  );

		   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','deleted ' || sql%rowcount || 'from canon_e633_ib_ser2process_tbl',NULL,NULL,NULL,NULL );--QC54684
          COMMIT;

--           --QC56364 (IBStatus)
--        BEGIN
--            MERGE INTO canon_e633_ib_stg_tbl e633
--                USING (with rmv_dt as (select *
--              from (select row_number() OVER (partition by svc_mach_mstr_pk order by trx_rgtn_dt desc) rn
--                           ,svc_mach_mstr_pk
--                           ,trx_rgtn_dt
--                           ,upd_fld_txt
--                           ,old_val_txt
--                           ,new_val_txt
--                           ,upd_usr_id
--                           ,note_exist_flg
--                    FROM  s21_csa_apps.svc_mach_mstr_trk a
--                    WHERE 1 = 1
--                    AND a.glbl_cmpy_cd = 'ADB'
--                    AND a.ezcancelflag = '0'
--                    --AND a.upd_fld_txt IN ('SVC_MACH_MSTR_STS_CD','SVC_CONFIG_MSTR_PK')
--                    AND a.upd_fld_txt IN ('SVC_MACH_MSTR_STS_CD')
--                    and new_val_txt = 'RMV')
--                where rn = 1)
--    ,config_dt as (select *
--                     from (select row_number() OVER (partition by svc_mach_mstr_pk order by trx_rgtn_dt desc) rn
--                                   ,svc_mach_mstr_pk
--                                   ,trx_rgtn_dt
--                                   ,upd_fld_txt
--                                   ,old_val_txt
--                                   ,new_val_txt
--                                   ,upd_usr_id
--                                   ,note_exist_flg
--                            FROM  s21_csa_apps.svc_mach_mstr_trk a
--                            WHERE 1 = 1
--                            AND a.glbl_cmpy_cd = 'ADB'
--                            AND a.ezcancelflag = '0'
--                            --AND a.upd_fld_txt IN ('SVC_MACH_MSTR_STS_CD','SVC_CONFIG_MSTR_PK')
--                            AND a.upd_fld_txt IN ('SVC_CONFIG_MSTR_PK')
--                            and new_val_txt IS NULL)
--                    where rn = 1)
--SELECT stg.svc_mach_mstr_pk, config_nbr, rd.trx_rgtn_dt, removal_date
--   FROM s21_csa_extn.canon_e633_ib_stg_tbl   stg
--        ,rmv_dt rd
--        ,config_dt cd
--  WHERE stg.removal_date IS NULL
--    AND rd.svc_mach_mstr_pk = stg.svc_mach_mstr_pk
--    AND cd.svc_mach_mstr_pk = stg.svc_mach_mstr_pk
--    AND cd.trx_rgtn_dt >= rd.trx_rgtn_dt
--    AND cd.svc_mach_mstr_pk = rd.svc_mach_mstr_pk
--    AND stg.config_nbr = cd.old_val_txt
--    AND NOT EXISTS (SELECT 1
--                      FROM s21_csa_apps.svc_mach_mstr smm
--                      WHERE 
--                      smm.GLBL_CMPY_CD = 'ADB'
--                      AND smm.EZCANCELFLAG = '0'
--                     AND smm.svc_mach_mstr_pk = stg.svc_mach_mstr_pk
--                        AND smm.svc_config_mstr_pk = stg.config_nbr)
--            ) s21
--        ON ( e633.svc_mach_mstr_pk = s21.svc_mach_mstr_pk AND
--             e633.config_nbr = s21.config_nbr )
--        WHEN MATCHED THEN UPDATE
--            SET e633.removal_date = s21.trx_rgtn_dt
--                ,e633.life_cycle_stat = 'REMOVED' 
--                ,lfs_sf_status_flag = (CASE WHEN lfs_sf_ib_id IS NOT NULL THEN 'U' ELSE lfs_sf_status_flag end)
--                ,pps_sf_status_flag = (CASE WHEN pps_sf_ib_id IS NOT NULL THEN 'U' ELSE pps_sf_status_flag END)
--                ,lfs_last_update_date = (CASE WHEN lfsbu IS NOT NULL THEN sysdate ELSE lfs_last_update_date END)
--                ,pps_last_update_date = (CASE WHEN ppsbu IS NOT NULL THEN sysdate ELSE pps_last_update_date END);
--       dbms_output.put_line(sql%rowcount||' Records updated for removal date.');
--	   canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records updated for RemovalDate',NULL,NULL,NULL,NULL);
--     COMMIT;
--    EXCEPTION WHEN OTHERS THEN
--        dbms_output.put_line('Removal date  update exception '|| sqlerrm);
--        canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR','RemovalDate update',NULL,NULL,NULL,sqlerrm);
--    END;
    
    
--    	  -- start 58123
--    BEGIN
--    merge into canon_e633_ib_stg_tbl e633 using (SELECT DISTINCT  trx.svc_mach_mstr_pk, trx.trx_rgtn_dt,trx.UPD_FLD_TXT, trx.OLD_VAL_TXT,  trx.new_val_txt, stg.install_customer , smm.SVC_CONFIG_MSTR_PK
--  FROM canon_e633_ib_stg_tbl  stg
--       ,svc_mach_mstr_trk trx
--       ,svc_mach_mstr smm
-- WHERE stg.removal_date IS NOT NULL
--   AND trx.trx_rgtn_dt = stg.removal_date
--   AND trx.new_val_txt = stg.install_customer
--   AND trx.svc_mach_mstr_pk  = stg.svc_mach_mstr_pk
--   and trx.UPD_FLD_TXT = 'IND_CUR_LOC_NUM'
--    AND smm.SVC_CONFIG_MSTR_PK =stg.config_nbr
--   --and trx.new_val_txt = 'RMV'
--   and smm.glbl_cmpy_cd = 'ADB'
--   AND smm.ezcancelflag = '0'
--   AND trx.glbl_cmpy_cd = 'ADB'
--   AND trx.ezcancelflag = '0'
----   AND NOT EXISTS (SELECT 1
----                     FROM canon_e633_cust_site_stg_tbl cust
----                     WHERE cust.location_number = stg.install_customer)
--)s21
--                     on (e633.SVC_MACH_MSTR_PK = s21.SVC_MACH_MSTR_PK and e633.CONFIG_NBR = s21.SVC_CONFIG_MSTR_PK)
--                     when matched then update
--                     set e633.INSTALL_CUSTOMER = s21.OLD_VAL_TXT
--                 ,lfs_sf_status_flag = (CASE WHEN lfs_sf_ib_id IS NOT NULL THEN 'U' ELSE lfs_sf_status_flag end)
--                ,pps_sf_status_flag = (CASE WHEN pps_sf_ib_id IS NOT NULL THEN 'U' ELSE pps_sf_status_flag END)
--                ,lfs_last_update_date = (CASE WHEN lfsbu IS NOT NULL THEN sysdate ELSE lfs_last_update_date END)
--                ,pps_last_update_date = (CASE WHEN ppsbu IS NOT NULL THEN sysdate ELSE pps_last_update_date END);
--       dbms_output.put_line(sql%rowcount||' Records updated for warehouse IBlocations.');
--	   canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records updated for warehouse IBlocations',NULL,NULL,NULL,NULL);
--     COMMIT;
--     EXCEPTION WHEN OTHERS THEN
--        dbms_output.put_line('warehouse IBlocations update exception '|| sqlerrm);
--        canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR','warehouse IBlocations update',NULL,NULL,NULL,sqlerrm);
-- END;--QC#58123

    --Now remove this svc_mach_mstr_pk from ib_ser2process_tbl
    BEGIN
    DELETE from canon_e633_ib_ser2process_tbl a
    WHERE svc_mach_mstr_pk IN (SELECT svc_mach_mstr_pk
                                 FROM canon_e633_ib_stg_tbl b
                                 WHERE removal_date IS NOT NULL
                                   AND (lfs_sf_status_flag = 'U' or pps_sf_status_flag = 'U')
                                   AND (lfs_last_update_date BETWEEN l_last_run_date AND sysdate OR pps_last_update_date BETWEEN l_last_run_date AND sysdate)
                                   );
    dbms_output.put_line(sql%rowcount||' removed from serials to process table ');
	   canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' removed from serials to process table',NULL,NULL,NULL,NULL);
     COMMIT;
     EXCEPTION WHEN OTHERS THEN
        dbms_output.put_line('Removal from serials to process table exception '|| sqlerrm);
        canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR','Reoval from ser2process',NULL,NULL,NULL,sqlerrm);
    END;
    --End QC56364
    
		   DBMS_OUTPUT.PUT_LINE('calling Merge...');

            /*** VJ: probably need to filter contracts further to exclude cancelled etc ***/

            MERGE INTO canon_e633_ib_stg_tbl stg
              USING(   WITH incr_ib
                         AS(
                     SELECT *
                       FROM canon_e633_ib_ser2process_tbl
                           ), warranty

                         AS(
                     SELECT DISTINCT
                            incr_ib.svc_mach_mstr_pk
                           ,FIRST_VALUE(dcd.contr_eff_thru_dt) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) warranty_end_date
                           --,FIRST_VALUE(dc.contr_durn_aot||' '||dc.bllg_cycle_uom_cd) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) warranty_duration
                           ,FIRST_VALUE(dc.contr_durn_aot) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) warranty_duration
                           ,FIRST_VALUE(dcd.contr_eff_from_dt) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) warranty_start_date
                       FROM incr_ib
                           ,ds_contr_dtl dcd
                           ,ds_contr dc
                      WHERE 1 = 1
                        AND dc.glbl_cmpy_cd = 'ADB'
                        AND dc.ezcancelflag = '0'
                        AND dcd.glbl_cmpy_cd = 'ADB'
                        AND dcd.ezcancelflag = '0'
                        AND dc.ds_contr_catg_cd = 'WTY'
                        AND dcd.ds_contr_pk = dc.ds_contr_pk
						AND dc.ds_contr_sts_cd NOT IN ('9','4','T') --QC#55179
                        AND incr_ib.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk
                           ), service

                         AS(
                     SELECT DISTINCT
                            incr_ib.svc_mach_mstr_pk
                           ,FIRST_VALUE(dcd.contr_eff_thru_dt) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) service_end_date
                           --,FIRST_VALUE(dc.contr_durn_aot||' '||dc.bllg_cycle_uom_cd) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) service_duration
                           ,FIRST_VALUE(dc.contr_durn_aot) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) service_duration
                           ,FIRST_VALUE(dcd.contr_eff_from_dt) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) service_start_date
                           ,FIRST_VALUE(NVL(dcd.contr_clo_dt, dcd.contr_eff_thru_dt)) OVER(PARTITION BY incr_ib.svc_mach_mstr_pk ORDER BY dcd.contr_eff_thru_dt DESC) service_expire_date
                       FROM incr_ib
                           ,ds_contr_dtl dcd
                           ,ds_contr dc
                      WHERE 1 = 1
                        AND dc.glbl_cmpy_cd = 'ADB'
                        AND dc.ezcancelflag = '0'
                        AND dcd.glbl_cmpy_cd = 'ADB'
                        AND dcd.ezcancelflag = '0'
                        AND dc.ds_contr_catg_cd != 'WTY'
                        AND dcd.ds_contr_pk = dc.ds_contr_pk
						AND dc.ds_contr_sts_cd NOT IN ('9','4','T') --QC#55179
                        AND incr_ib.svc_mach_mstr_pk = dcd.svc_mach_mstr_pk
                           )
                     SELECT DISTINCT NULL legacy_ref_id
                           ,to_char(smm.svc_mach_mstr_pk) svc_mach_mstr_pk
                           --,smm.cur_loc_acct_num install_customer
                           ,smm.ind_cur_loc_num install_customer
                           ,smm.svc_config_mstr_pk config_nbr
                          -- ,smm.ownr_acct_num soldto_account
                           ,smm.ind_bill_to_loc_num soldto_account  --QC57878
                           ,smm.sld_by_line_biz_tp_cd selling_bu
                           ,smm.svc_by_line_biz_tp_cd machine_bus_unit
                           ,mn.t_mdl_nm config_type
                           ,dm.mdl_desc_txt model_series
                           ,dmg.mdl_grp_id model_group
                           ,dmg.mdl_grp_nm model_group_desc
                           ,smm.istl_dt install_date
                           ,smms.svc_mach_mstr_sts_nm life_cycle_stat
                           ,smm.ser_num name
                           ,smm.SVC_MACH_RMV_DT removal_date --IBStatus
                           ,smtt.svc_mach_trx_tp_nm type_of_sale
                           ,warranty.warranty_end_date
                           ,warranty.warranty_duration
                           ,warranty.warranty_start_date
                           ,NULL warranty_type --Qc#56365
                           ,service.service_duration
                           ,service.service_start_date
                           ,service.service_end_date
                           ,service.service_expire_date
                           ,NULL service_po_nbr  --Qc#56365
                           ,smtt.svc_mach_trx_tp_nm order_type --SVC_MACH_TRX_TP_CD'Order_type' order_type
                           ,smm.cpo_ord_num order_number
                           ,'Avg Copy Vol' avg_cpy_vol_per_m
                           ,'LastMtrRd' LAST_METER_READ
                           ,NULL LAST_MTR_READ_DT --updated on June 4 2018
                       FROM incr_ib
                           ,svc_mach_mstr smm
                           ,svc_config_mstr scm
                           ,mdl_nm mn
                           ,ds_mdl dm
                           ,ds_mdl_grp dmg
                           ,svc_mach_mstr_sts smms
                           ,svc_mach_trx_tp smtt
                           ,warranty
                           ,service
                      WHERE smm.glbl_cmpy_cd = 'ADB'
                        AND smm.ezcancelflag = '0'
                        AND scm.glbl_cmpy_cd = 'ADB'
                        AND scm.ezcancelflag = '0'
                        AND mn.ezcancelflag = '0'
                        AND mn.t_glbl_cmpy_cd = 'ADB'
                        AND dm.glbl_cmpy_cd = 'ADB'
                        AND dm.ezcancelflag = '0'
                        AND dmg.glbl_cmpy_cd = 'ADB'
                        AND dmg.ezcancelflag = '0'
                        AND smms.glbl_cmpy_cd = 'ADB'
                        AND smms.ezcancelflag = '0'
                        AND smtt.glbl_cmpy_cd(+) = 'ADB'
                        AND smtt.ezcancelflag(+) = '0'
                        AND incr_ib.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                        AND smm.svc_config_mstr_pk = scm.svc_config_mstr_pk
                        AND scm.mdl_id = mn.t_mdl_id
                        AND scm.mdl_id = dm.mdl_id
                        AND dm.mdl_grp_id = dmg.mdl_grp_id
                        AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_cd
						AND smm.svc_mach_tp_cd = '1' --machine only
                        AND smm.svc_mach_trx_tp_cd = smtt.svc_mach_trx_tp_cd(+)
                        AND smm.svc_mach_mstr_pk = warranty.svc_mach_mstr_pk(+)
                        AND smm.svc_mach_mstr_pk = service.svc_mach_mstr_pk(+)
                        
                        --AND (smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') OR smm.svc_by_line_biz_tp_cd IN ('PPS','LFS')) since this condition is already true for recs in canon_e633_ib_sertoprocess
                         ) s21
             -- ON( stg.svc_mach_mstr_pk = s21.svc_mach_mstr_pk )
			  ON( stg.config_nbr = s21.config_nbr) --QC56364
             WHEN MATCHED THEN UPDATE
                 SET stg.svc_mach_mstr_pk = s21.svc_mach_mstr_pk
                      ,stg.install_customer = (CASE WHEN s21.life_cycle_stat != 'REMOVED' THEN s21.install_customer ELSE stg.install_customer END) --IBStatus
                      ,stg.CONFIG_TYPE = s21.CONFIG_TYPE
                      ,stg.MACHINE_BUS_UNIT = s21.MACHINE_BUS_UNIT
                      ,stg.MODEL_SERIES = s21.MODEL_SERIES
                      ,stg.MODEL_GROUP = s21.MODEL_GROUP
                      ,stg.MODEL_GROUP_DESC = s21.model_group_desc
                      ,stg.SERIAL_NUMBER = s21.name
                      ,stg.INSTALL_DATE = s21.INSTALL_DATE
                      ,stg.LIFE_CYCLE_STAT = s21.LIFE_CYCLE_STAT
                      ,stg.TYPE_OF_SALE = s21.TYPE_OF_SALE
                      ,stg.FS_CONT_DURATION = s21.service_DURATION
                      ,stg.FS_CONT_STR_DATE = s21.service_start_DATE
                      ,stg.FS_CONT_EXP_DATE = s21.service_EXPire_DATE
                      ,stg.FS_CONT_END_DATE = s21.service_END_DATE
                      ,stg.WARR_END_DATE = s21.WARRanty_END_DATE
                      ,stg.WARRANTY_DURATN = s21.WARRANTY_DURATION
                      ,stg.WARR_START_DATE = s21.WARRANTY_START_DATE
                      ,stg.SERVICE_PO_NBR = s21.SERVICE_PO_NBR
                      ,stg.ORDER_TYPE = s21.ORDER_TYPE
                      --,stg.avg_cpy_vol_per_m = s21.avg_cpy_vol_per_m
                      ,stg.LAST_METER_READ = s21.LAST_METER_READ
                      ,stg.LST_MTR_READ_DT = s21.LAST_MTR_READ_DT
                      ,stg.WARRANTY_TYPE = s21.WARRANTY_TYPE
                      ,stg.ORDER_NUMBER = s21.ORDER_NUMBER
                      ,stg.SELLING_BU = s21.SELLING_BU
                      ,stg.SOLD_TO_ADDRESS = s21.SOLDTO_Account
                      ,stg.removal_date = s21.removal_date --IBStatus
                      ,stg.lfs_sf_status_flag = CASE WHEN lfs_sf_ib_id IS NULL THEN 'I' ELSE 'U' END
                      ,stg.lfs_sf_status_message = NULL
                      ,stg.pps_sf_status_flag = CASE WHEN pps_sf_ib_id IS NULL THEN 'I' ELSE 'U' END
                      ,stg.pps_sf_status_message = NULL
                      ,stg.lfs_LAST_UPDATE_DATE = SYSDATE
                      ,stg.pps_last_update_date = SYSDATE
                       
              WHEN NOT MATCHED THEN INSERT (svc_mach_mstr_pk
                                          --,LEGACY_REF_ID
                                          ,install_customer
                                          ,CONFIG_NBR
                                          ,CONFIG_TYPE
                                          ,MACHINE_BUS_UNIT
                                          ,MODEL_SERIES
                                          ,MODEL_GROUP
                                          ,MODEL_GROUP_DESC
                                          ,SERIAL_NUMBER
                                          ,INSTALL_DATE
                                          ,LIFE_CYCLE_STAT
                                          ,FS_CONT_DURATION
                                          ,FS_CONT_STR_DATE
                                          ,FS_CONT_EXP_DATE
                                          ,FS_CONT_END_DATE
                                          ,WARR_END_DATE
                                          ,WARRANTY_DURATN
                                          ,WARR_START_DATE
                                          ,SERVICE_PO_NBR
                                       --   ,AVG_CPY_VOL_PER_M
                                          ,ORDER_TYPE
                                          ,LAST_METER_READ
                                          ,LST_MTR_READ_DT
                                          ,WARRANTY_TYPE
                                          ,REMOVAL_DATE
                                          ,ORDER_NUMBER
                                          ,SELLING_BU
                                          ,SOLD_TO_ADDRESS
                                          ,LFS_SF_IB_ID
                                          ,PPS_SF_IB_ID
                                          ,LFS_SF_STATUS_FLAG
                                          ,PPS_SF_STATUS_FLAG
                                          ,LFS_SF_STATUS_MESSAGE
                                          ,PPS_SF_STATUS_MESSAGE
                                          ,LFS_LAST_UPDATE_DATE
                                          ,PPS_LAST_UPDATE_DATE
                                         -- ,INSERT_FLAG
                                          ) VALUES( s21.svc_mach_mstr_pk
                                                      --,s21.legacy_ref_id
                                                      ,s21.install_customer
                                                      ,s21.CONFIG_NBR
                                                      ,s21.CONFIG_TYPE
                                                      ,s21.MACHINE_BUS_UNIT
                                                      ,s21.MODEL_SERIES
                                                      ,s21.MODEL_GROUP
                                                      ,s21.model_group_desc
                                                      ,s21.name
                                                      ,s21.INSTALL_DATE
                                                      ,s21.LIFE_CYCLE_STAT
                                                      ,s21.SERVICE_DURATION
                                                      ,s21.SERVICE_START_DATE
                                                      ,s21.SERVICE_EXPIRE_DATE
                                                      ,s21.SERVICE_END_DATE
                                                      ,s21.WARRANTY_END_DATE
                                                      ,s21.WARRANTY_DURATION
                                                      ,s21.WARRANTY_START_DATE
                                                      ,s21.SERVICE_PO_NBR
                                                     -- ,s21.AVG_CPY_VOL_PER_M
                                                      ,s21.ORDER_TYPE
                                                      ,s21.LAST_METER_READ
                                                      ,s21.LAST_MTR_READ_DT
                                                      ,s21.WARRANTY_TYPE
                                                      ,s21.removal_date
                                                      ,s21.ORDER_NUMBER
                                                      ,s21.SELLING_BU
                                                      ,s21.soldto_account
                                                      ,NULL
                                                      ,NULL
                                                      ,'I'
                                                      ,'I'
                                                      ,NULL
                                                      ,NULL
                                                      ,SYSDATE
                                                      ,SYSDATE);
                                                     
            DBMS_OUTPUT.PUT_LINE('No. of rows merged : ' ||sql%rowcount);
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of rows merged : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' merged into CANON_E633_IB_STG_TBL');
         COMMIT;
		 --QC#56365 Start
BEGIN
UPDATE canon_e633_ib_stg_tbl e633
  SET service_po_nbr = null
      ,warranty_type = null
      ,e633.lfs_sf_status_flag = (CASE WHEN e633.lfs_sf_ib_id IS NOT NULL THEN 'U' ELSE lfs_sf_status_flag END)
      ,e633.lfs_sf_status_message = NULL
      ,e633.pps_sf_status_flag = (CASE WHEN e633.pps_sf_ib_id IS NOT NULL THEN 'U' ELSE pps_sf_status_flag END)
     ,e633.pps_sf_status_message = NULL
     ,e633.lfs_last_update_date = SYSDATE
     ,e633.pps_last_update_date = SYSDATE
  where lower(service_po_nbr) = 'service_po'
     OR lower(warranty_type) = 'wty_tp';
    dbms_output.put_line(SQL%rowcount||' Records updated for ServicePo# and Warrenty Type.');
    canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records updated for ServicePo# and Warrenty Type',NULL,NULL,NULL,NULL);
   COMMIT;
EXCEPTION WHEN OTHERS THEN
    dbms_output.put_line('for ServicePo# and Warrenty Type Update Exception: ' ||sqlerrm);
    canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR','Update ServicePo# and Warrenty Type: ',NULL,NULL,NULL,SQLERRM);
END;
--end QC#56365

		 --QC53489 --start
		   BEGIN
			MERGE INTO canon_e633_ib_stg_tbl e633
			   USING (SELECT distinct smm.svc_mach_mstr_pk, p.coa_prod_nm
							  FROM mdse m
								   ,coa_prod p
								   ,svc_mach_mstr smm
								   ,canon_e633_ib_stg_tbl e633
							  where m.ezcancelflag = '0'
								AND m.glbl_cmpy_cd = 'ADB'
								AND p.ezcancelflag = m.ezcancelflag
								AND p.glbl_cmpy_cd = m.glbl_cmpy_cd
								AND smm.ezcancelflag = m.ezcancelflag
								AND smm.glbl_cmpy_cd = m.glbl_cmpy_cd
								AND smm.mdse_cd = m.mdse_cd
								AND m.coa_prod_cd = p.coa_prod_cd
								AND smm.svc_mach_mstr_pk = e633.svc_mach_mstr_pk
								and nvl(e633.itembu, 'NA') <> nvl(p.coa_prod_nm, 'NA'))s21
				ON(e633.svc_mach_mstr_pk = s21.svc_mach_mstr_pk)
			WHEN MATCHED THEN UPDATE
				SET itembu = s21.coa_prod_nm
								,lfs_sf_status_flag = (CASE WHEN lfs_sf_ib_id IS NOT NULL THEN 'U' ELSE lfs_sf_status_flag end) --QC54684
				  	            ,pps_sf_status_flag = CASE WHEN pps_sf_ib_id IS NOT NULL THEN 'U' ELSE pps_sf_status_flag END; -- QC54684
--         		,lfs_sf_status_flag = case when lfs_sf_ib_id is not null then 'U' end
--				,pps_sf_status_flag = case when pps_sf_ib_id is not null then 'U' end;
			dbms_output.put_line(sql%rowcount||' Records updated for Item BU.');
		   canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',SQL%rowcount||' records updated for Item BU',NULL,NULL,NULL,NULL);
			  COMMIT;
		   exception
		   WHEN OTHERS
			 THEN
			   dbms_output.put_line('ItemBU update exception '|| sqlerrm);
			   canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR','ItemBU update',NULL,NULL,NULL,sqlerrm);
		   END;
		  --QC53489 -- end

   -- Added for Defect fix

       UPDATE canon_e633_ib_stg_tbl stg
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(INSTALL_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

       UPDATE canon_e633_ib_stg_tbl stg
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(FS_CONT_STR_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

       UPDATE canon_e633_ib_stg_tbl stg
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(FS_CONT_EXP_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

         UPDATE canon_e633_ib_stg_tbl stg
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(FS_CONT_END_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

         UPDATE canon_e633_ib_stg_tbl stg
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(WARR_END_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

         UPDATE canon_e633_ib_stg_tbl stg
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(WARR_START_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

        UPDATE canon_e633_ib_stg_tbl stg
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(LST_MTR_READ_DT) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));


        commit;


         --last_meter_read

         --last_meter_read_dt
    --date validations
	 MERGE INTO canon_e633_ib_stg_tbl a
	  USING  (
				select row_id, INSTALL_DATE, mth, dt, yr
				  from ( select a.rowid row_id
							    ,INSTALL_DATE
								,TO_NUMBER(substr(replace(INSTALL_DATE,'/',''), 5, 2)) mth
								,TO_NUMBER(substr(replace(INSTALL_DATE,'/',''), 7, 2)) dt
								,TO_NUMBER(substr(replace(INSTALL_DATE,'/',''), 1, 4)) yr
						  from canon_e633_ib_stg_tbl a
						  where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
						  --  and length(INSTALL_DATE) >= 10
						)
				 where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
					ELSE 'VALID'
					 END = 'ERROR'   ) b
		ON (a.rowid = b.row_id)
		WHEN MATCHED THEN UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = 'Install Date is Invalid.'
			 ,pps_sf_status_message = 'Install Date is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE  ;

	--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records updated for invalid install date : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

	--date validations
	 MERGE INTO canon_e633_ib_stg_tbl a
	  USING  (
				select row_id, FS_CONT_STR_DATE, mth, dt, yr
				  from ( select a.rowid row_id
							    ,FS_CONT_STR_DATE
								,TO_NUMBER(substr(replace(FS_CONT_STR_DATE,'/',''), 5, 2)) mth
								,TO_NUMBER(substr(replace(FS_CONT_STR_DATE,'/',''), 7, 2)) dt
								,TO_NUMBER(substr(replace(FS_CONT_STR_DATE,'/',''), 1, 4)) yr
						  from canon_e633_ib_stg_tbl a
						  where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
						  --  and length(FS_CONT_STR_DATE) >= 10
						)
				 where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
					ELSE 'VALID'
					 END = 'ERROR'   ) b
		ON (a.rowid = b.row_id)
		WHEN MATCHED THEN UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message || 'FS_CONT_STR_DATE is Invalid.'
			 ,pps_sf_status_message = pps_sf_status_message || 'FS_CONT_STR_DATE is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE  ;

	--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records updated for invalid FS_CONT_STR_DATE : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

	MERGE INTO canon_e633_ib_stg_tbl a
	  USING  (
				select row_id, FS_CONT_EXP_DATE, mth, dt, yr
				  from ( select a.rowid row_id
							    ,FS_CONT_EXP_DATE
								,TO_NUMBER(substr(replace(FS_CONT_EXP_DATE,'/',''), 5, 2)) mth
								,TO_NUMBER(substr(replace(FS_CONT_EXP_DATE,'/',''), 7, 2)) dt
								,TO_NUMBER(substr(replace(FS_CONT_EXP_DATE,'/',''), 1, 4)) yr
						  from canon_e633_ib_stg_tbl a
						  where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
						  --  and length(FS_CONT_EXP_DATE) >= 10
						)
				 where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
					ELSE 'VALID'
					 END = 'ERROR'   ) b
		ON (a.rowid = b.row_id)
		WHEN MATCHED THEN UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message || 'FS_CONT_EXP_DATE is Invalid.'
			 ,pps_sf_status_message = pps_sf_status_message || 'FS_CONT_EXP_DATE is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE  ;

	--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records updated for invalid FS_CONT_EXP_DATE : ' ||sql%rowcount,NULL,NULL,NULL,NULL);


	MERGE INTO canon_e633_ib_stg_tbl a
	  USING  (
				select row_id, FS_CONT_END_DATE, mth, dt, yr
				  from ( select a.rowid row_id
							    ,FS_CONT_END_DATE
								,TO_NUMBER(substr(replace(FS_CONT_END_DATE,'/',''), 5, 2)) mth
								,TO_NUMBER(substr(replace(FS_CONT_END_DATE,'/',''), 7, 2)) dt
								,TO_NUMBER(substr(replace(FS_CONT_END_DATE,'/',''), 1, 4)) yr
						  from canon_e633_ib_stg_tbl a
						  where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
						   -- and length(FS_CONT_END_DATE) >= 10)
						)
				 where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
					ELSE 'VALID'
					 END = 'ERROR'   ) b
		ON (a.rowid = b.row_id)
		WHEN MATCHED THEN UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message || 'FS_CONT_END_DATE is Invalid.'
			 ,pps_sf_status_message = pps_sf_status_message || 'FS_CONT_END_DATE is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE  ;

	--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records updated for invalid FS_CONT_END_DATE : ' ||sql%rowcount,NULL,NULL,NULL,NULL);


	--WARR_END_DATE
	MERGE INTO canon_e633_ib_stg_tbl a
	  USING  (
				select row_id, WARR_END_DATE, mth, dt, yr
				  from ( select a.rowid row_id
							    ,WARR_END_DATE
								,TO_NUMBER(substr(replace(WARR_END_DATE,'/',''), 5, 2)) mth
								,TO_NUMBER(substr(replace(WARR_END_DATE,'/',''), 7, 2)) dt
								,TO_NUMBER(substr(replace(WARR_END_DATE,'/',''), 1, 4)) yr
						  from canon_e633_ib_stg_tbl a
						  where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
						--  AND length(WARR_END_DATE) >= 10
						)
				 where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
					ELSE 'VALID'
					 END = 'ERROR'   ) b
		ON (a.rowid = b.row_id)
		WHEN MATCHED THEN UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message || 'WARR_END_DATE is Invalid.'
			 ,pps_sf_status_message = pps_sf_status_message || 'WARR_END_DATE is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE  ;

	--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records updated for invalid WARR_END_DATE : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

	--WARR_START_DATE
	MERGE INTO canon_e633_ib_stg_tbl a
	  USING  (
				select row_id, WARR_START_DATE, mth, dt, yr
				  from ( select a.rowid row_id
							    ,WARR_START_DATE
								,TO_NUMBER(substr(replace(WARR_START_DATE,'/',''), 5, 2)) mth
								,TO_NUMBER(substr(replace(WARR_START_DATE,'/',''), 7, 2)) dt
								,TO_NUMBER(substr(replace(WARR_START_DATE,'/',''), 1, 4)) yr
						  from canon_e633_ib_stg_tbl a
						  where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
						 --   AND length(WARR_START_DATE) >= 10
						)
				 where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
					ELSE 'VALID'
					 END = 'ERROR'   ) b
		ON (a.rowid = b.row_id)
		WHEN MATCHED THEN UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message || 'WARR_START_DATE is Invalid.'
			 ,pps_sf_status_message = pps_sf_status_message || 'WARR_START_DATE is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE  ;

	--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records updated for invalid WARR_START_DATE : ' ||sql%rowcount,NULL,NULL,NULL,NULL);


         --Avg Copy Volume if meter read is available
         MERGE INTO canon_e633_ib_stg_tbl e633
         USING (SELECT to_char(smm.svc_mach_mstr_pk) svc_mach_mstr_pk
                      ,AVG(sabs.avg_mtr_read_cnt)*30.42 avg_monthly_cpy_vol_total
                  FROM svc_adcv_by_ser sabs
                       ,mtr_lb mtlb
                       ,canon_e633_ib_ser2process_tbl smm
                 WHERE 1 = 1
                   AND sabs.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                   AND mtlb.glbl_cmpy_cd  = sabs.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd     = sabs.mtr_lb_cd
                   AND mtlb.tot_mtr_flg   = 'Y'
                   AND sabs.glbl_cmpy_cd  = 'ADB'
                   AND mtlb.glbl_cmpy_cd  = 'ADB'
                   AND mtlb.ezcancelflag  = '0'
                   AND sabs.ezcancelflag  = '0'
                 GROUP BY smm.svc_mach_mstr_pk
                )b
            ON ( e633.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET avg_cpy_vol_per_m = b.avg_monthly_cpy_vol_total  ;
		   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of rows merged for Avg. copy vol. mtr read available : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

         --Avg Copy Volume if Meter Read is not available
         MERGE INTO canon_e633_ib_stg_tbl e633
         USING (SELECT to_char(smm.svc_mach_mstr_pk) svc_mach_mstr_pk
                       ,SUM(sabs.avg_mtr_read_cnt*30.42) avg_cpy_vol_per_m
                  FROM svc_adcv_by_ser sabs
                       ,mtr_lb mtlb
--                       ,svc_mach_mstr smm
                       ,canon_e633_ib_ser2process_tbl smm
                       ,canon_e633_ib_stg_tbl e633_1
                 WHERE 1 = 1
                   AND e633_1.svc_mach_mstr_pk = to_char(smm.svc_mach_mstr_pk)
                   AND (e633_1.avg_cpy_vol_per_m IS NULL OR e633_1.avg_cpy_vol_per_m = 0)
                   AND sabs.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                   AND sabs.ezcancelflag = '0'
                   AND mtlb.glbl_cmpy_cd  = sabs.glbl_cmpy_cd
                   AND mtlb.mtr_lb_cd     = sabs.mtr_lb_cd
                   AND mtlb.tot_mtr_flg   = 'N'
                   AND sabs.glbl_cmpy_cd  = 'ADB'
--                   AND smm.glbl_cmpy_cd(+)   = 'ADB'
                   AND mtlb.glbl_cmpy_cd  = 'ADB'
                   AND mtlb.ezcancelflag  = '0'
                   AND sabs.ezcancelflag  = '0'
--                   AND smm.ezcancelflag      = '0'
                   GROUP BY smm.svc_mach_mstr_pk
                 )b
            ON ( e633.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET avg_cpy_vol_per_m = b.avg_cpy_vol_per_m;
          canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of rows merged for Avg. copy vol. mtr read not available : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
            COMMIT;

         MERGE INTO canon_e633_ib_stg_tbl e633
         USING(SELECT DISTINCT
                       to_char(FIRST_VALUE(SPMR.SVC_MACH_MSTR_PK) OVER(PARTITION BY SPMR.SVC_MACH_MSTR_PK ORDER BY  SPMR.MTR_READ_DT DESC)) SVC_MACH_MSTR_PK
                      ,FIRST_VALUE(SPMR.READ_MTR_CNT) OVER(PARTITION BY SPMR.svc_mach_mstr_pk ORDER BY  SPMR.MTR_READ_DT DESC, spmr.read_mtr_cnt desc) READ_MTR_CNT
                      ,FIRST_VALUE(SPMR.MTR_READ_DT) OVER(PARTITION BY SPMR.svc_mach_mstr_pk ORDER BY  SPMR.MTR_READ_DT DESC) MTR_READ_DT
                  FROM SVC_PHYS_MTR_READ SPMR
                      ,MTR_LB            MTLB
                       ,canon_e633_ib_ser2process_tbl smm
                 WHERE 1 = 1
                   AND spmr.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                   AND SPMR.GLBL_CMPY_CD     = 'ADB'
                   --AND SPMR.SVC_MACH_MSTR_PK = 1017032
                   AND SPMR.EZCANCELFLAG     = '0'
                   AND SPMR.DS_MTR_READ_TP_GRP_CD ='B'
                   AND MTLB.GLBL_CMPY_CD     = SPMR.GLBL_CMPY_CD
                   AND MTLB.MTR_LB_CD        = SPMR.MTR_LB_CD
                   AND SPMR.GLBL_CMPY_CD     = 'ADB'
                   AND MTLB.EZCANCELFLAG     = '0'
                   AND MTLB.TOT_MTR_FLG = 'Y'
                   AND MTLB.actv_flg = 'Y'
                   --AND MTLB.MTR_LB_TP_CD = 'B'
                   AND TO_NUMBER(TO_CHAR(SYSDATE, 'RRRRMMDD')) BETWEEN mtlb.eff_from_dt AND NVL(mtlb.eff_thru_dt, TO_NUMBER(TO_CHAR(SYSDATE, 'RRRRMMDD')))
              )b
            ON ( e633.svc_mach_mstr_pk = b.svc_mach_mstr_pk)
          WHEN MATCHED THEN
        UPDATE
           SET last_meter_read = b.read_mtr_cnt
              ,lst_mtr_read_dt = TO_CHAR(TO_DATE(b.mtr_read_dt, 'RRRRMMDD'), 'RRRR/MM/DD');
	     canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of rows merged for last mtr read : ' ||sql%rowcount,NULL,NULL,NULL,NULL);


	--LST_MTR_READ_DT
	MERGE INTO canon_e633_ib_stg_tbl a
	  USING  (
				select row_id, LST_MTR_READ_DT, mth, dt, yr
				  from ( select a.rowid row_id
							    ,LST_MTR_READ_DT
								,TO_NUMBER(substr(replace(LST_MTR_READ_DT,'/',''), 5, 2)) mth
								,TO_NUMBER(substr(replace(LST_MTR_READ_DT,'/',''), 7, 2)) dt
								,TO_NUMBER(substr(replace(LST_MTR_READ_DT,'/',''), 1, 4)) yr
						  from canon_e633_ib_stg_tbl a
						  where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
						  -- and length(LST_MTR_READ_DT) >= 10)
						)
				 where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
					ELSE 'VALID'
					 END = 'ERROR'   ) b
		ON (a.rowid = b.row_id)
		WHEN MATCHED THEN UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message || 'LST_MTR_READ_DT is Invalid.'
			 ,pps_sf_status_message = pps_sf_status_message || 'LST_MTR_READ_DT is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE  ;

	--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records updated for invalid LST_MTR_READ_DT : ' ||sql%rowcount,NULL,NULL,NULL,NULL);



    BEGIN
    --UPDATE canon_e633_incrdt_tracker_v
    --   SET LAST_RUN_DT = l_program_start_date
    --  WHERE 1 = 1
    --   AND CODE_TABLE_NAME = 'CANON_E633_INCRDT_TRACKER'
    --   AND EXTRACT_PRG = 'IB';
	--   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','Updated S21 profiles code table',NULL,NULL,NULL,NULL);

    UPDATE canon_s21_cd_val_tbl v
       SET v.val76 = l_program_start_date
     WHERE v.val1 = 'IB'
       AND v.cd_id = (SELECT cd_id
                       FROM CANON_S21_CD_TBL
                       WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
	CANON_E633_SF_ERROR_LOG_PKG.LOG_ERROR(G_PACKAGE_NAME,L_PROCEDURE_NAME,'INFO','Updated S21 profiles code table',NULL,NULL,NULL,NULL);
   EXCEPTION
     WHEN OTHERS
     THEN
       dbms_output.put_line('in update S21 profiles code table exception '|| SQLERRM);
	   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR','update s21 profile code table',NULL,NULL,NULL,SQLERRM);
   END;

   COMMIT;

		EXCEPTION
            WHEN OTHERS THEN
            retcode := 2;
              errbuff := SQLERRM;
                canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		END load_ib;

PROCEDURE load_ib_contacts(retcode OUT VARCHAR2,errbuff OUT VARCHAR2)
AS
    l_procedure_name varchar2(30) := 'load_ib_contacts';
	l_last_run_date DATE;
--    l_program_start_date TIMESTAMP := SYSTIMESTAMP;
    l_program_start_date DATE := SYSDATE;
    l_last_run_date_num VARCHAR2(17);
BEGIN
    retcode := '0';
	BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'IBCONTACT';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;

    DBMS_OUTPUT.PUT_LINE('l_last_run_date: ' ||l_last_run_date);
    DBMS_OUTPUT.PUT_LINE('l_last_run_date_num: ' ||l_last_run_date_num);
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','l_last_run_date: ' ||l_last_run_date,NULL,NULL,NULL,NULL);
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','l_last_run_date_num: ' ||l_last_run_date_num,NULL,NULL,NULL,NULL);

   --QC58464 --start --to reprocess and unprocessed records
         UPDATE canon_e633_ib_cont_stg_tbl  stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_ib_contact_id IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC58464 --start
         UPDATE canon_e633_ib_cont_stg_tbl  stg
            SET pps_sf_status_flag =  'I'
            WHERE  pps_sf_status_flag IS NULL
              AND pps_sf_ib_contact_id IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464


    --to reprocess error and unprocessed records
		UPDATE canon_e633_ib_cont_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_ib_contact_id IS NOT NULL THEN 'U' ELSE 'I' END
               ,lfs_sf_status_message = CASE WHEN lfs_sf_ib_contact_id IS NOT NULL THEN lfs_sf_status_message ELSE NULL END
          WHERE lfs_sf_status_flag = 'E' ;

        DBMS_OUTPUT.PUT_LINE('No. of LFS error records updated to U : ' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of LFS error records updated lto U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
--        fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U : ' ||sql%rowcount);

          UPDATE canon_e633_ib_cont_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_ib_contact_id IS NOT NULL  THEN 'U' ELSE 'I' END
                ,pps_sf_status_message = CASE WHEN pps_sf_ib_contact_id IS NOT NULL THEN pps_sf_status_message ELSE NULL END
          WHERE pps_sf_status_flag = 'E' ;

        DBMS_OUTPUT.PUT_LINE('No. of PPS error records updated to U : ' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
--        fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U : ' ||sql%rowcount);

         COMMIT;

            MERGE INTO canon_e633_ib_cont_stg_tbl stg
               USING (SELECT DISTINCT smcp.SVC_MACH_CTAC_PSN_PK
                               ,smm.svc_mach_mstr_pk
                               ,smm.svc_config_mstr_pk configuration_number
                               ,sct.svc_ctac_tp_cd  CONTACT_PERSON_FUNC_CODE
                               ,sct.svc_ctac_tp_nm  CONTACT_PERSON_FUNC_NAME
                               ,dcp.ds_ctac_pnt_val_txt TELEFONE_NO
                               ,(CASE WHEN (cp.ctac_psn_last_nm IS NOT NULL AND cp.ctac_psn_first_nm IS NOT NULL) THEN cp.ctac_psn_last_nm || ', ' || cp.ctac_psn_first_nm
                                      WHEN (cp.ctac_psn_last_nm IS NOT NULL AND cp.ctac_psn_first_nm IS NULL) THEN cp.ctac_psn_last_nm
                                      WHEN (cp.ctac_psn_last_nm IS NULL AND cp.ctac_psn_first_nm IS NOT NULL) THEN cp.ctac_psn_first_nm ELSE NULL END) MAILING_NAME
                               ,stc.loc_num address_number
                               ,NULL source
                          FROM svc_mach_mstr smm
                               --,svc_config_mstr scm
                               ,svc_ctac_tp sct
                               ,svc_mach_ctac_psn smcp
                               ,ds_ctac_pnt dcp
                               ,ds_ctac_pnt_tp dcpt
                               ,ds_ctac_psn_reln dcpr -- DB Changes
                               ,ctac_psn cp
                               ,ship_to_cust stc
                          WHERE 1 = 1
						    AND smm.svc_mach_tp_cd = 1 -- machine only
                            AND smm.svc_mach_mstr_pk = smcp.svc_mach_mstr_pk
                            AND sct.SVC_CTAC_TP_CD = smcp.SVC_CTAC_TP_CD
                            AND smcp.ds_ctac_pnt_pk = dcp.ds_ctac_pnt_pk
                            AND dcp.ds_ctac_pnt_tp_cd = dcpt.ds_ctac_pnt_tp_cd
                            AND dcpt.ds_ctac_pnt_tp_nm IN ('PHONE - MOBILE','PHONE - WORK','PHONE - ASSISTANT')
                            AND dcp.ctac_psn_pk = cp.ctac_psn_pk -- DB Changes
                            AND cp.ctac_psn_pk = dcpr.ctac_psn_pk -- DB Changes
                            AND dcpr.ctac_tp_cd = 'DELIV_INSTALL' ----newly added on 525 - RG
							AND dcp.ds_ctac_pnt_actv_flg = 'Y' --newly added on 525 - RG
                            AND stc.ship_to_cust_cd = smm.ship_to_cust_cd
--							AND to_number(substr(smcp.ezuptime,1,14)) >= l_last_run_date_num
			--				AND TO_NUMBER(smcp.ezuptime) >= TO_NUMBER(l_last_run_date_num)
			                AND CAST(TO_TIMESTAMP (smcp.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
							AND smm.EZCANCELFLAG = '0'
                           -- AND scm.ezcancelflag = '0'
                            AND smm.glbl_cmpy_cd = 'ADB'
                            --AND scm.glbl_cmpy_cd = 'ADB'
                            AND sct.ezcancelflag = '0'
                            AND smcp.ezcancelflag = '0'
                            AND sct.glbl_cmpy_cd = 'ADB'
                            AND smcp.glbl_cmpy_cd = 'ADB'
                            AND dcp.glbl_cmpy_cd = 'ADB'
                            AND dcp.ezcancelflag = '0'
                            AND dcpt.glbl_cmpy_cd = 'ADB'
                            AND dcpt.ezcancelflag = '0'
                            AND dcpr.glbl_cmpy_cd = 'ADB' -- DB Changes
                            AND dcpr.ezcancelflag = '0' -- DB Changes
                            AND cp.glbl_cmpy_cd = 'ADB'
                            AND cp.ezcancelflag = '0'
                            AND stc.glbl_cmpy_cd = 'ADB'
                            AND stc.ezcancelflag = '0'
							--AND (smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') OR smm.svc_by_line_biz_tp_cd IN ('PPS','LFS'))) ext
							AND smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') ) ext
              --ON (stg.unique_key = SVC_MACH_CTAC_PSN_PK) --(trim(ext.configuration_number) || trim(ext.contact_person_func_code) || trim(ext.machine_id)))
			  ON (stg.unique_key = ext.configuration_number || ext.contact_person_func_code)
             WHEN MATCHED THEN UPDATE SET
                      stg.svc_mach_mstr_pk = ext.svc_mach_mstr_pk
                      ,stg.ADDRESS_NUMBER = trim(ext.ADDRESS_NUMBER)
                      ,stg.MAILING_NAME = trim(ext.MAILING_NAME)
                      ,stg.TELEFONE_NO = trim(ext.TELEFONE_NO)
                      ,stg.SOURCE = trim(ext.SOURCE)
                      ,stg.CONTACT_PERSON_FUNC_NAME = trim(ext.CONTACT_PERSON_FUNC_NAME)
                      ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_ib_contact_id IS NULL THEN 'I' ELSE 'U' END)
                      ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_ib_contact_id IS NULL THEN 'I' ELSE 'U' END)
                      ,stg.lfs_sf_status_message = NULL
                      ,stg.pps_sf_status_message = NULL
                      ,stg.lfs_last_update_date = SYSDATE
                      ,stg.pps_last_update_date = SYSDATE
            WHEN NOT MATCHED THEN INSERT ( CONFIGURATION_NUMBER
                                            ,svc_mach_mstr_pk
                                          ,CONTACT_PERSON_FUNC_CODE
                                          ,CONTACT_PERSON_FUNC_NAME
                                          ,ADDRESS_NUMBER
                                          ,MAILING_NAME
                                          ,TELEFONE_NO
                                          ,SOURCE
                                          ,lfs_sf_status_flag
                                          ,pps_sf_status_flag
                                          ,lfs_sf_status_message
                                          ,pps_sf_status_message
                                          ,lfs_last_update_date
                                          ,pps_last_update_date
                                          ,unique_key) VALUES (trim(ext.CONFIGURATION_NUMBER)
                                                                ,ext.svc_mach_mstr_pk
                                                                          ,trim(ext.CONTACT_PERSON_FUNC_CODE)
                                                                          ,trim(ext.CONTACT_PERSON_FUNC_NAME)
                                                                          ,trim(ext.ADDRESS_NUMBER)
                                                                          ,trim(ext.MAILING_NAME)
                                                                          ,trim(ext.TELEFONE_NO)
                                                                          ,trim(ext.SOURCE)
                                                                          ,'I'
                                                                          ,'I'
                                                                          ,NULL
                                                                          ,NULL
                                                                          ,SYSDATE
                                                                          ,SYSDATE
                                                                         -- ,SVC_MACH_CTAC_PSN_PK);
                                                                         ,ext.configuration_number || ext.contact_person_func_code );
--                              fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' merged into stg table.');
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' merged into stg table.',NULL,NULL,NULL,NULL);

UPDATE canon_e633_ib_cont_stg_tbl
   SET lfs_sf_status_flag = 'E'
         ,pps_sf_Status_flag = 'E'
         ,lfs_sf_status_message = 'Mailing Name missing.'
         ,pps_sf_status_message = 'Mailing Name missing.'
         ,lfs_last_update_date = SYSDATE
         ,pps_last_update_date = SYSDATE
         WHERE 1 = 1
         and mailing_name is null
         and pps_sf_Status_flag  IN ('I', 'E', 'U');
--         fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' marked ''E'' for Mailing Name Missing.');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' marked ''E'' for Mailing Name Missing.',NULL,NULL,NULL,NULL);

	BEGIN
    UPDATE canon_s21_cd_val_tbl v
       SET v.val76 = l_program_start_date
     WHERE v.val1 = 'IBCONTACT'
       AND v.cd_id = (SELECT cd_id
                       FROM canon_s21_cd_tbl
                       WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
   EXCEPTION
     WHEN OTHERS THEN
       dbms_output.put_line('update IBCONTACT last run date exception: '|| SQLERRM);
	   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);

   END;

COMMIT;

EXCEPTION
  WHEN OTHERS THEN
        retcode := '0';
        errbuff := SQLERRM;
--        fnd_file.put_line(fnd_file.log, l_procedure_name || ': ' || SQLERRM);
	   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END load_ib_contacts;

PROCEDURE update_dependIDs_4_ib(p_handler_name IN varchar2, x_return_status OUT VARCHAR2)
IS
    l_procedure_name VARCHAR2(30) := 'update_dependIDs_4_ib';
    l_isMatched boolean := false; --QC57878
BEGIN
        x_return_status := 'S';

		--reset the BUs for error records QC26137-3
    UPDATE canon_e633_ib_stg_tbl
       SET lfsbu = NULL
      WHERE lfs_sf_status_flag IN ('I', 'U')
	    AND lfsbu is not null
        AND lfs_sf_ib_id is null;

		--QC26137-3
        UPDATE canon_e633_ib_stg_tbl
       SET ppsbu = NULL
      WHERE pps_sf_status_flag IN ('I', 'U')
	    AND ppsbu is not null
        AND pps_sf_ib_id is null;

		UPDATE canon_e633_ib_stg_tbl ib
           SET lfsbu = 'LFS'
         WHERE 1 = 1
         --  AND(machine_bus_unit IN ('LFS') OR SELLING_BU IN ('LFS')) -- as suggested by Chris 12202018
		   AND(SELLING_BU IN ('LFS'))
           AND(lfs_sf_status_flag IN ('I','U')  OR pps_sf_status_flag IN ('I','U'));
		   --canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of rows PPS account Id''s updated : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
--           fnd_file.put_line(fnd_file.log, 'No. of rows updated for BU LFS for DG / ES : ' ||sql%rowcount);

        UPDATE canon_e633_ib_stg_tbl ib
           SET ppsbu = 'PPS'
         WHERE 1 = 1
        --   AND(machine_bus_unit IN ('PPS') OR SELLING_BU IN ('PPS')) --as suggested by Chris 12202018
		   AND(SELLING_BU IN ('PPS') )
           AND(lfs_sf_status_flag IN ('I','U')  OR pps_sf_status_flag IN ('I','U'));
--           fnd_file.put_line(fnd_file.log, 'No. of rows updated for BU PPS for PS : ' ||sql%rowcount);

		IF p_handler_name like '%LFS%' THEN
        --first check if the install customer is in SFDC --QC57878
        UPDATE canon_e633_ib_stg_tbl ib
           SET ib.lfs_sf_account_id = null
        WHERE ib.lfs_sf_status_flag in ('I','U')
          AND ib.lfsbu IS NOT NULL
          AND NOT EXISTS (SELECT 1
                            FROM canon_e633_cust_site_stg_tbl cust
                           WHERE cust.location_number = ib.install_customer
                             AND lfs_sf_account_id IS NOT NULL);

        --check SoldTo account --QC57878
        UPDATE canon_e633_ib_stg_tbl ib
           SET ib.lfs_sf_soldto_account_id = null
           WHERE ib.lfs_sf_status_flag in ('I','U')
             AND ib.lfsbu IS NOT NULL
             AND NOT EXISTS (SELECT 1
                               FROM canon_e633_cust_site_stg_tbl cust
                              WHERE cust.location_number = ib.sold_to_address
                                AND lfs_sf_account_id IS NOT NULL);

			--update the LFS account_id
         MERGE INTO canon_e633_ib_stg_tbl ib
            USING canon_e633_cust_site_stg_tbl cust
              ON (ib.install_customer = cust.LOCATION_NUMBER
                    AND ib.lfs_sf_status_flag IN ('I','U')
					AND ib.lfsbu is not null --QC26137-3
                 )
           WHEN MATCHED THEN UPDATE SET
                ib.lfs_sf_account_id = cust.LFS_SF_account_ID;

               -- ,ib.LFSBU = cust.lfsbu;
          DBMS_OUTPUT.PUT_LINE('No. of rows LFS account Id''s updated : ' ||sql%rowcount);
		  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of rows LFS account Id''s updated : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
--          fnd_file.put_line(fnd_file.log, 'No. of rows LFS account Id''s updated : ' ||sql%rowcount);
         COMMIT;

		 MERGE INTO canon_e633_ib_stg_tbl ib
            USING canon_e633_cust_site_stg_tbl cust
              ON (ib.SOLD_TO_ADDRESS = cust.LOCATION_NUMBER
                    AND ib.lfs_sf_status_flag IN ('I','U')
					AND ib.lfsbu is not null --QC26137-3
                 )
           WHEN MATCHED THEN UPDATE SET
                ib.lfs_sf_soldto_account_id = cust.LFS_SF_account_ID;

               -- ,ib.LFSBU = cust.lfsbu;
          DBMS_OUTPUT.PUT_LINE('No. of rows LFS Sold To account Id''s updated : ' ||sql%rowcount);
--          fnd_file.put_line(fnd_file.log, 'No. of rows LFS Sold To account Id''s updated : ' ||sql%rowcount);
         COMMIT;

		 UPDATE canon_e633_ib_stg_tbl
            SET lfs_sf_status_flag = NULL
               ,lfs_SF_account_ID = NULL
               ,lfs_sf_status_message = NULL
          WHERE 1 = 1
            AND lfsbu IS NULL
            AND lfs_sf_status_flag IN ('I','U');
--            fnd_file.put_line(fnd_file.log, 'No. of rows updated for LFS BU being blank : ' ||sql%rowcount);

		--mark the records as E if sf_account_id is not available
         UPDATE canon_e633_ib_stg_tbl
            SET lfs_sf_status_flag = 'E'
               ,lfs_sf_status_message = 'Unable to lookup account LFS SFDC ID'
          WHERE lfs_sf_status_flag IN ('I','U')
            AND lfsbu IS NOT NULL
            AND lfs_sf_account_id IS NULL;
--            fnd_file.put_line(fnd_file.log, 'No. of rows updated for unable to lookup LFS account SFDC ID : ' ||sql%rowcount);

		END IF;

		IF p_handler_name like '%PPS%' THEN

         --first check if the install customer is in SFDC --QC57878
        UPDATE canon_e633_ib_stg_tbl ib
           SET ib.pps_sf_account_id = null
        WHERE ib.pps_sf_status_flag in ('I','U')
          AND ib.ppsbu IS NOT NULL
          AND NOT EXISTS (SELECT 1
                            FROM canon_e633_cust_site_stg_tbl cust
                           WHERE cust.location_number = ib.install_customer
                             AND pps_sf_account_id IS NOT NULL);

        --check SoldTo account --QC57878
        UPDATE canon_e633_ib_stg_tbl ib
           SET ib.pps_sf_soldto_account_id = null
           WHERE ib.pps_sf_status_flag in ('I','U')
             AND ib.ppsbu IS NOT NULL
             AND NOT EXISTS (SELECT 1
                               FROM canon_e633_cust_site_stg_tbl cust
                              WHERE cust.location_number = ib.sold_to_address
                                AND pps_sf_account_id IS NOT NULL);
			--update the PPS account_id
         MERGE INTO canon_e633_ib_stg_tbl ib
            USING canon_e633_cust_site_stg_tbl cust
              ON (ib.install_customer = cust.LOCATION_NUMBER
                    AND ib.pps_sf_status_flag IN ('I','U')
					and ib.ppsbu is not null)--QC26137-3
           WHEN MATCHED THEN UPDATE SET
                ib.pps_sf_account_id = cust.PPS_SF_account_ID;
               -- ,ib.ppsbu = cust.ppsbu;
          DBMS_OUTPUT.PUT_LINE('No. of rows PPS account Id''s updated : ' ||sql%rowcount);
		  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of rows PPS account Id''s updated : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
--          fnd_file.put_line(fnd_file.log, 'No. of rows PPS account Id''s updated : ' ||sql%rowcount);
         COMMIT;

		 MERGE INTO canon_e633_ib_stg_tbl ib
            USING canon_e633_cust_site_stg_tbl cust
              ON (ib.SOLD_TO_ADDRESS = cust.location_number
                    AND ib.pps_sf_status_flag IN ('I','U')
					and ib.ppsbu is not null)--QC26137-3
           WHEN MATCHED THEN UPDATE SET
                ib.pps_sf_soldto_account_id = cust.PPS_SF_account_ID;
               -- ,ib.ppsbu = cust.ppsbu;
          DBMS_OUTPUT.PUT_LINE('No. of rows PPS Sold To account Id''s updated : ' ||sql%rowcount);
--          fnd_file.put_line(fnd_file.log, 'No. of rows PPS Sold To account Id''s updated : ' ||sql%rowcount);
         COMMIT;

		 UPDATE canon_e633_ib_stg_tbl
            SET Pps_sf_status_flag = NULL
               ,PPS_SF_account_ID = NULL
               ,pps_sf_status_message = NULL
          WHERE 1 = 1
            AND ppsbu IS NULL
            AND pps_sf_status_flag IN ('I', 'U');
--            fnd_file.put_line(fnd_file.log, 'No. of rows updated for PPS BU being blank : ' ||sql%rowcount);

         UPDATE canon_e633_ib_stg_tbl
            SET pps_sf_status_flag = 'E'
               ,pps_sf_status_message = 'Unable to lookup account PPS SFDC ID'
          WHERE pps_sf_status_flag IN ('I','U')
            AND ppsbu IS NOT NULL         
            AND pps_sf_account_id IS NULL;
--            AND LIFE_CYCLE_STAT not in ('REMOVED');--IB STATUS
--          fnd_file.put_line(fnd_file.log, 'No. of rows updated for unable to lookup PPS account SFDC ID : ' ||sql%rowcount);

        COMMIT;

		END IF;

EXCEPTION
    WHEN OTHERS THEN
    x_return_status := 'E';
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END update_dependIDs_4_ib;

PROCEDURE load_config_ib(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
IS
    l_procedure_name  VARCHAR2(20) := 'load_config_ib';
    l_last_run_date DATE;
--    l_program_start_date TIMESTAMP := SYSTIMESTAMP;
    l_program_start_date DATE := SYSDATE;
    l_last_run_date_num VARCHAR2(17);
BEGIN

    retcode := '0';
    BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'IBCONFIG';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;

    --QC58464 --start --to reprocess and unprocessed records
         UPDATE canon_e633_config_stg_tbl stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_config_id IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC58464 --start
         UPDATE canon_e633_config_stg_tbl stg
            SET pps_sf_status_flag =  'I'
            WHERE  pps_sf_status_flag IS NULL
              AND pps_sf_config_id IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464
    --to reprocess error and unprocessed records
		UPDATE canon_e633_config_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_config_id IS NOT NULL THEN 'U' ELSE 'I' END
              ,lfs_sf_status_message = NULL
          WHERE lfs_sf_status_flag = 'E';

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' lfs error records reset.');
            dbms_output.put_line(SQL%ROWCOUNT||' lfs error records reset.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' lfs error records reset.',NULL,NULL,NULL,NULL);


          UPDATE canon_e633_config_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_config_id IS NOT NULL THEN 'U' ELSE 'I' END
              ,pps_sf_status_message = NULL
          WHERE pps_sf_status_flag = 'E';
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' pps error records reset.',NULL,NULL,NULL,NULL);
--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' pps error records reset.');
            dbms_output.put_line(SQL%ROWCOUNT||' pps error records reset.');

         COMMIT;

	     MERGE INTO canon_e633_config_stg_tbl stg
          USING (SELECT smm.svc_config_mstr_pk config_number
					   ,(smm.svc_config_mstr_pk || ' '|| smm.svc_mach_sq_num)  name
					   ,row_number() over (partition by smm.svc_config_mstr_pk order by smm.svc_mach_tp_cd, smm.svc_mach_sq_num) as component_seq_number
					   ,smms.svc_mach_mstr_sts_nm lifecycle_status
					   ,m.mdse_nm component_type
					   ,mit.MDSE_ITEM_TP_NM item_type_code
					   ,smm.istl_dt  insdat
					   ,NULL technical_exch_seq_number
					   ,smm.svc_mach_rmv_dt rmvdat
					   ,m.mdse_desc_short_txt COMMERCIAL_NAME_LANG_1 -- DB Changes
					   ,smm.cur_loc_acct_num INSTALLATION_CUSTOMER_NO
					   ,smm.svc_config_mstr_pk||(row_number() over (partition by smm.svc_config_mstr_pk order by smm.svc_mach_tp_cd, smm.svc_mach_sq_num))  unique_key
					   ,null source
					   ,null LAST_AMEND_USER_ID
					   ,null AMSDAT
					   ,smm.ser_num serial_no
					   ,smm.svc_mach_mstr_pk
				   FROM svc_mach_mstr smm
						,svc_config_mstr scm
--						,ds_mdse_info  dmi -- DB Changes
						,mdse_item_tp mit
						,svc_mach_mstr_sts smms
						,mdse m
					WHERE scm.svc_config_mstr_pk = smm.svc_config_mstr_pk
--					  AND smm.mdse_cd = dmi.mdse_cd -- DB Changes
					  AND smm.mdse_cd = m.mdse_cd
					  AND smm.svc_mach_mstr_sts_cd IN ('INSTL','W4I')
					  AND smm.svc_mach_mstr_sts_cd = smms.svc_mach_mstr_sts_Cd
					  AND mit.mdse_item_tp_cd = m.mdse_item_tp_cd
					--  AND (smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') OR smm.svc_by_line_biz_tp_cd IN ('PPS','LFS'))
					  AND (smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') ) -- as suggested by Chris 12202018
					  AND (TO_NUMBER(scm.ezuptime) >= TO_NUMBER(l_last_run_date_num)
                          )
					  AND smm.EZCANCELFLAG = '0'
					  AND scm.ezcancelflag = '0'
--					  AND dmi.ezcancelflag = '0'
					  AND smm.glbl_cmpy_cd = 'ADB'
					  AND scm.glbl_cmpy_cd = 'ADB'
--					  AND dmi.glbl_cmpy_cd = 'ADB' -- DB Changes
					  AND mit.glbl_cmpy_cd = 'ADB'
					  AND mit.ezcancelflag = '0'
					  AND m.glbl_cmpy_cd = 'ADB'
					  AND m.ezcancelflag = '0'
					  AND smms.ezcancelflag = '0'
					  AND smms.glbl_cmpy_cd = 'ADB'
                )s21
          ON (stg.UNIQUE_key = s21.unique_key)
          WHEN MATCHED THEN UPDATE
            SET stg.serial_no = s21.serial_no
			  ,stg.component_seq_number = s21.component_seq_number
              ,stg.LIFECYCLE_STATUS = s21.LIFECYCLE_STATUS
              ,stg.COMPONENT_TYPE  = s21.COMPONENT_TYPE
              ,stg.ITEM_TYPE_CODE = s21.ITEM_TYPE_CODE
              ,stg.INSDAT = s21.INSDAT
              ,stg.AMSDAT = s21.AMSDAT
              ,stg.LAST_AMEND_USER_ID = s21.LAST_AMEND_USER_ID
              ,stg.TECHNICAL_EXCH_SEQ_NUMBER = s21.TECHNICAL_EXCH_SEQ_NUMBER
              ,stg.RMVDAT = s21.RMVDAT
              ,stg.INSTALLATION_CUSTOMER_NO = s21.INSTALLATION_CUSTOMER_NO
              ,stg.COMMERCIAL_NAME_LANG_1 = s21.COMMERCIAL_NAME_LANG_1
              ,stg.source = s21.source
              ,stg.svc_mach_mstr_pk = s21.svc_mach_mstr_pk
              /*,stg.lfs_sf_ib_id = (SELECT lfs_sf_ib_id
                                     FROM canon_E633_ib_stg_tbl
                                    WHERE trim(config_nbr) = trim(ext.config_number) )
              ,stg.pps_sf_ib_id = (SELECT pps_sf_ib_id
                                    FROM canon_e633_ib_stG_tbl
                                   WHERE trim(config_nbr) = trim(ext.config_number)) **/
              ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_config_id IS NULL THEN 'I' ELSE 'U' END)
              ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_config_id IS NULL THEN 'I' ELSE 'U' END)
              ,stg.lfs_sf_status_message = NULL
              ,stg.pps_sf_status_message = NULL
              ,stg.lfs_last_update_date = SYSDATE
              ,stg.pps_last_update_date = SYSDATE
          WHEN NOT MATCHED THEN INSERT(CONFIG_NUMBER
                                      ,COMPONENT_SEQ_NUMBER
                                      ,SERIAL_NO
                                      ,svc_mach_mstr_pk
                                      ,LIFECYCLE_STATUS
                                      ,COMPONENT_TYPE
                                      ,ITEM_TYPE_CODE
                                      ,INSDAT
                                      ,AMSDAT
                                      ,LAST_AMEND_USER_ID
                                      ,TECHNICAL_EXCH_SEQ_NUMBER
                                      ,RMVDAT
                                      ,INSTALLATION_CUSTOMER_NO
                                      ,COMMERCIAL_NAME_LANG_1
                                      ,SOURCE
                                      ,LFS_SF_IB_ID
                                      ,PPS_SF_IB_ID
                                      ,LFS_SF_STATUS_FLAG
                                      ,PPS_SF_STATUS_FLAG
                                      ,LFS_SF_STATUS_MESSAGE
                                      ,PPS_SF_STATUS_MESSAGE
                                      ,LFS_LAST_UPDATE_DATE
                                      ,PPS_LAST_UPDATE_DATE
                                      ,UNIQUE_key ) VALUES (s21.CONFIG_NUMBER
                                                                  ,s21.COMPONENT_SEQ_NUMBER
                                                                  ,s21.SERIAL_NO
                                                                  ,s21.svc_mach_mstr_pk
                                                                  ,s21.LIFECYCLE_STATUS
                                                                  ,s21.COMPONENT_TYPE
                                                                  ,s21.ITEM_TYPE_CODE
                                                                  ,s21.INSDAT
                                                                  ,s21.AMSDAT
                                                                  ,s21.LAST_AMEND_USER_ID
                                                                  ,s21.TECHNICAL_EXCH_SEQ_NUMBER
                                                                  ,s21.RMVDAT
                                                                  ,s21.INSTALLATION_CUSTOMER_NO
                                                                  ,s21.COMMERCIAL_NAME_LANG_1
                                                                  ,s21.SOURCE
--                                                                  ,(SELECT lfs_sf_ib_id
--                                                                     FROM canon_e633_ib_stg_tbl ib
--                                                                    WHERE ib.config_nbr = ext.config_number)
                                                                  ,NULL
                                                                  ,NULL
                                                                  ,'I'
                                                                  ,'I'
                                                                  ,NULL
                                                                  ,NULL
                                                                  ,SYSDATE
                                                                  ,SYSDATE
                                                                  ,s21.unique_key);
--               fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' merged into CANON_E633_IB_STG_TBL');
               dbms_output.put_line(SQL%ROWCOUNT||' merged into CANON_E633_CONFIG_STG_TBL');
			   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' merged into CANON_E633_CONFIG_STG_TBL.',NULL,NULL,NULL,NULL);



-- Added  Update statement for  Defect fix


UPDATE canon_e633_config_stg_tbl
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(INSDAT) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

UPDATE canon_e633_config_stg_tbl
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(AMSDAT) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));


UPDATE canon_e633_config_stg_tbl
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(RMVDAT)< 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));


 commit;



    --date validations
	MERGE INTO canon_e633_config_stg_tbl a
	USING  ( select row_id, INSDAT, mth, dt, yr
			   from ( select a.rowid row_id
							 ,INSDAT
							 ,TO_NUMBER(substr(replace(INSDAT,'/',''), 5, 2)) mth
							 ,TO_NUMBER(substr(replace(INSDAT,'/',''), 7, 2)) dt
							 ,TO_NUMBER(substr(replace(INSDAT,'/',''), 1, 4)) yr
						from canon_e633_config_stg_tbl a
					   where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
					   --  and length(INSDAT) >= 10
					 )
			  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
						 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						ELSE 'VALID'
					  END = 'ERROR'   ) b
			 ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = 'INSDAT Date is Invalid.'
				 ,pps_sf_status_message = 'INSDAT Date is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;
		--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
	 dbms_output.put_line(SQL%ROWCOUNT||' records updated for invalid INSDAT');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for invalid INSDAT.',NULL,NULL,NULL,NULL);

	--date validations
	MERGE INTO canon_e633_config_stg_tbl a
	USING  ( select row_id, AMSDAT, mth, dt, yr
			   from ( select a.rowid row_id
							 ,AMSDAT
							 ,TO_NUMBER(substr(replace(AMSDAT,'/',''), 5, 2)) mth
							 ,TO_NUMBER(substr(replace(AMSDAT,'/',''), 7, 2)) dt
							 ,TO_NUMBER(substr(replace(AMSDAT,'/',''), 1, 4)) yr
						from canon_e633_config_stg_tbl a
					   where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
					  --   and length(AMSDAT) >= 10
					 )
			  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
						 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						ELSE 'VALID'
					  END = 'ERROR'   ) b
			 ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = 'AMSDAT Date is Invalid.'
				 ,pps_sf_status_message = 'AMSDAT Date is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;
		--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid AMSDAT date');
	 dbms_output.put_line(SQL%ROWCOUNT||' records updated for invalid AMSDAT.');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for invalid AMSDAT.',NULL,NULL,NULL,NULL);


	--date validations
	MERGE INTO canon_e633_config_stg_tbl a
	USING  ( select row_id, RMVDAT, mth, dt, yr
			   from ( select a.rowid row_id
							 ,RMVDAT
							 ,TO_NUMBER(substr(replace(RMVDAT,'/',''), 5, 2)) mth
							 ,TO_NUMBER(substr(replace(RMVDAT,'/',''), 7, 2)) dt
							 ,TO_NUMBER(substr(replace(RMVDAT,'/',''), 1, 4)) yr
						from canon_e633_config_stg_tbl a
					   where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
					  --   and length(RMVDAT) >= 10
					 )
			  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
						 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						ELSE 'VALID'
					  END = 'ERROR'   ) b
			 ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = 'RMVDAT Date is Invalid.'
				 ,pps_sf_status_message = 'RMVDAT Date is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;
		--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid RMVDAT date');
	 dbms_output.put_line(SQL%ROWCOUNT||' records updated for invalid RMVDAT.');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for invalid RMVDAT.',NULL,NULL,NULL,NULL);


        BEGIN
            UPDATE canon_s21_cd_val_tbl v
--               SET v.val76 = TO_DATE(TO_CHAR(l_program_start_date,'YYYY-MON-DD HH24:MI:SS'),'YYYY-MON-DD HH24:MI:SS')
               SET v.val76 = l_program_start_date
             WHERE v.val1 = 'IBCONFIG'
               AND v.cd_id = (SELECT cd_id
                               FROM canon_s21_cd_tbl
                               WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
        EXCEPTION
         WHEN OTHERS THEN
           dbms_output.put_line('update IBCONFIG last run date exception: '|| SQLERRM);
		   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
        END;

           COMMIT;

EXCEPTION
    WHEN OTHERS THEN
    retcode := 2;
    errbuff := SQLERRM;
--  fnd_file.put_line(fnd_file.log, l_procedure_name || ': ' || SQLERRM);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END load_config_ib;


PROCEDURE load_meter_reads(retcode OUT VARCHAR2,errbuff OUT VARCHAR2)
IS
            l_procedure_name         VARCHAR2(60) := 'load_meter_reads';
            l_run_date  DATE    := SYSDATE;
            l_last_run_date DATE;
--            l_program_start_date TIMESTAMP := SYSTIMESTAMP;
            l_program_start_date DATE := SYSDATE;
            l_last_run_date_num VARCHAR2(17);

BEGIN
        retcode := '0';

         BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'METERREADS';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;

	   --QC58464 --start reprocess
         UPDATE canon_e633_meter_reads_stg_tbl  stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND LFS_SF_MTR_READ_ID  IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_meter_reads_stg_tbl ' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC58464 --start
         UPDATE canon_e633_meter_reads_stg_tbl  stg
            SET pps_sf_status_flag = 'I'
            WHERE  pps_sf_status_flag IS NULL
              AND PPS_SF_MTR_READ_ID IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_meter_reads_stg_tbl ' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

--QC56364 archive the records that are associated with IB location change
	INSERT INTO canon_e633_meter_reads_stg_arc (meter_read_system_number
                                               ,invoice_number
                                               ,SERIAL_NO
                                               ,svc_mach_mstr_pk
                                               ,CONFIGURATION_NUMBER
                                               ,REGISTRATION_DATE
                                               ,invoice_period
                                               ,end_read
                                               ,start_read
                                               ,rate
                                               ,billable_usage
                                               ,actual_usage
                                               ,multiplier
												,LFS_SF_IB_ID
												,LFS_SF_ACCOUNT_ID
												,LFS_SF_MTR_READ_ID
												,LFS_SF_STATUS_FLAG
												,LFS_SF_STATUS_MESSAGE
												,LFS_LAST_UPDATE_DATE
												,PPS_SF_IB_ID
												,PPS_SF_ACCOUNT_ID
												,PPS_SF_MTR_READ_ID
												,PPS_SF_STATUS_FLAG
												,PPS_SF_STATUS_MESSAGE
												,PPS_LAST_UPDATE_DATE
												,LFSBU
												,DPSBU
												,PPSBU
												,created_date) SELECT meter_read_system_number
                                                                   ,invoice_number
                                                                   ,SERIAL_NO
                                                                   ,svc_mach_mstr_pk
                                                                   ,CONFIGURATION_NUMBER
                                                                   ,REGISTRATION_DATE
                                                                   ,invoice_period
                                                                   ,end_read
                                                                   ,start_read
                                                                   ,rate
                                                                   ,billable_usage
                                                                   ,actual_usage
                                                                   ,multiplier
																,LFS_SF_IB_ID
																,LFS_SF_ACCOUNT_ID
																,LFS_SF_MTR_READ_ID
																,LFS_SF_STATUS_FLAG
																,LFS_SF_STATUS_MESSAGE
																,LFS_LAST_UPDATE_DATE
																,PPS_SF_IB_ID
																,PPS_SF_ACCOUNT_ID
																,PPS_SF_MTR_READ_ID
																,PPS_SF_STATUS_FLAG
																,PPS_SF_STATUS_MESSAGE
																,PPS_LAST_UPDATE_DATE
																,LFSBU
																,DPSBU
																,PPSBU
                                                                , sysdate
												  FROM canon_e633_meter_reads_stg_tbl mtr
												  WHERE 1 =1
													AND EXISTS (SELECT 1
																  FROM canon_e633_ib_del_tbl ib
																 WHERE 1 = 1
																   AND mtr.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                                                                      AND ib.config_nbr = mtr.configuration_number
                                                                      AND NVL(lfs_sf_status_flag,'P') IN ( 'I','P','DP','E')
                                                                       AND NVL(pps_sf_status_flag,'P') IN ('I', 'P','DP','E')
                                                                       AND NVL(mtr.lfs_sf_ib_id, NVL(ib.lfs_sf_ib_id,'XX')) = NVL(ib.lfs_sf_ib_id,'XX')
                                                                       AND NVL(mtr.pps_sf_ib_id, NVL(ib.pps_sf_ib_id,'XX')) = NVL(ib.pps_sf_ib_id,'XX')
                                                                       AND NVL(mtr.lfs_sf_account_id, NVL(ib.lfs_sf_account_id,'XX')) = NVL(ib.lfs_sf_account_id,'XX')
                                                                       AND NVL(mtr.pps_sf_account_id, NVL(ib.pps_sf_account_id,'XX')) = NVL(ib.pps_sf_account_id,'XX'));
	DBMS_OUTPUT.PUT_LINE('No. of records archived : ' ||sql%rowcount);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records archived.',NULL,NULL,NULL,NULL);
	commit;

	--QC56364 -- delete archived records
	DELETE
	  FROM canon_e633_meter_reads_stg_tbl mtr
	 WHERE 1 = 1
	   AND EXISTS (SELECT 1
				     FROM canon_e633_ib_del_tbl ib
				    WHERE 1 = 1
				      AND mtr.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                      AND ib.config_nbr = mtr.configuration_number
                      AND NVL(lfs_sf_status_flag,'P') IN ( 'I','P','DP','E')
                       AND NVL(pps_sf_status_flag,'P') IN ('I', 'P','DP','E')
                       AND NVL(mtr.lfs_sf_ib_id, NVL(ib.lfs_sf_ib_id,'XX')) = NVL(ib.lfs_sf_ib_id,'XX')
                       AND NVL(mtr.pps_sf_ib_id, NVL(ib.pps_sf_ib_id,'XX')) = NVL(ib.pps_sf_ib_id,'XX')
                       AND NVL(mtr.lfs_sf_account_id, NVL(ib.lfs_sf_account_id,'XX')) = NVL(ib.lfs_sf_account_id,'XX')
                       AND NVL(mtr.pps_sf_account_id, NVL(ib.pps_sf_account_id,'XX')) = NVL(ib.pps_sf_account_id,'XX'));
	DBMS_OUTPUT.PUT_LINE('No. of records deleted from stg : ' ||sql%rowcount);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records deleted from stg.',NULL,NULL,NULL,NULL);
	commit;


     --to reprocess error and unprocessed records
		UPDATE canon_e633_meter_reads_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_mtr_read_id IS NOT NULL THEN 'U' ELSE 'I' END
              ,lfs_sf_Status_message = NULL
          WHERE lfs_sf_status_flag = 'E';
--          fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' lfs error records reset.');
        dbms_output.put_line(SQL%ROWCOUNT||' lfs error records reset.');
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' lfs error records reset.',NULL,NULL,NULL,NULL);

          UPDATE canon_e633_meter_reads_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_mtr_read_id IS NOT NULL THEN 'U' ELSE 'I' END
              ,pps_sf_status_message = NULL
          WHERE pps_sf_status_flag = 'E' ;
--          fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' pps error records reset.');
        dbms_output.put_line(SQL%ROWCOUNT||' pps error records reset.');
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' pps error records reset.',NULL,NULL,NULL,NULL);
 COMMIT;

  MERGE INTO canon_e633_meter_reads_stg_tbl  stg
         USING ( WITH trx as (SELECT distinct svc_mach_mstr_pk
                                                        ,first_value(trx_rgtn_dt) over (partition by svc_mach_mstr_pk order by trx_rgtn_dt desc) trx_dt
                                                        FROM s21_csa_apps.svc_mach_mstr_trk a
                                                        WHERE 1 = 1
                                                        AND a.ezcancelflag = 0
                                                        AND a.glbl_cmpy_cd = 'ADB'
                                                        AND upd_fld_txt = 'CUR_LOC_NUM')
                    ,print_data as (SELECT distinct a.inv_prt_maint_mtr_pk
                                        , a.inv_prt_maint_mach_pk
                                        , mtr_lb_nm
                                        , prev_tot_copy_qty_txt
                                        , tot_copy_qty_txt
                                        , bllg_copy_qty_txt actual_usage
                                        , xs_mtr_amt_rate_txt rate
                                        , mtr_copy_qty_txt billable_usage
                                        , contr_mtr_mult_rate_txt multiplier
                                        , a.inv_num
                                        ,smm.svc_mach_mstr_pk
                                        ,smm.svc_config_mstr_pk
                                        ,smm.ser_num
                                        from s21_csa_apps.inv_prt_maint_mtr a
                                        ,s21_csa_apps.inv_prt_maint_mach b
                                        ,s21_csa_apps.svc_mach_mstr smm
                                        where 1 = 1
                                        and a.glbl_cmpy_cd = 'ADB'
                                        and a.ezcancelflag = '0'
                                        and b.glbl_cmpy_cd = 'ADB'
                                        and b.ezcancelflag = '0'
                                        and smm.glbl_cmpy_cd = 'ADB'
                                        and smm.ezcancelflag = '0'
                                        and smm.sld_by_line_biz_tp_cd IN ('PPS','LFS')
                                        and a.inv_prt_maint_mach_pk = b.inv_prt_maint_mach_pk
                                        and a.inv_num = b.inv_num
                                        and b.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                                        -- and smm.svc_mach_mstr_pk = 29826
                                        -- order by inv_num
                                        )
                        select distinct d.svc_inv_line_mtr_pk || ' - '|| pd.inv_prt_maint_mtr_pk  meter_read_system_number
                                        ,a.SVC_INV_NUM
                                        ,b.SER_NUM  SERIAL_NUMBER
										,spmr.VLD_MTR_FLG--QC#59883
                                        ,pd.svc_mach_mstr_pk
                                        ,pd.svc_config_mstr_pk config_number
                                        ,to_char(to_date(b.BLLG_PER_FROM_DT,'rrrrmmdd'),'mm/dd/yyyy') || ' - ' || to_char(to_date(b.BLLG_PER_THRU_DT,'rrrrmmdd'),'mm/dd/yyyy') invoice_period
                                        ,d.TOT_COPY_QTY end_read
                                        ,d.PREV_TOT_COPY_QTY start_read
                                        ,pd.mtr_lb_nm meter_type
                                        ,pd.rate rate
                                        ,pd.billable_usage Billable_usage
                                        ,pd.actual_usage Actual_usage
                                        ,d.mtr_read_dt REGISTRATION_DATE
                                        ,pd.multiplier multiplier
                                        ,TO_CHAR(TO_DATE(SUBSTR(d.ezuptime, 1, 8), 'RRRRMMDD'), 'RRRR/MM/DD') LAST_CHANGE_DATE
                               from S21_CSA_APPS.SVC_INV a
							        ,S21_CSA_APPS.SVC_PHYS_MTR_READ SPMR --QC#59883
                                    ,S21_CSA_APPS.SVC_INV_LINE b
                                    ,S21_CSA_APPS.SVC_INV_LINE_MTR d
                                    ,print_data pd
                                    ,trx t
                      where 1=1
                            and a.glbl_cmpy_cd = 'ADB'
                            and a.ezcancelflag = '0'
                            and b.glbl_cmpy_cd = 'ADB'
                            and b.ezcancelflag = '0'
                            and d.ezcancelflag = '0'
                            and d.glbl_cmpy_cd = 'ADB'
                            -- and a.SVC_INV_NUM = '4040008060'--'4040402610'
--                          --  and a.svc_mach_mstr_pk = 36561 --29826
                         /*   and a.SVC_INV_NUM IN (select  max(a1.svc_inv_num)  Commented by Venkatesham K -- QC60783, this will pick only rebill invoice
											 from     S21_CSA_APPS.SVC_INV a1 ,
													  S21_CSA_APPS.SVC_PHYS_MTR_READ SPMR1 --QC#59883
													  ,
													  S21_CSA_APPS.SVC_INV_LINE b1 ,
													  S21_CSA_APPS.SVC_INV_LINE_MTR d1
											 where    a1.glbl_cmpy_cd = 'ADB'
													  and a1.ezcancelflag = '0'
													  and b1.glbl_cmpy_cd = 'ADB'
													  and b1.ezcancelflag = '0'
													  and d1.ezcancelflag = '0'
													  and d1.glbl_cmpy_cd = 'ADB'
													  and a1.svc_mach_mstr_pk = a.svc_mach_mstr_pk --29826
													  and a1.SVC_INV_NUM = b1.SVC_INV_NUM
													  AND spmr1.svc_mach_mstr_pk = a1.svc_mach_mstr_pk--QC#59883
													  AND spmr1.VLD_MTR_FLG ='Y'--QC#59883
													  AND SPMR1.GLBL_CMPY_CD = 'ADB' --QC#59883
													  AND SPMR1.EZCANCELFLAG = '0'--QC#59883
													  AND a1.inv_tp_cd not in ( '2',
																			   '3')
													  and a1.svc_mach_mstr_pk = b1.svc_mach_mstr_pk
													  and b1.SVC_INV_NUM = d1.SVC_INV_NUM
													  and b1.SVC_INV_LINE_PK = d1.SVC_INV_LINE_PK
													  AND a.svc_mach_mstr_pk = a1.svc_mach_mstr_pk--QC#59883
													  AND d.MTR_LB_CD = d1.MTR_LB_CD
													  and to_char(to_date(b.BLLG_PER_FROM_DT,'rrrrmmdd'),'mm/dd/yyyy') || ' - ' || to_char(to_date(b.BLLG_PER_THRU_DT,'rrrrmmdd'),'mm/dd/yyyy') = to_char(to_date(b1.BLLG_PER_FROM_DT,'rrrrmmdd'),'mm/dd/yyyy') || ' - ' || to_char(to_date(b1.BLLG_PER_THRU_DT,'rrrrmmdd'),'mm/dd/yyyy') )
							*/
							AND spmr.svc_mach_mstr_pk = a.svc_mach_mstr_pk--QC#59883
                            AND spmr.VLD_MTR_FLG ='Y'--QC#59883
                            AND SPMR.GLBL_CMPY_CD = 'ADB' --QC#59883
                           --AND SPMR.SVC_MACH_MSTR_PK = 1017032 --QC#59883
                            AND SPMR.EZCANCELFLAG     = '0'--QC#59883
                            and a.svc_mach_mstr_pk = b.svc_mach_mstr_pk
                            and b.SVC_INV_NUM = d.SVC_INV_NUM
                            -- and b.SVC_INV_LINE_pk = d.SVC_INV_LINE_pk
                            and b.SVC_INV_LINE_PK = d.SVC_INV_LINE_PK
                            and pd.svc_mach_mstr_pk = a.svc_mach_mstr_pk
                            and pd.inv_num = a.svc_inv_num
                            AND t.svc_mach_mstr_pk(+) = a.svc_mach_mstr_pk --QC57878
                            and d.mtr_read_dt >= t.trx_dt(+)
						    and lower(ltrim(d.mtr_lb_desc_txt,'.')) = lower(pd.mtr_lb_nm)--QC#59307
                         --   and lower(substr(d.mtr_lb_desc_txt,2)) = lower(pd.mtr_lb_nm)
                             -- AND a.inv_tp_cd not in ( '2','3')  Commented by Venkatesham K -- QC60783 This will pick the credit invoice record also
                             and a.SVC_INV_NUM = b.SVC_INV_NUM  --28122022
                            and (CAST(TO_TIMESTAMP (d.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) > l_last_run_date )
                            order by d.mtr_read_dt
      ) ext
           ON (ext.meter_read_system_number = stg.meter_read_system_number)
          WHEN MATCHED THEN UPDATE
                            SET
                                     stg.invoice_number=(CASE WHEN ext.VLD_MTR_FLG ='Y' THEN ext.svc_inv_num ELSE stg.invoice_number END) --QC#59883                                
                                    ,stg.Serial_no=ext.SERIAL_NUMBER
                                   ,stg.svc_mach_mstr_pk =ext.svc_mach_mstr_pk
                                   ,stg.CONFIGURATION_NUMBER=ext.config_number
                                     ,stg.invoice_period  = ext.invoice_period
                                       ,stg.end_read =ext.end_read
                                       ,stg.start_read =ext.start_read
                                       ,stg.meter_type =ext.meter_type
                                       ,stg.rate = ext.rate
                                       ,stg.billable_usage=ext.billable_usage
                                       ,stg.actual_usage =ext.actual_usage
                                       ,stg.REGISTRATION_DATE=ext.REGISTRATION_DATE
                                       ,stg.multiplier =ext.multiplier
                                      ,stg.LAST_CHANGE_DATE = ext.LAST_CHANGE_DATE
                                      ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_mtr_read_id IS NULL THEN 'I' ELSE 'U' END)
                                      ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_mtr_read_id IS NULL THEN 'I' ELSE 'U' END)
                                      --,stg.lfs_sf_ib_id = (CASE lfs_sf_ib_id WHEN NULL THEN (SELECT lfs_sf_ib_id FROM canon_e633_ib_stg_tbl ib WHERE ib.config_nbr = ext.configuration_number) ELSE lfs_sf_ib_id END)
                                      --,stg.pps_sf_ib_id = (CASE pps_sf_ib_id WHEN NULL THEN (SELECT pps_sf_ib_id FROM canon_e633_ib_stg_tbl ib WHERE ib.config_nbr = ext.configuration_number) ELSE pps_sf_ib_id END)
                                     ,stg.lfs_sf_status_message = NULL
                                     ,stg.pps_sf_status_message = NULL
                                     ,stg.lfs_last_update_date = SYSDATE
                                     ,stg.pps_last_update_date = SYSDATE
      WHEN NOT MATCHED THEN INSERT (meter_read_system_number
                                                ,invoice_number
                                                ,SERIAL_NO
                                                ,svc_mach_mstr_pk
                                                ,CONFIGURATION_NUMBER
                                                ,invoice_period
                                                ,end_read
                                                ,start_read
                                                ,meter_type
                                                ,rate
                                                ,billable_usage
                                                ,actual_usage
                                                ,REGISTRATION_DATE
                                                ,multiplier
                                                ,LAST_CHANGE_DATE
                                                ,lfs_sf_status_flag
                                                 ,pps_sf_status_flag
                                                 ,lfs_sf_status_message
                                                 ,pps_sf_status_message
                                                 ,lfs_last_update_date
                                                 ,pps_last_update_date) VALUES ( ext.meter_read_system_number
                                                                        ,ext.svc_inv_num
                                                                        ,ext.SERIAL_NUMBER
                                                                        ,ext.svc_mach_mstr_pk
                                                                        ,ext.config_number
                                                                        ,ext.invoice_period
                                                                        ,ext.end_read
                                                                        ,ext.start_read
                                                                        ,ext.meter_type
                                                                        ,ext.rate
                                                                        ,ext.billable_usage
                                                                        ,ext.actual_usage
                                                                        ,ext.REGISTRATION_DATE
                                                                        ,ext.multiplier
                                                                        ,ext.LAST_CHANGE_DATE
                                                                        ,'I'
                                                                        ,'I'
                                                                        ,NULL
                                                                        ,NULL
                                                                        ,SYSDATE
                                                                        ,SYSDATE) ;


--         fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' merged into CANON_E633_MTR_READS_STG_TBL');
    dbms_output.put_line(SQL%ROWCOUNT||' merged into canon_e633_meter_reads_stg_tbl');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' merged into canon_e633_meter_reads_stg_tbl.',NULL,NULL,NULL,NULL);

 -- Added updated statements  for Defect fix

UPDATE canon_e633_meter_reads_stg_tbl
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(REGISTRATION_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

--UPDATE canon_e633_meter_reads_stg_tbl
--           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
--           WHERE length(CREATION_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));
--
--
--UPDATE canon_e633_meter_reads_stg_tbl
--           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
--           WHERE length(LAST_CHANGE_DATE) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

  commit;


	--date validations
	MERGE INTO canon_e633_meter_reads_stg_tbl a
	USING  ( select row_id, REGISTRATION_DATE, mth, dt, yr
			   from ( select a.rowid row_id
							 ,REGISTRATION_DATE
							 ,TO_NUMBER(substr(replace(REGISTRATION_DATE,'/',''), 5, 2)) mth
							 ,TO_NUMBER(substr(replace(REGISTRATION_DATE,'/',''), 7, 2)) dt
							 ,TO_NUMBER(substr(replace(REGISTRATION_DATE,'/',''), 1, 4)) yr
						from canon_e633_meter_reads_stg_tbl a
					   where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
					    -- and length(REGISTRATION_DATE) >= 10
					 )
			  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
						 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						ELSE 'VALID'
					  END = 'ERROR'   ) b
			 ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = 'REGISTRATION_DATE is Invalid.'
				 ,pps_sf_status_message = 'REGISTRATION_DATE is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;
		--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid REGISTRATION_DATE date');
	 dbms_output.put_line(SQL%ROWCOUNT||' records updated for invalid REGISTRATION_DATE.');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for invalid REGISTRATION_DATE.',NULL,NULL,NULL,NULL);

	/*--date validations
	MERGE INTO canon_e633_meter_reads_stg_tbl a
	USING  ( select row_id, CREATION_DATE, mth, dt, yr
			   from ( select a.rowid row_id
							 ,CREATION_DATE
							 ,TO_NUMBER(substr(replace(CREATION_DATE,'/',''), 5, 2)) mth
							 ,TO_NUMBER(substr(replace(CREATION_DATE,'/',''), 7, 2)) dt
							 ,TO_NUMBER(substr(replace(CREATION_DATE,'/',''), 1, 4)) yr
						from canon_e633_meter_reads_stg_tbl a
					   where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
					    -- and length(CREATION_DATE) >= 10
					 )
			  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
						 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						ELSE 'VALID'
					  END = 'ERROR'   ) b
			 ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = 'CREATION_DATE is Invalid.'
				 ,pps_sf_status_message = 'CREATION_DATE is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;
		--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid CREATION_DATE date');
	 dbms_output.put_line(SQL%ROWCOUNT||' records updated for invalid CREATION_DATE.');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for invalid CREATION_DATE.',NULL,NULL,NULL,NULL);

	--date validations
	MERGE INTO canon_e633_meter_reads_stg_tbl a
	USING  ( select row_id, LAST_CHANGE_DATE, mth, dt, yr
			   from ( select a.rowid row_id
							 ,LAST_CHANGE_DATE
							 ,TO_NUMBER(substr(replace(LAST_CHANGE_DATE,'/',''), 5, 2)) mth
							 ,TO_NUMBER(substr(replace(LAST_CHANGE_DATE,'/',''), 7, 2)) dt
							 ,TO_NUMBER(substr(replace(LAST_CHANGE_DATE,'/',''), 1, 4)) yr
						from canon_e633_meter_reads_stg_tbl a
					   where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
					    -- and length(LAST_CHANGE_DATE) >= 10
					 )
			  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
						 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						ELSE 'VALID'
					  END = 'ERROR'   ) b
			 ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = 'LAST_CHANGE_DATE is Invalid.'
				 ,pps_sf_status_message = 'LAST_CHANGE_DATE is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;
		--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid LAST_CHANGE_DATE date');
	 dbms_output.put_line(SQL%ROWCOUNT||' records updated for invalid LAST_CHANGE_DATE.');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for invalid LAST_CHANGE_DATE.',NULL,NULL,NULL,NULL);
	commit;
*/
    -- Added by Venkatesham K -- QC60783
	BEGIN
		update S21_CSA_EXTN.canon_e633_meter_reads_stg_tbl stg
		set stg.lfs_sf_status_flag = decode (nvl(stg.lfs_sf_status_flag,'X'),'X','','D'),
			stg.pps_sf_status_flag = decode (nvl(stg.pps_sf_status_flag,'X'),'X','','D')
		where 1=1
		and (
		      (nvl(stg.lfs_sf_status_flag,'D') <> 'D') OR 
		      (nvl(stg.pps_sf_status_flag,'D') <> 'D')
			)
		and stg.invoice_number <
			 (select max(stg2.invoice_number)
			  from s21_csa_extn.canon_e633_meter_reads_stg_tbl stg2
			  where stg2.serial_no = stg.serial_no
			  and stg2.meter_type = stg.meter_type
			  and stg2.invoice_period = stg.invoice_period
			  );
    
	EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('update METEREADS update Flag D exception: '|| SQLERRM);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
    END;
	
	COMMIT;

    BEGIN
            UPDATE canon_s21_cd_val_tbl v
--               SET v.val76 = TO_DATE(TO_CHAR(l_program_start_date,'YYYY-MON-DD HH24:MI:SS'),'YYYY-MON-DD HH24:MI:SS')
               SET v.val76 = l_program_start_date
             WHERE v.val1 = 'METERREADS'
               AND v.cd_id = (SELECT cd_id
                               FROM canon_s21_cd_tbl
                               WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');

			   COMMIT;

        EXCEPTION
         WHEN OTHERS THEN
           dbms_output.put_line('update METEREADS last run date exception: '|| SQLERRM);
		   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
        END;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    retcode := 2;
    errbuff := SQLERRM;
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END load_meter_reads;


PROCEDURE load_contracts(retcode OUT VARCHAR2,errbuff OUT VARCHAR2)
IS
    l_procedure_name varchar2(30) := 'load_contracts';
    l_run_date  DATE    := SYSDATE;
    l_last_run_date DATE;
--    l_program_start_date TIMESTAMP := SYSTIMESTAMP;
    l_program_start_date DATE := SYSDATE;
    l_last_run_date_num VARCHAR2(17);
BEGIN
    retcode := '0';
     BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'CONTRACTS';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
--	   l_last_run_date_num := TO_CHAR(TO_TIMESTAMP(l_last_run_date,'DD-MON-RRRR HH24:MI:SS'), 'RRRRMMDDHH24MISS');
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;

    DBMS_OUTPUT.PUT_LINE('l_last_run_date: ' ||l_last_run_date);
    DBMS_OUTPUT.PUT_LINE('l_last_run_date_num: ' ||l_last_run_date_num);

    --QC58464 --start
         UPDATE canon_e633_contracts_stg_tbl stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_contract_id IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC58464 --start
         UPDATE canon_e633_contracts_stg_tbl stg
            SET pps_sf_status_flag =  'I'
            WHERE  pps_sf_status_flag IS NULL
              AND pps_sf_contract_id IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

	--QC56364 archive the records that are associated with IB location change
	INSERT INTO canon_e633_contracts_stg_arc (ID
											,CONTRACT_NO
											,CONTRACT
											,CONTRACT_TYPE
											,BILLING_TYPE
											,POOL_COMP_FREE_CO
											,CONFIG_NBR
											,START_DATE
											,END_DATE
											,BOUNDARY_1_BUSINE
											,BOUNDARY_2_BUSINE
											,BOUNDARY_3_BUSINE
											,FIRST_COPY_PRICE_1
											,FIRST_COPY_PRICE_2
											,FIRST_COPY_PRICE_3
											,FIRST_COPY_PRICE_4
											,MIN_VOL_AMT_BUS
											,MIN_VOL_AMT_IND
											,FIRST_INVOLVED_BU
											,SCND_INVOLVED_BU
											,FIRST_FIXED_AMOUNT
											,SCND_FIXED_AMOUNT
											,ADV_ARREAR_IND_1
											,ADV_ARREAR_IND_2
											,ADV_ARREAR_IND_3
											,FIRST_FIXED_BILL_FR
											,SCND_FIXED_BILL_FR
											,VAR_BILL_FREQUENC
											,FIRST_1
											,SECOND_1
											,VARIABLE_1
											,REF_FIELD_1
											,SEVICE_TYPE
											,COVERAGE
											,FIXED_PRICE_END_1
											,PRICE_CHG_CODE_1
											,FIXED_PRICE_PRD_1
											,MAX_PRICE_UPLFT_1
											,FIXED_PRICE_END_2
											,PRICE_CHG_CODE_2
											,FIXED_PRICE_PRD_2
											,MAX_PRICE_UPLFT_2
											,VAR_PRC_END_DT_1
											,VAR_PRICE_CHG_CD1
											,VAR_PRC_PERIOD_2
											,VAR_MAX_PRC_PERC1
											,VAR_PRC_END_DT_2
											,VAR_PRICE_CHG_CD2
											,VAR_PRC_PERIOD_2_2
											,VAR_MAX_PRC_PERC2
											,CURRENCY_CODE
											,COMPONENT_SEQ
											,CONDITION_SET_VERSION
											,PURCHASE_OPTION
											,ADDRESS_NUMBER
											,EXPIRATION_DATE
											,SOURCE
											,FREE_TO_USE_1
											,FREE_TO_USE_2
											,FREE_TO_USE_3
											,FREE_TO_USE_4
											,FREE_TO_USE_5
											,FREE_TO_USE_6
											,METER_TYPE
											,LABEL
											,LABEL_DESCRIP
											,DESCRIPTION
											,COMPONENT_TYPE
											,COMMERCIAL_NAME_LANGUAGE_1
											,UNIQUE_KEY
											,LFS_SF_IB_ID
											,LFS_SF_ACCOUNT_ID
											,LFS_SF_CONTRACT_ID
											,LFS_SF_STATUS_FLAG
											,LFS_SF_STATUS_MESSAGE
											,LFS_LAST_UPDATE_DATE
											,PPS_SF_IB_ID
											,PPS_SF_ACCOUNT_ID
											,PPS_SF_CONTRACT_ID
											,PPS_SF_STATUS_FLAG
											,PPS_SF_STATUS_MESSAGE
											,PPS_LAST_UPDATE_DATE
											,LFSBU
											,DPSBU
											,PPSBU
											,BILL_TO_ACCT_NAME
											,BILL_TO_ADDRESS
											,DS_CONTR_BLLG_MTR_PK
											,CONTR_XS_COPY_PK
											,SVC_MACH_MSTR_PK
											,created_date) SELECT ID
															,CONTRACT_NO
															,CONTRACT
															,CONTRACT_TYPE
															,BILLING_TYPE
															,POOL_COMP_FREE_CO
															,CONFIG_NBR
															,START_DATE
															,END_DATE
															,BOUNDARY_1_BUSINE
															,BOUNDARY_2_BUSINE
															,BOUNDARY_3_BUSINE
															,FIRST_COPY_PRICE_1
															,FIRST_COPY_PRICE_2
															,FIRST_COPY_PRICE_3
															,FIRST_COPY_PRICE_4
															,MIN_VOL_AMT_BUS
															,MIN_VOL_AMT_IND
															,FIRST_INVOLVED_BU
															,SCND_INVOLVED_BU
															,FIRST_FIXED_AMOUNT
															,SCND_FIXED_AMOUNT
															,ADV_ARREAR_IND_1
															,ADV_ARREAR_IND_2
															,ADV_ARREAR_IND_3
															,FIRST_FIXED_BILL_FR
															,SCND_FIXED_BILL_FR
															,VAR_BILL_FREQUENC
															,FIRST_1
															,SECOND_1
															,VARIABLE_1
															,REF_FIELD_1
															,SEVICE_TYPE
															,COVERAGE
															,FIXED_PRICE_END_1
															,PRICE_CHG_CODE_1
															,FIXED_PRICE_PRD_1
															,MAX_PRICE_UPLFT_1
															,FIXED_PRICE_END_2
															,PRICE_CHG_CODE_2
															,FIXED_PRICE_PRD_2
															,MAX_PRICE_UPLFT_2
															,VAR_PRC_END_DT_1
															,VAR_PRICE_CHG_CD1
															,VAR_PRC_PERIOD_2
															,VAR_MAX_PRC_PERC1
															,VAR_PRC_END_DT_2
															,VAR_PRICE_CHG_CD2
															,VAR_PRC_PERIOD_2_2
															,VAR_MAX_PRC_PERC2
															,CURRENCY_CODE
															,COMPONENT_SEQ
															,CONDITION_SET_VERSION
															,PURCHASE_OPTION
															,ADDRESS_NUMBER
															,EXPIRATION_DATE
															,SOURCE
															,FREE_TO_USE_1
															,FREE_TO_USE_2
															,FREE_TO_USE_3
															,FREE_TO_USE_4
															,FREE_TO_USE_5
															,FREE_TO_USE_6
															,METER_TYPE
															,LABEL
															,LABEL_DESCRIP
															,DESCRIPTION
															,COMPONENT_TYPE
															,COMMERCIAL_NAME_LANGUAGE_1
															,UNIQUE_KEY
															,LFS_SF_IB_ID
															,LFS_SF_ACCOUNT_ID
															,LFS_SF_CONTRACT_ID
															,LFS_SF_STATUS_FLAG
															,LFS_SF_STATUS_MESSAGE
															,LFS_LAST_UPDATE_DATE
															,PPS_SF_IB_ID
															,PPS_SF_ACCOUNT_ID
															,PPS_SF_CONTRACT_ID
															,PPS_SF_STATUS_FLAG
															,PPS_SF_STATUS_MESSAGE
															,PPS_LAST_UPDATE_DATE
															,LFSBU
															,DPSBU
															,PPSBU
															,BILL_TO_ACCT_NAME
															,BILL_TO_ADDRESS
															,DS_CONTR_BLLG_MTR_PK
															,CONTR_XS_COPY_PK
															,SVC_MACH_MSTR_PK, sysdate
												  FROM canon_e633_contracts_stg_tbl cont
												  WHERE 1 =1
													AND EXISTS (SELECT 1
																  FROM canon_e633_ib_del_tbl ib
																 WHERE 1 = 1
																   AND cont.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                                                                      AND ib.config_nbr = cont.config_nbr
                                                                      AND NVL(lfs_sf_status_flag,'P') IN ( 'I','P','DP' ,'E')
                                                                       AND NVL(pps_sf_status_flag,'P') IN ('I', 'P','DP','E')
                                                                       AND NVL(cont.lfs_sf_ib_id, NVL(ib.lfs_sf_ib_id,'XX')) = NVL(ib.lfs_sf_ib_id,'XX')
                                                                       AND NVL(cont.pps_sf_ib_id, NVL(ib.pps_sf_ib_id,'XX')) = NVL(ib.pps_sf_ib_id,'XX')
                                                                       AND NVL(cont.lfs_sf_account_id, NVL(ib.lfs_sf_account_id,'XX')) = NVL(ib.lfs_sf_account_id,'XX')
                                                                       AND NVL(cont.pps_sf_account_id, NVL(ib.pps_sf_account_id,'XX')) = NVL(ib.pps_sf_account_id,'XX'));
	DBMS_OUTPUT.PUT_LINE('No. of records archived : ' ||sql%rowcount);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records archived.',NULL,NULL,NULL,NULL);
	commit;

	--QC56364 -- delete archived records
	DELETE
	  FROM canon_e633_contracts_stg_tbl cont
	 WHERE 1 = 1
	   AND EXISTS (SELECT 1
				     FROM canon_e633_ib_del_tbl ib
				    WHERE 1 = 1
				      AND cont.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                      AND ib.config_nbr = cont.config_nbr
                      AND NVL(lfs_sf_status_flag,'P') IN ( 'I','P','DP' ,'E')
                       AND NVL(pps_sf_status_flag,'P') IN ('I', 'P','DP','E')
                       AND NVL(cont.lfs_sf_ib_id, NVL(ib.lfs_sf_ib_id,'XX')) = NVL(ib.lfs_sf_ib_id,'XX')
                       AND NVL(cont.pps_sf_ib_id, NVL(ib.pps_sf_ib_id,'XX')) = NVL(ib.pps_sf_ib_id,'XX')
                       AND NVL(cont.lfs_sf_account_id, NVL(ib.lfs_sf_account_id,'XX')) = NVL(ib.lfs_sf_account_id,'XX')
                       AND NVL(cont.pps_sf_account_id, NVL(ib.pps_sf_account_id,'XX')) = NVL(ib.pps_sf_account_id,'XX'));
	DBMS_OUTPUT.PUT_LINE('No. of records deleted from stg : ' ||sql%rowcount);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records deleted from stg.',NULL,NULL,NULL,NULL);
	commit;

    --to reprocess error and unprocessed records
		UPDATE canon_e633_contracts_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_contract_id IS NOT NULL THEN 'U' ELSE 'I' END
          WHERE lfs_sf_status_flag = 'E' ;

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' lfs error records reset.');
            dbms_output.put_line(SQL%ROWCOUNT||' lfs error records reset.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' lfs error records reset.',NULL,NULL,NULL,NULL);

          UPDATE canon_e633_contracts_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_contract_id IS NOT NULL THEN 'U' ELSE 'I' END
          WHERE pps_sf_status_flag = 'E' ;

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' pps error records reset.');
            dbms_output.put_line(SQL%ROWCOUNT||' pps error records reset.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' pps error records reset.',NULL,NULL,NULL,NULL);
         COMMIT;

         EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_contr2process_gtbl';

         INSERT
                  INTO canon_e633_contr2process_gtbl
                      (ds_contr_pk
                       ,SVC_LINE_BIZ_CD
                      )
                SELECT DISTINCT ds_contr_pk, SVC_LINE_BIZ_CD
                  FROM ds_contr dc
                 WHERE 1 = 1
                   AND dc.glbl_cmpy_cd = 'ADB'
                   AND dc.ezcancelflag = '0'
                   AND dc.SVC_LINE_BIZ_CD IN ('LFS','PPS')
				   AND CAST(TO_TIMESTAMP (dc.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                   --AND TO_NUMBER(dc.ezuptime) > TO_NUMBER(l_last_run_date_num)
                 UNION
                SELECT DISTINCT dcd.ds_contr_pk
                       ,dc.SVC_LINE_BIZ_CD
                  FROM ds_contr_dtl dcd
                       ,ds_contr dc
                 WHERE 1 = 1
                   AND dcd.glbl_cmpy_cd = 'ADB'
                   AND dcd.ezcancelflag = '0'
                   AND dc.glbl_cmpy_cd = 'ADB'
                   AND dc.ezcancelflag = '0'
                   AND dc.SVC_LINE_BIZ_CD IN ('LFS','PPS')
                   AND dcd.ds_contr_pk = dc.ds_contr_pk
				   AND CAST(TO_TIMESTAMP (dcd.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                   --AND TO_NUMBER(dcd.ezuptime) > TO_NUMBER(l_last_run_date_num)
                 UNION
                SELECT DISTINCT
                        dcd.ds_contr_pk
                       ,dc.SVC_LINE_BIZ_CD
                  FROM ds_contr_bllg_mtr dcbm
                      ,ds_contr_dtl dcd
                      ,ds_contr dc
                 WHERE 1 = 1
                   AND dcbm.glbl_cmpy_cd = 'ADB'
                   AND dcbm.ezcancelflag = '0'
                   AND dcd.glbl_cmpy_cd = 'ADB'
                   AND dcd.ezcancelflag = '0'
                   AND dc.glbl_cmpy_cd = 'ADB'
                   AND dc.ezcancelflag = '0'
                   AND dc.ds_contr_pk = dcd.ds_contr_pk
                   AND dcd.ds_contr_dtl_pk = dcbm.ds_contr_dtl_pk
                   AND dc.SVC_LINE_BIZ_CD IN ('LFS','PPS')
				   AND CAST(TO_TIMESTAMP (dcbm.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                   --AND(  TO_NUMBER(dcbm.ezuptime) > TO_NUMBER(l_last_run_date_num))
                 UNION
                SELECT DISTINCT
                        dc.ds_contr_pk
                       ,dc.SVC_LINE_BIZ_CD
                  FROM ds_contr dc
                       ,ds_contr_dtl dcd
                       ,ds_contr_bllg_mtr dcbm
                       ,contr_xs_copy cxc
                 WHERE 1 = 1
                   AND dc.glbl_cmpy_cd = 'ADB'
                   AND dc.ezcancelflag = '0'
                   AND dcd.glbl_cmpy_cd = 'ADB'
                   AND dcd.ezcancelflag = '0'
                   AND dcbm.glbl_cmpy_cd = 'ADB'
                   AND dcbm.ezcancelflag = '0'
                   AND cxc.glbl_cmpy_cd = 'ADB'
                   AND cxc.ezcancelflag = '0'
                   AND dc.SVC_LINE_BIZ_CD IN ('LFS','PPS')
                   AND dc.ds_contr_pk = dcd.ds_contr_pk
                   AND dcd.ds_contr_dtl_pk = dcbm.ds_contr_dtl_pk
                   AND cxc.DS_CONTR_BLLG_MTR_PK = dcbm.DS_CONTR_BLLG_MTR_PK
				   AND CAST(TO_TIMESTAMP (cxc.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                   --AND TO_NUMBER(cxc.ezuptime) > TO_NUMBER(l_last_run_date_num)
                 ;
    dbms_output.put_line(SQL%ROWCOUNT||' records merged into canon_e633_contr2process_tbl.');
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records merged into canon_e633_contr2process_tbl.',NULL,NULL,NULL,NULL);

         MERGE INTO canon_e633_contracts_stg_tbl stg
            USING (select distinct dc.ds_contr_pk contract
                                ,dc.ds_contr_num contract_no
                               ,dc.svc_line_biz_cd
                               ,NULL contract_type --dct.ds_contr_tp_nm contract_type
                               ,NULL billing_type --dibt.ds_inv_bllg_tp_nm
                               ,NULL pool_comp_free_co
                               ,dcd.ds_contr_dtl_pk
                               ,dcd.svc_mach_mstr_pk
                               ,dcbm.ds_contr_bllg_mtr_pk
                               ,dcd.svc_config_mstr_pk config_nbr
                               ,dcd.contr_eff_from_dt start_date
                               ,dcd.contr_eff_thru_dt end_date
                               ,(CASE WHEN dcbm.bllg_min_amt_rate IS NULL AND dcbm.bllg_min_cnt IS NOT NULL THEN dcbm.bllg_min_cnt
                                     WHEN dcbm.bllg_min_amt_rate IS NOT NULL AND dcbm.bllg_min_cnt IS NULL THEN dcbm.bllg_min_amt_rate END) min_vol_amt_bus
                               ,NULL min_vol_amt_ind
                               ,NULL first_involved_bu
                               ,NULL scnd_involved_bu
                               ,NULL first_fixed_amount
                               ,NULL scnd_fixed_amount
                               ,NULL adv_arrear_ind_1
                               ,NULL adv_arrear_ind_2
                               ,dcd.base_bllg_cycle_cd first_fixed_bill_fr
                               ,NULL scnd_fixed_bill_fr
                               ,dmi.mdse_desc_short_txt sevice_type
                               ,a.ahs_nm coverage
                               ,NULL price_chg_code_1
                               ,NULL fixed_price_prd_1
                               ,NULL max_price_uplft_1
                               ,NULL price_chg_code_2
                               ,NULL fixed_price_prd_2
                               ,NULL max_price_uplft_2
                               ,dcd.ds_contr_dtl_pk component_seq
                               ,dc.contr_vrsn_eff_thru_dt expiration_date
                               ,ml.mtr_lb_desc_txt label_descrip
                               ,dmi.mdse_cd component_type
                               ,btc.loc_nm bill_to_Acc_name
                               ,btc.loc_num address_number
                               ,btc.first_line_addr || btc.scd_line_addr || btc.third_line_addr || btc.frth_line_addr billing_address
                               ,dcbm.bllg_mtr_lb_cd meter_label
                               ,NULL meter_type
--                               ,mlt.mtr_lb_tp_nm meter_type
--                               ,sctt.svc_cov_tm_tp_nm description
                               ,NULL description
                               ,NULL condition_set_version
                               ,NULL COMMERCIAL_NAME_LANGUAGE_1
                               ,NULL LABEL
                               ,NULL SOURCE
                               ,NULL FREE_TO_USE_1
                               ,NULL FREE_TO_USE_2
                               ,NULL FREE_TO_USE_3
                               ,NULL FREE_TO_USE_4
                               ,NULL FREE_TO_USE_5
                               ,NULL FREE_TO_USE_6
                               ,NULL VAR_PRC_END_DT_1
                               ,NULL VAR_PRICE_CHG_CD1
                               ,NULL VAR_PRC_PERIOD_2
                               ,NULL VAR_MAX_PRC_PERC1
                               ,NULL VAR_PRC_END_DT_2
                               ,NULL VAR_PRICE_CHG_CD2
                               ,NULL VAR_PRC_PERIOD_2_2
                               ,NULL VAR_MAX_PRC_PERC2
                               ,NULL CURRENCY_CODE
                               ,NULL PURCHASE_OPTION
                               ,NULL FIXED_PRICE_END_2
                               ,NULL FIXED_PRICE_END_1
                               ,NULL FIRST_1
                              ,NULL SECOND_1
                              ,NULL VARIABLE_1
                              ,NULL REF_FIELD_1
--                              ,NULL SEVICE_TYPE
                              ,NULL VAR_BILL_FREQUENC
                              ,NULL ADV_ARREAR_IND_3
--                              ,cxc.contr_xs_copy_pk
							  ,spm.svc_phys_mtr_pk
                          from ds_contr dc
                               ,canon_e633_contr2process_gtbl cont
                               ,ds_contr_dtl dcd
                               ,ds_contr_bllg_mtr dcbm
                               ,mdse dmi -- DB Changes
                               ,ahs a
                               ,svc_term_cond stc
                               ,mtr_lb ml
                               ,bill_to_cust btc
							   ,contr_phys_bllg_mtr_reln cpb
							   ,svc_phys_mtr spm
                               ,svc_mach_mstr smm --QC57878

--                               ,mtr_lb_tp mlt
--                              ,svc_cov_tm_tp sctt
--                               ,contr_xs_copy cxc
                          where
                                smm.glbl_cmpy_cd = 'ADB'--QC57878
					        and smm.ezcancelflag = '0'--QC57878
                            and dc.ezcancelflag = '0'
                            and dc.glbl_cmpy_cd = 'ADB'
                            and dcd.ezcancelflag = '0'
                            and dcd.glbl_cmpy_cd = 'ADB'
                            and dcbm.ezcancelflag(+) = '0'
                            and dcbm.glbl_cmpy_cd(+) = 'ADB'
                            and dmi.ezcancelflag = '0'
                            and dmi.glbl_cmpy_cd = 'ADB'
                            and a.ezcancelflag(+) = '0'
                            and a.glbl_cmpy_cd(+) = 'ADB'
                            and stc.ezcancelflag(+) = '0'
                            and stc.glbl_cmpy_cd(+) = 'ADB'
                            and ml.ezcancelflag(+) = '0'
                            and ml.glbl_cmpy_cd(+) = 'ADB'
                            AND btc.ezcancelflag = '0'
                            AND btc.glbl_cmpy_cd = 'ADB'
--                            AND cxc.glbl_cmpy_cd(+) = 'ADB'
--                            AND cxc.ezcancelflag(+) = '0'
                            AND dc.ds_contr_sts_cd IN ('3','4') -- Approved, effective
                            AND dcd.ds_contr_dtl_sts_cd IN ('ACTV') -- active
                            and dc.ds_contr_pk = cont.ds_contr_pk
--                            and dc.ds_contr_pk = '1000003'
                            and dc.ds_contr_pk = dcd.ds_contr_pk
                            and dcd.ds_contr_dtl_pk = dcbm.ds_contr_dtl_pk(+)
                            and dcd.svc_pgm_mdse_cd = dmi.mdse_cd
                            AND dcd.ds_contr_dtl_pk = stc.ds_contr_dtl_pk(+)
                           AND stc.svc_term_cond_attrb_pk = '17' --for AHS
                           AND stc.svc_term_attrb_map_val_cd = a.ahs_cd(+)
                            AND dcbm.bllg_mtr_lb_cd = ml.mtr_lb_cd(+)
                            AND dcd.base_bill_to_cust_cd = btc.bill_to_cust_cd
							AND dcbm.ds_contr_bllg_mtr_pk = cpb.ds_contr_bllg_mtr_pk(+)
							AND cpb.svc_phys_mtr_pk = spm.svc_phys_mtr_pk(+)
                             AND btc.loc_num = smm.ind_bill_to_loc_num--QC57878
--                            AND dcbm.ds_contr_bllg_mtr_pk = cxc.ds_contr_bllg_mtr_pk(+)
                        --    AND ml.mtr_lb_tp_cd = mlt.MTR_LB_TP_CD
                        --                           AND dcd.svc_cov_tm_tp_cd = sctt.svc_cov_tm_tp_cd(+)
                        )s21
               ON (    stg.unique_key = (s21.contract_no||s21.ds_contr_dtl_pk||s21.ds_contr_bllg_mtr_pk||s21.svc_phys_mtr_pk)
                /*       AND stg.svc_mach_mstr_pk = s21.svc_mach_mstr_pk
					   AND stg.config_nbr = s21.config_nbr
                        AND stg.component_seq = s21.component_seq
                        AND NVL(stg.ds_contr_bllg_mtr_pk, -9) = NVL(s21.ds_contr_bllg_mtr_pk, -9)*/
               )
           WHEN MATCHED THEN UPDATE
                SET  stg.CONTRACT = s21.CONTRACT
--                      ,stg.ds_contr_bllg_mtr_pk = s21.ds_contr_bllg_mtr_pk
                      ,stg.svc_mach_mstr_pk = s21.svc_mach_mstr_pk
                      ,stg.contract_type = s21.contract_type
                      ,stg.BILLING_TYPE = s21.BILLING_TYPE
                      ,stg.POOL_COMP_FREE_CO = s21.POOL_COMP_FREE_CO
                      --,stg.CONFIG_NBR = trim(ext.CONFIG_NBR)
                      ,stg.START_DATE = s21.start_date
                      ,stg.END_DATE = s21.end_date
--                      ,stg.BOUNDARY_1_BUSINE = s21.BOUNDARY_1_BUSINE
--                      ,stg.BOUNDARY_2_BUSINE = s21.BOUNDARY_2_BUSINE
--                      ,stg.BOUNDARY_3_BUSINE = s21.BOUNDARY_3_BUSINE
--                      ,stg.FIRST_COPY_PRICE_1 = s21.FIRST_COPY_PRICE_1
--                      ,stg.FIRST_COPY_PRICE_2 = s21.FIRST_COPY_PRICE_2
--                      ,stg.FIRST_COPY_PRICE_3 = s21.FIRST_COPY_PRICE_3
--                      ,stg.FIRST_COPY_PRICE_4 = s21.FIRST_COPY_PRICE_4
                      ,stg.MIN_VOL_AMT_BUS = s21.MIN_VOL_AMT_BUS
                      ,stg.MIN_VOL_AMT_IND = s21.MIN_VOL_AMT_IND
                      ,stg.FIRST_INVOLVED_BU = s21.FIRST_INVOLVED_BU
                      ,stg.SCND_INVOLVED_BU = s21.SCND_INVOLVED_BU
                      --,stg.FIRST_FIXED_AMOUNT = s21.FIRST_FIXED_AMOUNT)
					  ,stg.FIRST_FIXED_AMOUNT = (CASE WHEN s21.FIRST_INVOLVED_BU = 'S' THEN s21.SCND_FIXED_AMOUNT ELSE s21.FIRST_FIXED_AMOUNT END) --ITG662026
					  ,stg.SCND_FIXED_AMOUNT = (CASE WHEN s21.FIRST_INVOLVED_BU = 'S' THEN s21.FIRST_FIXED_AMOUNT ELSE s21.SCND_FIXED_AMOUNT END) --ITG662026
                     -- ,stg.SCND_FIXED_AMOUNT = trim(ext.SCND_FIXED_AMOUNT)
                      ,stg.ADV_ARREAR_IND_1 = s21.ADV_ARREAR_IND_1
                      ,stg.ADV_ARREAR_IND_2 = s21.ADV_ARREAR_IND_2
                      ,stg.ADV_ARREAR_IND_3 = s21.ADV_ARREAR_IND_3
                      ,stg.FIRST_FIXED_BILL_FR = s21.FIRST_FIXED_BILL_FR
                      ,stg.SCND_FIXED_BILL_FR = s21.SCND_FIXED_BILL_FR
                      ,stg.VAR_BILL_FREQUENC = s21.VAR_BILL_FREQUENC
                      ,stg.FIRST_1 = s21.FIRST_1
                      ,stg.SECOND_1 = s21.SECOND_1
                      ,stg.VARIABLE_1 = s21.VARIABLE_1
                      ,stg.REF_FIELD_1 = s21.REF_FIELD_1
                      ,stg.SEVICE_TYPE = s21.SEVICE_TYPE
                      ,stg.COVERAGE = s21.COVERAGE
                      ,stg.FIXED_PRICE_END_1 = s21.FIXED_PRICE_END_1
                      ,stg.PRICE_CHG_CODE_1 = s21.PRICE_CHG_CODE_1
                      ,stg.FIXED_PRICE_PRD_1 = s21.FIXED_PRICE_PRD_1
                      ,stg.MAX_PRICE_UPLFT_1 = s21.MAX_PRICE_UPLFT_1
                      ,stg.FIXED_PRICE_END_2 = s21.FIXED_PRICE_END_2
                      ,stg.PRICE_CHG_CODE_2 = s21.PRICE_CHG_CODE_2
                      ,stg.FIXED_PRICE_PRD_2 = s21.FIXED_PRICE_PRD_2
                      ,stg.MAX_PRICE_UPLFT_2 = s21.MAX_PRICE_UPLFT_2
                      ,stg.VAR_PRC_END_DT_1  = s21.VAR_PRC_END_DT_1
                      ,stg.VAR_PRICE_CHG_CD1 = s21.VAR_PRICE_CHG_CD1
                      ,stg.VAR_PRC_PERIOD_2 = s21.VAR_PRC_PERIOD_2
                      ,stg.VAR_MAX_PRC_PERC1 = s21.VAR_MAX_PRC_PERC1
                      ,stg.VAR_PRC_END_DT_2 = s21.VAR_PRC_END_DT_2
                      ,stg.VAR_PRICE_CHG_CD2 = s21.VAR_PRICE_CHG_CD2
                      ,stg.VAR_PRC_PERIOD_2_2 = s21.VAR_PRC_PERIOD_2_2
                      ,stg.VAR_MAX_PRC_PERC2 = s21.VAR_MAX_PRC_PERC2
                      ,stg.CURRENCY_CODE  = s21.CURRENCY_CODE
                      --,stg.COMPONENT_SEQ = trim(ext.COMPONENT_SEQ)
                      --,stg.CONDITION_SET_VERSION = trim(ext.CONDITION_SET_VERSION)
                      ,stg.PURCHASE_OPTION = s21.PURCHASE_OPTION
                      ,stg.ADDRESS_NUMBER = s21.ADDRESS_NUMBER
                      ,stg.EXPIRATION_DATE = s21.EXPIRATION_DATE
                      ,stg.SOURCE = s21.SOURCE
                      ,stg.FREE_TO_USE_1 = s21.FREE_TO_USE_1
                      ,stg.FREE_TO_USE_2 = s21.FREE_TO_USE_2
                      ,stg.FREE_TO_USE_3 = s21.FREE_TO_USE_3
                      ,stg.FREE_TO_USE_4 = s21.FREE_TO_USE_4
                      ,stg.FREE_TO_USE_5 = s21.FREE_TO_USE_5
                      ,stg.FREE_TO_USE_6 = s21.FREE_TO_USE_6
                      ,stg.METER_TYPE = s21.METER_TYPE
                      ,stg.LABEL = s21.meter_label
                      ,stg.LABEL_DESCRIP = s21.LABEL_DESCRIP
                      ,stg.DESCRIPTION = s21.DESCRIPTION
                      ,stg.COMPONENT_TYPE = s21.COMPONENT_TYPE
                      ,stg.COMMERCIAL_NAME_LANGUAGE_1 = s21.COMMERCIAL_NAME_LANGUAGE_1
                      ,stg.lfs_last_update_date = sysdate
                      ,stg.pps_last_update_date = sysdate
                      ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_contract_id  IS NULL THEN 'I' ELSE 'U' END)
                      ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_contract_id IS NULL THEN 'I' ELSE 'U' END)
                      --,stg.lfs_sf_ib_id = (CASE lfs_sf_ib_id WHEN NULL THEN (SELECT lfs_sf_ib_id FROM canon_e633_ib_stg_tbl ib WHERE ib.config_nbr = ext.config_nbr) ELSE lfs_sf_ib_id END)
                      --,stg.pps_sf_ib_id = (CASE pps_sf_ib_id WHEN NULL THEN (SELECT pps_sf_ib_id FROM canon_e633_ib_stg_tbl ib WHERE ib.config_nbr = ext.config_nbr) ELSE pps_sf_ib_id END)
                      ,lfs_sf_status_message = NULL
                      ,pps_sf_status_message = NULL
            WHEN NOT MATCHED THEN INSERT (CONTRACT_NO
                                          ,CONTRACT
                                          ,ds_contr_bllg_mtr_pk
                                          ,svc_mach_mstr_pk
--                                          ,contr_xs_copy_pk
                                          ,CONTRACT_TYPE
                                          ,BILLING_TYPE
                                          ,POOL_COMP_FREE_CO
                                          ,CONFIG_NBR
                                          ,START_DATE
                                          ,END_DATE
--                                          ,BOUNDARY_1_BUSINE
--                                          ,BOUNDARY_2_BUSINE
--                                          ,BOUNDARY_3_BUSINE
--                                          ,FIRST_COPY_PRICE_1
--                                          ,FIRST_COPY_PRICE_2
--                                          ,FIRST_COPY_PRICE_3
--                                          ,FIRST_COPY_PRICE_4
                                          ,MIN_VOL_AMT_BUS
                                          ,MIN_VOL_AMT_IND
                                          ,FIRST_INVOLVED_BU
                                          ,SCND_INVOLVED_BU
                                          ,FIRST_FIXED_AMOUNT
                                          ,SCND_FIXED_AMOUNT
                                          ,ADV_ARREAR_IND_1
                                          ,ADV_ARREAR_IND_2
                                          ,ADV_ARREAR_IND_3
                                          ,FIRST_FIXED_BILL_FR
                                          ,SCND_FIXED_BILL_FR
                                          ,VAR_BILL_FREQUENC
                                          ,FIRST_1
                                          ,SECOND_1
                                          ,VARIABLE_1
                                          ,REF_FIELD_1
                                          ,SEVICE_TYPE
                                          ,COVERAGE
                                          ,FIXED_PRICE_END_1
                                          ,PRICE_CHG_CODE_1
                                          ,FIXED_PRICE_PRD_1
                                          ,MAX_PRICE_UPLFT_1
                                          ,FIXED_PRICE_END_2
                                          ,PRICE_CHG_CODE_2
                                          ,FIXED_PRICE_PRD_2
                                          ,MAX_PRICE_UPLFT_2
                                          ,VAR_PRC_END_DT_1
                                          ,VAR_PRICE_CHG_CD1
                                          ,VAR_PRC_PERIOD_2
                                          ,VAR_MAX_PRC_PERC1
                                          ,VAR_PRC_END_DT_2
                                          ,VAR_PRICE_CHG_CD2
                                          ,VAR_PRC_PERIOD_2_2
                                          ,VAR_MAX_PRC_PERC2
                                          ,CURRENCY_CODE
                                          ,COMPONENT_SEQ
                                          ,CONDITION_SET_VERSION
                                          ,PURCHASE_OPTION
                                          ,ADDRESS_NUMBER
                                          ,EXPIRATION_DATE
                                          ,SOURCE
                                          ,FREE_TO_USE_1
                                          ,FREE_TO_USE_2
                                          ,FREE_TO_USE_3
                                          ,FREE_TO_USE_4
                                          ,FREE_TO_USE_5
                                          ,FREE_TO_USE_6
                                          ,METER_TYPE
                                          ,LABEL
                                          ,LABEL_DESCRIP
                                          ,DESCRIPTION
                                          ,COMPONENT_TYPE
                                          ,COMMERCIAL_NAME_LANGUAGE_1
                                          ,lfs_sf_ib_id
                                          ,pps_sf_ib_id
                                          ,lfs_sf_contract_id
                                          ,pps_sf_contract_id
                                          ,lfs_sf_status_flag
                                          ,pps_sf_status_flag
                                          ,lfs_sf_status_message
                                          ,pps_sf_status_message
                                          ,lfs_last_update_date
                                          ,pps_last_update_date
                                          ,unique_key
                                          ) VALUES(s21.CONTRACT_NO
                                                              ,s21.CONTRACT
                                                              ,s21.ds_contr_bllg_mtr_pk
                                                              ,s21.svc_mach_mstr_pk
--                                                              ,s21.contr_xs_copy_pk
                                                              ,s21.CONTRACT_TYPE
                                                              ,s21.BILLING_TYPE
                                                              ,s21.POOL_COMP_FREE_CO
                                                              ,s21.CONFIG_NBR
                                                              ,s21.START_DATE
                                                              ,s21.END_DATE
--                                                              ,s21.BOUNDARY_1_BUSINE
--                                                              ,s21.BOUNDARY_2_BUSINE
--                                                              ,s21.BOUNDARY_3_BUSINE
--                                                              ,s21.FIRST_COPY_PRICE_1
--                                                              ,s21.FIRST_COPY_PRICE_2
--                                                              ,s21.FIRST_COPY_PRICE_3
--                                                              ,s21.FIRST_COPY_PRICE_4
                                                              ,s21.MIN_VOL_AMT_BUS
                                                              ,s21.MIN_VOL_AMT_IND
                                                              ,s21.FIRST_INVOLVED_BU
                                                              ,s21.SCND_INVOLVED_BU
                                                             -- ,s21.FIRST_FIXED_AMOUNT)
															   ,(CASE WHEN s21.FIRST_INVOLVED_BU = 'S' THEN s21.SCND_FIXED_AMOUNT ELSE s21.FIRST_FIXED_AMOUNT END) --ITG662026
                                                             -- ,s21.SCND_FIXED_AMOUNT)
															  ,(CASE WHEN s21.FIRST_INVOLVED_BU = 'S' THEN s21.FIRST_FIXED_AMOUNT ELSE s21.SCND_FIXED_AMOUNT END) --ITG662026
                                                              ,s21.ADV_ARREAR_IND_1
                                                              ,s21.ADV_ARREAR_IND_2
                                                              ,s21.ADV_ARREAR_IND_3
                                                              ,s21.FIRST_FIXED_BILL_FR
                                                              ,s21.SCND_FIXED_BILL_FR
                                                              ,s21.VAR_BILL_FREQUENC
                                                              ,s21.FIRST_1
                                                              ,s21.SECOND_1
                                                              ,s21.VARIABLE_1
                                                              ,s21.REF_FIELD_1
                                                              ,s21.SEVICE_TYPE
                                                              ,s21.COVERAGE
                                                              ,s21.FIXED_PRICE_END_1
                                                              ,s21.PRICE_CHG_CODE_1
                                                              ,s21.FIXED_PRICE_PRD_1
                                                              ,s21.MAX_PRICE_UPLFT_1
                                                              ,s21.FIXED_PRICE_END_2
                                                              ,s21.PRICE_CHG_CODE_2
                                                              ,s21.FIXED_PRICE_PRD_2
                                                              ,s21.MAX_PRICE_UPLFT_2
                                                              ,s21.VAR_PRC_END_DT_1
                                                              ,s21.VAR_PRICE_CHG_CD1
                                                              ,s21.VAR_PRC_PERIOD_2
                                                              ,s21.VAR_MAX_PRC_PERC1
                                                              ,s21.VAR_PRC_END_DT_2
                                                              ,s21.VAR_PRICE_CHG_CD2
                                                              ,s21.VAR_PRC_PERIOD_2_2
                                                              ,s21.VAR_MAX_PRC_PERC2
                                                              ,s21.CURRENCY_CODE
                                                              ,s21.COMPONENT_SEQ
                                                              ,s21.CONDITION_SET_VERSION
                                                              ,s21.PURCHASE_OPTION
                                                              ,s21.ADDRESS_NUMBER
                                                              ,s21.EXPIRATION_DATE
                                                              ,s21.SOURCE
                                                              ,s21.FREE_TO_USE_1
                                                              ,s21.FREE_TO_USE_2
                                                              ,s21.FREE_TO_USE_3
                                                              ,s21.FREE_TO_USE_4
                                                              ,s21.FREE_TO_USE_5
                                                              ,s21.FREE_TO_USE_6
                                                              ,s21.METER_TYPE
                                                              ,s21.LABEL
                                                              ,s21.LABEL_DESCRIP
                                                              ,s21.DESCRIPTION
                                                              ,s21.COMPONENT_TYPE
                                                              ,s21.COMMERCIAL_NAME_LANGUAGE_1
                                                              ,NULL
                                                              ,NULL
                                                              ,NULL
                                                              ,NULL
                                                              ,'I'
                                                              ,'I'
                                                              ,NULL
                                                              ,NULL
                                                              ,SYSDATE
                                                              ,SYSDATE
                                                              ,s21.contract_no||s21.ds_contr_dtl_pk||s21.ds_contr_bllg_mtr_pk||s21.svc_phys_mtr_pk
                                                              );

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records merged into CANON_E633_CONTRACTS_STG_TBL.');
            dbms_output.put_line(SQL%ROWCOUNT||' records merged from file to staging table.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records merged into CANON_E633_CONTRACTS_STG_TBL.',NULL,NULL,NULL,NULL);


-- Added Update statemens for Defect fix

 UPDATE canon_e633_contracts_stg_tbl
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(expiration_date) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));

UPDATE canon_e633_contracts_stg_tbl
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(start_date) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));


UPDATE canon_e633_contracts_stg_tbl
           SET pps_sf_status_flag = 'E' , lfs_sf_status_flag = 'E'
           WHERE length(end_date) < 8  and (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'));
 commit;

			-- date validations
			MERGE INTO canon_e633_contracts_stg_tbl a
			USING  ( select row_id, expiration_date, mth, dt, yr
					   from ( select a.rowid row_id
									 ,expiration_date
									 ,TO_NUMBER(substr(replace(expiration_date,'/',''), 5, 2)) mth
									 ,TO_NUMBER(substr(replace(expiration_date,'/',''), 7, 2)) dt
									 ,TO_NUMBER(substr(replace(expiration_date,'/',''), 1, 4)) yr
								from canon_e633_contracts_stg_tbl a
								where (lfs_sf_status_flag IN ('I','U') OR pps_sf_Status_flag  IN ('I', 'U'))
								--  and length(expiration_date) >= 10
							)
					  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
								 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
								 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
								 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
								 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
								 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
								 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								ELSE 'VALID'
							 END = 'ERROR'   ) b
						 ON (a.rowid = b.row_id)
						WHEN MATCHED THEN UPDATE
						 SET lfs_sf_status_flag = 'E'
							 ,pps_sf_Status_flag = 'E'
							 ,lfs_sf_status_message = 'Expiration Date is Invalid.'
							 ,pps_sf_status_message = 'Expiration Date is Invalid.'
							 ,lfs_last_update_date = SYSDATE
							 ,pps_last_update_date = SYSDATE
							 ;
			--   fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid expiration date');
			dbms_output.put_line(SQL%ROWCOUNT||' records updated for invalid expiration date.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for invalid expiration date.',NULL,NULL,NULL,NULL);

			MERGE INTO canon_e633_contracts_stg_tbl a
			 USING  (select row_id, start_date, mth, dt, yr
					   from (select a.rowid row_id
									,start_date
									,TO_NUMBER(substr(replace(start_date,'/',''), 5, 2)) mth
									,TO_NUMBER(substr(replace(start_date,'/',''), 7, 2)) dt
									,TO_NUMBER(substr(replace(start_date,'/',''), 1, 4)) yr
							  from canon_e633_contracts_stg_tbl a
							  where (lfs_sf_status_flag IN ('I','U','E') OR pps_sf_Status_flag  IN ('I', 'E', 'U'))
							   and length(start_date) >= 8
							  )
					  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
								 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
								 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
								 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
								 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
								 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
								 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
								ELSE 'VALID'
							END = 'ERROR'   ) b
				 ON (a.rowid = b.row_id)
				WHEN MATCHED THEN UPDATE
				 SET lfs_sf_status_flag = 'E'
					 ,pps_sf_Status_flag = 'E'
					 ,lfs_sf_status_message = lfs_sf_status_message||'Start Date is Invalid.'
					 ,pps_sf_status_message = pps_sf_status_message||'Start Date is Invalid.'
					 ,lfs_last_update_date = SYSDATE
					 ,pps_last_update_date = SYSDATE
					 ;
			--         fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records update for invalid start date');
			dbms_output.put_line(SQL%ROWCOUNT||' records update for invalid start date.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records update for invalid start date.',NULL,NULL,NULL,NULL);

			MERGE INTO canon_e633_contracts_stg_tbl a
			 USING  ( select row_id, end_date, mth, dt, yr
			            from ( select a.rowid row_id
									  ,end_date
									  ,TO_NUMBER(substr(replace(end_date,'/',''), 5, 2)) mth
									  ,TO_NUMBER(substr(replace(end_date,'/',''), 7, 2)) dt
									  ,TO_NUMBER(substr(replace(end_date,'/',''), 1, 4)) yr
								 from canon_e633_contracts_stg_tbl a
								where (lfs_sf_Status_flag  IN ('I', 'E', 'U') OR pps_sf_Status_flag  IN ('I', 'E', 'U'))
								 and length(end_date) >= 8
							  )
						where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
									 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
									 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
									 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
									 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
									 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
									 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
									 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
									 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
									 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
									 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
									 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
									 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
									ELSE 'VALID'
								END = 'ERROR'   ) b
				ON (a.rowid = b.row_id)
				WHEN MATCHED THEN UPDATE
				 SET lfs_sf_status_flag = 'E'
					 ,pps_sf_Status_flag = 'E'
					 ,lfs_sf_status_message = lfs_sf_status_message||'End Date is Invalid.'
					 ,pps_sf_status_message = pps_sf_status_message||'End Date is Invalid.'
					 ,lfs_last_update_date = SYSDATE
					 ,pps_last_update_date = SYSDATE
					 ;
			--fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for invalid end date');
			dbms_output.put_line(SQL%ROWCOUNT||' records updated for invalid end date.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated for invalid end date.',NULL,NULL,NULL,NULL);


            MERGE INTO canon_e633_contracts_stg_tbl a
                USING (SELECT distinct contr_xs_copy_pk, ds_contr_bllg_mtr_pk, xs_mtr_copy_qty, xs_mtr_amt_rate
                        FROM( SELECT rownum rn
                                      ,cxc.contr_xs_copy_pk
                                      ,cxc.ds_contr_bllg_mtr_pk
                                      ,xs_mtr_copy_qty
                                      ,xs_mtr_amt_rate
                                      ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY cxc.contr_xs_copy_pk) mtr_copy_rn
--                                      ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY xs_mtr_amt_rate ) mtr_amt_rn
                                FROM contr_xs_copy cxc
                                    ,CANON_E633_CONTRACTS_STG_TBL B
                                WHERE 1 = 1 --xs_mtr_first_flg = 'N'
                                  AND ezcancelflag = '0'
                                  AND glbl_cmpy_cd = 'ADB'
                                  AND b.ds_contr_bllg_mtr_pk = cxc.ds_contr_bllg_mtr_pk
                                ) MT2
                            WHERE MT2.MTR_COPY_RN = 1)mtr
                ON ( a.ds_contr_bllg_mtr_pk = mtr.ds_contr_bllg_mtr_pk
                     AND (a.lfs_sf_status_flag IN ('I','U') OR a.pps_sf_status_flag IN ('I','U')))
                WHEN MATCHED THEN UPDATE
                    SET a.BOUNDARY_1_BUSINE = mtr.xs_mtr_copy_qty
--                        ,a.contr_xs_copy_pk = mtr.contr_xs_copy_pk
                        ,a.FIRST_COPY_PRICE_1 = mtr.xs_mtr_amt_rate     ;
              dbms_output.put_line(SQL%ROWCOUNT||' records merged for Boundary_1 and first_copy_price_1.');
			  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records merged for Boundary_1 and first_copy_price_1.',NULL,NULL,NULL,NULL);

                MERGE INTO canon_e633_contracts_stg_tbl a
                USING ( SELECT distinct contr_xs_copy_pk, ds_contr_bllg_mtr_pk, xs_mtr_copy_qty, xs_mtr_amt_rate
                        FROM( SELECT rownum rn
                                      ,cxc.contr_xs_copy_pk
                                      ,cxc.ds_contr_bllg_mtr_pk
                                      ,xs_mtr_copy_qty
                                      ,xs_mtr_amt_rate
                                      ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY cxc.contr_xs_copy_pk) mtr_copy_rn
                                     -- ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY xs_mtr_amt_rate ) mtr_amt_rn
                                FROM contr_xs_copy cxc
                                    ,canon_e633_contracts_stg_tbl b
                                WHERE 1 = 1 --xs_mtr_first_flg = 'N'
                                  AND ezcancelflag = '0'
                                  AND glbl_cmpy_cd = 'ADB'
                                  AND b.ds_contr_bllg_mtr_pk = cxc.ds_contr_bllg_mtr_pk
                                ) mt2
                            WHERE mt2.mtr_copy_rn = 2
                              --AND mt2.mtr_amt_rn = 1
                         )mtr
                ON (  a.ds_contr_bllg_mtr_pk = mtr.ds_contr_bllg_mtr_pk
                    AND (a.lfs_sf_status_flag IN ('I','U') OR a.pps_sf_status_flag IN ('I','U')))
                WHEN MATCHED THEN UPDATE
                    SET a.BOUNDARY_2_BUSINE = mtr.xs_mtr_copy_qty
--                        ,a.contr_xs_copy_pk = mtr.contr_xs_copy_pk
                        ,a.FIRST_COPY_PRICE_2 = mtr.xs_mtr_amt_rate
                ;
                 dbms_output.put_line(SQL%ROWCOUNT||' records merged for Boundary_2 and first_copy_price_2.');
				 canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records merged for Boundary_2 and first_copy_price_2.',NULL,NULL,NULL,NULL);

            MERGE INTO canon_e633_contracts_stg_tbl a
                USING(SELECT distinct contr_xs_copy_pk, ds_contr_bllg_mtr_pk, xs_mtr_copy_qty, xs_mtr_amt_rate
                        FROM ( SELECT rownum rn
                                      ,cxc.contr_xs_copy_pk
                                      ,cxc.ds_contr_bllg_mtr_pk
                                      ,xs_mtr_copy_qty
                                      ,xs_mtr_amt_rate
                                      ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY cxc.contr_xs_copy_pk) mtr_copy_rn
                                     -- ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY xs_mtr_amt_rate) mtr_amt_rn
                                FROM contr_xs_copy cxc
                                    ,canon_e633_contracts_stg_tbl b
                                WHERE 1 = 1 --xs_mtr_first_flg = 'N'
                                  AND ezcancelflag = '0'
                                  AND glbl_cmpy_cd = 'ADB'
                                  AND b.ds_contr_bllg_mtr_pk = cxc.ds_contr_bllg_mtr_pk
                                ) mt2
                            WHERE mt2.mtr_copy_rn = 3
                              --AND mt2.mtr_amt_rn = 2
                         )mtr
                    ON( a.ds_contr_bllg_mtr_pk = mtr.ds_contr_bllg_mtr_pk
                        AND (a.lfs_sf_status_flag IN ('I','U') OR a.pps_sf_status_flag IN ('I','U')))
                WHEN MATCHED THEN UPDATE
                    SET a.BOUNDARY_3_BUSINE = mtr.xs_mtr_copy_qty
--                        ,a.contr_xs_copy_pk = mtr.contr_xs_copy_pk
                        ,a.FIRST_COPY_PRICE_3 = mtr.xs_mtr_amt_rate
                ;
                 dbms_output.put_line(SQL%ROWCOUNT||' records merged for Boundary_3 and first_copy_price_3.');
				 canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records merged for Boundary_3 and first_copy_price_3.',NULL,NULL,NULL,NULL);

                MERGE INTO canon_e633_contracts_stg_tbl a
                USING(SELECT distinct contr_xs_copy_pk, ds_contr_bllg_mtr_pk, xs_mtr_copy_qty, xs_mtr_amt_rate
                        FROM ( SELECT rownum rn
                                      ,cxc.contr_xs_copy_pk
                                      ,cxc.ds_contr_bllg_mtr_pk
                                      ,xs_mtr_copy_qty
                                      ,xs_mtr_amt_rate
                                      ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY cxc.contr_xs_copy_pk ) mtr_copy_rn
                                    --  ,ROW_NUMBER() OVER (PARTITION BY cxc.ds_contr_bllg_mtr_pk ORDER BY xs_mtr_amt_rate) mtr_amt_rn
                                FROM contr_xs_copy cxc
                                    ,canon_e633_contracts_stg_tbl b
                                WHERE 1 = 1 --xs_mtr_first_flg = 'N'
                                  AND ezcancelflag = '0'
                                  AND glbl_cmpy_cd = 'ADB'
                                  AND b.ds_contr_bllg_mtr_pk = cxc.ds_contr_bllg_mtr_pk
                                ) mt3
                            WHERE mt3.mtr_copy_rn = 4
                              --AND mt3.mtr_amt_rn = 3
                         )mtr
                    ON( a.ds_contr_bllg_mtr_pk = mtr.ds_contr_bllg_mtr_pk
                    AND (a.lfs_sf_status_flag IN ('I','U') OR a.pps_sf_status_flag IN ('I','U')))
                WHEN MATCHED THEN UPDATE
                    SET a.FIRST_COPY_PRICE_4 = mtr.xs_mtr_amt_rate
                ;
                 dbms_output.put_line(SQL%ROWCOUNT||' records merged for first_copy_price_4.');
				 canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records merged for first_copy_price_4.',NULL,NULL,NULL,NULL);


            MERGE
             INTO canon_e633_contracts_stg_tbl a
            USING(SELECT distinct b.rowid row_id
                        ,c.account_name
                        ,c.address || ', ' ||
                         c.town || ', ' ||
                         c.geo_area  || ', ' ||
                         c.country  || ' - ' ||
                         c.postal_code address
                    FROM canon_e633_contracts_stg_tbl b
                        ,canon_e633_cust_site_stg_tbl c
                   WHERE 1 = 1
                     AND b.address_number = c.location_number
                     AND b.lfs_sf_status_flag IN ('I', 'U') -- no need to check pps because both status flags are marked for insert or update together here.
                 ) b
               ON(a.rowid = b.row_id)
             WHEN MATCHED THEN
           UPDATE
              SET bill_to_acct_name = b.account_name
                 ,bill_to_address = b.address;

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated with bill to information.');
            dbms_output.put_line(SQL%ROWCOUNT||' records updated with bill to information.');
--fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated for bill_to information');
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated with bill to information.',NULL,NULL,NULL,NULL);


            BEGIN

            UPDATE canon_s21_cd_val_tbl v
--               SET v.val76 = TO_DATE(TO_CHAR(l_program_start_date,'YYYY-MON-DD HH24:MI:SS'),'YYYY-MON-DD HH24:MI:SS')
               SET v.val76 = l_program_start_date
             WHERE v.val1 = 'CONTRACTS'
               AND v.cd_id = (SELECT cd_id
                               FROM canon_s21_cd_tbl
                               WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
        EXCEPTION
         WHEN OTHERS THEN
           dbms_output.put_line('update CONTRACTS last run date exception: '|| SQLERRM);
		   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
        END;

         COMMIT;

EXCEPTION
    WHEN OTHERS THEN
    retcode := 2;
              errbuff := SQLERRM;
--              fnd_file.put_line(fnd_file.log, l_procedure_name || ': ' || SQLERRM);
        dbms_output.put_line('Contracts extract exception: '|| SQLERRM);
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);

END load_contracts;

PROCEDURE load_closed_service_calls(retcode OUT VARCHAR2,errbuff OUT VARCHAR2)
IS
    l_procedure_name varchar2(30) := 'load_closed_service_calls';
    l_last_run_date DATE;
--            l_program_start_date TIMESTAMP := SYSTIMESTAMP;
            l_program_start_date DATE := SYSDATE;
            l_last_run_date_num VARCHAR2(17);

BEGIN
        retcode := '0';

         BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'CLOSE_SVC_CALLS';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date,'RRRRMMDDHH24MISS');
    END;

	  --QC58464 --start
         UPDATE canon_e633_closecalls_stg_tbl stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND LFS_SF_CLOSE_SERVICE_ID IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC58464 --start
         UPDATE canon_e633_closecalls_stg_tbl stg
            SET pps_sf_status_flag =  'I'
            WHERE  pps_sf_status_flag IS NULL
              AND PPS_SF_CLOSE_SERVICE_ID IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

    --QC56364 - archive
	INSERT INTO canon_e633_closecalls_stg_arc (LEGACY_REF_ID
												,CONFIGURATION_NUMBER
												,JOBNUMBER
												,ARRIVAL_DATE
												,ACTION_CODE
												,CAUSE_CODE
												,MODULE_CODE
												,TIME_BETWEEN_JOBS
												,TIME_BETWEEN_FAILURE
												,COPIES_BETWEEN_JOBS
												,COPIES_BETWEEN_FAILURE
												,DE_INSTALLATION_JOB
												,INSTALLATION_JOB
												,CM_JOB
												,PM_JOB
												,LAST_VISIT_TECHNICIAN
												,CLOSE
												,CARD_CODE
												,CLOSED_DATE
												,REASON_CANCEL_CODE
												,JOB_STATUS
												,ADDRESS_NUMBER
												,SOURCE
												,FREE_TO_USE_1
												,FREE_TO_USE_2
												,FREE_TO_USE_3
												,FREE_TO_USE_4
												,FREE_TO_USE_5
												,FREE_TO_USE_6
												,CALL_CREATION_DATE
												,LAST_VISIT_TECH
												,ACCOUNT_NAME
												,ADDRESS
												,UNIQUE_KEY
												,LFS_SF_IB_ID
												,LFS_SF_ACCOUNT_ID
												,LFS_SF_CLOSE_SERVICE_ID
												,LFS_SF_STATUS_FLAG
												,LFS_SF_STATUS_MESSAGE
												,LFS_LAST_UPDATE_DATE
												,PPS_SF_IB_ID
												,PPS_SF_ACCOUNT_ID
												,PPS_SF_CLOSE_SERVICE_ID
												,PPS_SF_STATUS_FLAG
												,PPS_SF_STATUS_MESSAGE
												,PPS_LAST_UPDATE_DATE
												,LFSBU
												,DPSBU
												,PPSBU
												,SVC_MACH_MSTR_PK
												,FSR_NUM
												,created_date) SELECT LEGACY_REF_ID
																,CONFIGURATION_NUMBER
																,JOBNUMBER
																,ARRIVAL_DATE
																,ACTION_CODE
																,CAUSE_CODE
																,MODULE_CODE
																,TIME_BETWEEN_JOBS
																,TIME_BETWEEN_FAILURE
																,COPIES_BETWEEN_JOBS
																,COPIES_BETWEEN_FAILURE
																,DE_INSTALLATION_JOB
																,INSTALLATION_JOB
																,CM_JOB
																,PM_JOB
																,LAST_VISIT_TECHNICIAN
																,CLOSE
																,CARD_CODE
																,CLOSED_DATE
																,REASON_CANCEL_CODE
																,JOB_STATUS
																,ADDRESS_NUMBER
																,SOURCE
																,FREE_TO_USE_1
																,FREE_TO_USE_2
																,FREE_TO_USE_3
																,FREE_TO_USE_4
																,FREE_TO_USE_5
																,FREE_TO_USE_6
																,CALL_CREATION_DATE
																,LAST_VISIT_TECH
																,ACCOUNT_NAME
																,ADDRESS
																,UNIQUE_KEY
																,LFS_SF_IB_ID
																,LFS_SF_ACCOUNT_ID
																,LFS_SF_CLOSE_SERVICE_ID
																,LFS_SF_STATUS_FLAG
																,LFS_SF_STATUS_MESSAGE
																,LFS_LAST_UPDATE_DATE
																,PPS_SF_IB_ID
																,PPS_SF_ACCOUNT_ID
																,PPS_SF_CLOSE_SERVICE_ID
																,PPS_SF_STATUS_FLAG
																,PPS_SF_STATUS_MESSAGE
																,PPS_LAST_UPDATE_DATE
																,LFSBU
																,DPSBU
																,PPSBU
																,SVC_MACH_MSTR_PK
																,FSR_NUM, sysdate
														FROM canon_e633_closecalls_stg_tbl cls
													   WHERE 1 = 1
														 AND EXISTS (SELECT 1
																	  FROM canon_e633_ib_del_tbl ib
																	 WHERE 1 = 1
																	   AND cls.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                                                                      AND ib.config_nbr = cls.configuration_number
                                                                      AND NVL(lfs_sf_status_flag,'P') IN ( 'I','P','DP','E')
                                                                       AND NVL(pps_sf_status_flag,'P') IN ('I', 'P','DP','E')
                                                                       AND NVL(cls.lfs_sf_ib_id, NVL(ib.lfs_sf_ib_id,'XX')) = NVL(ib.lfs_sf_ib_id,'XX')
                                                                       AND NVL(cls.pps_sf_ib_id, NVL(ib.pps_sf_ib_id,'XX')) = NVL(ib.pps_sf_ib_id,'XX')
                                                                       AND NVL(cls.lfs_sf_account_id, NVL(ib.lfs_sf_account_id,'XX')) = NVL(ib.lfs_sf_account_id,'XX')
                                                                       AND NVL(cls.pps_sf_account_id, NVL(ib.pps_sf_account_id,'XX')) = NVL(ib.pps_sf_account_id,'XX'));
    DBMS_OUTPUT.PUT_LINE('No. of records archived : ' ||sql%rowcount);
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records archived.',NULL,NULL,NULL,NULL);
	commit;

	--QC56364 -- delete archived records
	DELETE
	  FROM canon_e633_closecalls_stg_tbl cls
	 WHERE 1 = 1
	   AND EXISTS (SELECT 1
				     FROM canon_e633_ib_del_tbl ib
				    WHERE 1 = 1
				      AND cls.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                      AND ib.config_nbr = cls.configuration_number
                      AND NVL(lfs_sf_status_flag,'P') IN ( 'I','P','DP', 'E')
                       AND NVL(pps_sf_status_flag,'P') IN ('I', 'P','DP','E')
                       AND NVL(cls.lfs_sf_ib_id, NVL(ib.lfs_sf_ib_id,'XX')) = NVL(ib.lfs_sf_ib_id,'XX')
                       AND NVL(cls.pps_sf_ib_id, NVL(ib.pps_sf_ib_id,'XX')) = NVL(ib.pps_sf_ib_id,'XX')
                       AND NVL(cls.lfs_sf_account_id, NVL(ib.lfs_sf_account_id,'XX')) = NVL(ib.lfs_sf_account_id,'XX')
                       AND NVL(cls.pps_sf_account_id, NVL(ib.pps_sf_account_id,'XX')) = NVL(ib.pps_sf_account_id,'XX'));
	DBMS_OUTPUT.PUT_LINE('No. of records deleted from stg : ' ||sql%rowcount);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records deleted from stg.',NULL,NULL,NULL,NULL);
	commit;

 --Delete FSR null records which are not in SFDC
 Delete from s21_csa_extn.canon_e633_closecalls_stg_tbl where
                     unique_key<>jobnumber
                  and fsr_num is null
                  and PPS_SF_IB_ID is null
                  and LFS_SF_IB_ID is null
                  and lfs_sf_status_flag='I'
                  and pps_sf_status_flag ='I';
    DBMS_OUTPUT.PUT_LINE('No. of records deleted from close service stg for Fsr value Null : ' ||sql%rowcount);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records deleted from stg.',NULL,NULL,NULL,NULL);
	commit;
	--End Deletion of FSR Null records
		UPDATE canon_e633_closecalls_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_close_service_id IS NOT NULL THEN 'U' ELSE 'I' END
          WHERE lfs_sf_status_flag = 'E' ;

        DBMS_OUTPUT.PUT_LINE('No. of LFS error records updated to U : ' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of LFS error records updated to U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

          UPDATE canon_e633_closecalls_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_close_service_id IS NOT NULL THEN 'U' ELSE 'I' END
          WHERE pps_sf_status_flag = 'E' ;

        DBMS_OUTPUT.PUT_LINE('No. of PPS error records updated to U : ' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U : ' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

         COMMIT;
 --58464--uniquekeychanges
        UPDATE canon_e633_closecalls_stg_tbl SET unique_key =jobnumber
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
                 ,lfs_sf_status_flag = (CASE lfs_sf_close_service_id WHEN NULL THEN 'I' ELSE 'U' END)
                  ,pps_sf_status_flag = (CASE pps_sf_close_service_id WHEN NULL THEN 'I' ELSE 'U' END)
		 WHERE unique_key !=jobnumber
            and jobnumber = fsr_num;
            DBMS_OUTPUT.PUT_LINE('No. of records for unique_key changes updated is : ' ||sql%rowcount);
            canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',' unique_key changes updated :'||sql%ROWCOUNT,NULL,NULL,NULL,NULL);
		COMMIT;
        --end uniquekey changes

         MERGE INTO canon_e633_closecalls_stg_tbl stg
            USING (SELECT distinct f.fsr_num
                       ,NULL LEGACY_REF_ID
                       ,f.svc_mach_mstr_pk
                       ,smm.svc_config_mstr_pk CONFIGURATION_NUMBER
                     --  ,fv.svc_task_num JOBNUMBER
					   ,f.fsr_num JOBNUMBER
                       ,fv.fsr_visit_arv_dt ARRIVAL_DATE
                       ,spct.SVC_PBLM_CRCT_TP_NM ACTION_CODE
                       ,sptc.SVC_PBLM_TP_NM CAUSE_CODE
                       ,NULL MODULE_CODE
                        ,NULL TIME_BETWEEN_JOBS
                       ,NULL TIME_BETWEEN_FAILURE
                       ,NULL COPIES_BETWEEN_JOBS
                       ,NULL COPIES_BETWEEN_FAILURE
                       ,(CASE WHEN (dsct.ds_svc_call_tp_nm LIKE 'HARD DRIVE REMOVAL INSTALL' )THEN 'Y' ELSE 'N' END)DE_INSTALLATION_JOB
                       ,(CASE WHEN (dsct.ds_svc_call_tp_nm LIKE '%INSTALL%' AND dsct.ds_svc_call_tp_nm NOT LIKE 'HARD DRIVE REMOVAL INSTALL' )THEN 'Y' ELSE 'N' END)INSTALLATION_JOB
                       ,(CASE WHEN dsct.ds_svc_call_tp_nm = 'SERV CALL' THEN 'Y' ELSE 'N' END)CM_JOB
                       ,(CASE WHEN dsct.ds_svc_call_tp_nm = 'PREVENTATIVE MAITENANCE' THEN 'Y' ELSE 'N' END) PM_JOB
                       ,NULL CARD_CODE
                       ,'Y' CLOSE
                       ,f.fsr_clo_dt CLOSED_DATE
                       ,smr.svc_memo_rsn_nm REASON_CANCEL_CODE
                       ,f.fsr_crat_dt CALL_CREATION_DATE
                       ,sts.svc_task_sts_nm JOB_STATUS
                       ,PSN_LAST_NM || ', ' ||PSN_FIRST_NM LAST_VISIT_TECH
                       --,psn.psn_num LAST_VISIT_TECH
                       ,stc.loc_num ADDRESS_NUMBER
                       ,scst.svc_call_src_tp_nm SOURCE
                       ,NULL FREE_TO_USE_1
                      ,NULL FREE_TO_USE_2
                      ,NULL FREE_TO_USE_3
                      ,NULL FREE_TO_USE_4
                      ,NULL FREE_TO_USE_5
                      ,NULL FREE_TO_USE_6
                  FROM fsr f
                       ,svc_mach_mstr smm
                        ,(SELECT *
                           FROM(SELECT row_number() over (partition by fsr_num order by fsr_visit_num desc) rn, fv.*
                                  from fsr_visit fv
                                 where glbl_cmpy_cd = 'ADB'
                                   and ezcancelflag = '0')
                           WHERE rn = 1) fv
                       ,(SELECT *
                           FROM(SELECT row_number() over (partition by fsr_num order by fsr_visit_num desc) rn, fv.*
                                  from fsr_visit_task fv
                                 where glbl_cmpy_cd = 'ADB'
                                   and ezcancelflag = '0')
                           WHERE rn = 1) fvt
                       ,svc_pblm_crct_tp spct
                       ,ds_svc_call_tp dsct
                       ,svc_task sv
                       ,svc_task_sts sts
                       /* ,(select distinct svc_task_num, fsr_num, svc_memo_tp_cd, first_value(svc_memo_rsn_cd) OVER(PARTITION BY svc_task_num ORDER BY svc_memo_rsn_cd nulls last) svc_memo_rsn_cd
                            from svc_memo
                            where glbl_cmpy_cd = 'ADB'
                            and ezcancelflag = '0') sm*/ --QC58464
                        ,(select distinct fsr_num, svc_memo_tp_cd, first_value(svc_memo_rsn_cd) OVER(PARTITION BY fsr_num ORDER BY svc_memo_rsn_cd,last_upd_ts desc nulls last) svc_memo_rsn_cd
                            from svc_memo
                            where glbl_cmpy_cd = 'ADB'
                            and ezcancelflag = '0'
                            AND svc_memo_tp_cd = '17') sm --QC58464
                       ,svc_memo_rsn smr
                       ,s21_psn psn
                       ,ship_to_cust stc
                       ,svc_call_src_tp scst
						 ,SVC_PBLM_TP sptc
                 WHERE f.glbl_cmpy_cd = 'ADB'
                   AND f.ezcancelflag = '0'
                   AND smm.glbl_cmpy_cd ='ADB'
                   AND smm.ezcancelflag = '0'
--                   AND fv.glbl_cmpy_cd = 'ADB'
--                   AND fv.ezcancelflag = '0'
--                   AND fvt.glbl_cmpy_cd(+) = 'ADB'
--                   AND fvt.ezcancelflag(+) = '0'
                   AND spct.glbl_cmpy_cd(+) = 'ADB'
                   AND spct.ezcancelflag(+) = '0'
                   AND sv.glbl_cmpy_cd = 'ADB'
                   AND sv.ezcancelflag = '0'
                   AND dsct.glbl_cmpy_cd = 'ADB'
                   AND dsct.ezcancelflag = '0'
                   AND sts.glbl_cmpy_cd = 'ADB'
                   AND sts.ezcancelflag = '0'
--                   AND sm.glbl_cmpy_cd(+) = 'ADB'
--                   AND sm.ezcancelflag(+) = '0'
                   AND smr.glbl_cmpy_cd(+) = 'ADB'
                   AND smr.ezcancelflag(+) = '0'
                   AND psn.glbl_cmpy_cd(+) = 'ADB'
                   AND psn.ezcancelflag(+) = '0'
                   AND stc.ezcancelflag = '0'
                   AND stc.glbl_cmpy_cd = 'ADB'
                   AND scst.ezcancelflag = '0'
                   AND scst.glbl_cmpy_cd = 'ADB'
                   AND sptc.ezcancelflag = '0'
                   AND sptc.glbl_cmpy_cd = 'ADB'
                   AND f.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                   AND f.ship_to_cust_cd = smm.ship_to_cust_cd --QC57878
                   AND sts.svc_task_sts_cd = sv.svc_task_sts_cd
                   --AND sts.open_task_flg = 'N' --for closed calls
				   AND f.fsr_sts_cd  = '95' --for closed calls
               --    AND (smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') OR smm.svc_by_line_biz_tp_cd IN ('PPS','LFS'))
				   AND smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') -- as suggested by Chris 12202018
                   AND f.fsr_num = fv.fsr_num
                   AND fv.svc_task_num = fvt.svc_task_num(+)
                   AND fvt.svc_pblm_crct_tp_cd = spct.svc_pblm_crct_tp_cd(+)
				   AND fvt.svc_pblm_tp_cd = sptc.svc_pblm_tp_cd(+)
                   AND fv.fsr_num = sv.fsr_num
				   AND fv.fsr_visit_ltst_flg = 'Y' -- new
                   AND fv.svc_task_num = sv.svc_task_num
                   AND sv.ds_svc_call_tp_cd = dsct.ds_svc_call_tp_cd
                   AND fv.fsr_num = sm.fsr_num(+)
--                   AND fv.svc_task_num = sm.svc_task_num (+) --QC58464
--				   AND sm.svc_memo_tp_cd(+) = '17' --QC58464
                   AND sm.svc_memo_rsn_cd = smr.svc_memo_rsn_cd(+)
                   AND sv.tech_cd = psn.psn_cd(+)
                   AND f.ship_to_cust_cd = stc.ship_to_cust_cd
                   AND scst.svc_call_src_tp_cd = f.svc_call_src_tp_cd
				   AND (
--					    TO_NUMBER(f.ezuptime) >= TO_NUMBER(l_last_run_date_num) OR
--					    TO_NUMBER(fv.ezuptime) >= TO_NUMBER(l_last_run_date_num) OR
-- 					    TO_NUMBER(sv.ezuptime) >= TO_NUMBER(l_last_run_date_num)
						CAST(TO_TIMESTAMP (f.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
						CAST(TO_TIMESTAMP (fv.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
						CAST(TO_TIMESTAMP (sv.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
					   )
                   ) s21
             ON (stg.jobnumber = s21.jobnumber)
          WHEN MATCHED THEN UPDATE
                SET stg.LEGACY_REF_ID = s21.LEGACY_REF_ID
                      ,stg.fsr_num = s21.fsr_num
                      ,stg.svc_mach_mstr_pk = s21.svc_mach_mstr_pk
                      ,stg.CONFIGURATION_NUMBER = s21.CONFIGURATION_NUMBER
                      ,stg.ARRIVAL_DATE = s21.ARRIVAL_DATE
                      ,stg.ACTION_CODE = s21.ACTION_CODE
                      ,stg.CAUSE_CODE = s21.CAUSE_CODE
                      ,stg.MODULE_CODE = s21.MODULE_CODE
                      ,stg.TIME_BETWEEN_JOBS = s21.TIME_BETWEEN_JOBS
                      ,stg.TIME_BETWEEN_FAILURE = s21.TIME_BETWEEN_FAILURE
                      ,stg.COPIES_BETWEEN_JOBS = s21.COPIES_BETWEEN_JOBS
                      ,stg.COPIES_BETWEEN_FAILURE = s21.COPIES_BETWEEN_FAILURE
                      ,stg.DE_INSTALLATION_JOB = s21.DE_INSTALLATION_JOB
                      ,stg.INSTALLATION_JOB = s21.INSTALLATION_JOB
                      ,stg.CM_JOB = s21.CM_JOB
                      ,stg.PM_JOB = s21.PM_JOB
                      ,stg.LAST_VISIT_TECHNICIAN = s21.LAST_VISIT_TECH
                      ,stg.CLOSE = s21.CLOSE
                      ,stg.CARD_CODE = s21.CARD_CODE
                      ,stg.CLOSED_DATE = s21.CLOSED_DATE
                      ,stg.REASON_CANCEL_CODE = s21.REASON_CANCEL_CODE
                      ,stg.JOB_STATUS = s21.JOB_STATUS
                      ,stg.ADDRESS_NUMBER = s21.ADDRESS_NUMBER
                      ,stg.SOURCE = s21.SOURCE
                      ,stg.FREE_TO_USE_1 = s21.FREE_TO_USE_1
                      ,stg.FREE_TO_USE_2 = s21.FREE_TO_USE_2
                      ,stg.FREE_TO_USE_3 = s21.FREE_TO_USE_3
                      ,stg.FREE_TO_USE_4 = s21.FREE_TO_USE_4
                      ,stg.FREE_TO_USE_5 = s21.FREE_TO_USE_5
                      ,stg.FREE_TO_USE_6 = s21.FREE_TO_USE_6
                      ,stg.CALL_CREATION_DATE = s21.CALL_CREATION_DATE
                      ,stg.last_visit_tech = s21.last_visit_tech
                      ,stg.lfs_sf_status_flag = (CASE lfs_sf_close_service_id WHEN NULL THEN 'I' ELSE 'U' END)
                      ,stg.pps_sf_status_flag = (CASE pps_sf_close_service_id WHEN NULL THEN 'I' ELSE 'U' END)
                      ,stg.lfs_sf_status_message = NULL
                      ,stg.pps_sf_status_message = NULL
                      ,stg.lfs_last_update_date = SYSDATE
                      ,stg.pps_last_update_date = SYSDATE
            WHEN NOT MATCHED THEN INSERT(
                      LEGACY_REF_ID
                      ,fsr_num
                      ,svc_mach_mstr_pk
                      ,CONFIGURATION_NUMBER
                      ,JOBNUMBER
                      ,ARRIVAL_DATE
                      ,ACTION_CODE
                      ,CAUSE_CODE
                      ,MODULE_CODE
                      ,TIME_BETWEEN_JOBS
                      ,TIME_BETWEEN_FAILURE
                      ,COPIES_BETWEEN_JOBS
                      ,COPIES_BETWEEN_FAILURE
                      ,DE_INSTALLATION_JOB
                      ,INSTALLATION_JOB
                      ,CM_JOB
                      ,PM_JOB
                      ,LAST_VISIT_TECHNICIAN
                      ,CLOSE
                      ,CARD_CODE
                      ,CLOSED_DATE
                      ,REASON_CANCEL_CODE
                      ,JOB_STATUS
                      ,ADDRESS_NUMBER
                      ,SOURCE
                      ,FREE_TO_USE_1
                      ,FREE_TO_USE_2
                      ,FREE_TO_USE_3
                      ,FREE_TO_USE_4
                      ,FREE_TO_USE_5
                      ,FREE_TO_USE_6
                      ,CALL_CREATION_DATE
                      ,last_visit_tech
                      ,lfs_sf_status_flag
                      ,pps_sf_status_flag
                      ,lfs_sf_status_message
                      ,pps_sf_status_message
                      ,lfs_last_update_date
                      ,pps_last_update_date
                      ,Unique_key) VALUES (
                                              s21.LEGACY_REF_ID
                                              ,s21.fsr_num
                                              ,s21.svc_mach_mstr_pk
                                              ,s21.CONFIGURATION_NUMBER
                                              ,s21.JOBNUMBER
                                              ,s21.ARRIVAL_DATE
                                              ,s21.ACTION_CODE
                                              ,s21.CAUSE_CODE
                                              ,s21.MODULE_CODE
                                              ,s21.TIME_BETWEEN_JOBS
                                              ,s21.TIME_BETWEEN_FAILURE
                                              ,s21.COPIES_BETWEEN_JOBS
                                              ,s21.COPIES_BETWEEN_FAILURE
                                              ,s21.DE_INSTALLATION_JOB
                                              ,s21.INSTALLATION_JOB
                                              ,s21.CM_JOB
                                              ,s21.PM_JOB
                                              ,s21.LAST_VISIT_TECH
                                              ,s21.CLOSE
                                              ,s21.CARD_CODE
                                              ,s21.CLOSED_DATE
                                              ,s21.REASON_CANCEL_CODE
                                              ,s21.JOB_STATUS
                                              ,s21.ADDRESS_NUMBER
                                              ,s21.SOURCE
                                              ,s21.FREE_TO_USE_1
                                              ,s21.FREE_TO_USE_2
                                              ,s21.FREE_TO_USE_3
                                              ,s21.FREE_TO_USE_4
                                              ,s21.FREE_TO_USE_5
                                              ,s21.FREE_TO_USE_6
                                              ,s21.CALL_CREATION_DATE
                                              ,s21.last_visit_tech
                                              ,'I'
                                              ,'I'
                                              ,NULL
                                              ,NULL
                                              ,sysdate
                                              ,sysdate
                                              ,s21.jobnumber);
--        fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' merged into stg table.');
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' merged into stg table.',NULL,NULL,NULL,NULL);





	MERGE INTO canon_e633_closecalls_stg_tbl a
	 USING  (select row_id, ARRIVAL_DATE, mth, dt, yr
	           from (select a.rowid row_id
							,ARRIVAL_DATE
							,TO_NUMBER(substr(replace(arrival_date,'/',''), 5, 2)) mth
							,TO_NUMBER(substr(replace(arrival_date,'/',''), 7, 2)) dt
							,TO_NUMBER(substr(replace(arrival_date,'/',''), 1, 4)) yr
					  from canon_e633_closecalls_stg_tbl a
					 where (pps_sf_Status_flag  IN ('I', 'E', 'U') OR pps_sf_Status_flag  IN ('I', 'E', 'U'))
   					   and arrival_date != '0   /  /'
					   and length(arrival_date) >= 8)
				where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
						 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
						 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
						 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						ELSE 'VALID'
					 END = 'ERROR'   ) b
		 ON (a.rowid = b.row_id)
		WHEN MATCHED THEN
	  UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message||'Arrival Date is Invalid.'
			 ,pps_sf_status_message = pps_sf_status_message||'Arrival Date is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE
			 ;

        DBMS_OUTPUT.PUT_LINE('No. of arrival date error records.' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of arrival date error records.' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records for invalid arrival date.',NULL,NULL,NULL,NULL);

	UPDATE canon_e633_closecalls_stg_tbl
               SET lfs_sf_status_flag = 'E'
                   , pps_sf_status_flag = 'E'
                   ,lfs_sf_status_message = 'Invalid Arrival Date.'
                   , pps_sf_status_message = 'Invalid Arrival Date.'
                   , lfs_last_update_Date = SYSDATE
                   , pps_last_update_date = SYSDATE
            WHERE arrival_date = '0   /  /'
              AND lfs_sf_status_Flag IN ('I', 'U');
--          fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' updated for invalid arrival date for ''0   /  /''.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' updated for invalid arrival date for ''0   /  /''.',NULL,NULL,NULL,NULL);
		COMMIT;

	UPDATE canon_e633_closecalls_stg_tbl
               SET lfs_sf_status_flag = 'E'
                   , pps_sf_status_flag = 'E'
                   ,lfs_sf_status_message = 'Invalid Closed Date.'
                   , pps_sf_status_message = 'Invalid Closed Date.'
                   , lfs_last_update_Date = SYSDATE
                   , pps_last_update_date = SYSDATE
            WHERE closed_date = '0   /  /'
              AND lfs_sf_status_Flag IN ('I', 'U');
--          fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' updated for invalid closed date for ''0   /  /''.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' updated for invalid closed date for ''0   /  /''.',NULL,NULL,NULL,NULL);
		COMMIT;

		MERGE INTO canon_e633_closecalls_stg_tbl a
		USING  (select row_id, closed_date, mth, dt, yr
		          from (select a.rowid row_id
							   ,closed_date
							   ,TO_NUMBER(substr(replace(closed_date,'/',''), 5, 2)) mth
							   ,TO_NUMBER(substr(replace(closed_date,'/',''), 7, 2)) dt
							   ,TO_NUMBER(substr(replace(closed_date,'/',''), 1, 4)) yr
						 from canon_e633_closecalls_stg_tbl a
						where (lfs_sf_Status_flag  IN ('I', 'E', 'U') OR pps_sf_Status_flag  IN ('I', 'E', 'U'))
						  and closed_date != '0   /  /'
						  and length(closed_date) >= 8
						)
				 where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							ELSE 'VALID'
						END = 'ERROR'   ) b
		 ON (a.rowid = b.row_id)
		WHEN MATCHED THEN
	  UPDATE
		 SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message||'closed_date is Invalid.'
			 ,pps_sf_status_message = pps_sf_status_message||'closed_date is Invalid.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE
			 ;
		 DBMS_OUTPUT.PUT_LINE('No. of invalid closed_date records.' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' updated for invalid closed_date.',NULL,NULL,NULL,NULL);

		UPDATE canon_e633_closecalls_stg_tbl
		SET lfs_sf_status_flag = 'E'
			 ,pps_sf_Status_flag = 'E'
			 ,lfs_sf_status_message = lfs_sf_status_message||'Call Creation Date is Missing.'
			 ,pps_sf_status_message = pps_sf_status_message||'Call Creation is Missing.'
			 ,lfs_last_update_date = SYSDATE
			 ,pps_last_update_date = SYSDATE
         WHERE call_creation_date is null
          and pps_sf_Status_flag  IN ('I', 'E', 'U');

        DBMS_OUTPUT.PUT_LINE('No. of missing call_creation_date records.' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' updated for invalid call_creation_date for ''0   /  /''.',NULL,NULL,NULL,NULL);
--        fnd_file.put_line(fnd_file.log, 'No. of missing call_creation_date records.' ||sql%rowcount);

		UPDATE canon_e633_closecalls_stg_tbl
               SET lfs_sf_status_flag = 'E'
                   , pps_sf_status_flag = 'E'
                   ,lfs_sf_status_message = 'Invalid Call Creation Date.'
                   , pps_sf_status_message = 'Invalid Call Creation Date.'
                   , lfs_last_update_Date = SYSDATE
                   , pps_last_update_date = SYSDATE
            WHERE call_creation_date = '0   /  /'
              AND lfs_sf_status_Flag IN ('I', 'U');
--          fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' updated for invalid call creation date for ''0   /  /''.');
			DBMS_OUTPUT.PUT_LINE('No. of invalid call_creation_date records : ' ||sql%rowcount);
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' updated for invalid call creation date for ''0   /  /''.',NULL,NULL,NULL,NULL);
		COMMIT;

		MERGE INTO canon_e633_closecalls_stg_tbl a
		USING  (select row_id, call_creation_date, mth, dt, yr
				  from (select a.rowid row_id
							   ,call_creation_date
							   ,TO_NUMBER(substr(replace(call_creation_date,'/',''), 5, 2)) mth
							   ,TO_NUMBER(substr(replace(call_creation_date,'/',''), 7, 2)) dt
							   ,TO_NUMBER(substr(replace(call_creation_date,'/',''), 1, 4)) yr
						 from canon_e633_closecalls_stg_tbl a
						where (lfs_sf_Status_flag  IN ('I', 'E', 'U') or pps_sf_Status_flag  IN ('I', 'E', 'U'))
						  and call_creation_date != '0   /  /'
						 and length(call_creation_date) >= 8
						)
				where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							ELSE 'VALID'
						END = 'ERROR'   ) b
			ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = lfs_sf_status_message||'call_creation_date is Invalid.'
				 ,pps_sf_status_message = pps_sf_status_message||'call_creation_date is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;
		DBMS_OUTPUT.PUT_LINE('No. of invalid call_creation_date records.' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' updated for invalid call_creation_date.',NULL,NULL,NULL,NULL);


           MERGE
             INTO canon_e633_closecalls_stg_tbl a
            USING(SELECT b.rowid row_id
                        ,c.account_name
                        ,c.address || ', ' ||
                         c.town || ', ' ||
                         c.geo_area  || ', ' ||
                         c.country  || ' - ' ||
                         c.postal_code address
                    FROM canon_e633_closecalls_stg_tbl b
                        ,canon_e633_cust_site_stg_tbl c
                   WHERE 1 = 1
                     AND b.address_number = c.location_number
                     AND b.lfs_sf_status_flag IN ('I', 'U') -- no need to check pps because both status flags are marked for insert or update together here.
                 ) b
               ON(a.rowid = b.row_id)
             WHEN MATCHED THEN
           UPDATE
              SET account_name = b.account_name
                 ,address = b.address;

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated with address information.');
            dbms_output.put_line(SQL%ROWCOUNT||' records updated with address information.');

			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated with address information.',NULL,NULL,NULL,NULL);
            BEGIN
				UPDATE canon_s21_cd_val_tbl v
				   SET v.val76 = l_program_start_date
				 WHERE v.val1 = 'CLOSE_SVC_CALLS'
				   AND v.cd_id = (SELECT cd_id
								   FROM canon_s21_cd_tbl
								   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
			   EXCEPTION
				 WHEN OTHERS THEN
				   dbms_output.put_line('update CLOSE_SVC_CALLS last run date exception: '|| SQLERRM);
				   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		    END;


       COMMIT;


EXCEPTION
 WHEN OTHERS THEN
        retcode := '2';
        errbuff := SQLERRM;
        dbms_output.put_line('load CLOSE_SVC_CALLS exception: '|| SQLERRM);
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END load_closed_service_calls;

PROCEDURE load_open_service_calls(retcode OUT VARCHAR2,errbuff OUT VARCHAR2)
IS l_procedure_name VARCHAR2(30) := 'load_open_service_calls';
--	l_last_run_date VARCHAR2(20);
--    l_program_start_date DATE := SYSDATE;
    l_last_run_date DATE;
--            l_program_start_date TIMESTAMP := SYSTIMESTAMP;
            l_program_start_date DATE := SYSDATE;
            l_last_run_date_num VARCHAR2(17);

BEGIN
        retcode := '0';

         BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'OPEN_SVC_CALLS';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;
    --QC58464 --start
         UPDATE canon_e633_opencalls_stg_tbl stg
            SET lfs_sf_status_flag =  'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_open_service_id IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_opencalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC54684 --start
         UPDATE canon_e633_opencalls_stg_tbl stg
            SET pps_sf_status_flag = 'I'
            WHERE  pps_sf_status_flag IS NULL
              AND pps_sf_open_service_id IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_opencalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464
--QC56364 - archive
	INSERT INTO canon_e633_opencalls_stg_arc (LEGACY_REF_ID
											,CONFIGURATION_NUMBER
											,JOBNUMBER
											,ARRIVAL_DATE
											,ACTION_CODE
											,CAUSE_CODE
											,MODULE_CODE
											,CLOSE
											,CARD_CODE
											,ADDRESS_NUMBER
											,SOURCE
											,FREE_TO_USE_1
											,FREE_TO_USE_2
											,FREE_TO_USE_3
											,FREE_TO_USE_4
											,FREE_TO_USE_5
											,FREE_TO_USE_6
											,CALL_CREATION_DATE
											,UNIQUE_KEY
											,LFS_SF_IB_ID
											,LFS_SF_ACCOUNT_ID
											,LFS_SF_OPEN_SERVICE_ID
											,LFS_SF_STATUS_FLAG
											,LFS_SF_STATUS_MESSAGE
											,LFS_LAST_UPDATE_DATE
											,PPS_SF_IB_ID
											,PPS_SF_ACCOUNT_ID
											,PPS_SF_OPEN_SERVICE_ID
											,PPS_SF_STATUS_FLAG
											,PPS_SF_STATUS_MESSAGE
											,PPS_LAST_UPDATE_DATE
											,LFSBU
											,DPSBU
											,PPSBU
											,ACCOUNT_NAME
											,ADDRESS
											,SVC_MACH_MSTR_PK
											,FSR_NUM
											,created_date) SELECT LEGACY_REF_ID
															,CONFIGURATION_NUMBER
															,JOBNUMBER
															,ARRIVAL_DATE
															,ACTION_CODE
															,CAUSE_CODE
															,MODULE_CODE
															,CLOSE
															,CARD_CODE
															,ADDRESS_NUMBER
															,SOURCE
															,FREE_TO_USE_1
															,FREE_TO_USE_2
															,FREE_TO_USE_3
															,FREE_TO_USE_4
															,FREE_TO_USE_5
															,FREE_TO_USE_6
															,CALL_CREATION_DATE
															,UNIQUE_KEY
															,LFS_SF_IB_ID
															,LFS_SF_ACCOUNT_ID
															,LFS_SF_OPEN_SERVICE_ID
															,LFS_SF_STATUS_FLAG
															,LFS_SF_STATUS_MESSAGE
															,LFS_LAST_UPDATE_DATE
															,PPS_SF_IB_ID
															,PPS_SF_ACCOUNT_ID
															,PPS_SF_OPEN_SERVICE_ID
															,PPS_SF_STATUS_FLAG
															,PPS_SF_STATUS_MESSAGE
															,PPS_LAST_UPDATE_DATE
															,LFSBU
															,DPSBU
															,PPSBU
															,ACCOUNT_NAME
															,ADDRESS
															,SVC_MACH_MSTR_PK
															,FSR_NUM, sysdate
													FROM canon_e633_opencalls_stg_tbl opn
												   WHERE 1 = 1
													 AND EXISTS (SELECT 1
																  FROM canon_e633_ib_del_tbl ib
																 WHERE 1 = 1
                                                                  AND opn.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                                                                  AND ib.config_nbr = opn.configuration_number
                                                                  AND NVL(lfs_sf_status_flag,'P') IN ( 'I','P','DP','E')
                                                                   AND NVL(pps_sf_status_flag,'P') IN ('I', 'P','DP','E')
                                                                   AND NVL(opn.lfs_sf_ib_id, NVL(ib.lfs_sf_ib_id,'XX')) = NVL(ib.lfs_sf_ib_id,'XX')
                                                                   AND NVL(opn.pps_sf_ib_id, NVL(ib.pps_sf_ib_id,'XX')) = NVL(ib.pps_sf_ib_id,'XX')
                                                                   AND NVL(opn.lfs_sf_account_id, NVL(ib.lfs_sf_account_id,'XX')) = NVL(ib.lfs_sf_account_id,'XX')
                                                                   AND NVL(opn.pps_sf_account_id, NVL(ib.pps_sf_account_id,'XX')) = NVL(ib.pps_sf_account_id,'XX'));
--																   AND (ib.lfs_sf_ib_id = opn.lfs_sf_ib_id OR ib.pps_sf_ib_id = opn.pps_sf_ib_id)
--																   AND (ib.lfs_sf_account_id = opn.lfs_sf_account_id OR ib.pps_sf_account_id = opn.pps_sf_account_id));
   DBMS_OUTPUT.PUT_LINE('No. of records archived : ' ||sql%rowcount);
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records archived.',NULL,NULL,NULL,NULL);

	commit;

	--QC56364 -- delete archived records
	DELETE
	  FROM canon_e633_opencalls_stg_tbl opn
	 WHERE 1 = 1
	   AND EXISTS (SELECT 1
				     FROM canon_e633_ib_del_tbl ib
				    WHERE 1 = 1
                      AND opn.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                      AND ib.config_nbr = opn.configuration_number
				      AND NVL(lfs_sf_status_flag,'P') IN ( 'I','P','DP','E')
                       AND NVL(pps_sf_status_flag,'P') IN ('I', 'P','DP','E')
                       AND NVL(opn.lfs_sf_ib_id, NVL(ib.lfs_sf_ib_id,'XX')) = NVL(ib.lfs_sf_ib_id,'XX')
                       AND NVL(opn.pps_sf_ib_id, NVL(ib.pps_sf_ib_id,'XX')) = NVL(ib.pps_sf_ib_id,'XX')
                       AND NVL(opn.lfs_sf_account_id, NVL(ib.lfs_sf_account_id,'XX')) = NVL(ib.lfs_sf_account_id,'XX')
                       AND NVL(opn.pps_sf_account_id, NVL(ib.pps_sf_account_id,'XX')) = NVL(ib.pps_sf_account_id,'XX'));
    DBMS_OUTPUT.PUT_LINE('No. of records deleted from stg : ' ||sql%rowcount);
	canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records deleted from stg.',NULL,NULL,NULL,NULL);
	commit;

	--DeleteRecord fsr null which are not in SFDC
 Delete from s21_csa_extn.canon_e633_opencalls_stg_tbl where
                     unique_key<>jobnumber
                  and fsr_num is null
                  and PPS_SF_IB_ID is null
                  and LFS_SF_IB_ID is null
                  and lfs_sf_status_flag='I'
                  and pps_sf_status_flag ='I';
    DBMS_OUTPUT.PUT_LINE('No. of records deleted from open service stg for Fsr value Null : ' ||sql%rowcount);
    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records deleted from stg.',NULL,NULL,NULL,NULL);
	commit;
    --End

--to reprocess error and unprocessed records
		UPDATE canon_e633_opencalls_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_open_service_id IS NOT NULL THEN 'U' ELSE 'I' END
		       ,lfs_sf_status_message = null  --QC55115
          WHERE lfs_sf_status_flag = 'E' ;

        DBMS_OUTPUT.PUT_LINE('No. of LFS error records updated to U : ' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U : ' ||sql%rowcount);

          UPDATE canon_e633_opencalls_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_open_service_id IS NOT NULL  THEN 'U' ELSE 'I' END
		       ,pps_sf_status_message = null --QC55115
          WHERE pps_sf_status_flag = 'E' ;


        DBMS_OUTPUT.PUT_LINE('No. of PPS error records updated to U : ' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U : ' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

         COMMIT;

		          --QC#58634 unique key changes
             UPDATE canon_e633_opencalls_stg_tbl SET unique_key =jobnumber
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
                 ,lfs_sf_status_flag = (CASE lfs_sf_open_service_id WHEN NULL THEN 'I' ELSE 'U' END)
                  ,pps_sf_status_flag = (CASE pps_sf_open_service_id WHEN NULL THEN 'I' ELSE 'U' END)
		    WHERE unique_key !=jobnumber
            and jobnumber = fsr_num;
            DBMS_OUTPUT.PUT_LINE('No. of records for unique_key changes updated is : ' ||sql%rowcount);
            canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',' unique_key changes updated :'||sql%ROWCOUNT,NULL,NULL,NULL,NULL);
            commit;

            --unique_key changes

         MERGE INTO canon_e633_opencalls_stg_tbl stg
		      USING(select distinct f.fsr_num
                         ,NULL legacy_ref_id
						 ,f.svc_mach_mstr_pk
						 ,smm.svc_config_mstr_pk configuration_number
					--	 ,fv.svc_task_num jobnumber --new
						 ,f.fsr_num jobnumber
						 ,fv.fsr_visit_arv_dt arrival_date
						 ,spct.SVC_PBLM_CRCT_TP_NM action_code
						 ,f.fsr_crat_dt call_creation_date
						 ,stc.loc_num address_number
					   ,stc.loc_nm account_name
					   ,stc.first_line_addr || stc.scd_line_addr || stc.third_line_addr || stc.frth_line_addr || ' ' || stc.CTY_ADDR || ' ' || stc.ST_CD || ' ' || stc.POST_CD account_address
					   ,sptc.SVC_PBLM_TP_NM cause_code
					   ,NULL module_code
					   ,'N' close
					   ,NULL card_code
					   ,scst.svc_call_src_tp_nm source
					   ,NULL FREE_TO_USE_1
					   ,NULL FREE_TO_USE_2
					   ,NULL FREE_TO_USE_3
					   ,NULL FREE_TO_USE_4
					   ,NULL FREE_TO_USE_5
					   ,NULL FREE_TO_USE_6
					FROM fsr f
						 ,svc_mach_mstr smm
						 ,fsr_visit fv
						 ,fsr_visit_task fvt
						 ,svc_pblm_crct_tp spct
						 ,svc_task_sts sts
						 ,ship_to_cust stc
						 ,svc_call_src_tp scst
						 ,svc_task st
						 ,SVC_PBLM_TP sptc
					WHERE f.glbl_cmpy_cd = 'ADB'
					  AND f.ezcancelflag = '0'
					  AND smm.glbl_cmpy_cd = 'ADB'
					  AND smm.ezcancelflag = '0'
					  AND fv.glbl_cmpy_cd = 'ADB'
					  AND fv.ezcancelflag = '0'
					  AND fvt.ezcancelflag(+) = '0'
					  AND fvt.glbl_cmpy_cd(+) = 'ADB'
					  AND spct.ezcancelflag(+) = '0'
					  AND spct.glbl_cmpy_cd(+) = 'ADB'
					  AND sts.glbl_cmpy_cd = 'ADB'
					  AND sts.ezcancelflag = '0'
					  AND stc.glbl_cmpy_cd = 'ADB'
					  AND stc.ezcancelflag = '0'
					  AND scst.ezcancelflag = '0'
					  AND scst.glbl_cmpy_cd = 'ADB'
					  AND st.ezcancelflag = '0'
					  AND st.glbl_cmpy_cd = 'ADB'
					  AND sptc.ezcancelflag(+) = '0'
					  AND sptc.glbl_cmpy_cd(+) = 'ADB'
					  AND f.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
                      AND f.ship_to_cust_cd = smm.ship_to_cust_cd --57878
					  AND f.fsr_num = fv.fsr_num
					  AND f.fsr_sts_cd = '10'
					  AND fv.fsr_visit_ltst_flg = 'Y'  --new
					  AND f.fsr_num = fvt.fsr_num(+)
					  AND fv.svc_task_num = fvt.svc_task_num(+)
					  AND fvt.svc_pblm_crct_tp_cd = spct.svc_pblm_crct_tp_cd(+)
					  AND fvt.svc_pblm_tp_cd = sptc.svc_pblm_tp_cd(+)
					  AND f.ship_to_cust_cd = stc.ship_to_cust_cd
					  AND sts.svc_task_sts_cd = st.SVC_TASK_STS_CD
					  AND sts.open_task_flg = 'Y'
					  AND scst.svc_call_src_tp_cd = f.svc_call_src_tp_cd
					  AND fv.fsr_num = st.fsr_num
                      AND fv.svc_task_num = st.svc_task_num
					--  AND (smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') OR smm.svc_by_line_biz_tp_cd IN ('PPS','LFS'))
					  AND smm.sld_by_line_biz_tp_cd IN ('PPS','LFS') -- as suggested by Chris 12202018
					  AND (
--						   TO_NUMBER(f.ezuptime) >= TO_NUMBER(l_last_run_date_num) OR
--					       TO_NUMBER(fv.ezuptime) >= TO_NUMBER(l_last_run_date_num) OR
--					       TO_NUMBER(st.ezuptime) >= TO_NUMBER(l_last_run_date_num)
						   CAST(TO_TIMESTAMP (f.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
						   CAST(TO_TIMESTAMP (fv.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date OR
						   CAST(TO_TIMESTAMP (st.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
					      )
					  ) s21
             ON( stg.jobnumber = s21.jobnumber)
             WHEN MATCHED THEN UPDATE SET
                    LEGACY_REF_ID = s21.LEGACY_REF_ID
                      ,stg.fsr_num = s21.fsr_num
                      ,stg.svc_mach_mstr_pk = s21.svc_mach_mstr_pk
                      ,stg.CONFIGURATION_NUMBER = s21.CONFIGURATION_NUMBER
                      ,stg.ARRIVAL_DATE = s21.ARRIVAL_DATE
                      ,stg.ACTION_CODE = s21.ACTION_CODE
                      ,stg.CAUSE_CODE = s21.CAUSE_CODE
                      ,stg.MODULE_CODE = s21.MODULE_CODE
                      ,stg.CLOSE = s21.CLOSE
                      ,stg.CARD_CODE = s21.CARD_CODE
                      ,stg.ADDRESS_NUMBER = s21.ADDRESS_NUMBER
                      ,stg.SOURCE  = s21.SOURCE
                      ,stg.FREE_TO_USE_1 = s21.FREE_TO_USE_1
                      ,stg.FREE_TO_USE_2 = s21.FREE_TO_USE_2
                      ,stg.FREE_TO_USE_3 = s21.FREE_TO_USE_3
                      ,stg.FREE_TO_USE_4 = s21.FREE_TO_USE_4
                      ,stg.FREE_TO_USE_5 = s21.FREE_TO_USE_5
                      ,stg.FREE_TO_USE_6 = s21.FREE_TO_USE_6
                      ,stg.CALL_CREATION_DATE = s21.CALL_CREATION_DATE
                      ,stg.lfs_sf_status_flag = (CASE lfs_sf_open_service_id WHEN NULL THEN 'I' ELSE 'U' END)
                      ,stg.pps_sf_status_flag = (CASE pps_sf_open_service_id WHEN NULL THEN 'I' ELSE 'U' END)
                      ,stg.lfs_sf_status_message = NULL
                      ,stg.pps_sf_status_message = NULL
                      ,stg.lfs_last_update_date = SYSDATE
                      ,stg.pps_last_update_date = SYSDATE
             WHEN NOT MATCHED THEN INSERT(
                      LEGACY_REF_ID
                      ,fsr_num
                      ,CONFIGURATION_NUMBER
                      ,svc_mach_mstr_pk
                      ,JOBNUMBER
                      ,ARRIVAL_DATE
                      ,ACTION_CODE
                      ,CAUSE_CODE
                      ,MODULE_CODE
                      ,CLOSE
                      ,CARD_CODE
                      ,ADDRESS_NUMBER
                      ,SOURCE
                      ,FREE_TO_USE_1
                      ,FREE_TO_USE_2
                      ,FREE_TO_USE_3
                      ,FREE_TO_USE_4
                      ,FREE_TO_USE_5
                      ,FREE_TO_USE_6
                      ,CALL_CREATION_DATE
                      ,unique_key
                      ,lfs_sf_status_flag
                      ,pps_sf_status_flag
                      ,lfs_sf_status_message
                      ,pps_sf_status_message
                      ,lfs_last_update_date
                      ,pps_last_update_date) VALUES (
                                      s21.LEGACY_REF_ID
                                      ,s21.fsr_num
                                      ,s21.CONFIGURATION_NUMBER
                                      ,s21.svc_mach_mstr_pk
                                      ,s21.JOBNUMBER
                                      ,s21.ARRIVAL_DATE
                                      ,s21.ACTION_CODE
                                      ,s21.CAUSE_CODE
                                      ,s21.MODULE_CODE
                                      ,s21.CLOSE
                                      ,s21.CARD_CODE
                                      ,s21.ADDRESS_NUMBER
                                      ,s21.SOURCE
                                      ,s21.FREE_TO_USE_1
                                      ,s21.FREE_TO_USE_2
                                      ,s21.FREE_TO_USE_3
                                      ,s21.FREE_TO_USE_4
                                      ,s21.FREE_TO_USE_5
                                      ,s21.FREE_TO_USE_6
                                      ,s21.CALL_CREATION_DATE
                                      ,s21.jobnumber
                                      ,'I'
                                      ,'I'
                                      ,NULL
                                      ,NULL
                                      ,SYSDATE
                                      ,SYSDATE);

        DBMS_OUTPUT.PUT_LINE('No. of records merged from the file : ' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of records merged from the file: ' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of records merged from the file : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

        UPDATE canon_e633_opencalls_stg_tbl
               SET lfs_sf_status_flag = 'E'
                   , pps_sf_status_flag = 'E'
                   ,lfs_sf_status_message = 'Invalid Arrival Date.'
                   , pps_sf_status_message = 'Invalid Arrival Date.'
                   , lfs_last_update_Date = SYSDATE
                   , pps_last_update_date = SYSDATE
            WHERE arrival_date = '0   /  /'
              AND lfs_sf_status_Flag IN ('I', 'U');
--          fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' updated for invalid arrival date for ''0   /  /''.');
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' updated for invalid arrival date for ''0   /  /''.',NULL,NULL,NULL,NULL);
		COMMIT;

		MERGE INTO canon_e633_opencalls_stg_tbl a
		 USING  (select row_id, ARRIVAL_DATE, mth, dt, yr
				   from (select a.rowid row_id
								,ARRIVAL_DATE
								,TO_NUMBER(substr(replace(arrival_date,'/',''), 5, 2)) mth
								,TO_NUMBER(substr(replace(arrival_date,'/',''), 7, 2)) dt
								,TO_NUMBER(substr(replace(arrival_date,'/',''), 1, 4)) yr
						  from canon_e633_opencalls_stg_tbl a
						 where (lfs_sf_Status_flag  IN ('I', 'E', 'U') OR pps_sf_Status_flag  IN ('I', 'E', 'U'))
 						   and arrival_date != '0   /  /'
						  and length(arrival_date) >= 8
						 )
					where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
						ELSE 'VALID'
						 END = 'ERROR'   ) b
			 ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = lfs_sf_status_message||'Arrival Date is Invalid.'
				 ,pps_sf_status_message = pps_sf_status_message||'Arrival Date is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;

				DBMS_OUTPUT.PUT_LINE('No. of arrival date error records.' ||sql%rowcount);
		--        fnd_file.put_line(fnd_file.log, 'No. of arrival date error records.' ||sql%rowcount);

		UPDATE canon_e633_opencalls_stg_tbl SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = lfs_sf_status_message||'Call Creation Date is Missing.'
				 ,pps_sf_status_message = pps_sf_status_message||'Call Creation is Missing.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
		 WHERE call_creation_date is null
		   and pps_sf_Status_flag  IN ('I', 'E', 'U');

        DBMS_OUTPUT.PUT_LINE('No. of missing call_creation_date records.' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of missing call_creation_date records.' ||sql%rowcount,NULL,NULL,NULL,NULL);
--        fnd_file.put_line(fnd_file.log, 'No. of missing call_creation_date records.' ||sql%rowcount);

		UPDATE canon_e633_opencalls_stg_tbl SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = lfs_sf_status_message||'Call Creation Date is Invalid.'
				 ,pps_sf_status_message = pps_sf_status_message||'Call Creation is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
		 WHERE call_creation_date = '0   /  /'
		   and pps_sf_Status_flag  IN ('I', 'E', 'U');

        DBMS_OUTPUT.PUT_LINE('No. of invalid call_creation_date records for ''0   /  /''.' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of invalid call_creation_date records for ''0   /  /''.' ||sql%rowcount,NULL,NULL,NULL,NULL);
--        fnd_file.put_line(fnd_file.log, 'No. of invalid call_creation_date records.' ||sql%rowcount);


		MERGE INTO canon_e633_opencalls_stg_tbl a
		USING  ( select row_id, call_creation_date, mth, dt, yr
				   from ( select a.rowid row_id
								 ,call_creation_date
								 ,TO_NUMBER(substr(replace(call_creation_date,'/',''), 5, 2)) mth
								 ,TO_NUMBER(substr(replace(call_creation_date,'/',''), 7, 2)) dt
								 ,TO_NUMBER(substr(replace(call_creation_date,'/',''), 1, 4)) yr
							from canon_e633_opencalls_stg_tbl a
						   where (lfs_sf_Status_flag  IN ('I', 'E', 'U') OR pps_sf_Status_flag  IN ('I', 'E', 'U'))
						     and call_creation_date != '0   /  /'
							  and length(call_creation_date) >= 8
						  )
				  where CASE WHEN MTH = 1 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)=0 AND ( dt < 1 OR dt > 29) THEN 'ERROR'
							 WHEN MTH = 2 AND MOD(yr,4)!=0 AND ( dt < 1 OR dt > 28) THEN 'ERROR'
							 WHEN MTH = 3 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 4 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 5 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 6 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 7 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 8 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 9 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 10 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							 WHEN MTH = 11 AND ( dt < 1 OR dt > 30) THEN 'ERROR'
							 WHEN MTH = 12 AND ( dt < 1 OR dt > 31) THEN 'ERROR'
							ELSE 'VALID'
						 END = 'ERROR'   ) b
			 ON (a.rowid = b.row_id)
			WHEN MATCHED THEN UPDATE
			 SET lfs_sf_status_flag = 'E'
				 ,pps_sf_Status_flag = 'E'
				 ,lfs_sf_status_message = lfs_sf_status_message||'call_creation_date is Invalid.'
				 ,pps_sf_status_message = pps_sf_status_message||'call_creation_date is Invalid.'
				 ,lfs_last_update_date = SYSDATE
				 ,pps_last_update_date = SYSDATE
				 ;

        DBMS_OUTPUT.PUT_LINE('No. of invalid call_creation_date records.' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of invalid call_creation_date records.' ||sql%rowcount);
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of invalid call_creation_date records.' ||sql%rowcount,NULL,NULL,NULL,NULL);

           MERGE
             INTO canon_e633_opencalls_stg_tbl a
            USING(SELECT b.rowid row_id
                        ,c.account_name
                        ,c.address || ', ' ||
                         c.town || ', ' ||
                         c.geo_area  || ', ' ||
                         c.country  || ' - ' ||
                         c.postal_code address
                    FROM canon_e633_opencalls_stg_tbl b
                        ,canon_e633_cust_site_stg_tbl c
                   WHERE 1 = 1
                     AND b.address_number = c.location_number
                     AND b.lfs_sf_status_flag IN ('I', 'U') -- no need to check pps because both status flags are marked for insert or update together here.
                 ) b
               ON(a.rowid = b.row_id)
             WHEN MATCHED THEN
           UPDATE
              SET account_name = b.account_name
                 ,address = b.address;

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records updated with address information.');
            dbms_output.put_line(SQL%ROWCOUNT||' records updated with address information.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records updated with address information.',NULL,NULL,NULL,NULL);

			BEGIN
				UPDATE canon_s21_cd_val_tbl v
				   SET v.val76 = l_program_start_date
				 WHERE v.val1 = 'OPEN_SVC_CALLS'
				   AND v.cd_id = (SELECT cd_id
								   FROM canon_s21_cd_tbl
								   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
			   EXCEPTION
				 WHEN OTHERS THEN
				   dbms_output.put_line('update OPEN_SVC_CALLS last run date exception: '|| SQLERRM);
				   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		    END;
            COMMIT;

EXCEPTION
 WHEN OTHERS THEN
        retcode := '2';
        errbuff := SQLERRM;
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);

END load_open_service_calls;


PROCEDURE update_dependIDs(p_handler_name IN varchar2, x_return_status OUT VARCHAR2)
AS
    l_procedure_name varchar2(30) := 'update_dependIDs';
    l_a_table varchar2(60) ;
    l_b_table varchar2(60) ;
    l_a_col varchar2(60) ;
    l_b_col varchar2(60) ;
    l_sql varchar2(32767) ;
    l_c_col varchar2(60);
    l_d_col varchar2(60);

BEGIN

    x_return_status := 'S';

    l_a_table := null;
    l_b_table := null ;
    l_a_col := null ;
    l_b_col := null;
    l_c_col := null; --new
    l_d_col := null; --new

    IF(p_handler_name LIKE '%ConfigUpload') THEN
        l_a_table := 'canon_e633_config_stg_tbl a';
        l_b_table := 'canon_e633_ib_stg_tbl b';
        l_a_col := 'a.config_number';
        l_b_col := 'b.config_nbr';
        --l_a_col := 'a.svc_mach_mstr_pk';
        --l_b_col := 'b.svc_mach_mstr_pk';
    ELSIF (p_handler_name LIKE '%ContractUpload') THEN
        l_a_table := 'canon_e633_contracts_stg_tbl a';
        l_b_table := 'canon_e633_ib_stg_tbl b';
       l_a_col := 'a.config_nbr';
        l_b_col := 'b.config_nbr';
     --   l_a_col := 'a.svc_mach_mstr_pk';
      --  l_b_col := 'b.svc_mach_mstr_pk';
    ELSIF(p_handler_name LIKE '%MtrReadUpload') THEN
        l_a_table := 'canon_e633_meter_reads_stg_tbl a';
        l_b_table := 'canon_e633_ib_stg_tbl b';
       -- l_a_col := 'a.svc_mach_mstr_pk';
      --  l_b_col := 'b.svc_mach_mstr_pk';
        l_a_col := 'a.configuration_number';
        l_b_col := 'b.config_nbr';
    ELSIF(p_handler_name LIKE '%OpenCallUpload') THEN
        l_a_table := 'canon_e633_opencalls_stg_tbl a';
        l_b_table := 'canon_e633_ib_stg_tbl b';
       l_a_col := 'a.configuration_number';
       l_b_col := 'b.config_nbr';
     --   l_a_col := 'a.svc_mach_mstr_pk';
    --    l_b_col := 'b.svc_mach_mstr_pk';
    ELSIF(p_handler_name LIKE '%CloseCallUpload') THEN
        l_a_table := 'canon_e633_closecalls_stg_tbl a';
        l_b_table := 'canon_e633_ib_stg_tbl b';
        l_a_col := 'a.configuration_number';
       l_b_col := 'b.config_nbr';
     --    l_a_col := 'a.svc_mach_mstr_pk';
     --   l_b_col := 'b.svc_mach_mstr_pk';
     ELSIF(p_handler_name LIKE '%ContactUpload') THEN
        l_a_table := 'CANON_E633_IB_CONT_STG_TBL a';
        l_b_table := 'canon_e633_ib_stg_tbl b';
        l_a_col := 'a.configuration_number';
       l_b_col := 'b.config_nbr';
      --      l_a_col := 'a.svc_mach_mstr_pk';
      --  l_b_col := 'b.svc_mach_mstr_pk';
     ELSIF(p_handler_name LIKE '%CFSLeaseUpload') THEN
        l_a_table := 'CANON_E633_CFSLEASE_STG_TBL a';
        l_b_table := 'canon_e633_ib_stg_tbl b';
        l_a_col := 'a.configuration_number';
        l_b_col := 'b.config_nbr';
     END IF;

    dbms_output.put_line('p_handler_name:'||p_handler_name);
    dbms_output.put_line('l_a_table:'||l_a_table);
    dbms_output.put_line('l_a_col:'||l_a_col);
    dbms_output.put_line('l_b_table:'||l_b_table);
    dbms_output.put_line('l_b_col:'||l_b_col);

--    fnd_file.put_line(fnd_file.log, 'updating ID''s in ' || l_a_table || ' from ' || l_b_table);
     --for LFS
	 if(p_handler_name like 'LFS%') THEN
     IF(p_handler_name LIKE '%LFSMtrReadUpload%') THEN --QC59307
        MERGE INTO canon_e633_meter_reads_stg_tbl stg
        USING (SELECT a.meter_read_system_number rnid, b.lfs_sf_account_id, b.lfsbu, b.lfs_sf_IB_id
                  FROM canon_e633_meter_reads_stg_tbl a
                       ,canon_e633_ib_stg_tbl b
                 WHERE a.configuration_number = b.config_nbr
                   AND NVL(a.lfs_sf_account_id, b.lfs_sf_account_id) = b.lfs_sf_account_id
                   AND a.lfs_sf_status_flag IN ('I','U')) s21
         ON (stg.meter_read_system_number = s21.rnid) --QC59307
         WHEN MATCHED THEN UPDATE SET
         stg.lfs_sf_account_id = s21.lfs_sf_account_id
         ,stg.lfs_sf_ib_id = s21.lfs_sf_ib_id
         ,stg.lfsbu = s21.lfsbu
         ,stg.lfs_last_update_date = SYSDATE;

--        l_c_col := 'a.lfs_sf_account_id';
--        l_d_col := 'b.lfs_sf_account_id';
--        l_sql := 'MERGE INTO ' || l_a_table ||
--                 ' USING ' || l_b_table ||
--                 ' ON (' || l_a_col || ' = ' || l_b_col ||
--                        ' AND NVL(' || l_c_col || ',' || l_d_col || ') = ' || l_d_col ||
----                        ' AND b.life_Cycle_stat <> ''REMOVED'' ' ||
--                        ' AND a.lfs_sf_status_flag IN (''I'',''U'')) ' ||
--                'WHEN MATCHED THEN UPDATE SET ' ||
--                '  a.lfs_sf_ib_id = b.lfs_sf_ib_id ' ||
--                ' ,a.lfs_sf_account_id = b.lfs_sf_account_id ' ||
--                ' ,a.LFSBU = b.lfsbu ' ||
--                ' ,a.lfs_last_update_date = SYSDATE ' ;
--                canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
--        dbms_output.put_line('l_sql:'||l_sql);
--       EXECUTE IMMEDIATE l_sql;
       commit;
    elsIF(p_handler_name LIKE '%LFSOpenCallUpload%') THEN --QC59307
        MERGE INTO canon_e633_opencalls_stg_tbl stg
        USING (SELECT a.rowID rnid, b.lfs_sf_account_id, b.lfsbu, b.lfs_sf_IB_id
                  FROM canon_e633_opencalls_stg_tbl a
                       ,canon_e633_ib_stg_tbl b
                 WHERE a.configuration_number = b.config_nbr
                   AND a.svc_mach_mstr_pk = b.svc_mach_mstr_pk
                   AND NVL(a.lfs_sf_account_id, NVL(b.lfs_sf_account_id,'XX')) = NVL(b.lfs_sf_account_id,'XX')
                   AND a.lfs_sf_status_flag IN ('I','U')) s21
         ON (stg.rowid = s21.rnid)
         WHEN MATCHED THEN UPDATE SET
         stg.lfs_sf_account_id = s21.lfs_sf_account_id
         ,stg.lfs_sf_ib_id = s21.lfs_sf_ib_id
         ,stg.lfsbu = s21.lfsbu
         ,stg.lfs_last_update_date = SYSDATE;
         commit;
    elsIF(p_handler_name LIKE '%LFSCloseCallUpload%') THEN --QC59307
        MERGE INTO canon_e633_closecalls_stg_tbl stg
        USING (SELECT a.rowID rnid, b.lfs_sf_account_id, b.lfsbu, b.lfs_sf_IB_id
                  FROM canon_e633_closecalls_stg_tbl a
                       ,canon_e633_ib_stg_tbl b
                 WHERE a.configuration_number = b.config_nbr
                   AND NVL(a.lfs_sf_account_id, b.lfs_sf_account_id) = b.lfs_sf_account_id
                   AND a.lfs_sf_status_flag IN ('I','U')) s21
         ON (stg.rowid = s21.rnid)
         WHEN MATCHED THEN UPDATE SET
         stg.lfs_sf_account_id = s21.lfs_sf_account_id
         ,stg.lfs_sf_ib_id = s21.lfs_sf_ib_id
         ,stg.lfsbu = s21.lfsbu
         ,stg.lfs_last_update_date = SYSDATE;
         commit;
     elsIF(p_handler_name LIKE '%LFSContractUpload%') THEN --QC59307
        MERGE INTO canon_e633_contracts_stg_tbl stg
        USING (SELECT a.rowID rnid, b.lfs_sf_account_id, b.lfsbu, b.lfs_sf_IB_id
                  FROM canon_e633_contracts_stg_tbl a
                       ,canon_e633_ib_stg_tbl b
                 WHERE a.config_nbr = b.config_nbr
                   AND NVL(a.lfs_sf_account_id, b.lfs_sf_account_id) = b.lfs_sf_account_id
                   AND a.lfs_sf_status_flag IN ('I','U')) s21
         ON (stg.rowid = s21.rnid)
         WHEN MATCHED THEN UPDATE SET
         stg.lfs_sf_account_id = s21.lfs_sf_account_id
         ,stg.lfs_sf_ib_id = s21.lfs_sf_ib_id
         ,stg.lfsbu = s21.lfsbu
         ,stg.lfs_last_update_date = SYSDATE;
         commit;
     ELSE
      l_sql := 'MERGE INTO ' || l_a_table ||
                ' USING ' || l_b_table ||
                ' ON (' || l_a_col || ' = ' || l_b_col ||
                      ' AND a.lfs_sf_status_flag IN (''I'',''U'')) ' ||
                'WHEN MATCHED THEN UPDATE SET ' ||
                '  a.lfs_sf_ib_id = b.lfs_sf_ib_id ' ||
                ' ,a.lfs_sf_account_id = b.lfs_sf_account_id ' ||
                ' ,a.LFSBU = b.lfsbu ' ||
                ' ,a.lfs_last_update_date = SYSDATE ' ;
     canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
        dbms_output.put_line('l_sql:'||l_sql);
       EXECUTE IMMEDIATE l_sql;
--       fnd_file.put_line(fnd_file.log,'No. of rows LFS IB and Account Id''s updated : ' ||sql%rowcount);
		commit;
      END IF;
		--update for LFS bu
        l_sql := 'UPDATE ' || l_a_table ||
                  ' SET a.lfs_sf_status_flag = NULL, a.lfs_sf_ib_id = NULL, a.lfs_sf_account_id = null ' ||
                  '    , a.lfs_sf_status_message = null ' ||
                  ' WHERE a.lfsbu IS NULL ' ||
                  '   AND a.lfs_sf_status_flag IN (''I'',''U'') ';
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
        dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;
--        fnd_file.put_line(fnd_file.log,'No. of rows updated for LFS BU  : ' ||sql%rowcount);

		--update if sf_ib_id is missing for lfs
        l_sql := 'UPDATE ' || l_a_table ||
                 ' SET a.lfs_sf_status_flag = ''E'' ' ||
                 '    ,a.lfs_sf_status_message = ''Unable to find IB LFS SFDC ID.'' ' ||
                 ' WHERE a.lfsbu IS NOT NULL ' ||
                 '   AND a.lfs_sf_ib_id IS NULL ' ||
                 '   AND a.lfs_sf_status_flag IN (''I'',''U'')' ;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
        dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;
--        fnd_file.put_line(fnd_file.log,'No. of rows updated for unable to lookup LFS IB ID  : ' ||sql%rowcount);
        COMMIT;




	  END IF;

       --for PPS
	   IF(p_handler_name LIKE 'PPS%') THEN
       IF(p_handler_name LIKE '%PPSMtrReadUpload%') THEN --QC59307
       MERGE INTO canon_e633_meter_reads_stg_tbl stg
        USING (SELECT a.meter_read_system_number rnid, b.pps_sf_account_id, b.ppsbu, b.pps_sf_IB_id
                  FROM canon_e633_meter_reads_stg_tbl a
                       ,canon_e633_ib_stg_tbl b
                 WHERE a.configuration_number = b.config_nbr
                   AND NVL(a.pps_sf_account_id, b.pps_sf_account_id) = b.pps_sf_account_id
                   AND a.pps_sf_status_flag IN ('I','U')) s21
         ON (stg.meter_read_system_number = s21.rnid)
         WHEN MATCHED THEN UPDATE SET
         stg.pps_sf_account_id = s21.pps_sf_account_id
         ,stg.pps_sf_ib_id = s21.pps_sf_ib_id
         ,stg.ppsbu = s21.ppsbu
         ,stg.pps_last_update_date = SYSDATE;
--       l_c_col := 'a.pps_sf_account_id';
--       l_d_col := 'b.pps_sf_account_id';
--        l_sql := 'MERGE INTO ' || l_a_table ||
--                 ' USING ' || l_b_table ||
--                 ' ON (' || l_a_col || ' = ' || l_b_col ||
--                       -- ' AND NVL(' || l_c_col || ', ' || l_d_col || ') = ' || l_d_col ||
--                        ' AND b.life_Cycle_stat <> ''REMOVED'' ' ||
--                        ' AND a.pps_sf_status_flag IN (''I'',''U'')) ' ||
--                'WHEN MATCHED THEN UPDATE SET ' ||
--                '  a.pps_sf_ib_id = b.pps_sf_ib_id ' ||
--                ' ,a.pps_sf_account_id = b.pps_sf_account_id ' ||
--                ' ,a.PPSBU = b.ppsbu ' ||
--                ' ,a.pps_last_update_date = SYSDATE ' ;
--        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
--        dbms_output.put_line('l_sql:'||l_sql);
--       EXECUTE IMMEDIATE l_sql;
       commit;
       elsiF(p_handler_name LIKE '%PPSOpenCallUpload%') THEN --QC59307
        MERGE INTO canon_e633_opencalls_stg_tbl stg
        USING (SELECT a.rowID rnid, b.pps_sf_account_id, b.ppsbu, b.pps_sf_IB_id
                  FROM canon_e633_opencalls_stg_tbl a
                       ,canon_e633_ib_stg_tbl b
                 WHERE a.configuration_number = b.config_nbr
                   AND a.svc_mach_mstr_pk = b.svc_mach_mstr_pk
                   AND NVL(a.pps_sf_account_id, NVL(b.pps_sf_account_id,'XX')) = NVL(b.pps_sf_account_id,'XX')
                   AND a.pps_sf_status_flag IN ('I','U')) s21
         ON (stg.rowid = s21.rnid)
         WHEN MATCHED THEN UPDATE SET
         stg.pps_sf_account_id = s21.pps_sf_account_id
         ,stg.pps_sf_ib_id = s21.pps_sf_ib_id
         ,stg.ppsbu = s21.ppsbu
         ,stg.pps_last_update_date = SYSDATE;
         commit;
    elsIF(p_handler_name LIKE '%PPSCloseCallUpload%') THEN --QC59307
        MERGE INTO canon_e633_closecalls_stg_tbl stg
        USING (SELECT a.rowID rnid, b.pps_sf_account_id, b.ppsbu, b.pps_sf_IB_id
                  FROM canon_e633_closecalls_stg_tbl a
                       ,canon_e633_ib_stg_tbl b
                 WHERE a.configuration_number = b.config_nbr
                   AND NVL(a.pps_sf_account_id, b.pps_sf_account_id) = b.pps_sf_account_id
                   AND a.pps_sf_status_flag IN ('I','U')) s21
         ON (stg.rowid = s21.rnid)
         WHEN MATCHED THEN UPDATE SET
         stg.pps_sf_account_id = s21.pps_sf_account_id
         ,stg.pps_sf_ib_id = s21.pps_sf_ib_id
         ,stg.ppsbu = s21.ppsbu
         ,stg.pps_last_update_date = SYSDATE;
         commit;
     elsIF(p_handler_name LIKE '%PPSContractUpload%') THEN  --QC59307
        MERGE INTO canon_e633_contracts_stg_tbl stg
        USING (SELECT a.rowID rnid, b.pps_sf_account_id, b.ppsbu, b.pps_sf_IB_id
                  FROM canon_e633_contracts_stg_tbl a
                       ,canon_e633_ib_stg_tbl b
                 WHERE a.config_nbr = b.config_nbr
                   AND NVL(a.pps_sf_account_id, b.pps_sf_account_id) = b.pps_sf_account_id
                   AND a.pps_sf_status_flag IN ('I','U')) s21
         ON (stg.rowid = s21.rnid)
         WHEN MATCHED THEN UPDATE SET
         stg.pps_sf_account_id = s21.pps_sf_account_id
         ,stg.pps_sf_ib_id = s21.pps_sf_ib_id
         ,stg.ppsbu = s21.ppsbu
         ,stg.pps_last_update_date = SYSDATE;
         commit;
       ELSE
       l_sql := 'MERGE INTO ' || l_a_table ||
                ' USING ' || l_b_table ||
                ' ON (' || l_a_col || ' = ' || l_b_col ||
                      ' AND a.pps_sf_status_flag IN (''I'',''U'')) ' ||
                'WHEN MATCHED THEN UPDATE SET ' ||
                ' a.pps_sf_ib_id = b.pps_sf_ib_id ' ||
                ' ,a.pps_sf_account_id = b.pps_sf_account_id ' ||
                ' ,a.PPSBU = b.ppsbu ' ||
                ' ,a.pps_last_update_date = SYSDATE ';
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
       dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;
--        fnd_file.put_line(fnd_file.log,'No. of rows PPS IB and Account Id''s updated : ' ||sql%rowcount);
        COMMIT;
    END IF;
        l_sql := 'UPDATE ' || l_a_table ||
                  ' SET a.pps_sf_ib_id = NULL, a.pps_sf_account_id = null ' ||
                  '    ,a.pps_sf_status_flag = NULL, a.pps_sf_status_message = NULL ' ||
                  ' WHERE a.ppsbu IS NULL ' ||
                  '   AND a.pps_sf_status_flag IN (''I'',''U'') ' ;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
        dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;
--        fnd_file.put_line(fnd_file.log,'No. of rows updated for PPS BU  : ' ||sql%rowcount);
        COMMIT;

        --update if sf_ib_id is missing for pps
        l_sql := 'UPDATE ' || l_a_table ||
                 ' SET a.pps_sf_status_flag = ''E'' ' ||
                 '    ,a.pps_sf_status_message = ''Unable to find IB PPS SFDC ID.'' ' ||
                 ' WHERE a.ppsbu IS NOT NULL ' ||
                 '   AND a.pps_sf_ib_id IS NULL ' ||
                 '   AND a.pps_sf_status_flag IN (''I'',''U'') ' ;
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
       dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;
--        fnd_file.put_line(fnd_file.log,'No. of rows updated for unable to lookup PPS IB ID  : ' ||sql%rowcount);
        COMMIT;
	END IF;

        dbms_output.put_line('x_return_status:'||x_return_status);
EXCEPTION
   WHEN OTHERS THEN
        x_return_status := 'E';
        dbms_output.put_line('x_return_status:'||x_return_status);
        dbms_output.put_line(SQLERRM);
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END update_dependIDs;

PROCEDURE load_ar_aging(retcode out varchar2, errbuff out varchar2)
IS
    l_procedure_name VARCHAR2(30) := 'load_ar_aging';
    l_last_run_date DATE;
    l_program_start_date DATE := SYSDATE;
    l_last_run_date_num VARCHAR2(17);

BEGIN
        retcode := '0';

         BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'AR_AGING';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;

   --QC58464 --start --to reprocess and unprocessed records
         UPDATE canon_e633_ar_aging_stg_tbl  stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_ar_id IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464
            --QC58464 --start
         UPDATE canon_e633_ar_aging_stg_tbl  stg
            SET pps_sf_status_flag =  'I'
            WHERE  pps_sf_status_flag IS NULL
              AND pps_sf_ar_id IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464
--to reprocess error and unprocessed records
		UPDATE canon_e633_ar_aging_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_ar_id IS NOT NULL THEN 'U' ELSE 'I' END
               ,lfs_Sf_Status_message = NULL
          WHERE lfs_sf_status_flag = 'E';
--         fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U / I : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of LFS error records updated to U / I : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

          UPDATE canon_e633_ar_aging_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_ar_id IS NOT NULL THEN 'U' ELSE 'I' END
              ,pps_sf_Status_message = NULL
          WHERE pps_sf_status_flag = 'E' ;
--         fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U / I : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U / I : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
         COMMIT;

         MERGE INTO canon_e633_ar_aging_stg_tbl stg
           USING (SELECT bill_to_loc_num loc_num
                         ,sum(DEAL_AGING_AMT_00) age0
                         ,sum(DEAL_AGING_AMT_01) age30
                         ,sum(DEAL_AGING_AMT_02) age60
                         ,sum(DEAL_AGING_AMT_03) age90
                         ,sum(DEAL_AGING_AMT_04) age120
                         ,sum(DEAL_AGING_AMT_05
                               +DEAL_AGING_AMT_06
                             +DEAL_AGING_AMT_07
                             +DEAL_AGING_AMT_08
                             +DEAL_AGING_AMT_09
                             +DEAL_AGING_AMT_10
                             +DEAL_AGING_AMT_11
                             +DEAL_AGING_AMT_12
                             +DEAL_AGING_AMT_13) age120plus
                     FROM AR_AGING_BY_COA
                      WHERE glbl_cmpy_cd = 'ADB'
                        AND ezcancelflag = '0'
                        AND line_biz_tp_cd IN ('LFS','PPS','CSAPPS','CSALFS')
                        AND to_date(dwh_trgt_dt, 'RRRRMMDD') <= trunc(sysdate)
                        --AND TO_NUMBER(ezuptime) > TO_NUMBER(l_last_run_date_num)
						AND CAST(TO_TIMESTAMP (ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                        GROUP BY bill_to_loc_num
                        ) s21
           ON (stg.legacy_ref_id = s21.loc_num )
           WHEN MATCHED THEN UPDATE SET
                   -- stg.LEGACY_REF_ID = s21.customer)
                      stg.CURRENT_C = s21.age0
                      ,stg.ONE_TO_30 = s21.age30
                      ,stg.THIRTYONE_TO_60 = s21.age60
                      ,stg.SIXTYONE_TO_90 = s21.age90
                      ,stg.NINETYONE_TO_120 = s21.age120
                      ,stg.ONETWENTY_PLUS = s21.age120plus
                      ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_ar_id IS NULL THEN 'I' ELSE 'U' END)
                      ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_ar_id IS NULL THEN 'I' ELSE 'U' END)
                     ,stg.lfs_sf_status_message = NULL
                     ,stg.pps_sf_status_message = NULL
                     ,stg.lfs_last_update_date = SYSDATE
                     ,stg.pps_last_update_date = SYSDATE
           WHEN NOT MATCHED THEN INSERT (LEGACY_REF_ID
                                          ,CURRENT_C
                                          ,ONE_TO_30
                                          ,THIRTYONE_TO_60
                                          ,SIXTYONE_TO_90
                                          ,NINETYONE_TO_120
                                          ,ONETWENTY_PLUS
                                          ,lfs_sf_status_flag
                                          ,pps_sf_status_flag
                                          ,lfs_sf_status_message
                                          ,pps_sf_status_message
                                          ,lfs_last_update_date
                                          ,pps_last_update_date) VALUES (s21.loc_num
                                                                      ,s21.age0
                                                                      ,s21.age30
                                                                      ,s21.age60
                                                                      ,s21.age90
                                                                      ,s21.age120
                                                                      ,s21.age120plus
                                                                      ,'I'
                                                                      ,'I'
                                                                      ,NULL
                                                                      ,NULL
                                                                      ,sysdate
                                                                      ,sysdate );
--                           fnd_file.put_line(fnd_file.log, sql%rowcount || ' merged into CANON_E633_AR_AGING_STG_TBL');
            dbms_output.put_line(sql%rowcount || ' merged into CANON_E633_AR_AGING_STG_TBL');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' merged into CANON_E633_AR_AGING_STG_TBL',NULL,NULL,NULL,NULL);

            BEGIN
				UPDATE canon_s21_cd_val_tbl v
				   SET v.val76 = l_program_start_date
				 WHERE v.val1 = 'AR_AGING'
				   AND v.cd_id = (SELECT cd_id
								   FROM canon_s21_cd_tbl
								   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
			   EXCEPTION
				 WHEN OTHERS THEN
				   dbms_output.put_line('update AR_AGING last run date exception: '|| SQLERRM);
				   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		    END;
            COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        --x_return_status := 'E';
        --dbms_output.put_line('x_return_status:'||x_return_status);
        retcode := '2';
        errbuff := SQLERRM;
        dbms_output.put_line(SQLERRM);
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END load_ar_aging;

PROCEDURE update_dependIDs_4_AR(p_handler_name IN VARCHAR2, x_return_status OUT VARCHAR2)
IS
    l_sql varchar2(32767);
    l_procedure_name varchar2(30) := 'update_dependIDs_4_AR';
    l_table varchar2(50) := NULL;
    l_a_col varchar2(50) := NULL;
BEGIN
    x_Return_status := 'S';
    IF ( p_handler_name like '%ARUpload') THEN
        l_table := 'canon_e633_ar_aging_stg_tbl a ';
        l_a_col := 'a.legacy_ref_id';
    ELSIF ( p_handler_name like '%OrderHeader%') THEN
        l_table := 'canon_e633_ordhdr_stg_tbl a ';
        l_a_col := 'a.sold_to' ;
    END IF;
--    fnd_file.put_line(fnd_file.log, 'Updating ID''s in ' || l_table || ' from canon_e633_cust_site_stg_tbl.');

    IF(p_handler_name LIKE '%LFS%' ) THEN --QC51955
      l_sql := ' MERGE INTO ' || l_table ||
             ' USING canon_e633_cust_site_stg_tbl cust ' ;
            IF (upper(p_handler_name) LIKE '%ORD%') THEN --QC51955
             l_sql := l_sql || ' ON ( ' || l_a_col || ' = cust.location_number AND a.lfs_sf_status_flag in (''I'',''U'') AND a.order_reason like ''%LFS%'') ' ;
            ELSE
              l_sql := l_sql ||' ON ( ' || l_a_col || ' = cust.location_number AND a.lfs_sf_status_flag in (''I'',''U'') ) ' ;
            END IF;
             l_sql := l_sql || ' WHEN MATCHED THEN UPDATE SET ' ||
             ' a.lfs_sf_account_id = cust.LFS_SF_account_ID ' ||
             ' ,a.LFSBU = cust.lfsbu ' ;
          canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
          EXECUTE IMMEDIATE l_sql;
          DBMS_OUTPUT.PUT_LINE('No. of rows LFS account Id''s updated : ' ||sql%rowcount);
         COMMIT;

        l_sql := 'UPDATE ' || l_table ||
                  ' SET lfs_sf_status_flag = NULL ' ||
                  '     ,lfs_sf_account_id = NULL ' ||
                  '     ,lfs_sf_status_message = NULL ' ||
                  ' WHERE 1 = 1 ' ||
                  '  AND lfsbu IS NULL ' ||
                  '  AND lfs_sf_status_flag IN (''I'',''U'') ' ;
        canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',l_sql,NULL,NULL,NULL,NULL);
          EXECUTE IMMEDIATE l_sql;

        l_sql := 'UPDATE ' || l_table ||
                  ' SET lfs_sf_status_flag = ''E'' ' ||
                  '     ,lfs_sf_status_message = ''Unable to lookup account LFS SFDC ID'' ' ||
                  ' WHERE lfs_sf_status_flag IN (''I'',''U'') ' ||
                  ' AND lfsbu IS NOT NULL ' ||
                  ' AND lfs_sf_account_id IS NULL ' ;
          canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',l_sql,NULL,NULL,NULL,NULL);
          EXECUTE IMMEDIATE l_sql;

        commit;

  END IF;


    IF(p_handler_name LIKE '%PPS%' ) THEN
        --update the PPS account_id
          l_sql := ' MERGE INTO ' || l_table ||
             ' USING canon_e633_cust_site_stg_tbl cust ' ;
             IF (upper(p_handler_name) LIKE '%ORD%') THEN --QC51955
             l_sql := l_sql || ' ON ( ' || l_a_col || ' = cust.location_number AND a.pps_sf_status_flag in (''I'',''U'') AND a.order_reason like ''%PPS%'') ' ;
            ELSE
              l_sql := l_sql ||' ON ( ' || l_a_col || ' = cust.location_number AND a.pps_sf_status_flag in (''I'',''U'') ) ' ;
            END IF;
           l_sql := l_sql ||  ' WHEN MATCHED THEN UPDATE SET ' ||
             ' a.pps_sf_account_id = cust.PPS_SF_account_ID ' ||
             ' ,a.PPSBU = cust.PPSBU ' ;
          canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
          EXECUTE IMMEDIATE l_sql;
          DBMS_OUTPUT.PUT_LINE('No. of rows PPS account Id''s updated : ' ||sql%rowcount);
         COMMIT;

          l_sql := 'UPDATE ' || l_table ||
                  ' SET pps_sf_status_flag = NULL ' ||
                  '     ,pps_sf_account_id = NULL ' ||
                  '     ,pps_sf_status_message = NULL ' ||
                  ' WHERE 1 = 1 ' ||
                  '  AND ppsbu IS NULL ' ||
                  '  AND pps_sf_status_flag IN (''I'',''U'') ' ;
          canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO',l_sql,NULL,NULL,NULL,NULL);
          EXECUTE IMMEDIATE l_sql;

          l_sql := 'UPDATE ' || l_table ||
                  ' SET pps_sf_status_flag = ''E'' ' ||
                  '     ,pps_sf_status_message = ''Unable to lookup account PPS SFDC ID'' ' ||
                  ' WHERE pps_sf_status_flag IN (''I'',''U'') ' ||
                  ' AND ppsbu IS NOT NULL ' ||
                  ' AND pps_sf_account_id IS NULL ' ;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
          EXECUTE IMMEDIATE l_sql;
       COMMIT;

    END IF;

EXCEPTION
    WHEN OTHERS THEN
        x_return_status := 'E';
        --dbms_output.put_line('x_return_status:'||x_return_status);
        dbms_output.put_line(SQLERRM);
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END update_dependIDs_4_AR;

PROCEDURE load_order_headers(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
IS
    l_procedure_name VARCHAR2(30) := 'load_order_headers';
    l_last_run_date DATE;
    l_program_start_date DATE := SYSDATE;
    l_last_run_date_num VARCHAR2(17);

BEGIN
        retcode := '0';

         BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'ORD_HEADER';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;

   --QC58464 --start --to reprocess and unprocessed records
         UPDATE canon_e633_ordhdr_stg_tbl  stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_order_id IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC58464 --start
         UPDATE canon_e633_ordhdr_stg_tbl  stg
            SET pps_sf_status_flag =  'I'
            WHERE  pps_sf_status_flag IS NULL
              AND pps_sf_order_id IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

        --to reprocess error and unprocessed records
		UPDATE canon_e633_ordhdr_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_order_id IS NOT NULL THEN 'U' ELSE 'I' END
		       ,lfsbu = CASE WHEN lfs_sf_order_id IS NOT NULL then lfsbu ELSE NULL END --QC51955
          WHERE lfs_sf_status_flag = 'E';
--          fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U / I : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of LFS error records updated to U / I : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

          UPDATE canon_e633_ordhdr_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_order_id IS NOT NULL THEN 'U' ELSE 'I' END
		       ,ppsbu = CASE WHEN pps_sf_order_id IS NOT NULL THEN ppsbu ELSE NULL END --QC51955
          WHERE pps_sf_status_flag = 'E' ;
--          fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U / I : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U / I : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

		--QC51955
		UPDATE canon_e633_ordhdr_stg_tbl stg
            SET lfs_sf_status_flag = 'I'
                ,pps_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_order_id IS NULL
              AND lfs_sf_account_id IS NULL
              AND lfsbu IS NULL
              AND pps_sf_status_flag IS NULL
              AND pps_sf_order_id IS NULL
              AND pps_sf_account_id IS NULL
              AND ppsbu IS NULL;

         COMMIT;

        EXECUTE IMMEDIATE 'TRUNCATE TABLE canon_e633_ordhr2process_gtbl';

        INSERT
                  INTO canon_e633_ordhr2process_gtbl
                  (cpo_ord_number,
                    sell_to_cust_cd
                   )
                   SELECT cpo_ord_num
                          ,sell_to_cust_cd
                     FROM cpo c
                    WHERE ezcancelflag = '0'
                      AND glbl_cmpy_cd = 'ADB'
                      --AND TO_NUMBER(ezuptime) >= TO_NUMBER(l_last_run_date_num)
					  AND CAST(TO_TIMESTAMP (c.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                   UNION
                    SELECT c.cpo_ord_num -- DB Change
                           ,c.sell_to_cust_cd
                      FROM cpo c
--                           ,ds_cpo dc -- DB Change
                     WHERE c.ezcancelflag = '0'
                       AND c.glbl_cmpy_cd = 'ADB'
--                       AND dc.ezcancelflag = '0'
--                       AND dc.glbl_cmpy_cd = 'ADB'
--                       AND c.cpo_ord_num = dc.cpo_ord_num
                       --AND c.ds_cpo_upd_ts >= l_last_run_date_num
					   AND CAST(TO_TIMESTAMP (c.ds_cpo_upd_ts, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                   UNION
                      SELECT ad.att_data_grp_txt
                             ,c.sell_to_cust_cd
                        FROM cpo c
                             ,att_data ad
                        WHERE c.ezcancelflag = '0'
                          AND c.glbl_cmpy_cd = 'ADB'
                          AND ad.ezcancelflag = '0'
                          AND ad.glbl_cmpy_cd = 'ADB'
                          AND att_data_tp_cd = 'NT'--note
                          AND ezbusinessid='NWAL1500'
                          --AND TO_NUMBER(ad.ezuptime) >= TO_NUMBER(l_last_run_date_num);
						  AND CAST(TO_TIMESTAMP (ad.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date;

  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' loaded into canon_e633_ordhr2process_gtbl',NULL,NULL,NULL,NULL);

                 DELETE FROM canon_e633_ordhr2process_gtbl a
                   WHERE a.sell_to_cust_cd IN (SELECT 1
                                               FROM pty_loc_wrk plw
--                                                    ,ds_pty_loc_wrk dplw -- DB Change
                                                    ,sell_to_cust stc
                                                    ,canon_e633_ordhr2process_gtbl a1
													,acct_trty_role_asg atra
													,trty_grp_tp tgt
                                                WHERE plw.ezcancelflag = '0'
                                                   AND plw.glbl_cmpy_cd = 'ADB'
--                                                   AND dplw.ezcancelflag = '0' -- DB Change
--                                                   AND dplw.glbl_cmpy_cd = 'ADB'
                                                   AND stc.ezcancelflag = '0'
                                                   AND stc.glbl_cmpy_cd = 'ADB'
												   AND atra.ezcancelflag = '0'
												   AND atra.glbl_cmpy_cd = 'ADB'
												   AND tgt.ezcancelflag = '0'
												   AND tgt.glbl_cmpy_cd = 'ADB'
                                                   AND stc.sell_to_cust_cd = a1.sell_to_cust_cd
                                                   AND plw.loc_num = stc.loc_num
												   AND plw.loc_num = atra.loc_num
												   AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
												   AND atra.line_biz_role_tp_cd IS NOT NULL
												   AND tgt.line_biz_tp_cd NOT IN ('LFS','PPS')
                                  );
            --dbms_output.put_line(sql%rowcount || ' loaded into canon_e633_ordhr2process_gtbl');

            COMMIT;

            MERGE INTO canon_e633_ordhdr_stg_tbl stg
            USING ( SELECT distinct c.cpo_ord_num document_number
                           ,c.aqu_num fast_track_number -- DB Change
                           ,c.ord_log_tp_cd fast_track_id -- DB Change
--                           ,substr(ad.ezuptime,1,8) last_comment_date
                           ,NULL last_comment_date
                           ,c.ds_cpo_crat_usr_id creation_user -- DB Change
                           ,substr(c.ds_cpo_crat_ts,9) creation_time -- DB Change
                           ,substr(c.ds_cpo_crat_ts,1,8) creation_date -- DB Change
                           ,dot.ds_ord_tp_nm order_reason
                           ,c.ord_sgn_dt order_signed_date -- DB Change
                           ,NULL original_paperwork_date
                           ,c.org_rqst_dely_dt original_requested_delivery_dt -- DB Change
                           ,c.add_rdd_dt requested_delivery
                           ,doc.ds_ord_catg_nm order_type --|| '-' || cot.cpo_ord_tp_nm order_type QC54017
                           ,stc.loc_num account_location
                           ,stc.loc_nm account_name
                           ,NULL client
                           ,NULL INTERFACE_FLAG
                           ,NULL INTERFACE_TIME
                           ,NULL INTERFACE_DATE
                           ,stc.loc_num SOLD_TO
                           ,NULL DELIVERY_BLOCK
                           ,NULL BILLING_BLOCK
                           ,NULL COLLECTIVE_NUMBER
                           ,NULL LAST_COMMENT_TIME --substr(ad.ezuptime,9) LAST_COMMENT_TIME
                           ,NULL LAST_COMMENT_SEQUENCE
                           ,NULL ORDER_DELETE_FLAG
                           ,NULL ORDER_HEADER_CHANGES_FLAG
                           ,NULL NAME
                           ,c.cpo_ord_ts DOCUMENT_DATE
                    FROM cpo c
--                         ,ds_cpo dc -- DB Change
                         ,ds_ord_tp dot
                        -- ,cpo_ord_tp cot QC54017
                         ,sell_to_cust stc
                         ,ord_hdr_sts ohs
                         ,canon_e633_ordhr2process_gtbl v
--                         ,att_data ad
                         ,ds_ord_catg doc
                    WHERE c.glbl_cmpy_cd = 'ADB'
                      AND c.ezcancelflag = '0'
--                      AND dc.glbl_cmpy_cd = 'ADB'
--                      AND dc.ezcancelflag = '0'
                      AND dot.ezcancelflag = '0'
                      AND dot.glbl_cmpy_cd = 'ADB'
                     -- AND cot.glbl_cmpy_cd = 'ADB' --QC54017
                     -- AND cot.ezcancelflag = '0' --QC54017
                      AND ohs.glbl_cmpy_cd = 'ADB'
                      AND ohs.ezcancelflag = '0'
--                      AND ad.ezbusinessid(+) = 'NWAL1500'
--                      AND ad.att_data_tp_cd(+) = 'NT'
                      AND doc.ezcancelflag = '0'
                      AND doc.glbl_cmpy_cd = 'ADB'
                      AND v.cpo_ord_number = c.cpo_ord_num
--                      AND c.cpo_ord_num = dc.cpo_ord_num -- DB Change
--                      AND c.cpo_ord_num = ad.att_data_grp_txt(+)
                      AND dot.ds_ord_tp_cd = c.ds_ord_tp_cd -- DB Change
					   --AND dot.ds_ord_tp_nm NOT LIKE ('%PARTS%') --QC51955
		      --AND dot.ds_ord_tp_nm NOT LIKE ('%PART%') --QC54017
			  AND (doc.ds_ord_catg_nm NOT LIKE ('%PART%') AND doc.ds_ord_catg_nm NOT LIKE('%SUPPL%')) -- QC54017
                     -- AND c.cpo_ord_tp_cd = cot.cpo_ord_tp_cd --QC54017
                      AND c.sell_to_cust_cd = stc.sell_to_cust_cd
                      AND c.ord_hdr_sts_cd = ohs.ord_hdr_sts_cd
                      AND c.ds_ord_catg_cd = doc.ds_ord_catg_cd -- db Change
                      AND lower(ohs.ord_hdr_sts_nm) IN ('saved','validated','closed') -- QC31072
                       ) s21
            ON (stg.document_number = s21.document_number)
            WHEN MATCHED THEN UPDATE SET
                 stg.CLIENT = s21.CLIENT
                  ,stg.CREATION_DATE = s21.CREATION_DATE
                  ,stg.CREATION_TIME = s21.CREATION_TIME
                  ,stg.CREATION_USER = s21.CREATION_USER
                  ,stg.INTERFACE_FLAG = s21.INTERFACE_FLAG
                  ,stg.INTERFACE_DATE = s21.INTERFACE_DATE
                  ,stg.INTERFACE_TIME  = s21.INTERFACE_TIME
                  ,stg.REQUESTED_DELIVERY = s21.REQUESTED_DELIVERY
                  ,stg.SOLD_TO = s21.SOLD_TO
                  ,stg.ORDER_TYPE = s21.ORDER_TYPE
                  ,stg.ORDER_REASON  = s21.ORDER_REASON
                  ,stg.DELIVERY_BLOCK = s21.DELIVERY_BLOCK
                  ,stg.BILLING_BLOCK = s21.BILLING_BLOCK
                  ,stg.COLLECTIVE_NUMBER = s21.COLLECTIVE_NUMBER
                  ,stg.LAST_COMMENT_DATE = s21.LAST_COMMENT_DATE
                  ,stg.LAST_COMMENT_TIME = s21.LAST_COMMENT_TIME
                  ,stg.LAST_COMMENT_SEQUENCE = s21.LAST_COMMENT_SEQUENCE
                  ,stg.ORDER_DELETE_FLAG = s21.ORDER_DELETE_FLAG
                  ,stg.ORDER_HEADER_CHANGES_FLAG = s21.ORDER_HEADER_CHANGES_FLAG
                  ,stg.NAME = s21.NAME
                  ,stg.FAST_TRACK_ID = s21.FAST_TRACK_ID
                  ,stg.FAST_TRACK_NUMBER = s21.FAST_TRACK_NUMBER
                  ,stg.DOCUMENT_DATE = s21.DOCUMENT_DATE
                  ,stg.ORDER_SIGNED_DATE = s21.ORDER_SIGNED_DATE
                  ,stg.ORIGINAL_PAPERWORK = s21.ORIGINAL_PAPERWORK_date
                  ,stg.ORIGINAL_REQUESTED_DELIVERY_DT = s21.ORIGINAL_REQUESTED_DELIVERY_DT
                  ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_order_id IS NULL THEN 'I' ELSE 'U' END)
                  ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_order_id IS NULL THEN 'I' ELSE 'U' END)
                  ,stg.lfs_sf_status_message = NULL
                  ,stg.pps_sf_status_message = NULL
                  ,stg.lfs_last_update_date = SYSDATE
                  ,stg.pps_last_update_date = SYSDATE
             WHEN NOT MATCHED THEN INSERT(CLIENT
                  ,DOCUMENT_NUMBER
                  ,CREATION_DATE
                  ,CREATION_TIME
                  ,CREATION_USER
                  ,INTERFACE_FLAG
                  ,INTERFACE_DATE
                  ,INTERFACE_TIME
                  ,REQUESTED_DELIVERY
                  ,SOLD_TO
                  ,ORDER_TYPE
                  ,ORDER_REASON
                  ,DELIVERY_BLOCK
                  ,BILLING_BLOCK
                  ,COLLECTIVE_NUMBER
                  ,LAST_COMMENT_DATE
                  ,LAST_COMMENT_TIME
                  ,LAST_COMMENT_SEQUENCE
                  ,ORDER_DELETE_FLAG
                  ,ORDER_HEADER_CHANGES_FLAG
                  ,NAME
                  ,FAST_TRACK_ID
                  ,FAST_TRACK_NUMBER
                  ,DOCUMENT_DATE
                  ,ORDER_SIGNED_DATE
                  ,ORIGINAL_PAPERWORK
                  ,ORIGINAL_REQUESTED_DELIVERY_DT
                  ,lfs_sf_status_flag
                  ,pps_sf_status_flag
                  ,lfs_sf_status_message
                  ,pps_sf_status_message
                  ,lfs_last_update_date
                  ,pps_last_update_date) VALUES (s21.CLIENT
                                                          ,s21.DOCUMENT_NUMBER
                                                          ,s21.CREATION_DATE
                                                          ,s21.CREATION_TIME
                                                          ,s21.CREATION_USER
                                                          ,s21.INTERFACE_FLAG
                                                          ,s21.INTERFACE_DATE
                                                          ,s21.INTERFACE_TIME
                                                          ,s21.REQUESTED_DELIVERY
                                                          ,s21.SOLD_TO
                                                          ,s21.ORDER_TYPE
                                                          ,s21.ORDER_REASON
                                                          ,s21.DELIVERY_BLOCK
                                                          ,s21.BILLING_BLOCK
                                                          ,s21.COLLECTIVE_NUMBER
                                                          ,s21.LAST_COMMENT_DATE
                                                          ,s21.LAST_COMMENT_TIME
                                                          ,s21.LAST_COMMENT_SEQUENCE
                                                          ,s21.ORDER_DELETE_FLAG
                                                          ,s21.ORDER_HEADER_CHANGES_FLAG
                                                          ,s21.NAME
                                                          ,s21.FAST_TRACK_ID
                                                          ,s21.FAST_TRACK_NUMBER
                                                          ,s21.DOCUMENT_DATE
                                                          ,s21.ORDER_SIGNED_DATE
                                                          ,s21.ORIGINAL_PAPERWORK_date
                                                          ,s21.ORIGINAL_REQUESTED_DELIVERY_DT
                                                          ,'I'
                                                          ,'I'
                                                          ,NULL
                                                          ,NULL
                                                          ,SYSDATE
                                                          ,SYSDATE );
            dbms_output.put_line(sql%rowcount || ' loaded into canon_e633_ordhdr_stg_tbl');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' loaded into canon_e633_ordhdr_stg_tbl',NULL,NULL,NULL,NULL);

			BEGIN
            MERGE INTO canon_e633_ordhdr_stg_tbl A
              USING (SELECT e633.document_number, doc.ds_ord_catg_cd, doc.ds_ord_catg_nm
                      FROM ds_ord_catg doc
                           ,cpo c
                           ,canon_e633_ordhdr_stg_tbl e633
                      WHERE doc.ezcancelflag = '0'
                        AND doc.glbl_cmpy_cd = 'ADB'
                        AND c.ezcancelflag = doc.ezcancelflag
                        AND c.glbl_cmpy_cd = doc.glbl_cmpy_cd
                        AND e633.document_number = c.cpo_ord_num
                        AND c.ds_ord_catg_cd = doc.ds_ord_catg_cd
                        AND e633.lfs_sf_status_flag NOT IN ('I','U')
                        AND nvl(doc.ds_ord_catg_nm,'NA') <> nvl(e633.order_type,'NA')
                        )b
                 ON (A.document_number = b.document_number)
               WHEN MATCHED THEN UPDATE
                SET A.order_type = b.ds_ord_catg_nm
                    ,A.lfs_sf_status_flag = (CASE WHEN lfs_sf_order_id IS NOT NULL THEN 'U' ELSE lfs_sf_status_flag END)
                    ,A.pps_sf_status_flag = (CASE WHEN pps_sf_order_id IS NOT NULL THEN 'U' ELSE pps_sf_status_flag END);
                    dbms_output.put_line(sql%rowcount || ' updated for order_type');
                    canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated for order_type',NULL,NULL,NULL,NULL);
               COMMIT;
              exception WHEN others THEN
                dbms_output.put_line('Exception for order_type updates');
                canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR','Exception for order_type updates',NULL,NULL,NULL,'Exception for order_type updates');
              END ;
            --QC54017 --END

            MERGE INTO canon_e633_ordhdr_stg_tbl a
              USING (SELECT distinct att_data_grp_txt sales_document
                            ,FIRST_VALUE(ezuptime) OVER(ORDER BY att_data_grp_txt desc) last_update_date
                       FROM att_data a
                      WHERE att_data_tp_cd = 'NT'--note
                        AND ezbusinessid='NWAL1500' --order entry
                        AND ezcancelflag = '0'
                        AND glbl_cmpy_cd = 'ADB'
                      ) s21
                 ON (a.document_number = s21.sales_document)
              WHEN MATCHED THEN UPDATE
                    SET last_comment_date = substr(s21.last_update_date,1,8)
                        ,last_comment_time = substr(s21.last_update_date,9);
            dbms_output.put_line(sql%rowcount || ' updated for last comment dates');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated for last comment dates',NULL,NULL,NULL,NULL);


            BEGIN
				UPDATE canon_s21_cd_val_tbl v
				   SET v.val76 = l_program_start_date
				 WHERE v.val1 = 'ORD_HEADER'
				   AND v.cd_id = (SELECT cd_id
								   FROM canon_s21_cd_tbl
								   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
			   EXCEPTION
				 WHEN OTHERS THEN
				   dbms_output.put_line('update ORD_HEADER last run date exception: '|| SQLERRM);
				   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		    END;
            COMMIT;

EXCEPTION
    WHEN OTHERS THEN
    --x_return_status := 'E';
        --dbms_output.put_line('x_return_status:'||x_return_status);
        dbms_output.put_line(SQLERRM);
        retcode := '2';
        errbuff := SQLERRM;
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END load_order_headers;


PROCEDURE load_order_details(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
IS
    l_procedure_name varchar2(50) := 'load_order_details';
    l_last_run_date DATE;
    l_program_start_date DATE := SYSDATE;
    l_last_run_date_num VARCHAR2(17);
    l_last_rma_run_date DATE;
    l_last_rma_run_date_num varchar(17);

BEGIN
        retcode := '0';

         BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'ORD_DETAIL';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;

    BEGIN
	   SELECT last_run_dt
		 INTO l_last_rma_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'ORD_RMA';
	   IF l_last_rma_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_rma_run_date_num := TO_CHAR(l_last_rma_run_date, 'RRRRMMDDHH24MISS');
    END;


   --QC58464 --start --to reprocess and unprocessed records
         UPDATE canon_e633_orddtl_stg_tbl  stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_ord_detail_id IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC58464 --start
         UPDATE canon_e633_orddtl_stg_tbl  stg
            SET pps_sf_status_flag =  'I'
            WHERE  pps_sf_status_flag IS NULL
              AND pps_sf_ord_detail_id IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464



    --to reprocess error and unprocessed records
		UPDATE canon_e633_orddtl_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_ord_detail_id IS NOT NULL THEN 'U' ELSE 'I' END
          WHERE lfs_sf_status_flag = 'E';
--          fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U / I : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of LFS error records updated to U / I : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

          UPDATE canon_e633_orddtl_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_ord_detail_id IS NOT NULL THEN 'U' ELSE 'I' END
          WHERE pps_sf_status_flag = 'E' ;
--          fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U / I : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U / I : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

         COMMIT;

         INSERT
                  INTO canon_e633_ordtl2process_gtbl
                  (cpo_ord_number,
                    sell_to_cust_cd
                   )
                   SELECT distinct cd.cpo_ord_num
                           ,c.sell_to_cust_cd
                      FROM cpo c
                           ,cpo_dtl cd
                     WHERE c.ezcancelflag = '0'
                       AND c.glbl_cmpy_cd = 'ADB'
                       AND cd.ezcancelflag = '0'
                       AND cd.glbl_cmpy_cd = 'ADB'
                       AND c.cpo_ord_num = cd.cpo_ord_num
                       --AND TO_NUMBER(cd.ezuptime) >= TO_NUMBER(l_last_run_date_num)
					   AND CAST(TO_TIMESTAMP (cd.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date
                   UNION
                   SELECT distinct dcd.cpo_ord_num
                          ,sell_to_cust_cd
                     FROM cpo_dtl dcd -- DB Changes
                          ,cpo c
                    WHERE dcd.ezcancelflag = '0'
                      AND dcd.glbl_cmpy_cd = 'ADB'
                      AND c.ezcancelflag = '0'
                      AND c.glbl_cmpy_cd = 'ADB'
                      --AND ds_cpo_dtl_upd_ts >= l_last_run_date_num -- DB Changes
					  AND CAST(TO_TIMESTAMP (DS_CPO_DTL_UPD_TS, 'RRRRMMDDHH24MISSFF3') AS DATE) >= L_LAST_RUN_DATE
                      AND DCD.CPO_ORD_NUM = C.CPO_ORD_NUM
                    UNION
                      SELECT distinct CPRD.CPO_ORD_NUM
                             ,SELL_TO_CUST_CD
                        FROM DS_CPO_RTRN_DTL CPRD
                             ,CPO C
                        WHERE CPRD.CPO_ORD_NUM = C.CPO_ORD_NUM
                           AND C.EZCANCELFLAG = '0'
                           AND C.GLBL_CMPY_CD = 'ADB'
                           AND CPRD.EZCANCELFLAG = '0'
                           AND cprd.glbl_cmpy_cd = 'ADB'
                           AND CAST(TO_TIMESTAMP (CprD.EZUPTIME, 'RRRRMMDDHH24MISSFF3') AS DATE) >= L_LAST_RMA_RUN_DATE
                    ;
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' loaded into canon_e633_ordtl2process_gtbl',NULL,NULL,NULL,NULL);

                 DELETE FROM canon_e633_ordtl2process_gtbl a
                   WHERE EXISTS (SELECT 1
                                   FROM pty_loc_wrk plw
--                                        ,ds_pty_loc_wrk dplw -- DB Changes
                                        ,sell_to_cust stc
                                        ,acct_trty_role_asg atra
                                        ,trty_grp_tp tgt
                                    WHERE plw.ezcancelflag = '0'
                                       AND plw.glbl_cmpy_cd = 'ADB'
                                       AND atra.ezcancelflag = '0'
                                       AND atra.glbl_cmpy_cd = 'ADB'
                                       AND tgt.ezcancelflag = '0'
                                       AND tgt.glbl_cmpy_cd = 'ADB'
--                                       AND dplw.ezcancelflag = '0' -- DB Changes
--                                       AND dplw.glbl_cmpy_cd = 'ADB' -- DB Changes
                                       AND stc.ezcancelflag = '0'
                                       AND stc.glbl_cmpy_cd = 'ADB'
                                       AND stc.sell_to_cust_cd = a.sell_to_cust_cd
                                       AND plw.loc_num = stc.loc_num
                                       AND plw.loc_num = atra.loc_num
									   AND atra.trty_grp_tp_cd = tgt.trty_grp_tp_cd
									   AND atra.line_biz_role_tp_cd IS NOT NULL
									   AND tgt.line_biz_tp_cd NOT IN ('LFS','PPS')
                                  );
            dbms_output.put_line(sql%rowcount || ' deleted from canon_e633_ordtl2process_gtbl');
            COMMIT;

            MERGE INTO canon_e633_orddtl_stg_tbl stg
              USING( SELECT distinct dcd.cpo_ord_num sales_document
                                ,dcd.cpo_ord_num || dcd.cpo_dtl_line_num || dcd.cpo_dtl_line_sub_num unique_key
                                ,dcd.ds_cpo_dtl_upd_ts LAST_STATUS_DATE
                                ,dcd.ds_cpo_dtl_upd_ts LAST_STATUS_TIME
                                ,dcdv.ord_line_dply_sts_desc_txt current_line_status
                                ,dcdv.ord_qty cumulative_order_quantity
                                ,dcdv.ent_cpo_dtl_deal_net_amt net_price
                                ,dcv.crat_ts_dply_txt creation_date
                                ,(dcd.cpo_dtl_line_num || dcd.cpo_dtl_line_sub_num) sales_document_item
                                ,dcdv.canc_rsn_desc_txt reason_for_rejection
                                ,dcdv.ship_to_loc_nm ship_to_party
                                ,dcdv.mdse_cd material_number
                                ,dcdv.ser_num SERIAL_NUMBER
                                ,NULL actual_ship_date
                                ,NULL alias_for_the_transaction
                                ,NULL bill_of_lading
                                ,NULL carrier_from
                                ,NULL overall_credit_status
--                                ,NULL current_line_status
                                ,NULL delivery_date
                                ,NULL delivery_priority
                                ,NULL install_a_rep_name
                                ,NULL model_series
                                ,NULL planned_goods_movement_date
                                ,NULL sales_district
                                ,NULL sales_group
                                ,NULL sales_office
                                ,NULL sales_rep_name
                                ,NULL sales_rep_id
                                ,NULL name
                                ,NULL client
                                ,NULL ENTRY_TIME
                                ,NULL CREATION_NAME
                                ,NULL PRODUCT_HIERARCHY
                                ,NULL INTERFACE_FLAG
                                ,NULL INTERFACE_DATE
                                ,NULL INTERFACE_TIME
                                ,NULL INSTALL_A_REP_ID
                                ,NULL GENERATED_DELIVERY_NUMBER
                                ,NULL DELIVERY_ITEM
                                ,NULL ACTUAL_GOODS_MOVEMENT_DATE
                                ,NULL BILLING_DOCUMENT
                                ,NULL BILLING_DOCUMENT_ITEM
                                ,NULL SCHD_LN_BLOCKED_FOR_DELV
                                ,NULL BILLING_BLOCK_FOR_ITEM
                                ,NULL ORDER_IS_GEN_INCOMPLETE
                                ,NULL ORDER_LINE_DELETE_STATUS
                                ,NULL TOTAL_PRICE
                                ,NULL OMSI_CONFIGURATION_NUMBER
                                ,NULL START_TIME_OF_TRANSACTION
                                ,NULL START_TIME_ZONE_OF_TRANSACTION
                                ,NULL ORIGINAL_SCHEDULE_LINE_DATE
                                ,NULL SCHEDULE_LINE_DATE
                         FROM cpo_dtl dcd
                              ,cpo_v dcv
                              ,cpo_dtl_v dcdv
                            --  ,ds_ord_sts_v dosv
                              ,canon_e633_ordtl2process_gtbl e633
                        WHERE dcd.ezcancelflag = '0'
                          AND dcd.glbl_cmpy_cd = 'ADB'
                          AND dcv.ezcancelflag = '0'
                          AND dcv.glbl_cmpy_cd = 'ADB'
                          AND dcdv.ezcancelflag = '0'
                          AND dcdv.glbl_cmpy_cd = 'ADB'
                         -- AND dosv.ezcancelflag(+) = '0'
                         -- AND dosv.glbl_cmpy_cd(+) = 'ADB'
                          AND dcd.cpo_ord_num = dcdv.cpo_ord_num
                          AND dcd.cpo_dtl_line_num = dcdv.cpo_dtl_line_num
                          AND dcd.cpo_dtl_line_sub_num = dcdv.cpo_dtl_line_sub_num
                          AND dcd.cpo_ord_num = dcv.cpo_ord_num
                         -- AND dcd.cpo_ord_num = dosv.cpo_ord_num(+)
                        --  AND dcd.cpo_dtl_line_num = dosv.cpo_dtl_line_num(+)
                        --  AND dcd.cpo_dtl_line_sub_num = dosv.cpo_dtl_line_sub_num(+)
                         -- AND lower(dcdv.ord_line_dply_sts_desc_txt) NOT IN ('cancelled','closed')
						  AND lower(dcdv.ord_line_dply_sts_desc_txt) NOT IN ('cancelled') -- QC31072
--                          AND dcv.line_biz_tp_cd IN ('LFS','PPS')
                          AND e633.cpo_ord_number = dcd.cpo_ord_num
                          AND EXISTS (SELECT 1
                                        FROM canon_e633_ordhdr_stg_tbl hdr
                                        WHERE 1 = 1
                                          AND hdr.document_number = dcd.cpo_ord_num)
                         /* AND NOT EXISTS(SELECT 1
                                          FROM ds_cpo_rtrn_dtl cprd
                                          WHERE dcd.cpo_ord_num = cprd.cpo_ord_num
                                            AND dcd.cpo_dtl_line_num = cprd.ds_cpo_rtrn_line_num
                                            AND dcd.cpo_dtl_line_sub_num = cprd.ds_cpo_rtrn_line_sub_num)*/

                          UNION
                            SELECT DISTINCT cprd.cpo_ord_num sales_document
                                ,cprd.cpo_ord_num || cprd.ds_cpo_rtrn_line_num || cprd.ds_cpo_rtrn_line_sub_num || 'RMA' unique_key
                                ,substr(cprd.ezuptime,1,8) --cprd.ds_cpo_dtl_upd_ts last_status_date
                                ,substr(cprd.ezuptime,9) --dcd.ds_cpo_dtl_upd_ts LAST_STATUS_TIME
                                ,rlds.RTRN_LINE_DPLY_STS_DESC_TXT current_line_status
                                ,cprd.ORD_QTY CUMULATIVE_ORDER_QUANTITY
                                ,cprd.ent_cpo_dtl_deal_net_amt net_price
                                ,dcv.crat_ts_dply_txt creation_date
                                ,(CPRD.DS_CPO_RTRN_LINE_NUM || CPRD.DS_CPO_RTRN_LINE_SUB_NUM) SALES_DOCUMENT_ITEM --(dcd.cpo_dtl_line_num || dcd.cpo_dtl_line_sub_num) sales_document_item
                                ,rr.rtrn_rsn_desc_txt REASON_FOR_REJECTION
                                ,cprd.SHIP_TO_LOC_NM SHIP_TO_PARTY
                                ,cprd.mdse_cd material_number
                                ,cprd.ser_num SERIAL_NUMBER
                                ,NULL actual_ship_date
                                ,NULL alias_for_the_transaction
                                ,NULL bill_of_lading
                                ,NULL carrier_from
                                ,NULL overall_credit_status
--                                ,NULL current_line_status
                                ,NULL delivery_date
                                ,NULL delivery_priority
                                ,NULL install_a_rep_name
                                ,NULL model_series
                                ,NULL planned_goods_movement_date
                                ,NULL sales_district
                                ,NULL sales_group
                                ,NULL sales_office
                                ,NULL sales_rep_name
                                ,NULL sales_rep_id
                                ,NULL name
                                ,NULL client
                                ,NULL ENTRY_TIME
                                ,NULL CREATION_NAME
                                ,NULL PRODUCT_HIERARCHY
                                ,NULL INTERFACE_FLAG
                                ,NULL INTERFACE_DATE
                                ,NULL INTERFACE_TIME
                                ,NULL INSTALL_A_REP_ID
                                ,NULL GENERATED_DELIVERY_NUMBER
                                ,NULL DELIVERY_ITEM
                                ,NULL ACTUAL_GOODS_MOVEMENT_DATE
                                ,NULL BILLING_DOCUMENT
                                ,NULL BILLING_DOCUMENT_ITEM
                                ,NULL SCHD_LN_BLOCKED_FOR_DELV
                                ,NULL BILLING_BLOCK_FOR_ITEM
                                ,NULL ORDER_IS_GEN_INCOMPLETE
                                ,NULL ORDER_LINE_DELETE_STATUS
                                ,NULL TOTAL_PRICE
                                ,NULL OMSI_CONFIGURATION_NUMBER
                                ,NULL START_TIME_OF_TRANSACTION
                                ,NULL START_TIME_ZONE_OF_TRANSACTION
                                ,NULL ORIGINAL_SCHEDULE_LINE_DATE
                                ,NULL SCHEDULE_LINE_DATE
                         FROM cpo_v dcv
--                              ,cpo_dtl_v dcdv
                            --  ,ds_ord_sts_v dosv
                              ,CANON_E633_ORDTL2PROCESS_GTBL E633
                              ,ds_cpo_rtrn_dtl cprd
                              ,RTRN_LINE_DPLY_STS RLDS
                              ,s21_csa_apps.rtrn_rsn rr
                        WHERE cprd.ezcancelflag = '0'
                          AND cprd.glbl_cmpy_cd = 'ADB'
                          AND dcv.ezcancelflag = '0'
                          AND dcv.glbl_cmpy_cd = 'ADB'
                          AND RLDS.GLBL_CMPY_CD = 'ADB'
                          AND RLDS.EZCANCELFLAG = '0'
                          AND rr.ezcancelflag = '0'
                          AND RR.GLBL_CMPY_CD = 'ADB'
                          AND cprd.cpo_ord_num = dcv.cpo_ord_num
                          AND E633.CPO_ORD_NUMBER = CPRD.CPO_ORD_NUM
						  AND cprd.rtrn_line_dply_sts_cd = rlds.rtrn_line_dply_sts_cd
                          AND cprd.rtrn_rsn_cd = rr.rtrn_rsn_cd
                          AND EXISTS (SELECT 1
                                        FROM canon_e633_ordhdr_stg_tbl hdr
                                       WHERE hdr.document_number = cprd.cpo_ord_num)
                         /* AND NOT EXISTS(SELECT 1
                                          FROM cpo_dtl cdv
                                          WHERE cdv.cpo_ord_num = cprd.cpo_ord_num
                                            AND cdv.cpo_dtl_line_num = cprd.ds_cpo_rtrn_line_num
                                            AND cdv.cpo_dtl_line_sub_num = cprd.ds_cpo_rtrn_line_sub_num)   */
                    ) s21
           ON( stg.unique_key = s21.unique_key)
          WHEN MATCHED THEN UPDATE SET
                stg.CLIENT = s21.CLIENT
                ,stg.SALES_DOCUMENT = s21.sales_document
                  ,stg.sales_document_item = s21.SALES_DOCUMENT_ITEM
                  ,stg.CREATION_DATE = s21.CREATION_DATE
                  ,stg.ENTRY_TIME = s21.ENTRY_TIME
                  ,stg.CREATION_NAME = s21.CREATION_NAME
                  ,stg.MATERIAL_NUMBER = s21.MATERIAL_NUMBER
                  ,stg.PRODUCT_HIERARCHY = s21.PRODUCT_HIERARCHY
                  ,stg.MODEL_SERIES = s21.MODEL_SERIES
                  ,stg.CUMULATIVE_ORDER_QUANTITY = s21.CUMULATIVE_ORDER_QUANTITY
                  ,stg.CURRENT_LINE_STATUS  = s21.CURRENT_LINE_STATUS
                  ,stg.LAST_STATUS_DATE = s21.LAST_STATUS_DATE
                  ,stg.LAST_STATUS_TIME = s21.LAST_STATUS_TIME
                  ,stg.INTERFACE_FLAG = s21.INTERFACE_FLAG
                  ,stg.INTERFACE_DATE = s21.INTERFACE_DATE
                  ,stg.INTERFACE_TIME  = s21.INTERFACE_TIME
                  ,stg.SERIAL_NUMBER = s21.SERIAL_NUMBER
                  ,stg.BILL_OF_LADING  = s21.BILL_OF_LADING
                  ,stg.CARRIER_FROM = s21.CARRIER_FROM
                  ,stg.SALES_REP_ID = s21.SALES_REP_ID
                  ,stg.SALES_REP_NAME = s21.SALES_REP_NAME
                  ,stg.INSTALL_A_REP_ID = s21.INSTALL_A_REP_ID
                  ,stg.INSTALL_A_REP_NAME = s21.INSTALL_A_REP_NAME
                  ,stg.SALES_OFFICE = s21.SALES_OFFICE
                  ,stg.SALES_GROUP = s21.SALES_GROUP
                  ,stg.SALES_DISTRICT = s21.SALES_DISTRICT
                  ,stg.GENERATED_DELIVERY_NUMBER = s21.GENERATED_DELIVERY_NUMBER
                  ,stg.DELIVERY_ITEM = s21.DELIVERY_ITEM
                  ,stg.PLANNED_GOODS_MOVEMENT_DATE  = s21.PLANNED_GOODS_MOVEMENT_DATE
                  ,stg.ACTUAL_GOODS_MOVEMENT_DATE = s21.ACTUAL_GOODS_MOVEMENT_DATE
                  ,stg.ACTUAL_SHIP_DATE  = s21.ACTUAL_SHIP_DATE
                  ,stg.BILLING_DOCUMENT = s21.BILLING_DOCUMENT
                  ,stg.BILLING_DOCUMENT_ITEM = s21.BILLING_DOCUMENT_ITEM
                  ,stg.SCHD_LN_BLOCKED_FOR_DELV = s21.SCHD_LN_BLOCKED_FOR_DELV
                  ,stg.BILLING_BLOCK_FOR_ITEM  = s21.BILLING_BLOCK_FOR_ITEM
                  ,stg.REASON_FOR_REJECTION  = s21.REASON_FOR_REJECTION
                  ,stg.ORDER_IS_GEN_INCOMPLETE = s21.ORDER_IS_GEN_INCOMPLETE
                  ,stg.OVERALL_CREDIT_STATUS = s21.OVERALL_CREDIT_STATUS
                  ,stg.NET_PRICE = s21.NET_PRICE
                  ,stg.ORDER_LINE_DELETE_STATUS = s21.ORDER_LINE_DELETE_STATUS
                  ,stg.TOTAL_PRICE  = s21.TOTAL_PRICE
                  ,stg.DELIVERY_DATE = s21.DELIVERY_DATE
                  ,stg.OMSI_CONFIGURATION_NUMBER = s21.OMSI_CONFIGURATION_NUMBER
                  ,stg.START_TIME_OF_TRANSACTION = s21.START_TIME_OF_TRANSACTION
                  ,stg.START_TIME_ZONE_OF_TRANSACTION  = s21.START_TIME_ZONE_OF_TRANSACTION
                  ,stg.ALIAS_FOR_THE_TRANSACTION  = s21.ALIAS_FOR_THE_TRANSACTION
                  ,stg.ORIGINAL_SCHEDULE_LINE_DATE = s21.ORIGINAL_SCHEDULE_LINE_DATE
                  ,stg.DELIVERY_PRIORITY  = s21.DELIVERY_PRIORITY
                  ,stg.SHIP_TO_PARTY  = s21.SHIP_TO_PARTY
                  ,stg.NAME = s21.NAME
                  ,stg.SCHEDULE_LINE_DATE = s21.SCHEDULE_LINE_DATE
                  ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_ord_detail_id IS NULL THEN 'I' ELSE 'U' END)
                  ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_ord_detail_id IS NULL THEN 'I' ELSE 'U' END)
                  ,stg.lfs_sf_status_message = NULL
                  ,stg.pps_sf_status_message = NULL
                  ,stg.lfs_last_update_date = SYSDATE
                  ,stg.pps_last_update_date = SYSDATE
--                  ,unique_key = trim(ext.sales_document) || trim(ext.sales_document_item)
            WHEN NOT MATCHED THEN INSERT(CLIENT
                  ,SALES_DOCUMENT
                  ,SALES_DOCUMENT_ITEM
                  ,CREATION_DATE
                  ,ENTRY_TIME
                  ,CREATION_NAME
                  ,MATERIAL_NUMBER
                  ,PRODUCT_HIERARCHY
                  ,MODEL_SERIES
                  ,CUMULATIVE_ORDER_QUANTITY
                  ,CURRENT_LINE_STATUS
                  ,LAST_STATUS_DATE
                  ,LAST_STATUS_TIME
                  ,INTERFACE_FLAG
                  ,INTERFACE_DATE
                  ,INTERFACE_TIME
                  ,SERIAL_NUMBER
                  ,BILL_OF_LADING
                  ,CARRIER_FROM
                  ,SALES_REP_ID
                  ,SALES_REP_NAME
                  ,INSTALL_A_REP_ID
                  ,INSTALL_A_REP_NAME
                  ,SALES_OFFICE
                  ,SALES_GROUP
                  ,SALES_DISTRICT
                  ,GENERATED_DELIVERY_NUMBER
                  ,DELIVERY_ITEM
                  ,PLANNED_GOODS_MOVEMENT_DATE
                  ,ACTUAL_GOODS_MOVEMENT_DATE
                  ,ACTUAL_SHIP_DATE
                  ,BILLING_DOCUMENT
                  ,BILLING_DOCUMENT_ITEM
                  ,SCHD_LN_BLOCKED_FOR_DELV
                  ,BILLING_BLOCK_FOR_ITEM
                  ,REASON_FOR_REJECTION
                  ,ORDER_IS_GEN_INCOMPLETE
                  ,OVERALL_CREDIT_STATUS
                  ,NET_PRICE
                  ,ORDER_LINE_DELETE_STATUS
                  ,TOTAL_PRICE
                  ,DELIVERY_DATE
                  ,OMSI_CONFIGURATION_NUMBER
                  ,START_TIME_OF_TRANSACTION
                  ,START_TIME_ZONE_OF_TRANSACTION
                  ,ALIAS_FOR_THE_TRANSACTION
                  ,ORIGINAL_SCHEDULE_LINE_DATE
                  ,DELIVERY_PRIORITY
                  ,SHIP_TO_PARTY
                  ,NAME
                  ,SCHEDULE_LINE_DATE
                  ,lfs_sf_status_flag
                  ,pps_sf_status_flag
                  ,lfs_sf_status_message
                  ,pps_sf_status_message
                  ,lfs_last_update_date
                  ,pps_last_update_date
                  ,unique_key) VALUES (s21.CLIENT
                                      ,s21.SALES_DOCUMENT
                                      ,s21.SALES_DOCUMENT_ITEM
                                      ,s21.CREATION_DATE
                                      ,s21.ENTRY_TIME
                                      ,s21.CREATION_NAME
                                      ,s21.MATERIAL_NUMBER
                                      ,s21.PRODUCT_HIERARCHY
                                      ,s21.MODEL_SERIES
                                      ,s21.CUMULATIVE_ORDER_QUANTITY
                                      ,s21.CURRENT_LINE_STATUS
                                      ,s21.LAST_STATUS_DATE
                                      ,s21.LAST_STATUS_TIME
                                      ,s21.INTERFACE_FLAG
                                      ,s21.INTERFACE_DATE
                                      ,s21.INTERFACE_TIME
                                      ,s21.SERIAL_NUMBER
                                      ,s21.BILL_OF_LADING
                                      ,s21.CARRIER_FROM
                                      ,s21.SALES_REP_ID
                                      ,s21.SALES_REP_NAME
                                      ,s21.INSTALL_A_REP_ID
                                      ,s21.INSTALL_A_REP_NAME
                                      ,s21.SALES_OFFICE
                                      ,s21.SALES_GROUP
                                      ,s21.SALES_DISTRICT
                                      ,s21.GENERATED_DELIVERY_NUMBER
                                      ,s21.DELIVERY_ITEM
                                      ,s21.PLANNED_GOODS_MOVEMENT_DATE
                                      ,s21.ACTUAL_GOODS_MOVEMENT_DATE
                                      ,s21.ACTUAL_SHIP_DATE
                                      ,s21.BILLING_DOCUMENT
                                      ,s21.BILLING_DOCUMENT_ITEM
                                      ,s21.SCHD_LN_BLOCKED_FOR_DELV
                                      ,s21.BILLING_BLOCK_FOR_ITEM
                                      ,s21.REASON_FOR_REJECTION
                                      ,s21.ORDER_IS_GEN_INCOMPLETE
                                      ,s21.OVERALL_CREDIT_STATUS
                                      ,s21.NET_PRICE
                                      ,s21.ORDER_LINE_DELETE_STATUS
                                      ,s21.TOTAL_PRICE
                                      ,s21.DELIVERY_DATE
                                      ,s21.OMSI_CONFIGURATION_NUMBER
                                      ,s21.START_TIME_OF_TRANSACTION
                                      ,s21.START_TIME_ZONE_OF_TRANSACTION
                                      ,s21.ALIAS_FOR_THE_TRANSACTION
                                      ,s21.ORIGINAL_SCHEDULE_LINE_DATE
                                      ,s21.DELIVERY_PRIORITY
                                      ,s21.SHIP_TO_PARTY
                                      ,s21.NAME
                                      ,s21.SCHEDULE_LINE_DATE
                                      ,'I'
                                      ,'I'
                                      ,NULL
                                      ,NULL
                                      ,SYSDATE
                                      ,SYSDATE
                                      ,s21.unique_key);
             dbms_output.put_line(sql%rowcount || ' loaded into canon_e633_orddtl_stg_tbl');
			 canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' loaded into canon_e633_orddtl_stg_tbl',NULL,NULL,NULL,NULL);

           --update the shipping details info
           MERGE INTO canon_e633_orddtl_stg_tbl stg
             USING(SELECT DISTINCT FIRST_VALUE(sp.actl_ship_dt) OVER(PARTITION BY sp.trx_hdr_num,sp.trx_line_num,sp.trx_line_sub_num ORDER BY sp.shpg_pln_dply_line_num ) actual_ship_date
                          ,first_value(sp.bol_num) OVER(PARTITION BY sp.trx_hdr_num,sp.trx_line_num,sp.trx_line_sub_num ORDER BY sp.shpg_pln_dply_line_num) bill_of_lading
						  ,first_value(sod.actl_dely_dt) OVER(PARTITION BY sp.trx_hdr_num,sp.trx_line_num,sp.trx_line_sub_num ORDER BY sp.shpg_pln_dply_line_num)  delivery_date -- new
                        --  ,sod.actl_dely_dt delivery_date -- DB Change
                          ,sod.trx_hdr_num || sod.trx_line_num || sod.trx_line_sub_num unique_key
                     FROM shpg_pln sp
                          ,shpg_ord_dtl sod
--                          ,ds_shpg_ord_dtl dsod -- DB Change
                          ,canon_e633_orddtl_stg_tbl e633
                     WHERE sp.ezcancelflag = '0'
                       AND sp.glbl_cmpy_cd = 'ADB'
                       AND sod.ezcancelflag = '0'
                       AND sod.glbl_cmpy_cd = 'ADB'
--                       AND dsod.ezcancelflag = '0' -- DB Change
--                       AND dsod.glbl_cmpy_cd = 'ADB' -- DB Change
                       AND e633.unique_key = (sod.trx_hdr_num || sod.trx_line_num || sod.trx_line_sub_num)
                       AND sod.so_num  = sp.so_num
                       AND sod.so_slp_num = sp.so_slp_num
--                       AND sod.so_num = dsod.so_num -- DB Change
--                       AND sod.so_slp_num = dsod.so_slp_num -- DB Change
                       AND (e633.lfs_sf_status_flag IN ('I','U') OR e633.pps_sf_status_flag in ('I','U'))
                     ) s21
                 ON(stg.unique_key = s21.unique_key)
                 WHEN MATCHED THEN UPDATE SET
                        stg.actual_ship_date = s21.actual_ship_date
                        ,stg.bill_of_lading = s21.bill_of_lading
                        ,stg.delivery_date = s21.delivery_date;

              dbms_output.put_line(sql%rowcount || ' updated shipping details into canon_e633_orddtl_stg_tbl');
			  canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated shipping details into canon_e633_orddtl_stg_tbl',NULL,NULL,NULL,NULL);

              --update model info
              MERGE INTO canon_e633_orddtl_stg_tbl stg
                USING(SELECT distinct dmg.mdl_grp_nm model_series
                             ,dcd.cpo_ord_num || dcd.cpo_dtl_line_num || dcd.cpo_dtl_line_sub_num unique_key
                        FROM ds_mdl_grp dmg
                             ,canon_e633_orddtl_stg_tbl e633
                             ,svc_config_mstr scm
                             ,cpo_dtl dcd -- DB Change
                        WHERE dmg.ezcancelflag = '0'
                          AND dmg.glbl_cmpy_cd = 'ADB'
                          AND scm.ezcancelflag = '0'
                          AND scm.glbl_cmpy_cd = 'ADB'
                          AND dcd.ezcancelflag = '0'
                          AND dcd.glbl_cmpy_cd = 'ADB'
                          AND (dcd.cpo_ord_num || dcd.cpo_dtl_line_num || dcd.cpo_dtl_line_sub_num) = e633.unique_key
                          AND dcd.svc_config_mstr_pk = scm.svc_config_mstr_pk
                          AND SCM.MDL_ID = DMG.MDL_GRP_ID
                          AND (e633.lfs_sf_status_flag IN ('I','U') OR e633.pps_sf_status_flag IN ('I','U'))
                          UNION
                          SELECT DISTINCT dmg.mdl_grp_nm model_series
                                 ,CPRD.CPO_ORD_NUM || CPRD.DS_CPO_RTRN_LINE_NUM || CPRD.DS_CPO_RTRN_LINE_SUB_NUM || 'RMA' UNIQUE_KEY
                            FROM ds_mdl_grp dmg
                             ,canon_e633_orddtl_stg_tbl e633
                             ,SVC_CONFIG_MSTR SCM
                            -- ,cpo_dtl dcd -- DB Change
                            ,ds_cpo_rtrn_dtl cprd
                        WHERE dmg.ezcancelflag = '0'
                          AND dmg.glbl_cmpy_cd = 'ADB'
                          AND SCM.EZCANCELFLAG = '0'
                          AND SCM.GLBL_CMPY_CD = 'ADB'
                          AND CPRD.EZCANCELFLAG = '0'
                          AND cprd.glbl_cmpy_cd = 'ADB'
                          AND (cprd.cpo_ord_num || CPRD.DS_CPO_RTRN_LINE_NUM || CPRD.DS_CPO_RTRN_LINE_SUB_NUM || 'RMA') = e633.unique_key
                          AND cprd.svc_config_mstr_pk = scm.svc_config_mstr_pk
                          AND SCM.MDL_ID = DMG.MDL_GRP_ID
                          AND (E633.LFS_SF_STATUS_FLAG IN ('I','U') OR E633.PPS_SF_STATUS_FLAG IN ('I','U'))
                          ) s21
                    ON (stg.unique_key = s21.unique_key)
                    WHEN MATCHED THEN UPDATE SET
                            stg.model_series = s21.model_series;
                dbms_output.put_line(sql%rowcount || ' updated model series into canon_e633_orddtl_stg_tbl');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated model series into canon_e633_orddtl_stg_tbl',NULL,NULL,NULL,NULL);

                --update install rep name and id
                MERGE INTO canon_e633_orddtl_stg_tbl stg
                USING(SELECT DISTINCT (CASE WHEN p.psn_last_nm IS NOT NULL AND p.psn_first_nm IS NOT NULL THEN p.psn_last_nm || ' ,' || p.psn_first_nm ELSE NULL END) install_a_rep_name
                             ,p.psn_num install_a_rep_id
                             ,e633.unique_key
                        FROM ds_cpo_sls_cr dcsc
                               ,line_biz_role_tp lbrt
                               ,org_func_asg ofa
                               ,s21_psn p
                               ,cpo_dtl_v dcdv -- DB Change
                               ,canon_e633_orddtl_stg_tbl e633
                        WHERE dcsc.ezcancelflag = '0'
                          AND dcsc.glbl_cmpy_cd = 'ADB'
                          AND lbrt.ezcancelflag = '0'
                          AND lbrt.glbl_cmpy_cd = 'ADB'
                          AND ofa.ezcancelflag = '0'
                          AND ofa.glbl_cmpy_cd = 'ADB'
                          AND p.ezcancelflag = '0'
                          AND p.glbl_cmpy_cd = 'ADB'
                          AND e633.unique_key = dcdv.cpo_ord_num  ||  dcdv.cpo_dtl_line_num || dcdv.cpo_dtl_line_sub_num
                          AND dcdv.cpo_ord_num = dcsc.cpo_ord_num
                          AND dcdv.ds_cpo_config_pk = dcsc.ds_cpo_config_pk
                          AND dcsc.sls_rep_role_tp_cd = lbrt.line_biz_role_tp_cd
                          AND dcsc.sls_cr_quot_flg = 'Y'
                          AND lbrt.prim_rep_role_flg = 'N' --Installer
                          AND dcsc.sls_rep_toc_cd = ofa.toc_cd
                          AND OFA.PSN_CD = P.PSN_CD
                          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ofa.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ofa.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                          AND (e633.lfs_sf_status_flag IN ('I','U') OR e633.pps_sf_status_flag IN ('I','U'))
                       UNION
                        SELECT DISTINCT (CASE WHEN p.psn_last_nm IS NOT NULL AND p.psn_first_nm IS NOT NULL THEN p.psn_last_nm || ' ,' || p.psn_first_nm ELSE NULL END) install_a_rep_name
                             ,p.psn_num install_a_rep_id
                             ,e633.unique_key
                        FROM ds_cpo_sls_cr dcsc
                               ,LINE_BIZ_ROLE_TP LBRT
                               ,org_func_asg ofa
                               ,S21_PSN P
                             --  ,cpo_dtl_v dcdv -- DB Change
                              ,ds_cpo_rtrn_dtl cprd
                               ,canon_e633_orddtl_stg_tbl e633
                        WHERE dcsc.ezcancelflag = '0'
                          AND dcsc.glbl_cmpy_cd = 'ADB'
                          AND lbrt.ezcancelflag = '0'
                          AND lbrt.glbl_cmpy_cd = 'ADB'
                          AND ofa.ezcancelflag = '0'
                          AND ofa.glbl_cmpy_cd = 'ADB'
                          AND p.ezcancelflag = '0'
                          AND p.glbl_cmpy_cd = 'ADB'
                          AND e633.unique_key = cprd.cpo_ord_num || CPRD.DS_CPO_RTRN_LINE_NUM || CPRD.DS_CPO_RTRN_LINE_SUB_NUM || 'RMA'
                          AND CPRD.CPO_ORD_NUM = DCSC.CPO_ORD_NUM
                          AND cprd.ds_cpo_config_pk = dcsc.ds_cpo_config_pk
                          AND dcsc.sls_rep_role_tp_cd = lbrt.line_biz_role_tp_cd
                          AND dcsc.sls_cr_quot_flg = 'Y'
                          AND lbrt.prim_rep_role_flg = 'N' --Installer
                          AND dcsc.sls_rep_toc_cd = ofa.toc_cd
                          AND ofa.psn_cd = p.psn_cd
                          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ofa.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ofa.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                          AND (e633.lfs_sf_status_flag IN ('I','U') OR e633.pps_sf_status_flag in ('I','U'))
                      )s21
                   ON(stg.unique_key = s21.unique_key)
                   WHEN MATCHED THEN UPDATE SET
                        stg.install_a_rep_id = s21.install_a_rep_id
                        ,stg.install_a_rep_name = s21.install_a_rep_name;

                dbms_output.put_line(sql%rowcount || ' updated install rep info into canon_e633_orddtl_stg_tbl');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated install rep info into canon_e633_orddtl_stg_tbl',NULL,NULL,NULL,NULL);

                --update writer rep name and id
               MERGE INTO CANON_E633_ORDDTL_STG_TBL STG
     USING (SELECT --+ no_merge(dcdv) leading(e633 dcdv)
            DISTINCT
                   (CASE
                        WHEN     P.PSN_LAST_NM IS NOT NULL
                             AND P.PSN_FIRST_NM IS NOT NULL
                        THEN
                            P.PSN_LAST_NM || ' ,' || P.PSN_FIRST_NM
                        ELSE
                            NULL
                    END)
                       SALES_REP_NAME,
                   P.PSN_NUM
                       SALES_REP_ID,
                   E633.UNIQUE_KEY
              FROM DS_CPO_SLS_CR              DCSC,
                   LINE_BIZ_ROLE_TP           LBRT,
                   ORG_FUNC_ASG               OFA,
                   S21_PSN                    P,
                   CPO_DTL_V                  DCDV,
                   CANON_E633_ORDDTL_STG_TBL  E633
             WHERE     DCSC.EZCANCELFLAG = '0'
                   AND DCSC.GLBL_CMPY_CD = 'ADB'
                   AND LBRT.EZCANCELFLAG = '0'
                   AND LBRT.GLBL_CMPY_CD = 'ADB'
                   AND OFA.EZCANCELFLAG = '0'
                   AND OFA.GLBL_CMPY_CD = 'ADB'
                   AND P.EZCANCELFLAG = '0'
                   AND P.GLBL_CMPY_CD = 'ADB'
                   AND E633.UNIQUE_KEY =
                          DCDV.CPO_ORD_NUM
                       || DCDV.CPO_DTL_LINE_NUM
                       || DCDV.CPO_DTL_LINE_SUB_NUM
                   AND DCDV.CPO_ORD_NUM = DCSC.CPO_ORD_NUM
                   AND DCDV.DS_CPO_CONFIG_PK = DCSC.DS_CPO_CONFIG_PK
                   AND DCSC.SLS_REP_ROLE_TP_CD = LBRT.LINE_BIZ_ROLE_TP_CD
                   AND DCSC.SLS_CR_QUOT_FLG = 'Y'
                   AND LBRT.PRIM_REP_ROLE_FLG = 'Y'
                   AND DCSC.SLS_REP_TOC_CD = OFA.TOC_CD
                   AND OFA.PSN_CD = P.PSN_CD
                   AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                                   NVL (
                                                       TO_DATE (
                                                           OFA.EFF_FROM_DT,
                                                           'yyyymmdd'),
                                                       SYSDATE))
                                           AND TRUNC (
                                                   NVL (
                                                       TO_DATE (
                                                           OFA.EFF_THRU_DT,
                                                           'yyyymmdd'),
                                                       SYSDATE))
                   AND (   E633.LFS_SF_STATUS_FLAG IN ('I', 'U')
                        OR E633.PPS_SF_STATUS_FLAG IN ('I', 'U'))
            UNION
            SELECT DISTINCT
                   (CASE
                        WHEN     P.PSN_LAST_NM IS NOT NULL
                             AND P.PSN_FIRST_NM IS NOT NULL
                        THEN
                            P.PSN_LAST_NM || ' ,' || P.PSN_FIRST_NM
                        ELSE
                            NULL
                    END)
                       SALES_REP_NAME,
                   P.PSN_NUM
                       SALES_REP_ID,
                   E633.UNIQUE_KEY
              FROM DS_CPO_SLS_CR              DCSC,
                   LINE_BIZ_ROLE_TP           LBRT,
                   ORG_FUNC_ASG               OFA,
                   S21_PSN                    P,
                   DS_CPO_RTRN_DTL            CPRD,
                   CANON_E633_ORDDTL_STG_TBL  E633
             WHERE     DCSC.EZCANCELFLAG = '0'
                   AND DCSC.GLBL_CMPY_CD = 'ADB'
                   AND LBRT.EZCANCELFLAG = '0'
                   AND LBRT.GLBL_CMPY_CD = 'ADB'
                   AND OFA.EZCANCELFLAG = '0'
                   AND OFA.GLBL_CMPY_CD = 'ADB'
                   AND P.EZCANCELFLAG = '0'
                   AND P.GLBL_CMPY_CD = 'ADB'
                   AND E633.UNIQUE_KEY =
                          CPRD.CPO_ORD_NUM
                       || CPRD.DS_CPO_RTRN_LINE_NUM
                       || CPRD.DS_CPO_RTRN_LINE_SUB_NUM
                       || 'RMA'
                   AND CPRD.CPO_ORD_NUM = DCSC.CPO_ORD_NUM
                   AND CPRD.DS_CPO_CONFIG_PK = DCSC.DS_CPO_CONFIG_PK
                   AND DCSC.SLS_REP_ROLE_TP_CD = LBRT.LINE_BIZ_ROLE_TP_CD
                   AND DCSC.SLS_CR_QUOT_FLG = 'Y'
                   AND LBRT.PRIM_REP_ROLE_FLG = 'Y'
                   AND DCSC.SLS_REP_TOC_CD = OFA.TOC_CD
                   AND OFA.PSN_CD = P.PSN_CD
                   AND TRUNC (SYSDATE) BETWEEN TRUNC (
                                                   NVL (
                                                       TO_DATE (
                                                           OFA.EFF_FROM_DT,
                                                           'yyyymmdd'),
                                                       SYSDATE))
                                           AND TRUNC (
                                                   NVL (
                                                       TO_DATE (
                                                           OFA.EFF_THRU_DT,
                                                           'yyyymmdd'),
                                                       SYSDATE))
                   AND (   E633.LFS_SF_STATUS_FLAG IN ('I', 'U')
                        OR E633.PPS_SF_STATUS_FLAG IN ('I', 'U'))) S21
        ON (STG.UNIQUE_KEY = S21.UNIQUE_KEY)
WHEN MATCHED
THEN
    UPDATE SET
        STG.SALES_REP_ID = S21.SALES_REP_ID,
        STG.SALES_REP_NAME = S21.SALES_REP_NAME;


                dbms_output.put_line(sql%rowcount || ' updated writer rep info into canon_e633_orddtl_stg_tbl');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated writer rep info into canon_e633_orddtl_stg_tbl',NULL,NULL,NULL,NULL);

             --update account name
             MERGE INTO canon_e633_orddtl_stg_tbl stg
               USING(SELECT DISTINCT stc.ds_acct_nm name
                            ,dcv.cpo_ord_num sales_document
                       FROM sell_to_cust stc
                            ,cpo_v dcv -- DB Change
                            ,canon_e633_orddtl_stg_tbl e633
                       WHERE stc.ezcancelflag = '0'
                         AND stc.glbl_cmpy_cd = 'ADB'
                         AND dcv.ezcancelflag = '0'
                         AND dcv.glbl_cmpy_cd = 'ADB'
                         AND dcv.cpo_ord_num = e633.sales_document
                         AND dcv.sell_to_cust_cd = stc.sell_to_cust_cd
                         AND (e633.lfs_sf_status_flag IN ('I','U') OR e633.pps_sf_status_flag in ('I','U'))
                      )s21
                    ON(stg.sales_document = s21.sales_document)
                    WHEN MATCHED THEN UPDATE SET
                        stg.name = s21.name;
                 dbms_output.put_line(sql%rowcount || ' updated account name info into canon_e633_orddtl_stg_tbl');
				 canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated account name info into canon_e633_orddtl_stg_tbl',NULL,NULL,NULL,NULL);


/**
                  MERGE INTO canon_e633_orddtl_stg_tbl stg
            USING (SELECT distinct dcd.cpo_ord_num sales_document
                                   , dcd.cpo_ord_num || dcd.cpo_dtl_line_num || dcd.cpo_dtl_line_sub_num unique_key
                                       ,sp.actl_ship_dt actual_ship_date
                                       ,NULL alias_for_the_transaction
                                       ,sp.bol_num bill_of_lading
                                       ,NULL carrier_from
                                       ,NULL overall_credit_status
                                       ,dosv.ORD_LINE_STS_NM current_line_status
                                       ,dsod.actl_dely_dt delivery_date
                                       ,NULL delivery_priority
                                       ,(CASE WHEN p.psn_last_nm IS NOT NULL AND p.psn_first_nm IS NOT NULL THEN p.psn_last_nm || ' ,' || p.psn_first_nm ELSE NULL END) install_a_rep_name
                                       ,dcdv.ord_qty cumulative_order_quantity
                                       ,dmg.mdl_grp_nm model_series
                                       ,dcdv.ent_cpo_dtl_deal_net_amt net_price
                                       ,dcv.crat_ts_dply_txt creation_date
                                       ,dcdv.dply_line_ref_num sales_document_item
                                       ,NULL planned_goods_movement_date
                                       ,dcdv.canc_rsn_desc_txt reason_for_rejection
                                       ,NULL sales_district
                                       ,NULL sales_group
                                       ,NULL sales_office
                                       ,(CASE WHEN pW.psn_last_nm IS NOT NULL AND pW.psn_first_nm IS NOT NULL THEN pW.psn_last_nm || ' ,' || pW.psn_first_nm ELSE NULL END) sales_rep_name
                                       ,pW.psn_num sales_rep_id
                                       ,dcdv.ship_to_loc_nm ship_to_party
                                       ,dac.ds_acct_nm name
                                       ,dcdv.mdse_cd material_number
                                       ,NULL client
                                      ,NULL ENTRY_TIME
                                      ,NULL CREATION_NAME
                                      ,NULL PRODUCT_HIERARCHY
                                      ,dcd.ds_cpo_dtl_upd_ts LAST_STATUS_DATE
                                      ,dcd.ds_cpo_dtl_upd_ts LAST_STATUS_TIME
                                      ,NULL INTERFACE_FLAG
                                      ,NULL INTERFACE_DATE
                                      ,NULL INTERFACE_TIME
                                      ,dcdv.ser_num SERIAL_NUMBER
                                      ,NULL INSTALL_A_REP_ID
                                      ,NULL GENERATED_DELIVERY_NUMBER
                                      ,NULL DELIVERY_ITEM
                                      ,NULL ACTUAL_GOODS_MOVEMENT_DATE
                                      ,NULL BILLING_DOCUMENT
                                      ,NULL BILLING_DOCUMENT_ITEM
                                      ,NULL SCHD_LN_BLOCKED_FOR_DELV
                                      ,NULL BILLING_BLOCK_FOR_ITEM
                                      ,NULL ORDER_IS_GEN_INCOMPLETE
                                      ,NULL ORDER_LINE_DELETE_STATUS
                                      ,NULL TOTAL_PRICE
                                      ,NULL OMSI_CONFIGURATION_NUMBER
                                      ,NULL START_TIME_OF_TRANSACTION
                                      ,NULL START_TIME_ZONE_OF_TRANSACTION
                                      ,NULL ORIGINAL_SCHEDULE_LINE_DATE
                                      ,NULL SCHEDULE_LINE_DATE
                                  FROM ds_cpo_dtl dcd
                                       ,shpg_pln sp
                                       ,shpg_ord_dtl sod
                                       ,ds_ord_sts_v dosv
                                       ,ds_shpg_ord_dtl dsod
                                       ,ds_cpo_sls_cr dcsc
                                       ,line_biz_role_tp lbrt
                                       ,ds_cpo_sls_cr dcscW
                                       ,org_func_asg ofa
                                       ,s21_psn p
                                       ,org_func_asg ofaW
                                       ,s21_psn pW
                                       ,line_biz_role_tp lbrtW
                                       ,svc_config_mstr scm
                                       ,ds_mdl_grp dmg
                                       ,ds_cpo_v dcv
                                       ,ds_cpo_dtl_v dcdv
                                        ,sell_to_cust stc
                                        ,acct_loc al
                                        ,ds_acct_cust dac
                                 WHERE dcd.ezcancelflag = '0'
                                   AND dcd.glbl_cmpy_cd = 'ADB'
                                   AND sp.ezcancelflag(+) = '0'
                                   AND sp.glbl_cmpy_cd(+) = 'ADB'
                                   AND sod.ezcancelflag(+) = '0'
                                   AND sod.glbl_cmpy_cd(+) = 'ADB'
                                   AND dosv.ezcancelflag(+) = '0'
                                   AND dosv.glbl_cmpy_cd(+) = 'ADB'
                                   AND dsod.ezcancelflag(+) = '0'
                                   AND dsod.glbl_cmpy_cd(+) = 'ADB'
                                   AND dcsc.ezcancelflag(+) = '0'
                                   AND dcsc.glbl_cmpy_cd(+) = 'ADB'
                                   AND ofa.ezcancelflag(+) = '0'
                                   AND ofa.glbl_cmpy_cd(+) = 'ADB'
                                   AND p.ezcancelflag(+) = '0'
                                   AND p.glbl_cmpy_cd(+) = 'ADB'
                                   AND scm.ezcancelflag(+) = '0'
                                   AND scm.glbl_cmpy_cd(+) = 'ADB'
                                   AND dmg.ezcancelflag(+) = '0'
                                   AND dmg.glbl_cmpy_cd(+) = 'ADB'
                                   AND dcv.ezcancelflag = '0'
                                   AND dcv.glbl_cmpy_cd = 'ADB'
                                   AND dcdv.ezcancelflag = '0'
                                   AND dcdv.glbl_cmpy_cd = 'ADB'
                                   AND dcscW.ezcancelflag(+) = '0'
                                   AND dcscW.glbl_cmpy_cd(+) = 'ADB'
                                   AND ofaW.ezcancelflag(+) = '0'
                                   AND ofaW.glbl_cmpy_cd(+) = 'ADB'
                                   AND pW.ezcancelflag(+) = '0'
                                   AND pW.glbl_cmpy_cd(+) = 'ADB'
                                   AND stc.ezcancelflag = '0'
                                   AND stc.glbl_cmpy_cd = 'ADB'
                                   AND al.ezcancelflag = '0'
                                   AND al.glbl_cmpy_cd = 'ADB'
                                   AND dac.ezcancelflag = '0'
                                   AND dac.glbl_cmpy_cd = 'ADB'
                                   AND lbrt.ezcancelflag(+) = '0'
                                   AND lbrt.glbl_cmpy_cd(+) = 'ADB'
                                   AND lbrtW.ezcancelflag(+) = '0'
                                   AND lbrtW.glbl_cmpy_cd(+) = 'ADB'
                                   AND dcd.cpo_ord_num = sod.trx_hdr_num(+)
                                   AND dcd.cpo_dtl_line_num = sod.trx_line_num(+)
                                    AND dcd.cpo_dtl_line_sub_num = sod.trx_line_sub_num(+)
                                    AND sod.so_num  = sp.so_num(+)
                                    AND sod.so_slp_num = sp.so_slp_num(+)
                                    AND dcd.cpo_ord_num = dosv.cpo_ord_num(+)
                                    AND dcd.cpo_dtl_line_num = dosv.cpo_dtl_line_num(+)
                                    AND dcd.cpo_dtl_line_sub_num = dosv.cpo_dtl_line_sub_num(+)
                                    AND lower(dosv.ord_line_sts_nm(+)) NOT LIKE ('cancelled')
                                    AND sod.so_num = dsod.so_num(+)
                                    AND sod.so_slp_num = dsod.so_slp_num(+)
                                    AND dcd.cpo_ord_num = dcsc.cpo_ord_num(+)
                                    AND dcsc.sls_rep_role_tp_cd = lbrt.line_biz_role_tp_cd(+)
                                    AND lbrt.sls_cr_quot_flg(+)='Y'
                                    AND lbrt.prim_rep_role_flg(+)='N' --Installer
                                    AND dcsc.sls_rep_toc_cd = ofa.toc_cd(+)
                                    AND ofa.psn_cd = p.psn_cd(+)
                                    AND dcd.svc_config_mstr_pk = scm.svc_config_mstr_pk(+)
                                    AND scm.mdl_id = dmg.mdl_grp_id(+)
                                    AND dcd.cpo_ord_num = dcv.cpo_ord_num
--                                    AND dcv.line_biz_tp_cd IN ('LFS','PPS')
                                    AND dcd.cpo_ord_num = dcdv.cpo_ord_num
                                    AND dcd.cpo_dtl_line_num = dcdv.cpo_dtl_line_num
                                    AND dcd.cpo_dtl_line_sub_num = dcdv.cpo_dtl_line_sub_num
                                    AND dcd.cpo_ord_num = dcscW.cpo_ord_num
                                    AND dcscW.sls_rep_role_tp_cd = lbrtW.line_biz_role_tp_cd(+)
                                    AND lbrtW.sls_cr_quot_flg(+) = 'Y'
                                    AND lbrtW.prim_rep_role_flg(+) = 'Y' -- writer
                                    AND dcscW.sls_rep_toc_cd = ofaW.toc_cd(+)
                                    AND ofaW.psn_cd = pW.psn_cd(+)
                                    AND dcv.sell_to_cust_cd = stc.sell_to_cust_cd
                                    AND stc.loc_num = al.loc_num
                                    AND al.ds_acct_num = dac.ds_acct_num
--                                    AND dcv.cpo_orD_num = '20001432'
                                    order by dcd.cpo_ord_num || dcd.cpo_dtl_line_num || dcd.cpo_dtl_line_sub_num
                          ) s21
           ON (stg.unique_key = s21.unique_key)
          WHEN MATCHED THEN UPDATE SET
                stg.CLIENT = s21.CLIENT
                ,stg.SALES_DOCUMENT = s21.sales_document
                  ,stg.sales_document_item = s21.SALES_DOCUMENT_ITEM
                  ,stg.CREATION_DATE = s21.CREATION_DATE
                  ,stg.ENTRY_TIME = s21.ENTRY_TIME
                  ,stg.CREATION_NAME = s21.CREATION_NAME
                  ,stg.MATERIAL_NUMBER = s21.MATERIAL_NUMBER
                  ,stg.PRODUCT_HIERARCHY = s21.PRODUCT_HIERARCHY
                  ,stg.MODEL_SERIES = s21.MODEL_SERIES
                  ,stg.CUMULATIVE_ORDER_QUANTITY = s21.CUMULATIVE_ORDER_QUANTITY
                  ,stg.CURRENT_LINE_STATUS  = s21.CURRENT_LINE_STATUS
                  ,stg.LAST_STATUS_DATE = s21.LAST_STATUS_DATE
                  ,stg.LAST_STATUS_TIME = s21.LAST_STATUS_TIME
                  ,stg.INTERFACE_FLAG = s21.INTERFACE_FLAG
                  ,stg.INTERFACE_DATE = s21.INTERFACE_DATE
                  ,stg.INTERFACE_TIME  = s21.INTERFACE_TIME
                  ,stg.SERIAL_NUMBER = s21.SERIAL_NUMBER
                  ,stg.BILL_OF_LADING  = s21.BILL_OF_LADING
                  ,stg.CARRIER_FROM = s21.CARRIER_FROM
                  ,stg.SALES_REP_ID = s21.SALES_REP_ID
                  ,stg.SALES_REP_NAME = s21.SALES_REP_NAME
                  ,stg.INSTALL_A_REP_ID = s21.INSTALL_A_REP_ID
                  ,stg.INSTALL_A_REP_NAME = s21.INSTALL_A_REP_NAME
                  ,stg.SALES_OFFICE = s21.SALES_OFFICE
                  ,stg.SALES_GROUP = s21.SALES_GROUP
                  ,stg.SALES_DISTRICT = s21.SALES_DISTRICT
                  ,stg.GENERATED_DELIVERY_NUMBER = s21.GENERATED_DELIVERY_NUMBER
                  ,stg.DELIVERY_ITEM = s21.DELIVERY_ITEM
                  ,stg.PLANNED_GOODS_MOVEMENT_DATE  = s21.PLANNED_GOODS_MOVEMENT_DATE
                  ,stg.ACTUAL_GOODS_MOVEMENT_DATE = s21.ACTUAL_GOODS_MOVEMENT_DATE
                  ,stg.ACTUAL_SHIP_DATE  = s21.ACTUAL_SHIP_DATE
                  ,stg.BILLING_DOCUMENT = s21.BILLING_DOCUMENT
                  ,stg.BILLING_DOCUMENT_ITEM = s21.BILLING_DOCUMENT_ITEM
                  ,stg.SCHD_LN_BLOCKED_FOR_DELV = s21.SCHD_LN_BLOCKED_FOR_DELV
                  ,stg.BILLING_BLOCK_FOR_ITEM  = s21.BILLING_BLOCK_FOR_ITEM
                  ,stg.REASON_FOR_REJECTION  = s21.REASON_FOR_REJECTION
                  ,stg.ORDER_IS_GEN_INCOMPLETE = s21.ORDER_IS_GEN_INCOMPLETE
                  ,stg.OVERALL_CREDIT_STATUS = s21.OVERALL_CREDIT_STATUS
                  ,stg.NET_PRICE = s21.NET_PRICE
                  ,stg.ORDER_LINE_DELETE_STATUS = s21.ORDER_LINE_DELETE_STATUS
                  ,stg.TOTAL_PRICE  = s21.TOTAL_PRICE
                  ,stg.DELIVERY_DATE = s21.DELIVERY_DATE
                  ,stg.OMSI_CONFIGURATION_NUMBER = s21.OMSI_CONFIGURATION_NUMBER
                  ,stg.START_TIME_OF_TRANSACTION = s21.START_TIME_OF_TRANSACTION
                  ,stg.START_TIME_ZONE_OF_TRANSACTION  = s21.START_TIME_ZONE_OF_TRANSACTION
                  ,stg.ALIAS_FOR_THE_TRANSACTION  = s21.ALIAS_FOR_THE_TRANSACTION
                  ,stg.ORIGINAL_SCHEDULE_LINE_DATE = s21.ORIGINAL_SCHEDULE_LINE_DATE
                  ,stg.DELIVERY_PRIORITY  = s21.DELIVERY_PRIORITY
                  ,stg.SHIP_TO_PARTY  = s21.SHIP_TO_PARTY
                  ,stg.NAME = s21.NAME
                  ,stg.SCHEDULE_LINE_DATE = s21.SCHEDULE_LINE_DATE
                  ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_ord_detail_id IS NULL THEN 'I' ELSE 'U' END)
                  ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_ord_detail_id IS NULL THEN 'I' ELSE 'U' END)
                  ,stg.lfs_sf_status_message = NULL
                  ,stg.pps_sf_status_message = NULL
                  ,stg.lfs_last_update_date = SYSDATE
                  ,stg.pps_last_update_date = SYSDATE
--                  ,unique_key = trim(ext.sales_document) || trim(ext.sales_document_item)
            WHEN NOT MATCHED THEN INSERT(CLIENT
                  ,SALES_DOCUMENT
                  ,SALES_DOCUMENT_ITEM
                  ,CREATION_DATE
                  ,ENTRY_TIME
                  ,CREATION_NAME
                  ,MATERIAL_NUMBER
                  ,PRODUCT_HIERARCHY
                  ,MODEL_SERIES
                  ,CUMULATIVE_ORDER_QUANTITY
                  ,CURRENT_LINE_STATUS
                  ,LAST_STATUS_DATE
                  ,LAST_STATUS_TIME
                  ,INTERFACE_FLAG
                  ,INTERFACE_DATE
                  ,INTERFACE_TIME
                  ,SERIAL_NUMBER
                  ,BILL_OF_LADING
                  ,CARRIER_FROM
                  ,SALES_REP_ID
                  ,SALES_REP_NAME
                  ,INSTALL_A_REP_ID
                  ,INSTALL_A_REP_NAME
                  ,SALES_OFFICE
                  ,SALES_GROUP
                  ,SALES_DISTRICT
                  ,GENERATED_DELIVERY_NUMBER
                  ,DELIVERY_ITEM
                  ,PLANNED_GOODS_MOVEMENT_DATE
                  ,ACTUAL_GOODS_MOVEMENT_DATE
                  ,ACTUAL_SHIP_DATE
                  ,BILLING_DOCUMENT
                  ,BILLING_DOCUMENT_ITEM
                  ,SCHD_LN_BLOCKED_FOR_DELV
                  ,BILLING_BLOCK_FOR_ITEM
                  ,REASON_FOR_REJECTION
                  ,ORDER_IS_GEN_INCOMPLETE
                  ,OVERALL_CREDIT_STATUS
                  ,NET_PRICE
                  ,ORDER_LINE_DELETE_STATUS
                  ,TOTAL_PRICE
                  ,DELIVERY_DATE
                  ,OMSI_CONFIGURATION_NUMBER
                  ,START_TIME_OF_TRANSACTION
                  ,START_TIME_ZONE_OF_TRANSACTION
                  ,ALIAS_FOR_THE_TRANSACTION
                  ,ORIGINAL_SCHEDULE_LINE_DATE
                  ,DELIVERY_PRIORITY
                  ,SHIP_TO_PARTY
                  ,NAME
                  ,SCHEDULE_LINE_DATE
                  ,lfs_sf_status_flag
                  ,pps_sf_status_flag
                  ,lfs_sf_status_message
                  ,pps_sf_status_message
                  ,lfs_last_update_date
                  ,pps_last_update_date
                  ,unique_key) VALUES (s21.CLIENT
                                                  ,s21.SALES_DOCUMENT
                                                  ,s21.SALES_DOCUMENT_ITEM
                                                  ,s21.CREATION_DATE
                                                  ,s21.ENTRY_TIME
                                                  ,s21.CREATION_NAME
                                                  ,s21.MATERIAL_NUMBER
                                                  ,s21.PRODUCT_HIERARCHY
                                                  ,s21.MODEL_SERIES
                                                  ,s21.CUMULATIVE_ORDER_QUANTITY
                                                  ,s21.CURRENT_LINE_STATUS
                                                  ,s21.LAST_STATUS_DATE
                                                  ,s21.LAST_STATUS_TIME
                                                  ,s21.INTERFACE_FLAG
                                                  ,s21.INTERFACE_DATE
                                                  ,s21.INTERFACE_TIME
                                                  ,s21.SERIAL_NUMBER
                                                  ,s21.BILL_OF_LADING
                                                  ,s21.CARRIER_FROM
                                                  ,s21.SALES_REP_ID
                                                  ,s21.SALES_REP_NAME
                                                  ,s21.INSTALL_A_REP_ID
                                                  ,s21.INSTALL_A_REP_NAME
                                                  ,s21.SALES_OFFICE
                                                  ,s21.SALES_GROUP
                                                  ,s21.SALES_DISTRICT
                                                  ,s21.GENERATED_DELIVERY_NUMBER
                                                  ,s21.DELIVERY_ITEM
                                                  ,s21.PLANNED_GOODS_MOVEMENT_DATE
                                                  ,s21.ACTUAL_GOODS_MOVEMENT_DATE
                                                  ,s21.ACTUAL_SHIP_DATE
                                                  ,s21.BILLING_DOCUMENT
                                                  ,s21.BILLING_DOCUMENT_ITEM
                                                  ,s21.SCHD_LN_BLOCKED_FOR_DELV
                                                  ,s21.BILLING_BLOCK_FOR_ITEM
                                                  ,s21.REASON_FOR_REJECTION
                                                  ,s21.ORDER_IS_GEN_INCOMPLETE
                                                  ,s21.OVERALL_CREDIT_STATUS
                                                  ,s21.NET_PRICE
                                                  ,s21.ORDER_LINE_DELETE_STATUS
                                                  ,s21.TOTAL_PRICE
                                                  ,s21.DELIVERY_DATE
                                                  ,s21.OMSI_CONFIGURATION_NUMBER
                                                  ,s21.START_TIME_OF_TRANSACTION
                                                  ,s21.START_TIME_ZONE_OF_TRANSACTION
                                                  ,s21.ALIAS_FOR_THE_TRANSACTION
                                                  ,s21.ORIGINAL_SCHEDULE_LINE_DATE
                                                  ,s21.DELIVERY_PRIORITY
                                                  ,s21.SHIP_TO_PARTY
                                                  ,s21.NAME
                                                  ,s21.SCHEDULE_LINE_DATE
                                                  ,'I'
                                                  ,'I'
                                                  ,NULL
                                                  ,NULL
                                                  ,SYSDATE
                                                  ,SYSDATE
                                                  ,s21.unique_key);
--       fnd_file.put_line(fnd_file.log, sql%rowcount || ' merged into CANON_E633_ORDDTL_EXT_TBL');
         dbms_output.put_line(sql%rowcount || ' loaded into canon_e633_orddtl_stg_tbl');
**/

             update canon_e633_orddtl_stg_tbl
                    set lfs_sf_status_flag = 'E'
                        ,pps_sf_status_flag = 'E'
                        ,lfs_sf_status_message = 'Invalid Data Net Price.'
                        ,pps_sf_status_message = 'Invalid Data Net Price.'
                        ,lfs_last_update_date = SYSDATE
                        ,pps_last_update_date = SYSDATE
                  where trim(translate(net_price, '1234567890.-', '            ')) IS NOT NULL
                     AND (lfs_sf_status_flag IN ('I','U') OR
                          pps_sf_status_flag IN ('I','U'));
--            fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for invalid Data Net Price');
            dbms_output.put_line(sql%rowcount || ' updated for Invalid Data Net Price');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated for Invalid Data Net Price.',NULL,NULL,NULL,NULL);

            BEGIN
				UPDATE canon_s21_cd_val_tbl v
				   SET v.val76 = l_program_start_date
				 WHERE v.val1 = 'ORD_DETAIL'
				   AND v.cd_id = (SELECT cd_id
								   FROM canon_s21_cd_tbl
								   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');

          UPDATE canon_s21_cd_val_tbl v
				   SET v.val76 = l_program_start_date
				 WHERE v.val1 = 'ORD_RMA'
				   AND v.cd_id = (SELECT cd_id
								   FROM canon_s21_cd_tbl
								   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
            COMMIT;
			   EXCEPTION
				 WHEN OTHERS THEN
				   dbms_output.put_line('update ORD_DETAIL last run date exception: '|| SQLERRM);
				   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		    END;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    --x_return_status := 'E';
        --dbms_output.put_line('x_return_status:'||x_return_status);
        dbms_output.put_line(SQLERRM);
        retcode := '2';
        errbuff := SQLERRM;
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END LOAD_ORDER_DETAILS;

PROCEDURE update_dependIDs_4_ord(p_handler_name IN varchar2, x_return_status OUT VARCHAR2)
IS
    l_procedure_name varchar2(50) := 'update_dependIDs_4_ord';
    l_a_table varchar2(60) ;
    l_b_table varchar2(60) ;
    l_a_col varchar2(60) ;
    l_b_col varchar2(60) ;
    l_sql varchar2(32767) ;
BEGIN

    x_return_status := 'S';

    l_a_table := null;
    l_b_table := null ;
    l_a_col := null ;
    l_b_col := null;

    IF(p_handler_name LIKE '%OrderDetailUpload%') THEN
         l_a_table := 'CANON_E633_ORDDTL_STG_TBL a';
         l_b_table := 'CANON_E633_ORDHDR_STG_TBL b' ;
         l_a_col := 'a.sales_document';
         l_b_col := 'b.document_number';
    ELSIF(p_handler_name LIKE '%OrderCommentUpload%') THEN
         l_a_table := 'CANON_E633_ORDCMT_STG_TBL a';
         l_b_table := 'CANON_E633_ORDHDR_STG_TBL b' ;
         l_a_col := 'a.sales_document';
         l_b_col := 'b.document_number';
    END IF;

    dbms_output.put_line('p_handler_name:'||p_handler_name);
    dbms_output.put_line('l_a_table:'||l_a_table);
    dbms_output.put_line('l_a_col:'||l_a_col);
    dbms_output.put_line('l_b_table:'||l_a_table);
    dbms_output.put_line('l_b_col:'||l_a_col);


	IF(p_handler_name like '%LFS%') THEN  --QC51955

			--for LFS
      l_sql := 'MERGE INTO ' || l_a_table ||
                ' USING ' || l_b_table ||
                ' ON (' || l_a_col || ' = ' || l_b_col ||
                      ' AND a.lfs_sf_status_flag IN (''I'',''U'')) ' ||
                'WHEN MATCHED THEN UPDATE SET ' ||
                '  a.lfs_sf_order_id = b.lfs_sf_order_id ' ||
                ' ,a.LFSBU = b.lfsbu ' ||
                ' ,a.lfs_last_update_date = SYSDATE ' ;
     canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
        dbms_output.put_line('l_sql:'||l_sql);
       EXECUTE IMMEDIATE l_sql;
--       fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated LFS Order ID''s in ' || l_a_table || ' from ' || l_b_table);

		--update for DPS bu
        l_sql := 'UPDATE ' || l_a_table ||
                  ' SET a.lfs_sf_status_flag = NULL, a.lfs_sf_order_id = NULL ' ||
                  '    , a.lfs_sf_status_message = null ' ||
                  ' WHERE a.lfsbu IS NULL ' ||
                  '   AND a.lfs_sf_status_flag IN (''I'',''U'') ';
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
        dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;

		--update if sf_order_id is missing for lfs
        l_sql := 'UPDATE ' || l_a_table ||
                 ' SET a.lfs_sf_status_flag = ''E'' ' ||
                 '    ,a.lfs_sF_status_message = ''Unable to find Order LFS SFDC ID.'' ' ||
                 ' WHERE a.lfsbu IS NOT NULL ' ||
                 '   AND a.lfs_sf_order_id IS NULL ' ||
                 '   AND a.lfs_sf_status_flag IN (''I'',''U'')' ;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
        dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;
--        fnd_file.put_line(fnd_file.log, sql%rowcount || ' unable to find Order LFS SFDC ID');
		COMMIT;

	END IF;

     IF(p_handler_name like '%PPS%') THEN --QC51955
       --for PPS
       l_sql := 'MERGE INTO ' || l_a_table ||
                ' USING ' || l_b_table ||
                ' ON (' || l_a_col || ' = ' || l_b_col ||
                      ' AND a.pps_sf_status_flag IN (''I'',''U'')) ' ||
                'WHEN MATCHED THEN UPDATE SET ' ||
                ' a.pps_sf_order_id = b.pps_sf_order_id ' ||
--                ' ,a.pps_sf_account_id = b.pps_sf_account_id ' ||
                ' ,a.PPSBU = b.ppsbu ' ||
                ' ,a.pps_last_update_date = SYSDATE ';
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
       dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;
--        fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated PPS Order ID''s in ' || l_a_table || ' from ' || l_b_table);

        l_sql := 'UPDATE ' || l_a_table ||
                  ' SET a.pps_sf_order_id = NULL ' ||
                  '    ,a.pps_sf_status_flag = NULL, a.pps_sf_status_message = NULL ' ||
                  ' WHERE a.ppsbu IS NULL ' ||
                  '   AND a.pps_sf_status_flag IN (''I'',''U'') ' ;
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
        dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;

        --update if sf_order_id is missing for pps
        l_sql := 'UPDATE ' || l_a_table ||
                 ' SET a.pps_sF_status_flag = ''E'' ' ||
                 '    ,a.pps_sF_status_message = ''Unable to find Order PPS SFDC ID.'' ' ||
                 ' WHERE a.ppsbu IS NOT NULL ' ||
                 '   AND a.pps_sf_order_id IS NULL ' ||
                 '   AND a.pps_sf_status_flag IN (''I'',''U'') ' ;
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',l_sql,NULL,NULL,NULL,NULL);
       dbms_output.put_line('l_sql:'||l_sql);
        EXECUTE IMMEDIATE l_sql;
--        fnd_file.put_line(fnd_file.log, sql%rowcount || ' unable to find Order PPS SFDC ID');
        COMMIT;
	END IF;
        dbms_output.put_line('x_return_status:'||x_return_status);


EXCEPTION
    WHEN OTHERS THEN
        x_return_status := 'E';
        dbms_output.put_line('x_return_status:'||x_return_status);
        dbms_output.put_line(SQLERRM);
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END update_dependIDs_4_ord;

PROCEDURE load_order_comments(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
IS
    l_procedure_name varchar2(50) := 'load_order_comments';
    l_last_run_date DATE;
    l_program_start_date DATE := SYSDATE;
    l_last_run_date_num VARCHAR2(17);

BEGIN
        retcode := '0';

         BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 FROM canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'ORD_COMMENT';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    END;


   --QC58464 --start --to reprocess and unprocessed records
         UPDATE canon_e633_ordcmt_stg_tbl  stg
            SET lfs_sf_status_flag = 'I'
            WHERE lfs_sf_status_flag IS NULL
              AND lfs_sf_ord_comment_id IS NULL
              AND lfsbu IS NULL;
              DBMS_OUTPUT.PUT_LINE('LFS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || ' LFS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464

            --QC58464 --start
         UPDATE canon_e633_ordcmt_stg_tbl  stg
            SET pps_sf_status_flag =  'I'
            WHERE  pps_sf_status_flag IS NULL
              AND pps_sf_ord_comment_id IS NULL
              AND ppsbu IS NULL;
               DBMS_OUTPUT.PUT_LINE('PPS unprocessed records to I : ' ||sql%rowcount);
             canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','updated '|| sql%rowcount || 'PPS unprocessed records in canon_e633_closecalls_stg_tbl' ,NULL,NULL,NULL,NULL );
          COMMIT;
           --end  -- QC58464
    --to reprocess error and unprocessed records
		UPDATE canon_e633_ordcmt_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_ord_comment_id IS NOT NULL THEN 'U' ELSE 'I' END
          WHERE lfs_sf_status_flag = 'E';
--           fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U / I : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of LFS error records updated to U / I : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

          UPDATE canon_e633_ordcmt_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_ord_comment_id IS NOT NULL THEN 'U' ELSE 'I' END
          WHERE pps_sf_status_flag = 'E' ;
--          fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U / I : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U / I : ' ||sql%rowcount,NULL,NULL,NULL,NULL);
         COMMIT;



         MERGE INTO canon_e633_ordcmt_stg_tbl stg
           USING (SELECT ad.att_data_pk unique_key
                       ,ad.ezinuserid created_by
                       ,ad.ezintime creation_date
--                     ,TO_CHAR(SUBSTR(ad.att_data_cmnt_clod,0,3999)) text
                       ,to_char(substr(regexp_replace(ad.att_data_cmnt_clod, '[^ -~|[:space:]]', ' '),1,3500))text --QC54825
--                       ,ad.att_data_cmnt_clod text
                       ,ad.att_data_grp_txt sales_document
                       ,'000001' sequence_number
                       ,NULL client
                       ,NULL INTERFACE_FLAG
                       ,NULL INTERFACE_DATE
                       ,NULL INTERFACE_TIME
                       ,NULL entry_time
                   FROM att_data ad
                        ,cpo_v dcv -- DB Change
                   WHERE ad.att_data_tp_cd = 'NT'
                     AND ad.ezbusinessid='NWAL1500'
                     AND ad.glbl_cmpy_cd = 'ADB'
                     AND ad.ezcancelflag = '0'
                     AND dcv.ezcancelflag = '0'
                     AND dcv.glbl_cmpy_cd = 'ADB'
                     AND dcv.cpo_ord_num = ad.att_data_grp_txt
                     AND dcv.line_biz_tp_cd IN ('LFS','PPS')
--                     AND TO_NUMBER(ad.ezuptime) >= TO_NUMBER(l_last_run_date_num)
					 AND CAST(TO_TIMESTAMP (ad.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) >= l_last_run_date) s21
         ON(stg.unique_key = s21.unique_key )
         WHEN MATCHED THEN UPDATE SET
                    stg.CLIENT = s21.CLIENT
                  ,stg.INTERFACE_FLAG = s21.INTERFACE_FLAG
                  ,stg.INTERFACE_DATE = s21.INTERFACE_DATE
                  ,stg.INTERFACE_TIME = s21. INTERFACE_TIME
                  ,stg.TEXT = s21.TEXT
                  ,stg.CREATION_DATE = s21.CREATION_DATE
                  ,stg.ENTRY_TIME = s21.ENTRY_TIME
                  ,stg.CREATED_BY = s21.CREATED_BY
                  ,stg.sales_document = s21.sales_document
                  --,stg.sequence_number = s21.sequence_number
--                  ,stg.UNIQUE_KEY = trim(ext.sales_document) || trim(ext.sequence_number)
                  ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_ord_comment_id IS NULL THEN 'I' ELSE 'U' END)
                  ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_ord_comment_id IS NULL THEN 'I' ELSE 'U' END)
                  ,stg.lfs_sf_status_message = NULL
                  ,stg.pps_sf_status_message = NULL
                  ,stg.lfs_last_update_date = SYSDATE
                  ,stg.pps_last_update_date = SYSDATE
         WHEN NOT MATCHED THEN INSERT (CLIENT
                                      ,SALES_DOCUMENT
                                      ,SEQUENCE_NUMBER
                                      ,INTERFACE_FLAG
                                      ,INTERFACE_DATE
                                      ,INTERFACE_TIME
                                      ,TEXT
                                      ,CREATION_DATE
                                      ,ENTRY_TIME
                                      ,CREATED_BY
                                      ,UNIQUE_KEY
                                      ,lfs_sf_status_flag
                                      ,pps_sf_status_flag
                                      ,lfs_sf_status_message
                                      ,pps_sf_status_message
                                      ,lfs_last_update_date
                                      ,pps_last_update_date
									  ,att_data_pk) VALUES(s21.CLIENT
                                                                  ,s21.SALES_DOCUMENT
                                                                  ,s21.SEQUENCE_NUMBER
                                                                  ,s21.INTERFACE_FLAG
                                                                  ,s21.INTERFACE_DATE
                                                                  ,s21.INTERFACE_TIME
                                                                  ,s21.TEXT
                                                                  ,s21.CREATION_DATE
                                                                  ,s21.ENTRY_TIME
                                                                  ,s21.CREATED_BY
                                                                  ,s21.unique_key || s21.SEQUENCE_NUMBER
                                                                  ,'I'
                                                                  ,'I'
                                                                  ,NULL
                                                                  ,NULL
                                                                  ,SYSDATE
                                                                  ,SYSDATE
																  ,s21.unique_key);
--             fnd_file.put_line(fnd_file.log, sql%rowcount || ' inserted into CANON_E633_ORDCMT_STG_TBL.');
                dbms_output.put_line(sql%rowcount || ' loaded into canon_e633_ordcmt_stg_tbl');
				canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' loaded into canon_e633_ordcmt_stg_tbl',NULL,NULL,NULL,NULL);

           --validation checks
           UPDATE canon_e633_ordcmt_stg_tbl
              SET lfs_sf_status_flag = 'E'
                  ,pps_sf_status_flag = 'E'
                  ,lfs_sf_status_message = 'No comment found.'
                  ,pps_sf_status_message = 'No comment found.'
                  ,lfs_last_update_date = SYSDATE
                  ,pps_last_update_date = SYSDATE
              WHERE (lfs_sf_status_flag IN ('I','U') OR
                      pps_sf_status_flag IN ('I','U'))
                AND text IS NULL;
--           fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for No comment found.');
            dbms_output.put_line(sql%rowcount || ' updated for No Comment Found');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',sql%rowcount || ' updated for No Comment Found',NULL,NULL,NULL,NULL);

            BEGIN
				UPDATE canon_s21_cd_val_tbl v
				   SET v.val76 = l_program_start_date
				 WHERE v.val1 = 'ORD_COMMENT'
				   AND v.cd_id = (SELECT cd_id
								   FROM canon_s21_cd_tbl
								   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
			   EXCEPTION
				 WHEN OTHERS THEN
				   dbms_output.put_line('update ORD_COMMENT last run date exception: '|| SQLERRM);
				   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		    END;
		    dbms_output.put_line(sql%rowcount || ' updated last rundate');
           COMMIT;

EXCEPTION
WHEN OTHERS THEN
    --x_return_status := 'E';
        --dbms_output.put_line('x_return_status:'||x_return_status);
        dbms_output.put_line(SQLERRM);
        retcode := '2';
        errbuff := SQLERRM;
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END load_order_comments;


PROCEDURE load_sales_comp(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
IS
    l_procedure_name varchar2(50) := 'load_sales_comp';
    l_last_run_date DATE;
    l_program_start_date date := sysdate;
    l_last_run_date_num varchar2(17);

BEGIN
    retcode := '0';

    BEGIN
	   SELECT last_run_dt
		 INTO l_last_run_date
		 from canon_e633_incrdt_tracker_v
		WHERE extract_prg = 'SALES_COMP';
	   IF l_last_run_date IS NULL THEN
		 l_last_run_date := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
	   END IF;
	   l_last_run_date_num := TO_CHAR(l_last_run_date, 'RRRRMMDDHH24MISS');
    end;
    dbms_output.put_line(l_last_run_date_num || ' --> last rundate');
    canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','last rundate: ' ||l_last_run_date_num ,null,null,null,null);

    --to reprocess error and unprocessed records
		UPDATE canon_e633_sales_comp_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_comp_id IS NOT NULL THEN 'U' ELSE 'I' END
               ,lfs_sf_status_message = CASE WHEN lfs_sf_comp_id IS NOT NULL THEN lfs_sf_status_message ELSE NULL END
          where lfs_sf_status_flag = 'E';
          canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. of LFS error records updated to U / I : ' ||sql%rowcount ,null,null,null,null);
--          fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U / I : ' ||sql%rowcount);

          UPDATE canon_e633_sales_comp_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_comp_id IS NOT NULL THEN 'U' ELSE 'I' END
               ,pps_sf_status_message = CASE WHEN pps_sf_comp_id IS NOT NULL THEN pps_sf_status_message ELSE NULL END
          where pps_sf_status_flag = 'E' ;
          canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. of PPS error records updated to U / I : ' ||sql%rowcount ,null,null,null,null);
--          fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U / I : ' ||sql%rowcount);

         COMMIT;

         MERGE INTO canon_e633_sales_comp_stg_tbl stg
           USING (SELECT crm_sls_per_nm period_name
                          ,crm_sls_den_lvl_cd denorm_level
                          ,crm_sls_den_lvl_desc_txt denorm_level_desc
                          ,crm_sls_immd_prnt_flg immediate_parent_flag
                          ,crm_sls_actl_prnt_id actual_parent_id
                          ,crm_sls_actl_prnt_grp_nm actual_parent_group_name
                          ,crm_sls_grp_id group_id
                          ,crm_sls_grp_nm group_name
                          ,crm_sls_sls_rep_id sales_rep_id
                          ,crm_sls_resrc_id resource_id
                          ,crm_sls_sls_rep_nm salesrep_name
                          ,crm_sls_actl_prnt_rep_id actual_parent_salesrep_id
                          ,crm_sls_actl_prnt_resrc_id actual_parent_resource_id
                          ,crm_sls_actl_prnt_rep_nm actual_parent_salesrep_name
                          ,crm_sls_per_num period_num
                          ,crm_sls_qtr_num quarter_num
                          ,CRM_SLS_ABS_AMT revenue_amount
                          ,crm_sls_quot_amt quota_amount
                          ,crm_sls_tot_unit_sold_qty total_units_sold
                          ,crm_sls_biz_unit_tp_cd businessunit
                     from nmai8210_01
                     where ezuptime > l_last_run_date_num
                    UNION
                   SELECT crm_sls_per_nm period_name
                          ,crm_sls_den_lvl_cd denorm_level
                          ,crm_sls_den_lvl_desc_txt denorm_level_desc
                          ,crm_sls_immd_prnt_flg immediate_parent_flag
                          ,crm_sls_actl_prnt_id actual_parent_id
                          ,crm_sls_actl_prnt_grp_nm actual_parent_group_name
                          ,crm_sls_grp_id group_id
                          ,crm_sls_grp_nm group_name
                          ,crm_sls_sls_rep_id sales_rep_id
                          ,crm_sls_resrc_id resource_id
                          ,crm_sls_sls_rep_nm salesrep_name
                          ,crm_sls_actl_prnt_rep_id actual_parent_salesrep_id
                          ,crm_sls_actl_prnt_resrc_id actual_parent_resource_id
                          ,crm_sls_actl_prnt_rep_nm actual_parent_salesrep_name
                          ,crm_sls_per_num period_num
                          ,crm_sls_qtr_num quarter_num
                          ,CRM_SLS_REV_AMT revenue_amount
                          ,crm_sls_quot_amt quota_amount
                          ,crm_sls_tot_unit_sold_qty total_units_sold
                          ,crm_sls_biz_unit_tp_cd businessunit
                        from nmai8220_01
                        where ezuptime > l_last_run_date_num
                        ) ext
                ON (stg.period_num = trim(ext.period_num) AND
                     stg.resource_id = trim(ext.resource_id) )
                WHEN MATCHED THEN UPDATE SET
                        stg.PERIOD_NAME = trim(ext.PERIOD_NAME)
                          ,stg.DENORM_LEVEL = trim(ext.DENORM_LEVEL)
                          ,stg.DENORM_LEVEL_DESC = trim(ext.DENORM_LEVEL_DESC)
                          ,stg.IMMEDIATE_PARENT_FLAG = trim(ext.IMMEDIATE_PARENT_FLAG)
                          ,stg.ACTUAL_PARENT_ID = trim(ext.ACTUAL_PARENT_ID)
                          ,stg.ACTUAL_PARENT_GROUP_NAME = trim(ext.ACTUAL_PARENT_GROUP_NAME)
                          ,stg.GROUP_ID = trim(ext.GROUP_ID)
                          ,stg.GROUP_NAME = trim(ext.GROUP_NAME)
                          ,stg.sales_rep_id = trim(ext.sales_rep_id)
                          --,stg.RESOURCE_ID = trim(ext.RESOURCE_ID)
                          ,stg.SALESREP_NAME = trim(ext.SALESREP_NAME)
                          ,stg.ACTUAL_PARENT_SALESREP_ID = trim(ext.ACTUAL_PARENT_SALESREP_ID)
                          ,stg.ACTUAL_PARENT_RESOURCE_ID = trim(ext.ACTUAL_PARENT_RESOURCE_ID)
                          ,stg.ACTUAL_PARENT_SALESREP_NAME = trim(ext.ACTUAL_PARENT_SALESREP_NAME)
                          ,stg.QUARTER_NUM = trim(ext.QUARTER_NUM)
                          ,stg.REVENUE_AMOUNT = trim(ext.REVENUE_AMOUNT)
                          ,stg.QUOTA_AMOUNT = trim(ext.QUOTA_AMOUNT)
                          ,stg.TOTAL_UNITS_SOLD = trim(ext.TOTAL_UNITS_SOLD)
                          ,stg.BUSINESSUNIT = trim(ext.BUSINESSUNIT)
                          ,stg.lfs_sf_status_flag = (CASE WHEN lfs_sf_comp_id IS NULL THEN 'I' ELSE 'U' END)
                          ,stg.pps_sf_status_flag = (CASE WHEN pps_sf_comp_id IS NULL THEN 'I' ELSE 'U' END)
                          ,stg.lfs_sf_status_message = NULL
                          ,stg.pps_sf_status_message = NULL
                          ,stg.lfs_last_update_date = SYSDATE
                          ,stg.pps_last_update_date = sysdate
                          ,stg.unique_key = trim(ext.sales_rep_id) || trim(ext.period_num)
                   WHEN NOT MATCHED THEN INSERT(PERIOD_NAME
                                                  ,DENORM_LEVEL
                                                  ,DENORM_LEVEL_DESC
                                                  ,IMMEDIATE_PARENT_FLAG
                                                  ,ACTUAL_PARENT_ID
                                                  ,ACTUAL_PARENT_GROUP_NAME
                                                  ,GROUP_ID
                                                  ,GROUP_NAME
                                                  ,SALES_REP_ID
                                                  ,RESOURCE_ID
                                                  ,SALESREP_NAME
                                                  ,ACTUAL_PARENT_SALESREP_ID
                                                  ,ACTUAL_PARENT_RESOURCE_ID
                                                  ,ACTUAL_PARENT_SALESREP_NAME
                                                  ,PERIOD_NUM
                                                  ,QUARTER_NUM
                                                  ,REVENUE_AMOUNT
                                                  ,QUOTA_AMOUNT
                                                  ,TOTAL_UNITS_SOLD
                                                  ,BUSINESSUNIT
                                                  ,unique_key
                                                  ,lfs_sf_status_flag
                                                  ,pps_sf_status_flag
                                                  ,lfs_sf_status_message
                                                  ,pps_sf_status_message
                                                  ,lfs_last_update_date
                                                  ,pps_last_update_date
                                                  ,lfsbu
                                                  ,ppsbu
                                                  ,dpsbu) VALUES (trim(ext.PERIOD_NAME)
                                                                          ,trim(ext.DENORM_LEVEL)
                                                                          ,trim(ext.DENORM_LEVEL_DESC)
                                                                          ,trim(ext.IMMEDIATE_PARENT_FLAG)
                                                                          ,trim(ext.ACTUAL_PARENT_ID)
                                                                          ,trim(ext.ACTUAL_PARENT_GROUP_NAME)
                                                                          ,trim(ext.GROUP_ID)
                                                                          ,trim(ext.GROUP_NAME)
                                                                          ,trim(ext.SALES_REP_ID)
                                                                          ,trim(ext.RESOURCE_ID)
                                                                          ,trim(ext.SALESREP_NAME)
                                                                          ,trim(ext.ACTUAL_PARENT_SALESREP_ID)
                                                                          ,trim(ext.ACTUAL_PARENT_RESOURCE_ID)
                                                                          ,trim(ext.ACTUAL_PARENT_SALESREP_NAME)
                                                                          ,trim(ext.PERIOD_NUM)
                                                                          ,trim(ext.QUARTER_NUM)
                                                                          ,trim(ext.REVENUE_AMOUNT)
                                                                          ,trim(ext.QUOTA_AMOUNT)
                                                                          ,trim(ext.TOTAL_UNITS_SOLD)
                                                                          ,trim(ext.businessunit)
                                                                          ,trim(ext.sales_rep_id) || trim(ext.period_num)
                                                                          ,(case WHEN trim(ext.businessunit) IN('DG','TDS') THEN 'I' ELSE NULL END)
                                                                          ,(case WHEN trim(ext.businessunit) IN('PPS') THEN 'I' ELSE NULL END)
                                                                          ,NULL
                                                                          ,NULL
                                                                          ,sysdate
                                                                          ,sysdate
                                                                          ,(case WHEN trim(ext.businessunit) IN('DG','TDS') THEN 'LFS' ELSE NULL END)
                                                                          ,(case trim(ext.businessunit) WHEN 'PPS' THEN 'PPS' ELSE NULL END)
                                                                          ,(case trim(ext.businessunit) WHEN 'DPS' THEN 'DPS' ELSE NULL END) );
--                fnd_file.put_line(fnd_file.log, sql%rowcount || ' merged into CANON_E633_SALES_COMP_STG_TBL from');
                canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. of records merged into CANON_E633_SALES_COMP_STG_TBL : ' ||sql%rowcount ,null,null,null,null);
                COMMIT;

                --validation checks
                update canon_e633_sales_comp_stg_tbl
                    set lfs_sf_status_flag = 'E'
                        ,pps_sf_status_flag = 'E'
                        ,lfs_sf_status_message = 'Invalid Data Revenue_amount/quota_amount/total_units_sold'
                        ,pps_sf_status_message = 'Invalid Data Revenue_amount/quota_amount/total_units_sold'
                  where (trim(translate(REVENUE_AMOUNT, '1234567890.', '           ')) IS NOT NULL OR
                         trim(translate(QUOTA_AMOUNT, '1234567890.', '           ')) IS NOT NULL OR
                         trim(translate(total_units_sold, '1234567890.', '           ')) IS NOT NULL)
                     AND (lfs_sf_status_flag IN ('I','U') OR
                          pps_sf_status_flag in ('I','U'));
                 canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. of invalid Revenue_amount/quota_amount/total_units_sold : ' ||sql%rowcount ,null,null,null,null);
--               fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for invalid Revenue_amount/quota_amount/total_units_sold');
                commit;

                --update the sf_user_id
                MERGE INTO canon_e633_sales_comp_stg_tbl a
                  USING CANON_E633_LFS_USER_DWLD_TBL b
                   ON (a.sales_rep_id = b.csa_employee_number AND
                        a.lfs_sf_status_flag IN ('I','U') AND
                        a.businessunit IN ('DG','TDS'))
                   WHEN MATCHED THEN UPDATE SET
                        lfs_sf_user_id = b.sf_user_id;
                  canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. updated for LFS sales rep SF user id : ' ||sql%rowcount ,null,null,null,null);
--                 fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for LFS sales rep SF user id');

                MERGE INTO canon_e633_sales_comp_stg_tbl a
                    USING canon_e633_lfs_user_dwld_tbl b
                    ON (a.ACTUAL_PARENT_SALESREP_ID = b.csa_employee_number AND
                        a.lfs_sf_status_flag IN ('I','U') AND
                        a.businessunit IN ('DG','TDS'))
                 WHEN MATCHED THEN UPDATE SET
                        lfs_sf_parent_user_id = b.sf_user_id;
                canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. updated for LFS parent sales rep SF user id : ' ||sql%rowcount ,null,null,null,null);
--                 fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for LFS parent sales rep SF user id');

                 MERGE INTO canon_e633_sales_comp_stg_tbl a
                  USING CANON_E633_PPS_USER_DWLD_TBL b
                   ON (a.sales_rep_id = b.csa_employee_number AND
                        a.pps_sf_status_flag IN ('I','U') AND
                        a.businessunit = 'PPS')
                   WHEN MATCHED THEN UPDATE SET
                        pps_sf_user_id = b.sf_user_id;
                   canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. updated for PPS sales rep SF user id : ' ||sql%rowcount ,null,null,null,null);
--                  fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for PPS sales rep SF user id');

                MERGE INTO canon_e633_sales_comp_stg_tbl a
                    USING canon_e633_PPS_user_dwld_tbl b
                    ON (a.ACTUAL_PARENT_SALESREP_ID = b.csa_employee_number AND
                        a.pps_sf_status_flag IN ('I','U') AND
                        a.businessunit = 'PPS')
                 WHEN MATCHED THEN UPDATE SET
                        pps_sf_parent_user_id = b.sf_user_id;
                canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. updated for PPS parent sales rep SF user id : ' ||sql%rowcount ,null,null,null,null);
--                  fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for PPS parent sales rep SF user id');

               update canon_e633_sales_comp_stg_tbl
                  set lfs_sf_status_flag = 'E'
                      ,lfs_sf_status_message = 'Unable to get LFS SF User ID.'
                  where lfs_sf_user_id is null;
               canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. updated for unable to get LFS SF USER ID : ' ||sql%rowcount ,null,null,null,null);
--               fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for unable to get LFS SF USER ID');

               update canon_e633_sales_comp_stg_tbl
                  set pps_sf_status_flag = 'E'
                      ,pps_sf_status_message = 'Unable to get PPS SF User ID.'
                  where pps_sf_user_id is null;
              canon_e633_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','No. updated for unable to get PPS SF USER ID : ' ||sql%rowcount ,null,null,null,null);
--                 fnd_file.put_line(fnd_file.log, sql%rowcount || ' updated for unable to get PPS SF USER ID');
                COMMIT;

                BEGIN
                    UPDATE canon_s21_cd_val_tbl v
                       set v.val76 = l_program_start_date
                     WHERE v.val1 = 'SALES_COMP'
                       AND v.cd_id = (SELECT cd_id
                               FROM canon_s21_cd_tbl
                               where cd_name = 'CANON_E633_INCRDT_TRACKER');
                EXCEPTION
                     when others then
                       dbms_output.put_line('update SALES_COMP last run date exception: '|| SQLERRM);
                       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
                END;
		    dbms_output.put_line(sql%rowcount || ' updated last rundate');
           COMMIT;

EXCEPTION
WHEN OTHERS THEN
    --x_return_status := 'E';
        --dbms_output.put_line('x_return_status:'||x_return_status);
		rollback;
        dbms_output.put_line(SQLERRM);
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
       retcode := '2';
       errbuff := SQLERRM;
END load_sales_comp;

PROCEDURE load_cfslease(retcode OUT VARCHAR2,errbuff OUT VARCHAR2)
IS
    l_procedure_name varchar2(30) := 'load_cfslease';
    l_cfs_lastupdated DATE := NULL;
    l_program_start VARCHAR2(30);
    l_last_run_date_num VARCHAR2(17);
    l_status BOOLEAN;
BEGIN
--to reprocess error and unprocessed records
        retcode := '0';

--        l_program_start := TO_CHAR(SYSDATE, 'DD-MON-YY HH:MI:SS PM');
        l_program_start := SYSDATE;

--        fnd_file.put_line(fnd_file.log, 'Program Start Date: '||l_program_start);
        dbms_output.put_line('Program Start Date: '||l_program_start);

--        l_cfs_lastupdated := TO_DATE(fnd_profile.value('CANON_E633_CFSLEASE_LASTUPDT'), 'DD-MON-YY HH:MI:SS PM');
            BEGIN
                   SELECT last_run_dt
                     INTO l_cfs_lastupdated
                     FROM canon_e633_incrdt_tracker_v
                    WHERE extract_prg = 'CFS_LEASE';
                   IF l_cfs_lastupdated IS NULL THEN
--                     l_cfs_lastupdated := TO_DATE('01-JAN-2008 00:00:00', 'DD-MON-RRRR HH24:MI:SS');
                     l_cfs_lastupdated := NULL;
                   END IF;
                   l_last_run_date_num := TO_CHAR(l_cfs_lastupdated, 'RRRRMMDDHH24MISS');
                END;

--        fnd_file.put_line(fnd_file.log, 'Canon: E633 CFS Lease Last Update Date: '||TO_CHAR(l_cfs_lastupdated, 'DD-MON-YY HH:MI:SS PM'));
        dbms_output.put_line('Canon: E633 CFS Lease Last Update Date: '||TO_CHAR(l_cfs_lastupdated, 'DD-MON-YY HH:MI:SS PM'));
		canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','CFS Last Update Date: '||TO_CHAR(l_cfs_lastupdated, 'DD-MON-YY HH:MI:SS PM'),NULL,NULL,NULL,NULL);

		UPDATE canon_e633_cfslease_stg_tbl stg
           SET lfs_sf_status_flag = CASE WHEN lfs_sf_lease_id IS NOT NULL THEN 'U' ELSE 'I' END
              ,lfs_sf_status_message = NULL
          WHERE lfs_sf_status_flag = 'E' ;

        DBMS_OUTPUT.PUT_LINE('No. of LFS error records updated to U : ' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of LFS error records updated to U : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of LFS error records updated to U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

          UPDATE canon_e633_cfslease_stg_tbl stg
           SET pps_sf_status_flag = CASE WHEN pps_sf_lease_id IS NOT NULL THEN 'U' ELSE 'I' END
              ,pps_sf_status_message = NULL
          WHERE pps_sf_status_flag = 'E' ;

        DBMS_OUTPUT.PUT_LINE('No. of PPS error records updated to U : ' ||sql%rowcount);
--        fnd_file.put_line(fnd_file.log, 'No. of PPS error records updated to U : ' ||sql%rowcount);
canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO','No. of PPS error records updated to U : ' ||sql%rowcount,NULL,NULL,NULL,NULL);

         COMMIT;

        l_cfs_lastupdated := NULL;
         MERGE INTO canon_e633_cfslease_stg_tbl stg
            USING(SELECT cfs_lease_num
                        ,cfs_asset_id
                        ,mtr_tp_cd
                        ,OVAG_TIER_KEY_NUM
                        ,cfs_cust_num
                        ,SER_NUM
                        ,cfs_ADVTG_txt
                        ,ALLW_COPY_NUM
                        ,CUR_DUE_AMT
                        ,PAST_DUE_AMT
                        ,AVAL_LINE_NUM
                        ,FIRST_BLLG_ADDR
                        ,SCD_BLLG_ADDR
                        ,BLLG_CTY_ADDR
                        ,BLLG_ST_ADDR
                        ,BLLG_ZIP_ADDR
                        ,CFS_BR_NM
                        ,CFS_BLLG_MAINT_CD
                        ,CR_LINE_NUM
                        ,CFS_CUST_NM
                        ,DLNQ_STS_NUM
                        ,CFS_DUE_DAYS_CNT
                        ,EQUIP_BLLG_FREQ_CD
                        ,CFS_FLEET_FLG
                        ,FREQ_OVAG_CHRG_CD
                        ,INS_DESC_TXT
                        ,CFS_INV_CD
                        ,INV_LEAD_DAYS_NUM
                        ,INV_METH_CD
                        ,PROP_TAX_INC_IN_PMT_CD
                        ,CASE WHEN LAST_PMT_TS IS NOT NULL THEN  TO_DATE(SUBSTR(LAST_PMT_TS, 1, 8), 'RRRRMMDD')  ELSE NULL END LAST_PMT_TS
                        ,LAST_UPD_TS
                        ,LEASE_CTAC_NM
                        ,LEASE_CTAC_PHO_NUM
                        ,LEASE_SGN_NM
                        ,LEASE_SGN_PHO_NUM
                        ,CFS_LSSR_CD
                        ,CFS_loc_CD
                        ,CASE WHEN NEXT_PMT_TS IS NOT NULL THEN  TO_DATE(SUBSTR(NEXT_PMT_TS, 1, 8), 'RRRRMMDD')  ELSE NULL END NEXT_PMT_TS
                        ,PMT_MADE_NUM
                        ,ORIG_LEASE_PMT_AMT
                        ,CFS_OVAG_RATE
                        ,CFS_PG_FLG
                        ,PO_CUST_FLG
                        ,PRTY_SVC_FLG
                        ,CFS_SVC_DLR_NM
                        ,SPCL_REF_INFO_TXT
                        ,CFS_TAX_CD
                        ,CFS_TERM_CNT
                        ,CASE WHEN CFS_TERM_TS IS NOT NULL THEN  TO_CHAR(TO_DATE(SUBSTR(CFS_TERM_TS, 1, 8), 'RRRRMMDD'), 'MM/DD/RRRR') ELSE NULL END CFS_TERM_TS
                        ,CFS_UPG_AMT
                        ,UPG_TO_RTRN_AMT
                        ,CFS_BYOT_AMT
                        ,FIRST_CPC_MACH_REF_TXT
                        ,SCD_CPC_MACH_REF_TXT
                        ,CFS_DLR_CD
                        ,CFS_DLR_NM
                        ,CASE WHEN CFS_DSPL_TS IS NOT NULL THEN  TO_DATE(SUBSTR(CFS_DSPL_TS, 1, 8), 'RRRRMMDD')  ELSE NULL END CFS_DSPL_TS
                        ,CFS_EQUIP_NUM
                        ,EQUIP_COST_PCT
                        ,FREQ_MAINT_CHRG_CD
                        ,CASE WHEN CFS_INVTY_TS IS NOT NULL THEN  TO_DATE(SUBSTR(CFS_INVTY_TS, 1, 8), 'RRRRMMDD')  ELSE NULL END CFS_INVTY_TS
                        ,INV_SPCL_REF_TXT
                        ,CFS_LEGAL_FLG
                        ,SUB_SUB_FLG
                        ,WRK_OUT_FLG
                        ,CFS_MDL_CD
                        ,ACTV_ASSET_NUM
                        ,PRCH_OPT_CD
                        ,QUOTE_DAYS_FLG
                        ,CFS_RG_CD
                        ,EQUIP_DESC_TXT
                        ,CFS_IF_PO_NUM
                        ,CFS_UPG_APP_NUM
                        ,CFS_CUST_PO_NUM
                        ,FIRST_EQUIP_ADDR
                        ,SCD_EQUIP_ADDR
                        ,EQUIP_CTY_ADDR
                        ,EQUIP_ST_ADDR
                        ,EQUIP_ZIP_ADDR
                        ,CFS_PMT_TXT
                        ,DSP_TP_CD
                        ,SVC_ONLY_TXT
                        ,IN_RNW_CD
                        ,NET_TERMS_TXT
                        ,AGGR_ALLW_COPY_NUM
                        ,PMT_OPT_TXT
                        ,CASE WHEN CFS_CMNC_TS IS NOT NULL THEN  TO_DATE(SUBSTR(CFS_CMNC_TS, 1, 8), 'RRRRMMDD')  ELSE NULL END CFS_CMNC_TS
                        ,CFS_CPC_FLG
                        ,CASE WHEN CR_LINE_EXPR_TS IS NOT NULL THEN  TO_DATE(SUBSTR(CR_LINE_EXPR_TS, 1, 8), 'RRRRMMDD')  ELSE NULL END CR_LINE_EXPR_TS
                        ,CASE WHEN QUOTE_EXPR_TS IS NOT NULL THEN  TO_DATE(SUBSTR(QUOTE_EXPR_TS, 1, 8), 'RRRRMMDD')   ELSE NULL END QUOTE_EXPR_TS
                        ,FINAL_ASSET_USG_AMT
                        ,FINAL_ASSET_USG_TAX_AMT
                        ,FINAL_MAINT_AMT
                        ,FINAL_AU_ERR_FLG
                        ,FINAL_QUOTE_MSG_TXT
                        ,FINAL_MAINT_TAX_AMT
                        ,CUST_LEGAL_NM
                        ,CUST_DBA_TXT
                        ,LATE_CHRG_EXEM_FLG
                        ,CFS_TIER_FLG
                        ,RECUR_CR_CARD_FLG
                        ,RECUR_ACH_FLG
                        ,TOT_FINAL_ASSET_USG_AMT
                        ,TOT_FINAL_ASSET_USG_TAX_AMT
                        ,PRNT_LEASE_NUM
                        ,SRG_LEASE_NUM
                        ,CO_TERM_FLG
                        ,LEASE_LOC_CD
                        ,LEASE_ORIG_CD
                        ,SER_CHNG_FLG
                        ,CFS_CONFIG_NUM
                        ,LEASE_TP_CD
                 FROM NWCI0140_04 cudv
                --WHERE to_date(LAST_UPD_TS,'RRRRMMDD') >= NVL(trunc(l_cfs_lastupdated), to_date(LAST_UPD_TS,'RRRRMMDD'))
                WHERE 1 = 1
                  AND to_date(SUBSTR(LAST_UPD_TS,1,14), 'RRRRMMDDHH24MISS') > NVL(to_date(l_last_run_date_num, 'RRRRMMDDHH24MISS'), to_date(SUBSTR(LAST_UPD_TS,1,14), 'RRRRMMDDHH24MISS')-1 )
				-- AND to_date(SUBSTR(LAST_UPD_TS,1,14), 'RRRRMMDDHH24MISS') > NVL(stg.last_update_date, to_date(SUBSTR(LAST_UPD_TS,1,14), 'RRRRMMDDHH24MISS')) --QC58464
              ) ext
            ON(    stg.lease_number = ext.cfs_lease_num
                 AND stg.asset_id = ext.cfs_asset_id
                 AND NVL(stg.meter_type, '-1') = NVL(ext.mtr_tp_cd, '-1')
                 AND NVL(stg.overage_tier_key, -1) = NVL(ext.OVAG_TIER_KEY_NUM, -1)
              )
          WHEN MATCHED THEN UPDATE
                SET  stg.CUSTOMER_NUMBER = ext.cfs_cust_num
                    ,stg.SERIAL_NUMBER = ext.SER_NUM
                    ,stg.ADVANTAGE = ext.cfs_ADVTG_txt
                    ,stg.ALLOWABLE_COPIES = ext.ALLW_COPY_NUM
                    ,stg.AMOUNT_CURRENT_DUE = ext.CUR_DUE_AMT
                    ,stg.AMOUNT_PAST_DUE = ext.PAST_DUE_AMT
                    ,stg.AVAILABLE_LINE = ext.AVAL_LINE_NUM
                    ,stg.BILLING_ADDRESS1 = ext.FIRST_BLLG_ADDR
                    ,stg.BILLING_ADDRESS2 = ext.SCD_BLLG_ADDR
                    ,stg.BILLING_CITY = ext.BLLG_CTY_ADDR
                    ,stg.BILLING_STATE = ext.BLLG_ST_ADDR
                    ,stg.BILLING_ZIP = ext.BLLG_ZIP_ADDR
                    ,stg.BRANCH_NAME = ext.CFS_BR_NM
                    ,stg.CFS_BILLING_MAINT = ext.CFS_BLLG_MAINT_CD
                    ,stg.CREDIT_LINE = ext.CR_LINE_NUM
                    ,stg.CUSTOMER_NAME = ext.CFS_CUST_NM
                    ,stg.DELINQUENCY_STATUS = ext.DLNQ_STS_NUM
                    ,stg.DUE_DATE = ext.CFS_DUE_DAYS_CNT
                    ,stg.EQUIP_BILLING_FREQUENCY = ext.EQUIP_BLLG_FREQ_CD
                    ,stg.FLEET = ext.CFS_FLEET_FLG
                    ,stg.FREQ_OF_OVERAGE_CHARGES = ext.FREQ_OVAG_CHRG_CD
                    ,stg.INSURANCE_DESC = ext.INS_DESC_TXT
                    ,stg.INVOICE_CODE = ext.CFS_INV_CD
                    ,stg.INVOICE_LEAD_DAYS = ext.INV_LEAD_DAYS_NUM
                    ,stg.INVOICE_METHOD = ext.INV_METH_CD
                    ,stg.IS_PROP_TAX_INC_IN_PAYMT = ext.PROP_TAX_INC_IN_PMT_CD
                    ,stg.LAST_PAYMENT_DATE = ext.LAST_PMT_TS
                    ,stg.LAST_UPDATE_DATE = to_date(SUBSTR(ext.LAST_UPD_TS,1,14), 'RRRRMMDDHH24MISS')
                    ,stg.LEASE_CONTACT_NAME = ext.LEASE_CTAC_NM
                    ,stg.LEASE_CONTACT_PHONE = ext.LEASE_CTAC_PHO_NUM
                    ,stg.LEASE_SIGNER_NAME = ext.LEASE_SGN_NM
                    ,stg.LEASE_SIGNER_PHONE = ext.LEASE_SGN_PHO_NUM
                    ,stg.LESSOR = ext.CFS_LSSR_CD
                    ,stg.LOCATION = ext.CFS_loc_CD
                    ,stg.NEXT_PAYMENT_DATE = ext.NEXT_PMT_TS
                    ,stg.NO_OF_PAYMT_MADE = ext.PMT_MADE_NUM
                    ,stg.ORIGINAL_LEASE_PAYMENT = ext.ORIG_LEASE_PMT_AMT
                    ,stg.OVERAGE_RATE = ext.CFS_OVAG_RATE
                    ,stg.PG = ext.CFS_PG_FLG
                    ,stg.PO_CUSTOMER = ext.PO_CUST_FLG
                    ,stg.PRIORITY_SERVICE = ext.PRTY_SVC_FLG
                    ,stg.SERVICE_DEALER = ext.CFS_SVC_DLR_NM
                    ,stg.SPECIAL_REFERENCE_INFO = ext.SPCL_REF_INFO_TXT
                    ,stg.TAXABLE = ext.CFS_TAX_CD
                    ,stg.TERM = ext.CFS_TERM_CNT
                    ,stg.TERM_DATE = ext.CFS_TERM_TS
                    ,stg.UPGRADE = ext.CFS_UPG_AMT
                    ,stg.UPGRADE_TO_RETURN = ext.UPG_TO_RTRN_AMT
                    ,stg.BUYOUT = ext.CFS_BYOT_AMT
                    ,stg.CPC_MACHINE_REF1 = ext.FIRST_CPC_MACH_REF_TXT
                    ,stg.CPC_MACHINE_REF2 = ext.SCD_CPC_MACH_REF_TXT
                    ,stg.DEALER = ext.CFS_DLR_CD
                    ,stg.DEALER_NAME = ext.CFS_DLR_NM
                    ,stg.DISPOSAL_DATE = ext.CFS_DSPL_TS
                    ,stg.EQUIP_CODE = ext.CFS_EQUIP_NUM
                    ,stg.EQUIP_COST_PCT = ext.EQUIP_COST_PCT
                    ,stg.FREQ_OF_MAINT_CHARGES = ext.FREQ_MAINT_CHRG_CD
                    ,stg.INVENTORY_DATE = ext.CFS_INVTY_TS
                    ,stg.INVOICE_SPECIAL_REF2 = ext.INV_SPCL_REF_TXT
                    ,stg.IS_LEGAL = ext.CFS_LEGAL_FLG
                    ,stg.IS_SUB_SUB = ext.SUB_SUB_FLG
                    ,stg.IS_WORKOUT = ext.WRK_OUT_FLG
                    ,stg.MODEL = ext.CFS_MDL_CD
                    ,stg.NUM_OF_ACTIVE_ASSETS = ext.ACTV_ASSET_NUM
                    ,stg.PURCHASE_OPT_CODE = ext.PRCH_OPT_CD
                    ,stg.QUOTE_90_DAYS = ext.QUOTE_DAYS_FLG
                    ,stg.REGION = ext.CFS_RG_CD
                    ,stg.EQUIP_DESC = ext.EQUIP_DESC_TXT
                    ,stg.CFS_PO_NUMBER = ext.CFS_IF_PO_NUM
                    ,stg.APPLICATION_NO = ext.CFS_UPG_APP_NUM
                    ,stg.CUSTOMER_PO_NUMBER = ext.CFS_CUST_PO_NUM
                    ,stg.EQUIP_ADDR1 = ext.FIRST_EQUIP_ADDR
                    ,stg.EQUIP_ADDR2 = ext.SCD_EQUIP_ADDR
                    ,stg.EQUIP_CITY = ext.EQUIP_CTY_ADDR
                    ,stg.EQUIP_STATE = ext.EQUIP_ST_ADDR
                    ,stg.EQUIP_ZIP = ext.EQUIP_ZIP_ADDR
                    ,stg.PAYMENT_AMOUNT = ext.CFS_PMT_TXT
                    ,stg.DISPOSITION_TYPE = ext.DSP_TP_CD
                    ,stg.SERVICE_ONLY = ext.SVC_ONLY_TXT
                    ,stg.IN_RENEWAL = ext.IN_RNW_CD
                    ,stg.NET_TERMS = ext.NET_TERMS_TXT
                    ,stg.AGGR_ALLOWABLE_COPIES = ext.AGGR_ALLW_COPY_NUM
                    ,stg.PAYMENT_OPTION = ext.PMT_OPT_TXT
                    ,stg.COMMENCEMENT_DATE = ext.CFS_CMNC_TS
                    ,stg.IS_CPC = ext.CFS_CPC_FLG
                    ,stg.CREDIT_LINE_EXPIRE_DATE = ext.CR_LINE_EXPR_TS
                    ,stg.QUOTE_EXPIRY_DATE = ext.QUOTE_EXPR_TS
                    ,stg.FINAL_ASSET_USAGE = ext.FINAL_ASSET_USG_AMT
                    ,stg.FINAL_ASSET_USAGE_TAX = ext.FINAL_ASSET_USG_TAX_AMT
                    ,stg.FINAL_MAINTENANCE = ext.FINAL_MAINT_AMT
                    ,stg.FINAL_AU_ERR_FLG = ext.FINAL_AU_ERR_FLG
                    ,stg.FINAL_QUOTE_MSG = ext.FINAL_QUOTE_MSG_TXT
                    ,stg.FINAL_MAINTENANCE_TAX = ext.FINAL_MAINT_TAX_AMT
                    ,stg.CUST_LEGAL_NAME = ext.CUST_LEGAL_NM
                    ,stg.CUST_DBA = ext.CUST_DBA_TXT
                    ,stg.LATE_CHRG_EXMPT = ext.LATE_CHRG_EXEM_FLG
                    ,stg.IS_TIERED = ext.CFS_TIER_FLG
                    ,stg.IS_RECUR_CREDIT_CARD = ext.RECUR_CR_CARD_FLG
                    ,stg.IS_RECUR_ACH = ext.RECUR_ACH_FLG
                    ,stg.TOTAL_FINAL_ASSET_USAGE = ext.TOT_FINAL_ASSET_USG_AMT
                    ,stg.TOTAL_FINAL_ASSET_USAGE_TAX = ext.TOT_FINAL_ASSET_USG_TAX_AMT
                    ,stg.PARENT_LEASE_NUMBER = ext.PRNT_LEASE_NUM
                    ,stg.SRG_LEASE_NUMBER = ext.SRG_LEASE_NUM
                    ,stg.IS_CO_TERM = ext.CO_TERM_FLG
                    ,stg.LEASE_LOCATION = ext.LEASE_LOC_CD
                    ,stg.LEASE_ORIGINATION = ext.LEASE_ORIG_CD
                    ,stg.IS_SERIAL_CHANGED = ext.SER_CHNG_FLG
                    ,stg.CONFIGURATION_NUMBER = ext.CFS_CONFIG_NUM
                    ,stg.LEASE_TYPE = ext.LEASE_TP_CD
                    ,stg.lfs_sf_status_flag = (CASE lfs_sf_lease_id WHEN NULL THEN 'I' ELSE 'U' END)
                    ,stg.pps_sf_status_flag = (CASE pps_sf_lease_id WHEN NULL THEN 'I' ELSE 'U' END)
                    ,stg.lfs_sf_status_message = NULL
                    ,stg.pps_sf_status_message = NULL
                    ,stg.lfs_last_update_date = SYSDATE
                    ,stg.pps_last_update_date = SYSDATE
            WHEN NOT MATCHED THEN INSERT(
                     LEASE_NUMBER
                    ,CUSTOMER_NUMBER
                    ,ASSET_ID
                    ,SERIAL_NUMBER
                    ,METER_TYPE
                    ,OVERAGE_TIER_KEY
                    ,ADVANTAGE
                    ,ALLOWABLE_COPIES
                    ,AMOUNT_CURRENT_DUE
                    ,AMOUNT_PAST_DUE
                    ,AVAILABLE_LINE
                    ,BILLING_ADDRESS1
                    ,BILLING_ADDRESS2
                    ,BILLING_CITY
                    ,BILLING_STATE
                    ,BILLING_ZIP
                    ,BRANCH_NAME
                    ,CFS_BILLING_MAINT
                    ,CREDIT_LINE
                    ,CUSTOMER_NAME
                    ,DELINQUENCY_STATUS
                    ,DUE_DATE
                    ,EQUIP_BILLING_FREQUENCY
                    ,FLEET
                    ,FREQ_OF_OVERAGE_CHARGES
                    ,INSURANCE_DESC
                    ,INVOICE_CODE
                    ,INVOICE_LEAD_DAYS
                    ,INVOICE_METHOD
                    ,IS_PROP_TAX_INC_IN_PAYMT
                    ,LAST_PAYMENT_DATE
                    ,LAST_UPDATE_DATE
                    ,LEASE_CONTACT_NAME
                    ,LEASE_CONTACT_PHONE
                    ,LEASE_SIGNER_NAME
                    ,LEASE_SIGNER_PHONE
                    ,LESSOR
                    ,LOCATION
                    ,NEXT_PAYMENT_DATE
                    ,NO_OF_PAYMT_MADE
                    ,ORIGINAL_LEASE_PAYMENT
                    ,OVERAGE_RATE
                    ,PG
                    ,PO_CUSTOMER
                    ,PRIORITY_SERVICE
                    ,SERVICE_DEALER
                    ,SPECIAL_REFERENCE_INFO
                    ,TAXABLE
                    ,TERM
                    ,TERM_DATE
                    ,UPGRADE
                    ,UPGRADE_TO_RETURN
                    ,BUYOUT
                    ,CPC_MACHINE_REF1
                    ,CPC_MACHINE_REF2
                    ,DEALER
                    ,DEALER_NAME
                    ,DISPOSAL_DATE
                    ,EQUIP_CODE
                    ,EQUIP_COST_PCT
                    ,FREQ_OF_MAINT_CHARGES
                    ,INVENTORY_DATE
                    ,INVOICE_SPECIAL_REF2
                    ,IS_LEGAL
                    ,IS_SUB_SUB
                    ,IS_WORKOUT
                    ,MODEL
                    ,NUM_OF_ACTIVE_ASSETS
                    ,PURCHASE_OPT_CODE
                    ,QUOTE_90_DAYS
                    ,REGION
                    ,EQUIP_DESC
                    ,CFS_PO_NUMBER
                    ,APPLICATION_NO
                    ,CUSTOMER_PO_NUMBER
                    ,EQUIP_ADDR1
                    ,EQUIP_ADDR2
                    ,EQUIP_CITY
                    ,EQUIP_STATE
                    ,EQUIP_ZIP
                    ,PAYMENT_AMOUNT
                    ,DISPOSITION_TYPE
                    ,SERVICE_ONLY
                    ,IN_RENEWAL
                    ,NET_TERMS
                    ,AGGR_ALLOWABLE_COPIES
                    ,PAYMENT_OPTION
                    ,COMMENCEMENT_DATE
                    ,IS_CPC
                    ,CREDIT_LINE_EXPIRE_DATE
                    ,QUOTE_EXPIRY_DATE
                    ,FINAL_ASSET_USAGE
                    ,FINAL_ASSET_USAGE_TAX
                    ,FINAL_MAINTENANCE
                    ,FINAL_AU_ERR_FLG
                    ,FINAL_QUOTE_MSG
                    ,FINAL_MAINTENANCE_TAX
                    ,CUST_LEGAL_NAME
                    ,CUST_DBA
                    ,LATE_CHRG_EXMPT
                    ,IS_TIERED
                    ,IS_RECUR_CREDIT_CARD
                    ,IS_RECUR_ACH
                    ,TOTAL_FINAL_ASSET_USAGE
                    ,TOTAL_FINAL_ASSET_USAGE_TAX
                    ,PARENT_LEASE_NUMBER
                    ,SRG_LEASE_NUMBER
                    ,IS_CO_TERM
                    ,LEASE_LOCATION
                    ,LEASE_ORIGINATION
                    ,IS_SERIAL_CHANGED
                    ,CONFIGURATION_NUMBER
                    ,LEASE_TYPE
                    ,lfs_sf_status_flag
                    ,pps_sf_status_flag
                    ,lfs_sf_status_message
                    ,pps_sf_status_message
                    ,lfs_last_update_date
                    ,pps_last_update_date
                    ,Unique_key
                   )
--
             VALUES (ext.cfs_lease_num
                    ,ext.cfs_cust_num
                    ,ext.cfs_ASSET_ID
                    ,ext.SER_NUM
                    ,ext.MTR_TP_CD
                    ,ext.OVAG_TIER_KEY_NUM
                    ,ext.cfs_ADVTG_txt
                    ,ext.ALLW_COPY_NUM
                    ,ext.CUR_DUE_AMT
                    ,ext.PAST_DUE_AMT
                    ,ext.AVAL_LINE_NUM
                    ,ext.FIRST_BLLG_ADDR
                    ,ext.SCD_BLLG_ADDR
                    ,ext.BLLG_CTY_ADDR
                    ,ext.BLLG_ST_ADDR
                    ,ext.BLLG_ZIP_ADDR
                    ,ext.CFS_BR_NM
                    ,ext.CFS_BLLG_MAINT_CD
                    ,ext.CR_LINE_NUM
                    ,ext.CFS_CUST_NM
                    ,ext.DLNQ_STS_NUM
                    ,ext.CFS_DUE_DAYS_CNT
                    ,ext.EQUIP_BLLG_FREQ_CD
                    ,ext.CFS_FLEET_FLG
                    ,ext.FREQ_OVAG_CHRG_CD
                    ,ext.INS_DESC_TXT
                    ,ext.CFS_INV_CD
                    ,ext.INV_LEAD_DAYS_NUM
                    ,ext.INV_METH_CD
                    ,ext.PROP_TAX_INC_IN_PMT_CD
                    ,ext.LAST_PMT_TS
                    ,to_date(SUBSTR(ext.LAST_UPD_TS,1,14), 'RRRRMMDDHH24MISS')
                    ,ext.LEASE_CTAC_NM
                    ,ext.LEASE_CTAC_PHO_NUM
                    ,ext.LEASE_SGN_NM
                    ,ext.LEASE_SGN_PHO_NUM
                    ,ext.CFS_LSSR_CD
                    ,ext.CFS_LOC_CD
                    ,ext.NEXT_PMT_TS
                    ,ext.PMT_MADE_NUM
                    ,ext.ORIG_LEASE_PMT_AMT
                    ,ext.CFS_OVAG_RATE
                    ,ext.CFS_PG_FLG
                    ,ext.PO_CUST_FLG
                    ,ext.PRTY_SVC_FLG
                    ,ext.CFS_SVC_DLR_NM
                    ,ext.SPCL_REF_INFO_TXT
                    ,ext.CFS_TAX_CD
                    ,ext.CFS_TERM_CNT
                    ,ext.CFS_TERM_TS
                    ,ext.CFS_UPG_AMT
                    ,ext.UPG_TO_RTRN_AMT
                    ,ext.CFS_BYOT_AMT
                    ,ext.FIRST_CPC_MACH_REF_TXT
                    ,ext.SCD_CPC_MACH_REF_TXT
                    ,ext.CFS_DLR_CD
                    ,ext.CFS_DLR_NM
                    ,ext.CFS_DSPL_TS
                    ,ext.CFS_EQUIP_NUM
                    ,ext.EQUIP_COST_PCT
                    ,ext.FREQ_MAINT_CHRG_CD
                    ,ext.CFS_INVTY_TS
                    ,ext.INV_SPCL_REF_TXT
                    ,ext.CFS_LEGAL_FLG
                    ,ext.SUB_SUB_FLG
                    ,ext.WRK_OUT_FLG
                    ,ext.CFS_MDL_CD
                    ,ext.ACTV_ASSET_NUM
                    ,ext.PRCH_OPT_CD
                    ,ext.QUOTE_DAYS_FLG
                    ,ext.CFS_RG_CD
                    ,ext.EQUIP_DESC_TXT
                    ,ext.CFS_IF_PO_NUM
                    ,ext.CFS_UPG_APP_NUM
                    ,ext.CFS_CUST_PO_NUM
                    ,ext.FIRST_EQUIP_ADDR
                    ,ext.SCD_EQUIP_ADDR
                    ,ext.EQUIP_CTY_ADDR
                    ,ext.EQUIP_ST_ADDR
                    ,ext.EQUIP_ZIP_ADDR
                    ,ext.CFS_PMT_TXT
                    ,ext.DSP_TP_CD
                    ,ext.SVC_ONLY_TXT
                    ,ext.IN_RNW_CD
                    ,ext.NET_TERMS_TXT
                    ,ext.AGGR_ALLW_COPY_NUM
                    ,ext.PMT_OPT_TXT
                    ,ext.CFS_CMNC_TS
                    ,ext.CFS_CPC_FLG
                    ,ext.CR_LINE_EXPR_TS
                    ,ext.QUOTE_EXPR_TS
                    ,ext.FINAL_ASSET_USG_AMT
                    ,ext.FINAL_ASSET_USG_TAX_AMT
                    ,ext.FINAL_MAINT_AMT
                    ,ext.FINAL_AU_ERR_FLG
                    ,ext.FINAL_QUOTE_MSG_TXT
                    ,ext.FINAL_MAINT_TAX_AMT
                    ,ext.CUST_LEGAL_NM
                    ,ext.CUST_DBA_TXT
                    ,ext.LATE_CHRG_EXEM_FLG
                    ,ext.CFS_TIER_FLG
                    ,ext.RECUR_CR_CARD_FLG
                    ,ext.RECUR_ACH_FLG
                    ,ext.TOT_FINAL_ASSET_USG_AMT
                    ,ext.TOT_FINAL_ASSET_USG_TAX_AMT
                    ,ext.PRNT_LEASE_NUM
                    ,ext.SRG_LEASE_NUM
                    ,ext.CO_TERM_FLG
                    ,ext.LEASE_LOC_CD
                    ,ext.LEASE_ORIG_CD
                    ,ext.SER_CHNG_FLG
                    ,ext.CFS_CONFIG_NUM
                    ,ext.LEASE_TP_CD
                    ,'I'
                    ,'I'
                    ,NULL
                    ,NULL
                    ,SYSDATE
                    ,SYSDATE
                    ,ext.cfs_lease_num||ext.cfs_asset_id||ext.mtr_tp_cd||ext.OVAG_TIER_KEY_NUM);

--            fnd_file.put_line(fnd_file.log, SQL%ROWCOUNT||' records merged from view.');
            dbms_output.put_line(SQL%ROWCOUNT||' records merged from view.');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records merged from view.',NULL,NULL,NULL,NULL);

--        l_status := fnd_profile.save('CANON_E633_CFSLEASE_LASTUPDT', l_program_start, 'SITE', NULL, NULL, NULL);

--        fnd_file.put_line(fnd_file.log, 'Canon: E633 CFS Lease Last Update Date set to '||l_program_start);
         --update the IB's if any location changes QC#58123
         UPDATE canon_e633_cfslease_stg_tbl cfs
           SET lfs_sf_status_flag = 'U'
           WHERE lfsbu is not null
             AND lfs_sf_lease_id IS NOT NULL
             AND lfs_sf_ib_id <> (SELECT lfs_sf_ib_id
                                    FROM canon_e633_ib_stg_tbl ib
                                    WHERE ib.config_nbr = cfs.configuration_number
                                      AND lfs_sf_status_flag = 'P')
            AND configuration_number = (SELECT config_nbr
                                          FROM canon_e633_ib_stg_tbl ib
                                          WHERE ib.config_nbr = cfs.configuration_number
                                            AND lfs_sf_status_flag = 'P');
--          AND to_date(l_last_run_date_num, 'RRRRMMDDHH24MISS') < (SELECT CAST(TO_TIMESTAMP (smm.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) ezuptime
--                                                                       FROM svc_mach_mstr smm
--                                                                            ,canon_e633_ib_stg_tbl ib
--                                                                       WHERE smm.svc_config_mstr_pk = cfs.configuration_number
--                                                                         and smm.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
--                                                                         and ib.config_nbr = cfs.configuration_number
--                                                                         and smm.glbl_cmpy_cd = 'ADB'
--                                                                         and smm.ezcancelflag = '0');
         dbms_output.put_line(SQL%ROWCOUNT||' LFS leases requires IB ID changes');
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' LFS leases requires IB ID changes.',NULL,NULL,NULL,NULL);

        UPDATE canon_e633_cfslease_stg_tbl cfs
           SET pps_sf_status_flag = 'U'
           WHERE ppsbu is not null
             AND pps_sf_lease_id IS NOT NULL
             AND pps_sf_ib_id <> (SELECT pps_sf_ib_id
                                    FROM canon_e633_ib_stg_tbl ib
                                    WHERE ib.config_nbr = cfs.configuration_number
                                      AND pps_sf_status_flag = 'P')
            AND configuration_number = (SELECT config_nbr
                                          FROM canon_e633_ib_stg_tbl ib
                                          WHERE ib.config_nbr = cfs.configuration_number
                                            AND pps_sf_status_flag = 'P');
--          AND to_date(l_last_run_date_num, 'RRRRMMDDHH24MISS') < (SELECT CAST(TO_TIMESTAMP (smm.ezuptime, 'RRRRMMDDHH24MISSFF3') AS DATE) ezuptime
--                                                                       FROM svc_mach_mstr smm
--                                                                            ,canon_e633_ib_stg_tbl ib
--                                                                       WHERE smm.svc_config_mstr_pk = cfs.configuration_number
--                                                                         and smm.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
--                                                                         and ib.config_nbr = cfs.configuration_number
--                                                                         and smm.glbl_cmpy_cd = 'ADB'
--                                                                         and smm.ezcancelflag = '0');
         dbms_output.put_line(SQL%ROWCOUNT||' PPS leases requires IB ID changes');
        canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' PPS leases requires IB ID changes.',NULL,NULL,NULL,NULL);
--
          dbms_output.put_line(SQL%ROWCOUNT||' LFS leases requires IB ID changes');
			canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'INFO',SQL%ROWCOUNT||' records merged from view.',NULL,NULL,NULL,NULL);
--end QC#58123
            BEGIN
				UPDATE canon_s21_cd_val_tbl v
				   SET v.val76 = l_program_start
				 WHERE v.val1 = 'CFS_LEASE'
				   AND v.cd_id = (SELECT cd_id
								   FROM canon_s21_cd_tbl
								   WHERE cd_name = 'CANON_E633_INCRDT_TRACKER');
			   EXCEPTION
				 WHEN OTHERS THEN
				   dbms_output.put_line('update CFS_LEASE last run date exception: '|| SQLERRM);
				   canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		    END;
		    dbms_output.put_line(sql%rowcount || ' updated last rundate');
        dbms_output.put_line('Canon: E633 CFS Lease Last Update Date set to '||l_program_start);

       COMMIT;
EXCEPTION
 WHEN OTHERS THEN
        retcode := '2';
        errbuff := SQLERRM;
       canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END load_cfslease;


PROCEDURE extract(errbuff OUT VARCHAR2, retcode OUT VARCHAR2, p_extract IN VARCHAR2)
IS
   l_procedure_name varchar2(30) := 'extract';
   x_retcode VARCHAR2(300);
   x_errbuff VARCHAR2(4000);
   x_status VARCHAR2(1);
BEGIN
   retcode := '0';

--   canon_e633_sf_bulk_pkg.is_Program_Running('CANON_E633_LFSPPS_EXTRACT'
--                                            ,p_extract
--                                            ,x_status
--                                            );

   IF x_status = 'Y' THEN
      errbuff := 'Canon: E633 LFSPPS Extract Program already Running extract '||p_extract;
      retcode := '1';
   ELSE
   CASE
   WHEN p_extract = 'Customers' THEN
	  load_customers(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Install Base' THEN
	  load_ib(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Configurations' THEN
	  load_config_ib(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Machine Contacts' THEN
	  load_ib_contacts(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Meter Reads' THEN
	  load_meter_reads(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Contracts' THEN
	  load_contracts(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Open Service Calls' THEN
	  load_open_service_calls(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Closed Service Calls' THEN
	  load_closed_service_calls(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'AR Aging' THEN
	  load_ar_aging(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
  WHEN p_extract = 'CFS Leases' THEN
          load_cfslease(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Order Headers' THEN
	  load_order_headers(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Order Details' THEN
	  load_order_details(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Order Comments' THEN
	  load_order_comments(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'Sales Comp' THEN
	  load_sales_comp(x_retcode, x_errbuff);
	  retcode := x_retcode;
	  errbuff := x_errbuff;
   WHEN p_extract = 'IB Delete' THEN --QC57878
      delete_ib(x_retcode, x_errbuff);
      retcode := x_retcode;
      errbuff := x_errbuff;
   WHEN p_extract = 'Contract Delete' THEN --QC58464
      delete_contracts(x_retcode, x_errbuff);
      retcode := x_retcode;
      errbuff := x_errbuff;
   WHEN p_extract = 'Open SVC Calls Delete' THEN --QC58464
      delete_open_service_calls(x_retcode, x_errbuff);
      retcode := x_retcode;
      errbuff := x_errbuff;
   WHEN p_extract = 'Closed SVC Calls Delete' THEN --QC58464
      delete_closed_service_calls(x_retcode, x_errbuff);
      retcode := x_retcode;
      errbuff := x_errbuff;
   WHEN p_extract = 'Meter Reads Delete' THEN --QC58464
      delete_meter_reads(x_retcode, x_errbuff);
      retcode := x_retcode;
      errbuff := x_errbuff;
   ELSE retcode := '2';
    END CASE;
    END IF;

EXCEPTION
   WHEN OTHERS THEN
      retcode := '2';
      errbuff := SQLERRM;
      canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
END extract;

    FUNCTION format_phone (
        v_phone IN VARCHAR2
    ) RETURN VARCHAR2 AS

        retphone    VARCHAR2(15) := NULL;
        frmtphone   VARCHAR2(25) := NULL;
        areacode    VARCHAR2(3) := NULL;
        p1          VARCHAR2(3) := NULL;
        p2          VARCHAR2(4) := NULL;
    BEGIN
            --dbms_output.put_line('v_phone: ' || v_phone);
        frmtphone := trim(v_phone);
            --dbms_output.put_line('frmtPhone: ' || frmtPhone);
        IF ( length(frmtphone) > 0 ) THEN
            frmtphone := regexp_replace(v_phone, '[^0-9A-Za-z]', '');
                --dbms_output.put_line('rmtPhone: ' || REGEXP_REPLACE(v_phone, '[^0-9A-Za-z]', ''));
        END IF;
            --dbms_output.put_line('frmtPhone: ' || frmtPhone);
            --dbms_output.put_line('length of frmtPhone: ' || length(frmtPhone));

        IF ( length(frmtphone) = 10 ) THEN
            areacode := substr(frmtphone, 1, 3);
            p1 := substr(frmtphone, 4, 3);
            p2 := substr(frmtphone, 7);
            retphone := '('
                        || areacode
                        || ')'
						|| ' '
                        || p1
                        || '-'
                        || p2;

        END IF;

        IF ( length(frmtphone) = 11 ) THEN
            IF ( frmtphone LIKE '1%' ) THEN -- phone number starting with 1 like 18003334444
                areacode := substr(frmtphone, 2, 3);
                p1 := substr(frmtphone, 5, 3);
                p2 := substr(frmtphone, 8);
                retphone := '('
                            || areacode
                            || ')'
							|| ' '
                            || p1
                            || '-'
                            || p2;

            ELSE
                retphone := v_phone;
            END IF;
        END IF;
            --dbms_output.put_line('retPhone: ' || retPhone);

        RETURN retphone;
--        EXCEPTION
--            WHEN OTHERS THEN
--            canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,'format_phone','ERROR',NULL,NULL,NULL,NULL,SQLERRM);
--            return v_phone;
    END;

	--QC58464
 	PROCEDURE delete_contracts(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
		IS
            l_procedure_name        VARCHAR2(60) := 'delete_contracts';
            l_run_date  DATE    := SYSDATE;
            l_last_run_date DATE;
            l_program_start_date DATE := SYSDATE;
            l_last_run_date_num VARCHAR2(17);

	  BEGIN
	  retcode := '0';
		-- delete old data from the archive table
		DELETE
		  FROM canon_e633_contracts_stg_arc
		 WHERE created_date <= add_months(sysdate, -6);

		-- archive the processed records
		--BEGIN
			INSERT INTO canon_e633_contracts_stg_arc(id,
													contract_no,
													contract,
													contract_type,
													billing_type,
													pool_comp_free_co,
													config_nbr,
													start_date,
													end_date,
													boundary_1_busine,
													boundary_2_busine,
													boundary_3_busine,
													first_copy_price_1,
													first_copy_price_2,
													first_copy_price_3,
													first_copy_price_4,
													min_vol_amt_bus,
													min_vol_amt_ind,
													first_involved_bu,
													scnd_involved_bu,
													first_fixed_amount,
													scnd_fixed_amount,
													adv_arrear_ind_1,
													adv_arrear_ind_2,
													adv_arrear_ind_3,
													first_fixed_bill_fr,
													scnd_fixed_bill_fr,
													var_bill_frequenc,
													first_1,
													second_1,
													variable_1,
													ref_field_1,
													sevice_type,
													coverage,
													fixed_price_end_1,
													price_chg_code_1,
													fixed_price_prd_1,
													max_price_uplft_1,
													fixed_price_end_2,
													price_chg_code_2,
													fixed_price_prd_2,
													max_price_uplft_2,
													var_prc_end_dt_1,
													var_price_chg_cd1,
													var_prc_period_2,
													var_max_prc_perc1,
													var_prc_end_dt_2,
													var_price_chg_cd2,
													var_prc_period_2_2,
													var_max_prc_perc2,
													currency_code,
													component_seq,
													condition_set_version,
													purchase_option,
													address_number,
													expiration_date,
													source,
													free_to_use_1,
													free_to_use_2,
													free_to_use_3,
													free_to_use_4,
													free_to_use_5,
													free_to_use_6,
													meter_type,
													label,
													label_descrip,
													description,
													component_type,
													commercial_name_language_1,
													unique_key,
													lfs_sf_ib_id,
													lfs_sf_account_id,
													lfs_sf_contract_id,
													lfs_sf_status_flag,
													lfs_sf_status_message,
													lfs_last_update_date,
													pps_sf_ib_id,
													pps_sf_account_id,
													pps_sf_contract_id,
													pps_sf_status_flag,
													pps_sf_status_message,
													pps_last_update_date,
													lfsbu,
													dpsbu,
													ppsbu,
													bill_to_acct_name,
													bill_to_address,
													ds_contr_bllg_mtr_pk,
													contr_xs_copy_pk,
													svc_mach_mstr_pk,
													created_date) SELECT id,
													contract_no,
													contract,
													contract_type,
													billing_type,
													pool_comp_free_co,
													config_nbr,
													start_date,
													end_date,
													boundary_1_busine,
													boundary_2_busine,
													boundary_3_busine,
													first_copy_price_1,
													first_copy_price_2,
													first_copy_price_3,
													first_copy_price_4,
													min_vol_amt_bus,
													min_vol_amt_ind,
													first_involved_bu,
													scnd_involved_bu,
													first_fixed_amount,
													scnd_fixed_amount,
													adv_arrear_ind_1,
													adv_arrear_ind_2,
													adv_arrear_ind_3,
													first_fixed_bill_fr,
													scnd_fixed_bill_fr,
													var_bill_frequenc,
													first_1,
													second_1,
													variable_1,
													ref_field_1,
													sevice_type,
													coverage,
													fixed_price_end_1,
													price_chg_code_1,
													fixed_price_prd_1,
													max_price_uplft_1,
													fixed_price_end_2,
													price_chg_code_2,
													fixed_price_prd_2,
													max_price_uplft_2,
													var_prc_end_dt_1,
													var_price_chg_cd1,
													var_prc_period_2,
													var_max_prc_perc1,
													var_prc_end_dt_2,
													var_price_chg_cd2,
													var_prc_period_2_2,
													var_max_prc_perc2,
													currency_code,
													component_seq,
													condition_set_version,
													purchase_option,
													address_number,
													expiration_date,
													source,
													free_to_use_1,
													free_to_use_2,
													free_to_use_3,
													free_to_use_4,
													free_to_use_5,
													free_to_use_6,
													meter_type,
													label,
													label_descrip,
													description,
													component_type,
													commercial_name_language_1,
													unique_key,
													lfs_sf_ib_id,
													lfs_sf_account_id,
													lfs_sf_contract_id,
													lfs_sf_status_flag,
													lfs_sf_status_message,
													lfs_last_update_date,
													pps_sf_ib_id,
													pps_sf_account_id,
													pps_sf_contract_id,
													pps_sf_status_flag,
													pps_sf_status_message,
													pps_last_update_date,
													lfsbu,
													dpsbu,
													ppsbu,
													bill_to_acct_name,
													bill_to_address,
													ds_contr_bllg_mtr_pk,
													contr_xs_copy_pk,
													svc_mach_mstr_pk,
													sysdate
													  FROM canon_e633_contracts_del_tbl
													 WHERE 1 = 1
													   AND NVL(lfs_sf_status_flag,'P') IN ('P', 'DP' )
													   AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
													canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows archived : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
													COMMIT;

		--delete the processed records which are archived from delete table
			DELETE
			  FROM canon_e633_contracts_del_tbl
			  WHERE 1 = 1
			    AND NVL(lfs_sf_status_flag,'P') IN ('P','DP' )
             AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from contracts delete table : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--
		--reprocess the errored records
			UPDATE canon_e633_contracts_del_tbl
			   SET lfs_sf_status_flag = 'D'
			 WHERE lfs_sf_status_flag IN ('E','DE')
			   AND lfsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of lfs rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--

			UPDATE canon_e633_contracts_del_tbl
			   SET pps_sf_status_flag = 'D'
			 WHERE pps_sf_status_flag IN ('E','DE')
			   AND ppsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of pps rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;


			INSERT INTO canon_e633_contracts_del_tbl ( id,
													contract_no,
													contract,
													contract_type,
													billing_type,
													pool_comp_free_co,
													config_nbr,
													start_date,
													end_date,
													boundary_1_busine,
													boundary_2_busine,
													boundary_3_busine,
													first_copy_price_1,
													first_copy_price_2,
													first_copy_price_3,
													first_copy_price_4,
													min_vol_amt_bus,
													min_vol_amt_ind,
													first_involved_bu,
													scnd_involved_bu,
													first_fixed_amount,
													scnd_fixed_amount,
													adv_arrear_ind_1,
													adv_arrear_ind_2,
													adv_arrear_ind_3,
													first_fixed_bill_fr,
													scnd_fixed_bill_fr,
													var_bill_frequenc,
													first_1,
													second_1,
													variable_1,
													ref_field_1,
													sevice_type,
													coverage,
													fixed_price_end_1,
													price_chg_code_1,
													fixed_price_prd_1,
													max_price_uplft_1,
													fixed_price_end_2,
													price_chg_code_2,
													fixed_price_prd_2,
													max_price_uplft_2,
													var_prc_end_dt_1,
													var_price_chg_cd1,
													var_prc_period_2,
													var_max_prc_perc1,
													var_prc_end_dt_2,
													var_price_chg_cd2,
													var_prc_period_2_2,
													var_max_prc_perc2,
													currency_code,
													component_seq,
													condition_set_version,
													purchase_option,
													address_number,
													expiration_date,
													source,
													free_to_use_1,
													free_to_use_2,
													free_to_use_3,
													free_to_use_4,
													free_to_use_5,
													free_to_use_6,
													meter_type,
													label,
													label_descrip,
													description,
													component_type,
													commercial_name_language_1,
													unique_key,
													lfs_sf_ib_id,
													lfs_sf_account_id,
													lfs_sf_contract_id,
													lfs_sf_status_flag,
													lfs_sf_status_message,
													lfs_last_update_date,
													pps_sf_ib_id,
													pps_sf_account_id,
													pps_sf_contract_id,
													pps_sf_status_flag,
													pps_sf_status_message,
													pps_last_update_date,
													lfsbu,
													dpsbu,
													ppsbu,
													bill_to_acct_name,
													bill_to_address,
													ds_contr_bllg_mtr_pk,
													contr_xs_copy_pk,
													svc_mach_mstr_pk,
													created_date) SELECT id,
																		contract_no,
																		contract,
																		contract_type,
																		billing_type,
																		pool_comp_free_co,
																		config_nbr,
																		start_date,
																		end_date,
																		boundary_1_busine,
																		boundary_2_busine,
																		boundary_3_busine,
																		first_copy_price_1,
																		first_copy_price_2,
																		first_copy_price_3,
																		first_copy_price_4,
																		min_vol_amt_bus,
																		min_vol_amt_ind,
																		first_involved_bu,
																		scnd_involved_bu,
																		first_fixed_amount,
																		scnd_fixed_amount,
																		adv_arrear_ind_1,
																		adv_arrear_ind_2,
																		adv_arrear_ind_3,
																		first_fixed_bill_fr,
																		scnd_fixed_bill_fr,
																		var_bill_frequenc,
																		first_1,
																		second_1,
																		variable_1,
																		ref_field_1,
																		sevice_type,
																		coverage,
																		fixed_price_end_1,
																		price_chg_code_1,
																		fixed_price_prd_1,
																		max_price_uplft_1,
																		fixed_price_end_2,
																		price_chg_code_2,
																		fixed_price_prd_2,
																		max_price_uplft_2,
																		var_prc_end_dt_1,
																		var_price_chg_cd1,
																		var_prc_period_2,
																		var_max_prc_perc1,
																		var_prc_end_dt_2,
																		var_price_chg_cd2,
																		var_prc_period_2_2,
																		var_max_prc_perc2,
																		currency_code,
																		component_seq,
																		condition_set_version,
																		purchase_option,
																		address_number,
																		expiration_date,
																		source,
																		free_to_use_1,
																		free_to_use_2,
																		free_to_use_3,
																		free_to_use_4,
																		free_to_use_5,
																		free_to_use_6,
																		meter_type,
																		label,
																		label_descrip,
																		description,
																		component_type,
																		commercial_name_language_1,
																		unique_key,
																		lfs_sf_ib_id,
																		lfs_sf_account_id,
																		lfs_sf_contract_id,
																		(CASE WHEN lfs_sf_contract_id IS NOT NULL AND lfsbu IS NOT NULL THEN 'D'
																			  WHEN lfs_sf_contract_id IS NULL THEN 'P' ELSE lfs_sf_status_flag END) lfs_sf_status_flag,
																		lfs_sf_status_message,
																		lfs_last_update_date,
																		pps_sf_ib_id,
																		pps_sf_account_id,
																		pps_sf_contract_id,
																		(CASE WHEN pps_sf_contract_id IS NOT NULL AND ppsbu IS NOT NULL THEN 'D'
																			  WHEN pps_sf_contract_id IS NULL THEN 'P' ELSE pps_sf_status_flag END)pps_sf_status_flag,
																		pps_sf_status_message,
																		pps_last_update_date,
																		lfsbu,
																		dpsbu,
																		ppsbu,
																		bill_to_acct_name,
																		bill_to_address,
																		ds_contr_bllg_mtr_pk,
																		contr_xs_copy_pk,
																		svc_mach_mstr_pk,
																		sysdate
																		  FROM canon_e633_contracts_stg_tbl cnt
																		 WHERE 1 = 1
																		   AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
																		   AND (lfs_sf_contract_id IS NOT NULL OR pps_sf_contract_id IS NOT NULL)
																		--   AND life_cycle_stat NOT IN ('REMOVED')
																		   AND (NOT EXISTS (SELECT 1
																							 FROM canon_e633_ib_stg_tbl ib
																							WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
																							  AND ib.config_nbr = cnt.config_nbr
																							  AND ib.life_cycle_stat NOT IN ('REMOVED')
																							--  AND(ib.lfs_sf_status_flag IN ('I','U','P') OR ib.pps_sf_Status_flag  IN ('I', 'U','P'))
																							  AND NVL(ib.lfs_sf_account_id,'AB') = NVL(cnt.lfs_sf_account_id, 'AB')
																							  AND NVL(ib.pps_sf_account_id, 'AB') = NVL(cnt.pps_sf_account_id, 'AB')));
																				 /*OR EXISTS (SELECT 1
																							  FROM canon_e633_ib_del_tbl ib
																							  WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
																							    AND ib.config_nbr = cnt.config_nbr
																								AND ib.lfs_status_flag IN ('P','DE')
																								AND ib.pps_sf_status_flag IN ('P','DE')
																								AND (ib.lfs_status_message LIKE 'DELETE_OPERATION_TOO_LARGE%' OR ib.pps_sf_status_message LIKE 'DELETE_OPERATION_TOO_LARGE%')*/

					canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows identified for delete : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
					COMMIT;

		--delete these records from contracts_stg table
		DELETE
		  FROM canon_e633_contracts_stg_tbl cnt
		 WHERE 1 = 1
		  AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
		  AND (lfs_sf_contract_id IS NOT NULL OR pps_sf_contract_id IS NOT NULL)
		   AND NOT EXISTS (SELECT 1
							 FROM canon_e633_ib_stg_tbl ib
							WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
							  AND ib.config_nbr = cnt.config_nbr
							  AND ib.life_cycle_stat NOT IN ('REMOVED')
							--  AND(ib.lfs_sf_status_flag IN ('I','U','P') OR ib.pps_sf_Status_flag  IN ('I', 'U','P'))
							  AND NVL(ib.lfs_sf_account_id,'AB') = NVL(cnt.lfs_sf_account_id, 'AB')
							  AND NVL(ib.pps_sf_account_id, 'AB') = NVL(cnt.pps_sf_account_id, 'AB'));
		canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from Contracts STG : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
		COMMIT;

        	EXCEPTION
            WHEN OTHERS THEN
            retcode := 2;
              errbuff := SQLERRM;
                canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		END delete_contracts;


--QC58464
 	PROCEDURE delete_open_service_calls(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
		IS
            l_procedure_name        VARCHAR2(60) := 'delete_open_service_calls';
            l_run_date  DATE    := SYSDATE;
            l_last_run_date DATE;
            l_program_start_date DATE := SYSDATE;
            l_last_run_date_num VARCHAR2(17);

	  BEGIN
	  retcode := '0';
		-- delete old data from the archive table
		DELETE
		  FROM canon_e633_opencalls_stg_arc
		 WHERE created_date <= add_months(sysdate, -6);

		-- archive the processed records
		--BEGIN
			INSERT INTO canon_e633_opencalls_stg_arc(legacy_ref_id,
													configuration_number,
													jobnumber,
													arrival_date,
													action_code,
													cause_code,
													module_code,
													close,
													card_code,
													address_number,
													source,
													free_to_use_1,
													free_to_use_2,
													free_to_use_3,
													free_to_use_4,
													free_to_use_5,
													free_to_use_6,
													call_creation_date,
													unique_key,
													lfs_sf_ib_id,
													lfs_sf_account_id,
													lfs_sf_open_service_id ,
													lfs_sf_status_flag,
													lfs_sf_status_message,
													lfs_last_update_date,
													pps_sf_ib_id,
													pps_sf_account_id,
													pps_sf_open_service_id,
													pps_sf_status_flag,
													pps_sf_status_message,
													pps_last_update_date,
													lfsbu,
													dpsbu,
													ppsbu,
													account_name,
													address,
													svc_mach_mstr_pk ,
													fsr_num,
													created_date) SELECT legacy_ref_id,
																		configuration_number,
																		jobnumber,
																		arrival_date,
																		action_code,
																		cause_code,
																		module_code,
																		close,
																		card_code ,
																		address_number,
																		source ,
																		free_to_use_1,
																		free_to_use_2,
																		free_to_use_3,
																		free_to_use_4,
																		free_to_use_5,
																		free_to_use_6,
																		call_creation_date,
																		unique_key,
																		lfs_sf_ib_id,
																		lfs_sf_account_id,
																		lfs_sf_open_service_id,
																		lfs_sf_status_flag,
																		lfs_sf_status_message,
																		lfs_last_update_date,
																		pps_sf_ib_id,
																		pps_sf_account_id,
																		pps_sf_open_service_id,
																		pps_sf_status_flag ,
																		pps_sf_status_message,
																		pps_last_update_date,
																		lfsbu,
																		dpsbu,
																		ppsbu,
																		account_name,
																		address,
																		svc_mach_mstr_pk ,
																		fsr_num,
																		sysdate
																		  FROM canon_e633_opencalls_del_tbl
																		 WHERE 1 = 1
																		   AND NVL(lfs_sf_status_flag,'P') IN ('P', 'DP' )
																		   AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
													canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows archived : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
													COMMIT;

		--delete the processed records which are archived from delete table
			DELETE
			  FROM canon_e633_opencalls_del_tbl
			  WHERE 1 = 1
			    AND NVL(lfs_sf_status_flag,'P') IN ('P','DP' )
             AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from contracts delete table : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--
		--reprocess the errored records
			UPDATE canon_e633_opencalls_del_tbl
			   SET lfs_sf_status_flag = 'D'
			 WHERE lfs_sf_status_flag IN ('E','DE')
			   AND lfsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of lfs rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--

			UPDATE canon_e633_opencalls_del_tbl
			   SET pps_sf_status_flag = 'D'
			 WHERE pps_sf_status_flag IN ('E','DE')
			   AND ppsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of pps rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;


			INSERT INTO canon_e633_opencalls_del_tbl ( legacy_ref_id,
													configuration_number,
													jobnumber,
													arrival_date,
													action_code,
													cause_code,
													module_code,
													close,
													card_code ,
													address_number,
													source ,
													free_to_use_1,
													free_to_use_2,
													free_to_use_3,
													free_to_use_4,
													free_to_use_5,
													free_to_use_6,
													call_creation_date,
													unique_key,
													lfs_sf_ib_id,
													lfs_sf_account_id,
													lfs_sf_open_service_id,
													lfs_sf_status_flag,
													lfs_sf_status_message,
													lfs_last_update_date,
													pps_sf_ib_id,
													pps_sf_account_id,
													pps_sf_open_service_id,
													pps_sf_status_flag ,
													pps_sf_status_message,
													pps_last_update_date,
													lfsbu,
													dpsbu,
													ppsbu,
													account_name,
													address,
													svc_mach_mstr_pk ,
													fsr_num,
													created_date) SELECT legacy_ref_id,
																		configuration_number,
																		jobnumber,
																		arrival_date,
																		action_code,
																		cause_code,
																		module_code,
																		close,
																		card_code ,
																		address_number,
																		source ,
																		free_to_use_1,
																		free_to_use_2,
																		free_to_use_3,
																		free_to_use_4,
																		free_to_use_5,
																		free_to_use_6,
																		call_creation_date,
																		unique_key,
																		lfs_sf_ib_id,
																		lfs_sf_account_id,
																		lfs_sf_open_service_id,
																		(CASE WHEN lfs_sf_open_service_id IS NOT NULL AND lfsbu IS NOT NULL THEN 'D'
																			  WHEN lfs_sf_open_service_id IS NULL THEN 'P' ELSE lfs_sf_status_flag END) lfs_sf_status_flag,
																		lfs_sf_status_message,
																		lfs_last_update_date,
																		pps_sf_ib_id,
																		pps_sf_account_id,
																		pps_sf_open_service_id,
																		(CASE WHEN pps_sf_open_service_id IS NOT NULL AND ppsbu IS NOT NULL THEN 'D'
																			  WHEN pps_sf_open_service_id IS NULL THEN 'P' ELSE pps_sf_status_flag END) pps_sf_status_flag,
																		pps_sf_status_message,
																		pps_last_update_date,
																		lfsbu,
																		dpsbu,
																		ppsbu,
																		account_name,
																		address,
																		svc_mach_mstr_pk ,
																		fsr_num,
																		sysdate
																		  FROM canon_e633_opencalls_stg_tbl cnt
																		 WHERE 1 = 1
																		   AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
																		  -- AND (lfs_sf_open_service_id IS NOT NULL OR pps_sf_open_service_id IS NOT NULL)
																		--   AND life_cycle_stat NOT IN ('REMOVED')
																		   AND (NOT EXISTS (SELECT 1
																							 FROM canon_e633_ib_stg_tbl ib
																							WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
																							  AND ib.config_nbr = cnt.configuration_number
																							  AND ib.life_cycle_stat NOT IN ('REMOVED')
																							--  AND(ib.lfs_sf_status_flag IN ('I','U','P') OR ib.pps_sf_Status_flag  IN ('I', 'U','P'))
																							  AND NVL(ib.lfs_sf_account_id,'AB') = NVL(cnt.lfs_sf_account_id, 'AB')
																							  AND NVL(ib.pps_sf_account_id, 'AB') = NVL(cnt.pps_sf_account_id, 'AB')));
																				 /*OR EXISTS (SELECT 1
																							  FROM canon_e633_ib_del_tbl ib
																							  WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
																							    AND ib.config_nbr = cnt.config_nbr
																								AND ib.lfs_status_flag IN ('P','DE')
																								AND ib.pps_sf_status_flag IN ('P','DE')
																								AND (ib.lfs_status_message LIKE 'DELETE_OPERATION_TOO_LARGE%' OR ib.pps_sf_status_message LIKE 'DELETE_OPERATION_TOO_LARGE%')*/

					canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows identified for delete : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
					COMMIT;

		--delete these records from contracts_stg table
		DELETE
		  FROM canon_e633_opencalls_stg_tbl cnt
		 WHERE 1 = 1
		  AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
		 -- AND (lfs_sf_contract_id IS NOT NULL OR pps_sf_contract_id IS NOT NULL)
		   AND NOT EXISTS (SELECT 1
							 FROM canon_e633_ib_stg_tbl ib
							WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
							  AND ib.config_nbr = cnt.configuration_number
							  AND ib.life_cycle_stat NOT IN ('REMOVED')
							--  AND(ib.lfs_sf_status_flag IN ('I','U','P') OR ib.pps_sf_Status_flag  IN ('I', 'U','P'))
							  AND NVL(ib.lfs_sf_account_id,'AB') = NVL(cnt.lfs_sf_account_id, 'AB')
							  AND NVL(ib.pps_sf_account_id, 'AB') = NVL(cnt.pps_sf_account_id, 'AB'));
		canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from Contracts STG : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
		COMMIT;

        	EXCEPTION
            WHEN OTHERS THEN
            retcode := 2;
              errbuff := SQLERRM;
                canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		END delete_open_service_calls;

PROCEDURE delete_closed_service_calls(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
		IS
            l_procedure_name        VARCHAR2(60) := 'delete_closed_service_calls';
            l_run_date  DATE    := SYSDATE;
            l_last_run_date DATE;
            l_program_start_date DATE := SYSDATE;
            l_last_run_date_num VARCHAR2(17);

	  BEGIN
	  retcode := '0';
		-- delete old data from the archive table
		DELETE
		  FROM canon_e633_closecalls_stg_arc
		 WHERE created_date <= add_months(sysdate, -6);

		-- archive the processed records
		--BEGIN
			INSERT INTO canon_e633_closecalls_stg_arc(legacy_ref_id,
													configuration_number,
													jobnumber,
													arrival_date,
													action_code,
													cause_code,
													module_code,
													time_between_jobs,
													time_between_failure,
													copies_between_jobs,
													copies_between_failure,
													de_installation_job,
													installation_job,
													cm_job,
													pm_job,
													last_visit_technician,
													close,
													card_code,
													closed_date,
													reason_cancel_code,
													job_status,
													address_number,
													source,
													free_to_use_1,
													free_to_use_2,
													free_to_use_3,
													free_to_use_4,
													free_to_use_5,
													free_to_use_6,
													call_creation_date,
													last_visit_tech,
													account_name,
													address,
													unique_key,
													lfs_sf_ib_id,
													lfs_sf_account_id,
													lfs_sf_close_service_id,
													lfs_sf_status_flag,
													lfs_sf_status_message,
													lfs_last_update_date,
													pps_sf_ib_id,
													pps_sf_account_id,
													pps_sf_close_service_id,
													pps_sf_status_flag,
													pps_sf_status_message,
													pps_last_update_date,
													lfsbu,
													dpsbu,
													ppsbu,
													svc_mach_mstr_pk,
													fsr_num,
													created_date) SELECT legacy_ref_id,
																		configuration_number,
																		jobnumber,
																		arrival_date,
																		action_code,
																		cause_code,
																		module_code,
																		time_between_jobs,
																		time_between_failure,
																		copies_between_jobs,
																		copies_between_failure,
																		de_installation_job,
																		installation_job,
																		cm_job,
																		pm_job,
																		last_visit_technician,
																		close,
																		card_code,
																		closed_date,
																		reason_cancel_code,
																		job_status,
																		address_number,
																		source,
																		free_to_use_1,
																		free_to_use_2,
																		free_to_use_3,
																		free_to_use_4,
																		free_to_use_5,
																		free_to_use_6,
																		call_creation_date,
																		last_visit_tech,
																		account_name,
																		address,
																		unique_key,
																		lfs_sf_ib_id,
																		lfs_sf_account_id,
																		lfs_sf_close_service_id,
																		lfs_sf_status_flag,
																		lfs_sf_status_message,
																		lfs_last_update_date,
																		pps_sf_ib_id,
																		pps_sf_account_id,
																		pps_sf_close_service_id,
																		pps_sf_status_flag,
																		pps_sf_status_message,
																		pps_last_update_date,
																		lfsbu,
																		dpsbu,
																		ppsbu,
																		svc_mach_mstr_pk,
																		fsr_num,
																		sysdate
																		  FROM canon_e633_closecalls_del_tbl
																		 WHERE 1 = 1
																		   AND NVL(lfs_sf_status_flag,'P') IN ('P', 'DP' )
																		   AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
													canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows archived : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
													COMMIT;

		--delete the processed records which are archived from delete table
			DELETE
			  FROM canon_e633_closecalls_del_tbl
			  WHERE 1 = 1
			    AND NVL(lfs_sf_status_flag,'P') IN ('P','DP' )
             AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from contracts delete table : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--
		--reprocess the errored records
			UPDATE canon_e633_closecalls_del_tbl
			   SET lfs_sf_status_flag = 'D'
			 WHERE lfs_sf_status_flag IN ('E','DE')
			   AND lfsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of lfs rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--

			UPDATE canon_e633_closecalls_del_tbl
			   SET pps_sf_status_flag = 'D'
			 WHERE pps_sf_status_flag IN ('E','DE')
			   AND ppsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of pps rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;


			INSERT INTO canon_e633_closecalls_del_tbl ( legacy_ref_id,
													configuration_number,
													jobnumber,
													arrival_date,
													action_code,
													cause_code,
													module_code,
													time_between_jobs,
													time_between_failure,
													copies_between_jobs,
													copies_between_failure,
													de_installation_job,
													installation_job,
													cm_job,
													pm_job,
													last_visit_technician,
													close,
													card_code,
													closed_date,
													reason_cancel_code,
													job_status,
													address_number,
													source,
													free_to_use_1,
													free_to_use_2,
													free_to_use_3,
													free_to_use_4,
													free_to_use_5,
													free_to_use_6,
													call_creation_date,
													last_visit_tech,
													account_name,
													address,
													unique_key,
													lfs_sf_ib_id,
													lfs_sf_account_id,
													lfs_sf_close_service_id,
													lfs_sf_status_flag,
													lfs_sf_status_message,
													lfs_last_update_date,
													pps_sf_ib_id,
													pps_sf_account_id,
													pps_sf_close_service_id,
													pps_sf_status_flag,
													pps_sf_status_message,
													pps_last_update_date,
													lfsbu,
													dpsbu,
													ppsbu,
													svc_mach_mstr_pk,
													fsr_num,
													created_date) SELECT legacy_ref_id,
																		configuration_number,
																		jobnumber,
																		arrival_date,
																		action_code,
																		cause_code,
																		module_code,
																		time_between_jobs,
																		time_between_failure,
																		copies_between_jobs,
																		copies_between_failure,
																		de_installation_job,
																		installation_job,
																		cm_job,
																		pm_job,
																		last_visit_technician,
																		close,
																		card_code,
																		closed_date,
																		reason_cancel_code,
																		job_status,
																		address_number,
																		source,
																		free_to_use_1,
																		free_to_use_2,
																		free_to_use_3,
																		free_to_use_4,
																		free_to_use_5,
																		free_to_use_6,
																		call_creation_date,
																		last_visit_tech,
																		account_name,
																		address,
																		unique_key,
																		lfs_sf_ib_id,
																		lfs_sf_account_id,
																		lfs_sf_close_service_id,
																		(CASE WHEN lfs_sf_close_service_id IS NOT NULL AND lfsbu IS NOT NULL THEN 'D'
																			  WHEN lfs_sf_close_service_id IS NULL THEN 'P' ELSE lfs_sf_status_flag END) lfs_sf_status_flag,
																		lfs_sf_status_message,
																		lfs_last_update_date,
																		pps_sf_ib_id,
																		pps_sf_account_id,
																		pps_sf_close_service_id,
																		(CASE WHEN pps_sf_close_service_id IS NOT NULL AND ppsbu IS NOT NULL THEN 'D'
																			  WHEN pps_sf_close_service_id IS NULL THEN 'P' ELSE pps_sf_status_flag END) pps_sf_status_flag,
																		pps_sf_status_message,
																		pps_last_update_date,
																		lfsbu,
																		dpsbu,
																		ppsbu,
																		svc_mach_mstr_pk,
																		fsr_num,
																		sysdate
																 FROM canon_e633_closecalls_stg_tbl cnt
																WHERE 1 = 1
																  AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
															   -- AND (lfs_sf_open_service_id IS NOT NULL OR pps_sf_open_service_id IS NOT NULL)
															   -- AND life_cycle_stat NOT IN ('REMOVED')
																  AND (NOT EXISTS (SELECT 1
																					 FROM canon_e633_ib_stg_tbl ib
																					WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
																					  AND ib.config_nbr = cnt.configuration_number
																					  AND ib.life_cycle_stat NOT IN ('REMOVED')
																					--  AND(ib.lfs_sf_status_flag IN ('I','U','P') OR ib.pps_sf_Status_flag  IN ('I', 'U','P'))
																					  AND NVL(ib.lfs_sf_account_id,'AB') = NVL(cnt.lfs_sf_account_id, 'AB')
																					  AND NVL(ib.pps_sf_account_id, 'AB') = NVL(cnt.pps_sf_account_id, 'AB')));
																		 /*OR EXISTS (SELECT 1
																					  FROM canon_e633_ib_del_tbl ib
																					  WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
																						AND ib.config_nbr = cnt.config_nbr
																						AND ib.lfs_status_flag IN ('P','DE')
																						AND ib.pps_sf_status_flag IN ('P','DE')
																						AND (ib.lfs_status_message LIKE 'DELETE_OPERATION_TOO_LARGE%' OR ib.pps_sf_status_message LIKE 'DELETE_OPERATION_TOO_LARGE%')*/

					canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows identified for delete : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
					COMMIT;

		--delete these records from contracts_stg table
		DELETE
		  FROM canon_e633_closecalls_stg_tbl cnt
		 WHERE 1 = 1
		  AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
		 -- AND (lfs_sf_contract_id IS NOT NULL OR pps_sf_contract_id IS NOT NULL)
		   AND NOT EXISTS (SELECT 1
							 FROM canon_e633_ib_stg_tbl ib
							WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
							  AND ib.config_nbr = cnt.configuration_number
							  AND ib.life_cycle_stat NOT IN ('REMOVED')
							--  AND(ib.lfs_sf_status_flag IN ('I','U','P') OR ib.pps_sf_Status_flag  IN ('I', 'U','P'))
							  AND NVL(ib.lfs_sf_account_id,'AB') = NVL(cnt.lfs_sf_account_id, 'AB')
							  AND NVL(ib.pps_sf_account_id, 'AB') = NVL(cnt.pps_sf_account_id, 'AB'));
		canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from Contracts STG : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
		COMMIT;

        	EXCEPTION
            WHEN OTHERS THEN
            retcode := 2;
              errbuff := SQLERRM;
                canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		END delete_closed_service_calls;

		PROCEDURE delete_meter_reads(retcode OUT VARCHAR2, errbuff OUT VARCHAR2)
		IS
            l_procedure_name        VARCHAR2(60) := 'delete_meter_reads';
            l_run_date  DATE    := SYSDATE;
            l_last_run_date DATE;
            l_program_start_date DATE := SYSDATE;
            l_last_run_date_num VARCHAR2(17);

	  BEGIN
	  retcode := '0';
		-- delete old data from the archive table
		DELETE
		  FROM canon_e633_meter_reads_stg_arc
		 WHERE created_date <= add_months(sysdate, -6);

		-- archive the processed records
		--BEGIN
			INSERT INTO canon_e633_meter_reads_stg_arc(configuration_number,
														serial_no,
														label,
														label_descrip,
														meter_type,
														meter_read_type,
														meter_reading,
														registration_date,
														creation_date,
														invoice_period,
														meter_source,
														meter_read_type_1,
														meter_read_system_number,
														logical_delete_indicator,
														last_change_date,
														svc_mach_mstr_pk,
														lfs_sf_ib_id,
														lfs_sf_account_id,
														lfs_sf_mtr_read_id,
														lfs_sf_status_flag,
														lfs_sf_status_message,
														lfs_last_update_date,
														pps_sf_ib_id,
														pps_sf_account_id,
														pps_sf_mtr_read_id,
														pps_sf_status_flag,
														pps_sf_status_message,
														pps_last_update_date,
														lfsbu,
														dpsbu,
														ppsbu,
														created_date) SELECT configuration_number,
																			serial_no,
																			label,
																			label_descrip,
																			meter_type,
																			meter_read_type,
																			meter_reading,
																			registration_date,
																			creation_date,
																			invoice_period,
																			meter_source,
																			meter_read_type_1,
																			meter_read_system_number,
																			logical_delete_indicator,
																			last_change_date,
																			svc_mach_mstr_pk,
																			lfs_sf_ib_id,
																			lfs_sf_account_id,
																			lfs_sf_mtr_read_id,
																			lfs_sf_status_flag,
																			lfs_sf_status_message,
																			lfs_last_update_date,
																			pps_sf_ib_id,
																			pps_sf_account_id,
																			pps_sf_mtr_read_id,
																			pps_sf_status_flag,
																			pps_sf_status_message,
																			pps_last_update_date,
																			lfsbu,
																			dpsbu,
																			ppsbu,
																			sysdate
																	FROM canon_e633_meter_reads_del_tbl
																   WHERE 1 = 1
																     AND NVL(lfs_sf_status_flag,'P') IN ('P', 'DP' )
																     AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
													canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows archived : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
													COMMIT;

		--delete the processed records which are archived from delete table
			DELETE
			  FROM canon_e633_meter_reads_del_tbl
			  WHERE 1 = 1
			    AND NVL(lfs_sf_status_flag,'P') IN ('P','DP' )
             AND NVL(pps_sf_status_flag, 'P') IN ('P','DP');
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from contracts delete table : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--
		--reprocess the errored records
			UPDATE canon_e633_meter_reads_del_tbl
			   SET lfs_sf_status_flag = 'D'
			 WHERE lfs_sf_status_flag IN ('E','DE')
			   AND lfsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of lfs rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;
--

			UPDATE canon_e633_meter_reads_del_tbl
			   SET pps_sf_status_flag = 'D'
			 WHERE pps_sf_status_flag IN ('E','DE')
			   AND ppsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of pps rows updated to reprocess  : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;


			INSERT INTO canon_e633_meter_reads_del_tbl ( configuration_number,
														serial_no,
														label,
														label_descrip,
														meter_type,
														meter_read_type,
														meter_reading,
														registration_date,
														creation_date,
														invoice_period,
														meter_source,
														meter_read_type_1,
														meter_read_system_number,
														logical_delete_indicator,
														last_change_date,
														svc_mach_mstr_pk,
														lfs_sf_ib_id,
														lfs_sf_account_id,
														lfs_sf_mtr_read_id,
														lfs_sf_status_flag,
														lfs_sf_status_message,
														lfs_last_update_date,
														pps_sf_ib_id,
														pps_sf_account_id,
														pps_sf_mtr_read_id,
														pps_sf_status_flag,
														pps_sf_status_message,
														pps_last_update_date,
														lfsbu,
														dpsbu,
														ppsbu,
														created_date) SELECT configuration_number,
																			serial_no,
																			label,
																			label_descrip,
																			meter_type,
																			meter_read_type,
																			meter_reading,
																			registration_date,
																			creation_date,
																			invoice_period,
																			meter_source,
																			meter_read_type_1,
																			meter_read_system_number,
																			logical_delete_indicator,
																			last_change_date,
																			svc_mach_mstr_pk,
																			lfs_sf_ib_id,
																			lfs_sf_account_id,
																			lfs_sf_mtr_read_id,
																			(CASE WHEN lfs_sf_mtr_read_id IS NOT NULL AND lfsbu IS NOT NULL THEN 'D'
																			  WHEN lfs_sf_mtr_read_id IS NULL THEN 'P' ELSE lfs_sf_status_flag END) lfs_sf_status_flag,
																			lfs_sf_status_message,
																			lfs_last_update_date,
																			pps_sf_ib_id,
																			pps_sf_account_id,
																			pps_sf_mtr_read_id,
																			(CASE WHEN pps_sf_mtr_read_id IS NOT NULL AND ppsbu IS NOT NULL THEN 'D'
																			  WHEN pps_sf_mtr_read_id IS NULL THEN 'P' ELSE pps_sf_status_flag END) pps_sf_status_flag,
																			pps_sf_status_message,
																			pps_last_update_date,
																			lfsbu,
																			dpsbu,
																			ppsbu,
																			sysdate
																		  FROM canon_e633_meter_reads_stg_tbl cnt
																		 WHERE 1 = 1
																		   AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
																		  -- AND (lfs_sf_open_service_id IS NOT NULL OR pps_sf_open_service_id IS NOT NULL)
																		--   AND life_cycle_stat NOT IN ('REMOVED')
																		   AND NOT EXISTS (SELECT 1
																							 FROM canon_e633_ib_stg_tbl ib
																							WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
																							  AND ib.config_nbr = cnt.configuration_number
																							  AND ib.life_cycle_stat NOT IN ('REMOVED')
																							--  AND(ib.lfs_sf_status_flag IN ('I','U','P') OR ib.pps_sf_Status_flag  IN ('I', 'U','P'))
																							  AND NVL(ib.lfs_sf_account_id,'AB') = NVL(cnt.lfs_sf_account_id, 'AB')
																							  AND NVL(ib.pps_sf_account_id, 'AB') = NVL(cnt.pps_sf_account_id, 'AB'))
																		UNION
																		--for if any IB delete failed due to huge number of meter read accounts for delete.
																		-- for this we have to first the meter read(child) records and then delete the IB(parent)
																		SELECT configuration_number,
																				serial_no,
																				label,
																				label_descrip,
																				meter_type,
																				meter_read_type,
																				meter_reading,
																				registration_date,
																				creation_date,
																				invoice_period,
																				meter_source,
																				meter_read_type_1,
																				meter_read_system_number,
																				logical_delete_indicator,
																				last_change_date,
																				svc_mach_mstr_pk,
																				lfs_sf_ib_id,
																				lfs_sf_account_id,
																				lfs_sf_mtr_read_id,
																				(CASE WHEN lfs_sf_mtr_read_id IS NOT NULL AND lfsbu IS NOT NULL THEN 'D'
																				  WHEN lfs_sf_mtr_read_id IS NULL THEN 'P' ELSE lfs_sf_status_flag END) lfs_sf_status_flag,
																				lfs_sf_status_message,
																				lfs_last_update_date,
																				pps_sf_ib_id,
																				pps_sf_account_id,
																				pps_sf_mtr_read_id,
																				(CASE WHEN pps_sf_mtr_read_id IS NOT NULL AND ppsbu IS NOT NULL THEN 'D'
																				  WHEN pps_sf_mtr_read_id IS NULL THEN 'P' ELSE pps_sf_status_flag END) pps_sf_status_flag,
																				pps_sf_status_message,
																				pps_last_update_date,
																				lfsbu,
																				dpsbu,
																				ppsbu,
																				sysdate
																		  FROM canon_e633_meter_reads_stg_tbl cnt
																		 WHERE 1 = 1
																		   AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
																		   AND EXISTS (SELECT 1
																							  FROM canon_e633_ib_del_tbl ib
																							  WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
																							    AND ib.config_nbr = cnt.configuration_number
																								AND ib.lfs_sf_status_flag IN ('P','DE')
																								AND ib.pps_sf_status_flag IN ('P','DE')
																								AND (ib.lfs_sf_status_message LIKE 'DELETE_OPERATION_TOO_LARGE%' OR ib.pps_sf_status_message LIKE 'DELETE_OPERATION_TOO_LARGE%'));

					canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows identified for delete : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
					COMMIT;
			UPDATE canon_e633_meter_reads_del_tbl cnt
			   SET pps_sf_status_flag = 'D'
			 WHERE INVOICE_PERIOD >= '09/01/2021-09/30/2021'
             AND ppsbu IS NOT NULL;
			canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of pps rows updated to Invoice_date: ' || SQL%rowcount,NULL,NULL, NULL, NULL);
			COMMIT;

		--delete these records from contracts_stg table
		DELETE
		  FROM canon_e633_meter_reads_stg_tbl cnt
		 WHERE 1 = 1
		  AND(lfs_sf_status_flag IN ('I','U','P') OR pps_sf_Status_flag  IN ('I', 'U','P'))
		 -- AND (lfs_sf_contract_id IS NOT NULL OR pps_sf_contract_id IS NOT NULL)
		   AND NOT EXISTS (SELECT 1
							 FROM canon_e633_ib_stg_tbl ib
							WHERE ib.svc_mach_mstr_pk = cnt.svc_mach_mstr_pk
							  AND ib.config_nbr = cnt.configuration_number
							  AND ib.life_cycle_stat NOT IN ('REMOVED')
							--  AND(ib.lfs_sf_status_flag IN ('I','U','P') OR ib.pps_sf_Status_flag  IN ('I', 'U','P'))
							  AND NVL(ib.lfs_sf_account_id,'AB') = NVL(cnt.lfs_sf_account_id, 'AB')
							  AND NVL(ib.pps_sf_account_id, 'AB') = NVL(cnt.pps_sf_account_id, 'AB'));
		canon_e633_sf_error_log_pkg.log_error(g_package_name, l_procedure_name, 'INFO', 'No. of rows deleted from Contracts STG : ' || SQL%rowcount,NULL,NULL, NULL, NULL);
		COMMIT;

        	EXCEPTION
            WHEN OTHERS THEN
            retcode := 2;
              errbuff := SQLERRM;
                canon_e633_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,'ERROR',NULL,NULL,NULL,NULL,SQLERRM);
		END delete_meter_reads;

END canon_e633_sf_lfspps_pkg;		

/

