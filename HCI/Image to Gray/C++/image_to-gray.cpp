// Amr Sameh
#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "iostream"
#include<Windows.h>

using namespace cv;
using namespace std;

int main()
{	Mat image = imread("4.jpg", CV_LOAD_IMAGE_COLOR);
	Mat gray;
	cvtColor(image, gray, CV_BGR2GRAY);
	namedWindow("Original", CV_WINDOW_AUTOSIZE);
	resizeWindow("Original", 200, 200);
	imshow("Original", image);
	namedWindow("Gray", CV_WINDOW_AUTOSIZE);
	resizeWindow("Gray", 200, 200);
	imshow("Gray", gray);
	waitKey(0);
	return 0;
}
