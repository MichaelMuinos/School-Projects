from tkinter import *
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from Database import Base, Attack
import os
import threading
import datetime
from os.path import isfile
import platform
from Message import Message

current_os = platform.system()
if os == 'Windows':
    file_path = os.path.expanduser("~/Desktop/")
else:
    file_path = os.path.expanduser("~/Desktop/")


class GUI:
    def __init__(self, master):
        self.master = master
        master.title("DDOS Detector")
        master.geometry("750x600")

        self.label_file = Label(master, text="Enter the file name: ", fg="red")
        self.label_file.pack()

        self.entry_file = Entry(master, width=45)
        self.entry_file.pack()

        self.button = Button(master, text="Determine Threats", command=self.determine_threats_button_click)
        self.button.pack()

        self.frame_date = Frame()

        self.label_start_date = Label(self.frame_date, text="Start Date", fg="red")
        self.label_start_date.grid(row=0, column=0, columnspan=7)

        self.label_start_date_day = Label(self.frame_date, text="Day")
        self.label_start_date_day.grid(row=1, column=0)

        self.entry_start_date_day = Entry(self.frame_date, width=5)
        self.entry_start_date_day.grid(row=1, column=1)

        self.label_start_date_month = Label(self.frame_date, text="Month")
        self.label_start_date_month.grid(row=1, column=2)

        self.entry_start_date_month = Entry(self.frame_date, width=5)
        self.entry_start_date_month.grid(row=1, column=3)

        self.label_end_date = Label(self.frame_date, text="End Date", fg="red")
        self.label_end_date.grid(row=2, column=0, columnspan=7)

        self.label_end_date_day = Label(self.frame_date, text="Day")
        self.label_end_date_day.grid(row=3, column=0)

        self.entry_end_date_day = Entry(self.frame_date, width=5)
        self.entry_end_date_day.grid(row=3, column=1)

        self.label_end_date_month = Label(self.frame_date, text="Month")
        self.label_end_date_month.grid(row=3, column=2)

        self.entry_end_date_month = Entry(self.frame_date, width=5)
        self.entry_end_date_month.grid(row=3, column=3)

        self.frame_date.pack()

        self.button = Button(master, text="Filter", command=self.filter_button_click)
        self.button.pack()

        self.label_help = Label(master, text="\nHOW TO USE:\n1. Create a file of possible DDOS threats."
                                             "\n2. Enter the file name inside the top most box.\n3. (Optional) Specify a date interval to search attacks.\n"
                                             "4. Click \"Determine Threats\" and the program will define the threats."
                                             "\n-----------------------------------------------------------------------------------\n", fg="blue")
        self.label_help.pack()

        self.text = Text(master, width=52)
        self.text.pack(side="left")

        self.text_right = Text(master)
        self.text_right.pack(side="left")

    def determine_threats_button_click(self):
        if len(self.entry_file.get()) == 0:
           self.update_text_widget(self.text, "You can't enter an empty file!\n\n")
        else:
            global file_path
            # path to read text file
            file_path = file_path + self.entry_file.get()
            # tkinter is single threaded, thus start a seperate thread for the parsing process
            ParseDataThread(file_path, self.text, self.text_right).start()

    def filter_button_click(self):
        if self.isBadFilterInput():
            self.update_text_widget(self.text, "Bad filter input, try again!\n\n")
        else:
            start_date = datetime.datetime(year=2017, month=int(self.entry_start_date_month.get()), day=int(self.entry_start_date_day.get()))
            end_date = datetime.datetime(year=2017, month=int(self.entry_end_date_month.get()), day=int(self.entry_end_date_day.get()), hour=23, minute=0, second=0)
            if end_date < start_date:
                self.update_text_widget(self.text, "End date can't be less than the start date!\n\n")
            else:
                filter_attacks = session.query(Attack).filter(Attack.date >= start_date, Attack.date <= end_date).all()
                # clear text box
                self.text_right.delete('1.0', END)
                if len(list(filter_attacks)) == 0:
                    self.update_text_widget(self.text_right, "No attacks in that date interval!\n\n")
                else:
                    # print out filtered attacks
                    for attack in filter_attacks:
                        self.update_text_widget(self.text_right, str(attack.date)[5:] + "\tDSP: " + str(attack.destination_port) + "\tIP: " + str(attack.source_ip) + "\n")

    def isBadFilterInput(self):
        return True if self.isBadFilterInputHelper(self.entry_end_date_day) or self.isBadFilterInputHelper(self.entry_end_date_month) \
                or self.isBadFilterInputHelper(self.entry_start_date_day) or self.isBadFilterInputHelper(self.entry_start_date_month) \
                else False

    def isBadFilterInputHelper(self, widget):
        return True if len(widget.get()) == 0 or any(i.isalpha() for i in str(widget.get())) else False

    '''
    Update text to the tkinter widget to inform user what is happening.
    '''
    def update_text_widget(self, widget, message):
        # Update text to text widget
        widget.insert(END, message)
        # Move to the end of the text box if text has gone outside the initial length
        widget.see(END)


