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