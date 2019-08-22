SELECT ArtObject.* FROM ArtObject 
	INNER JOIN PermanentCollection
    INNER JOIN Exhibition
	WHERE ArtObject.ExhibitionName=Exhibition.Name
    AND Ownership="permanent"
	AND PermanentCollection.ArtObjectID=ArtObject.ID
    AND PermanentCollection.CurrentStatus="display"
    AND Exhibition.StartDate>="2013-2-2"
    AND Exhibition.EndDate<="2013-2-3";
    

-- Query Art by a date in which they are on display at the exhibition.