import datetime
import sys
from ArtMuseumModels import *


class FormMenus:

    def __init__(self, insert_queries, delete_queries, where_queries):
        self.insert_queries = insert_queries
        self.delete_queries = delete_queries
        self.where_queries = where_queries

    def show_main_menu(self):
        print("What would you like to do?")
        print("1) Insert content")
        print("2) Query content")
        print("3) View content")
        print("4) Delete content")
        print("5) Leave the museum")
        # Get user input
        choice = self.get_user_input("")
        # Check if the number is within range
        if self.choice_is_within_range(choice, 5):
            if choice == 1:
                self.show_insert_menu()
            elif choice == 2:
                self.show_query_menu()
            elif choice == 3:
                self.show_view_menu()
            elif choice == 4:
                self.show_delete_menu()
            else:
                print("Bye!")
                sys.exit()
        else:
            print("Error: Incorrect input. Try again!")
            self.show_main_menu()

    def show_insert_menu(self):
        print("Choose an option:")
        print("1) Insert art object")
        print("2) Insert artist")
        print("3) Insert exhibition")
        print("4) Insert collection")
        print("5) Return to main menu")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 5):
            if choice == 1:
                self.show_art_object_form()
            elif choice == 2:
                args = self.show_artist_form(None)
                self.insert_queries.insert_artist(args)
            elif choice == 3:
                args = self.show_exhibition_form(None)
                self.insert_queries.insert_exhibition(args)
            elif choice == 4:
                args = self.show_collection_form(None)
                self.insert_queries.insert_collection(args)
            else:
                self.show_main_menu()
        else:
            print("Error: Incorrect input. Try again!")
        self.show_insert_menu()

    def show_art_object_form(self):
        print("Please fill out this Art Object Form.")
        year_created = self.get_year_created()
        artist_name = self.get_artist_name()
        title = input("Title: ")
        description = input("Description: ")
        origin = self.get_origin()
        category = self.get_category()
        ownership = self.get_ownership()
        exhibition_name = input("Exhibition name: ")
        collection_name = input("Collection name: ")

        # if artist name is not in table, have user fill artist form
        if self.insert_queries.artist_is_present(artist_name) is False:
            args = self.show_artist_form(artist_name)
            self.insert_queries.insert_artist(args)

        if self.insert_queries.exhibition_is_present(exhibition_name) is False:
            args = self.show_exhibition_form(exhibition_name)
            self.insert_queries.insert_exhibition(args)

        if self.insert_queries.collection_is_present(collection_name) is False:
            args = self.show_collection_form(collection_name)
            self.insert_queries.insert_collection(args)

        # have to insert art object here because it has a reference to the artist, collection, and exhibition
        art_object_id = self.insert_queries.insert_art_object(year_created, artist_name, title, description, origin, category, ownership, exhibition_name, collection_name)

        # have user enter category form
        if category == "Painting":
            args = self.show_painting_form()
            self.insert_queries.insert_art_painting(art_object_id, args)
        elif category == "Sculpture":
            args = self.show_sculpture_form()
            self.insert_queries.insert_art_sculpture(art_object_id, args)
        elif category == "Statue":
            args = self.show_statue_form()
            self.insert_queries.insert_art_statue(art_object_id, args)
        else:
            args = self.show_other_form()
            self.insert_queries.insert_art_other(art_object_id, args)

        # have user enter ownership form
        if ownership == "Permanent Collection":
            args = self.show_perm_collection_form()
            self.insert_queries.insert_perm_collection(art_object_id, args)
        else:
            args = self.show_borrowed_form()
            self.insert_queries.insert_borrowed(art_object_id, collection_name, args)

        self.show_insert_menu()

    def show_artist_form(self, artist_name):
        print("Please fill out this Artist Form.")
        name = artist_name if artist_name is not None else input("Name of artist: ")
        origin = self.get_origin()
        main_style = self.get_style()
        epoch = self.get_epoch()
        date_born = self.get_date("Date born: ", False)
        date_died = self.get_date("Date died (Optional): ", True)
        description = input("Description: ")
        return name, origin, main_style, epoch, date_born, date_died, description

    def show_painting_form(self):
        print("Please fill out this Painting Form.")
        material = self.get_material()
        style = self.get_style()
        paint_type = self.get_paint_type()
        return material, style, paint_type

    def show_sculpture_form(self):
        print("Please fill out this Sculpture Form.")
        material = self.get_material()
        height = int(input("Height(feet): "))
        weight = int(input("Weight(pounds): "))
        style = self.get_style()
        return material, height, weight, style

    def show_perm_collection_form(self):
        print("Please fill out further details on the piece.")
        date_acquired = self.get_date("Date acquired: ", False)
        status = self.get_status()
        cost = float(input("Cost: "))
        return date_acquired, status, cost

    def show_borrowed_form(self):
        print("Please fill out further details on the piece.")
        date_borrowed = self.get_date("Date Borrowed: ", False)
        date_returned = self.get_date("Date Returned (Optional): ", True)
        return date_borrowed, date_returned

    def show_statue_form(self):
        print("Please fill out this Statue Form.")
        material = self.get_material()
        height = int(input("Height(feet): "))
        weight = int(input("Weight(pounds): "))
        style = self.get_style()
        return material, height, weight, style

    def show_other_form(self):
        print("Please fill out this Other Form.")
        style = self.get_style()
        material_type = self.get_material()
        return style, material_type

    def show_exhibition_form(self, exhibition_name):
        print("Please fill out this Exhibition Form.")
        name = exhibition_name if exhibition_name is not None else input("Name of exhibition: ")
        start_date = self.get_date("Start date: ", False)
        end_date = self.get_date("End date: ", False)
        return name, start_date, end_date

    def show_collection_form(self, collection_name):
        print("Please fill out the Collection Form.")
        name = collection_name if collection_name is not None else input("Name of collection: ")
        type = self.get_collection_type()
        description = input("Description: ")
        address = input("Address: ")
        phone_number = input("Phone number: ")
        contact_person_name = input("Contact person name: ")
        return name, type, description, address, phone_number, contact_person_name

    def show_query_menu(self):
        print("Choose an option:")
        print("1) Query art objects")
        print("2) Query artists")
        print("3) Query collections")
        print("4) Query exhibitions")
        print("5) Return to main menu")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 5):
            if choice == 1:
                self.show_query_art_objects_menu()
            elif choice == 2:
                self.show_query_artists_menu()
            elif choice == 3:
                self.show_query_collections_menu()
            elif choice == 4:
                date = self.get_date("Date to query by: ", False)
                self.where_queries.exhibition_query_by_date(date)
            else:
                self.show_main_menu()
        else:
            print("Error: Incorrect input. Try again!")
        self.show_query_menu()

    def show_query_art_objects_menu(self):
        print("What would you like to query by?")
        print("1) Category")
        print("2) Artist")
        print("3) Ownership")
        print("4) Origin")
        print("5) Exhibition")
        print("6) Displayed art")
        print("7) Collection")
        print("8) Cost")
        print("9) Return to query menu")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 9):
            if choice == 1:
                category = self.get_category()
                if category == "Painting":
                    self.where_queries.art_object_query_by_painting()
                elif category == "Sculpture":
                    self.where_queries.art_object_query_by_sculpture()
                elif category == "Statue":
                    self.where_queries.art_object_query_by_statue()
                else:
                    self.where_queries.art_object_query_by_other()
            elif choice == 2:
                artist_name = input("Artist name: ")
                self.where_queries.art_object_query_by_artist(artist_name)
            elif choice == 3:
                ownership = self.get_ownership()
                self.where_queries.art_object_query_by_ownership(ownership)
            elif choice == 4:
                origin = self.get_origin()
                self.where_queries.art_object_query_by_origin(origin)
            elif choice == 5:
                exhibition_name = input("Exhibition name: ")
                self.where_queries.art_object_query_by_exhibition(exhibition_name)
            elif choice == 6:
                self.where_queries.art_object_query_by_displayed()
            elif choice == 7:
                collection_name = input("Collection name: ")
                self.where_queries.art_object_query_by_collection(collection_name)
            elif choice == 8:
                self.where_queries.art_object_query_by_cost()
            else:
                self.show_query_menu()
        else:
            print("Error: Incorrect input. Try again!")
        self.show_query_art_objects_menu()

    def show_query_collections_menu(self):
        print("What would you like to query by?")
        print("1) Type")
        print("2) Contact name")
        print("3) Phone number")
        print("4) Return to query menu")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 4):
            if choice == 1:
                type = self.get_collection_type()
                self.where_queries.collection_query_by_type(type)
            elif choice == 2:
                contact_name = input("Contact name: ")
                self.where_queries.collection_query_by_contact_name(contact_name)
            elif choice == 3:
                phone_number = input("Phone number: ")
                self.where_queries.collection_query_by_phone_number(phone_number)
            else:
                self.show_query_menu()
        else:
            print("Incorrect input. Try again!")
        self.show_query_collections_menu()

    def show_query_artists_menu(self):
        print("What would you like to query by?")
        print("1) Epoch")
        print("2) Main style")
        print("3) Origin")
        print("4) Return to query menu")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 4):
            if choice == 1:
                epoch = self.get_epoch()
                self.where_queries.artist_query_by_epoch(epoch)
            elif choice == 2:
                main_style = self.get_style()
                self.where_queries.artist_query_by_main_style(main_style)
            elif choice == 3:
                origin = self.get_origin()
                self.where_queries.artist_query_by_origin(origin)
            else:
                self.show_query_menu()
        else:
            print("Error: Incorrect input. Try again!")
        self.show_query_artists_menu()

    def show_view_menu(self):
        print("Choose an option:")
        print("1) View all art objects")
        print("2) View all art paintings")
        print("3) View all art sculptures")
        print("4) View all art statues")
        print("5) View all art others")
        print("6) View all artists")
        print("7) View all collections")
        print("8) View all exhibitions")
        print("9) Return to main menu")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 9):
            if choice == 1:
                self.where_queries.query_all_table(ArtObject)
            elif choice == 2:
                self.where_queries.query_all_table(Painting)
            elif choice == 3:
                self.where_queries.query_all_table(Sculpture)
            elif choice == 4:
                self.where_queries.query_all_table(Statue)
            elif choice == 5:
                self.where_queries.query_all_table(Other)
            elif choice == 6:
                self.where_queries.query_all_table(Artist)
            elif choice == 7:
                self.where_queries.query_all_table(Collection)
            elif choice == 8:
                self.where_queries.query_all_table(Exhibition)
            else:
                self.show_main_menu()
        else:
            print("Error: Incorrect input. Try again!")
        self.show_view_menu()

    def show_delete_menu(self):
        print("Choose an option: ")
        print("1) Delete an art object")
        print("2) Return to main menu")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 2):
            if choice == 1:
                self.show_delete_form()
            else:
                self.show_main_menu()
        else:
            print("Error: Incorrect input. Try again!")
        self.show_delete_menu()

    def show_delete_form(self):
        if self.delete_queries.table_is_empty() is False:
            self.where_queries.query_all_table(ArtObject)
            art_object_id = int(input("Enter the ID you want to delete: "))
            args = self.delete_queries.delete_art_object(art_object_id)
            category = args[0]
            ownership = args[1]
            # delete the category
            if category == "Painting":
                self.delete_queries.delete_painting(art_object_id)
            elif category == "Sculpture":
                self.delete_queries.delete_sculpture(art_object_id)
            elif category == "Statue":
                self.delete_queries.delete_statue(art_object_id)
            else:
                self.delete_queries.delete_other(art_object_id)
            # delete the ownership
            if ownership == "Permanent Collection":
                self.delete_queries.delete_perm_collection(art_object_id)
            else:
                self.delete_queries.delete_borrowed(art_object_id)
        else:
            print("Error: No data has been found!")
        self.show_delete_menu()

    # Returns collection type as a string
    def get_collection_type(self):
        print("Type of collection: ")
        print("1) Museum")
        print("2) Personal")
        print("3) Other")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 3):
            if choice == 1:
                return "Museum"
            elif choice == 2:
                return "Personal"
            else:
                return "Other"
        else:
            print("Error: Incorrect input. Try again!")
            self.get_collection_type()

    # Return artist name for art object
    def get_artist_name(self):
        print("Artist name: ")
        if self.fill_field() is False:
            return None
        else:
            return input("Artist -> ")

    # Returns year created for art object
    def get_year_created(self):
        print("Year created: ")
        if self.fill_field() is False:
            return None
        else:
            choice = self.get_user_input("Year -> ")
            if choice > 0:
                return choice
            else:
                print("Error: Incorrect input. Try again!")
                self.get_year_created()

    # Return status choice for art object
    def get_status(self):
        print("Status: ")
        print("1) Display")
        print("2) Stored")
        print("3) Loan")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 3):
            if choice == 1:
                return "Display"
            elif choice == 2:
                return "Stored"
            else:
                return "Loan"
        else:
            print("Error: Incorrect input. Try again!")
            self.get_status()

    # Return category choice for art object
    def get_category(self):
        print("Category: ")
        print("1) Painting")
        print("2) Sculpture")
        print("3) Statue")
        print("4) Other")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 4):
            if choice == 1:
                return "Painting"
            elif choice == 2:
                return "Sculpture"
            elif choice == 3:
                return "Statue"
            else:
                return "Other"
        else:
            print("Error: Incorrect input. Try again!")
            self.get_category()

    # Returns the ownership of the art object
    def get_ownership(self):
        print("Ownership: ")
        print("1) Permanent Collection")
        print("2) Borrowed")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 2):
            if choice == 1:
                return "Permanent Collection"
            else:
                return "Borrowed"
        else:
            print("Error: Incorrect input. Try again!")
            self.get_ownership()

    # Returns the origin that is chosen as a string
    def get_origin(self):
        print("Country of origin: ")
        print("1) Italian")
        print("2) Egyptian")
        print("3) American")
        print("4) Chinese")
        print("5) Other")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 5):
            if choice == 1:
                return "Italian"
            elif choice == 2:
                return "Egyptian"
            elif choice == 3:
                return "American"
            elif choice == 4:
                return "Chinese"
            else:
                return "Other"
        else:
            print("Error: Incorrect input. Try again!")
            self.get_origin()

    def get_paint_type(self):
        print("Paint type: ")
        print("1) Oil")
        print("2) Watercolor")
        print("3) Other")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 3):
            if choice == 1:
                return "Oil"
            elif choice == 2:
                return "Watercolor"
            else:
                return "Other"

    # Returns the material that is chosen as a string
    def get_material(self):
        print("Material: ")
        print("1) Paper")
        print("2) Canvas")
        print("3) Wood")
        print("4) Stone")
        print("5) Print")
        print("6) Photo")
        print("7) Other")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 7):
            if choice == 1:
                return "Paper"
            elif choice == 2:
                return "Canvas"
            elif choice == 3:
                return "Wood"
            elif choice == 4:
                return "Stone"
            elif choice == 5:
                return "Print"
            elif choice == 6:
                return "Photo"
            else:
                return "Other"
        else:
            print("Error: Incorrect input. Try again!")
            self.get_material()

    # Returns the style that is chosen as a string
    def get_style(self):
        print("Main style: ")
        print("1) Modern")
        print("2) Abstract")
        print("3) Contemporary")
        print("4) Cubism")
        print("5) Impressionism")
        print("6) Other")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 6):
            if choice == 1:
                return "Modern"
            elif choice == 2:
                return "Abstract"
            elif choice == 3:
                return "Contemporary"
            elif choice == 4:
                return "Cubism"
            elif choice == 5:
                return "Impressionism"
            elif choice == 6:
                return "Other"
        else:
            print("Error: Incorrect input. Try again!")
            self.get_style()

    # Returns the epoch that is chosen as a string
    def get_epoch(self):
        print("Epoch: ")
        print("1) Renaissance")
        print("2) Modern")
        print("3) Ancient")
        print("4) Other")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 4):
            if choice == 1:
                return "Renaissance"
            elif choice == 2:
                return "Modern"
            elif choice == 3:
                return "Ancient"
            else:
                return "Other"
        else:
            print("Error: Incorrect input. Try again!")
            self.get_epoch()

    # Retrieve the inputted date from the user
    def get_date(self, message, optional):
        print(message)
        if optional is True:
            if self.fill_field() is False:
                return None
        year = self.get_user_input("Enter year -> ")
        month = self.get_user_input("Enter month -> ")
        day = self.get_user_input("Enter day -> ")
        if self.is_valid_date(year, month, day):
            return datetime.datetime(year, month, day)
        else:
            print("Error: Incorrect input. Try again!")
            self.get_date(message)

    # Get user input
    def get_user_input(self, msg):
        try:
            choice = int(input(msg))
        except ValueError:
            choice = 0
        return choice

    # Determine if the user wants to fill the field or not
    def fill_field(self):
        print("Did you want to enter anything to this field?")
        print("1) Yes")
        print("2) No")
        choice = self.get_user_input("")
        if self.choice_is_within_range(choice, 2):
            return True if choice == 1 else False
        else:
            print("Error: Incorrect input. Try again!")
            self.fill_field()

    # Determine if the choice the user entered is within range
    def choice_is_within_range(self, choice, choice_range):
        return True if (choice > 0) and (choice <= choice_range) else False

    # Determine if the date the user entered is a valid date
    def is_valid_date(self, year, month, day):
        try:
            datetime.datetime(year, month, day)
            correct_date = True
        except ValueError:
            correct_date = False
        return correct_date


