from prettytable import PrettyTable
from ArtMuseumModels import *


class InsertQueries:

    def __init__(self, session):
        self.session = session

    def insert_art_object(self, year_created, artist_name, title, description, origin, category, ownership, exhibition_name, collection_name):
        # add art object to table
        art_object = ArtObject(id=None, year_created=year_created, artist_name=artist_name,
                              title=title, description=description, origin=origin,
                              category=category, ownership=ownership, exhibition_name=exhibition_name,
                              collection_name=collection_name)
        self.session.add(art_object)
        self.session.commit()
        return art_object.id

    def insert_perm_collection(self, art_object_id, args):
        self.session.add(PermanentCollection(id=None, art_object_id=art_object_id, date_acquired=args[0],
                                        status=args[1], cost=args[2]))
        self.session.commit()

    def insert_borrowed(self, art_object_id, collection_name, args):
        self.session.add(Borrowed(id=None, art_object_id=art_object_id, collection_name=collection_name,
                             date_borrowed=args[0], date_returned=args[1]))
        self.session.commit()

    def insert_art_painting(self, art_object_id, args):
        self.session.add(Painting(id=None, art_object_id=art_object_id, material=args[0],
                             style=args[1], paint_type=args[2]))
        self.session.commit()

    def insert_art_sculpture(self, art_object_id, args):
        self.session.add(Sculpture(id=None, art_object_id=art_object_id, material=args[0],
                                 height=args[1], weight=args[2], style=args[3]))
        self.session.commit()

    def insert_art_statue(self, art_object_id, args):
        self.session.add(Statue(id=None, art_object_id=art_object_id, material=args[0],
                                 height=args[1], weight=args[2], style=args[3]))
        self.session.commit()

    def insert_art_other(self, art_object_id, args):
        self.session.add(Other(id=None, art_object_id=art_object_id, style=args[0],
                          material_type=args[1]))
        self.session.commit()

    def insert_artist(self, args):
        self.session.add(Artist(name=args[0], origin=args[1], main_style=args[2],
                           epoch=args[3], date_born=args[4], date_died=args[5],
                           description=args[6]))
        self.session.commit()

    def insert_exhibition(self, args):
        # add to exhibition table
        self.session.add(Exhibition(name=args[0], start_date=args[1], end_date=args[2]))
        self.session.commit()

    def insert_collection(self, args):
        # add to collection table
        self.session.add(Collection(name=args[0], type=args[1], description=args[2],
                               address=args[3], phone_number=args[4],
                               contact_person_name=args[5]))
        self.session.commit()

    def artist_is_present(self, artist_name):
        # if artist name is not in table, have user fill artist form
        if artist_name is not None:
            if len(list(self.session.query(Artist).filter(Artist.name == artist_name))) == 0:
                return False
        return True

    def exhibition_is_present(self, exhibition_name):
        return False if len(
            list(self.session.query(Exhibition).filter(Exhibition.name == exhibition_name))) == 0 else True

    def collection_is_present(self, collection_name):
        return False if len(
            list(self.session.query(Collection).filter(Collection.name == collection_name))) == 0 else True


