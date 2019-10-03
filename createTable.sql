DROP TABLE IF EXISTS Publications cascade;

CREATE TABLE Publications(
	pubKey TEXT primary key,
	mdate TEXT,
	title TEXT,
	category TEXT,
	journal TEXT,
	bookTitle TEXT,
	publishedYear TEXT,
	publishedMonth TEXT,
	crossref TEXT
);

Drop Table IF EXISTS Author cascade;

Create TABLE Author (
	name TEXT primary key
);

DROP TABLE IF EXISTS authored cascade;
Create TABLE authored (
	author_name TEXT,
	publication_key TEXT
);

publications (pubKey);

copy author(name) 
from 'C:\Users\Zhi En\Downloads\CE4031_csv\author.csv';
delete from author where name = 'name';

copy authored
from 'C:\Users\Zhi En\Downloads\CE4031_csv\authored.csv'
delimiter '`';
delete from authored where author_name = 'author_name';

copy publications
from 'C:\Users\Zhi En\Downloads\CE4031_csv\publication.csv'
delimiter '`';
delete from publications where pubKey = 'pubKey';

ALTER TABLE authored 
ADD CONSTRAINT verify_authored FOREIGN KEY (author_name) REFERENCES author (name);

ALTER TABLE authored 
ADD CONSTRAINT verify_publication FOREIGN KEY (publication_key) REFERENCES 

--load halfed data
copy author(name) 
from 'C:\Users\Zhi En\Downloads\CE4031_csv\author_half.csv';
delete from author where name = 'name';

copy authored
from 'C:\Users\Zhi En\Downloads\CE4031_csv\authored_half.csv'
delimiter '`';
delete from authored where author_name = 'author_name';

copy publications
from 'C:\Users\Zhi En\Downloads\CE4031_csv\publication_half.csv'
delimiter '`';
delete from publications where pubKey = 'pubKey';

--load quartered data

copy author(name) 
from 'C:\Users\Zhi En\Downloads\CE4031_csv\author_quarter.csv';
delete from author where name = 'name';

copy authored
from 'C:\Users\Zhi En\Downloads\CE4031_csv\authored_quarter.csv'
delimiter '`';
delete from authored where author_name = 'author_name';

copy publications
from 'C:\Users\Zhi En\Downloads\CE4031_csv\publication_quarter.csv'
delimiter '`';
delete from publications where pubKey = 'pubKey';
