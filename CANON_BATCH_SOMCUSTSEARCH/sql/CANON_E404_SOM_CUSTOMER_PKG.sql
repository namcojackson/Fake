CREATE OR REPLACE PACKAGE CANON_E404_SOM_CUSTOMER_PKG
AS

TYPE pk_cust_cursor IS REF CURSOR;

PROCEDURE get_customers(
   p_employee_id       IN       VARCHAR2,    -- Pass Canon Employee ID of the resource
   p_customer_name     IN       VARCHAR2,
   p_address           IN       VARCHAR2,
   p_city              IN       VARCHAR2,
   p_state             IN       VARCHAR2,
   p_zipcode           IN       VARCHAR2,
   p_manager_flag      IN       VARCHAR2,
   p_specialist_flag   IN       VARCHAR2,
   p_deptcode          in       varchar2,
   p_sortorder         IN       VARCHAR2,
   p_sortfield         IN       VARCHAR2,
   p_party_number      IN       VARCHAR2 DEFAULT NULL,   --Pass Account Number from S21
   p_party_site_number IN       VARCHAR2 DEFAULT NULL,   --Pass Location Number from S21  
   x_customer_data     OUT      pk_cust_cursor
);
   
END CANON_E404_SOM_CUSTOMER_PKG;
/

CREATE OR REPLACE PACKAGE BODY CANON_E404_SOM_CUSTOMER_PKG
AS

PROCEDURE get_customers (
   p_employee_id       IN       VARCHAR2,    -- Pass Canon Employee ID of the resource
   p_customer_name     IN       VARCHAR2,
   p_address           IN       VARCHAR2,
   p_city              IN       VARCHAR2,
   p_state             IN       VARCHAR2,
   p_zipcode           IN       VARCHAR2,
   p_manager_flag      IN       VARCHAR2,
   p_specialist_flag   IN       VARCHAR2,
   p_deptcode          in       varchar2,
   p_sortorder         IN       VARCHAR2,
   p_sortfield         IN       VARCHAR2,
   p_party_number      IN       VARCHAR2 DEFAULT NULL,   --Pass Account Number from S21
   p_party_site_number IN       VARCHAR2 DEFAULT NULL,   --Pass Location Number from S21  
   x_customer_data     OUT      pk_cust_cursor
)
IS
   l_sql_qry         VARCHAR2(32000);
   l_sortfield       VARCHAR2 (300);
   
