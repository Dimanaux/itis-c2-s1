CREATE TABLE "user" (
      id       SERIAL             NOT NULL,
      username VARCHAR(16) UNIQUE NOT NULL,
      password VARCHAR(64)        NOT NULL,
      name     VARCHAR(20),
      CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE post (
      id        SERIAL    NOT NULL,
      authorId INTEGER   NOT NULL,
      text      TEXT      NOT NULL,
      datetime  TIMESTAMP NOT NULL,
      CONSTRAINT pk_post PRIMARY KEY (id),
      FOREIGN KEY (authorId) REFERENCES "user" (id)
);


CREATE TABLE post_comment (
      id        SERIAL    NOT NULL,
      authorId INTEGER   NOT NULL,
      postId   INTEGER   NOT NULL,
      text      TEXT      NOT NULL,
      datetime  TIMESTAMP NOT NULL,
      CONSTRAINT pk_post_comment PRIMARY KEY (id),
      CONSTRAINT comment_author_fk FOREIGN KEY (authorId) REFERENCES "user" (id),
      CONSTRAINT comment_post_fk FOREIGN KEY (postId) REFERENCES post (id)
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
      is_vegan    BIT,
      CONSTRAINT pk_dish PRIMARY KEY (id)
);

CREATE TABLE recipe (
      id           SERIAL    NOT NULL,
      dishId      INTEGER   NOT NULL,
      authorId    INTEGER   NOT NULL,
      text         TEXT      NOT NULL,
      publish_date TIMESTAMP NOT NULL,
      CONSTRAINT pk_recipe PRIMARY KEY (id),
      CONSTRAINT recipe_dish_fk FOREIGN KEY (dishId) REFERENCES dish (id),
      CONSTRAINT recipe_author_fk FOREIGN KEY (authorId) REFERENCES "user" (id)
);

CREATE TABLE recipe_comment (
      id        SERIAL    NOT NULL,
      authorId INTEGER   NOT NULL,
      recipeId INTEGER   NOT NULL,
      text      TEXT      NOT NULL,
      datetime  TIMESTAMP NOT NULL,
      CONSTRAINT pk_recipe_comment PRIMARY KEY (id),
      CONSTRAINT comment_author_fk FOREIGN KEY (authorId) REFERENCES "user" (id),
      CONSTRAINT comment_recipe_fk FOREIGN KEY (recipeId) REFERENCES recipe (id)
);

CREATE TABLE ingredient_recipe_relation (
      ingredient_id INTEGER NOT NULL,
      recipeId     INTEGER NOT NULL,
      CONSTRAINT unique_pair_ingredient_recipe UNIQUE (ingredient_id, recipeId),
      CONSTRAINT ingredient_fk FOREIGN KEY (ingredient_id) REFERENCES ingredient (id),
      CONSTRAINT recipe_fk FOREIGN KEY (recipeId) REFERENCES recipe (id)
);


