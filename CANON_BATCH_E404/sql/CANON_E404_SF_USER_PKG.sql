CREATE OR REPLACE PACKAGE CANON_e404_sf_user_pkg
AS
TYPE x_role_data IS REF CURSOR ;
g_package_name varchar2(30) := 'CANON_E404_SF_USER_PKG';
PROCEDURE extract_users(x_return_status   OUT VARCHAR2
                       ,x_msg_data        OUT VARCHAR2
                       );
PROCEDURE get_org_code(v_resource_id IN VARCHAR2
                        ,l_mapped_org OUT VARCHAR2
                        ,l_comments OUT VARCHAR2);
PROCEDURE get_role_name(v_resource_id IN VARCHAR2
                        ,v_org_name IN VARCHAR2
                        ,x_role        OUT VARCHAR2
                        ,x_comments  IN OUT VARCHAR2);
PROCEDURE get_sf_profile_id(v_last_name IN VARCHAR2
                            ,v_role        IN VARCHAR2
                            ,x_sf_profile_id OUT VARCHAR2
                            ,l_comments    IN OUT VARCHAR2);
PROCEDURE get_sf_role_id(l_mapped_org IN VARCHAR2
                         ,l_last_name IN VARCHAR2
                         ,l_resource_id IN VARCHAR2
                         ,x_sf_role_id OUT VARCHAR2
                         ,l_comments IN OUT VARCHAR2);
PROCEDURE get_hrch_name(v_resource_id IN VARCHAR2
                    ,v_toc_cd IN VARCHAR2
                    ,v_tier_cd IN VARCHAR2
                    ,l_tier_org_nm OUT VARCHAR2
                    );
PROCEDURE get_hrch_name_psn(v_resource_id IN VARCHAR2
                    ,v_toc_cd IN VARCHAR2
                    ,v_tier_cd IN VARCHAR2
                    ,l_tier_org_nm OUT VARCHAR2
                    ,l_tier_psn_cd OUT VARCHAR2
                    );
FUNCTION get_sfdc_id(v_psn_cd IN VARCHAR) RETURN VARCHAR2;


END CANON_e404_sf_user_pkg;
/

create or replace PACKAGE BODY              CANON_e404_sf_user_pkg
AS

PROCEDURE extract_users(x_return_status   OUT VARCHAR2
                       ,x_msg_data        OUT VARCHAR2
                       )
AS
    l_procedure_name VARCHAR2(25) := 'extract_users';
    
    CURSOR ins_user_cur IS
    SELECT distinct psn.psn_num employee_number--employee_number
        ,psn.psn_first_nm first_name --first_name
        ,psn.psn_last_nm last_name --last_name
        ,psn.psn_cd resource_id --resource_id
        ,psn.eml_addr email_address ---|| '.example.com' email_address ---THIS HAS TO BE CHANGED WHEN GOING TO PRD INSTANCE
        ,psn.hr_ttl_nm job_title --job title
        ,(CASE WHEN psn.psn_last_nm LIKE '%ADJ%' OR psn.psn_last_nm LIKE '%TERRITORY%'
             THEN
               NULL
             ELSE
               'TRUE'
           END) forecast_enabled --forcast_enabled
        ,(psn.first_line_addr || NVL(psn.scd_line_Addr,'') || NVL(psn.third_line_addr,'') || NVL(psn.frth_line_addr,'')) address --address
        ,psn.cty_addr city --city
        ,psn.st_cd state --state
        ,psn.ctry_cd country --country
        ,psn.post_cd postal_code --postal_code
        ,psn.hr_psn_cmpy_nm company_name --'Canon Solutions America' --company_name
        ,(CASE WHEN psn.tm_zone_cd = 'EST' THEN 'America/New_York'
               WHEN psn.tm_zone_cd = 'PST' THEN 'America/LosAngeles'
               WHEN psn.tm_zone_cd = 'MST' THEN 'America/Denver'
               WHEN psn.tm_zone_cd = 'CST' THEN 'America/Indiana/Indianapolis'
               WHEN psn.tm_zone_cd = 'HST' THEN 'Pacific/Honolulu'
               ELSE 'America/New_York' END) timezone_id
        ,'en_US' locale --locale
        ,'en_us' language_used --language_used
        ,'ISO-8859-1' email_encoding
        ,substr(REPLACE(REPLACE(trim(NVL(psn.psn_first_nm,'') || NVL(psn.psn_last_nm,'')),' ',''),'.',''),1,40) community_nickname --community_nickname
        ,substr(REPLACE(REPLACE(trim(NVL(psn.psn_first_nm,'') || NVL(psn.psn_last_nm,'')),' ',''),'.',''),1,8) user_alias --user_alias
        ,'TRUE' user_permission_offline
        ,(CASE WHEN psn.psn_last_nm LIKE '%ADJ%' OR psn.psn_last_nm LIKE '%TERRITORY%'
              THEN
                'FALSE'
              ELSE
                'TRUE'
        END) is_active
        ,cb.coa_br_nm oracle_gl_branch --psn.coa_br_cd oracle_gl_branch --oracle_gl_branch --not available in EXTN5
        ,psn.line_biz_tp_cd ln_of_bus --line_of_business
        ,'I' status_flag
        ,SYSDATE oracle_load_date --oracle_load_date
        ,NULL sf_user_id --sf_user_id
        ,NULL sf_update_date --sf_update_date
        ,NULL comments --comments
    FROM s21_psn psn --S21_PSN_rg psn
         ,ds_org_resrc_rev dorrv
         ,coa_br cb
   WHERE psn.glbl_cmpy_cd = 'ADB' 
     AND dorrv.glbl_cmpy_cd = 'ADB'
     AND psn.ezcancelflag = '0'
     AND dorrv.ezcancelflag = '0'
     AND cb.glbl_cmpy_cd = 'ADB'
     AND psn.LINE_BIZ_TP_CD = 'ESS'
     AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(psn.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(psn.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
     AND dorrv.psn_cd(+) = psn.psn_cd
     AND dorrv.rev_acct_tp_cd(+) = 'REV'
     AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dorrv.eff_FROM_DT(+),'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dorrv.eff_THRU_DT(+), 'yyyymmdd'),SYSDATE))
     AND dorrv.coa_br_cd = cb.coa_br_cd(+)
     --to make sure if PSN belongs to a sales org
    AND EXISTS (SELECT 1
                  FROM org_func_asg ofa
                     ,toc t
                     ,org_stru_tp ost
                     ,biz_area_org bao
                     ,ds_org_unit dou
                     ,org_func_role_tp ofrt
                WHERE ofa.glbl_cmpy_cd = 'ADB'
				  AND ofa.ezcancelflag = '0'
                  AND ofa.psn_cd = psn.psn_cd
                  AND ofa.toc_cd = t.toc_cd
                  AND ofrt.ezcancelflag = '0'
                  AND ofrt.glbl_cmpy_cd = 'ADB'
                  AND ofrt.org_func_role_tp_cd = t.org_func_role_tp_cd
                  AND (ofrt.sls_rep_flg = 'Y' OR ofrt.mgr_flg = 'Y' OR ofrt.SPCL_FLG = 'Y')
                  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ofa.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ofa.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                  AND dou.org_cd = t.org_cd
                  AND dou.org_stru_tp_cd = ost.org_stru_tp_cd
                  AND dou.glbl_cmpy_cd = 'ADB'
				  AND dou.ezcancelflag = '0'
                  AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
                  AND ost.glbl_cmpy_cd = 'ADB'
				  AND ost.ezcancelflag = '0'
                  AND bao.org_stru_tp_cd = ost.org_stru_tp_cd
				  AND dou.first_org_cd = bao.biz_area_org_cd
                  AND bao.glbl_cmpy_cd = 'ADB'
				  AND bao.ezcancelflag = '0'
                  AND bao.sls_flg = 'Y')                
    --to make sure the org does not exists in the valueset
    AND NOT EXISTS (select val_tbl.val2
                    from canon_s21_cd_val_tbl val_tbl
                         ,canon_s21_cd_tbl cd_tbl
                         ,org_func_asg ofa
                          ,toc t
                         ,ds_org_unit dou
                    where ofa.psn_cd = psn.psn_cd
                      and ofa.toc_cd = t.toc_cd 
                      and t.org_cd = dou.org_cd
                      and cd_tbl.cd_id = val_tbl.cd_id
					  AND val_tbl.is_active IS NULL
                    and cd_tbl.cd_name = 'CANON_E404_ROLE_EXCLU_FOR_USR'
                    and TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(val_tbl.val76,'dd-MON-yy'),SYSDATE)) AND TRUNC(NVL(to_date(val_tbl.val77, 'dd-MON-yy'),SYSDATE))
                    and dou.org_nm = val_tbl.val2
                    )
    --to make sure if the PSN is not already in user mapping
     AND NOT EXISTS (SELECT '1'
                     FROM canon_e404_sf_user_mapping_tbl e404
                    WHERE e404.employee_number = psn.psn_num
                      AND sf_user_id IS NOT NULL
                   );
