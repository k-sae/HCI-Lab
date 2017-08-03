import matplotlib.pyplot as plt
import numpy as np
from sklearn.model_selection import learning_curve
from sklearn import datasets
from sklearn.naive_bayes import GaussianNB
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis

#Generate datasets
X, y = datasets.make_classification(n_samples=2000, n_classes=3, n_features=4, shuffle=False, n_redundant=0, n_informative=3)

print(X)
print(y)

lda = LinearDiscriminantAnalysis()

X_r = lda.fit(X, y).transform(X)

colors = ['red', 'blue']
plt.figure()
for color, i in zip(colors, [0, 1]):
    plt.scatter(X_r[y == i, 0], X_r[y == i, 1], alpha=.8, color=color)

plt.title('LDA')
plt.show()

bayes = GaussianNB()
bayes.fit(X, y)

train_sizes= np.linspace(.1, 1.0, 5)
plt.xlabel("Training examples")
plt.ylabel("Score")
train_sizes, train_scores, test_scores = learning_curve( bayes, X, y, n_jobs=1, train_sizes=train_sizes)


train_scores_mean = np.mean(train_scores, axis=1)
train_scores_std = np.std(train_scores, axis=1)
test_scores_mean = np.mean(test_scores, axis=1)
test_scores_std = np.std(test_scores, axis=1)

plt.fill_between(train_sizes, train_scores_mean - train_scores_std, train_scores_mean + train_scores_std, alpha=0.1, color="r")
plt.fill_between(train_sizes, test_scores_mean - test_scores_std, test_scores_mean + test_scores_std, alpha=0.1, color="g")
plt.plot(train_sizes, train_scores_mean, 'o-', color="r", label="Training score")
plt.plot(train_sizes, test_scores_mean, 'o-', color="g", label="Cross-validation score")

plt.show()
