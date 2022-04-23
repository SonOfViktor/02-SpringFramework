-- -----------------------------------------------------
-- Table `module_two`.`gift_certificate`
-- -----------------------------------------------------
INSERT INTO gift_certificate (name, description, price, duration)
VALUES ('Oz.by', 'Books, games, stationery', 20, 40),
       ('Belvest', 'Change shoes', 50, 30),
       ('Sportmaster', 'Summer soon, time to buy ski', 50, 20),
       ('Evroopt', 'We have a lot of sugar!', 40, 10),
       ('Evroopt', 'Buy two bananas', 20, 10);

-- -----------------------------------------------------
-- Table `module_two`.`tag`
-- -----------------------------------------------------
INSERT INTO tag (name)
values ('food'), ('stationery'), ('shoe'), ('virtual'), ('paper'), ('by');

-- -----------------------------------------------------
-- Table `module_two`.`gift_certificate_tag`
-- -----------------------------------------------------
INSERT INTO gift_certificate_tag (gct_gift_certificate_id, gct_tag_id)
VALUES (1, 2), (1, 4), (1, 6), (2, 3), (2, 5), (2, 6), (3, 3), (3, 4),
       (4, 1), (4, 2), (4, 5), (4, 6), (5, 1), (5, 2), (5, 5), (5, 6);