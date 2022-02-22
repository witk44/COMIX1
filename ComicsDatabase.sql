CREATE TABLE IF NOT EXISTS comix (
    id SERIAL,
    series VARCHAR(255),
    issue VARCHAR(10),
    full_title VARCHAR(255),
    variant VARCHAR(255),
    publisher VARCHAR(100),
    release_date VARCHAR(30),
    format VARCHAR(30),
    added_date VARCHAR(30),
    creators VARCHAR(255)
);

COPY comix(series, issue, full_title, variant, publisher, release_date, format, added_date, creators)
FROM 'comics.csv'
DELIMITER ','
CSV HEADER;