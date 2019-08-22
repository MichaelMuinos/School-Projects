CREATE TABLE Artist(
	Name VARCHAR(50) NOT NULL,
    Origin VARCHAR(30) NOT NULL,
    MainStyle VARCHAR(30) NOT NULL,
    Epoch VARCHAR(50) NOT NULL,
    DateBorn DATE NOT NULL,
    DateDied DATE,
    Description VARCHAR(512) NOT NULL,
    PRIMARY KEY(Name)
);