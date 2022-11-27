INSERT INTO "user" ("first_name", "last_name", "email", "password", "user_name") VALUES ('Swati', 'Chauhan', 'swatichauhan3112@gmail.com', '$2a$10$HP.zXTqNaxBvQvO0UItq4.eYiR0AM.TipKsJpm376l.imKgfkFy2m','swati3112');
INSERT INTO "user" ("first_name", "last_name", "email", "password", "user_name") VALUES ('Lovely', 'Singh', 'lovely606@gmail.com', '$2a$10$HP.zXTqNaxBvQvO0UItq4.eYiR0AM.TipKsJpm376l.imKgfkFy2m', 'lovely606');

INSERT INTO "roles" ("name") VALUES('ROLE_ADMIN');
INSERT INTO "roles" ("name") VALUES('ROLE_INSTRUCTOR');
INSERT INTO "roles" ("name") VALUES('ROLE_STUDENT');
INSERT INTO "user_roles" VALUES (1, 1);
INSERT INTO "user_roles" VALUES (1, 2);
INSERT INTO "user_roles" VALUES (1, 3);
INSERT INTO "user_roles" VALUES (2, 3);
