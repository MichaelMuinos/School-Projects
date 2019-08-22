SELECT ArtObject.* FROM Sculpture 
	INNER JOIN ArtObject
    WHERE Sculpture.Height>=10
    AND Sculpture.ArtObjectID=ArtObject.ID;
    
    
-- Query the Art that identifies as a sculpture by the height.