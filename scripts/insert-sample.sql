
INSERT INTO "user" (username, password, name)
VALUES ('cosmo', 'cosmo123#hash', 'Dima Dmitry');

INSERT INTO post (author_id, text, datetime)
VALUES (1, 'hello world', '2018-09-24 12:34:56');

INSERT INTO post_comment (author_id, post_id, text, datetime)
VALUES (1, 1, 'this is my post!', '2018-09-24 12:34:57');

INSERT INTO ingredient (name)
VALUES ('������');

INSERT INTO dish (name, description, is_vegan)
VALUES ('������ ������', '������ ������ �� �������� ������. ������ ������ �������� ��� � ������� ����...', 1);

INSERT INTO recipe (dish_id, author_id, text, publish_date)
VALUES (1, 1, '�������� ������ � ������� ����. ����� 5 �����.', '2018-09-24 12:35:40');

INSERT INTO ingredient_recipe_relation (ingredient_id, recipe_id)
VALUES (1, 1);

INSERT INTO recipe_comment (author_id, recipe_id, text, datetime)
VALUES (1, 1, '�������! ��� �� � ��� ��� �����?!', '2018-09-24 12:35:42');


