CREATE OR REPLACE PACKAGE CANON_E307_SERVMSG_ADMIN_PKG AS

   TYPE g_ref_cur_typ IS REF CURSOR;
   TYPE REFCURTYP IS REF CURSOR;

    PROCEDURE search_service_message(
      p_field       	    IN        VARCHAR2,
      p_value       	    IN        VARCHAR2,
      p_start_range       IN        NUMBER,
      p_end_range         IN        NUMBER,
      o_no_of_recs        OUT 	    NUMBER,
      x_user_cur          OUT       REFCURTYP
   );

  PROCEDURE create_service_message(
      p_region            IN       VARCHAR2,
      p_branch            IN       VARCHAR2,
      p_postal            IN       VARCHAR2,
      p_party_name        IN       VARCHAR2,
      p_party_num         IN       VARCHAR2,
      p_party_site_num    IN       VARCHAR2,
      p_acct_num          IN       VARCHAR2,
      p_model             IN       VARCHAR2,
      p_serial_num        IN       VARCHAR2,
      p_start_date        IN       VARCHAR2,
      p_end_date          IN       VARCHAR2,
      p_start_time        IN       VARCHAR2,
      p_end_time          IN       VARCHAR2,
      p_full_hour         IN       VARCHAR2,
      p_serv_msg          IN       VARCHAR2,
      p_created_by        IN       VARCHAR2,
      o_return_status         OUT     VARCHAR2,
      o_return_message        OUT     VARCHAR2,
	  p_svc_team		 IN			VARCHAR2		
   );

    PROCEDURE update_service_message(
      p_servMsg_id        IN       NUMBER,
      p_start_date        IN       VARCHAR2,
      p_end_date          IN       VARCHAR2,
      p_start_time        IN       VARCHAR2,
      p_end_time          IN       VARCHAR2,
      p_full_hour         IN       VARCHAR2,
      p_serv_msg          IN       VARCHAR2,
      p_updated_by        IN       VARCHAR2,
      o_return_status         OUT     VARCHAR2,
      o_return_message        OUT     VARCHAR2
   );


 PROCEDURE GET_REGION_LOV
             	(p_region        	        IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP);

 PROCEDURE GET_BRANCH_LOV
             	(p_branch        	        IN     VARCHAR2,
               p_branch_desc   	        IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP) ;
			   
 PROCEDURE GET_SVC_TEAM_LOV
              (p_branch        	        IN     VARCHAR2,
               p_svc_team_desc   	    IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          	OUT    NUMBER,
               x_user_cur               OUT    REFCURTYP);			   

  PROCEDURE GET_PARTY_NUMBER_LOV
             	(p_party_name    	        IN     VARCHAR2,
               p_party_num    	        IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP);

 PROCEDURE GET_ACCOUNT_NUMBER_LOV
             	(
               p_account_num            IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP);

 PROCEDURE GET_PARTY_SITE_NUM_LOV
             	(
               p_party_site_num         IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP);

/* PROCEDURE GET_PARTY_LOV
             	(p_party_name    	        IN     VARCHAR2,
               p_party_num    	        IN     VARCHAR2,
               p_account_num            IN     VARCHAR2,
               p_party_site_num         IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP); */

 PROCEDURE GET_SERIAL_LOV
             	(p_serial_num    	        IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP);

PROCEDURE GET_MODEL_LOV
             	(p_model       	          IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP);

  PROCEDURE validate_data
             	(p_field       	          IN     VARCHAR2,
               p_value       	          IN     VARCHAR2,
               o_return_status         OUT     VARCHAR2,
               o_return_message        OUT     VARCHAR2
               );
  PROCEDURE debug_msg (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2);
   FUNCTION GET_REGION_COUNT(P_VALUE    IN VARCHAR2)
   RETURN NUMBER;                        
