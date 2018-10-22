CREATE TABLE "user" (
  id       SERIAL      NOT NULL,
  username VARCHAR(16) NOT NULL,
  password VARCHAR(128) NOT NULL,
  name     VARCHAR(20) DEFAULT NULL,
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT unique_username UNIQUE (username)
);

CREATE TABLE post (
  id           SERIAL      NOT NULL,
  title        VARCHAR(40) NOT NULL,
  author_id    INTEGER     NOT NULL,
  text         TEXT        NOT NULL,
  publish_date TIMESTAMP   NOT NULL DEFAULT now(),
  CONSTRAINT pk_post PRIMARY KEY (id),
  FOREIGN KEY (author_id) REFERENCES "user" (id)
);

CREATE TABLE post_comment (
  id           SERIAL    NOT NULL,
  author_id    INTEGER   NOT NULL,
  post_id      INTEGER   NOT NULL,
  text         TEXT      NOT NULL,
  publish_date TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT pk_post_comment PRIMARY KEY (id),
  CONSTRAINT comment_author_fk FOREIGN KEY (author_id) REFERENCES "user" (id),
  CONSTRAINT comment_post_fk FOREIGN KEY (post_id) REFERENCES post (id)
);

CREATE TABLE ingredient (
  id   SERIAL      NOT NULL,
  name VARCHAR(16) NOT NULL,
  CONSTRAINT pk_ingredient PRIMARY KEY (id),
  CONSTRAINT ingredient_unique_name UNIQUE (name)
);

CREATE TABLE dish (
  id          SERIAL      NOT NULL,
  name        VARCHAR(40) NOT NULL,
  description TEXT        NOT NULL,
  is_vegan    BOOLEAN DEFAULT FALSE,
  CONSTRAINT pk_dish PRIMARY KEY (id)
);

CREATE TABLE recipe (
  id           SERIAL      NOT NULL,
  title        VARCHAR(40) NOT NULL,
  dishId       INTEGER     NOT NULL,
  authorId     INTEGER     NOT NULL,
  text         TEXT        NOT NULL,
  publish_date TIMESTAMP   NOT NULL DEFAULT now(),
  CONSTRAINT pk_recipe PRIMARY KEY (id),
  CONSTRAINT recipe_dish_fk FOREIGN KEY (dishId) REFERENCES dish (id),
  CONSTRAINT recipe_author_fk FOREIGN KEY (authorId) REFERENCES "user" (id)
);

CREATE TABLE recipe_comment (
  id           SERIAL    NOT NULL,
  author_id    INTEGER   NOT NULL,
  recipe_id    INTEGER   NOT NULL,
  text         TEXT      NOT NULL,
  publish_date TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT pk_recipe_comment PRIMARY KEY (id),
  CONSTRAINT comment_author_fk FOREIGN KEY (author_id) REFERENCES "user" (id),
  CONSTRAINT comment_recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id)
);

CREATE TABLE ingredient_recipe_relation (
  ingredient_id INTEGER NOT NULL,
  recipe_id     INTEGER NOT NULL,
  CONSTRAINT unique_pair_ingredient_recipe UNIQUE (ingredient_id, recipe_id),
  CONSTRAINT ingredient_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient (id),
  CONSTRAINT recipe_fk FOREIGN KEY (recipe_id) REFERENCES recipe (id)
);

CREATE TABLE user_token_relation (
  user_id INTEGER      NOT NULL,
  token   VARCHAR(128) NOT NULL,
  CONSTRAINT user_token_relation_user_fk FOREIGN KEY (user_id) REFERENCES "user" (id),
  CONSTRAINT unique_pair_user_token UNIQUE (user_id)
);
