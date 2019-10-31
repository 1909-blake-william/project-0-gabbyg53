--SELECT * FROM Bank_User;

SELECT * FROM Bank_Account left join
Bank_User ON (Bank_Account.user_id = Bank_User.bank_user_id);

select * from Bank_Account;

Select * from Account_Transaction;

--Select * from Account_Transaction
--Where user_trans_id = 1;

Select * from Account_Transaction left join
Bank_User on (Account_Transaction.user_trans_id = Bank_User.bank_user_id)
Where user_trans_id = 1;

--SELECT * FROM Account_Transaction 
--LEFT JOIN Bank_Account ON (Account_Transaction.account_id = Bank_Account.bank_account_id) 
--WHERE account_id = 4;

--Update Account_Transaction
--Set transaction_id = 9
--Where transaction_id = 22;
--select * from Bank_Account;
--
--UPDATE Bank_Account
--SET balance = 0, is_open = 0
--WHERE bank_account_id = 21;
--
--select * from Bank_Account;

--Select * from Account_Transaction;

--Select * from Bank_Account left join Bank_User on (Bank_Account.user_id = bank_user.bank_user_id);-- Where username = 'crlv';
--
--INSERT INTO Bank_Account (bank_account_id, account_type, user_id, balance, is_open)
--VALUES (BANK_ACCOUNT_ID_SEQ.nextval, 'savings', 1, 100.00, 1);

--truncate table Bank_Account; --bank_account_id, account_type, user_id, balance, is_open;

--DROP TABLE Bank_User CASCADE CONSTRAINTS;
--Drop table Bank_User;

/*UPDATE Bank_User
SET b_account_id = 1
WHERE bank_user_id = 1;

UPDATE Bank_User
SET b_account_id = 3
WHERE bank_user_id = 4;

UPDATE Bank_User
SET b_account_id = 4
WHERE bank_user_id = 3;*/

--ALTER TABLE Account_Transaction
--DROP COLUMN trans_date;
/*
Select * from Account_Transaction left join 
Bank_Account ON (Account_Transaction.account_id = Bank_Account.user_id)
Where account_id = 1;*/


--Select * from Account_Transaction left join 
--Bank_User ON (Account_Transaction.account_id = Bank_User.b_account_id);
--Where user_trans_id = 1;
--
--Select * from Bank_User left join 
--Bank_Account ON (Bank_User.bank_user_id = Bank_Account.user_id)
--Where Bank_User.bank_user_id = 1;
--
--select * from Account_Transaction;

--Select * from Bank_Account left join
--Bank_User on (Bank_Account.user_id = Bank_User.bank_user_id)
--Where Bank_User.username = 'crlv';

--ALTER TABLE Bank_User
--DROP COLUMN b_account_id;

SELECT * FROM Account_Transaction
LEFT JOIN Bank_User ON (Account_Transaction.user_trans_id = Bank_User.bank_user_id)
WHERE user_trans_id = 1;


