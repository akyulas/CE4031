DROP TABLE IF EXISTS Publications;

CREATE TABLE Publications(
	pubKey TEXT,
	mdate TEXT,
	title TEXT,
	category TEXT,
	journal TEXT,
	bookTitle TEXT,
	publishedYear TEXT
	
);


Drop Table IF EXISTS Author;

Create TABLE Author (
	name TEXT 
);

DROP TABLE IF EXISTS Authored;
Create TABLE Authored (
	publication_key TEXT,
	author_name TEXT
);

copy author(name) 
from 'C:\Users\Zhi En\Downloads\CE4031_csv\author.csv' 
csv header;


copy authored
from 'C:\Users\Zhi En\Downloads\CE4031_csv\authored.csv'
delimiter '`';

copy publications
from 'C:\Users\Zhi En\Downloads\CE4031_csv\publication.csv'
delimiter '`'

