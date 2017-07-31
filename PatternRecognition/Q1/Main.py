import scipy.stats as stats

a, b = 10, 50
mean, stdDev, limit = 37, 2, 42

z = (limit - mean) / stdDev
probability = stats.norm.sf(abs(z))

dist = stats.truncnorm((a - mean) / stdDev, (b - mean) / stdDev, loc=mean, scale=stdDev)

count = [0]
values = [0]
for num in range(10):
    values[num] = dist.rvs(10000)
    # noinspection PyTypeChecker
    for val in values[num]:
        if val > limit:
            count[num] += 1
    print(count[num])
    count.append(0)
    values.append(0)

# all rand items stored in values
# count holds the occurrence of 42