class ParseDataThread(threading.Thread):
    file = None
    THRESHOLD = 3
    attack_logs = []
    prev = None

    def __init__(self, file, text, text_right):
        threading.Thread.__init__(self)
        self.text = text
        self.text_right = text_right
        self.open(file)

    def open(self, file):
        if isfile(file):
            self.file = file
            return True
        else:
            return False

    def read(self):
        is_not_first_line = False
        if self.file is not None:
            self.text.delete('1.0', END)
            with open(self.file) as f:
                for line in f:
                    if line is not None:
                        message = self.process(line)
                        if is_not_first_line:
                            if self.is_attack(message.messages):
                                self.attack_logs.append(message)
                            else:
                                length = len(self.attack_logs)
                                if length >= self.THRESHOLD:
                                    self.add_attack_logs(self.attack_logs)
                                    self.print_type(self.attack_logs, "ATTACK")
                                elif length > 0:
                                    self.print_type(self.attack_logs, "SAFE")
                                else:
                                    self.update_text_widget(str(message.messages['date'])[5:] + "\tDSP: " + str(message.messages['destination_port']) + "\t\t\t\tSAFE" + "\n")
                                self.attack_logs.clear()
                            self.prev = message
                        else:
                            is_not_first_line = True
                            self.prev = message
                f.close()

    def process(self, line):
        final = Message()
        messages = {
            'src': 'source_ip',
            'dst': 'destination_ip',
            'spt': 'source_port',
            'dpt': 'destination_port',
            'ttl': 'ttl',
            'len': 'packet_length',
            'date': None
        }
        od = str(line).lower().split()
        reconstructed_date = "%s %s 2017 %s" % (str(od[0]).title(), str(od[1]), str(od[2]))
        date = datetime.datetime.strptime(reconstructed_date, "%b %d %Y %H:%M:%S")
        del(od[0:2])
        messages['date'] = date

        final.add('date', date)
        for field in od:
            of = field.split("=")
            key = str(of[0]).split()[0]
            if key in messages.keys():
                final.add(messages[key], of[1])
        return final

    def is_attack(self, cur):
        # if days are the same
        prev_date = self.prev.messages['date']
        cur_date = cur['date']
        if (prev_date.day == cur_date.day) and (prev_date.month == cur_date.month):
            # do conversion of seconds here
            if (self.second_conversion(cur_date.hour, cur_date.minute, cur_date.second) \
                    - self.second_conversion(prev_date.hour, prev_date.minute, prev_date.second) <= 10) \
                    and self.prev.messages['destination_port'] == cur['destination_port']:
                return True
        return False

    def second_conversion(self, hours, minutes, seconds):
        return (60 * hours) + (60 * minutes) + seconds

    def add_attack_logs(self, attack_logs):
        for attack in attack_logs:
            msg = attack.messages
            session.add(Attack(source_ip=msg['source_ip'], destination_ip=msg['destination_ip'],
                               source_port=msg['source_port'], destination_port=msg['destination_port'],
                               ttl=msg['ttl'], packet_length=msg['packet_length'], date=msg['date']))
        session.commit()

    def run(self):
        self.read()

    def print_type(self, list, type):
        for item in list:
            self.update_text_widget(str(item.messages['date'])[5:] + "\tDSP: " + str(item.messages['destination_port']) + "\t\t\t\t" + type + "\n")

    def update_text_widget(self, message):
        self.text.insert(END, message)
        self.text.see(END)

engine = create_engine('sqlite:///attackdatabase.db')
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
root = Tk()
root.resizable(width=False, height=False)
gui = GUI(root)
root.mainloop()