--     to make sure the gl_branch is a valid us_gl_branch
--     AND EXISTS (SELECT coa_br_cd
--                  FROM coa_br cb
--                      ,us_gl_branch_vs vs
--                 WHERE cb.coa_br_cd = psn.coa_br_cd
--                   AND vs.name = cb.coa_br_nm
--                   AND cb.glbl_cmpy_cd = 'ADB'
--                   AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(vs.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(vs.eff_To_DT, 'yyyymmdd'),SYSDATE))
--               );



CURSOR updt_user_cur IS
     SELECT psn.psn_cd rid
           ,psn.psn_first_nm fname
           ,psn.psn_last_nm lname
           ,psn.eml_addr emailAddr 
           ,psn.hr_ttl_nm jTitle --jt.job_ttl_nm jTitle
           ,(CASE WHEN psn.psn_last_nm LIKE '%ADJ%' OR psn.psn_last_nm LIKE '%TERRITORY%'
             THEN
               NULL
             ELSE
               'TRUE'
           END) frcstEnbl
           ,(psn.first_line_addr || NVL(psn.scd_line_Addr,'') || NVL(psn.third_line_addr,'') || NVL(psn.frth_line_addr,'')) haddr
           ,psn.cty_addr hcity
           ,psn.st_cd hstate
           ,psn.ctry_cd hcountry
           ,psn.post_cd hpc
           ,SUBSTR(REPLACE(REPLACE(trim(NVL(psn.psn_first_nm,'') || NVL(psn.psn_last_nm,'')),' ',''),'.',''),1,40) cName
           ,SUBSTR(REPLACE(REPLACE(trim(NVL(psn.psn_first_nm,'') || NVL(psn.psn_last_nm,'')),' ',''),'.',''),1,8) alias1
           ,(CASE WHEN psn.psn_last_nm LIKE '%ADJ%' OR psn.psn_last_nm LIKE '%TERRITORY%' THEN 'FALSE'
		          WHEN trunc(sysdate) > trunc(to_date(psn.eff_THRU_DT, 'yyyymmdd')) THEN 'FALSE'
            ELSE
              'TRUE'
           END) isActv
           ,NULL gl_branch --psn.coa_br_cd gl_branch
           ,psn.line_biz_tp_cd ln_of_bus --lbt.line_biz_tp_nm ln_of_bus
           ,psn.hr_psn_cmpy_nm cmpyNm
           ,e404.*
      FROM canon_e404_sf_user_mapping_tbl e404
           ,s21_psn psn
     WHERE psn.psn_cd = e404.resource_id
       AND psn.glbl_cmpy_cd = 'ADB'
       AND psn.ezcancelflag = '0'
       AND psn.line_biz_tp_cd = 'ESS'
      -- AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(psn.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(psn.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
       AND e404.employee_number = psn.psn_num
       AND e404.sf_user_id IS NOT NULL;
--       AND psn.coa_br_cd IN (SELECT coa_br_cd
--                            FROM coa_br cb
--                                 ,us_gl_branch_vs vs
--                            WHERE vs.name = cb.coa_br_nm
--                              AND cb.glbl_cmpy_cd = 'ADB'
--                              AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(vs.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(vs.eff_To_DT, 'yyyymmdd'),SYSDATE))
--                          );

CURSOR hrch_tier_cur IS
    SELECT ohsd.org_tier_cd tier_cd
           ,ohsd.org_hrch_stru_dfn_nm dfn_nm
           ,(CASE WHEN cmpy_flg = 'Y'    THEN 'C'
                  WHEN bu_flg = 'Y'      THEN 'BU'
                  WHEN zn_flg = 'Y'      THEN 'VP'
                  WHEN rg_flg = 'Y'      THEN 'R'
                  WHEN sub_rg_flg = 'Y'  THEN 'SR'
                  WHEN br_flg = 'Y'      THEN 'B'
                  WHEN mgr_flg = 'Y'     THEN 'M'
                  WHEN rep_flg = 'Y'     THEN 'R'
              ELSE ''
             END) org_type
     FROM org_stru_tp          ost
          ,biz_area_org        bao
          ,org_hrch_stru_dfn    ohsd
          ,stru_dfn             sd
    WHERE 1 = 1
      AND ost.trty_stru_flg = 'N'
      AND ost.glbl_cmpy_cd = 'ADB'
	  AND ost.ezcancelflag = '0'
      AND bao.sls_flg = 'Y'
      AND bao.org_stru_tp_cd = ost.org_stru_tp_cd
      AND bao.glbl_cmpy_cd = 'ADB'
	  AND bao.ezcancelflag = '0'
      AND ohsd.glbl_cmpy_cd = 'ADB'
	  AND ohsd.ezcancelflag = '0'
      AND ohsd.biz_area_org_cd = bao.biz_area_org_cd
      AND sd.glbl_cmpy_cd(+) = 'ADB'
	  AND sd.ezcancelflag(+) = '0'
     AND sd.stru_dfn_cd(+) = ohsd.stru_dfn_cd
     AND UPPER(ohsd.org_hrch_stru_dfn_nm) IN ('BRANCH','REGION','ZONE','MANAGER')
     ORDER by to_number(ohsd.org_tier_cd);

l_comments          VARCHAR2(32767) := NULL;
l_mapped_org        VARCHAR2(1000) := NULL;
l_role                VARCHAR2(500) := NULL;
l_sf_role_id        VARCHAR2(20) := NULL;
l_sf_profile_id        VARCHAR2(20) := NULL;
l_br_org_nm            VARCHAR2(500) := NULL;
l_region_org_nm        VARCHAR2(500) := NULL;
l_zone_org_nm        VARCHAR2(500) := NULL;
l_mgr_org_nm        VARCHAR2(500) := NULL;
l_mgr_psn_cd        VARCHAR2(100) := NULL;
l_dir_psn_cd        VARCHAR2(100) := NULL;
l_mgr_sfdc_id       VARCHAR(20)   := NULL;
l_dir_sfdc_id       VARCHAR2(20)  := NULL;
l_u_comments        VARCHAR2(100) := NULL;
l_u_mapped_org        VARCHAR2(100) := NULL;
l_u_br                 VARCHAR2(100) := NULL;
l_u_region             VARCHAR2(100) := NULL;
l_u_zone             VARCHAR2(100) := NULL;
l_u_mgr_br             VARCHAR2(100) := NULL;
l_u_mgr_psn_cd        VARCHAR2(100) := NULL;
l_u_dir_psn_cd        VARCHAR2(100) := NULL;
l_u_sf_profile         VARCHAR2(100) := NULL;
l_u_sf_role             VARCHAR2(100) := NULL;
l_u_role             VARCHAR2(100) := NULL;
l_u_mgr_sfdc_id         VARCHAR2(100) := NULL;
l_u_dir_sfdc_id         VARCHAR2(100) := NULL;
l_user_del_count    NUMBER ;
l_update_flag       NUMBER;
l_toc_cd        VARCHAR2(30) := NULL;
l_inserted_count VARCHAR2(5) := NULL;
l_updated_count VARCHAR2(5) := NULL;
l_instance VARCHAR2(20) := NULL; 
l_val_flag VARCHAR2(10) := 'TRUE';

BEGIN

  DELETE
    FROM canon_e404_sf_user_mapping_tbl e404
   WHERE e404.sf_user_id IS NULL
     AND status_flag IN ('I','U','E');

  dbms_output.put_line('Deleted records to reprocess :' ||SQL%ROWCOUNT);
  canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Deleted records to reprocess :' ||SQL%ROWCOUNT,NULL,NULL,NULL,NULL);


  l_user_del_count := SQL%ROWCOUNT;

  IF l_user_del_count > 0 THEN
    COMMIT;
  END IF;

  --to reprocess error records

  UPDATE canon_e404_sf_user_mapping_tbl e404
     SET status_flag = 'U'
         ,oracle_load_date = sysdate
    WHERE sf_user_id IS NOT NULL
      AND status_flag = 'E';
 dbms_output.put_line('Update flags to U for any previously errored records, to reprocess :' ||SQL%ROWCOUNT);
 canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_procedure_name,'INFO','Updated flag to U to reprocess :' ||SQL%ROWCOUNT,NULL,NULL,NULL,NULL);

   IF (SQL%ROWCOUNT > 0) THEN
    COMMIT;
   END IF;

    BEGIN
    SELECT instance_name 
      INTO l_instance
      FROM CANON_E404_INSTANCE_NAME_V;
   EXCEPTION WHEN OTHERS THEN
    l_instance := 'NA';
    END;
	
   --loop thru the insert cursor and get assigned role, org and toc for each PSN
   --if a PSN is multiple org's then check the map_grp value set and get the mapped org
   --if a PSN is assigned to multiple org's and no mapped org available update the comments accordingly
   --if a PSN is not assigned to any org, update the comments accordingly.
   FOR ins_user_rec IN ins_user_cur
   LOOP
