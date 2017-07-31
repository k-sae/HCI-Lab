import scipy.stats as stats

a, b = 50, 10
mu, sigma = 37, 2
dist = stats.truncnorm((a - mu) / sigma, (b - mu) / sigma, loc=mu, scale=sigma)

count = [0]
values = [0]
for num in range(10):
    values[num] = dist.rvs(10000)
    # noinspection PyTypeChecker
    for val in values[num]:
        if val > 42:
            count[num] += 1
    print(count[num])
    count.append(0)
    values.append(0)

# all rand items stored in values
# count holds the occurrence of 42
