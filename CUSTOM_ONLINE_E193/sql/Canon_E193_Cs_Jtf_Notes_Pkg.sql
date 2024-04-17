CREATE OR REPLACE PACKAGE CANON_E193_CS_JTF_NOTES_PKG
AS
/*******************************************************************************
Program Name        : Canon_E193_Cs_Jtf_Notes_Pkg.sql
Author              : Vikas Basal
Functional Overview : APIs for CS Ticket Notes operations.
Table modified      : JTF_NOTES API Call
Comments            :
Modification History:
---------------------------------------------------------------------------------
Author              Version         Date                    Comments
---------------------------------------------------------------------------------
Vikas Basal         1.00            19-SEP-2005      Initial creation
Dipti Shedji        2.00            23-NOV-2005      Modified error messages
                                                   - Added Ticket Number to be
                             inserted in error table
********************************************************************************/
PROCEDURE canon_e193_cs_create_notes (l_source_object_id IN NUMBER, 
				      l_source_object_code IN VARCHAR2, 
				      l_notes_detail IN VARCHAR2,
                                    l_org_id IN NUMBER, 
                                    l_entered_by IN VARCHAR2, 
                                    l_note_status IN VARCHAR2, 
                                    l_note_id OUT NUMBER);

PROCEDURE canon_e193_cs_delete_notes(l_note_id IN NUMBER);

PROCEDURE CANON_E193_CS_UPDATE_NOTES (l_source_object_id     IN     NUMBER,
                                      l_source_object_code   IN     VARCHAR2,
                                      l_note_id              IN     NUMBER,
                                      l_notes_detail         IN     VARCHAR2,
                                      l_note_status          IN     VARCHAR2,
                                      l_append_flag          IN     VARCHAR2,
                                      l_updated_by           IN     VARCHAR2,
                                      l_ret_status              OUT NUMBER);

PROCEDURE debug_msg( 
   l_program_name IN VARCHAR2, 
   l_attribute1 IN VARCHAR2 DEFAULT NULL,
   l_attribute2 IN VARCHAR2 DEFAULT NULL,
   l_attribute3 IN VARCHAR2 DEFAULT NULL,
   l_attribute4 IN VARCHAR2 DEFAULT NULL,
   l_attribute5 IN VARCHAR2 DEFAULT NULL,
   l_error_msg  IN VARCHAR2
);

END CANON_E193_CS_JTF_NOTES_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E193_CS_JTF_NOTES_PKG AS
/*******************************************************************************
Program Name        : Canon_E193_Cs_Jtf_Notes_Pkg.sql
Author              : Vikas Basal
Functional Overview : APIs for CS Ticket Notes operations.
Table modified      : JTF_NOTES API Call
Comments            :
Modification History:
---------------------------------------------------------------------------------
Author              Version         Date                    Comments
---------------------------------------------------------------------------------
Vikas Basal         1.00            19-SEP-2005         Initial creation
Dipti Shedji        2.00            23-NOV-2005      Modified error messages
                                                   - Added Ticket Number to be
                             inserted in error table
********************************************************************************/

PROCEDURE writeDatatoLob
------------------------------------------------------------------------------
--  Procedure    : writeDatatoLob
------------------------------------------------------------------------------
( x_jtf_note_id IN  NUMBER
, x_buffer      IN  VARCHAR2
)
IS

  Position   INTEGER := 1;

  CURSOR c1
  IS SELECT notes_detail
     FROM  canon_e193_notes_tl
     WHERE jtf_note_id = x_jtf_note_id
     FOR UPDATE;

BEGIN
  FOR i IN c1
  LOOP
    DBMS_LOB.WRITE(i.notes_detail,LENGTH(x_buffer),position,x_buffer);
  END LOOP;
END WriteDataToLob;

PROCEDURE writeLobToData
------------------------------------------------------------------------------
--  Procedure    : writeLobToData
------------------------------------------------------------------------------
( x_jtf_note_id               NUMBER
, x_buffer         OUT NOCOPY VARCHAR2
)
IS
  lob_loc   CLOB;
  Amount    BINARY_INTEGER := 32767;
  Position  INTEGER := 1;
  Buffer    VARCHAR2(32767);
  Chunksize INTEGER;

BEGIN

  SELECT notes_detail
  INTO lob_loc
  FROM canon_e193_notes_tl
  WHERE jtf_note_id = x_jtf_note_id;

  Chunksize := DBMS_LOB.GETCHUNKSIZE(lob_loc);

  IF Chunksize IS NOT NULL
  THEN
    IF chunksize < 32767
    THEN
      amount := (32767/chunksize) * chunksize;
    END IF;

    DBMS_LOB.READ(lob_loc,amount,position,buffer);

  END IF;

  x_buffer := buffer;

