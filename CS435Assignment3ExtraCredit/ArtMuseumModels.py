from sqlalchemy import Column, Integer, String, Date, ForeignKey, DECIMAL
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()


class Artist(Base):
    __tablename__ = 'ArtistTable'
    name = Column(String(50), primary_key=True, nullable=False)
    origin = Column(String(30), nullable=False)
    main_style = Column(String(30), nullable=False)
    epoch = Column(String(50), nullable=False)
    date_born = Column(Date, nullable=False)
    date_died = Column(Date, nullable=True)
    description = Column(String(512), nullable=False)

    def to_list(self):
        return [self.name, self.origin, self.main_style, self.epoch, self.date_born,
                self.date_died, self.description]


class Collection(Base):
    __tablename__ = 'CollectionTable'
    name = Column(String(30), primary_key=True, nullable=False)
    type = Column(String(30), nullable=False)
    description = Column(String(512), nullable=False)
    address = Column(String(50), nullable=False)
    phone_number = Column(String(30), nullable=False)
    contact_person_name = Column(String(50), nullable=False)

    def to_list(self):
        return [self.name, self.type, self.description, self.address, self.phone_number, self.contact_person_name]


class Exhibition(Base):
    __tablename__ = 'ExhibitionTable'
    name = Column(String(50), primary_key=True, nullable=False)
    start_date = Column(Date, nullable=False)
    end_date = Column(Date, nullable=False)

    def to_list(self):
        return [self.name, self.start_date, self.end_date]


class ArtObject(Base):
    __tablename__ = 'ArtObjectTable'
    id = Column(Integer, primary_key=True, autoincrement=True, nullable=False)
    year_created = Column(Integer, nullable=True)
    artist_name = Column(String(50), ForeignKey(Artist.name), nullable=True)
    title = Column(String(30), nullable=False)
    description = Column(String(512), nullable=False)
    origin = Column(String(30), nullable=False)
    category = Column(String(30), nullable=False)
    ownership = Column(String(30), nullable=False)
    exhibition_name = Column(String(50), ForeignKey(Exhibition.name), nullable=False)
    collection_name = Column(String(50), ForeignKey(Collection.name), nullable=False)

    def to_list(self):
        return [self.id, self.year_created, self.artist_name, self.title,
                self.description, self.origin, self.category, self.ownership,
                self.exhibition_name, self.collection_name]


class Painting(Base):
    __tablename__ = 'PaintingTable'
    id = Column(Integer, primary_key=True, autoincrement=True, nullable=False)
    art_object_id = Column(Integer, ForeignKey(ArtObject.id), nullable=False)
    material = Column(String(30), nullable=False)
    style = Column(String(30), nullable=False)
    paint_type = Column(String(30), nullable=False)

    def to_list(self):
        return [self.id, self.art_object_id, self.material, self.style, self.paint_type]


class Sculpture(Base):
    __tablename__ = 'SculptureTable'
    id = Column(Integer, primary_key=True, autoincrement=True, nullable=False)
    art_object_id = Column(Integer, ForeignKey(ArtObject.id), nullable=False)
    material = Column(String(30), nullable=False)
    height = Column(Integer, nullable=False)
    weight = Column(Integer, nullable=False)
    style = Column(String(30), nullable=False)

    def to_list(self):
        return [self.id, self.art_object_id, self.material, self.height, self.weight, self.height]


class Statue(Base):
    __tablename__ = 'StatueTable'
    id = Column(Integer, primary_key=True, autoincrement=True, nullable=False)
    art_object_id = Column(Integer, ForeignKey(ArtObject.id), nullable=False)
    material = Column(String(30), nullable=False)
    height = Column(Integer, nullable=False)
    weight = Column(Integer, nullable=False)
    style = Column(String(30), nullable=False)

    def to_list(self):
        return [self.id, self.art_object_id, self.material, self.height, self.weight, self.height]


class Other(Base):
    __tablename__ = 'OtherTable'
    id = Column(Integer, primary_key=True, autoincrement=True, nullable=False)
    art_object_id = Column(Integer, ForeignKey(ArtObject.id), nullable=False)
    material_type = Column(String(30), nullable=False)
    style = Column(String(30), nullable=False)

    def to_list(self):
        return [self.id, self.art_object_id, self.material_type, self.style]


class PermanentCollection(Base):
    __tablename__ = 'PermanentCollectionTable'
    id = Column(Integer, primary_key=True, autoincrement=True, nullable=False)
    art_object_id = Column(Integer, ForeignKey(ArtObject.id), nullable=False)
    status = Column(String(30), nullable=False)
    date_acquired = Column(Date, nullable=False)
    cost = Column(DECIMAL(10,2), nullable=False)

    def to_list(self):
        return [self.id, self.art_object_id, self.status, self.date_acquired, self.cost]


class Borrowed(Base):
    __tablename__ = 'BorrowedTable'
    id = Column(Integer, primary_key=True, autoincrement=True, nullable=False)
    art_object_id = Column(Integer, ForeignKey(ArtObject.id), nullable=False)
    collection_name = Column(String(30), ForeignKey(Collection.name), nullable=False)
    date_borrowed = Column(Date, nullable=False)
    date_returned = Column(Date, nullable=True)

    def to_list(self):
        return [self.id, self.art_object_id, self.collection_name, self.date_borrowed, self.date_returned]