BEGIN

   IF (p_sortfield = 'Type')
   THEN
      l_sortfield := 'category_code';
   ELSIF (p_sortfield = 'Name')
   THEN
      l_sortfield := 'party_name';
   ELSIF (p_sortfield = 'CBS_Acct_Source__c')
   THEN
      l_sortfield := 'writing_rep_name';
   ELSIF (p_sortfield = 'org_name__C')
   THEN
      l_sortfield := 'sfdc_number';
   ELSIF (p_sortfield = 'CBS_Party_Site_Number__c')
   THEN
      l_sortfield := 'party_site_number';
   ELSIF (p_sortfield = 'AccountNumber')
   THEN
      l_sortfield := 'account_number';
   ELSIF (p_sortfield = 'CBS_Territory_Name__c')
   THEN
      l_sortfield := 'writing_territory';
   ELSE
      l_sortfield := 'party_name';
   END IF;

   l_sql_qry := 
  'WITH 
      team_members
    AS(SELECT /*+ materialize */
              DISTINCT
              sp1.psn_num
             ,sp1.psn_cd
             ,tomi.org_func_role_tp_cd
             ,ofrt.actv_flg
             ,ofrt.mgr_flg
             ,ofrt.spcl_flg
         FROM s21_psn sp
             ,toc_org_mgr_info tomi
             ,org_func_role_tp ofrt
             ,s21_psn sp1
        WHERE 1 = 1
          AND sp.glbl_cmpy_cd = ''ADB''
          AND sp.ezcancelflag = ''0''
          AND TO_NUMBER(TO_CHAR(SYSDATE, ''YYYYMMDD'')) BETWEEN TO_NUMBER(sp.eff_from_dt) AND TO_NUMBER(NVL(sp.eff_thru_dt, TO_CHAR(SYSDATE+1, ''YYYYMMDD''))) 
          AND sp.psn_num = '''||p_employee_id||'''
          AND tomi.glbl_cmpy_cd = ''ADB''
          AND tomi.ezcancelflag = ''0''
          AND TO_NUMBER(TO_CHAR(SYSDATE, ''YYYYMMDD'')) BETWEEN TO_NUMBER(tomi.eff_from_dt) AND TO_NUMBER(NVL(tomi.eff_thru_dt, TO_CHAR(SYSDATE+1, ''YYYYMMDD''))) 
          AND(   tomi.first_org_mgr_psn_cd     = sp.psn_cd
              OR tomi.scd_org_mgr_psn_cd       = sp.psn_cd
              OR tomi.third_org_mgr_psn_cd     = sp.psn_cd
              OR tomi.frth_org_mgr_psn_cd      = sp.psn_cd
              OR tomi.fifth_org_mgr_psn_cd     = sp.psn_cd
              OR tomi.sixth_org_mgr_psn_cd     = sp.psn_cd
              OR tomi.svnth_org_mgr_psn_cd     = sp.psn_cd
              OR tomi.eighth_org_mgr_psn_cd    = sp.psn_cd
              OR tomi.ninth_org_mgr_psn_cd     = sp.psn_cd
              OR tomi.tenth_org_mgr_psn_cd     = sp.psn_cd
              OR tomi.elvth_org_mgr_psn_cd     = sp.psn_cd
              OR tomi.psn_cd                   = sp.psn_cd
             )
          AND ofrt.glbl_cmpy_cd = ''ADB''
          AND ofrt.ezcancelflag = ''0''
          AND ofrt.org_func_role_tp_cd = tomi.org_func_role_tp_cd
          AND sp1.glbl_cmpy_cd = ''ADB''
          AND sp1.ezcancelflag = ''0''
          AND TO_NUMBER(TO_CHAR(SYSDATE, ''YYYYMMDD'')) BETWEEN TO_NUMBER(sp1.eff_from_dt) AND TO_NUMBER(NVL(sp1.eff_thru_dt, TO_CHAR(SYSDATE+1, ''YYYYMMDD'')))           
          AND sp1.psn_cd = tomi.psn_cd
      ),
      trtyrule_psn
    AS(SELECT /*+ MATERIALIZE */ 
              sp1.psn_num
             ,sp1.psn_cd
         FROM toc_org_mgr_info tomi
             ,(SELECT /*+ ORDERED */
                      DISTINCT
                      dou.org_cd
                 FROM trty_rule_tp         trt
                     ,trty_rule            tr
                     ,org_stru_tp          ost
                     ,ds_org_unit          dou
                     ,ds_org_resrc_reln    dorr
                     ,team_members         tm
                WHERE tr.ezcancelflag = ''0''
                  AND tr.glbl_cmpy_cd = ''ADB''
                  AND TO_NUMBER(TO_CHAR(SYSDATE, ''YYYYMMDD'')) BETWEEN TO_NUMBER(tr.eff_from_dt) AND TO_NUMBER(NVL(tr.eff_thru_dt,TO_NUMBER(TO_CHAR(SYSDATE+1, ''YYYYMMDD'')) )) 
                  AND tr.org_stru_tp_cd = ost.org_stru_tp_cd
                  AND ost.ezcancelflag = ''0''
                  AND ost.glbl_cmpy_cd = ''ADB'' 
                  AND ost.trty_stru_flg = ''Y''
                  AND ost.org_stru_tp_nm = ''Territory Structure''
                  AND tr.trty_rule_tp_cd = trt.trty_rule_tp_cd
                  AND trt.ezcancelflag = ''0''
                  AND trt.glbl_cmpy_cd = ''ADB''
                  AND trt.trty_rule_tp_nm = ''Organization Name''
                  AND dou.org_nm = tr.trty_rule_from_val_txt -- From Value Should have organization name
                  --AND dou.org_stru_tp_cd = tr.org_stru_tp_cd --Probably not needed. no harm adding this condition.
                  AND dou.ezcancelflag = ''0''
                  AND dou.glbl_cmpy_cd = ''ADB''
                  AND TO_NUMBER(TO_CHAR(SYSDATE, ''YYYYMMDD'')) BETWEEN TO_NUMBER(dou.eff_from_dt) AND TO_NUMBER(NVL(dou.eff_thru_dt,TO_NUMBER(TO_CHAR(SYSDATE+1, ''YYYYMMDD'')) ))
                  AND dorr.org_cd = tr.org_cd
                  AND dorr.ezcancelflag = ''0''
                  AND dorr.glbl_cmpy_cd = ''ADB''
                  AND TO_NUMBER(TO_CHAR(SYSDATE, ''YYYYMMDD'')) BETWEEN TO_NUMBER(dorr.eff_from_dt) AND TO_NUMBER(NVL(dorr.eff_thru_dt,TO_NUMBER(TO_CHAR(SYSDATE+1, ''YYYYMMDD'')) ))
                  AND dorr.psn_cd = tm.psn_cd
              ) orgs
             ,s21_psn sp1
        WHERE tomi.glbl_cmpy_cd = ''ADB''
          AND tomi.ezcancelflag = ''0''
          AND TO_NUMBER(TO_CHAR(SYSDATE, ''YYYYMMDD'')) BETWEEN TO_NUMBER(tomi.eff_from_dt) AND TO_NUMBER(NVL(tomi.eff_thru_dt, TO_CHAR(SYSDATE+1, ''YYYYMMDD''))) 
          AND(   tomi.first_org_cd     = orgs.org_cd
              OR tomi.scd_org_cd       = orgs.org_cd
              OR tomi.third_org_cd     = orgs.org_cd
              OR tomi.frth_org_cd      = orgs.org_cd
              OR tomi.fifth_org_cd     = orgs.org_cd
              OR tomi.sixth_org_cd     = orgs.org_cd
              OR tomi.svnth_org_cd     = orgs.org_cd
              OR tomi.eighth_org_cd    = orgs.org_cd
              OR tomi.ninth_org_cd     = orgs.org_cd
              OR tomi.tenth_org_cd     = orgs.org_cd
              OR tomi.elvth_org_cd     = orgs.org_cd
             )
          AND sp1.glbl_cmpy_cd = ''ADB''
          AND sp1.ezcancelflag = ''0''
          AND TO_NUMBER(TO_CHAR(SYSDATE, ''YYYYMMDD'')) BETWEEN TO_NUMBER(sp1.eff_from_dt) AND TO_NUMBER(NVL(sp1.eff_thru_dt, TO_CHAR(SYSDATE+1, ''YYYYMMDD''))) 
          AND sp1.psn_cd = tomi.psn_cd
      ),
      mass_share_requests
    AS(SELECT /*+ materialize */
              *
         FROM(
              SELECT DISTINCT
                     e404_user_req.employee_number requested_by
                    ,e404_user_asg.employee_number assign_to 
                    ,FIRST_VALUE(request_status) OVER(PARTITION BY e404_msr.requested_by, e404_msr.assign_to ORDER BY requested_date DESC) request_status 
                FROM canon_e404_mass_share_req_tbl  e404_msr 
                    ,(SELECT employee_number, sf_user_id
                        FROM canon_e404_sf_user_mapping_tbl
                       WHERE status_flag != ''R''
                         AND sf_user_id IS NOT NULL
                     )   e404_user_req --VJ no need for mulitple records per employee number handling logic
                    ,(SELECT employee_number, sf_user_id
                        FROM canon_e404_sf_user_mapping_tbl 
                       WHERE status_flag != ''R''
                         AND sf_user_id IS NOT NULL
                     )   e404_user_asg --VJ no need for mulitple records per employee number handling logic
               WHERE e404_msr.requested_by = e404_user_req.sf_user_id
                 AND e404_msr.assign_to = e404_user_asg.sf_user_id  
              )
         WHERE request_status = ''Shared''
      ) 
