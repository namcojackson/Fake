CREATE OR REPLACE PACKAGE canon_e404_sf_stack_rank_pkg
AS
G_PACKAGE_NAME VARCHAR2(28) := 'canon_e404_sf_stack_rank_pkg';
PROCEDURE extract_stack_rank(x_return_status   OUT VARCHAR2
                          ,x_msg_data        OUT VARCHAR2
                          );

END canon_e404_sf_stack_rank_pkg;
/
CREATE OR REPLACE PACKAGE BODY canon_e404_sf_stack_rank_pkg
AS
PROCEDURE extract_stack_rank(x_return_status   OUT VARCHAR2
                          ,x_msg_data        OUT VARCHAR2
                          )
AS
   l_procedure_name VARCHAR2(28) := 'extract_stack_rank';

   l_r_stack_rank CANON_E404_SF_STACK_RANK_GTBL%ROWTYPE;
   l_r_stack_rank_default CANON_E404_SF_STACK_RANK_GTBL%ROWTYPE;

   TYPE t_stack_rank IS TABLE OF CANON_E404_SF_STACK_RANK_GTBL%ROWTYPE;
   l_t_stack_rank t_stack_rank := t_stack_rank();

   
   i NUMBER := 1;
   
   Start_time NUMBER;
   End_time NUMBER;

   CURSOR stack_rank_cur
       IS
   SELECT TO_CHAR(TO_DATE(tomi.eff_from_dt, 'RRRRMMDD'), 'MON-RR') period_name
         ,TO_CHAR(TO_DATE(tomi.eff_from_dt, 'RRRRMMDD'), 'Q') quarter
         ,TO_CHAR(TO_DATE(tomi.eff_from_dt, 'RRRRMMDD'), 'RRRR') yr
         ,psn.line_biz_tp_cd 
         ,ofrt.mgr_flg
         ,toc_first.org_func_role_tp_cd ofrt_first
         ,toc_scd.org_func_role_tp_cd ofrt_scd
         ,toc_third.org_func_role_tp_cd ofrt_third
         ,toc_frth.org_func_role_tp_cd ofrt_frth
         ,toc_fifth.org_func_role_tp_cd ofrt_fifth
         ,toc_sixth.org_func_role_tp_cd ofrt_sixth
         ,toc_svnth.org_func_role_tp_cd ofrt_svnth
         ,toc_eighth.org_func_role_tp_cd ofrt_eighth
         ,toc_ninth.org_func_role_tp_cd ofrt_ninth
         ,toc_tenth.org_func_role_tp_cd ofrt_tenth
         ,toc_elvth.org_func_role_tp_cd ofrt_elvth
         ,tomi.*
     FROM toc_org_mgr_info tomi
         ,toc
         ,toc toc_first
         ,toc toc_scd
         ,toc toc_third
         ,toc toc_frth
         ,toc toc_fifth
         ,toc toc_sixth
         ,toc toc_svnth
         ,toc toc_eighth
         ,toc toc_ninth
         ,toc toc_tenth
         ,toc toc_elvth
         ,org_func_role_tp ofrt
         ,s21_psn psn
    WHERE 1 = 1
      --AND tomi.psn_cd IN( 'K00298', 'M05313', 'M03543','M01577', 'M04557', 'C17324')
      AND tomi.ezcancelflag = '0'
      AND tomi.glbl_cmpy_cd = 'ADB'
      AND toc.ezcancelflag = '0'
      AND toc.glbl_cmpy_cd = 'ADB'
      AND toc_first.ezcancelflag(+) = '0'
      AND toc_first.glbl_cmpy_cd(+) = 'ADB'
      AND toc_scd.ezcancelflag(+) = '0'
      AND toc_scd.glbl_cmpy_cd(+) = 'ADB'
      AND toc_third.ezcancelflag(+) = '0'
      AND toc_third.glbl_cmpy_cd(+) = 'ADB'
      AND toc_frth.ezcancelflag(+) = '0'
      AND toc_frth.glbl_cmpy_cd(+) = 'ADB'
      AND toc_fifth.ezcancelflag(+) = '0'
      AND toc_fifth.glbl_cmpy_cd(+) = 'ADB'
      AND toc_sixth.ezcancelflag(+) = '0'
      AND toc_sixth.glbl_cmpy_cd(+) = 'ADB'
      AND toc_svnth.ezcancelflag(+) = '0'
      AND toc_svnth.glbl_cmpy_cd(+) = 'ADB'
      AND toc_eighth.ezcancelflag(+) = '0'
      AND toc_eighth.glbl_cmpy_cd(+) = 'ADB'
      AND toc_ninth.ezcancelflag(+) = '0'
      AND toc_ninth.glbl_cmpy_cd(+) = 'ADB'
      AND toc_tenth.ezcancelflag(+) = '0'
      AND toc_tenth.glbl_cmpy_cd(+) = 'ADB'
      AND toc_elvth.ezcancelflag(+) = '0'
      AND toc_elvth.glbl_cmpy_cd(+) = 'ADB'
      AND ofrt.ezcancelflag = '0'
      AND ofrt.glbl_cmpy_cd = 'ADB'
      AND psn.ezcancelflag = '0'
      AND psn.glbl_cmpy_cd = 'ADB'
      AND toc.toc_cd = tomi.toc_cd
      AND toc_first.toc_cd(+) = tomi.first_org_mgr_toc_cd
      AND toc_scd.toc_cd(+) = tomi.scd_org_mgr_toc_cd
      AND toc_third.toc_cd(+) = tomi.third_org_mgr_toc_cd
      AND toc_frth.toc_cd(+) = tomi.frth_org_mgr_toc_cd
      AND toc_fifth.toc_cd(+) = tomi.fifth_org_mgr_toc_cd
      AND toc_sixth.toc_cd(+) = tomi.sixth_org_mgr_toc_cd
      AND toc_svnth.toc_cd(+) = tomi.svnth_org_mgr_toc_cd
      AND toc_eighth.toc_cd(+) = tomi.eighth_org_mgr_toc_cd
      AND toc_ninth.toc_cd(+) = tomi.ninth_org_mgr_toc_cd
      AND toc_tenth.toc_cd(+) = tomi.tenth_org_mgr_toc_cd
      AND toc_elvth.toc_cd(+) = tomi.elvth_org_mgr_toc_cd
      AND ofrt.org_func_role_tp_cd = toc.org_func_role_tp_cd
      AND psn.psn_cd = tomi.psn_cd 
     -- AND TO_NUMBER(tomi.eff_from_dt) >= TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY')||'0101')
      --AND TO_NUMBER(tomi.eff_thru_dt) <= TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY')||'0131')
	  AND to_number(tomi.eff_from_dt) >= to_number(to_char(sysdate, 'YYYY')|| to_char(sysdate, 'MM') || '01')
      AND TO_NUMBER(tomi.eff_thru_dt) <= TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY')||to_char(sysdate, 'MM') || to_char(last_day(sysdate),'DD'))
      AND psn.line_biz_tp_cd IN ('ESS', 'ALL')
    ORDER BY TO_NUMBER(tomi.eff_from_dt);    

