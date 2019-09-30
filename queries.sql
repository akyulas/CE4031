--query 1
select category, count(*) 
from publications
where publishedyear between '2000' and '2018'
group by category

--query2 
select distinct booktitle
from (
	select booktitle, publishedyear, count(*) as conf_count
	from publications
	where type = 'inproceedings'
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

--query 4
--todo


--query 5
select *
from (
	select author_name,count(*) as pub_count
	from authored inner join author on authored.author_name = author.name 
	inner join publications on publications.pubkey = authored.publication_key
	group by author_name
)T
order by pub_count DESC
LIMIT 10;

--query 6


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
create view temp1 as (select author_name, booktitle, count(*) as number_of_times_published from authored join publications
on authored.publication_key = publications.pubkey
where publications.category = 'inproceedings'
group by authored.author_name,publications.booktitle);

select author_name from(select booktitle, max(number_of_times_published) as max_count 
from temp1
group by booktitle) T1 join temp1 on temp1.number_of_times_published = T1.max_count;