--    dbms_output.put_line('PSN_CD :' ||ins_user_rec.resource_id);
    --reset
        l_comments := NULL;
        l_br_org_nm := NULL;
        l_region_org_nm := NULL;
        l_zone_org_nm := NULL;
        l_mgr_org_nm := NULL;
        l_mgr_psn_cd := NULL;
        l_dir_psn_cd := NULL;
        l_mgr_sfdc_id := NULL;
        l_dir_sfdc_id := NULL;
        l_mapped_org := NULL;
        l_role := NULL;
        l_sf_profile_id := NULL;
        l_sf_role_id := NULL;
        l_toc_cd := NULL;
        l_inserted_count := NULL;
        l_updated_count := NULL;
        
        -- get org name the resource is associated with
        get_org_code(ins_user_rec.resource_id, l_mapped_org, l_comments);
--        dbms_output.put_line('Insert : l_mapped_org : ' ||l_mapped_org);

        BEGIN
        
        SELECT t.toc_cd
          INTO l_toc_cd
          FROM toc t
               ,org_func_asg ofa
         WHERE ofa.psn_cd = ins_user_rec.resource_id
           AND ofa.glbl_cmpy_cd = 'ADB'
		   AND ofa.ezcancelflag = '0'
           AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ofa.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ofa.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
           AND ofa.toc_cd = t.toc_cd
           AND t.org_cd = l_mapped_org
           AND t.glbl_cmpy_cd = 'ADB'
		   AND t.ezcancelflag = '0';
    EXCEPTION
		WHEN NO_DATA_FOUND THEN	
			l_toc_cd := NULL;
        WHEN OTHERS THEN
            l_toc_cd := NULL;
            canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SQLERRM);
    END;
    
        --get hierarchy org names and mgr,dir psn cd
        FOR hrch_tier_rec IN hrch_tier_cur
        LOOP

            IF (UPPER(hrch_tier_rec.dfn_nm) LIKE 'ZONE') THEN
                get_hrch_name(ins_user_rec.resource_id, l_toc_cd, hrch_tier_rec.tier_cd, l_zone_org_nm);
            ELSIF (UPPER(hrch_tier_rec.dfn_nm) LIKE 'BRANCH') THEN
                get_hrch_name_psn(ins_user_rec.resource_id, l_toc_cd, hrch_tier_rec.tier_cd, l_br_org_nm, l_dir_psn_cd);
            ELSIF (UPPER(hrch_tier_rec.dfn_nm) LIKE 'REGION') THEN
                get_hrch_name(ins_user_rec.resource_id, l_toc_cd, hrch_tier_rec.tier_cd, l_region_org_nm);
            ELSIF (UPPER(hrch_tier_rec.dfn_nm) LIKE 'MANAGER') THEN
                get_hrch_name_psn(ins_user_rec.resource_id, l_toc_cd, hrch_tier_rec.tier_cd, l_mgr_org_nm, l_mgr_psn_cd);
            END IF;
        END LOOP;
        
--        dbms_output.put_line('Insert : l_zone_org_nm : ' ||l_zone_org_nm);
--        dbms_output.put_line('Insert : l_br_org_nm : ' ||l_br_org_nm);
--        dbms_output.put_line('Insert : l_dir_psn_cd : ' ||l_dir_psn_cd);
--        dbms_output.put_line('Insert : l_region_org_nm : ' ||l_region_org_nm);
--        dbms_output.put_line('Insert : l_mgr_org_nm : ' ||l_mgr_org_nm);
--        dbms_output.put_line('Insert : l_mgr_psn_cd : ' ||l_mgr_psn_cd);
        

         --get sf_user_id for mgr and dir
         l_mgr_sfdc_id := get_sfdc_id(l_mgr_psn_cd);
         l_dir_sfdc_id := get_sfdc_id(l_dir_psn_cd);
         
--         dbms_output.put_line('Insert : l_mgr_sfdc_cd : ' ||l_mgr_sfdc_id);
--         dbms_output.put_line('Insert : l_mgr_sfdc_id : ' ||l_mgr_sfdc_id);
        

        --get role and sf_profile, sf_role_id
        IF (l_mapped_org IS NOT NULL) THEN
            get_role_name(ins_user_rec.resource_id, l_mapped_org, l_role, l_comments);
            get_sf_profile_id(ins_user_rec.last_name, l_role, l_sf_profile_id, l_comments);
            get_sf_role_id(l_mapped_org, ins_user_rec.last_name, ins_user_rec.resource_id,l_sf_role_id, l_comments);
            
        END IF;
        
