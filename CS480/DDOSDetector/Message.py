class Message:
    ### Class object for messages
    messages = {
        'source_ip': None,
        'destination_ip': None,
        'source_port': None,
        'destination_port': None,
        'ttl': None,
        'packet_length': None,
        'date': None
    }

    def __init__(self):
        self.messages = {
            'source_ip': None,
            'destination_ip': None,
            'source_port': None,
            'destination_port': None,
            'ttl': None,
            'packet_length': None,
            'date': None
        }

    def __repr__(self):
        str = ""
        for key, value in self.messages.items():
            # if key != "date":
            str = str + "%s = %s,\t" % (key, value)

        return str

    # i know its bad
    def __str__(self):
        return self.__repr__()

    def add(self, item, value):
        if item in self.messages.keys():
            self.messages[item] = value
            return True
        else:
            return False
