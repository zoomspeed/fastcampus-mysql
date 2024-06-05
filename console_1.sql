select count(*)
from POST;

select memberId, count(id)
from POST
group by memberId;

select createdDate, count(id)
from POST
group by createdDate
order by 2 desc;


select count(distinct createdDate)
from POST;

explain select count(distinct createdDate)
from POST use index (POST__index_member_id)
where 1 = 1
  and memberId = 5;


explain SELECT createdDate, memberId, count(id) as count
FROM POST
WHERE memberId = 3 and createdDate between '1900-01-01' and '2023-01-01'
GROUP BY createdDate, memberId;

explain SELECT createdDate, memberId, count(id) as count
        FROM POST use index (POST__index_member_id)
        WHERE memberId = 5 and createdDate between '1900-01-01' and '2023-01-01'
        GROUP BY createdDate, memberId;

explain SELECT count(id)
        FROM POST use index (POST__index_created_date)
        WHERE memberId = 5 and createdDate between '1900-01-01' and '2023-01-01'
        GROUP BY memberId, createdDate;

explain SELECT createdDate, memberId, count(id) as count
        FROM POST use index (POST__index_member_id_created_date)
        WHERE memberId = 5 and createdDate between '1900-01-01' and '2023-01-01'
        GROUP BY createdDate, memberId;


select *
from POST
where memberId = 4
# order by id desc
limit 2
offset 0;

select * from POST order by createdAt desc limit 1;

select * from Follow;

select * from Timeline;

SELECT *
FROM Post
WHERE memberId = 3
LIMIT 2
    OFFSET 0;

select *
from POST
where memberId = 4 and id > -1;

desc post;

select * from Follow;

select *
from Timeline;

select * from MEMBER;

select * from MemberNicknameHistory;

start transaction;
select * from POST where memberId = 4 and contents Like '%qlrs%' for update;
commit;

select * from performance_schema.data_locks;
select * from information_schema.INNODB_TRX;

#START TRANSACTION ;
#~~~
#COMMIT ;