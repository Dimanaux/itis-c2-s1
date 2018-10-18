INSERT INTO "user" (username, password, name)
VALUES ('cosmo', 'cosmo123#hash', 'Dima Dmitry');

INSERT INTO post (authorId, text, datetime)
VALUES (1, 'hello world', '2018-09-24 12:34:56');

INSERT INTO post_comment (authorId, postId, text, datetime)
VALUES (1, 1, 'this is my post!', '2018-09-24 12:34:57');

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