END CANON_E307_SERVMSG_ADMIN_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_SERVMSG_ADMIN_PKG AS

  PROCEDURE search_service_message(
      p_field       	    IN        VARCHAR2,
      p_value       	    IN        VARCHAR2,
      p_start_range       IN        NUMBER,
      p_end_range         IN        NUMBER,
      o_no_of_recs        OUT 	    NUMBER,
      x_user_cur          OUT       REFCURTYP
   ) AS

   V_SQL_WHERE      VARCHAR2 (4000) := '';
   V_ORDER_BY       VARCHAR2 (1000);
   l_dynsql         VARCHAR2 (32767);
   l_dynsql_count   VARCHAR2 (32767);
   l_temp           NUMBER;
   lv_sql           VARCHAR2 (32767);
   l_sql_count      VARCHAR2 (32767);

  BEGIN

    l_dynsql := 'SELECT * FROM (SELECT ROWNUM row_num, d.* FROM (SELECT c.* FROM
 			       (SELECT SERV_MSG_ID,REGION, BRANCH, POSTAL_CODE, PARTY_NAME, PARTY_NUMBER,
ACCOUNT_NUMBER, PARTY_SITE_NUM, MODEL_NUM, SERIAL_NUMBER, TO_CHAR(START_DATE, ''MM/DD/YYYY'') START_DATE,
TO_CHAR(END_DATE,''MM/DD/YYYY'') END_DATE, START_TIME, END_TIME, IS_FULLHOUR, SERVICE_MESSAGE, CREATED_BY, LAST_UPDATED_BY, SVC_TEAM FROM CANON_E307_SERV_MSGS_TBL
              WHERE 1=1
              AND TRUNC(SYSDATE) BETWEEN TRUNC(START_DATE) AND TRUNC(END_DATE)
              UNION
              SELECT SERV_MSG_ID,REGION, BRANCH, POSTAL_CODE, PARTY_NAME, PARTY_NUMBER,
ACCOUNT_NUMBER, PARTY_SITE_NUM, MODEL_NUM, SERIAL_NUMBER, TO_CHAR(START_DATE, ''MM/DD/YYYY'') START_DATE,
TO_CHAR(END_DATE,''MM/DD/YYYY'') END_DATE, START_TIME, END_TIME, IS_FULLHOUR, SERVICE_MESSAGE, CREATED_BY, LAST_UPDATED_BY, SVC_TEAM FROM CANON_E307_SERV_MSGS_TBL
              WHERE 1=1
              AND TRUNC(SYSDATE) < TRUNC(START_DATE))c
              WHERE 1=1
              ';

   IF( p_field = 'ALL')
    THEN
      NULL;
    ELSIF( p_field = 'REGION')
    THEN
      V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(REGION) LIKE UPPER(trim('''
            || p_value
            || ''')||''%'''||')';

    ELSIF( p_field = 'BRANCH')
    THEN
          V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(BRANCH) LIKE UPPER(trim('''
            || p_value
            || ''')||''%'''||')';
    ELSIF( p_field = 'POSTAL')
    THEN
          V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND POSTAL_CODE LIKE trim('''
            || p_value
            || ''')||''%''';
    ELSIF( p_field = 'PARTY_NUMBER')
    THEN
           V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND PARTY_NUMBER LIKE trim('''
            || p_value
            || ''')||''%''';
    ELSIF( p_field = 'PARTY_SITE_NUMBER')
    THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(PARTY_SITE_NUM) LIKE UPPER(trim('''
            || p_value
            || ''')||''%'''||')';
    ELSIF( p_field = 'ACCOUNT_NUMBER')
    THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND ACCOUNT_NUMBER LIKE trim('''
            || p_value
            || ''')||''%''';
    ELSIF( p_field = 'SERIAL_NUMBER')
    THEN
          V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(SERIAL_NUMBER) LIKE UPPER(trim('''
            || p_value
            || ''')||''%'''||')';
    ELSIF( p_field = 'MODEL')
    THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(MODEL_NUM) LIKE UPPER(trim('''
            || p_value
            || ''')||''%'''||')';
	ELSIF( p_field = 'SVCTEAM')
	THEN
	         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(SVC_TEAM) LIKE UPPER(trim('''
            || p_value
            || ''')||''%'''||')';
    END IF;

    V_ORDER_BY := V_ORDER_BY || ' ORDER BY SERV_MSG_ID DESC )d )';

     l_sql_count := l_dynsql || V_SQL_WHERE || ')d)' ;
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;

    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

   -- DBMS_OUTPUT.put_line (l_dynsql);


      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
    --       DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END search_service_message;


  PROCEDURE create_service_message(
      p_region            IN       VARCHAR2,
      p_branch            IN       VARCHAR2,
      p_postal            IN       VARCHAR2,
      p_party_name        IN       VARCHAR2,
      p_party_num         IN       VARCHAR2,
      p_party_site_num    IN       VARCHAR2,
      p_acct_num          IN       VARCHAR2,
      p_model             IN       VARCHAR2,
      p_serial_num        IN       VARCHAR2,
      p_start_date        IN       VARCHAR2,
      p_end_date          IN       VARCHAR2,
      p_start_time        IN       VARCHAR2,
      p_end_time          IN       VARCHAR2,
      p_full_hour         IN       VARCHAR2,
      p_serv_msg          IN       VARCHAR2,
      p_created_by        IN       VARCHAR2,
      o_return_status         OUT     VARCHAR2,
      o_return_message        OUT     VARCHAR2,
	  p_svc_team		 IN			VARCHAR2
   ) AS

   l_party_name VARCHAR2(360);

  BEGIN

  IF(p_party_num IS NOT NULL)
  THEN
    BEGIN
         SELECT  distinct loc_nm
          INTO l_party_name
         FROM SHIP_TO_CUST
          WHERE sell_to_cust_cd = trim(p_party_num)--522434;
         AND ROWNUM=1;
    EXCEPTION WHEN OTHERS THEN
      l_party_name := NULL;
    END;
 END IF;

  BEGIN
        INSERT INTO CANON_E307_SERV_MSGS_TBL(
        SERV_MSG_ID,
        REGION,
        BRANCH,
        POSTAL_CODE,
        PARTY_NAME,
        PARTY_NUMBER,
        ACCOUNT_NUMBER,
        PARTY_SITE_NUM,
        MODEL_NUM,
        SERIAL_NUMBER,
        START_DATE,
        END_DATE,
        START_TIME,
        END_TIME,
        IS_FULLHOUR,
        SERVICE_MESSAGE,
        CREATED_BY,
        CREATION_DATE,
        LAST_UPDATED_BY,
        LAST_UPDATE_DATE,
		SVC_TEAM
        )
        VALUES (
            CANON_E307_SERV_MSGS_TBL_S.NEXTVAL,
            p_region,
            p_branch,
            p_postal,
            l_party_name,
            p_party_num,
            p_acct_num,
            p_party_site_num,
            p_model,
            p_serial_num,
            TRUNC(TO_DATE(p_start_date,'MM/DD/YYYY')),
            TRUNC(TO_DATE(p_end_date,'MM/DD/YYYY')),
            p_start_time,
            p_end_time,
            p_full_hour,
            p_serv_msg,
            p_created_by,
            SYSDATE,
            p_created_by,
            SYSDATE,
			p_svc_team
        );

        o_return_status := 'S';
        o_return_message := 'Service Message has been created successfully';
    EXCEPTION WHEN OTHERS THEN
		o_return_status := 'E';
		o_return_message := 'Service Message creation errored' || SQLERRM;
	END;
  END create_service_message;

  PROCEDURE update_service_message(
      p_servMsg_id        IN       NUMBER,
      p_start_date        IN       VARCHAR2,
      p_end_date          IN       VARCHAR2,
      p_start_time        IN       VARCHAR2,
      p_end_time          IN       VARCHAR2,
      p_full_hour         IN       VARCHAR2,
      p_serv_msg          IN       VARCHAR2,
      p_updated_by        IN       VARCHAR2,
      o_return_status         OUT     VARCHAR2,
      o_return_message        OUT     VARCHAR2
   ) AS
   BEGIN
      BEGIN
        UPDATE CANON_E307_SERV_MSGS_TBL
        SET
        START_DATE = TRUNC(TO_DATE(p_start_date,'MM/DD/YYYY')),
        END_DATE = TRUNC(TO_DATE(p_end_date,'MM/DD/YYYY')),
        START_TIME = p_start_time,
        END_TIME = p_end_time,
        IS_FULLHOUR = p_full_hour,
        SERVICE_MESSAGE = p_serv_msg,
        LAST_UPDATED_BY = p_updated_by,
        LAST_UPDATE_DATE = SYSDATE
        WHERE SERV_MSG_ID = p_servMsg_id;

        o_return_status := 'S';
        o_return_message := 'Service Message has been updated successfully';
    EXCEPTION WHEN OTHERS THEN
    o_return_status := 'E';
    o_return_message := 'Service Message Update errored' || SQLERRM;
  END;

   END update_service_message;

 PROCEDURE GET_REGION_LOV
             	(p_region        	        IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP)
   AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

    l_dynsql :=    'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       ( WITH TIER AS(
        SELECT /*+ ALL_ROWS */
            OHSD.ORG_TIER_CD
            ,SDF.CMPY_FLG
            ,SDF.BU_FLG
            ,SDF.ZN_FLG
            ,SDF.RG_FLG
            ,SDF.BR_FLG
            ,SDF.REP_FLG
        FROM
             S21_CSA_APPS.STRU_DFN SDF
            ,S21_CSA_APPS.ORG_HRCH_STRU_DFN OHSD
        WHERE
            SDF.GLBL_CMPY_CD         = ''ADB''
            AND SDF.GLBL_CMPY_CD     = OHSD.GLBL_CMPY_CD
            AND SDF.STRU_DFN_CD      = OHSD.STRU_DFN_CD
            AND OHSD.BIZ_AREA_ORG_CD = ''2''
            AND SDF.EZCANCELFLAG     = ''0''
            AND OHSD.EZCANCELFLAG    = ''0''
            AND (SDF.CMPY_FLG = ''Y'' OR SDF.BU_FLG = ''Y'' OR SDF.ZN_FLG = ''Y'' OR SDF.RG_FLG = ''Y'' OR SDF.BR_FLG = ''Y'' OR SDF.REP_FLG = ''Y'')
            AND OHSD.EFF_FROM_DT               <= TO_CHAR (SYSDATE, ''YYYYMMDD'') --Parameter 
            AND NVL(OHSD.EFF_THRU_DT, ''99991231'') >= TO_CHAR (SYSDATE, ''YYYYMMDD'') --Parameter 
        )
        ,ORG_BR AS(
            SELECT  /*+ ALL_ROWS */
                CMPY_NM
               ,BU_NM
               ,ZN_NM
               ,RG_NM
               ,BR_NM
               ,ROW_NUMBER() OVER (PARTITION BY SUBSTR(BR_NM,1,3) ORDER BY RG_NM) ROW_NUM
            FROM
            (
                SELECT /*+ ALL_ROWS */ DISTINCT
                           CASE  WHEN CM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS CMPY_NM
                    ,CASE  WHEN BU.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                    --MOD 2018/09/23
                           WHEN BU.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS BU_NM
                    ,CASE  WHEN ZN.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS ZN_NM
                    ,CASE  WHEN RG.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS RG_NM
                    ,CASE  WHEN BR.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS BR_NM
                FROM
                    S21_CSA_APPS.S21_ORG ORG
                    ,(SELECT  /*+ FIRST_ROWS */ * FROM TIER WHERE CMPY_FLG =''Y'') CM
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BU_FLG =''Y'') BU
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE ZN_FLG =''Y'') ZN
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE RG_FLG =''Y'') RG
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BR_FLG =''Y'') BR
               WHERE
                    ORG.GLBL_CMPY_CD =''ADB''
                    AND ORG.FIRST_ORG_CD =''2''
                    AND ORG.RGTN_STS_CD  =''P20''
                    AND ORG.EZCANCELFLAG = ''0''
                )
    )
--MOD 2018/09/23
        ,ORG_TM AS(SELECT /*+ ALL_ROWS */ DISTINCT
                   CASE  WHEN CM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS CMPY_NM
            ,CASE  WHEN BU.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS BU_NM
            ,CASE  WHEN ZN.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS ZN_NM
            ,CASE  WHEN RG.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS RG_NM
            ,CASE  WHEN BR.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS BR_NM
             ,CASE WHEN TM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS TM_NM
        FROM
            S21_CSA_APPS.S21_ORG ORG
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE CMPY_FLG =''Y'') CM
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BU_FLG =''Y'') BU
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE ZN_FLG =''Y'') ZN
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE RG_FLG =''Y'') RG
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BR_FLG =''Y'') BR
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE REP_FLG =''Y'') TM
       WHERE
            ORG.GLBL_CMPY_CD =''ADB''
            AND ORG.FIRST_ORG_CD =''2''
            AND ORG.RGTN_STS_CD  =''P20''
            AND ORG.EZCANCELFLAG = ''0''
            )
    SELECT distinct 
        -- SMM.SVC_MACH_MSTR_PK
        --NVL(OT2.ZN_NM,NVL(OT.ZN_NM,NVL(OB.ZN_NM,OB2.ZN_NM)))                  AS SVC_ZN_NM,
        NVL(OT2.RG_NM,NVL(OT.RG_NM,NVL(OB.RG_NM,OB2.RG_NM)))                  AS REGION
    FROM
         S21_CSA_APPS.SVC_MACH_MSTR      SMM
        ,S21_CSA_APPS.SHIP_TO_CUST       STC
        ,(SELECT PX1.GLBL_CMPY_CD, PX1.EZCANCELFLAG, PX1.SVC_LINE_BIZ_CD,
          PX1.SVC_BR_CD, PX1.POST_CD, PX1.SVC_BR_CD_DESC_TXT, PX1.SVC_TRTY_DESC_TXT, PX1.SVC_TEAM_TXT,
          ROW_NUMBER() OVER (PARTITION BY PX1.SVC_LINE_BIZ_CD, PX1.SVC_BR_CD, PX1.POST_CD ORDER BY PX1.SVC_TRTY_DESC_TXT) NUM
          FROM S21_CSA_APPS.SVC_BR_POST_XREF PX1
          WHERE PX1.GLBL_CMPY_CD = ''ADB'' AND PX1.EZCANCELFLAG = ''0'') BPX1
        ,(SELECT PX2.GLBL_CMPY_CD, PX2.EZCANCELFLAG, PX2.SVC_LINE_BIZ_CD,
          PX2.SVC_BR_CD, PX2.POST_CD, PX2.SVC_BR_CD_DESC_TXT, PX2.SVC_TRTY_DESC_TXT, PX2.SVC_TEAM_TXT,
          ROW_NUMBER() OVER (PARTITION BY PX2.SVC_LINE_BIZ_CD, PX2.SVC_BR_CD, PX2.POST_CD ORDER BY PX2.SVC_TRTY_DESC_TXT) NUM
          FROM S21_CSA_APPS.SVC_BR_POST_XREF PX2
          WHERE PX2.GLBL_CMPY_CD = ''ADB'' AND PX2.EZCANCELFLAG = ''0'') BPX2
        ,ORG_BR     OB
        ,ORG_BR     OB2
        ,ORG_TM     OT
        ,ORG_TM     OT2
    WHERE 1=1
        AND SMM.GLBL_CMPY_CD = ''ADB''
--        AND SMM.SVC_MACH_MSTR_PK = '' --Parameter
        AND SMM.EZCANCELFLAG = ''0''
        AND SMM.SVC_MACH_TP_CD = ''1''
        -- Customer
        AND SMM.GLBL_CMPY_CD = STC.GLBL_CMPY_CD(+)
        AND SMM.CUR_LOC_NUM = STC.SHIP_TO_CUST_CD(+)
        AND STC.EZCANCELFLAG(+) = ''0''
        -- Territory
        AND SMM.GLBL_CMPY_CD = BPX1.GLBL_CMPY_CD(+)
        AND SMM.SVC_BY_LINE_BIZ_TP_CD = BPX1.SVC_LINE_BIZ_CD(+)
        AND SMM.FLD_SVC_BR_CD = BPX1.SVC_BR_CD(+)
        AND STC.POST_CD = BPX1.POST_CD(+)
        AND BPX1.EZCANCELFLAG(+) = ''0''
        AND BPX1.NUM(+) = 1
        AND SMM.GLBL_CMPY_CD = BPX2.GLBL_CMPY_CD(+)
        AND SMM.SVC_BY_LINE_BIZ_TP_CD = BPX2.SVC_LINE_BIZ_CD(+)
        AND SMM.FLD_SVC_BR_CD = BPX2.SVC_BR_CD(+)
        AND SUBSTR(STC.POST_CD ,1,5)= BPX2.POST_CD(+)
        AND BPX2.EZCANCELFLAG(+) = ''0''
        AND BPX2.NUM(+) = 1
        AND OB.BR_NM(+) = UPPER(NVL(BPX1.SVC_BR_CD_DESC_TXT, BPX2.SVC_BR_CD_DESC_TXT))
        AND OT.BR_NM(+) = UPPER(BPX1.SVC_BR_CD_DESC_TXT)
        AND OT.TM_NM(+) = UPPER(BPX1.SVC_TEAM_TXT)
        AND OT2.BR_NM(+) = UPPER(BPX2.SVC_BR_CD_DESC_TXT)
        AND OT2.TM_NM(+) = UPPER(BPX2.SVC_TEAM_TXT)
        AND DECODE(SMM.SVC_BY_LINE_BIZ_TP_CD,''LFS'',''PPS'',SMM.SVC_BY_LINE_BIZ_TP_CD) = NVL(OB2.BU_NM(+),''PPS'')
        AND SMM.FLD_SVC_BR_CD = SUBSTR(OB2.BR_NM(+),1,3)
        AND OB2.ROW_NUM(+) = ''1'' 
        AND NVL(OT2.RG_NM,NVL(OT.RG_NM,NVL(OB.RG_NM,OB2.RG_NM))) IS NOT NULL ';

     IF p_region IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || 'AND NVL(OT2.RG_NM,NVL(OT.RG_NM,NVL(OB.RG_NM,OB2.RG_NM))) LIKE UPPER(trim('''
            || p_region
            || ''')||''%'''||')';
     END IF;

 --DBMS_OUTPUT.put_line (V_SQL_WHERE);

     V_ORDER_BY := V_ORDER_BY || ' ORDER BY 1 )c)';

     l_sql_count := l_dynsql || V_SQL_WHERE || ')c)' ;
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;

    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

   -- DBMS_OUTPUT.put_line (l_dynsql);


      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
     --      DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_REGION_LOV;

 PROCEDURE GET_BRANCH_LOV
             	(p_branch        	        IN     VARCHAR2,
               p_branch_desc   	        IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP)
   AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

    l_dynsql :=    'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       ( SELECT distinct SVC_BR_CD BRANCH, SVC_BR_CD_DESC_TXT BRANCH_DESC
                FROM SVC_BR_POST_XREF
                WHERE EZCANCELFLAG =''0'' 
                AND GLBL_CMPY_CD =''ADB'' ';


     IF p_branch IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND SVC_BR_CD LIKE trim('''
            || p_branch
            || ''')||''%''';
     END IF;

     IF p_branch_desc IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(SVC_BR_CD_DESC_TXT) LIKE UPPER(trim(''%'
            || p_branch_desc
            || ''')||''%'''||')';
     END IF;

     V_ORDER_BY := V_ORDER_BY || ' ORDER BY SVC_BR_CD )c)';
     l_sql_count := l_dynsql || V_SQL_WHERE || ' )c)';
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;


    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

    -- DBMS_OUTPUT.put_line (l_dynsql);


      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
    --       DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_BRANCH_LOV;
  
 PROCEDURE GET_SVC_TEAM_LOV
             	(p_branch        	        IN     VARCHAR2,
               p_svc_team_desc   	        IN     VARCHAR2,
               p_start_range            	IN     NUMBER,
               p_end_range              	IN     NUMBER,
               o_no_of_recs          		OUT    NUMBER,
               x_user_cur               	OUT    REFCURTYP)
   AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

    l_dynsql :=    'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       ( SELECT distinct SVC_BR_CD BRANCH, SVC_BR_CD_DESC_TXT BRANCH_DESC, SVC_TEAM_TXT TEAM_DESC
                FROM SVC_BR_POST_XREF
                WHERE EZCANCELFLAG =''0'' 
                AND GLBL_CMPY_CD =''ADB'' ';


     IF p_branch IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND SVC_BR_CD LIKE trim('''
            || p_branch
            || ''')||''%''';
     END IF;

     IF p_svc_team_desc IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(SVC_TEAM_TXT) LIKE UPPER(trim(''%'
            || p_svc_team_desc
            || ''')||''%'''||')';
     END IF;

     V_ORDER_BY := V_ORDER_BY || ' ORDER BY SVC_BR_CD )c)';
     l_sql_count := l_dynsql || V_SQL_WHERE || ' )c)';
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;


    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

    -- DBMS_OUTPUT.put_line (l_dynsql);


      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
    --       DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_SVC_TEAM_LOV;

  PROCEDURE GET_PARTY_NUMBER_LOV
             	(p_party_name    	        IN     VARCHAR2,
               p_party_num    	        IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP)
   AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

    l_dynsql :=    'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       ( SELECT ship_to.sell_to_cust_cd PARTY_NUMBER,
                ship_to.loc_nm PARTY_NAME,
                ship_to.SHIP_TO_CUST_PK PARTY_ID
           FROM svc_mach_mstr ib, ship_to_cust ship_to
          WHERE  1=1
                AND ib.svc_mach_tp_cd = ''1''
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=
                       TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                       TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND ib.glbl_cmpy_cd = ''ADB''
                AND ib.ezcancelflag = ''0''
                AND ship_to.glbl_cmpy_cd = ''ADB''
                AND ship_to.ezcancelflag = ''0''
                AND ship_to.ship_to_cust_cd = ib.cur_loc_num';

     IF p_party_name IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(ship_to.loc_nm) LIKE UPPER(trim('''
            || p_party_name
            || ''')||''%'''||')';
     END IF;

     IF p_party_num IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND ship_to.sell_to_cust_cd LIKE trim('''
            || p_party_num
            || ''')||''%''';
     END IF;

     V_ORDER_BY := V_ORDER_BY || ' ORDER BY ship_to.loc_nm )c)';

     l_sql_count := l_dynsql || V_SQL_WHERE ||' )c)';
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;


    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

    -- DBMS_OUTPUT.put_line (l_dynsql);

      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
    --       DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_PARTY_NUMBER_LOV;

  PROCEDURE GET_ACCOUNT_NUMBER_LOV
             	(
               p_account_num            IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP)
   AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

    l_dynsql :=    'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       ( SELECT DISTINCT smm.OWNR_ACCT_NUM ACCOUNT_NUMBER,
                ship_to.sell_to_cust_cd PARTY_NUMBER,
                ship_to.loc_nm PARTY_NAME,
                ship_to.sell_to_cust_cd PARTY_ID
                FROM svc_mach_mstr smm,
                  SHIP_TO_CUST ship_to
                WHERE 1                                                           = 1
                AND smm.SVC_MACH_TP_CD                                            = ''1''
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1)     >= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD''))         <= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND smm.glbl_cmpy_cd                                              = ''ADB''
                AND smm.ezcancelflag                                              = ''0''
                AND ship_to.ship_to_cust_cd                                       = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD''))     <= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND ship_to.ezcancelflag                                          = ''0''
                AND ship_to.glbl_cmpy_cd                                          = ''ADB'' ';

     IF p_account_num IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND smm.OWNR_ACCT_NUM LIKE trim('''
            || p_account_num
            || ''')||''%''';
     END IF;

     V_ORDER_BY := V_ORDER_BY || ' ORDER BY ship_to.loc_nm )c)';

     l_sql_count := l_dynsql || V_SQL_WHERE ||' )c)';
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;


    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

    --DBMS_OUTPUT.put_line (l_dynsql);

      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
    --       DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_ACCOUNT_NUMBER_LOV;

  PROCEDURE GET_PARTY_SITE_NUM_LOV
             	(
               p_party_site_num         IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP)
   AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

    l_dynsql :=    'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       ( SELECT DISTINCT sell_to.loc_num PARTY_SITE_NUMBER,
                smm.cur_loc_acct_num,
                ship_to.sell_to_cust_cd PARTY_NUMBER,
                sell_to.cmpy_pk PARTY_ID,
                ship_to.loc_nm PARTY_NAME
                FROM svc_mach_mstr smm,
                  SHIP_TO_CUST ship_to,
                  sell_to_cust sell_to
                WHERE 1 = 1
                AND smm.SVC_MACH_TP_CD  = ''1''
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1)     >= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD''))         <= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND smm.glbl_cmpy_cd = ''ADB''
                AND smm.ezcancelflag = ''0''
                AND sell_to.loc_num = smm.ind_cur_loc_num
                AND ship_to.sell_to_cust_cd  = sell_to.sell_to_cust_cd
                AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD''))     <= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND ship_to.glbl_cmpy_cd = ''ADB''
                AND ship_to.ezcancelflag = ''0''
                AND sell_to.glbl_cmpy_cd = ''ADB''
                AND sell_to.ezcancelflag = ''0'' ';


     IF p_party_site_num IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND  sell_to.loc_num  LIKE trim('''
            || p_party_site_num
            || ''')||''%''';
     END IF;

     V_ORDER_BY := V_ORDER_BY || ' ORDER BY ship_to.loc_nm )c)';

     l_sql_count := l_dynsql || V_SQL_WHERE ||' )c)';
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;


    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

    -- DBMS_OUTPUT.put_line (l_dynsql);

      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
        --   DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_PARTY_SITE_NUM_LOV;


/*  PROCEDURE GET_PARTY_LOV
             	(p_party_name    	        IN     VARCHAR2,
               p_party_num    	        IN     VARCHAR2,
               p_account_num            IN     VARCHAR2,
               p_party_site_num         IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP)
   AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

    l_dynsql :=    'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       ( select
                                    hp.party_id,
                                    hp.party_number,
                                    hp.party_name,
                                    hca.account_number,
                                    hps.party_site_number
                            FROM
                                    hz_parties                  hp,
                                    hz_party_sites              hps,
                                    hz_cust_accounts            hca,
                                    hz_cust_acct_sites_all      hcasa
                            WHERE   1=1
                            AND     hp.party_id         =       hps.party_id
                            AND     hp.party_id         =       hca.party_id
                            AND     hcasa.party_site_id =       hps.party_site_id
                            AND     hcasa.cust_account_id =     hca.cust_account_id';

     IF p_party_name IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(hp.party_name) LIKE UPPER(trim('''
            || p_party_name
            || ''')||''%'''||')';
     END IF;

     IF p_party_num IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND hp.party_number LIKE trim('''
            || p_party_num
            || ''')||''%''';
     END IF;

     IF p_account_num IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND hca.account_number LIKE trim('''
            || p_account_num
            || ''')||''%''';
     END IF;

     IF p_party_site_num IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND hps.party_site_number LIKE trim('''
            || p_party_site_num
            || ''')||''%''';
     END IF;

     V_ORDER_BY := V_ORDER_BY || ' ORDER BY hp.party_name )c)';

     l_sql_count := l_dynsql || V_SQL_WHERE ||' )c)';
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;


    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

    DBMS_OUTPUT.put_line (l_dynsql);


      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
           DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_PARTY_LOV;  */

  PROCEDURE GET_SERIAL_LOV
             	(p_serial_num    	        IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP)
   AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_SQL_WHERE_1    VARCHAR2 (4000) := '';
      V_SQL_WHERE_2    VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_1       VARCHAR2 (32767);
      l_dynsql_2       VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

  l_dynsql := 'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       (';

    l_dynsql_1 :=    ' SELECT DISTINCT smm.ser_num SERIAL_NUMBER,
                        smm.CUST_MACH_CTRL_NUM EXTERNAL_REFERENCE,
                        ship_to.loc_nm PARTY_NAME,
                        ship_to.FIRST_LINE_ADDR || '', '' ||
                        ship_to.SCD_LINE_ADDR|| '', '' ||
                        ship_to.CTY_ADDR|| '', '' ||
                        ship_to.ST_CD|| '', '' ||
                        ship_to.POST_CD ADDRESS
                    FROM svc_mach_mstr smm,
                      SHIP_TO_CUST ship_to
                    WHERE 1                                                           = 1
                    AND smm.SVC_MACH_TP_CD                                            = ''1''
                    AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1)     >= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                    AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD''))         <= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                    AND smm.glbl_cmpy_cd                                              = ''ADB''
                    AND smm.ezcancelflag                                              = ''0''
                    AND ship_to.ship_to_cust_cd                                       = smm.cur_loc_num
                    AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                    AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD''))     <= TO_CHAR (SYSDATE, ''YYYYMMDD'')
                    AND ship_to.ezcancelflag                                          = ''0''
                    AND ship_to.glbl_cmpy_cd                                          = ''ADB'' ';

     IF p_serial_num IS NOT NULL
      THEN
         V_SQL_WHERE_1 :=
               V_SQL_WHERE_1
            || ' AND (UPPER(smm.ser_num) LIKE UPPER(trim('''
            || p_serial_num
            || '%''))'
            || ' OR smm.cust_mach_ctrl_num LIKE UPPER(trim('''
            || p_serial_num
            || '%'')))';
     END IF;

     V_ORDER_BY := V_ORDER_BY || ' ORDER BY smm.SER_NUM, smm.CUST_MACH_CTRL_NUM)c)';

     l_sql_count := l_dynsql || l_dynsql_1 || V_SQL_WHERE_1 || l_dynsql_2 || V_SQL_WHERE_2 || ')c)';
     l_dynsql := l_dynsql || l_dynsql_1 || V_SQL_WHERE_1 || l_dynsql_2 || V_SQL_WHERE_2 || V_ORDER_BY;


    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

    -- DBMS_OUTPUT.put_line (l_dynsql);

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
        --   DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_SERIAL_LOV;


   PROCEDURE GET_MODEL_LOV
             	(p_model       	          IN     VARCHAR2,
               p_start_range            IN     NUMBER,
               p_end_range              IN     NUMBER,
               o_no_of_recs          		OUT 	 NUMBER,
               x_user_cur               OUT    REFCURTYP)
  AS
      V_SQL_WHERE      VARCHAR2 (4000) := '';
      V_ORDER_BY       VARCHAR2 (1000);
      l_dynsql         VARCHAR2 (32767);
      l_dynsql_count   VARCHAR2 (32767);
      l_temp           NUMBER;
      lv_sql           VARCHAR2 (32767);
      l_sql_count      VARCHAR2 (32767);
   BEGIN

    l_dynsql :=    'SELECT * FROM (SELECT ROWNUM row_num, c.* FROM
 			       ( SELECT distinct mdl.T_MDL_NM model, null description
             FROM svc_mach_mstr smm,
                SVC_CONFIG_MSTR config,
                MDL_NM mdl,
                SHIP_TO_CUST ship_to
          WHERE     1 = 1
                AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
                AND smm.SVC_MACH_TP_CD = ''1''
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=
                                   TO_CHAR (SYSDATE, ''YYYYMMDD'')
                            AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                          TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND config.glbl_cmpy_cd = ''ADB''
                AND smm.glbl_cmpy_cd = ''ADB''
                AND smm.ezcancelflag = ''0''
                AND config.glbl_cmpy_cd = ''ADB''
                AND config.ezcancelflag = ''0''
                AND mdl.ezcancelflag = ''0''
                AND mdl.T_MDL_ID = config.MDL_ID
                AND ship_to.ship_to_cust_cd = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >=
                       TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                       TO_CHAR (SYSDATE, ''YYYYMMDD'')
                AND ship_to.glbl_cmpy_cd = ''ADB''
                AND ship_to.ezcancelflag = ''0''
         ';

     IF p_model IS NOT NULL
      THEN
         V_SQL_WHERE :=
               V_SQL_WHERE
            || ' AND UPPER(mdl.t_mdl_nm) LIKE UPPER(trim('''
            || p_model
            || ''')||''%'''||')';
     END IF;

     V_ORDER_BY := V_ORDER_BY || ' order by mdl.t_mdl_nm )c)';

     l_sql_count := l_dynsql || V_SQL_WHERE ||' )c)';
     l_dynsql := l_dynsql || V_SQL_WHERE || V_ORDER_BY;


    l_dynsql :=
                     l_dynsql ||   ' WHERE ROW_NUM >=   NVL( ' || nvl(to_char(p_start_range),'null') || ', 1 )
                        AND ROW_NUM <= NVL( ' || NVL(to_char(p_end_range), 'null') || ', 9999999999 ) ';

    -- DBMS_OUTPUT.put_line (l_dynsql);


      COMMIT;

     l_dynsql_count :=
         ' SELECT COUNT(*) no_of_recs FROM ( ' || l_sql_count || ')';
        --   DBMS_OUTPUT.put_line (l_dynsql_count);

     EXECUTE IMMEDIATE l_dynsql_count INTO o_no_of_recs;

      OPEN x_user_cur FOR l_dynsql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN x_user_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
  END GET_MODEL_LOV;

 PROCEDURE validate_data
             	(p_field       	          IN     VARCHAR2,
               p_value       	          IN     VARCHAR2,
               o_return_status         OUT     VARCHAR2,
               o_return_message        OUT     VARCHAR2
               )
  AS
      l_no_of_recs    NUMBER;
      x_return_cur    REFCURTYP;
  BEGIN
    IF( p_field = 'REGION')
    THEN
    BEGIN
   /*   SELECT count(distinct v.REGION) INTO l_no_of_recs
        FROM CANON_E307_BRANCH_REGION_MAP_V v
        WHERE 1=1
        AND v.REGION is not null
        AND UPPER(trim(v.REGION)) = UPPER(trim(p_value)); */
      /*    SELECT count(DISTINCT STRU.FIFTH_ORG_NM)
            INTO l_no_of_recs
           FROM ORG_STRU STRU
          WHERE STRU.GLBL_CMPY_CD = 'ADB'
          AND STRU.EZCANCELFLAG   = '0'
          AND STRU.FIRST_ORG_CD   = '2'
          AND STRU.ORG_LAYER_NUM  = '6'
          AND STRU.FIFTH_ORG_NM IS NOT NULL
          AND UPPER(trim(STRU.FIFTH_ORG_NM)) = UPPER(trim(p_value)); */
          l_no_of_recs:= CANON_E307_SERVMSG_ADMIN_PKG.GET_REGION_COUNT(p_value);
      EXCEPTION WHEN OTHERS THEN
        l_no_of_recs := 0;
      END;

    ELSIF( p_field = 'BRANCH')
    THEN
    BEGIN
     /*  SELECT COUNT(distinct v.BRANCH)
       INTO l_no_of_recs
                FROM CANON_E307_BRANCH_REGION_MAP_V v
                WHERE BRANCH IS NOT NULL
        AND trim(V.BRANCH) = trim(p_value); */
      SELECT COUNT(DISTINCT SVC_BR_CD)
       INTO l_no_of_recs
        FROM SVC_BR_POST_XREF
        WHERE EZCANCELFLAG ='0'
        AND GLBL_CMPY_CD = 'ADB'
        AND SVC_BR_CD = trim(p_value);
      EXCEPTION WHEN OTHERS THEN
        l_no_of_recs := 0;
      END;

    ELSIF( p_field = 'POSTAL')
    THEN
           l_no_of_recs := 1;
    ELSIF( p_field = 'PARTY_NUMBER')
    THEN
       BEGIN
        SELECT COUNT(ship_to.sell_to_cust_cd) INTO l_no_of_recs
           FROM svc_mach_mstr ib, ship_to_cust ship_to
          WHERE  1=1
                AND ib.svc_mach_tp_cd = '1'
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ib.glbl_cmpy_cd = 'ADB'
                AND ib.ezcancelflag = '0'
                AND ship_to.glbl_cmpy_cd = 'ADB'
                AND ship_to.ezcancelflag = '0'
                AND ship_to.ship_to_cust_cd = ib.cur_loc_num
                AND ship_to.sell_to_cust_cd = trim(p_value);
      EXCEPTION WHEN OTHERS THEN
                l_no_of_recs := 0;
      END;
    ELSIF( p_field = 'PARTY_SITE_NUMBER')
    THEN
            BEGIN
            SELECT COUNT(distinct sell_to.loc_num)
              INTO l_no_of_recs
             FROM svc_mach_mstr smm,
                  SHIP_TO_CUST ship_to,
                  sell_to_cust sell_to
                WHERE 1 = 1
                AND smm.SVC_MACH_TP_CD  = '1'
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1)     >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))         <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND smm.glbl_cmpy_cd = 'ADB'
                AND smm.ezcancelflag = '0'
                AND sell_to.loc_num = smm.ind_cur_loc_num
                AND ship_to.sell_to_cust_cd  = sell_to.sell_to_cust_cd
                AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ship_to.glbl_cmpy_cd = 'ADB'
                AND ship_to.ezcancelflag = '0'
                AND sell_to.glbl_cmpy_cd = 'ADB'
                AND sell_to.ezcancelflag = '0'
                AND  sell_to.loc_num = trim(p_value);
            EXCEPTION WHEN OTHERS THEN
              l_no_of_recs := 0;
            END;
     /*     BEGIN
             SELECT COUNT(smm.cur_loc_num) INTO l_no_of_recs
                FROM svc_mach_mstr smm,
                  SHIP_TO_CUST ship_to
                WHERE 1                                                           = 1
                AND smm.SVC_MACH_TP_CD                                            = '1'
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1)     >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))         <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND smm.glbl_cmpy_cd                                              = 'ADB'
                AND ship_to.ship_to_cust_cd                                       = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ship_to.glbl_cmpy_cd                                          = 'ADB'
                AND smm.cur_loc_num = trim(p_value);
          EXCEPTION WHEN OTHERS THEN
                  l_no_of_recs := 0;
          END; */
    ELSIF( p_field = 'ACCOUNT_NUMBER')
    THEN
          BEGIN
          SELECT COUNT(DISTINCT smm.OWNR_ACCT_NUM) INTO l_no_of_recs
                   FROM svc_mach_mstr smm,
                  SHIP_TO_CUST ship_to
                WHERE 1                                                           = 1
                AND smm.SVC_MACH_TP_CD                                            = '1'
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1)     >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))         <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND smm.glbl_cmpy_cd                                              = 'ADB'
                AND smm.ezcancelflag                                               = '0'
                AND ship_to.ship_to_cust_cd                                       = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ship_to.glbl_cmpy_cd                                          = 'ADB'
                AND ship_to.ezcancelflag                                          = '0'
                AND smm.OWNR_ACCT_NUM = trim(p_value);
        EXCEPTION WHEN OTHERS THEN
                l_no_of_recs := 0;
        END;

    ELSIF( p_field = 'SERIAL_NUMBER')
    THEN
       BEGIN
       SELECT  COUNT(DISTINCT smm.ser_num) INTO  l_no_of_recs
                        FROM svc_mach_mstr smm,
                          SHIP_TO_CUST ship_to
                        WHERE 1                                                           = 1
                        AND smm.SVC_MACH_TP_CD                                            = '1'
                        AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1)     >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))         <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND smm.glbl_cmpy_cd                                              = 'ADB'
                        AND smm.ezcancelflag                                               = '0'
                        AND ship_to.ship_to_cust_cd                                       = smm.cur_loc_num
                        AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND ship_to.glbl_cmpy_cd                                          = 'ADB'
                        AND ship_to.ezcancelflag                                          = '0'
                        AND (UPPER(smm.ser_num) LIKE UPPER(trim(p_value)) OR smm.cust_mach_ctrl_num LIKE UPPER(trim(p_value))) ;
        EXCEPTION WHEN OTHERS THEN
        l_no_of_recs := 0;
        END;
    ELSIF( p_field = 'MODEL')
    THEN
        BEGIN
         SELECT COUNT(DISTINCT mdl.T_MDL_NM) INTO l_no_of_recs
             FROM svc_mach_mstr smm,
                SVC_CONFIG_MSTR config,
                MDL_NM mdl
          WHERE     1 = 1
                AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
                AND smm.SVC_MACH_TP_CD = '1'
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                            AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND config.glbl_cmpy_cd = 'ADB'
                AND smm.glbl_cmpy_cd = 'ADB'
                AND smm.ezcancelflag='0'
                AND config.glbl_cmpy_cd = 'ADB'
                AND config.ezcancelflag='0'
                AND mdl.ezcancelflag='0'
                AND mdl.T_MDL_ID = config.MDL_ID
               AND  UPPER(mdl.T_MDL_NM) = UPPER(trim(p_value));
		EXCEPTION WHEN OTHERS THEN
			l_no_of_recs := 0;
        END;
	ELSIF(p_field = 'SVCTEAM')
	THEN
		BEGIN
			SELECT COUNT(DISTINCT SVC_BR_CD)
			INTO l_no_of_recs
			FROM SVC_BR_POST_XREF
			WHERE EZCANCELFLAG ='0'
			AND GLBL_CMPY_CD = 'ADB'
			AND SVC_TEAM_TXT = trim(p_value);
		EXCEPTION WHEN OTHERS THEN
			l_no_of_recs := 0;
			END;
    END IF;

    -- DBMS_OUTPUT.put_line (l_no_of_recs);

    IF(l_no_of_recs = 1)
    THEN
        o_return_status := 'S';
        o_return_message := 'VALID';
    ELSE
        o_return_status := 'F';
        o_return_message := 'INVALID';
    END IF;
    --      DBMS_OUTPUT.put_line ('o_return_status: '||o_return_status);
 EXCEPTION
      WHEN OTHERS
      THEN
        o_return_status := 'F';
        o_return_message := 'INVALID';
 END validate_data;
  PROCEDURE debug_msg (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO CANON_E307_SR_ERRORS_TBL
           VALUES (l_program_name,
                   SUBSTR (l_attribute1, 200),
                   l_attribute2,
                   l_attribute3,
                   l_attribute4,
                   l_attribute5,
                   l_error_msg,
                   SYSDATE);

      COMMIT;
   END debug_msg;
   FUNCTION GET_REGION_COUNT(P_VALUE    IN VARCHAR2)
   RETURN NUMBER
   AS
   l_count      NUMBER;
   BEGIN
    SELECT count(*) 
    INTO l_count
    FROM (
    WITH TIER AS(
        SELECT /*+ ALL_ROWS */
            OHSD.ORG_TIER_CD
            ,SDF.CMPY_FLG
            ,SDF.BU_FLG
            ,SDF.ZN_FLG
            ,SDF.RG_FLG
            ,SDF.BR_FLG
            ,SDF.REP_FLG
        FROM
             S21_CSA_APPS.STRU_DFN SDF
            ,S21_CSA_APPS.ORG_HRCH_STRU_DFN OHSD
        WHERE
            SDF.GLBL_CMPY_CD         = 'ADB'
            AND SDF.GLBL_CMPY_CD     = OHSD.GLBL_CMPY_CD
            AND SDF.STRU_DFN_CD      = OHSD.STRU_DFN_CD
            AND OHSD.BIZ_AREA_ORG_CD = '2'
            AND SDF.EZCANCELFLAG     = '0'
            AND OHSD.EZCANCELFLAG    = '0'
            AND (SDF.CMPY_FLG = 'Y' OR SDF.BU_FLG = 'Y' OR SDF.ZN_FLG = 'Y' OR SDF.RG_FLG = 'Y' OR SDF.BR_FLG = 'Y' OR SDF.REP_FLG = 'Y')
            AND OHSD.EFF_FROM_DT               <= TO_CHAR (SYSDATE, 'YYYYMMDD') --Parameter 
            AND NVL(OHSD.EFF_THRU_DT, '99991231') >= TO_CHAR (SYSDATE, 'YYYYMMDD') --Parameter 
        )
        ,ORG_BR AS(
            SELECT  /*+ ALL_ROWS */
                CMPY_NM
               ,BU_NM
               ,ZN_NM
               ,RG_NM
               ,BR_NM
               ,ROW_NUMBER() OVER (PARTITION BY SUBSTR(BR_NM,1,3) ORDER BY RG_NM) ROW_NUM
            FROM
            (
                SELECT /*+ ALL_ROWS */ DISTINCT
                           CASE  WHEN CM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS CMPY_NM
                    ,CASE  WHEN BU.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                    --MOD 2018/09/23
                           WHEN BU.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS BU_NM
                    ,CASE  WHEN ZN.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS ZN_NM
                    ,CASE  WHEN RG.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS RG_NM
                    ,CASE  WHEN BR.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS BR_NM
                FROM
                    S21_CSA_APPS.S21_ORG ORG
                    ,(SELECT  /*+ FIRST_ROWS */ * FROM TIER WHERE CMPY_FLG ='Y') CM
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BU_FLG ='Y') BU
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE ZN_FLG ='Y') ZN
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE RG_FLG ='Y') RG
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BR_FLG ='Y') BR
               WHERE
                    ORG.GLBL_CMPY_CD ='ADB'
                    AND ORG.FIRST_ORG_CD ='2'
                    AND ORG.RGTN_STS_CD  ='P20'
                    AND ORG.EZCANCELFLAG = '0'
                )
    )
