insert into user (id, login, password, email, city, phone) values (1, 'john', 'secret', 'john@example.org', 'London', '+44213213213');
insert into user (id, login, password, email, city, phone) values (2, 'jane', 'secret', 'jane@example.org', 'London', '+44213213214');
insert into room (id, tittle, city, description, prize, address, owner) values (1, 'Best ever', 'London', 'Best ever', 65, 'Noway, 71', 1);
insert into room (id, tittle, city, description, prize, address, owner) values (2, 'Best ever++', 'London', 'Best ever++', 70, 'Noway, 70', 2);
insert into rented (id, nigths, stat_date, total_prize, room, renter) values (1, 3, CURRENT_TIMESTAMP(), 210, 1, 1);
insert into comment (id, comment, room, author) values (1, 'The best', 2, 1);
insert into comment (id, comment, room, author) values (2, 'The best++', 1, 2);