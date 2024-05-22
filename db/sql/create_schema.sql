CREATE TABLE customer (
                          dni varchar(255) NOT NULL,
                          lastname varchar(255) DEFAULT NULL,
                          name varchar(255) DEFAULT NULL,
                          phone_number varchar(255) DEFAULT NULL,
                          total_credit_card int DEFAULT NULL,
                          PRIMARY KEY ("dni"));

CREATE TABLE creditcard (
                            product_id bigint NOT NULL ,
                            enable boolean DEFAULT NULL,
                            quota_creditcard decimal(19,2) DEFAULT NULL,
                            fecha_creacion date DEFAULT NULL,
                            expiry_date varchar(255) DEFAULT NULL,
                            id_card_activation varchar(255) DEFAULT NULL,
                            type_credit_card varchar(255) DEFAULT NULL,
                            id_card_customer varchar(255) DEFAULT NULL,
                            PRIMARY KEY (product_id),
                            CONSTRAINT fk_id_card_customer  FOREIGN KEY (id_card_customer) REFERENCES customer (dni));



CREATE TABLE transaction (
                             transaction_id bigint NOT NULL ,
                             fecha_creacion timestamp DEFAULT NULL,
                             description_transaction varchar(255) DEFAULT NULL,
                             state_transaccion varchar(255) DEFAULT NULL,
                             type_transaction varchar(255) DEFAULT NULL,
                             value_transaction decimal(19,2) DEFAULT NULL,
                             id_credit_card bigint DEFAULT NULL,
                             id_customer varchar(255) DEFAULT NULL,
                             PRIMARY KEY (transaction_id));