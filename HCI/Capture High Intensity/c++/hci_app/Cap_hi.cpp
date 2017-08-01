// Amr Sameh
#include "opencv2/core/core.hpp"
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "iostream"
#include<Windows.h>

using namespace cv;
using namespace std;

int main()
{	

	Mat image = imread("6.jpg", CV_LOAD_IMAGE_COLOR);
	Mat gray;
	cvtColor(image, gray, CV_BGR2GRAY);
	double max;
	double min;
	Point max_loc;
	Point min_loc;
	minMaxLoc(gray, &min, &max, &min_loc, &max_loc);
	circle(image, max_loc, 20, (255,255,255), 2);
	namedWindow("Original", CV_WINDOW_AUTOSIZE);
	resizeWindow("Original", 200, 200);
	imshow("Original", image);
		waitKey(0);
	return 0;
}
