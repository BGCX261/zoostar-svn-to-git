insert into Client(email, password, firstName, lastName, dateOfBirth, updatedTs, createdTs)
values('test@zoostar.net', 'password', 'First', 'Last', CURDATE(), NOW(), NOW());

insert into Activity(clientId, symbol, activityDate, quantity, amount)
values((select clientId from Client where email = 'test@zoostar.net'), 'CA$H', NOW(), 1, 100.123456);

insert into Balance(clientId, symbol, quantity, amount, updatedTs)
values((select clientId from Client where email = 'test@zoostar.net'), 'CA$H', 1, 100.123456, NOW());

insert into Activity(clientId, symbol, activityDate, quantity, amount)
values((select clientId from Client where email = 'test@zoostar.net'), 'CA$H2', NOW(), 1, 100.123456);
commit;

select * from Client;
select * from Activity order by activityTs;
select * from Balance order by updatedTs;

truncate table Balance;
commit;
truncate table Activity;
commit;
truncate table Client;
commit;