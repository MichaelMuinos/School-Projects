SELECT ArtObject.* FROM Painting
	INNER JOIN ArtObject
    WHERE Painting.PaintType="oil"
    AND Painting.ArtObjectID=ArtObject.ID;
    
    
-- Query the Art that identifies as a Painting by the Paint Type.