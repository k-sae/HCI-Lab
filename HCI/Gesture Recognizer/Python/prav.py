import json
with open('Templates.json') as data_file:    
             data = json.load(data_file)

templetes = []
for i in range(len(data['Templates'])) : 
    
    templete.append(data['Templates'][i]['Name'])
    templetes.append(templete)
    for m in range(len(data['Templates'][i]['Points'])) : 
         point = [data['Templates'][i]['Points'][m]['x'],data['Templates'][i]['Points'][m]['y']]
         points.append(point)
         point = []
    templete.append(points)
    
    templete = []
    points = []
print(templetes[0])