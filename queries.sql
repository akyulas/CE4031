--query 1
select category, count(*) 
from publications
where publishedyear between '2000' and '2018'
group by category

--query2 
create unique index bookTitle_index
on publications(bookTitle);

drop index bookTitle_index;

select distinct booktitle
from (
	select booktitle, publishedyear, count(*) as conf_count
	from publications
	where category = 'inproceedings'
	group by booktitle, publishedyear
) conf
where conf_count > 500

--query 3


SELECT concat(CAST(T.YearDivision * 10 AS nchar(4)),  N' - ', CAST(T.YearDivision * 10 + 9 AS nchar(4))) AS YearRange, SUM(T.TotalCount)
FROM
(
    SELECT cast (publishedyear as int) / 10 AS YearDivision, COUNT(*) AS TotalCount
    FROM publications
	where publishedyear <> 'null'
    GROUP BY publishedyear
) T
GROUP BY YearDivision

create index journal_index on publications (journal);

--query 4
create view co_count as(
select T3.author, T3.cnt from (
select author, count(*) as cnt from (
select A1.author_name as author, A2.author_name as co_author from authored A1 join (
select * from publications where (category = 'inproceedings'
and crossRef in (select pubkey from publications where category = 'proceedings'
and lower(title) like '%data%')) 
or (category = 'article' and lower(journal) like '%data%')
)T1 on A1.publication_key = T1.pubkey join authored A2 on A1.publication_key = A2.publication_key
and A1.author_name <> A2.author_name) T2 group by author) T3);

select author, cnt 
from co_count 
where cnt = (select max(cnt) from co_count);


--query 5
select *
from (
	select author_name, count(*) as pub_count
	from authored join (
	select * from publications where 
	(category = 'inproceedings'
		and crossRef in (select pubkey from publications where category = 'proceedings'
		and lower(title) like '%data%')) 
	or (category = 'article' and lower(journal) like '%data%') 
	) T1
	on T1.pubkey = authored.publication_key
	group by author_name
)T
order by pub_count DESC
LIMIT 10;

--query 6

select title from publications
where category = 'proceedings'
and lower(title) like '%data%'
and pubkey in (
select crossref from(
select crossref, count(*) as cnt from publications where category = 'inproceedings'
group by crossref) T1 where T1.cnt > 100);

--query 7a)
select author.name, authored.author_name
from (
	author join authored on author.name = authored.author_name
	join publications on authored.publication_key = publications.pubKey
)
where publications.publishedYear Between '1990' and '2019'
and substring(author.name, length(author.name)-strpos(reverse(author.name),' ')+2, length(author.name)) like 'H%'
group by authored.author_name, author.name
having count(distinct publications.publishedYear) = 30;

--query 7b)
select author.name, authored.author_name, count(*)
from author join authored
on author.name = authored.author_name
where author.name in (
	select distinct authored.author_name
	from authored join publications on authored.publication_key = publications.pubKey
	where publications.publishedyear = (select min(publishedyear) from publications)
)
group by author.name, authored.author_name

--query 8

create view inpro_count as(
	select author_name, publishedyear, count(*) as cnt from
	authored join publications on authored.publication_key = publications.pubKey
	where publications.category = 'inproceedings'
	group by author_name, publishedyear
);

select author_name, cnt
from inpro_count
where cnt = (
	select cnt
	from inpro_count
	order by cnt desc limit 1 offset 1
)