class WhereQueries:

    def __init__(self, session):
        self.session = session

    def art_object_query_by_painting(self):
        self.print_query_table(self.session.query(ArtObject)
                               .filter(ArtObject.category == "Painting"), ArtObject)

    def art_object_query_by_sculpture(self):
        self.print_query_table(self.session.query(ArtObject)
                           .filter(ArtObject.category == "Sculpture"), ArtObject)

    def art_object_query_by_statue(self):
        self.print_query_table(self.session.query(ArtObject)
                           .filter(ArtObject.category == "Statue"), ArtObject)

    def art_object_query_by_other(self):
        self.print_query_table(self.session.query(ArtObject)
                           .filter(ArtObject.category == "Other"), ArtObject)

    def art_object_query_by_artist(self, artist_name):
        self.print_query_table(self.session.query(ArtObject)
                                   .filter(ArtObject.artist_name == artist_name), ArtObject)

    def art_object_query_by_ownership(self, ownership):
        self.print_query_table(self.session.query(ArtObject)
                               .filter(ArtObject.ownership == ownership), ArtObject)

    def art_object_query_by_origin(self, origin):
        self.print_query_table(self.session.query(ArtObject)
                               .filter(ArtObject.origin == origin), ArtObject)

    def art_object_query_by_exhibition(self, exhibition_name):
            self.print_query_table(self.session.query(ArtObject)
                                   .filter(ArtObject.exhibition_name == exhibition_name), ArtObject)

    def art_object_query_by_displayed(self):
        self.print_query_table(self.session.query(ArtObject)
                               .join(PermanentCollection)
                               .filter(ArtObject.id == PermanentCollection.art_object_id, PermanentCollection.status == "Display"), ArtObject)

    def art_object_query_by_collection(self, collection_name):
            self.print_query_table(self.session.query(ArtObject)
                                   .filter(ArtObject.collection_name == collection_name), ArtObject)

    def art_object_query_by_cost(self):
        rows = self.session.query(ArtObject, PermanentCollection)\
            .join(PermanentCollection)\
            .filter(ArtObject.id == PermanentCollection.art_object_id)
        if len(list(rows)) == 0:
            print("Error: There is no data for that query!")
        else:
            table = PrettyTable(['ID', 'Artist Name', 'Category', 'Cost'])
            for row in rows:
                table.add_row([row[0].id, row[0].artist_name, row[0].category, row[1].cost])
            print(table)

    def artist_query_by_epoch(self, epoch):
        self.print_query_table(self.session.query(Artist)
                               .filter(Artist.epoch == epoch), Artist)

    def artist_query_by_main_style(self, main_style):
        self.print_query_table(self.session.query(Artist)
                               .filter(Artist.main_style == main_style), Artist)

    def artist_query_by_origin(self, origin):
        self.print_query_table(self.session.query(Artist)
                               .filter(Artist.origin == origin), Artist)

    def collection_query_by_type(self, type):
        self.print_query_table(self.session.query(Collection)
                               .filter(Collection.type == type), Collection)

    def collection_query_by_contact_name(self, contact_name):
        self.print_query_table(self.session.query(Collection)
                               .filter(Collection.contact_person_name == contact_name), Collection)

    def collection_query_by_phone_number(self, phone_number):
        self.print_query_table(self.session.query(Collection)
                               .filter(Collection.phone_number == phone_number), Collection)

    def exhibition_query_by_date(self, date):
        self.print_query_table(self.session.query(Exhibition)
                               .filter(Exhibition.start_date >= date, Exhibition.end_date <= date), Exhibition)

    def query_all_table(self, model):
        all_rows = self.session.query(model).all()
        if len(list(all_rows)) == 0:
            print("Error: There is no data yet!")
            return False
        else:
            table = PrettyTable(list(model.metadata.tables[model.__tablename__].columns.keys()))
            for row in all_rows:
                table.add_row(row.to_list())
            print(table)
            return True

    def print_query_table(self, rows, model):
        if len(list(rows)) == 0:
            print("Error: There is no data for that query!")
        else:
            table = PrettyTable(list(model.metadata.tables[model.__tablename__].columns.keys()))
            for row in rows:
                table.add_row(row.to_list())
            print(table)


class DeleteQueries:

    def __init__(self, session):
        self.session = session

    def delete_art_object(self, art_object_id):
        art_object = self.session.query(ArtObject).filter(ArtObject.id == art_object_id).one()
        # delete the art object
        self.session.delete(art_object)
        self.session.commit()
        return art_object.category, art_object.ownership

    def delete_painting(self, art_object_id):
        painting = self.session.query(Painting)\
            .filter(Painting.art_object_id == art_object_id).one()
        self.session.delete(painting)
        self.session.commit()

    def delete_sculpture(self, art_object_id):
        sculpture = self.session.query(Sculpture)\
            .filter(Sculpture.art_object_id == art_object_id).one()
        self.session.delete(sculpture)
        self.session.commit()

    def delete_statue(self, art_object_id):
        statue = self.session.query(Statue)\
            .filter(Statue.art_object_id == art_object_id).one()
        self.session.delete(statue)
        self.session.commit()

    def delete_other(self, art_object_id):
        other = self.session.query(Other)\
            .filter(Other.art_object_id == art_object_id).one()
        self.session.delete(other)
        self.session.commit()

    def delete_perm_collection(self, art_object_id):
        perm = self.session.query(PermanentCollection)\
            .filter(PermanentCollection.art_object_id == art_object_id).one()
        self.session.delete(perm)
        self.session.commit()

    def delete_borrowed(self, art_object_id):
        borrowed = self.session.query(Borrowed).filter(Borrowed.art_object_id == art_object_id).one()
        self.session.delete(borrowed)
        self.session.commit()

    def table_is_empty(self):
        return True if len(list(self.session.query(ArtObject).all())) == 0 else False