ignore_words = ["and","by","in","of","on"]

def create_acryonym(str):
    all_words = str.split()
    acronym = ""
    for word in all_words:
        if word not in ignore_words:
            acronym += word[0]
    return acronym.upper()

print(create_acryonym("North Atlantic Treaty Organization"))
print(create_acryonym("light amplification by stimulated emission of radiation"))