--        dbms_output.put_line('Insert : l_role_name : ' ||l_role);
--        dbms_output.put_line('Insert : l_profile_id : ' ||l_sf_profile_id);
--        dbms_output.put_line('Insert : l_sf_role_id : ' ||l_sf_role_id);
        

        BEGIN
         INSERT INTO canon_e404_sf_user_mapping_tbl
                (
                employee_number,
                first_name,
                last_name,
                resource_id,
                email_address,
                user_name,
                job_title,
                group_id,
                sales_comp_role,
                sf_role_id,
                forecast_enabled,
                address,
                city,
                state,
                country,
                postal_code,
                company_name,
                sf_timezone_id,
                locale,
                language_used,
                email_encoding,
                sf_profile_id,
                community_nickname,
                user_alias,
                user_permission_offline,
                is_active,
                status_flag,
                oracle_load_date,
                sf_user_id,
                sf_update_date,
                comments,
                oracle_branch,
                oracle_region,
                oracle_zone,
                oracle_gl_branch,
                oracle_mgr_branch,
                line_of_business,
                mgr_empid,
                mgr_sfdcid,
                dir_empid,
                dir_sfdcid
              )
              VALUES
              (
                ins_user_rec.employee_number,
                ins_user_rec.first_name,
                ins_user_rec.last_name,
                ins_user_rec.resource_id,
                (CASE WHEN l_instance <> 'CSAPRD' AND ins_user_rec.email_address IS NOT NULL THEN (ins_user_rec.email_address || '.' || l_instance) 
                      WHEN l_instance = 'CSAPRD' AND ins_user_rec.email_address IS NOT NULL THEN ins_user_rec.email_address 
                      END),
                (CASE WHEN l_instance <> 'CSAPRD' AND ins_user_rec.email_address IS NOT NULL THEN (ins_user_rec.email_address || '.' || l_instance) 
                      WHEN l_instance = 'CSAPRD' AND ins_user_rec.email_address IS NOT NULL THEN ins_user_rec.email_address
                      END),
                ins_user_rec.job_title,
                l_mapped_org,
                l_role,
                l_sf_role_id,
                ins_user_rec.forecast_enabled,
                ins_user_rec.address,
                ins_user_rec.city,
                ins_user_rec.state,
                ins_user_rec.country,
                ins_user_rec.postal_code,
                ins_user_rec.company_name,
                ins_user_rec.timezone_id,
                ins_user_rec.locale,
                ins_user_rec.language_used,
                ins_user_rec.email_encoding,
                l_sf_profile_id,
                ins_user_rec.community_nickname,
                ins_user_rec.user_alias,
                ins_user_rec.user_permission_offline,
                ins_user_rec.is_active,
                ins_user_rec.status_flag,
                ins_user_rec.oracle_load_date,
                ins_user_rec.sf_user_id,
                ins_user_rec.sf_update_date,
                DECODE(l_comments,NULL, ins_user_rec.comments,l_comments)
                ,l_br_org_nm,
                l_region_org_nm,
                l_zone_org_nm,
                ins_user_rec.oracle_gl_branch,
                l_mgr_org_nm,
                ins_user_rec.ln_of_bus,
                l_mgr_psn_cd,
                l_mgr_sfdc_id,
                l_dir_psn_cd,
                l_dir_sfdc_id
              );
--              dbms_output.put_line('Inserted : ' || ins_user_rec.employee_number);
        EXCEPTION
            WHEN OTHERS THEN
              dbms_output.put_line('Insert : ' ||SQLERRM );
              canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));

        END;
--        END LOOP;
   END LOOP;

   COMMIT;

   SELECT count(*)
     INTO l_inserted_count
     FROM canon_E404_sf_user_mapping_tbl
    WHERE status_flag = 'I'
       AND sf_user_id IS NULL;

   dbms_output.put_line('# of records to insert: ' ||l_inserted_count);
   canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of records to insert: ' ||l_inserted_count,NULL,NULL,NULL,NULL);


