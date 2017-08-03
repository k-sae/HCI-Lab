import random
import numpy as np
import matplotlib.pyplot as plt
start = 150
fathersTall=np.arange(150.0, 190.00, 0.4)
sonsTalls = []
for father in fathersTall:
    start=0
    temp=[]
    while start<100:
        temp.append(father*0.7+random.uniform(-1,1))
        start+=1
    sonsTalls.append(temp)
for son in sonsTalls:
    print (son)
drawx=[]
drawy = []
for father , son in zip(fathersTall , sonsTalls):
    for tall in son :
        drawx.append(father)
        drawy.append(tall)
plt.xticks(np.arange(150,190,0.4))
plt.scatter(drawx,drawy)
plt.xlabel("Heights of fathers")
plt.ylabel("Heights of sons")
plt.show()

