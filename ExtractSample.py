# -*- coding: utf-8 -*-
"""
Created on Sun Sep 22 12:20:12 2019

@author: Zhi En
"""
import os


def extractSample(original_file_dir, sample_dir,part_size,wanted_part):
    header = getHeader(original_file_dir)
    total_rows = getTotalRows(original_file_dir)
    total_parts = (total_rows // part_size) + 1
    file = open(original_file_dir, 'r',encoding="utf8")
    sample = open(sample_dir,'w',encoding="utf8")
        
    if(wanted_part > total_parts):
        print("wanted part out of total number of part")
        return -1;
    
    if(wanted_part != 1):
        sample.write(header)

    for a in range(1, total_parts + 1):
        if(a == wanted_part):
            for i in range (0,part_size):
                line = file.readlines(1)
                if line:
                    sample.write(line[0])
                else:
                    return 1;
            break;
                
        else:
            for i in range(0,part_size):
                file.readlines(1)
                
    os.chmod(sample_dir, 0o777)
    file.close()
    sample.close()

def extractAltSample(original_file_dir, sample_dir, size_reduce_factor):
    file = open(original_file_dir,'r', encoding = 'utf-8')
    line = file.readlines(1)
    if(line == ''): #empty csv
        file.close()
        return -1
    
    sample = open(sample_dir,'w',encoding = 'utf-8')
    counter = size_reduce_factor - 1 #track iteration to write to file
    while(line):
        if(counter % size_reduce_factor == 0):
            sample.write(line[0])
        
        
        counter += 1
        line = file.readlines(1)
    print("lines written to " + sample_dir + ": " , counter // size_reduce_factor)
    file.close()
    sample.close()
    return 1
    
    
    

def getTotalRows(file_dir):
    file = open(file_dir,'r',encoding="utf8")
    rows = 0
    for line in file:
        rows += 1    
    file.close()
    return rows

def getHeader(file_dir):
    file = open(file_dir,encoding="utf8");
    return file.readlines(1)[0];
    
    


#extractSample("C:/Users/Zhi En/Downloads/CE4031_csv/publication.csv",
#              "C:/Users/Zhi En/Downloads/CE4031_csv/sample1.csv",1000000,1)
    
extractAltSample("C:/Users/Zhi En/Downloads/CE4031_csv/publication.csv",
                 "C:/Users/Zhi En/Downloads/CE4031_csv/publication_half.csv", 2)

extractAltSample("C:/Users/Zhi En/Downloads/CE4031_csv/author.csv",
                 "C:/Users/Zhi En/Downloads/CE4031_csv/author_half.csv", 2)

extractAltSample("C:/Users/Zhi En/Downloads/CE4031_csv/authored.csv",
                 "C:/Users/Zhi En/Downloads/CE4031_csv/authored_half.csv", 2)

extractAltSample("C:/Users/Zhi En/Downloads/CE4031_csv/publication.csv",
                 "C:/Users/Zhi En/Downloads/CE4031_csv/publication_quarter.csv", 4)

extractAltSample("C:/Users/Zhi En/Downloads/CE4031_csv/author.csv",
                 "C:/Users/Zhi En/Downloads/CE4031_csv/author_quarter.csv", 4)

extractAltSample("C:/Users/Zhi En/Downloads/CE4031_csv/authored.csv",
                 "C:/Users/Zhi En/Downloads/CE4031_csv/authored_quarter.csv", 4)



       



    