EXCEPTION
  WHEN NO_DATA_FOUND
  THEN
    x_buffer := NULL;
  WHEN TOO_MANY_ROWS
  THEN
    x_buffer := NULL;
END WriteLobtoData;

------------------------------------------------------------------------------
-- Create_note
--   Inserts a note record in the JTF_NOTES_B, JTF_NOTES_TL
--   and JTF_NOTE_CONTEXTS table
------------------------------------------------------------------------------
PROCEDURE canon_e193_cs_create_notes (l_source_object_id IN NUMBER, l_source_object_code IN VARCHAR2, l_notes_detail IN VARCHAR2,
                                    l_org_id IN NUMBER, l_entered_by IN VARCHAR2, l_note_status IN VARCHAR2, l_note_id OUT NUMBER) IS

l_entered_date DATE := SYSDATE;
l_last_update_date DATE := SYSDATE;
l_creation_date DATE := SYSDATE;
l_return_status VARCHAR2(1);
l_msg_count NUMBER;
l_msg_data VARCHAR2(2000);
l_error VARCHAR2(2000) := NULL;
l_ticket_id NUMBER;
lv_note_id NUMBER :=NULL;

l_notes  VARCHAR2(4000);

BEGIN
/*
    Jtf_Notes_Pub.Create_note@CANDEV(p_api_version => 1.0,
                p_source_object_id => l_source_object_id,
                p_source_object_code => l_source_object_code,
                p_org_id  => l_org_id,
                p_notes_detail => l_notes_detail,
                p_entered_by => l_entered_by,
                p_entered_date => l_entered_date,
                p_last_update_date => l_last_update_date,
                p_creation_date => l_creation_date,
                x_jtf_note_id    => l_note_id,
                x_return_status => l_return_status,
                x_msg_count => l_msg_count,
                x_msg_data => l_msg_data);
*/

INSERT INTO canon_e193_notes_b 
(jtf_note_id,source_object_id,source_object_code,last_update_date,last_updated_by,creation_date,created_by,last_update_login,note_status,
entered_by,entered_date)
VALUES (CANON_E193_NOTES_SEQ.NEXTVAL,l_source_object_id,l_source_object_code,sysdate,l_entered_by,sysdate,l_entered_by,'-1',l_note_status,l_entered_by,sysdate);
COMMIT;

BEGIN
SELECT jtf_note_id
INTO lv_note_id
FROM CANON_E193_NOTES_B
WHERE source_object_id = l_source_object_id
AND    source_object_code = l_source_object_code;
         
EXCEPTION WHEN OTHERS
THEN
lv_note_id:=NULL;
l_return_status:='E';
END;

l_notes:=substr(l_notes_detail,1,3000);

INSERT INTO CANON_E193_NOTES_TL
VALUES(lv_note_id,l_notes,EMPTY_CLOB(),'US','US',SYSDATE,l_entered_by,SYSDATE,l_entered_by,'-1','');
COMMIT;
writedatatolob(lv_note_id,l_notes_detail);
COMMIT;

    IF (l_return_status <> 'S' ) THEN
    
        SELECT ticket_id
        INTO l_ticket_id
        FROM canon_e193_cs_lines
        WHERE line_id = l_source_object_id
        ;
        
        FOR i IN 1..l_msg_count
        LOOP
            l_msg_data := l_msg_data;--||Fnd_Msg_Pub.get(i,Fnd_Api.g_false);
        END LOOP;
        debug_msg(l_program_name  => 'canon_e193_cs_create_notes',
                          l_attribute1 => 'Ticket# ' || l_ticket_id,
                          l_attribute2 => 'Line# ' || l_source_object_id,
                          l_attribute5 => 'After call to create_note api',
                          l_error_msg => 'l_msg_data :'||SUBSTR(l_msg_data,1,220)
                        );
    /*
    ELSE
        debug_msg(l_program_name  => 'canon_e193_cs_create_notes',
                          l_error_msg =>'Notes Created Sucessfully. Created Notes ID is:' || l_note_id
                         );
    */
    END IF;
    
EXCEPTION 
WHEN OTHERS THEN
     SELECT ticket_id
     INTO l_ticket_id
     FROM canon_e193_cs_lines
     WHERE line_id = l_source_object_id
     ;
     l_error :='Unexpected error occured while creating notes for line' || l_source_object_id ||' error -'|| SUBSTR(SQLERRM, 2000);
     debug_msg( l_program_name  => 'canon_e193_cs_create_notes',
                         l_attribute1 => 'Ticket# ' || l_ticket_id,
                        l_attribute2 => 'Line# ' || l_source_object_id,
                        l_attribute5 => 'OTHERS',
                        l_error_msg => l_error
                      );
