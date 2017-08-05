import os
import random
import numpy as np
from mnist import MNIST
import matplotlib.pyplot as plt
from sklearn.neighbors import KNeighborsClassifier
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis


def imagesTo2D(images, size):
    imgs = [[[]]]*size
    for i in range(len(images)):
        imgs[i] = np.reshape(images[i], (-1, 28))

    return imgs

path = os.path.join('datasets', 'mnist')
mndata = MNIST(path)

#Using MNIST, with the original files
images, labels = mndata.load_training()
images2, labels2 = mndata.load_testing()

#Print the first digit
#print(labels[0])
#print(mndata.display(images[0]))

#Convert the whole images to 2D array
imgs = imagesTo2D(images, len(images))

#Plot the first 15 digits
images_and_labels = list(zip(imgs, labels))
plt.figure(figsize=(7, 7))
plt.suptitle("Original", fontsize=20)
for index, (image, label) in enumerate(images_and_labels[:15]):
    plt.subplot(3, 5, index + 1)
    plt.axis('off')
    plt.imshow(image, cmap=plt.cm.gray_r, interpolation='nearest')
    plt.title('%i' % label)
plt.show()

n_samples = len(imgs)
n = np.array(imgs)
x = n.reshape((n_samples, -1))
y = labels

#Create random indices
sample_index = random.sample(range(len(x)), 350)
valid_index = [i for i in range(len(x)) if i not in sample_index]

#Sample and validation images
sample_images = [x[i] for i in sample_index]
valid_images = [x[i] for i in valid_index]

#Sample and validation targets
sample_target = [y[i] for i in sample_index]
valid_target = [y[i] for i in valid_index]

#KNN Classifier
k = 10
knn = KNeighborsClassifier(n_neighbors=k)
knn.fit(sample_images, sample_target)

plt.figure(figsize=(7, 7))
plt.suptitle("KNN Classifier", fontsize=20)
for i, (image, label) in enumerate(images_and_labels[:15]):
    k += ((i+1)*2) #K changes every loop
    knn.n_neighbors = k
    prediction = knn.predict(x[i])
    plt.subplot(3, 5, i + 1)
    plt.axis('off')
    plt.imshow(image, cmap=plt.cm.gray_r, interpolation='nearest')
    plt.title('predicted %i' % prediction[0] + '\nexpected %i' % label)
#plt.show()


#LDA Classifier
lda = LinearDiscriminantAnalysis()
lda.fit(sample_images, sample_target)

plt.figure(figsize=(7, 7))
plt.suptitle("LDA Classifier", fontsize=20)
for i, (image, label) in enumerate(images_and_labels[:15]):
    prediction = lda.predict(x[i])
    plt.subplot(3, 5, i + 1)
    plt.axis('off')
    plt.imshow(image, cmap=plt.cm.gray_r, interpolation='nearest')
    plt.title('predicted %i' % prediction[0] + '\nexpected %i' % label)
#plt.show()


#QDA Classifier
qda = QuadraticDiscriminantAnalysis()
qda.fit(sample_images, sample_target)

plt.figure(figsize=(7, 7))
plt.suptitle("QDA Classifier", fontsize=20)
for i, (image, label) in enumerate(images_and_labels[:15]):
    prediction = qda.predict(x[i])
    plt.subplot(3, 5, i + 1)
    plt.axis('off')
    plt.imshow(image, cmap=plt.cm.gray_r, interpolation='nearest')
    plt.title('predicted %i' % prediction[0] + '\nexpected %i' % label)
plt.show()
