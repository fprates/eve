/* tokens '\c e \p nao sao aceitos. nao existe parametro 'if exists' para 'drop user' */
\c true
drop user evedb;
\c false
create user evedb password initial;
\p user generated.

drop table custmr003 if exists;
drop table custmr002 if exists;
drop table custmr001 if exists;
drop table sdcomm001 if exists;
drop table sdcomm002 if exists;
drop table sdcomm003 if exists;
drop table numrng if exists;
\p tables dropped.

/* range numérico */
create table numrng (
   range char(6) primary key,
   crrnt numeric(12)
);

/* mestre de clientes - dados básicos */
create table custmr001 (
   ident numeric(10) primary key,
   rname char(40),
   aname char(40),
   dtreg date,
   hrreg timestamp,
   usreg char(8),
   tpcdn numeric(1),
   nrcdn char(20),
   statu char(1)
);

/* mestre de clientes - contatos */
create table custmr002 (
   nrseq numeric(12) primary key,
   ident numeric(10) foreign key references custmr001(ident),
   rname char(40),
   funct char(20),
   teln1 numeric(12),
   teln2 numeric(12)
);

/* mestre de clientes - endereços */
create table custmr003 (
   nrseq numeric(12) primary key,
   ident numeric(10) foreign key references custmr001(ident)
);

/* unidades países */
create table sdcomm001 (
    cntry char(3) primary key
);

/* unidades federativas */
create table sdcomm002 (
    cntry char(3),
    coduf char(2),
    ufkey char(5),
    primary key(cntry, coduf)
);

/* municípios */
create table sdcomm003 (
    ufkey char(5),
    munic numeric(7),
    rname char(20),
    primary key(ufkey, munic)
);

\p tables generated.

grant select, insert, update, delete on custmr001 to evedb;
grant select, insert, update, delete on custmr002 to evedb;
grant select, insert, update, delete on custmr002 to evedb;
grant select, insert, update, delete on sdcomm001 to evedb;
grant select, insert, update, delete on sdcomm002 to evedb;
grant select, insert, update, delete on sdcomm003 to evedb;
grant select, insert, update, delete on numrng to evedb;
\p permissions granted.

insert into numrng (range, crrnt) values ('CUSTMR', 0);
\p initial configuration saved.

commit work;

