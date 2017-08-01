import random
import numpy as np
import matplotlib.pyplot as plt
start = 150
fathersTall=np.arange(150.0, 190.0, 0.4)


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

plt.scatter(sonsTalls,bins=30, normed=True, alpha=0.6)
plt.xlabel("Heights of fathers")
plt.ylabel("Heights of sons")
plt.show()
