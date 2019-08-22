SELECT ArtObject.* FROM PermanentCollection
	INNER JOIN ArtObject
    WHERE PermanentCollection.Cost > 15.00
    AND PermanentCollection.ArtObjectID=ArtObject.ID;
    

-- Query the Art by the Cost using the Permanent Collection table.