--------------------------UPDATE BLOCK-------------------------------------------------------------------------------

    FOR updt_user_rec IN updt_user_cur
      LOOP
        l_update_flag       := 0;
		l_val_flag := 'TRUE';


        IF nvl(updt_user_rec.first_name,'-1') <> nvl(updt_user_rec.fname,'-1') THEN
          l_update_flag := l_update_flag + 1;
          dbms_output.put_line('first_name: ' ||updt_user_rec.first_name);
        dbms_output.put_line('fname : ' ||updt_user_rec.fname);
        END IF;

        IF nvl(updt_user_rec.last_name,'-1') <> nvl(updt_user_rec.lname,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF(l_instance = 'CSAPRD') THEN
          IF nvl(updt_user_rec.email_address,'-1') <> nvl(updt_user_rec.emailAddr,'-1') THEN
            l_update_flag := l_update_flag + 1;
          END IF;
        ELSE
           IF nvl(updt_user_rec.email_address,'-1') <> nvl((CASE WHEN updt_user_rec.emailAddr IS NULL THEN NULL ELSE (updt_user_rec.emailAddr|| '.' || l_instance)END),'-1') THEN
            l_update_flag := l_update_flag + 1;
           END IF;
        END IF;

        IF nvl(updt_user_rec.job_title,'-1') <> nvl(updt_user_rec.jTitle,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.forecast_enabled,'-1') <> nvl(updt_user_rec.frcstEnbl,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.address,'-1') <> nvl(updt_user_rec.haddr,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.city,'-1') <> nvl(updt_user_rec.hcity,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.state,'-1') <> nvl(updt_user_rec.hstate,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.country,'-1') <> nvl(updt_user_rec.hcountry,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.postal_code,'-1') <> nvl(updt_user_rec.hpc,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;


        IF nvl(updt_user_rec.community_nickname,'-1') <> nvl(updt_user_rec.cName,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.user_alias,'-1') <> nvl(updt_user_rec.alias1,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.is_active,'-1') <> nvl(updt_user_rec.isActv,'-1') THEN
          l_update_flag := l_update_flag + 1;
		  l_val_flag := updt_user_rec.isActv;
        END IF;

        --reset
        l_u_comments    := NULL;
        l_u_mapped_org := NULL;
        l_u_br := NULL;
        l_u_region := NULL;
        l_u_zone := NULL;
        l_u_mgr_br := NULL;
        l_u_mgr_psn_cd    := NULL;
        l_u_dir_psn_cd    := NULL;
        l_u_sf_profile     := NULL;
        l_u_sf_role         := NULL;
        l_u_role         := NULL;
        l_u_mgr_sfdc_id     := NULL;
        l_u_dir_sfdc_id     := NULL;
        l_toc_cd      := NULL;

        BEGIN
            SELECT t.toc_cd
              INTO l_toc_cd
              FROM toc t
                   ,org_func_asg ofa
             WHERE ofa.psn_cd = updt_user_rec.resource_id
               AND ofa.glbl_cmpy_cd = 'ADB'
			   AND ofa.ezcancelflag = '0'
               AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ofa.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ofa.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
               AND ofa.toc_cd = t.toc_cd
               AND t.glbl_cmpy_cd = 'ADB'
			   AND t.ezcancelflag = '0';
        EXCEPTION
			WHEN NO_DATA_FOUND THEN	
				l_toc_cd := NULL;
            WHEN OTHERS THEN
                l_toc_cd := NULL;
                canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
        END;

         FOR hrch_tier_rec IN hrch_tier_cur
         LOOP

                IF (UPPER(hrch_tier_rec.dfn_nm) LIKE 'ZONE') THEN
                    get_hrch_name(updt_user_rec.resource_id, l_toc_cd, hrch_tier_rec.tier_cd, l_u_zone);
                ELSIF (UPPER(hrch_tier_rec.dfn_nm) LIKE 'BRANCH') THEN
                    get_hrch_name_psn(updt_user_rec.resource_id, l_toc_cd, hrch_tier_rec.tier_cd, l_u_br, l_u_dir_psn_cd);
                ELSIF (UPPER(hrch_tier_rec.dfn_nm) LIKE 'REGION') THEN
                    get_hrch_name(updt_user_rec.resource_id, l_toc_cd, hrch_tier_rec.tier_cd, l_u_region);
                ELSIF (UPPER(hrch_tier_rec.dfn_nm) LIKE 'MANAGER') THEN
                    get_hrch_name_psn(updt_user_rec.resource_id, l_toc_cd, hrch_tier_rec.tier_cd, l_u_mgr_br, l_u_mgr_psn_cd);
                END IF;
         END LOOP;

        --get sf_user_id for mgr and dir
        IF (l_u_mgr_psn_cd IS NOT NULL) THEN
            l_u_mgr_sfdc_id := get_sfdc_id(l_mgr_psn_cd);
        END IF;
        IF (l_u_dir_psn_cd IS NOT NULL ) THEN
            l_dir_sfdc_id := get_sfdc_id(l_dir_psn_cd);
        END IF;

--        dbms_output.put_line('l_u_mgr_sfdc_id: ' );
        -- get org name the resource is associated with
        get_org_code(updt_user_rec.rid, l_u_mapped_org, l_u_comments);

        --get role and sf_profile, sf_role_id
        If (l_u_mapped_org IS NOT NULL) THEN
            get_role_name(updt_user_rec.rid, l_u_mapped_org, l_u_role, l_u_comments);
            get_sf_profile_id(updt_user_rec.last_name, l_u_role, l_u_sf_profile, l_u_comments);
            get_sf_role_id(l_u_mapped_org, updt_user_rec.last_name, updt_user_rec.rid, l_u_sf_role,l_u_comments);
        END IF;

        IF nvl(updt_user_rec.group_id,'-1') <> nvl(l_u_mapped_org,'-1') THEN
            l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.oracle_branch,'-1') <> nvl(l_u_br,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.oracle_region,'-1') <> nvl(l_u_region,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.oracle_zone,'-1') <> nvl(l_u_zone,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.oracle_mgr_branch,'-1') <> nvl(l_u_mgr_br,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.oracle_gl_branch,'-1') <> nvl(updt_user_rec.gl_branch,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.line_of_business,'-1') <> nvl(updt_user_rec.ln_of_bus,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.sf_role_id,-9999) <> nvl(l_u_sf_role,-9999) THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.sales_comp_role,'-1') <> nvl(l_u_role,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.mgr_empid,'-1') <> nvl(l_u_mgr_psn_cd,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;
        IF nvl(updt_user_rec.mgr_sfdcid,'-1') <> nvl(l_u_mgr_sfdc_id,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;
        IF nvl(updt_user_rec.dir_empid,'-1') <> nvl(l_u_dir_psn_cd,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;
        IF nvl(updt_user_rec.dir_sfdcid,'-1') <> nvl(l_u_dir_sfdc_id,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;
        IF nvl(updt_user_rec.company_name,'-1') <> nvl(updt_user_rec.cmpyNm,'-1') THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF nvl(updt_user_rec.sf_profile_id,-9999) <> nvl(l_u_sf_profile,-9999) THEN
          l_update_flag := l_update_flag + 1;
        END IF;

        IF (l_update_flag > 0) THEN
        UPDATE canon_e404_sf_user_mapping_tbl e404
           SET first_name = updt_user_rec.fname,
               last_name = updt_user_rec.lname,
               email_address = (CASE WHEN l_instance <> 'CSAPRD' THEN updt_user_rec.emailAddr || '.' || l_instance ELSE updt_user_rec.emailAddr END),
               user_name = (CASE WHEN l_instance <> 'CSAPRD' THEN updt_user_rec.emailAddr || '.' || l_instance ELSE updt_user_rec.emailAddr END),
               job_title = updt_user_rec.jTitle,
               group_id = l_u_mapped_org,
               forecast_enabled = updt_user_rec.frcstEnbl,
               sf_role_id = (CASE WHEN l_val_flag = 'FALSE' THEN updt_user_rec.sf_role_id ELSE l_u_sf_role END),
               sales_comp_role = l_u_role,
               address = updt_user_rec.haddr,
               city = updt_user_rec.hcity,
               state = updt_user_rec.hstate,
               country = updt_user_rec.hcountry,
               postal_code = updt_user_rec.hpc,
               sf_profile_id = (CASE WHEN l_val_flag = 'FALSE' THEN updt_useR_rec.sf_profile_id ELSE l_u_sf_profile END),
               community_nickname = updt_user_rec.cName,
               user_alias = updt_user_rec.alias1,
               is_active = updt_user_rec.isActv,
               status_flag = 'U',
               oracle_load_date = SYSDATE,
               comments = NULL,
               oracle_branch = l_u_br,
               oracle_region = l_u_region,
               oracle_zone = l_u_zone,
               oracle_gl_branch = updt_user_rec.gl_branch,
               oracle_mgr_branch = l_u_mgr_br,
               line_of_business = updt_user_rec.ln_of_bus,
               mgr_empid = l_u_mgr_psn_cd,
               mgr_sfdcid = l_u_mgr_sfdc_id,
               dir_empid = l_u_dir_psn_cd,
               dir_sfdcid = l_u_dir_sfdc_id,
               company_name = 'Canon Solutions America'
         WHERE e404.sf_user_id = updt_user_rec.sf_user_id;
         END IF;
    END LOOP;

    COMMIT;

    SELECT count(*)
      INTO l_updated_count
      FROM canon_e404_sf_user_mapping_tbl
     WHERE status_flag = 'U'
       AND sf_user_id IS NOT NULL;

    dbms_output.put_line('# of records to update: ' ||l_updated_count);
	canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'INFO','# of records to update: ' ||l_updated_count,NULL,NULL,NULL,NULL);
	
	x_return_status := 'S';

EXCEPTION
WHEN OTHERS THEN
    ROLLBACK;
    x_return_status := 'E';
    x_msg_data      := 'Unexpected Error:'|| SQLERRM;
    dbms_output.put_line('Error: ' ||SQLERRM);
    canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
END extract_users;

PROCEDURE get_org_code(v_resource_id IN VARCHAR2
                        ,l_mapped_org OUT VARCHAR2
                        ,l_comments OUT VARCHAR2)
AS
l_assigned_orgs     NUMBER;
l_procedure_name VARCHAR2(25) := 'get_org_code';
BEGIN
    BEGIN
        SELECT COUNT(DISTINCT dou.org_cd)
          INTO l_assigned_orgs
          FROM org_func_asg ofa
               ,toc t
               ,ds_org_unit dou
               ,org_stru_tp        ost
               ,biz_area_org      bao
               ,org_hrch_stru_dfn ohsd
               ,stru_dfn          sd
        WHERE ofa.psn_cd = v_resource_id
          AND ofa.glbl_cmpy_cd = 'ADB'
		  AND ofa.ezcancelflag = '0'
          AND t.toc_cd = ofa.toc_cd
          AND t.glbl_cmpy_cd = 'ADB'
		  AND t.ezcancelflag = '0'
          AND t.org_cd = dou.org_cd
          AND dou.glbl_cmpy_cd = 'ADB'
		  AND dou.ezcancelflag = '0'
          AND dou.org_stru_tp_cd = ost.org_stru_tp_cd
          AND ost.glbl_cmpy_cd = 'ADB'
          AND bao.org_stru_tp_cd = ost.org_stru_tp_cd
          AND bao.glbl_cmpy_cd = 'ADB'
		  AND bao.ezcancelflag = '0'
          AND bao.sls_flg = 'Y'
          AND ohsd.biz_area_org_cd = bao.biz_area_org_cd
          AND ohsd.glbl_cmpy_cd = 'ADB'
		  AND ohsd.ezcancelflag = '0'
          AND dou.org_tier_cd = ohsd.org_tier_cd
		  AND dou.first_org_cd = bao.biz_area_org_cd
          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ohsd.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ohsd.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
          AND sd.stru_dfn_cd(+) = ohsd.stru_dfn_cd
          AND sd.glbl_cmpy_cd(+) = 'ADB'
		  AND sd.ezcancelflag(+) = '0'
          AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE));
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            l_assigned_orgs := 0;
    END;

    --if PSN is assigned to multiple orgs
    IF (l_assigned_orgs > 1 ) THEN
        BEGIN
            SELECT dou.org_cd
              INTO l_mapped_org
              FROM  org_func_asg ofa
                    ,toc t
                    ,ds_org_unit dou
                   ,org_stru_tp        ost
                   ,biz_area_org      bao
                   ,org_hrch_stru_dfn ohsd
                   ,stru_dfn          sd
                   ,(SELECT val1 employee_number, val2 group_name
                      FROM canon_s21_cd_val_tbl val_tbl
                           ,canon_s21_cd_tbl cd_tbl
                       WHERE cd_tbl.cd_id = val_tbl.cd_id
					     AND val_tbl.is_active IS NULL
                         AND CD_TBL.CD_NAME = 'CANON_E404_USER_ORG_MAPPING'
                         and TRUNC(SYSDATE) BETWEEN TRUNC(NVL(val_tbl.val76,SYSDATE)) AND TRUNC(NVL(val_tbl.val77,SYSDATE))
                     )grp_map 
            WHERE ofa.psn_cd = v_resource_id
              AND ofa.glbl_cmpy_cd = 'ADB'
			  AND ofa.ezcancelflag = '0'
              AND ofa.toc_cd = t.toc_cd
              AND t.org_cd = dou.org_cd
              AND dou.org_stru_tp_cd = ost.org_stru_tp_cd
              AND ost.glbl_cmpy_cd = 'ADB'
			  AND ost.ezcancelflag = '0'
              AND bao.org_stru_tp_cd = ost.org_stru_tp_cd
              AND bao.glbl_cmpy_cd = 'ADB'
			  AND bao.ezcancelflag = '0'
              AND bao.sls_flg = 'Y'
              AND ohsd.biz_area_org_cd = bao.biz_area_org_cd
              AND ohsd.glbl_cmpy_cd = 'ADB'
			  AND ohsd.ezcancelflag = '0'
              AND dou.org_tier_cd = ohsd.org_tier_cd
			  AND dou.first_org_cd = bao.biz_area_org_cd
              AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ohsd.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ohsd.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
              AND sd.stru_dfn_cd(+) = ohsd.stru_dfn_cd
              AND sd.glbl_cmpy_cd(+) = 'ADB'
			  AND sd.ezcancelflag(+) = '0'
              AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
              AND grp_map.employee_number = v_resource_id
              AND grp_map.group_name = dou.org_nm;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                l_mapped_org := NULL;
                l_comments := 'Multiple Org''s assigned. No mapped org found in E404_SF_GRP_MAP_VS for PSN';
                dbms_output.put_line('Multiple Org''s assigned. No mapped org found in E404_SF_GRP_MAP_VS for PSN');

        END;
    ELSIF (l_assigned_orgs = 1) THEN
        BEGIN
            SELECT dou.org_cd
              INTO l_mapped_org
              FROM  org_func_asg ofa
                    ,toc t
                    ,ds_org_unit dou
                   ,org_stru_tp        ost
                   ,biz_area_org      bao
                   ,org_hrch_stru_dfn ohsd
                   ,stru_dfn         sd
            WHERE ofa.psn_cd = v_resource_id
              AND ofa.glbl_cmpy_cd = 'ADB'
			  AND ofa.ezcancelflag = '0'
              AND ofa.toc_cd = t.toc_cd
              AND t.org_cd = dou.org_cd
              AND dou.org_stru_tp_cd = ost.org_stru_tp_cd
              AND ost.glbl_cmpy_cd = 'ADB'
			  AND ost.ezcancelflag = '0'
              AND bao.org_stru_tp_cd = ost.org_stru_tp_cd
              AND bao.glbl_cmpy_cd = 'ADB'
			  AND bao.ezcancelflag = '0'
              AND bao.sls_flg = 'Y'
              AND ohsd.biz_area_org_cd = bao.biz_area_org_cd
              AND ohsd.glbl_cmpy_cd = 'ADB'
			  AND ohsd.ezcancelflag = '0'
              AND dou.org_tier_cd = ohsd.org_tier_cd
			  AND dou.first_org_cd = bao.biz_area_org_cd
              AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(ohsd.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(ohsd.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
              AND sd.stru_dfn_cd(+) = ohsd.stru_dfn_cd
              AND sd.glbl_cmpy_cd(+) = 'ADB'
			  AND sd.ezcancelflag(+) = '0'
              AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE));
        EXCEPTION
            WHEN OTHERS THEN
                l_mapped_org := NULL;
                l_comments := 'Error fetching the org for PSN';
                dbms_output.put_line('Error fetching the org for PSN' ||SQLERRM);
        END;
    ELSIF (l_assigned_orgs = 0) THEN
        l_mapped_org := NULL;
        l_comments := 'No orgs assigned to PSN: ' ||v_resource_id;
        dbms_output.put_line('No orgs assigned to PSN');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        l_mapped_org := NULL;
    WHEN OTHERS THEN
    dbms_output.put_line('Error - GET_ORG_CODE: ' ||SQLERRM);
        canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
END get_org_code;

PROCEDURE get_role_name(v_resource_id IN VARCHAR2
                        ,v_org_name IN VARCHAR2
                        ,x_role        OUT VARCHAR2
                        ,x_comments  IN OUT VARCHAR2)
AS
    l_procedure_name VARCHAR2(25) := 'get_role_name';
BEGIN
    IF (v_org_name IS NOT NULL) THEN
        SELECT ofrt.org_func_role_tp_nm
          INTO x_role
          FROM org_func_role_tp ofrt
               ,org_func_asg ofa
               ,toc t
          WHERE ofa.psn_cd = v_resource_id
            AND ofa.toc_cd = t.toc_cd
            AND t.org_cd = v_org_name
            AND t.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
            AND ofrt.glbl_cmpy_cd = 'ADB'
			AND ofrt.ezcancelflag = '0'
			AND ofa.glbl_cmpy_cd = 'ADB'
			AND ofa.ezcancelflag = '0'
			AND t.glbl_cmpy_cd = 'ADB'
			AND t.ezcancelflag = '0';
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        x_role := NULL;
        IF (x_comments IS NULL) THEN
            x_comments := 'No org role assigned to PSN';
--            dbms_output.put_line('Error - GET_ROLE_NAME1: ' ||SQLERRM);
        ELSE
            x_comments := x_comments || '|No org role assigned to PSN';
--            dbms_output.put_line('Error: GET_ROLE_NAME2: ' ||SQLERRM);
         END IF;

    WHEN OTHERS THEN
        x_role := NULL;
        IF (x_comments IS NULL) THEN
            x_comments := 'Error fetching the org role for PSN';
--            dbms_output.put_line('Error - GET_ROLE_NAME3: ' ||SQLERRM);
        ELSE
            x_comments := x_comments || '|Error fetching the org role for PSN';
--            dbms_output.put_line('Error - GET_ROLE_NAME4: ' ||SQLERRM);
         END IF;

END get_role_name;

PROCEDURE get_sf_profile_id(v_last_name IN VARCHAR2
                            ,v_role        IN VARCHAR2
                            ,x_sf_profile_id OUT VARCHAR2
                            ,l_comments    IN OUT VARCHAR2)
AS
    l_procedure_name VARCHAR2(25) := 'get_sf_profile_id';
BEGIN
    IF (v_last_name like '%ADJ%' OR v_last_name like '%TERRITORY%') THEN
        SELECT e404_profile.profile_id
         INTO x_sf_profile_id
         FROM canon_e404_sf_profile_tbl e404_profile
        WHERE e404_profile.NAME = 'CBS Adjustment' ;
    ELSE
    --NOTE: using code table for testing only - buth THIS NEEDS TO BE CHANGED - SFDC_PROFILE_NAME is an attribute of role
    -- so this should some from role table.CONFIRM AND CHANGE THE CODE ACCORDINGLY.
    --    SELECT e404_profile.profile_id
     --     INTO x_sf_profile_id
     --     FROM canon_e404_sf_profile_tbl e404_profile
     --       ,canon_s21_cd_tbl cd_tbl
      --       ,canon_s21_cd_val_tbl val_tbl 
     --    WHERE cd_tbl.cd_name = 'CANON_E404_USER_PROFILE_MAP'
      --     AND cd_tbl.cd_id = val_tbl.cd_id
      --     AND val_tbl.val1 = v_role
		--   AND val_tbl.is_active IS NULL
        --   AND val_tbl.val2 = e404_profile.name;
--           AND e404_profile.name = profile_vs.role_name
--           AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(profile_vs.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(profile_vs.eff_TO_DT, 'yyyymmdd'),SYSDATE));

		-- Should be using this after getting confirmation from Yukiko
		SELECT e404.profile_id
            INTO x_sf_profile_id
            FROM org_func_role_tp ofrt
                 ,canon_e404_sf_profile_tbl e404
            WHERE ofrt.org_func_role_tp_nm = v_role
              AND ofrt.crm_sls_prfl_nm = e404.name
              AND ofrt.ezcancelflag = '0'
              AND ofrt.glbl_cmpy_cd = 'ADB';
		
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        x_sf_profile_id := NULL;
        IF (l_comments IS NULL) THEN
            l_comments := 'No SF_PROFILE_ID found for role, ' || v_role ;
