DROP TABLE IF EXISTS Publications;

CREATE TABLE Publications(
	pubKey TEXT primary key,
	mdate TEXT,
	title TEXT,
	category TEXT,
	journal TEXT,
	bookTitle TEXT,
	publishedYear TEXT,
	publishedMonth TEXT
	
);


Drop Table IF EXISTS Author;

Create TABLE Author (
	name TEXT primary key
);

DROP TABLE IF EXISTS authored;
Create TABLE authored (
	author_name TEXT,
	publication_key TEXT
);

copy author(name) 
from 'C:\Users\Zhi En\Downloads\CE4031_csv\author.csv'
csv header; 

copy authored
from 'C:\Users\Zhi En\Downloads\CE4031_csv\authored.csv'
delimiter '`';
delete from authored where author_name = 'author_name';

copy publications
from 'C:\Users\Zhi En\Downloads\CE4031_csv\publication.csv'
delimiter '`';
delete from publications where pubKey = 'pubKey';

