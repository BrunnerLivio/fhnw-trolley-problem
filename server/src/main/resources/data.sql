INSERT INTO category (name) VALUES ('Funny');
INSERT INTO category (name) VALUES ('Thoughtful');
INSERT INTO category (name) VALUES ('Ethical');
INSERT INTO category (name) VALUES ('Unethical');
INSERT INTO category (name) VALUES ('Political');
INSERT INTO category (name) VALUES ('Economic');


INSERT INTO victim (name, image_url) VALUES ('Person', 'https://d3rjk7x0pvg6e3.cloudfront.net/one-guy.svg');
INSERT INTO victim (name, image_url) VALUES ('Money', 'https://d3rjk7x0pvg6e3.cloudfront.net/money.svg');
INSERT INTO victim (name, image_url) VALUES ('Mona Lisa', 'https://d3rjk7x0pvg6e3.cloudfront.net/mona-lisa.svg');
INSERT INTO victim (name, image_url) VALUES ('Rich Guy', 'https://d3rjk7x0pvg6e3.cloudfront.net/rich-guy.svg');
INSERT INTO victim (name, image_url) VALUES ('Baby', 'https://d3rjk7x0pvg6e3.cloudfront.net/one-baby.svg');
INSERT INTO victim (name, image_url) VALUES ('Trolley', 'https://d3rjk7x0pvg6e3.cloudfront.net/one-trolley.svg');
INSERT INTO victim (name, image_url) VALUES ('Cat', 'https://d3rjk7x0pvg6e3.cloudfront.net/cat.svg');
INSERT INTO victim (name, image_url) VALUES ('Brick Wall', 'https://d3rjk7x0pvg6e3.cloudfront.net/brick-wall.svg');
INSERT INTO victim (name, image_url) VALUES ('Enemy', 'https://d3rjk7x0pvg6e3.cloudfront.net/enemy.svg');


-- Funny Problems

INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id) VALUES ('Oh no! A trolley is heading towards 5 people. You can pull the lever to divert it to the other track, killing yourself instead. What do you do?', 0, 0, NULL, 'You', 1);

INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (1, 1, 1);

INSERT INTO comment (text, problem_id, author) VALUES ('I would pull the lever', 1, 'Jane Doe');
INSERT INTO comment (text, problem_id, author) VALUES ('I would not pull the lever', 1, 'John Doe');

INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id) VALUES ('Oh no! A trolley is heading towards 5 people. You can pull the lever to divert it to the other track, killing 4 people instead. What do you do?', 0, 0, NULL, NULL, 1);

INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (2, 1, 1);

INSERT INTO comment (text, problem_id, author) VALUES ('ROFL', 2, 'Troll');
INSERT INTO comment (text, problem_id, author) VALUES ('CRAZZY', 2, 'Mr. Ethical');
INSERT INTO comment (text, problem_id, author) VALUES ('What the hell was that????', 2, 'Mr. Unethical');

INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id) VALUES ('Oh no! A trolley is heading towards 0 people. You can pull the lever to divert it to the other track, killing 0 people instead. What do you do?', 0, 0, NULL, NULL, 1);

INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards a group of people. You can pull the lever to divert it to the other track, hitting a pile of money instead. What do you do?',
    0, 0,
    'Money',
    'People',
    1
);

INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (4, 2, 0);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (4, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (4, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (4, 1, 1);

INSERT INTO comment (text, problem_id, author) VALUES ('Money cant buy happiness, but it can stop a trolley!', 4, 'CashIsKing');
INSERT INTO comment (text, problem_id, author) VALUES ('Lets make it rain! Pull the lever!', 4, 'MoneyMagnet');
INSERT INTO comment (text, problem_id, author) VALUES ('Sorry folks, gotta save the cash!', 4, 'ThriftyTom');

INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards a group of babies. You can pull the lever to divert it to the other track, hitting a pile of money instead. What do you do?',
    0, 0,
    'Money',
    'Babies',
    1
);

INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (5, 2, 0);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (5, 5, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (5, 5, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (5, 5, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (5, 5, 1);

-- Thoughtful
INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards a group of scientists working on a cure for cancer. You can pull the lever to divert it to the other track, killing a single child instead. What do you do?',
    0, 0,
    'Child',
    'Scientists',
    2
);

INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (6, 5, 0);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (6, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (6, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (6, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position) VALUES (6, 1, 1);

INSERT INTO comment (text, problem_id, author) VALUES ('This is a really tough choice. Saving many at the cost of one...', 6, 'Alice');
INSERT INTO comment (text, problem_id, author) VALUES ('The potential future impact of the scientists is enormous, but the immediate loss of a child is heart-wrenching.', 6, 'Bob');
INSERT INTO comment (text, problem_id, author) VALUES ('What if those scientists dont find a cure? The certainty of saving a child seems more tangible.', 6, 'Charlie');
INSERT INTO comment (text, problem_id, author) VALUES ('Ethically, can we justify the loss of one to save many?', 6, 'Dave');


-- Ethical
INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards five convicted criminals. You can pull the lever to divert it to the other track, killing one innocent bystander instead. What do you do?',
    0, 0,
    'Innocent Bystander',
    'Criminals',
    3
);

INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (7, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (7, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (7, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (7, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (7, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (7, 1, 0);


INSERT INTO comment (text, problem_id, author) VALUES ('This is an ethical nightmare. How can we justify taking one innocent life?', 7, 'Eleanor');
INSERT INTO comment (text, problem_id, author) VALUES ('The criminals have already made their choices, but the innocent bystander hasnt.', 7, 'Chidi');
INSERT INTO comment (text, problem_id, author) VALUES ('I would let the trolley hit the criminals. Taking an innocent life feels wrong.', 7, 'Tahani');
INSERT INTO comment (text, problem_id, author) VALUES ('What if those criminals could be rehabilitated? Its a tough call.', 7, 'Jason');

-- Unethical
INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards a priceless work of art. You can pull the lever to divert it to the other track, killing a stray cat instead. What do you do?',
    0, 0,
    'Stray Cat',
    'Priceless Art',
    4
);

INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (8, 3, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (8, 7, 0);

INSERT INTO comment (text, problem_id, author) VALUES ('This is so unethical! Sacrificing a living being for an object?', 8, 'AnimalLover');
INSERT INTO comment (text, problem_id, author) VALUES ('The art is irreplaceable, but is it worth more than a life?', 8, 'ArtFanatic');
INSERT INTO comment (text, problem_id, author) VALUES ('I would save the cat. No piece of art is worth a life.', 8, 'EthicalThinker');
INSERT INTO comment (text, problem_id, author) VALUES ('Destroying art feels wrong, but sacrificing an animal is worse.', 8, 'CompassionateSoul');

-- Political
INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards a group of politicians known for corruption. You can pull the lever to divert it to the other track, hitting a protester demanding justice instead. What do you do?',
    0, 0,
    'Protester',
    'Corrupt Politicians',
    5
);

INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (9, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (9, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (9, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (9, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (9, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (9, 1, 0);

INSERT INTO comment (text, problem_id, author) VALUES ('This is a tough one. Politicians are corrupt, but they are still people.', 9, 'Politico');
INSERT INTO comment (text, problem_id, author) VALUES ('I would save the protester. They are fighting for justice.', 9, 'Activist');
INSERT INTO comment (text, problem_id, author) VALUES ('Corruption is terrible, but taking a life is worse.', 9, 'MoralCompanion');
INSERT INTO comment (text, problem_id, author) VALUES ('The protester represents hope for change. We must protect them.', 9, 'JusticeSeeker');
INSERT INTO comment (text, problem_id, author) VALUES ('Politicians have power to change things, but are they worth saving?', 9, 'Skeptic');
INSERT INTO comment (text, problem_id, author) VALUES ('I would let the trolley hit the corrupt politicians. Enough is enough.', 9, 'Revolutionary');
INSERT INTO comment (text, problem_id, author) VALUES ('Sacrificing a protester seems unjust. Corrupt politicians have caused enough harm.', 9, 'Righteous');
INSERT INTO comment (text, problem_id, author) VALUES ('Both options are bad, but the protester is an innocent civilian.', 9, 'EthicalObserver');

INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards a group of peaceful protesters. You can pull the lever to divert it to the other track, hitting a high-ranking government official instead. What do you do?',
    0, 0,
    'Government Official',
    'Peaceful Protesters',
    5
);

INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (10, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (10, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (10, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (10, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (10, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (10, 1, 0);

INSERT INTO comment (text, problem_id, author) VALUES ('Its a difficult choice, but I would prioritize saving the peaceful protesters.', 10, 'CitizenConscience');

-- Economic
INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards a group of workers at a struggling local business. You can pull the lever to divert it to the other track, hitting a shipment of expensive luxury goods instead. What do you do?',
    0, 0,
    'Expensive Luxury Goods',
    'Local Business Workers',
    6
);

INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (11, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (11, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (11, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (11, 1, 1);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (11, 3, 0);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (11, 3, 0);
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (11, 3, 0);

INSERT INTO comment (text, problem_id, author) VALUES ('Saving the workers is the priority. The economy can recover, but lives lost cannot be replaced.', 11, 'ConcernedCitizen');
INSERT INTO comment (text, problem_id, author) VALUES ('We must protect local jobs. Sacrificing luxury goods is a small price to pay.', 11, 'LocalSupporter');
INSERT INTO comment (text, problem_id, author) VALUES ('The workers are the backbone of the community. Their livelihoods matter more than expensive goods.', 11, 'CommunityMinded');
INSERT INTO comment (text, problem_id, author) VALUES ('Luxury goods are replaceable, but the workers lives are not.', 11, 'Humanitarian');

-- Insert a new problem in the Economic category
INSERT INTO problem (question, right_votes, left_votes, left_label, right_label, category_id)
VALUES (
    'Oh no! A trolley is heading towards a group of factory workers. You can pull the lever to divert it to the other track, hitting a shipment of expensive machinery instead. What do you do?',
    0, 0,
    'Expensive Machinery',
    'Factory Workers',
    6
);

-- Insert problem victims
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (12, 1, 1); -- Expensive Machinery (using 'Person' to represent each item)
INSERT INTO problem_victim (problem_id, victim_id, position)
VALUES (12, 1, 0); -- Factory Workers (using 'Person' to represent each worker)
