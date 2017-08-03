import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis

iris = datasets.load_digits(n_class=10)

X = iris.data
print(X)
y = iris.target
print(y)
print(len(X[0]))
print(len(X))
target_names = iris.target_names
print(target_names)

lda = LinearDiscriminantAnalysis(n_components=2)
X_r = lda.fit(X, y).transform(X)

colors = ['red', 'blue', 'green','black', 'yellow', 'gray']
plt.figure()
for color, i, target_name in zip(colors, [0, 1, 2, 3,4,5,6], target_names):
    plt.scatter(X_r[y == i, 0], X_r[y == i, 1], alpha=.8, color=color)

plt.title('LDA')
plt.show()
