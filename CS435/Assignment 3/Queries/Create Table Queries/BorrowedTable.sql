CREATE TABLE Borrowed(
	ID INT NOT NULL AUTO_INCREMENT,
    ArtObjectID INT NOT NULL REFERENCES ArtObject(ID),
    CollectionName VARCHAR(30) REFERENCES Collection(Name),
    DateBorrowed DATE NOT NULL,
    DateReturned DATE,
	PRIMARY KEY(ID)
);