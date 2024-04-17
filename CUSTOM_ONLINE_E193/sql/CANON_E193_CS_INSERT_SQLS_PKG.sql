CREATE OR REPLACE PACKAGE CANON_E193_CS_INSERT_SQLS_PKG
IS
    g_regcode   VARCHAR2 (30) := 'EAST_REGION';
   PROCEDURE UPDATE_CATEGORY (
      p_cat_id           IN       NUMBER
     ,p_role_id          IN       VARCHAR2
     ,p_role_code        IN       VARCHAR2
     ,p_role_name        IN       VARCHAR2
     ,p_role_type_code   IN       VARCHAR2
     ,p_group_id         IN       NUMBER
     ,p_group_name       IN       VARCHAR2
     ,p_group_desc       IN       VARCHAR2
     ,p_resource_id      IN       VARCHAR2
     ,p_resource_name    IN       VARCHAR2
     ,p_success          OUT      NUMBER
   );
   PROCEDURE UPDATE_TICKET_OWNER (
      p_hdr_tbl      IN       canon_e193_number_tbl_typ
     ,p_new_owner    IN       VARCHAR2
     ,p_prev_owner   IN       VARCHAR2
     ,p_org_id       IN       NUMBER
     ,p_user_id      IN       VARCHAR2
     ,p_success      OUT      NUMBER
   );
END CANON_E193_CS_INSERT_SQLS_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E193_CS_INSERT_SQLS_PKG
IS
   PROCEDURE UPDATE_CATEGORY (
      p_cat_id           IN         NUMBER
     ,p_role_id          IN          VARCHAR2
     ,p_role_code        IN        VARCHAR2
     ,p_role_name        IN        VARCHAR2
     ,p_role_type_code   IN      VARCHAR2
     ,p_group_id             IN      NUMBER
     ,p_group_name       IN       VARCHAR2
     ,p_group_desc       IN        VARCHAR2
     ,p_resource_id      IN         VARCHAR2
     ,p_resource_name    IN     VARCHAR2
     ,p_success          OUT      NUMBER
   )
   IS
      l_role_code           VARCHAR2 (100):=Null;
      l_role_name          VARCHAR2 (100):=Null;
      l_role_type_code   VARCHAR2 (100):=Null;
      l_group_name        VARCHAR2 (100):=Null;
      l_group_desc         VARCHAR2 (100):=Null;
      l_resource_name    VARCHAR2 (400):=Null;
      l_error                    VARCHAR2 (2000):=Null;
   BEGIN
      IF p_role_code = '$' THEN
         l_role_code    := NULL;
      ELSE
         l_role_code    := p_role_code;
      END IF;
      IF p_role_name = '$' THEN
         l_role_name    := NULL;
      ELSE
         l_role_name    := p_role_name;
      END IF;
      IF p_role_type_code = '$' THEN
         l_role_type_code    := NULL;
      ELSE
         l_role_type_code    := p_role_type_code;
      END IF;
      IF p_group_name = '$' THEN
         l_group_name    := NULL;
      ELSE
         l_group_name    := p_group_name;
      END IF;
      IF p_group_desc = '$' THEN
         l_group_desc    := NULL;
      ELSE
         l_group_desc    := p_group_desc;
      END IF;
      IF p_resource_name = '$' THEN
         l_resource_name    := NULL;
      ELSE
         l_resource_name    := p_resource_name;
      END IF;
      BEGIN
         UPDATE canon_e193_cs_categories
         SET crm_role_id = p_role_id
                ,crm_role_code = l_role_code
                ,crm_role_name = l_role_name
                ,crm_role_type_code = l_role_type_code
                ,crm_group_id = p_group_id
                ,crm_group_name = l_group_name
                ,crm_group_desc = l_group_desc
                ,crm_resource_id = p_resource_id
                ,crm_resource_name = l_resource_name
		,last_update_date=SYSDATE
         WHERE  cat_id = p_cat_id;
         COMMIT;
         p_success    := 1;
      EXCEPTION
         WHEN OTHERS THEN
            p_success    := 0;
            ROLLBACK;
            l_error      := 'Exception while updating Categories : ' || SQLERRM;
            INSERT INTO canon_e193_cs_errors
                        (program_name
                        ,error_msg
                        ,error_date)
            VALUES('UPDATE_CATEGORY'
                        ,l_error
                        ,SYSDATE);
      END;
   END UPDATE_CATEGORY;
   PROCEDURE UPDATE_TICKET_OWNER (
      p_hdr_tbl           IN       canon_e193_number_tbl_typ
     ,p_new_owner    IN       VARCHAR2
     ,p_prev_owner    IN       VARCHAR2
     ,p_org_id            IN       NUMBER
     ,p_user_id          IN       VARCHAR2
     ,p_success         OUT   NUMBER
   )
   IS
      l_res_id                     VARCHAR2(200):=Null;
      l_role_id                    VARCHAR2(200):=Null;
      l_dept_code              VARCHAR2 (200):=Null;
      updatesql                  VARCHAR2 (2000):=Null;
      insql                          VARCHAR2 (2000) := '';
      l_error                       VARCHAR2 (2000):=Null;
      l_date                        DATE:= SYSDATE;
      l_ticket_id                  NUMBER:=Null;
      new_owner_user_id   NUMBER:=Null;
      l_cfsaccessflag          VARCHAR2 (250):=Null;
      l_cfsuserflag              VARCHAR2 (250):=Null;
      l_regcode                  VARCHAR2 (250):=Null;
   BEGIN
   
      l_regcode    := g_regcode;
      canon_e193_cs_evolution_pkg.get_owner_details (p_new_owner, p_org_id, l_regcode, NULL,
                                                                                 l_res_id, l_role_id, l_dept_code);
      BEGIN
         -- Get date as per the timezone for respective org
         l_date:= canon_e193_cs_evolution_pkg.get_date (p_org_id);
         FOR i IN 1 .. p_hdr_tbl.COUNT
         LOOP
            l_ticket_id    := p_hdr_tbl (i);
            UPDATE canon_e193_cs_headers
            SET owner_res_id = l_res_id
                   ,owner_role_id = l_role_id
                   ,owner_dept_code = l_dept_code
                   ,last_updated_by = p_user_id
                   ,last_update_date = l_date
            WHERE owner_res_id = p_prev_owner
            AND org_id = p_org_id
            AND ticket_id = p_hdr_tbl (i);
            COMMIT;
         END LOOP;
         p_success    := 1;
      EXCEPTION
         WHEN OTHERS THEN
            p_success    := 0;
            ROLLBACK;
            l_error      := 'Exception while updating Ticket owner : ' || SQLERRM;
            INSERT INTO canon_e193_cs_errors
                        (program_name
                        ,attribute1
                        ,attribute3
                        ,attribute4
                        ,error_msg
                        ,error_date)
            VALUES('UPDATE_TICKET_OWNER'
                          ,'Ticket# ' || l_ticket_id
                          ,'Previous_Owner ' || p_prev_owner
                          ,'New_Owner ' || p_new_owner
                          ,l_error
                          ,SYSDATE);
      END;
   END UPDATE_TICKET_OWNER;
END CANON_E193_CS_INSERT_SQLS_PKG;
/
