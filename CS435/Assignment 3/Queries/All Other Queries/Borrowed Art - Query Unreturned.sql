SELECT ArtObject.* FROM Borrowed 
	INNER JOIN ArtObject
    WHERE Borrowed.DateReturned IS NULL
    AND Borrowed.ArtObjectID=ArtObject.ID;
    
    
-- Query all Art that has not been returned yet.