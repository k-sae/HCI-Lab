import matplotlib.pyplot as plt
import scipy.stats as stats
import numpy as np

a, b = 10, 50
mean, stdDev, limit = 37, 2, 42

z = (limit - mean) / stdDev
probability = stats.norm.sf(abs(z))

dist = stats.truncnorm((a - mean) / stdDev, (b - mean) / stdDev, loc=mean, scale=stdDev)

count = [0]
values = [0]
for num in range(10):
    values[num] = dist.rvs(100000)
    # noinspection PyTypeChecker
    for val in values[num]:
        if val > limit:
            count[num] += 1
    print(count[num])
    count.append(0)
    values.append(0)
plt.hist(count,bins=25, normed=True, alpha=0.6)
plt.xlabel("value")
plt.ylabel("frequency")
xmin, xmax = plt.xlim()
x = np.linspace(xmin, xmax, 100)
p = stats.norm.pdf(x, mean, stdDev)
plt.plot(x, p, 'k', linewidth=2)
plt.show()
# all rand items stored in values
# count holds the occurrence of 42
