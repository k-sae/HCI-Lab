import matplotlib.pyplot as plt
from sklearn import datasets
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis

#Generate datasets of 10 samples
X, y = datasets.make_classification(n_samples=10, n_classes=2, n_features=4, shuffle=False, n_redundant=0, n_informative=3)

print(X)

print(y)
