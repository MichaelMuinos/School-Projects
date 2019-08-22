from sqlalchemy import create_engine, Date
from sqlalchemy.orm import sessionmaker
from FormMenus import *
from Queries import *


engine = create_engine('sqlite:///artmuseumdatabase.db')
# Create all tables in the engine. This is equivalent to "Create Table"
# statements in raw SQL.
Base.metadata.create_all(engine)
# Bind the engine to the metadata of the Base class so that the
# declaratives can be accessed through a DBSession instance
Base.metadata.bind = engine
DBSession = sessionmaker(bind=engine)
# A DBSession() instance establishes all conversations with the database
# and represents a "staging zone" for all the objects loaded into the
# database session object. Any change made against the objects in the
# session won't be persisted into the database until you call
# session.commit(). If you're not happy about the changes, you can
# revert all of them back to the last commit by calling
# session.rollback()
session = DBSession()

# initialize query classes
insert_queries = InsertQueries(session)
where_queries = WhereQueries(session)
delete_queries = DeleteQueries(session)
# pass query classes to our UI
form_menus = FormMenus(insert_queries, delete_queries, where_queries)

# start the UI
print("Welcome to the Art Museum!")
form_menus.show_main_menu()