SELECT /*+ ORDERED */
       DISTINCT
       e404_acct.party_name
      ,e404_acct.category_code
      ,e404_acct.party_number
      ,e404_acct.party_site_number
      ,e404_acct.party_number account_number
      ,e404_acct.s_street ||''<br /> ''|| e404_acct.s_city ||'', ''||e404_acct.s_state||''  ''||e404_acct.s_postal_code party_address
      ,NVL(e404_acct.writing_employee_number, '' '') writing_employee_number
      ,NVL(e404_acct.writing_rep_name,'' '') writing_rep_name
      ,NVL(e404_acct.writing_territory,'' '') writing_territory
      ,'' '' installing_rep_name
      ,'' '' installing_rep_number
      ,NVL(e404_acct.sf_prospect_number,'' '') sfdc_number
      ,NVL(e404_acct.sf_account_id,'' '') sfdc_id
      ,NVL(e404_acct.acct_legal_name, '' '') legal_name
      ,NVL(e404_acct.dba_name, '' '') dba_name
      ,'' '' logged_in_user
      ,NVL(e404_acct.csmp_level,'' '') csmp_level
      ,'' '' csmp_start_date
      ,'' '' csmp_end_date
      ,'' '' major_acct_flag
     -- ,NVL(e404_acct.fm_dept_name, '' '') deptCode
	   ,e473.salesrep_number deptCode
      ,'' '' bid_pricing
	  ,e404_acct.duns_number
	  ,e404_acct.phone
  FROM (SELECT psn_num 
          FROM team_members
         UNION 
        SELECT psn_num
          FROM trtyrule_psn
       ) tm
      ,(SELECT *
          FROM canon_e404_sf_acct_team_tbl
         WHERE status_flag NOT LIKE ''D%''
       ) e404_acct_team
      ,canon_e404_sf_acct_mapping_tbl e404_acct 
	   ,CANON_E473_BSD_ACCT_REP_VS_V e473
 WHERE 1 = 1 
   AND e404_acct_team.salesrep_number = tm.psn_num
   AND e404_acct.party_site_id = e404_acct_team.party_site_id
   AND e404_acct.account_number = e473.account_number(+)
   AND e404_acct.party_name LIKE NVL(UPPER('''||p_customer_name||'''),e404_acct.party_name)||''%''   
   AND e404_acct.s_street LIKE NVL(UPPER('''||p_address||'''),e404_acct.s_street)||''%''
   AND e404_acct.s_city LIKE NVL(UPPER('''||p_city||'''),e404_acct.s_city)||''%''
   AND e404_acct.s_state LIKE NVL(UPPER('''||p_state||'''),e404_acct.s_state)||''%''
   AND e404_acct.s_postal_code LIKE NVL(UPPER('''||p_zipcode||'''),e404_acct.s_postal_code)||''%''
   AND NVL(e404_acct.fm_dept_name, ''X'') LIKE NVL(UPPER('''||p_deptcode||'''),NVL(e404_acct.fm_dept_name, ''X''))||''%''
   AND e404_acct.party_number LIKE NVL(UPPER('''||p_party_number||'''),e404_acct.party_number)||''%''
   AND e404_acct.party_site_number LIKE NVL(UPPER('''||p_party_site_number||'''),e404_acct.party_site_number)||''%''
 UNION
