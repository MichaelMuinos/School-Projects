SELECT ArtObject.* FROM Other
	INNER JOIN ArtObject
    WHERE Other.MaterialType="photo"
    AND Other.ArtObjectID=ArtObject.ID;
    

-- Query the Art that idendifies as Other by the Material Type (photo, print, etc.).