CREATE TABLE ITEM(
	item_code VARCHAR(5) NOT NULL,
	item_type VARCHAR(10) NOT NULL,
	item_value VARCHAR(20),
	comment VARCHAR(100),
	PRIMARY KEY(item_code,item_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE PARTY(
	party_id VARCHAR(5) NOT NULL,
	party_date VARCHAR(10) NOT NULL,
	start_time VARCHAR(5) NOT NULL,
	end_time VARCHAR(5) NOT NULL,
	user_name VARCHAR(15),
  tel_no VARCHAR(15),
	email_no VARCHAR(20),
	resion_id VARCHAR(5),
	comment VARCHAR(223),
	flag VARCHAR(2),
	create_date TIMESTAMP,
	PRIMARY KEY(party_id,party_date,start_time,end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

view plaincopy
set character_set_client=gbk;
set character_set_connection=gbk;
set character_set_database=utf8;
set character_set_server=utf8;