--            dbms_output.put_line('Error - get_sf_profile_id1: ' ||SUBSTR(SQLERRM,2000));
        ELSE
            l_comments := l_comments || '|No SF_PROFILE_ID found for role, ' || v_role;
--            dbms_output.put_line('Error - get_sf_profile_id2: ' ||SUBSTR(SQLERRM,2000));
         END IF;
        --canon_e404_sf_error_log_pkg.log_error('EXTRACT_USERS','GET_SF_PROFILE_ID','psn_last_nm:'||v_psn_last_nm,' role_name:'||v_psn_role_nm,NULL,NULL,NULL,'Profile is not setup for SALES_COMP role');
    WHEN TOO_MANY_ROWS THEN
        x_sf_profile_id := NULL;
        IF (l_comments IS NULL) THEN
            l_comments := 'Multiple SF_PROFILE_ID''s found for role, '|| v_role;
--            dbms_output.put_line('get_sf_profile_id3 ' ||SUBSTR(SQLERRM,2000));
        ELSE
            l_comments := l_comments || '|Multiple SF_PROFILE_ID''s found for role, '|| v_role;
            dbms_output.put_line('get_sf_profile_id4: ' ||SQLERRM);
         END IF;
         dbms_output.put_line('get_sf_profile_id4: ' ||SQLERRM);
        --canon_e404_sf_error_log_pkg.log_error('EXTRACT_USERS','GET_SF_PROFILE_ID','psn_last_nm:'||v_psn_last_nm,' role_name:'||v_psn_role_nm,NULL,NULL,NULL,'SALES_COMP role is setup multiple times');
        --RETURN x_sf_profile_id;
    WHEN OTHERS THEN
        x_sf_profile_id := NULL;
         IF (l_comments IS NULL) THEN
            l_comments := 'Error fetching SF_PROFILE_ID for Role, ' || v_role;
