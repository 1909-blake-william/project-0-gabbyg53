/******************************************
Data
******************************************/


--Bank_User data
/*INSERT INTO Bank_User (bank_user_id, user_type_id, f_name, l_name,
                        username, u_password, account_type, b_account_id)
VALUES (BANK_USER_ID_SEQ.nextval, 'customer', 'Gabrielle', 'Griggs', 
                        'gabbyg53', 'password', 'Checking', NULL);
                        
INSERT INTO Bank_User (bank_user_id, user_type_id, f_name, l_name,
                        username, u_password, account_type, b_account_id)
VALUES (BANK_USER_ID_SEQ.nextval, 'admin', 'Gabrielle', 'Griggs', 
                        'ggriggs', 'p4ssw0rd', NULL, NULL);
                        
INSERT INTO Bank_User (bank_user_id, user_type_id, f_name, l_name,
                        username, u_password, account_type, b_account_id)
VALUES (BANK_USER_ID_SEQ.nextval, 'customer', 'Ciera', 'Love', 
                        'crlv', 'jinkies', 'Checking', NULL);
 
INSERT INTO Bank_User (bank_user_id, user_type_id, f_name, l_name,
                        username, u_password, account_type, b_account_id)
VALUES (BANK_USER_ID_SEQ.nextval, 'customer', 'Jenelle', 'Francis', 
                        'acsa', 'pres', 'Savings',NULL);*/
 

--Bank_Account data
/*INSERT INTO Bank_Account (bank_account_id, account_type, user_id, balance,
                        is_open)
VALUES (BANK_ACCOUNT_ID_SEQ.nextval, 'Checking', 1, 100.00, 1);

INSERT INTO Bank_Account (bank_account_id, account_type, user_id, balance,
                        is_open)
VALUES (BANK_ACCOUNT_ID_SEQ.nextval, 'Checking', 3, 69.00, 1);

INSERT INTO Bank_Account (bank_account_id, account_type, user_id, balance,
                        is_open)
VALUES (BANK_ACCOUNT_ID_SEQ.nextval, 'Checking', 4, 500.00, 1);*/

--Account_Transaction data
/*
INSERT INTO Account_Transaction (transaction_id, account_id, user_trans_id, amount,
                        action, trans_date)
VALUES (ACCOUNT_TRANSACTION_ID_SEQ.nextval, 1, 1, 50.00, 'deposit', 
to_date('20191025','YYYYMMDD'));

INSERT INTO Account_Transaction (transaction_id, account_id, user_trans_id, amount,
                        action, trans_date)
VALUES (ACCOUNT_TRANSACTION_ID_SEQ.nextval, 3, 4, 69.00, 'withdraw', 
to_date('20191025','YYYYMMDD'));

INSERT INTO Account_Transaction (transaction_id, account_id, user_trans_id, amount,
                        action, trans_date)
VALUES (ACCOUNT_TRANSACTION_ID_SEQ.nextval, 4, 3, 10.00, 'deposit', 
to_date('20191025','YYYYMMDD'));
*/

--commit;