END canon_e193_cs_create_notes;

------------------------------------------------------------------------------
-- Secure Delete note
--   Delete_Note will only work when the user is granted the
--   JTF_NOTE_DELETE privilege through AOL security framework
------------------------------------------------------------------------------
PROCEDURE canon_e193_cs_delete_notes(l_note_id IN NUMBER) IS
    l_error VARCHAR2(2000) := NULL;
BEGIN
        /*Jtf_Notes_Pub.secure_delete_note@CANDEV(p_api_version => 1.0,
            p_jtf_note_id   => l_note_id,
            x_return_status => l_return_status,
            x_msg_count => l_msg_count,
            x_msg_data => l_msg_data);*/
            
            DELETE FROM canon_e193_notes_tl
            where jtf_note_id=l_note_id;
            COMMIT;
            
            DELETE FROM canon_e193_notes_b
            where jtf_note_id=l_note_id;
            COMMIT;
                        

EXCEPTION 
WHEN OTHERS THEN
     l_error := 'Unexpected error occured while deleting note' || l_note_id ||' error -'||  SUBSTR(SQLERRM, 2000);
     debug_msg( l_program_name  => 'canon_e193_cs_delete_notes',
                         l_error_msg  => l_error 
                      );
END canon_e193_cs_delete_notes;

------------------------------------------------------------------------------
-- Update_note
--   Updates a note record in the JTF_NOTES_B, JTF_NOTES_TL
--   and JTF_NOTE_CONTEXTS table
------------------------------------------------------------------------------
PROCEDURE CANON_E193_CS_UPDATE_NOTES (l_source_object_id     IN     NUMBER,
                                      l_source_object_code   IN     VARCHAR2,
                                      l_note_id              IN     NUMBER,
                                      l_notes_detail         IN     VARCHAR2,
                                      l_note_status          IN     VARCHAR2,
                                      l_append_flag          IN     VARCHAR2,
                                      l_updated_by           IN     VARCHAR2,
                                      l_ret_status              OUT NUMBER)
IS
   l_jtf_note_id              NUMBER;
   l_last_update_date         DATE := SYSDATE;
   l_return_status            VARCHAR2 (1);
   l_msg_count                NUMBER;
   l_msg_data                 VARCHAR2 (2000);
   l_error                    VARCHAR2 (2000) := NULL;
   l_ticket_id                NUMBER;

   lv_notes_detail            VARCHAR2 (32767) := l_notes_detail;
   l_new_clob_length          NUMBER;
   l_old_clob_length          NUMBER;
   l_total_clob_length        NUMBER;
   l_notes_detail_truncated   VARCHAR2 (32767);
   lv_line_cnt		NUMBER:=0;
   l_notes  VARCHAR2(4000);
