drop table if exists  h578056.public.ansatt;
Create table  h578056.public.ansatt (ansattnr int not null,
            firstname varchar(255),
            lastname varchar(255), 
            ansettelsesdato date,
            stilling varchar(255),
            maanedslonn float,
            primary key(ansattnr));

