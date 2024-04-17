CREATE OR REPLACE PACKAGE CANON_E001_CODETABLE_UI_PKG
AS
   /****************************************************************************
      *                                                                        *
      * File NAME       : CANON_E001_CODETABLE_UI_PKG.sql                      *
      * Package Name    : CANON_E001_CODETABLE_UI_PKG                          *
      * DESCRIPTION     :                                                      *
      *   This common package creates data for code table setup                *
      *                                                                        *
      * REVISION HISTORY:                                                      *
      *                                                                        *
      * DEVELOPER         DATE           DESCRIPTION                           *
      * -------------     -----------    ---------------------------           *
      * Lakshmi Gopalsami 6/29/2016      Initial Creation                      *
	  *                                                                        *
	  *  PROCEDURE INFORMATION
	  *     profile_search  			  - For Profile Search UI
	  *     create_update_profile	      - Create or update profile
	  *     create_update_profile_values  - Insert or Update profile values
	  *     get_profile_details           - get profile and profile value details
	  *     delete_profile_values         - Delete Profile Value
	  *     get_user_list                 - List of users (equivalent to fnd_user in oracle)
	  *     get_role_list                 - List of custom Apps roles
	  *     retrieve_profile_values       - Derive the profile value
	  *		code_name_search	          - Code table name search
	  *		get_code_tab_and_cols	      - Retrieve code table and column list
	  *		create_update_code_table      - Create/Update code table
	  *		create_update_code_tab_col    - Create/Update code table cols
	  *     delete_code_tab_col           - delete code table column
	  *     get_code_tab_vals             - Retrieve code table values
	  *     create_code_table_view        - Creates view for the code table
	  --------------------------------------------------------------------------
	  *  UPDATE HISTORY
	  *  DATE        DEVELOPER            Description   
	  *  ----------  -----------------    --------------------------------------
	  *  08/12/2016  Lakshmi Gopalsami    Added UPPER for user search.
	  *                                   Added Role Description for role search
      **************************************************************************/


   TYPE result_cursor IS REF CURSOR;
   g_package_name VARCHAR2(30) := 'CANON_E001_CODETABLE_UI_PKG';
   
   FUNCTION get_user_id(
      p_user_name    IN     VARCHAR2
   ) RETURN NUMBER;
   
   PROCEDURE profile_search(
      p_user_name             IN        VARCHAR2,
	  p_profile_name          IN        VARCHAR2,
	  p_order_by              IN        VARCHAR2,
	  p_sort_order            IN        VARCHAR2,
	  p_page_no               IN        NUMBER,
 	  p_records_per_page      IN        NUMBER,
	  p_result_cursor              OUT  result_cursor,
	  p_result_count               OUT  NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE create_update_profile(
      p_user_name             IN        VARCHAR2,
	  p_profile_name          IN        VARCHAR2,
	  p_profile_description   IN        VARCHAR2,
	  p_profile_id            IN   OUT NUMBER,        
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
      
   PROCEDURE create_update_profile_values(
      p_user_name             IN        VARCHAR2,
	  p_profile_id            IN        NUMBER,
	  p_prf_value_rec_tbl     IN        CANON_E001_PRF_VALUE_TYPE,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE get_profile_details(
      p_user_name             IN        VARCHAR2,
	  p_profile_id            IN        NUMBER,
	  p_profile_header_cur         OUT  result_cursor,
	  p_profile_values_cur         OUT  result_cursor,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE delete_profile_values(
      p_user_name             IN        VARCHAR2,
	  p_profile_id            IN        NUMBER,
	  p_profile_value_id            IN        NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE get_user_list(
      p_user_name             IN        VARCHAR2,
	  p_first_name            IN        VARCHAR2,
	  p_last_name             IN        VARCHAR2,
	  p_order_by              IN        VARCHAR2,
	  p_sort_order            IN        VARCHAR2,
	  p_page_no               IN        NUMBER,
	  p_records_per_page      IN        NUMBER,
      p_user_list_cursor           OUT  result_cursor,
	  p_user_count                 OUT  NUMBER,
      p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE get_role_list(
      p_role_name             IN       VARCHAR2,
	  p_order_by              IN        VARCHAR2,
	  p_sort_order            IN        VARCHAR2,
	  p_page_no               IN        NUMBER,
	  p_records_per_page      IN        NUMBER,
      p_role_list_cursor           OUT  result_cursor,
	  p_role_count                 OUT  NUMBER,
      p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   
   PROCEDURE retrieve_profile_values(
      p_user_name             IN        VARCHAR2,
	  p_role_name             IN        VARCHAR2,
	  p_profile_name          IN        VARCHAR2,
	  p_profile_value              OUT  VARCHAR2,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
    PROCEDURE code_name_search(
	 p_user_name             IN        VARCHAR2,
	 p_code_table_name       IN        VARCHAR2,
	 p_order_by              IN        VARCHAR2,
	 p_sort_order            IN        VARCHAR2,
	 p_page_no               IN        NUMBER,
	 p_records_per_page      IN        NUMBER,
	 p_result_cursor              OUT  result_cursor,
	 p_result_count               OUT  NUMBER,
	 p_validation_status          OUT  VARCHAR2,
	 p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE get_code_tab_and_cols(
      p_user_name             IN        VARCHAR2,
	  p_cd_id                 IN        NUMBER,
	  p_code_tab_cur               OUT  result_cursor,
	  p_code_col_cur               OUT  result_cursor,
	  p_code_col_cnt               OUT  NUMBER,
	  p_code_val_cnt               OUT  NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE create_update_code_table(
      p_user_name              IN        VARCHAR2,
	  p_code_table_name        IN        VARCHAR2,
	  p_code_table_description IN        VARCHAR2,
	  p_cd_id                  IN   OUT NUMBER,        
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE create_update_code_tab_col(
      p_user_name              IN        VARCHAR2,
	  p_cd_id                  IN        NUMBER,
	  p_code_tab_cols_tbl      IN        CANON_E001_CODE_TAB_COL_TYPE,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE delete_code_tab_col(
      p_user_name              IN        VARCHAR2,
	  p_cd_id                  IN        NUMBER,
	  p_cd_col_id              IN        NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
    PROCEDURE get_code_tab_vals(
      p_user_name             IN        VARCHAR2,
	  p_cd_id                 IN        NUMBER,
	  p_order_by              IN        VARCHAR2,
	  p_sort_order            IN        VARCHAR2,
	  p_page_no               IN        NUMBER,
	  p_records_per_page      IN        NUMBER,
	  p_code_val_cur               OUT  result_cursor,
	  p_code_val_cnt               OUT  NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE create_update_code_tab_val(
      p_user_name              IN        VARCHAR2,
	  p_cd_id                  IN        NUMBER,
	  p_code_tab_cols_tbl      IN        CANON_E001_CODE_TAB_COL_TYPE,
	  p_code_tab_vals_tbl      IN        CANON_E001_CODE_TAB_VAL_TYPE,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE delete_code_tab_val(
      p_user_name              IN        VARCHAR2,
	  p_cd_id                  IN        NUMBER,
	  p_cd_val_id              IN        NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE create_code_table_view(
      p_user_name              IN        VARCHAR2,
      p_cd_id                  IN       NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   PROCEDURE log_error(
      p_package_name      IN        VARCHAR2,
	  p_procedure_name    IN        VARCHAR2,
	  p_key1              IN        VARCHAR2, 
	  p_key2              IN        VARCHAR2,
	  p_key3              IN        VARCHAR2,
	  p_key4              IN        VARCHAR2, 
	  p_key5              IN        VARCHAR2,
	  p_sqlerrm            IN        VARCHAR2 
   );
   
   PROCEDURE record_duplicate_check(
      p_user_name              IN        VARCHAR2,
      p_cd_id                  IN        NUMBER,
	  p_code_tab_cols_tbl      IN        CANON_E001_CODE_TAB_COL_TYPE,
	  p_code_tab_vals_tbl      IN        CANON_E001_CODE_TAB_VAL_TYPE,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   );
   
   
   TYPE prf_col_list is RECORD(
	COL_FORMAT             VARCHAR2 (100 CHAR),
	COL_PROMPT             VARCHAR2 (100 CHAR),
	COL_SEQ			  	   NUMBER,
	DEFAULT_VALUE		   VARCHAR2(100 CHAR),
	IS_ACTIVE			   CHAR(1)     DEFAULT 'Y'  , 
	IS_MANDATORY			 CHAR(1)	 DEFAULT 'N',
	IS_UNIQUE				 CHAR(1)     DEFAULT 'N',
	RESULT_COL			 VARCHAR2(100 CHAR)
   );

	TYPE prf_col_tab IS TABLE OF prf_col_list INDEX BY BINARY_INTEGER; 
	g_prf_col_tab prf_col_tab;

   TYPE user_list is RECORD(
      user_id  number
   );
   TYPE user_list_tab IS TABLE OF user_list INDEX BY varchar2(30); 
   g_user_list user_list_tab;
   
   
   TYPE cd_col_list_tbl is RECORD(
	RESULT_COL		VARCHAR2(100 CHAR),
	col_prompt		VARCHAR2(100 CHAR),
	column_num      NUMBER
   );

	TYPE cd_col_list_tab IS TABLE OF cd_col_list_tbl INDEX BY BINARY_INTEGER; 
	g_cd_col_list_tbl cd_col_list_tab;
   
   --TYPE cd_col_list_tbl IS TABLE OF VARCHAR2(100);
   
   --g_cd_col_list_tbl  cd_col_list_tbl :=cd_col_list_tbl();
   
END CANON_E001_CODETABLE_UI_PKG;
/   


CREATE OR REPLACE PACKAGE BODY CANON_E001_CODETABLE_UI_PKG
AS
   /****************************************************************************
      *                                                                        *
      * File NAME       : CANON_E001_CODETABLE_UI_PKG.sql                      *
      * Package Name    : CANON_E001_CODETABLE_UI_PKG                          *
      * DESCRIPTION     :                                                      *
      *   This common package creates data for code table setup                *
      *                                                                        *
      * REVISION HISTORY:                                                      *
      *                                                                        *
      * DEVELOPER         DATE           DESCRIPTION                           *
      * -------------     -----------    ---------------------------           *
      * Lakshmi Gopalsami 6/29/2016      Initial Creation                      *
	  *                                                                        *
	  *  PROCEDURE INFORMATION
	  *     profile_search  			  - For Profile Search UI
	  *     create_update_profile	      - Create or update profile
	  *     create_update_profile_values  - Insert or Update profile values
	  *     get_profile_details           - get profile and profile value details
	  *     delete_profile_values         - Delete Profile Value
	  *     get_user_list                 - List of users (equivalent to fnd_user in oracle)
	  *     get_role_list                 - List of custom Apps roles
	  *     retrieve_profile_values       - Derive the profile value
	  *		code_name_search	          - Code table name search
	  *		get_code_tab_and_cols	      - Retrieve code table and column list
	  *		create_update_code_table      - Create/Update code table
	  *		create_update_code_tab_col    - Create/Update code table cols
	  *     delete_code_tab_col           - delete code table column
	  *     get_code_tab_vals             - Retrieve code table values
	  *     create_code_table_view        - Creates view for the code table
	  --------------------------------------------------------------------------
	  *  UPDATE HISTORY
	  *  DATE        DEVELOPER            Description   
	  *  ----------  -----------------    --------------------------------------
	  *  08/12/2016  Lakshmi Gopalsami    Added UPPER for user search.
	  *                                   Added Role Description for role search
      **************************************************************************/

   FUNCTION get_user_id(
      p_user_name    IN     VARCHAR2
   ) RETURN NUMBER
   IS
     ln_user_id  NUMBER;
   BEGIN 
   
    IF p_user_name IS NOT NULL THEN 
	  
	  IF g_user_list.EXISTS(p_user_name) THEN 
	     ln_user_id := g_user_list(p_user_name).user_id;
	  ELSE 
	     
		 BEGIN 
		    SELECT auth_psn_id
			  INTO ln_user_id
			  FROM AUTH_PSN
			 WHERE usr_nm = p_user_name;
		 EXCEPTION 
		   WHEN OTHERS THEN 
		     ln_user_id := NULL;
		 END;
	  
	  END IF;
	  IF ln_user_id IS NOT NULL THEN 
	    RETURN ln_user_id;
	  ELSE 
	    RETURN -1;
	  END IF;
    ELSE 
	  RETURN -1;
    END IF;	
   EXCEPTION 
     WHEN OTHERS THEN 
	    RETURN -1;
   END ;
	  
   PROCEDURE profile_search(
      p_user_name             IN        VARCHAR2,
	  p_profile_name          IN        VARCHAR2,
	  p_order_by              IN        VARCHAR2,
	  p_sort_order            IN        VARCHAR2,
	  p_page_no               IN        NUMBER,
 	  p_records_per_page      IN        NUMBER,
	  p_result_cursor              OUT  result_cursor,
	  p_result_count               OUT  NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
		ln_cd_id NUMBER;
		lv_main_query            VARCHAR2 (3999) := '';
		lv_sub_query             VARCHAR2 (3999) := '';
		lv_from_query            VARCHAR2 (3999) := '';
		lv_count_query           VARCHAR2 (3999) := '';
		lv_select_query          VARCHAR2 (3999) := '';
		ln_end_count             NUMBER;
		ln_start_count           NUMBER;
		ln_tot_records           NUMBER;
		lv_prf_name_srch              VARCHAR2 (3999) := '';
		lv_procedure_name        VARCHAR2(30) := 'profile_search';
	BEGIN 
   
      IF p_profile_name IS NOT NULL THEN 
	     lv_prf_name_srch := ' AND PROFILE_NAME LIKE '||'''%'||p_profile_name||'%''';
	  END IF;
	  
	   lv_select_query :=
			   'SELECT  '||
			   'PROFILE_ID '||
			   ',PROFILE_NAME '||
			   ',PROFILE_DESCRIPTION ';
       lv_from_query :=
		     'FROM CANON_E001_PROFILES_TBL  WHERE 1 = 1';
	   
	   lv_count_query := ' SELECT count(*) ';		 
			 
       lv_sub_query :=
			   lv_select_query
			|| lv_from_query
			|| lv_prf_name_srch;


		 lv_count_query :=
			   lv_count_query
			|| lv_from_query
			|| lv_prf_name_srch;
			
	   dbms_output.put_line(' lv_count_query: '||lv_count_query);
	   dbms_output.put_line(' lv_sub_query: '||lv_sub_query);
	   EXECUTE IMMEDIATE lv_count_query INTO ln_tot_records;		
    	   

	   IF ln_tot_records > 0
		 THEN
			ln_end_count := p_records_per_page * p_page_no;

			IF ln_end_count >= ln_tot_records
			THEN
			   ln_end_count := ln_tot_records;
			END IF;

			IF p_page_no = 1
			THEN
			   ln_start_count := 1;
			ELSE
			   ln_start_count := p_records_per_page * (p_page_no - 1) + 1;
			END IF;

			lv_main_query :=
				  'SELECT  '
			   ||	  'PROFILE_ID PROFILE_ID '
			   || ',PROFILE_NAME PROFILE_NAME '
			   || ',PROFILE_DESCRIPTION PROFILE_DESCRIPTION '
			   || '  FROM (SELECT prf.* '
			   || '               ,ROWNUM r1 '
			   || '          FROM ('
			   || lv_sub_query;
			
				/* Set the order by */			
				IF p_order_by IS NOT NULL
				THEN
				   lv_main_query := lv_main_query || '  ORDER BY ' || p_order_by;
				ELSE                                             -- Default Search
				   lv_main_query := lv_main_query || '  ORDER BY profile_name asc';
				END IF;
			
				IF p_sort_order IS NOT NULL
				THEN
				  lv_main_query := lv_main_query || ' ' || p_sort_order;
				END IF;
			
			
			lv_main_query := lv_main_query 
			   || '               ) prf '
			   || '       ) '
			   || ' WHERE r1 BETWEEN '
			   || ln_start_count
			   || ' AND '
			   || ln_end_count;
			   
			dbms_output.put_line(' lv_main_query: '||lv_main_query);

			OPEN p_result_cursor FOR lv_main_query;

			p_result_count := ln_tot_records;

			p_validation_status := 'S';
			p_error_msg := NULL;
		 ELSE
			OPEN p_result_cursor FOR
			   SELECT 1,1,1
				 FROM DUAL
				WHERE 1 = 2;

			p_result_count := 0;

			p_validation_status := 'S';
			p_error_msg := 'No records retrieved. ';
		 END IF;

		p_validation_status := 'S';
		p_error_msg         := NULL;
		
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
		p_result_count      := 0;
		 OPEN p_result_cursor FOR 
		  SELECT   
			  1,1,1      
			FROM DUAL WHERE 1=2;
	    canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_profile_name,
												p_order_by||'-'||p_sort_order,
												p_page_no||'-'||p_records_per_page,
												lv_main_query,
												SQLERRM
                                             );
	 
   END profile_search;
   
  PROCEDURE create_update_profile(
      p_user_name             IN        VARCHAR2,
	  p_profile_name          IN        VARCHAR2,
	  p_profile_description   IN        VARCHAR2,
	  p_profile_id            IN   OUT NUMBER,        
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
     ln_profile_id NUMBER;
	 lv_dup_cnt_check  NUMBER;
   BEGIN 
   
     IF p_profile_id IS NOT NULL THEN 
	 
	    UPDATE CANON_E001_PROFILES_TBL
		   SET PROFILE_DESCRIPTION = p_profile_description
		 WHERE profile_id = p_profile_id;
	 
	 ELSE 
       BEGIN 
	    SELECT  COUNT(*)
		  INTO lv_dup_cnt_check
		  FROM CANON_E001_PROFILES_TBL
		 WHERE profile_name =  p_profile_name;
	   EXCEPTION
	     WHEN OTHERS THEN 
		   lv_dup_cnt_check := 0;
	   END;
	 
       
	  IF ( lv_dup_cnt_check =0 ) THEN
         
        INSERT INTO CANON_E001_PROFILES_TBL
		(
		   PROFILE_ID
		   ,PROFILE_NAME
		   ,PROFILE_DESCRIPTION
		   ,CREATION_DATE
		   ,CREATED_BY
		   ,LAST_UPDATE_DATE
		   ,LAST_UPDATED_BY
		)
		values
		(
		CANON_E001_PROFILES_TBL_S.nextval,
		p_profile_name,
		p_profile_description,
		sysdate,
		-1,
		sysdate,
		-1
		) RETURNING PROFILE_ID INTO ln_profile_id;
	
		p_profile_id    := ln_profile_id;
		p_validation_status := 'S';
		p_error_msg         := NULL;
      ELSE 
	     p_validation_status := 'E';
		 p_error_msg         := 'Profile Name already exists. Please create with new name';
		 p_profile_id             := -1;
	  END IF;		
    END IF;		
		
		
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
	    p_profile_id := -1;
	 
   END create_update_profile;
   
   PROCEDURE create_update_profile_values(
      p_user_name             IN        VARCHAR2,
	  p_profile_id            IN        NUMBER,
	  p_prf_value_rec_tbl     IN        CANON_E001_PRF_VALUE_TYPE,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
     ln_profile_value_id NUMBER;
	 lt_prf_value_rec_tbl        CANON_E001_PRF_VALUE_TYPE;
   BEGIN 
   
      --p_prf_value_id_rec_tbl  := CANON_E001_PRF_VALUE_TYPE();
	  lt_prf_value_rec_tbl := p_prf_value_rec_tbl;
	  
	  FOR val in 1..lt_prf_value_rec_tbl.COUNT
	  LOOP
		  /* Profile Value doesn't exist. Create one */
		  IF lt_prf_value_rec_tbl(val).profile_value_id IS NULL THEN 
			 
				INSERT INTO CANON_E001_PROFILE_VALUES_TBL(
					PROFILE_ID
					,PROFILE_VALUE_ID
					,PROFILE_LEVEL_NUMBER
					,PROFILE_LEVEL_NAME
					,PROFILE_LEVEL_VALUE
					,START_DATE
					,END_DATE
					,IS_ACTIVE
					,PROFILE_VALUE
					,CREATION_DATE
					,CREATED_BY
					,LAST_UPDATE_DATE
					,LAST_UPDATED_BY
				)
				values
				(
				p_profile_id,
				CANON_E001_PROFILE_VALUES_S.NEXTVAL,
				lt_prf_value_rec_tbl(val).profile_level_number,
				lt_prf_value_rec_tbl(val).profile_level_name,
				lt_prf_value_rec_tbl(val).profile_level_value,
				lt_prf_value_rec_tbl(val).start_date,
				lt_prf_value_rec_tbl(val).end_date,
				lt_prf_value_rec_tbl(val).is_active,
				lt_prf_value_rec_tbl(val).profile_value,
				sysdate,
				-1,
				sysdate,
				-1
				);
		   ELSIF lt_prf_value_rec_tbl(val).profile_value_id IS NOT NULL THEN /* Profile Value exists. Update the information */
			  UPDATE CANON_E001_PROFILE_VALUES_TBL
				 SET PROFILE_LEVEL_NAME   = lt_prf_value_rec_tbl(val).profile_level_name
					,PROFILE_LEVEL_VALUE  = lt_prf_value_rec_tbl(val).profile_level_value
					,PROFILE_LEVEL_NUMBER = lt_prf_value_rec_tbl(val).profile_level_number
					,START_DATE           = lt_prf_value_rec_tbl(val).start_date
					,END_DATE             = lt_prf_value_rec_tbl(val).end_date
					,IS_ACTIVE            = lt_prf_value_rec_tbl(val).is_active
					,PROFILE_VALUE        = lt_prf_value_rec_tbl(val).profile_value
					,LAST_UPDATE_DATE = SYSDATE
					,LAST_UPDATED_BY  = -1
			   WHERE profile_id = p_profile_id
				  AND profile_value_id =lt_prf_value_rec_tbl(val).profile_value_id;
			  
		   END IF;
	  END LOOP;
     
	  p_validation_status := 'S';
	  p_error_msg         := NULL;
		
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
	 
   END create_update_profile_values;
   
   PROCEDURE get_profile_details(
      p_user_name             IN        VARCHAR2,
	  p_profile_id            IN        NUMBER,
	  p_profile_header_cur         OUT  result_cursor,
	  p_profile_values_cur         OUT  result_cursor,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
      lv_val_indx NUMBER := 1;
   BEGIN 
     --p_prf_value_rec_tbl := CANON_E001_PRF_VALUE_TYPE();
   
     IF p_profile_id IS NOT NULL THEN 
	    OPEN p_profile_header_cur FOR 
		SELECT PROFILE_ID
			    ,PROFILE_NAME 
				,PROFILE_DESCRIPTION 
          FROM CANON_E001_PROFILES_TBL 
		 WHERE profile_id = p_profile_id;
		
		OPEN p_profile_values_cur FOR
		SELECT 
			PROFILE_ID
			,PROFILE_VALUE_ID
			,PROFILE_LEVEL_NUMBER
			,PROFILE_LEVEL_NAME
			,PROFILE_LEVEL_VALUE
			,START_DATE
			,END_DATE
			,IS_ACTIVE
			,PROFILE_VALUE
		  FROM CANON_E001_PROFILE_VALUES_TBL
		 WHERE profile_id = p_profile_id
		   AND NVL(IS_ACTIVE,'Y') ='Y'
		 ORDER BY PROFILE_LEVEL_NUMBER, PROFILE_VALUE_ID;
        
		/* 
		FOR prf_value in (
		   SELECT PROFILE_VALUE_ID
					,PROFILE_LEVEL
					,PROFILE_LEVEL_VALUE
					,START_DATE
					,END_DATE
					,IS_ACTIVE
					,PROFILE_VALUE
			FROM CANON_E001_PROFILE_VALUES_TBL
		   WHERE PROFILE_ID = p_profile_id
		)		
		LOOP 
		   	 p_prf_value_rec_tbl.EXTEND;
		     p_prf_value_rec_tbl (lv_val_indx) :=
		     CANON_E001_PRF_VALUE_REC (
				p_profile_id,
				prf_value.PROFILE_VALUE_ID,
				prf_value.PROFILE_LEVEL,
				prf_value.PROFILE_LEVEL_VALUE,
				prf_value.START_DATE,
				prf_value.END_DATE,
				prf_value.IS_ACTIVE,
				prf_value.PROFILE_VALUE);
	
		END LOOP;
		*/
		 
	 ELSE 
	   OPEN p_profile_header_cur FOR 
		SELECT 1,1,1
          FROM DUAL WHERE 1=2;
	  OPEN p_profile_values_cur FOR  
      SELECT 
			1
			,1
			,1
			,1
			,1
			,1
			,1
			,1
			,1
		  FROM DUAL
		 WHERE 1=2;
		 
		 
		 /*p_prf_value_rec_tbl.EXTEND;
		 p_prf_value_rec_tbl (1) :=
			CANON_E001_PRF_VALUE_REC (
			   NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
		 */ 
	 
	 END IF;
	 p_validation_status := 'S';
	 p_error_msg         := NULL;
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
   	    OPEN p_profile_header_cur FOR 
		SELECT 1,1,1
          FROM DUAL WHERE 1=2;
		  
		OPEN p_profile_values_cur FOR   
		SELECT 
			1
			,1
			,1
			,1
			,1
			,1
			,1
			,1
			,1
		  FROM DUAL
		 WHERE 1=2;  
		 
	 
   END get_profile_details;	 
   
    PROCEDURE delete_profile_values(
      p_user_name             IN        VARCHAR2,
	  p_profile_id            IN        NUMBER,
	  p_profile_value_id            IN        NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
      lv_val_indx NUMBER := 1;
   BEGIN 
   
      UPDATE CANON_E001_PROFILE_VALUES_TBL
		 SET IS_ACTIVE           = 'N'
			,LAST_UPDATE_DATE = SYSDATE
			,LAST_UPDATED_BY  = -1
	   WHERE profile_id = p_profile_id
		  AND profile_value_id =p_profile_value_id;
      p_validation_status := 'S';
	  p_error_msg         := NULL;
	 
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
	 
   END delete_profile_values;

   PROCEDURE get_user_list(
      p_user_name             IN        VARCHAR2,
	  p_first_name            IN        VARCHAR2,
	  p_last_name             IN        VARCHAR2,
	  p_order_by              IN        VARCHAR2,
	  p_sort_order            IN        VARCHAR2,
	  p_page_no               IN        NUMBER,
	  p_records_per_page      IN        NUMBER,
      p_user_list_cursor           OUT  result_cursor,
	  p_user_count                 OUT  NUMBER,
      p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS 
      	lv_main_query            VARCHAR2 (3999) := '';
		lv_sub_query             VARCHAR2 (3999) := '';
		lv_where_query           VARCHAR2 (3999) := '';
		lv_from_query            VARCHAR2 (3999) := '';
		lv_count_query           VARCHAR2 (3999) := '';
		lv_select_query          VARCHAR2 (3999) := '';
		ln_end_count             NUMBER;
		ln_start_count           NUMBER;
		ln_tot_records           NUMBER;
		lv_procedure_name        VARCHAR2(30) := 'get_user_list';
		lv_user_name             AUTH_PSN.usr_nm%TYPE;
		lv_first_name            AUTH_PSN.first_nm%TYPE;
		lv_last_name             AUTH_PSN.last_nm%TYPE;

   BEGIN 
   
      lv_from_query   := ' FROM AUTH_PSN WHERE EZCANCELFLAG = 0 AND actv_flg = ''Y'''  ;
	  lv_select_query := ' SELECT usr_nm, first_nm, last_nm ' ;
	  lv_count_query  := 'select count(*) ';
	  lv_user_name    := UPPER(p_user_name);
	  lv_first_name   := UPPER(p_first_name);
	  lv_last_name    := UPPER(p_last_name);
	  
	  IF lv_user_name IS NOT NULL THEN 
	     lv_where_query := ' AND UPPER(usr_nm) like '||'''%'||lv_user_name||'%''';
	  END IF;
	  
	  IF lv_first_name IS NOT NULL THEN 
	      lv_where_query := lv_where_query||' AND UPPER(first_nm) like '||'''%'||lv_first_name||'%''';
	  END IF;
	  
	  IF lv_last_name IS NOT NULL THEN 
	     lv_where_query := lv_where_query || ' AND UPPER(last_nm) like '||'''%'||lv_last_name||'%''';
	  END IF;
	  
	  lv_count_query  := lv_count_query || lv_from_query || lv_where_query;
	  lv_sub_query    := lv_select_query || lv_from_query || lv_where_query;
	  
	  
	  EXECUTE IMMEDIATE lv_count_query INTO ln_tot_records;		
    	   

	   IF ln_tot_records > 0
		 THEN
			ln_end_count := p_records_per_page * p_page_no;

			IF ln_end_count >= ln_tot_records
			THEN
			   ln_end_count := ln_tot_records;
			END IF;

			IF p_page_no = 1
			THEN
			   ln_start_count := 1;
			ELSE
			   ln_start_count := p_records_per_page * (p_page_no - 1) + 1;
			END IF;

			lv_main_query :=
				  'SELECT  '
			   ||	  'usr_nm usr_nm '
			   || ',first_nm first_nm '
			   || ',last_nm last_nm '
			   || '  FROM (SELECT usr.* '
			   || '               ,ROWNUM r1 '
			   || '          FROM ('
			   || lv_sub_query;
			
				/* Set the order by */			
				IF p_order_by IS NOT NULL
				THEN
				   lv_main_query := lv_main_query || '  ORDER BY ' || p_order_by;
				ELSE                                             -- Default Search
				   lv_main_query := lv_main_query || '  ORDER BY last_nm asc';
				END IF;
			
				IF p_sort_order IS NOT NULL
				THEN
				  lv_main_query := lv_main_query || ' ' || p_sort_order;
				END IF;
			
			
			lv_main_query := lv_main_query 
			   || '               ) usr '
			   || '       ) '
			   || ' WHERE r1 BETWEEN '
			   || ln_start_count
			   || ' AND '
			   || ln_end_count;
			   
			dbms_output.put_line(' lv_main_query: '||lv_main_query);

			OPEN p_user_list_cursor FOR lv_main_query;

			p_user_count := ln_tot_records;

			p_validation_status := 'S';
			p_error_msg := NULL;
		 ELSE
			OPEN p_user_list_cursor FOR
			   SELECT 1,1,1
				 FROM DUAL
				WHERE 1 = 2;

			p_user_count := 0;

			p_validation_status := 'S';
			p_error_msg := 'No records retrieved. ';
		 END IF;
		 
	  canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_first_name||'-'||p_last_name,
												p_order_by||'-'||p_sort_order,
												p_page_no||'-'||p_records_per_page,
												lv_main_query,
												SQLERRM
                                             );		 
	   
      p_validation_status := 'S';
	  p_error_msg         := NULL;
	 
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
		p_user_count := 0;
		 OPEN p_user_list_cursor FOR 
		  SELECT   
			  1  
			FROM DUAL WHERE 1=2;
		canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_first_name||'-'||p_last_name,
												p_order_by||'-'||p_sort_order,
												p_page_no||'-'||p_records_per_page,
												lv_main_query,
												SQLERRM
                                             );	
	 
   END get_user_list;
   
   PROCEDURE get_role_list(
      p_role_name             IN       VARCHAR2,
	  p_order_by              IN        VARCHAR2,
	  p_sort_order            IN        VARCHAR2,
	  p_page_no               IN        NUMBER,
	  p_records_per_page      IN        NUMBER,
      p_role_list_cursor           OUT  result_cursor,
	  p_role_count                 OUT  NUMBER,
      p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
    IS 
	lv_main_query            VARCHAR2 (3999) := '';
		lv_sub_query             VARCHAR2 (3999) := '';
		lv_where_query           VARCHAR2 (3999) := '';
		lv_from_query            VARCHAR2 (3999) := '';
		lv_count_query           VARCHAR2 (3999) := '';
		lv_select_query          VARCHAR2 (3999) := '';
		ln_end_count             NUMBER;
		ln_start_count           NUMBER;
		ln_tot_records           NUMBER;
   BEGIN  
   
      
	  
      lv_from_query   := 'FROM '||
			' ROLE R, '||
			--' S21_CSA_APPS.APP APP, '||
			' RESRC_ROLE RR, '||
			' PROT_RESRC PR, '||
			' PROT_RESRC_OBJ PRO, '||
			' RESRC_OBJ RO, '||
			' RESRC_CLS RCLS '||
		' WHERE '||
			' 1 =1  '||
			' AND R.ROLE_ID               = RR.ROLE_ID  '||
			--' AND R.APP_ID                = APP.APP_ID  '||
			' AND RR.PROT_RESRC_ID        = PR.PROT_RESRC_ID  '||
			' AND PR.PROT_RESRC_ID        = PRO.PROT_RESRC_ID  '||
			--' AND PR.APP_ID               = APP.APP_ID  '||
			' AND PRO.RESRC_OBJ_ID        = RO.RESRC_OBJ_ID  '||
			--' AND RO.APP_ID               = APP.APP_ID  '||
			--' AND PMSN.PMSN_ID            = PRO.PMSN_ID  '||
			--' AND PMSN.PMSN_TP_ID         = PMSN_TP.PMSN_TP_ID  '||
			' AND RCLS.RESRC_CLS_ID       = RO.RESRC_CLS_ID '||
			' AND R.EZCANCELFLAG          = 0 '||
			--' AND APP.EZCANCELFLAG        = 0 '||
			' AND RR.EZCANCELFLAG         = 0 '||
			' AND PR.EZCANCELFLAG         = 0 '||
			' AND PRO.EZCANCELFLAG        = 0 '||
			' AND RO.EZCANCELFLAG         = 0 '||
			--' AND PMSN.EZCANCELFLAG       = 0 '||
			--' AND PMSN_TP.EZCANCELFLAG    = 0 '||
			' AND RCLS.EZCANCELFLAG       = 0 '||
			' and RO.RESRC_OBJ_NM like ''EXTN%'' '||
			--and r.ROLE_NM like '%479%' '||
			' and         RCLS.RESRC_CLS_NM = ''BIZ_APP'''||
			' and R.ACTV_FLG =''Y'' ';
			
	  lv_select_query := ' SELECT R.ROLE_NM, R.ROLE_DESC_TXT ' ;
	  lv_count_query  := 'select count(*) ';
	  
	  IF p_role_name IS NOT NULL THEN 
	     lv_where_query := ' AND R.ROLE_NM like '||'''%'||p_role_name||'%''';
	  END IF;
	  
	  
	  lv_count_query  := lv_count_query || lv_from_query || lv_where_query;
	  lv_sub_query    := lv_select_query || lv_from_query || lv_where_query;
	  
	  dbms_output.put_line(' lv_count_query: '||lv_count_query);
	  
	  EXECUTE IMMEDIATE lv_count_query INTO ln_tot_records;		
    	   

	   IF ln_tot_records > 0
		 THEN
			ln_end_count := p_records_per_page * p_page_no;

			IF ln_end_count >= ln_tot_records
			THEN
			   ln_end_count := ln_tot_records;
			END IF;

			IF p_page_no = 1
			THEN
			   ln_start_count := 1;
			ELSE
			   ln_start_count := p_records_per_page * (p_page_no - 1) + 1;
			END IF;

			lv_main_query :=
				  'SELECT  '
			   ||	  'ROLE_NM ROLE_NM, ROLE_DESC_TXT ROLE_DESC_TXT'
			   || '  FROM (SELECT usr.* '
			   || '               ,ROWNUM r1 '
			   || '          FROM ('
			   || lv_sub_query;
			
				-- Set the order by 
				IF p_order_by IS NOT NULL
				THEN
				   lv_main_query := lv_main_query || '  ORDER BY ' || p_order_by;
				ELSE                                             -- Default Search
				   lv_main_query := lv_main_query || '  ORDER BY ROLE_NM asc';
				END IF;
			
				IF p_sort_order IS NOT NULL
				THEN
				  lv_main_query := lv_main_query || ' ' || p_sort_order;
				END IF;
			
			
			lv_main_query := lv_main_query 
			   || '               ) usr '
			   || '       ) '
			   || ' WHERE r1 BETWEEN '
			   || ln_start_count
			   || ' AND '
			   || ln_end_count;
			   
			dbms_output.put_line(' lv_main_query: '||lv_main_query);

			OPEN p_role_list_cursor FOR lv_main_query;

			p_role_count := ln_tot_records;

			p_validation_status := 'S';
			p_error_msg := NULL;
		 ELSE
			OPEN p_role_list_cursor FOR
			   SELECT 1,1,1
				 FROM DUAL
				WHERE 1 = 2;

			p_role_count := 0;

			p_validation_status := 'S';
			p_error_msg := 'No records retrieved. ';
		 END IF;
	
	 
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
		p_role_count := 0;
		 OPEN p_role_list_cursor FOR 
		  SELECT   
			  1  
			FROM DUAL WHERE 1=2;
	 
   END get_role_list;
   
   PROCEDURE retrieve_profile_values(
      p_user_name             IN        VARCHAR2,
	  p_role_name             IN        VARCHAR2,
	  p_profile_name          IN        VARCHAR2,
	  p_profile_value              OUT  VARCHAR2,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS 
      lv_profile_level_number NUMBER;
	  lv_profile_level_value VARCHAR2 (100 CHAR);
	  lv_profile_level_name  VARCHAR2 (500 CHAR);
   BEGIN
     /* Hiearchy to derive profile value is 
	  * (1) User
	  * (2) Role
	  * (3) Application
	  */
	  
	  IF p_user_name IS NOT NULL THEN 
	     lv_profile_level_name    :=  'USER';
	     lv_profile_level_value   := p_user_name;
		 lv_profile_level_number  := 3;
	  ELSIF p_role_name IS NOT NULL THEN 
	     lv_profile_level_name    :=  'ROLE';
	     lv_profile_level_value   := p_role_name;
		 lv_profile_level_number  := 2;
	  ELSE 
	     lv_profile_level_name    :=  'APPLICATION';
	     lv_profile_level_value   := NULL;
		 lv_profile_level_number  := 1;
	  END IF;
	  
	  BEGIN
		  SELECT 
				PROFILE_VALUE
			  INTO 	p_profile_value
			  FROM CANON_E001_PROFILE_VALUES_TBL prf_val, 
					CANON_E001_PROFILES_TBL prf 
			 WHERE prf.PROFILE_NAME = p_profile_name
				AND prf.profile_id = prf_val.profile_id
				AND prf_val.profile_level_name = lv_profile_level_name
				AND NVL(prf_val.profile_level_value,'APPLICATION') = NVL(lv_profile_level_value,'APPLICATION')
				and prf_val.profile_level_number = lv_profile_level_number
				AND SYSDATE BETWEEN NVL(START_DATE,SYSDATE-1)  AND NVL(END_DATE,SYSDATE+1)
			   AND NVL(IS_ACTIVE,'Y') ='Y';
       EXCEPTION
	    /* Get from application level */
	    WHEN NO_DATA_FOUND THEN 
		  BEGIN 
		    SELECT 
				PROFILE_VALUE
			  INTO 	p_profile_value
			  FROM CANON_E001_PROFILE_VALUES_TBL prf_val, 
					CANON_E001_PROFILES_TBL prf 
			 WHERE prf.PROFILE_NAME = p_profile_name
				AND prf.profile_id = prf_val.profile_id
				AND prf_val.profile_level_name = 'APPLICATION'
				and prf_val.profile_level_number = 1
				AND SYSDATE BETWEEN NVL(START_DATE,SYSDATE-1)  AND NVL(END_DATE,SYSDATE+1)
			   AND NVL(IS_ACTIVE,'Y') ='Y';
		   EXCEPTION
		     WHEN OTHERS THEN 
	           p_profile_value     := NULL;
		   END;
	    WHEN OTHERS THEN 
	      p_profile_value     := NULL;
       END;	   
	   
	   p_validation_status := 'S';
	   p_error_msg         := NULL;
	  
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_profile_value     := NULL;
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
	 
   END retrieve_profile_values;	
   
    PROCEDURE code_name_search(
	 p_user_name             IN        VARCHAR2,
	 p_code_table_name       IN        VARCHAR2,
	 p_order_by              IN        VARCHAR2,
	 p_sort_order            IN        VARCHAR2,
	 p_page_no               IN        NUMBER,
	 p_records_per_page      IN        NUMBER,
	 p_result_cursor              OUT  result_cursor,
	 p_result_count               OUT  NUMBER,
	 p_validation_status          OUT  VARCHAR2,
	 p_error_msg                  OUT  VARCHAR2
   )
   IS 
   ln_cd_id NUMBER;
		lv_main_query            VARCHAR2 (3999) := '';
		lv_sub_query             VARCHAR2 (3999) := '';
		lv_from_query            VARCHAR2 (3999) := '';
		lv_count_query           VARCHAR2 (3999) := '';
		lv_select_query          VARCHAR2 (3999) := '';
		ln_end_count             NUMBER;
		ln_start_count           NUMBER;
		ln_tot_records           NUMBER;
		lv_prf_name_srch              VARCHAR2 (3999) := '';
		lv_procedure_name        VARCHAR2(30) := 'code_name_search';
	BEGIN 
   
      IF p_code_table_name IS NOT NULL THEN 
	     lv_prf_name_srch := ' AND cd_name LIKE '||'''%'||p_code_table_name||'%''';
	  END IF;
	  
	  
	   lv_select_query :=
			   'SELECT  '||
			   ' cd_id '|| 
			   ' ,cd_name '||
			   ' ,cd_description '||
			   ' ,cd_view_name ';
       lv_from_query :=
		     'FROM CANON_S21_CD_TBL  WHERE 1 = 1';
	   
	   lv_count_query := ' SELECT count(*) ';		 
			 
       lv_sub_query :=
			   lv_select_query
			|| lv_from_query
			|| lv_prf_name_srch;


		 lv_count_query :=
			   lv_count_query
			|| lv_from_query
			|| lv_prf_name_srch;
			
	   dbms_output.put_line(' lv_count_query: '||lv_count_query);
	   dbms_output.put_line(' lv_sub_query: '||lv_sub_query);
	   EXECUTE IMMEDIATE lv_count_query INTO ln_tot_records;		
    	   

	   IF ln_tot_records > 0
		 THEN
			ln_end_count := p_records_per_page * p_page_no;

			IF ln_end_count >= ln_tot_records
			THEN
			   ln_end_count := ln_tot_records;
			END IF;

			IF p_page_no = 1
			THEN
			   ln_start_count := 1;
			ELSE
			   ln_start_count := p_records_per_page * (p_page_no - 1) + 1;
			END IF;

			lv_main_query :=
				  'SELECT  '
			   ||	  'cd_id cd_id '
			   || ',cd_name cd_name '
			   || ',cd_description cd_description '
			   || ',cd_view_name cd_view_name '
			   || '  FROM (SELECT cd_tab.* '
			   || '               ,ROWNUM r1 '
			   || '          FROM ('
			   || lv_sub_query;
			
				/* Set the order by */			
				IF p_order_by IS NOT NULL
				THEN
				   lv_main_query := lv_main_query || '  ORDER BY ' || p_order_by;
				ELSE                                             -- Default Search
				   lv_main_query := lv_main_query || '  ORDER BY cd_name asc';
				END IF;
			
				IF p_sort_order IS NOT NULL
				THEN
				  lv_main_query := lv_main_query || ' ' || p_sort_order;
				END IF;
			
			
			lv_main_query := lv_main_query 
			   || '               ) cd_tab '
			   || '       ) '
			   || ' WHERE r1 BETWEEN '
			   || ln_start_count
			   || ' AND '
			   || ln_end_count;
			   
			dbms_output.put_line(' lv_main_query: '||lv_main_query);

			OPEN p_result_cursor FOR lv_main_query;

			p_result_count := ln_tot_records;

			p_validation_status := 'S';
			p_error_msg := NULL;
		 ELSE
			OPEN p_result_cursor FOR
			   SELECT 1,1,1,1
				 FROM DUAL
				WHERE 1 = 2;

			p_result_count := 0;

			p_validation_status := 'S';
			p_error_msg := 'No records retrieved. ';
		 END IF;

		p_validation_status := 'S';
		p_error_msg         := NULL;
		
		 canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_code_table_name,
												p_order_by||'-'||p_sort_order,
												p_page_no||'-'||p_records_per_page,
												lv_main_query,
												SQLERRM
                                             );
		
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
		p_result_count      := 0;
		 OPEN p_result_cursor FOR 
		  SELECT   
			  1,1,1,1     
			FROM DUAL WHERE 1=2;
	    canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_code_table_name,
												p_order_by||'-'||p_sort_order,
												p_page_no||'-'||p_records_per_page,
												lv_main_query,
												SQLERRM
                                             );		
	     
	 
   END code_name_search;
   
    PROCEDURE get_code_tab_and_cols(
      p_user_name             IN        VARCHAR2,
	  p_cd_id                 IN        NUMBER,
	  p_code_tab_cur               OUT  result_cursor,
	  p_code_col_cur               OUT  result_cursor,
	  p_code_col_cnt               OUT  NUMBER,
	  p_code_val_cnt               OUT  NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS 
   BEGIN
   
   
    IF p_cd_id IS NOT NULL THEN 
	    OPEN p_code_tab_cur FOR 
		SELECT   cd_id 
			    ,cd_name 
			    ,cd_description 
			    ,cd_view_name 
          FROM CANON_S21_CD_TBL 
		 WHERE cd_id = p_cd_id;
		
		OPEN p_code_col_cur FOR
		SELECT   
				col_id
				,col_format
				,db_col_name
				,col_prompt
				,col_seq
				--,default_value
				--,is_active
				,is_mandatory
				--,is_unique
				,result_col
			FROM canon_s21_cd_col_tbl
		   WHERE cd_id =  p_cd_id
		     AND nvl(is_active,'Y') ='Y'
			 ORDER BY col_seq;
		
       BEGIN		
		SELECT   
				count(*)
			INTO p_code_col_cnt
			FROM canon_s21_cd_col_tbl
		   WHERE cd_id =  p_cd_id
		     AND nvl(is_active,'Y') ='Y';	 
	   EXCEPTION
	     WHEN OTHERS THEN p_code_col_cnt := 0;
	   END;
	   
	   BEGIN		
		SELECT   
				count(*)
			INTO p_code_val_cnt
			FROM canon_s21_cd_val_tbl
		   WHERE cd_id =  p_cd_id
		     AND nvl(is_active,'Y') ='Y';	 
	   EXCEPTION
	     WHEN OTHERS THEN p_code_val_cnt := 0;
	   END;
		   
		 
	 ELSE 
	   OPEN p_code_tab_cur FOR 
		SELECT 1,1,1,1
          FROM DUAL WHERE 1=2;
		  
	  OPEN p_code_col_cur FOR  
      SELECT 
			1
			,1
			,1
			,1
			,1
			,1
			,1			
		  FROM DUAL
		 WHERE 1=2;
	  p_code_col_cnt := 0;	 
	  p_code_val_cnt := 0;
	
	 END IF;
	 
	 
	 
	 p_validation_status := 'S';
	 p_error_msg         := NULL;
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
		p_code_col_cnt      := 0;
		p_code_val_cnt      := 0;
   	    OPEN p_code_tab_cur FOR 
		SELECT 1,1,1,1
          FROM DUAL WHERE 1=2;
		  
		OPEN p_code_col_cur FOR   
		SELECT 
			1
			,1
			,1
			,1
			,1
			,1
			,1
		  FROM DUAL
		 WHERE 1=2;  
		 
	 
   END get_code_tab_and_cols;	 
   
   PROCEDURE create_update_code_table(
      p_user_name              IN        VARCHAR2,
	  p_code_table_name        IN        VARCHAR2,
	  p_code_table_description IN        VARCHAR2,
	  p_cd_id                  IN   OUT NUMBER,        
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
     ln_cd_id          NUMBER;
	 lv_dup_cnt_check  NUMBER;
	 lv_dup_view_check NUMBER;
	 lv_view_name      VARCHAR2(30);
   BEGIN 
   
     IF p_cd_id IS NOT NULL THEN 
	 
	    UPDATE CANON_S21_CD_TBL
		   SET cd_description = p_code_table_description,
		   cd_view_name = decode(cd_view_name, null, substr(p_code_table_name,1,28)||'_V',cd_view_name)
		 WHERE cd_id = p_cd_id;
		 
		p_validation_status := 'S';
		p_error_msg         := NULL;	
	 
	 ELSE 
	 
	   lv_view_name := substr(p_code_table_name,1,28)||'_V';
	   
	   BEGIN 
	    SELECT  COUNT(*)
		  INTO lv_dup_cnt_check
		  FROM CANON_S21_CD_TBL
		 WHERE cd_name =  p_code_table_name;
	   EXCEPTION
	     WHEN OTHERS THEN 
		   lv_dup_cnt_check := 0;
	   END;
	   
	   BEGIN 
	    SELECT  COUNT(*)
		  INTO lv_dup_view_check
		  FROM all_objects
		 WHERE object_name =  lv_view_name
		   AND object_type ='VIEW';
	   EXCEPTION
	     WHEN OTHERS THEN 
		   lv_dup_view_check := 0;
	   END;
	 
       
	   IF ( lv_dup_cnt_check =0 AND lv_dup_view_check=0) THEN
			 
			INSERT INTO CANON_S21_CD_TBL
			(
			   cd_id
			   ,cd_name
			   ,cd_description
			   ,cd_view_name
			   ,CREATION_DATE
			   ,CREATED_BY
			   ,LAST_UPDATE_DATE
			   ,LAST_UPDATED_BY
			)
			values
			(
			CANON_S21_CD_ID_SEQ.nextval,
			p_code_table_name,
			p_code_table_description,
			lv_view_name,
			sysdate,
			-1,
			sysdate,
			-1
			) RETURNING cd_id INTO ln_cd_id;
		
			p_cd_id    := ln_cd_id;
			p_validation_status := 'S';
		    p_error_msg         := NULL;
	  ELSE 
	     p_validation_status := 'E';
		 IF lv_dup_cnt_check > 0 THEN 
		  p_error_msg         := 'Code Table already exists. Please create with new name';
		 END IF;
		 IF lv_dup_view_check > 0 THEN 
		  p_error_msg         := p_error_msg||' Code Table view '|| lv_view_name ||' already exists. Please create code table with different name';
		 END IF;
		 p_cd_id             := -1;
	  END IF;
    END IF;		
		
		
   EXCEPTION 
     WHEN OTHERS THEN 
	    p_validation_status := 'E';
		p_error_msg         := substr(SQLERRM,1,4000);
	    p_cd_id := -1;
	 
   END create_update_code_table;   
   
   PROCEDURE create_update_code_tab_col(
      p_user_name              IN        VARCHAR2,
	  p_cd_id                  IN        NUMBER,
	  p_code_tab_cols_tbl      IN        CANON_E001_CODE_TAB_COL_TYPE,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
	 lt_code_col_rec_tbl        CANON_E001_CODE_TAB_COL_TYPE;
	 lv_validation_status       VARCHAR2(1);
	 lv_error_msg               VARCHAR2(3999);
	 lv_procedure_name        VARCHAR2(30) := 'create_update_code_tab_col';
   BEGIN
   
      lt_code_col_rec_tbl := p_code_tab_cols_tbl;
	  FOR code_col IN 1..lt_code_col_rec_tbl.COUNT
	  LOOP
	    
		/* Update the col table if col_id is not null */
		IF lt_code_col_rec_tbl(code_col).col_id IS NOT NULL THEN 
		    UPDATE CANON_S21_CD_COL_TBL
			   SET  
			  DB_COL_NAME  = lt_code_col_rec_tbl(code_col).db_col_name, 
			  COL_FORMAT   = lt_code_col_rec_tbl(code_col).col_format, 
			  COL_PROMPT   = lt_code_col_rec_tbl(code_col).col_prompt, 
			  COL_SEQ      = lt_code_col_rec_tbl(code_col).col_seq,         
			  IS_MANDATORY = lt_code_col_rec_tbl(code_col).is_mandatory,        
			  RESULT_COL   = lt_code_col_rec_tbl(code_col).result_col     
			  WHERE cd_id = p_cd_id
			    AND col_id = lt_code_col_rec_tbl(code_col).col_id;
			
		ELSE 
		 /* Insert */
			 INSERT INTO CANON_S21_CD_COL_TBL ( 
			  CD_ID                  ,
			  COL_ID                 ,
			  DB_COL_NAME            ,
			  COL_FORMAT             ,
			  COL_PROMPT             ,
			  COL_SEQ                 ,
			  DEFAULT_VALUE             ,
			  IS_ACTIVE                 ,
			  IS_MANDATORY             ,
			  IS_UNIQUE                 ,
			  RESULT_COL             ,
			  CREATION_DATE          ,
			  CREATED_BY             ,
			  LAST_UPDATE_DATE       ,
			  LAST_UPDATED_BY        
		  )
		  values (
			p_cd_id, 
			CANON_S21_CD_COL_ID_SEQ.NEXTVAL, 
			lt_code_col_rec_tbl(code_col).db_col_name, 
			lt_code_col_rec_tbl(code_col).col_format, 
			lt_code_col_rec_tbl(code_col).col_prompt, 
			lt_code_col_rec_tbl(code_col).col_seq, 
			NULL,
			'Y', 
			lt_code_col_rec_tbl(code_col).is_mandatory, 
			'N', 
			lt_code_col_rec_tbl(code_col).result_col, 
			sysdate, 
			-1  ,  
			SYSDATE,
			-1
		  );
		END IF;  
	  END LOOP;
	  
	  
	  IF (lt_code_col_rec_tbl.COUNT > 0) THEN 
	  	  CANON_E001_CODETABLE_UI_PKG.create_code_table_view(p_user_name,p_cd_id, lv_validation_status,lv_error_msg);
	  END IF;
	  
	  IF lv_validation_status <> 'S' THEN 
	    p_validation_status := lv_validation_status;
		p_error_msg         := lv_error_msg;
	  ELSE 
	  	p_validation_status := 'S';
		p_error_msg         := NULL;
	  END IF;
	  
	  

       
   EXCEPTION 
     WHEN OTHERS THEN 
	     p_validation_status := 'E';
		 p_error_msg         := substr(SQLERRM,1,4000);
		 canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_cd_id,
												NULL,
												NULL,
												NULL,
												NULL,
												SQLERRM
                                             );
   END create_update_code_tab_col;
   
    PROCEDURE delete_code_tab_col(
      p_user_name              IN        VARCHAR2,
	  p_cd_id                  IN        NUMBER,
	  p_cd_col_id              IN        NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
     lv_validation_status   VARCHAR2 (1);
     lv_error_msg           VARCHAR2 (4000);
   BEGIN
   
        UPDATE CANON_S21_CD_COL_TBL
		 SET IS_ACTIVE           = 'N'
			,LAST_UPDATE_DATE = SYSDATE
			,LAST_UPDATED_BY  = -1
	   WHERE COL_ID = p_cd_col_id
		  AND CD_ID =p_cd_id;
		  
      /* Call create view to regenerate the view */		  
	  CANON_E001_CODETABLE_UI_PKG.create_code_table_view(p_user_name,p_cd_id, lv_validation_status,lv_error_msg);	  
      

	  IF lv_validation_status <> 'S' THEN 
	    p_validation_status := lv_validation_status;
		p_error_msg         := lv_error_msg;
	  ELSE 
	  	p_validation_status := 'S';
		p_error_msg         := NULL;
	  END IF;
       
   EXCEPTION 
     WHEN OTHERS THEN 
	     p_validation_status := 'E';
		 p_error_msg         := substr(SQLERRM,1,4000);
   END delete_code_tab_col;
   
   PROCEDURE get_code_tab_vals(
      p_user_name             IN        VARCHAR2,
	  p_cd_id                 IN        NUMBER,
	  p_order_by              IN        VARCHAR2,
	  p_sort_order            IN        VARCHAR2,
	  p_page_no               IN        NUMBER,
	  p_records_per_page      IN        NUMBER,
	  p_code_val_cur               OUT  result_cursor,
	  p_code_val_cnt               OUT  NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS 
		lv_col_list varchar2(3999) := '1';
		lv_main_query            VARCHAR2 (3999) := '';
		lv_sub_query             VARCHAR2 (3999) := '';
		lv_from_query            VARCHAR2 (3999) := '';
		lv_select_query          VARCHAR2 (3999) := '';
		ln_end_count             NUMBER;
		ln_start_count           NUMBER;
		ln_tot_records           NUMBER;
		lv_procedure_name        VARCHAR2(30) := 'get_code_tab_vals';
   BEGIN
     
	 IF p_cd_id is not null THEN 
	 
			BEGIN		
			SELECT   
				count(*)
			INTO ln_tot_records
			FROM canon_s21_cd_val_tbl
			WHERE cd_id =  p_cd_id
			 AND nvl(is_active,'Y') ='Y';	 
			EXCEPTION
			WHEN OTHERS THEN ln_tot_records := 0;
			END;
	 
		 lv_from_query := ' FROM CANON_S21_CD_VAL_TBL WHERE 1=1  AND nvl(is_active,''Y'') =''Y'' AND cd_id = '||p_cd_id;
		 /* Get the column list */
		 FOR col_list in (
			SELECT  
			  db_col_name,
			  col_prompt,             
			  result_col
			 FROM CANON_S21_CD_COL_TBL
			WHERE CD_ID =p_cd_id
			  AND  nvl(is_active,'Y') ='Y'
			  ORDER BY COL_SEQ
		 )
		 LOOP
			 lv_col_list := lv_col_list ||','|| col_list.result_col;
		 END LOOP;
		 IF LENGTH(lv_col_list) > 1 THEN 
		    lv_col_list := substr(lv_col_list,3,length(lv_col_list));
			 
			 lv_sub_query := 
				' SELECT cd_id, val_id, '||
				lv_col_list || ' '||
				lv_from_query;
			 
			 
			 IF ln_tot_records > 0
			 THEN
				ln_end_count := p_records_per_page * p_page_no;

				IF ln_end_count >= ln_tot_records
				THEN
				   ln_end_count := ln_tot_records;
				END IF;

				IF p_page_no = 1
				THEN
				   ln_start_count := 1;
				ELSE
				   ln_start_count := p_records_per_page * (p_page_no - 1) + 1;
				END IF;

				lv_main_query :=
					  'SELECT  '
				   || ' cd_val_res. * '
				   || '  FROM (SELECT cd_val.* '
				   || '               ,ROWNUM r1 '
				   || '          FROM ('
				   || lv_sub_query;
				
					/* Set the order by */			
					IF p_order_by IS NOT NULL
					THEN
					   lv_main_query := lv_main_query || '  ORDER BY ' || p_order_by;
					ELSE                                             -- Default Search
					   lv_main_query := lv_main_query || '  ORDER BY cd_id, val_id';
					END IF;
				
					IF p_sort_order IS NOT NULL
					THEN
					  lv_main_query := lv_main_query || ' ' || p_sort_order;
					ELSE 
                      lv_main_query := lv_main_query || '  ASC';
					END IF;
				
				
				lv_main_query := lv_main_query 
				   || '               ) cd_val '
				   || '       ) cd_val_res '
				   || ' WHERE r1 BETWEEN '
				   || ln_start_count
				   || ' AND '
				   || ln_end_count;
				   
				dbms_output.put_line(' lv_main_query: '||lv_main_query);

				OPEN p_code_val_cur FOR lv_main_query;

				p_code_val_cnt := ln_tot_records;
			 
			 
		   ELSE   
		       p_code_val_cnt := 0;
			   OPEN p_code_val_cur FOR 
				SELECT 1
				  FROM DUAL WHERE 1=2;
           END IF;		   
		ELSE
		   p_code_val_cnt := 0;
		   OPEN p_code_val_cur FOR 
		    SELECT 1
              FROM DUAL WHERE 1=2;
        END IF;		
		 
		 /* Get the values */
	 ELSE 
	     p_code_val_cnt := 0;
		  OPEN p_code_val_cur FOR 
		    SELECT 1
              FROM DUAL WHERE 1=2;
	 END IF;
	 p_validation_status := 'S';
	 p_error_msg         := NULL;
	 
	  canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_cd_id,
												p_order_by||'-'||p_sort_order,
												p_page_no||'-'||p_records_per_page,
												lv_main_query,
												SQLERRM
                                             );		
         
   EXCEPTION 
     WHEN OTHERS THEN 
	     p_validation_status := 'E';
		 p_error_msg         := substr(SQLERRM,1,4000);
		  p_code_val_cnt := 0;
		  OPEN p_code_val_cur FOR 
		    SELECT 1
              FROM DUAL WHERE 1=2;
          canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_cd_id,
												p_order_by||'-'||p_sort_order,
												p_page_no||'-'||p_records_per_page,
												lv_main_query,
												SQLERRM
                                             );					  
   END get_code_tab_vals;
   
    PROCEDURE create_update_code_tab_val(
      p_user_name              IN        VARCHAR2,
	  p_cd_id                  IN        NUMBER,
	  p_code_tab_cols_tbl      IN        CANON_E001_CODE_TAB_COL_TYPE,
	  p_code_tab_vals_tbl      IN        CANON_E001_CODE_TAB_VAL_TYPE,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS 
      lt_code_col_rec_tbl        CANON_E001_CODE_TAB_COL_TYPE;
	  lt_code_col_val_tbl        CANON_E001_CODE_TAB_VAL_TYPE;
	  lv_main_query VARCHAR2(32000);
	  lv_update_col VARCHAR2(32000);
	  lv_insert_col VARCHAR2(32000);
	  col_index      NUMBER := 1;
	  lv_result_col VARCHAR2(100);
	  lv_col_number NUMBER;
	  lv_col_name   VARCHAR2(100);
	  lv_col_val    VARCHAR2(4000);
	  lv_col_value    VARCHAR2(4000);
	  lv_procedure_name VARCHAR2(30) := 'create_update_code_tab_val' ;
	  lv_validation_status VARCHAR2(1);
	  lv_error_msg         VARCHAR2(32000);
	  ln_user_id  NUMBER := CANON_E001_CODETABLE_UI_PKG.get_user_id(p_user_name);

   BEGIN
   
      lt_code_col_rec_tbl := p_code_tab_cols_tbl;
	  lt_code_col_val_tbl := p_code_tab_vals_tbl;
	  lv_update_col := NULL;
	  lv_insert_col := NULL;
	   dbms_output.put_line(' 1');
	  g_cd_col_list_tbl.DELETE;
	  FOR code_col IN 1..lt_code_col_rec_tbl.COUNT
	  LOOP
	   lv_result_col := lt_code_col_rec_tbl(code_col).result_col;
	   IF lv_result_col IS NOT NULL THEN 
	     lv_insert_col := lv_insert_col || lv_result_col || ',';
		 
		 g_cd_col_list_tbl (col_index).result_col :=lv_result_col;		 
		 g_cd_col_list_tbl (col_index).column_num :=TO_NUMBER(SUBSTR(lv_result_col,4));
		 col_index := col_index+1;
		 
	   END IF;
	  END LOOP;
	  dbms_output.put_line(' 2');
	  
	  IF lv_insert_col IS NOT NULL THEN 
   	    lv_insert_col := '(cd_id, val_id,creation_Date,  last_update_date, created_by, last_updated_by, '||SUBSTR(lv_insert_col,1,LENGTH(lv_insert_col)-1)||')';
	  END IF;

	  dbms_output.put_line(' 3');
	  

	  IF lv_validation_status <> 'S' THEN
		  p_validation_status  := lv_validation_status;
		  p_error_msg          := lv_error_msg;
	  ELSE
			  FOR code_val IN 1..lt_code_col_val_tbl.COUNT
			  LOOP
				 lv_main_query := '';
				 lv_col_val    := '';
				 
				
						
				 /* Insert */

				 IF lt_code_col_val_tbl(code_val).val_id IS NULL THEN 
				 
					 lv_main_query := ' INSERT INTO CANON_S21_CD_VAL_TBL '||lv_insert_col ||'  VALUES ( '||lt_code_col_val_tbl(code_val).cd_id||','||CANON_S21_CD_VAL_ID_SEQ.nextval||','||
					                  'sysdate,sysdate,'|| ln_user_id||','||ln_user_id||' , ';
					 
					 FOR col_list  IN 1..g_cd_col_list_tbl.COUNT
					 LOOP 
						 lv_col_number := g_cd_col_list_tbl(col_list).column_num; 
						 lv_col_value := 
						  CASE WHEN lv_col_number = 1 THEN lt_code_col_val_tbl(code_val).VAL1
								WHEN lv_col_number = 2 THEN lt_code_col_val_tbl(code_val).VAL2
								WHEN lv_col_number = 3 THEN lt_code_col_val_tbl(code_val).VAL3
								WHEN lv_col_number = 4 THEN lt_code_col_val_tbl(code_val).VAL4
								WHEN lv_col_number = 5 THEN lt_code_col_val_tbl(code_val).VAL5
								WHEN lv_col_number = 6 THEN lt_code_col_val_tbl(code_val).VAL6
								WHEN lv_col_number = 7 THEN lt_code_col_val_tbl(code_val).VAL7
								WHEN lv_col_number = 8 THEN lt_code_col_val_tbl(code_val).VAL8
								WHEN lv_col_number = 9 THEN lt_code_col_val_tbl(code_val).VAL9
								WHEN lv_col_number = 10 THEN lt_code_col_val_tbl(code_val).VAL10
								WHEN lv_col_number = 11 THEN lt_code_col_val_tbl(code_val).VAL11
								WHEN lv_col_number = 12 THEN lt_code_col_val_tbl(code_val).VAL12
								WHEN lv_col_number = 13 THEN lt_code_col_val_tbl(code_val).VAL13
								WHEN lv_col_number = 14 THEN lt_code_col_val_tbl(code_val).VAL14
								WHEN lv_col_number = 15 THEN lt_code_col_val_tbl(code_val).VAL15
								WHEN lv_col_number = 16 THEN lt_code_col_val_tbl(code_val).VAL16
								WHEN lv_col_number = 17 THEN lt_code_col_val_tbl(code_val).VAL17
								WHEN lv_col_number = 18 THEN lt_code_col_val_tbl(code_val).VAL18
								WHEN lv_col_number = 19 THEN lt_code_col_val_tbl(code_val).VAL19
								WHEN lv_col_number = 20 THEN lt_code_col_val_tbl(code_val).VAL20
								WHEN lv_col_number = 21 THEN lt_code_col_val_tbl(code_val).VAL21
								WHEN lv_col_number = 22 THEN lt_code_col_val_tbl(code_val).VAL22
								WHEN lv_col_number = 23 THEN lt_code_col_val_tbl(code_val).VAL23
								WHEN lv_col_number = 24 THEN lt_code_col_val_tbl(code_val).VAL24
								WHEN lv_col_number = 25 THEN lt_code_col_val_tbl(code_val).VAL25
								WHEN lv_col_number = 26 THEN lt_code_col_val_tbl(code_val).VAL26
								WHEN lv_col_number = 27 THEN lt_code_col_val_tbl(code_val).VAL27
								WHEN lv_col_number = 28 THEN lt_code_col_val_tbl(code_val).VAL28
								WHEN lv_col_number = 29 THEN lt_code_col_val_tbl(code_val).VAL29
								WHEN lv_col_number = 30 THEN lt_code_col_val_tbl(code_val).VAL30
								WHEN lv_col_number = 31 THEN lt_code_col_val_tbl(code_val).VAL31
								WHEN lv_col_number = 32 THEN lt_code_col_val_tbl(code_val).VAL32
								WHEN lv_col_number = 33 THEN lt_code_col_val_tbl(code_val).VAL33
								WHEN lv_col_number = 34 THEN lt_code_col_val_tbl(code_val).VAL34
								WHEN lv_col_number = 35 THEN lt_code_col_val_tbl(code_val).VAL35
								WHEN lv_col_number = 36 THEN lt_code_col_val_tbl(code_val).VAL36
								WHEN lv_col_number = 37 THEN lt_code_col_val_tbl(code_val).VAL37
								WHEN lv_col_number = 38 THEN lt_code_col_val_tbl(code_val).VAL38
								WHEN lv_col_number = 39 THEN lt_code_col_val_tbl(code_val).VAL39
								WHEN lv_col_number = 40 THEN lt_code_col_val_tbl(code_val).VAL40
								WHEN lv_col_number = 41 THEN lt_code_col_val_tbl(code_val).VAL41
								WHEN lv_col_number = 42 THEN lt_code_col_val_tbl(code_val).VAL42
								WHEN lv_col_number = 43 THEN lt_code_col_val_tbl(code_val).VAL43
								WHEN lv_col_number = 44 THEN lt_code_col_val_tbl(code_val).VAL44
								WHEN lv_col_number = 45 THEN lt_code_col_val_tbl(code_val).VAL45
								WHEN lv_col_number = 46 THEN lt_code_col_val_tbl(code_val).VAL46
								WHEN lv_col_number = 47 THEN lt_code_col_val_tbl(code_val).VAL47
								WHEN lv_col_number = 48 THEN lt_code_col_val_tbl(code_val).VAL48
								WHEN lv_col_number = 49 THEN lt_code_col_val_tbl(code_val).VAL49
								WHEN lv_col_number = 50 THEN lt_code_col_val_tbl(code_val).VAL50
								WHEN lv_col_number = 51 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL51)
								WHEN lv_col_number = 52 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL52)
								WHEN lv_col_number = 53 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL53)
								WHEN lv_col_number = 54 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL54)
								WHEN lv_col_number = 55 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL55)
								WHEN lv_col_number = 56 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL56)
								WHEN lv_col_number = 57 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL57)
								WHEN lv_col_number = 58 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL58)
								WHEN lv_col_number = 59 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL59)
								WHEN lv_col_number = 60 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL60)
								WHEN lv_col_number = 61 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL61)
								WHEN lv_col_number = 62 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL62)
								WHEN lv_col_number = 63 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL63)
								WHEN lv_col_number = 64 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL64)
								WHEN lv_col_number = 65 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL65)
								WHEN lv_col_number = 66 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL66)
								WHEN lv_col_number = 67 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL67)
								WHEN lv_col_number = 68 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL68)
								WHEN lv_col_number = 69 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL69)
								WHEN lv_col_number = 70 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL70)
								WHEN lv_col_number = 71 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL71)
								WHEN lv_col_number = 72 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL72)
								WHEN lv_col_number = 73 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL73)
								WHEN lv_col_number = 74 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL74)
								WHEN lv_col_number = 75 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL75)
								WHEN lv_col_number = 76 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL76,'DD-MON-YYYY')
								WHEN lv_col_number = 77 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL77,'DD-MON-YYYY')
								WHEN lv_col_number = 78 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL78,'DD-MON-YYYY')
								WHEN lv_col_number = 79 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL79,'DD-MON-YYYY')
								WHEN lv_col_number = 80 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL80,'DD-MON-YYYY')
								WHEN lv_col_number = 81 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL81,'DD-MON-YYYY')
								WHEN lv_col_number = 82 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL82,'DD-MON-YYYY')
								WHEN lv_col_number = 83 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL83,'DD-MON-YYYY')
								WHEN lv_col_number = 84 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL84,'DD-MON-YYYY')
								WHEN lv_col_number = 85 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL85,'DD-MON-YYYY')
								WHEN lv_col_number = 86 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL86,'DD-MON-YYYY')
								WHEN lv_col_number = 87 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL87,'DD-MON-YYYY')
								WHEN lv_col_number = 88 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL88,'DD-MON-YYYY')
								WHEN lv_col_number = 89 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL89,'DD-MON-YYYY')
								WHEN lv_col_number = 90 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL90,'DD-MON-YYYY')
								WHEN lv_col_number = 91 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL91,'DD-MON-YYYY')
								WHEN lv_col_number = 92 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL92,'DD-MON-YYYY')
								WHEN lv_col_number = 93 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL93,'DD-MON-YYYY')
								WHEN lv_col_number = 94 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL94,'DD-MON-YYYY')
								WHEN lv_col_number = 95 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL95,'DD-MON-YYYY')
								WHEN lv_col_number = 96 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL96,'DD-MON-YYYY')
								WHEN lv_col_number = 97 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL97,'DD-MON-YYYY')
								WHEN lv_col_number = 98 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL98,'DD-MON-YYYY')
								WHEN lv_col_number = 99 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL99,'DD-MON-YYYY')
								WHEN lv_col_number = 100 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL100,'DD-MON-YYYY')
						   END;

					   IF  ((lv_col_number BETWEEN 1 and 50 ) OR (lv_col_number BETWEEN 76 AND 100)) THEN 
						 
						  lv_col_val := lv_col_val || NVL(
										  ''''|| 
										  lv_col_value
										  ||'''',NULL)||',';
		 
					   ELSIF (lv_col_number BETWEEN 51 and 75 ) then 
						  IF lv_col_value IS NOT NULL THEN 
						   lv_col_val := lv_col_val || lv_col_value||',';
						  ELSE 
							 lv_col_val := lv_col_val || ' NULL,';
						  END IF;
					   END IF;		   
					 END LOOP;		

					 IF LENGTH(lv_col_val) > 0  THEN 
						lv_col_val := SUBSTR(lv_col_val,1,LENGTH(lv_col_val)-1)||')';
					 END IF;	

					 lv_main_query := lv_main_query || lv_col_val;
					 dbms_output.put_line(' lv_main_query ' || lv_main_query);
					 
					 IF LENGTH(lv_main_query) > 0  THEN 
						EXECUTE IMMEDIATE lv_main_query;
						COMMIT;
					 END IF;
					 
				 /* Update */
				 ELSIF lt_code_col_val_tbl(code_val).val_id IS NOT NULL  THEN
					lv_main_query := ' UPDATE CANON_S21_CD_VAL_TBL SET last_update_date=sysdate, last_updated_by= '||ln_user_id;
					lv_update_col := '';
					 FOR col_list  IN 1..g_cd_col_list_tbl.COUNT
					 LOOP 
						lv_col_name := g_cd_col_list_tbl(col_list).result_col;
						lv_col_number := g_cd_col_list_tbl(col_list).column_num;
						lv_col_value := 
						  CASE WHEN lv_col_number = 1 THEN lt_code_col_val_tbl(code_val).VAL1
								WHEN lv_col_number = 2 THEN lt_code_col_val_tbl(code_val).VAL2
								WHEN lv_col_number = 3 THEN lt_code_col_val_tbl(code_val).VAL3
								WHEN lv_col_number = 4 THEN lt_code_col_val_tbl(code_val).VAL4
								WHEN lv_col_number = 5 THEN lt_code_col_val_tbl(code_val).VAL5
								WHEN lv_col_number = 6 THEN lt_code_col_val_tbl(code_val).VAL6
								WHEN lv_col_number = 7 THEN lt_code_col_val_tbl(code_val).VAL7
								WHEN lv_col_number = 8 THEN lt_code_col_val_tbl(code_val).VAL8
								WHEN lv_col_number = 9 THEN lt_code_col_val_tbl(code_val).VAL9
								WHEN lv_col_number = 10 THEN lt_code_col_val_tbl(code_val).VAL10
								WHEN lv_col_number = 11 THEN lt_code_col_val_tbl(code_val).VAL11
								WHEN lv_col_number = 12 THEN lt_code_col_val_tbl(code_val).VAL12
								WHEN lv_col_number = 13 THEN lt_code_col_val_tbl(code_val).VAL13
								WHEN lv_col_number = 14 THEN lt_code_col_val_tbl(code_val).VAL14
								WHEN lv_col_number = 15 THEN lt_code_col_val_tbl(code_val).VAL15
								WHEN lv_col_number = 16 THEN lt_code_col_val_tbl(code_val).VAL16
								WHEN lv_col_number = 17 THEN lt_code_col_val_tbl(code_val).VAL17
								WHEN lv_col_number = 18 THEN lt_code_col_val_tbl(code_val).VAL18
								WHEN lv_col_number = 19 THEN lt_code_col_val_tbl(code_val).VAL19
								WHEN lv_col_number = 20 THEN lt_code_col_val_tbl(code_val).VAL20
								WHEN lv_col_number = 21 THEN lt_code_col_val_tbl(code_val).VAL21
								WHEN lv_col_number = 22 THEN lt_code_col_val_tbl(code_val).VAL22
								WHEN lv_col_number = 23 THEN lt_code_col_val_tbl(code_val).VAL23
								WHEN lv_col_number = 24 THEN lt_code_col_val_tbl(code_val).VAL24
								WHEN lv_col_number = 25 THEN lt_code_col_val_tbl(code_val).VAL25
								WHEN lv_col_number = 26 THEN lt_code_col_val_tbl(code_val).VAL26
								WHEN lv_col_number = 27 THEN lt_code_col_val_tbl(code_val).VAL27
								WHEN lv_col_number = 28 THEN lt_code_col_val_tbl(code_val).VAL28
								WHEN lv_col_number = 29 THEN lt_code_col_val_tbl(code_val).VAL29
								WHEN lv_col_number = 30 THEN lt_code_col_val_tbl(code_val).VAL30
								WHEN lv_col_number = 31 THEN lt_code_col_val_tbl(code_val).VAL31
								WHEN lv_col_number = 32 THEN lt_code_col_val_tbl(code_val).VAL32
								WHEN lv_col_number = 33 THEN lt_code_col_val_tbl(code_val).VAL33
								WHEN lv_col_number = 34 THEN lt_code_col_val_tbl(code_val).VAL34
								WHEN lv_col_number = 35 THEN lt_code_col_val_tbl(code_val).VAL35
								WHEN lv_col_number = 36 THEN lt_code_col_val_tbl(code_val).VAL36
								WHEN lv_col_number = 37 THEN lt_code_col_val_tbl(code_val).VAL37
								WHEN lv_col_number = 38 THEN lt_code_col_val_tbl(code_val).VAL38
								WHEN lv_col_number = 39 THEN lt_code_col_val_tbl(code_val).VAL39
								WHEN lv_col_number = 40 THEN lt_code_col_val_tbl(code_val).VAL40
								WHEN lv_col_number = 41 THEN lt_code_col_val_tbl(code_val).VAL41
								WHEN lv_col_number = 42 THEN lt_code_col_val_tbl(code_val).VAL42
								WHEN lv_col_number = 43 THEN lt_code_col_val_tbl(code_val).VAL43
								WHEN lv_col_number = 44 THEN lt_code_col_val_tbl(code_val).VAL44
								WHEN lv_col_number = 45 THEN lt_code_col_val_tbl(code_val).VAL45
								WHEN lv_col_number = 46 THEN lt_code_col_val_tbl(code_val).VAL46
								WHEN lv_col_number = 47 THEN lt_code_col_val_tbl(code_val).VAL47
								WHEN lv_col_number = 48 THEN lt_code_col_val_tbl(code_val).VAL48
								WHEN lv_col_number = 49 THEN lt_code_col_val_tbl(code_val).VAL49
								WHEN lv_col_number = 50 THEN lt_code_col_val_tbl(code_val).VAL50
								WHEN lv_col_number = 51 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL51)
								WHEN lv_col_number = 52 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL52)
								WHEN lv_col_number = 53 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL53)
								WHEN lv_col_number = 54 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL54)
								WHEN lv_col_number = 55 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL55)
								WHEN lv_col_number = 56 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL56)
								WHEN lv_col_number = 57 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL57)
								WHEN lv_col_number = 58 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL58)
								WHEN lv_col_number = 59 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL59)
								WHEN lv_col_number = 60 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL60)
								WHEN lv_col_number = 61 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL61)
								WHEN lv_col_number = 62 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL62)
								WHEN lv_col_number = 63 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL63)
								WHEN lv_col_number = 64 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL64)
								WHEN lv_col_number = 65 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL65)
								WHEN lv_col_number = 66 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL66)
								WHEN lv_col_number = 67 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL67)
								WHEN lv_col_number = 68 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL68)
								WHEN lv_col_number = 69 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL69)
								WHEN lv_col_number = 70 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL70)
								WHEN lv_col_number = 71 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL71)
								WHEN lv_col_number = 72 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL72)
								WHEN lv_col_number = 73 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL73)
								WHEN lv_col_number = 74 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL74)
								WHEN lv_col_number = 75 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL75)
								WHEN lv_col_number = 76 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL76,'DD-MON-YYYY')
								WHEN lv_col_number = 77 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL77,'DD-MON-YYYY')
								WHEN lv_col_number = 78 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL78,'DD-MON-YYYY')
								WHEN lv_col_number = 79 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL79,'DD-MON-YYYY')
								WHEN lv_col_number = 80 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL80,'DD-MON-YYYY')
								WHEN lv_col_number = 81 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL81,'DD-MON-YYYY')
								WHEN lv_col_number = 82 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL82,'DD-MON-YYYY')
								WHEN lv_col_number = 83 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL83,'DD-MON-YYYY')
								WHEN lv_col_number = 84 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL84,'DD-MON-YYYY')
								WHEN lv_col_number = 85 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL85,'DD-MON-YYYY')
								WHEN lv_col_number = 86 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL86,'DD-MON-YYYY')
								WHEN lv_col_number = 87 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL87,'DD-MON-YYYY')
								WHEN lv_col_number = 88 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL88,'DD-MON-YYYY')
								WHEN lv_col_number = 89 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL89,'DD-MON-YYYY')
								WHEN lv_col_number = 90 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL90,'DD-MON-YYYY')
								WHEN lv_col_number = 91 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL91,'DD-MON-YYYY')
								WHEN lv_col_number = 92 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL92,'DD-MON-YYYY')
								WHEN lv_col_number = 93 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL93,'DD-MON-YYYY')
								WHEN lv_col_number = 94 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL94,'DD-MON-YYYY')
								WHEN lv_col_number = 95 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL95,'DD-MON-YYYY')
								WHEN lv_col_number = 96 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL96,'DD-MON-YYYY')
								WHEN lv_col_number = 97 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL97,'DD-MON-YYYY')
								WHEN lv_col_number = 98 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL98,'DD-MON-YYYY')
								WHEN lv_col_number = 99 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL99,'DD-MON-YYYY')
								WHEN lv_col_number = 100 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL100,'DD-MON-YYYY')
						   END;

						lv_update_col := lv_update_col||lv_col_name || ' = ';
						dbms_output.put_line(' lv_update_col ' || lv_update_col);
						  IF  ((lv_col_number BETWEEN 1 and 50 ) OR (lv_col_number BETWEEN 76 AND 100)) THEN 
							  lv_update_col := lv_update_col  || NVL(
										  ''''|| lv_col_value||'''',NULL)||',';
						  ELSIF (lv_col_number BETWEEN 51 and 75 ) then 
							IF lv_col_value IS NOT NULL THEN 
							   lv_update_col := lv_update_col || lv_col_value||',';
							ELSE 
								 lv_update_col := lv_update_col || ' NULL,';
							END IF;
						  END IF;
					   END LOOP;
					   IF LENGTH(lv_update_col) > 0  THEN 
						lv_update_col := SUBSTR(lv_update_col,1,LENGTH(lv_update_col)-1);
					   END IF;	

					   lv_main_query := lv_main_query || ','||lv_update_col|| ' WHERE cd_id = '|| lt_code_col_val_tbl(code_val).cd_id ||' AND val_id = '|| lt_code_col_val_tbl(code_val).val_id;
					   dbms_output.put_line(' lv_main_query ' || lv_main_query);
					   canon_e001_codetable_ui_pkg.log_error (g_package_name,
														lv_procedure_name,
														p_user_name,
														p_cd_id,
														NULL,
														NULL,
														lv_main_query,
														SQLERRM
													 );		
					 
					   IF LENGTH(lv_main_query) > 0  THEN 
						 EXECUTE IMMEDIATE lv_main_query;
						 COMMIT;
					   END IF;		       
				 END IF;
				 
			  END LOOP;
			  
			 p_validation_status := 'S';
			 p_error_msg         := NULL;
			 
	  END IF;
         
   EXCEPTION 
     WHEN OTHERS THEN 
	     p_validation_status := 'E';
		 p_error_msg         := substr(lv_main_query ||SQLERRM,1,4000);
		  canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_cd_id,
												NULL,
												NULL,
												lv_main_query,
												SQLERRM
                                             );		
   END create_update_code_tab_val;
   
   PROCEDURE delete_code_tab_val(
      p_user_name              IN        VARCHAR2,
	  p_cd_id                  IN        NUMBER,
	  p_cd_val_id              IN        NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS 
   BEGIN
     UPDATE canon_s21_cd_val_tbl
	    SET is_active       ='N',
		     end_date_active = SYSDATE
	  WHERE cd_id  = p_cd_id
	    AND val_id = p_cd_val_id;
		
     p_validation_status := 'S';
	 p_error_msg         := NULL;
         
   EXCEPTION 
     WHEN OTHERS THEN 
	     p_validation_status := 'E';
		 p_error_msg         := substr(SQLERRM,1,4000);
   END delete_code_tab_val;
   
   
   PROCEDURE create_code_table_view(
      p_user_name              IN        VARCHAR2,
      p_cd_id                  IN       NUMBER,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS 
        lv_col_list varchar2(3999) := '1';
		lv_view_col_list varchar2(3999) := '1';
		lv_view_query    VARCHAR2 (3999) := '';
		lv_cd_name       varchar2(500);
		lv_view_name     varchar2(500);
		lv_view_exists   VARCHAR2(1);
		lv_procedure_name  VARCHAR2(30) := 'create_code_table_view';
   BEGIN
   
         BEGIN 
		   SELECT  
			  cd.cd_name,
			  cd.cd_view_name
			 INTO lv_cd_name, lv_view_name
             FROM canon_s21_cd_tbl cd 
            WHERE cd_id = p_cd_id;			 
		 
		 EXCEPTION 
		   WHEN OTHERS THEN 
		      lv_cd_name := NULL;
			  lv_view_name := NULL;
		 END ;
  
   
         /* Get the column list */
		 FOR col_list in (
			SELECT  
			  db_col_name,
			  col_prompt,             
			  result_col
			 FROM canon_s21_cd_col_tbl col
			WHERE 1=1
			  AND col.CD_ID =p_cd_id
			  AND nvl(col.is_active,'Y') ='Y'
			  ORDER BY  col.col_seq
		 )
		 LOOP
			 lv_col_list := lv_col_list ||','|| col_list.result_col;
			 lv_view_col_list := lv_view_col_list ||','|| col_list.db_col_name;
		 END LOOP;
		 
		 IF LENGTH(lv_col_list) > 1 THEN 
		      lv_col_list := substr(lv_col_list,3,length(lv_col_list));
		 END IF;
		 
		 IF LENGTH(lv_view_col_list) > 1 THEN 
		      lv_view_col_list := substr(lv_view_col_list,3,length(lv_view_col_list));
		 END IF;
		 
		 /* Create view only if the column list is derived */
		 if ( lv_cd_name IS NOT NULL AND 
		      lv_view_name IS NOT NULL AND 
		      LENGTH(lv_col_list) > 1 AND 
			  LENGTH(lv_view_col_list) > 1 ) THEN 
		 
			 dbms_output.put_line('lv_col_list '||lv_col_list) ;
			 dbms_output.put_line('lv_view_col_list '||lv_view_col_list) ;
			 dbms_output.put_line('lv_cd_name '||lv_cd_name) ;
			 dbms_output.put_line('lv_view_name '||lv_view_name) ;
			 
			 /* form the view query */
			 lv_view_query := 
			   'CREATE OR REPLACE VIEW '|| lv_view_name || '(  ' ||
			   ' CODE_TABLE_NAME, '||
			   ' CD_ID, '|| 
			   ' VAL_ID, '|| 
			   lv_view_col_list ||
			   ') '||
			   'AS SELECT '||
			   ''''|| lv_cd_name || '''' ||
			   ','||p_cd_id || ', '||
			   ' val.val_id , '||
			   lv_col_list ||
			   ' FROM CANON_S21_CD_TBL cd, '||
			   ' CANON_S21_CD_VAL_TBL val'||
			   ' WHERE cd.cd_id = val.cd_id '||
			   ' AND nvl(is_active,''Y'') =''Y'''  ||
			   ' AND cd.cd_name = '''||  lv_cd_name ||''''||
			   ' ORDER BY cd.CD_ID,val.VAL_ID ';
			   
			 dbms_output.put_line('view query '||lv_view_query) ;
			 
			 execute immediate lv_view_query;		 
        else /* if there are no active columns, drop the view */
           /* See whether the view is already created. If so, drop it */		
			 BEGIN 
			   SELECT  
				 'Y'
				 INTO lv_view_exists
				 FROM all_objects
				WHERE object_name = lv_view_name
				  AND object_type ='VIEW';
			 
			 EXCEPTION 
			   WHEN OTHERS THEN 
				  lv_cd_name := NULL;
				  lv_view_name := NULL;
			 END ;
			 /* If view exists drop it as there are no active columns in the view */
			 
			 IF lv_view_exists ='Y' THEN 
			    execute immediate ' drop view '||lv_view_name;
			 END IF;
        end if;
     p_validation_status := 'S';
	 p_error_msg         := NULL;
	   canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_cd_id,
												lv_view_name,
												NULL,
												lv_view_query,
												SQLERRM
                                             );		
   
   EXCEPTION 
     WHEN OTHERS THEN 
	     p_validation_status := 'E';
		 p_error_msg         := substr(SQLERRM,1,4000);
		  canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_cd_id,
												lv_view_name,
												NULL,
												lv_view_query,
												SQLERRM
                                             );		
   END create_code_table_view;
   
   PROCEDURE log_error(
      p_package_name      IN        VARCHAR2,
	  p_procedure_name    IN        VARCHAR2,
	  p_key1              IN        VARCHAR2, 
	  p_key2              IN        VARCHAR2,
	  p_key3              IN        VARCHAR2,
	  p_key4              IN        VARCHAR2, 
	  p_key5              IN        VARCHAR2,
	  p_sqlerrm            IN        VARCHAR2 
   )
   IS
      pragma autonomous_transaction;
   BEGIN
      INSERT INTO CANON_E001_ERRORS_TBL(
		program_name,
		process_name,
		key1,
		key2,
		key3,
		key4,
		key5,
		error_msg,
		error_date
	  )
	  values(
	  p_package_name,
	  p_procedure_name,
	  p_key1,
	  p_key2,
	  p_key3,
	  p_key4,
	  p_key5,
	  p_sqlerrm,
	  sysdate
	  );
	  commit;
   EXCEPTION 
       WHEN OTHERS THEN 
	     null;
   END log_error;
   
    PROCEDURE record_duplicate_check(
      p_user_name              IN        VARCHAR2,      
      p_cd_id                  IN        NUMBER,
	  p_code_tab_cols_tbl      IN        CANON_E001_CODE_TAB_COL_TYPE,
	  p_code_tab_vals_tbl      IN        CANON_E001_CODE_TAB_VAL_TYPE,
	  p_validation_status          OUT  VARCHAR2,
	  p_error_msg                  OUT  VARCHAR2
   )
   IS
   lt_code_col_rec_tbl        CANON_E001_CODE_TAB_COL_TYPE;
	  lt_code_col_val_tbl     CANON_E001_CODE_TAB_VAL_TYPE;
	  lt_code_col_val_tbl1     CANON_E001_CODE_TAB_VAL_TYPE;
	  lt_code_col_val_tbl2     CANON_E001_CODE_TAB_VAL_TYPE;
	  lv_main_query VARCHAR2(32000);
	  lv_update_col VARCHAR2(32000);
	  lv_insert_col VARCHAR2(32000);
	  lv_display_msg VARCHAR2(32000);
	  lv_dup_val_check  VARCHAR2(32000);
	  col_index      NUMBER := 1;
	  lv_result_col VARCHAR2(100);
	  lv_col_number NUMBER;
	  lv_col_name   VARCHAR2(100);
	  lv_col_prompt   VARCHAR2(100);
	  lv_col_val    VARCHAR2(4000);
	  lv_col_value    VARCHAR2(4000);
	  lv_procedure_name VARCHAR2(30) := 'record_duplicate_check' ;
	  lv_validation_status VARCHAR2(1);
	  lv_error_msg         VARCHAR2(32000);
	  lg_cd_col_list_tbl cd_col_list_tab;
	  ln_dup_cnt  NUMBER;
	  --lr_dup_check_rec CANON_S21_CD_VAL_TEMP_TBL%ROWTYPE;
	  lc_dup_check_cur result_cursor;
	  lv_select_columns VARCHAR2(5000) := 'VAL1,VAL2,VAL3,VAL4,VAL5,VAL6,VAL7,VAL8,VAL9,VAL10, '||
											'VAL11,VAL12,VAL13,VAL14,VAL15,VAL16,VAL17,VAL18,VAL19,VAL20, '||
											'VAL21,VAL22,VAL23,VAL24,VAL25,VAL26,VAL27,VAL28,VAL29,VAL30, '||
											'VAL31,VAL32,VAL33,VAL34,VAL35,VAL36,VAL37,VAL38,VAL39,VAL40, '||
											'VAL41,VAL42,VAL43,VAL44,VAL45,VAL46,VAL47,VAL48,VAL49,VAL50, '||
											'VAL51,VAL52,VAL53,VAL54,VAL55,VAL56,VAL57,VAL58,VAL59,VAL60, '||
											'VAL61,VAL62,VAL63,VAL64,VAL65,VAL66,VAL67,VAL68,VAL69,VAL70, '||
											'VAL71,VAL72,VAL73,VAL74,VAL75,VAL76,VAL77,VAL78,VAL79,VAL80, '||
											'VAL81,VAL82,VAL83,VAL84,VAL85,VAL86,VAL87,VAL88,VAL89,VAL90, '||
											'VAL91,VAL92,VAL93,VAL94,VAL95,VAL96,VAL97,VAL98,VAL99,VAL100';
     TYPE lr_dup_check_record
     IS	  RECORD (
		 VAL1					 VARCHAR2 (2000 CHAR)    ,
		VAL2					 VARCHAR2 (2000 CHAR)    ,
		VAL3					 VARCHAR2 (2000 CHAR)    ,
		VAL4					 VARCHAR2 (2000 CHAR)    ,
		VAL5					 VARCHAR2 (2000 CHAR)    ,
		VAL6					 VARCHAR2 (2000 CHAR)    ,
		VAL7					 VARCHAR2 (2000 CHAR)    ,
		VAL8					 VARCHAR2 (2000 CHAR)    ,
		VAL9					 VARCHAR2 (2000 CHAR)    ,
		VAL10					 VARCHAR2 (2000 CHAR)    ,
		VAL11					 VARCHAR2 (2000 CHAR)    ,
		VAL12					 VARCHAR2 (2000 CHAR)    ,
		VAL13					 VARCHAR2 (2000 CHAR)    ,
		VAL14					 VARCHAR2 (2000 CHAR)    ,
		VAL15					 VARCHAR2 (2000 CHAR)    ,
		VAL16					 VARCHAR2 (2000 CHAR)    ,
		VAL17					 VARCHAR2 (2000 CHAR)    ,
		VAL18					 VARCHAR2 (2000 CHAR)    ,
		VAL19					 VARCHAR2 (2000 CHAR)    ,
		VAL20					 VARCHAR2 (2000 CHAR)    ,
		VAL21					 VARCHAR2 (2000 CHAR)    ,
		VAL22					 VARCHAR2 (2000 CHAR)    ,
		VAL23					 VARCHAR2 (2000 CHAR)    ,
		VAL24					 VARCHAR2 (2000 CHAR)    ,
		VAL25					 VARCHAR2 (2000 CHAR)    ,
		VAL26					 VARCHAR2 (2000 CHAR)    ,
		VAL27					 VARCHAR2 (2000 CHAR)    ,
		VAL28					 VARCHAR2 (2000 CHAR)    ,
		VAL29					 VARCHAR2 (2000 CHAR)    ,
		VAL30					 VARCHAR2 (2000 CHAR)    ,
		VAL31					 VARCHAR2 (2000 CHAR)    ,
		VAL32					 VARCHAR2 (2000 CHAR)    ,
		VAL33					 VARCHAR2 (2000 CHAR)    ,
		VAL34					 VARCHAR2 (2000 CHAR)    ,
		VAL35					 VARCHAR2 (2000 CHAR)    ,
		VAL36					 VARCHAR2 (2000 CHAR)    ,
		VAL37					 VARCHAR2 (2000 CHAR)    ,
		VAL38					 VARCHAR2 (2000 CHAR)    ,
		VAL39					 VARCHAR2 (2000 CHAR)    ,
		VAL40					 VARCHAR2 (2000 CHAR)    ,
		VAL41					 VARCHAR2 (2000 CHAR)    ,
		VAL42					 VARCHAR2 (2000 CHAR)    ,
		VAL43					 VARCHAR2 (2000 CHAR)    ,
		VAL44					 VARCHAR2 (2000 CHAR)    ,
		VAL45					 VARCHAR2 (2000 CHAR)    ,
		VAL46					 VARCHAR2 (2000 CHAR)    ,
		VAL47					 VARCHAR2 (2000 CHAR)    ,
		VAL48					 VARCHAR2 (2000 CHAR)    ,
		VAL49					 VARCHAR2 (2000 CHAR)    ,
		VAL50					 VARCHAR2 (2000 CHAR)    ,
		VAL51					 NUMBER			    ,
		VAL52					 NUMBER			    ,
		VAL53					 NUMBER			    ,
		VAL54					 NUMBER			    ,
		VAL55					 NUMBER			    ,
		VAL56					 NUMBER			    ,
		VAL57					 NUMBER			    ,
		VAL58					 NUMBER			    ,
		VAL59					 NUMBER			    ,
		VAL60					 NUMBER			    ,
		VAL61					 NUMBER			    ,
		VAL62					 NUMBER			    ,
		VAL63					 NUMBER			    ,
		VAL64					 NUMBER			    ,
		VAL65					 NUMBER			    ,
		VAL66					 NUMBER			    ,
		VAL67					 NUMBER			    ,
		VAL68					 NUMBER			    ,
		VAL69					 NUMBER			    ,
		VAL70					 NUMBER			    ,
		VAL71					 NUMBER			    ,
		VAL72					 NUMBER			    ,
		VAL73					 NUMBER			    ,
		VAL74					 NUMBER			    ,
		VAL75					 NUMBER			    ,  
		VAL76					 DATE			    ,
		VAL77					 DATE			    ,
		VAL78					 DATE			    ,
		VAL79					 DATE			    ,
		VAL80					 DATE			    ,
		VAL81					 DATE			    ,
		VAL82					 DATE			    ,
		VAL83					 DATE			    ,
		VAL84					 DATE			    ,
		VAL85					 DATE			    ,
		VAL86					 DATE			    ,
		VAL87					 DATE			    ,
		VAL88					 DATE			    ,
		VAL89					 DATE			    ,
		VAL90					 DATE			    ,
		VAL91					 DATE			    ,
		VAL92					 DATE			    ,
		VAL93					 DATE			    ,
		VAL94					 DATE			    ,
		VAL95					 DATE			    ,
		VAL96					 DATE			    ,
		VAL97					 DATE			    ,
		VAL98					 DATE			    ,
		VAL99					 DATE			    ,
		VAL100				 DATE			    
	 );
	 lr_dup_check_rec lr_dup_check_record;
	 
   BEGIN
   
      DELETE FROM CANON_S21_CD_VAL_TEMP_TBL WHERE CD_ID = p_cd_id AND NVL(is_active,'Y') ='Y';
	   
      lt_code_col_rec_tbl := p_code_tab_cols_tbl;
	  lt_code_col_val_tbl := p_code_tab_vals_tbl;
	  lt_code_col_val_tbl1 := p_code_tab_vals_tbl;
	  lt_code_col_val_tbl2 := p_code_tab_vals_tbl;
	  lv_update_col := NULL;
	  lv_insert_col := NULL;
	  lv_display_msg := NULL;
	   dbms_output.put_line(' 1');
	  lg_cd_col_list_tbl.DELETE;
	  FOR code_col IN 1..lt_code_col_rec_tbl.COUNT
	  LOOP
	   lv_result_col := lt_code_col_rec_tbl(code_col).result_col;
	   IF lv_result_col IS NOT NULL THEN 
	     lv_insert_col := lv_insert_col || lv_result_col || ',';
		  
		 
		 lg_cd_col_list_tbl (col_index).result_col :=lv_result_col;		 
		 lg_cd_col_list_tbl (col_index).column_num :=TO_NUMBER(SUBSTR(lv_result_col,4));
		 lg_cd_col_list_tbl (col_index).col_prompt :=lt_code_col_rec_tbl(code_col).col_prompt;
		 if lg_cd_col_list_tbl (col_index).column_num between 1 and 50 then 
		   lv_dup_val_check := lv_dup_val_check||' NVL( ' || lv_result_col || ',''$$$''),';
		 elsif lg_cd_col_list_tbl (col_index).column_num between 51 and 75 then 
		   lv_dup_val_check := lv_dup_val_check||' NVL( ' || lv_result_col || ',-9999),';
		 else 
		   lv_dup_val_check := lv_dup_val_check||' NVL( ' || lv_result_col || ',TO_CHAR(SYSDATE+30,''DD-MON-YYYY'')),';
		   
		 end if;
		 col_index := col_index+1;
		 
	   END IF;
	  END LOOP;
	  dbms_output.put_line(' 2');
	  
	  IF lv_insert_col IS NOT NULL THEN 
	    lv_dup_val_check := SUBSTR(lv_dup_val_check,1,LENGTH(lv_dup_val_check)-1);
   	    lv_insert_col := '(cd_id, val_id,'||SUBSTR(lv_insert_col,1,LENGTH(lv_insert_col)-1)||')';
		
	  END IF;

	  dbms_output.put_line(' 3');
	  
	  FOR code_val IN 1..lt_code_col_val_tbl.COUNT
	  LOOP
		 lv_main_query := '';
		 lv_col_val    := '';
		 
		 IF lt_code_col_val_tbl(code_val).val_id IS NULL THEN 
		 lv_main_query := ' INSERT INTO CANON_S21_CD_VAL_TEMP_TBL '||lv_insert_col ||'  VALUES ( '||lt_code_col_val_tbl(code_val).cd_id||',NULL, ';
		 ELSE 
  		   lv_main_query := ' INSERT INTO CANON_S21_CD_VAL_TEMP_TBL '||lv_insert_col ||'  VALUES ( '||lt_code_col_val_tbl(code_val).cd_id||','||lt_code_col_val_tbl(code_val).val_id||', ';
		 END IF;
				 
			 FOR col_list  IN 1..lg_cd_col_list_tbl.COUNT
			 LOOP 
				 lv_col_number := lg_cd_col_list_tbl(col_list).column_num; 
				 lv_col_value := 
				  CASE WHEN lv_col_number = 1 THEN lt_code_col_val_tbl(code_val).VAL1
						WHEN lv_col_number = 2 THEN lt_code_col_val_tbl(code_val).VAL2
						WHEN lv_col_number = 3 THEN lt_code_col_val_tbl(code_val).VAL3
						WHEN lv_col_number = 4 THEN lt_code_col_val_tbl(code_val).VAL4
						WHEN lv_col_number = 5 THEN lt_code_col_val_tbl(code_val).VAL5
						WHEN lv_col_number = 6 THEN lt_code_col_val_tbl(code_val).VAL6
						WHEN lv_col_number = 7 THEN lt_code_col_val_tbl(code_val).VAL7
						WHEN lv_col_number = 8 THEN lt_code_col_val_tbl(code_val).VAL8
						WHEN lv_col_number = 9 THEN lt_code_col_val_tbl(code_val).VAL9
						WHEN lv_col_number = 10 THEN lt_code_col_val_tbl(code_val).VAL10
						WHEN lv_col_number = 11 THEN lt_code_col_val_tbl(code_val).VAL11
						WHEN lv_col_number = 12 THEN lt_code_col_val_tbl(code_val).VAL12
						WHEN lv_col_number = 13 THEN lt_code_col_val_tbl(code_val).VAL13
						WHEN lv_col_number = 14 THEN lt_code_col_val_tbl(code_val).VAL14
						WHEN lv_col_number = 15 THEN lt_code_col_val_tbl(code_val).VAL15
						WHEN lv_col_number = 16 THEN lt_code_col_val_tbl(code_val).VAL16
						WHEN lv_col_number = 17 THEN lt_code_col_val_tbl(code_val).VAL17
						WHEN lv_col_number = 18 THEN lt_code_col_val_tbl(code_val).VAL18
						WHEN lv_col_number = 19 THEN lt_code_col_val_tbl(code_val).VAL19
						WHEN lv_col_number = 20 THEN lt_code_col_val_tbl(code_val).VAL20
						WHEN lv_col_number = 21 THEN lt_code_col_val_tbl(code_val).VAL21
						WHEN lv_col_number = 22 THEN lt_code_col_val_tbl(code_val).VAL22
						WHEN lv_col_number = 23 THEN lt_code_col_val_tbl(code_val).VAL23
						WHEN lv_col_number = 24 THEN lt_code_col_val_tbl(code_val).VAL24
						WHEN lv_col_number = 25 THEN lt_code_col_val_tbl(code_val).VAL25
						WHEN lv_col_number = 26 THEN lt_code_col_val_tbl(code_val).VAL26
						WHEN lv_col_number = 27 THEN lt_code_col_val_tbl(code_val).VAL27
						WHEN lv_col_number = 28 THEN lt_code_col_val_tbl(code_val).VAL28
						WHEN lv_col_number = 29 THEN lt_code_col_val_tbl(code_val).VAL29
						WHEN lv_col_number = 30 THEN lt_code_col_val_tbl(code_val).VAL30
						WHEN lv_col_number = 31 THEN lt_code_col_val_tbl(code_val).VAL31
						WHEN lv_col_number = 32 THEN lt_code_col_val_tbl(code_val).VAL32
						WHEN lv_col_number = 33 THEN lt_code_col_val_tbl(code_val).VAL33
						WHEN lv_col_number = 34 THEN lt_code_col_val_tbl(code_val).VAL34
						WHEN lv_col_number = 35 THEN lt_code_col_val_tbl(code_val).VAL35
						WHEN lv_col_number = 36 THEN lt_code_col_val_tbl(code_val).VAL36
						WHEN lv_col_number = 37 THEN lt_code_col_val_tbl(code_val).VAL37
						WHEN lv_col_number = 38 THEN lt_code_col_val_tbl(code_val).VAL38
						WHEN lv_col_number = 39 THEN lt_code_col_val_tbl(code_val).VAL39
						WHEN lv_col_number = 40 THEN lt_code_col_val_tbl(code_val).VAL40
						WHEN lv_col_number = 41 THEN lt_code_col_val_tbl(code_val).VAL41
						WHEN lv_col_number = 42 THEN lt_code_col_val_tbl(code_val).VAL42
						WHEN lv_col_number = 43 THEN lt_code_col_val_tbl(code_val).VAL43
						WHEN lv_col_number = 44 THEN lt_code_col_val_tbl(code_val).VAL44
						WHEN lv_col_number = 45 THEN lt_code_col_val_tbl(code_val).VAL45
						WHEN lv_col_number = 46 THEN lt_code_col_val_tbl(code_val).VAL46
						WHEN lv_col_number = 47 THEN lt_code_col_val_tbl(code_val).VAL47
						WHEN lv_col_number = 48 THEN lt_code_col_val_tbl(code_val).VAL48
						WHEN lv_col_number = 49 THEN lt_code_col_val_tbl(code_val).VAL49
						WHEN lv_col_number = 50 THEN lt_code_col_val_tbl(code_val).VAL50
						WHEN lv_col_number = 51 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL51)
						WHEN lv_col_number = 52 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL52)
						WHEN lv_col_number = 53 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL53)
						WHEN lv_col_number = 54 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL54)
						WHEN lv_col_number = 55 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL55)
						WHEN lv_col_number = 56 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL56)
						WHEN lv_col_number = 57 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL57)
						WHEN lv_col_number = 58 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL58)
						WHEN lv_col_number = 59 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL59)
						WHEN lv_col_number = 60 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL60)
						WHEN lv_col_number = 61 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL61)
						WHEN lv_col_number = 62 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL62)
						WHEN lv_col_number = 63 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL63)
						WHEN lv_col_number = 64 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL64)
						WHEN lv_col_number = 65 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL65)
						WHEN lv_col_number = 66 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL66)
						WHEN lv_col_number = 67 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL67)
						WHEN lv_col_number = 68 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL68)
						WHEN lv_col_number = 69 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL69)
						WHEN lv_col_number = 70 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL70)
						WHEN lv_col_number = 71 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL71)
						WHEN lv_col_number = 72 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL72)
						WHEN lv_col_number = 73 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL73)
						WHEN lv_col_number = 74 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL74)
						WHEN lv_col_number = 75 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL75)
						WHEN lv_col_number = 76 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL76,'DD-MON-YYYY')
						WHEN lv_col_number = 77 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL77,'DD-MON-YYYY')
						WHEN lv_col_number = 78 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL78,'DD-MON-YYYY')
						WHEN lv_col_number = 79 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL79,'DD-MON-YYYY')
						WHEN lv_col_number = 80 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL80,'DD-MON-YYYY')
						WHEN lv_col_number = 81 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL81,'DD-MON-YYYY')
						WHEN lv_col_number = 82 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL82,'DD-MON-YYYY')
						WHEN lv_col_number = 83 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL83,'DD-MON-YYYY')
						WHEN lv_col_number = 84 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL84,'DD-MON-YYYY')
						WHEN lv_col_number = 85 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL85,'DD-MON-YYYY')
						WHEN lv_col_number = 86 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL86,'DD-MON-YYYY')
						WHEN lv_col_number = 87 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL87,'DD-MON-YYYY')
						WHEN lv_col_number = 88 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL88,'DD-MON-YYYY')
						WHEN lv_col_number = 89 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL89,'DD-MON-YYYY')
						WHEN lv_col_number = 90 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL90,'DD-MON-YYYY')
						WHEN lv_col_number = 91 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL91,'DD-MON-YYYY')
						WHEN lv_col_number = 92 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL92,'DD-MON-YYYY')
						WHEN lv_col_number = 93 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL93,'DD-MON-YYYY')
						WHEN lv_col_number = 94 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL94,'DD-MON-YYYY')
						WHEN lv_col_number = 95 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL95,'DD-MON-YYYY')
						WHEN lv_col_number = 96 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL96,'DD-MON-YYYY')
						WHEN lv_col_number = 97 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL97,'DD-MON-YYYY')
						WHEN lv_col_number = 98 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL98,'DD-MON-YYYY')
						WHEN lv_col_number = 99 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL99,'DD-MON-YYYY')
						WHEN lv_col_number = 100 THEN TO_CHAR(lt_code_col_val_tbl(code_val).VAL100,'DD-MON-YYYY')
				   END;

			   IF  ((lv_col_number BETWEEN 1 and 50 ) OR (lv_col_number BETWEEN 76 AND 100)) THEN 
				 IF lv_col_value IS NOT NULL THEN
				  lv_col_val := lv_col_val ||
								  ''''|| 
								  lv_col_value
								  ||''''||',';
                 ELSE 
				 	 lv_col_val := lv_col_val || ' NULL,';
                 END IF;				 
 
			   ELSIF (lv_col_number BETWEEN 51 and 75 ) then 
				  IF lv_col_value IS NOT NULL THEN 
				   lv_col_val := lv_col_val || lv_col_value||',';
				  ELSE 
					 lv_col_val := lv_col_val || ' NULL,';
				  END IF;
			   END IF;		   
			 END LOOP;		
 dbms_output.put_line(' lv_col_val ' || lv_col_val);
			 IF LENGTH(lv_col_val) > 0  THEN 
				lv_col_val := SUBSTR(lv_col_val,1,LENGTH(lv_col_val)-1)||')';
			 END IF;	
 dbms_output.put_line(' lv_main_query ' || lv_main_query);
			 lv_main_query := lv_main_query || lv_col_val;
			 dbms_output.put_line(' lv_main_query ' || lv_main_query);
			 
			 IF LENGTH(lv_main_query) > 0  THEN 
				EXECUTE IMMEDIATE lv_main_query;
				COMMIT;
			 END IF;
 	 
       END LOOP;
	   
	   INSERT INTO CANON_S21_CD_VAL_TEMP_TBL 
	   SELECT * FROM CANON_S21_CD_VAL_TBL 
	    WHERE CD_ID = p_cd_id 
   	      AND NVL(is_active,'Y') ='Y'
		  AND VAL_ID NOT IN (SELECT DISTINCT nvl(VAL_ID,-1) FROM CANON_S21_CD_VAL_TEMP_TBL WHERE cd_id = p_cd_id);
	   COMMIT;

       lv_main_query := 'SELECT distinct ' || lv_select_columns ||
                      	 '  from CANON_S21_CD_VAL_TEMP_TBL WHERE cd_id = '||p_cd_id ||' AND  ('||lv_dup_val_check ||')   IN '||
	                    '(SELECT '||lv_dup_val_check|| ' FROM CANON_S21_CD_VAL_TEMP_TBL WHERE cd_id = '||p_cd_id ||' GROUP BY '||lv_dup_val_check||
						' HAVING COUNT(*) > 1)';
       col_index := 1;	

FOR CHK IN (SELECT * FROM CANON_S21_CD_VAL_TEMP_TBL  WHERE cd_id = p_cd_id )	   
LOOP
  dbms_output.put_line(' CHK.VAL1 ' || CHK.VAL1);	   
  dbms_output.put_line(' CHK.VALid ' || CHK.VAL_id);	   
END LOOP;

dbms_output.put_line(' lv_main_query ' || lv_main_query);	   
	   
	   OPEN lc_dup_check_cur FOR lv_main_query;
	   LOOP
	    FETCH lc_dup_check_cur INTO lr_dup_check_rec;
		 EXIT WHEN lc_dup_check_cur%NOTFOUND;
		 
        
		  dbms_output.put_line(' inside duplicate check ' );
		 FOR col_list  IN 1..lg_cd_col_list_tbl.COUNT
			 LOOP 
				lv_col_prompt := lg_cd_col_list_tbl(col_list).col_prompt;
				lv_col_number := lg_cd_col_list_tbl(col_list).column_num; 
				lv_col_value := 
				  CASE WHEN lv_col_number = 1 THEN lr_dup_check_rec.VAL1
						WHEN lv_col_number = 2 THEN lr_dup_check_rec.VAL2
						WHEN lv_col_number = 3 THEN lr_dup_check_rec.VAL3
						WHEN lv_col_number = 4 THEN lr_dup_check_rec.VAL4
						WHEN lv_col_number = 5 THEN lr_dup_check_rec.VAL5
						WHEN lv_col_number = 6 THEN lr_dup_check_rec.VAL6
						WHEN lv_col_number = 7 THEN lr_dup_check_rec.VAL7
						WHEN lv_col_number = 8 THEN lr_dup_check_rec.VAL8
						WHEN lv_col_number = 9 THEN lr_dup_check_rec.VAL9
						WHEN lv_col_number = 10 THEN lr_dup_check_rec.VAL10
						WHEN lv_col_number = 11 THEN lr_dup_check_rec.VAL11
						WHEN lv_col_number = 12 THEN lr_dup_check_rec.VAL12
						WHEN lv_col_number = 13 THEN lr_dup_check_rec.VAL13
						WHEN lv_col_number = 14 THEN lr_dup_check_rec.VAL14
						WHEN lv_col_number = 15 THEN lr_dup_check_rec.VAL15
						WHEN lv_col_number = 16 THEN lr_dup_check_rec.VAL16
						WHEN lv_col_number = 17 THEN lr_dup_check_rec.VAL17
						WHEN lv_col_number = 18 THEN lr_dup_check_rec.VAL18
						WHEN lv_col_number = 19 THEN lr_dup_check_rec.VAL19
						WHEN lv_col_number = 20 THEN lr_dup_check_rec.VAL20
						WHEN lv_col_number = 21 THEN lr_dup_check_rec.VAL21
						WHEN lv_col_number = 22 THEN lr_dup_check_rec.VAL22
						WHEN lv_col_number = 23 THEN lr_dup_check_rec.VAL23
						WHEN lv_col_number = 24 THEN lr_dup_check_rec.VAL24
						WHEN lv_col_number = 25 THEN lr_dup_check_rec.VAL25
						WHEN lv_col_number = 26 THEN lr_dup_check_rec.VAL26
						WHEN lv_col_number = 27 THEN lr_dup_check_rec.VAL27
						WHEN lv_col_number = 28 THEN lr_dup_check_rec.VAL28
						WHEN lv_col_number = 29 THEN lr_dup_check_rec.VAL29
						WHEN lv_col_number = 30 THEN lr_dup_check_rec.VAL30
						WHEN lv_col_number = 31 THEN lr_dup_check_rec.VAL31
						WHEN lv_col_number = 32 THEN lr_dup_check_rec.VAL32
						WHEN lv_col_number = 33 THEN lr_dup_check_rec.VAL33
						WHEN lv_col_number = 34 THEN lr_dup_check_rec.VAL34
						WHEN lv_col_number = 35 THEN lr_dup_check_rec.VAL35
						WHEN lv_col_number = 36 THEN lr_dup_check_rec.VAL36
						WHEN lv_col_number = 37 THEN lr_dup_check_rec.VAL37
						WHEN lv_col_number = 38 THEN lr_dup_check_rec.VAL38
						WHEN lv_col_number = 39 THEN lr_dup_check_rec.VAL39
						WHEN lv_col_number = 40 THEN lr_dup_check_rec.VAL40
						WHEN lv_col_number = 41 THEN lr_dup_check_rec.VAL41
						WHEN lv_col_number = 42 THEN lr_dup_check_rec.VAL42
						WHEN lv_col_number = 43 THEN lr_dup_check_rec.VAL43
						WHEN lv_col_number = 44 THEN lr_dup_check_rec.VAL44
						WHEN lv_col_number = 45 THEN lr_dup_check_rec.VAL45
						WHEN lv_col_number = 46 THEN lr_dup_check_rec.VAL46
						WHEN lv_col_number = 47 THEN lr_dup_check_rec.VAL47
						WHEN lv_col_number = 48 THEN lr_dup_check_rec.VAL48
						WHEN lv_col_number = 49 THEN lr_dup_check_rec.VAL49
						WHEN lv_col_number = 50 THEN lr_dup_check_rec.VAL50
						WHEN lv_col_number = 51 THEN TO_CHAR(lr_dup_check_rec.VAL51)
						WHEN lv_col_number = 52 THEN TO_CHAR(lr_dup_check_rec.VAL52)
						WHEN lv_col_number = 53 THEN TO_CHAR(lr_dup_check_rec.VAL53)
						WHEN lv_col_number = 54 THEN TO_CHAR(lr_dup_check_rec.VAL54)
						WHEN lv_col_number = 55 THEN TO_CHAR(lr_dup_check_rec.VAL55)
						WHEN lv_col_number = 56 THEN TO_CHAR(lr_dup_check_rec.VAL56)
						WHEN lv_col_number = 57 THEN TO_CHAR(lr_dup_check_rec.VAL57)
						WHEN lv_col_number = 58 THEN TO_CHAR(lr_dup_check_rec.VAL58)
						WHEN lv_col_number = 59 THEN TO_CHAR(lr_dup_check_rec.VAL59)
						WHEN lv_col_number = 60 THEN TO_CHAR(lr_dup_check_rec.VAL60)
						WHEN lv_col_number = 61 THEN TO_CHAR(lr_dup_check_rec.VAL61)
						WHEN lv_col_number = 62 THEN TO_CHAR(lr_dup_check_rec.VAL62)
						WHEN lv_col_number = 63 THEN TO_CHAR(lr_dup_check_rec.VAL63)
						WHEN lv_col_number = 64 THEN TO_CHAR(lr_dup_check_rec.VAL64)
						WHEN lv_col_number = 65 THEN TO_CHAR(lr_dup_check_rec.VAL65)
						WHEN lv_col_number = 66 THEN TO_CHAR(lr_dup_check_rec.VAL66)
						WHEN lv_col_number = 67 THEN TO_CHAR(lr_dup_check_rec.VAL67)
						WHEN lv_col_number = 68 THEN TO_CHAR(lr_dup_check_rec.VAL68)
						WHEN lv_col_number = 69 THEN TO_CHAR(lr_dup_check_rec.VAL69)
						WHEN lv_col_number = 70 THEN TO_CHAR(lr_dup_check_rec.VAL70)
						WHEN lv_col_number = 71 THEN TO_CHAR(lr_dup_check_rec.VAL71)
						WHEN lv_col_number = 72 THEN TO_CHAR(lr_dup_check_rec.VAL72)
						WHEN lv_col_number = 73 THEN TO_CHAR(lr_dup_check_rec.VAL73)
						WHEN lv_col_number = 74 THEN TO_CHAR(lr_dup_check_rec.VAL74)
						WHEN lv_col_number = 75 THEN TO_CHAR(lr_dup_check_rec.VAL75)
						WHEN lv_col_number = 76 THEN TO_CHAR(lr_dup_check_rec.VAL76,'DD-MON-YYYY')
						WHEN lv_col_number = 77 THEN TO_CHAR(lr_dup_check_rec.VAL77,'DD-MON-YYYY')
						WHEN lv_col_number = 78 THEN TO_CHAR(lr_dup_check_rec.VAL78,'DD-MON-YYYY')
						WHEN lv_col_number = 79 THEN TO_CHAR(lr_dup_check_rec.VAL79,'DD-MON-YYYY')
						WHEN lv_col_number = 80 THEN TO_CHAR(lr_dup_check_rec.VAL80,'DD-MON-YYYY')
						WHEN lv_col_number = 81 THEN TO_CHAR(lr_dup_check_rec.VAL81,'DD-MON-YYYY')
						WHEN lv_col_number = 82 THEN TO_CHAR(lr_dup_check_rec.VAL82,'DD-MON-YYYY')
						WHEN lv_col_number = 83 THEN TO_CHAR(lr_dup_check_rec.VAL83,'DD-MON-YYYY')
						WHEN lv_col_number = 84 THEN TO_CHAR(lr_dup_check_rec.VAL84,'DD-MON-YYYY')
						WHEN lv_col_number = 85 THEN TO_CHAR(lr_dup_check_rec.VAL85,'DD-MON-YYYY')
						WHEN lv_col_number = 86 THEN TO_CHAR(lr_dup_check_rec.VAL86,'DD-MON-YYYY')
						WHEN lv_col_number = 87 THEN TO_CHAR(lr_dup_check_rec.VAL87,'DD-MON-YYYY')
						WHEN lv_col_number = 88 THEN TO_CHAR(lr_dup_check_rec.VAL88,'DD-MON-YYYY')
						WHEN lv_col_number = 89 THEN TO_CHAR(lr_dup_check_rec.VAL89,'DD-MON-YYYY')
						WHEN lv_col_number = 90 THEN TO_CHAR(lr_dup_check_rec.VAL90,'DD-MON-YYYY')
						WHEN lv_col_number = 91 THEN TO_CHAR(lr_dup_check_rec.VAL91,'DD-MON-YYYY')
						WHEN lv_col_number = 92 THEN TO_CHAR(lr_dup_check_rec.VAL92,'DD-MON-YYYY')
						WHEN lv_col_number = 93 THEN TO_CHAR(lr_dup_check_rec.VAL93,'DD-MON-YYYY')
						WHEN lv_col_number = 94 THEN TO_CHAR(lr_dup_check_rec.VAL94,'DD-MON-YYYY')
						WHEN lv_col_number = 95 THEN TO_CHAR(lr_dup_check_rec.VAL95,'DD-MON-YYYY')
						WHEN lv_col_number = 96 THEN TO_CHAR(lr_dup_check_rec.VAL96,'DD-MON-YYYY')
						WHEN lv_col_number = 97 THEN TO_CHAR(lr_dup_check_rec.VAL97,'DD-MON-YYYY')
						WHEN lv_col_number = 98 THEN TO_CHAR(lr_dup_check_rec.VAL98,'DD-MON-YYYY')
						WHEN lv_col_number = 99 THEN TO_CHAR(lr_dup_check_rec.VAL99,'DD-MON-YYYY')
						WHEN lv_col_number = 100 THEN TO_CHAR(lr_dup_check_rec.VAL100,'DD-MON-YYYY')
				   END;

				--lv_display_msg := lv_display_msg ||lv_col_prompt ;
				dbms_output.put_line(' lv_display_msg ' || lv_display_msg);
				IF lv_col_value IS NOT NULL THEN 
                       lv_display_msg := lv_display_msg||' <b> '|| lv_col_prompt ||' </b>  = ' || 
								  ''''|| lv_col_value||''''||' , ';								  
                END IF;
					 
			   END LOOP;
			   IF LENGTH(lv_display_msg) > 0  THEN 
				lv_display_msg := SUBSTR(lv_display_msg,1,LENGTH(lv_display_msg)-2);
			 END IF;
		 p_validation_status := 'W';
		 p_error_msg         := p_error_msg || '('||col_index||') '||lv_display_msg ||'<br>';
		 dbms_output.put_line(' lv_display_msg ' || lv_display_msg);
		 col_index := col_index+1;
	   END LOOP;
	   CLOSE lc_dup_check_cur;

	   IF p_error_msg IS NOT NULL THEN
	     p_error_msg := '<br> Following Record combination already exists-> <br>'|| p_error_msg;
	   END IF ;
	   
	   IF p_validation_status IS  NULL THEN 
	     p_error_msg := NULL;
		 p_validation_status := 'S';
	   END IF; 
	   
     
   EXCEPTION 
     WHEN OTHERS THEN 
	  p_error_msg :=  lv_main_query||SQLERRM;
	  p_validation_status := 'E';
	 canon_e001_codetable_ui_pkg.log_error (g_package_name,
                                                lv_procedure_name,
                                                p_user_name,
												p_cd_id,
												NULL,
												NULL,
												lv_main_query,
												SQLERRM
                                             );		
   END record_duplicate_check;
   
   
END CANON_E001_CODETABLE_UI_PKG;
/