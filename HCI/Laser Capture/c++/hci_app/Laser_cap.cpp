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
	
	VideoCapture stream(0);
	if (!stream.isOpened()) { //check if video device has been initialised
		cout << "cannot open camera";
		return 0;
	}
	while (true) {
		double max;
		double min;
		Point max_loc;
		Point min_loc;
		Mat cameraFrame;
		Mat gray;	
		stream.read(cameraFrame);
		cvtColor(cameraFrame, gray, CV_BGR2GRAY);
		minMaxLoc(gray, &min, &max, &min_loc, &max_loc);
		circle(cameraFrame, max_loc, 20, (255, 255, 255), 2);
		imshow("cam", cameraFrame);
		if (waitKey(10) == 27) break;	 // Esc button
	}
	return 0;
}

