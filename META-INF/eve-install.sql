/* tokens '\c e \p nao sao aceitos. nao existe parametro 'if exists' para 'drop user' */
\c true
drop user evedb;
\c false
create user evedb password initial;
\p user generated.

drop table custmr003 if exists;
drop table custmr002 if exists;
drop table custmr001 if exists;
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
   ident numeric(10),
   nrseq numeric(2),
   primary key(ident, nrseq)
);

/* mestre de clientes - endereços */
create table custmr003 (
   ident numeric(10),
   nrseq numeric(2),
   primary key(ident, nrseq)
);
\p tables generated.

grant select, insert, update, delete on custmr001 to evedb;
grant select, insert, update, delete on custmr002 to evedb;
grant select, insert, update, delete on custmr002 to evedb;
grant select, insert, update, delete on numrng to evedb;
\p permissions granted.

insert into numrng (range, crrnt) values ('CUSTMR', 0);
\p initial configuration saved.

commit work;

