INSERT INTO Genre (name) VALUES ('Fiction');
INSERT INTO Genre (name) VALUES ('Thriller');

INSERT INTO USER_APLICATION (dtype, name, lastname, username, email, password, date_birth, image, description)
VALUES ('Admin','John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', '2000-01-01', 'profile.jpg', 'Description of John Doe');

INSERT INTO USER_APLICATION (dtype, name, lastname, username, email, password, date_birth, image, description, active)
VALUES ('Author', 'Bob', 'Smith', 'bobsmith', 'bobsmith@example.com', 'password789', '1980-10-10', 'baseImage.png', 'Avid reader and book collector', false);

INSERT INTO Review (grade, text, review_date, user_id) VALUES (4, 'Great book!', '2023-04-19', 1);
INSERT INTO Review (grade, text, review_date, user_id) VALUES (5, 'This book was just cool 10/10!', '2023-05-19', 1);

INSERT INTO Book (title, image, ISBN, date_published, pages, description, genre_id)
VALUES ('The Catcher in the Rye', 'catcher.jpg', '0316769487', '1951-07-16', 224, 'The story of Holden Caulfield', 1);
INSERT INTO Book (title, image, ISBN, date_published, pages, description, genre_id)
VALUES ('To Kill a Mockingbird', 'mockingbird.jpg', '0061120081', '1960-07-11', 336, 'The story of a lawyer in a racially divided Alabama town in the 1930s', 1);

INSERT INTO shelf_instance (review_id, book_id)
VALUES (1, 2);

INSERT INTO Shelf (name, is_primary) VALUES ('Favorites', true);

INSERT INTO Shelf (name, is_primary) VALUES ('Reading Now', false);

INSERT INTO Shelf (name, is_primary) VALUES ('To Read', false);

INSERT INTO Request (email, phone, message, date) VALUES ('example@example.com', '123-456-7890', 'Can I schedule an appointment?', '2023-04-19');
