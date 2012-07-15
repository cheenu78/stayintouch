declare 
v_count NUMBER;

user_table_sql_stmt varchar2 (200) := 'INSERT INTO USER_TABLE VALUES (:a, :b, :c)';
user_details_sql_stmt varchar2 (200) := 'INSERT INTO USER_DETAILS VALUES (:a, :b, :c, :d, :e, :f)';
roles_table_sql_stmt varchar2 (200) := 'INSERT INTO USER_ROLES VALUES (:a, :b)';
USER_ROLE_MAPPING_sql_stmt varchar2 (200) := 'INSERT INTO USER_ROLE_MAPPING VALUES (:a, :b, :c)';

BEGIN
 	select count(*) INTO v_count from USER_tables where table_name='USER_DETAILS';
 	IF v_count>0 THEN
    	execute immediate 'DROP TABLE USER_DETAILS';
  	END IF;
  	
  	select count(*) INTO v_count from USER_tables where table_name='USER_ROLE_MAPPING';
 	IF v_count>0 THEN
    	execute immediate 'DROP TABLE USER_ROLE_MAPPING';
  	END IF;
  	
  	select count(*) INTO v_count from USER_tables where table_name='USER_ROLES';
 	IF v_count>0 THEN
    	execute immediate 'DROP TABLE USER_ROLES';
  	END IF;
  	
  	select count(*) INTO v_count from USER_tables where table_name='USER_TABLE';
 	IF v_count>0 THEN
    	execute immediate 'DROP TABLE USER_TABLE';
  	END IF;
  	
  	select count(*) INTO v_count from USER_tables where table_name='PERSISTENT_LOGINS';
 	IF v_count>0 THEN
    	execute immediate 'DROP TABLE PERSISTENT_LOGINS';
  	END IF;
  	
  	select count(*) INTO v_count from USER_sequences where sequence_name='USER_TABLE_SEQ';
 	IF v_count>0 THEN
    	execute immediate 'DROP sequence USER_TABLE_SEQ';
  	END IF;
  	
  	select count(*) INTO v_count from USER_sequences where sequence_name='USER_DETAILS_SEQ';
 	IF v_count>0 THEN
    	execute immediate 'DROP sequence USER_DETAILS_SEQ';
  	END IF;
  	
  	select count(*) INTO v_count from USER_sequences where sequence_name='USER_ROLE_MAPPING_SEQ';
 	IF v_count>0 THEN
    	execute immediate 'DROP sequence USER_ROLE_MAPPING_SEQ';
  	END IF;

execute immediate 
'CREATE TABLE USER_TABLE
(
  	ID       NUMBER(*,0) NOT NULL ENABLE PRIMARY KEY,
    EMAIL    VARCHAR2(45 BYTE) UNIQUE,
    PASSWORD VARCHAR2(45 BYTE) DEFAULT NULL
)';

execute immediate

'CREATE SEQUENCE USER_TABLE_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3 CACHE 20 NOORDER NOCYCLE';

execute immediate
'CREATE TABLE USER_DETAILS
(
    ID            NUMBER(*,0) NOT NULL ENABLE PRIMARY KEY,
    USER_TABLE_ID NUMBER(*,0) NOT NULL ENABLE,
    FIRST_NAME    VARCHAR2(45 BYTE) DEFAULT NULL,
    LAST_NAME     VARCHAR2(45 BYTE) DEFAULT NULL,
    BIRTHDAY DATE DEFAULT NULL,
    GENDER CHAR(1 BYTE) DEFAULT NULL,
    CONSTRAINT FK_USER_TABLE FOREIGN KEY (USER_TABLE_ID) REFERENCES USER_TABLE (ID) ENABLE
)';

execute immediate
'CREATE SEQUENCE USER_DETAILS_SEQ MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3 CACHE 20 NOORDER NOCYCLE';

execute immediate
'CREATE TABLE PERSISTENT_LOGINS
(
    USERNAME VARCHAR2(64 BYTE) NOT NULL ENABLE PRIMARY KEY,
    SERIES   VARCHAR2(64 BYTE),
    TOKEN    VARCHAR2(64 BYTE) NOT NULL ENABLE,
    LAST_USED TIMESTAMP (6) NOT NULL ENABLE
)';

execute immediate
'CREATE TABLE USER_ROLES
(
	ROLE_ID INT PRIMARY KEY,
	ROLE_NAME VARCHAR2(40) UNIQUE
)';

execute immediate
'CREATE TABLE USER_ROLE_MAPPING
(
	ID NUMBER PRIMARY KEY,
	USER_TABLE_ID NUMBER NOT NULL,
	USER_ROLE_MAPPING_ID NUMBER NOT NULL,
	CONSTRAINT FK_USER_ROLE_MAPPING_1 FOREIGN KEY (USER_TABLE_ID) REFERENCES USER_TABLE (ID) ENABLE,
	CONSTRAINT FK_USER_ROLE_MAPPING_2 FOREIGN KEY (USER_ROLE_MAPPING_ID) REFERENCES USER_ROLES (ROLE_ID) ENABLE
)';

EXECUTE IMMEDIATE 'CREATE SEQUENCE USER_ROLE_MAPPING_SEQ START WITH 5';

execute immediate user_table_sql_stmt using 1, 'cheenu78@gmail.com', 'abcd123';
execute immediate user_table_sql_stmt using 2, 'gireesh.nemath@gmail.com', 'abcd123';

execute immediate user_details_sql_stmt using 1, 1, 'Srinivasa Gopalan', 'Parthasarathi', sysdate, 1;
execute immediate user_details_sql_stmt using 2, 2, 'Gireesh', 'Nemath', sysdate, 1;

execute immediate roles_table_sql_stmt using 1, 'ADMINISTRATOR';
execute immediate roles_table_sql_stmt using 2, 'ROLE_USER';

execute immediate USER_ROLE_MAPPING_sql_stmt using 1, 1, 1;
execute immediate USER_ROLE_MAPPING_sql_stmt using 2, 2, 2;
execute immediate USER_ROLE_MAPPING_sql_stmt using 3, 2, 1;
execute immediate USER_ROLE_MAPPING_sql_stmt using 4, 2, 2;

execute immediate 'commit';

end;