"""
combine.py
Developed by: Zack Laine

A basic data manipulation script I wrote. 
Combines data about schools spread out over multiple very large CSV files 
into a single file with only the information we wanted to work with.

"""

import csv

values = [["" for i in xrange(0)] for i in xrange(8000)]
majors = {}
# values[0] has the list of column names

#Directory info
with open("Directory information.csv", "rb") as csvfile:
    directoryInfo = csv.reader(csvfile, delimiter=",", quotechar='"')
    i = 0
    for row in directoryInfo:

        #Adds the specified column to the entry for the school
        #Basic data for the school, 0 is the uID
        values[i].append(row[0])
        values[i].append(row[1])
        values[i].append(row[2])
        values[i].append(row[3])
        values[i].append(row[4])
        values[i].append(row[5])
        values[i].append(row[17])
        #should be whether the school is public or private, but it is represented as a numerical scale -- need to decipher.
        values[i].append(row[23])
        i=i+1

#Educational Offerings
with open("Educational offerings.csv", "rb") as csvfile:
    directoryInfo = csv.reader(csvfile, delimiter=",", quotechar='"')
    i = 0
    for row in directoryInfo:
        if row[0] == "UNITID":
            values[0].append(row[11])
            values[0].append(row[12])
            values[0].append(row[13])
            values[0].append(row[14])
            values[0].append(row[15])
            values[0].append(row[16])
            values[0].append(row[17])
            values[0].append(row[18])
            values[0].append(row[19])
            values[0].append(row[20])
            values[0].append(row[21])
            values[0].append(row[22])
            #next four give info on if AP, dual credit, or life experience is accepted
            values[0].append(row[24])
            values[0].append(row[25])
            values[0].append(row[26])
            values[0].append(row[27])
            #next four give info on distance learning options
            values[0].append(row[97])
            values[0].append(row[98])
            values[0].append(row[99])
            values[0].append(row[100])
        #checks the uid for the new file against the uID for each stored school and adds the info to the correct entry when it finds a match
        try:
            for value in values:
                if (row[0] == value[0]) and row[0] != "UNITID" and i < 7800:
                    #Adds the specified column to the entry for the school
                    #These tell if the school offers certain levels of programs, from less than one year through doctorate programs
                    value.append(row[12])
                    value.append(row[13])
                    value.append(row[14])
                    value.append(row[15])
                    value.append(row[16])
                    value.append(row[17])
                    value.append(row[18])
                    value.append(row[19])
                    value.append(row[20])
                    value.append(row[21])
                    value.append(row[22])
                    value.append(row[23])
                    #next four give info on if AP, dual credit, or life experience is accepted
                    value.append(row[25])
                    value.append(row[26])
                    value.append(row[27])
                    value.append(row[28])
                    #next four give info on distance learning options
                    value.append(row[97])
                    value.append(row[98])
                    value.append(row[99])
                    value.append(row[100])
                    #value[i].append("\n")
                    i=i+1

        except IndexError:
                pass

#Admissions info
with open("Admission considerations.csv", "rb") as csvfile:
    directoryInfo = csv.reader(csvfile, delimiter=",", quotechar='"')
    i = 0
    for row in directoryInfo:
        if row[0] == "UNITID":
            values[0].append(row[11])
            values[0].append(row[13])
            values[0].append(row[15])
            values[0].append(row[17])
            values[0].append(row[19])
            values[0].append(row[21])
            values[0].append(row[23])
            #SAT titles
            values[0].append(row[49])
            values[0].append(row[51])
            values[0].append(row[53])
            values[0].append(row[55])
            values[0].append(row[57])
            values[0].append(row[59])
            #ACT titles
            values[0].append(row[61])
            values[0].append(row[63])
            values[0].append(row[65])
            values[0].append(row[67])
            values[0].append(row[69])
            values[0].append(row[71])
            values[0].append(row[73])
            values[0].append(row[75])
        #checks the uid for the new file against the uID for each stored school and adds the info to the correct entry when it finds a match
        #if row[0] !="PEO1ISTR":
        try:
            for value in values:
                if (row[0] == value[0]) and row[0] != "UNITID" and i < 7800:
                    #Adds the specified column to the entry for the school
                    #value.append(row[0])
                    #These give info on application and admissions in total and by gender
                    value.append(row[11])
                    value.append(row[13])
                    value.append(row[15])
                    value.append(row[17])
                    value.append(row[19])
                    value.append(row[21])
                    value.append(row[23])
                    #SAT info
                    value.append(row[49])
                    value.append(row[51])
                    value.append(row[53])
                    value.append(row[55])
                    value.append(row[57])
                    value.append(row[59])
                    #ACT info
                    value.append(row[61])
                    value.append(row[63])
                    value.append(row[65])
                    value.append(row[67])
                    value.append(row[69])
                    value.append(row[71])
                    value.append(row[73])
                    value.append(row[75])
                    i=i+1

        except IndexError:
                pass

#Tuition costs
with open("Student charges.csv", "rb") as csvfile:
    directoryInfo = csv.reader(csvfile, delimiter=",", quotechar='"')
    i = 0
    for row in directoryInfo:
        if row[0] == "UNITID":
            #undergrad costs
            values[0].append(row[8])
            values[0].append(row[10])
            values[0].append(row[12])
            values[0].append(row[14])
            values[0].append(row[16])
            values[0].append(row[18])
            #Graduate costs
            values[0].append(row[26])
            values[0].append(row[28])
            values[0].append(row[30])
            values[0].append(row[32])
            values[0].append(row[34])
            values[0].append(row[36])
        #checks the uid for the new file against the uID for each stored school and adds the info to the correct entry when it finds a match
        #if row[0] !="PEO1ISTR":
        try:
            for value in values:
                if (row[0] == value[0]) and row[0] != "UNITID" and i < 7800:
                    #Adds the specified column to the entry for the school
                    #value.append(row[0])
                    #undergrad costs

                    value.append(row[8])
                    value.append(row[10])
                    value.append(row[12])
                    value.append(row[14])
                    value.append(row[16])
                    value.append(row[18])
                    #grad costs
                    value.append(row[26])
                    value.append(row[28])
                    value.append(row[30])
                    value.append(row[32])
                    value.append(row[34])
                    value.append(row[36])
                    i=i+1

        except IndexError:
                pass

with open("majors.csv", "rb") as csvfile2:
    majorInfo = csv.reader(csvfile2, delimiter=",", quotechar='"')
    for row in majorInfo:
        majors[row[0]] = row[1]

#Majors
with open("Programs offered.csv", "rb") as csvfile:
    directoryInfo = csv.reader(csvfile, delimiter=",", quotechar='"')
    i = 0
    for row in directoryInfo:
        if row[0] == "UNITID":
            #major list
            values[0].append(row[1])
        #checks the uid for the new file against the uID for each stored school and adds the info to the correct entry when it finds a match
        #if row[0] !="PEO1ISTR":
        try:
            for value in values:
                if (row[0] == value[0]) and row[0] != "UNITID" and i < 28000:

                    #Adds the specified column to the entry for the school
                    #adds a major
                    try:
                        value[60] += " " + row[1]
                    except:
                        value.append(row[1])
                    i=i+1

        except IndexError:
                pass

    #for value in values:
    #value[0] is the uID
     #   print value



#writes the data to a new csv file
with open('combined_data.csv', 'wb') as csvfile:
    schoolData = csv.writer(csvfile, delimiter=",", quotechar='"', quoting=csv.QUOTE_MINIMAL)
    for value in values:
        i= 0
        schoolData.writerow(value)
        i=i+1