BEGIN
   IF (l_note_id IS NULL)
   THEN
      BEGIN
         SELECT jtf_note_id
           INTO l_jtf_note_id
           FROM canon_e193_notes_b jnb
          WHERE     jnb.source_object_id = l_source_object_id
                AND jnb.source_object_code = l_source_object_code;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            l_jtf_note_id := -1;
      END;
   ELSE
      l_jtf_note_id := l_note_id;
   END IF;
     
   --dbms_output.put_line('l_jtf_note_id = '||l_jtf_note_id);
  --To handle inconsistent data
       SELECT count(1) 
       INTO lv_line_cnt
       FROM canon_e193_notes_tl
       WHERE jtf_note_id=l_jtf_note_id;
       --dbms_output.put_line('lv_line_cnt = '||lv_line_cnt);
   IF lv_line_cnt>0
       THEN     
   IF l_notes_detail IS NOT NULL
   THEN
      lv_notes_detail := l_notes_detail;

      --dbms_output.put_line('lv_notes_detail = '||lv_notes_detail);
      IF l_append_flag = 'Y'
      THEN 
     
         writelobtodata (l_jtf_note_id, lv_notes_detail);
         --dbms_output.put_line('lv_notes_detail123 = '||lv_notes_detail);
         l_old_clob_length := LENGTHB (lv_notes_detail);
         l_new_clob_length := LENGTHB (l_notes_detail);
         l_total_clob_length := l_old_clob_length + l_new_clob_length;

         -- dbms_output.put_line('l_total_clob_length = '||l_total_clob_length);
         IF (l_total_clob_length > 32766) -- 32367 minus 1 since we'll append a space
         THEN
            -- we'll need to truncate before we append
            l_notes_detail_truncated :=
               SUBSTRB (lv_notes_detail, 1, (32766 - l_old_clob_length)); -- 32367


            IF l_notes_detail_truncated IS NOT NULL
            THEN
               lv_notes_detail :=
                  lv_notes_detail || ' ' || l_notes_detail_truncated;
            END IF;
         ELSE
            lv_notes_detail := l_notes_detail || ' ' || lv_notes_detail;
         END IF;
      --dbms_output.put_line('l_notes_detail = '||l_notes_detail);
      --- dbms_output.put_line('lv_notes_detail = '||lv_notes_detail);

      --lv_notes_detail := l_notes_detail||' '||lv_notes_detail;
      END IF;
   --dbms_output.put_line('lv_notes_detail = '||lv_notes_detail);
   END IF;

   IF l_jtf_note_id <> -1
   THEN
      /*Jtf_Notes_Pub.update_note@CANDEV(p_api_version => 1.0,
          p_jtf_note_id => l_jtf_note_id,
          p_notes_detail => l_notes_detail,
          p_append_flag => l_append_flag,
          p_note_status => l_note_status,
          p_last_updated_by => l_updated_by,
          p_last_update_date => l_last_update_date,
          x_return_status => l_return_status,
          x_msg_count => l_msg_count,
          x_ msg_data => l_msg_data);
  */
      UPDATE canon_e193_notes_tl
         SET notes_detail = EMPTY_CLOB (),           --l_notes_detail ||notes,
             last_updated_by = l_updated_by,
             last_update_date = SYSDATE
       WHERE jtf_note_id = l_jtf_note_id;

      writeDatatoLob (l_jtf_note_id, lv_notes_detail);
      COMMIT;

      UPDATE canon_e193_notes_b
         SET last_updated_by = l_updated_by, last_update_date = SYSDATE
       WHERE jtf_note_id = l_jtf_note_id;

      COMMIT;
      
   END IF;
         ELSE--IF lv_line_cnt<=0
         --dbms_output.put_line('Inside IF lv_line_cnt<=0');
         IF l_notes_detail IS NOT NULL
         THEN
         l_notes:=substr(l_notes_detail,1,3000);
         INSERT INTO CANON_E193_NOTES_TL
         VALUES(l_jtf_note_id,l_notes,EMPTY_CLOB(),'US','US',SYSDATE,l_updated_by,SYSDATE,l_updated_by,'-1','');
         COMMIT;
         writedatatolob(l_jtf_note_id,l_notes_detail);
         
   COMMIT;
      UPDATE canon_e193_notes_b
            SET last_updated_by = l_updated_by, last_update_date = SYSDATE
          WHERE jtf_note_id = l_jtf_note_id;
   
      COMMIT;
      END IF;
      END IF;
   
   
EXCEPTION
   WHEN OTHERS
   THEN
      l_error :=
            'Unexpected error occured while updating note'
         || l_note_id
         || ' for line '
         || l_source_object_id
         || ' error -'
         || SUBSTR (SQLERRM, 2000);

      SELECT ticket_id
        INTO l_ticket_id
        FROM canon_e193_cs_lines
       WHERE line_id = l_source_object_id;

      debug_msg (l_program_name   => 'canon_e193_cs_update_notes',
                 l_attribute1     => 'Ticket# ' || l_ticket_id,
                 l_attribute2     => 'Line# ' || l_source_object_id,
                 l_attribute5     => 'OTHERS',
                 l_error_msg      => l_error);
END CANON_E193_CS_UPDATE_NOTES;

PROCEDURE debug_msg ( 
   l_program_name IN VARCHAR2, 
   l_attribute1 IN VARCHAR2 DEFAULT NULL,
   l_attribute2 IN VARCHAR2 DEFAULT NULL,
   l_attribute3 IN VARCHAR2 DEFAULT NULL,
   l_attribute4 IN VARCHAR2 DEFAULT NULL,
   l_attribute5 IN VARCHAR2 DEFAULT NULL,
   l_error_msg  IN VARCHAR2
)
IS
BEGIN
    INSERT INTO 
       canon_e193_cs_errors  
    VALUES 
    (
       l_program_name,
       l_attribute1,
       l_attribute2,
       l_attribute3,
       l_attribute4,
       l_attribute5,
       l_error_msg, 
       SYSDATE
    );
--    COMMIT;
END debug_msg;

END CANON_E193_CS_JTF_NOTES_PKG;
/