BEGIN

   FOR stack_rank_rec IN stack_rank_cur LOOP


      l_r_stack_rank := l_r_stack_rank_default;

      l_r_stack_rank.PERIOD_NAME        := stack_rank_rec.period_name;
      l_r_stack_rank.QUARTER_NO         := stack_rank_rec.quarter;
      l_r_stack_rank.YEAR               := stack_rank_rec.yr;

      /* TENTH START */
      IF stack_rank_rec.tenth_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.tenth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.tenth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.tenth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.tenth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.ninth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.ninth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.eighth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.eighth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
         END IF;

         IF stack_rank_rec.ninth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.eighth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

            IF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

         ELSIF stack_rank_rec.eighth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

         ELSIF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

         ELSIF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.third_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 9;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.tenth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_tenth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.tenth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 9;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;
      
      /* NINTH START */
      ELSIF stack_rank_rec.ninth_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.ninth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.ninth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;

            IF stack_rank_rec.eighth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.eighth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;

         END IF;

         IF stack_rank_rec.eighth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 8;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

         ELSIF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 8;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

         ELSIF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 8;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 8;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 8;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.third_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 8;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 8;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 8;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.ninth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_ninth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.ninth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 8;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;

      /* EIGHTH START */   
      ELSIF stack_rank_rec.eighth_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.eighth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.eighth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;

            IF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
         END IF;

         IF stack_rank_rec.svnth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 7;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;

         ELSIF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 7;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 7;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 7;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.third_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 7;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 7;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.eighth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_eighth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.eighth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 7;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;

      /*SVNTH start */
      ELSIF stack_rank_rec.svnth_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.svnth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.svnth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
         END IF;

        IF stack_rank_rec.sixth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 6;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 7;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 6;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 6;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.third_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 6;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 6;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 6;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.svnth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_svnth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.svnth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 6;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;

      /*SIXTH Start*/
      ELSIF stack_rank_rec.sixth_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.sixth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.sixth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;

            IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
         END IF;

         IF stack_rank_rec.fifth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 5;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 5;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.third_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 5;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 5;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 5;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.sixth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_sixth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.sixth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 5;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;       

      /*FIFTH START*/
      ELSIF stack_rank_rec.fifth_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.fifth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.fifth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;

            IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
         END IF;

         IF stack_rank_rec.frth_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 4;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 4;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 4;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 4;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.third_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 4;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 4;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 4;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 4;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 4;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.fifth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_fifth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.fifth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 4;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;       

      /*FRTH START*/
      ELSIF stack_rank_rec.frth_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.frth_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.frth_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;

            IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
         END IF;

         IF stack_rank_rec.third_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.frth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_frth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 3;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.frth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_frth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 3;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.frth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_frth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 3;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.frth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_frth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 3;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.frth_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_frth;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.frth_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 3;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.frth_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_frth;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.frth_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 3;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;

      /*THIRD START*/
      ELSIF stack_rank_rec.third_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.third_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.third_ORG_mgr_psn_cd
                                                      WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
         END IF;

         IF stack_rank_rec.scd_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.third_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_third;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 2;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
            
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_r_stack_rank.SALESREP_NO        := stack_rank_rec.third_ORG_mgr_psn_cd;
               l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_third;
               l_r_stack_rank.GROUP_NAME         := stack_rank_rec.third_ORG_NM;
               l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
               l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
               l_r_stack_rank.MANAGER_FLAG       := 'Y';
               l_r_stack_rank.DENORM_LEVEL       := 2;
               l_r_stack_rank.BRANCH_NAME        := NULL;    
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            
            END IF;
            
         ELSIF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.third_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_third;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.third_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 2;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;
  
     /*SCD START*/
      ELSIF stack_rank_rec.scd_org_cd IS NOT NULL THEN

         IF stack_rank_rec.mgr_flg = 'N' THEN
            l_r_stack_rank.VIEW_BY_SALESNO    := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.org_func_role_tp_cd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := CASE WHEN stack_rank_rec.scd_ORG_mgr_psn_cd IS NOT NULL THEN
                                                           stack_rank_rec.scd_ORG_mgr_psn_cd
                                                      ELSE stack_rank_rec.first_ORG_mgr_psn_cd
                                                  END;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := stack_rank_rec.mgr_flg;
            l_r_stack_rank.DENORM_LEVEL       := -1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
           
            IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN
               l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
               l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
               l_t_stack_rank.extend();
               l_t_stack_rank(i) := l_r_stack_rank;
               i := i + 1;
            END IF;
         END IF;

         IF stack_rank_rec.first_ORG_NM IS NOT NULL THEN

            l_r_stack_rank.VIEW_BY_SALESNO    := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.VIEW_BY_GROUP_NAME := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.SALESREP_NO        := stack_rank_rec.scd_ORG_mgr_psn_cd;
            l_r_stack_rank.SALESREP_ROLE      := stack_rank_rec.ofrt_scd;
            l_r_stack_rank.GROUP_NAME         := stack_rank_rec.scd_ORG_NM;
            l_r_stack_rank.PARENT_SALESREP_NO := stack_rank_rec.first_ORG_mgr_psn_cd;
            l_r_stack_rank.PARENT_GROUP       := stack_rank_rec.first_ORG_NM;
            l_r_stack_rank.MANAGER_FLAG       := 'Y';
            l_r_stack_rank.DENORM_LEVEL       := 1;
            l_r_stack_rank.BRANCH_NAME        := NULL;    
            l_t_stack_rank.extend();
            l_t_stack_rank(i) := l_r_stack_rank;
            i := i + 1;
            
         END IF;
      END IF;

   END LOOP;
    
   Start_time := DBMS_UTILITY.get_time;
   FORALL j in l_t_stack_rank.first .. l_t_stack_rank.last
   INSERT INTO CANON_E404_SF_STACK_RANK_GTBL VALUES l_t_stack_rank(j);
   end_time := DBMS_UTILITY.get_time;
   DBMS_OUTPUT.PUT_LINE('Bulk Insert: '||to_char(end_time-start_time));

   DELETE
     FROM canon_e404_stack_rank_del_tbl
    where (status_flag = 'P' OR
          (status_flag = 'D' AND period_name = TO_CHAR(SYSDATE, 'MON')|| '-' || TO_CHAR(SYSDATE, 'RR')));

   INSERT
     INTO canon_e404_stack_rank_del_tbl
   SELECT period_name
         ,quarter_no
         ,year
         ,view_by_salesno
         ,salesrep_no
         ,salesrep_role
         ,group_name
         ,parent_salesrep_no
         ,parent_group
         ,manager_flag
         ,denorm_level
         ,'D'
         ,NULL status_message
         ,sf_stack_rank_id
         ,SYSDATE creation_date
         ,-1 created_by
         ,SYSDATE last_update_date
         ,-1 last_updated_by
         ,-1 last_update_login
         ,view_by_group_name
         ,branch_name
         ,unique_key
     FROM canon_e404_sf_stack_rank_tbl
    WHERE 1 = 1
      and period_name = to_char(sysdate, 'MON')|| '-' || to_char(sysdate, 'RR')
      AND sf_stack_rank_id is not null;

   DELETE
     FROM canon_e404_sf_stack_rank_tbl
    WHERE 1 = 1
      AND period_name = to_char(sysdate, 'MON')|| '-' || to_char(sysdate, 'RR');

   INSERT
     INTO canon_e404_sf_stack_rank_tbl
         (period_name
         ,quarter_no
         ,year
         ,view_by_salesno
         ,salesrep_no
         ,salesrep_role
         ,group_name
         ,parent_salesrep_no
         ,parent_group
         ,manager_flag
         ,denorm_level
         ,status_flag
         ,status_message
         ,sf_stack_rank_id
         ,creation_date
         ,created_by
         ,last_update_date
         ,last_updated_by
         ,last_update_login
         ,view_by_group_name
         ,branch_name
         ,unique_key
         )
   SELECT DISTINCT
          period_name
         ,quarter_no
         ,year
         ,(SELECT psn_num FROM s21_psn WHERE psn_cd = view_by_salesno)
         ,(SELECT psn_num FROM s21_psn WHERE psn_cd = salesrep_no)
         ,salesrep_role
         ,group_name
         ,parent_salesrep_no
         ,parent_group
         ,manager_flag
         ,denorm_level
         ,'I'
         ,NULL status_message
         ,NULL sf_stack_rank_id
         ,SYSDATE creation_date
         ,-1 created_by
         ,SYSDATE last_update_date
         ,-1 last_updated_by
         ,-1 last_update_login
         ,view_by_group_name
         ,branch_name
         ,null --CANON_E404_SF_STACK_RANK_SEQ.nextval
     FROM canon_e404_sf_stack_rank_gtbl;

   COMMIT;

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    x_return_status := 'E';
    x_msg_data      := 'Unexpected Error:'|| SUBSTR(SQLERRM,1, 3500);
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
END extract_stack_rank;

END canon_e404_sf_stack_rank_pkg;
/