--MOD 2018/09/23
        ,ORG_TM AS(SELECT /*+ ALL_ROWS */ DISTINCT
                   CASE  WHEN CM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS CMPY_NM
            ,CASE  WHEN BU.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS BU_NM
            ,CASE  WHEN ZN.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS ZN_NM
            ,CASE  WHEN RG.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS RG_NM
            ,CASE  WHEN BR.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS BR_NM
             ,CASE WHEN TM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS TM_NM
        FROM
            S21_CSA_APPS.S21_ORG ORG
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE CMPY_FLG ='Y') CM
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BU_FLG ='Y') BU
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE ZN_FLG ='Y') ZN
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE RG_FLG ='Y') RG
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BR_FLG ='Y') BR
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE REP_FLG ='Y') TM
       WHERE
            ORG.GLBL_CMPY_CD ='ADB'
            AND ORG.FIRST_ORG_CD ='2'
            AND ORG.RGTN_STS_CD  ='P20'
            AND ORG.EZCANCELFLAG = '0'
            )
    SELECT distinct 
        NVL(OT2.RG_NM,NVL(OT.RG_NM,NVL(OB.RG_NM,OB2.RG_NM)))                  AS SVC_RG_NM
    FROM
         S21_CSA_APPS.SVC_MACH_MSTR      SMM
        ,S21_CSA_APPS.SHIP_TO_CUST       STC
        ,(SELECT PX1.GLBL_CMPY_CD, PX1.EZCANCELFLAG, PX1.SVC_LINE_BIZ_CD,
          PX1.SVC_BR_CD, PX1.POST_CD, PX1.SVC_BR_CD_DESC_TXT, PX1.SVC_TRTY_DESC_TXT, PX1.SVC_TEAM_TXT,
          ROW_NUMBER() OVER (PARTITION BY PX1.SVC_LINE_BIZ_CD, PX1.SVC_BR_CD, PX1.POST_CD ORDER BY PX1.SVC_TRTY_DESC_TXT) NUM
          FROM S21_CSA_APPS.SVC_BR_POST_XREF PX1
          WHERE PX1.GLBL_CMPY_CD = 'ADB' AND PX1.EZCANCELFLAG = '0') BPX1
        ,(SELECT PX2.GLBL_CMPY_CD, PX2.EZCANCELFLAG, PX2.SVC_LINE_BIZ_CD,
          PX2.SVC_BR_CD, PX2.POST_CD, PX2.SVC_BR_CD_DESC_TXT, PX2.SVC_TRTY_DESC_TXT, PX2.SVC_TEAM_TXT,
          ROW_NUMBER() OVER (PARTITION BY PX2.SVC_LINE_BIZ_CD, PX2.SVC_BR_CD, PX2.POST_CD ORDER BY PX2.SVC_TRTY_DESC_TXT) NUM
          FROM S21_CSA_APPS.SVC_BR_POST_XREF PX2
          WHERE PX2.GLBL_CMPY_CD = 'ADB' AND PX2.EZCANCELFLAG = '0') BPX2
        ,ORG_BR     OB
        ,ORG_BR     OB2
        ,ORG_TM     OT
        ,ORG_TM     OT2
    WHERE 1=1
        AND SMM.GLBL_CMPY_CD = 'ADB'
