SELECT ArtObject.* FROM ArtObject INNER JOIN PermanentCollection
	WHERE ExhibitionName="Art Show"
    AND Ownership="permanent"
	AND PermanentCollection.ArtObjectID=ArtObject.ID
    AND PermanentCollection.CurrentStatus="display";
    
    
-- Query Art that is currently on display at an exhibition.