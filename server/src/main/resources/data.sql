INSERT INTO category (name) VALUES ('Funny');
INSERT INTO category (name) VALUES ('Thoughtful');

INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id) VALUES ('Oh no! A trolley is heading towards 5 people. You can pull the lever to divert it to the other track, killing yourself instead. What do you do?', 0, 0, NULL, 'You', 1);
INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id) VALUES ('Oh no! A trolley is heading towards 5 people. You can pull the lever to divert it to the other track, killing 4 people instead. What do you do?', 0, 0, NULL, NULL, 1);
INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id) VALUES ('Oh no! A trolley is heading towards 0 people. You can pull the lever to divert it to the other track, killing 0 people instead. What do you do?', 0, 0, NULL, NULL, 1);

INSERT INTO victim (name, image_url) VALUES ('Person', 'https://trolley-problem-media.s3.eu-central-1.amazonaws.com/one-guy.svg');
INSERT INTO victim (name, image_url) VALUES ('Money', 'https://trolley-problem-media.s3.eu-central-1.amazonaws.com/money.svg');
INSERT INTO victim (name, image_url) VALUES ('Mona Lisa', 'https://trolley-problem-media.s3.eu-central-1.amazonaws.com/mona-lisa.svg');

INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);

INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);


INSERT INTO comment (text, problem_id, author) VALUES ('I would pull the lever', 1, 'Jane Doe');
INSERT INTO comment (text, problem_id, author) VALUES ('I would not pull the lever', 1, 'John Doe');