--        AND SMM.SVC_MACH_MSTR_PK = '' --Parameter
        AND SMM.EZCANCELFLAG = '0'
        AND SMM.SVC_MACH_TP_CD = '1'
        -- Customer
        AND SMM.GLBL_CMPY_CD = STC.GLBL_CMPY_CD(+)
        AND SMM.CUR_LOC_NUM = STC.SHIP_TO_CUST_CD(+)
        AND STC.EZCANCELFLAG(+) = '0'
        -- Territory
        AND SMM.GLBL_CMPY_CD = BPX1.GLBL_CMPY_CD(+)
        AND SMM.SVC_BY_LINE_BIZ_TP_CD = BPX1.SVC_LINE_BIZ_CD(+)
        AND SMM.FLD_SVC_BR_CD = BPX1.SVC_BR_CD(+)
        AND STC.POST_CD = BPX1.POST_CD(+)
        AND BPX1.EZCANCELFLAG(+) = '0'
        AND BPX1.NUM(+) = 1
        AND SMM.GLBL_CMPY_CD = BPX2.GLBL_CMPY_CD(+)
        AND SMM.SVC_BY_LINE_BIZ_TP_CD = BPX2.SVC_LINE_BIZ_CD(+)
        AND SMM.FLD_SVC_BR_CD = BPX2.SVC_BR_CD(+)
        AND SUBSTR(STC.POST_CD ,1,5)= BPX2.POST_CD(+)
        AND BPX2.EZCANCELFLAG(+) = '0'
        AND BPX2.NUM(+) = 1
        AND OB.BR_NM(+) = UPPER(NVL(BPX1.SVC_BR_CD_DESC_TXT, BPX2.SVC_BR_CD_DESC_TXT))
        AND OT.BR_NM(+) = UPPER(BPX1.SVC_BR_CD_DESC_TXT)
        AND OT.TM_NM(+) = UPPER(BPX1.SVC_TEAM_TXT)
        AND OT2.BR_NM(+) = UPPER(BPX2.SVC_BR_CD_DESC_TXT)
        AND OT2.TM_NM(+) = UPPER(BPX2.SVC_TEAM_TXT)
        AND DECODE(SMM.SVC_BY_LINE_BIZ_TP_CD,'LFS','PPS',SMM.SVC_BY_LINE_BIZ_TP_CD) = NVL(OB2.BU_NM(+),'PPS')
        AND SMM.FLD_SVC_BR_CD = SUBSTR(OB2.BR_NM(+),1,3)
        AND OB2.ROW_NUM(+) = '1'
        AND  NVL(OT2.RG_NM,NVL(OT.RG_NM,NVL(OB.RG_NM,OB2.RG_NM))) = UPPER(trim(p_value))
        );
        RETURN l_count;
   EXCEPTION WHEN OTHERS THEN
    RETURN 0;
   END;

END CANON_E307_SERVMSG_ADMIN_PKG;
/
SHOW ERRORS