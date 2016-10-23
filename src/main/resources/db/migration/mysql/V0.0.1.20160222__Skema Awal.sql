-- tabel Product --
create table product (
    id varchar(36) primary key,
    kode_produk varchar(10) not null unique,
    nama_produk varchar(255) not null,
    harga decimal(19,2) not null
    terakhir_update datetime NOT NULL,
) Engine=InnoDB;