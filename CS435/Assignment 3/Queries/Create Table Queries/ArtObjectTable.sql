CREATE TABLE ArtObject(
	ID INT NOT NULL AUTO_INCREMENT,
    YearCreated INT,
    ArtistName VARCHAR(50) REFERENCES Artist(Name),
    Title VARCHAR(30) NOT NULL,
    Description VARCHAR(512) NOT NULL,
    Origin VARCHAR(30) NOT NULL,
    Epoch VARCHAR(50) NOT NULL,
    Category VARCHAR(30) NOT NULL,
    Ownership VARCHAR(30) NOT NULL,
    ExhibitionName VARCHAR(50) NOT NULL REFERENCES Exhibition(Name),
    CollectionName VARCHAR(50) NOT NULL REFERENCES Collection(Name),
    PRIMARY KEY(ID)
);