INSERT INTO Genre (name) VALUES ('Fiction');

INSERT INTO Admin (name, lastname, username, email, password, dateBirth, image, description)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', '2000-01-01', 'profile.jpg', 'Description of John Doe');

INSERT INTO Author (id, name, lastname, username, email, password, dateBirth, image, description, active)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', '2000-01-01', 'profile.jpg', 'Description of John Doe', true);

INSERT INTO Review (grade, text, review_date, user_id) VALUES (4, 'Great book!', '2023-04-19', 1);

INSERT INTO Book (title, image, ISBN, date_published, pages, description, genre_id, review_id)
VALUES ('The Catcher in the Rye', 'catcher.jpg', '0316769487', '1951-07-16', 224, 'The story of Holden Caulfield', 1, 1);
INSERT INTO Book (title, image, ISBN, date_published, pages, description, genre_id, review_id)
VALUES ('To Kill a Mockingbird', 'mockingbird.jpg', '0061120081', '1960-07-11', 336, 'The story of a lawyer in a racially divided Alabama town in the 1930s', 1, 1);

INSERT INTO Shelf (name, primary, shelfInstance_id) VALUES ('Bookshelf', true, 1);

INSERT INTO Review (grade, text, review_date, user_id) VALUES (4, 'Great book!', '2023-04-19', 1);

INSERT INTO Request (email, phone, message, date, status) VALUES ('example@example.com', '123-456-7890', 'Can I schedule an appointment?', '2023-04-19', 'WAITING');
