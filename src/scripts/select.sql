INSERT INTO "user" (username, password, name)
VALUES ('cosmo', 'cosmo123#hash', 'Dima Dmitry');

SELECT * FROM "user" WHERE id = ? LIMIT 1;

SELECT * FROM "user" WHERE username = ? LIMIT 1;

SELECT * FROM "user" u INNER JOIN post p on u.id = p.author_id WHERE u.id = ?;

SELECT * FROM "user" u INNER JOIN recipe r on u.id = r.author_id WHERE u.id = ? ;


INSERT INTO post (authorId, text, datetime)
VALUES (1, 'hello world', '2018-09-24 12:34:56');

INSERT INTO post_comment (authorId, postId, text, datetime)
VALUES (1, 1, 'this is my post!', '2018-09-24 12:34:57');

SELECT * FROM post_comment c INNER JOIN post p on c.post_id = p.id;

INSERT INTO ingredient (name)
VALUES ('Garlic');

INSERT INTO dish (name, description, is_vegan)
VALUES ('Boiled eggs', 'Eggs cooked by boiling', 0);

INSERT INTO recipe (dishId, authorId, text, publish_date)
VALUES (1, 1, 'Boil egg for 5 minutes.', '2018-09-24 12:35:40');

INSERT INTO ingredient_recipe_relation (ingredient_id, recipeId)
VALUES (1, 1);

INSERT INTO recipe_comment (authorId, recipeId, text, datetime)
VALUES (1, 1, 'Amazing! Thank you very much!', '2018-09-24 12:35:42');
