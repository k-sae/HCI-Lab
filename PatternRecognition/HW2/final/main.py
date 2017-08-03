import numpy as np
import matplotlib.pyplot as plt
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsClassifier as KNN


def toPairs(item1, item2, size):
    pairs = []
    for i in range(size):
        pairs.append([item1[i], item2[i]])
    return pairs

# part 1

mean1 = [37, 37]
cov = [[4, 0], [0, 4]]
size1 = 10
class1_x, class1_y = np.random.multivariate_normal(mean=mean1, cov=cov, size=size1).T

mean2 = [39, 39]
class2_x, class2_y = np.random.multivariate_normal(mean=mean2, cov=cov, size=size1).T

y1 = [1]*size1
y2 = [2]*size1

class3 = np.random.multivariate_normal(mean=mean2, cov=cov, size=1000)
class4 = np.random.multivariate_normal(mean=mean2, cov=cov, size=1000)
# part 2
lda = LinearDiscriminantAnalysis()

LinearDiscriminantAnalysis(n_components=2, priors=None)
lda.fit(toPairs(class1_x, class1_y, size1), y1)
test1 = lda.predict(class3 + class4)

print()