--            dbms_output.put_line('Error - get_sf_profile_id5: ' ||SUBSTR(SQLERRM,2000));
        ELSE
            l_comments := l_comments || '|Error fetching SF_PROFILE_ID for Role, ' || v_role;

         END IF;
         dbms_output.put_line('Error-get_sf_profile_id6: ' ||SQLERRM);
        --canon_e404_sf_error_log_pkg.log_error('EXTRACT_USERS','GET_SF_PROFILE_ID','psn_last_nm:'||v_psn_last_nm,' role_name:'||v_psn_role_nm,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
        --RETURN x_sf_profile_id;
END get_sf_profile_id;

PROCEDURE get_sf_role_id(l_mapped_org IN VARCHAR2
                         ,l_last_name IN VARCHAR2
                         ,l_resource_id IN VARCHAR2
                         ,x_sf_role_id  OUT VARCHAR2
                         ,l_comments IN OUT VARCHAR2)
AS
    l_procedure_name VARCHAR2(25) := 'get_sf_role_id';
    l_cmt VARCHAR2(32767) := null;
BEGIN
    SELECT e404.role_id
     INTO x_sf_role_id
     FROM canon_e404_sf_role_tbl e404
           ,ds_org_unit dou
          ,org_func_asg ofa
          ,toc t
    WHERE dou.org_cd = l_mapped_org
      AND ofa.psn_cd = l_resource_id
      AND ofa.toc_cd = t.toc_cd
      AND dou.org_cd = t.org_cd
      AND dou.glbl_cmpy_cd = 'ADB'
	  AND dou.ezcancelflag = '0'
      AND ofa.glbl_cmpy_cd = 'ADB'
	  AND ofa.ezcancelflag ='0'
      AND t.glbl_cmpy_cd = 'ADB'
	  AND t.ezcancelflag = '0'
      AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(dou.eff_FROM_DT,'yyyymmdd'),SYSDATE)) AND TRUNC(NVL(to_date(dou.eff_THRU_DT, 'yyyymmdd'),SYSDATE))
      AND UPPER(e404.NAME) = UPPER(trim(dou.org_nm));
     -- AND UPPER(e404.NAME) = DECODE(ofrt.mgr_flg,'Y', UPPER(dou.org_nm),
                                       --     CASE WHEN l_last_name LIKE '%ADJ%' OR l_last_name LIKE '%TERRITORY%' THEN UPPER(dou.org_nm)
                                           --      ELSE UPPER(dou.org_nm) || ' - REP' END);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        x_sf_role_id := NULL;
        dbms_output.put_line('Error-get_sf_role_id mapped_org: ' ||l_mapped_org || ' l_mapped_rg length: ' ||length(l_mapped_org));
        dbms_output.put_line('Error-get_sf_role_id comments: ' ||l_comments || ' l_comments length: ' ||length(l_comments));
        
         IF (l_comments IS NULL) THEN
              l_cmt := 'No SF_ROLE_ID found for the org: ' || l_mapped_org;
--            l_comments := 'No SF_ROLE_ID found for the org, ' || l_mapped_org;
             l_comments := l_cmt;
--            dbms_output.put_line('Error-get_sf_role_id1: ' ||SQLERRM);
        ELSE
--            l_comments := l_comments || '|NO SF_ROLE_ID found for the org, ' || l_mapped_org;
            l_cmt := '|No SF_ROLE_ID found for the org: ' || l_mapped_org;
--            l_comments := l_comments || l_cmt;
--            dbms_output.put_line('Error-get_sf_role_id2: ' ||SQLERRM);
         dbms_output.put_line('Error-get_sf_role_id comments post: ' ||l_comments || ' l_comments length: ' ||length(l_comments));

         END IF;

        --canon_e404_sf_error_log_pkg.log_error('EXTRACT_USERS','GET_SF_ROLE_ID','v_resource_id:'||v_resource_id,NULL,NULL,NULL,NULL,'Resource does not have any SALES Roles');
    WHEN TOO_MANY_ROWS THEN
        x_sf_role_id := NULL;
         IF (l_comments IS NULL) THEN
            l_comments := 'Multiple SF_ROLE_ID''s found for org, ' || l_mapped_org;
