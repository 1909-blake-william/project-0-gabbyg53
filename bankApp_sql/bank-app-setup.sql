
/*********************************
Tables and sequences
**********************************/

CREATE SEQUENCE bank_user_id_seq;
CREATE TABLE Bank_User (
   bank_user_id INT PRIMARY KEY,
   user_type_id VARCHAR2(10) NOT NULL,
   f_name VARCHAR2(20) NOT NULL,
   l_name VARCHAR2(30) NOT NULL,
   username VARCHAR2(30) UNIQUE NOT NULL,
   u_password VARCHAR2(30) NOT NULL,
   account_type VARCHAR2(20)
);
--ADD ACCT ID reff to user AFTER CREATING ACCOUNT TABLE

CREATE SEQUENCE bank_account_id_seq;
CREATE TABLE Bank_Account (
    bank_account_id INT PRIMARY KEY,
    account_type VARCHAR2(20),
    user_id INT REFERENCES Bank_User(bank_user_id),
    balance FLOAT,
    is_open INT NOT NULL
);

CREATE SEQUENCE account_transaction_id_seq;
CREATE TABLE Account_Transaction (
    transaction_id INT PRIMARY KEY,
    account_id INT REFERENCES Bank_Account(bank_account_id),
    user_trans_id INT REFERENCES Bank_User(bank_user_id),
    amount FLOAT,
    action VARCHAR2(20),
    --trans_date DATE NOT NULL
);

--ALTER TABLE bank_user
--ADD b_account_id INT REFERENCES Bank_Account(bank_account_id);
--don't need account id cuz ref integrity


ALTER TABLE Account_Transaction
DROP COLUMN trans_date;

--CASCADE CONSTRAINTS on drop tables

--commit;
