INSERT INTO Genre (name) VALUES ('Fiction');

INSERT INTO Book (title, image, ISBN, date_published, pages, description, genre_id, review)
VALUES ('The Catcher in the Rye', 'catcher.jpg', '0316769487', '1951-07-16', 224, 'The story of Holden Caulfield', 1, 'A classic novel that everyone should read at least once.');
INSERT INTO Book (title, image, ISBN, date_published, pages, description, genre_id, review)
VALUES ('To Kill a Mockingbird', 'mockingbird.jpg', '0061120081', '1960-07-11', 336, 'The story of a lawyer in a racially divided Alabama town in the 1930s', 1, 'A Pulitzer Prize-winning classic that explores the themes of racism and injustice.');

INSERT INTO Admin (name, lastname, username, email, password, dateBirth, image, description)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', '2000-01-01', 'profile.jpg', 'Description of John Doe');

INSERT INTO Author (id, name, lastname, username, email, password, dateBirth, image, description, active)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', '2000-01-01', 'profile.jpg', 'Description of John Doe', true);