--            dbms_output.put_line('Error-get_sf_role_id3: ' ||SUBSTR(SQLERRM,2000));
        ELSE
            l_comments := l_comments || '|Multiple SF_ROLE_ID''s found for org, ' || l_mapped_org;
--            dbms_output.put_line('Error-get_sf_role_id4: ' ||SUBSTR(SQLERRM,2000));
         END IF;

        --canon_e404_sf_error_log_pkg.log_error('EXTRACT_USERS','GET_SF_ROLE_ID','v_resource_id:'||v_resource_id,NULL,NULL,NULL,NULL,'Resource is part of multiple groups');
    WHEN OTHERS THEN
        x_sf_role_id := NULL;
         IF (l_comments IS NULL) THEN
            l_comments := 'Error fetching SF_ROLE_ID for org, ' || l_mapped_org;
--            dbms_output.put_line('Error - get_sf_role_id5: ' ||SUBSTR(SQLERRM,2000));
        ELSE
            l_comments := l_comments || '|Error fetching SF_ROLE_ID for org, ' || l_mapped_org;
--            dbms_output.put_line('Error-get_sf_role_id6: ' ||SUBSTR(SQLERRM,2000));
         END IF;
        --canon_e404_sf_error_log_pkg.log_error('EXTRACT_USERS','GET_SF_ROLE_ID','v_resource_id:'||v_resource_id,NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
END get_sf_role_id;

PROCEDURE get_hrch_name(v_resource_id IN VARCHAR2
                    ,v_toc_cd IN VARCHAR2
                    ,v_tier_cd IN VARCHAR2
                    ,l_tier_org_nm OUT VARCHAR2)

AS
    l_procedure_name VARCHAR2(25) := 'get_hrch_name';
l_sql   VARCHAR2(32767) := NULL;
l_from  VARCHAR2(32767) := NULL;
l_nvl   VARCHAR2(32767) := NULL;
l_ctr   NUMBER := 0;
--l_resource_id VARCHAR2(30) := NULL;

BEGIN
 l_from := ' FROM toc_org_mgr_info tomi WHERE tomi.psn_cd = ''' || v_resource_id || ''''
                   || '  AND tomi.toc_cd = ''' || v_toc_cd || '''' 
                   || ' AND  TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, ''yyyymmdd''),SYSDATE))';
--        dbms_output.put_line('tier cd :' ||hrch_tier_rec.tier_cd);
        l_sql := 'SELECT NVL(';
        FOR l_ctr IN REVERSE 2..(v_tier_cd)
        LOOP
            l_nvl := l_nvl ||
            (CASE l_ctr WHEN 1 THEN 'FIRST'
                                   WHEN 2 THEN 'SCD'
                                   WHEN 3 THEN 'THIRD'
                                   WHEN 4 THEN 'FRTH'
                                   WHEN 5 THEN 'FIFTH'
                                   WHEN 6 THEN 'SIXTH'
                                   WHEN 7 THEN 'SVNTH'
                                   WHEN 8 THEN 'EIGHTH'
                                   WHEN 9 THEN 'NINTH'
                                   WHEN 10 THEN 'TENTH'
                                   WHEN 11 THEN 'ELVNTH'
                                 ELSE '' END) || '_ORG_NM, NVL(' ;
        END LOOP;
--        dbms_output.put_line('l_nvl :' ||l_nvl);
        l_nvl := substr(l_nvl, 1,length(l_nvl)-5);
        l_nvl := l_nvl || ' FIRST_ORG_NM';
--        dbms_output.put_line('l_nvl :' ||l_nvl);
        l_sql := l_sql || l_nvl;
        l_ctr := 1;
        
        FOR l_ctr IN REVERSE 2..v_tier_cd
        LOOP
            l_sql := l_sql || ')';
        END LOOP;

        l_sql := l_sql || l_from ;
--        dbms_output.put_line('hrch sql : ' ||l_sql);
         EXECUTE IMMEDIATE l_sql INTO l_tier_org_nm;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        l_tier_org_nm := NULL;
    WHEN OTHERS THEN
		dbms_output.put_line('Error - get_hrch: ' ||SQLERRM);
        l_tier_org_nm := NULL;
        canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
END get_hrch_name;

PROCEDURE get_hrch_name_psn(v_resource_id IN VARCHAR2
                    ,v_toc_cd IN VARCHAR2
                    ,v_tier_cd IN VARCHAR2
                    ,l_tier_org_nm OUT VARCHAR2
                    ,l_tier_psn_cd OUT VARCHAR2
                    )
AS
l_sql   VARCHAR2(32767) := NULL;
l_from  VARCHAR2(32767) := NULL;
l_tier_nm VARCHAR2(20) := NULL;
l_procedure_name VARCHAR2(25) := 'get_hrch_name_psn';

BEGIN
 l_from := ' FROM toc_org_mgr_info tomi WHERE tomi.psn_cd = ''' || v_resource_id || ''''
                   || '  AND tomi.toc_cd = ''' || v_toc_cd || '''' 
                   || ' AND TRUNC(SYSDATE) BETWEEN TRUNC(NVL(to_date(tomi.eff_FROM_DT,''yyyymmdd''),SYSDATE)) AND TRUNC(NVL(to_date(tomi.eff_THRU_DT, ''yyyymmdd''),SYSDATE))';
--        dbms_output.put_line('tier cd :' ||hrch_tier_rec.tier_cd);
        l_tier_nm := (CASE v_tier_cd WHEN 1 THEN 'FIRST'
                                   WHEN 2 THEN 'SCD'
                                   WHEN 3 THEN 'THIRD'
                                   WHEN 4 THEN 'FRTH'
                                   WHEN 5 THEN 'FIFTH'
                                   WHEN 6 THEN 'SIXTH'
                                   WHEN 7 THEN 'SVNTH'
                                   WHEN 8 THEN 'EIGHTH'
                                   WHEN 9 THEN 'NINTH'
                                   WHEN 10 THEN 'TENTH'
                                   WHEN 11 THEN 'ELVNTH'
                                 ELSE '' END) || '_ORG_';

        
        l_sql := 'SELECT ' || l_tier_nm ||'NM, ' || l_tier_nm || 'MGR_PSN_CD ' || l_from ;
--        dbms_output.put_line('dyn sql : ' ||l_sql);
         EXECUTE IMMEDIATE l_sql INTO l_tier_org_nm,l_tier_psn_cd;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        l_tier_org_nm := NULL;
        l_tier_psn_cd := NULL;
    WHEN OTHERS THEN
--    dbms_output.put_line('Error - get_hrch: ' ||SQLERRM);
        l_tier_org_nm := NULL;
        l_tier_psn_cd := NULL;
        canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
END get_hrch_name_psn;

FUNCTION get_sfdc_id(v_psn_cd IN VARCHAR) RETURN VARCHAR2
AS
        x_sfdc_id VARCHAR2(20)    := NULL;
        l_procedure_name VARCHAR2(25) := 'get_sfdc_id';
BEGIN
    SELECT sf_user_id
      INTO x_sfdc_id
      FROM canon_e404_sf_user_mapping_tbl
     WHERE employee_number = v_psn_cd
       AND status_flag = 'P' ;
	   
     RETURN x_sfdc_id;
	 
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        x_sfdc_id := NULL;
        RETURN x_sfdc_id;
    WHEN OTHERS THEN
        x_sfdc_id := NULL;
        dbms_output.put_line('Error - get_sfdc_id: ' ||SQLERRM);
        canon_e404_sf_error_log_pkg.log_error(g_package_name,l_procedure_name,'ERROR',NULL,NULL,NULL,NULL,'Unexpected Error:'||SUBSTR(SQLERRM,2000));
        RETURN x_sfdc_id;
END get_sfdc_id;                      

END    CANON_e404_sf_user_pkg;
/