SELECT /*+ ORDERED */
       DISTINCT
       e404_acct.party_name
      ,e404_acct.category_code
      ,e404_acct.party_number
      ,e404_acct.party_site_number
      ,e404_acct.party_number account_number
      ,e404_acct.s_street ||''<br /> ''|| e404_acct.s_city ||'', ''||e404_acct.s_state||''  ''||e404_acct.s_postal_code party_address
      ,NVL(e404_acct.writing_employee_number, '' '') writing_employee_number
      ,NVL(e404_acct.writing_rep_name,'' '') writing_rep_name
      ,NVL(e404_acct.writing_territory,'' '') writing_territory
      ,'' '' installing_rep_name
      ,'' '' installing_rep_number
      ,NVL(e404_acct.sf_prospect_number,'' '') sfdc_number
      ,NVL(e404_acct.sf_account_id,'' '') sfdc_id
      ,NVL(e404_acct.acct_legal_name, '' '') legal_name
      ,NVL(e404_acct.dba_name, '' '') dba_name
      ,'' '' logged_in_user
      ,NVL(e404_acct.csmp_level,'' '') csmp_level
      ,'' '' csmp_start_date
      ,'' '' csmp_end_date
      ,'' '' major_acct_flag
    --  ,NVL(e404_acct.fm_dept_name, '' '') deptCode
	  ,e473.salesrep_number deptCode
      ,'' '' bid_pricing
	  ,e404_acct.duns_number
	  ,e404_acct.phone
  FROM (SELECT DISTINCT
               psn_num
          FROM team_members
         UNION 
        SELECT DISTINCT
               psn_num
          FROM trtyrule_psn
       )tm
      ,mass_share_requests msr
      ,canon_e404_sf_acct_mapping_tbl e404_acct 
	  ,CANON_E473_BSD_ACCT_REP_VS_V e473
 WHERE 1 = 1
   AND msr.assign_to = tm.psn_num
   AND msr.requested_by = e404_acct.writing_employee_number
   AND e404_acct.account_number = e473.account_number(+)
   AND e404_acct.party_name LIKE NVL(UPPER('''||p_customer_name||'''),e404_acct.party_name)||''%''   
   AND e404_acct.s_street LIKE NVL(UPPER('''||p_address||'''),e404_acct.s_street)||''%''
   AND e404_acct.s_city LIKE NVL(UPPER('''||p_city||'''),e404_acct.s_city)||''%''
   AND e404_acct.s_state LIKE NVL(UPPER('''||p_state||'''),e404_acct.s_state)||''%''
   AND e404_acct.s_postal_code LIKE NVL(UPPER('''||p_zipcode||'''),e404_acct.s_postal_code)||''%''
   AND NVL(e404_acct.fm_dept_name, ''X'') LIKE NVL(UPPER('''||p_deptcode||'''),NVL(e404_acct.fm_dept_name, ''X''))||''%''
   AND e404_acct.party_number LIKE NVL(UPPER('''||p_party_number||'''),e404_acct.party_number)||''%''
   AND e404_acct.party_site_number LIKE NVL(UPPER('''||p_party_site_number||'''),e404_acct.party_site_number)||''%''';

   l_sql_qry := ' SELECT * FROM (' || l_sql_qry || ') ';
   
   IF (    (p_sortorder IS NOT NULL AND LENGTH (p_sortorder) > 0)
       AND (l_sortfield IS NOT NULL AND LENGTH (l_sortfield) > 0)
      )
   THEN
      l_sql_qry :=
            l_sql_qry || ' order by ' || l_sortfield || ' ' || p_sortorder;
   END IF;

   --l_sql_qry := 'SELECT * FROM '||l_sql_qry||' WHERE rownum < 501 '; 
   l_sql_qry := l_sql_qry||' WHERE rownum < 501 '; 

   dbms_output.put_line(l_sql_qry);
   OPEN x_customer_data FOR l_sql_qry;

EXCEPTION
   WHEN OTHERS THEN
      dbms_output.put_line(SQLERRM);
      OPEN x_customer_data
       FOR
    SELECT NULL
      FROM DUAL
     WHERE 1=-1;      

END get_customers;

END CANON_E404_SOM_CUSTOMER_PKG;
/
