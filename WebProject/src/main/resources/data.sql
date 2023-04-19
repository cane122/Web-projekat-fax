INSERT INTO Genre (name) VALUES ('Fiction');

INSERT INTO Book (title, ISBN, image, datePublished, pages, description, genre) VALUES ('Jungs Cognitive Functions', '1240740921', "/img.png", "20-20-1860",200,"realy good book","psyhology");
INSERT INTO Book (title, image, ISBN, date_published, pages, description, genre_id, review)
VALUES ('The Catcher in the Rye', 'catcher.jpg', '0316769487', '1951-07-16', 224, 'The story of Holden Caulfield', 1, 'A classic novel that everyone should read at least once.');
INSERT INTO Book (title, image, ISBN, date_published, pages, description, genre_id, review)
VALUES ('To Kill a Mockingbird', 'mockingbird.jpg', '0061120081', '1960-07-11', 336, 'The story of a lawyer in a racially divided Alabama town in the 1930s', 1, 'A Pulitzer Prize-winning classic that explores the themes of racism and injustice.');

INSERT INTO shelf (name, primary, shelf_instance_id)
VALUES ('My Shelf', true, 1);

INSERT INTO user (name, lastname, username, email, password, dateB, image, description, role, active, list)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123', '1990-01-01', 'image.jpg', 'Description of John Doe', 'READER', true, 'READER');

INSERT INTO user (name, lastname, username, email, password, dateB, image, description, role, active, list)
VALUES ('Jane', 'Doe', 'janedoe', 'janedoe@example.com', 'password456', '1995-02-14', 'image2.jpg', 'Description of Jane Doe', 'AUTHOR', true, 'READER, AUTHOR');

INSERT INTO user (name, lastname, username, email, password, dateB, image, description, role, active, list)
VALUES ('Admin', '', 'admin', 'admin@example.com', 'password789', '1985-05-12', 'admin.jpg', 'Description of admin', 'ADMIN', true, 'READER, AUTHOR, ADMIN');

INSERT INTO Review (grade, text, review_date, user_id) VALUES
                                                           (4, 'This book was amazing!', '2022-01-01', 1),
                                                           (3, 'It was okay, not my favorite.', '2022-02-15', 2),
                                                           (5, 'I couldn''t put this book down!', '2022-03-20', 3),
                                                            (2, 'I was disappointed with this book.', '2022-04-05', 4),
                                                            (4, 'A great read for anyone interested in the topic.', '2022-05-10', 5);


INSERT INTO Request (email, phone, message, date, status)
VALUES ('john@example.com', '123-456-7890', 'I would like to request more information about your services.', '2022-01-01', 'WAITING');
